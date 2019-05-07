package jp.pioneer.mbg.appradio.AppRadioLauncher.screen;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jp.pioneer.mbg.appradio.recommend.al;
import jp.pioneer.mbg.appradio.recommend.c;

class cz
  extends BaseAdapter
{
  Context a;
  
  public cz(LaunchThroughSetting paramLaunchThroughSetting, Context paramContext)
  {
    this.a = paramContext;
  }
  
  public int getCount()
  {
    return LaunchThroughSetting.a(this.b).size();
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = LayoutInflater.from(this.a).inflate(2130903308, null);
    }
    paramView = (ImageView)paramViewGroup.findViewById(2131624050);
    TextView localTextView = (TextView)paramViewGroup.findViewById(2131624004);
    RadioButton localRadioButton = (RadioButton)paramViewGroup.findViewById(2131624277);
    c localC = ((al)LaunchThroughSetting.a(this.b).get(paramInt)).c();
    PackageManager localPackageManager = this.b.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(8192).iterator();
    if (!localIterator.hasNext())
    {
      label107:
      localTextView.setText(localC.b());
      if ((this.b.c.get(String.valueOf(paramInt)) != null) && (((Boolean)this.b.c.get(String.valueOf(paramInt))).booleanValue())) {
        break label261;
      }
      this.b.c.put(String.valueOf(paramInt), Boolean.valueOf(false));
    }
    label261:
    for (boolean bool = false;; bool = true)
    {
      localTextView.setEllipsize(TextUtils.TruncateAt.END);
      localTextView.setSingleLine(true);
      localRadioButton.setChecked(bool);
      return paramViewGroup;
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (!localPackageInfo.packageName.equals(((al)LaunchThroughSetting.a(this.b).get(paramInt)).d())) {
        break;
      }
      paramView.setImageDrawable(localPackageInfo.applicationInfo.loadIcon(localPackageManager));
      break label107;
    }
  }
}
