package com.S.c.c.c.g;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.S.c.S;
import com.S.c.S.c;
import com.android.absbase.c;
import com.android.absbase.utils.NE;
import com.android.absbase.utils.gb;
import com.android.absbase.utils.gb.n;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSettings.IntegrationErrorMode;
import com.facebook.ads.AdSettings.MultiprocessSupportMode;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.TypeCastException;
import kotlin.collections.RF;
import kotlin.jvm.internal.zA;
import kotlin.text.D;
import kotlin.text.Regex;

public final class g
{
  private static long F;
  private static final ReentrantLock S = new ReentrantLock();
  public static final g c = new g();
  private static boolean g;
  private static final AtomicBoolean m;
  private static final AdError n = new AdError(-1, "Unsupported facebook, Probably because facebook is not installed natively.");
  
  static
  {
    m = new AtomicBoolean(true);
  }
  
  private g() {}
  
  private final String c(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append((char)(paramArrayOfInt[i] - (j - i)));
      i += 1;
    }
    paramArrayOfInt = localStringBuilder.toString();
    zA.c(paramArrayOfInt, "sb.toString()");
    return paramArrayOfInt;
  }
  
  private final void c(boolean paramBoolean)
  {
    gb.n.c(gb.c, null, 1, null).n("FU_IFACU", paramBoolean);
  }
  
  public static final void m()
  {
    Object localObject = c.c();
    AdSettings.isTestMode((Context)localObject);
    localObject = ((Context)localObject).getSharedPreferences("FBAdPrefs", 0).getString("deviceIdHash", (String)null);
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return;
    }
    AdSettings.addTestDevice((String)localObject);
  }
  
  public final void F()
  {
    if (g) {}
    do
    {
      return;
      AudienceNetworkAds.initialize(c.c());
      AdSettings.setMultiprocessSupportMode(AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_OFF);
    } while ((!com.android.absbase.utils.g.c()) && (!S.c.c()));
    AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE);
  }
  
  public final AdError c()
  {
    return n;
  }
  
  public final void c(View paramView)
  {
    if ((paramView instanceof MediaView))
    {
      ViewParent localViewParent = ((MediaView)paramView).getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup))) {
        ((ViewGroup)localViewParent).removeView(paramView);
      }
      ((MediaView)paramView).setListener(null);
      ((MediaView)paramView).destroy();
    }
  }
  
  public final boolean c(long paramLong)
  {
    long l = System.currentTimeMillis();
    if (l - F > paramLong) {
      NE.c(new c(l, 3), 0L, 1, null);
    }
    return m.get();
  }
  
  public final boolean c(Activity paramActivity)
  {
    return paramActivity instanceof AudienceNetworkActivity;
  }
  
  public final boolean c(String paramString)
  {
    zA.n(paramString, "referrer");
    if (TextUtils.isEmpty((CharSequence)paramString)) {
      return false;
    }
    try
    {
      localObject = URLDecoder.decode(paramString, "UTF-8");
      zA.c(localObject, "URLDecoder.decode(referrer, \"UTF-8\")");
      paramString = (String)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        continue;
        i = 0;
        continue;
        paramString = RF.c();
      }
      label174:
      paramString = paramString.toArray(new String[0]);
      if (paramString != null) {
        break label200;
      }
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
      label200:
      String[] arrayOfString = (String[])paramString;
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break label575;
      }
      String str1 = c(new int[] { 125, 123, 115, 100, 119, 104, 112, 101 });
      String str2 = c(new int[] { 130, 108, 122, 107, 111, 105, 109, 110, 111, 122, 105 });
      int k = arrayOfString.length;
      int i = 0;
      while (i < k)
      {
        paramString = (CharSequence)arrayOfString[i];
        paramString = new Regex("=").split(paramString, 0);
        ListIterator localListIterator;
        int j;
        if (!paramString.isEmpty())
        {
          localListIterator = paramString.listIterator(paramString.size());
          if (localListIterator.hasPrevious()) {
            if (((CharSequence)localListIterator.previous()).length() == 0)
            {
              j = 1;
              label433:
              if (j != 0) {
                break label477;
              }
            }
          }
        }
        for (paramString = RF.n((Iterable)paramString, localListIterator.nextIndex() + 1);; paramString = RF.c())
        {
          paramString = (Collection)paramString;
          if (paramString != null) {
            break label486;
          }
          throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
          j = 0;
          break label433;
          label477:
          break;
        }
        label486:
        paramString = paramString.toArray(new String[0]);
        if (paramString == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        paramString = (String[])paramString;
        if ((paramString != null) && (paramString.length == 2) && (TextUtils.equals((CharSequence)str1, (CharSequence)paramString[0])) && (TextUtils.equals((CharSequence)str2, (CharSequence)paramString[1])))
        {
          c(true);
          return true;
        }
        i += 1;
      }
    }
    if (paramString != null)
    {
      paramString = (CharSequence)paramString;
      paramString = new Regex("&").split(paramString, 0);
      if (!paramString.isEmpty())
      {
        localObject = paramString.listIterator(paramString.size());
        for (;;)
        {
          if (((ListIterator)localObject).hasPrevious()) {
            if (((CharSequence)((ListIterator)localObject).previous()).length() == 0)
            {
              i = 1;
              if (i != 0) {
                continue;
              }
              paramString = RF.n((Iterable)paramString, ((ListIterator)localObject).nextIndex() + 1);
              paramString = (Collection)paramString;
              if (paramString != null) {
                break label174;
              }
              throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
          }
        }
      }
    }
    label575:
    return false;
  }
  
  public final boolean n()
  {
    return gb.n.c(gb.c, null, 1, null).F("FU_IFACU", false);
  }
  
  public static final class c
    extends NE
  {
    c(long paramLong, int paramInt)
    {
      super();
    }
    
    public void run()
    {
      if (g.c(g.c).tryLock()) {}
      for (;;)
      {
        try
        {
          Object localObject1 = c.c().getPackageManager();
          if (localObject1 != null)
          {
            localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
            if (((Iterator)localObject1).hasNext())
            {
              String str = ((PackageInfo)((Iterator)localObject1).next()).packageName;
              zA.c(str, "pi.packageName");
              if (!D.c(str, "com.facebook.", false, 2, null)) {
                continue;
              }
              bool = true;
              g.n(g.c).set(bool);
              if (!g.n(g.c).get()) {}
              g.c(g.c, this.n);
              return;
            }
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        finally
        {
          g.c(g.c).unlock();
        }
        boolean bool = false;
      }
    }
  }
}
