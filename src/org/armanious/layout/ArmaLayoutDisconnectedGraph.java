package org.armanious.layout;

import java.util.HashMap;
import java.util.Map;

import org.armanious.PhysicsVector;

public class ArmaLayoutDisconnectedGraph extends Layout {

	private final Graph[] connectedSubgraphs;
	private final Map<Layout, Boolean> subLayouts;
	private final Graph centralSubgraph;

	public ArmaLayoutDisconnectedGraph(Graph g){
		this.connectedSubgraphs = g.getConnectedSubgraphs();
		this.subLayouts = new HashMap<>();
		for(int i = 0; i < connectedSubgraphs.length; i++){
			final ArmaLayout key = new ArmaLayout(connectedSubgraphs[i]);
			this.subLayouts.put(key, true);
		}

		Graph mostImportantSubgraph = connectedSubgraphs[0];
		for(int i = 1; i < connectedSubgraphs.length; i++){
			if(connectedSubgraphs[i].getNumberNodes() > mostImportantSubgraph.getNumberNodes()){
				mostImportantSubgraph = connectedSubgraphs[i];
			}
		}
		this.centralSubgraph = mostImportantSubgraph;
	}

	public void performLayout(){
		for(Layout l : subLayouts.keySet()){
			subLayouts.put(l, true);
		}
		while(!Thread.interrupted()){
			boolean didIterate = false;
			for(Layout l : subLayouts.keySet()){
				if(subLayouts.get(l)){
					didIterate = true;
					subLayouts.put(l, l.iterate());
				}
			}
			if(didIterate){
				iterate();
			}else{
				break;
			}
		}
	}

	boolean iterate(){
		double sizeOfCentralSubgraph = centralSubgraph.getSize();
		double maxSizeOfPeripheralSubgraphs = Integer.MIN_VALUE;
		for(Graph sg : connectedSubgraphs){
			if(sg == centralSubgraph) continue;
			double size = sg.getSize();
			if(size > maxSizeOfPeripheralSubgraphs){
				maxSizeOfPeripheralSubgraphs = size;
			}
		}
		double distance = (0.4 + 0.4 / connectedSubgraphs.length) * (sizeOfCentralSubgraph + maxSizeOfPeripheralSubgraphs);
		
		int numPeripherals = connectedSubgraphs.length - 1;
		double deltaDirection = (2 * Math.PI) / numPeripherals;
		double direction = 0;
		PhysicsVector centralPos = centralSubgraph.getPosition();
		for(Graph g : connectedSubgraphs){
			if(g == centralSubgraph) continue;
			PhysicsVector offsetFromCentral = PhysicsVector.fromMagnitudeDirection(distance, direction += deltaDirection);
			PhysicsVector newCenter = centralPos.add(offsetFromCentral);
			PhysicsVector curPos = g.getPosition();
			PhysicsVector translation = newCenter.subtract(curPos);
			g.translate(translation);
		}
		

		return true;

	}
	
}
