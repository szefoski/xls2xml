package com.gamelion.xls2xml;

import java.util.HashMap;

public class Bank {
	
	static protected HashMap<String, Dict> dicts = new HashMap<String, Dict>();
	
	static void add( String key, String value, String lang ) {
		Dict dict = dicts.get(lang);
		
		if ( dict == null ) {
			dict = new Dict();
			dicts.put(lang, dict);
		}
		
		dict.Add( key, value );
	}
}
