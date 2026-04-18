public class CircularQueue<E> {

    private final E[] arr;
    private final int capacity;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");

        this.capacity = capacity;
        this.arr = (E[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void enqueue(E data) {
        if (isFull())
            throw new IllegalStateException("Queue is full");

        arr[rear] = data;
        rear = (rear + 1) % capacity;
        size++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        E value = arr[front];
        arr[front] = null;  // prevent memory leak
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public E peek() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        return arr[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }

        int index = front;
        for (int i = 0; i < size; i++) {
            System.out.print(arr[index] + " ");
            index = (index + 1) % capacity;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        CircularQueue<Integer> q = new CircularQueue<>(5);
        q.enqueue(0);
        q.dequeue();
        q.display();
    }
}
