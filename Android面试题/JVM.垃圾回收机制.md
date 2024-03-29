> 1. 什么是垃圾回收机制？
>
> 2. 回收哪个区域的垃圾？
> 3. 什么时候回收？
> 4. 如何回收？

#### 什么是垃圾回收机制？

总的来说就是**虚拟机中一套可以自动对不再使用的对象进行回收释放内存的机制**

#### 回收哪个区域的垃圾？

方法区和堆。

但堆区的管理显得比方法区更加重要且常见。

#### 如何识别垃圾？

如何判断一个对象是否是垃圾？引用计数法、可达性分析

##### 引用计数法

> 使用一个额外的内存对对象的引用进行计数。当一个地方对这个对象进行引用时，那么引用计数+1，当一个引用失效时，引用数-1。如果一个对象的引用数为0，说明这个对象没有被引用，标记为垃圾。

引用计数法有两个优点：原理简单、判断效率高。

缺点：**无法解决循环引用问题**。

例如：A持有了B，B持有了A，那么如果A、B不再使用，但是两者却无法被释放。可能你会觉得，这种两个对象的互相引用很少，但是，当对象越来越多、逻辑越来越复杂的时候，对象之间彼此引用，可能造成整个堆区的对象都无法被释放，从而造成了严重的内存泄露。

所以，引用计数法需要**配合大量额外的的处理才能保证正确工作**。同时每个对象需要**额外的空间来存储计数器**，虽然不多，但也是一种内存代价。

##### 可达性分析

> 从一系列被称为GCRoots的对象出发，根据他们所持有的引用向下检索，检索走过的路线称为“引用链”。如果一个对象与GCRoots之间没有引用链，那么该对象被标记为垃圾。

优点：解决了循环引用的问题。但带来了两个问题：如何确定GCRoots、引用膨胀。

首先是GCRoots的确定。什么对象可以作为GCRoots？**存在且能被虚拟机直接访问到的对象**

可作为GCRoots的对象有以下类型：

- 方法栈中引用的对象，包括虚拟机栈和本地方法栈。
- 方法区类静态属性引用的对象。
- 常量引用的对象。
- 虚拟机内部引用的对象。
- 被同步锁持有的对象。
- 反应虚拟机内部情况的JMXBean、JCMTI中注册的回调、本地代码缓存等。
- 根据具体区域临时加入的对象，主要是跨代引用的加入。

类型非常多，记住我的那条可能不太严谨的总结：**该对象一定存在且可以被虚拟机直接访问到，那么该对象就可以作为GCRoots**。这些对象在运行时都是一定会存在的，那么他们就可以作为GC Roots。

第二个问题是引用膨胀。当内存中的对象越来越多，这个时候一个GC Root的引用链会变成一棵巨大无比的树，那么遍历这个树的性能消耗就比较高了。那么需要一定的额外操作，来优化引用膨胀问题，例如**分代收集**等等，后续会慢慢讲到这个问题。

#### Java四大引用类型

##### 强引用：

强引用也称为直接引用，例如Person p = new Person()，此时的p就是对Person实例的强引用。在对象拥有强引用的情况下，对象永远不会被回收，如果内存不足则抛出OutOfMemoryError异常。

##### 软引用：

软引用比强引用更加弱一点。使用方法是SoftReference<Person> pS = new SoftReference<>(new Person);。当一个对象仅拥有软引用时，当内存不足时会被系统回收。而如果内存充足则不会被回收。

这个引用一般是用于对象持有的一些资源，但这些资源又不是绝对必要，在程序崩溃和释放资源的选择下，选择了释放资源。例如缓存资源。当内存不足的时候，肯定是先把缓存清理了，不至于发生OOM。

##### 弱引用：

弱引用比软引用更加弱一个档次。使用方法`WeakReference<String> s = new WeakReference<>("");`。对于一个只有弱引用的对象，无论内存是否充足，都会进行回收。

弱引用强调两个对象的关系是很弱的，他解决的问题是：A确实需要引用到B，但当B需要回收的时候，却因为被A引用了而无法回收。这个时候使用弱引用就可以很好地解决问题了。例如Android中的MVP设计模式，Activity持有presenter的引用可以直接调用presenter的接口来做请求，同样presenter需要异步回调activity的接口来更新UI。但是，如果presenter直接持有activity的引用，那么当退出界面后，就会导致activity无法被及时回收而导致内存泄露。所以这个时候，presenter只能持有activity的弱引用。

