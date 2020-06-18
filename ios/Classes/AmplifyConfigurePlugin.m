#import "AmplifyConfigurePlugin.h"
#if __has_include(<amplify_configure/amplify_configure-Swift.h>)
#import <amplify_configure/amplify_configure-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "amplify_configure-Swift.h"
#endif

@implementation AmplifyConfigurePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAmplifyConfigurePlugin registerWithRegistrar:registrar];
}
@end
