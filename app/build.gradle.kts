plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.pick_pop"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pick_pop"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.androidx.datastore.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth:21.0.1")

    implementation("com.google.firebase:firebase-database")

    // Firebase BoM: Firebase와 관련된 라이브러리 관리
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))


    // Firestore (데이터베이스)
    implementation("com.google.firebase:firebase-firestore-ktx")
    // 이미지 관련
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-firestore:24.8.1")

    // Glide 라이브러리
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    implementation("com.google.android.gms:play-services-base:18.0.1")
    implementation("com.google.android.gms:play-services-auth:20.1.0")

}