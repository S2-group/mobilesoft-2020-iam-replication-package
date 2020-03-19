package com.hyperspeed.rocketclean.pro;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class cwc
{
  private static final List<String> m = new ArrayList() {};
  
  public static boolean a()
  {
    return ((TelephonyManager)cep.m().getSystemService("phone")).getCallState() != 0;
  }
  
  public static List<ApplicationInfo> b()
  {
    List localList = mn();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = dvv.a.m().m().iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localList.contains(localApplicationInfo.packageName.toLowerCase())) {
        localArrayList.add(localApplicationInfo);
      }
    }
    return localArrayList;
  }
  
  public static Map<String, ApplicationInfo> bv()
  {
    Object localObject = v();
    HashMap localHashMap = new HashMap();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      localHashMap.put(localApplicationInfo.packageName, localApplicationInfo);
    }
    return localHashMap;
  }
  
  public static int c()
  {
    return cfm.m(10, new String[] { "Application", "Modules", "AppLock", "SuggestLockListMaxCount" });
  }
  
  public static boolean cx()
  {
    return dxq.n() >= 720;
  }
  
  public static int m(int paramInt)
  {
    return Math.round(cep.m().getResources().getDisplayMetrics().density * paramInt);
  }
  
  public static int m(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    for (;;)
    {
      double d1;
      Object localObject1;
      float f1;
      int j;
      label348:
      float f3;
      float f2;
      label435:
      label524:
      float f4;
      try
      {
        if (!paramBitmap.isRecycled())
        {
          Object localObject2 = new ih.a(paramBitmap);
          ((ih.a)localObject2).b = 1;
          int i;
          Object localObject3;
          if (((ih.a)localObject2).n != null)
          {
            paramBitmap = ((ih.a)localObject2).n;
            double d2 = -1.0D;
            if (((ih.a)localObject2).v > 0)
            {
              i = paramBitmap.getWidth() * paramBitmap.getHeight();
              d1 = d2;
              if (i <= ((ih.a)localObject2).v) {
                break label964;
              }
              d1 = Math.sqrt(((ih.a)localObject2).v / i);
              break label964;
              localObject1 = ((ih.a)localObject2).x;
              if ((paramBitmap != ((ih.a)localObject2).n) && (localObject1 != null))
              {
                d1 = paramBitmap.getWidth() / ((ih.a)localObject2).n.getWidth();
                ((Rect)localObject1).left = ((int)Math.floor(((Rect)localObject1).left * d1));
                ((Rect)localObject1).top = ((int)Math.floor(((Rect)localObject1).top * d1));
                ((Rect)localObject1).right = Math.min((int)Math.ceil(((Rect)localObject1).right * d1), paramBitmap.getWidth());
                ((Rect)localObject1).bottom = Math.min((int)Math.ceil(d1 * ((Rect)localObject1).bottom), paramBitmap.getHeight());
              }
              localObject3 = ((ih.a)localObject2).m(paramBitmap);
              i = ((ih.a)localObject2).b;
              if (((ih.a)localObject2).c.isEmpty())
              {
                localObject1 = null;
                localObject1 = new ig((int[])localObject3, i, (ih.b[])localObject1);
                if (paramBitmap != ((ih.a)localObject2).n) {
                  paramBitmap.recycle();
                }
                paramBitmap = ((ig)localObject1).mn;
                localObject2 = new ih(paramBitmap, ((ih.a)localObject2).mn);
                int n = ((ih)localObject2).n.size();
                i = 0;
                if (i >= n) {
                  continue;
                }
                localObject3 = (ii)((ih)localObject2).n.get(i);
                k = ((ii)localObject3).cx.length;
                f1 = 0.0F;
                j = 0;
                if (j >= k) {
                  continue;
                }
                f3 = localObject3.cx[j];
                f2 = f1;
                if (f3 <= 0.0F) {
                  break label973;
                }
                f2 = f1 + f3;
                break label973;
              }
            }
            else
            {
              d1 = d2;
              if (((ih.a)localObject2).bv <= 0) {
                break label964;
              }
              i = Math.max(paramBitmap.getWidth(), paramBitmap.getHeight());
              d1 = d2;
              if (i <= ((ih.a)localObject2).bv) {
                break label964;
              }
              d1 = ((ih.a)localObject2).bv / i;
              break label964;
              paramBitmap = Bitmap.createScaledBitmap(paramBitmap, (int)Math.ceil(paramBitmap.getWidth() * d1), (int)Math.ceil(d1 * paramBitmap.getHeight()), false);
              continue;
            }
            localObject1 = (ih.b[])((ih.a)localObject2).c.toArray(new ih.b[((ih.a)localObject2).c.size()]);
            continue;
          }
          else
          {
            paramBitmap = ((ih.a)localObject2).m;
            continue;
          }
          if (f1 != 0.0F)
          {
            j = 0;
            k = ((ii)localObject3).cx.length;
            if (j < k)
            {
              if (localObject3.cx[j] <= 0.0F) {
                break label986;
              }
              paramBitmap = ((ii)localObject3).cx;
              paramBitmap[j] /= f1;
              break label986;
            }
          }
          Map localMap = ((ih)localObject2).mn;
          f1 = 0.0F;
          paramBitmap = null;
          int i1 = ((ih)localObject2).m.size();
          j = 0;
          if (j < i1)
          {
            localObject1 = (ih.c)((ih)localObject2).m.get(j);
            float[] arrayOfFloat = ((ih.c)localObject1).m();
            if ((arrayOfFloat[1] < localObject3.c[0]) || (arrayOfFloat[1] > localObject3.c[2]) || (arrayOfFloat[2] < localObject3.x[0]) || (arrayOfFloat[2] > localObject3.x[2]) || (((ih)localObject2).b.get(((ih.c)localObject1).m))) {
              break label1033;
            }
            k = 1;
            if (k != 0)
            {
              arrayOfFloat = ((ih.c)localObject1).m();
              f2 = 0.0F;
              f3 = 0.0F;
              f4 = 0.0F;
              if (((ih)localObject2).v == null) {
                break label1039;
              }
              k = ((ih)localObject2).v.n;
              if (localObject3.cx[0] > 0.0F) {
                f2 = localObject3.cx[0] * (1.0F - Math.abs(arrayOfFloat[1] - localObject3.c[1]));
              }
              if (localObject3.cx[1] > 0.0F) {
                f3 = localObject3.cx[1] * (1.0F - Math.abs(arrayOfFloat[2] - localObject3.x[1]));
              }
              if (localObject3.cx[2] <= 0.0F) {
                break label995;
              }
              f4 = localObject3.cx[2] * (((ih.c)localObject1).n / k);
              break label995;
            }
          }
          else
          {
            if ((paramBitmap != null) && (((ii)localObject3).z)) {
              ((ih)localObject2).b.append(paramBitmap.m, true);
            }
            localMap.put(localObject3, paramBitmap);
            i += 1;
            continue;
            ((ih)localObject2).b.clear();
            paramBitmap = Collections.unmodifiableList(((ih)localObject2).m);
            if (paramBitmap.isEmpty()) {
              return 0;
            }
            i = ((ih.c)paramBitmap.get(0)).m;
            return i & 0xFFFFFF | 0xFF000000;
          }
        }
      }
      catch (ArithmeticException paramBitmap)
      {
        paramBitmap.printStackTrace();
        return 0;
      }
      for (;;)
      {
        break label1024;
        return 0;
        label964:
        if (d1 > 0.0D) {
          break label435;
        }
        break;
        label973:
        j += 1;
        f1 = f2;
        break label348;
        label986:
        j += 1;
        break label524;
        label995:
        f2 = f4 + (f3 + f2);
        if ((paramBitmap == null) || (f2 > f1))
        {
          f1 = f2;
          paramBitmap = (Bitmap)localObject1;
        }
      }
      label1024:
      j += 1;
      continue;
      label1033:
      int k = 0;
      continue;
      label1039:
      k = 1;
    }
  }
  
  public static String m(long paramLong)
  {
    if (paramLong < 0L) {
      return "-1-0s";
    }
    if (paramLong < 1000L) {
      return "0-1s";
    }
    if (paramLong < 2000L) {
      return "1-2s";
    }
    if (paramLong < 3000L) {
      return "2-3s";
    }
    if (paramLong < 4000L) {
      return "3-4s";
    }
    if (paramLong < 5000L) {
      return "4-5s";
    }
    return "5s+";
  }
  
  private static List<Map<String, String>> m(Map<String, Object> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramMap = epn.c(paramMap, new String[] { "Default", "ApplockSuggestLockList" });
    if (paramMap == null) {
      return localArrayList;
    }
    localArrayList.addAll(paramMap);
    return localArrayList;
  }
  
  public static void m()
  {
    if (cfh.n()) {
      cgh.m(cep.m(), "optimizer_app_lock").mn("PREF_KEY_IS_AFTER_RE_LOCK_TYPE_OPTIMIZE_MOMENT_VALUE", true);
    }
  }
  
  public static List<String> mn()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = x().iterator();
    while (localIterator.hasNext())
    {
      String str = cge.m((Map)localIterator.next(), "", new String[] { "PackageName" });
      if (!TextUtils.isEmpty(str)) {
        localArrayList.add(str.trim());
      }
    }
    return localArrayList;
  }
  
  public static boolean n()
  {
    return cgh.m(cep.m(), "optimizer_app_lock").m("PREF_KEY_IS_AFTER_RE_LOCK_TYPE_OPTIMIZE_MOMENT_VALUE", false);
  }
  
  public static List<ApplicationInfo> v()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = cep.m().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo != null) && (ckr.n(localApplicationInfo.packageName))) {
        localArrayList.add(localApplicationInfo);
      }
    }
    return localArrayList;
  }
  
  public static List<Map<String, String>> x()
  {
    ArrayList localArrayList = new ArrayList();
    Map localMap1 = za();
    if (localMap1 == null) {
      return localArrayList;
    }
    Map localMap2 = epn.x(localMap1, new String[] { "Regions" });
    if (localMap2 == null) {
      return m(localMap1);
    }
    String str1 = Locale.getDefault().getCountry().trim();
    Object localObject1 = epn.x(localMap2, new String[] { str1 });
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = epn.x(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
    }
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = epn.x(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
    }
    if (localObject1 == null)
    {
      localObject2 = localMap2.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        String str2 = (String)((Iterator)localObject2).next();
        if (TextUtils.equals(str2.toUpperCase(), str1.toUpperCase())) {
          localObject1 = epn.x(localMap2, new String[] { str2 });
        }
      }
    }
    for (;;)
    {
      if (localObject1 == null) {
        return m(localMap1);
      }
      localObject1 = epn.c((Map)localObject1, new String[] { "ApplockSuggestLockList" });
      if (localObject1 == null) {
        return m(localMap1);
      }
      localArrayList.addAll((Collection)localObject1);
      return localArrayList;
    }
  }
  
  public static Account z()
  {
    Account localAccount = null;
    try
    {
      Account[] arrayOfAccount = AccountManager.get(cep.m()).getAccountsByType("com.google");
      if (arrayOfAccount.length > 0) {
        localAccount = arrayOfAccount[0];
      }
      return localAccount;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  private static Map<String, Object> za()
  {
    // Byte code:
    //   0: ldc_w 469
    //   3: invokestatic 473	com/hyperspeed/rocketclean/pro/epq:m	(Ljava/lang/String;)Z
    //   6: pop
    //   7: invokestatic 24	com/hyperspeed/rocketclean/pro/cep:m	()Landroid/content/Context;
    //   10: invokevirtual 477	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   13: astore_1
    //   14: aload_1
    //   15: ldc_w 469
    //   18: invokevirtual 483	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   21: astore_2
    //   22: aload_2
    //   23: astore_1
    //   24: aload_2
    //   25: invokestatic 488	com/hyperspeed/rocketclean/pro/epl:m	(Ljava/io/InputStream;)Ljava/util/Map;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +33 -> 63
    //   33: aload_2
    //   34: astore_1
    //   35: aload_3
    //   36: invokeinterface 489 1 0
    //   41: istore_0
    //   42: iload_0
    //   43: ifne +20 -> 63
    //   46: aload_2
    //   47: ifnull +7 -> 54
    //   50: aload_2
    //   51: invokevirtual 494	java/io/InputStream:close	()V
    //   54: aload_3
    //   55: areturn
    //   56: astore_1
    //   57: aload_1
    //   58: invokevirtual 495	java/io/IOException:printStackTrace	()V
    //   61: aload_3
    //   62: areturn
    //   63: aload_2
    //   64: ifnull +7 -> 71
    //   67: aload_2
    //   68: invokevirtual 494	java/io/InputStream:close	()V
    //   71: aconst_null
    //   72: areturn
    //   73: astore_1
    //   74: aload_1
    //   75: invokevirtual 495	java/io/IOException:printStackTrace	()V
    //   78: goto -7 -> 71
    //   81: astore_3
    //   82: aconst_null
    //   83: astore_2
    //   84: aload_2
    //   85: astore_1
    //   86: aload_3
    //   87: invokevirtual 496	java/lang/Exception:printStackTrace	()V
    //   90: aload_2
    //   91: ifnull -20 -> 71
    //   94: aload_2
    //   95: invokevirtual 494	java/io/InputStream:close	()V
    //   98: goto -27 -> 71
    //   101: astore_1
    //   102: aload_1
    //   103: invokevirtual 495	java/io/IOException:printStackTrace	()V
    //   106: goto -35 -> 71
    //   109: astore_2
    //   110: aconst_null
    //   111: astore_1
    //   112: aload_1
    //   113: ifnull +7 -> 120
    //   116: aload_1
    //   117: invokevirtual 494	java/io/InputStream:close	()V
    //   120: aload_2
    //   121: athrow
    //   122: astore_1
    //   123: aload_1
    //   124: invokevirtual 495	java/io/IOException:printStackTrace	()V
    //   127: goto -7 -> 120
    //   130: astore_2
    //   131: goto -19 -> 112
    //   134: astore_3
    //   135: goto -51 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   41	2	0	bool	boolean
    //   13	22	1	localObject1	Object
    //   56	2	1	localIOException1	java.io.IOException
    //   73	2	1	localIOException2	java.io.IOException
    //   85	1	1	localObject2	Object
    //   101	2	1	localIOException3	java.io.IOException
    //   111	6	1	localObject3	Object
    //   122	2	1	localIOException4	java.io.IOException
    //   21	74	2	localInputStream	java.io.InputStream
    //   109	12	2	localObject4	Object
    //   130	1	2	localObject5	Object
    //   28	34	3	localMap	Map
    //   81	6	3	localException1	Exception
    //   134	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   50	54	56	java/io/IOException
    //   67	71	73	java/io/IOException
    //   14	22	81	java/lang/Exception
    //   94	98	101	java/io/IOException
    //   14	22	109	finally
    //   116	120	122	java/io/IOException
    //   24	29	130	finally
    //   35	42	130	finally
    //   86	90	130	finally
    //   24	29	134	java/lang/Exception
    //   35	42	134	java/lang/Exception
  }
}
