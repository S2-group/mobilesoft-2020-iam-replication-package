package com.lbe.parallel.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebJavaInterface
{
  Context mContext;
  
  public WebJavaInterface(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  @JavascriptInterface
  public String getInstalledPackages()
  {
    localJSONArray = new JSONArray();
    try
    {
      Iterator localIterator = new PackageManagerWrapper(this.mContext).getInstalledPackages(0).iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((PackageInfo)localIterator.next()).packageName);
      }
      return localJSONArray.toString();
    }
    catch (Exception localException) {}
  }
  
  @JavascriptInterface
  public String getModel()
  {
    return Build.MODEL;
  }
  
  @JavascriptInterface
  public String getPackageInfo()
  {
    localJSONArray = new JSONArray();
    try
    {
      Iterator localIterator = new PackageManagerWrapper(this.mContext).getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("package", localPackageInfo.packageName);
        localJSONObject.put("versionCode", localPackageInfo.versionCode);
        localJSONArray.put(localJSONObject);
      }
      return localJSONArray.toString();
    }
    catch (Exception localException) {}
  }
  
  /* Error */
  @JavascriptInterface
  public String getSupportAbis()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 85	java/util/HashSet
    //   5: dup
    //   6: invokespecial 86	java/util/HashSet:<init>	()V
    //   9: astore 4
    //   11: aload 4
    //   13: getstatic 89	android/os/Build:CPU_ABI	Ljava/lang/String;
    //   16: invokeinterface 95 2 0
    //   21: pop
    //   22: aload 4
    //   24: getstatic 98	android/os/Build:CPU_ABI2	Ljava/lang/String;
    //   27: invokeinterface 95 2 0
    //   32: pop
    //   33: getstatic 103	android/os/Build$VERSION:SDK_INT	I
    //   36: bipush 21
    //   38: if_icmplt +110 -> 148
    //   41: getstatic 107	android/os/Build:SUPPORTED_ABIS	[Ljava/lang/String;
    //   44: astore 5
    //   46: aload 5
    //   48: arraylength
    //   49: istore_3
    //   50: iconst_0
    //   51: istore_1
    //   52: iload_1
    //   53: iload_3
    //   54: if_icmpge +22 -> 76
    //   57: aload 4
    //   59: aload 5
    //   61: iload_1
    //   62: aaload
    //   63: invokeinterface 95 2 0
    //   68: pop
    //   69: iload_1
    //   70: iconst_1
    //   71: iadd
    //   72: istore_1
    //   73: goto -21 -> 52
    //   76: getstatic 110	android/os/Build:SUPPORTED_32_BIT_ABIS	[Ljava/lang/String;
    //   79: astore 5
    //   81: aload 5
    //   83: arraylength
    //   84: istore_3
    //   85: iconst_0
    //   86: istore_1
    //   87: iload_1
    //   88: iload_3
    //   89: if_icmpge +22 -> 111
    //   92: aload 4
    //   94: aload 5
    //   96: iload_1
    //   97: aaload
    //   98: invokeinterface 95 2 0
    //   103: pop
    //   104: iload_1
    //   105: iconst_1
    //   106: iadd
    //   107: istore_1
    //   108: goto -21 -> 87
    //   111: getstatic 113	android/os/Build:SUPPORTED_64_BIT_ABIS	[Ljava/lang/String;
    //   114: astore 5
    //   116: aload 5
    //   118: arraylength
    //   119: istore_3
    //   120: iload_2
    //   121: istore_1
    //   122: iload_1
    //   123: iload_3
    //   124: if_icmpge +24 -> 148
    //   127: aload 4
    //   129: aload 5
    //   131: iload_1
    //   132: aaload
    //   133: invokeinterface 95 2 0
    //   138: pop
    //   139: iload_1
    //   140: iconst_1
    //   141: iadd
    //   142: istore_1
    //   143: goto -21 -> 122
    //   146: astore 5
    //   148: new 21	org/json/JSONArray
    //   151: dup
    //   152: invokespecial 22	org/json/JSONArray:<init>	()V
    //   155: astore 5
    //   157: aload 4
    //   159: invokeinterface 114 1 0
    //   164: astore 4
    //   166: aload 4
    //   168: invokeinterface 41 1 0
    //   173: ifeq +22 -> 195
    //   176: aload 5
    //   178: aload 4
    //   180: invokeinterface 45 1 0
    //   185: checkcast 116	java/lang/String
    //   188: invokevirtual 55	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   191: pop
    //   192: goto -26 -> 166
    //   195: aload 5
    //   197: invokevirtual 58	org/json/JSONArray:toString	()Ljava/lang/String;
    //   200: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	this	WebJavaInterface
    //   51	92	1	i	int
    //   1	120	2	j	int
    //   49	76	3	k	int
    //   9	170	4	localObject	Object
    //   44	86	5	arrayOfString	String[]
    //   146	1	5	localException	Exception
    //   155	41	5	localJSONArray	JSONArray
    // Exception table:
    //   from	to	target	type
    //   11	50	146	java/lang/Exception
    //   57	69	146	java/lang/Exception
    //   76	85	146	java/lang/Exception
    //   92	104	146	java/lang/Exception
    //   111	120	146	java/lang/Exception
    //   127	139	146	java/lang/Exception
  }
  
  @JavascriptInterface
  public int getVersionCode()
  {
    try
    {
      int i = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  @JavascriptInterface
  public String getVersionName()
  {
    try
    {
      String str = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  @JavascriptInterface
  public boolean isExist(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = Intent.parseUri(paramString, 1);
        paramString = this.mContext.getPackageManager().resolveActivity(paramString, 0);
        if (paramString != null) {
          return true;
        }
      }
      catch (URISyntaxException paramString) {}
    }
    return false;
  }
  
  @JavascriptInterface
  public void openIntent(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return;
      try
      {
        paramString = new JSONArray(paramString);
        int i = 0;
        for (;;)
        {
          int j = paramString.length();
          if (i >= j) {
            break;
          }
          try
          {
            Object localObject2 = (JSONObject)paramString.get(i);
            Object localObject1 = (String)((JSONObject)localObject2).get("intentUri");
            localObject2 = (String)((JSONObject)localObject2).get("toast");
            localObject1 = Intent.parseUri((String)localObject1, 1);
            this.mContext.startActivity((Intent)localObject1);
            if (TextUtils.isEmpty((CharSequence)localObject2)) {
              break;
            }
            Toast.makeText(this.mContext, (CharSequence)localObject2, 1).show();
            return;
          }
          catch (Exception localException)
          {
            i += 1;
          }
        }
        return;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}
