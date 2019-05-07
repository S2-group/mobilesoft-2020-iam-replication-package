package com.lucktastic.scratch;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.jumpramp.lucktastic.core.R.id;
import com.jumpramp.lucktastic.core.R.layout;
import com.jumpramp.lucktastic.core.application.LucktasticCore;
import com.jumpramp.lucktastic.core.core.JumpRampActivity;
import com.jumpramp.lucktastic.core.core.adunits.LucktasticAdActivity;
import com.jumpramp.lucktastic.core.core.analytics.AmplitudeHelper;
import com.jumpramp.lucktastic.core.core.api.dto.DxCampaign;
import com.jumpramp.lucktastic.core.core.api.dto.DxCampaign.Details;
import com.jumpramp.lucktastic.core.core.api.dto.DxCampaign.Package;
import com.jumpramp.lucktastic.core.core.steps.DxStep.DxContent;
import com.jumpramp.lucktastic.core.core.utils.EmptyUtils;
import com.jumpramp.lucktastic.core.core.utils.SharedPreferencesHelper;
import com.jumpramp.lucktastic.core.core.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DxActivity
  extends LucktasticAdActivity
{
  public static final String DX_CAMPAIGN_TYPE = "re-engage";
  public static final int REQUEST_CODE = 3972;
  public static final String TAG = DxActivity.class.getSimpleName();
  private ImageView backgroundImage;
  private List<DxCampaign> campaigns = new ArrayList();
  private ImageButton closeButton;
  private ProgressBar cloverLoader;
  private DisplayMetrics displayMetrics = new DisplayMetrics();
  private LinearLayout dxContainer1;
  private LinearLayout dxContainer2;
  private LinearLayout dxContainer3;
  private TextView dxDescription1;
  private TextView dxDescription2;
  private TextView dxDescription3;
  private ImageView dxImage1;
  private ImageView dxImage2;
  private ImageView dxImage3;
  List<DxItem> dxItems = new ArrayList();
  private TextView dxName1;
  private TextView dxName2;
  private TextView dxName3;
  private TextView dxTokenBounty1;
  private TextView dxTokenBounty2;
  private TextView dxTokenBounty3;
  private TextView header;
  private String oppName;
  private String opportunityId;
  private ImageView placeholderView;
  private DxStep.DxContent stepContent;
  
  public DxActivity() {}
  
  private double getScreenSize(DisplayMetrics paramDisplayMetrics)
  {
    getWindow().getWindowManager().getDefaultDisplay().getMetrics(paramDisplayMetrics);
    return Math.sqrt(Math.pow(paramDisplayMetrics.widthPixels / paramDisplayMetrics.xdpi, 2.0D) + Math.pow(paramDisplayMetrics.heightPixels / paramDisplayMetrics.ydpi, 2.0D));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 3972)
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
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_dx_wall);
    getWindow().addFlags(128);
    this.displayMetrics = getResources().getDisplayMetrics();
    this.cloverLoader = ((ProgressBar)findViewById(R.id.in_line_loader));
    this.header = ((TextView)findViewById(R.id.dx_header));
    this.placeholderView = ((ImageView)findViewById(R.id.cpxwall_placeholder));
    this.backgroundImage = ((ImageView)findViewById(R.id.background_image));
    this.closeButton = ((ImageButton)findViewById(R.id.close_button));
    this.closeButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DxActivity.this.onStepComplete();
      }
    });
    this.dxContainer1 = ((LinearLayout)findViewById(R.id.dx_container1));
    this.dxImage1 = ((ImageView)findViewById(R.id.dx_app_image1));
    this.dxTokenBounty1 = ((TextView)findViewById(R.id.dx_token_value1));
    this.dxName1 = ((TextView)findViewById(R.id.dx_app_name1));
    this.dxDescription1 = ((TextView)findViewById(R.id.dx_app_story1));
    this.dxContainer2 = ((LinearLayout)findViewById(R.id.dx_container2));
    this.dxImage2 = ((ImageView)findViewById(R.id.dx_app_image2));
    this.dxTokenBounty2 = ((TextView)findViewById(R.id.dx_token_value2));
    this.dxName2 = ((TextView)findViewById(R.id.dx_app_name2));
    this.dxDescription2 = ((TextView)findViewById(R.id.dx_app_story2));
    this.dxContainer3 = ((LinearLayout)findViewById(R.id.dx_container3));
    this.dxImage3 = ((ImageView)findViewById(R.id.dx_app_image3));
    this.dxTokenBounty3 = ((TextView)findViewById(R.id.dx_token_value3));
    this.dxName3 = ((TextView)findViewById(R.id.dx_app_name3));
    this.dxDescription3 = ((TextView)findViewById(R.id.dx_app_story3));
    this.dxItems.add(new DxItem(this.dxContainer1, this.dxImage1, this.dxTokenBounty1, this.dxName1, this.dxDescription1));
    this.dxItems.add(new DxItem(this.dxContainer2, this.dxImage2, this.dxTokenBounty2, this.dxName2, this.dxDescription2));
    this.dxItems.add(new DxItem(this.dxContainer3, this.dxImage3, this.dxTokenBounty3, this.dxName3, this.dxDescription3));
    if ((!EmptyUtils.isBundleEmpty(getIntent().getExtras())) && (!TextUtils.isEmpty(getIntent().getExtras().getString("step_content"))))
    {
      this.stepContent = ((DxStep.DxContent)new Gson().fromJson(getIntent().getExtras().getString("step_content"), DxStep.DxContent.class));
      this.opportunityId = getIntent().getExtras().getString("opportunityId");
      this.oppName = getIntent().getExtras().getString("OppName");
      AmplitudeHelper.tagDxPostRollViewEvent(this.oppName);
      if (!TextUtils.isEmpty(this.stepContent.getHeaderMessage()))
      {
        if (getScreenSize(this.displayMetrics) < 4.7D) {
          this.header.setTextSize(25.0F);
        }
        this.header.setText(this.stepContent.getHeaderMessage());
      }
      if ((!TextUtils.isEmpty(this.stepContent.getHeaderColor())) && (Utils.isValidColor(this.stepContent.getHeaderColor()))) {
        this.header.setTextColor(Color.parseColor(Utils.formatColor(this.stepContent.getHeaderColor())));
      }
      if (!TextUtils.isEmpty(this.stepContent.getBackgroundImage())) {
        Picasso.with(this).load(this.stepContent.getBackgroundImage()).into(this.backgroundImage);
      }
      if (!EmptyUtils.isListEmpty(this.stepContent.getDxCampaigns()))
      {
        LucktasticCore.getInstance().refreshComPackageList();
        paramBundle = SharedPreferencesHelper.getInstalledPackages();
        Object localObject1 = this.stepContent.getDxCampaigns().iterator();
        Object localObject2;
        do
        {
          do
          {
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject2 = (DxCampaign)((Iterator)localObject1).next();
          } while ((!paramBundle.contains(((DxCampaign)localObject2).getPackage().getComPackageId())) || (!((DxCampaign)localObject2).getPackage().getShow()));
          this.campaigns.add(localObject2);
        } while (this.campaigns.size() != 3);
        if (this.campaigns.size() == 0) {
          onStepNoFill();
        }
        int i = 0;
        while (i < this.campaigns.size())
        {
          paramBundle = ((DxCampaign)this.campaigns.get(i)).getName();
          localObject1 = ((DxCampaign)this.campaigns.get(i)).getCampaignId();
          localObject2 = ((DxCampaign)this.campaigns.get(i)).getVerificationId();
          final String str = ((DxCampaign)this.campaigns.get(i)).getReengagementId();
          Picasso.with(this).load(((DxCampaign)this.campaigns.get(i)).getDxDetails().getImageUrl()).into(((DxItem)this.dxItems.get(i)).dxImage);
          ((DxItem)this.dxItems.get(i)).dxDescription.setText(((DxCampaign)this.campaigns.get(i)).getDxDetails().getStory());
          ((DxItem)this.dxItems.get(i)).dxTokenBounty.setText(String.valueOf(((DxCampaign)this.campaigns.get(i)).getDxDetails().getAwardAmount()));
          ((DxItem)this.dxItems.get(i)).dxName.setText(paramBundle);
          if (getScreenSize(this.displayMetrics) < 4.7D) {
            ((DxItem)this.dxItems.get(i)).dxName.setTextSize(18.0F);
          }
          ((DxItem)this.dxItems.get(i)).dxContainer.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              AmplitudeHelper.tagDxPostRollClickEvent(DxActivity.this.oppName, paramBundle);
              JumpRampActivity.launchCampaignIntent(DxActivity.this, "", DxActivity.this.opportunityId, this.val$campaignId, str, this.val$verificationId, "re-engage", new Bundle(), Integer.valueOf(5772));
            }
          });
          ((DxItem)this.dxItems.get(i)).dxContainer.setVisibility(0);
          if (i == 1) {
            findViewById(R.id.mid_space1).setVisibility(0);
          }
          if (i == 2) {
            findViewById(R.id.mid_space2).setVisibility(0);
          }
          i += 1;
        }
        this.placeholderView.setVisibility(8);
        this.cloverLoader.setVisibility(8);
        return;
      }
      onStepNoFill();
      return;
    }
    onStepNoFill();
  }
  
  private class DxItem
  {
    public LinearLayout dxContainer;
    public TextView dxDescription;
    public ImageView dxImage;
    public TextView dxName;
    public TextView dxTokenBounty;
    
    public DxItem(LinearLayout paramLinearLayout, ImageView paramImageView, TextView paramTextView1, TextView paramTextView2, TextView paramTextView3)
    {
      this.dxContainer = paramLinearLayout;
      this.dxImage = paramImageView;
      this.dxTokenBounty = paramTextView1;
      this.dxName = paramTextView2;
      this.dxDescription = paramTextView3;
    }
  }
}
