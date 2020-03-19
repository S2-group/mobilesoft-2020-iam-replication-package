package jp.naver.line.android.sdk.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import jp.naver.line.android.sdk.LineSdkConfig;
import jp.naver.line.android.sdk.auth.bo.LineA2ALoginTask;
import jp.naver.line.android.sdk.auth.bo.LineConnector;
import jp.naver.line.android.sdk.auth.bo.LineWebLoginTask;
import jp.naver.line.android.sdk.auth.bo.LoginListenerHelper;
import jp.naver.line.android.sdk.auth.bo.LogoutHandler;
import jp.naver.line.android.sdk.auth.bo.RequestAccessTokenTask;
import jp.naver.line.android.sdk.auth.bo.RequestAccessTokenTask.AccessTokenListener;
import jp.naver.line.android.sdk.auth.model.LineAuthInfo;
import jp.naver.line.android.sdk.auth.util.StaticObjectHolder;
import jp.naver.line.android.sdk.commons.StringUtils;

public final class LineAuthManager
{
  private static final String EXTRAS_KEY_FROM_AUTH_COMP_ACT = LineAuthManager.class.getName() + ".fromAuthCompAct";
  private static final String EXTRAS_KEY_FROM_AUTH_FUTURE_KEY = LineAuthManager.class.getName() + ".futureKey";
  private static final LineAuthManagerHolder holder = new LineAuthManagerHolder();
  volatile LineA2ALoginTask a2aLoginTask;
  private final String authScheme;
  private final String channelId;
  volatile jp.naver.line.android.sdk.auth.bo.Handler handler;
  private long timestamp;
  
  private LineAuthManager(String paramString1, String paramString2)
  {
    this.channelId = paramString1;
    this.authScheme = paramString2;
  }
  
  private boolean canExecute(Activity paramActivity, Object paramObject)
  {
    if (paramActivity == null) {
      throw new IllegalArgumentException("Activity is null.");
    }
    if (paramObject == null) {
      throw new IllegalArgumentException("Listener is null.");
    }
    long l = System.currentTimeMillis();
    if (500L < l - this.timestamp)
    {
      this.timestamp = l;
      return true;
    }
    return false;
  }
  
  public static final Intent createIntentIfKilledProcess(Activity paramActivity, Class<? extends Activity> paramClass, long paramLong)
  {
    paramActivity = new Intent(paramActivity, paramClass);
    paramActivity.putExtra(EXTRAS_KEY_FROM_AUTH_COMP_ACT, true);
    paramActivity.putExtra(EXTRAS_KEY_FROM_AUTH_FUTURE_KEY, paramLong);
    return paramActivity;
  }
  
  public static void dispose(LineAuthManager paramLineAuthManager)
  {
    synchronized (holder)
    {
      if (holder.instance == paramLineAuthManager) {
        holder.instance = null;
      }
      if (holder.instance == null) {
        StaticObjectHolder.dispose();
      }
      return;
    }
  }
  
  @Deprecated
  public static void disposeCurrentInstanceHandler()
  {
    synchronized (holder)
    {
      if (holder.instance != null) {
        holder.instance.handler = null;
      }
      return;
    }
  }
  
  private void executeHandler(jp.naver.line.android.sdk.auth.bo.Handler paramHandler)
  {
    synchronized (holder)
    {
      holder.instance.handler = paramHandler;
      paramHandler.execute();
      return;
    }
  }
  
  static LineAuthManager getCurrentInstance()
  {
    synchronized (holder)
    {
      LineAuthManager localLineAuthManager = holder.instance;
      return localLineAuthManager;
    }
  }
  
  static jp.naver.line.android.sdk.auth.bo.Handler getCurrentInstanceHandler()
  {
    for (;;)
    {
      synchronized (holder)
      {
        if (holder.instance != null)
        {
          jp.naver.line.android.sdk.auth.bo.Handler localHandler = holder.instance.handler;
          return localHandler;
        }
      }
      Object localObject2 = null;
    }
  }
  
