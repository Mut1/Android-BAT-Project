#### 字符型常量和字符串常量的区别

1. 形式上：字符常量是单引号引起的一个字符，字符串常量是双引号引起的若干个字符
2. 含以上：字符常量相当于一个整形值(ASCII值)，可以参加表达式运算。字符串常量代表一个地址值（该字符串在内存中存放的位置）
3. 占内存大小：字符常量只占两个字节，字符串常量占若干个字节（至少一个字符结束标志）

#### 构造器Constructor是否可被override

父类的私有属性和构造方法并不能被继承，所以 Constructor 也就不能被 override（重写）,但是可以 overload（重载）,所以 你可以看到一个类中有多个构造函数的情况。

#### 重载和重写的区别

重载：发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同，发生在编译时。

重写：发生在父子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类；如果父类方法访问修饰符为private，则子类就不能重写该方法。

#### Java 面向对象编程三大特性: 封装 继承 多态

#### String StringBuffer 和 StringBuilder 的区别 是什么 String 为什么是不可变的

##### 可变性

String类中使用final关键字字符数组保存字符串，private final char value[]，所以String对象是不可变的。而StringBuilder 与 StringBuffer 都继承自 AbstractStringBuilder 类，在 AbstractStringBuilder 中 也是使用字符数组保存字符串 char[]value 但是没有用 final 关键字修饰，所以 这两种对象都是可变的。

##### 线程安全性

String中的对象是不可变的，也就可以理解为常量，线程安全。

AbstractStringBuilder 是 StringBuilder 与 StringBuffer 的公共父类，定义了 一些字符串的基本操作，如 expandCapacity、append、insert、indexOf 等公 共方法。

StringBuffer 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。

StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。 　　

##### 性能

每次对String类型进行改变的时候，都会产生一个新的String对象，然后将指针指向新的String对象。

StringBuffer每次都会对StringBuffer对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 StringBuilder 相比使用 StringBuffer 仅能获得 10%~15% 左右的性能提升， 但却要冒多线程不安全的风险。
#### intern
https://qwerhuan.gitee.io/2020/12/18/java/java-zhi-string-chong-dian-jie-xi/

##### 总结

1. 操作少量数据   String
2. 单线程操作字符串，缓冲区下操作大量数据   StringBuilder
3. 多线程操作字符串，缓冲区下操作大量数据   StringBuffer

#### 自动装箱与拆箱

装箱：将基本类型用它们对应的引用类型包装起来

拆箱：将包装类型转换为基本数据类型

#### 接口和抽象类的区别是什么？

1. 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始 接口方法可以有默认实现），抽象类可以有非抽象的方法

2. 接口中的实例变量默认是 final 类型的，而抽象类中则不一定
3. 一个类可以实现多个接口，但最多只能实现一个抽象类
4. 一个类实现接口的话要实现接口的所有方法，而抽象类不一定
5. 接口不能用 new 实例化，但可以声明，但是必须引用一个实现该接口 的对象 从设计层面来说，抽象是对类的抽象，是一种模板设计，接口是 行为的抽象，是一种行为的规范。

#### 成员变量与局部变量的区别有那些

1. 从语法形式上，成员变量是属于类的，而局部变量是在方法中定义的 变量或是方法的参数；成员变量可以被 public,private,static 等修饰符所 修饰，而局部变量不能被访问控制修饰符及 static 所修饰；但是，成员 变量和局部变量都能被 final 所修饰；

2. 从变量在内存中的存储方式来看，成员变量是对象的一部分，而对象存 在于堆内存，局部变量存在于栈内存 
3. 从变量在内存中的生存时间上看，成员变量是对象的一部分，它随着对 象的创建而存在，而局部变量随着方法的调用而自动消失。
4. 成员变量如果没有被赋初值，则会自动以类型的默认值而赋值（一种情 况例外被 final 修饰的成员变量也必须显示地赋值）；而局部变量则不会自动赋值。

#### 对象的相等与指向他们的引用相等，两者有什么不同？

对象的相等，比的是内存中存放的==内容==是否相等。而引用相等，比较的是他们==指向的内存地址==是否相等。

#### == 与 equals(重要)

== : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象。(基本数据类型 == 比较的是值，引用数据类型 == 比较的是内存地址)

equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况：

- 情况 1：类没有覆盖 equals() 方法。则通过 equals() 比较该类的两个对象时，等价于通过“==”比较这两个对象。 

- 情况 2：类覆盖了 equals() 方法。一般，我们都覆盖 equals() 方法来 两个对象的内容相等；若它们的内容相等，则返回 true (即，认为这两 个对象相等)。

```java
public class test1 {

public static void main(String[] args) { 
  String a = new String("ab"); // a 为一个引用 
  String b = new String("ab"); // b 为另一个引用,对象的内容一样

String aa = "ab"; // 放在常量池中

String bb = "ab"; // 从常量池中查找

if (aa == bb) // true

System.out.println("aa==bb");

if (a == b) // false，非同一对象

System.out.println("a==b");

if (a.equals(b)) // true System.out.println("aEQb"); if (42 == 42.0) { // true

System.out.println("true"); }

}
}
```

说明：

- String 中的 equals 方法是被重写过的，因为 object 的 equals 方法是 比较的对象的内存地址，而 String 的 equals 方法比较的是对象的值。 
- 当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。

#### [hashCode 与 equals（重要）](https://juejin.cn/post/6844903854639693837)

##### hashCode（）

hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个 int 整数。

这个哈希码的作用是确定该对象在哈希表中的索引位置。hashCode() 定义在JDK的 Object.java 中，这就意味着 Java 中的任何类都包含有 hashCode() 函数。 散列表存储的是键值对(key-value)，它的特点是：能根据“键”快速的检索出对应 的“值”。这其中就利用到了散列码！（可以快速找到所需要的对象）

##### 为什么要有 hashCode

可以先比较hashcode的值，再进行equals，提高了执行速度

##### hashCode（）与 equals（）的相关规定

1. 如果两个对象相等，则hashcode一定也是相同的

2. 两个对象相等，对两个对象分别调用equals方法则返回true

3. 两个对象有相同的hashcode值，它们也不一定相等

4. 因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖

5. hashCode() 的默认行为是对堆上的对象产生独特值。如果没有重写

   hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）

   ![img](https://user-gold-cdn.xitu.io/2019/5/27/16af8aa4340aaf42?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

#### 两个值相等的 Integer 对象，== 比较，判断是否相等？

不一定相等

```java
Integer a1 = 127;
Integer b1 = 127;
if(a1==b1){
    System.out.println("相等");
}else{
    System.out.println("不等");
}
Integer a = 128;
Integer b = 128;
if(a==b){
    System.out.println("相等");
}else{
    System.out.println("不等");
}
```

JVM会自动维护八种基本类型的常量池，int常量池中初始化[-128,127]的范围，所以当为Integer i=127时，在自动装箱过程中是取自常量池中的数值，而当Integer i=128时，128不在常量池范围内，所以在自动装箱过程中需new 128，所以地址不一样。

对于Integer来说，你用==比较的是对象引用地址，而不是Integer的值。Integer你要把当当成一个对象来看待

要比较两个Integer类型的大小可以调用它的intValue方法 Long 对应的是longValue，这个其实在api就是调用强转的方法

#### 为什么 Java 中只有值传递

> 为什么说java里面只有值传递？ - 沉默王二的回答 - 知乎
> https://www.zhihu.com/question/385114001/answer/1393887646

### 线程问题[[线程通信]]

#### 简述线程，程序、进程的基本概念。以及他们之 间关系是什么？

#### 线程有哪些基本状态?

### [[Map大家族]]

### 题目：

1. 数字类型直接量
   直接量是在程序中直接出现的常量值。

   将整数类型的直接量赋值给整数类型的变量时，只要直接量没有超出变量的取值范围，即可直接赋值，如果直接量超出了变量的取值范围，则会导致编译错误。

   整数类型的直接量默认是 int 类型，如果直接量超出了 int 类型的取值范围，则必须在其后面加上字母 L 或 l，将直接量显性声明为 long 类型，否则会导致编译错误。

   浮点类型的直接量默认是 double 类型，如果要将直接量表示成 float 类型，则必须在其后面加上字母 F 或 f。将 double 类型的直接量赋值给 float 类型的变量是不允许的，会导致编译错误。![](https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/20210808000639.png)

