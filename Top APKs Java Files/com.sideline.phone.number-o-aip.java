package o;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.pinger.common.app.PingerApplication;
import com.pinger.common.store.Preferences.AuX;
import com.pinger.common.store.Preferences.iF;
import com.pinger.common.store.Preferences.ͺ.ˏ;
import com.pinger.textfree.call.activities.CallScreen;
import com.pinger.textfree.call.activities.ConversationActivity;
import com.pinger.textfree.call.activities.InboxActivity;
import com.pinger.textfree.call.app.TFApplication;
import com.pinger.textfree.call.app.TFService;
import com.pinger.textfree.call.beans.ContactAddressAndName;
import com.pinger.textfree.call.util.VoicemailController;
import com.pinger.textfree.call.voice.managers.VoiceManager;
import com.pinger.voice.PTAPICallBase;
import com.pinger.voice.PhoneAddress;
import com.pinger.voice.client.PTAPISoftphoneAsync;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;

public class aip
{
  public aip() {}
  
  public static class AuX
  {
    public AuX() {}
    
    public static Intent ˊ(Context paramContext, boolean paramBoolean1, String paramString1, int paramInt, String paramString2, String paramString3, long paramLong1, long paramLong2, String paramString4, String paramString5, boolean paramBoolean2)
    {
      return ˊ(paramContext, paramBoolean1, paramString1, paramInt, paramString2, paramString3, paramLong1, paramLong2, paramString4, paramString5, paramBoolean2, false, false, null);
    }
    
    public static Intent ˊ(Context paramContext, boolean paramBoolean1, String paramString1, int paramInt, String paramString2, String paramString3, long paramLong1, long paramLong2, String paramString4, String paramString5, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String paramString6)
    {
      if (!paramBoolean3)
      {
        boolean bool;
        if ((Ч.ˊ) && (!TextUtils.isEmpty(paramString1))) {
          bool = true;
        } else {
          bool = false;
        }
        ь.ˊ(bool, "address is invalid: " + paramString1);
        if ((Ч.ˊ) && (!TextUtils.isEmpty(paramString2))) {
          bool = true;
        } else {
          bool = false;
        }
        ь.ˊ(bool, "displayName is invalid: " + paramString2);
      }
      paramContext = new Intent(paramContext, ConversationActivity.class);
      PR.CONVERSATION.infest(paramContext);
      paramContext.putExtra("started_from_inbox_screen", paramBoolean1);
      paramContext.putExtra("address_E164", paramString1);
      paramContext.putExtra("address_type", paramInt);
      paramContext.putExtra("display_name_or_address", paramString2);
      paramContext.putExtra("contact_or_group_picture_url", paramString3);
      paramContext.putExtra("native_contact_id", paramLong1);
      paramContext.putExtra("thread_id", paramLong2);
      paramContext.putExtra("thread_draft_message", paramString4);
      paramContext.putExtra("thread_draft_image_path", paramString5);
      paramContext.putExtra("conversation_mode", 0);
      paramContext.putExtra("call_after_init", paramBoolean2);
      paramContext.putExtra("is_empty_conversation", paramBoolean3);
      paramContext.putExtra("open_sticker_category_list", paramBoolean4);
      paramContext.putExtra("sticker_search_term", paramString6);
      paramContext.setFlags(1);
      return paramContext;
    }
    
    public static Intent ˊ(Context paramContext, boolean paramBoolean1, String paramString1, long paramLong1, String paramString2, String paramString3, String paramString4, ArrayList<ContactAddressAndName> paramArrayList, long paramLong2, String paramString5, String paramString6, boolean paramBoolean2)
    {
      paramContext = new Intent(paramContext, ConversationActivity.class);
      PR.CONVERSATION.infest(paramContext);
      paramContext.putExtra("started_from_inbox_screen", paramBoolean1);
      paramContext.putExtra("address_E164", paramString1);
      paramContext.putExtra("group_id", paramLong1);
      paramContext.putExtra("display_name_or_address", paramString2);
      paramContext.putExtra("contact_or_group_picture_url", paramString3);
      paramContext.putExtra("group_members", paramString4);
      paramContext.putParcelableArrayListExtra("group_member_list", paramArrayList);
      paramContext.putExtra("thread_id", paramLong2);
      paramContext.putExtra("thread_draft_message", paramString5);
      paramContext.putExtra("thread_draft_image_path", paramString6);
      paramContext.putExtra("group_active", paramBoolean2);
      paramContext.putExtra("conversation_mode", 2);
      paramContext.setFlags(1);
      return paramContext;
    }
    
    public static Intent ˊ(Context paramContext, String[] paramArrayOfString)
    {
      return ˊ(paramContext, paramArrayOfString, false);
    }
    
    public static Intent ˊ(Context paramContext, String[] paramArrayOfString, boolean paramBoolean)
    {
      int i;
      if (paramBoolean) {
        i = 2131231125;
      } else {
        i = 2131231749;
      }
      return ˊ(paramArrayOfString, paramContext.getString(i), ˎ(paramContext));
    }
    
    public static Intent ˊ(Context paramContext, String[] paramArrayOfString, boolean paramBoolean, String paramString)
    {
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.setType("message/rfc822");
      localIntent.setData(Uri.parse("mailto:"));
      localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      paramArrayOfString = aip.ˊ.ˊ();
      if (!TextUtils.isEmpty(paramArrayOfString)) {
        localIntent.putExtra("android.intent.extra.CC", new String[] { paramArrayOfString });
      }
      if (TextUtils.isEmpty(paramString))
      {
        int i;
        if (paramBoolean) {
          i = 2131231125;
        } else {
          i = 2131231749;
        }
        paramString = paramContext.getString(i);
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString);
      localIntent.putExtra("android.intent.extra.TEXT", ˎ(paramContext));
      return localIntent;
    }
    
    public static Intent ˊ(String[] paramArrayOfString)
    {
      return ˊ(paramArrayOfString, null, null);
    }
    
    public static Intent ˊ(String[] paramArrayOfString, String paramString1, String paramString2)
    {
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.setType("message/rfc822");
      localIntent.setData(Uri.parse("mailto:"));
      localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      paramArrayOfString = aip.ˊ.ˊ();
      if (!TextUtils.isEmpty(paramArrayOfString)) {
        localIntent.putExtra("android.intent.extra.CC", new String[] { paramArrayOfString });
      }
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
      return localIntent;
    }
    
    public static <T> T ˊ(int paramInt)
    {
      Object localObject1 = null;
      for (;;)
      {
        try
        {
          String str = PingerApplication.ˏ().getString(paramInt);
          localObject1 = str;
          PW.ˋ().info("initDynamicComponent with class: " + str);
          localObject1 = str;
          Object localObject2 = Class.forName(str).newInstance();
          localObject1 = str;
          if (Ч.ˊ)
          {
            localObject1 = str;
            if (localObject2.getClass().isAnnotationPresent(QX.class))
            {
              bool = true;
              localObject1 = str;
              ν.ˊ(bool, "Dynamic component must be annotated with DxIgnore: " + str);
              return localObject2;
            }
          }
        }
        catch (Exception localException)
        {
          PW.ˋ().ˊ(Level.SEVERE, localException);
          bool = Ч.ˊ;
          ν.ˊ(false, "Cannot find dynamic component: " + localObject1);
          return null;
        }
        boolean bool = false;
      }
    }
    
    private static String ˊ(Context paramContext, int paramInt)
    {
      return ˊ(paramContext, paramInt, null);
    }
    
    public static String ˊ(Context paramContext, int paramInt, String paramString)
    {
      Wc localWc = TFService.getTFInstance().getProfile();
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramInt <= 0) {
        paramInt = 2131231748;
      }
      localStringBuilder.append(paramContext.getString(paramInt));
      localStringBuilder.append(paramContext.getString(2131231200));
      if (!TextUtils.isEmpty(paramString)) {
        localStringBuilder.append(paramString);
      }
      String str = localWc.ـ();
      if (!TextUtils.isEmpty(localWc.י())) {
        paramString = localWc.י();
      } else {
        paramString = Preferences.ͺ.ˏ.ˊ();
      }
      localStringBuilder.append(paramContext.getString(2131231375, new Object[] { str, paramString, Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, TFApplication.ˍ().getPackageName(), TFApplication.ˍ().ʼ() }));
      return localStringBuilder.toString();
    }
    
    public static void ˊ()
    {
      Context localContext = TFApplication.ˍ().getApplicationContext();
      Intent localIntent = new Intent(localContext, InboxActivity.class);
      localIntent.addFlags(268435456);
      ˊ(localContext, localIntent);
      try
      {
        throw new Exception("Cannot start ConversationActivity as getArguments() returns null");
      }
      catch (Exception localException)
      {
        PW.ˋ().log(Level.INFO, "Cannot start ConversationActivity as getArguments() returns null");
        Crashlytics.logException(localException);
      }
    }
    
