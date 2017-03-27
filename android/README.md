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

var Analytics = require("ti.firebase").Analytics;

Analytics.sendEvent({
    Analytics.ITEM_ID : "39836299",
    Analytics.ITEM_NAME : "39836299",
    Analytics.TAX : "19.0",
    Analytics.CURRENCY : "EUR",
    Analytics.PRICE : "39836.99",
    Analytics.START_DATE : "2017-02-28",
    Analytics.SIGN_UP : "google",
    Analytics.SHIPPING : "9.40",
    Analytics.ITEM_LOCATION_ID : "ChIJiyj437sx3YAR9kUWC8QkLzQ", // Google Place ID
    Analytics.LEVEL : "39836.99", // Level in game (long).
    Analytics.LOCATION : "3983699",
    Analytics.ACHIEVEMENT_ID : "10_matches_won", // Game achievement ID (String)
    Analytics.ACLID : "12334", 
    campaignDetails : Analytics.createCampaign({
        Analytics.Campaign.ACLID : "9e293283",
        Analytics.Campaign.CAMPAIGN : "9e293283",
        Analytics.Campaign.CONTENT : "9e293283",
        Analytics.Campaign.CP1 : "9e293283",
        Analytics.Campaign.MEDIUM : "9e293283",
        Analytics.Campaign.SOURCE : "9e293283",
        Analytics.Campaign.TERM : "9e293283"
    })
});

```
You can use all paramters from this [documentation](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param)


``` 


