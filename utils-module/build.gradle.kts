plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)


    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    namespace = "com.project.utils"

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation("androidx.core:core-ktx:1.1.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.10.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.10.0")

    //Room
    implementation("androidx.room:room-runtime:2.2.3")
    implementation("androidx.room:room-rxjava2:2.2.3")
    kapt("androidx.room:room-compiler:2.2.3")

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.testJunit)
    androidTestImplementation(TestLibs.esporesso)
}

