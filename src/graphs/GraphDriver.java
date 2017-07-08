package graphs;

import java.util.*;

public class GraphDriver {


	public static void main(String[] args) {
		GraphUtils.bfs(createGraph(), 0);
		
        GraphUtils.dfs(createGraph(),0);
        
        GraphUtils.topologicalSort(createDirectedGraph());
        
        GraphUtils.minimumSpanningtreeKruskal(createWeightedGraph());
        GraphUtils.minimumSpanningTreePrim(createWeightedGraph());
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
	
	public static Graph createWeightedGraph(){
		Graph g = new Graph(false);
		g.addEdge(0,1,10);
		g.addEdge(0,2, 6);
		g.addEdge(1,3,15);
		g.addEdge(0,3,5);
		g.addEdge(2,3, 4);
		return g;
	}
}
