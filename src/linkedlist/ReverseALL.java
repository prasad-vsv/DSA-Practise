package linkedlist;

import java.util.*;

public class ReverseALL {
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
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		System.out.println("before reversing ");
		printLL(head);
		head = reverse(head);
		System.out.println("after reversing");
		printLL(head);
		head = reverse(head);
		
		System.out.println("after reversing nodes of 3 pairs");
		head = reverseK(head,3);
		printLL(head);
		
	}
	
	private static Node reverseK(Node head, int k){
		Node h,t;
		h = head;
		t = head;
		Node cur = head;
		Node rem = null;
		Node ret = null;
		Node retTail = null;
		while(h != null){
			int count  = 0;
			while(count <(k-1) && t.next!= null){
				t = t.next;
				++count;
			}
			rem = t == null ? null : t.next;
			t.next = null;
			//h is pointing to head of this sub chain and t is pointing to tail
			//after reversing h becomes tail and t becomes head
			reverse(h);
			if(ret == null){
				ret = t;
			}if(retTail == null){
				retTail = h;
			}else{
				retTail.next  = t;
				retTail = h;
			}
			h = rem;
			t = rem;
		}
		return ret;
	}
	
	private static Node reverse(Node head){
		Node cur = head;
		while(cur != null && cur.next!= null){
			Node next = cur.next;
			cur.next = next.next;
			next.next = head;
			head = next;
		}
		return head;
	}
	
	
	
	private static void printLL(Node head){
		Node cur = head;
		while(cur!= null){
			System.out.print(cur.data);
			cur = cur.next;
		}
		System.out.println();
	}
}

class Node{
	int data;
	Node next;
	public Node(int d){
		data = d;
		next = null;
	}
}
