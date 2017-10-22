package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
	// TODO: Implement this class
	
	private int[][] adjMatrix;
	private List<Vertex> vertList;
	
	/**
	 * Constructor that builds an arrayList and a 2x2 int matrix
	 * 
	 * @param arraySize Input number of vertices, must be >0
	 */
	public AdjacencyMatrixGraph(int numVertices){
		vertList = new ArrayList<Vertex>(numVertices);
		adjMatrix = new int[numVertices][numVertices];
		//initialize all edges of adjMatrix to 0
		for (int i = 0; i < vertList.size()+1; i++) {
			adjMatrix[i][i] = 0;
		}
	}	
	
	/**
	 * Adds vertex to row and col of matrix
	 * @param v must not have already been added before
	 */
	@Override
	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		vertList.add(v);	
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		// 1 represents an edge
		if (adjMatrix[vertList.indexOf(v1)][vertList.indexOf(v2)] == 0) {
			adjMatrix[vertList.indexOf(v1)][vertList.indexOf(v2)] = 1;
		}
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		if (adjMatrix[vertList.indexOf(v1)][vertList.indexOf(v2)] == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		List<Vertex> list = new ArrayList<Vertex>();
		for (int i = 0; i < vertList.size(); i++) {
			if (adjMatrix[vertList.indexOf(v)][i] == 1) {
				list.add(vertList.get(i));
			}
		}
		return list;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		List<Vertex> list = new ArrayList<Vertex>();
		for (int i = 0; i < vertList.size(); i++) {
			if (adjMatrix[i][vertList.indexOf(v)] == 1) {
				list.add(vertList.get(i));
			}
		}
		return list;
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return vertList;
	}

}
