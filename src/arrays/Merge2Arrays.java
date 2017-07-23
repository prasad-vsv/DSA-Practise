package arrays;

import java.util.*;

public class Merge2Arrays {
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
		int[] a = { 2, Integer.MAX_VALUE, 7, Integer.MAX_VALUE,
				Integer.MAX_VALUE, 10, Integer.MAX_VALUE };
		int[] b = {5,8,12,14};
		System.out.println(merge(a,b));
	}
	
	private static int[] merge(int[] a, int[] b){
		for(int i=0; i<b.length; ++i){
			if(a[i] > b[i]){
				//figure out first empty slot
				int j=i;
				while(++j<a.length){
					if(a[j] == Integer.MAX_VALUE){
						break;
					}
				}
				//now j would point to an empty slot
				//push all elements once till we fill the empty slot
				while(j>=i){
					a[j] = a[--j]; //copy previous element
				}
				//now put b[i] in a[i]
				b[i] = a[i];
			}
			else{
				//figure out if a slot is directly available so that we can place the element in there
				int j = i;
				while(++j<a.length){
					if(a[j] != Integer.MAX_VALUE && a[j]>b[i]){
						//this is the right position for b[i]. so push elements from now till we find an empty slot
						int k = j;
						while(++k <a.length){
							if(a[k] == Integer.MAX_VALUE){
								break;
							}
						}
						while(k>j){
							a[k] = a[--k];
						}
						a[j] = b[i];
						
					}
					else if(a[j] == Integer.MAX_VALUE){
						//that means that we encountered an empty slot before encountering an element that is greater than b[i]
						// so push the elements from right to left till we find an element that is greater than b[i] 
						
					}
				}
			}
		}
		return a;
	}
	
	//incomplete
	private static void merge2(int[] a, int[] b){
		//the idea is to push all the elements in a to right, creating n slots, now start merging from 0th element in b and nth element in a
		pushToLast(a);
		
	}
	
	private static void pushToLast(int[] a){
		int i = a.length-1; 
		int j = a.length-1;
		//let j be pointing to last empty slot and i be pointing to an element
		while(a[j] != Integer.MAX_VALUE){
			--j;
		}
		i=j-1;
		while(i>0 && j>0){
			if(a[i] != Integer.MAX_VALUE && a[j] == Integer.MAX_VALUE){
				a[j] = a[i];
				a[i] = Integer.MAX_VALUE;
				while(a[--j] != Integer.MAX_VALUE);//this would ultimately point j to an empty slot
				while(a[--i] == Integer.MAX_VALUE);//this would point i to non empty slot
				continue;
			}
			
		}
	}
}
  