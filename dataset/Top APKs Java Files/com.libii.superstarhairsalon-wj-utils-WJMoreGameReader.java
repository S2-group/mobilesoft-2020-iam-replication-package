package wj.utils;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Handler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class WJMoreGameReader
{
  private static final String CST_MOREGAME_TMP_XML = "_moregame.xml";
  private static final String CST_MOREGAME_XML = "moregame.xml";
  private static Vector<MoreGameUpdateCallback> callBackVector;
  private static Handler handler = null;
  private static boolean isLoading = false;
  private static String moreGamePath = "moregame_google/";
  private static Dialog notificationDialog;
  private static Thread thread;
  
  static
  {
    callBackVector = new Vector();
    thread = null;
    notificationDialog = null;
  }
  
  public WJMoreGameReader() {}
  
  public static void addUpdateCallback(MoreGameUpdateCallback paramMoreGameUpdateCallback)
  {
    if (paramMoreGameUpdateCallback != null) {
      callBackVector.add(paramMoreGameUpdateCallback);
    }
  }
  
  private static InputStream getCurrentMoreGameXML(Context paramContext)
  {
    String str = moreGamePath + "moregame.xml";
    File localFile = new File(WJUtils.getWriteablePath() + str);
    if (localFile.exists()) {}
    for (;;)
    {
      try
      {
        paramContext = new FileInputStream(localFile);
        return paramContext;
      }
      catch (FileNotFoundException paramContext)
      {
        return null;
      }
      paramContext = paramContext.getAssets();
      try
      {
        paramContext = paramContext.open(str);
      }
      catch (IOException paramContext) {}
    }
    return null;
  }
  
  public static void hideNotificationDialog()
  {
    if (notificationDialog != null)
    {
      notificationDialog.dismiss();
      notificationDialog = null;
    }
  }
  
  public static boolean isLoadingFromInternet()
  {
    return isLoading;
  }
  
  public static MoreGameData readCurrentMoreGameData(Context paramContext, String paramString)
  {
    WJLog.LOGD("read current MoreGameData from xml");
    InputStream localInputStream = getCurrentMoreGameXML(paramContext);
    paramContext = readXML(paramContext, localInputStream, paramString);
    if (localInputStream != null) {}
    try
    {
      localInputStream.close();
      return paramContext;
    }
    catch (IOException paramString) {}
    return paramContext;
  }
  
  public static MoreGameData readXML(Context paramContext, InputStream paramInputStream, String paramString)
  {
    MoreGameData localMoreGameData = new MoreGameData();
    if (paramInputStream == null) {
      return localMoreGameData;
    }
    try
    {
      SAXParserFactory.newInstance().newSAXParser().parse(paramInputStream, new XMLParser(localMoreGameData));
      localMoreGameData.exceptThisAppId(paramString);
      localMoreGameData.sortAppInfoList(paramContext);
      return localMoreGameData;
    }
    catch (Exception paramInputStream)
    {
      for (;;)
      {
        WJLog.LOGD("WJMoreGame parser xml: " + paramInputStream.getMessage());
      }
    }
  }
  
  /* Error */
  public static MoreGameData readXML(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 133	java/io/FileInputStream
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 208	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   13: astore_1
    //   14: aload_0
    //   15: aload_1
    //   16: aload_2
    //   17: invokestatic 168	wj/utils/WJMoreGameReader:readXML	(Landroid/content/Context;Ljava/io/InputStream;Ljava/lang/String;)Lwj/utils/WJMoreGameReader$MoreGameData;
    //   20: astore_0
    //   21: aload_1
    //   22: ifnull +7 -> 29
    //   25: aload_1
    //   26: invokevirtual 173	java/io/InputStream:close	()V
    //   29: aload_0
    //   30: astore_1
    //   31: aload_1
    //   32: areturn
    //   33: astore_0
    //   34: aload 4
    //   36: astore_0
    //   37: aload_0
    //   38: astore_3
    //   39: new 17	wj/utils/WJMoreGameReader$MoreGameData
    //   42: dup
    //   43: invokespecial 176	wj/utils/WJMoreGameReader$MoreGameData:<init>	()V
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: aload_0
    //   50: ifnull -19 -> 31
    //   53: aload_0
    //   54: invokevirtual 173	java/io/InputStream:close	()V
    //   57: aload_2
    //   58: areturn
    //   59: astore_0
    //   60: aload_2
    //   61: areturn
    //   62: astore_0
    //   63: aload_3
    //   64: ifnull +7 -> 71
    //   67: aload_3
    //   68: invokevirtual 173	java/io/InputStream:close	()V
    //   71: aload_0
    //   72: athrow
    //   73: astore_1
    //   74: goto -3 -> 71
    //   77: astore_1
    //   78: goto -49 -> 29
    //   81: astore_0
    //   82: aload_1
    //   83: astore_3
    //   84: goto -21 -> 63
    //   87: astore_0
    //   88: aload_1
    //   89: astore_0
    //   90: goto -53 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramContext	Context
    //   0	93	1	paramString1	String
    //   0	93	2	paramString2	String
    //   1	83	3	localObject1	Object
    //   3	32	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	14	33	java/io/FileNotFoundException
    //   53	57	59	java/io/IOException
    //   5	14	62	finally
    //   39	47	62	finally
    //   67	71	73	java/io/IOException
    //   25	29	77	java/io/IOException
    //   14	21	81	finally
    //   14	21	87	java/io/FileNotFoundException
  }
  
  public static void removeUpdateCallback(MoreGameUpdateCallback paramMoreGameUpdateCallback)
  {
    callBackVector.remove(paramMoreGameUpdateCallback);
  }
  
  public static void setMoreGameFolder(String paramString)
  {
    if ("".equals(paramString)) {
      return;
    }
    if (!paramString.endsWith("/"))
    {
      moreGamePath = paramString + "/";
      return;
    }
    moreGamePath = paramString;
  }
  
  private static void showNotificationMsg(Context paramContext, final String paramString1, final String paramString2, final String paramString3)
  {
    hideNotificationDialog();
    handler.post(new Runnable()
    {
      public void run()
      {
        WJMoreGameReader.notificationDialog = new AlertDialog.Builder(WJMoreGameReader.this).setTitle(paramString1).setMessage(paramString2).setPositiveButton("Play It Now", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            WJUtils.openURL(this.val$appUrl);
          }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).create();
        WJMoreGameReader.notificationDialog.show();
      }
    });
  }
  
  public static void stopUpdateMoreGameData()
  {
    if ((isLoading) && (thread != null) && (thread.isAlive()))
    {
      WJLog.LOGD("stop Updating moregame thread");
      thread.interrupt();
      isLoading = false;
    }
  }
  
  public static void updateMoreGameData(final Context paramContext, final String paramString1, final boolean paramBoolean, final String paramString2, MoreGameUpdateCallback paramMoreGameUpdateCallback)
  {
    if (isLoading) {
      WJLog.LOGD("moreGame(Update) is busy");
    }
    while (!WJUtils.isInternetConnected()) {
      return;
    }
    isLoading = true;
    if (handler == null) {
      handler = new Handler();
    }
    thread = new Thread()
    {
      public void run()
      {
        if (WJMoreGameReader.this != null) {}
        try
        {
          WJMoreGameReader.this.onBegin();
          int i = WJMoreGameReader.callBackVector.size() - 1;
          for (;;)
          {
            Object localObject1;
            String str2;
            Object localObject2;
            boolean bool1;
            String str1;
            boolean bool2;
            if (i < 0)
            {
              localObject1 = null;
              str2 = WJUtils.getWriteablePath() + WJMoreGameReader.moreGamePath;
              localObject2 = new File(str2);
              if (!((File)localObject2).exists()) {
                ((File)localObject2).mkdirs();
              }
              bool1 = true;
              str1 = str2 + "_moregame.xml";
              if (WJUtils.downloadFileFromInternet(paramString1, str1)) {
                break label210;
              }
              bool2 = false;
              WJMoreGameReader.isLoading = false;
              if ((!WJMoreGameReader.thread.isInterrupted()) && (WJMoreGameReader.this == null)) {}
            }
            try
            {
              WJMoreGameReader.this.onComplete(bool2, (WJMoreGameReader.MoreGameData)localObject1);
              i = WJMoreGameReader.callBackVector.size() - 1;
              if (i < 0)
              {
                WJLog.LOGD("update MoreGame data complete: " + bool2);
                return;
              }
            }
            catch (Exception localException4)
            {
              try
              {
                for (;;)
                {
                  ((WJMoreGameReader.MoreGameUpdateCallback)WJMoreGameReader.callBackVector.get(i)).onBegin();
                  i -= 1;
                  break;
                  label210:
                  localObject1 = WJMoreGameReader.readXML(paramContext, WJMoreGameReader.getCurrentMoreGameXML(paramContext), paramString2);
                  localObject2 = WJMoreGameReader.readXML(paramContext, str1, paramString2);
                  i = 0;
                  int m = ((WJMoreGameReader.MoreGameData)localObject2).getAppInfoList().size();
                  for (;;)
                  {
                    if (i >= m) {}
                    for (;;)
                    {
                      localObject1 = localObject2;
                      bool2 = bool1;
                      if (!bool1) {
                        break;
                      }
                      localObject1 = new File(WJUtils.getWriteablePath() + WJMoreGameReader.moreGamePath + "moregame.xml");
                      if (((File)localObject1).exists()) {
                        ((File)localObject1).delete();
                      }
                      new File(str1).renameTo((File)localObject1);
                      localObject1 = localObject2;
                      bool2 = bool1;
                      if (!paramBoolean) {
                        break;
                      }
                      localObject1 = localObject2;
                      bool2 = bool1;
                      if (WJMoreGameReader.thread.isInterrupted()) {
                        break;
                      }
                      j = WJUtils.readSharedPreferencesInt("notifNo", 0);
                      k = ((WJMoreGameReader.MoreGameData)localObject2).getNotificationInfoList().size();
                      localObject1 = localObject2;
                      bool2 = bool1;
                      if (k <= 0) {
                        break;
                      }
                      i = j;
                      if (j >= k) {
                        i = 0;
                      }
                      localObject1 = (WJMoreGameReader.NotificationDataInfo)((WJMoreGameReader.MoreGameData)localObject2).getNotificationInfoList().get(i);
                      WJUtils.writeSharedPreferencesInt("notifNo", i + 1);
                      WJMoreGameReader.showNotificationMsg(paramContext, ((WJMoreGameReader.NotificationDataInfo)localObject1).getTitle(), ((WJMoreGameReader.NotificationDataInfo)localObject1).getContent(), ((WJMoreGameReader.NotificationDataInfo)localObject1).getAppUrl());
                      localObject1 = localObject2;
                      bool2 = bool1;
                      break;
                      if (!WJMoreGameReader.thread.isInterrupted()) {
                        break label486;
                      }
                      bool1 = false;
                    }
                    label486:
                    int k = 1;
                    WJMoreGameReader.AppDataInfo localAppDataInfo = (WJMoreGameReader.AppDataInfo)((WJMoreGameReader.MoreGameData)localObject2).getAppInfoList().get(i);
                    int j = 0;
                    int n = ((WJMoreGameReader.MoreGameData)localObject1).getAppInfoList().size();
                    for (;;)
                    {
                      if (j >= n) {}
                      for (j = k;; j = 0)
                      {
                        if (j == 0) {
                          break label634;
                        }
                        Object localObject3 = str2 + WJUtils.getUrlFileName(WJMoreGameReader.AppDataInfo.access$3(localAppDataInfo));
                        if (WJUtils.downloadFileFromInternet(WJMoreGameReader.AppDataInfo.access$3(localAppDataInfo), (String)localObject3)) {
                          break label634;
                        }
                        bool1 = false;
                        break;
                        localObject3 = (WJMoreGameReader.AppDataInfo)((WJMoreGameReader.MoreGameData)localObject1).getAppInfoList().get(j);
                        if ((!localAppDataInfo.getAppID().equals(((WJMoreGameReader.AppDataInfo)localObject3).getAppID())) || (localAppDataInfo.getIconVersion() != ((WJMoreGameReader.AppDataInfo)localObject3).getIconVersion())) {
                          break label627;
                        }
                      }
                      label627:
                      j += 1;
                    }
                    label634:
                    i += 1;
                  }
                  try
                  {
                    ((WJMoreGameReader.MoreGameUpdateCallback)WJMoreGameReader.callBackVector.get(i)).onComplete(bool2, (WJMoreGameReader.MoreGameData)localObject1);
                    i -= 1;
                  }
                  catch (Exception localException3)
                  {
                    for (;;) {}
                  }
                }
                localException4 = localException4;
              }
              catch (Exception localException1)
              {
                for (;;) {}
              }
            }
          }
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
      }
    };
    thread.start();
  }
  
  public static class AppDataInfo
    implements Comparable<AppDataInfo>
  {
    private String appDescription;
    private String appID;
    private boolean appInstalled = false;
    private String appName;
    private String appUrl;
    private int buttonDisplay;
    private int cellIndex;
    private Bitmap iconBitmap;
    private String iconUrl;
    private int iconVersion;
    private String launchActivityName;
    private String launchPackageName;
    private String price;
    private int priceResourceId;
    
    public AppDataInfo() {}
    
    /* Error */
    private void readIconBitmap(Context paramContext)
    {
      // Byte code:
      //   0: new 54	java/lang/StringBuilder
      //   3: dup
      //   4: invokestatic 58	wj/utils/WJMoreGameReader:access$0	()Ljava/lang/String;
      //   7: invokestatic 64	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   10: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   13: aload_0
      //   14: invokevirtual 70	wj/utils/WJMoreGameReader$AppDataInfo:getIconUrl	()Ljava/lang/String;
      //   17: invokestatic 76	wj/utils/WJUtils:getUrlFileName	(Ljava/lang/String;)Ljava/lang/String;
      //   20: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   23: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   26: astore_2
      //   27: new 85	java/io/File
      //   30: dup
      //   31: new 54	java/lang/StringBuilder
      //   34: dup
      //   35: invokestatic 88	wj/utils/WJUtils:getWriteablePath	()Ljava/lang/String;
      //   38: invokestatic 64	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   41: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   44: aload_2
      //   45: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   48: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   51: invokespecial 89	java/io/File:<init>	(Ljava/lang/String;)V
      //   54: astore_3
      //   55: aload_3
      //   56: invokevirtual 93	java/io/File:exists	()Z
      //   59: ifeq +46 -> 105
      //   62: new 95	java/io/FileInputStream
      //   65: dup
      //   66: aload_3
      //   67: invokespecial 98	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   70: astore_1
      //   71: aconst_null
      //   72: astore_2
      //   73: aload_1
      //   74: invokestatic 104	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
      //   77: astore_3
      //   78: aload_3
      //   79: ifnull +14 -> 93
      //   82: aload_3
      //   83: bipush 15
      //   85: invokestatic 108	wj/utils/WJUtils:toRoundCorner	(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
      //   88: astore_2
      //   89: aload_3
      //   90: invokevirtual 113	android/graphics/Bitmap:recycle	()V
      //   93: aload_1
      //   94: invokevirtual 118	java/io/InputStream:close	()V
      //   97: aload_0
      //   98: aload_2
      //   99: invokevirtual 122	wj/utils/WJMoreGameReader$AppDataInfo:setIconBitmap	(Landroid/graphics/Bitmap;)V
      //   102: return
      //   103: astore_1
      //   104: return
      //   105: aload_1
      //   106: invokevirtual 128	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
      //   109: astore_1
      //   110: aload_1
      //   111: aload_2
      //   112: invokevirtual 134	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
      //   115: astore_1
      //   116: goto -45 -> 71
      //   119: astore_1
      //   120: return
      //   121: astore_1
      //   122: goto -25 -> 97
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	125	0	this	AppDataInfo
      //   0	125	1	paramContext	Context
      //   26	86	2	localObject1	Object
      //   54	36	3	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   62	71	103	java/io/FileNotFoundException
      //   110	116	119	java/io/IOException
      //   93	97	121	java/io/IOException
    }
    
    public int compareTo(AppDataInfo paramAppDataInfo)
    {
      return new Integer(getCellIndex()).compareTo(Integer.valueOf(paramAppDataInfo.getCellIndex()));
    }
    
    public String getAppDescription()
    {
      return this.appDescription;
    }
    
    public String getAppID()
    {
      return this.appID;
    }
    
    public String getAppName()
    {
      return this.appName;
    }
    
    public String getAppUrl()
    {
      return this.appUrl;
    }
    
    public int getButtonDisplay()
    {
      return this.buttonDisplay;
    }
    
    public int getCellIndex()
    {
      return this.cellIndex;
    }
    
    public Bitmap getIconBitmap()
    {
      return this.iconBitmap;
    }
    
    public String getIconUrl()
    {
      return this.iconUrl;
    }
    
    public int getIconVersion()
    {
      return this.iconVersion;
    }
    
    public String getLaunchActivityName()
    {
      return this.launchActivityName;
    }
    
    public String getLaunchPackageName()
    {
      return this.launchPackageName;
    }
    
    public String getPrice()
    {
      return this.price;
    }
    
    public int getPriceResourceId()
    {
      return this.priceResourceId;
    }
    
    public boolean isAppInstalled()
    {
      return this.appInstalled;
    }
    
    public void setAppDescription(String paramString)
    {
      this.appDescription = paramString;
    }
    
    public void setAppID(String paramString)
    {
      this.appID = paramString;
    }
    
    public void setAppInstalled(boolean paramBoolean)
    {
      this.appInstalled = paramBoolean;
    }
    
    public void setAppName(String paramString)
    {
      this.appName = paramString;
    }
    
    public void setAppUrl(String paramString)
    {
      this.appUrl = paramString;
    }
    
    public void setButtonDisplay(int paramInt)
    {
      this.buttonDisplay = paramInt;
    }
    
    public void setCellIndex(int paramInt)
    {
      this.cellIndex = paramInt;
    }
    
    public void setIconBitmap(Bitmap paramBitmap)
    {
      this.iconBitmap = paramBitmap;
    }
    
    public void setIconUrl(String paramString)
    {
      this.iconUrl = paramString;
    }
    
    public void setIconVersion(int paramInt)
    {
      this.iconVersion = paramInt;
    }
    
    public void setLaunchActivityName(String paramString)
    {
      this.launchActivityName = paramString;
    }
    
    public void setLaunchPackageName(String paramString)
    {
      this.launchPackageName = paramString;
    }
    
    public void setPrice(String paramString)
    {
      this.price = paramString;
      if ("free".equals(paramString))
      {
        this.priceResourceId = 2130837587;
        return;
      }
      this.priceResourceId = 2130837592;
    }
    
    public void setPriceResourceId(int paramInt)
    {
      this.priceResourceId = paramInt;
    }
  }
  
  public static class MoreGameData
  {
    private List<WJMoreGameReader.AppDataInfo> appInfoList = new ArrayList();
    private List<WJMoreGameReader.NotificationDataInfo> notificationInfoList = new ArrayList();
    
    public MoreGameData() {}
    
    private void exceptThisAppId(String paramString)
    {
      int i = this.appInfoList.size() - 1;
      if (i < 0) {
        i = this.notificationInfoList.size() - 1;
      }
      for (;;)
      {
        if (i < 0)
        {
          return;
          if (paramString.equals(((WJMoreGameReader.AppDataInfo)this.appInfoList.get(i)).getAppID())) {
            this.appInfoList.remove(i);
          }
          i -= 1;
          break;
        }
        if (paramString.equals(((WJMoreGameReader.NotificationDataInfo)this.notificationInfoList.get(i)).getAppID())) {
          this.notificationInfoList.remove(i);
        }
        i -= 1;
      }
    }
    
    private void sortAppInfoList(Context paramContext)
    {
      for (;;)
      {
        int i;
        try
        {
          paramContext = paramContext.getPackageManager().getInstalledPackages(0);
          i = 0;
          int k = this.appInfoList.size();
          if (i < k) {
            continue;
          }
        }
        catch (Exception paramContext)
        {
          WJMoreGameReader.AppDataInfo localAppDataInfo;
          WJLog.LOGD("moregame check installed app:" + paramContext.getMessage());
          continue;
        }
        Collections.sort(this.appInfoList);
        return;
        localAppDataInfo = (WJMoreGameReader.AppDataInfo)this.appInfoList.get(i);
        int j = 0;
        int m = paramContext.size();
        break label171;
        if (((PackageInfo)paramContext.get(j)).packageName.equals(localAppDataInfo.getLaunchPackageName()))
        {
          localAppDataInfo.setCellIndex(localAppDataInfo.getCellIndex() + 10000);
          localAppDataInfo.setAppInstalled(true);
          localAppDataInfo.setPriceResourceId(2130837591);
          WJLog.LOGD("moregame installed:" + localAppDataInfo.appName);
        }
        j += 1;
        label171:
        if (j >= m) {
          i += 1;
        }
      }
    }
    
    public void clearAndRelease()
    {
      int i = 0;
      int j = getAppInfoList().size();
      for (;;)
      {
        if (i >= j)
        {
          getAppInfoList().clear();
          getNotificationInfoList().clear();
          return;
        }
        Bitmap localBitmap = ((WJMoreGameReader.AppDataInfo)getAppInfoList().get(i)).getIconBitmap();
        if (localBitmap != null) {
          localBitmap.recycle();
        }
        i += 1;
      }
    }
    
    public void exceptButtonNotDisplay()
    {
      int i = this.appInfoList.size() - 1;
      for (;;)
      {
        if (i < 0) {
          return;
        }
        if (((WJMoreGameReader.AppDataInfo)this.appInfoList.get(i)).getButtonDisplay() != 1) {
          this.appInfoList.remove(i);
        }
        i -= 1;
      }
    }
    
    public List<WJMoreGameReader.AppDataInfo> getAppInfoList()
    {
      return this.appInfoList;
    }
    
    public List<WJMoreGameReader.NotificationDataInfo> getNotificationInfoList()
    {
      return this.notificationInfoList;
    }
    
    public void readAppInfoListIconBitmap(Context paramContext)
    {
      Iterator localIterator = this.appInfoList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        ((WJMoreGameReader.AppDataInfo)localIterator.next()).readIconBitmap(paramContext);
      }
    }
  }
  
  public static abstract interface MoreGameUpdateCallback
  {
    public abstract void onBegin();
    
    public abstract void onComplete(boolean paramBoolean, WJMoreGameReader.MoreGameData paramMoreGameData);
  }
  
  public static class NotificationDataInfo
  {
    private String appID;
    private String appUrl;
    private String content;
    private String title;
    
    public NotificationDataInfo() {}
    
    public String getAppID()
    {
      return this.appID;
    }
    
    public String getAppUrl()
    {
      return this.appUrl;
    }
    
    public String getContent()
    {
      return this.content;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public void setAppID(String paramString)
    {
      this.appID = paramString;
    }
    
    public void setAppUrl(String paramString)
    {
      this.appUrl = paramString;
    }
    
    public void setContent(String paramString)
    {
      this.content = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
  }
  
  private static class XMLParser
    extends DefaultHandler
  {
    private boolean isAppTag;
    private WJMoreGameReader.AppDataInfo lastAppDataInfo;
    private WJMoreGameReader.NotificationDataInfo lastNotifiDataInfo;
    private WJMoreGameReader.MoreGameData moreGameData;
    private StringBuilder sbLastTagText = new StringBuilder();
    
    public XMLParser(WJMoreGameReader.MoreGameData paramMoreGameData)
    {
      this.moreGameData = paramMoreGameData;
    }
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      this.sbLastTagText.append(paramArrayOfChar, paramInt1, paramInt2);
    }
    
    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      paramString1 = this.sbLastTagText.toString();
      if (this.isAppTag) {
        if ("iconUrl".equals(paramString2)) {
          this.lastAppDataInfo.setIconUrl(paramString1);
        }
      }
      do
      {
        do
        {
          do
          {
            return;
            if ("iconVersion".equals(paramString2))
            {
              this.lastAppDataInfo.setIconVersion(Integer.parseInt(paramString1));
              return;
            }
            if ("cellIndex".equals(paramString2))
            {
              this.lastAppDataInfo.setCellIndex(Integer.parseInt(paramString1));
              return;
            }
            if ("buttonDisplay".equals(paramString2))
            {
              this.lastAppDataInfo.setButtonDisplay(Integer.parseInt(paramString1));
              return;
            }
            if ("appName".equals(paramString2))
            {
              this.lastAppDataInfo.setAppName(paramString1);
              return;
            }
            if ("appID".equals(paramString2))
            {
              this.lastAppDataInfo.setAppID(paramString1);
              return;
            }
            if ("appDescription".equals(paramString2))
            {
              this.lastAppDataInfo.setAppDescription(paramString1);
              return;
            }
            if ("price".equals(paramString2))
            {
              this.lastAppDataInfo.setPrice(paramString1);
              return;
            }
            if ("appUrl".equals(paramString2))
            {
              this.lastAppDataInfo.setAppUrl(paramString1);
              return;
            }
          } while (!"launchUrl".equals(paramString2));
          paramString1 = paramString1.split("\\|");
        } while (paramString1.length < 2);
        this.lastAppDataInfo.setLaunchPackageName(paramString1[0]);
        this.lastAppDataInfo.setLaunchActivityName(paramString1[1]);
        return;
        if ("title".equals(paramString2))
        {
          this.lastNotifiDataInfo.setTitle(paramString1);
          return;
        }
        if ("content".equals(paramString2))
        {
          this.lastNotifiDataInfo.setContent(paramString1);
          return;
        }
        if ("appUrl".equals(paramString2))
        {
          this.lastNotifiDataInfo.setAppUrl(paramString1);
          return;
        }
      } while (!"appID".equals(paramString2));
      this.lastNotifiDataInfo.setAppID(paramString1);
    }
    
    public void startDocument()
      throws SAXException
    {}
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      this.sbLastTagText.setLength(0);
      if ("app".equals(paramString2))
      {
        this.isAppTag = true;
        this.lastAppDataInfo = new WJMoreGameReader.AppDataInfo();
        this.moreGameData.getAppInfoList().add(this.lastAppDataInfo);
      }
      while (!"notification".equals(paramString2)) {
        return;
      }
      this.isAppTag = false;
      this.lastNotifiDataInfo = new WJMoreGameReader.NotificationDataInfo();
      this.moreGameData.getNotificationInfoList().add(this.lastNotifiDataInfo);
    }
  }
}
