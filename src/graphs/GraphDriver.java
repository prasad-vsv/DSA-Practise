package graphs;

import java.util.*;

public class GraphDriver {


	public static void main(String[] args) {
		GraphUtils.bfs(createGraph(), 2);
		
        GraphUtils.dfs(createGraph(),2);
	}
	
	public static Graph createGraph(){
		Graph g = new Graph();
		g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        return g;	
	}
}
