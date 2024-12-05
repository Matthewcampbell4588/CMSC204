import java.util.ArrayList;


public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	
	private TreeNode<String> root;
	
	
	public MorseCodeTree() {
		root=null;
		buildTree();
	}
	
	
	
	public TreeNode<String> getRoot() {

		return root;
	}
	

	
	public void setRoot(TreeNode<String> newNode) {
	
		root=new TreeNode<String>(newNode);
		
	}

	
	
	public void insert(String code, String letter) {
		if(root==null){
			root=new TreeNode<String>(letter);
		}
		else {
			addNode(root,code,letter);
		}
	}

	
	public void addNode(TreeNode<String> root, String code, String letter) {

		if(code.length()>1) {
			if(code.charAt(0)=='-') {
				addNode(root.rightChild,code.substring(1),letter);
			}
			else {
				addNode(root.leftChild,code.substring(1),letter);
			}
		}
		else {
			if(code.equals(".")) {
				root.leftChild=new TreeNode<String>(letter);
			}
			else {
				root.rightChild=new TreeNode<String>(letter);
			}
		}
		
	}

	
	public String fetch(String code) {
		String result=fetchNode(root,code);
		return result;
	}

	
	public String fetchNode(TreeNode<String> root, String code) {
		String letter="";
		if(code.length()>1) {
			if(code.charAt(0)=='.') {
				letter+=fetchNode(root.leftChild,code.substring(1));
			}
			else {
				letter+=fetchNode(root.rightChild,code.substring(1));
			}
			
		}
		else {
			if(code.equals(".")) {
				letter+=root.leftChild.getData();
				return letter;
			}
			else { 
				letter+=root.rightChild.getData();
				return letter;
			}
		}
		return letter;
	}

	
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	public void buildTree() {
		
		
		
		insert("","");
		
		insert(".","e"); insert("-","t");
		
		
		insert("..","i"); insert(".-","a");insert("-.","n"); insert("--","m");
		
		
		insert("...","s"); insert("..-","u");insert(".-.","r"); insert(".--","w");insert("-..","d"); insert("-.-","k");insert("--.","g"); insert("---","o");
		
		
		insert("....","h"); insert("...-","v");insert("..-.","f"); insert(".-..","l");insert(".--.","p"); insert(".---","j");insert("-...","b"); insert("-..-","x");insert("-.-.","c"); insert("-.--","y");insert("--..","z"); insert("--.-","q");
		
		
		
	}

	
	public ArrayList<String> toArrayList() {
		ArrayList<String>list=new ArrayList<String>();
		LNRoutputTraversal(root,list);
		return list;
	}

	
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root.leftChild==null && root.rightChild==null) {
			list.add(root.getData()+" ");
		}
		else {
			if(root.leftChild!=null) {
				LNRoutputTraversal(root.leftChild,list);
				list.add(root.getData()+" ");
			}
			if(root.rightChild!=null) {
				LNRoutputTraversal(root.rightChild,list);
			}
		}
		
	}
	
}