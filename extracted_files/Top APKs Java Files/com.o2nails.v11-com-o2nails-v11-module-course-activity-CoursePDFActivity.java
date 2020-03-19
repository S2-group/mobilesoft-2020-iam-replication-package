package com.o2nails.v11.module.course.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.o2nails.v11.bean.MediaResourceVO;
import com.o2nails.v11.bean.UpdateInfo;
import com.o2nails.v11.constants.Constants;
import com.o2nails.v11.factory.FileMassege;
import com.o2nails.v11.factory.SizeCalculation;
import com.o2nails.v11.factory.skin.GetResourcesUtils;
import com.o2nails.v11.http.HttpGet;
import com.o2nails.v11.module.course.service.CountService;
import java.io.File;
import java.util.List;
import t.lib.DBLIB;

public class CoursePDFActivity
  extends Activity
{
  MediaResourceVO bean;
  private Context context;
  private TextView down_tips_tv;
  HttpHandler handler = null;
  private HttpHandler httpHandler;
  private TextView num_tv;
  Integer pageNumber = Integer.valueOf(0);
  private String pdf_url;
  private ProgressBar progressBar1;
  private String target;
  
  public CoursePDFActivity() {}
  
  public static int getSDKVersionNumber()
  {
    try
    {
      int i = Integer.valueOf(Build.VERSION.SDK).intValue();
      return i;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0;
  }
  
  private boolean isAvilible(Context paramContext, String paramString, UpdateInfo paramUpdateInfo)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return false;
      }
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return ((PackageInfo)paramContext.get(i)).versionCode >= paramUpdateInfo.getCode();
      }
      i += 1;
    }
  }
  
  public void Http(String paramString1, final String paramString2)
  {
    String str = FileMassege.getSDPath() + Constants.V11NAIL + Constants.DOWNLOAD + "/" + paramString2;
    this.handler = new HttpUtils().download(paramString1, str, false, false, new RequestCallBack()
    {
      public void onFailure(HttpException paramAnonymousHttpException, String paramAnonymousString)
      {
        CoursePDFActivity.this.down_tips_tv.setText(FileMassege.getLangConKey("文件下载失败", CoursePDFActivity.this.context) + "!");
      }
      
      public void onLoading(long paramAnonymousLong1, long paramAnonymousLong2, boolean paramAnonymousBoolean)
      {
        CoursePDFActivity.this.progressBar1.setProgress((int)(paramAnonymousLong2 * 100L / paramAnonymousLong1));
        CoursePDFActivity.this.down_tips_tv.setText(FileMassege.getLangConKey("正在下载:", CoursePDFActivity.this.context) + (int)(paramAnonymousLong2 * 100L / paramAnonymousLong1) + "%");
      }
      
      public void onStart()
      {
        CoursePDFActivity.this.progressBar1.setVisibility(0);
        CoursePDFActivity.this.down_tips_tv.setVisibility(0);
        Log.e("app name", FileMassege.getSDPath() + Constants.V11NAIL + Constants.DOWNLOAD + "/" + paramString2);
      }
      
      public void onSuccess(ResponseInfo<File> paramAnonymousResponseInfo)
      {
        paramAnonymousResponseInfo = new File(FileMassege.getSDPath() + Constants.V11NAIL + Constants.DOWNLOAD + "/" + paramString2);
        CoursePDFActivity.this.installApk(paramAnonymousResponseInfo);
        CoursePDFActivity.this.finish();
      }
    });
  }
  
  public Intent getPdfFileIntent(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/pdf");
    return localIntent;
  }
  
  public void http()
  {
    this.httpHandler = new HttpGet(this.context).httpDown(null, null, this.pdf_url, this.target, new RequestCallBack()
    {
      public void onFailure(HttpException paramAnonymousHttpException, String paramAnonymousString)
      {
        if (paramAnonymousHttpException.getExceptionCode() == 416)
        {
          CoursePDFActivity.this.down_tips_tv.setText("");
          CoursePDFActivity.this.progressBar1.setVisibility(8);
          try
          {
            CoursePDFActivity.this.setFDP(CoursePDFActivity.this.target);
            CoursePDFActivity.this.setDB();
            return;
          }
          catch (DbException paramAnonymousHttpException)
          {
            for (;;)
            {
              paramAnonymousHttpException.printStackTrace();
            }
          }
        }
        CoursePDFActivity.this.down_tips_tv.setText(FileMassege.getLangConKey("XZSB", CoursePDFActivity.this.context));
      }
      
      public void onLoading(long paramAnonymousLong1, long paramAnonymousLong2, boolean paramAnonymousBoolean)
      {
        super.onLoading(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousBoolean);
        CoursePDFActivity.this.down_tips_tv.setText(FileMassege.getLangConKey("下载", CoursePDFActivity.this.context) + "...(" + SizeCalculation.Size(paramAnonymousLong2) + "/" + SizeCalculation.Size(paramAnonymousLong1) + ")");
        CoursePDFActivity.this.progressBar1.setProgress((int)(100L * paramAnonymousLong2 / paramAnonymousLong1));
      }
      
      public void onStart()
      {
        super.onStart();
        CoursePDFActivity.this.progressBar1.setVisibility(0);
      }
      
      public void onSuccess(ResponseInfo<File> paramAnonymousResponseInfo)
      {
        CoursePDFActivity.this.down_tips_tv.setText("");
        CoursePDFActivity.this.progressBar1.setVisibility(8);
        Log.e("target+pdf_url", CoursePDFActivity.this.target);
        try
        {
          CoursePDFActivity.this.setFDP(CoursePDFActivity.this.target);
          CoursePDFActivity.this.setDB();
          return;
        }
        catch (DbException paramAnonymousResponseInfo)
        {
          for (;;)
          {
            paramAnonymousResponseInfo.printStackTrace();
          }
        }
      }
    });
  }
  
  public void initText()
  {
    ((TextView)findViewById(2131361793)).setText(FileMassege.getLangConKey("TWJC", this.context));
  }
  
  public void initView()
  {
    this.num_tv = ((TextView)findViewById(2131361832));
    this.down_tips_tv = ((TextView)findViewById(2131361894));
    this.progressBar1 = ((ProgressBar)findViewById(2131361840));
    this.progressBar1.setVisibility(8);
  }
  
  protected void installApk(File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    startActivity(localIntent);
    Process.killProcess(Process.myPid());
  }
  
  public void onBack(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    this.context = this;
    setContentView(GetResourcesUtils.ResourceChange(this.context, 2130903060));
    initText();
    initView();
    setDocumentPath();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.httpHandler != null) && (!this.httpHandler.isCancelled())) {
      this.httpHandler.cancel();
    }
    if ((this.handler != null) && (!this.handler.isCancelled())) {
      this.handler.cancel();
    }
  }
  
  public boolean openPdfFile(String paramString)
  {
    paramString = getPdfFileIntent(paramString);
    if (paramString != null) {}
    try
    {
      startActivity(paramString);
      return true;
    }
    catch (ActivityNotFoundException paramString) {}
    Toast.makeText(this.context, "null", 0).show();
    return false;
    return false;
  }
  
  public void setDB()
  {
    try
    {
      DbUtils localDbUtils = DBLIB.DB(this.context);
      if (localDbUtils != null)
      {
        if (CountService.IsExist(localDbUtils))
        {
          MediaResourceVO localMediaResourceVO = (MediaResourceVO)localDbUtils.findFirst(Selector.from(MediaResourceVO.class).where("name", "=", this.bean.getName()).and("parentId", "=", Integer.valueOf(this.bean.getParentId())));
          this.bean.setState(5);
          if (localMediaResourceVO != null)
          {
            this.bean.setId(localMediaResourceVO.getId());
            localDbUtils.update(this.bean, new String[] { "name", "type", "size", "url", "URLhash", "id", "parentId", "progress", "state", "target" });
            return;
          }
          localDbUtils.save(this.bean);
          return;
        }
        localDbUtils.save(this.bean);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public void setDocumentPath()
  {
    try
    {
      this.bean = ((MediaResourceVO)JSON.parseObject(getIntent().getStringExtra(Constants.URL), MediaResourceVO.class));
      if (this.bean != null)
      {
        this.pdf_url = this.bean.getUrl();
        this.target = (this.bean.getTarget() + "/" + this.bean.getName());
        FileMassege.makeRootDirectory(this.target, true);
        http();
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void setFDP(String paramString)
    throws DbException
  {
    Intent localIntent = new Intent(this.context, PDFJSActivity.class);
    localIntent.putExtra("pdfUrl", paramString);
    startActivity(localIntent);
    finish();
  }
}
