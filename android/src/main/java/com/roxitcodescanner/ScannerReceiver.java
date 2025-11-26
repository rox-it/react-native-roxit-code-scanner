package com.roxitcodescanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.ReactApplication;

import javax.annotation.Nullable;

public class ScannerReceiver extends BroadcastReceiver {
    ReactContext reactContext;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
//            Toast.makeText(context, intent.getAction(), 2000).show();
//            Bundle bundle = new Bundle();
                ReactApplication rnApp = (ReactApplication) context.getApplicationContext();
            if (intent.getAction().equals("com.xcheng.scanner.action.BARCODE_DECODING_BROADCAST")) {
//                bundle.putString("barcode", intent.getStringExtra(Intent.EXTRA_TEXT));
//                Toast.makeText(context, intent.getStringExtra("EXTRA_BARCODE_DECODING_DATA"), 2000).show();
                rnApp.getReactNativeHost().getReactInstanceManager()
                    .getCurrentReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("EXTRA_BARCODE_DECODING_DATA"));
            }
            if (intent.getAction().equals("android.intent.ACTION_DECODE_DATA")) {
                rnApp.getReactNativeHost().getReactInstanceManager()
                    .getCurrentReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcode_string"));
            }
            if (intent.getAction().equals("scan.rcv.message")) {
                rnApp.getReactNativeHost().getReactInstanceManager()
                    .getCurrentReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcodeData"));
            }
            if (intent.getAction().equals("barcode")) {
                rnApp.getReactNativeHost().getReactInstanceManager()
                    .getCurrentReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcode"));
            }
        }
    }

//    public void sendCallEvent(String barcode){
//            WritableMap params = Arguments.createMap();
//            params.putString("barcode", barcode);
//            sendEvent("BarcodeRecevied", params);
//    }
//
//    private void sendEvent(String eventName, @Nullable WritableMap params) {
//        reactContext
//            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//            .emit(eventName, params);
//    }
}
