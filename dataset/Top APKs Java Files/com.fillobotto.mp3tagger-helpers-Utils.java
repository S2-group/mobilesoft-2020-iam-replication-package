package helpers;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Utils
{
  public static final String AlbumId = "album_id";
  public static final int AlbumMode = 2;
  public static final String CanSwipe = "can_swipe";
  public static final String DataPath = "path_file";
  public static final String EditMode = "mode";
  public static final String ImagePath = "path_art";
  public static final int SongMode = 1;
  
  public Utils() {}
  
  public static boolean IS_BETA()
  {
    return false;
  }
  
  public static void copyInputStreamToFile(InputStream paramInputStream, File paramFile)
  {
    try
    {
      paramFile = new FileOutputStream(paramFile);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        paramFile.write(arrayOfByte, 0, i);
      }
      paramFile.close();
    }
    catch (Exception paramInputStream)
    {
      paramInputStream.printStackTrace();
      return;
    }
    paramInputStream.close();
  }
  
  public static int deleteAlbumArt(Context paramContext, String paramString)
  {
    return paramContext.getContentResolver().delete(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), Long.parseLong(getAlbumId(paramContext, paramString))), null, null);
  }
  
  public static void deleteCache(Context paramContext)
  {
    try
    {
      paramContext = new File(paramContext.getCacheDir().getPath() + "/audio");
      if ((paramContext.exists()) && (paramContext.isDirectory())) {
        deleteDir(paramContext);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean deleteDir(File paramFile)
  {
    int i;
    if ((paramFile != null) && (paramFile.isDirectory()))
    {
      String[] arrayOfString = paramFile.list();
      int j = arrayOfString.length;
      i = 0;
      if (i < j) {
        if (deleteDir(new File(paramFile, arrayOfString[i]))) {}
      }
    }
    while ((paramFile == null) || (!paramFile.delete()))
    {
      return false;
      i += 1;
      break;
    }
    return true;
  }
  
  public static String getAlbumArtPath(Context paramContext, String paramString)
  {
    paramString = paramContext.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[] { "_id", "album_art" }, "_id=?", new String[] { paramString }, null);
    paramContext = "";
    if (paramString.moveToFirst()) {
      paramContext = paramString.getString(paramString.getColumnIndex("album_art"));
    }
    paramString.close();
    return paramContext;
  }
  
  public static String getAlbumId(Context paramContext, String paramString)
  {
    paramString = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "album_id", "_data" }, "_data=?", new String[] { paramString }, null);
    paramContext = "";
    if (paramString.moveToFirst()) {
      paramContext = paramString.getString(paramString.getColumnIndex("album_id"));
    }
    paramString.close();
    return paramContext;
  }
  
  public static int getEditedSongs(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("edited_songs", 0);
  }
  
  public static String getPostDataString(HashMap<String, String> paramHashMap)
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    paramHashMap = paramHashMap.entrySet().iterator();
    if (paramHashMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramHashMap.next();
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getKey(), "UTF-8"));
        localStringBuilder.append("=");
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getValue(), "UTF-8"));
        break;
        localStringBuilder.append("&");
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String[] getScanDirs(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("scan_dirs", null);
    if (paramContext == null) {
      return new String[] { "" };
    }
    return paramContext.split("\\|");
  }
  
  public static String getSdPath(Context paramContext)
  {
    int i = 0;
    String str = null;
    Object localObject2 = null;
    localObject1 = str;
    try
    {
      File[] arrayOfFile = ContextCompat.getExternalFilesDirs(paramContext, null);
      localObject1 = str;
      int j = arrayOfFile.length;
      for (paramContext = (Context)localObject2;; paramContext = (Context)localObject2)
      {
        localObject1 = paramContext;
        if (i >= j) {
          break;
        }
        localObject1 = paramContext;
        str = arrayOfFile[i].getAbsolutePath();
        localObject2 = paramContext;
        localObject1 = paramContext;
        if (!str.isEmpty())
        {
          localObject2 = paramContext;
          localObject1 = paramContext;
          if (!str.contains("emulated"))
          {
            localObject2 = paramContext;
            localObject1 = paramContext;
            if (str.contains("Android"))
            {
              localObject1 = paramContext;
              localObject2 = str.substring(0, str.indexOf("/Android"));
            }
          }
        }
        i += 1;
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
  }
  
  public static File getTempArtDir(Context paramContext)
  {
    paramContext = new File(paramContext.getCacheDir() + "/arts/");
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext;
  }
  
  public static boolean hasWritePermission(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      String[] arrayOfString = FileUtil.getExtSdCardPaths(paramContext);
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (!FileUtil.isWritableNormalOrSaf(paramContext, new File(arrayOfString[i]))) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return true;
  }
  
  public static void insertAlbumArt(Context paramContext, String paramString, Uri paramUri)
  {
    paramContext = paramContext.getContentResolver();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("album_id", paramString);
    localContentValues.put("_data", paramUri.toString());
    paramUri = Uri.parse("content://media/external/audio/albumart");
    paramContext.delete(ContentUris.withAppendedId(paramUri, Long.valueOf(paramString).longValue()), null, null);
    paramContext.insert(paramUri, localContentValues);
  }
  
  public static AlertDialog openSDDialog(Activity paramActivity)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(paramActivity.getResources().getString(2131230786));
    String str;
    if (Build.VERSION.SDK_INT >= 21)
    {
      ImageView localImageView = new ImageView(paramActivity);
      localImageView.setImageResource(2130837678);
      str = getSdPath(paramActivity);
      if (str != null)
      {
        str = paramActivity.getString(2131230782, new Object[] { " (" + str + ")" });
        localBuilder.setMessage(str).setCancelable(true).setPositiveButton(paramActivity.getResources().getString(2131230787), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Utils.triggerStorageAccessFramework(this.val$context);
          }
        }).setNegativeButton(paramActivity.getResources().getString(2131230778), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.cancel();
          }
        }).setView(localImageView);
      }
    }
    for (;;)
    {
      return localBuilder.create();
      str = paramActivity.getString(2131230782, new Object[] { "" });
      break;
      if (Build.VERSION.SDK_INT < 19) {
        break label242;
      }
      localBuilder.setMessage(2131230781).setCancelable(true).setPositiveButton(paramActivity.getResources().getString(2131230789), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.val$context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=nextapp.sdfix")));
        }
      }).setNegativeButton(paramActivity.getResources().getString(2131230778), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
    }
    label242:
    return null;
  }
  
  public static void runOnUiThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  public static void setTheme(Activity paramActivity)
  {
    switch (PreferenceUtil.getTheme(paramActivity))
    {
    default: 
      return;
    case 0: 
      paramActivity.setTheme(2131427383);
      return;
    }
    paramActivity.setTheme(2131427457);
  }
  
  public static boolean showSwipe(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    boolean bool = localSharedPreferences.getBoolean("first_setup_swipe", true);
    if (bool)
    {
      localEditor.putBoolean("first_setup_swipe", false);
      localEditor.apply();
    }
    return (bool) && (getEditedSongs(paramContext) > 5);
  }
  
  public static boolean showTutorial(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = paramContext.getBoolean(paramString, true);
    if (bool)
    {
      paramContext = paramContext.edit();
      paramContext.putBoolean(paramString, false);
      paramContext.apply();
    }
    return bool;
  }
  
  private static void triggerStorageAccessFramework(Activity paramActivity)
  {
    Intent localIntent;
    if (Build.VERSION.SDK_INT >= 21) {
      localIntent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
    }
    try
    {
      paramActivity.startActivityForResult(localIntent, 42);
      return;
    }
    catch (Exception localException1)
    {
      int i;
      for (;;)
      {
        Object localObject1 = paramActivity.getPackageManager().getInstalledApplications(0);
        i = 0;
        try
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
            if ((((ApplicationInfo)localObject2).packageName.equals("com.android.documentsui")) && (!((ApplicationInfo)localObject2).enabled))
            {
              i = 1;
              localObject2 = new AlertDialog.Builder(paramActivity);
              ((AlertDialog.Builder)localObject2).setTitle(2131230785);
              ((AlertDialog.Builder)localObject2).setMessage(2131230784).setCancelable(true).setPositiveButton(2131230778, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                {
                  paramAnonymousDialogInterface.dismiss();
                }
              });
              ((AlertDialog.Builder)localObject2).create().show();
            }
          }
          if (i != 0) {}
        }
        catch (Exception localException2)
        {
          Toast.makeText(paramActivity, "This device does not support writing to SD card", 1).show();
          return;
        }
      }
      Toast.makeText(paramActivity, "This device does not support writing to SD card", 1).show();
    }
  }
}
