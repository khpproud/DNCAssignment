
object AppConfig {
    const val applicationId = "dev.patrick.dncassignment"

    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"

    const val minSdkVersion = 17
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val gradle = "4.2.1"
    const val kotlin = "1.5.20"
    const val appcompat = "1.3.0"
    const val coreKtx = "1.5.0"
    const val constraintlayout = "2.0.4"
    const val recyclerView = "1.2.1"
    const val material = "1.3.0"
    const val fragment = "1.3.5"

    const val hilt = "2.37"
    const val hiltJetpack = "1.0.0-alpha03"
    const val lifecycle = "2.3.0"

    const val coroutines = "1.5.0"

    const val retrofit = "2.9.0"
    const val moshi = "1.12.0"
    const val okhttp = "4.9.0"

    const val timber = "4.7.1"

    const val room = "2.3.0"

    const val coil = "1.2.2"

    // Testing
    const val junit = "4.13.2"
    const val androidXJUnit = "1.1.2"
    const val espresso = "3.3.0"
}

object GradlePlugins {
    const val Android = "com.android.tools.build:gradle:${Versions.gradle}"
    const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val Hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object Deps {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Lifecycle {
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Fragment {
        const val ktx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpack}"
        const val HiltExtCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpack}"
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Square {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val convertMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }

    object Timer {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
        const val extJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}