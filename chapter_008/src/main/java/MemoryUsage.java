import java.lang.ref.SoftReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryUsage {
    private final Logger log = LoggerFactory.getLogger(MemoryUsage.class);

    public static void main(String[] args) {
        System.out.println("start");
        // info();
        System.out.println("Создать объект User c полями и перекрытым методом finalize");
        User user = new User("test");
        System.out.println(user);
        info();
        user = null;
        System.gc();
        info();
        //   SoftReference<User> user = new SoftReference(new User("test"));
        //    System.out.println(User.get().name);

        //   System.gc();
        //    System.out.println("finish");
        // info();

    }

    public static void info() {
        int mb = 1024*1024;

        Runtime runtime = Runtime.getRuntime();
        System.out.println("###### Heap utilization statistics (MB) ######");
        System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()));
        System.out.println("Free Memory:" + runtime.freeMemory());
        System.out.println("Total Memory:" + runtime.totalMemory());
        System.out.println("Max Memory:" + runtime.maxMemory());
        System.out.println("#################################");
    }


    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class EmptyUser {

        public EmptyUser() {
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "EmptyUser{}" + this;
        }
    }
}
