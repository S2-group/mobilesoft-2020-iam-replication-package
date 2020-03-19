package com.Q.F.F.F.l;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.Q.F.U;
import com.Q.F.U.F;
import com.android.absbase.F;
import com.android.absbase.utils.PG;
import com.android.absbase.utils.W;
import com.android.absbase.utils.W.a;
import com.android.absbase.utils.l;
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
import kotlin.text.R;
import kotlin.text.Regex;

public final class N
{
  public static final N F = new N();
  private static final AtomicBoolean Q = new AtomicBoolean(true);
  private static final ReentrantLock U = new ReentrantLock();
  private static final AdError a = new AdError(-1, "Unsupported facebook, Probably because facebook is not installed natively.");
  private static long g;
  private static boolean l;
  
  private N() {}
  
  private final String F(int[] paramArrayOfInt)
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
    kotlin.jvm.internal.C.F(paramArrayOfInt, "sb.toString()");
    return paramArrayOfInt;
  }
  
  private final void F(boolean paramBoolean)
  {
    W.a.F(W.F, null, 1, null).a("FU_IFACU", paramBoolean);
  }
  
  public static final void Q()
  {
    Object localObject = F.F();
    AdSettings.isTestMode((Context)localObject);
    localObject = ((Context)localObject).getSharedPreferences("FBAdPrefs", 0).getString("deviceIdHash", (String)null);
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return;
    }
    AdSettings.addTestDevice((String)localObject);
  }
  
  public final AdError F()
  {
    return a;
  }
  
  public final void F(View paramView)
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
  
  public final boolean F(long paramLong)
  {
    long l1 = System.currentTimeMillis();
    if (l1 - g > paramLong) {
      PG.F(new F(l1, 3), 0L, 1, null);
    }
    return Q.get();
  }
  
  public final boolean F(Activity paramActivity)
  {
    return paramActivity instanceof AudienceNetworkActivity;
  }
  
  public final boolean F(String paramString)
  {
    kotlin.jvm.internal.C.a(paramString, "referrer");
    if (TextUtils.isEmpty((CharSequence)paramString)) {
      return false;
    }
    try
    {
      localObject = URLDecoder.decode(paramString, "UTF-8");
      kotlin.jvm.internal.C.F(localObject, "URLDecoder.decode(referrer, \"UTF-8\")");
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
        paramString = kotlin.collections.C.F();
      }
      label173:
      paramString = paramString.toArray(new String[0]);
      if (paramString != null) {
        break label198;
      }
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
      label198:
      String[] arrayOfString = (String[])paramString;
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break label571;
      }
      String str1 = F(new int[] { 125, 123, 115, 100, 119, 104, 112, 101 });
      String str2 = F(new int[] { 130, 108, 122, 107, 111, 105, 109, 110, 111, 122, 105 });
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
              label431:
              if (j != 0) {
                break label474;
              }
            }
          }
        }
        for (paramString = kotlin.collections.C.a((Iterable)paramString, localListIterator.nextIndex() + 1);; paramString = kotlin.collections.C.F())
        {
          paramString = (Collection)paramString;
          if (paramString != null) {
            break label483;
          }
          throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
          j = 0;
          break label431;
          label474:
          break;
        }
        label483:
        paramString = paramString.toArray(new String[0]);
        if (paramString == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        paramString = (String[])paramString;
        if ((paramString != null) && (paramString.length == 2) && (TextUtils.equals((CharSequence)str1, (CharSequence)paramString[0])) && (TextUtils.equals((CharSequence)str2, (CharSequence)paramString[1])))
        {
          F(true);
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
              paramString = kotlin.collections.C.a((Iterable)paramString, ((ListIterator)localObject).nextIndex() + 1);
              paramString = (Collection)paramString;
              if (paramString != null) {
                break label173;
              }
              throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
          }
        }
      }
    }
    label571:
    return false;
  }
  
  public final boolean a()
  {
    return W.a.F(W.F, null, 1, null).g("FU_IFACU", false);
  }
  
  public final void g()
  {
    if (l) {}
    do
    {
      return;
      AudienceNetworkAds.initialize(F.F());
      AdSettings.setMultiprocessSupportMode(AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_OFF);
    } while ((!l.F()) && (!U.F.F()));
    AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE);
  }
  
  public static final class F
    extends PG
  {
    F(long paramLong, int paramInt)
    {
      super();
    }
    
    public void run()
    {
      if (N.F(N.F).tryLock()) {}
      for (;;)
      {
        try
        {
          Object localObject1 = F.F().getPackageManager();
          if (localObject1 != null)
          {
            localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
            if (((Iterator)localObject1).hasNext())
            {
              String str = ((PackageInfo)((Iterator)localObject1).next()).packageName;
              kotlin.jvm.internal.C.F(str, "pi.packageName");
              if (!R.F(str, "com.facebook.", false, 2, null)) {
                continue;
              }
              bool = true;
              N.a(N.F).set(bool);
              if (!N.a(N.F).get()) {}
              N.F(N.F, this.a);
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
          N.F(N.F).unlock();
        }
        boolean bool = false;
      }
    }
  }
}
