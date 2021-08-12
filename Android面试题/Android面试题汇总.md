
### 1.Activity
#### 1.1生命周期
- onCreate：表示activity正在被创建
- onRestart：表示activity正在被重新启动
- onStart：表示activity正在被启动，这时已经可见，但没有出现在前台无法进行交互。
- onResume：表示activity已经可见，并且处于前台。
- onPause：表示activity正在停止（可做一次保存状态停止动画等非耗时操作）
- onStop：表示activity即将停止（可进行重量级回收工作）
- onDestroy：表示activity即将被销毁

对于生命周期，通常还会问如下的一些问题：

- 第一次启动：onCreate->onStart->onResume；
- 打开新的Activity或者返回桌面：onPause->onStop。如果打开新的Activity为透明主题，则不会调用onStop；
- 当回到原来Activity时：onRestart->onStart->onResume;
- 当按下返回键：onPause->onStop->onDestroy

#### 1.2启动模式

activity四种启动模式：Standard、SingleTop、SingleTask和SingleInstance

- **Standard：**标准模式，也是默认模式。每次启动都会创建一个全新的实例。
- **SingleTop：**栈顶复用模式。这种模式下如果activity位于栈顶，不会新建实例。onNewIntent会被调用，接收新的请求信息，不会再调用onCreate和onStart。
- **SingleTask：**栈内复用模式。升级版SingleTop，如果栈内有实例，则复用，并会降该实例之上的activity全部清除。
- **SingleInstance：**系统会为它创建一个单独的任务栈，并且这个实例独立运行在一个task中，这个task只有这个实例，不允许有别的activity存在（可以理解为手机内只有一个）

#### 1.3启动流程

在理解activity的启动流程之前，先让我们来看一下Android系统启动流程。总的来说，Android系统启动流程的主要经历init进程->Zygote进程->SystemServer进程->各种系统服务->应用进程等阶段。

**1.启动电源以及系统启动：**当电源按下时引导芯片从预定义的地方（固化在ROM）开始执行，加载引导程序BootLoader到RAM，然后执行。

**2.引导程序BootLoader：**BootLoader是在Android系统开始运行前的一个小程序,主要用于把系统OS拉起来并运行。

**3.Linux内核启动：**当内核启动时，设置缓存、被保护存储器、计划列表、加载驱动。当其完成系统设置时，会先在系统文件中寻找init.rc文件，并启动init进程。

**4.init进程启动：**初始化和启动属性服务，并且启动Zygote进程。

**5.SystemServer进程启动**：启动Binder线程池和SystemServiceManager，并且启动各种系统服务。

**6.Launcher启动**：被SystemServer进程启动的AMS会启动Launcher，Launcher启动后会将已安装应用的快捷图标显示到系统桌面上。

Launcher进程启动后，就会调用activity的启动了。首先，Launcher会调用ActivityTaskManagerService，然后ActivityTaskManagerService会调用ApplocationThread，然后ApplicationThread再通过ActivityThread启动Activity。

#### 1.4切换横竖屏

- 动态切换横竖屏 setRequestedOrientation(Activityinfo.SCREEN_ORIENTATION_LANDSCAPE(PORTRAIT));
  
- AndroidManifest不设置时
  - 竖屏->横屏/横屏-->竖屏
    onPause-->
    onStop-->
    onDestory-->
    onCreate-->
    onStart-->
    onRestoreInstanceState-->
    onResume-->
  
- AndroidManifest设置android:configChanges='orientation'
  - 竖屏->横屏/横屏-->竖屏
    onPause-->
    onStop-->
    onDestory-->
    onCreate-->
    onStart-->
    onRestoreInstanceState-->
    onResume-->
  
- AndroidManifest设置android:configChanges='orientation|screenSize'或者android:configChanges='orientation|keyboardHidden|screenSize'
  
   - 竖屏->横屏/横屏-->竖屏
     
      onConfigChanged-->
#### 1.5tips
- 当前Activity产生事件弹出Toast和AlertDIalog的时候Activity的生命周期不会有改变

- Activity运行时按下HOME键（跟被完全覆盖一样）

  onPause-->onStop  onRestart-->onStart-->onResume

- Activity未被完全覆盖只是失去焦点：

  onPause-->onResume

#### 1.6Android怎么加速启动Activity？
- onCreate()中不执行耗时操作
  把页面显示的 View 细分一下，放在 AsyncTask 里逐步显示，用 Handler 更好。这样用户的看到的就是有层次有步骤的一个个的 View 的展示，不会是先看到一个黑屏，然后一下显示所有 View。最好做成动画，效果更自然。

- 利用多线程的目的就是尽可能的减少onCreate()和onResume()的时间，使得用户能尽快看到页面，操作页面
- 减少主线程阻塞时间
- 提高Adapter和AdapterView的效率
- ⭐️优化布局文件

#### [1.7优化布局文件](https://juejin.cn/post/6844903657310257159#heading-8)

