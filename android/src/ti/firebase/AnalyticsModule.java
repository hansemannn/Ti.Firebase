package ti.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import com.google.firebase.analytics.FirebaseAnalytics;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

@Kroll.module(parentModule = FirebaseModule.class)
public class AnalyticsModule extends FirebaseModule {
	@Kroll.constant
	public static final int API_LEVEL = Build.VERSION.SDK_INT;

	private FirebaseAnalytics firebaseAnalytics;

	public AnalyticsModule() {
		super();
		firebaseAnalytics = FirebaseAnalytics.getInstance(TiApplication
				.getInstance().getApplicationContext());
	}

	@Override
	public String getApiName() {
		return "Ti.Platform.Android";
	}

	@Kroll.method
	public void sendEvent(KrollDict opts) {
		Bundle bundle = new Bundle();
		if (opts.containsKeyAndNotNull("id"))
			bundle.putString(FirebaseAnalytics.Param.ITEM_ID,
					opts.getString("id"));

		if (opts.containsKeyAndNotNull("name"))
			bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
					opts.getString("name"));
		if (opts.containsKeyAndNotNull("category"))
			bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,
					opts.getString("category"));

		firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,
				bundle);
	}
}
