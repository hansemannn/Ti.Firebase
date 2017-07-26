package ti.firebase;

import java.io.IOException;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import com.google.firebase.iid.*;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

@Kroll.module(parentModule = FirebaseModule.class)
public class CloudmessagingModule extends FirebaseModule {

	public CloudmessagingModule() {
		super();

	}

	@Kroll.method
	public static synchronized String getToken() {
		return FirebaseInstanceId.getInstance().getToken();
	}

	@Kroll.method
	public void deleteToken(String token, String scope) {
		try {
			FirebaseInstanceId.getInstance().deleteToken(token, scope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Kroll.method
	public String getCreationTime() {
		return FirebaseInstanceId.getInstance().getToken();
	}

	@Override
	public String getApiName() {
		return "Ti.Firebase.CloudMessaging";
	}

}
