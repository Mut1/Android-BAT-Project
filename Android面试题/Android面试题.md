#### 请你说一下touch事件在控件里面的传递过程

三个关键方法：

dispatchTouchEvent()、onInterceptTouchEvent()、onTouchEvent();

只有ViewGroup才能实现onInterceptTouchEvent()；而View则和Activity一样，也只实现了dispatchTouchEvent()、onTouchEvent()。

从结果来看，事件传递就是从activity传向ViewGroup，然后如果ViewGroup不拦截事件，则又把事件传给View，最后View来处理事件，如果View处理不了，则又把事件往上传给ViewGroup，然后ViewGroup如果也处理不了，则又把事件传给activity，最后由activity处理。

#### Android生命周期

##### 场景生命周期流程

`onCreate -> onStart -> onResume -> onPause -> onStop -> onDestroy`

##### Activity切换

`Activity1:onPause
Activity2:onCreate -> onStart -> onResume
Activity1:onStop`

##### 屏幕旋转

`running -> onPause -> onStop -> onSaveInstanceState -> onDestroy

onCreate -> onStart -> onRestoreInstanceState -> onResume`

##### 后台应用被系统杀死

onDestroy

onCreate -> onStart -> onRestoreInstanceState -> onResume

##### 具有返回值的启动

onActivityResult -> onRestart -> onResume

##### 重复启动

onPause -> onNewIntent -> onResume

#### 项目里的懒加载

##### viewpager+tablayout

我使用的setUserVisibleHint

可以看到此时setUserVisibleHint的调用时机总是在**初始化时调用，可见时调用，由可见转换成不可见时调用。**

我们一般在Fragment的onActivityCreated中加载数据，这个时候我们可以判断此时的Fragment是否对用户可见，调用fragment.getUserVisibleHint()可以获得isVisibleToUser的值，如果为true，表示可见，就加载数据，如果不可见，就不加载数据了



