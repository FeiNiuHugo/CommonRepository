plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.hugo.trytospeak"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hugo.trytospeak"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

        ndk {
            abiFilters.add( "armeabi-v7a")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable = true
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
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.github.Justson:dispatch-queue:v1.0.5")
    implementation("com.github.Justson.AgentWeb:agentweb-core:v5.0.6-androidx")
    implementation("com.github.Justson.AgentWeb:agentweb-filechooser:v5.0.6-androidx")
    implementation("com.github.Justson:Downloader:v5.0.4-androidx")
    implementation("com.github.Ferfalk:SimpleSearchView:0.2.0") {
        exclude(group = "com.android.support")
    }
    implementation("top.zibin:Luban:1.1.8")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.airbnb.android:lottie:5.2.0")
    implementation("com.blankj:utilcodex:1.31.1")
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    implementation("com.tbruyelle.rxpermissions2:rxpermissions:0.9.5@aar")
    implementation("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation("io.reactivex.rxjava2:rxandroid:2.0.2")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("com.alibaba:arouter-api:1.5.2")
    kapt("com.alibaba:arouter-compiler:1.5.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(project(":lib_speech"))
}