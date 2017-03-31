package trees;

import java.util.*;

public class LevelOrderTraversal {
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
		n a = new n(1);
		a.left = new n(2);
		a.right = new n(3);
		a.left.left = new n(4);
		a.left.right = new n(5);
		a.right.left = new n(6);
		a.right.right = new n(7);
		lot(a);
	}

	private static void lot(n a) {
		Queue<n> q = new LinkedList();
		q.add(a);
		q.add(dummy);
		int count =0;
		while(!q.isEmpty() ){
			n b = q.poll();
			if(!b.equals(dummy)){
				System.out.println(b.value);
			}
			
			if(b.equals(dummy)){
				//previous level is completed. so enqueue dummy again
				System.out.println("level " + (++count)+ " completed");
				if(q.size()!=0){
					q.add(dummy); //else this can be the last dummy element that was added after reading last level. if we add again, it may lead to an infinite loop
				}
				continue;
			}
			if(b.left != null){
				q.add(b.left);
			}
			if(b.right != null){
				q.add(b.right);
			}
		}
	}
}

class n{
	int value;
	n left;
	n right;
	
	public n(int value){
		this.value = value;
		this.left = this.right = null;
	}
	
	public boolean equals(Object a){
		if(!(a instanceof n)){
			return false;
		}
		n b = (n) a;
		if(this.value != b.value){
			return false;
		}
		return true;
	}
}
