package graphs;

import java.util.*;

public class GraphUtils {
	public static void bfs(Graph g, int a) {
		// /it is similar to iterative level order traversal of trees. the only
		// change is that we have to check if the node is visited already, as
		// there may be cycles in graphs.

		Set<Node> set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		while (true) {
			q.add(g.nodes.get(a));

			while (!q.isEmpty()) {
				Node n = q.poll();
				set.add(n);
				System.out.print(n.data + " ");
				// add n's connections
				Set<Edge> edges = n.adjacencySet;
				for (Edge e : edges) {
					Node to = e.getDestination(n); // from will always be n
					if (!set.contains(to)) {
						q.add(to);
					}
				}
			}
			// till now only the connected nodes of 2 will be traversed. if
			// there is
			// another island that is not reachable from 2, that won't be
			// covered.
			// so we must check if all the nodes are visited and then restart
			// the
			// process
			if (set.size() == g.nodes.size()) {
				break;
			} else {
				for (int n : g.nodes.keySet()) {
					Node node = g.nodes.get(n);
					if (!set.contains(node)) {
						// that means this node is not yet visited. so use this
						// as starting point
						a = node.data;
						break;
					}
				}
			}
		}
		System.out.println();

	}

	public static void dfs(Graph g, int a) {
		// a is the starting point
		Stack<Node> s = new Stack<>();
		Set<Node> set = new HashSet<>();
		s.push(g.nodes.get(a));
		while (true) {
			while (!s.isEmpty()) {
				Node n = s.pop();
				System.out.print(n.data + " ");
				set.add(n);
				Set<Edge> edges = n.adjacencySet;
				for (Edge e : edges) {
					Node to = e.getDestination(n);
					if (!set.contains(to)) {
						s.push(to);
					}
				}
			}
			// till now only the connected nodes of 2 will be traversed. if
			// there is
			// another island that is not reachable from 2, that won't be
			// covered.
			// so we must check if all the nodes are visited and then
			// restart
			// the
			// process
			if (set.size() == g.nodes.size()) {
				break;
			} else {
				for (int x : g.nodes.keySet()) {
					Node node = g.nodes.get(x);
					if (!set.contains(node)) {
						// that means this node is not yet visited. so use
						// this as starting point
						a = node.data;
						break;
					}
				}
			}
		}
		System.out.println();

	}

