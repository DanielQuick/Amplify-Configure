import 'dart:async';

import 'package:flutter/services.dart';

class AmplifyConfigure {
  static const MethodChannel _channel =
      const MethodChannel('amplify_configure');

  static Future<bool> initialize() async {
    try {
      var result = await _channel.invokeMethod("initialize");
      return result;
    } catch (e) {
      print(e);
      return false;
    }
  }
}
