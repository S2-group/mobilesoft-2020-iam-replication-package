package ua.com.tim_berners.parental_control.data;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.DateUtils;
import android.util.Log;
import com.google.android.gms.location.LocationRequest;
import com.kochava.base.Tracker;
import com.kochava.base.Tracker.IdentityLink;
import io.realm.dk;
import io.realm.do.a;
import io.realm.dq;
import io.realm.du;
import io.realm.dy;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import okhttp3.MediaType;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import ua.com.tim_berners.parental_control.App;
import ua.com.tim_berners.parental_control.data.models.device.Device;
import ua.com.tim_berners.parental_control.data.models.location.LocationModel;
import ua.com.tim_berners.parental_control.data.models.location.MonitoringZone;
import ua.com.tim_berners.parental_control.data.models.permission.SettingsModel;
import ua.com.tim_berners.parental_control.data.network.ParentalService;
import ua.com.tim_berners.parental_control.service.ParentalAccessibilityService;
import ua.com.tim_berners.parental_control.service.ParentalNotificationListenerService;
import ua.com.tim_berners.parental_control.service.ServiceManager;
import ua.com.tim_berners.parental_control.service.UploadService;
import ua.com.tim_berners.parental_control.service.app_block.AppsUsageService;
import ua.com.tim_berners.parental_control.service.location.LocationTrackerService;

public class b
{
  private final ua.com.tim_berners.parental_control.data.b.a a;
  private final Context b;
  private final ua.com.tim_berners.parental_control.data.a.a c;
  private final ParentalService d;
  private long e;
  private String f;
  private int g;
  private final a h;
  private final ua.com.tim_berners.a.a.d i;
  private String j = null;
  private final ua.com.tim_berners.parental_control.service.a.b k;
  
  public b(Context paramContext, ua.com.tim_berners.parental_control.data.a.a paramA, ParentalService paramParentalService, ua.com.tim_berners.a.a.d paramD, ua.com.tim_berners.parental_control.data.b.a paramA1, a paramA2)
  {
    this.b = paramContext;
    this.c = paramA;
    this.d = paramParentalService;
    this.a = paramA1;
    this.i = paramD;
    this.h = paramA2;
    this.e = this.c.p();
    this.f = this.c.r();
    this.g = this.c.q();
    this.k = new ua.com.tim_berners.parental_control.service.a.b(paramContext, this);
    ua.com.tim_berners.parental_control.d.y.a().a(this.c.j());
    ua.com.tim_berners.parental_control.service.a.a(paramContext);
    V("dt_mng | cnstr");
    if (i())
    {
      ua.com.tim_berners.parental_control.d.z.m(paramContext);
      ua.com.tim_berners.parental_control.d.z.o(paramContext);
    }
  }
  
  private void B(boolean paramBoolean)
  {
    this.c.x(paramBoolean);
  }
  
  private void C(boolean paramBoolean)
  {
    this.c.D(paramBoolean);
  }
  
  private void D(boolean paramBoolean)
  {
    this.c.E(paramBoolean);
  }
  
  private void E(boolean paramBoolean)
  {
    this.c.F(paramBoolean);
  }
  
  private void F(boolean paramBoolean)
  {
    this.c.G(paramBoolean);
  }
  
  private void G(boolean paramBoolean)
  {
    this.c.H(paramBoolean);
  }
  
