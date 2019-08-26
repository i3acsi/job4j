import org.jsoup.select.Evaluator;


import java.lang.ref.*;
import java.util.WeakHashMap;


public class MemoryUsage {

    private static final Runtime runtime = Runtime.getRuntime();


    public static void main(String[] args) {
        //System.gc();
//        long before, after;
//        String name ="name";

//        int c =0;
//        for (int i = 0; i < 1_000_000; i++) {
//            c+=(""+i).getBytes().length;
//        }
//        System.out.println(c);


//        WeakReference[] s = new WeakReference[1000_000];
//        before = (runtime.totalMemory() - runtime.freeMemory());
//        for (int i = 0; i < 1000_000; i++) {//EmptyUser u: users) {
//            s[i] = new WeakReference(""+i);//48 байт = 24 байта - String + 16 байт - byte[] +8?
//        }
//        after = (runtime.totalMemory() - runtime.freeMemory());
//        System.out.println((after-before)/1_000_000);

        Object obj = new Object();

        WeakReference ref = new WeakReference<>(obj);
        System.gc();
        System.out.println(ref.get().hashCode());
        Reference.reachabilityFence(obj);
        //System.gc();
//
//        User[] u = new User[1000_000];
//        before = (runtime.totalMemory() - runtime.freeMemory());
//        for (int i = 0; i < 1000_000; i++) {//EmptyUser u: users) {
//            u[i] = new User(name); //16 байт
//        }
//        after = (runtime.totalMemory() - runtime.freeMemory());
//        System.out.println((after-before)/1_000_000);
//        System.gc();
//
//        EmptyUser[] em = new EmptyUser[1000_000];
//        before = (runtime.totalMemory() - runtime.freeMemory());
//        for (int i = 0; i < 1000_000; i++) {//EmptyUser u: users) {
//            em[i] = new EmptyUser(); //8 байт
//        }
//        after = (runtime.totalMemory() - runtime.freeMemory());
//        System.out.println((after-before)/1_000_000);
//        System.gc();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


        public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

//        @SuppressWarnings("deprecation")
//        @Override
//        protected void finalize() throws Throwable {
//            //System.out.print(this);
//            System.out.println("  finalize");
//            super.finalize();
//        }
//
////        @Override
////        public String toString() {
////            return "User{" +
////                    "name='" + name + '\'' + this +
////                    '}';
////        }
    }

    public static class EmptyUser {

        public EmptyUser() {
        }

//        @SuppressWarnings("deprecation")
//        @Override
//        protected void finalize() throws Throwable {
//            //System.out.println(this);
//            super.finalize();
//            System.out.println("finalize");
//        }
//
//        @Override
//        public String toString() {
//            return "EmptyUser{}" + this;
//        }
        /*
        -Xcomp
        -Xmx60M
-Xms60M
-XX:-ClassUnloading
-XX:+UnlockExperimentalVMOptions
-verbose:gc
-Xlog:gc:gc.log
-XX:+UseSerialGC
         */
    }

}

