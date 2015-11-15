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
		HashMap<String, Node> temp = children;
		int i = 0;
		for( ; i < data.size(); i++ ) {
			if( word == data.get(i) ) {
				// System.out.println( word );
				ret += word;
				break;
			}
		}
		for( ; i < data.size(); i++ ) {
			// System.out.println(j);
			if( temp.containsKey( data.get(i) ) ) {
				// System.out.println( temp.get(j).word );
				ret += " " + temp.get( data.get(i) ).word;
				temp = temp.get( data.get(i) ).children;
			}
		}
		return ret + ".";
	}
	
	public static void main(String[] args) {
		System.out.println("Hi");
	}
}