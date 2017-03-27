package ti.firebase;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

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
	final String CAMPAIGN_DETAILS = FirebaseAnalytics.Event.CAMPAIGN_DETAILS;
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

	public KrollDict createEvent(KrollDict event) {
		return event;
	}

	@Kroll.method
	public void selectContent(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, opts);
	}

	@Kroll.method
	public void addPaymentInfo(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, opts);
	}

	@Kroll.method
	public void addToCart(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.ADD_TO_CART, opts);
	}

	@Kroll.method
	public void addToWishlist(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, opts);
	}

	@Kroll.method
	public void appOpen(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.APP_OPEN, opts);
	}

	@Kroll.method
	public void beginCheckout(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, opts);
	}

	@Kroll.method
	public void compainDetails(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.CAMPAIGN_DETAILS, opts);
	}

	@Kroll.method
	public void earnVirtualCurrency(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.EARN_VIRTUAL_CURRENCY, opts);
	}

	@Kroll.method
	public void ecommercePurchase(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, opts);
	}

	@Kroll.method
	public void generateLead(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.GENERATE_LEAD, opts);
	}

	@Kroll.method
	public void joinGroup(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.JOIN_GROUP, opts);
	}

	@Kroll.method
	public void levelUp(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.LEVEL_UP, opts);
	}

	@Kroll.method
	public void login(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.LOGIN, opts);
	}

	@Kroll.method
	public void postScore(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.POST_SCORE, opts);
	}

	@Kroll.method
	public void presentOffer(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.PRESENT_OFFER, opts);
	}

	@Kroll.method
	public void purchaseRefound(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.PURCHASE_REFUND, opts);
	}

	@Kroll.method
	public void search(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.SEARCH, opts);
	}

	@Kroll.method
	public void share(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.SHARE, opts);
	}

	@Kroll.method
	public void signUp(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.SIGN_UP, opts);
	}

	@Kroll.method
	public void spendVirtualCurrency(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY, opts);
	}

	@Kroll.method
	public void tutorialBegin(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.TUTORIAL_BEGIN, opts);
	}

	@Kroll.method
	public void tutorialComplete(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.TUTORIAL_COMPLETE, opts);
	}

	@Kroll.method
	public void unlockArchievement(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.UNLOCK_ACHIEVEMENT, opts);
	}

	@Kroll.method
	public void viewItem(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.VIEW_ITEM, opts);
	}

	@Kroll.method
	public void viewItemList(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, opts);
	}

	@Kroll.method
	public void viewSearchResults(KrollDict opts) {
		sendEvent(FirebaseAnalytics.Event.VIEW_SEARCH_RESULTS, opts);
	}

	@Kroll.method
	public void setUserProperty(String key, String value) {
		firebaseAnalytics.setUserProperty(key, value);
	}

	private void sendEvent(String event, KrollDict opts) {
		Bundle bundle = new Bundle();
		String[] strings = { ACHIEVEMENT_ID, CHARACTER, CONTENT_TYPE, COUPON,
				CURRENCY, DESTINATION, END_DATE, FLIGHT_NUMBER, GROUP_ID,
				ITEM_CATEGORY, ITEM_ID, ITEM_LOCATION_ID, ITEM_NAME, LOCATION,
				ORIGIN, SEARCH_TERM, SIGN_UP_METHOD, START_DATE,
				TRANSACTION_ID, TRAVEL_CLASS, VIRTUAL_CURRENCY_NAME };
		String[] longs = { LEVEL, NUMBER_OF_NIGHTS, NUMBER_OF_PASSENGERS,
				NUMBER_OF_ROOMS, PRICE, QUANTITY, SCORE, SHIPPING, TAX, VALUE };
		for (String s : strings) {
			if (opts.containsKeyAndNotNull(s))
				bundle.putString(s, opts.getString(s));
		}
		for (String s : longs) {
			if (opts.containsKeyAndNotNull(s))
				bundle.putLong(s, (long) opts.get(s));
		}
		firebaseAnalytics.logEvent(event, bundle);
	}
}
