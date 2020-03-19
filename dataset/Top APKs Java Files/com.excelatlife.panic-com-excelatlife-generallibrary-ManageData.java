package com.excelatlife.generallibrary;

import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;

public class ManageData
  extends BaseActivity
  implements View.OnClickListener
{
  private Button backupservice;
  private Button exportDbToSdButton;
  private Button importsdcard;
  private boolean noData;
  
  public ManageData() {}
  
  private File getDataDir(String paramString)
  {
    try
    {
      paramString = getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo;
      if ((paramString != null) && (paramString.dataDir != null))
      {
        paramString = new File(paramString.dataDir);
        return paramString;
      }
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  private boolean isExternalStorageAvail()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void openRestoreDialog()
  {
    new AlertDialog.Builder(this).setTitle("RESTORE DATA? Use only after a re-install or loss of data.").setItems(R.array.restore, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt == 1)
        {
          if (ManageData.this.isExternalStorageAvail()) {
            new ManageData.ImportDatabaseFileTask(ManageData.this, null).execute(new String[0]);
          }
        }
        else {
          return;
        }
        Utilities.showToastText(ManageData.this, "Storage or SD card is not available, unable to import data.");
      }
    }).show();
  }
  
  private void openSDDialog()
  {
    new AlertDialog.Builder(this).setTitle("SAVE TO STORAGE? This is to test save function.").setItems(R.array.sdsave, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt == 1)
        {
          if (ManageData.this.isExternalStorageAvail()) {
            new ManageData.ExportDatabaseFileTask(ManageData.this).execute(new String[0]);
          }
        }
        else {
          return;
        }
        Utilities.showToastText(ManageData.this, "Storage or SD card is not available, unable to export data.");
      }
    }).show();
  }
  
  protected NavDrawerActivityConfiguration getNavDrawerConfiguration()
  {
    NavDrawerActivityConfiguration localNavDrawerActivityConfiguration = super.getNavDrawerConfiguration();
    localNavDrawerActivityConfiguration.setMainLayout(R.layout.managedata);
    return localNavDrawerActivityConfiguration;
  }
  
  String getWebAddress()
  {
    return "http://www.excelatlife.com/privacy_policy.htm";
  }
  
  boolean googlePlayInstalled()
  {
    Iterator localIterator = getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((PackageInfo)localIterator.next()).packageName.equals("com.android.vending"));
    return true;
  }
  
  public boolean isExternalStorageRemovable()
  {
    try
    {
      i = Integer.parseInt(Build.VERSION.SDK);
      if (i >= 9) {
        return UtilitiesAPI9.isExternalStorageRemovable();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i = 3;
      }
    }
    return false;
  }
  
  public void onClick(View paramView)
  {
    super.onClick(paramView);
    if (paramView.getId() == R.id.helpBackup) {
      Utilities.openDialog("BACKING UP DATA", R.string.sdcardinfo, this);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Fragment()).commit();
    }
    initAll();
    nullCheckAndSetButton(R.id.helpBackup, this);
    paramBundle = (Button)findViewById(R.id.sdcard);
    if (!isExternalStorageRemovable()) {
      paramBundle.setText("STORAGE");
    }
    this.backupservice = ((Button)findViewById(R.id.backupservice));
    this.backupservice.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((TextView)ManageData.this.findViewById(R.id.txtSd)).setVisibility(8);
        paramAnonymousView = (TextView)ManageData.this.findViewById(R.id.txtInfo);
        paramAnonymousView.setVisibility(0);
        if ((!Build.MODEL.equalsIgnoreCase("Kindle Fire")) && (Build.VERSION.SDK_INT >= 8) && (ManageData.this.googlePlayInstalled()))
        {
          paramAnonymousView.setText("The Android Backup Service can be activated by going to your device's SETTINGS, select PRIVACY, and check BACK UP MY DATA and AUTOMATIC RESTORE. This allows backup of any data for enabled apps including this one.\n\n The Backup Service might not work on all devices. It is suggested you also backup to SD card or internal storage by checking SAVE TO STORAGE in SETTINGS.");
          return;
        }
        paramAnonymousView.setText("Backup Service is not available for this device. You may backup to SD card or internal storage by checking SAVE TO STORAGE in SETTINGS.");
      }
    });
    this.exportDbToSdButton = ((Button)findViewById(R.id.sdcard));
    this.exportDbToSdButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageData.this.openSDDialog();
      }
    });
    this.importsdcard = ((Button)findViewById(R.id.importsdcard));
    this.importsdcard.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageData.this.openRestoreDialog();
      }
    });
  }
  
  protected class ExportDatabaseFileTask
    extends AsyncTask<String, Void, Boolean>
  {
    private final ProgressDialog dialog = new ProgressDialog(ManageData.this);
    private String message;
    
    protected ExportDatabaseFileTask() {}
    
    void copyFile(File paramFile1, File paramFile2)
      throws IOException
    {
      paramFile1 = new FileInputStream(paramFile1).getChannel();
      paramFile2 = new FileOutputStream(paramFile2).getChannel();
      try
      {
        paramFile1.transferTo(0L, paramFile1.size(), paramFile2);
        return;
      }
      finally
      {
        if (paramFile1 != null) {
          paramFile1.close();
        }
        if (paramFile2 != null) {
          paramFile2.close();
        }
      }
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      paramVarArgs = new File(ManageData.this.getDataDir(ManageData.this.getPackageName()) + "/databases/" + "data.db");
      File localFile1 = new File(ManageData.this.getDataDir(ManageData.this.getPackageName()), ManageData.this.getResources().getString(Constants.PREFSNAME));
      File localFile2 = new File(Environment.getExternalStorageDirectory(), ManageData.this.getResources().getString(Constants.DATAFILE));
      if (!localFile2.exists()) {
        localFile2.mkdirs();
      }
      localFile2 = new File(localFile2, paramVarArgs.getName());
      File localFile3 = new File(Environment.getExternalStorageDirectory(), ManageData.this.getResources().getString(Constants.PREFSFILE));
      if (!localFile3.exists()) {
        localFile3.mkdirs();
      }
      localFile3 = new File(localFile3, localFile1.getName());
      try
      {
        localFile2.createNewFile();
        localFile3.createNewFile();
        if (!paramVarArgs.exists()) {
          ManageData.this.noData = true;
        }
        for (;;)
        {
          copyFile(localFile1, localFile3);
          return Boolean.valueOf(true);
          copyFile(paramVarArgs, localFile2);
        }
        return Boolean.valueOf(false);
      }
      catch (IOException paramVarArgs)
      {
        Log.e("mylog", paramVarArgs.getMessage(), paramVarArgs);
        this.message = paramVarArgs.toString();
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (this.dialog.isShowing()) {
        this.dialog.dismiss();
      }
      if (paramBoolean.booleanValue())
      {
        paramBoolean = (TextView)ManageData.this.findViewById(R.id.txtSd);
        paramBoolean.setVisibility(0);
        if (ManageData.this.isExternalStorageRemovable()) {
          paramBoolean.setText("Your data has been saved to sd card. To enable automatic save, check SAVE TO STORAGE in Settings. If you switch devices and want to retrieve your data, you need to use the same sd card and tap RESTORE on the new device.\n\n CAUTION: This data is not encrypted if you save to sd card.\n\n");
        }
        for (;;)
        {
          Utilities.showToastText(ManageData.this, "Export successful!");
          return;
          paramBoolean.setText("Your data has been saved to internal storage. To enable automatic save, check SAVE TO STORAGE in Settings. If you switch devices and want to retrieve your data, you may or may not be able to do so based upon the restrictions set by your device's manufacturer.\n\n");
        }
      }
      paramBoolean = (TextView)ManageData.this.findViewById(R.id.txtSd);
      paramBoolean.setVisibility(0);
      if (ManageData.this.noData) {
        paramBoolean.setText("Your data cannot be backed up because you have not created data to save.");
      }
      for (;;)
      {
        Utilities.showToastText(ManageData.this, "Export failed.");
        return;
        paramBoolean.setText(this.message);
      }
    }
    
    protected void onPreExecute()
    {
      ((TextView)ManageData.this.findViewById(R.id.txtInfo)).setVisibility(8);
      this.dialog.setMessage("Exporting database...");
      this.dialog.show();
    }
  }
  
  private class ImportDatabaseFileTask
    extends AsyncTask<String, Void, Boolean>
  {
    private final ProgressDialog dialog = new ProgressDialog(ManageData.this);
    private String message;
    
    private ImportDatabaseFileTask() {}
    
    void copyFile(File paramFile1, File paramFile2)
      throws IOException
    {
      paramFile1 = new FileInputStream(paramFile1).getChannel();
      paramFile2 = new FileOutputStream(paramFile2).getChannel();
      try
      {
        paramFile1.transferTo(0L, paramFile1.size(), paramFile2);
        return;
      }
      finally
      {
        if (paramFile1 != null) {
          paramFile1.close();
        }
        if (paramFile2 != null) {
          paramFile2.close();
        }
      }
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      File localFile1 = new File(ManageData.this.getDataDir(ManageData.this.getPackageName()) + "/databases/" + "data.db");
      paramVarArgs = new File(ManageData.this.getDataDir(ManageData.this.getPackageName()), ManageData.this.getResources().getString(Constants.PREFSNAME));
      File localFile2 = new File(Environment.getExternalStorageDirectory(), ManageData.this.getResources().getString(Constants.DATAFILE));
      if (!localFile2.exists()) {
        ManageData.this.noData = true;
      }
      localFile2 = new File(localFile2, localFile1.getName());
      File localFile3 = new File(new File(Environment.getExternalStorageDirectory(), ManageData.this.getResources().getString(Constants.PREFSFILE)), paramVarArgs.getName());
      try
      {
        copyFile(localFile2, localFile1);
        copyFile(localFile3, paramVarArgs);
        try
        {
          i = Integer.parseInt(Build.VERSION.SDK);
          if (i >= 9)
          {
            UtilitiesAPI9.setReadable(localFile1);
            UtilitiesAPI9.setReadable(paramVarArgs);
          }
          return Boolean.valueOf(true);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            int i = 3;
          }
        }
        return Boolean.valueOf(false);
      }
      catch (IOException localIOException)
      {
        Log.e("mylog", localIOException.getMessage(), localIOException);
        this.message = (localIOException.toString() + paramVarArgs.getName().toString());
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (this.dialog.isShowing()) {
        this.dialog.dismiss();
      }
      if (paramBoolean.booleanValue())
      {
        paramBoolean = (TextView)ManageData.this.findViewById(R.id.txtSd);
        paramBoolean.setVisibility(0);
        paramBoolean.setText("Your saved data has been imported.");
        Utilities.showToastText(ManageData.this, "Import successful!");
        return;
      }
      paramBoolean = (TextView)ManageData.this.findViewById(R.id.txtSd);
      paramBoolean.setVisibility(0);
      if (ManageData.this.noData) {
        paramBoolean.setText("Your data cannot be retrieved because no data was saved.\n\nCheck common problems:\n1) Are you using same SD card you saved to?\n2) Did you have the settings set to automatically SAVE TO STORAGE?\n3) Did you create any data to save?");
      }
      for (;;)
      {
        Utilities.showToastText(ManageData.this, "Import failed.");
        return;
        paramBoolean.setText("Your data cannot be retrieved.");
      }
    }
    
    protected void onPreExecute()
    {
      ((TextView)ManageData.this.findViewById(R.id.txtInfo)).setVisibility(8);
      this.dialog.setMessage("Importing database...");
      this.dialog.show();
    }
  }
}
