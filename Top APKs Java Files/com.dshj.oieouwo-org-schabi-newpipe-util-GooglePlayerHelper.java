package org.schabi.newpipe.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.List;
import org.schabi.newpipe.App;
import org.schabi.newpipe.feedback.bmob.FreeMusic;

public class GooglePlayerHelper
{
  public static void goToGooglePlay(Context paramContext, String paramString)
  {
    if (isAvailable(paramContext, "com.android.vending")) {
      try
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("market://details?id=");
        localStringBuilder.append(paramString);
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
        paramString.setPackage("com.android.vending");
        paramString.addFlags(268435456);
        paramContext.startActivity(paramString);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
  }
  
  private static boolean isAvailable(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static class updateDialogFragment
    extends DialogFragment
  {
    public updateDialogFragment() {}
    
    @SuppressLint({"SetJavaScriptEnabled"})
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      paramBundle = super.onCreateDialog(paramBundle);
      View localView = LayoutInflater.from(getActivity()).inflate(2131492907, null);
      TextView localTextView = (TextView)localView.findViewById(2131296332);
      Object localObject = (Button)localView.findViewById(2131296424);
      WebView localWebView = (WebView)localView.findViewById(2131296874);
      ((TextView)localView.findViewById(2131296828)).setText("Update");
      localWebView.getSettings().setJavaScriptEnabled(true);
      localWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
      if (Build.VERSION.SDK_INT >= 19) {
        localWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
      } else {
        localWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
      }
      localWebView.getSettings().setLoadWithOverviewMode(true);
      localWebView.loadDataWithBaseURL(null, App.getFreeMusic().getPromotionHtml(), "text/html", "UTF-8", "about:blank");
      localTextView.setText("Cancel");
      ((Button)localObject).setText("Go to Google Play");
      localTextView.setOnClickListener(new -..Lambda.GooglePlayerHelper.updateDialogFragment.FafURL41ZjiV3UKFVx0B_QvsZ3Q(this));
      ((Button)localObject).setOnClickListener(new -..Lambda.GooglePlayerHelper.updateDialogFragment.KWsbOSZCBEPesvsUmnui8xau3G8(this));
      if (App.getFreeMusic().isForcedUpdate())
      {
        localTextView.setVisibility(8);
        localObject = new RelativeLayout.LayoutParams(-1, 105);
        ((RelativeLayout.LayoutParams)localObject).addRule(13);
        localTextView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
      paramBundle.setContentView(localView);
      if (App.getFreeMusic().isForcedUpdate())
      {
        paramBundle.setCancelable(false);
        paramBundle.setCanceledOnTouchOutside(false);
        paramBundle.setOnKeyListener(-..Lambda.GooglePlayerHelper.updateDialogFragment.DqCO0hroiPPIp7RGJFq6fvD4Hoc.INSTANCE);
      }
      return paramBundle;
    }
  }
}
