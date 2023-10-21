# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Keep the classes and methods used by the Retrofit library.
-keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }

# Keep the classes and methods used by the OkHttp library.
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Ignore warnings for certain classes and attributes.
-dontwarn javax.management.**
-dontwarn org.xml.sax.**

# Renames obfuscated class members to shorter names.
-obfuscationdictionary dictionary.txt# Add project-specific ProGuard rules here.
                                     # By default, the Android SDK uses ProGuard version 6.2.2.
                                     # You can control the set of applied configuration files using the
                                     # proguardFiles setting in the build.gradle file.
                                     #
                                     # For more details, see:
                                     #   https://developer.android.com/studio/build/shrink-code

                                     # Optimization passes eliminate unused code and reduce the size of the APK.
                                     # Keep in mind that these options may remove code that is actually needed
                                     # for the application to function correctly. You should test thoroughly
                                     # after enabling these options.
                                     #
                                     # For more details, see:
                                     #   https://developer.android.com/studio/build/shrink-code#optimization-passes

                                     # Enable optimization passes.
                                     -optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*

                                     # Keep the entry points of the application.
                                     -keep public class * extends android.app.Activity
                                     -keep public class * extends android.app.Application
                                     -keep public class * extends android.app.Service
                                     -keep public class * extends android.content.BroadcastReceiver
                                     -keep public class * extends android.content.ContentProvider
                                     -keep public class * extends androidx.core.app.NotificationCompat$Style
                                     -keep public class * extends androidx.fragment.app.Fragment
                                     -keep public class * extends androidx.appcompat.app.AppCompatActivity
                                     -keep public class * extends androidx.appcompat.app.ActionBar
                                     -keep public class * extends androidx.appcompat.widget.Toolbar
                                     -keep public class * extends androidx.lifecycle.ViewModel
                                     -keep public class * extends androidx.lifecycle.AndroidViewModel

                                     # Keep the classes and methods used by the Android Support Library.
                                     -dontwarn android.support.**
                                     -dontwarn androidx.**
                                     -keep class androidx.** { *; }
                                     -keep interface androidx.** { *; }

                                     # Keep the classes and methods used by the Google Play Services APIs.
                                     # Note that you should only include the APIs that you actually use in your application.
                                     -keep class com.google.android.gms.common.api.** { *; }
                                     -keep class com.google.android.gms.location.** { *; }
                                     -keep class com.google.android.gms.maps.** { *; }

                                     # Keep the classes and methods used by the Retrofit library.
                                     -keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault
                                     -keepclassmembers,allowshrinking,allowobfuscation interface * {
                                         @retrofit2.http.* <methods>;
                                     }
                                     -keep class retrofit2.** { *; }
                                     -keep interface retrofit2.** { *; }

                                     # Keep the classes and methods used by the OkHttp library.
                                     -keep class okhttp3.** { *; }
                                     -keep interface okhttp3.** { *; }

                                     # Ignore warnings for certain classes and attributes.
                                     -dontwarn javax.management.**
                                     -dontwarn org.xml.sax.**

                                     # Renames obfuscated class members to shorter names.
                                     -obfuscationdictionary dictionary.txt


# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**


# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation