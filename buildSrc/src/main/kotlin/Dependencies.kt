object Versions {
    //Kotlin
    const val core_ktx = "1.12.0"

    //AndroidX
    const val appcompat = "1.6.1"
    const val andrx_fragment_ktx = "1.3.6"

    //Design
    const val material = "1.10.0"
    const val constraint_layout = "2.1.4"
    const val pagination = "3.2.1"

    //Coil
    const val coil = "2.4.0"

    //Retrofit
    const val retrofit_main = "2.9.0"
    const val retrofit_gson_converter = "2.9.0"
    const val logging_interceptor = "4.11.0"
    const val retrofit2_coroutines_adapter = "0.9.2"

    //Navigation
    const val fragment_ktx = "2.7.5"
    const val ui_ktx = "2.7.5"

    //Firebase
    const val messaging_ktx = "23.4.0"

    //Room
    const val room_runtime = "2.6.1"

    //WorkManager
    const val work_runtime_ktx = "2.9.0"

    //GoogleMaps
    const val google_maps_service = "18.1.0"

    //SharedPreferences
    const val shared_preferences = "1.2.1"

    //Dagger
    const val dagger = "2.49"

    //Hilt
    const val hilt = "2.49"

}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core_ktx}"
}
object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.andrx_fragment_ktx}"
}

object Design {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val pagination = "androidx.paging:paging-runtime:${Versions.pagination}"
}

object Coil {
    const val coil_kt = "io.coil-kt:coil:${Versions.coil}"
}

object Retrofit {
    const val main = "com.squareup.retrofit2:retrofit:${Versions.retrofit_main}"
    const val gson_convertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson_converter}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    const val retrofit_coroutine_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit2_coroutines_adapter}"
}

object Navigation {
    const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.fragment_ktx}"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.fragment_ktx}"
}

object Firebase {
    const val messaging_ktx = "com.google.firebase:firebase-messaging-ktx:${Versions.messaging_ktx}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_runtime}"
    const val ktx = "androidx.room:room-ktx:${Versions.room_runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_runtime}"
}

object WorkManager {
    const val runtime_ktx = "androidx.work:work-runtime-ktx:${Versions.work_runtime_ktx}"
}

object GoogleMaps {
    const val service_maps = "com.google.android.gms:play-services-maps:${Versions.google_maps_service}"
}

object SharedPreferences {
    const val main = "androidx.preference:preference:${Versions.shared_preferences}"
    const val ktx = "androidx.preference:preference-ktx:${Versions.shared_preferences}"
}

object Dagger {
    const val main = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Hilt {
    const val main = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}