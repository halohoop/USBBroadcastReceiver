package com.halohoop.usbbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "halohoop:";
    private USBBroadcastReceiver usbBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        usbBroadcastReceiver = new USBBroadcastReceiver();
        registerReceiver(usbBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (usbBroadcastReceiver != null) {
            unregisterReceiver(usbBroadcastReceiver);
        }
    }

    class USBBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals("android.hardware.usb.action.USB_STATE")) {
                if (intent.getExtras().getBoolean("connected")) {
                    // usb 插入
                    Toast.makeText(MainActivity.this, "usb 插入", Toast.LENGTH_SHORT).show();
                    logi("usb 插入");
                } else {
                    //   usb 拔出
                    Toast.makeText(MainActivity.this, "usb 拔出", Toast.LENGTH_SHORT).show();
                    logi("usb 拔出");
                }
            }
        }
    }


    public void logi(String s) {
        Log.i(TAG, "halohoop:" + s);
    }
}
