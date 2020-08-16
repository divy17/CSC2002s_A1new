//package ParallelSum2;

public class BasinThread extends Thread {
	  int lo; // arguments
	  int hi;
	  int[] arr;

	  int ans = 0; // result 
	    
	  BasinThread(int[] a, int l, int h) { 
	    lo=l; hi=h; arr=a;
	  }


	  public void run() { //override must have this type
	    for(int i=lo; i < hi; i++)
	      ans += arr[i];
	  }

}
