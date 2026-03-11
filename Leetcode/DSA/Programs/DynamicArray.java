public class DynamicArray<T> {
    private T[] list;
    private int index;
    DynamicArray(){
        list = (T[]) new Object[4];
        index = -1;
    }

    public void add(T data){
        if(isFull()){
            resize(list.length*2);
        }
        index++;
        list[index] = data;
    }
    public void remove(int j){
        if(isEmpty()) throw new IndexOutOfBoundsException();
        if(j>index || j <0) throw new IndexOutOfBoundsException();
        for(int i = j; i<index; i++){
            list[i] = list[i+1];
        }
        list[index] = null;
        index--;
        if(index+1 == list.length/4){
            resize(list.length/2);
        }
    }
    public T get(int i){
        if(i>index || i<0) throw new IndexOutOfBoundsException();
        return list[i];
    }
    public void set(int i, T data){
        if(i>index || i<0) throw new IndexOutOfBoundsException();
        list[i] = data;
    }
    public int size(){
        return index+1;
    }
    public boolean isEmpty(){
        return index==-1;
    }
    public boolean isFull(){
        return index==list.length-1;
    }
    public void display(){
        for(int i = 0; i<=index; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
    private void resize(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        for (int i = 0; i <= index; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }
    public static void main(String[] args) {
    DynamicArray<Integer> arr = new DynamicArray<>();

    // Test add and grow
    arr.add(10);
    arr.add(20);
    arr.add(30);
    arr.add(40);
    arr.add(50); // triggers grow
    arr.display(); // expect: 10 20 30 40 50

    // Test get and set
    System.out.println(arr.get(2)); // expect: 30
    arr.set(2, 99);
    arr.display(); // expect: 10 20 99 40 50

    // Test remove from middle
    arr.remove(1);
    arr.display(); // expect: 10 99 40 50

    // Test remove until shrink triggers
    arr.remove(0);
    arr.remove(0); // size becomes 2, capacity 8 → shrink fires at size 2 (8/4)
    arr.display(); // expect: 40 50

    // Test remove last element
    arr.remove(0);
    arr.remove(0);
    System.out.println(arr.isEmpty()); // expect: true
}

}

