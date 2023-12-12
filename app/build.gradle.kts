plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    kotlin("kapt")
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    flavorDimensions("version")
    productFlavors {
        create("free") {
            dimension = "version"
            versionNameSuffix = "freeConfig"
        }
        create("paid") {
            dimension = "version"
            versionNameSuffix = "paidConfig"
        }
    }

    afterEvaluate {
        tasks.named("mergeFreeDebugResources").configure {
            inputs.files(tasks.named("processFreeDebugGoogleServices"))
        }
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
    implementation(project(":home"))
    implementation(project(":favorites"))
    implementation(project(":search"))
    implementation(project(":contacts"))
    implementation(project(":settings"))
    implementation(project(":movie_details"))
    implementation(project(":person_details"))
    implementation(project(":remote_data"))
    implementation(project(":local_data"))

    //Kotlin
    implementation(Kotlin.core)

    //AndroidX
    implementation(AndroidX.appcompat)

    //Firebase
    implementation(Firebase.messaging_ktx)

    //Design
    implementation(Design.material)
    implementation(Design.constraint_layout)

    //Hilt
    implementation (Hilt.main)
    kapt (Hilt.compiler)

    //Navigation
    implementation(Navigation.fragment_ktx)
    implementation(Navigation.ui_ktx)

    //WorkManager
    implementation(WorkManager.runtime_ktx)

    //Dagger
    implementation(Dagger.main)
    implementation("com.google.dagger:dagger-android-support:2.49")
    kapt(Dagger.compiler)
}