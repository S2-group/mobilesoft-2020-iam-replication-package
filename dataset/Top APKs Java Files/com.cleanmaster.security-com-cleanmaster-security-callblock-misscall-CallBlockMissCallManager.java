package com.cleanmaster.security.callblock.misscall;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.cleanmaster.security.CmsBaseReceiver;
import com.cleanmaster.security.callblock.CallBlockPref;
import com.cleanmaster.security.callblock.CallBlocker;
import com.cleanmaster.security.callblock.CallSession;
import com.cleanmaster.security.callblock.CallerInfo;
import com.cleanmaster.security.callblock.R.dimen;
import com.cleanmaster.security.callblock.R.drawable;
import com.cleanmaster.security.callblock.R.id;
import com.cleanmaster.security.callblock.R.layout;
import com.cleanmaster.security.callblock.R.string;
import com.cleanmaster.security.callblock.advertise.CbAdHelper;
import com.cleanmaster.security.callblock.data.CallLogData;
import com.cleanmaster.security.callblock.data.CallblockExtData;
import com.cleanmaster.security.callblock.data.TagData;
import com.cleanmaster.security.callblock.database.CallLogItemManger;
import com.cleanmaster.security.callblock.database.item.CallLogItem;
import com.cleanmaster.security.callblock.interfaces.ICallBlocker;
import com.cleanmaster.security.callblock.interfaces.ICommons;
import com.cleanmaster.security.callblock.interfaces.IPref;
import com.cleanmaster.security.callblock.interfaces.ImageLoadingListener;
import com.cleanmaster.security.callblock.misscall.ui.CallBlockShowMissedCallActivity;
import com.cleanmaster.security.callblock.phonestate.ContactUtils;
import com.cleanmaster.security.callblock.report.CallBlockWhatsCallIntlReportItem;
import com.cleanmaster.security.callblock.utils.CloudConfig;
import com.cleanmaster.security.callblock.utils.Commons;
import com.cleanmaster.security.callblock.utils.DebugMode;
import com.cleanmaster.security.callblock.utils.NetUtil;
import com.cleanmaster.security.callblock.utils.PhotoUtils;
import com.cleanmaster.security.callblock.utils.TagUtils;
import com.cleanmaster.security.callblock.utils.UIUtils.ImageSize;
import com.cleanmaster.security.util.MiuiCommonHelper;
import com.cleanmaster.security.util.PackageInfoUtil;
import com.google.a.a.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ks.cm.antivirus.common.ui.IconFontTextView;
import org.json.JSONObject;

