### 进程与线程

#### 进程与线程

#### CPU线程与操作系统线程

#### 线程是什么

### 多线程的使用

#### Thread 和 Runnable

##### Thread

```java
Thread thread = new Thread() { 
  @Override 
  public void run() { 
    System.out.println("Thread started!"); 
  } 
}; 
 thread.start();
```

##### Runnable

```java
Runnable runnable = new Runnable() { 
  @Override 
  public void run() { 
    System.out.println("Thread with Runnable started!"); } }; Thread thread = new Thread(runnable); 
thread.start();
```

#### ThreadFactory

```java
ThreadFactory factory = new ThreadFactory() { 
  int count = 0;

@Override 
  public Thread newThread(Runnable r) {
    count ++;
    return new Thread(r, "Thread-" + count); 
  }
};

Runnable runnable = new Runnable() { 
  @Override 
  public void run() {        System.out.println(Thread.currentThread().getName() + " started!");
            } 
};
Thread thread = factory.newThread(runnable); 
thread.start(); 
Thread thread1 = factory.newThread(runnable); 
thread1.start();
```

#### Executor 和线程池

- 常⽤：newCachedThreadPool()

```java
Runnable runnable = new Runnable() { 
  @Override 
  public void run() { 
    System.out.println("Thread with Runnable started!"); } };

Executor executor = Executors.newCachedThreadPool(); executor.execute(runnable); 
executor.execute(runnable); 
executor.execute(runnable);
```

- 短时批量处理：newFixedThreadPool()

```java
ExecutorService executor = Executors.newFixedThreadPool(20); 
for (Bitmap bitmap : bitmaps) { executor.execute(bitmapProcessor(bitmap)); 
  } 
executor.shutdown();
```

#### Callable 和 Future

```java
Callable<String> callable = new Callable<String>() {

@Override 
  public String call() {

try { 
  Thread.sleep(1500); 
} catch (InterruptedException e) {
     e.printStackTrace(); } 
    return "Done!";
}
};
ExecutorService executor = Executors.newCachedThreadPool();
Future<String> future = executor.submit(callable);
try { 
  String result = future.get(); 
  System.out.println("result: " + result); } 
catch (InterruptedException | ExecutionException e) {
e.printStackTrace(); }
```

### 线程同步与线程安全

#### synchronized

##### synchronized方法

```java
private synchronized void count(int newValue) {
		 x = newValue; 
		 y = newValue; 
			  if (x != y) { 
 					   System.out.println("x: " + x + ", y:" + y); 
  }

}
```

##### synchronized代码块

```java 
private void count(int newValue) {
synchronized (this) 
{     x = newValue; 
      y = newValue; 
    if (x != y) { 
      System.out.println("x: " + x + ", y:" + y); }
}
}
```

```java
Object monitor1 = new Object();
synchronized (monitor1) {     
      synchronized (monitor2) { 
           name = x + "-" + y; 
      } 
    }
```

##### synchronized的本质

- 保证方法内部或代码块内部资源（数据）的**互斥访问**。即同一时间、由同一个Monitor监视的代码，最多只能有一个线程在访问。

- 保证线程之间对监视资源的**数据同步**。即，任何线程在获取到Monitor后的第一时间，会先将共享内存中的数据复制到自己的缓存中；任何线程在释放monitor的第一时间，会先将缓存中的数据复制到共享内存中。

#####  死锁

#### volatile

```java
private volatile int a=0;
```

- 保证加了volatile关键字的字段的操作具有**原子性**和同步性，其中原子性相当于实现了针对单一字段的线程间互斥访问。因此volatile可以看做是简化版的synchronized。
- volatile 只对基本类型 (byte、char、short、int、long、ﬂoat、double、boolean) 的**赋值操作**和对象的**引⽤赋值**操作有效。

#### java.util.concurrent.atomic 包

- 下面有AtomicInteger AtomicBoolean等类，作用和volatile基本一致，可以看做是通用版的volatile。

```java
AtomicInteger atomicInteger = new AtomicInteger(0);

...

atomicInteger.getAndIncrement();
```

#### Lock/ReentrantReadWriteLock

- 同样是加锁机制。但使用方式更灵活，同时也更麻烦一些

```java
Lock lock = new ReentrantLock();

...

lock.lock(); 
try {

x++; } 
finally {
lock.unlock(); 
}
```

> ﬁnally 的作⽤：保证在⽅法提前结束或出现 Exception 的时候，依然能正常释放锁。

- ⼀般并不会只是使⽤ Lock ，⽽是会使⽤更复杂的锁，例如 ReadWriteLock 

```java
ReentrantReadWriteLock lock = new ReentrantReadWriteLock(); 
Lock readLock = lock.readLock(); 
Lock writeLock = lock.writeLock();

private int x = 0;

private void count() {

writeLock.lock(); 
  try {
     x++; } 
  finally {
writeLock.unlock(); }
}

private void print(int time) {
readLock.lock(); 
  try { 
    for (int i = 0; i < time; i++) { 
      System.out.print(x + " "); } 
    System.out.println(); } 
  finally { 
    readLock.unlock(); }

}
```

#### 线程安全问题的本质： 

在多个线程访问共同的资源时，在某**⼀个线程**对资源进⾏**写操作的中途**（写⼊已经开始，但还没 结束），**其他线程**对这个**写了⼀半的资源**进⾏了**读操作**，或者基于这个写了⼀半的资源进⾏了**写 操作**，导致出现数据错误。 

#### 锁机制的本质：

- 通过对共享资源进⾏访问限制，让同⼀时间只有⼀个线程可以访问资源，保证了数据的准确性。 

- 不论是线程安全问题，还是针对线程安全问题所衍⽣出的锁机制，它们的核⼼都在于共享的资 源，⽽不是某个⽅法或者某⼏⾏代码。