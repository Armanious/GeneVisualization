package org.armanious.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

import org.armanious.PhysicsVector;

public class Graph {

	private ArrayList<Graph> connectedSubgraphs;
	
	protected final Node[] nodes;

	protected Graph(){
		this.nodes = new Node[0];
	}

	public Graph(Node...nodes){
		this.nodes = Arrays.copyOf(nodes, nodes.length);
	}

	public Graph(Collection<Node> nodes){
		nodes.toArray(this.nodes = new Node[nodes.size()]);
	}

	public boolean isConnectedGraph(){
		return getConnectedSubgraphs().length <= 1;
	}

	public Graph[] getConnectedSubgraphs(){
		if(connectedSubgraphs == null){
			final Node[] nodes = getNodes();
			final ArrayList<ArrayList<Node>> connectedGraphs = new ArrayList<>();
			final HashMap<Node, ArrayList<Node>> nodeToGraphMap = new HashMap<>();

			for(Node node : nodes){
				if(nodeToGraphMap.get(node) != null) continue;
				final ArrayList<Node> newGraph = new ArrayList<>();
				newGraph.add(node);
				connectedGraphs.add(newGraph);
				nodeToGraphMap.put(node, newGraph);

				final Stack<Node> connectedToGraph = new Stack<>();
				for(Node neighbor : node.getNeighbors()){
					connectedToGraph.push(neighbor);
				}

				while(!connectedToGraph.isEmpty()){
					final Node connectedTo = connectedToGraph.pop();
					if(nodeToGraphMap.get(connectedTo) != null) continue;

					nodeToGraphMap.put(connectedTo, newGraph);
					newGraph.add(connectedTo);

					for(Node neighbor : connectedTo.getNeighbors()){
						connectedToGraph.push(neighbor);
					}
				}
			}
			connectedSubgraphs = new ArrayList<>();
			for(ArrayList<Node> componentsOfSubgraph : connectedGraphs){
				connectedSubgraphs.add(new Graph(componentsOfSubgraph));
			}
		}
		return connectedSubgraphs.toArray(new Graph[connectedSubgraphs.size()]);
	}

	public int getNumberNodes(){
		return nodes.length;
	}

	public Node[] getNodes(){
		return Arrays.copyOf(nodes, nodes.length);
	}
	
	public PhysicsVector getPosition(){
		double sumX = 0;
		double sumY = 0;
		for(Node node : nodes){
			sumX += node.getPosition().getX();
			sumY += node.getPosition().getY();
		}
		return PhysicsVector.fromXY(sumX / getNumberNodes(), sumY / getNumberNodes());
	}
	
	public double getSize(){
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for(Node node : nodes){
			double halfNodeSize = node.getNodeSize() * 0.5;
			double x = node.getPosition().getX();
			double y = node.getPosition().getY();
			if(x - halfNodeSize < minX) minX = x - halfNodeSize;
			if(x + halfNodeSize > maxX) maxX = x + halfNodeSize;
			if(y - halfNodeSize < minY) minY = y - halfNodeSize;
			if(y + halfNodeSize > maxY) maxY = y + halfNodeSize;
		}
		return Math.max(maxX - minX, maxY - minY);
	}
	
	public void translate(PhysicsVector pv) {
		for(Node node : nodes){
			node.setPosition(node.getPosition().add(pv));
		}
	}

}
