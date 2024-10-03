plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.jsonmoviedb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jsonmoviedb"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.common)

    // Dependency untuk Android Networking
    implementation ("com.amitshekhar.android:android-networking:1.0.2")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation(libs.transport.api)

    // Unit Testing
    testImplementation(libs.junit)

    // Android Testing
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
