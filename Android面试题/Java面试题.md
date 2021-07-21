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