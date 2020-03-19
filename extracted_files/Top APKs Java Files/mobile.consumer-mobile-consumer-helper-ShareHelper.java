package mobile.consumer.helper;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ShareEvent;
import java.util.Iterator;
import java.util.List;
import mobile.consumer.headquarter.User;

public class ShareHelper
{
  private static final String CLASS_NAME_LINE = "jp.naver.line.android.activity.selectchat.SelectChatActivity";
  private static final String PACKAGE_NAME_FACEBOOK = "com.facebook.katana";
  private static final String PACKAGE_NAME_LINE = "jp.naver.line.android";
  private static final String PACKAGE_NAME_WHATSAPP = "com.whatsapp";
  
  public ShareHelper() {}
  
  private static boolean checkLineInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    boolean bool2 = false;
    paramContext = paramContext.iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public static void copyToClipboard(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
  }
  
  public static boolean shareTextToEmail(Context paramContext, String paramString)
  {
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("mailto:");
      localStringBuffer.append("?subject=");
      localStringBuffer.append("&body=");
      localStringBuffer.append(paramString);
      paramContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SENDTO", Uri.parse(localStringBuffer.toString().replace(" ", "%20"))), User.loc(2131558699)));
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean shareTextToFacebook(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (checkLineInstalled(paramContext, "com.facebook.katana")) {}
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage("com.facebook.katana");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      paramContext.startActivity(localIntent);
      bool = true;
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean shareTextToLine(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (checkLineInstalled(paramContext, "jp.naver.line.android")) {}
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setClassName("jp.naver.line.android", "jp.naver.line.android.activity.selectchat.SelectChatActivity");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      paramContext.startActivity(localIntent);
      bool = true;
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean shareTextToWhatsApp(Context paramContext, String paramString)
  {
    boolean bool = false;
    if (checkLineInstalled(paramContext, "com.whatsapp")) {}
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage("com.whatsapp");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      paramContext.startActivity(localIntent);
      bool = true;
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void showSharePanel(Context paramContext, final String paramString1, final String paramString2, final String paramString3)
  {
    String str1 = User.loc(2131558700);
    String str2 = User.loc(2131558703);
    String str3 = User.loc(2131558701);
    String str4 = User.loc(2131558699);
    String str5 = User.loc(2131558698);
    String str6 = User.loc(2131558702);
    String str7 = User.loc(2131558789);
    AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(paramContext);
    AlertDialog.Builder localBuilder2 = localBuilder1.setTitle("");
    paramContext = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
        case 0: 
        case 1: 
        case 2: 
        case 3: 
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                } while (!ShareHelper.shareTextToFacebook(this.val$context, paramString1));
                Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("Facebook").putContentType(paramString3).putCustomAttribute("Env", "Live"));
                return;
              } while (!ShareHelper.shareTextToWhatsApp(this.val$context, paramString2));
              Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("WhatsApp").putContentType(paramString3).putCustomAttribute("Env", "Live"));
              return;
            } while (!ShareHelper.shareTextToLine(this.val$context, paramString2));
            Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("Line").putContentType(paramString3).putCustomAttribute("Env", "Live"));
            return;
          } while (!ShareHelper.shareTextToEmail(this.val$context, paramString2));
          Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("Email").putContentType(paramString3).putCustomAttribute("Env", "Live"));
          return;
        case 4: 
          ShareHelper.copyToClipboard(this.val$context, paramString2);
          Toast.makeText(this.val$context, User.loc(2131558465), 0).show();
          Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("Copy").putContentType(paramString3).putCustomAttribute("Env", "Live"));
          return;
        }
        paramAnonymousDialogInterface = new Intent("android.intent.action.SEND");
        paramAnonymousDialogInterface.setType("text/plain");
        paramAnonymousDialogInterface.putExtra("android.intent.extra.TEXT", paramString2);
        this.val$context.startActivity(Intent.createChooser(paramAnonymousDialogInterface, User.loc(2131558702)));
        Answers.getInstance().logShare((ShareEvent)new ShareEvent().putMethod("Facebook").putContentType(paramString3).putCustomAttribute("Env", "Live"));
      }
    };
    localBuilder2.setItems(new String[] { str1, str2, str3, str4, str5, str6, str7 }, paramContext);
    localBuilder1.create().show();
  }
}
