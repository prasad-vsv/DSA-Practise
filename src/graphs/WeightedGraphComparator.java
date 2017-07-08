package graphs;

import java.util.Comparator;

public class WeightedGraphComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		int w1 = o1.weight;
		int w2 = o2.weight;
		if(w1 == w2){
			return 0;
		}
		if(w1>w2){
			return 1;
		}
		return -1;
	}
}
