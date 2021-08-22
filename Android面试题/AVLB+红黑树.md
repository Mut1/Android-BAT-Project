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
>
> ---
>
> Mut

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

##### 示例

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820010507.png)

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820010530.png)

##### 统一所有旋转

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820010813.png)

#### 删除导致的失衡

- 示例：删除子树中的 16
- 可能会导致父节点或祖先节点失衡（只有1个节点会失衡），其他节点，都不可能失衡

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820011201.png)

##### LL-右旋转（单旋）

- 如果绿色节点不存在，更高层的祖先节点可能也会失衡，需要再次恢复平衡，然后又可能导致更高层的祖先节点失衡...
- 极端情况下，所有祖先节点都需要进行恢复平衡的操作，共 O(logn) 次调整

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820011353.png)

##### RR – 左旋转（单旋）

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820011552.png)

##### LR – RR左旋转，LL右旋转（双旋）

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820011615.png)

##### RL – LL右旋转，RR左旋转（双旋）

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820011638.png)

##### AVL总结

- 添加
  - 可能会导致所有祖先节点都失衡
  - 只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡【仅需 O(1) 次调整】
- 删除
  - 可能会导致**父节点**或**祖先节点**失衡（只有1个节点会失衡）
  - 恢复平衡后，可能会导致更高层的祖先节点失衡【最多需要 O(logn) 次调整】
- 平均时间复杂度
  - 搜索：O(logn)
  - 添加：O(logn)，仅需 O(1) 次的旋转操作
  - 删除：O(logn)，最多需要 O(logn) 次的旋转操作

### B树（B-tree）

- **B树**是一种平衡的**多路**搜索树，多用于文件系统、数据库的实现

#### 特点：

- 1 个节点可以存储超过 2 个元素、可以拥有超过 2 个子节点
- 拥有二叉搜索树的一些性质
- 平衡，每个节点的所有子树高度一致
- 比较矮

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820015116.png" style="zoom:50%;" />

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820015137.png" style="zoom:50%;" />

#### m阶B树的性质（m≥2）（特别重要）

假设一个节点存储的元素个数为 x

- 根节点：1 ≤ x ≤ m − 1
- 非根节点：┌ m/2 ┐ − 1 ≤ x ≤ m − 1
- 如果有子节点，子节点个数 y = x + 1
  - 根节点：2 ≤ y ≤ m
  - 非根节点：┌ m/2 ┐ ≤ y ≤ m
- 举例
  - 比如 m = 3，2 ≤ y ≤ 3，因此可以称为（2, 3）树、2-3树
  - 比如 m = 4，2 ≤ y ≤ 4，因此可以称为（2, 4）树、2-3-4树
  - 比如 m = 5，3 ≤ y ≤ 5，因此可以称为（3, 5）树、3-4-5树
  - 比如 m = 2，1 ≤ y ≤ 2，因此就是二叉搜索树

> 数据库中一般用几阶B树？200-300阶

#### B树Vs二叉搜索树

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820020249.png)

- B树 和 二叉搜索树，在逻辑上是等价的
- 多代节点合并，可以获得一个超级节点
  - 2代合并的超级节点，最多拥有 4 个子节点（至少是 4阶B树）
  - 3代合并的超级节点，最多拥有 8 个子节点（至少是 8阶B树）
  - n代合并的超级节点，最多拥有 2 n 个子节点（ 至少是 2 n 阶B树）
- m阶B树，最多需要 lo g 2 m 代合并

#### B树的搜索

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820020631.png" style="zoom:50%;" />

跟二叉搜索树的搜索类似

1. 先在节点内部从小到大开始搜索元素
2. 如果命中，搜索结束
3. 如果未命中，再去对应的子节点中搜索元素，重复步骤 1

#### B树的添加

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820020902.png" style="zoom:50%;" />

**新添加的元素必定是添加到叶子节点**

- 插入55

  <img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820020940.png" style="zoom:50%;" />

- 插入95

  <img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820021026.png" style="zoom:50%;" />

