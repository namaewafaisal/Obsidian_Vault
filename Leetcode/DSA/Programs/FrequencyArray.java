public class FrequencyArray {
    int[] freq;
    int mostFreq;
    FrequencyArray(int[] arr){
        int maxRange = 0;
        for(int t: arr){
            maxRange = Math.max(maxRange,t);
        }
        freq = new int[maxRange+1];
        for(int t : arr){
            
        }
    }
}