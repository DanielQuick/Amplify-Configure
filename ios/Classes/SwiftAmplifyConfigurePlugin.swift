import Flutter
import UIKit
import Amplify

public class SwiftAmplifyConfigurePlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "amplify_configure", binaryMessenger: registrar.messenger())
    let instance = SwiftAmplifyConfigurePlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    if (call.method == "initialize") {
      do {
        try Amplify.configure()
        print("Amplify configured with Auth and Analytics plugins")
        result(true)
      } catch {
        result(FlutterError(code: "Failed to configure Amplify",
                        message: "Empty Message",
                        details: (error as! AmplifyError).errorDescription))
      }
    } else {
      result(FlutterMethodNotImplemented)
    }
  }
}
