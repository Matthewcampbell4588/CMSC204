//Matthew Campbell
public class TreeNode<T> {
	private T data;
	protected TreeNode<T>leftChild;
	protected TreeNode<T>rightChild;
	
	 TreeNode(T dataNode) {
		data=dataNode;
		leftChild=null;
		rightChild=null;
	}
	

	public TreeNode(TreeNode<T> node) {
		this(node.leftChild,node.rightChild,node.getData());
	}
	
	public TreeNode(TreeNode<T> left,TreeNode<T> right,T info) {		
		data= info;
		leftChild=new TreeNode<T>(left);
		rightChild=new TreeNode<T>(right);
	}
	
	
	public T getData() {
		return data;
	}

}