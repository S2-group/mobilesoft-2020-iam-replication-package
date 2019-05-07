package com.intsig.camscanner.control;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.intsig.camscanner.ImageShareActivity;
import com.intsig.camscanner.ScannerApplication;
import com.intsig.camscanner.SecureLinkActivity;
import com.intsig.camscanner.UploadFaxPrintActivity;
import com.intsig.camscanner.a.bv;
import com.intsig.camscanner.a.j;
import com.intsig.camscanner.ads.b.g;
import com.intsig.camscanner.provider.k;
import com.intsig.camscanner.provider.o;
import com.intsig.camscanner.service.ImageRegisterService;
import com.intsig.datastruct.DocumentListItem;
import com.intsig.k.a.h;
import com.intsig.nativelib.OcrLanguage;
import com.intsig.ocrapi.aj;
import com.intsig.pdfengine.PDF_Util.OnPdfCreateListener;
import com.intsig.tsapp.sync.AppConfigJson.ShareDoc;
import com.intsig.tsapp.sync.AppConfigJson.WxApp;
import com.intsig.tsapp.sync.av;
import com.intsig.util.bg;
import com.intsig.util.bo;
import com.intsig.util.bu;
import com.intsig.util.r;
import com.intsig.util.t;
import com.intsig.utils.aa;
import com.intsig.utils.ad;
import com.intsig.utils.l;
import com.intsig.utils.p;
import com.intsig.utils.x;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;

public class ax
{
  private static boolean b = true;
  private static ax c;
  private String a;
  private long d = 0L;
  private long e = 0L;
  private ArrayList<String> f;
  private com.intsig.snslogin.a.a g;
  private ad h = new br(this);
  private Context i;
  private String j;
  private int k;
  private boolean l;
  private boolean m;
  private boolean n;
  private cw o;
  
  private ax()
  {
    b = false;
  }
  
