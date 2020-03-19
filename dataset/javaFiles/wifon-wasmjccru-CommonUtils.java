package com.croconaut.ratemebuddy.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.croconaut.ratemebuddy.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommonUtils {
    private static final String TAG = CommonUtils.class.getName();

    //extras for activities
    public static final String EXTRA_TARGET_CROCO_ID = "targetCrocoID";
    public static final String EXTRA_CLEAR_NOTIF_MESS_PROFILES = "extraCleanMessProfiles";
    public static final String HOPS_JSON_ARRAY = "HOPS_JSON_ARRAY";
    public static final String DIRECTORY_WIFON = "wifon";
    public static final String DIRECTORY_EXPORT_HOPS = "hops";
    public static final String FILE_NAME_PROFILE = "profile.wifon";
    public static final String PROFILE_DIRECTORY_MYSELF = "wifon/myself";

    public static final String CEO_CROCO_ID = "66:bc:0c:51:4a:0a";

    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String getMyId(Context context) {
        String uniqueID;
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_UNIQUE_ID, Context.MODE_PRIVATE);
        uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(PREF_UNIQUE_ID, uniqueID);
            editor.apply();
        }

        return uniqueID;
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static void openFile(Context context, Uri uri) throws Exception {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, context.getContentResolver().getType(uri));
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static int getDrawableForFile(Context context, Uri uri) {
        if (uri == null)
            return R.drawable.ic_action_file_not_found;

        String type = context.getContentResolver().getType(uri);

        if (type == null)
            return R.drawable.ic_action_file_not_found;

        if (type.contains("wav") || type.contains("mp3")) {
            return R.drawable.ic_action_file_music;
        } else if (type.contains("gif") || type.contains("jpg") || type.contains("jpeg")
                || type.contains("png")) {
            return R.drawable.ic_action_file_image;
        } else if (type.contains("3gp") || type.contains("mpg") || type.contains("mpeg")
                || type.contains("mpe") || type.contains("mp4") || type.contains("avi")) {
            return R.drawable.ic_action_file_movie;
        } else {
            return R.drawable.ic_action_file_folder;
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public String getMyName(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            Uri table = ContactsContract.Profile.CONTENT_URI;
            String[] projection = {ContactsContract.Profile.DISPLAY_NAME};

            Cursor c = context.getContentResolver().query(table, projection, null, null, null);

            try {
                if (c != null && c.moveToFirst()) {
                    do {
                        String myName = c.getString(0);

                        if (myName != null) {
                            myName = myName.substring(0, 1).toUpperCase() + myName.substring(1);
                            return myName;
                        }
                    } while (c.moveToNext());
                }
            } finally {
                if (c != null)
                    c.close();
            }
        }
        return null;
    }

    public static Object deserializeObject(byte[] objectByteArray) {
        if (objectByteArray == null) return null;

        ByteArrayInputStream bis = null;
        ObjectInput in = null;
        try {
            bis = new ByteArrayInputStream(objectByteArray);
            in = new ObjectInputStream(bis);
            Object object = in.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            Log.e(TAG, "deserializeObject", e);
        } finally {
            try {
                if (bis != null) bis.close();
                if (in != null) in.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception:", e);
            }
        }
        Log.e(TAG, "objectByteArray cannot be converted to Object!");
        return null;
    }

    public static byte[] serializeObject(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutput out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (IOException e) {
            Log.e(TAG, "serializeObject", e);
        } finally {
            try {
                if (bos != null) bos.close();
                if (out != null) out.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception:", e);
            }
        }
        Log.e(TAG, "Object cannot be converted to byte array!");
        return null;
    }

    public static boolean isPackageInstalled(String packageName, Context context) {
        boolean ret = false;
        final PackageManager pm = context.getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName != null && packageInfo.packageName.equals(packageName)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public static int getImageTypeRes(String type) {
        if (type == null)
            return R.drawable.ic_action_file_not_found;

        if (type.contains("wav") || type.contains("mp3")) {
            return R.drawable.ic_action_file_music;
        } else if (type.contains("gif") || type.contains("jpg") || type.contains("jpeg")
                || type.contains("png")) {
            return R.drawable.ic_action_file_image;
        } else if (type.contains("3gp") || type.contains("mpg") || type.contains("mpeg")
                || type.contains("mpe") || type.contains("mp4") || type.contains("avi")) {
            return R.drawable.ic_action_file_movie;
        } else {
            return R.drawable.ic_action_file_folder;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Uri getOutputMediaFile(Context context) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(context.getPackageName(), "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return  Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg"));
    }
}
