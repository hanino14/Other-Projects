package A2;
import java.util.*;
//Hassan Hanino 10146297 
/*I confirm that this submission is my own work and is consistent with the Queen's regulations
on Academic Integrity */

public class RedBlackTreeVertex {

	public int value;
	public RedBlackTreeVertex right;
	public RedBlackTreeVertex left;
	public int colour;
	public boolean is_a_leaf;
	
	public RedBlackTreeVertex() throws IllegalData{
		setValue(-9999); // This signifies a leaf. Can't make it null.
		setRight(null);
		setLeft(null);
		this.colour=0;
		this.is_a_leaf=true;
	}
	
	public RedBlackTreeVertex(int data) throws IllegalData{
		setValue(data);
		setRight(new RedBlackTreeVertex());
		setLeft(new RedBlackTreeVertex());
		this.colour=1;	
		this.is_a_leaf=false;
	}
	
	public RedBlackTreeVertex(int data, RedBlackTreeVertex rightTree, RedBlackTreeVertex leftTree, int colour) throws IllegalData{
		setValue(data);
		setRight(rightTree);
		setLeft(leftTree);
	}
	
	public void setLeaf(RedBlackTreeVertex tree) throws IllegalData{
		if ((tree instanceof RedBlackTreeVertex) && (tree.left == null) && (tree.right==null) && (tree.colour==0))
			tree.is_a_leaf=true;
		else if((tree instanceof RedBlackTreeVertex) && ((tree.left!=null) || (tree.right!=null) || (tree.colour==1)))
			tree.is_a_leaf=false;
		else
			throw new IllegalData("Illegal tree provided: " + tree);
	}
	public void setValue(int data)throws IllegalData{
		if (data == (int)data)
			value = data;
		else
			throw new IllegalData("Illegal data: " + data);
	}
	
	public void setRight(RedBlackTreeVertex tree) throws IllegalData{
		if (tree==null)
			this.right=null;
		else if (tree instanceof RedBlackTreeVertex)
			this.right = tree;
		else
			throw new IllegalData("Illegal tree: " + tree);
	}
	
	public void setLeft(RedBlackTreeVertex tree) throws IllegalData{
		if (tree==null)
			this.left=null;
		else if (tree instanceof RedBlackTreeVertex)
			this.left = tree;
		else
			throw new IllegalData("Illegal tree: " + tree);
	}
}
