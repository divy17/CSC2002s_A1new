

import java.util.concurrent.RecursiveTask;

public class BasinThread extends RecursiveTask<String>  {
	  int s1;
     int s2; // arguments
     int f1;
     int f2;
     Count count;
     int add = 0;
	  float[][] arr;
     String subans = "";
     
	  static final int SEQUENTIAL_CUTOFF = 100;

	  String ansfinal = ""; // result 
	    
	  BasinThread(float[][] a, int start1, int finish1, int start2, int finish2, Count c) { 
	    arr = a; s1 = start1 ; f1 = finish1 ; s2 = start2 ; f2 = finish2 ; count = c;
	  }


	  protected String compute(){// return answer - instead of run
		  if(((f1-s1)*(f1-s1)) < SEQUENTIAL_CUTOFF) {
         
         
         
         for (int i = s1; i<f1;i++) {
          for (int j = s2; j<f2; j++) { 
           boolean b = false;
           
           if ((i>0) && (i<(arr.length-1)) && (j>0) && (j<(f2-1))) {
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
              count.count1 = count.count1 +1;
              subans = subans + Integer.toString(i) +" "+ Integer.toString(j) + "\n" ; }
             
		  }}
        return subans ;
        } 
        
		  else {
			  BasinThread one   = new BasinThread(arr, s1   , (s1+f1)/2, 0, f2, count ); 
			  BasinThread two   = new BasinThread(arr, (s1+f1)/2   , f1, 0, f2, count   );

			  

			  two.fork();
			  String oneAns    =  one.compute();
			  String twoAns    =  two.join();
           
			  return  oneAns+ twoAns;  
              
		  }
        
	 }
}

