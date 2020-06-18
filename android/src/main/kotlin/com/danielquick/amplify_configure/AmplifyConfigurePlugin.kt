package com.danielquick.amplify_configure

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** AmplifyConfigurePlugin */
public class AmplifyConfigurePlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    try {
      Amplify.configure(flutterPluginBinding.getApplicationContext())
      Log.i("MyAmplifyApp", "Initialized Amplify")
    } catch (error: AmplifyException) {
      Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
    }

    channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "amplify_configure")
    channel.setMethodCallHandler(this);
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      try {
        Amplify.configure(registrar.context())
        Log.i("MyAmplifyApp", "Initialized Amplify")
      } catch (error: AmplifyException) {
        Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
      }

      val channel = MethodChannel(registrar.messenger(), "amplify_configure")
      channel.setMethodCallHandler(AmplifyConfigurePlugin())
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {}

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
