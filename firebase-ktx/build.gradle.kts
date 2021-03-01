plugins {
    id("com.android.library")
    id("kotlin-platform-android")
    id("org.jlleitschuh.gradle.ktlint")
    id("digital.wup.android-maven-publish").version("3.6.2")
    id("mirego.release").version("2.0")
    id("mirego.publish").version("1.0")
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://s3.amazonaws.com/mirego-maven/public")
}

group = "com.mirego.trikot.analytics"

configurations.forEach { it.exclude("org.reactivestreams") }

dependencies {
    api("com.mirego.trikot:analytics:2.0.0")
    api("com.mirego.trikot:streams:${project.extra["trikot_streams_version"]}")
    api("com.mirego.trikot:trikotFoundation:${project.extra["trikot_foundation_version"]}")
    implementation("com.google.firebase:firebase-analytics:18.0.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.3.0")
}

android {
    buildToolsVersion = "29.0.3"
    defaultConfig {
        compileSdkVersion(30)
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

release {
    checkTasks = listOf("check")
    buildTasks = listOf("publish")
    updateVersionPart = 2
    tagPrefix = "firebase-ktx-"
}

publishing {
    publications {
        register("mavenAar", MavenPublication::class) {
            from(components["android"])
        }
    }
}
