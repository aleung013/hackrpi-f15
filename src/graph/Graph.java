package graph;
import graph.Node;
import java.util.*;

public class Graph{

	public static void main(String[] args) {
		ArrayList<String> testString = new ArrayList<String>();
		testString.add("I");
		testString.add("have");
		testString.add("am");
		testString.add("a");
		testString.add("person");
		
		Node I = new Node( "I" );
		Node have = new Node( "have" );
		Node am = new Node( "am" );
		Node a = new Node( "a" );
		Node person = new Node( "person" );
		I.addChild( have );
		I.addChild( am );
		am.addChild( a );
		a.addChild( person );
		System.out.println( testString );
		System.out.println( I.parse( testString ) );
	}  
}