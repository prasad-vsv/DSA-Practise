package graphs;

import java.util.*;

public class GraphDriver {


	public static void main(String[] args) {
		GraphUtils.bfs(createGraph(), 0);
		
        GraphUtils.dfs(createGraph(),0);
	}
	
	public static Graph createGraph(){
		Graph g = new Graph(false);
		g.addEdge(1, 0);
	    g.addEdge(2, 1);
	    g.addEdge(3, 4);
	    g.addEdge(4, 0);
        return g;	
	}
	
	public static Graph createDirectedGraph(){
		Graph g = new Graph(true);
		g.addEdge(1, 0);
	    g.addEdge(2, 1);
	    g.addEdge(3, 4);
	    g.addEdge(4, 0);
        return g;	
	}
}
