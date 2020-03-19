/**
 * Newtifry for Android.
 * 
 * Copyright 2011 Daniel Foote
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.newtifry.android;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.actionbarsherlock.app.SherlockActivity;
import com.google.android.gcm.GCMRegistrar;
import com.newtifry.android.database.NewtifryMessage;
import static com.newtifry.android.CommonUtilities.TAG;
import static com.newtifry.android.CommonUtilities.DEFAULT_TEXT_COLOR;
import com.newtifry.android.R;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Newtifry extends SherlockActivity {	
	public final static String UPDATE_INTENT = "com.newtifry.android.UpdateUI";
	public static Intent healthCheckerIntent = null;
	public SherlockActivity  thisActivity;
	public int deleteMessagesOlderThan = 4 * 86400 * 1000; // 4 days by default
//	private AdView adView;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		// Prepare the view.
		super.onCreate(savedInstanceState);
		thisActivity = this;
		setContentView(R.layout.screen_home);

		// Figure out if we have the TTS installed.
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, 0x1010);
	
		Preferences.saveNotifierProInstalled(false, this);
		final PackageManager pm = getPackageManager();
		//get a list of installed apps.
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		for (ApplicationInfo packageInfo : packages) {
			if (packageInfo.packageName.equals("com.nlucas.notificationtoaster") || 
					packageInfo.packageName.equals("com.nlucas.notificationtoasterlite")) {
				Preferences.saveNotifierProInstalled(true, this);
				break;
			}
		}
		// check if we have a google account here
		AccountManager manager = AccountManager.get(this);
		Account[] accounts = manager.getAccountsByType("com.google");	
		if (accounts.length == 0) {
	        Toast.makeText(this, getString(R.string.google_account_needed), Toast.LENGTH_SHORT).show();
		} else {
			gcmRegistration();
		}
		
		if (!BuildConfig.DEBUG) { // do not delete old messages in debug
			// Clean out old messages.
			ScheduledExecutorService scheduler =
					Executors.newSingleThreadScheduledExecutor();
			scheduler.scheduleAtFixedRate (new Runnable() {
		    		public void run() {
		    			Date olderThan = new Date();
		    			olderThan.setTime(olderThan.getTime() - deleteMessagesOlderThan);
		    			NewtifryMessage.FACTORY.deleteOlderThan(thisActivity, olderThan);
			    	}
	    		}, 0, 8 * 60 * 60  , TimeUnit.SECONDS);
		}
	}
	
	private void gcmRegistration() {
		String senderId = Preferences.getSenderId(this);
	
	    try {
			GCMRegistrar.checkDevice(this);
		    GCMRegistrar.checkManifest(this);
			final String registrationId = GCMRegistrar.getRegistrationId(this);
			if( registrationId != null && !"".equals(registrationId) ) {
				Log.d(TAG, "Already registered. registrationId is " + registrationId);
			} else {
				Log.d(TAG, "No existing registrationId. Registering.");
				GCMRegistrar.register(this, senderId);
			}
	    } catch (UnsupportedOperationException e) {
	        Toast.makeText(this, R.string.gcm_not_supported, Toast.LENGTH_SHORT).show();
	    }
		
	}
	
//	public void getBackendURL()
//	{
//		// Sync the list off the server.
//		BackendRequest request = new BackendRequest("/params");
//
//		// Indicate what we're doing.
//		request.addMeta("operation", "geturl");
//
//		// For debugging, dump the request data.
////		request.dumpRequest();
//		
//		// Where to come back when we're done.
//		request.setHandler(handler);
//
//		// Start a thread to make the request.
//		// This will just update our view when ready.
//		request.startInThread(this, getString(R.string.loading_sources_from_server), "");		
//	}
//
//	/**
//	 * Private handler class that is the callback for when the external requests
//	 * are complete.
//	 */
//	private Handler handler = new Handler()
//	{
//		@Override
//		public void handleMessage( Message msg )
//		{
//			// Fetch out the response.
//			BackendResponse response = (BackendResponse) msg.obj;
//
//			// Was it successful?
//			if( response.isError() )
//			{
//				// No, not successful.
//				Toast.makeText(thisActivity, response.getError() + " - Please try again.", Toast.LENGTH_LONG).show();
//			}
//			else
//			{
//				try
//				{
//					// Fetch out metadata.
//					BackendRequest request = response.getRequest();
//					String operation = (String) request.getMeta("operation");
//
//					// Determine our operation.
//					if( operation.equals("geturl") )
//					{	
//						// set backend server url
//						JSONObject url = response.getJSON().getJSONObject("serverurl");
//						Log.d(TAG, "Server URL : " + url.toString());
//						BackendClient.setBackendURL(url.toString());
//					}
//
//				}
//				catch( JSONException e )
//				{
//					// The response doesn't look like we expected.
//					Log.d(TAG, "Invalid response from server: " + e.getMessage());
//					Toast.makeText(thisActivity, getString(R.string.server_invalid_response), Toast.LENGTH_LONG).show();

