package mbinc12.mb32b;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import api;
import aru;
import arx;
import asa;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import mbinc12.mb32b.classes.MyFbLoginButton;
import mbinc12.mb32b.notifications.GcmRegistrationService;
import mbinc12.mb32b.services.GetMyPageAssetsService;
import mbinc12.mb32b.utils.MixerBoxUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MixerBox
  extends ActionBarActivity
{
  static Bundle c;
  static Context d;
  static int f;
  static boolean g;
  static HashMap<String, Integer> h;
  AtomicInteger a = new AtomicInteger();
  String b = "281866154540";
  boolean e;
  public MyFbLoginButton i;
  private int j;
  private boolean k;
  private UiLifecycleHelper l;
  private Session.StatusCallback m = new Session.StatusCallback()
  {
    public final void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
    {
      MixerBox.a(paramAnonymousSessionState);
    }
  };
  
  public MixerBox() {}
  
  /* Error */
  private void a(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: iload_1
    //   5: iconst_1
    //   6: if_icmpne +13 -> 19
    //   9: aload_0
    //   10: aload_0
    //   11: getfield 164	mbinc12/mb32b/MixerBox:j	I
    //   14: iconst_1
    //   15: iadd
    //   16: putfield 164	mbinc12/mb32b/MixerBox:j	I
    //   19: aload_0
    //   20: getfield 164	mbinc12/mb32b/MixerBox:j	I
    //   23: ifle +33 -> 56
    //   26: getstatic 169	android/os/Build$VERSION:SDK_INT	I
    //   29: bipush 23
    //   31: if_icmplt +28 -> 59
    //   34: aload_0
    //   35: invokestatic 175	android/provider/Settings:canDrawOverlays	(Landroid/content/Context;)Z
    //   38: istore_2
    //   39: iload_2
    //   40: ifeq +33 -> 73
    //   43: new 8	mbinc12/mb32b/MixerBox$a
    //   46: dup
    //   47: invokespecial 176	mbinc12/mb32b/MixerBox$a:<init>	()V
    //   50: iconst_1
    //   51: lconst_0
    //   52: invokevirtual 180	mbinc12/mb32b/MixerBox$a:sendEmptyMessageDelayed	(IJ)Z
    //   55: pop
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: aload_0
    //   60: ldc -74
    //   62: invokevirtual 186	mbinc12/mb32b/MixerBox:checkCallingOrSelfPermission	(Ljava/lang/String;)I
    //   65: ifeq -26 -> 39
    //   68: iconst_0
    //   69: istore_2
    //   70: goto -31 -> 39
    //   73: aload_0
    //   74: iconst_1
    //   75: putfield 188	mbinc12/mb32b/MixerBox:k	Z
    //   78: ldc -66
    //   80: aconst_null
    //   81: invokestatic 195	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   84: new 197	org/json/JSONObject
    //   87: dup
    //   88: invokespecial 198	org/json/JSONObject:<init>	()V
    //   91: astore_3
    //   92: aload_3
    //   93: ldc -56
    //   95: ldc -54
    //   97: invokevirtual 205	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   100: pop
    //   101: aload_0
    //   102: ldc -49
    //   104: aload_3
    //   105: invokestatic 210	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   108: new 212	android/app/AlertDialog$Builder
    //   111: dup
    //   112: aload_0
    //   113: invokespecial 215	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   116: astore_3
    //   117: aload_3
    //   118: ldc -40
    //   120: invokevirtual 220	android/app/AlertDialog$Builder:setMessage	(I)Landroid/app/AlertDialog$Builder;
    //   123: iconst_0
    //   124: invokevirtual 224	android/app/AlertDialog$Builder:setCancelable	(Z)Landroid/app/AlertDialog$Builder;
    //   127: ldc -31
    //   129: new 227	aqt$7
    //   132: dup
    //   133: aload_0
    //   134: invokespecial 230	aqt$7:<init>	(Landroid/app/Activity;)V
    //   137: invokevirtual 234	android/app/AlertDialog$Builder:setPositiveButton	(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   140: ldc -21
    //   142: new 237	aqt$6
    //   145: dup
    //   146: aload_0
    //   147: invokespecial 238	aqt$6:<init>	(Landroid/app/Activity;)V
    //   150: invokevirtual 241	android/app/AlertDialog$Builder:setNegativeButton	(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   153: pop
    //   154: aload_3
    //   155: invokevirtual 245	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   158: invokevirtual 250	android/app/Dialog:show	()V
    //   161: goto -105 -> 56
    //   164: astore_3
    //   165: aload_0
    //   166: monitorexit
    //   167: aload_3
    //   168: athrow
    //   169: astore 4
    //   171: goto -70 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	MixerBox
    //   0	174	1	paramInt	int
    //   1	69	2	bool	boolean
    //   91	64	3	localObject1	Object
    //   164	4	3	localObject2	Object
    //   169	1	4	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   9	19	164	finally
    //   19	39	164	finally
    //   43	56	164	finally
    //   59	68	164	finally
    //   73	92	164	finally
    //   92	101	164	finally
    //   101	161	164	finally
    //   92	101	169	org/json/JSONException
  }
  
  private static void b(SessionState paramSessionState)
  {
    if (paramSessionState.isOpened()) {
      g = true;
    }
    while (!paramSessionState.isClosed()) {
      return;
    }
    g = false;
  }
  
  private boolean b()
  {
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    int n = localGoogleApiAvailability.isGooglePlayServicesAvailable(this);
    JSONObject localJSONObject;
    if (n != 0) {
      if (localGoogleApiAvailability.isUserResolvableError(n)) {
        localJSONObject = new JSONObject();
      }
    }
    try
    {
      localJSONObject.put("MsgType", "GooglePlayService");
      MixerBoxUtils.a(this, "PopAlert", localJSONObject);
      localGoogleApiAvailability.getErrorDialog(this, n, 9000).show();
      for (;;)
      {
        return false;
        finish();
      }
      return true;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.l.onActivityResult(paramInt1, paramInt2, paramIntent);
    Session.getActiveSession().onActivityResult(this, paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getIntent() != null) {
      onNewIntent(getIntent());
    }
    setContentView(2130903123);
    getSupportActionBar().h();
    this.l = new UiLifecycleHelper(this, this.m);
    this.l.onCreate(paramBundle);
    this.i = ((MyFbLoginButton)findViewById(2131624215));
    this.i.setReadPermissions(Arrays.asList(new String[] { "user_likes", "email", "read_stream", "user_friends" }));
    this.k = false;
    d = this;
    this.e = false;
    g = false;
    h = new HashMap();
    this.j = 0;
    if (b()) {
      startService(new Intent(this, GcmRegistrationService.class));
    }
    api.a(this, "DEFAULT", "font/Roboto-Light.ttf");
    api.a(this, "SANS_SERIF", "font/Roboto-Light.ttf");
    api.a(this, "SERIF", "font/Roboto-Light.ttf");
    int n = asa.l(this);
    f = n;
    Object localObject;
    if (n == -1)
    {
      paramBundle = getPackageManager().getInstalledApplications(128).iterator();
      if (!paramBundle.hasNext()) {
        break label569;
      }
      localObject = (ApplicationInfo)paramBundle.next();
      if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox")) {
        n = 1;
      }
    }
    for (;;)
    {
      label260:
      if (n != 0)
      {
        asa.e(this, true);
        label269:
        asa.k(this, false);
        asa.d(this, false);
        paramBundle = Calendar.getInstance();
        asa.a(this, paramBundle.getTimeInMillis());
        asa.b(this, paramBundle.getTimeInMillis());
        asa.a(this, 1);
        asa.J(this);
        if ((!asa.b(this)) || (asa.u(this) > aru.j())) {
          break label561;
        }
        asa.g(this, 2);
      }
      for (;;)
      {
        startService(new Intent(this, GetMyPageAssetsService.class));
        a(1);
        return;
        if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox2"))
        {
          n = 1;
          break label260;
        }
        if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox2b"))
        {
          n = 1;
          break label260;
        }
        if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox3"))
        {
          n = 1;
          break label260;
        }
        if (!((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox3b")) {
          break;
        }
        n = 1;
        break label260;
        asa.e(this, false);
        break label269;
        asa.b(this, Calendar.getInstance().getTimeInMillis());
        if (!asa.A(this))
        {
          paramBundle = Long.valueOf(Calendar.getInstance().getTimeInMillis());
          localObject = Long.valueOf(asa.u(this));
          if ((paramBundle.longValue() - ((Long)localObject).longValue() < 86400000L) || (paramBundle.longValue() - ((Long)localObject).longValue() > 172800000L) || (!arx.a(this))) {
            break label537;
          }
          paramBundle = asa.ai(this);
          asa.d(this, true);
          arx.a(arx.e(paramBundle), null);
        }
        for (;;)
        {
          asa.a(this, f + 1);
          break;
          label537:
          if (paramBundle.longValue() - ((Long)localObject).longValue() > 172800000L) {
            asa.d(this, true);
          }
        }
        label561:
        asa.g(this, 1);
      }
      label569:
      n = 0;
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.l.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Bundle localBundle = paramIntent.getExtras();
    c = localBundle;
    if (localBundle == null) {
      c = new Bundle();
    }
    if (paramIntent.getData() != null) {
      c.putString("data", paramIntent.getData().toString());
    }
  }
  
  public void onPause()
  {
    super.onPause();
    this.l.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    Session localSession = Session.getActiveSession();
    if ((localSession != null) && ((localSession.isOpened()) || (localSession.isClosed()))) {
      b(localSession.getState());
    }
    this.l.onResume();
    b();
    if (this.k)
    {
      this.k = false;
      a(3);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.l.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    super.onStart();
    FlurryAgent.onStartSession(this);
  }
  
  protected void onStop()
  {
    FlurryAgent.onEndSession(this);
    super.onStop();
  }
  
  static final class a
    extends Handler
  {
    a() {}
    
    public final void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default: 
        return;
      }
      if (MixerBox.f < 0)
      {
        MixerBox.a();
        if (MixerBox.c == null) {
          MixerBox.c = new Bundle();
        }
        MixerBox.c.putSerializable("fav_artists", MixerBox.h);
        ary.a = true;
        MixerBox.c.putBoolean("isLogIn", false);
        paramMessage = new Intent(MixerBox.d, MainPage.class);
        paramMessage.putExtras(MixerBox.c);
        MixerBox.d.startActivity(paramMessage);
        ((Activity)MixerBox.d).finish();
        return;
      }
      if (MixerBox.g)
      {
        if (MixerBox.c == null) {
          MixerBox.c = new Bundle();
        }
        asa.d(MixerBox.d, Session.getActiveSession().getAccessToken());
        MixerBoxUtils.a(MixerBox.d, MixerBox.c);
        return;
      }
      ary.a = true;
      if (MixerBox.c == null) {
        MixerBox.c = new Bundle();
      }
      MixerBox.c.putBoolean("isLogIn", false);
      paramMessage = new Intent();
      paramMessage.putExtras(MixerBox.c);
      paramMessage.setClass(MixerBox.d, MainPage.class);
      MixerBox.d.startActivity(paramMessage);
      ((Activity)MixerBox.d).finish();
    }
  }
}
