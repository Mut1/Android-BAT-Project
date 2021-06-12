public class MaxHeap<E extends Comparable<E>>  {
    private Array<E> data;
    public MaxHeap(int capacity){
        data=new Array<>(capacity);
    }

    public MaxHeap(){
        data=new Array<>();
    }

    //返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    //返回一个布尔值，表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完成二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){

    }
}
