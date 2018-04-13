package cn.com.focus.helloandroidgithub.proxy;

/**
 * Created by MrLu on 2018/1/5.
 */

public class TestImpl implements Itest{
    private  int i = 0;

    public byte[] getBdb() {
        return bdb;
    }

    private byte[] bdb;

    @Override
    public String sayHello(final String content) {
        System.out.println(content);
        new Myinner(){
            @Override
            public void test() {
                super.test();
                System.out.println(content);
                i++;
            }
        };
        return content + "  " +  content;
    }

    public class Myinner{
        public void test(){
            i++;
        }
    }
}
