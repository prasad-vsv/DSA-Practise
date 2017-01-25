package trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SwapNodes {
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
		int size = getNextInt();
		Map<Integer,Node> map = new HashMap<>();
		Node root = new Node(1);
		map.put(1,root);
		Node parent = root;
		//form the tree
		for(int i =1; i<= size ;++i){
			int[] indices = getNextIntegerArray();
			Node left =null; 
			if(indices[0] != -1){
				left = new Node(indices[0]);
			}
			
			Node right=null; 
			if(indices[1] != -1){
				right = new Node(indices[1]);
			}
			
			parent = map.get(i);
			if(left != null){
				map.put(left.data, left);
				parent.left = left;
			}
			if(right != null){
				map.put(right.data, right);
				parent.right = right;
			}	
		}
//		printInorder(root);
		int swaps = getNextInt();
		for(int i=0; i<swaps;++i){
			int depth = getNextInt();
			swap(map.get(1),map,depth);
			printInorder(map.get(1));
			System.out.println();
		}
	}
	
	private static void swap(Node root, Map<Integer, Node> map, int depth) {
		Deque<Node> q = new ArrayDeque<>();
		//first figure out all the nodes to be swapped
		Deque<Node> qq = new ArrayDeque<>();
		int __depth = depth;
		int _depth = 0;
		int height = calculateHeightOfTree(root,0);
		while(depth <= height){
			while(_depth != depth){
				if(_depth ==0){
					q.add(map.get(1));
					++_depth;
					continue;
				}
				//get all the children of the nodes in q and add them to qq after increasing the level by 1
				for(Node n: q){
					if(n.left != null){
						qq.add(n.left);
					}
					if(n.right != null){
						qq.add(n.right);
					}
					
					q.remove();
				}
				++_depth;
				q.addAll(qq);
				qq.removeAll(q);
			}
			
			//the contents of q are the ones whose childen should be swapped.
//			System.out.println(q);
			for(Node n : q){
				if(n == null){
					continue;
				}
				Node temp = n.left;
				n.left = n.right;
				n.right = temp;
			}
			depth = depth + __depth;
		}
		
	}

	private static int calculateHeightOfTree(Node root,int _height) {
		int height = 0;
		if(root == null){
			return _height;
		}
		
		return max(calculateHeightOfTree(root.left, _height+1), calculateHeightOfTree(root.right, _height+1));
	}

	public static void printInorder(Node root){
		if(root == null){
			return;
		}
		printInorder(root.left);
		System.out.print(root.data + " ");
		printInorder(root.right);
	}
}


class Node{
	int data;
	Node left;
	Node right;
	Node(int _data){
		this.data = _data;
	}
	Node(int _data, Node n1, Node n2){
		this.data = _data;
		left = n1;
		right = n2;
	}
	@Override
	public String toString() {
		return data+"";
	}
	
}