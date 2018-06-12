package A2;
import java.util.*;
//Hassan Hanino 10146297 
/*I confirm that this submission is my own work and is consistent with the Queen's regulations
on Academic Integrity */

public class BinarySearchTree{

	public BinaryTreeVertex root;
	
	public BinarySearchTree(BinaryTreeVertex root){
		if (root instanceof BinaryTreeVertex || root == null)
			this.root=root;
	}
	
	
	public void Insert(int data) throws IllegalData{
			if (this.root==null)
				this.root = new BinaryTreeVertex(data);
			this.root = InsertHelper(data, this.root);
	}
	public static BinaryTreeVertex InsertHelper (int data, BinaryTreeVertex current)throws IllegalData{
		if(current.right==null && data > current.value){
			current.right = new BinaryTreeVertex(data);
			return current;
		}
		else if (current.left==null && data < current.value){
			current.left = new BinaryTreeVertex(data);
			return current;
		}
		else if (current.value == data){
			return current;
			}
		else if(current.value > data && current.left !=null)
			return InsertHelper(data, current.left);
		else
			return InsertHelper(data, current.right);
		//return current; // We will never reach this, but java won't leave me alone.
	}
	
	public ArrayList<Integer> SearchPath(int target) throws IllegalData{
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		return SearchPathHelper(target, pathList, this.root);
	}
	
	public static ArrayList<Integer> SearchPathHelper(int target, ArrayList<Integer> pathList, BinaryTreeVertex tree) throws IllegalData{
		if (tree.value==target){
			pathList.add(target);
			return pathList;
		}
		else if(tree.value > target){
			pathList.add(tree.value);
			return SearchPathHelper(target, pathList, tree.left);
		}
		else if(tree.value < target){
			pathList.add(tree.value);
			return SearchPathHelper(target, pathList, tree.right);
		}
		else
			return pathList; // Will never reach this point. Java won't leave me alone though.
	}
	
	public int Total_Depth(BinaryTreeVertex tree){
		return Total_DepthHelper(tree, 1);
	}
	
	public int Total_DepthHelper(BinaryTreeVertex tree, int tracker){
		if (tree.right == null && tree.left == null)
			return tracker;
		else if (tree.right == null && tree.left != null)
			return Total_DepthHelper(tree.left, (tracker+1)) + tracker;
		else if (tree.right != null && tree.left == null)
			return Total_DepthHelper(tree.right, (tracker+1)) + tracker;
		else
			return (Total_DepthHelper(tree.right, (tracker+1))) + (Total_DepthHelper(tree.left, (tracker+1))) + tracker;
	}
	
}