- 再插入98呢？（假设这是一颗4阶B树）
  - 最右下角的叶子节点的元素个数将超过限制
  - 这种现象可以称之为：**上溢（overflow）**

##### 解决上溢

- 上溢节点的元素个数必然等于 m
- 假设上溢节点最中间元素的位置为 k
  - 将 k 位置的元素向上与父节点合并
  - 将 [0, k-1] 和 [k + 1, m - 1] 位置的元素分裂成 2 个子节点
    - 这 2 个子节点的元素个数，必然都不会低于最低限制（┌ m/2 ┐ − 1）
- 一次分裂完毕后，有可能导致父节点上溢，依然按照上述方法解决
  - 最极端的情况，有可能一直分裂到根节点(**唯一一种能让B树长高的情况**)

举例：

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820021823.png)

#### B树的删除（叶子节点）

直接删除

#### B树的删除（非叶子节点）

![image-20210820022244692](../../../Library/Application%20Support/typora-user-images/image-20210820022244692.png)

##### 解决下溢

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820022601.png)

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820023111.png)

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820023126.png)



#### B树重要结论

**新添加的元素必定是添加到叶子节点**

**真正的删除元素都是发生在叶子节点中**

### 红黑树（Red Black Tree）

- 红黑树也是一种自平衡的二叉搜索树
  - 以前也叫做平衡二叉B树
- 红黑树必须满足以下5条性质
  1. 节点是RED或者BLACK
  2. 根节点是BLACK
  3. 叶子节点（外部节点，空节点）都是BLACK
  4. RED节点的子节点都是BLACK
     - RED节点的parent都是BLACK
     - 从根节点到叶子结点的所有路径上不能有2个连续的Red节点
  5. 从任一节点到叶子节点的所有路径都包含相同数目的BLACK节点

#### 红黑树的性质

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820014759.png)

#### 请问下面这棵是红黑树么？

![image-20210820024737399](../../../Library/Application%20Support/typora-user-images/image-20210820024737399.png)

#### 红黑树的等价变化

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820095041.png)

#### 红黑树vs2-3-4树

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820095418.png)

#### 红黑树的添加

> 红黑树添加时心中一定要有B树

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/image-20210820100618884.png)

##### 添加的所有情况：

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820101043.png)

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820101600.png)

##### 添加-修复性质4-LL\RR

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820102150.png"  />

##### 添加-修复性质4-LR\RL

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820102536.png)

##### 添加-修复性质4-上溢-LL

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820103405.png)

##### 添加-修复性质4-上溢-RR

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820103523.png)

##### 添加-修复性质4-上溢-LR

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820103714.png)

##### 添加-修复性质4-上溢-RL

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820103758.png)

##### 总结

记住红黑树的根节点与子树根节点只能是BLACK

#### 红黑树的删除

> 在B树中真正被删除的元素都在叶子节点中

##### 删除-RED节点

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820104539.png)

##### 删除-BLACK节点

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820104708.png)

###### 删除 – 拥有1个RED子节点的BLACK节点

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820105255.png)

###### 删除 – BLACK叶子节点 – sibling为BLACK

- 可以和兄弟节点借

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820110701.png)

- 不可以和兄弟节点借，父节点下来合并

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820115028.png)

###### 删除 – BLACK叶子节点 – sibling为RED

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820122013.png)

##### 删除总结

**B树中，最后真正被删除的元素都在叶子节点中**

- 删除红色节点
- 删除黑色节点
  - 拥有两个红色节点的黑色节点
    - 不可能被直接删除，会找他的子节点替代删除
    - 因此不需要考虑
  - 拥有一个红色子节点的黑色节点
  - 黑色叶子节点

#### 红黑树的平衡

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820125804.png)

因为最长路径是最短路径每一个黑节点后插入一个红节点而已

#### 平均复杂度

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820130345.png" style="zoom:50%;" />

#### AVL树 vs 红黑树

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820130417.png)

#### BST vs AVL Tree vs Red Black Tree

![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210820130445.png)

