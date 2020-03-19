package com.xyan.skincommon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.List;

public class LauncherConfig
{
  public static final String KEY_LAUNCHER = "launcher_skin_promote_launcher_pkg";
  public static final String KEY_SKIN_INDIVIDUAL_MAP = "launcher_skin_individual_map";
  private static final String REMOTE_TAB = "remote_config";
  private static final String TAG = "RC_LOG";
  private static final LauncherConfig sLauncherConfig = new LauncherConfig();
  private String mRemoteLauncher;
  private SharedPreferences mSharedPreferences;
  
  public LauncherConfig() {}
  
  public static LauncherConfig getInstance()
  {
    return sLauncherConfig;
  }
  
  public String getInstallLauncher(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {}
    Object localObject;
    do
    {
      while (!paramContext.hasNext())
      {
        do
        {
          return null;
          localObject = new Intent("android.intent.action.MAIN", null);
          ((Intent)localObject).addCategory("android.intent.category.HOME");
          ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
          ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
          ((Intent)localObject).addCategory("android.intent.category.MONKEY");
          paramContext = paramContext.getInstalledPackages(8192);
        } while ((paramContext == null) || (paramContext.isEmpty()));
        paramContext = paramContext.iterator();
      }
      localObject = (PackageInfo)paramContext.next();
    } while ((localObject == null) || (TextUtils.isEmpty(((PackageInfo)localObject).packageName)) || (!((PackageInfo)localObject).packageName.startsWith("com.amber.launcher")) || (((PackageInfo)localObject).packageName.startsWith("com.amber.launcher.skin.")));
    return ((PackageInfo)localObject).packageName;
  }
  
  public String getLauncher(Context paramContext)
  {
    return this.mSharedPreferences.getString("launcher_skin_promote_launcher_pkg", "");
  }
  
  public String getRemoteLauncher(Context paramContext)
  {
    return getRemoteLauncher(paramContext, false, false);
  }
  
