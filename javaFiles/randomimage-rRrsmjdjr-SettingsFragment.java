package de.jeisfeld.randomimage;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import de.jeisfeld.randomimage.util.DialogUtil;
import de.jeisfeld.randomimage.util.DialogUtil.ConfirmDialogFragment.ConfirmDialogListener;
import de.jeisfeld.randomimage.util.ImageRegistry;
import de.jeisfeld.randomimage.util.PreferenceUtil;
import de.jeisfeld.randomimage.util.SystemUtil;
import de.jeisfeld.randomimage.util.SystemUtil.ApplicationInfo;
import de.jeisfeld.randomimage.util.TrackingUtil;
import de.jeisfeld.randomimage.view.DynamicMultiSelectListPreference;
import de.jeisfeld.randomimage.view.DynamicMultiSelectListPreference.DynamicListPreferenceOnClickListener;
import de.jeisfeld.randomimagelib.R;

/**
 * Fragment for displaying the settings.
 */
public class SettingsFragment extends PreferenceFragment {
	/**
	 * Field holding the value of the language preference, in order to detect a real change.
	 */
	private String mLanguageString;

	/**
	 * Field holding the value of the hidden lists pattern, in order to detect a real change.
	 */
	private String mHiddenListsPattern;

	/**
	 * Field holding the value of the hidden folders pattern, in order to detect a real change.
	 */
	private String mHiddenFoldersPattern;

	/**
	 * A preference value change listener that updates the preference's summary to reflect its new value.
	 */
	private CustomOnPreferenceChangeListener mOnPreferenceChangeListener = new CustomOnPreferenceChangeListener();

