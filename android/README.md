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
var Firebase = require("ti.firebase");
FireBase.init();

var event = Firebase.Analytics.createEvent({
    Firebase.Analytics.ITEM_ID : "39836299",
    Firebase.Analytics.ITEM_NAME : "39836299",
    Firebase.Analytics.TAX : "19.0",
    Firebase.Analytics.CURRENCY : "EUR",
    Firebase.Analytics.PRICE : "12",
    Firebase.Analytics.START_DATE : "2017-02-28",
    Firebase.Analytics.END_DATE : "2017-02-28",
    Firebase.Analytics.SIGN_UP : "google",
    Firebase.Analytics.SHIPPING : "940",
    Firebase.Analytics.ITEM_LOCATION_ID : "ChIJiyj437sx3YAR9kUWC8QkLzQ", // Google Place ID
    Firebase.Analytics.LEVEL : "398369", // Level in game (long).
    Firebase.Analytics.LOCATION : "3983699",
    Firebase.Analytics.ACHIEVEMENT_ID : "10_matches_won", // Game achievement ID (String)
});
```
Now you can send some events:
```javascript
Firebase.Analytics.selectContent(event);
Firebase.Analytics.addPaymentInfo(event);
Firebase.Analytics.addToCart(event);
Firebase.Analytics.addToWishlist(event);
Firebase.Analytics.appOpen(event);
Firebase.Analytics.beginCheckout(event);
Firebase.Analytics.compainDetails(event);
Firebase.Analytics.earnVirtualCurrency(event);
Firebase.Analytics.ecommercePurchase(event);
Firebase.Analytics.generateLead(event);
Firebase.Analytics.joinGroup(event);
Firebase.Analytics.levelUp(event);
Firebase.Analytics.login(event);
Firebase.Analytics.postScore(event);
Firebase.Analytics.presentOffer(event);
Firebase.Analytics.purchaseRefound(event);
Firebase.Analytics.search(event);
Firebase.Analytics.share(event);
Firebase.Analytics.signUp(event);
Firebase.Analytics.spendVirtualCurrency(event);
Firebase.Analytics.tutorialBegin(event);
Firebase.Analytics.tutorialComplete(event);
Firebase.Analytics.unlockArchievement(event);
Firebase.Analytics.viewItem(event);
Firebase.Analytics.viewItemList(event);
Firebase.Analytics.viewSearchResults(event);

```
Sets a user property to a given value. Up to 25 user property names are supported. Once set, user property values persist throughout the app lifecycle and across sessions.
```javascript
Firebase.Analytics.setUserId(id);
Firebase.Analytics.setUserProperty("favorite_food","dogs sweet sour");
```


You can use all paramters from this [documentation](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param)


``` 


