package xianchao.com.practice.processConnect;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import xianchao.com.practice.R;

public class ServiceTestActivity extends AppCompatActivity {

    private CommunicationService service2;
    private ISpanComm iSpanComm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        findViewById(R.id.tv_call_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iSpanComm.printMsg("jfdakslfjalsdjfkasl");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iSpanComm = ISpanComm.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(new Intent(this,CommunicationService.class),conn,BIND_AUTO_CREATE);
    }
}