  public static LineAuthManager getInstance(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    if (StringUtils.isEmpty(paramString1)) {
      throw new IllegalArgumentException("channelId is empty.");
    }
    if (StringUtils.isEmpty(paramString2)) {
      throw new IllegalArgumentException("authScheme is empty.");
    }
    synchronized (holder)
    {
      if (holder.instance != null)
      {
        if ((holder.instance.channelId.equals(paramString1)) && (holder.instance.authScheme.equals(paramString2)))
        {
          paramString1 = holder.instance;
          return paramString1;
        }
        dispose(holder.instance);
      }
      LineAuthManagerHolder localLineAuthManagerHolder2 = holder;
      paramString1 = new LineAuthManager(paramString1, paramString2);
      localLineAuthManagerHolder2.instance = paramString1;
      return paramString1;
    }
  }
  
  public static final boolean isFromAuthCompleteActivity(Intent paramIntent)
  {
    return paramIntent.getBooleanExtra(EXTRAS_KEY_FROM_AUTH_COMP_ACT, false);
  }
  
  public static boolean isLineInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if ("jp.naver.line.android".equals(((ApplicationInfo)paramContext.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isLoginSupportedLineInstalled(Context paramContext)
  {
    if (!isLineInstalled(paramContext)) {}
    Intent localIntent;
    do
    {
      return false;
      localIntent = new Intent("jp.naver.line.android.intent.action.APPAUTH", null);
    } while (paramContext.getPackageManager().queryIntentActivities(localIntent, 0).isEmpty());
    return true;
  }
  
  private static final void notifyAuthFutureResultToLoginListener(LoginListener paramLoginListener, LineAuthFuture paramLineAuthFuture)
  {
    if (paramLineAuthFuture.isSuccess()) {
      paramLoginListener.onSuccess(paramLineAuthFuture.getLineAuth());
    }
    do
    {
      return;
      if (paramLineAuthFuture.isCanceled())
      {
        paramLoginListener.onCancel();
        return;
      }
    } while (!paramLineAuthFuture.isFailed());
    paramLoginListener.onFail(paramLineAuthFuture.getAuthException());
  }
  
  private static final void notifyOnProcessing(OnProcessingIfKilledProcessListener paramOnProcessingIfKilledProcessListener)
  {
    if (paramOnProcessingIfKilledProcessListener != null) {}
    try
    {
      paramOnProcessingIfKilledProcessListener.onProcessing();
      return;
    }
    catch (Exception paramOnProcessingIfKilledProcessListener)
    {
      while (!Log.isLoggable("LineSDK", 3)) {}
      Log.w("LineSDK", "failed OnProcessingIfKilledProcessListener.onProcessing()", paramOnProcessingIfKilledProcessListener);
    }
  }
  
  static void requestAccessToken(Context paramContext, String paramString1, String paramString2, LineAuthInfo paramLineAuthInfo, RequestAccessTokenTask.AccessTokenListener paramAccessTokenListener)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineLoginTask] (3) AccessToken : start");
    }
    new RequestAccessTokenTask(paramContext, paramString1, paramLineAuthInfo.getRequestToken(), paramString2, paramAccessTokenListener, LineSdkConfig.isUseProgress()).execute(new Void[0]);
  }
  
  public static final void setOrExecuteLoginListenerIfKilledProcess(Intent paramIntent, LoginListener paramLoginListener, OnProcessingIfKilledProcessListener paramOnProcessingIfKilledProcessListener)
  {
    if (isFromAuthCompleteActivity(paramIntent)) {
      try
      {
        if ((setOrExecuteLoginListenerIfKilledProcess(paramIntent, paramLoginListener)) && (paramOnProcessingIfKilledProcessListener != null))
        {
          if (Looper.myLooper() == Looper.getMainLooper())
          {
            notifyOnProcessing(paramOnProcessingIfKilledProcessListener);
            return;
          }
          new android.os.Handler(Looper.getMainLooper()).post(new Runnable()
          {
            public void run()
            {
              LineAuthManager.notifyOnProcessing(this.val$onProcessingIfKilledProcessListener);
            }
          });
          return;
        }
      }
      catch (AuthException paramIntent)
      {
        LoginListenerHelper.notifyLoginFailed(paramLoginListener, paramIntent, LoginStep.REQUEST_TOKEN_LINE, null);
      }
    }
  }
  
