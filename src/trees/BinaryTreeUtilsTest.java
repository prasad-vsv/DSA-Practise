package trees;

import java.util.*;

public class BinaryTreeUtilsTest {
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
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		//testing inorder traversal
		//expected answer is 4251637
		System.out.println("inorderRecursionPrint:");
		BinaryTreeUtil.inorderRecursionPrint(root);
		System.out.println("inorderIterativePrint");
		BinaryTreeUtil.inorderIterativePrint(root);
		System.out.println();
		
		//testing preorder traversal
		//expected answer is 1245367
		System.out.println("preorderRecursionPrint:");
		BinaryTreeUtil.preorderRecursionPrint(root);
		System.out.println("preorderIterativePrint");
		BinaryTreeUtil.preorderIterativePrint(root);
		
		System.out.println();
		
		
		//testing postorder traversal
		//expected result is 4526731
		System.out.println("postorderRecursionPrint:");
		BinaryTreeUtil.postorderRecursionPrint(root);
		
		System.out.println();
		
		//testing levelorder traversal using recursion
		//expected result is 1234567
		System.out.println("levelOrderIterativePrint");
		BinaryTreeUtil.levelOrderIterativePrint(root);
		System.out.println("levelOrderRecusivePrint");
		BinaryTreeUtil.levelOrderRecusivePrint(root);
		System.out.println();
		
		//get nodes at 3rd level.expected answer is 4567
		
		System.out.println("getNodesAtNthLevel:" + BinaryTreeUtil.getNodesAtNthLevel(root, 3));
		System.out.println("getNodesAtKDistanceFromRoot:" + BinaryTreeUtil.getNodesAtKDistanceFromRoot(root, 2));
		
		System.out.println("leaf nodes count: " + BinaryTreeUtil.countLeafNodes(root));
		
		BinaryTreeUtil.printRootToLeafPaths(root);
		
		System.out.println("levelOrderSpiralIterativePrint:");
		BinaryTreeUtil.levelOrderSpiralIterativePrint(root);
		
		System.out.println("getMaximumWidth:" + BinaryTreeUtil.getMaximumWidth(root));
		
		System.out.println("convertToCircularDoubleLinkedList: " + BinaryTreeUtil.convertToCircularDoublyLinkedList(root));
		
		System.out.println("formTree: " + BinaryTreeUtil.formTree("4251637","1245367"));
	}
}
