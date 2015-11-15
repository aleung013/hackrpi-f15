package graph;
import java.util.*;

public class Node {
	double freq;
	String word;
	HashMap<String,Node> children = new HashMap<String,Node>();
	
	public Node( String aWord, double aFreq ) {
		freq = aFreq;
		word = aWord;
	}
	
	public Node( String aWord ) {
		word = aWord;
	}
	
	public void addChild( Node child ) {
		children.put( child.word, child);
	}
	
	public String parse( ArrayList<String> data ){
		String ret = "";
		int words = 0;
		for( int i = -1; i < data.size(); i++ ) {
			String retTemp = "";
			int wordsTemp = 0;
			HashMap<String, Node> tempMap = children;
			int j = 0;
			for( ; j < data.size(); j++ ) {
				if( word == data.get(j) && j != i) {
					retTemp += word;
					wordsTemp++;
					break;
				}
			}
			for( ; j < data.size(); j++ ) {
				if( tempMap.containsKey( data.get(j) ) && j != i ) {
					retTemp += " " + tempMap.get( data.get(j) ).word;
					tempMap = tempMap.get( data.get(j) ).children;
					wordsTemp++;
				}
			}
			if( wordsTemp > words ) {
				ret = retTemp;
				words = wordsTemp;
			}
		}
		return ret + ".";		
	}
	
	public static void main(String[] args) {
		System.out.println("Hi");
	}
}