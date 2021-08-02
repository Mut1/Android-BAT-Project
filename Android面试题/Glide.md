-   [Android图片加载框架最全解析（一），Glide的基本用法](https://blog.csdn.net/guolin_blog/article/details/53759439) 
-   [Android图片加载框架最全解析（二），从源码的角度理解Glide的执行流程](https://blog.csdn.net/guolin_blog/article/details/53939176)
-   [Android图片加载框架最全解析（三），深入探究Glide的缓存机制](https://blog.csdn.net/guolin_blog/article/details/54895665)
-   [Android图片加载框架最全解析（四），玩转Glide的回调与监听](https://blog.csdn.net/guolin_blog/article/details/70215985)
-   [Android图片加载框架最全解析（五），Glide强大的图片变换功能](https://blog.csdn.net/guolin_blog/article/details/71524668)
-   [Android 【手撕Glide】--Glide缓存机制（面试）](https://www.jianshu.com/p/ba7f38ede854)
-   [⭐️⭐️面试官：简历上最好不要写Glide，不是问源码那么简单](https://juejin.cn/post/6844903986412126216)
-   [聊一聊关于Glide在面试中的那些事](https://juejin.cn/post/6844904002551808013)
-   ⭐️⭐️[【带着问题学】Glide做了哪些优化?](https://juejin.cn/post/6970683481127043085)

#### Glide 怎么绑定生命周期

Glid.with(Activity activity)的方式传入页面引用

1. 创建无UI的Fragment，并绑定到当前activity
2. builder模式创建RequestManager，将fragment的lifecycle传入，这样Fragment和RequestManager就建立了联系
3. RequestManager实现LifecycleListener是一个接口，回调中处理请求

#### Glide 缓存机制内存缓存，磁盘缓存

Glid的缓存机制，主要分为2中缓存，一种是内存缓存，一种是磁盘缓存

**之所以使用内存缓存的原因是：防止应用重复将图片读入到内存，造成内存资源浪费。**

**之所以使用磁盘缓存的原因是：防止应用重复的从网络或者其他地方下载和读取数据。**

Glide缓存分为`弱引用+ LruCache+ DiskLruCache`，

**其中读取数据的顺序是：弱引用 > LruCache > DiskLruCache>网络；**

```tex
先去Lru中找，有则直接取。
没有，则去SoftRefrence中找，有则取，同时将图片放回Lru中。
没有的话去文件系统找，有则取，同时将图片添加到Lru中。
没有就走下载图片逻辑，保存到文件系统中，并放到Lru中。
```

**写入缓存的顺序是：弱引用缓存-->Lru算法缓存-->磁盘缓存中**

内存缓存分为弱引用的和 LruCache ，其中正在使用的图片使用弱引用缓存，暂时不使用的图片用 LruCache缓存，这一点是通过 图片引用计数器（acquired变量）来实现的，详情可以看内存缓存的小结。

磁盘缓存就是通过DiskLruCache实现的，根据缓存策略的不同会获取到不同类型的缓存图片。它的逻辑是：先从转换后的缓存中取；没有的话再从原始的（没有转换过的）缓存中拿数据；再没有的话就从网络加载图片数据，获取到数据之后，再依次缓存到磁盘和弱引用。

#### 关于LruCache

- LinkHashMap 继承HashMap，在 HashMap的基础上，新增了双向链表结构，每次访问数据的时候，会更新被访问的数据的链表指针，具体就是先在链表中删除该节点，然后添加到链表头header之前，这样就保证了链表头header节点之前的数据都是最近访问的（从链表中删除并不是真的删除数据，只是移动链表指针，数据本身在map中的位置是不变的）

- LruCache 内部用LinkHashMap存取数据，在双向链表保证数据新旧顺序的前提下，设置一个最大内存，往里面put数据的时候，当数据达到最大内存的时候，将最老的数据移除掉，保证内存不超过设定的最大值