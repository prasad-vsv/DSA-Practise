package Incomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BalancedForest {
	static Scanner s = new Scanner(System.in);

	public static int getNextInt() {
		return Integer.parseInt(s.nextLine());
	}

	public static long getNextLong() {
		return Long.parseLong(s.nextLine());
	}

	public static int[] getNextIntegerArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		int[] input = new int[tokens.length];
		for (int i = 0; i < tokens.length; ++i) {
			input[i] = Integer.parseInt(tokens[i]);
		}
		return input;
	}

	public static long[] getNextLongArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		long[] input = new long[tokens.length];
		for (int i = 0; i < tokens.length; ++i) {
			input[i] = Long.parseLong(tokens[i]);
		}
		return input;
	}

	public static String getNextLine() {
		return s.nextLine();
	}

	public static String[] getNextStringArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		return tokens;
	}

	public static void printArray(Object[] objs) {
		for (Object o : objs) {
			System.out.println(o);
		}
	}

	public static int max(int i, int j) {
		if (i > j) {
			return i;
		}
		return j;
	}

	public static String max(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		}
		return s2;
	}

	public static int max(int i, int j, int k) {
		if (i > j) {
			if (i > k) {
				return i;
			} else {
				return k;
			}
		}
		if (j > k) {
			return j;
		} else {
			return k;
		}

	}

	public static String max(String s1, String s2, String s3) {
		int i = s1.length(), j = s2.length(), k = s3.length();
		if (i > j) {
			if (i > k) {
				return s1;
			} else {
				return s3;
			}
		}
		if (j > k) {
			return s2;
		} else {
			return s3;
		}
	}

	public static int min(int i, int j) {
		if (i < j) {
			return i;
		}
		return j;
	}

	public static String min(String s1, String s2) {
		if (s1.length() < s2.length()) {
			return s1;
		}
		return s2;
	}

	public static int min(int i, int j, int k) {
		if (i < j) {
			if (i < k) {
				return i;
			} else {
				return k;
			}
		}
		if (j < k) {
			return j;
		} else {
			return k;
		}

	}

	public static String min(String s1, String s2, String s3) {
		int i = s1.length(), j = s2.length(), k = s3.length();
		if (i < j) {
			if (i < k) {
				return s1;
			} else {
				return s3;
			}
		}
		if (j < k) {
			return s2;
		} else {
			return s3;
		}
	}

	public static void main(String[] args) {
		int tests = getNextInt();
		for(int i=0; i<tests; ++i){
			int num = getNextInt();
			int[] costs = getNextIntegerArray();
			Map<Integer,ForestNode> map = new HashMap<>();
			//create children
			/*for(int k=0; k<num;++k){
				map.put(k+1, new ForestNode(k+1, costs[k]));
			}*/
//			map.get(1).parent = null;
			for(int j=0; j< num -1;++j){
				//store the edges
				int[] edge = getNextIntegerArray();
				
				ForestNode parent = map.get(edge[0]);
				ForestNode child = map.get(edge[1]);
				
				if(parent == null && child == null){
					parent = new ForestNode(edge[0], costs[edge[0]-1]);
					child = new ForestNode(edge[1], costs[edge[1]-1]);
					map.put(parent.data, parent);
					map.put(child.data, child);
				}
				if(parent == null){
					//swap parent to child
					ForestNode temp = parent;
					parent = child;
					child = temp;
					if(child == null){
						child = new ForestNode(edge[0],costs[edge[0]-1]);
						map.put(child.data, child);
					}
					
				}
				if(child == null){
					child = new ForestNode(edge[1],costs[edge[1]-1]);
					map.put(child.data, child);
				}
				child.parent = parent;
				parent.children.add(child);
				
				//ensure that first on	e is lesser than second one
				/*if(edge[0]< edge[1]){
					map.get(edge[0]).children.add(map.get(edge[1]));
					map.get(edge[1]).parent = map.get(edge[0]);
				}else{
					map.get(edge[1]).children.add(map.get(edge[0]));
					map.get(edge[0]).parent = map.get(edge[1]);
				}*/
			}
//			System.out.println(map);
			calculateBranchValues(map);
//			System.out.println(map);
			System.out.println(solve(map,num));
		}
	}

	private static int solve(Map<Integer, ForestNode> map, int num) {
		//select each edge (parent child combo) and for that edge look for another edge such that if we cut both the edges, two forests should have the same sum.
		// to check sum of a forest, always go till the parent is null which would then give the root and look for branchValue in that root..
		//when breaking an edge, subtract the value of the child of the edge from the ancestors till the root.
		int min = Integer.MAX_VALUE;
		for(Integer i :map.keySet()){
			ForestNode parent = map.get(i);
			List<ForestNode> children = parent.children;
			//select edge to each child
			for(ForestNode n: children){
				//assume this is the correct child that we are breaking the edge with
				int branchValue = n.branchValue;
				//subtract this value from all its ancestors
				ForestNode temp = n;
				while(temp.parent!= null){
					temp.parent.branchValue -= branchValue;
					temp = temp.parent;
				}
				
				//now pick up another edge other than this one and repeat the process
				for(Integer _i : map.keySet()){
					ForestNode _parent = map.get(i);
					List<ForestNode> _children = parent.children;
					for(ForestNode _n: children){
						//check if it turned out to be the same branch
						if(n.equals(_n)){
							continue;
						}
						//otherwise imagine these are the two edges to break and proceed
						
						int _branchValue = _n.branchValue;
						//subtract this value from all its ancestors
						ForestNode _temp = _n;	
						while(_temp.parent!= null){
							_temp.parent.branchValue -= _branchValue;
							_temp = _temp.parent;
						}
						//there are 3 sections now. one is the one that root holds, which is the oldest ancestor of n and _n. second is the forest starting from n and the third is the forest starting from _N
						//if two of these 3 have same branch value, then the difference of their bv to the third one is the answer. if nothing is possible, then return -1;
						int a = n.branchValue;
						int b = _n.branchValue;
						
						//_temp will now be holding the root's address and the while loop above breaks when _temp.parent == null which means that it is the root.
						int c = _temp.branchValue;
						
						if(a==b){
							if(a>c){
								if((a-c) < min){
									min = (a-c);
								}
							}
						}
						else if (a == c){
							if(a>b){
								if((a-b) < min){
									min = (a-b);
								}
							}
						}else if (b==c){
							if(b > a){
								if((b-a) < min){
									min = (b-a);
								}
							}
						}
						//if solution is not found, take out the effects
						_temp = _n;
						while(_temp.parent!= null){
							_temp.parent.branchValue += _branchValue;
							_temp = _temp.parent;
						}
					}
				}
				
				temp = n;
				while(temp.parent!= null){
					temp.parent.branchValue += branchValue;
					temp = temp.parent;
				}
			}
			
		}if(min == Integer.MAX_VALUE){
			return -1;
		}
		return min;
	}

	private static void calculateBranchValues(Map<Integer, ForestNode> map) {
		for(int i: map.keySet()){
			sum(map.get(i));
		}
	}

	private static int sum(ForestNode forestNode) {
		if(forestNode ==null){
			return 0;
		}
		if(forestNode.branchValue != -1){
			return forestNode.branchValue;
		}
		int sum = forestNode.cost;
		for(ForestNode n : forestNode.children){
			sum += sum(n);
		}
		forestNode.branchValue = sum;
		return sum;
	}
}

class ForestNode{
	int data;
	ForestNode parent;
	int branchValue = -1;
	int cost = -1;
	
	List<ForestNode> children = new ArrayList<>();
	
	public ForestNode(int data, int costs){
		this.data = data;
		this.cost = costs;
	}

	@Override
	public String toString() {
		return "data:" + data + " branchValue is " + branchValue+ " and children are" + children;
	}

	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof ForestNode)){
			return false;
		}
		ForestNode arg = (ForestNode) arg0;
		if((this.data != arg.data)){
			return false;
		}
		
		return true;
	}
	
	
	
}
