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
		if ("".equals(inorder) || "".equals(preorder)) {
			return null;
		}
		if (inorder.length() == 1 && preorder.length() == 1) {
			// that means that the recursion has narrowed down to one element.
			// so simply return that element
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
		for (int i = 0; i < preorder.length(); ++i) {
			if (set.contains(preorder.charAt(i))) {
				rightRootPreorderIndex = i;
				break;
			}
		}
		// if rightRootPreorderIndex is not initialized, that means that there
		// is no right part in the preorder
		String leftPreorder = null;
		String rightPreorder = null;
		if (rightRootPreorderIndex < preorder.length()) {
			leftPreorder = preorder.substring(1, rightRootPreorderIndex);
			rightPreorder = preorder.substring(rightRootPreorderIndex);
		}
		if (!"".equals(leftPreorder)) {
			root.left = formTree(leftInorder, leftPreorder);
		}
		if (!"".equals(rightPreorder)) {
			root.right = formTree(rightInorder, rightPreorder);
		}

		// root.left = formTree(leftInorder, leftPreorder);
		// root.right = formTree(rightInorder, rightPreorder);
		return root;
	}

	public static int diameterWithoutCacheing(Node root) {
		// PITFALL: usually the intuitive idea is that we return
		// height(root.left) + 1 + height(root.right) but that is wrong
		// the idea is that the left sub tree may be very large and right
		// subtree may be vey small and so the maximum diameter can occur with
		// both the leaves from left subtree itself or viceversa

		// The height is calculated at every level again and again, this can
		// instead be cached for each level while calculating for the root level
		// and be reused. that would reduce the time complexity from O(n^2) to
		// O(n) or we can calculate the height while calculating the diameter
		// itslef

		// Approach 1: without caching
		int diameterGoingThroughRoot = 1 + height(root.left)
				+ height(root.right);
		int diameterOnlyFromLeftRoot = diameter(root.left);
		int diameterOnlyFromRightRoot = diameter(root.right);

		return Math.max(
				Math.max(diameterOnlyFromLeftRoot, diameterOnlyFromRightRoot),
				diameterGoingThroughRoot);

		// Approach 2: with caching. instead of calling height method, have one
		// more method called cachedHeight
		// that method should cache the results of heights of smalltrees using
		// DP memoization and then use this cache to return values immediately
		// for smaller subtrees
	}

	public static boolean checkChildrenSumProperty(Node root) {
		// if the root is leaf, return true
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		int leftsum = 0;
		if (root.left != null) {
			leftsum = root.left.data;
		}
		int rightsum = 0;
		if (root.right != null) {
			rightsum += root.right.data;
		}
		boolean check = (root.data == leftsum + rightsum);

		return check && checkChildrenSumProperty(root.left)
				&& checkChildrenSumProperty(root.right);
	}

	public static void convertToChildrenSumPropertyHoldingTree(Node root) {
		// go fixing from bottom up and when you have to, go down from a point
		// to increment the values accordingly.
		// if root return
		if (root.left == null && root.right == null) {
			return;
		}

		while (true) {
			if (checkChildrenSumProperty(root)) {
				break;
			}
			int leftsum = 0;
			if (root.left != null) {
				leftsum = root.left.data;
			}

			int rightsum = 0;
			if (root.right != null) {
				rightsum = root.right.data;
			}
			if (root.data == (leftsum + rightsum)) {
				convertToChildrenSumPropertyHoldingTree(root.left);
				convertToChildrenSumPropertyHoldingTree(root.right);
			} else {
				// if root.data is less, simply increment it and return. the
				// parent would then have to adjust accordingly
				if (root.data < (leftsum + rightsum)) {
					root.data = leftsum + rightsum;
				}
				// else pick left child, increment its value and then call this
				// function recursively with left child
				else {
					int diff = Math.abs(root.data - leftsum - rightsum);
					root.left.data += diff;
					convertToChildrenSumPropertyHoldingTree(root.left);
				}
			}
		}
	}

	public static void boundaryTraversalPrint(Node root) { // in anti clockwise
															// direction
		// this comes in 3 parts. print left boundary, print leaves, print right
		// boundary in reverse direction
		// print left boundary. Else if we want to print in clock wise
		// direction, print right boundary as is, print leaves and then print
		// left boundary in reverse direction.
		Node current = root;
		while (current != null) {
			// dont print the leaf as it would be covered in the print leaves
			// section
			if (current.left == null && current.right == null) {
				current = null;
				continue;
			}
			System.out.print(current.data + " ");
			current = current.left;
		}

		// now print the leaves from left to right. best way is to go inorder as
		// level order may print leaves in between first
		current = root;
		Stack<Node> stack = new Stack<>();
		while (current != null) {
			stack.add(current);
			current = current.left;
		}

		while (!stack.isEmpty()) {
			current = stack.pop();
			// instead of directly printing it, check if it is a leaf and only
			// then print it.
			if (current.left == null && current.right == null) {
				System.out.print(current.data + " ");
			}
			current = current.right;
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
		}

		// print right boundary in reverse direction
		Stack<Node> s = new Stack<>();
		current = root.right; // root is already covered when printing the left
								// boundary
		while (current != null) {
			// add only when it is not a leaf, as the leaf would've been covered
			// in the print leaves section
			if (current.left == null && current.right == null) {
				current = null;
				continue;
			}
			s.add(current);
			current = current.right;
		}
		// now print the stack in reverse direction
		while (!s.isEmpty()) {
			System.out.print(s.pop().data + " ");
		}

	}

	public static int convertToSumTree(Node root) {
		if (root == null) {
			return 0;
		}
		int left = convertToSumTree(root.left);
		int right = convertToSumTree(root.right);
		int temp = root.data;
		root.data = left + right;
		return temp + left + right;
	}

	public static boolean isBalanced(Node root) {
		// the idea is to find if height(root.left)== height(root.right) and if
		// left tree and right tree are balanced
		// an optimization is that instead of calculating height for every level
		// again, we could calculate them all in one go.
		return isBalanced(root, new Height(0));
		// another way is to cache the heights of all nodes using memoization
		// and then use that instead of recalculating heights at every level
	}

	private static boolean isBalanced(Node root, Height h) {
		if (root == null) {
			h = new Height(0);
			return true;
		}
		h.h++;
		Height leftHeight = new Height(0);
		Height rightHeight = new Height(0);
		boolean left = isBalanced(root.left, leftHeight);
		boolean right = isBalanced(root.right, rightHeight);

		h.h = 1 + Math.max(leftHeight.h, rightHeight.h);

		return left && right && (Math.abs(rightHeight.h - leftHeight.h) <= 1);
	}

	public boolean isRootToLeafPathEqualTON(Node root, int n) {
		// is root null?
		if (root == null) {
			if (n == 0) {
				return true;
			}
			return false;
		}
		// is root a leaf node?
		if (root.right == null && root.right == null) {
			if (root.data != n) {
				return false;
			}
			return true;
		}

		return isRootToLeafPathEqualTON(root.left, n - root.data)
				|| isRootToLeafPathEqualTON(root.right, n - root.data);
	}

	private static boolean isLeaf(Node root) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		return false;
	}

	public static boolean checkSumTree(Node root) {
		// PITFALL: Usually we would calculate sum(root.left), sum(root.right)
		// and then check if their sum == root.data and then recursively check
		// for root.left and root.right. But this is O(n^2) and can be reduced
		// easily because, if we assume that the left subtree is sumTree and
		// right subtree is sumTree(which should be the case anyways for the
		// overall tree to be sumTree), then the sum of the current subtree =
		// 2*root.data; if the subtree just has a leaf, then the value of the
		// leaf is it's sum.
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		int leftSum = 0;

		if (root.left != null) {// there should be a check for isLeaf for this
								// left element, if so, we should directly
								// consider this element's value
			if (isLeaf(root.left)) {
				leftSum = root.left.data;
			} else {
				leftSum = 2 * root.left.data;
			}
		}
		int rightSum = 0;

		if (root.right != null) {
			if (isLeaf(root.right)) {
				rightSum = root.right.data;
			} else {
				rightSum = 2 * root.right.data;
			}
		}
		// check if rightSum + leftSum == root.data
		if (root.data != (leftSum + rightSum)) {
			return false;
		}
		// recursively check for left and right sub trees
		return checkSumTree(root.left) && checkSumTree(root.right);
	}

	public static int getLevelOfKey(Node root, int key) {
		if (root == null) {
			return 0;
		}
		if (root.data == key) {
			return 1;
		}
		// go with level order traversal and track level in a key.
		int level = 1;
		Node cur = root;
		Node dummy = Node.dummy;
		Queue<Node> q = new LinkedList<>();
		q.add(cur);
		q.add(dummy);
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur.equals(dummy)) {
				++level;
				continue;
			}
			if (cur.data == key) {
				// data found. return level
				break;
			}
			if (cur.left != null) {
				q.add(cur.left);
			}
			if (cur.right != null) {
				q.add(cur.right);
			}
			if (q.peek().equals(dummy)) {
				q.add(dummy);
			}
		}
		return level;
	}

	public static Node createDoubleTree(Node root) {
		// we use post order traversal because, that ways we handle children
		// first and then the parent
		if (root == null) {
			return null;
		}
		createDoubleTree(root.left);
		createDoubleTree(root.right);
		Node temp = root.left;
		Node duplicate = new Node(root.data);
		duplicate.left = temp;
		root.left = duplicate;
		return root;
	}

	public static boolean isFoldable(Node root) {
		// the idea is to mirror the left subtree and then check if it's
		// structure matches right subtree
		if (root == null) {
			return true;
		}
		if ((root.left == null && root.right != null)
				|| (root.left != null && root.right == null)) {
			return false;
		}
		root.left = mirror(root.left);
		// now check if the structure of left subtree matches that of right
		// subtree

		return checkIfSimilar(root.left, root.right);
	}

	private static boolean checkIfSimilar(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if ((root1 == null && root2 != null)
				|| (root1 != null && root2 == null)) {
			return false;
		}
		return checkIfSimilar(root1.left, root2.left)
				&& checkIfSimilar(root1.right, root2.right);
	}

	public static boolean printAncestors(Node root, int key) {
		// the idea is to go with post order recursion and return true if the
		// data is found at a child level so that parents can then start
		// printing their values
		if (root == null) {
			return false;
		}
		if (root.data == key) {
			// key found; don't go to it's children level, dont print the
			// current element. just return true
			return true;

		}
		if (printAncestors(root.left, key) || printAncestors(root.right, key)) {
			System.out.println(root.data + " ");
			return true;
		}
		return false;
	}

	public static boolean checkIfOneTreeIsASubtreeOfAnother(Node root1,
			Node root2, boolean started) {
		// We assume that root2 is smaller, Actually we should check size of
		// both and then decide
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (started) {
			if (root1.data == root2.data) {
				// this would be the start of the second subtree

				return checkIfOneTreeIsASubtreeOfAnother(root1.left,
						root2.left, true)
						&& checkIfOneTreeIsASubtreeOfAnother(root1.right,
								root2.right, true);
			}
			return false;
		}
		if (root1.data == root2.data) {
			return checkIfOneTreeIsASubtreeOfAnother(root1.left, root2.left,
					true)
					&& checkIfOneTreeIsASubtreeOfAnother(root1.right,
							root2.right, true);
		}
		return checkIfOneTreeIsASubtreeOfAnother(root1.left, root2, false)
				|| checkIfOneTreeIsASubtreeOfAnother(root1.right, root2, false);

	}

	public static void connectSiblings(Node root) {
		// INCOMPLETE
		if (root == null) {
			return;
		}
		root.left.parent = root.right.parent = root;
		if (root.parent == null) {
			// root is the actual root
			// so simply continue;
			connectSiblings(root.left);
			connectSiblings(root.right);
		}
		// else first set the sibling of the current element
		// it can be parent's right child. If the parent's right child is null
		// or itself, then it should go to parent's sibling's left child or
		// right child if left child is null
		Node parentRight = root.parent.right;
		if (parentRight != null) {
			root.rightSibling = parentRight;
		} else {
			Node parentSibling = root.parent.rightSibling;
			if (parentSibling != null) {
				// Node parentSiblingLeft
			}
		}
	}

	public static boolean removeNodesThatDontLieInPathOfSumK(Node root, int k,
			int currentSum) {
		// http://www.geeksforgeeks.org/remove-all-nodes-which-lie-on-a-path-having-sum-less-than-k/

		// the idea is to check recursively if any one of the children lead to a
		// complete path of sum k
		// if no one does, return false, which means at a parent level, we
		// remove this node link
		// if one of them does and other dont, then remove just the path that
		// doesnt and then return true'
		if (currentSum > k) {
			return true;
		}
		if (root == null) {
			return currentSum >= k;

		}
		if (root.left == null && root.right == null) {
			currentSum += root.data;
			if (currentSum >= k) {
				return true;
			} else {
				// look if children can lead to path and then return true if
				// either one of them does, otherwise return false
				boolean left = removeNodesThatDontLieInPathOfSumK(root.left, k,
						currentSum);
				boolean right = removeNodesThatDontLieInPathOfSumK(root.right,
						k, currentSum);
				if (left && right) {
					// both lead to the correct sum paths
					return true;
				} else if (left || right) {
					// one of them does
					if (!left) {
						System.out.println("deleting:" + root.left.data);
						delete(root.left);
						root.left = null;
					} else if (!right) {
						System.out.println("deleting:" + root.right.data);
						delete(root.right);
						root.right = null;
					}

					return true;
				} else {
					System.out.println("deleting:" + root.left.data);
					System.out.println("deleting:" + root.right.data);
					// no one does
					delete(root.left);
					delete(root.right);
					root.left = null;
					root.right = null;
					return false;
				}
			}
		}
		return false;// the code wouldn't come here.
	}

	public static void printLeftView(Node root) {
		// The idea is to go with iterative level order traversal and at every
		// level, print the first node.

		if (root == null) {
			return;
		}
		Node dummy = Node.dummy;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		queue.add(dummy);
		System.out.print(root.data + " ");
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			if (temp == null) {
				return; // not necessary
			}
			if (temp.equals(dummy)) {
				Node next = queue.peek();
				if (next != null && !next.equals(dummy)) {
					System.out.print(next.data + " ");

				}
				continue;
			}
			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp.right);
			}
			Node next = queue.peek();
			if (next != null && next.equals(dummy)) {
				queue.add(dummy);
			}
		}
	}

	public static boolean checkIfLeavesAreAtSameLevel(Node root) {
		if (root == null) {
			return true;
		}
		Node dummy = Node.dummy;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(dummy);

		// figure out the left most leaf level
		int leftLeafLevel = -1;
		Node temp = root;
		while (temp != null) {
			temp = temp.left;
			++leftLeafLevel;
		}

		// base case
		if (root.left == null && root.right == null) {
			return true;
		}
		int curlevel = 0;
		// now go with level order traversal and check if all nodes at left leaf
		// level or not.
		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.equals(dummy)) {
				++curlevel;
				continue;
			}

			// if the node is a leaf and is not a leftLeafLevel, then return
			// false;
			if ((cur.left == null && cur.right == null)
					&& curlevel != leftLeafLevel) {
				return false;
			}

			if (cur.left != null) {
				q.add(cur.left);
			}
			if (cur.right != null) {
				q.add(cur.right);
			}
			Node next = q.peek();
			if (next.equals(dummy)) {
				q.add(dummy);
			}
		}

		return true;
	}

	public static void diagonalSum(Node root) {
		Map<Integer, Integer> map = new TreeMap<>();
		_diagonalSum(root, map, 0);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
	}

	private static void _diagonalSum(Node root, Map<Integer, Integer> map,
			int lefts) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(lefts)) {
			map.put(lefts, 0);
		}
		map.put(lefts, map.get(lefts) + root.data); // adding root.data to the
													// corresponding lefts
		_diagonalSum(root.left, map, lefts + 1);
		_diagonalSum(root.right, map, lefts);
	}

	public static int diameter(Node root) {
		// the diameter or width can be calculated 2 ways.
		// either in the subtree that is rooted at the current node i.e, the
		// path goes through the current node
		// or the right subtree can totally contain the diameter or the left
		// subtree can totally contain the diameter

		// going through current node => height of left sub tree + height of
		// right sub tree + 1 is the diameter. but rather than calculating
		// height for each node, we can actually reuse the calculated results
		// from smaller subtrees
		Height h = new Height(0);
		return _diameter(root,h);
	}
	
	private static int _diameter(Node root, Height h){
		if(root == null){
			h.h = 0;
			return 0;
		}
		if(root.left == null && root.right == null){
			h.h = 1;
			return 1;
		}
		//create two height objects, one for left and one for right and then find max of it and set it to h
		Height l = new Height(0);
		Height r = new Height(0);
		
		//this would populate l and r with heights of left and right subtrees respectively
		//lMax would have the max diameter contained totally within left subtree rMax for right subtree
		int lMax = _diameter(root.left,l);
		int rMax = _diameter(root.right,r);
		
		//set the height property of the subtree rooted at parent
		h.h = Math.max(l.h,r.h)+1;
		
		//now the actual diameter calculation
		return Math.max((1+l.h+r.h), Math.max(lMax,rMax));
		
	}

}
