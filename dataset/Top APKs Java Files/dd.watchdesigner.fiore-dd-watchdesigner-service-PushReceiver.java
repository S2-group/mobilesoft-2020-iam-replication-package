package dd.watchdesigner.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.SaveCallback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class PushReceiver
  extends ParsePushBroadcastReceiver
  implements SaveCallback
{
  public PushReceiver() {}
  
  private Map<String, String> getMap(Intent paramIntent)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      localHashMap.put("com.parse.Data", paramIntent.getExtras().getString("com.parse.Data"));
      localHashMap.put("com.parse.Channel", paramIntent.getExtras().getString("com.parse.Channel"));
      JSONObject localJSONObject = new JSONObject(paramIntent.getExtras().getString("com.parse.Data"));
      Iterator localIterator = localJSONObject.keys();
      for (;;)
      {
        paramIntent = localHashMap;
        if (!localIterator.hasNext()) {
          break;
        }
        paramIntent = (String)localIterator.next();
        localHashMap.put(paramIntent, localJSONObject.getString(paramIntent));
      }
      return paramIntent;
    }
    catch (Exception paramIntent)
    {
      paramIntent.printStackTrace();
      paramIntent = null;
    }
  }
  
  private static String getPushablePackageName(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(4);
    paramContext = paramContext.getPackageName();
    Iterator localIterator = ((List)localObject).iterator();
    do
    {
      localObject = paramContext;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (PackageInfo)localIterator.next();
    } while ((!((PackageInfo)localObject).packageName.startsWith("dd.watchdesigner.")) || (((PackageInfo)localObject).services == null));
    ServiceInfo[] arrayOfServiceInfo = ((PackageInfo)localObject).services;
    int j = arrayOfServiceInfo.length;
    int i = 0;
    for (;;)
    {
      localObject = paramContext;
      if (i < j)
      {
        localObject = arrayOfServiceInfo[i];
        if (((ServiceInfo)localObject).name.equals("dd.watchdesigner.service.NotificaionService")) {
          localObject = ((ServiceInfo)localObject).packageName;
        }
      }
      else
      {
        paramContext = (Context)localObject;
        if (0 == 0) {
          break;
        }
        return localObject;
      }
      i += 1;
    }
  }
  
  public static void showTestNoti(Context paramContext)
  {
    ServiceConnection local1 = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        paramAnonymousComponentName = IPushAidlInterface.Stub.asInterface(paramAnonymousIBinder);
        try
        {
          paramAnonymousComponentName.post("Bw5HRtP39p");
          return;
        }
        catch (RemoteException paramAnonymousComponentName)
        {
          paramAnonymousComponentName.printStackTrace();
        }
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        this.val$context.unbindService(this);
      }
    };
    Intent localIntent = new Intent("intent.action.dd.watchdesigner.service.notificaion");
    localIntent.setPackage(getPushablePackageName(paramContext));
    paramContext.bindService(localIntent, local1, 1);
  }
  
  public void done(ParseException paramParseException) {}
  
  protected void onPushDismiss(Context paramContext, Intent paramIntent)
  {
    super.onPushDismiss(paramContext, paramIntent);
  }
  
  protected void onPushOpen(Context paramContext, Intent paramIntent)
  {
    super.onPushOpen(paramContext, paramIntent);
  }
  
  protected void onPushReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = getMap(paramIntent);
    if (TextUtils.isEmpty((CharSequence)((Map)localObject1).get("pushObjectId"))) {
      return;
    }
    localObject1 = (String)((Map)localObject1).get("pushObjectId");
    String str = getPushablePackageName(paramContext);
    Object localObject2 = new Intent("intent.action.dd.watchdesigner.service.notificaion");
    ((Intent)localObject2).setPackage(str);
    localObject2 = peekService(paramContext, (Intent)localObject2);
    if (localObject2 != null) {
      paramContext = IPushAidlInterface.Stub.asInterface((IBinder)localObject2);
    }
    for (;;)
    {
      try
      {
        paramContext.post((String)localObject1);
        ParseAnalytics.trackAppOpenedInBackground(paramIntent, this);
        return;
      }
      catch (RemoteException paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      localObject2 = new Intent("intent.action.dd.watchdesigner.service.notificaion");
      ((Intent)localObject2).setPackage(str);
      ((Intent)localObject2).putExtra("pushObjectId", (String)localObject1);
      paramContext.startService((Intent)localObject2);
    }
  }
}
