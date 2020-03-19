package com.fiberlink.maas360.android.control.Dao.model.devicepolicies;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import bqr;
import brv;
import bvq;
import bvr;
import bxk;
import bxm;
import cag;
import cal;
import cmj;
import cmk;
import cml;
import cnr;
import cod;
import com.fiberlink.maas360.android.control.ControlApplication;
import com.fiberlink.maas360.android.control.lib.rulesengine.IFeatureService;
import cqv;
import crc;
import crq;
import dfk;
import dho;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nv;

public class BookmarkSettings
  implements Serializable
{
  private static final int DEFAULT_IMAGE_SIZE = 48;
  public static final String DUPLICATE = "duplicate";
  public static final String INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
  public static final String UNINSTALL_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";
  private static final String loggerName = BookmarkSettings.class.getSimpleName();
  private static final long serialVersionUID = 1L;
  private String AUTHORITY = "com.android.launcher2.settings";
  private String DEFAULT_PERMISSION = "com.android.launcher.permission.READ_SETTINGS";
  private String HTC_PERMISSION = "com.htc.launcher.permission.READ_SETTINGS";
  public Map<String, BookmarkSettings.BookmarkProfile> bookmarkProfiles;
  public String policyName;
  
  public BookmarkSettings() {}
  
  private void createImage(BookmarkSettings.BookmarkProfile paramBookmarkProfile, File paramFile, Intent paramIntent, boolean paramBoolean)
  {
    if (paramBookmarkProfile.showOnHomepage) {}
    try
    {
      paramFile = new FileInputStream(paramFile.getAbsolutePath());
      Bitmap localBitmap = BitmapFactory.decodeStream(paramFile);
      if (localBitmap != null)
      {
        int i = getCurrentDensity();
        localBitmap = Bitmap.createScaledBitmap(localBitmap, i, i, true);
        if (localBitmap != null) {
          paramIntent.putExtra("android.intent.extra.shortcut.ICON", localBitmap);
        }
      }
      if (paramFile != null) {
        paramFile.close();
      }
    }
    catch (Exception paramFile)
    {
      for (;;)
      {
        dho.c(loggerName, new String[] { paramFile.getMessage() });
      }
    }
    completeIntent(paramBookmarkProfile, paramIntent, paramBoolean);
  }
  
  private void deleteBookmarkProfile(BookmarkSettings.BookmarkProfile paramBookmarkProfile)
  {
    deleteContainerProfile(paramBookmarkProfile);
    deleteLauncherProfile(paramBookmarkProfile);
  }
  
  private void deleteContainerProfile(BookmarkSettings.BookmarkProfile paramBookmarkProfile)
  {
    cag.a(ControlApplication.b()).b(paramBookmarkProfile.bookmarkName, "container_web_shorcut");
    dho.b(loggerName, new String[] { "Bookmark- ", paramBookmarkProfile.bookmarkName, " is removed from Container" });
  }
  
  private void deleteFileUnderBookmarksFolder(String paramString)
  {
    String str = ControlApplication.b().getFilesDir() + "/Bookmarks/";
    if (new File(str).exists())
    {
      dho.a(loggerName, new String[] { "Deleting old policy Bookmark icon: ", paramString });
      paramString = new File(str + "/" + paramString);
      if (paramString.exists()) {
        paramString.delete();
      }
    }
  }
  
  private void deleteLauncherProfile(BookmarkSettings.BookmarkProfile paramBookmarkProfile)
  {
    ControlApplication localControlApplication = ControlApplication.b();
    Intent localIntent1 = new Intent();
    localIntent1.setAction("android.intent.action.VIEW");
    localIntent1.setData(Uri.parse(paramBookmarkProfile.bookmarkURL));
    Intent localIntent2 = new Intent();
    localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
    localIntent2.putExtra("android.intent.extra.shortcut.NAME", paramBookmarkProfile.bookmarkName);
    localIntent2.putExtra("duplicate", false);
    localIntent2.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localControlApplication.sendBroadcast(localIntent2);
  }
  
  private void downloadFile(final BookmarkSettings.BookmarkProfile paramBookmarkProfile, final Intent paramIntent, final boolean paramBoolean)
  {
    String str2 = paramBookmarkProfile.bookmarkIcon;
    if (cnr.h(str2))
    {
      completeIntent(paramBookmarkProfile, paramIntent, paramBoolean);
      return;
    }
    String str1 = "";
    String str3 = ControlApplication.b().getFilesDir() + "/Bookmarks/";
    new File(str3).mkdirs();
    if (str2.lastIndexOf('.') != -1) {
      str1 = paramBookmarkProfile.bookmarkIcon.substring(str2.lastIndexOf('.'));
    }
    String str4 = paramBookmarkProfile.encCrc;
    final File localFile = new File(str3, paramBookmarkProfile.bookmarkIconname);
    if (!localFile.exists())
    {
      paramBookmarkProfile = new cmk(ControlApplication.b(), str2, new cmj()
      {
        public void a(int paramAnonymousInt)
        {
          if (7 == paramAnonymousInt)
          {
            dho.a(BookmarkSettings.loggerName, new String[] { "Image download complete : ", localFile.toString() });
            BookmarkSettings.this.createImage(paramBookmarkProfile, localFile, paramIntent, paramBoolean);
          }
        }
        
        public void b(int paramAnonymousInt)
        {
          if (7 == paramAnonymousInt)
          {
            dho.d(BookmarkSettings.loggerName, new String[] { "Error in downloading : ", localFile.toString() });
            BookmarkSettings.this.completeIntent(paramBookmarkProfile, paramIntent, paramBoolean);
          }
        }
      }, localFile.toString(), 7, str3, str4, str1, paramBookmarkProfile.encCrc, paramBookmarkProfile.encKey);
      cml.a().b(paramBookmarkProfile);
      return;
    }
    createImage(paramBookmarkProfile, localFile, paramIntent, paramBoolean);
  }
  
  private String getAuthorityFromPermission(Context paramContext)
  {
    brv localBrv = ControlApplication.b().p().a();
    if ((localBrv != null) && (cnr.i(localBrv.a("WEBSHORCUTS_AUTHORITY")))) {
      return localBrv.a("WEBSHORCUTS_AUTHORITY");
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if (this.DEFAULT_PERMISSION.equals(localProviderInfo.readPermission))
            {
              if (localBrv != null) {
                localBrv.b("WEBSHORCUTS_AUTHORITY", localProviderInfo.authority);
              }
              return localProviderInfo.authority;
            }
            if (this.HTC_PERMISSION.equals(localProviderInfo.readPermission))
            {
              if (localBrv != null) {
                localBrv.b("WEBSHORCUTS_AUTHORITY", localProviderInfo.authority);
              }
              return localProviderInfo.authority;
            }
            i += 1;
          }
        }
      }
    }
    return null;
  }
  
  private int getCurrentDensity()
  {
    WindowManager localWindowManager = (WindowManager)ControlApplication.b().getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    switch (localDisplayMetrics.densityDpi)
    {
    default: 
      return 48;
    case 120: 
      return 36;
    case 160: 
      return 48;
    case 240: 
      return 72;
    case 320: 
      return 96;
    case 400: 
      return 120;
    case 480: 
      return 144;
    case 560: 
      return 168;
    }
    return 192;
  }
  
  public static BookmarkSettings loadBookmarkSettings(bvr paramBvr)
  {
    BookmarkSettings localBookmarkSettings = new BookmarkSettings();
    localBookmarkSettings.policyName = paramBvr.b();
    Object localObject6 = paramBvr.d();
    HashMap localHashMap = new HashMap();
    if ((ControlApplication.b().w().evaluateFeature("kindleFireCheck")) && (ControlApplication.b().a() != bqr.a))
    {
      localBookmarkSettings.setBookmarkProfiles(localHashMap);
      return localBookmarkSettings;
    }
    Object localObject2;
    label129:
    Object localObject3;
    label155:
    Object localObject4;
    label184:
    label215:
    label246:
    label263:
    String str2;
    label356:
    label434:
    label452:
    String str1;
    if (localObject6 != null)
    {
      try
      {
        paramBvr = new HashMap();
        Object localObject1 = ((bxk)localObject6).b("bookmarkURL");
        if (localObject1 == null) {
          break label690;
        }
        paramBvr = ((bxm)localObject1).a;
        localObject1 = new HashMap();
        localObject2 = ((bxk)localObject6).b("bookmarkName");
        if (localObject2 == null) {
          break label687;
        }
        localObject1 = ((bxm)localObject2).a;
        localObject2 = new HashMap();
        localObject3 = ((bxk)localObject6).b("iconImageEncCrc");
        if (localObject3 == null) {
          break label684;
        }
        localObject2 = ((bxm)localObject3).a;
        localObject3 = new HashMap();
        localObject4 = ((bxk)localObject6).b("iconImageKey");
        if (localObject4 == null) {
          break label681;
        }
        localObject3 = ((bxm)localObject4).a;
        localObject4 = new HashMap();
        Object localObject5 = ((bxk)localObject6).b("iconImageUrl");
        if (localObject5 == null) {
          break label678;
        }
        localObject4 = ((bxm)localObject5).a;
        localObject5 = new HashMap();
        localObject6 = ((bxk)localObject6).b("showOnHomePage");
        if (localObject6 == null) {
          break label675;
        }
        localObject5 = ((bxm)localObject6).a;
        if (paramBvr == null) {
          break label584;
        }
        Iterator localIterator = paramBvr.keySet().iterator();
        if (!localIterator.hasNext()) {
          break label584;
        }
        str2 = (String)localIterator.next();
        localBookmarkSettings.getClass();
        localBookmarkProfile = new BookmarkSettings.BookmarkProfile(localBookmarkSettings);
        localBookmarkProfile.bookmarkName = ((String)((Map)localObject1).get(str2)).trim();
        localBookmarkProfile.bookmarkURL = ((String)paramBvr.get(str2)).trim();
        if (((Map)localObject4).get(str2) != null) {
          break label594;
        }
        localObject6 = "";
        localBookmarkProfile.bookmarkIcon = ((String)localObject6);
        if (localObject5 != null) {
          localBookmarkProfile.showOnHomepage = cnr.a((String)((Map)localObject5).get(str2), true);
        }
        if ((!cnr.i(localBookmarkProfile.bookmarkIcon)) || (localBookmarkProfile.bookmarkIcon.lastIndexOf('.') == -1)) {
          break label668;
        }
        localObject6 = localBookmarkProfile.bookmarkIcon.substring(localBookmarkProfile.bookmarkIcon.lastIndexOf('.'));
        if (localObject2 == null) {
          break label693;
        }
        if (((Map)localObject2).get(str2) != null) {
          break label611;
        }
      }
      catch (Exception paramBvr)
      {
        BookmarkSettings.BookmarkProfile localBookmarkProfile;
        label516:
        dho.a(loggerName, new String[] { paramBvr.getMessage() });
      }
      localBookmarkProfile.bookmarkIconname = str1;
      if (cnr.i(localBookmarkProfile.bookmarkIconname)) {
        localBookmarkProfile.bookmarkIconname += (String)localObject6;
      }
      if (localObject2 == null) {
        break label700;
      }
      if (((Map)localObject2).get(str2) != null) {
        break label630;
      }
      break label700;
      localBookmarkProfile.encCrc = ((String)localObject6);
      if (localObject3 == null) {
        break label707;
      }
      if (((Map)localObject3).get(str2) != null) {
        break label649;
      }
      break label707;
    }
    for (;;)
    {
      localBookmarkProfile.encKey = ((String)localObject6);
      localHashMap.put(localBookmarkProfile.bookmarkName, localBookmarkProfile);
      break label263;
      label584:
      localBookmarkSettings.setBookmarkProfiles(localHashMap);
      return localBookmarkSettings;
      label594:
      localObject6 = (String)((Map)localObject4).get(str2);
      break label356;
      label611:
      str1 = ((String)((Map)localObject2).get(str2)).trim();
      break label452;
      label630:
      localObject6 = ((String)((Map)localObject2).get(str2)).trim();
      break label516;
      label649:
      localObject6 = ((String)((Map)localObject3).get(str2)).trim();
      continue;
      label668:
      localObject6 = "";
      break label434;
      label675:
      break label246;
      label678:
      break label215;
      label681:
      break label184;
      label684:
      break label155;
      label687:
      break label129;
      label690:
      break;
      label693:
      str1 = "";
      break label452;
      label700:
      localObject6 = "";
      break label516;
      label707:
      localObject6 = "";
    }
  }
  
  public void clearBookmarkSettings()
  {
    if ((ControlApplication.b().w().evaluateFeature("kindleFireCheck")) && (ControlApplication.b().a() != bqr.a)) {
      dho.a(loggerName, new String[] { "Bookmark settings not supported in kindle fire device" });
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.bookmarkProfiles.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        deleteBookmarkProfile((BookmarkSettings.BookmarkProfile)this.bookmarkProfiles.get(str));
      }
    }
  }
  
  public void clearOldBookmarkSettings()
  {
    if ((ControlApplication.b().w().evaluateFeature("kindleFireCheck")) && (ControlApplication.b().a() != bqr.a))
    {
      dho.a(loggerName, new String[] { "Bookmark settings not supported in kindle fire device" });
      return;
    }
    Object localObject2 = ControlApplication.b();
    Object localObject1 = ((ControlApplication)localObject2).A().H();
    localObject2 = ((ControlApplication)localObject2).A().G();
    if (localObject1 != null)
    {
      localObject1 = ((bvq)localObject1).p();
      if (localObject1 == null) {}
    }
    for (localObject1 = ((BookmarkSettings)localObject1).bookmarkProfiles;; localObject1 = null)
    {
      if (localObject2 != null)
      {
        localObject2 = ((bvq)localObject2).p();
        if (localObject2 == null) {}
      }
      for (localObject2 = ((BookmarkSettings)localObject2).bookmarkProfiles;; localObject2 = null)
      {
        ArrayList localArrayList = new ArrayList();
        if ((localObject1 != null) && (((Map)localObject1).size() > 0)) {}
        for (int i = 0;; i = 1)
        {
          if ((localObject2 != null) && (((Map)localObject2).size() > 0)) {}
          for (int j = 0;; j = 1)
          {
            if ((i == 0) && (j != 0)) {
              localArrayList.addAll(((Map)localObject1).values());
            }
            while ((i != 0) || (j != 0))
            {
              localObject1 = localArrayList.iterator();
              while (((Iterator)localObject1).hasNext())
              {
                localObject2 = (BookmarkSettings.BookmarkProfile)((Iterator)localObject1).next();
                deleteBookmarkProfile((BookmarkSettings.BookmarkProfile)localObject2);
                deleteFileUnderBookmarksFolder(((BookmarkSettings.BookmarkProfile)localObject2).bookmarkIconname);
              }
              break;
            }
            Iterator localIterator = ((Map)localObject1).keySet().iterator();
            label246:
            label319:
            label337:
            for (;;)
            {
              Object localObject3;
              BookmarkSettings.BookmarkProfile localBookmarkProfile;
              if (localIterator.hasNext())
              {
                localObject3 = (String)localIterator.next();
                localBookmarkProfile = (BookmarkSettings.BookmarkProfile)((Map)localObject2).get(localObject3);
                localObject3 = (BookmarkSettings.BookmarkProfile)((Map)localObject1).get(localObject3);
                if (localBookmarkProfile != null) {
                  break label319;
                }
                i = 1;
              }
              for (;;)
              {
                if (i == 0) {
                  break label337;
                }
                localArrayList.add(localObject3);
                break label246;
                break;
                if (!localBookmarkProfile.equals(localObject3)) {
                  i = 1;
                } else {
                  i = 0;
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected void completeIntent(BookmarkSettings.BookmarkProfile paramBookmarkProfile, Intent paramIntent, boolean paramBoolean)
  {
    deleteContainerProfile(paramBookmarkProfile);
    cag localCag = cag.a(ControlApplication.b());
    Object localObject = "";
    if (cnr.i(paramBookmarkProfile.bookmarkIconname)) {
      localObject = ControlApplication.b().getFilesDir() + "/Bookmarks/" + paramBookmarkProfile.bookmarkIconname;
    }
    localObject = new cal(paramBookmarkProfile.bookmarkName, "container_web_shorcut", paramBookmarkProfile.bookmarkName, (String)localObject, "", 1, 0, 1, 0, 0, 10000);
    localCag.a(paramBookmarkProfile.bookmarkName, "container_web_shorcut", (cal)localObject);
    localCag.a((cal)localObject);
    dho.b(loggerName, new String[] { "Bookmark ", paramBookmarkProfile.bookmarkName, " is added to Container" });
    if ((ControlApplication.b().w().evaluateFeature("kindleFireCheck")) && (ControlApplication.b().a() != bqr.a)) {
      dho.a(loggerName, new String[] { "Cannot enforce bookmark settings in kindle fire device" });
    }
    label212:
    do
    {
      return;
      if (!cod.a()) {
        break;
      }
      nv.a(ControlApplication.b()).a(new Intent("KIOSK_HOME_SCREEN_REFRESH_ACTION"));
      if (!paramBookmarkProfile.showOnHomepage) {
        break label302;
      }
      if (paramBoolean) {
        break label304;
      }
    } while (shortcutExists(ControlApplication.b(), paramBookmarkProfile.bookmarkName, paramBookmarkProfile.bookmarkURL));
    for (;;)
    {
      deleteLauncherProfile(paramBookmarkProfile);
      paramIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      ControlApplication.b().sendBroadcast(paramIntent);
      return;
      localObject = new Intent("com.fiberlink.maas360.android.control.samsung.kiosk.KIOSK_BOOKMARK_REFRESH");
      ((Intent)localObject).setComponent(new ComponentName(cqv.f, "com.fiberlink.maas360.android.kiosk.intenthandlers.KioskIntentHandler"));
      dfk.a(ControlApplication.b(), (Intent)localObject);
      break label212;
      label302:
      break;
      label304:
      localObject = ControlApplication.b().p().a();
      if (localObject != null) {
        ((brv)localObject).d("WEBSHORCUTS_AUTHORITY");
      }
    }
  }
  
  public void evaluateAndSetBookmarkProfiles(final boolean paramBoolean)
  {
    if (ControlApplication.b().Z())
    {
      dho.d(loggerName, new String[] { "Cannot enforce bookmark settings since selective wipe enforced" });
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        Map localMap = BookmarkSettings.this.getOldPolicyBookMarks();
        dho.b(BookmarkSettings.loggerName, new String[] { "Bookmark Thread started" });
        Iterator localIterator = BookmarkSettings.this.bookmarkProfiles.keySet().iterator();
        while (localIterator.hasNext())
        {
          Object localObject = (String)localIterator.next();
          BookmarkSettings.BookmarkProfile localBookmarkProfile = (BookmarkSettings.BookmarkProfile)BookmarkSettings.this.bookmarkProfiles.get(localObject);
          if ((localMap != null) && (localBookmarkProfile != null) && (!paramBoolean))
          {
            localObject = (BookmarkSettings.BookmarkProfile)localMap.get(localObject);
            if ((localObject != null) && (((BookmarkSettings.BookmarkProfile)localObject).equals(localBookmarkProfile))) {}
          }
          else if (localBookmarkProfile != null)
          {
            try
            {
              localObject = new Intent();
              ((Intent)localObject).setAction("android.intent.action.VIEW");
              ((Intent)localObject).setData(Uri.parse(localBookmarkProfile.bookmarkURL));
              Intent localIntent = new Intent();
              localIntent.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject);
              localIntent.putExtra("android.intent.extra.shortcut.NAME", localBookmarkProfile.bookmarkName);
              localIntent.putExtra("duplicate", false);
              BookmarkSettings.this.downloadFile(localBookmarkProfile, localIntent, paramBoolean);
              Thread.sleep(1000L);
            }
            catch (Exception localException)
            {
              dho.d(BookmarkSettings.loggerName, localException, new String[] { "Failed setting Bookmark :" + localBookmarkProfile.bookmarkName });
            }
          }
        }
      }
    }).start();
  }
  
  public Map<String, BookmarkSettings.BookmarkProfile> getBookmarkProfiles()
  {
    return this.bookmarkProfiles;
  }
  
  public Map<String, BookmarkSettings.BookmarkProfile> getOldPolicyBookMarks()
  {
    if ((ControlApplication.b().w().evaluateFeature("kindleFireCheck")) && (ControlApplication.b().a() != bqr.a)) {
      dho.a(loggerName, new String[] { "Bookmark settings not supported in kindle fire device" });
    }
    Object localObject;
    do
    {
      do
      {
        return null;
        localObject = ControlApplication.b().A().H();
      } while (localObject == null);
      localObject = ((bvq)localObject).p();
    } while (localObject == null);
    return ((BookmarkSettings)localObject).bookmarkProfiles;
  }
  
  public boolean isBookmarkProfileAvailable()
  {
    if (this.bookmarkProfiles.size() > 0)
    {
      Iterator localIterator = this.bookmarkProfiles.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (((BookmarkSettings.BookmarkProfile)this.bookmarkProfiles.get(str)).showOnHomepage) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void setBookmarkProfiles(Map<String, BookmarkSettings.BookmarkProfile> paramMap)
  {
    this.bookmarkProfiles = paramMap;
  }
  
  /* Error */
  public boolean shortcutExists(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: aload_1
    //   3: invokespecial 597	com/fiberlink/maas360/android/control/Dao/model/devicepolicies/BookmarkSettings:getAuthorityFromPermission	(Landroid/content/Context;)Ljava/lang/String;
    //   6: putfield 53	com/fiberlink/maas360/android/control/Dao/model/devicepolicies/BookmarkSettings:AUTHORITY	Ljava/lang/String;
    //   9: new 170	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   16: ldc_w 599
    //   19: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload_0
    //   23: getfield 53	com/fiberlink/maas360/android/control/Dao/model/devicepolicies/BookmarkSettings:AUTHORITY	Ljava/lang/String;
    //   26: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: ldc_w 601
    //   32: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 217	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   41: astore 5
    //   43: aload_1
    //   44: invokevirtual 605	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   47: astore_1
    //   48: aload_1
    //   49: ifnull +156 -> 205
    //   52: new 170	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   59: ldc_w 607
    //   62: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload_3
    //   66: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 607
    //   72: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore_3
    //   79: aload_1
    //   80: aload 5
    //   82: iconst_2
    //   83: anewarray 124	java/lang/String
    //   86: dup
    //   87: iconst_0
    //   88: ldc_w 609
    //   91: aastore
    //   92: dup
    //   93: iconst_1
    //   94: ldc_w 611
    //   97: aastore
    //   98: ldc_w 613
    //   101: iconst_2
    //   102: anewarray 124	java/lang/String
    //   105: dup
    //   106: iconst_0
    //   107: aload_2
    //   108: aastore
    //   109: dup
    //   110: iconst_1
    //   111: aload_3
    //   112: aastore
    //   113: aconst_null
    //   114: invokevirtual 619	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   117: astore_1
    //   118: aload_1
    //   119: ifnull +80 -> 199
    //   122: aload_1
    //   123: invokeinterface 624 1 0
    //   128: ifle +71 -> 199
    //   131: aload_1
    //   132: invokeinterface 627 1 0
    //   137: istore 4
    //   139: aload_1
    //   140: ifnull +9 -> 149
    //   143: aload_1
    //   144: invokeinterface 628 1 0
    //   149: iload 4
    //   151: ireturn
    //   152: astore_2
    //   153: aload_1
    //   154: ifnull +9 -> 163
    //   157: aload_1
    //   158: invokeinterface 628 1 0
    //   163: aload_2
    //   164: athrow
    //   165: astore_1
    //   166: iconst_0
    //   167: istore 4
    //   169: getstatic 45	com/fiberlink/maas360/android/control/Dao/model/devicepolicies/BookmarkSettings:loggerName	Ljava/lang/String;
    //   172: iconst_2
    //   173: anewarray 124	java/lang/String
    //   176: dup
    //   177: iconst_0
    //   178: ldc_w 630
    //   181: aastore
    //   182: dup
    //   183: iconst_1
    //   184: aload_1
    //   185: invokevirtual 127	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   188: aastore
    //   189: invokestatic 577	dho:d	(Ljava/lang/String;[Ljava/lang/String;)V
    //   192: iload 4
    //   194: ireturn
    //   195: astore_1
    //   196: goto -27 -> 169
    //   199: iconst_0
    //   200: istore 4
    //   202: goto -63 -> 139
    //   205: iconst_0
    //   206: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	this	BookmarkSettings
    //   0	207	1	paramContext	Context
    //   0	207	2	paramString1	String
    //   0	207	3	paramString2	String
    //   137	64	4	bool	boolean
    //   41	40	5	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   122	139	152	finally
    //   0	48	165	java/lang/Exception
    //   52	118	165	java/lang/Exception
    //   157	163	165	java/lang/Exception
    //   163	165	165	java/lang/Exception
    //   143	149	195	java/lang/Exception
  }
}
