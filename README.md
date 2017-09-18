# Ti.Firebase

This is the Titanium version of Firebase for Android. Currently the development is in working progress.

## Setup
First you have to create a `google-services.json` following the instructions of the [Firebase docs](https://firebase.google.com/docs/android/setup).

For this, you need your `bundleID` and optional the SHA1 key of you CERT.

Download and copy this file into the `Resources/`  (Titanium classic) or `app/assets/android/` (Alloy)
directory.

## Usage

### Authentication

#### Basics
The credentials will be read from the `google-services.json`. You can overwrite those keys
in the  `initFirebase()` method.

#### Log-In anonymously
```js
var Firebase = require('ti.firebase');

if (Firebase.initFirebaseApp()) {
  var Auth = Firebase.createAuthentication();
  Auth.signInAnonymously({
    onComplete: function(_event) {
      if (_event) {
        console.log(_event);
      }
    }
  }
});

Auth.addEventListener('onAuthStateChanged', function(e) {
  if (e) {
    console.log(e.user);
  }
});
```

#### Log-In with E-Mail and Password
```js
Auth.createUserWithEmailAndPassword('xxx@xxxx.de', 'p4$$w0r4!');

Auth.signInWithEmailAndPassword(email, password,
  onComplete: function(e) {
    if (e) {
      console.log(e);
    }
});

Auth.addEventListener('onAuthStateChanged', function(e) {
  if (e) {
    console.log(e.user);
  }
});
```

### Cloud messaging
Receive the token to be used for cloud-messaging.
```js
var token = require('ti.firebase').CloudMessaging.getToken();
```

### Database
```js
var Firebase = require('ti.firebase');

var database = Firebase.createDatabase();
var reference = database.createReference('app:test');
reference.addEventListener('onDataChange', function(e) {
  console.log(e);
});
reference.setValue({ color: 'brown', sound: 'beem' });
``` 

### Storage
The first step in order to access your storage bucket is to create an instance of the `FirebaseStorage`:
```js

var Firebase = require('ti.firebase');
var storage = Firebase.createFirebasestorage();
``` 

### Analytics
Please follow [these instructions](https://support.google.com/adwords/answer/6366292?hl=en).

```js
var Firebase = require('ti.firebase');
var success = FireBase.initFirebaseApp();

if (!success) {
  Ti.API.error('Error initializing Firebase!');
  return;
}

var event = Firebase.Analytics.createEvent({
  Firebase.Analytics.ITEM_ID: '39836299',
  Firebase.Analytics.ITEM_NAME: '39836299',
  Firebase.Analytics.TAX: '19.0',
  Firebase.Analytics.CURRENCY: 'EUR',
  Firebase.Analytics.PRICE: '12',
  Firebase.Analytics.START_DATE: '2017-02-28',
  Firebase.Analytics.END_DATE: '2017-02-28',
  Firebase.Analytics.SIGN_UP: 'google',
  Firebase.Analytics.SHIPPING: '940',
  Firebase.Analytics.ITEM_LOCATION_ID: 'ChIJiyj437sx3YAR9kUWC8QkLzQ', // Google Place ID
  Firebase.Analytics.LEVEL: '398369', // Level in game (long).
  Firebase.Analytics.LOCATION: '3983699',
  Firebase.Analytics.ACHIEVEMENT_ID: '10_matches_won', // Game achievement ID (String)
});
```

Now you can send some events:
```js
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

### Change User
Sets a user property to a given value. Up to 25 user property names are supported.
Once set, the user property values persist throughout the app lifecycle and across sessions.
```js
Firebase.Analytics.setUserId(id);
Firebase.Analytics.setUserProperty('favorite_food','dogs sweet sour');
```

You can use all parameters from this [documentation](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param).
