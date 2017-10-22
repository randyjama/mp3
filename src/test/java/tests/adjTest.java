package tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class adjTest {

	@Test
	//test addVertex method
	public void test0() throws IOException {
		Vertex v1 = new Vertex("Dante"); //dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish");// dante to trish
		Vertex v4 = new Vertex("Megaman"); //megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		Vertex v6 = new Vertex("Link"); //link is unmatched
		
		Graph adjGraph = new AdjacencyListGraph();
		
		adjGraph.addVertex(v1);
		adjGraph.addVertex(v2);
		adjGraph.addVertex(v3);
		adjGraph.addVertex(v4);
		adjGraph.addVertex(v5);
		adjGraph.addVertex(v6);
		
		adjGraph.addEdge(v1, v2);
		adjGraph.addEdge(v2, v1);
		adjGraph.addEdge(v1, v3);
		adjGraph.addEdge(v4, v5);
		adjGraph.addEdge(v5, v4);
		
		//test list of vertices
		assert(adjGraph.getVertices().contains(v1));
		assert(adjGraph.getVertices().contains(v2));
		assert(adjGraph.getVertices().contains(v3));
		assert(adjGraph.getVertices().contains(v4));
		assert(adjGraph.getVertices().contains(v5));
		assert(adjGraph.getVertices().contains(v6));
		
		//test edges
		assert(adjGraph.edgeExists(v1, v2));
		assert(adjGraph.edgeExists(v2, v1));
		assert(adjGraph.edgeExists(v1, v3));
		assert(adjGraph.edgeExists(v4, v5));
		assert(adjGraph.edgeExists(v5, v4));
		assertFalse(adjGraph.edgeExists(v1, v6)); //untrue edge
		
		//test downstream neighbours
		assert(adjGraph.getDownstreamNeighbors(v1).contains(v2)); //Dante -> Vergil
		assert(adjGraph.getDownstreamNeighbors(v1).contains(v3)); //Dante -> Trish
		assertFalse(adjGraph.getDownstreamNeighbors(v1).contains(v5)); //Dante does NOT link Zero
		assert(adjGraph.getDownstreamNeighbors(v4).contains(v5)); //Megaman -> Zero
		
		//test upstream neighbours
		assert(adjGraph.getUpstreamNeighbors(v1).contains(v2)); //Dante <- Vergil
		assertFalse(adjGraph.getUpstreamNeighbors(v1).contains(v3)); //dante <-/- Trish
		assert(adjGraph.getUpstreamNeighbors(v4).contains(v5)); //Megaman <- Zero
		

	}
	
	@Test
	public void test1() throws IOException {
		Vertex v1 = new Vertex("Dante"); //dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish");// dante to trish
		Vertex v4 = new Vertex("Megaman"); //megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		//Vertex v6 = new Vertex("Link"); //link is unmatched
		
		Graph adjGraph = new AdjacencyMatrixGraph(5);
		
		adjGraph.addVertex(v1);
		adjGraph.addVertex(v2);
		adjGraph.addVertex(v3);
		adjGraph.addVertex(v4);
		adjGraph.addVertex(v5);
		//adjGraph.addVertex(v6);
		
		adjGraph.addEdge(v1, v2);
		adjGraph.addEdge(v2, v1);
		adjGraph.addEdge(v1, v3);
		adjGraph.addEdge(v4, v5);
		adjGraph.addEdge(v5, v4);
		
		//test list of vertices
		assert(adjGraph.getVertices().contains(v1));
		assert(adjGraph.getVertices().contains(v2));
		assert(adjGraph.getVertices().contains(v3));
		assert(adjGraph.getVertices().contains(v4));
		assert(adjGraph.getVertices().contains(v5));
		//assert(adjGraph.getVertices().contains(v6));

		//test edges
		assert(adjGraph.edgeExists(v1, v2));
		assert(adjGraph.edgeExists(v2, v1));
		assert(adjGraph.edgeExists(v1, v3));
		assert(adjGraph.edgeExists(v4, v5));
		assert(adjGraph.edgeExists(v5, v4));
		assertFalse(adjGraph.edgeExists(v1, v5)); //untrue edge
		
		//test downstream neighbours
		assert(adjGraph.getDownstreamNeighbors(v1).contains(v2)); //Dante -> Vergil
		assert(adjGraph.getDownstreamNeighbors(v1).contains(v3)); //Dante -> Trish
		assertFalse(adjGraph.getDownstreamNeighbors(v1).contains(v5)); //Dante does NOT link Zero
		assert(adjGraph.getDownstreamNeighbors(v4).contains(v5)); //Megaman -> Zero
		
		//test upstream neighbours
		assert(adjGraph.getUpstreamNeighbors(v1).contains(v2)); //Dante <- Vergil
		assertFalse(adjGraph.getUpstreamNeighbors(v1).contains(v3)); //dante <-/- Trish
		assert(adjGraph.getUpstreamNeighbors(v4).contains(v5)); //Megaman <- Zero
	}

}
