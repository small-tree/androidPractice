package xianchao.com.practice.processConnect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileDescriptor;

public class CommunicationService extends Service {
    private OperatorBinder operatorBinder = new OperatorBinder();

    private ISpanComm.Stub spanBinder = new ISpanComm.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void printMsg(String msg) throws RemoteException {
            CommunicationService.this.printMsg(msg);
        }


    };

    public void printMsg(String msg) {
        Log.e("xianchao", msg);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return spanBinder;
    }


    class OperatorBinder extends Binder {
        public CommunicationService getService() {
            return CommunicationService.this;
        }
    }
}
