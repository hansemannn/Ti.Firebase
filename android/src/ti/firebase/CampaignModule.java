package ti.firebase;

import org.appcelerator.kroll.annotations.Kroll;
import com.google.firebase.analytics.FirebaseAnalytics;

@Kroll.module(parentModule = AnalyticsModule.class)
public class CampaignModule extends FirebaseModule {
	@Kroll.constant
	final static String ACLID = FirebaseAnalytics.Param.ACLID;
	@Kroll.constant
	final static String CAMPAIGN = FirebaseAnalytics.Param.CAMPAIGN;
	@Kroll.constant
	final static String CONTENT = FirebaseAnalytics.Param.CONTENT;
	@Kroll.constant
	final static String CP1 = FirebaseAnalytics.Param.CP1;
	@Kroll.constant
	final static String MEDIUM = FirebaseAnalytics.Param.MEDIUM;
	@Kroll.constant
	final static String SOURCE = FirebaseAnalytics.Param.SOURCE;
	@Kroll.constant
	final static String TERM = FirebaseAnalytics.Param.TERM;

	public CampaignModule() {
		super();

	}
}
