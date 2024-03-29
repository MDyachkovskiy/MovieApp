plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.test.application.local_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":core"))
    //Kotlin
    implementation (Kotlin.core)
    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //AndroidX
    implementation (AndroidX.appcompat)

    //Room
    implementation (Room.runtime)
    implementation (Room.ktx)
    kapt(Room.compiler)

    //WorkManager
    implementation(WorkManager.runtime_ktx)

    //Dagger
    implementation (Dagger.main)
    kapt (Dagger.compiler)

    //Hilt
    implementation (Hilt.main)
    kapt (Hilt.compiler)
}