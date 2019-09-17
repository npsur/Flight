plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")
    defaultConfig {
        applicationId = "com.ns.flight"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TOKEN",
            "\"81d93056-9c7f-451d-94b6-3e88eb6fa9ad\"")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding.isEnabled = true
}

dependencies {
    implementation(Dependency.kotlin)
    implementation(Dependency.appCompat)
    implementation(Dependency.ktx)
    implementation(Dependency.cardView)
    implementation(Dependency.recyclerView)
    implementation(Dependency.constraintLayout)
    implementation(Dependency.lifecycle)
    implementation(Dependency.viewModel)
    implementation(Dependency.liveData)
    implementation(Dependency.threeTenAbp)
    implementation(Dependency.retrofit)
    implementation(Dependency.zxing)
    kapt(Dependency.moshi)
    implementation(Dependency.moshiConverter)
    implementation(Dependency.daggerAndroid)
    implementation(Dependency.daggerSupport)
    kapt(Dependency.daggerProcessor)
    kapt(Dependency.daggerAndroidProcessor)
    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.androidTestRunner)
    androidTestImplementation(Dependency.androidJUnit)
    androidTestImplementation(Dependency.androidTestRules)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.espressoContrib)
}
