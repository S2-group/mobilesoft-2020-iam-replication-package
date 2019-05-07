package com.jumpramp.lucktastic.core.core;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jumpramp.lucktastic.core.R.dimen;
import com.jumpramp.lucktastic.core.R.drawable;
import com.jumpramp.lucktastic.core.R.id;
import com.jumpramp.lucktastic.core.R.layout;
import com.jumpramp.lucktastic.core.application.LucktasticCore;
import com.jumpramp.lucktastic.core.core.adunits.LucktasticAdActivity;
import com.jumpramp.lucktastic.core.core.api.responses.AppInstallResponse;
import com.jumpramp.lucktastic.core.core.dto.AppInstallGeneric;
import com.jumpramp.lucktastic.core.core.utils.EmptyUtils;
import com.jumpramp.lucktastic.core.core.utils.SharedPreferencesHelper;
import com.jumpramp.lucktastic.core.core.utils.UriUtils;
import com.jumpramp.lucktastic.core.core.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AppInstallActivity
  extends LucktasticAdActivity
{
  public static final String APP_MESSAGE = "app_message";
  public static final int REQUEST_CODE = 2472;
  public static final String TAG = AppInstallActivity.class.getSimpleName();
  private final String CANCEL_LEFT = "L";
  private final String CANCEL_RIGHT = "R";
  private boolean validPostRollFound = false;
  
  public AppInstallActivity() {}
  
  private void initXButton(ImageView paramImageView, Boolean paramBoolean1, Boolean paramBoolean2)
  {
    if ((!paramBoolean1.booleanValue()) && (paramBoolean2.booleanValue()))
    {
      System.err.println("ERROR - Cannot show and disable an X button");
      return;
    }
    if (paramBoolean2.booleanValue())
    {
      i = R.drawable.x_with_circle;
      paramImageView.setImageResource(i);
      paramImageView.setClickable(paramBoolean1.booleanValue());
      paramImageView.setEnabled(paramBoolean1.booleanValue());
      if (!paramBoolean1.booleanValue()) {
        break label81;
      }
    }
    label81:
    for (int i = 0;; i = 4)
    {
      paramImageView.setVisibility(i);
      return;
      i = 17170445;
      break;
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 2472)
    {
      onStepComplete();
      return;
    }
    onStepCanceled();
  }
  
  public void onBackPressed()
  {
    onStepComplete();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getStringExtra("app_message").trim();
    Object localObject1 = new Gson();
    this.validPostRollFound = false;
    Object localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    int i = ((DisplayMetrics)localObject2).widthPixels;
    int j = ((DisplayMetrics)localObject2).heightPixels;
    int k = (int)getResources().getDimension(R.dimen.action_bar_height);
    for (;;)
    {
      try
      {
        localObject1 = (AppInstallResponse)((Gson)localObject1).fromJson(paramBundle, AppInstallResponse.class);
        if ((localObject1 == null) || (EmptyUtils.isListEmpty(((AppInstallResponse)localObject1).getAppInstalls()))) {
          break label712;
        }
        LucktasticCore.getInstance().refreshComPackageList();
        paramBundle = SharedPreferencesHelper.getInstalledPackages();
        localObject2 = ((AppInstallResponse)localObject1).getAppInstalls().iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject1 = (AppInstallGeneric)((Iterator)localObject2).next();
          if (paramBundle.contains(((AppInstallGeneric)localObject1).getComPackageID())) {
            continue;
          }
        }
        switch (((AppInstallGeneric)localObject1).getTemplate())
        {
        case 1: 
          localObject2 = new StringBuilder().append("http://cdn.jumprampgames.com/images/").append(((AppInstallGeneric)localObject1).getImageDir());
          if (!((AppInstallGeneric)localObject1).getImageDir().endsWith("/")) {
            break label721;
          }
          paramBundle = "";
          paramBundle = paramBundle + "detail.jpg";
          Picasso.with(this).load(paramBundle).resize(i, j - k - 3).into((ImageView)findViewById(R.id.app_install_background));
          paramBundle = (ImageView)Utils.findById(this, R.id.button_cancel_left);
          localObject2 = (ImageView)Utils.findById(this, R.id.button_cancel_right);
          if (TextUtils.isEmpty(((AppInstallGeneric)localObject1).getxPosition())) {
            break label633;
          }
          if ((((AppInstallGeneric)localObject1).getxEnabled() != 1) || (!((AppInstallGeneric)localObject1).getxPosition().equalsIgnoreCase("L"))) {
            break label449;
          }
          initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(true));
          initXButton((ImageView)localObject2, Boolean.valueOf(false), Boolean.valueOf(false));
          View.OnClickListener local1 = new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              AppInstallActivity.this.onStepComplete();
            }
          };
          paramBundle.setOnClickListener(local1);
          ((ImageView)localObject2).setOnClickListener(local1);
          findViewById(R.id.button_install).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              paramAnonymousView = Uri.parse(this.val$appMessage.getAppLink());
              if (paramAnonymousView.getScheme().equals("market")) {}
              while (UriUtils.launchUri(AppInstallActivity.this, paramAnonymousView)) {
                try
                {
                  AppInstallActivity.this.startActivity(new Intent("android.intent.action.VIEW", paramAnonymousView));
                  return;
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                  AppInstallActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/" + paramAnonymousView.getHost() + "?" + paramAnonymousView.getQuery())));
                  return;
                }
              }
              AppInstallActivity.this.onStepNoFill();
            }
          });
          this.validPostRollFound = true;
          if (this.validPostRollFound) {
            break label720;
          }
          onStepComplete();
          return;
        }
      }
      catch (JsonSyntaxException paramBundle)
      {
        onStepComplete();
        return;
      }
      setContentView(R.layout.activity_app_install);
      continue;
      setContentView(R.layout.activity_app_install);
      continue;
      label449:
      if ((((AppInstallGeneric)localObject1).getxEnabled() == 1) && (((AppInstallGeneric)localObject1).getxPosition().equalsIgnoreCase("R")))
      {
        initXButton(paramBundle, Boolean.valueOf(false), Boolean.valueOf(false));
        initXButton((ImageView)localObject2, Boolean.valueOf(true), Boolean.valueOf(true));
      }
      else if ((((AppInstallGeneric)localObject1).getxEnabled() == 0) && (((AppInstallGeneric)localObject1).getxPosition().equalsIgnoreCase("L")))
      {
        initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(false));
        initXButton((ImageView)localObject2, Boolean.valueOf(false), Boolean.valueOf(false));
      }
      else if ((((AppInstallGeneric)localObject1).getxEnabled() == 0) && (((AppInstallGeneric)localObject1).getxPosition().equalsIgnoreCase("R")))
      {
        initXButton(paramBundle, Boolean.valueOf(false), Boolean.valueOf(false));
        initXButton((ImageView)localObject2, Boolean.valueOf(true), Boolean.valueOf(false));
      }
      else
      {
        initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(true));
        initXButton((ImageView)localObject2, Boolean.valueOf(false), Boolean.valueOf(false));
        continue;
        label633:
        if (((AppInstallGeneric)localObject1).getxEnabled() == 1) {
          initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(true));
        }
        for (;;)
        {
          initXButton((ImageView)localObject2, Boolean.valueOf(false), Boolean.valueOf(false));
          break;
          if (((AppInstallGeneric)localObject1).getxEnabled() == 0) {
            initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(false));
          } else {
            initXButton(paramBundle, Boolean.valueOf(true), Boolean.valueOf(true));
          }
        }
        label712:
        onStepComplete();
        return;
        continue;
        label720:
        return;
        label721:
        paramBundle = "/";
      }
    }
  }
}
