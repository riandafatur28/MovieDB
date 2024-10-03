plugins {
    alias(libs.plugins.android.application) // Apply the Android application plugin
}

android {
    namespace = "com.example.jsonmoviedb" // Specify the application namespace
    compileSdk = 34 // Compile SDK version

    defaultConfig {
        applicationId = "com.example.jsonmoviedb" // Unique application ID
        minSdk = 24 // Minimum SDK version supported
        targetSdk = 34 // Target SDK version
        versionCode = 1 // Application version code
        versionName = "1.0" // Application version name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Test runner for instrumentation tests
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Disable code shrinking
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Default ProGuard configuration
                "proguard-rules.pro" // Custom ProGuard rules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Set source compatibility to Java 8
        targetCompatibility = JavaVersion.VERSION_1_8 // Set target compatibility to Java 8
    }
}

dependencies {
    implementation(libs.appcompat) // AndroidX AppCompat library for backward compatibility
    implementation(libs.material) // Material Design components
    implementation(libs.activity) // Activity KTX library
    implementation("com.amitshekhar.android:android-networking:1.0.2") // Networking library
    implementation("com.remotion.cardslider:card-slider:0.3.1") // Card Slider library
    implementation("com.github.ivbaranov:materialfavoritebutton:0.1.5") // Material favorite button library
    implementation("com.github.florent37:diagonallayout:1.0.9") // Diagonal layout library
    implementation("com.flaviofaria:kenburnsview:1.0.7") // Ken Burns view library
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")
    implementation(libs.constraintlayout) // ConstraintLayout for flexible UI design
    testImplementation(libs.junit) // JUnit library for unit testing
    androidTestImplementation(libs.ext.junit) // JUnit extensions for Android testing
    androidTestImplementation(libs.espresso.core) // Espresso library for UI testing
}
