//package ParallelSum2;

public class FindBasin {

	static int sum(int[] arr, int numTs) throws InterruptedException {
		  int ans = 0;
		  BasinThread[] ts = new BasinThread[numTs];
		  for(int i=0; i < numTs; i++){
			  ts[i] = new BasinThread(arr,(i*arr.length)/numTs,
		                             ((i+1)*arr.length)/numTs);
			  ts[i].start();  //start, not run
		  }
		 
		  for(int i=0; i < numTs; i++) { 
			ts[i].join(); // wait for helper to finish!
		    ans += ts[i].ans;
		  }
		  return ans;
		}

	
	public static void main(String[] args) {
		int max =1000000;
		int noThreads =4;
		int [] arr = new int[max];
		for (int i=0;i<max;i++) {
			arr[i]=100;
		}
		try {
		int sumArr = sum(arr,noThreads);
		System.out.println("Sum is:");
		System.out.println(sumArr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
