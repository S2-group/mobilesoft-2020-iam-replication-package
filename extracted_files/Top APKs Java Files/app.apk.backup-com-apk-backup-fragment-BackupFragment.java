package com.apk.backup.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.apk.backup.MainActivity;
import com.apk.backup.adapter.BackupListAdapter;
import com.apk.backup.data.Constant;
import com.apk.backup.data.PermissionUtil;
import com.apk.backup.data.Utils;
import com.apk.backup.model.BackupModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BackupFragment
  extends Fragment
{
  private ActionMode act_mode = null;
  public BackupListAdapter bAdapter;
  private ListView listView;
  private boolean mode_checkall = false;
  private AbsListView.MultiChoiceModeListener multiChoiceModeListener = new AbsListView.MultiChoiceModeListener()
  {
    public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
    {
      int i = paramAnonymousMenuItem.getItemId();
      if (i != 2131230728)
      {
        if (i != 2131230736)
        {
          if (i != 2131230751) {
            return false;
          }
          BackupFragment.this.uninstallApp(BackupFragment.this.bAdapter.getSelected());
          return true;
        }
        BackupFragment.this.toogleCheckAll();
        return true;
      }
      new BackupFragment.FileSaveTask(BackupFragment.this, BackupFragment.this.bAdapter.getSelected()).execute(new Void[0]);
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
    {
      paramAnonymousActionMode.getMenuInflater().inflate(2131427328, paramAnonymousMenu);
      paramAnonymousMenu = new StringBuilder();
      paramAnonymousMenu.append(BackupFragment.this.listView.getCheckedItemCount());
      paramAnonymousMenu.append(" conversation selected");
      paramAnonymousActionMode.setTitle(paramAnonymousMenu.toString());
      BackupFragment.access$902(BackupFragment.this, paramAnonymousActionMode);
      ((MainActivity)BackupFragment.this.getActivity()).getToolbar().setVisibility(8);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
    {
      int i = 0;
      while (i < BackupFragment.this.bAdapter.getCount())
      {
        BackupFragment.this.listView.setItemChecked(i, BackupFragment.this.mode_checkall);
        i += 1;
      }
      BackupFragment.this.bAdapter.resetSelected();
      ((MainActivity)BackupFragment.this.getActivity()).getToolbar().setVisibility(0);
    }
    
    public void onItemCheckedStateChanged(ActionMode paramAnonymousActionMode, int paramAnonymousInt, long paramAnonymousLong, boolean paramAnonymousBoolean)
    {
      int i = BackupFragment.this.listView.getCheckedItemCount();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(i);
      localStringBuilder.append(" selected");
      paramAnonymousActionMode.setTitle(localStringBuilder.toString());
      BackupFragment.this.bAdapter.setSelected(paramAnonymousInt, paramAnonymousBoolean);
    }
    
    public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
    {
      return false;
    }
  };
  private PackageManager pm;
  private ProgressBar progressBar;
  private boolean taskRunning = false;
  private View view;
  
  public BackupFragment() {}
  
  private void dialogAppOption(int paramInt)
  {
    Object localObject = new AlertDialog.Builder(getActivity());
    final BackupModel localBackupModel = this.bAdapter.getItem(paramInt);
    ((AlertDialog.Builder)localObject).setTitle("Application Option");
    ListView localListView = new ListView(getActivity());
    localListView.setPadding(25, 25, 25, 25);
    localListView.setAdapter(new ArrayAdapter(getActivity(), 17367043, new String[] { "Backup", "Uninstall", "Details" }));
    ((AlertDialog.Builder)localObject).setView(localListView);
    localObject = ((AlertDialog.Builder)localObject).create();
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        this.val$dialog.dismiss();
        paramAnonymousAdapterView = new ArrayList();
        paramAnonymousAdapterView.add(localBackupModel);
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 2: 
          BackupFragment.this.showInstalledAppDetails(localBackupModel.getPackgae_name());
          return;
        case 1: 
          BackupFragment.this.uninstallApp(paramAnonymousAdapterView);
          return;
        }
        new BackupFragment.FileSaveTask(BackupFragment.this, paramAnonymousAdapterView).execute(new Void[0]);
      }
    });
    ((AppCompatDialog)localObject).show();
  }
  
  private void setMultipleChoice()
  {
    this.listView.setChoiceMode(3);
    this.listView.setMultiChoiceModeListener(this.multiChoiceModeListener);
  }
  
  private void showInstalledAppDetails(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    startActivity(localIntent);
  }
  
  private void toogleCheckAll()
  {
    this.mode_checkall ^= true;
    int i = 0;
    while (i < this.bAdapter.getCount())
    {
      this.listView.setItemChecked(i, this.mode_checkall);
      i += 1;
    }
    if (this.mode_checkall)
    {
      this.bAdapter.selectAll();
      return;
    }
    this.bAdapter.resetSelected();
  }
  
  private void uninstallApp(List<BackupModel> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      startActivity(new Intent("android.intent.action.DELETE", Uri.fromParts("package", ((BackupModel)paramList.next()).getPackgae_name(), null)));
    }
  }
  
  public ActionMode getActionMode()
  {
    return this.act_mode;
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2131361836, paramViewGroup, false);
    this.listView = ((ListView)this.view.findViewById(2131230822));
    this.progressBar = ((ProgressBar)this.view.findViewById(2131230847));
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        BackupFragment.this.dialogAppOption(paramAnonymousInt);
      }
    });
    this.pm = getActivity().getApplicationContext().getPackageManager();
    return this.view;
  }
  
  public void onResume()
  {
    super.onResume();
    if (PermissionUtil.isAllPermissionGranted(getActivity())) {
      refresh(false);
    }
  }
  
  public void refresh(boolean paramBoolean)
  {
    if (this.taskRunning)
    {
      Snackbar.make(this.view, "Task still running", -1).show();
      return;
    }
    new AppListLoaderTask(paramBoolean).execute(new String[0]);
  }
  
  private class AppListLoaderTask
    extends AsyncTask<String, String, String>
  {
    private List<BackupModel> app_list = new ArrayList();
    private boolean fab_flag = false;
    private String status = "";
    
    public AppListLoaderTask(boolean paramBoolean)
    {
      this.fab_flag = paramBoolean;
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = BackupFragment.this.pm.getInstalledPackages(0);
        i = 0;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          int i;
          PackageInfo localPackageInfo;
          BackupModel localBackupModel;
          label178:
          continue;
          i += 1;
        }
      }
      if (i < paramVarArgs.size())
      {
        localPackageInfo = (PackageInfo)paramVarArgs.get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          localBackupModel = new BackupModel();
          localBackupModel.setApp_name(localPackageInfo.applicationInfo.loadLabel(BackupFragment.this.pm).toString());
          localBackupModel.setPackgae_name(localPackageInfo.packageName);
          localBackupModel.setVersion_name(localPackageInfo.versionName);
          localBackupModel.setVersion_code(localPackageInfo.versionCode);
          localBackupModel.setApp_icon(localPackageInfo.applicationInfo.loadIcon(BackupFragment.this.pm));
          localBackupModel.setFile(new File(localPackageInfo.applicationInfo.publicSourceDir));
          this.app_list.add(localBackupModel);
        }
      }
      else
      {
        this.status = "success";
        break label178;
        this.status = "failed";
        publishProgress(new String[0]);
        return null;
      }
    }
    
    protected void onPreExecute()
    {
      BackupFragment.access$102(BackupFragment.this, true);
      this.app_list.clear();
      BackupFragment.this.progressBar.setVisibility(0);
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(String... paramVarArgs)
    {
      BackupFragment.this.progressBar.setVisibility(8);
      if (this.status.equals("success"))
      {
        Collections.sort(this.app_list, new Comparator()
        {
          public int compare(BackupModel paramAnonymousBackupModel1, BackupModel paramAnonymousBackupModel2)
          {
            return paramAnonymousBackupModel1.getApp_name().toLowerCase().compareTo(paramAnonymousBackupModel2.getApp_name().toLowerCase());
          }
        });
        this.app_list = Utils.backupExistChecker(this.app_list, BackupFragment.this.getActivity());
        BackupFragment.this.bAdapter = new BackupListAdapter(BackupFragment.this.getActivity(), this.app_list);
        BackupFragment.this.listView.setAdapter(BackupFragment.this.bAdapter);
        BackupFragment.this.setMultipleChoice();
      }
      else
      {
        Snackbar.make(BackupFragment.this.view, "Failed load applications", -1).show();
      }
      BackupFragment.access$102(BackupFragment.this, false);
      if (this.fab_flag) {
        Snackbar.make(BackupFragment.this.view, "Refresh finished", -1).show();
      }
      super.onProgressUpdate(paramVarArgs);
    }
  }
  
  private class FileSaveTask
    extends AsyncTask<Void, Integer, File>
  {
    private ProgressDialog progress;
    private List<BackupModel> selected_app;
    
    public FileSaveTask()
    {
      Object localObject;
      this.selected_app = localObject;
    }
    
    protected File doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = null;
      int i = 0;
      while (this.selected_app.size() > i)
      {
        paramVarArgs = new StringBuilder();
        paramVarArgs.append(((BackupModel)this.selected_app.get(i)).getApp_name());
        paramVarArgs.append("_");
        paramVarArgs.append(((BackupModel)this.selected_app.get(i)).getVersion_name());
        paramVarArgs.append(".apk");
        Object localObject1 = paramVarArgs.toString();
        paramVarArgs = new File(Constant.BACKUP_FOLDER);
        if (!paramVarArgs.exists()) {
          paramVarArgs.mkdirs();
        }
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramVarArgs.getPath());
        ((StringBuilder)localObject2).append("/");
        ((StringBuilder)localObject2).append((String)localObject1);
        localObject2 = new File(((StringBuilder)localObject2).toString());
        try
        {
          ((File)localObject2).createNewFile();
          localObject1 = new FileInputStream(((BackupModel)this.selected_app.get(i)).getFile());
          localObject2 = new FileOutputStream((File)localObject2);
          byte[] arrayOfByte = new byte['Ѐ'];
          for (;;)
          {
            int j = ((InputStream)localObject1).read(arrayOfByte);
            if (j <= 0) {
              break;
            }
            ((OutputStream)localObject2).write(arrayOfByte, 0, j);
          }
          ((InputStream)localObject1).close();
          ((OutputStream)localObject2).close();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
        }
        publishProgress(new Integer[] { Integer.valueOf(i) });
        i += 1;
      }
      return paramVarArgs;
    }
    
    protected void onPostExecute(File paramFile)
    {
      if (this.progress != null) {
        this.progress.dismiss();
      }
      if (paramFile != null)
      {
        paramFile = new AlertDialog.Builder(BackupFragment.this.getActivity());
        paramFile.setCancelable(false);
        paramFile.setTitle("Backup Completed");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("APK Location: ");
        localStringBuilder.append(Constant.BACKUP_FOLDER);
        paramFile.setMessage(localStringBuilder.toString());
        paramFile.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            BackupFragment.this.bAdapter.resetSelected();
            BackupFragment.this.bAdapter.notifyDataSetChanged();
            BackupFragment.this.refresh(false);
            paramAnonymousDialogInterface.dismiss();
          }
        });
        paramFile.show();
        return;
      }
      Toast.makeText(BackupFragment.this.getActivity(), "APK backup failed", 1).show();
    }
    
    protected void onPreExecute()
    {
      this.progress = new ProgressDialog(BackupFragment.this.getActivity());
      this.progress.setMessage("App Backup");
      this.progress.setProgressStyle(1);
      this.progress.setMax(this.selected_app.size());
      this.progress.setCancelable(false);
      this.progress.show();
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs)
    {
      this.progress.setProgress(paramVarArgs[0].intValue());
    }
  }
}
