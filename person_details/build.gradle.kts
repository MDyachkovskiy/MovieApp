plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.test.application.person_details"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))

    //Kotlin
    implementation (Kotlin.core)
    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //AndroidX
    implementation (AndroidX.appcompat)
    implementation (AndroidX.fragment_ktx)

    //Hilt
    implementation (Hilt.main)
    kapt (Hilt.compiler)

    //Design
    implementation (Design.material)
    implementation (Coil.coil_kt)

    //GoogleMap
    implementation (GoogleMaps.service_maps)
}