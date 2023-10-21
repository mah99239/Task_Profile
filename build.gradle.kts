// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
                classpath( libs.kotlin.gradle.plugin)
        classpath (libs.navigation.safe.args)

    }
    repositories {
        mavenCentral()
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false

}
