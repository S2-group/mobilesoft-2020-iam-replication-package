package com.chegg.sdk.auth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import com.chegg.config.Foundation;
import com.chegg.sdk.R.string;
import com.chegg.sdk.R.style;
import com.chegg.sdk.analytics.k;
import com.chegg.sdk.log.Logger;
import com.chegg.sdk.network.apiclient.APIError;
import com.chegg.sdk.network.apiclient.APIRequestCallback;
import com.chegg.sdk.network.apiclient.BackgroundThreadExecutor;
import com.chegg.sdk.network.apiclient.ExecutionInfo;
import com.chegg.sdk.network.apiclient.MainThreadExecutor;
import com.chegg.sdk.network.apiclient.NetworkResult;
import com.chegg.sdk.utils.NetworkUtils;
import com.chegg.sdk.utils.Utils;
import com.facebook.a;
import e.a.a.a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.j;

@Singleton
public class SigninService
{
  public static final String SIGNIN_TAG = "CHEGG.SIGNIN.REQUESTS";
  private static final String TAG = "CheggAuth";
  private final e authApi;
  private w authSecurityResolver;
  private final BackgroundThreadExecutor backgroundThreadExecutor;
  private final ah cheggAccountManager;
  private final c eventBus;
  private final am facebookService;
  private String facebookUserEmail = "";
  private volatile boolean isSigninInProgress;
  private volatile boolean isSignupInProgress;
  private Boolean isSuperAuthEnabled = Boolean.valueOf(true);
  private final MainThreadExecutor mainThreadExecutor;
  private List<av> signInPlugins;
  private final k signinAnalytics;
  private final as signinPluginExecutor;
  private final ay superAuthApi;
  private final bg userServiceApi;
  
  @Inject
  public SigninService(e paramE, ay paramAy, ah paramAh, am paramAm, as paramAs, c paramC, BackgroundThreadExecutor paramBackgroundThreadExecutor, MainThreadExecutor paramMainThreadExecutor, bg paramBg, Foundation paramFoundation, k paramK, w paramW, List<av> paramList)
  {
    this.authApi = paramE;
    this.superAuthApi = paramAy;
    this.cheggAccountManager = paramAh;
    this.facebookService = paramAm;
    this.signinAnalytics = paramK;
    this.signinPluginExecutor = paramAs;
    this.eventBus = paramC;
    this.backgroundThreadExecutor = paramBackgroundThreadExecutor;
    this.mainThreadExecutor = paramMainThreadExecutor;
    this.userServiceApi = paramBg;
    this.eventBus.a(this);
    this.authSecurityResolver = paramW;
    this.signInPlugins = paramList;
    this.isSuperAuthEnabled = paramFoundation.getIsSuperAuthEnabled();
    if (isSignedIn())
    {
      this.signinAnalytics.e();
      refreshMyUserInfo();
      return;
    }
    this.signinAnalytics.b();
  }
  
