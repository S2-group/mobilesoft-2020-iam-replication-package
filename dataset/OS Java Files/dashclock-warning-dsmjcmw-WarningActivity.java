package com.mridang.warning;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.acra.ACRA;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/*
 * This class is the activity which contains the preferences
 */
public class WarningActivity extends PreferenceActivity {

	/* A boolean value indicating whether this activity should be recreated */
	private Boolean booRecreate = false;

	/**
	 * Custom preference widget that is used to show an stacktrace preference.
	 */
	public class ErrorPreference extends Preference implements OnClickListener {

		/* The full stack trace as represented by this preference */
		private final String strTrace;

		/**
		 * Constructor for the preference that simply sets a custom layout for
		 * itself
		 *
		 * @param ctxContext The context of the preferences activity
		 * @param strTrace The string representation of the stack trace that should be displayed
		 */
		public ErrorPreference(Context ctxContext, String strTrace) {
			super(ctxContext);
			setLayoutResource(R.layout.error);
			this.strTrace = strTrace;
		}

		/*
		 * @see android.preference.Preference#onBindView(android.view.View)
		 */
		@Override
		protected void onBindView(@NonNull View vewView) {

			super.onBindView(vewView);
			vewView.findViewById(R.id.thepref).setOnClickListener(this);

		}

		/*
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View vewView) {

			AlertDialog.Builder bldBuilder = new AlertDialog.Builder(WarningActivity.this);
			bldBuilder.setMessage(strTrace).setTitle(strTrace.split("\n")[0]);

			bldBuilder.setNeutralButton(R.string.copy, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface difDialog, int intId) {
					((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setText(strTrace);
				}
			});

			bldBuilder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface difDialog, int intId) {
					difDialog.dismiss();
				}
			});

			AlertDialog dlgTrace = bldBuilder.create();
			dlgTrace.show();

			TextView tvwMessage = (TextView) dlgTrace.findViewById(android.R.id.message);
			tvwMessage.setHorizontallyScrolling(true);

		}

	}

	/*
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("ConstantConditions")
	@Override
	public void onCreate(Bundle bndSate) {

		getActionBar().setIcon(R.drawable.ic_dashclock);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(bndSate);
		setContentView(R.layout.loading);

	}

	/*
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle bndSate) {

		super.onPostCreate(bndSate);
		findViewById(R.id.progress).setVisibility(View.VISIBLE);
		findViewById(android.R.id.list).setVisibility(View.GONE);

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... voiParams) {

				PackageManager mgrPackages = getPackageManager();
				List<String> lstPackages = new ArrayList<>();
				for (ApplicationInfo packageInfo : mgrPackages.getInstalledApplications(PackageManager.GET_META_DATA)) {

					if (!packageInfo.packageName.startsWith("com.android")
							&& !packageInfo.packageName.equalsIgnoreCase("android")) {
						lstPackages.add(packageInfo.packageName);
					}

				}
				Collections.sort(lstPackages);
				Collections.reverse(lstPackages);

				PreferenceScreen pscScreen = getPreferenceManager().createPreferenceScreen(WarningActivity.this);
				setPreferenceScreen(pscScreen);
				SharedPreferences speSettings = PreferenceManager.getDefaultSharedPreferences(WarningActivity.this);
				Map<Long, String> mySortedMap = new TreeMap<>(Collections.reverseOrder());

				Set<String> setTraces = speSettings.getStringSet("warning", new HashSet<String>());
				setTraces.addAll(speSettings.getStringSet("failure", new HashSet<String>()));
				for (String strTrace : setTraces) {

					try {

						String strHash = WarningWidget.hashString(strTrace);
						Set<String> setTimes = speSettings.getStringSet(strHash, new HashSet<String>());
						for (String strTime : setTimes) {
							mySortedMap.put(Long.valueOf(strTime), strTrace);
						}

					} catch (NoSuchAlgorithmException e) {
						ACRA.getErrorReporter().handleSilentException(e);
					}

				}

				for (Entry<Long, String> entTrace : mySortedMap.entrySet()) {

					String strTrace = entTrace.getValue();
					String strLine = strTrace.split("\n")[0];
					ErrorPreference errException = new ErrorPreference(WarningActivity.this, strTrace);
					errException.setTitle(strLine);
					errException.setSummary(DateUtils.formatDateTime(WarningActivity.this, entTrace.getKey(),
							DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
					errException.setIcon(android.R.drawable.sym_def_app_icon);
					for (String strPackage : lstPackages) {

						if (strTrace.contains(strPackage)) {

							try {
								errException.setIcon(getPackageManager().getApplicationIcon(strPackage));
							} catch (NameNotFoundException e) {
								break;
							}
							break;

						}

					}

					pscScreen.addPreference(errException);

				}

				return null;

			}

			@Override
			protected void onPostExecute(Void voiResult) {

				findViewById(R.id.progress).setVisibility(View.GONE);
				findViewById(android.R.id.list).setVisibility(View.VISIBLE);

			}

		}.execute();

	}

	/**
	 * Sets the flag when the activity is paused so we can recreate it when it
	 * is resumed
	 *
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {

		booRecreate = true;
		super.onPause();

	}

	/**
	 * Forces the activity to recreate all the preferences after the activity
	 * was paused A handler is used for recreating an activity. Calling recreate
	 * directly from the the onResume causes a runtime exception
	 *
	 * @see android.app.Activity#onResume()
	 */
	@Override
	public void onResume() {

		super.onResume();
		if (booRecreate) {
			Handler hndRecreate = new Handler();
			hndRecreate.postDelayed(new Runnable() {

				@Override
				public void run() {

					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {

						finish();
						startActivity(getIntent());

					} else {
						recreate();
					}

				}

			}, 1);

		}

	}

	/*
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu mnuMenu) {

		getMenuInflater().inflate(R.menu.menu, mnuMenu);
		return true;

	}

	/*
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem itmItem) {

		AlertDialog.Builder bldDialog = new AlertDialog.Builder(this);
		bldDialog.setTitle(R.string.dialog);
		bldDialog.setMessage(R.string.question);
		bldDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface difDialog, int intWhich) {

				SharedPreferences speSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				Editor ediEditor = speSettings.edit();
				ediEditor.clear();
				ediEditor.putLong("bookmark", new Date().getTime());
				ediEditor.apply();

				Intent ittRefresh = new Intent("com.mridang.warning.ACTION_REFRESH");
				sendBroadcast(ittRefresh);
				finish();

			}

		});

		bldDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface difDialog, int intWhich) {

				difDialog.dismiss();

			}
		});

		bldDialog.show();
		return true;

	}

}