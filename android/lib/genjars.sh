#!/bin/sh

VERSION=9.6.0
GOOGLE=/opt/android-sdk/extras/google/m2repository/com/google
AAR2JAR=/usr/local/bin/aar2jar.sh

rm *.jar
#$AAR2JAR $GOOGLE/android/gms/play-services/$VERSION/play-services-$VERSION.aar .
$AAR2JAR $GOOGLE/android/gms/play-services-gcm/$VERSION/play-services-gcm-$VERSION.aar .
$AAR2JAR $GOOGLE/android/gms/play-services-base/$VERSION/play-services-base-$VERSION.aar .
$AAR2JAR $GOOGLE/android/gms/play-services-basement/$VERSION/play-services-basement-$VERSION.aar .
$AAR2JAR $GOOGLE/android/gms/play-services-tasks/$VERSION/play-services-tasks-$VERSION.aar .



$AAR2JAR $GOOGLE/firebase/firebase-analytics/$VERSION/firebase-analytics-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-analytics-impl/$VERSION/firebase-analytics-impl-$VERSION.aar .

$AAR2JAR $GOOGLE/firebase/firebase-auth/$VERSION/firebase-auth-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-auth-common/$VERSION/firebase-auth-common-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-auth-impl/$VERSION/firebase-auth-impl-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-auth-module/$VERSION/firebase-auth-module-$VERSION.aar .

$AAR2JAR $GOOGLE/firebase/firebase-common/$VERSION/firebase-common-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-config/$VERSION/firebase-config-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-crash/$VERSION/firebase-crash-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-database/$VERSION/firebase-database-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-database-connection/$VERSION/firebase-database-connection-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-iid/$VERSION/firebase-iid-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-messaging/$VERSION/firebase-messaging-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-storage/$VERSION/firebase-storage-$VERSION.aar .
$AAR2JAR $GOOGLE/firebase/firebase-storage-common/$VERSION/firebase-storage-common-$VERSION.aar .

ls -l *.jar