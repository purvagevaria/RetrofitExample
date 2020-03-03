 //Retrofit dependecies
    //Rx java
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.x.x'

    //Retrofit dependencies
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.0'
    implementation 'com.squareup.okhttp3:okhttp:3.9.1'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'com.github.bumptech.glide:glide:4.9.0'

    //Room database
    def room_version = "2.2.0-rc01"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    
    
      <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

