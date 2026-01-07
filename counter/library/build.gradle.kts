plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.buildkt.counter"
}

dependencies {
    implementation(projects.designSystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.icons.core)
    implementation(libs.androidx.material3.icons.extended)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.hilt.android)
    implementation(libs.mvi.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    debugImplementation(libs.androidx.ui.tooling)

    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)
    ksp(libs.mvi.annotation.processor)
}
