package de.appwerft.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
//import org.appcelerator.titanium.TiContext.OnLifecycleEvent;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

//import org.appcelerator.titanium.TiBaseActivity;

@Kroll.proxy(creatableInModule = FirebaseModule.class)
public class AuthenticationProxy extends KrollProxy {

	private final class AuthStateHandler implements
			FirebaseAuth.AuthStateListener {
		@Override
		public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
			FirebaseUser user = firebaseAuth.getCurrentUser();
			KrollDict result = new KrollDict();
			if (hasListeners("onAuthStateChanged"))
				if (user != null) {
					result.put("uid", user.getUid());
					if (user.getDisplayName() != null)
						result.put("displayName", user.getDisplayName());
					if (user.getEmail() != null)
						result.put("email", user.getEmail());
					if (user.getPhotoUrl() != null)
						result.put("photoUrl", user.getPhotoUrl());
					if (hasListeners("onAuthStateChanged"))
						fireEvent("onAuthStateChanged", result);
					Log.d(LCAT, result.toString());
				} else {
					Log.e(LCAT, "user was null in onAuthStateChanged");
				}
		}
	}

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
		Activity activity = TiApplication.getAppRootOrCurrentActivity();
		if (opts.containsKeyAndNotNull("onComplete")) {
			Object o = opts.get("onComplete");
			if (o instanceof KrollFunction) {
				onComplete = (KrollFunction) o;
				Log.d(LCAT, "onComplete callback imported");
			}
		}
		Log.e(LCAT, "opts in signInAnonymously imported, try to initAuth()");
		auth = FirebaseModule.auth;
		if (auth == null) {
			Log.e(LCAT, "auth was null");
			return;
		}
		Log.d(LCAT,
				"auth created, try to add AuthStateListener " + auth.toString());
		auth.addAuthStateListener(new AuthStateHandler());
		Log.e(LCAT, "auth was not null, try to signInAnonymously");
		if (activity != null) {
			Log.d(LCAT, activity.toString());
			auth.signInAnonymously().addOnCompleteListener(activity,
					new OnCompleteHandler());
		} else
			Log.e(LCAT, "activity was null");
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

}