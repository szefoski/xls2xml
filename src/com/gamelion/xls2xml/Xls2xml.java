package com.gamelion.xls2xml;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class Xls2xml {
	
	static Path inputPath;
	static Path outputDirectoryPath;
	static int rowStart = 0;
	static int columnKey = 0;
	static ArrayList<LangSheetInfo> lngsInfo = new ArrayList<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello Eclipse!");
		
		parseArg(args);
		
		XlsReader reader = new XlsReader();
		
		reader.read(inputPath, rowStart, columnKey, lngsInfo);
	}
	
	
	private static void parseArg( String[] args ) {
		
		if ( args.length < 5 ) {
			System.err.println( "Arguments error!" );
			printExample();
			System.exit( -1 );
		}

		inputPath = FileSystems.getDefault().getPath(args[0]);
		File inputFile = inputPath.toFile();
		if (inputFile.exists()) {
			if (inputFile.isFile()) {
				if (!inputFile.canRead()) {
					System.err.println( "Can't read from: " + inputFile.getAbsolutePath() );
					System.exit( -1 );
				}
			} else {
				System.err.println( "This is not a file: " + inputFile.getAbsolutePath() );
				System.exit( -1 );
			}
		} else {
			System.err.println( "File doesn't exists: " + inputFile.getAbsolutePath() );
			System.exit( -1 );
		}
		
		outputDirectoryPath = FileSystems.getDefault().getPath(args[1]);
		
		rowStart = Integer.parseInt(args[2]);
		columnKey = Integer.parseInt(args[3]);
		
		
		for ( int i = 4 ; i < args.length; i++ ) {
			lngsInfo.add(parseLangInfo(args[i]));
		}
	}
	
	
	static LangSheetInfo parseLangInfo( String val ) {
		LangSheetInfo li = new LangSheetInfo();
		
		String[] elements = val.split(":");
		
		li.column = Integer.parseInt(elements[0]);
		li.lang = elements[1];
		
		return li;
	}
	
	
	private static void printExample() {
		System.out.println("args: [xls input path] [export direcotry] [row of keys start (0 base)] [key column (0 base)] [column of language (0 base):language name]");
		System.out.println("example: \"d:/texts.xls\" \"d:/res/texts\" 1 1 \"2:en\" \"3:de\" \"4:fr\"");
		System.out.println("example: \"d:/texts.xls\" \"d:/res/texts\" 1 1 \"4:fr\"");
	}
	

	public static class LangSheetInfo {
		int column;
		String lang;
	}
}
