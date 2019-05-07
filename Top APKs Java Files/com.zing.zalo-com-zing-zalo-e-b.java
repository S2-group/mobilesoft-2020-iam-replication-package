package com.zing.zalo.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.zing.zalo.app.MainApplication;
import com.zing.zalo.b.r;
import com.zing.zalo.control.bk;
import com.zing.zalo.control.bq;
import com.zing.zalo.control.ca;
import com.zing.zalo.control.cg;
import com.zing.zalo.control.cp;
import com.zing.zalo.control.cw;
import com.zing.zalo.control.da;
import com.zing.zalo.control.dd;
import com.zing.zalo.control.de;
import com.zing.zalo.control.dh;
import com.zing.zalo.control.dn;
import com.zing.zalo.control.ed;
import com.zing.zalo.control.ei;
import com.zing.zalo.control.ek;
import com.zing.zalo.d.t;
import com.zing.zalo.db.cb;
import com.zing.zalo.h.n;
import com.zing.zalo.r.ab;
import com.zing.zalo.ui.MessagePopupActivity;
import com.zing.zalo.ui.MessagePopupSMSActivity;
import com.zing.zalo.ui.PasscodeActivity;
import com.zing.zalo.ui.RetryMsgPopupActivity;
import com.zing.zalo.ui.fragments.ChatFragment;
import com.zing.zalo.ui.fragments.mr;
import com.zing.zalo.utils.as;
import com.zing.zalo.utils.bl;
import com.zing.zalo.utils.bm;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"DefaultLocale"})
public final class b
{
  private static ThreadPoolExecutor Ih;
  public static boolean aLP = false;
  public static long aLQ = 0L;
  private static volatile b aLR;
  public final String TAG = getClass().getSimpleName();
  private final int aLS = 5000;
  private boolean aLT = false;
  
  public b()
  {
    if (Ih == null)
    {
      Ih = new ThreadPoolExecutor(3, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
      if (Build.VERSION.SDK_INT >= 9) {
        Ih.allowCoreThreadTimeOut(true);
      }
    }
  }
  
  /* Error */
  public static b AK()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 77	com/zing/zalo/e/b:aLR	Lcom/zing/zalo/e/b;
    //   6: ifnonnull +25 -> 31
    //   9: ldc 2
    //   11: monitorenter
    //   12: getstatic 77	com/zing/zalo/e/b:aLR	Lcom/zing/zalo/e/b;
    //   15: ifnonnull +13 -> 28
    //   18: new 2	com/zing/zalo/e/b
    //   21: dup
    //   22: invokespecial 78	com/zing/zalo/e/b:<init>	()V
    //   25: putstatic 77	com/zing/zalo/e/b:aLR	Lcom/zing/zalo/e/b;
    //   28: ldc 2
    //   30: monitorexit
    //   31: getstatic 77	com/zing/zalo/e/b:aLR	Lcom/zing/zalo/e/b;
    //   34: astore_0
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_0
    //   39: areturn
    //   40: astore_0
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    //   46: astore_0
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_0
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   34	5	0	localB	b
    //   40	5	0	localObject1	Object
    //   46	5	0	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   12	28	40	finally
    //   28	31	40	finally
    //   41	44	40	finally
    //   3	12	46	finally
    //   31	35	46	finally
    //   44	46	46	finally
  }
  
