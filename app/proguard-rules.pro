# ProGuard rules for IntentoDeApp

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep interface javax.inject.** { *; }

# Keep Room classes
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }

# Keep Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Keep Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }

# Keep application classes
-keep class com.intento.** { *; }

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
