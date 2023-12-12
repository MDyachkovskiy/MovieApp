import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.test.application.remote_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    val properties = Properties()
    val apikeyFile = rootProject.file("apikey.properties")
    if (apikeyFile.exists()) {
        properties.load(apikeyFile.inputStream())
    }
    val kinopoiskApiKey = properties.getProperty("kinopoisk_api_key", "")

    android.buildTypes.forEach { buildType ->
        buildType.buildConfigField("String", "KINOPOISK_API_KEY", "\"$kinopoiskApiKey\"")
    }

    buildTypes {
        release {
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
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation (project(":core"))
    //Kotlin
    implementation (Kotlin.core)
    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //AndroidX
    implementation (AndroidX.appcompat)

    //Retrofit
    implementation(Retrofit.main)
    implementation(Retrofit.gson_convertor)
    implementation(Retrofit.interceptor)
    implementation(Retrofit.retrofit_coroutine_adapter)

    //Pagination
    implementation (Design.pagination)

    //Dagger
    implementation (Dagger.main)
    kapt (Dagger.compiler)

    //Hilt
    implementation (Hilt.main)
    kapt (Hilt.compiler)
}