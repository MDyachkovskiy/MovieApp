import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.test.application.core"
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
        forEach { buildType ->
            val properties = Properties()
            val apiKeyFile = project.rootProject.file("apikey.properties")
            apiKeyFile.inputStream().use { properties.load(it) }
            val apiKey = properties.getProperty("kinopoisk_api_key", "")

            buildType.buildConfigField("String", "KINOPOISK_API_KEY", apiKey)
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
    //Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //AndroidX
    implementation("androidx.appcompat:appcompat:1.6.1")

    //Design
    implementation("com.google.android.material:material:1.10.0")

    //Pagination
    implementation ("androidx.paging:paging-runtime:3.2.1")
}