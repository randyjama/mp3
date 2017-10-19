package ca.ubc.ece.cpen221.mp3.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

//must internally represent the graph as an adjacency list
//use hash table to link each vertex of graph with array of adjacent vertices
//???Why use array list instead of just list, since downstream demands we return an actual list, not array list?
public class AdjacencyListGraph implements Graph {

	private Map<Vertex, ArrayList<Vertex>> adjGraph;

	// constructor of hashmap
	public AdjacencyListGraph() {
		adjGraph = new HashMap<Vertex, ArrayList<Vertex>>();
	}

	@Override
	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		adjGraph.put(v, null);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		// ???need to throw v1/v2 into array list 1st? is arraylist below supposed to be
		// an instance variable above?
		ArrayList<Vertex> arrayEdge = new ArrayList<Vertex>();
		arrayEdge.add(v1);
		arrayEdge.add(v2);
		adjGraph.put(null, arrayEdge);
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		// since directional, v1 is key and v2 is value in key's list array

		for (Vertex key : adjGraph.keySet()) { // iterate through all keys
			if (key == v1) {
				// iterate through values of arrayList for v2 match
				for (Vertex edge : adjGraph.get(v1)) {
					if (edge == v2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub

		List<Vertex> returnList = new ArrayList<Vertex>();

		// iterate through all keys
		for (Vertex key : adjGraph.keySet()) {
			// if arraylist of key is empty (no edges), return empty list
			if (adjGraph.get(key) == null) {
				returnList.clear();
				return returnList;
			} else if (key == v) { // match found
				// iterate through values of key matched
				// put arraylist into returnList and return latter
				int i = 0;
				for (ArrayList<Vertex> edge : adjGraph.values()) { // ???why arraylist<vertex>, is edge an arraylist or
																	// value?
					returnList.add(edge.get(i));
					i++;
				}

				return returnList;
			}
			// ??? do I need an else statement here to handle if key =/= v, or will it just
			// cycle back thru the loop
		}
		return null;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	// TODO: Implement this class
}
