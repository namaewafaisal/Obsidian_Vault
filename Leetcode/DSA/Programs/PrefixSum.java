public class PrefixSum {
    int[] prefix;

    public PrefixSum(int[] arr){
        prefix = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            prefix[i] = arr[i] + (i==0 ? 0 : prefix[i-1]);
        }
    }
    public int rangeSum(int l, int r){
        if(l < 0 || prefix.length <= l) throw new IndexOutOfBoundsException();
        if(r < 0 || prefix.length <= r) throw new IndexOutOfBoundsException();
        if(l > r) throw new IndexOutOfBoundsException();
        return prefix[r] - (l==0 ? 0 : prefix[l-1]);
    }
    public double rangeAvg(int l, int r){
        if(l < 0 || prefix.length <= l) throw new IndexOutOfBoundsException();
        if(r < 0 || prefix.length <= r) throw new IndexOutOfBoundsException();
        if(l > r) throw new IndexOutOfBoundsException();
        return (prefix[r] - (l==0 ? 0 : prefix[l-1]))/(double) (r-l+1);
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        PrefixSum ps = new PrefixSum(arr);
        System.out.println(ps.rangeSum(3,4));
        System.out.println(ps.rangeAvg(3,4));
    }



}
