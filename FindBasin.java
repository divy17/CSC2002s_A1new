//package ForkJoinSum;

import java.util.concurrent.ForkJoinPool;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class FindBasin {
	static long startTime = 0;
    
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
   
	static final ForkJoinPool fjPool = new ForkJoinPool();
   static final Count c1 = new Count(0);
	static String sum(float[][] arr, int col) throws InterruptedException {     
	  return fjPool.invoke(new BasinThread(arr, 0, arr.length, 0, col, c1 ));
	}

	
	public static void main(String[] args) {
    try {
     try {
       FileReader fin = new FileReader(args[0]);      
       //FileReader fin = new FileReader("med_in.txt");
       
       Scanner graphFile = new Scanner( fin );
       String line;
       line = graphFile.nextLine( );
       StringTokenizer st = new StringTokenizer( line );
       int length1 = Integer.parseInt( st.nextToken( ) );
       
       int length2 = Integer.parseInt( st.nextToken( ) );
       
       float [][] arr = new float[length1][length2];
        line = graphFile.nextLine( );
        StringTokenizer st3 = new StringTokenizer( line );
        
       for (int i=0;i<length1;i++) {
        for (int j=0;j<length2;j++) {                 
         arr[i][j] = Float.parseFloat( st3.nextToken( ) );}}


		tick();
		String sumArr = sum(arr, length2);
		float time1 = tock();
      

              try {
            File file = new File(args[1]);
            FileWriter fr = new FileWriter(file, true);
            fr.write(c1.Counter()+"\n"+sumArr+ "\n"+"Time "+ time1);
            fr.close();
            System.out.println("Successfully wrote to the file."+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
      
      
      
      
                 }
             catch( IOException e )
           { System.err.println( e ); }
           
           		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
