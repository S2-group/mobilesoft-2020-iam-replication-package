package com.baidu.simeji.skins.data;

import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.simeji.App;
import com.baidu.simeji.common.data.impl.AbstractDataProvider;
import com.baidu.simeji.common.data.impl.fetchers.HttpFetcher;
import com.baidu.simeji.common.data.impl.fetchers.ServerJsonConverter;
import com.baidu.simeji.common.network.NetworkUtils;
import com.baidu.simeji.common.network.NetworkUtils.DownloadInfo;
import com.baidu.simeji.common.receivers.PackageReceiver.PackageListener;
import com.baidu.simeji.common.statistic.StatisticUtil;
import com.baidu.simeji.common.util.ExternalStrageUtil;
import com.baidu.simeji.common.util.FileUtils;
import com.baidu.simeji.common.util.WorkerThreadPool;
import com.baidu.simeji.preferences.SimejiMultiProcessPreference;
import com.baidu.simeji.sticker.entry.AbsSticker;
import com.baidu.simeji.sticker.entry.ApkSticker;
import com.baidu.simeji.sticker.entry.ZipSticker;
import com.baidu.simeji.util.ApiUtil;
import com.baidu.simeji.util.DebugLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApkStickerProvider
  extends AbstractDataProvider<List<AbsSticker>>
{
  public static final String EMOJI_PACKAGE_PREFFIX = "com.adamrocker.android.input.simeji.global.emoji.";
  public static final String KEY = ApkStickerProvider.class.getName();
  public static final String STICKER_PACKAGE_PREFFIX = "com.adamrocker.android.input.simeji.global.sticker.";
  private static final String TAG = ApkStickerProvider.class.getSimpleName();
  private static final ApkStickerProvider sInstance = new ApkStickerProvider();
  private List<String> mEmojiList = new ArrayList();
  private final PackageReceiver.PackageListener mPackageListener = new ApkStickerProvider.1(this);
  private List<AbsSticker> mStickerList = new ArrayList();
  private String mZipDir;
  
  private ApkStickerProvider()
  {
    WorkerThreadPool.getInstance().execute(new ApkStickerProvider.2(this), true);
    setFetcher(new ApkStickerProvider.StickerFetcher(this, null));
  }
  
  private boolean addSticker(List<AbsSticker> paramList, String paramString, boolean paramBoolean)
  {
    for (;;)
    {
      int i;
      try
      {
        if (paramString.startsWith("com.adamrocker.android.input.simeji.global.sticker.")) {
          break label137;
        }
        return false;
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return false;
      }
      if (i < paramList.size())
      {
        if (((AbsSticker)paramList.get(i)).mStickerId.equalsIgnoreCase(paramString)) {
          return false;
        }
      }
      else
      {
        if (SimejiMultiProcessPreference.getStringPreference(App.instance, "key_sticker_zip_data", "").contains(paramString))
        {
          paramString = new ZipSticker(this.mZipDir + paramString, paramString);
          if (paramBoolean)
          {
            paramList.add(0, paramString);
            break;
          }
        }
        else
        {
          paramString = new ApkSticker(paramString);
          continue;
        }
        paramList.add(paramString);
        break;
        label137:
        i = 0;
        continue;
      }
      i += 1;
    }
    return true;
  }
  
  public static ApkStickerProvider getInstance()
  {
    return sInstance;
  }
  
  private void reloadOrder()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = SimejiMultiProcessPreference.getStringPreference(App.instance, "key_sticker_mybox_sort_list", "").split(",");
    int i = 0;
    while (i < arrayOfString.length)
    {
      if ((addSticker(localArrayList, arrayOfString[i], false)) && (((AbsSticker)localArrayList.get(localArrayList.size() - 1)).getType() == 1))
      {
        AbsSticker localAbsSticker = (AbsSticker)localArrayList.get(localArrayList.size() - 1);
        if (!new File(this.mZipDir + localAbsSticker.mStickerId).exists()) {
          localArrayList.remove(localArrayList.size() - 1);
        }
      }
      i += 1;
    }
    this.mStickerList = localArrayList;
    refresh();
  }
  
  private void saveEmojiList()
  {
    String str1 = "";
    int i = 0;
    while (i < this.mEmojiList.size())
    {
      String str2 = str1;
      if (!TextUtils.isEmpty(str1)) {
        str2 = str1 + ",";
      }
      str1 = str2 + (String)this.mEmojiList.get(i);
      i += 1;
    }
    SimejiMultiProcessPreference.saveStringPreference(App.instance, "key_emoji_apk_list", str1);
  }
  
  private void saveList()
  {
    Object localObject1 = "";
    String str = "";
    int i = 0;
    if (i < this.mStickerList.size())
    {
      if (TextUtils.isEmpty(str)) {
        break label199;
      }
      str = str + ",";
    }
    label196:
    label199:
    for (;;)
    {
      Object localObject2 = localObject1;
      if (((AbsSticker)this.mStickerList.get(i)).getType() == 1)
      {
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break label196;
        }
        localObject1 = (String)localObject1 + ",";
      }
      for (;;)
      {
        localObject2 = (String)localObject1 + ((AbsSticker)this.mStickerList.get(i)).mStickerId;
        str = str + ((AbsSticker)this.mStickerList.get(i)).mStickerId;
        i += 1;
        localObject1 = localObject2;
        break;
        SimejiMultiProcessPreference.saveStringPreference(App.instance, "key_sticker_mybox_sort_list", str);
        SimejiMultiProcessPreference.saveStringPreference(App.instance, "key_sticker_zip_data", (String)localObject1);
        return;
      }
    }
  }
  
  public void addSticker(AbsSticker paramAbsSticker)
  {
    SimejiMultiProcessPreference.saveIntPreference(App.instance, "key_keyboard_spoof_last_position", 0);
    this.mStickerList.add(0, paramAbsSticker);
    refresh();
  }
  
  public void deleteSticker(AbsSticker paramAbsSticker)
  {
    paramAbsSticker.delete(App.instance);
    this.mStickerList.remove(paramAbsSticker);
    refresh();
  }
  
  public void getApkSupportVersion()
  {
    TreeMap localTreeMap;
    String str;
    int i;
    Object localObject4;
    JSONArray localJSONArray2;
    AbsSticker localAbsSticker;
    Object localObject1;
    if ((this.mStickerList != null) && (this.mStickerList.size() > 0))
    {
      localTreeMap = new TreeMap();
      str = SimejiMultiProcessPreference.getStringPreference(App.instance, "key_sticker_apk_support_version_list", "");
      if (!TextUtils.isEmpty(str)) {
        try
        {
          JSONArray localJSONArray1 = new JSONArray(str);
          i = 0;
          while (i < localJSONArray1.length())
          {
            localObject4 = localJSONArray1.getJSONObject(i);
            if (localObject4 != null) {
              localTreeMap.put(((JSONObject)localObject4).optString("package"), Integer.valueOf(((JSONObject)localObject4).getInt("app_version")));
            }
            i += 1;
          }
          localJSONArray2 = new JSONArray();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
      }
      i = 0;
      if (i < this.mStickerList.size())
      {
        localAbsSticker = (AbsSticker)this.mStickerList.get(i);
        localObject1 = (String)new ServerJsonConverter(new HttpFetcher("https://simejiglobal.com/smallapp/sticker/androidI18n/getStickerByPkg?package=" + localAbsSticker.mStickerId)).fetch();
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break label355;
        }
      }
    }
    for (;;)
    {
      try
      {
        localObject1 = new JSONArray((String)localObject1);
        if (((JSONArray)localObject1).length() <= 0) {
          break label355;
        }
        localObject1 = ((JSONArray)localObject1).getJSONObject(0);
        if (localObject1 == null) {
          break label355;
        }
        localObject1 = Integer.valueOf(((JSONObject)localObject1).optString("app_version"));
        localObject4 = localObject1;
        if (localObject1 == null) {
          localObject4 = (Integer)localTreeMap.get(localAbsSticker.mStickerId);
        }
        if (localObject4 == null) {}
      }
      catch (Exception localException2)
      {
        try
        {
          localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("package", localAbsSticker.mStickerId);
          ((JSONObject)localObject1).put("app_version", ((Integer)localObject4).intValue());
          localJSONArray2.put(localObject1);
          i += 1;
          break;
          localException2 = localException2;
          Object localObject2 = null;
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          continue;
        }
      }
      if (!localJSONArray2.toString().equals(str)) {
        SimejiMultiProcessPreference.saveStringPreference(App.instance, "key_sticker_apk_support_version_list", localJSONArray2.toString());
      }
      return;
      label355:
      Object localObject3 = null;
    }
  }
  
  public void getKeyboardSticker()
  {
    label805:
    label808:
    for (;;)
    {
      try
      {
        Object localObject1 = (String)new ServerJsonConverter(new HttpFetcher("https://simejiglobal.com/smallapp/sticker/androidI18n/getKeyboardSticker?app_version=143&system_version=" + Build.VERSION.SDK_INT + "&lang=" + Locale.getDefault().getLanguage() + "&country=" + Locale.getDefault().getCountry())).fetch();
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (!bool)
        {
          TreeMap localTreeMap;
          int i;
          Object localObject4;
          Object localObject2;
          JSONArray localJSONArray;
          int k;
          try
          {
            localObject1 = new JSONObject((String)localObject1).getJSONArray("list");
            if ((localObject1 == null) || (((JSONArray)localObject1).length() <= 0)) {
              break;
            }
            localTreeMap = new TreeMap();
            localObject3 = SimejiMultiProcessPreference.getStringPreference(App.instance, "key_sticker_keyboard_list", "");
            bool = TextUtils.isEmpty((CharSequence)localObject3);
            if (bool) {}
          }
          catch (Exception localException1)
          {
            try
            {
              Object localObject3 = new JSONArray((String)localObject3);
              i = 0;
              if (i < ((JSONArray)localObject3).length())
              {
                localObject4 = ((JSONArray)localObject3).getJSONObject(i);
                localTreeMap.put(((JSONObject)localObject4).optString("package").toLowerCase(), Boolean.valueOf(((JSONObject)localObject4).optBoolean("is_new")));
                i += 1;
                continue;
                localException1 = localException1;
                localObject2 = null;
              }
            }
            catch (Exception localException3)
            {
              localException3.printStackTrace();
            }
            localJSONArray = new JSONArray();
            localObject4 = new TreeMap();
            k = 0;
            i = 0;
          }
          int j = localObject2.length();
          if (k < j) {
            try
            {
              Object localObject5 = localObject2.getJSONObject(k);
              String str1 = ((JSONObject)localObject5).optString("package").toLowerCase();
              String str3 = ((JSONObject)localObject5).optString("keyboard_preview_img");
              Object localObject6 = ((JSONObject)localObject5).optString("icon");
              if ((143 < ((JSONObject)localObject5).optInt("app_version")) || (TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str3)) || (TextUtils.isEmpty((CharSequence)localObject6)) || (!str1.startsWith("com.adamrocker.android.input.simeji.global.sticker.")) || (((Map)localObject4).containsKey(str1))) {
                break label808;
              }
              String str2 = ExternalStrageUtil.getExternalFilesDir(App.instance, "sticker") + "/" + str1.hashCode() + "/preview";
              NetworkUtils.DownloadInfo localDownloadInfo;
              if (!FileUtils.checkFileExist(str2))
              {
                localDownloadInfo = new NetworkUtils.DownloadInfo();
                localDownloadInfo.link = str3;
                localDownloadInfo.path = str2;
                NetworkUtils.syncDownload(localDownloadInfo);
                if (!FileUtils.checkFileExist(str2)) {
                  StatisticUtil.onEvent(200124, str1);
                }
              }
              str3 = ExternalStrageUtil.getExternalFilesDir(App.instance, "sticker") + "/" + str1.hashCode() + "/icon";
              if (!FileUtils.checkFileExist(str3))
              {
                localDownloadInfo = new NetworkUtils.DownloadInfo();
                localDownloadInfo.link = ((String)localObject6);
                localDownloadInfo.path = str3;
                NetworkUtils.syncDownload(localDownloadInfo);
                if (!FileUtils.checkFileExist(str3)) {
                  StatisticUtil.onEvent(200123, str1);
                }
              }
              if ((!FileUtils.checkFileExist(str2)) || (!FileUtils.checkFileExist(str3))) {
                break label808;
              }
              ((Map)localObject4).put(str1, str1);
              ((JSONObject)localObject5).put("keyboard_preview_img_path", str2);
              ((JSONObject)localObject5).put("icon_path", str3);
              localObject6 = (Boolean)localTreeMap.get(str1);
              if (localObject6 != null)
              {
                bool = ((Boolean)localObject6).booleanValue();
                ((JSONObject)localObject5).put("is_new", bool);
                localJSONArray.put(localObject5);
                if (localObject6 != null) {
                  break label808;
                }
                localObject5 = this.mStickerList.iterator();
                j = 0;
                if (((Iterator)localObject5).hasNext())
                {
                  localObject6 = (AbsSticker)((Iterator)localObject5).next();
                  if (((AbsSticker)localObject6).mStickerId == null) {
                    break label805;
                  }
                  bool = ((AbsSticker)localObject6).mStickerId.equals(str1);
                  if (!bool) {
                    break label805;
                  }
                  j = 1;
                  continue;
                }
              }
              else
              {
                bool = true;
                continue;
              }
              if (j != 0) {
                break label808;
              }
              i = 1;
              k += 1;
            }
            catch (Exception localException4)
            {
              localException4.printStackTrace();
              break label808;
            }
          }
          if (localJSONArray.length() <= 0) {
            break;
          }
          SimejiMultiProcessPreference.saveStringPreference(App.instance, "key_sticker_keyboard_list", localJSONArray.toString());
          if (i != 1) {
            break;
          }
          SimejiMultiProcessPreference.saveIntPreference(App.instance, "key_keyboard_sticker_new_suggest", 1);
          return;
        }
        StatisticUtil.onEvent(100264);
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        return;
      }
    }
  }
  
  public PackageReceiver.PackageListener getPackageListener()
  {
    return this.mPackageListener;
  }
  
  public List<AbsSticker> getStickers()
  {
    reloadOrder();
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.mStickerList);
    return localArrayList;
  }
  
  public void loadDataFromSp()
  {
    this.mZipDir = (ExternalStrageUtil.getExternalFilesDir(App.instance, "sticker_zip").toString() + "/");
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = SimejiMultiProcessPreference.getStringPreference(App.instance, "key_sticker_mybox_sort_list", "");
    Object localObject2 = ((String)localObject1).split(",");
    DebugLog.d(TAG, (String)localObject1);
    int i = 0;
    while (i < localObject2.length)
    {
      addSticker(localArrayList, localObject2[i], false);
      i += 1;
    }
    DebugLog.d(TAG, (String)localObject1);
    localObject1 = new HashMap();
    localObject2 = new HashMap();
    Object localObject3 = localArrayList.iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (AbsSticker)((Iterator)localObject3).next();
      if (((AbsSticker)localObject4).getType() == 1) {
        ((HashMap)localObject2).put(((AbsSticker)localObject4).mStickerId, localObject4);
      } else {
        ((HashMap)localObject1).put(((AbsSticker)localObject4).mStickerId, localObject4);
      }
    }
    localObject3 = new ArrayList();
    Object localObject4 = ApiUtil.getInstalledPackages(App.instance.getPackageManager());
    Object localObject5;
    if (localObject4 != null)
    {
      localObject4 = ((List)localObject4).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (PackageInfo)((Iterator)localObject4).next();
        if (((PackageInfo)localObject5).packageName.startsWith("com.adamrocker.android.input.simeji.global.sticker.")) {
          ((List)localObject3).add(((PackageInfo)localObject5).packageName);
        }
        if (((PackageInfo)localObject5).packageName.startsWith("com.adamrocker.android.input.simeji.global.emoji.")) {
          this.mEmojiList.add(((PackageInfo)localObject5).packageName);
        }
      }
    }
    localObject4 = ((HashMap)localObject1).keySet().iterator();
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (String)((Iterator)localObject4).next();
      if (!((List)localObject3).contains(localObject5)) {
        localArrayList.remove(((HashMap)localObject1).get(localObject5));
      }
    }
    localObject4 = ((HashMap)localObject2).keySet().iterator();
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (String)((Iterator)localObject4).next();
      if (!new File(this.mZipDir + (String)localObject5).exists()) {
        localArrayList.remove(((HashMap)localObject2).get(localObject5));
      }
    }
    localObject2 = ((List)localObject3).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      if (!((HashMap)localObject1).containsKey(localObject3)) {
        localArrayList.add(0, new ApkSticker((String)localObject3));
      }
    }
    if (sHandler != null) {
      sHandler.post(new ApkStickerProvider.3(this, localArrayList));
    }
  }
  
  public void swapItem(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != paramInt2) && (paramInt1 < this.mStickerList.size()) && (paramInt2 <= this.mStickerList.size()))
    {
      AbsSticker localAbsSticker = (AbsSticker)this.mStickerList.get(paramInt1);
      this.mStickerList.remove(localAbsSticker);
      this.mStickerList.add(paramInt2, localAbsSticker);
      refresh();
    }
  }
}
