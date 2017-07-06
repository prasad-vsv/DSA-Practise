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
				if (set.contains(n)) {
					continue; // dont process it again
				}
				set.add(n);
				System.out.print(n.data + " ");
				Set<Node> nodes = n.adjacencySet;
				q.addAll(nodes);

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
				if (set.contains(n)) {
					// already visited. dont do any further processing
					continue;
				}

				System.out.print(n.data + " ");
				set.add(n);
				s.addAll(n.adjacencySet);
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
			System.out.println();
		}
	}
}
