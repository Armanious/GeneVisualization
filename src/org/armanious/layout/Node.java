package org.armanious.layout;

import java.util.HashSet;
import java.util.Set;

import org.armanious.PhysicsVector;

public abstract class Node {
	
	private PhysicsVector position = PhysicsVector.ZERO_VECTOR;
	private final Set<Node> neighbors = new HashSet<>();
	
	public final PhysicsVector getPosition(){
		return position;
	}
	
	public final void setPosition(PhysicsVector pos){
		position = pos;
	}
	
	public final void connectTo(Node other){
		if(this == other) return;
		neighbors.add(other);
		other.neighbors.add(this);
	}
	
	public int getNodeSize(){
		//2500 connections: size is 500
		//1000 connections: size is 200
		return (int) (5 + getNodeDegree());
	}
	
	public final int getNodeDegree(){
		return neighbors.size();
	}
	
	public final Node[] getNeighbors(){
		return neighbors.toArray(new Node[neighbors.size()]);
	}

	public final boolean isConnectedTo(Node other){
		return neighbors.contains(other);
	}

}
