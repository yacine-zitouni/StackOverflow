plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // added by me
    alias (libs.plugins.kotlin.ksp)
    alias (libs.plugins.dagger.hilt)

}

android {
    namespace = "fr.mastersid.zitouni.stackoverflow"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.mastersid.zitouni.stackoverflow"
        minSdk = 22
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // added by me
    implementation (libs.androidx.compose.runtime)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.dagger.hilt.android)
    ksp (libs.dagger.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.androidx.material)
    implementation (libs.moshi.kotlin)
    implementation (libs.retrofit.converter.moshi)
    implementation (libs.androidx.room.runtime)
    ksp ( libs.androidx.room.compiler)
    implementation ( libs.androidx.room.ktx)

}