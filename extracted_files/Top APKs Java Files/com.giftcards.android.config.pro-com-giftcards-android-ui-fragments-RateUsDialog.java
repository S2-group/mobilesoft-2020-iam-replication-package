package com.giftcards.android.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.giftcards.android.a.a;
import com.giftcards.android.injection.a.b;
import com.giftcards.android.local.ConsumerInfoController;
import com.giftcards.android.network.c.b.ac;
import com.giftcards.android.ui.activities.BaseActivity;
import com.giftcards.android.ui.web.WebviewActivity;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

public class RateUsDialog
  extends DialogFragment
  implements View.OnClickListener
{
  public static final String a = RateUsDialog.class.getSimpleName();
  private boolean b = true;
  @Bind({2131690250})
  TextView btnNope;
  @Bind({2131690251})
  TextView btnOk;
  @Inject
  ConsumerInfoController consumerInfoController;
  @Inject
  a memoryOperator;
  @Bind({2131690249})
  TextView txtBody;
  @Bind({2131690248})
  TextView txtTitle;
  
  public RateUsDialog() {}
  
  private Intent a(Context paramContext)
  {
    if (!"pro".equals("pro")) {}
    for (Object localObject = "com.giftcards.android.config.pro";; localObject = paramContext.getPackageName())
    {
      localObject = new Intent("android.intent.action.VIEW", a((String)localObject));
      if (a(paramContext, "com.android.vending")) {
        ((Intent)localObject).setPackage("com.android.vending");
      }
      ((Intent)localObject).addFlags(1208483840);
      return localObject;
    }
  }
  
  public static RateUsDialog a()
  {
    return new RateUsDialog();
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  @NonNull
  private View.OnClickListener b()
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RateUsDialog.this.txtTitle.setText(RateUsDialog.this.getString(2131231563));
        RateUsDialog.this.txtBody.setText(Html.fromHtml(RateUsDialog.this.getString(2131231555)));
        Linkify.addLinks(RateUsDialog.this.txtBody, 4);
        RateUsDialog.this.txtBody.setMovementMethod(LinkMovementMethod.getInstance());
        RateUsDialog.this.btnOk.setText(RateUsDialog.this.getString(2131231558));
        RateUsDialog.this.btnOk.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            WebviewActivity.c(RateUsDialog.this.getActivity());
            RateUsDialog.this.dismiss();
          }
        });
        RateUsDialog.this.btnNope.setText(RateUsDialog.this.getString(2131231557));
        RateUsDialog.this.btnNope.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            RateUsDialog.this.dismiss();
          }
        });
      }
    };
  }
  
  @NonNull
  private View.OnClickListener c()
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RateUsDialog.this.txtBody.setText(RateUsDialog.this.getString(2131231556));
        RateUsDialog.this.btnNope.setText(RateUsDialog.this.getString(2131231561));
        RateUsDialog.this.btnOk.setText(RateUsDialog.this.getString(2131231562));
        RateUsDialog.this.btnNope.setOnClickListener(RateUsDialog.this);
        RateUsDialog.this.btnOk.setOnClickListener(RateUsDialog.this);
      }
    };
  }
  
  private void d()
  {
    if (this.consumerInfoController.b() == null)
    {
      str = "";
      if (!TextUtils.isEmpty(str)) {
        break label50;
      }
    }
    label50:
    for (String str = getString(2131231496);; str = String.format(getString(2131231497), new Object[] { str }))
    {
      this.txtTitle.setText(str);
      return;
      str = this.consumerInfoController.b().j();
      break;
    }
  }
  
  private Thread e()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        RateUsDialog.this.memoryOperator.a("doNotShowRateUsAgain", "doNotShowRateUsAgain");
      }
    });
  }
  
  @Nullable
  Uri a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://play.google.com/store/apps/details?id=" + paramString);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    if (this.b)
    {
      this.b = false;
      ((BaseActivity)getActivity()).a_().a(this);
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131690251) {
      startActivity(a(getActivity()));
    }
    dismiss();
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    e().start();
    paramBundle = new AlertDialog.Builder(getActivity(), 2);
    View localView = LayoutInflater.from(getActivity()).inflate(2130968736, new LinearLayout(getActivity()), false);
    ButterKnife.bind(this, localView);
    d();
    this.btnOk.setOnClickListener(c());
    this.btnNope.setOnClickListener(b());
    return paramBundle.setView(localView).create();
  }
}
