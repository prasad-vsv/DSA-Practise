package graphs;

import java.util.*;

public class Node {
	int data;
	Set<Node> adjacencySet;
	
	public Node() {
		this.adjacencySet = new HashSet<>();
	}
	
	public Node(int data){
		this();
		this.data = data;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Node)){
			return false;
		}
		Node temp = (Node) o;
		
		return this.data == temp.data; //should we check for all the edges as well? 
	}
	
	@Override
	public String toString(){
		return ""+this.data;
	}
}
 

class Edge{
	Node to;
	Node from;
	int weight;
	
	public Edge(Node to, Node from) {
		this.to = to;
		this.from = from;
		this.weight = 0;
	}
	
	public Edge(int to, int from){
		this.to = new Node(to);
		this.from = new Node(from);
		this.weight = 0;
	}
	
	public Edge(Node to, Node from, int weight){
		this(to,from);
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return from.data + ":" + to.data;  
	}
}


class Graph{
	Map<Integer,Node> nodes= new HashMap<>();
	
	
	List<Edge> edges; //optional
	
	public Graph() {
		this.nodes = new HashMap<>();
		this.edges = new ArrayList<>();
	}
	
	public Graph addNode(Node n){
		if(n == null){
			return null;
		}
		this.nodes.put(n.data,n);
		return this;
	}
	
	
	
	public Graph addEdge(int a, int b){
		Edge e = new Edge(a,b);
		if(!nodes.containsKey(a)){
			nodes.put(a, new Node(a));
		}
		if(!nodes.containsKey(b)){
			nodes.put(b, new Node(b));
		}
		nodes.get(a).adjacencySet.add(nodes.get(b));
		nodes.get(b).adjacencySet.add(nodes.get(a));
		return this;
	}
}