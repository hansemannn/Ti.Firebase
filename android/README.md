Ti.Firebase
===========

This is the Titanium version of Firebase for Android. Currently it doesn't work, because the question of [playservices.json](https://developers.google.com/android/guides/google-services-plugin)
 not solved yet.

##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

For this you need your bundleID and optional the SHA1 key of you CERT.

Download and copy  this file into Ressources folder inside your project folder.

##Usage

###Authentication
####Basics

```javascript
var FiBa =require("de.appwerft.firebase");

// first initalizing, options are optional, comes from JOSN file in your Ressources folder
FiBa.initFirebase({
	apiKey:"AIzaSyB2RC****9q5uxjPl0h41qnp3QPcw",  //optional
	applicationId :"1:531***4300:android:39aa7ff12f2dfd4b" //optional
});

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

0
