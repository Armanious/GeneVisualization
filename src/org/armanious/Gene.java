package org.armanious;

import org.armanious.layout.Node;

public class Gene extends Node {
	
	private final String name;
	private final int hgncId;
	
	public Gene(String name){
		this(name, Utilities.getHgncId(name));
	}
	
	private Gene(String name, int hgncId){
		this.name = name;
		this.hgncId = hgncId;
	}
	
	public String toString(){
		return name;
	}

	public String getName() {
		return name;
	}

	public int getHgncID() {
		return hgncId;
	}

}
