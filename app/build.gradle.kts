plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}


// ========== 版本声明 ==========
val splashscreenVersion = "1.2.0-beta01"
val navVersion = "2.9.0-beta01"
val hiltVersion = "2.56.2"
val hiltNavigationVersion = "1.2.0"
val retrofitVersion = "2.11.0"
val coilVersion = "2.4.0"
val datastoreVersion = "1.2.0-alpha01"
val foundationVersion = "1.8.0-beta02"
val accompanistVersion = "0.36.0"
val pagingVersion = "3.3.1"
val pagingComposeVersion = "3.3.6"
val roomVersion = "2.7.0"

android {
    namespace = "org.wit.allfootballapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.wit.allfootballapp"
        minSdk = 28
        targetSdk = 35
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

    //icon extend
    implementation("androidx.compose.material:material-icons-extended-android:1.7.8")

    //serialized name
    implementation("com.google.code.gson:gson:2.9.0")

    // Splash API
    implementation("androidx.core:core-splashscreen:$splashscreenVersion")


    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Datastore
    implementation("androidx.datastore:datastore-preferences:$datastoreVersion")
    // Compose Foundation
    implementation("androidx.compose.foundation:foundation:$foundationVersion")
    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    // Paging 3
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingComposeVersion")
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

}