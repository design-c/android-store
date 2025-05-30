plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.csmain.finalproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.csmain.finalproject"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }


    signingConfigs {
        create("release") {
            storeFile = file("release.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
        }
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.common)
    implementation(projects.featureHome)
    implementation(projects.featureDetails)
    implementation(platform(libs.compose.bom))
    implementation(libs.room.runtime)
    implementation(libs.converter.gson)
    implementation(projects.featureCart)
    implementation(projects.featureProfile)
    implementation(libs.room.ktx)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material)
    implementation(libs.slf4j.android)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.ktor)
    implementation(libs.logging.interceptor)
    implementation(project(":feature-orders"))
    implementation(project(":feature-about"))
    kapt(libs.bundles.hilt.compiler)
    implementation(libs.androidx.datastore.preferences)
}

configurations {
    implementation {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}
