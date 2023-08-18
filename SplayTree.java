public class SplayTree {
    private SplayNode root;
    public SplayTree() {
        root = null;
    }
    String prefix = ""; //for the preorder function
    boolean rootExist = false; //for the preorder function
    
    private void Splay(SplayNode nodeSplay) { //Splay function that does zag, zig, zigzig, zigzag, zagzig, and zagag
        while (nodeSplay.parent != null) {
            SplayNode Parent = nodeSplay.parent;
            SplayNode GrandParent = Parent.parent;
            if (GrandParent == null) {
                if (nodeSplay != Parent.left) { //zag
                	zag(nodeSplay, Parent);
                }
                else { //zig
                	zig(nodeSplay, Parent); 
                }
            } 
            else {
                if (nodeSplay == Parent.left) {
                    if (Parent == GrandParent.left) { //zigzig
                    	zig(Parent, GrandParent);
                    	zig(nodeSplay, Parent);
                    }
                    else { //zigzag
                    	zig(nodeSplay, nodeSplay.parent);
                    	zag(nodeSplay, nodeSplay.parent);
                    }
                }
                else { //zagzig
                    if (Parent == GrandParent.left) {
                    	zag(nodeSplay, nodeSplay.parent);
                        zig(nodeSplay, nodeSplay.parent);
                    } 
                    else { //zagzag
                    	zag(Parent, GrandParent);
                    	zag(nodeSplay, Parent);
                    }
                }
            }
        }
        root = nodeSplay;
    }
    
    public void zig(SplayNode childNode, SplayNode parentNode) { //zig operation which will rotate right and make the left child the new parent
        if (parentNode.parent != null) {
            if (parentNode == parentNode.parent.left)
            	parentNode.parent.left = childNode;
            else 
            	parentNode.parent.right = childNode;
        }
        if (childNode.right != null) {
        	childNode.right.parent = parentNode;
        }
        parentNode.left = childNode.right;
        childNode.right = parentNode;
        childNode.parent = parentNode.parent;
        parentNode.parent = childNode;
    }
    public void zag(SplayNode childNode, SplayNode parentNode) { //zag operation which will rotate left and make the right child the new parent
        if (parentNode.parent != null) {
            if (parentNode == parentNode.parent.left)
            	parentNode.parent.left = childNode;
            else
            	parentNode.parent.right = childNode;
        }
        if (childNode.left != null) {
        	childNode.left.parent = parentNode;
        }
        childNode.parent = parentNode.parent;
        parentNode.parent = childNode;
        parentNode.right = childNode.left;
        childNode.left = parentNode;
    }
    
    public void insert(int insertNodeNum) { //insert function that takes in the user integer input as a parameter and adds that as a node in the splay tree
        SplayNode treeRoot = root;
        SplayNode treeParent = null;
        while (treeRoot != null) {
        	treeParent = treeRoot;
            if (insertNodeNum > treeParent.element)
            	treeRoot = treeRoot.right;
            else
            	treeRoot = treeRoot.left;
        }
        treeRoot = new SplayNode();
        treeRoot.element = insertNodeNum;
        treeRoot.parent = treeParent;
        if (treeParent == null)
            root = treeRoot;
        else if (insertNodeNum > treeParent.element)
        	treeParent.right = treeRoot;
        else
        	treeParent.left = treeRoot;
        Splay(treeRoot);
    }
    public void delete(int deleteNodeNum) { //the version of the function that the main program accesses and it takes in the integer input of the user
    	if(search(deleteNodeNum)) {
    		SplayNode node = nodeFinder(deleteNodeNum);
        	delete(node);
    	}
    	else {
    		System.out.println("The node " + deleteNodeNum + " does not exist in the tree.");
    	}
    }
    private void delete(SplayNode nodeDelete) { //the version of the delete function that is accessed by the other delete function that does the actual deleting of the node and it takes in the actual node as the input
        if (nodeDelete == null) {
            return;
        }
        Splay(nodeDelete);
        if (nodeDelete.right != null && nodeDelete.left == null) { //if only left is null
        	nodeDelete.right.parent = null;
            root = nodeDelete.right;
        } 
        else if(nodeDelete.left !=null && nodeDelete.right == null) { //if only right is null
        	nodeDelete.left.parent = null;
            root = nodeDelete.left;
        }
        else if(nodeDelete.left != null && nodeDelete.right != null) { //if both are null
            SplayNode minimumNode = nodeDelete.left;
            while(minimumNode.right!=null) {
            	minimumNode = minimumNode.right;
            }
            minimumNode.right = nodeDelete.right;
            nodeDelete.right.parent = minimumNode;
            nodeDelete.left.parent = null;
            root = nodeDelete.left;
        }
        else {
            root = null;
        }
        nodeDelete.left = null;
        nodeDelete.right = null;
        nodeDelete.parent = null;
        nodeDelete = null;
    }
    public boolean search(int val) { //the version of the search function(boolean form) that is accessed by the main program
        return (nodeFinder(val) != null);
    }
    private SplayNode nodeFinder(int searchNodeNum) { //the version of the search function that is accessed by the other search function
   	 	SplayNode PrevNode = null;
        SplayNode currentNode = root;
        while (currentNode != null) {
       	 PrevNode = currentNode;
            if (searchNodeNum > currentNode.element) {
            	currentNode = currentNode.right;
            }
            else if (searchNodeNum < currentNode.element) {
            	currentNode = currentNode.left;
            }
            else if(searchNodeNum == currentNode.element) {
                Splay(currentNode);
                return currentNode;
            }
        }
        if(PrevNode != null) {
            Splay(PrevNode);
            return null;
        }
        return null;
    }
    public void preorder() { //the version of the preorder function that the main program accesses
        preorder(root, prefix);
    }
    
    private void preorder(SplayNode preorderNode, String position) { //the version of the preorder function that the other preorder function accesses and actually does the preordering. The parameters are the SplayNode and the position
        if (preorderNode != null) {
            String nodePosition;
            if (preorderNode.parent != null) {
                nodePosition = preorderNode == preorderNode.parent.left ? "L":"R";
            } 
            else {
                nodePosition = position.equals("") ? "RT":position;
            }
            if(preorderNode.element == 0) {
            	System.out.print(null + " ");
            	preorder(preorderNode.left, "L");
            	preorder(preorderNode.right, "R");
            }
            else {
            	System.out.print(preorderNode.element + nodePosition + " ");
            	preorder(preorderNode.left, "L");
            	preorder(preorderNode.right, "R");
            }
        }
    }
    public void preorderPrint(SplayTree userSplayTree) {
 	   System.out.print("The preorder traversal is: ");
 	   userSplayTree.preorder(); //displays the tree in a preorder traversal
        System.out.println("\n");
    }
}

