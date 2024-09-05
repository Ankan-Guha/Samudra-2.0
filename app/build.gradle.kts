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
    // Material and UI dependencies
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")

    // Image libraries
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Google Maps and Location services
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Network libraries
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")

    // JSON parsing libraries
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20210307")

    // GraphView for tide graphs
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.code.gson:gson:2.9.0")

    // WebView support
    implementation("androidx.webkit:webkit:1.11.0")

    // Unit testing
    testImplementation("junit:junit:4.13.2")

    // Android testing
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
