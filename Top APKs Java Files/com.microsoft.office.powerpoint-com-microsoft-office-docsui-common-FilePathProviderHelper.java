package com.microsoft.office.docsui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import com.microsoft.intune.mam.client.content.MAMContentResolverManagement;
import com.microsoft.intune.mam.client.content.pm.MAMPackageManagement;
import com.microsoft.office.apphost.ap;
import com.microsoft.office.apphost.ba;
import com.microsoft.office.apphost.j;
import com.microsoft.office.loggingapi.Logging;
import com.microsoft.office.loggingapi.Severity;
import com.microsoft.office.loggingapi.StructuredBoolean;
import com.microsoft.office.loggingapi.StructuredLong;
import com.microsoft.office.loggingapi.StructuredObject;
import com.microsoft.office.loggingapi.StructuredString;
import com.microsoft.office.mso.docs.appdocs.AppDocsDocumentOperationProxy;
import com.microsoft.office.mso.docs.appdocs.ApplicationDocumentsEventsNotifier;
import com.microsoft.office.mso.docs.appdocs.IApplicationDocumentsEventListener;
import com.microsoft.office.mso.docs.appdocsfm.DocumentOperationEventType;
import com.microsoft.office.mso.docs.appdocsfm.DocumentOperationType;
import com.microsoft.office.mso.docs.appdocsfm.InitializationReason;
import com.microsoft.office.msohttp.DocsTestHelper;
import com.microsoft.office.officehub.util.OHubUtil;
import com.microsoft.office.plat.logging.Trace;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePathProviderHelper
  implements IApplicationDocumentsEventListener
{
  public static final String DOCUMENT_FOLDER = "Document Folder";
  public static final String DOWNLOAD_FOLDER = "Download Folder";
  private static final String LOG_TAG = "FilePathProviderHelper";
  private static final long REFRESH_TIMEOUT = 300000L;
  private static List<ApplicationInfo> sInstalledPackages;
  private static Long sLastUpdatedTime;
  private static HashMap<String, String> sWhitelistedApps;
  private boolean mIsCallbackRegistered = false;
  
  static
  {
    if (!FilePathProviderHelper.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      sLastUpdatedTime = null;
      sWhitelistedApps = new HashMap();
      sWhitelistedApps.put("/Download/Line/", "Line");
      sWhitelistedApps.put("/SHAREit/", "ShareIt");
      sWhitelistedApps.put("/Tencent/MicroMsg/", "Wechat");
      sWhitelistedApps.put("/Tencent/QQfile_recv/", "Tencent QQ");
      sWhitelistedApps.put("/WhatsApp/", "Whatsapp");
      return;
    }
  }
  
  private FilePathProviderHelper() {}
  
  public static FilePathProviderHelper GetInstance()
  {
    return FilePathProviderHelper.SingletonHolder.sInstance;
  }
  
  private String extractTargetPackageName(String paramString, Uri paramUri1, Uri paramUri2, Context paramContext)
  {
    Iterator localIterator = paramUri1.getPathSegments().iterator();
    paramUri2 = paramUri2.getPathSegments().iterator();
    while ((localIterator.hasNext()) && (paramUri2.hasNext()))
    {
      String str = (String)localIterator.next();
      paramUri1 = (String)paramUri2.next();
      if (!str.equalsIgnoreCase(paramUri1)) {
        if (str.toLowerCase().startsWith(paramString.toLowerCase()))
        {
          paramString = getInstalledPackages(paramContext).iterator();
          while (paramString.hasNext())
          {
            paramUri2 = (ApplicationInfo)paramString.next();
            if ((paramUri2 != null) && (paramUri2.packageName.equalsIgnoreCase(paramUri1))) {
              return paramUri1;
            }
          }
        }
      }
    }
    return null;
  }
  
  private List<ApplicationInfo> getInstalledPackages(Context paramContext)
  {
    Long localLong = Long.valueOf(Calendar.getInstance().getTimeInMillis());
    if ((sLastUpdatedTime == null) || (localLong.longValue() - sLastUpdatedTime.longValue() > 300000L))
    {
      sInstalledPackages = MAMPackageManagement.getInstalledApplications(paramContext.getPackageManager(), 128);
      sLastUpdatedTime = localLong;
    }
    return sInstalledPackages;
  }
  
  private long getLocalFileSize(String paramString)
  {
    long l2 = -1L;
    long l1 = l2;
    if (paramString != null)
    {
      paramString = Uri.parse(paramString);
      l1 = l2;
      if (paramString != null)
      {
        l1 = l2;
        if (!OHubUtil.isNullOrEmptyOrWhitespace(paramString.getPath()))
        {
          paramString = new File(paramString.getPath());
          l1 = l2;
          if (paramString.exists()) {
            l1 = paramString.length();
          }
        }
      }
    }
    return l1;
  }
  
  private String getProviderAppInfo(Uri paramUri, String paramString)
  {
    if ((paramUri == null) || (OHubUtil.isNullOrEmptyOrWhitespace(paramString)))
    {
      Severity localSeverity = Severity.Info;
      if (paramUri == null) {}
      for (boolean bool = true;; bool = false)
      {
        Logging.a(18621910L, 1135, localSeverity, "ProviderAppInfoError", new StructuredObject[] { new StructuredBoolean("IsIntentUriNull", bool), new StructuredBoolean("IsLocalFilePathEmpty", OHubUtil.isNullOrEmptyOrWhitespace(paramString)) });
        return "";
      }
    }
    paramString = paramUri.getScheme();
    if (("file".equals(paramString)) || ("content".equals(paramString)))
    {
      if ("file".equals(paramString)) {
        return getProviderFromFileUri(paramUri, ba.c());
      }
      return getProviderFromContentUri(paramUri);
    }
    return "";
  }
  
  private String getProviderFromContentUri(Uri paramUri)
  {
    String str2 = paramUri.getAuthority();
    String str1 = str2;
    if (OHubUtil.isNullOrEmptyOrWhitespace(str2)) {
      str1 = matchPattern("content:/+(.*?)/", paramUri.toString());
    }
    Trace.d("FilePathProviderHelper", "FilePathProviderHelper.getProviderFromContentUri returns provider = " + str1);
    return str1;
  }
  
  private String getProviderFromFileUri(Uri paramUri, Context paramContext)
  {
    Object localObject2 = paramUri.toString();
    Object localObject1 = null;
    paramUri = null;
    String str;
    if (!OHubUtil.isNullOrEmptyOrWhitespace((String)localObject2))
    {
      localObject2 = Uri.parse(Uri.decode((String)localObject2));
      str = paramContext.getPackageName();
      File localFile = paramContext.getExternalCacheDir();
      localObject1 = paramUri;
      if (localFile != null) {
        localObject1 = extractTargetPackageName(str, Uri.fromFile(localFile), (Uri)localObject2, paramContext);
      }
      paramUri = (Uri)localObject1;
      if (OHubUtil.isNullOrEmptyOrWhitespace((String)localObject1))
      {
        localFile = paramContext.getCacheDir();
        paramUri = (Uri)localObject1;
        if (localFile != null) {
          paramUri = extractTargetPackageName(str, Uri.fromFile(localFile), (Uri)localObject2, paramContext);
        }
      }
      localObject1 = paramUri;
      if (OHubUtil.isNullOrEmptyOrWhitespace(paramUri))
      {
        localObject1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        paramContext = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        localObject1 = ((File)localObject1).getAbsolutePath();
        str = paramContext.getAbsolutePath();
        paramContext = ((Uri)localObject2).getPath();
        if (!paramContext.startsWith((String)localObject1)) {
          break label230;
        }
        paramUri = "Download Folder";
      }
    }
    label230:
    label250:
    for (;;)
    {
      localObject1 = sWhitelistedApps.keySet().iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (String)((Iterator)localObject1).next();
      } while (!paramContext.contains((CharSequence)localObject2));
      for (localObject1 = (String)sWhitelistedApps.get(localObject2);; localObject1 = paramUri)
      {
        Trace.d("FilePathProviderHelper", "FilePathProviderHelper.getProviderFromFileUri returns providerName = " + (String)localObject1);
        return localObject1;
        if (!paramContext.startsWith(str)) {
          break label250;
        }
        paramUri = "Document Folder";
        break;
      }
    }
  }
  
  private boolean isContentProviderFileEmpty(String paramString)
    throws IOException
  {
    paramString = Uri.parse(paramString);
    assert ("content".equals(paramString.getScheme()));
    paramString = MAMContentResolverManagement.openInputStream(ba.c().getContentResolver(), paramString);
    if (paramString.read() == -1) {}
    for (boolean bool = true;; bool = false)
    {
      paramString.close();
      return bool;
    }
  }
  
  private String matchPattern(String paramString1, String paramString2)
  {
    Object localObject = null;
    paramString2 = Pattern.compile(paramString1, 2).matcher(paramString2);
    paramString1 = localObject;
    if (paramString2.find()) {
      paramString1 = paramString2.group(1);
    }
    return paramString1;
  }
  
  public String GetLoggingId()
  {
    return "FilePathProviderHelper";
  }
  
  public void OnOperationEvent(DocumentOperationEventType paramDocumentOperationEventType, AppDocsDocumentOperationProxy paramAppDocsDocumentOperationProxy)
  {
    boolean bool = true;
    Object localObject1 = paramAppDocsDocumentOperationProxy.c();
    Object localObject3 = paramAppDocsDocumentOperationProxy.b();
    String str2 = paramAppDocsDocumentOperationProxy.d();
    Trace.i("FilePathProviderHelper", "Document operation type : " + localObject1 + " Document event type : " + paramDocumentOperationEventType + " Initialization Reason :" + localObject3);
    Object localObject2;
    if ((paramAppDocsDocumentOperationProxy.c() == DocumentOperationType.Open) && (paramDocumentOperationEventType == DocumentOperationEventType.IDocumentInitialized) && ((localObject3 == InitializationReason.OpenFromShell) || (localObject3 == InitializationReason.OpenFromGenericThirdParty)))
    {
      String str1 = "NotSet";
      localObject2 = "NotSet";
      paramDocumentOperationEventType = (DocumentOperationEventType)localObject2;
      localObject1 = str1;
      if (localObject3 == InitializationReason.OpenFromShell)
      {
        Intent localIntent = ba.c().getIntent();
        if (ba.b().getIntentExtras() == null) {
          break label415;
        }
        localObject3 = ba.b().getIntentExtras().getString(j.g);
        paramDocumentOperationEventType = (DocumentOperationEventType)localObject2;
        localObject1 = str1;
        if (localIntent != null)
        {
          paramDocumentOperationEventType = (DocumentOperationEventType)localObject2;
          localObject1 = str1;
          if (!OHubUtil.isNullOrEmptyOrWhitespace((String)localObject3))
          {
            localObject1 = localIntent.getData();
            paramDocumentOperationEventType = (DocumentOperationEventType)localObject2;
            if (localObject1 != null) {
              paramDocumentOperationEventType = ((Uri)localObject1).getScheme();
            }
            localObject1 = getProviderAppInfo((Uri)localObject1, (String)localObject3);
          }
        }
      }
      if (OHubUtil.isNullOrEmptyOrWhitespace(str2)) {
        break label475;
      }
      localObject2 = Uri.parse(str2);
      if ((localObject2 == null) || (!"content".equals(((Uri)localObject2).getScheme()))) {
        break label441;
      }
    }
    for (;;)
    {
      try
      {
        bool = isContentProviderFileEmpty(str2);
        l1 = -1L;
      }
      catch (IOException localIOException)
      {
        long l2;
        label415:
        Trace.e("FilePathProviderHelper", "Error checking file for empty");
        l1 = -1L;
        bool = true;
        continue;
      }
      l2 = l1;
      if (l1 < 0L) {
        l2 = 2147483647L;
      }
      paramAppDocsDocumentOperationProxy = OHubUtil.getExtension(paramAppDocsDocumentOperationProxy.e());
      localObject2 = new ArrayList();
      ((ArrayList)localObject2).add(new StructuredString("provider", (String)localObject1));
      ((ArrayList)localObject2).add(new StructuredString("scheme", paramDocumentOperationEventType));
      ((ArrayList)localObject2).add(new StructuredBoolean("IsEmpty", bool));
      ((ArrayList)localObject2).add(new StructuredLong("FileSize", l2));
      ((ArrayList)localObject2).add(new StructuredString("extension", paramAppDocsDocumentOperationProxy));
      Logging.a(18425033L, 1135, Severity.Info, "ProviderApp", (StructuredObject[])((ArrayList)localObject2).toArray(new StructuredObject[((ArrayList)localObject2).size()]));
      return;
      localObject3 = null;
      break;
      label441:
      long l1 = getLocalFileSize(str2);
      if (l1 != -1L)
      {
        if (l1 > 0L) {
          bool = false;
        }
      }
      else
      {
        bool = true;
        continue;
        label475:
        l1 = -1L;
        bool = true;
      }
    }
  }
  
  public void ensureCallbacksRegistered()
  {
    if ((!DocsTestHelper.IsTestMode()) && (!this.mIsCallbackRegistered))
    {
      ApplicationDocumentsEventsNotifier.a().a(this);
      this.mIsCallbackRegistered = true;
    }
  }
}
