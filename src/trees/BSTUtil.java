package trees;

import java.util.Stack;

public class BSTUtil {
	public static Node search(Node root, int key){
		if(root == null || key == root.data){
			return root;
		}
		if(root.data< key){
			return search(root.right, key);
		}
		return search(root.left, key);
	}
	
	public static Node insert(Node root, int key){
		if(root == null){
			//parent was a leaff to which this key needs to be added
			root = new Node(key);
			return root;
		}
		if(root.data<key){
			//insert in right sub tree
			root.right = insert(root.right, key);
		}else{
			root.left = insert(root.left, key);
		}
		
		return root;
	}
	
	public static Node delete(Node root, int key){
		//search for the key first and then see if it is a leaf or a parent with one child or a parent with two children
		if(root == null){
			return root;
		}
		if(root.data == key){
			if(root.left == null && root.right == null){
				root= null;
				return root;
			}
			else if(root.left == null || root.right == null){
				//one child case. attach the child to its grand parent
				if(root.left == null){
					Node temp = root.right;
					root = null;
					return temp;
				}
				else{
					Node temp = root.left;
					root = null;
					return temp;
				}
			}
			else{
				//parent has both the children
				//find the inorder successor. that will bbe right subtree's left most element
				Node right = root.right;
				Node successor = null;
				while(right!= null){
					successor = right;
					right = right.left;					
				}
				root.data = successor.data;
				delete(root.right, successor.data);
				return root;
			}
		}
		//current node is not the one that we are looking for
		//traverse left or right sub tree
		if(root.data < key){
			root.right = delete(root.right,key);
		}else{
			root.left = delete(root.left,key);
		}
		return root;
	}
	
	public static void printInorder(Node root){
		if(root == null){
			return;
		}
		printInorder(root.left);
		System.out.print(root.data + " " );
		printInorder(root.right);
	}
	
	public static void printMinValue(Node root){
		//the left most leaf will be the minimum value in a BST
		if(root == null){
			return ;
		}
		while(root.left != null ){
			root = root.left;
		}
		System.out.println(root.data);
	}
	
	public static void inorderSuccessorAndPredecessor(Node root, int key){
		//if the left subtree exists, then right most child of left sub tree
		//else one of the ancestors, which is the left child of its parent
		
		_inorderSuccessorAndPredecessor(root, key, root, root);
	}
	
	public static void _inorderSuccessorAndPredecessor(Node root, int key, Node pre , Node suc){
		//predecessor should be changed when calling right sub tree
		//successor should be updated to current node when calling left sub tree
		if(root == null){
			System.out.println("successor: " + suc);
			System.out.println("predecessor: " + pre);
			return;
		}
		if(root.data == key){
			if(root.right != null){
				//successor exists as left most node in the right sub tree
				Node temp = root.right;
				while(temp.left != null){
					temp = temp.left;
				}
				System.out.println("successor:"+temp.data);
			}else{
				System.out.println("successor:" + suc);
			}
			if(root.left != null){
				Node temp = root.left;
				while(temp.right!= null)
					temp = temp.right;
				System.out.println("predecessor:"+temp.data);
			}else{
				System.out.println("predecessor:"+pre);
			}
		}
		if(root.data > key){
			_inorderSuccessorAndPredecessor(root.left, key, pre, root);
		}else{
			_inorderSuccessorAndPredecessor(root.right, key, root, suc);
		}
	}
	
	public static boolean isBST(Node root){
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		return isBST(root, max, min);
	}
	
	public static boolean isBST(Node root, int upper, int lower){
		if(root == null){
			return true;
		}
		if(root.data > upper || root.data <lower){
			return false;
		}
		
		boolean left = isBST(root.left, root.data, lower);
		boolean right = isBST(root.right, upper, root.data);
		return left && right;
	}
	
	public static int lca(Node root, int a, int b){
		//the first element in between a and b is the one that is lca
		if(root == null){
			return -1; //which means none of a and b exist
		}
		if(root.data < a && root.data <b){
			//both exist in the right subtree
			return lca(root.right, a, b);
		}
		if(root.data >a && root.data >b){
			//both exist in left subtree
			return lca(root.left, a, b);
		}
		
		return root.data;
	}
	
	public static int kthSmallest(Node root, int k){
		//the idea is to do iterative inorder traversal and keep a count of elements processed.
		if(root == null){
			return -1;
		}
		Stack<Node> stack = new Stack<>();
		Node start = root;
		while(start != null){
			stack.push(start);
			start = start.left;
		}
		int count = 0;
		while(!stack.isEmpty()){
			Node cur = stack.pop();
			++count;
			if(count == k){
				return cur.data;
			}
			cur=cur.right;
			while(cur!= null ){
				stack.push(cur);
				cur = cur.left;
			}
		}
		return -1;
	}
	
	public static void printNodesInGivenRange(Node root, int min, int max){
		if(root == null){
			return ;
		}
		if(root.data > min && root.data > max){
			printNodesInGivenRange(root.left, min, max);
		}
		else if(root.data<min && root.data<max){
			printNodesInGivenRange(root.right, min, max);
		}
		//that means that the root is within the range
		else{
			System.out.print(root.data+ " ");
			printNodesInGivenRange(root.left, min, max);
			printNodesInGivenRange(root.right, min, max);
		}
	}
}
