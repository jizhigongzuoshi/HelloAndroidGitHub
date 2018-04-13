package cn.com.focus.helloandroidgithub.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MrLu on 2018/1/5.
 */

public class maintest {
    private int counter;
    @Test
    public void test1(){
        final TestImpl target = new TestImpl();
        byte[] bdb = target.getBdb();
        bdb = new byte[]{65, 66, 67};

        System.out.println(new String(target.getBdb()));
        final int number = 0;
        Itest itest = (Itest) Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{Itest.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //Class<?> declaringClass = method.getDeclaringClass();
                System.out.println(number);
                counter++;
                return method.invoke(target, args);

                //return ;

            }
        });

        String xixi = itest.sayHello("xixi");
        System.out.println(xixi);
    }

    @Test
    public void test2(){
        ArrayList<String> al = new ArrayList<>();
        al.add("a");
        al.add("b");
        al.add("c");
        al.add("d");

        ArrayList clone = (ArrayList)al.clone();

        System.out.println(al == clone);

        System.out.println(clone);

        System.out.println(3*0.1 == 0.3);
        int[] data = new int[]{1,2 ,3 ,34 ,545};
        double[] ddata = new double[]{12.2323, 34.2323, 323.1212};
        System.out.println(data);

        System.out.println(Arrays.toString(ddata));

    }
    @Test
    public void remove(){
        ArrayList<String> al = new ArrayList<>();
        al.add("a");
        al.add("ab");
        al.add("bb");
        al.add("bb");
        al.add("abc");
        al.add("abcd");

        System.out.println(al);
        for (int i = 0; i < al.size(); i++) {
            String s = al.get(i);
            if (s.equals("bb")) {
                al.remove(s);
            }
        }

        for (String s : al) {
            if (s.equals("bb")) {
                al.remove(s);
            }
        }

        System.out.println(al);
    }
    @Test
    public void testLamda(){
        //Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ));
    }
}
