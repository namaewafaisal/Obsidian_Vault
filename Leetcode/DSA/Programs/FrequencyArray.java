public class FrequencyArray<T> {
    int[] freq;
    int mostFreq;
    FrequencyArray(T[] arr){
        int maxRange = 0;
        for(T t: arr){
            maxRange = Math.max(maxRange,t);
        }
    }
}