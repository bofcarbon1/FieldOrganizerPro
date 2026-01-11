plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.fieldorganizerprov2"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.fieldorganizerprov2"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    //implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.datastore.core)
    //implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // Paging Compose
    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.13.0")
    // API Services
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    // fucking ViewModelScope shit
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-runtime-ktx
    runtimeOnly("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-compose
    //runtimeOnly("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    // JSON stuff
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    // supbase postgres for the api call
    //implementation("io.github.jan-tennert.supabase:postgrest-kt:1.4.0")
    implementation("io.ktor:ktor-client-core:2.3.6")
    implementation("io.ktor:ktor-client-okhttp:2.3.6")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.6")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
    // Observables & Subscriptions
    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.12")
    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxandroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxkotlin:3.x.y")
    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxkotlin
    runtimeOnly("io.reactivex.rxjava3:rxkotlin:3.0.1")
    // Inject
    // If you use javax.inject annotations directly, you might need this:
    implementation("javax.inject:javax.inject:1")
    // ... other dependencies
    //implementation("androidx.compose.material3:material3:1.x.x")
    // For date formatting utility (optional but helpful)
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    dependencies {
        //implementation("com.google.dagger:hilt-android:2.48") // Use the latest version
        //ksp("com.google.dagger:hilt-android-compiler:2.48") // Use ksp or kapt
        // ... other dependencies
    }



}