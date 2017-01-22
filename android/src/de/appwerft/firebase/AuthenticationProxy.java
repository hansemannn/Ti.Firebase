package de.appwerft.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;

//import android.app.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import org.appcelerator.titanium.TiBaseActivity;

@Kroll.proxy(creatableInModule = FirebaseModule.class)
public class AuthenticationProxy extends KrollProxy implements OnLifecycleEvent {
	private final class OnCompleteHandler implements
			OnCompleteListener<AuthResult> {
		@Override
		public void onComplete(Task<AuthResult> task) {
			if (onComplete != null) {
				KrollDict kd = new KrollDict();
				kd.put("success", task.isSuccessful());
				onComplete.call(getKrollObject(), kd);
			}
		}
	}

	KrollProxy proxy;
	private FirebaseAuth auth;

	private FirebaseAuth.AuthStateListener authListener;
	private static final String LCAT = "FiBa 🚝🚝";
	KrollFunction onComplete;

	public AuthenticationProxy() {
		super();
	}

	@Kroll.method
	public void signInAnonymously(KrollDict opts) {
		if (opts.containsKeyAndNotNull("onComplete")) {
			Object o = opts.get("onComplete");
			if (o instanceof KrollFunction) {
				onComplete = (KrollFunction) o;
			}
		}
		if (auth != null) {
			auth.signInAnonymously().addOnCompleteListener(activity,
					new OnCompleteHandler());
		} else
			Log.e(LCAT, "auth was null, cannot do some magic");
	}

	@Kroll.method
	public void createUserWithEmailAndPassword(String email, String password) {
		auth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(activity, new OnCompleteHandler());

	}

	@Kroll.method
	public void signInWithEmailAndPassword(String email, String password) {
		auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
				activity, new OnCompleteHandler());
	}

	@Kroll.method
	public void signInGithub(String token) {
		AuthCredential credential = GithubAuthProvider.getCredential(token);
		auth.signInWithCredential(credential).addOnCompleteListener(activity,
				new OnCompleteHandler());
	}

	@Override
	public void onCreate(Activity activity, Bundle unused) {
		((TiBaseActivity) activity).addOnLifecycleEventListener(this);
		this.activity = activity;
		Log.e(LCAT, "try to get instance of FirebaseAuth. ");
		auth = FirebaseAuth.getInstance();
		authListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				KrollDict result = new KrollDict();
				if (proxy.hasListeners("onAuthStateChanged"))
					if (user != null) {
						result.put("displayName", user.getDisplayName());
						result.put("uid", user.getUid());
						result.put("email", user.getEmail());
						result.put("photoUrl", user.getPhotoUrl());
						proxy.fireEvent("onAuthStateChanged", result);
						// User is signed in
					} else {
						// User is signed out
					}
			}
		};
	}

	@Override
	public void onStart(Activity activity) {
		super.onStart(activity);
		// auth.addAuthStateListener(authListener);
	}

	@Override
	public void onStop(Activity activity) {
		if (authListener != null) {
			auth.removeAuthStateListener(authListener);
		}
		super.onStop(activity);
	}
}