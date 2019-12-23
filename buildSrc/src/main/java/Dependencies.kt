object Version {
    const val kotlinVersion = "1.3.31"
    const val coroutineVersion = "1.1.1"

    //support libs
    const val appcompatVersion = "1.1.0"
    const val constraintLayoutVersion = "2.0.0-beta3"
    const val corektx = "1.1.0"
    const val lifecycle = "2.1.0-alpha04"
    const val recyclerview = "1.0.0"
    const val nav = "2.0.0"

    // third libs
    const val koinVer = "2.0.1"
    const val retrofitCoroutines = "0.9.2"
    const val retrofitVer = "2.6.2"
    const val retrofitGsonVer = "2.6.2"
    const val gsonVer = "2.8.5"
    const val okHttp = "3.12.1"
    const val glideVer = "4.9.0"
    const val shapeOfView = "1.4.4"
    const val loaderPack = "0.5"

    //test libs
    const val junitVersion = "4.12"
    const val androidTestRunnerVer = "1.1.2-alpha02"
    const val espressoCore = "3.2.0-alpha02"
    const val androidJunit = "1.1.0"
    const val archCoreTestVer = "2.0.0"
    const val mockkVer = "1.9.2"
    const val mockwebserver = "2.7.5"
    const val databinding = "3.3.2"
    const val fragmentTest = "1.1.0-alpha06"
}

object Kotlin {
    val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutineVersion}"
    val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutineVersion}"
}

object SupportLibs {
    val appCompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    val coreKtx = "androidx.core:core-ktx:${Version.corektx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerview}"
    val navigation = "androidx.navigation:navigation-ui-ktx:${Version.nav}"
    val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Version.nav}"
}

object Libraries {
    val koin = "org.koin:koin-android:${Version.koinVer}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Version.koinVer}"
    val koinExt = "org.koin:koin-android-ext:${Version.koinVer}"

    val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofitCoroutines}"
    val gson = "com.google.code.gson:gson:${Version.gsonVer}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVer}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofitGsonVer}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"

    val glide = "com.github.bumptech.glide:glide:${Version.glideVer}"

    val shapeOfView = "com.github.florent37:shapeofview:${Version.shapeOfView}"
    val loaderPack = "com.agrawalsuneet.androidlibs:squareloaderspack:${Version.loaderPack}"
}

object TestLibs {
    val junit_junit = "junit:junit:${Version.junitVersion}"
    val androidTestRunner = "androidx.test:runner:${Version.androidTestRunnerVer}"
    val espresso = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espressoCore}"
    val archCoreTest = "androidx.arch.core:core-testing:${Version.archCoreTestVer}"
    val junit = "androidx.test.ext:junit:${Version.androidJunit}"
    val fragmentNav = "androidx.fragment:fragment-testing:${Version.fragmentTest}"
    // KOIN
    val koin = "org.koin:koin-test:${Version.koinVer}"
    // MOCK WEBSERVER
    val mockWebServer = "com.squareup.okhttp:mockwebserver:${Version.mockwebserver}"
    // MOCK
    val mockkAndroid = "io.mockk:mockk-android:${Version.mockkVer}"
    val mockk = "io.mockk:mockk:${Version.mockkVer}"
    // COROUTINE
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutineVersion}"
    // DATA BINDING
    val dataBinding = "androidx.databinding:databinding-compiler:${Version.databinding}"
}

object Android {
    val minSdkVersion = 21
    val targetSdkVersion = 29
    val compileSdkVersion = 29
    val applicationId = "com.app.zuludin.mover"
    val versionCode = 1
    val versionName = "0.1"
    val buildToolsVersion = "29.0.2"
}