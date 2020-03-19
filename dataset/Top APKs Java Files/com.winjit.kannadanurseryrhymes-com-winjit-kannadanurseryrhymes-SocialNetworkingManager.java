package com.winjit.kannadanurseryrhymes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.FeedDialogBuilder;
import d;
import java.util.Iterator;
import java.util.List;

public class SocialNetworkingManager
{
  public static final String TAG = SocialNetworkingManager.class.getSimpleName();
  String PostMessage = "check out this cool app";
  Activity activity;
  SocialNetworkingManager.SocialNetworkType socialNetworkType;
  d twitterManager;
  
  public SocialNetworkingManager(Activity paramActivity, SocialNetworkingManager.SocialNetworkType paramSocialNetworkType)
  {
    this.activity = paramActivity;
    this.socialNetworkType = paramSocialNetworkType;
  }
  
  private void callMethod()
  {
    switch (this.socialNetworkType)
    {
    case GOOGLE_PLUS: 
    default: 
      return;
    case EMAIL: 
      doSocialNetworkinWithFacebook();
      return;
    case FACEBOOK: 
      doSocialNetworkinWithTwitter();
      return;
    case MESSAGING: 
      doSocialNetworkingWithEmail();
      return;
    case TWITTER: 
      doSocialNetworkingWithMessage();
      return;
    }
    doSocialNetworkinWithWhatsApp();
  }
  
  private void doSocialNetworkinWithTwitter()
  {
    if (this.PostMessage != null) {
      this.PostMessage = (this.activity.getString(2131492952) + " " + this.activity.getString(2131492953));
    }
    if (this.twitterManager == null) {
      this.twitterManager = new d(this.activity);
    }
    this.twitterManager.a(this.PostMessage);
    if (this.twitterManager.a())
    {
      ShowContinueDialog(this.twitterManager.c(), 1);
      return;
    }
    this.twitterManager.d();
  }
  
  private void doSocialNetworkingWithEmail()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "" });
    localIntent.putExtra("android.intent.extra.SUBJECT", this.activity.getString(2131492944));
    localIntent.setType("text/image");
    localIntent.putExtra("android.intent.extra.TEXT", this.activity.getString(2131492952) + " " + this.activity.getString(2131492953));
    this.activity.startActivity(localIntent);
  }
  
  private void doSocialNetworkingWithMessage()
  {
    String str = this.activity.getString(2131492952) + " " + this.activity.getString(2131492953);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("sms_body", str);
    localIntent.setType("vnd.android-dir/mms-sms");
    this.activity.startActivity(localIntent);
  }
  
  private void publishFeed(Bundle paramBundle)
  {
    ((WebDialog.FeedDialogBuilder)new WebDialog.FeedDialogBuilder(this.activity, Session.getActiveSession(), paramBundle).setOnCompleteListener(new SocialNetworkingManager.2(this))).build().show();
  }
  
  private void publishFeedDialog(String paramString)
  {
    paramString = new Bundle();
    paramString.putString("name", this.activity.getString(2131492954));
    paramString.putString("description", "Checkout amazing '10 Top Kannada Nursery Rhymes' app by Winjit Technologies Pvt Ltd at Google Play Store\nhttp://bit.ly/KannadaNRAndroid");
    paramString.putString("caption", "Winjit Technologies");
    paramString.putString("link", "http://bit.ly/KannadaNRAndroid");
    paramString.putString("picture", "http://www.winjitapps.com/WJ/KannadaNurseryRhymes/KanadaNR.jpg");
    publishFeed(paramString);
  }
  
  public void ShowContinueDialog(String paramString, int paramInt)
  {
    Object localObject2 = new AlertDialog.Builder(this.activity);
    Object localObject1 = LayoutInflater.from(this.activity).inflate(2130903083, null);
    ((AlertDialog.Builder)localObject2).setView((View)localObject1);
    ((AlertDialog.Builder)localObject2).setCancelable(true);
    localObject2 = ((AlertDialog.Builder)localObject2).create();
    Object localObject3 = (TextView)((View)localObject1).findViewById(2131427475);
    if (paramString != null) {
      ((TextView)localObject3).setText("Welcome : " + paramString);
    }
    paramString = (Button)((View)localObject1).findViewById(2131427476);
    localObject3 = (Button)((View)localObject1).findViewById(2131427477);
    localObject1 = (Button)((View)localObject1).findViewById(2131427478);
    paramString.setOnClickListener(new SocialNetworkingManager.3(this, (AlertDialog)localObject2, paramInt));
    ((Button)localObject3).setOnClickListener(new SocialNetworkingManager.4(this, (AlertDialog)localObject2, paramInt));
    ((Button)localObject1).setOnClickListener(new SocialNetworkingManager.5(this, (AlertDialog)localObject2));
    ((AlertDialog)localObject2).show();
  }
  
  public void doSocialNetworkinWithFacebook()
  {
    Session localSession = Session.getActiveSession();
    if ((localSession != null) && (localSession.isOpened()))
    {
      if (this.PostMessage != null)
      {
        publishFeedDialog(this.PostMessage);
        return;
      }
      publishFeedDialog(this.PostMessage);
      return;
    }
    Session.openActiveSession(this.activity, true, new SocialNetworkingManager.1(this));
  }
  
  public void doSocialNetworkinWithWhatsApp()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    if (this.PostMessage != null) {
      this.PostMessage = (this.activity.getString(2131492952) + " " + this.activity.getString(2131492953));
    }
    localIntent.setPackage("com.whatsapp");
    if (localIntent != null)
    {
      if (isPackageExisted("com.whatsapp"))
      {
        localIntent.putExtra("android.intent.extra.TEXT", this.PostMessage);
        this.activity.startActivity(Intent.createChooser(localIntent, "Share with"));
      }
    }
    else {
      return;
    }
    Toast.makeText(this.activity, "Whatsapp not installed", 0).show();
  }
  
  public void doSocialNetworking()
  {
    callMethod();
  }
  
  public boolean isPackageExisted(String paramString)
  {
    Iterator localIterator = this.activity.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    return true;
  }
}
