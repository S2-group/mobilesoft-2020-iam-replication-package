package com.hideitpro.misc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hideitpro.util.ActivityLogout;
import com.hideitpro.util.PrefManager;
import com.smartanuj.a.a.a;
import com.smartanuj.a.a.b.3;
import com.smartanuj.a.a.b.4;
import com.smartanuj.a.a.c;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindMissingFiles
  extends ActivityLogout
{
  private static final String _vault = "ProgramData/Android/Language/.fr/";
  static ArrayList<String> toSkip;
  StringBuilder builder;
  private int currentMediaCount;
  private File currentVaultFile;
  private FilenameFilter filenameFilter = new a.b.3();
  boolean logFileWritten = false;
  private FileFilter onlyDirectoryFilter = new a.b.4();
  private ProgressBar pBar;
  private ArrayList<String> pathsToScan = new ArrayList();
  private PrefManager prefs;
  StringBuilder scannedPathsBuilder;
  private TextView scanningPathsView;
  private Button sendTextLog;
  private String signatureFileString = "signature_" + Math.random() * 1000000.0D;
  private Button startScan;
  private boolean stopProcess = false;
  private TextView textLog;
  
  static
  {
    ArrayList localArrayList = new ArrayList();
    toSkip = localArrayList;
    localArrayList.add("/sys");
    toSkip.add("/proc");
    toSkip.add("/data");
    toSkip.add("/system");
  }
  
  public FindMissingFiles() {}
  
  private int countMediaFiles(String paramString)
  {
    return getFilesInDirCount(paramString, "Pictures") + getFilesInDirCount(paramString, "Videos") + getFilesInDirCount(paramString, "Audio");
  }
  
  private long freeSpaceOnSDcard(String paramString)
  {
    paramString = new StatFs(paramString);
    long l = paramString.getAvailableBlocks();
    return paramString.getBlockSize() * l;
  }
  
  private int getFilesInDirCount(String paramString1, String paramString2)
  {
    int k = 0;
    int i = 0;
    paramString1 = new File(paramString1, paramString2).listFiles(this.onlyDirectoryFilter);
    if (paramString1 != null)
    {
      int m = paramString1.length;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= m) {
          break;
        }
        paramString2 = paramString1[j].list(this.filenameFilter);
        k = i;
        if (paramString2 != null) {
          k = i + paramString2.length;
        }
        j += 1;
        i = k;
      }
    }
    return k;
  }
  
  private File getRoot()
  {
    File localFile;
    for (Object localObject = Environment.getExternalStorageDirectory();; localObject = localFile)
    {
      localFile = ((File)localObject).getParentFile();
      if ((localFile == null) || (!localFile.canRead())) {
        break;
      }
    }
    return localObject;
  }
  
  private boolean isSameVault(File paramFile)
  {
    return (this.currentVaultFile.getAbsolutePath().equals(paramFile.getAbsolutePath())) || (new File(paramFile, this.signatureFileString).exists());
  }
  
  private void mergeMediaFolders(File paramFile1, File paramFile2)
  {
    paramFile1 = paramFile1.listFiles(this.onlyDirectoryFilter);
    if (paramFile1 != null)
    {
      int j = paramFile1.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramFile1[i];
        writeToLog(new String[] { "  - " + localFile.getName() + "\n" });
        a.c.c(localFile, paramFile2);
        i += 1;
      }
    }
  }
  
  private boolean scanPath(String paramString)
  {
    if (this.stopProcess) {
      return true;
    }
    File localFile = new File(paramString);
    Object localObject = new File(paramString, "ProgramData/Android/Language/.fr/");
    int j;
    int i;
    if ((((File)localObject).exists()) && (!isSameVault((File)localObject)) && (countMediaFiles(((File)localObject).getAbsolutePath()) > 0))
    {
      writeToLog(new String[] { "--------------------------\n\n" });
      writeToLog(new String[] { "Found vault at\n", paramString, "\n\n" });
      if (this.currentMediaCount == 0)
      {
        writeToLog(new String[] { "Changing vault location..." });
        if (this.prefs.setVaultLoc(getApplicationContext(), ((File)localObject).getAbsolutePath())) {
          writeToLog(new String[] { "[ok]\n" });
        }
      }
      for (;;)
      {
        writeToLog(new String[] { "------------------------" });
        return true;
        writeToLog(new String[] { "[failed]\n" });
        continue;
        writeToLog(new String[] { "- Size of found vault : " });
        long l1 = a.a.a((File)localObject);
        writeToLog(new String[] { a.a.a(l1), "\n" });
        long l2 = freeSpaceOnSDcard(this.currentVaultFile.getAbsolutePath());
        writeToLog(new String[] { "- Free space on device : ", a.a.a(l2), "\n" });
        if (l2 > l1)
        {
          writeToLog(new String[] { "Moving photos\n" });
          mergeMediaFolders(new File((File)localObject, "Pictures"), new File(this.currentVaultFile, "Pictures"));
          writeToLog(new String[] { "Moving videos\n" });
          mergeMediaFolders(new File((File)localObject, "Videos"), new File(this.currentVaultFile, "Videos"));
          writeToLog(new String[] { "Moving music\n" });
          mergeMediaFolders(new File((File)localObject, "Audio"), new File(this.currentVaultFile, "Audio"));
          try
          {
            paramString = new File((File)localObject, "Encrypted");
            writeToLog(new String[] { "Moving encrypted files\n" });
            paramString = paramString.listFiles();
            if (paramString != null)
            {
              j = paramString.length;
              i = 0;
              while (i < j)
              {
                a.c.a(paramString[i], new File(this.currentVaultFile, "Encrypted"));
                i += 1;
              }
            }
          }
          catch (Exception paramString)
          {
            paramString.printStackTrace();
            writeToLog(new String[] { "Success\n" });
          }
        }
        else
        {
          writeToLog(new String[] { "#2 Error need : ", a.a.a(l1 - l2), " more space" });
        }
      }
    }
    paramString = localFile.listFiles(this.onlyDirectoryFilter);
    if (paramString != null)
    {
      j = paramString.length;
      i = 0;
      while (i < j)
      {
        localFile = paramString[i];
        localObject = localFile.getCanonicalPath();
        if ((localFile.canRead()) && (!toSkip.contains(localObject)) && (!this.pathsToScan.contains(localObject))) {
          this.pathsToScan.add(localObject);
        }
        i += 1;
      }
    }
    return false;
  }
  
  private void writeScannedPaths(final String paramString)
  {
    this.scannedPathsBuilder.append(paramString).append("\n");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        FindMissingFiles.this.scanningPathsView.setText(paramString);
      }
    });
  }
  
  private void writeToLog(String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      this.builder.append(str);
      i += 1;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        FindMissingFiles.this.textLog.setText(FindMissingFiles.this.builder.toString());
      }
    });
  }
  
  String getDebugString()
  {
    localStringBuilder = new StringBuilder("\nGetting phone specs\n");
    localStringBuilder.append("Phone details:\n");
    localStringBuilder.append("Manufacturer - ").append(Build.MANUFACTURER).append("\n");
    localStringBuilder.append("Brand - ").append(Build.BRAND).append("\n");
    localStringBuilder.append("Device - ").append(Build.DEVICE).append("\n");
    localStringBuilder.append("Model - ").append(Build.MODEL).append("\n");
    localStringBuilder.append("Android - ").append(Build.VERSION.CODENAME).append(" (").append(Build.VERSION.SDK_INT).append(")\n");
    localStringBuilder.append("Board - ").append(Build.BOARD).append("\n");
    localStringBuilder.append("\nInstalled apps:\n");
    try
    {
      Object localObject = getPackageManager().getInstalledPackages(128);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          localStringBuilder.append(((PackageInfo)((Iterator)localObject).next()).packageName);
          localStringBuilder.append("\n");
        }
      }
      return localStringBuilder.toString();
    }
    catch (Exception localException) {}
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle("Find missing files");
    getSupportActionBar().a(true);
    setContentView(2131427501);
    this.prefs = new PrefManager(this);
    this.textLog = ((TextView)findViewById(2131296664));
    this.sendTextLog = ((Button)findViewById(2131296326));
    this.scanningPathsView = ((TextView)findViewById(2131296665));
    this.scanningPathsView.setText("Tap above to start the scan");
    this.pBar = ((ProgressBar)findViewById(2131296571));
    this.pBar.setVisibility(8);
    this.sendTextLog.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new Thread()
        {
          public void run()
          {
            final File localFile = new File(Environment.getExternalStorageDirectory(), "Android/process_log.txt");
            try
            {
              localFile.createNewFile();
              com.smartanuj.a.a.a(FindMissingFiles.this.builder.toString(), localFile, false);
              com.smartanuj.a.a.a(FindMissingFiles.this.scannedPathsBuilder.toString(), localFile, true);
              FindMissingFiles.this.logFileWritten = true;
              FindMissingFiles.this.runOnUiThread(new Runnable()
              {
                public void run()
                {
                  Intent localIntent = FindMissingFiles.this.prefs.getMediaRecoveryEmailIntent();
                  if (FindMissingFiles.this.logFileWritten) {
                    localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(localFile));
                  }
                  for (;;)
                  {
                    localIntent.setFlags(268435456);
                    FindMissingFiles.this.startActivity(Intent.createChooser(localIntent, FindMissingFiles.this.getString(2131689608)));
                    return;
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FindMissingFiles.this.builder.toString());
                    localStringBuilder.append("\n\n------------------\n\n");
                    localStringBuilder.append(FindMissingFiles.this.scannedPathsBuilder.toString());
                    localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
                  }
                }
              });
              return;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                FindMissingFiles.this.logFileWritten = false;
                localException.printStackTrace();
              }
            }
          }
        }.start();
      }
    });
    this.sendTextLog.setVisibility(8);
    this.startScan = ((Button)findViewById(2131296329));
    this.startScan.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new FindMissingFiles.ScanTask(FindMissingFiles.this, null).execute(new Void[0]);
      }
    });
  }
  
  public void onStop()
  {
    super.onStop();
    this.stopProcess = true;
    try
    {
      if (this.currentVaultFile != null) {
        new File(this.currentVaultFile, this.signatureFileString).delete();
      }
      Log.i("Anuj", "stop process");
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private class ScanTask
    extends AsyncTask<Void, String, Boolean>
  {
    private ScanTask() {}
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      FindMissingFiles.this.writeToLog(new String[] { "Starting scan..." });
      FindMissingFiles.access$302(FindMissingFiles.this, FindMissingFiles.this.countMediaFiles(FindMissingFiles.this.currentVaultFile.getAbsolutePath()));
      FindMissingFiles.this.writeScannedPaths(String.valueOf(FindMissingFiles.this.currentMediaCount));
      FindMissingFiles.this.writeToLog(new String[] { "[ok]\n" });
      FindMissingFiles.this.writeToLog(new String[] { FindMissingFiles.this.getDebugString() });
      FindMissingFiles.this.writeToLog(new String[] { "\n\n" });
      FindMissingFiles.this.writeToLog(new String[] { "Current:", FindMissingFiles.this.currentVaultFile.getAbsolutePath(), "\n" });
      try
      {
        FindMissingFiles.this.pathsToScan.add(FindMissingFiles.this.getRoot().getCanonicalPath());
        int i = 0;
        while (i < FindMissingFiles.this.pathsToScan.size())
        {
          paramVarArgs = (String)FindMissingFiles.this.pathsToScan.get(i);
          FindMissingFiles.this.writeScannedPaths(paramVarArgs);
          if (FindMissingFiles.this.scanPath(paramVarArgs)) {
            return Boolean.valueOf(true);
          }
          i += 1;
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        FindMissingFiles.this.writeToLog(new String[] { "#1 Error\n", Log.getStackTraceString(paramVarArgs) });
        paramVarArgs.printStackTrace();
        FindMissingFiles.this.writeToLog(new String[] { "No files missing\n" });
        FindMissingFiles.this.writeToLog(new String[] { "Scan finished\n\n" });
        FindMissingFiles.this.writeToLog(new String[] { "You may send log file to us for further analysis\n\n" });
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (!paramBoolean.booleanValue()) {
        FindMissingFiles.this.sendTextLog.setVisibility(0);
      }
      FindMissingFiles.this.scanningPathsView.setVisibility(4);
      FindMissingFiles.this.startScan.setEnabled(true);
      FindMissingFiles.this.pBar.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      FindMissingFiles.access$402(FindMissingFiles.this, new File(FindMissingFiles.this.prefs.getVaultLoc()));
      try
      {
        new File(FindMissingFiles.this.currentVaultFile, FindMissingFiles.this.signatureFileString).createNewFile();
        FindMissingFiles.this.startScan.setEnabled(false);
        FindMissingFiles.this.builder = new StringBuilder();
        FindMissingFiles.this.scannedPathsBuilder = new StringBuilder();
        FindMissingFiles.this.scanningPathsView.setVisibility(0);
        FindMissingFiles.this.scanningPathsView.setText("");
        FindMissingFiles.this.pBar.setVisibility(0);
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException.printStackTrace();
        }
      }
    }
  }
}