  private io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, String paramString2, long paramLong, String paramString3)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("monitoring_id", Long.valueOf(paramLong));
      localM2.a("zone_identifier", paramString3);
      localM1.a("device_id", paramString1);
      localM1.a("data", localM2);
      localM1.a("type", "locations");
      localM1.a("action", paramString2);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  private List<ua.com.tim_berners.parental_control.data.models.e.a> a(dk paramDk, List<ua.com.tim_berners.parental_control.data.models.e.a> paramList, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramDk = new HashSet(this.a.b(paramDk, paramString));
      paramDk.removeAll(paramList);
      localArrayList.addAll(paramDk);
      return localArrayList;
    }
    catch (Exception paramDk)
    {
      paramDk.printStackTrace();
    }
    return localArrayList;
  }
  
  private ua.com.tim_berners.parental_control.data.models.a.d a(Device paramDevice)
  {
    ua.com.tim_berners.parental_control.data.models.a.d localD = new ua.com.tim_berners.parental_control.data.models.a.d();
    localD.a(paramDevice.b());
    localD.a(paramDevice.c());
    localD.b(paramDevice.d());
    localD.c(paramDevice.e());
    localD.b(paramDevice.f());
    localD.d(paramDevice.g());
    localD.e(paramDevice.h());
    localD.f(paramDevice.i());
    localD.g(paramDevice.j());
    localD.h(paramDevice.k());
    localD.i(paramDevice.m());
    localD.c(paramDevice.n());
    localD.a(paramDevice.o());
    localD.b(paramDevice.p());
    localD.j(paramDevice.q());
    localD.k(paramDevice.r());
    localD.a(paramDevice.s());
    localD.l(paramDevice.t());
    localD.c(paramDevice.u());
    localD.d(paramDevice.v());
    localD.m(paramDevice.w());
    localD.n(paramDevice.x());
    return localD;
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    Tracker.setIdentityLink(new Tracker.IdentityLink().add("device_id", String.valueOf(paramInt2)).add("user_id", String.valueOf(paramInt1)));
  }
  
  private void a(ua.com.tim_berners.parental_control.data.models.a.a paramA)
  {
    this.a.a(paramA);
  }
  
  private boolean a(List<ua.com.tim_berners.parental_control.data.models.e.a> paramList, dy<ua.com.tim_berners.parental_control.data.models.e.a> paramDy)
  {
    HashMap localHashMap = new HashMap();
    paramDy = paramDy.iterator();
    Object localObject1;
    while (paramDy.hasNext())
    {
      localObject1 = (ua.com.tim_berners.parental_control.data.models.e.a)paramDy.next();
      localHashMap.put(((ua.com.tim_berners.parental_control.data.models.e.a)localObject1).k(), localObject1);
    }
    paramDy = paramList.iterator();
    int m;
    do
    {
      boolean bool2 = paramDy.hasNext();
      bool1 = false;
      int i1 = 0;
      if (!bool2) {
        break;
      }
      localObject1 = (ua.com.tim_berners.parental_control.data.models.e.a)paramDy.next();
      Object localObject2 = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject1).k();
      if (((ua.com.tim_berners.parental_control.data.models.e.a)localObject1).i() != null) {
        paramList = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject1).i();
      } else {
        paramList = "";
      }
      localObject2 = (ua.com.tim_berners.parental_control.data.models.e.a)localHashMap.get(localObject2);
      if (localObject2 != null)
      {
        Object localObject3 = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject2).i();
        int n;
        if (((localObject3 != null) && (!((String)localObject3).equals(paramList))) || ((localObject3 != null) && (paramList == null)) || ((localObject3 == null) && (paramList != null))) {
          n = 1;
        } else {
          n = 0;
        }
        m = n;
        if (n == 0)
        {
          localObject1 = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject1).m();
          paramList = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject2).m();
          m = n;
          if (paramList != null)
          {
            m = n;
            if (localObject1 != null)
            {
              if (paramList.size() != ((dq)localObject1).size()) {
                n = 1;
              }
              m = n;
              if (n == 0)
              {
                localObject1 = ((dq)localObject1).iterator();
                do
                {
                  do
                  {
                    m = n;
                    if (!((Iterator)localObject1).hasNext()) {
                      break;
                    }
                    localObject2 = ((ua.com.tim_berners.parental_control.data.models.e.h)((Iterator)localObject1).next()).d();
                  } while (localObject2 == null);
                  localObject3 = paramList.iterator();
                  ua.com.tim_berners.parental_control.data.models.e.h localH;
                  do
                  {
                    m = i1;
                    if (!((Iterator)localObject3).hasNext()) {
                      break;
                    }
                    localH = (ua.com.tim_berners.parental_control.data.models.e.h)((Iterator)localObject3).next();
                  } while ((localH == null) || (!localH.d().equals(localObject2)));
                  m = 1;
                  i1 = m;
                } while (m != 0);
                m = 1;
              }
            }
          }
        }
      }
      else
      {
        m = 0;
      }
    } while (m == 0);
    boolean bool1 = true;
    return bool1;
  }
  
  private boolean a(ua.com.tim_berners.a.b.f paramF)
  {
    ua.com.tim_berners.parental_control.data.models.a.e localE = R();
    boolean bool3 = true;
    boolean bool1 = bool3;
    if (localE != null)
    {
      if (paramF.c != localE.i) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      boolean bool2 = bool1;
      if (!bool1)
      {
        long l4 = Long.valueOf(paramF.b).longValue();
        long l5 = Long.valueOf(paramF.a).longValue();
        Object localObject = localE.l;
        long l2 = 0L;
        long l1;
        if (localObject != null) {
          l1 = Long.valueOf(localE.l).longValue();
        } else {
          l1 = 0L;
        }
        long l3;
        if (localE.m != null) {
          l3 = Long.valueOf(localE.m).longValue();
        } else {
          l3 = 0L;
        }
        if ((l4 != l1) || (l5 != l3)) {
          bool1 = true;
        }
        bool2 = bool1;
        if (!bool1)
        {
          paramF = paramF.d;
          if ((paramF != null) && (paramF.size() > 0))
          {
            paramF = paramF.entrySet().iterator();
            for (l1 = 0L;; l1 += ((ua.com.tim_berners.a.b.i)localObject).b)
            {
              l4 = l1;
              l3 = l2;
              if (!paramF.hasNext()) {
                break;
              }
              localObject = (ua.com.tim_berners.a.b.i)((Map.Entry)paramF.next()).getValue();
              l2 += ((ua.com.tim_berners.a.b.i)localObject).a;
            }
          }
          l4 = 0L;
          l3 = l2;
          bool1 = bool3;
          if (l3 != localE.j) {
            return bool1;
          }
          return l4 != localE.k;
        }
      }
      bool1 = bool2;
    }
    return bool1;
  }
  
  private void ag(String paramString)
  {
    this.c.o(paramString);
  }
  
  private ua.com.tim_berners.parental_control.data.models.f.a ah(String paramString)
  {
    ua.com.tim_berners.parental_control.data.models.f.a localA = new ua.com.tim_berners.parental_control.data.models.f.a();
    long l1 = System.currentTimeMillis();
    long l2 = l1 / 1000L;
    localA.a(l2);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" | ");
    localStringBuilder.append(l1);
    localA.a(ua.com.tim_berners.parental_control.d.z.a(localStringBuilder.toString()));
    localA.b(paramString);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" ||| now = ");
    localStringBuilder.append(l2);
    Log.d("Parental", localStringBuilder.toString());
    return localA;
  }
  
  /* Error */
  private long ai(String paramString)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 6
    //   3: lload 6
    //   5: lstore 4
    //   7: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   10: astore 10
    //   12: aconst_null
    //   13: astore 9
    //   15: aload 9
    //   17: astore 8
    //   19: aload_0
    //   20: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   23: aload 10
    //   25: aload_1
    //   26: invokevirtual 510	ua/com/tim_berners/parental_control/data/b/a:s	(Lio/realm/dk;Ljava/lang/String;)Lua/com/tim_berners/parental_control/data/models/a/c;
    //   29: astore_1
    //   30: lload 6
    //   32: lstore_2
    //   33: aload_1
    //   34: ifnull +12 -> 46
    //   37: aload 9
    //   39: astore 8
    //   41: aload_1
    //   42: invokevirtual 514	ua/com/tim_berners/parental_control/data/models/a/c:c	()J
    //   45: lstore_2
    //   46: lload_2
    //   47: lstore 4
    //   49: aload 10
    //   51: ifnull +81 -> 132
    //   54: lload_2
    //   55: lstore 4
    //   57: aload 10
    //   59: invokevirtual 517	io/realm/dk:close	()V
    //   62: lload_2
    //   63: lreturn
    //   64: astore_1
    //   65: goto +9 -> 74
    //   68: astore_1
    //   69: aload_1
    //   70: astore 8
    //   72: aload_1
    //   73: athrow
    //   74: aload 10
    //   76: ifnull +45 -> 121
    //   79: aload 8
    //   81: ifnull +31 -> 112
    //   84: lload 6
    //   86: lstore 4
    //   88: aload 10
    //   90: invokevirtual 517	io/realm/dk:close	()V
    //   93: goto +28 -> 121
    //   96: astore 9
    //   98: lload 6
    //   100: lstore 4
    //   102: aload 8
    //   104: aload 9
    //   106: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   109: goto +12 -> 121
    //   112: lload 6
    //   114: lstore 4
    //   116: aload 10
    //   118: invokevirtual 517	io/realm/dk:close	()V
    //   121: lload 6
    //   123: lstore 4
    //   125: aload_1
    //   126: athrow
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   132: lload 4
    //   134: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	this	b
    //   0	135	1	paramString	String
    //   32	31	2	l1	long
    //   5	128	4	l2	long
    //   1	121	6	l3	long
    //   17	86	8	localObject1	Object
    //   13	25	9	localObject2	Object
    //   96	9	9	localThrowable	Throwable
    //   10	107	10	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   19	30	64	finally
    //   41	46	64	finally
    //   72	74	64	finally
    //   19	30	68	java/lang/Throwable
    //   41	46	68	java/lang/Throwable
    //   88	93	96	java/lang/Throwable
    //   7	12	127	java/lang/Exception
    //   57	62	127	java/lang/Exception
    //   88	93	127	java/lang/Exception
    //   102	109	127	java/lang/Exception
    //   116	121	127	java/lang/Exception
    //   125	127	127	java/lang/Exception
  }
  
  /* Error */
  private boolean aj(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 526	ua/com/tim_berners/parental_control/data/b/a:o	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  /* Error */
  private boolean ak(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 530	ua/com/tim_berners/parental_control/data/b/a:d	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  /* Error */
  private boolean b(List<ua.com.tim_berners.parental_control.data.models.e.a> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 534	ua/com/tim_berners/parental_control/data/b:an	()Ljava/lang/String;
    //   4: astore 11
    //   6: iconst_0
    //   7: istore 5
    //   9: iconst_0
    //   10: istore 6
    //   12: iconst_0
    //   13: istore_3
    //   14: iconst_0
    //   15: istore 4
    //   17: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   20: astore 10
    //   22: aconst_null
    //   23: astore 8
    //   25: aconst_null
    //   26: astore 9
    //   28: iload 5
    //   30: istore_3
    //   31: aload 9
    //   33: astore 7
    //   35: aload 10
    //   37: invokevirtual 524	io/realm/dk:a	()V
    //   40: iload 5
    //   42: istore_3
    //   43: aload 9
    //   45: astore 7
    //   47: aload_0
    //   48: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   51: aload 10
    //   53: aload 11
    //   55: invokevirtual 197	ua/com/tim_berners/parental_control/data/b/a:b	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   58: astore 11
    //   60: iload 5
    //   62: istore_3
    //   63: aload 9
    //   65: astore 7
    //   67: new 192	java/util/HashSet
    //   70: dup
    //   71: aload 11
    //   73: invokespecial 200	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   76: astore 12
    //   78: iload 5
    //   80: istore_3
    //   81: aload 9
    //   83: astore 7
    //   85: new 192	java/util/HashSet
    //   88: dup
    //   89: aload_1
    //   90: invokespecial 200	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   93: astore 13
    //   95: iload 5
    //   97: istore_3
    //   98: aload 9
    //   100: astore 7
    //   102: aload 13
    //   104: aload 12
    //   106: invokeinterface 206 2 0
    //   111: pop
    //   112: iload 5
    //   114: istore_3
    //   115: aload 9
    //   117: astore 7
    //   119: aload 13
    //   121: invokeinterface 535 1 0
    //   126: istore_2
    //   127: iload_2
    //   128: ifle +8 -> 136
    //   131: iconst_1
    //   132: istore_3
    //   133: goto +5 -> 138
    //   136: iconst_0
    //   137: istore_3
    //   138: iload_3
    //   139: ifne +35 -> 174
    //   142: aload_0
    //   143: aload_1
    //   144: aload 11
    //   146: invokespecial 537	ua/com/tim_berners/parental_control/data/b:a	(Ljava/util/List;Lio/realm/dy;)Z
    //   149: istore 5
    //   151: iload 5
    //   153: ifeq +24 -> 177
    //   156: goto +18 -> 174
    //   159: astore_1
    //   160: iload_3
    //   161: istore 4
    //   163: aload 8
    //   165: astore 7
    //   167: goto +45 -> 212
    //   170: astore_1
    //   171: goto +36 -> 207
    //   174: iconst_1
    //   175: istore 4
    //   177: iload 4
    //   179: istore_3
    //   180: aload 10
    //   182: ifnull +84 -> 266
    //   185: iload 4
    //   187: istore_3
    //   188: aload 10
    //   190: invokevirtual 517	io/realm/dk:close	()V
    //   193: iload 4
    //   195: ireturn
    //   196: astore_1
    //   197: iload_3
    //   198: istore 4
    //   200: goto +12 -> 212
    //   203: astore_1
    //   204: iload 6
    //   206: istore_3
    //   207: aload_1
    //   208: astore 7
    //   210: aload_1
    //   211: athrow
    //   212: aload 10
    //   214: ifnull +42 -> 256
    //   217: aload 7
    //   219: ifnull +29 -> 248
    //   222: iload 4
    //   224: istore_3
    //   225: aload 10
    //   227: invokevirtual 517	io/realm/dk:close	()V
    //   230: goto +26 -> 256
    //   233: astore 8
    //   235: iload 4
    //   237: istore_3
    //   238: aload 7
    //   240: aload 8
    //   242: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   245: goto +11 -> 256
    //   248: iload 4
    //   250: istore_3
    //   251: aload 10
    //   253: invokevirtual 517	io/realm/dk:close	()V
    //   256: iload 4
    //   258: istore_3
    //   259: aload_1
    //   260: athrow
    //   261: astore_1
    //   262: aload_1
    //   263: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   266: iload_3
    //   267: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	b
    //   0	268	1	paramList	List<ua.com.tim_berners.parental_control.data.models.e.a>
    //   126	2	2	m	int
    //   13	254	3	bool1	boolean
    //   15	242	4	bool2	boolean
    //   7	145	5	bool3	boolean
    //   10	195	6	bool4	boolean
    //   33	206	7	localObject1	Object
    //   23	141	8	localObject2	Object
    //   233	8	8	localThrowable	Throwable
    //   26	90	9	localObject3	Object
    //   20	232	10	localDk	dk
    //   4	141	11	localObject4	Object
    //   76	29	12	localHashSet1	HashSet
    //   93	27	13	localHashSet2	HashSet
    // Exception table:
    //   from	to	target	type
    //   142	151	159	finally
    //   142	151	170	java/lang/Throwable
    //   35	40	196	finally
    //   47	60	196	finally
    //   67	78	196	finally
    //   85	95	196	finally
    //   102	112	196	finally
    //   119	127	196	finally
    //   210	212	196	finally
    //   35	40	203	java/lang/Throwable
    //   47	60	203	java/lang/Throwable
    //   67	78	203	java/lang/Throwable
    //   85	95	203	java/lang/Throwable
    //   102	112	203	java/lang/Throwable
    //   119	127	203	java/lang/Throwable
    //   225	230	233	java/lang/Throwable
    //   17	22	261	java/lang/Exception
    //   188	193	261	java/lang/Exception
    //   225	230	261	java/lang/Exception
    //   238	245	261	java/lang/Exception
    //   251	256	261	java/lang/Exception
    //   259	261	261	java/lang/Exception
  }
  
  private long bA()
  {
    return this.c.Z();
  }
  
  private long bB()
  {
    return this.c.aa();
  }
  
  private String bC()
  {
    return this.c.ab();
  }
  
  private boolean bD()
  {
    return this.c.ad();
  }
  
  private boolean bE()
  {
    return this.c.ae();
  }
  
  private boolean bF()
  {
    return this.c.af();
  }
  
  private boolean bG()
  {
    return this.c.ag();
  }
  
  private boolean bH()
  {
    return this.c.ah();
  }
  
  private String bI()
  {
    String str2 = this.c.h();
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.length() != 0) {}
    }
    else
    {
      str1 = ua.com.tim_berners.parental_control.d.z.a(this.b);
      d(str1);
    }
    return str1;
  }
  
  private List<ua.com.tim_berners.parental_control.data.models.c.a> bJ()
  {
    localArrayList = new ArrayList();
    try
    {
      PackageManager localPackageManager = this.b.getPackageManager();
      Object localObject1 = localPackageManager.getInstalledApplications(128);
      HashMap localHashMap = new HashMap();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        String str = ((ApplicationInfo)((Iterator)localObject1).next()).packageName;
        if (str != null)
        {
          boolean bool = ua.com.tim_berners.parental_control.d.a.f(str);
          if ((bool) || (ua.com.tim_berners.parental_control.d.a.d(str)))
          {
            ua.com.tim_berners.parental_control.data.models.c.a localA = new ua.com.tim_berners.parental_control.data.models.c.a();
            if (localHashMap.get(str) == null)
            {
              Object localObject2;
              if (!bool)
              {
                localObject2 = localPackageManager.getLaunchIntentForPackage(str);
                if (localObject2 == null) {}
              }
              else
              {
                try
                {
                  localObject2 = str.split("\\.");
                  localObject2 = localObject2[(localObject2.length - 1)];
                  StringBuilder localStringBuilder = new StringBuilder();
                  localStringBuilder.append(((String)localObject2).substring(0, 1).toUpperCase());
                  localStringBuilder.append(((String)localObject2).substring(1).toLowerCase());
                  localObject2 = localStringBuilder.toString();
                  localA.e(str);
                  localA.g((String)localObject2);
                  localArrayList.add(localA);
                  localHashMap.put(str, localA);
                }
                catch (Exception localException2)
                {
                  localException2.printStackTrace();
                }
              }
            }
          }
        }
      }
      return localArrayList;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
  }
  
  private ua.com.tim_berners.a.b.f bK()
  {
    return this.i.a();
  }
  
  private void bL()
  {
    try
    {
      this.a.b();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean bM()
  {
    return (ua.com.tim_berners.parental_control.d.w.n(this.b)) && (ua.com.tim_berners.parental_control.d.w.o(this.b));
  }
  
  private boolean bN()
  {
    return ua.com.tim_berners.parental_control.d.w.g(this.b);
  }
  
  private boolean bO()
  {
    return ua.com.tim_berners.parental_control.d.t.b(this.b);
  }
  
  private boolean bP()
  {
    return ua.com.tim_berners.parental_control.service.a.b().size() > 0;
  }
  
  private boolean bQ()
  {
    return ua.com.tim_berners.parental_control.service.a.a().size() > 0;
  }
  
  /* Error */
  private boolean bR()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_2
    //   5: iload 4
    //   7: istore_3
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 7
    //   13: aconst_null
    //   14: astore 6
    //   16: aload 6
    //   18: astore 5
    //   20: aload 7
    //   22: invokevirtual 524	io/realm/dk:a	()V
    //   25: aload 6
    //   27: astore 5
    //   29: aload_0
    //   30: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   33: aload 7
    //   35: invokevirtual 668	ua/com/tim_berners/parental_control/data/b/a:g	(Lio/realm/dk;)Lio/realm/dy;
    //   38: invokevirtual 527	io/realm/dy:size	()I
    //   41: istore_1
    //   42: iload_1
    //   43: ifle +5 -> 48
    //   46: iconst_1
    //   47: istore_2
    //   48: iload_2
    //   49: istore_3
    //   50: aload 7
    //   52: ifnull +83 -> 135
    //   55: iload_2
    //   56: istore_3
    //   57: aload 7
    //   59: invokevirtual 517	io/realm/dk:close	()V
    //   62: iload_2
    //   63: ireturn
    //   64: astore 6
    //   66: goto +12 -> 78
    //   69: astore 6
    //   71: aload 6
    //   73: astore 5
    //   75: aload 6
    //   77: athrow
    //   78: aload 7
    //   80: ifnull +42 -> 122
    //   83: aload 5
    //   85: ifnull +29 -> 114
    //   88: iload 4
    //   90: istore_3
    //   91: aload 7
    //   93: invokevirtual 517	io/realm/dk:close	()V
    //   96: goto +26 -> 122
    //   99: astore 7
    //   101: iload 4
    //   103: istore_3
    //   104: aload 5
    //   106: aload 7
    //   108: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   111: goto +11 -> 122
    //   114: iload 4
    //   116: istore_3
    //   117: aload 7
    //   119: invokevirtual 517	io/realm/dk:close	()V
    //   122: iload 4
    //   124: istore_3
    //   125: aload 6
    //   127: athrow
    //   128: astore 5
    //   130: aload 5
    //   132: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   135: iload_3
    //   136: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	b
    //   41	2	1	m	int
    //   4	59	2	bool1	boolean
    //   7	129	3	bool2	boolean
    //   1	122	4	bool3	boolean
    //   18	87	5	localObject1	Object
    //   128	3	5	localException	Exception
    //   14	12	6	localObject2	Object
    //   64	1	6	localObject3	Object
    //   69	57	6	localThrowable1	Throwable
    //   11	81	7	localDk	dk
    //   99	19	7	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   20	25	64	finally
    //   29	42	64	finally
    //   75	78	64	finally
    //   20	25	69	java/lang/Throwable
    //   29	42	69	java/lang/Throwable
    //   91	96	99	java/lang/Throwable
    //   8	13	128	java/lang/Exception
    //   57	62	128	java/lang/Exception
    //   91	96	128	java/lang/Exception
    //   104	111	128	java/lang/Exception
    //   117	122	128	java/lang/Exception
    //   125	128	128	java/lang/Exception
  }
  
  private boolean bu()
  {
    return this.c.P();
  }
  
  private long bv()
  {
    return this.c.Q();
  }
  
  private long bw()
  {
    return this.c.R();
  }
  
  private long bx()
  {
    return this.c.W();
  }
  
  private long by()
  {
    return this.c.X();
  }
  
  private long bz()
  {
    return this.c.Y();
  }
  
  private void c(Location paramLocation, String paramString1, String paramString2)
  {
    try
    {
      ua.com.tim_berners.parental_control.data.models.location.e localE = new ua.com.tim_berners.parental_control.data.models.location.e();
      localE.a(paramString1);
      localE.b(paramString2);
      paramString1 = new LocationModel();
      paramString1.a(paramLocation.getLatitude());
      paramString1.b(paramLocation.getLongitude());
      localE.a(paramString1);
      this.a.a(localE);
      return;
    }
    catch (Exception paramLocation)
    {
      paramLocation.printStackTrace();
    }
  }
  
  /* Error */
  private boolean c(List<ua.com.tim_berners.parental_control.data.models.e.a> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 720	ua/com/tim_berners/parental_control/data/b:b	(Ljava/util/List;)Z
    //   5: istore_3
    //   6: aload_0
    //   7: invokevirtual 534	ua/com/tim_berners/parental_control/data/b:an	()Ljava/lang/String;
    //   10: astore 8
    //   12: iload_3
    //   13: istore 4
    //   15: iload_3
    //   16: ifne +159 -> 175
    //   19: iload_3
    //   20: istore 4
    //   22: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   25: astore 7
    //   27: aconst_null
    //   28: astore 6
    //   30: aload 6
    //   32: astore 5
    //   34: aload 7
    //   36: invokevirtual 524	io/realm/dk:a	()V
    //   39: aload 6
    //   41: astore 5
    //   43: aload_0
    //   44: aload 7
    //   46: aload_1
    //   47: aload 8
    //   49: invokespecial 722	ua/com/tim_berners/parental_control/data/b:a	(Lio/realm/dk;Ljava/util/List;Ljava/lang/String;)Ljava/util/List;
    //   52: astore 8
    //   54: aload 8
    //   56: ifnull +24 -> 80
    //   59: aload 6
    //   61: astore 5
    //   63: aload 8
    //   65: invokeinterface 661 1 0
    //   70: istore_2
    //   71: iload_2
    //   72: ifle +8 -> 80
    //   75: iconst_1
    //   76: istore_3
    //   77: goto +5 -> 82
    //   80: iconst_0
    //   81: istore_3
    //   82: iload_3
    //   83: istore 4
    //   85: aload 7
    //   87: ifnull +88 -> 175
    //   90: iload_3
    //   91: istore 4
    //   93: aload 7
    //   95: invokevirtual 517	io/realm/dk:close	()V
    //   98: iload_3
    //   99: istore 4
    //   101: goto +74 -> 175
    //   104: astore 6
    //   106: goto +12 -> 118
    //   109: astore 6
    //   111: aload 6
    //   113: astore 5
    //   115: aload 6
    //   117: athrow
    //   118: aload 7
    //   120: ifnull +42 -> 162
    //   123: aload 5
    //   125: ifnull +29 -> 154
    //   128: iload_3
    //   129: istore 4
    //   131: aload 7
    //   133: invokevirtual 517	io/realm/dk:close	()V
    //   136: goto +26 -> 162
    //   139: astore 7
    //   141: iload_3
    //   142: istore 4
    //   144: aload 5
    //   146: aload 7
    //   148: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   151: goto +11 -> 162
    //   154: iload_3
    //   155: istore 4
    //   157: aload 7
    //   159: invokevirtual 517	io/realm/dk:close	()V
    //   162: iload_3
    //   163: istore 4
    //   165: aload 6
    //   167: athrow
    //   168: astore 5
    //   170: aload 5
    //   172: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   175: new 471	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   182: astore 5
    //   184: aload 5
    //   186: ldc_w 724
    //   189: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: aload 5
    //   195: aload_1
    //   196: invokeinterface 661 1 0
    //   201: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload 5
    //   207: ldc_w 729
    //   210: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: aload 5
    //   216: iload 4
    //   218: invokevirtual 732	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_0
    //   223: aload 5
    //   225: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokevirtual 91	ua/com/tim_berners/parental_control/data/b:V	(Ljava/lang/String;)V
    //   231: iload 4
    //   233: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	234	0	this	b
    //   0	234	1	paramList	List<ua.com.tim_berners.parental_control.data.models.e.a>
    //   70	2	2	m	int
    //   5	158	3	bool1	boolean
    //   13	219	4	bool2	boolean
    //   32	113	5	localObject1	Object
    //   168	3	5	localException	Exception
    //   182	42	5	localStringBuilder	StringBuilder
    //   28	32	6	localObject2	Object
    //   104	1	6	localObject3	Object
    //   109	57	6	localThrowable1	Throwable
    //   25	107	7	localDk	dk
    //   139	19	7	localThrowable2	Throwable
    //   10	54	8	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   34	39	104	finally
    //   43	54	104	finally
    //   63	71	104	finally
    //   115	118	104	finally
    //   34	39	109	java/lang/Throwable
    //   43	54	109	java/lang/Throwable
    //   63	71	109	java/lang/Throwable
    //   131	136	139	java/lang/Throwable
    //   22	27	168	java/lang/Exception
    //   93	98	168	java/lang/Exception
    //   131	136	168	java/lang/Exception
    //   144	151	168	java/lang/Exception
    //   157	162	168	java/lang/Exception
    //   165	168	168	java/lang/Exception
  }
  
  /* Error */
  private boolean d(List<ua.com.tim_berners.parental_control.data.models.c.a> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 534	ua/com/tim_berners/parental_control/data/b:an	()Ljava/lang/String;
    //   4: astore 11
    //   6: iconst_0
    //   7: istore 4
    //   9: iconst_0
    //   10: istore 5
    //   12: iconst_0
    //   13: istore 7
    //   15: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   18: astore 10
    //   20: aconst_null
    //   21: astore 8
    //   23: aconst_null
    //   24: astore 9
    //   26: aload 10
    //   28: invokevirtual 524	io/realm/dk:a	()V
    //   31: aload_0
    //   32: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   35: aload 10
    //   37: aload 11
    //   39: invokevirtual 734	ua/com/tim_berners/parental_control/data/b/a:f	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   42: astore 11
    //   44: aload_1
    //   45: invokeinterface 661 1 0
    //   50: istore_2
    //   51: aload 11
    //   53: invokevirtual 527	io/realm/dy:size	()I
    //   56: istore_3
    //   57: iload_2
    //   58: iload_3
    //   59: if_icmpeq +9 -> 68
    //   62: iconst_1
    //   63: istore 4
    //   65: goto +6 -> 71
    //   68: iconst_0
    //   69: istore 4
    //   71: iload 4
    //   73: istore 5
    //   75: iload 4
    //   77: ifne +222 -> 299
    //   80: iload 4
    //   82: istore 6
    //   84: aload 9
    //   86: astore 8
    //   88: iload 4
    //   90: istore 5
    //   92: new 192	java/util/HashSet
    //   95: dup
    //   96: aload 11
    //   98: invokespecial 200	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   101: astore 12
    //   103: iload 4
    //   105: istore 6
    //   107: aload 9
    //   109: astore 8
    //   111: iload 4
    //   113: istore 5
    //   115: new 192	java/util/HashSet
    //   118: dup
    //   119: aload_1
    //   120: invokespecial 200	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   123: astore 13
    //   125: iload 4
    //   127: istore 6
    //   129: aload 9
    //   131: astore 8
    //   133: iload 4
    //   135: istore 5
    //   137: aload 13
    //   139: aload 12
    //   141: invokeinterface 206 2 0
    //   146: pop
    //   147: iload 4
    //   149: istore 6
    //   151: aload 9
    //   153: astore 8
    //   155: iload 4
    //   157: istore 5
    //   159: aload 13
    //   161: invokeinterface 535 1 0
    //   166: istore_2
    //   167: iload_2
    //   168: ifle +9 -> 177
    //   171: iconst_1
    //   172: istore 4
    //   174: goto +6 -> 180
    //   177: iconst_0
    //   178: istore 4
    //   180: iload 4
    //   182: istore 5
    //   184: iload 4
    //   186: ifne +113 -> 299
    //   189: iload 4
    //   191: istore 6
    //   193: aload 9
    //   195: astore 8
    //   197: iload 4
    //   199: istore 5
    //   201: new 192	java/util/HashSet
    //   204: dup
    //   205: aload 11
    //   207: invokespecial 200	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   210: astore 11
    //   212: iload 4
    //   214: istore 6
    //   216: aload 9
    //   218: astore 8
    //   220: iload 4
    //   222: istore 5
    //   224: aload 11
    //   226: aload_1
    //   227: invokeinterface 206 2 0
    //   232: pop
    //   233: iload 4
    //   235: istore 6
    //   237: aload 9
    //   239: astore 8
    //   241: iload 4
    //   243: istore 5
    //   245: aload 11
    //   247: invokeinterface 535 1 0
    //   252: istore_2
    //   253: iload 7
    //   255: istore 4
    //   257: iload_2
    //   258: ifle +45 -> 303
    //   261: iconst_1
    //   262: istore 4
    //   264: goto +39 -> 303
    //   267: astore_1
    //   268: iload 4
    //   270: istore 6
    //   272: aload 9
    //   274: astore 8
    //   276: iload 4
    //   278: istore 5
    //   280: aload_1
    //   281: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   284: iload 4
    //   286: istore 5
    //   288: goto +11 -> 299
    //   291: astore_1
    //   292: iload 6
    //   294: istore 5
    //   296: goto +36 -> 332
    //   299: iload 5
    //   301: istore 4
    //   303: iload 4
    //   305: istore 5
    //   307: aload 10
    //   309: ifnull +91 -> 400
    //   312: iload 4
    //   314: istore 5
    //   316: aload 10
    //   318: invokevirtual 517	io/realm/dk:close	()V
    //   321: iload 4
    //   323: ireturn
    //   324: astore_1
    //   325: goto +17 -> 342
    //   328: astore_1
    //   329: iconst_0
    //   330: istore 5
    //   332: aload_1
    //   333: astore 8
    //   335: aload_1
    //   336: athrow
    //   337: astore_1
    //   338: iload 5
    //   340: istore 4
    //   342: aload 10
    //   344: ifnull +45 -> 389
    //   347: aload 8
    //   349: ifnull +31 -> 380
    //   352: iload 4
    //   354: istore 5
    //   356: aload 10
    //   358: invokevirtual 517	io/realm/dk:close	()V
    //   361: goto +28 -> 389
    //   364: astore 9
    //   366: iload 4
    //   368: istore 5
    //   370: aload 8
    //   372: aload 9
    //   374: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   377: goto +12 -> 389
    //   380: iload 4
    //   382: istore 5
    //   384: aload 10
    //   386: invokevirtual 517	io/realm/dk:close	()V
    //   389: iload 4
    //   391: istore 5
    //   393: aload_1
    //   394: athrow
    //   395: astore_1
    //   396: aload_1
    //   397: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   400: iload 5
    //   402: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	403	0	this	b
    //   0	403	1	paramList	List<ua.com.tim_berners.parental_control.data.models.c.a>
    //   50	208	2	m	int
    //   56	4	3	n	int
    //   7	383	4	bool1	boolean
    //   10	391	5	bool2	boolean
    //   82	211	6	bool3	boolean
    //   13	241	7	bool4	boolean
    //   21	350	8	localObject1	Object
    //   24	249	9	localObject2	Object
    //   364	9	9	localThrowable	Throwable
    //   18	367	10	localDk	dk
    //   4	242	11	localObject3	Object
    //   101	39	12	localHashSet1	HashSet
    //   123	37	13	localHashSet2	HashSet
    // Exception table:
    //   from	to	target	type
    //   201	212	267	java/lang/Exception
    //   224	233	267	java/lang/Exception
    //   245	253	267	java/lang/Exception
    //   92	103	291	java/lang/Throwable
    //   115	125	291	java/lang/Throwable
    //   137	147	291	java/lang/Throwable
    //   159	167	291	java/lang/Throwable
    //   201	212	291	java/lang/Throwable
    //   224	233	291	java/lang/Throwable
    //   245	253	291	java/lang/Throwable
    //   280	284	291	java/lang/Throwable
    //   26	57	324	finally
    //   26	57	328	java/lang/Throwable
    //   92	103	337	finally
    //   115	125	337	finally
    //   137	147	337	finally
    //   159	167	337	finally
    //   201	212	337	finally
    //   224	233	337	finally
    //   245	253	337	finally
    //   280	284	337	finally
    //   335	337	337	finally
    //   356	361	364	java/lang/Throwable
    //   15	20	395	java/lang/Exception
    //   316	321	395	java/lang/Exception
    //   356	361	395	java/lang/Exception
    //   370	377	395	java/lang/Exception
    //   384	389	395	java/lang/Exception
    //   393	395	395	java/lang/Exception
  }
  
  private f.m<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.j.c>> e(List<MultipartBody.Part> paramList)
    throws f.h, IOException
  {
    String str = this.c.j();
    return this.d.uploadPhotos(str, paramList).a();
  }
  
  private void i(long paramLong)
  {
    this.c.d(paramLong);
  }
  
  private void j(long paramLong)
  {
    this.c.e(paramLong);
  }
  
  private void k(long paramLong)
  {
    this.c.h(paramLong);
  }
  
  private void l(long paramLong)
  {
    this.c.i(paramLong);
  }
  
  private void m(long paramLong)
  {
    this.c.j(paramLong);
  }
  
  private List<ua.com.tim_berners.parental_control.data.models.j.b> n(long paramLong)
  {
    localArrayList = new ArrayList();
    try
    {
      ua.com.tim_berners.a.b.g[] arrayOfG = new ua.com.tim_berners.a.a.e().a(this.b, paramLong);
      if (arrayOfG != null)
      {
        int n = arrayOfG.length;
        int m = 0;
        while (m < n)
        {
          ua.com.tim_berners.a.b.g localG = arrayOfG[m];
          ua.com.tim_berners.parental_control.data.models.j.b localB = new ua.com.tim_berners.parental_control.data.models.j.b();
          localB.a(localG.a);
          localB.b(localG.b);
          localArrayList.add(localB);
          m += 1;
        }
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void o(long paramLong)
  {
    this.a.a(paramLong);
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.e.e> A(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: new 781	java/text/SimpleDateFormat
    //   42: dup
    //   43: ldc_w 796
    //   46: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   49: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   52: astore 8
    //   54: aload 8
    //   56: ldc_w 802
    //   59: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   62: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   65: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   68: astore 5
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_3
    //   73: astore_2
    //   74: aload 5
    //   76: invokevirtual 524	io/realm/dk:a	()V
    //   79: aload_3
    //   80: astore_2
    //   81: aload 4
    //   83: aload_0
    //   84: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   87: aload 5
    //   89: aload_1
    //   90: invokevirtual 530	ua/com/tim_berners/parental_control/data/b/a:d	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   93: aload 8
    //   95: aload 6
    //   97: aload 7
    //   99: invokestatic 817	ua/com/tim_berners/parental_control/data/models/e/e:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   102: aload 5
    //   104: ifnull +58 -> 162
    //   107: aload 5
    //   109: invokevirtual 517	io/realm/dk:close	()V
    //   112: aload 4
    //   114: areturn
    //   115: astore_1
    //   116: goto +8 -> 124
    //   119: astore_1
    //   120: aload_1
    //   121: astore_2
    //   122: aload_1
    //   123: athrow
    //   124: aload 5
    //   126: ifnull +29 -> 155
    //   129: aload_2
    //   130: ifnull +20 -> 150
    //   133: aload 5
    //   135: invokevirtual 517	io/realm/dk:close	()V
    //   138: goto +17 -> 155
    //   141: astore_3
    //   142: aload_2
    //   143: aload_3
    //   144: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   147: goto +8 -> 155
    //   150: aload 5
    //   152: invokevirtual 517	io/realm/dk:close	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_1
    //   158: aload_1
    //   159: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   162: aload 4
    //   164: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	b
    //   0	165	1	paramString	String
    //   73	70	2	localObject1	Object
    //   71	9	3	localObject2	Object
    //   141	3	3	localThrowable	Throwable
    //   7	156	4	localArrayList	ArrayList
    //   68	83	5	localDk	dk
    //   22	74	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	61	7	localSimpleDateFormat2	SimpleDateFormat
    //   52	42	8	localSimpleDateFormat3	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   74	79	115	finally
    //   81	102	115	finally
    //   122	124	115	finally
    //   74	79	119	java/lang/Throwable
    //   81	102	119	java/lang/Throwable
    //   133	138	141	java/lang/Throwable
    //   65	70	157	java/lang/Exception
    //   107	112	157	java/lang/Exception
    //   133	138	157	java/lang/Exception
    //   142	147	157	java/lang/Exception
    //   150	155	157	java/lang/Exception
    //   155	157	157	java/lang/Exception
  }
  
  public void A(boolean paramBoolean)
  {
    this.c.o(paramBoolean);
  }
  
  public boolean A()
  {
    return ua.com.tim_berners.parental_control.service.a.m(this.b);
  }
  
  public void B(String paramString)
  {
    this.c.g(paramString);
  }
  
  public boolean B()
  {
    boolean bool1 = this.c.M();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.q(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.j.d> C(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 5
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload 5
    //   20: invokevirtual 524	io/realm/dk:a	()V
    //   23: aload_3
    //   24: astore_2
    //   25: aload 4
    //   27: aload_1
    //   28: aload_0
    //   29: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   32: aload 5
    //   34: aload_1
    //   35: invokevirtual 830	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   38: invokestatic 835	ua/com/tim_berners/parental_control/data/models/j/d:a	(Ljava/util/ArrayList;Ljava/lang/String;Lio/realm/dy;)V
    //   41: aload 5
    //   43: ifnull +58 -> 101
    //   46: aload 5
    //   48: invokevirtual 517	io/realm/dk:close	()V
    //   51: aload 4
    //   53: areturn
    //   54: astore_1
    //   55: goto +8 -> 63
    //   58: astore_1
    //   59: aload_1
    //   60: astore_2
    //   61: aload_1
    //   62: athrow
    //   63: aload 5
    //   65: ifnull +29 -> 94
    //   68: aload_2
    //   69: ifnull +20 -> 89
    //   72: aload 5
    //   74: invokevirtual 517	io/realm/dk:close	()V
    //   77: goto +17 -> 94
    //   80: astore_3
    //   81: aload_2
    //   82: aload_3
    //   83: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   86: goto +8 -> 94
    //   89: aload 5
    //   91: invokevirtual 517	io/realm/dk:close	()V
    //   94: aload_1
    //   95: athrow
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   101: aload 4
    //   103: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	b
    //   0	104	1	paramString	String
    //   17	65	2	localObject1	Object
    //   15	9	3	localObject2	Object
    //   80	3	3	localThrowable	Throwable
    //   7	95	4	localArrayList	ArrayList
    //   12	78	5	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   18	23	54	finally
    //   25	41	54	finally
    //   61	63	54	finally
    //   18	23	58	java/lang/Throwable
    //   25	41	58	java/lang/Throwable
    //   72	77	80	java/lang/Throwable
    //   9	14	96	java/lang/Exception
    //   46	51	96	java/lang/Exception
    //   72	77	96	java/lang/Exception
    //   81	86	96	java/lang/Exception
    //   89	94	96	java/lang/Exception
    //   94	96	96	java/lang/Exception
  }
  
  public boolean C()
  {
    if (!ua.com.tim_berners.parental_control.d.x.d()) {
      return true;
    }
    boolean bool1 = this.c.N();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.d(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.c.k> D(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 5
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload 5
    //   20: invokevirtual 524	io/realm/dk:a	()V
    //   23: aload_3
    //   24: astore_2
    //   25: aload 4
    //   27: aload_0
    //   28: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   31: aload 5
    //   33: aload_1
    //   34: invokevirtual 847	ua/com/tim_berners/parental_control/data/b/a:g	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   37: aload_0
    //   38: getfield 52	ua/com/tim_berners/parental_control/data/b:e	J
    //   41: invokestatic 852	ua/com/tim_berners/parental_control/data/models/c/k:a	(Ljava/util/ArrayList;Lio/realm/dy;J)V
    //   44: aload 5
    //   46: ifnull +58 -> 104
    //   49: aload 5
    //   51: invokevirtual 517	io/realm/dk:close	()V
    //   54: aload 4
    //   56: areturn
    //   57: astore_1
    //   58: goto +8 -> 66
    //   61: astore_1
    //   62: aload_1
    //   63: astore_2
    //   64: aload_1
    //   65: athrow
    //   66: aload 5
    //   68: ifnull +29 -> 97
    //   71: aload_2
    //   72: ifnull +20 -> 92
    //   75: aload 5
    //   77: invokevirtual 517	io/realm/dk:close	()V
    //   80: goto +17 -> 97
    //   83: astore_3
    //   84: aload_2
    //   85: aload_3
    //   86: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   89: goto +8 -> 97
    //   92: aload 5
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: aload_1
    //   98: athrow
    //   99: astore_1
    //   100: aload_1
    //   101: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   104: aload 4
    //   106: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	b
    //   0	107	1	paramString	String
    //   17	68	2	localObject1	Object
    //   15	9	3	localObject2	Object
    //   83	3	3	localThrowable	Throwable
    //   7	98	4	localArrayList	ArrayList
    //   12	81	5	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   18	23	57	finally
    //   25	44	57	finally
    //   64	66	57	finally
    //   18	23	61	java/lang/Throwable
    //   25	44	61	java/lang/Throwable
    //   75	80	83	java/lang/Throwable
    //   9	14	99	java/lang/Exception
    //   49	54	99	java/lang/Exception
    //   75	80	99	java/lang/Exception
    //   84	89	99	java/lang/Exception
    //   92	97	99	java/lang/Exception
    //   97	99	99	java/lang/Exception
  }
  
  public boolean D()
  {
    return (!ua.com.tim_berners.parental_control.d.w.u(this.b)) || (this.c.O());
  }
  
  public long E()
  {
    return this.c.S();
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.c.k E(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload 4
    //   7: astore_2
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 5
    //   13: aload 5
    //   15: invokevirtual 524	io/realm/dk:a	()V
    //   18: aload_0
    //   19: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   22: aload 5
    //   24: aload_1
    //   25: invokevirtual 865	ua/com/tim_berners/parental_control/data/b/a:h	(Lio/realm/dk;Ljava/lang/String;)Lua/com/tim_berners/parental_control/data/models/c/a;
    //   28: astore_2
    //   29: aload_3
    //   30: astore_1
    //   31: aload_2
    //   32: ifnull +12 -> 44
    //   35: aload_2
    //   36: aload_0
    //   37: getfield 52	ua/com/tim_berners/parental_control/data/b:e	J
    //   40: invokestatic 868	ua/com/tim_berners/parental_control/data/models/c/k:a	(Lua/com/tim_berners/parental_control/data/models/c/a;J)Lua/com/tim_berners/parental_control/data/models/c/k;
    //   43: astore_1
    //   44: aload_1
    //   45: astore_2
    //   46: aload 5
    //   48: ifnull +74 -> 122
    //   51: aload_1
    //   52: astore_2
    //   53: aload 5
    //   55: invokevirtual 517	io/realm/dk:close	()V
    //   58: aload_1
    //   59: areturn
    //   60: astore_3
    //   61: aconst_null
    //   62: astore_1
    //   63: goto +7 -> 70
    //   66: astore_1
    //   67: aload_1
    //   68: athrow
    //   69: astore_3
    //   70: aload 5
    //   72: ifnull +40 -> 112
    //   75: aload_1
    //   76: ifnull +28 -> 104
    //   79: aload 4
    //   81: astore_2
    //   82: aload 5
    //   84: invokevirtual 517	io/realm/dk:close	()V
    //   87: goto +25 -> 112
    //   90: astore 5
    //   92: aload 4
    //   94: astore_2
    //   95: aload_1
    //   96: aload 5
    //   98: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   101: goto +11 -> 112
    //   104: aload 4
    //   106: astore_2
    //   107: aload 5
    //   109: invokevirtual 517	io/realm/dk:close	()V
    //   112: aload 4
    //   114: astore_2
    //   115: aload_3
    //   116: athrow
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   122: aload_2
    //   123: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	b
    //   0	124	1	paramString	String
    //   7	116	2	localObject1	Object
    //   4	26	3	localObject2	Object
    //   60	1	3	localObject3	Object
    //   69	47	3	localObject4	Object
    //   1	112	4	localObject5	Object
    //   11	72	5	localDk	dk
    //   90	18	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   13	29	60	finally
    //   35	44	60	finally
    //   13	29	66	java/lang/Throwable
    //   35	44	66	java/lang/Throwable
    //   67	69	69	finally
    //   82	87	90	java/lang/Throwable
    //   8	13	117	java/lang/Exception
    //   53	58	117	java/lang/Exception
    //   82	87	117	java/lang/Exception
    //   95	101	117	java/lang/Exception
    //   107	112	117	java/lang/Exception
    //   115	117	117	java/lang/Exception
  }
  
  public io.b.b F(String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString != null) {
      localM.a("push_token", paramString);
    }
    V("dt_mng | upl_psh_tok");
    return this.d.refreshTokenFirebase(str, localM);
  }
  
  public boolean F()
  {
    return this.c.ac();
  }
  
  public String G()
  {
    return this.c.ai();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.d.b> G(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   42: astore 5
    //   44: aconst_null
    //   45: astore_3
    //   46: aload_3
    //   47: astore_2
    //   48: aload 5
    //   50: invokevirtual 524	io/realm/dk:a	()V
    //   53: aload_3
    //   54: astore_2
    //   55: aload 4
    //   57: aload_0
    //   58: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   61: aload 5
    //   63: aload_1
    //   64: invokevirtual 884	ua/com/tim_berners/parental_control/data/b/a:i	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   67: aload 6
    //   69: aload 7
    //   71: invokestatic 889	ua/com/tim_berners/parental_control/data/models/d/b:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   74: aload 5
    //   76: ifnull +58 -> 134
    //   79: aload 5
    //   81: invokevirtual 517	io/realm/dk:close	()V
    //   84: aload 4
    //   86: areturn
    //   87: astore_1
    //   88: goto +8 -> 96
    //   91: astore_1
    //   92: aload_1
    //   93: astore_2
    //   94: aload_1
    //   95: athrow
    //   96: aload 5
    //   98: ifnull +29 -> 127
    //   101: aload_2
    //   102: ifnull +20 -> 122
    //   105: aload 5
    //   107: invokevirtual 517	io/realm/dk:close	()V
    //   110: goto +17 -> 127
    //   113: astore_3
    //   114: aload_2
    //   115: aload_3
    //   116: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   119: goto +8 -> 127
    //   122: aload 5
    //   124: invokevirtual 517	io/realm/dk:close	()V
    //   127: aload_1
    //   128: athrow
    //   129: astore_1
    //   130: aload_1
    //   131: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   134: aload 4
    //   136: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	b
    //   0	137	1	paramString	String
    //   47	68	2	localObject1	Object
    //   45	9	3	localObject2	Object
    //   113	3	3	localThrowable	Throwable
    //   7	128	4	localArrayList	ArrayList
    //   42	81	5	localDk	dk
    //   22	46	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	33	7	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   48	53	87	finally
    //   55	74	87	finally
    //   94	96	87	finally
    //   48	53	91	java/lang/Throwable
    //   55	74	91	java/lang/Throwable
    //   105	110	113	java/lang/Throwable
    //   39	44	129	java/lang/Exception
    //   79	84	129	java/lang/Exception
    //   105	110	129	java/lang/Exception
    //   114	119	129	java/lang/Exception
    //   122	127	129	java/lang/Exception
    //   127	129	129	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.d>> H(@NonNull String paramString)
  {
    String str = this.c.j();
    V("dt_mng | dwnl_act");
    return this.d.getActions(str, paramString).a(new bc(this)).b(new bd(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public String H()
  {
    return this.c.aj();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> I(String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("device_id", paramString);
      localM.a("type", "phone");
      localM.a("action", "unlock");
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public boolean I()
  {
    return ua.com.tim_berners.parental_control.d.w.u(this.b);
  }
  
  public String J()
  {
    return this.c.i();
  }
  
  public void J(String paramString)
  {
    this.c.l(paramString);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> K(String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    com.google.gson.m localM2 = ua.com.tim_berners.parental_control.d.z.k(this.b);
    if (localM2 != null)
    {
      com.google.gson.m localM3 = new com.google.gson.m();
      localM2.a("tracker_attribution", this.c.T());
      localM2.a("tracker_device_id", this.c.U());
      localM2.a("tracker_is_gathered", Boolean.valueOf(this.c.V()));
      localM2.a("tracker", localM3);
      localM1.a("technical_data", localM2);
    }
    V("dt_mng | upl_tec");
    return this.d.changeMyDeviceTechnicalData(str, paramString, localM1).a(new be(this)).b(new bf(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public String K()
  {
    return this.c.m();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.common.h>> L()
  {
    V("dt_mng | rst_pin");
    return this.d.restorePinCode(this.c.j()).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> L(@Nullable String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString != null) {
      localM.a("purchase_token", paramString);
    }
    localM.a("product_id", "ua.com.tim_berners.parental_control.annual.one_year_subscription");
    return this.d.paymentInApp(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.b M()
  {
    String str = this.c.j();
    V("dt_mng | lgout");
    return this.d.logout(str).c(new z(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public String M(String paramString)
  {
    return this.a.a(paramString);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<List<Device>>> N()
  {
    String str = this.c.j();
    return this.d.getAllDevices(str).a(new av(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void N(String paramString)
  {
    this.a.b(paramString);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.a.a>> O()
  {
    String str = this.c.j();
    long l = ai("my_info");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return this.d.getMyInfo(str, l).a(new bq(this, localSimpleDateFormat, "my_info")).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.g.c>> O(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("news_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!aj(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadNewsList(str, paramString, l).a(new bj(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.g.d> P(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 794
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 796
    //   31: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: aload 7
    //   41: ldc_w 802
    //   44: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   47: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   50: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   53: astore 5
    //   55: aconst_null
    //   56: astore_3
    //   57: aload_3
    //   58: astore_2
    //   59: aload 5
    //   61: invokevirtual 524	io/realm/dk:a	()V
    //   64: aload_3
    //   65: astore_2
    //   66: aload 4
    //   68: aload_0
    //   69: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   72: aload 5
    //   74: aload_1
    //   75: invokevirtual 526	ua/com/tim_berners/parental_control/data/b/a:o	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   78: aload 7
    //   80: aload 6
    //   82: invokestatic 1046	ua/com/tim_berners/parental_control/data/models/g/d:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   85: aload 5
    //   87: ifnull +58 -> 145
    //   90: aload 5
    //   92: invokevirtual 517	io/realm/dk:close	()V
    //   95: aload 4
    //   97: areturn
    //   98: astore_1
    //   99: goto +8 -> 107
    //   102: astore_1
    //   103: aload_1
    //   104: astore_2
    //   105: aload_1
    //   106: athrow
    //   107: aload 5
    //   109: ifnull +29 -> 138
    //   112: aload_2
    //   113: ifnull +20 -> 133
    //   116: aload 5
    //   118: invokevirtual 517	io/realm/dk:close	()V
    //   121: goto +17 -> 138
    //   124: astore_3
    //   125: aload_2
    //   126: aload_3
    //   127: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   130: goto +8 -> 138
    //   133: aload 5
    //   135: invokevirtual 517	io/realm/dk:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_1
    //   141: aload_1
    //   142: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   145: aload 4
    //   147: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	b
    //   0	148	1	paramString	String
    //   58	68	2	localObject1	Object
    //   56	9	3	localObject2	Object
    //   124	3	3	localThrowable	Throwable
    //   7	139	4	localArrayList	ArrayList
    //   53	81	5	localDk	dk
    //   22	59	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	42	7	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   59	64	98	finally
    //   66	85	98	finally
    //   105	107	98	finally
    //   59	64	102	java/lang/Throwable
    //   66	85	102	java/lang/Throwable
    //   116	121	124	java/lang/Throwable
    //   50	55	140	java/lang/Exception
    //   90	95	140	java/lang/Exception
    //   116	121	140	java/lang/Exception
    //   125	130	140	java/lang/Exception
    //   133	138	140	java/lang/Exception
    //   138	140	140	java/lang/Exception
  }
  
  public boolean P()
  {
    return DateUtils.isToday(bx() * 1000L) ^ true;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.a.d>> Q()
  {
    String str = this.c.j();
    long l = ai("my_account");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return this.d.getMyAccount(str, l).a(new g(this, localSimpleDateFormat, "my_account")).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.l.l>> Q(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("social_logs_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ae(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadSocialLogList(str, paramString, l).a(new bo(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> R(String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("is_real_push", Integer.valueOf(0));
      localM1.a("device_id", paramString);
      localM1.a("type", "all");
      localM1.a("action", "refresh");
      localM1.a("data", localM2);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.a.e R()
  {
    // Byte code:
    //   0: new 781	java/text/SimpleDateFormat
    //   3: dup
    //   4: ldc_w 796
    //   7: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   10: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   13: astore_3
    //   14: new 781	java/text/SimpleDateFormat
    //   17: dup
    //   18: ldc_w 796
    //   21: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   24: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   27: astore 6
    //   29: aload 6
    //   31: ldc_w 802
    //   34: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   37: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   40: aconst_null
    //   41: astore 4
    //   43: aconst_null
    //   44: astore_1
    //   45: aload 4
    //   47: astore_2
    //   48: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   51: astore 5
    //   53: aload 5
    //   55: invokevirtual 524	io/realm/dk:a	()V
    //   58: aload_0
    //   59: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   62: aload 5
    //   64: invokevirtual 1089	ua/com/tim_berners/parental_control/data/b/a:e	(Lio/realm/dk;)Lua/com/tim_berners/parental_control/data/models/a/d;
    //   67: astore_2
    //   68: aload_2
    //   69: ifnull +11 -> 80
    //   72: aload_2
    //   73: aload_3
    //   74: aload 6
    //   76: invokestatic 1092	ua/com/tim_berners/parental_control/data/models/a/e:a	(Lua/com/tim_berners/parental_control/data/models/a/d;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)Lua/com/tim_berners/parental_control/data/models/a/e;
    //   79: astore_1
    //   80: aload_1
    //   81: astore_2
    //   82: aload 5
    //   84: ifnull +74 -> 158
    //   87: aload_1
    //   88: astore_2
    //   89: aload 5
    //   91: invokevirtual 517	io/realm/dk:close	()V
    //   94: aload_1
    //   95: areturn
    //   96: astore_1
    //   97: aconst_null
    //   98: astore_3
    //   99: goto +7 -> 106
    //   102: astore_3
    //   103: aload_3
    //   104: athrow
    //   105: astore_1
    //   106: aload 5
    //   108: ifnull +40 -> 148
    //   111: aload_3
    //   112: ifnull +28 -> 140
    //   115: aload 4
    //   117: astore_2
    //   118: aload 5
    //   120: invokevirtual 517	io/realm/dk:close	()V
    //   123: goto +25 -> 148
    //   126: astore 5
    //   128: aload 4
    //   130: astore_2
    //   131: aload_3
    //   132: aload 5
    //   134: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   137: goto +11 -> 148
    //   140: aload 4
    //   142: astore_2
    //   143: aload 5
    //   145: invokevirtual 517	io/realm/dk:close	()V
    //   148: aload 4
    //   150: astore_2
    //   151: aload_1
    //   152: athrow
    //   153: astore_1
    //   154: aload_1
    //   155: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   158: aload_2
    //   159: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	b
    //   44	51	1	localE	ua.com.tim_berners.parental_control.data.models.a.e
    //   96	1	1	localObject1	Object
    //   105	47	1	localObject2	Object
    //   153	2	1	localException	Exception
    //   47	112	2	localObject3	Object
    //   13	86	3	localSimpleDateFormat1	SimpleDateFormat
    //   102	30	3	localThrowable1	Throwable
    //   41	108	4	localObject4	Object
    //   51	68	5	localDk	dk
    //   126	18	5	localThrowable2	Throwable
    //   27	48	6	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   53	68	96	finally
    //   72	80	96	finally
    //   53	68	102	java/lang/Throwable
    //   72	80	102	java/lang/Throwable
    //   103	105	105	finally
    //   118	123	126	java/lang/Throwable
    //   48	53	153	java/lang/Exception
    //   89	94	153	java/lang/Exception
    //   118	123	153	java/lang/Exception
    //   131	137	153	java/lang/Exception
    //   143	148	153	java/lang/Exception
    //   151	153	153	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.permission.e>> S()
  {
    String str1 = this.c.j();
    ua.com.tim_berners.parental_control.data.models.permission.c localC = T();
    String str2 = this.c.h();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("admin", Boolean.valueOf(localC.m()));
      localM2.a("contacts", Boolean.valueOf(localC.c()));
      localM2.a("calls", Boolean.valueOf(localC.d()));
      localM2.a("sms", Boolean.valueOf(localC.e()));
      localM2.a("photos", Boolean.valueOf(localC.f()));
      localM2.a("locations", Boolean.valueOf(localC.g()));
      localM2.a("app", Boolean.valueOf(localC.h()));
      localM2.a("app_usage", Boolean.valueOf(localC.i()));
      localM2.a("phone", Boolean.valueOf(localC.j()));
      localM2.a("notifications", Boolean.valueOf(localC.k()));
      localM2.a("location_service", Boolean.valueOf(localC.n()));
      localM2.a("gps_service", Boolean.valueOf(localC.o()));
      localM2.a("google_service", Boolean.valueOf(localC.r()));
      localM2.a("ignore_battery_optimization", Boolean.valueOf(localC.s()));
      localM2.a("location_support", Boolean.valueOf(localC.v()));
      localM2.a("protected_apps", Boolean.valueOf(localC.t()));
      localM2.a("oppo_energy_saver", Boolean.valueOf(u()));
      localM2.a("zte_energy_saver", Boolean.valueOf(C()));
      localM2.a("xiaomi_permission", Boolean.valueOf(D()));
      localM2.a("xiaomi_permission", Boolean.valueOf(aI()));
      localM2.a("automatic_time_zone", Boolean.valueOf(localC.p()));
      localM2.a("mobile_internet", Boolean.valueOf(localC.q()));
      localM2.a("auto_start", Boolean.valueOf(localC.u()));
      localM2.a("draw_overlays", Boolean.valueOf(localC.w()));
      localM1.a("permissions", localM2);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    V("dt_mng | upl_per");
    return this.d.uploadPermissions(str1, localM1).a(new j(this, str2)).b(new k(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.m.h>> S(@NonNull String paramString)
  {
    String str = this.c.j();
    return this.d.downloadWebUrlList(str, paramString).a(new bs(this, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.m.c>> T(@NonNull String paramString)
  {
    String str = this.c.j();
    return this.d.downloadWebCategoryList(str, paramString).a(new bt(this, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public ua.com.tim_berners.parental_control.data.models.permission.c T()
  {
    ua.com.tim_berners.parental_control.data.models.permission.c localC = new ua.com.tim_berners.parental_control.data.models.permission.c();
    localC.j(ar());
    localC.g(au());
    localC.a(ax());
    localC.b(ay());
    localC.c(az());
    localC.d(aw());
    localC.e(aB());
    localC.f(at());
    localC.h(at());
    localC.i(aC());
    localC.k(aD());
    localC.l(bN());
    localC.n(bO());
    localC.o(aE());
    localC.p(aG());
    localC.s(aA());
    localC.r(aI());
    localC.q(aH());
    localC.m(bM());
    localC.t(as());
    return localC;
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.m.d> U(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 5
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload 5
    //   20: invokevirtual 524	io/realm/dk:a	()V
    //   23: aload_3
    //   24: astore_2
    //   25: aload 4
    //   27: aload_0
    //   28: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   31: aload 5
    //   33: aload_1
    //   34: invokevirtual 1308	ua/com/tim_berners/parental_control/data/b/a:r	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   37: invokestatic 1313	ua/com/tim_berners/parental_control/data/models/m/d:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   40: aload 5
    //   42: ifnull +58 -> 100
    //   45: aload 5
    //   47: invokevirtual 517	io/realm/dk:close	()V
    //   50: aload 4
    //   52: areturn
    //   53: astore_1
    //   54: goto +8 -> 62
    //   57: astore_1
    //   58: aload_1
    //   59: astore_2
    //   60: aload_1
    //   61: athrow
    //   62: aload 5
    //   64: ifnull +29 -> 93
    //   67: aload_2
    //   68: ifnull +20 -> 88
    //   71: aload 5
    //   73: invokevirtual 517	io/realm/dk:close	()V
    //   76: goto +17 -> 93
    //   79: astore_3
    //   80: aload_2
    //   81: aload_3
    //   82: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   85: goto +8 -> 93
    //   88: aload 5
    //   90: invokevirtual 517	io/realm/dk:close	()V
    //   93: aload_1
    //   94: athrow
    //   95: astore_1
    //   96: aload_1
    //   97: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   100: aload 4
    //   102: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	b
    //   0	103	1	paramString	String
    //   17	64	2	localObject1	Object
    //   15	9	3	localObject2	Object
    //   79	3	3	localThrowable	Throwable
    //   7	94	4	localArrayList	ArrayList
    //   12	77	5	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   18	23	53	finally
    //   25	40	53	finally
    //   60	62	53	finally
    //   18	23	57	java/lang/Throwable
    //   25	40	57	java/lang/Throwable
    //   71	76	79	java/lang/Throwable
    //   9	14	95	java/lang/Exception
    //   45	50	95	java/lang/Exception
    //   71	76	95	java/lang/Exception
    //   80	85	95	java/lang/Exception
    //   88	93	95	java/lang/Exception
    //   93	95	95	java/lang/Exception
  }
  
  /* Error */
  public boolean U()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 534	ua/com/tim_berners/parental_control/data/b:an	()Ljava/lang/String;
    //   4: astore 10
    //   6: iconst_0
    //   7: istore 4
    //   9: iconst_0
    //   10: istore_3
    //   11: iload 4
    //   13: istore_2
    //   14: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   17: astore 8
    //   19: aconst_null
    //   20: astore 7
    //   22: aload 7
    //   24: astore 6
    //   26: aload 8
    //   28: invokevirtual 524	io/realm/dk:a	()V
    //   31: aload 7
    //   33: astore 6
    //   35: aload_0
    //   36: invokevirtual 1095	ua/com/tim_berners/parental_control/data/b:T	()Lua/com/tim_berners/parental_control/data/models/permission/c;
    //   39: astore 9
    //   41: aload 7
    //   43: astore 6
    //   45: aload_0
    //   46: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   49: aload 8
    //   51: aload 10
    //   53: invokestatic 1317	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   56: invokevirtual 1320	java/lang/Integer:intValue	()I
    //   59: invokevirtual 1323	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;I)Lua/com/tim_berners/parental_control/data/models/device/Device;
    //   62: invokevirtual 1327	ua/com/tim_berners/parental_control/data/models/device/Device:y	()Lua/com/tim_berners/parental_control/data/models/permission/SettingsModel;
    //   65: invokevirtual 1332	ua/com/tim_berners/parental_control/data/models/permission/SettingsModel:c	()Lua/com/tim_berners/parental_control/data/models/permission/PermissionModel;
    //   68: astore 10
    //   70: iload_3
    //   71: istore_1
    //   72: aload 9
    //   74: ifnull +296 -> 370
    //   77: iload_3
    //   78: istore_1
    //   79: aload 10
    //   81: ifnull +289 -> 370
    //   84: aload 7
    //   86: astore 6
    //   88: aload 9
    //   90: invokevirtual 1101	ua/com/tim_berners/parental_control/data/models/permission/c:m	()Z
    //   93: aload 10
    //   95: invokevirtual 1335	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:c	()Z
    //   98: if_icmpne +270 -> 368
    //   101: aload 7
    //   103: astore 6
    //   105: aload 9
    //   107: invokevirtual 1122	ua/com/tim_berners/parental_control/data/models/permission/c:h	()Z
    //   110: aload 10
    //   112: invokevirtual 1336	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:d	()Z
    //   115: if_icmpne +253 -> 368
    //   118: aload 7
    //   120: astore 6
    //   122: aload 9
    //   124: invokevirtual 1105	ua/com/tim_berners/parental_control/data/models/permission/c:c	()Z
    //   127: aload 10
    //   129: invokevirtual 1337	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:h	()Z
    //   132: if_icmpne +236 -> 368
    //   135: aload 7
    //   137: astore 6
    //   139: aload 9
    //   141: invokevirtual 1108	ua/com/tim_berners/parental_control/data/models/permission/c:d	()Z
    //   144: aload 10
    //   146: invokevirtual 1338	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:g	()Z
    //   149: if_icmpne +219 -> 368
    //   152: aload 7
    //   154: astore 6
    //   156: aload 9
    //   158: invokevirtual 1112	ua/com/tim_berners/parental_control/data/models/permission/c:e	()Z
    //   161: aload 10
    //   163: invokevirtual 1339	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:j	()Z
    //   166: if_icmpne +202 -> 368
    //   169: aload 7
    //   171: astore 6
    //   173: aload 9
    //   175: invokevirtual 1125	ua/com/tim_berners/parental_control/data/models/permission/c:i	()Z
    //   178: aload 10
    //   180: invokevirtual 1340	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:e	()Z
    //   183: if_icmpne +185 -> 368
    //   186: aload 7
    //   188: astore 6
    //   190: aload 9
    //   192: invokevirtual 1116	ua/com/tim_berners/parental_control/data/models/permission/c:f	()Z
    //   195: aload 10
    //   197: invokevirtual 1341	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:i	()Z
    //   200: if_icmpne +168 -> 368
    //   203: aload 7
    //   205: astore 6
    //   207: aload 9
    //   209: invokevirtual 1118	ua/com/tim_berners/parental_control/data/models/permission/c:g	()Z
    //   212: aload 10
    //   214: invokevirtual 1342	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:k	()Z
    //   217: if_icmpne +151 -> 368
    //   220: aload 7
    //   222: astore 6
    //   224: aload 9
    //   226: invokevirtual 1127	ua/com/tim_berners/parental_control/data/models/permission/c:j	()Z
    //   229: aload 10
    //   231: invokevirtual 1343	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:f	()Z
    //   234: if_icmpne +134 -> 368
    //   237: aload 7
    //   239: astore 6
    //   241: aload 9
    //   243: invokevirtual 1131	ua/com/tim_berners/parental_control/data/models/permission/c:k	()Z
    //   246: aload 10
    //   248: invokevirtual 1344	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:m	()Z
    //   251: if_icmpne +117 -> 368
    //   254: aload 7
    //   256: astore 6
    //   258: aload 9
    //   260: invokevirtual 1135	ua/com/tim_berners/parental_control/data/models/permission/c:n	()Z
    //   263: aload 10
    //   265: invokevirtual 1345	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:n	()Z
    //   268: if_icmpne +100 -> 368
    //   271: aload 7
    //   273: astore 6
    //   275: aload 9
    //   277: invokevirtual 1139	ua/com/tim_berners/parental_control/data/models/permission/c:o	()Z
    //   280: aload 10
    //   282: invokevirtual 1346	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:o	()Z
    //   285: if_icmpne +83 -> 368
    //   288: aload 7
    //   290: astore 6
    //   292: aload 9
    //   294: invokevirtual 1143	ua/com/tim_berners/parental_control/data/models/permission/c:r	()Z
    //   297: aload 10
    //   299: invokevirtual 1347	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:r	()Z
    //   302: if_icmpne +66 -> 368
    //   305: aload 7
    //   307: astore 6
    //   309: aload 9
    //   311: invokevirtual 1185	ua/com/tim_berners/parental_control/data/models/permission/c:w	()Z
    //   314: aload 10
    //   316: invokevirtual 1348	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:v	()Z
    //   319: if_icmpne +49 -> 368
    //   322: aload 7
    //   324: astore 6
    //   326: aload 9
    //   328: invokevirtual 1147	ua/com/tim_berners/parental_control/data/models/permission/c:s	()Z
    //   331: aload 10
    //   333: invokevirtual 1349	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:s	()Z
    //   336: if_icmpne +32 -> 368
    //   339: aload 7
    //   341: astore 6
    //   343: aload 9
    //   345: invokevirtual 1151	ua/com/tim_berners/parental_control/data/models/permission/c:v	()Z
    //   348: istore_2
    //   349: aload 7
    //   351: astore 6
    //   353: aload 10
    //   355: invokevirtual 1350	ua/com/tim_berners/parental_control/data/models/permission/PermissionModel:u	()Z
    //   358: istore 5
    //   360: iload_3
    //   361: istore_1
    //   362: iload_2
    //   363: iload 5
    //   365: if_icmpeq +5 -> 370
    //   368: iconst_1
    //   369: istore_1
    //   370: iload_1
    //   371: istore_2
    //   372: aload 8
    //   374: ifnull +83 -> 457
    //   377: iload_1
    //   378: istore_2
    //   379: aload 8
    //   381: invokevirtual 517	io/realm/dk:close	()V
    //   384: iload_1
    //   385: ireturn
    //   386: astore 7
    //   388: goto +12 -> 400
    //   391: astore 7
    //   393: aload 7
    //   395: astore 6
    //   397: aload 7
    //   399: athrow
    //   400: aload 8
    //   402: ifnull +42 -> 444
    //   405: aload 6
    //   407: ifnull +29 -> 436
    //   410: iload 4
    //   412: istore_2
    //   413: aload 8
    //   415: invokevirtual 517	io/realm/dk:close	()V
    //   418: goto +26 -> 444
    //   421: astore 8
    //   423: iload 4
    //   425: istore_2
    //   426: aload 6
    //   428: aload 8
    //   430: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   433: goto +11 -> 444
    //   436: iload 4
    //   438: istore_2
    //   439: aload 8
    //   441: invokevirtual 517	io/realm/dk:close	()V
    //   444: iload 4
    //   446: istore_2
    //   447: aload 7
    //   449: athrow
    //   450: astore 6
    //   452: aload 6
    //   454: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   457: iload_2
    //   458: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	459	0	this	b
    //   71	314	1	bool1	boolean
    //   13	445	2	bool2	boolean
    //   10	351	3	bool3	boolean
    //   7	438	4	bool4	boolean
    //   358	8	5	bool5	boolean
    //   24	403	6	localObject1	Object
    //   450	3	6	localException	Exception
    //   20	330	7	localObject2	Object
    //   386	1	7	localObject3	Object
    //   391	57	7	localThrowable1	Throwable
    //   17	397	8	localDk	dk
    //   421	19	8	localThrowable2	Throwable
    //   39	305	9	localC	ua.com.tim_berners.parental_control.data.models.permission.c
    //   4	350	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   26	31	386	finally
    //   35	41	386	finally
    //   45	70	386	finally
    //   88	101	386	finally
    //   105	118	386	finally
    //   122	135	386	finally
    //   139	152	386	finally
    //   156	169	386	finally
    //   173	186	386	finally
    //   190	203	386	finally
    //   207	220	386	finally
    //   224	237	386	finally
    //   241	254	386	finally
    //   258	271	386	finally
    //   275	288	386	finally
    //   292	305	386	finally
    //   309	322	386	finally
    //   326	339	386	finally
    //   343	349	386	finally
    //   353	360	386	finally
    //   397	400	386	finally
    //   26	31	391	java/lang/Throwable
    //   35	41	391	java/lang/Throwable
    //   45	70	391	java/lang/Throwable
    //   88	101	391	java/lang/Throwable
    //   105	118	391	java/lang/Throwable
    //   122	135	391	java/lang/Throwable
    //   139	152	391	java/lang/Throwable
    //   156	169	391	java/lang/Throwable
    //   173	186	391	java/lang/Throwable
    //   190	203	391	java/lang/Throwable
    //   207	220	391	java/lang/Throwable
    //   224	237	391	java/lang/Throwable
    //   241	254	391	java/lang/Throwable
    //   258	271	391	java/lang/Throwable
    //   275	288	391	java/lang/Throwable
    //   292	305	391	java/lang/Throwable
    //   309	322	391	java/lang/Throwable
    //   326	339	391	java/lang/Throwable
    //   343	349	391	java/lang/Throwable
    //   353	360	391	java/lang/Throwable
    //   413	418	421	java/lang/Throwable
    //   14	19	450	java/lang/Exception
    //   379	384	450	java/lang/Exception
    //   413	418	450	java/lang/Exception
    //   426	433	450	java/lang/Exception
    //   439	444	450	java/lang/Exception
    //   447	450	450	java/lang/Exception
  }
  
  public io.b.q<List<ua.com.tim_berners.parental_control.data.models.e.a>> V()
  {
    return io.b.q.a(new l(this));
  }
  
  public void V(String paramString)
  {
    this.a.a(ah(paramString));
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.d>> W()
  {
    String str = this.c.j();
    return this.d.getAppsLimits(str).a(new s(this)).b(new t(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.l.f>> W(@NonNull String paramString)
  {
    String str = this.c.j();
    return this.d.downloadMessengerChatList(str, paramString).a(new cg(this, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.d>> X()
  {
    String str1 = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    com.google.gson.g localG1 = new com.google.gson.g();
    long l2 = bw();
    long l3 = l2;
    for (;;)
    {
      long l1;
      try
      {
        Object localObject1 = new ua.com.tim_berners.a.a.a(this.b).a(l2);
        l3 = l2;
        Object localObject2 = new StringBuilder();
        l3 = l2;
        ((StringBuilder)localObject2).append("dt_mng | upl_app_log | f = ");
        if (localObject1 == null) {
          break label593;
        }
        l3 = l2;
        m = ((Map)localObject1).size();
        l3 = l2;
        ((StringBuilder)localObject2).append(m);
        l3 = l2;
        V(((StringBuilder)localObject2).toString());
        l1 = l2;
        if (localObject1 != null)
        {
          l3 = l2;
          localObject1 = ((Map)localObject1).entrySet().iterator();
          l1 = l2;
          l3 = l1;
          try
          {
            if (((Iterator)localObject1).hasNext())
            {
              l3 = l1;
              localObject2 = (Map.Entry)((Iterator)localObject1).next();
              l3 = l1;
              String str2 = (String)((Map.Entry)localObject2).getKey();
              l3 = l1;
              if (this.j == null) {
                break label598;
              }
              l3 = l1;
              if (!this.j.equals(str2)) {
                break label598;
              }
              m = 1;
              l3 = l1;
              if ((ua.com.tim_berners.parental_control.d.a.b(str2)) && (m == 0))
              {
                l3 = l1;
                localObject2 = (ua.com.tim_berners.a.b.a)((Map.Entry)localObject2).getValue();
                l3 = l1;
                com.google.gson.g localG2 = new com.google.gson.g();
                l3 = l1;
                localObject2 = ((ua.com.tim_berners.a.b.a)localObject2).b.iterator();
                try
                {
                  ua.com.tim_berners.a.b.b localB;
                  com.google.gson.m localM2;
                  if (((Iterator)localObject2).hasNext())
                  {
                    localB = (ua.com.tim_berners.a.b.b)((Iterator)localObject2).next();
                    localM2 = new com.google.gson.m();
                    localM2.a("duration", Long.valueOf(localB.a));
                    localM2.a("start_time", Long.valueOf(localB.b));
                    l3 = localB.b;
                  }
                  try
                  {
                    long l4 = Math.max(l2, l3 + localB.a);
                    l3 = l4;
                    localG2.a(localM2);
                    l1 = l4;
                  }
                  catch (Exception localException1)
                  {
                    continue;
                  }
                  localObject2 = new com.google.gson.m();
                  ((com.google.gson.m)localObject2).a("package_name", str2);
                  ((com.google.gson.m)localObject2).a("usage", localG2);
                  localG1.a((com.google.gson.j)localObject2);
                }
                catch (Exception localException2)
                {
                  continue;
                }
              }
              continue;
            }
          }
          catch (Exception localException3)
          {
            l1 = l3;
            continue;
          }
        }
        l3 = l1;
        if (localG1.a() == 0)
        {
          l3 = l1;
          return io.b.q.a(v.a);
        }
        l3 = l1;
        localM1.a("apps_log", localG1);
      }
      catch (Exception localException4)
      {
        l1 = l3;
        localException4.printStackTrace();
      }
      localM1.a("apps_log", localG1);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("dt_mng | upl_app_log | f = ");
      localStringBuilder.append(localG1.a());
      V(localStringBuilder.toString());
      return this.d.uploadAppsLog(str1, localM1).a(new w(this, l1)).b(new x(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
      label593:
      int m = 0;
      continue;
      label598:
      m = 0;
    }
  }
  
  /* Error */
  public boolean X(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 734	ua/com/tim_berners/parental_control/data/b/a:f	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.e>> Y()
  {
    boolean bool1 = bD();
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    com.google.gson.g localG = new com.google.gson.g();
    C(false);
    try
    {
      Object localObject1 = bJ();
      boolean bool2 = d((List)localObject1);
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | upl_app | f = ");
      ((StringBuilder)localObject2).append(((List)localObject1).size());
      ((StringBuilder)localObject2).append(" | chg = ");
      ((StringBuilder)localObject2).append(bool2);
      V(((StringBuilder)localObject2).toString());
      if (!bool2) {
        return io.b.q.a(ab.a);
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ua.com.tim_berners.parental_control.data.models.c.a)((Iterator)localObject1).next();
        com.google.gson.m localM2 = new com.google.gson.m();
        localM2.a("app_package", ((ua.com.tim_berners.parental_control.data.models.c.a)localObject2).n());
        localM2.a("app_name", ((ua.com.tim_berners.parental_control.data.models.c.a)localObject2).p());
        localG.a(localM2);
      }
      if (localG.a() == 0)
      {
        localObject1 = io.b.q.a(ac.a);
        return localObject1;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      localM1.a("is_first_sync", Boolean.valueOf(bool1));
      localM1.a("apps", localG);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("dt_mng | upl_app | f = ");
      localStringBuilder.append(localG.a());
      V(localStringBuilder.toString());
    }
    return this.d.uploadApps(str, localM1).a(new ad(this)).b(new ae(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public boolean Y(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 884	ua/com/tim_berners/parental_control/data/b/a:i	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.d.c>> Z()
  {
    boolean bool = bF();
    long l = by();
    com.google.gson.m localM = new com.google.gson.m();
    String str = this.c.j();
    E(false);
    com.google.gson.g localG = new com.google.gson.g();
    List localList = new ua.com.tim_berners.a.a.b(this.b).a(l);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("dt_mng | upl_cal = ");
    int m;
    if (localList != null) {
      m = localList.size();
    } else {
      m = 0;
    }
    ((StringBuilder)localObject1).append(m);
    ((StringBuilder)localObject1).append(" | t ");
    ((StringBuilder)localObject1).append(l);
    V(((StringBuilder)localObject1).toString());
    if ((localList != null) && (localList.size() != 0)) {
      try
      {
        Iterator localIterator = localList.iterator();
        Object localObject2;
        while (localIterator.hasNext())
        {
          ua.com.tim_berners.a.b.c localC = (ua.com.tim_berners.a.b.c)localIterator.next();
          localObject2 = localC.b;
          localObject1 = localObject2;
          if (localC.e != null)
          {
            localObject1 = localObject2;
            if (localC.e.equals("")) {
              localObject1 = "No Name";
            }
          }
          localObject2 = new com.google.gson.m();
          if (localC.a != null) {
            ((com.google.gson.m)localObject2).a("call_identifier", localC.a);
          }
          if (localObject1 != null) {
            ((com.google.gson.m)localObject2).a("name", (String)localObject1);
          }
          if (localC.e != null) {
            ((com.google.gson.m)localObject2).a("number", localC.e);
          }
          ((com.google.gson.m)localObject2).a("call_date", Long.valueOf(localC.c));
          ((com.google.gson.m)localObject2).a("type", Integer.valueOf(localC.f));
          ((com.google.gson.m)localObject2).a("duration", Integer.valueOf((int)localC.d));
          localG.a((com.google.gson.j)localObject2);
        }
        StringBuilder localStringBuilder;
        return io.b.q.a(ag.a);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("dt_mng | upl_cal_cth | e = ");
        ((StringBuilder)localObject2).append(localException.toString());
        V(((StringBuilder)localObject2).toString());
        l = ((ua.com.tim_berners.a.b.c)localList.get(0)).c;
        localM.a("is_first_sync", Boolean.valueOf(bool));
        localM.a("calls", localG);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("dt_mng | upl_cal | f = ");
        localStringBuilder.append(localG.a());
        V(localStringBuilder.toString());
        return this.d.uploadCalls(str, localM).a(new ah(this, l)).b(new ai(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
      }
    }
  }
  
  /* Error */
  public boolean Z(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 884	ua/com/tim_berners/parental_control/data/b/a:i	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.b a(String paramString1, String paramString2, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Object localObject = new JSONObject();
        ((JSONObject)localObject).put("url", paramString1);
        ua.com.tim_berners.parental_control.data.b.a localA = this.a;
        localObject = ((JSONObject)localObject).toString();
        if (!paramBoolean) {
          break label70;
        }
        paramString1 = "blocked";
        localA.a("url", paramString2, (String)localObject, paramString1);
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
      return io.b.b.a(bp.a);
      label70:
      paramString1 = "normal";
    }
  }
  
  public io.b.j<ua.com.tim_berners.parental_control.data.models.common.a> a(long paramLong, String paramString, com.google.gson.m paramM)
  {
    o(paramLong);
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("id", Long.valueOf(paramLong));
      if (paramM != null) {
        localM2.a("data", paramM);
      }
      localM2.a("status", paramString);
      paramM = new com.google.gson.g();
      paramM.a(localM2);
      localM1.a("actions", paramM);
      paramM = new StringBuilder();
      paramM.append("dt_mng | act = ");
      paramM.append(paramLong);
      paramM.append(" | s = ");
      paramM.append(paramString);
      V(paramM.toString());
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.changeActionStatusObservable(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.j<ua.com.tim_berners.parental_control.data.models.common.a> a(Location paramLocation)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("lat", Double.valueOf(paramLocation.getLatitude()));
      localM2.a("lon", Double.valueOf(paramLocation.getLongitude()));
      localM1.a("location", localM2);
    }
    catch (Exception paramLocation)
    {
      paramLocation.printStackTrace();
    }
    V("dt_mng | upl_loc");
    return this.d.uploadLocationObservable(str, localM1).a(new ax(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.location.d>> a(int paramInt)
  {
    String str = this.c.j();
    return this.d.getMonitoringZones(str, paramInt).a(new ba(this, paramInt)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.m.g>> a(@NonNull com.google.gson.m paramM, @NonNull String paramString1, @NonNull String paramString2)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("data", paramM);
      localM.a("type", paramString1);
    }
    catch (Exception paramM)
    {
      paramM.printStackTrace();
    }
    V("dt_mng | upl_web | add ");
    return this.d.addWebUrl(str, paramString2, localM).a(new bu(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> a(@NonNull String paramString, int paramInt)
  {
    String str = this.c.j();
    return this.d.disableAlarm(str, paramString, paramInt).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, long paramLong, String paramString2)
  {
    return a(paramString1, "monitoring_delete", paramLong, paramString2);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, long paramLong, String paramString2, double paramDouble1, double paramDouble2)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("lat", Double.valueOf(paramDouble1));
      localM2.a("lon", Double.valueOf(paramDouble2));
      com.google.gson.m localM3 = new com.google.gson.m();
      localM3.a("location", localM2);
      localM3.a("radius", Long.valueOf(paramLong));
      localM3.a("type", paramString2);
      localM1.a("device_id", paramString1);
      localM1.a("data", localM3);
      localM1.a("type", "locations");
      localM1.a("action", "monitoring_new");
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, long paramLong1, String paramString2, long paramLong2, String paramString3, double paramDouble1, double paramDouble2)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("lat", Double.valueOf(paramDouble1));
      localM2.a("lon", Double.valueOf(paramDouble2));
      com.google.gson.m localM3 = new com.google.gson.m();
      localM3.a("monitoring_id", Long.valueOf(paramLong1));
      localM3.a("zone_identifier", paramString2);
      localM3.a("location", localM2);
      localM3.a("radius", Long.valueOf(paramLong2));
      localM3.a("type", paramString3);
      localM1.a("device_id", paramString1);
      localM1.a("data", localM3);
      localM1.a("type", "locations");
      localM1.a("action", "monitoring_on");
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.common.c>> a(@NonNull String paramString1, @NonNull String paramString2)
  {
    com.google.gson.m localM1 = new com.google.gson.m();
    localM1.a("email", paramString1);
    localM1.a("password", paramString2);
    localM1.a("device_id", bI());
    localM1.a("device_type", "android");
    com.google.gson.m localM2 = ua.com.tim_berners.parental_control.d.z.k(this.b);
    if (localM2 != null) {
      localM1.a("technical_data", localM2);
    }
    if (localM2 != null)
    {
      com.google.gson.m localM3 = new com.google.gson.m();
      localM2.a("tracker_attribution", this.c.T());
      localM2.a("tracker_device_id", this.c.U());
      localM2.a("tracker_is_gathered", Boolean.valueOf(this.c.V()));
      localM2.a("tracker", localM3);
      localM1.a("technical_data", localM2);
    }
    V("dt_mng | lgn | str");
    return this.d.login(localM1).a(new c(this, paramString1, paramString2)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("device_id", paramString1);
      localM.a("type", paramString3);
      localM.a("action", paramString4);
      paramString1 = new com.google.gson.m();
      paramString3 = new com.google.gson.g();
      paramString3.a(paramString2);
      paramString1.a("apps", paramString3);
      if (paramInt > 0) {
        paramString1.a("locked_duration", Integer.valueOf(paramInt));
      }
      localM.a("data", paramString1);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.common.c>> a(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3)
  {
    com.google.gson.m localM1 = new com.google.gson.m();
    localM1.a("email", paramString1);
    localM1.a("password", paramString2);
    localM1.a("password_token", paramString3);
    localM1.a("device_id", bI());
    localM1.a("device_type", "android");
    paramString3 = ua.com.tim_berners.parental_control.d.z.k(this.b);
    if (paramString3 != null)
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      paramString3.a("tracker_attribution", this.c.T());
      paramString3.a("tracker_device_id", this.c.U());
      paramString3.a("tracker_is_gathered", Boolean.valueOf(this.c.V()));
      paramString3.a("tracker", localM2);
      localM1.a("technical_data", paramString3);
    }
    V("dt_mng | rst_pas");
    return this.d.resetPassword(localM1).a(new o(this, paramString1, paramString2)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.common.c>> a(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @NonNull String paramString4)
  {
    Object localObject2 = f();
    Object localObject1 = localObject2;
    if (!((String)localObject2).equals("parent")) {
      if (((String)localObject2).equals("child")) {
        localObject1 = localObject2;
      } else {
        localObject1 = "parent";
      }
    }
    localObject2 = new com.google.gson.m();
    ((com.google.gson.m)localObject2).a("email", paramString1);
    ((com.google.gson.m)localObject2).a("password", paramString2);
    ((com.google.gson.m)localObject2).a("name", paramString3);
    ((com.google.gson.m)localObject2).a("role", (String)localObject1);
    ((com.google.gson.m)localObject2).a("device_id", bI());
    ((com.google.gson.m)localObject2).a("device_type", "android");
    ((com.google.gson.m)localObject2).a("code", paramString4);
    paramString2 = ua.com.tim_berners.parental_control.d.z.k(this.b);
    if (paramString2 != null)
    {
      paramString3 = new com.google.gson.m();
      paramString2.a("tracker_attribution", this.c.T());
      paramString2.a("tracker_device_id", this.c.U());
      paramString2.a("tracker_is_gathered", Boolean.valueOf(this.c.V()));
      paramString2.a("tracker", paramString3);
      ((com.google.gson.m)localObject2).a("technical_data", paramString2);
    }
    V("dt_mng | ver_eml | str");
    return this.d.emailVerification((com.google.gson.m)localObject2).a(new d(this, paramString1)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, String paramString2, String... paramVarArgs)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      com.google.gson.g localG = new com.google.gson.g();
      int n = paramVarArgs.length;
      int m = 0;
      while (m < n)
      {
        localG.a(paramVarArgs[m]);
        m += 1;
      }
      localM.a("device_id", paramString1);
      localM.a("data", localG);
      localM.a("type", paramString2);
      localM.a("action", "delete");
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, ArrayList<String> paramArrayList, int paramInt, String paramString2, String paramString3)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("device_id", paramString1);
      localM.a("type", paramString2);
      localM.a("action", paramString3);
      paramString1 = new com.google.gson.m();
      paramString1.a("apps", new com.google.gson.e().a(paramArrayList).m());
      if (!paramString3.equals("unlock")) {
        paramString1.a("locked_duration", Integer.valueOf(paramInt));
      }
      localM.a("data", paramString1);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString1, ArrayList<String> paramArrayList, com.google.gson.g paramG, String paramString2, String paramString3)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("device_id", paramString1);
      localM.a("type", paramString2);
      localM.a("action", paramString3);
      paramString1 = new com.google.gson.m();
      paramString1.a("apps", new com.google.gson.e().a(paramArrayList).m());
      paramString1.a("schedule", paramG);
      localM.a("data", paramString1);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> a(String paramString, boolean paramBoolean)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("is_real_push", Boolean.valueOf(paramBoolean));
      localM1.a("device_id", paramString);
      localM1.a("type", "locations");
      localM1.a("action", "current_location");
      localM1.a("data", localM2);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.e.b>> a(List<ua.com.tim_berners.parental_control.data.models.e.a> paramList, boolean paramBoolean)
  {
    boolean bool = bE();
    com.google.gson.g localG1 = new com.google.gson.g();
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    D(false);
    if (!c(paramList)) {
      return io.b.q.a(m.a);
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (ua.com.tim_berners.parental_control.data.models.e.a)paramList.next();
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("contact_identifier", ((ua.com.tim_berners.parental_control.data.models.e.a)localObject).k());
      localM2.a("first_name", ((ua.com.tim_berners.parental_control.data.models.e.a)localObject).i());
      localM2.a("last_name", ((ua.com.tim_berners.parental_control.data.models.e.a)localObject).j());
      localM2.a("contact_created_date", ((ua.com.tim_berners.parental_control.data.models.e.a)localObject).f());
      com.google.gson.g localG2 = new com.google.gson.g();
      if (((ua.com.tim_berners.parental_control.data.models.e.a)localObject).m() != null)
      {
        localObject = ((ua.com.tim_berners.parental_control.data.models.e.a)localObject).m().iterator();
        while (((Iterator)localObject).hasNext())
        {
          ua.com.tim_berners.parental_control.data.models.e.h localH = (ua.com.tim_berners.parental_control.data.models.e.h)((Iterator)localObject).next();
          com.google.gson.m localM3 = new com.google.gson.m();
          if ((localH != null) && (localH.d() != null) && (localH.e() != null))
          {
            localM3.a("type", localH.e());
            localM3.a("phone", localH.d());
            localG2.a(localM3);
          }
        }
      }
      localM2.a("phones", localG2);
      localG1.a(localM2);
    }
    localM1.a("is_observered", Boolean.valueOf(paramBoolean));
    localM1.a("is_first_sync", Boolean.valueOf(bool));
    localM1.a("contacts", localG1);
    paramList = new StringBuilder();
    paramList.append("dt_mng | upl_cnt | f = ");
    paramList.append(localG1.a());
    paramList.append(" | o = ");
    paramList.append(paramBoolean);
    V(paramList.toString());
    return this.d.uploadContacts(str, localM1).a(new n(this)).b(new p(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> a(ua.com.tim_berners.parental_control.data.models.location.f paramF)
  {
    String str1 = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    String str2 = paramF.a;
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("lat", Double.valueOf(paramF.b.a));
      localM2.a("lon", Double.valueOf(paramF.b.b));
      localM1.a("action_type", paramF.c);
      localM1.a("location", localM2);
      localM1.a("zone_identifier", str2);
    }
    catch (Exception paramF)
    {
      paramF.printStackTrace();
    }
    V("dt_mng | ntf_zone");
    return this.d.notifyZoneCross(str1, localM1).b(io.b.h.a.b()).a(new ay(this)).b(new az(this)).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<SettingsModel>> a(boolean paramBoolean, @NonNull String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.k.g> a(long paramLong, String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 8
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 9
    //   39: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   42: astore 7
    //   44: aconst_null
    //   45: astore 5
    //   47: aload 5
    //   49: astore 4
    //   51: aload 7
    //   53: invokevirtual 524	io/realm/dk:a	()V
    //   56: aload 5
    //   58: astore 4
    //   60: aload 6
    //   62: aload_0
    //   63: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   66: aload 7
    //   68: lload_1
    //   69: aload_3
    //   70: invokevirtual 1843	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;JLjava/lang/String;)Lio/realm/dy;
    //   73: aload 8
    //   75: aload 9
    //   77: invokestatic 1846	ua/com/tim_berners/parental_control/data/models/k/g:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   80: aload 7
    //   82: ifnull +63 -> 145
    //   85: aload 7
    //   87: invokevirtual 517	io/realm/dk:close	()V
    //   90: aload 6
    //   92: areturn
    //   93: astore_3
    //   94: goto +9 -> 103
    //   97: astore_3
    //   98: aload_3
    //   99: astore 4
    //   101: aload_3
    //   102: athrow
    //   103: aload 7
    //   105: ifnull +33 -> 138
    //   108: aload 4
    //   110: ifnull +23 -> 133
    //   113: aload 7
    //   115: invokevirtual 517	io/realm/dk:close	()V
    //   118: goto +20 -> 138
    //   121: astore 5
    //   123: aload 4
    //   125: aload 5
    //   127: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   130: goto +8 -> 138
    //   133: aload 7
    //   135: invokevirtual 517	io/realm/dk:close	()V
    //   138: aload_3
    //   139: athrow
    //   140: astore_3
    //   141: aload_3
    //   142: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   145: aload 6
    //   147: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	b
    //   0	148	1	paramLong	long
    //   0	148	3	paramString	String
    //   49	75	4	localObject1	Object
    //   45	12	5	localObject2	Object
    //   121	5	5	localThrowable	Throwable
    //   7	139	6	localArrayList	ArrayList
    //   42	92	7	localDk	dk
    //   22	52	8	localSimpleDateFormat1	SimpleDateFormat
    //   37	39	9	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   51	56	93	finally
    //   60	80	93	finally
    //   101	103	93	finally
    //   51	56	97	java/lang/Throwable
    //   60	80	97	java/lang/Throwable
    //   113	118	121	java/lang/Throwable
    //   39	44	140	java/lang/Exception
    //   85	90	140	java/lang/Exception
    //   113	118	140	java/lang/Exception
    //   123	130	140	java/lang/Exception
    //   133	138	140	java/lang/Exception
    //   138	140	140	java/lang/Exception
  }
  
  public a a()
  {
    return this.h;
  }
  
  @Nullable
  public MonitoringZone a(dk paramDk, @NonNull String paramString)
  {
    return this.a.k(paramDk, paramString);
  }
  
  public void a(int paramInt1, int paramInt2, long paramLong)
  {
    this.a.a(paramInt1, paramInt2, paramLong);
  }
  
  public void a(int paramInt, boolean paramBoolean)
  {
    this.a.a(paramInt, paramBoolean);
  }
  
  public void a(long paramLong)
  {
    this.c.b(paramLong);
  }
  
  public void a(long paramLong, int paramInt, String paramString)
  {
    this.a.a(paramLong, paramInt, paramString);
  }
  
  public void a(Location paramLocation, String paramString1, String paramString2)
  {
    c(paramLocation, paramString1, paramString2);
    try
    {
      LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent("ua.com.tim_berners.parental_control.StartNotifyZoneUpload"));
      LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent("ua.com.tim_berners.parental_control.StartUpload"));
      return;
    }
    catch (Exception paramLocation)
    {
      paramLocation.printStackTrace();
    }
  }
  
  public void a(Class<? extends du> paramClass)
  {
    this.a.a(paramClass);
  }
  
  public void a(String paramString)
  {
    this.c.k(paramString);
  }
  
  public void a(String paramString, double paramDouble1, double paramDouble2)
  {
    this.a.a(paramString, paramDouble1, paramDouble2);
  }
  
  public void a(String paramString, long paramLong)
  {
    ua.com.tim_berners.parental_control.data.models.a.c localC = new ua.com.tim_berners.parental_control.data.models.a.c();
    localC.a(paramString);
    localC.a(paramLong);
    this.a.a(localC);
  }
  
  public void a(String paramString, long paramLong1, long paramLong2)
  {
    this.c.a(paramString, paramLong1, paramLong2);
  }
  
  public void a(List<ua.com.tim_berners.parental_control.data.models.b.b> paramList)
  {
    this.a.c(paramList);
  }
  
  public void a(List<String> paramList, int paramInt1, int paramInt2, String paramString)
  {
    this.a.a(paramList, paramInt1, paramInt2, paramString);
  }
  
  public void a(List<String> paramList, List<Integer> paramList1)
  {
    this.a.a(paramList, paramList1);
  }
  
  public void a(List<ua.com.tim_berners.parental_control.data.models.e.a> paramList1, List<ua.com.tim_berners.parental_control.data.models.e.a> paramList2, String paramString)
  {
    this.a.a(paramList1, paramList2, paramString);
  }
  
  public void a(ua.com.tim_berners.parental_control.data.models.a.d paramD)
  {
    this.a.a(paramD);
  }
  
  public void a(ua.com.tim_berners.parental_control.data.models.b.b paramB)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramB);
      this.a.c(localArrayList);
      LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent("ua.com.tim_berners.parental_control.ProcessAction"));
      return;
    }
    catch (Exception paramB)
    {
      paramB.printStackTrace();
    }
  }
  
  public void a(@NonNull MonitoringZone paramMonitoringZone)
  {
    this.a.a(paramMonitoringZone);
  }
  
  public void a(boolean paramBoolean)
  {
    this.c.b(paramBoolean);
  }
  
  public void a(String[] paramArrayOfString)
  {
    this.a.a(paramArrayOfString);
  }
  
  public boolean aA()
  {
    return ua.com.tim_berners.parental_control.d.w.t(this.b);
  }
  
  public boolean aB()
  {
    return ua.com.tim_berners.parental_control.d.w.e(this.b);
  }
  
  public boolean aC()
  {
    return NotificationManagerCompat.from(this.b).areNotificationsEnabled();
  }
  
  public boolean aD()
  {
    return ua.com.tim_berners.parental_control.d.w.h(this.b);
  }
  
  public boolean aE()
  {
    return ua.com.tim_berners.parental_control.d.w.k(this.b);
  }
  
  public boolean aF()
  {
    return ua.com.tim_berners.parental_control.d.w.m(this.b);
  }
  
  public boolean aG()
  {
    return ua.com.tim_berners.parental_control.d.w.s(this.b);
  }
  
  public boolean aH()
  {
    return (!bQ()) || (this.c.c());
  }
  
  public boolean aI()
  {
    return (!bP()) || (this.c.d());
  }
  
  public boolean aJ()
  {
    boolean bool2 = ua.com.tim_berners.parental_control.d.x.b();
    boolean bool1 = true;
    if (!bool2) {
      return true;
    }
    if (ua.com.tim_berners.parental_control.service.a.l(this.b))
    {
      if (this.c.e()) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
  
  public boolean aK()
  {
    return (!ua.com.tim_berners.parental_control.d.w.u(this.b)) && (ax()) && (ay()) && (aw()) && (az()) && (aB()) && (aC());
  }
  
  public boolean aL()
  {
    return (!ax()) || (!ay()) || (!az()) || (!aw()) || (!aE()) || (!aG()) || (!bM()) || (!aB()) || (!aD()) || (!bN()) || (!bO());
  }
  
  public ua.com.tim_berners.parental_control.data.models.device.b aM()
  {
    ua.com.tim_berners.parental_control.data.models.device.b localB = new ua.com.tim_berners.parental_control.data.models.device.b();
    localB.e = aB();
    localB.a = ax();
    localB.b = ay();
    localB.c = az();
    localB.d = aw();
    localB.j = aE();
    localB.k = aG();
    localB.l = aH();
    localB.m = aI();
    localB.h = bM();
    localB.f = aD();
    localB.g = bN();
    localB.i = bO();
    return localB;
  }
  
  public boolean aN()
  {
    return this.c.o();
  }
  
  public boolean aO()
  {
    return this.c.C();
  }
  
  public String aP()
  {
    return this.c.D();
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.a.b aQ()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_1
    //   5: aload 4
    //   7: astore_2
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 5
    //   13: aload 5
    //   15: invokevirtual 524	io/realm/dk:a	()V
    //   18: aload_0
    //   19: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   22: aload 5
    //   24: invokevirtual 2070	ua/com/tim_berners/parental_control/data/b/a:d	(Lio/realm/dk;)Lua/com/tim_berners/parental_control/data/models/a/a;
    //   27: astore_2
    //   28: aload_2
    //   29: ifnull +8 -> 37
    //   32: aload_2
    //   33: invokestatic 2075	ua/com/tim_berners/parental_control/data/models/a/b:a	(Lua/com/tim_berners/parental_control/data/models/a/a;)Lua/com/tim_berners/parental_control/data/models/a/b;
    //   36: astore_1
    //   37: aload_1
    //   38: astore_2
    //   39: aload 5
    //   41: ifnull +74 -> 115
    //   44: aload_1
    //   45: astore_2
    //   46: aload 5
    //   48: invokevirtual 517	io/realm/dk:close	()V
    //   51: aload_1
    //   52: areturn
    //   53: astore_1
    //   54: aconst_null
    //   55: astore_3
    //   56: goto +7 -> 63
    //   59: astore_3
    //   60: aload_3
    //   61: athrow
    //   62: astore_1
    //   63: aload 5
    //   65: ifnull +40 -> 105
    //   68: aload_3
    //   69: ifnull +28 -> 97
    //   72: aload 4
    //   74: astore_2
    //   75: aload 5
    //   77: invokevirtual 517	io/realm/dk:close	()V
    //   80: goto +25 -> 105
    //   83: astore 5
    //   85: aload 4
    //   87: astore_2
    //   88: aload_3
    //   89: aload 5
    //   91: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   94: goto +11 -> 105
    //   97: aload 4
    //   99: astore_2
    //   100: aload 5
    //   102: invokevirtual 517	io/realm/dk:close	()V
    //   105: aload 4
    //   107: astore_2
    //   108: aload_1
    //   109: athrow
    //   110: astore_1
    //   111: aload_1
    //   112: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   115: aload_2
    //   116: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	b
    //   4	48	1	localB	ua.com.tim_berners.parental_control.data.models.a.b
    //   53	1	1	localObject1	Object
    //   62	47	1	localObject2	Object
    //   110	2	1	localException	Exception
    //   7	109	2	localObject3	Object
    //   55	1	3	localObject4	Object
    //   59	30	3	localThrowable1	Throwable
    //   1	105	4	localObject5	Object
    //   11	65	5	localDk	dk
    //   83	18	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   13	28	53	finally
    //   32	37	53	finally
    //   13	28	59	java/lang/Throwable
    //   32	37	59	java/lang/Throwable
    //   60	62	62	finally
    //   75	80	83	java/lang/Throwable
    //   8	13	110	java/lang/Exception
    //   46	51	110	java/lang/Exception
    //   75	80	110	java/lang/Exception
    //   88	94	110	java/lang/Exception
    //   100	105	110	java/lang/Exception
    //   108	110	110	java/lang/Exception
  }
  
  public void aR()
  {
    this.c.i(false);
    ua.com.tim_berners.parental_control.d.z.d(this.b);
  }
  
  public void aS()
  {
    if (ua.com.tim_berners.parental_control.d.z.a(LocationTrackerService.class, this.b))
    {
      this.b.stopService(LocationTrackerService.a(this.b));
      V("dt_mng | loc_srv | stp");
    }
  }
  
  public void aT()
  {
    if (i())
    {
      if (!aE()) {
        return;
      }
      if ((c(Integer.valueOf(an()).intValue()) != null) && (!ua.com.tim_berners.parental_control.d.z.a(LocationTrackerService.class, this.b)))
      {
        this.b.startService(LocationTrackerService.a(this.b));
        V("dt_mng | loc_srv | str");
      }
      return;
    }
  }
  
  public void aU()
  {
    if (ua.com.tim_berners.parental_control.d.z.a(ServiceManager.class, this.b)) {
      try
      {
        LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent("ua.com.tim_berners.parental_control.ServicesAvailabilityStop"));
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    if (ua.com.tim_berners.parental_control.d.z.a(AppsUsageService.class, this.b)) {
      this.b.stopService(AppsUsageService.a(this.b));
    }
    if (ua.com.tim_berners.parental_control.d.z.a(UploadService.class, this.b)) {
      this.b.stopService(UploadService.a(this.b));
    }
    if ((Build.VERSION.SDK_INT >= 21) && (ua.com.tim_berners.parental_control.d.z.a(ParentalNotificationListenerService.class, this.b))) {
      this.b.stopService(ParentalNotificationListenerService.a(this.b));
    }
    if (ua.com.tim_berners.parental_control.d.z.a(LocationTrackerService.class, this.b)) {
      this.b.stopService(LocationTrackerService.a(this.b));
    }
    V("dt_mng | srv | stp");
  }
  
  public void aV()
  {
    if (!i()) {
      return;
    }
    if (c(Integer.valueOf(an()).intValue()) != null)
    {
      if (!ua.com.tim_berners.parental_control.d.z.a(ParentalAccessibilityService.class, this.b)) {
        this.b.startService(ParentalAccessibilityService.a(this.b));
      }
      if (!ua.com.tim_berners.parental_control.d.z.a(AppsUsageService.class, this.b)) {
        this.b.startService(AppsUsageService.a(this.b));
      }
      if (!ua.com.tim_berners.parental_control.d.z.a(ServiceManager.class, this.b)) {
        this.b.startService(ServiceManager.a(this.b, App.a));
      }
      if (!ua.com.tim_berners.parental_control.d.z.a(UploadService.class, this.b)) {
        this.b.startService(UploadService.a(this.b));
      }
      if ((Build.VERSION.SDK_INT >= 21) && (!ua.com.tim_berners.parental_control.d.z.a(ParentalNotificationListenerService.class, this.b))) {
        this.b.startService(ParentalNotificationListenerService.a(this.b));
      }
      if (!ua.com.tim_berners.parental_control.d.z.a(LocationTrackerService.class, this.b)) {
        this.b.startService(LocationTrackerService.a(this.b));
      }
      V("dt_mng | srv | str");
    }
  }
  
  public boolean aW()
  {
    String str = bC();
    return (str == null) || (!str.equals(String.valueOf(220)));
  }
  
  public long aX()
  {
    return this.e;
  }
  
  public int aY()
  {
    return this.g;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.i> aZ()
  {
    String str = this.c.j();
    return this.d.getServerTime(str).a(new bh(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.b aa()
  {
    return io.b.b.a(new al(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public boolean aa(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 884	ua/com/tim_berners/parental_control/data/b/a:i	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.b ab()
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    boolean bool = false;
    Object localObject1 = null;
    try
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        localObject1 = bK();
        bool = a((ua.com.tim_berners.a.b.f)localObject1);
      }
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | upl_sts | chg ");
      ((StringBuilder)localObject2).append(bool);
      V(((StringBuilder)localObject2).toString());
      if ((bool) && (localObject1 != null))
      {
        localM.a("battery_level", Float.valueOf(((ua.com.tim_berners.a.b.f)localObject1).c));
        localM.a("total_space", ((ua.com.tim_berners.a.b.f)localObject1).b);
        localM.a("last_available_space", ((ua.com.tim_berners.a.b.f)localObject1).a);
        Object localObject3 = ((ua.com.tim_berners.a.b.f)localObject1).d;
        if ((localObject3 != null) && (((HashMap)localObject3).size() > 0))
        {
          localObject1 = new com.google.gson.m();
          localObject2 = new com.google.gson.e();
          localObject3 = ((HashMap)localObject3).entrySet().iterator();
          while (((Iterator)localObject3).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
            ((com.google.gson.m)localObject1).a((String)localEntry.getKey(), ((com.google.gson.e)localObject2).a(localEntry.getValue()));
          }
          localM.a("internet_traffic", (com.google.gson.j)localObject1);
        }
      }
      else
      {
        localObject1 = io.b.b.a(an.a);
        return localObject1;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      V("dt_mng | upl_sts");
    }
    return this.d.uploadStatistic(str, localM).a(new ao(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public boolean ab(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 2189	ua/com/tim_berners/parental_control/data/b/a:e	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.k.f>> ac()
  {
    boolean bool = bH();
    long l = bz();
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    G(false);
    com.google.gson.g localG = new com.google.gson.g();
    List localList = new ua.com.tim_berners.a.a.g(this.b).a(l);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("dt_mng | upl_sms | c = ");
    int m;
    if (localList != null) {
      m = localList.size();
    } else {
      m = 0;
    }
    ((StringBuilder)localObject).append(m);
    ((StringBuilder)localObject).append(" | t ");
    ((StringBuilder)localObject).append(l);
    V(((StringBuilder)localObject).toString());
    if ((localList != null) && (localList.size() != 0))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ua.com.tim_berners.a.b.h localH = (ua.com.tim_berners.a.b.h)localIterator.next();
        com.google.gson.m localM2 = new com.google.gson.m();
        if (localH.a != null) {
          localM2.a("sms_identifier", localH.a);
        }
        if (localH.c != null) {
          localM2.a("address", localH.c);
        }
        if (localH.d != null) {
          localM2.a("body", localH.d);
        }
        localM2.a("thread_id", Integer.valueOf(localH.b));
        localM2.a("read_state", Integer.valueOf(localH.e));
        localM2.a("date", Long.valueOf(localH.f));
        if (localH.g == null) {
          localObject = "empty";
        } else {
          localObject = localH.g;
        }
        localM2.a("contact_name", (String)localObject);
        localM2.a("type", Integer.valueOf(localH.h));
        localG.a(localM2);
      }
      l = ((ua.com.tim_berners.a.b.h)localList.get(0)).f;
      localM1.a("is_first_sync", Boolean.valueOf(bool));
      localM1.a("sms", localG);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("dt_mng | upl_sms | f ");
      ((StringBuilder)localObject).append(localG.a());
      V(((StringBuilder)localObject).toString());
      return this.d.uploadSms(str, localM1).a(new aq(this, l)).b(new ar(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
    }
    return io.b.q.a(ap.a);
  }
  
  /* Error */
  public boolean ac(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 830	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public boolean ad()
  {
    return (!ak()) && (aD()) && (i()) && (aB());
  }
  
  /* Error */
  public boolean ad(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 197	ua/com/tim_berners/parental_control/data/b/a:b	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> ae()
  {
    String str = this.c.j();
    LocationRequest localLocationRequest = LocationRequest.a().a(1000L).b(5000L).b(1);
    e.a.a.a.a localA = new e.a.a.a.a(this.b);
    V("dt_mng | req_upl_loc_str");
    return io.b.q.a(localA.a(localLocationRequest).a(io.b.h.a.b())).a(new aw(this, str));
  }
  
  /* Error */
  public boolean ae(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 2284	ua/com/tim_berners/parental_control/data/b/a:p	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.location.f> af()
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 4
    //   13: aconst_null
    //   14: astore_2
    //   15: aload_2
    //   16: astore_1
    //   17: aload 4
    //   19: invokevirtual 524	io/realm/dk:a	()V
    //   22: aload_2
    //   23: astore_1
    //   24: aload_3
    //   25: aload_0
    //   26: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   29: aload 4
    //   31: invokevirtual 2287	ua/com/tim_berners/parental_control/data/b/a:b	(Lio/realm/dk;)Lio/realm/dy;
    //   34: invokestatic 2288	ua/com/tim_berners/parental_control/data/models/location/f:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   37: aload 4
    //   39: ifnull +59 -> 98
    //   42: aload 4
    //   44: invokevirtual 517	io/realm/dk:close	()V
    //   47: aload_3
    //   48: areturn
    //   49: astore_2
    //   50: goto +8 -> 58
    //   53: astore_2
    //   54: aload_2
    //   55: astore_1
    //   56: aload_2
    //   57: athrow
    //   58: aload 4
    //   60: ifnull +31 -> 91
    //   63: aload_1
    //   64: ifnull +22 -> 86
    //   67: aload 4
    //   69: invokevirtual 517	io/realm/dk:close	()V
    //   72: goto +19 -> 91
    //   75: astore 4
    //   77: aload_1
    //   78: aload 4
    //   80: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   83: goto +8 -> 91
    //   86: aload 4
    //   88: invokevirtual 517	io/realm/dk:close	()V
    //   91: aload_2
    //   92: athrow
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   98: aload_3
    //   99: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	b
    //   16	62	1	localObject1	Object
    //   93	2	1	localException	Exception
    //   14	9	2	localObject2	Object
    //   49	1	2	localObject3	Object
    //   53	39	2	localThrowable1	Throwable
    //   7	92	3	localArrayList	ArrayList
    //   11	57	4	localDk	dk
    //   75	12	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   17	22	49	finally
    //   24	37	49	finally
    //   56	58	49	finally
    //   17	22	53	java/lang/Throwable
    //   24	37	53	java/lang/Throwable
    //   67	72	75	java/lang/Throwable
    //   8	13	93	java/lang/Exception
    //   42	47	93	java/lang/Exception
    //   67	72	93	java/lang/Exception
    //   77	83	93	java/lang/Exception
    //   86	91	93	java/lang/Exception
    //   91	93	93	java/lang/Exception
  }
  
  /* Error */
  public boolean af(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_3
    //   5: iload 5
    //   7: istore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 7
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 8
    //   36: aload_1
    //   37: invokevirtual 2291	ua/com/tim_berners/parental_control/data/b/a:q	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   40: invokevirtual 527	io/realm/dy:size	()I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +5 -> 50
    //   48: iconst_1
    //   49: istore_3
    //   50: iload_3
    //   51: istore 4
    //   53: aload 8
    //   55: ifnull +81 -> 136
    //   58: iload_3
    //   59: istore 4
    //   61: aload 8
    //   63: invokevirtual 517	io/realm/dk:close	()V
    //   66: iload_3
    //   67: ireturn
    //   68: astore_1
    //   69: goto +9 -> 78
    //   72: astore_1
    //   73: aload_1
    //   74: astore 6
    //   76: aload_1
    //   77: athrow
    //   78: aload 8
    //   80: ifnull +45 -> 125
    //   83: aload 6
    //   85: ifnull +31 -> 116
    //   88: iload 5
    //   90: istore 4
    //   92: aload 8
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +28 -> 125
    //   100: astore 7
    //   102: iload 5
    //   104: istore 4
    //   106: aload 6
    //   108: aload 7
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +12 -> 125
    //   116: iload 5
    //   118: istore 4
    //   120: aload 8
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: iload 5
    //   127: istore 4
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   136: iload 4
    //   138: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	b
    //   0	139	1	paramString	String
    //   43	2	2	m	int
    //   4	63	3	bool1	boolean
    //   7	130	4	bool2	boolean
    //   1	125	5	bool3	boolean
    //   19	88	6	localObject1	Object
    //   15	12	7	localObject2	Object
    //   100	9	7	localThrowable	Throwable
    //   12	109	8	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	68	finally
    //   30	44	68	finally
    //   76	78	68	finally
    //   21	26	72	java/lang/Throwable
    //   30	44	72	java/lang/Throwable
    //   92	97	100	java/lang/Throwable
    //   9	14	131	java/lang/Exception
    //   61	66	131	java/lang/Exception
    //   92	97	131	java/lang/Exception
    //   106	113	131	java/lang/Exception
    //   120	125	131	java/lang/Exception
    //   129	131	131	java/lang/Exception
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.h.a>> ag()
  {
    String str = this.c.j();
    if (!bR()) {
      a("notifications", 0L);
    }
    long l = ai("notifications");
    return this.d.getListNotifications(str, l).a(new bb(this, "notifications")).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public Integer ah()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: invokestatic 1082	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   6: astore 5
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 6
    //   13: aconst_null
    //   14: astore 4
    //   16: aload 4
    //   18: astore_3
    //   19: aload 6
    //   21: invokevirtual 524	io/realm/dk:a	()V
    //   24: aload 4
    //   26: astore_3
    //   27: aload_0
    //   28: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   31: aload 6
    //   33: invokevirtual 2303	ua/com/tim_berners/parental_control/data/b/a:f	(Lio/realm/dk;)Lio/realm/dy;
    //   36: astore 7
    //   38: iload_2
    //   39: istore_1
    //   40: aload 4
    //   42: astore_3
    //   43: aload_0
    //   44: invokevirtual 2249	ua/com/tim_berners/parental_control/data/b:ak	()Z
    //   47: ifeq +19 -> 66
    //   50: iload_2
    //   51: istore_1
    //   52: aload 7
    //   54: ifnull +12 -> 66
    //   57: aload 4
    //   59: astore_3
    //   60: aload 7
    //   62: invokevirtual 527	io/realm/dy:size	()I
    //   65: istore_1
    //   66: aload 4
    //   68: astore_3
    //   69: iload_1
    //   70: invokestatic 1082	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   73: astore 4
    //   75: aload 4
    //   77: astore_3
    //   78: aload 6
    //   80: ifnull +82 -> 162
    //   83: aload 6
    //   85: invokevirtual 517	io/realm/dk:close	()V
    //   88: aload 4
    //   90: areturn
    //   91: astore 5
    //   93: aload 4
    //   95: astore_3
    //   96: aload 5
    //   98: astore 4
    //   100: goto +57 -> 157
    //   103: astore 4
    //   105: goto +11 -> 116
    //   108: astore 4
    //   110: aload 4
    //   112: astore_3
    //   113: aload 4
    //   115: athrow
    //   116: aload 6
    //   118: ifnull +31 -> 149
    //   121: aload_3
    //   122: ifnull +22 -> 144
    //   125: aload 6
    //   127: invokevirtual 517	io/realm/dk:close	()V
    //   130: goto +19 -> 149
    //   133: astore 6
    //   135: aload_3
    //   136: aload 6
    //   138: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   141: goto +8 -> 149
    //   144: aload 6
    //   146: invokevirtual 517	io/realm/dk:close	()V
    //   149: aload 4
    //   151: athrow
    //   152: astore 4
    //   154: aload 5
    //   156: astore_3
    //   157: aload 4
    //   159: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   162: aload_3
    //   163: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	b
    //   39	31	1	m	int
    //   1	50	2	n	int
    //   18	145	3	localObject1	Object
    //   14	85	4	localObject2	Object
    //   103	1	4	localObject3	Object
    //   108	42	4	localThrowable1	Throwable
    //   152	6	4	localException1	Exception
    //   6	1	5	localInteger	Integer
    //   91	64	5	localException2	Exception
    //   11	115	6	localDk	dk
    //   133	12	6	localThrowable2	Throwable
    //   36	25	7	localDy	dy
    // Exception table:
    //   from	to	target	type
    //   83	88	91	java/lang/Exception
    //   19	24	103	finally
    //   27	38	103	finally
    //   43	50	103	finally
    //   60	66	103	finally
    //   69	75	103	finally
    //   113	116	103	finally
    //   19	24	108	java/lang/Throwable
    //   27	38	108	java/lang/Throwable
    //   43	50	108	java/lang/Throwable
    //   60	66	108	java/lang/Throwable
    //   69	75	108	java/lang/Throwable
    //   125	130	133	java/lang/Throwable
    //   8	13	152	java/lang/Exception
    //   125	130	152	java/lang/Exception
    //   135	141	152	java/lang/Exception
    //   144	149	152	java/lang/Exception
    //   149	152	152	java/lang/Exception
  }
  
  public long ai()
  {
    return this.c.k();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.device.a> aj()
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: new 781	java/text/SimpleDateFormat
    //   11: dup
    //   12: ldc_w 796
    //   15: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   18: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   21: astore 5
    //   23: new 781	java/text/SimpleDateFormat
    //   26: dup
    //   27: ldc_w 796
    //   30: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   33: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   36: astore 6
    //   38: aload 6
    //   40: ldc_w 802
    //   43: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   46: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   49: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   52: astore 4
    //   54: aconst_null
    //   55: astore_2
    //   56: aload_2
    //   57: astore_1
    //   58: aload 4
    //   60: invokevirtual 524	io/realm/dk:a	()V
    //   63: aload_2
    //   64: astore_1
    //   65: aload_3
    //   66: aload_0
    //   67: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   70: aload 4
    //   72: invokevirtual 2307	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;)Lio/realm/dy;
    //   75: aload 5
    //   77: aload 6
    //   79: invokestatic 2310	ua/com/tim_berners/parental_control/data/models/device/a:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   82: aload 4
    //   84: ifnull +59 -> 143
    //   87: aload 4
    //   89: invokevirtual 517	io/realm/dk:close	()V
    //   92: aload_3
    //   93: areturn
    //   94: astore_2
    //   95: goto +8 -> 103
    //   98: astore_2
    //   99: aload_2
    //   100: astore_1
    //   101: aload_2
    //   102: athrow
    //   103: aload 4
    //   105: ifnull +31 -> 136
    //   108: aload_1
    //   109: ifnull +22 -> 131
    //   112: aload 4
    //   114: invokevirtual 517	io/realm/dk:close	()V
    //   117: goto +19 -> 136
    //   120: astore 4
    //   122: aload_1
    //   123: aload 4
    //   125: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   128: goto +8 -> 136
    //   131: aload 4
    //   133: invokevirtual 517	io/realm/dk:close	()V
    //   136: aload_2
    //   137: athrow
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   143: aload_3
    //   144: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	this	b
    //   57	66	1	localObject1	Object
    //   138	2	1	localException	Exception
    //   55	9	2	localObject2	Object
    //   94	1	2	localObject3	Object
    //   98	39	2	localThrowable1	Throwable
    //   7	137	3	localArrayList	ArrayList
    //   52	61	4	localDk	dk
    //   120	12	4	localThrowable2	Throwable
    //   21	55	5	localSimpleDateFormat1	SimpleDateFormat
    //   36	42	6	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   58	63	94	finally
    //   65	82	94	finally
    //   101	103	94	finally
    //   58	63	98	java/lang/Throwable
    //   65	82	98	java/lang/Throwable
    //   112	117	120	java/lang/Throwable
    //   49	54	138	java/lang/Exception
    //   87	92	138	java/lang/Exception
    //   112	117	138	java/lang/Exception
    //   122	128	138	java/lang/Exception
    //   131	136	138	java/lang/Exception
    //   136	138	138	java/lang/Exception
  }
  
  public boolean ak()
  {
    return this.c.l().equals("parent");
  }
  
  public void al()
  {
    if (!i()) {
      return;
    }
    V("dt_mng | clr_dat");
    try
    {
      this.c.g();
      this.c.a(false);
      this.c.s(false);
      this.c.q(false);
      this.c.r(false);
      this.c.n(false);
      new File(ua.com.tim_berners.parental_control.d.z.b(this.b)).delete();
      aU();
      aS();
      this.a.a();
      dk.a(this.b.getApplicationContext());
      dk.b(new do.a().a("default.realm").a(1L).a(new ua.com.tim_berners.parental_control.data.b.bj()).a());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  /* Error */
  public void am()
  {
    // Byte code:
    //   0: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   3: astore_3
    //   4: aconst_null
    //   5: astore_2
    //   6: aload_2
    //   7: astore_1
    //   8: aload_3
    //   9: invokevirtual 524	io/realm/dk:a	()V
    //   12: aload_2
    //   13: astore_1
    //   14: aload_0
    //   15: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   18: aload_3
    //   19: invokevirtual 2366	ua/com/tim_berners/parental_control/data/b/a:c	(Lio/realm/dk;)Lio/realm/dy;
    //   22: invokevirtual 359	io/realm/dy:iterator	()Ljava/util/Iterator;
    //   25: astore 4
    //   27: aload_2
    //   28: astore_1
    //   29: aload 4
    //   31: invokeinterface 364 1 0
    //   36: ifeq +31 -> 67
    //   39: aload_2
    //   40: astore_1
    //   41: aload 4
    //   43: invokeinterface 368 1 0
    //   48: checkcast 2368	ua/com/tim_berners/parental_control/data/models/b/b
    //   51: astore 5
    //   53: aload_2
    //   54: astore_1
    //   55: aload_0
    //   56: getfield 71	ua/com/tim_berners/parental_control/data/b:k	Lua/com/tim_berners/parental_control/service/a/b;
    //   59: aload 5
    //   61: invokevirtual 2370	ua/com/tim_berners/parental_control/service/a/b:a	(Lua/com/tim_berners/parental_control/data/models/b/b;)V
    //   64: goto -37 -> 27
    //   67: aload_3
    //   68: ifnull +52 -> 120
    //   71: aload_3
    //   72: invokevirtual 517	io/realm/dk:close	()V
    //   75: return
    //   76: astore_2
    //   77: goto +8 -> 85
    //   80: astore_2
    //   81: aload_2
    //   82: astore_1
    //   83: aload_2
    //   84: athrow
    //   85: aload_3
    //   86: ifnull +27 -> 113
    //   89: aload_1
    //   90: ifnull +19 -> 109
    //   93: aload_3
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: goto +16 -> 113
    //   100: astore_3
    //   101: aload_1
    //   102: aload_3
    //   103: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   106: goto +7 -> 113
    //   109: aload_3
    //   110: invokevirtual 517	io/realm/dk:close	()V
    //   113: aload_2
    //   114: athrow
    //   115: astore_1
    //   116: aload_1
    //   117: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   120: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	this	b
    //   7	95	1	localObject1	Object
    //   115	2	1	localException	Exception
    //   5	49	2	localObject2	Object
    //   76	1	2	localObject3	Object
    //   80	34	2	localThrowable1	Throwable
    //   3	91	3	localDk	dk
    //   100	10	3	localThrowable2	Throwable
    //   25	17	4	localIterator	Iterator
    //   51	9	5	localB	ua.com.tim_berners.parental_control.data.models.b.b
    // Exception table:
    //   from	to	target	type
    //   8	12	76	finally
    //   14	27	76	finally
    //   29	39	76	finally
    //   41	53	76	finally
    //   55	64	76	finally
    //   83	85	76	finally
    //   8	12	80	java/lang/Throwable
    //   14	27	80	java/lang/Throwable
    //   29	39	80	java/lang/Throwable
    //   41	53	80	java/lang/Throwable
    //   55	64	80	java/lang/Throwable
    //   93	97	100	java/lang/Throwable
    //   0	4	115	java/lang/Exception
    //   71	75	115	java/lang/Exception
    //   93	97	115	java/lang/Exception
    //   101	106	115	java/lang/Exception
    //   109	113	115	java/lang/Exception
    //   113	115	115	java/lang/Exception
  }
  
  public String an()
  {
    return this.a.d();
  }
  
  public String ao()
  {
    return this.c.f();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.h.d> ap()
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: new 781	java/text/SimpleDateFormat
    //   42: dup
    //   43: ldc_w 796
    //   46: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   49: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   52: astore 8
    //   54: aload 8
    //   56: ldc_w 802
    //   59: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   62: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   65: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   68: astore 5
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_3
    //   73: astore_2
    //   74: aload 5
    //   76: invokevirtual 524	io/realm/dk:a	()V
    //   79: aload_3
    //   80: astore_2
    //   81: aload_0
    //   82: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   85: aload 5
    //   87: invokevirtual 2307	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;)Lio/realm/dy;
    //   90: astore 9
    //   92: aload_3
    //   93: astore_2
    //   94: aload_0
    //   95: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   98: aload 5
    //   100: invokevirtual 668	ua/com/tim_berners/parental_control/data/b/a:g	(Lio/realm/dk;)Lio/realm/dy;
    //   103: astore 10
    //   105: aload_3
    //   106: astore_2
    //   107: aload_0
    //   108: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   111: invokevirtual 2371	ua/com/tim_berners/parental_control/data/b/a:d	()Ljava/lang/String;
    //   114: invokestatic 1317	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   117: invokevirtual 1320	java/lang/Integer:intValue	()I
    //   120: istore_1
    //   121: aload_3
    //   122: astore_2
    //   123: aload_0
    //   124: getfield 34	ua/com/tim_berners/parental_control/data/b:b	Landroid/content/Context;
    //   127: ifnull +29 -> 156
    //   130: aload_3
    //   131: astore_2
    //   132: aload 4
    //   134: aload 10
    //   136: iload_1
    //   137: aload 8
    //   139: aload 6
    //   141: aload 7
    //   143: aload 9
    //   145: aload_0
    //   146: getfield 34	ua/com/tim_berners/parental_control/data/b:b	Landroid/content/Context;
    //   149: aload_0
    //   150: getfield 52	ua/com/tim_berners/parental_control/data/b:e	J
    //   153: invokestatic 2379	ua/com/tim_berners/parental_control/data/models/h/d:a	(Ljava/util/ArrayList;Lio/realm/dy;ILjava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Lio/realm/dy;Landroid/content/Context;J)V
    //   156: aload 5
    //   158: ifnull +60 -> 218
    //   161: aload 5
    //   163: invokevirtual 517	io/realm/dk:close	()V
    //   166: aload 4
    //   168: areturn
    //   169: astore_3
    //   170: goto +8 -> 178
    //   173: astore_3
    //   174: aload_3
    //   175: astore_2
    //   176: aload_3
    //   177: athrow
    //   178: aload 5
    //   180: ifnull +31 -> 211
    //   183: aload_2
    //   184: ifnull +22 -> 206
    //   187: aload 5
    //   189: invokevirtual 517	io/realm/dk:close	()V
    //   192: goto +19 -> 211
    //   195: astore 5
    //   197: aload_2
    //   198: aload 5
    //   200: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   203: goto +8 -> 211
    //   206: aload 5
    //   208: invokevirtual 517	io/realm/dk:close	()V
    //   211: aload_3
    //   212: athrow
    //   213: astore_2
    //   214: aload_2
    //   215: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   218: aload 4
    //   220: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	this	b
    //   120	17	1	m	int
    //   73	125	2	localObject1	Object
    //   213	2	2	localException	Exception
    //   71	60	3	localObject2	Object
    //   169	1	3	localObject3	Object
    //   173	39	3	localThrowable1	Throwable
    //   7	212	4	localArrayList	ArrayList
    //   68	120	5	localDk	dk
    //   195	12	5	localThrowable2	Throwable
    //   22	118	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	105	7	localSimpleDateFormat2	SimpleDateFormat
    //   52	86	8	localSimpleDateFormat3	SimpleDateFormat
    //   90	54	9	localDy1	dy
    //   103	32	10	localDy2	dy
    // Exception table:
    //   from	to	target	type
    //   74	79	169	finally
    //   81	92	169	finally
    //   94	105	169	finally
    //   107	121	169	finally
    //   123	130	169	finally
    //   132	156	169	finally
    //   176	178	169	finally
    //   74	79	173	java/lang/Throwable
    //   81	92	173	java/lang/Throwable
    //   94	105	173	java/lang/Throwable
    //   107	121	173	java/lang/Throwable
    //   123	130	173	java/lang/Throwable
    //   132	156	173	java/lang/Throwable
    //   187	192	195	java/lang/Throwable
    //   65	70	213	java/lang/Exception
    //   161	166	213	java/lang/Exception
    //   187	192	213	java/lang/Exception
    //   197	203	213	java/lang/Exception
    //   206	211	213	java/lang/Exception
    //   211	213	213	java/lang/Exception
  }
  
  public io.b.b aq()
  {
    String str = this.c.j();
    return this.d.setReadListNotifications(str).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public boolean ar()
  {
    return ua.com.tim_berners.parental_control.d.w.i(this.b);
  }
  
  public boolean as()
  {
    if (!ua.com.tim_berners.parental_control.d.x.b()) {
      return true;
    }
    return ua.com.tim_berners.parental_control.d.w.l(this.b);
  }
  
  public boolean at()
  {
    return ua.com.tim_berners.parental_control.d.w.j(this.b);
  }
  
  public boolean au()
  {
    return ua.com.tim_berners.parental_control.d.w.q(this.b);
  }
  
  public boolean av()
  {
    return ((ua.com.tim_berners.parental_control.d.z.a()) && (bu())) || (ua.com.tim_berners.parental_control.d.w.r(this.b));
  }
  
  public boolean aw()
  {
    return ua.com.tim_berners.parental_control.d.w.b(this.b);
  }
  
  public boolean ax()
  {
    return ua.com.tim_berners.parental_control.d.w.c(this.b);
  }
  
  public boolean ay()
  {
    return ua.com.tim_berners.parental_control.d.w.d(this.b);
  }
  
  public boolean az()
  {
    return ua.com.tim_berners.parental_control.d.w.f(this.b);
  }
  
  public f.b<ResponseBody> b(long paramLong, String paramString, com.google.gson.m paramM)
  {
    o(paramLong);
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("id", Long.valueOf(paramLong));
      if (paramM != null) {
        localM2.a("data", paramM);
      }
      localM2.a("status", paramString);
      paramM = new com.google.gson.g();
      paramM.a(localM2);
      localM1.a("actions", paramM);
      paramM = new StringBuilder();
      paramM.append("dt_mng | act = ");
      paramM.append(paramLong);
      paramM.append(" | s = ");
      paramM.append(paramString);
      V(paramM.toString());
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.changeActionStatus(str, localM1);
  }
  
  public io.b.b b(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3)
  {
    Object localObject2 = f();
    Object localObject1 = localObject2;
    if (!((String)localObject2).equals("parent")) {
      if (((String)localObject2).equals("child")) {
        localObject1 = localObject2;
      } else {
        localObject1 = "parent";
      }
    }
    localObject2 = new com.google.gson.m();
    ((com.google.gson.m)localObject2).a("email", paramString1);
    ((com.google.gson.m)localObject2).a("password", paramString2);
    ((com.google.gson.m)localObject2).a("name", paramString3);
    ((com.google.gson.m)localObject2).a("role", (String)localObject1);
    ((com.google.gson.m)localObject2).a("device_id", bI());
    ((com.google.gson.m)localObject2).a("device_type", "android");
    ((com.google.gson.m)localObject2).a("lang", this.b.getResources().getConfiguration().locale.getLanguage());
    V("dt_mng | sgnp | str");
    return this.d.register((com.google.gson.m)localObject2).b(new ak(this, paramString1, paramString2)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.b b(String paramString1, String paramString2, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Object localObject = new JSONObject();
        ((JSONObject)localObject).put("title", paramString1);
        ((JSONObject)localObject).put("channel", paramString2);
        paramString2 = this.a;
        localObject = ((JSONObject)localObject).toString();
        if (!paramBoolean) {
          break label80;
        }
        paramString1 = "blocked";
        paramString2.a("youtube", "com.google.android.youtube", (String)localObject, paramString1);
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
      return io.b.b.a(br.a);
      label80:
      paramString1 = "normal";
    }
  }
  
  public io.b.i.a<String> b()
  {
    return this.h.a();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> b(String paramString, int paramInt)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("locked_duration", Integer.valueOf(paramInt));
      localM1.a("device_id", paramString);
      localM1.a("data", localM2);
      localM1.a("type", "phone");
      localM1.a("action", "lock");
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> b(String paramString1, long paramLong, String paramString2)
  {
    return a(paramString1, "monitoring_off", paramLong, paramString2);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.common.g>> b(@NonNull String paramString1, @NonNull String paramString2)
  {
    com.google.gson.m localM = new com.google.gson.m();
    localM.a("email", paramString1);
    localM.a("code", paramString2);
    V("dt_mng | ver_pas");
    return this.d.passwordResetVerification(localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = this.c.j();
    com.google.gson.m localM1 = new com.google.gson.m();
    try
    {
      com.google.gson.m localM2 = new com.google.gson.m();
      localM2.a("mobile", paramString3);
      paramString3 = new com.google.gson.g();
      paramString3.a(localM2);
      localM2 = new com.google.gson.m();
      if (paramString1 != null) {
        localM2.a("first_name", paramString1);
      }
      if (paramString2 != null) {
        localM2.a("last_name", paramString2);
      }
      localM2.a("phones", paramString3);
      localM1.a("device_id", paramString4);
      localM1.a("data", localM2);
      localM1.a("type", "contacts");
      localM1.a("action", "add");
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.postAction2(str, localM1).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<SettingsModel>> b(boolean paramBoolean, @NonNull String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.location.c> b(int paramInt)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 5
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload 5
    //   20: invokevirtual 524	io/realm/dk:a	()V
    //   23: aload_3
    //   24: astore_2
    //   25: aload 4
    //   27: aload_0
    //   28: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   31: aload 5
    //   33: iload_1
    //   34: invokevirtual 2477	ua/com/tim_berners/parental_control/data/b/a:c	(Lio/realm/dk;I)Lio/realm/dy;
    //   37: invokestatic 2480	ua/com/tim_berners/parental_control/data/models/location/c:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   40: aload 5
    //   42: ifnull +60 -> 102
    //   45: aload 5
    //   47: invokevirtual 517	io/realm/dk:close	()V
    //   50: aload 4
    //   52: areturn
    //   53: astore_3
    //   54: goto +8 -> 62
    //   57: astore_3
    //   58: aload_3
    //   59: astore_2
    //   60: aload_3
    //   61: athrow
    //   62: aload 5
    //   64: ifnull +31 -> 95
    //   67: aload_2
    //   68: ifnull +22 -> 90
    //   71: aload 5
    //   73: invokevirtual 517	io/realm/dk:close	()V
    //   76: goto +19 -> 95
    //   79: astore 5
    //   81: aload_2
    //   82: aload 5
    //   84: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   87: goto +8 -> 95
    //   90: aload 5
    //   92: invokevirtual 517	io/realm/dk:close	()V
    //   95: aload_3
    //   96: athrow
    //   97: astore_2
    //   98: aload_2
    //   99: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   102: aload 4
    //   104: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	b
    //   0	105	1	paramInt	int
    //   17	65	2	localObject1	Object
    //   97	2	2	localException	Exception
    //   15	9	3	localObject2	Object
    //   53	1	3	localObject3	Object
    //   57	39	3	localThrowable1	Throwable
    //   7	96	4	localArrayList	ArrayList
    //   12	60	5	localDk	dk
    //   79	12	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   18	23	53	finally
    //   25	40	53	finally
    //   60	62	53	finally
    //   18	23	57	java/lang/Throwable
    //   25	40	57	java/lang/Throwable
    //   71	76	79	java/lang/Throwable
    //   9	14	97	java/lang/Exception
    //   45	50	97	java/lang/Exception
    //   71	76	97	java/lang/Exception
    //   81	87	97	java/lang/Exception
    //   90	95	97	java/lang/Exception
    //   95	97	97	java/lang/Exception
  }
  
  public void b(long paramLong)
  {
    this.c.c(paramLong);
  }
  
  public void b(Location paramLocation, String paramString1, String paramString2)
  {
    try
    {
      ua.com.tim_berners.parental_control.data.models.location.g localG = new ua.com.tim_berners.parental_control.data.models.location.g();
      localG.a(paramString1);
      localG.c(paramString2);
      paramString1 = new LocationModel();
      paramString1.a(paramLocation.getLatitude());
      paramString1.b(paramLocation.getLongitude());
      localG.a(paramString1);
      this.a.a(localG);
      return;
    }
    catch (Exception paramLocation)
    {
      paramLocation.printStackTrace();
    }
  }
  
  public void b(dk paramDk, String paramString)
  {
    this.a.a(paramDk, ah(paramString));
  }
  
  public void b(String paramString)
  {
    this.c.p(paramString);
  }
  
  public void b(String paramString, boolean paramBoolean)
  {
    this.a.a(paramString, paramBoolean);
  }
  
  public void b(boolean paramBoolean)
  {
    this.c.n(paramBoolean);
  }
  
  public boolean ba()
  {
    return (at()) && (ar()) && (as()) && (aE()) && (au()) && (av());
  }
  
  public void bb()
  {
    f(true);
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.b().iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | aut_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bc()
  {
    g(true);
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity"));
      localIntent.addFlags(335544320);
      this.b.startActivity(localIntent);
      V("dt_mng | opn | xi_opt");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void bd()
  {
    B(true);
    boolean bool1 = z();
    boolean bool2 = y();
    if ((!bool1) && (!bool2))
    {
      StringBuilder localStringBuilder2 = null;
      StringBuilder localStringBuilder1;
      try
      {
        localObject2 = ua.com.tim_berners.parental_control.service.a.p(this.b).iterator();
        ResolveInfo localResolveInfo;
        do
        {
          Object localObject1 = localStringBuilder2;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject1 = (Intent)((Iterator)localObject2).next();
          localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
        } while (localResolveInfo == null);
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("dt_mng | opn | aut_lnv_e = ");
        ((StringBuilder)localObject2).append(localException1.toString());
        V(((StringBuilder)localObject2).toString());
        localStringBuilder1 = localStringBuilder2;
      }
      if (localStringBuilder1 != null) {
        try
        {
          localStringBuilder1.addFlags(335544320);
          this.b.startActivity(localStringBuilder1);
          localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("dt_mng | opn | aut_lnv = ");
          localStringBuilder2.append(localStringBuilder1.toString());
          V(localStringBuilder2.toString());
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("dt_mng | opn | aut_lnv_e = ");
          localStringBuilder2.append(localException2.toString());
          V(localStringBuilder2.toString());
        }
      }
      return;
    }
    aR();
  }
  
  public void be()
  {
    p(true);
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.j(this.b).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | aut_hua_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_hua = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_hua_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bf()
  {
    q(true);
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.h(this.b).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | aut_mzu_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_mzu = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_mzu_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bg()
  {
    r(true);
    Log.d("Parental", "======= makeSamsungAutorun");
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.b(this.b).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("======= makeSamsungAutorun = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      Log.d("Parental", ((StringBuilder)localObject2).toString());
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | aut_smg_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_smg = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("======= makeSamsungAutorun = ");
        localStringBuilder2.append(localException2.toString());
        Log.d("Parental", localStringBuilder2.toString());
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_smg_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bh()
  {
    s(true);
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.f(this.b).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | aut_zte_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_zte = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | aut_zte_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bi()
  {
    o(true);
    StringBuilder localStringBuilder2 = null;
    StringBuilder localStringBuilder1;
    try
    {
      localObject2 = ua.com.tim_berners.parental_control.service.a.g(this.b).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localStringBuilder2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = (Intent)((Iterator)localObject2).next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("dt_mng | opn | auto_oppo_e = ");
      ((StringBuilder)localObject2).append(localException1.toString());
      V(((StringBuilder)localObject2).toString());
      localStringBuilder1 = localStringBuilder2;
    }
    if (localStringBuilder1 != null) {
      try
      {
        localStringBuilder1.addFlags(335544320);
        this.b.startActivity(localStringBuilder1);
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | auto_oppo = ");
        localStringBuilder2.append(localStringBuilder1.toString());
        V(localStringBuilder2.toString());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("dt_mng | opn | auto_oppo_e = ");
        localStringBuilder2.append(localException2.toString());
        V(localStringBuilder2.toString());
      }
    }
  }
  
  public void bj()
  {
    e(true);
    Object localObject3 = null;
    Object localObject2;
    try
    {
      Iterator localIterator = ua.com.tim_berners.parental_control.service.a.a().iterator();
      ResolveInfo localResolveInfo;
      do
      {
        Object localObject1 = localObject3;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (Intent)localIterator.next();
        localResolveInfo = this.b.getPackageManager().resolveActivity((Intent)localObject1, 65536);
      } while (localResolveInfo == null);
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      localObject2 = localObject3;
    }
    if (localObject2 != null) {
      try
      {
        localObject2.addFlags(335544320);
        this.b.startActivity(localObject2);
        V("dt_mng | opn | app_prt");
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
  }
  
  public void bk()
  {
    try
    {
      LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent("ua.com.tim_berners.parental_control.StartUpload"));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean bl()
  {
    boolean bool1 = ak();
    boolean bool2 = ar();
    boolean bool3 = as();
    boolean bool4 = at();
    boolean bool5 = aE();
    boolean bool6 = au();
    boolean bool7 = aH();
    boolean bool8 = av();
    boolean bool9 = aG();
    boolean bool10 = aI();
    int m = bool5 ^ true;
    if (!bool1) {
      if ((bool2) && (bool4) && (bool3) && (bool6) && (bool8) && (bool7) && (bool9) && (bool10)) {
        m = 0;
      } else {
        m = 1;
      }
    }
    return m == 0;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.i.b> bm()
  {
    String str = this.c.j();
    return this.d.getPaymentSettings(str).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void bn()
  {
    this.a.c();
  }
  
  public io.b.q<String> bo()
  {
    return io.b.q.a(new bi(this));
  }
  
  /* Error */
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.l.l>> bp()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	ua/com/tim_berners/parental_control/data/b:c	Lua/com/tim_berners/parental_control/data/a/a;
    //   4: invokevirtual 78	ua/com/tim_berners/parental_control/data/a/a:j	()Ljava/lang/String;
    //   7: astore 10
    //   9: new 129	com/google/gson/m
    //   12: dup
    //   13: invokespecial 130	com/google/gson/m:<init>	()V
    //   16: astore 11
    //   18: new 1381	com/google/gson/g
    //   21: dup
    //   22: invokespecial 1382	com/google/gson/g:<init>	()V
    //   25: astore 9
    //   27: aload_0
    //   28: invokespecial 2660	ua/com/tim_berners/parental_control/data/b:bv	()J
    //   31: lstore 5
    //   33: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   36: astore 12
    //   38: aload_0
    //   39: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   42: aload 12
    //   44: invokevirtual 2662	ua/com/tim_berners/parental_control/data/b/a:h	(Lio/realm/dk;)Lio/realm/dy;
    //   47: astore 7
    //   49: new 471	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   56: astore 8
    //   58: aload 8
    //   60: ldc_w 2664
    //   63: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload 7
    //   69: ifnull +586 -> 655
    //   72: aload 7
    //   74: invokevirtual 527	io/realm/dy:size	()I
    //   77: istore_1
    //   78: goto +3 -> 81
    //   81: aload 8
    //   83: iload_1
    //   84: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_0
    //   89: aload 12
    //   91: aload 8
    //   93: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   99: aload 7
    //   101: ifnull +21 -> 122
    //   104: aload 7
    //   106: invokevirtual 527	io/realm/dy:size	()I
    //   109: istore_1
    //   110: iload_1
    //   111: ifne +6 -> 117
    //   114: goto +8 -> 122
    //   117: iconst_0
    //   118: istore_1
    //   119: goto +5 -> 124
    //   122: iconst_1
    //   123: istore_1
    //   124: iload_1
    //   125: ifne +349 -> 474
    //   128: aload 7
    //   130: invokevirtual 359	io/realm/dy:iterator	()Ljava/util/Iterator;
    //   133: astore 7
    //   135: lconst_0
    //   136: lstore_3
    //   137: aload 7
    //   139: invokeinterface 364 1 0
    //   144: ifeq +214 -> 358
    //   147: aload 7
    //   149: invokeinterface 368 1 0
    //   154: checkcast 2668	ua/com/tim_berners/parental_control/data/models/l/k
    //   157: astore 13
    //   159: lload 5
    //   161: aload 13
    //   163: invokevirtual 2669	ua/com/tim_berners/parental_control/data/models/l/k:c	()J
    //   166: lcmp
    //   167: ifge +493 -> 660
    //   170: aload 13
    //   172: invokevirtual 2670	ua/com/tim_berners/parental_control/data/models/l/k:f	()Ljava/lang/String;
    //   175: ifnonnull +6 -> 181
    //   178: goto +482 -> 660
    //   181: new 129	com/google/gson/m
    //   184: dup
    //   185: invokespecial 130	com/google/gson/m:<init>	()V
    //   188: astore 14
    //   190: aload 14
    //   192: ldc_w 1424
    //   195: aload 13
    //   197: invokevirtual 2671	ua/com/tim_berners/parental_control/data/models/l/k:d	()Ljava/lang/String;
    //   200: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: aload 14
    //   205: ldc_w 2673
    //   208: aload 13
    //   210: invokevirtual 2669	ua/com/tim_berners/parental_control/data/models/l/k:c	()J
    //   213: invokestatic 138	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   216: invokevirtual 141	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/Number;)V
    //   219: aload 14
    //   221: ldc -101
    //   223: aload 13
    //   225: invokevirtual 2674	ua/com/tim_berners/parental_control/data/models/l/k:e	()Ljava/lang/String;
    //   228: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   231: aload 14
    //   233: ldc_w 1573
    //   236: aload 13
    //   238: invokevirtual 2675	ua/com/tim_berners/parental_control/data/models/l/k:g	()Ljava/lang/String;
    //   241: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   244: new 129	com/google/gson/m
    //   247: dup
    //   248: invokespecial 130	com/google/gson/m:<init>	()V
    //   251: astore 15
    //   253: new 1544	org/json/JSONObject
    //   256: dup
    //   257: aload 13
    //   259: invokevirtual 2670	ua/com/tim_berners/parental_control/data/models/l/k:f	()Ljava/lang/String;
    //   262: invokespecial 2676	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   265: astore 16
    //   267: aload 16
    //   269: invokevirtual 2680	org/json/JSONObject:names	()Lorg/json/JSONArray;
    //   272: astore 8
    //   274: iconst_0
    //   275: istore_2
    //   276: iload_2
    //   277: aload 8
    //   279: invokevirtual 2683	org/json/JSONArray:length	()I
    //   282: if_icmpge +32 -> 314
    //   285: aload 8
    //   287: iload_2
    //   288: invokevirtual 2686	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   291: astore 17
    //   293: aload 15
    //   295: aload 17
    //   297: aload 16
    //   299: aload 17
    //   301: invokevirtual 2688	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   304: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   307: iload_2
    //   308: iconst_1
    //   309: iadd
    //   310: istore_2
    //   311: goto -35 -> 276
    //   314: aload 14
    //   316: ldc -106
    //   318: aload 15
    //   320: invokevirtual 153	com/google/gson/m:a	(Ljava/lang/String;Lcom/google/gson/j;)V
    //   323: goto +15 -> 338
    //   326: astore 8
    //   328: goto +5 -> 333
    //   331: astore 8
    //   333: aload 8
    //   335: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   338: lload_3
    //   339: aload 13
    //   341: invokevirtual 2669	ua/com/tim_berners/parental_control/data/models/l/k:c	()J
    //   344: invokestatic 1419	java/lang/Math:max	(JJ)J
    //   347: lstore_3
    //   348: aload 9
    //   350: aload 14
    //   352: invokevirtual 1422	com/google/gson/g:a	(Lcom/google/gson/j;)V
    //   355: goto +305 -> 660
    //   358: aload 9
    //   360: invokevirtual 1428	com/google/gson/g:a	()I
    //   363: ifne +34 -> 397
    //   366: getstatic 2691	ua/com/tim_berners/parental_control/data/bk:a	Lio/b/t;
    //   369: invokestatic 1356	io/b/q:a	(Lio/b/t;)Lio/b/q;
    //   372: astore 7
    //   374: aload 12
    //   376: ifnull +18 -> 394
    //   379: aload 12
    //   381: invokevirtual 517	io/realm/dk:close	()V
    //   384: aload 7
    //   386: areturn
    //   387: astore 7
    //   389: lconst_0
    //   390: lstore_3
    //   391: goto +193 -> 584
    //   394: aload 7
    //   396: areturn
    //   397: aload 11
    //   399: ldc_w 2693
    //   402: aload 9
    //   404: invokevirtual 153	com/google/gson/m:a	(Ljava/lang/String;Lcom/google/gson/j;)V
    //   407: new 471	java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   414: astore 7
    //   416: aload 7
    //   418: ldc_w 2695
    //   421: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: pop
    //   425: aload 7
    //   427: aload 9
    //   429: invokevirtual 1428	com/google/gson/g:a	()I
    //   432: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: aload_0
    //   437: aload 12
    //   439: aload 7
    //   441: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   447: goto +29 -> 476
    //   450: astore 7
    //   452: goto +58 -> 510
    //   455: astore 7
    //   457: goto +65 -> 522
    //   460: astore 7
    //   462: lconst_0
    //   463: lstore_3
    //   464: goto +46 -> 510
    //   467: astore 7
    //   469: lconst_0
    //   470: lstore_3
    //   471: goto +51 -> 522
    //   474: lconst_0
    //   475: lstore_3
    //   476: lload_3
    //   477: lstore 5
    //   479: iload_1
    //   480: istore_2
    //   481: aload 12
    //   483: ifnull +111 -> 594
    //   486: aload 12
    //   488: invokevirtual 517	io/realm/dk:close	()V
    //   491: lload_3
    //   492: lstore 5
    //   494: iload_1
    //   495: istore_2
    //   496: goto +98 -> 594
    //   499: astore 7
    //   501: goto +83 -> 584
    //   504: astore 7
    //   506: lconst_0
    //   507: lstore_3
    //   508: iconst_0
    //   509: istore_1
    //   510: aconst_null
    //   511: astore 8
    //   513: goto +22 -> 535
    //   516: astore 7
    //   518: lconst_0
    //   519: lstore_3
    //   520: iconst_0
    //   521: istore_1
    //   522: aload 7
    //   524: athrow
    //   525: astore 9
    //   527: aload 7
    //   529: astore 8
    //   531: aload 9
    //   533: astore 7
    //   535: aload 12
    //   537: ifnull +33 -> 570
    //   540: aload 8
    //   542: ifnull +23 -> 565
    //   545: aload 12
    //   547: invokevirtual 517	io/realm/dk:close	()V
    //   550: goto +20 -> 570
    //   553: astore 9
    //   555: aload 8
    //   557: aload 9
    //   559: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   562: goto +8 -> 570
    //   565: aload 12
    //   567: invokevirtual 517	io/realm/dk:close	()V
    //   570: aload 7
    //   572: athrow
    //   573: astore 7
    //   575: goto +9 -> 584
    //   578: astore 7
    //   580: lconst_0
    //   581: lstore_3
    //   582: iconst_0
    //   583: istore_1
    //   584: aload 7
    //   586: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   589: iload_1
    //   590: istore_2
    //   591: lload_3
    //   592: lstore 5
    //   594: iload_2
    //   595: ifeq +10 -> 605
    //   598: getstatic 2698	ua/com/tim_berners/parental_control/data/bl:a	Lio/b/t;
    //   601: invokestatic 1356	io/b/q:a	(Lio/b/t;)Lio/b/q;
    //   604: areturn
    //   605: aload_0
    //   606: getfield 38	ua/com/tim_berners/parental_control/data/b:d	Lua/com/tim_berners/parental_control/data/network/ParentalService;
    //   609: aload 10
    //   611: aload 11
    //   613: invokeinterface 2701 3 0
    //   618: new 2703	ua/com/tim_berners/parental_control/data/bm
    //   621: dup
    //   622: aload_0
    //   623: lload 5
    //   625: invokespecial 2704	ua/com/tim_berners/parental_control/data/bm:<init>	(Lua/com/tim_berners/parental_control/data/b;J)V
    //   628: invokevirtual 906	io/b/q:a	(Lio/b/d/d;)Lio/b/q;
    //   631: new 2706	ua/com/tim_berners/parental_control/data/bn
    //   634: dup
    //   635: aload_0
    //   636: invokespecial 2707	ua/com/tim_berners/parental_control/data/bn:<init>	(Lua/com/tim_berners/parental_control/data/b;)V
    //   639: invokevirtual 911	io/b/q:b	(Lio/b/d/d;)Lio/b/q;
    //   642: invokestatic 173	io/b/h/a:b	()Lio/b/p;
    //   645: invokevirtual 178	io/b/q:b	(Lio/b/p;)Lio/b/q;
    //   648: invokestatic 182	io/b/a/b/a:a	()Lio/b/p;
    //   651: invokevirtual 184	io/b/q:a	(Lio/b/p;)Lio/b/q;
    //   654: areturn
    //   655: iconst_0
    //   656: istore_1
    //   657: goto -576 -> 81
    //   660: goto -523 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	663	0	this	b
    //   77	580	1	m	int
    //   275	320	2	n	int
    //   136	456	3	l1	long
    //   31	593	5	l2	long
    //   47	338	7	localObject1	Object
    //   387	8	7	localException1	Exception
    //   414	26	7	localStringBuilder	StringBuilder
    //   450	1	7	localObject2	Object
    //   455	1	7	localThrowable1	Throwable
    //   460	1	7	localObject3	Object
    //   467	1	7	localThrowable2	Throwable
    //   499	1	7	localException2	Exception
    //   504	1	7	localObject4	Object
    //   516	12	7	localThrowable3	Throwable
    //   533	38	7	localObject5	Object
    //   573	1	7	localException3	Exception
    //   578	7	7	localException4	Exception
    //   56	230	8	localObject6	Object
    //   326	1	8	localException5	Exception
    //   331	3	8	localException6	Exception
    //   511	45	8	localThrowable4	Throwable
    //   25	403	9	localG	com.google.gson.g
    //   525	7	9	localObject7	Object
    //   553	5	9	localThrowable5	Throwable
    //   7	603	10	str1	String
    //   16	596	11	localM1	com.google.gson.m
    //   36	530	12	localDk	dk
    //   157	183	13	localK	ua.com.tim_berners.parental_control.data.models.l.k
    //   188	163	14	localM2	com.google.gson.m
    //   251	68	15	localM3	com.google.gson.m
    //   265	33	16	localJSONObject	JSONObject
    //   291	9	17	str2	String
    // Exception table:
    //   from	to	target	type
    //   276	307	326	java/lang/Exception
    //   314	323	326	java/lang/Exception
    //   244	274	331	java/lang/Exception
    //   379	384	387	java/lang/Exception
    //   407	447	450	finally
    //   407	447	455	java/lang/Throwable
    //   128	135	460	finally
    //   137	178	460	finally
    //   181	244	460	finally
    //   244	274	460	finally
    //   276	307	460	finally
    //   314	323	460	finally
    //   333	338	460	finally
    //   338	355	460	finally
    //   358	374	460	finally
    //   397	407	460	finally
    //   128	135	467	java/lang/Throwable
    //   137	178	467	java/lang/Throwable
    //   181	244	467	java/lang/Throwable
    //   244	274	467	java/lang/Throwable
    //   276	307	467	java/lang/Throwable
    //   314	323	467	java/lang/Throwable
    //   333	338	467	java/lang/Throwable
    //   338	355	467	java/lang/Throwable
    //   358	374	467	java/lang/Throwable
    //   397	407	467	java/lang/Throwable
    //   486	491	499	java/lang/Exception
    //   38	67	504	finally
    //   72	78	504	finally
    //   81	99	504	finally
    //   104	110	504	finally
    //   38	67	516	java/lang/Throwable
    //   72	78	516	java/lang/Throwable
    //   81	99	516	java/lang/Throwable
    //   104	110	516	java/lang/Throwable
    //   522	525	525	finally
    //   545	550	553	java/lang/Throwable
    //   545	550	573	java/lang/Exception
    //   555	562	573	java/lang/Exception
    //   565	570	573	java/lang/Exception
    //   570	573	573	java/lang/Exception
    //   33	38	578	java/lang/Exception
  }
  
  /* Error */
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> bq()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	ua/com/tim_berners/parental_control/data/b:c	Lua/com/tim_berners/parental_control/data/a/a;
    //   4: invokevirtual 78	ua/com/tim_berners/parental_control/data/a/a:j	()Ljava/lang/String;
    //   7: astore 9
    //   9: new 129	com/google/gson/m
    //   12: dup
    //   13: invokespecial 130	com/google/gson/m:<init>	()V
    //   16: astore 10
    //   18: new 189	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 190	java/util/ArrayList:<init>	()V
    //   25: astore 11
    //   27: iconst_0
    //   28: istore 4
    //   30: iconst_0
    //   31: istore 5
    //   33: iconst_0
    //   34: istore_3
    //   35: iconst_0
    //   36: istore 6
    //   38: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   41: astore 12
    //   43: aconst_null
    //   44: astore 8
    //   46: iload 4
    //   48: istore_2
    //   49: aload 8
    //   51: astore 7
    //   53: iload 5
    //   55: istore_3
    //   56: aload 12
    //   58: invokevirtual 524	io/realm/dk:a	()V
    //   61: iload 4
    //   63: istore_2
    //   64: aload 8
    //   66: astore 7
    //   68: iload 5
    //   70: istore_3
    //   71: new 1381	com/google/gson/g
    //   74: dup
    //   75: invokespecial 1382	com/google/gson/g:<init>	()V
    //   78: astore 13
    //   80: iload 4
    //   82: istore_2
    //   83: aload 8
    //   85: astore 7
    //   87: iload 5
    //   89: istore_3
    //   90: aload_0
    //   91: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   94: aload 12
    //   96: invokevirtual 2711	ua/com/tim_berners/parental_control/data/b/a:i	(Lio/realm/dk;)Lio/realm/dy;
    //   99: astore 14
    //   101: iload 4
    //   103: istore_2
    //   104: aload 8
    //   106: astore 7
    //   108: iload 5
    //   110: istore_3
    //   111: new 471	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   118: astore 15
    //   120: iload 4
    //   122: istore_2
    //   123: aload 8
    //   125: astore 7
    //   127: iload 5
    //   129: istore_3
    //   130: aload 15
    //   132: ldc_w 2713
    //   135: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload 14
    //   141: ifnull +528 -> 669
    //   144: iload 4
    //   146: istore_2
    //   147: aload 8
    //   149: astore 7
    //   151: iload 5
    //   153: istore_3
    //   154: aload 14
    //   156: invokevirtual 527	io/realm/dy:size	()I
    //   159: istore_1
    //   160: goto +3 -> 163
    //   163: iload 4
    //   165: istore_2
    //   166: aload 8
    //   168: astore 7
    //   170: iload 5
    //   172: istore_3
    //   173: aload 15
    //   175: iload_1
    //   176: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: iload 4
    //   182: istore_2
    //   183: aload 8
    //   185: astore 7
    //   187: iload 5
    //   189: istore_3
    //   190: aload_0
    //   191: aload 12
    //   193: aload 15
    //   195: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   201: aload 14
    //   203: ifnull +471 -> 674
    //   206: iload 6
    //   208: istore_1
    //   209: iload 4
    //   211: istore_2
    //   212: aload 8
    //   214: astore 7
    //   216: iload 5
    //   218: istore_3
    //   219: aload 14
    //   221: invokevirtual 527	io/realm/dy:size	()I
    //   224: ifne +6 -> 230
    //   227: goto +447 -> 674
    //   230: iload_1
    //   231: ifne +289 -> 520
    //   234: iload_1
    //   235: istore_2
    //   236: aload 8
    //   238: astore 7
    //   240: iload_1
    //   241: istore_3
    //   242: aload 14
    //   244: invokevirtual 359	io/realm/dy:iterator	()Ljava/util/Iterator;
    //   247: astore 14
    //   249: iload_1
    //   250: istore_2
    //   251: aload 8
    //   253: astore 7
    //   255: iload_1
    //   256: istore_3
    //   257: aload 14
    //   259: invokeinterface 364 1 0
    //   264: ifeq +166 -> 430
    //   267: iload_1
    //   268: istore_2
    //   269: aload 8
    //   271: astore 7
    //   273: iload_1
    //   274: istore_3
    //   275: aload 14
    //   277: invokeinterface 368 1 0
    //   282: checkcast 2715	ua/com/tim_berners/parental_control/data/models/l/h
    //   285: astore 15
    //   287: iload_1
    //   288: istore_2
    //   289: aload 8
    //   291: astore 7
    //   293: iload_1
    //   294: istore_3
    //   295: new 129	com/google/gson/m
    //   298: dup
    //   299: invokespecial 130	com/google/gson/m:<init>	()V
    //   302: astore 16
    //   304: iload_1
    //   305: istore_2
    //   306: aload 8
    //   308: astore 7
    //   310: iload_1
    //   311: istore_3
    //   312: aload 16
    //   314: ldc_w 2717
    //   317: aload 15
    //   319: invokevirtual 2718	ua/com/tim_berners/parental_control/data/models/l/h:f	()Ljava/lang/String;
    //   322: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: iload_1
    //   326: istore_2
    //   327: aload 8
    //   329: astore 7
    //   331: iload_1
    //   332: istore_3
    //   333: aload 16
    //   335: ldc_w 1510
    //   338: aload 15
    //   340: invokevirtual 2719	ua/com/tim_berners/parental_control/data/models/l/h:d	()Ljava/lang/String;
    //   343: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   346: iload_1
    //   347: istore_2
    //   348: aload 8
    //   350: astore 7
    //   352: iload_1
    //   353: istore_3
    //   354: aload 16
    //   356: ldc_w 2721
    //   359: aload 15
    //   361: invokevirtual 2723	ua/com/tim_berners/parental_control/data/models/l/h:e	()J
    //   364: invokestatic 138	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   367: invokevirtual 141	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/Number;)V
    //   370: iload_1
    //   371: istore_2
    //   372: aload 8
    //   374: astore 7
    //   376: iload_1
    //   377: istore_3
    //   378: aload 16
    //   380: ldc_w 2725
    //   383: aload 15
    //   385: invokevirtual 2726	ua/com/tim_berners/parental_control/data/models/l/h:c	()Ljava/lang/String;
    //   388: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   391: iload_1
    //   392: istore_2
    //   393: aload 8
    //   395: astore 7
    //   397: iload_1
    //   398: istore_3
    //   399: aload 11
    //   401: aload 15
    //   403: invokevirtual 2729	ua/com/tim_berners/parental_control/data/models/l/h:j	()Lua/com/tim_berners/parental_control/data/models/l/h;
    //   406: invokeinterface 631 2 0
    //   411: pop
    //   412: iload_1
    //   413: istore_2
    //   414: aload 8
    //   416: astore 7
    //   418: iload_1
    //   419: istore_3
    //   420: aload 13
    //   422: aload 16
    //   424: invokevirtual 1422	com/google/gson/g:a	(Lcom/google/gson/j;)V
    //   427: goto -178 -> 249
    //   430: iload_1
    //   431: istore_2
    //   432: aload 8
    //   434: astore 7
    //   436: iload_1
    //   437: istore_3
    //   438: aload 10
    //   440: ldc_w 2731
    //   443: aload 13
    //   445: invokevirtual 153	com/google/gson/m:a	(Ljava/lang/String;Lcom/google/gson/j;)V
    //   448: iload_1
    //   449: istore_2
    //   450: aload 8
    //   452: astore 7
    //   454: iload_1
    //   455: istore_3
    //   456: new 471	java/lang/StringBuilder
    //   459: dup
    //   460: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   463: astore 14
    //   465: iload_1
    //   466: istore_2
    //   467: aload 8
    //   469: astore 7
    //   471: iload_1
    //   472: istore_3
    //   473: aload 14
    //   475: ldc_w 2733
    //   478: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: pop
    //   482: iload_1
    //   483: istore_2
    //   484: aload 8
    //   486: astore 7
    //   488: iload_1
    //   489: istore_3
    //   490: aload 14
    //   492: aload 13
    //   494: invokevirtual 1428	com/google/gson/g:a	()I
    //   497: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   500: pop
    //   501: iload_1
    //   502: istore_2
    //   503: aload 8
    //   505: astore 7
    //   507: iload_1
    //   508: istore_3
    //   509: aload_0
    //   510: aload 12
    //   512: aload 14
    //   514: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   517: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   520: iload_1
    //   521: istore_3
    //   522: aload 12
    //   524: ifnull +84 -> 608
    //   527: iload_1
    //   528: istore_3
    //   529: aload 12
    //   531: invokevirtual 517	io/realm/dk:close	()V
    //   534: iload_1
    //   535: istore_3
    //   536: goto +72 -> 608
    //   539: astore 8
    //   541: goto +14 -> 555
    //   544: astore 8
    //   546: iload_3
    //   547: istore_2
    //   548: aload 8
    //   550: astore 7
    //   552: aload 8
    //   554: athrow
    //   555: aload 12
    //   557: ifnull +39 -> 596
    //   560: aload 7
    //   562: ifnull +27 -> 589
    //   565: iload_2
    //   566: istore_3
    //   567: aload 12
    //   569: invokevirtual 517	io/realm/dk:close	()V
    //   572: goto +24 -> 596
    //   575: astore 12
    //   577: iload_2
    //   578: istore_3
    //   579: aload 7
    //   581: aload 12
    //   583: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   586: goto +10 -> 596
    //   589: iload_2
    //   590: istore_3
    //   591: aload 12
    //   593: invokevirtual 517	io/realm/dk:close	()V
    //   596: iload_2
    //   597: istore_3
    //   598: aload 8
    //   600: athrow
    //   601: astore 7
    //   603: aload 7
    //   605: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   608: iload_3
    //   609: ifeq +10 -> 619
    //   612: getstatic 2736	ua/com/tim_berners/parental_control/data/bz:a	Lio/b/t;
    //   615: invokestatic 1356	io/b/q:a	(Lio/b/t;)Lio/b/q;
    //   618: areturn
    //   619: aload_0
    //   620: getfield 38	ua/com/tim_berners/parental_control/data/b:d	Lua/com/tim_berners/parental_control/data/network/ParentalService;
    //   623: aload 9
    //   625: aload 10
    //   627: invokeinterface 2739 3 0
    //   632: new 2741	ua/com/tim_berners/parental_control/data/ca
    //   635: dup
    //   636: aload_0
    //   637: aload 11
    //   639: invokespecial 2744	ua/com/tim_berners/parental_control/data/ca:<init>	(Lua/com/tim_berners/parental_control/data/b;Ljava/util/List;)V
    //   642: invokevirtual 906	io/b/q:a	(Lio/b/d/d;)Lio/b/q;
    //   645: new 2746	ua/com/tim_berners/parental_control/data/cc
    //   648: dup
    //   649: aload_0
    //   650: invokespecial 2747	ua/com/tim_berners/parental_control/data/cc:<init>	(Lua/com/tim_berners/parental_control/data/b;)V
    //   653: invokevirtual 911	io/b/q:b	(Lio/b/d/d;)Lio/b/q;
    //   656: invokestatic 173	io/b/h/a:b	()Lio/b/p;
    //   659: invokevirtual 178	io/b/q:b	(Lio/b/p;)Lio/b/q;
    //   662: invokestatic 182	io/b/a/b/a:a	()Lio/b/p;
    //   665: invokevirtual 184	io/b/q:a	(Lio/b/p;)Lio/b/q;
    //   668: areturn
    //   669: iconst_0
    //   670: istore_1
    //   671: goto -508 -> 163
    //   674: iconst_1
    //   675: istore_1
    //   676: goto -446 -> 230
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	679	0	this	b
    //   159	517	1	m	int
    //   48	549	2	n	int
    //   34	575	3	i1	int
    //   28	182	4	i2	int
    //   31	186	5	i3	int
    //   36	171	6	i4	int
    //   51	529	7	localObject1	Object
    //   601	3	7	localException	Exception
    //   44	460	8	localObject2	Object
    //   539	1	8	localObject3	Object
    //   544	55	8	localThrowable1	Throwable
    //   7	617	9	str	String
    //   16	610	10	localM1	com.google.gson.m
    //   25	613	11	localArrayList	ArrayList
    //   41	527	12	localDk	dk
    //   575	17	12	localThrowable2	Throwable
    //   78	415	13	localG	com.google.gson.g
    //   99	414	14	localObject4	Object
    //   118	284	15	localObject5	Object
    //   302	121	16	localM2	com.google.gson.m
    // Exception table:
    //   from	to	target	type
    //   56	61	539	finally
    //   71	80	539	finally
    //   90	101	539	finally
    //   111	120	539	finally
    //   130	139	539	finally
    //   154	160	539	finally
    //   173	180	539	finally
    //   190	201	539	finally
    //   219	227	539	finally
    //   242	249	539	finally
    //   257	267	539	finally
    //   275	287	539	finally
    //   295	304	539	finally
    //   312	325	539	finally
    //   333	346	539	finally
    //   354	370	539	finally
    //   378	391	539	finally
    //   399	412	539	finally
    //   420	427	539	finally
    //   438	448	539	finally
    //   456	465	539	finally
    //   473	482	539	finally
    //   490	501	539	finally
    //   509	520	539	finally
    //   552	555	539	finally
    //   56	61	544	java/lang/Throwable
    //   71	80	544	java/lang/Throwable
    //   90	101	544	java/lang/Throwable
    //   111	120	544	java/lang/Throwable
    //   130	139	544	java/lang/Throwable
    //   154	160	544	java/lang/Throwable
    //   173	180	544	java/lang/Throwable
    //   190	201	544	java/lang/Throwable
    //   219	227	544	java/lang/Throwable
    //   242	249	544	java/lang/Throwable
    //   257	267	544	java/lang/Throwable
    //   275	287	544	java/lang/Throwable
    //   295	304	544	java/lang/Throwable
    //   312	325	544	java/lang/Throwable
    //   333	346	544	java/lang/Throwable
    //   354	370	544	java/lang/Throwable
    //   378	391	544	java/lang/Throwable
    //   399	412	544	java/lang/Throwable
    //   420	427	544	java/lang/Throwable
    //   438	448	544	java/lang/Throwable
    //   456	465	544	java/lang/Throwable
    //   473	482	544	java/lang/Throwable
    //   490	501	544	java/lang/Throwable
    //   509	520	544	java/lang/Throwable
    //   567	572	575	java/lang/Throwable
    //   38	43	601	java/lang/Exception
    //   529	534	601	java/lang/Exception
    //   567	572	601	java/lang/Exception
    //   579	586	601	java/lang/Exception
    //   591	596	601	java/lang/Exception
    //   598	601	601	java/lang/Exception
  }
  
  /* Error */
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> br()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	ua/com/tim_berners/parental_control/data/b:c	Lua/com/tim_berners/parental_control/data/a/a;
    //   4: invokevirtual 78	ua/com/tim_berners/parental_control/data/a/a:j	()Ljava/lang/String;
    //   7: astore 9
    //   9: new 129	com/google/gson/m
    //   12: dup
    //   13: invokespecial 130	com/google/gson/m:<init>	()V
    //   16: astore 10
    //   18: new 189	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 190	java/util/ArrayList:<init>	()V
    //   25: astore 11
    //   27: new 1381	com/google/gson/g
    //   30: dup
    //   31: invokespecial 1382	com/google/gson/g:<init>	()V
    //   34: astore 13
    //   36: iconst_0
    //   37: istore 4
    //   39: iconst_0
    //   40: istore 5
    //   42: iconst_0
    //   43: istore_3
    //   44: iconst_0
    //   45: istore 6
    //   47: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   50: astore 12
    //   52: aconst_null
    //   53: astore 8
    //   55: iload 4
    //   57: istore_2
    //   58: aload 8
    //   60: astore 7
    //   62: iload 5
    //   64: istore_3
    //   65: aload 12
    //   67: invokevirtual 524	io/realm/dk:a	()V
    //   70: iload 4
    //   72: istore_2
    //   73: aload 8
    //   75: astore 7
    //   77: iload 5
    //   79: istore_3
    //   80: aload_0
    //   81: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   84: aload 12
    //   86: invokevirtual 2750	ua/com/tim_berners/parental_control/data/b/a:j	(Lio/realm/dk;)Lio/realm/dy;
    //   89: astore 14
    //   91: iload 4
    //   93: istore_2
    //   94: aload 8
    //   96: astore 7
    //   98: iload 5
    //   100: istore_3
    //   101: new 471	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   108: astore 15
    //   110: iload 4
    //   112: istore_2
    //   113: aload 8
    //   115: astore 7
    //   117: iload 5
    //   119: istore_3
    //   120: aload 15
    //   122: ldc_w 2752
    //   125: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload 14
    //   131: ifnull +674 -> 805
    //   134: iload 4
    //   136: istore_2
    //   137: aload 8
    //   139: astore 7
    //   141: iload 5
    //   143: istore_3
    //   144: aload 14
    //   146: invokevirtual 527	io/realm/dy:size	()I
    //   149: istore_1
    //   150: goto +3 -> 153
    //   153: iload 4
    //   155: istore_2
    //   156: aload 8
    //   158: astore 7
    //   160: iload 5
    //   162: istore_3
    //   163: aload 15
    //   165: iload_1
    //   166: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: iload 4
    //   172: istore_2
    //   173: aload 8
    //   175: astore 7
    //   177: iload 5
    //   179: istore_3
    //   180: aload_0
    //   181: aload 12
    //   183: aload 15
    //   185: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   191: aload 14
    //   193: ifnull +617 -> 810
    //   196: iload 6
    //   198: istore_1
    //   199: iload 4
    //   201: istore_2
    //   202: aload 8
    //   204: astore 7
    //   206: iload 5
    //   208: istore_3
    //   209: aload 14
    //   211: invokevirtual 527	io/realm/dy:size	()I
    //   214: ifne +6 -> 220
    //   217: goto +593 -> 810
    //   220: iload_1
    //   221: ifne +435 -> 656
    //   224: iload_1
    //   225: istore_2
    //   226: aload 8
    //   228: astore 7
    //   230: iload_1
    //   231: istore_3
    //   232: aload 14
    //   234: invokevirtual 359	io/realm/dy:iterator	()Ljava/util/Iterator;
    //   237: astore 14
    //   239: iload_1
    //   240: istore_2
    //   241: aload 8
    //   243: astore 7
    //   245: iload_1
    //   246: istore_3
    //   247: aload 14
    //   249: invokeinterface 364 1 0
    //   254: ifeq +315 -> 569
    //   257: iload_1
    //   258: istore_2
    //   259: aload 8
    //   261: astore 7
    //   263: iload_1
    //   264: istore_3
    //   265: aload 14
    //   267: invokeinterface 368 1 0
    //   272: checkcast 2754	ua/com/tim_berners/parental_control/data/models/l/i
    //   275: astore 15
    //   277: iload_1
    //   278: istore_2
    //   279: aload 8
    //   281: astore 7
    //   283: iload_1
    //   284: istore_3
    //   285: new 129	com/google/gson/m
    //   288: dup
    //   289: invokespecial 130	com/google/gson/m:<init>	()V
    //   292: astore 16
    //   294: iload_1
    //   295: istore_2
    //   296: aload 8
    //   298: astore 7
    //   300: iload_1
    //   301: istore_3
    //   302: aload 16
    //   304: ldc_w 2717
    //   307: aload 15
    //   309: invokevirtual 2755	ua/com/tim_berners/parental_control/data/models/l/i:g	()Ljava/lang/String;
    //   312: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   315: iload_1
    //   316: istore_2
    //   317: aload 8
    //   319: astore 7
    //   321: iload_1
    //   322: istore_3
    //   323: aload 16
    //   325: ldc_w 2721
    //   328: aload 15
    //   330: invokevirtual 2756	ua/com/tim_berners/parental_control/data/models/l/i:h	()Ljava/lang/String;
    //   333: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: iload_1
    //   337: istore_2
    //   338: aload 8
    //   340: astore 7
    //   342: iload_1
    //   343: istore_3
    //   344: aload 16
    //   346: ldc_w 2725
    //   349: aload 15
    //   351: invokevirtual 2757	ua/com/tim_berners/parental_control/data/models/l/i:c	()Ljava/lang/String;
    //   354: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   357: iload_1
    //   358: istore_2
    //   359: aload 8
    //   361: astore 7
    //   363: iload_1
    //   364: istore_3
    //   365: aload 16
    //   367: ldc -101
    //   369: aload 15
    //   371: invokevirtual 2759	ua/com/tim_berners/parental_control/data/models/l/i:d	()I
    //   374: invokestatic 322	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   377: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   380: iload_1
    //   381: istore_2
    //   382: aload 8
    //   384: astore 7
    //   386: iload_1
    //   387: istore_3
    //   388: aload 16
    //   390: ldc_w 2761
    //   393: aload 15
    //   395: invokevirtual 2762	ua/com/tim_berners/parental_control/data/models/l/i:k	()Ljava/lang/String;
    //   398: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   401: iload_1
    //   402: istore_2
    //   403: aload 8
    //   405: astore 7
    //   407: iload_1
    //   408: istore_3
    //   409: aload 16
    //   411: ldc_w 2764
    //   414: aload 15
    //   416: invokevirtual 2765	ua/com/tim_berners/parental_control/data/models/l/i:m	()Ljava/lang/String;
    //   419: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   422: iload_1
    //   423: istore_2
    //   424: aload 8
    //   426: astore 7
    //   428: iload_1
    //   429: istore_3
    //   430: aload 16
    //   432: ldc_w 2767
    //   435: aload 15
    //   437: invokevirtual 2768	ua/com/tim_berners/parental_control/data/models/l/i:n	()Ljava/lang/String;
    //   440: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   443: iload_1
    //   444: istore_2
    //   445: aload 8
    //   447: astore 7
    //   449: iload_1
    //   450: istore_3
    //   451: aload 16
    //   453: ldc_w 2770
    //   456: aload 15
    //   458: invokevirtual 2772	ua/com/tim_berners/parental_control/data/models/l/i:j	()J
    //   461: invokestatic 138	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   464: invokevirtual 141	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/Number;)V
    //   467: iload_1
    //   468: istore_2
    //   469: aload 8
    //   471: astore 7
    //   473: iload_1
    //   474: istore_3
    //   475: aload 16
    //   477: ldc_w 2774
    //   480: aload 15
    //   482: invokevirtual 2775	ua/com/tim_berners/parental_control/data/models/l/i:i	()Ljava/lang/String;
    //   485: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   488: iload_1
    //   489: istore_2
    //   490: aload 8
    //   492: astore 7
    //   494: iload_1
    //   495: istore_3
    //   496: aload 16
    //   498: ldc_w 2777
    //   501: aload 15
    //   503: invokevirtual 2778	ua/com/tim_berners/parental_control/data/models/l/i:e	()Ljava/lang/String;
    //   506: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   509: iload_1
    //   510: istore_2
    //   511: aload 8
    //   513: astore 7
    //   515: iload_1
    //   516: istore_3
    //   517: aload 16
    //   519: ldc_w 2780
    //   522: aload 15
    //   524: invokevirtual 2781	ua/com/tim_berners/parental_control/data/models/l/i:f	()Ljava/lang/String;
    //   527: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   530: iload_1
    //   531: istore_2
    //   532: aload 8
    //   534: astore 7
    //   536: iload_1
    //   537: istore_3
    //   538: aload 11
    //   540: aload 15
    //   542: invokevirtual 2784	ua/com/tim_berners/parental_control/data/models/l/i:r	()Lua/com/tim_berners/parental_control/data/models/l/i;
    //   545: invokeinterface 631 2 0
    //   550: pop
    //   551: iload_1
    //   552: istore_2
    //   553: aload 8
    //   555: astore 7
    //   557: iload_1
    //   558: istore_3
    //   559: aload 13
    //   561: aload 16
    //   563: invokevirtual 1422	com/google/gson/g:a	(Lcom/google/gson/j;)V
    //   566: goto -327 -> 239
    //   569: iload_1
    //   570: istore_2
    //   571: aload 8
    //   573: astore 7
    //   575: iload_1
    //   576: istore_3
    //   577: aload 10
    //   579: ldc_w 2786
    //   582: aload 13
    //   584: invokevirtual 153	com/google/gson/m:a	(Ljava/lang/String;Lcom/google/gson/j;)V
    //   587: iload_1
    //   588: istore_2
    //   589: aload 8
    //   591: astore 7
    //   593: iload_1
    //   594: istore_3
    //   595: new 471	java/lang/StringBuilder
    //   598: dup
    //   599: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   602: astore 14
    //   604: iload_1
    //   605: istore_2
    //   606: aload 8
    //   608: astore 7
    //   610: iload_1
    //   611: istore_3
    //   612: aload 14
    //   614: ldc_w 2788
    //   617: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   620: pop
    //   621: iload_1
    //   622: istore_2
    //   623: aload 8
    //   625: astore 7
    //   627: iload_1
    //   628: istore_3
    //   629: aload 14
    //   631: aload 13
    //   633: invokevirtual 2791	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   636: pop
    //   637: iload_1
    //   638: istore_2
    //   639: aload 8
    //   641: astore 7
    //   643: iload_1
    //   644: istore_3
    //   645: aload_0
    //   646: aload 12
    //   648: aload 14
    //   650: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   653: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   656: iload_1
    //   657: istore_3
    //   658: aload 12
    //   660: ifnull +84 -> 744
    //   663: iload_1
    //   664: istore_3
    //   665: aload 12
    //   667: invokevirtual 517	io/realm/dk:close	()V
    //   670: iload_1
    //   671: istore_3
    //   672: goto +72 -> 744
    //   675: astore 8
    //   677: goto +14 -> 691
    //   680: astore 8
    //   682: iload_3
    //   683: istore_2
    //   684: aload 8
    //   686: astore 7
    //   688: aload 8
    //   690: athrow
    //   691: aload 12
    //   693: ifnull +39 -> 732
    //   696: aload 7
    //   698: ifnull +27 -> 725
    //   701: iload_2
    //   702: istore_3
    //   703: aload 12
    //   705: invokevirtual 517	io/realm/dk:close	()V
    //   708: goto +24 -> 732
    //   711: astore 12
    //   713: iload_2
    //   714: istore_3
    //   715: aload 7
    //   717: aload 12
    //   719: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   722: goto +10 -> 732
    //   725: iload_2
    //   726: istore_3
    //   727: aload 12
    //   729: invokevirtual 517	io/realm/dk:close	()V
    //   732: iload_2
    //   733: istore_3
    //   734: aload 8
    //   736: athrow
    //   737: astore 7
    //   739: aload 7
    //   741: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   744: iload_3
    //   745: ifeq +10 -> 755
    //   748: getstatic 2794	ua/com/tim_berners/parental_control/data/cd:a	Lio/b/t;
    //   751: invokestatic 1356	io/b/q:a	(Lio/b/t;)Lio/b/q;
    //   754: areturn
    //   755: aload_0
    //   756: getfield 38	ua/com/tim_berners/parental_control/data/b:d	Lua/com/tim_berners/parental_control/data/network/ParentalService;
    //   759: aload 9
    //   761: aload 10
    //   763: invokeinterface 2797 3 0
    //   768: new 2799	ua/com/tim_berners/parental_control/data/ce
    //   771: dup
    //   772: aload_0
    //   773: aload 11
    //   775: invokespecial 2800	ua/com/tim_berners/parental_control/data/ce:<init>	(Lua/com/tim_berners/parental_control/data/b;Ljava/util/List;)V
    //   778: invokevirtual 906	io/b/q:a	(Lio/b/d/d;)Lio/b/q;
    //   781: new 2802	ua/com/tim_berners/parental_control/data/cf
    //   784: dup
    //   785: aload_0
    //   786: invokespecial 2803	ua/com/tim_berners/parental_control/data/cf:<init>	(Lua/com/tim_berners/parental_control/data/b;)V
    //   789: invokevirtual 911	io/b/q:b	(Lio/b/d/d;)Lio/b/q;
    //   792: invokestatic 173	io/b/h/a:b	()Lio/b/p;
    //   795: invokevirtual 178	io/b/q:b	(Lio/b/p;)Lio/b/q;
    //   798: invokestatic 182	io/b/a/b/a:a	()Lio/b/p;
    //   801: invokevirtual 184	io/b/q:a	(Lio/b/p;)Lio/b/q;
    //   804: areturn
    //   805: iconst_0
    //   806: istore_1
    //   807: goto -654 -> 153
    //   810: iconst_1
    //   811: istore_1
    //   812: goto -592 -> 220
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	815	0	this	b
    //   149	663	1	m	int
    //   57	676	2	n	int
    //   43	702	3	i1	int
    //   37	163	4	i2	int
    //   40	167	5	i3	int
    //   45	152	6	i4	int
    //   60	656	7	localObject1	Object
    //   737	3	7	localException	Exception
    //   53	587	8	localObject2	Object
    //   675	1	8	localObject3	Object
    //   680	55	8	localThrowable1	Throwable
    //   7	753	9	str	String
    //   16	746	10	localM1	com.google.gson.m
    //   25	749	11	localArrayList	ArrayList
    //   50	654	12	localDk	dk
    //   711	17	12	localThrowable2	Throwable
    //   34	598	13	localG	com.google.gson.g
    //   89	560	14	localObject4	Object
    //   108	433	15	localObject5	Object
    //   292	270	16	localM2	com.google.gson.m
    // Exception table:
    //   from	to	target	type
    //   65	70	675	finally
    //   80	91	675	finally
    //   101	110	675	finally
    //   120	129	675	finally
    //   144	150	675	finally
    //   163	170	675	finally
    //   180	191	675	finally
    //   209	217	675	finally
    //   232	239	675	finally
    //   247	257	675	finally
    //   265	277	675	finally
    //   285	294	675	finally
    //   302	315	675	finally
    //   323	336	675	finally
    //   344	357	675	finally
    //   365	380	675	finally
    //   388	401	675	finally
    //   409	422	675	finally
    //   430	443	675	finally
    //   451	467	675	finally
    //   475	488	675	finally
    //   496	509	675	finally
    //   517	530	675	finally
    //   538	551	675	finally
    //   559	566	675	finally
    //   577	587	675	finally
    //   595	604	675	finally
    //   612	621	675	finally
    //   629	637	675	finally
    //   645	656	675	finally
    //   688	691	675	finally
    //   65	70	680	java/lang/Throwable
    //   80	91	680	java/lang/Throwable
    //   101	110	680	java/lang/Throwable
    //   120	129	680	java/lang/Throwable
    //   144	150	680	java/lang/Throwable
    //   163	170	680	java/lang/Throwable
    //   180	191	680	java/lang/Throwable
    //   209	217	680	java/lang/Throwable
    //   232	239	680	java/lang/Throwable
    //   247	257	680	java/lang/Throwable
    //   265	277	680	java/lang/Throwable
    //   285	294	680	java/lang/Throwable
    //   302	315	680	java/lang/Throwable
    //   323	336	680	java/lang/Throwable
    //   344	357	680	java/lang/Throwable
    //   365	380	680	java/lang/Throwable
    //   388	401	680	java/lang/Throwable
    //   409	422	680	java/lang/Throwable
    //   430	443	680	java/lang/Throwable
    //   451	467	680	java/lang/Throwable
    //   475	488	680	java/lang/Throwable
    //   496	509	680	java/lang/Throwable
    //   517	530	680	java/lang/Throwable
    //   538	551	680	java/lang/Throwable
    //   559	566	680	java/lang/Throwable
    //   577	587	680	java/lang/Throwable
    //   595	604	680	java/lang/Throwable
    //   612	621	680	java/lang/Throwable
    //   629	637	680	java/lang/Throwable
    //   645	656	680	java/lang/Throwable
    //   703	708	711	java/lang/Throwable
    //   47	52	737	java/lang/Exception
    //   665	670	737	java/lang/Exception
    //   703	708	737	java/lang/Exception
    //   715	722	737	java/lang/Exception
    //   727	732	737	java/lang/Exception
    //   734	737	737	java/lang/Exception
  }
  
  public void bs()
  {
    this.a.e();
  }
  
  public io.b.i.a<ua.com.tim_berners.parental_control.data.models.b.b> c()
  {
    return this.h.b();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.a.d>> c(@Nullable String paramString1, @Nullable String paramString2)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString1 != null) {
      localM.a("name", paramString1);
    }
    if (paramString2 != null) {
      localM.a("role", paramString2);
    }
    paramString1 = new StringBuilder();
    paramString1.append("dt_mng | chg_rl = ");
    paramString1.append(paramString2);
    V(paramString1.toString());
    return this.d.changeMyDeviceInfo(str, localM).a(new cb(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.i.a> c(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString1 != null) {
      localM.a("mode", paramString1);
    }
    if (paramString2 != null) {
      localM.a("period", paramString2);
    }
    if (paramString3 != null) {
      localM.a("lang", paramString3);
    }
    return this.d.paymentWithCard(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.m.b>> c(@NonNull String paramString1, @NonNull String paramString2, boolean paramBoolean)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("category_id", paramString1);
      localM.a("block", Boolean.valueOf(paramBoolean));
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    V("dt_mng | upl_cat | blk ");
    return this.d.blockWebCategory(str, paramString2, localM).a(new bw(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public ua.com.tim_berners.parental_control.data.models.c.f c(dk paramDk, String paramString)
  {
    return this.a.n(paramDk, paramString);
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.device.a c(int paramInt)
  {
    // Byte code:
    //   0: new 781	java/text/SimpleDateFormat
    //   3: dup
    //   4: ldc_w 796
    //   7: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   10: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   13: astore 4
    //   15: new 781	java/text/SimpleDateFormat
    //   18: dup
    //   19: ldc_w 796
    //   22: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   25: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   28: astore 7
    //   30: aload 7
    //   32: ldc_w 802
    //   35: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   38: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   41: aconst_null
    //   42: astore 5
    //   44: aconst_null
    //   45: astore_2
    //   46: aload 5
    //   48: astore_3
    //   49: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   52: astore 6
    //   54: aload 6
    //   56: invokevirtual 524	io/realm/dk:a	()V
    //   59: aload_0
    //   60: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   63: aload 6
    //   65: iload_1
    //   66: invokevirtual 1323	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;I)Lua/com/tim_berners/parental_control/data/models/device/Device;
    //   69: astore_3
    //   70: aload_3
    //   71: ifnull +12 -> 83
    //   74: aload_3
    //   75: aload 4
    //   77: aload 7
    //   79: invokestatic 2847	ua/com/tim_berners/parental_control/data/models/device/a:a	(Lua/com/tim_berners/parental_control/data/models/device/Device;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)Lua/com/tim_berners/parental_control/data/models/device/a;
    //   82: astore_2
    //   83: aload_2
    //   84: astore_3
    //   85: aload 6
    //   87: ifnull +77 -> 164
    //   90: aload_2
    //   91: astore_3
    //   92: aload 6
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: aload_2
    //   98: areturn
    //   99: astore 4
    //   101: aconst_null
    //   102: astore_2
    //   103: goto +8 -> 111
    //   106: astore_2
    //   107: aload_2
    //   108: athrow
    //   109: astore 4
    //   111: aload 6
    //   113: ifnull +40 -> 153
    //   116: aload_2
    //   117: ifnull +28 -> 145
    //   120: aload 5
    //   122: astore_3
    //   123: aload 6
    //   125: invokevirtual 517	io/realm/dk:close	()V
    //   128: goto +25 -> 153
    //   131: astore 6
    //   133: aload 5
    //   135: astore_3
    //   136: aload_2
    //   137: aload 6
    //   139: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   142: goto +11 -> 153
    //   145: aload 5
    //   147: astore_3
    //   148: aload 6
    //   150: invokevirtual 517	io/realm/dk:close	()V
    //   153: aload 5
    //   155: astore_3
    //   156: aload 4
    //   158: athrow
    //   159: astore_2
    //   160: aload_2
    //   161: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   164: aload_3
    //   165: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	166	0	this	b
    //   0	166	1	paramInt	int
    //   45	58	2	localA	ua.com.tim_berners.parental_control.data.models.device.a
    //   106	31	2	localThrowable1	Throwable
    //   159	2	2	localException	Exception
    //   48	117	3	localObject1	Object
    //   13	63	4	localSimpleDateFormat1	SimpleDateFormat
    //   99	1	4	localObject2	Object
    //   109	48	4	localObject3	Object
    //   42	112	5	localObject4	Object
    //   52	72	6	localDk	dk
    //   131	18	6	localThrowable2	Throwable
    //   28	50	7	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   54	70	99	finally
    //   74	83	99	finally
    //   54	70	106	java/lang/Throwable
    //   74	83	106	java/lang/Throwable
    //   107	109	109	finally
    //   123	128	131	java/lang/Throwable
    //   49	54	159	java/lang/Exception
    //   92	97	159	java/lang/Exception
    //   123	128	159	java/lang/Exception
    //   136	142	159	java/lang/Exception
    //   148	153	159	java/lang/Exception
    //   156	159	159	java/lang/Exception
  }
  
  public void c(long paramLong)
  {
    this.c.g(paramLong);
  }
  
  public void c(String paramString)
  {
    this.c.q(paramString);
  }
  
  public void c(boolean paramBoolean)
  {
    this.c.m(paramBoolean);
  }
  
  public io.b.b d(String paramString1, String paramString2, String paramString3)
  {
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString1 != null) {
      localM.a("email", paramString1);
    }
    if (paramString2 != null) {
      localM.a("message", paramString2);
    }
    if (paramString3 != null) {
      localM.a("name", paramString3);
    }
    paramString1 = an();
    if (paramString1 != null) {
      localM.a("device_id", paramString1);
    }
    V("dt_mng | sup_snd = ");
    return this.d.sendMessageToSupport(localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.c.h> d(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 7
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 8
    //   39: new 781	java/text/SimpleDateFormat
    //   42: dup
    //   43: ldc_w 796
    //   46: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   49: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   52: astore 9
    //   54: aload 9
    //   56: ldc_w 802
    //   59: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   62: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   65: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   68: astore 6
    //   70: aconst_null
    //   71: astore 4
    //   73: aload 4
    //   75: astore_3
    //   76: aload 6
    //   78: invokevirtual 524	io/realm/dk:a	()V
    //   81: aload 4
    //   83: astore_3
    //   84: aload 5
    //   86: aload_0
    //   87: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   90: aload 6
    //   92: aload_1
    //   93: aload_2
    //   94: invokevirtual 2893	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;)Lio/realm/dy;
    //   97: aload 9
    //   99: aload 7
    //   101: aload 8
    //   103: invokestatic 2896	ua/com/tim_berners/parental_control/data/models/c/h:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   106: aload 6
    //   108: ifnull +58 -> 166
    //   111: aload 6
    //   113: invokevirtual 517	io/realm/dk:close	()V
    //   116: aload 5
    //   118: areturn
    //   119: astore_1
    //   120: goto +8 -> 128
    //   123: astore_1
    //   124: aload_1
    //   125: astore_3
    //   126: aload_1
    //   127: athrow
    //   128: aload 6
    //   130: ifnull +29 -> 159
    //   133: aload_3
    //   134: ifnull +20 -> 154
    //   137: aload 6
    //   139: invokevirtual 517	io/realm/dk:close	()V
    //   142: goto +17 -> 159
    //   145: astore_2
    //   146: aload_3
    //   147: aload_2
    //   148: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   151: goto +8 -> 159
    //   154: aload 6
    //   156: invokevirtual 517	io/realm/dk:close	()V
    //   159: aload_1
    //   160: athrow
    //   161: astore_1
    //   162: aload_1
    //   163: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   166: aload 5
    //   168: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	b
    //   0	169	1	paramString1	String
    //   0	169	2	paramString2	String
    //   75	72	3	localObject1	Object
    //   71	11	4	localObject2	Object
    //   7	160	5	localArrayList	ArrayList
    //   68	87	6	localDk	dk
    //   22	78	7	localSimpleDateFormat1	SimpleDateFormat
    //   37	65	8	localSimpleDateFormat2	SimpleDateFormat
    //   52	46	9	localSimpleDateFormat3	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   76	81	119	finally
    //   84	106	119	finally
    //   126	128	119	finally
    //   76	81	123	java/lang/Throwable
    //   84	106	123	java/lang/Throwable
    //   137	142	145	java/lang/Throwable
    //   65	70	161	java/lang/Exception
    //   111	116	161	java/lang/Exception
    //   137	142	161	java/lang/Exception
    //   146	151	161	java/lang/Exception
    //   154	159	161	java/lang/Exception
    //   159	161	161	java/lang/Exception
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.c.k d(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_2
    //   5: aload 5
    //   7: astore_3
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 6
    //   13: aload 6
    //   15: invokevirtual 524	io/realm/dk:a	()V
    //   18: aload_0
    //   19: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   22: aload 6
    //   24: iload_1
    //   25: invokevirtual 2901	ua/com/tim_berners/parental_control/data/b/a:b	(Lio/realm/dk;I)Lua/com/tim_berners/parental_control/data/models/c/a;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +12 -> 42
    //   33: aload_3
    //   34: aload_0
    //   35: getfield 52	ua/com/tim_berners/parental_control/data/b:e	J
    //   38: invokestatic 868	ua/com/tim_berners/parental_control/data/models/c/k:a	(Lua/com/tim_berners/parental_control/data/models/c/a;J)Lua/com/tim_berners/parental_control/data/models/c/k;
    //   41: astore_2
    //   42: aload_2
    //   43: astore_3
    //   44: aload 6
    //   46: ifnull +77 -> 123
    //   49: aload_2
    //   50: astore_3
    //   51: aload 6
    //   53: invokevirtual 517	io/realm/dk:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: astore 4
    //   60: aconst_null
    //   61: astore_2
    //   62: goto +8 -> 70
    //   65: astore_2
    //   66: aload_2
    //   67: athrow
    //   68: astore 4
    //   70: aload 6
    //   72: ifnull +40 -> 112
    //   75: aload_2
    //   76: ifnull +28 -> 104
    //   79: aload 5
    //   81: astore_3
    //   82: aload 6
    //   84: invokevirtual 517	io/realm/dk:close	()V
    //   87: goto +25 -> 112
    //   90: astore 6
    //   92: aload 5
    //   94: astore_3
    //   95: aload_2
    //   96: aload 6
    //   98: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   101: goto +11 -> 112
    //   104: aload 5
    //   106: astore_3
    //   107: aload 6
    //   109: invokevirtual 517	io/realm/dk:close	()V
    //   112: aload 5
    //   114: astore_3
    //   115: aload 4
    //   117: athrow
    //   118: astore_2
    //   119: aload_2
    //   120: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   123: aload_3
    //   124: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	this	b
    //   0	125	1	paramInt	int
    //   4	58	2	localK	ua.com.tim_berners.parental_control.data.models.c.k
    //   65	31	2	localThrowable1	Throwable
    //   118	2	2	localException	Exception
    //   7	117	3	localObject1	Object
    //   58	1	4	localObject2	Object
    //   68	48	4	localObject3	Object
    //   1	112	5	localObject4	Object
    //   11	72	6	localDk	dk
    //   90	18	6	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   13	29	58	finally
    //   33	42	58	finally
    //   13	29	65	java/lang/Throwable
    //   33	42	65	java/lang/Throwable
    //   66	68	68	finally
    //   82	87	90	java/lang/Throwable
    //   8	13	118	java/lang/Exception
    //   51	56	118	java/lang/Exception
    //   82	87	118	java/lang/Exception
    //   95	101	118	java/lang/Exception
    //   107	112	118	java/lang/Exception
    //   115	118	118	java/lang/Exception
  }
  
  public void d(long paramLong)
  {
    this.c.f(paramLong);
  }
  
  public void d(String paramString)
  {
    this.c.c(paramString);
  }
  
  public void d(boolean paramBoolean)
  {
    this.c.a(paramBoolean);
  }
  
  public boolean d()
  {
    return ua.com.tim_berners.parental_control.d.t.a(this.b);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.k.f>> e(@NonNull String paramString1, @NonNull String paramString2)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("sms_chat_msgs_");
    ((StringBuilder)localObject).append(paramString2);
    ((StringBuilder)localObject).append("_");
    ((StringBuilder)localObject).append(paramString1);
    localObject = ((StringBuilder)localObject).toString();
    if (!ab(paramString1)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.getChatSms(str, paramString1, paramString2, l).a(new au(this, (String)localObject, paramString2, paramString1)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.l.d>> e(@NonNull String paramString1, String paramString2, String paramString3)
  {
    String str = this.c.j();
    return this.d.downloadMessengerChatMessagesList(str, paramString1, paramString2, paramString3).a(new ch(this, paramString1, paramString2, paramString3)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.k.c> e(long paramLong)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 7
    //   24: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   27: astore 6
    //   29: aconst_null
    //   30: astore 4
    //   32: aload 4
    //   34: astore_3
    //   35: aload 6
    //   37: invokevirtual 524	io/realm/dk:a	()V
    //   40: aload 4
    //   42: astore_3
    //   43: aload 5
    //   45: aload_0
    //   46: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   49: aload 6
    //   51: lload_1
    //   52: invokevirtual 2958	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;J)Lio/realm/dy;
    //   55: aload 7
    //   57: aload 7
    //   59: invokestatic 2961	ua/com/tim_berners/parental_control/data/models/k/c:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   62: aload 6
    //   64: ifnull +65 -> 129
    //   67: aload 6
    //   69: invokevirtual 517	io/realm/dk:close	()V
    //   72: aload 5
    //   74: areturn
    //   75: astore 4
    //   77: goto +11 -> 88
    //   80: astore 4
    //   82: aload 4
    //   84: astore_3
    //   85: aload 4
    //   87: athrow
    //   88: aload 6
    //   90: ifnull +31 -> 121
    //   93: aload_3
    //   94: ifnull +22 -> 116
    //   97: aload 6
    //   99: invokevirtual 517	io/realm/dk:close	()V
    //   102: goto +19 -> 121
    //   105: astore 6
    //   107: aload_3
    //   108: aload 6
    //   110: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   113: goto +8 -> 121
    //   116: aload 6
    //   118: invokevirtual 517	io/realm/dk:close	()V
    //   121: aload 4
    //   123: athrow
    //   124: astore_3
    //   125: aload_3
    //   126: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   129: aload 5
    //   131: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	this	b
    //   0	132	1	paramLong	long
    //   34	74	3	localObject1	Object
    //   124	2	3	localException	Exception
    //   30	11	4	localObject2	Object
    //   75	1	4	localObject3	Object
    //   80	42	4	localThrowable1	Throwable
    //   7	123	5	localArrayList	ArrayList
    //   27	71	6	localDk	dk
    //   105	12	6	localThrowable2	Throwable
    //   22	36	7	localSimpleDateFormat	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   35	40	75	finally
    //   43	62	75	finally
    //   85	88	75	finally
    //   35	40	80	java/lang/Throwable
    //   43	62	80	java/lang/Throwable
    //   97	102	105	java/lang/Throwable
    //   24	29	124	java/lang/Exception
    //   67	72	124	java/lang/Exception
    //   97	102	124	java/lang/Exception
    //   107	113	124	java/lang/Exception
    //   116	121	124	java/lang/Exception
    //   121	124	124	java/lang/Exception
  }
  
  public void e(int paramInt)
  {
    this.a.b(paramInt);
  }
  
  public void e(String paramString)
  {
    this.c.d(paramString);
  }
  
  public void e(boolean paramBoolean)
  {
    this.c.c(paramBoolean);
  }
  
  public boolean e()
  {
    return this.c.b();
  }
  
  public String f()
  {
    return this.c.z();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.l.e> f(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 7
    //   14: aconst_null
    //   15: astore 5
    //   17: aload 5
    //   19: astore 4
    //   21: aload 7
    //   23: invokevirtual 524	io/realm/dk:a	()V
    //   26: aload 5
    //   28: astore 4
    //   30: aload 6
    //   32: aload_0
    //   33: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   36: aload 7
    //   38: aload_1
    //   39: aload_2
    //   40: aload_3
    //   41: invokevirtual 3004	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/realm/dy;
    //   44: invokestatic 3007	ua/com/tim_berners/parental_control/data/models/l/e:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   47: aload 7
    //   49: ifnull +61 -> 110
    //   52: aload 7
    //   54: invokevirtual 517	io/realm/dk:close	()V
    //   57: aload 6
    //   59: areturn
    //   60: astore_1
    //   61: goto +9 -> 70
    //   64: astore_1
    //   65: aload_1
    //   66: astore 4
    //   68: aload_1
    //   69: athrow
    //   70: aload 7
    //   72: ifnull +31 -> 103
    //   75: aload 4
    //   77: ifnull +21 -> 98
    //   80: aload 7
    //   82: invokevirtual 517	io/realm/dk:close	()V
    //   85: goto +18 -> 103
    //   88: astore_2
    //   89: aload 4
    //   91: aload_2
    //   92: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   95: goto +8 -> 103
    //   98: aload 7
    //   100: invokevirtual 517	io/realm/dk:close	()V
    //   103: aload_1
    //   104: athrow
    //   105: astore_1
    //   106: aload_1
    //   107: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   110: aload 6
    //   112: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	b
    //   0	113	1	paramString1	String
    //   0	113	2	paramString2	String
    //   0	113	3	paramString3	String
    //   19	71	4	localObject1	Object
    //   15	12	5	localObject2	Object
    //   7	104	6	localArrayList	ArrayList
    //   12	87	7	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   21	26	60	finally
    //   30	47	60	finally
    //   68	70	60	finally
    //   21	26	64	java/lang/Throwable
    //   30	47	64	java/lang/Throwable
    //   80	85	88	java/lang/Throwable
    //   9	14	105	java/lang/Exception
    //   52	57	105	java/lang/Exception
    //   80	85	105	java/lang/Exception
    //   89	95	105	java/lang/Exception
    //   98	103	105	java/lang/Exception
    //   103	105	105	java/lang/Exception
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.location.c f(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload 4
    //   8: astore_3
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 6
    //   14: aload 6
    //   16: invokevirtual 524	io/realm/dk:a	()V
    //   19: aload_0
    //   20: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   23: aload 6
    //   25: aload_1
    //   26: invokestatic 1317	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   29: invokevirtual 1320	java/lang/Integer:intValue	()I
    //   32: aload_2
    //   33: invokevirtual 3012	ua/com/tim_berners/parental_control/data/b/a:a	(Lio/realm/dk;ILjava/lang/String;)Lua/com/tim_berners/parental_control/data/models/location/MonitoringZone;
    //   36: astore_2
    //   37: aload 5
    //   39: astore_1
    //   40: aload_2
    //   41: ifnull +8 -> 49
    //   44: aload_2
    //   45: invokestatic 3015	ua/com/tim_berners/parental_control/data/models/location/c:a	(Lua/com/tim_berners/parental_control/data/models/location/MonitoringZone;)Lua/com/tim_berners/parental_control/data/models/location/c;
    //   48: astore_1
    //   49: aload_1
    //   50: astore_3
    //   51: aload 6
    //   53: ifnull +74 -> 127
    //   56: aload_1
    //   57: astore_3
    //   58: aload 6
    //   60: invokevirtual 517	io/realm/dk:close	()V
    //   63: aload_1
    //   64: areturn
    //   65: astore_1
    //   66: aconst_null
    //   67: astore_2
    //   68: goto +7 -> 75
    //   71: astore_2
    //   72: aload_2
    //   73: athrow
    //   74: astore_1
    //   75: aload 6
    //   77: ifnull +40 -> 117
    //   80: aload_2
    //   81: ifnull +28 -> 109
    //   84: aload 4
    //   86: astore_3
    //   87: aload 6
    //   89: invokevirtual 517	io/realm/dk:close	()V
    //   92: goto +25 -> 117
    //   95: astore 5
    //   97: aload 4
    //   99: astore_3
    //   100: aload_2
    //   101: aload 5
    //   103: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   106: goto +11 -> 117
    //   109: aload 4
    //   111: astore_3
    //   112: aload 6
    //   114: invokevirtual 517	io/realm/dk:close	()V
    //   117: aload 4
    //   119: astore_3
    //   120: aload_1
    //   121: athrow
    //   122: astore_1
    //   123: aload_1
    //   124: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   127: aload_3
    //   128: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	this	b
    //   0	129	1	paramString1	String
    //   0	129	2	paramString2	String
    //   8	120	3	localObject1	Object
    //   1	117	4	localObject2	Object
    //   4	34	5	localObject3	Object
    //   95	7	5	localThrowable	Throwable
    //   12	101	6	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   14	37	65	finally
    //   44	49	65	finally
    //   14	37	71	java/lang/Throwable
    //   44	49	71	java/lang/Throwable
    //   72	74	74	finally
    //   87	92	95	java/lang/Throwable
    //   9	14	122	java/lang/Exception
    //   58	63	122	java/lang/Exception
    //   87	92	122	java/lang/Exception
    //   100	106	122	java/lang/Exception
    //   112	117	122	java/lang/Exception
    //   120	122	122	java/lang/Exception
  }
  
  public void f(long paramLong)
  {
    this.a.c(paramLong);
  }
  
  public void f(String paramString)
  {
    this.c.i(paramString);
  }
  
  public void f(boolean paramBoolean)
  {
    this.c.d(paramBoolean);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<Object>> g(String paramString)
  {
    Object localObject = new File(paramString);
    paramString = this.c.j();
    RequestBody localRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), (File)localObject);
    localObject = MultipartBody.Part.createFormData("avatar", ((File)localObject).getName(), localRequestBody);
    return this.d.uploadAvatarToMyDevice(paramString, (MultipartBody.Part)localObject).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.i.a> g(@Nullable String paramString1, @Nullable String paramString2)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    if (paramString1 != null) {
      localM.a("code", paramString1);
    }
    if (paramString2 != null) {
      localM.a("lang", paramString2);
    }
    return this.d.paymentWithCode(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void g(long paramLong)
  {
    this.a.b(paramLong);
  }
  
  public void g(boolean paramBoolean)
  {
    this.c.e(paramBoolean);
  }
  
  public boolean g()
  {
    return this.c.B();
  }
  
  public io.b.b h(@NonNull String paramString)
  {
    com.google.gson.m localM = new com.google.gson.m();
    localM.a("email", paramString);
    V("dt_mng | fgt_pas");
    return this.d.forgotPassword(localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.l.m> h(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 794
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 7
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 796
    //   31: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 8
    //   39: aload 8
    //   41: ldc_w 802
    //   44: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   47: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   50: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   53: astore 6
    //   55: aconst_null
    //   56: astore 4
    //   58: aload 4
    //   60: astore_3
    //   61: aload 6
    //   63: invokevirtual 524	io/realm/dk:a	()V
    //   66: aload 4
    //   68: astore_3
    //   69: aload 5
    //   71: aload_0
    //   72: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   75: aload 6
    //   77: aload_1
    //   78: aload_2
    //   79: invokevirtual 3134	ua/com/tim_berners/parental_control/data/b/a:b	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;)Lio/realm/dy;
    //   82: aload 8
    //   84: aload 7
    //   86: invokestatic 3137	ua/com/tim_berners/parental_control/data/models/l/m:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   89: aload 6
    //   91: ifnull +58 -> 149
    //   94: aload 6
    //   96: invokevirtual 517	io/realm/dk:close	()V
    //   99: aload 5
    //   101: areturn
    //   102: astore_1
    //   103: goto +8 -> 111
    //   106: astore_1
    //   107: aload_1
    //   108: astore_3
    //   109: aload_1
    //   110: athrow
    //   111: aload 6
    //   113: ifnull +29 -> 142
    //   116: aload_3
    //   117: ifnull +20 -> 137
    //   120: aload 6
    //   122: invokevirtual 517	io/realm/dk:close	()V
    //   125: goto +17 -> 142
    //   128: astore_2
    //   129: aload_3
    //   130: aload_2
    //   131: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   134: goto +8 -> 142
    //   137: aload 6
    //   139: invokevirtual 517	io/realm/dk:close	()V
    //   142: aload_1
    //   143: athrow
    //   144: astore_1
    //   145: aload_1
    //   146: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   149: aload 5
    //   151: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	this	b
    //   0	152	1	paramString1	String
    //   0	152	2	paramString2	String
    //   60	70	3	localObject1	Object
    //   56	11	4	localObject2	Object
    //   7	143	5	localArrayList	ArrayList
    //   53	85	6	localDk	dk
    //   22	63	7	localSimpleDateFormat1	SimpleDateFormat
    //   37	46	8	localSimpleDateFormat2	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   61	66	102	finally
    //   69	89	102	finally
    //   109	111	102	finally
    //   61	66	106	java/lang/Throwable
    //   69	89	106	java/lang/Throwable
    //   120	125	128	java/lang/Throwable
    //   50	55	144	java/lang/Exception
    //   94	99	144	java/lang/Exception
    //   120	125	144	java/lang/Exception
    //   129	134	144	java/lang/Exception
    //   137	142	144	java/lang/Exception
    //   142	144	144	java/lang/Exception
  }
  
  public void h(long paramLong)
  {
    this.a.c(paramLong);
  }
  
  public void h(boolean paramBoolean)
  {
    this.c.j(paramBoolean);
  }
  
  public boolean h()
  {
    return this.c.A();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<Device>> i(String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("device_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    long l = ai((String)localObject);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return this.d.getUserDeviceInfo(str, paramString, l).a(new bg(this, localSimpleDateFormat, (String)localObject)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.m.i i(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload 4
    //   8: astore_3
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 6
    //   14: aload 6
    //   16: invokevirtual 524	io/realm/dk:a	()V
    //   19: aload_0
    //   20: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   23: aload 6
    //   25: aload_1
    //   26: aload_2
    //   27: invokevirtual 3159	ua/com/tim_berners/parental_control/data/b/a:c	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;)Lua/com/tim_berners/parental_control/data/models/m/f;
    //   30: astore_2
    //   31: aload 5
    //   33: astore_1
    //   34: aload_2
    //   35: ifnull +8 -> 43
    //   38: aload_2
    //   39: invokestatic 3164	ua/com/tim_berners/parental_control/data/models/m/i:a	(Lua/com/tim_berners/parental_control/data/models/m/f;)Lua/com/tim_berners/parental_control/data/models/m/i;
    //   42: astore_1
    //   43: aload_1
    //   44: astore_3
    //   45: aload 6
    //   47: ifnull +74 -> 121
    //   50: aload_1
    //   51: astore_3
    //   52: aload 6
    //   54: invokevirtual 517	io/realm/dk:close	()V
    //   57: aload_1
    //   58: areturn
    //   59: astore_1
    //   60: aconst_null
    //   61: astore_2
    //   62: goto +7 -> 69
    //   65: astore_2
    //   66: aload_2
    //   67: athrow
    //   68: astore_1
    //   69: aload 6
    //   71: ifnull +40 -> 111
    //   74: aload_2
    //   75: ifnull +28 -> 103
    //   78: aload 4
    //   80: astore_3
    //   81: aload 6
    //   83: invokevirtual 517	io/realm/dk:close	()V
    //   86: goto +25 -> 111
    //   89: astore 5
    //   91: aload 4
    //   93: astore_3
    //   94: aload_2
    //   95: aload 5
    //   97: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   100: goto +11 -> 111
    //   103: aload 4
    //   105: astore_3
    //   106: aload 6
    //   108: invokevirtual 517	io/realm/dk:close	()V
    //   111: aload 4
    //   113: astore_3
    //   114: aload_1
    //   115: athrow
    //   116: astore_1
    //   117: aload_1
    //   118: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   121: aload_3
    //   122: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	b
    //   0	123	1	paramString1	String
    //   0	123	2	paramString2	String
    //   8	114	3	localObject1	Object
    //   1	111	4	localObject2	Object
    //   4	28	5	localObject3	Object
    //   89	7	5	localThrowable	Throwable
    //   12	95	6	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   14	31	59	finally
    //   38	43	59	finally
    //   14	31	65	java/lang/Throwable
    //   38	43	65	java/lang/Throwable
    //   66	68	68	finally
    //   81	86	89	java/lang/Throwable
    //   9	14	116	java/lang/Exception
    //   52	57	116	java/lang/Exception
    //   81	86	116	java/lang/Exception
    //   94	100	116	java/lang/Exception
    //   106	111	116	java/lang/Exception
    //   114	116	116	java/lang/Exception
  }
  
  public void i(boolean paramBoolean)
  {
    this.c.k(paramBoolean);
  }
  
  public boolean i()
  {
    return this.c.a();
  }
  
  /* Error */
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> j(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	ua/com/tim_berners/parental_control/data/b:c	Lua/com/tim_berners/parental_control/data/a/a;
    //   4: invokevirtual 78	ua/com/tim_berners/parental_control/data/a/a:j	()Ljava/lang/String;
    //   7: astore 10
    //   9: new 129	com/google/gson/m
    //   12: dup
    //   13: invokespecial 130	com/google/gson/m:<init>	()V
    //   16: astore 11
    //   18: lconst_0
    //   19: lstore 6
    //   21: iconst_0
    //   22: istore_3
    //   23: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   26: astore 12
    //   28: aconst_null
    //   29: astore 8
    //   31: aconst_null
    //   32: astore 9
    //   34: aload 12
    //   36: invokevirtual 524	io/realm/dk:a	()V
    //   39: aload_0
    //   40: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   43: aload 12
    //   45: invokevirtual 3209	ua/com/tim_berners/parental_control/data/b/a:k	(Lio/realm/dk;)Lio/realm/dy;
    //   48: astore 13
    //   50: new 471	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   57: astore 14
    //   59: aload 14
    //   61: ldc_w 3211
    //   64: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 13
    //   70: ifnull +467 -> 537
    //   73: aload 13
    //   75: invokevirtual 527	io/realm/dy:size	()I
    //   78: istore_2
    //   79: goto +3 -> 82
    //   82: aload 14
    //   84: iload_2
    //   85: invokevirtual 727	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_0
    //   90: aload 12
    //   92: aload 14
    //   94: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   100: aload 13
    //   102: ifnull +21 -> 123
    //   105: aload 13
    //   107: invokevirtual 527	io/realm/dy:size	()I
    //   110: istore_2
    //   111: iload_2
    //   112: ifne +6 -> 118
    //   115: goto +8 -> 123
    //   118: iconst_0
    //   119: istore_2
    //   120: goto +5 -> 125
    //   123: iconst_1
    //   124: istore_2
    //   125: lload 6
    //   127: lstore 4
    //   129: iload_2
    //   130: ifne +225 -> 355
    //   133: aload 9
    //   135: astore 8
    //   137: iload_2
    //   138: istore_3
    //   139: new 471	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 472	java/lang/StringBuilder:<init>	()V
    //   146: astore 14
    //   148: aload 9
    //   150: astore 8
    //   152: iload_2
    //   153: istore_3
    //   154: aload 13
    //   156: invokevirtual 359	io/realm/dy:iterator	()Ljava/util/Iterator;
    //   159: astore 15
    //   161: aload 9
    //   163: astore 8
    //   165: iload_2
    //   166: istore_3
    //   167: aload 15
    //   169: invokeinterface 364 1 0
    //   174: ifeq +101 -> 275
    //   177: aload 9
    //   179: astore 8
    //   181: iload_2
    //   182: istore_3
    //   183: aload 15
    //   185: invokeinterface 368 1 0
    //   190: checkcast 460	ua/com/tim_berners/parental_control/data/models/f/a
    //   193: astore 16
    //   195: aload 9
    //   197: astore 8
    //   199: iload_2
    //   200: istore_3
    //   201: aload 14
    //   203: aload 16
    //   205: invokevirtual 3212	ua/com/tim_berners/parental_control/data/models/f/a:c	()J
    //   208: invokevirtual 481	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   211: pop
    //   212: aload 9
    //   214: astore 8
    //   216: iload_2
    //   217: istore_3
    //   218: aload 14
    //   220: ldc_w 478
    //   223: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload 9
    //   229: astore 8
    //   231: iload_2
    //   232: istore_3
    //   233: aload 14
    //   235: aload 16
    //   237: invokevirtual 3213	ua/com/tim_berners/parental_control/data/models/f/a:d	()Ljava/lang/String;
    //   240: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: pop
    //   244: aload 9
    //   246: astore 8
    //   248: iload_2
    //   249: istore_3
    //   250: aload 14
    //   252: ldc_w 3215
    //   255: invokevirtual 476	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: pop
    //   259: goto -98 -> 161
    //   262: astore 15
    //   264: aload 9
    //   266: astore 8
    //   268: iload_2
    //   269: istore_3
    //   270: aload 15
    //   272: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   275: aload 9
    //   277: astore 8
    //   279: iload_2
    //   280: istore_3
    //   281: aload 11
    //   283: ldc -106
    //   285: aload 14
    //   287: invokevirtual 484	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: invokevirtual 146	com/google/gson/m:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   293: aload 9
    //   295: astore 8
    //   297: iload_2
    //   298: istore_3
    //   299: aload_0
    //   300: aload 12
    //   302: ldc_w 3217
    //   305: invokevirtual 2666	ua/com/tim_berners/parental_control/data/b:b	(Lio/realm/dk;Ljava/lang/String;)V
    //   308: aload 9
    //   310: astore 8
    //   312: iload_2
    //   313: istore_3
    //   314: aload 13
    //   316: iconst_0
    //   317: invokevirtual 3218	io/realm/dy:get	(I)Ljava/lang/Object;
    //   320: checkcast 460	ua/com/tim_berners/parental_control/data/models/f/a
    //   323: astore 13
    //   325: lload 6
    //   327: lstore 4
    //   329: aload 13
    //   331: ifnull +24 -> 355
    //   334: aload 9
    //   336: astore 8
    //   338: iload_2
    //   339: istore_3
    //   340: aload 13
    //   342: invokevirtual 3212	ua/com/tim_berners/parental_control/data/models/f/a:c	()J
    //   345: lstore 4
    //   347: goto +8 -> 355
    //   350: astore 9
    //   352: goto +44 -> 396
    //   355: lload 4
    //   357: lstore 6
    //   359: iload_2
    //   360: istore_3
    //   361: aload 12
    //   363: ifnull +112 -> 475
    //   366: aload 12
    //   368: invokevirtual 517	io/realm/dk:close	()V
    //   371: lload 4
    //   373: lstore 6
    //   375: iload_2
    //   376: istore_3
    //   377: goto +98 -> 475
    //   380: astore 8
    //   382: goto +82 -> 464
    //   385: astore 9
    //   387: iload_3
    //   388: istore_2
    //   389: goto +20 -> 409
    //   392: astore 9
    //   394: iconst_0
    //   395: istore_2
    //   396: aload 9
    //   398: astore 8
    //   400: iload_2
    //   401: istore_3
    //   402: aload 9
    //   404: athrow
    //   405: astore 9
    //   407: iload_3
    //   408: istore_2
    //   409: aload 12
    //   411: ifnull +33 -> 444
    //   414: aload 8
    //   416: ifnull +23 -> 439
    //   419: aload 12
    //   421: invokevirtual 517	io/realm/dk:close	()V
    //   424: goto +20 -> 444
    //   427: astore 12
    //   429: aload 8
    //   431: aload 12
    //   433: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   436: goto +8 -> 444
    //   439: aload 12
    //   441: invokevirtual 517	io/realm/dk:close	()V
    //   444: aload 9
    //   446: athrow
    //   447: astore 8
    //   449: lload 6
    //   451: lstore 4
    //   453: goto +11 -> 464
    //   456: astore 8
    //   458: iconst_0
    //   459: istore_2
    //   460: lload 6
    //   462: lstore 4
    //   464: aload 8
    //   466: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   469: iload_2
    //   470: istore_3
    //   471: lload 4
    //   473: lstore 6
    //   475: iload_3
    //   476: ifeq +10 -> 486
    //   479: getstatic 3221	ua/com/tim_berners/parental_control/data/ci:a	Lio/b/t;
    //   482: invokestatic 1356	io/b/q:a	(Lio/b/t;)Lio/b/q;
    //   485: areturn
    //   486: aload_0
    //   487: getfield 38	ua/com/tim_berners/parental_control/data/b:d	Lua/com/tim_berners/parental_control/data/network/ParentalService;
    //   490: aload 10
    //   492: aload_1
    //   493: aload 11
    //   495: invokeinterface 3224 4 0
    //   500: new 3226	ua/com/tim_berners/parental_control/data/e
    //   503: dup
    //   504: aload_0
    //   505: invokespecial 3227	ua/com/tim_berners/parental_control/data/e:<init>	(Lua/com/tim_berners/parental_control/data/b;)V
    //   508: invokevirtual 911	io/b/q:b	(Lio/b/d/d;)Lio/b/q;
    //   511: new 3229	ua/com/tim_berners/parental_control/data/f
    //   514: dup
    //   515: aload_0
    //   516: lload 6
    //   518: invokespecial 3230	ua/com/tim_berners/parental_control/data/f:<init>	(Lua/com/tim_berners/parental_control/data/b;J)V
    //   521: invokevirtual 906	io/b/q:a	(Lio/b/d/d;)Lio/b/q;
    //   524: invokestatic 173	io/b/h/a:b	()Lio/b/p;
    //   527: invokevirtual 178	io/b/q:b	(Lio/b/p;)Lio/b/q;
    //   530: invokestatic 182	io/b/a/b/a:a	()Lio/b/p;
    //   533: invokevirtual 184	io/b/q:a	(Lio/b/p;)Lio/b/q;
    //   536: areturn
    //   537: iconst_0
    //   538: istore_2
    //   539: goto -457 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	542	0	this	b
    //   0	542	1	paramString	String
    //   78	461	2	m	int
    //   22	454	3	n	int
    //   127	345	4	l1	long
    //   19	498	6	l2	long
    //   29	308	8	localObject1	Object
    //   380	1	8	localException1	Exception
    //   398	32	8	localObject2	Object
    //   447	1	8	localException2	Exception
    //   456	9	8	localException3	Exception
    //   32	303	9	localObject3	Object
    //   350	1	9	localThrowable1	Throwable
    //   385	1	9	localObject4	Object
    //   392	11	9	localThrowable2	Throwable
    //   405	40	9	localObject5	Object
    //   7	484	10	str	String
    //   16	478	11	localM	com.google.gson.m
    //   26	394	12	localDk	dk
    //   427	13	12	localThrowable3	Throwable
    //   48	293	13	localObject6	Object
    //   57	229	14	localStringBuilder	StringBuilder
    //   159	25	15	localIterator	Iterator
    //   262	9	15	localException4	Exception
    //   193	43	16	localA	ua.com.tim_berners.parental_control.data.models.f.a
    // Exception table:
    //   from	to	target	type
    //   154	161	262	java/lang/Exception
    //   167	177	262	java/lang/Exception
    //   183	195	262	java/lang/Exception
    //   201	212	262	java/lang/Exception
    //   218	227	262	java/lang/Exception
    //   233	244	262	java/lang/Exception
    //   250	259	262	java/lang/Exception
    //   139	148	350	java/lang/Throwable
    //   154	161	350	java/lang/Throwable
    //   167	177	350	java/lang/Throwable
    //   183	195	350	java/lang/Throwable
    //   201	212	350	java/lang/Throwable
    //   218	227	350	java/lang/Throwable
    //   233	244	350	java/lang/Throwable
    //   250	259	350	java/lang/Throwable
    //   270	275	350	java/lang/Throwable
    //   281	293	350	java/lang/Throwable
    //   299	308	350	java/lang/Throwable
    //   314	325	350	java/lang/Throwable
    //   340	347	350	java/lang/Throwable
    //   366	371	380	java/lang/Exception
    //   34	68	385	finally
    //   73	79	385	finally
    //   82	100	385	finally
    //   105	111	385	finally
    //   34	68	392	java/lang/Throwable
    //   73	79	392	java/lang/Throwable
    //   82	100	392	java/lang/Throwable
    //   105	111	392	java/lang/Throwable
    //   139	148	405	finally
    //   154	161	405	finally
    //   167	177	405	finally
    //   183	195	405	finally
    //   201	212	405	finally
    //   218	227	405	finally
    //   233	244	405	finally
    //   250	259	405	finally
    //   270	275	405	finally
    //   281	293	405	finally
    //   299	308	405	finally
    //   314	325	405	finally
    //   340	347	405	finally
    //   402	405	405	finally
    //   419	424	427	java/lang/Throwable
    //   419	424	447	java/lang/Exception
    //   429	436	447	java/lang/Exception
    //   439	444	447	java/lang/Exception
    //   444	447	447	java/lang/Exception
    //   23	28	456	java/lang/Exception
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.m.i> j(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 6
    //   14: aconst_null
    //   15: astore 4
    //   17: aload 4
    //   19: astore_3
    //   20: aload 6
    //   22: invokevirtual 524	io/realm/dk:a	()V
    //   25: aload 4
    //   27: astore_3
    //   28: aload 5
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 6
    //   36: aload_1
    //   37: aload_2
    //   38: invokevirtual 3232	ua/com/tim_berners/parental_control/data/b/a:d	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;)Lio/realm/dy;
    //   41: invokestatic 3233	ua/com/tim_berners/parental_control/data/models/m/i:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   44: aload 6
    //   46: ifnull +58 -> 104
    //   49: aload 6
    //   51: invokevirtual 517	io/realm/dk:close	()V
    //   54: aload 5
    //   56: areturn
    //   57: astore_1
    //   58: goto +8 -> 66
    //   61: astore_1
    //   62: aload_1
    //   63: astore_3
    //   64: aload_1
    //   65: athrow
    //   66: aload 6
    //   68: ifnull +29 -> 97
    //   71: aload_3
    //   72: ifnull +20 -> 92
    //   75: aload 6
    //   77: invokevirtual 517	io/realm/dk:close	()V
    //   80: goto +17 -> 97
    //   83: astore_2
    //   84: aload_3
    //   85: aload_2
    //   86: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   89: goto +8 -> 97
    //   92: aload 6
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: aload_1
    //   98: athrow
    //   99: astore_1
    //   100: aload_1
    //   101: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   104: aload 5
    //   106: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	b
    //   0	107	1	paramString1	String
    //   0	107	2	paramString2	String
    //   19	66	3	localObject1	Object
    //   15	11	4	localObject2	Object
    //   7	98	5	localArrayList	ArrayList
    //   12	81	6	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   20	25	57	finally
    //   28	44	57	finally
    //   64	66	57	finally
    //   20	25	61	java/lang/Throwable
    //   28	44	61	java/lang/Throwable
    //   75	80	83	java/lang/Throwable
    //   9	14	99	java/lang/Exception
    //   49	54	99	java/lang/Exception
    //   75	80	99	java/lang/Exception
    //   84	89	99	java/lang/Exception
    //   92	97	99	java/lang/Exception
    //   97	99	99	java/lang/Exception
  }
  
  public void j(boolean paramBoolean)
  {
    this.c.l(paramBoolean);
  }
  
  public boolean j()
  {
    return this.c.s();
  }
  
  public long k()
  {
    return this.c.t();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<JSONObject>> k(String paramString)
  {
    String str = this.c.j();
    V("dt_mng | lgout_chd");
    return this.d.onChildLogout(str, paramString).a(new h(this, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a> k(@NonNull String paramString1, @NonNull String paramString2)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("id", paramString1);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    V("dt_mng | upl_web | rem");
    return this.d.removeWebUrl(str, paramString2, localM).a(new bv(this, paramString1)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void k(boolean paramBoolean)
  {
    this.c.p(paramBoolean);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("dt_mng | shw_tut | ");
    localStringBuilder.append(paramBoolean);
    V(localStringBuilder.toString());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<JSONObject>> l(String paramString)
  {
    String str = this.c.j();
    V("dt_mng | rem_chd");
    return this.d.onChildRemove(str, paramString).a(new i(this, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.m.e>> l(@NonNull String paramString1, @NonNull String paramString2)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("url", paramString1);
      localM.a("package", paramString2);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return this.d.checkUrl(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void l(boolean paramBoolean)
  {
    this.c.q(paramBoolean);
  }
  
  public boolean l()
  {
    return this.c.u();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.e.b>> m(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("contacts_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ad(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.getDeviceContacts(str, paramString, l).a(new q(this, (String)localObject, paramString)).a(io.b.a.b.a.a()).b(io.b.h.a.b());
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.l.g> m(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 6
    //   14: aconst_null
    //   15: astore 4
    //   17: aload 4
    //   19: astore_3
    //   20: aload 6
    //   22: invokevirtual 524	io/realm/dk:a	()V
    //   25: aload 4
    //   27: astore_3
    //   28: aload 5
    //   30: aload_0
    //   31: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   34: aload 6
    //   36: aload_1
    //   37: aload_2
    //   38: invokevirtual 3346	ua/com/tim_berners/parental_control/data/b/a:e	(Lio/realm/dk;Ljava/lang/String;Ljava/lang/String;)Lio/realm/dy;
    //   41: invokestatic 3349	ua/com/tim_berners/parental_control/data/models/l/g:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   44: aload 6
    //   46: ifnull +58 -> 104
    //   49: aload 6
    //   51: invokevirtual 517	io/realm/dk:close	()V
    //   54: aload 5
    //   56: areturn
    //   57: astore_1
    //   58: goto +8 -> 66
    //   61: astore_1
    //   62: aload_1
    //   63: astore_3
    //   64: aload_1
    //   65: athrow
    //   66: aload 6
    //   68: ifnull +29 -> 97
    //   71: aload_3
    //   72: ifnull +20 -> 92
    //   75: aload 6
    //   77: invokevirtual 517	io/realm/dk:close	()V
    //   80: goto +17 -> 97
    //   83: astore_2
    //   84: aload_3
    //   85: aload_2
    //   86: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   89: goto +8 -> 97
    //   92: aload 6
    //   94: invokevirtual 517	io/realm/dk:close	()V
    //   97: aload_1
    //   98: athrow
    //   99: astore_1
    //   100: aload_1
    //   101: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   104: aload 5
    //   106: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	b
    //   0	107	1	paramString1	String
    //   0	107	2	paramString2	String
    //   19	66	3	localObject1	Object
    //   15	11	4	localObject2	Object
    //   7	98	5	localArrayList	ArrayList
    //   12	81	6	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   20	25	57	finally
    //   28	44	57	finally
    //   64	66	57	finally
    //   20	25	61	java/lang/Throwable
    //   28	44	61	java/lang/Throwable
    //   75	80	83	java/lang/Throwable
    //   9	14	99	java/lang/Exception
    //   49	54	99	java/lang/Exception
    //   75	80	99	java/lang/Exception
    //   84	89	99	java/lang/Exception
    //   92	97	99	java/lang/Exception
    //   97	99	99	java/lang/Exception
  }
  
  public void m(boolean paramBoolean)
  {
    this.c.r(paramBoolean);
  }
  
  public boolean m()
  {
    return this.c.v();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.e.c>> n(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("contacts_statistics_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ak(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.getDeviceContactsStatistics(str, paramString, l).a(new r(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void n(boolean paramBoolean)
  {
    this.c.s(paramBoolean);
  }
  
  public boolean n()
  {
    return this.c.w();
  }
  
  public long o()
  {
    return this.c.x();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.c.h> o(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: new 781	java/text/SimpleDateFormat
    //   42: dup
    //   43: ldc_w 3375
    //   46: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   49: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   52: astore 8
    //   54: aload 8
    //   56: ldc_w 802
    //   59: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   62: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   65: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   68: astore 5
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_3
    //   73: astore_2
    //   74: aload 5
    //   76: invokevirtual 524	io/realm/dk:a	()V
    //   79: aload_3
    //   80: astore_2
    //   81: aload 4
    //   83: aload_0
    //   84: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   87: aload 5
    //   89: aload_1
    //   90: invokevirtual 3377	ua/com/tim_berners/parental_control/data/b/a:l	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   93: aload 8
    //   95: aload 6
    //   97: aload 7
    //   99: invokestatic 3379	ua/com/tim_berners/parental_control/data/models/c/h:b	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   102: aload 5
    //   104: ifnull +58 -> 162
    //   107: aload 5
    //   109: invokevirtual 517	io/realm/dk:close	()V
    //   112: aload 4
    //   114: areturn
    //   115: astore_1
    //   116: goto +8 -> 124
    //   119: astore_1
    //   120: aload_1
    //   121: astore_2
    //   122: aload_1
    //   123: athrow
    //   124: aload 5
    //   126: ifnull +29 -> 155
    //   129: aload_2
    //   130: ifnull +20 -> 150
    //   133: aload 5
    //   135: invokevirtual 517	io/realm/dk:close	()V
    //   138: goto +17 -> 155
    //   141: astore_3
    //   142: aload_2
    //   143: aload_3
    //   144: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   147: goto +8 -> 155
    //   150: aload 5
    //   152: invokevirtual 517	io/realm/dk:close	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_1
    //   158: aload_1
    //   159: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   162: aload 4
    //   164: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	b
    //   0	165	1	paramString	String
    //   73	70	2	localObject1	Object
    //   71	9	3	localObject2	Object
    //   141	3	3	localThrowable	Throwable
    //   7	156	4	localArrayList	ArrayList
    //   68	83	5	localDk	dk
    //   22	74	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	61	7	localSimpleDateFormat2	SimpleDateFormat
    //   52	42	8	localSimpleDateFormat3	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   74	79	115	finally
    //   81	102	115	finally
    //   122	124	115	finally
    //   74	79	119	java/lang/Throwable
    //   81	102	119	java/lang/Throwable
    //   133	138	141	java/lang/Throwable
    //   65	70	157	java/lang/Exception
    //   107	112	157	java/lang/Exception
    //   133	138	157	java/lang/Exception
    //   142	147	157	java/lang/Exception
    //   150	155	157	java/lang/Exception
    //   155	157	157	java/lang/Exception
  }
  
  public void o(boolean paramBoolean)
  {
    this.c.t(paramBoolean);
  }
  
  public long p()
  {
    return this.c.y();
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.c.h> p(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 781	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc_w 783
    //   16: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   19: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   22: astore 6
    //   24: new 781	java/text/SimpleDateFormat
    //   27: dup
    //   28: ldc_w 794
    //   31: invokestatic 789	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   34: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   37: astore 7
    //   39: new 781	java/text/SimpleDateFormat
    //   42: dup
    //   43: ldc_w 796
    //   46: getstatic 800	java/util/Locale:UK	Ljava/util/Locale;
    //   49: invokespecial 792	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   52: astore 8
    //   54: aload 8
    //   56: ldc_w 802
    //   59: invokestatic 808	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   62: invokevirtual 812	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   65: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   68: astore 5
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_3
    //   73: astore_2
    //   74: aload 5
    //   76: invokevirtual 524	io/realm/dk:a	()V
    //   79: aload_3
    //   80: astore_2
    //   81: aload 4
    //   83: aload_0
    //   84: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   87: aload 5
    //   89: aload_1
    //   90: invokevirtual 3387	ua/com/tim_berners/parental_control/data/b/a:m	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   93: aload 8
    //   95: aload 6
    //   97: aload 7
    //   99: invokestatic 2896	ua/com/tim_berners/parental_control/data/models/c/h:a	(Ljava/util/ArrayList;Lio/realm/dy;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;Ljava/text/SimpleDateFormat;)V
    //   102: aload 5
    //   104: ifnull +58 -> 162
    //   107: aload 5
    //   109: invokevirtual 517	io/realm/dk:close	()V
    //   112: aload 4
    //   114: areturn
    //   115: astore_1
    //   116: goto +8 -> 124
    //   119: astore_1
    //   120: aload_1
    //   121: astore_2
    //   122: aload_1
    //   123: athrow
    //   124: aload 5
    //   126: ifnull +29 -> 155
    //   129: aload_2
    //   130: ifnull +20 -> 150
    //   133: aload 5
    //   135: invokevirtual 517	io/realm/dk:close	()V
    //   138: goto +17 -> 155
    //   141: astore_3
    //   142: aload_2
    //   143: aload_3
    //   144: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   147: goto +8 -> 155
    //   150: aload 5
    //   152: invokevirtual 517	io/realm/dk:close	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_1
    //   158: aload_1
    //   159: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   162: aload 4
    //   164: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	b
    //   0	165	1	paramString	String
    //   73	70	2	localObject1	Object
    //   71	9	3	localObject2	Object
    //   141	3	3	localThrowable	Throwable
    //   7	156	4	localArrayList	ArrayList
    //   68	83	5	localDk	dk
    //   22	74	6	localSimpleDateFormat1	SimpleDateFormat
    //   37	61	7	localSimpleDateFormat2	SimpleDateFormat
    //   52	42	8	localSimpleDateFormat3	SimpleDateFormat
    // Exception table:
    //   from	to	target	type
    //   74	79	115	finally
    //   81	102	115	finally
    //   122	124	115	finally
    //   74	79	119	java/lang/Throwable
    //   81	102	119	java/lang/Throwable
    //   133	138	141	java/lang/Throwable
    //   65	70	157	java/lang/Exception
    //   107	112	157	java/lang/Exception
    //   133	138	157	java/lang/Exception
    //   142	147	157	java/lang/Exception
    //   150	155	157	java/lang/Exception
    //   155	157	157	java/lang/Exception
  }
  
  public void p(boolean paramBoolean)
  {
    this.c.u(paramBoolean);
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.m>> q(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps_usage_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!Z(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadAppsUsageList(str, paramString, l).a(new u(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void q(boolean paramBoolean)
  {
    this.c.v(paramBoolean);
  }
  
  public boolean q()
  {
    return this.c.E();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.d>> r(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps_logs_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!Y(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadAppsLogList(str, paramString, l).a(new y(this, (String)localObject, paramString)).b(new aa(this)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void r(boolean paramBoolean)
  {
    this.c.w(paramBoolean);
  }
  
  public boolean r()
  {
    return this.c.F();
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.c.e>> s(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!X(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadApplicationList(str, paramString, l).a(new af(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void s(boolean paramBoolean)
  {
    this.c.y(paramBoolean);
  }
  
  public boolean s()
  {
    return (Build.VERSION.SDK_INT <= 21) || (this.c.G());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.d.c>> t(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("calls_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!aa(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.getCalls(str, paramString, l).a(new aj(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void t(boolean paramBoolean)
  {
    this.c.z(paramBoolean);
  }
  
  public boolean t()
  {
    return (Build.VERSION.SDK_INT <= 21) || (this.c.H());
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.b.c>> u(String paramString)
  {
    String str = this.c.j();
    com.google.gson.m localM = new com.google.gson.m();
    try
    {
      localM.a("device_id", paramString);
      localM.a("type", "calls");
      localM.a("action", "clear_all");
      localM.a("data", new com.google.gson.g());
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return this.d.postAction2(str, localM).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void u(boolean paramBoolean)
  {
    this.c.A(paramBoolean);
  }
  
  public boolean u()
  {
    if (!ua.com.tim_berners.parental_control.d.x.c()) {
      return true;
    }
    boolean bool1 = this.c.I();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.e(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.j.c>> v(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("photos_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ac(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadPhotos(str, paramString, l).a(new am(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void v(boolean paramBoolean)
  {
    this.c.C(paramBoolean);
  }
  
  public boolean v()
  {
    if (!ua.com.tim_berners.parental_control.d.x.e()) {
      return true;
    }
    boolean bool1 = this.c.J();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.k(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.k.f>> w(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("sms_msgs_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ab(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.downloadSms(str, paramString, l).a(new as(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void w(boolean paramBoolean)
  {
    this.c.f(paramBoolean);
  }
  
  public boolean w()
  {
    if (!ua.com.tim_berners.parental_control.d.x.g()) {
      return true;
    }
    boolean bool1 = this.c.K();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.i(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  public io.b.q<ua.com.tim_berners.parental_control.data.models.common.a<ua.com.tim_berners.parental_control.data.models.k.b>> x(@NonNull String paramString)
  {
    String str = this.c.j();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("sms_chats_");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (!ab(paramString)) {
      a((String)localObject, 0L);
    }
    long l = ai((String)localObject);
    return this.d.getChats(str, paramString, l).a(new at(this, (String)localObject, paramString)).b(io.b.h.a.b()).a(io.b.a.b.a.a());
  }
  
  public void x(boolean paramBoolean)
  {
    this.c.g(paramBoolean);
  }
  
  public boolean x()
  {
    if (!ua.com.tim_berners.parental_control.d.x.h()) {
      return true;
    }
    boolean bool1 = this.c.L();
    if (!bool1) {
      try
      {
        boolean bool2 = ua.com.tim_berners.parental_control.service.a.c(this.b);
        return bool2 ^ true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return bool1;
  }
  
  /* Error */
  public ua.com.tim_berners.parental_control.data.models.location.h y(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload 4
    //   7: astore_2
    //   8: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   11: astore 5
    //   13: aload 5
    //   15: invokevirtual 524	io/realm/dk:a	()V
    //   18: aload_0
    //   19: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   22: aload 5
    //   24: aload_1
    //   25: invokevirtual 3503	ua/com/tim_berners/parental_control/data/b/a:j	(Lio/realm/dk;Ljava/lang/String;)Lua/com/tim_berners/parental_control/data/models/location/g;
    //   28: astore_2
    //   29: aload_3
    //   30: astore_1
    //   31: aload_2
    //   32: ifnull +8 -> 40
    //   35: aload_2
    //   36: invokestatic 3508	ua/com/tim_berners/parental_control/data/models/location/h:a	(Lua/com/tim_berners/parental_control/data/models/location/g;)Lua/com/tim_berners/parental_control/data/models/location/h;
    //   39: astore_1
    //   40: aload_1
    //   41: astore_2
    //   42: aload 5
    //   44: ifnull +74 -> 118
    //   47: aload_1
    //   48: astore_2
    //   49: aload 5
    //   51: invokevirtual 517	io/realm/dk:close	()V
    //   54: aload_1
    //   55: areturn
    //   56: astore_3
    //   57: aconst_null
    //   58: astore_1
    //   59: goto +7 -> 66
    //   62: astore_1
    //   63: aload_1
    //   64: athrow
    //   65: astore_3
    //   66: aload 5
    //   68: ifnull +40 -> 108
    //   71: aload_1
    //   72: ifnull +28 -> 100
    //   75: aload 4
    //   77: astore_2
    //   78: aload 5
    //   80: invokevirtual 517	io/realm/dk:close	()V
    //   83: goto +25 -> 108
    //   86: astore 5
    //   88: aload 4
    //   90: astore_2
    //   91: aload_1
    //   92: aload 5
    //   94: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   97: goto +11 -> 108
    //   100: aload 4
    //   102: astore_2
    //   103: aload 5
    //   105: invokevirtual 517	io/realm/dk:close	()V
    //   108: aload 4
    //   110: astore_2
    //   111: aload_3
    //   112: athrow
    //   113: astore_1
    //   114: aload_1
    //   115: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   118: aload_2
    //   119: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	b
    //   0	120	1	paramString	String
    //   7	112	2	localObject1	Object
    //   4	26	3	localObject2	Object
    //   56	1	3	localObject3	Object
    //   65	47	3	localObject4	Object
    //   1	108	4	localObject5	Object
    //   11	68	5	localDk	dk
    //   86	18	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   13	29	56	finally
    //   35	40	56	finally
    //   13	29	62	java/lang/Throwable
    //   35	40	62	java/lang/Throwable
    //   63	65	65	finally
    //   78	83	86	java/lang/Throwable
    //   8	13	113	java/lang/Exception
    //   49	54	113	java/lang/Exception
    //   78	83	113	java/lang/Exception
    //   91	97	113	java/lang/Exception
    //   103	108	113	java/lang/Exception
    //   111	113	113	java/lang/Exception
  }
  
  public void y(boolean paramBoolean)
  {
    this.c.h(paramBoolean);
  }
  
  public boolean y()
  {
    return ua.com.tim_berners.parental_control.service.a.n(this.b);
  }
  
  /* Error */
  public ArrayList<ua.com.tim_berners.parental_control.data.models.e.e> z(String paramString)
  {
    // Byte code:
    //   0: new 189	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 190	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 507	io/realm/dk:n	()Lio/realm/dk;
    //   12: astore 5
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload 5
    //   20: invokevirtual 524	io/realm/dk:a	()V
    //   23: aload_3
    //   24: astore_2
    //   25: aload 4
    //   27: aload_0
    //   28: getfield 40	ua/com/tim_berners/parental_control/data/b:a	Lua/com/tim_berners/parental_control/data/b/a;
    //   31: aload 5
    //   33: aload_1
    //   34: invokevirtual 3512	ua/com/tim_berners/parental_control/data/b/a:c	(Lio/realm/dk;Ljava/lang/String;)Lio/realm/dy;
    //   37: invokestatic 3513	ua/com/tim_berners/parental_control/data/models/e/e:a	(Ljava/util/ArrayList;Lio/realm/dy;)V
    //   40: aload 5
    //   42: ifnull +58 -> 100
    //   45: aload 5
    //   47: invokevirtual 517	io/realm/dk:close	()V
    //   50: aload 4
    //   52: areturn
    //   53: astore_1
    //   54: goto +8 -> 62
    //   57: astore_1
    //   58: aload_1
    //   59: astore_2
    //   60: aload_1
    //   61: athrow
    //   62: aload 5
    //   64: ifnull +29 -> 93
    //   67: aload_2
    //   68: ifnull +20 -> 88
    //   71: aload 5
    //   73: invokevirtual 517	io/realm/dk:close	()V
    //   76: goto +17 -> 93
    //   79: astore_3
    //   80: aload_2
    //   81: aload_3
    //   82: invokevirtual 520	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   85: goto +8 -> 93
    //   88: aload 5
    //   90: invokevirtual 517	io/realm/dk:close	()V
    //   93: aload_1
    //   94: athrow
    //   95: astore_1
    //   96: aload_1
    //   97: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   100: aload 4
    //   102: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	b
    //   0	103	1	paramString	String
    //   17	64	2	localObject1	Object
    //   15	9	3	localObject2	Object
    //   79	3	3	localThrowable	Throwable
    //   7	94	4	localArrayList	ArrayList
    //   12	77	5	localDk	dk
    // Exception table:
    //   from	to	target	type
    //   18	23	53	finally
    //   25	40	53	finally
    //   60	62	53	finally
    //   18	23	57	java/lang/Throwable
    //   25	40	57	java/lang/Throwable
    //   71	76	79	java/lang/Throwable
    //   9	14	95	java/lang/Exception
    //   45	50	95	java/lang/Exception
    //   71	76	95	java/lang/Exception
    //   80	85	95	java/lang/Exception
    //   88	93	95	java/lang/Exception
    //   93	95	95	java/lang/Exception
  }
  
  public void z(boolean paramBoolean)
  {
    this.c.i(paramBoolean);
  }
  
  public boolean z()
  {
    return ua.com.tim_berners.parental_control.service.a.o(this.b);
  }
}
