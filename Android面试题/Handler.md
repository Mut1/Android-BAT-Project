
### 面试题

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





