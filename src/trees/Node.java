package trees;

class Node{
	int data;
	Node left = null;
	Node right = null;
	
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