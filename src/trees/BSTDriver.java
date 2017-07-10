package trees;

public class BSTDriver {
	
	public static void main(String[] args) {
		BSTUtil.printInorder(createBST());
		System.out.println();
		BSTUtil.printInorder(BSTUtil.delete(createBST(), 50));
		System.out.println();
		BSTUtil.printMinValue(createBST());
		System.out.println(BSTUtil.kthSmallest(createBST(),4));
		BSTUtil.printNodesInGivenRange(createBST(), 30, 50);
		System.out.println();
		BSTUtil.inorderSuccessorAndPredecessor(createBST(), 35);
	}
	
	public static Node createBST(){
		Node root = new Node(50);
		BSTUtil.insert(root,30 );
		BSTUtil.insert(root, 20);
		BSTUtil.insert(root, 40);
		BSTUtil.insert(root, 70);
		BSTUtil.insert(root, 60);
		BSTUtil.insert(root, 80);
		return root;
	}
}
