import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class AmplifyConfigure {
  static const MethodChannel _channel =
      const MethodChannel('amplify_configure');

  static Future<void> initialize({Function(FlutterError) onError}) async {
    try {
      await _channel.invokeMethod("initialize");
      print("successfully configured Amplify");
    } catch (e) {
      if (onError != null) onError(e);
    }
  }
}