  public String getRemoteLauncher(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && (TextUtils.isEmpty(this.mRemoteLauncher))) {}
    for (String str = "com.amber.launcher";; str = this.mRemoteLauncher)
    {
      setRemoteLauncher(paramContext, str);
      return str;
    }
  }
  
  public void init(final Context paramContext)
  {
    Log.d("RC_LOG", "开始初始化");
    this.mSharedPreferences = paramContext.getSharedPreferences("remote_config", 0);
    String str1 = this.mSharedPreferences.getString("launcher_skin_promote_launcher_pkg", "");
    String str2 = this.mSharedPreferences.getString("launcher_skin_individual_map", "");
    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)))
    {
      Log.d("RC_LOG", "Fetch");
      FirebaseRemoteConfig.getInstance().fetch().addOnCompleteListener(new OnCompleteListener()
      {
        /* Error */
        @SuppressLint({"ApplySharedPref"})
        public void onComplete(@android.support.annotation.NonNull Task<Void> arg1)
        {
          // Byte code:
          //   0: ldc 36
          //   2: new 38	java/lang/StringBuilder
          //   5: dup
          //   6: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   9: ldc 41
          //   11: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   14: aload_1
          //   15: invokevirtual 51	com/google/android/gms/tasks/Task:isSuccessful	()Z
          //   18: invokevirtual 54	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
          //   21: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   24: invokestatic 64	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   27: pop
          //   28: aload_1
          //   29: invokevirtual 51	com/google/android/gms/tasks/Task:isSuccessful	()Z
          //   32: ifeq +147 -> 179
          //   35: invokestatic 70	com/google/firebase/remoteconfig/FirebaseRemoteConfig:getInstance	()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;
          //   38: invokevirtual 73	com/google/firebase/remoteconfig/FirebaseRemoteConfig:activateFetched	()Z
          //   41: pop
          //   42: invokestatic 70	com/google/firebase/remoteconfig/FirebaseRemoteConfig:getInstance	()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;
          //   45: ldc 75
          //   47: invokevirtual 79	com/google/firebase/remoteconfig/FirebaseRemoteConfig:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   50: astore_3
          //   51: ldc 36
          //   53: new 38	java/lang/StringBuilder
          //   56: dup
          //   57: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   60: ldc 81
          //   62: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   65: aload_3
          //   66: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   69: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   72: invokestatic 64	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   75: pop
          //   76: invokestatic 70	com/google/firebase/remoteconfig/FirebaseRemoteConfig:getInstance	()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;
          //   79: ldc 83
          //   81: invokevirtual 79	com/google/firebase/remoteconfig/FirebaseRemoteConfig:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   84: astore_2
          //   85: ldc 36
          //   87: new 38	java/lang/StringBuilder
          //   90: dup
          //   91: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   94: ldc 85
          //   96: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   99: aload_2
          //   100: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   103: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   106: invokestatic 64	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   109: pop
          //   110: ldc 87
          //   112: astore_1
          //   113: new 89	org/json/JSONObject
          //   116: dup
          //   117: aload_2
          //   118: invokespecial 92	org/json/JSONObject:<init>	(Ljava/lang/String;)V
          //   121: aload_0
          //   122: getfield 22	com/xyan/skincommon/LauncherConfig$1:val$context	Landroid/content/Context;
          //   125: invokevirtual 97	android/content/Context:getPackageName	()Ljava/lang/String;
          //   128: invokevirtual 100	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
          //   131: astore_2
          //   132: aload_2
          //   133: astore_1
          //   134: aload_3
          //   135: invokestatic 106	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   138: ifne +41 -> 179
          //   141: aload_1
          //   142: aload_3
          //   143: invokestatic 110	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
          //   146: ifne +33 -> 179
          //   149: invokestatic 114	com/xyan/skincommon/LauncherConfig:access$000	()Lcom/xyan/skincommon/LauncherConfig;
          //   152: astore_1
          //   153: aload_1
          //   154: monitorenter
          //   155: aload_0
          //   156: getfield 20	com/xyan/skincommon/LauncherConfig$1:this$0	Lcom/xyan/skincommon/LauncherConfig;
          //   159: invokestatic 118	com/xyan/skincommon/LauncherConfig:access$100	(Lcom/xyan/skincommon/LauncherConfig;)Ljava/lang/String;
          //   162: invokestatic 106	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   165: ifeq +12 -> 177
          //   168: aload_0
          //   169: getfield 20	com/xyan/skincommon/LauncherConfig$1:this$0	Lcom/xyan/skincommon/LauncherConfig;
          //   172: aload_3
          //   173: invokestatic 122	com/xyan/skincommon/LauncherConfig:access$102	(Lcom/xyan/skincommon/LauncherConfig;Ljava/lang/String;)Ljava/lang/String;
          //   176: pop
          //   177: aload_1
          //   178: monitorexit
          //   179: return
          //   180: astore_2
          //   181: aload_2
          //   182: invokevirtual 125	java/lang/Exception:printStackTrace	()V
          //   185: goto -51 -> 134
          //   188: astore_2
          //   189: aload_1
          //   190: monitorexit
          //   191: aload_2
          //   192: athrow
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	193	0	this	1
          //   84	49	2	str1	String
          //   180	2	2	localException	Exception
          //   188	4	2	localObject	Object
          //   50	123	3	str2	String
          // Exception table:
          //   from	to	target	type
          //   113	132	180	java/lang/Exception
          //   155	177	188	finally
          //   177	179	188	finally
          //   189	191	188	finally
        }
      });
    }
  }
  
  @SuppressLint({"ApplySharedPref"})
  public void setRemoteLauncher(Context arg1, String paramString)
  {
    synchronized (sLauncherConfig)
    {
      this.mRemoteLauncher = paramString;
      this.mSharedPreferences.edit().putString("launcher_skin_promote_launcher_pkg", paramString).commit();
      return;
    }
  }
}