//				}
//			}
//		}
//	};
	
	@Override
	public void onDestroy() {
//		adView.destroy();
	    super.onDestroy();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		NewtifryApp.setIsAppVisible(true);

		// Change the master enable/disable button based on the settings.
		// This is done in onResume() so it's correct even if you go to the
		// settings and come back.
		
		// TODO : update sources for each registered account 
		this.changeEnabledLabelFor(findViewById(R.id.home_disableAll),Preferences.getMasterEnable(this));
		healthCheckerIntent = registerReceiver(healthCheckReceiver, new IntentFilter(UPDATE_INTENT));
/*
		final String registrationId = GCMRegistrar.getRegistrationId(this);
		if( registrationId != null && !"".equals(registrationId)  && Preferences.getServerLessMode(this) == true ) {
			Button accountsButton = (Button) findViewById(R.id.home_accounts);
			accountsButton.setEnabled(false);
		}				
*/
		
		// Also, do a health check and post the results.
		try {
			this.doHealthCheck();
		} catch( NameNotFoundException e ) {
			Log.d(TAG, "Can't find own installed package...");
		}
	}
	
	public void onPause() {
		super.onPause();
		NewtifryApp.setIsAppVisible(true);
		unregisterReceiver(healthCheckReceiver);
	}
	public void doHealthCheck() throws NameNotFoundException {
		// Perform the health check.
		HealthCheck check = HealthCheck.performHealthcheck(this);
		
		TextView healthCheckArea = (TextView) findViewById(R.id.home_healthCheck);
		
		// litle hack
		if (DEFAULT_TEXT_COLOR == -1) {
			DEFAULT_TEXT_COLOR= healthCheckArea.getCurrentTextColor();
		}
		
		// Format the check text.
		StringBuilder allText = new StringBuilder();
		for( String error: check.getErrors() )
		{
			allText.append("- ");
			allText.append(error);
			allText.append('\n');
		}
		for( String error: check.getWarnings() )
		{
			allText.append("- ");
			allText.append(error);
			allText.append('\n');
		}

		int allTextSize = allText.length();
		allText.append('\n');
		allText.append('v');
		String versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
		if ( this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode == 999) {
			versionName +="-dev";
		}
		allText.append(versionName);
		
		if( allTextSize == 0 ) {
			healthCheckArea.setText(getString(R.string.health_check_all_ok) + allText.toString());
		} else {
			healthCheckArea.setText(allText.toString().trim());
		}

		// Enable the accounts button once we have an ID.
		final String registrationId = GCMRegistrar.getRegistrationId(this);
		if( registrationId != null && !"".equals(registrationId) /* && Preferences.getServerLessMode(this) == false */)
		{
			Button accountsButton = (Button) findViewById(R.id.home_accounts);
			accountsButton.setEnabled(true);
		}				
	}

	/**
	 * OnClick handler to stop reading now.
	 * 
	 * @param view
	 */
	public void stopReadingNow( View view )
	{
		// Inform our service to stop reading now.
		Intent intentData = new Intent(getBaseContext(), SpeakService.class);
		intentData.putExtra("stopNow", true);
		startService(intentData);
	}

	/**
	 * OnClick handler to toggle the master enable.
	 * 
	 * @param view
	 * @throws NameNotFoundException 
	 */
	public void disableEnableNotifications( View view ) throws NameNotFoundException
	{
		// Enable or disable the master enable flag, updating the button as
		// appropriate.
		boolean masterEnable = !Preferences.getMasterEnable(this);
		Preferences.saveMasterEnable(masterEnable, this);
		this.changeEnabledLabelFor(view, masterEnable);
		
		this.doHealthCheck();
	}

	/**
	 * Based on the settings, change the text on the given view to match.
	 * 
	 * @param view
	 */
	public void changeEnabledLabelFor( View view, boolean masterEnable )
	{
//		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		if( masterEnable )
		{
			// It is enabled. Give the button the enabled text.
			Button button = (Button) view;
			button.setText(R.string.disable_all_notifications);
		}
		else
		{
			Button button = (Button) view;
			button.setText(R.string.enable_all_notifications);
		}
	}

	/**
	 * OnClick handler to launch the settings dialog.
	 * 
	 * @param view
	 */
	public void launchSettings( View view )
	{
		Intent intent = new Intent(this, NewtifryPreferenceActivity.class);
		startActivity(intent);
/*		
		Intent intent = new Intent(getBaseContext(), Settings.class);
		startActivity(intent);
*/		
	}

	/**
	 * Onclick handler to launch the recent messages dialog.
	 * 
	 * @param view
	 */
	public void launchRecentMessages( View view )
	{
		Intent intent = new Intent(getBaseContext(), MessageList.class);
		startActivity(intent);
	}

	/**
	 * OnClick handler to launch the account chooser dialog.
	 * 
	 * @param view
	 */
	public void launchAccounts( View view )
	{
		Intent intent = new Intent(getBaseContext(), ChooseAccount.class);
		startActivity(intent);
	}

	/**
	 * Callback function for checking if the Text to Speech is installed. If
	 * not, it will redirect the user to download the text data.
	 */
	protected void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		if( requestCode == 0x1010 )
		{
			if( resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS )
			{
				// All systems are go.
				Log.d(TAG, "All systems are go.");
			}
			else
			{
				// TTS data missing. Go get it.
				Toast.makeText(getApplicationContext(), R.string.need_tts_data_installed, Toast.LENGTH_LONG).show();
				Log.d(TAG, "Redirecting to get data.");
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}
	
	private final BroadcastReceiver healthCheckReceiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive( Context context, Intent intent )
		{
			Log.d(TAG, "Re-performing health check.");
			try
			{
				doHealthCheck();
			}
			catch( NameNotFoundException e )
			{
				Log.d(TAG, "Can't find own package information...");
			}
		}
	};	
	
}