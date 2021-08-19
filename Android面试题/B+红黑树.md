> 树
>
> 二叉树
>
> 真二叉树
>
> 满二叉树
>
> 完全二叉树
>
> 二叉搜索树、二叉查找树、二叉排序树
>
> 平衡二叉搜索树
>
> - AVL 
> - 红黑树

### 二叉搜索树

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819155816.png)

接口设计：![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819155944.png)

添加节点：

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819163857.png" style="zoom:50%;" />

```java
public void add(E element) {
		elementNotNullCheck(element);
		
		// 添加第一个节点
		if (root == null) {
			root = new Node<>(element, null);
			size++;
			return;
		}
		// 添加的不是第一个节点
		// 找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等
				node.element = element;
				return;
			}
		} while (node != null);

		// 看看插入到父节点的哪个位置
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
	}
```

#### 遍历

- 前序遍历：**根节点**、前序遍历左子树、前序遍历右子树（递归）
- 中序遍历：中序遍历左子树、**根节点**、中序遍历右子树（递归）
- 后序遍历：后序遍历左子树、后序遍历右子树、**根节点**（递归）
- 层序遍历：（队列、DFS）

#### 重构二叉树

前序+中序

后序+中序

#### 前驱结点（predecessor）

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819220909.png" style="zoom:50%;" />

- 前驱结点：中序遍历时的前一个节点
  - 如果是二叉搜索树，前驱节点就是前一个比它小的节点

1. 举例6、13、8

- `node.left!=null`
- `predecessor=node.left.right.right.right...`
  - 终止条件：`right`为`null`

2. 举例7、11、9、1

- `node.left==null&&node.parent!=null`
- `predecessor=node.parent.parent.parent...`
  - 终止条件：`node`在`parent`的右子树中

3. 举例8

- `node.left==null&&node.parent==null`
  - 那就没有前驱节点

```java
private Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（left.right.right.right....）
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		//退出循环有两种情况
		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
```

#### 后继节点（successor）

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819222219.png" style="zoom:50%;" />

- 后继节点：中序遍历时的后一个节点
  - 如果是二叉搜索树，后继节点就是后一个比它大的节点

1. 举例1、8、4

- `node.right!=null`
- `successor=node.right.left.left...`
  - 终止条件：left为null

2. 举例：7、6、3、11

- `node.right==null&&node.parent!=null`
- `successor=node.parent.parent.parent..`
  - 终止条件：`node`在parent的左子树中

3.没有右子树的根节点

- `node.right==null&&node.parent==null`
  - 那就没有后驱节点

```java
private Node<E> successor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（right.left.left.left....）
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}

		return node.parent;
	}
	
```

#### remove-度为0 1的节点

#### remove-度为2的节点

### 平衡二叉搜索树

防止二叉搜索树退化成链表

#### 如何改进二叉搜索树：

- 首先，节点的添加、删除顺序是无法限制的，可以认为是随机的
- 所以，改进方案是：在节点的添加、删除操作之后，想办法让二叉搜索树恢复平衡（减小树的高度）

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819234130.png" style="zoom:50%;" />

- 如果接着继续调整节点的位置，完全可以达到理想平衡，但是付出的代价可能会比较大
  - 比如调整的次数会比较多，反而增加了时间复杂度
- 总结来说，比较合理的改进方案是：用尽量少的调整次数达到**适度平衡**即可
- 一棵达到适度平衡的二叉搜索树，可以称之为：**平衡二叉搜索树**

#### 常见的平衡二叉搜索树：

- AVL树
  - Windows NT 内核中广泛使用
- 红黑树
  - C++ STL（比如 map、set ）
  - Java 的 TreeMap、TreeSet、HashMap、HashSet
  - Linux 的进程调度
  - Ngix 的 timer 管理

一般也称它们为：自平衡的二叉搜索树（Self-balancing Binary Search Tree）

#### 继承结构

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819234736.png" style="zoom:50%;" />

### AVL树

> AVL树是最早发明的自平衡二叉搜索树之一

- 平衡因子（Balance Factor）：某结点的左右子树的高度差
- AVL树的特点:
  - 每个节点的平衡因子只可能是 **1、0、-1**（绝对值 ≤ 1，如果超过 1，称之为“失衡”）
  - 每个节点的左右子树高度差不超过 1
  - 搜索、添加、删除的时间复杂度是 O(logn)

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819234622.png" style="zoom:50%;" />

#### 添加导致的失衡

- 示例：往下面这棵子树中添加 13
- 最坏情况：可能会导致**所有祖先节点**都失衡
- 父节点、非祖先节点，都不可能失衡

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210819235133.png" style="zoom:50%;" />

##### LL – 右旋转（单旋）

LL----g.left.left

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820001311.png)

##### RR-左旋转（单旋）

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820001647.png)

##### LR-RR左旋转，LL右旋转（双旋）

- 先对P进行左旋转
- 再对g进行右旋转

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820002019.png)

##### RL-LL右旋转，RR左旋转（双旋）

- 先对p进行右旋转
- 再对g进行左旋转

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820002221.png)

