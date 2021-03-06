## 1.Activity

#### 1.1生命周期

- onCreate：表示activity正在被创建
- onRestart：表示activity正在被重新启动
- onStart：表示activity正在被启动，这时已经可见，但没有出现在前台无法进行交互。
- onResume：表示activity已经可见，并且处于前台。
- onPause：表示activity正在停止（可做一次保存状态停止动画等非耗时操作）
- onStop：表示activity即将停止（可进行重量级回收工作）
- onDestroy：表示activity即将被销毁

![在这里插入图片描述](图片/Untitled.assets/2464105a73a045fdbe5c9776c0134b9b~tplv-k3u1fbpfcp-zoom-1.image)

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

## 2.Fragment

### 2.1简介

fragment有如下几个核心的类：

- Fragment：Fragment的基类，任何创建的Fragment都需要继承该类。
- FragmentManager：管理和维护Fragment。抽象类，具体的实现类是FragmentManagerImpl。
- FragmentTransaction：对Frgament的添加、删除等操作都需要通过事务方式进行。抽象类，具体实现类是BackStackRecord。

### 2.2生命周期

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
