//package ForkJoinSum;

import java.util.concurrent.RecursiveTask;

public class BasinThread extends RecursiveTask<String>  {
	  int s1;
     int s2; // arguments
     int f1;
     int f2;
	  float[][] arr;
	  static final int SEQUENTIAL_CUTOFF = 17;

	  String ansfinal = ""; // result 
	    
	  BasinThread(float[][] a, int start1, int finish1, int start2, int finish2) { 
	    arr = a; s1 = start1 ; f1 = finish1 ; s2 = start2 ; f2 = finish2 ; 
	  }


	  protected String compute(){// return answer - instead of run
		  if(((f1-s1)*(f1-s1)) < SEQUENTIAL_CUTOFF) {
         
         String subans = "";
         for (int i = s1; i<f1;i++) {
          for (int j = s2; j<f2; j++) { 
           boolean b = false;
           
           if ((i>s1) && (i<(f1-1)) && (j>s2) && (j<(f2-1))) {
            float initial = 0.01f;
            float v = arr[i][j] + initial;
            int top   = i-1;
            int bot   = i+1;
            int left  = j-1;
            int right = j+1;
            
            if ( (v <= arr[top][left]) && (v <= arr[top][j]) && (v <= arr[top][right]) && (v <= arr[i][left]) 
               && (v <= arr[i][right]) && (v <= arr[bot][left]) && (v <= arr[bot][j]) && (v <= arr[bot][right]) ) {
               b = true; }}
               
             if (b == true) {
              subans = subans + Integer.toString(i) +" "+ Integer.toString(j) + "\n" ; }
             
		  }}
        return subans ; }
        
		  else {
			  BasinThread one   = new BasinThread(arr, 0   , f1/2, 0   , f2/2 );
			  BasinThread two   = new BasinThread(arr, 0   , f1/2, f2/2, f2   );
           BasinThread three = new BasinThread(arr, f1/2, f1  , 0   , f2/2 );
           BasinThread four  = new BasinThread(arr, f1/2, f1  , f2/2, f2   );
			  
			  // order of next 4 lines
			  // essential – why?
			  two.fork();
           three.fork();
           four.fork();
			  String oneAns    =  one.compute();
			  String twoAns    =  two.join();
           String threeAns  =  three.join();
           String fourAns   =  four.join();
			  return oneAns + twoAns + threeAns + fourAns  ;     
		  }
	 }
}

