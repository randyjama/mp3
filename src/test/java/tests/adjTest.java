package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class adjTest {

	@Test
	// test addVertex method
	public void adjListTest() throws IOException {
		Vertex v1 = new Vertex("Dante"); // dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish");// dante to trish
		Vertex v4 = new Vertex("Megaman"); // megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		Vertex v6 = new Vertex("Link"); // link is unmatched

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

		// test list of vertices
		assert (adjGraph.getVertices().contains(v1));
		assert (adjGraph.getVertices().contains(v2));
		assert (adjGraph.getVertices().contains(v3));
		assert (adjGraph.getVertices().contains(v4));
		assert (adjGraph.getVertices().contains(v5));
		assert (adjGraph.getVertices().contains(v6));

		// test edges
		assert (adjGraph.edgeExists(v1, v2));
		assert (adjGraph.edgeExists(v2, v1));
		assert (adjGraph.edgeExists(v1, v3));
		assert (adjGraph.edgeExists(v4, v5));
		assert (adjGraph.edgeExists(v5, v4));
		assertFalse(adjGraph.edgeExists(v1, v6)); // untrue edge
		assertFalse(adjGraph.edgeExists(v1, v1)); // untrue edge

		// test downstream neighbours
		assert (adjGraph.getDownstreamNeighbors(v1).contains(v2)); // Dante -> Vergil
		assert (adjGraph.getDownstreamNeighbors(v1).contains(v3)); // Dante -> Trish
		assertFalse(adjGraph.getDownstreamNeighbors(v1).contains(v5)); // Dante does NOT link Zero
		assert (adjGraph.getDownstreamNeighbors(v4).contains(v5)); // Megaman -> Zero

		// test upstream neighbours
		assert (adjGraph.getUpstreamNeighbors(v1).contains(v2)); // Dante <- Vergil
		assertFalse(adjGraph.getUpstreamNeighbors(v1).contains(v3)); // dante <-/- Trish
		assert (adjGraph.getUpstreamNeighbors(v4).contains(v5)); // Megaman <- Zero

	}

	@Test
	public void adjMatrixTest() throws IOException {
		Vertex v1 = new Vertex("Dante"); // dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish"); // dante to trish
		Vertex v4 = new Vertex("Megaman"); // megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		// Vertex v6 = new Vertex("Link"); //link is unmatched

		Graph adjGraph = new AdjacencyMatrixGraph(5);

		adjGraph.addVertex(v1);
		adjGraph.addVertex(v2);
		adjGraph.addVertex(v3);
		adjGraph.addVertex(v4);
		adjGraph.addVertex(v5);
		// adjGraph.addVertex(v6);

		adjGraph.addEdge(v1, v2);
		adjGraph.addEdge(v2, v1);
		adjGraph.addEdge(v1, v3);
		adjGraph.addEdge(v4, v5);
		adjGraph.addEdge(v5, v4);

		// test list of vertices
		assert (adjGraph.getVertices().contains(v1));
		assert (adjGraph.getVertices().contains(v2));
		assert (adjGraph.getVertices().contains(v3));
		assert (adjGraph.getVertices().contains(v4));
		assert (adjGraph.getVertices().contains(v5));
		// assert(adjGraph.getVertices().contains(v6));

		// test edges
		assert (adjGraph.edgeExists(v1, v2));
		assert (adjGraph.edgeExists(v2, v1));
		assert (adjGraph.edgeExists(v1, v3));
		assert (adjGraph.edgeExists(v4, v5));
		assert (adjGraph.edgeExists(v5, v4));
		assertFalse(adjGraph.edgeExists(v1, v5)); // untrue edge
		assertFalse(adjGraph.edgeExists(v1, v1)); // untrue edge

		// test downstream neighbours
		assert (adjGraph.getDownstreamNeighbors(v1).contains(v2)); // Dante -> Vergil
		assert (adjGraph.getDownstreamNeighbors(v1).contains(v3)); // Dante -> Trish
		assertFalse(adjGraph.getDownstreamNeighbors(v1).contains(v5)); // Dante does NOT link Zero
		assert (adjGraph.getDownstreamNeighbors(v4).contains(v5)); // Megaman -> Zero

		// test upstream neighbours
		assert (adjGraph.getUpstreamNeighbors(v1).contains(v2)); // Dante <- Vergil
		assertFalse(adjGraph.getUpstreamNeighbors(v1).contains(v3)); // dante <-/- Trish
		assert (adjGraph.getUpstreamNeighbors(v4).contains(v5)); // Megaman <- Zero
	}

	@Test
	public void bfsTest() throws IOException {
		Vertex v1 = new Vertex("Dante"); // dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish"); // dante to trish
		Vertex v4 = new Vertex("Megaman"); // megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		Vertex v7 = new Vertex("Blue");
		// Vertex v6 = new Vertex("Link"); //link is unmatched

		Graph adjGraph = new AdjacencyMatrixGraph(6);

		// Set<List> setCompare = new HashSet<List>();
		// Algorithms alg = new Algorithms();

		adjGraph.addVertex(v1);
		adjGraph.addVertex(v2);
		adjGraph.addVertex(v3);
		adjGraph.addVertex(v4);
		adjGraph.addVertex(v5);
		adjGraph.addVertex(v7);
		// adjGraph.addVertex(v6);

		adjGraph.addEdge(v1, v2);
		adjGraph.addEdge(v2, v1);
		adjGraph.addEdge(v1, v3);
		adjGraph.addEdge(v4, v5);
		adjGraph.addEdge(v5, v4);
		adjGraph.addEdge(v2, v7);

		Set<List<Vertex>> setCompare = new HashSet<>();
		setCompare = Algorithms.breadthFirstSearch(adjGraph);

		assert (setCompare.size() == 6);
	}

	@Test
	public void dfsTest() throws IOException {
		Vertex v1 = new Vertex("Dante"); // dante vergil linked both ways
		Vertex v2 = new Vertex("Vergil");
		Vertex v3 = new Vertex("Trish"); // dante to trish
		Vertex v4 = new Vertex("Megaman"); // megaman zero linked both ways
		Vertex v5 = new Vertex("Zero");
		Vertex v7 = new Vertex("Blue");
		// Vertex v6 = new Vertex("Link"); //link is unmatched

		Graph adjGraph = new AdjacencyMatrixGraph(6);

		// Set<List> setCompare = new HashSet<List>();
		// Algorithms alg = new Algorithms();

		adjGraph.addVertex(v1);
		adjGraph.addVertex(v2);
		adjGraph.addVertex(v3);
		adjGraph.addVertex(v4);
		adjGraph.addVertex(v5);
		adjGraph.addVertex(v7);
		// adjGraph.addVertex(v6);

		adjGraph.addEdge(v1, v2);
		adjGraph.addEdge(v2, v1);
		adjGraph.addEdge(v1, v3);
		adjGraph.addEdge(v4, v5);
		adjGraph.addEdge(v5, v4);
		adjGraph.addEdge(v2, v7);

		Set<List<Vertex>> setCompare = new HashSet<>();
		setCompare = Algorithms.depthFirstSearch(adjGraph);

		assert (setCompare.size() == 6);
	}

	@Test
	public void distanceTest() throws IOException {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");
		Vertex v6 = new Vertex("6");
		Vertex v7 = new Vertex("7");
		Vertex v8 = new Vertex("8");

		
		Graph graph = new AdjacencyListGraph();
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);

		graph.addEdge(v1, v4);
		graph.addEdge(v1, v2);
		graph.addEdge(v2, v1);
		graph.addEdge(v2, v4);
		graph.addEdge(v4, v5);
		graph.addEdge(v2, v3);
		graph.addEdge(v2, v6);
		graph.addEdge(v6, v2);
		graph.addEdge(v4, v7);
		graph.addEdge(v7, v4);
		graph.addEdge(v7, v8);
		
		assertEquals(2, Algorithms.shortestDistance(graph, v1, v3));
		assertEquals(3, Algorithms.shortestDistance(graph, v1, v8));
		assertFalse(Algorithms.shortestDistance(graph, v6, v8) == 2);
		assertEquals(4, Algorithms.shortestDistance(graph, v6, v8));
		assertFalse(Algorithms.shortestDistance(graph, v5, v6) == 3);
		assertEquals(2, Algorithms.shortestDistance(graph, v7, v5));
	}
	
	@Test
	public void diameterTest() throws IOException {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");
		Vertex v6 = new Vertex("6");
		Vertex v7 = new Vertex("7");
		Vertex v8 = new Vertex("8");

		
		Graph graph = new AdjacencyListGraph();
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);

		graph.addEdge(v1, v4);
		graph.addEdge(v1, v2);
		graph.addEdge(v2, v1);
		graph.addEdge(v2, v4);
		graph.addEdge(v4, v5);
		graph.addEdge(v2, v3);
		graph.addEdge(v2, v6);
		graph.addEdge(v6, v2);
		graph.addEdge(v4, v7);
		graph.addEdge(v7, v4);
		graph.addEdge(v7, v8);
		
		assertEquals(4, Algorithms.diameter(graph));
		assertFalse(Algorithms.diameter(graph) == 3);
	}
	
	@Test
	public void centerTest() throws IOException {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");
		Vertex v6 = new Vertex("6");
		Vertex v7 = new Vertex("7");
		Vertex v8 = new Vertex("8");

		
		Graph graph = new AdjacencyListGraph();
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);

		graph.addEdge(v1, v4);
		graph.addEdge(v1, v2);
		graph.addEdge(v2, v1);
		graph.addEdge(v2, v4);
		graph.addEdge(v4, v5);
		graph.addEdge(v2, v3);
		graph.addEdge(v2, v6);
		graph.addEdge(v6, v2);
		graph.addEdge(v4, v7);
		graph.addEdge(v7, v4);
		graph.addEdge(v7, v8);
		
		//Vertex result = Algorithms.center(graph);
		
		//either v4 or v5 work for this graph center
		assertTrue(Algorithms.center(graph).equals(v4) || Algorithms.center(graph).equals(v7));
	}
	
}
