plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.samudra20"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.samudra20"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("org.json:json:20210307")  // For parsing JSON data
    implementation("com.squareup.okhttp3:okhttp:4.9.3")  // For network requests
    implementation ("de.hdodenhof:circleimageview:3.1.0" )
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.webkit:webkit:1.11.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("org.json:json:20210307")

    // For making HTTP requests
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")

// For JSON parsing
    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("com.squareup.okhttp3:okhttp:4.9.3")

    implementation ("com.squareup.okhttp3:okhttp:4.9.2")
    implementation ("com.google.code.gson:gson:2.8.7")


}