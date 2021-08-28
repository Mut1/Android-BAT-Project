
### 面试题
优秀文章：

[Handler 都没搞懂，拿什么去跳槽啊？](https://juejin.cn/post/6844903783139393550#heading-15)
[你真的懂Handler吗？Handler问答](https://juejin.cn/post/6844903624510799886)

[为什么Looper中的Loop()方法不会导致主线程卡死？](https://juejin.cn/post/6844903774096457736)


#### [为什么Looper中的Loop()方法不会导致主线程卡死？](https://juejin.cn/post/6844903774096457736)

***原因就是因为耗时操作本身并不会导致主线程卡死, 导致主线程卡死的真正原因是耗时操作之后的触屏操作, 没有在规定的时间内被分发。***

1. 耗时操作本身并不会导致主线程卡死, 导致主线程卡死的真正原因是耗时操作之后的触屏操作, 没有在规定的时间内被分发。
2. Looper 中的 loop()方法, 他的作用就是从消息队列MessageQueue 中不断地取消息, 然后将事件分发出去。

##### 可以在子线程创建handler么？

首先要Looper.prepare()

##### 主线程的Looper和子线程的Looper有什么区别？

主线程的Looper不可以退出，子线程的Looper可以退出；

主线程的Looper在线程启动时自己创建好，子线程的Looper需要自己prepare

##### Looper和MessageQueue有什么关系？

一对一

#### 说说Android线程间消息传递机制

##### 消息怎么发送

Handler.sendMessage

##### 怎么进行消息循环的？

Looper.loop

##### 怎么处理分发消息的？

Handler.dispatchMessage

#### Handler的消息延时是怎么实现的？

消息队列按消息触发时间排序
设置 epoll_wait的超时时间,使其在特定时间唤醒
关于延时精度

##### Handler发送消息的delay可靠吗？

- 不可靠，但需要深入分析
  - 大于Handler Looper的周期时基本可靠（例如主线程>50ms）
  - Looper负载越高，任务越容易积压，进而导致卡顿
  - 不要用Handler的delay作为计时
- 给出基于原理的实践

#### 消息队列设计时的优化思路

- 重复消息过滤

- 互斥消息取消

- 复用消息

- 消息空闲IdleHandler      （消息队列空闲时间时才调用该handler）

  Looper.myQueue().addIdleHandler(idleHandler);

- 使用独享的Looper     handlerThread

  <img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/image-20210719184418119.png" style="zoom:50%;" />

#### Handler为什么会造成内存泄露？

handler创建的时候，是生成了一个内部类，内部类能直接使用外部类的属性和方法，内部类是默认持有外部类的引用，即activity。而在handler调用enqueueMessage方法时，将自己赋予了msg的target属性，所以msg是持有handler的引用的。如果某个消息因为延迟执行或者没有处理完成，消息会一直挂载。msg持有handler，handler持有activity导致activity无法回收，造成了内存泄露。

（Handler 允许我们发送**延时消息**，如果在延时期间用户关闭了 Activity，那么该 Activity 会泄露。

这个泄露是因为 Message 会持有 Handler，而又因为 **Java 的特性，内部类会持有外部类**，使得 Activity 会被 Handler 持有，这样最终就导致 Activity 泄露。）

解决方法：

将handler定义成静态的内部类，在内部持有activity的弱引用，并在activity的onDestroy()中调用handler.removeCallbacksAndMessages(null)及时
移除所有消息。

#### Hander机制采用的是什么设计模式？

采用的生产者/消费者的设计模式。子线程生成消息，主线程消费消息，MessageQueue为生产仓库。

#### Looper.prepare() :

Looper类提供了Looper.prepare()方法来创建Looper，并且会借助ThreadLoacl来实现与当前线程绑定的功能。Looper.loop()则会开始不断尝试从MessageQueue中获取Message，并分发给对应的Handler。

也就是Handler跟线程的关联是靠Looper来实现的。

####  Message 的分发与处理

Looper.loop()对事件进行分发。

涉及到的方法：

MessageQueue.next()

调用msg.target.dispatchMessage(msg)，这里的msg.target就是发送消息的Handler，这样就可以回调到了Handler那边去了：

tips：dispatchMessage()方法对Runnable的方法做了特殊的处理，如果是，则会直接执行Runnable.run()。否则回调到 Handler 的 handleMessage 方法。

分析：Looper.loop()是个死循环，会不断调用MessageQueue.next() 获取 Message ，并调用 `msg.target.dispatchMessage(msg)` 回到了 Handler 来分发消息，以此来完成消息的回调。

#### 线程切换

```java
Thread.foo(){
	Looper.loop()
	 -> MessageQueue.next()
 	  -> Message.target.dispatchMessage()
 	   -> Handler.handleMessage()
}
```

很明显，handler.handleMessage()方法是在调用Looper.loop()的线程所决定的。

平时我们用的时候从异步线程发送消息到 Handler，这个 Handler 的 `handleMessage()` 方法是在主线程调用的，所以消息就从异步线程切换到了主线程。

#### Handler的消息延时是怎么实现的？

handler在sendMessage()时可以加入需要延迟的时间，looper.loop（）在获取MessageQueue消息的时候会判断当前时间和消息的时间msg.when，如果小于则那就更新等待时间`nextPollTimeoutMillis`,等下次再做比较
如果时间到了，就取这个消息并返回。
如果没有消息，nextPollTimeoutMillis被赋为-1，这个循环又执行到`nativePollOnce`继续阻塞。

**https://blog.csdn.net/qq_38366777/article/details/108942036**
