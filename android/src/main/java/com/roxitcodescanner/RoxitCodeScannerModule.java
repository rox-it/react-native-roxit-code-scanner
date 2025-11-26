package com.roxitcodescanner;

import android.util.Log;
import android.content.IntentFilter;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RoxitCodeScannerModule extends ReactContextBaseJavaModule {
  public static ReactApplicationContext reactContext;

  public RoxitCodeScannerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    reactContext.registerReceiver(new ScannerReceiver(), new IntentFilter("com.xcheng.scanner.action.BARCODE_DECODING_BROADCAST"));
    reactContext.registerReceiver(new ScannerReceiver(), new IntentFilter("android.intent.ACTION_DECODE_DATA"));
    reactContext.registerReceiver(new ScannerReceiver(), new IntentFilter("scan.rcv.message"));
    reactContext.registerReceiver(new ScannerReceiver(), new IntentFilter("barcode"));
  }

    @Override
    public String getName() {
        return "ScannerBroadcast";
    }
}
