package indian.plusone.phone.launcher;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import indian.plusone.phone.launcher.themeui.request.LoadTask;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class InstallShortcutReceiver
  extends BroadcastReceiver
{
  public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
  public static final String APPS_PENDING_INSTALL = "apps_to_install";
  public static final String DATA_INTENT_KEY = "intent.data";
  private static final boolean DBG = false;
  public static final String ICON_KEY = "icon";
  public static final String ICON_RESOURCE_NAME_KEY = "iconResource";
  public static final String ICON_RESOURCE_PACKAGE_NAME_KEY = "iconResourcePackage";
  private static final int INSTALL_SHORTCUT_IS_DUPLICATE = -1;
  private static final int INSTALL_SHORTCUT_SUCCESSFUL = 0;
  public static final String LAUNCH_INTENT_KEY = "intent.launch";
  public static final String NAME_KEY = "name";
  public static final int NEW_SHORTCUT_BOUNCE_DURATION = 450;
  public static final int NEW_SHORTCUT_STAGGER_DELAY = 85;
  public static final String SHORTCUT_MIMETYPE = "indian.plusone.phone.launcher/shortcut";
  private static final String TAG = "InstallShortcutReceiver";
  private static boolean mUseInstallQueue = false;
  private static Object sLock = new Object();
  
  public InstallShortcutReceiver() {}
  
  private static void addToInstallQueue(SharedPreferences paramSharedPreferences, PendingInstallShortcutInfo paramPendingInstallShortcutInfo)
  {
    synchronized (sLock)
    {
      try
      {
        Object localObject2 = new JSONStringer().object().key("intent.data").value(paramPendingInstallShortcutInfo.data.toUri(0)).key("intent.launch").value(paramPendingInstallShortcutInfo.launchIntent.toUri(0)).key("name").value(paramPendingInstallShortcutInfo.name);
        Object localObject1 = localObject2;
        if (paramPendingInstallShortcutInfo.icon != null)
        {
          localObject1 = ItemInfo.flattenBitmap(paramPendingInstallShortcutInfo.icon);
          localObject1 = ((JSONStringer)localObject2).key("icon").value(Base64.encodeToString((byte[])localObject1, 0, localObject1.length, 0));
        }
        localObject2 = localObject1;
        if (paramPendingInstallShortcutInfo.iconResource != null) {
          localObject2 = ((JSONStringer)localObject1).key("iconResource").value(paramPendingInstallShortcutInfo.iconResource.resourceName).key("iconResourcePackage").value(paramPendingInstallShortcutInfo.iconResource.packageName);
        }
        paramPendingInstallShortcutInfo = ((JSONStringer)localObject2).endObject();
        localObject1 = paramSharedPreferences.edit();
        addToStringSet(paramSharedPreferences, (SharedPreferences.Editor)localObject1, "apps_to_install", paramPendingInstallShortcutInfo.toString());
        ((SharedPreferences.Editor)localObject1).commit();
      }
      catch (JSONException paramSharedPreferences)
      {
        for (;;)
        {
          Log.d("InstallShortcutReceiver", "Exception when adding shortcut: " + paramSharedPreferences);
        }
      }
      return;
    }
  }
  
  private static void addToStringSet(SharedPreferences paramSharedPreferences, SharedPreferences.Editor paramEditor, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.getStringSet(paramString1, null);
    if (paramSharedPreferences == null) {}
    for (paramSharedPreferences = new HashSet(0);; paramSharedPreferences = new HashSet(paramSharedPreferences))
    {
      paramSharedPreferences.add(paramString2);
      paramEditor.putStringSet(paramString1, paramSharedPreferences);
      return;
    }
  }
  
  static void disableAndFlushInstallQueue(Context paramContext)
  {
    mUseInstallQueue = false;
    flushInstallQueue(paramContext);
  }
  
  static void enableInstallQueue()
  {
    mUseInstallQueue = true;
  }
  
  private static CharSequence ensureValidName(Context paramContext, Intent paramIntent, CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {}
    try
    {
      paramContext = paramContext.getPackageManager();
      localObject = paramContext.getActivityInfo(paramIntent.getComponent(), 0).loadLabel(paramContext).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  static void flushInstallQueue(Context paramContext)
  {
    Object localObject = getAndClearInstallQueue(paramContext.getSharedPreferences(LauncherAppState.getSharedPreferencesKey(), 0));
    ArrayList localArrayList;
    if (!((ArrayList)localObject).isEmpty())
    {
      localObject = ((ArrayList)localObject).iterator();
      localArrayList = new ArrayList();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (-1 == 0) {
          Toast.makeText(paramContext, paramContext.getString(2131230865, new Object[] { "" }), 0).show();
        }
        if (!localArrayList.isEmpty()) {
          LauncherAppState.getInstance().getModel().addAndBindAddedWorkspaceApps(paramContext, localArrayList);
        }
        return;
      }
      PendingInstallShortcutInfo localPendingInstallShortcutInfo = (PendingInstallShortcutInfo)((Iterator)localObject).next();
      Intent localIntent = localPendingInstallShortcutInfo.launchIntent;
      String str = localPendingInstallShortcutInfo.name;
      if (((!LauncherAppState.isDisableAllApps()) || (isValidShortcutLaunchIntent(localIntent))) && (!LauncherModel.shortcutExists(paramContext, str, localIntent))) {
        localArrayList.add(getShortcutInfo(paramContext, localPendingInstallShortcutInfo.data, localPendingInstallShortcutInfo.launchIntent));
      }
    }
  }
  
  private static ArrayList<PendingInstallShortcutInfo> getAndClearInstallQueue(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      Object localObject2;
      ArrayList localArrayList;
      synchronized (sLock)
      {
        localObject2 = paramSharedPreferences.getStringSet("apps_to_install", null);
        if (localObject2 == null)
        {
          paramSharedPreferences = new ArrayList();
          return paramSharedPreferences;
        }
        localArrayList = new ArrayList();
        localObject2 = ((Set)localObject2).iterator();
        if (!((Iterator)localObject2).hasNext())
        {
          paramSharedPreferences.edit().putStringSet("apps_to_install", new HashSet()).commit();
          return localArrayList;
        }
      }
      Object localObject3 = (String)((Iterator)localObject2).next();
      try
      {
        localObject6 = (JSONObject)new JSONTokener((String)localObject3).nextValue();
        localObject3 = Intent.parseUri(((JSONObject)localObject6).getString("intent.data"), 0);
        Intent localIntent = Intent.parseUri(((JSONObject)localObject6).getString("intent.launch"), 0);
        String str = ((JSONObject)localObject6).getString("name");
        localObject5 = ((JSONObject)localObject6).optString("icon");
        localObject4 = ((JSONObject)localObject6).optString("iconResource");
        localObject6 = ((JSONObject)localObject6).optString("iconResourcePackage");
        if ((localObject5 != null) && (!((String)localObject5).isEmpty()))
        {
          localObject4 = Base64.decode((String)localObject5, 0);
          ((Intent)localObject3).putExtra("android.intent.extra.shortcut.ICON", BitmapFactory.decodeByteArray((byte[])localObject4, 0, localObject4.length));
          ((Intent)localObject3).putExtra("android.intent.extra.shortcut.INTENT", localIntent);
          localArrayList.add(new PendingInstallShortcutInfo((Intent)localObject3, str, localIntent));
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Object localObject6;
          Object localObject5;
          Object localObject4;
          Log.d("InstallShortcutReceiver", "Exception reading shortcut to add: " + localJSONException);
          break;
          if ((localObject4 != null) && (!((String)localObject4).isEmpty()))
          {
            localObject5 = new Intent.ShortcutIconResource();
            ((Intent.ShortcutIconResource)localObject5).resourceName = ((String)localObject4);
            ((Intent.ShortcutIconResource)localObject5).packageName = ((String)localObject6);
            localJSONException.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject5);
          }
        }
      }
      catch (URISyntaxException localURISyntaxException)
      {
        Log.d("InstallShortcutReceiver", "Exception reading shortcut to add: " + localURISyntaxException);
      }
    }
  }
  
  private static ShortcutInfo getShortcutInfo(Context paramContext, Intent paramIntent1, Intent paramIntent2)
  {
    if (paramIntent2.getAction() == null) {
      paramIntent2.setAction("android.intent.action.VIEW");
    }
    for (;;)
    {
      paramIntent1 = LauncherAppState.getInstance().getModel().infoFromShortcutIntent(paramContext, paramIntent1, null);
      paramIntent1.title = ensureValidName(paramContext, paramIntent2, paramIntent1.title);
      return paramIntent1;
      if ((paramIntent2.getAction().equals("android.intent.action.MAIN")) && (paramIntent2.getCategories() != null) && (paramIntent2.getCategories().contains("android.intent.category.LAUNCHER"))) {
        paramIntent2.addFlags(270532608);
      }
    }
  }
  
  static boolean isValidShortcutLaunchIntent(Intent paramIntent)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if ("android.intent.action.MAIN".equals(paramIntent.getAction()))
      {
        bool1 = bool2;
        if (paramIntent.getComponent() != null)
        {
          bool1 = bool2;
          if (paramIntent.getCategories() != null)
          {
            bool1 = bool2;
            if (paramIntent.getCategories().size() == 1)
            {
              bool1 = bool2;
              if (paramIntent.hasCategory("android.intent.category.LAUNCHER"))
              {
                bool1 = bool2;
                if (paramIntent.getExtras() == null)
                {
                  bool1 = bool2;
                  if (TextUtils.isEmpty(paramIntent.getDataString())) {
                    bool1 = false;
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static void removeFromInstallQueue(SharedPreferences paramSharedPreferences, ArrayList<String> paramArrayList)
  {
    if (paramArrayList.isEmpty()) {
      return;
    }
    for (;;)
    {
      Iterator localIterator;
      synchronized (sLock)
      {
        localObject1 = paramSharedPreferences.getStringSet("apps_to_install", null);
        if (localObject1 != null)
        {
          HashSet localHashSet = new HashSet((Collection)localObject1);
          localIterator = localHashSet.iterator();
          if (!localIterator.hasNext()) {
            paramSharedPreferences.edit().putStringSet("apps_to_install", new HashSet(localHashSet)).commit();
          }
        }
        else
        {
          return;
        }
      }
      Object localObject1 = (String)localIterator.next();
      try
      {
        Intent localIntent = Intent.parseUri(((JSONObject)new JSONTokener((String)localObject1).nextValue()).getString("intent.launch"), 0);
        String str = localIntent.getPackage();
        localObject1 = str;
        if (str == null) {
          localObject1 = localIntent.getComponent().getPackageName();
        }
        if (paramArrayList.contains(localObject1)) {
          localIterator.remove();
        }
      }
      catch (JSONException localJSONException)
      {
        Log.d("InstallShortcutReceiver", "Exception reading shortcut to remove: " + localJSONException);
      }
      catch (URISyntaxException localURISyntaxException)
      {
        Log.d("InstallShortcutReceiver", "Exception reading shortcut to remove: " + localURISyntaxException);
      }
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (!"com.android.launcher.action.INSTALL_SHORTCUT".equals(paramIntent.getAction())) {}
    for (;;)
    {
      return;
      Intent localIntent = (Intent)paramIntent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
      if (localIntent != null) {}
      try
      {
        localObject = localIntent.getComponent().getPackageName();
        if ((localObject != null) && (((String)localObject).contains("theme")))
        {
          boolean bool = LoadTask.getInstalledPackages(paramContext).contains(localIntent.getComponent().getPackageName());
          if (bool) {
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject;
        String str;
        Intent.ShortcutIconResource localShortcutIconResource;
        int i;
        for (;;) {}
      }
    }
    str = ensureValidName(paramContext, localIntent, paramIntent.getStringExtra("android.intent.extra.shortcut.NAME")).toString();
    localObject = (Bitmap)paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON");
    localShortcutIconResource = (Intent.ShortcutIconResource)paramIntent.getParcelableExtra("android.intent.extra.shortcut.ICON_RESOURCE");
    LauncherAppState.setApplicationContext(paramContext.getApplicationContext());
    if (LauncherAppState.getInstance().getDynamicGrid() == null) {}
    for (i = 1;; i = 0)
    {
      paramIntent = new PendingInstallShortcutInfo(paramIntent, str, localIntent);
      paramIntent.icon = ((Bitmap)localObject);
      paramIntent.iconResource = localShortcutIconResource;
      addToInstallQueue(paramContext.getSharedPreferences(LauncherAppState.getSharedPreferencesKey(), 0), paramIntent);
      if ((mUseInstallQueue) || (i != 0)) {
        break;
      }
      flushInstallQueue(paramContext);
      return;
    }
  }
  
  private static class PendingInstallShortcutInfo
  {
    Intent data;
    Bitmap icon;
    Intent.ShortcutIconResource iconResource;
    Intent launchIntent;
    String name;
    
    public PendingInstallShortcutInfo(Intent paramIntent1, String paramString, Intent paramIntent2)
    {
      this.data = paramIntent1;
      this.name = paramString;
      this.launchIntent = paramIntent2;
    }
  }
}
