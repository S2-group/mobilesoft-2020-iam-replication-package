package com.mridang.updates;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.acra.ACRA;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.apps.dashclock.api.ExtensionData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/*
 * This class is the main class that provides the widget
 */
public class UpdatesWidget extends ImprovedExtension {

	/*
	 * (non-Javadoc)
	 * @see com.mridang.updates.ImprovedExtension#getIntents()
	 */
	@Override
	protected IntentFilter getIntents() {

		IntentFilter itfIntents = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
		itfIntents.addAction(Intent.ACTION_PACKAGE_CHANGED);
		itfIntents.addAction(Intent.ACTION_PACKAGE_REPLACED);
		itfIntents.addAction(Intent.ACTION_PACKAGE_REMOVED);
		return itfIntents;

	}

	/*
	 * (non-Javadoc)
	 * @see com.mridang.updates.ImprovedExtension#getTag()
	 */
	@Override
	protected String getTag() {
		return getClass().getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mridang.updates.ImprovedExtension#getUris()
	 */
	@Override
	protected String[] getUris() {
		return null;
	}

	/*
	 * @see
	 * com.google.android.apps.dashclock.api.DashClockExtension#onUpdateData
	 * (int)
	 */
	@Override
	protected void onUpdateData(int intReason) {

		final ExtensionData edtInformation = new ExtensionData();
		setUpdateWhenScreenOn(false);

		try {

			TelephonyManager mgrTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String strAndrid = "" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
			String strDevice = "" + mgrTelephony.getDeviceId();
			String strSerial = "" + mgrTelephony.getSimSerialNumber();
			UUID uidUniqid = new UUID(strAndrid.hashCode(), ((long) strDevice.hashCode() << 32) | strSerial.hashCode());
			JSONObject jsoRequest = new JSONObject();

			final Map<String, Integer> mapVersions = new HashMap<>();
			final Map<String, String> mapPackages = new HashMap<>();

			Log.d(getTag(), "Building request payload with installed applications");
			jsoRequest.put("android_version", Build.VERSION.SDK_INT);
			jsoRequest.put("model", Build.DEVICE);
			jsoRequest.put("apps", new JSONArray());
			JSONArray jsoUpdates = new JSONArray();

			final Editor ediSettings = getEditor();
			Integer intFlags = PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_SIGNATURES;
			for (PackageInfo pkgPackage : getApplicationContext().getPackageManager().getInstalledPackages(intFlags)) {

				String strPackage = pkgPackage.applicationInfo.loadLabel(getPackageManager()).toString();
				if ((pkgPackage.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
					Log.d(getTag(), "Skipping system package " + strPackage);
					continue;
				}

				if (getBoolean(pkgPackage.packageName, false)) {
					Log.d(getTag(), "Skipping excluded package " + strPackage);
					continue;
				}

				if ((pkgPackage.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
					Log.d(getTag(), "Skipping debuggable package " + strPackage);
					continue;
				}

				FileInputStream fisPackage = null;
				try {

					fisPackage = new FileInputStream(new File(pkgPackage.applicationInfo.sourceDir));
					String strSha1 = new String(Hex.encodeHex(DigestUtils.sha1(fisPackage)));
					String strCert = new String(Hex.encodeHex(DigestUtils.sha1(pkgPackage.signatures[0].toByteArray())));

					JSONObject jsoPackage = new JSONObject();
					jsoPackage.put("pname", pkgPackage.packageName);
					jsoPackage.put("vcode", pkgPackage.versionCode);
					jsoPackage.put("vname", pkgPackage.versionName);
					jsoPackage.put("cert_sig", strCert);
					jsoPackage.put("apk_sha1", strSha1);
					jsoRequest.getJSONArray("apps").put(jsoPackage);

					mapVersions.put(pkgPackage.packageName, pkgPackage.versionCode);
					mapPackages.put(pkgPackage.packageName, strPackage);

					if (getBoolean(strSha1, false)) {
						Log.d(getTag(), "Skipping reported package " + strPackage);
						continue;
					}

					Log.d(getTag(), "Adding " + strPackage);
					JSONObject jsoUpdate = new JSONObject();
					jsoUpdate.put("pname", pkgPackage.packageName);
					jsoUpdate.put("vcode", pkgPackage.versionCode);
					jsoUpdate.put("vname", pkgPackage.versionName);
					jsoUpdate.put("cert_sig", strCert);
					jsoUpdate.put("apk_sha1", strSha1);
					jsoUpdates.put(jsoUpdate);
					ediSettings.putBoolean(strSha1, true);

				} catch (Exception e) {
					Log.w(getTag(), "Unable to calculate checksum for the package");
				} finally {
					if (fisPackage != null) {
						fisPackage.close();
					}
				}

			}

			Log.d(getTag(), "Sending the details of the installed packages");
			AsyncHttpClient ascClient = new AsyncHttpClient();
			String strServer = "http://dashclock-updates.appspot.com";
			String strUrl = String.format("%s/%s/%s/%s/", strServer, uidUniqid.toString().replace("-", ""), Build.VERSION.SDK_INT, Build.DEVICE);
			Log.v(getTag(), strUrl);

			ascClient.setTimeout(60000);
			ascClient.setMaxRetriesAndTimeout(3, 60000);
			ascClient.post(getApplicationContext(), strUrl, new StringEntity(jsoUpdates.toString(), "UTF-8"),
					"application/json", new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(String strResponse) {
					ediSettings.commit();
					Log.d(getTag(), "Successfully sent the details");
				}

				@Override
				public void onFailure(int intCode, Header[] arrHeaders, byte[] arrBytes, Throwable errError) {
					Log.w(getTag(), "Error posting the details due to code " + intCode);
				}

			});

			ascClient.setTimeout(60000);
			ascClient.setMaxRetriesAndTimeout(3, 60000);
			ascClient.post(getApplicationContext(),
					"http://goddchen.de/android/appupdate_crowd/siteground/versions.php", new StringEntity(
							jsoRequest.toString(), "UTF-8"), "application/json", new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(String strResponse) {

					try {

						Log.v(getTag(), "Server reponded with: " + strResponse);
						if (!strResponse.trim().isEmpty()) {

							JSONArray jsoResponse = new JSONArray(strResponse);
							Integer intUpdates = 0;
							String strPackages = "";

							Log.d(getTag(), "Checking which packages have newer versions");
							for (Integer intI = 0; intI < jsoResponse.length(); intI++) {

								JSONObject jsoPackage = jsoResponse.getJSONObject(intI);
								JSONArray jsoVersions = jsoPackage.getJSONArray("by_android_version");
								for (Integer intJ = 0; intJ < jsoVersions.length(); intJ++) {

									Integer intVersion = jsoVersions.getJSONObject(intJ).getInt("vcode");
									//noinspection SuspiciousMethodCalls
									if (intVersion > mapVersions.get(jsoPackage.get("pname"))) {

										//noinspection SuspiciousMethodCalls
										String strPackage = mapPackages.get(jsoPackage.get("pname"));
										Log.d(getTag(), strPackage != null ? strPackage : jsoPackage.getString("pname"));
										intUpdates = intUpdates + 1;
										strPackages = strPackages + (strPackages.length() > 0 ? ", " : "") + strPackage;
										break;

									}

								}

							}

							Log.d(getTag(), String.format("Found %d possible updates", intUpdates));
							if (intUpdates > 0) {

								PackageManager pkgManager = getPackageManager();
								Intent ittApplication = pkgManager.getLaunchIntentForPackage("com.android.vending");
								ittApplication.addCategory(Intent.CATEGORY_LAUNCHER);

								edtInformation.visible(true);
								edtInformation.clickIntent(ittApplication);
								edtInformation.status(intUpdates.toString());
								edtInformation.expandedBody(strPackages);
								edtInformation.expandedTitle(getQuantityString(R.plurals.updates, intUpdates, intUpdates));

							} else {
								edtInformation.visible(false);
							}

							edtInformation.icon(R.drawable.ic_dashclock);
							doUpdate(edtInformation);

						}

					} catch (Exception e) {
						edtInformation.visible(false);
						Log.e(getTag(), "Encountered an error", e);
						ACRA.getErrorReporter().handleSilentException(e);
					}

				}

			});


		} catch (Exception e) {
			edtInformation.visible(false);
			Log.e(getTag(), "Encountered an error", e);
			ACRA.getErrorReporter().handleSilentException(e);
		}

		edtInformation.icon(R.drawable.ic_dashclock);
		doUpdate(edtInformation);

	}

	/*
	 * (non-Javadoc)
	 * @see com.mridang.alarmer.ImprovedExtension#onReceiveIntent(android.content.Context, android.content.Intent)
	 */
	@Override
	protected void onReceiveIntent(Context ctxContext, Intent ittIntent) {
		onUpdateData(UPDATE_REASON_MANUAL);
	}

}