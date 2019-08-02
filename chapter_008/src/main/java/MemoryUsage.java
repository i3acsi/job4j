import java.lang.ref.SoftReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;


public class MemoryUsage {
    //private final Logger log = LoggerFactory.getLogger(MemoryUsage.class);

    public static void main(String[] args) {
        System.out.println("start");
        info();
        System.out.println("create long delta: ");
        long delta;
        info();
        System.out.println("create long free: ");
        long free;
        info();

        long nFree;
        System.out.println("create long nFree:");
        info();
        System.out.println("++++++");
   //     System.out.println("Создать объект User c полями и перекрытым методом finalize");
       // System.out.println("Создаем и инициализирум строку = System.lineSeparator");
       // String ln = System.lineSeparator();
       // showFree();

       // int size = 10;
       // System.out.printf("создаем переменную int и инициализируем ее = %d %s", size, ln);
       // showFree();
        info();
        System.out.println("declare array of User");
        User[] users;
        info();
        System.out.println("init the array");
        users = new User[7000];
        info();
        //System.out.printf("Создаем массив User размера %d %s", 10, ln);
        free = showFree();
        System.out.println(free);
        System.out.println("///////////////");
        EmptyUser[] emptyUsers = new EmptyUser[7000];
        System.out.println("create array of Empty user");
        free = showFree();
        System.out.println(free);
        System.out.println("///////////////");

//        Unsafe u = T.get();
//
//        long obj = T.o2a(new User("test"));
//        for (int i = 0; i < 48; i++) {
//            System.out.print(u.getByte(obj + i) + " ");
//        }
//
//        System.out.println("");
//
//        Unsafe u2 = T.get();
//
//        long obj2 = T.o2a(new EmptyUser());
//        for (int i = 0; i < 40; i++) {
//            System.out.print(u2.getByte(obj2 + i) + " ");
//        }
//
//        System.out.println("");

        for (int i = 0; i < 7000; i++) {
            System.out.println("create user" + i);
            users[i] = new User("test");
            nFree = showFree();
            System.out.println(nFree);
            if (nFree != free) {
                delta = nFree-free;
                System.out.println("\t\t\t\t\t" + delta);
                System.out.println("\t\t\t\t\t*****" + i + "*******");
                free = nFree;
                if (delta<0) {
                    info();
                    for (int j = 0; j < i; j++) {
                        if (users[j]==null) {
                            System.out.println("user"+j+"is null");
                        }
                    }
                }
            }

        }

        for (int i = 0; i < 7000; i++) {
            System.out.println("create emptyUser" + i);
            emptyUsers[i] = new EmptyUser();
            nFree = showFree();
            System.out.println(nFree);
            if (nFree != free) {
                delta = nFree-free;
                System.out.println("\t\t\t\t\t" + delta);
                System.out.println("\t\t\t\t\t*****" + i + "*******");
                free = nFree;
                if (delta<0) {
                    info();
                }
            }
        }

        System.out.println("Приравниваем всем ссылкам users значение null");
        for (User us: users) {
            us = null;
        }
        users = null;
        System.out.println("Вызов System.gc");
        System.gc();
        info();
        for (EmptyUser us: emptyUsers) {
            us = null;
        }
        emptyUsers = null;
        System.out.println("Вызов System.gc");
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
        System.out.println("###### Heap utilization statistics ######");
        System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()));
        System.out.println("Free Memory:" + runtime.freeMemory());
        System.out.println("Total Memory:" + runtime.totalMemory());
        System.out.println("Max Memory:" + runtime.maxMemory());
        System.out.println("#################################");
    }

    public static long showFree() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory());
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
            System.out.printf("finalize %s \n", this);
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' + this +
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
            System.out.printf("finalize %s \n", this);
        }

        @Override
        public String toString() {
            return "EmptyUser{}" + this;
        }
    }

}

