Ti.Firebase
===========

This is the Titanium version of Firebase for Android.

##Setup
First you have to create a google-services.json following the instructions of [original firebase page](https://firebase.google.com/docs/android/setup)

Then you have to copy this file into 'platform/android' in your project folder.

##Usage
The first step in accessing your storage bucket is to create an instance of FirebaseStorage:

```javascript

var FiBa = require("de.appwerft.firebase");
var storage = FiBa.createFirebasestorage();
``` 