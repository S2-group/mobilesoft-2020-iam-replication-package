package com.pof.newapi.request;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.common.collect.BiMap;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;
import com.pof.android.PofApplication;
import com.pof.android.analytics.AnalyticsEventBuilder;
import com.pof.android.analytics.AnalyticsEventParams;
import com.pof.android.analytics.AnalyticsEventTracker;
import com.pof.android.analytics.EventParam;
import com.pof.android.analytics.EventType;
import com.pof.android.crashreporting.CrashReporter;
import com.pof.android.experiment.ExperimentParameters;
import com.pof.android.experiment.ExperimentStore;
import com.pof.android.session.AppPreferences;
import com.pof.newapi.localData.DataStore;
import com.pof.newapi.model.api.AccountSettings;
import com.pof.newapi.model.api.ApiBase;
import com.pof.newapi.model.api.OptInStatus;
import com.pof.newapi.model.api.PromptPageSources;
import com.pof.newapi.model.api.UploadImageStatus;
import com.pof.newapi.model.api.UserDetail;
import com.pof.newapi.model.api.VoiceCallUserCapabilities;
import com.pof.newapi.request.api.AccountSettingsRequest;
import com.pof.newapi.request.api.ApiRequest;
import com.pof.newapi.request.api.GooglePaymentRequest;
import com.pof.newapi.request.api.PromptPageSourcesRequest;
import com.pof.newapi.request.api.UnsecureRequest;
import com.pof.newapi.request.api.UploadImageStatusRequest;
import com.pof.newapi.request.api.UserDetailRequest;
import com.pof.newapi.request.api.VoiceCallOptInStatusRequest;
import com.pof.newapi.request.api.VoiceCallUserSignatureRequest;
import com.pof.newapi.request.binary.BinaryRequestCallback;
import com.pof.newapi.request.thirdParty.GooglePaymentRequestManager;
import com.pof.newapi.service.BinaryRequestService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.lang3.StringUtils;

public class ApplicationBoundRequestManagerProvider
{
  private ApiRequestManager a = new ApiRequestManager();
  private BinaryRequestManager b = new BinaryRequestManager(BinaryRequestService.class);
  private Context c;
  private ConcurrentLinkedQueue<ApiRequest> d = new ConcurrentLinkedQueue();
  private AppPreferences e;
  private GooglePaymentRequestManager f = new GooglePaymentRequestManager();
  
  public ApplicationBoundRequestManagerProvider(Context paramContext, AppPreferences paramAppPreferences)
  {
    this.c = paramContext;
    this.e = paramAppPreferences;
    h();
    j();
  }
  
  private void d(final ApiRequest paramApiRequest)
  {
    a(paramApiRequest, new RequestCallbackAdapter()
    {
      public void a(ApiBase paramAnonymousApiBase)
      {
        super.a(paramAnonymousApiBase);
        ApplicationBoundRequestManagerProvider.b(ApplicationBoundRequestManagerProvider.this);
      }
      
      public void c(ApiBase paramAnonymousApiBase)
      {
        super.c(paramAnonymousApiBase);
        ApplicationBoundRequestManagerProvider.c(ApplicationBoundRequestManagerProvider.this).add(paramApiRequest);
      }
    });
  }
  
  private void h()
  {
    if (!this.a.b()) {
      this.a.a(this.c);
    }
  }
  
  private void i()
  {
    if (!this.f.b()) {
      this.f.a(this.c);
    }
  }
  
  private void j()
  {
    if (!this.b.b()) {
      this.b.a(this.c);
    }
  }
  
