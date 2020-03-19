package com.nvison.gpsmycity.master.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.nvison.gpsmycity.data.DatabaseManager;
import com.nvison.gpsmycity.data.SessionManager;
import com.nvison.gpsmycity.entity.Upgrade;
import com.nvison.gpsmycity.master.common.AppConstants;
import com.nvison.gpsmycity.master.common.CustomDialog;
import com.nvison.gpsmycity.master.common.FileUtils;
import com.nvison.gpsmycity.master.common.FileUtils.DownloadListner;
import com.nvison.gpsmycity.master.map.MapCreate;
import com.nvison.gpsmycity.master.objects.BaseDo;
import com.nvison.gpsmycity.master.objects.GuideDataDo;
import com.nvison.gpsmycity.master.objects.GuidsDo;
import com.nvison.gpsmycity.master.objects.SightsDo;
import com.nvison.gpsmycity.master.utils.KeyValue;
import com.nvison.gpsmycity.master.utils.PreferenceUtils;
import com.nvison.gpsmycity.master.utils.SharedPrefUtils;
import com.nvison.gpsmycity.master.walk.WkCreate;
import com.nvison.gpsmycity.util.Utils;
import com.nvison.gpsmycity.web.WebManager;
import com.skobbler.ngx.SKDeveloperKeyException;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.map.SKMapViewStyle;
import com.skobbler.ngx.navigation.SKAdvisorSettings;
import com.skobbler.ngx.navigation.SKAdvisorSettings.SKAdvisorLanguage;
import com.skobbler.ngx.packages.SKPackage;
import com.skobbler.ngx.packages.SKPackageManager;
import com.skobbler.ngx.util.SKLogging;
import java.io.File;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TravelArticleFragment
  extends Fragment
  implements FileUtils.DownloadListner
{
  private ProgressDialog _dialog;
  private BaseDo baseDo;
  public Context context;
  private CustomDialog dialog;
  private Dialog dialogDownload;
  private TextView dialogMessageTextView;
  private GuideDataDo guide;
  private GuidsDo guidsDo;
  String mapResourcesDir;
  private String mapResourcesDirPath;
  private PreferenceUtils preferenceUtils;
  private ProgressBar progressBar;
  private SessionManager sessionManager;
  private ArrayList<SightsDo> sights;
  private Timer timer;
  private TextView tvProgress;
  private TextView tvlike;
  private TextView tvlisten;
  private TextView tvupgrade;
  private TextView tvwalk;
  private ShowUpgradeCustomDialog updialog;
  
  public TravelArticleFragment() {}
  
  public TravelArticleFragment(Context paramContext, GuideDataDo paramGuideDataDo, GuidsDo paramGuidsDo)
  {
    this.context = paramContext;
    this.guide = paramGuideDataDo;
    this.guidsDo = paramGuidsDo;
    this.preferenceUtils = new PreferenceUtils(paramContext);
  }
  
  @SuppressLint({"NewApi"})
  private void showDownloadProgressBar()
  {
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        View localView = LayoutInflater.from(TravelArticleFragment.this.context).inflate(2130903173, null);
        TravelArticleFragment.this.progressBar = ((ProgressBar)localView.findViewById(2131493286));
        TravelArticleFragment.this.tvProgress = ((TextView)localView.findViewById(2131493279));
        if (TravelArticleFragment.this.dialogDownload == null)
        {
          TravelArticleFragment.this.dialogDownload = new Dialog(TravelArticleFragment.this.context);
          TravelArticleFragment.this.dialogDownload.getWindow().setBackgroundDrawable(new ColorDrawable(0));
          TravelArticleFragment.this.dialogDownload.requestWindowFeature(1);
          TravelArticleFragment.this.dialogDownload.setCancelable(false);
        }
        TravelArticleFragment.this.dialogDownload.setContentView(localView, new ViewGroup.LayoutParams(700, -2));
        TravelArticleFragment.this.dialogDownload.getWindow().setGravity(17);
        TravelArticleFragment.this.progressBar.setMax(100);
        TravelArticleFragment.this.progressBar.setProgress(0);
        TravelArticleFragment.this.tvProgress.setText("0 %");
        TravelArticleFragment.this.dialogDownload.show();
      }
    });
  }
  
  public void changeAppVersion()
  {
    Upgrade localUpgrade = new Upgrade();
    localUpgrade.setId(1);
    try
    {
      String str = new Sha1Hex().makeSHA1Hash("ept" + this.guide.getinappcode() + "dbp");
      System.err.print(str + " " + this.guide.getid());
      localUpgrade.setVersion(str);
      Utils.setAPP_VERSION(true);
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
  }
  
  public void downloadforWalk()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject = TravelArticleFragment.this.guide.getskmfile();
        int i = TravelArticleFragment.this.guide.getid();
        final String str = "SKM" + String.valueOf(i);
        TravelArticleFragment.this.initializeLibrary(TravelArticleFragment.this.getActivity());
        SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
        int j = 0;
        i = 0;
        for (;;)
        {
          if (i >= arrayOfSKPackage.length) {}
          for (i = j;; i = 1)
          {
            if (i != 0) {
              break label180;
            }
            TravelArticleFragment.this.showDownloadProgressBar();
            if (!TravelArticleFragment.this.downloadskm((String)localObject, str, TravelArticleFragment.this)) {
              break label158;
            }
            ((Activity)TravelArticleFragment.this.context).runOnUiThread(new Runnable()
            {
              public void run()
              {
                SKPackageManager.getInstance().addOfflinePackage(AppConstants.DATABASE_PATH + "Travelarticles/", str);
                TravelArticleFragment.this.changeAppVersion();
                Intent localIntent = new Intent(TravelArticleFragment.this.getActivity(), WkCreate.class);
                localIntent.putExtra("pageNo", false);
                localIntent.putExtra("isfrommaster", true);
                localIntent.putExtra("guide", TravelArticleFragment.this.guide);
                TravelArticleFragment.this.startActivity(localIntent);
              }
            });
            return;
            if (!str.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
              break;
            }
          }
          i += 1;
        }
        label158:
        ((Activity)TravelArticleFragment.this.context).runOnUiThread(new Runnable()
        {
          public void run()
          {
            Utils.showBasicOkDialog(null, "No Internet connection. Map is not available, please connect to the Internet to download the map", null, "Ok", TravelArticleFragment.this.getActivity());
          }
        });
        return;
        label180:
        localObject = new Intent(TravelArticleFragment.this.getActivity(), WkCreate.class);
        ((Intent)localObject).putExtra("pageNo", false);
        ((Intent)localObject).putExtra("isfrommaster", true);
        ((Intent)localObject).putExtra("guide", TravelArticleFragment.this.guide);
        TravelArticleFragment.this.startActivity((Intent)localObject);
      }
    }).start();
  }
  
  public boolean downloadskm(String paramString1, String paramString2, FileUtils.DownloadListner paramDownloadListner)
  {
    if ((new File(AppConstants.DATABASE_PATH, paramString2).exists()) || (!TextUtils.isEmpty(FileUtils.downloadSQLITE(paramString1, AppConstants.DATABASE_PATH, this.context, paramString2, paramDownloadListner)))) {
      return true;
    }
    if (paramDownloadListner != null) {
      paramDownloadListner.onError();
    }
    return false;
  }
  
  public View getUpgradebtn()
  {
    return this.tvupgrade;
  }
  
  public void hideLoader()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (TravelArticleFragment.this._dialog.isShowing()) {
          TravelArticleFragment.this._dialog.dismiss();
        }
      }
    }, 100L);
  }
  
  public boolean initializeLibrary(Context paramContext)
  {
    SKLogging.enableLogs(true);
    this.mapResourcesDir = new PreferenceUtils(paramContext).getmapSettingsPath(PreferenceUtils.mapSettingsPath, "");
    if (this.mapResourcesDir.equals("")) {
      this.mapResourcesDir = (this.mapResourcesDir + File.separator + "PreinstalledMaps" + File.separator + "v1" + File.separator + "20150413" + File.separator + "package");
    }
    SKMapsInitSettings localSKMapsInitSettings = new SKMapsInitSettings();
    localSKMapsInitSettings.setMapResourcesPaths(this.mapResourcesDir, new SKMapViewStyle(this.mapResourcesDir + "daystyle/", "daystyle.json"));
    SKAdvisorSettings localSKAdvisorSettings = localSKMapsInitSettings.getAdvisorSettings();
    localSKAdvisorSettings.setAdvisorConfigPath(this.mapResourcesDir + "/Advisor");
    localSKAdvisorSettings.setResourcePath(this.mapResourcesDir + "/Advisor/Languages");
    localSKAdvisorSettings.setLanguage(SKAdvisorSettings.SKAdvisorLanguage.LANGUAGE_EN);
    localSKAdvisorSettings.setAdvisorVoice("en");
    localSKMapsInitSettings.setAdvisorSettings(localSKAdvisorSettings);
    localSKMapsInitSettings.setPreinstalledMapsPath(this.mapResourcesDir + "/PreinstalledMaps");
    localSKMapsInitSettings.setConnectivityMode(2);
    localSKMapsInitSettings.setMapDetailLevel("full/");
    try
    {
      SKMaps.getInstance().initializeSKMaps(paramContext, localSKMapsInitSettings);
      System.out.println("");
      return true;
    }
    catch (SKDeveloperKeyException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public boolean isUpgraded()
  {
    this.guide.getskmfile();
    int i = this.guide.getid();
    String str = "SKM" + String.valueOf(i);
    initializeLibrary(getActivity());
    SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
    i = 0;
    for (;;)
    {
      if (i >= arrayOfSKPackage.length) {
        return false;
      }
      if (str.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
        return true;
      }
      i += 1;
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == 1)) {
      this.tvupgrade.setText("Map");
    }
  }
  
  public void onComplete()
  {
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!((Activity)TravelArticleFragment.this.context).isFinishing()) && (TravelArticleFragment.this.dialogDownload != null) && (TravelArticleFragment.this.dialogDownload.isShowing()))
          {
            TravelArticleFragment.this.dialogDownload.dismiss();
            KeyValue localKeyValue = new KeyValue(TravelArticleFragment.this.guide.getid(), "true");
            SharedPrefUtils.setValue(TravelArticleFragment.this.getActivity(), SharedPrefUtils.TRAVEL_ARTICLE_PROMO, localKeyValue);
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903231, paramViewGroup, false);
    if (this.context == null) {
      this.context = paramLayoutInflater.getContext();
    }
    AppConstants.DATABASE_PATH = this.context.getFilesDir().toString() + "/";
    this.tvwalk = ((TextView)paramLayoutInflater.findViewById(2131494019));
    this.tvlisten = ((TextView)paramLayoutInflater.findViewById(2131493899));
    this.tvupgrade = ((TextView)paramLayoutInflater.findViewById(2131494018));
    this.tvlike = ((TextView)paramLayoutInflater.findViewById(2131494017));
    this.tvupgrade.setText("Upgrade");
    if (this.guide != null)
    {
      if (SharedPrefUtils.getKeyValue(getActivity(), SharedPrefUtils.TRAVEL_ARTICLE_PROMO, this.guide.getid(), "").equalsIgnoreCase("true"))
      {
        this.tvupgrade.setText("Map");
        this.sights = this.guide.sights;
      }
    }
    else
    {
      if (this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 1) {
        this.tvupgrade.setText("Map");
      }
      this.sessionManager = new SessionManager(getActivity());
      if ((this.sights == null) || (this.sights.size() != 0)) {
        break label448;
      }
      this.tvwalk.setVisibility(8);
      this.tvupgrade.setVisibility(8);
      label268:
      this.tvlisten.setTypeface(AppConstants.Roboto_Medium);
      this.tvupgrade.setTypeface(AppConstants.Roboto_Medium);
      this.tvlike.setTypeface(AppConstants.Roboto_Medium);
      this.tvwalk.setTypeface(AppConstants.Roboto_Medium);
      setNormalColors();
      if (Build.VERSION.SDK_INT < 21) {
        this.tvlisten.setEnabled(false);
      }
      if (this.guide != null)
      {
        if (!SharedPrefUtils.getKeyValue(this.context, AppConstants.PrefNameMaster, this.guide.getgdid() + "_liked").equals("true")) {
          break label467;
        }
        this.tvlike.setOnClickListener(null);
      }
    }
    for (;;)
    {
      this.tvwalk.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((TravelArticleFragment.this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 0) && (!TravelArticleFragment.this.isUpgraded()))
          {
            TravelArticleFragment.this.showCustomTourSaveDailog(TravelArticleFragment.this.getActivity(), "", "You can select some or all of the sights mentioned in this article to create self-guided walk.This function is available only in the upgraded version this article", "Upgrade", "Cancel", null);
            return;
          }
          if ((TravelArticleFragment.this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 1) && (!TravelArticleFragment.this.isUpgraded()))
          {
            TravelArticleFragment.this.downloadforWalk();
            return;
          }
          TravelArticleFragment.this.tvwalk.setTextColor(TravelArticleFragment.this.getActivity().getResources().getColor(2131296303));
          paramAnonymousView = new Intent(TravelArticleFragment.this.getActivity(), WkCreate.class);
          paramAnonymousView.putExtra("pageNo", false);
          paramAnonymousView.putExtra("isfrommaster", true);
          paramAnonymousView.putExtra("guide", TravelArticleFragment.this.guide);
          TravelArticleFragment.this.startActivity(paramAnonymousView);
        }
      });
      this.tvupgrade.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (TravelArticleFragment.this.tvupgrade.getText().toString().equalsIgnoreCase("map"))
          {
            if (DatabaseManager.getInstance().islite(TravelArticleFragment.this.guide.getid()))
            {
              paramAnonymousView = new Intent(TravelArticleFragment.this.getActivity(), MapCreate.class);
              paramAnonymousView.putExtra("pageNo", false);
              paramAnonymousView.putExtra("isfrommaster", true);
              paramAnonymousView.putExtra("guide", TravelArticleFragment.this.guide);
              paramAnonymousView.putExtra("guidesdo", TravelArticleFragment.this.guidsDo);
              TravelArticleFragment.this.startActivity(paramAnonymousView);
              return;
            }
            new Thread(new Runnable()
            {
              public void run()
              {
                Object localObject = TravelArticleFragment.this.guide.getskmfile();
                int i = TravelArticleFragment.this.guide.getid();
                final String str = "SKM" + String.valueOf(i);
                TravelArticleFragment.this.initializeLibrary(TravelArticleFragment.this.getActivity());
                SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
                int j = 0;
                i = 0;
                for (;;)
                {
                  if (i >= arrayOfSKPackage.length) {}
                  for (i = j;; i = 1)
                  {
                    if (i != 0) {
                      break label207;
                    }
                    TravelArticleFragment.this.showDownloadProgressBar();
                    if (!TravelArticleFragment.this.downloadskm((String)localObject, str, TravelArticleFragment.this)) {
                      break label182;
                    }
                    ((Activity)TravelArticleFragment.this.context).runOnUiThread(new Runnable()
                    {
                      public void run()
                      {
                        SKPackageManager.getInstance().addOfflinePackage(AppConstants.DATABASE_PATH + "Travelarticles/", str);
                        TravelArticleFragment.this.changeAppVersion();
                        SKPackageManager.getInstance().getInstalledPackages();
                        Intent localIntent = new Intent(TravelArticleFragment.this.getActivity(), MapCreate.class);
                        localIntent.putExtra("pageNo", false);
                        localIntent.putExtra("isfrommaster", true);
                        localIntent.putExtra("guide", TravelArticleFragment.this.guide);
                        localIntent.putExtra("guidesdo", TravelArticleFragment.this.guidsDo);
                        TravelArticleFragment.this.startActivity(localIntent);
                      }
                    });
                    return;
                    if (!str.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
                      break;
                    }
                  }
                  i += 1;
                }
                label182:
                ((Activity)TravelArticleFragment.this.context).runOnUiThread(new Runnable()
                {
                  public void run()
                  {
                    Utils.showBasicOkDialog(null, "No Internet connection. Map is not available, please connect to the Internet to download the map", null, "Ok", TravelArticleFragment.this.getActivity());
                  }
                });
                return;
                label207:
                localObject = new Intent(TravelArticleFragment.this.getActivity(), MapCreate.class);
                ((Intent)localObject).putExtra("pageNo", false);
                ((Intent)localObject).putExtra("isfrommaster", true);
                ((Intent)localObject).putExtra("guide", TravelArticleFragment.this.guide);
                ((Intent)localObject).putExtra("guidesdo", TravelArticleFragment.this.guidsDo);
                TravelArticleFragment.this.startActivity((Intent)localObject);
              }
            }).start();
            return;
          }
          paramAnonymousView = new Intent(TravelArticleFragment.this.getActivity(), UpgradActivity.class);
          paramAnonymousView.putExtra("guide", TravelArticleFragment.this.guide);
          TravelArticleFragment.this.startActivityForResult(paramAnonymousView, 1);
        }
      });
      this.tvlisten.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (AppConstants.isDone)
          {
            paramAnonymousView = new ArticlelistenFragment2(TravelArticleFragment.this.context, TravelArticleFragment.this.guide.gethtml());
            FragmentTransaction localFragmentTransaction = TravelArticleFragment.this.getFragmentManager().beginTransaction();
            localFragmentTransaction.add(2131492890, paramAnonymousView);
            localFragmentTransaction.addToBackStack(null);
            localFragmentTransaction.commit();
            return;
          }
          ((GetGuideDataActivity)TravelArticleFragment.this.getActivity()).loadfiles();
        }
      });
      return paramLayoutInflater;
      this.tvupgrade.setText("Upgrade");
      break;
      label448:
      this.tvwalk.setVisibility(0);
      this.tvupgrade.setVisibility(0);
      break label268;
      label467:
      this.tvlike.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          TravelArticleFragment.this.showcustomdialog("Click  'Yes'  to recommend this travel article", 2130903108);
        }
      });
    }
  }
  
  public void onError()
  {
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!((Activity)TravelArticleFragment.this.context).isFinishing()) && (TravelArticleFragment.this.dialogDownload != null) && (TravelArticleFragment.this.dialogDownload.isShowing())) {
            TravelArticleFragment.this.dialogDownload.dismiss();
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public void onProgrss(final int paramInt)
  {
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (TravelArticleFragment.this.dialogDownload != null)
        {
          TravelArticleFragment.this.progressBar.setProgress(paramInt);
          TravelArticleFragment.this.tvProgress.setText(paramInt + " %");
        }
      }
    });
  }
  
  public void onResume()
  {
    Log.e("DEBUG", "onResume of LoginFragment");
    super.onResume();
  }
  
  public <T> void processResponse(String paramString, int paramInt)
  {
    if (paramInt == 1) {
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.baseDo = ((BaseDo)new Gson().fromJson(paramString, BaseDo.class));
        if (!this.baseDo.getSid().equals("")) {
          break label57;
        }
      }
    }
    label57:
    do
    {
      do
      {
        return;
        this.sessionManager.persistSid(this.baseDo.getSid());
        WebManager.getInstance().like_Tour(getActivity(), this, this.guide.getid(), 98);
        return;
      } while ((paramInt != 98) || (paramString == null) || (paramString.equals("")));
      if (paramString.equals("!auth or expired sid")) {
        break;
      }
      this.baseDo = ((BaseDo)new Gson().fromJson(paramString, BaseDo.class));
    } while (!this.baseDo.getStatus().equals("1"));
    this.tvlike.setTextColor(getActivity().getResources().getColor(2131296305));
    this.tvlike.setOnClickListener(null);
    paramString = new KeyValue(this.guide.getgdid() + "_liked", "true");
    SharedPrefUtils.setValue(this.context, AppConstants.PrefNameMaster, paramString);
    return;
    WebManager.getInstance().getauthenticate(getActivity(), this, 1, true);
  }
  
  public void setGuide(GuideDataDo paramGuideDataDo)
  {
    this.guide = paramGuideDataDo;
  }
  
  public void setNormalColors()
  {
    this.tvlike.setTextColor(getActivity().getResources().getColor(2131296258));
    this.tvlisten.setTextColor(getActivity().getResources().getColor(2131296258));
    this.tvwalk.setTextColor(getActivity().getResources().getColor(2131296258));
    this.tvupgrade.setTextColor(getActivity().getResources().getColor(2131296258));
  }
  
  public void showCustomTourSaveDailog(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramContext = new AlertDialog.Builder(paramContext, 3);
    paramContext.setTitle(paramString1).setMessage(paramString2).setCancelable(false).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        paramAnonymousDialogInterface = new Intent(TravelArticleFragment.this.getActivity(), UpgradActivity.class);
        paramAnonymousDialogInterface.putExtra("guide", TravelArticleFragment.this.guide);
        TravelArticleFragment.this.startActivityForResult(paramAnonymousDialogInterface, 1);
      }
    }).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    paramContext.create().show();
  }
  
  public void showUpgradeCustomdialog(String paramString, int paramInt)
  {
    this.updialog = new ShowUpgradeCustomDialog(getActivity(), paramString, paramInt, this.guide.getid());
    this.updialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    if (paramInt == 2130903110)
    {
      paramString = this.updialog.getWindow().getAttributes();
      paramString.gravity = 48;
      paramString.width = -1;
      paramString.flags &= 0xFFFFFFFD;
      this.updialog.getWindow().setAttributes(paramString);
      this.updialog.getWindow().getAttributes().windowAnimations = 2131230732;
    }
    try
    {
      if (!this.updialog.isShowing()) {
        this.updialog.show();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void showcustomdialog(String paramString, int paramInt)
  {
    this.dialog = new CustomDialog(getActivity(), this, paramString, paramInt, this.guide.getid());
    this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    if (paramInt == 2130903110)
    {
      paramString = this.dialog.getWindow().getAttributes();
      paramString.gravity = 48;
      paramString.width = -1;
      paramString.flags &= 0xFFFFFFFD;
      this.dialog.getWindow().setAttributes(paramString);
      this.dialog.getWindow().getAttributes().windowAnimations = 2131230732;
    }
    try
    {
      if (!this.dialog.isShowing()) {
        this.dialog.show();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public class Sha1Hex
  {
    public Sha1Hex() {}
    
    public String makeSHA1Hash(String paramString)
      throws NoSuchAlgorithmException
    {
      Object localObject = MessageDigest.getInstance("SHA1");
      ((MessageDigest)localObject).reset();
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      paramString = "";
      int i = 0;
      for (;;)
      {
        if (i >= localObject.length) {
          return paramString;
        }
        paramString = paramString + Integer.toString((localObject[i] & 0xFF) + 256, 16).substring(1);
        i += 1;
      }
    }
  }
  
  class ShowUpgradeCustomDialog
    extends Dialog
    implements View.OnClickListener
  {
    public Activity ac;
    public Button btncancel;
    public Button btnok;
    public Dialog d;
    public EditText etmessage;
    public int resourceid;
    public String str = "";
    public String strtile;
    public TextView textView;
    public int tourid;
    public TextView tvcancel;
    
    public ShowUpgradeCustomDialog(Activity paramActivity, String paramString, int paramInt1, int paramInt2)
    {
      super();
      this.ac = paramActivity;
      this.strtile = paramString;
      this.resourceid = paramInt1;
      this.tourid = paramInt2;
    }
    
    public void delay(int paramInt)
    {
      new Timer().scheduleAtFixedRate(new TimerTask()
      {
        public void run()
        {
          TravelArticleFragment.ShowUpgradeCustomDialog.this.dismiss();
        }
      }, paramInt, 2000L);
    }
    
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default: 
        dismiss();
      }
      for (;;)
      {
        dismiss();
        return;
        paramView = new Intent(TravelArticleFragment.this.getActivity(), UpgradActivity.class);
        paramView.putExtra("inappcode", TravelArticleFragment.this.guide.getinappcode());
        TravelArticleFragment.this.startActivity(paramView);
        continue;
        dismiss();
        continue;
        dismiss();
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      requestWindowFeature(1);
      if ((this.resourceid == 2130903110) || (this.resourceid == 2130903169))
      {
        paramBundle = ((LayoutInflater)this.ac.getSystemService("layout_inflater")).inflate(2130903110, null);
        paramBundle.setMinimumWidth(AppConstants.width);
        setContentView(paramBundle);
        delay(6000);
      }
      for (;;)
      {
        this.textView = ((TextView)findViewById(2131493233));
        this.textView.setText(this.strtile);
        this.textView.setTypeface(AppConstants.Roboto_Bold);
        this.textView.setOnClickListener(this);
        this.btnok.setOnClickListener(this);
        return;
        if (this.resourceid == 2130903109)
        {
          setContentView(this.resourceid);
          this.etmessage = ((EditText)findViewById(2131493236));
        }
        else if (this.resourceid == 2130903108)
        {
          setContentView(2130903108);
          this.btnok = ((Button)findViewById(2131493235));
          this.btnok.setText("Upgrade");
          this.btncancel = ((Button)findViewById(2131493234));
          this.btncancel.setOnClickListener(this);
        }
      }
    }
  }
}
