package zok.android.shapes.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationFactory
{
  public NotificationFactory() {}
  
  public static Notification createNotification(JSONObject paramJSONObject, Context paramContext, Handler paramHandler)
  {
    try
    {
      paramJSONObject = doCreateNotification(paramJSONObject, paramContext, paramHandler);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  private static Notification doCreateNotification(JSONObject paramJSONObject, Context paramContext, Handler paramHandler)
    throws JSONException
  {
    String str1 = paramJSONObject.getString("id");
    String str2 = paramJSONObject.getString("type");
    paramJSONObject = paramJSONObject.getJSONObject("data");
    boolean bool = Notification.isNotificationAlreadyShown(paramContext, str1);
    if (str2.equals("text"))
    {
      if (bool) {
        return null;
      }
      return new TextNotification(str1, paramJSONObject);
    }
    if (str2.equals("image"))
    {
      paramJSONObject = new ImageNotification(paramJSONObject, str1, paramContext.getResources(), paramHandler);
      if ((isShowable(paramContext, paramJSONObject.getPreferenceKey())) && (!isInstalled(paramContext, paramJSONObject.getFreeAppPackageName())) && (!isInstalled(paramContext, paramJSONObject.getPaidAppPackageName())))
      {
        paramJSONObject.fetchBackgroundDrawable();
        return paramJSONObject;
      }
      return null;
    }
    return null;
  }
  
  private static boolean isInstalled(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      while (!paramContext.hasNext())
      {
        return false;
        paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      }
    } while (!paramString.equals(((ApplicationInfo)paramContext.next()).packageName));
    return true;
  }
  
  public static boolean isShowable(Context paramContext, String paramString)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramString, true);
  }
}
