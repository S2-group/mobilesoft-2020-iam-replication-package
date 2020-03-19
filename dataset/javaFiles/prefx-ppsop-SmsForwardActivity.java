package oshai.prefx;


import java.util.List;

import oshai.prefx.activities.PrefxActivity;
import oshai.prefx.common.Constants;
import oshai.prefx.common.ErrorReporting;
import oshai.prefx.common.SmsType;
import oshai.prefx.phone_numbers.PhoneNumberNormalizer;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;

public class SmsForwardActivity extends PrefxActivity {

	private static final String TAG = SmsForwardActivity.class.getSimpleName();
	private SmsType smsType;
	@Inject private PhoneNumberNormalizer phoneNumberNormalizer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		smsType = (SmsType) getIntent().getExtras().get(Constants.INTENT_PARAM_SMS_TYPE);
		forwardSmsByType();
		finish();
	}
	
	private void forwardSmsByType() {
		Log.i(TAG, "forwarding " + getDataString() + " to " + smsType);
		trackForwardingEvent();
//		checkNewTypes();
		try {
			switch (smsType)
			{
			case DeviceBuiltIn:
				openMessageOnDeviceBuiltin();
				break;
			default:
				openMessageForProvider();
				break;
			}
		} catch (Exception e) {
			new ErrorReporting().handle(e);
			if (!SmsType.DeviceBuiltIn.equals(smsType)) {
				//will try the default
				try {
					openMessageOnDeviceBuiltin();
				} catch (Exception e1) {
					new ErrorReporting().handle(e1);
				}
			}
		}
	}

	private void trackForwardingEvent() {
		SharedPreferences settings = getSharedPreferences(Constants.PREFERENCE_STORE, 0);
		String prefix = settings.getString(Constants.PREFERENCE_COUNTRY_PREFIX, null);
		String phone = settings.getString(Constants.PREFERENCE_PHONE_NUMBER, null);
		trackEvent("forwarding","from_"+prefix+"-"+phone+"_by_"+smsType+"_to_"+getDataString(),smsType.toString(),1);
	}
	private String getDataString() {
		Log.i(TAG, "getDataString() " + getOriginalIntent().getDataString());
		return getOriginalIntent().getDataString();
	}
	
	private void openMessageOnDeviceBuiltin() {
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//		if (invitationSendSmsFirstTimerStrategy) {
//			sendIntent.putExtra("sms_body", "Check out Prefx! Download it today at "
//					+ LINK_TO_APP);
//		}
		sendIntent.setData(getCorrectedData());
		sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(sendIntent);
	}
	
	private void openMessageForProvider() {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setComponent(new ComponentName(smsType.packageName(), smsType.className()));
		Builder builder = new Uri.Builder();
		builder.scheme("smsto");
		String normalizedNumberForSkype = phoneNumberNormalizer.getNormalizedNumberForSkype(getDataString());
		builder.encodedOpaquePart(Uri.encode(normalizedNumberForSkype));
		Uri uri = builder.build();
		uri.getSchemeSpecificPart();
		intent.setData(uri);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private Uri getCorrectedData() {
		return Uri.parse(phoneNumberNormalizer.getNormalizedNumberWithCountryPrefixAndSmsto(getDataString()));
	}
	
	public void checkNewTypes() {
//        Bundle localBundle = new Bundle();
//        localBundle.putBoolean("no_animations", true);
//        long l = SystemClock.currentThreadTimeMillis();
//        localBundle.putLong("wall_identifier", l);
//        localBundle.putString("phone", phoneNumberNormalizer.getNormalizedNumberWithPrefix(getDataString()));
//        intent = intent.replaceExtras(localBundle);
//		new ManifestEncoder().getIntents("/data/app/com.skype.raider-1.apk");
		List<PackageInfo> p = getApplicationContext().getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
		for (int i = 0; i < p.size(); i++) {
			System.out.println(i + " " + p.get(i));
		}
		try {
			PackageInfo a = getApplicationContext().getPackageManager().getPackageInfo("com.viber.voip", PackageManager.GET_ACTIVITIES);
			for (int i = 0; i < a.activities.length; i++) {
				if (a.activities[i].exported)
				System.out.println(i + " " +a.activities[i]);
			}
			ActivityInfo activityInfo = a.activities[27];
			System.out.println(activityInfo);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Intent getOriginalIntent() {
		if (null == getIntent().getExtras() || null == getIntent().getExtras().get(Constants.INTENT_PARAM_ORIGINAL_INTENT)) {
			return getIntent();
		}
		return (Intent)getIntent().getExtras().get(Constants.INTENT_PARAM_ORIGINAL_INTENT);
	}
	private void openMessageOnViber() {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(smsType.packageName(), smsType.className()));
		intent.setData(getCorrectedData());
		intent.putExtra("recipient_number", phoneNumberNormalizer.getNormalizedNumberWithCountryPrefixAndSmsto(getDataString()));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
