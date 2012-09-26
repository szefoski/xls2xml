package com.gamelion.xls2xml;

import java.util.ArrayList;

public class Dict {
	
	protected ArrayList<DictElement> elements = new ArrayList<DictElement>();
	
	
	public void Add( String key, String value ) {
		elements.add( new DictElement(key, value) );
	}
}
