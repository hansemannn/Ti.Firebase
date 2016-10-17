Ti.Firebase
===========

This is the Titanium version of Firebase for Android.

##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

Then you have to copy this file into 'platform/android' in your project folder.

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
var Ref = Db.createReference("Dog");
Ref.setValue("Wau");
Ref.addEventListener("onDataChange",function(_event){

});

``` 

###Storage
The first step in accessing your storage bucket is to create an instance of FirebaseStorage:
```javascript

var FiBa = require("de.appwerft.firebase");
var storage = FiBa.createFirebasestorage();
``` 