buildscript {
    dependencies {
        classpath("com.alibaba:arouter-register:1.0.2")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.1" apply false
}

apply {
    plugin("com.alibaba.arouter")
}