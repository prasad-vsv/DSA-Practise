package trees;

import java.util.*;

// This class will host all the common methods used on binary trees eg:
//traversals

public class BinaryTreeUtil {

	static Scanner s = new Scanner(System.in);

	public static void inorderRecursionPrint(Node root) {
		if (root == null) {
			return;
		}
		inorderRecursionPrint(root.left);
		System.out.print(root.data + " ");
		inorderRecursionPrint(root.right);
	}

	public static void preorderRecursionPrint(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preorderRecursionPrint(root.left);
		preorderRecursionPrint(root.right);
	}

	public static void postorderRecursionPrint(Node root) {
		if (root == null) {
			return;
		}
		postorderRecursionPrint(root.left);
		postorderRecursionPrint(root.right);
		System.out.print(root.data + " ");
	}

	public static void levelOrderRecusivePrint(Node root) {
		// iterative approach is preferred over this
		// the idea is to have one more function which would also take in a
		// level and reduce that level at every step
		for (int i = 0; i <= height(root); ++i) {
			levelOrderRecursivePrintWithLevel(root, i);
		}
	}

	private static void levelOrderRecursivePrintWithLevel(Node root, int level) {
		if (root == null) {
			return;
		}
		if (level == 1) {
			System.out.print(root.data);
			return;
		}
		levelOrderRecursivePrintWithLevel(root.left, level - 1);
		levelOrderRecursivePrintWithLevel(root.right, level - 1);
	}

	public static int height(Node root) {
		// this could be translated into tail recursion as well
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(height(root.left), height(root.right));
	}

