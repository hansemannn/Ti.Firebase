Ti.Firebase
===========

This is the Titanium version of Firebase for Android. Currently it doesn't work, because the question of [playservices.json](https://developers.google.com/android/guides/google-services-plugin)
 not solved yet.

##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

Then you have to copy this file into 'platform/android' in your project folder.

For this you need your bundleID and optional the SHA1 key of you CERT.
Goggle gives you this file:
```json
{
  "project_info": {
    "project_number": "4434****480",
    "firebase_url": "https://messaging-nixzulesen.firebaseio.com",
    "project_id": "messaging-nixzulesen",
    "storage_bucket": "messaging-nixzulesen.appspot.com"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:44348****droid:404d2XX32dedc",
        "android_client_info": {
          "package_name": "com.test.firebase"
        }
      },
      "oauth_client": [
        {
          "client_id": "443482********"
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCZ0Zc******tQasE4lcbprd4"
        }
      ],
      "services": {
        "analytics_service": {
          "status": 1
        },
        "appinvite_service": {
          "status": 1,
          "other_platform_oauth_client": []
        },
        "ads_service": {
          "status": 2
        }
      }
    }
  ],
  "configuration_version": "1"
}
```
But the build system aspects a xml file in res folder like this:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="default_web_client_id" translatable="false">44348****ifdng4gfjujdj80u2tqffqvtvieqac.apps.googleusercontent.com</string>
    <string name="firebase_database_url" translatable="false">https://messaging-nixzulesen.firebaseio.com</string>
    <string name="gcm_defaultSenderId" translatable="false">443482612480</string>
    <string name="google_api_key" translatable="false">AIzaSyC******QasE4lcbprd4</string>
    <string name="google_app_id" translatable="false">1:443****480:android:404d21998632dedc</string>
    <string name="google_crash_reporting_api_key" translatable="false">AIzaSy****tQasE4lcbprd4</string>
    <string name="google_storage_bucket" translatable="false">messaging-nixzulesen.appspot.com</string>
</resources>
```
You can manually convert or use a [gradle script](https://developers.google.com/android/guides/google-services-plugin).
After downloading you can copy the file in MODULE/android folder. 
In this folder you need an additional file `build.gradle` with this content (maybe you have to edit paths):
```
buildscript {
    repositories {
        jcenter()
    }

    dependencies {	
        classpath 'com.android.tools.build:gradle:2.2.3'
        classpath 'com.google.gms:google-services:3.0.0'
    }
}


task jar(type: Jar) {
    from 'android.sourceSets.main.java.srcDirs'
}


apply plugin: 'com.android.application'


android {
  compileSdkVersion 23
  buildToolsVersion "23"
}

dependencies {
  // ...
  compile 'com.google.firebase:firebase-core:9.6.1'
  compile 'com.google.firebase:firebase-database:9.6.1'
  compile 'com.google.firebase:firebase-auth:9.6.1'
  compile 'com.google.firebase:firebase-messaging:9.6.1'
}

apply plugin: 'com.google.gms.google-services'
```


In this folder you start in a shell:
```bash
gradle processReleaseGoogleServices
```
You will find `gradle` and `processReleaseGoogleServices` is missing.

You can install `gradle` with brew.

##Usage

###Authentication
####Basics

```javascript
var Auth = FiBa.createAuthentication();

Auth.signInAnonymously({
	onComplete: function(_event) {
		if (_event) console.log(_event);
	}
});

Auth.createUserWithEmailAndPassword("xxx@xxxx.de","sehrGeheim");

Auth.signInWithEmailAndPassword(email, password,
	onComplete: function(_event) {
 		if (_event) console.log(_event);
		
})

Auth.addEventListener("onAuthStateChanged",function(_event) {
	if (_event) console.log(_event.user);
});
```

###Database
```javascript
var FiBa = require("de.appwerft.firebase");
var Db = FiBa.createDatabase();
var Ref = Db.createReference("Animals/Dog");
Ref.setValue({color:"braun",sound:"wau"});
Ref.addEventListener("onDataChange",function(_event){

});

``` 

###Storage
The first step in accessing your storage bucket is to create an instance of FirebaseStorage:
```javascript

var FiBa = require("de.appwerft.firebase");
var storage = FiBa.createFirebasestorage();
``` 