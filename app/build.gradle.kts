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
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.junit)

    implementation("com.github.amitshekhariitbhu.Fast-Android-Networking:android-networking:1.0.4")
    implementation("com.ramotion.cardslider:card-slider:0.3.1")
    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation("com.github.florent37:diagonallayout:1.0.7")
    implementation("com.github.ivbaranov:materialfavoritebutton:0.1.5")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
    implementation("com.flaviofaria:kenburnsview:1.0.7")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation(libs.transport.api)

}
