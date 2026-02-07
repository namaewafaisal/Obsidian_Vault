
```java
class SegmentNode{
  int data;
  SegmentNode left;
  SegmentNode right;
  int rangeLeft;
  int rangeRight;

  SegmentNode(){}

  SegmentNode(int data){
    this.data = data;

  }
  SegmentNode(int data, int rangeLeft, int rangeRight){
    this.data = data;
    this.rangeLeft = rangeLeft;
    this.rangeRight = rangeRight;

  }

  public SegmentNode buildTree(int[] arr, int rangeLeft, int rangeRight){
    if(rangeLeft == rangeRight) return new SegmentNode(arr[rangeLeft],rangeLeft, rangeRight);

    int mid = findMid(rangeLeft, rangeRight);
    SegmentNode root = new SegmentNode();
    root.left = buildTree(arr, rangeLeft, mid);
    root.right = buildTree(arr, mid+1, rangeRight);
    root.rangeLeft = rangeLeft;
    root.rangeRight = rangeRight;
    root.data = Math.min(root.left.data, root.right.data);
    
    return root;

  }

  public int findMid(int left, int right){
    return (left + right) / 2;
  }

  public int queryMin(SegmentNode root, int left, int right){
// 1 4

    if(right < root.rangeLeft || left > root.rangeRight) return Integer.MAX_VALUE;
    if(root.rangeLeft >= left && root.rangeRight <= right){
      return root.data;
    } // 0 1

    int mid = findMid(root.rangeLeft,root.rangeRight);
  
    return Math.min(queryMin(root.left, left, mid),queryMin(root.right, mid+1,right));
    
  }

  }



public class Main{
  public static void main(String[] args){
      int[] arr = {3,4,7,6,1,9};

      SegmentNode root = new SegmentNode();

      root = root.buildTree(arr, 0,arr.length-1);
      System.out.println(root.queryMin(root, 1, 2));
      
  }
}
```