  private static final boolean setOrExecuteLoginListenerIfKilledProcess(final Intent paramIntent, LoginListener paramLoginListener)
    throws AuthException
  {
    boolean bool = paramIntent.getBooleanExtra(EXTRAS_KEY_FROM_AUTH_COMP_ACT, false);
    long l = paramIntent.getLongExtra(EXTRAS_KEY_FROM_AUTH_FUTURE_KEY, -1L);
    if ((bool) && (l != -1L))
    {
      paramIntent = LineAuthFuture.getCurrentFuture(l);
      if (paramIntent != null)
      {
        bool = paramIntent.setLoginListenerIfProcessing(paramLoginListener);
        if (!bool)
        {
          if (Looper.getMainLooper() != Looper.myLooper()) {
            break label71;
          }
          notifyAuthFutureResultToLoginListener(paramLoginListener, paramIntent);
        }
        for (;;)
        {
          LineAuthFuture.clearCurrentFuture(l);
          return bool;
          label71:
          new android.os.Handler(Looper.getMainLooper()).post(new Runnable()
          {
            public void run()
            {
              LineAuthManager.notifyAuthFutureResultToLoginListener(this.val$listener, paramIntent);
            }
          });
        }
      }
    }
    throw new AuthException(AuthException.Type.ILLEGAL_PARAMETER, "LineAuthFuture was not found.");
  }
  
  @Deprecated
  public final void a2aLogin(Activity paramActivity, LoginListener paramLoginListener)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineAuthManager] a2aLogin()");
    }
    if (!canExecute(paramActivity, paramLoginListener)) {
      return;
    }
    this.a2aLoginTask = new LineA2ALoginTask(paramActivity, paramLoginListener, this.channelId, this.authScheme);
    this.a2aLoginTask.execute();
  }
  
  public LineAuthInfo getUnproccesedLineAuthInfo(Context paramContext)
  {
    return LineConnector.getSavedLineAuthInfo(paramContext);
  }
  
  public void login(Activity paramActivity, LoginListener paramLoginListener)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineAuthManager] login()");
    }
    if (isLoginSupportedLineInstalled(paramActivity))
    {
      a2aLogin(paramActivity, paramLoginListener);
      return;
    }
    webLogin(paramActivity, paramLoginListener);
  }
  
  public void logout(Activity paramActivity, LogoutListener paramLogoutListener, String paramString)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineAuthManager] logout()");
    }
    if (!canExecute(paramActivity, paramLogoutListener)) {
      return;
    }
    executeHandler(new LogoutHandler(paramActivity, this.channelId, paramString, paramLogoutListener));
  }
  
  public boolean retryLogin(final Context paramContext, LoginListener paramLoginListener)
  {
    LineAuthInfo localLineAuthInfo = LineConnector.getSavedLineAuthInfo(paramContext);
    String str = LineConnector.getSavedOtp(LineConnector.getSavedInfoPreStartLine(paramContext));
    if ((localLineAuthInfo == null) || (str == null)) {
      return false;
    }
    requestAccessToken(paramContext, this.channelId, str, localLineAuthInfo, new AccessTokenListenerForLonginListener(paramLoginListener)
    {
      public void onSuccess(LineAuth paramAnonymousLineAuth)
      {
        LineConnector.clearSavedInfo(paramContext);
        super.onSuccess(paramAnonymousLineAuth);
      }
    });
    return true;
  }
  
  @Deprecated
  public final void startLoginPage(Activity paramActivity, LoginListener paramLoginListener)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineAuthManager] startLoginPage()");
    }
    if (!canExecute(paramActivity, paramLoginListener)) {
      return;
    }
    LineLoginActivity.startActivity(paramActivity, paramLoginListener, this.channelId);
  }
  
  @Deprecated
  public final void webLogin(Activity paramActivity, LoginListener paramLoginListener)
  {
    if (Log.isLoggable("LineSDK", 3)) {
      Log.v("LineSDK", "[LineAuthManager] webLogin()");
    }
    if (!canExecute(paramActivity, paramLoginListener)) {
      return;
    }
    new LineWebLoginTask(paramActivity, paramLoginListener, this.channelId).execute();
  }
  
  static final class LineAuthManagerHolder
  {
    volatile LineAuthManager instance;
    
    LineAuthManagerHolder() {}
  }
}
