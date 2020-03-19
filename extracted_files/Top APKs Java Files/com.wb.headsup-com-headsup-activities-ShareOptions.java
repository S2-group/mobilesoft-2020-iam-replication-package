package com.headsup.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.login.LoginManager;
import com.headsup.utils.a;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ShareOptions
  extends Activity
{
  private static final List<String> a = Arrays.asList(new String[] { "public_profile" });
  private static final List<String> b = Arrays.asList(new String[] { "publish_actions" });
  private View c;
  private CallbackManager d;
  private z e;
  private boolean f = false;
  private SoundPool g;
  private AudioManager h;
  private float i;
  private float j;
  private float k;
  private int l = 100;
  private int m;
  private String n;
  private String o;
  private String p;
  
  public ShareOptions() {}
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramActivity.startActivity(new Intent(paramActivity, ShareOptions.class).putExtra("share_options_extra_deck_title", paramString1).putExtra("share_options_extra_title", paramString2).putExtra("share_options_extra_words_list_string", paramString3));
  }
  
  public static byte[] a(String paramString)
  {
    paramString = new FileInputStream(paramString);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i1 = paramString.read(arrayOfByte);
      if (i1 == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i1);
    }
    return localByteArrayOutputStream.toByteArray();
  }
  
  private void b(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    for (int i1 = 1; i1 == 0; i1 = 0) {
      try
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
        return;
      }
    }
    String str = a.a();
    if (str == null)
    {
      a.b(this, "Could not share video. Please check if SD card is properly mounted and try again.");
      return;
    }
    paramString = new m(this, paramString);
    MediaScannerConnection.scanFile(this, new String[] { str }, new String[] { "video/mp4" }, paramString);
  }
  
  public final void a()
  {
    this.g.play(this.m, this.k, this.k, 1, 0, 1.0F);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.d.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (!this.f) {
      super.onBackPressed();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903065);
    this.n = getIntent().getStringExtra("share_options_extra_deck_title");
    this.p = getIntent().getStringExtra("share_options_extra_title");
    this.o = getIntent().getStringExtra("share_options_extra_words_list_string");
    this.d = CallbackManager.Factory.create();
    paramBundle = new l(this);
    LoginManager.getInstance().registerCallback(this.d, new o(this, paramBundle));
    this.e = new z(this);
    this.c = findViewById(2131165295);
    ((Button)findViewById(2131165296)).setOnClickListener(new q(this));
    ((Button)findViewById(2131165297)).setOnClickListener(new r(this));
    ((Button)findViewById(2131165298)).setOnClickListener(new s(this));
    ((Button)findViewById(2131165299)).setOnClickListener(new t(this));
    ((Button)findViewById(2131165300)).setOnClickListener(new v(this));
    ((Button)findViewById(2131165301)).setOnClickListener(new w(this));
    setVolumeControlStream(3);
    this.g = new SoundPool(10, 3, 0);
    this.h = ((AudioManager)getSystemService("audio"));
    this.i = this.h.getStreamVolume(3);
    this.j = this.h.getStreamMaxVolume(3);
    this.k = (this.i / this.j);
    this.m = this.g.load(this, 2131034138, 1);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.g.autoPause();
  }
}
