Ti.Firebase
===========

This is the Titanium version of Firebase for Android. Currently it doesn't work, because the question of [playservices.json](https://developers.google.com/android/guides/google-services-plugin)
 not solved yet.

##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

Then you have to copy this file into 'platform/android' in your project folder.

##Usage

First you have to generate a google-services.json on your [firebase console](https://console.firebase.google.com/). 
For this you need your bundleID and optional the SHA1 key of you CERT.
Goggle gives you this file:
```json
{
  "project_info": {
    "project_number": "443482612480",
    "firebase_url": "https://messaging-b1607.firebaseio.com",
    "project_id": "messaging-b1607",
    "storage_bucket": "messaging-b1607.appspot.com"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:443482612480:android:404d21998632dedc",
        "android_client_info": {
          "package_name": "com.test.firebase"
        }
      },
      "oauth_client": [
        {
          "client_id": "443482612480-43ifdng4gfjujdj80u2tqffqvtvieqac.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCZ0ZcNbV5JtfJxKLHYsPtQasE4lcbprd4"
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
    <string name="default_web_client_id" translatable="false">443482612480-43ifdng4gfjujdj80u2tqffqvtvieqac.apps.googleusercontent.com</string>
    <string name="firebase_database_url" translatable="false">https://messaging-b1607.firebaseio.com</string>
    <string name="gcm_defaultSenderId" translatable="false">443482612480</string>
    <string name="google_api_key" translatable="false">AIzaSyCZ0ZcNbV5JtfJxKLHYsPtQasE4lcbprd4</string>
    <string name="google_app_id" translatable="false">1:443482612480:android:404d21998632dedc</string>
    <string name="google_crash_reporting_api_key" translatable="false">AIzaSyCZ0ZcNbV5JtfJxKLHYsPtQasE4lcbprd4</string>
    <string name="google_storage_bucket" translatable="false">messaging-b1607.appspot.com</string>
</resources>
```
You can manually convert or use a gradle script.
After downloading you can copy the file in your project folder. In this folder you start in a shell:
```bash
gradle processReleaseGoogleServices
```
You will find `gradle` and `processReleaseGoogleServices` is missing.

You can install `gradle` with brew.



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