package A2;
import java.util.*;
//Hassan Hanino 10146297 
/*I confirm that this submission is my own work and is consistent with the Queen's regulations
 on Academic Integrity */

public class BinaryTreeVertex {

	public int value;
	public BinaryTreeVertex right;
	public BinaryTreeVertex left;
	
	
	
	public BinaryTreeVertex(int data) throws IllegalData{
		setValue(data);
		setRight(null);
		setLeft(null);
	}
	
	public BinaryTreeVertex(int data, BinaryTreeVertex rightTree, BinaryTreeVertex leftTree) throws IllegalData{
		setValue(data);
		setRight(rightTree);
		setLeft(leftTree);
	}
	
	public void setValue(int data)throws IllegalData{
		if (data == (int)data)
			value = data;
		else
			throw new IllegalData("Illegal data: " + data);
	}
	
	public void setRight(BinaryTreeVertex tree) throws IllegalData{
		if (tree==null)
			this.right=null; 
		else if (tree instanceof BinaryTreeVertex)
			this.right = tree;
		else
			throw new IllegalData("Illegal tree: " + tree);
	}
	public void setLeft(BinaryTreeVertex tree) throws IllegalData{
		if (tree==null)
			this.left=null;
		else if (tree instanceof BinaryTreeVertex)
			this.left = tree;
		else
			throw new IllegalData("Illegal tree: " + tree);
	}
}
