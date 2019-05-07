package com.cleanmaster.security.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class a
{
  private static String[][] a;
  
  static
  {
    String[] arrayOfString1 = { ".apk", "application/vnd.android.package-archive" };
    String[] arrayOfString2 = { ".bin", "application/octet-stream" };
    String[] arrayOfString3 = { ".bmp", "image/bmp" };
    String[] arrayOfString4 = { ".c", "text/plain" };
    String[] arrayOfString5 = { ".xls", "application/vnd.ms-excel" };
    String[] arrayOfString6 = { ".exe", "application/octet-stream" };
    String[] arrayOfString7 = { ".htm", "text/html" };
    String[] arrayOfString8 = { ".jar", "application/java-archive" };
    String[] arrayOfString9 = { ".java", "text/plain" };
    String[] arrayOfString10 = { ".jpg", "image/jpeg" };
    String[] arrayOfString11 = { ".mov", "video/quicktime" };
    String[] arrayOfString12 = { ".mpc", "application/vnd.mpohun.certificate" };
    String[] arrayOfString13 = { ".mpeg", "video/mpeg" };
    String[] arrayOfString14 = { ".mpg", "video/mpeg" };
    String[] arrayOfString15 = { ".msg", "application/vnd.ms-outlook" };
    String[] arrayOfString16 = { ".pps", "application/vnd.ms-powerpoint" };
    String[] arrayOfString17 = { ".rc", "text/plain" };
    String[] arrayOfString18 = { ".rmvb", "audio/x-pn-realaudio" };
    String[] arrayOfString19 = { ".wps", "application/vnd.ms-works" };
    String[] arrayOfString20 = { ".z", "application/x-compress" };
    String[] arrayOfString21 = { "", "*/*" };
    a = new String[][] { { ".3gp", "video/3gpp" }, arrayOfString1, { ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, arrayOfString2, arrayOfString3, arrayOfString4, { ".class", "application/octet-stream" }, { ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" }, { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" }, arrayOfString5, { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" }, arrayOfString6, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" }, { ".h", "text/plain" }, arrayOfString7, { ".html", "text/html" }, arrayOfString8, arrayOfString9, { ".jpeg", "image/jpeg" }, arrayOfString10, { ".js", "application/x-javascript" }, { ".log", "text/plain" }, { ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" }, { ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" }, arrayOfString11, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mp3" }, { ".mp4", "video/mp4" }, arrayOfString12, { ".mpe", "video/mpeg" }, arrayOfString13, arrayOfString14, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" }, arrayOfString15, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" }, { ".png", "image/png" }, arrayOfString16, { ".ppt", "application/vnd.ms-powerpoint" }, { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" }, { ".prop", "text/plain" }, arrayOfString17, arrayOfString18, { ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" }, { ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, arrayOfString19, { ".xml", "text/plain" }, arrayOfString20, { ".zip", "application/x-zip-compressed" }, arrayOfString21 };
  }
  
  public static String a(File paramFile)
  {
    String str1 = "*/*";
    paramFile = paramFile.getName();
    int i = paramFile.lastIndexOf(".");
    Object localObject;
    if (i < 0) {
      localObject = str1;
    }
    String str2;
    do
    {
      return localObject;
      str2 = paramFile.substring(i, paramFile.length()).toLowerCase();
      localObject = str1;
    } while (str2 == "");
    i = 0;
    paramFile = str1;
    for (;;)
    {
      localObject = paramFile;
      if (i >= 66) {
        break;
      }
      if (str2.equals(a[i][0])) {
        paramFile = a[i][1];
      }
      i += 1;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        if (paramString.equals(((PackageInfo)paramContext.get(i)).packageName)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
}