  private void k()
  {
    this.a.a(new VoiceCallOptInStatusRequest(), new RequestCallbackAdapter()
    {
      public void a(OptInStatus paramAnonymousOptInStatus)
      {
        ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).r(paramAnonymousOptInStatus.isOptedIn());
        ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).aR();
      }
    });
  }
  
  private void l()
  {
    ApiRequest localApiRequest = (ApiRequest)this.d.poll();
    if (localApiRequest != null) {
      d(localApiRequest);
    }
  }
  
  private void m()
  {
    if ((!ExperimentStore.a().a(ExperimentParameters.l)) || (!this.e.aD())) {}
    do
    {
      return;
      PackageManager localPackageManager = PofApplication.e().getPackageManager();
      Object localObject1 = new ArrayList();
      try
      {
        localObject2 = localPackageManager.getInstalledPackages(128);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject2;
          CrashReporter.a().a(localException, "Unable to get packages");
        }
        localObject1 = new AnalyticsEventParams();
        ((AnalyticsEventParams)localObject1).a(EventParam.bC, StringUtils.join(localException, ","));
        localObject1 = new AnalyticsEventBuilder(EventType.cE, (AnalyticsEventParams)localObject1);
        ((AnalyticsEventBuilder)localObject1).a(true);
        AnalyticsEventTracker.a().a(((AnalyticsEventBuilder)localObject1).a());
        this.e.k(System.currentTimeMillis());
        this.e.aR();
      }
    } while (((List)localObject1).isEmpty());
    localObject2 = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localPackageInfo.packageName).append(":").append(localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo)).append(":").append(localPackageInfo.firstInstallTime).append(":").append(localPackageInfo.lastUpdateTime);
        ((List)localObject2).add(localStringBuilder.toString());
      }
    }
  }
  
  public void a()
  {
    if (this.b.b()) {
      this.b.c();
    }
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != paramInt2) || (this.e.H() == null) || (this.e.H().getUpgradeCtaEnabled() == null)) {
      a(new VoiceCallUserSignatureRequest(), new RequestCallbackAdapter()
      {
        public void a(VoiceCallUserCapabilities paramAnonymousVoiceCallUserCapabilities)
        {
          ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).a(paramAnonymousVoiceCallUserCapabilities);
          ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).aR();
        }
      });
    }
  }
  
  public void a(CachedSpiceRequest paramCachedSpiceRequest, BinaryRequestCallback paramBinaryRequestCallback)
  {
    j();
    this.b.a(paramCachedSpiceRequest, paramBinaryRequestCallback);
  }
  
  public void a(ApiRequest paramApiRequest)
  {
    a(paramApiRequest, new RequestCallbackAdapter() {});
  }
  
  public void a(ApiRequest paramApiRequest, ApiRequestCallback paramApiRequestCallback)
  {
    h();
    this.a.a(paramApiRequest, paramApiRequestCallback);
  }
  
  public <T extends ApiBase> void a(GooglePaymentRequest<T> paramGooglePaymentRequest, ApiRequestCallback<T> paramApiRequestCallback)
  {
    i();
    this.f.a(paramGooglePaymentRequest, paramApiRequestCallback);
  }
  
  public <T extends ApiRequest<?, ?>> boolean a(Class<T> paramClass)
  {
    return this.a.b(paramClass);
  }
  
  public void b()
  {
    if (this.f.b()) {
      this.f.c();
    }
  }
  
  public void b(ApiRequest paramApiRequest)
  {
    this.d.add(paramApiRequest);
  }
  
  public void c()
  {
    h();
    this.a.a(new PromptPageSourcesRequest(), new RequestCallbackAdapter()
    {
      public void a(PromptPageSources paramAnonymousPromptPageSources)
      {
        super.a(paramAnonymousPromptPageSources);
        ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).a(paramAnonymousPromptPageSources);
        ApplicationBoundRequestManagerProvider.a(ApplicationBoundRequestManagerProvider.this).aR();
      }
    });
    this.a.a(new UploadImageStatusRequest(), new RequestCallbackAdapter()
    {
      public void a(UploadImageStatus paramAnonymousUploadImageStatus)
      {
        super.a(paramAnonymousUploadImageStatus);
        DataStore.a().a(paramAnonymousUploadImageStatus);
      }
    });
    if (ExperimentStore.a().f()) {
      ExperimentStore.a().b();
    }
    AnalyticsEventTracker.a().b();
    l();
    k();
    m();
  }
  
  public void c(ApiRequest paramApiRequest)
  {
    this.a.a(paramApiRequest);
  }
  
  public void d()
  {
    h();
    this.a.a(new AccountSettingsRequest(), new RequestCallbackAdapter()
    {
      public void a(AccountSettings paramAnonymousAccountSettings)
      {
        super.a(paramAnonymousAccountSettings);
        DataStore.a().a(paramAnonymousAccountSettings);
      }
    });
  }
  
  public void e()
  {
    if (DataStore.a().f())
    {
      h();
      this.a.a(new UserDetailRequest(DataStore.a().c().getProfileId().intValue(), false), new RequestCallbackAdapter()
      {
        public void a(UserDetail paramAnonymousUserDetail)
        {
          super.a(paramAnonymousUserDetail);
          DataStore.a().a(paramAnonymousUserDetail);
        }
      });
    }
  }
  
  public void f()
  {
    if (this.a.b()) {
      synchronized (this.a.c)
      {
        Iterator localIterator = this.a.c.a().iterator();
        while (localIterator.hasNext())
        {
          SpiceRequest localSpiceRequest = (SpiceRequest)localIterator.next();
          if (!(localSpiceRequest instanceof UnsecureRequest))
          {
            this.a.c.remove(localSpiceRequest);
            localSpiceRequest.c();
          }
        }
      }
    }
    this.d.clear();
  }
  
  public <T> void g()
  {
    this.a.e();
  }
}
