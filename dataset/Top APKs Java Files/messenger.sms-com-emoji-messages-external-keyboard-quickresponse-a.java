package com.emoji.messages.external.keyboard.quickresponse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.emoji.messages.external.entity.QROnlineInfo;
import com.emoji.messages.external.entity.QROnlineItemInfo;
import com.emoji.messages.external.utils.b;
import com.emoji.messages.sms.MmsApp;
import com.emoji.messages.sms.util.ae;
import com.emoji.messages.sms.util.q;
import com.google.a.f;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.Header;

public class a
{
  static a a;
  private static final String b = a.class.getSimpleName();
  private Context c = MmsApp.a().getApplicationContext();
  private Thread d;
  private Thread e;
  private Map<String, String[]> f = new HashMap();
  private List<String> g = new ArrayList();
  private Runnable h = new Runnable()
  {
    public void run()
    {
      Object localObject1 = a.a(a.this).getPackageManager();
      Object localObject2;
      try
      {
        localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject1 = new ArrayList();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if ((localPackageInfo.packageName.contains("love.messages.sms.")) && (!((List)localObject1).contains(localPackageInfo.packageName)))
          {
            ((List)localObject1).add(localPackageInfo.packageName);
            continue;
            PreferenceManager.getDefaultSharedPreferences(a.a(a.this)).edit().putBoolean("pref_qr_package_scanned", true).apply();
          }
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
      }
      for (;;)
      {
        a.a(a.this, null);
        return;
        localObject2 = new f().a(localNullPointerException);
        PreferenceManager.getDefaultSharedPreferences(a.a(a.this)).edit().putString("pref_qr_package_collection", (String)localObject2).apply();
        localNullPointerException.clear();
        a.a(a.this, true);
      }
    }
  };
  private Runnable i = new Runnable()
  {
    public void run()
    {
      a.this.e();
      Intent localIntent = new Intent("com.emoji.messages.KK_QR_DATA_CHANGED_INENT");
      a.a(a.this).sendBroadcast(localIntent);
    }
  };
  
  public a() {}
  
  public static a a()
  {
    if (a == null) {
      a = new a();
    }
    return a;
  }
  
  private void a(boolean paramBoolean)
  {
    if (((this.f.size() == 0) || (paramBoolean)) && ((this.e == null) || (!this.e.isAlive())))
    {
      this.e = new Thread(this.i);
      this.e.start();
    }
  }
  
  private void b(String paramString)
  {
    String[] arrayOfString2 = com.emoji.messages.external.theme.c.f(this.c, paramString, "qr_category_name");
    if (arrayOfString2 != null)
    {
      int j = 0;
      if (j < arrayOfString2.length)
      {
        String str = arrayOfString2[j];
        if (this.g.contains(str)) {}
        label402:
        for (;;)
        {
          j += 1;
          break;
          Object localObject1;
          if (str.equals("qr_default")) {
            localObject1 = null;
          }
          for (;;)
          {
            if (localObject1 == null) {
              break label402;
            }
            this.g.add(str);
            this.f.put(str, localObject1);
            break;
            Object localObject7 = b.a(g(), b.a(g(), 15897));
            Object localObject8 = new String(b.a);
            localObject1 = new f();
            Object localObject9 = new SecretKeySpec(((String)localObject7).getBytes(), (String)localObject8);
            localObject7 = new StringBuffer("");
            try
            {
              int k = g().createPackageContext(paramString, 2).getResources().getIdentifier("raw/" + str, "raw", paramString);
              InputStream localInputStream = g().createPackageContext(paramString, 2).getResources().openRawResource(k);
              localObject8 = Cipher.getInstance((String)localObject8);
              ((Cipher)localObject8).init(2, (Key)localObject9);
              localObject8 = new CipherInputStream(localInputStream, (Cipher)localObject8);
              localObject9 = new byte['⠀'];
              for (;;)
              {
                k = ((CipherInputStream)localObject8).read((byte[])localObject9);
                if (k == -1) {
                  break;
                }
                ((StringBuffer)localObject7).append(new String((byte[])localObject9, 0, k));
              }
            }
            catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
            {
              localNoSuchAlgorithmException.printStackTrace();
              String[] arrayOfString1 = null;
              continue;
              ((CipherInputStream)localObject8).close();
              arrayOfString1 = (String[])arrayOfString1.a(((StringBuffer)localObject7).toString(), new com.google.a.c.a() {}.b());
            }
            catch (NoSuchPaddingException localNoSuchPaddingException)
            {
              localNoSuchPaddingException.printStackTrace();
              Object localObject2 = null;
            }
            catch (InvalidKeyException localInvalidKeyException)
            {
              localInvalidKeyException.printStackTrace();
              Object localObject3 = null;
            }
            catch (IOException localIOException)
            {
              localIOException.printStackTrace();
              Object localObject4 = null;
            }
            catch (Resources.NotFoundException localNotFoundException)
            {
              localNotFoundException.printStackTrace();
              Object localObject5 = null;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              localNameNotFoundException.printStackTrace();
              Object localObject6 = null;
            }
          }
        }
      }
    }
  }
  
  private Context g()
  {
    return this.c;
  }
  
  public String[] a(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (this.e != null) && (!this.e.isAlive())) {
      return (String[])this.f.get(paramString);
    }
    return null;
  }
  
  public void b()
  {
    com.b.a.a.a localA = new com.b.a.a.a();
    localA.a(7000);
    localA.a("http://www.phoneonlineupdate.com:7080/online_collection/collection.php", new com.b.a.a.c()
    {
      public void a(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
      {
        paramAnonymousArrayOfHeader = new String(paramAnonymousArrayOfByte);
        paramAnonymousArrayOfByte = new f();
        ArrayList localArrayList;
        try
        {
          List localList = ((QROnlineInfo)paramAnonymousArrayOfByte.a(paramAnonymousArrayOfHeader, new com.google.a.c.a() {}.b())).getThemes();
          localArrayList = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext()) {
            localArrayList.add(((QROnlineItemInfo)localIterator.next()).getPackage_name());
          }
          localList.clear();
        }
        catch (Exception paramAnonymousArrayOfByte)
        {
          q.e(a.f(), "Cannot parse json from qr online config: " + paramAnonymousArrayOfHeader);
          return;
        }
        paramAnonymousArrayOfByte = paramAnonymousArrayOfByte.a(localArrayList);
        ae.f(a.a(a.this)).edit().putString("pref_main_online_qr_pkg_list", paramAnonymousArrayOfByte).apply();
      }
      
      public void a(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable) {}
    });
  }
  
  public void c()
  {
    if ((this.d == null) || (!this.d.isAlive()))
    {
      this.d = new Thread(this.h);
      this.d.start();
    }
  }
  
  public void d()
  {
    a(false);
  }
  
  public void e()
  {
    this.f.clear();
    Object localObject2 = ae.f(this.c).getString("pref_qr_package_collection", "");
    Object localObject1 = new ArrayList();
    ((List)localObject1).add(this.c.getPackageName());
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject2 = (List)new f().a((String)localObject2, new com.google.a.c.a() {}.b());
      if (localObject2 != null) {
        ((List)localObject1).addAll((Collection)localObject2);
      }
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      b((String)((Iterator)localObject1).next());
    }
    this.g.clear();
  }
}
