package A3;
import java.util.*;
import java.io.*;
/* Hassan Hanino 10146297 
I confirm that this submission is my own work and is consistent with the Queen's regulations
on Academic Integrity.
The general quadratic probing and double hashing functions were
referred from Robin Dawe's code on his site.
Please feel free to change any global variables to test the code. */
public class CISC235A3 {

	public static int  M = 2251	; //2251 and 2000
	public static int  NUMBER_OF_KEYS = 2000;
	public static double C1 = 5;//5
	public static double C2 = 3;//3
	public static double COMPARISONS=0;
	public static int[] locations = new int[M];
	
	public static int[] parseData()throws Exception{
		int[] codenameArray = new int[NUMBER_OF_KEYS];
		BufferedReader file = new BufferedReader(new FileReader("top_secret_agent_codenames_2016.txt"));
		String line = null;
		int counter = 0;
		while ((line = file.readLine()) != null) {
			int lengthOfName = line.length();
			int result = 0;
			for (int k =0; k < lengthOfName; k++)
				result = result*3 + (int)line.charAt(k);
				if (result < 0)
					result = result  * (-1);
			codenameArray[counter] = result;
			counter++;
		}
		file.close();
		return codenameArray;
	}
	
	public static void quadraticProbing(int key){
		//System.out.println("Length of List (M) = " + M + "\t\t Number of keys to store " + NUMBER_OF_KEYS);
		int hashFunction = (key%M);
		int index = 0;
		int v = hashFunction; 
		int a = v;
		while ((index < M) && (locations[a]!=0)){ // Until there are no more indices to check in the list, we will try to find a free (value at location a equals 0) location.
			index++;
			a = ((int)(v + C1*index + C2*(index*index))) % M; //Our formula to find a free index value in the array index = ((int)(hashing function + C1*i + C2*i^2) % the length of the array
			COMPARISONS++;
		}
		if (locations[a] == 0){
			locations[a] = key;
		}
		
		else
			System.out.println("The hash table is full.");
	}
	
	public static void doubleHashing(int key){
		double V = (Math.sqrt(5)-1)/2; // Honour to Donald Knuth for inventing this clever value for V. Referenced from Robin Dawe's code!
		double wholeNumber = (V*key) - (long)V;
		double remainder = wholeNumber - (long)wholeNumber;
		int multiplicationMethod = (int)(M*remainder);
		int firstHashFunction = (key%M);
		int secondHashFunction = (multiplicationMethod); // feel free to change h''(k) 
		int index = 0;
		int hashFunction = (firstHashFunction + index*secondHashFunction)%M;
		int v = hashFunction; 
		int a = v;
		while ((index < M) && (locations[a]!=0)){
			index++;
			a = (int)((firstHashFunction + index*secondHashFunction)%M);
			COMPARISONS++;
		}
		if (locations[a] == 0)
			locations[a] = key;
		else
			System.out.println("The hash table is  full.");
		}
	public static void Part2()throws Exception{
		int [] keyArray = parseData();
		System.out.println("Length of List (M) = " + M + "\t\t Number of keys to store " + NUMBER_OF_KEYS);
		for (int k = 0; k < NUMBER_OF_KEYS; k++)
			quadraticProbing(keyArray[k]);
		locations = new int[M];
		double averageComparisons = COMPARISONS/NUMBER_OF_KEYS;
		COMPARISONS = 0;
		System.out.println("The average number of comparisons to find an available index in the hash table using quadratic probing is: " + averageComparisons);
	}
	
	public static void Part3()throws Exception{
		int [] keyArray = parseData();
		for (int k = 0; k < NUMBER_OF_KEYS; k++)
			doubleHashing(keyArray[k]);
		locations = new int[M];
		double averageComparisons = COMPARISONS/NUMBER_OF_KEYS;
		COMPARISONS = 0;
		System.out.println("The average number of comparisons to find an available index in the hash table using double hashing is: " + averageComparisons);
	}
	
	public static void main(String[] args) throws Exception{
		Part2();
		Part3();
	}
}
