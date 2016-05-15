package com.playground.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListAggregator {
	
	private List<String> names;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public ListAggregator() {
		super();
		names = new ArrayList<String>();
	}
	
}
