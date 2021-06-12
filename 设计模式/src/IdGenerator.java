import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式
 * <p>
 * <p>
 * 懒汉式
 */
//public class IdGenerator{
//    private AtomicLong id = new AtomicLong(0);
//    private static final IdGenerator instance=new IdGenerator();
//    private IdGenerator(){}
//    public static IdGenerator getInstance(){
//        return instance;
//    }
//}
/**
 *
 * 懒汉式
 */
//public class IdGenerator{
//    private AtomicLong id = new AtomicLong(0);
//    private static  IdGenerator instance;
//    private IdGenerator(){}
//    public static synchronized IdGenerator getInstance(){
//        if(instance==null){
//            instance=new IdGenerator();
//        }
//        return instance;
//    }
//}

/**
 *
 * 双重检测
 */
//public class IdGenerator {
//    private AtomicLong id = new AtomicLong(0);
//    private static IdGenerator instance;
//
//    private IdGenerator() {}
//
//    public static IdGenerator getInstance() {
//        if (instance == null) {
//            synchronized (IdGenerator.class) { //此处为类级别的锁
//                if (instance == null) {
//                    instance = new IdGenerator();
//                }
//            }
//        }
//        return instance;
//    }
//}

/**
 *
 * 静态内部类
 */
//public class IdGenerator {
//    private AtomicLong id = new AtomicLong(0);
//
//    private IdGenerator() {}
//
//    public static class SingletonHolder{
//        private static final IdGenerator instance=new IdGenerator();
//    }
//    public static IdGenerator getInstance(){
//        return SingletonHolder.instance;
//    }
//}

/**
 *
 * 枚举
 */
public enum IdGenerator  {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId(){
        return id.incrementAndGet();
    }

}

