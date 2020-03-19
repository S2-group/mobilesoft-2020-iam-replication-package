package com.lionmobi.powerclean.e;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.lionmobi.powerclean.service.lionmobiService;
import com.lionmobi.util.q;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class g
{
  private static g g = null;
  long a = 0L;
  String b = "";
  long c = 0L;
  String d = "";
  Set e;
  private lionmobiService f;
  private ActivityManager h = null;
  private com.lionmobi.util.g i;
  
  private g(lionmobiService paramLionmobiService)
  {
    this.f = paramLionmobiService;
    this.h = ((ActivityManager)paramLionmobiService.getSystemService("activity"));
    a();
    q.getInstance(this.f).getReadableDatabase();
    this.i = new com.lionmobi.util.g(this.f);
  }
  
  /* Error */
  private long a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_0
    //   4: getfield 43	com/lionmobi/powerclean/e/g:f	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   7: invokestatic 61	com/lionmobi/util/q:getInstance	(Landroid/content/Context;)Lcom/lionmobi/util/q;
    //   10: invokevirtual 65	com/lionmobi/util/q:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore 7
    //   15: aload 7
    //   17: ldc 77
    //   19: iconst_2
    //   20: anewarray 79	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc 81
    //   27: aastore
    //   28: dup
    //   29: iconst_1
    //   30: ldc 83
    //   32: aastore
    //   33: ldc 85
    //   35: iconst_1
    //   36: anewarray 79	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: aload_1
    //   42: aastore
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: invokevirtual 91	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_1
    //   50: aload_1
    //   51: astore 6
    //   53: lconst_0
    //   54: lstore_2
    //   55: aload 6
    //   57: astore_1
    //   58: aload 6
    //   60: invokeinterface 97 1 0
    //   65: ifeq +22 -> 87
    //   68: aload 6
    //   70: astore_1
    //   71: aload 6
    //   73: iconst_1
    //   74: invokeinterface 101 2 0
    //   79: lstore 4
    //   81: lload 4
    //   83: lstore_2
    //   84: goto -29 -> 55
    //   87: lload_2
    //   88: lstore 4
    //   90: aload 6
    //   92: ifnull +13 -> 105
    //   95: aload 6
    //   97: invokeinterface 104 1 0
    //   102: lload_2
    //   103: lstore 4
    //   105: lload 4
    //   107: ldc2_w 105
    //   110: lmul
    //   111: lreturn
    //   112: astore 7
    //   114: aconst_null
    //   115: astore 6
    //   117: lconst_0
    //   118: lstore_2
    //   119: aload 6
    //   121: astore_1
    //   122: aload 7
    //   124: invokevirtual 109	java/lang/Exception:printStackTrace	()V
    //   127: lload_2
    //   128: lstore 4
    //   130: aload 6
    //   132: ifnull -27 -> 105
    //   135: aload 6
    //   137: invokeinterface 104 1 0
    //   142: lload_2
    //   143: lstore 4
    //   145: goto -40 -> 105
    //   148: astore_1
    //   149: aload 6
    //   151: ifnull +10 -> 161
    //   154: aload 6
    //   156: invokeinterface 104 1 0
    //   161: aload_1
    //   162: athrow
    //   163: astore 7
    //   165: aload_1
    //   166: astore 6
    //   168: aload 7
    //   170: astore_1
    //   171: goto -22 -> 149
    //   174: astore 7
    //   176: goto -57 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	g
    //   0	179	1	paramString	String
    //   54	89	2	l1	long
    //   79	65	4	l2	long
    //   1	166	6	str	String
    //   13	3	7	localSQLiteDatabase	SQLiteDatabase
    //   112	11	7	localException1	Exception
    //   163	6	7	localObject	Object
    //   174	1	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   15	50	112	java/lang/Exception
    //   15	50	148	finally
    //   58	68	163	finally
    //   71	81	163	finally
    //   122	127	163	finally
    //   58	68	174	java/lang/Exception
    //   71	81	174	java/lang/Exception
  }
  
  private void a()
  {
    this.e = new HashSet();
    try
    {
      Object localObject = this.f.getPackageManager();
      if (localObject == null) {
        return;
      }
      localObject = ((PackageManager)localObject).getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) > 0) {
          this.e.add(localPackageInfo.packageName);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static g initInstance(lionmobiService paramLionmobiService)
  {
    if (g != null) {
      return g;
    }
    paramLionmobiService = new g(paramLionmobiService);
    g = paramLionmobiService;
    return paramLionmobiService;
  }
  
  public final void scheduledCheckAppUsage()
  {
    try
    {
      this.d = this.i.getCurrentPkg();
      if (TextUtils.isEmpty(this.d)) {
        return;
      }
      if (this.d.equals(this.b))
      {
        this.a += 60000L;
        return;
      }
      if (!this.b.equals(""))
      {
        this.a += a(this.d);
        updateAPPUsageTime(this.b, this.a);
      }
      this.a = 0L;
      this.b = this.d;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final void unregister()
  {
    g = null;
  }
  
  protected final void updateAPPUsageTime(String paramString, long paramLong)
  {
    try
    {
      if (!this.e.contains(paramString))
      {
        paramString = "insert or replace into lion_prostats (id, pkgname, usagetime, lastlaunchtime) values (null, \"" + paramString + "\", " + paramLong / 1000L + ", " + System.currentTimeMillis() + ");";
        q.getInstance(this.f).getWritableDatabase().execSQL(paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
}