    public static void ˊ(Activity paramActivity)
    {
      PTAPISoftphoneAsync localPTAPISoftphoneAsync = VoiceManager.ˊ().ʹ();
      boolean bool;
      if ((Ч.ˊ) && (localPTAPISoftphoneAsync != null)) {
        bool = true;
      } else {
        bool = false;
      }
      ь.ˊ(bool, "client is null or inactive when trying to bind, not allowed");
      for (;;)
      {
        try
        {
          if (paramActivity.getVolumeControlStream() != localPTAPISoftphoneAsync.getInCallStream())
          {
            paramActivity.setVolumeControlStream(localPTAPISoftphoneAsync.getInCallStream());
            if ((!Ч.ˊ) || (paramActivity.getVolumeControlStream() != localPTAPISoftphoneAsync.getInCallStream())) {
              break label168;
            }
            bool = true;
            з.ˊ(bool, "volume streams are different after set");
            PW.ˋ().log(Level.INFO, "Call volume bound to ptapi call");
          }
          else
          {
            PW.ˋ().log(Level.INFO, "Call volume already bound to ptapi call");
          }
          return;
        }
        catch (Exception localException)
        {
          PW.ˋ().ˊ(Level.SEVERE, localException);
          paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
          PW.ˋ().log(Level.INFO, "Call volume bound to ringer");
          if ((Ч.ˊ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
            bool = true;
          } else {
            bool = false;
          }
          з.ˊ(bool, "volume streams are different after set");
          return;
        }
        label168:
        bool = false;
      }
    }
    
    public static void ˊ(Context paramContext)
    {
      ˊ(paramContext, false);
    }
    
    public static void ˊ(Context paramContext, Intent paramIntent)
    {
      paramIntent.putExtra("finish_all", true);
      paramContext.startActivity(paramIntent);
    }
    
    public static void ˊ(Context paramContext, Intent paramIntent, Class... paramVarArgs)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramVarArgs != null)
      {
        int i = 0;
        while (i < paramVarArgs.length)
        {
          if (i != 0) {
            localStringBuilder.append(",");
          }
          localStringBuilder.append(paramVarArgs[i].getName());
          i += 1;
        }
      }
      paramIntent.putExtra("finish_exclude_class", localStringBuilder.toString());
      paramIntent.putExtra("finish_all", true);
      paramContext.startActivity(paramIntent);
    }
    
    public static void ˊ(Context paramContext, Class paramClass)
    {
      ˊ(paramContext, new Intent(paramContext, paramClass));
    }
    
    public static void ˊ(Context paramContext, Class paramClass, PR paramPR)
    {
      paramClass = new Intent(paramContext, paramClass);
      paramPR.infest(paramClass);
      ˊ(paramContext, paramClass);
    }
    
    public static void ˊ(Context paramContext, String paramString)
    {
      boolean bool;
      if ((Ч.ˊ) && (!TextUtils.isEmpty(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˊ(bool, "Trying to start conversation with an empty phone number");
      if ((Ч.ˊ) && (aip.COn.ʽ(paramString))) {
        bool = true;
      } else {
        bool = false;
      }
      ν.ˊ(bool, "Trying to start conversation but the address is not a phone number");
      aip.aUX.ˊ(new aix(aip.ʿ.ˊ(paramContext, paramString), paramContext), new Boolean[0]);
    }
    
    public static void ˊ(Context paramContext, boolean paramBoolean)
    {
      ˊ(paramContext, paramBoolean, null, null);
    }
    
    public static void ˊ(Context paramContext, boolean paramBoolean, String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString1)) {
        paramString1 = ˋ();
      }
      paramContext.startActivity(Intent.createChooser(ˊ(paramContext, new String[] { paramString1 }, paramBoolean, paramString2), paramContext.getString(2131231776)));
    }
    
    private static void ˊ(FragmentActivity paramFragmentActivity, String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
    {
      boolean bool;
      if ((Ч.ˊ) && (!TextUtils.isEmpty(paramString1))) {
        bool = true;
      } else {
        bool = false;
      }
      ь.ˊ(bool, "calling number should be valid!");
      if (aip.COn.ᐝ(paramString1))
      {
        if (TFApplication.ˍ().getApplicationContext().getResources().getBoolean(2131558412)) {
          aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), ZH.ˊ(paramFragmentActivity, paramString1), null);
        } else {
          aip.ͺ.ˎ(paramString1, paramFragmentActivity);
        }
        ahu.ˊ().ˏ("Call emergency number");
        return;
      }
      if (aip.ι.ˏ())
      {
        afj.ˊ().ˊ("Make Call (attempt or connected)", "from", "conversation");
        Intent localIntent = new Intent(paramFragmentActivity, CallScreen.class);
        localIntent.putExtra("navigate_to_call_contact_address", new VM(paramString1, paramString1, (byte)1));
        if (paramBoolean) {
          PR.GOTO_CALL_THEN_CONVERSATION.infest(localIntent);
        } else {
          PR.GOTO_CALL_THEN_RETURN.infest(localIntent);
        }
        localIntent.putExtra("contact_address_address", paramString1);
        localIntent.putExtra("contact_address_name", paramString2);
        if (!aip.aUX.ˊ(new aiw(paramBundle, localIntent, paramFragmentActivity))) {
          paramFragmentActivity.startActivity(localIntent);
        }
        return;
      }
      aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity.getString(2131231282), null), null);
      afj.ˊ().ˊ("Make Call (attempt or connected)", "error or connected", "NO INTERNET", true);
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, String paramString1, String paramString2, View paramView, boolean paramBoolean)
    {
      aiD.if.ˊ("Daily outgoing calls");
      OS.ˊ("Last Call initiated").ˊ(new OZ[] { OS.ˋ.APPBOY }).ˊ();
      OS.ˊ("Call initiated").ˊ(new OZ[] { OS.ˋ.FB }).ˊ();
      if (!aip.COn.ʽ(paramString1))
      {
        aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity.getString(2131231116), null), "call_invalid_numbers");
        return;
      }
      if (aip.ʿ.ʼ(paramString1))
      {
        aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity.getString(2131231120, new Object[] { paramFragmentActivity.getString(2131231895) }), null), null);
        return;
      }
      Intent localIntent = new Intent(paramFragmentActivity, CallScreen.class);
      Bundle localBundle = new Bundle();
      aip.aUX.ˊ(new aiu(paramView, paramFragmentActivity, localBundle, localIntent));
      paramView = VoiceManager.ˊ().ˎ();
      if ((paramView != null) && (aip.ʿ.ˊ(paramString1, paramView.getPhoneAddress().getNumber())))
      {
        afj.ˊ().ˊ("Make Call (attempt or connected)", "from", "conversation");
        if (paramBoolean) {
          PR.GOTO_CALL_THEN_CONVERSATION.infest(localIntent);
        } else {
          PR.GOTO_CALL_THEN_RETURN.infest(localIntent);
        }
        localIntent.putExtra("contact_address_address", paramString1);
        localIntent.putExtra("contact_address_name", paramString2);
        localIntent.putExtra("call_id", paramView.getCallId());
        if (!aip.aUX.ˊ(new aiv(paramFragmentActivity, localIntent, localBundle))) {
          paramFragmentActivity.startActivity(localIntent);
        }
        return;
      }
      if ((Preferences.AuX.ᐝ()) && (VoiceManager.ˊ().ˎ() == null))
      {
        if (VoicemailController.ͺ().ˏ())
        {
          aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity.getString(2131231230), paramFragmentActivity.getString(2131231231)), null);
          return;
        }
        aip.ͺ.ˋ(paramString1, paramFragmentActivity);
        return;
      }
      if ((VoiceManager.ˊ().ˋ()) || (aip.ι.ˋ(paramFragmentActivity.getApplicationContext())))
      {
        aim.ˎ(paramFragmentActivity);
        return;
      }
      if (VoicemailController.ͺ().ˏ())
      {
        aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity.getString(2131231230), paramFragmentActivity.getString(2131231231)), null);
        return;
      }
      ˊ(paramFragmentActivity, paramString1, paramString2, localBundle, paramBoolean);
    }
    
    public static Intent ˋ(Context paramContext, String[] paramArrayOfString)
    {
      return ˊ(paramArrayOfString, paramContext.getString(2131231055), ˏ(paramContext));
    }
    
    public static String ˋ()
    {
      return "support@pinger.com";
    }
    
    public static void ˋ(Activity paramActivity)
    {
      try
      {
        paramActivity.setVolumeControlStream(2);
        PW.ˋ().log(Level.INFO, "Call volume bound to ptapi call");
        return;
      }
      catch (Exception localException)
      {
        PW.ˋ().ˊ(Level.SEVERE, localException);
        paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
        PW.ˋ().log(Level.INFO, "Call volume bound to ringer");
        boolean bool;
        if ((Ч.ˊ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
          bool = true;
        } else {
          bool = false;
        }
        з.ˊ(bool, "volume streams are different after set");
      }
    }
    
    public static void ˋ(Context paramContext)
    {
      paramContext.startActivity(Intent.createChooser(ˋ(paramContext, new String[] { ˋ() }), paramContext.getString(2131231776)));
    }
    
    private static void ˋ(Context paramContext, Intent paramIntent, int paramInt)
    {
      if ((paramContext != null) && ((paramContext instanceof Activity)))
      {
        paramContext = (Activity)paramContext;
        if (paramInt == -1) {
          paramContext.startActivity(paramIntent);
        } else {
          paramContext.startActivityForResult(paramIntent, paramInt);
        }
        return;
      }
      paramContext.startActivity(paramIntent);
    }
    
    private static String ˎ(Context paramContext)
    {
      return ˊ(paramContext, -1);
    }
    
    public static void ˎ(Activity paramActivity)
    {
      paramActivity.setVolumeControlStream(Integer.MIN_VALUE);
      PW.ˋ().log(Level.INFO, "Call volume bound to ringer");
      boolean bool;
      if ((Ч.ˊ) && (paramActivity.getVolumeControlStream() == Integer.MIN_VALUE)) {
        bool = true;
      } else {
        bool = false;
      }
      з.ˊ(bool, "volume streams are different after set");
    }
    
    private static String ˏ(Context paramContext)
    {
      return ˊ(paramContext, 2131231056) + " beta";
    }
  }
  
  public static class aUX
  {
    public static ArrayList<Character> ˊ;
    private static final byte[] ˋ = { 72, -12, 75, 106, -11, 12, -12, 5, 8, 7 };
    private static int ˎ = 169;
    private static int ˏ = 0;
    private static int ᐝ = 1;
    
    public aUX() {}
    
    /* Error */
    public static String ʻ(String paramString)
    {
      // Byte code:
      //   0: goto +177 -> 177
      //   3: aload_3
      //   4: astore 6
      //   6: aload 5
      //   8: astore_0
      //   9: aload_3
      //   10: astore 4
      //   12: iload_1
      //   13: lookupswitch	default:+27->40, 17:+33->46, 61:+392->405
      //   40: goto +16 -> 56
      //   43: astore_0
      //   44: aload_0
      //   45: athrow
      //   46: ldc 47
      //   48: astore_0
      //   49: aload 6
      //   51: astore 4
      //   53: goto +352 -> 405
      //   56: bipush 61
      //   58: istore_1
      //   59: goto -56 -> 3
      //   62: bipush 92
      //   64: istore_1
      //   65: goto +301 -> 366
      //   68: new 49	java/lang/StringBuilder
      //   71: dup
      //   72: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   75: ldc 52
      //   77: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   84: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   87: astore 4
      //   89: goto +437 -> 526
      //   92: aload_2
      //   93: astore 6
      //   95: aload_3
      //   96: astore_0
      //   97: aload_2
      //   98: astore 4
      //   100: iload_1
      //   101: tableswitch	default:+23->124, 0:+304->405, 1:+-55->46
      //   124: aload_3
      //   125: astore_0
      //   126: goto +153 -> 279
      //   129: iconst_1
      //   130: istore_1
      //   131: aload_2
      //   132: astore_3
      //   133: goto +9 -> 142
      //   136: bipush 83
      //   138: istore_1
      //   139: goto +227 -> 366
      //   142: aload 5
      //   144: astore_0
      //   145: aload_3
      //   146: astore_2
      //   147: iload_1
      //   148: tableswitch	default:+24->172, 0:+138->286, 1:+354->502
      //   172: aload_3
      //   173: astore_2
      //   174: goto +98 -> 272
      //   177: new 62	o/PW$ˊ
      //   180: dup
      //   181: ldc 64
      //   183: invokespecial 67	o/PW$ˊ:<init>	(Ljava/lang/String;)V
      //   186: astore 6
      //   188: aload 6
      //   190: ldc 69
      //   192: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   195: aconst_null
      //   196: astore 5
      //   198: aload_0
      //   199: ldc 52
      //   201: invokevirtual 77	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   204: ifne +6 -> 210
      //   207: goto -145 -> 62
      //   210: goto -74 -> 136
      //   213: astore_0
      //   214: aload_0
      //   215: athrow
      //   216: bipush 17
      //   218: istore_1
      //   219: goto -216 -> 3
      //   222: invokestatic 83	com/google/i18n/phonenumbers/PhoneNumberUtil:getInstance	()Lcom/google/i18n/phonenumbers/PhoneNumberUtil;
      //   225: astore_0
      //   226: aload 6
      //   228: ldc 85
      //   230: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   233: aload 4
      //   235: astore_3
      //   236: aload_0
      //   237: aload 4
      //   239: ldc 47
      //   241: invokevirtual 89	com/google/i18n/phonenumbers/PhoneNumberUtil:parse	(Ljava/lang/String;Ljava/lang/String;)Lo/wm$if;
      //   244: astore_0
      //   245: aload 4
      //   247: astore_3
      //   248: aload 6
      //   250: ldc 91
      //   252: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   255: aload 4
      //   257: astore_3
      //   258: invokestatic 83	com/google/i18n/phonenumbers/PhoneNumberUtil:getInstance	()Lcom/google/i18n/phonenumbers/PhoneNumberUtil;
      //   261: aload_0
      //   262: invokevirtual 95	com/google/i18n/phonenumbers/PhoneNumberUtil:getRegionCodeForNumber	(Lo/wm$if;)Ljava/lang/String;
      //   265: astore_0
      //   266: aload 4
      //   268: astore_2
      //   269: goto +233 -> 502
      //   272: iconst_0
      //   273: istore_1
      //   274: aload_2
      //   275: astore_3
      //   276: goto -134 -> 142
      //   279: iconst_0
      //   280: istore_1
      //   281: aload_0
      //   282: astore_3
      //   283: goto -191 -> 92
      //   286: aload 6
      //   288: ldc 97
      //   290: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   293: aload 5
      //   295: ifnonnull +6 -> 301
      //   298: goto -82 -> 216
      //   301: goto -245 -> 56
      //   304: aload 4
      //   306: astore_2
      //   307: iload_1
      //   308: lookupswitch	default:+28->336, 82:+-86->222, 99:+148->456
      //   336: goto +63 -> 399
      //   339: getstatic 20	o/aip$aUX:ˏ	I
      //   342: bipush 23
      //   344: iadd
      //   345: istore_1
      //   346: iload_1
      //   347: sipush 128
      //   350: irem
      //   351: putstatic 22	o/aip$aUX:ᐝ	I
      //   354: iload_1
      //   355: iconst_2
      //   356: irem
      //   357: ifne +6 -> 363
      //   360: goto -88 -> 272
      //   363: goto -234 -> 129
      //   366: aload_0
      //   367: astore_2
      //   368: iload_1
      //   369: lookupswitch	default:+27->396, 83:+87->456, 92:+-301->68
      //   396: goto -260 -> 136
      //   399: bipush 82
      //   401: istore_1
      //   402: goto -98 -> 304
      //   405: invokestatic 102	o/PW:ˋ	()Lo/PW;
      //   408: astore_2
      //   409: new 49	java/lang/StringBuilder
      //   412: dup
      //   413: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   416: astore_3
      //   417: aload_3
      //   418: ldc 104
      //   420: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   423: astore_3
      //   424: aload_3
      //   425: aload_0
      //   426: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   429: astore_3
      //   430: aload_3
      //   431: ldc 106
      //   433: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   436: astore_3
      //   437: aload_3
      //   438: aload 4
      //   440: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   443: astore_3
      //   444: aload_3
      //   445: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   448: astore_3
      //   449: aload_2
      //   450: aload_3
      //   451: invokevirtual 109	o/PW:info	(Ljava/lang/String;)V
      //   454: aload_0
      //   455: areturn
      //   456: invokestatic 83	com/google/i18n/phonenumbers/PhoneNumberUtil:getInstance	()Lcom/google/i18n/phonenumbers/PhoneNumberUtil;
      //   459: astore_0
      //   460: aload 6
      //   462: ldc 85
      //   464: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   467: aload_2
      //   468: astore_3
      //   469: aload_0
      //   470: aload_2
      //   471: ldc 47
      //   473: invokevirtual 89	com/google/i18n/phonenumbers/PhoneNumberUtil:parse	(Ljava/lang/String;Ljava/lang/String;)Lo/wm$if;
      //   476: astore_0
      //   477: aload_2
      //   478: astore_3
      //   479: aload 6
      //   481: ldc 91
      //   483: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   486: aload_2
      //   487: astore_3
      //   488: invokestatic 83	com/google/i18n/phonenumbers/PhoneNumberUtil:getInstance	()Lcom/google/i18n/phonenumbers/PhoneNumberUtil;
      //   491: aload_0
      //   492: invokevirtual 95	com/google/i18n/phonenumbers/PhoneNumberUtil:getRegionCodeForNumber	(Lo/wm$if;)Ljava/lang/String;
      //   495: astore_0
      //   496: aload_0
      //   497: astore 5
      //   499: goto -160 -> 339
      //   502: aload 6
      //   504: ldc 97
      //   506: invokevirtual 71	o/PW$ˊ:ˊ	(Ljava/lang/String;)V
      //   509: aload_0
      //   510: ifnonnull +6 -> 516
      //   513: goto +6 -> 519
      //   516: goto -237 -> 279
      //   519: iconst_1
      //   520: istore_1
      //   521: aload_0
      //   522: astore_3
      //   523: goto -431 -> 92
      //   526: getstatic 22	o/aip$aUX:ᐝ	I
      //   529: bipush 57
      //   531: iadd
      //   532: istore_1
      //   533: iload_1
      //   534: sipush 128
      //   537: irem
      //   538: putstatic 20	o/aip$aUX:ˏ	I
      //   541: iload_1
      //   542: iconst_2
      //   543: irem
      //   544: ifeq +6 -> 550
      //   547: goto -148 -> 399
      //   550: bipush 99
      //   552: istore_1
      //   553: goto -249 -> 304
      //   556: astore_0
      //   557: invokestatic 102	o/PW:ˋ	()Lo/PW;
      //   560: getstatic 115	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
      //   563: new 49	java/lang/StringBuilder
      //   566: dup
      //   567: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   570: ldc 117
      //   572: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   575: aload_3
      //   576: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   579: ldc 119
      //   581: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   584: aload_0
      //   585: invokevirtual 122	com/google/i18n/phonenumbers/NumberParseException:getMessage	()Ljava/lang/String;
      //   588: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   591: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   594: invokevirtual 126	o/PW:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
      //   597: aload 5
      //   599: astore_0
      //   600: aload_3
      //   601: astore_2
      //   602: goto -100 -> 502
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	605	0	paramString	String
      //   12	541	1	i	int
      //   92	510	2	localObject1	Object
      //   3	598	3	localObject2	Object
      //   10	429	4	localObject3	Object
      //   6	592	5	str	String
      //   4	499	6	localObject4	Object
      // Exception table:
      //   from	to	target	type
      //   409	417	43	java/lang/Exception
      //   417	424	43	java/lang/Exception
      //   424	430	43	java/lang/Exception
      //   430	437	43	java/lang/Exception
      //   437	444	43	java/lang/Exception
      //   444	449	43	java/lang/Exception
      //   449	454	43	java/lang/Exception
      //   405	409	213	java/lang/Exception
      //   236	245	556	com/google/i18n/phonenumbers/NumberParseException
      //   248	255	556	com/google/i18n/phonenumbers/NumberParseException
      //   258	266	556	com/google/i18n/phonenumbers/NumberParseException
      //   469	477	556	com/google/i18n/phonenumbers/NumberParseException
      //   479	486	556	com/google/i18n/phonenumbers/NumberParseException
      //   488	496	556	com/google/i18n/phonenumbers/NumberParseException
    }
    
    public static boolean ʻ()
    {
      return false;
    }
    
    /* Error */
    public static Pair<String, String> ʼ(String paramString)
    {
      // Byte code:
      //   0: goto +74 -> 74
      //   3: astore_0
      //   4: aload_0
      //   5: athrow
      //   6: getstatic 20	o/aip$aUX:ˏ	I
      //   9: bipush 49
      //   11: iadd
      //   12: istore_1
      //   13: iload_1
      //   14: sipush 128
      //   17: irem
      //   18: putstatic 22	o/aip$aUX:ᐝ	I
      //   21: iload_1
      //   22: iconst_2
      //   23: irem
      //   24: ifne +6 -> 30
      //   27: goto +6 -> 33
      //   30: goto +100 -> 130
      //   33: bipush 31
      //   35: istore_1
      //   36: goto +139 -> 175
      //   39: iload_1
      //   40: lookupswitch	default:+28->68, 46:+133->173, 64:+-34->6
      //   68: bipush 46
      //   70: istore_1
      //   71: goto -32 -> 39
      //   74: aload_0
      //   75: ldc -125
      //   77: invokevirtual 135	java/lang/String:indexOf	(Ljava/lang/String;)I
      //   80: istore_2
      //   81: iload_2
      //   82: iconst_m1
      //   83: if_icmpeq +6 -> 89
      //   86: goto +53 -> 139
      //   89: goto -21 -> 68
      //   92: aload_0
      //   93: iconst_0
      //   94: iload_2
      //   95: invokevirtual 139	java/lang/String:substring	(II)Ljava/lang/String;
      //   98: astore_3
      //   99: aload_0
      //   100: invokevirtual 143	java/lang/String:length	()I
      //   103: istore_1
      //   104: aload_0
      //   105: iload_2
      //   106: iconst_1
      //   107: iadd
      //   108: iload_1
      //   109: invokevirtual 139	java/lang/String:substring	(II)Ljava/lang/String;
      //   112: astore_0
      //   113: aload_0
      //   114: invokevirtual 146	java/lang/String:trim	()Ljava/lang/String;
      //   117: astore_0
      //   118: new 148	android/util/Pair
      //   121: dup
      //   122: aload_3
      //   123: aload_0
      //   124: invokespecial 151	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
      //   127: astore_0
      //   128: aload_0
      //   129: areturn
      //   130: bipush 76
      //   132: istore_1
      //   133: goto +42 -> 175
      //   136: astore_0
      //   137: aload_0
      //   138: athrow
      //   139: bipush 64
      //   141: istore_1
      //   142: goto -103 -> 39
      //   145: new 148	android/util/Pair
      //   148: dup
      //   149: aload_0
      //   150: iconst_0
      //   151: iload_2
      //   152: invokevirtual 139	java/lang/String:substring	(II)Ljava/lang/String;
      //   155: aload_0
      //   156: iload_2
      //   157: iconst_1
      //   158: iadd
      //   159: aload_0
      //   160: invokevirtual 143	java/lang/String:length	()I
      //   163: invokevirtual 139	java/lang/String:substring	(II)Ljava/lang/String;
      //   166: invokevirtual 146	java/lang/String:trim	()Ljava/lang/String;
      //   169: invokespecial 151	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
      //   172: areturn
      //   173: aconst_null
      //   174: areturn
      //   175: iload_1
      //   176: lookupswitch	default:+28->204, 31:+-31->145, 76:+-84->92
      //   204: goto -74 -> 130
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	207	0	paramString	String
      //   12	164	1	i	int
      //   80	79	2	j	int
      //   98	25	3	str	String
      // Exception table:
      //   from	to	target	type
      //   74	81	3	java/lang/Exception
      //   92	99	3	java/lang/Exception
      //   99	104	3	java/lang/Exception
      //   104	113	3	java/lang/Exception
      //   113	118	3	java/lang/Exception
      //   118	128	3	java/lang/Exception
      //   74	81	136	java/lang/Exception
    }
    
    /* Error */
    public static String ʼ()
    {
      // Byte code:
      //   0: goto +407 -> 407
      //   3: iload_0
      //   4: lookupswitch	default:+28->32, 14:+165->169, 68:+525->529
      //   32: goto +75 -> 107
      //   35: iconst_1
      //   36: istore_0
      //   37: goto +592 -> 629
      //   40: iconst_1
      //   41: istore_0
      //   42: goto +71 -> 113
      //   45: astore_3
      //   46: aload_3
      //   47: athrow
      //   48: bipush 59
      //   50: istore_0
      //   51: goto +672 -> 723
      //   54: iconst_1
      //   55: istore_1
      //   56: goto +330 -> 386
      //   59: iconst_1
      //   60: istore_1
      //   61: goto +325 -> 386
      //   64: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   67: astore_3
      //   68: aload_3
      //   69: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   72: astore_3
      //   73: aload_3
      //   74: invokevirtual 167	o/Wc:ˋ	()Ljava/lang/String;
      //   77: astore_3
      //   78: aload_3
      //   79: areturn
      //   80: getstatic 22	o/aip$aUX:ᐝ	I
      //   83: bipush 33
      //   85: iadd
      //   86: istore_0
      //   87: iload_0
      //   88: sipush 128
      //   91: irem
      //   92: putstatic 20	o/aip$aUX:ˏ	I
      //   95: iload_0
      //   96: iconst_2
      //   97: irem
      //   98: ifeq +6 -> 104
      //   101: goto +46 -> 147
      //   104: goto +555 -> 659
      //   107: bipush 14
      //   109: istore_0
      //   110: goto -107 -> 3
      //   113: iload_0
      //   114: tableswitch	default:+22->136, 0:+439->553, 1:+364->478
      //   136: goto -96 -> 40
      //   139: iconst_1
      //   140: istore_0
      //   141: goto +422 -> 563
      //   144: ldc -87
      //   146: areturn
      //   147: iconst_1
      //   148: istore_0
      //   149: goto +447 -> 596
      //   152: bipush 75
      //   154: istore_0
      //   155: goto +98 -> 253
      //   158: iconst_0
      //   159: istore_0
      //   160: goto +30 -> 190
      //   163: bipush 75
      //   165: istore_0
      //   166: goto +328 -> 494
      //   169: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   172: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   175: invokevirtual 171	o/Wc:ˎ	()Ljava/lang/String;
      //   178: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   181: ifne +6 -> 187
      //   184: goto +495 -> 679
      //   187: goto -48 -> 139
      //   190: iload_0
      //   191: tableswitch	default:+21->212, 0:+473->664, 1:+158->349
      //   212: goto +143 -> 355
      //   215: bipush 95
      //   217: istore_0
      //   218: goto +276 -> 494
      //   221: bipush 79
      //   223: istore_0
      //   224: goto +88 -> 312
      //   227: iconst_0
      //   228: istore_2
      //   229: goto +54 -> 283
      //   232: iconst_0
      //   233: ldc -77
      //   235: invokestatic 184	o/ь:ˊ	(ZLjava/lang/String;)V
      //   238: invokestatic 189	com/pinger/common/store/Preferences$AUx$if:ͺ	()Ljava/lang/String;
      //   241: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   244: ifne +6 -> 250
      //   247: goto +96 -> 343
      //   250: goto -29 -> 221
      //   253: iload_0
      //   254: lookupswitch	default:+26->280, 65:+106->360, 75:+-27->227
      //   280: goto +404 -> 684
      //   283: getstatic 22	o/aip$aUX:ᐝ	I
      //   286: istore_0
      //   287: iload_0
      //   288: bipush 123
      //   290: iadd
      //   291: istore_0
      //   292: iload_0
      //   293: sipush 128
      //   296: irem
      //   297: putstatic 20	o/aip$aUX:ˏ	I
      //   300: iload_0
      //   301: iconst_2
      //   302: irem
      //   303: ifeq +6 -> 309
      //   306: goto -271 -> 35
      //   309: goto +66 -> 375
      //   312: iload_0
      //   313: lookupswitch	default:+27->340, 52:+36->349, 79:+351->664
      //   340: goto -119 -> 221
      //   343: bipush 52
      //   345: istore_0
      //   346: goto -34 -> 312
      //   349: invokestatic 189	com/pinger/common/store/Preferences$AUx$if:ͺ	()Ljava/lang/String;
      //   352: astore_3
      //   353: aload_3
      //   354: areturn
      //   355: iconst_1
      //   356: istore_0
      //   357: goto -167 -> 190
      //   360: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   363: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   366: ifnull +6 -> 372
      //   369: goto -154 -> 215
      //   372: goto -209 -> 163
      //   375: iconst_0
      //   376: istore_0
      //   377: goto +252 -> 629
      //   380: bipush 68
      //   382: istore_0
      //   383: goto -380 -> 3
      //   386: iload_1
      //   387: ldc -77
      //   389: invokestatic 184	o/ь:ˊ	(ZLjava/lang/String;)V
      //   392: invokestatic 189	com/pinger/common/store/Preferences$AUx$if:ͺ	()Ljava/lang/String;
      //   395: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   398: ifne +6 -> 404
      //   401: goto -46 -> 355
      //   404: goto -246 -> 158
      //   407: getstatic 194	o/Ч:ˊ	Z
      //   410: ifeq +6 -> 416
      //   413: goto +271 -> 684
      //   416: goto -264 -> 152
      //   419: iload_0
      //   420: lookupswitch	default:+28->448, 67:+-276->144, 97:+31->451
      //   448: goto +75 -> 523
      //   451: getstatic 20	o/aip$aUX:ˏ	I
      //   454: bipush 91
      //   456: iadd
      //   457: istore_0
      //   458: iload_0
      //   459: sipush 128
      //   462: irem
      //   463: putstatic 22	o/aip$aUX:ᐝ	I
      //   466: iload_0
      //   467: iconst_2
      //   468: irem
      //   469: ifne +6 -> 475
      //   472: goto -365 -> 107
      //   475: goto -95 -> 380
      //   478: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   481: astore_3
      //   482: aload_3
      //   483: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   486: astore_3
      //   487: aload_3
      //   488: invokevirtual 171	o/Wc:ˎ	()Ljava/lang/String;
      //   491: astore_3
      //   492: aload_3
      //   493: areturn
      //   494: iload_0
      //   495: lookupswitch	default:+25->520, 75:+-268->227, 95:+-415->80
      //   520: goto -305 -> 215
      //   523: bipush 67
      //   525: istore_0
      //   526: goto -107 -> 419
      //   529: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   532: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   535: invokevirtual 171	o/Wc:ˎ	()Ljava/lang/String;
      //   538: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   541: ifne +6 -> 547
      //   544: goto -496 -> 48
      //   547: goto +76 -> 623
      //   550: astore_3
      //   551: aload_3
      //   552: athrow
      //   553: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   556: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   559: invokevirtual 171	o/Wc:ˎ	()Ljava/lang/String;
      //   562: areturn
      //   563: iload_0
      //   564: tableswitch	default:+24->588, 0:+-86->478, 1:+-500->64
      //   588: goto +91 -> 679
      //   591: iconst_0
      //   592: istore_0
      //   593: goto -480 -> 113
      //   596: iload_0
      //   597: tableswitch	default:+23->620, 0:+-538->59, 1:+-543->54
      //   620: goto +39 -> 659
      //   623: bipush 94
      //   625: istore_0
      //   626: goto +97 -> 723
      //   629: iload_2
      //   630: istore_1
      //   631: iload_0
      //   632: tableswitch	default:+24->656, 0:+-246->386, 1:+-400->232
      //   656: goto -621 -> 35
      //   659: iconst_0
      //   660: istore_0
      //   661: goto -65 -> 596
      //   664: invokestatic 159	com/pinger/textfree/call/app/TFService:getTFInstance	()Lcom/pinger/textfree/call/app/TFService;
      //   667: invokevirtual 163	com/pinger/textfree/call/app/TFService:getProfile	()Lo/Wc;
      //   670: ifnull +6 -> 676
      //   673: goto +44 -> 717
      //   676: goto -153 -> 523
      //   679: iconst_0
      //   680: istore_0
      //   681: goto -118 -> 563
      //   684: bipush 65
      //   686: istore_0
      //   687: goto -434 -> 253
      //   690: getstatic 20	o/aip$aUX:ˏ	I
      //   693: bipush 99
      //   695: iadd
      //   696: istore_0
      //   697: iload_0
      //   698: sipush 128
      //   701: irem
      //   702: putstatic 22	o/aip$aUX:ᐝ	I
      //   705: iload_0
      //   706: iconst_2
      //   707: irem
      //   708: ifne +6 -> 714
      //   711: goto -120 -> 591
      //   714: goto -674 -> 40
      //   717: bipush 97
      //   719: istore_0
      //   720: goto -301 -> 419
      //   723: iload_0
      //   724: lookupswitch	default:+28->752, 59:+-34->690, 94:+-660->64
      //   752: goto -704 -> 48
      // Local variable table:
      //   start	length	slot	name	signature
      //   3	721	0	i	int
      //   55	576	1	bool1	boolean
      //   228	402	2	bool2	boolean
      //   45	2	3	localException1	Exception
      //   67	426	3	localObject	Object
      //   550	2	3	localException2	Exception
      // Exception table:
      //   from	to	target	type
      //   73	78	45	java/lang/Exception
      //   349	353	45	java/lang/Exception
      //   482	487	45	java/lang/Exception
      //   487	492	45	java/lang/Exception
      //   64	68	550	java/lang/Exception
      //   68	73	550	java/lang/Exception
      //   73	78	550	java/lang/Exception
      //   283	287	550	java/lang/Exception
      //   292	300	550	java/lang/Exception
      //   478	482	550	java/lang/Exception
    }
    
    /* Error */
    public static String ʽ(String paramString)
    {
      // Byte code:
      //   0: invokestatic 200	o/aip$cOn:ˊ	()Landroid/util/DisplayMetrics;
      //   3: astore 5
      //   5: aload 5
      //   7: getfield 205	android/util/DisplayMetrics:widthPixels	I
      //   10: istore_3
      //   11: iload_3
      //   12: i2f
      //   13: fstore_1
      //   14: getstatic 210	o/aii:ˊ	F
      //   17: fstore_2
      //   18: fload_1
      //   19: fload_2
      //   20: fmul
      //   21: f2i
      //   22: istore_3
      //   23: invokestatic 200	o/aip$cOn:ˊ	()Landroid/util/DisplayMetrics;
      //   26: astore 5
      //   28: aload 5
      //   30: getfield 213	android/util/DisplayMetrics:heightPixels	I
      //   33: istore 4
      //   35: iload 4
      //   37: i2f
      //   38: fstore_1
      //   39: getstatic 215	o/aii:ˋ	F
      //   42: fstore_2
      //   43: fload_1
      //   44: fload_2
      //   45: fmul
      //   46: f2i
      //   47: istore 4
      //   49: aload_0
      //   50: iload_3
      //   51: iload 4
      //   53: invokestatic 218	o/aip$aUX:ˊ	(Ljava/lang/String;II)Ljava/lang/String;
      //   56: astore_0
      //   57: aload_0
      //   58: areturn
      //   59: astore_0
      //   60: aload_0
      //   61: athrow
      //   62: astore_0
      //   63: aload_0
      //   64: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	65	0	paramString	String
      //   13	31	1	f1	float
      //   17	28	2	f2	float
      //   10	41	3	i	int
      //   33	19	4	j	int
      //   3	26	5	localDisplayMetrics	android.util.DisplayMetrics
      // Exception table:
      //   from	to	target	type
      //   0	5	59	java/lang/Exception
      //   5	11	62	java/lang/Exception
      //   14	18	62	java/lang/Exception
      //   23	28	62	java/lang/Exception
      //   28	35	62	java/lang/Exception
      //   39	43	62	java/lang/Exception
      //   49	57	62	java/lang/Exception
    }
    
    /* Error */
    public static boolean ʾ(String paramString)
    {
      // Byte code:
      //   0: goto +175 -> 175
      //   3: iload_1
      //   4: tableswitch	default:+24->28, 0:+32->36, 1:+203->207
      //   28: goto +65 -> 93
      //   31: iconst_1
      //   32: istore_1
      //   33: goto +5 -> 38
      //   36: iconst_0
      //   37: ireturn
      //   38: iload_1
      //   39: tableswitch	default:+21->60, 0:+-3->36, 1:+24->63
      //   60: goto -29 -> 31
      //   63: iconst_1
      //   64: ireturn
      //   65: astore_0
      //   66: aload_0
      //   67: athrow
      //   68: iconst_0
      //   69: istore_1
      //   70: goto -32 -> 38
      //   73: aload_0
      //   74: ldc -35
      //   76: invokevirtual 225	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   79: arraylength
      //   80: iconst_2
      //   81: if_icmplt +6 -> 87
      //   84: goto -53 -> 31
      //   87: goto -19 -> 68
      //   90: astore_0
      //   91: aload_0
      //   92: athrow
      //   93: iconst_0
      //   94: istore_1
      //   95: goto -92 -> 3
      //   98: iconst_1
      //   99: istore_1
      //   100: goto -97 -> 3
      //   103: iconst_4
      //   104: istore_1
      //   105: goto +38 -> 143
      //   108: bipush 25
      //   110: istore_1
      //   111: iload_1
      //   112: lookupswitch	default:+28->140, 25:+78->190, 63:+-39->73
      //   140: goto -32 -> 108
      //   143: iload_1
      //   144: lookupswitch	default:+28->172, 4:+-81->63, 63:+-108->36
      //   172: goto -69 -> 103
      //   175: aload_0
      //   176: ldc -29
      //   178: invokevirtual 77	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   181: ifeq +6 -> 187
      //   184: goto -86 -> 98
      //   187: goto -94 -> 93
      //   190: aload_0
      //   191: ldc -35
      //   193: invokevirtual 225	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   196: arraylength
      //   197: iconst_2
      //   198: if_icmplt +6 -> 204
      //   201: goto -98 -> 103
      //   204: goto +30 -> 234
      //   207: getstatic 22	o/aip$aUX:ᐝ	I
      //   210: bipush 105
      //   212: iadd
      //   213: istore_1
      //   214: iload_1
      //   215: sipush 128
      //   218: irem
      //   219: putstatic 20	o/aip$aUX:ˏ	I
      //   222: iload_1
      //   223: iconst_2
      //   224: irem
      //   225: ifeq +6 -> 231
      //   228: goto -120 -> 108
      //   231: goto +9 -> 240
      //   234: bipush 63
      //   236: istore_1
      //   237: goto -94 -> 143
      //   240: bipush 63
      //   242: istore_1
      //   243: goto -132 -> 111
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	246	0	paramString	String
      //   3	240	1	i	int
      // Exception table:
      //   from	to	target	type
      //   214	222	65	java/lang/Exception
      //   207	214	90	java/lang/Exception
      //   214	222	90	java/lang/Exception
    }
    
    public static boolean ʿ(String paramString)
    {
      return paramString.toLowerCase().equals("unknown_number");
    }
    
    /* Error */
    public static String ˈ(String paramString)
    {
      // Byte code:
      //   0: goto +82 -> 82
      //   3: iload_2
      //   4: istore_1
      //   5: iload_3
      //   6: tableswitch	default:+22->28, 0:+419->425, 1:+226->232
      //   28: iload_2
      //   29: istore_1
      //   30: goto +262 -> 292
      //   33: iconst_0
      //   34: istore_1
      //   35: goto +349 -> 384
      //   38: iconst_1
      //   39: istore_1
      //   40: goto +344 -> 384
      //   43: getstatic 22	o/aip$aUX:ᐝ	I
      //   46: bipush 87
      //   48: iadd
      //   49: istore_1
      //   50: iload_1
      //   51: sipush 128
      //   54: irem
      //   55: putstatic 20	o/aip$aUX:ˏ	I
      //   58: iload_1
      //   59: iconst_2
      //   60: irem
      //   61: ifeq +6 -> 67
      //   64: goto +144 -> 208
      //   67: goto +220 -> 287
      //   70: iload_2
      //   71: iload 5
      //   73: if_icmpge +6 -> 79
      //   76: goto +223 -> 299
      //   79: goto +256 -> 335
      //   82: ldc -16
      //   84: invokestatic 245	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
      //   87: astore 6
      //   89: aload_0
      //   90: invokevirtual 249	java/lang/String:getBytes	()[B
      //   93: astore_0
      //   94: aload 6
      //   96: aload_0
      //   97: invokevirtual 253	java/security/MessageDigest:update	([B)V
      //   100: aload 6
      //   102: invokevirtual 256	java/security/MessageDigest:digest	()[B
      //   105: astore_0
      //   106: new 49	java/lang/StringBuilder
      //   109: dup
      //   110: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   113: astore 6
      //   115: aload_0
      //   116: arraylength
      //   117: istore 5
      //   119: iconst_0
      //   120: istore_3
      //   121: iconst_0
      //   122: istore 4
      //   124: goto +31 -> 155
      //   127: iload_2
      //   128: istore_1
      //   129: iload_3
      //   130: tableswitch	default:+22->152, 0:+295->425, 1:+102->232
      //   152: goto +183 -> 335
      //   155: getstatic 22	o/aip$aUX:ᐝ	I
      //   158: bipush 83
      //   160: iadd
      //   161: istore_1
      //   162: iload_1
      //   163: sipush 128
      //   166: irem
      //   167: putstatic 20	o/aip$aUX:ˏ	I
      //   170: iload_1
      //   171: iconst_2
      //   172: irem
      //   173: ifeq +6 -> 179
      //   176: goto +244 -> 420
      //   179: goto +236 -> 415
      //   182: iload_2
      //   183: istore_1
      //   184: iload_3
      //   185: tableswitch	default:+23->208, 0:+-115->70, 1:+298->483
      //   208: iconst_0
      //   209: istore_3
      //   210: goto -28 -> 182
      //   213: astore_0
      //   214: aload_0
      //   215: athrow
      //   216: astore_0
      //   217: aload_0
      //   218: athrow
      //   219: iconst_0
      //   220: istore_3
      //   221: iload_1
      //   222: istore_2
      //   223: goto -220 -> 3
      //   226: bipush 26
      //   228: istore_2
      //   229: goto +75 -> 304
      //   232: aload 6
      //   234: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   237: areturn
      //   238: aload_0
      //   239: iload_1
      //   240: baload
      //   241: istore_2
      //   242: iload_2
      //   243: sipush 255
      //   246: iand
      //   247: sipush 256
      //   250: iadd
      //   251: bipush 16
      //   253: invokestatic 260	java/lang/Integer:toString	(II)Ljava/lang/String;
      //   256: astore 7
      //   258: aload 7
      //   260: iconst_1
      //   261: invokevirtual 263	java/lang/String:substring	(I)Ljava/lang/String;
      //   264: astore 7
      //   266: aload 6
      //   268: aload 7
      //   270: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   273: pop
      //   274: iload_1
      //   275: iconst_1
      //   276: iadd
      //   277: istore_1
      //   278: goto +205 -> 483
      //   281: bipush 18
      //   283: istore_2
      //   284: goto +20 -> 304
      //   287: iconst_1
      //   288: istore_3
      //   289: goto -107 -> 182
      //   292: iconst_1
      //   293: istore_3
      //   294: iload_1
      //   295: istore_2
      //   296: goto -293 -> 3
      //   299: iconst_0
      //   300: istore_3
      //   301: goto -174 -> 127
      //   304: iload_1
      //   305: istore_3
      //   306: iload_2
      //   307: lookupswitch	default:+25->332, 18:+44->351, 26:+-69->238
      //   332: goto -51 -> 281
      //   335: iconst_1
      //   336: istore_3
      //   337: goto -210 -> 127
      //   340: iload 5
      //   342: ifge +6 -> 348
      //   345: goto -312 -> 33
      //   348: goto -310 -> 38
      //   351: aload 6
      //   353: aload_0
      //   354: iload_3
      //   355: baload
      //   356: sipush 255
      //   359: iand
      //   360: sipush 256
      //   363: iadd
      //   364: bipush 16
      //   366: invokestatic 260	java/lang/Integer:toString	(II)Ljava/lang/String;
      //   369: iconst_1
      //   370: invokevirtual 263	java/lang/String:substring	(I)Ljava/lang/String;
      //   373: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   376: pop
      //   377: iload_3
      //   378: iconst_1
      //   379: iadd
      //   380: istore_2
      //   381: goto -338 -> 43
      //   384: iload 4
      //   386: istore_3
      //   387: iload_1
      //   388: tableswitch	default:+24->412, 0:+-37->351, 1:+-156->232
      //   412: goto -379 -> 33
      //   415: iconst_0
      //   416: istore_2
      //   417: goto +37 -> 454
      //   420: iconst_1
      //   421: istore_2
      //   422: goto +32 -> 454
      //   425: getstatic 22	o/aip$aUX:ᐝ	I
      //   428: istore_2
      //   429: iload_2
      //   430: bipush 59
      //   432: iadd
      //   433: istore_2
      //   434: iload_2
      //   435: sipush 128
      //   438: irem
      //   439: putstatic 20	o/aip$aUX:ˏ	I
      //   442: iload_2
      //   443: iconst_2
      //   444: irem
      //   445: ifeq +6 -> 451
      //   448: goto -222 -> 226
      //   451: goto -170 -> 281
      //   454: iload_3
      //   455: istore_1
      //   456: iload_2
      //   457: tableswitch	default:+23->480, 0:+26->483, 1:+-117->340
      //   480: goto -65 -> 415
      //   483: iload_1
      //   484: iload 5
      //   486: if_icmpge +6 -> 492
      //   489: goto -270 -> 219
      //   492: goto -200 -> 292
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	495	0	paramString	String
      //   4	483	1	i	int
      //   3	454	2	j	int
      //   5	450	3	k	int
      //   122	263	4	m	int
      //   71	416	5	n	int
      //   87	265	6	localObject	Object
      //   256	13	7	str	String
      // Exception table:
      //   from	to	target	type
      //   43	50	213	java/lang/Exception
      //   50	58	213	java/lang/Exception
      //   82	89	213	java/lang/Exception
      //   89	94	213	java/lang/Exception
      //   94	100	213	java/lang/Exception
      //   100	106	213	java/lang/Exception
      //   106	115	213	java/lang/Exception
      //   115	119	213	java/lang/Exception
      //   425	429	213	java/lang/Exception
      //   434	442	213	java/lang/Exception
      //   89	94	216	java/lang/Exception
      //   242	258	216	java/lang/Exception
      //   258	266	216	java/lang/Exception
      //   266	274	216	java/lang/Exception
    }
    
    /* Error */
    public static File ˊ(String paramString)
    {
      // Byte code:
      //   0: goto +190 -> 190
      //   3: aload 6
      //   5: invokevirtual 269	java/io/File:delete	()Z
      //   8: pop
      //   9: goto +102 -> 111
      //   12: iload_1
      //   13: lookupswitch	default:+27->40, 55:+233->246, 71:+-10->3
      //   40: bipush 71
      //   42: istore_1
      //   43: goto -31 -> 12
      //   46: iload_1
      //   47: tableswitch	default:+21->68, 0:+64->111, 1:+32->79
      //   68: goto +6 -> 74
      //   71: astore_0
      //   72: aload_0
      //   73: athrow
      //   74: iconst_1
      //   75: istore_1
      //   76: goto -30 -> 46
      //   79: getstatic 22	o/aip$aUX:ᐝ	I
      //   82: bipush 59
      //   84: iadd
      //   85: istore_1
      //   86: iload_1
      //   87: sipush 128
      //   90: irem
      //   91: putstatic 20	o/aip$aUX:ˏ	I
      //   94: iload_1
      //   95: iconst_2
      //   96: irem
      //   97: ifeq +6 -> 103
      //   100: goto +140 -> 240
      //   103: goto -63 -> 40
      //   106: iconst_0
      //   107: istore_1
      //   108: goto -62 -> 46
      //   111: aload 6
      //   113: invokevirtual 272	java/io/File:createNewFile	()Z
      //   116: pop
      //   117: new 274	java/io/FileOutputStream
      //   120: dup
      //   121: aload 6
      //   123: invokespecial 277	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   126: astore 4
      //   128: new 279	java/util/zip/ZipOutputStream
      //   131: dup
      //   132: aload 4
      //   134: invokespecial 282	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   137: astore 5
      //   139: aload 5
      //   141: new 284	java/util/zip/ZipEntry
      //   144: dup
      //   145: ldc_w 286
      //   148: invokespecial 287	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
      //   151: invokevirtual 291	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
      //   154: aload_3
      //   155: aload 5
      //   157: invokestatic 296	o/aiC:ˊ	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
      //   160: aload 5
      //   162: invokevirtual 299	java/util/zip/ZipOutputStream:closeEntry	()V
      //   165: aload_3
      //   166: invokevirtual 304	java/io/InputStream:close	()V
      //   169: aload 5
      //   171: invokevirtual 305	java/util/zip/ZipOutputStream:close	()V
      //   174: aload 4
      //   176: invokevirtual 308	java/io/OutputStream:close	()V
      //   179: aload_0
      //   180: invokevirtual 269	java/io/File:delete	()Z
      //   183: pop
      //   184: aload 6
      //   186: areturn
      //   187: astore_0
      //   188: aload_0
      //   189: athrow
      //   190: new 266	java/io/File
      //   193: dup
      //   194: aload_0
      //   195: invokespecial 309	java/io/File:<init>	(Ljava/lang/String;)V
      //   198: astore_0
      //   199: new 311	java/io/FileInputStream
      //   202: dup
      //   203: aload_0
      //   204: invokespecial 312	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   207: astore_3
      //   208: invokestatic 315	com/pinger/common/store/Preferences$aUx:ˎ	()Ljava/lang/String;
      //   211: astore 4
      //   213: new 266	java/io/File
      //   216: dup
      //   217: aload 4
      //   219: invokespecial 309	java/io/File:<init>	(Ljava/lang/String;)V
      //   222: astore 6
      //   224: aload 6
      //   226: invokevirtual 318	java/io/File:exists	()Z
      //   229: istore_2
      //   230: iload_2
      //   231: ifeq +6 -> 237
      //   234: goto -160 -> 74
      //   237: goto -131 -> 106
      //   240: bipush 55
      //   242: istore_1
      //   243: goto -231 -> 12
      //   246: aload 6
      //   248: invokevirtual 269	java/io/File:delete	()Z
      //   251: pop
      //   252: goto -141 -> 111
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	255	0	paramString	String
      //   12	231	1	i	int
      //   229	2	2	bool	boolean
      //   154	54	3	localObject1	Object
      //   126	92	4	localObject2	Object
      //   137	33	5	localZipOutputStream	java.util.zip.ZipOutputStream
      //   3	244	6	localFile	File
      // Exception table:
      //   from	to	target	type
      //   246	252	71	java/lang/Exception
      //   190	199	187	java/lang/Exception
      //   199	208	187	java/lang/Exception
      //   208	213	187	java/lang/Exception
      //   213	224	187	java/lang/Exception
      //   224	230	187	java/lang/Exception
    }
    
    /* Error */
    public static Integer ˊ(SharedPreferences paramSharedPreferences, String paramString)
    {
      // Byte code:
      //   0: goto +123 -> 123
      //   3: bipush 31
      //   5: istore_2
      //   6: goto +57 -> 63
      //   9: aload_0
      //   10: aload_1
      //   11: iconst_0
      //   12: invokeinterface 325 3 0
      //   17: istore_2
      //   18: iload_2
      //   19: invokestatic 329	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   22: areturn
      //   23: iconst_0
      //   24: istore_2
      //   25: goto +70 -> 95
      //   28: astore_0
      //   29: aload_0
      //   30: athrow
      //   31: astore_0
      //   32: aload_0
      //   33: athrow
      //   34: getstatic 22	o/aip$aUX:ᐝ	I
      //   37: istore_2
      //   38: iload_2
      //   39: bipush 17
      //   41: iadd
      //   42: istore_2
      //   43: iload_2
      //   44: sipush 128
      //   47: irem
      //   48: putstatic 20	o/aip$aUX:ˏ	I
      //   51: iload_2
      //   52: iconst_2
      //   53: irem
      //   54: ifeq +6 -> 60
      //   57: goto -54 -> 3
      //   60: goto +79 -> 139
      //   63: iload_2
      //   64: lookupswitch	default:+28->92, 31:+-55->9, 87:+81->145
      //   92: goto -89 -> 3
      //   95: iload_2
      //   96: tableswitch	default:+24->120, 0:+-62->34, 1:+61->157
      //   120: goto -97 -> 23
      //   123: aload_0
      //   124: aload_1
      //   125: invokeinterface 332 2 0
      //   130: ifeq +6 -> 136
      //   133: goto -110 -> 23
      //   136: goto +23 -> 159
      //   139: bipush 87
      //   141: istore_2
      //   142: goto -79 -> 63
      //   145: aload_0
      //   146: aload_1
      //   147: iconst_0
      //   148: invokeinterface 325 3 0
      //   153: invokestatic 329	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   156: areturn
      //   157: aconst_null
      //   158: areturn
      //   159: iconst_1
      //   160: istore_2
      //   161: goto -66 -> 95
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	164	0	paramSharedPreferences	SharedPreferences
      //   0	164	1	paramString	String
      //   5	156	2	i	int
      // Exception table:
      //   from	to	target	type
      //   34	38	28	java/lang/Exception
      //   43	51	28	java/lang/Exception
      //   9	18	31	java/lang/Exception
    }
    
    public static String ˊ()
    {
      return "Unknown Number";
    }
    
    /* Error */
    public static String ˊ(int paramInt)
    {
      // Byte code:
      //   0: goto +324 -> 324
      //   3: iconst_0
      //   4: istore 5
      //   6: aload 7
      //   8: astore 9
      //   10: iload_3
      //   11: istore 4
      //   13: iload 5
      //   15: tableswitch	default:+21->36, 0:+172->187, 1:+322->337
      //   36: goto -33 -> 3
      //   39: iconst_0
      //   40: istore 4
      //   42: goto +204 -> 246
      //   45: getstatic 20	o/aip$aUX:ˏ	I
      //   48: bipush 79
      //   50: iadd
      //   51: istore 4
      //   53: iload 4
      //   55: sipush 128
      //   58: irem
      //   59: putstatic 22	o/aip$aUX:ᐝ	I
      //   62: iload 4
      //   64: iconst_2
      //   65: irem
      //   66: ifne +6 -> 72
      //   69: goto +237 -> 306
      //   72: goto -69 -> 3
      //   75: astore 7
      //   77: aload 7
      //   79: athrow
      //   80: bipush 46
      //   82: istore_3
      //   83: goto +60 -> 143
      //   86: bipush 9
      //   88: istore_3
      //   89: goto +54 -> 143
      //   92: iconst_1
      //   93: istore 4
      //   95: goto +151 -> 246
      //   98: iload_0
      //   99: ifge +6 -> 105
      //   102: goto -16 -> 86
      //   105: goto -25 -> 80
      //   108: aload 7
      //   110: astore 10
      //   112: iload 4
      //   114: lookupswitch	default:+26->140, 45:+-69->45, 58:+270->384
      //   140: goto +99 -> 239
      //   143: aload 8
      //   145: astore 9
      //   147: iload 5
      //   149: istore 4
      //   151: aload 8
      //   153: astore 10
      //   155: iload_3
      //   156: lookupswitch	default:+28->184, 9:+31->187, 46:+228->384
      //   184: goto -98 -> 86
      //   187: invokestatic 340	java/lang/Math:random	()D
      //   190: dstore_1
      //   191: dload_1
      //   192: ldc2_w 341
      //   195: dmul
      //   196: d2i
      //   197: istore_3
      //   198: new 49	java/lang/StringBuilder
      //   201: dup
      //   202: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   205: astore 7
      //   207: aload 7
      //   209: aload 9
      //   211: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   214: astore 7
      //   216: aload 7
      //   218: iload_3
      //   219: invokevirtual 345	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   222: astore 7
      //   224: aload 7
      //   226: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   229: astore 7
      //   231: iload 4
      //   233: iconst_1
      //   234: iadd
      //   235: istore_3
      //   236: goto +137 -> 373
      //   239: bipush 45
      //   241: istore 4
      //   243: goto -135 -> 108
      //   246: aload 8
      //   248: astore 7
      //   250: iload 6
      //   252: istore_3
      //   253: iload 4
      //   255: tableswitch	default:+21->276, 0:+118->373, 1:+-157->98
      //   276: goto -184 -> 92
      //   279: getstatic 20	o/aip$aUX:ˏ	I
      //   282: bipush 91
      //   284: iadd
      //   285: istore_3
      //   286: iload_3
      //   287: sipush 128
      //   290: irem
      //   291: putstatic 22	o/aip$aUX:ᐝ	I
      //   294: iload_3
      //   295: iconst_2
      //   296: irem
      //   297: ifne +6 -> 303
      //   300: goto -208 -> 92
      //   303: goto -264 -> 39
      //   306: iconst_1
      //   307: istore 5
      //   309: goto -303 -> 6
      //   312: astore 7
      //   314: aload 7
      //   316: athrow
      //   317: bipush 58
      //   319: istore 4
      //   321: goto -213 -> 108
      //   324: ldc -87
      //   326: astore 8
      //   328: iconst_0
      //   329: istore 6
      //   331: iconst_0
      //   332: istore 5
      //   334: goto -55 -> 279
      //   337: invokestatic 340	java/lang/Math:random	()D
      //   340: ldc2_w 341
      //   343: dmul
      //   344: d2i
      //   345: istore 4
      //   347: new 49	java/lang/StringBuilder
      //   350: dup
      //   351: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   354: aload 7
      //   356: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   359: iload 4
      //   361: invokevirtual 345	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   364: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   367: astore 7
      //   369: iload_3
      //   370: iconst_1
      //   371: iadd
      //   372: istore_3
      //   373: iload_3
      //   374: iload_0
      //   375: if_icmpge +6 -> 381
      //   378: goto -139 -> 239
      //   381: goto -64 -> 317
      //   384: aload 10
      //   386: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	387	0	paramInt	int
      //   190	2	1	d	double
      //   10	366	3	i	int
      //   11	349	4	j	int
      //   4	329	5	k	int
      //   250	80	6	m	int
      //   6	1	7	localObject1	Object
      //   75	34	7	localException1	Exception
      //   205	44	7	localObject2	Object
      //   312	43	7	localException2	Exception
      //   367	1	7	str1	String
      //   143	184	8	str2	String
      //   8	202	9	localObject3	Object
      //   110	275	10	localObject4	Object
      // Exception table:
      //   from	to	target	type
      //   187	191	75	java/lang/Exception
      //   198	207	75	java/lang/Exception
      //   207	216	75	java/lang/Exception
      //   216	224	75	java/lang/Exception
      //   224	231	75	java/lang/Exception
      //   45	53	312	java/lang/Exception
      //   53	62	312	java/lang/Exception
    }
    
    private static String ˊ(int paramInt, short paramShort, byte paramByte)
    {
      break label137;
      int k;
      int i;
      short s;
      int j;
      for (;;)
      {
        paramShort = ˏ + 51;
        ᐝ = paramShort % 128;
        if (paramShort % 2 == 0) {
          break label129;
        }
        break;
        i = k + 1;
        s = s + -j + 2;
      }
      byte[] arrayOfByte2;
      label129:
      label137:
      label194:
      label323:
      label331:
      for (int m = 84;; m = 73)
      {
        s = paramByte;
        j = i;
        int i1;
        paramInt = i1;
        k = paramShort;
        switch (m)
        {
        case 84: 
        default: 
          break;
        case 73: 
          byte b;
          byte[] arrayOfByte1;
          for (;;)
          {
            paramByte = j + 1;
            arrayOfByte2[j] = ((byte)paramShort);
            if (paramByte == b) {
              break label338;
            }
            i = paramInt;
            break;
            int n;
            for (;;)
            {
              k = 0;
              paramByte = paramInt;
              break label194;
              k = 1;
              paramByte = paramInt;
              break label194;
              n = 0;
              i1 = 0;
              b = paramInt * 4 + 7;
              paramByte = paramByte * 2 + 97;
              arrayOfByte1 = ˋ;
              paramShort = paramShort * 3 + 4;
              arrayOfByte2 = new byte[b];
              if (arrayOfByte1 == null) {
                break label323;
              }
              j = n;
              paramInt = paramShort;
              paramShort = paramByte;
              break;
              j = paramByte;
              paramInt = i;
              paramShort = s;
              switch (k)
              {
              case 0: 
              default: 
                paramInt = paramByte;
              }
            }
            paramInt = paramShort + 1;
            paramShort = paramByte + -i + 2;
            j = n;
          }
          for (;;)
          {
            j = arrayOfByte1[i];
            s = paramShort;
            paramInt = paramByte;
            k = i;
            break;
            paramInt = paramByte + 1;
            arrayOfByte2[paramByte] = ((byte)s);
            if (paramInt == b) {
              break label338;
            }
            paramByte = paramInt;
            paramShort = s;
          }
          for (;;)
          {
            paramInt = ˏ + 23;
            ᐝ = paramInt % 128;
            if (paramInt % 2 == 0) {
              break label331;
            }
            break;
            paramByte = b;
            i = paramShort;
          }
        }
      }
      label338:
      return new String(arrayOfByte2, 0);
    }
    
    public static String ˊ(Context paramContext, boolean paramBoolean)
    {
      break label534;
      int i = 45;
      break label182;
      Object localObject2;
      Object localObject1 = localObject2;
      Object localObject3;
      switch (i)
      {
      default: 
        break label528;
        return localObject3;
        localObject1 = ((Locale)localObject3).getCountry().toUpperCase();
        break label504;
        label59:
        i = 1;
        break label469;
        for (;;)
        {
          label64:
          i = 0;
          break label399;
          if (paramBoolean) {
            break label721;
          }
          break label458;
          label79:
          i = 1;
          break;
          label84:
          i = ᐝ + 1;
          ˏ = i % 128;
          if (i % 2 != 0) {
            break label558;
          }
        }
        return localObject1;
        localObject2 = ((Locale)localObject3).getCountry().toUpperCase();
        break;
        label126:
        i = 0;
        break label469;
        label131:
        localObject3 = localObject2;
        localObject1 = localObject2;
        switch (i)
        {
        }
        break;
      }
      for (;;)
      {
        label171:
        i = 8;
        break label740;
        label177:
        i = 1;
        break label677;
        label182:
        localObject1 = localObject2;
        switch (i)
        {
        default: 
          break label671;
          for (;;)
          {
            try
            {
              i = ᐝ + 41;
              try
              {
                ˏ = i % 128;
                if (i % 2 != 0) {
                  continue;
                }
              }
              catch (Exception paramContext)
              {
                throw paramContext;
              }
              i = 30;
            }
            catch (Exception paramContext)
            {
              label270:
              label319:
              label330:
              label363:
              label399:
              throw paramContext;
            }
            return "US";
            localObject3 = Locale.getDefault();
            if (localObject3 != null) {
              continue;
            }
            break;
            i = 29;
            localObject2 = localObject1;
            localObject3 = localObject2;
            localObject1 = localObject2;
            switch (i)
            {
            case 29: 
            default: 
              localObject1 = localObject2;
              continue;
              i = 6;
              continue;
              i = 0;
              continue;
              i = 49;
              switch (i)
              {
              }
              continue;
              localObject3 = localObject2;
              localObject1 = localObject2;
              switch (i)
              {
              case 0: 
              default: 
                break label79;
                localObject1 = localObject2;
                switch (i)
                {
                case 1: 
                default: 
                  break label64;
                  i = ˏ + 23;
                  ᐝ = i % 128;
                  if (i % 2 == 0) {
                    break label171;
                  }
                }
                break;
              }
              break;
            }
          }
          label458:
          i = 19;
          break;
        }
        for (;;)
        {
          i = 0;
          break label363;
          switch (i)
          {
          case 0: 
          default: 
            break label126;
            do
            {
              i = 3;
              localObject2 = localObject1;
              break;
            } while (paramBoolean);
            break label270;
            if (TextUtils.isEmpty(((Locale)localObject3).getCountry()))
            {
              i = 27;
              break;
              localObject1 = ˋ(paramContext);
              localObject2 = "US";
              if (!TextUtils.isEmpty((CharSequence)localObject1)) {
                break label59;
              }
              break label126;
              i = 1;
            }
            break;
          case 1: 
            for (;;)
            {
              label469:
              label504:
              label528:
              label534:
              label558:
              if (paramBoolean) {
                break label330;
              }
              break label319;
              paramBoolean = false;
              try
              {
                paramContext = Class.forName(paramContext.getPackageName() + ".util.CountryManager");
                localObject2 = paramContext.getMethod("getInstance", new Class[0]);
                boolean bool = ((Boolean)paramContext.getMethod("isSupportedCountry", new Class[] { String.class }).invoke(((Method)localObject2).invoke(null, new Object[0]), new Object[] { localObject1 })).booleanValue();
                paramBoolean = bool;
              }
              catch (Exception paramContext)
              {
                paramContext.printStackTrace();
              }
              i = 56;
              break;
              label671:
              i = 7;
              break label182;
              label677:
              switch (i)
              {
              }
              break label177;
              localObject2 = ((String)localObject1).toUpperCase();
              break label84;
            }
            label721:
            i = 40;
            break label131;
            if (paramBoolean) {
              break label79;
            }
          }
        }
        label740:
        localObject1 = localObject2;
        switch (i)
        {
        }
      }
    }
    
    /* Error */
    public static String ˊ(Bundle paramBundle)
    {
      // Byte code:
      //   0: goto +662 -> 662
      //   3: bipush 99
      //   5: istore_3
      //   6: iload_1
      //   7: istore_2
      //   8: goto +322 -> 330
      //   11: bipush 49
      //   13: istore_1
      //   14: goto +401 -> 415
      //   17: astore_0
      //   18: aload_0
      //   19: athrow
      //   20: iload_3
      //   21: istore_1
      //   22: iload_2
      //   23: lookupswitch	default:+25->48, 43:+225->248, 49:+581->604
      //   48: goto +139 -> 187
      //   51: bipush 43
      //   53: istore_2
      //   54: goto -34 -> 20
      //   57: iconst_1
      //   58: istore_1
      //   59: goto +68 -> 127
      //   62: aload 9
      //   64: aload 6
      //   66: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   69: pop
      //   70: aload 9
      //   72: ldc_w 402
      //   75: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: pop
      //   79: aload 9
      //   81: aload_0
      //   82: aload 6
      //   84: invokevirtual 408	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   87: invokevirtual 411	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   90: pop
      //   91: iconst_0
      //   92: istore_1
      //   93: goto +100 -> 193
      //   96: iload_3
      //   97: istore_1
      //   98: iload_2
      //   99: lookupswitch	default:+25->124, 29:+94->193, 87:+406->505
      //   124: goto +90 -> 214
      //   127: iload 4
      //   129: istore_3
      //   130: iload_1
      //   131: tableswitch	default:+21->152, 0:+89->220, 1:+473->604
      //   152: goto -95 -> 57
      //   155: iload_1
      //   156: istore_3
      //   157: iload_2
      //   158: lookupswitch	default:+26->184, 44:+62->220, 89:+464->622
      //   184: goto +472 -> 656
      //   187: bipush 49
      //   189: istore_2
      //   190: goto -170 -> 20
      //   193: aload 10
      //   195: invokeinterface 416 1 0
      //   200: ifeq +6 -> 206
      //   203: goto -200 -> 3
      //   206: goto +442 -> 648
      //   209: iconst_2
      //   210: istore_1
      //   211: goto +204 -> 415
      //   214: bipush 29
      //   216: istore_2
      //   217: goto -121 -> 96
      //   220: aload 10
      //   222: invokeinterface 420 1 0
      //   227: checkcast 73	java/lang/String
      //   230: astore 8
      //   232: iload_3
      //   233: ifne +6 -> 239
      //   236: goto -27 -> 209
      //   239: goto -228 -> 11
      //   242: bipush 81
      //   244: istore_1
      //   245: goto +120 -> 365
      //   248: getstatic 22	o/aip$aUX:ᐝ	I
      //   251: bipush 117
      //   253: iadd
      //   254: istore_2
      //   255: iload_2
      //   256: sipush 128
      //   259: irem
      //   260: putstatic 20	o/aip$aUX:ˏ	I
      //   263: iload_2
      //   264: iconst_2
      //   265: irem
      //   266: ifeq +6 -> 272
      //   269: goto +50 -> 319
      //   272: goto +384 -> 656
      //   275: bipush 88
      //   277: istore_1
      //   278: goto +87 -> 365
      //   281: aload 8
      //   283: astore 6
      //   285: aload 8
      //   287: astore 7
      //   289: iload_1
      //   290: lookupswitch	default:+26->316, 1:+262->552, 78:+165->455
      //   316: goto +9 -> 325
      //   319: bipush 89
      //   321: istore_2
      //   322: goto -167 -> 155
      //   325: iconst_1
      //   326: istore_1
      //   327: goto -46 -> 281
      //   330: iload_2
      //   331: istore_1
      //   332: iload_3
      //   333: lookupswitch	default:+27->360, 68:+271->604, 99:+-85->248
      //   360: iload_2
      //   361: istore_1
      //   362: goto +286 -> 648
      //   365: aload 6
      //   367: astore 7
      //   369: iload_1
      //   370: lookupswitch	default:+26->396, 81:+182->552, 88:+-308->62
      //   396: goto -121 -> 275
      //   399: aload 10
      //   401: invokeinterface 416 1 0
      //   406: ifeq +6 -> 412
      //   409: goto -358 -> 51
      //   412: goto -225 -> 187
      //   415: aload 8
      //   417: astore 6
      //   419: aload 8
      //   421: astore 7
      //   423: iload_1
      //   424: lookupswitch	default:+28->452, 2:+31->455, 49:+128->552
      //   452: goto -441 -> 11
      //   455: aload 9
      //   457: ldc_w 422
      //   460: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   463: pop
      //   464: goto +14 -> 478
      //   467: iconst_0
      //   468: istore_1
      //   469: goto -342 -> 127
      //   472: bipush 78
      //   474: istore_1
      //   475: goto -194 -> 281
      //   478: getstatic 20	o/aip$aUX:ˏ	I
      //   481: bipush 23
      //   483: iadd
      //   484: istore_1
      //   485: iload_1
      //   486: sipush 128
      //   489: irem
      //   490: putstatic 22	o/aip$aUX:ᐝ	I
      //   493: iload_1
      //   494: iconst_2
      //   495: irem
      //   496: ifne +6 -> 502
      //   499: goto -224 -> 275
      //   502: goto -260 -> 242
      //   505: aload 10
      //   507: invokeinterface 416 1 0
      //   512: istore 5
      //   514: iload 5
      //   516: ifeq +6 -> 522
      //   519: goto -52 -> 467
      //   522: goto -465 -> 57
      //   525: getstatic 20	o/aip$aUX:ˏ	I
      //   528: bipush 9
      //   530: iadd
      //   531: istore_1
      //   532: iload_1
      //   533: sipush 128
      //   536: irem
      //   537: putstatic 22	o/aip$aUX:ᐝ	I
      //   540: iload_1
      //   541: iconst_2
      //   542: irem
      //   543: ifne +6 -> 549
      //   546: goto +47 -> 593
      //   549: goto -335 -> 214
      //   552: aload 9
      //   554: aload 7
      //   556: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   559: pop
      //   560: aload 9
      //   562: ldc_w 402
      //   565: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   568: pop
      //   569: aload_0
      //   570: aload 7
      //   572: invokevirtual 408	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   575: astore 6
      //   577: aload 9
      //   579: aload 6
      //   581: invokevirtual 411	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   584: pop
      //   585: iconst_0
      //   586: istore_3
      //   587: iconst_0
      //   588: istore 4
      //   590: goto +137 -> 727
      //   593: bipush 87
      //   595: istore_2
      //   596: goto -500 -> 96
      //   599: iconst_1
      //   600: istore_2
      //   601: goto +98 -> 699
      //   604: aload 9
      //   606: ldc_w 424
      //   609: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   612: pop
      //   613: aload 9
      //   615: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   618: areturn
      //   619: astore_0
      //   620: aload_0
      //   621: athrow
      //   622: aload 10
      //   624: invokeinterface 420 1 0
      //   629: astore 6
      //   631: aload 6
      //   633: checkcast 73	java/lang/String
      //   636: astore 8
      //   638: iload_1
      //   639: ifne +6 -> 645
      //   642: goto -170 -> 472
      //   645: goto -320 -> 325
      //   648: bipush 68
      //   650: istore_3
      //   651: iload_1
      //   652: istore_2
      //   653: goto -323 -> 330
      //   656: bipush 44
      //   658: istore_2
      //   659: goto -504 -> 155
      //   662: new 49	java/lang/StringBuilder
      //   665: dup
      //   666: ldc_w 426
      //   669: invokespecial 427	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   672: astore 9
      //   674: iconst_1
      //   675: istore 4
      //   677: iconst_1
      //   678: istore_3
      //   679: aload_0
      //   680: invokevirtual 431	android/os/Bundle:keySet	()Ljava/util/Set;
      //   683: astore 6
      //   685: aload 6
      //   687: invokeinterface 437 1 0
      //   692: astore 10
      //   694: goto -169 -> 525
      //   697: iconst_0
      //   698: istore_2
      //   699: iload 4
      //   701: istore_1
      //   702: iload_2
      //   703: tableswitch	default:+21->724, 0:+-510->193, 1:+-304->399
      //   724: goto -125 -> 599
      //   727: getstatic 22	o/aip$aUX:ᐝ	I
      //   730: istore_1
      //   731: iload_1
      //   732: bipush 35
      //   734: iadd
      //   735: istore_1
      //   736: iload_1
      //   737: sipush 128
      //   740: irem
      //   741: putstatic 20	o/aip$aUX:ˏ	I
      //   744: iload_1
      //   745: iconst_2
      //   746: irem
      //   747: ifeq +6 -> 753
      //   750: goto -151 -> 599
      //   753: goto -56 -> 697
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	756	0	paramBundle	Bundle
      //   6	741	1	i	int
      //   7	696	2	j	int
      //   5	674	3	k	int
      //   127	573	4	m	int
      //   512	3	5	bool	boolean
      //   64	622	6	localObject1	Object
      //   287	284	7	localObject2	Object
      //   230	407	8	str	String
      //   62	611	9	localStringBuilder	StringBuilder
      //   193	500	10	localIterator	Iterator
      // Exception table:
      //   from	to	target	type
      //   505	514	17	java/lang/Exception
      //   552	560	619	java/lang/Exception
      //   560	569	619	java/lang/Exception
      //   569	577	619	java/lang/Exception
      //   577	585	619	java/lang/Exception
      //   622	631	619	java/lang/Exception
      //   631	638	619	java/lang/Exception
      //   662	674	619	java/lang/Exception
      //   679	685	619	java/lang/Exception
      //   685	694	619	java/lang/Exception
      //   727	731	619	java/lang/Exception
      //   736	744	619	java/lang/Exception
    }
    
    public static String ˊ(TextView paramTextView)
    {
      return paramTextView.getText().toString();
    }
    
    /* Error */
    public static String ˊ(Integer paramInteger, String paramString, boolean paramBoolean)
    {
      // Byte code:
      //   0: goto +48 -> 48
      //   3: bipush 20
      //   5: istore_3
      //   6: goto +306 -> 312
      //   9: aload 4
      //   11: astore_0
      //   12: iload_3
      //   13: lookupswitch	default:+27->40, 25:+724->737, 33:+457->470
      //   40: goto +655 -> 695
      //   43: iconst_0
      //   44: istore_3
      //   45: goto +547 -> 592
      //   48: aload_0
      //   49: ifnonnull +6 -> 55
      //   52: goto +431 -> 483
      //   55: goto +880 -> 935
      //   58: iconst_1
      //   59: istore_3
      //   60: goto +532 -> 592
      //   63: iconst_0
      //   64: istore_3
      //   65: goto +214 -> 279
      //   68: iconst_0
      //   69: istore_3
      //   70: goto +559 -> 629
      //   73: iload_2
      //   74: ifeq +6 -> 80
      //   77: goto -74 -> 3
      //   80: goto +543 -> 623
      //   83: aload_0
      //   84: astore 4
      //   86: iload_3
      //   87: tableswitch	default:+21->108, 0:+889->976, 1:+56->143
      //   108: goto +375 -> 483
      //   111: aload 4
      //   113: astore_0
      //   114: aload 4
      //   116: astore_1
      //   117: iload_3
      //   118: tableswitch	default:+22->140, 0:+342->460, 1:+344->462
      //   140: goto +587 -> 727
      //   143: invokestatic 453	com/pinger/common/app/PingerApplication:ˏ	()Lcom/pinger/common/app/PingerApplication;
      //   146: astore_0
      //   147: aload_0
      //   148: astore 5
      //   150: aload_0
      //   151: astore 6
      //   153: aload_0
      //   154: astore 7
      //   156: aload_0
      //   157: astore 8
      //   159: aload 4
      //   161: invokevirtual 456	java/lang/Integer:intValue	()I
      //   164: tableswitch	default:+32->196, 1:+208->372, 2:+195->359, 3:+634->798, 4:+183->347
      //   196: goto +36 -> 232
      //   199: bipush 11
      //   201: istore_3
      //   202: goto +461 -> 663
      //   205: getstatic 20	o/aip$aUX:ˏ	I
      //   208: bipush 89
      //   210: iadd
      //   211: istore_3
      //   212: iload_3
      //   213: sipush 128
      //   216: irem
      //   217: putstatic 22	o/aip$aUX:ᐝ	I
      //   220: iload_3
      //   221: iconst_2
      //   222: irem
      //   223: ifne +6 -> 229
      //   226: goto +744 -> 970
      //   229: goto +492 -> 721
      //   232: aload_1
      //   233: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   236: ifne +6 -> 242
      //   239: goto -176 -> 63
      //   242: goto +510 -> 752
      //   245: aload 4
      //   247: astore_0
      //   248: aload 4
      //   250: astore_1
      //   251: iload_3
      //   252: tableswitch	default:+24->276, 0:+208->460, 1:+210->462
      //   276: goto +456 -> 732
      //   279: iload_3
      //   280: tableswitch	default:+24->304, 0:+170->450, 1:+239->519
      //   304: goto -241 -> 63
      //   307: iconst_0
      //   308: istore_3
      //   309: goto +558 -> 867
      //   312: aload 4
      //   314: astore_0
      //   315: aload 4
      //   317: astore_1
      //   318: iload_3
      //   319: lookupswitch	default:+25->344, 20:+143->462, 81:+141->460
      //   344: goto -341 -> 3
      //   347: aload 5
      //   349: ldc_w 457
      //   352: invokevirtual 460	android/content/Context:getString	(I)Ljava/lang/String;
      //   355: astore_0
      //   356: goto +114 -> 470
      //   359: aload 6
      //   361: ldc_w 461
      //   364: invokevirtual 460	android/content/Context:getString	(I)Ljava/lang/String;
      //   367: astore 4
      //   369: goto +167 -> 536
      //   372: aload 7
      //   374: ldc_w 462
      //   377: invokevirtual 460	android/content/Context:getString	(I)Ljava/lang/String;
      //   380: astore 4
      //   382: goto +513 -> 895
      //   385: iconst_1
      //   386: istore_3
      //   387: goto +242 -> 629
      //   390: aload 5
      //   392: astore 4
      //   394: iload_3
      //   395: lookupswitch	default:+25->420, 10:+-252->143, 98:+416->811
      //   420: goto +550 -> 970
      //   423: getstatic 22	o/aip$aUX:ᐝ	I
      //   426: bipush 107
      //   428: iadd
      //   429: istore_3
      //   430: iload_3
      //   431: sipush 128
      //   434: irem
      //   435: putstatic 20	o/aip$aUX:ˏ	I
      //   438: iload_3
      //   439: iconst_2
      //   440: irem
      //   441: ifeq +6 -> 447
      //   444: goto -386 -> 58
      //   447: goto -404 -> 43
      //   450: aload_1
      //   451: astore_0
      //   452: goto +18 -> 470
      //   455: iconst_1
      //   456: istore_3
      //   457: goto +410 -> 867
      //   460: aload_0
      //   461: areturn
      //   462: aload_1
      //   463: invokevirtual 231	java/lang/String:toLowerCase	()Ljava/lang/String;
      //   466: astore_1
      //   467: goto +96 -> 563
      //   470: iload_2
      //   471: ifeq +6 -> 477
      //   474: goto +487 -> 961
      //   477: goto +230 -> 707
      //   480: astore_0
      //   481: aload_0
      //   482: athrow
      //   483: iconst_0
      //   484: istore_3
      //   485: goto -402 -> 83
      //   488: aload 4
      //   490: astore_0
      //   491: iload_3
      //   492: tableswitch	default:+24->516, 0:+430->922, 1:+-22->470
      //   516: goto +231 -> 747
      //   519: aload_0
      //   520: ldc_w 463
      //   523: invokevirtual 460	android/content/Context:getString	(I)Ljava/lang/String;
      //   526: astore 4
      //   528: goto +457 -> 985
      //   531: iconst_0
      //   532: istore_3
      //   533: goto -422 -> 111
      //   536: getstatic 22	o/aip$aUX:ᐝ	I
      //   539: bipush 17
      //   541: iadd
      //   542: istore_3
      //   543: iload_3
      //   544: sipush 128
      //   547: irem
      //   548: putstatic 20	o/aip$aUX:ˏ	I
      //   551: iload_3
      //   552: iconst_2
      //   553: irem
      //   554: ifeq +6 -> 560
      //   557: goto +393 -> 950
      //   560: goto +135 -> 695
      //   563: getstatic 22	o/aip$aUX:ᐝ	I
      //   566: bipush 51
      //   568: iadd
      //   569: istore_3
      //   570: iload_3
      //   571: sipush 128
      //   574: irem
      //   575: putstatic 20	o/aip$aUX:ˏ	I
      //   578: iload_3
      //   579: iconst_2
      //   580: irem
      //   581: ifeq +6 -> 587
      //   584: goto -277 -> 307
      //   587: goto -132 -> 455
      //   590: aload_1
      //   591: areturn
      //   592: aload 4
      //   594: astore_0
      //   595: iload_3
      //   596: tableswitch	default:+24->620, 0:+-126->470, 1:+344->940
      //   620: goto -577 -> 43
      //   623: bipush 81
      //   625: istore_3
      //   626: goto -314 -> 312
      //   629: aload 4
      //   631: astore_0
      //   632: aload 4
      //   634: astore_1
      //   635: iload_3
      //   636: tableswitch	default:+24->660, 0:+-174->462, 1:+-176->460
      //   660: goto -592 -> 68
      //   663: aload 4
      //   665: astore_0
      //   666: iload_3
      //   667: lookupswitch	default:+25->692, 11:+-197->470, 13:+-594->73
      //   692: goto +9 -> 701
      //   695: bipush 33
      //   697: istore_3
      //   698: goto -689 -> 9
      //   701: bipush 13
      //   703: istore_3
      //   704: goto -41 -> 663
      //   707: bipush 6
      //   709: istore_3
      //   710: aload_0
      //   711: astore 4
      //   713: goto +44 -> 757
      //   716: iconst_1
      //   717: istore_3
      //   718: goto -230 -> 488
      //   721: bipush 10
      //   723: istore_3
      //   724: goto -334 -> 390
      //   727: iconst_1
      //   728: istore_3
      //   729: goto -618 -> 111
      //   732: iconst_0
      //   733: istore_3
      //   734: goto -489 -> 245
      //   737: iload_2
      //   738: ifeq +6 -> 744
      //   741: goto +215 -> 956
      //   744: goto -12 -> 732
      //   747: iconst_0
      //   748: istore_3
      //   749: goto -261 -> 488
      //   752: iconst_1
      //   753: istore_3
      //   754: goto -475 -> 279
      //   757: aload 4
      //   759: astore_0
      //   760: aload 4
      //   762: astore_1
      //   763: iload_3
      //   764: lookupswitch	default:+28->792, 6:+-304->460, 76:+-302->462
      //   792: aload 4
      //   794: astore_0
      //   795: goto +166 -> 961
      //   798: aload 8
      //   800: ldc_w 464
      //   803: invokevirtual 460	android/content/Context:getString	(I)Ljava/lang/String;
      //   806: astore 4
      //   808: goto -385 -> 423
      //   811: invokestatic 453	com/pinger/common/app/PingerApplication:ˏ	()Lcom/pinger/common/app/PingerApplication;
      //   814: astore_0
      //   815: aload 5
      //   817: invokevirtual 456	java/lang/Integer:intValue	()I
      //   820: istore_3
      //   821: aload_0
      //   822: astore 5
      //   824: aload_0
      //   825: astore 6
      //   827: aload_0
      //   828: astore 7
      //   830: aload_0
      //   831: astore 8
      //   833: iload_3
      //   834: tableswitch	default:+30->864, 1:+-462->372, 2:+-475->359, 3:+-36->798, 4:+-487->347
      //   864: goto -632 -> 232
      //   867: aload_1
      //   868: astore_0
      //   869: iload_3
      //   870: tableswitch	default:+22->892, 0:+-280->590, 1:+-410->460
      //   892: goto -437 -> 455
      //   895: getstatic 20	o/aip$aUX:ˏ	I
      //   898: bipush 75
      //   900: iadd
      //   901: istore_3
      //   902: iload_3
      //   903: sipush 128
      //   906: irem
      //   907: putstatic 22	o/aip$aUX:ᐝ	I
      //   910: iload_3
      //   911: iconst_2
      //   912: irem
      //   913: ifne +6 -> 919
      //   916: goto -215 -> 701
      //   919: goto -720 -> 199
      //   922: iload_2
      //   923: ifeq +6 -> 929
      //   926: goto -858 -> 68
      //   929: goto -544 -> 385
      //   932: astore_0
      //   933: aload_0
      //   934: athrow
      //   935: iconst_1
      //   936: istore_3
      //   937: goto -854 -> 83
      //   940: iload_2
      //   941: ifeq +6 -> 947
      //   944: goto -217 -> 727
      //   947: goto -416 -> 531
      //   950: bipush 25
      //   952: istore_3
      //   953: goto -944 -> 9
      //   956: iconst_1
      //   957: istore_3
      //   958: goto -713 -> 245
      //   961: bipush 76
      //   963: istore_3
      //   964: aload_0
      //   965: astore 4
      //   967: goto -210 -> 757
      //   970: bipush 98
      //   972: istore_3
      //   973: goto -583 -> 390
      //   976: iconst_3
      //   977: invokestatic 329	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   980: astore 5
      //   982: goto -777 -> 205
      //   985: getstatic 20	o/aip$aUX:ˏ	I
      //   988: istore_3
      //   989: iload_3
      //   990: bipush 61
      //   992: iadd
      //   993: istore_3
      //   994: iload_3
      //   995: sipush 128
      //   998: irem
      //   999: putstatic 22	o/aip$aUX:ᐝ	I
      //   1002: iload_3
      //   1003: iconst_2
      //   1004: irem
      //   1005: ifne +6 -> 1011
      //   1008: goto -261 -> 747
      //   1011: goto -295 -> 716
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1014	0	paramInteger	Integer
      //   0	1014	1	paramString	String
      //   0	1014	2	paramBoolean	boolean
      //   5	1000	3	i	int
      //   9	957	4	localObject	Object
      //   148	833	5	localInteger1	Integer
      //   151	675	6	localInteger2	Integer
      //   154	675	7	localInteger3	Integer
      //   157	675	8	localInteger4	Integer
      // Exception table:
      //   from	to	target	type
      //   519	528	480	java/lang/Exception
      //   798	808	480	java/lang/Exception
      //   811	815	480	java/lang/Exception
      //   815	821	480	java/lang/Exception
      //   347	356	932	java/lang/Exception
      //   985	989	932	java/lang/Exception
      //   994	1002	932	java/lang/Exception
    }
    
    public static String ˊ(String paramString, int paramInt)
    {
      label3:
      int i;
      label41:
      label46:
      int j;
      switch (i)
      {
      default: 
        break label215;
        paramInt = 42;
        break;
      case 91: 
        do
        {
          i = 0;
          break label187;
          switch (paramInt)
          {
          default: 
            break label181;
            j = paramString.length();
          }
        } while (j < paramInt);
        break label221;
        if (!TextUtils.isEmpty(paramString))
        {
          break label238;
          return "";
        }
        break;
      case 61: 
        for (;;)
        {
          paramInt = 81;
          break;
          paramInt = ᐝ + 53;
          ˏ = paramInt % 128;
          if (paramInt % 2 == 0) {
            break label181;
          }
        }
      }
      for (;;)
      {
        paramInt = 89;
        break label148;
        return paramString;
        switch (paramInt)
        {
        case 89: 
        default: 
          break;
        case 42: 
          label148:
          return paramString;
          label181:
          paramInt = 21;
          break label46;
          for (;;)
          {
            switch (i)
            {
            default: 
              label187:
              break label41;
              label215:
              i = 61;
              break label3;
              label221:
              i = 1;
            }
          }
          return "";
          return paramString.substring(j - paramInt, j);
          label238:
          i = 91;
          break label3;
          paramInt = ᐝ + 25;
          ˏ = paramInt % 128;
          if (paramInt % 2 != 0) {
            break;
          }
        }
      }
    }
    
    public static String ˊ(String paramString, int paramInt1, int paramInt2)
    {
      return ɜ.ˊ(paramString, paramInt1, paramInt2);
    }
    
    public static Qo ˊ(Qd paramQd, Qm... paramVarArgs)
    {
      break label753;
      int i = 67;
      Object localObject2;
      Object localObject1 = localObject2;
      break label942;
      label13:
      Object localObject5 = localObject1;
      Object localObject4 = localObject2;
      int j = i;
      int k;
      int n = k;
      int m;
      label73:
      Object localObject3;
      label127:
      label138:
      label197:
      label202:
      label216:
      boolean bool1;
      label227:
      label255:
      label261:
      label298:
      label337:
      label394:
      boolean bool2;
      switch (m)
      {
      default: 
        j = k;
        break label1047;
        i = 46;
        break;
        i = 1;
        break label1263;
        i = 18;
        break;
      case 83: 
        for (;;)
        {
          localObject5 = localObject3;
          Qm[] arrayOfQm;
          localObject4 = arrayOfQm;
          j = k;
          int i1;
          n = i1;
          localObject2 = localObject3;
          switch (i)
          {
          default: 
            break;
          case 91: 
            for (;;)
            {
              if (j >= i)
              {
                break label1480;
                switch (i)
                {
                default: 
                  break label873;
                  switch (i)
                  {
                  default: 
                    break label789;
                    i = 87;
                    break label1358;
                    i = 1;
                    break label832;
                    m = 31;
                    localObject2 = localObject3;
                    k = j;
                    break label13;
                    i = 1;
                    break label227;
                    bool1 = true;
                  }
                  break;
                case 1: 
                  for (;;)
                  {
                    switch (i)
                    {
                    default: 
                      break label513;
                      i = 82;
                      break label337;
                      i = 1;
                      break label1021;
                      for (;;)
                      {
                        i = 1;
                        break;
                        i = ˏ + 25;
                        ᐝ = i % 128;
                        if (i % 2 == 0) {
                          break label216;
                        }
                        break label513;
                        i = 1;
                        break label718;
                        k = ˏ + 111;
                        ᐝ = k % 128;
                        if (k % 2 == 0) {
                          break label202;
                        }
                        localObject2 = localObject3;
                        break label1047;
                        switch (i)
                        {
                        default: 
                          break label255;
                          i = ˏ + 121;
                          ᐝ = i % 128;
                          if (i % 2 == 0) {
                            break label747;
                          }
                          break label811;
                          bool1 = bool2;
                          switch (i)
                          {
                          default: 
                            break label659;
                            i = 0;
                            break label622;
                            i = ˏ + 107;
                            ᐝ = i % 128;
                            if (i % 2 == 0) {
                              break label1225;
                            }
                          }
                          break;
                        case 82: 
                          label427:
                          i = ˏ + 87;
                          ᐝ = i % 128;
                          if (i % 2 == 0) {
                            break label261;
                          }
                          break label1215;
                          i = ˏ + 117;
                          ᐝ = i % 128;
                          if (i % 2 == 0) {
                            break label789;
                          }
                        }
                      }
                      label513:
                      i = 0;
                    }
                  }
                  localObject1 = localObject4[n];
                  try
                  {
                    ((Qm)localObject1).ˊ(paramQd);
                    k = n + 1;
                    localObject1 = localObject5;
                    localObject2 = localObject4;
                    i = j;
                    j = k;
                  }
                  catch (Exception paramQd)
                  {
                    label553:
                    label587:
                    label622:
                    label659:
                    label679:
                    label718:
                    label747:
                    label753:
                    throw paramQd;
                  }
                  localObject2 = localObject1;
                  switch (i)
                  {
                  default: 
                    break label747;
                    localObject3 = localObject1;
                    switch (i)
                    {
                    default: 
                      break label1220;
                      return localObject3;
                      switch (i)
                      {
                      }
                      break;
                    case 0: 
                      localObject2 = paramVarArgs;
                      i = localObject2.length;
                      j = 0;
                    }
                    break;
                  case 96: 
                  case 43: 
                    for (;;)
                    {
                      i = 82;
                      break label394;
                      if (paramQd != null) {
                        break;
                      }
                      localObject1 = localObject2;
                      break label939;
                      i = 13;
                      break label337;
                      bool1 = false;
                      break label1433;
                      i = ˏ + 47;
                      ᐝ = i % 128;
                      if (i % 2 == 0) {
                        break label1220;
                      }
                      break label1418;
                      for (;;)
                      {
                        localObject3 = localObject1;
                        switch (i)
                        {
                        }
                        break label298;
                        i = 43;
                        break label553;
                        bool1 = Ч.ˊ;
                        if (bool1) {
                          break label255;
                        }
                        break label679;
                        label772:
                        m = 28;
                        break label893;
                        label779:
                        i = 0;
                        break label1263;
                        label784:
                        i = 0;
                        break label1297;
                        label789:
                        i = 0;
                        break;
                        arrayOfQm = paramVarArgs;
                        k = arrayOfQm.length;
                        i1 = 0;
                        n = 0;
                        break label1453;
                        label811:
                        i = 96;
                        break label553;
                        if (paramQd != null) {
                          break label1163;
                        }
                        break label1413;
                        label827:
                        i = 0;
                      }
                      label832:
                      localObject3 = localObject1;
                      localObject2 = localObject1;
                      switch (i)
                      {
                      case 1: 
                      default: 
                        break label197;
                        label867:
                        m = 0;
                        break label893;
                        label873:
                        i = 0;
                        break label138;
                        for (;;)
                        {
                          i = 1;
                          break label622;
                          if (paramQd != null) {
                            break label197;
                          }
                          break label1063;
                          label893:
                          localObject1 = localObject3;
                          localObject2 = arrayOfQm;
                          i = k;
                          j = n;
                          switch (m)
                          {
                          case 28: 
                          default: 
                            break label772;
                            for (;;)
                            {
                              label939:
                              i = 15;
                              label942:
                              localObject2 = localObject1;
                              switch (i)
                              {
                              }
                            }
                            label975:
                            i = 1;
                            break label138;
                            label980:
                            switch (i)
                            {
                            }
                            break label1121;
                            if (paramVarArgs != null) {
                              break label427;
                            }
                          }
                        }
                        switch (i)
                        {
                        case 1: 
                        default: 
                          break label1215;
                          m = 83;
                          k = j;
                          break;
                        case 0: 
                          label1021:
                          label1047:
                          label1063:
                          do
                          {
                            i = 77;
                            break label980;
                            i = 0;
                            break;
                          } while (paramQd != null);
                          break label1121;
                          i = ˏ + 61;
                          ᐝ = i % 128;
                          if (i % 2 == 0) {
                            break label73;
                          }
                        }
                        break;
                      }
                    }
                  }
                  break;
                }
              }
            }
            for (;;)
            {
              k = 0;
              localObject3 = localObject2;
              break label1127;
              bool1 = true;
              break label1433;
              label1121:
              i = 69;
              break;
              label1127:
              localObject2 = localObject1;
              switch (k)
              {
              case 0: 
              default: 
                localObject2 = localObject3;
              }
            }
            label1163:
            i = 0;
            break label1190;
            if (k >= 0) {
              break;
            }
            i = 91;
          }
        }
      }
      for (;;)
      {
        label1185:
        i = 1;
        break label1297;
        switch (i)
        {
        case 1: 
        default: 
          break label1413;
          i = 0;
          break label1021;
          i = 0;
          break;
          i = 23;
          break label1358;
          ((Qo)localObject2).ˊ(paramVarArgs);
          return localObject2;
        case 0: 
          label1190:
          label1215:
          label1220:
          label1225:
          bool1 = true;
          break label1433;
          localObject2[k].ˊ(paramQd);
          j = k + 1;
          break label127;
          switch (i)
          {
          case 0: 
          default: 
            break;
          case 1: 
            label1263:
            bool2 = false;
          }
          break;
        }
        label1297:
        localObject3 = localObject1;
        localObject2 = localObject1;
        switch (i)
        {
        }
      }
      for (;;)
      {
        ь.ˊ(bool1, "the requests array should be valid!");
        localObject1 = new Qo();
        if (paramVarArgs != null) {
          break label827;
        }
        break label298;
        switch (i)
        {
        case 87: 
        default: 
          break label1225;
          ь.ˊ(false, "the requests array should be valid!");
          localObject1 = new Qo();
          if (paramVarArgs != null) {
            break label1185;
          }
          break label784;
          i = 1;
          break label1190;
          i = 1;
          break label587;
          if (paramVarArgs != null) {
            break label779;
          }
          break;
          ь.ˊ(bool1, "the listener should be valid!");
          if (Ч.ˊ) {
            break label975;
          }
          break label873;
          i = ᐝ + 99;
          ˏ = i % 128;
          if (i % 2 != 0) {
            break label867;
          }
          break label772;
          k = 1;
          localObject3 = localObject2;
          break;
        case 23: 
          label1358:
          label1413:
          label1418:
          label1433:
          label1453:
          label1480:
          bool1 = true;
        }
      }
    }
    
    public static void ˊ(long paramLong, String paramString, boolean paramBoolean)
    {
      ˊ(new ahy(null, paramString, paramLong, paramBoolean), new Void[0]);
    }
    
    /* Error */
    public static void ˊ(Context paramContext)
    {
      // Byte code:
      //   0: goto +185 -> 185
      //   3: iconst_1
      //   4: istore_1
      //   5: iload_1
      //   6: tableswitch	default:+22->28, 0:+269->275, 1:+231->237
      //   28: goto +39 -> 67
      //   31: iconst_0
      //   32: istore_1
      //   33: goto +171 -> 204
      //   36: astore_0
      //   37: aload_0
      //   38: athrow
      //   39: iload_1
      //   40: tableswitch	default:+24->64, 0:+235->275, 1:+502->542
      //   64: goto +135 -> 199
      //   67: iconst_0
      //   68: istore_1
      //   69: goto -64 -> 5
      //   72: aload 6
      //   74: astore_0
      //   75: aload 7
      //   77: astore 5
      //   79: iload_1
      //   80: tableswitch	default:+24->104, 0:+280->360, 1:+195->275
      //   104: goto +222 -> 326
      //   107: iconst_1
      //   108: istore_1
      //   109: goto +342 -> 451
      //   112: iconst_1
      //   113: istore_1
      //   114: goto +90 -> 204
      //   117: aload_0
      //   118: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   121: ldc_w 501
      //   124: invokevirtual 507	android/content/res/Resources:getColor	(I)I
      //   127: istore_2
      //   128: aload_0
      //   129: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   132: ldc_w 509
      //   135: ldc_w 511
      //   138: iconst_0
      //   139: iconst_0
      //   140: iconst_0
      //   141: invokestatic 513	o/aip$aUX:ˊ	(ISB)Ljava/lang/String;
      //   144: invokevirtual 516	java/lang/String:intern	()Ljava/lang/String;
      //   147: invokevirtual 520	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
      //   150: istore_3
      //   151: aload_0
      //   152: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   155: ldc_w 522
      //   158: ldc_w 511
      //   161: iconst_0
      //   162: iconst_0
      //   163: iconst_0
      //   164: invokestatic 513	o/aip$aUX:ˊ	(ISB)Ljava/lang/String;
      //   167: invokevirtual 516	java/lang/String:intern	()Ljava/lang/String;
      //   170: invokevirtual 520	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
      //   173: istore 4
      //   175: iload_3
      //   176: ifle +6 -> 182
      //   179: goto +224 -> 403
      //   182: goto +100 -> 282
      //   185: getstatic 527	android/os/Build$VERSION:SDK_INT	I
      //   188: bipush 21
      //   190: if_icmpge +6 -> 196
      //   193: goto +138 -> 331
      //   196: goto +35 -> 231
      //   199: iconst_1
      //   200: istore_1
      //   201: goto -162 -> 39
      //   204: iload_1
      //   205: tableswitch	default:+23->228, 0:+306->511, 1:+137->342
      //   228: goto -116 -> 112
      //   231: bipush 90
      //   233: istore_1
      //   234: goto +335 -> 569
      //   237: getstatic 20	o/aip$aUX:ˏ	I
      //   240: bipush 51
      //   242: iadd
      //   243: istore_1
      //   244: iload_1
      //   245: sipush 128
      //   248: irem
      //   249: putstatic 22	o/aip$aUX:ᐝ	I
      //   252: iload_1
      //   253: iconst_2
      //   254: irem
      //   255: ifne +6 -> 261
      //   258: goto -227 -> 31
      //   261: goto -149 -> 112
      //   264: bipush 58
      //   266: istore_1
      //   267: goto +145 -> 412
      //   270: iconst_0
      //   271: istore_1
      //   272: goto +179 -> 451
      //   275: return
      //   276: bipush 19
      //   278: istore_1
      //   279: goto +133 -> 412
      //   282: bipush 82
      //   284: istore_1
      //   285: goto +86 -> 371
      //   288: aload_0
      //   289: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   292: astore 5
      //   294: aload 5
      //   296: iload 4
      //   298: invokevirtual 531	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
      //   301: astore 6
      //   303: aload_0
      //   304: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   307: astore_0
      //   308: aload_0
      //   309: iload_3
      //   310: invokevirtual 531	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
      //   313: astore 7
      //   315: aload 6
      //   317: ifnull +6 -> 323
      //   320: goto -44 -> 276
      //   323: goto -59 -> 264
      //   326: iconst_0
      //   327: istore_1
      //   328: goto -256 -> 72
      //   331: bipush 61
      //   333: istore_1
      //   334: goto +235 -> 569
      //   337: iconst_0
      //   338: istore_1
      //   339: goto -300 -> 39
      //   342: aload 5
      //   344: iload_2
      //   345: getstatic 537	android/graphics/PorterDuff$Mode:SRC_IN	Landroid/graphics/PorterDuff$Mode;
      //   348: invokevirtual 543	android/graphics/drawable/Drawable:setColorFilter	(ILandroid/graphics/PorterDuff$Mode;)V
      //   351: aload_0
      //   352: iload_2
      //   353: getstatic 537	android/graphics/PorterDuff$Mode:SRC_IN	Landroid/graphics/PorterDuff$Mode;
      //   356: invokevirtual 543	android/graphics/drawable/Drawable:setColorFilter	(ILandroid/graphics/PorterDuff$Mode;)V
      //   359: return
      //   360: aload 5
      //   362: ifnull +6 -> 368
      //   365: goto -362 -> 3
      //   368: goto -301 -> 67
      //   371: iload_1
      //   372: lookupswitch	default:+28->400, 71:+227->599, 82:+-97->275
      //   400: goto -118 -> 282
      //   403: bipush 71
      //   405: istore_1
      //   406: goto -35 -> 371
      //   409: astore_0
      //   410: aload_0
      //   411: athrow
      //   412: aload 6
      //   414: astore_0
      //   415: aload 7
      //   417: astore 5
      //   419: iload_1
      //   420: lookupswitch	default:+28->448, 19:+-60->360, 58:+-145->275
      //   448: goto -172 -> 276
      //   451: iload_1
      //   452: tableswitch	default:+24->476, 0:+27->479, 1:+-164->288
      //   476: goto -369 -> 107
      //   479: aload_0
      //   480: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   483: iload 4
      //   485: invokevirtual 531	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
      //   488: astore 6
      //   490: aload_0
      //   491: invokevirtual 500	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   494: iload_3
      //   495: invokevirtual 531	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
      //   498: astore 7
      //   500: aload 6
      //   502: ifnull +6 -> 508
      //   505: goto -179 -> 326
      //   508: goto +29 -> 537
      //   511: getstatic 537	android/graphics/PorterDuff$Mode:SRC_IN	Landroid/graphics/PorterDuff$Mode;
      //   514: astore 6
      //   516: aload 5
      //   518: iload_2
      //   519: aload 6
      //   521: invokevirtual 543	android/graphics/drawable/Drawable:setColorFilter	(ILandroid/graphics/PorterDuff$Mode;)V
      //   524: getstatic 537	android/graphics/PorterDuff$Mode:SRC_IN	Landroid/graphics/PorterDuff$Mode;
      //   527: astore 5
      //   529: aload_0
      //   530: iload_2
      //   531: aload 5
      //   533: invokevirtual 543	android/graphics/drawable/Drawable:setColorFilter	(ILandroid/graphics/PorterDuff$Mode;)V
      //   536: return
      //   537: iconst_1
      //   538: istore_1
      //   539: goto -467 -> 72
      //   542: getstatic 20	o/aip$aUX:ˏ	I
      //   545: bipush 125
      //   547: iadd
      //   548: istore_1
      //   549: iload_1
      //   550: sipush 128
      //   553: irem
      //   554: putstatic 22	o/aip$aUX:ᐝ	I
      //   557: iload_1
      //   558: iconst_2
      //   559: irem
      //   560: ifne +6 -> 566
      //   563: goto -456 -> 107
      //   566: goto -296 -> 270
      //   569: iload_1
      //   570: lookupswitch	default:+26->596, 61:+-453->117, 90:+-295->275
      //   596: goto -365 -> 231
      //   599: iload 4
      //   601: ifle +6 -> 607
      //   604: goto -405 -> 199
      //   607: goto -270 -> 337
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	610	0	paramContext	Context
      //   4	566	1	i	int
      //   127	404	2	j	int
      //   150	345	3	k	int
      //   173	427	4	m	int
      //   77	455	5	localObject1	Object
      //   72	448	6	localObject2	Object
      //   75	424	7	localDrawable	android.graphics.drawable.Drawable
      // Exception table:
      //   from	to	target	type
      //   288	294	36	java/lang/Exception
      //   524	529	36	java/lang/Exception
      //   294	303	409	java/lang/Exception
      //   303	308	409	java/lang/Exception
      //   308	315	409	java/lang/Exception
      //   511	516	409	java/lang/Exception
      //   516	524	409	java/lang/Exception
      //   529	536	409	java/lang/Exception
    }
    
    public static void ˊ(SharedPreferences.Editor paramEditor, String paramString, Integer paramInteger)
    {
      for (;;)
      {
        int i;
        try
        {
          i = ˏ + 73;
          try
          {
            ᐝ = i % 128;
            if (i % 2 == 0) {
              continue;
            }
          }
          catch (Exception paramEditor)
          {
            throw paramEditor;
          }
          i = 24;
          continue;
          paramEditor.putInt(paramString, paramInteger.intValue());
          return;
          i = paramInteger.intValue();
          paramEditor.putInt(paramString, i);
          return;
          i = 47;
          switch (i)
          {
          }
          continue;
          i = 1;
          switch (i)
          {
          }
        }
        catch (Exception paramEditor)
        {
          throw paramEditor;
        }
        if (paramInteger == null)
        {
          continue;
          i = 0;
        }
      }
    }
    
    public static void ˊ(SharedPreferences.Editor paramEditor, String paramString, Long paramLong)
    {
      break label33;
      try
      {
        long l = paramLong.longValue();
        paramEditor.putLong(paramString, l);
        return;
      }
      catch (Exception paramEditor)
      {
        throw paramEditor;
      }
      int i = 1;
      for (;;)
      {
        i = 0;
        break label44;
        label33:
        if (paramLong != null) {
          break;
        }
        continue;
        return;
        label44:
        switch (i)
        {
        }
      }
    }
    
    @SuppressLint({"NewApi"})
    public static <Params> void ˊ(AsyncTask<Params, ?, ?> paramAsyncTask, Params... paramVarArgs)
    {
      for (;;)
      {
        int i = 1;
        break label114;
        for (;;)
        {
          i = 1;
          break label25;
          paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
          return;
          label25:
          do
          {
            i = 0;
            switch (i)
            {
            default: 
              break;
            case 0: 
              for (;;)
              {
                i = 0;
                break label114;
                i = ˏ + 27;
                ᐝ = i % 128;
                if (i % 2 == 0) {
                  break;
                }
              }
            case 1: 
              paramAsyncTask.execute(paramVarArgs);
              return;
            }
          } while (Build.VERSION.SDK_INT >= 11);
        }
        paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
        return;
        label114:
        switch (i)
        {
        }
      }
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, Message paramMessage)
    {
      break label400;
      Object localObject2 = "";
      paramMessage = (Message)localObject1;
      Object localObject1 = localObject2;
      Object localObject3;
      int i1;
      ((StringBuilder)localObject3).append((String)localObject1).append(paramFragmentActivity.getString(i1, new Object[] { TextUtils.join(",", paramMessage) }));
      int j;
      int k = j;
      for (;;)
      {
        label50:
        k = 3;
        break label535;
        return;
        localObject1 = "\n\n";
        j = k;
        break;
        label68:
        int i = 0;
        break label510;
        label73:
        int m = 18;
        break label91;
        label80:
        m = 0;
        break label723;
        label86:
        j = 0;
        break label571;
        for (;;)
        {
          label91:
          j = i;
          localObject1 = localObject2;
          k = i;
          paramMessage = (Message)localObject2;
          switch (m)
          {
          }
          break label140;
          label135:
          j = 0;
          break;
          label140:
          m = 5;
        }
        label146:
        i = j;
        switch (k)
        {
        default: 
          break;
        }
        try
        {
          boolean bool = TextUtils.isEmpty((CharSequence)localObject3);
          if (!bool) {
            break label68;
          }
          break label712;
          label193:
          k = 58;
          j = i;
          break label628;
          localObject2 = aip.aux.ˊ(paramMessage);
          ArrayList localArrayList;
          localArrayList.addAll(Arrays.asList((Object[])localObject2));
          if (TextUtils.isEmpty((CharSequence)localObject3)) {
            break label80;
          }
          break label706;
          for (;;)
          {
            k = 0;
            break label146;
            paramMessage = aim.ˊ(((StringBuilder)localObject3).toString(), null);
            paramMessage.getArguments().putCharSequenceArrayList("rejected_members_key", localArrayList);
            aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), paramMessage, "rejected_members_dialog");
            return;
            k = i;
            label303:
            Pair[] arrayOfPair;
            switch (j)
            {
            default: 
              break;
            case 1: 
            case 0: 
              do
              {
                j = 1;
                break;
                j = k + 1;
                break label677;
                j = ˏ + 19;
                ᐝ = j % 128;
              } while (j % 2 == 0);
              break label86;
              localObject1 = arrayOfPair[i];
            }
            label400:
            int n;
            label510:
            label535:
            label571:
            label628:
            label665:
            do
            {
              for (;;)
              {
                try
                {
                  paramMessage = ((Pair)localObject1).first;
                  paramMessage = (List)paramMessage;
                  localObject1 = ((Pair)localObject1).second;
                  localObject1 = (Integer)localObject1;
                  i1 = ((Integer)localObject1).intValue();
                  bool = paramMessage.isEmpty();
                  if (!bool) {
                    break label135;
                  }
                  continue;
                  paramMessage = (afm.if)paramMessage.obj;
                  arrayOfPair = new Pair[4];
                  arrayOfPair[0] = Pair.create(paramMessage.ˎ(), Integer.valueOf(2131231628));
                  arrayOfPair[1] = Pair.create(paramMessage.ᐝ(), Integer.valueOf(2131231630));
                  arrayOfPair[2] = Pair.create(paramMessage.ʻ(), Integer.valueOf(2131231629));
                  arrayOfPair[3] = Pair.create(paramMessage.ʼ(), Integer.valueOf(2131231631));
                  localObject3 = new StringBuilder();
                  localArrayList = new ArrayList();
                  n = arrayOfPair.length;
                  i = 0;
                  break label665;
                  switch (i)
                  {
                  }
                  break label68;
                  i = j;
                  switch (k)
                  {
                  }
                  k = 89;
                  continue;
                  switch (j)
                  {
                  case 1: 
                  default: 
                    break label303;
                    if (j < n) {
                      continue;
                    }
                    break label50;
                    j = 1;
                  }
                }
                catch (Exception paramFragmentActivity)
                {
                  throw paramFragmentActivity;
                }
                k = 16;
                j = i;
                i = j;
                switch (k)
                {
                }
                i = j;
              }
            } while (i < n);
            break label193;
            label677:
            i = ˏ;
            i += 97;
            ᐝ = i % 128;
            if (i % 2 == 0) {
              break label717;
            }
          }
          for (;;)
          {
            label706:
            m = 1;
            break label723;
            label712:
            i = 1;
            break label510;
            label717:
            k = 1;
            break;
            label723:
            j = i;
            localObject1 = localObject2;
            k = i;
            paramMessage = (Message)localObject2;
            switch (m)
            {
            }
          }
          localObject2 = aip.aux.ˊ(paramMessage);
          localArrayList.addAll(Arrays.asList((Object[])localObject2));
          if (TextUtils.isEmpty((CharSequence)localObject3)) {
            break label73;
          }
        }
        catch (Exception paramFragmentActivity)
        {
          throw paramFragmentActivity;
        }
      }
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, String paramString)
    {
      aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), "invite_dialog");
    }
    
    public static void ˊ(FragmentActivity paramFragmentActivity, List<String> paramList, afd.if paramIf, String paramString1, String paramString2)
    {
      aim.ˊ(paramFragmentActivity.getSupportFragmentManager(), aim.ˊ(paramFragmentActivity), "invite_dialog");
      new afd(paramList, paramIf, paramString1, paramString2).ʿ();
    }
    
    public static void ˊ(Runnable paramRunnable, String paramString)
    {
      ˊ(paramRunnable, false, paramString);
    }
    
    /* Error */
    public static void ˊ(Runnable paramRunnable, boolean paramBoolean, String paramString)
    {
      // Byte code:
      //   0: goto +134 -> 134
      //   3: new 695	java/util/Random
      //   6: dup
      //   7: invokespecial 696	java/util/Random:<init>	()V
      //   10: bipush 10
      //   12: invokevirtual 699	java/util/Random:nextInt	(I)I
      //   15: iconst_1
      //   16: iadd
      //   17: bipush 10
      //   19: imul
      //   20: i2l
      //   21: lstore 4
      //   23: invokestatic 102	o/PW:ˋ	()Lo/PW;
      //   26: new 49	java/lang/StringBuilder
      //   29: dup
      //   30: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   33: ldc_w 701
      //   36: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: aload_0
      //   40: invokevirtual 706	java/lang/Thread:getId	()J
      //   43: invokevirtual 709	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   46: ldc_w 422
      //   49: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: aload_2
      //   53: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   56: ldc_w 711
      //   59: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   62: lload 4
      //   64: invokevirtual 709	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   67: ldc_w 713
      //   70: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   76: invokevirtual 109	o/PW:info	(Ljava/lang/String;)V
      //   79: invokestatic 719	com/pinger/textfree/call/app/TFApplication:ˍ	()Lcom/pinger/textfree/call/app/TFApplication;
      //   82: invokevirtual 722	com/pinger/textfree/call/app/TFApplication:ᐝ	()Landroid/os/Handler;
      //   85: new 724	o/aiy
      //   88: dup
      //   89: aload_0
      //   90: invokespecial 727	o/aiy:<init>	(Ljava/lang/Thread;)V
      //   93: lload 4
      //   95: invokevirtual 733	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
      //   98: pop
      //   99: return
      //   100: astore_0
      //   101: aload_0
      //   102: athrow
      //   103: aload_0
      //   104: iload_3
      //   105: invokevirtual 737	java/lang/Thread:setPriority	(I)V
      //   108: iload_1
      //   109: ifeq +6 -> 115
      //   112: goto -109 -> 3
      //   115: goto +32 -> 147
      //   118: aload_2
      //   119: astore 6
      //   121: goto +116 -> 237
      //   124: astore_0
      //   125: aload_0
      //   126: athrow
      //   127: ldc -87
      //   129: astore 6
      //   131: goto +106 -> 237
      //   134: aload_2
      //   135: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   138: ifne +6 -> 144
      //   141: goto -23 -> 118
      //   144: goto -17 -> 127
      //   147: invokestatic 102	o/PW:ˋ	()Lo/PW;
      //   150: astore 6
      //   152: new 49	java/lang/StringBuilder
      //   155: dup
      //   156: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   159: astore 7
      //   161: aload 7
      //   163: ldc_w 739
      //   166: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   169: astore 7
      //   171: aload_0
      //   172: invokevirtual 706	java/lang/Thread:getId	()J
      //   175: lstore 4
      //   177: aload 7
      //   179: lload 4
      //   181: invokevirtual 709	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   184: astore 7
      //   186: aload 7
      //   188: ldc_w 422
      //   191: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   194: astore 7
      //   196: aload 7
      //   198: aload_2
      //   199: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   202: astore_2
      //   203: aload_2
      //   204: ldc_w 424
      //   207: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   210: astore_2
      //   211: aload_2
      //   212: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   215: astore_2
      //   216: aload 6
      //   218: aload_2
      //   219: invokevirtual 109	o/PW:info	(Ljava/lang/String;)V
      //   222: aload_0
      //   223: invokevirtual 741	java/lang/Thread:start	()V
      //   226: return
      //   227: iconst_1
      //   228: istore_3
      //   229: goto -126 -> 103
      //   232: iconst_5
      //   233: istore_3
      //   234: goto -131 -> 103
      //   237: new 703	java/lang/Thread
      //   240: dup
      //   241: aload_0
      //   242: aload 6
      //   244: invokespecial 743	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
      //   247: astore_0
      //   248: iload_1
      //   249: ifeq +6 -> 255
      //   252: goto -25 -> 227
      //   255: goto -23 -> 232
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	258	0	paramRunnable	Runnable
      //   0	258	1	paramBoolean	boolean
      //   0	258	2	paramString	String
      //   104	130	3	i	int
      //   21	159	4	l	long
      //   119	124	6	localObject	Object
      //   159	38	7	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   152	161	100	java/lang/Exception
      //   161	171	100	java/lang/Exception
      //   171	177	100	java/lang/Exception
      //   177	186	100	java/lang/Exception
      //   186	196	100	java/lang/Exception
      //   196	203	100	java/lang/Exception
      //   203	211	100	java/lang/Exception
      //   211	216	100	java/lang/Exception
      //   216	222	100	java/lang/Exception
      //   222	226	100	java/lang/Exception
      //   147	152	124	java/lang/Exception
      //   237	248	124	java/lang/Exception
    }
    
    /* Error */
    public static void ˊ(String paramString1, String paramString2)
    {
      // Byte code:
      //   0: goto +134 -> 134
      //   3: iconst_1
      //   4: istore_2
      //   5: goto +8 -> 13
      //   8: iconst_0
      //   9: istore_2
      //   10: goto +29 -> 39
      //   13: iload_2
      //   14: tableswitch	default:+22->36, 0:+151->165, 1:+58->72
      //   36: goto +90 -> 126
      //   39: iload_2
      //   40: tableswitch	default:+24->64, 0:+27->67, 1:+120->160
      //   64: goto -56 -> 8
      //   67: aload_1
      //   68: invokestatic 749	com/pinger/common/logger/ServerLogger:sendLogsToServer	(Ljava/lang/String;)V
      //   71: return
      //   72: invokestatic 102	o/PW:ˋ	()Lo/PW;
      //   75: new 49	java/lang/StringBuilder
      //   78: dup
      //   79: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   82: aload_0
      //   83: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   86: ldc_w 751
      //   89: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   92: aload_1
      //   93: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: ldc_w 753
      //   99: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   102: ldc2_w 754
      //   105: lload_3
      //   106: ladd
      //   107: invokestatic 760	o/ail:ˋ	(J)Ljava/lang/String;
      //   110: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   113: ldc_w 762
      //   116: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   119: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   122: invokevirtual 109	o/PW:info	(Ljava/lang/String;)V
      //   125: return
      //   126: iconst_0
      //   127: istore_2
      //   128: goto -115 -> 13
      //   131: astore_0
      //   132: aload_0
      //   133: athrow
      //   134: invokestatic 767	com/pinger/common/store/Preferences$iF:ᵔ	()J
      //   137: lstore_3
      //   138: invokestatic 772	java/lang/System:currentTimeMillis	()J
      //   141: lstore 5
      //   143: lload 5
      //   145: lload_3
      //   146: lsub
      //   147: ldc2_w 754
      //   150: lcmp
      //   151: iflt +6 -> 157
      //   154: goto -28 -> 126
      //   157: goto -154 -> 3
      //   160: aload_1
      //   161: invokestatic 749	com/pinger/common/logger/ServerLogger:sendLogsToServer	(Ljava/lang/String;)V
      //   164: return
      //   165: getstatic 22	o/aip$aUX:ᐝ	I
      //   168: bipush 79
      //   170: iadd
      //   171: istore_2
      //   172: iload_2
      //   173: sipush 128
      //   176: irem
      //   177: putstatic 20	o/aip$aUX:ˏ	I
      //   180: iload_2
      //   181: iconst_2
      //   182: irem
      //   183: ifeq +6 -> 189
      //   186: goto -178 -> 8
      //   189: iconst_1
      //   190: istore_2
      //   191: goto -152 -> 39
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	194	0	paramString1	String
      //   0	194	1	paramString2	String
      //   4	187	2	i	int
      //   105	1	3	localObject	Object
      //   137	9	3	l1	long
      //   141	3	5	l2	long
      // Exception table:
      //   from	to	target	type
      //   134	138	131	java/lang/Exception
      //   138	143	131	java/lang/Exception
    }
    
    public static void ˊ(String paramString1, String paramString2, boolean paramBoolean)
    {
      ˊ(new ahy(paramString1, paramString2, -1L, paramBoolean), new Void[0]);
    }
    
    public static void ˊ(boolean paramBoolean)
    {
      break label48;
      int i = 47;
      switch (i)
      {
      default: 
        break;
      case 47: 
        label6:
        Preferences.iF.ˊ(System.currentTimeMillis());
        return;
        for (;;)
        {
          i = 52;
          break label6;
          label48:
          if (paramBoolean) {
            break;
          }
        }
      }
      Preferences.iF.ˊ(-1L);
    }
    
    @TargetApi(21)
    public static boolean ˊ(Runnable paramRunnable)
    {
      break label87;
      label3:
      int i = 32;
      break label135;
      return false;
      switch (i)
      {
      default: 
        break label214;
        i = 0;
        break;
      case 1: 
        label11:
        label39:
        paramRunnable.run();
        return true;
      }
      for (;;)
      {
        try
        {
          i = ᐝ;
          i += 77;
          ˏ = i % 128;
          if (i % 2 == 0)
          {
            break label214;
            i = 38;
            continue;
            label87:
            if (paramRunnable != null) {
              break label307;
            }
            continue;
          }
          switch (i)
          {
          case 38: 
          default: 
            break label307;
            i = 1;
            break label233;
            i = 35;
            label135:
            switch (i)
            {
            }
            continue;
            i = 1;
          }
        }
        catch (Exception paramRunnable)
        {
          label172:
          label177:
          throw paramRunnable;
        }
        i = 0;
        break label233;
        switch (i)
        {
        case 0: 
        default: 
          break;
        case 1: 
          paramRunnable.run();
          return true;
          label214:
          i = 0;
          break label11;
          if (Build.VERSION.SDK_INT >= 21) {
            break label3;
          }
          break;
        }
        switch (i)
        {
        case 0: 
        default: 
          label233:
          break;
        }
        do
        {
          i = 1;
          break label177;
          i = ᐝ + 85;
          ˏ = i % 128;
          if (i % 2 != 0) {
            break;
          }
          break label172;
          i = Build.VERSION.SDK_INT;
        } while (i >= 21);
        break label39;
        label307:
        i = 81;
      }
    }
    
    public static byte ˋ(String paramString)
    {
      label51:
      int i;
      if (!"phone".equals(paramString))
      {
        break label207;
        if (!"userId".equals(paramString))
        {
          break label138;
          if (!"email".equals(paramString)) {
            break label172;
          }
          i = 1;
          break label83;
          return 3;
        }
        for (;;)
        {
          switch (i)
          {
          default: 
            switch (i)
            {
            default: 
              label83:
              break label51;
              i = 1;
              break label180;
              label116:
              i = 1;
            }
            break;
          }
        }
        return 1;
        return 1;
      }
      for (;;)
      {
        i = 13;
        break label210;
        return 2;
        i = 0;
        break;
        for (;;)
        {
          label138:
          i = 0;
          break label180;
          try
          {
            i = ˏ;
            i += 49;
            ᐝ = i % 128;
            if (i % 2 == 0) {
              break label116;
            }
          }
          catch (Exception paramString)
          {
            label172:
            throw paramString;
          }
          i = 0;
          break;
          label180:
          switch (i)
          {
          }
        }
        label207:
        i = 82;
        label210:
        switch (i)
        {
        }
      }
      ν.ˊ(Ч.ˊ, "Invalid address type: " + paramString);
      return 0;
    }
    
    public static Long ˋ(SharedPreferences paramSharedPreferences, String paramString)
    {
      for (;;)
      {
        int i = 20;
        switch (i)
        {
        default: 
          break;
        case 20: 
          i = ˏ + 9;
          ᐝ = i % 128;
          if (i % 2 == 0) {}
          break;
        case 41: 
          return null;
        }
        for (;;)
        {
          i = 1;
          break label92;
          return Long.valueOf(paramSharedPreferences.getLong(paramString, 0L));
          i = 0;
          break label92;
          i = 41;
          break;
          label92:
          switch (i)
          {
          }
        }
        return Long.valueOf(paramSharedPreferences.getLong(paramString, 0L));
        try
        {
          boolean bool = paramSharedPreferences.contains(paramString);
          if (bool) {}
        }
        catch (Exception paramSharedPreferences)
        {
          throw paramSharedPreferences;
        }
      }
    }
    
    /* Error */
    public static String ˋ()
    {
      // Byte code:
      //   0: goto +151 -> 151
      //   3: iload_0
      //   4: tableswitch	default:+24->28, 0:+30->34, 1:+73->77
      //   28: goto +44 -> 72
      //   31: astore_1
      //   32: aload_1
      //   33: athrow
      //   34: new 49	java/lang/StringBuilder
      //   37: dup
      //   38: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   41: aload_1
      //   42: invokestatic 811	o/aip$ˌ:ˊ	(Ljava/lang/String;)Ljava/lang/String;
      //   45: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   48: ldc_w 751
      //   51: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   54: aload_2
      //   55: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   58: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   61: areturn
      //   62: aload_2
      //   63: invokestatic 811	o/aip$ˌ:ˊ	(Ljava/lang/String;)Ljava/lang/String;
      //   66: areturn
      //   67: iconst_0
      //   68: istore_0
      //   69: goto +47 -> 116
      //   72: iconst_0
      //   73: istore_0
      //   74: goto -71 -> 3
      //   77: getstatic 20	o/aip$aUX:ˏ	I
      //   80: istore_0
      //   81: iload_0
      //   82: bipush 11
      //   84: iadd
      //   85: istore_0
      //   86: iload_0
      //   87: sipush 128
      //   90: irem
      //   91: putstatic 22	o/aip$aUX:ᐝ	I
      //   94: iload_0
      //   95: iconst_2
      //   96: irem
      //   97: ifne +6 -> 103
      //   100: goto +43 -> 143
      //   103: goto -36 -> 67
      //   106: aload_2
      //   107: invokestatic 811	o/aip$ˌ:ˊ	(Ljava/lang/String;)Ljava/lang/String;
      //   110: areturn
      //   111: iconst_1
      //   112: istore_0
      //   113: goto -110 -> 3
      //   116: iload_0
      //   117: tableswitch	default:+23->140, 0:+-55->62, 1:+-11->106
      //   140: goto -73 -> 67
      //   143: iconst_1
      //   144: istore_0
      //   145: goto -29 -> 116
      //   148: astore_1
      //   149: aload_1
      //   150: athrow
      //   151: getstatic 817	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   154: astore_1
      //   155: getstatic 820	android/os/Build:MODEL	Ljava/lang/String;
      //   158: astore_2
      //   159: aload_2
      //   160: aload_1
      //   161: invokevirtual 77	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   164: ifeq +6 -> 170
      //   167: goto -56 -> 111
      //   170: goto -98 -> 72
      // Local variable table:
      //   start	length	slot	name	signature
      //   3	142	0	i	int
      //   31	11	1	localException1	Exception
      //   148	2	1	localException2	Exception
      //   154	7	1	str1	String
      //   54	106	2	str2	String
      // Exception table:
      //   from	to	target	type
      //   86	94	31	java/lang/Exception
      //   77	81	148	java/lang/Exception
    }
    
    /* Error */
    public static String ˋ(Context paramContext)
    {
      // Byte code:
      //   0: goto +14 -> 14
      //   3: astore_0
      //   4: aload_0
      //   5: athrow
      //   6: iconst_0
      //   7: istore_1
      //   8: goto +42 -> 50
      //   11: ldc -87
      //   13: areturn
      //   14: aload_0
      //   15: ldc_w 790
      //   18: invokevirtual 823	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   21: astore_0
      //   22: aload_0
      //   23: checkcast 825	android/telephony/TelephonyManager
      //   26: astore_0
      //   27: aload_0
      //   28: invokevirtual 828	android/telephony/TelephonyManager:getSimCountryIso	()Ljava/lang/String;
      //   31: astore_0
      //   32: aload_0
      //   33: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   36: istore_2
      //   37: iload_2
      //   38: ifeq +6 -> 44
      //   41: goto -35 -> 6
      //   44: goto +31 -> 75
      //   47: astore_0
      //   48: aload_0
      //   49: athrow
      //   50: iload_1
      //   51: tableswitch	default:+21->72, 0:+-40->11, 1:+29->80
      //   72: goto -66 -> 6
      //   75: iconst_1
      //   76: istore_1
      //   77: goto -27 -> 50
      //   80: aload_0
      //   81: invokevirtual 358	java/lang/String:toUpperCase	()Ljava/lang/String;
      //   84: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	85	0	paramContext	Context
      //   7	70	1	i	int
      //   36	2	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   14	22	3	java/lang/Exception
      //   14	22	47	java/lang/Exception
      //   22	27	47	java/lang/Exception
      //   27	32	47	java/lang/Exception
      //   32	37	47	java/lang/Exception
    }
    
    /* Error */
    public static String ˋ(String paramString1, String paramString2)
    {
      // Byte code:
      //   0: goto +265 -> 265
      //   3: getstatic 22	o/aip$aUX:ᐝ	I
      //   6: bipush 41
      //   8: iadd
      //   9: istore_2
      //   10: iload_2
      //   11: sipush 128
      //   14: irem
      //   15: putstatic 20	o/aip$aUX:ˏ	I
      //   18: iload_2
      //   19: iconst_2
      //   20: irem
      //   21: ifeq +6 -> 27
      //   24: goto +231 -> 255
      //   27: goto +233 -> 260
      //   30: invokestatic 719	com/pinger/textfree/call/app/TFApplication:ˍ	()Lcom/pinger/textfree/call/app/TFApplication;
      //   33: invokevirtual 830	com/pinger/textfree/call/app/TFApplication:getResources	()Landroid/content/res/Resources;
      //   36: astore_0
      //   37: aload_1
      //   38: astore 6
      //   40: aload_0
      //   41: astore 7
      //   43: aload_1
      //   44: astore 8
      //   46: aload_0
      //   47: astore 9
      //   49: aload_1
      //   50: astore 5
      //   52: aload_0
      //   53: astore 4
      //   55: getstatic 835	o/aiq:ˎ	[I
      //   58: aload 10
      //   60: invokevirtual 840	com/pinger/textfree/call/ui/ConversationMediaContainer$if:ordinal	()I
      //   63: iaload
      //   64: tableswitch	default:+28->92, 1:+553->617, 2:+34->98, 3:+421->485
      //   92: aload_1
      //   93: astore 4
      //   95: goto +95 -> 190
      //   98: new 49	java/lang/StringBuilder
      //   101: dup
      //   102: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   105: aload 6
      //   107: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   110: aload 7
      //   112: ldc_w 841
      //   115: invokevirtual 842	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   118: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   121: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   124: areturn
      //   125: astore_0
      //   126: aload_0
      //   127: athrow
      //   128: aload_1
      //   129: astore_0
      //   130: iload_2
      //   131: tableswitch	default:+21->152, 0:+-101->30, 1:+154->285
      //   152: goto +108 -> 260
      //   155: iconst_0
      //   156: istore_2
      //   157: goto +384 -> 541
      //   160: astore_0
      //   161: aload_0
      //   162: athrow
      //   163: getstatic 20	o/aip$aUX:ˏ	I
      //   166: bipush 65
      //   168: iadd
      //   169: istore_2
      //   170: iload_2
      //   171: sipush 128
      //   174: irem
      //   175: putstatic 22	o/aip$aUX:ᐝ	I
      //   178: iload_2
      //   179: iconst_2
      //   180: irem
      //   181: ifne +6 -> 187
      //   184: goto -29 -> 155
      //   187: goto +93 -> 280
      //   190: new 49	java/lang/StringBuilder
      //   193: dup
      //   194: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   197: astore_1
      //   198: aload_1
      //   199: aload 4
      //   201: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   204: astore_1
      //   205: aload_0
      //   206: ldc_w 843
      //   209: invokevirtual 842	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   212: astore_0
      //   213: aload_1
      //   214: aload_0
      //   215: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   218: astore_0
      //   219: aload_0
      //   220: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   223: astore_0
      //   224: goto +136 -> 360
      //   227: iload_2
      //   228: tableswitch	default:+24->252, 0:+226->454, 1:+387->615
      //   252: goto +408 -> 660
      //   255: iconst_0
      //   256: istore_2
      //   257: goto -129 -> 128
      //   260: iconst_1
      //   261: istore_2
      //   262: goto -134 -> 128
      //   265: aload_1
      //   266: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   269: istore_3
      //   270: iload_3
      //   271: ifeq +6 -> 277
      //   274: goto +386 -> 660
      //   277: goto +198 -> 475
      //   280: iconst_1
      //   281: istore_2
      //   282: goto +259 -> 541
      //   285: invokestatic 719	com/pinger/textfree/call/app/TFApplication:ˍ	()Lcom/pinger/textfree/call/app/TFApplication;
      //   288: astore_1
      //   289: aload_1
      //   290: invokevirtual 830	com/pinger/textfree/call/app/TFApplication:getResources	()Landroid/content/res/Resources;
      //   293: astore_1
      //   294: getstatic 835	o/aiq:ˎ	[I
      //   297: astore 11
      //   299: aload 10
      //   301: invokevirtual 840	com/pinger/textfree/call/ui/ConversationMediaContainer$if:ordinal	()I
      //   304: istore_2
      //   305: aload_0
      //   306: astore 6
      //   308: aload_1
      //   309: astore 7
      //   311: aload_0
      //   312: astore 8
      //   314: aload_1
      //   315: astore 9
      //   317: aload_0
      //   318: astore 5
      //   320: aload_1
      //   321: astore 4
      //   323: aload 11
      //   325: iload_2
      //   326: iaload
      //   327: tableswitch	default:+25->352, 1:+290->617, 2:+-229->98, 3:+158->485
      //   352: aload_0
      //   353: astore 4
      //   355: aload_1
      //   356: astore_0
      //   357: goto -167 -> 190
      //   360: getstatic 22	o/aip$aUX:ᐝ	I
      //   363: bipush 119
      //   365: iadd
      //   366: istore_2
      //   367: iload_2
      //   368: sipush 128
      //   371: irem
      //   372: putstatic 20	o/aip$aUX:ˏ	I
      //   375: iload_2
      //   376: iconst_2
      //   377: irem
      //   378: ifeq +6 -> 384
      //   381: goto +99 -> 480
      //   384: goto +226 -> 610
      //   387: invokestatic 719	com/pinger/textfree/call/app/TFApplication:ˍ	()Lcom/pinger/textfree/call/app/TFApplication;
      //   390: invokevirtual 830	com/pinger/textfree/call/app/TFApplication:getResources	()Landroid/content/res/Resources;
      //   393: astore_0
      //   394: aload_1
      //   395: astore 6
      //   397: aload_0
      //   398: astore 7
      //   400: aload_1
      //   401: astore 8
      //   403: aload_0
      //   404: astore 9
      //   406: aload_1
      //   407: astore 5
      //   409: aload_0
      //   410: astore 4
      //   412: getstatic 835	o/aiq:ˎ	[I
      //   415: aload 10
      //   417: invokevirtual 840	com/pinger/textfree/call/ui/ConversationMediaContainer$if:ordinal	()I
      //   420: iaload
      //   421: tableswitch	default:+27->448, 1:+196->617, 2:+-323->98, 3:+64->485
      //   448: aload_1
      //   449: astore 4
      //   451: goto -261 -> 190
      //   454: aload_1
      //   455: invokestatic 848	o/aip$AUx:ˊ	(Ljava/lang/String;)Lcom/pinger/textfree/call/ui/ConversationMediaContainer$if;
      //   458: astore 10
      //   460: aload_0
      //   461: ldc -125
      //   463: invokevirtual 850	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   466: ifeq +6 -> 472
      //   469: goto +104 -> 573
      //   472: goto +182 -> 654
      //   475: iconst_0
      //   476: istore_2
      //   477: goto -250 -> 227
      //   480: iconst_0
      //   481: istore_2
      //   482: goto +30 -> 512
      //   485: new 49	java/lang/StringBuilder
      //   488: dup
      //   489: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   492: aload 8
      //   494: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   497: aload 9
      //   499: ldc_w 851
      //   502: invokevirtual 842	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   505: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   508: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   511: areturn
      //   512: iload_2
      //   513: tableswitch	default:+23->536, 0:+26->539, 1:+58->571
      //   536: goto -56 -> 480
      //   539: aload_0
      //   540: areturn
      //   541: aload_1
      //   542: astore_0
      //   543: iload_2
      //   544: tableswitch	default:+24->568, 0:+-157->387, 1:+-259->285
      //   568: goto -413 -> 155
      //   571: aload_0
      //   572: areturn
      //   573: new 49	java/lang/StringBuilder
      //   576: dup
      //   577: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   580: aload_0
      //   581: iconst_0
      //   582: aload_0
      //   583: ldc -125
      //   585: invokevirtual 135	java/lang/String:indexOf	(Ljava/lang/String;)I
      //   588: invokevirtual 139	java/lang/String:substring	(II)Ljava/lang/String;
      //   591: invokestatic 854	o/aip$auX:ˊ	(Ljava/lang/String;)Ljava/lang/String;
      //   594: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   597: ldc_w 856
      //   600: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   603: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   606: astore_1
      //   607: goto -444 -> 163
      //   610: iconst_1
      //   611: istore_2
      //   612: goto -100 -> 512
      //   615: aload_0
      //   616: areturn
      //   617: new 49	java/lang/StringBuilder
      //   620: dup
      //   621: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   624: astore_0
      //   625: aload_0
      //   626: aload 5
      //   628: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   631: astore_0
      //   632: aload 4
      //   634: ldc_w 843
      //   637: invokevirtual 842	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   640: astore_1
      //   641: aload_0
      //   642: aload_1
      //   643: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   646: astore_0
      //   647: aload_0
      //   648: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   651: astore_0
      //   652: aload_0
      //   653: areturn
      //   654: ldc -87
      //   656: astore_1
      //   657: goto -654 -> 3
      //   660: iconst_1
      //   661: istore_2
      //   662: goto -435 -> 227
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	665	0	paramString1	String
      //   0	665	1	paramString2	String
      //   9	653	2	i	int
      //   269	2	3	bool	boolean
      //   53	580	4	str1	String
      //   50	577	5	str2	String
      //   38	358	6	str3	String
      //   41	358	7	str4	String
      //   44	449	8	str5	String
      //   47	451	9	str6	String
      //   58	401	10	localIf	com.pinger.textfree.call.ui.ConversationMediaContainer.if
      //   297	27	11	arrayOfInt	int[]
      // Exception table:
      //   from	to	target	type
      //   198	205	125	java/lang/Exception
      //   265	270	125	java/lang/Exception
      //   617	625	125	java/lang/Exception
      //   625	632	125	java/lang/Exception
      //   632	641	125	java/lang/Exception
      //   641	647	125	java/lang/Exception
      //   647	652	125	java/lang/Exception
      //   190	198	160	java/lang/Exception
      //   198	205	160	java/lang/Exception
      //   205	213	160	java/lang/Exception
      //   213	219	160	java/lang/Exception
      //   219	224	160	java/lang/Exception
      //   285	289	160	java/lang/Exception
      //   289	294	160	java/lang/Exception
      //   294	299	160	java/lang/Exception
      //   299	305	160	java/lang/Exception
    }
    
    public static boolean ˋ(Runnable paramRunnable)
    {
      break label51;
      return false;
      int i = 0;
      for (;;)
      {
        try
        {
          i = Build.VERSION.SDK_INT;
          if (i < 21) {
            break;
          }
          continue;
          switch (i)
          {
          case 1: 
          default: 
            continue;
            if (paramRunnable != null) {
              continue;
            }
            break;
          case 0: 
            label51:
            paramRunnable.run();
            return true;
          }
          i = 1;
          continue;
          switch (i)
          {
          }
          i = 1;
          continue;
          i = 0;
        }
        catch (Exception paramRunnable)
        {
          throw paramRunnable;
        }
      }
    }
    
    /* Error */
    public static byte ˎ(String paramString)
    {
      // Byte code:
      //   0: ldc_w 790
      //   3: aload_0
      //   4: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   7: ifeq +6 -> 13
      //   10: goto +109 -> 119
      //   13: goto +34 -> 47
      //   16: iload_1
      //   17: lookupswitch	default:+27->44, 46:+41->58, 59:+84->101
      //   44: goto +75 -> 119
      //   47: bipush 59
      //   49: istore_1
      //   50: goto -34 -> 16
      //   53: iconst_5
      //   54: ireturn
      //   55: astore_0
      //   56: aload_0
      //   57: athrow
      //   58: iconst_1
      //   59: ireturn
      //   60: astore_0
      //   61: aload_0
      //   62: athrow
      //   63: iload_1
      //   64: tableswitch	default:+24->88, 0:+-11->53, 1:+61->125
      //   88: goto +8 -> 96
      //   91: iconst_1
      //   92: istore_1
      //   93: goto -30 -> 63
      //   96: iconst_0
      //   97: istore_1
      //   98: goto -35 -> 63
      //   101: ldc_w 858
      //   104: aload_0
      //   105: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   108: istore_2
      //   109: iload_2
      //   110: ifeq +6 -> 116
      //   113: goto -17 -> 96
      //   116: goto -25 -> 91
      //   119: bipush 46
      //   121: istore_1
      //   122: goto -106 -> 16
      //   125: getstatic 194	o/Ч:ˊ	Z
      //   128: istore_2
      //   129: new 49	java/lang/StringBuilder
      //   132: dup
      //   133: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   136: astore_3
      //   137: aload_3
      //   138: ldc_w 796
      //   141: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   144: astore_3
      //   145: aload_3
      //   146: aload_0
      //   147: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   150: astore_0
      //   151: aload_0
      //   152: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   155: astore_0
      //   156: iload_2
      //   157: aload_0
      //   158: invokestatic 799	o/ν:ˊ	(ZLjava/lang/String;)V
      //   161: iconst_0
      //   162: ireturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	163	0	paramString	String
      //   16	106	1	i	int
      //   108	49	2	bool	boolean
      //   136	10	3	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   101	109	55	java/lang/Exception
      //   101	109	60	java/lang/Exception
      //   125	129	60	java/lang/Exception
      //   129	137	60	java/lang/Exception
      //   137	145	60	java/lang/Exception
      //   145	151	60	java/lang/Exception
      //   151	156	60	java/lang/Exception
      //   156	161	60	java/lang/Exception
    }
    
    public static List<PackageInfo> ˎ()
    {
      break label155;
      label3:
      int i;
      label31:
      Object localObject1;
      Object localObject2;
      for (;;)
      {
        switch (i)
        {
        default: 
          break label69;
          i = ˏ + 59;
          ᐝ = i % 128;
          if (i % 2 == 0) {
            break label139;
          }
          break label326;
          ((List)localObject1).add(localObject2);
          break label323;
          label69:
          i = 0;
        }
      }
      return localObject1;
      label76:
      label139:
      label145:
      label150:
      label155:
      Iterator localIterator;
      label185:
      String str;
      switch (i)
      {
      default: 
        break;
      case 75: 
        for (;;)
        {
          i = 14;
          switch (i)
          {
          default: 
            break label185;
            i = 75;
            break label76;
            i = 1;
            break label3;
            i = 0;
            break label295;
            localObject1 = new ArrayList(3);
            localIterator = PingerApplication.ˏ().getPackageManager().getInstalledPackages(128).iterator();
            break label31;
            do
            {
              i = 94;
              break;
              if (localIterator.hasNext()) {
                break label150;
              }
              break label239;
              localObject2 = (PackageInfo)localIterator.next();
              str = ((PackageInfo)localObject2).packageName;
            } while (str.startsWith("com.pinger"));
          }
        }
        label239:
        i = 1;
        break;
      }
      for (;;)
      {
        switch (i)
        {
        case 35: 
        default: 
          break label353;
          if (str.equals(TFApplication.ˍ().getPackageName()))
          {
            break label353;
            switch (i)
            {
            }
          }
          break;
        case 14: 
          label295:
          label323:
          break label332;
          label326:
          i = 10;
          break label76;
          label332:
          if (localIterator.hasNext()) {
            break label145;
          }
          break label69;
          i = 35;
          continue;
          label353:
          i = 14;
        }
      }
    }
    
    public static String ˏ(String paramString)
    {
      break label33;
      return null;
      for (;;)
      {
        int i;
        switch (i)
        {
        case 0: 
        default: 
          for (;;)
          {
            i = 0;
            break;
            label33:
            if (ʾ(paramString)) {
              break label46;
            }
          }
          label46:
          i = 1;
        }
      }
      paramString = paramString.split(",");
      return paramString[(paramString.length - 1)];
    }
    
    /* Error */
    public static boolean ˏ()
    {
      // Byte code:
      //   0: goto +49 -> 49
      //   3: iconst_1
      //   4: ireturn
      //   5: astore_1
      //   6: aload_1
      //   7: athrow
      //   8: bipush 63
      //   10: istore_0
      //   11: goto +74 -> 85
      //   14: iload_0
      //   15: lookupswitch	default:+25->40, 42:+135->150, 95:+106->121
      //   40: bipush 42
      //   42: istore_0
      //   43: goto -29 -> 14
      //   46: astore_1
      //   47: aload_1
      //   48: athrow
      //   49: invokestatic 890	com/pinger/common/store/Preferences$aux:ˋ	()F
      //   52: ldc_w 891
      //   55: fmul
      //   56: f2i
      //   57: istore_0
      //   58: new 695	java/util/Random
      //   61: dup
      //   62: invokespecial 696	java/util/Random:<init>	()V
      //   65: bipush 100
      //   67: invokevirtual 699	java/util/Random:nextInt	(I)I
      //   70: iconst_1
      //   71: iadd
      //   72: iload_0
      //   73: if_icmpgt +6 -> 79
      //   76: goto +39 -> 115
      //   79: goto -39 -> 40
      //   82: bipush 94
      //   84: istore_0
      //   85: iload_0
      //   86: lookupswitch	default:+26->112, 63:+-83->3, 94:+66->152
      //   112: goto -30 -> 82
      //   115: bipush 95
      //   117: istore_0
      //   118: goto -104 -> 14
      //   121: getstatic 20	o/aip$aUX:ˏ	I
      //   124: istore_0
      //   125: iload_0
      //   126: bipush 43
      //   128: iadd
      //   129: istore_0
      //   130: iload_0
      //   131: sipush 128
      //   134: irem
      //   135: putstatic 22	o/aip$aUX:ᐝ	I
      //   138: iload_0
      //   139: iconst_2
      //   140: irem
      //   141: ifne +6 -> 147
      //   144: goto -136 -> 8
      //   147: goto -65 -> 82
      //   150: iconst_0
      //   151: ireturn
      //   152: iconst_1
      //   153: ireturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   10	131	0	i	int
      //   5	2	1	localException1	Exception
      //   46	2	1	localException2	Exception
      // Exception table:
      //   from	to	target	type
      //   121	125	5	java/lang/Exception
      //   130	138	46	java/lang/Exception
    }
    
    /* Error */
    public static byte ͺ(String paramString)
    {
      // Byte code:
      //   0: goto +470 -> 470
      //   3: iconst_1
      //   4: ireturn
      //   5: iconst_4
      //   6: ireturn
      //   7: iload_1
      //   8: lookupswitch	default:+28->36, 7:+400->408, 36:+177->185
      //   36: goto +485 -> 521
      //   39: ldc_w 893
      //   42: aload_0
      //   43: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   46: ifeq +6 -> 52
      //   49: goto +466 -> 515
      //   52: goto +311 -> 363
      //   55: iload_1
      //   56: lookupswitch	default:+28->84, 13:+313->369, 29:+-51->5
      //   84: goto +443 -> 527
      //   87: bipush 65
      //   89: istore_1
      //   90: goto +346 -> 436
      //   93: bipush 7
      //   95: istore_1
      //   96: goto -89 -> 7
      //   99: bipush 29
      //   101: istore_1
      //   102: goto -47 -> 55
      //   105: getstatic 22	o/aip$aUX:ᐝ	I
      //   108: bipush 123
      //   110: iadd
      //   111: istore_1
      //   112: iload_1
      //   113: sipush 128
      //   116: irem
      //   117: putstatic 20	o/aip$aUX:ˏ	I
      //   120: iload_1
      //   121: iconst_2
      //   122: irem
      //   123: ifeq +6 -> 129
      //   126: goto +360 -> 486
      //   129: goto +49 -> 178
      //   132: iload_1
      //   133: lookupswitch	default:+27->160, 25:+98->231, 72:+254->387
      //   160: goto +355 -> 515
      //   163: iconst_5
      //   164: ireturn
      //   165: astore_0
      //   166: aload_0
      //   167: athrow
      //   168: iconst_1
      //   169: istore_1
      //   170: goto +18 -> 188
      //   173: iconst_1
      //   174: istore_1
      //   175: goto +160 -> 335
      //   178: iconst_1
      //   179: istore_1
      //   180: goto +127 -> 307
      //   183: iconst_2
      //   184: ireturn
      //   185: bipush 6
      //   187: ireturn
      //   188: iload_1
      //   189: tableswitch	default:+23->212, 0:+44->233, 1:+-84->105
      //   212: goto +14 -> 226
      //   215: bipush 42
      //   217: istore_1
      //   218: goto +58 -> 276
      //   221: iconst_0
      //   222: istore_1
      //   223: goto +112 -> 335
      //   226: iconst_0
      //   227: istore_1
      //   228: goto -40 -> 188
      //   231: iconst_3
      //   232: ireturn
      //   233: ldc_w 895
      //   236: aload_0
      //   237: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   240: ifeq +6 -> 246
      //   243: goto -28 -> 215
      //   246: goto +245 -> 491
      //   249: getstatic 20	o/aip$aUX:ˏ	I
      //   252: bipush 43
      //   254: iadd
      //   255: istore_1
      //   256: iload_1
      //   257: sipush 128
      //   260: irem
      //   261: putstatic 22	o/aip$aUX:ᐝ	I
      //   264: iload_1
      //   265: iconst_2
      //   266: irem
      //   267: ifne +6 -> 273
      //   270: goto -49 -> 221
      //   273: goto -100 -> 173
      //   276: iload_1
      //   277: lookupswitch	default:+27->304, 42:+-114->163, 52:+220->497
      //   304: goto -89 -> 215
      //   307: iload_1
      //   308: tableswitch	default:+24->332, 0:+-125->183, 1:+225->533
      //   332: goto -154 -> 178
      //   335: iload_1
      //   336: tableswitch	default:+24->360, 0:+67->403, 1:+-333->3
      //   360: goto -187 -> 173
      //   363: bipush 72
      //   365: istore_1
      //   366: goto -234 -> 132
      //   369: ldc_w 897
      //   372: aload_0
      //   373: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   376: istore_2
      //   377: iload_2
      //   378: ifeq +6 -> 384
      //   381: goto -213 -> 168
      //   384: goto -158 -> 226
      //   387: ldc_w 898
      //   390: aload_0
      //   391: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   394: ifeq +6 -> 400
      //   397: goto -298 -> 99
      //   400: goto +127 -> 527
      //   403: iconst_1
      //   404: ireturn
      //   405: astore_0
      //   406: aload_0
      //   407: athrow
      //   408: getstatic 194	o/Ч:ˊ	Z
      //   411: new 49	java/lang/StringBuilder
      //   414: dup
      //   415: invokespecial 50	java/lang/StringBuilder:<init>	()V
      //   418: ldc_w 900
      //   421: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   424: aload_0
      //   425: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   428: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   431: invokestatic 799	o/ν:ˊ	(ZLjava/lang/String;)V
      //   434: iconst_0
      //   435: ireturn
      //   436: iload_1
      //   437: lookupswitch	default:+27->464, 65:+-398->39, 72:+-188->249
      //   464: bipush 72
      //   466: istore_1
      //   467: goto -31 -> 436
      //   470: ldc_w 902
      //   473: aload_0
      //   474: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   477: ifeq +6 -> 483
      //   480: goto -16 -> 464
      //   483: goto -396 -> 87
      //   486: iconst_0
      //   487: istore_1
      //   488: goto -181 -> 307
      //   491: bipush 52
      //   493: istore_1
      //   494: goto -218 -> 276
      //   497: ldc_w 904
      //   500: aload_0
      //   501: invokevirtual 237	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   504: istore_2
      //   505: iload_2
      //   506: ifeq +6 -> 512
      //   509: goto +12 -> 521
      //   512: goto -419 -> 93
      //   515: bipush 25
      //   517: istore_1
      //   518: goto -386 -> 132
      //   521: bipush 36
      //   523: istore_1
      //   524: goto -517 -> 7
      //   527: bipush 13
      //   529: istore_1
      //   530: goto -475 -> 55
      //   533: iconst_2
      //   534: ireturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	535	0	paramString	String
      //   7	523	1	i	int
      //   376	130	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   249	256	165	java/lang/Exception
      //   256	264	165	java/lang/Exception
      //   369	377	165	java/lang/Exception
      //   256	264	405	java/lang/Exception
      //   497	505	405	java/lang/Exception
    }
    
    public static String ᐝ(String paramString)
    {
      for (;;)
      {
        int i = 93;
        break label12;
        return "unknown_number";
        switch (i)
        {
        case 6: 
        default: 
          label12:
          continue;
          do
          {
            i = 6;
            break;
          } while (aip.ʿ.ʻ(paramString));
        }
      }
      return paramString;
    }
    
    /* Error */
    public static boolean ᐝ()
    {
      // Byte code:
      //   0: goto +75 -> 75
      //   3: getstatic 22	o/aip$aUX:ᐝ	I
      //   6: bipush 79
      //   8: iadd
      //   9: istore_0
      //   10: iload_0
      //   11: sipush 128
      //   14: irem
      //   15: putstatic 20	o/aip$aUX:ˏ	I
      //   18: iload_0
      //   19: iconst_2
      //   20: irem
      //   21: ifeq +6 -> 27
      //   24: goto +9 -> 33
      //   27: goto +16 -> 43
      //   30: astore_1
      //   31: iconst_1
      //   32: ireturn
      //   33: bipush 12
      //   35: istore_0
      //   36: goto +10 -> 46
      //   39: iconst_0
      //   40: ireturn
      //   41: iconst_0
      //   42: ireturn
      //   43: bipush 44
      //   45: istore_0
      //   46: iload_0
      //   47: lookupswitch	default:+25->72, 12:+-6->41, 44:+-8->39
      //   72: goto -29 -> 43
      //   75: ldc_w 910
      //   78: invokestatic 378	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   81: invokevirtual 913	java/lang/Class:newInstance	()Ljava/lang/Object;
      //   84: astore_1
      //   85: aload_1
      //   86: invokevirtual 917	java/lang/Object:getClass	()Ljava/lang/Class;
      //   89: ldc_w 919
      //   92: invokevirtual 923	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   95: astore_2
      //   96: aload_2
      //   97: iconst_1
      //   98: invokevirtual 928	java/lang/reflect/Field:setAccessible	(Z)V
      //   101: aload_2
      //   102: aload_1
      //   103: invokevirtual 931	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   106: checkcast 73	java/lang/String
      //   109: astore_1
      //   110: goto -107 -> 3
      // Local variable table:
      //   start	length	slot	name	signature
      //   9	38	0	i	int
      //   30	1	1	localException	Exception
      //   84	26	1	localObject	Object
      //   95	7	2	localField	java.lang.reflect.Field
      // Exception table:
      //   from	to	target	type
      //   75	110	30	java/lang/Exception
    }
    
    public static void ι(String paramString)
    {
      ˊ(new aiz(paramString), true, "Insert outgoing call with duration 0");
    }
  }
  
  public static class ˈ
  {
    public ˈ() {}
    
    public static Uri ˊ(int paramInt)
    {
      Uri localUri = ˎ(paramInt);
      File localFile = ˋ(paramInt);
      if ((localUri == null) || (!localFile.exists()))
      {
        if (!ˊ(PingerApplication.ˏ(), paramInt)) {
          return RingtoneManager.getActualDefaultRingtoneUri(PingerApplication.ˏ(), paramInt);
        }
        return ˎ(paramInt);
      }
      return localUri;
    }
    
    public static void ˊ()
    {
      File localFile1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath() + File.separator + "sideline_ringtone.mp3");
      File localFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath() + File.separator + "sideline_ringtone.mp3");
      ˊ(localFile1);
      ˊ(localFile2);
      MediaScannerConnection.scanFile(PingerApplication.ˏ().getApplicationContext(), new String[] { localFile1.getAbsolutePath() }, null, null);
    }
    
    public static void ˊ(Ringtone paramRingtone)
    {
      ˊ(paramRingtone, ((AudioManager)PingerApplication.ˏ().getApplicationContext().getSystemService("audio")).shouldVibrate(0));
    }
    
    public static void ˊ(Ringtone paramRingtone, boolean paramBoolean)
    {
      if (paramRingtone != null) {
        try
        {
          if (paramRingtone.isPlaying()) {
            paramRingtone.stop();
          }
          paramRingtone.setStreamType(5);
          paramRingtone.play();
        }
        catch (Throwable paramRingtone)
        {
          ν.ˊ(Ч.ˊ, "Cannot play ringtone");
        }
      }
      if (paramBoolean) {
        ((Vibrator)PingerApplication.ˏ().getApplicationContext().getSystemService("vibrator")).vibrate(1000L);
      }
    }
    
    private static void ˊ(File paramFile)
    {
      if ((paramFile != null) && (paramFile.exists())) {
        aip.AUx.ˊ(paramFile, false);
      }
    }
    
    public static boolean ˊ(Context paramContext, int paramInt)
    {
      if (paramInt == 2) {
        localObject1 = "sounds/Pinger_Notification.mp3";
      } else if (paramInt == 1) {
        localObject1 = paramContext.getString(2131231952);
      } else {
        localObject1 = null;
      }
      Object localObject2 = ˋ(paramInt);
      if ((!((File)localObject2).getParentFile().exists()) && (!((File)localObject2).getParentFile().mkdirs())) {
        return false;
      }
      if ((!((File)localObject2).exists()) && (!TextUtils.isEmpty((CharSequence)localObject1))) {
        ˊ(paramContext, (String)localObject1, ((File)localObject2).getAbsolutePath());
      }
      if (!((File)localObject2).exists()) {
        return false;
      }
      Object localObject1 = new Semaphore(0, true);
      localObject2 = ((File)localObject2).getAbsolutePath();
      aiB localAiB = new aiB(paramInt, (Semaphore)localObject1);
      MediaScannerConnection.scanFile(paramContext, new String[] { localObject2 }, new String[] { "audio/mp3" }, localAiB);
      try
      {
        ((Semaphore)localObject1).acquire();
      }
      catch (InterruptedException paramContext)
      {
        paramContext.printStackTrace();
      }
      return ˎ(paramInt) != null;
    }
    
    private static boolean ˊ(Context paramContext, String paramString1, String paramString2)
    {
      Object localObject3 = null;
      Context localContext = null;
      Object localObject8 = null;
      Object localObject7 = null;
      Object localObject6 = null;
      byte[] arrayOfByte = null;
      Object localObject1 = localObject7;
      Object localObject2 = arrayOfByte;
      Object localObject4 = localObject8;
      Object localObject5 = localObject6;
      try
      {
        paramContext = paramContext.getAssets().openFd(paramString1);
        localContext = paramContext;
        localObject1 = localObject7;
        localObject2 = arrayOfByte;
        localObject3 = paramContext;
        localObject4 = localObject8;
        localObject5 = localObject6;
        paramString1 = paramContext.createInputStream();
        localContext = paramContext;
        localObject1 = paramString1;
        localObject2 = arrayOfByte;
        localObject3 = paramContext;
        localObject4 = paramString1;
        localObject5 = localObject6;
        paramString2 = new FileOutputStream(new File(paramString2));
        localContext = paramContext;
        localObject1 = paramString1;
        localObject2 = paramString2;
        localObject3 = paramContext;
        localObject4 = paramString1;
        localObject5 = paramString2;
        arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          localContext = paramContext;
          localObject1 = paramString1;
          localObject2 = paramString2;
          localObject3 = paramContext;
          localObject4 = paramString1;
          localObject5 = paramString2;
          int i = paramString1.read(arrayOfByte);
          if (i <= 0) {
            break;
          }
          localContext = paramContext;
          localObject1 = paramString1;
          localObject2 = paramString2;
          localObject3 = paramContext;
          localObject4 = paramString1;
          localObject5 = paramString2;
          paramString2.write(arrayOfByte, 0, i);
        }
        if (paramString1 != null) {}
        try
        {
          paramString1.close();
          if (paramString2 != null) {
            paramString2.close();
          }
          if (paramContext != null) {
            paramContext.close();
          }
          return true;
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
          return true;
        }
        return false;
      }
      catch (IOException paramContext)
      {
        localObject3 = localContext;
        localObject4 = localObject1;
        localObject5 = localObject2;
        paramContext.printStackTrace();
        if (localObject1 != null) {}
        try
        {
          ((FileInputStream)localObject1).close();
          if (localObject2 != null) {
            ((FileOutputStream)localObject2).close();
          }
          if (localContext != null) {
            localContext.close();
          }
        }
        catch (IOException paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      finally
      {
        if (localObject4 != null) {}
        try
        {
          ((FileInputStream)localObject4).close();
          if (localObject5 != null) {
            ((FileOutputStream)localObject5).close();
          }
          if (localObject3 != null) {
            ((AssetFileDescriptor)localObject3).close();
          }
        }
        catch (IOException paramString1)
        {
          paramString1.printStackTrace();
        }
      }
    }
    
    public static File ˋ(int paramInt)
    {
      String str;
      if (paramInt == 2) {
        str = Environment.DIRECTORY_NOTIFICATIONS;
      } else if (paramInt == 1) {
        str = Environment.DIRECTORY_RINGTONES;
      } else {
        str = null;
      }
      boolean bool;
      if ((Ч.ˊ) && (!TextUtils.isEmpty(str))) {
        bool = true;
      } else {
        bool = false;
      }
      ь.ˊ(bool, "Invalid type = " + paramInt);
      StringBuilder localStringBuilder = new StringBuilder().append(Environment.getExternalStoragePublicDirectory(str).getAbsolutePath()).append(File.separator);
      if (paramInt == 2) {
        str = "Pinger.mp3";
      } else {
        str = PingerApplication.ˏ().getString(2131231953);
      }
      return new File(String.valueOf(str));
    }
    
    public static Uri ˎ(int paramInt)
    {
      if (paramInt == 2) {
        return Preferences.iF.ʻ();
      }
      if (paramInt == 1) {
        return Preferences.iF.ʼ();
      }
      return null;
    }
  }
}
