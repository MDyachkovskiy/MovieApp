import java.util.Properties

plugins {
    id ("com.android.application")
    kotlin ("android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.kotlin_movieapp"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.kotlin_movieapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    flavorDimensions ("version")
    productFlavors {
        create("free"){
            dimension = "version"
            versionNameSuffix = "freeConfig"
        }
        create("paid"){
            dimension = "version"
            versionNameSuffix = "paidConfig"
        }
    }

    afterEvaluate {
        tasks.named("mergeFreeDebugResources").configure {
            inputs.files (tasks.named("processFreeDebugGoogleServices"))
        }
    }
}

dependencies {

    implementation (project(":core"))
    implementation (project(":home"))
    implementation (project(":favorites"))
    implementation (project(":search"))
    implementation (project(":contacts"))
    implementation (project(":settings"))
    implementation (project(":movie_details"))
    implementation (project(":person_details"))
    implementation (project(":remote_data"))
    implementation (project(":local_data"))

    //Kotlin
    implementation (Kotlin.core)

    //AndroidX
    implementation (AndroidX.appcompat)

    //Firebase
    implementation (Firebase.messaging_ktx)

    //Design
    implementation (Design.material)
    implementation (Design.constraint_layout)

    //Retrofit
    implementation (Retrofit.main)
    implementation (Retrofit.gson_convertor)
    implementation (Retrofit.interceptor)
    implementation (Retrofit.retrofit_coroutine_adapter)

    //Room
    implementation (Room.runtime)

    //Koin
    implementation (Koin.android)
    implementation (Koin.core)
    implementation (Koin.navigation)

    //Navigation
    implementation (Navigation.fragment_ktx)
    implementation (Navigation.ui_ktx)

    //WorkManager
    implementation (WorkManager.runtime_ktx)
}