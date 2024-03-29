public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;

        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //向二分搜索树中添加新的元素(key,value)
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以Node为根的二分搜索树中插入元素(key,value)，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {

        //递归终止条件
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        return node;//返回根
    }

    //返回以Node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else return getNode(node.right, key);
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key)!=null;
    }

    @Override
    public V get(K key) {
        Node node=getNode(root,key);
        return node==null?null: node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node=getNode(root,key);
        if (node==null)
            throw new IllegalArgumentException(key+" doesn't exist!");
        node.value=newValue;
    }



    //从二分搜索树中删除元素为e的节点
    @Override
    public V remove(K key) {
        Node node=getNode(root,key);
        if (node!=null){
            root=remove(root,key);
            return node.value;
        }
        return null;
    }
    //删除掉以node为根的二分搜索树中值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {//key==node.key
            //待删除的节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除的节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除的节点左右子树均为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            else {
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);//removeMin中有size--
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }
    //删除以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
}
