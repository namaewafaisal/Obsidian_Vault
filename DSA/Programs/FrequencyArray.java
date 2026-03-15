public class FrequencyArray {
    private int[] freq;
    private int mostFreq;
    FrequencyArray(int[] arr){
        int maxRange = 0;
        for(int t: arr){
            maxRange = Math.max(maxRange,t);
        }
        freq = new int[maxRange+1];
        mostFreq = arr[0];
        for(int t : arr){
            freq[t]++;
            if(mostFreq == -1){
                mostFreq = t;
            }
            else if(freq[t] > freq[mostFreq]){
                mostFreq = t;
            }
        }
    }

    public int getFrequency(int i){
        if(i < 0 || i >= freq.length) throw new IndexOutOfBoundsException();
        return freq[i];
    }
    public int getMostFrequent(){
        return mostFreq;
    }
    public boolean contains(int i){
        if(i < 0 || i >= freq.length) throw new IndexOutOfBoundsException();
        return freq[i] > 0;
    }
    public void display(){
        for(int i = 0; i<freq.length; i++){
            if(freq[i]>0)
            System.out.printf("%d : %d\n", i, freq[i]);
        }
    }

    public static void main(String[] args) {
        FrequencyArray fq = new FrequencyArray(new int[] {2,4,5,1,5,6,3,4,2});
        fq.display();
        System.out.println(fq.getMostFrequent());
        System.out.println(fq.contains(0));
        System.out.println(fq.getFrequency(2));
    }

}