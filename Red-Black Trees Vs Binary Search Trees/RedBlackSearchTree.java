package A2;
import java.util.*;
//Hassan Hanino 10146297 
/*I confirm that this submission is my own work and is consistent with the Queen's regulations
on Academic Integrity */

public class RedBlackSearchTree {
	
	public RedBlackTreeVertex root;
	public int rotation = 0;
	
	
	public RedBlackSearchTree(RedBlackTreeVertex root){
		if (root instanceof RedBlackTreeVertex || root == null)
			this.root=root;
	}
	
	public void Insert(int data, RedBlackSearchTree tree) throws IllegalData{
			tree.root = InsertHelper(data, tree.root);
			tree.root.colour = 0; 
	}
	
	
	public RedBlackTreeVertex InsertHelper(int data, RedBlackTreeVertex tree)throws IllegalData{
		if(tree == null)
			return new RedBlackTreeVertex(data);
		if(tree.is_a_leaf==true){
			tree = new RedBlackTreeVertex(data);
			return tree;
		}
		if (tree.value == data)
			return tree; // No duplicates!
		else if(tree.value > data){
			tree.left = InsertHelper(data, tree.left);
			if (tree.colour==1)
				return tree;
			else{
				if(tree.left.colour==1){
					if(tree.left.left.colour==1)
						return RedBlackTreeFixLeftLeft(tree); // Not good. Two consecutive red nodes.
					else if(tree.left.right.colour==1)
						return RedBlackTreeFixLeftRight(tree); // Not good. Two consecutive red nodes.
					else
						return tree; // No issues with red red nodes.
				}
				else
					return tree; // No red node on the left.
				
			}
		}
		else if(tree.value < data){
			tree.right = InsertHelper(data, tree.right);
			if (tree.colour==1)
				return tree;
			else{
				if (tree.right.colour == 1){
					if (tree.right.left.colour==1)// Not good. Two consecutive red nodes.
						return RedBlackTreeFixRightLeft(tree);
					else if (tree.right.right.colour==1) // Not good. Two consecutive red nodes.
						return RedBlackTreeFixRightRight(tree);
					else
						return tree; // No issues
				}
				else
					return tree; // No red node on the right.
			}
		}
		return tree; // We will never reach this, but java won't leave me alone.
	}
	
	
	public RedBlackTreeVertex RedBlackTreeFixRightRight(RedBlackTreeVertex tree){
		RedBlackTreeVertex rightChild= tree.right;
		RedBlackTreeVertex leftChild = tree.left;
		if (leftChild.colour==1){
			rightChild.colour=0;
			leftChild.colour=0;
			tree.colour=1;
			return tree;
		}
		else{
			rotation++;
			tree.right=rightChild.left;
			rightChild.left=tree;
			rightChild.colour=0;
			tree.colour=1;
			return rightChild;
		}
	}
	
	public RedBlackTreeVertex RedBlackTreeFixRightLeft(RedBlackTreeVertex tree){
		RedBlackTreeVertex rightChild = tree.right;
		RedBlackTreeVertex leftChild = tree.left;
		if (leftChild.colour==1){
			rightChild.colour = 0;
			leftChild.colour=0;
			tree.colour=1;
			return tree;
		}
		else{
			rotation = rotation + 2;
			RedBlackTreeVertex rightChildLeftChild = rightChild.left;
			rightChild.left = rightChildLeftChild.right;
			tree.right = rightChildLeftChild.left;
			rightChildLeftChild.left = tree;
			rightChildLeftChild.right = rightChild;
			rightChildLeftChild.colour = 0;
			tree.colour = 1;
			return rightChildLeftChild;
		}
	}
	
	public RedBlackTreeVertex RedBlackTreeFixLeftLeft(RedBlackTreeVertex tree){
		RedBlackTreeVertex leftChild= tree.left;
		RedBlackTreeVertex rightChild = tree.right;
		if (rightChild.colour==1){
			leftChild.colour=0;
			rightChild.colour=0;
			tree.colour=1;
			return tree;
		}
		else{
			rotation++;
			tree.left=leftChild.right;
			leftChild.right=tree;
			leftChild.colour=0;
			tree.colour=1;
			return leftChild;
		}
	}
	
	public RedBlackTreeVertex RedBlackTreeFixLeftRight(RedBlackTreeVertex tree){
		RedBlackTreeVertex leftChild = tree.left;
		RedBlackTreeVertex rightChild = tree.right;
		if (rightChild.colour==1){
			leftChild.colour = 0;
			rightChild.colour=0;
			tree.colour=1;
			return tree;
		}
		else{
			rotation = rotation +2;
			RedBlackTreeVertex leftChildRightChild = leftChild.right;
			leftChild.right = leftChildRightChild.left;
			tree.left = leftChildRightChild.right;
			leftChildRightChild.right = tree;
			leftChildRightChild.left = leftChild;
			leftChildRightChild.colour = 0;
			tree.colour = 1;
			return leftChildRightChild;
		}
	}
	
	
	public ArrayList<Integer> SearchPath(int target) throws IllegalData{
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		return RedBlackSearchPathHelper(target, pathList, this.root);
	}
	
	public ArrayList<Integer> RedBlackSearchPathHelper(int target, ArrayList<Integer> pathList, RedBlackTreeVertex tree) throws IllegalData{
		if(tree.right.value==-9999 && tree.left.value==-9999 && tree.value!=target){
			pathList.add(tree.value);
			return pathList;
		}
		else if (tree.value==target){
			pathList.add(target);
			if (tree.colour==0)
				System.out.print("B");
			else
				System.out.print("R");
			return pathList;
		}
		else if(tree.value > target){
			pathList.add(tree.value);
			if (tree.colour==0)
				System.out.print("B, ");
			else
				System.out.print("R, ");
			return RedBlackSearchPathHelper(target, pathList, tree.left);
		}
		else if(tree.value < target){
			pathList.add(tree.value);
			if (tree.colour==0)
				System.out.print("B, ");
			else
				System.out.print("R, ");
			return RedBlackSearchPathHelper(target, pathList, tree.right);
		}
		else
			return pathList; // Will never reach this point. Java won't leave me alone though.
	}
	
	public int Total_Depth(RedBlackTreeVertex tree){
		return Total_DepthHelper(tree, 1);
	}
	
	public int Total_DepthHelper(RedBlackTreeVertex tree, int tracker){
		if (tree.right.value == -9999 && tree.left.value == -9999)
			return tracker;
		else if (tree.right.value == -9999 && tree.left.value != -9999 )
			return Total_DepthHelper(tree.left, (tracker+1)) + tracker;
		else if (tree.right.value != -9999 && tree.left.value == -9999)
			return Total_DepthHelper(tree.right, (tracker+1)) + tracker;
		else
			return (Total_DepthHelper(tree.right, (tracker+1))) + (Total_DepthHelper(tree.left, (tracker+1))) + tracker;
	}
	
}

