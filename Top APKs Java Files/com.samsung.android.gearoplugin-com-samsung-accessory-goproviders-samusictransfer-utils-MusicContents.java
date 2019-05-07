package com.samsung.accessory.goproviders.samusictransfer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.MediaStore.Audio.Playlists;
import com.samsung.accessory.goproviders.samusictransfer.list.BaseListFragment.QueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.AlbumTrackQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.AllTrackQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.ArtistTrackQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.FolderTrackQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.PlaylistTrackQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.list.query.PlaylistTrackSupportQueryArgs;
import com.samsung.accessory.goproviders.samusictransfer.utils.log.iLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicContents
{
  private static final String DEFAULT_ORDER_BY_POST_FIX = " COLLATE LOCALIZED ASC";
  private static final String FAVORITE_LIST_NAME = "FavoriteList#328795!432@1341";
  private static final String MEDIASTORE_CONTENT_AUTHORITY_SLASH = "content://media/";
  private static final String NATIVE_MUSIC_PLAYER_CHN_PACKAGE_NAME = "com.samsung.android.app.music.chn";
  private static final String NATIVE_MUSIC_PLAYER_PACKAGE_NAME = "com.sec.android.app.music";
  public static final String PARAM_LIMIT = "limit";
  public static final int PLAYED_LIST_LIMIT = 100;
  private static final String QUICK_LIST = "Quick list";
  public static final String REORDER_NAME = "Reorder playlist";
  private static final String TAG = MusicContents.class.getSimpleName();
  private static long sFavoriteListId = -1L;
  private static final Object sFavoriteListToken = new Object();
  
  public MusicContents() {}
  
  public static long getFavoriteListId(Context paramContext)
  {
    if (sFavoriteListId < 0L) {}
    synchronized (sFavoriteListToken)
    {
      if (sFavoriteListId < 0L)
      {
        if (paramContext == null) {
          iLog.d(TAG, "try to getFavorietListId but id is minus value and context is null. Please check your logic");
        }
      }
      else {
        return sFavoriteListId;
      }
      updateFavoriteListIdFromName(paramContext);
    }
  }
  
  public static String getFavoriteListName(Context paramContext)
  {
    if (getNativeMusicPlayerVersionCode(paramContext) < 8) {
      return "Quick list";
    }
    return "FavoriteList#328795!432@1341";
  }
  
  public static Uri getLimitAppendedUri(Uri paramUri, String paramString)
  {
    return paramUri.buildUpon().appendQueryParameter("limit", paramString).build();
  }
  
  public static String getMatchedAudioCol(String paramString)
  {
    long l = Long.valueOf(paramString).longValue();
    if ((l == -14L) || (l == -12L) || (l == -13L)) {
      return "_id";
    }
    return "audio_id";
  }
  
  public static Uri getMediaContentUri()
  {
    return Uri.parse("content://media/external/audio/media");
  }
  
  public static int getMediaUriVersion(Context paramContext)
  {
    int i = 3;
    Uri localUri = Uri.parse("content://media/external/audio/media/music_folders");
    try
    {
      paramContext = paramContext.getContentResolver().query(localUri, null, null, null, null);
      int j = 2;
      i = j;
      if (paramContext != null)
      {
        paramContext.close();
        i = j;
      }
    }
    catch (IllegalStateException paramContext)
    {
      iLog.d(TAG, "getMediaUriVersion : " + paramContext.toString());
    }
    finally
    {
      if (0 == 0) {
        break label93;
      }
      throw new NullPointerException();
    }
    return i;
  }
  
  private static int getNativeMusicPlayerVersionCode(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo("com.sec.android.app.music", 128).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      iLog.d(TAG, "NameNotFoundException : " + paramContext.toString());
    }
    return -1;
  }
  
  public static ArrayList<AppConstants.Track> getPlaylistMember(Context paramContext, long paramLong, boolean paramBoolean)
  {
    iLog.d(TAG, "getPlaylistMember - playlistId : " + paramLong);
    if (paramLong == -1L) {
      return null;
    }
    Object localObject1 = new PlaylistTrackSupportQueryArgs(paramContext, paramLong, paramBoolean);
    paramContext = paramContext.getContentResolver().query(((BaseListFragment.QueryArgs)localObject1).uri, ((BaseListFragment.QueryArgs)localObject1).projection, ((BaseListFragment.QueryArgs)localObject1).selection, ((BaseListFragment.QueryArgs)localObject1).selectionArgs, ((BaseListFragment.QueryArgs)localObject1).orderBy);
    if (paramContext == null)
    {
      iLog.d(TAG, "cursor is null");
      return null;
    }
    if (paramContext.getCount() == 0)
    {
      iLog.d(TAG, "count is 0");
      paramContext.close();
      return null;
    }
    localObject1 = new ArrayList();
    try
    {
      int i = paramContext.getColumnIndex("_data");
      int j = paramContext.getColumnIndex("_size");
      int k = paramContext.getColumnIndex("title");
      int m = paramContext.getColumnIndex("album_id");
      paramContext.moveToFirst();
      do
      {
        String str = paramContext.getString(i);
        paramLong = paramContext.getLong(j);
        ((ArrayList)localObject1).add(new AppConstants.Track(paramContext.getString(k), str, paramContext.getLong(m), paramLong));
        paramBoolean = paramContext.moveToNext();
      } while (paramBoolean);
      return localObject1;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static int getSupportFormatFileCount(Context paramContext, int paramInt, String paramString)
  {
    Object localObject = null;
    switch (paramInt)
    {
    }
    while (localObject == null)
    {
      return -1;
      localObject = new PlaylistTrackQueryArgs(paramContext, Long.valueOf(paramString).longValue());
      continue;
      localObject = new AllTrackQueryArgs();
      continue;
      localObject = new AlbumTrackQueryArgs(paramString);
      continue;
      localObject = new ArtistTrackQueryArgs(paramString);
      continue;
      localObject = new FolderTrackQueryArgs(paramString);
    }
    ((BaseListFragment.QueryArgs)localObject).projection = MusicContents.Query.COUNT_PROJECTION;
    ((BaseListFragment.QueryArgs)localObject).selection += " AND  lower(replace(_data, rtrim(_data, replace(_data, '.', '' ) ), ''))  IN (?,?,?,?,?) ";
    ((BaseListFragment.QueryArgs)localObject).selectionArgs = MusicContents.FileFormat.SUPPORT_SELECTION_ARGS;
    ((BaseListFragment.QueryArgs)localObject).orderBy = null;
    paramContext = paramContext.getContentResolver().query(((BaseListFragment.QueryArgs)localObject).uri, ((BaseListFragment.QueryArgs)localObject).projection, ((BaseListFragment.QueryArgs)localObject).selection, ((BaseListFragment.QueryArgs)localObject).selectionArgs, ((BaseListFragment.QueryArgs)localObject).orderBy);
    int j = -1;
    int i = j;
    if (paramContext != null) {
      i = j;
    }
    try
    {
      if (paramContext.moveToFirst()) {
        i = paramContext.getInt(paramContext.getColumnIndex("count_column"));
      }
      if ((paramContext != null) && (!paramContext.isClosed())) {
        paramContext.close();
      }
      iLog.d(TAG, "getSupportFormatFileCount - listType : " + paramInt + ", keyword : " + paramString + ", count : " + i);
      return i;
    }
    finally
    {
      if ((paramContext != null) && (!paramContext.isClosed())) {
        paramContext.close();
      }
    }
  }
  
  public static boolean hasLimitParam(Uri paramUri)
  {
    return paramUri.getQueryParameter("limit") != null;
  }
  
  public static boolean isNativeMusicPlayerExist(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(128).iterator();
    PackageInfo localPackageInfo;
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((!localPackageInfo.packageName.contains("com.sec.android.app.music")) && (!localPackageInfo.packageName.contains("com.samsung.android.app.music.chn")));
    boolean bool1 = true;
    iLog.d(TAG, "isNativeMusicPlayerExist " + bool1);
    return bool1;
  }
  
  private static boolean updateFavoriteListIdFromName(Context paramContext)
  {
    if (!PermissionCheckUtil.hasPermission(paramContext, "android.permission.READ_EXTERNAL_STORAGE"))
    {
      iLog.e(TAG, "updateFavoriteListIdFromName no permission");
      sFavoriteListId = -1L;
      return false;
    }
    Object localObject2 = null;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject1 = localObject2;
    try
    {
      Uri localUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI.buildUpon().appendQueryParameter("secFilter", "include").build();
      localObject1 = localObject2;
      paramContext = getFavoriteListName(paramContext);
      localObject1 = localObject2;
      paramContext = localContentResolver.query(localUri, new String[] { "_id" }, "name= ?", new String[] { paramContext }, null);
      if (paramContext != null)
      {
        localObject1 = paramContext;
        if (paramContext.moveToFirst())
        {
          localObject1 = paramContext;
          sFavoriteListId = paramContext.getLong(0);
          if (paramContext != null) {
            paramContext.close();
          }
          return true;
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
      return false;
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
  }
}
