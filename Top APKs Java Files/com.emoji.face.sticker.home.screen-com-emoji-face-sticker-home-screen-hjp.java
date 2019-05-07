package com.emoji.face.sticker.home.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.ihs.device.common.HSAppFilter;
import com.ihs.device.common.HSAppFilter.aux;
import com.ihs.device.common.HSAppInfo;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public final class hjp
{
  final Map<String, aux> Code;
  ReentrantReadWriteLock V;
  
  /* Error */
  private hjp()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 25	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 27	java/util/HashMap
    //   8: dup
    //   9: invokespecial 28	java/util/HashMap:<init>	()V
    //   12: putfield 30	com/emoji/face/sticker/home/screen/hjp:Code	Ljava/util/Map;
    //   15: aload_0
    //   16: new 32	java/util/concurrent/locks/ReentrantReadWriteLock
    //   19: dup
    //   20: invokespecial 33	java/util/concurrent/locks/ReentrantReadWriteLock:<init>	()V
    //   23: putfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   26: aload_0
    //   27: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   30: invokevirtual 39	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   33: invokevirtual 44	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:lock	()V
    //   36: new 46	android/content/IntentFilter
    //   39: dup
    //   40: invokespecial 47	android/content/IntentFilter:<init>	()V
    //   43: astore_1
    //   44: aload_1
    //   45: ldc 49
    //   47: invokevirtual 53	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: ldc 55
    //   53: invokevirtual 53	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: ldc 57
    //   59: invokevirtual 60	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   62: invokestatic 66	com/emoji/face/sticker/home/screen/hez:H	()Landroid/content/Context;
    //   65: new 6	com/emoji/face/sticker/home/screen/hjp$1
    //   68: dup
    //   69: aload_0
    //   70: invokespecial 69	com/emoji/face/sticker/home/screen/hjp$1:<init>	(Lcom/emoji/face/sticker/home/screen/hjp;)V
    //   73: aload_1
    //   74: invokevirtual 75	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   77: pop
    //   78: invokestatic 66	com/emoji/face/sticker/home/screen/hez:H	()Landroid/content/Context;
    //   81: invokevirtual 79	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   84: astore_1
    //   85: new 81	java/util/HashSet
    //   88: dup
    //   89: invokestatic 86	com/emoji/face/sticker/home/screen/hjx:Code	()Ljava/util/List;
    //   92: invokespecial 89	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   95: astore_2
    //   96: aload_1
    //   97: sipush 128
    //   100: invokevirtual 95	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   103: invokeinterface 101 1 0
    //   108: astore_3
    //   109: aload_3
    //   110: invokeinterface 107 1 0
    //   115: ifeq +79 -> 194
    //   118: aload_3
    //   119: invokeinterface 111 1 0
    //   124: checkcast 113	android/content/pm/ApplicationInfo
    //   127: astore 4
    //   129: aload 4
    //   131: aload_1
    //   132: invokestatic 116	com/emoji/face/sticker/home/screen/hjp:Code	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;)Z
    //   135: ifeq -26 -> 109
    //   138: aload_0
    //   139: getfield 30	com/emoji/face/sticker/home/screen/hjp:Code	Ljava/util/Map;
    //   142: aload 4
    //   144: getfield 120	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   147: new 10	com/emoji/face/sticker/home/screen/hjp$aux
    //   150: dup
    //   151: aload 4
    //   153: aload_1
    //   154: aload_2
    //   155: aload 4
    //   157: getfield 120	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   160: invokeinterface 126 2 0
    //   165: iconst_0
    //   166: invokespecial 129	com/emoji/face/sticker/home/screen/hjp$aux:<init>	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;ZB)V
    //   169: invokeinterface 135 3 0
    //   174: pop
    //   175: goto -66 -> 109
    //   178: astore_1
    //   179: aload_1
    //   180: invokestatic 140	com/emoji/face/sticker/home/screen/hbh:Code	(Ljava/lang/Throwable;)V
    //   183: aload_0
    //   184: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   187: invokevirtual 39	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   190: invokevirtual 143	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   193: return
    //   194: new 145	java/lang/StringBuilder
    //   197: dup
    //   198: ldc -109
    //   200: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   203: aload_0
    //   204: getfield 30	com/emoji/face/sticker/home/screen/hjp:Code	Ljava/util/Map;
    //   207: invokeinterface 153 1 0
    //   212: invokevirtual 157	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload_0
    //   217: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   220: invokevirtual 39	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   223: invokevirtual 143	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   226: return
    //   227: astore_1
    //   228: aload_0
    //   229: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   232: invokevirtual 39	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   235: invokevirtual 143	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   238: aload_1
    //   239: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	240	0	this	hjp
    //   43	111	1	localObject1	Object
    //   178	2	1	localException	Exception
    //   227	12	1	localObject2	Object
    //   95	60	2	localHashSet	java.util.HashSet
    //   108	11	3	localIterator	Iterator
    //   127	29	4	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   36	109	178	java/lang/Exception
    //   109	175	178	java/lang/Exception
    //   194	216	178	java/lang/Exception
    //   36	109	227	finally
    //   109	175	227	finally
    //   179	183	227	finally
    //   194	216	227	finally
  }
  
  private List<aux> Code()
  {
    this.V.readLock().lock();
    try
    {
      ArrayList localArrayList = new ArrayList(this.Code.values());
      return localArrayList;
    }
    finally
    {
      this.V.readLock().unlock();
    }
  }
  
  static boolean Code(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
  {
    if ((paramApplicationInfo == null) || (paramPackageManager == null)) {}
    while (TextUtils.isEmpty(paramApplicationInfo.packageName)) {
      return false;
    }
    return true;
  }
  
  public final <T extends HSAppInfo> T Code(Class<T> paramClass, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    this.V.readLock().lock();
    try
    {
      paramString = (aux)this.Code.get(paramString);
      if (paramString != null)
      {
        paramClass = paramString.Code(paramClass);
        return paramClass;
      }
      return null;
    }
    catch (Exception paramClass)
    {
      hbh.Code(paramClass);
      return null;
    }
    finally
    {
      this.V.readLock().unlock();
    }
  }
  
  public final <T extends HSAppInfo> ArrayList<T> Code(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    HSAppFilter localHSAppFilter = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localHSAppFilter = new HSAppFilter();
    }
    Object localObject1 = Code();
    paramHSAppFilter = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (aux)((Iterator)localObject1).next();
      if (localHSAppFilter.Code((HSAppFilter.aux)localObject2))
      {
        localObject2 = ((aux)localObject2).Code(paramClass);
        if (localObject2 != null) {
          paramHSAppFilter.add(localObject2);
        }
      }
    }
    return paramHSAppFilter;
  }
  
  /* Error */
  public final boolean Code(String paramString, HSAppFilter paramHSAppFilter)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 4
    //   3: aload_2
    //   4: ifnonnull +12 -> 16
    //   7: new 194	com/ihs/device/common/HSAppFilter
    //   10: dup
    //   11: invokespecial 195	com/ihs/device/common/HSAppFilter:<init>	()V
    //   14: astore 4
    //   16: aload_1
    //   17: invokestatic 182	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   20: ifeq +5 -> 25
    //   23: iconst_0
    //   24: ireturn
    //   25: aload_0
    //   26: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   29: invokevirtual 163	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   32: invokevirtual 166	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   35: aload_0
    //   36: getfield 30	com/emoji/face/sticker/home/screen/hjp:Code	Ljava/util/Map;
    //   39: aload_1
    //   40: invokeinterface 187 2 0
    //   45: checkcast 10	com/emoji/face/sticker/home/screen/hjp$aux
    //   48: astore_1
    //   49: aload_1
    //   50: ifnull +28 -> 78
    //   53: aload 4
    //   55: aload_1
    //   56: invokevirtual 200	com/ihs/device/common/HSAppFilter:Code	(Lcom/ihs/device/common/HSAppFilter$aux;)Z
    //   59: istore_3
    //   60: iload_3
    //   61: ifeq +17 -> 78
    //   64: iconst_1
    //   65: istore_3
    //   66: aload_0
    //   67: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   70: invokevirtual 163	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   73: invokevirtual 174	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   76: iload_3
    //   77: ireturn
    //   78: iconst_0
    //   79: istore_3
    //   80: goto -14 -> 66
    //   83: astore_1
    //   84: aload_0
    //   85: getfield 35	com/emoji/face/sticker/home/screen/hjp:V	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   88: invokevirtual 163	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   91: invokevirtual 174	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	hjp
    //   0	96	1	paramString	String
    //   0	96	2	paramHSAppFilter	HSAppFilter
    //   59	21	3	bool	boolean
    //   1	53	4	localHSAppFilter	HSAppFilter
    // Exception table:
    //   from	to	target	type
    //   35	49	83	finally
    //   53	60	83	finally
  }
  
  static final class aux
    implements HSAppFilter.aux
  {
    private final boolean B;
    private final boolean C;
    private final String Code;
    private final boolean D;
    private final boolean F;
    private final int I;
    private final int L;
    private final boolean S;
    private final String V;
    private final String Z;
    
    private aux(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
    {
      this(paramApplicationInfo, paramPackageManager, hjx.Code().contains(str));
    }
    
    private aux(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager, boolean paramBoolean)
    {
      this.Code = paramApplicationInfo.packageName;
      paramPackageManager = paramPackageManager.getApplicationLabel(paramApplicationInfo);
      if (paramPackageManager == null)
      {
        paramPackageManager = "";
        this.V = paramPackageManager;
        this.I = paramApplicationInfo.flags;
        this.Z = paramApplicationInfo.publicSourceDir;
        this.B = hjx.V(paramApplicationInfo.packageName);
        this.C = hjx.Code(paramApplicationInfo);
        this.S = hjx.I(paramApplicationInfo.packageName);
        this.F = paramBoolean;
        paramPackageManager = paramApplicationInfo.packageName;
        if ((paramPackageManager == null) || ((!paramPackageManager.toLowerCase().contains("clock")) && (!paramPackageManager.toLowerCase().contains("alarm")))) {
          break label143;
        }
      }
      label143:
      for (paramBoolean = true;; paramBoolean = false)
      {
        this.D = paramBoolean;
        this.L = paramApplicationInfo.uid;
        return;
        paramPackageManager = paramPackageManager.toString().trim();
        break;
      }
    }
    
    final <T extends HSAppInfo> T Code(Class<T> paramClass)
    {
      try
      {
        paramClass = (HSAppInfo)paramClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { this.Code });
        paramClass.setAppName(this.V);
        paramClass.setApplicationInfoFlag(this.I);
        paramClass.setPublicSourceDir(this.Z);
        paramClass.setIsLaunchable(this.B);
        paramClass.setIsSysApp(this.C);
        paramClass.setIsLauncherApp(this.S);
        paramClass.setIsInputApp(this.F);
        paramClass.setIsAlarmApp(this.D);
        paramClass.setUid(this.L);
        return paramClass;
      }
      catch (Throwable paramClass) {}
      return null;
    }
    
    public final String getPackageName()
    {
      return this.Code;
    }
    
    public final boolean isAlarmApp()
    {
      return this.D;
    }
    
    public final boolean isInputApp()
    {
      return this.F;
    }
    
    public final boolean isLaunchable()
    {
      return this.B;
    }
    
    public final boolean isLauncherApp()
    {
      return this.S;
    }
    
    public final boolean isMusicPlayer()
    {
      return false;
    }
    
    public final boolean isRecentApp()
    {
      return false;
    }
    
    public final boolean isSysApp()
    {
      return this.C;
    }
  }
  
  public static final class con
  {
    private static final hjp Code = new hjp((byte)0);
  }
}
