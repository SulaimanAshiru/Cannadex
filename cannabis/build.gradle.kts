import Dependencies.implementationAndroidX
import Dependencies.implementationAnimation
import Dependencies.implementationDatabase
import Dependencies.implementationImage
import Dependencies.implementationMultiThreading
import Dependencies.implementationNavigation
import Dependencies.implementationNetwork
import Dependencies.implementationParsing

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.example.cannadex"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coreKtx)
    implementationAndroidX()
    implementationAnimation()
    implementationDatabase()
    implementationMultiThreading()
    implementationParsing()
    implementationImage()
    implementationNetwork()
    implementationNavigation()
}