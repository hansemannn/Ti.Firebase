Ti.Firebase
===========

This is the Titanium version of Firebase for Android. Currently the development is in working progress.
##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

For this you need your bundleID and optional the SHA1 key of you CERT.

Download and copy  this file into Ressources folder inside your project folder. The json file is then neighbor of app.js
In Alloy projects copy the file to `app/assets`

Usage
-------
Authentication
-----------------
Basics
------
The auth/creds wil read from google-services.json. YOu can overwrite this in method `initFirebase()`
```javascript
var FiBa =require("ti.firebase");
if (FiBa.initFirebase()) {
	var Auth = FiBa.createAuthentication();
	Auth.signInAnonymously({
		onComplete: function(_event) {
			if (_event) console.log(_event);
			var Db = FiBa.createDatabase();
			var Ref = Db.createReference("app:test");
			Ref.setValue({color:"braun",sound:"wau"});
			Ref.addEventListener("onDataChange",function(_event){
				console.log(_event);
			});
 		}
	}
});
Auth.addEventListener("onAuthStateChanged",function(_event) {
	if (_event) console.log(_event.user);
});

Auth.createUserWithEmailAndPassword("xxx@xxxx.de","sehrGeheim");

Auth.signInWithEmailAndPassword(email, password,
	onComplete: function(_event) {
 		if (_event) console.log(_event);
		
});

Auth.addEventListener("onAuthStateChanged",function(_event) {
	if (_event) console.log(_event.user);
});
```

RT Database
-----------
```javascript
var FiBa = require("ti.firebase");
var Db = FiBa.createDatabase();
var Ref = Db.createReference("Animals/Dog");
Ref.setValue({color:"braun",sound:"wau"});
Ref.addEventListener("onDataChange",function(_event){
	console.log(_event)
});

``` 

Storage
-------
The first step in accessing your storage bucket is to create an instance of FirebaseStorage:
```javascript

var FiBa = require("ti.firebase");
var storage = FiBa.createFirebasestorage();
``` 

Analytics
---------
Please follow [these instructions](https://support.google.com/adwords/answer/6366292?hl=en)

```javascript

var analytics = require("ti.firebase").Analytics;

analytics.sendEvent({
    analytics.ITEM_ID : "39836299",
    analytics.ITEM_NAME : "39836299",
    analytics.TAX : "39836.99",
    analytics.PRICE : "39836.99",
    analytics.ITEM_LOCATION_ID : "39836.99", // Google Place ID
    analytics.LEVEL : "39836.99", // Level in game (long).
    analytics.LOCATION : "3983699",
    campaignDetails : {
        analytics.CAMPAIGN_DETAIL_ACLID : "9e293283",
        analytics.CAMPAIGN_DETAIL_CAMPAIGN : "9e293283",
        analytics.CAMPAIGN_DETAIL_CONTENT : "9e293283",
        analytics.CAMPAIGN_DETAIL_CP1 : "9e293283",
        analytics.CAMPAIGN_DETAIL_MEDIUM : "9e293283",
        analytics.CAMPAIGN_DETAIL_SOURCE : "9e293283",
        analytics.CAMPAIGN_DETAIL_TERM : "9e293283"
    }
});

```
You can use all paramters from this [documentation](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param)


``` 