##### 虚引用：

对于对象的生命周期没有任何的影响，也无法通过虚引用来获取对象实例，只用接收对象被回收时的通知。他的使用方法是：

```java 
ReferenceQueue queue = new ReferenceQueue();
PhantomReference<String> reference = new PhantomReference<String>("一只修仙的猿", queue);
```

通过这个reference我们无法获取到对象的引用。当对象被回收时，该对象的相关信息会被放到队列中，我们只需要从队列中获取信息即可。

#### 如果收集垃圾

GC机制是为了释放内存，减少内存压力，同时GC本身作为一个内存管理的自动化工具，肯定**不能造成太高的性能压力。**而在收集这些垃圾的过程中，直接遍历堆中所有的对象是性能非常低的做法，会严重影响程序的性能。

##### 分代收集理论

分代收集理论是建立在两个假说上的：

> 1. 弱分代假说：绝大多数对象都是朝生夕灭的
> 2. 强分代假说：熬过越多次垃圾收集过程的对象就越难以消亡

这两个假说指出了两种不同年龄的对象拥有不同的特性：**刚创建的对象大都会被回收，而在多次回收中存活的对象则后续也很少被回收。**

这虽然是两个假说，但也在实践中得到了证实。而事实上也是很好理解，大部分创建的对象都是临时对象，而存活的时间越久说明这个对象的作用域越大或者更加重要，后续也会继续存活下来。那么我们可以针对这些不同特性的对象执行不同的回收算法来提高GC性能：

> 1. 对于新创建的对象，我们需要更加频繁地对他们进行GC释放内存，且每次只需要记录需要留下来的对象即可，而不必要去标记其它大量需要被回收的对象，提高性能。
> 2. 对于熬过很多次GC的对象，则可以以更低的频率对他们进行GC，且每次只需要关注少量需要被回收的对象即可。

针对这两种不同的对象，把堆划分为两个区域：

一个用来装新创建的对象             -------> **新生代**

一个用来装多次GC后存活的对象 -------> **老年代**

**这种针对不同年龄的对象执行不同的收集方案的理论就称为分代收集理论。**

##### 跨代引用

##### ![BZLBwD](https://s1.ax1x.com/2020/10/24/BZLBwD.png)

> 跨代引用假说：跨代引用相对于同代引用来说仅占少数

> 我们可以在新生代维护一个“记忆集”，该记忆集把老年代划分为不同的区域，把老年代中存在跨代引用的对象放在一块小内存中。那么当下次新生代GC的时候，只需要对这小部分内存进行扫描，而无需遍历所有的老年代对象。

---

#### 不同的收集算法

##### 标记-清除算法

最基础的收集算法。

**概念：**每次遍历并标记出需要被回收的对象或者存活的对象，再一个个进行回收标记或者没有标记的对象

**缺点：**

1. 性能不稳定。在新生代每次需要回收大量的对象的情况下，进行大量标记再一个个去释放内存显然效率非常低

			2. 空间碎片化。这个好理解，回收之后会剩下很多不连续的空间，这样会导致大对象无法创建。

##### 标记-复制算法

这个算法是针对新生代而设计的：

> 把内存分为两个等分的部分A和B，同时只使用一个部分，例如使用A部分。
> 当垃圾回收时，把A部分的活着对象全部复制到另一个部分B。
> 当前A部分的内存全部释放，然后把B部分当成主要使用内存。

**好处：**

1. **只需要转移少量存活的对象，然后统一对一整片内存进行回收；转移后的对象集中，不会出现空间碎片化的问题。**

**代价：**

内存只能使用一半。因此有个新的解决方案：Appel式回收

###### Appel式回收

Appel式回收的设计背景就是每次新生代GC，都有98%的对象被回收。他把内存分为三个部分：**Eden**和两个**Survivor**。Eden占80%内存，而每个Survivor占10%内存。Appel式回收算法也不复杂：

> 主要使用Eden部分和一个Survivor部分。
> 当发生GC时把存活的对象全部复制到另一个Survivor中，Eden和原来的Survivor被释放。
> 这个时候主要使用的内存变为Eden部分和存活的对象存储的Survivor区域。

<img src="https://s1.ax1x.com/2020/10/24/BeSIKA.png" alt="BeSIKA" style="zoom:60%;" />