  private void J(long paramLong)
  {
    try
    {
      List localList = ek.GK().Dw().Cb();
      int i = 0;
      while (i < localList.size())
      {
        if (((com.zing.zalo.control.g)localList.get(i)).aOY.equals("" + paramLong))
        {
          ((com.zing.zalo.control.g)localList.get(i)).setState(7);
          com.zing.zalo.d.b.a.yY().b(1, new Object[0]);
          ab.ae((com.zing.zalo.control.g)localList.get(i));
        }
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
      com.zing.zalo.db.m.Hv().h(paramLong, 7);
    }
  }
  
  private void Z(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        paramString1 = ek.GK().eE("" + paramString1);
        if (paramString1 == null) {
          continue;
        }
        localObject = paramString1.Cb();
        if ((localObject == null) || (((List)localObject).size() <= 0)) {
          continue;
        }
        i = ((List)localObject).size();
        i -= 1;
        if (i < 0) {
          continue;
        }
      }
      catch (Exception paramString1)
      {
        Object localObject;
        int i;
        com.zing.a.f.e.b(this.TAG, paramString1);
        paramString1 = null;
        continue;
      }
      try
      {
        if ((((List)localObject).get(i) != null) && (paramString2.equals(((com.zing.zalo.control.g)((List)localObject).get(i)).aOZ)))
        {
          paramString1 = (com.zing.zalo.control.g)((List)localObject).get(i);
          localObject = paramString1;
          if (paramString1 != null) {}
        }
      }
      catch (Exception paramString1)
      {
        com.zing.a.f.e.b(this.TAG, paramString1);
        i -= 1;
      }
      try
      {
        localObject = com.zing.zalo.db.m.Hv().eG(paramString2);
        if (localObject != null)
        {
          com.zing.zalo.c.aa.xi().d(((com.zing.zalo.control.g)localObject).aOW, (com.zing.zalo.control.g)localObject);
          bl.i(new q(this, (com.zing.zalo.control.g)localObject));
        }
      }
      catch (Exception paramString1)
      {
        com.zing.a.f.e.b(this.TAG, paramString1);
        continue;
      }
      dK(paramString2);
      return;
    }
  }
  
  private void a(int paramInt1, JSONObject paramJSONObject, int paramInt2, boolean paramBoolean)
  {
    try
    {
      com.zing.zalo.r.c.b(new w(this, paramInt1, paramJSONObject, paramInt2, paramBoolean));
      return;
    }
    finally
    {
      paramJSONObject = finally;
      throw paramJSONObject;
    }
  }
  
  private void a(int paramInt1, JSONObject paramJSONObject, int paramInt2, boolean paramBoolean, ArrayList<com.zing.a.d.a.a> paramArrayList)
  {
    ArrayList localArrayList1;
    String str1;
    for (;;)
    {
      try
      {
        l1 = System.currentTimeMillis();
        if (paramJSONObject.isNull("msg")) {
          return;
        }
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
      }
      catch (Exception paramJSONObject)
      {
        long l1;
        ArrayList localArrayList2;
        int j;
        int i;
        long l2;
        com.zing.a.f.e.b(this.TAG, paramJSONObject);
        return;
        b(new com.zing.zalo.control.g(localException2, 0, paramInt2), paramBoolean);
        continue;
      }
      catch (OutOfMemoryError paramJSONObject)
      {
        com.zing.a.f.e.b(this.TAG, paramJSONObject);
        return;
      }
      try
      {
        paramJSONObject = paramJSONObject.getJSONArray("msg");
        j = paramJSONObject.length();
        i = 0;
        if (i < j)
        {
          try
          {
            localObject = paramJSONObject.getJSONObject(i).getJSONObject("text");
            str1 = ((JSONObject)localObject).getString("type");
            localObject = ((JSONObject)localObject).getJSONObject("data");
            if (((JSONObject)localObject).has("id")) {
              localArrayList1.add(Long.valueOf(((JSONObject)localObject).getLong("id")));
            }
            if (((JSONObject)localObject).has("fromU"))
            {
              String str2 = ((JSONObject)localObject).getString("fromU");
              l2 = ((JSONObject)localObject).getLong("ts");
              com.zing.zalo.h.a.Px().f(str2, l2);
            }
            if (!str1.equals("webchat")) {
              break label452;
            }
            if ((!((JSONObject)localObject).has("mcrypt")) || (!((JSONObject)localObject).getString("mcrypt").equals("1"))) {
              continue;
            }
            l2 = ((JSONObject)localObject).getLong("id");
          }
          catch (Exception localException2)
          {
            Object localObject;
            com.zing.a.f.e.b(this.TAG, localException2);
            continue;
          }
          try
          {
            str1 = com.zing.zalo.f.c.eu(MainApplication.uw());
            str1 = com.zing.a.d.a.f.c(com.zing.a.a.c.oD(((JSONObject)localObject).getString("iv")), str1, ((JSONObject)localObject).getString("msg"));
            localObject = new com.zing.zalo.control.g((JSONObject)localObject, 0, paramInt2);
            ((com.zing.zalo.control.g)localObject).setMessage(str1);
            b((com.zing.zalo.control.g)localObject, paramBoolean);
            i += 1;
          }
          catch (Exception localException1)
          {
            wV();
            localArrayList1.remove(Long.valueOf(l2));
            localArrayList2.add(Long.valueOf(l2));
            com.zing.a.f.e.b(this.TAG, localException1);
            continue;
          }
        }
        else
        {
          if (paramInt1 != 101) {
            break label1129;
          }
        }
      }
      catch (Exception paramJSONObject)
      {
        l2 = System.currentTimeMillis();
        com.zing.a.e.g.a(com.zing.a.a.dzm, 15001, paramJSONObject.toString(), l2 - l1, 15000, com.zing.a.a.versionCode);
        paramJSONObject.printStackTrace();
      }
    }
    if (paramBoolean) {
      com.zing.zalo.e.a.a.Bc().c(localArrayList1, false);
    }
    for (;;)
    {
      if ((localArrayList2 == null) || (localArrayList2.size() <= 0)) {
        return;
      }
      com.zing.zalo.e.a.a.Bc().o(localArrayList2);
      return;
      label452:
      if (str1.equals("chat.sticker"))
      {
        b(new com.zing.zalo.control.g(localException2, 11, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.voice"))
      {
        b(new com.zing.zalo.control.g(localException2, 4, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.photo"))
      {
        b(new com.zing.zalo.control.g(localException2, 3, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.photo.album"))
      {
        b(new com.zing.zalo.control.g(localException2, 14, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.video"))
      {
        b(new com.zing.zalo.control.g(localException2, 9, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.link"))
      {
        b(new com.zing.zalo.control.g(localException2, 15, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.mp3"))
      {
        b(new com.zing.zalo.control.g(localException2, 5, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.doodle"))
      {
        b(new com.zing.zalo.control.g(localException2, 2, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.recommended"))
      {
        b(new com.zing.zalo.control.g(localException2, 13, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.list.action"))
      {
        b(new com.zing.zalo.control.g(localException2, 17, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.location.new"))
      {
        b(new com.zing.zalo.control.g(localException2, 19, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("chat.inform"))
      {
        b(new com.zing.zalo.control.g(localException2, 12, paramInt2), paramBoolean);
        break;
      }
      if (str1.equals("talk.songpop.send"))
      {
        l(localException2);
        break;
      }
      if (str1.equals("webchat.draw"))
      {
        a(localException2, paramInt2, paramBoolean);
        break;
      }
      if ((str1.equals("group.join")) || (str1.equals("group.invite")) || (str1.equals("group.delete")) || (str1.equals("group.leave")) || (str1.equals("group.force.leave")) || (str1.equals("group.update")) || (str1.equals("group.reject")) || (str1.equals("group.invited.cancel")))
      {
        b(localException2, str1);
        break;
      }
      if ((str1.equals("room.join")) || (str1.equals("room.leave")) || (str1.equals("room.force.leave")))
      {
        c(localException2, str1);
        break;
      }
      if (str1.equals("chat.video.msg"))
      {
        b(new com.zing.zalo.control.g(localException2, 21, paramInt2), paramBoolean);
        break;
      }
      if (!str1.equals("chat.undo")) {
        break;
      }
      com.zing.zalo.control.g localG = new com.zing.zalo.control.g(localException2, 50, paramInt2);
      if ((localG.aPv == null) || (TextUtils.isEmpty(localG.aPv.Ew()))) {
        break;
      }
      Z(localG.aOW, localG.aPv.Ew());
      break;
      com.zing.zalo.e.a.a.Bc().c(localArrayList1, true);
      continue;
      label1129:
      if (paramInt1 == 121)
      {
        com.zing.zalo.e.a.a.Bc().c(localArrayList1, false);
        if ((localArrayList1 != null) && (localArrayList1.size() > 48)) {
          com.zing.zalo.e.a.a.Bc().Bg();
        }
      }
      else if (paramInt1 == 201)
      {
        if (paramBoolean) {
          com.zing.zalo.e.a.a.Bc().d(localArrayList1, false);
        } else {
          com.zing.zalo.e.a.a.Bc().d(localArrayList1, true);
        }
      }
      else if (paramInt1 == 224)
      {
        com.zing.zalo.e.a.a.Bc().d(localArrayList1, false);
        if ((localArrayList1 != null) && (localArrayList1.size() > 48)) {
          com.zing.zalo.e.a.a.Bc().Bh();
        }
      }
      else if (paramInt1 == 64536)
      {
        if (localArrayList1.size() > 0) {
          com.zing.zalo.f.a.GP();
        }
        a(paramArrayList, localArrayList1);
      }
    }
  }
  
  private void a(bk paramBk, long paramLong)
  {
    if (paramBk != null) {
      try
      {
        if ((!TextUtils.isEmpty(paramBk.aOC)) && (!paramBk.EF()))
        {
          if ((com.zing.zalo.f.a.aXf.containsKey(paramBk.aOC)) && (com.zing.zalo.f.a.aXf.get(paramBk.aOC) != null) && (((dh)com.zing.zalo.f.a.aXf.get(paramBk.aOC)).getStatus() == 3)) {
            return;
          }
          dh localDh = new dh();
          localDh.ex(paramBk.aOC);
          localDh.fN(3);
          localDh.X(paramLong);
          com.zing.zalo.db.m.Hv().b(localDh);
          return;
        }
      }
      catch (Exception paramBk)
      {
        paramBk.printStackTrace();
      }
    }
  }
  
  private void a(bk paramBk, com.zing.zalo.control.g paramG)
  {
    Handler localHandler;
    do
    {
      try
      {
        com.zing.a.f.e.d(this.TAG, "afterProcessSmsMessage");
        int i = com.zing.zalo.f.c.cZ(MainApplication.uw());
        if (i >= 3) {
          try
          {
            com.zing.zalo.r.c.b(new d(this, paramG));
            return;
          }
          catch (Exception paramBk)
          {
            com.zing.a.f.e.b(this.TAG, paramBk);
            return;
          }
        }
        com.zing.zalo.d.ag.yj().yw();
      }
      catch (Exception paramBk)
      {
        com.zing.a.f.e.b(this.TAG, paramBk);
        return;
      }
      if (!com.zing.zalo.d.ag.yj().isPlaying()) {
        com.zing.zalo.d.ag.yj().yz();
      }
      if (RetryMsgPopupActivity.afy() != null)
      {
        RetryMsgPopupActivity.dU(false);
        RetryMsgPopupActivity.afy().finish();
      }
      if (MessagePopupActivity.adO() != null)
      {
        MessagePopupActivity.dI(false);
        MessagePopupActivity.adO().finish();
      }
      localHandler = new Handler(Looper.getMainLooper());
      paramBk = new bq(paramBk.aOC, paramBk.g(true, false), paramG.getMessage(), paramG.getType(), paramBk.aRZ, paramG.timestamp, paramG.aOZ);
    } while (MainApplication.uw() == null);
    paramG = new Intent(MainApplication.uw(), MessagePopupSMSActivity.class);
    paramG.putExtra("EXTRA_MESSAGE", paramBk);
    paramG.addFlags(67108864);
    paramG.addFlags(268435456);
    localHandler.postDelayed(new e(this, paramG), 2000L);
  }
  
  private void a(bk paramBk, com.zing.zalo.control.g paramG, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramG = new r();
    paramG.a(new g(this, paramBk));
    paramG.cQ(paramBk.aOC);
  }
  
  private void a(String paramString, long paramLong, cw paramCw)
  {
    try
    {
      paramString = ek.GK().eE(paramString).Cb();
      int i = 0;
      while (i < paramString.size())
      {
        if (((com.zing.zalo.control.g)paramString.get(i)).aOY.equals("" + paramLong))
        {
          ((com.zing.zalo.control.g)paramString.get(i)).a(paramCw);
          com.zing.zalo.d.b.a.yY().b(1, new Object[0]);
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void a(String paramString1, String paramString2, String paramString3, long paramLong, bk paramBk)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString3);
      r localR = new r();
      localR.a(new x(this, paramString3, paramString1, paramString2, paramLong, paramBk));
      localR.l(localArrayList);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    finally {}
  }
  
  private void a(ArrayList<dh> paramArrayList, int paramInt, boolean paramBoolean)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        int j = paramArrayList.size();
        if (i < j) {
          try
          {
            dh localDh = (dh)paramArrayList.get(i);
            localDh.fN(paramInt);
            if ((com.zing.zalo.f.a.aXf.containsKey(localDh.FW())) && (com.zing.zalo.f.a.aXf.get(localDh.FW()) != null) && (((dh)com.zing.zalo.f.a.aXf.get(localDh.FW())).getStatus() == 3) && ((paramInt == 0) || (paramInt == 2))) {
              break label208;
            }
            if (!paramBoolean) {
              break label133;
            }
            com.zing.zalo.db.m.Hv().b(localDh);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        return;
      }
      catch (Exception paramArrayList)
      {
        paramArrayList.printStackTrace();
      }
      label133:
      if ((com.zing.zalo.f.a.aXf.containsKey(localException.FW())) && (com.zing.zalo.f.a.aXf.get(localException.FW()) != null)) {
        ((dh)com.zing.zalo.f.a.aXf.get(localException.FW())).fN(localException.getStatus());
      } else {
        com.zing.zalo.f.a.aXf.put(localException.FW(), localException);
      }
      label208:
      i += 1;
    }
  }
  
  private void a(JSONObject paramJSONObject, int paramInt, boolean paramBoolean)
  {
    String str2;
    try
    {
      localObject = new JSONObject(paramJSONObject.getString("msg"));
      str1 = ((JSONObject)localObject).getString("type");
      if (str1.equals("draw"))
      {
        b(new com.zing.zalo.control.g(paramJSONObject, 6, paramInt), paramBoolean);
        return;
      }
      if (!str1.equals("answerpassed")) {
        return;
      }
      str1 = paramJSONObject.getString("fromU");
      str2 = n.aY(str1, paramJSONObject.getString("fromD"));
      l = ((JSONObject)localObject).getLong("id");
      localCw = com.zing.zalo.db.m.Hv().ac(l);
      if (localCw == null) {
        return;
      }
      localCw.setState(12);
      com.zing.zalo.db.m.Hv().a(l, localCw);
      paramJSONObject = "";
    }
    catch (Exception paramJSONObject)
    {
      Object localObject;
      String str1;
      long l;
      cw localCw;
      label151:
      String str3;
      com.zing.a.f.e.b(this.TAG, paramJSONObject);
      return;
    }
    try
    {
      localObject = com.zing.zalo.db.m.Hv().eJ(str1);
      paramJSONObject = (JSONObject)localObject;
    }
    catch (Exception localException)
    {
      break label151;
    }
    str3 = String.format(MainApplication.uw().getString(2131166675), new Object[] { str2, localCw.getKey().toUpperCase(), paramJSONObject });
    com.zing.zalo.f.a.aYI = str3;
    com.zing.zalo.f.a.aYK = String.format(MainApplication.uw().getString(2131167334), new Object[] { str2 });
    com.zing.zalo.f.a.aYL = localCw.getKey().toUpperCase();
    a(str1, l, localCw);
    paramJSONObject = new bk(str1);
    if (!str1.equals(""))
    {
      localObject = com.zing.zalo.d.v.yc().du(str1);
      if (localObject != null)
      {
        ((bk)localObject).g(true, false);
        paramJSONObject = (JSONObject)localObject;
      }
      for (;;)
      {
        com.zing.zalo.f.a.aYJ = paramJSONObject;
        com.zing.zalo.d.b.a.yY().b(1, new Object[0]);
        com.zing.zalo.d.o.xR().a(paramJSONObject, str3, paramJSONObject.aRZ);
        return;
        paramJSONObject.aRW = str2;
        paramJSONObject.aRZ = "";
      }
    }
  }
  
  /* Error */
  private void b(com.zing.a.d.a.e paramE)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 9
    //   3: lconst_0
    //   4: lstore_3
    //   5: lconst_0
    //   6: lstore 7
    //   8: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   11: getfield 773	com/zing/zalo/e/a/a:dAR	Ljava/util/Map;
    //   14: invokeinterface 774 1 0
    //   19: ifle +217 -> 236
    //   22: ldc 117
    //   24: astore 14
    //   26: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   29: aload_1
    //   30: invokevirtual 779	com/zing/a/d/a/e:awn	()I
    //   33: invokevirtual 783	com/zing/zalo/e/a/a:eY	(I)Lcom/zing/a/d/a/g;
    //   36: astore 13
    //   38: lload 9
    //   40: lstore 5
    //   42: aload 13
    //   44: ifnull +27 -> 71
    //   47: lload 9
    //   49: lstore_3
    //   50: aload 13
    //   52: invokevirtual 788	com/zing/a/d/a/g:wn	()J
    //   55: lstore 5
    //   57: lload 5
    //   59: lstore_3
    //   60: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   63: lstore 7
    //   65: lload 7
    //   67: lload 5
    //   69: lsub
    //   70: lstore_3
    //   71: aload 14
    //   73: astore 12
    //   75: aload_1
    //   76: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   79: ifne +445 -> 524
    //   82: aload 13
    //   84: ifnull +607 -> 691
    //   87: aload 14
    //   89: astore 12
    //   91: new 235	org/json/JSONObject
    //   94: dup
    //   95: invokespecial 792	org/json/JSONObject:<init>	()V
    //   98: astore 15
    //   100: aload 14
    //   102: astore 12
    //   104: aload_1
    //   105: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   108: astore 14
    //   110: aload 14
    //   112: astore 12
    //   114: aload 14
    //   116: ldc 117
    //   118: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: istore 11
    //   123: iload 11
    //   125: ifne +573 -> 698
    //   128: new 235	org/json/JSONObject
    //   131: dup
    //   132: aload 14
    //   134: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   137: astore 12
    //   139: aload 12
    //   141: astore 15
    //   143: aload 14
    //   145: astore 12
    //   147: aload 15
    //   149: ldc_w 797
    //   152: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   155: ifeq +340 -> 495
    //   158: aload 14
    //   160: astore 12
    //   162: aload 15
    //   164: ldc_w 797
    //   167: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   170: istore_2
    //   171: iload_2
    //   172: ifne +243 -> 415
    //   175: aload 13
    //   177: invokevirtual 805	com/zing/a/d/a/g:awy	()Lcom/zing/a/d/c;
    //   180: aload 15
    //   182: invokeinterface 809 2 0
    //   187: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   190: invokevirtual 813	com/zing/zalo/e/a/a:awD	()Lcom/zing/a/d/a/c;
    //   193: invokevirtual 818	com/zing/a/d/a/c:atg	()Ljava/lang/String;
    //   196: astore 12
    //   198: iconst_1
    //   199: iconst_1
    //   200: aload_1
    //   201: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   204: aload_1
    //   205: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   208: aload_1
    //   209: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   212: lload_3
    //   213: aload 12
    //   215: lload 5
    //   217: lload 7
    //   219: invokestatic 829	com/zing/a/e/g:a	(ZZIIIJLjava/lang/String;JJ)V
    //   222: aload 14
    //   224: astore 12
    //   226: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   229: aload_1
    //   230: invokevirtual 779	com/zing/a/d/a/e:awn	()I
    //   233: invokevirtual 832	com/zing/zalo/e/a/a:eZ	(I)V
    //   236: return
    //   237: astore 12
    //   239: aload_0
    //   240: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   243: aload 12
    //   245: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   248: pop
    //   249: lconst_0
    //   250: lstore 7
    //   252: lload_3
    //   253: lstore 5
    //   255: lconst_0
    //   256: lstore_3
    //   257: goto -186 -> 71
    //   260: astore 16
    //   262: aload 14
    //   264: astore 12
    //   266: aload_0
    //   267: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   270: aload 16
    //   272: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   275: pop
    //   276: goto +422 -> 698
    //   279: astore 15
    //   281: aload 14
    //   283: astore 12
    //   285: aload 15
    //   287: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   290: goto -103 -> 187
    //   293: astore 14
    //   295: aload_0
    //   296: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   299: aload 14
    //   301: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   304: pop
    //   305: aload 13
    //   307: ifnull +93 -> 400
    //   310: aload 13
    //   312: invokevirtual 805	com/zing/a/d/a/g:awy	()Lcom/zing/a/d/c;
    //   315: new 834	com/zing/a/b/c
    //   318: dup
    //   319: sipush 502
    //   322: sipush 502
    //   325: aload 12
    //   327: invokestatic 839	com/zing/zalo/utils/bm:f	(ILjava/lang/String;)Ljava/lang/String;
    //   330: invokespecial 842	com/zing/a/b/c:<init>	(ILjava/lang/String;)V
    //   333: invokeinterface 845 2 0
    //   338: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   341: invokevirtual 813	com/zing/zalo/e/a/a:awD	()Lcom/zing/a/d/a/c;
    //   344: astore 13
    //   346: iconst_1
    //   347: iconst_0
    //   348: aload_1
    //   349: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   352: aload_1
    //   353: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   356: aload_1
    //   357: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   360: lload_3
    //   361: ldc 117
    //   363: lload 5
    //   365: lload 7
    //   367: invokestatic 829	com/zing/a/e/g:a	(ZZIIIJLjava/lang/String;JJ)V
    //   370: aload 14
    //   372: ifnull +260 -> 632
    //   375: aload 14
    //   377: invokevirtual 353	java/lang/Exception:toString	()Ljava/lang/String;
    //   380: astore 12
    //   382: aload 13
    //   384: sipush 9008
    //   387: aload 12
    //   389: aload_1
    //   390: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   393: aload_1
    //   394: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   397: invokestatic 848	com/zing/a/e/g:a	(Lcom/zing/a/d/a/c;ILjava/lang/String;II)V
    //   400: aload_1
    //   401: ifnull -165 -> 236
    //   404: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   407: aload_1
    //   408: invokevirtual 779	com/zing/a/d/a/e:awn	()I
    //   411: invokevirtual 832	com/zing/zalo/e/a/a:eZ	(I)V
    //   414: return
    //   415: aload 15
    //   417: ldc_w 268
    //   420: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   423: ifeq +65 -> 488
    //   426: aload 15
    //   428: ldc_w 268
    //   431: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   434: astore 12
    //   436: new 834	com/zing/a/b/c
    //   439: dup
    //   440: iload_2
    //   441: iload_2
    //   442: ldc 117
    //   444: invokestatic 839	com/zing/zalo/utils/bm:f	(ILjava/lang/String;)Ljava/lang/String;
    //   447: invokespecial 842	com/zing/a/b/c:<init>	(ILjava/lang/String;)V
    //   450: astore 15
    //   452: aload 15
    //   454: aload 12
    //   456: invokevirtual 851	com/zing/a/b/c:setData	(Ljava/lang/String;)V
    //   459: aload 13
    //   461: invokevirtual 805	com/zing/a/d/a/g:awy	()Lcom/zing/a/d/c;
    //   464: aload 15
    //   466: invokeinterface 845 2 0
    //   471: goto -284 -> 187
    //   474: astore 15
    //   476: aload 14
    //   478: astore 12
    //   480: aload 15
    //   482: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   485: goto -298 -> 187
    //   488: ldc 117
    //   490: astore 12
    //   492: goto -56 -> 436
    //   495: aload 13
    //   497: invokevirtual 805	com/zing/a/d/a/g:awy	()Lcom/zing/a/d/c;
    //   500: aload 15
    //   502: invokeinterface 809 2 0
    //   507: goto -320 -> 187
    //   510: astore 15
    //   512: aload 14
    //   514: astore 12
    //   516: aload 15
    //   518: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   521: goto -334 -> 187
    //   524: aload 13
    //   526: ifnull +165 -> 691
    //   529: aload 13
    //   531: invokevirtual 805	com/zing/a/d/a/g:awy	()Lcom/zing/a/d/c;
    //   534: new 834	com/zing/a/b/c
    //   537: dup
    //   538: sipush 502
    //   541: sipush 502
    //   544: ldc 117
    //   546: invokestatic 839	com/zing/zalo/utils/bm:f	(ILjava/lang/String;)Ljava/lang/String;
    //   549: invokespecial 842	com/zing/a/b/c:<init>	(ILjava/lang/String;)V
    //   552: invokeinterface 845 2 0
    //   557: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   560: invokevirtual 813	com/zing/zalo/e/a/a:awD	()Lcom/zing/a/d/a/c;
    //   563: invokevirtual 818	com/zing/a/d/a/c:atg	()Ljava/lang/String;
    //   566: astore 12
    //   568: iconst_1
    //   569: iconst_0
    //   570: aload_1
    //   571: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   574: aload_1
    //   575: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   578: aload_1
    //   579: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   582: lload_3
    //   583: aload 12
    //   585: lload 5
    //   587: lload 7
    //   589: invokestatic 829	com/zing/a/e/g:a	(ZZIIIJLjava/lang/String;JJ)V
    //   592: ldc 117
    //   594: astore 12
    //   596: goto -370 -> 226
    //   599: astore 15
    //   601: aload 14
    //   603: astore 12
    //   605: aload 15
    //   607: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   610: goto -53 -> 557
    //   613: astore 12
    //   615: ldc 117
    //   617: astore 12
    //   619: goto -393 -> 226
    //   622: astore 12
    //   624: aload 12
    //   626: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   629: goto -291 -> 338
    //   632: ldc 117
    //   634: astore 12
    //   636: goto -254 -> 382
    //   639: astore 12
    //   641: goto -241 -> 400
    //   644: astore 14
    //   646: lconst_0
    //   647: lstore 7
    //   649: lconst_0
    //   650: lstore 5
    //   652: aconst_null
    //   653: astore 13
    //   655: ldc 117
    //   657: astore 12
    //   659: lconst_0
    //   660: lstore_3
    //   661: goto -366 -> 295
    //   664: astore 14
    //   666: lconst_0
    //   667: lstore 7
    //   669: lload_3
    //   670: lstore 5
    //   672: lconst_0
    //   673: lstore_3
    //   674: ldc 117
    //   676: astore 12
    //   678: goto -383 -> 295
    //   681: astore 14
    //   683: goto -388 -> 295
    //   686: astore 12
    //   688: goto -466 -> 222
    //   691: ldc 117
    //   693: astore 12
    //   695: goto -469 -> 226
    //   698: goto -555 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	701	0	this	b
    //   0	701	1	paramE	com.zing.a.d.a.e
    //   170	272	2	i	int
    //   4	670	3	l1	long
    //   40	631	5	l2	long
    //   6	662	7	l3	long
    //   1	47	9	l4	long
    //   121	3	11	bool	boolean
    //   73	152	12	localObject1	Object
    //   237	7	12	localException1	Exception
    //   264	340	12	localObject2	Object
    //   613	1	12	localException2	Exception
    //   617	1	12	str1	String
    //   622	3	12	localException3	Exception
    //   634	1	12	str2	String
    //   639	1	12	localException4	Exception
    //   657	20	12	str3	String
    //   686	1	12	localException5	Exception
    //   693	1	12	str4	String
    //   36	618	13	localObject3	Object
    //   24	258	14	str5	String
    //   293	309	14	localException6	Exception
    //   644	1	14	localException7	Exception
    //   664	1	14	localException8	Exception
    //   681	1	14	localException9	Exception
    //   98	83	15	localObject4	Object
    //   279	148	15	localException10	Exception
    //   450	15	15	localC	com.zing.a.b.c
    //   474	27	15	localException11	Exception
    //   510	7	15	localException12	Exception
    //   599	7	15	localException13	Exception
    //   260	11	16	localException14	Exception
    // Exception table:
    //   from	to	target	type
    //   50	57	237	java/lang/Exception
    //   60	65	237	java/lang/Exception
    //   128	139	260	java/lang/Exception
    //   175	187	279	java/lang/Exception
    //   75	82	293	java/lang/Exception
    //   91	100	293	java/lang/Exception
    //   104	110	293	java/lang/Exception
    //   114	123	293	java/lang/Exception
    //   147	158	293	java/lang/Exception
    //   162	171	293	java/lang/Exception
    //   266	276	293	java/lang/Exception
    //   285	290	293	java/lang/Exception
    //   480	485	293	java/lang/Exception
    //   516	521	293	java/lang/Exception
    //   605	610	293	java/lang/Exception
    //   415	436	474	java/lang/Exception
    //   436	471	474	java/lang/Exception
    //   495	507	510	java/lang/Exception
    //   529	557	599	java/lang/Exception
    //   557	592	613	java/lang/Exception
    //   310	338	622	java/lang/Exception
    //   338	370	639	java/lang/Exception
    //   375	382	639	java/lang/Exception
    //   382	400	639	java/lang/Exception
    //   26	38	644	java/lang/Exception
    //   239	249	664	java/lang/Exception
    //   226	236	681	java/lang/Exception
    //   187	222	686	java/lang/Exception
  }
  
  private void b(bk paramBk, com.zing.zalo.control.g paramG)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    int i;
    Object localObject1;
    label200:
    label237:
    Object localObject3;
    Object localObject2;
    label281:
    Object localObject4;
    try
    {
      boolean bool3 = com.zing.zalo.f.c.cN(MainApplication.uw());
      if ((!com.zing.zalo.f.a.aXX) || (mr.akr() == null) || (mr.akr().getCurrentTab() != 0)) {
        break label809;
      }
      i = 1;
      if ((!bool3) || (i != 0)) {
        break label808;
      }
      if (com.zing.zalo.f.a.aXB) {
        return;
      }
      localObject1 = MainApplication.uw().getString(2131166863);
      switch (paramG.getType())
      {
      case 14: 
        if ((paramG.type != 0) && (paramG.type != 18)) {
          break label723;
        }
        if (bm.arq()) {
          break label663;
        }
        if (!bm.arr()) {
          break label817;
        }
        localObject1 = paramG.getMessage();
      }
    }
    catch (Exception paramBk)
    {
      label321:
      String str;
      t localT;
      com.zing.a.f.e.b(this.TAG, paramBk);
      return;
    }
    if ((bm.arq()) || (bm.arr()))
    {
      localObject3 = new StringBuilder().append(paramBk.g(true, false));
      if (paramG.type != 18) {
        break label859;
      }
      localObject2 = ": (SMS) ";
      localObject3 = (String)localObject2 + (String)localObject1;
      localObject4 = new StringBuilder();
      if (paramG.type != 18) {
        break label867;
      }
      localObject2 = "(SMS) ";
      localObject4 = (String)localObject2 + (String)localObject1;
      localObject1 = com.zing.zalo.d.o.xR();
      localObject2 = paramBk.g(true, false);
      localObject4 = ((CharSequence)localObject4).toString();
      localObject3 = ((CharSequence)localObject3).toString();
      str = paramBk.aRZ;
      localT = t.aBC;
      if (paramG.type != 18) {
        break label874;
      }
    }
    for (;;)
    {
      ((com.zing.zalo.d.o)localObject1).a(paramBk, (String)localObject2, (String)localObject4, (String)localObject3, str, localT, bool1, paramG.aOZ);
      return;
      if (bm.arr())
      {
        localObject1 = MainApplication.uw().getString(2131166864);
        break label820;
        if (!bm.arr()) {
          break label823;
        }
        localObject1 = MainApplication.uw().getString(2131166875);
        break label823;
        if (!bm.arr()) {
          break label826;
        }
        localObject1 = MainApplication.uw().getString(2131166872);
        break label826;
        if (!bm.arr()) {
          break label829;
        }
        localObject1 = MainApplication.uw().getString(2131166873);
        break label829;
        if (!bm.arr()) {
          break label832;
        }
        localObject1 = MainApplication.uw().getString(2131166869);
        break label832;
        if (!bm.arr()) {
          break label835;
        }
        localObject1 = MainApplication.uw().getString(2131166866);
        break label835;
        if (!bm.arr()) {
          break label838;
        }
        localObject1 = MainApplication.uw().getString(2131166876);
        break label838;
        if (!bm.arr()) {
          break label841;
        }
        localObject1 = MainApplication.uw().getString(2131166865);
        break label841;
        if (!bm.arr()) {
          break label844;
        }
        localObject1 = MainApplication.uw().getString(2131166867);
        break label844;
        if (!bm.arr()) {
          break label847;
        }
        localObject1 = MainApplication.uw().getString(2131166868);
        break label847;
        if (!bm.arr()) {
          break label850;
        }
        localObject1 = MainApplication.uw().getString(2131166870);
        break label850;
        if (!bm.arr()) {
          break label853;
        }
        localObject1 = MainApplication.uw().getString(2131166874);
        break label853;
        label663:
        if ((com.zing.zalo.f.a.aYb) || (com.zing.zalo.f.a.aXZ) || ((bm.arp().equals("com.zing.zalo.ui.PasscodeActivity")) && (PasscodeActivity.cvK == -1)) || (com.zing.a.f.c.awR() == 0))
        {
          localObject1 = MainApplication.uw().getString(2131166871);
          break label237;
        }
        localObject1 = paramG.getMessage();
        break label237;
        label723:
        if (!bm.arr()) {
          break label856;
        }
        localObject1 = paramG.getMessage();
        break label856;
        localObject2 = com.zing.zalo.d.o.xR();
        localObject3 = ((CharSequence)localObject1).toString();
        localObject1 = ((CharSequence)localObject1).toString();
        localObject4 = t.aBC;
        if (paramG.type == 18) {}
        for (bool1 = bool2;; bool1 = false)
        {
          ((com.zing.zalo.d.o)localObject2).a(paramBk, "Zalo", (String)localObject3, (String)localObject1, null, (t)localObject4, bool1, paramG.aOZ);
          return;
        }
        label808:
        return;
        label809:
        i = 0;
        break;
        break label200;
        label817:
        break label237;
      }
      label820:
      break label237;
      label823:
      break label237;
      label826:
      break label237;
      label829:
      break label237;
      label832:
      break label237;
      label835:
      break label237;
      label838:
      break label237;
      label841:
      break label237;
      label844:
      break label237;
      label847:
      break label237;
      label850:
      break label237;
      label853:
      break label237;
      label856:
      break label237;
      label859:
      localObject2 = ": ";
      break label281;
      label867:
      localObject2 = "";
      break label321;
      label874:
      bool1 = false;
    }
  }
  
  /* Error */
  private void b(bk paramBk, com.zing.zalo.control.g paramG, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 6
    //   3: aload_1
    //   4: aload_2
    //   5: getfield 936	com/zing/zalo/control/g:state	I
    //   8: putfield 939	com/zing/zalo/control/bk:aSr	I
    //   11: aload_1
    //   12: aload_2
    //   13: getfield 598	com/zing/zalo/control/g:timestamp	J
    //   16: putfield 940	com/zing/zalo/control/bk:timestamp	J
    //   19: ldc 117
    //   21: astore 8
    //   23: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   26: ifnull +1187 -> 1213
    //   29: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   32: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   35: ifnull +1178 -> 1213
    //   38: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   41: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   44: invokevirtual 953	com/zing/zalo/control/c:Cd	()Ljava/lang/String;
    //   47: astore 9
    //   49: aload 9
    //   51: astore 8
    //   53: aload 8
    //   55: astore 9
    //   57: aload 8
    //   59: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   62: ifeq +56 -> 118
    //   65: aload 8
    //   67: astore 9
    //   69: invokestatic 87	com/zing/zalo/control/ek:GK	()Lcom/zing/zalo/control/ek;
    //   72: ifnull +46 -> 118
    //   75: aload 8
    //   77: astore 9
    //   79: invokestatic 87	com/zing/zalo/control/ek:GK	()Lcom/zing/zalo/control/ek;
    //   82: invokevirtual 91	com/zing/zalo/control/ek:Dw	()Lcom/zing/zalo/control/c;
    //   85: ifnull +33 -> 118
    //   88: aload 8
    //   90: astore 9
    //   92: invokestatic 87	com/zing/zalo/control/ek:GK	()Lcom/zing/zalo/control/ek;
    //   95: invokevirtual 91	com/zing/zalo/control/ek:Dw	()Lcom/zing/zalo/control/c;
    //   98: invokevirtual 953	com/zing/zalo/control/c:Cd	()Ljava/lang/String;
    //   101: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   104: ifne +14 -> 118
    //   107: invokestatic 87	com/zing/zalo/control/ek:GK	()Lcom/zing/zalo/control/ek;
    //   110: invokevirtual 91	com/zing/zalo/control/ek:Dw	()Lcom/zing/zalo/control/c;
    //   113: invokevirtual 953	com/zing/zalo/control/c:Cd	()Ljava/lang/String;
    //   116: astore 9
    //   118: aload_2
    //   119: invokevirtual 956	com/zing/zalo/control/g:CX	()Z
    //   122: ifeq +12 -> 134
    //   125: aload_0
    //   126: aload_1
    //   127: aload_2
    //   128: getfield 958	com/zing/zalo/control/g:id	J
    //   131: invokespecial 960	com/zing/zalo/e/b:a	(Lcom/zing/zalo/control/bk;J)V
    //   134: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   137: ifnull +241 -> 378
    //   140: aload 9
    //   142: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   145: ifne +233 -> 378
    //   148: aload_2
    //   149: getfield 186	com/zing/zalo/control/g:aOW	Ljava/lang/String;
    //   152: aload 9
    //   154: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   157: ifeq +221 -> 378
    //   160: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   163: invokevirtual 963	com/zing/zalo/ui/fragments/ChatFragment:aiO	()Z
    //   166: istore 7
    //   168: iload 7
    //   170: ifeq +208 -> 378
    //   173: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   176: invokevirtual 966	com/zing/zalo/ui/fragments/ChatFragment:ahY	()V
    //   179: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   182: invokevirtual 969	com/zing/zalo/ui/fragments/ChatFragment:Dx	()V
    //   185: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   188: invokevirtual 972	com/zing/zalo/ui/fragments/ChatFragment:aiP	()V
    //   191: aload_2
    //   192: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   195: ifne +130 -> 325
    //   198: iload_3
    //   199: ifne +121 -> 320
    //   202: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   205: aload_1
    //   206: iconst_0
    //   207: aload_2
    //   208: iconst_0
    //   209: iload_3
    //   210: invokeinterface 978 6 0
    //   215: aload_1
    //   216: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   219: astore 8
    //   221: aload_2
    //   222: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   225: ifne +20 -> 245
    //   228: iload_3
    //   229: ifeq +16 -> 245
    //   232: new 980	com/zing/zalo/e/i
    //   235: dup
    //   236: aload_0
    //   237: aload 8
    //   239: invokespecial 983	com/zing/zalo/e/i:<init>	(Lcom/zing/zalo/e/b;Ljava/lang/String;)V
    //   242: invokestatic 986	com/zing/zalo/r/f:b	(Lcom/zing/zalo/db/bz;)V
    //   245: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   248: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   251: ifne +49 -> 300
    //   254: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   257: aload_1
    //   258: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   261: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   264: ifeq +36 -> 300
    //   267: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   270: ifnull +30 -> 300
    //   273: new 603	android/content/Intent
    //   276: dup
    //   277: ldc_w 991
    //   280: invokespecial 992	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   283: astore_1
    //   284: aload_1
    //   285: ldc_w 994
    //   288: iconst_1
    //   289: invokevirtual 997	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   292: pop
    //   293: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   296: aload_1
    //   297: invokevirtual 1001	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   300: return
    //   301: astore 9
    //   303: aload_0
    //   304: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   307: aload 9
    //   309: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   312: pop
    //   313: aload 8
    //   315: astore 9
    //   317: goto -199 -> 118
    //   320: iconst_0
    //   321: istore_3
    //   322: goto -120 -> 202
    //   325: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   328: aload_1
    //   329: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   332: iconst_1
    //   333: invokeinterface 1004 3 0
    //   338: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   341: aload_1
    //   342: iconst_1
    //   343: aload_2
    //   344: iconst_0
    //   345: iconst_0
    //   346: invokeinterface 978 6 0
    //   351: iconst_0
    //   352: istore_3
    //   353: goto -138 -> 215
    //   356: astore_1
    //   357: aload_0
    //   358: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   361: aload_1
    //   362: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   365: pop
    //   366: return
    //   367: astore_1
    //   368: aload_0
    //   369: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   372: aload_1
    //   373: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   376: pop
    //   377: return
    //   378: aload_2
    //   379: getfield 1007	com/zing/zalo/control/g:aPb	I
    //   382: ifeq +34 -> 416
    //   385: aload_2
    //   386: getfield 1007	com/zing/zalo/control/g:aPb	I
    //   389: iconst_m1
    //   390: if_icmpeq +26 -> 416
    //   393: aload_2
    //   394: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   397: ifne +19 -> 416
    //   400: aload_1
    //   401: invokevirtual 489	com/zing/zalo/control/bk:EF	()Z
    //   404: ifne +12 -> 416
    //   407: iload_3
    //   408: ifeq +8 -> 416
    //   411: iload 4
    //   413: ifeq +199 -> 612
    //   416: aload_2
    //   417: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   420: ifne +11 -> 431
    //   423: aload_2
    //   424: getfield 1007	com/zing/zalo/control/g:aPb	I
    //   427: iconst_m1
    //   428: if_icmpne +13 -> 441
    //   431: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   434: aload_1
    //   435: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   438: invokevirtual 1010	com/zing/zalo/d/o:do	(Ljava/lang/String;)V
    //   441: aload_2
    //   442: getfield 1007	com/zing/zalo/control/g:aPb	I
    //   445: istore 5
    //   447: iload 5
    //   449: iconst_m1
    //   450: if_icmpne +760 -> 1210
    //   453: iconst_0
    //   454: istore_3
    //   455: aload_2
    //   456: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   459: ifne +122 -> 581
    //   462: iload_3
    //   463: ifne +113 -> 576
    //   466: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   469: aload_1
    //   470: iconst_0
    //   471: aload_2
    //   472: iconst_0
    //   473: iload_3
    //   474: invokeinterface 978 6 0
    //   479: aload_1
    //   480: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   483: astore 8
    //   485: aload_2
    //   486: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   489: ifne +20 -> 509
    //   492: iload_3
    //   493: ifeq +16 -> 509
    //   496: new 980	com/zing/zalo/e/i
    //   499: dup
    //   500: aload_0
    //   501: aload 8
    //   503: invokespecial 983	com/zing/zalo/e/i:<init>	(Lcom/zing/zalo/e/b;Ljava/lang/String;)V
    //   506: invokestatic 986	com/zing/zalo/r/f:b	(Lcom/zing/zalo/db/bz;)V
    //   509: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   512: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   515: ifne -215 -> 300
    //   518: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   521: aload_1
    //   522: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   525: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   528: ifeq -228 -> 300
    //   531: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   534: ifnull -234 -> 300
    //   537: new 603	android/content/Intent
    //   540: dup
    //   541: ldc_w 991
    //   544: invokespecial 992	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   547: astore_1
    //   548: aload_1
    //   549: ldc_w 994
    //   552: iconst_1
    //   553: invokevirtual 997	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   556: pop
    //   557: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   560: aload_1
    //   561: invokevirtual 1001	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   564: return
    //   565: astore_1
    //   566: aload_0
    //   567: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   570: aload_1
    //   571: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   574: pop
    //   575: return
    //   576: iconst_1
    //   577: istore_3
    //   578: goto -112 -> 466
    //   581: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   584: aload_1
    //   585: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   588: iconst_1
    //   589: invokeinterface 1004 3 0
    //   594: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   597: aload_1
    //   598: iconst_1
    //   599: aload_2
    //   600: iconst_0
    //   601: iconst_0
    //   602: invokeinterface 978 6 0
    //   607: iconst_0
    //   608: istore_3
    //   609: goto -130 -> 479
    //   612: aload_1
    //   613: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   616: invokestatic 1013	com/zing/zalo/utils/bm:nd	(Ljava/lang/String;)Z
    //   619: ifne +67 -> 686
    //   622: getstatic 352	com/zing/a/a:dzm	Ljava/lang/String;
    //   625: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   628: ifne +58 -> 686
    //   631: aload_1
    //   632: invokevirtual 1016	com/zing/zalo/control/bk:EG	()Z
    //   635: ifne +51 -> 686
    //   638: aload_0
    //   639: aload_1
    //   640: aload_2
    //   641: invokespecial 1018	com/zing/zalo/e/b:b	(Lcom/zing/zalo/control/bk;Lcom/zing/zalo/control/g;)V
    //   644: aload_0
    //   645: aload_1
    //   646: aload_2
    //   647: invokespecial 1020	com/zing/zalo/e/b:c	(Lcom/zing/zalo/control/bk;Lcom/zing/zalo/control/g;)V
    //   650: invokestatic 538	com/zing/zalo/d/ag:yj	()Lcom/zing/zalo/d/ag;
    //   653: invokevirtual 544	com/zing/zalo/d/ag:isPlaying	()Z
    //   656: ifne +30 -> 686
    //   659: invokestatic 538	com/zing/zalo/d/ag:yj	()Lcom/zing/zalo/d/ag;
    //   662: invokevirtual 541	com/zing/zalo/d/ag:yw	()V
    //   665: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   668: ifnull +12 -> 680
    //   671: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   674: invokevirtual 1023	com/zing/zalo/ui/fragments/ChatFragment:ajn	()Z
    //   677: ifne +9 -> 686
    //   680: invokestatic 538	com/zing/zalo/d/ag:yj	()Lcom/zing/zalo/d/ag;
    //   683: invokevirtual 547	com/zing/zalo/d/ag:yz	()V
    //   686: aload_2
    //   687: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   690: ifne +122 -> 812
    //   693: iload_3
    //   694: ifne +113 -> 807
    //   697: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   700: aload_1
    //   701: iconst_0
    //   702: aload_2
    //   703: iconst_0
    //   704: iload_3
    //   705: invokeinterface 978 6 0
    //   710: aload_1
    //   711: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   714: astore 8
    //   716: aload_2
    //   717: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   720: ifne +20 -> 740
    //   723: iload_3
    //   724: ifeq +16 -> 740
    //   727: new 980	com/zing/zalo/e/i
    //   730: dup
    //   731: aload_0
    //   732: aload 8
    //   734: invokespecial 983	com/zing/zalo/e/i:<init>	(Lcom/zing/zalo/e/b;Ljava/lang/String;)V
    //   737: invokestatic 986	com/zing/zalo/r/f:b	(Lcom/zing/zalo/db/bz;)V
    //   740: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   743: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   746: ifne -446 -> 300
    //   749: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   752: aload_1
    //   753: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   756: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   759: ifeq -459 -> 300
    //   762: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   765: ifnull -465 -> 300
    //   768: new 603	android/content/Intent
    //   771: dup
    //   772: ldc_w 991
    //   775: invokespecial 992	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   778: astore_1
    //   779: aload_1
    //   780: ldc_w 994
    //   783: iconst_1
    //   784: invokevirtual 997	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   787: pop
    //   788: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   791: aload_1
    //   792: invokevirtual 1001	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   795: return
    //   796: astore_1
    //   797: aload_0
    //   798: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   801: aload_1
    //   802: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   805: pop
    //   806: return
    //   807: iconst_1
    //   808: istore_3
    //   809: goto -112 -> 697
    //   812: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   815: aload_1
    //   816: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   819: iconst_1
    //   820: invokeinterface 1004 3 0
    //   825: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   828: aload_1
    //   829: iconst_1
    //   830: aload_2
    //   831: iconst_0
    //   832: iconst_0
    //   833: invokeinterface 978 6 0
    //   838: iconst_0
    //   839: istore_3
    //   840: goto -130 -> 710
    //   843: astore 8
    //   845: iconst_1
    //   846: istore 4
    //   848: aload_0
    //   849: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   852: aload 8
    //   854: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   857: pop
    //   858: aload_2
    //   859: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   862: ifne +123 -> 985
    //   865: iload_3
    //   866: ifne +113 -> 979
    //   869: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   872: aload_1
    //   873: iconst_0
    //   874: aload_2
    //   875: iconst_0
    //   876: iload_3
    //   877: invokeinterface 978 6 0
    //   882: aload_1
    //   883: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   886: astore 8
    //   888: aload_2
    //   889: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   892: ifne +20 -> 912
    //   895: iload_3
    //   896: ifeq +16 -> 912
    //   899: new 980	com/zing/zalo/e/i
    //   902: dup
    //   903: aload_0
    //   904: aload 8
    //   906: invokespecial 983	com/zing/zalo/e/i:<init>	(Lcom/zing/zalo/e/b;Ljava/lang/String;)V
    //   909: invokestatic 986	com/zing/zalo/r/f:b	(Lcom/zing/zalo/db/bz;)V
    //   912: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   915: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   918: ifne -618 -> 300
    //   921: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   924: aload_1
    //   925: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   928: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   931: ifeq -631 -> 300
    //   934: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   937: ifnull -637 -> 300
    //   940: new 603	android/content/Intent
    //   943: dup
    //   944: ldc_w 991
    //   947: invokespecial 992	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   950: astore_1
    //   951: aload_1
    //   952: ldc_w 994
    //   955: iconst_1
    //   956: invokevirtual 997	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   959: pop
    //   960: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   963: aload_1
    //   964: invokevirtual 1001	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   967: return
    //   968: astore_1
    //   969: aload_0
    //   970: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   973: aload_1
    //   974: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   977: pop
    //   978: return
    //   979: iload 4
    //   981: istore_3
    //   982: goto -113 -> 869
    //   985: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   988: aload_1
    //   989: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   992: iconst_1
    //   993: invokeinterface 1004 3 0
    //   998: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   1001: aload_1
    //   1002: iconst_1
    //   1003: aload_2
    //   1004: iconst_0
    //   1005: iconst_0
    //   1006: invokeinterface 978 6 0
    //   1011: iconst_0
    //   1012: istore_3
    //   1013: goto -131 -> 882
    //   1016: astore 8
    //   1018: iload 6
    //   1020: istore 4
    //   1022: aload_2
    //   1023: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   1026: ifne +114 -> 1140
    //   1029: iload_3
    //   1030: ifne +104 -> 1134
    //   1033: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   1036: aload_1
    //   1037: iconst_0
    //   1038: aload_2
    //   1039: iconst_0
    //   1040: iload_3
    //   1041: invokeinterface 978 6 0
    //   1046: aload_1
    //   1047: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   1050: astore 9
    //   1052: aload_2
    //   1053: invokevirtual 975	com/zing/zalo/control/g:CZ	()Z
    //   1056: ifne +20 -> 1076
    //   1059: iload_3
    //   1060: ifeq +16 -> 1076
    //   1063: new 980	com/zing/zalo/e/i
    //   1066: dup
    //   1067: aload_0
    //   1068: aload 9
    //   1070: invokespecial 983	com/zing/zalo/e/i:<init>	(Lcom/zing/zalo/e/b;Ljava/lang/String;)V
    //   1073: invokestatic 986	com/zing/zalo/r/f:b	(Lcom/zing/zalo/db/bz;)V
    //   1076: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   1079: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1082: ifne +49 -> 1131
    //   1085: getstatic 989	com/zing/zalo/f/a:aXJ	Ljava/lang/String;
    //   1088: aload_1
    //   1089: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   1092: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1095: ifeq +36 -> 1131
    //   1098: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1101: ifnull +30 -> 1131
    //   1104: new 603	android/content/Intent
    //   1107: dup
    //   1108: ldc_w 991
    //   1111: invokespecial 992	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1114: astore_1
    //   1115: aload_1
    //   1116: ldc_w 994
    //   1119: iconst_1
    //   1120: invokevirtual 997	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   1123: pop
    //   1124: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1127: aload_1
    //   1128: invokevirtual 1001	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   1131: aload 8
    //   1133: athrow
    //   1134: iload 4
    //   1136: istore_3
    //   1137: goto -104 -> 1033
    //   1140: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   1143: aload_1
    //   1144: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   1147: iconst_1
    //   1148: invokeinterface 1004 3 0
    //   1153: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   1156: aload_1
    //   1157: iconst_1
    //   1158: aload_2
    //   1159: iconst_0
    //   1160: iconst_0
    //   1161: invokeinterface 978 6 0
    //   1166: iconst_0
    //   1167: istore_3
    //   1168: goto -122 -> 1046
    //   1171: astore_1
    //   1172: aload_0
    //   1173: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1176: aload_1
    //   1177: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1180: pop
    //   1181: goto -50 -> 1131
    //   1184: astore 8
    //   1186: iconst_0
    //   1187: istore 4
    //   1189: goto -167 -> 1022
    //   1192: astore 8
    //   1194: goto -172 -> 1022
    //   1197: astore 8
    //   1199: iconst_0
    //   1200: istore 4
    //   1202: goto -354 -> 848
    //   1205: astore 9
    //   1207: goto -904 -> 303
    //   1210: goto -755 -> 455
    //   1213: ldc 117
    //   1215: astore 8
    //   1217: goto -1164 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1220	0	this	b
    //   0	1220	1	paramBk	bk
    //   0	1220	2	paramG	com.zing.zalo.control.g
    //   0	1220	3	paramBoolean1	boolean
    //   0	1220	4	paramBoolean2	boolean
    //   445	6	5	i	int
    //   1	1018	6	bool1	boolean
    //   166	3	7	bool2	boolean
    //   21	712	8	localObject1	Object
    //   843	10	8	localException1	Exception
    //   886	19	8	str1	String
    //   1016	116	8	localObject2	Object
    //   1184	1	8	localObject3	Object
    //   1192	1	8	localObject4	Object
    //   1197	1	8	localException2	Exception
    //   1215	1	8	str2	String
    //   47	106	9	localObject5	Object
    //   301	7	9	localException3	Exception
    //   315	754	9	localObject6	Object
    //   1205	1	9	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   23	49	301	java/lang/Exception
    //   191	198	356	java/lang/Exception
    //   202	215	356	java/lang/Exception
    //   215	228	356	java/lang/Exception
    //   232	245	356	java/lang/Exception
    //   245	300	356	java/lang/Exception
    //   325	351	356	java/lang/Exception
    //   357	366	367	java/lang/Exception
    //   566	575	367	java/lang/Exception
    //   797	806	367	java/lang/Exception
    //   969	978	367	java/lang/Exception
    //   1131	1134	367	java/lang/Exception
    //   1172	1181	367	java/lang/Exception
    //   455	462	565	java/lang/Exception
    //   466	479	565	java/lang/Exception
    //   479	492	565	java/lang/Exception
    //   496	509	565	java/lang/Exception
    //   509	564	565	java/lang/Exception
    //   581	607	565	java/lang/Exception
    //   686	693	796	java/lang/Exception
    //   697	710	796	java/lang/Exception
    //   710	723	796	java/lang/Exception
    //   727	740	796	java/lang/Exception
    //   740	795	796	java/lang/Exception
    //   812	838	796	java/lang/Exception
    //   3	19	843	java/lang/Exception
    //   118	134	843	java/lang/Exception
    //   134	168	843	java/lang/Exception
    //   303	313	843	java/lang/Exception
    //   378	407	843	java/lang/Exception
    //   416	431	843	java/lang/Exception
    //   431	441	843	java/lang/Exception
    //   441	447	843	java/lang/Exception
    //   612	680	843	java/lang/Exception
    //   680	686	843	java/lang/Exception
    //   858	865	968	java/lang/Exception
    //   869	882	968	java/lang/Exception
    //   882	895	968	java/lang/Exception
    //   899	912	968	java/lang/Exception
    //   912	967	968	java/lang/Exception
    //   985	1011	968	java/lang/Exception
    //   3	19	1016	finally
    //   23	49	1016	finally
    //   57	65	1016	finally
    //   69	75	1016	finally
    //   79	88	1016	finally
    //   92	118	1016	finally
    //   118	134	1016	finally
    //   134	168	1016	finally
    //   303	313	1016	finally
    //   378	407	1016	finally
    //   416	431	1016	finally
    //   431	441	1016	finally
    //   441	447	1016	finally
    //   612	680	1016	finally
    //   680	686	1016	finally
    //   1022	1029	1171	java/lang/Exception
    //   1033	1046	1171	java/lang/Exception
    //   1046	1059	1171	java/lang/Exception
    //   1063	1076	1171	java/lang/Exception
    //   1076	1131	1171	java/lang/Exception
    //   1140	1166	1171	java/lang/Exception
    //   173	191	1184	finally
    //   848	858	1192	finally
    //   173	191	1197	java/lang/Exception
    //   57	65	1205	java/lang/Exception
    //   69	75	1205	java/lang/Exception
    //   79	88	1205	java/lang/Exception
    //   92	118	1205	java/lang/Exception
  }
  
  private void b(JSONObject paramJSONObject, String paramString)
  {
    String str2;
    String str1;
    String str3;
    String str4;
    String str5;
    int k;
    StringBuilder localStringBuilder;
    Object localObject6;
    Object localObject1;
    ArrayList localArrayList1;
    int j;
    label146:
    Object localObject5;
    Object localObject2;
    label184:
    Object localObject4;
    label205:
    label226:
    ArrayList localArrayList2;
    label325:
    boolean bool;
    try
    {
      str2 = paramJSONObject.getString("name");
      str1 = paramJSONObject.getString("groupId");
      str3 = paramJSONObject.getString("creatorId");
      str4 = paramJSONObject.getString("senderId");
      String str6 = paramJSONObject.getString("type");
      String str7 = paramJSONObject.getString("ts");
      String str8 = paramJSONObject.getString("desc");
      str5 = paramJSONObject.optString("avt");
      k = 0;
      localStringBuilder = new StringBuilder();
      localObject6 = new StringBuilder();
      localObject1 = new JSONArray();
      localArrayList1 = new ArrayList();
      if (paramJSONObject.isNull("currentMems")) {
        break label2780;
      }
      localObject1 = new JSONArray(paramJSONObject.getString("currentMems"));
      j = ((JSONArray)localObject1).length();
      i = 0;
      if (i < j)
      {
        localObject5 = (JSONObject)((JSONArray)localObject1).get(i);
        if (((JSONObject)localObject5).isNull("id")) {
          break label2790;
        }
        localObject2 = ((JSONObject)localObject5).getString("id");
        if (((JSONObject)localObject5).isNull("dName")) {
          break label2797;
        }
        localObject4 = ((JSONObject)localObject5).getString("dName");
        if (((JSONObject)localObject5).isNull("avatar")) {
          break label2804;
        }
        localObject5 = ((JSONObject)localObject5).getString("avatar");
        localArrayList1.add(localObject2);
        if (com.zing.zalo.d.v.yc().du((String)localObject2) != null) {
          break label2783;
        }
        localObject2 = new bk((String)localObject2);
        ((bk)localObject2).aRW = ((String)localObject4);
        ((bk)localObject2).aRZ = ((String)localObject5);
        com.zing.zalo.db.bj.HC().b((bk)localObject2, false);
        break label2783;
      }
      localObject1 = new JSONArray();
      localArrayList2 = new ArrayList();
      if (paramJSONObject.isNull("updateMems")) {
        break label2774;
      }
      paramJSONObject = new JSONArray(paramJSONObject.getString("updateMems"));
      if (paramJSONObject.length() <= 0) {
        break label2768;
      }
      localObject1 = (JSONObject)paramJSONObject.get(paramJSONObject.length() - 1);
      if (((JSONObject)localObject1).isNull("id")) {
        break label2820;
      }
      localObject1 = ((JSONObject)localObject1).getString("id");
      bool = com.zing.a.a.dzm.equals(localObject1);
      label378:
      int m = paramJSONObject.length();
      j = 0;
      i = k;
      label390:
      if (j < m)
      {
        localObject4 = (JSONObject)paramJSONObject.get(j);
        if (((JSONObject)localObject4).isNull("id")) {
          break label2827;
        }
        localObject1 = ((JSONObject)localObject4).getString("id");
        label429:
        if (((JSONObject)localObject4).isNull("dName")) {
          break label2834;
        }
        localObject2 = ((JSONObject)localObject4).getString("dName");
        label450:
        if (((JSONObject)localObject4).isNull("avatar")) {
          break label2841;
        }
        localObject4 = ((JSONObject)localObject4).getString("avatar");
        label471:
        localArrayList2.add(localObject1);
        if (com.zing.zalo.d.v.yc().du((String)localObject1) == null)
        {
          localObject5 = new bk((String)localObject1);
          ((bk)localObject5).aRW = ((String)localObject2);
          ((bk)localObject5).aRZ = ((String)localObject4);
          com.zing.zalo.db.bj.HC().b((bk)localObject5, false);
        }
        localObject2 = n.aY((String)localObject1, (String)localObject2);
        if (((String)localObject1).equals(com.zing.a.a.dzm)) {
          break label2848;
        }
        ((StringBuilder)localObject6).append((String)localObject2);
        if ((j == paramJSONObject.length() - 1) || ((bool) && (j == paramJSONObject.length() - 2))) {
          break label2765;
        }
        ((StringBuilder)localObject6).append(", ");
        break label2811;
      }
      if (TextUtils.isEmpty(str3)) {
        break label2866;
      }
      localObject1 = com.zing.zalo.d.v.yc().du(str3);
      label609:
      if (TextUtils.isEmpty(str4)) {
        break label2872;
      }
      paramJSONObject = com.zing.zalo.d.v.yc().du(str4);
      label626:
      if (localObject1 == null) {
        break label2758;
      }
      localObject2 = n.aY(((bk)localObject1).aOC, ((bk)localObject1).g(true, false));
      label648:
      if (paramJSONObject == null) {
        break label2751;
      }
      localObject4 = n.aY(paramJSONObject.aOC, paramJSONObject.g(true, false));
      label667:
      if (localObject6 == null) {
        break label2744;
      }
      localObject5 = ((StringBuilder)localObject6).toString();
      label679:
      if (i == 0) {
        break label2737;
      }
      if (((String)localObject5).length() > 0)
      {
        paramJSONObject = MainApplication.uw().getString(2131166687);
        break label2853;
        label704:
        paramJSONObject = com.zing.zalo.db.bj.HC().fg(str1);
        if (paramJSONObject != null) {
          break label2731;
        }
        localObject1 = new cg(str1, str6, str2, str8, str3, str4, localArrayList1, paramString, localArrayList2, str7);
        ((cg)localObject1).eh(str5);
        label752:
        if (!paramString.equals("group.join")) {
          break label1295;
        }
        if ((!com.zing.a.a.dzm.equals(str4)) && (!TextUtils.isEmpty((CharSequence)localObject4))) {
          break label941;
        }
        localStringBuilder.append(String.format(MainApplication.uw().getString(2131166892), new Object[] { localObject6, localObject5 }));
      }
      for (;;)
      {
        if ((!n.im(str4)) && (i != 0))
        {
          localStringBuilder.append("\n");
          localStringBuilder.append(MainApplication.uw().getString(2131166886));
        }
        if ((paramJSONObject == null) || (TextUtils.isEmpty(paramJSONObject.getId())) || (paramJSONObject.EX() == null) || (paramJSONObject.EZ() == null)) {
          break label1046;
        }
        j = localArrayList2.size();
        i = 0;
        label886:
        if (i >= j) {
          break;
        }
        localObject2 = (String)localArrayList2.get(i);
        if (paramJSONObject.EX().contains(localObject2)) {
          break label2859;
        }
        paramJSONObject.EX().add(localObject2);
        break label2859;
        paramJSONObject = MainApplication.uw().getString(2131166689);
        break label2853;
        label941:
        localStringBuilder.append(String.format(MainApplication.uw().getString(2131166893), new Object[] { localObject4, ((String)localObject6).toLowerCase(), localObject5 }));
      }
      j = localArrayList2.size();
    }
    catch (Exception paramJSONObject)
    {
      com.zing.a.f.e.b(this.TAG, paramJSONObject);
      return;
    }
    int i = 0;
    label1004:
    if (i < j)
    {
      localObject2 = (String)localArrayList2.get(i);
      if (paramJSONObject.EZ().contains(localObject2)) {
        paramJSONObject.EZ().remove(localObject2);
      }
    }
    else
    {
      label1046:
      if (TextUtils.isEmpty(com.zing.zalo.f.a.aYy)) {
        break label2928;
      }
      localObject2 = str1 + "_";
      if (!com.zing.zalo.f.a.aYy.startsWith((String)localObject2)) {
        break label2884;
      }
      com.zing.zalo.f.a.aYy = "";
      break label2884;
    }
    label1097:
    label1127:
    label1277:
    label1290:
    label1295:
    label1650:
    label2067:
    label2561:
    label2604:
    label2725:
    label2731:
    label2737:
    label2744:
    label2751:
    label2758:
    label2765:
    label2768:
    label2774:
    label2780:
    label2783:
    label2790:
    label2797:
    label2804:
    label2811:
    label2820:
    label2827:
    label2834:
    label2841:
    label2848:
    label2853:
    label2859:
    label2866:
    label2872:
    label2884:
    label2887:
    label2893:
    label2900:
    label2903:
    label2908:
    label2915:
    label2918:
    label2925:
    label2928:
    for (;;)
    {
      localObject2 = "";
      Object localObject3;
      if ((paramJSONObject != null) && (!TextUtils.isEmpty(paramJSONObject.getId())))
      {
        com.zing.zalo.db.bj.HC().a(paramJSONObject);
        paramJSONObject = paramJSONObject.Fb();
        if ((ChatFragment.ahI() == null) || (ChatFragment.ahI().aQb == null)) {
          break label2604;
        }
        localObject2 = "group_" + str1;
        if ((localStringBuilder != null) && (!TextUtils.isEmpty(localStringBuilder.toString().trim())))
        {
          localObject1 = new com.zing.zalo.control.g(localStringBuilder.toString(), (String)localObject2, 8);
          ((com.zing.zalo.control.g)localObject1).aOW = ((String)localObject2);
          com.zing.zalo.db.m.Hv().D((com.zing.zalo.control.g)localObject1);
          localObject4 = ChatFragment.ahI().aQb.Cc();
          if (!((String)localObject2).equals(((bk)localObject4).aOC)) {
            break label2561;
          }
          localObject2 = ek.GK().j((bk)localObject4);
          ((com.zing.zalo.control.c)localObject2).p((com.zing.zalo.control.g)localObject1);
          if (!TextUtils.isEmpty(paramJSONObject)) {
            ((com.zing.zalo.control.c)localObject2).Cc().aRZ = paramJSONObject;
          }
        }
        if (!paramString.equals("group.force.leave")) {
          break label2725;
        }
        paramJSONObject = str1;
        bm.mV(paramJSONObject);
        return;
        if (paramString.equals("group.delete"))
        {
          if (paramJSONObject == null) {
            break label2887;
          }
          if (!TextUtils.isEmpty(paramJSONObject.getName())) {
            localStringBuilder.append(String.format(MainApplication.uw().getString(2131166888), new Object[] { paramJSONObject.getName() }));
          }
          com.zing.zalo.f.a.aYy = str1 + "_1";
          paramJSONObject = null;
          com.zing.zalo.db.bj.HC().ff(str1);
          com.zing.zalo.db.m.Hv().C("group_" + str1, 0);
          com.zing.zalo.db.m.Hv().eR("group_" + str1);
          com.zing.zalo.db.bj.HC().fN("group_" + str1);
          if ((ChatFragment.ahI() != null) && (ChatFragment.ahI().aQb != null))
          {
            localObject2 = ChatFragment.ahI().aQb.Cc();
            if (("group_" + str1).equals(((bk)localObject2).aOC)) {
              ChatFragment.ahI().aiF();
            }
          }
          try
          {
            com.zing.zalo.d.o.xR().jdMethod_do("group_" + str1);
          }
          catch (Exception localException)
          {
            com.zing.a.f.e.b(this.TAG, localException);
          }
          continue;
        }
        if (paramString.equals("group.leave"))
        {
          localStringBuilder.append(String.format(MainApplication.uw().getString(2131166894), new Object[] { localObject6, localObject5 }));
          if ((paramJSONObject == null) || (TextUtils.isEmpty(paramJSONObject.getId())) || (paramJSONObject.EX() == null)) {
            break label2928;
          }
          j = localArrayList2.size();
          i = 0;
          if (i >= j) {
            break label2900;
          }
          localObject3 = (String)localArrayList2.get(i);
          if (!paramJSONObject.EX().contains(localObject3)) {
            break label2893;
          }
          paramJSONObject.EX().remove(localObject3);
          break label2893;
        }
        if (paramString.equals("group.force.leave"))
        {
          if ((com.zing.a.a.dzm.equals(str3)) || (TextUtils.isEmpty((CharSequence)localObject3))) {
            localStringBuilder.append(String.format(MainApplication.uw().getString(2131166889), new Object[] { localObject6, localObject5 }));
          }
          while ((paramJSONObject != null) && (!TextUtils.isEmpty(paramJSONObject.getId())) && (paramJSONObject.EX() != null)) {
            if (!com.zing.a.a.dzm.equals(str3))
            {
              if (i != 0)
              {
                com.zing.zalo.f.a.aYy = str1 + "_2";
                com.zing.zalo.db.bj.HC().ff(str1);
                com.zing.zalo.db.m.Hv().C("group_" + str1, 0);
                com.zing.zalo.db.m.Hv().eR("group_" + str1);
                com.zing.zalo.db.bj.HC().fN("group_" + str1);
                if ((ChatFragment.ahI() != null) && (ChatFragment.ahI().aQb != null))
                {
                  paramJSONObject = ChatFragment.ahI().aQb.Cc();
                  if (("group_" + str1).equals(paramJSONObject.aOC)) {
                    ChatFragment.ahI().aiF();
                  }
                }
                com.zing.zalo.d.o.xR().xY();
                paramJSONObject = ek.GK().eE("group_" + str1);
                if (paramJSONObject == null) {
                  break label2903;
                }
                paramJSONObject.BV();
                break label2903;
                localStringBuilder.append(String.format(MainApplication.uw().getString(2131166890), new Object[] { ((String)localObject6).toLowerCase(), localObject5, localObject3 }));
              }
              else
              {
                j = localArrayList2.size();
                i = 0;
                if (i >= j) {
                  break label2915;
                }
                localObject3 = (String)localArrayList2.get(i);
                if (!paramJSONObject.EX().contains(localObject3)) {
                  break label2908;
                }
                paramJSONObject.EX().remove(localObject3);
                break label2908;
              }
            }
            else
            {
              j = localArrayList2.size();
              i = 0;
            }
          }
        }
      }
      for (;;)
      {
        if (i >= j) {
          break label2925;
        }
        localObject3 = (String)localArrayList2.get(i);
        if (paramJSONObject.EX().contains(localObject3))
        {
          paramJSONObject.EX().remove(localObject3);
          break label2918;
          if ((com.zing.a.a.dzm.equals(str3)) || (i == 0)) {
            break label2928;
          }
          localObject1 = null;
          break label1097;
          if (!paramString.equals("group.update")) {
            break label2928;
          }
          j = 0;
          k = 0;
          i = j;
          if (paramJSONObject != null)
          {
            i = j;
            if (!TextUtils.isEmpty(paramJSONObject.getId()))
            {
              i = j;
              if (!TextUtils.isEmpty(str2))
              {
                i = j;
                if (!str2.equals(paramJSONObject.getName()))
                {
                  paramJSONObject.setName(str2);
                  com.zing.zalo.db.m.Hv().am("group_" + str1, str2);
                  i = 1;
                }
              }
            }
          }
          j = k;
          if (paramJSONObject != null)
          {
            j = k;
            if (!TextUtils.isEmpty(paramJSONObject.getId()))
            {
              j = k;
              if (!TextUtils.isEmpty(str5))
              {
                j = k;
                if (!str5.equals(paramJSONObject.Fb()))
                {
                  paramJSONObject.eh(str5);
                  com.zing.zalo.db.m.Hv().an("group_" + str1, str5);
                  j = 1;
                }
              }
            }
          }
          if ((i != 0) && (j != 0))
          {
            localStringBuilder.append(String.format(MainApplication.uw().getString(2131166904), new Object[] { localObject4 }));
            break label1097;
          }
          if (i != 0)
          {
            if ((com.zing.a.a.dzm.equals(str4)) || (TextUtils.isEmpty((CharSequence)localObject4)))
            {
              localStringBuilder.append(MainApplication.uw().getString(2131166896));
              break label1097;
            }
            localStringBuilder.append(String.format(MainApplication.uw().getString(2131166899), new Object[] { localObject4, str2 }));
            break label1097;
          }
          if (j != 0) {
            break label2928;
          }
          localStringBuilder.append(String.format(MainApplication.uw().getString(2131166904), new Object[] { localObject4 }));
          break label2928;
          paramJSONObject = (JSONObject)localObject3;
          if (localObject1 == null) {
            break label1127;
          }
          paramJSONObject = (JSONObject)localObject3;
          if (TextUtils.isEmpty(((cg)localObject1).getId())) {
            break label1127;
          }
          com.zing.zalo.db.bj.HC().a((cg)localObject1);
          paramJSONObject = ((cg)localObject1).Fb();
          break label1127;
          localObject3 = new bk(1, str1, localArrayList1);
          ((bk)localObject3).aRW = str2;
          ((bk)localObject3).aRZ = paramJSONObject;
          ek.GK().j((bk)localObject3).p((com.zing.zalo.control.g)localObject1);
          break label1277;
          localObject1 = "group_" + str1;
          if ((localStringBuilder == null) || (TextUtils.isEmpty(localStringBuilder.toString().trim()))) {
            break label1277;
          }
          localObject3 = new com.zing.zalo.control.g(localStringBuilder.toString(), (String)localObject1, 8);
          ((com.zing.zalo.control.g)localObject3).aOW = ((String)localObject1);
          com.zing.zalo.db.m.Hv().D((com.zing.zalo.control.g)localObject3);
          localObject1 = new bk(1, str1, localArrayList1);
          ((bk)localObject1).aRW = str2;
          ((bk)localObject1).aRZ = paramJSONObject;
          ek.GK().j((bk)localObject1).p((com.zing.zalo.control.g)localObject3);
          break label1277;
          paramJSONObject = "";
          break label1290;
          localObject1 = null;
          break label752;
          localObject6 = "";
          break label704;
          localObject5 = "";
          break label679;
          localObject4 = "";
          break label667;
          localObject3 = "";
          break label648;
          break label2811;
          bool = false;
          break label378;
          paramJSONObject = (JSONObject)localObject1;
          break label325;
          break;
          i += 1;
          break label146;
          localObject3 = "";
          break label184;
          localObject4 = "";
          break label205;
          localObject5 = "";
          break label226;
          for (;;)
          {
            j += 1;
            break label390;
            localObject1 = "";
            break;
            localObject1 = "";
            break label429;
            localObject3 = "";
            break label450;
            localObject4 = "";
            break label471;
            i = 1;
          }
          localObject6 = paramJSONObject;
          break label704;
          i += 1;
          break label886;
          localObject1 = null;
          break label609;
          paramJSONObject = null;
          break label626;
          i += 1;
          break label1004;
          break label1097;
          localObject1 = null;
          break label1097;
          i += 1;
          break label1650;
          break label1097;
          paramJSONObject = null;
          break label1097;
          i += 1;
          break label2067;
          break label1097;
        }
        i += 1;
      }
    }
  }
  
  private void c(bk paramBk, com.zing.zalo.control.g paramG)
  {
    try
    {
      if ((!com.zing.zalo.f.c.cN(MainApplication.uw())) || ((com.zing.zalo.f.a.aXX) && (mr.akr() != null) && (mr.akr().getCurrentTab() == 0)) || (com.zing.zalo.f.a.aXB)) {
        return;
      }
      if (paramG.aPb == 3) {
        return;
      }
      if ((System.currentTimeMillis() - com.zing.zalo.f.a.aYo <= 5000L) || (!bm.arr()) || (!com.zing.zalo.f.c.cO(MainApplication.uw())) || (n.io(paramBk.aOC)) || (paramBk.Cn()) || (com.zing.a.f.c.awR() >= 2) || (bm.arq()) || (com.zing.zalo.h.a.Px().hZ(paramBk.aOC))) {
        return;
      }
      if (RetryMsgPopupActivity.afy() != null)
      {
        RetryMsgPopupActivity.dU(false);
        RetryMsgPopupActivity.afy().finish();
      }
      if (MessagePopupSMSActivity.adN()) {
        return;
      }
      if (!MessagePopupActivity.adN()) {
        break label221;
      }
      if (paramG.getType() == 18)
      {
        new Handler(Looper.getMainLooper()).postDelayed(new j(this, paramBk, paramG), 2000L);
        return;
      }
    }
    catch (Exception paramBk)
    {
      paramBk.printStackTrace();
      return;
    }
    a(paramBk.aOC, paramBk.aRW, paramG.getMessage(), paramG.getType(), paramBk.aRZ, paramG.timestamp, paramG.aOZ);
    return;
    label221:
    if (MainApplication.uw() != null)
    {
      Intent localIntent = new Intent(MainApplication.uw(), MessagePopupActivity.class);
      localIntent.addFlags(67108864);
      localIntent.addFlags(268435456);
      if ((MessagePopupActivity.adP() != null) && (!MessagePopupActivity.adP().isEmpty()))
      {
        paramBk = new bq(paramBk.aOC, paramBk.aRW, paramG.getMessage(), paramG.getType(), paramBk.aRZ, paramG.timestamp, paramG.aOZ);
        MessagePopupActivity.adP().add(paramBk);
      }
      while (paramG.getType() == 18)
      {
        new Handler(Looper.getMainLooper()).postDelayed(new k(this, localIntent), 2000L);
        return;
        localIntent.putExtra("senderUID", paramBk.aOC);
        localIntent.putExtra("senderName", paramBk.aRW);
        localIntent.putExtra("message", paramG.getMessage());
        localIntent.putExtra("typemsg", paramG.getType());
        localIntent.putExtra("urlAvatar", paramBk.aRZ);
        localIntent.putExtra("timestamp", paramG.CB());
        paramBk = new bq(paramBk.aOC, paramBk.aRW, paramG.getMessage(), paramG.getType(), paramBk.aRZ, paramG.timestamp, paramG.aOZ);
        MessagePopupActivity.I(new ArrayList());
        MessagePopupActivity.adP().add(paramBk);
      }
      MainApplication.uw().startActivity(localIntent);
    }
  }
  
  private void c(JSONObject paramJSONObject, String paramString)
  {
    int j;
    int i;
    for (;;)
    {
      Object localObject3;
      Object localObject4;
      String str1;
      String str2;
      try
      {
        localObject3 = new StringBuilder();
        localObject4 = new ArrayList();
        str1 = bm.d(paramJSONObject, "roomId");
        str2 = bm.d(paramJSONObject, "name");
        localObject1 = new JSONArray();
        localObject2 = new ArrayList();
        if (paramJSONObject.isNull("updateMember")) {
          break label719;
        }
        paramJSONObject = new JSONArray(paramJSONObject.getString("updateMember"));
        int k = paramJSONObject.length();
        j = 0;
        i = 0;
        if (j >= k) {
          break label327;
        }
        localObject1 = (JSONObject)paramJSONObject.get(j);
        bk localBk = new bk();
        localBk.aOC = ((JSONObject)localObject1).getString("id");
        localBk.aRW = ((JSONObject)localObject1).getString("dName");
        localBk.aRZ = ((JSONObject)localObject1).getString("avatar");
        ((ArrayList)localObject4).add(localBk);
        ((ArrayList)localObject2).add(localBk.aOC);
        if (!localBk.aOC.equals(com.zing.a.a.dzm))
        {
          ((StringBuilder)localObject3).append(localBk.aRW);
          if (j >= paramJSONObject.length() - 1) {
            break label716;
          }
          ((StringBuilder)localObject3).append(", ");
          break;
        }
        if (j < paramJSONObject.length() - 1)
        {
          if (j == 0)
          {
            localObject1 = MainApplication.uw().getString(2131166687);
            ((StringBuilder)localObject3).append((String)localObject1);
          }
          else
          {
            localObject1 = MainApplication.uw().getString(2131166688);
            continue;
          }
        }
        else if (j == 0)
        {
          localObject1 = MainApplication.uw().getString(2131166689);
          ((StringBuilder)localObject3).append((String)localObject1);
        }
      }
      catch (Exception paramJSONObject)
      {
        com.zing.a.f.e.b(this.TAG, paramJSONObject);
        return;
      }
      Object localObject1 = MainApplication.uw().getString(2131166690);
      continue;
      label327:
      if (paramString.equals("room.join")) {
        ((StringBuilder)localObject3).append(String.format(MainApplication.uw().getString(2131166901), new Object[] { str2 }));
      }
      for (;;)
      {
        localObject4 = "room_" + str1;
        if ((ChatFragment.ahI() == null) || (ChatFragment.ahI().aQb == null)) {
          break;
        }
        paramJSONObject = new com.zing.zalo.control.g(((StringBuilder)localObject3).toString(), (String)localObject4, 8);
        paramJSONObject.aOW = ((String)localObject4);
        localObject1 = ChatFragment.ahI().aQb.Cc();
        if (com.zing.zalo.f.c.dl(MainApplication.uw()))
        {
          if (!((String)localObject4).equals(((bk)localObject1).aOC)) {
            break label622;
          }
          ek.GK().j((bk)localObject1).p(paramJSONObject);
        }
        label470:
        if ((!paramString.equals("room.force.leave")) || (i == 0)) {
          break label685;
        }
        if (("room_" + str1).equals(((bk)localObject1).aOC)) {
          ChatFragment.ahI().aiG();
        }
        paramJSONObject = new bk(2, str1, null);
        ek.GK().f(paramJSONObject);
        return;
        if (paramString.equals("room.leave")) {
          ((StringBuilder)localObject3).append(String.format(MainApplication.uw().getString(2131166902), new Object[] { str2 }));
        } else if (paramString.equals("room.force.leave")) {
          ((StringBuilder)localObject3).append(String.format(MainApplication.uw().getString(2131166900), new Object[] { str2 }));
        }
      }
      label622:
      Object localObject2 = new bk(2, str1, (ArrayList)localObject2);
      ((bk)localObject2).aRW = "";
      if ((com.zing.zalo.f.a.aXD != null) && (!TextUtils.isEmpty(com.zing.zalo.f.a.aXD.aRZ))) {
        ((bk)localObject2).aRZ = com.zing.zalo.f.a.aXD.aRZ;
      }
      for (;;)
      {
        ek.GK().j((bk)localObject2).p(paramJSONObject);
        break label470;
        label685:
        break;
        localObject3 = com.zing.zalo.d.v.yc().dt(com.zing.a.a.dzm);
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          ((bk)localObject2).aRZ = ((String)localObject3);
        }
      }
      label716:
      break;
      label719:
      paramJSONObject = (JSONObject)localObject1;
    }
    for (;;)
    {
      j += 1;
      break;
      i = 1;
    }
  }
  
  private void d(com.zing.a.d.a.e paramE)
  {
    int j = 0;
    try
    {
      if ((paramE.WA() == 266) && (!paramE.Wz().equals("")))
      {
        paramE = new JSONObject(paramE.Wz());
        if (paramE != null)
        {
          if (com.zing.zalo.f.a.aXF == null) {
            ed.GA();
          }
          paramE = paramE.getJSONObject("data");
          if ((paramE != null) && (paramE.has("roomId")))
          {
            paramE = bm.d(paramE, "roomId");
            int i = j;
            if (!TextUtils.isEmpty(paramE))
            {
              i = j;
              if (TextUtils.equals(paramE, com.zing.zalo.f.a.aXF.GH())) {
                i = 1;
              }
            }
            if (i != 0)
            {
              com.zing.zalo.f.a.aXF.bv(false);
              paramE = "wifi_room_" + com.zing.zalo.f.a.aXF.GH();
              if (!TextUtils.isEmpty(paramE)) {
                ed.ah(paramE, "");
              }
              if (MainApplication.uw() != null)
              {
                paramE = new Intent("com.zing.zalo.ACTION_WIFI_CHAT_KICK_OUT");
                MainApplication.uw().sendBroadcast(paramE);
              }
            }
          }
        }
      }
      return;
    }
    catch (Exception paramE)
    {
      paramE.printStackTrace();
    }
  }
  
  /* Error */
  private void dG(String paramString)
  {
    // Byte code:
    //   0: new 248	org/json/JSONArray
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 1042	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   8: astore 12
    //   10: new 241	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 242	java/util/ArrayList:<init>	()V
    //   17: astore 13
    //   19: iconst_0
    //   20: istore_2
    //   21: aload 12
    //   23: invokevirtual 251	org/json/JSONArray:length	()I
    //   26: istore_3
    //   27: iload_2
    //   28: iload_3
    //   29: if_icmpge +5180 -> 5209
    //   32: aload 12
    //   34: iload_2
    //   35: invokevirtual 255	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual 1305	org/json/JSONObject:toString	()Ljava/lang/String;
    //   43: invokestatic 1310	com/zing/zalo/utils/y:mz	(Ljava/lang/String;)Z
    //   46: pop
    //   47: aload 13
    //   49: aload_1
    //   50: ldc_w 270
    //   53: invokevirtual 277	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   56: invokestatic 283	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   59: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   62: pop
    //   63: aload_1
    //   64: ldc_w 262
    //   67: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   70: istore 4
    //   72: aload_1
    //   73: ldc_w 1312
    //   76: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   79: astore 14
    //   81: aload_1
    //   82: ldc_w 1314
    //   85: invokevirtual 1317	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   88: iconst_1
    //   89: if_icmpne +398 -> 487
    //   92: iconst_1
    //   93: istore_3
    //   94: aload_1
    //   95: ldc_w 1319
    //   98: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   101: astore 8
    //   103: aload_1
    //   104: ldc -23
    //   106: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   109: astore 9
    //   111: iload 4
    //   113: iconst_1
    //   114: if_icmpeq +41 -> 155
    //   117: iload 4
    //   119: iconst_2
    //   120: if_icmpeq +35 -> 155
    //   123: iload 4
    //   125: iconst_5
    //   126: if_icmpeq +29 -> 155
    //   129: iload 4
    //   131: ifeq +24 -> 155
    //   134: iload 4
    //   136: bipush 23
    //   138: if_icmpeq +17 -> 155
    //   141: iload 4
    //   143: bipush 21
    //   145: if_icmpeq +10 -> 155
    //   148: iload 4
    //   150: bipush 42
    //   152: if_icmpne +492 -> 644
    //   155: aload_0
    //   156: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   159: new 114	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   166: ldc_w 1321
    //   169: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload 14
    //   174: invokevirtual 1324	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 525	com/zing/a/f/e:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   183: pop
    //   184: new 1326	com/zing/zalo/control/cy
    //   187: dup
    //   188: aload 14
    //   190: invokespecial 1328	com/zing/zalo/control/cy:<init>	(Lorg/json/JSONObject;)V
    //   193: astore 11
    //   195: new 1330	com/zing/zalo/control/cz
    //   198: dup
    //   199: aload 14
    //   201: invokespecial 1331	com/zing/zalo/control/cz:<init>	(Lorg/json/JSONObject;)V
    //   204: astore 9
    //   206: aload 11
    //   208: invokevirtual 1335	com/zing/zalo/control/cy:Fx	()Ljava/lang/CharSequence;
    //   211: invokeinterface 886 1 0
    //   216: astore 15
    //   218: aload 11
    //   220: getfield 1336	com/zing/zalo/control/cy:aPb	I
    //   223: istore 5
    //   225: aload 11
    //   227: invokevirtual 1339	com/zing/zalo/control/cy:Fu	()Ljava/lang/String;
    //   230: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   233: ifeq +42 -> 275
    //   236: ldc 117
    //   238: astore 8
    //   240: aload 11
    //   242: invokevirtual 1342	com/zing/zalo/control/cy:Fw	()Ljava/lang/String;
    //   245: astore_1
    //   246: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   249: aload_1
    //   250: invokevirtual 753	com/zing/zalo/d/v:du	(Ljava/lang/String;)Lcom/zing/zalo/control/bk;
    //   253: astore 10
    //   255: aload 8
    //   257: astore_1
    //   258: aload 10
    //   260: ifnull +9 -> 269
    //   263: aload 10
    //   265: getfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   268: astore_1
    //   269: aload 11
    //   271: aload_1
    //   272: invokevirtual 1345	com/zing/zalo/control/cy:em	(Ljava/lang/String;)V
    //   275: aload 9
    //   277: invokevirtual 1348	com/zing/zalo/control/cz:FD	()I
    //   280: iconst_m1
    //   281: if_icmpeq +14 -> 295
    //   284: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   287: aload 9
    //   289: invokevirtual 1351	com/zing/zalo/db/bj:b	(Lcom/zing/zalo/control/cz;)Z
    //   292: ifeq +14 -> 306
    //   295: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   298: aload 9
    //   300: invokevirtual 1354	com/zing/zalo/db/bj:c	(Lcom/zing/zalo/control/cz;)V
    //   303: invokestatic 1357	com/zing/zalo/utils/bm:YI	()V
    //   306: iload 4
    //   308: iconst_1
    //   309: if_icmpeq +9 -> 318
    //   312: iload 4
    //   314: iconst_2
    //   315: if_icmpne +311 -> 626
    //   318: ldc 117
    //   320: astore 8
    //   322: ldc 117
    //   324: astore 9
    //   326: iconst_1
    //   327: istore_3
    //   328: aload 14
    //   330: ldc_w 1359
    //   333: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   336: astore 10
    //   338: aload 10
    //   340: ldc_w 1361
    //   343: invokevirtual 1363	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   346: ifeq +4925 -> 5271
    //   349: iconst_2
    //   350: istore_3
    //   351: ldc_w 1365
    //   354: astore_1
    //   355: ldc_w 1367
    //   358: bipush 34
    //   360: invokestatic 1373	java/util/regex/Pattern:compile	(Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   363: aload 10
    //   365: invokevirtual 1377	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   368: astore 14
    //   370: aload 14
    //   372: invokevirtual 1382	java/util/regex/Matcher:find	()Z
    //   375: ifeq +158 -> 533
    //   378: aload 14
    //   380: iconst_0
    //   381: invokevirtual 1385	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   384: astore 10
    //   386: aload 10
    //   388: aload_1
    //   389: invokevirtual 1107	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   392: ifeq -22 -> 370
    //   395: ldc_w 1387
    //   398: bipush 34
    //   400: invokestatic 1373	java/util/regex/Pattern:compile	(Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   403: aload 10
    //   405: invokevirtual 1377	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   408: astore 10
    //   410: aload 10
    //   412: invokevirtual 1382	java/util/regex/Matcher:find	()Z
    //   415: ifeq -45 -> 370
    //   418: aload 10
    //   420: iconst_1
    //   421: invokevirtual 1385	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   424: ldc_w 1389
    //   427: ldc 117
    //   429: invokevirtual 1393	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   432: astore 16
    //   434: iload_3
    //   435: iconst_1
    //   436: if_icmpne +89 -> 525
    //   439: ldc_w 1395
    //   442: astore 10
    //   444: aload 16
    //   446: aload 10
    //   448: ldc 117
    //   450: invokevirtual 1393	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   453: ldc_w 1397
    //   456: invokevirtual 1401	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   459: astore 10
    //   461: aload 10
    //   463: ifnull -93 -> 370
    //   466: aload 10
    //   468: arraylength
    //   469: ifle -99 -> 370
    //   472: aload 10
    //   474: iconst_0
    //   475: aaload
    //   476: astore 9
    //   478: aload 10
    //   480: iconst_1
    //   481: aaload
    //   482: astore 8
    //   484: goto -114 -> 370
    //   487: iconst_0
    //   488: istore_3
    //   489: goto -395 -> 94
    //   492: astore_1
    //   493: aload_0
    //   494: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   497: aload_1
    //   498: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   501: pop
    //   502: aload 8
    //   504: astore_1
    //   505: goto -236 -> 269
    //   508: astore_1
    //   509: aload_0
    //   510: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   513: aload_1
    //   514: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   517: pop
    //   518: iload_2
    //   519: iconst_1
    //   520: iadd
    //   521: istore_2
    //   522: goto -501 -> 21
    //   525: ldc_w 1361
    //   528: astore 10
    //   530: goto -86 -> 444
    //   533: aload 8
    //   535: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   538: ifne +70 -> 608
    //   541: aload 9
    //   543: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   546: ifne +62 -> 608
    //   549: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   552: iload 5
    //   554: aload 15
    //   556: iload_3
    //   557: aload 8
    //   559: aload 9
    //   561: aload 11
    //   563: invokevirtual 1339	com/zing/zalo/control/cy:Fu	()Ljava/lang/String;
    //   566: aload 11
    //   568: invokevirtual 1402	com/zing/zalo/control/cy:FD	()I
    //   571: invokevirtual 1405	com/zing/zalo/d/o:a	(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   574: goto -56 -> 518
    //   577: astore_1
    //   578: aload_1
    //   579: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   582: aload_1
    //   583: invokevirtual 1406	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   586: invokestatic 1310	com/zing/zalo/utils/y:mz	(Ljava/lang/String;)Z
    //   589: pop
    //   590: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   593: iload 5
    //   595: aload 15
    //   597: aload 11
    //   599: invokevirtual 1339	com/zing/zalo/control/cy:Fu	()Ljava/lang/String;
    //   602: invokevirtual 1409	com/zing/zalo/d/o:c	(ILjava/lang/String;Ljava/lang/String;)V
    //   605: goto -87 -> 518
    //   608: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   611: iload 5
    //   613: aload 15
    //   615: aload 11
    //   617: invokevirtual 1339	com/zing/zalo/control/cy:Fu	()Ljava/lang/String;
    //   620: invokevirtual 1409	com/zing/zalo/d/o:c	(ILjava/lang/String;Ljava/lang/String;)V
    //   623: goto -105 -> 518
    //   626: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   629: iload 5
    //   631: aload 15
    //   633: aload 11
    //   635: invokevirtual 1339	com/zing/zalo/control/cy:Fu	()Ljava/lang/String;
    //   638: invokevirtual 1409	com/zing/zalo/d/o:c	(ILjava/lang/String;Ljava/lang/String;)V
    //   641: goto -123 -> 518
    //   644: iload 4
    //   646: iconst_3
    //   647: if_icmpne +170 -> 817
    //   650: getstatic 1412	com/zing/zalo/f/a:aXz	I
    //   653: iconst_1
    //   654: iadd
    //   655: putstatic 1412	com/zing/zalo/f/a:aXz	I
    //   658: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   661: getstatic 1412	com/zing/zalo/f/a:aXz	I
    //   664: invokestatic 1416	com/zing/zalo/f/c:F	(Landroid/content/Context;I)V
    //   667: invokestatic 1419	com/zing/zalo/utils/bm:akE	()V
    //   670: iload_3
    //   671: ifeq -153 -> 518
    //   674: aload 8
    //   676: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   679: ifne -161 -> 518
    //   682: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   685: aload 8
    //   687: invokevirtual 753	com/zing/zalo/d/v:du	(Ljava/lang/String;)Lcom/zing/zalo/control/bk;
    //   690: astore_1
    //   691: aload_1
    //   692: ifnull -174 -> 518
    //   695: aload 8
    //   697: aload_1
    //   698: getfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   701: invokestatic 701	com/zing/zalo/h/n:aY	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   704: astore 10
    //   706: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   709: invokevirtual 1423	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   712: ldc_w 1424
    //   715: invokevirtual 1427	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   718: iconst_1
    //   719: anewarray 4	java/lang/Object
    //   722: dup
    //   723: iconst_0
    //   724: aload 10
    //   726: aastore
    //   727: invokestatic 730	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   730: astore_1
    //   731: aload 9
    //   733: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   736: ifne +14 -> 750
    //   739: aload 9
    //   741: ldc_w 1429
    //   744: aload 10
    //   746: invokevirtual 1393	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   749: astore_1
    //   750: aload 8
    //   752: aload_1
    //   753: invokestatic 1432	com/zing/zalo/utils/bm:bu	(Ljava/lang/String;Ljava/lang/String;)V
    //   756: aload 8
    //   758: invokestatic 1435	com/zing/zalo/h/n:iu	(Ljava/lang/String;)V
    //   761: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   764: ifnull -246 -> 518
    //   767: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   770: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   773: ifnull -255 -> 518
    //   776: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   779: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   782: invokevirtual 1129	com/zing/zalo/control/c:Cc	()Lcom/zing/zalo/control/bk;
    //   785: ifnull -267 -> 518
    //   788: aload 8
    //   790: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   793: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   796: invokevirtual 1129	com/zing/zalo/control/c:Cc	()Lcom/zing/zalo/control/bk;
    //   799: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   802: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   805: ifeq -287 -> 518
    //   808: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   811: invokevirtual 1438	com/zing/zalo/ui/fragments/ChatFragment:ajS	()V
    //   814: goto -296 -> 518
    //   817: iload 4
    //   819: bipush 50
    //   821: if_icmpne +181 -> 1002
    //   824: aload_1
    //   825: ldc_w 1440
    //   828: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   831: ifeq -313 -> 518
    //   834: aload 14
    //   836: ldc_w 1442
    //   839: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   842: astore 9
    //   844: aload 14
    //   846: ldc_w 1230
    //   849: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   852: astore 8
    //   854: aload 14
    //   856: ldc_w 1047
    //   859: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   862: astore 10
    //   864: aload_1
    //   865: ldc_w 1440
    //   868: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   871: ldc_w 1444
    //   874: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   877: astore 11
    //   879: aload_1
    //   880: ldc_w 1446
    //   883: invokestatic 1449	com/zing/zalo/utils/bm:a	(Lorg/json/JSONObject;Ljava/lang/String;)I
    //   886: istore 4
    //   888: aload_1
    //   889: ldc_w 1451
    //   892: invokestatic 1449	com/zing/zalo/utils/bm:a	(Lorg/json/JSONObject;Ljava/lang/String;)I
    //   895: istore_3
    //   896: new 114	java/lang/StringBuilder
    //   899: dup
    //   900: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   903: aload 11
    //   905: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   908: ldc_w 1104
    //   911: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   914: aload 9
    //   916: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: ldc_w 1104
    //   922: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: iload 4
    //   927: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   930: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   933: invokevirtual 1457	java/lang/String:hashCode	()I
    //   936: istore 4
    //   938: new 1330	com/zing/zalo/control/cz
    //   941: dup
    //   942: new 114	java/lang/StringBuilder
    //   945: dup
    //   946: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   949: iload 4
    //   951: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   954: ldc 117
    //   956: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   962: iload_3
    //   963: invokespecial 1460	com/zing/zalo/control/cz:<init>	(Ljava/lang/String;I)V
    //   966: astore_1
    //   967: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   970: aload_1
    //   971: invokevirtual 1351	com/zing/zalo/db/bj:b	(Lcom/zing/zalo/control/cz;)Z
    //   974: ifeq +13 -> 987
    //   977: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   980: aload_1
    //   981: invokevirtual 1354	com/zing/zalo/db/bj:c	(Lcom/zing/zalo/control/cz;)V
    //   984: invokestatic 1357	com/zing/zalo/utils/bm:YI	()V
    //   987: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   990: iload 4
    //   992: aload 8
    //   994: aload 10
    //   996: invokevirtual 1462	com/zing/zalo/d/o:d	(ILjava/lang/String;Ljava/lang/String;)V
    //   999: goto -481 -> 518
    //   1002: iload 4
    //   1004: bipush 10
    //   1006: if_icmpne +867 -> 1873
    //   1009: aload 14
    //   1011: ldc_w 1464
    //   1014: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1017: ifeq +369 -> 1386
    //   1020: ldc 117
    //   1022: astore 8
    //   1024: aload 14
    //   1026: ldc_w 1466
    //   1029: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1032: ifeq +367 -> 1399
    //   1035: sipush 1000
    //   1038: istore_3
    //   1039: aload 14
    //   1041: ldc_w 1442
    //   1044: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1047: ifeq +364 -> 1411
    //   1050: ldc 117
    //   1052: astore_1
    //   1053: aload 14
    //   1055: ldc_w 1468
    //   1058: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1061: ifeq +362 -> 1423
    //   1064: ldc 117
    //   1066: astore 9
    //   1068: aload 14
    //   1070: ldc_w 1035
    //   1073: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1076: ifeq +360 -> 1436
    //   1079: ldc 117
    //   1081: astore 10
    //   1083: aload 14
    //   1085: ldc_w 1470
    //   1088: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1091: ifeq +358 -> 1449
    //   1094: ldc 117
    //   1096: astore 11
    //   1098: aload 14
    //   1100: ldc_w 1472
    //   1103: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1106: ifne +4179 -> 5285
    //   1109: aload 14
    //   1111: ldc_w 1472
    //   1114: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1117: istore 4
    //   1119: new 482	com/zing/zalo/control/bk
    //   1122: dup
    //   1123: aload_1
    //   1124: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   1127: astore 14
    //   1129: aload 14
    //   1131: aload 10
    //   1133: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   1136: aload 14
    //   1138: aload 9
    //   1140: putfield 1475	com/zing/zalo/control/bk:aRY	Ljava/lang/String;
    //   1143: aload 14
    //   1145: aload 11
    //   1147: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   1150: aload 14
    //   1152: aload 8
    //   1154: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   1157: aload 14
    //   1159: iconst_1
    //   1160: putfield 1481	com/zing/zalo/control/bk:aSS	I
    //   1163: aload 14
    //   1165: iload_3
    //   1166: putfield 1484	com/zing/zalo/control/bk:aSB	I
    //   1169: new 482	com/zing/zalo/control/bk
    //   1172: dup
    //   1173: aload_1
    //   1174: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   1177: astore 15
    //   1179: aload 15
    //   1181: aload 10
    //   1183: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   1186: aload 15
    //   1188: aload 9
    //   1190: putfield 1475	com/zing/zalo/control/bk:aRY	Ljava/lang/String;
    //   1193: aload 15
    //   1195: aload 11
    //   1197: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   1200: aload 15
    //   1202: aload 8
    //   1204: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   1207: aload 15
    //   1209: iload_3
    //   1210: putfield 1484	com/zing/zalo/control/bk:aSB	I
    //   1213: aload 15
    //   1215: iconst_1
    //   1216: putfield 1481	com/zing/zalo/control/bk:aSS	I
    //   1219: aload 15
    //   1221: iload 4
    //   1223: putfield 1485	com/zing/zalo/control/bk:aPb	I
    //   1226: aload_1
    //   1227: invokestatic 1488	com/zing/zalo/h/n:il	(Ljava/lang/String;)Z
    //   1230: ifne +450 -> 1680
    //   1233: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1236: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1239: invokevirtual 374	java/util/ArrayList:size	()I
    //   1242: iconst_5
    //   1243: isub
    //   1244: istore 4
    //   1246: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1249: invokestatic 1494	com/zing/zalo/f/c:er	(Landroid/content/Context;)Ljava/lang/String;
    //   1252: astore 9
    //   1254: new 241	java/util/ArrayList
    //   1257: dup
    //   1258: invokespecial 242	java/util/ArrayList:<init>	()V
    //   1261: astore 8
    //   1263: aload 9
    //   1265: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1268: ifne +4000 -> 5268
    //   1271: new 241	java/util/ArrayList
    //   1274: dup
    //   1275: aload 9
    //   1277: ldc_w 1496
    //   1280: invokestatic 1499	android/text/TextUtils:split	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
    //   1283: invokestatic 1505	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   1286: invokespecial 1508	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   1289: astore 8
    //   1291: iload 4
    //   1293: ifle +169 -> 1462
    //   1296: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1299: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1302: invokevirtual 374	java/util/ArrayList:size	()I
    //   1305: iconst_1
    //   1306: isub
    //   1307: istore_3
    //   1308: iload_3
    //   1309: iflt +153 -> 1462
    //   1312: iload_3
    //   1313: iflt +3965 -> 5278
    //   1316: iload_3
    //   1317: iload 4
    //   1319: if_icmpge +3959 -> 5278
    //   1322: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1325: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1328: iload_3
    //   1329: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1332: checkcast 1510	com/zing/zalo/control/cx
    //   1335: invokevirtual 1513	com/zing/zalo/control/cx:Fs	()Ljava/lang/String;
    //   1338: astore 9
    //   1340: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1343: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1346: iload_3
    //   1347: invokevirtual 1515	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   1350: pop
    //   1351: aload 8
    //   1353: aload 9
    //   1355: invokevirtual 1094	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   1358: ifeq +3920 -> 5278
    //   1361: aload 8
    //   1363: aload 9
    //   1365: invokevirtual 347	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   1368: pop
    //   1369: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1372: ifle +3906 -> 5278
    //   1375: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1378: iconst_1
    //   1379: isub
    //   1380: putstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1383: goto +3895 -> 5278
    //   1386: aload 14
    //   1388: ldc_w 1464
    //   1391: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1394: astore 8
    //   1396: goto -372 -> 1024
    //   1399: aload 14
    //   1401: ldc_w 1466
    //   1404: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1407: istore_3
    //   1408: goto -369 -> 1039
    //   1411: aload 14
    //   1413: ldc_w 1442
    //   1416: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1419: astore_1
    //   1420: goto -367 -> 1053
    //   1423: aload 14
    //   1425: ldc_w 1468
    //   1428: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1431: astore 9
    //   1433: goto -365 -> 1068
    //   1436: aload 14
    //   1438: ldc_w 1035
    //   1441: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1444: astore 10
    //   1446: goto -363 -> 1083
    //   1449: aload 14
    //   1451: ldc_w 1470
    //   1454: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1457: astore 11
    //   1459: goto -361 -> 1098
    //   1462: aload 8
    //   1464: aload_1
    //   1465: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1468: pop
    //   1469: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1472: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1475: new 1510	com/zing/zalo/control/cx
    //   1478: dup
    //   1479: aload_1
    //   1480: lconst_0
    //   1481: invokespecial 1520	com/zing/zalo/control/cx:<init>	(Ljava/lang/String;J)V
    //   1484: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1487: pop
    //   1488: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1491: iconst_1
    //   1492: iadd
    //   1493: putstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1496: new 241	java/util/ArrayList
    //   1499: dup
    //   1500: invokespecial 242	java/util/ArrayList:<init>	()V
    //   1503: astore 9
    //   1505: iconst_0
    //   1506: istore_3
    //   1507: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1510: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1513: invokevirtual 374	java/util/ArrayList:size	()I
    //   1516: istore 4
    //   1518: iload_3
    //   1519: iload 4
    //   1521: if_icmpge +63 -> 1584
    //   1524: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1527: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   1530: iload_3
    //   1531: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1534: checkcast 1510	com/zing/zalo/control/cx
    //   1537: astore 10
    //   1539: aload 9
    //   1541: new 114	java/lang/StringBuilder
    //   1544: dup
    //   1545: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   1548: aload 10
    //   1550: invokevirtual 1513	com/zing/zalo/control/cx:Fs	()Ljava/lang/String;
    //   1553: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1556: ldc_w 1104
    //   1559: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1562: aload 10
    //   1564: invokevirtual 1523	com/zing/zalo/control/cx:Ft	()J
    //   1567: invokevirtual 124	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1570: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1573: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1576: pop
    //   1577: iload_3
    //   1578: iconst_1
    //   1579: iadd
    //   1580: istore_3
    //   1581: goto -74 -> 1507
    //   1584: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1587: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   1590: invokestatic 1526	com/zing/zalo/f/c:Q	(Landroid/content/Context;I)V
    //   1593: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1596: ldc_w 1496
    //   1599: aload 9
    //   1601: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   1604: invokestatic 1533	com/zing/zalo/f/c:ae	(Landroid/content/Context;Ljava/lang/String;)V
    //   1607: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1610: ldc_w 1496
    //   1613: aload 8
    //   1615: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   1618: invokestatic 1536	com/zing/zalo/f/c:af	(Landroid/content/Context;Ljava/lang/String;)V
    //   1621: aload_1
    //   1622: invokestatic 1539	com/zing/zalo/utils/bm:mX	(Ljava/lang/String;)V
    //   1625: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1628: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   1631: ifnull +45 -> 1676
    //   1634: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1637: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   1640: invokeinterface 103 1 0
    //   1645: iconst_3
    //   1646: if_icmplt +163 -> 1809
    //   1649: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1652: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   1655: iconst_0
    //   1656: invokeinterface 1543 2 0
    //   1661: pop
    //   1662: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1665: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   1668: aload 15
    //   1670: invokeinterface 1544 2 0
    //   1675: pop
    //   1676: iconst_1
    //   1677: invokestatic 1549	com/zing/zalo/ui/bfs:ea	(Z)V
    //   1680: aload 14
    //   1682: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   1685: putfield 940	com/zing/zalo/control/bk:timestamp	J
    //   1688: aload 14
    //   1690: ldc_w 306
    //   1693: invokevirtual 1552	com/zing/zalo/control/bk:dY	(Ljava/lang/String;)V
    //   1696: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1699: aload 14
    //   1701: invokevirtual 1554	com/zing/zalo/h/a:o	(Lcom/zing/zalo/control/bk;)V
    //   1704: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1707: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   1710: ifnull +116 -> 1826
    //   1713: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1716: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   1719: aload_1
    //   1720: invokevirtual 1563	com/zing/zalo/control/ca:ed	(Ljava/lang/String;)Z
    //   1723: ifeq +103 -> 1826
    //   1726: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1729: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   1732: aload_1
    //   1733: invokevirtual 1566	com/zing/zalo/control/ca:ef	(Ljava/lang/String;)Lcom/zing/zalo/control/bk;
    //   1736: ldc_w 306
    //   1739: invokevirtual 1552	com/zing/zalo/control/bk:dY	(Ljava/lang/String;)V
    //   1742: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   1745: aload_1
    //   1746: ldc_w 306
    //   1749: invokevirtual 1569	com/zing/zalo/db/bj:at	(Ljava/lang/String;Ljava/lang/String;)I
    //   1752: pop
    //   1753: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   1756: aload_1
    //   1757: ldc_w 306
    //   1760: invokevirtual 1571	com/zing/zalo/d/v:X	(Ljava/lang/String;Ljava/lang/String;)V
    //   1763: invokestatic 1574	com/zing/zalo/h/n:PW	()V
    //   1766: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   1769: aload_1
    //   1770: iconst_1
    //   1771: invokeinterface 1576 3 0
    //   1776: pop
    //   1777: invokestatic 1579	com/zing/zalo/utils/bm:arb	()V
    //   1780: invokestatic 1582	com/zing/zalo/utils/bm:aqW	()V
    //   1783: invokestatic 1585	com/zing/zalo/utils/bm:arl	()V
    //   1786: aload_1
    //   1787: invokestatic 1588	com/zing/zalo/utils/bm:mP	(Ljava/lang/String;)V
    //   1790: invokestatic 1591	com/zing/zalo/utils/bm:arv	()V
    //   1793: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1796: aload_1
    //   1797: invokevirtual 1594	com/zing/zalo/h/a:ih	(Ljava/lang/String;)V
    //   1800: invokestatic 1600	com/zing/zalo/r/ag:auR	()Lcom/zing/zalo/r/ag;
    //   1803: invokevirtual 1603	com/zing/zalo/r/ag:auZ	()V
    //   1806: goto -1288 -> 518
    //   1809: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1812: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   1815: aload 15
    //   1817: invokeinterface 1544 2 0
    //   1822: pop
    //   1823: goto -147 -> 1676
    //   1826: aload_1
    //   1827: invokestatic 1078	com/zing/zalo/h/n:im	(Ljava/lang/String;)Z
    //   1830: ifne -88 -> 1742
    //   1833: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   1836: aload 14
    //   1838: iconst_1
    //   1839: invokevirtual 1606	com/zing/zalo/db/bj:c	(Lcom/zing/zalo/control/bk;Z)J
    //   1842: pop2
    //   1843: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   1846: aload 14
    //   1848: iconst_0
    //   1849: invokevirtual 1608	com/zing/zalo/d/v:a	(Lcom/zing/zalo/control/bk;Z)V
    //   1852: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1855: invokevirtual 1611	com/zing/zalo/h/a:PD	()V
    //   1858: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   1861: aload 14
    //   1863: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   1866: invokevirtual 1614	com/zing/zalo/db/bj:eX	(Ljava/lang/String;)I
    //   1869: pop
    //   1870: goto -128 -> 1742
    //   1873: iload 4
    //   1875: bipush 11
    //   1877: if_icmpne +514 -> 2391
    //   1880: aload 14
    //   1882: ldc_w 1442
    //   1885: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1888: ifeq +342 -> 2230
    //   1891: ldc 117
    //   1893: astore_1
    //   1894: aload 14
    //   1896: ldc_w 1035
    //   1899: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1902: ifeq +340 -> 2242
    //   1905: ldc 117
    //   1907: astore 8
    //   1909: aload 14
    //   1911: ldc_w 1472
    //   1914: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1917: ifne +3377 -> 5294
    //   1920: aload 14
    //   1922: ldc_w 1472
    //   1925: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1928: istore_3
    //   1929: aload 14
    //   1931: ldc_w 1470
    //   1934: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1937: ifeq +318 -> 2255
    //   1940: ldc 117
    //   1942: astore 9
    //   1944: aload 14
    //   1946: ldc_w 1464
    //   1949: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1952: ifeq +316 -> 2268
    //   1955: ldc 117
    //   1957: astore 10
    //   1959: aload 14
    //   1961: ldc_w 1466
    //   1964: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1967: ifne +3332 -> 5299
    //   1970: aload 14
    //   1972: ldc_w 1466
    //   1975: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1978: istore 4
    //   1980: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1983: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   1986: ifnull -1468 -> 518
    //   1989: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   1992: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   1995: aload_1
    //   1996: invokevirtual 1094	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   1999: ifne -1481 -> 518
    //   2002: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2005: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2008: aload_1
    //   2009: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2012: pop
    //   2013: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2016: iconst_1
    //   2017: iadd
    //   2018: putstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2021: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2024: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2027: invokestatic 1623	com/zing/zalo/f/c:v	(Landroid/content/Context;I)V
    //   2030: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2033: ldc_w 1496
    //   2036: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2039: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2042: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   2045: invokestatic 1626	com/zing/zalo/f/c:x	(Landroid/content/Context;Ljava/lang/String;)V
    //   2048: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2051: invokestatic 1629	com/zing/zalo/f/c:es	(Landroid/content/Context;)Ljava/lang/String;
    //   2054: astore 11
    //   2056: aload 11
    //   2058: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2061: ifne +220 -> 2281
    //   2064: aload 11
    //   2066: invokestatic 1633	com/zing/zalo/utils/bm:ne	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   2069: astore 11
    //   2071: aload 11
    //   2073: invokevirtual 374	java/util/ArrayList:size	()I
    //   2076: bipush 10
    //   2078: if_icmplt +10 -> 2088
    //   2081: aload 11
    //   2083: iconst_0
    //   2084: invokevirtual 1515	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   2087: pop
    //   2088: new 482	com/zing/zalo/control/bk
    //   2091: dup
    //   2092: aload_1
    //   2093: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   2096: astore 14
    //   2098: aload 14
    //   2100: aload 8
    //   2102: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   2105: aload 14
    //   2107: aload 9
    //   2109: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   2112: aload 14
    //   2114: iconst_0
    //   2115: putfield 1636	com/zing/zalo/control/bk:aSO	I
    //   2118: aload 14
    //   2120: aload 10
    //   2122: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   2125: iload 4
    //   2127: tableswitch	default:+3164->5291, 1101:+166->2293, 1102:+201->2328, 1103:+217->2344, 1104:+33->2160, 1105:+233->2360
    //   2160: aload 11
    //   2162: aload 14
    //   2164: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2167: pop
    //   2168: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2171: aload 11
    //   2173: invokestatic 1640	com/zing/zalo/utils/bm:aq	(Ljava/util/List;)Ljava/lang/String;
    //   2176: invokestatic 1643	com/zing/zalo/f/c:ag	(Landroid/content/Context;Ljava/lang/String;)V
    //   2179: invokestatic 1646	com/zing/zalo/utils/bm:aqX	()V
    //   2182: iload_3
    //   2183: iconst_1
    //   2184: if_icmpne -1666 -> 518
    //   2187: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2190: ldc_w 1647
    //   2193: invokevirtual 720	android/content/Context:getString	(I)Ljava/lang/String;
    //   2196: astore 9
    //   2198: invokestatic 916	com/zing/zalo/utils/bm:arp	()Ljava/lang/String;
    //   2201: astore 10
    //   2203: aload 10
    //   2205: ifnull +172 -> 2377
    //   2208: aload 10
    //   2210: ldc_w 1649
    //   2213: invokevirtual 1652	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   2216: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2219: ifeq +158 -> 2377
    //   2222: aload 9
    //   2224: invokestatic 1655	com/zing/zalo/utils/bm:mK	(Ljava/lang/String;)V
    //   2227: goto -1709 -> 518
    //   2230: aload 14
    //   2232: ldc_w 1442
    //   2235: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2238: astore_1
    //   2239: goto -345 -> 1894
    //   2242: aload 14
    //   2244: ldc_w 1035
    //   2247: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2250: astore 8
    //   2252: goto -343 -> 1909
    //   2255: aload 14
    //   2257: ldc_w 1470
    //   2260: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2263: astore 9
    //   2265: goto -321 -> 1944
    //   2268: aload 14
    //   2270: ldc_w 1464
    //   2273: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2276: astore 10
    //   2278: goto -319 -> 1959
    //   2281: new 241	java/util/ArrayList
    //   2284: dup
    //   2285: invokespecial 242	java/util/ArrayList:<init>	()V
    //   2288: astore 11
    //   2290: goto -219 -> 2071
    //   2293: aload 14
    //   2295: iconst_1
    //   2296: putfield 1658	com/zing/zalo/control/bk:src	I
    //   2299: aload 10
    //   2301: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2304: ifne -144 -> 2160
    //   2307: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2310: aload 10
    //   2312: invokestatic 1664	com/zing/zalo/db/cb:aJ	(Landroid/content/Context;Ljava/lang/String;)Lcom/zing/zalo/control/da;
    //   2315: ifnonnull -155 -> 2160
    //   2318: aload 14
    //   2320: ldc 117
    //   2322: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   2325: goto -165 -> 2160
    //   2328: aload 14
    //   2330: iconst_2
    //   2331: putfield 1658	com/zing/zalo/control/bk:src	I
    //   2334: aload 14
    //   2336: ldc 117
    //   2338: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   2341: goto -181 -> 2160
    //   2344: aload 14
    //   2346: iconst_3
    //   2347: putfield 1658	com/zing/zalo/control/bk:src	I
    //   2350: aload 14
    //   2352: ldc 117
    //   2354: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   2357: goto -197 -> 2160
    //   2360: aload 14
    //   2362: bipush 6
    //   2364: putfield 1658	com/zing/zalo/control/bk:src	I
    //   2367: aload 14
    //   2369: ldc 117
    //   2371: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   2374: goto -214 -> 2160
    //   2377: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   2380: aload_1
    //   2381: aload 9
    //   2383: aload 8
    //   2385: invokevirtual 1668	com/zing/zalo/d/o:t	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2388: goto -1870 -> 518
    //   2391: iload 4
    //   2393: bipush 12
    //   2395: if_icmpne +13 -> 2408
    //   2398: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2401: lconst_0
    //   2402: invokestatic 1672	com/zing/zalo/f/c:B	(Landroid/content/Context;J)V
    //   2405: goto -1887 -> 518
    //   2408: iload 4
    //   2410: bipush 13
    //   2412: if_icmpne +526 -> 2938
    //   2415: aload 14
    //   2417: ldc_w 1442
    //   2420: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2423: ifeq +438 -> 2861
    //   2426: ldc 117
    //   2428: astore 8
    //   2430: aload 14
    //   2432: ldc_w 1035
    //   2435: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2438: ifeq +436 -> 2874
    //   2441: ldc 117
    //   2443: astore 9
    //   2445: aload 14
    //   2447: ldc_w 1470
    //   2450: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2453: ifeq +434 -> 2887
    //   2456: ldc 117
    //   2458: astore 10
    //   2460: aload 14
    //   2462: ldc_w 1472
    //   2465: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2468: ifne +2839 -> 5307
    //   2471: aload 14
    //   2473: ldc_w 1472
    //   2476: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   2479: istore_3
    //   2480: aload 14
    //   2482: ldc_w 1674
    //   2485: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2488: ifne +2824 -> 5312
    //   2491: aload 14
    //   2493: ldc_w 1674
    //   2496: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   2499: istore 4
    //   2501: aload 14
    //   2503: ldc -23
    //   2505: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2508: ifne +2810 -> 5318
    //   2511: aload 14
    //   2513: ldc -23
    //   2515: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2518: astore_1
    //   2519: aload 14
    //   2521: ldc_w 1464
    //   2524: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2527: ifeq +373 -> 2900
    //   2530: aload_1
    //   2531: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2534: ifeq +2790 -> 5324
    //   2537: ldc 117
    //   2539: astore 11
    //   2541: new 1676	com/zing/zalo/control/cb
    //   2544: dup
    //   2545: aload 8
    //   2547: iload 4
    //   2549: aload 11
    //   2551: invokespecial 1679	com/zing/zalo/control/cb:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   2554: astore 11
    //   2556: invokestatic 1685	com/zing/zalo/h/l:PR	()Lcom/zing/zalo/h/l;
    //   2559: aload 11
    //   2561: invokevirtual 1688	com/zing/zalo/h/l:c	(Lcom/zing/zalo/control/cb;)V
    //   2564: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2567: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2570: ifnull -2052 -> 518
    //   2573: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2576: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2579: aload 8
    //   2581: invokevirtual 1094	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   2584: ifne -2066 -> 518
    //   2587: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2590: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2593: aload 8
    //   2595: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2598: pop
    //   2599: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2602: iconst_1
    //   2603: iadd
    //   2604: putstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2607: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2610: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   2613: invokestatic 1623	com/zing/zalo/f/c:v	(Landroid/content/Context;I)V
    //   2616: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2619: ldc_w 1496
    //   2622: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2625: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   2628: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   2631: invokestatic 1626	com/zing/zalo/f/c:x	(Landroid/content/Context;Ljava/lang/String;)V
    //   2634: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2637: invokestatic 1629	com/zing/zalo/f/c:es	(Landroid/content/Context;)Ljava/lang/String;
    //   2640: astore 11
    //   2642: aload 11
    //   2644: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2647: ifne +265 -> 2912
    //   2650: aload 11
    //   2652: invokestatic 1633	com/zing/zalo/utils/bm:ne	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   2655: astore 11
    //   2657: aload 11
    //   2659: invokevirtual 374	java/util/ArrayList:size	()I
    //   2662: bipush 10
    //   2664: if_icmplt +10 -> 2674
    //   2667: aload 11
    //   2669: iconst_0
    //   2670: invokevirtual 1515	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   2673: pop
    //   2674: new 482	com/zing/zalo/control/bk
    //   2677: dup
    //   2678: aload 8
    //   2680: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   2683: astore 14
    //   2685: aload 14
    //   2687: aload 9
    //   2689: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   2692: aload 14
    //   2694: aload 10
    //   2696: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   2699: aload 14
    //   2701: iconst_4
    //   2702: putfield 1658	com/zing/zalo/control/bk:src	I
    //   2705: aload 14
    //   2707: aload_1
    //   2708: invokevirtual 1691	com/zing/zalo/control/bk:dZ	(Ljava/lang/String;)V
    //   2711: aload 14
    //   2713: iconst_0
    //   2714: putfield 1636	com/zing/zalo/control/bk:aSO	I
    //   2717: aload 14
    //   2719: iload 4
    //   2721: putfield 1694	com/zing/zalo/control/bk:aSQ	I
    //   2724: aload 11
    //   2726: aload 14
    //   2728: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   2731: pop
    //   2732: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2735: aload 11
    //   2737: invokestatic 1640	com/zing/zalo/utils/bm:aq	(Ljava/util/List;)Ljava/lang/String;
    //   2740: invokestatic 1643	com/zing/zalo/f/c:ag	(Landroid/content/Context;Ljava/lang/String;)V
    //   2743: invokestatic 1646	com/zing/zalo/utils/bm:aqX	()V
    //   2746: invokestatic 1697	com/zing/zalo/utils/bm:aqY	()V
    //   2749: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2752: ldc_w 1698
    //   2755: invokevirtual 720	android/content/Context:getString	(I)Ljava/lang/String;
    //   2758: iconst_1
    //   2759: anewarray 4	java/lang/Object
    //   2762: dup
    //   2763: iconst_0
    //   2764: aload 10
    //   2766: aastore
    //   2767: invokestatic 730	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   2770: astore_1
    //   2771: iload_3
    //   2772: iconst_1
    //   2773: if_icmpne +31 -> 2804
    //   2776: invokestatic 916	com/zing/zalo/utils/bm:arp	()Ljava/lang/String;
    //   2779: astore 10
    //   2781: aload 10
    //   2783: ifnull +141 -> 2924
    //   2786: aload 10
    //   2788: ldc_w 1649
    //   2791: invokevirtual 1652	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   2794: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2797: ifeq +127 -> 2924
    //   2800: aload_1
    //   2801: invokestatic 1655	com/zing/zalo/utils/bm:mK	(Ljava/lang/String;)V
    //   2804: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   2807: ifnull -2289 -> 518
    //   2810: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   2813: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   2816: ifnull -2298 -> 518
    //   2819: aload 8
    //   2821: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   2824: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   2827: invokevirtual 1129	com/zing/zalo/control/c:Cc	()Lcom/zing/zalo/control/bk;
    //   2830: getfield 485	com/zing/zalo/control/bk:aOC	Ljava/lang/String;
    //   2833: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2836: ifeq -2318 -> 518
    //   2839: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   2842: invokevirtual 1702	com/zing/zalo/ui/fragments/ChatFragment:aiQ	()Lcom/zing/zalo/ui/fragments/ChatInfoFragment;
    //   2845: ifnull -2327 -> 518
    //   2848: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   2851: invokevirtual 1702	com/zing/zalo/ui/fragments/ChatFragment:aiQ	()Lcom/zing/zalo/ui/fragments/ChatInfoFragment;
    //   2854: iconst_0
    //   2855: invokevirtual 1706	com/zing/zalo/ui/fragments/ChatInfoFragment:eu	(Z)V
    //   2858: goto -2340 -> 518
    //   2861: aload 14
    //   2863: ldc_w 1442
    //   2866: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2869: astore 8
    //   2871: goto -441 -> 2430
    //   2874: aload 14
    //   2876: ldc_w 1035
    //   2879: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2882: astore 9
    //   2884: goto -439 -> 2445
    //   2887: aload 14
    //   2889: ldc_w 1470
    //   2892: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2895: astore 10
    //   2897: goto -437 -> 2460
    //   2900: aload 14
    //   2902: ldc_w 1464
    //   2905: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2908: pop
    //   2909: goto -379 -> 2530
    //   2912: new 241	java/util/ArrayList
    //   2915: dup
    //   2916: invokespecial 242	java/util/ArrayList:<init>	()V
    //   2919: astore 11
    //   2921: goto -264 -> 2657
    //   2924: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   2927: aload 8
    //   2929: aload_1
    //   2930: aload 9
    //   2932: invokevirtual 1709	com/zing/zalo/d/o:s	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2935: goto -131 -> 2804
    //   2938: iload 4
    //   2940: bipush 45
    //   2942: if_icmpne +352 -> 3294
    //   2945: aload 14
    //   2947: ldc_w 1442
    //   2950: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2953: ifeq +277 -> 3230
    //   2956: ldc 117
    //   2958: astore_1
    //   2959: aload 14
    //   2961: ldc_w 1035
    //   2964: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2967: ifeq +275 -> 3242
    //   2970: ldc 117
    //   2972: astore 8
    //   2974: aload 14
    //   2976: ldc_w 1470
    //   2979: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2982: ifeq +273 -> 3255
    //   2985: ldc 117
    //   2987: astore 9
    //   2989: aload 14
    //   2991: ldc_w 1472
    //   2994: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2997: ifne +2333 -> 5330
    //   3000: aload 14
    //   3002: ldc_w 1472
    //   3005: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   3008: istore_3
    //   3009: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3012: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   3015: ifnull -2497 -> 518
    //   3018: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3021: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   3024: aload_1
    //   3025: invokevirtual 1094	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   3028: ifne -2510 -> 518
    //   3031: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3034: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   3037: aload_1
    //   3038: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   3041: pop
    //   3042: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   3045: iconst_1
    //   3046: iadd
    //   3047: putstatic 1620	com/zing/zalo/f/a:aXA	I
    //   3050: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3053: getstatic 1620	com/zing/zalo/f/a:aXA	I
    //   3056: invokestatic 1623	com/zing/zalo/f/c:v	(Landroid/content/Context;I)V
    //   3059: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3062: ldc_w 1496
    //   3065: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3068: invokevirtual 1617	com/zing/zalo/h/a:PN	()Ljava/util/ArrayList;
    //   3071: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   3074: invokestatic 1626	com/zing/zalo/f/c:x	(Landroid/content/Context;Ljava/lang/String;)V
    //   3077: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3080: invokestatic 1629	com/zing/zalo/f/c:es	(Landroid/content/Context;)Ljava/lang/String;
    //   3083: astore 10
    //   3085: aload 10
    //   3087: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   3090: ifne +178 -> 3268
    //   3093: aload 10
    //   3095: invokestatic 1633	com/zing/zalo/utils/bm:ne	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   3098: astore 10
    //   3100: aload 10
    //   3102: invokevirtual 374	java/util/ArrayList:size	()I
    //   3105: bipush 10
    //   3107: if_icmplt +10 -> 3117
    //   3110: aload 10
    //   3112: iconst_0
    //   3113: invokevirtual 1515	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   3116: pop
    //   3117: new 482	com/zing/zalo/control/bk
    //   3120: dup
    //   3121: aload_1
    //   3122: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   3125: astore 11
    //   3127: aload 11
    //   3129: aload 8
    //   3131: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   3134: aload 11
    //   3136: aload 9
    //   3138: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   3141: aload 11
    //   3143: bipush 7
    //   3145: putfield 1658	com/zing/zalo/control/bk:src	I
    //   3148: aload 10
    //   3150: aload 11
    //   3152: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   3155: pop
    //   3156: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3159: aload 10
    //   3161: invokestatic 1640	com/zing/zalo/utils/bm:aq	(Ljava/util/List;)Ljava/lang/String;
    //   3164: invokestatic 1643	com/zing/zalo/f/c:ag	(Landroid/content/Context;Ljava/lang/String;)V
    //   3167: invokestatic 1646	com/zing/zalo/utils/bm:aqX	()V
    //   3170: iload_3
    //   3171: iconst_1
    //   3172: if_icmpne -2654 -> 518
    //   3175: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3178: ldc_w 1710
    //   3181: invokevirtual 720	android/content/Context:getString	(I)Ljava/lang/String;
    //   3184: iconst_1
    //   3185: anewarray 4	java/lang/Object
    //   3188: dup
    //   3189: iconst_0
    //   3190: aload 9
    //   3192: aastore
    //   3193: invokestatic 730	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   3196: astore 9
    //   3198: invokestatic 916	com/zing/zalo/utils/bm:arp	()Ljava/lang/String;
    //   3201: astore 10
    //   3203: aload 10
    //   3205: ifnull +75 -> 3280
    //   3208: aload 10
    //   3210: ldc_w 1649
    //   3213: invokevirtual 1652	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   3216: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3219: ifeq +61 -> 3280
    //   3222: aload 9
    //   3224: invokestatic 1655	com/zing/zalo/utils/bm:mK	(Ljava/lang/String;)V
    //   3227: goto -2709 -> 518
    //   3230: aload 14
    //   3232: ldc_w 1442
    //   3235: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3238: astore_1
    //   3239: goto -280 -> 2959
    //   3242: aload 14
    //   3244: ldc_w 1035
    //   3247: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3250: astore 8
    //   3252: goto -278 -> 2974
    //   3255: aload 14
    //   3257: ldc_w 1470
    //   3260: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3263: astore 9
    //   3265: goto -276 -> 2989
    //   3268: new 241	java/util/ArrayList
    //   3271: dup
    //   3272: invokespecial 242	java/util/ArrayList:<init>	()V
    //   3275: astore 10
    //   3277: goto -177 -> 3100
    //   3280: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   3283: aload_1
    //   3284: aload 9
    //   3286: aload 8
    //   3288: invokevirtual 1668	com/zing/zalo/d/o:t	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   3291: goto -2773 -> 518
    //   3294: iload 4
    //   3296: bipush 14
    //   3298: if_icmpne +318 -> 3616
    //   3301: aload 14
    //   3303: ldc_w 1442
    //   3306: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3309: ifeq +225 -> 3534
    //   3312: ldc 117
    //   3314: astore_1
    //   3315: invokestatic 1685	com/zing/zalo/h/l:PR	()Lcom/zing/zalo/h/l;
    //   3318: aload_1
    //   3319: invokevirtual 1713	com/zing/zalo/h/l:ft	(Ljava/lang/String;)V
    //   3322: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   3325: aload_1
    //   3326: ldc_w 1715
    //   3329: invokevirtual 1569	com/zing/zalo/db/bj:at	(Ljava/lang/String;Ljava/lang/String;)I
    //   3332: pop
    //   3333: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   3336: aload_1
    //   3337: ldc_w 1715
    //   3340: invokevirtual 1571	com/zing/zalo/d/v:X	(Ljava/lang/String;Ljava/lang/String;)V
    //   3343: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3346: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   3349: ifnull +197 -> 3546
    //   3352: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3355: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   3358: aload_1
    //   3359: invokevirtual 1563	com/zing/zalo/control/ca:ed	(Ljava/lang/String;)Z
    //   3362: ifeq +184 -> 3546
    //   3365: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3368: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   3371: aload_1
    //   3372: invokevirtual 1566	com/zing/zalo/control/ca:ef	(Ljava/lang/String;)Lcom/zing/zalo/control/bk;
    //   3375: ldc_w 1715
    //   3378: invokevirtual 1552	com/zing/zalo/control/bk:dY	(Ljava/lang/String;)V
    //   3381: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3384: aload_1
    //   3385: invokevirtual 1718	com/zing/zalo/h/a:hY	(Ljava/lang/String;)Z
    //   3388: pop
    //   3389: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   3392: aload_1
    //   3393: invokevirtual 1721	com/zing/zalo/db/bj:fr	(Ljava/lang/String;)Z
    //   3396: ifeq +33 -> 3429
    //   3399: new 241	java/util/ArrayList
    //   3402: dup
    //   3403: invokespecial 242	java/util/ArrayList:<init>	()V
    //   3406: astore 8
    //   3408: aload 8
    //   3410: aload_1
    //   3411: invokestatic 1726	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3414: invokestatic 1729	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   3417: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   3420: pop
    //   3421: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3424: aload 8
    //   3426: invokevirtual 1732	com/zing/zalo/h/a:y	(Ljava/util/ArrayList;)V
    //   3429: aload_1
    //   3430: ldc_w 1734
    //   3433: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3436: ifne +73 -> 3509
    //   3439: aload_1
    //   3440: invokestatic 1197	com/zing/zalo/h/n:io	(Ljava/lang/String;)Z
    //   3443: ifeq +66 -> 3509
    //   3446: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   3449: aload_1
    //   3450: invokeinterface 1737 2 0
    //   3455: ifne +54 -> 3509
    //   3458: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   3461: aload_1
    //   3462: iconst_2
    //   3463: invokeinterface 1576 3 0
    //   3468: pop
    //   3469: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   3472: iconst_2
    //   3473: invokeinterface 1741 2 0
    //   3478: astore 8
    //   3480: aload 8
    //   3482: invokeinterface 103 1 0
    //   3487: ifle +22 -> 3509
    //   3490: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   3493: aload 8
    //   3495: iconst_0
    //   3496: invokeinterface 107 2 0
    //   3501: checkcast 482	com/zing/zalo/control/bk
    //   3504: invokeinterface 1744 2 0
    //   3509: aload_1
    //   3510: invokestatic 1539	com/zing/zalo/utils/bm:mX	(Ljava/lang/String;)V
    //   3513: invokestatic 1574	com/zing/zalo/h/n:PW	()V
    //   3516: invokestatic 1747	com/zing/zalo/h/n:PX	()V
    //   3519: invokestatic 1579	com/zing/zalo/utils/bm:arb	()V
    //   3522: invokestatic 1582	com/zing/zalo/utils/bm:aqW	()V
    //   3525: invokestatic 1585	com/zing/zalo/utils/bm:arl	()V
    //   3528: invokestatic 1591	com/zing/zalo/utils/bm:arv	()V
    //   3531: goto -3013 -> 518
    //   3534: aload 14
    //   3536: ldc_w 1442
    //   3539: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3542: astore_1
    //   3543: goto -228 -> 3315
    //   3546: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3549: invokevirtual 1750	com/zing/zalo/h/a:PG	()Lcom/zing/zalo/control/ca;
    //   3552: ifnull -171 -> 3381
    //   3555: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3558: invokevirtual 1750	com/zing/zalo/h/a:PG	()Lcom/zing/zalo/control/ca;
    //   3561: aload_1
    //   3562: invokevirtual 1563	com/zing/zalo/control/ca:ed	(Ljava/lang/String;)Z
    //   3565: ifeq -184 -> 3381
    //   3568: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3571: invokevirtual 1750	com/zing/zalo/h/a:PG	()Lcom/zing/zalo/control/ca;
    //   3574: aload_1
    //   3575: invokevirtual 1566	com/zing/zalo/control/ca:ef	(Ljava/lang/String;)Lcom/zing/zalo/control/bk;
    //   3578: astore 8
    //   3580: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   3583: aload_1
    //   3584: iconst_0
    //   3585: invokevirtual 1753	com/zing/zalo/db/bj:H	(Ljava/lang/String;I)I
    //   3588: pop
    //   3589: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3592: aload_1
    //   3593: invokevirtual 1756	com/zing/zalo/h/a:ib	(Ljava/lang/String;)V
    //   3596: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   3599: aload_1
    //   3600: iconst_0
    //   3601: invokevirtual 1758	com/zing/zalo/d/v:x	(Ljava/lang/String;I)V
    //   3604: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   3607: aload 8
    //   3609: iconst_0
    //   3610: invokevirtual 1056	com/zing/zalo/db/bj:b	(Lcom/zing/zalo/control/bk;Z)V
    //   3613: goto -232 -> 3381
    //   3616: iload 4
    //   3618: bipush 15
    //   3620: if_icmpne +696 -> 4316
    //   3623: aload 14
    //   3625: ldc_w 1464
    //   3628: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3631: ifeq +356 -> 3987
    //   3634: ldc 117
    //   3636: astore 8
    //   3638: aload 14
    //   3640: ldc_w 1442
    //   3643: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3646: ifeq +354 -> 4000
    //   3649: ldc 117
    //   3651: astore_1
    //   3652: aload 14
    //   3654: ldc_w 1468
    //   3657: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3660: ifeq +352 -> 4012
    //   3663: ldc 117
    //   3665: astore 9
    //   3667: aload 14
    //   3669: ldc_w 1035
    //   3672: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3675: ifeq +350 -> 4025
    //   3678: ldc 117
    //   3680: astore 10
    //   3682: aload 14
    //   3684: ldc_w 1470
    //   3687: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3690: ifeq +348 -> 4038
    //   3693: ldc 117
    //   3695: astore 11
    //   3697: aload 14
    //   3699: ldc_w 1472
    //   3702: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   3705: ifne +1637 -> 5342
    //   3708: aload 14
    //   3710: ldc_w 1472
    //   3713: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   3716: istore_3
    //   3717: new 482	com/zing/zalo/control/bk
    //   3720: dup
    //   3721: aload_1
    //   3722: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   3725: astore 14
    //   3727: aload 14
    //   3729: aload 10
    //   3731: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   3734: aload 14
    //   3736: aload 9
    //   3738: putfield 1475	com/zing/zalo/control/bk:aRY	Ljava/lang/String;
    //   3741: aload 14
    //   3743: aload 11
    //   3745: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   3748: aload 14
    //   3750: aload 8
    //   3752: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   3755: aload 14
    //   3757: sipush 64535
    //   3760: putfield 1484	com/zing/zalo/control/bk:aSB	I
    //   3763: aload 14
    //   3765: iconst_1
    //   3766: putfield 1481	com/zing/zalo/control/bk:aSS	I
    //   3769: new 482	com/zing/zalo/control/bk
    //   3772: dup
    //   3773: aload_1
    //   3774: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   3777: astore 14
    //   3779: aload 14
    //   3781: aload 10
    //   3783: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   3786: aload 14
    //   3788: aload 9
    //   3790: putfield 1475	com/zing/zalo/control/bk:aRY	Ljava/lang/String;
    //   3793: aload 14
    //   3795: aload 11
    //   3797: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   3800: aload 14
    //   3802: aload 8
    //   3804: putfield 1478	com/zing/zalo/control/bk:aSc	Ljava/lang/String;
    //   3807: aload 14
    //   3809: sipush 64535
    //   3812: putfield 1484	com/zing/zalo/control/bk:aSB	I
    //   3815: aload 14
    //   3817: iconst_1
    //   3818: putfield 1481	com/zing/zalo/control/bk:aSS	I
    //   3821: aload 14
    //   3823: iload_3
    //   3824: putfield 1485	com/zing/zalo/control/bk:aPb	I
    //   3827: aload_1
    //   3828: invokestatic 1488	com/zing/zalo/h/n:il	(Ljava/lang/String;)Z
    //   3831: ifne +438 -> 4269
    //   3834: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3837: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   3840: invokevirtual 374	java/util/ArrayList:size	()I
    //   3843: iconst_5
    //   3844: isub
    //   3845: istore 4
    //   3847: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3850: invokestatic 1494	com/zing/zalo/f/c:er	(Landroid/content/Context;)Ljava/lang/String;
    //   3853: astore 9
    //   3855: new 241	java/util/ArrayList
    //   3858: dup
    //   3859: invokespecial 242	java/util/ArrayList:<init>	()V
    //   3862: astore 8
    //   3864: aload 9
    //   3866: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   3869: ifne +1396 -> 5265
    //   3872: new 241	java/util/ArrayList
    //   3875: dup
    //   3876: aload 9
    //   3878: ldc_w 1496
    //   3881: invokestatic 1499	android/text/TextUtils:split	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
    //   3884: invokestatic 1505	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   3887: invokespecial 1508	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   3890: astore 8
    //   3892: iload 4
    //   3894: ifle +157 -> 4051
    //   3897: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3900: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   3903: invokevirtual 374	java/util/ArrayList:size	()I
    //   3906: iconst_1
    //   3907: isub
    //   3908: istore_3
    //   3909: iload_3
    //   3910: iflt +141 -> 4051
    //   3913: iload_3
    //   3914: iflt +1421 -> 5335
    //   3917: iload_3
    //   3918: iload 4
    //   3920: if_icmpge +1415 -> 5335
    //   3923: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3926: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   3929: iload_3
    //   3930: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   3933: checkcast 1510	com/zing/zalo/control/cx
    //   3936: invokevirtual 1513	com/zing/zalo/control/cx:Fs	()Ljava/lang/String;
    //   3939: astore 9
    //   3941: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   3944: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   3947: iload_3
    //   3948: invokevirtual 1515	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   3951: pop
    //   3952: aload 8
    //   3954: aload 9
    //   3956: invokevirtual 1094	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   3959: ifeq +1376 -> 5335
    //   3962: aload 8
    //   3964: aload 9
    //   3966: invokevirtual 347	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   3969: pop
    //   3970: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   3973: ifle +1362 -> 5335
    //   3976: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   3979: iconst_1
    //   3980: isub
    //   3981: putstatic 1518	com/zing/zalo/f/a:aXy	I
    //   3984: goto +1351 -> 5335
    //   3987: aload 14
    //   3989: ldc_w 1464
    //   3992: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3995: astore 8
    //   3997: goto -359 -> 3638
    //   4000: aload 14
    //   4002: ldc_w 1442
    //   4005: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4008: astore_1
    //   4009: goto -357 -> 3652
    //   4012: aload 14
    //   4014: ldc_w 1468
    //   4017: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4020: astore 9
    //   4022: goto -355 -> 3667
    //   4025: aload 14
    //   4027: ldc_w 1035
    //   4030: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4033: astore 10
    //   4035: goto -353 -> 3682
    //   4038: aload 14
    //   4040: ldc_w 1470
    //   4043: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4046: astore 11
    //   4048: goto -351 -> 3697
    //   4051: aload 8
    //   4053: aload_1
    //   4054: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   4057: pop
    //   4058: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4061: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   4064: new 1510	com/zing/zalo/control/cx
    //   4067: dup
    //   4068: aload_1
    //   4069: lconst_0
    //   4070: invokespecial 1520	com/zing/zalo/control/cx:<init>	(Ljava/lang/String;J)V
    //   4073: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   4076: pop
    //   4077: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   4080: iconst_1
    //   4081: iadd
    //   4082: putstatic 1518	com/zing/zalo/f/a:aXy	I
    //   4085: new 241	java/util/ArrayList
    //   4088: dup
    //   4089: invokespecial 242	java/util/ArrayList:<init>	()V
    //   4092: astore 9
    //   4094: iconst_0
    //   4095: istore_3
    //   4096: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4099: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   4102: invokevirtual 374	java/util/ArrayList:size	()I
    //   4105: istore 4
    //   4107: iload_3
    //   4108: iload 4
    //   4110: if_icmpge +63 -> 4173
    //   4113: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4116: invokevirtual 1491	com/zing/zalo/h/a:PM	()Ljava/util/ArrayList;
    //   4119: iload_3
    //   4120: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   4123: checkcast 1510	com/zing/zalo/control/cx
    //   4126: astore 10
    //   4128: aload 9
    //   4130: new 114	java/lang/StringBuilder
    //   4133: dup
    //   4134: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   4137: aload 10
    //   4139: invokevirtual 1513	com/zing/zalo/control/cx:Fs	()Ljava/lang/String;
    //   4142: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4145: ldc_w 1104
    //   4148: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4151: aload 10
    //   4153: invokevirtual 1523	com/zing/zalo/control/cx:Ft	()J
    //   4156: invokevirtual 124	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   4159: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4162: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   4165: pop
    //   4166: iload_3
    //   4167: iconst_1
    //   4168: iadd
    //   4169: istore_3
    //   4170: goto -74 -> 4096
    //   4173: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4176: getstatic 1518	com/zing/zalo/f/a:aXy	I
    //   4179: invokestatic 1526	com/zing/zalo/f/c:Q	(Landroid/content/Context;I)V
    //   4182: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4185: ldc_w 1496
    //   4188: aload 9
    //   4190: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   4193: invokestatic 1533	com/zing/zalo/f/c:ae	(Landroid/content/Context;Ljava/lang/String;)V
    //   4196: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4199: ldc_w 1496
    //   4202: aload 8
    //   4204: invokestatic 1530	android/text/TextUtils:join	(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
    //   4207: invokestatic 1536	com/zing/zalo/f/c:af	(Landroid/content/Context;Ljava/lang/String;)V
    //   4210: aload_1
    //   4211: invokestatic 1539	com/zing/zalo/utils/bm:mX	(Ljava/lang/String;)V
    //   4214: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4217: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   4220: ifnull +45 -> 4265
    //   4223: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4226: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   4229: invokeinterface 103 1 0
    //   4234: iconst_3
    //   4235: if_icmplt +64 -> 4299
    //   4238: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4241: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   4244: iconst_0
    //   4245: invokeinterface 1543 2 0
    //   4250: pop
    //   4251: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4254: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   4257: aload 14
    //   4259: invokeinterface 1544 2 0
    //   4264: pop
    //   4265: iconst_1
    //   4266: invokestatic 1549	com/zing/zalo/ui/bfs:ea	(Z)V
    //   4269: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4272: aload_1
    //   4273: invokevirtual 1761	com/zing/zalo/h/a:hX	(Ljava/lang/String;)V
    //   4276: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   4279: aload_1
    //   4280: iconst_1
    //   4281: invokevirtual 1764	com/zing/zalo/d/v:w	(Ljava/lang/String;I)V
    //   4284: invokestatic 1574	com/zing/zalo/h/n:PW	()V
    //   4287: invokestatic 1582	com/zing/zalo/utils/bm:aqW	()V
    //   4290: invokestatic 1600	com/zing/zalo/r/ag:auR	()Lcom/zing/zalo/r/ag;
    //   4293: invokevirtual 1603	com/zing/zalo/r/ag:auZ	()V
    //   4296: goto -3778 -> 518
    //   4299: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4302: invokevirtual 1542	com/zing/zalo/h/a:PL	()Ljava/util/List;
    //   4305: aload 14
    //   4307: invokeinterface 1544 2 0
    //   4312: pop
    //   4313: goto -48 -> 4265
    //   4316: iload 4
    //   4318: bipush 16
    //   4320: if_icmpne +54 -> 4374
    //   4323: aload 14
    //   4325: ldc_w 1442
    //   4328: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4331: ifeq +31 -> 4362
    //   4334: ldc 117
    //   4336: astore_1
    //   4337: aload_1
    //   4338: invokestatic 1539	com/zing/zalo/utils/bm:mX	(Ljava/lang/String;)V
    //   4341: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4344: aload_1
    //   4345: invokevirtual 1761	com/zing/zalo/h/a:hX	(Ljava/lang/String;)V
    //   4348: invokestatic 749	com/zing/zalo/d/v:yc	()Lcom/zing/zalo/d/v;
    //   4351: aload_1
    //   4352: iconst_1
    //   4353: invokevirtual 1764	com/zing/zalo/d/v:w	(Ljava/lang/String;I)V
    //   4356: invokestatic 1574	com/zing/zalo/h/n:PW	()V
    //   4359: goto -3841 -> 518
    //   4362: aload 14
    //   4364: ldc_w 1442
    //   4367: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4370: astore_1
    //   4371: goto -34 -> 4337
    //   4374: iload 4
    //   4376: bipush 43
    //   4378: if_icmpne +16 -> 4394
    //   4381: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4384: iconst_1
    //   4385: invokestatic 1768	com/zing/zalo/f/c:W	(Landroid/content/Context;Z)V
    //   4388: invokestatic 1771	com/zing/zalo/utils/bm:aqZ	()V
    //   4391: goto -3873 -> 518
    //   4394: iload 4
    //   4396: bipush 44
    //   4398: if_icmpeq -3880 -> 518
    //   4401: iload 4
    //   4403: bipush 46
    //   4405: if_icmpne +99 -> 4504
    //   4408: aload 14
    //   4410: ldc_w 1773
    //   4413: invokevirtual 1317	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   4416: istore_3
    //   4417: aload 14
    //   4419: ldc_w 1775
    //   4422: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4425: ifne +73 -> 4498
    //   4428: aload 14
    //   4430: ldc_w 1775
    //   4433: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4436: astore_1
    //   4437: aload_0
    //   4438: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   4441: new 114	java/lang/StringBuilder
    //   4444: dup
    //   4445: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   4448: ldc_w 1777
    //   4451: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4454: iload_3
    //   4455: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   4458: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4461: invokestatic 525	com/zing/a/f/e:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4464: pop
    //   4465: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4468: iload_3
    //   4469: invokestatic 1780	com/zing/zalo/f/c:Y	(Landroid/content/Context;I)V
    //   4472: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   4475: aload_1
    //   4476: invokestatic 1783	com/zing/zalo/f/c:ar	(Landroid/content/Context;Ljava/lang/String;)V
    //   4479: invokestatic 1786	com/zing/zalo/utils/bm:ara	()V
    //   4482: goto -3964 -> 518
    //   4485: astore_1
    //   4486: aload_0
    //   4487: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   4490: aload_1
    //   4491: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   4494: pop
    //   4495: goto -3977 -> 518
    //   4498: ldc 117
    //   4500: astore_1
    //   4501: goto -64 -> 4437
    //   4504: iload 4
    //   4506: bipush 47
    //   4508: if_icmpne +354 -> 4862
    //   4511: aload 14
    //   4513: ldc_w 1442
    //   4516: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4519: ifeq +228 -> 4747
    //   4522: ldc 117
    //   4524: astore_1
    //   4525: aload 14
    //   4527: ldc_w 1788
    //   4530: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4533: ifeq +226 -> 4759
    //   4536: iconst_0
    //   4537: istore 6
    //   4539: aload 14
    //   4541: ldc_w 1035
    //   4544: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4547: ifeq +225 -> 4772
    //   4550: ldc 117
    //   4552: astore 8
    //   4554: aload 14
    //   4556: ldc_w 1790
    //   4559: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4562: ifeq +223 -> 4785
    //   4565: ldc 117
    //   4567: astore 9
    //   4569: aload 14
    //   4571: ldc_w 1792
    //   4574: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4577: ifeq +221 -> 4798
    //   4580: iconst_0
    //   4581: istore_3
    //   4582: aload 14
    //   4584: ldc_w 1470
    //   4587: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4590: ifeq +220 -> 4810
    //   4593: ldc 117
    //   4595: astore 10
    //   4597: aload 14
    //   4599: ldc_w 1794
    //   4602: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4605: ifeq +218 -> 4823
    //   4608: iconst_0
    //   4609: istore 7
    //   4611: aload 14
    //   4613: ldc_w 262
    //   4616: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4619: ifeq +217 -> 4836
    //   4622: iconst_0
    //   4623: istore 4
    //   4625: aload 14
    //   4627: ldc_w 1796
    //   4630: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   4633: ifeq +216 -> 4849
    //   4636: iconst_1
    //   4637: istore 5
    //   4639: new 482	com/zing/zalo/control/bk
    //   4642: dup
    //   4643: aload_1
    //   4644: invokespecial 743	com/zing/zalo/control/bk:<init>	(Ljava/lang/String;)V
    //   4647: astore 11
    //   4649: aload 11
    //   4651: iload 6
    //   4653: putfield 1799	com/zing/zalo/control/bk:aSI	Z
    //   4656: aload 11
    //   4658: aload 8
    //   4660: putfield 595	com/zing/zalo/control/bk:aRZ	Ljava/lang/String;
    //   4663: aload 11
    //   4665: aload 9
    //   4667: putfield 1802	com/zing/zalo/control/bk:aSd	Ljava/lang/String;
    //   4670: aload 11
    //   4672: iload_3
    //   4673: putfield 1805	com/zing/zalo/control/bk:aSH	I
    //   4676: aload 11
    //   4678: aload 10
    //   4680: putfield 769	com/zing/zalo/control/bk:aRW	Ljava/lang/String;
    //   4683: aload 11
    //   4685: iload 7
    //   4687: putfield 1808	com/zing/zalo/control/bk:aSJ	Z
    //   4690: aload 11
    //   4692: iload 4
    //   4694: putfield 1811	com/zing/zalo/control/bk:aSK	I
    //   4697: aload 11
    //   4699: iload 5
    //   4701: putfield 1814	com/zing/zalo/control/bk:aSM	I
    //   4704: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4707: invokevirtual 1817	com/zing/zalo/h/a:PH	()Lcom/zing/zalo/control/ca;
    //   4710: aload_1
    //   4711: invokevirtual 1563	com/zing/zalo/control/ca:ed	(Ljava/lang/String;)Z
    //   4714: ifeq +10 -> 4724
    //   4717: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4720: aload_1
    //   4721: invokevirtual 1820	com/zing/zalo/h/a:fA	(Ljava/lang/String;)V
    //   4724: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   4727: invokevirtual 1817	com/zing/zalo/h/a:PH	()Lcom/zing/zalo/control/ca;
    //   4730: aload 11
    //   4732: invokevirtual 1824	com/zing/zalo/control/ca:e	(Lcom/zing/zalo/control/bk;)Z
    //   4735: pop
    //   4736: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   4739: aload 11
    //   4741: invokevirtual 1827	com/zing/zalo/db/bj:m	(Lcom/zing/zalo/control/bk;)V
    //   4744: goto -4226 -> 518
    //   4747: aload 14
    //   4749: ldc_w 1442
    //   4752: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4755: astore_1
    //   4756: goto -231 -> 4525
    //   4759: aload 14
    //   4761: ldc_w 1788
    //   4764: invokevirtual 1830	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   4767: istore 6
    //   4769: goto -230 -> 4539
    //   4772: aload 14
    //   4774: ldc_w 1035
    //   4777: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4780: astore 8
    //   4782: goto -228 -> 4554
    //   4785: aload 14
    //   4787: ldc_w 1790
    //   4790: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4793: astore 9
    //   4795: goto -226 -> 4569
    //   4798: aload 14
    //   4800: ldc_w 1792
    //   4803: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   4806: istore_3
    //   4807: goto -225 -> 4582
    //   4810: aload 14
    //   4812: ldc_w 1470
    //   4815: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   4818: astore 10
    //   4820: goto -223 -> 4597
    //   4823: aload 14
    //   4825: ldc_w 1794
    //   4828: invokevirtual 1830	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   4831: istore 7
    //   4833: goto -222 -> 4611
    //   4836: aload 14
    //   4838: ldc_w 262
    //   4841: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   4844: istore 4
    //   4846: goto -221 -> 4625
    //   4849: aload 14
    //   4851: ldc_w 1796
    //   4854: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   4857: istore 5
    //   4859: goto -220 -> 4639
    //   4862: iload 4
    //   4864: bipush 48
    //   4866: if_icmpne +197 -> 5063
    //   4869: aload 14
    //   4871: ldc_w 1832
    //   4874: invokevirtual 1317	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   4877: istore_3
    //   4878: aload 14
    //   4880: ldc_w 1834
    //   4883: invokevirtual 1317	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   4886: istore 4
    //   4888: aload 14
    //   4890: ldc_w 1836
    //   4893: invokestatic 1251	com/zing/zalo/utils/bm:d	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   4896: astore 8
    //   4898: aload 8
    //   4900: astore_1
    //   4901: aload 8
    //   4903: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4906: ifne +34 -> 4940
    //   4909: aload 8
    //   4911: astore_1
    //   4912: iload 4
    //   4914: iconst_2
    //   4915: if_icmpne +25 -> 4940
    //   4918: new 114	java/lang/StringBuilder
    //   4921: dup
    //   4922: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   4925: ldc_w 1115
    //   4928: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4931: aload 8
    //   4933: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4936: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4939: astore_1
    //   4940: iload 4
    //   4942: ifle -4424 -> 518
    //   4945: aload_1
    //   4946: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4949: ifne -4431 -> 518
    //   4952: iload_3
    //   4953: ifne +43 -> 4996
    //   4956: invokestatic 1842	com/zing/zalo/r/a:auk	()Lcom/zing/zalo/r/a;
    //   4959: aload_1
    //   4960: invokevirtual 1845	com/zing/zalo/r/a:fs	(Ljava/lang/String;)V
    //   4963: new 571	android/os/Handler
    //   4966: dup
    //   4967: invokestatic 577	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   4970: invokespecial 580	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   4973: new 1847	com/zing/zalo/e/u
    //   4976: dup
    //   4977: aload_0
    //   4978: invokespecial 1850	com/zing/zalo/e/u:<init>	(Lcom/zing/zalo/e/b;)V
    //   4981: invokevirtual 1854	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   4984: pop
    //   4985: goto -4467 -> 518
    //   4988: astore_1
    //   4989: aload_1
    //   4990: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   4993: goto -4475 -> 518
    //   4996: new 1856	com/zing/zalo/control/d
    //   4999: dup
    //   5000: invokespecial 1857	com/zing/zalo/control/d:<init>	()V
    //   5003: astore 8
    //   5005: aload 8
    //   5007: iload 4
    //   5009: invokevirtual 1860	com/zing/zalo/control/d:fa	(I)V
    //   5012: aload 8
    //   5014: aload_1
    //   5015: invokevirtual 1863	com/zing/zalo/control/d:dL	(Ljava/lang/String;)V
    //   5018: aload 8
    //   5020: aload 14
    //   5022: ldc_w 1865
    //   5025: invokestatic 1868	com/zing/zalo/utils/bm:f	(Lorg/json/JSONObject;Ljava/lang/String;)J
    //   5028: ldc2_w 1869
    //   5031: lmul
    //   5032: invokevirtual 1873	com/zing/zalo/control/d:O	(J)V
    //   5035: aload 8
    //   5037: aload 14
    //   5039: ldc_w 1875
    //   5042: invokestatic 1868	com/zing/zalo/utils/bm:f	(Lorg/json/JSONObject;Ljava/lang/String;)J
    //   5045: ldc2_w 1869
    //   5048: lmul
    //   5049: invokevirtual 1878	com/zing/zalo/control/d:P	(J)V
    //   5052: invokestatic 1842	com/zing/zalo/r/a:auk	()Lcom/zing/zalo/r/a;
    //   5055: aload 8
    //   5057: invokevirtual 1881	com/zing/zalo/r/a:h	(Lcom/zing/zalo/control/d;)V
    //   5060: goto -97 -> 4963
    //   5063: iload 4
    //   5065: bipush 49
    //   5067: if_icmpne -4549 -> 518
    //   5070: aload_0
    //   5071: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   5074: new 114	java/lang/StringBuilder
    //   5077: dup
    //   5078: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   5081: ldc_w 1883
    //   5084: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5087: aload_1
    //   5088: invokevirtual 1324	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   5091: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5094: invokestatic 525	com/zing/a/f/e:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5097: pop
    //   5098: new 241	java/util/ArrayList
    //   5101: dup
    //   5102: invokespecial 242	java/util/ArrayList:<init>	()V
    //   5105: astore_1
    //   5106: aload 14
    //   5108: ldc_w 1885
    //   5111: invokevirtual 246	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   5114: astore 8
    //   5116: iconst_0
    //   5117: istore_3
    //   5118: aload 8
    //   5120: invokevirtual 251	org/json/JSONArray:length	()I
    //   5123: istore 4
    //   5125: iload_3
    //   5126: iload 4
    //   5128: if_icmpge +52 -> 5180
    //   5131: aload 8
    //   5133: iload_3
    //   5134: invokevirtual 255	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   5137: astore 9
    //   5139: aload 9
    //   5141: ldc_w 1464
    //   5144: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   5147: astore 10
    //   5149: aload_1
    //   5150: new 1887	android/support/v4/f/m
    //   5153: dup
    //   5154: aload 9
    //   5156: ldc_w 1442
    //   5159: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   5162: aload 10
    //   5164: invokespecial 1890	android/support/v4/f/m:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   5167: invokeinterface 1544 2 0
    //   5172: pop
    //   5173: iload_3
    //   5174: iconst_1
    //   5175: iadd
    //   5176: istore_3
    //   5177: goto -59 -> 5118
    //   5180: aload_1
    //   5181: invokeinterface 1891 1 0
    //   5186: ifne -4668 -> 518
    //   5189: aload_1
    //   5190: invokestatic 1897	com/zing/zalo/r/u:as	(Ljava/util/List;)V
    //   5193: goto -4675 -> 518
    //   5196: astore_1
    //   5197: aload_0
    //   5198: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   5201: aload_1
    //   5202: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   5205: pop
    //   5206: goto -4688 -> 518
    //   5209: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   5212: aload 13
    //   5214: invokevirtual 1899	com/zing/zalo/e/a/a:p	(Ljava/util/ArrayList;)V
    //   5217: aload 13
    //   5219: ifnull +19 -> 5238
    //   5222: aload 13
    //   5224: invokevirtual 374	java/util/ArrayList:size	()I
    //   5227: bipush 48
    //   5229: if_icmple +9 -> 5238
    //   5232: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   5235: invokevirtual 1902	com/zing/zalo/e/a/a:Bi	()V
    //   5238: return
    //   5239: astore_1
    //   5240: aload_0
    //   5241: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   5244: aload_1
    //   5245: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   5248: pop
    //   5249: return
    //   5250: astore 9
    //   5252: goto -79 -> 5173
    //   5255: astore 10
    //   5257: goto -1091 -> 4166
    //   5260: astore 10
    //   5262: goto -3685 -> 1577
    //   5265: goto -1373 -> 3892
    //   5268: goto -3977 -> 1291
    //   5271: ldc_w 1904
    //   5274: astore_1
    //   5275: goto -4920 -> 355
    //   5278: iload_3
    //   5279: iconst_1
    //   5280: isub
    //   5281: istore_3
    //   5282: goto -3974 -> 1308
    //   5285: iconst_1
    //   5286: istore 4
    //   5288: goto -4169 -> 1119
    //   5291: goto -3131 -> 2160
    //   5294: iconst_0
    //   5295: istore_3
    //   5296: goto -3367 -> 1929
    //   5299: sipush 1100
    //   5302: istore 4
    //   5304: goto -3324 -> 1980
    //   5307: iconst_1
    //   5308: istore_3
    //   5309: goto -2829 -> 2480
    //   5312: iconst_m1
    //   5313: istore 4
    //   5315: goto -2814 -> 2501
    //   5318: ldc 117
    //   5320: astore_1
    //   5321: goto -2802 -> 2519
    //   5324: aload_1
    //   5325: astore 11
    //   5327: goto -2786 -> 2541
    //   5330: iconst_0
    //   5331: istore_3
    //   5332: goto -2323 -> 3009
    //   5335: iload_3
    //   5336: iconst_1
    //   5337: isub
    //   5338: istore_3
    //   5339: goto -1430 -> 3909
    //   5342: iconst_1
    //   5343: istore_3
    //   5344: goto -1627 -> 3717
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	5347	0	this	b
    //   0	5347	1	paramString	String
    //   20	502	2	i	int
    //   26	5318	3	j	int
    //   70	5244	4	k	int
    //   223	4635	5	m	int
    //   4537	231	6	bool1	boolean
    //   4609	223	7	bool2	boolean
    //   101	5031	8	localObject1	Object
    //   109	5046	9	localObject2	Object
    //   5250	1	9	localException1	Exception
    //   253	4910	10	localObject3	Object
    //   5255	1	10	localException2	Exception
    //   5260	1	10	localException3	Exception
    //   193	5133	11	localObject4	Object
    //   8	25	12	localJSONArray	JSONArray
    //   17	5206	13	localArrayList	ArrayList
    //   79	5028	14	localObject5	Object
    //   216	1600	15	localObject6	Object
    //   432	13	16	str	String
    // Exception table:
    //   from	to	target	type
    //   240	255	492	java/lang/Exception
    //   263	269	492	java/lang/Exception
    //   32	92	508	java/lang/Exception
    //   94	111	508	java/lang/Exception
    //   155	236	508	java/lang/Exception
    //   269	275	508	java/lang/Exception
    //   275	295	508	java/lang/Exception
    //   295	306	508	java/lang/Exception
    //   493	502	508	java/lang/Exception
    //   578	605	508	java/lang/Exception
    //   626	641	508	java/lang/Exception
    //   650	670	508	java/lang/Exception
    //   674	691	508	java/lang/Exception
    //   695	731	508	java/lang/Exception
    //   731	750	508	java/lang/Exception
    //   750	814	508	java/lang/Exception
    //   824	987	508	java/lang/Exception
    //   987	999	508	java/lang/Exception
    //   1009	1020	508	java/lang/Exception
    //   1024	1035	508	java/lang/Exception
    //   1039	1050	508	java/lang/Exception
    //   1053	1064	508	java/lang/Exception
    //   1068	1079	508	java/lang/Exception
    //   1083	1094	508	java/lang/Exception
    //   1098	1119	508	java/lang/Exception
    //   1119	1291	508	java/lang/Exception
    //   1296	1308	508	java/lang/Exception
    //   1322	1383	508	java/lang/Exception
    //   1386	1396	508	java/lang/Exception
    //   1399	1408	508	java/lang/Exception
    //   1411	1420	508	java/lang/Exception
    //   1423	1433	508	java/lang/Exception
    //   1436	1446	508	java/lang/Exception
    //   1449	1459	508	java/lang/Exception
    //   1462	1505	508	java/lang/Exception
    //   1507	1518	508	java/lang/Exception
    //   1584	1676	508	java/lang/Exception
    //   1676	1680	508	java/lang/Exception
    //   1680	1742	508	java/lang/Exception
    //   1742	1806	508	java/lang/Exception
    //   1809	1823	508	java/lang/Exception
    //   1826	1870	508	java/lang/Exception
    //   1880	1891	508	java/lang/Exception
    //   1894	1905	508	java/lang/Exception
    //   1909	1929	508	java/lang/Exception
    //   1929	1940	508	java/lang/Exception
    //   1944	1955	508	java/lang/Exception
    //   1959	1980	508	java/lang/Exception
    //   1980	2071	508	java/lang/Exception
    //   2071	2088	508	java/lang/Exception
    //   2088	2125	508	java/lang/Exception
    //   2160	2182	508	java/lang/Exception
    //   2187	2203	508	java/lang/Exception
    //   2208	2227	508	java/lang/Exception
    //   2230	2239	508	java/lang/Exception
    //   2242	2252	508	java/lang/Exception
    //   2255	2265	508	java/lang/Exception
    //   2268	2278	508	java/lang/Exception
    //   2281	2290	508	java/lang/Exception
    //   2293	2325	508	java/lang/Exception
    //   2328	2341	508	java/lang/Exception
    //   2344	2357	508	java/lang/Exception
    //   2360	2374	508	java/lang/Exception
    //   2377	2388	508	java/lang/Exception
    //   2398	2405	508	java/lang/Exception
    //   2415	2426	508	java/lang/Exception
    //   2430	2441	508	java/lang/Exception
    //   2445	2456	508	java/lang/Exception
    //   2460	2480	508	java/lang/Exception
    //   2480	2501	508	java/lang/Exception
    //   2501	2519	508	java/lang/Exception
    //   2519	2530	508	java/lang/Exception
    //   2530	2537	508	java/lang/Exception
    //   2541	2657	508	java/lang/Exception
    //   2657	2674	508	java/lang/Exception
    //   2674	2771	508	java/lang/Exception
    //   2776	2781	508	java/lang/Exception
    //   2786	2804	508	java/lang/Exception
    //   2804	2858	508	java/lang/Exception
    //   2861	2871	508	java/lang/Exception
    //   2874	2884	508	java/lang/Exception
    //   2887	2897	508	java/lang/Exception
    //   2900	2909	508	java/lang/Exception
    //   2912	2921	508	java/lang/Exception
    //   2924	2935	508	java/lang/Exception
    //   2945	2956	508	java/lang/Exception
    //   2959	2970	508	java/lang/Exception
    //   2974	2985	508	java/lang/Exception
    //   2989	3009	508	java/lang/Exception
    //   3009	3100	508	java/lang/Exception
    //   3100	3117	508	java/lang/Exception
    //   3117	3170	508	java/lang/Exception
    //   3175	3203	508	java/lang/Exception
    //   3208	3227	508	java/lang/Exception
    //   3230	3239	508	java/lang/Exception
    //   3242	3252	508	java/lang/Exception
    //   3255	3265	508	java/lang/Exception
    //   3268	3277	508	java/lang/Exception
    //   3280	3291	508	java/lang/Exception
    //   3301	3312	508	java/lang/Exception
    //   3315	3381	508	java/lang/Exception
    //   3381	3429	508	java/lang/Exception
    //   3429	3509	508	java/lang/Exception
    //   3509	3531	508	java/lang/Exception
    //   3534	3543	508	java/lang/Exception
    //   3546	3613	508	java/lang/Exception
    //   3623	3634	508	java/lang/Exception
    //   3638	3649	508	java/lang/Exception
    //   3652	3663	508	java/lang/Exception
    //   3667	3678	508	java/lang/Exception
    //   3682	3693	508	java/lang/Exception
    //   3697	3717	508	java/lang/Exception
    //   3717	3892	508	java/lang/Exception
    //   3897	3909	508	java/lang/Exception
    //   3923	3984	508	java/lang/Exception
    //   3987	3997	508	java/lang/Exception
    //   4000	4009	508	java/lang/Exception
    //   4012	4022	508	java/lang/Exception
    //   4025	4035	508	java/lang/Exception
    //   4038	4048	508	java/lang/Exception
    //   4051	4094	508	java/lang/Exception
    //   4096	4107	508	java/lang/Exception
    //   4173	4265	508	java/lang/Exception
    //   4265	4269	508	java/lang/Exception
    //   4269	4296	508	java/lang/Exception
    //   4299	4313	508	java/lang/Exception
    //   4323	4334	508	java/lang/Exception
    //   4337	4359	508	java/lang/Exception
    //   4362	4371	508	java/lang/Exception
    //   4381	4391	508	java/lang/Exception
    //   4486	4495	508	java/lang/Exception
    //   4511	4522	508	java/lang/Exception
    //   4525	4536	508	java/lang/Exception
    //   4539	4550	508	java/lang/Exception
    //   4554	4565	508	java/lang/Exception
    //   4569	4580	508	java/lang/Exception
    //   4582	4593	508	java/lang/Exception
    //   4597	4608	508	java/lang/Exception
    //   4611	4622	508	java/lang/Exception
    //   4625	4636	508	java/lang/Exception
    //   4639	4724	508	java/lang/Exception
    //   4724	4744	508	java/lang/Exception
    //   4747	4756	508	java/lang/Exception
    //   4759	4769	508	java/lang/Exception
    //   4772	4782	508	java/lang/Exception
    //   4785	4795	508	java/lang/Exception
    //   4798	4807	508	java/lang/Exception
    //   4810	4820	508	java/lang/Exception
    //   4823	4833	508	java/lang/Exception
    //   4836	4846	508	java/lang/Exception
    //   4849	4859	508	java/lang/Exception
    //   4989	4993	508	java/lang/Exception
    //   5197	5206	508	java/lang/Exception
    //   328	349	577	java/lang/Exception
    //   355	370	577	java/lang/Exception
    //   370	434	577	java/lang/Exception
    //   444	461	577	java/lang/Exception
    //   466	472	577	java/lang/Exception
    //   533	574	577	java/lang/Exception
    //   608	623	577	java/lang/Exception
    //   4408	4437	4485	java/lang/Exception
    //   4437	4482	4485	java/lang/Exception
    //   4869	4898	4988	java/lang/Exception
    //   4901	4909	4988	java/lang/Exception
    //   4918	4940	4988	java/lang/Exception
    //   4945	4952	4988	java/lang/Exception
    //   4956	4963	4988	java/lang/Exception
    //   4963	4985	4988	java/lang/Exception
    //   4996	5060	4988	java/lang/Exception
    //   5070	5116	5196	java/lang/Exception
    //   5118	5125	5196	java/lang/Exception
    //   5180	5193	5196	java/lang/Exception
    //   0	19	5239	java/lang/Exception
    //   21	27	5239	java/lang/Exception
    //   509	518	5239	java/lang/Exception
    //   5209	5217	5239	java/lang/Exception
    //   5222	5238	5239	java/lang/Exception
    //   5131	5173	5250	java/lang/Exception
    //   4113	4166	5255	java/lang/Exception
    //   1524	1577	5260	java/lang/Exception
  }
  
  private void dH(String paramString)
  {
    try
    {
      com.zing.zalo.f.a.aWV.put(paramString, "");
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void dI(String paramString)
  {
    r localR = new r();
    localR.a(new h(this));
    localR.n(paramString, 0);
  }
  
  /* Error */
  private void dJ(String paramString)
  {
    // Byte code:
    //   0: new 235	org/json/JSONObject
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   8: astore 7
    //   10: new 248	org/json/JSONArray
    //   13: dup
    //   14: invokespecial 1039	org/json/JSONArray:<init>	()V
    //   17: astore_1
    //   18: aload 7
    //   20: ldc_w 268
    //   23: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   26: ifne +221 -> 247
    //   29: new 248	org/json/JSONArray
    //   32: dup
    //   33: aload 7
    //   35: ldc_w 268
    //   38: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: invokespecial 1042	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   44: astore_1
    //   45: new 241	java/util/ArrayList
    //   48: dup
    //   49: invokespecial 242	java/util/ArrayList:<init>	()V
    //   52: astore 9
    //   54: iconst_0
    //   55: istore_2
    //   56: aload_1
    //   57: invokevirtual 251	org/json/JSONArray:length	()I
    //   60: istore_3
    //   61: iload_2
    //   62: iload_3
    //   63: if_icmpge +171 -> 234
    //   66: aload_1
    //   67: iload_2
    //   68: invokevirtual 255	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   71: astore 10
    //   73: aload 10
    //   75: ldc_w 262
    //   78: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   81: istore_3
    //   82: aload 10
    //   84: ldc_w 270
    //   87: invokevirtual 277	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   90: lstore 5
    //   92: aload 10
    //   94: ldc_w 1916
    //   97: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   100: ifne +157 -> 257
    //   103: aload 10
    //   105: ldc_w 1916
    //   108: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 7
    //   113: aload 10
    //   115: ldc_w 1918
    //   118: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   121: istore 4
    //   123: aload 10
    //   125: ldc_w 1359
    //   128: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   131: ifne +64 -> 195
    //   134: aload 10
    //   136: ldc_w 1359
    //   139: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   142: astore 8
    //   144: aload 9
    //   146: new 1920	com/zing/zalo/control/dn
    //   149: dup
    //   150: iload_3
    //   151: lload 5
    //   153: aload 7
    //   155: iload 4
    //   157: aload 8
    //   159: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   162: lconst_0
    //   163: invokespecial 1923	com/zing/zalo/control/dn:<init>	(IJLjava/lang/String;ILjava/lang/String;JJ)V
    //   166: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   169: pop
    //   170: aload_0
    //   171: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   174: aload 10
    //   176: invokevirtual 1305	org/json/JSONObject:toString	()Ljava/lang/String;
    //   179: invokestatic 1925	com/zing/a/f/e:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   182: pop
    //   183: aload 10
    //   185: invokevirtual 1305	org/json/JSONObject:toString	()Ljava/lang/String;
    //   188: invokestatic 1928	com/zing/zalo/utils/y:my	(Ljava/lang/String;)Z
    //   191: pop
    //   192: goto +58 -> 250
    //   195: ldc 117
    //   197: astore 8
    //   199: goto -55 -> 144
    //   202: astore 7
    //   204: aload_0
    //   205: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   208: aload 7
    //   210: invokevirtual 353	java/lang/Exception:toString	()Ljava/lang/String;
    //   213: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   216: pop
    //   217: goto +33 -> 250
    //   220: astore_1
    //   221: aload_0
    //   222: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   225: aload_1
    //   226: invokevirtual 353	java/lang/Exception:toString	()Ljava/lang/String;
    //   229: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   232: pop
    //   233: return
    //   234: aload_0
    //   235: aload 9
    //   237: invokevirtual 1932	com/zing/zalo/e/b:n	(Ljava/util/ArrayList;)V
    //   240: aload_0
    //   241: aload 9
    //   243: invokevirtual 1934	com/zing/zalo/e/b:m	(Ljava/util/ArrayList;)V
    //   246: return
    //   247: goto -202 -> 45
    //   250: iload_2
    //   251: iconst_1
    //   252: iadd
    //   253: istore_2
    //   254: goto -198 -> 56
    //   257: ldc 117
    //   259: astore 7
    //   261: goto -148 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	this	b
    //   0	264	1	paramString	String
    //   55	199	2	i	int
    //   60	91	3	j	int
    //   121	35	4	k	int
    //   90	62	5	l	long
    //   8	146	7	localObject	Object
    //   202	7	7	localException	Exception
    //   259	1	7	str1	String
    //   142	56	8	str2	String
    //   52	190	9	localArrayList	ArrayList
    //   71	113	10	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   66	113	202	java/lang/Exception
    //   113	144	202	java/lang/Exception
    //   144	192	202	java/lang/Exception
    //   0	45	220	java/lang/Exception
    //   45	54	220	java/lang/Exception
    //   56	61	220	java/lang/Exception
    //   204	217	220	java/lang/Exception
    //   234	246	220	java/lang/Exception
  }
  
  private void dK(String paramString)
  {
    try
    {
      int i;
      if ((MessagePopupActivity.adN()) && (MessagePopupActivity.adP() != null)) {
        i = 0;
      }
      for (;;)
      {
        synchronized (MessagePopupActivity.adP())
        {
          Iterator localIterator = MessagePopupActivity.adP().iterator();
          if (localIterator.hasNext())
          {
            bq localBq = (bq)localIterator.next();
            if ((localBq != null) && (!TextUtils.isEmpty(localBq.aOZ)) && (TextUtils.equals(localBq.aOZ, paramString)))
            {
              localBq.aTp = 50;
              i = 1;
            }
          }
          else
          {
            if (i != 0)
            {
              paramString = new Intent();
              paramString.setAction("com.zing.zalo.ui.MessagePopupActivityIntent");
              MainApplication.uw().sendBroadcast(paramString);
            }
            return;
          }
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
  }
  
  private void eW(int paramInt)
  {
    try
    {
      r localR = new r();
      localR.a(new f(this));
      com.zing.zalo.f.a.aYw = -1;
      localR.n(paramInt, false);
      return;
    }
    catch (Exception localException)
    {
      com.zing.a.f.e.b(this.TAG, localException);
    }
  }
  
  private void n(com.zing.zalo.control.g paramG)
  {
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        if (!com.zing.zalo.d.o.xR().V(paramG.aOW, paramG.aOZ)) {
          break;
        }
        Object localObject1;
        if (paramG.aOW.startsWith("group_"))
        {
          localObject1 = paramG.aOW.split("_");
          if ((localObject1 == null) || (localObject1.length <= 0)) {
            break label203;
          }
          localObject1 = localObject1[(localObject1.length - 1)];
          localCg = com.zing.zalo.db.bj.HC().fg((String)localObject1);
          localObject1 = localObject2;
          if (localCg != null)
          {
            localObject1 = localObject2;
            if (!TextUtils.isEmpty(localCg.getName()))
            {
              localObject1 = localObject2;
              if (!TextUtils.isEmpty(localCg.Fb()))
              {
                localObject1 = new bk(paramG.aOW);
                ((bk)localObject1).aRW = localCg.getName();
                ((bk)localObject1).aRZ = localCg.Fb();
              }
            }
          }
          if (localObject1 != null) {
            b((bk)localObject1, paramG);
          }
        }
        else
        {
          localObject1 = localObject2;
          if (paramG.aOW.startsWith("room_")) {
            continue;
          }
          localObject1 = localObject2;
          if (paramG.aOW.startsWith("wifi_room_")) {
            continue;
          }
          localObject1 = com.zing.zalo.d.v.yc().du(paramG.aOW);
          continue;
        }
        com.zing.zalo.d.o.xR().jdMethod_do(paramG.aOW);
        return;
      }
      catch (Exception paramG)
      {
        paramG.printStackTrace();
        return;
      }
      label203:
      cg localCg = null;
    }
  }
  
  public void AL()
  {
    try
    {
      if (com.zing.zalo.f.a.aXg.size() > 0)
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        for (;;)
        {
          dh localDh = (dh)com.zing.zalo.f.a.aXg.remove(0);
          if (!localDh.FW().startsWith("group_")) {
            localArrayList1.add(localDh);
          }
          while (com.zing.zalo.f.a.aXg.size() <= 0)
          {
            if (localArrayList1.size() > 0) {
              b(localArrayList1, false);
            }
            if (localArrayList2.size() <= 0) {
              return;
            }
            b(localArrayList2, true);
            return;
            localArrayList2.add(localDh);
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void AM()
  {
    af localAf;
    if (as.fP(true))
    {
      com.zing.a.f.e.i(this.TAG, "getMsgOfflineHttp");
      localAf = new af(null);
      localAf.type = 5;
      localAf.wG = ag.a(ai.aNT);
    }
    try
    {
      ArrayList localArrayList = new ArrayList();
      com.zing.a.d.a.e localE = new com.zing.a.d.a.e();
      localE.mM(Integer.parseInt(com.zing.a.a.dzm));
      localE.mN(Integer.parseInt(com.zing.a.a.dzm));
      localE.a((short)115);
      localE.i((byte)41);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localByteArrayOutputStream.write(com.zing.a.d.a.b.mG(com.zing.a.a.dzl.getBytes().length));
      localByteArrayOutputStream.write(com.zing.a.a.dzl.getBytes());
      localByteArrayOutputStream.write(0);
      localE.r(localByteArrayOutputStream.toByteArray());
      localArrayList.add(localE);
      localAf.K(localArrayList);
      com.zing.zalo.r.aa.c(localAf);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public String AN()
  {
    ArrayList localArrayList = AO();
    int j = localArrayList.size();
    StringBuilder localStringBuilder = new StringBuilder();
    if (j > 0) {}
    for (;;)
    {
      int i;
      try
      {
        localStringBuilder.append("[");
      }
      catch (Exception localException)
      {
        y localY;
        localException.printStackTrace();
      }
      if (i < j)
      {
        localY = (y)localArrayList.get(i);
        localStringBuilder.append("{");
        localStringBuilder.append("\"appname\":" + JSONObject.quote(localY.aMo) + ",");
        localStringBuilder.append("\"pname\":" + JSONObject.quote(localY.aMp) + ",");
        localStringBuilder.append("\"versionCode\":" + JSONObject.quote(new StringBuilder().append("").append(localY.versionCode).toString()));
        if (i != j - 1) {
          localStringBuilder.append("},");
        } else {
          localStringBuilder.append("}]");
        }
      }
      else
      {
        return localStringBuilder.toString();
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  public ArrayList<y> AO()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = MainApplication.uw().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      y localY = new y(this);
      localY.aMo = localPackageInfo.applicationInfo.loadLabel(MainApplication.uw().getPackageManager()).toString();
      localY.aMp = localPackageInfo.packageName;
      localY.versionCode = localPackageInfo.versionCode;
      localArrayList.add(localY);
      i += 1;
    }
    return localArrayList;
  }
  
  public void K(long paramLong)
  {
    try
    {
      new Thread(new p(this, paramLong)).start();
      return;
    }
    catch (Exception localException)
    {
      com.zing.a.f.e.e(this.TAG, localException.toString());
    }
  }
  
  /* Error */
  public void a(com.zing.a.d.a.e paramE)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 6
    //   3: iconst_1
    //   4: istore 5
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore_2
    //   10: aload_1
    //   11: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   14: ifnull +35 -> 49
    //   17: aload_1
    //   18: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   21: arraylength
    //   22: istore 4
    //   24: iload 4
    //   26: ifle +23 -> 49
    //   29: aload_1
    //   30: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   33: invokestatic 2136	com/zing/zalo/e/a/j:m	([B)[B
    //   36: astore 9
    //   38: aload 9
    //   40: ifnull +9 -> 49
    //   43: aload_1
    //   44: aload 9
    //   46: invokevirtual 2049	com/zing/a/d/a/e:r	([B)V
    //   49: new 114	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   56: ldc_w 2138
    //   59: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_1
    //   63: invokevirtual 2141	com/zing/a/d/a/e:awm	()B
    //   66: invokestatic 2143	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   69: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 2145
    //   75: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload_1
    //   79: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   82: invokestatic 2143	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   85: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   91: invokestatic 1928	com/zing/zalo/utils/y:my	(Ljava/lang/String;)Z
    //   94: pop
    //   95: aload_1
    //   96: invokevirtual 2141	com/zing/a/d/a/e:awm	()B
    //   99: iconst_2
    //   100: if_icmpne +658 -> 758
    //   103: aload_1
    //   104: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   107: bipush 105
    //   109: if_icmpne +90 -> 199
    //   112: aload_1
    //   113: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   116: arraylength
    //   117: bipush 8
    //   119: if_icmpne +24 -> 143
    //   122: aload_1
    //   123: bipush 105
    //   125: invokevirtual 2148	com/zing/a/d/a/e:iE	(I)V
    //   128: aload_0
    //   129: invokevirtual 344	com/zing/zalo/e/b:wV	()V
    //   132: aload_0
    //   133: aload_1
    //   134: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   137: invokestatic 2151	com/zing/a/d/a/b:y	([B)J
    //   140: invokespecial 2153	com/zing/zalo/e/b:J	(J)V
    //   143: return
    //   144: astore 9
    //   146: aload_0
    //   147: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   150: aload 9
    //   152: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   155: pop
    //   156: aconst_null
    //   157: astore 9
    //   159: goto -121 -> 38
    //   162: astore 9
    //   164: aload_0
    //   165: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   168: aload 9
    //   170: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   173: pop
    //   174: goto -125 -> 49
    //   177: astore_1
    //   178: aload_0
    //   179: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   182: aload_1
    //   183: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   186: pop
    //   187: return
    //   188: astore_1
    //   189: aload_0
    //   190: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   193: aload_1
    //   194: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   197: pop
    //   198: return
    //   199: aload_1
    //   200: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   203: istore_2
    //   204: iload_2
    //   205: bipush 106
    //   207: if_icmpne +91 -> 298
    //   210: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   213: ifnull -70 -> 143
    //   216: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   219: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   222: ifnull -79 -> 143
    //   225: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   228: getfield 950	com/zing/zalo/ui/fragments/ChatFragment:aQb	Lcom/zing/zalo/control/c;
    //   231: invokevirtual 953	com/zing/zalo/control/c:Cd	()Ljava/lang/String;
    //   234: new 114	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   241: ldc 117
    //   243: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: aload_1
    //   247: invokevirtual 2156	com/zing/a/d/a/e:awo	()I
    //   250: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   253: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   259: ifeq -116 -> 143
    //   262: aload_1
    //   263: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   266: bipush 31
    //   268: if_icmpne +22 -> 290
    //   271: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   274: iconst_1
    //   275: invokevirtual 2159	com/zing/zalo/ui/fragments/ChatFragment:kB	(I)V
    //   278: return
    //   279: astore_1
    //   280: aload_0
    //   281: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   284: aload_1
    //   285: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   288: pop
    //   289: return
    //   290: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   293: iconst_0
    //   294: invokevirtual 2159	com/zing/zalo/ui/fragments/ChatFragment:kB	(I)V
    //   297: return
    //   298: aload_1
    //   299: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   302: bipush 110
    //   304: if_icmpne +9 -> 313
    //   307: aload_0
    //   308: aload_1
    //   309: invokevirtual 2161	com/zing/zalo/e/b:c	(Lcom/zing/a/d/a/e;)V
    //   312: return
    //   313: aload_1
    //   314: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   317: ldc 117
    //   319: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   322: ifne -179 -> 143
    //   325: new 235	org/json/JSONObject
    //   328: dup
    //   329: aload_1
    //   330: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   333: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   336: astore 11
    //   338: aload 11
    //   340: ifnull -197 -> 143
    //   343: aload_1
    //   344: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   347: istore_2
    //   348: iload_2
    //   349: bipush 101
    //   351: if_icmpne +78 -> 429
    //   354: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   357: ifnull +2272 -> 2629
    //   360: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   363: new 114	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   370: ldc 117
    //   372: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: aload_1
    //   376: invokevirtual 2156	com/zing/a/d/a/e:awo	()I
    //   379: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   382: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   385: invokevirtual 2164	com/zing/zalo/ui/fragments/ChatFragment:lZ	(Ljava/lang/String;)Z
    //   388: istore 6
    //   390: iload 6
    //   392: ifeq +2237 -> 2629
    //   395: iconst_1
    //   396: istore_2
    //   397: aload_1
    //   398: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   401: istore_3
    //   402: iload_2
    //   403: ifne +2231 -> 2634
    //   406: aload_0
    //   407: iload_3
    //   408: aload 11
    //   410: iconst_0
    //   411: iload 5
    //   413: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   416: return
    //   417: astore 9
    //   419: aload 9
    //   421: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   424: iconst_0
    //   425: istore_2
    //   426: goto -29 -> 397
    //   429: aload_1
    //   430: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   433: sipush 201
    //   436: if_icmpne +276 -> 712
    //   439: ldc 117
    //   441: astore 10
    //   443: aconst_null
    //   444: astore 9
    //   446: aload 11
    //   448: ldc -23
    //   450: invokevirtual 246	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   453: astore 12
    //   455: aload 12
    //   457: invokevirtual 251	org/json/JSONArray:length	()I
    //   460: ifle +37 -> 497
    //   463: aload 12
    //   465: iconst_0
    //   466: invokevirtual 255	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   469: ldc_w 257
    //   472: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   475: astore 9
    //   477: aload 9
    //   479: ldc_w 262
    //   482: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   485: astore 10
    //   487: aload 9
    //   489: ldc_w 268
    //   492: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   495: astore 9
    //   497: aload 10
    //   499: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   502: ifne +130 -> 632
    //   505: aload 10
    //   507: ldc_w 419
    //   510: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   513: ifeq +119 -> 632
    //   516: aload 9
    //   518: ldc_w 1027
    //   521: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   524: ifne +102 -> 626
    //   527: aload 9
    //   529: ldc_w 1027
    //   532: invokevirtual 277	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   535: lstore 7
    //   537: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   540: new 114	java/lang/StringBuilder
    //   543: dup
    //   544: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   547: ldc_w 1115
    //   550: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: lload 7
    //   555: invokevirtual 124	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   558: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 2169	com/zing/zalo/f/c:aA	(Landroid/content/Context;Ljava/lang/String;)V
    //   564: invokestatic 1053	com/zing/zalo/db/bj:HC	()Lcom/zing/zalo/db/bj;
    //   567: lload 7
    //   569: invokestatic 2172	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   572: invokevirtual 1148	com/zing/zalo/db/bj:ff	(Ljava/lang/String;)V
    //   575: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   578: lload 7
    //   580: invokestatic 2172	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   583: bipush -3
    //   585: invokeinterface 2174 3 0
    //   590: invokestatic 763	com/zing/zalo/d/o:xR	()Lcom/zing/zalo/d/o;
    //   593: new 114	java/lang/StringBuilder
    //   596: dup
    //   597: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   600: ldc_w 1115
    //   603: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: lload 7
    //   608: invokevirtual 124	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   611: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   614: invokevirtual 1010	com/zing/zalo/d/o:do	(Ljava/lang/String;)V
    //   617: lload 7
    //   619: invokestatic 2172	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   622: invokestatic 2177	com/zing/zalo/utils/bm:ng	(Ljava/lang/String;)V
    //   625: return
    //   626: lconst_0
    //   627: lstore 7
    //   629: goto -92 -> 537
    //   632: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   635: ifnull +2005 -> 2640
    //   638: invokestatic 946	com/zing/zalo/ui/fragments/ChatFragment:ahI	()Lcom/zing/zalo/ui/fragments/ChatFragment;
    //   641: new 114	java/lang/StringBuilder
    //   644: dup
    //   645: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   648: ldc_w 1115
    //   651: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: aload_1
    //   655: invokevirtual 2180	com/zing/a/d/a/e:aws	()I
    //   658: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   661: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   664: invokevirtual 2164	com/zing/zalo/ui/fragments/ChatFragment:lZ	(Ljava/lang/String;)Z
    //   667: istore 5
    //   669: iload 5
    //   671: ifeq +1969 -> 2640
    //   674: iconst_1
    //   675: istore_2
    //   676: aload_1
    //   677: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   680: istore_3
    //   681: iload_2
    //   682: ifne +1963 -> 2645
    //   685: iload 6
    //   687: istore 5
    //   689: aload_0
    //   690: iload_3
    //   691: aload 11
    //   693: iconst_1
    //   694: iload 5
    //   696: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   699: return
    //   700: astore 9
    //   702: aload 9
    //   704: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   707: iconst_0
    //   708: istore_2
    //   709: goto -33 -> 676
    //   712: aload_1
    //   713: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   716: sipush 301
    //   719: if_icmpne +16 -> 735
    //   722: aload_0
    //   723: aload_1
    //   724: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   727: aload 11
    //   729: iconst_2
    //   730: iconst_0
    //   731: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   734: return
    //   735: aload_1
    //   736: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   739: sipush 302
    //   742: if_icmpne -599 -> 143
    //   745: aload_0
    //   746: aload_1
    //   747: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   750: aload 11
    //   752: iconst_3
    //   753: iconst_0
    //   754: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   757: return
    //   758: aload_1
    //   759: invokevirtual 2141	com/zing/a/d/a/e:awm	()B
    //   762: iconst_1
    //   763: if_icmpne +719 -> 1482
    //   766: aload_1
    //   767: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   770: sipush 402
    //   773: if_icmpeq +73 -> 846
    //   776: aload_1
    //   777: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   780: sipush 407
    //   783: if_icmpeq +63 -> 846
    //   786: aload_1
    //   787: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   790: sipush 408
    //   793: if_icmpeq +53 -> 846
    //   796: aload_1
    //   797: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   800: sipush 409
    //   803: if_icmpeq +43 -> 846
    //   806: aload_1
    //   807: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   810: sipush 411
    //   813: if_icmpeq +33 -> 846
    //   816: aload_1
    //   817: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   820: sipush 413
    //   823: if_icmpeq +23 -> 846
    //   826: aload_1
    //   827: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   830: sipush 418
    //   833: if_icmpeq +13 -> 846
    //   836: aload_1
    //   837: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   840: sipush 419
    //   843: if_icmpne +281 -> 1124
    //   846: ldc_w 2182
    //   849: new 114	java/lang/StringBuilder
    //   852: dup
    //   853: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   856: aload_1
    //   857: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   860: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   863: ldc 117
    //   865: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   868: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   871: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   874: pop
    //   875: ldc_w 2184
    //   878: new 114	java/lang/StringBuilder
    //   881: dup
    //   882: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   885: aload_1
    //   886: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   889: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   892: ldc 117
    //   894: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   897: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   900: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   903: pop
    //   904: aload_1
    //   905: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   908: ifne -765 -> 143
    //   911: invokestatic 876	com/zing/zalo/utils/bm:arq	()Z
    //   914: ifeq +95 -> 1009
    //   917: getstatic 913	com/zing/zalo/f/a:aXZ	Z
    //   920: ifne +9 -> 929
    //   923: getstatic 910	com/zing/zalo/f/a:aYb	Z
    //   926: ifeq +83 -> 1009
    //   929: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   932: getstatic 2187	com/zing/zalo/f/a:aYg	J
    //   935: lsub
    //   936: lstore 7
    //   938: getstatic 2190	com/zing/zalo/f/a:aYd	Ljava/lang/String;
    //   941: getstatic 2191	com/zing/zalo/ui/PasscodeActivity:TAG	Ljava/lang/String;
    //   944: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   947: ifne +108 -> 1055
    //   950: lload 7
    //   952: lconst_0
    //   953: lcmp
    //   954: iflt +101 -> 1055
    //   957: lload 7
    //   959: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   962: invokestatic 2194	com/zing/zalo/f/c:ek	(Landroid/content/Context;)I
    //   965: i2l
    //   966: lcmp
    //   967: ifge +88 -> 1055
    //   970: lconst_0
    //   971: putstatic 2187	com/zing/zalo/f/a:aYg	J
    //   974: iconst_0
    //   975: putstatic 913	com/zing/zalo/f/a:aXZ	Z
    //   978: iconst_0
    //   979: putstatic 910	com/zing/zalo/f/a:aYb	Z
    //   982: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   985: getstatic 2187	com/zing/zalo/f/a:aYg	J
    //   988: invokestatic 2197	com/zing/zalo/f/c:U	(Landroid/content/Context;J)V
    //   991: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   994: getstatic 913	com/zing/zalo/f/a:aXZ	Z
    //   997: invokestatic 2199	com/zing/zalo/f/c:Q	(Landroid/content/Context;Z)V
    //   1000: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1003: getstatic 910	com/zing/zalo/f/a:aYb	Z
    //   1006: invokestatic 2202	com/zing/zalo/f/c:S	(Landroid/content/Context;Z)V
    //   1009: aload_1
    //   1010: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1013: sipush 402
    //   1016: if_icmpne +9 -> 1025
    //   1019: invokestatic 538	com/zing/zalo/d/ag:yj	()Lcom/zing/zalo/d/ag;
    //   1022: invokevirtual 2205	com/zing/zalo/d/ag:yn	()V
    //   1025: invokestatic 183	com/zing/zalo/c/aa:xi	()Lcom/zing/zalo/c/aa;
    //   1028: aload_1
    //   1029: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1032: aload_1
    //   1033: invokevirtual 2156	com/zing/a/d/a/e:awo	()I
    //   1036: aload_1
    //   1037: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1040: invokevirtual 2208	com/zing/zalo/c/aa:a	(IILjava/lang/String;)V
    //   1043: return
    //   1044: astore_1
    //   1045: aload_0
    //   1046: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1049: aload_1
    //   1050: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1053: pop
    //   1054: return
    //   1055: getstatic 913	com/zing/zalo/f/a:aXZ	Z
    //   1058: ifeq +40 -> 1098
    //   1061: getstatic 910	com/zing/zalo/f/a:aYb	Z
    //   1064: ifeq +34 -> 1098
    //   1067: iconst_3
    //   1068: putstatic 2211	com/zing/zalo/f/a:aYf	I
    //   1071: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   1074: getstatic 2211	com/zing/zalo/f/a:aYf	I
    //   1077: invokestatic 2214	com/zing/zalo/f/c:L	(Landroid/content/Context;I)V
    //   1080: goto -71 -> 1009
    //   1083: astore 9
    //   1085: aload_0
    //   1086: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1089: aload 9
    //   1091: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1094: pop
    //   1095: goto -70 -> 1025
    //   1098: getstatic 913	com/zing/zalo/f/a:aXZ	Z
    //   1101: ifeq +10 -> 1111
    //   1104: iconst_1
    //   1105: putstatic 2211	com/zing/zalo/f/a:aYf	I
    //   1108: goto -37 -> 1071
    //   1111: getstatic 910	com/zing/zalo/f/a:aYb	Z
    //   1114: ifeq -43 -> 1071
    //   1117: iconst_2
    //   1118: putstatic 2211	com/zing/zalo/f/a:aYf	I
    //   1121: goto -50 -> 1071
    //   1124: aload_1
    //   1125: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1128: sipush 403
    //   1131: if_icmpne +50 -> 1181
    //   1134: ldc_w 2182
    //   1137: ldc_w 2216
    //   1140: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1143: pop
    //   1144: aload_1
    //   1145: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1148: ifne -1005 -> 143
    //   1151: invokestatic 183	com/zing/zalo/c/aa:xi	()Lcom/zing/zalo/c/aa;
    //   1154: aload_1
    //   1155: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1158: aload_1
    //   1159: invokevirtual 2156	com/zing/a/d/a/e:awo	()I
    //   1162: aload_1
    //   1163: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1166: invokevirtual 2208	com/zing/zalo/c/aa:a	(IILjava/lang/String;)V
    //   1169: return
    //   1170: astore_1
    //   1171: aload_0
    //   1172: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1175: aload_1
    //   1176: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1179: pop
    //   1180: return
    //   1181: aload_1
    //   1182: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1185: sipush 405
    //   1188: if_icmpne +50 -> 1238
    //   1191: ldc_w 2182
    //   1194: ldc_w 2218
    //   1197: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1200: pop
    //   1201: aload_1
    //   1202: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1205: ifne -1062 -> 143
    //   1208: invokestatic 183	com/zing/zalo/c/aa:xi	()Lcom/zing/zalo/c/aa;
    //   1211: aload_1
    //   1212: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1215: aload_1
    //   1216: invokevirtual 2156	com/zing/a/d/a/e:awo	()I
    //   1219: aload_1
    //   1220: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1223: invokevirtual 2208	com/zing/zalo/c/aa:a	(IILjava/lang/String;)V
    //   1226: return
    //   1227: astore_1
    //   1228: aload_0
    //   1229: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1232: aload_1
    //   1233: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1236: pop
    //   1237: return
    //   1238: aload_1
    //   1239: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1242: bipush 107
    //   1244: if_icmpne +54 -> 1298
    //   1247: aload_0
    //   1248: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1251: ldc_w 2220
    //   1254: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1257: pop
    //   1258: aload_1
    //   1259: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1262: ifne -1119 -> 143
    //   1265: aload_1
    //   1266: invokevirtual 2223	com/zing/a/d/a/e:aww	()Ljava/util/ArrayList;
    //   1269: ifnull -1126 -> 143
    //   1272: aload_1
    //   1273: invokevirtual 2223	com/zing/a/d/a/e:aww	()Ljava/util/ArrayList;
    //   1276: invokevirtual 374	java/util/ArrayList:size	()I
    //   1279: ifle -1136 -> 143
    //   1282: new 2225	com/zing/zalo/e/c
    //   1285: dup
    //   1286: aload_0
    //   1287: aload_1
    //   1288: invokevirtual 2223	com/zing/a/d/a/e:aww	()Ljava/util/ArrayList;
    //   1291: invokespecial 2228	com/zing/zalo/e/c:<init>	(Lcom/zing/zalo/e/b;Ljava/util/ArrayList;)V
    //   1294: invokestatic 222	com/zing/zalo/r/c:b	(Lcom/zing/zalo/db/bz;)V
    //   1297: return
    //   1298: aload_1
    //   1299: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1302: bipush 90
    //   1304: if_icmpne +12 -> 1316
    //   1307: aload_0
    //   1308: aload_1
    //   1309: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1312: invokespecial 2230	com/zing/zalo/e/b:dJ	(Ljava/lang/String;)V
    //   1315: return
    //   1316: aload_1
    //   1317: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1320: bipush 109
    //   1322: if_icmpne -1179 -> 143
    //   1325: aload_1
    //   1326: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1329: ldc 117
    //   1331: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1334: ifne -1191 -> 143
    //   1337: new 235	org/json/JSONObject
    //   1340: dup
    //   1341: aload_1
    //   1342: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1345: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1348: astore 9
    //   1350: aload 9
    //   1352: ifnull -1209 -> 143
    //   1355: aload 9
    //   1357: ldc_w 2232
    //   1360: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1363: ifne +96 -> 1459
    //   1366: aload 9
    //   1368: ldc_w 2232
    //   1371: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1374: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1377: ifne +82 -> 1459
    //   1380: new 248	org/json/JSONArray
    //   1383: dup
    //   1384: aload 9
    //   1386: ldc_w 2232
    //   1389: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1392: invokespecial 1042	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   1395: astore_1
    //   1396: aload 9
    //   1398: ldc_w 2234
    //   1401: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1404: ifne +66 -> 1470
    //   1407: aload 9
    //   1409: ldc_w 2234
    //   1412: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1415: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1418: ifne +52 -> 1470
    //   1421: new 248	org/json/JSONArray
    //   1424: dup
    //   1425: aload 9
    //   1427: ldc_w 2234
    //   1430: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1433: invokespecial 1042	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   1436: astore 9
    //   1438: new 2236	com/zing/zalo/e/n
    //   1441: dup
    //   1442: aload_0
    //   1443: aload_1
    //   1444: aload 9
    //   1446: invokespecial 2239	com/zing/zalo/e/n:<init>	(Lcom/zing/zalo/e/b;Lorg/json/JSONArray;Lorg/json/JSONArray;)V
    //   1449: invokestatic 222	com/zing/zalo/r/c:b	(Lcom/zing/zalo/db/bz;)V
    //   1452: return
    //   1453: astore_1
    //   1454: aload_1
    //   1455: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   1458: return
    //   1459: new 248	org/json/JSONArray
    //   1462: dup
    //   1463: invokespecial 1039	org/json/JSONArray:<init>	()V
    //   1466: astore_1
    //   1467: goto -71 -> 1396
    //   1470: new 248	org/json/JSONArray
    //   1473: dup
    //   1474: invokespecial 1039	org/json/JSONArray:<init>	()V
    //   1477: astore 9
    //   1479: goto -41 -> 1438
    //   1482: aload_1
    //   1483: invokevirtual 2141	com/zing/a/d/a/e:awm	()B
    //   1486: iconst_3
    //   1487: if_icmpeq +11 -> 1498
    //   1490: aload_1
    //   1491: invokevirtual 2141	com/zing/a/d/a/e:awm	()B
    //   1494: iconst_4
    //   1495: if_icmpne -1352 -> 143
    //   1498: aload_1
    //   1499: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1502: bipush 105
    //   1504: if_icmpne +65 -> 1569
    //   1507: aload_1
    //   1508: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   1511: arraylength
    //   1512: bipush 8
    //   1514: if_icmpne +24 -> 1538
    //   1517: aload_1
    //   1518: bipush 105
    //   1520: invokevirtual 2148	com/zing/a/d/a/e:iE	(I)V
    //   1523: aload_0
    //   1524: invokevirtual 344	com/zing/zalo/e/b:wV	()V
    //   1527: aload_0
    //   1528: aload_1
    //   1529: invokevirtual 2131	com/zing/a/d/a/e:WC	()[B
    //   1532: invokestatic 2151	com/zing/a/d/a/b:y	([B)J
    //   1535: invokespecial 2153	com/zing/zalo/e/b:J	(J)V
    //   1538: getstatic 48	com/zing/zalo/e/b:Ih	Ljava/util/concurrent/ThreadPoolExecutor;
    //   1541: new 2241	com/zing/zalo/e/t
    //   1544: dup
    //   1545: aload_0
    //   1546: aload_1
    //   1547: invokespecial 2243	com/zing/zalo/e/t:<init>	(Lcom/zing/zalo/e/b;Lcom/zing/a/d/a/e;)V
    //   1550: invokevirtual 2246	java/util/concurrent/ThreadPoolExecutor:execute	(Ljava/lang/Runnable;)V
    //   1553: return
    //   1554: astore 9
    //   1556: aload_0
    //   1557: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1560: aload 9
    //   1562: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1565: pop
    //   1566: goto -28 -> 1538
    //   1569: aload_1
    //   1570: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1573: iconst_1
    //   1574: if_icmpne +67 -> 1641
    //   1577: aload_1
    //   1578: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1581: ifne +24 -> 1605
    //   1584: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   1587: iconst_1
    //   1588: putfield 2249	com/zing/zalo/e/a/a:aWy	Z
    //   1591: aload_0
    //   1592: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1595: ldc_w 2251
    //   1598: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1601: pop
    //   1602: goto -64 -> 1538
    //   1605: aload_1
    //   1606: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1609: iconst_m1
    //   1610: if_icmpne +22 -> 1632
    //   1613: iconst_1
    //   1614: putstatic 2256	com/zing/zalo/r/r:dtK	Z
    //   1617: invokestatic 2260	com/zing/zalo/r/r:aut	()Lcom/zing/zalo/r/r;
    //   1620: invokevirtual 2263	com/zing/zalo/r/r:auu	()V
    //   1623: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   1626: invokevirtual 2266	com/zing/zalo/e/a/a:disconnect	()V
    //   1629: goto -91 -> 1538
    //   1632: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   1635: invokevirtual 2266	com/zing/zalo/e/a/a:disconnect	()V
    //   1638: goto -100 -> 1538
    //   1641: aload_1
    //   1642: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1645: iconst_3
    //   1646: if_icmpne +53 -> 1699
    //   1649: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   1652: aload_1
    //   1653: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1656: invokevirtual 2269	com/zing/zalo/e/a/a:mQ	(I)V
    //   1659: aload_1
    //   1660: invokevirtual 791	com/zing/a/d/a/e:awr	()I
    //   1663: bipush -16
    //   1665: if_icmpne +23 -> 1688
    //   1668: invokestatic 370	com/zing/zalo/e/a/a:Bc	()Lcom/zing/zalo/e/a/a;
    //   1671: invokevirtual 813	com/zing/zalo/e/a/a:awD	()Lcom/zing/a/d/a/c;
    //   1674: bipush -16
    //   1676: ldc 117
    //   1678: sipush 9021
    //   1681: iconst_0
    //   1682: invokestatic 848	com/zing/a/e/g:a	(Lcom/zing/a/d/a/c;ILjava/lang/String;II)V
    //   1685: invokestatic 2272	com/zing/zalo/r/r:auw	()V
    //   1688: ldc_w 2274
    //   1691: iconst_1
    //   1692: invokestatic 2279	com/zing/a/f/f:w	(Ljava/lang/String;Z)Z
    //   1695: pop
    //   1696: goto -158 -> 1538
    //   1699: aload_1
    //   1700: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1703: bipush 102
    //   1705: if_icmpne +47 -> 1752
    //   1708: aload_1
    //   1709: invokevirtual 826	com/zing/a/d/a/e:awq	()B
    //   1712: iconst_2
    //   1713: if_icmpne +21 -> 1734
    //   1716: new 2281	com/zing/zalo/e/r
    //   1719: dup
    //   1720: aload_0
    //   1721: aload_1
    //   1722: invokevirtual 2284	com/zing/a/d/a/e:awu	()Ljava/util/ArrayList;
    //   1725: invokespecial 2285	com/zing/zalo/e/r:<init>	(Lcom/zing/zalo/e/b;Ljava/util/ArrayList;)V
    //   1728: invokestatic 222	com/zing/zalo/r/c:b	(Lcom/zing/zalo/db/bz;)V
    //   1731: goto -193 -> 1538
    //   1734: new 2287	com/zing/zalo/e/s
    //   1737: dup
    //   1738: aload_0
    //   1739: aload_1
    //   1740: invokevirtual 2284	com/zing/a/d/a/e:awu	()Ljava/util/ArrayList;
    //   1743: invokespecial 2288	com/zing/zalo/e/s:<init>	(Lcom/zing/zalo/e/b;Ljava/util/ArrayList;)V
    //   1746: invokestatic 222	com/zing/zalo/r/c:b	(Lcom/zing/zalo/db/bz;)V
    //   1749: goto -211 -> 1538
    //   1752: aload_1
    //   1753: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1756: sipush 224
    //   1759: if_icmpne +63 -> 1822
    //   1762: aload_1
    //   1763: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1766: ldc 117
    //   1768: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1771: ifne -233 -> 1538
    //   1774: new 235	org/json/JSONObject
    //   1777: dup
    //   1778: aload_1
    //   1779: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1782: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1785: astore 9
    //   1787: aload 9
    //   1789: ifnull -251 -> 1538
    //   1792: aload_0
    //   1793: aload_1
    //   1794: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1797: aload 9
    //   1799: iconst_1
    //   1800: iconst_1
    //   1801: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   1804: goto -266 -> 1538
    //   1807: astore 9
    //   1809: aload_0
    //   1810: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1813: aload 9
    //   1815: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1818: pop
    //   1819: goto -281 -> 1538
    //   1822: aload_1
    //   1823: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1826: bipush 121
    //   1828: if_icmpne +63 -> 1891
    //   1831: aload_1
    //   1832: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1835: ldc 117
    //   1837: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1840: ifne -302 -> 1538
    //   1843: new 235	org/json/JSONObject
    //   1846: dup
    //   1847: aload_1
    //   1848: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1851: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1854: astore 9
    //   1856: aload 9
    //   1858: ifnull -320 -> 1538
    //   1861: aload_0
    //   1862: aload_1
    //   1863: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1866: aload 9
    //   1868: iconst_0
    //   1869: iconst_1
    //   1870: invokespecial 2166	com/zing/zalo/e/b:a	(ILorg/json/JSONObject;IZ)V
    //   1873: goto -335 -> 1538
    //   1876: astore 9
    //   1878: aload_0
    //   1879: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   1882: aload 9
    //   1884: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1887: pop
    //   1888: goto -350 -> 1538
    //   1891: aload_1
    //   1892: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1895: sipush 600
    //   1898: if_icmpne +14 -> 1912
    //   1901: aload_0
    //   1902: aload_1
    //   1903: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1906: invokespecial 2290	com/zing/zalo/e/b:dG	(Ljava/lang/String;)V
    //   1909: goto -371 -> 1538
    //   1912: aload_1
    //   1913: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   1916: sipush 808
    //   1919: if_icmpne +507 -> 2426
    //   1922: aload_1
    //   1923: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1926: ldc 117
    //   1928: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1931: ifne -393 -> 1538
    //   1934: new 235	org/json/JSONObject
    //   1937: dup
    //   1938: aload_1
    //   1939: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   1942: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1945: astore 9
    //   1947: aload 9
    //   1949: ifnull -411 -> 1538
    //   1952: aload 9
    //   1954: ldc_w 268
    //   1957: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   1960: astore 9
    //   1962: aload 9
    //   1964: ifnull -426 -> 1538
    //   1967: aload 9
    //   1969: ldc_w 2292
    //   1972: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1975: ifeq -437 -> 1538
    //   1978: aload 9
    //   1980: ldc_w 2292
    //   1983: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   1986: astore 16
    //   1988: aload 16
    //   1990: ifnull -452 -> 1538
    //   1993: aload 16
    //   1995: ldc_w 1836
    //   1998: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2001: ifeq +334 -> 2335
    //   2004: ldc 117
    //   2006: astore 9
    //   2008: aload 16
    //   2010: ldc_w 2294
    //   2013: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2016: ifeq +332 -> 2348
    //   2019: ldc 117
    //   2021: astore 10
    //   2023: aload 16
    //   2025: ldc_w 2296
    //   2028: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2031: ifeq +330 -> 2361
    //   2034: ldc 117
    //   2036: astore 11
    //   2038: aload 16
    //   2040: ldc_w 1047
    //   2043: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2046: ifeq +328 -> 2374
    //   2049: ldc 117
    //   2051: astore 12
    //   2053: aload 16
    //   2055: ldc_w 2298
    //   2058: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2061: ifeq +326 -> 2387
    //   2064: ldc 117
    //   2066: astore 13
    //   2068: aload 16
    //   2070: ldc_w 2300
    //   2073: invokevirtual 239	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   2076: ifeq +324 -> 2400
    //   2079: ldc 117
    //   2081: astore 15
    //   2083: aload 15
    //   2085: astore 14
    //   2087: aload 16
    //   2089: ifnull +62 -> 2151
    //   2092: aload 15
    //   2094: astore 14
    //   2096: aload 16
    //   2098: ldc_w 2302
    //   2101: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   2104: ifeq +47 -> 2151
    //   2107: aload 16
    //   2109: ldc_w 2302
    //   2112: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   2115: astore 16
    //   2117: aload 15
    //   2119: astore 14
    //   2121: aload 16
    //   2123: ifnull +28 -> 2151
    //   2126: aload 15
    //   2128: astore 14
    //   2130: aload 16
    //   2132: ldc_w 2304
    //   2135: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   2138: ifeq +13 -> 2151
    //   2141: aload 16
    //   2143: ldc_w 2304
    //   2146: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2149: astore 14
    //   2151: new 2306	com/zing/zalo/social/radar/c
    //   2154: dup
    //   2155: invokespecial 2307	com/zing/zalo/social/radar/c:<init>	()V
    //   2158: astore 15
    //   2160: aload 15
    //   2162: aload 9
    //   2164: putfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2167: aload 15
    //   2169: aload 12
    //   2171: putfield 2309	com/zing/zalo/social/radar/c:aRZ	Ljava/lang/String;
    //   2174: aload 15
    //   2176: aload 10
    //   2178: putfield 2310	com/zing/zalo/social/radar/c:aRW	Ljava/lang/String;
    //   2181: aload 15
    //   2183: aload 11
    //   2185: putfield 2311	com/zing/zalo/social/radar/c:aRY	Ljava/lang/String;
    //   2188: aload 15
    //   2190: iload_2
    //   2191: putfield 2314	com/zing/zalo/social/radar/c:aSa	I
    //   2194: aload 15
    //   2196: aload 14
    //   2198: putfield 2317	com/zing/zalo/social/radar/c:aSg	Ljava/lang/String;
    //   2201: aload 15
    //   2203: aload 15
    //   2205: getfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2208: aload 15
    //   2210: getfield 2310	com/zing/zalo/social/radar/c:aRW	Ljava/lang/String;
    //   2213: invokestatic 701	com/zing/zalo/h/n:aY	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2216: putfield 2310	com/zing/zalo/social/radar/c:aRW	Ljava/lang/String;
    //   2219: aload 15
    //   2221: aload 13
    //   2223: putfield 2318	com/zing/zalo/social/radar/c:aSd	Ljava/lang/String;
    //   2226: aload 15
    //   2228: iconst_2
    //   2229: putfield 2321	com/zing/zalo/social/radar/c:zB	I
    //   2232: getstatic 2324	com/zing/zalo/f/a:aXd	Ljava/util/Map;
    //   2235: aload 15
    //   2237: getfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2240: invokeinterface 498 2 0
    //   2245: ifne -707 -> 1538
    //   2248: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   2251: invokevirtual 1558	com/zing/zalo/h/a:PK	()Lcom/zing/zalo/control/ca;
    //   2254: aload 15
    //   2256: getfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2259: invokevirtual 1563	com/zing/zalo/control/ca:ed	(Ljava/lang/String;)Z
    //   2262: ifne -724 -> 1538
    //   2265: getstatic 2324	com/zing/zalo/f/a:aXd	Ljava/util/Map;
    //   2268: aload 15
    //   2270: getfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2273: aload 15
    //   2275: invokeinterface 686 3 0
    //   2280: pop
    //   2281: invokestatic 1591	com/zing/zalo/utils/bm:arv	()V
    //   2284: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2287: invokestatic 2327	com/zing/zalo/f/c:fe	(Landroid/content/Context;)Z
    //   2290: ifeq -752 -> 1538
    //   2293: getstatic 2331	com/zing/zalo/f/a:aXe	Ljava/util/Vector;
    //   2296: aload 15
    //   2298: invokevirtual 2334	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   2301: pop
    //   2302: invokestatic 231	java/lang/System:currentTimeMillis	()J
    //   2305: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   2308: invokestatic 2337	com/zing/zalo/f/c:ff	(Landroid/content/Context;)J
    //   2311: lsub
    //   2312: ldc2_w 2338
    //   2315: lcmp
    //   2316: ifle -778 -> 1538
    //   2319: invokestatic 2342	com/zing/zalo/utils/bm:arx	()V
    //   2322: goto -784 -> 1538
    //   2325: astore 9
    //   2327: aload 9
    //   2329: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   2332: goto -794 -> 1538
    //   2335: aload 16
    //   2337: ldc_w 1836
    //   2340: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2343: astore 9
    //   2345: goto -337 -> 2008
    //   2348: aload 16
    //   2350: ldc_w 2294
    //   2353: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2356: astore 10
    //   2358: goto -335 -> 2023
    //   2361: aload 16
    //   2363: ldc_w 2296
    //   2366: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2369: astore 11
    //   2371: goto -333 -> 2038
    //   2374: aload 16
    //   2376: ldc_w 1047
    //   2379: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2382: astore 12
    //   2384: goto -331 -> 2053
    //   2387: aload 16
    //   2389: ldc_w 2298
    //   2392: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2395: astore 13
    //   2397: goto -329 -> 2068
    //   2400: aload 16
    //   2402: ldc_w 2300
    //   2405: invokevirtual 801	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   2408: istore_2
    //   2409: goto -330 -> 2079
    //   2412: astore 14
    //   2414: aload 14
    //   2416: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   2419: aload 15
    //   2421: astore 14
    //   2423: goto -272 -> 2151
    //   2426: aload_1
    //   2427: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   2430: sipush 809
    //   2433: if_icmpne +168 -> 2601
    //   2436: aload_1
    //   2437: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   2440: ldc 117
    //   2442: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2445: ifne -907 -> 1538
    //   2448: new 235	org/json/JSONObject
    //   2451: dup
    //   2452: aload_1
    //   2453: invokevirtual 795	com/zing/a/d/a/e:Wz	()Ljava/lang/String;
    //   2456: invokespecial 689	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   2459: astore 9
    //   2461: aload 9
    //   2463: ifnull -925 -> 1538
    //   2466: aload 9
    //   2468: ldc_w 268
    //   2471: invokevirtual 260	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   2474: astore 9
    //   2476: aload 9
    //   2478: ifnull -940 -> 1538
    //   2481: aload 9
    //   2483: ldc_w 2292
    //   2486: invokevirtual 273	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   2489: ifeq -951 -> 1538
    //   2492: aload 9
    //   2494: ldc_w 2292
    //   2497: invokevirtual 266	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   2500: astore 9
    //   2502: aload 9
    //   2504: invokestatic 459	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2507: ifne -969 -> 1538
    //   2510: getstatic 2324	com/zing/zalo/f/a:aXd	Ljava/util/Map;
    //   2513: aload 9
    //   2515: invokeinterface 498 2 0
    //   2520: ifeq -982 -> 1538
    //   2523: getstatic 2324	com/zing/zalo/f/a:aXd	Ljava/util/Map;
    //   2526: aload 9
    //   2528: invokeinterface 2344 2 0
    //   2533: pop
    //   2534: invokestatic 1591	com/zing/zalo/utils/bm:arv	()V
    //   2537: iload_3
    //   2538: istore_2
    //   2539: iload_2
    //   2540: getstatic 2331	com/zing/zalo/f/a:aXe	Ljava/util/Vector;
    //   2543: invokevirtual 2345	java/util/Vector:size	()I
    //   2546: if_icmpge +32 -> 2578
    //   2549: getstatic 2331	com/zing/zalo/f/a:aXe	Ljava/util/Vector;
    //   2552: iload_2
    //   2553: invokevirtual 2346	java/util/Vector:get	(I)Ljava/lang/Object;
    //   2556: checkcast 2306	com/zing/zalo/social/radar/c
    //   2559: getfield 2308	com/zing/zalo/social/radar/c:aOC	Ljava/lang/String;
    //   2562: aload 9
    //   2564: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2567: ifeq +27 -> 2594
    //   2570: getstatic 2331	com/zing/zalo/f/a:aXe	Ljava/util/Vector;
    //   2573: iload_2
    //   2574: invokevirtual 2347	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   2577: pop
    //   2578: invokestatic 2342	com/zing/zalo/utils/bm:arx	()V
    //   2581: goto -1043 -> 1538
    //   2584: astore 9
    //   2586: aload 9
    //   2588: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   2591: goto -1053 -> 1538
    //   2594: iload_2
    //   2595: iconst_1
    //   2596: iadd
    //   2597: istore_2
    //   2598: goto -59 -> 2539
    //   2601: aload_1
    //   2602: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   2605: sipush 812
    //   2608: if_icmpeq -1070 -> 1538
    //   2611: aload_1
    //   2612: invokevirtual 822	com/zing/a/d/a/e:WA	()S
    //   2615: sipush 266
    //   2618: if_icmpne -1080 -> 1538
    //   2621: aload_0
    //   2622: aload_1
    //   2623: invokespecial 2349	com/zing/zalo/e/b:d	(Lcom/zing/a/d/a/e;)V
    //   2626: goto -1088 -> 1538
    //   2629: iconst_0
    //   2630: istore_2
    //   2631: goto -2234 -> 397
    //   2634: iconst_0
    //   2635: istore 5
    //   2637: goto -2231 -> 406
    //   2640: iconst_0
    //   2641: istore_2
    //   2642: goto -1966 -> 676
    //   2645: iconst_0
    //   2646: istore 5
    //   2648: goto -1959 -> 689
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2651	0	this	b
    //   0	2651	1	paramE	com.zing.a.d.a.e
    //   9	2633	2	i	int
    //   7	2531	3	j	int
    //   22	3	4	k	int
    //   4	2643	5	bool1	boolean
    //   1	685	6	bool2	boolean
    //   535	423	7	l	long
    //   36	9	9	arrayOfByte	byte[]
    //   144	7	9	localException1	Exception
    //   157	1	9	localObject1	Object
    //   162	7	9	localException2	Exception
    //   417	3	9	localException3	Exception
    //   444	84	9	localJSONObject1	JSONObject
    //   700	3	9	localException4	Exception
    //   1083	7	9	localException5	Exception
    //   1348	130	9	localObject2	Object
    //   1554	7	9	localException6	Exception
    //   1785	13	9	localJSONObject2	JSONObject
    //   1807	7	9	localException7	Exception
    //   1854	13	9	localJSONObject3	JSONObject
    //   1876	7	9	localException8	Exception
    //   1945	218	9	localObject3	Object
    //   2325	3	9	localException9	Exception
    //   2343	220	9	localObject4	Object
    //   2584	3	9	localException10	Exception
    //   441	1916	10	str1	String
    //   336	2034	11	localObject5	Object
    //   453	1930	12	localObject6	Object
    //   2066	330	13	str2	String
    //   2085	112	14	localObject7	Object
    //   2412	3	14	localException11	Exception
    //   2421	1	14	localObject8	Object
    //   2081	339	15	localObject9	Object
    //   1986	415	16	localJSONObject4	JSONObject
    // Exception table:
    //   from	to	target	type
    //   29	38	144	java/lang/Exception
    //   10	24	162	java/lang/Exception
    //   43	49	162	java/lang/Exception
    //   146	156	162	java/lang/Exception
    //   128	143	177	java/lang/Exception
    //   103	128	188	java/lang/Exception
    //   178	187	188	java/lang/Exception
    //   199	204	188	java/lang/Exception
    //   280	289	188	java/lang/Exception
    //   298	312	188	java/lang/Exception
    //   313	338	188	java/lang/Exception
    //   343	348	188	java/lang/Exception
    //   397	402	188	java/lang/Exception
    //   406	416	188	java/lang/Exception
    //   419	424	188	java/lang/Exception
    //   429	439	188	java/lang/Exception
    //   446	455	188	java/lang/Exception
    //   455	497	188	java/lang/Exception
    //   497	537	188	java/lang/Exception
    //   537	625	188	java/lang/Exception
    //   676	681	188	java/lang/Exception
    //   689	699	188	java/lang/Exception
    //   702	707	188	java/lang/Exception
    //   712	734	188	java/lang/Exception
    //   735	757	188	java/lang/Exception
    //   210	278	279	java/lang/Exception
    //   290	297	279	java/lang/Exception
    //   354	390	417	java/lang/Exception
    //   632	669	700	java/lang/Exception
    //   1025	1043	1044	java/lang/Exception
    //   911	929	1083	java/lang/Exception
    //   929	950	1083	java/lang/Exception
    //   957	1009	1083	java/lang/Exception
    //   1009	1025	1083	java/lang/Exception
    //   1055	1071	1083	java/lang/Exception
    //   1071	1080	1083	java/lang/Exception
    //   1098	1108	1083	java/lang/Exception
    //   1111	1121	1083	java/lang/Exception
    //   1151	1169	1170	java/lang/Exception
    //   1208	1226	1227	java/lang/Exception
    //   1325	1350	1453	java/lang/Exception
    //   1355	1396	1453	java/lang/Exception
    //   1396	1438	1453	java/lang/Exception
    //   1438	1452	1453	java/lang/Exception
    //   1459	1467	1453	java/lang/Exception
    //   1470	1479	1453	java/lang/Exception
    //   1523	1538	1554	java/lang/Exception
    //   1762	1787	1807	java/lang/Exception
    //   1792	1804	1807	java/lang/Exception
    //   1831	1856	1876	java/lang/Exception
    //   1861	1873	1876	java/lang/Exception
    //   1922	1947	2325	java/lang/Exception
    //   1952	1962	2325	java/lang/Exception
    //   1967	1988	2325	java/lang/Exception
    //   1993	2004	2325	java/lang/Exception
    //   2008	2019	2325	java/lang/Exception
    //   2023	2034	2325	java/lang/Exception
    //   2038	2049	2325	java/lang/Exception
    //   2053	2064	2325	java/lang/Exception
    //   2068	2079	2325	java/lang/Exception
    //   2151	2322	2325	java/lang/Exception
    //   2335	2345	2325	java/lang/Exception
    //   2348	2358	2325	java/lang/Exception
    //   2361	2371	2325	java/lang/Exception
    //   2374	2384	2325	java/lang/Exception
    //   2387	2397	2325	java/lang/Exception
    //   2400	2409	2325	java/lang/Exception
    //   2414	2419	2325	java/lang/Exception
    //   2096	2117	2412	java/lang/Exception
    //   2130	2151	2412	java/lang/Exception
    //   2436	2461	2584	java/lang/Exception
    //   2466	2476	2584	java/lang/Exception
    //   2481	2537	2584	java/lang/Exception
    //   2539	2578	2584	java/lang/Exception
    //   2578	2581	2584	java/lang/Exception
  }
  
  public void a(dh paramDh)
  {
    try
    {
      if (paramDh.FW().startsWith("room_")) {
        return;
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramDh);
      b(localArrayList, paramDh.FW().startsWith("group_"));
      return;
    }
    catch (Exception paramDh)
    {
      paramDh.printStackTrace();
    }
  }
  
  public void a(String paramString1, String paramString2, long paramLong)
  {
    try
    {
      com.zing.a.f.e.d(this.TAG, "message:" + paramString1);
      com.zing.a.f.e.d(this.TAG, "from:" + paramString2);
      paramString2 = com.zing.zalo.utils.b.e.nw(paramString2);
      com.zing.a.f.e.d(this.TAG, "from inter:" + paramString2);
      if (TextUtils.isEmpty(paramString2)) {
        return;
      }
      if (paramString2.startsWith(com.zing.zalo.utils.b.e.dmh)) {
        return;
      }
      if (!as.fP(false))
      {
        com.zing.a.f.e.d(this.TAG, "ignore sms socket do'nt contect");
        return;
      }
    }
    catch (Exception paramString1)
    {
      com.zing.a.f.e.b(this.TAG, paramString1);
      return;
    }
    paramString2 = cb.aJ(MainApplication.uw(), paramString2);
    if ((paramString2 == null) || (paramString2.FH() <= 0L))
    {
      com.zing.a.f.e.d(this.TAG, "ignore sms getPhoneContactByNumberIso null");
      return;
    }
    String str2 = String.valueOf(paramString2.FH());
    String str1 = paramString2.getDisplayName();
    Object localObject = com.zing.zalo.h.a.Px().PG().ef(str2);
    paramString2 = (String)localObject;
    if (localObject == null)
    {
      paramString2 = (String)localObject;
      if (com.zing.zalo.h.a.Px().PG().size() == 0)
      {
        com.zing.a.f.e.w(this.TAG, "processSmsMessage reload zalolist");
        com.zing.zalo.db.bj.HC().Ix();
        paramString2 = com.zing.zalo.h.a.Px().PG().ef(str2);
      }
    }
    if (paramString2 == null)
    {
      com.zing.a.f.e.d(this.TAG, "ignore sms profile null");
      return;
    }
    localObject = str1;
    if (TextUtils.isEmpty(str1))
    {
      localObject = str1;
      if (paramString2 != null) {
        localObject = paramString2.g(true, false);
      }
    }
    boolean bool = com.zing.zalo.h.a.Px().PK().ed(str2);
    if ((TextUtils.isEmpty(str2)) || (TextUtils.isEmpty((CharSequence)localObject)) || (bool))
    {
      com.zing.a.f.e.d(this.TAG, "ignore sms");
      return;
    }
    if (as.fP(false))
    {
      a(paramString1, (String)localObject, str2, paramLong, paramString2);
      return;
    }
    com.zing.a.f.e.d(this.TAG, "ignore sms socket do'nt contect");
  }
  
  public void a(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, long paramLong, String paramString5)
  {
    paramString1 = new bq(paramString1, paramString2, paramString3, paramInt, paramString4, paramLong, paramString5);
    if (MessagePopupActivity.adP() != null) {
      MessagePopupActivity.adP().add(paramString1);
    }
    paramString1 = new Intent();
    paramString1.setAction("com.zing.zalo.ui.MessagePopupActivityIntent");
    MainApplication.uw().sendBroadcast(paramString1);
  }
  
  /* Error */
  public void a(ArrayList<com.zing.a.d.a.a> paramArrayList, ArrayList<Long> paramArrayList1)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: new 241	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 242	java/util/ArrayList:<init>	()V
    //   10: astore 6
    //   12: aload_1
    //   13: ifnull +187 -> 200
    //   16: aload_1
    //   17: invokevirtual 374	java/util/ArrayList:size	()I
    //   20: ifle +180 -> 200
    //   23: new 2023	java/io/ByteArrayOutputStream
    //   26: dup
    //   27: invokespecial 2024	java/io/ByteArrayOutputStream:<init>	()V
    //   30: astore 7
    //   32: aload 7
    //   34: getstatic 2027	com/zing/a/a:dzl	Ljava/lang/String;
    //   37: invokevirtual 2031	java/lang/String:getBytes	()[B
    //   40: arraylength
    //   41: invokestatic 2037	com/zing/a/d/a/b:mG	(I)[B
    //   44: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   47: aload 7
    //   49: getstatic 2027	com/zing/a/a:dzl	Ljava/lang/String;
    //   52: invokevirtual 2031	java/lang/String:getBytes	()[B
    //   55: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   58: iconst_0
    //   59: istore_3
    //   60: aload_1
    //   61: invokevirtual 374	java/util/ArrayList:size	()I
    //   64: istore 5
    //   66: iload_3
    //   67: iload 5
    //   69: if_icmpge +74 -> 143
    //   72: aload 7
    //   74: aload_1
    //   75: iload_3
    //   76: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   79: checkcast 2394	com/zing/a/d/a/a
    //   82: invokevirtual 2397	com/zing/a/d/a/a:getUid	()I
    //   85: invokestatic 2400	com/zing/a/d/a/b:mH	(I)[B
    //   88: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   91: aload 7
    //   93: aload_1
    //   94: iload_3
    //   95: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   98: checkcast 2394	com/zing/a/d/a/a
    //   101: invokevirtual 2403	com/zing/a/d/a/a:awi	()J
    //   104: invokestatic 2406	com/zing/a/d/a/b:aY	(J)[B
    //   107: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   110: iload_3
    //   111: iconst_1
    //   112: iadd
    //   113: istore_3
    //   114: goto -54 -> 60
    //   117: astore 8
    //   119: aload 8
    //   121: invokevirtual 2407	java/lang/NumberFormatException:printStackTrace	()V
    //   124: goto -14 -> 110
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 364	java/lang/Exception:printStackTrace	()V
    //   132: return
    //   133: astore 8
    //   135: aload 8
    //   137: invokevirtual 2408	java/io/IOException:printStackTrace	()V
    //   140: goto -30 -> 110
    //   143: new 776	com/zing/a/d/a/e
    //   146: dup
    //   147: invokespecial 2009	com/zing/a/d/a/e:<init>	()V
    //   150: astore 8
    //   152: aload 8
    //   154: getstatic 352	com/zing/a/a:dzm	Ljava/lang/String;
    //   157: invokestatic 1726	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   160: invokevirtual 2012	com/zing/a/d/a/e:mM	(I)V
    //   163: aload 8
    //   165: iconst_3
    //   166: invokevirtual 2410	com/zing/a/d/a/e:h	(B)V
    //   169: aload 8
    //   171: bipush 117
    //   173: invokevirtual 2018	com/zing/a/d/a/e:a	(S)V
    //   176: aload 8
    //   178: iconst_0
    //   179: invokevirtual 2021	com/zing/a/d/a/e:i	(B)V
    //   182: aload 8
    //   184: aload 7
    //   186: invokevirtual 2046	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   189: invokevirtual 2049	com/zing/a/d/a/e:r	([B)V
    //   192: aload 6
    //   194: aload 8
    //   196: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   199: pop
    //   200: aload_2
    //   201: ifnull +163 -> 364
    //   204: aload_2
    //   205: invokevirtual 374	java/util/ArrayList:size	()I
    //   208: ifle +156 -> 364
    //   211: new 2023	java/io/ByteArrayOutputStream
    //   214: dup
    //   215: invokespecial 2024	java/io/ByteArrayOutputStream:<init>	()V
    //   218: astore 7
    //   220: aload 7
    //   222: getstatic 2027	com/zing/a/a:dzl	Ljava/lang/String;
    //   225: invokevirtual 2031	java/lang/String:getBytes	()[B
    //   228: arraylength
    //   229: invokestatic 2037	com/zing/a/d/a/b:mG	(I)[B
    //   232: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   235: aload 7
    //   237: getstatic 2027	com/zing/a/a:dzl	Ljava/lang/String;
    //   240: invokevirtual 2031	java/lang/String:getBytes	()[B
    //   243: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   246: iload 4
    //   248: istore_3
    //   249: aload_2
    //   250: invokevirtual 374	java/util/ArrayList:size	()I
    //   253: istore 4
    //   255: iload_3
    //   256: iload 4
    //   258: if_icmpge +49 -> 307
    //   261: aload 7
    //   263: aload_2
    //   264: iload_3
    //   265: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   268: checkcast 279	java/lang/Long
    //   271: invokevirtual 2413	java/lang/Long:longValue	()J
    //   274: invokestatic 2406	com/zing/a/d/a/b:aY	(J)[B
    //   277: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   280: iload_3
    //   281: iconst_1
    //   282: iadd
    //   283: istore_3
    //   284: goto -35 -> 249
    //   287: astore 8
    //   289: aload 8
    //   291: invokevirtual 2407	java/lang/NumberFormatException:printStackTrace	()V
    //   294: goto -14 -> 280
    //   297: astore 8
    //   299: aload 8
    //   301: invokevirtual 2408	java/io/IOException:printStackTrace	()V
    //   304: goto -24 -> 280
    //   307: new 776	com/zing/a/d/a/e
    //   310: dup
    //   311: invokespecial 2009	com/zing/a/d/a/e:<init>	()V
    //   314: astore 8
    //   316: aload 8
    //   318: getstatic 352	com/zing/a/a:dzm	Ljava/lang/String;
    //   321: invokestatic 1726	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   324: invokevirtual 2012	com/zing/a/d/a/e:mM	(I)V
    //   327: aload 8
    //   329: iconst_3
    //   330: invokevirtual 2410	com/zing/a/d/a/e:h	(B)V
    //   333: aload 8
    //   335: bipush 116
    //   337: invokevirtual 2018	com/zing/a/d/a/e:a	(S)V
    //   340: aload 8
    //   342: iconst_0
    //   343: invokevirtual 2021	com/zing/a/d/a/e:i	(B)V
    //   346: aload 8
    //   348: aload 7
    //   350: invokevirtual 2046	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   353: invokevirtual 2049	com/zing/a/d/a/e:r	([B)V
    //   356: aload 6
    //   358: aload 8
    //   360: invokevirtual 286	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   363: pop
    //   364: aload 6
    //   366: invokevirtual 374	java/util/ArrayList:size	()I
    //   369: ifle -237 -> 132
    //   372: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   375: invokestatic 2418	com/zing/a/c/a:hx	(Landroid/content/Context;)Z
    //   378: ifeq -246 -> 132
    //   381: aload_0
    //   382: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   385: new 114	java/lang/StringBuilder
    //   388: dup
    //   389: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   392: ldc_w 2420
    //   395: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: aload_1
    //   399: invokevirtual 374	java/util/ArrayList:size	()I
    //   402: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   405: ldc_w 2422
    //   408: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: aload_2
    //   412: invokevirtual 374	java/util/ArrayList:size	()I
    //   415: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   418: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   421: invokestatic 1930	com/zing/a/f/e:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   424: pop
    //   425: new 1990	com/zing/zalo/e/af
    //   428: dup
    //   429: aconst_null
    //   430: invokespecial 1993	com/zing/zalo/e/af:<init>	(Lcom/zing/a/d/c;)V
    //   433: astore_1
    //   434: aload_1
    //   435: iconst_5
    //   436: putfield 1994	com/zing/zalo/e/af:type	I
    //   439: aload_1
    //   440: getstatic 2000	com/zing/zalo/e/ai:aNT	Lcom/zing/zalo/e/ai;
    //   443: invokestatic 2005	com/zing/zalo/e/ag:a	(Lcom/zing/zalo/e/ai;)Ljava/lang/String;
    //   446: putfield 2008	com/zing/zalo/e/af:wG	Ljava/lang/String;
    //   449: aload_1
    //   450: aload 6
    //   452: invokevirtual 2052	com/zing/zalo/e/af:K	(Ljava/util/ArrayList;)V
    //   455: aload_1
    //   456: invokestatic 2057	com/zing/zalo/r/aa:c	(Lcom/zing/zalo/e/af;)V
    //   459: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	460	0	this	b
    //   0	460	1	paramArrayList	ArrayList<com.zing.a.d.a.a>
    //   0	460	2	paramArrayList1	ArrayList<Long>
    //   59	225	3	i	int
    //   1	258	4	j	int
    //   64	6	5	k	int
    //   10	441	6	localArrayList	ArrayList
    //   30	319	7	localByteArrayOutputStream	ByteArrayOutputStream
    //   117	3	8	localNumberFormatException1	NumberFormatException
    //   133	3	8	localIOException1	java.io.IOException
    //   150	45	8	localE1	com.zing.a.d.a.e
    //   287	3	8	localNumberFormatException2	NumberFormatException
    //   297	3	8	localIOException2	java.io.IOException
    //   314	45	8	localE2	com.zing.a.d.a.e
    // Exception table:
    //   from	to	target	type
    //   72	110	117	java/lang/NumberFormatException
    //   3	12	127	java/lang/Exception
    //   16	58	127	java/lang/Exception
    //   60	66	127	java/lang/Exception
    //   72	110	127	java/lang/Exception
    //   119	124	127	java/lang/Exception
    //   135	140	127	java/lang/Exception
    //   143	200	127	java/lang/Exception
    //   204	246	127	java/lang/Exception
    //   249	255	127	java/lang/Exception
    //   261	280	127	java/lang/Exception
    //   289	294	127	java/lang/Exception
    //   299	304	127	java/lang/Exception
    //   307	364	127	java/lang/Exception
    //   364	459	127	java/lang/Exception
    //   72	110	133	java/io/IOException
    //   261	280	287	java/lang/NumberFormatException
    //   261	280	297	java/io/IOException
  }
  
  /* Error */
  public void a(ArrayList<com.zing.a.d.a.a> paramArrayList, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iload_3
    //   3: aload_1
    //   4: invokevirtual 374	java/util/ArrayList:size	()I
    //   7: if_icmpge +349 -> 356
    //   10: aload_1
    //   11: iload_3
    //   12: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   15: checkcast 2394	com/zing/a/d/a/a
    //   18: invokevirtual 2397	com/zing/a/d/a/a:getUid	()I
    //   21: istore 5
    //   23: aload_1
    //   24: iload_3
    //   25: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   28: checkcast 2394	com/zing/a/d/a/a
    //   31: invokevirtual 2403	com/zing/a/d/a/a:awi	()J
    //   34: lstore 6
    //   36: invokestatic 87	com/zing/zalo/control/ek:GK	()Lcom/zing/zalo/control/ek;
    //   39: new 114	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   46: ldc 117
    //   48: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: iload 5
    //   53: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   56: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: invokevirtual 170	com/zing/zalo/control/ek:eE	(Ljava/lang/String;)Lcom/zing/zalo/control/c;
    //   62: astore 8
    //   64: aload 8
    //   66: ifnull +220 -> 286
    //   69: aload 8
    //   71: invokevirtual 97	com/zing/zalo/control/c:Cb	()Ljava/util/List;
    //   74: astore 9
    //   76: aload 9
    //   78: ifnull +231 -> 309
    //   81: aload 9
    //   83: invokeinterface 103 1 0
    //   88: istore 4
    //   90: iload 4
    //   92: iconst_1
    //   93: isub
    //   94: istore 4
    //   96: iload 4
    //   98: iflt +211 -> 309
    //   101: aload 9
    //   103: iload 4
    //   105: invokeinterface 107 2 0
    //   110: ifnull +247 -> 357
    //   113: new 114	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   120: ldc 117
    //   122: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: lload 6
    //   127: invokevirtual 124	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   130: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: aload 9
    //   135: iload 4
    //   137: invokeinterface 107 2 0
    //   142: checkcast 109	com/zing/zalo/control/g
    //   145: getfield 112	com/zing/zalo/control/g:aOY	Ljava/lang/String;
    //   148: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifeq +206 -> 357
    //   154: iload_2
    //   155: ifeq +64 -> 219
    //   158: aload 9
    //   160: iload 4
    //   162: invokeinterface 107 2 0
    //   167: checkcast 109	com/zing/zalo/control/g
    //   170: bipush 17
    //   172: invokevirtual 137	com/zing/zalo/control/g:setState	(I)V
    //   175: invokestatic 296	com/zing/zalo/h/a:Px	()Lcom/zing/zalo/h/a;
    //   178: new 114	java/lang/StringBuilder
    //   181: dup
    //   182: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   185: iload 5
    //   187: invokevirtual 1454	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   190: ldc 117
    //   192: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: aload 9
    //   200: iload 4
    //   202: invokeinterface 107 2 0
    //   207: checkcast 109	com/zing/zalo/control/g
    //   210: getfield 598	com/zing/zalo/control/g:timestamp	J
    //   213: invokevirtual 300	com/zing/zalo/h/a:f	(Ljava/lang/String;J)V
    //   216: goto +141 -> 357
    //   219: aload 9
    //   221: iload 4
    //   223: invokeinterface 107 2 0
    //   228: checkcast 109	com/zing/zalo/control/g
    //   231: invokevirtual 2426	com/zing/zalo/control/g:getState	()I
    //   234: bipush 17
    //   236: if_icmpeq -61 -> 175
    //   239: aload 9
    //   241: iload 4
    //   243: invokeinterface 107 2 0
    //   248: checkcast 109	com/zing/zalo/control/g
    //   251: bipush 13
    //   253: invokevirtual 137	com/zing/zalo/control/g:setState	(I)V
    //   256: goto -81 -> 175
    //   259: astore 10
    //   261: aload_0
    //   262: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   265: aload 10
    //   267: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   270: pop
    //   271: goto +86 -> 357
    //   274: astore 8
    //   276: aload_0
    //   277: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   280: aload 8
    //   282: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   285: pop
    //   286: iload_2
    //   287: ifeq +44 -> 331
    //   290: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   293: lload 6
    //   295: bipush 17
    //   297: invokeinterface 2428 4 0
    //   302: iload_3
    //   303: iconst_1
    //   304: iadd
    //   305: istore_3
    //   306: goto -304 -> 2
    //   309: invokestatic 143	com/zing/zalo/d/b/a:yY	()Lcom/zing/zalo/d/b/a;
    //   312: iconst_1
    //   313: iconst_1
    //   314: anewarray 4	java/lang/Object
    //   317: dup
    //   318: iconst_0
    //   319: aload 8
    //   321: invokevirtual 953	com/zing/zalo/control/c:Cd	()Ljava/lang/String;
    //   324: aastore
    //   325: invokevirtual 147	com/zing/zalo/d/b/a:b	(I[Ljava/lang/Object;)V
    //   328: goto -42 -> 286
    //   331: invokestatic 159	com/zing/zalo/db/m:Hv	()Lcom/zing/zalo/db/l;
    //   334: lload 6
    //   336: bipush 13
    //   338: invokeinterface 2428 4 0
    //   343: goto -41 -> 302
    //   346: astore_1
    //   347: aload_0
    //   348: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   351: aload_1
    //   352: invokestatic 210	com/zing/a/f/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   355: pop
    //   356: return
    //   357: iload 4
    //   359: iconst_1
    //   360: isub
    //   361: istore 4
    //   363: goto -267 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	366	0	this	b
    //   0	366	1	paramArrayList	ArrayList<com.zing.a.d.a.a>
    //   0	366	2	paramBoolean	boolean
    //   1	305	3	i	int
    //   88	274	4	j	int
    //   21	165	5	k	int
    //   34	301	6	l	long
    //   62	8	8	localC	com.zing.zalo.control.c
    //   274	46	8	localException1	Exception
    //   74	166	9	localList	List
    //   259	7	10	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   101	154	259	java/lang/Exception
    //   158	175	259	java/lang/Exception
    //   175	216	259	java/lang/Exception
    //   219	256	259	java/lang/Exception
    //   36	64	274	java/lang/Exception
    //   69	76	274	java/lang/Exception
    //   81	90	274	java/lang/Exception
    //   261	271	274	java/lang/Exception
    //   309	328	274	java/lang/Exception
    //   2	36	346	java/lang/Exception
    //   276	286	346	java/lang/Exception
    //   290	302	346	java/lang/Exception
    //   331	343	346	java/lang/Exception
  }
  
  public void b(com.zing.zalo.control.g paramG, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    try
    {
      if (com.zing.zalo.f.a.aXr.size() == 0)
      {
        l2 = com.zing.zalo.db.m.Hv().Hj();
        com.zing.zalo.db.m.Hv().Z(l2);
      }
      bool = paramBoolean;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          boolean bool;
          if (paramG.CZ())
          {
            bool = paramBoolean;
            if (!paramG.aOW.startsWith("room_"))
            {
              bool = paramBoolean;
              if (!paramG.aOW.startsWith("wifi_room_"))
              {
                bool = paramBoolean;
                if (paramG.aOV.equals(com.zing.a.a.dzm)) {
                  bool = false;
                }
              }
            }
          }
          paramG.bl(bool);
          paramG.state = -1;
          if (!paramG.aOW.startsWith("room_")) {
            break;
          }
          m(paramG);
          return;
        }
        catch (Exception paramG)
        {
          long l2 = System.currentTimeMillis();
          com.zing.a.e.g.a(com.zing.a.a.dzm, 15002, paramG.toString(), l2 - l1, 15000, com.zing.a.a.versionCode);
          com.zing.a.f.e.b(this.TAG, paramG);
          com.zing.a.f.e.i("notificationListener", paramG.toString());
          return;
        }
        localException = localException;
        com.zing.a.f.e.b(this.TAG, localException);
      }
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(com.zing.a.a.dzm);
    ((StringBuilder)localObject).append(paramG.aOY);
    ((StringBuilder)localObject).append(paramG.aOV);
    localObject = ((StringBuilder)localObject).toString();
    if (paramG.aOW.startsWith("wifi_room_"))
    {
      if (!com.zing.zalo.f.a.aWV.containsKey(localObject))
      {
        c(paramG, true);
        dH((String)localObject);
      }
    }
    else if (!com.zing.zalo.f.a.aWU.containsKey(localObject))
    {
      com.zing.zalo.db.m.Hv().a(paramG, (String)localObject);
      c(paramG, true);
    }
  }
  
  public void b(ArrayList<dh> paramArrayList, boolean paramBoolean)
  {
    try
    {
      r localR = new r();
      localR.a(new l(this, paramArrayList));
      if (!paramBoolean) {
        localR.i(paramArrayList);
      }
      for (;;)
      {
        a(paramArrayList, 1, true);
        return;
        localR.k(paramArrayList);
      }
      return;
    }
    catch (Exception paramArrayList)
    {
      paramArrayList.printStackTrace();
    }
  }
  
  public void c(com.zing.a.d.a.e paramE)
  {
    com.zing.zalo.r.c.b(new v(this, paramE));
  }
  
  public void c(com.zing.zalo.control.g paramG, boolean paramBoolean)
  {
    bk localBk;
    try
    {
      localBk = new bk(paramG.aOW);
      ek.GK().j(localBk).p(paramG);
      com.zing.zalo.c.aa.xi().c(localBk.aOC, paramG);
      boolean bool = localBk.EF();
      if (!bool) {}
      try
      {
        if (paramG.aPu != null) {
          com.zing.zalo.d.a.xr().a(paramG.aPu, "" + paramG.timestamp);
        }
        if (localBk.EF())
        {
          cp localCp = com.zing.zalo.f.c.dk(MainApplication.uw());
          if ((localCp == null) || (!("room_" + localCp.Dp).equals(localBk.aOC))) {
            break label554;
          }
          localBk.aRW = localCp.name;
          b(localBk, paramG, paramBoolean, false);
          return;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          com.zing.a.f.e.b(this.TAG, localException);
        }
      }
      if (!localBk.EG()) {
        break label208;
      }
    }
    catch (Exception paramG)
    {
      com.zing.a.f.e.b(this.TAG, paramG);
      return;
    }
    b(localBk, paramG, paramBoolean, false);
    return;
    label208:
    int i;
    label297:
    int j;
    label348:
    String str;
    if (localBk.Cn())
    {
      localObject = localBk.aOC.split("_");
      if ((localObject == null) || (localObject.length <= 0)) {
        break label548;
      }
      localObject = localObject[(localObject.length - 1)];
      localObject = com.zing.zalo.db.bj.HC().fg((String)localObject);
      if (localObject == null)
      {
        b(localBk, paramG, paramBoolean, true);
        a(localBk, paramG, paramBoolean, false);
        return;
      }
      if (TextUtils.isEmpty(((cg)localObject).getName())) {
        break label555;
      }
      i = 1;
      if ((((cg)localObject).EX() == null) || (((cg)localObject).EX().isEmpty()) || (!((cg)localObject).EX().contains(com.zing.a.a.dzm)) || (!((cg)localObject).EX().contains(paramG.aOV))) {
        break label542;
      }
      j = 1;
      if ((i != 0) && (j != 0))
      {
        localBk.aRW = ((cg)localObject).getName();
        localBk.aRZ = ((cg)localObject).Fb();
        b(localBk, paramG, paramBoolean, false);
        return;
      }
      if (((cg)localObject).getName() == null) {
        break label560;
      }
      str = ((cg)localObject).getName();
      label402:
      localBk.aRW = str;
      if (((cg)localObject).Fb() == null) {
        break label567;
      }
    }
    label542:
    label548:
    label554:
    label555:
    label560:
    label567:
    for (Object localObject = ((cg)localObject).Fb();; localObject = "")
    {
      localBk.aRZ = ((String)localObject);
      b(localBk, paramG, paramBoolean, true);
      a(localBk, paramG, paramBoolean, true);
      return;
      localObject = com.zing.zalo.d.v.yc().du(paramG.aOW);
      if ((localObject == null) || (((bk)localObject).aOC.length() == 0) || (((bk)localObject).aOC.equalsIgnoreCase("null")) || (((bk)localObject).aRZ.equals("")))
      {
        localBk.aRW = paramG.aEO;
        b(localBk, paramG, paramBoolean, true);
        dI(paramG.aOW);
        return;
      }
      b((bk)localObject, paramG, paramBoolean, false);
      return;
      j = 0;
      break label348;
      localObject = null;
      break;
      return;
      i = 0;
      break label297;
      str = "";
      break label402;
    }
  }
  
  public void d(long paramLong, String paramString)
  {
    try
    {
      af localAf = new af(null);
      localAf.gt(true);
      localAf.type = 2;
      Object localObject = paramString.getBytes("UTF-8");
      paramString = new ByteArrayOutputStream();
      paramString.write(com.zing.a.d.a.b.aY(paramLong));
      paramString.write(com.zing.a.d.a.b.mG(localObject.length));
      paramString.write((byte[])localObject);
      localObject = new com.zing.a.d.a.e();
      ((com.zing.a.d.a.e)localObject).d((byte)1);
      ((com.zing.a.d.a.e)localObject).g((byte)0);
      ((com.zing.a.d.a.e)localObject).mM(Integer.parseInt(com.zing.a.a.dzm));
      ((com.zing.a.d.a.e)localObject).h((byte)3);
      ((com.zing.a.d.a.e)localObject).a((short)91);
      ((com.zing.a.d.a.e)localObject).i((byte)0);
      ((com.zing.a.d.a.e)localObject).r(paramString.toByteArray());
      localAf.i((com.zing.a.d.a.e)localObject);
      if (com.zing.a.c.a.hx(MainApplication.uw())) {
        com.zing.zalo.r.af.c(localAf);
      }
      return;
    }
    catch (Exception paramString)
    {
      com.zing.a.f.e.e(this.TAG, paramString.toString());
    }
  }
  
  public void l(JSONObject paramJSONObject)
  {
    for (;;)
    {
      int i;
      try
      {
        new ArrayList().clear();
        ArrayList localArrayList = com.zing.zalo.db.bj.HC().IB();
        if (localArrayList.size() > 0)
        {
          localArrayList = bm.c(localArrayList, bm.gW(MainApplication.uw()));
          i = 0;
          if (i < localArrayList.size())
          {
            Object localObject = (dd)localArrayList.get(i);
            if (!((dd)localObject).getPackageName().equals("com.zing.zalo.mp3game")) {
              break label207;
            }
            if (((dd)localObject).FS())
            {
              localObject = new Intent("com.zing.zalo.giaidieuvui.intent.NOTIFCATION");
              ((Intent)localObject).putExtra("com.zing.zalo.giaidieuvui.extra.notification", paramJSONObject.toString());
              MainApplication.uw().sendBroadcast((Intent)localObject);
            }
            else
            {
              String str = paramJSONObject.getString("fromD");
              int j = new JSONObject(paramJSONObject.getString("msg")).getInt("type");
              if ((j == 0) || (j == 1))
              {
                str = String.format(MainApplication.uw().getString(2131166906), new Object[] { str });
                com.zing.zalo.d.o.xR().U(str, ((dd)localObject).getPackageName());
              }
            }
          }
        }
      }
      catch (Exception paramJSONObject)
      {
        com.zing.a.f.e.b(this.TAG, paramJSONObject);
      }
      return;
      label207:
      i += 1;
    }
  }
  
  public void m(com.zing.zalo.control.g paramG)
  {
    if (com.zing.zalo.f.a.aYw == -1) {
      if ((com.zing.zalo.f.a.aXC) || (System.currentTimeMillis() - aLQ <= 20000L)) {}
    }
    label305:
    for (;;)
    {
      int i;
      Object localObject;
      try
      {
        paramG = paramG.aOW.split("_");
        if ((paramG != null) && (paramG.length > 0))
        {
          i = Integer.parseInt(paramG[(paramG.length - 1)]);
          aLQ = System.currentTimeMillis();
          eW(i);
          if ((ChatFragment.ahI() != null) && (ChatFragment.ahI().aQb != null))
          {
            localObject = ChatFragment.ahI().aQb.Cc();
            if (("room_" + i).equals(((bk)localObject).aOC)) {
              ChatFragment.ahI().aiG();
            }
            paramG = new bk(2, paramG[(paramG.length - 1)], null);
            ek.GK().f(paramG);
          }
        }
        return;
      }
      catch (Exception paramG)
      {
        com.zing.a.f.e.b(this.TAG, paramG);
        return;
      }
      if (com.zing.zalo.f.a.aYw > 0)
      {
        localObject = ek.GK().eE(paramG.aOW);
        if (localObject != null)
        {
          localObject = ((com.zing.zalo.control.c)localObject).Cb();
          if (localObject != null)
          {
            i = ((List)localObject).size() - 1;
            int j = 0;
            if (i >= 0) {
              if (paramG.timestamp == ((com.zing.zalo.control.g)((List)localObject).get(i)).timestamp) {
                i = 1;
              }
            }
            for (;;)
            {
              if (i != 0) {
                break label305;
              }
              c(paramG, true);
              com.zing.a.f.e.i("notificationListener", "" + paramG.timestamp);
              return;
              j += 1;
              if (j >= 5)
              {
                i = 0;
              }
              else
              {
                i -= 1;
                break;
                i = 0;
              }
            }
          }
        }
      }
    }
  }
  
  /* Error */
  public void m(ArrayList<dn> paramArrayList)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 374	java/util/ArrayList:size	()I
    //   4: ifle +103 -> 107
    //   7: new 1990	com/zing/zalo/e/af
    //   10: dup
    //   11: aconst_null
    //   12: invokespecial 1993	com/zing/zalo/e/af:<init>	(Lcom/zing/a/d/c;)V
    //   15: astore 4
    //   17: aload 4
    //   19: iconst_1
    //   20: invokevirtual 2512	com/zing/zalo/e/af:gt	(Z)V
    //   23: aload 4
    //   25: iconst_2
    //   26: putfield 1994	com/zing/zalo/e/af:type	I
    //   29: new 2023	java/io/ByteArrayOutputStream
    //   32: dup
    //   33: invokespecial 2024	java/io/ByteArrayOutputStream:<init>	()V
    //   36: astore 5
    //   38: iconst_0
    //   39: istore_2
    //   40: aload_1
    //   41: invokevirtual 374	java/util/ArrayList:size	()I
    //   44: istore_3
    //   45: iload_2
    //   46: iload_3
    //   47: if_icmpge +79 -> 126
    //   50: aload 5
    //   52: aload_1
    //   53: iload_2
    //   54: invokevirtual 679	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   57: checkcast 1920	com/zing/zalo/control/dn
    //   60: invokevirtual 2564	com/zing/zalo/control/dn:getId	()J
    //   63: invokestatic 2406	com/zing/a/d/a/b:aY	(J)[B
    //   66: invokevirtual 2041	java/io/ByteArrayOutputStream:write	([B)V
    //   69: iload_2
    //   70: iconst_1
    //   71: iadd
    //   72: istore_2
    //   73: goto -33 -> 40
    //   76: astore 6
    //   78: aload_0
    //   79: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   82: aload 6
    //   84: invokevirtual 2565	java/lang/NumberFormatException:toString	()Ljava/lang/String;
    //   87: invokestatic 2128	com/zing/a/f/e:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   90: pop
    //   91: goto -22 -> 69
    //   94: astore_1
    //   95: aload_0
    //   96: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   99: aload_1
    //   100: invokevirtual 353	java/lang/Exception:toString	()Ljava/lang/String;
    //   103: invokestatic 2128	com/zing/a/f/e:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   106: pop
    //   107: return
    //   108: astore 6
    //   110: aload_0
    //   111: getfield 42	com/zing/zalo/e/b:TAG	Ljava/lang/String;
    //   114: aload 6
    //   116: invokevirtual 2566	java/io/IOException:toString	()Ljava/lang/String;
    //   119: invokestatic 2128	com/zing/a/f/e:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   122: pop
    //   123: goto -54 -> 69
    //   126: new 776	com/zing/a/d/a/e
    //   129: dup
    //   130: invokespecial 2009	com/zing/a/d/a/e:<init>	()V
    //   133: astore_1
    //   134: aload_1
    //   135: iconst_1
    //   136: invokevirtual 2518	com/zing/a/d/a/e:d	(B)V
    //   139: aload_1
    //   140: iconst_0
    //   141: invokevirtual 2520	com/zing/a/d/a/e:g	(B)V
    //   144: aload_1
    //   145: getstatic 352	com/zing/a/a:dzm	Ljava/lang/String;
    //   148: invokestatic 1726	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   151: invokevirtual 2012	com/zing/a/d/a/e:mM	(I)V
    //   154: aload_1
    //   155: iconst_3
    //   156: invokevirtual 2410	com/zing/a/d/a/e:h	(B)V
    //   159: aload_1
    //   160: bipush 90
    //   162: invokevirtual 2018	com/zing/a/d/a/e:a	(S)V
    //   165: aload_1
    //   166: iconst_0
    //   167: invokevirtual 2021	com/zing/a/d/a/e:i	(B)V
    //   170: aload_1
    //   171: aload 5
    //   173: invokevirtual 2046	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   176: invokevirtual 2049	com/zing/a/d/a/e:r	([B)V
    //   179: aload 4
    //   181: aload_1
    //   182: invokevirtual 2522	com/zing/zalo/e/af:i	(Lcom/zing/a/d/a/e;)V
    //   185: invokestatic 312	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   188: invokestatic 2418	com/zing/a/c/a:hx	(Landroid/content/Context;)Z
    //   191: ifeq -84 -> 107
    //   194: aload 4
    //   196: invokestatic 2525	com/zing/zalo/r/af:c	(Lcom/zing/zalo/e/af;)V
    //   199: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	b
    //   0	200	1	paramArrayList	ArrayList<dn>
    //   39	34	2	i	int
    //   44	4	3	j	int
    //   15	180	4	localAf	af
    //   36	136	5	localByteArrayOutputStream	ByteArrayOutputStream
    //   76	7	6	localNumberFormatException	NumberFormatException
    //   108	7	6	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   50	69	76	java/lang/NumberFormatException
    //   0	38	94	java/lang/Exception
    //   40	45	94	java/lang/Exception
    //   50	69	94	java/lang/Exception
    //   78	91	94	java/lang/Exception
    //   110	123	94	java/lang/Exception
    //   126	199	94	java/lang/Exception
    //   50	69	108	java/io/IOException
  }
  
  public void m(JSONObject paramJSONObject)
  {
    com.zing.zalo.r.c.b(new o(this, paramJSONObject));
  }
  
  public void n(ArrayList<dn> paramArrayList)
  {
    int i = 0;
    for (;;)
    {
      boolean bool2;
      try
      {
        j = paramArrayList.size();
        if (i < j)
        {
          try
          {
            localDn = (dn)paramArrayList.get(i);
            localObject2 = de.ev(localDn.Gh());
            if (localObject2 == null) {
              break label1795;
            }
            if (!((ArrayList)localObject2).isEmpty()) {
              break label1813;
            }
            bool1 = true;
            bool2 = bool1;
            if (localDn.getType() != 13) {
              break label1801;
            }
            j = new com.zing.zalo.control.b(new JSONObject(localDn.getBody())).BR();
            bool2 = bool1;
            if (com.zing.zalo.db.m.Hv().fY(1).size() >= j) {
              break label1801;
            }
            bool2 = false;
          }
          catch (Exception localException1)
          {
            Intent localIntent;
            break label1806;
          }
          if (j >= ((ArrayList)localObject2).size()) {
            break label1792;
          }
          localObject3 = (de)((ArrayList)localObject2).get(j);
          if (localObject3 != null) {
            if (((de)localObject3).fF() == 1)
            {
              localIntent = null;
              if (!TextUtils.isEmpty(((de)localObject3).FU())) {
                localIntent = MainApplication.uw().getPackageManager().getLaunchIntentForPackage(((de)localObject3).FU());
              }
              if (((de)localObject3).FT() == 1)
              {
                if (localIntent == null) {
                  break label1828;
                }
                bool1 = true;
                break label1821;
              }
              if (((de)localObject3).FT() == 0)
              {
                if (localIntent != null) {
                  break label1834;
                }
                bool1 = true;
                break label1821;
              }
            }
            else
            {
              if (((de)localObject3).fF() != 4) {
                break label1846;
              }
              bool2 = com.zing.zalo.db.bj.HC().gg(((de)localObject3).FV());
              if (((de)localObject3).FT() == 1)
              {
                bool1 = bool2;
                break label1821;
              }
              if (((de)localObject3).FT() == 0)
              {
                if (bool2) {
                  break label1840;
                }
                bool1 = true;
                break label1821;
                com.zing.zalo.db.bj.HC().gj(localDn.getType());
                com.zing.zalo.i.g.bJS.set(false);
                com.zing.zalo.i.g.bJT.set(false);
                break label1806;
              }
            }
          }
        }
        else
        {
          i = 0;
          j = paramArrayList.size();
          if (i >= j) {}
        }
      }
      catch (Exception paramArrayList)
      {
        dn localDn;
        Object localObject2;
        Object localObject3;
        Object localObject1;
        int m;
        com.zing.zalo.stickers.a localA;
        com.zing.a.f.e.e(this.TAG, paramArrayList.toString());
      }
      try
      {
        localDn = (dn)paramArrayList.get(i);
        localObject2 = de.ev(localDn.Gh());
        if (localObject2 == null) {
          break label1783;
        }
        if (!((ArrayList)localObject2).isEmpty()) {
          break label1859;
        }
        bool1 = true;
        bool2 = bool1;
        if (localDn.getType() == 13)
        {
          j = new com.zing.zalo.control.b(new JSONObject(localDn.getBody())).BR();
          bool2 = bool1;
          if (com.zing.zalo.db.m.Hv().fY(1).size() < j) {
            bool2 = false;
          }
        }
        if (!bool2)
        {
          if (localDn.getType() != 10) {
            break label1852;
          }
          com.zing.zalo.actionlog.b.C(com.zing.zalo.actionlog.a.a.aon.ul(), "");
          com.zing.zalo.actionlog.b.ua();
          break label1852;
          if (j >= ((ArrayList)localObject2).size()) {
            break label1780;
          }
          localObject3 = (de)((ArrayList)localObject2).get(j);
          if (localObject3 == null) {
            break label1777;
          }
          if (((de)localObject3).fF() == 1)
          {
            localObject1 = null;
            if (!TextUtils.isEmpty(((de)localObject3).FU())) {
              localObject1 = MainApplication.uw().getPackageManager().getLaunchIntentForPackage(((de)localObject3).FU());
            }
            if (((de)localObject3).FT() == 1)
            {
              if (localObject1 == null) {
                break label1874;
              }
              bool1 = true;
              break label1867;
            }
            if (((de)localObject3).FT() != 0) {
              break label1777;
            }
            if (localObject1 != null) {
              break label1880;
            }
            bool1 = true;
            break label1867;
          }
          if (((de)localObject3).fF() != 4) {
            break label1892;
          }
          bool2 = com.zing.zalo.db.bj.HC().gg(((de)localObject3).FV());
          if (((de)localObject3).FT() == 1)
          {
            bool1 = bool2;
            break label1867;
          }
          if (((de)localObject3).FT() != 0) {
            break label1777;
          }
          if (bool2) {
            break label1886;
          }
          bool1 = true;
          break label1867;
        }
        if (localDn.getType() == 10)
        {
          com.zing.zalo.actionlog.b.C(com.zing.zalo.actionlog.a.a.aom.ul(), "");
          com.zing.zalo.actionlog.b.ua();
        }
        if (localDn.getType() == 10)
        {
          com.zing.zalo.db.bj.HC().a(localDn);
          com.zing.zalo.i.g.bJS.set(false);
          bm.arb();
          break label1852;
        }
        if (localDn.getType() == 11)
        {
          com.zing.zalo.db.bj.HC().Je();
          com.zing.zalo.f.c.J(MainApplication.uw(), false);
          com.zing.zalo.f.c.K(MainApplication.uw(), false);
          com.zing.zalo.f.c.L(MainApplication.uw(), false);
          com.zing.zalo.i.g.bJS.set(false);
          com.zing.zalo.i.g.bJT.set(false);
          bm.arb();
          bm.arc();
          bm.ard();
          break label1852;
        }
        if (localDn.getType() == 61)
        {
          localObject1 = localDn.getBody();
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label1852;
          }
          localObject1 = new com.zing.zalo.control.b(new JSONObject((String)localObject1));
          com.zing.zalo.db.bj.HC().gj(((com.zing.zalo.control.b)localObject1).BM());
          if (((com.zing.zalo.control.b)localObject1).getType() == 12) {
            com.zing.zalo.f.c.J(MainApplication.uw(), false);
          }
          if (((com.zing.zalo.control.b)localObject1).getType() == 51)
          {
            com.zing.zalo.f.c.K(MainApplication.uw(), false);
            com.zing.zalo.f.c.L(MainApplication.uw(), false);
          }
          com.zing.zalo.i.g.bJS.set(false);
          com.zing.zalo.i.g.bJT.set(false);
          bm.arb();
          bm.arc();
          bm.ard();
          break label1852;
        }
        if (localDn.getType() == 12)
        {
          com.zing.zalo.db.bj.HC().a(localDn);
          com.zing.zalo.f.c.J(MainApplication.uw(), true);
          break label1852;
        }
        if (localDn.getType() == 51)
        {
          com.zing.zalo.db.bj.HC().a(localDn);
          com.zing.zalo.f.c.K(MainApplication.uw(), true);
          bm.arc();
          if ((localDn.getBody() == null) || (localDn.getBody().equals("")) || (new com.zing.zalo.control.b(new JSONObject(localDn.getBody())).BO() != 1)) {
            break label1852;
          }
          com.zing.zalo.f.c.L(MainApplication.uw(), true);
          bm.ard();
          break label1852;
        }
        if (localDn.getType() != 20) {
          continue;
        }
        localObject1 = new com.zing.zalo.control.b(new JSONObject(localDn.getBody()));
        if (((com.zing.zalo.control.b)localObject1).getType() != 1) {
          break label1852;
        }
        localObject1 = new JSONObject(((com.zing.zalo.control.b)localObject1).BK());
        if (((JSONObject)localObject1).isNull("pkgname")) {
          break;
        }
        localObject1 = ((JSONObject)localObject1).getString("pkgname");
      }
      catch (Exception localException2)
      {
        label1777:
        label1780:
        label1783:
        label1792:
        label1795:
        label1801:
        label1806:
        label1813:
        label1821:
        label1828:
        label1834:
        label1840:
        label1846:
        label1852:
        for (;;) {}
        String str = "";
        continue;
        j = 0;
        continue;
        k = 0;
        continue;
        j += 1;
        continue;
      }
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        if (MainApplication.uw().getPackageManager().getLaunchIntentForPackage((String)localObject1) == null) {
          break label1910;
        }
        j = 1;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("{");
        ((StringBuilder)localObject2).append("\"pkgname\":").append(JSONObject.quote((String)localObject1)).append(",");
        ((StringBuilder)localObject2).append("\"isinstalled\":").append(j);
        ((StringBuilder)localObject2).append("}");
        localObject1 = ((StringBuilder)localObject2).toString();
        if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localDn.Gi() == 1))
        {
          d(localDn.getId(), (String)localObject1);
          break label1852;
          if (localDn.getType() == 22)
          {
            localObject1 = new com.zing.zalo.control.b(new JSONObject(localDn.getBody()));
            if (((com.zing.zalo.control.b)localObject1).getType() == 1)
            {
              localObject1 = new JSONObject(((com.zing.zalo.control.b)localObject1).BK());
              if (!((JSONObject)localObject1).isNull("pkgname"))
              {
                localObject1 = ((JSONObject)localObject1).getString("pkgname");
                localObject1 = ((String)localObject1).split("\\|");
                localObject2 = new StringBuilder();
                if (localObject1.length <= 0) {
                  continue;
                }
                ((StringBuilder)localObject2).append("[");
                m = localObject1.length;
                j = 0;
                if (j >= m) {}
              }
              else
              {
                try
                {
                  localObject3 = localObject1[j].trim();
                  if (!TextUtils.isEmpty((CharSequence)localObject3))
                  {
                    if (MainApplication.uw().getPackageManager().getLaunchIntentForPackage((String)localObject3) == null) {
                      break label1915;
                    }
                    k = 1;
                    ((StringBuilder)localObject2).append("{");
                    ((StringBuilder)localObject2).append("\"pkgname\":").append(JSONObject.quote((String)localObject3)).append(",");
                    ((StringBuilder)localObject2).append("\"isinstalled\":").append(k);
                    ((StringBuilder)localObject2).append("}");
                  }
                  if (j != m - 1) {
                    ((StringBuilder)localObject2).append(",");
                  }
                }
                catch (Exception localException3)
                {
                  localException3.printStackTrace();
                  continue;
                }
                j += 1;
                continue;
                localObject1 = "";
                continue;
              }
              ((StringBuilder)localObject2).append("]");
              localObject1 = ((StringBuilder)localObject2).toString();
              if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localDn.Gi() == 1)) {
                d(localDn.getId(), (String)localObject1);
              }
            }
          }
          else if (localDn.getType() == 21)
          {
            K(localDn.getId());
          }
          else if (localDn.getType() == 30)
          {
            localObject1 = com.zing.zalo.db.bj.HC().fj(com.zing.a.a.dzm);
            if (((ArrayList)localObject1).size() > 0)
            {
              localObject2 = new StringBuilder();
              k = ((ArrayList)localObject1).size();
              j = 0;
              if (j < k)
              {
                localA = (com.zing.zalo.stickers.a)((ArrayList)localObject1).get(j);
                if (j != k - 1)
                {
                  ((StringBuilder)localObject2).append(localA.getId() + ";");
                  break label1921;
                }
                ((StringBuilder)localObject2).append("" + localA.getId());
                break label1921;
              }
              localObject1 = ((StringBuilder)localObject2).toString();
              if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localDn.Gi() == 1)) {
                d(localDn.getId(), (String)localObject1);
              }
            }
          }
          else if (localDn.getType() == 13)
          {
            localDn.ey(com.zing.zalo.control.b.a(new com.zing.zalo.control.b(new JSONObject(localDn.getBody()))));
            com.zing.zalo.db.bj.HC().a(localDn);
            com.zing.zalo.i.g.bJT.set(false);
            bm.arb();
          }
          else if (localDn.getType() == 43)
          {
            localObject1 = bm.arC();
            if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localDn.Gi() == 1))
            {
              d(localDn.getId(), (String)localObject1);
              break label1852;
              return;
              break label1867;
              continue;
              bool1 = false;
              continue;
              break label1821;
              continue;
              bool1 = false;
              continue;
              if (bool2) {
                continue;
              }
              i += 1;
              continue;
              j = 0;
              bool1 = false;
              continue;
              for (;;)
              {
                j += 1;
                break;
                bool1 = false;
                continue;
                bool1 = false;
                continue;
                bool1 = false;
              }
              bool1 = false;
              continue;
            }
          }
        }
      }
      i += 1;
      continue;
      label1859:
      j = 0;
      boolean bool1 = false;
      continue;
      for (;;)
      {
        label1867:
        j += 1;
        break;
        label1874:
        bool1 = false;
        continue;
        label1880:
        bool1 = false;
        continue;
        label1886:
        bool1 = false;
      }
      label1892:
      bool1 = false;
    }
  }
  
  public void wV()
  {
    try
    {
      if (this.aLT) {
        return;
      }
      this.aLT = true;
      com.zing.zalo.f.c.ai(MainApplication.uw(), "");
      com.zing.zalo.f.c.aj(MainApplication.uw(), "");
      r localR = new r();
      localR.a(new m(this));
      localR.wV();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}
