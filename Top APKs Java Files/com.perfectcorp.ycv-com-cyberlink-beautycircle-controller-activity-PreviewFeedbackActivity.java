package com.cyberlink.beautycircle.controller.activity;

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
import com.cyberlink.beautycircle.e.d;
import com.cyberlink.beautycircle.e.g;
import com.cyberlink.beautycircle.e.h;
import com.cyberlink.beautycircle.e.k;
import com.cyberlink.beautycircle.model.network.NetworkFeedback;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.FeedbackConfig;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.FeedbackResult;
import com.cyberlink.beautycircle.model.network.NetworkFeedback.a;
import com.cyberlink.beautycircle.model.network.NetworkFile;
import com.cyberlink.beautycircle.model.network.NetworkFile.b;
import com.cyberlink.beautycircle.utility.AccountManager;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.GetUploadUrlResult;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.Header;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkCall.ReportIssueResponse;
import com.cyberlink.beautycircle.utility.doserver.DoNetworkManager;
import com.cyberlink.beautycircle.utility.doserver.Logger;
import com.cyberlink.beautycircle.utility.g;
import com.google.common.util.concurrent.l;
import com.google.common.util.concurrent.q;
import com.google.common.util.concurrent.r;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.ImageUtils.CompressSetting;
import com.perfectcorp.utility.a;
import com.pf.common.android.DeviceUtils;
import com.pf.common.android.PackageUtils;
import com.pf.common.utility.Log;
import com.pf.common.utility.NetTask.b;
import com.pf.common.utility.NetTask.h;
import com.pf.common.utility.PromisedTask;
import com.pf.common.utility.PromisedTask.b;
import com.pf.common.utility.aa;
import com.pf.common.utility.f;
import com.pf.common.utility.j;
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
  
  private void A()
  {
    Object localObject = findViewById(e.g.include_topbar_panel);
    if (localObject != null) {
      ((View)localObject).setBackgroundResource(e.d.bc_color_black);
    }
    localObject = (TextView)findViewById(e.g.edit_feedback_title);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(com.pf.common.utility.w.c(e.d.bc_color_white));
    }
    localObject = (TintableImageView)findViewById(e.g.edit_feedback_back);
    if (localObject != null) {
      ((TintableImageView)localObject).setColorFilter(ContextCompat.getColorStateList(this, e.d.bc_topbar_btn_icon_white));
    }
    localObject = (TintableImageView)findViewById(e.g.edit_feedback_next);
    if (localObject != null) {
      ((TintableImageView)localObject).setColorFilter(ContextCompat.getColorStateList(this, e.d.bc_topbar_btn_icon_white));
    }
  }
  
  private com.cyberlink.beautycircle.view.widgetpool.common.b B()
  {
    com.cyberlink.beautycircle.view.widgetpool.common.b localB = new com.cyberlink.beautycircle.view.widgetpool.common.b(this, true);
    this.L.add(localB);
    ViewGroup localViewGroup = (ViewGroup)findViewById(e.g.bc_feedback_image_panel);
    localViewGroup.addView(localB.a(LayoutInflater.from(this), localViewGroup, null));
    return localB;
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
    if (com.pf.common.android.c.a()) {
      localObject1 = "Yes";
    } else {
      localObject1 = "No";
    }
    localA.t = ((String)localObject1);
    if (localA.q == null) {
      localA.q = new ArrayList();
    }
    ArrayList localArrayList = new ArrayList();
    if (paramFeedbackConfig.attachmentPath != null)
    {
      localObject1 = paramFeedbackConfig.attachmentPath.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          File localFile = new File((String)localObject2);
          if (localFile.exists()) {
            localArrayList.add(localFile);
          } else {
            Log.e("AttachmentFile not exist: ", (String)localObject2);
          }
        }
      }
    }
    Object localObject1 = a.b(false);
    if ((localObject1 != null) && (paramFeedbackConfig.bNeedLog)) {
      localArrayList.add(new File((String)localObject1));
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("");
    ((StringBuilder)localObject1).append("version_upgrade_history: ");
    ((StringBuilder)localObject1).append(paramFeedbackConfig.versionUpgradeHistory);
    ((StringBuilder)localObject1).append("\r\n");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("os_version_upgrade_history: ");
    ((StringBuilder)localObject2).append(paramFeedbackConfig.osVersionUpgradeHistory);
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("umaid: ");
    ((StringBuilder)localObject2).append(paramFeedbackConfig.umaid);
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("store_name: ");
    ((StringBuilder)localObject2).append(com.pf.common.utility.w.e(e.k.FN_STORE_NAME));
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("installed_apps: ");
    ((StringBuilder)localObject2).append(a(com.pf.common.b.c()));
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("os_upgrade_history: ");
    ((StringBuilder)localObject2).append(y());
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(g.a());
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("sd storage:");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.c() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.d() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("external storage:");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.e() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.f() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("internal storage:");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.g() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(j.h() / 1.073741824E9D) }));
    ((StringBuilder)localObject2).append("\r\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    if (TextUtils.isEmpty(paramFeedbackConfig.deviceMemory))
    {
      localObject1 = "\r\n";
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("device memory: ");
      ((StringBuilder)localObject1).append(paramFeedbackConfig.deviceMemory);
      ((StringBuilder)localObject1).append(" MB");
      ((StringBuilder)localObject1).append("\r\n");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    ((StringBuilder)localObject2).append((String)localObject1);
    localObject1 = a.a(false, ((StringBuilder)localObject2).toString(), "moreinfo.txt");
    if ((localObject1 != null) && (paramFeedbackConfig.bNeedLog)) {
      localArrayList.add(new File((String)localObject1));
    }
    localObject1 = com.cyberlink.beautycircle.model.network.e.l();
    if ((localObject1 != null) && (paramFeedbackConfig.bNeedLog)) {
      localArrayList.add(new File((String)localObject1));
    }
    localObject1 = com.pf.common.android.c.c();
    if ((localObject1 != null) && (paramFeedbackConfig.bNeedLog)) {
      Collections.addAll(localArrayList, (Object[])localObject1);
    }
    paramA = Logger.a(a.c(paramA));
    if ((paramA != null) && (paramA.exists())) {
      localArrayList.add(paramA);
    }
    paramA = Logger.a();
    if ((paramA != null) && (paramA.exists())) {
      localArrayList.add(paramA);
    }
    paramA = z();
    if ((paramA != null) && (paramA.exists())) {
      localArrayList.add(paramA);
    }
    paramA = Logger.b();
    if ((paramA != null) && (paramA.exists())) {
      localArrayList.add(paramA);
    }
    a.a(com.pf.common.b.c(), localArrayList);
    paramA = NetworkFile.a(com.perfectcorp.utility.d.b(com.pf.common.b.c()), null);
    if (paramA == null)
    {
      Log.d(new Object[] { "logfile open fail" });
      return localA;
    }
    if (paramA.b < 5242880L)
    {
      localA.q.add(paramA);
      return localA;
    }
    Log.d(new Object[] { "logfile too large: ", Long.valueOf(paramA.b) });
    return localA;
  }
  
  @Deprecated
  public static NetworkFeedback.a a(String paramString1, String paramString2, NetworkFeedback.FeedbackConfig paramFeedbackConfig)
  {
    return a(new PreviewFeedbackActivity.a.a().a(paramString1).b(paramString2).a(), paramFeedbackConfig);
  }
  
  public static r<List<NetTask.b>> a(DoNetworkCall.ReportIssueResponse paramReportIssueResponse, List<NetworkFile.b> paramList)
  {
    Object localObject2 = com.google.common.util.concurrent.w.h();
    if ((paramReportIssueResponse != null) && (paramReportIssueResponse.b() != null))
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject1 = new ArrayList();
      int i = 0;
      if ("application/zip".equals(((NetworkFile.b)paramList.get(0)).c)) {
        ((List)localObject1).add(paramReportIssueResponse.b());
      }
      if ((paramReportIssueResponse.d() != null) && (!paramReportIssueResponse.d().isEmpty())) {
        ((List)localObject1).addAll(paramReportIssueResponse.d());
      }
      if (((List)localObject1).size() != paramList.size())
      {
        ((com.google.common.util.concurrent.w)localObject2).a(new Throwable("reportIssueContent's size isn't equal to uploadFiles' size."));
        return localObject2;
      }
      paramReportIssueResponse = new LinkedHashMap();
      while (i < ((List)localObject1).size())
      {
        paramReportIssueResponse.put(((List)localObject1).get(i), paramList.get(i));
        i += 1;
      }
      paramReportIssueResponse = paramReportIssueResponse.entrySet().iterator();
      while (paramReportIssueResponse.hasNext())
      {
        localObject1 = (Map.Entry)paramReportIssueResponse.next();
        paramList = new com.pf.common.utility.m(((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).b());
        localObject2 = new HashMap();
        ((Map)localObject2).put("x-amz-acl", ((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).d().b());
        ((Map)localObject2).put("Content-Type", ((DoNetworkCall.GetUploadUrlResult)((Map.Entry)localObject1).getKey()).d().d());
        paramList.a((Map)localObject2);
        localObject1 = new File(((NetworkFile.b)((Map.Entry)localObject1).getValue()).g);
        if (((File)localObject1).exists())
        {
          Log.b("1On1PreviewFeedbackActivity", "Start UploadFile");
          DoNetworkManager.a().a("1On1PreviewFeedbackActivity", "Start UploadFile");
          localArrayList.add(q.a(new NetTask.h((File)localObject1).d(paramList).g().b()));
        }
      }
      return com.google.common.util.concurrent.m.b(localArrayList);
    }
    ((com.google.common.util.concurrent.w)localObject2).a(new Throwable("reportIssueResponse or feedback is null."));
    return localObject2;
  }
  
  public static StringBuilder a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    }
    catch (Exception paramContext)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramContext = null;
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
  
  private static boolean e(boolean paramBoolean)
  {
    int i = BcLib.j();
    if (paramBoolean) {
      i = O[(O.length - 1)] + 1;
    } else {
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Number of Launch: ");
    localStringBuilder.append(i);
    aa.b(localStringBuilder.toString());
    BcLib.b(i);
    if ((i > 3) && (i % 3 == 0)) {
      return true;
    }
    int j = 0;
    while (j < O.length)
    {
      if (O[j] == i) {
        return true;
      }
      j += 1;
    }
    return false;
  }
  
  public static String y()
  {
    return com.cyberlink.beautycircle.c.a().getString("OsUpgradeHistory", "");
  }
  
  public static File z()
  {
    try
    {
      Object localObject = com.perfectcorp.billing.d.a();
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        Log.c(new Object[] { "[getIapLog] Can't get logger folder path" });
        return null;
      }
      localObject = new File((String)localObject);
      if (!((File)localObject).isDirectory())
      {
        Log.c(new Object[] { "[getIapLog] logger folder path is not a directory" });
        return null;
      }
      localObject = ((File)localObject).listFiles();
      if ((localObject != null) && (localObject.length != 0))
      {
        a.a(com.pf.common.b.c(), new ArrayList(Arrays.asList((Object[])localObject)));
        localObject = new File(com.perfectcorp.utility.d.b(com.pf.common.b.c()));
        File localFile = new File(((File)localObject).getParent(), "IapLog.zip");
        localFile.delete();
        if (((File)localObject).renameTo(localFile))
        {
          Log.c(new Object[] { "[getIapLog] pack succeed, file=", localFile });
          return localFile;
        }
        Log.c(new Object[] { "[getIapLog] rename ", localObject, " to ", localFile, " failed." });
        return null;
      }
      Log.c(new Object[] { "[getIapLog] No file in folder" });
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  protected void b(Uri paramUri)
  {
    Object localObject = findViewById(e.g.preview_image_mask);
    if (localObject != null) {
      ((View)localObject).setVisibility(0);
    }
    localObject = (ImageView)findViewById(e.g.preview_image);
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
      i = e.h.bc_activity_preview_feedback;
    }
    setContentView(i);
    this.z = ((NetworkFeedback.FeedbackConfig)paramBundle.getSerializableExtra("FeedbackConfig"));
    String str1 = paramBundle.getStringExtra("FeedbackDesc");
    String str2 = paramBundle.getStringExtra("FeedbackEmail");
    this.B = Model.b(Uri.class, paramBundle.getStringExtra("FeedbackImage"));
    this.K = paramBundle.getIntExtra("TopBarStyle", 0);
    if (this.K == 1) {
      A();
    }
    this.C = ((TextView)findViewById(e.g.bc_feedback_description));
    if (this.C != null) {
      this.C.setText(str1);
    }
    this.D = ((TextView)findViewById(e.g.bc_feedback_email));
    if (this.D != null) {
      this.D.setText(str2);
    }
    this.E = ((TextView)findViewById(e.g.bc_feedback_appver));
    if (this.E != null)
    {
      paramBundle = "Unknown";
      if (this.z != null) {
        paramBundle = this.z.appversion;
      }
      this.E.setText(paramBundle);
    }
    this.F = ((TextView)findViewById(e.g.bc_feedback_devicemodel));
    if (this.F != null) {
      this.F.setText(Build.MODEL);
    }
    this.G = ((TextView)findViewById(e.g.bc_feedback_osver));
    if (this.G != null) {
      this.G.setText(Build.VERSION.RELEASE);
    }
    this.H = ((TextView)findViewById(e.g.bc_feedback_time));
    if (this.H != null)
    {
      paramBundle = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ssZ", Locale.US);
      paramBundle.setTimeZone(TimeZone.getTimeZone("GMT"));
      this.H.setText(paramBundle.format(new Date()));
    }
    if (((ViewGroup)findViewById(e.g.bc_feedback_image_panel) != null) && (this.B != null))
    {
      paramBundle = this.B.iterator();
      while (paramBundle.hasNext())
      {
        final Uri localUri = (Uri)paramBundle.next();
        if (localUri != null)
        {
          com.cyberlink.beautycircle.view.widgetpool.common.b localB = B();
          localB.a(localUri);
          localB.a().setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              PreviewFeedbackActivity.this.b(localUri);
            }
          });
        }
      }
    }
    paramBundle = findViewById(e.g.preview_image_mask);
    if (paramBundle != null) {
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView.setVisibility(8);
        }
      });
    }
    paramBundle = findViewById(e.g.edit_feedback_back);
    if (paramBundle != null) {
      paramBundle.setOnClickListener(this.M);
    }
    paramBundle = findViewById(e.g.edit_feedback_next);
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
    if (this.z != null)
    {
      if (this.z.apiUri == null) {
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
          long l = 0L;
          if (PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q != null)
          {
            paramAnonymousVoid = PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q.iterator();
            while (paramAnonymousVoid.hasNext()) {
              l += ((NetworkFile.b)paramAnonymousVoid.next()).b;
            }
            Log.c(new Object[] { "Attachment size: ", Long.valueOf(l) });
            if (l > 5242880L)
            {
              PreviewFeedbackActivity.this.o();
              new AlertDialog.a(PreviewFeedbackActivity.this).b().b(e.k.bc_dialog_button_ok, null).e(e.k.bc_feedback_dialog_file_size_excceed).e();
              return null;
            }
          }
          if (PreviewFeedbackActivity.d(PreviewFeedbackActivity.this))
          {
            DoNetworkCall.a(AccountManager.f(), PreviewFeedbackActivity.e(PreviewFeedbackActivity.this), PreviewFeedbackActivity.c(PreviewFeedbackActivity.this)).g().a(new io.reactivex.b.e()new io.reactivex.b.e
            {
              public void a(DoNetworkCall.ReportIssueResponse paramAnonymous2ReportIssueResponse)
              {
                com.pf.common.guava.d.a(PreviewFeedbackActivity.a(paramAnonymous2ReportIssueResponse, PreviewFeedbackActivity.c(PreviewFeedbackActivity.this).q), new l()
                {
                  public void a(Throwable paramAnonymous3Throwable)
                  {
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append("something wrong when get uploadReportIssueFile result, throwable: ");
                    localStringBuilder.append(paramAnonymous3Throwable);
                    Log.b("1On1PreviewFeedbackActivity", localStringBuilder.toString());
                    PreviewFeedbackActivity.this.o();
                    aa.b(e.k.bc_feedback_message_send_fail);
                  }
                  
                  public void a(List<NetTask.b> paramAnonymous3List)
                  {
                    Log.b("1On1PreviewFeedbackActivity", "uploadReportIssueFile completes");
                    PreviewFeedbackActivity.this.o();
                    new AlertDialog.a(PreviewFeedbackActivity.this).b(e.k.bc_dialog_button_ok, new DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface paramAnonymous4DialogInterface, int paramAnonymous4Int)
                      {
                        a.a(com.perfectcorp.utility.d.b(PreviewFeedbackActivity.this.getApplicationContext()));
                        Intents.b(PreviewFeedbackActivity.this, Uri.parse("ymk://action/one_to_one_ba"));
                      }
                    }).c(e.k.bc_feedback_title_send_ok).e(e.k.bc_feedback_dialog_send_ok).e();
                  }
                });
              }
            }, new io.reactivex.b.e()
            {
              public void a(Throwable paramAnonymous2Throwable)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("something wrong when get reportIssue result, throwable: ");
                localStringBuilder.append(paramAnonymous2Throwable);
                Log.b("1On1PreviewFeedbackActivity", localStringBuilder.toString());
                PreviewFeedbackActivity.this.o();
                aa.b(e.k.bc_feedback_message_send_fail);
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
              aa.b(e.k.bc_feedback_message_send_fail);
            }
            
            protected void a(NetworkFeedback.FeedbackResult paramAnonymous2FeedbackResult)
            {
              PreviewFeedbackActivity.this.o();
              if (paramAnonymous2FeedbackResult != null) {
                paramAnonymous2FeedbackResult = paramAnonymous2FeedbackResult.status;
              } else {
                paramAnonymous2FeedbackResult = "";
              }
              if (!"OK".equals(paramAnonymous2FeedbackResult))
              {
                a(-2147483647);
                return;
              }
              PreviewFeedbackActivity.this.setResult(-1);
              if (PreviewFeedbackActivity.f(PreviewFeedbackActivity.this).product.equals("BeautyCircle")) {
                PreviewFeedbackActivity.d(true);
              }
              new AlertDialog.a(PreviewFeedbackActivity.this).b(e.k.bc_dialog_button_ok, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  PreviewFeedbackActivity.g(PreviewFeedbackActivity.this);
                  a.a(com.perfectcorp.utility.d.b(PreviewFeedbackActivity.this.getApplicationContext()));
                }
              }).c(e.k.bc_feedback_title_send_ok).e(e.k.bc_feedback_dialog_send_ok).e();
            }
          });
          return null;
        }
      }.d(null);
      return;
    }
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
