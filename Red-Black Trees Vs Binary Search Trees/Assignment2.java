package A2;
// Hassan Hanino 10146297
import java.util.ArrayList;
import java.util.Random;
/* I confirm that this submission is my own work and is consistent with the Queen's regulations
 * on Academic Integrity */
 
public class Assignment2 {

	public static void main (String []args) throws IllegalData{
      System.out.println("This program first calculates the average number of rotations to balance a red-black tree (RBT). \nIt then compares the depth of a binary search tree (BST) to a red-black tree (RBT).\n");
      System.out.println("For 500 iterations, we compare the average number of rotations needed to balance a RB tree at intervals of 50 nodes inserted in the RBT.");
		System.out.println("Checkpoint -------------Average Number of Rotations");
		double[] array = new double[10];
		int arrayCount = 0;
		Random randommInt = new Random();
		for (int i = 1; i < 501; i++){
			int number1 = randommInt.nextInt(501) + 1;
			RedBlackTreeVertex rooter = new RedBlackTreeVertex(number1);
			RedBlackSearchTree tree = new RedBlackSearchTree(rooter);
			for (int k=1; k <= 500; k++){
				int number2 = randommInt.nextInt(501) + 1;
				tree.Insert(number2, tree);
				//System.out.println(tree.rotation);
				if (k%50 ==0 ){
					//System.out.println(i+"     ------------------ " + (tree.rotation));
					array[arrayCount] += tree.rotation;
					arrayCount++;
					tree.rotation = 0;
				}
			}
			arrayCount = 0;
		}
		arrayCount = 0; // To print checkpoints, start at 0 for arrayCount
		while (arrayCount < 10){
			System.out.println((50*arrayCount+50)+" \t\t\t\t\t\t\t"+array[arrayCount]/500);
			arrayCount++;
		}
		
		// Experiment 2 ----------------------------------------------------
		experiment2();
	}
	
	public static void experiment2()throws IllegalData{
		System.out.println();
      System.out.println("For 500 iterations, we compare the depth of a BST to a RB tree with 20, 100, 500, and 2500 nodes.\nR represents the ratio of the depth of a BST tree to a RB tree.\nThe values represent what percentage of the 500 iterations R fell in the described range.\n");
		System.out.println("\tR<0.5\t\t\t\t0.5<=R<0.75\t\t\t0.75<=R<=1.25\t\t\t1.25<R<=1.5\t\t\tR>1.5 ");
		int[] ratioArray = new int[5];
		Random randommInt = new Random();
		for (int N=20; N <=2500; N = N*5){
			for (int k = 0; k < 500; k++){
				int numberN = randommInt.nextInt(N) + 1;
				RedBlackTreeVertex rooter = new RedBlackTreeVertex(numberN);
				RedBlackSearchTree tree = new RedBlackSearchTree(rooter);
				BinaryTreeVertex BSTrooter = new BinaryTreeVertex(numberN);
				BinarySearchTree BSTtree = new BinarySearchTree(BSTrooter);
					for (int n=0; n < N; n++){
						int numberM = randommInt.nextInt(N) + 1;
						tree.Insert(numberM,tree);
						BSTtree.InsertHelper(numberM,BSTtree.root);
					}
				double RBdepth = tree.Total_Depth(tree.root);
				double BSTdepth = BSTtree.Total_Depth(BSTtree.root);
				double R = BSTdepth / RBdepth;
				if (R < 0.5)
					ratioArray[0]++;
				else if (R < 0.75 && R >= 0.5)
					ratioArray[1]++;
				else if(0.75 <= R && R <=1.25)
					ratioArray[2]++;
				else if(1.25 < R && R <=1.5)
					ratioArray[3]++;
				else
					ratioArray[4]++;
			}
	
			int arrayCounter = 0;
			System.out.print(N+":\t");
			while (arrayCounter <= 4){
				System.out.print( String.format("%.2f",((ratioArray[arrayCounter]/500.00)*100))+ "\t\t\t\t");
				arrayCounter++;
			}
			ratioArray = new int[5];
			System.out.println();
		}
	}
	
}

