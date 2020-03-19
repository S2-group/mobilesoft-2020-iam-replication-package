package com.cyberlink.beautycircle.controller.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.beautycircle.BaseActivity;
import com.cyberlink.beautycircle.BcLib;
import com.cyberlink.beautycircle.Intents;
import com.cyberlink.beautycircle.R.color;
import com.cyberlink.beautycircle.R.id;
import com.cyberlink.beautycircle.R.layout;
import com.cyberlink.beautycircle.R.string;
import com.cyberlink.beautycircle.c;
import com.cyberlink.beautycircle.model.network.NetworkFeedback;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.FeedbackConfig;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.FeedbackResult;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.a;
import com.cyberlink.beautycircle.model.network.NetworkFile;
import com.cyberlink.beautycircle.model.network.NetworkFile.a;
import com.cyberlink.beautycircle.model.network.f;
import com.cyberlink.beautycircle.utility.AccountManager;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.GetUploadUrlResult;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.Header;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.ReportIssueResponse;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkManager;
import com.cyberlink.beautycircle.utility.doserver.Logger;
import com.cyberlink.beautycircle.utility.g;
import com.google.common.util.concurrent.m;
import com.google.common.util.concurrent.n;
import com.google.common.util.concurrent.r;
import com.google.common.util.concurrent.s;
import com.google.common.util.concurrent.z;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.ImageUtils.CompressSetting;
import com.perfectcorp.utility.a;
import com.pf.common.android.DeviceUtils;
import com.pf.common.android.PackageUtils;
import com.pf.common.android.i;
import com.pf.common.utility.Log;
import com.pf.common.utility.NetTask.b;
import com.pf.common.utility.NetTask.h;
import com.pf.common.utility.PromisedTask;
import com.pf.common.utility.PromisedTask.b;
import com.pf.common.utility.al;
import com.pf.common.utility.ar;
import com.pf.common.utility.k;
import com.pf.common.utility.y;
import io.reactivex.b.e;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import w.TintableImageView;
import w.dialogs.AlertDialog.a;
import w.dialogs.AlertDialog.b;

