package org.armanious.layout;

import java.util.ArrayList;

import org.armanious.PhysicsVector;

public class ArmaLayout extends Layout {

	private static final double NODE_REPULSION_POWER = 2.5;
	private static final double SPRING_STIFFNESS = .25;

	private final Node[] hubNodes;
	private final Node[] nodes;

	public ArmaLayout(Graph g){
		this.nodes = g.getNodes();
		
		ArrayList<Node> hubs = new ArrayList<>();
		if(nodes.length >= 100){
			for(Node node : nodes){
				if(node.getNodeDegree() >= nodes.length / 10){
					hubs.add(node);
				}
			}
		}
		hubNodes = hubs.toArray(new Node[hubs.size()]);
	}

	public void performLayout(){
		while(!Thread.interrupted()){
			if(!iterate()){
				break;
			}
		}
	}
	
	boolean iterate(){
		//numIters++;
		double totalDisplacement = 0;

		for(int i = 0; i < hubNodes.length; i++){
			final Node curHub = hubNodes[i];
			PhysicsVector netForce = PhysicsVector.ZERO_VECTOR;
			for(int j = 0; j < hubNodes.length; j++){
				if(i == j) continue;
				netForce = netForce.add(calculateRepulsionForce(curHub, hubNodes[j]).multiply(Math.log10(2 * (curHub.getNodeDegree() + hubNodes[j].getNodeDegree()))));
			}
			double damping = 1D / Math.max(1, 2 * Math.log10(nodes.length) * curHub.getNodeDegree());
			PhysicsVector newPos = curHub.getPosition().add(netForce.multiply(damping));
			curHub.setPosition(newPos);
		}

		for(int i = 0; i < nodes.length; i++){
			final Node curNode = nodes[i];
			final PhysicsVector curPos = curNode.getPosition();
			PhysicsVector netForce = PhysicsVector.ZERO_VECTOR;
			for(int j = 0; j < nodes.length; j++){
				if(i == j) continue;
				netForce = netForce.add(calculateRepulsionForce(curNode, nodes[j]));
			}
			for(Node other : curNode.getNeighbors()){
				netForce = netForce.subtract(calculateAttractionForce(curNode, other));
			}
			double damping = 1D / Math.max(1, 2 * Math.log10(nodes.length) * curNode.getNodeDegree());
			PhysicsVector newPos = curNode.getPosition().add(netForce.multiply(damping));
			totalDisplacement += curPos.distanceTo(newPos);
			curNode.setPosition(newPos);

		}

		if(totalDisplacement < nodes.length / 15D){
			return false;
		}
		
		return true;
	}

	private static double distanceForceNoOverlap(Node n1, Node n2){
		final double x1 = n1.getPosition().getX();
		final double y1 = n1.getPosition().getY();
		final double x2 = n2.getPosition().getX();
		final double y2 = n2.getPosition().getY();

		final double deltaX = x2 - x1;
		final double deltaY = y2 - y1;

		return Math.max(1, Math.sqrt(deltaX * deltaX + deltaY * deltaY) - (n1.getNodeSize() + n2.getNodeSize()));
	}

	private static PhysicsVector calculateRepulsionForce(Node n1, Node n2){
		double distance = distanceForceNoOverlap(n1,n2);

		
		double force;
		if(n1.getNodeDegree() <= 3 || n2.getNodeDegree() <= 3){
			force = Math.pow( Math.pow((n1.getNodeSize() + n2.getNodeSize())/5, 2), NODE_REPULSION_POWER) / (distance * distance);
		}else{
			force = Math.pow((n1.getNodeSize() * n2.getNodeSize()), NODE_REPULSION_POWER) / (distance * distance);
		}
		if(force > 5000){
			double n = n1.getNodeSize() * n2.getNodeSize();
			n = (Math.log10(n) + n) * 0.25;
			force = Math.pow(n, NODE_REPULSION_POWER) / (distance * distance);
			if(force > 1000){
				force = 1000;
			}
		}
		/*if(force >= 5000){
			//force = 5000;
			System.err.println("anomaly");
			if(n1.getConnectivity() <= 3 || n2.getConnectivity() <= 3){
				force = Math.pow( Math.pow((n1.getNodeSize() + n2.getNodeSize())/5, 2), NODE_REPULSION_POWER) / (distance * distance);
			}else{
				double n = n1.getNodeSize() * n2.getNodeSize();
				n = (Math.log10(n) + n) * 0.25;
				force = Math.pow(n, NODE_REPULSION_POWER) / (distance * distance);
			}
			force = 5000;
		}*/
		double angle = n1.getPosition().subtract(n2.getPosition()).getDirection();

		return PhysicsVector.fromMagnitudeDirection(force, angle);
	}

	private static PhysicsVector calculateAttractionForce(Node n1, Node n2){
		double distance = distanceForceNoOverlap(n1,n2);
		
		double force = SPRING_STIFFNESS * Math.max(distance, 0);
		
		if(n1.getNodeDegree() <= 3 && n2.getNodeDegree() <= 3){
			force = force * 1.5;
		}
		
		double angle = n1.getPosition().subtract(n2.getPosition()).getDirection();

		return PhysicsVector.fromMagnitudeDirection(force, angle);
	}

}
