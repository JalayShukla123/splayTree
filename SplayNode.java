public class SplayNode {    
    SplayNode left, right, parent;
    int element;
    public SplayNode() {
    }          
    public SplayNode(int nodeValue) {
    } 
    public SplayNode(int nodeValue, SplayNode left, SplayNode right, SplayNode parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.element = nodeValue;         
    }    
}

