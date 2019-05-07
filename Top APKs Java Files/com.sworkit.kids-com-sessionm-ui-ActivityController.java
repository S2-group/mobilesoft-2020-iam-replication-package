package com.sessionm.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract.Events;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.sessionm.api.m;
import com.sessionm.d.ad;
import com.sessionm.d.ae;
import com.sessionm.d.an;
import com.sessionm.d.as;
import com.sessionm.e.c;
import com.sessionm.e.e;
import com.sessionm.ui.fragment.ActivityFragment;
import com.sessionm.ui.fragment.FragmentActivityController;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityController
  implements SessionMViewContainer.SessionMViewContainerListener, VideoErrorListener, VideoProgressListener
{
  static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
  static final String TAG = "SessionM.Activity";
  private static final Map<String, Integer> xPosFlagMap = new HashMap();
  private static final Map<String, Integer> yPosFlagMap = new HashMap();
  private String activityContent;
  private Activity activityContext;
  private com.sessionm.b.a activityData;
  private SessionMViewContainer browserViewContainer;
  private ActivityController.State browserViewState = ActivityController.State.DISMISSED;
  private com.sessionm.b.a contentData;
  private com.sessionm.b.a contentData2;
  private String contentUrl;
  private com.sessionm.b.a eventCreationMessage;
  private JSInterfaceListener jsInterfaceListener;
  private String lastBrowserUrl;
  private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
  private ActivityController.LoadWebViewListener loadWebViewListener;
  private long presentationStartTime = System.currentTimeMillis();
  private FrameLayout providedViewGroup;
  private com.sessionm.d.g session = com.sessionm.d.g.b();
  private SessionMViewContainer sessionMViewContainer;
  private ActivityController.State state = ActivityController.State.DISMISSED;
  private ActivityController.StateChangeListener stateChangeListener;
  
  static
  {
    xPosFlagMap.put("left", Integer.valueOf(3));
    xPosFlagMap.put("right", Integer.valueOf(5));
    xPosFlagMap.put("center", Integer.valueOf(1));
    xPosFlagMap.put("middle", Integer.valueOf(1));
    yPosFlagMap.put("bottom", Integer.valueOf(80));
    yPosFlagMap.put("top", Integer.valueOf(48));
    yPosFlagMap.put("center", Integer.valueOf(16));
    yPosFlagMap.put("middle", Integer.valueOf(16));
  }
  
  public ActivityController(Activity paramActivity, String paramString)
  {
    this.activityContext = paramActivity;
    this.contentUrl = paramString;
  }
  
  static int convertDpToPixels(Context paramContext, int paramInt)
  {
    if ((paramContext == null) || (paramInt <= 0)) {
      return 0;
    }
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt);
  }
  
  static int convertPixelsToDp(Context paramContext, int paramInt)
  {
    if ((paramContext == null) || (paramInt <= 0)) {
      return 0;
    }
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / f);
  }
  
  @SuppressLint({"NewApi"})
  public static ActivityController createActivityController(boolean paramBoolean, Activity paramActivity, String paramString, Object... paramVarArgs)
  {
    int i = com.sessionm.d.g.b().t();
    if (i != -1) {
      if (Build.VERSION.SDK_INT < 11)
      {
        Log.w("SessionM.Activity", "Disabling fragment presentation for OS versions below 11");
        i = -1;
      }
    }
    label64:
    label274:
    for (;;)
    {
      Object localObject;
      if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
        if ((paramVarArgs.length > 0) && ((paramVarArgs[0] instanceof com.sessionm.b.a)))
        {
          localObject = (com.sessionm.b.a)paramVarArgs[0];
          if ((paramVarArgs.length > 1) && ((paramVarArgs[1] instanceof com.sessionm.b.a)))
          {
            com.sessionm.b.a localA = (com.sessionm.b.a)paramVarArgs[1];
            paramVarArgs = (Object[])localObject;
            localObject = localA;
          }
        }
      }
      for (;;)
      {
        if (i == -1)
        {
          paramActivity = new ActivityController(paramActivity, paramString);
          paramActivity.setContentData(paramVarArgs);
          paramActivity.setContentData2((com.sessionm.b.a)localObject);
          paramActivity.setActivityContent(null);
          return paramActivity;
          localObject = paramActivity.findViewById(i);
          if ((localObject instanceof FrameLayout)) {
            break label274;
          }
          Log.e("SessionM.Activity", "Fragment FrameLayout ID does not identify FrameLayout instance (" + localObject + "). Using non-Fragment based presentation instead.");
          i = -1;
          break;
        }
        paramActivity = new ActivityFragment();
        localObject = new Bundle();
        ((Bundle)localObject).putString("URL_ARG_KEY", paramString);
        ((Bundle)localObject).putString("JSON_DATA_ARG_KEY", paramVarArgs.toString());
        paramActivity.setArguments((Bundle)localObject);
        paramActivity = paramActivity.getActivityController(com.sessionm.d.g.b().k());
        paramActivity.setFragmentFrameLayoutId(i);
        paramActivity.setActivityContent(null);
        return paramActivity;
        paramVarArgs = (Object[])localObject;
        localObject = null;
        continue;
        localObject = null;
        break label64;
        localObject = null;
        paramVarArgs = null;
      }
    }
  }
  
  private com.sessionm.b.a createDispatchJsonObject(SessionMVideoView paramSessionMVideoView)
  {
    com.sessionm.b.a localA = com.sessionm.b.a.a();
    int i = (int)TimeUnit.MILLISECONDS.toSeconds(paramSessionMVideoView.getDuration());
    int j = (int)TimeUnit.MILLISECONDS.toSeconds(paramSessionMVideoView.getCurrentPosition());
    localA.a("percentComplete", (int)(j / i * 100.0F));
    localA.a("timeRemaining", i - j);
    localA.a("currentTime", j);
    return localA;
  }
  
  private void dispatchMessage(String paramString1, String paramString2)
  {
    executeJavascript(String.format("GreyhoundEventDispatcher.dispatch('%s',%s);", new Object[] { paramString1, paramString2 }));
  }
  
  private void downloadImage(String paramString, com.sessionm.b.a paramA)
  {
    File localFile = getPictureStoragePath();
    localFile.mkdirs();
    paramString = new com.sessionm.e.a(com.sessionm.e.g.e, new Object[] { paramString });
    paramString.a(c.a.a(), "no-cache");
    paramString.a(new ActivityController.17(this, paramA, localFile));
    paramString.r();
  }
  
  @TargetApi(11)
  private com.sessionm.b.a getActivityData()
  {
    int i = 0;
    Object localObject1;
    Object localObject2;
    boolean bool;
    if (this.activityData == null)
    {
      this.activityData = this.session.G();
      if (this.session.i() != null) {
        this.activityData.a("global_data", this.session.i());
      }
      if (this.session.h() != null)
      {
        localObject1 = this.session.h().entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          this.activityData.a("user[data][" + (String)((Map.Entry)localObject2).getKey() + "]", (String)((Map.Entry)localObject2).getValue());
        }
      }
      localObject1 = getActivityContext();
      localObject2 = ((Activity)localObject1).getWindow().getDecorView();
      this.activityData.a("width", convertPixelsToDp((Context)localObject1, ((View)localObject2).getWidth()));
      this.activityData.a("height", convertPixelsToDp((Context)localObject1, ((View)localObject2).getHeight()));
      this.activityData.a("z", "" + System.currentTimeMillis());
      if (Build.VERSION.SDK_INT <= 16) {
        break label391;
      }
      if ((!this.session.y()) && (((View)localObject2).isHardwareAccelerated())) {
        bool = true;
      }
    }
    for (;;)
    {
      this.activityData.a("hardware_acceleration_enabled", bool);
      this.activityData.a("connection_type", as.a(getActivityContext()));
      if (this.contentData2 != null)
      {
        if (this.contentData == null) {
          this.contentData = com.sessionm.b.a.a();
        }
        localObject1 = this.contentData2.b();
        int j = localObject1.length;
        for (;;)
        {
          if (i < j)
          {
            localObject2 = localObject1[i];
            this.contentData.a((String)localObject2, this.contentData2.g((String)localObject2));
            i += 1;
            continue;
            bool = false;
            break;
          }
        }
      }
      this.activityData.a("data", this.contentData);
      return this.activityData;
      label391:
      bool = false;
    }
  }
  
  private String getFileNameForUriAndHeaderFields(URI paramURI, List<String> paramList)
  {
    paramURI = paramURI.getPath();
    if (paramURI == null) {
      return null;
    }
    paramURI = new File(paramURI).getName();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (str.contains("image/"))
        {
          paramList = "." + str.split("/")[1];
          if (!paramURI.endsWith(paramList)) {
            return paramURI + paramList;
          }
        }
      }
    }
    return paramURI;
  }
  
  private Method getJSHandlerMethod(String paramString)
  {
    Method[] arrayOfMethod = ActivityController.class.getDeclaredMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = arrayOfMethod[i];
      if (localMethod.getName().equals(paramString)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private File getPictureStoragePath()
  {
    return new File(Environment.getExternalStorageDirectory(), "Pictures");
  }
  
  private ViewGroup.LayoutParams getViewLayoutParams(com.sessionm.b.a paramA)
  {
    Activity localActivity = getActivityContext();
    String str1 = paramA.b("vertical_alignment");
    String str2 = paramA.b("horizontal_alignment");
    if ((str1 == null) || (str1.equalsIgnoreCase("")) || (paramA.i("vertical_alignment"))) {
      str1 = "center";
    }
    if ((str2 == null) || (str2.equalsIgnoreCase("")) || (paramA.i("horizontal_alignment"))) {
      str2 = "center";
    }
    String str3 = paramA.b("display_type");
    int m = paramA.d("horizontal_offset");
    int n = paramA.d("vertical_offset");
    int i1 = ((Integer)xPosFlagMap.get(str2)).intValue();
    int i2 = ((Integer)yPosFlagMap.get(str1)).intValue();
    int j = convertDpToPixels(localActivity, paramA.d("width"));
    int k = convertDpToPixels(localActivity, paramA.d("height"));
    paramA = localActivity.getWindow().getDecorView();
    int i = j;
    if (paramA.getWidth() <= j) {
      i = -1;
    }
    j = k;
    if (paramA.getHeight() <= k) {
      j = -1;
    }
    if ((str3 != null) && (str3.equalsIgnoreCase("frame")))
    {
      paramA = new FrameLayout.LayoutParams(i, j);
      if (i2 == 48) {
        paramA.topMargin = convertDpToPixels(localActivity, n);
      }
      if (i2 == 80) {
        paramA.bottomMargin = convertDpToPixels(localActivity, n);
      }
      if (i1 == 3) {
        paramA.leftMargin = convertDpToPixels(localActivity, m);
      }
      for (;;)
      {
        paramA.gravity = (i1 | i2);
        return paramA;
        if (i1 == 5) {
          paramA.rightMargin = convertDpToPixels(localActivity, m);
        }
      }
    }
    paramA = new FrameLayout.LayoutParams(-1, -1);
    paramA.gravity = 17;
    return paramA;
  }
  
  private com.sessionm.b.a handleCheckInstalledMessage(com.sessionm.b.a paramA)
  {
    if (isApplicationInstalled(paramA.b("packageName"))) {
      executeJavascript(String.format("GreyhoundEventDispatcher.dispatch('packageInstalled',{'installed':true});", new Object[0]));
    }
    for (;;)
    {
      return null;
      executeJavascript(String.format("GreyhoundEventDispatcher.dispatch('packageInstalled',{'installed':false});", new Object[0]));
    }
  }
  
  private com.sessionm.b.a handleCloseMessage(com.sessionm.b.a paramA)
  {
    if (isBrowserDisplayed())
    {
      dismissBrowser();
      return null;
    }
    dismiss();
    return null;
  }
  
  @TargetApi(14)
  private com.sessionm.b.a handleCreateCalendarEventMessage(com.sessionm.b.a paramA)
  {
    if (Build.VERSION.SDK_INT <= 14)
    {
      paramA = com.sessionm.b.a.a();
      paramA.a("error", "Unsupported operation below API 14.");
      notifyContentAboutError(ActivityController.ContentError.CALENDAR_ACTIVITY_UNAVAILABLE_ERROR, null);
      return paramA;
    }
    if (this.eventCreationMessage != null) {
      this.eventCreationMessage = null;
    }
    this.eventCreationMessage = paramA;
    Object localObject = paramA.c("eventData");
    if (localObject == null)
    {
      paramA = com.sessionm.b.a.a();
      paramA.a("error", "Invalid event data.");
      notifyContentAboutError(ActivityController.ContentError.MISSING_EVENT_DATA, null);
      return paramA;
    }
    paramA = ((com.sessionm.b.a)localObject).b("title");
    long l1 = ((com.sessionm.b.a)localObject).e("startDate");
    long l2 = ((com.sessionm.b.a)localObject).e("endDate");
    String str = ((com.sessionm.b.a)localObject).b("location");
    localObject = ((com.sessionm.b.a)localObject).b("notes");
    Intent localIntent = new Intent("android.intent.action.INSERT");
    if (as.d(getActivityContext())) {
      notifyContentAboutError(ActivityController.ContentError.CALENDAR_ACTIVITY_UNAVAILABLE_ERROR, null);
    }
    for (;;)
    {
      return null;
      localIntent.setType("vnd.android.cursor.item/event");
      localIntent.setData(CalendarContract.Events.CONTENT_URI);
      localIntent.putExtra("beginTime", l1 * 1000L);
      localIntent.putExtra("endTime", l2 * 1000L);
      localIntent.putExtra("title", paramA);
      localIntent.putExtra("description", (String)localObject);
      localIntent.putExtra("eventLocation", str);
      try
      {
        getActivityContext().startActivity(localIntent);
      }
      catch (Throwable paramA)
      {
        notifyContentAboutError(ActivityController.ContentError.CALENDAR_ACTIVITY_UNAVAILABLE_ERROR, null);
      }
    }
  }
  
  private com.sessionm.b.a handleDisableCloseButtonMessage(com.sessionm.b.a paramA)
  {
    if ((this.sessionMViewContainer == null) || (this.state == ActivityController.State.DISMISSED) || (this.state == ActivityController.State.DISMISSING)) {
      return null;
    }
    this.sessionMViewContainer.removeCloseButton();
    return null;
  }
  
  private void handleDisplayMessage(com.sessionm.b.a paramA)
  {
    if (!isFullScreenActivity())
    {
      launchDisplayContainer();
      this.layoutParams = getViewLayoutParams(paramA);
      resizeView(this.layoutParams);
    }
    dispatchMessage("show", getActivityData().toString());
  }
  
  @TargetApi(16)
  private com.sessionm.b.a handleEnableCloseButtonMessage(com.sessionm.b.a paramA)
  {
    if ((this.sessionMViewContainer == null) || (this.state == ActivityController.State.DISMISSED) || (this.state == ActivityController.State.DISMISSING)) {
      return null;
    }
    this.sessionMViewContainer.showCloseButton();
    return null;
  }
  
  private com.sessionm.b.a handleGetCookieMessage(com.sessionm.b.a paramA)
  {
    String str3 = null;
    String str1;
    Object localObject1;
    if (paramA.h("domain"))
    {
      str1 = paramA.b("domain");
      if (str1.startsWith(".")) {
        localObject1 = str1.substring(1);
      }
    }
    for (;;)
    {
      String str2;
      label60:
      Object localObject2;
      int i;
      label155:
      Object localObject4;
      if (paramA.h("path"))
      {
        str2 = paramA.b("path");
        if (paramA.h("name")) {
          str3 = paramA.b("name");
        }
        localObject1 = "http://" + (String)localObject1 + str2;
        paramA = new JSONArray();
        localObject2 = CookieManager.getInstance();
        if (str1 == null) {
          break label301;
        }
        localObject1 = ((CookieManager)localObject2).getCookie((String)localObject1);
        if (localObject1 == null) {
          break label301;
        }
        localObject1 = ((String)localObject1).split("; ");
        int j = localObject1.length;
        i = 0;
        if (i >= j) {
          break label301;
        }
        localObject2 = localObject1[i];
        localObject4 = ((String)localObject2).split("=");
        Object localObject3 = localObject4[0];
        localObject4 = localObject4[1];
        if ((str3 == null) || (str3.equals(localObject3))) {
          break label225;
        }
      }
      for (;;)
      {
        i += 1;
        break label155;
        localObject1 = str1;
        break;
        str2 = "";
        break label60;
        label225:
        Log.d("SessionM", "Found cookie: " + (String)localObject2);
        localObject2 = com.sessionm.b.a.a();
        ((com.sessionm.b.a)localObject2).a("domain", str1);
        ((com.sessionm.b.a)localObject2).a("path", str2);
        ((com.sessionm.b.a)localObject2).a("value", (String)localObject4);
        paramA.put(((com.sessionm.b.a)localObject2).c());
      }
      label301:
      localObject1 = com.sessionm.b.a.a();
      ((com.sessionm.b.a)localObject1).a("cookies", paramA.toString());
      return localObject1;
      localObject1 = null;
      str1 = null;
    }
  }
  
  private void handleGetSystemIdentifiersMessageAsync(com.sessionm.b.a paramA)
  {
    this.session.w().execute(new ActivityController.14(this, paramA));
  }
  
  private void handleMakeRequestMessageAsync(com.sessionm.b.a paramA)
  {
    String str = paramA.b("url");
    if (str == null)
    {
      notifyContentAboutError(ActivityController.ContentError.MISSING_URL, str);
      finishMessageHandling(paramA, null);
      return;
    }
    this.session.w().execute(new ActivityController.19(this, paramA));
  }
  
  private void handleOptinMessageAsync(com.sessionm.b.a paramA)
  {
    an localAn = this.session.a();
    if (!localAn.a())
    {
      if (Log.isLoggable("SessionM.Activity", 6)) {
        Log.e("SessionM.Activity", "Opt in command called but user was already opted in.");
      }
      com.sessionm.b.a localA = com.sessionm.b.a.a();
      localA.a("error", "Opt in command called but user was already opted in.");
      finishMessageHandling(paramA, localA);
    }
    localAn.a(this.session.f(), false, new ActivityController.18(this, paramA));
  }
  
  private void handlePlayMessage(com.sessionm.b.a paramA)
  {
    paramA = paramA.b("url");
    if (paramA == null) {
      notifyContentAboutError(ActivityController.ContentError.MISSING_URL, paramA);
    }
    if (!SessionMVideoActivity.start(getActivityContext(), paramA)) {
      notifyContentAboutError(ActivityController.ContentError.ANDROID_VIDEO_ERROR, paramA);
    }
  }
  
  private void handleStorePictureMessageAsync(com.sessionm.b.a paramA)
  {
    String str = paramA.b("url");
    if (str == null)
    {
      notifyContentAboutError(ActivityController.ContentError.IMAGE_SAVE_ERROR, str);
      finishMessageHandling(paramA, null);
      return;
    }
    if (getActivityContext().getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", getActivityContext().getPackageName()) != 0)
    {
      notifyContentAboutError(ActivityController.ContentError.IMAGE_SAVE_ERROR, str);
      finishMessageHandling(paramA, null);
      return;
    }
    presentStorePictureDialog(str, paramA);
  }
  
  private com.sessionm.b.a handleUpdateForecastMessage(com.sessionm.b.a paramA)
  {
    this.session.w().execute(new ActivityController.12(this, paramA));
    return null;
  }
  
  private com.sessionm.b.a handleUserActionMessage(com.sessionm.b.a paramA)
  {
    int j = 0;
    int k = paramA.d("code");
    com.sessionm.b.a localA = paramA.c("data");
    Object localObject = com.sessionm.api.h.values();
    int m = localObject.length;
    int i = 0;
    if (i < m)
    {
      paramA = localObject[i];
      if (paramA.a() != k) {}
    }
    for (;;)
    {
      localObject = paramA;
      if (paramA == null)
      {
        localObject = com.sessionm.api.h.n;
        ((com.sessionm.api.h)localObject).a(k);
      }
      paramA = new HashMap();
      if (localA != null)
      {
        String[] arrayOfString = localA.b();
        k = arrayOfString.length;
        i = j;
        for (;;)
        {
          if (i < k)
          {
            String str = arrayOfString[i];
            paramA.put(str, localA.b(str));
            i += 1;
            continue;
            i += 1;
            break;
          }
        }
      }
      this.session.a((com.sessionm.api.h)localObject, paramA);
      return null;
      paramA = null;
    }
  }
  
  private boolean isApplicationInstalled(String paramString)
  {
    List localList = getActivityContext().getPackageManager().getInstalledPackages(8192);
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equalsIgnoreCase(((PackageInfo)localList.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void loadPictureIntoGalleryApp(String paramString)
  {
    paramString = new ActivityController.SessionMMediaScannerConnectionClient(this, paramString, null, null);
    MediaScannerConnection localMediaScannerConnection = new MediaScannerConnection(this.session.f(), paramString);
    ActivityController.SessionMMediaScannerConnectionClient.access$1600(paramString, localMediaScannerConnection);
    localMediaScannerConnection.connect();
  }
  
  private void loadWebView(String paramString1, String paramString2, ActivityController.LoadWebViewListener paramLoadWebViewListener)
  {
    this.loadWebViewListener = paramLoadWebViewListener;
    getActivityContext().runOnUiThread(new ActivityController.3(this, paramString1, paramString2));
  }
  
  private void presentStorePictureDialog(String paramString, com.sessionm.b.a paramA)
  {
    if (paramString == null)
    {
      if (Log.isLoggable("SessionM.Activity", 6)) {
        Log.e("SessionM.Activity", "Null url provided for storePicture command.");
      }
      notifyContentAboutError(ActivityController.ContentError.IMAGE_SAVE_ERROR, paramString);
    }
    if (as.c(getActivityContext()))
    {
      if (Log.isLoggable("SessionM.Activity", 6)) {
        Log.e("SessionM.Activity", "Store picture not supported by this phone.");
      }
      notifyContentAboutError(ActivityController.ContentError.IMAGE_SAVE_ERROR, paramString);
    }
    new AlertDialog.Builder(getActivityContext()).setTitle("Save Image").setMessage("Download image to Picture gallery?").setNegativeButton("Cancel", new ActivityController.16(this, paramString, paramA)).setPositiveButton("Okay", new ActivityController.15(this, paramString, paramA)).setCancelable(true).show();
  }
  
  private void sendContentRequest(ActivityController.LoadContentListener paramLoadContentListener)
  {
    com.sessionm.e.a localA = new com.sessionm.e.a(com.sessionm.e.g.e, new Object[] { this.contentUrl });
    localA.a(e.c);
    localA.a(new ActivityController.2(this, paramLoadContentListener));
    localA.r();
  }
  
  private void sendJSLoadToContent()
  {
    dispatchMessage("load", getActivityData().toString());
  }
  
  private void sendMakeRequest(com.sessionm.b.a paramA)
  {
    String str = paramA.b("url");
    Object localObject = paramA.b("method");
    com.sessionm.b.a localA = paramA.c("params");
    com.sessionm.e.h localH = this.session.q();
    localObject = new com.sessionm.e.a(str, (String)localObject);
    ((com.sessionm.e.a)localObject).a(localA);
    localH.a((com.sessionm.e.a)localObject, new ActivityController.20(this, str, paramA));
    localH.b((com.sessionm.e.a)localObject);
  }
  
  private void setState(ActivityController.State paramState)
  {
    if ((this.state == ActivityController.State.PRESENTED) && (paramState != ActivityController.State.DISMISSING)) {
      return;
    }
    if ((paramState != this.state) && (this.stateChangeListener != null)) {
      this.stateChangeListener.onStateChange(this, paramState);
    }
    this.state = paramState;
  }
  
  @JavascriptInterface
  public void bridgeAction(String paramString)
  {
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", "Process JavaScriptInterface command: " + paramString);
    }
    getActivityContext().runOnUiThread(new ActivityController.8(this, paramString));
  }
  
  public void dismiss()
  {
    dismiss(true);
  }
  
  protected void dismiss(boolean paramBoolean)
  {
    getActivityContext().runOnUiThread(new ActivityController.5(this, paramBoolean));
  }
  
  void dismissBrowser()
  {
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", "dismissing browser with animation.");
    }
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    localTranslateAnimation.setDuration(300L);
    localTranslateAnimation.setAnimationListener(new ActivityController.13(this));
    this.browserViewContainer.getViewGroup().startAnimation(localTranslateAnimation);
    this.browserViewState = ActivityController.State.DISMISSING;
  }
  
  public void displayView()
  {
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", "Display view");
    }
    sendJSLoadToContent();
    if (getState() != ActivityController.State.PRESENTED) {}
    for (int i = 1; (i != 0) && (!isFullScreenActivity()); i = 0)
    {
      setState(ActivityController.State.PRESENTED);
      this.session.K();
      ad.a(ae.g, this.presentationStartTime - System.currentTimeMillis());
      return;
    }
    getViewContainer().getViewGroup().setVisibility(0);
    setState(ActivityController.State.PRESENTED);
    getViewContainer().showCloseButton();
  }
  
  public void executeJavascript(String paramString)
  {
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", String.format(Locale.US, "executing javascript: %s with contorller: %s", new Object[] { paramString, this }));
    }
    if (this.sessionMViewContainer != null) {
      this.sessionMViewContainer.executeJavascript(paramString);
    }
    while (!Log.isLoggable("SessionM.Activity", 3)) {
      return;
    }
    Log.d("SessionM.Activity", "Attempted to execute javascript on null webview container. Container may have been reset.");
  }
  
  protected void finishMessageHandling(com.sessionm.b.a paramA1, com.sessionm.b.a paramA2)
  {
    if (this.jsInterfaceListener != null)
    {
      localObject = paramA1.b("handler");
      if (localObject != null) {
        this.jsInterfaceListener.processedJSInterfaceCommand((String)localObject, paramA2);
      }
    }
    Object localObject = paramA1.c("callback");
    if (localObject == null) {
      return;
    }
    paramA1 = paramA2;
    if (paramA2 == null) {
      paramA1 = com.sessionm.b.a.a();
    }
    paramA1.a("_id", ((com.sessionm.b.a)localObject).b("_id"));
    executeJavascript(String.format(Locale.US, "%s(%s);", new Object[] { ((com.sessionm.b.a)localObject).b("name"), paramA1.toString() }));
  }
  
  public Activity getActivityContext()
  {
    return this.activityContext;
  }
  
  public SessionMViewContainer getBrowserView()
  {
    return this.browserViewContainer;
  }
  
  public JSInterfaceListener getJsInterfaceListener()
  {
    return this.jsInterfaceListener;
  }
  
  public ViewGroup.LayoutParams getLayoutParams()
  {
    return this.layoutParams;
  }
  
  public ActivityController.State getState()
  {
    try
    {
      ActivityController.State localState = this.state;
      return localState;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public SessionMViewContainer getViewContainer()
  {
    if (this.sessionMViewContainer == null)
    {
      this.sessionMViewContainer = new SessionMViewContainer(getActivityContext(), SessionMViewContainer.WebViewType.NORMAL, this);
      this.sessionMViewContainer.setListener(this);
      if (Log.isLoggable("SessionM.Activity", 3)) {
        Log.d("SessionM.Activity", "Created web view");
      }
    }
    return this.sessionMViewContainer;
  }
  
  protected com.sessionm.b.a handleBrowserMessage(com.sessionm.b.a paramA)
  {
    String str = paramA.b("action");
    paramA = paramA.b("url");
    if ((isFullScreenActivity()) && (str.equalsIgnoreCase("open")) && (paramA != null) && (paramA.length() > 0)) {
      if (this.browserViewContainer == null) {
        presentBrowser(paramA);
      }
    }
    for (;;)
    {
      return null;
      if (Log.isLoggable("SessionM.Activity", 6))
      {
        Log.e("SessionM.Activity", "Browser is already open. Ignoring open browser command.");
        continue;
        if ((isFullScreenActivity()) && (str.equalsIgnoreCase("close"))) {
          if (this.browserViewContainer != null) {
            removeBrowserView();
          } else if (Log.isLoggable("SessionM.Activity", 5)) {
            Log.w("SessionM.Activity", "Attempt to close browser via jsinterface but no browser was present.");
          }
        }
      }
    }
  }
  
  @TargetApi(16)
  protected void handleOpenMessageAsync(com.sessionm.b.a paramA)
  {
    String str = paramA.b("url");
    if (str == null)
    {
      paramA = String.format(Locale.US, "Open message has null URL, message: %s", new Object[] { paramA.toString() });
      this.session.a("open-error", paramA, null);
      dismiss();
      return;
    }
    if (isFullScreenActivity())
    {
      ActivityController localActivityController = createActivityController(true, getActivityContext(), str, new Object[0]);
      localActivityController.prepare(new ActivityController.11(this, paramA, localActivityController, str));
      return;
    }
    finishMessageHandling(paramA, null);
    dismiss(false);
    SessionMActivity.startSessionMActivity(getActivityContext(), str, null, false);
  }
  
  public boolean isBrowserDisplayed()
  {
    return this.browserViewContainer != null;
  }
  
  protected boolean isFullScreenActivity()
  {
    return getActivityContext() instanceof SessionMActivity;
  }
  
  public boolean isInForeground()
  {
    boolean bool = isFullScreenActivity();
    SessionMActivity localSessionMActivity = null;
    if (bool) {
      localSessionMActivity = (SessionMActivity)getActivityContext();
    }
    return (!bool) || ((localSessionMActivity != null) && (localSessionMActivity.isInForeground()));
  }
  
  @TargetApi(16)
  protected void launchDisplayContainer()
  {
    Activity localActivity = getActivityContext();
    ViewGroup localViewGroup = this.sessionMViewContainer.getViewGroup();
    if (localViewGroup.getParent() == null)
    {
      if (this.providedViewGroup != null) {
        this.providedViewGroup.addView(localViewGroup, localViewGroup.getLayoutParams());
      }
    }
    else {
      return;
    }
    localActivity.addContentView(localViewGroup, localViewGroup.getLayoutParams());
  }
  
  public void loadActivity(ActivityController.LoadContentListener paramLoadContentListener)
  {
    if (paramLoadContentListener == null) {
      throw new NullPointerException("LoadContentListener is null.");
    }
    if (this.contentUrl == null) {
      throw new NullPointerException("content url is null.");
    }
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", String.format("Loading content, url: %s", new Object[] { this.contentUrl }));
    }
    setState(ActivityController.State.LOADING_CONTENT);
    this.session.w().execute(new ActivityController.1(this, paramLoadContentListener));
  }
  
  void notifyAboutVideoError(String paramString, int paramInt1, int paramInt2)
  {
    paramString = com.sessionm.b.a.a();
    paramString.a("code", ActivityController.ContentError.ANDROID_VIDEO_ERROR.getCode());
    paramString.a("message", ActivityController.ContentError.ANDROID_VIDEO_ERROR.getMessage());
    paramString.a("what", paramInt1);
    paramString.a("extra", paramInt2);
    dispatchMessage("error", paramString.c().toString());
  }
  
  void notifyActivityPaused()
  {
    if (isBrowserDisplayed()) {
      this.browserViewContainer.onPause();
    }
    while (this.sessionMViewContainer == null) {
      return;
    }
    this.sessionMViewContainer.onPause();
  }
  
  void notifyActivityResumed()
  {
    if (isBrowserDisplayed()) {
      this.browserViewContainer.onResume();
    }
    while (this.sessionMViewContainer == null) {
      return;
    }
    this.sessionMViewContainer.onResume();
  }
  
  void notifyContentAboutError(ActivityController.ContentError paramContentError, String paramString)
  {
    com.sessionm.b.a localA = com.sessionm.b.a.a();
    localA.a("code", paramContentError.getCode());
    localA.a("message", paramContentError.getMessage());
    if (paramString != null) {
      localA.a("url", paramString);
    }
    dispatchMessage("error", localA.c().toString());
  }
  
  public void onClosePressed(SessionMViewContainer paramSessionMViewContainer)
  {
    getActivityContext().runOnUiThread(new ActivityController.9(this, paramSessionMViewContainer));
  }
  
  public void onFailure(SessionMViewContainer paramSessionMViewContainer, int paramInt, String paramString1, String paramString2)
  {
    if (paramSessionMViewContainer == this.browserViewContainer)
    {
      notifyContentAboutError(ActivityController.ContentError.BROWSER_LOAD_FAILED, paramString2);
      return;
    }
    if (this.loadWebViewListener != null) {
      getActivityContext().runOnUiThread(new ActivityController.10(this, paramString1));
    }
    for (;;)
    {
      ad.a(ae.i, 1L);
      return;
      notifyContentAboutError(ActivityController.ContentError.WEBVIEW_LOAD_ERROR, paramString2);
    }
  }
  
  public void onValueCallback(SessionMViewContainer paramSessionMViewContainer, ValueCallback<Uri> paramValueCallback)
  {
    if (isFullScreenActivity()) {
      ((SessionMActivity)getActivityContext()).setUploadFile(paramValueCallback);
    }
  }
  
  public void onVideoCompleted(SessionMVideoView paramSessionMVideoView)
  {
    dispatchMessage("video.ended", createDispatchJsonObject(paramSessionMVideoView).toString());
  }
  
  public boolean onVideoError(SessionMVideoView paramSessionMVideoView, int paramInt1, int paramInt2)
  {
    dispatchMessage("video.error", "{}");
    notifyAboutVideoError(paramSessionMVideoView.getUrl(), paramInt1, paramInt2);
    return false;
  }
  
  public void onVideoProgress(SessionMVideoView paramSessionMVideoView, int paramInt)
  {
    dispatchMessage("video.progress", createDispatchJsonObject(paramSessionMVideoView).toString());
  }
  
  public void onVideoStarted(SessionMVideoView paramSessionMVideoView)
  {
    dispatchMessage("video.playing", "{}");
  }
  
  public void onViewLoadStarted(SessionMViewContainer paramSessionMViewContainer, String paramString)
  {
    if ((isBrowserDisplayed()) && (paramSessionMViewContainer == this.browserViewContainer))
    {
      paramSessionMViewContainer = com.sessionm.b.a.a();
      paramSessionMViewContainer.a("url", paramString);
      dispatchMessage("loadURL.callback", paramSessionMViewContainer.c().toString());
    }
  }
  
  public void onViewLoaded(SessionMViewContainer paramSessionMViewContainer, String paramString)
  {
    if ((isBrowserDisplayed()) && (this.browserViewContainer == paramSessionMViewContainer))
    {
      if (this.browserViewState == ActivityController.State.LOADING_WEBVIEW) {
        this.browserViewState = ActivityController.State.PRESENTED;
      }
      this.lastBrowserUrl = paramString;
    }
    do
    {
      do
      {
        return;
        paramSessionMViewContainer = getState();
      } while (paramSessionMViewContainer == ActivityController.State.PRESENTED);
      if (paramSessionMViewContainer != ActivityController.State.DISMISSED) {
        break;
      }
    } while (!Log.isLoggable("SessionM.Activity", 3));
    Log.d("SessionM.Activity", "Cancel display due to DISMISSED state.");
    return;
    if (this.loadWebViewListener == null) {
      displayView();
    }
    for (;;)
    {
      setState(ActivityController.State.LOADED_WEBVIEW);
      return;
      this.loadWebViewListener.onWebViewLoaded();
      this.loadWebViewListener = null;
    }
  }
  
  public void prepare(ActivityController.PrepareListener paramPrepareListener)
  {
    if (paramPrepareListener == null) {
      throw new NullPointerException("Prepare listener is null");
    }
    if ((!this.session.g().a()) && (!this.session.a().a()))
    {
      setState(ActivityController.State.UNPRESENTABLE);
      paramPrepareListener.onFailure(new IllegalStateException("Session state is " + this.session.g()));
      return;
    }
    ActivityController.6 local6 = new ActivityController.6(this, paramPrepareListener);
    if (getState() == ActivityController.State.LOADED_CONTENT)
    {
      loadWebView(this.contentUrl, this.activityContent, local6);
      return;
    }
    loadActivity(new ActivityController.7(this, local6, paramPrepareListener));
  }
  
  public void present(boolean paramBoolean)
  {
    getActivityContext().runOnUiThread(new ActivityController.4(this));
  }
  
  public void presentBrowser(String paramString)
  {
    int i = 0;
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", String.format("present browser with url: %s", new Object[] { paramString }));
    }
    if (this.browserViewState != ActivityController.State.DISMISSED) {
      paramString = this.lastBrowserUrl;
    }
    for (;;)
    {
      Object localObject = new SessionMViewContainer(getActivityContext(), SessionMViewContainer.WebViewType.BROWSER, this);
      setBrowserView((SessionMViewContainer)localObject);
      ((SessionMViewContainer)localObject).loadUrl(paramString);
      this.lastBrowserUrl = paramString;
      paramString = ((SessionMViewContainer)localObject).getViewGroup();
      if (i != 0)
      {
        localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
        ((Animation)localObject).setDuration(300L);
        this.browserViewContainer.getViewGroup().startAnimation((Animation)localObject);
      }
      getActivityContext().addContentView(paramString, paramString.getLayoutParams());
      if (Log.isLoggable("SessionM.Activity", 3)) {
        Log.d("SessionM.Activity", "browser presented.");
      }
      this.browserViewState = ActivityController.State.LOADING_WEBVIEW;
      return;
      i = 1;
    }
  }
  
  public void removeBrowserView()
  {
    this.browserViewContainer.onPause();
    this.browserViewContainer.getViewGroup().setVisibility(8);
    if (Log.isLoggable("SessionM.Activity", 3)) {
      Log.d("SessionM.Activity", String.format("Removing browser view %s", new Object[] { this.browserViewContainer }));
    }
    removeView(this.browserViewContainer);
    this.browserViewContainer = null;
    this.browserViewState = ActivityController.State.DISMISSED;
    executeJavascript("GreyhoundEventDispatcher.dispatch('browserDidClose',{});");
    this.lastBrowserUrl = null;
  }
  
  protected void removeView(SessionMViewContainer paramSessionMViewContainer)
  {
    if (paramSessionMViewContainer == null) {}
    ViewParent localViewParent;
    do
    {
      do
      {
        return;
        if (Log.isLoggable("SessionM.Activity", 3)) {
          Log.d("SessionM.Activity", String.format("Removing view: %s", new Object[] { paramSessionMViewContainer }));
        }
        localViewParent = paramSessionMViewContainer.getViewGroup().getParent();
        if ((localViewParent == null) || (!(localViewParent instanceof ViewGroup))) {
          break;
        }
        ((ViewGroup)localViewParent).removeView(paramSessionMViewContainer.getViewGroup());
        paramSessionMViewContainer.destroy();
      } while (!Log.isLoggable("SessionM.Activity", 3));
      Log.d("SessionM.Activity", String.format("View removed and destroyed %s.", new Object[] { paramSessionMViewContainer }));
      return;
      if (Log.isLoggable("SessionM.Activity", 3)) {
        Log.d("SessionM.Activity", String.format("Unable to remove view: %s", new Object[] { paramSessionMViewContainer }));
      }
    } while (localViewParent == null);
    Log.e("SessionM.Activity", "Problem removing web view from view hierarchy because parent is not ViewGroup, parent: " + localViewParent);
  }
  
  protected void replaceDisplayContainer(ActivityController paramActivityController)
  {
    this.session.a(paramActivityController);
    ((SessionMActivity)getActivityContext()).pushActivityController(paramActivityController);
    this.session.b(this);
    setState(ActivityController.State.DISMISSED);
    paramActivityController.sendJSLoadToContent();
  }
  
  void resetWebView()
  {
    if (this.browserViewContainer != null)
    {
      removeView(this.browserViewContainer);
      this.browserViewContainer = null;
    }
    if (this.sessionMViewContainer != null)
    {
      removeView(this.sessionMViewContainer);
      this.sessionMViewContainer = null;
    }
  }
  
  protected void resizeView(ViewGroup.LayoutParams paramLayoutParams)
  {
    getViewContainer().getViewGroup().setLayoutParams(paramLayoutParams);
  }
  
  void setActivityContent(String paramString)
  {
    this.activityContent = paramString;
    if (this.activityContent != null) {
      setState(ActivityController.State.LOADED_CONTENT);
    }
  }
  
  public void setBrowserView(SessionMViewContainer paramSessionMViewContainer)
  {
    this.browserViewContainer = paramSessionMViewContainer;
    if (this.browserViewContainer != null) {
      this.browserViewContainer.setListener(this);
    }
  }
  
  void setContentData(com.sessionm.b.a paramA)
  {
    this.contentData = paramA;
  }
  
  void setContentData2(com.sessionm.b.a paramA)
  {
    this.contentData2 = paramA;
  }
  
  public void setJsInterfaceListener(JSInterfaceListener paramJSInterfaceListener)
  {
    this.jsInterfaceListener = paramJSInterfaceListener;
  }
  
  public void setProvidedView(FrameLayout paramFrameLayout)
  {
    this.providedViewGroup = paramFrameLayout;
  }
  
  public void setStateChangeListener(ActivityController.StateChangeListener paramStateChangeListener)
  {
    this.stateChangeListener = paramStateChangeListener;
  }
}
