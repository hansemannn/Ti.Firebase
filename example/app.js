/* The the README.md for more examples */

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