- 删除布局中无用的控件和层级

- 选择耗费性能较少的布局

  - 性能耗费低的布局 = 功能简单 = FrameLayout、LinearLayout
  - 性能耗费高的布局 = 功能复杂 = RelativeLayout（ConstraintLayout）
  - 嵌套所耗费的性能 > 单个布局本身耗费的性能

- 提高布局复用性（使用<include>布局标签）通过提高布局的复用性减少测量 & 绘制时间

- 减少布局的层级(使用 <merge> 布局标签)

- 减少初次测量&绘制时间

  - <ViewStub>

  - 尽可能少用布局属性wrap_content

    布局属性 wrap_content 会增加布局测量时计算成本，应尽可能少用

- 减少控件的使用（善用控件属性

  - TextView文字加图片
  - LinearLayout分割线    android:divider="@drawable/divider_line"
  - TextView的行间距和占位符的使用

### 2.Fragment

#### 2.1简介

fragment有如下几个核心的类：

- Fragment：Fragment的基类，任何创建的Fragment都需要继承该类。
- FragmentManager：管理和维护Fragment。抽象类，具体的实现类是FragmentManagerImpl。
- FragmentTransaction：对Frgament的添加、删除等操作都需要通过事务方式进行。抽象类，具体实现类是BackStackRecord。

#### 2.2生命周期

Fragment必须是依存于Activity而存在的，因此Activity的生命周期会直接影响到Fragment的生命周期。相比于Activity的生命周期，Fragment的生命周期如下所示。

- onAttach()：Fragment和Activity相关联时调用。如果不是一定要使用具体的宿主Activity对象的话，可以使用这个方法或者getContext()获取Context对象，用于解决Context上下文引用的问题。同时还可以在此方法中通过getArgument()获取到需要在Fragment创建时需要的参数。
- onCreate()：Fragment被创建时调用。
- onCreateView()：创建Fragment的布局。
- onActivityCreated()：当Activity完成oncreate()时调用。
- onStart():当Fragment可见时调用。
- onResume():当Fragment可见且可交互时调用
- onPause():当Fragment不可交互但可见时调用
- onStop():当Fragment不可见时调用
- onDestroyView():当Fragment的UI从视图结构中移除时调用
- onDestroy():销毁Fragment时调用
- onDetach():当Fragment和Activity解除关联时调用。

#### 2.3与Activity传递数据

##### 2.3.1Fragment向Activity传递数据

首先，
##### 2.4Fragment[[懒加载]]

### 3.Contex

#### 1.关于getContext()、getApplication()、getApplicationContext()、getActivity()的区别：

1. getContext():获取到当前对象的上下文。
2. getApplication():获得Application的对象
3. getApplicationContext():获得应用程序的上下文。有且仅有一个相同的对象。生命周期随着应用程序的摧毁而销毁。
4. getActivity():获得Fragment依附的Activity对象。Fragment里边的getActivity()不推荐使用原因如下：这个方法会返回当前Fragment所附加的Activity，当Fragment生命周期结束并销毁时，getActivity()返回的是null，所以在使用时要注意判断null或者捕获空指针异常。所以只要判断getActivity()为空，就可以不再执行下面的代码，这完全不影响业务的使用。

#### 2.一个应用程序有几你们 个Context？

Context数量=Activity数量+Service数量+1

#### 3.Context能干什么？

比如：弹出Toast、启动Activity、启动Service、发送广播、操作数据库等等都需要用到Context。

#### 4.Context作用域？

一句话总结：凡是跟UI相关的，都应该使用Activity做为Context来处理；其他的一些操作，Service,Activity,Application等实例都可以，当然了，注意Context引用的持有，防止内存泄漏。

#### 5.如何获取Context?

通常我们想要获取Context对象，主要有以下四种方法

1. View.getContext,返回当前View对象的Context对象，通常是当前正在展示的Activity对象。
2. Activity.getApplicationContext,获取当前Activity所在的(应用)进程的Context对象，通常我们使用Context对象时，要优先考虑这个全局的进程Context。
3. ContextWrapper.getBaseContext():用来获取一个ContextWrapper进行装饰之前的Context，可以使用这个方法，这个方法在实际开发中使用并不多，也不建议使用。
4. Activity.this 返回当前的Activity实例，如果是UI控件需要使用Activity作为Context对象，但是默认的Toast实际上使用ApplicationContext也可以。

##### **getApplication()和getApplicationContext()**

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/1240.jpeg" style="zoom:50%;" />

Application本身就是一个Context，所以这里获取getApplicationContext()得到的结果就是Application本身的实例。

- getApplication只有在Activity和Service中才能调用的到。
- 在一些其它的场景，比如BroadcastReceiver中也想获得Application的实例，这时就可以借助getApplicationContext()方法了。

#### Context引起的内存泄露

**错误的单例模式**

**View持有Activity引用**

