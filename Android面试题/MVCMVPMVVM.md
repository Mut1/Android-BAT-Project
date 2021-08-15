#### 优质文章

[正确认识 MVC/MVP/MVVM](https://mp.weixin.qq.com/s/h2i_17ChO9JsJvxq8tXwlA)

郭霖[**MVC、MVP、MVVM，谈谈我对 Android 应用架构的理解**](https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650242635&idx=1&sn=0b5f40fb5420a33ad5e42f3fafbfabb6&chksm=88638f24bf140632507d4908b14a686c642cfd9b773579c3101f5c123b9aee32e5366eaf4410&scene=38#wechat_redirect)

[mvc](https://qwerhuan.gitee.io/2020/08/16/android/android-jia-gou-zhi-mvc-mvp-mvvm-xiang-jie/)

https://wildma.github.io/blog/81ad7a6.html

#### Android 架构

Android架构，即为开发Android时使用的架构。Android的开发一般分为三个部分：UI逻辑、业务逻辑和数据操作逻辑。

Android架构，就是为了更好地协调三者的关系。达到：

> 1. 各模块高内聚低耦合的状态，方便进行团队分工合作开发
> 2. 代码思路清晰，提高代码的可维护性与可测试性
> 3. 减少样板代码，提高开发效率，减少开发错误

#### MVC

<img src="https://s1.ax1x.com/2020/08/16/dER0C4.png" style="zoom:50%;" />

- View:负责与用户交汇，显示界面
- Controller：负责接收来自View的请求，处理业务逻辑
- Model：负责数据逻辑，网络请求数据以及本地数据库操作数据等

在MVC架构中，Controller是业务的主要承载者，几乎所有的业务逻辑都在Controller中进行编写。而View主要负责UI逻辑，而Model是数据逻辑，彼此分工。

在Android中，view一般使用xml进行编写，但xml的能力不全面，需要Activity进行一些UI逻辑的编写，因而MVC中的V即为xml+Activity。

Model数据层，在Android中负责网络请求和数据库操作，并向外暴露接口。

Controller是争议比较多的写法：一种是直接把Activity当成Controller；一种是独立出Controller类，进行逻辑分离。

MVC架构的处理流程一般是：

- view接收用户的点击
- view请求controller进行处理或直接去model获取数据
- controller请求model获取数据，进行其他的业务操作
  - 这一步可以有多种做法：
    - 利用callBack从controller进行回调
    - 把view实例给controller，让controller进行处理
    - 通知view去model获取数据

##### 缺点：

- 几乎所有的业务逻辑代码都在controller中进行，会导致非常臃肿，降低项目的可测试性与可维护性。
- view直接持有controller和model实例，不同职责的代码进行耦合，导致代码耦合性高，模块分工不清晰。

##### 优点：

- 简单

##### 改进方向：

- 对模块进行更加彻底的分离，不要让view和model直接联系。
- 对controller进行减压。

#### MVP

相比MVC，MVP的更加的完善。MVP全名是Model-View-Presenter。图解如下：

<img src="https://s1.ax1x.com/2020/08/16/dETLCV.png" style="zoom:50%;" />

- View：UI模块，负责界面显示和与用户交汇。
- Presenter：负责业务逻辑，起着连接View和Model桥梁的作用。
- Model：专注于数据逻辑。

MVP和MVC的区别很明显就在这个Presenter中。为了解决MVC中代码的耦合严重性，把业务逻辑都抽离到了Presenter中。这样**View和Model完全被隔离，实现了单向依赖，大大减少了耦合度**。view和prensenter之间通过接口来通信，只要定义好接口，那么团队可以合作**同时开发不同的模块，同时不同的模块也可以进行独立测试**。也因各模块独立了，所以要只要符合接口规范，即可做到动态更换模块而**不需要修改其他的模块。**

在Android中，需要让Activity提供控件的更新接口，prensenter提供业务逻辑接口，Activity持有presenter的实例，prensenter持有Activity的弱引用（不用直接引用是为了避免内存泄露），Activity直接调用prensenter的方法更新界面，prensenter去model获取数据之后，通过view的接口更新view。如下图：

<img src="https://s1.ax1x.com/2020/08/16/dE7aan.png" alt="dE7aan" style="zoom:50%;" />

不同的view可以通过实现相同的接口来共享prensenter。presenter也可以通过实现接口来实现动态更换逻辑。Model是完全独立开发的，向外暴露的方法参数中含有callBack参数，可以直接调用callBack进行回调。

##### 总结：

- MVP通过模块职责分工，抽离业务逻辑，降低代码的耦合性
- 实现模块间的单向依赖，代码思路清晰，提高可维护性
- 模块间通过接口进行通信，降低了模块间的耦合度，可以实现不同模块独立开发或动态更换

##### 缺点：

MVP的最大特点就是接口通信，接口的作用是为了实现模块间的独立开发，模块代码复用以及模块的动态更换。但是我们会发现后两个特性，在Android开发中使用的机会非常少。presenter的作用就是接受view的请求，然后再model中获取数据后调用view的方法进行展示，但是每个界面都是不同的，很少可以共用模块的情景出现。这就导致了每个Activity/Fragment都必须写一个IView接口，然后还需要再写个IPresenter接口，从而产生了非常多的接口，需要编写大量的代码来进行解耦。如果在小型的项目，这样反而会大大降低了开发效率。
其次，prensenter并没有真正解耦，他还需要调用view的接口进行UI操作，解耦没有彻底。MVP也没有解决MVC中Controller代码臃肿的问题，甚至还把部分的UI操作带到了Presenter中。

- 过度设计导致接口过多，编写大量的代码来实现模块解耦，降低了开发效率
- 并没有彻底进行解耦，prensenter需要同时处理UI逻辑和业务逻辑，presenter臃肿

##### MVVM

MVVM，全名为Model-View-ViewModel。图解：<img src="https://s1.ax1x.com/2020/08/17/dVONJs.png" alt="dVONJs" style="zoom:50%;" />

- View：和前面的MVP、MVC中的View一样，负责UI界面的显示以及与用户的交汇。
- Model：同样是负责网络数据获取或者本地数据库数据获取。
- ViewModel：负责存储view的数据映像以及业务逻辑。

MVVM的view和model和前面的两种架构模式是差不多的，重点在ViewModel。viewModel通过将数据和view进行绑定，修改数据会直接反映到view上，通过**数据驱动型思想**，彻底把MVP中的Presenter的UI操作逻辑给去掉了。而viewModel是绑定于单独的view的，也就不需要进行编写接口了。但viewModel中依旧有很多的业务逻辑，但是因为把**view和数据进行绑定**，这样可以让view和业务彻底的解耦了。view可以专注于UI操作，而viewModel可以专注于业务操作。因而：

> MVVM通过数据驱动型思想，彻底把业务和UI逻辑进行解耦，各模块分工职责明确。

View只需要关注Viewmodel的数据部分，而无需知道数据是怎么来的；而ViewModel只需要关注数据逻辑，而不需要知道UI是如何实现的。View可以随意更换UI实现，但ViewModel却完全不需要改变。

但依旧存在的问题是：viewModel会依旧很臃肿；需要一个绑定框架来对view和数据对象进行绑定。这是MVVM的两大弊端。上面的两种架构模式都是不需要框架的，但MVVM必须要有一个view-data绑定框架，来实现对data的更改可以实时反映到view上，这就造成了需要有一定的上手难度：学习框架。

##### 缺点：

- MVVM的viewModel依旧很臃肿。
- MVVM需要学习数据绑定框架，具有一定的上手难度。