	public static void levelOrderIterativePrint(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			Node n = q.poll();
			System.out.print(n.data + " ");
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
		}
	}

	public static void levelOrderSpiralIterativePrint(Node root) {
		Node dummy = new Node(Integer.MAX_VALUE);
		Queue<Node> q = new LinkedList<>();
		// base cases
		q.add(root);
		q.add(dummy);
		boolean reverse = true;
		Stack<Node> temp = new Stack();
		// the idea is that, just like in the case of level order traversal,
		// we store dummy to differentiate different level.
		// whenever we reach a dummy, we flip the reverse boolean, which
		// would indicate if or not, we should display the elements in the
		// same order
		while (!q.isEmpty()) {

			Node n = q.poll();
			if (n.equals(dummy)) {
				continue;
			}
			if (reverse) {
				temp.push(n);
			} else {
				System.out.print(n.data + " ");
			}
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}

			if (q.peek().equals(dummy)) {
				// we have reached the end of the current level and so if we are
				// reversing the current level, we need to pop elements and
				// display them
				if (reverse) {
					while (!temp.isEmpty()) {
						System.out.print(temp.pop() + " ");
					}
				}
				reverse = !reverse;
				q.add(dummy);
			}
		}
	}

	public static List<Node> getNodesAtNthLevel(Node root, int levelRequired) {
		// do level order traversal, at the end of every level, add a delimiter
		// to say that the current level has ended.
		// have a level variable that tracks the current level. have an array to
		// insert all elements at the required level.
		List<Node> nodesAtNthLevel = new ArrayList<>();
		if (root == null) {
			return nodesAtNthLevel; // with nothing in it.
		}
		Node dummy = new Node(Integer.MAX_VALUE);
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(dummy);
		int level = 1;

		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.equals(dummy)) {
				++level;
				continue;
			}
			if (level == levelRequired) { // this would stop processing after
											// the required level
				nodesAtNthLevel.add(n);
				continue;
			}
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
			if (q.peek() == dummy) { // when the elements at the current element
										// end, the next element in the queue
										// would be dummmy and so we should
										// check for it and then add dummy athe
										// end stating that the children of the
										// current element are all appended.
				q.add(dummy);
			}
		}
		return nodesAtNthLevel;
	}

	public static List<Node> getNodesAtKDistanceFromRoot(Node root, int k) {
		return getNodesAtNthLevel(root, k + 1);
	}

	public static int getMaximumWidth(Node root) {
		// the idea is to do level order traversal and at every level, check the
		// number of elements in it and see which level has most and return that
		// value;

		Node current = root;
		Node dummy = new Node(Integer.MAX_VALUE);
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(dummy);
		int max = Integer.MIN_VALUE;
		int temp = 0;
		while (!q.isEmpty()) {
			current = q.poll();
			if (current.equals(dummy)) {
				continue;
			}
			++temp;
			if (current.left != null) {
				q.add(current.left);
			}
			if (current.right != null) {
				q.add(current.right);
			}
			if (q.peek().equals(dummy)) {
				max = Math.max(max, temp);
				temp = 0;
				q.add(dummy);
			}
		}
		return max;
	}

	public static int size(Node root) {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.left) + size(root.right);
	}

	public static boolean areIdentical(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if ((root1 == null && root2 != null)
				|| (root1 != null && root2 == null)) {
			return false;
		}
		if (root1.data != root2.data) {
			return false;
		}
		return areIdentical(root1.left, root2.left)
				&& areIdentical(root1.right, root2.right);
	}

	public static void delete(Node root) {
		// recursively take out all the references
		if (root == null) {
			return;
		}
		// post order traversal
		delete(root.left);
		delete(root.right);
		root.left = null;
		root.right = null;
		// root = null doesnt make any difference as it is anyways a method
		// local variable
	}

	public static Node mirror(Node root) {
		if (root == null) {
			return null;
		}
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;

		mirror(root.left);
		mirror(root.right);
		return root;
	}

	public static void preorderIterativePrint(Node root) {
		Node current = root;
		Stack<Node> s = new Stack<>();
		s.push(current);
		while (!s.isEmpty()) {
			current = s.pop();
			System.out.print(current.data);
			if (current.right != null) {
				s.push(current.right);
			}
			if (current.left != null) {
				s.push(current.left);
			}
		}
	}

	public static void inorderIterativePrint(Node root) {
		// Approach 1: This requires an extra boolean space
		System.out.println("Inorder Iterative Approach 1");
		if (root == null) {
			return;
		}
		Stack<Node> s = new Stack<>();
		s.push(root);
		while (!s.isEmpty()) {
			Node n = s.pop();
			// if the node is already visited then print its data other wise
			// stack up its right element, then this element and then its left
			// element
			if (n.visited) {
				System.out.print(n.data + " ");
				continue;
			}
			n.visited = true;
			if (n.right != null) {
				s.push(n.right); // so that it comes out last
			}
			s.push(n);
			if (n.left != null) {
				s.push(n.left);
			}
		}

		// Approach 2: This doesn't need visited flag
		System.out.println("Approach 2: ");
		Stack<Node> ss = new Stack<>();
		Node current = root;
		// putting the left boundary in the stack first
		while (current != null) {
			ss.push(current);
			current = current.left;
		}

		while (!ss.isEmpty()) {
			current = ss.pop(); // pop the first element and print it. This will
								// start with left most node in the tree
			System.out.print(current.data + " ");
			current = current.right; // if there is a right sub tree, we
										// initialize current with its right
										// node and then push left boundary of
										// that sub tree
			while (current != null) {
				ss.push(current);
				current = current.left;
			}
		}

		// Approach 3
		System.out.println("Approach 3:");
		Stack<Node> sss = new Stack<>();
		current = root;
		do {
			while (current != null) {
				sss.push(current);
				current = current.left;
			}
			current = sss.pop();
			System.out.print(current.data + " ");
			current = current.right;
		} while (!sss.isEmpty() || current != null);

	}

	public static int countLeafNodes(Node root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		return countLeafNodes(root.left) + countLeafNodes(root.right);
	}

	public static void printRootToLeafPaths(Node root) {
		printRootToLeafPathsWithResidue(root.left, root.data + "");
		printRootToLeafPathsWithResidue(root.right, root.data + "");
	}

	private static void printRootToLeafPathsWithResidue(Node root,
			String residue) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			System.out.println(residue + " " + root.data);
			return;
		}
		printRootToLeafPathsWithResidue(root.left, residue + " " + root.data);
		printRootToLeafPathsWithResidue(root.right, residue + " " + root.data);
	}

	public static Node convertToCircularDoublyLinkedList(Node root) {
		// the idea is to go with iterative in order traversal and instead of
		// printing the data, attach the list node.

		Node head = null;
		Node tail = null;
		Node n = root;
		Stack<Node> s = new Stack<>();

		while (n != null) {
			s.push(n);
			n = n.left;
		}

		while (!s.isEmpty()) {
			n = s.pop();
			if (head == null && tail == null) {
				head = n;
				tail = n;
			} else {
				tail.right = n;
				n.left = tail;
				tail = n;
			}
			n = n.right;
			while (n != null) {
				s.push(n);
				n = n.left;
			}
		}
		tail.right = head;
		head.left = tail;
		return head;
	}

	public static Node formTree(String inorder, String preorder) {
		// the idea is that the first element in preorder traversal will give us
		// the root and that would divide inorder traversal equally into left
		// and right subtrees
		// now we have to divide the preorder string into left and right parts,
		// using the logic that, the first element in the right part of inorder
		// that occurs in preorder is where it's right part starts
		if("".equals(inorder) ||"".equals(preorder)){
			return null;
		}
		if(inorder.length() == 1 && preorder.length() ==1){
			//that means that the recursion has narrowed down to one element. so simply return that element
			return new Node(Integer.parseInt(inorder));
		}
		String s = preorder.substring(0, 1);
		Node root = new Node(Integer.parseInt(s));
		int rootIndex = inorder.indexOf(s);
		String leftInorder = inorder.substring(0, rootIndex);
		String rightInorder = inorder.substring(rootIndex + 1);

		// caching characters in rightInorder for easier retrieval
		Set<Character> set = new HashSet<>();
		for (char c : rightInorder.toCharArray()) {
			set.add(c);
		}

		// now go through preorder string and look at each character, if it
		// exists in the set. The first one which does is the root of the right
		// sub tree
		int rightRootPreorderIndex = Integer.MIN_VALUE;
		for(int i=0; i<preorder.length(); ++i){
			if(set.contains(preorder.charAt(i))){
				rightRootPreorderIndex = i;
				break;
			}
		}
		//if rightRootPreorderIndex is not initialized, that means that there is no right part in the preorder
		String leftPreorder = preorder.substring(1,rightRootPreorderIndex);
		String rightPreorder = preorder.substring(rightRootPreorderIndex);
		
		root.left = formTree(leftInorder,leftPreorder);
		root.right = formTree(rightInorder, rightPreorder);
		return root;
	}
}
