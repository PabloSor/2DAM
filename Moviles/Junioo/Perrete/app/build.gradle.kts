plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.pablosor.perros"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pablosor.perros"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Credential manager de firebase
    implementation(libs.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.gson)
    implementation (libs.retrofit2.converter.gson)

    // Corroutines
    implementation(libs.kotlinx.coroutines.android)

    // Picasso
    implementation(libs.picasso)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}