```java
public class MainActivity extends Activity {
    private static Drawable mDrawable;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = new ImageView(this);
        mDrawable = getResources().getDrawable(R.drawable.ic_launcher);
        iv.setImageDrawable(mDrawable);
    }
}
```

有一个静态的Drawable对象，当ImageView设置这个Drawable时，ImageView保存了mDrawable的引用，而ImageView传入的this是MainActivity的mContext，因为被static修饰的mDrawable是常驻内存的，MainActivity是它的间接引用，MainActivity被销毁时，也不能被GC掉，所以造成内存泄漏。

#### **正确使用Context**

一般Context造成的内存泄露，几乎都是当Context销毁的时候，却因为被引用导致销毁失败，而Application的Context对象可以理解为随着进程存在的，所以可以总结出使用Context的正确姿势：

1. 当application的Context能搞定的情况下，并且生命周期长的对象，**优先使用Application的Context。**
2. 不要让生命周期长于Activity的对象持有到Activity的引用
3. 尽量不要在Activity中使用非静态内部类，因为非静态内部类会隐式持有外部类实例的引用，如果使用静态内部类，将外部实例引用作为弱引用持有。

#### Tips：

1. 创建对话框时不可以用Application的context，只能用Activity的context

### 4.Handler[[Android面试题/Handler]]

#### Handler引起的内存泄露原因以及最佳解决方案

Handler允许我们发送延迟消息，如果在延迟期间用户关闭了Activity，那么该Activity就会泄露。

这个泄露是因为Message会持有Handler，而又因为Java的特效，内部类会持有外部类，使得Activity会被Handler持有，这样最终就导致Activity泄露。

解决：将Handler定义成静态的内部类，在内部持有Activity的弱引用，并在Activity的onDestroy()中调用

handler.removeCallbacksAndMessages(null)及时移除所有消息。

#### 为什么我们能在主线程直接使用Handler，而不需要创建Looper？

因为在ActivityThread.main()方法中调用了Looper.prepareMainLooper()方法创建了主线程中的Looper，并且调用Looper()方法，所以我们就可以直接使用Handler了。

#### 主线程的Looper不允许退出

主线程不允许退出，退出就意味APP要挂

#### Handler里藏着Callback能干什么？

Handler.Callback有优先处理消息的权利，当一条消息被Callback处理并拦截（返回true），那么Handler的handleMessage(msg)方法就不会被调用；如果Callback处理了消息，但并没有被拦截，那么就意味着一个消息可以同时被Callback以及Handler处理。

#### 创建Message实例的最佳方式

尽量复用Message，减少内存消耗：

- 通过Message的静态方法Message.obtain()创建message;
- 通过Handler的公有方法handler.obtainMessage().

#### 子线程里弹Toast的正确姿势

本质上是因为Toast的实现依赖于Handler，按子线程使用Handler的要求修改即可，同理还有Dialog(**吐司操作的是window**)

```java
Looper.prepare();

Toast....

Looper.loop();
```

#### 妙用Looper机制

- 将Runnable post到主线程执行
- 利用Looper判断当前线程是否是主线程。

```java
private static final Handler  mHandler = new Handler(Looper.getMainLooper());

public static void run(Runnable runnable){
  if(isMainThread())
    	runnable.run();
  else
    mHandler.post(runnable);
}

public static boolean isMainThread(){
  return  Looper.myLooper()==Looper.getMainLooper();
} 
```

#### 总结：

1. Handler的背后有Looper、MessageQueue支撑，Looper负责消息分发，MessageQueue负责消息管理；
2. 在创建Handler之前一定要先创建Looper；
3. Looper有退出功能，但是主线程的Looper不允许退出；
4. 异步线程的Looper需要自己调用Looper.myLooper().quit;退出
5. Runnable被封装进了Message，可以说是一个特殊的Message
6. Handler.handleMessage()所在的线程是Looper.loop()方法被调用的线程，也可以说成Looper所在的线程，并不是创建Hadnler的线程
7. 使用内部类的方式使用Handler可能会导致内存泄露，即便在Activity.onDestroy里移除延时消息，必须要写成静态内部类；

优秀文章：

[Handler 都没搞懂，拿什么去跳槽啊？](https://juejin.cn/post/6844903783139393550#heading-15)
[你真的懂Handler吗？Handler问答](https://juejin.cn/post/6844903624510799886)


### 5.[[BitMap]]
### 6.[[事件分发]]
### 7.[[Glide]]
### Questions

#### 类的初始化顺序依次是：

（静态变量、静态代码块）>(变量、代码块)>构造方法

#### 通过Gson解析json时，定义javaBean的规则是什么？

1. 实现序列化Serializable （不继承，会默认生成SerializableId）
2. 属性私有化，并提供get，set方法
3. 提供无参构造
4. 属性名必须与json串中的属性名保持一致（因为Gson解析json串底层用到了java的反射原理）

### 杂项
#### 接口回调
#### Serializable和Parcelable的区别