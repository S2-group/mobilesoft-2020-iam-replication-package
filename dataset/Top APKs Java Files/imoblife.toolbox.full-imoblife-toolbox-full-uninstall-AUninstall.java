package imoblife.toolbox.full.uninstall;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import base.util.CustomToast;
import base.util.LogUtil;
import base.util.PackageUtil;
import base.util.PreferenceHelper;
import base.util.os.FormatUtil;
import base.util.ui.titlebar.BaseTitlebarActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.ButtonCallback;
import com.afollestad.materialdialogs.MaterialDialog.ListCallback;
import imoblife.android.os.ModernAsyncTask;
import imoblife.android.os.ModernAsyncTask.Status;
import imoblife.startupmanager.roottools.execution.ShellUtils;
import imoblife.toolbox.full.clean.StatusbarUtil;
import imoblife.toolbox.full.receiver.PackageEventReceiver;
import imoblife.toolbox.full.receiver.PackageEventReceiver.PackageEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import net.londatiga.android.QuickAction.OnActionItemClickListener;
import util.android.content.pm.PackageManagerUtil;
import util.ui.ViewUtil;

public class AUninstall
  extends BaseTitlebarActivity
  implements AdapterView.OnItemClickListener, View.OnClickListener, PackageEventReceiver.PackageEventListener
{
  private static final int HANDLE_ADD = 1;
  private static final int HANDLE_CLEAR = 3;
  private static final int HANDLE_PROGRESS = 5;
  private static final int HANDLE_REMOVE = 4;
  private static final int HANDLE_SORT = 2;
  private static final int HANDLE_UPDATE = 0;
  public static final String TAG = AUninstall.class.getSimpleName();
  private UninstallAdapter adapter;
  private CheckBox checkbox_cb;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (AUninstall.this.adapter == null) {}
      for (;;)
      {
        return;
        switch (paramAnonymousMessage.what)
        {
        }
        while (AUninstall.this.getActivity() != null)
        {
          StatusbarUtil.setStatusbarLeftText(AUninstall.this.getActivity(), "" + AUninstall.this.adapter.getCount());
          StatusbarUtil.setStatusbarRightText(AUninstall.this.getActivity(), "" + Formatter.formatFileSize(AUninstall.this.getContext(), AUninstall.this.adapter.getSize()));
          return;
          if (!AUninstall.this.isUpdateTaskRunning())
          {
            AUninstall.access$202(AUninstall.this, new AUninstall.UpdateTask(AUninstall.this, null));
            AUninstall.this.updateTask.execute(new Void[0]);
            continue;
            AUninstall.this.adapter.add((AUninstall.UninstallItem)paramAnonymousMessage.obj);
            continue;
            AUninstall.this.adapter.sort();
            continue;
            AUninstall.this.adapter.clear();
            continue;
            paramAnonymousMessage = paramAnonymousMessage.getData();
            int i = paramAnonymousMessage.getInt("pkgPosition");
            paramAnonymousMessage.getString("pkgName");
            AUninstall.this.adapter.remove(i);
            continue;
            StatusbarUtil.setProgressbarText(AUninstall.this.getActivity(), " " + paramAnonymousMessage.obj);
            StatusbarUtil.setProgressbarProgress(AUninstall.this.getActivity(), paramAnonymousMessage.arg1, paramAnonymousMessage.arg2);
          }
        }
      }
    }
  };
  private boolean isAllSelected;
  private ListView listview_lv;
  private UpdateTask updateTask;
  
  public AUninstall() {}
  
  private int getSortByPreference()
  {
    return PreferenceHelper.getInt(getContext(), getString(2131165975), 0);
  }
  
  private boolean isUpdateTaskRunning()
  {
    return (this.updateTask != null) && (!this.updateTask.isCancelled()) && (this.updateTask.getStatus() == ModernAsyncTask.Status.RUNNING);
  }
  
  private void setSortByPreference(int paramInt)
  {
    PreferenceHelper.setInt(getContext(), getString(2131165975), paramInt);
  }
  
  private void toggleSelect()
  {
    if (!this.isAllSelected) {}
    for (boolean bool = true;; bool = false)
    {
      this.isAllSelected = bool;
      this.checkbox_cb.setChecked(this.isAllSelected);
      int i = 0;
      while ((this.adapter != null) && (i < this.adapter.getCount()))
      {
        this.adapter.setChecked(i, this.isAllSelected);
        i += 1;
      }
    }
  }
  
  protected Activity getActivity()
  {
    return this;
  }
  
  public String getTrackModule()
  {
    return getClass().getSimpleName();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131689648) {
      if (this.adapter.loadSelectedAmount() == 0) {
        CustomToast.show(getContext(), 2131165638, 0);
      }
    }
    do
    {
      return;
      new ConfirmDialog(this.adapter.loadSelectedAmount(), null);
      return;
      if (paramView.getId() == 2131689711)
      {
        paramView = this.handler.obtainMessage(0);
        this.handler.sendMessage(paramView);
        return;
      }
    } while (paramView.getId() != 2131689712);
    toggleSelect();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903279);
    setTitle(getString(2131165759));
    setActionVisibility(0);
    ((ImageView)findViewById(2131689711)).setOnClickListener(this);
    ((TextView)findViewById(2131689650)).setText(getString(2131165757));
    ((LinearLayout)findViewById(2131689648)).setOnClickListener(this);
    paramBundle = (LinearLayout)findViewById(2131689712);
    paramBundle.setOnClickListener(this);
    paramBundle.setVisibility(0);
    this.checkbox_cb = ((CheckBox)findViewById(2131689696));
    this.listview_lv = ((ListView)findViewById(2131689909));
    this.listview_lv.setOnItemClickListener(this);
    applyScrollListener(this.listview_lv);
    this.adapter = new UninstallAdapter(getActivity());
    this.listview_lv.setAdapter(this.adapter);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    PackageEventReceiver.removeListener(this);
    if (this.updateTask != null) {
      this.updateTask.cancel(true);
    }
    if (this.handler != null) {
      this.handler.removeCallbacksAndMessages(null);
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (isUpdateTaskRunning()) {
      return;
    }
    this.adapter.toggleChecked(paramInt);
  }
  
  public void onPackageAdded(String paramString) {}
  
  public void onPackageRemoved(String paramString) {}
  
  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    PackageEventReceiver.addListener(this);
    paramBundle = this.handler.obtainMessage(0);
    this.handler.sendMessage(paramBundle);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  public void onTitlebarActionClick(View paramView)
  {
    super.onTitlebarActionClick(paramView);
    new QuickActionMenu(paramView);
  }
  
  private class ConfirmDialog
    extends MaterialDialog.ButtonCallback
  {
    private ConfirmDialog(int paramInt)
    {
      new MaterialDialog.Builder(AUninstall.this.getActivity()).title(2131165313).content(paramInt + AUninstall.this.getString(2131165756)).positiveText(2131165312).negativeText(2131165309).callback(this).show();
    }
    
    public void onPositive(MaterialDialog paramMaterialDialog)
    {
      new AUninstall.UninstallTask(AUninstall.this, null).execute(new Void[0]);
    }
  }
  
  private class ItemDialog
    implements MaterialDialog.ListCallback
  {
    private final int OPTION0 = 0;
    private final int OPTION1 = 1;
    private final int OPTION2 = 2;
    private String appName;
    private String pkgName;
    private int position;
    
    private ItemDialog(int paramInt)
    {
      this.position = paramInt;
      this.appName = AUninstall.this.adapter.getAppName(paramInt);
      this.pkgName = AUninstall.this.adapter.getPkgName(paramInt);
      String str1 = AUninstall.this.getString(2131165755);
      String str2 = AUninstall.this.getString(2131165478);
      String str3 = AUninstall.this.getString(2131165257);
      new MaterialDialog.Builder(AUninstall.this.getActivity()).title(this.appName).items(new String[] { str1, str2, str3 }).itemsCallback(this).show();
    }
    
    public void onSelection(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      switch (paramInt)
      {
      default: 
        return;
      case 0: 
        paramMaterialDialog = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        paramMaterialDialog.addDataScheme("package");
        AUninstall.this.registerReceiver(new AUninstall.UninstallReceiver(AUninstall.this, this.position, this.pkgName), paramMaterialDialog);
        PackageUtil.startUninstall(AUninstall.this.getContext(), this.pkgName);
        return;
      case 1: 
        PackageUtil.switchApp(AUninstall.this.getContext(), this.pkgName);
        return;
      }
      PackageUtil.showAppDetails(AUninstall.this.getContext(), this.pkgName);
    }
  }
  
  private class QuickActionMenu
    implements QuickAction.OnActionItemClickListener
  {
    public QuickActionMenu(View paramView)
    {
      int i = AUninstall.this.getSortByPreference();
      Drawable localDrawable1 = AUninstall.this.getResources().getDrawable(2130837652);
      Drawable localDrawable2 = AUninstall.this.getResources().getDrawable(2130837651);
      QuickAction localQuickAction = new QuickAction(AUninstall.this.getActivity(), 1);
      localQuickAction.setOnActionItemClickListener(this);
      String str = AUninstall.this.getString(2131165566) + " ";
      if (i == 0)
      {
        localQuickAction.addActionItem(new ActionItem(0, str + AUninstall.this.getString(2131165791), localDrawable1), true);
        localQuickAction.addActionItem(new ActionItem(1, str + AUninstall.this.getString(2131165683), localDrawable2), true);
        localQuickAction.addActionItem(new ActionItem(2, str + AUninstall.this.getString(2131165686), localDrawable2), false);
      }
      for (;;)
      {
        localQuickAction.show(paramView);
        return;
        if (i == 1)
        {
          localQuickAction.addActionItem(new ActionItem(0, str + AUninstall.this.getString(2131165791), localDrawable2), true);
          localQuickAction.addActionItem(new ActionItem(1, str + AUninstall.this.getString(2131165683), localDrawable1), true);
          localQuickAction.addActionItem(new ActionItem(2, str + AUninstall.this.getString(2131165686), localDrawable2), false);
        }
        else if (i == 2)
        {
          localQuickAction.addActionItem(new ActionItem(0, str + AUninstall.this.getString(2131165791), localDrawable2), true);
          localQuickAction.addActionItem(new ActionItem(1, str + AUninstall.this.getString(2131165683), localDrawable2), true);
          localQuickAction.addActionItem(new ActionItem(2, str + AUninstall.this.getString(2131165686), localDrawable1), false);
        }
      }
    }
    
    public void onItemClick(QuickAction paramQuickAction, int paramInt1, int paramInt2)
    {
      paramQuickAction = AUninstall.this.handler.obtainMessage(2);
      paramQuickAction.arg1 = paramInt2;
      AUninstall.this.handler.sendMessage(paramQuickAction);
      AUninstall.this.setSortByPreference(paramInt2);
    }
  }
  
  private class UninstallAdapter
    extends BaseAdapter
  {
    private View.OnClickListener itemLeftListener = new AUninstall.UninstallAdapter.1(this);
    private List<AUninstall.UninstallItem> list = new ArrayList();
    
    public UninstallAdapter(Context paramContext) {}
    
    public void add(AUninstall.UninstallItem paramUninstallItem)
    {
      this.list.add(paramUninstallItem);
      notifyDataSetChanged();
    }
    
    public void clear()
    {
      this.list.clear();
      notifyDataSetChanged();
    }
    
    public String getAppName(int paramInt)
    {
      return getItem(paramInt).getAppName();
    }
    
    public int getCheckedAmount()
    {
      int j = 0;
      int i = 0;
      while (i < getCount())
      {
        int k = j;
        if (getItem(i).isChecked()) {
          k = j + 1;
        }
        i += 1;
        j = k;
      }
      return j;
    }
    
    public int getCount()
    {
      return this.list.size();
    }
    
    public AUninstall.UninstallItem getItem(int paramInt)
    {
      return (AUninstall.UninstallItem)this.list.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public String getPkgName(int paramInt)
    {
      return getItem(paramInt).getPkgName();
    }
    
    public long getSize()
    {
      long l = 0L;
      int i = 0;
      while (i < getCount())
      {
        l += AUninstall.UninstallItem.access$500(getItem(i));
        i += 1;
      }
      return l;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = AUninstall.this.getInflater().inflate(2130903280, null);
        paramViewGroup = new AUninstall.ViewHolder(AUninstall.this, null);
        paramViewGroup.icon_iv = ((ImageView)paramView.findViewById(2131689747));
        paramViewGroup.name_tv = ((TextView)paramView.findViewById(2131689785));
        paramViewGroup.size_tv = ((TextView)paramView.findViewById(2131690072));
        paramViewGroup.checkbox_cb = ((CheckBox)paramView.findViewById(2131689696));
        paramViewGroup.time_tv = ((TextView)paramView.findViewById(2131689979));
        paramViewGroup.item_left_ll = ((LinearLayout)paramView.findViewById(2131689781));
        paramView.setTag(paramViewGroup);
      }
      synchronized (getItem(paramInt))
      {
        AUninstall.this.loadImage(paramViewGroup.icon_iv, AUninstall.UninstallItem.access$700(???), null);
        paramViewGroup.name_tv.setText(AUninstall.UninstallItem.access$800(???));
        paramViewGroup.size_tv.setText(Formatter.formatFileSize(AUninstall.this.getContext(), AUninstall.UninstallItem.access$500(???)));
        paramViewGroup.checkbox_cb.setChecked(AUninstall.UninstallItem.access$900(???));
        paramViewGroup.time_tv.setText(AUninstall.UninstallItem.access$1000(???));
        paramViewGroup.item_left_ll.setTag(new Integer(paramInt));
        paramViewGroup.item_left_ll.setOnClickListener(this.itemLeftListener);
        return paramView;
        paramViewGroup = (AUninstall.ViewHolder)paramView.getTag();
      }
    }
    
    public boolean isChecked(int paramInt)
    {
      return getItem(paramInt).isChecked();
    }
    
    public int loadSelectedAmount()
    {
      int j = 0;
      int i = 0;
      while (i < this.list.size())
      {
        int k = j;
        if (getItem(i).isChecked()) {
          k = j + 1;
        }
        i += 1;
        j = k;
      }
      return j;
    }
    
    public void remove(int paramInt)
    {
      try
      {
        this.list.remove(paramInt);
        notifyDataSetChanged();
        return;
      }
      catch (Exception localException)
      {
        LogUtil.w(AUninstall.TAG, localException);
      }
    }
    
    public void setChecked(int paramInt, boolean paramBoolean)
    {
      getItem(paramInt).setChecked(paramBoolean);
      notifyDataSetChanged();
    }
    
    public void sort()
    {
      int i = AUninstall.this.getSortByPreference();
      Collections.sort(this.list, new AUninstall.UninstallAdapter.2(this, i));
      notifyDataSetChanged();
    }
    
    public void toggleChecked(int paramInt)
    {
      getItem(paramInt).toggleChecked();
      notifyDataSetChanged();
    }
  }
  
  private class UninstallItem
  {
    private String appName;
    private boolean checked;
    private long fileSize;
    private String iconUri;
    private String installTimeString = "";
    private String pkgName;
    
    public UninstallItem(PackageInfo paramPackageInfo, String paramString1, String paramString2)
    {
      this.pkgName = paramString1;
      this.appName = paramString2;
      this.iconUri = ("package://" + paramString1);
      this.fileSize = PackageManagerUtil.loadMaxPackageSize(AUninstall.this.getContext(), paramString1);
      if (Build.VERSION.SDK_INT > 8) {
        this.installTimeString = FormatUtil.formatTime(paramPackageInfo.firstInstallTime, "yyyy-MM-dd");
      }
    }
    
    public String getAppName()
    {
      return this.appName;
    }
    
    public String getPkgName()
    {
      return this.pkgName;
    }
    
    public boolean isChecked()
    {
      return this.checked;
    }
    
    public boolean setChecked(boolean paramBoolean)
    {
      this.checked = paramBoolean;
      return isChecked();
    }
    
    public boolean toggleChecked()
    {
      if (!this.checked) {}
      for (boolean bool = true;; bool = false)
      {
        setChecked(bool);
        return isChecked();
      }
    }
  }
  
  private class UninstallReceiver
    extends BroadcastReceiver
  {
    private String pkgName;
    private int position;
    
    public UninstallReceiver(int paramInt, String paramString)
    {
      this.position = paramInt;
      this.pkgName = paramString;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        paramIntent = paramIntent.getDataString().replace("package:", "");
        if (this.pkgName.equals(paramIntent))
        {
          paramIntent = new Bundle();
          paramIntent.putInt("pkgPosition", this.position);
          paramIntent.putString("pkgName", AUninstall.this.adapter.getPkgName(this.position));
          Message localMessage = AUninstall.this.handler.obtainMessage(4);
          localMessage.setData(paramIntent);
          AUninstall.this.handler.sendMessage(localMessage);
          paramContext.unregisterReceiver(this);
        }
        return;
      }
      catch (Exception paramContext)
      {
        LogUtil.w(AUninstall.TAG, paramContext);
      }
    }
  }
  
  private class UninstallTask
    extends ModernAsyncTask<Void, String, Void>
    implements DialogInterface.OnCancelListener
  {
    private MaterialDialog dialog;
    
    private UninstallTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      Object localObject;
      IntentFilter localIntentFilter;
      if (PreferenceHelper.isRooted(AUninstall.this.getContext()))
      {
        i = AUninstall.this.adapter.getCount() - 1;
        while (i >= 0)
        {
          if (AUninstall.this.adapter.isChecked(i))
          {
            localObject = AUninstall.this.adapter.getItem(i);
            paramVarArgs = ((AUninstall.UninstallItem)localObject).pkgName;
            localObject = ((AUninstall.UninstallItem)localObject).appName;
            localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
            localIntentFilter.addDataScheme("package");
            AUninstall.this.registerReceiver(new AUninstall.UninstallReceiver(AUninstall.this, i, paramVarArgs), localIntentFilter);
            publishProgress(new String[] { localObject });
            ShellUtils.runPmUninstallCommand(paramVarArgs);
          }
          i -= 1;
        }
      }
      int i = 0;
      while (i < AUninstall.this.adapter.getCount())
      {
        if (AUninstall.this.adapter.isChecked(i))
        {
          localObject = AUninstall.this.adapter.getItem(i);
          paramVarArgs = ((AUninstall.UninstallItem)localObject).pkgName;
          localObject = ((AUninstall.UninstallItem)localObject).appName;
          localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
          localIntentFilter.addDataScheme("package");
          AUninstall.this.registerReceiver(new AUninstall.UninstallReceiver(AUninstall.this, i, paramVarArgs), localIntentFilter);
          publishProgress(new String[] { localObject });
          PackageUtil.startUninstall(AUninstall.this.getContext(), paramVarArgs);
        }
        i += 1;
      }
      return null;
    }
    
    public void onCancel(DialogInterface paramDialogInterface)
    {
      cancel(true);
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      try
      {
        AUninstall.this.checkbox_cb.setChecked(false);
        if (PreferenceHelper.isRooted(AUninstall.this.getContext())) {
          this.dialog.dismiss();
        }
        return;
      }
      catch (Exception paramVoid)
      {
        LogUtil.w(AUninstall.TAG, paramVoid);
      }
    }
    
    protected void onPreExecute()
    {
      try
      {
        if (PreferenceHelper.isRooted(AUninstall.this.getContext()))
        {
          this.dialog = new MaterialDialog.Builder(AUninstall.this.getActivity()).content(AUninstall.this.getResources().getString(2131165530)).title(AUninstall.this.getResources().getString(2131165757)).progress(false, AUninstall.this.adapter.getCheckedAmount(), true).cancelable(false).build();
          this.dialog.show();
        }
        return;
      }
      catch (Exception localException)
      {
        LogUtil.w(AUninstall.TAG, localException);
      }
    }
    
    protected void onProgressUpdate(String... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
      try
      {
        this.dialog.setMessage(paramVarArgs[0]);
        this.dialog.incrementProgress(1);
        return;
      }
      catch (Exception paramVarArgs)
      {
        LogUtil.w(AUninstall.TAG, paramVarArgs);
      }
    }
  }
  
  private class UpdateTask
    extends ModernAsyncTask<Void, Void, Void>
  {
    private UpdateTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = AUninstall.this.getPM().getInstalledPackages(0);
        int i = 0;
        while (i < paramVarArgs.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramVarArgs.get(i);
          String str1 = ((PackageInfo)paramVarArgs.get(i)).packageName;
          String str2 = localPackageInfo.applicationInfo.loadLabel(AUninstall.this.getPM()).toString();
          Message localMessage = AUninstall.this.handler.obtainMessage(5);
          localMessage.arg1 = i;
          localMessage.arg2 = paramVarArgs.size();
          localMessage.obj = str2;
          AUninstall.this.handler.sendMessage(localMessage);
          if (isCancelled()) {
            break;
          }
          if (!PackageUtil.isSystemApp(AUninstall.this.getContext(), str1))
          {
            localMessage = AUninstall.this.handler.obtainMessage(1);
            localMessage.obj = new AUninstall.UninstallItem(AUninstall.this, localPackageInfo, str1, str2);
            AUninstall.this.handler.sendMessage(localMessage);
          }
          i += 1;
        }
        return null;
      }
      catch (Exception paramVarArgs)
      {
        LogUtil.w(AUninstall.TAG, paramVarArgs);
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      try
      {
        AUninstall.this.checkbox_cb.setChecked(false);
        paramVoid = AUninstall.this.handler.obtainMessage(2);
        AUninstall.this.handler.sendMessage(paramVoid);
        StatusbarUtil.setProgressbarVisible(AUninstall.this.getActivity(), false);
        ViewUtil.setEmptyText(AUninstall.this.getContext(), AUninstall.this.listview_lv, 2131165758);
        return;
      }
      catch (Exception paramVoid)
      {
        LogUtil.w(AUninstall.TAG, paramVoid);
      }
    }
    
    protected void onPreExecute()
    {
      try
      {
        Message localMessage = AUninstall.this.handler.obtainMessage(3);
        AUninstall.this.handler.sendMessage(localMessage);
        StatusbarUtil.setProgressbarVisible(AUninstall.this.getActivity(), true);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.w(AUninstall.TAG, localException);
      }
    }
  }
  
  private class ViewHolder
  {
    public CheckBox checkbox_cb;
    public ImageView icon_iv;
    public LinearLayout item_left_ll;
    public TextView name_tv;
    public TextView size_tv;
    public TextView time_tv;
    
    private ViewHolder() {}
  }
}
