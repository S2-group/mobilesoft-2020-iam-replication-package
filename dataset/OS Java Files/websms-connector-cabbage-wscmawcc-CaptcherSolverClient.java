/*
 * Copyright (C) 2012 Mikhail Blinov
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/>.
 */
package com.mikebl71.android.websms.connector.cabbage;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Client for Cabbage Captcha app.
 */
public class CaptcherSolverClient {

	// Intent parameters for the captcha solver app (Cabbage Captcha)
    private static String CAPTCHA_AUTO_SOLVER_PKG = "com.cabbagetexter.android.cabbagecaptcha";
    private static String CAPTCHA_AUTO_SOLVER_REQUEST = "com.cabbagetexter.android.cabbagecaptcha.Captcha";
    private static String CAPTCHA_AUTO_SOLVER_RESPONSE = "com.mikebl71.android.websms.connector.cabbage.CAPTCHA_AUTO_SOLVED";
    private static String CAPTCHA_AUTO_SOLVER_BITMAP = "captcha";
    private static String CAPTCHA_AUTO_SOLVER_REPLYTO = "broadcastreceiver";
    private static String CAPTCHA_AUTO_SOLVER_ANSWER = "text";
    private static String CAPTCHA_AUTO_SOLVER_NONFIXED_ANSWER = "textbeforefixing";

    // Max number of attempts for calling the solver app for a single message
	private static final int MAX_ATTEMPTS = 2;

    // Max number of attempts to remind user about installing Cabbage Captcha app
	private static final int MAX_REMINDERS = 4;

    private static final char[] CAPTCHA_FIX_FROM = new char[] {
		'C','F','I','K','M','O','P','S','U','V','W','X','Y','Z', '0','1','5','/','\\'};					
	private static final char[] CAPTCHA_FIX_TO = new char[] {
		'c','f','i','k','m','o','p','s','u','v','w','x','y','z', 'o','l','s','l','f'};					
	static { Arrays.sort(CAPTCHA_FIX_FROM); Arrays.sort(CAPTCHA_FIX_TO); }


	/**
	 * Checks if the solver app is installed.
	 */
	public static boolean isInstalled(final Context context) {
        for (PackageInfo pkgInfo : context.getPackageManager().getInstalledPackages(0)) {
        	if (pkgInfo.packageName != null && pkgInfo.packageName.equals(CAPTCHA_AUTO_SOLVER_PKG)) {
        		return true;
        	}
        }
        return false;
	}

	/**
	 * Checks if the solver app can be used.
	 */
	public static boolean canUse(final Context context) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return isInstalled(context) && CabbageConnectorPreferences.canUseCaptchaSolver(prefs);
	}

	/**
	 * Returns the maximum number of attempts for calling the captcha solver app for a single message.
	 */
	public static int getMaxAttempts() {
		return MAX_ATTEMPTS;
	}

	/**
	 * Creates an intent with the captcha image for sending to the solver app.
	 */
	public static Intent createRequestIntent(final Bitmap captcha) {
		final Intent intent = new Intent(CAPTCHA_AUTO_SOLVER_REQUEST);
	    intent.putExtra(CAPTCHA_AUTO_SOLVER_REPLYTO, CAPTCHA_AUTO_SOLVER_RESPONSE);
	    ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
	    captcha.compress(Bitmap.CompressFormat.PNG, 100, byteArrayStream);
	    intent.putExtra(CAPTCHA_AUTO_SOLVER_BITMAP, byteArrayStream.toByteArray());
	    return intent;
	}

	/**
	 * Checks if the intent is a response from the solver app.
	 */
    public static boolean isResponseIntent(final Context context, final Intent intent) {
		final String action = intent.getAction();
		return action != null && action.equals(CAPTCHA_AUTO_SOLVER_RESPONSE);
	}

	/**
	 * Extracts the captcha answer from the response from the solver app.
	 */
    public static String parseResponseIntent(final Context context, final Intent intent) {
    	String answer = null;
		final Bundle extras = intent.getExtras();
		if (extras != null) {
			String fixedAnswer = extras.getString(CAPTCHA_AUTO_SOLVER_ANSWER);
			String nonfixedAnswer = extras.getString(CAPTCHA_AUTO_SOLVER_NONFIXED_ANSWER);

			if (TextUtils.isEmpty(fixedAnswer) && !TextUtils.isEmpty(nonfixedAnswer)) {
				// try to fix ourselves
				fixedAnswer = fixCaptchaAnswer(nonfixedAnswer);
			}

			if (!TextUtils.isEmpty(fixedAnswer)) {
				answer = fixedAnswer;
			}
		}
		return answer;
	}

	/**
	 * Attempts to fix the raw guessed answer.
	 */
	private static String fixCaptchaAnswer(final String answer) {
		StringBuilder fixed = new StringBuilder(answer.length());
		for (int idx = 0; idx < answer.length(); idx++) {
			char ch = answer.charAt(idx);
			if (ch >= 'a' && ch <= 'z') {
				fixed.append(ch);
			} else {
				int mapIdx = Arrays.binarySearch(CAPTCHA_FIX_FROM, ch);
				if (mapIdx >= 0) {
					fixed.append(CAPTCHA_FIX_TO[mapIdx]);
				}
			}
		}
		return fixed.length() == 4 ? fixed.toString() : null;
	}

	/**
	 * Checks if the user should be reminded about installing the solver app.
	 * Decrements the number of remaining reminders.
	 */
	public static boolean shouldRemind(final Context context) {
		if (isInstalled(context)) {
			return false;
		} else {
			final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			int reminded = CabbageConnectorPreferences.getCaptchaSolverReminded(prefs);
			if (reminded < MAX_REMINDERS) {
				CabbageConnectorPreferences.setCaptchaSolverReminded(prefs, reminded + 1);
				return true;
			} else {
				return false;
			}
		}
	}

}
