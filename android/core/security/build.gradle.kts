plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}
android {
    namespace = "com.projectatlas.creatoros.core.security"
    compileSdk = 34
    defaultConfig { minSdk = 26 }
}
dependencies {
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.biometric)
    implementation(libs.kotlinx.coroutines.core)
}
