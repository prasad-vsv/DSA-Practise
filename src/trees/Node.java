package trees;

class Node{
	int data;
	Node left = null;
	Node right = null;
	Node rightSibling = null;
	Node parent = null;
	static Node dummy = new Node(Integer.MIN_VALUE);
	boolean visited = false;
	Node(int _data){
		this.data = _data;
	}
	Node(int _data, Node n1, Node n2){
		this.data = _data;
		left = n1;
		right = n2;
	}
	
	//copy constructor
	Node(Node other){
		this.data = other.data;
		this.left = other.left;
		this.right = other.right;
		this.rightSibling = other.rightSibling;
		this.parent = other.parent;
	}
	@Override
	public String toString() {
		return "data:" + data;
	}
	
	@Override
	public boolean equals(Object n){
		if(! (n instanceof Node) ){
			return false;
		}
		Node _n = (Node) n;
		return this.data == _n.data;
	}
	
}