package cn.com.focus.helloandroidgithub.collection;

/**
 * Created by MrLu on 2018/1/4.
 */

public class Child extends Parent {
    private int a = 10;
    private Child child = new Child("alsdjf");
    public Child(String a) {
        System.out.println("*****************");
        System.out.println(child.a);
    }
}
