package com.roxitcodescanner;

import android.util.Log;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RoxitCodeScannerModule extends ReactContextBaseJavaModule {
  public static ReactApplicationContext reactContext;

  public RoxitCodeScannerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
//    reactContext.registerReceiver(new ScannerReceiver(),
//        new IntentFilter("barcode")
//    );
    reactContext.registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("EXTRA_BARCODE_DECODING_DATA"));
            }
        }
    }, new IntentFilter("com.xcheng.scanner.action.BARCODE_DECODING_BROADCAST"));

    reactContext.registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcode_string"));
            }
        }
    }, new IntentFilter("android.intent.ACTION_DECODE_DATA"));

    reactContext.registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcodeData"));
            }
        }
    }, new IntentFilter("scan.rcv.message"));

    reactContext.registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("barcode", intent.getStringExtra("barcode"));
            }
        }
    }, new IntentFilter("barcode"));
  }

    @Override
    public String getName() {
        return "ScannerBroadcast";
    }
}
