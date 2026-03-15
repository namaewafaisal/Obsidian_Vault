public class FrequencyArray {
    private int[] freq;
    private int mostFreq;
    FrequencyArray(int[] arr){
        int maxRange = 0;
        for(int t: arr){
            maxRange = Math.max(maxRange,t);
        }
        freq = new int[maxRange+1];
        mostFreq = -1;
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
        return freq[i] == 0 ? false : true;
    }
    public void display(){
        for(int i = 0; i<freq.length; i++){
            System.out.printf("%d : %d\n", i, freq[i]);
        }
    }

    public static void main(String[] args) {
        FrequencyArray fq = new FrequencyArray(new int)
    }

}