其中左边是使用的Eden和Survivor1，经过GC之后，对象1和对象3存活了，那么就把他们复制到Survivior2中。然后使用的内存区域就变成了Eden和Survivor2

这样的好处就是，我们只需要占用10%的内存来复制，而可以使用到90%的内存空间。但是总有意外的那一刻。如果存活的对象超过了Survivor的空间，那么就会把无法承载的对象先托管到老年代中。等到回收有足够的空间就可以把对象转移回来，或者直接升级为老年代对象。如果老年代也没有多余的空间，那么就会触发一次老年代的GC 来腾出足够的空间。

##### 标记-整理算法

前面的标记-复制法是针对新生代而言，而对于老年代，则就完全不适合了。**老年代每次存活的对象非常多，如果进行复制，则每次都需要进行大量的复制操作，效率非常低；而且如果对象全部存活，有可能另一半空间不够存放，就会直接导致出问题了，因为没有另外的空间来暂时存放。**所以需要有另一种更加符合老年代特性的方案：**标记-整理法**

> 把存活的对象标记下来，然后全部往一侧的内存移动，把边界以外的所有内存全部释放

但这还有另一种优化标记-整理的方案：“和稀泥法”。

> 在内存足够使用的时候仅采用标记-清除法，等到碎片化程序影响对象的创建时再执行一次标记-整理法。


这样就可以明显降低移动对象的次数，提高标记-整理算法的性能。

#### 被标记为垃圾就肯定会被回收吗？

既然问了，那肯定是不对的。首先回顾一下GC的流程：

1. 首先确定要进行GC的区域。这里更加细分，新生代还是整个堆区？（事实上很少单独对老年代GC）
2. 找出所有需要回收的对象或者存活的对象，并打上标记
3. GC收集器开始收集。根据不同的算法采取不同的方式释放内存

而在2,3之间缺少了一个步骤，也就是被标记为垃圾不一定会被回收的原因。

> 在对象被第一次标记为垃圾之后，如果该对象重写了finalize方法且没有被执行过，会把对象放到一个队列中，等待虚拟机的调用。如果对象在finalize方法中把自己赋值给了别的引用，那么他就不会被回收.
> 如果对象已经执行了一次finalize方法，那么下一次标记他就会直接回收了，不会执行第二次finalize方法。

所以当对象被标记为垃圾之后，如果他重写了finalize，那么会进入到一个队列中，等待虚拟机来调用finalize方法把自己赋值给其它引用来让自己活下去。但是：**虚拟机使用一个低优先级的线程来执行次任务，且不会等待该finalize方法运行结束。**原因是如果finalize发生了死循环会造成阻塞，导致整个GC无法运行。

所以可以看到finalize方法有两个个非常大缺点：执行时间与完成度不确定、代价高昂。在《深入理解Java虚拟机》一书中作者的观点是：把这个方法忘了，不要去使用它。而我们仅做一些了解即可。那么下面讨论一个很类似的问题，调用了`System.gc()`就一定会触发回收吗？

#### 调用`System.gc()`一定会触发回收吗？

答案肯定是否。

根据官方的注释，这个方法只是通知虚拟机需要进行垃圾回收，**虚拟机会尽量帮你做，但是什么时候做、甚至做不做都是不确定的。**所以从官方的注释中就已经可以看出来这个回收时不确定的，包括`Runtime.getRuntime.gc()`，本质上前者调用了后者。而关于`System.gc()`，有众多的不建议使用的观点：

1. 当调用了System.gc()之后，我们不确定虚拟机会做什么。完全不确定GC什么时候运行、如何运行、甚至运不运行。
2. 该方法会降低虚拟机的GC效率。虚拟机拥有自己的垃圾收集策略，而我们的操作会破坏这个策略，降低了性能。例如前面的“和稀泥”法，原本虚拟机应该是在碎片化空间影响对象的创建的时候再进行GC，而如果我们提前进行gc则会破坏这个策略。
3. 虚拟机的GC机制比我们更懂得如何去进行GC，我们完全没有必要去手动GC。

当然也有赞同使用的观点：

1. 建议不要依赖它做任何事情是正确的。不要依赖于它的工作，但是暗示现在是一个可以接受的收集时间是完全可以的。
2. 当试图找出某个特定对象是否泄露时也可以使用此方法。
3. 如果虚拟机的策略是内存不足再GC，而后续有更重要的任务，那么可以提前进行GC。
4. 在计算机空闲的时候可以进行GC。