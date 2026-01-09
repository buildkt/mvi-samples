plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.buildkt.feature.product"

    android {
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
                all { it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware" }
            }
        }
    }
}

dependencies {
    implementation(projects.designSystem)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.icons.core)
    implementation(libs.androidx.material3.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.compose.shimmer)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.mvi.android)

    debugImplementation(libs.androidx.ui.tooling)

    ksp(libs.mvi.annotation.processor)
}