	@Override
	public final void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.pref_general);

		// Fill variables in order to detect changed values.
		mLanguageString = PreferenceUtil.getSharedPreferenceString(R.string.key_pref_language);
		mHiddenListsPattern = PreferenceUtil.getSharedPreferenceString(R.string.key_pref_hidden_lists_pattern);
		mHiddenFoldersPattern = PreferenceUtil.getSharedPreferenceString(R.string.key_pref_hidden_folders_pattern);

		bindPreferenceSummaryToValue(R.string.key_pref_language);
		bindPreferenceSummaryToValue(R.string.key_pref_folder_selection_mechanism);
		bindPreferenceSummaryToValue(R.string.key_pref_show_hidden_folders);
		bindPreferenceSummaryToValue(R.string.key_pref_show_list_notification);
		bindPreferenceSummaryToValue(R.string.key_pref_use_regex_filter);
		bindPreferenceSummaryToValue(R.string.key_pref_hidden_folders_pattern);
		bindPreferenceSummaryToValue(R.string.key_pref_hidden_lists_pattern);
		bindPreferenceSummaryToValue(R.string.key_pref_detail_scale_type);
		bindPreferenceSummaryToValue(R.string.key_pref_detail_background);
		bindPreferenceSummaryToValue(R.string.key_pref_detail_flip_behavior);
		bindPreferenceSummaryToValue(R.string.key_pref_detail_change_with_tap);

		addHintButtonListener(R.string.key_pref_show_info, false);
		addHintButtonListener(R.string.key_pref_hide_info, true);
		addSearchImageFoldersListener();
		addHelpPageListener();
		addDonationListener();
		addDeveloperContactListener();
		addProAppButtonListener();
		addRestrictPopupNotificationsListener();
		updateRegexpPreferences(getActivity());
		updateShowHiddenFoldersPreference(getActivity());


		if (Boolean.parseBoolean(Application.getResourceString(R.string.has_premium))) {
			getPreferenceScreen().removePreference(findPreference(getString(R.string.key_pref_category_premium)));
		}
	}

	@Override
	public final void onResume() {
		super.onResume();
		TrackingUtil.sendScreen(this);
	}

	/**
	 * Add the listener for a "hints" button.
	 *
	 * @param preferenceId        The id of the button.
	 * @param hintPreferenceValue The value to be set to all the hints preferences.
	 */
	private void addHintButtonListener(final int preferenceId, final boolean hintPreferenceValue) {
		Preference showPreference = findPreference(getString(preferenceId));
		showPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				PreferenceUtil.setAllHints(hintPreferenceValue);
				return true;
			}
		});
	}

	/**
	 * Add the listener for the "Search image folders" button.
	 */
	private void addSearchImageFoldersListener() {
		Preference searchPreference = findPreference(getString(R.string.key_pref_search_image_folders));
		searchPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				DialogUtil.displaySearchForImageFoldersIfRequired(getActivity(), true);
				return true;
			}
		});
	}

	/**
	 * Add an entry for variable donation.
	 */
	private void addDonationListener() {
		Preference variableDonationPreference = findPreference(getString(R.string.key_pref_donation));

		variableDonationPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				Intent browserIntent =
						new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.menu_target_donation)));
				startActivity(browserIntent);
				return true;
			}
		});
	}

	/**
	 * Add an entry for variable donation.
	 */
	private void addHelpPageListener() {
		Preference variableDonationPreference = findPreference(getString(R.string.key_pref_help_page));

		variableDonationPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				Intent browserIntent =
						new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.menu_target_help_page)));
				startActivity(browserIntent);
				return true;
			}
		});
	}

	/**
	 * Add an entry for developer contact.
	 */
	private void addDeveloperContactListener() {
		Preference contactPreference = findPreference(getString(R.string.key_pref_contact_developer));

		contactPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto", getString(R.string.menu_email_contact_developer), null));
				intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.menu_subject_contact_developer));

				startActivity(intent);
				return true;
			}
		});
	}

	/**
	 * Add the listener for pro app.
	 */
	private void addProAppButtonListener() {
		Preference unlockPreference = findPreference(getString(R.string.key_pref_pro_app));
		unlockPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(final Preference preference) {
				Intent googlePlayIntent = new Intent(Intent.ACTION_VIEW);
				googlePlayIntent.setData(Uri.parse("market://details?id=de.jeisfeld.randomimagepro"));
				try {
					startActivity(googlePlayIntent);
				}
				catch (Exception e) {
					DialogUtil.displayToast(getActivity(), R.string.toast_failed_to_open_google_play, false);
				}
				return true;
			}
		});
		unlockPreference.setEnabled(!SystemUtil.isAppInstalled("de.jeisfeld.randomimagepro"));
	}

	/**
	 * Add an entry for variable donation.
	 */
	private void addRestrictPopupNotificationsListener() {
		final DynamicMultiSelectListPreference restrictPopupNotificationsPreference =
				(DynamicMultiSelectListPreference) findPreference(getString(R.string.key_pref_apps_without_popup_notifications));
		if (android.os.Build.VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
			((PreferenceCategory) findPreference(getString(R.string.key_pref_category_other))).removePreference(restrictPopupNotificationsPreference);
			return;
		}

		restrictPopupNotificationsPreference.setOnClickListener(new DynamicListPreferenceOnClickListener() {
			@Override
			public boolean onClick(final DynamicMultiSelectListPreference preference) {
				if (!SystemUtil.isUsageStatsAvailable()) {
					restrictPopupNotificationsPreference.setEntries(new String[0]);
					restrictPopupNotificationsPreference.setEntryValues(new String[0]);

					DialogUtil.displayConfirmationMessage(getActivity(), new ConfirmDialogListener() {
						@RequiresApi(VERSION_CODES.LOLLIPOP)
						@Override
						public void onDialogPositiveClick(final DialogFragment dialog) {
							Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(intent, 0);
						}

						@Override
						public void onDialogNegativeClick(final DialogFragment dialog) {
						}
					}, R.string.title_dialog_request_permission, R.string.button_continue, R.string.dialog_confirmation_need_usage_access);
					return false;
				}

				List<ApplicationInfo> applicationInfoList = SystemUtil.getInstalledApplications();
				String[] entries = new String[applicationInfoList.size()];
				String[] entryValues = new String[applicationInfoList.size()];
				int i = 0;

				for (ApplicationInfo applicationInfo : applicationInfoList) {
					entries[i] = applicationInfo.getLabelName();
					entryValues[i] = applicationInfo.getPackageName();
					i++;
				}

				restrictPopupNotificationsPreference.setEntries(entries);
				restrictPopupNotificationsPreference.setEntryValues(entryValues);
				return true;
			}
		});
	}

	/**
	 * Binds a preference's summary to its value. More specifically, when the preference's value is changed, its summary
	 * (line of text below the preference title) is updated to reflect the value. The summary is also immediately
	 * updated upon calling this method. The exact display format is dependent on the type of preference.
	 *
	 * @param preference The preference to be bound.
	 */
	private void bindPreferenceSummaryToValue(final Preference preference) {
		// Set the listener to watch for value changes.
		preference.setOnPreferenceChangeListener(mOnPreferenceChangeListener);

		// Trigger the listener immediately with the preference's current value.
		if (!(preference instanceof CheckBoxPreference)) {
			mOnPreferenceChangeListener.setSummary(preference, PreferenceManager
					.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
		}
	}

	/**
	 * Helper method for easier call of {@link #bindPreferenceSummaryToValue(android.preference.Preference)}.
	 *
	 * @param preferenceKey The key of the preference.
	 */
	private void bindPreferenceSummaryToValue(final int preferenceKey) {
		bindPreferenceSummaryToValue(findPreference(getString(preferenceKey)));
	}

	/**
	 * Update the enabling of the regexp preferences.
	 *
	 * @param context The context.
	 */
	private void updateRegexpPreferences(final Context context) {
		boolean booleanValue = PreferenceUtil.getSharedPreferenceBoolean(R.string.key_pref_use_regex_filter);
		findPreference(context.getString(R.string.key_pref_hidden_folders_pattern)).setEnabled(booleanValue);
		findPreference(context.getString(R.string.key_pref_hidden_lists_pattern)).setEnabled(booleanValue);
	}

	/**
	 * Update the enabling of the "Show Hidden Folders" preference.
	 *
	 * @param context The context.
	 */
	private void updateShowHiddenFoldersPreference(final Context context) {
		int mechanism = PreferenceUtil.getSharedPreferenceIntString(R.string.key_pref_folder_selection_mechanism, -1);
		findPreference(context.getString(R.string.key_pref_show_hidden_folders)).setEnabled(mechanism == 0);
	}

	/**
	 * A preference value change listener that updates the preference's summary to reflect its new value.
	 */
	private class CustomOnPreferenceChangeListener implements OnPreferenceChangeListener {
		@Override
		public boolean onPreferenceChange(final Preference preference, final Object value) {
			String stringValue = value.toString();

			// Apply change of language
			if (preference.getKey().equals(preference.getContext().getString(R.string.key_pref_language))) {

				if (mLanguageString == null || !mLanguageString.equals(value)) {
					PreferenceUtil.setSharedPreferenceString(R.string.key_pref_language, stringValue);

					Application.startApplication(getActivity());
					if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
						System.exit(0);
					}
				}
			}
			// show/hide regex preferences in dependence of main setting
			else if (preference.getKey().equals(preference.getContext().getString(R.string.key_pref_use_regex_filter))) {
				PreferenceUtil.setSharedPreferenceBoolean(R.string.key_pref_use_regex_filter, (Boolean) value);
				updateRegexpPreferences(preference.getContext());
				ImageRegistry.parseConfigFiles();
			}
			// In case of switch of hidden lists pattern, refresh
			else if (preference.getKey().equals(preference.getContext().getString(R.string.key_pref_hidden_lists_pattern))) {
				if (mHiddenListsPattern == null || !mHiddenListsPattern.equals(stringValue)) {
					try {
						//noinspection ResultOfMethodCallIgnored
						Pattern.compile(stringValue);
					}
					catch (PatternSyntaxException e) {
						DialogUtil.displayInfo(getActivity(), R.string.dialog_info_invalid_regexp);
						return false;
					}
					PreferenceUtil.setSharedPreferenceString(R.string.key_pref_hidden_lists_pattern, (String) value);
					ImageRegistry.parseConfigFiles();
				}
			}
			else if (preference.getKey().equals(preference.getContext().getString(R.string.key_pref_hidden_folders_pattern))) {
				if (mHiddenFoldersPattern == null || !mHiddenFoldersPattern.equals(stringValue)) {
					try {
						//noinspection ResultOfMethodCallIgnored
						Pattern.compile(stringValue);
					}
					catch (PatternSyntaxException e) {
						DialogUtil.displayInfo(getActivity(), R.string.dialog_info_invalid_regexp);
						return false;
					}
				}
			}
			// Update view depending on folder selection mechanism.
			else if (preference.getKey().equals(preference.getContext().getString(R.string.key_pref_folder_selection_mechanism))) {
				PreferenceUtil.setSharedPreferenceString(R.string.key_pref_folder_selection_mechanism, stringValue);
				updateShowHiddenFoldersPreference(preference.getContext());
			}

			setSummary(preference, stringValue);

			return true;
		}

		/**
		 * Set the summary of the preference.
		 *
		 * @param preference The preference.
		 * @param value      The value of the preference.
		 */
		public void setSummary(final Preference preference, final String value) {
			// set summary
			if (preference.getClass().equals(ListPreference.class)) {
				// For list preferences (except customized ones), look up the correct display value in
				// the preference's 'entries' list.
				ListPreference listPreference = (ListPreference) preference;
				int index = listPreference.findIndexOfValue(value);

				preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
			}
			else if (!(preference instanceof CheckBoxPreference)) {
				// For all other preferences, set the summary to the value's
				// simple string representation.
				preference.setSummary(value);
			}
		}
	}

}
