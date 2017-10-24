package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 *
	 * Please see the README for this machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other methods
	 * should be implemented here.
	 *
	 * You should write the specs for this and all other methods.
	 *
	 * @param graph
	 * @param a
	 *            Vertex must exist within the graph
	 * @param b
	 *            Vertex must exist within the graph
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		// if list starting with a does not contain b, then no link exists, return -1
		for (List<Vertex> list : Algorithms.breadthFirstSearch(graph)) {
			if (list.indexOf(a) == 0) {
				if (!list.contains(b))
					return -1;
			}
		}
		// other base case for no distance
		if (a == b)
			return 0;

		// begin creating path from a to b
		int distance = 0;
		List<Vertex> nextDownStream = new ArrayList<Vertex>();
		List<Vertex> downStream = new ArrayList<Vertex>();
		List<Vertex> allDownStreams = new ArrayList<Vertex>(); // used for tracking already visited nodes to prevent
																// cycles
		downStream.add(a);
		allDownStreams.add(a);

		while (!downStream.contains(b) && !downStream.isEmpty()) {
			distance++;
			for (Vertex vertex : downStream) {
				nextDownStream.addAll(graph.getDownstreamNeighbors(vertex));
				allDownStreams.addAll(downStream);
			}

			// check and remove all common vertices from nextDownStream if already present
			// in allDownStreams
			for (Vertex vertex : allDownStreams) {
				if (nextDownStream.contains(vertex))
					nextDownStream.remove(vertex);
			}

			downStream.clear();
			downStream.addAll(nextDownStream);
			nextDownStream.clear();
		}

		return distance;
	}

	/**
	 * Perform a complete depth first search of the given graph. Start with the
	 * search at each vertex of the graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list of elements seen by
	 * starting a DFS at a specific vertex of the graph (the number of elements in
	 * the returned set should correspond to the number of graph vertices).
	 *
	 * @param
	 * @return
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		// TODO: Implement this method

		Set<List<Vertex>> resultBFS = new HashSet<List<Vertex>>();

		// vertexList holds all vertices to do DFS on
		List<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.addAll(graph.getVertices());

		// list contains DFS results for a given vertex (later added to resultBFS)
		List<Vertex> list = new ArrayList<Vertex>();

		for (int i = 0; i < vertexList.size(); i++) {
			list.clear();
			Stack<Vertex> S = new Stack<Vertex>();
			S.push(vertexList.get(i));
			// boolean[] discovered = new boolean [vertexList.size()];
			while (!S.empty()) {
				Vertex v = S.pop();
				// if list doesn't contain v, add it to list and add DSNs to stack
				if (!list.contains(v)) {
					list.add(v);
					for (Vertex j : graph.getDownstreamNeighbors(v)) {
						S.push(j);
					}

				}
			}
			// need to create a new list to store results of vertex's search for diff memory
			// locations
			List<Vertex> addingList = new ArrayList<Vertex>(list);
			resultBFS.add(addingList);
		}

		return resultBFS; // this should be changed

	}

	/**
	 * Perform a complete breadth first search of the given graph. Start with the
	 * search at each vertex of the graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list of elements seen by
	 * starting a BFS at a specific vertex of the graph (the number of elements in
	 * the returned set should correspond to the number of graph vertices).
	 *
	 * @param graph
	 *            must have correct implementation of graph interface
	 * @return set's # elements = # vertices
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		// TODO: Implement this method

		Set<List<Vertex>> resultBFS = new HashSet<List<Vertex>>();
		// set and initialize capacity of vertexList, then fill the list with vertices
		List<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.addAll(graph.getVertices());

		// list contains children of given vertex
		List<Vertex> list = new ArrayList<Vertex>();

		// do BFS for all vertices
		for (int k = 0; k < graph.getVertices().size(); k++) {
			list.clear();
			list.add(vertexList.get(k));

			Queue<Vertex> queue = new LinkedList<Vertex>();

			// throw 1st parent vertex into queue
			queue.offer(vertexList.get(k));
			while (!queue.isEmpty()) {
				Vertex v = queue.poll();

				// add all UNREPEATED DS neighbours to the queue
				for (int i = 0; i < graph.getDownstreamNeighbors(v).size(); i++) {
					// if entry of list does NOT contain DSN, add it to list and queue
					if (!list.contains(graph.getDownstreamNeighbors(v).get(i))) {
						list.add(graph.getDownstreamNeighbors(v).get(i));
						queue.offer(graph.getDownstreamNeighbors(v).get(i));
					}
				}
			}
			// need to create a new list to store results of vertex's search for diff memory
			// locations
			List<Vertex> addingList = new ArrayList<Vertex>(list);
			resultBFS.add(addingList);
		}

		return resultBFS;
	}

	/**
	 * You should write the spec for this method
	 */
	public static Vertex center(Graph graph) {
		// TODO: Implement this method
		return null; // this should be changed
	}

	/**
	 * You should write the spec for this method
	 */
	public static int diameter(Graph graph) {
		// TODO: Implement this method
		
		return -1; // this should be changed
	}

	/**
	 * You should write the spec for this method
	 */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method
		return null; // this should be changed
	}

	/**
	 * You should write the spec for this method
	 */
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method
		return null; // this should be changed
	}

}
