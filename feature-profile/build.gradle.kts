plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.example.feature_profile"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.bundles.hilt)
    implementation(projects.common)
    implementation(platform(libs.compose.bom))
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material)
    implementation(libs.slf4j.android)
    implementation(libs.converter.gson)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.ktor)
    kapt(libs.hilt.compiler)
}