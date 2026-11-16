# Changelog

## 1.0.28

- Added Sunmi scanner broadcast: `com.sunmi.scanner.ACTION_DATA_CODE_RECEIVED`
  (`data` extra, `source_byte` fallback)
- Single receiver with an action → extra map instead of per-action receivers
- Receivers registered as `RECEIVER_EXPORTED` via `ContextCompat` (required on
  Android 13+/targetSdk 34+, safe on older APIs)
- `null` scan data is no longer emitted to JS
- Receiver is unregistered on catalyst teardown (`invalidate()` /
  `onCatalystInstanceDestroy()`)
- Native module renamed to `RoxitCodeScannerModule` — now matches
  `NativeModules.RoxitCodeScannerModule` in `index.js`
- Gradle: `namespace` for AGP 8+, `com.facebook.react:react-android` dependency,
  dropped dead `jcenter()` repo
