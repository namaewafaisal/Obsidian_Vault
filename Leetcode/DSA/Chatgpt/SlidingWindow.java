class SlidingWindow {
    public static void main(String[] args) {
		int k = 4;
		int[] arr = {3,-5,2,8,-5,-7,-3,5,9};

		int left = 0, right = 0;
		int maxSum = Integer.MIN_VALUE;
		int currSum = 0;
		//int winSize = 0;  No need
		int loc = 0;

		while(right < arr.length) {
			currSum += arr[right];
			// winSize = right + 1 - left;
			// if(winSize > k) {
			// 	currSum -= arr[left++];
			// 	winSize = right + 1 - left;
			// } 
			//else if (winSize < k) {
			// 	right++;
			//	continue;
			//}
			if(right + 1 - left == k) {
				if(currSum > maxSum) { // or we can use math.max()
					maxSum = currSum;
					loc = right;
				}
				currSum -= arr[left++];
			}
			right++;
		}
		System.out.println(maxSum + " " + loc);
	}


}