public class PreviewFeedbackActivity
  extends BaseActivity
{
  private static int[] O = { 2, 4 };
  private NetworkFeedback.a A;
  private ArrayList<Uri> B;
  private TextView C;
  private TextView D;
  private TextView E;
  private TextView F;
  private TextView G;
  private TextView H;
  private boolean I;
  private int J;
  private int K;
  private ArrayList<com.cyberlink.beautycircle.view.widgetpool.common.b> L = new ArrayList();
  private View.OnClickListener M = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      PreviewFeedbackActivity.a(PreviewFeedbackActivity.this);
    }
  };
  private View.OnClickListener N = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      PreviewFeedbackActivity.this.onRightBtnClick(paramAnonymousView);
    }
  };
  private NetworkFeedback.FeedbackConfig z;
  
  public PreviewFeedbackActivity() {}
  
  public static String A()
  {
    return c.a().getString("OsUpgradeHistory", "");
  }
  
  public static File B()
  {
    try
    {
      Object localObject1 = com.perfectcorp.billing.d.a();
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        Log.c(new Object[] { "[getIapLog] Can't get logger folder path" });
        return null;
      }
      localObject1 = new File((String)localObject1);
      if (!((File)localObject1).isDirectory())
      {
        Log.c(new Object[] { "[getIapLog] logger folder path is not a directory" });
        return null;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return null;
    }
    Object localObject2 = localException.listFiles();
    if ((localObject2 == null) || (localObject2.length == 0))
    {
      Log.c(new Object[] { "[getIapLog] No file in folder" });
      return null;
    }
    a.a(com.pf.common.b.c(), new ArrayList(Arrays.asList((Object[])localObject2)));
    localObject2 = new File(com.perfectcorp.utility.d.b(com.pf.common.b.c()));
    File localFile = new File(((File)localObject2).getParent(), "IapLog.zip");
    localFile.delete();
    if (((File)localObject2).renameTo(localFile))
    {
      Log.c(new Object[] { "[getIapLog] pack succeed, file=", localFile });
      return localFile;
    }
    Log.c(new Object[] { "[getIapLog] rename ", localObject2, " to ", localFile, " failed." });
    return null;
  }
  
  private void C()
  {
    Object localObject = findViewById(R.id.include_topbar_panel);
    if (localObject != null) {
      ((View)localObject).setBackgroundResource(R.color.bc_color_black);
    }
    localObject = (TextView)findViewById(R.id.edit_feedback_title);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(al.c(R.color.bc_color_white));
    }
    localObject = (TintableImageView)findViewById(R.id.edit_feedback_back);
    if (localObject != null) {
      ((TintableImageView)localObject).setColorFilter(ContextCompat.getColorStateList(this, R.color.bc_topbar_btn_icon_white));
    }
    localObject = (TintableImageView)findViewById(R.id.edit_feedback_next);
    if (localObject != null) {
      ((TintableImageView)localObject).setColorFilter(ContextCompat.getColorStateList(this, R.color.bc_topbar_btn_icon_white));
    }
  }
  
  private com.cyberlink.beautycircle.view.widgetpool.common.b D()
  {
    com.cyberlink.beautycircle.view.widgetpool.common.b localB = new com.cyberlink.beautycircle.view.widgetpool.common.b(this, true);
    this.L.add(localB);
    ViewGroup localViewGroup = (ViewGroup)findViewById(R.id.bc_feedback_image_panel);
    localViewGroup.addView(localB.a(LayoutInflater.from(this), localViewGroup, null));
    return localB;
  }
  
  private static String E()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)com.pf.common.b.c().getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return a(localMemoryInfo.totalMem) + " MB";
  }
  
  public static NetworkFeedback.a a(a paramA, NetworkFeedback.FeedbackConfig paramFeedbackConfig)
  {
    if (paramFeedbackConfig == null) {
      return null;
    }
    NetworkFeedback.a localA = new NetworkFeedback.a(paramFeedbackConfig);
    localA.c = "for Android";
    localA.d = TimeZone.getDefault().getID();
    localA.e = "Android";
    localA.f = Build.VERSION.RELEASE;
    localA.h = Locale.getDefault().toString();
    localA.i = Build.MODEL;
    localA.j = Build.MANUFACTURER;
    localA.k = DeviceUtils.c();
    localA.o = a.a(paramA);
    localA.p = a.b(paramA);
    localA.s = String.format(Locale.US, "%s %s %d", new Object[] { Build.HARDWARE, Build.FINGERPRINT, Integer.valueOf(DeviceUtils.a().intValue() / 1024) });
    Object localObject1;
    if (i.a())
    {
      localObject1 = "Yes";
      localA.t = ((String)localObject1);
      if (localA.q == null) {
        localA.q = new ArrayList();
      }
      localObject1 = new ArrayList();
      if (paramFeedbackConfig.attachmentPath != null) {
        localObject2 = paramFeedbackConfig.attachmentPath.iterator();
      }
    }
    else
    {
      for (;;)
      {
        if (!((Iterator)localObject2).hasNext()) {
          break label272;
        }
        String str = (String)((Iterator)localObject2).next();
        if (str != null)
        {
          File localFile = new File(str);
          if (localFile.exists())
          {
            ((ArrayList)localObject1).add(localFile);
            continue;
            localObject1 = "No";
            break;
          }
          Log.e("AttachmentFile not exist: ", str);
        }
      }
    }
    label272:
    Object localObject2 = a.b(false);
    if ((localObject2 != null) && (paramFeedbackConfig.bNeedLog)) {
      ((ArrayList)localObject1).add(new File((String)localObject2));
    }
    localObject2 = "" + "version_upgrade_history: " + paramFeedbackConfig.versionUpgradeHistory + "\r\n";
    localObject2 = (String)localObject2 + "umaid: " + paramFeedbackConfig.umaid + "\r\n";
    localObject2 = (String)localObject2 + "store_name: " + al.e(R.string.FN_STORE_NAME) + "\r\n";
    localObject2 = (String)localObject2 + "installed_apps: " + a(com.pf.common.b.c()) + "\r\n";
    localObject2 = (String)localObject2 + "os_upgrade_history: " + A() + "\r\n";
    localObject2 = (String)localObject2 + g.a() + "\r\n";
    localObject2 = (String)localObject2 + "sd storage:" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.c() / 1.073741824E9D) }) + "/" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.d() / 1.073741824E9D) }) + "\r\n";
    localObject2 = (String)localObject2 + "external storage:" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.e() / 1.073741824E9D) }) + "/" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.f() / 1.073741824E9D) }) + "\r\n";
    localObject2 = (String)localObject2 + "internal storage:" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.g() / 1.073741824E9D) }) + "/" + String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(com.pf.common.utility.u.h() / 1.073741824E9D) }) + "\r\n";
    localObject2 = a.a(false, (String)localObject2 + "device memory: " + E() + "\r\n", "moreinfo.txt");
    if ((localObject2 != null) && (paramFeedbackConfig.bNeedLog)) {
      ((ArrayList)localObject1).add(new File((String)localObject2));
    }
    localObject2 = f.l();
    if ((localObject2 != null) && (paramFeedbackConfig.bNeedLog)) {
      ((ArrayList)localObject1).add(new File((String)localObject2));
    }
    localObject2 = i.c();
    if ((localObject2 != null) && (paramFeedbackConfig.bNeedLog)) {
      Collections.addAll((Collection)localObject1, (Object[])localObject2);
    }
    paramA = Logger.a(a.c(paramA));
    if ((paramA != null) && (paramA.exists())) {
      ((ArrayList)localObject1).add(paramA);
    }
    paramA = Logger.b();
    if ((paramA != null) && (paramA.exists())) {
      ((ArrayList)localObject1).add(paramA);
    }
    paramA = B();
    if ((paramA != null) && (paramA.exists())) {
      ((ArrayList)localObject1).add(paramA);
    }
    a.a(com.pf.common.b.c(), (ArrayList)localObject1);
    paramA = NetworkFile.a(com.perfectcorp.utility.d.b(com.pf.common.b.c()), null);
    if (paramA == null) {
      Log.d(new Object[] { "logfile open fail" });
    }
    for (;;)
    {
      return localA;
      if (paramA.b < 5242880L) {
        localA.q.add(paramA);
      } else {
        Log.d(new Object[] { "logfile too large: ", Long.valueOf(paramA.b) });
      }
    }
  }
  
  @Deprecated
  public static NetworkFeedback.a a(String paramString1, String paramString2, NetworkFeedback.FeedbackConfig paramFeedbackConfig)
  {
    return a(new PreviewFeedbackActivity.a.a().a(paramString1).b(paramString2).a(), paramFeedbackConfig);
  }
  
  public static s<List<NetTask.b>> a(DoNetworkCall.ReportIssueResponse paramReportIssueResponse, List<NetworkFile.a> paramList)
  {
    Object localObject2 = z.h();
    if ((paramReportIssueResponse == null) || (paramReportIssueResponse.b() == null))
    {
      ((z)localObject2).a(new Throwable("reportIssueResponse or feedback is null."));
      return localObject2;
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ArrayList();
    if ("application/zip".equals(((NetworkFile.a)paramList.get(0)).c)) {
      ((List)localObject1).add(paramReportIssueResponse.b());
    }
    if ((paramReportIssueResponse.d() != null) && (!paramReportIssueResponse.d().isEmpty())) {
      ((List)localObject1).addAll(paramReportIssueResponse.d());
    }
    if (((List)localObject1).size() != paramList.size())
    {
      ((z)localObject2).a(new Throwable("reportIssueContent's size isn't equal to uploadFiles' size."));
      return localObject2;
    }
    paramReportIssueResponse = new LinkedHashMap();
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      paramReportIssueResponse.put(((List)localObject1).get(i), paramList.get(i));
      i += 1;
    }
    paramReportIssueResponse = paramReportIssueResponse.entrySet().iterator();
    while (paramReportIssueResponse.hasNext())
    {
      localObject1 = (Map.Entry)paramReportIssueResponse.next();
      paramList = new y(((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).b());
      localObject2 = new HashMap();
      ((Map)localObject2).put("x-amz-acl", ((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).d().b());
      ((Map)localObject2).put("Content-Type", ((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).d().d());
      paramList.a((Map)localObject2);
      localObject1 = new File(((NetworkFile.a)((Map.Entry)localObject1).getValue()).g);
      if (((File)localObject1).exists())
      {
        Log.b("1On1PreviewFeedbackActivity", "Start UploadFile");
        DoNetworkManager.a().a("1On1PreviewFeedbackActivity", "Start UploadFile");
        localArrayList.add(r.a(new NetTask.h((File)localObject1).d(paramList).g().f()));
      }
    }
    return n.b(localArrayList);
  }
  
  private static String a(long paramLong)
  {
    return String.valueOf((int)(b(paramLong) * 100.0F) / 100.0F);
  }
  
  public static StringBuilder a(Context paramContext)
  {
    StringBuilder localStringBuilder = null;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      localStringBuilder = new StringBuilder("[\"");
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        if (paramContext.hasNext()) {
          localStringBuilder.append(((ApplicationInfo)paramContext.next()).packageName);
        }
        while (paramContext.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
          localStringBuilder.append("\", \"");
          localStringBuilder.append(localApplicationInfo.packageName);
        }
      }
      localStringBuilder.append("\"]");
      return localStringBuilder;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = localStringBuilder;
      }
    }
  }
  
  private static float b(long paramLong)
  {
    return (float)(paramLong / Math.pow(1024.0D, 2.0D));
  }
  
  private static boolean f(boolean paramBoolean)
  {
    boolean bool = false;
    int i = BcLib.j();
    if (paramBoolean) {
      i = O[(O.length - 1)] + 1;
    }
    for (;;)
    {
      ar.b("Number of Launch: " + i);
      BcLib.b(i);
      if ((i <= 3) || (i % 3 != 0)) {
        break;
      }
      paramBoolean = true;
      return paramBoolean;
      i += 1;
    }
    int j = 0;
    for (;;)
    {
      paramBoolean = bool;
      if (j >= O.length) {
        break;
      }
      if (O[j] == i) {
        return true;
      }
      j += 1;
    }
  }
  
  protected void a(Uri paramUri)
  {
    Object localObject = findViewById(R.id.preview_image_mask);
    if (localObject != null) {
      ((View)localObject).setVisibility(0);
    }
    localObject = (ImageView)findViewById(R.id.preview_image);
    if (localObject != null) {
      ((ImageView)localObject).setImageURI(paramUri);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    c = 0L;
    paramBundle = getIntent();
    int j = paramBundle.getIntExtra("feedbackPreviewRes", 0);
    this.I = paramBundle.getBooleanExtra("IsOneOnOne", false);
    int i = j;
    if (j == 0) {
      i = R.layout.bc_activity_preview_feedback;
    }
    setContentView(i);
    this.z = ((NetworkFeedback.FeedbackConfig)paramBundle.getSerializableExtra("FeedbackConfig"));
    String str1 = paramBundle.getStringExtra("FeedbackDesc");
    String str2 = paramBundle.getStringExtra("FeedbackEmail");
    this.B = Model.b(Uri.class, paramBundle.getStringExtra("FeedbackImage"));
    this.K = paramBundle.getIntExtra("TopBarStyle", 0);
    if (this.K == 1) {
      C();
    }
    this.C = ((TextView)findViewById(R.id.bc_feedback_description));
    if (this.C != null) {
      this.C.setText(str1);
    }
    this.D = ((TextView)findViewById(R.id.bc_feedback_email));
    if (this.D != null) {
      this.D.setText(str2);
    }
    this.E = ((TextView)findViewById(R.id.bc_feedback_appver));
    if (this.E != null)
    {
      paramBundle = "Unknown";
      if (this.z != null) {
        paramBundle = this.z.appversion;
      }
      this.E.setText(paramBundle);
    }
    this.F = ((TextView)findViewById(R.id.bc_feedback_devicemodel));
    if (this.F != null) {
      this.F.setText(Build.MODEL);
    }
    this.G = ((TextView)findViewById(R.id.bc_feedback_osver));
    if (this.G != null) {
      this.G.setText(Build.VERSION.RELEASE);
    }
    this.H = ((TextView)findViewById(R.id.bc_feedback_time));
    if (this.H != null)
    {
      paramBundle = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ssZ", Locale.US);
      paramBundle.setTimeZone(TimeZone.getTimeZone("GMT"));
      this.H.setText(paramBundle.format(new Date()));
    }
    if (((ViewGroup)findViewById(R.id.bc_feedback_image_panel) != null) && (this.B != null))
    {
      paramBundle = this.B.iterator();
      while (paramBundle.hasNext())
      {
        final Uri localUri = (Uri)paramBundle.next();
        if (localUri != null)
        {
          com.cyberlink.beautycircle.view.widgetpool.common.b localB = D();
          localB.a(localUri);
          localB.a().setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              PreviewFeedbackActivity.this.a(localUri);
            }
          });
        }
      }
    }
    paramBundle = findViewById(R.id.preview_image_mask);
    if (paramBundle != null) {
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView.setVisibility(8);
        }
      });
    }
    paramBundle = findViewById(R.id.edit_feedback_back);
    if (paramBundle != null) {
      paramBundle.setOnClickListener(this.M);
    }
    paramBundle = findViewById(R.id.edit_feedback_next);
    if (paramBundle != null) {
      paramBundle.setOnClickListener(this.N);
    }
    this.A = a(str1, str2, this.z);
    if (PackageUtils.c()) {
      this.i = false;
    }
  }
  
  public void onRightBtnClick(final View paramView)
  {
    if ((this.z == null) || (this.z.apiUri == null)) {
      return;
    }
    paramView = this.z.apiUri;
    n();
    new PromisedTask()
    {
      protected Void a(Void paramAnonymousVoid)
      {
        if ((PreviewFeedbackActivity.b(PreviewFeedbackActivity.this) != null) && (!PreviewFeedbackActivity.b(PreviewFeedbackActivity.this).isEmpty()))
        {
          PreviewFeedbackActivity.a(PreviewFeedbackActivity.this, PreviewFeedbackActivity.b(PreviewFeedbackActivity.this).size());
          if (PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q == null) {
            PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q = new ArrayList();
          }
          paramAnonymousVoid = PreviewFeedbackActivity.b(PreviewFeedbackActivity.this).iterator();
          while (paramAnonymousVoid.hasNext())
          {
            Uri localUri = (Uri)paramAnonymousVoid.next();
            paramAnonymousVoid.remove();
            if (localUri != null) {
              PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q.add(NetworkFile.a(localUri, ImageUtils.CompressSetting.FeedbackSnapshot));
            }
          }
        }
        if (PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q != null)
        {
          paramAnonymousVoid = PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q.iterator();
          for (long l = 0L; paramAnonymousVoid.hasNext(); l = ((NetworkFile.a)paramAnonymousVoid.next()).b + l) {}
          Log.c(new Object[] { "Attachment size: ", Long.valueOf(l) });
          if (l > 5242880L)
          {
            PreviewFeedbackActivity.this.o();
            new AlertDialog.a(PreviewFeedbackActivity.this).d().c(R.string.bc_dialog_button_ok, null).e(R.string.bc_feedback_dialog_file_size_excceed).h();
            return null;
          }
        }
        if (PreviewFeedbackActivity.d(PreviewFeedbackActivity.this))
        {
          DoNetworkCall.a(AccountManager.g(), PreviewFeedbackActivity.e(PreviewFeedbackActivity.this), PreviewFeedbackActivity.c(PreviewFeedbackActivity.this)).g().a(new e()new e
          {
            public void a(DoNetworkCall.ReportIssueResponse paramAnonymous2ReportIssueResponse)
              throws Exception
            {
              com.pf.common.c.d.a(PreviewFeedbackActivity.a(paramAnonymous2ReportIssueResponse, PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q), new m()
              {
                public void a(Throwable paramAnonymous3Throwable)
                {
                  Log.b("1On1PreviewFeedbackActivity", "something wrong when get uploadReportIssueFile result, throwable: " + paramAnonymous3Throwable);
                  PreviewFeedbackActivity.this.o();
                  ar.b(R.string.bc_feedback_message_send_fail);
                }
                
                public void a(List<NetTask.b> paramAnonymous3List)
                {
                  Log.b("1On1PreviewFeedbackActivity", "uploadReportIssueFile completes");
                  PreviewFeedbackActivity.this.o();
                  new AlertDialog.a(PreviewFeedbackActivity.this).c(R.string.bc_dialog_button_ok, new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous4DialogInterface, int paramAnonymous4Int)
                    {
                      a.a(com.perfectcorp.utility.d.b(PreviewFeedbackActivity.this.getApplicationContext()));
                      Intents.b(PreviewFeedbackActivity.this, Uri.parse("ymk://action/one_to_one_ba"));
                    }
                  }).c(R.string.bc_feedback_title_send_ok).e(R.string.bc_feedback_dialog_send_ok).h();
                }
              });
            }
          }, new e()
          {
            public void a(Throwable paramAnonymous2Throwable)
              throws Exception
            {
              Log.b("1On1PreviewFeedbackActivity", "something wrong when get reportIssue result, throwable: " + paramAnonymous2Throwable);
              PreviewFeedbackActivity.this.o();
              ar.b(R.string.bc_feedback_message_send_fail);
            }
          });
          return null;
        }
        NetworkFeedback.a(paramView, PreviewFeedbackActivity.c(PreviewFeedbackActivity.this)).a(new PromisedTask.b()
        {
          protected void a()
          {
            PreviewFeedbackActivity.this.o();
            super.a();
          }
          
          protected void a(int paramAnonymous2Int)
          {
            PreviewFeedbackActivity.this.o();
            super.a(paramAnonymous2Int);
            ar.b(R.string.bc_feedback_message_send_fail);
          }
          
          protected void a(NetworkFeedback.FeedbackResult paramAnonymous2FeedbackResult)
          {
            PreviewFeedbackActivity.this.o();
            if (paramAnonymous2FeedbackResult != null) {}
            for (paramAnonymous2FeedbackResult = paramAnonymous2FeedbackResult.status; !"OK".equals(paramAnonymous2FeedbackResult); paramAnonymous2FeedbackResult = "")
            {
              a(-2147483647);
              return;
            }
            PreviewFeedbackActivity.this.setResult(-1);
            if (PreviewFeedbackActivity.f(PreviewFeedbackActivity.this).product.equals("BeautyCircle")) {
              PreviewFeedbackActivity.e(true);
            }
            new AlertDialog.a(PreviewFeedbackActivity.this).c(R.string.bc_dialog_button_ok, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                PreviewFeedbackActivity.g(PreviewFeedbackActivity.this);
                a.a(com.perfectcorp.utility.d.b(PreviewFeedbackActivity.this.getApplicationContext()));
              }
            }).c(R.string.bc_feedback_title_send_ok).e(R.string.bc_feedback_dialog_send_ok).h();
          }
        });
        return null;
      }
    }.d(null);
  }
  
  public static class a
  {
    private final String a;
    private final String b;
    private final boolean c;
    
    private a(a paramA)
    {
      this.a = a.a(paramA);
      this.b = a.b(paramA);
      this.c = a.c(paramA);
    }
    
    public static class a
    {
      private String a;
      private String b;
      private boolean c;
      
      public a() {}
      
      public a a(String paramString)
      {
        this.a = paramString;
        return this;
      }
      
      public a a(boolean paramBoolean)
      {
        this.c = paramBoolean;
        return this;
      }
      
      public PreviewFeedbackActivity.a a()
      {
        return new PreviewFeedbackActivity.a(this, null);
      }
      
      public a b(String paramString)
      {
        this.b = paramString;
        return this;
      }
    }
  }
}
