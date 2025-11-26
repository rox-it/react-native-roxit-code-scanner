# react-native-roxit-code-scanner

Broadcast code scanner receiver for intent actions:

- com.xcheng.scanner.action.BARCODE_DECODING_BROADCAST
- android.intent.ACTION_DECODE_DATA
- scan.rcv.message
- barcode

## Installation

```sh
npm install react-native-roxit-code-scanner
yarn add react-native-roxit-code-scanner
```

## Usage

```js
import 'react-native-roxit-code-scanner';
import {DeviceEventEmitter} from "react-native";

// ...

const barcode_callback = useCallback(barcode => {
	console.log('barcode', barcode);
}, []);

useEffect(() => {
	DeviceEventEmitter.addListener('barcode', barcode_callback);
	return () => DeviceEventEmitter.removeAllListeners('barcode');
});
```

