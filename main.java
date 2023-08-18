/*This program takes user input for how many nodes in the tree they would like then creates an empty binary tree with that many nodes. The user is then given a menu and chooses which operation they would like to do on the tree(insert, delete, or search). Then after the operation is chosen and performed, the splay tree is then given back to the user in preorder traversal*/
import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
       SplayTree userSplayTree = new SplayTree();
       Scanner scnr = new Scanner(System.in);
       boolean quit = false; //for the loop to know when to exit 
       System.out.println("How many nodes would you like there to initially be in the tree?");
	   int initialTreeSize = scnr.nextInt();
	   for (int i = 0; i < initialTreeSize; i++) {
		   userSplayTree.insert(0);
	   }
       while(!quit){
    	   //Serves as the main menu for which the user chooses which splay tree operations they want to use
           System.out.println("Which splay tree operation would you like to use?\n1. Insert\n2. Delete\n3. Search\n4. Quit\n");
           try {
        	   switch (scnr.nextInt()) {
        	   case 1: //insert
        		   System.out.println("Which node would you like to insert?");
        		   userSplayTree.insert(scnr.nextInt()); //user inputs which node they would like added into the splay tree
        		   userSplayTree.preorderPrint(userSplayTree);
        		   break;
        	   case 2: //delete
        		   System.out.println("Which node would you like to delete?");
        		   userSplayTree.delete(scnr.nextInt()); //user inputs which node they would like deleted from the splay tree
        		   userSplayTree.preorderPrint(userSplayTree);
        		   break;                          
        	   case 3: //search
        		   System.out.println("Which node would you like to search for?");
        		   if(userSplayTree.search(scnr.nextInt())) {//if the user input is in the splay tree
        			   System.out.println("The node was found.\n");
        		   }
        		   else { //if the user input is not in the splay tree
        			   System.out.println("The node was not found.\n");
        		   }
        		   userSplayTree.preorderPrint(userSplayTree);
        		   break;
        	   case 4: //quit
        		   quit = true;
        		   userSplayTree.preorderPrint(userSplayTree);
        		   break;
        	   default :
        		   System.out.println("This is an invalid integer input. Please provide a valid integer input"); //in the case that the user inputs something that is an integer but is not valid
        		   break;   
        	   }
           }
           catch (Exception e) { //in the case that the user inputs something that is 
        	   System.out.println("This is an invalid input. Please provide a valid integer input.\n");
        	   scnr.next();
           }
       }
       scnr.close();
   }

