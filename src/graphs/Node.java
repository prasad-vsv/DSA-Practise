package graphs;

import java.util.*;

public class Node {
	int data;
	// Set<Node> adjacencySet; This method wont scale for weighted graphs
	Set<Edge> adjacencySet;

	public Node() {
		this.adjacencySet = new HashSet<>();
	}

	public Node(int data) {
		this();
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Node)) {
			return false;
		}
		Node temp = (Node) o;

		return this.data == temp.data; // should we check for all the edges as
										// well?
	}

	@Override
	public String toString() {
		return "" + this.data;
	}
}

class Edge {
	Node to;
	Node from;
	int weight = -1; // meaning not a weighted graph
	boolean directional = false; // whether the edge is directional or not is
									// the property of edge and graph but not of
									// node itself

	public Edge(Node from, Node to, boolean directional) {
		this.to = to;
		this.from = from;
	}

	public Edge(Node from, Node to, int weight, boolean directional) {
		this(to, from, directional);
		this.weight = weight;
	}
	
	public Node getDestination(Node from){
		if(from == null){
			return null;
		}
		if(this.directional){
			return to;
		}
		Node to = (from.equals(this.from))? this.to:this.from; //return the opposite one
		return to;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Edge)){
			return false;
		}
		Edge e = (Edge) o;
		return this.from.equals(e.from) && this.to.equals(e.to);
	}

	@Override
	public String toString() {
		return from.data + ":" + to.data;
	}
}

class Graph {
	Map<Integer, Node> nodes = new HashMap<>();

	boolean directed = false;
	List<Edge> edges; // optional

	public Graph(boolean _directed) {
		this.nodes = new HashMap<>();
		this.edges = new ArrayList<>();
		this.directed = _directed;
	}

	public Graph addNode(Node n) {
		if (n == null) {
			return null;
		}
		this.nodes.put(n.data, n);
		return this;
	}

	public Graph addEdge(int a, int b) {
		if (!nodes.containsKey(a)) {
			nodes.put(a, new Node(a));
		}
		if (!nodes.containsKey(b)) {
			nodes.put(b, new Node(b));
		}
		Edge e = new Edge(nodes.get(a), nodes.get(b), directed);
		if (directed) {
			nodes.get(a).adjacencySet.add(e);
			// it is a directed graph and so it can only be considered that edge
			// exists from a to b
		} else {
			nodes.get(a).adjacencySet.add(e);
			nodes.get(b).adjacencySet.add(e);
		}

		return this;
	}
	
	public Graph addEdge(int a, int b, int weight) {
		if (!nodes.containsKey(a)) {
			nodes.put(a, new Node(a));
		}
		if (!nodes.containsKey(b)) {
			nodes.put(b, new Node(b));
		}
		Edge e = new Edge(nodes.get(a), nodes.get(b), weight,  directed);
		if (directed) {
			nodes.get(a).adjacencySet.add(e);
			// it is a directed graph and so it can only be considered that edge
			// exists from a to b
		} else {
			nodes.get(a).adjacencySet.add(e);
			nodes.get(b).adjacencySet.add(e);
		}

		return this;
	}
}