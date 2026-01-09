plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.buildkt.app.product"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.buildkt.app.product"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(jdkVersion = 17)
}

dependencies {
    implementation(projects.designSystem)
    implementation(projects.listDetail.library)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.expressive)
    implementation(libs.androidx.material3.icons.core)
    implementation(libs.androidx.material3.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.mvi.android)
    implementation(libs.room.runtime)

    debugImplementation(libs.androidx.ui.tooling)

    ksp(libs.mvi.annotation.processor)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)
}
