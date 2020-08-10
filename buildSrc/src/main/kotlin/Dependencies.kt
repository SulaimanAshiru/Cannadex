import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.3.72"
object GradlePlugins {
    object Versions {
        const val safeArgs = "2.2.1"
        const val androidGradle = "4.0.1"
    }

    const val android = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val coreKtx = "androidx.core:core-ktx:1.3.1"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val material = "com.google.android.material:material:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"
        const val preference = "androidx.preference:preference:1.1.1"
    }

    object Navigation {
        const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:2.3.0"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:2.3.0"
    }

    object Parsing {
        const val moshi = "com.squareup.moshi:moshi-kotlin:1.9.3"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.9.3"
    }

    object Image {
        const val glide = "com.github.bumptech.glide:glide:4.11.0"
        const val glideCompiler = "com.github.bumptech.glide:compiler:4.11.0"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.8.1"
        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.8.0"
    }

    object MultiThreading {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.8"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8"
    }
    object Animation {
        const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    }

    object LocaleDb {
        const val room = "androidx.room:room-runtime:2.2.5"
        const val roomCompiler = "androidx.room:room-compiler:2.2.5"
        const val roomKotlin = "androidx.room:room-ktx:2.2.5"
    }

    object TestLibraries {
        const val junit4 = "junit:junit:_"
        const val core = "androidx.arch.core:core-testing:_"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:_"
        const val testExt = "androidx.test.ext:junit:_"
        const val espresso = "androidx.test.espresso:espresso-core:_"
        const val mockk = "io.mockk:mockk:_"
    }

    fun DependencyHandler.implementationAndroidX() {
        add("implementation", AndroidX.appcompat)
        add("implementation", AndroidX.constraintLayout)
        add("implementation", AndroidX.material)
        add("implementation", AndroidX.preference)
    }

    fun DependencyHandler.implementationAnimation() {
        add("implementation", Animation.shimmer)
    }

    fun DependencyHandler.implementationNetwork() {
        add("implementation", Network.retrofit)
        add("implementation", Network.retrofitMoshi)
        add("implementation", Network.retrofitCoroutines)
        add("implementation", Network.okhttpInterceptor)
    }

    fun DependencyHandler.implementationParsing() {
        add("implementation", Parsing.moshi)
        add("kapt", Parsing.moshiCodeGen)
    }

    fun DependencyHandler.implementationImage() {
        add("implementation", Image.glide)
        add("kapt", Image.glideCompiler)
    }

    fun DependencyHandler.implementationNavigation() {
        add("implementation", Navigation.navigationKtx)
        add("implementation", Navigation.navigationUiKtx)
    }

    fun DependencyHandler.implementationMultiThreading() {
        add("implementation", MultiThreading.coroutinesAndroid)
        add("implementation", MultiThreading.coroutinesCore)
    }

    fun DependencyHandler.implementationDatabase() {
        add("implementation", LocaleDb.room)
        add("implementation", LocaleDb.roomKotlin)
        add("kapt", LocaleDb.roomCompiler)
    }
}