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
	@Kroll.constant
	final String ACHIEVEMENT_ID = FirebaseAnalytics.Param.ACHIEVEMENT_ID;

	@Kroll.constant
	final String CHARACTER = FirebaseAnalytics.Param.CHARACTER;
	@Kroll.constant
	final String CONTENT_TYPE = FirebaseAnalytics.Param.CONTENT_TYPE;
	@Kroll.constant
	final String COUPON = FirebaseAnalytics.Param.COUPON;
	@Kroll.constant
	final String CURRENCY = FirebaseAnalytics.Param.CURRENCY;
	@Kroll.constant
	final String DESTINATION = FirebaseAnalytics.Param.DESTINATION;
	@Kroll.constant
	final String END_DATE = FirebaseAnalytics.Param.END_DATE;
	@Kroll.constant
	final String FLIGHT_NUMBER = FirebaseAnalytics.Param.FLIGHT_NUMBER;
	@Kroll.constant
	final String GROUP_ID = FirebaseAnalytics.Param.GROUP_ID;
	@Kroll.constant
	final String ITEM_CATEGORY = FirebaseAnalytics.Param.ITEM_CATEGORY;
	@Kroll.constant
	final String ITEM_ID = FirebaseAnalytics.Param.ITEM_ID;
	@Kroll.constant
	final String ITEM_LOCATION_ID = FirebaseAnalytics.Param.ITEM_LOCATION_ID;
	@Kroll.constant
	final String ITEM_NAME = FirebaseAnalytics.Param.ITEM_NAME;
	@Kroll.constant
	final String LEVEL = FirebaseAnalytics.Param.LEVEL;
	@Kroll.constant
	final String LOCATION = FirebaseAnalytics.Param.LOCATION;
	@Kroll.constant
	final String MEDIUM = FirebaseAnalytics.Param.MEDIUM;
	@Kroll.constant
	final String NUMBER_OF_NIGHTS = FirebaseAnalytics.Param.NUMBER_OF_NIGHTS;
	@Kroll.constant
	final String NUMBER_OF_PASSENGERS = FirebaseAnalytics.Param.NUMBER_OF_PASSENGERS;
	@Kroll.constant
	final String NUMBER_OF_ROOMS = FirebaseAnalytics.Param.NUMBER_OF_ROOMS;
	@Kroll.constant
	final String ORIGIN = FirebaseAnalytics.Param.ORIGIN;
	@Kroll.constant
	final String PRICE = FirebaseAnalytics.Param.PRICE;
	@Kroll.constant
	final String QUANTITY = FirebaseAnalytics.Param.QUANTITY;
	@Kroll.constant
	final String SCORE = FirebaseAnalytics.Param.SCORE;
	@Kroll.constant
	final String SEARCH_TERM = FirebaseAnalytics.Param.SEARCH_TERM;
	@Kroll.constant
	final String SHIPPING = FirebaseAnalytics.Param.SHIPPING;
	@Kroll.constant
	final String SIGN_UP_METHOD = FirebaseAnalytics.Param.SIGN_UP_METHOD;
	@Kroll.constant
	final String SOURCE = FirebaseAnalytics.Param.SOURCE;
	@Kroll.constant
	final String START_DATE = FirebaseAnalytics.Param.START_DATE;
	@Kroll.constant
	final String TAX = FirebaseAnalytics.Param.TAX;
	@Kroll.constant
	final String TRANSACTION_ID = FirebaseAnalytics.Param.TRANSACTION_ID;
	@Kroll.constant
	final String TRAVEL_CLASS = FirebaseAnalytics.Param.TRAVEL_CLASS;
	@Kroll.constant
	final String VALUE = FirebaseAnalytics.Param.VALUE;
	@Kroll.constant
	final String VIRTUAL_CURRENCY_NAME = FirebaseAnalytics.Param.VIRTUAL_CURRENCY_NAME;

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

	public KrollDict createCampaign(KrollDict c) {
		return c;
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
