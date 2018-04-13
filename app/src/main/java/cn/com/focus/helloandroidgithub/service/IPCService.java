package cn.com.focus.helloandroidgithub.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import cn.com.focus.helloandroidgithub.IMyAidlInterface;

/**
 * Created by MrLu on 2018/3/27.
 */

public class IPCService extends Service {
    private static final String DESCRIPTOR = "IPCService";
    private final String[] names = {"狗", "猫", "鸡", "鸭", "鹅"};
    private MyBinderr mBinder = new MyBinderr();

    private class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 0x001: {
                    data.enforceInterface(DESCRIPTOR);
                    int num = data.readInt();
                    reply.writeNoException();
                    reply.writeString(names[num]);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    class MyBinderr extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            System.out.println("get data from client:" + anInt + " " + aLong);
        }

        @Override
        public void sayHello() throws RemoteException {

        }

    }
}
