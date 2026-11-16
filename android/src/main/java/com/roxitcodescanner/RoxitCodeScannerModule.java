package com.roxitcodescanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.core.content.ContextCompat;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RoxitCodeScannerModule extends ReactContextBaseJavaModule {
  public static ReactApplicationContext reactContext;

  private static final String SUNMI_ACTION = "com.sunmi.scanner.ACTION_DATA_CODE_RECEIVED";

  // intent action -> extra с текстом кода
  private static final String[][] SCAN_INTENTS = {
      {"com.xcheng.scanner.action.BARCODE_DECODING_BROADCAST", "EXTRA_BARCODE_DECODING_DATA"},
      {"android.intent.ACTION_DECODE_DATA", "barcode_string"},
      {"scan.rcv.message", "barcodeData"},
      {"barcode", "barcode"},
      {SUNMI_ACTION, "data"},
  };

  private final BroadcastReceiver scanner_receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent == null || intent.getAction() == null) {
        return;
      }
      String data = null;
      for (String[] scan_intent : SCAN_INTENTS) {
        if (scan_intent[0].equals(intent.getAction())) {
          data = intent.getStringExtra(scan_intent[1]);
          break;
        }
      }
      if (data == null && SUNMI_ACTION.equals(intent.getAction())) {
        byte[] source_byte = intent.getByteArrayExtra("source_byte");
        if (source_byte != null) {
          data = new String(source_byte);
        }
      }
      if (data != null) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit("barcode", data);
      }
    }
  };

  public RoxitCodeScannerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    IntentFilter filter = new IntentFilter();
    for (String[] scan_intent : SCAN_INTENTS) {
      filter.addAction(scan_intent[0]);
    }
    // бродкасты приходят от сервисов сканеров — нужен EXPORTED
    ContextCompat.registerReceiver(reactContext, scanner_receiver, filter, ContextCompat.RECEIVER_EXPORTED);
  }

  // отписка при teardown: invalidate() — RN >= 0.65, onCatalystInstanceDestroy() — старые
  public void invalidate() {
    unregister_scanner_receiver();
    super.invalidate();
  }

  public void onCatalystInstanceDestroy() {
    unregister_scanner_receiver();
  }

  private void unregister_scanner_receiver() {
    try {
      reactContext.unregisterReceiver(scanner_receiver);
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Override
  public String getName() {
    return "RoxitCodeScannerModule";
  }
}
