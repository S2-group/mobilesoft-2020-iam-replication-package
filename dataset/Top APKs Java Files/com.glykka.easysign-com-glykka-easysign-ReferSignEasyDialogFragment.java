package com.glykka.easysign;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.glykka.easysign.credits.CreditsManager;
import com.glykka.easysign.util.EasySignUtil;
import io.branch.indexing.BranchUniversalObject;
import io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE;
import io.branch.referral.Branch.BranchLinkCreateListener;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import java.util.Iterator;
import java.util.List;

public class ReferSignEasyDialogFragment
  extends DialogFragment
  implements View.OnClickListener
{
  private BroadcastReceiver mBroadcastReceiver;
  private RelativeLayout mContainerReferralCount;
  private ImageView mIvReferralBadge;
  private String mReferralUrl = "";
  private TextView mTvReferralCode;
  private TextView mTvReferralsEarned;
  
  public ReferSignEasyDialogFragment() {}
  
  private boolean isMessengerInstalledOnDevice()
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.facebook.orca")) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isTablet()
  {
    return getResources().getBoolean(2131427332);
  }
  
  private boolean isWhatsappInstalledOnDevice()
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.whatsapp")) {
        return true;
      }
    }
    return false;
  }
  
  private void refreshReferralCountView()
  {
    int i = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("PREFS_MY_TOTAL_REFERRAL_COUNT", 0);
    if (i > 0)
    {
      this.mContainerReferralCount.setVisibility(0);
      this.mTvReferralsEarned.setText(i + "");
      this.mIvReferralBadge.setVisibility(8);
      return;
    }
    this.mContainerReferralCount.setVisibility(8);
    this.mIvReferralBadge.setVisibility(0);
  }
  
  private void setupStringsByAccountType(View paramView)
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(getActivity());
    boolean bool1;
    boolean bool2;
    label57:
    boolean bool3;
    label65:
    boolean bool4;
    label78:
    boolean bool6;
    label91:
    boolean bool5;
    label99:
    TextView localTextView1;
    if (CreditsManager.isBusinessReferral(getActivity()))
    {
      int i = ((SharedPreferences)localObject).getInt("PREFS_ACTUAL_ACCOUNT_TYPE_WITHOUT_PROMO", -1);
      int j = ((SharedPreferences)localObject).getInt("PREFS_ACTUAL_ACCOUNT_IS_PAID_WITHOUT_PROMO", -1);
      if (i == 3)
      {
        bool1 = true;
        if (i != 2) {
          break label191;
        }
        bool2 = true;
        if (i != 1) {
          break label197;
        }
        bool3 = true;
        if ((i != 2) || (j == 1)) {
          break label203;
        }
        bool4 = true;
        if ((i != 3) || (j == 1)) {
          break label209;
        }
        bool6 = true;
        if (i != 4) {
          break label215;
        }
        bool5 = true;
        localObject = (TextView)paramView.findViewById(2131755539);
        localTextView1 = (TextView)paramView.findViewById(2131755540);
        TextView localTextView2 = (TextView)paramView.findViewById(2131755537);
        paramView = (TextView)paramView.findViewById(2131755538);
        if (((!bool1) || (bool6)) && (!bool5)) {
          break label283;
        }
        ((TextView)localObject).setText(2131231419);
        localTextView1.setText(2131231324);
        localTextView2.setText(2131231117);
        paramView.setText(2131231270);
      }
    }
    label191:
    label197:
    label203:
    label209:
    label215:
    label283:
    while (((!bool2) || (bool4)) && (!bool3))
    {
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label57;
      bool3 = false;
      break label65;
      bool4 = false;
      break label78;
      bool6 = false;
      break label91;
      bool5 = false;
      break label99;
      bool1 = CreditsManager.isBusinessUser(getActivity());
      bool2 = CreditsManager.isProUser(getActivity());
      bool3 = CreditsManager.getUserAccountType(getActivity()).equals("Premium_Lifetime");
      bool4 = CreditsManager.isProTrial(getActivity());
      bool6 = CreditsManager.isBusinessTrial(getActivity());
      bool5 = CreditsManager.isEnterpriseUser(getActivity());
      break label99;
    }
    ((TextView)localObject).setText(2131231119);
    localTextView1.setText(2131231325);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131755541: 
      ((ClipboardManager)getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("SignEasy Referral Code: ", this.mTvReferralCode.getText()));
      paramView = getActivity().getLayoutInflater().inflate(2130903236, null);
      localObject = new Toast(getActivity());
      ((Toast)localObject).setGravity(17, 0, 0);
      ((Toast)localObject).setDuration(0);
      ((Toast)localObject).setView(paramView);
      ((Toast)localObject).show();
      SignEasyEventsTracker.track(getActivity(), "action", "copy_link");
      SignEasyEventsTracker.logEventTapReferAction(getActivity());
      return;
    case 2131755542: 
      if (isWhatsappInstalledOnDevice())
      {
        paramView = new Intent();
        paramView.setAction("android.intent.action.SEND");
        localObject = String.format(getString(2131231644), new Object[] { this.mTvReferralCode.getText().toString() });
        paramView.putExtra("android.intent.extra.TEXT", (String)localObject + " " + this.mReferralUrl);
        paramView.setType("text/plain");
        paramView.setPackage("com.whatsapp");
        startActivity(paramView);
        SignEasyEventsTracker.track(getActivity(), "action", "whatsapp");
        SignEasyEventsTracker.logEventTapReferAction(getActivity());
        return;
      }
      Toast.makeText(getActivity(), "WhatsApp not installed.", 0).show();
      return;
    case 2131755543: 
      paramView = new Bundle();
      paramView.putString("link", this.mReferralUrl);
      if (isTablet())
      {
        localObject = getActivity().getSupportFragmentManager();
        ComposeEmailDialogFragment localComposeEmailDialogFragment = new ComposeEmailDialogFragment();
        localComposeEmailDialogFragment.setArguments(paramView);
        localComposeEmailDialogFragment.show((FragmentManager)localObject, "");
      }
      for (;;)
      {
        SignEasyEventsTracker.track(getActivity(), "action", "email");
        SignEasyEventsTracker.logEventTapReferAction(getActivity());
        return;
        localObject = new Intent(getActivity(), ComposeEmailActivity.class);
        ((Intent)localObject).putExtras(paramView);
        startActivity((Intent)localObject);
      }
    case 2131755544: 
      if (isMessengerInstalledOnDevice())
      {
        paramView = new Intent();
        paramView.setAction("android.intent.action.SEND");
        localObject = String.format(getString(2131231644), new Object[] { this.mTvReferralCode.getText().toString() });
        paramView.putExtra("android.intent.extra.TEXT", (String)localObject + " " + this.mReferralUrl);
        paramView.setType("text/plain");
        paramView.setPackage("com.facebook.orca");
        startActivity(paramView);
        SignEasyEventsTracker.track(getActivity(), "action", "facebook_messenger");
        SignEasyEventsTracker.logEventTapReferAction(getActivity());
        return;
      }
      Toast.makeText(getActivity(), "Messenger not installed.", 0).show();
      return;
    }
    paramView = new Intent();
    paramView.setAction("android.intent.action.SEND");
    Object localObject = String.format(getString(2131231644), new Object[] { this.mTvReferralCode.getText().toString() });
    paramView.putExtra("android.intent.extra.TEXT", (String)localObject + " " + this.mReferralUrl);
    paramView.setType("text/plain");
    startActivity(paramView);
    SignEasyEventsTracker.track(getActivity(), "action", "other_apps");
    SignEasyEventsTracker.logEventTapReferAction(getActivity());
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setStyle(1, 16973942);
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = super.onCreateDialog(paramBundle);
    paramBundle.getWindow().requestFeature(1);
    paramBundle.setCanceledOnTouchOutside(false);
    return paramBundle;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramBundle = paramLayoutInflater.inflate(2130903227, paramViewGroup, false);
    paramLayoutInflater = (Toolbar)paramBundle.findViewById(2131755155);
    if (Build.VERSION.SDK_INT >= 21) {
      getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(2131689491));
    }
    label131:
    Object localObject1;
    String str;
    Object localObject2;
    if ((CreditsManager.isProAndAboveUser(getActivity())) && (!CreditsManager.isProTrial(getActivity())) && (!CreditsManager.isBusinessTrial(getActivity())))
    {
      paramLayoutInflater.setTitle(getString(2131231190));
      paramLayoutInflater.setTitleTextColor(getResources().getColor(2131689707));
      if (!EasySignUtil.isDeviceTablet(getActivity())) {
        break label625;
      }
      paramLayoutInflater.setNavigationIcon(getResources().getDrawable(2130837979));
      paramLayoutInflater.setNavigationOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ReferSignEasyDialogFragment.this.getShowsDialog())
          {
            ReferSignEasyDialogFragment.this.dismiss();
            return;
          }
          ReferSignEasyDialogFragment.this.getActivity().onBackPressed();
        }
      });
      localObject1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
      str = ((SharedPreferences)localObject1).getString("PREFS_MY_REFERRER_CODE", "");
      this.mTvReferralCode = ((TextView)paramBundle.findViewById(2131755541));
      this.mTvReferralCode.setText(str);
      this.mTvReferralCode.setOnClickListener(this);
      this.mContainerReferralCount = ((RelativeLayout)paramBundle.findViewById(2131755534));
      this.mTvReferralsEarned = ((TextView)paramBundle.findViewById(2131755536));
      this.mIvReferralBadge = ((ImageView)paramBundle.findViewById(2131755533));
      paramLayoutInflater = (TextView)paramBundle.findViewById(2131755546);
      paramLayoutInflater.setText(Html.fromHtml(getString(2131231426)));
      paramLayoutInflater.setMovementMethod(LinkMovementMethod.getInstance());
      paramLayoutInflater = (Button)paramBundle.findViewById(2131755542);
      paramViewGroup = (Button)paramBundle.findViewById(2131755544);
      localObject2 = (Button)paramBundle.findViewById(2131755543);
      TextView localTextView = (TextView)paramBundle.findViewById(2131755545);
      if (isWhatsappInstalledOnDevice())
      {
        paramLayoutInflater.setVisibility(0);
        paramLayoutInflater.setOnClickListener(this);
      }
      if (isMessengerInstalledOnDevice())
      {
        paramViewGroup.setVisibility(0);
        paramViewGroup.setOnClickListener(this);
      }
      ((Button)localObject2).setOnClickListener(this);
      localTextView.setOnClickListener(this);
      SignEasyEventsTracker.logEventViewScreenReferral(getActivity());
      setupStringsByAccountType(paramBundle);
      refreshReferralCountView();
      paramViewGroup = ((SharedPreferences)localObject1).getString("SessionUserName", "");
      paramLayoutInflater = ((SharedPreferences)localObject1).getString("SessionUserLastName", "");
      if (paramViewGroup.equals("")) {
        break label642;
      }
      paramLayoutInflater = paramViewGroup + " " + paramLayoutInflater;
    }
    label625:
    label642:
    for (;;)
    {
      paramViewGroup = paramLayoutInflater;
      if (paramLayoutInflater.equalsIgnoreCase("")) {
        paramViewGroup = ((SharedPreferences)localObject1).getString("SessionUser", "");
      }
      localObject1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
      paramLayoutInflater = "";
      localObject2 = ((SharedPreferences)localObject1).getString("FB_ID", "");
      if (!((String)localObject2).equals("")) {
        paramLayoutInflater = "https://graph.facebook.com/" + (String)localObject2 + "/picture?type=large";
      }
      localObject1 = ((SharedPreferences)localObject1).getString("GOOGLE_PIC_URL", "");
      paramLayoutInflater = new BranchUniversalObject().setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC).addContentMetadata("signup_referrer_identity", paramViewGroup.trim()).addContentMetadata("FB_URL", paramLayoutInflater).addContentMetadata("GOOGLE_URL", (String)localObject1).addContentMetadata("signup_referrer_code", str);
      paramViewGroup = new LinkProperties();
      paramLayoutInflater.generateShortUrl(getActivity(), paramViewGroup, new Branch.BranchLinkCreateListener()
      {
        public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError == null)
          {
            Log.d("Branch_Test", "My Branch Link to Share: " + paramAnonymousString);
            ReferSignEasyDialogFragment.access$002(ReferSignEasyDialogFragment.this, paramAnonymousString);
            return;
          }
          android.util.Log.d("Branch_Test", "Error generating link to share.");
          ReferSignEasyDialogFragment.access$002(ReferSignEasyDialogFragment.this, "");
        }
      });
      return paramBundle;
      paramLayoutInflater.setTitle(2131231113);
      break;
      paramLayoutInflater.setNavigationIcon(getResources().getDrawable(2130837811));
      break label131;
    }
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.mBroadcastReceiver != null) {
      LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mBroadcastReceiver);
    }
  }
  
  public void onResume()
  {
    super.onResume();
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
    this.mBroadcastReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if (paramAnonymousIntent.getAction().equals("BROADCAST_FILTER_REFERRAL_DETAILS")) {
          ReferSignEasyDialogFragment.this.refreshReferralCountView();
        }
      }
    };
    localLocalBroadcastManager.registerReceiver(this.mBroadcastReceiver, new IntentFilter("BROADCAST_FILTER_REFERRAL_DETAILS"));
  }
  
  public void onStart()
  {
    super.onStart();
  }
}