  private static boolean areMoreCheggAppsInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    boolean bool = false;
    paramContext = paramContext.getInstalledApplications(0);
    if (paramContext != null)
    {
      int m = paramContext.size();
      int i = 0;
      int j = 0;
      for (;;)
      {
        k = i;
        if (i >= 2) {
          break;
        }
        k = i;
        if (j >= m) {
          break;
        }
        k = i;
        if (((ApplicationInfo)paramContext.get(j)).packageName.startsWith("com.chegg")) {
          k = i + 1;
        }
        j += 1;
        i = k;
      }
    }
    int k = 0;
    if (k >= 2) {
      bool = true;
    }
    return bool;
  }
  
  private void cancelLoginRequests()
  {
    if (this.isSuperAuthEnabled.booleanValue())
    {
      this.superAuthApi.a();
      return;
    }
    this.authApi.a();
  }
  
  private void executeOAuthSignInRequests(String paramString1, String paramString2)
    throws APIError
  {
    Logger.tag("CheggAuth").i("oauth signing in with user name and password", new Object[0]);
    OAuthResponse localOAuthResponse = this.authApi.a(paramString1, paramString2);
    if (localOAuthResponse.getAccessToken() != null)
    {
      UserInfo localUserInfo = getUserInfo(localOAuthResponse);
      this.cheggAccountManager.a(localUserInfo, localOAuthResponse, paramString1, paramString2);
      executePostSignInRequests();
      Logger.tag("CheggAuth").i("oauth sign in completed successfully with user name and password", new Object[0]);
      return;
    }
    Logger.tag("CheggAuth").i("oauth sign in failed with user name and password", new Object[0]);
  }
  
  private void executePostSignInRequests()
    throws APIError
  {
    Logger.tag("CheggAuth").i("executing sign in plugins", new Object[0]);
    this.signinPluginExecutor.a(this.signInPlugins);
  }
  
  private void executeSuperAuthSignInRequests(String paramString1, String paramString2)
    throws APIError
  {
    Logger.tag("CheggAuth").i("superauth signing in with user name and password", new Object[0]);
    bb localBb = this.superAuthApi.a(paramString1, paramString2);
    if (localBb.a() != null)
    {
      UserInfo localUserInfo = getUserInfo(localBb);
      this.cheggAccountManager.a(localUserInfo, localBb, paramString1, paramString2);
      executePostSignInRequests();
      Logger.tag("CheggAuth").i("superauth sign in completed successfully with user name and password", new Object[0]);
      return;
    }
    Logger.tag("CheggAuth").i("superauth sign in failed with user name and password", new Object[0]);
  }
  
  private void executeSuperAuthSignUpRequests(String paramString1, String paramString2, String paramString3, z paramZ)
    throws APIError
  {
    try
    {
      executeSuperAuthSignInRequests(paramString1, paramString2);
      this.isSignupInProgress = false;
      this.signinAnalytics.g(paramString3);
      onSigninCompleted(paramZ, al.b.a);
      this.eventBus.d(bf.b());
      return;
    }
    catch (APIError localAPIError)
    {
      if (this.authSecurityResolver.a(localAPIError))
      {
        this.authSecurityResolver.a(localAPIError, new -..Lambda.SigninService.9_mYQtPEGF1L1RSFUB2FiSLrb3g(this, paramString1, paramString2, paramString3, paramZ, localAPIError), paramZ, paramString3);
        return;
      }
      throw localAPIError;
    }
  }
  
  private UserInfo getUserInfo(OAuthResponse paramOAuthResponse)
    throws APIError
  {
    Logger.tag("CheggAuth").d("fetching user info", new Object[0]);
    return this.userServiceApi.a(paramOAuthResponse.getAccessToken());
  }
  
  private UserInfo getUserInfo(bb paramBb)
    throws APIError
  {
    Logger.tag("CheggAuth").d("fetching user info", new Object[0]);
    return this.userServiceApi.a(paramBb.a());
  }
  
  private void notifySignInCompleted(z paramZ, al.b paramB)
  {
    if (paramZ != null) {
      this.mainThreadExecutor.execute(new -..Lambda.SigninService.k-Q33bsJyv0xloewT1q1TBIw4kI(paramZ, paramB));
    }
  }
  
  private void notifySignout(z paramZ, boolean paramBoolean)
  {
    if (paramZ != null) {
      paramZ.onSignout();
    }
    this.eventBus.d(bf.a(paramBoolean));
  }
  
  private void onAccountAdded()
  {
    Logger.tag("CheggAuth").d("executing sign in plugins after account was added", new Object[0]);
    this.signinPluginExecutor.a(this.signInPlugins, new z()
    {
      public void onAuthenticateCompleted(al.b paramAnonymousB)
      {
        SigninService.this.signinAnalytics.e();
        SigninService.this.signinAnalytics.a("SSO");
        if (paramAnonymousB != al.b.a)
        {
          Logger.tag("CheggAuth").e("some sign in plugins failed", new Object[0]);
          SigninService.this.eventBus.d(bf.d());
        }
        SigninService.this.eventBus.d(bf.a());
      }
    });
  }
  
  private void onAccountRemoved(boolean paramBoolean)
  {
    clear();
    this.eventBus.d(bf.a(paramBoolean));
  }
  
  private void onErrorInSignUpImpl(APIError paramAPIError, String paramString, z paramZ)
  {
    this.isSignupInProgress = false;
    paramAPIError = al.a(paramAPIError);
    this.signinAnalytics.a(paramAPIError.c(), paramAPIError.b(), paramString);
    onSigninCompleted(paramZ, paramAPIError);
  }
  
  private void onSignInFailed(z paramZ, APIError paramAPIError, UserService.LoginType paramLoginType, String paramString)
  {
    onSignInFailed(paramZ, al.a(paramAPIError), paramLoginType, paramString);
  }
  
  private void onSignInSuccess(String paramString, z paramZ)
  {
    this.signinAnalytics.a(paramString);
    onSigninCompleted(paramZ, al.b.a);
    this.eventBus.d(bf.a());
  }
  
  private void onSigninCompleted(z paramZ, al.b paramB)
  {
    try
    {
      this.isSigninInProgress = false;
      if ((paramB != al.b.a) && (paramB != al.b.f))
      {
        clear();
        this.cheggAccountManager.u();
      }
      notifySignInCompleted(paramZ, paramB);
      return;
    }
    finally {}
  }
  
  private void refreshMyUserInfo()
  {
    this.backgroundThreadExecutor.execute(new -..Lambda.SigninService.9WuzyBvrYGmud0dzdUz42YWLvuU(this));
  }
  
  private void resetPasswordImpl(String paramString, final z paramZ)
  {
    if (this.isSuperAuthEnabled.booleanValue())
    {
      this.superAuthApi.a(paramString, new APIRequestCallback()
      {
        public void a(ExecutionInfo paramAnonymousExecutionInfo, Void paramAnonymousVoid)
        {
          paramZ.onAuthenticateCompleted(al.b.a);
        }
        
        public void onError(ExecutionInfo paramAnonymousExecutionInfo, APIError paramAnonymousAPIError)
        {
          paramZ.onAuthenticateCompleted(al.a(paramAnonymousAPIError));
        }
      });
      return;
    }
    this.authApi.a(paramString, new APIRequestCallback()
    {
      public void a(ExecutionInfo paramAnonymousExecutionInfo, Void paramAnonymousVoid)
      {
        paramZ.onAuthenticateCompleted(al.b.a);
      }
      
      public void onError(ExecutionInfo paramAnonymousExecutionInfo, APIError paramAnonymousAPIError)
      {
        paramZ.onAuthenticateCompleted(al.a(paramAnonymousAPIError));
      }
    });
  }
  
  public static void showSignOutDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext, R.style.CustomAlertDialogStyle).setTitle(R.string.signout_dialog_title);
    int i;
    if (areMoreCheggAppsInstalled(paramContext)) {
      i = R.string.signout_dialog_multiple_apps_message;
    } else {
      i = R.string.signout_dialog_single_app_message;
    }
    localBuilder.setMessage(i).setPositiveButton(R.string.ok, paramOnClickListener).setNegativeButton(R.string.cancel, null).create().show();
  }
  
  private void signInCheggImpl(String paramString1, String paramString2, String paramString3, z paramZ)
  {
    try
    {
      if (this.isSuperAuthEnabled.booleanValue()) {
        executeSuperAuthSignInRequests(paramString1, paramString2);
      } else {
        executeOAuthSignInRequests(paramString1, paramString2);
      }
      onSignInSuccess(paramString3, paramZ);
      return;
    }
    catch (APIError localAPIError)
    {
      if (this.authSecurityResolver.a(localAPIError))
      {
        this.authSecurityResolver.a(localAPIError, new -..Lambda.SigninService.yh1rxChVLOv9gWdO09rTZDFSTA0(this, paramString1, paramString2, paramString3, paramZ), paramZ, paramString3);
        return;
      }
      onSignInFailed(paramZ, localAPIError, UserService.LoginType.Chegg, paramString3);
    }
  }
  
  private void signInFacebookWithOAuthPasswordImpl(a paramA, String paramString1, String paramString2, z paramZ, au paramAu)
  {
    try
    {
      Logger.tag("CheggAuth").i("oauth signing in to Chegg with Facebook access token", new Object[0]);
      OAuthResponse localOAuthResponse = this.authApi.a(paramA, paramString1);
      UserInfo localUserInfo = getUserInfo(localOAuthResponse);
      this.cheggAccountManager.a(localUserInfo, localOAuthResponse, this.facebookUserEmail, paramString1, paramA);
      executePostSignInRequests();
      Logger.tag("CheggAuth").i("oauth sign in completed successfully with Facebook access token", new Object[0]);
      onSignInSuccess(paramString2, paramZ);
      return;
    }
    catch (APIError paramA)
    {
      if (this.authSecurityResolver.a(paramA))
      {
        this.authSecurityResolver.a(paramA, paramAu, paramZ, paramString2);
        return;
      }
      onSignInFailed(paramZ, paramA, UserService.LoginType.Facebook, paramString2);
    }
  }
  
  private void signInFacebookWithSuperAuthPasswordImpl(a paramA, String paramString1, String paramString2, z paramZ, au paramAu)
  {
    try
    {
      Logger.tag("CheggAuth").i("superauth signing in to Chegg with Facebook access token", new Object[0]);
      bb localBb = this.superAuthApi.a(paramA, paramString1);
      UserInfo localUserInfo = getUserInfo(localBb);
      this.cheggAccountManager.a(localUserInfo, localBb, this.facebookUserEmail, paramString1, paramA);
      executePostSignInRequests();
      Logger.tag("CheggAuth").i("superauth sign in completed successfully with Facebook access token", new Object[0]);
      onSignInSuccess(paramString2, paramZ);
      return;
    }
    catch (APIError paramA)
    {
      if (this.authSecurityResolver.a(paramA))
      {
        this.authSecurityResolver.a(paramA, paramAu, paramZ, paramString2);
        return;
      }
      onSignInFailed(paramZ, paramA, UserService.LoginType.Facebook, paramString2);
    }
  }
  
  private void signUpImpl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, z paramZ)
  {
    try
    {
      Logger.tag("CheggAuth").d("signing up", new Object[0]);
      signUpImpl(paramString1, paramString2, paramString3, paramString4, paramString5);
      Logger.tag("CheggAuth").d("sign up completed successfully", new Object[0]);
      executeSuperAuthSignUpRequests(paramString1, paramString2, paramString6, paramZ);
      return;
    }
    catch (APIError paramString1)
    {
      onErrorInSignUpImpl(paramString1, paramString6, paramZ);
    }
  }
  
  private boolean validEmail(String paramString)
  {
    return Utils.validEmailFormat(paramString);
  }
  
  private boolean validPassword(String paramString)
  {
    return Utils.isValidPasswordFormat(paramString);
  }
  
  private boolean validateParams(String paramString1, String paramString2)
  {
    return (validEmail(paramString1)) && (validPassword(paramString2));
  }
  
  public void addSignInPlugin(av paramAv)
  {
    this.signInPlugins.add(paramAv);
  }
  
  public void cancelSignin(String paramString)
  {
    this.signinAnalytics.f(paramString);
  }
  
  void clear()
  {
    this.facebookUserEmail = "";
    cancelLoginRequests();
    this.signinAnalytics.b();
    this.isSigninInProgress = false;
    this.isSignupInProgress = false;
  }
  
  public boolean isFacebookRequestCode(int paramInt)
  {
    return this.facebookService.a(paramInt);
  }
  
  public boolean isSignedIn()
  {
    return this.cheggAccountManager.q();
  }
  
  public boolean isSigninInProgress()
  {
    try
    {
      if (!this.isSigninInProgress)
      {
        bool = this.isSignupInProgress;
        if (!bool)
        {
          bool = false;
          break label28;
        }
      }
      boolean bool = true;
      label28:
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void onActivityResultFacebook(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.facebookService.a(paramInt1, paramInt2, paramIntent);
  }
  
  @j
  public void onEvent(b paramB)
  {
    if (paramB.b())
    {
      Logger.tag("CheggAuth").d("account added", new Object[0]);
      onAccountAdded();
      return;
    }
    if (paramB.c())
    {
      Logger.tag("CheggAuth").d("account removed", new Object[0]);
      onAccountRemoved(paramB.d());
    }
  }
  
  void onSignInFailed(z paramZ, al.b paramB, UserService.LoginType paramLoginType, String paramString)
  {
    this.signinAnalytics.a(paramB.c(), paramB.b(), paramLoginType, paramString);
    onSigninCompleted(paramZ, paramB);
  }
  
  public void resetPassword(String paramString, z paramZ)
  {
    if (!validEmail(paramString))
    {
      paramZ.onAuthenticateCompleted(al.b.k);
      return;
    }
    resetPasswordImpl(paramString, paramZ);
  }
  
  public void signInFacebook(final Activity paramActivity, final String paramString, final z paramZ)
  {
    if (isSignedIn())
    {
      paramZ.onAuthenticateCompleted(al.b.a);
      return;
    }
    if (!NetworkUtils.isNetworkOnline(paramActivity))
    {
      notifySignInCompleted(paramZ, al.b.c);
      return;
    }
    this.facebookUserEmail = "";
    this.facebookService.a(paramActivity, new am.d()
    {
      public void a(al.b paramAnonymousB)
      {
        SigninService.this.signinAnalytics.a(paramAnonymousB.c(), paramAnonymousB.b(), UserService.LoginType.Facebook, paramString);
        paramZ.onAuthenticateCompleted(paramAnonymousB);
      }
      
      public void a(String paramAnonymousString)
      {
        SigninService.access$202(SigninService.this, paramAnonymousString);
        SigninService.this.signinFacebookWithPassword(null, paramString, paramZ, new -..Lambda.SigninService.3.y9gm1h3EtQgL3ovyXEoK9hn0lUU(this, paramActivity, paramString, paramZ));
      }
    });
  }
  
  public void signOut(z paramZ)
  {
    signOut(paramZ, false);
  }
  
  public void signOut(z paramZ, boolean paramBoolean)
  {
    try
    {
      if ((!this.isSigninInProgress) && (!this.isSignupInProgress))
      {
        Logger.tag("CheggAuth").d("signing out", new Object[0]);
        clear();
        this.cheggAccountManager.u();
        notifySignout(paramZ, paramBoolean);
        return;
      }
      return;
    }
    finally {}
  }
  
  public void signUp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, z paramZ)
  {
    try
    {
      if (isSignedIn())
      {
        paramZ.onAuthenticateCompleted(al.b.a);
        return;
      }
      if (!validateParams(paramString1, paramString2))
      {
        notifySignInCompleted(paramZ, al.b.k);
        return;
      }
      boolean bool = this.isSignupInProgress;
      if (bool) {
        return;
      }
      this.isSignupInProgress = true;
      this.backgroundThreadExecutor.execute(new -..Lambda.SigninService.pz7CFnarVJa3w8g1B1N6_P6nJNE(this, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramZ));
      return;
    }
    finally {}
  }
  
  public void signUpImpl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws APIError
  {
    if (this.isSuperAuthEnabled.booleanValue())
    {
      this.authApi.a(paramString1, paramString2, paramString3, paramString4, paramString5);
      return;
    }
    this.authApi.a(paramString1, paramString2, paramString3, paramString4, paramString5);
  }
  
  public void signinChegg(String paramString1, String paramString2, String paramString3, z paramZ)
  {
    try
    {
      if (isSignedIn())
      {
        paramZ.onAuthenticateCompleted(al.b.a);
        return;
      }
      if (!validateParams(paramString1, paramString2))
      {
        notifySignInCompleted(paramZ, al.b.k);
        return;
      }
      boolean bool = this.isSigninInProgress;
      if (bool) {
        return;
      }
      this.isSigninInProgress = true;
      this.backgroundThreadExecutor.execute(new -..Lambda.SigninService.GURVhvtvgL6v_s6MP4EKfmPp_xw(this, paramString1, paramString2, paramString3, paramZ));
      return;
    }
    finally {}
  }
  
  public void signinFacebookWithPassword(String paramString1, String paramString2, z paramZ, au paramAu)
  {
    try
    {
      if (isSignedIn())
      {
        paramZ.onAuthenticateCompleted(al.b.a);
        return;
      }
      a localA = this.facebookService.c();
      if (localA == null)
      {
        notifySignInCompleted(paramZ, al.b.k);
        return;
      }
      boolean bool = this.isSigninInProgress;
      if (bool) {
        return;
      }
      this.isSigninInProgress = true;
      this.backgroundThreadExecutor.execute(new -..Lambda.SigninService.YP0WBdwu6d0Fj8Vu-uEqG865wjA(this, localA, paramString1, paramString2, paramZ, paramAu));
      return;
    }
    finally {}
  }
}