  public static long a(Context paramContext, ArrayList<Long> paramArrayList)
  {
    long l1 = 0L;
    if (paramArrayList != null)
    {
      if (paramArrayList.size() == 0) {
        return 0L;
      }
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext()) {
        l1 += bo.a(((Long)paramArrayList.next()).longValue(), paramContext);
      }
      return l1;
    }
    return 0L;
  }
  
  private Intent a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    com.intsig.q.e.b("ShareControl", "getOneImageJpgShareIntent");
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("image/jpeg");
    if (TextUtils.isEmpty(paramString4))
    {
      localObject1 = "";
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("_");
      ((StringBuilder)localObject1).append(paramString4);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    Object localObject2 = t.d();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append(j.c(paramString3));
    localStringBuilder.append("_");
    paramInt += 1;
    localStringBuilder.append(paramInt);
    localStringBuilder.append((String)localObject1);
    Object localObject1 = localStringBuilder.toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(".jpg");
    localObject2 = ((StringBuilder)localObject2).toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("_snap.jpg");
    this.j = localStringBuilder.toString();
    p.a(paramString1, (String)localObject2);
    p.a(paramString2, this.j);
    new Thread(new ba(this, (String)localObject2, paramString1, paramString2), "go2Share").start();
    localIntent.putExtra("android.intent.extra.STREAM", p.f((String)localObject2));
    if (TextUtils.isEmpty(paramString4))
    {
      paramContext = bg.a(paramContext, paramString3, paramInt, 2);
    }
    else
    {
      paramContext = new StringBuilder();
      paramContext.append(paramString3);
      paramContext.append(" - ");
      paramContext.append(paramString4);
      paramContext = paramContext.toString();
    }
    localIntent.putExtra("android.intent.extra.SUBJECT", paramContext);
    return localIntent;
  }
  
  private static ResolveInfo a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    Object localObject3 = new Intent("android.intent.action.SEND", null, paramContext, UploadFaxPrintActivity.class);
    ((Intent)localObject3).putExtra("SEND_TYPE", 10);
    localObject3 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject3, 65536);
    if (localObject3 != null)
    {
      localObject3 = ((List)localObject3).iterator();
      for (;;)
      {
        localObject2 = localObject1;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        localObject2 = (ResolveInfo)((Iterator)localObject3).next();
        if (((ResolveInfo)localObject2).loadLabel(paramContext.getPackageManager()).equals(paramContext.getString(2131690113)))
        {
          if (!paramBoolean1) {
            ((ResolveInfo)localObject2).labelRes = 2131690114;
          } else if (!paramBoolean2) {
            ((ResolveInfo)localObject2).labelRes = 2131690931;
          }
          localObject1 = localObject2;
        }
      }
    }
    return localObject2;
  }
  
  public static ax a()
  {
    if (c == null) {
      c = new ax();
    }
    return c;
  }
  
  private String a(Context paramContext, String paramString1, String paramString2)
  {
    return aj.a(paramContext).a(OcrLanguage.getLanguage(), paramString1, paramString2);
  }
  
  private ArrayList<cs> a(Context paramContext, Long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getContentResolver().query(o.a(paramLong.longValue()), new String[] { "ocr_border", "_id", "_data", "image_backup" }, null, null, null);
    if (paramContext != null)
    {
      while (paramContext.moveToNext())
      {
        Object localObject = paramContext.getString(0);
        if (!new com.intsig.tsapp.sync.y().c((String)localObject))
        {
          localArrayList.add(new cs(this, paramLong, Long.valueOf(paramContext.getLong(1)), paramContext.getString(2), paramContext.getString(3)));
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("test no ocr text ,page id:");
          ((StringBuilder)localObject).append(paramContext.getLong(1));
          com.intsig.q.e.c("ShareControl", ((StringBuilder)localObject).toString());
        }
      }
      paramContext.close();
    }
    return localArrayList;
  }
  
  public static ArrayList<String> a(String paramString)
  {
    Object localObject = paramString;
    if (TextUtils.isEmpty(paramString)) {
      localObject = "Doc";
    }
    paramString = j.c((String)localObject);
    localObject = t.d();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".zip");
    paramString = new File((String)localObject, localStringBuilder.toString());
    localObject = new ArrayList();
    ((ArrayList)localObject).add(paramString.getAbsolutePath());
    return localObject;
  }
  
  public static ArrayList<String> a(List<DocumentListItem> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramList != null) && (paramList.size() > 0))
    {
      String str = t.d();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        DocumentListItem localDocumentListItem = (DocumentListItem)localIterator.next();
        paramList = new StringBuilder();
        paramList.append(localDocumentListItem.c);
        paramList.append(".zip");
        paramList = new File(str, paramList.toString());
        int i1 = 1;
        while (localArrayList.contains(paramList.getAbsolutePath()))
        {
          paramList = new StringBuilder();
          paramList.append(localDocumentListItem.c);
          paramList.append("_(");
          paramList.append(i1);
          paramList.append(").zip");
          paramList = new File(str, paramList.toString());
          i1 += 1;
        }
        localArrayList.add(paramList.getAbsolutePath());
      }
    }
    return localArrayList;
  }
  
  private ArrayList<ResolveInfo> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (!this.n) {
      if (paramBoolean)
      {
        if (this.k == 1)
        {
          localArrayList.add(d(this.i));
          return localArrayList;
        }
      }
      else
      {
        if (this.k == 2)
        {
          localArrayList.add(a(this.i, true ^ this.m, this.l));
          return localArrayList;
        }
        if (this.k == 1)
        {
          localArrayList.add(e(this.i));
          localArrayList.add(d(this.i));
          localArrayList.add(a(this.i, true ^ this.m, this.l));
          return localArrayList;
        }
        if (this.k == 4)
        {
          localArrayList.add(a(this.i, true ^ this.m, this.l));
          return localArrayList;
        }
        if (this.k == 3) {
          localArrayList.add(a(this.i, true ^ this.m, this.l));
        }
      }
    }
    return localArrayList;
  }
  
  private void a(int paramInt)
  {
    if (paramInt == 0)
    {
      com.intsig.q.f.a(31028);
      return;
    }
    if (1 == paramInt)
    {
      com.intsig.q.f.a(31029);
      return;
    }
    if (2 == paramInt) {
      com.intsig.q.f.a(31030);
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    a(paramActivity, localArrayList);
  }
  
  private void a(Activity paramActivity, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramActivity, ImageShareActivity.class);
    localIntent.putExtra("piccccc", paramString1);
    localIntent.putExtra("fekfje", paramString2);
    paramActivity.startActivity(localIntent);
  }
  
  public static void a(Activity paramActivity, ArrayList<String> paramArrayList)
  {
    a(paramActivity, paramArrayList, null);
  }
  
  public static void a(Activity paramActivity, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    a(paramActivity, paramArrayList1, paramArrayList2, null, null);
  }
  
  public static void a(Activity paramActivity, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, String paramString1, String paramString2)
  {
    if (t.a(paramActivity))
    {
      new Thread(new bf(paramActivity, paramArrayList1, paramArrayList2, paramString1, paramString2)).start();
      return;
    }
    com.intsig.q.e.b("ShareControl", "SD is not available");
  }
  
  private void a(Activity paramActivity, String[] paramArrayOfString, String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean, ad paramAd, ArrayList<ResolveInfo> paramArrayList)
  {
    com.intsig.q.e.b("ShareControl", "go2ShareMultiFiles");
    if (paramAd == null) {
      paramAd = this.h;
    }
    if ((paramActivity != null) && (paramArrayOfString != null))
    {
      ArrayList localArrayList = new ArrayList();
      int i2 = paramArrayOfString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        localArrayList.add(p.f(paramArrayOfString[i1]));
        i1 += 1;
      }
      if (localArrayList.size() > 0)
      {
        Intent localIntent = new Intent();
        localIntent.setType(paramString2);
        if (!TextUtils.isEmpty(paramString3)) {
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString3);
        }
        if (localArrayList.size() > 1)
        {
          localIntent.setAction("android.intent.action.SEND_MULTIPLE");
          localIntent.putExtra("android.intent.extra.STREAM", localArrayList);
        }
        else
        {
          localIntent.setAction("android.intent.action.SEND");
          localIntent.putExtra("android.intent.extra.STREAM", p.f(paramArrayOfString[0]));
        }
        if (!TextUtils.isEmpty(paramString1))
        {
          com.intsig.camscanner.a.cb.a(localIntent, paramString1, true);
          aa.a().a(paramActivity, localIntent, paramAd, paramBoolean, paramInt, paramArrayList);
        }
        aa.a().a(paramActivity, localIntent, paramAd, paramBoolean, paramInt, paramArrayList);
        paramActivity = new StringBuilder();
        paramActivity.append("go2ShareMultiTxtFiles to ");
        paramActivity.append(paramString1);
        paramActivity.append(", subject = ");
        paramActivity.append(paramString3);
        com.intsig.q.e.b("ShareControl", paramActivity.toString());
        return;
      }
      com.intsig.q.e.b("ShareControl", "go2ShareMultiTxtFiles uris are null");
      return;
    }
    com.intsig.q.e.b("ShareControl", "go2ShareMultiTxtFiles activity or files are null");
  }
  
  private void a(Activity paramActivity, String[] paramArrayOfString, String paramString1, String paramString2, String paramString3, ad paramAd)
  {
    a(paramActivity, paramArrayOfString, paramString1, paramString2, paramString3, 5, this.n, paramAd, null);
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject1 = paramIntent.getComponent();
    if (localObject1 != null)
    {
      localObject1 = ((ComponentName)localObject1).getPackageName();
      Object localObject2 = paramContext.getResources().getStringArray(2130903046);
      if ((b) || (Arrays.asList((Object[])localObject2).contains(localObject1))) {
        try
        {
          localObject2 = paramContext.getPackageManager().getPackageInfo((String)localObject1, 0);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("packageName=");
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append(" versionCode=");
          ((StringBuilder)localObject3).append(((PackageInfo)localObject2).versionCode);
          com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject3).toString());
          com.intsig.camscanner.a.cb.b(paramIntent);
          localObject1 = null;
        }
        catch (Exception paramContext)
        {
          Object localObject3;
          label129:
          com.intsig.q.e.b("ShareControl", paramContext);
          return;
        }
      }
    }
    try
    {
      localObject2 = paramIntent.getSerializableExtra("android.intent.extra.STREAM");
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      break label129;
    }
    if (localObject1 == null)
    {
      localObject1 = (Uri)paramIntent.getParcelableExtra("android.intent.extra.STREAM");
      if (localObject1 == null)
      {
        localObject2 = paramIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0))
        {
          localObject1 = new ArrayList();
          localObject2 = ((ArrayList)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (Uri)((Iterator)localObject2).next();
            if ("content".equals(((Uri)localObject3).getScheme()))
            {
              ((ArrayList)localObject1).add(localObject3);
            }
            else
            {
              localObject3 = ((Uri)localObject3).getPath();
              if (!TextUtils.isEmpty((CharSequence)localObject3)) {
                ((ArrayList)localObject1).add(p.a(paramContext, (String)localObject3));
              }
            }
          }
          if (((ArrayList)localObject1).size() > 0) {
            paramIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject1);
          }
        }
      }
      else
      {
        if ("content".equals(((Uri)localObject1).getScheme())) {
          return;
        }
        localObject1 = ((Uri)localObject1).getPath();
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          paramIntent.putExtra("android.intent.extra.STREAM", p.a(paramContext, (String)localObject1));
        }
      }
    }
    else if ((localObject1 instanceof Uri))
    {
      localObject1 = (Uri)localObject1;
      if ("content".equals(((Uri)localObject1).getScheme())) {
        return;
      }
      localObject1 = ((Uri)localObject1).getPath();
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        paramIntent.putExtra("android.intent.extra.STREAM", p.a(paramContext, (String)localObject1));
      }
    }
    else if ((localObject1 instanceof ArrayList))
    {
      localObject2 = (ArrayList)localObject1;
      localObject1 = new ArrayList();
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Uri)((Iterator)localObject2).next();
        if ("content".equals(((Uri)localObject3).getScheme()))
        {
          ((ArrayList)localObject1).add(localObject3);
        }
        else
        {
          localObject3 = ((Uri)localObject3).getPath();
          if (!TextUtils.isEmpty((CharSequence)localObject3)) {
            ((ArrayList)localObject1).add(p.a(paramContext, (String)localObject3));
          }
        }
      }
      if (((ArrayList)localObject1).size() > 0)
      {
        paramIntent.putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
        return;
      }
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent, long paramLong, String paramString1, String paramString2, cx paramCx, com.intsig.utils.q paramQ)
  {
    a(paramContext, paramIntent, paramLong, paramString1, paramString2, paramCx, true, paramQ);
  }
  
  public static void a(Context paramContext, Intent paramIntent, long paramLong, String paramString1, String paramString2, cx paramCx, boolean paramBoolean, com.intsig.utils.q paramQ)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shareDocJpgs: ");
    localStringBuilder.append(paramLong);
    localStringBuilder.append(", title = ");
    localStringBuilder.append(paramString2);
    com.intsig.q.e.b("ShareControl", localStringBuilder.toString());
    new e(paramContext, new bh(paramContext, paramLong, paramString1, paramString2, paramBoolean, paramQ, paramIntent, paramCx), paramContext.getString(2131689665)).a();
  }
  
  public static void a(Context paramContext, View paramView)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2131427523, null);
    LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131296928);
    paramView.setBackgroundResource(2131230836);
    localLinearLayout.addView(paramView);
    paramContext = new com.intsig.d.c(paramContext, 2131755178).a(localView).a(0).a(new bp(localLinearLayout)).a();
    localView.findViewById(2131296801).setOnClickListener(new bq(paramContext));
    paramContext.getWindow().getDecorView().setBackgroundColor(0);
    try
    {
      paramContext.show();
      return;
    }
    catch (RuntimeException paramContext) {}
  }
  
  private void a(Context paramContext, com.intsig.k.a.c paramC, String paramString)
  {
    if (paramC == null)
    {
      com.intsig.q.e.b("ShareControl", "docShareLinkInfo == null");
      return;
    }
    for (;;)
    {
      try
      {
        String str = paramC.a().replace("\n", "");
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("webPageUrl: ");
        ((StringBuilder)localObject1).append(str);
        com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
        localObject1 = r.T();
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("get WXMini info from local: ");
        ((StringBuilder)localObject2).append((String)localObject1);
        com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject2).toString());
        AppConfigJson.WxApp localWxApp = new AppConfigJson.WxApp((String)localObject1);
        localObject2 = "";
        localObject1 = localObject2;
        if (str != null)
        {
          localObject1 = localObject2;
          if (str.contains("?"))
          {
            localObject1 = str.substring(str.indexOf("?"), str.length());
            if (!TextUtils.isEmpty((CharSequence)localObject1))
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(localWxApp.share_doc.path);
              ((StringBuilder)localObject2).append((String)localObject1);
              localObject2 = ((StringBuilder)localObject2).toString();
              localObject1 = localObject2;
              if (!TextUtils.isEmpty(paramC.c()))
              {
                localObject1 = new StringBuilder();
                if (!paramC.b()) {
                  break label447;
                }
                i1 = 2;
                ((StringBuilder)localObject1).append(i1);
                ((StringBuilder)localObject1).append("");
                localObject1 = ((StringBuilder)localObject1).toString();
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append((String)localObject2);
                localStringBuilder.append("&area=");
                localStringBuilder.append((String)localObject1);
                localObject1 = localStringBuilder.toString();
              }
            }
            else
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("webPageUrl format error： ");
              ((StringBuilder)localObject1).append(str);
              com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
              localObject1 = localObject2;
            }
          }
        }
        com.intsig.q.e.b("ShareControl", "complete to assemble arguments ,what comes now is to share to WXMini");
        a(paramContext, localWxApp, str, (String)localObject1, paramC.d(), paramString);
        return;
      }
      catch (NullPointerException paramContext)
      {
        com.intsig.q.e.b("ShareControl", paramContext.toString());
        paramContext.printStackTrace();
        return;
      }
      catch (JSONException paramContext)
      {
        com.intsig.q.e.b("ShareControl", paramContext.toString());
        paramContext.printStackTrace();
        return;
      }
      catch (ArrayIndexOutOfBoundsException paramContext)
      {
        paramC = new StringBuilder();
        paramC.append("check webPageUrl ");
        paramC.append(paramContext.toString());
        com.intsig.q.e.b("ShareControl", paramC.toString());
        paramContext.printStackTrace();
        return;
      }
      label447:
      int i1 = 1;
    }
  }
  
  private void a(Context paramContext, AppConfigJson.WxApp paramWxApp, String paramString1, String paramString2, byte[] paramArrayOfByte, String paramString3)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("share to WXMini webPageUrl: ");
    ((StringBuilder)localObject).append(paramString1);
    ((StringBuilder)localObject).append("  path: ");
    ((StringBuilder)localObject).append(paramString2);
    com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject).toString());
    localObject = com.intsig.v.b.a();
    WXMiniProgramObject localWXMiniProgramObject = new WXMiniProgramObject();
    localWXMiniProgramObject.webpageUrl = paramString1;
    localWXMiniProgramObject.userName = paramWxApp.share_doc.user_name;
    localWXMiniProgramObject.path = paramString2;
    localWXMiniProgramObject.withShareTicket = true;
    paramWxApp = new StringBuilder();
    paramWxApp.append("ScannerApplication.ApiType : ");
    paramWxApp.append(ScannerApplication.i);
    com.intsig.q.e.b("ShareControl", paramWxApp.toString());
    if (ScannerApplication.i == 0) {
      localWXMiniProgramObject.miniprogramType = 2;
    } else if (ScannerApplication.i == 1) {
      localWXMiniProgramObject.miniprogramType = 0;
    }
    paramWxApp = new WXMediaMessage(localWXMiniProgramObject);
    paramString1 = new StringBuilder();
    paramString1.append(paramContext.getString(2131690797));
    paramString1.append(": ");
    paramString1.append(paramString3);
    paramWxApp.title = paramString1.toString();
    paramWxApp.thumbData = paramArrayOfByte;
    paramContext = new SendMessageToWX.Req();
    paramContext.transaction = "";
    paramContext.message = paramWxApp;
    paramContext.scene = 0;
    ((com.intsig.v.b)localObject).b().sendReq(paramContext);
    com.intsig.e.a.c("wechat_miniapp");
  }
  
  private void a(Context paramContext, Long paramLong, long paramLong1, String paramString1, String paramString2)
  {
    String str = paramString2;
    if (Build.VERSION.SDK_INT >= 19)
    {
      str = paramString2;
      if (!TextUtils.isEmpty(this.a))
      {
        str = paramString2;
        if (!this.a.equals(paramString2))
        {
          p.c(paramString2, this.a);
          p.a(paramString2);
          str = this.a;
          this.a = null;
        }
      }
    }
    paramString2 = ContentUris.withAppendedId(o.a, paramLong1);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("ocr_result", paramString1);
    localContentValues.put("ocr_border", str);
    int i1 = paramContext.getContentResolver().update(paramString2, localContentValues, null, null);
    paramString1 = new StringBuilder();
    paramString1.append("saveOcrResult() update ");
    paramString1.append(i1);
    com.intsig.q.e.b("ShareControl", paramString1.toString());
    av.c(paramContext, paramLong1, 3, true);
    com.intsig.camscanner.a.y.b(paramContext, paramLong.longValue(), false);
  }
  
  private void a(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd, ArrayList<cs> paramArrayList1)
  {
    new cu(this, paramContext, paramArrayList, paramString, paramAd, paramArrayList1).executeOnExecutor(l.a(), new Integer[0]);
  }
  
  private void a(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd, boolean paramBoolean)
  {
    new e(paramContext, new az(this, paramContext, paramArrayList, paramString, paramAd), paramContext.getString(2131690177), paramBoolean).a();
  }
  
  private static void a(ResolveInfo paramResolveInfo, ImageView paramImageView, PackageManager paramPackageManager)
  {
    try
    {
      paramImageView.setImageDrawable(paramResolveInfo.loadIcon(paramPackageManager));
      return;
    }
    catch (NullPointerException paramPackageManager)
    {
      com.intsig.q.e.b("ShareControl", "showAppIcon NullPointerException ", paramPackageManager);
    }
    try
    {
      paramImageView.setImageResource(paramResolveInfo.activityInfo.icon);
      return;
    }
    catch (Exception paramResolveInfo)
    {
      for (;;) {}
    }
    com.intsig.q.e.b("ShareControl", "showAppIcon Exception ", paramPackageManager);
  }
  
  private boolean a(Activity paramActivity, String paramString1, String paramString2, long paramLong, int paramInt, String paramString3, boolean paramBoolean, com.intsig.utils.q paramQ)
  {
    if ("com.intsig.camscanner.UploadFaxPrintActivity".equals(paramString2))
    {
      com.intsig.q.e.b("ShareControl", "onSpecialResolveInfoItemClick: upload print fax");
      paramString1 = new Intent("android.intent.action.SEND", null, paramActivity, UploadFaxPrintActivity.class);
      paramString1.putExtra("SEND_TYPE", 10);
      paramString1.putExtra("doc_id", paramLong);
      paramString1.putExtra("send_page_pos", paramInt);
      paramString1.putExtra("is_need_suffix", paramBoolean);
      try
      {
        paramActivity.startActivity(paramString1);
      }
      catch (ActivityNotFoundException paramActivity)
      {
        com.intsig.q.e.b("ShareControl", paramActivity);
      }
      com.intsig.q.f.a(14000);
      return true;
    }
    if (paramActivity.getString(2131691273).equals(paramString1))
    {
      if ((paramQ != null) && (!paramQ.c())) {
        new e(paramActivity, new bb(this, paramString3, paramQ, paramActivity), paramActivity.getString(2131689665)).a();
      } else {
        a(paramActivity, paramString3, this.j);
      }
      com.intsig.q.f.a(14002);
      return true;
    }
    if ("savetogallery".equals(paramString1))
    {
      if ((paramQ != null) && (!paramQ.c()))
      {
        new e(paramActivity, new bc(this, paramString3, paramQ, paramActivity), paramActivity.getString(2131689665)).a();
        return true;
      }
      a(paramActivity, paramString3);
      return true;
    }
    return false;
  }
  
  private boolean a(Activity paramActivity, String paramString1, String paramString2, String paramString3, boolean paramBoolean, com.intsig.utils.q paramQ)
  {
    boolean bool = "com.intsig.camscanner.UploadFaxPrintActivity".equals(paramString2);
    paramBoolean = true;
    if (bool)
    {
      com.intsig.q.e.b("ShareControl", "onSpecialResolveInfoItemClickForJPG: upload print fax");
      com.intsig.q.f.a(14000);
      Toast.makeText(paramActivity, paramActivity.getString(2131689933), 0).show();
      return true;
    }
    if (paramActivity.getString(2131691273).equals(paramString1))
    {
      if ((paramQ != null) && (!paramQ.c())) {
        new e(paramActivity, new bd(this, paramString3, paramQ, paramActivity), paramActivity.getString(2131689665)).a();
      } else {
        a(paramActivity, paramString3, this.j);
      }
      com.intsig.q.f.a(14002);
      return true;
    }
    if ("savetogallery".equals(paramString1))
    {
      if ((paramQ != null) && (!paramQ.c()))
      {
        new e(paramActivity, new be(this, paramString3, paramQ, paramActivity), paramActivity.getString(2131689665)).a();
        return true;
      }
      a(paramActivity, paramString3);
      return true;
    }
    if (paramActivity.getString(2131691272).equals(paramString1))
    {
      if (this.o != null)
      {
        this.o.a(paramString3);
        return true;
      }
    }
    else {
      paramBoolean = false;
    }
    return paramBoolean;
  }
  
  public static boolean a(Intent paramIntent, String paramString)
  {
    com.intsig.q.e.b("ShareControl", "filterSubjectForGoogleDrive");
    boolean bool3 = false;
    boolean bool2 = false;
    if (paramIntent == null) {
      return false;
    }
    Object localObject = paramIntent.getComponent();
    boolean bool1 = bool3;
    if (localObject != null)
    {
      bool1 = bool3;
      if (!TextUtils.isEmpty(paramString))
      {
        bool1 = bool3;
        if ("com.google.android.apps.docs".equals(((ComponentName)localObject).getPackageName()))
        {
          String str = c(paramIntent);
          bool1 = bool2;
          localObject = paramString;
          if (!TextUtils.isEmpty(str))
          {
            str = p.h(str);
            bool1 = bool2;
            localObject = paramString;
            if (!TextUtils.isEmpty(str))
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append(paramString);
              ((StringBuilder)localObject).append(".");
              ((StringBuilder)localObject).append(str);
              localObject = ((StringBuilder)localObject).toString();
              paramIntent.putExtra("android.intent.extra.SUBJECT", (String)localObject);
              bool1 = true;
            }
          }
          paramIntent = new StringBuilder();
          paramIntent.append("filterSubjectForGoogleDrive subject=");
          paramIntent.append((String)localObject);
          com.intsig.q.e.b("ShareControl", paramIntent.toString());
        }
      }
    }
    return bool1;
  }
  
  public static ResolveInfo[] a(Context paramContext)
  {
    ResolveInfo[] arrayOfResolveInfo = new ResolveInfo[3];
    Object localObject1 = new Intent("android.intent.action.SEND");
    ((Intent)localObject1).setType("image/jpeg");
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 65536);
    }
    catch (RuntimeException paramContext)
    {
      com.intsig.q.e.b("ShareControl", paramContext);
      paramContext = null;
    }
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (ResolveInfo)paramContext.next();
        Object localObject2 = ((ResolveInfo)localObject1).activityInfo;
        if (localObject2 == null)
        {
          com.intsig.q.e.b("ShareControl", "activityInfo == null");
        }
        else
        {
          String str = ((ActivityInfo)localObject2).packageName;
          localObject2 = ((ActivityInfo)localObject2).name;
          if (("com.tencent.mm".equals(str)) && ("com.tencent.mm.ui.tools.ShareImgUI".equals(localObject2))) {
            arrayOfResolveInfo[0] = localObject1;
          } else if (("com.whatsapp".equals(str)) && ("com.whatsapp.ContactPicker".equals(localObject2))) {
            arrayOfResolveInfo[1] = localObject1;
          } else if (("com.facebook.katana".equals(str)) && ("com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias".equals(localObject2))) {
            arrayOfResolveInfo[2] = localObject1;
          }
        }
      }
    }
    return arrayOfResolveInfo;
  }
  
  public static long b(Context paramContext, ArrayList<Long> paramArrayList)
  {
    if ((paramContext != null) && (paramArrayList != null) && (paramArrayList.size() > 0))
    {
      paramArrayList = paramArrayList.iterator();
      int i1 = 0;
      while (paramArrayList.hasNext())
      {
        long l1 = ((Long)paramArrayList.next()).longValue();
        Object localObject = ContentUris.withAppendedId(k.a, l1);
        localObject = paramContext.getContentResolver().query((Uri)localObject, new String[] { "pages" }, null, null, null);
        if (localObject != null)
        {
          int i2 = i1;
          if (((Cursor)localObject).moveToFirst()) {
            i2 = i1 + ((Cursor)localObject).getInt(0);
          }
          ((Cursor)localObject).close();
          i1 = i2;
        }
      }
      return 300 * i1;
    }
    com.intsig.q.e.b("ShareControl", "estimateDocPagesTextSize context or docIds is empty");
    return 0L;
  }
  
  private String b(String paramString)
  {
    String str2 = paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    paramString = new StringBuilder();
    paramString.append(str2);
    paramString.append(".ocr");
    String str1 = av.e(paramString.toString());
    paramString = str1;
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramString = str1;
      if (!TextUtils.isEmpty(x.a))
      {
        paramString = str1;
        if (str1.contains(x.a))
        {
          File localFile = new File(Environment.getExternalStorageDirectory(), "CamScanner/.temp/");
          paramString = str1;
          if (t.g(localFile.getAbsolutePath()))
          {
            this.a = str1;
            paramString = new StringBuilder();
            paramString.append(str2);
            paramString.append(".ocr");
            paramString = new File(localFile, paramString.toString()).getAbsolutePath();
          }
        }
      }
    }
    return paramString;
  }
  
  private void b()
  {
    if (this.g != null) {
      try
      {
        this.g.a(this.f);
        return;
      }
      catch (Exception localException)
      {
        com.intsig.q.e.b("ShareControl", localException);
      }
    }
  }
  
  private static void b(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, long paramLong)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramContext.getString(2131689991));
    ((StringBuilder)localObject1).append(" (");
    ((StringBuilder)localObject1).append("%.2fMB)");
    localObject1 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Double.valueOf(paramLong * 9.5367431640625E-7D) });
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramContext.getString(2131689990));
    ((StringBuilder)localObject2).append(" (");
    ((StringBuilder)localObject2).append("%.2fMB)");
    localObject2 = ((StringBuilder)localObject2).toString();
    float f1 = (float)paramLong;
    localObject2 = String.format((String)localObject2, new Object[] { Double.valueOf(0.7F * f1 * 9.5367431640625E-7D) });
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append(paramContext.getString(2131689992));
    ((StringBuilder)localObject3).append(" (");
    ((StringBuilder)localObject3).append("%.2fMB)");
    localObject3 = String.format(((StringBuilder)localObject3).toString(), new Object[] { Double.valueOf(0.5F * f1 * 9.5367431640625E-7D) });
    try
    {
      new com.intsig.d.c(paramContext).d(2131689989).a(new CharSequence[] { localObject1, localObject2, localObject3 }, paramOnClickListener).a().show();
      return;
    }
    catch (RuntimeException paramContext)
    {
      com.intsig.q.e.b("ShareControl", paramContext);
    }
  }
  
  private void b(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd)
  {
    this.k = 5;
    String str1 = av.b(paramContext, paramArrayList);
    paramArrayList = bo.a(paramContext, paramArrayList);
    if (paramArrayList != null)
    {
      if (TextUtils.isEmpty(str1))
      {
        new com.intsig.tsapp.bp(paramContext, paramArrayList, paramString, paramAd).executeOnExecutor(l.a(), new ArrayList[0]);
        paramContext = str1;
      }
      else
      {
        str1 = String.format(paramContext.getString(2131689667), new Object[] { str1 });
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", str1);
        if (paramArrayList.size() == 1)
        {
          localIntent.putExtra("android.intent.extra.SUBJECT", av.f(paramContext, (String)paramArrayList.get(0)));
        }
        else if (paramArrayList.size() > 1)
        {
          String str2 = av.f(paramContext, (String)paramArrayList.get(0));
          String str3 = Locale.getDefault().getLanguage().toLowerCase();
          int i2 = paramArrayList.size();
          int i1 = i2;
          if (!"zh".equals(str3)) {
            i1 = i2 - 1;
          }
          localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131690616, new Object[] { str2, Integer.valueOf(i1) }));
        }
        else
        {
          localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131689668));
        }
        localIntent.setType("text/plain");
        if (!TextUtils.isEmpty(paramString)) {
          com.intsig.camscanner.a.cb.a(localIntent, paramString, false);
        }
        paramArrayList = paramAd;
        if (paramAd == null) {
          paramArrayList = this.h;
        }
        aa.a().a(paramContext, localIntent, paramArrayList, this.n, this.k, null);
        paramContext = str1;
      }
      paramArrayList = new StringBuilder();
      paramArrayList.append("go2ShareDocsLink docPdfLink=");
      paramArrayList.append(paramContext);
      com.intsig.q.e.b("ShareControl", paramArrayList.toString());
      return;
    }
    com.intsig.q.e.b("ShareControl", "go2ShareDocsLink docSyncIds = null");
  }
  
  private void b(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd, ArrayList<cs> paramArrayList1)
  {
    com.intsig.d.c localC = new com.intsig.d.c(paramContext);
    localC.d(2131689674).b(paramContext.getString(2131689703, new Object[] { Integer.valueOf(paramArrayList1.size()) })).b(2131690932, new bj(this, paramContext, paramArrayList, paramString, paramAd)).c(2131690934, new bi(this, paramContext, paramArrayList, paramString, paramAd, paramArrayList1));
    try
    {
      localC.a().show();
      return;
    }
    catch (Exception paramContext)
    {
      com.intsig.q.e.a("ShareControl", paramContext);
    }
  }
  
  private void b(com.intsig.datastruct.q paramQ)
  {
    new h(this.i, paramQ.c, new cq(this, paramQ)).executeOnExecutor(l.a(), new ArrayList[0]);
  }
  
  public static boolean b(Activity paramActivity, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("gallery dir: ");
    ((StringBuilder)localObject1).append(t.h());
    com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
    if (com.intsig.camscanner.a.f.d(paramActivity))
    {
      if (paramArrayList1 != null) {
        Collections.reverse(paramArrayList1);
      }
      if (paramArrayList2 != null) {
        Collections.reverse(paramArrayList2);
      }
    }
    File localFile1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    if ((localFile1 != null) && (!localFile1.exists()))
    {
      bool = localFile1.mkdir();
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("make gallery dir: ");
      ((StringBuilder)localObject1).append(localFile1);
      ((StringBuilder)localObject1).append(" = ");
      ((StringBuilder)localObject1).append(bool);
      com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
    }
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    boolean bool = false;
    while (i1 < paramArrayList1.size())
    {
      File localFile2 = new File((String)paramArrayList1.get(i1));
      if (paramArrayList2 == null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localFile1.getAbsolutePath());
        ((StringBuilder)localObject1).append(File.separator);
        ((StringBuilder)localObject1).append(localFile2.getName());
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localFile1.getAbsolutePath());
        ((StringBuilder)localObject1).append(File.separator);
        ((StringBuilder)localObject1).append((String)paramArrayList2.get(i1));
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      Object localObject2 = localObject1;
      if (new File((String)localObject1).exists()) {
        localObject2 = com.intsig.webstorage.f.b.a((String)localObject1);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("no dup file path: ");
      ((StringBuilder)localObject1).append((String)localObject2);
      com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
      try
      {
        p.a(localFile2, new File((String)localObject2));
        localArrayList.add(localObject2);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("save to gallery successful ");
        ((StringBuilder)localObject1).append((String)localObject2);
        com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
        bool = true;
      }
      catch (Exception localException)
      {
        com.intsig.q.e.b("ShareControl", localException);
      }
      catch (IOException localIOException)
      {
        com.intsig.q.e.b("ShareControl", localIOException);
      }
      i1 += 1;
    }
    if (localArrayList.size() > 0)
    {
      paramArrayList1 = (String[])localArrayList.toArray(new String[localArrayList.size()]);
      ImageRegisterService.a(paramActivity, paramArrayList1);
      paramActivity = new StringBuilder();
      paramActivity.append("notify MediaScanner，fileSize = ");
      paramActivity.append(paramArrayList1.length);
      com.intsig.q.e.b("ShareControl", paramActivity.toString());
    }
    paramActivity = new StringBuilder();
    paramActivity.append("res: ");
    paramActivity.append(bool);
    com.intsig.q.e.b("ShareControl", paramActivity.toString());
    return bool;
  }
  
  private boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i1 = 0;
      while (i1 < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i1)).packageName.equals("com.tencent.mm"))
        {
          com.intsig.q.e.b("ShareControl", "user has install weChat app");
          return true;
        }
        i1 += 1;
      }
    }
    return false;
  }
  
  public static String c(Intent paramIntent)
  {
    Serializable localSerializable = paramIntent.getSerializableExtra("android.intent.extra.STREAM");
    StringBuilder localStringBuilder = null;
    if (localSerializable == null)
    {
      paramIntent = (Uri)paramIntent.getParcelableExtra("android.intent.extra.STREAM");
      if (paramIntent != null)
      {
        paramIntent = paramIntent.getPath();
      }
      else
      {
        com.intsig.q.e.b("ShareControl", "uri == null");
        paramIntent = localStringBuilder;
      }
    }
    else if ((localSerializable instanceof Uri))
    {
      paramIntent = ((Uri)localSerializable).getPath();
    }
    else
    {
      paramIntent = localStringBuilder;
      if ((localSerializable instanceof ArrayList)) {
        paramIntent = ((Uri)((ArrayList)localSerializable).get(0)).getPath();
      }
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Path=");
    localStringBuilder.append(paramIntent);
    com.intsig.q.e.b("ShareControl", localStringBuilder.toString());
    return paramIntent;
  }
  
  private static ArrayList<ResolveInfo> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    ResolveInfo localResolveInfo = new ResolveInfo();
    ActivityInfo localActivityInfo = new ActivityInfo();
    localActivityInfo.packageName = paramContext.getString(2131691272);
    localActivityInfo.name = "null";
    if (bu.a()) {
      localActivityInfo.icon = 2131231290;
    } else {
      localActivityInfo.icon = 2131231291;
    }
    localActivityInfo.labelRes = 2131691165;
    localResolveInfo.activityInfo = localActivityInfo;
    localArrayList.add(localResolveInfo);
    return localArrayList;
  }
  
  private void c()
  {
    this.g = new com.intsig.snslogin.a.a(this.i, new ay(this), new bk(this));
  }
  
  private void c(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd)
  {
    Intent localIntent = new Intent(paramContext, SecureLinkActivity.class);
    localIntent.putExtra("shareDocId_key", bo.a(paramArrayList));
    localIntent.putExtra("email_key", paramString);
    paramContext.startActivity(localIntent);
    paramAd.a(null);
  }
  
  private boolean c(Context paramContext, ArrayList<Long> paramArrayList)
  {
    return (b(paramContext)) && (paramArrayList != null) && (paramArrayList.size() == 1);
  }
  
  private static ResolveInfo d(Context paramContext)
  {
    ResolveInfo localResolveInfo = new ResolveInfo();
    ActivityInfo localActivityInfo = new ActivityInfo();
    localActivityInfo.packageName = paramContext.getString(2131691273);
    localActivityInfo.name = "null";
    localActivityInfo.icon = 2131230968;
    localActivityInfo.labelRes = 2131690437;
    localResolveInfo.activityInfo = localActivityInfo;
    return localResolveInfo;
  }
  
  private ArrayList<ResolveInfo> d()
  {
    return a(false);
  }
  
  private ArrayList<cs> d(Context paramContext, ArrayList<Long> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.addAll(a(paramContext, (Long)paramArrayList.next()));
    }
    return localArrayList;
  }
  
  public static void d(Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject = paramIntent.getComponent();
    if (localObject != null)
    {
      localObject = ((ComponentName)localObject).getPackageName();
      if ((TextUtils.equals("org.me.mobiexpensifyg", (CharSequence)localObject)) || (TextUtils.equals("com.tencent.androidqqmail", (CharSequence)localObject))) {
        paramIntent.setFlags(268435456);
      }
    }
  }
  
  private static ResolveInfo e(Context paramContext)
  {
    paramContext = new ResolveInfo();
    ActivityInfo localActivityInfo = new ActivityInfo();
    localActivityInfo.packageName = "savetogallery";
    localActivityInfo.name = "null";
    localActivityInfo.icon = 2131230967;
    localActivityInfo.labelRes = 2131690436;
    paramContext.activityInfo = localActivityInfo;
    return paramContext;
  }
  
  private ArrayList<String> e(Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
    if (localObject == null)
    {
      paramIntent = (Uri)paramIntent.getParcelableExtra("android.intent.extra.STREAM");
      paramIntent = com.intsig.util.f.a().a(this.i, paramIntent);
      if (!TextUtils.isEmpty(paramIntent))
      {
        localArrayList.add(paramIntent);
        return localArrayList;
      }
    }
    else
    {
      paramIntent = ((ArrayList)localObject).iterator();
      while (paramIntent.hasNext())
      {
        localObject = (Uri)paramIntent.next();
        localObject = com.intsig.util.f.a().a(this.i, (Uri)localObject);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("path:");
        localStringBuilder.append((String)localObject);
        com.intsig.q.e.b("ShareControl", localStringBuilder.toString());
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          localArrayList.add(localObject);
        }
      }
    }
    return localArrayList;
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.g != null) {
      this.g.a(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void a(Activity paramActivity, long paramLong, int[] paramArrayOfInt, String paramString, cx paramCx, PDF_Util.OnPdfCreateListener paramOnPdfCreateListener)
  {
    this.i = paramActivity;
    g.a(paramActivity);
    this.n = false;
    String str;
    if (paramArrayOfInt.length > 1) {
      str = bg.a(paramActivity, paramString, 0, 3);
    } else {
      str = bg.a(paramActivity, paramString, paramArrayOfInt[0] + 1, 2);
    }
    Object localObject1 = new File(t.d());
    if (!((File)localObject1).exists()) {
      ((File)localObject1).mkdirs();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(str);
    ((StringBuilder)localObject2).append(".pdf");
    localObject2 = new File((File)localObject1, ((StringBuilder)localObject2).toString()).getAbsolutePath();
    localObject1 = new StringBuilder();
    int i2 = paramArrayOfInt.length;
    int i1 = 0;
    while (i1 < i2)
    {
      int i3 = paramArrayOfInt[i1];
      if (((StringBuilder)localObject1).length() > 0)
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(", ");
        ((StringBuilder)localObject3).append(i3 + 1);
        ((StringBuilder)localObject1).append(((StringBuilder)localObject3).toString());
      }
      else
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("");
        ((StringBuilder)localObject3).append(i3 + 1);
        ((StringBuilder)localObject1).append(((StringBuilder)localObject3).toString());
      }
      i1 += 1;
    }
    if (((StringBuilder)localObject1).length() > 0)
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("( ");
      ((StringBuilder)localObject3).append(((StringBuilder)localObject1).toString());
      ((StringBuilder)localObject3).append(" )");
      localObject1 = ((StringBuilder)localObject3).toString();
    }
    else
    {
      localObject1 = null;
    }
    long l2 = bo.a(this.i, paramLong, (String)localObject1);
    long l1;
    if (l2 > 0L) {
      l1 = l2 + 8192L;
    } else {
      l1 = 0L;
    }
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append(paramActivity.getString(2131690011));
    ((StringBuilder)localObject3).append(" (");
    ((StringBuilder)localObject3).append("%.2fMB)");
    localObject3 = String.format(((StringBuilder)localObject3).toString(), new Object[] { Double.valueOf(l1 / 1024.0D / 1024.0D) });
    Object localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append(paramActivity.getString(2131690010));
    ((StringBuilder)localObject4).append(" (");
    ((StringBuilder)localObject4).append("%.2fMB)");
    localObject4 = String.format(((StringBuilder)localObject4).toString(), new Object[] { Double.valueOf(l2 / 1024.0D / 1024.0D) });
    Object localObject5 = new StringBuilder();
    ((StringBuilder)localObject5).append("pdfSize=");
    ((StringBuilder)localObject5).append(l1);
    ((StringBuilder)localObject5).append(" imageSize=");
    ((StringBuilder)localObject5).append(l2);
    ((StringBuilder)localObject5).append(" filterString=");
    ((StringBuilder)localObject5).append((String)localObject1);
    com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject5).toString());
    localObject5 = new com.intsig.d.c(paramActivity);
    ((com.intsig.d.c)localObject5).d(2131690929);
    paramActivity = new bs(this, paramActivity, (String)localObject2, str, paramLong, paramArrayOfInt, paramOnPdfCreateListener, paramCx, paramString, (String)localObject1, l1, l2);
    ((com.intsig.d.c)localObject5).a(new CharSequence[] { localObject3, localObject4 }, paramActivity);
    try
    {
      ((com.intsig.d.c)localObject5).a().show();
      return;
    }
    catch (RuntimeException paramActivity)
    {
      com.intsig.q.e.b("ShareControl", paramActivity);
    }
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, cx paramCx)
  {
    g.a(paramActivity);
    Intent localIntent = new Intent();
    localIntent.setType("image/jpeg");
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.STREAM", p.f(paramString2));
    localIntent.putExtra("IS_SHARE_TO_COMMUNITY", true);
    paramString2 = new bl(this, paramString1, paramActivity, paramCx, paramString2, null);
    if (r.bJ(paramActivity)) {}
    for (paramString1 = c(paramActivity);; paramString1 = null) {
      break;
    }
    aa.a().a(paramActivity, localIntent, paramString2, false, 1, paramString1);
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean)
  {
    a(paramActivity, paramString1, paramString2, paramString3, paramString4, paramLong, paramInt, paramBoolean, false, true);
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    a(paramActivity, paramString1, paramString2, paramString3, paramString4, paramLong, paramInt, paramBoolean1, paramBoolean2, false, null, paramBoolean3);
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, cx paramCx, boolean paramBoolean4)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("showImagePageShareDialog: docid = ");
    ((StringBuilder)localObject1).append(paramLong);
    ((StringBuilder)localObject1).append(", imgPath = ");
    ((StringBuilder)localObject1).append(paramString1);
    com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
    this.i = paramActivity;
    this.n = false;
    this.m = paramBoolean1;
    this.l = true;
    g.a(paramActivity);
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString4);
    ((StringBuilder)localObject1).append("_");
    int i1 = paramInt + 1;
    ((StringBuilder)localObject1).append(i1);
    Object localObject2 = ((StringBuilder)localObject1).toString();
    if (TextUtils.isEmpty(paramString3)) {
      localObject1 = bg.a(paramActivity, paramString4, i1, 2);
    }
    for (;;)
    {
      break;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramString4);
      ((StringBuilder)localObject1).append(" - ");
      ((StringBuilder)localObject1).append(paramString3);
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject2);
      ((StringBuilder)localObject3).append("_");
      ((StringBuilder)localObject3).append(paramString3);
      localObject2 = ((StringBuilder)localObject3).toString();
    }
    Object localObject3 = t.d();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append(".pdf");
    localObject3 = new File((String)localObject3, localStringBuilder.toString()).getAbsolutePath();
    long l2 = new File(paramString1).length();
    long l1;
    if (l2 > 0L) {
      l1 = l2 + 8192L;
    } else {
      l1 = 0L;
    }
    localObject2 = new com.intsig.d.c(paramActivity);
    ((com.intsig.d.c)localObject2).d(2131690929);
    paramString1 = new cb(this, paramActivity, paramString1, paramString2, paramString4, paramInt, paramString3, (String)localObject1, paramCx, paramLong, paramBoolean4, paramBoolean3, (String)localObject3, l1, l2);
    paramString2 = new StringBuilder();
    paramString2.append(paramActivity.getString(2131690011));
    paramString2.append(" (");
    paramString2.append("%.2fMB)");
    paramString2 = String.format(paramString2.toString(), new Object[] { Double.valueOf(l1 / 1024.0D / 1024.0D) });
    paramString3 = new StringBuilder();
    paramString3.append(paramActivity.getString(2131690010));
    paramString3.append(" (");
    paramString3.append("%.2fMB)");
    paramActivity = String.format(paramString3.toString(), new Object[] { Double.valueOf(l2 / 1024.0D / 1024.0D) });
    if (paramBoolean2)
    {
      paramString3 = LayoutInflater.from(this.i).inflate(2131427680, null);
      ((com.intsig.d.c)localObject2).a(paramString3);
      paramString4 = this.i.getPackageManager();
      paramCx = a(this.i);
      if ("zh-cn".equals(com.intsig.h.f.b()))
      {
        if (paramCx[0] != null)
        {
          localObject1 = (ImageView)paramString3.findViewById(2131296865);
          a(paramCx[0], (ImageView)localObject1, paramString4);
          ((ImageView)localObject1).setVisibility(0);
        }
      }
      else
      {
        if (paramCx[1] != null)
        {
          localObject1 = (ImageView)paramString3.findViewById(2131296867);
          a(paramCx[1], (ImageView)localObject1, paramString4);
          ((ImageView)localObject1).setVisibility(0);
        }
        if (paramCx[2] != null)
        {
          localObject1 = (ImageView)paramString3.findViewById(2131296818);
          a(paramCx[2], (ImageView)localObject1, paramString4);
          ((ImageView)localObject1).setVisibility(0);
        }
      }
      paramString4 = ((com.intsig.d.c)localObject2).a();
      paramCx = (TextView)paramString3.findViewById(2131297653);
      paramCx.setText(paramString2);
      paramCx.setOnClickListener(new cj(this, paramString4, paramString1));
      ((TextView)paramString3.findViewById(2131297626)).setText(paramActivity);
      paramString3.findViewById(2131296976).setOnClickListener(new ck(this, paramString4, paramString1));
      try
      {
        paramString4.show();
        return;
      }
      catch (RuntimeException paramActivity)
      {
        com.intsig.q.e.b("ShareControl", paramActivity);
        return;
      }
    }
    ((com.intsig.d.c)localObject2).a(new CharSequence[] { paramString2, paramActivity }, paramString1);
    try
    {
      ((com.intsig.d.c)localObject2).a().show();
      return;
    }
    catch (RuntimeException paramActivity)
    {
      com.intsig.q.e.b("ShareControl", paramActivity);
    }
  }
  
  public void a(Context paramContext, ArrayList<Long> paramArrayList, String paramString, ad paramAd)
  {
    if (paramContext == null)
    {
      paramArrayList = new StringBuilder();
      paramArrayList.append("go2ShareDocsTxt context=");
      paramArrayList.append(paramContext);
      com.intsig.q.e.b("ShareControl", paramArrayList.toString());
      return;
    }
    new ArrayList();
    ad localAd = paramAd;
    if (paramAd == null) {
      localAd = this.h;
    }
    this.i = paramContext;
    paramAd = d(paramContext, paramArrayList);
    if (paramAd.size() > 0)
    {
      b(paramContext, paramArrayList, paramString, localAd, paramAd);
      return;
    }
    a(paramContext, paramArrayList, paramString, localAd, true);
  }
  
  public void a(cw paramCw)
  {
    this.o = paramCw;
  }
  
  public void a(com.intsig.datastruct.q paramQ)
  {
    if ((paramQ != null) && (paramQ.a != null) && (paramQ.c != null) && (paramQ.c.size() != 0))
    {
      if ((paramQ.e != null) && (paramQ.d != null))
      {
        g.a(paramQ.a);
        if (paramQ.h == null) {}
        for (Object localObject3 = this.h;; localObject3 = paramQ.h) {
          break;
        }
        this.i = paramQ.a;
        this.m = paramQ.b;
        this.n = paramQ.k;
        int i1 = paramQ.c.size();
        int i2 = 1;
        if (i1 == 1) {
          bool = true;
        } else {
          bool = false;
        }
        this.l = bool;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("showDocShareDialog docIds ");
        ((StringBuilder)localObject1).append(paramQ.c);
        ((StringBuilder)localObject1).append(", isMailToMe ");
        ((StringBuilder)localObject1).append(paramQ.k);
        com.intsig.q.e.b("ShareControl", ((StringBuilder)localObject1).toString());
        String str1 = paramQ.a.getString(2131689995);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramQ.a.getString(2131690012));
        ((StringBuilder)localObject1).append(" (");
        ((StringBuilder)localObject1).append("%.2fKB)");
        Object localObject5 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Float.valueOf(paramQ.c.size() * 100.0F / 1024.0F) });
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramQ.a.getString(2131690009));
        ((StringBuilder)localObject1).append(" (");
        ((StringBuilder)localObject1).append("%.2fKB)");
        String str2 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Float.valueOf(100.0F * paramQ.c.size() / 1024.0F) });
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramQ.a.getString(2131690011));
        ((StringBuilder)localObject1).append(" (");
        ((StringBuilder)localObject1).append("%.2fMB)");
        Object localObject2 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Double.valueOf(paramQ.i / 1024.0D / 1024.0D) });
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramQ.a.getString(2131690010));
        ((StringBuilder)localObject1).append(" (");
        ((StringBuilder)localObject1).append("%.2fMB)");
        Object localObject4 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Double.valueOf(paramQ.j / 1024.0D / 1024.0D) });
        com.intsig.util.cb localCb = com.intsig.util.cb.a();
        int i9 = localCb.a(this.i);
        boolean bool = av.a(paramQ.a, paramQ.c);
        int i5 = 3;
        if (bool)
        {
          if (localCb.b(i9))
          {
            if ((c(this.i, paramQ.c)) && (!paramQ.k))
            {
              if (paramQ.m)
              {
                localObject1 = new CharSequence[2];
                localObject1[0] = localObject2;
                localObject1[1] = localObject4;
              }
              else
              {
                localObject1 = new CharSequence[4];
                localObject1[0] = str1;
                localObject1[1] = localObject5;
                localObject1[2] = localObject2;
                localObject1[3] = localObject4;
              }
            }
            else if (paramQ.m)
            {
              localObject1 = new CharSequence[2];
              localObject1[0] = localObject2;
              localObject1[1] = localObject4;
            }
            else
            {
              localObject1 = new CharSequence[3];
              localObject1[0] = localObject5;
              localObject1[1] = localObject2;
              localObject1[2] = localObject4;
            }
            if ((c(this.i, paramQ.c)) && (!paramQ.k))
            {
              if (!paramQ.m)
              {
                i7 = 0;
                i1 = 1;
                i4 = i1;
                i2 = 2;
                i3 = 3;
                i5 = -1;
                localObject4 = null;
                i6 = -1;
                localObject2 = localObject1;
                localObject1 = localObject4;
                break label1478;
              }
            }
            else {
              if (paramQ.m) {
                break label1242;
              }
            }
            i4 = 0;
            i3 = 2;
            localObject2 = localObject1;
            break label1303;
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramQ.a.getString(2131690017));
            ((StringBuilder)localObject1).append(" (");
            ((StringBuilder)localObject1).append("%.2fKB)");
            String str3 = String.format(((StringBuilder)localObject1).toString(), new Object[] { Float.valueOf((float)b(paramQ.a, paramQ.c) / 1024.0F) });
            if ((c(this.i, paramQ.c)) && (!paramQ.k))
            {
              if (paramQ.m)
              {
                localObject1 = new CharSequence[3];
                localObject1[0] = str3;
                localObject1[1] = localObject2;
                localObject1[2] = localObject4;
              }
              else
              {
                localObject1 = new CharSequence[6];
                localObject1[0] = str1;
                localObject1[1] = str2;
                localObject1[2] = str3;
                localObject1[3] = localObject5;
                localObject1[4] = localObject2;
                localObject1[5] = localObject4;
              }
            }
            else
            {
              if (!paramQ.m) {
                break label998;
              }
              localObject1 = new CharSequence[3];
              localObject1[0] = str3;
              localObject1[1] = localObject2;
              localObject1[2] = localObject4;
            }
            break label1034;
            label998:
            localObject1 = new CharSequence[5];
            localObject1[0] = str2;
            localObject1[1] = str3;
            localObject1[2] = localObject5;
            localObject1[3] = localObject2;
            localObject1[4] = localObject4;
            label1034:
            if ((c(this.i, paramQ.c)) && (!paramQ.k))
            {
              if (!paramQ.m)
              {
                i3 = 5;
                i4 = 3;
                i2 = 1;
                i6 = 0;
                i5 = 4;
                i1 = 2;
                break label1124;
              }
            }
            else {
              if (!paramQ.m) {
                break label1111;
              }
            }
            i2 = -1;
            i1 = 0;
            i5 = 1;
            i4 = -1;
            i3 = 2;
            for (;;)
            {
              i6 = -1;
              break;
              label1111:
              i3 = 4;
              i2 = 0;
              i1 = 1;
              i4 = 2;
            }
            label1124:
            if (localCb.c(i9))
            {
              localObject2 = new ArrayList();
              if (!paramQ.m) {
                ((ArrayList)localObject2).add(Integer.valueOf(i2));
              }
              ((ArrayList)localObject2).add(Integer.valueOf(i1));
            }
            else
            {
              localObject2 = null;
            }
            i7 = i2;
            i2 = i5;
            int i8 = i6;
            i6 = i1;
            localObject4 = localObject1;
            i1 = 1;
            i5 = i7;
            localObject1 = localObject2;
            localObject2 = localObject4;
            i7 = i8;
            break label1478;
          }
        }
        else
        {
          if (!localCb.b(i9)) {
            break label1272;
          }
          localObject1 = new CharSequence[2];
          localObject1[0] = localObject2;
          localObject1[1] = localObject4;
        }
        label1242:
        localObject2 = localObject1;
        int i3 = 1;
        int i4 = -1;
        i5 = -1;
        localObject1 = null;
        int i6 = -1;
        int i7 = -1;
        i2 = 0;
        i1 = i3;
        break label1478;
        label1272:
        if (paramQ.b)
        {
          localObject2 = new CharSequence[] { localObject2, localObject4 };
          i2 = 0;
          i3 = 1;
          i4 = -1;
          label1303:
          i1 = 1;
          i5 = -1;
          localObject1 = null;
          i6 = -1;
          i7 = -1;
        }
        else
        {
          if (localCb.c(i9))
          {
            localObject1 = new ArrayList();
            ((ArrayList)localObject1).add(Integer.valueOf(0));
          }
          else
          {
            localObject1 = null;
          }
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append(paramQ.a.getString(2131689937));
          ((StringBuilder)localObject5).append(" (");
          ((StringBuilder)localObject5).append("%.2fKB)");
          localObject5 = ((StringBuilder)localObject5).toString();
          i1 = 1;
          localObject5 = String.format((String)localObject5, new Object[] { Float.valueOf((float)b(paramQ.a, paramQ.c) / 1024.0F) });
          i3 = 2;
          localObject2 = new CharSequence[] { localObject5, localObject2, localObject4 };
          i6 = 0;
          i4 = -1;
          i5 = -1;
          i7 = -1;
          i2 = 1;
        }
        label1478:
        if ((localObject2 != null) && (localObject2.length > i1))
        {
          localObject3 = new cl(this, paramQ, i2, i3, i4, (ad)localObject3, i5, localCb, i9, i6, i7);
          localObject4 = new com.intsig.d.c(paramQ.a);
          if (this.n) {
            ((com.intsig.d.c)localObject4).d(2131689915);
          } else {
            ((com.intsig.d.c)localObject4).d(2131690004);
          }
          if ((localObject1 != null) && (!((ArrayList)localObject1).isEmpty())) {
            ((com.intsig.d.c)localObject4).a((CharSequence[])localObject2, new cr(this, paramQ.a, (bv)localObject3), (ArrayList)localObject1, 2131231488, i7, 2131231496);
          } else {
            ((com.intsig.d.c)localObject4).a((CharSequence[])localObject2, new cr(this, paramQ.a, (bv)localObject3), null, -1, i7, 2131231496);
          }
          try
          {
            ((com.intsig.d.c)localObject4).a().show();
            return;
          }
          catch (RuntimeException paramQ)
          {
            com.intsig.q.e.b("ShareControl", paramQ);
          }
        }
        return;
      }
      com.intsig.q.e.b("ShareControl", "showDocShareDialog with empyt share intent");
      return;
    }
    com.intsig.q.e.b("ShareControl", "showDocShareDialog with empyt doc item");
  }
  
  public boolean a(Intent paramIntent)
  {
    return false;
  }
  
  public void b(Intent paramIntent)
  {
    this.f = e(paramIntent);
    if ((this.f != null) && (this.f.size() > 0))
    {
      c();
      b();
      return;
    }
    com.intsig.q.e.b("ShareControl", "paths is 0");
  }
}
