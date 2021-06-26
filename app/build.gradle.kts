plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "dev.patrick.dncassignment"
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.material)
    implementation(Deps.AndroidX.recyclerView)

    // coroutines
    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)

    // lifecycle
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.viewmodelKtx)
    implementation(Deps.Lifecycle.commonJava8)

    // fragment-ktx
    implementation(Deps.Fragment.ktx)

    // hilt
    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltCompiler)
    implementation(Deps.Hilt.hiltViewModel)
    kapt(Deps.Hilt.HiltExtCompiler)

    // room
    implementation(Deps.Room.ktx)
    kapt(Deps.Room.compiler)

    // retrofit
    implementation(Deps.Square.retrofit)
    implementation(Deps.Square.convertMoshi)
    // moshi
    implementation(Deps.Square.moshi)
    implementation(Deps.Square.moshiCodegen)
    // logging
    implementation(Deps.Square.logging)

    // coil
    implementation(Deps.Coil.coil)

    // timber
    implementation(Deps.Timer.timber)

    // test
    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.Test.extJUnit)
    androidTestImplementation(Deps.Test.espresso)
}