public class CallBlockMissCallManager
{
  public static boolean a = true;
  private static CallBlockMissCallManager b;
  private static int i = 0;
  private static int j = 0;
  private static boolean m = false;
  private static boolean n = true;
  private static Bitmap o = null;
  private JSONObject c;
  private int d = 0;
  private RemoteViews e = null;
  private long f = 0L;
  private CallblockExtData g;
  private Handler h;
  private boolean k = false;
  private CmsBaseReceiver l = new CmsBaseReceiver()
  {
    public void onSyncReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("com.cleanmaster.security.callblock.misscall.ACTION_NOTIFICATION_DELETE")) {
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "notify deleted intent");
        }
      }
      do
      {
        try
        {
          CallBlockMissCallManager.a().a(0, null, 0L, null);
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "notify deleted intent remove from manager success");
          }
          CallBlockMissCallManager.a().a(System.currentTimeMillis());
          return;
        }
        catch (Exception paramAnonymousContext)
        {
          while (!DebugMode.a) {}
          DebugMode.a("CallBlockMissCallManager", "notify deleted intent fail");
          return;
        }
        if (paramAnonymousIntent.getAction().equals("com.cleanmaster.security.callblock.misscall.ACTION_RESET_MISSCALL_TS_INTENT"))
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "notify reset ts intent");
          }
          CallBlockMissCallManager.a().a(System.currentTimeMillis());
          return;
        }
      } while (!paramAnonymousIntent.getAction().equals("com.cleanmaster.security.callblock.misscall.ACTION_CALL_AND_DISMISS"));
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "ACTION_CALL_AND_DISMISS");
      }
      paramAnonymousContext = paramAnonymousIntent.getStringExtra("number");
      Commons.a(CallBlocker.b(), paramAnonymousContext, true);
      try
      {
        CallBlocker.a().o().c(8001);
        int i = paramAnonymousIntent.getIntExtra("call_soft_type", 11);
        CallBlocker.a().o().a(i, 0);
        return;
      }
      catch (Exception paramAnonymousContext) {}
    }
  };
  
  private CallBlockMissCallManager()
  {
    try
    {
      this.c = new JSONObject();
      i();
      this.h = new Handler(Looper.getMainLooper());
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  private int a(Context paramContext, String paramString, long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aload_1
    //   4: ifnonnull +9 -> 13
    //   7: iconst_0
    //   8: istore 7
    //   10: iload 7
    //   12: ireturn
    //   13: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   16: ifeq +27 -> 43
    //   19: ldc 96
    //   21: new 98	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   28: ldc 101
    //   30: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_2
    //   34: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   43: iload 5
    //   45: ifeq +215 -> 260
    //   48: new 98	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   55: ldc 114
    //   57: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: lload_3
    //   61: invokestatic 120	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   64: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: astore 8
    //   72: aload_2
    //   73: invokestatic 126	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   76: ifne +211 -> 287
    //   79: new 98	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   86: aload 8
    //   88: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: ldc -128
    //   93: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: astore 8
    //   101: new 98	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   108: aload 8
    //   110: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: ldc -126
    //   115: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload_2
    //   119: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: ldc -124
    //   124: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: astore_2
    //   131: new 98	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   138: aload_2
    //   139: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: ldc -122
    //   144: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: astore_2
    //   151: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   154: ifeq +27 -> 181
    //   157: ldc 96
    //   159: new 98	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   166: ldc -120
    //   168: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: aload_2
    //   172: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   181: aload_1
    //   182: invokevirtual 142	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   185: getstatic 148	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   188: iconst_1
    //   189: anewarray 116	java/lang/String
    //   192: dup
    //   193: iconst_0
    //   194: ldc -106
    //   196: aastore
    //   197: aload_2
    //   198: aconst_null
    //   199: aconst_null
    //   200: invokevirtual 156	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   203: astore_1
    //   204: aload_1
    //   205: ifnull +14 -> 219
    //   208: aload_1
    //   209: astore_2
    //   210: aload_1
    //   211: invokeinterface 162 1 0
    //   216: ifne +85 -> 301
    //   219: aload_1
    //   220: astore_2
    //   221: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   224: ifeq +12 -> 236
    //   227: aload_1
    //   228: astore_2
    //   229: ldc 96
    //   231: ldc -92
    //   233: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   236: aload_1
    //   237: ifnull +11 -> 248
    //   240: aload_1
    //   241: astore_2
    //   242: aload_1
    //   243: invokeinterface 167 1 0
    //   248: aload_1
    //   249: ifnull +9 -> 258
    //   252: aload_1
    //   253: invokeinterface 167 1 0
    //   258: iconst_0
    //   259: ireturn
    //   260: new 98	java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   267: ldc -87
    //   269: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: lload_3
    //   273: invokestatic 120	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   276: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: astore 8
    //   284: goto -212 -> 72
    //   287: iconst_0
    //   288: ifeq +11 -> 299
    //   291: new 171	java/lang/NullPointerException
    //   294: dup
    //   295: invokespecial 172	java/lang/NullPointerException:<init>	()V
    //   298: athrow
    //   299: iconst_0
    //   300: ireturn
    //   301: aload_1
    //   302: astore_2
    //   303: aload_1
    //   304: invokeinterface 162 1 0
    //   309: istore 6
    //   311: aload_1
    //   312: astore_2
    //   313: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   316: ifeq +30 -> 346
    //   319: aload_1
    //   320: astore_2
    //   321: ldc 96
    //   323: new 98	java/lang/StringBuilder
    //   326: dup
    //   327: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   330: ldc -82
    //   332: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: iload 6
    //   337: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   340: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   346: iload 6
    //   348: istore 7
    //   350: aload_1
    //   351: ifnull -341 -> 10
    //   354: aload_1
    //   355: invokeinterface 167 1 0
    //   360: iload 6
    //   362: ireturn
    //   363: astore 8
    //   365: aconst_null
    //   366: astore_1
    //   367: iconst_0
    //   368: istore 6
    //   370: aload_1
    //   371: astore_2
    //   372: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   375: ifeq +33 -> 408
    //   378: aload_1
    //   379: astore_2
    //   380: ldc 96
    //   382: new 98	java/lang/StringBuilder
    //   385: dup
    //   386: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   389: ldc -77
    //   391: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: aload 8
    //   396: invokevirtual 182	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   399: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   405: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   408: iload 6
    //   410: istore 7
    //   412: aload_1
    //   413: ifnull -403 -> 10
    //   416: aload_1
    //   417: invokeinterface 167 1 0
    //   422: iload 6
    //   424: ireturn
    //   425: astore_2
    //   426: aload 9
    //   428: astore_1
    //   429: aload_1
    //   430: ifnull +9 -> 439
    //   433: aload_1
    //   434: invokeinterface 167 1 0
    //   439: aload_2
    //   440: athrow
    //   441: astore 8
    //   443: aload_2
    //   444: astore_1
    //   445: aload 8
    //   447: astore_2
    //   448: goto -19 -> 429
    //   451: astore 8
    //   453: iconst_0
    //   454: istore 6
    //   456: goto -86 -> 370
    //   459: astore 8
    //   461: goto -91 -> 370
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	464	0	this	CallBlockMissCallManager
    //   0	464	1	paramContext	Context
    //   0	464	2	paramString	String
    //   0	464	3	paramLong	long
    //   0	464	5	paramBoolean	boolean
    //   309	146	6	i1	int
    //   8	403	7	i2	int
    //   70	213	8	str	String
    //   363	32	8	localException1	Exception
    //   441	5	8	localObject1	Object
    //   451	1	8	localException2	Exception
    //   459	1	8	localException3	Exception
    //   1	426	9	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   48	72	363	java/lang/Exception
    //   72	181	363	java/lang/Exception
    //   181	204	363	java/lang/Exception
    //   260	284	363	java/lang/Exception
    //   48	72	425	finally
    //   72	181	425	finally
    //   181	204	425	finally
    //   260	284	425	finally
    //   210	219	441	finally
    //   221	227	441	finally
    //   229	236	441	finally
    //   242	248	441	finally
    //   303	311	441	finally
    //   313	319	441	finally
    //   321	346	441	finally
    //   372	378	441	finally
    //   380	408	441	finally
    //   210	219	451	java/lang/Exception
    //   221	227	451	java/lang/Exception
    //   229	236	451	java/lang/Exception
    //   242	248	451	java/lang/Exception
    //   303	311	451	java/lang/Exception
    //   313	319	459	java/lang/Exception
    //   321	346	459	java/lang/Exception
  }
  
  private static RemoteViews a(long paramLong, CallblockExtData paramCallblockExtData, Intent paramIntent)
  {
    Object localObject2 = CallBlocker.b();
    Object localObject1 = a().d();
    long l1 = a().e();
    if ((localObject1 == null) || (((ArrayList)localObject1).size() == 0))
    {
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "no miss call data ");
      }
      return null;
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "total misscall items  " + ((ArrayList)localObject1).size());
    }
    int i3 = 0;
    int i1 = 0;
    while (i1 < ((ArrayList)localObject1).size())
    {
      if ((((ArrayList)localObject1).get(i1) != null) && (((CallLogData)((ArrayList)localObject1).get(i1)).b == 0)) {
        ((CallLogData)((ArrayList)localObject1).get(i1)).b = 1;
      }
      i3 += ((CallLogData)((ArrayList)localObject1).get(i1)).b;
      i1 += 1;
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "total misscall totalCount  " + i3);
    }
    if (i3 == 0) {
      return null;
    }
    if (i3 > 1)
    {
      i1 = R.layout.a0001_cb_callblock_miscall_multiple_items_for_notify;
      if ((MiuiCommonHelper.e()) || (MiuiCommonHelper.f()) || (MiuiCommonHelper.g()))
      {
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "misscall layout m6, m7, m8  ");
        }
        i1 = R.layout.callblock_miscall_multiple_items_for_notify_miui6;
      }
    }
    label376:
    label1170:
    label1346:
    label1461:
    for (;;)
    {
      RemoteViews localRemoteViews = new RemoteViews(CallBlocker.b().getPackageName(), i1);
      localRemoteViews.setViewVisibility(R.id.call_item_block, 8);
      localRemoteViews.setViewVisibility(R.id.call_status_icon, 8);
      JSONObject localJSONObject = new JSONObject();
      a(localJSONObject, "com.cleanmaster.security.callblock.misscall.ACTION_NOTIFICATION_DELETE", "com.cleanmaster.security.callblock.misscall.ACTION_NOTIFICATION_DELETE");
      paramIntent.putExtra("last_list_time", l1);
      a(localJSONObject, "click_action", "-1");
      paramIntent.putExtra("click_action", -1);
      paramIntent.putExtra("main_click_action", -1);
      Object localObject3;
      if (i3 == 1)
      {
        localObject3 = (CallLogData)((ArrayList)localObject1).get(0);
        if (localObject3 != null)
        {
          localObject1 = ((CallLogData)localObject3).a;
          if (localObject1 != null)
          {
            if ((((CallLogItem)localObject1).f() == 3) && (DebugMode.a)) {
              DebugMode.a("CallBlockMissCallManager", "not show notification due to 1 contact");
            }
            String str = ((CallLogItem)localObject1).c();
            paramIntent.putExtra("number", str);
            a((Context)localObject2, localRemoteViews, (CallLogData)localObject3, true, paramLong, paramCallblockExtData, paramIntent);
            a(localJSONObject, "number", str);
            paramIntent.putExtra("extra_call_log_item", ((CallLogItem)localObject1).a());
            paramIntent.putExtra("extra_call_log_source", (byte)2);
          }
          switch (paramIntent.getIntExtra("click_action", -1))
          {
          case 1: 
          default: 
            localRemoteViews.setTextViewText(R.id.btnRight, CallBlocker.b().getResources().getString(R.string.callblock_btn_detail));
          }
        }
      }
      for (;;)
      {
        paramCallblockExtData.d = localJSONObject.toString();
        paramCallblockExtData.a = paramIntent;
        return localRemoteViews;
        if (MiuiCommonHelper.d())
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "misscall layout m5  ");
          }
          i1 = R.layout.callblock_miscall_multiple_items_for_notify_miui6;
          break;
        }
        if (!MiuiCommonHelper.b()) {
          break label1461;
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "misscall layout mi  ");
        }
        i1 = R.layout.callblock_miscall_multiple_items_for_notify_miui6;
        break;
        i1 = R.layout.a0001_cb_callblock_miscall_item_for_notify;
        if ((MiuiCommonHelper.e()) || (MiuiCommonHelper.f()) || (MiuiCommonHelper.g()))
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "misscall layout m6, m7, m8  ");
          }
          i1 = R.layout.callblock_miscall_item_for_notify_miui6;
          break;
        }
        if (MiuiCommonHelper.d())
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "misscall layout m5  ");
          }
          i1 = R.layout.callblock_miscall_item_for_notify_miui6;
          break;
        }
        if (!MiuiCommonHelper.b()) {
          break label1461;
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "misscall layout mi  ");
        }
        i1 = R.layout.callblock_miscall_item_for_notify_miui6;
        break;
        localObject1 = null;
        break label376;
        int i4 = 0;
        int i8 = 0;
        int i6 = 0;
        int i5 = 0;
        if (i5 < ((ArrayList)localObject1).size())
        {
          localObject2 = (CallLogData)((ArrayList)localObject1).get(i5);
          int i2;
          int i7;
          if (localObject2 == null)
          {
            i2 = i4;
            i1 = i8;
            i7 = i6;
          }
          for (;;)
          {
            i5 += 1;
            i6 = i7;
            i8 = i1;
            i4 = i2;
            break;
            if (((CallLogData)localObject2).b == 0) {
              ((CallLogData)localObject2).b = 1;
            }
            i7 = i6;
            i1 = i8;
            i2 = i4;
            if (((CallLogData)localObject2).a != null)
            {
              localObject3 = ((CallLogData)localObject2).a;
              if (((CallLogItem)localObject3).f() == 3)
              {
                i7 = i6 + ((CallLogData)localObject2).b;
                i1 = i8;
                i2 = i4;
              }
              else if (((CallLogItem)localObject3).f() == 0)
              {
                if (!TextUtils.isEmpty(((CallLogItem)localObject3).d()))
                {
                  i1 = i8 + ((CallLogData)localObject2).b;
                  i7 = i6;
                  i2 = i4;
                }
                else if ((((CallLogItem)localObject3).e() != null) && (TagData.a(((CallLogItem)localObject3).e()) != null))
                {
                  i1 = i8 + ((CallLogData)localObject2).b;
                  i7 = i6;
                  i2 = i4;
                }
                else
                {
                  i2 = i4 + ((CallLogData)localObject2).b;
                  i7 = i6;
                  i1 = i8;
                }
              }
              else if (((CallLogItem)localObject3).f() == 1)
              {
                i1 = i8 + ((CallLogData)localObject2).b;
                i7 = i6;
                i2 = i4;
              }
              else if (((CallLogItem)localObject3).f() == 2)
              {
                i1 = i8 + ((CallLogData)localObject2).b;
                i7 = i6;
                i2 = i4;
              }
              else if (((CallLogItem)localObject3).f() == 4)
              {
                i1 = i8 + ((CallLogData)localObject2).b;
                i7 = i6;
                i2 = i4;
              }
              else
              {
                i2 = i4 + ((CallLogData)localObject2).b;
                i7 = i6;
                i1 = i8;
              }
            }
          }
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "total misscall contact_count  " + i6);
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "total misscall known_count  " + i8);
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "total misscall unknown_count  " + i4);
        }
        if (i3 <= 1)
        {
          localObject1 = CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_one_missedcall_title);
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "total misscall count str  " + (String)localObject1);
          }
          localRemoteViews.setTextViewText(R.id.block_log_item_display_name, (CharSequence)localObject1);
          localRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          if (i6 <= 0) {
            break label1346;
          }
          localObject1 = String.format(CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_contact_missedcall_summary), new Object[] { Integer.valueOf(i6) });
        }
        for (;;)
        {
          localRemoteViews.setTextViewText(R.id.block_log_item_description, (CharSequence)localObject1);
          localRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          paramIntent.putExtra("click_action", 1);
          paramIntent.putExtra("main_click_action", 1);
          paramIntent.putExtra("extra_call_log_source", (byte)3);
          paramIntent.putExtra("call_soft_type", 12);
          break;
          localObject1 = String.format(CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_more_missedcall_title), new Object[] { Integer.valueOf(i3) });
          break label1170;
          if (i8 > 0) {
            localObject1 = String.format(CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_yellowpage_missedcall_summary), new Object[] { Integer.valueOf(i8) });
          } else {
            localObject1 = String.format(CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_onlylocation_missedcall_summary), new Object[] { Integer.valueOf(i4) });
          }
        }
        localRemoteViews.setTextViewText(R.id.btnRight, "");
        localRemoteViews.setViewVisibility(R.id.btnRightCallback, 0);
        continue;
        localRemoteViews.setTextViewText(R.id.btnRight, CallBlocker.b().getResources().getString(R.string.callblock_btn_detail));
      }
    }
  }
  
  public static CallBlockMissCallManager a()
  {
    if (b == null) {
      b = new CallBlockMissCallManager();
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "callblock manager callblockpid " + Process.myPid());
    }
    return b;
  }
  
  public static void a(int paramInt)
  {
    CallBlockWhatsCallIntlReportItem.a(, paramInt);
  }
  
  public static void a(int paramInt1, int paramInt2)
  {
    CallBlockWhatsCallIntlReportItem.a((byte)paramInt1, paramInt2);
  }
  
  public static void a(int paramInt1, ICallBlocker paramICallBlocker, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 == 2)
    {
      CallBlockShowMissedCallActivity.tryToDismissMissCallActivity();
      a().f();
    }
    try
    {
      CallBlocker.a().o().c(8001);
      paramICallBlocker.a(paramInt2, paramInt3, paramInt4);
      do
      {
        for (;;)
        {
          return;
          if (paramInt1 == 3)
          {
            a().f();
            try
            {
              paramICallBlocker = new CallblockExtData();
              paramICallBlocker.a = null;
              paramICallBlocker.c = "com.cleanmaster.security.callblock.misscall.ACTION_NOTIFICATION_DELETE";
              long l1 = System.currentTimeMillis();
              RemoteViews localRemoteViews = a(l1, paramICallBlocker, new Intent());
              if (localRemoteViews != null)
              {
                paramICallBlocker.b = localRemoteViews;
                a().a(8001, localRemoteViews, l1, paramICallBlocker);
                a().a(paramICallBlocker);
                if (!TextUtils.isEmpty(paramICallBlocker.g)) {
                  b(paramICallBlocker.g, paramICallBlocker.h);
                }
                p();
                return;
              }
            }
            catch (Exception paramICallBlocker)
            {
              return;
            }
          }
        }
      } while (!DebugMode.a);
      DebugMode.a("CallBlockMissCallManager", "nothing to show for misscall");
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private static void a(Context paramContext, RemoteViews paramRemoteViews, CallLogData paramCallLogData, boolean paramBoolean, long paramLong, CallblockExtData paramCallblockExtData, Intent paramIntent)
  {
    try
    {
      o.recycle();
      o = null;
      CallLogItem localCallLogItem = paramCallLogData.a;
      if (paramCallLogData.b == 0) {
        paramCallLogData.b = 1;
      }
      paramRemoteViews.setTextViewText(R.id.block_log_item_display_count, "");
      View localView;
      IconFontTextView localIconFontTextView;
      Object localObject;
      if (localCallLogItem != null)
      {
        localView = LayoutInflater.from(paramContext).inflate(R.layout.callblock_calllog_list_gen_portrait, null);
        localIconFontTextView = (IconFontTextView)localView.findViewById(R.id.call_item_emoji);
        localObject = (TextView)localView.findViewById(R.id.call_item_show_card_char);
        paramRemoteViews.setViewVisibility(R.id.iv_calllog_photo, 8);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 8);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 8);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 8);
        String str = paramContext.getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_one_missedcall_title);
        if (TextUtils.isEmpty(localCallLogItem.c())) {
          break label2282;
        }
        if (localCallLogItem.f() != 3) {
          break label663;
        }
        paramCallblockExtData.e = 1;
        paramCallblockExtData.f = 0;
        if ((!TextUtils.isEmpty(localCallLogItem.d())) && (!localCallLogItem.d().equals(localCallLogItem.c()))) {
          break label523;
        }
        paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, localCallLogItem.c());
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 8);
        localObject = localCallLogItem.c();
        paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(R.string.iconfont_imageid_default));
        localIconFontTextView.setText(CallBlocker.b().getResources().getString(R.string.iconfont_imageid_default));
        paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_safe);
        localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_safe);
        if (TextUtils.isEmpty(localCallLogItem.h())) {
          break label581;
        }
        if (n)
        {
          paramCallblockExtData.g = localCallLogItem.h();
          paramCallblockExtData.h = paramLong;
        }
        label324:
        paramIntent.putExtra("click_action", 0);
        paramIntent.putExtra("main_click_action", 2);
        paramIntent.putExtra("call_soft_type", 5);
        paramCallLogData = (CallLogData)localObject;
        label357:
        paramRemoteViews.setViewVisibility(R.id.call_item_emoji, 4);
        paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, str);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 8);
        paramCallblockExtData = paramCallLogData;
        if (TextUtils.isEmpty(paramCallLogData))
        {
          paramCallblockExtData = paramCallLogData;
          if (!TextUtils.isEmpty(localCallLogItem.k())) {
            paramCallblockExtData = localCallLogItem.k();
          }
        }
        if (!TextUtils.isEmpty(paramCallblockExtData)) {
          break label2248;
        }
        paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 8);
      }
      for (;;)
      {
        int i1 = paramContext.getResources().getDimensionPixelSize(R.dimen.missed_call_notify_portrait_size);
        try
        {
          localView.measure(0x40000000 | i1, 0x40000000 | i1);
          localView.layout(0, 0, i1, i1);
          localView.buildDrawingCache();
          o = PhotoUtils.a(localView.getDrawingCache(), i1, i1);
          paramRemoteViews.setViewVisibility(R.id.iv_calllog_photo, 0);
          paramRemoteViews.setImageViewBitmap(R.id.iv_calllog_photo, o);
          localView.destroyDrawingCache();
          return;
        }
        catch (Exception paramContext)
        {
          label523:
          TagData localTagData;
          return;
        }
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
        paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, localCallLogItem.d());
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
        paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c());
        localObject = localCallLogItem.d();
        break;
        label581:
        if (TextUtils.isEmpty(paramCallLogData.c)) {
          paramCallLogData.c = ContactUtils.d(paramContext, localCallLogItem.c());
        }
        if (TextUtils.isEmpty(paramCallLogData.c)) {
          break label324;
        }
        localCallLogItem.e(paramCallLogData.c);
        CallLogItemManger.a().b(localCallLogItem);
        paramCallLogData.a = localCallLogItem;
        if (!n) {
          break label324;
        }
        paramCallblockExtData.g = paramCallLogData.c;
        paramCallblockExtData.h = paramLong;
        break label324;
        label663:
        if (localCallLogItem.f() == 2)
        {
          paramCallblockExtData.e = 0;
          paramCallblockExtData.f = 0;
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_noti_title_answer));
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
          paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(TagData.n));
          paramCallLogData = CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_noti_title_answer);
          localIconFontTextView.setText(CallBlocker.b().getResources().getString(TagData.n));
          if (!TextUtils.isEmpty(localCallLogItem.k()))
          {
            paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.k());
            paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          }
          paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_fillteredcall);
          localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_fillteredcall);
          paramIntent.putExtra("click_action", 0);
          paramIntent.putExtra("main_click_action", 2);
          paramIntent.putExtra("call_soft_type", 10);
          break label357;
        }
        if (localCallLogItem.f() == 1)
        {
          paramCallblockExtData.e = 0;
          paramCallblockExtData.f = 0;
          paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_risky);
          localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_risky);
          localObject = TagData.a(localCallLogItem.e());
          if (localObject == null) {
            break label2275;
          }
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          paramCallblockExtData = paramContext.getResources().getString(((TagData)localObject).a());
          paramCallLogData = paramCallblockExtData;
          if (TextUtils.isEmpty(paramCallblockExtData)) {
            paramCallLogData = localCallLogItem.c();
          }
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, paramCallLogData);
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
          paramRemoteViews.setTextViewText(R.id.call_item_emoji, paramContext.getResources().getString(((TagData)localObject).b()));
          localIconFontTextView.setText(paramContext.getResources().getString(((TagData)localObject).b()));
          paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c());
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          paramCallblockExtData = localCallLogItem.p();
          if (!TextUtils.isEmpty(paramCallblockExtData))
          {
            paramRemoteViews.setTextViewText(R.id.block_log_item_description, paramCallblockExtData);
            paramCallLogData = paramCallblockExtData;
          }
          paramIntent.putExtra("click_action", 2);
          paramIntent.putExtra("main_click_action", 2);
          paramIntent.putExtra("call_soft_type", 6);
          break label357;
        }
        paramCallblockExtData.e = 0;
        paramCallblockExtData.f = 0;
        localTagData = TagData.a(localCallLogItem.e());
        paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_contact);
        localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_contact);
        if (localTagData != null)
        {
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          localObject = paramContext.getResources().getString(localTagData.a());
          paramCallLogData = (CallLogData)localObject;
          if (TextUtils.isEmpty((CharSequence)localObject)) {
            paramCallLogData = localCallLogItem.c();
          }
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, paramCallLogData);
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
          paramRemoteViews.setTextViewText(R.id.call_item_emoji, paramContext.getResources().getString(localTagData.b()));
          localIconFontTextView.setText(paramContext.getResources().getString(localTagData.b()));
          paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c());
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          switch (4.a[localTagData.ordinal()])
          {
          default: 
            paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_safe);
            localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_safe);
            if ((!TextUtils.isEmpty(localCallLogItem.h())) && (n))
            {
              paramCallblockExtData.g = localCallLogItem.h();
              paramCallblockExtData.h = paramLong;
            }
            break;
          }
          for (;;)
          {
            paramCallblockExtData = localCallLogItem.p();
            if (!TextUtils.isEmpty(paramCallblockExtData))
            {
              paramRemoteViews.setTextViewText(R.id.block_log_item_description, paramCallblockExtData);
              paramCallLogData = paramCallblockExtData;
            }
            paramIntent.putExtra("click_action", 2);
            paramIntent.putExtra("main_click_action", 2);
            paramIntent.putExtra("call_soft_type", 6);
            break;
            paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_risky);
            localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_risky);
          }
        }
        if (localCallLogItem.c().equals("0000000000"))
        {
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_noti_title_privatenumber));
          paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(R.string.iconfont_unknowncall));
          localIconFontTextView.setText(CallBlocker.b().getResources().getString(R.string.iconfont_unknowncall));
          paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_safe);
          localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_safe);
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 8);
          paramCallLogData = CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_noti_title_privatenumber);
          break label357;
        }
        if (localCallLogItem.f() == 4)
        {
          paramCallblockExtData.e = 1;
          paramCallblockExtData.f = 0;
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, localCallLogItem.d());
          if ((Build.VERSION.SDK_INT < 17) || (TextUtils.isEmpty(localCallLogItem.d())))
          {
            paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(R.string.iconfont_imageid_bizcard));
            localIconFontTextView.setText(CallBlocker.b().getResources().getString(R.string.iconfont_imageid_bizcard));
            localIconFontTextView.setVisibility(0);
            ((TextView)localObject).setVisibility(4);
          }
          for (;;)
          {
            if (!TextUtils.isEmpty(localCallLogItem.k()))
            {
              paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c() + "-" + localCallLogItem.k());
              paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
            }
            paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c());
            paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
            paramCallLogData = localCallLogItem.d();
            paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_safe);
            localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_safe);
            if (n)
            {
              paramCallblockExtData.g = localCallLogItem.h();
              paramCallblockExtData.h = paramLong;
            }
            paramIntent.putExtra("click_action", 0);
            paramIntent.putExtra("main_click_action", 2);
            paramIntent.putExtra("call_soft_type", 8);
            break;
            ((TextView)localObject).setText(TagUtils.a(localCallLogItem.d()));
            ((TextView)localObject).setVisibility(0);
            localIconFontTextView.setVisibility(4);
          }
        }
        if (!TextUtils.isEmpty(localCallLogItem.d()))
        {
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
          paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, localCallLogItem.d());
          paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
          paramCallLogData = localCallLogItem.d();
          paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(R.string.iconfont_callblock));
          localIconFontTextView.setText(CallBlocker.b().getResources().getString(R.string.iconfont_callblock));
          paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_safe);
          localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_safe);
          paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.c());
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          if (!TextUtils.isEmpty(localCallLogItem.h()))
          {
            if (n)
            {
              paramCallblockExtData.g = localCallLogItem.h();
              paramCallblockExtData.h = paramLong;
            }
            paramIntent.putExtra("call_soft_type", 9);
          }
          for (;;)
          {
            paramIntent.putExtra("click_action", 0);
            paramIntent.putExtra("main_click_action", 2);
            break;
            paramIntent.putExtra("call_soft_type", 7);
          }
        }
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_name, 0);
        paramRemoteViews.setTextViewText(R.id.block_log_item_display_name, localCallLogItem.c());
        paramRemoteViews.setViewVisibility(R.id.block_log_item_display_count, 0);
        paramCallLogData = localCallLogItem.c();
        paramRemoteViews.setTextViewText(R.id.call_item_emoji, CallBlocker.b().getResources().getString(R.string.iconfont_imageid_unknown));
        paramRemoteViews.setViewVisibility(R.id.call_item_emoji, 4);
        localIconFontTextView.setText(CallBlocker.b().getResources().getString(R.string.iconfont_imageid_unknown));
        paramRemoteViews.setInt(R.id.call_item_emoji, "setBackgroundResource", R.drawable.intl_callblock_circle_bg_contact);
        localIconFontTextView.setBackgroundResource(R.drawable.intl_callblock_circle_bg_unknown_notify);
        if (!TextUtils.isEmpty(localCallLogItem.k()))
        {
          paramRemoteViews.setTextViewText(R.id.block_log_item_description, localCallLogItem.k());
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          paramIntent.putExtra("call_soft_type", 11);
        }
        for (;;)
        {
          paramIntent.putExtra("click_action", 0);
          paramIntent.putExtra("main_click_action", 2);
          break;
          paramRemoteViews.setTextViewText(R.id.block_log_item_description, paramContext.getResources().getString(R.string.intl_cmsecurity_callblock_missedcalls_noti_dlg_unknown_location));
          paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
          paramIntent.putExtra("call_soft_type", 11);
        }
        label2248:
        paramRemoteViews.setTextViewText(R.id.block_log_item_description, paramCallblockExtData);
        paramRemoteViews.setViewVisibility(R.id.block_log_item_description, 0);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        continue;
        label2275:
        paramCallLogData = "";
        continue;
        label2282:
        paramCallLogData = "";
      }
    }
  }
  
  private void a(ArrayList<CallLogData> paramArrayList)
  {
    i();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      CallLogData localCallLogData = (CallLogData)localIterator.next();
      if ((localCallLogData != null) && (localCallLogData.a != null))
      {
        paramArrayList = localCallLogData.a.b();
        if (!localCallLogData.a.b().contains("+")) {
          paramArrayList = "+" + localCallLogData.a.b();
        }
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "getCallLogRingingInfo " + paramArrayList);
        }
        paramArrayList = c(paramArrayList);
        if (paramArrayList != null)
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "getCallLogRingingInfo info " + paramArrayList.toString());
          }
          try
          {
            localCallLogData.d = paramArrayList.getLong("duration");
            localCallLogData.e = paramArrayList.getLong("start");
            localCallLogData.f = paramArrayList.getLong("end");
          }
          catch (Exception paramArrayList) {}
        }
      }
    }
  }
  
  /* Error */
  private void a(ArrayList<CallLogData> paramArrayList, Context paramContext, long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aload_2
    //   4: ifnonnull +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: ifnull -2 -> 7
    //   12: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   15: ifeq +11 -> 26
    //   18: ldc 96
    //   20: ldc_w 721
    //   23: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   26: aload_1
    //   27: ifnull -20 -> 7
    //   30: aload_1
    //   31: invokevirtual 203	java/util/ArrayList:size	()I
    //   34: ifeq -27 -> 7
    //   37: new 723	android/support/v4/e/a
    //   40: dup
    //   41: invokespecial 724	android/support/v4/e/a:<init>	()V
    //   44: astore 9
    //   46: iconst_0
    //   47: istore 6
    //   49: ldc_w 408
    //   52: astore 7
    //   54: iload 6
    //   56: aload_1
    //   57: invokevirtual 203	java/util/ArrayList:size	()I
    //   60: if_icmpge +152 -> 212
    //   63: aload_1
    //   64: iload 6
    //   66: invokevirtual 211	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   69: checkcast 213	com/cleanmaster/security/callblock/data/CallLogData
    //   72: astore 10
    //   74: aload 9
    //   76: aload 10
    //   78: getfield 281	com/cleanmaster/security/callblock/data/CallLogData:a	Lcom/cleanmaster/security/callblock/database/item/CallLogItem;
    //   81: invokevirtual 289	com/cleanmaster/security/callblock/database/item/CallLogItem:c	()Ljava/lang/String;
    //   84: invokevirtual 727	android/support/v4/e/a:containsKey	(Ljava/lang/Object;)Z
    //   87: ifne +25 -> 112
    //   90: aload 9
    //   92: aload 10
    //   94: getfield 281	com/cleanmaster/security/callblock/data/CallLogData:a	Lcom/cleanmaster/security/callblock/database/item/CallLogItem;
    //   97: invokevirtual 289	com/cleanmaster/security/callblock/database/item/CallLogItem:c	()Ljava/lang/String;
    //   100: aload 10
    //   102: invokevirtual 731	android/support/v4/e/a:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: aload 10
    //   108: iconst_0
    //   109: putfield 215	com/cleanmaster/security/callblock/data/CallLogData:b	I
    //   112: iload 6
    //   114: aload_1
    //   115: invokevirtual 203	java/util/ArrayList:size	()I
    //   118: iconst_1
    //   119: isub
    //   120: if_icmpne +50 -> 170
    //   123: new 98	java/lang/StringBuilder
    //   126: dup
    //   127: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   130: aload 7
    //   132: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: ldc -126
    //   137: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 10
    //   142: getfield 281	com/cleanmaster/security/callblock/data/CallLogData:a	Lcom/cleanmaster/security/callblock/database/item/CallLogItem;
    //   145: invokevirtual 289	com/cleanmaster/security/callblock/database/item/CallLogItem:c	()Ljava/lang/String;
    //   148: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: ldc -124
    //   153: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: astore 7
    //   161: iload 6
    //   163: iconst_1
    //   164: iadd
    //   165: istore 6
    //   167: goto -113 -> 54
    //   170: new 98	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   177: aload 7
    //   179: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: ldc -126
    //   184: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload 10
    //   189: getfield 281	com/cleanmaster/security/callblock/data/CallLogData:a	Lcom/cleanmaster/security/callblock/database/item/CallLogItem;
    //   192: invokevirtual 289	com/cleanmaster/security/callblock/database/item/CallLogItem:c	()Ljava/lang/String;
    //   195: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: ldc_w 733
    //   201: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: astore 7
    //   209: goto -48 -> 161
    //   212: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   215: ifeq +29 -> 244
    //   218: ldc 96
    //   220: new 98	java/lang/StringBuilder
    //   223: dup
    //   224: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   227: ldc_w 735
    //   230: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload 7
    //   235: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   244: invokestatic 458	java/lang/System:currentTimeMillis	()J
    //   247: invokestatic 120	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   250: astore_1
    //   251: iload 5
    //   253: ifeq +234 -> 487
    //   256: new 98	java/lang/StringBuilder
    //   259: dup
    //   260: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   263: ldc 114
    //   265: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: lload_3
    //   269: invokestatic 120	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   272: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: astore 10
    //   280: new 98	java/lang/StringBuilder
    //   283: dup
    //   284: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   287: aload 10
    //   289: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: ldc_w 737
    //   295: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: aload_1
    //   299: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: astore_1
    //   306: aload 7
    //   308: invokestatic 126	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   311: ifne +501 -> 812
    //   314: new 98	java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   321: aload_1
    //   322: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: ldc_w 739
    //   328: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   334: astore_1
    //   335: new 98	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   342: aload_1
    //   343: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: aload 7
    //   348: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: astore_1
    //   355: new 98	java/lang/StringBuilder
    //   358: dup
    //   359: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   362: aload_1
    //   363: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: ldc_w 741
    //   369: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: astore_1
    //   376: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   379: ifeq +28 -> 407
    //   382: ldc 96
    //   384: new 98	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   391: ldc_w 743
    //   394: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: aload_1
    //   398: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   404: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_2
    //   408: invokevirtual 142	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   411: getstatic 148	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   414: iconst_1
    //   415: anewarray 116	java/lang/String
    //   418: dup
    //   419: iconst_0
    //   420: ldc -106
    //   422: aastore
    //   423: aload_1
    //   424: aconst_null
    //   425: ldc_w 745
    //   428: invokevirtual 156	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   431: astore_1
    //   432: aload_1
    //   433: ifnull +14 -> 447
    //   436: aload_1
    //   437: astore_2
    //   438: aload_1
    //   439: invokeinterface 162 1 0
    //   444: ifne +96 -> 540
    //   447: aload_1
    //   448: astore_2
    //   449: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   452: ifeq +12 -> 464
    //   455: aload_1
    //   456: astore_2
    //   457: ldc 96
    //   459: ldc -92
    //   461: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   464: aload_1
    //   465: ifnull +11 -> 476
    //   468: aload_1
    //   469: astore_2
    //   470: aload_1
    //   471: invokeinterface 167 1 0
    //   476: aload_1
    //   477: ifnull -470 -> 7
    //   480: aload_1
    //   481: invokeinterface 167 1 0
    //   486: return
    //   487: new 98	java/lang/StringBuilder
    //   490: dup
    //   491: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   494: ldc -87
    //   496: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: lload_3
    //   500: invokestatic 120	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   503: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: astore 10
    //   511: new 98	java/lang/StringBuilder
    //   514: dup
    //   515: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   518: aload 10
    //   520: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: ldc_w 737
    //   526: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: aload_1
    //   530: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   536: astore_1
    //   537: goto -231 -> 306
    //   540: aload_1
    //   541: astore_2
    //   542: new 200	java/util/ArrayList
    //   545: dup
    //   546: invokespecial 746	java/util/ArrayList:<init>	()V
    //   549: pop
    //   550: aload_1
    //   551: astore_2
    //   552: aload_1
    //   553: invokeinterface 749 1 0
    //   558: istore 5
    //   560: iload 5
    //   562: ifeq +143 -> 705
    //   565: aload_1
    //   566: astore_2
    //   567: aload_1
    //   568: aload_1
    //   569: ldc -106
    //   571: invokeinterface 753 2 0
    //   576: invokeinterface 754 2 0
    //   581: astore 7
    //   583: aload_1
    //   584: astore_2
    //   585: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   588: ifeq +31 -> 619
    //   591: aload_1
    //   592: astore_2
    //   593: ldc 96
    //   595: new 98	java/lang/StringBuilder
    //   598: dup
    //   599: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   602: ldc_w 756
    //   605: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   608: aload 7
    //   610: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   616: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   619: aload_1
    //   620: astore_2
    //   621: aload 9
    //   623: aload 7
    //   625: invokevirtual 727	android/support/v4/e/a:containsKey	(Ljava/lang/Object;)Z
    //   628: ifeq -78 -> 550
    //   631: aload_1
    //   632: astore_2
    //   633: aload 9
    //   635: aload 7
    //   637: invokevirtual 759	android/support/v4/e/a:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   640: checkcast 213	com/cleanmaster/security/callblock/data/CallLogData
    //   643: astore 7
    //   645: aload_1
    //   646: astore_2
    //   647: aload 7
    //   649: aload 7
    //   651: getfield 215	com/cleanmaster/security/callblock/data/CallLogData:b	I
    //   654: iconst_1
    //   655: iadd
    //   656: putfield 215	com/cleanmaster/security/callblock/data/CallLogData:b	I
    //   659: aload_1
    //   660: astore_2
    //   661: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   664: ifeq -114 -> 550
    //   667: aload_1
    //   668: astore_2
    //   669: ldc 96
    //   671: new 98	java/lang/StringBuilder
    //   674: dup
    //   675: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   678: ldc_w 761
    //   681: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   684: aload 7
    //   686: getfield 215	com/cleanmaster/security/callblock/data/CallLogData:b	I
    //   689: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   692: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   695: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   698: goto -148 -> 550
    //   701: astore_2
    //   702: goto -152 -> 550
    //   705: aload_1
    //   706: astore_2
    //   707: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   710: ifeq +13 -> 723
    //   713: aload_1
    //   714: astore_2
    //   715: ldc 96
    //   717: ldc_w 763
    //   720: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   723: aload_1
    //   724: ifnull -717 -> 7
    //   727: aload_1
    //   728: invokeinterface 167 1 0
    //   733: return
    //   734: astore 7
    //   736: aconst_null
    //   737: astore_1
    //   738: aload_1
    //   739: astore_2
    //   740: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   743: ifeq +33 -> 776
    //   746: aload_1
    //   747: astore_2
    //   748: ldc 96
    //   750: new 98	java/lang/StringBuilder
    //   753: dup
    //   754: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   757: ldc -77
    //   759: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: aload 7
    //   764: invokevirtual 182	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   767: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   773: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   776: aload_1
    //   777: ifnull -770 -> 7
    //   780: aload_1
    //   781: invokeinterface 167 1 0
    //   786: return
    //   787: astore_1
    //   788: aload 8
    //   790: astore_2
    //   791: aload_2
    //   792: ifnull +9 -> 801
    //   795: aload_2
    //   796: invokeinterface 167 1 0
    //   801: aload_1
    //   802: athrow
    //   803: astore_1
    //   804: goto -13 -> 791
    //   807: astore 7
    //   809: goto -71 -> 738
    //   812: goto -436 -> 376
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	815	0	this	CallBlockMissCallManager
    //   0	815	1	paramArrayList	ArrayList<CallLogData>
    //   0	815	2	paramContext	Context
    //   0	815	3	paramLong	long
    //   0	815	5	paramBoolean	boolean
    //   47	119	6	i1	int
    //   52	633	7	localObject1	Object
    //   734	29	7	localException1	Exception
    //   807	1	7	localException2	Exception
    //   1	788	8	localObject2	Object
    //   44	590	9	localA	android.support.v4.e.a
    //   72	447	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   567	583	701	java/lang/Exception
    //   585	591	701	java/lang/Exception
    //   593	619	701	java/lang/Exception
    //   621	631	701	java/lang/Exception
    //   633	645	701	java/lang/Exception
    //   647	659	701	java/lang/Exception
    //   661	667	701	java/lang/Exception
    //   669	698	701	java/lang/Exception
    //   244	251	734	java/lang/Exception
    //   256	306	734	java/lang/Exception
    //   306	376	734	java/lang/Exception
    //   376	407	734	java/lang/Exception
    //   407	432	734	java/lang/Exception
    //   487	537	734	java/lang/Exception
    //   244	251	787	finally
    //   256	306	787	finally
    //   306	376	787	finally
    //   376	407	787	finally
    //   407	432	787	finally
    //   487	537	787	finally
    //   438	447	803	finally
    //   449	455	803	finally
    //   457	464	803	finally
    //   470	476	803	finally
    //   542	550	803	finally
    //   552	560	803	finally
    //   567	583	803	finally
    //   585	591	803	finally
    //   593	619	803	finally
    //   621	631	803	finally
    //   633	645	803	finally
    //   647	659	803	finally
    //   661	667	803	finally
    //   669	698	803	finally
    //   707	713	803	finally
    //   715	723	803	finally
    //   740	746	803	finally
    //   748	776	803	finally
    //   438	447	807	java/lang/Exception
    //   449	455	807	java/lang/Exception
    //   457	464	807	java/lang/Exception
    //   470	476	807	java/lang/Exception
    //   542	550	807	java/lang/Exception
    //   552	560	807	java/lang/Exception
    //   707	713	807	java/lang/Exception
    //   715	723	807	java/lang/Exception
  }
  
  private static void a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      paramJSONObject.put(paramString1, paramString2);
      return;
    }
    catch (Exception paramJSONObject) {}
  }
  
  private static boolean a(int paramInt, CallerInfo paramCallerInfo)
  {
    if (paramCallerInfo == null) {}
    boolean bool1;
    boolean bool2;
    boolean bool3;
    do
    {
      return false;
      bool1 = CloudConfig.R();
      bool2 = a(paramCallerInfo);
      bool3 = CallBlockPref.a().aQ();
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "mc dlg block+add contact show_with_cloud=" + bool1);
      }
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "mc dlg block+add contact is_recognized=" + bool2);
      }
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "mc dlg block+add contact can_show_today=" + bool3);
      }
    } while ((!bool1) || (!bool3) || (paramInt == 0) || (!NetUtil.a()) || (!bool2));
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "mc dlg generic ad can show=true");
    }
    return true;
  }
  
  public static boolean a(int paramInt, CallerInfo paramCallerInfo, final CallSession paramCallSession)
  {
    boolean bool3;
    if ((paramCallerInfo == null) || (paramCallSession == null))
    {
      bool3 = false;
      return bool3;
    }
    b(0, 0);
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity from " + paramInt);
    }
    paramCallerInfo = null;
    long l1;
    if (paramCallSession != null)
    {
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity ringing time " + paramCallSession.h());
      }
      l1 = paramCallSession.h();
      paramCallerInfo = paramCallSession.j();
      if (paramCallerInfo == null) {
        break label595;
      }
    }
    label595:
    for (;;)
    {
      try
      {
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity caller " + paramCallerInfo);
        }
        String str;
        if (paramCallerInfo.d() != null)
        {
          str = "+" + String.valueOf(paramCallerInfo.d().b()) + paramCallerInfo.d().d();
          a().a(str, l1, paramCallSession.e(), paramCallSession.e() + paramCallSession.f());
          paramCallSession = CallBlocker.a();
          if (!CloudConfig.d())
          {
            if (DebugMode.a) {
              DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity not enable by mcc");
            }
            a(51);
            return false;
          }
        }
        else
        {
          str = paramCallerInfo.a;
          continue;
        }
      }
      catch (Exception paramCallSession)
      {
        continue;
        if (!Commons.e())
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity not agree to show");
          }
          a(52);
          return false;
        }
        if (!CallBlockPref.a().b())
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity call block switch not enable");
          }
          a(53);
          return false;
        }
        if (!CallBlockPref.a().y())
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivity call block misscall switch not enable");
          }
          a(54);
          return false;
        }
        final int i3 = CbAdHelper.a();
        if (DebugMode.a)
        {
          a(false);
          a(i3, paramCallerInfo);
        }
        if (paramInt == 4)
        {
          if (a(i3, paramCallerInfo))
          {
            b(14, 68);
            paramInt = 2;
            i1 = 0;
            i2 = 4;
            bool1 = true;
            if (paramInt != 2) {
              continue;
            }
            bool2 = true;
            paramCallerInfo = a().b();
            if (paramCallerInfo != null) {
              paramCallerInfo.postDelayed(new Runnable()
              {
                public void run()
                {
                  if (DebugMode.a) {
                    DebugMode.a("CallBlockMissCallManager", "checkIfShowMissCallActivityDelay from " + this.a + ", scene=" + i1 + ", sub scene=" + i2 + ",ad type=" + i3);
                  }
                  CallBlockMissCallManager.a(this.a, paramCallSession, i1, i2, i3);
                }
              }, 500L);
            }
            if (DebugMode.a) {
              DebugMode.a("CallBlockMissCallManager", "check_error_code_report: " + bool1 + ", code=" + i + ", report scene=" + j);
            }
            bool3 = bool2;
            if (!bool1) {
              break;
            }
            bool3 = bool2;
            if (i <= 0) {
              break;
            }
            a(j, i);
            return bool2;
          }
          if (l())
          {
            paramInt = 2;
            i1 = 1;
            i2 = 3;
            bool1 = false;
            continue;
          }
          if (m())
          {
            paramInt = 2;
            i1 = 1;
            i2 = 2;
            bool1 = true;
            continue;
          }
          b(15, 69);
          paramInt = 3;
          i2 = 1;
          i1 = 1;
          bool1 = true;
          continue;
          boolean bool2 = false;
          continue;
        }
        final int i2 = 1;
        final int i1 = 1;
        boolean bool1 = true;
      }
    }
  }
  
  private static boolean a(CallerInfo paramCallerInfo)
  {
    if (paramCallerInfo == null) {}
    while ((paramCallerInfo.g == null) || (paramCallerInfo.i) || (CallerInfo.j(paramCallerInfo))) {
      return false;
    }
    return true;
  }
  
  private static boolean a(boolean paramBoolean)
  {
    boolean bool1 = j();
    boolean bool2 = k();
    boolean bool3 = NetUtil.a();
    boolean bool4 = n();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "show_with_count_cloud " + bool1);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "show_with_pkg_install " + bool2);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "has_net " + bool3);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "show_with_count_local(new) " + bool4);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "has_what_call_installed " + m);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "canShowMissCallDlgToday " + CallBlockPref.a().at());
    }
    int i1;
    if (paramBoolean)
    {
      if (bool1) {
        break label290;
      }
      i1 = 55;
    }
    for (;;)
    {
      if (i1 > 0) {
        b(1, i1);
      }
      if ((bool1) && (bool4))
      {
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "show_with_pkg_install " + bool2);
        }
        if ((bool2) && (bool3) && (!m))
        {
          return true;
          label290:
          if (!bool4)
          {
            i1 = 59;
            continue;
          }
          if (!bool3)
          {
            i1 = 56;
            continue;
          }
          if (!bool2)
          {
            i1 = 57;
            continue;
          }
          if (!m) {
            break label336;
          }
          i1 = 58;
          continue;
        }
      }
      return false;
      label336:
      i1 = 0;
    }
  }
  
  public static void b(int paramInt)
  {
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "tryToDismissMissCallActivity");
    }
    try
    {
      if ((CallBlocker.b() != null) && (CallBlocker.b().getApplicationContext() != null)) {
        CallBlocker.b().getApplicationContext().sendBroadcast(new Intent("com.cleanmaster.security.callblock.ui.DISMISS_MISS_CALL"));
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      while (!DebugMode.a) {}
      DebugMode.a("CallBlockMissCallManager", "tryToDismissMissCallActivity fail");
    }
  }
  
  private static void b(int paramInt1, int paramInt2)
  {
    try
    {
      i = paramInt2;
      j = paramInt1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private static void b(String paramString, long paramLong)
  {
    a().a(paramString, paramLong);
  }
  
  private JSONObject c(String paramString)
  {
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "getRingingJsonInfo callblockpid " + Process.myPid());
    }
    try
    {
      if (this.c.has(paramString))
      {
        paramString = this.c.getJSONObject(paramString);
        if ((paramString.has("duration")) && (paramString.has("start")))
        {
          boolean bool = paramString.has("end");
          if (bool) {
            return paramString;
          }
        }
      }
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private void c(long paramLong)
  {
    i();
    try
    {
      Object localObject = new JSONObject();
      Iterator localIterator = this.c.keys();
      while (localIterator.hasNext())
      {
        String str = localIterator.next().toString();
        if (!TextUtils.isEmpty(str))
        {
          JSONObject localJSONObject = this.c.getJSONObject(str);
          if (localJSONObject != null)
          {
            long l1 = localJSONObject.optLong("start", -1L);
            if ((l1 > 0L) && (l1 > paramLong)) {
              ((JSONObject)localObject).put(str, localJSONObject);
            }
          }
        }
      }
      localObject = ((JSONObject)localObject).toString();
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "cleanRingingInfoBefore all new items " + (String)localObject);
      }
      CallBlocker.a().m().a("callblock_last_ringing_duration_map", (String)localObject);
      return;
    }
    catch (Exception localException) {}
  }
  
  private ArrayList<CallLogData> d(long paramLong)
  {
    CallBlocker.a();
    ArrayList localArrayList = new ArrayList();
    if (DebugMode.a) {}
    Object localObject = CallLogItemManger.a().a(paramLong, true);
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "get misscall bottom time  " + paramLong);
    }
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "no data ");
      }
      return null;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      CallLogItem localCallLogItem = (CallLogItem)((Iterator)localObject).next();
      if ((localCallLogItem != null) && (localCallLogItem.c() != null) && (localCallLogItem.c().equals("0000000000")))
      {
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "ignore private number");
        }
      }
      else
      {
        CallLogData localCallLogData = new CallLogData();
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "add item " + localCallLogItem);
        }
        localCallLogData.a = localCallLogItem;
        localArrayList.add(localCallLogData);
      }
    }
    a(localArrayList, CallBlocker.b(), paramLong, true);
    a(localArrayList);
    return localArrayList;
  }
  
  private void g() {}
  
  private void h()
  {
    try
    {
      String str = this.c.toString();
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "saveNumberRingingTimeMap all items " + str);
      }
      CallBlocker.a().m().a("callblock_last_ringing_duration_map", str);
      return;
    }
    catch (Exception localException) {}
  }
  
  private void i()
  {
    try
    {
      this.c = new JSONObject(CallBlocker.a().m().b("callblock_last_ringing_duration_map", ""));
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "loadNumberRingingTimeMap all items " + this.c.toString());
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private static boolean j()
  {
    return CloudConfig.A();
  }
  
  private static boolean k()
  {
    str1 = CloudConfig.H();
    str2 = CloudConfig.I();
    if (DebugMode.a)
    {
      DebugMode.a("CallBlockMissCallManager", "checkShowDialogWithPackage withPkg " + str1);
      DebugMode.a("CallBlockMissCallManager", "checkShowDialogWithPackage withSocialPkg " + str2);
    }
    try
    {
      localIterator = CallBlocker.b().getPackageManager().getInstalledApplications(128).iterator();
      i2 = 0;
      i1 = 0;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          Iterator localIterator;
          if (localIterator.hasNext())
          {
            localObject = (ApplicationInfo)localIterator.next();
            if ((localObject == null) || (TextUtils.isEmpty(((ApplicationInfo)localObject).packageName))) {
              continue;
            }
            localObject = ((ApplicationInfo)localObject).packageName.toLowerCase();
            if (!TextUtils.isEmpty(str1))
            {
              bool = str1.contains((CharSequence)localObject);
              if (!bool) {
                continue;
              }
            }
            i2 = 1;
          }
        }
        catch (Exception localException2)
        {
          int i2;
          int i1;
          int i4;
          int i3;
          Object localObject;
          boolean bool;
          continue;
          continue;
        }
        try
        {
          if (!TextUtils.isEmpty(str2))
          {
            bool = str2.contains((CharSequence)localObject);
            if (!bool) {
              continue;
            }
          }
          i1 = 1;
        }
        catch (Exception localException3)
        {
          continue;
          continue;
        }
        try
        {
          if (((String)localObject).equals("com.cmcm.whatscall")) {
            m = true;
          }
        }
        catch (Exception localException4) {}
      }
      localException1 = localException1;
      i2 = 0;
      i1 = 0;
      i3 = i1;
      i4 = i2;
      return (i3 != 0) && (i4 != 0);
    }
    i4 = i2;
    i3 = i1;
  }
  
  private static boolean l()
  {
    boolean bool1 = j();
    boolean bool2 = NetUtil.a();
    boolean bool3 = PackageInfoUtil.b(CallBlocker.b(), "com.cmcm.whatscall");
    boolean bool4 = o();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "show_with_count_cloud " + bool1);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "check if install whatscall " + bool3);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "check if has_net " + bool2);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "check if can_show_today " + bool4);
    }
    if (!bool1) {
      b(3, 55);
    }
    while ((bool1) && (bool3) && (bool4))
    {
      return true;
      if (!bool4) {
        b(3, 59);
      } else if (!bool3) {
        b(3, 55);
      }
    }
    return false;
  }
  
  private static boolean m()
  {
    boolean bool1 = j();
    boolean bool2 = NetUtil.a();
    boolean bool3 = o();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "check if can_show_today " + bool3);
    }
    if (!bool1) {
      b(2, 55);
    }
    while ((bool1) && (bool2) && (bool3))
    {
      return true;
      if (!bool3) {
        b(2, 59);
      } else if (!bool2) {
        b(2, 56);
      }
    }
    return false;
  }
  
  private static boolean n()
  {
    if (CallBlockPref.a().an()) {}
    while (!CallBlockPref.a().at()) {
      return false;
    }
    return true;
  }
  
  private static boolean o()
  {
    if (CallBlockPref.a().ap()) {}
    while (!CallBlockPref.a().ar()) {
      return false;
    }
    return true;
  }
  
  private static void p() {}
  
  public void a(int paramInt, RemoteViews paramRemoteViews, long paramLong, CallblockExtData paramCallblockExtData)
  {
    try
    {
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "setCurrentNotify " + paramInt + ", v=" + paramRemoteViews + ", session=" + paramLong);
      }
      this.d = paramInt;
      this.e = paramRemoteViews;
      this.f = paramLong;
      this.g = paramCallblockExtData;
      return;
    }
    finally {}
  }
  
  public void a(long paramLong)
  {
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "set check time  " + paramLong);
    }
    IPref localIPref = CallBlocker.a().m();
    if (localIPref == null) {
      return;
    }
    localIPref.a("callblock_last_check_miss_call_list_ts", paramLong);
    c(paramLong);
  }
  
  /* Error */
  public void a(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 63	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:f	J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifle +378 -> 386
    //   11: aload_0
    //   12: getfield 61	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:e	Landroid/widget/RemoteViews;
    //   15: ifnull +371 -> 386
    //   18: invokestatic 189	com/cleanmaster/security/callblock/CallBlocker:b	()Landroid/content/Context;
    //   21: astore 4
    //   23: aload 4
    //   25: invokestatic 496	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   28: getstatic 499	com/cleanmaster/security/callblock/R$layout:callblock_calllog_list_gen_portrait	I
    //   31: aconst_null
    //   32: invokevirtual 503	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   35: astore_3
    //   36: aload_3
    //   37: getstatic 522	com/cleanmaster/security/callblock/R$id:iv_calllog_photo	I
    //   40: invokevirtual 512	android/view/View:findViewById	(I)Landroid/view/View;
    //   43: checkcast 1072	com/cleanmaster/security/callblock/common/ui/CircleImageView
    //   46: astore 5
    //   48: aload_0
    //   49: getfield 1062	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:g	Lcom/cleanmaster/security/callblock/data/CallblockExtData;
    //   52: ifnull +27 -> 79
    //   55: aload 5
    //   57: aload_0
    //   58: getfield 1062	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:g	Lcom/cleanmaster/security/callblock/data/CallblockExtData;
    //   61: getfield 526	com/cleanmaster/security/callblock/data/CallblockExtData:f	I
    //   64: invokevirtual 1075	com/cleanmaster/security/callblock/common/ui/CircleImageView:setCircleImageSize	(I)V
    //   67: aload 5
    //   69: aload_0
    //   70: getfield 1062	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:g	Lcom/cleanmaster/security/callblock/data/CallblockExtData;
    //   73: getfield 524	com/cleanmaster/security/callblock/data/CallblockExtData:e	I
    //   76: invokevirtual 1078	com/cleanmaster/security/callblock/common/ui/CircleImageView:setCircleImageType	(I)V
    //   79: aload 5
    //   81: iconst_0
    //   82: invokevirtual 1079	com/cleanmaster/security/callblock/common/ui/CircleImageView:setVisibility	(I)V
    //   85: aload 5
    //   87: aload_1
    //   88: invokevirtual 1082	com/cleanmaster/security/callblock/common/ui/CircleImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   91: aload_3
    //   92: getstatic 506	com/cleanmaster/security/callblock/R$id:call_item_emoji	I
    //   95: invokevirtual 512	android/view/View:findViewById	(I)Landroid/view/View;
    //   98: iconst_4
    //   99: invokevirtual 1083	android/view/View:setVisibility	(I)V
    //   102: aload_3
    //   103: getstatic 517	com/cleanmaster/security/callblock/R$id:call_item_show_card_char	I
    //   106: invokevirtual 512	android/view/View:findViewById	(I)Landroid/view/View;
    //   109: iconst_4
    //   110: invokevirtual 1083	android/view/View:setVisibility	(I)V
    //   113: aload 4
    //   115: invokevirtual 319	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   118: getstatic 559	com/cleanmaster/security/callblock/R$dimen:missed_call_notify_portrait_size	I
    //   121: invokevirtual 563	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   124: istore_2
    //   125: aload_3
    //   126: ldc_w 564
    //   129: iload_2
    //   130: ior
    //   131: ldc_w 564
    //   134: iload_2
    //   135: ior
    //   136: invokevirtual 567	android/view/View:measure	(II)V
    //   139: aload_3
    //   140: iconst_0
    //   141: iconst_0
    //   142: iload_2
    //   143: iload_2
    //   144: invokevirtual 571	android/view/View:layout	(IIII)V
    //   147: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   150: ifeq +32 -> 182
    //   153: ldc 96
    //   155: new 98	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   162: ldc_w 1085
    //   165: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: aload 5
    //   170: invokevirtual 1088	com/cleanmaster/security/callblock/common/ui/CircleImageView:getWidth	()I
    //   173: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   176: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   185: ifeq +32 -> 217
    //   188: ldc 96
    //   190: new 98	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   197: ldc_w 1085
    //   200: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload 5
    //   205: invokevirtual 1091	com/cleanmaster/security/callblock/common/ui/CircleImageView:getHeight	()I
    //   208: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   211: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   217: aload_3
    //   218: invokevirtual 574	android/view/View:buildDrawingCache	()V
    //   221: aload_3
    //   222: invokevirtual 578	android/view/View:getDrawingCache	()Landroid/graphics/Bitmap;
    //   225: astore 4
    //   227: aload 4
    //   229: iload_2
    //   230: iload_2
    //   231: invokestatic 583	com/cleanmaster/security/callblock/utils/PhotoUtils:a	(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    //   234: astore_1
    //   235: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   238: ifeq +32 -> 270
    //   241: ldc 96
    //   243: new 98	java/lang/StringBuilder
    //   246: dup
    //   247: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   250: ldc_w 1093
    //   253: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: aload 4
    //   258: invokevirtual 1094	android/graphics/Bitmap:getWidth	()I
    //   261: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   264: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   270: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   273: ifeq +32 -> 305
    //   276: ldc 96
    //   278: new 98	java/lang/StringBuilder
    //   281: dup
    //   282: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   285: ldc_w 1096
    //   288: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 4
    //   293: invokevirtual 1097	android/graphics/Bitmap:getHeight	()I
    //   296: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   302: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   305: aload_3
    //   306: invokevirtual 590	android/view/View:destroyDrawingCache	()V
    //   309: new 337	com/cleanmaster/security/callblock/data/CallblockExtData
    //   312: dup
    //   313: invokespecial 451	com/cleanmaster/security/callblock/data/CallblockExtData:<init>	()V
    //   316: astore_3
    //   317: aload_3
    //   318: aload_0
    //   319: getfield 1062	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:g	Lcom/cleanmaster/security/callblock/data/CallblockExtData;
    //   322: getfield 343	com/cleanmaster/security/callblock/data/CallblockExtData:a	Landroid/content/Intent;
    //   325: putfield 343	com/cleanmaster/security/callblock/data/CallblockExtData:a	Landroid/content/Intent;
    //   328: aload_3
    //   329: ldc_w 258
    //   332: putfield 453	com/cleanmaster/security/callblock/data/CallblockExtData:c	Ljava/lang/String;
    //   335: aload_3
    //   336: aload_0
    //   337: getfield 61	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:e	Landroid/widget/RemoteViews;
    //   340: putfield 464	com/cleanmaster/security/callblock/data/CallblockExtData:b	Ljava/lang/Object;
    //   343: aload_0
    //   344: getfield 61	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:e	Landroid/widget/RemoteViews;
    //   347: getstatic 522	com/cleanmaster/security/callblock/R$id:iv_calllog_photo	I
    //   350: iconst_0
    //   351: invokevirtual 253	android/widget/RemoteViews:setViewVisibility	(II)V
    //   354: aload_0
    //   355: getfield 61	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:e	Landroid/widget/RemoteViews;
    //   358: getstatic 522	com/cleanmaster/security/callblock/R$id:iv_calllog_photo	I
    //   361: aload_1
    //   362: invokevirtual 587	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
    //   365: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   368: ifeq +11 -> 379
    //   371: ldc 96
    //   373: ldc_w 1099
    //   376: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   379: invokestatic 192	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:a	()Lcom/cleanmaster/security/callblock/misscall/CallBlockMissCallManager;
    //   382: aload_3
    //   383: invokevirtual 470	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:a	(Lcom/cleanmaster/security/callblock/data/CallblockExtData;)V
    //   386: aload_0
    //   387: monitorexit
    //   388: return
    //   389: astore_1
    //   390: aload_0
    //   391: monitorexit
    //   392: aload_1
    //   393: athrow
    //   394: astore_1
    //   395: goto -9 -> 386
    //   398: astore_1
    //   399: goto -13 -> 386
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	402	0	this	CallBlockMissCallManager
    //   0	402	1	paramBitmap	Bitmap
    //   124	107	2	i1	int
    //   35	348	3	localObject1	Object
    //   21	271	4	localObject2	Object
    //   46	158	5	localCircleImageView	com.cleanmaster.security.callblock.common.ui.CircleImageView
    // Exception table:
    //   from	to	target	type
    //   2	79	389	finally
    //   79	125	389	finally
    //   125	182	389	finally
    //   182	217	389	finally
    //   217	270	389	finally
    //   270	305	389	finally
    //   305	379	389	finally
    //   379	386	389	finally
    //   2	79	394	java/lang/Exception
    //   79	125	394	java/lang/Exception
    //   125	182	398	java/lang/Exception
    //   182	217	398	java/lang/Exception
    //   217	270	398	java/lang/Exception
    //   270	305	398	java/lang/Exception
    //   305	379	398	java/lang/Exception
    //   379	386	398	java/lang/Exception
  }
  
  public void a(CallblockExtData paramCallblockExtData)
  {
    try
    {
      String str = CallBlocker.b().getResources().getString(R.string.intl_cmsecurity_callblock_missedcall_notifications_one_missedcall_title);
      CallBlocker.a().o().a(8001, str, "", "", paramCallblockExtData);
      return;
    }
    catch (Exception paramCallblockExtData) {}
  }
  
  public void a(String paramString)
  {
    long l1 = System.currentTimeMillis();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "outgoing call check " + l1 + "," + paramString);
    }
    a(l1);
  }
  
  public void a(String paramString, final long paramLong)
  {
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto " + paramString + ",key=" + paramLong);
    }
    if (!TextUtils.isEmpty(paramString))
    {
      UIUtils.ImageSize localImageSize = new UIUtils.ImageSize();
      CallBlocker.a().a(paramString, localImageSize, new ImageLoadingListener()
      {
        public void a(String paramAnonymousString, View paramAnonymousView)
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto onLoadingStarted");
          }
        }
        
        public void a(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto onLoadingComplete k1 " + paramLong);
          }
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto onLoadingComplete k2 " + CallBlockMissCallManager.a(CallBlockMissCallManager.this));
          }
          if ((paramLong != 0L) && (paramLong == CallBlockMissCallManager.a(CallBlockMissCallManager.this))) {
            CallBlockMissCallManager.this.a(paramAnonymousBitmap);
          }
        }
        
        public void b(String paramAnonymousString, View paramAnonymousView)
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto onLoadingFailed");
          }
        }
        
        public void c(String paramAnonymousString, View paramAnonymousView)
        {
          if (DebugMode.a) {
            DebugMode.a("CallBlockMissCallManager", "loadPortraitPhoto onLoadingCancelled");
          }
        }
      });
    }
  }
  
  public void a(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    i();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "setNumberRingingTime callblock pid " + Process.myPid());
    }
    try
    {
      if (this.c.has(paramString)) {}
      JSONObject localJSONObject;
      for (paramString = this.c.getJSONObject(paramString);; paramString = localJSONObject)
      {
        paramString.put("duration", paramLong1);
        paramString.put("start", paramLong2);
        paramString.put("end", paramLong3);
        paramString.put("ts", System.currentTimeMillis());
        if (DebugMode.a) {
          DebugMode.a("CallBlockMissCallManager", "setNumberRingingTime all items " + this.c.toString());
        }
        h();
        return;
        localJSONObject = new JSONObject();
        this.c.put(paramString, localJSONObject);
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  public Handler b()
  {
    return this.h;
  }
  
  public CallLogItem b(String paramString)
  {
    return CallLogItemManger.a().a(paramString);
  }
  
  public ArrayList<CallLogData> b(long paramLong)
  {
    long l1 = c();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "data bottom ts " + l1);
    }
    long l2 = System.currentTimeMillis() - 86400000L;
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "should reset check ts, expired..last24h " + l2);
    }
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "should reset check ts, expired..check lastSee " + l1);
    }
    l2 = Math.max(l2, l1);
    l1 = l2;
    if (l2 < 0L) {
      l1 = 0L;
    }
    if (paramLong > 0L) {}
    for (;;)
    {
      if (DebugMode.a) {
        DebugMode.a("CallBlockMissCallManager", "data bottom ts to got finally " + paramLong);
      }
      g();
      return d(paramLong);
      paramLong = l1;
    }
  }
  
  public long c()
  {
    IPref localIPref = CallBlocker.a().m();
    if (localIPref == null)
    {
      l2 = System.currentTimeMillis();
      l1 = l2;
      if (l2 > 10000L) {
        l1 = l2 - 10000L;
      }
    }
    do
    {
      return l1;
      l2 = localIPref.b("callblock_last_check_miss_call_list_ts", -1L);
      l1 = l2;
    } while (l2 >= 0L);
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "first time, set to current");
    }
    long l2 = System.currentTimeMillis();
    long l1 = l2;
    if (l2 > 10000L) {
      l1 = l2 - 10000L;
    }
    localIPref.a("callblock_last_check_miss_call_list_ts", l1);
    return l1;
  }
  
  public ArrayList<CallLogData> d()
  {
    return b(-1L);
  }
  
  public long e()
  {
    long l1 = c();
    if (DebugMode.a) {
      DebugMode.a("CallBlockMissCallManager", "data bottom ts " + l1);
    }
    l1 = Math.max(System.currentTimeMillis() - 86400000L, l1);
    if (l1 < 0L) {
      return 0L;
    }
    return l1;
  }
  
  /* Error */
  public void f()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 65	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:k	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +79 -> 87
    //   11: invokestatic 189	com/cleanmaster/security/callblock/CallBlocker:b	()Landroid/content/Context;
    //   14: ifnull +73 -> 87
    //   17: invokestatic 189	com/cleanmaster/security/callblock/CallBlocker:b	()Landroid/content/Context;
    //   20: invokevirtual 925	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   23: ifnull +64 -> 87
    //   26: invokestatic 189	com/cleanmaster/security/callblock/CallBlocker:b	()Landroid/content/Context;
    //   29: invokevirtual 925	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   32: astore_2
    //   33: new 1168	android/content/IntentFilter
    //   36: dup
    //   37: ldc_w 258
    //   40: invokespecial 1169	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   43: astore_3
    //   44: aload_3
    //   45: ldc_w 1171
    //   48: invokevirtual 1174	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   51: aload_3
    //   52: ldc_w 1176
    //   55: invokevirtual 1174	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   58: aload_2
    //   59: aload_0
    //   60: getfield 70	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:l	Lcom/cleanmaster/security/CmsBaseReceiver;
    //   63: aload_3
    //   64: invokevirtual 1180	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   67: pop
    //   68: aload_0
    //   69: iconst_1
    //   70: putfield 65	com/cleanmaster/security/callblock/misscall/CallBlockMissCallManager:k	Z
    //   73: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   76: ifeq +11 -> 87
    //   79: ldc 96
    //   81: ldc_w 1182
    //   84: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: aload_0
    //   88: monitorexit
    //   89: return
    //   90: astore_2
    //   91: getstatic 94	com/cleanmaster/security/callblock/utils/DebugMode:a	Z
    //   94: ifeq -7 -> 87
    //   97: ldc 96
    //   99: ldc_w 1184
    //   102: invokestatic 112	com/cleanmaster/security/callblock/utils/DebugMode:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   105: goto -18 -> 87
    //   108: astore_2
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	CallBlockMissCallManager
    //   6	2	1	bool	boolean
    //   32	27	2	localContext	Context
    //   90	1	2	localThrowable	Throwable
    //   108	4	2	localObject	Object
    //   43	21	3	localIntentFilter	android.content.IntentFilter
    // Exception table:
    //   from	to	target	type
    //   11	87	90	java/lang/Throwable
    //   2	7	108	finally
    //   11	87	108	finally
    //   91	105	108	finally
  }
}