	public static void topologicalSort(Graph g) {
		// we go through the graph nodes one by one, check if it is visited and
		// if not, then process it
		Set<Node> visited = new HashSet<>();
		Stack<Node> stack = new Stack<>();
		for (Map.Entry<Integer, Node> entry : g.nodes.entrySet()) {
			Node n = entry.getValue();
			if (!visited.contains(n)) {
				_topologicalSort(n, visited, stack);
			}
		}

		// one by one pop the stack and print the nodes
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().data + " ");
		}
		System.out.println();
	}

	private static void _topologicalSort(Node n, Set<Node> visited,
			Stack<Node> stack) {
		if (visited.contains(n)) {
			return;
		}
		visited.add(n);
		// first process all the children recursively and then add the parent at
		// the top of the stack so that the parent dependency is loaded first
		// before loading the child dependencies
		Set<Edge> edges = n.adjacencySet;
		for (Edge e : edges) {
			Node to = e.getDestination(n);
			_topologicalSort(to, visited, stack);
		}
		stack.push(n);
	}

	public static void minimumSpanningtreeKruskal(Graph g) {
		// have 2 sets, one to track visited nodes and one for unvisited nodes
		Set<Node> visited = new HashSet<>();
		Set<Node> unvisited = new HashSet<>();
		// init unvisited with all nodes in the graph and track all the edges as
		// well
		Set<Edge> edgesConsidered = new HashSet<>();
		TreeSet<Edge> edges = new TreeSet<>(new WeightedGraphComparator());
		for (Map.Entry<Integer, Node> entry : g.nodes.entrySet()) {
			Node n = entry.getValue();
			unvisited.add(n);
			// add all the edges to edges set which will keep it in sorted order
			for (Edge e : n.adjacencySet) {
				edges.add(e);
			}
		}
		int treeWieght = 0;
		while (!edges.isEmpty()) {
			if (unvisited.isEmpty()) {
				// that means that all nodes are already visited. no need to
				// further process edges
				break;
			}
			Edge e = edges.pollFirst();
			// now check if both the nodes connected by this edge are already
			// visited. This means that there is a cycle in the graph.
			if (visited.contains(e.to) && visited.contains(e.from)) {
				continue; // the node is not considered
			}
			// now the edge is considered. so add to treeWeight
			treeWieght += e.weight;
			edgesConsidered.add(e);
			// now both are not there. so check if one is there and one is not
			// there
			if (visited.contains(e.to) || visited.contains(e.from)) {
				if (visited.contains(e.to)) {
					unvisited.remove(e.from);
					visited.add(e.from);
				} else {
					unvisited.remove(e.to);
					visited.remove(e.to);
				}
			}
			// that means neither of nodes are already visited. so add them both
			// to visited set
			unvisited.remove(e.to);
			visited.add(e.to);
			unvisited.remove(e.from);
			visited.add(e.from);
		}
		System.out.println("treeWieght:" + treeWieght);
	}

	public static void minimumSpanningTreePrim(Graph g) {
		Set<Node> visited = new HashSet<Node>();
		Set<Node> unvisited = new HashSet<Node>();
		PriorityQueue<Edge> edges = new PriorityQueue<>(8,
				new WeightedGraphComparator());
		Set<Edge> edgesConsidered = new HashSet<>();
		Node start = null;
		// add all the edges to unvisited
		for (Map.Entry<Integer, Node> entry : g.nodes.entrySet()) {
			Node n = entry.getValue();
			if (start == null) {
				start = n;
				edges.addAll(n.adjacencySet);
				visited.add(n); // base condition
				continue;
			}
			unvisited.add(n);
		}
		int sum = 0;
		// consider all edges from visited nodes to unvisited nodes and pick the
		// one with least weight. repeat this till there are no unvisited nodes
		// left
		while (!unvisited.isEmpty()) {
			Edge e = edges.poll();
			if (visited.contains(e.to) && visited.contains(e.from)) {
				continue;
			}
			if (visited.contains(e.to) || visited.contains(e.from)) {// it has
																		// to
																		// contain
				Node n = visited.contains(e.to) ? e.getDestination(e.to) : e
						.getDestination(e.from);
				edgesConsidered.add(e);
				sum += e.weight;
				visited.add(n);
				unvisited.remove(n);
				edges.addAll(n.adjacencySet);
			}
		}
		System.out.println("tree weight : " + sum);

	}

	public static boolean doesCycleExist(Graph g) {
		// there are multiple ways. we coould use MST Kruskal's algorithm and if
		// we see an edge that has both to and from in the visited set, then
		// there is a cycle
		// we could do DFS and at everystep we could see, if there is a neighbor
		// other than the node that it came from, that is already in the visited
		// set. if it is, then there is a cycle. This approach works only for
		// Directed graphs. For undirected graphs, the node from which we
		// reached the current node will already be in visited set and will
		// again come as it's neighbor. we need to track parent to use this
		// method in the case of undirected graphs
		// we could go with MST Prim's algorithm and see all the outgoing edges
		// from visited set and see if one of those edges is pointing back to
		// one of the visited nodes.
		// lets follow the DFS way now.

		Set<Node> visited = new HashSet<>();
		for (Map.Entry<Integer, Node> entry : g.nodes.entrySet()) {
			Node n = entry.getValue();
			if (!visited.contains(n)) {
				boolean cycle = _doesCycleExist(n, null, visited);
				if (cycle) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean _doesCycleExist(Node start, Node from,
			Set<Node> visited) {
		visited.add(start);
		boolean result = false;
		for (Edge e : start.adjacencySet) {
			Node end = e.getDestination(start);
			if(end.equals(from)){
				continue;
			}
			if (visited.contains(end)) {
				return true;
			} else {
				boolean temp = _doesCycleExist(end, start, visited);
				if (temp) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static int numberOfIslands(Graph g){
		int count = 0;
		Set<Node> visited = new HashSet<>();
		for(Map.Entry<Integer, Node> entry : g.nodes.entrySet()){
			Node n = entry.getValue();
			if(!visited.contains(n)){
				++count;
				_dfsIsland(n,visited);
				System.out.println();
			}
		}
		return count;
	}
	
	private static void _dfsIsland(Node n, Set<Node> visited){
		if(visited.contains(n)){
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(n);
		while(!stack.isEmpty()){
			Node start = stack.pop();
			visited.add(start);
			System.out.print(start.data+" ");
			for(Edge e: start.adjacencySet){
				Node end = e.getDestination(start);
				if(!visited.contains(end)){
					stack.push(end);
				}
			}
		}
	}
	
	public static void dfsRecursive(Graph g){
		Set<Node> visited = new HashSet<>();
		for(Map.Entry<Integer,Node> entry: g.nodes.entrySet()){
			Node n = entry.getValue();
			if(!visited.contains(n)){
				_dfsRecursive(n,visited);
			}
		}
	}
	
	private static void _dfsRecursive(Node n, Set<Node> visited){
		visited.add(n);
		System.out.println(n.data + " ");
		for(Edge e: n.adjacencySet){
			Node to = e.getDestination(n);
			if(!visited.contains(to)){
				_dfsRecursive(to,visited);
			}
			
		}
	}
}
