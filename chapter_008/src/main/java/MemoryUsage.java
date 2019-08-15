public class MemoryUsage {

    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {

        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        System.gc();
        //String name ="name";
        //String[] u = new String[1000_000];
        //User[] u = new User[1000_000];
        //Object[] u = new Object[1000_000];
        EmptyUser[] u = new EmptyUser[1000_000];
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        for (int i = 0; i < 1000_000; i++) {//EmptyUser u: users) {
            u[i]
                    //=new User(name);//48 байт
                    = new EmptyUser();//40 байт
                    //=String.valueOf(i);//48 байт
                    //= new Object();// 8 байт
//            {
//                @SuppressWarnings("deprecation")
//                @Override
//                protected void finalize() throws Throwable {
//                    super.finalize();
//                    System.out.printf("finalize %s \n", this);
//                }
//
//            };
        }

        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        System.gc();
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


        public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void finalize() throws Throwable {
            //System.out.print(this);
            System.out.println("  finalize");
            super.finalize();
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
            //System.out.println(this);
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "EmptyUser{}" + this;
        }
    }

}

