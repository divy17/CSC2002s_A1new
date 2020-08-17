//package ForkJoinSum;

import java.util.concurrent.ForkJoinPool;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;

public class FindBasin {
	static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
   
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static String sum(float[][] arr) throws InterruptedException {
	  return fjPool.invoke(new BasinThread(arr, 0, arr.length, 0, arr.length ));
	}

	
	public static void main(String[] args) {
    try {
     try {
       //FileReader fin = new FileReader(args[0]);
       FileReader fin = new FileReader("test.txt");
       Scanner graphFile = new Scanner( fin );
       String line;
       line = graphFile.nextLine( );
       StringTokenizer st = new StringTokenizer( line );
       int length1 = Integer.parseInt( st.nextToken( ) );
       StringTokenizer st2 = new StringTokenizer( line );
       int length2 = Integer.parseInt( st2.nextToken( ) );
       
       float [][] arr = new float[length1][length2];
       for (int i=0;i<length1;i++) {
        line = graphFile.nextLine( );
        StringTokenizer st3 = new StringTokenizer( line );
        for (int j=0;j<length2;j++) {                 
         arr[i][j] = Float.parseFloat( st3.nextToken( ) );}}


		tick();
		String sumArr = sum(arr);
		float time = tock();
		System.out.println("Run took "+ time +" seconds");		
		System.out.println("Sum is:");
		System.out.println(sumArr);

		tick();
		sumArr = sum(arr);
		time = tock();
		System.out.println("Second run took "+ time +" seconds");		
		System.out.println("Sum is:");
		System.out.println(sumArr);
      
                 }
             catch( IOException e )
           { System.err.println( e ); }
           
           		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
