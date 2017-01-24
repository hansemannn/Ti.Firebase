package de.appwerft.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
//import org.appcelerator.titanium.TiContext.OnLifecycleEvent;
import org.appcelerator.titanium.TiBaseActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

//import org.appcelerator.titanium.TiBaseActivity;

@Kroll.proxy(creatableInModule = FirebaseModule.class)
public class AuthenticationProxy extends KrollProxy {
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
	Activity activity = FirebaseModule.activity;
	private FirebaseAuth.AuthStateListener authListener;
	private static final String LCAT = FirebaseModule.LCAT;
	KrollFunction onComplete;

	public AuthenticationProxy() {
		super();
	}

	@Kroll.method
	public void signInAnonymously(KrollDict opts) {
		Log.d(LCAT, " signInAnonymously");
		Activity activity = TiApplication.getAppCurrentActivity();
		if (opts.containsKeyAndNotNull("onComplete")) {
			Object o = opts.get("onComplete");
			if (o instanceof KrollFunction) {
				onComplete = (KrollFunction) o;
				Log.d(LCAT, "onComplete callback imported");
			}
		}
		initAuth();
		if (auth != null) {
			Log.e(LCAT, "auth was not null, try to signInAnonymously");
			Log.d(LCAT, activity.toString());
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

	private void initAuth() {
		auth = FirebaseModule.auth;
		Log.d(LCAT,
				"auth created, try to add AuthStateListener " + auth.toString());
		authListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				Log.d(LCAT, user.toString());
				KrollDict result = new KrollDict();
				if (hasListeners("onAuthStateChanged"))
					if (user != null) {
						result.put("displayName", user.getDisplayName());
						result.put("uid", user.getUid());
						result.put("email", user.getEmail());
						result.put("photoUrl", user.getPhotoUrl());
						fireEvent("onAuthStateChanged", result);
						Log.d(LCAT, result.toString());
						// User is signed in
					} else {
						// User is signed out
					}
			}
		};
		auth.addAuthStateListener(authListener);
	}

}