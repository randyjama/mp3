package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		ArrayList<Vertex> arrayEdge = new ArrayList<Vertex>();
		adjGraph.put(v, arrayEdge);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		adjGraph.get(v1).add(v2);
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		// since directional, v1 is key and v2 is value in key's list array
			if (adjGraph.get(v1).contains(v2)) {
				return true;
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
				for (Vertex edge : adjGraph.get(v)) {
					returnList.add(edge);
				}

				return returnList;
			}
		}
		return null;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		//iterate through keys u to find v in corresponding value arraylist
		List<Vertex> returnList = new ArrayList<Vertex>();
		returnList.clear();
		
		for (Vertex key : adjGraph.keySet()) {
			for (Vertex edge : adjGraph.get(key)) {
				if (edge == v) {
					//add key to return list
					returnList.add(key);
				}
			}
		}
		
		return returnList;
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		List<Vertex> returnList = new ArrayList<>(adjGraph.keySet());
		
		return returnList;
	}
}
