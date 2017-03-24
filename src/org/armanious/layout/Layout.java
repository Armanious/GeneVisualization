package org.armanious.layout;

import java.util.Arrays;

import org.armanious.PhysicsVector;

public abstract class Layout {
	
	public abstract void performLayout();
	
	abstract boolean iterate();

	public static void hueristicLayout(Graph g){
		double magnitude = 0;
		double direction = 0;
		Node[] nodes = g.getNodes();
		if(nodes.length > 100){
			Arrays.sort(nodes, (n1,n2) -> n1.getNodeDegree() - n2.getNodeDegree());
			int i;
			for(i = 0; i < nodes.length; i++){
				if(nodes[i].getNodeDegree() >= nodes.length / 10){
					break;
				}
				nodes[i].setPosition(PhysicsVector.fromMagnitudeDirection(magnitude += nodes[i].getNodeDegree(), direction += 2 * (2 * Math.PI) / nodes.length));
			}
			magnitude += nodes[nodes.length - 1].getNodeSize();
			int numHubs = nodes.length - i;
			direction = 0;
			for(; i < nodes.length; i++){
				nodes[i].setPosition(PhysicsVector.fromMagnitudeDirection(magnitude, direction += 2 * Math.PI / numHubs));
			}
		}else{
			for(Node node : nodes){
				node.setPosition(PhysicsVector.fromMagnitudeDirection(magnitude += node.getNodeSize() + 5, direction += 2 * Math.PI / Math.max(10, (nodes.length / 10))));
			}
		}
	}

}
