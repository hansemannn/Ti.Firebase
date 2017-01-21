#!/bin/bash

APPID=de.appwerft.firebase
VERSION=1.0.0


cd android;ant clean; rm -rf build/*;ant ;  unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..
