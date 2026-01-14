class SolutionProd {
    public int[] productExceptSelf(int[] nums) {
        int left = 0;
        int right = 0;
        int leftProd = 1;
        int rightProd = 1;
        int[] rightArray = new int[nums.length];
        int[] output = new int[nums.length];

        for(int i = 0; i< nums.length; i++) {
            rightArray[nums.length-i-1] = rightProd;
            rightProd *= nums[nums.length-1-i];

        }

        for(int i = 0; i< nums.length; i++) {

            if(i>0) {
                leftProd *= nums[i-1];
            }


            output[i] = leftProd * rightArray[i];
            


        }
        return output;
    }
}
class Product {
    public static void main(String[] args) {
        SolutionProd sol = new SolutionProd();
        int[] arr = {1,2,3,4};
        System.out.println(sol.productExceptSelf(arr));
    }
}