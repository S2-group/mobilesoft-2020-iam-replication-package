package com.simpleapp.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.print.PrintManager;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.tools.FileOperator;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.MyApp;
import com.appxy.tools.SDCardScanner;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
import com.example.localalbum.common.LocalImageHelper;
import com.flurry.android.FlurryAgent;
import com.itextpdf.text.pdf.PdfWriter;
import com.simpelapp.entity.MainListDao;
import com.simpelapp.entity.Photo_info;
import com.simpelapp.entity.Photo_item;
import com.simpleapp.adpter.CopyAdapter;
import com.simpleapp.adpter.GridAdapter;
import com.simpleapp.adpter.ListAdapter;
import com.simpleapp.adpter.MoreListAdapter;
import com.simpleapp.adpter.MyPrintDocumentAdapter;
import com.simpleapp.adsUtils.AdsUtils;
import com.simpleapp.db.MyDbHelper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;

public class Activity_Main
  extends BaseActivity
{
  private static final String PACKAGE_URL_SCHEME = "package:";
  private static final int PERMISSION_REQUEST_CODE = 0;
  private static final int RC_REQUEST = 10001;
  public static Activity_Main activity_Main;
  static Comparator<Photo_info> comparator = new Comparator()
  {
    public int compare(Photo_info paramAnonymousPhoto_info1, Photo_info paramAnonymousPhoto_info2)
    {
      if ((paramAnonymousPhoto_info1.isFolder()) && (paramAnonymousPhoto_info2.isFolder())) {
        if (paramAnonymousPhoto_info2.getRealtime() <= paramAnonymousPhoto_info1.getRealtime()) {}
      }
      do
      {
        do
        {
          return 1;
          if (paramAnonymousPhoto_info2.getRealtime() < paramAnonymousPhoto_info1.getRealtime()) {
            return -1;
          }
          return (int)(paramAnonymousPhoto_info2.getRealtime() - paramAnonymousPhoto_info1.getRealtime());
          if ((paramAnonymousPhoto_info1.isFolder()) || (paramAnonymousPhoto_info2.isFolder())) {
            break;
          }
        } while (paramAnonymousPhoto_info2.getRealtime() > paramAnonymousPhoto_info1.getRealtime());
        if (paramAnonymousPhoto_info2.getRealtime() < paramAnonymousPhoto_info1.getRealtime()) {
          return -1;
        }
        return (int)(paramAnonymousPhoto_info2.getRealtime() - paramAnonymousPhoto_info1.getRealtime());
      } while (!paramAnonymousPhoto_info1.isFolder());
      return -1;
    }
  };
  static Comparator<Photo_info> comparator2 = new Comparator()
  {
    @SuppressLint({"DefaultLocale"})
    public int compare(Photo_info paramAnonymousPhoto_info1, Photo_info paramAnonymousPhoto_info2)
    {
      if ((paramAnonymousPhoto_info1.isFolder()) && (paramAnonymousPhoto_info2.isFolder()))
      {
        if ((paramAnonymousPhoto_info1.getName().matches("New Folder\\(\\d{1,5}\\)")) && (paramAnonymousPhoto_info2.getName().matches("New Folder\\(\\d{1,5}\\)"))) {
          return Integer.parseInt(paramAnonymousPhoto_info1.getName().substring(paramAnonymousPhoto_info1.getName().length() - 2, paramAnonymousPhoto_info1.getName().length() - 1)) - Integer.parseInt(paramAnonymousPhoto_info2.getName().substring(paramAnonymousPhoto_info2.getName().length() - 2, paramAnonymousPhoto_info2.getName().length() - 1));
        }
        return paramAnonymousPhoto_info1.getName().toLowerCase().compareTo(paramAnonymousPhoto_info2.getName().toLowerCase());
      }
      if ((!paramAnonymousPhoto_info1.isFolder()) && (!paramAnonymousPhoto_info2.isFolder()))
      {
        if ((paramAnonymousPhoto_info1.getName().matches("New Document\\(\\d{1,5}\\)")) && (paramAnonymousPhoto_info2.getName().matches("New Document\\(\\d{1,5}\\)"))) {
          return Integer.parseInt(paramAnonymousPhoto_info1.getName().substring(13, paramAnonymousPhoto_info1.getName().length() - 1)) - Integer.parseInt(paramAnonymousPhoto_info2.getName().substring(13, paramAnonymousPhoto_info2.getName().length() - 1));
        }
        return paramAnonymousPhoto_info1.getName().toLowerCase().compareTo(paramAnonymousPhoto_info2.getName().toLowerCase());
      }
      if (paramAnonymousPhoto_info1.isFolder()) {
        return -1;
      }
      return 1;
    }
  };
  static Comparator<String> comparator3 = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.substring(paramAnonymousString1.length() - 7, paramAnonymousString1.length() - 4).compareTo(paramAnonymousString2.substring(paramAnonymousString2.length() - 7, paramAnonymousString2.length() - 4));
    }
  };
  static Comparator<File> comparator4 = new Comparator()
  {
    public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      return (int)(paramAnonymousFile2.lastModified() - paramAnonymousFile1.lastModified());
    }
  };
  static Comparator<File> comparator5 = new Comparator()
  {
    @SuppressLint({"DefaultLocale"})
    public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      if ((paramAnonymousFile1.getName().matches("New Document\\(\\d{1,5}\\)")) && (paramAnonymousFile2.getName().matches("New Document\\(\\d{1,5}\\)"))) {
        return Integer.parseInt(paramAnonymousFile1.getName().substring(13, paramAnonymousFile1.getName().length() - 1)) - Integer.parseInt(paramAnonymousFile2.getName().substring(13, paramAnonymousFile2.getName().length() - 1));
      }
      return paramAnonymousFile1.getName().toLowerCase().compareTo(paramAnonymousFile2.getName().toLowerCase());
    }
  };
  public static List<Photo_info> idlist;
  public static int sort_type;
  private String[] PERMISSION = { "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE" };
  private ImageView back;
  private ImageView camera;
  private String compressJpeg_Path = Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Jpeg/";
  private Context context;
  private SQLiteDatabase db;
  private SharedPreferences.Editor editor;
  private List<File> export_file;
  private int export_size = 0;
  private AlertDialog feedbackdialog;
  private GridView grid;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private PrintManager printManager;
    
    @SuppressLint({"NewApi", "DefaultLocale", "InflateParams"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131165605)).setMessage(Activity_Main.this.getResources().getString(2131165298)).setPositiveButton(Activity_Main.this.getResources().getString(2131165458), null).create().show();
        continue;
        if (Activity_Main.this.searchandclick) {
          Activity_Main.this.unselected2();
        }
        for (;;)
        {
          Activity_Main.this.list_folders();
          break;
          Activity_Main.this.unselected();
        }
        Activity_Main.idlist.clear();
        if (Activity_Main.this.list_type == 0) {
          if (Activity_Main.this.madapter != null) {
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
        }
        for (;;)
        {
          Activity_Main.this.mian_progreebar.setVisibility(8);
          if (!Activity_Main.this.searchandclick) {
            break label277;
          }
          Activity_Main.this.unselected2();
          break;
          if (Activity_Main.this.madapter2 != null) {
            Activity_Main.this.madapter2.notifyDataSetChanged();
          }
        }
        label277:
        Activity_Main.this.unselected();
        continue;
        if (Activity_Main.this.list_type == 0) {
          Activity_Main.this.list_by_grid();
        }
        for (;;)
        {
          Activity_Main.this.mian_progreebar.setVisibility(8);
          break;
          Activity_Main.this.list_by_list();
        }
        Activity_Main.this.mian_progreebar.setVisibility(8);
        Activity_Main.access$2802(Activity_Main.this, null);
        Activity_Main.access$2902(Activity_Main.this, new ArrayList());
        Activity_Main.this.export_file.clear();
        Object localObject3 = new ArrayList();
        Object localObject1;
        int k;
        int j;
        Object localObject5;
        if (Activity_Main.this.export_size == 0)
        {
          localObject4 = new Activity_Main.MyFilter2(Activity_Main.this);
          i = 0;
          if (i < Activity_Main.idlist.size())
          {
            if (((Photo_info)Activity_Main.idlist.get(i)).isFolder())
            {
              localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles();
              k = localObject1.length;
              j = 0;
              while (j < k)
              {
                localObject5 = localObject1[j].listFiles((FilenameFilter)localObject4);
                if (localObject5.length > 0) {
                  Activity_Main.this.export_file.add(localObject5[0]);
                }
                j += 1;
              }
            }
            if (Activity_Main.this.isSearch) {}
            for (localObject1 = new File(((Photo_info)Activity_Main.idlist.get(i)).getRotepath() + ((Photo_info)Activity_Main.idlist.get(i)).getName());; localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName()))
            {
              localObject1 = ((File)localObject1).listFiles((FilenameFilter)localObject4);
              if (localObject1.length > 0) {
                Activity_Main.this.export_file.add(localObject1[0]);
              }
              i += 1;
              break;
            }
          }
        }
        else
        {
          localObject1 = new Activity_Main.MyFilter(Activity_Main.this, ".pdf");
          localObject4 = new File(Activity_Main.this.root_Path5).listFiles((FilenameFilter)localObject1);
          i = 0;
          while (i < localObject4.length)
          {
            Activity_Main.this.export_file.add(localObject4[i]);
            i += 1;
          }
          localObject4 = new File(Activity_Main.this.root_Path6).listFiles();
          k = localObject4.length;
          i = 0;
          while (i < k)
          {
            localObject5 = localObject4[i].listFiles((FilenameFilter)localObject1);
            j = 0;
            while (j < localObject5.length)
            {
              Activity_Main.this.export_file.add(localObject5[j]);
              j += 1;
            }
            i += 1;
          }
        }
        int i = 0;
        while (i < Activity_Main.this.export_file.size())
        {
          ((ArrayList)localObject3).add(Uri.fromFile((File)Activity_Main.this.export_file.get(i)));
          i += 1;
        }
        Activity_Main.this.getPackageManager().getInstalledApplications(0).size();
        Object localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
        ((Intent)localObject4).setType("application/pdf");
        label1080:
        Object localObject6;
        label1135:
        label1227:
        label1318:
        label1333:
        label1384:
        label1398:
        Object localObject2;
        switch (Activity_Main.this.select_pdf_posiotion)
        {
        case 4: 
        default: 
          break;
        case 0: 
          if ((Activity_Main.idlist.size() > 1) || (Activity_Main.this.hasFolder))
          {
            localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject1).setType("application/pdf");
            localObject4 = new ArrayList();
            localObject1 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
            if (((List)localObject1).isEmpty()) {
              continue;
            }
            localObject5 = ((List)localObject1).iterator();
            if (!((Iterator)localObject5).hasNext()) {
              break label1398;
            }
            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
            if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasFolder)) {
              break label1318;
            }
            localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject1).setType("application/pdf");
            if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasFolder)) {
              break label1333;
            }
            ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName() + " and other " + (Activity_Main.idlist.size() - 1) + " documents");
            if (((ArrayList)localObject3).size() != 1) {
              break label1384;
            }
            ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
          }
          for (;;)
          {
            ((Intent)localObject1).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
            ((Intent)localObject1).setClassName(((ResolveInfo)localObject6).activityInfo.packageName, ((ResolveInfo)localObject6).activityInfo.name);
            ((List)localObject4).add(localObject1);
            break label1080;
            localObject1 = new Intent("android.intent.action.SEND");
            break;
            localObject1 = new Intent("android.intent.action.SEND");
            break label1135;
            if (Activity_Main.idlist.size() == 1)
            {
              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName());
              break label1227;
            }
            ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
            break label1227;
            ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
          }
          if (((List)localObject4).size() > 0)
          {
            localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share PDF file");
            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
            Activity_Main.this.startActivity((Intent)localObject1);
          }
          else
          {
            Toast.makeText(Activity_Main.activity_Main, "Error: Cannot open or share created PDF report.", 0).show();
          }
          break;
        case 1: 
          Activity_Main.this.mapp.setPdf_path(((Uri)((ArrayList)localObject3).get(0)).getPath());
          Activity_Main.this.mapp.setPdf_Name(((File)Activity_Main.this.export_file.get(0)).getName().replace(".pdf", ""));
          Activity_Main.this.mapp.setPdf_pages(Util.getPdfPages(((Uri)((ArrayList)localObject3).get(0)).getPath()));
          try
          {
            this.printManager = ((PrintManager)Activity_Main.activity_Main.getSystemService("print"));
            this.printManager.print(Activity_Main.this.mapp.getPdf_Name(), new MyPrintDocumentAdapter(Activity_Main.this.mapp), null);
          }
          catch (Exception localException)
          {
            Toast.makeText(Activity_Main.activity_Main, "printing error.", 0).show();
          }
          break;
        case 2: 
          localObject2 = new ArrayList();
          localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject4, 0);
          if (!((List)localObject4).isEmpty())
          {
            localObject4 = ((List)localObject4).iterator();
            if (((Iterator)localObject4).hasNext())
            {
              localObject5 = (ResolveInfo)((Iterator)localObject4).next();
              localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
              ((Intent)localObject6).setType("application/pdf");
              if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_to", ""))) {
                ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("emailsetting_to", "") });
              }
              if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_subject", ""))) {
                ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_Main.this.preferences.getString("emailsetting_subject", ""));
              }
              for (;;)
              {
                if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_body", ""))) {
                  ((Intent)localObject6).putExtra("android.intent.extra.TEXT", Activity_Main.this.preferences.getString("emailsetting_body", ""));
                }
                if ((!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                  break;
                }
                ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                ((List)localObject2).add(localObject6);
                break;
                if (Activity_Main.idlist.size() > 1) {
                  ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName() + " and other " + (Activity_Main.idlist.size() - 1) + " documents");
                } else if (Activity_Main.idlist.size() == 1) {
                  ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName());
                } else {
                  ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
                }
              }
            }
            if (((List)localObject2).size() > 0)
            {
              localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Export");
              ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
              Activity_Main.this.startActivityForResult((Intent)localObject3, 3);
            }
            else
            {
              Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165275), 0).show();
            }
          }
          break;
        case 3: 
          localObject2 = new Intent("android.intent.action.VIEW");
          if (((ArrayList)localObject3).size() > 0) {
            ((Intent)localObject2).setDataAndType((Uri)((ArrayList)localObject3).get(0), "application/pdf");
          }
          ((Intent)localObject2).setFlags(67108864);
          Activity_Main.this.startActivityForResult(Intent.createChooser((Intent)localObject2, "Export"), 3);
          break;
        case 5: 
          if (!Utils.findAndGotoApp1(Activity_Main.activity_Main, "com.studio.topfax", (ArrayList)localObject3))
          {
            Utils.showGooglePlaysimplefax(Activity_Main.activity_Main);
            continue;
            Activity_Main.this.mian_progreebar.setVisibility(8);
            Activity_Main.access$2802(Activity_Main.this, null);
            Activity_Main.this.mapp.setUpdate(false);
            localObject3 = new ArrayList();
            if (Activity_Main.this.export_size == 0)
            {
              i = 0;
              while (i < Activity_Main.idlist.size())
              {
                if (((Photo_info)Activity_Main.idlist.get(i)).isFolder())
                {
                  localObject2 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles();
                  int m = localObject2.length;
                  j = 0;
                  while (j < m)
                  {
                    localObject4 = localObject2[j].listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
                    if ((localObject4 != null) && (localObject4.length > 0))
                    {
                      k = 0;
                      while (k < localObject4.length)
                      {
                        ((ArrayList)localObject3).add(Uri.fromFile(localObject4[k]));
                        k += 1;
                      }
                    }
                    j += 1;
                  }
                }
                localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
                if ((localObject2 != null) && (localObject2.length > 0))
                {
                  j = 0;
                  while (j < localObject2.length)
                  {
                    ((ArrayList)localObject3).add(Uri.fromFile(localObject2[j]));
                    j += 1;
                  }
                }
                i += 1;
              }
            }
            localObject2 = new File(Activity_Main.this.compressJpeg_Path).listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
            if ((localObject2 != null) && (localObject2.length > 0))
            {
              i = 0;
              while (i < localObject2.length)
              {
                ((ArrayList)localObject3).add(Uri.fromFile(localObject2[i]));
                i += 1;
              }
            }
            Activity_Main.this.getPackageManager().getInstalledApplications(0).size();
            localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "TopScanner");
            ((Intent)localObject4).setType("image/jpeg");
            switch (Activity_Main.this.select_jpeg_posiotion)
            {
            case 1: 
            default: 
              break;
            case 0: 
              if (((ArrayList)localObject3).size() > 1)
              {
                localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                ((Intent)localObject2).setType("image/jpeg");
                localObject4 = new ArrayList();
                localObject2 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                if (((List)localObject2).isEmpty()) {
                  continue;
                }
                localObject5 = ((List)localObject2).iterator();
                if (!((Iterator)localObject5).hasNext()) {
                  break label3210;
                }
                localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                if (((ArrayList)localObject3).size() <= 1) {
                  break label3130;
                }
                localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                ((Intent)localObject2).setType("image/jpeg");
                if (Activity_Main.idlist.size() <= 1) {
                  break label3145;
                }
                ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName() + " and other " + (Activity_Main.idlist.size() - 1) + " documents");
                if (((ArrayList)localObject3).size() != 1) {
                  break label3196;
                }
                ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
              }
              for (;;)
              {
                ((Intent)localObject2).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                ((Intent)localObject2).setClassName(((ResolveInfo)localObject6).activityInfo.packageName, ((ResolveInfo)localObject6).activityInfo.name);
                ((List)localObject4).add(localObject2);
                break label2915;
                localObject2 = new Intent("android.intent.action.SEND");
                break;
                localObject2 = new Intent("android.intent.action.SEND");
                break label2957;
                if (Activity_Main.idlist.size() == 1)
                {
                  ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName());
                  break label3039;
                }
                ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
                break label3039;
                ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
              }
              if (((List)localObject4).size() > 0)
              {
                localObject2 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share JPEG file");
                ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                Activity_Main.this.startActivity((Intent)localObject2);
              }
              else
              {
                Toast.makeText(Activity_Main.activity_Main, "Error: No resource images found.", 0).show();
              }
              break;
            case 2: 
              label2915:
              label2957:
              label3039:
              label3130:
              label3145:
              label3196:
              label3210:
              localObject2 = new ArrayList();
              localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject4, 0);
              if (!((List)localObject4).isEmpty())
              {
                localObject4 = ((List)localObject4).iterator();
                if (((Iterator)localObject4).hasNext())
                {
                  localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                  localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                  ((Intent)localObject6).setType("image/jpeg");
                  if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_to", ""))) {
                    ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("emailsetting_to", "") });
                  }
                  if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_subject", ""))) {
                    ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_Main.this.preferences.getString("emailsetting_subject", ""));
                  }
                  for (;;)
                  {
                    if (!"".equals(Activity_Main.this.preferences.getString("emailsetting_body", ""))) {
                      ((Intent)localObject6).putExtra("android.intent.extra.TEXT", Activity_Main.this.preferences.getString("emailsetting_body", ""));
                    }
                    if ((!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                      break;
                    }
                    ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                    ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                    ((List)localObject2).add(localObject6);
                    break;
                    if (Activity_Main.idlist.size() > 1) {
                      ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName() + " and other " + (Activity_Main.idlist.size() - 1) + " documents");
                    } else if (Activity_Main.idlist.size() == 1) {
                      ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName());
                    } else {
                      ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
                    }
                  }
                }
                if (((List)localObject2).size() > 0)
                {
                  localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Email");
                  ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                  Activity_Main.this.startActivityForResult((Intent)localObject3, 3);
                }
                else
                {
                  Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165275), 0).show();
                  continue;
                  Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131165767));
                }
              }
              break;
            }
          }
          break;
        }
      }
    }
  };
  private boolean hasFolder = false;
  private IAPBuy iapBuy;
  private Integer[] imagedata;
  private ImageView imageview_rate1;
  private ImageView imageview_rate2;
  private ImageView imageview_rate3;
  private ImageView imageview_rate4;
  private ImageView imageview_rate5;
  private int inAndOutTimes;
  private LayoutInflater inflater;
  private String info;
  private int initNotnowTimes;
  private boolean isClickReminderMeLater;
  private boolean isSearch = false;
  private boolean isSelect = false;
  private boolean is_once_ads_chaye = true;
  private boolean islongclick = false;
  private boolean isrequestCheck = false;
  private int list_type;
  private LinearLayout listphotos;
  private MyApp mApp;
  private MyDbHelper mDbHelper;
  private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
  {
    public void onManagerConnected(int paramAnonymousInt)
    {
      super.onManagerConnected(paramAnonymousInt);
    }
  };
  private ProgressDialog mProgressDialog;
  private Thread mThread;
  private GridAdapter madapter;
  private ListAdapter madapter2;
  private TextView main_appname_textview;
  private LinearLayout main_deletelayout;
  private LinearLayout main_emaillayout;
  private ImageView main_imagesearch;
  private ImageView main_importfromgallery;
  private LinearLayout main_morelayout;
  private ImageView main_newfolder;
  private LinearLayout main_onlongclick_layout;
  private RelativeLayout main_relativelayout_ads;
  private RadioButton main_selectall;
  private TextView main_selecttextview;
  private LinearLayout main_sharelayout;
  private ImageView main_viewsortby;
  private MyApplication mapp;
  private ProgressBar mian_progreebar;
  private ArrayList<Photo_item> mlist = null;
  public List<Photo_info> mlist2;
  private ImageView more;
  private ArrayList<MainListDao> moreList = new ArrayList();
  View.OnClickListener myOnClickListener = new View.OnClickListener()
  {
    @SuppressLint({"InflateParams"})
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131624158: 
      case 2131624159: 
      case 2131624162: 
      case 2131624163: 
      case 2131624164: 
      case 2131624165: 
      default: 
        return;
      case 2131624168: 
        Activity_Main.this.showPdf_or_imagejpeg_SelectDailog(2);
        return;
      case 2131624160: 
        Activity_Main.this.search_text.setText("");
        return;
      case 2131624170: 
        Activity_Main.this.initPopuptWindow2(2);
        if (Activity_Main.this.mapp.isPad())
        {
          Activity_Main.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
          return;
        }
        if (Activity_Main.this.main_relativelayout_ads.getVisibility() == 0)
        {
          Activity_Main.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, Activity_Main.this.dip2px(56.0F));
          return;
        }
        Activity_Main.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, 0);
        return;
      case 2131624156: 
        Activity_Main.this.initPopuptWindow2(1);
        Activity_Main.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
        return;
      case 2131624167: 
        Activity_Main.this.showPdf_or_imagejpeg_SelectDailog(1);
        return;
      case 2131624166: 
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (Activity_Main.this.isrequestCheck)
          {
            Activity_Main.this.takePicture(true);
            return;
          }
          Activity_Main.this.requstPermisstion();
          return;
        }
        Activity_Main.this.takePicture(true);
        return;
      case 2131624157: 
        Activity_Main.this.clear();
        return;
      case 2131624169: 
        paramAnonymousView = Activity_Main.this.getResources().getString(2131165238);
        new AlertDialog.Builder(Activity_Main.this.context).setMessage(paramAnonymousView).setPositiveButton(Activity_Main.this.getResources().getString(2131165458), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            new Thread(new Runnable()
            {
              public void run()
              {
                int i = 0;
                while (i < Activity_Main.idlist.size())
                {
                  Object localObject2;
                  int j;
                  if (((Photo_info)Activity_Main.idlist.get(i)).isFolder())
                  {
                    localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.idlist.get(i)).getName());
                    if (!((File)localObject1).exists()) {
                      break label433;
                    }
                    if (((File)localObject1).isDirectory())
                    {
                      localObject2 = ((File)localObject1).listFiles();
                      if (localObject2 != null) {
                        j = 0;
                      }
                    }
                  }
                  else
                  {
                    for (;;)
                    {
                      if (j >= localObject2.length) {
                        break label314;
                      }
                      if (localObject2[j].isDirectory())
                      {
                        File[] arrayOfFile = localObject2[j].listFiles();
                        int m = arrayOfFile.length;
                        int k = 0;
                        for (;;)
                        {
                          if (k < m)
                          {
                            arrayOfFile[k].delete();
                            k += 1;
                            continue;
                            if (Activity_Main.this.isSearch)
                            {
                              localObject1 = new File(((Photo_info)Activity_Main.idlist.get(i)).getRotepath() + ((Photo_info)Activity_Main.idlist.get(i)).getName());
                              break;
                            }
                            localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName());
                            break;
                          }
                        }
                      }
                      localObject2[j].delete();
                      j += 1;
                    }
                  }
                  label314:
                  ((File)localObject1).delete();
                  Activity_Main.this.mlist2.remove(Activity_Main.idlist.get(i));
                  localObject1 = ((Photo_info)Activity_Main.idlist.get(i)).getImage_name().iterator();
                  while (((Iterator)localObject1).hasNext())
                  {
                    localObject2 = (String)((Iterator)localObject1).next();
                    Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localObject2);
                  }
                  label433:
                  i += 1;
                }
                Object localObject1 = new Message();
                ((Message)localObject1).what = 0;
                Activity_Main.this.handler.sendMessage((Message)localObject1);
              }
            }).start();
          }
        }).setNegativeButton(Activity_Main.this.getResources().getString(2131165274), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).create().show();
        return;
      }
      int i;
      if (Activity_Main.this.main_selectall.getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165561)))
      {
        Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165291));
        Activity_Main.idlist.clear();
        i = 0;
        while (i < Activity_Main.this.mlist2.size())
        {
          ((Photo_info)Activity_Main.this.mlist2.get(i)).setCheck(true);
          paramAnonymousView = (Photo_info)Activity_Main.this.mlist2.get(i);
          Activity_Main.idlist.add(paramAnonymousView);
          i += 1;
        }
        if (Activity_Main.this.list_type == 0)
        {
          Activity_Main.this.madapter.isse = true;
          Activity_Main.this.madapter.notifyDataSetChanged();
          Activity_Main.this.selected();
        }
      }
      for (;;)
      {
        Activity_Main.this.changeView();
        return;
        Activity_Main.this.madapter2.isse = true;
        Activity_Main.this.madapter2.notifyDataSetChanged();
        break;
        if (Activity_Main.this.main_selectall.getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165291)))
        {
          Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165561));
          i = 0;
          while (i < Activity_Main.this.mlist2.size())
          {
            ((Photo_info)Activity_Main.this.mlist2.get(i)).setCheck(false);
            Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(i));
            i += 1;
          }
          if (Activity_Main.idlist.isEmpty())
          {
            if (Activity_Main.this.searchandclick)
            {
              Activity_Main.this.unselected2();
              label714:
              Activity_Main.access$2202(Activity_Main.this, false);
              if (Activity_Main.this.list_type != 0) {
                break label777;
              }
              Activity_Main.this.madapter.isse = false;
            }
            for (;;)
            {
              if (Activity_Main.this.list_type != 0) {
                break label836;
              }
              Activity_Main.this.madapter.notifyDataSetChanged();
              break;
              Activity_Main.this.unselected();
              break label714;
              label777:
              Activity_Main.this.madapter2.isse = false;
            }
          }
          if (Activity_Main.this.list_type == 0) {
            Activity_Main.this.madapter.isse = true;
          }
          for (;;)
          {
            Activity_Main.this.selected();
            break;
            Activity_Main.this.madapter2.isse = true;
          }
          label836:
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
      }
    }
  };
  private String[] newCountryIAP_137 = { "US" };
  private boolean newuser_isnotnow;
  private int pdf_OR_jpeg = 1;
  private List<HashMap<String, Object>> pdf_file_name;
  private PopupWindow popupWindow;
  private SharedPreferences preferences;
  private AlertDialog rateNewDialog;
  private AlertDialog rateNewDialog1;
  private String root_Path;
  private String root_Path2;
  private String root_Path3;
  private String root_Path4;
  private String root_Path5;
  private String root_Path6;
  private ImageView search_delete;
  private RelativeLayout search_layout;
  private EditText search_text;
  private boolean searchandclick = false;
  private int select_jpeg_posiotion = 0;
  private int select_pdf_posiotion = 0;
  private int selecter_docmentCount = 0;
  private TextView selecttext;
  private int times;
  private String[] viewsortStr;
  
  public Activity_Main() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void MultipleVersionslimit() {}
  
  private void TraverseImagesSize(File paramFile)
  {
    paramFile = paramFile.listFiles();
    if (paramFile != null)
    {
      int i = 0;
      while (i < paramFile.length)
      {
        if (paramFile[i].getName().matches("[0-9]{18}.jpg"))
        {
          Photo_item localPhoto_item = new Photo_item();
          localPhoto_item.setPath(paramFile[i].getPath());
          localPhoto_item.setShow(false);
          this.mlist.add(localPhoto_item);
        }
        i += 1;
      }
    }
  }
  
  private void clear()
  {
    if (this.list_type == 0)
    {
      if (this.madapter != null) {
        this.madapter.isse = false;
      }
      if (!this.searchandclick) {
        break label224;
      }
      unselected2();
    }
    for (;;)
    {
      this.more.setVisibility(0);
      this.main_appname_textview.setVisibility(0);
      this.main_imagesearch.setVisibility(0);
      if (this.mapp.isPad())
      {
        this.main_importfromgallery.setVisibility(0);
        this.main_newfolder.setVisibility(0);
        this.main_viewsortby.setVisibility(0);
      }
      this.selecttext.setVisibility(8);
      this.main_selecttextview.setVisibility(8);
      this.main_selectall.setText(activity_Main.getResources().getString(2131165561));
      this.main_selectall.setVisibility(8);
      this.back.setVisibility(8);
      this.search_text.setText("");
      this.search_layout.setVisibility(8);
      this.main_onlongclick_layout.setVisibility(8);
      list_folders();
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.search_text.getWindowToken(), 0);
      this.isSearch = false;
      return;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label224:
      unselected();
    }
  }
  
  private void clear2()
  {
    this.back.setVisibility(8);
    this.search_layout.setVisibility(8);
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.search_text.getWindowToken(), 0);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  private void createNewFolder(final String paramString1, String paramString2, boolean paramBoolean)
  {
    if (checkName(paramString1))
    {
      paramString1 = new File(this.root_Path4 + paramString1);
      paramString1.mkdirs();
      if (!paramBoolean) {
        break label144;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = Activity_Main.idlist.iterator();
          if (localIterator.hasNext())
          {
            Photo_info localPhoto_info = (Photo_info)localIterator.next();
            if (Activity_Main.this.isSearch) {}
            for (localObject = new File(localPhoto_info.getRotepath() + localPhoto_info.getName());; localObject = new File(Activity_Main.this.root_Path3 + localPhoto_info.getName()))
            {
              BitmapDrawable localBitmapDrawable = Activity_Main.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
              Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
              ((File)localObject).renameTo(new File(Activity_Main.this.root_Path4 + paramString1.getName() + "/" + localPhoto_info.getName()));
              Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path4 + paramString1.getName() + "/" + localPhoto_info.getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
              break;
            }
          }
          Object localObject = new Message();
          ((Message)localObject).what = 21;
          Activity_Main.this.handler.sendMessage((Message)localObject);
        }
      }).start();
    }
    label144:
    label278:
    do
    {
      for (;;)
      {
        return;
        Object localObject = new Timestamp(System.currentTimeMillis());
        localObject = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject);
        paramString2 = paramString2.replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject;
        saveNameToDb(paramString2, paramString1);
        paramString1 = paramString2;
        break;
        paramString2 = Utils.getDate(new Date(paramString1.lastModified()));
        localObject = new ArrayList();
        paramString1 = new Photo_info(paramString1.getName(), getShowName(paramString1.getName()), paramString2, paramString1.lastModified(), 0, (ArrayList)localObject, false, true);
        this.mlist2.add(paramString1);
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        try
        {
          if (sort_type == 0) {
            Collections.sort(this.mlist2, comparator);
          }
          for (;;)
          {
            if (this.list_type != 0) {
              break label278;
            }
            if (this.madapter == null) {
              break;
            }
            this.madapter.notifyDataSetChanged();
            return;
            Collections.sort(this.mlist2, comparator2);
          }
        }
        catch (Exception paramString1)
        {
          for (;;)
          {
            paramString1.printStackTrace();
          }
        }
      }
    } while (this.madapter2 == null);
    this.madapter2.notifyDataSetChanged();
  }
  
  private int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  private void faxMonth()
  {
    this.pdf_OR_jpeg = 1;
    this.select_pdf_posiotion = 5;
    shareDocment();
  }
  
  public static Activity_Main getActivity_main()
  {
    return activity_Main;
  }
  
  private String getRealPathFromURI(Uri paramUri)
  {
    paramUri = new CursorLoader(this.context, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
    int i = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    return paramUri.getString(i);
  }
  
  private long getfilesizeLength()
  {
    long l = 0L;
    this.mlist = new ArrayList();
    int i = 0;
    if (i < idlist.size())
    {
      if (((Photo_info)idlist.get(i)).isFolder())
      {
        localObject = new File(this.root_Path4 + ((Photo_info)idlist.get(i)).getName()).listFiles();
        if (localObject != null)
        {
          int j = 0;
          while (j < localObject.length)
          {
            TraverseImagesSize(new File(localObject[j].getPath()));
            j += 1;
          }
        }
      }
      else
      {
        if (!this.isSearch) {
          break label206;
        }
      }
      label206:
      for (Object localObject = new File(((Photo_info)idlist.get(i)).getRotepath() + ((Photo_info)idlist.get(i)).getName());; localObject = new File(this.root_Path3 + ((Photo_info)idlist.get(i)).getName()))
      {
        TraverseImagesSize((File)localObject);
        i += 1;
        break;
      }
    }
    i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    return l;
  }
  
  private boolean hasAllPermissionGranted(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == -1) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private void hideProgressDialog()
  {
    if ((this.mProgressDialog != null) && (this.mProgressDialog.isShowing())) {
      this.mProgressDialog.dismiss();
    }
  }
  
  private void init()
  {
    this.mapp.initImageLoader(this.context);
    LocalImageHelper.init(this.mapp);
  }
  
  @SuppressLint({"InflateParams"})
  private void initPopuptWindow2(int paramInt)
  {
    Object localObject1 = new ArrayList();
    if (this.mapp.isPad()) {
      if (paramInt == 1)
      {
        ((ArrayList)localObject1).clear();
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165568));
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165400));
      }
    }
    for (;;)
    {
      this.moreList.clear();
      paramInt = 0;
      while (paramInt < ((ArrayList)localObject1).size())
      {
        localObject2 = new MainListDao();
        ((MainListDao)localObject2).setText((String)((ArrayList)localObject1).get(paramInt));
        this.moreList.add(localObject2);
        paramInt += 1;
      }
      ((ArrayList)localObject1).clear();
      if ((this.selecter_docmentCount > 1) && (!this.hasFolder)) {
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165402));
      }
      if ((idlist.size() == 1) && (!this.hasFolder)) {
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165537));
      }
      if ((this.selecter_docmentCount > 0) && (!this.hasFolder)) {
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165406));
      }
      ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165553));
      if ((Build.VERSION.SDK_INT >= 19) && (!this.hasFolder) && (this.selecter_docmentCount == 1)) {
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165520));
      }
      ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165665));
      if ((this.selecter_docmentCount <= 1) && (!this.hasFolder))
      {
        ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165464));
        continue;
        if (paramInt == 1)
        {
          ((ArrayList)localObject1).clear();
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165377));
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165568));
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165439));
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165816));
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165400));
        }
        else
        {
          ((ArrayList)localObject1).clear();
          if ((idlist.size() == 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165537));
          }
          if ((this.selecter_docmentCount > 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165402));
          }
          if ((this.selecter_docmentCount > 0) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165406));
          }
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165553));
          if ((Build.VERSION.SDK_INT >= 19) && (!this.hasFolder) && (this.selecter_docmentCount == 1)) {
            ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165520));
          }
          ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165665));
          if ((this.selecter_docmentCount <= 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(activity_Main.getResources().getString(2131165464));
          }
        }
      }
    }
    if (this.popupWindow != null)
    {
      this.popupWindow.dismiss();
      this.popupWindow = null;
    }
    localObject1 = activity_Main.getLayoutInflater().inflate(2130903104, null, false);
    this.popupWindow = new PopupWindow(activity_Main);
    Object localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    paramInt = ((DisplayMetrics)localObject2).widthPixels;
    this.popupWindow.setHeight(-2);
    if (this.mapp.isPad()) {
      if (getResources().getConfiguration().orientation == 1) {
        this.popupWindow.setWidth(paramInt * 2 / 5);
      }
    }
    for (;;)
    {
      this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.setFocusable(true);
      this.popupWindow.setContentView((View)localObject1);
      ((View)localObject1).setOnTouchListener(new View.OnTouchListener()
      {
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if ((Activity_Main.this.popupWindow != null) && (Activity_Main.this.popupWindow.isShowing())) {
            Activity_Main.this.popupWindow.dismiss();
          }
          Activity_Main.access$1102(Activity_Main.this, null);
          return false;
        }
      });
      localObject2 = (ListView)((View)localObject1).findViewById(2131624306);
      ((ListView)localObject2).setAdapter(new MoreListAdapter(activity_Main, this.moreList));
      ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if ((Activity_Main.this.popupWindow != null) && (Activity_Main.this.popupWindow.isShowing())) {
            Activity_Main.this.popupWindow.dismiss();
          }
          Activity_Main.access$1102(Activity_Main.this, null);
          if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165402))) {
            Activity_Main.this.mergeMeuth();
          }
          do
          {
            return;
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165439)))
            {
              Activity_Main.this.showFolderDialog(false);
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165768)))
            {
              Activity_Main.this.selectImage();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165377)))
            {
              Activity_Main.this.takePicture(false);
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165816)))
            {
              Activity_Main.this.showViewandSort();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165400)))
            {
              paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_Setting.class);
              Activity_Main.this.startActivity(paramAnonymousAdapterView);
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165537)))
            {
              Activity_Main.this.renameMonth();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165553)))
            {
              new Thread(new Runnable()
              {
                public void run()
                {
                  int i = 0;
                  while (i < Activity_Main.idlist.size())
                  {
                    if (((Photo_info)Activity_Main.idlist.get(i)).isFolder())
                    {
                      localObject1 = new File(Activity_Main.this.root_Path4 + "/" + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles();
                      j = 0;
                      if (localObject1 != null) {
                        j = localObject1.length;
                      }
                      int k = 0;
                      while (k < j)
                      {
                        if (localObject1[k].isDirectory())
                        {
                          Object localObject3 = localObject1[k].list();
                          localObject2 = new ArrayList();
                          if (localObject3.length > 0)
                          {
                            int m = 0;
                            while (m < localObject3.length)
                            {
                              if (localObject3[m].matches("[0-9]{18}.jpg")) {
                                ((List)localObject2).add(localObject3[m]);
                              }
                              m += 1;
                            }
                            m = 0;
                            for (;;)
                            {
                              if (m < ((List)localObject2).size())
                              {
                                localObject3 = new File(localObject1[k].getPath() + "/" + (String)((List)localObject2).get(m));
                                File localFile2 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TopScanner");
                                if (!localFile2.exists()) {
                                  localFile2.mkdirs();
                                }
                                localFile2 = new File(localFile2.getPath() + "/" + localObject1[k].getName() + "_" + m + ".jpg");
                                if ((localFile2 != null) && (localFile2.exists())) {
                                  localFile2.delete();
                                }
                                try
                                {
                                  Activity_Main.this.copy((File)localObject3, localFile2);
                                  MediaScannerConnection.scanFile(Activity_Main.this.getApplicationContext(), new String[] { localFile2.getAbsolutePath() }, null, null);
                                  m += 1;
                                }
                                catch (IOException localIOException2)
                                {
                                  for (;;)
                                  {
                                    localIOException2.printStackTrace();
                                  }
                                }
                              }
                            }
                          }
                        }
                        k += 1;
                      }
                    }
                    Object localObject2 = new File(Activity_Main.this.root_Path3 + "/" + ((Photo_info)Activity_Main.idlist.get(i)).getName()).list();
                    localObject1 = new ArrayList();
                    if (localObject2 != null)
                    {
                      j = 0;
                      while (j < localObject2.length)
                      {
                        if (localObject2[j].matches("[0-9]{18}.jpg")) {
                          ((List)localObject1).add(localObject2[j]);
                        }
                        j += 1;
                      }
                    }
                    Collections.sort((List)localObject1, Activity_Main.comparator3);
                    int j = 0;
                    for (;;)
                    {
                      if (j < ((List)localObject1).size())
                      {
                        localObject2 = new File(Activity_Main.this.root_Path3 + "/" + ((Photo_info)Activity_Main.idlist.get(i)).getName() + "/" + (String)((List)localObject1).get(j));
                        File localFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TopScanner");
                        if (!localFile1.exists()) {
                          localFile1.mkdirs();
                        }
                        localFile1 = new File(localFile1.getPath() + "/" + ((Photo_info)Activity_Main.idlist.get(i)).getName() + "_" + j + ".jpg");
                        if ((localFile1 != null) && (localFile1.exists())) {
                          localFile1.delete();
                        }
                        try
                        {
                          Activity_Main.this.copy((File)localObject2, localFile1);
                          MediaScannerConnection.scanFile(Activity_Main.this.getApplicationContext(), new String[] { localFile1.getAbsolutePath() }, null, null);
                          j += 1;
                        }
                        catch (IOException localIOException1)
                        {
                          for (;;)
                          {
                            localIOException1.printStackTrace();
                          }
                        }
                      }
                    }
                    i += 1;
                  }
                  Object localObject1 = new Message();
                  ((Message)localObject1).what = 10;
                  Activity_Main.this.handler.sendMessage((Message)localObject1);
                }
              }).start();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165568)))
            {
              Activity_Main.this.sharethisApp();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165406)))
            {
              Activity_Main.this.moveDocment();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165520)))
            {
              Activity_Main.this.printMonth();
              return;
            }
            if (((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165665)))
            {
              Activity_Main.this.faxMonth();
              return;
            }
          } while (!((MainListDao)Activity_Main.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_Main.activity_Main.getResources().getString(2131165464)));
          Activity_Main.this.openin();
        }
      });
      ((LinearLayout)((View)localObject1).findViewById(2131624305)).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4) && (Activity_Main.this.popupWindow != null) && (Activity_Main.this.popupWindow.isShowing())) {
            Activity_Main.this.popupWindow.dismiss();
          }
          Activity_Main.access$1102(Activity_Main.this, null);
          return false;
        }
      });
      return;
      this.popupWindow.setWidth(paramInt * 2 / 7);
      continue;
      this.popupWindow.setWidth(paramInt * 7 / 10);
    }
  }
  
  private void initView()
  {
    this.mian_progreebar = ((ProgressBar)findViewById(2131624171));
    this.main_appname_textview = ((TextView)findViewById(2131624154));
    this.main_imagesearch = ((ImageView)findViewById(2131624155));
    this.main_imagesearch.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Activity_Main.this.search();
      }
    });
    this.main_imagesearch.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165558), 0);
        paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
        paramAnonymousView.show();
        return false;
      }
    });
    this.inflater = LayoutInflater.from(this.context);
    this.listphotos = ((LinearLayout)findViewById(2131624164));
    this.search_text = ((EditText)findViewById(2131624159));
    this.search_text.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (paramAnonymousEditable.toString().equals("")) {
          Activity_Main.this.search_delete.setVisibility(4);
        }
        for (;;)
        {
          if (Activity_Main.this.isSearch) {
            Activity_Main.this.getResults(paramAnonymousEditable.toString());
          }
          return;
          Activity_Main.this.search_delete.setVisibility(0);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.selecttext = ((TextView)findViewById(2131624163));
    this.search_delete = ((ImageView)findViewById(2131624160));
    this.search_delete.setOnClickListener(this.myOnClickListener);
    this.main_selecttextview = ((TextView)findViewById(2131624162));
    this.main_selectall = ((RadioButton)findViewById(2131624161));
    this.main_selectall.setOnClickListener(this.myOnClickListener);
    this.search_layout = ((RelativeLayout)findViewById(2131624158));
    this.back = ((ImageView)findViewById(2131624157));
    this.back.setOnClickListener(this.myOnClickListener);
    this.camera = ((ImageView)findViewById(2131624166));
    this.camera.setOnClickListener(this.myOnClickListener);
    this.more = ((ImageView)findViewById(2131624156));
    this.more.setOnClickListener(this.myOnClickListener);
    this.more.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165403), 0);
        paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
        paramAnonymousView.show();
        return false;
      }
    });
    this.main_onlongclick_layout = ((LinearLayout)findViewById(2131624165));
    this.main_sharelayout = ((LinearLayout)findViewById(2131624167));
    this.main_sharelayout.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165567), 0);
        paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
        paramAnonymousView.show();
        return false;
      }
    });
    if (!this.mapp.isPad())
    {
      this.main_emaillayout = ((LinearLayout)findViewById(2131624168));
      this.main_emaillayout.setOnClickListener(this.myOnClickListener);
    }
    for (;;)
    {
      this.main_deletelayout = ((LinearLayout)findViewById(2131624169));
      this.main_morelayout = ((LinearLayout)findViewById(2131624170));
      this.main_onlongclick_layout.setVisibility(8);
      this.main_sharelayout.setOnClickListener(this.myOnClickListener);
      this.main_deletelayout.setOnClickListener(this.myOnClickListener);
      this.main_morelayout.setOnClickListener(this.myOnClickListener);
      this.main_deletelayout.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165290), 0);
          paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      return;
      this.main_importfromgallery = ((ImageView)findViewById(2131624172));
      this.main_newfolder = ((ImageView)findViewById(2131624173));
      this.main_viewsortby = ((ImageView)findViewById(2131624174));
      this.main_importfromgallery.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165377), 0);
          paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.main_newfolder.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165439), 0);
          paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.main_viewsortby.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165574), 0);
          paramAnonymousView.setGravity(48, 0, Activity_Main.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.main_importfromgallery.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Main.this.takePicture(false);
        }
      });
      this.main_newfolder.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Main.this.showFolderDialog(false);
        }
      });
      this.main_viewsortby.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Main.this.showViewandSort();
        }
      });
    }
  }
  
  private boolean isPad()
  {
    return (this.context.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  private void mergeMeuth()
  {
    int i = 1;
    Object localObject = idlist.iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((Photo_info)((Iterator)localObject).next()).isFolder()) {
        i = 0;
      }
    }
    if (i != 0)
    {
      if (idlist.size() == 1)
      {
        showToast(getResources().getString(2131165404));
        return;
      }
      localObject = this.inflater.inflate(2130903122, null);
      EditText localEditText = (EditText)((View)localObject).findViewById(2131624336);
      localEditText.setSelectAllOnFocus(true);
      if (idlist.size() > 0) {
        localEditText.setText(((Photo_info)idlist.get(0)).getShowname());
      }
      localObject = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131165402)).setView((View)localObject).setPositiveButton(getResources().getString(2131165549), new DialogInterface.OnClickListener()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Object localObject3 = (EditText)this.val$view.findViewById(2131624336);
          if (((EditText)localObject3).getText().toString().trim().equals(""))
          {
            Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131165333));
            return;
          }
          paramAnonymousInt = 0;
          Object localObject1;
          Object localObject2;
          int i;
          while (paramAnonymousInt < Activity_Main.idlist.size())
          {
            localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName()).listFiles();
            localObject2 = new ArrayList();
            if (localObject1 != null)
            {
              i = 0;
              while (i < localObject1.length)
              {
                if (localObject1[i].getName().matches("[0-9]{18}.jpg")) {
                  ((ArrayList)localObject2).add(localObject1[i].getPath());
                }
                i += 1;
              }
            }
            int j = ((ArrayList)localObject2).size();
            if (paramAnonymousInt != 0)
            {
              if (Activity_Main.this.isSearch) {}
              Object localObject4;
              File localFile;
              for (localObject2 = new File(((Photo_info)Activity_Main.idlist.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.idlist.get(paramAnonymousInt)).getName());; localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(paramAnonymousInt)).getName()))
              {
                localObject4 = ((File)localObject2).listFiles();
                localObject1 = new ArrayList();
                int k = localObject4.length;
                i = 0;
                while (i < k)
                {
                  localFile = localObject4[i];
                  if (localFile.getName().matches("[0-9]{18}.jpg")) {
                    ((ArrayList)localObject1).add(localFile.getPath());
                  }
                  i += 1;
                }
              }
              try
              {
                System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                Collections.sort((List)localObject1, Activity_Main.comparator3);
                localObject4 = ((ArrayList)localObject1).iterator();
                i = j;
                for (;;)
                {
                  if (!((Iterator)localObject4).hasNext()) {
                    break label617;
                  }
                  localFile = new File((String)((Iterator)localObject4).next());
                  if (i >= 10) {
                    break;
                  }
                  localObject1 = "00" + i;
                  localObject1 = localFile.getName().substring(0, 15) + (String)localObject1 + ".jpg";
                  localFile.renameTo(new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName() + "/" + (String)localObject1));
                  i += 1;
                }
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  localException.printStackTrace();
                  continue;
                  if (i < 100) {
                    localObject1 = "0" + i;
                  } else {
                    localObject1 = "" + i;
                  }
                }
                label617:
                localObject1 = ((File)localObject2).listFiles();
                j = localObject1.length;
                i = 0;
                while (i < j)
                {
                  localObject1[i].delete();
                  i += 1;
                }
                ((File)localObject2).delete();
              }
            }
            paramAnonymousInt += 1;
          }
          if ((Activity_Main.idlist.size() > 0) && (((EditText)localObject3).getText().toString().equals(((Photo_info)Activity_Main.idlist.get(0)).getShowname())))
          {
            localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName()).list(new Activity_Main.MyFilter2(Activity_Main.this));
            if (localObject1 != null)
            {
              i = localObject1.length;
              paramAnonymousInt = 0;
            }
          }
          else
          {
            while (paramAnonymousInt < i)
            {
              localObject2 = localObject1[paramAnonymousInt];
              new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName() + "/" + (String)localObject2).delete();
              paramAnonymousInt += 1;
              continue;
              localObject1 = ((EditText)localObject3).getText().toString();
              localObject2 = ((EditText)localObject3).getText().toString();
              if (Activity_Main.this.checkName((String)localObject1)) {}
              for (;;)
              {
                localObject1 = new File(Activity_Main.this.root_Path3 + (String)localObject1);
                if (Activity_Main.idlist.size() > 0) {
                  new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName()).renameTo((File)localObject1);
                }
                localObject2 = ((File)localObject1).list(new Activity_Main.MyFilter2(Activity_Main.this));
                if (localObject2 == null) {
                  break;
                }
                i = localObject2.length;
                paramAnonymousInt = 0;
                while (paramAnonymousInt < i)
                {
                  localObject3 = localObject2[paramAnonymousInt];
                  new File(((File)localObject1).getPath() + "/" + (String)localObject3).delete();
                  paramAnonymousInt += 1;
                }
                localObject3 = new Timestamp(System.currentTimeMillis());
                localObject3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject3);
                localObject2 = ((String)localObject2).replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject3;
                Activity_Main.this.saveNameToDb((String)localObject2, (String)localObject1);
                localObject1 = localObject2;
              }
            }
          }
          if (Activity_Main.this.searchandclick) {
            Activity_Main.this.unselected2();
          }
          for (;;)
          {
            Activity_Main.this.list_folders();
            paramAnonymousDialogInterface.dismiss();
            return;
            Activity_Main.this.unselected();
          }
        }
      }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).create();
      ((AlertDialog)localObject).show();
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          ((InputMethodManager)this.val$mDialog.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
        }
      }, 100L);
      return;
    }
    showToast(getResources().getString(2131165462));
  }
  
  private void onAuthenticated(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131165389), 1).show();
    }
  }
  
  private void onFolderSelected(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131165315), 1).show();
    }
  }
  
  private void openin()
  {
    this.pdf_OR_jpeg = 1;
    this.select_pdf_posiotion = 3;
    shareDocment();
  }
  
  private void printMonth()
  {
    this.pdf_OR_jpeg = 1;
    this.select_pdf_posiotion = 1;
    shareDocment();
  }
  
  private void renameMonth()
  {
    if (idlist.size() > 1) {
      Toast.makeText(this.context, getResources().getString(2131165463), 0).show();
    }
    do
    {
      return;
      final View localView = this.inflater.inflate(2130903122, null);
      EditText localEditText = (EditText)localView.findViewById(2131624336);
      localEditText.setSelectAllOnFocus(true);
      localEditText.setText(((Photo_info)idlist.get(0)).getShowname());
      new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131165537)).setView(localView).setPositiveButton(getResources().getString(2131165549), new DialogInterface.OnClickListener()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Object localObject1 = (EditText)localView.findViewById(2131624336);
          if (((EditText)localObject1).getText().toString().equals(((Photo_info)Activity_Main.idlist.get(0)).getName()))
          {
            if (Activity_Main.this.searchandclick) {
              if (Activity_Main.this.mapp.isPad()) {
                Activity_Main.this.unselected3();
              }
            }
            for (;;)
            {
              paramAnonymousDialogInterface.dismiss();
              return;
              Activity_Main.this.unselected2();
              continue;
              Activity_Main.this.unselected();
            }
          }
          if (((EditText)localObject1).getText().toString().trim().equals(""))
          {
            Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131165333));
            return;
          }
          String str1 = ((EditText)localObject1).getText().toString();
          localObject1 = ((EditText)localObject1).getText().toString();
          Photo_info localPhoto_info = (Photo_info)Activity_Main.idlist.get(0);
          if (localPhoto_info.isFolder()) {}
          for (boolean bool = Activity_Main.this.checkFoldername(str1); bool; bool = Activity_Main.this.checkDocumentname(str1))
          {
            paramAnonymousDialogInterface.dismiss();
            ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
            paramAnonymousDialogInterface = new Message();
            paramAnonymousDialogInterface.what = 31;
            Activity_Main.this.handler.sendMessageDelayed(paramAnonymousDialogInterface, 100L);
            return;
          }
          if (Activity_Main.this.checkName(str1))
          {
            localObject1 = str1;
            if (!localPhoto_info.isFolder()) {
              break label653;
            }
          }
          for (;;)
          {
            if (!localPhoto_info.isFolder()) {
              break label807;
            }
            localObject2 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.idlist.get(0)).getName());
            if (((File)localObject2).exists()) {
              ((File)localObject2).renameTo(new File(Activity_Main.this.root_Path4 + (String)localObject1));
            }
            localObject2 = localPhoto_info.getImage_name().iterator();
            while (((Iterator)localObject2).hasNext())
            {
              String str2 = (String)((Iterator)localObject2).next();
              BitmapDrawable localBitmapDrawable = Activity_Main.this.mapp.getBitmapFromMemCache("main" + str2);
              Activity_Main.this.mapp.getmMemoryCache().remove("main" + str2);
              paramAnonymousInt = str2.lastIndexOf("/", str2.lastIndexOf("/") - 1);
              Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path4 + (String)localObject1 + "/" + str2.substring(paramAnonymousInt + 1, str2.length()), localBitmapDrawable);
            }
            localObject2 = new Timestamp(System.currentTimeMillis());
            localObject2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject2);
            localObject1 = ((String)localObject1).replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject2;
            Activity_Main.this.saveNameToDb((String)localObject1, str1);
            break;
            label653:
            localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName() + "/" + ((Photo_info)Activity_Main.idlist.get(0)).getName() + ".pdf");
            if (((File)localObject2).exists()) {
              ((File)localObject2).renameTo(new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName() + "/" + (String)localObject1 + ".pdf"));
            }
          }
          label807:
          Object localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName());
          if (((File)localObject2).exists()) {
            ((File)localObject2).renameTo(new File(Activity_Main.this.root_Path3 + (String)localObject1));
          }
          localObject2 = Activity_Main.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
          Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
          Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path3 + (String)localObject1 + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), (BitmapDrawable)localObject2);
          localPhoto_info.setName((String)localObject1);
          localPhoto_info.setShowname(str1);
          if (Activity_Main.this.searchandclick) {
            if (Activity_Main.this.mapp.isPad()) {
              Activity_Main.this.unselected3();
            }
          }
          for (;;)
          {
            Activity_Main.this.list_folders();
            paramAnonymousDialogInterface.dismiss();
            return;
            Activity_Main.this.unselected2();
            continue;
            Activity_Main.this.unselected();
          }
        }
      }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).create().show();
    } while (this.mapp.isPad());
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
      }
    }, 100L);
  }
  
  private void requstPermisstion()
  {
    ActivityCompat.requestPermissions(this, this.PERMISSION, 0);
  }
  
  private void selectImage()
  {
    this.mapp.setSavePath(this.root_Path3);
    Utils.launchPicker(activity_Main);
  }
  
  private void sharethisApp()
  {
    FlurryAgent.logEvent("Share_this_App");
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + "com.studio.topscanner");
    localIntent.setType("text/plain");
    startActivity(Intent.createChooser(localIntent, activity_Main.getResources().getString(2131165567)));
  }
  
  private void showCancelPermissionDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setMessage(2131165786);
    localBuilder.setNegativeButton(2131165649, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.finish();
      }
    });
    localBuilder.setPositiveButton(2131165804, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.requstPermisstion();
      }
    });
    localBuilder.setCancelable(false);
    if (!activity_Main.isFinishing()) {
      localBuilder.show();
    }
  }
  
  @SuppressLint({"InflateParams"})
  private void showFolderDialog(final boolean paramBoolean)
  {
    File localFile = new File(this.root_Path4 + getString(2131165439));
    int i = 2;
    while (localFile.exists())
    {
      localFile = new File(this.root_Path4 + getString(2131165439) + "(" + i + ")");
      i += 1;
    }
    final View localView = this.inflater.inflate(2130903122, null);
    EditText localEditText = (EditText)localView.findViewById(2131624336);
    localEditText.setSelectAllOnFocus(true);
    localEditText.setText(localFile.getName());
    new AlertDialog.Builder(this.context).setTitle(getString(2131165439)).setView(localView).setPositiveButton(getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        EditText localEditText = (EditText)localView.findViewById(2131624336);
        if (localEditText.getText().toString().trim().equals(""))
        {
          Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131165333));
          return;
        }
        paramAnonymousDialogInterface.dismiss();
        if (Activity_Main.this.checkFoldername(localEditText.getText().toString()))
        {
          ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
          paramAnonymousDialogInterface = new Message();
          paramAnonymousDialogInterface.what = 31;
          Activity_Main.this.handler.sendMessageDelayed(paramAnonymousDialogInterface, 100L);
          return;
        }
        Activity_Main.this.createNewFolder(localEditText.getText().toString(), localEditText.getText().toString(), paramBoolean);
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
      }
    }, 100L);
  }
  
  private void showMessageDialog(String paramString1, String paramString2)
  {
    new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).create().show();
  }
  
  private void showMissingPermissionDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setMessage(2131165787);
    localBuilder.setNegativeButton(2131165725, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.finish();
      }
    });
    localBuilder.setPositiveButton(2131165678, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.startAppSettings();
      }
    });
    localBuilder.setCancelable(false);
    if (!activity_Main.isFinishing()) {
      localBuilder.show();
    }
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.mProgressDialog == null)
    {
      this.mProgressDialog = new ProgressDialog(this);
      this.mProgressDialog.setIndeterminate(true);
    }
    this.mProgressDialog.setMessage(paramString);
    this.mProgressDialog.show();
  }
  
  @SuppressLint({"InflateParams", "ClickableViewAccessibility", "DefaultLocale"})
  private void showRateDialog1()
  {
    View localView = activity_Main.getLayoutInflater().inflate(2130903135, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setTitle(activity_Main.getResources().getString(2131165757)).setView(localView).setNegativeButton(activity_Main.getResources().getString(2131165536).toUpperCase(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        Activity_Main.this.editor.putInt("click_notnow_times", Activity_Main.this.preferences.getInt("click_notnow_times", 0) + 1);
        Activity_Main.this.editor.putInt("reset_nitnow1.3.1", 1);
        Activity_Main.access$5202(Activity_Main.this, 1);
        Activity_Main.this.editor.putBoolean("newuser_isnotnow", true);
        Activity_Main.this.editor.commit();
      }
    }).setPositiveButton(activity_Main.getResources().getString(2131165785).toUpperCase(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        Activity_Main.this.editor.putInt("newuser_5stars", 2);
        Activity_Main.this.editor.commit();
        FlurryAgent.logEvent("5_RateNow");
        Utils.showGooglePlayApplication(Activity_Main.activity_Main);
      }
    });
    this.rateNewDialog1 = localBuilder.create();
    this.rateNewDialog1.setCanceledOnTouchOutside(false);
    if (!activity_Main.isFinishing()) {
      this.rateNewDialog1.show();
    }
    this.rateNewDialog1.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
  }
  
  @SuppressLint({"InflateParams"})
  private void showRateFeedback()
  {
    View localView = activity_Main.getLayoutInflater().inflate(2130903133, null);
    final EditText localEditText = (EditText)localView.findViewById(2131624350);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setTitle("").setView(localView).setPositiveButton(activity_Main.getResources().getString(2131165577), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        Activity_Main.this.mapp.setEmailInfo(Activity_Main.this.mapp.getEmailInfo() + localEditText.getText().toString().trim());
        Utils.sendFeedback(Activity_Main.activity_Main, Activity_Main.this.mapp);
      }
    }).setNegativeButton(activity_Main.getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    this.feedbackdialog = localBuilder.create();
    this.feedbackdialog.setCanceledOnTouchOutside(false);
    if (!activity_Main.isFinishing()) {
      this.feedbackdialog.show();
    }
    this.feedbackdialog.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
  }
  
  private void showThanksRate(final Activity paramActivity)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setTitle(activity_Main.getResources().getString(2131165530)).setMessage(paramActivity.getResources().getString(2131165528)).setPositiveButton(activity_Main.getResources().getString(2131165529), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        FlurryAgent.logEvent("5_RateNow");
        Utils.showGooglePlayApplication(paramActivity);
      }
    });
    paramActivity = localBuilder.create();
    paramActivity.setCanceledOnTouchOutside(false);
    if (!activity_Main.isFinishing()) {
      paramActivity.show();
    }
    paramActivity.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return (paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getRepeatCount() == 0);
      }
    });
  }
  
  private void showViewandSort()
  {
    Object localObject = new AlertDialog.Builder(activity_Main);
    ((AlertDialog.Builder)localObject).setItems(this.viewsortStr, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (Activity_Main.this.mapp.isPad())
        {
          switch (paramAnonymousInt)
          {
          default: 
            return;
          case 0: 
            Activity_Main.this.sortDateCreated();
            return;
          }
          Activity_Main.this.sortFileName();
          return;
        }
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          Activity_Main.this.sortGridView();
          return;
        case 1: 
          Activity_Main.this.sortListview();
          return;
        case 2: 
          Activity_Main.this.sortDateCreated();
          return;
        }
        Activity_Main.this.sortFileName();
      }
    }).setNegativeButton(activity_Main.getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((Dialog)localObject).setCanceledOnTouchOutside(true);
    ((Dialog)localObject).show();
  }
  
  private void sortDateCreated()
  {
    sort_type = 0;
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    try
    {
      Collections.sort(this.mlist2, comparator);
      if (this.list_type == 0) {
        if (this.madapter != null) {
          this.madapter.notifyDataSetChanged();
        }
      }
      for (;;)
      {
        this.editor = this.preferences.edit();
        this.editor.putInt("sort_type", 0);
        this.editor.commit();
        return;
        if (this.madapter2 != null) {
          this.madapter2.notifyDataSetChanged();
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void sortFileName()
  {
    sort_type = 1;
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    try
    {
      Collections.sort(this.mlist2, comparator2);
      if (this.list_type == 0) {
        if (this.madapter != null) {
          this.madapter.notifyDataSetChanged();
        }
      }
      for (;;)
      {
        this.editor = this.preferences.edit();
        this.editor.putInt("sort_type", 1);
        this.editor.commit();
        return;
        if (this.madapter2 != null) {
          this.madapter2.notifyDataSetChanged();
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void sortGridView()
  {
    if (this.list_type == 0) {
      return;
    }
    this.list_type = 0;
    list_by_grid();
    this.editor = this.preferences.edit();
    this.editor.putInt("list_type", 0);
    this.editor.commit();
  }
  
  private void sortListview()
  {
    if (this.list_type == 1) {
      return;
    }
    this.list_type = 1;
    list_by_list();
    this.editor = this.preferences.edit();
    this.editor.putInt("list_type", 1);
    this.editor.commit();
  }
  
  private void startAppSettings()
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.parse("package:" + getPackageName()));
    startActivity(localIntent);
  }
  
  private void thankBuy(String paramString)
  {
    new AlertDialog.Builder(activity_Main).setMessage(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }
  
  private void writeSDcardFile()
  {
    this.mapp.getSdcard_list().clear();
    this.mapp.getSdcard_list().addAll(SDCardScanner.getExtSDCardPaths(activity_Main));
    init();
    if (ExistSDCard()) {
      makefolder();
    }
    int i;
    for (;;)
    {
      try
      {
        list_folders();
        if (this.searchandclick)
        {
          search();
          getResults(this.search_text.getText().toString());
          clear2();
          selected();
          if (this.list_type != 0) {
            continue;
          }
          if (this.listphotos != null)
          {
            localObject = (GridView)this.listphotos.findViewById(2131624290);
            if (localObject != null) {
              ((GridView)localObject).setSelection(this.preferences.getInt("folder_id", 0));
            }
          }
          i = 0;
          Object localObject = this.mlist2.iterator();
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          Photo_info localPhoto_info = (Photo_info)((Iterator)localObject).next();
          if (localPhoto_info.getImage_name() == null) {
            continue;
          }
          i += localPhoto_info.getImage_name().size();
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if ((idlist == null) || (idlist.size() <= 0)) {
          continue;
        }
        int j = idlist.size();
        i = 0;
        if (i < j)
        {
          int k = findId((Photo_info)idlist.get(i));
          if (k != -1) {
            ((Photo_info)this.mlist2.get(k)).setCheck(true);
          }
          i += 1;
          continue;
        }
        if (this.list_type == 0)
        {
          list_by_grid();
          continue;
        }
        list_by_list();
        continue;
        if (this.isSelect)
        {
          selected();
          continue;
        }
        if (!this.isSearch) {
          continue;
        }
        if (this.search_text.getText().toString().equals(""))
        {
          getResults(this.search_text.getText().toString());
          new Timer().schedule(new TimerTask()
          {
            public void run()
            {
              ((InputMethodManager)Activity_Main.this.getSystemService("input_method")).toggleSoftInput(1, 2);
            }
          }, 500L);
          continue;
        }
        this.search_delete.setVisibility(0);
        continue;
        if (this.listphotos == null) {
          continue;
        }
        ListView localListView = (ListView)this.listphotos.findViewById(2131624291);
        if (localListView == null) {
          continue;
        }
        localListView.setSelection(this.preferences.getInt("folder_id", 0));
        continue;
      }
      Toast.makeText(this.context, getString(2131165557), 0).show();
    }
    this.editor.putInt("count_pdffile", i);
    this.editor.commit();
    Log.i("TAG", "conutFile===" + i);
    if ((this.preferences.getInt("newUser_1.0", -1) == 1) && (this.preferences.getBoolean("isCliek_takecamera", false)) && (this.preferences.getInt("newuser_5stars", 1) == 1) && (this.preferences.getInt("click_notnow_times", 0) <= 2))
    {
      if (!this.preferences.getBoolean("newuser_isnotnow", false)) {
        break label642;
      }
      if (this.initNotnowTimes >= 3)
      {
        if (this.rateNewDialog1 != null) {
          break label625;
        }
        showRateDialog1();
      }
    }
    for (;;)
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          int i = 0;
          for (;;)
          {
            try
            {
              if (i < Activity_Main.this.mapp.getSdcard_list().size()) {
                if (i == 0) {
                  FileOperator.deleteFile(new File(Environment.getExternalStorageDirectory() + "/TopScanner/temporary/"));
                } else {
                  FileOperator.deleteFile(new File((String)Activity_Main.this.mapp.getSdcard_list().get(i) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/"));
                }
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
            return;
            i += 1;
          }
        }
      }).start();
      return;
      label625:
      if (!this.rateNewDialog1.isShowing())
      {
        showRateDialog1();
        continue;
        label642:
        if (this.times >= 2)
        {
          if (this.rateNewDialog1 != null) {
            break label680;
          }
          showRateDialog1();
        }
        for (;;)
        {
          if (i < 2) {
            break label695;
          }
          if (this.rateNewDialog1 != null) {
            break label697;
          }
          showRateDialog1();
          break;
          label680:
          if (!this.rateNewDialog1.isShowing()) {
            showRateDialog1();
          }
        }
        label695:
        continue;
        label697:
        if (!this.rateNewDialog1.isShowing()) {
          showRateDialog1();
        }
      }
    }
  }
  
  public void CompressImage()
  {
    Object localObject = new File(this.compressJpeg_Path);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".jpg"));
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        clearFile(localObject[i]);
        i += 1;
      }
    }
    ((File)localObject).mkdir();
    this.mian_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        Object localObject1;
        int j;
        ArrayList localArrayList;
        Object localObject2;
        int k;
        if (i < Activity_Main.idlist.size())
        {
          if (((Photo_info)Activity_Main.idlist.get(i)).isFolder())
          {
            localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles();
            int m = localObject1.length;
            j = 0;
            while (j < m)
            {
              localArrayList = localObject1[j];
              Object localObject3 = localArrayList.list();
              localObject2 = new ArrayList();
              if (localObject3 != null)
              {
                k = 0;
                while (k < localObject3.length)
                {
                  if (localObject3[k].matches("[0-9]{18}.jpg")) {
                    ((List)localObject2).add(localObject3[k]);
                  }
                  k += 1;
                }
              }
              Collections.sort((List)localObject2, Activity_Main.comparator3);
              int n = ((List)localObject2).size();
              k = 0;
              label181:
              if (k < n)
              {
                BufferedOutputStream localBufferedOutputStream2;
                try
                {
                  localObject3 = BitmapFactory.decodeStream(new FileInputStream(new File(localArrayList.getPath() + "/" + (String)((List)localObject2).get(k))));
                  localBufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(Activity_Main.this.compressJpeg_Path + (String)((List)localObject2).get(k)));
                  if (Activity_Main.this.export_size == 0) {
                    ((Bitmap)localObject3).compress(Bitmap.CompressFormat.JPEG, 100, localBufferedOutputStream2);
                  } else if (Activity_Main.this.export_size == 1) {
                    ((Bitmap)localObject3).compress(Bitmap.CompressFormat.JPEG, 50, localBufferedOutputStream2);
                  }
                }
                catch (IOException localIOException2)
                {
                  System.err.println(localIOException2.getMessage());
                }
                if (Activity_Main.this.export_size != 2) {
                  break label764;
                }
                localIOException2.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream2);
                break label764;
              }
              j += 1;
            }
          }
          localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName());
          localObject2 = ((File)localObject1).list();
          localArrayList = new ArrayList();
          if (localObject2 != null)
          {
            j = 0;
            while (j < localObject2.length)
            {
              if (localObject2[j].matches("[0-9]{18}.jpg")) {
                localArrayList.add(localObject2[j]);
              }
              j += 1;
            }
          }
          Collections.sort(localArrayList, Activity_Main.comparator3);
          k = localArrayList.size();
          j = 0;
        }
        for (;;)
        {
          if (j < k)
          {
            BufferedOutputStream localBufferedOutputStream1;
            try
            {
              localObject2 = BitmapFactory.decodeStream(new FileInputStream(new File(((File)localObject1).getPath() + "/" + (String)localArrayList.get(j))));
              localBufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream(Activity_Main.this.compressJpeg_Path + (String)localArrayList.get(j)));
              if (Activity_Main.this.export_size == 0) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 100, localBufferedOutputStream1);
              } else if (Activity_Main.this.export_size == 1) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 50, localBufferedOutputStream1);
              }
            }
            catch (IOException localIOException1)
            {
              System.err.println(localIOException1.getMessage());
            }
            if (Activity_Main.this.export_size == 2) {
              localIOException1.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream1);
            }
          }
          else
          {
            i += 1;
            break;
            localObject1 = new Message();
            ((Message)localObject1).what = 33;
            Activity_Main.this.handler.sendMessage((Message)localObject1);
            return;
            label764:
            k += 1;
            break label181;
          }
          j += 1;
        }
      }
    });
    this.mThread.start();
  }
  
  public void changeView()
  {
    this.selecter_docmentCount = 0;
    this.hasFolder = false;
    Iterator localIterator = idlist.iterator();
    while (localIterator.hasNext()) {
      if (((Photo_info)localIterator.next()).isFolder()) {
        this.hasFolder = true;
      } else {
        this.selecter_docmentCount += 1;
      }
    }
  }
  
  public boolean checkDocumentname(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    Object localObject = new File(this.root_Path3);
    if (localObject != null)
    {
      localObject = ((File)localObject).listFiles();
      int j = localObject.length;
      int i = 0;
      for (;;)
      {
        bool2 = bool1;
        if (i >= j) {
          break;
        }
        if (getShowName(localObject[i].getName()).equals(paramString)) {
          bool1 = true;
        }
        i += 1;
      }
    }
    return bool2;
  }
  
  public boolean checkFoldername(String paramString)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    Object localObject = new File(this.root_Path4);
    boolean bool2 = bool3;
    if (localObject != null)
    {
      bool2 = bool3;
      if (((File)localObject).listFiles() != null)
      {
        localObject = ((File)localObject).listFiles();
        int j = localObject.length;
        int i = 0;
        for (;;)
        {
          bool2 = bool1;
          if (i >= j) {
            break;
          }
          if (getShowName(localObject[i].getName()).equals(paramString)) {
            bool1 = true;
          }
          i += 1;
        }
      }
    }
    return bool2;
  }
  
  public boolean checkName(String paramString)
  {
    return (!paramString.contains("*")) && (!paramString.contains("/")) && (!paramString.contains("\\")) && (!paramString.contains("\"")) && (!paramString.contains(":")) && (!paramString.contains("?")) && (!paramString.contains("|")) && (!paramString.contains("<")) && (!paramString.contains(">"));
  }
  
  public void clearFile(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          clearFile(arrayOfFile[i]);
          i += 1;
        }
      }
    }
    paramFile.delete();
  }
  
  public void copy(File paramFile1, File paramFile2)
    throws IOException
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramFile1.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile1.close();
    paramFile2.close();
  }
  
  public void createPDF()
  {
    Object localObject = new File(this.root_Path5);
    int j;
    int i;
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles();
      if (localObject != null)
      {
        j = localObject.length;
        i = 0;
        while (i < j)
        {
          clearFile(localObject[i]);
          i += 1;
        }
      }
    }
    else
    {
      ((File)localObject).mkdir();
    }
    localObject = new File(this.root_Path6);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles();
      if (localObject != null)
      {
        j = localObject.length;
        i = 0;
        while (i < j)
        {
          clearFile(localObject[i]);
          i += 1;
        }
      }
    }
    else
    {
      ((File)localObject).mkdir();
    }
    this.mian_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      private PdfWriter writer;
      
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_1
        //   2: iload_1
        //   3: aload_0
        //   4: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   7: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   10: invokeinterface 41 1 0
        //   15: if_icmpge +1341 -> 1356
        //   18: new 43	java/io/File
        //   21: dup
        //   22: aload_0
        //   23: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   26: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   29: iload_1
        //   30: invokeinterface 47 2 0
        //   35: checkcast 49	java/util/HashMap
        //   38: ldc 51
        //   40: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   43: checkcast 56	java/lang/String
        //   46: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   49: astore 7
        //   51: aload_0
        //   52: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   55: invokestatic 63	com/simpleapp/tinyscanfree/Activity_Main:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_Main;)I
        //   58: ifne +195 -> 253
        //   61: aload 7
        //   63: new 65	com/simpleapp/tinyscanfree/Activity_Main$MyFilter2
        //   66: dup
        //   67: aload_0
        //   68: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   71: invokespecial 67	com/simpleapp/tinyscanfree/Activity_Main$MyFilter2:<init>	(Lcom/simpleapp/tinyscanfree/Activity_Main;)V
        //   74: invokevirtual 71	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   77: astore 4
        //   79: aload 4
        //   81: ifnull +17 -> 98
        //   84: aload 4
        //   86: arraylength
        //   87: ifle +11 -> 98
        //   90: aload 4
        //   92: iconst_0
        //   93: aaload
        //   94: invokevirtual 75	java/io/File:delete	()Z
        //   97: pop
        //   98: new 43	java/io/File
        //   101: dup
        //   102: new 77	java/lang/StringBuilder
        //   105: dup
        //   106: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   109: aload_0
        //   110: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   113: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   116: iload_1
        //   117: invokeinterface 47 2 0
        //   122: checkcast 49	java/util/HashMap
        //   125: ldc 51
        //   127: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   130: checkcast 56	java/lang/String
        //   133: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: ldc 84
        //   138: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: aload_0
        //   142: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   145: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   148: iload_1
        //   149: invokeinterface 47 2 0
        //   154: checkcast 49	java/util/HashMap
        //   157: ldc 86
        //   159: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   162: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   165: ldc 91
        //   167: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   173: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   176: astore 4
        //   178: aload 4
        //   180: invokevirtual 98	java/io/File:exists	()Z
        //   183: ifeq +9 -> 192
        //   186: aload 4
        //   188: invokevirtual 75	java/io/File:delete	()Z
        //   191: pop
        //   192: aload 7
        //   194: invokevirtual 102	java/io/File:list	()[Ljava/lang/String;
        //   197: astore 5
        //   199: new 104	java/util/ArrayList
        //   202: dup
        //   203: invokespecial 105	java/util/ArrayList:<init>	()V
        //   206: astore 8
        //   208: aload 5
        //   210: ifnull +272 -> 482
        //   213: iconst_0
        //   214: istore_2
        //   215: iload_2
        //   216: aload 5
        //   218: arraylength
        //   219: if_icmpge +263 -> 482
        //   222: aload 5
        //   224: iload_2
        //   225: aaload
        //   226: ldc 107
        //   228: invokevirtual 111	java/lang/String:matches	(Ljava/lang/String;)Z
        //   231: ifeq +15 -> 246
        //   234: aload 8
        //   236: aload 5
        //   238: iload_2
        //   239: aaload
        //   240: invokeinterface 115 2 0
        //   245: pop
        //   246: iload_2
        //   247: iconst_1
        //   248: iadd
        //   249: istore_2
        //   250: goto -35 -> 215
        //   253: aload_0
        //   254: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   257: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   260: iload_1
        //   261: invokeinterface 47 2 0
        //   266: checkcast 49	java/util/HashMap
        //   269: ldc 117
        //   271: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   274: checkcast 119	java/lang/Boolean
        //   277: invokevirtual 122	java/lang/Boolean:booleanValue	()Z
        //   280: ifeq +141 -> 421
        //   283: aload_0
        //   284: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   287: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   290: iload_1
        //   291: invokeinterface 47 2 0
        //   296: checkcast 49	java/util/HashMap
        //   299: ldc 124
        //   301: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   304: checkcast 56	java/lang/String
        //   307: astore 4
        //   309: new 43	java/io/File
        //   312: dup
        //   313: new 77	java/lang/StringBuilder
        //   316: dup
        //   317: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   320: aload_0
        //   321: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   324: invokestatic 128	com/simpleapp/tinyscanfree/Activity_Main:access$3100	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   327: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: aload 4
        //   332: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   338: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   341: astore 4
        //   343: aload 4
        //   345: invokevirtual 98	java/io/File:exists	()Z
        //   348: ifne +9 -> 357
        //   351: aload 4
        //   353: invokevirtual 131	java/io/File:mkdirs	()Z
        //   356: pop
        //   357: new 43	java/io/File
        //   360: dup
        //   361: new 77	java/lang/StringBuilder
        //   364: dup
        //   365: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   368: aload 4
        //   370: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   373: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: ldc 84
        //   378: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: aload_0
        //   382: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   385: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   388: iload_1
        //   389: invokeinterface 47 2 0
        //   394: checkcast 49	java/util/HashMap
        //   397: ldc 86
        //   399: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   402: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   405: ldc 91
        //   407: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   413: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   416: astore 4
        //   418: goto -240 -> 178
        //   421: new 43	java/io/File
        //   424: dup
        //   425: new 77	java/lang/StringBuilder
        //   428: dup
        //   429: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   432: aload_0
        //   433: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   436: invokestatic 137	com/simpleapp/tinyscanfree/Activity_Main:access$3000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   439: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: aload_0
        //   443: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   446: invokestatic 35	com/simpleapp/tinyscanfree/Activity_Main:access$5000	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   449: iload_1
        //   450: invokeinterface 47 2 0
        //   455: checkcast 49	java/util/HashMap
        //   458: ldc 86
        //   460: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   463: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   466: ldc 91
        //   468: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   471: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   474: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   477: astore 4
        //   479: goto -301 -> 178
        //   482: ldc -117
        //   484: ldc -115
        //   486: invokestatic 147	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   489: pop
        //   490: aload 8
        //   492: getstatic 151	com/simpleapp/tinyscanfree/Activity_Main:comparator3	Ljava/util/Comparator;
        //   495: invokestatic 157	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   498: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   501: astore 5
        //   503: new 165	com/itextpdf/text/Document
        //   506: dup
        //   507: invokespecial 166	com/itextpdf/text/Document:<init>	()V
        //   510: astore 9
        //   512: aconst_null
        //   513: astore 6
        //   515: aconst_null
        //   516: astore 5
        //   518: new 168	com/itextpdf/text/pdf/PdfCopy
        //   521: dup
        //   522: aload 9
        //   524: new 170	java/io/FileOutputStream
        //   527: dup
        //   528: aload 4
        //   530: invokespecial 173	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   533: invokespecial 176	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   536: astore 4
        //   538: aload 9
        //   540: invokevirtual 179	com/itextpdf/text/Document:open	()V
        //   543: aload 4
        //   545: astore 5
        //   547: aload 8
        //   549: invokeinterface 41 1 0
        //   554: istore_3
        //   555: iconst_0
        //   556: istore_2
        //   557: iload_2
        //   558: iload_3
        //   559: if_icmpge +785 -> 1344
        //   562: aload 8
        //   564: iload_2
        //   565: invokeinterface 47 2 0
        //   570: checkcast 56	java/lang/String
        //   573: bipush 14
        //   575: bipush 15
        //   577: invokevirtual 183	java/lang/String:substring	(II)Ljava/lang/String;
        //   580: invokestatic 189	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   583: tableswitch	default:+37->620, 0:+433->1016, 1:+441->1024, 2:+449->1032, 3:+457->1040, 4:+465->1048, 5:+473->1056
        //   620: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   623: astore 4
        //   625: new 165	com/itextpdf/text/Document
        //   628: dup
        //   629: aload 4
        //   631: invokespecial 192	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   634: astore 6
        //   636: aload_0
        //   637: aload 6
        //   639: new 170	java/io/FileOutputStream
        //   642: dup
        //   643: new 77	java/lang/StringBuilder
        //   646: dup
        //   647: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   650: aload_0
        //   651: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   654: invokestatic 195	com/simpleapp/tinyscanfree/Activity_Main:access$5100	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   657: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   660: aload 8
        //   662: iload_2
        //   663: invokeinterface 47 2 0
        //   668: checkcast 56	java/lang/String
        //   671: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   674: ldc 91
        //   676: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   682: invokespecial 196	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   685: invokestatic 202	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   688: putfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   691: aload 6
        //   693: invokevirtual 179	com/itextpdf/text/Document:open	()V
        //   696: aconst_null
        //   697: astore 4
        //   699: aload_0
        //   700: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   703: invokestatic 63	com/simpleapp/tinyscanfree/Activity_Main:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_Main;)I
        //   706: ifne +368 -> 1074
        //   709: new 77	java/lang/StringBuilder
        //   712: dup
        //   713: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   716: aload 7
        //   718: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   721: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   724: ldc 84
        //   726: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   729: aload 8
        //   731: iload_2
        //   732: invokeinterface 47 2 0
        //   737: checkcast 56	java/lang/String
        //   740: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   743: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   746: invokestatic 209	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   749: astore 4
        //   751: aload 4
        //   753: invokevirtual 213	com/itextpdf/text/Image:getWidth	()F
        //   756: aload 6
        //   758: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   761: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   764: fcmpl
        //   765: ifge +20 -> 785
        //   768: aload 4
        //   770: invokevirtual 223	com/itextpdf/text/Image:getHeight	()F
        //   773: aload 6
        //   775: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   778: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   781: fcmpl
        //   782: iflt +13 -> 795
        //   785: aload 4
        //   787: aload 6
        //   789: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   792: invokevirtual 227	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   795: aload 4
        //   797: aload 6
        //   799: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   802: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   805: aload 4
        //   807: invokevirtual 230	com/itextpdf/text/Image:getScaledWidth	()F
        //   810: fsub
        //   811: fconst_2
        //   812: fdiv
        //   813: aload 6
        //   815: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   818: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   821: aload 4
        //   823: invokevirtual 233	com/itextpdf/text/Image:getScaledHeight	()F
        //   826: fsub
        //   827: fconst_2
        //   828: fdiv
        //   829: invokevirtual 237	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   832: aload 6
        //   834: aload 4
        //   836: invokevirtual 240	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   839: pop
        //   840: aload_0
        //   841: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   844: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   847: ifeq +18 -> 865
        //   850: aload_0
        //   851: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   854: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   857: ifne +8 -> 865
        //   860: aload 6
        //   862: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   865: new 251	com/itextpdf/text/pdf/PdfReader
        //   868: dup
        //   869: new 77	java/lang/StringBuilder
        //   872: dup
        //   873: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   876: aload_0
        //   877: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   880: invokestatic 195	com/simpleapp/tinyscanfree/Activity_Main:access$5100	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   883: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   886: aload 8
        //   888: iload_2
        //   889: invokeinterface 47 2 0
        //   894: checkcast 56	java/lang/String
        //   897: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   900: ldc 91
        //   902: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   905: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   908: invokespecial 252	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   911: astore 4
        //   913: aload 5
        //   915: aload 4
        //   917: invokevirtual 256	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   920: aload 4
        //   922: invokevirtual 257	com/itextpdf/text/pdf/PdfReader:close	()V
        //   925: new 43	java/io/File
        //   928: dup
        //   929: new 77	java/lang/StringBuilder
        //   932: dup
        //   933: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   936: aload_0
        //   937: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   940: invokestatic 195	com/simpleapp/tinyscanfree/Activity_Main:access$5100	(Lcom/simpleapp/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   943: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   946: aload 8
        //   948: iload_2
        //   949: invokeinterface 47 2 0
        //   954: checkcast 56	java/lang/String
        //   957: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   960: ldc 91
        //   962: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   965: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   968: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   971: invokevirtual 75	java/io/File:delete	()Z
        //   974: pop
        //   975: iload_2
        //   976: iconst_1
        //   977: iadd
        //   978: istore_2
        //   979: goto -422 -> 557
        //   982: astore 5
        //   984: aload 5
        //   986: invokevirtual 260	java/lang/Exception:printStackTrace	()V
        //   989: goto -491 -> 498
        //   992: astore 4
        //   994: aload 4
        //   996: invokevirtual 261	java/io/FileNotFoundException:printStackTrace	()V
        //   999: goto -452 -> 547
        //   1002: astore 4
        //   1004: aload 6
        //   1006: astore 5
        //   1008: aload 4
        //   1010: invokevirtual 262	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1013: goto -466 -> 547
        //   1016: getstatic 265	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   1019: astore 4
        //   1021: goto -396 -> 625
        //   1024: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   1027: astore 4
        //   1029: goto -404 -> 625
        //   1032: getstatic 268	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   1035: astore 4
        //   1037: goto -412 -> 625
        //   1040: getstatic 271	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   1043: astore 4
        //   1045: goto -420 -> 625
        //   1048: getstatic 274	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   1051: astore 4
        //   1053: goto -428 -> 625
        //   1056: new 219	com/itextpdf/text/Rectangle
        //   1059: dup
        //   1060: ldc_w 275
        //   1063: ldc_w 276
        //   1066: invokespecial 278	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   1069: astore 4
        //   1071: goto -446 -> 625
        //   1074: new 280	java/io/FileInputStream
        //   1077: dup
        //   1078: new 43	java/io/File
        //   1081: dup
        //   1082: new 77	java/lang/StringBuilder
        //   1085: dup
        //   1086: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   1089: aload 7
        //   1091: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   1094: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1097: ldc 84
        //   1099: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1102: aload 8
        //   1104: iload_2
        //   1105: invokeinterface 47 2 0
        //   1110: checkcast 56	java/lang/String
        //   1113: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1116: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1119: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   1122: invokespecial 281	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   1125: invokestatic 287	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   1128: astore 10
        //   1130: new 289	java/io/ByteArrayOutputStream
        //   1133: dup
        //   1134: invokespecial 290	java/io/ByteArrayOutputStream:<init>	()V
        //   1137: astore 11
        //   1139: aload_0
        //   1140: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   1143: invokestatic 63	com/simpleapp/tinyscanfree/Activity_Main:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_Main;)I
        //   1146: iconst_1
        //   1147: if_icmpne +29 -> 1176
        //   1150: aload 10
        //   1152: getstatic 296	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1155: bipush 50
        //   1157: aload 11
        //   1159: invokevirtual 302	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1162: pop
        //   1163: aload 11
        //   1165: invokevirtual 306	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1168: invokestatic 309	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1171: astore 4
        //   1173: goto -422 -> 751
        //   1176: aload_0
        //   1177: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   1180: invokestatic 63	com/simpleapp/tinyscanfree/Activity_Main:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_Main;)I
        //   1183: iconst_2
        //   1184: if_icmpne -433 -> 751
        //   1187: aload 10
        //   1189: getstatic 296	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1192: iconst_5
        //   1193: aload 11
        //   1195: invokevirtual 302	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1198: pop
        //   1199: aload 11
        //   1201: invokevirtual 306	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1204: invokestatic 309	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1207: astore 4
        //   1209: goto -458 -> 751
        //   1212: astore 4
        //   1214: getstatic 313	java/lang/System:err	Ljava/io/PrintStream;
        //   1217: aload 4
        //   1219: invokevirtual 316	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1222: invokevirtual 321	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1225: aload_0
        //   1226: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1229: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1232: ifeq -367 -> 865
        //   1235: aload_0
        //   1236: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1239: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1242: ifne -377 -> 865
        //   1245: aload 6
        //   1247: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1250: goto -385 -> 865
        //   1253: astore 4
        //   1255: getstatic 313	java/lang/System:err	Ljava/io/PrintStream;
        //   1258: aload 4
        //   1260: invokevirtual 322	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1263: invokevirtual 321	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1266: aload_0
        //   1267: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1270: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1273: ifeq -408 -> 865
        //   1276: aload_0
        //   1277: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1280: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1283: ifne -418 -> 865
        //   1286: aload 6
        //   1288: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1291: goto -426 -> 865
        //   1294: astore 4
        //   1296: aload_0
        //   1297: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1300: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1303: ifeq +18 -> 1321
        //   1306: aload_0
        //   1307: getfield 204	com/simpleapp/tinyscanfree/Activity_Main$54:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1310: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1313: ifne +8 -> 1321
        //   1316: aload 6
        //   1318: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1321: aload 4
        //   1323: athrow
        //   1324: astore 4
        //   1326: aload 4
        //   1328: invokevirtual 323	java/io/IOException:printStackTrace	()V
        //   1331: goto -356 -> 975
        //   1334: astore 4
        //   1336: aload 4
        //   1338: invokevirtual 262	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1341: goto -366 -> 975
        //   1344: aload 9
        //   1346: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1349: iload_1
        //   1350: iconst_1
        //   1351: iadd
        //   1352: istore_1
        //   1353: goto -1351 -> 2
        //   1356: new 325	android/os/Message
        //   1359: dup
        //   1360: invokespecial 326	android/os/Message:<init>	()V
        //   1363: astore 4
        //   1365: aload 4
        //   1367: iconst_3
        //   1368: putfield 330	android/os/Message:what	I
        //   1371: aload_0
        //   1372: getfield 19	com/simpleapp/tinyscanfree/Activity_Main$54:this$0	Lcom/simpleapp/tinyscanfree/Activity_Main;
        //   1375: getfield 334	com/simpleapp/tinyscanfree/Activity_Main:handler	Landroid/os/Handler;
        //   1378: aload 4
        //   1380: invokevirtual 340	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1383: pop
        //   1384: return
        //   1385: astore 6
        //   1387: aload 4
        //   1389: astore 5
        //   1391: aload 6
        //   1393: astore 4
        //   1395: goto -387 -> 1008
        //   1398: astore 6
        //   1400: aload 4
        //   1402: astore 5
        //   1404: aload 6
        //   1406: astore 4
        //   1408: goto -414 -> 994
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1411	0	this	54
        //   1	1352	1	i	int
        //   214	891	2	j	int
        //   554	6	3	k	int
        //   77	844	4	localObject1	Object
        //   992	3	4	localFileNotFoundException1	java.io.FileNotFoundException
        //   1002	7	4	localDocumentException1	com.itextpdf.text.DocumentException
        //   1019	189	4	localObject2	Object
        //   1212	6	4	localDocumentException2	com.itextpdf.text.DocumentException
        //   1253	6	4	localIOException1	IOException
        //   1294	28	4	localObject3	Object
        //   1324	3	4	localIOException2	IOException
        //   1334	3	4	localDocumentException3	com.itextpdf.text.DocumentException
        //   1363	44	4	localObject4	Object
        //   197	717	5	localObject5	Object
        //   982	3	5	localException	Exception
        //   1006	397	5	localObject6	Object
        //   513	804	6	localDocument1	com.itextpdf.text.Document
        //   1385	7	6	localDocumentException4	com.itextpdf.text.DocumentException
        //   1398	7	6	localFileNotFoundException2	java.io.FileNotFoundException
        //   49	1041	7	localFile	File
        //   206	897	8	localArrayList	ArrayList
        //   510	835	9	localDocument2	com.itextpdf.text.Document
        //   1128	60	10	localBitmap	Bitmap
        //   1137	63	11	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   490	498	982	java/lang/Exception
        //   518	538	992	java/io/FileNotFoundException
        //   518	538	1002	com/itextpdf/text/DocumentException
        //   636	696	1212	com/itextpdf/text/DocumentException
        //   699	751	1212	com/itextpdf/text/DocumentException
        //   751	785	1212	com/itextpdf/text/DocumentException
        //   785	795	1212	com/itextpdf/text/DocumentException
        //   795	840	1212	com/itextpdf/text/DocumentException
        //   1074	1173	1212	com/itextpdf/text/DocumentException
        //   1176	1209	1212	com/itextpdf/text/DocumentException
        //   636	696	1253	java/io/IOException
        //   699	751	1253	java/io/IOException
        //   751	785	1253	java/io/IOException
        //   785	795	1253	java/io/IOException
        //   795	840	1253	java/io/IOException
        //   1074	1173	1253	java/io/IOException
        //   1176	1209	1253	java/io/IOException
        //   636	696	1294	finally
        //   699	751	1294	finally
        //   751	785	1294	finally
        //   785	795	1294	finally
        //   795	840	1294	finally
        //   1074	1173	1294	finally
        //   1176	1209	1294	finally
        //   1214	1225	1294	finally
        //   1255	1266	1294	finally
        //   865	975	1324	java/io/IOException
        //   865	975	1334	com/itextpdf/text/DocumentException
        //   538	543	1385	com/itextpdf/text/DocumentException
        //   538	543	1398	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  @SuppressLint({"DefaultLocale"})
  public boolean findAndGotoApp(String paramString, ArrayList<Uri> paramArrayList)
  {
    Object localObject = new Intent("android.intent.action.SEND_MULTIPLE");
    ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
    ((Intent)localObject).setType("application/pdf");
    ArrayList localArrayList = new ArrayList();
    localObject = getPackageManager().queryIntentActivities((Intent)localObject, 0);
    if (!((List)localObject).isEmpty())
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        localIntent.setType("application/pdf");
        localIntent.putExtra("android.intent.extra.SUBJECT", "Top Scanner");
        if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains(paramString)) || (localResolveInfo.activityInfo.name.toLowerCase().contains(paramString)))
        {
          localIntent.putExtra("android.intent.extra.STREAM", paramArrayList);
          localIntent.setPackage(localResolveInfo.activityInfo.packageName);
          localArrayList.add(localIntent);
        }
      }
      if (localArrayList.size() > 0)
      {
        paramString = Intent.createChooser((Intent)localArrayList.remove(0), "Export");
        paramString.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
        startActivityForResult(paramString, 3);
        return true;
      }
      return false;
    }
    return false;
  }
  
  public int findId(Photo_info paramPhoto_info)
  {
    Photo_info localPhoto_info;
    if (paramPhoto_info.isFolder())
    {
      i = 0;
      while (i < this.mlist2.size())
      {
        localPhoto_info = (Photo_info)this.mlist2.get(i);
        if ((localPhoto_info != null) && (localPhoto_info.getName().equals(paramPhoto_info.getName())) && (localPhoto_info.isFolder())) {
          return i;
        }
        i += 1;
      }
    }
    int i = 0;
    while (i < this.mlist2.size())
    {
      localPhoto_info = (Photo_info)this.mlist2.get(i);
      if ((localPhoto_info != null) && (localPhoto_info.getName().equals(paramPhoto_info.getName())) && (!localPhoto_info.isFolder())) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public Activity_Main getActivity_Main()
  {
    return activity_Main;
  }
  
  public ArrayList<Photo_info> getFolders(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mlist2.iterator();
    while (localIterator.hasNext())
    {
      Photo_info localPhoto_info = (Photo_info)localIterator.next();
      if ((localPhoto_info.isFolder()) && (!localPhoto_info.getShowname().equals(paramString))) {
        localArrayList.add(localPhoto_info);
      }
    }
    return localArrayList;
  }
  
  public Photo_info getInfo(Photo_info paramPhoto_info)
  {
    int j = this.mlist2.size();
    if (paramPhoto_info.isFolder())
    {
      i = 0;
      while (i < j)
      {
        if ((paramPhoto_info.getName().equals(((Photo_info)this.mlist2.get(i)).getName())) && (((Photo_info)this.mlist2.get(i)).isFolder())) {
          return (Photo_info)this.mlist2.get(i);
        }
        i += 1;
      }
    }
    int i = 0;
    while (i < j)
    {
      if ((paramPhoto_info.getName().equals(((Photo_info)this.mlist2.get(i)).getName())) && (!((Photo_info)this.mlist2.get(i)).isFolder())) {
        return (Photo_info)this.mlist2.get(i);
      }
      i += 1;
    }
    return null;
  }
  
  public int getPhotoNum(String paramString)
  {
    int i = 0;
    Iterator localIterator = this.mlist2.iterator();
    while (localIterator.hasNext())
    {
      Photo_info localPhoto_info = (Photo_info)localIterator.next();
      if (((!localPhoto_info.isFolder()) || (!localPhoto_info.getName().equals(paramString))) && (localPhoto_info.getImage_name() != null)) {
        i += localPhoto_info.getImage_name().size();
      }
    }
    return i;
  }
  
  @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
  public void getResults(String paramString)
  {
    if (paramString.equals("")) {
      list_folders();
    }
    do
    {
      for (;;)
      {
        return;
        this.mlist2.clear();
        File[] arrayOfFile = new File(this.root_Path3).listFiles();
        int k;
        int i;
        Object localObject2;
        Object localObject1;
        int j;
        if (arrayOfFile != null)
        {
          k = arrayOfFile.length;
          i = 0;
          for (;;)
          {
            if (i < k)
            {
              if ((arrayOfFile[i].isDirectory()) && (getShowName(arrayOfFile[i].getName()).toLowerCase().contains(paramString.toLowerCase())))
              {
                localObject2 = arrayOfFile[i].list();
                localObject1 = new ArrayList();
                if ((localObject2 != null) && (localObject2.length > 0))
                {
                  j = 0;
                  while (j < localObject2.length)
                  {
                    if (localObject2[j].matches("[0-9]{18}.jpg")) {
                      ((List)localObject1).add(localObject2[j]);
                    }
                    j += 1;
                  }
                  if (((List)localObject1).size() > 0)
                  {
                    localObject2 = Utils.getDate(new Date(arrayOfFile[i].lastModified()));
                    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                  }
                }
              }
              try
              {
                Collections.sort((List)localObject1, comparator3);
                ArrayList localArrayList1 = new ArrayList();
                localArrayList1.add(arrayOfFile[i].getPath() + "/" + (String)((List)localObject1).get(0));
                localObject1 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject2, arrayOfFile[i].lastModified(), ((List)localObject1).size(), localArrayList1, false, false);
                ((Photo_info)localObject1).setRotepath(this.root_Path3);
                this.mlist2.add(localObject1);
                i += 1;
              }
              catch (Exception localException1)
              {
                for (;;)
                {
                  localException1.printStackTrace();
                }
              }
            }
          }
        }
        arrayOfFile = new File(this.root_Path4).listFiles();
        if (arrayOfFile != null)
        {
          int m = arrayOfFile.length;
          i = 0;
          while (i < m)
          {
            if (arrayOfFile[i].isDirectory())
            {
              localObject1 = arrayOfFile[i].listFiles();
              if (localObject1 != null)
              {
                int n = localObject1.length;
                j = 0;
                for (;;)
                {
                  if (j < n)
                  {
                    localObject2 = localObject1[j];
                    Object localObject3;
                    ArrayList localArrayList2;
                    if ((((File)localObject2).isDirectory()) && (getShowName(((File)localObject2).getName()).toLowerCase().contains(paramString.toLowerCase())))
                    {
                      localObject3 = ((File)localObject2).list();
                      localArrayList2 = new ArrayList();
                      if (localObject3.length > 0)
                      {
                        k = 0;
                        while (k < localObject3.length)
                        {
                          if (localObject3[k].toLowerCase().matches("[0-9]{18}.jpg".toLowerCase())) {
                            localArrayList2.add(localObject3[k]);
                          }
                          k += 1;
                        }
                        if (localArrayList2.size() > 0)
                        {
                          localObject3 = Utils.getDate(new Date(((File)localObject2).lastModified()));
                          System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                        }
                      }
                    }
                    try
                    {
                      Collections.sort(localArrayList2, comparator3);
                      ArrayList localArrayList3 = new ArrayList();
                      localArrayList3.add(((File)localObject2).getPath() + "/" + (String)localArrayList2.get(0));
                      localObject2 = new Photo_info(((File)localObject2).getName(), getShowName(((File)localObject2).getName()), (String)localObject3, ((File)localObject2).lastModified(), localArrayList2.size(), localArrayList3, false, false);
                      ((Photo_info)localObject2).setRotepath(arrayOfFile[i].getPath() + "/");
                      this.mlist2.add(localObject2);
                      j += 1;
                    }
                    catch (Exception localException2)
                    {
                      for (;;)
                      {
                        localException2.printStackTrace();
                      }
                    }
                  }
                }
              }
            }
            i += 1;
          }
        }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        try
        {
          if (sort_type == 0) {
            Collections.sort(this.mlist2, comparator);
          }
          for (;;)
          {
            j = idlist.size();
            i = 0;
            while (i < j)
            {
              k = findId((Photo_info)idlist.get(i));
              if (k != -1) {
                ((Photo_info)this.mlist2.get(k)).setCheck(true);
              }
              i += 1;
            }
            Collections.sort(this.mlist2, comparator2);
          }
        }
        catch (Exception paramString)
        {
          for (;;)
          {
            paramString.printStackTrace();
          }
          if (this.list_type == 0)
          {
            if (this.madapter == null) {
              continue;
            }
            this.madapter.notifyDataSetChanged();
          }
        }
      }
    } while (this.madapter2 == null);
    this.madapter2.notifyDataSetChanged();
  }
  
  public String getShowName(String paramString)
  {
    Object localObject = new String[3];
    localObject[0] = "id";
    localObject[1] = "realname";
    localObject[2] = "showname";
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    if (this.db.isOpen())
    {
      localObject = this.db.query("NameMaps", (String[])localObject, "realname = ?", arrayOfString, null, null, "id DESC");
      if (!((Cursor)localObject).moveToFirst()) {
        break label131;
      }
      paramString = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("showname"));
    }
    label131:
    for (;;)
    {
      ((Cursor)localObject).close();
      return paramString;
      this.db = this.mDbHelper.getWritableDatabase();
      localObject = this.db.query("NameMaps", (String[])localObject, "realname = ?", arrayOfString, null, null, "id DESC");
      break;
    }
  }
  
  public boolean isEmail(String paramString)
  {
    return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(paramString).matches();
  }
  
  @SuppressLint({"InflateParams"})
  public void list_by_grid()
  {
    this.listphotos.removeAllViews();
    View localView = this.inflater.inflate(2130903098, null);
    this.listphotos.addView(localView);
    this.grid = ((GridView)localView.findViewById(2131624290));
    if (this.mapp.isPad()) {
      if (getResources().getConfiguration().orientation == 1)
      {
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
        this.grid.setNumColumns(3);
      }
    }
    for (;;)
    {
      this.grid.setSelector(new ColorDrawable(0));
      if (this.madapter == null) {
        this.madapter = new GridAdapter(this.context, this.mlist2);
      }
      this.grid.setAdapter(this.madapter);
      this.grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
          if (paramAnonymousView.isFolder())
          {
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            if (!paramAnonymousAdapterView.exists()) {
              break label977;
            }
            if (!Activity_Main.this.islongclick) {
              break label593;
            }
            if (Activity_Main.this.isSearch)
            {
              Activity_Main.access$2102(Activity_Main.this, true);
              Activity_Main.this.clear2();
            }
            Log.i("TAG", "000000000000000000000================");
            if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
              break label448;
            }
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
            if (!Activity_Main.idlist.isEmpty()) {
              break label438;
            }
            if (!Activity_Main.this.searchandclick) {
              break label428;
            }
            Activity_Main.this.unselected2();
            label218:
            Activity_Main.access$2202(Activity_Main.this, false);
            Activity_Main.this.madapter.isse = false;
          }
          for (;;)
          {
            Activity_Main.this.madapter.notifyDataSetChanged();
            if (Activity_Main.idlist.size() != Activity_Main.this.mlist2.size()) {
              Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165561));
            }
            Activity_Main.this.changeView();
            return;
            if (Activity_Main.this.isSearch)
            {
              paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
              break;
            }
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
            label428:
            Activity_Main.this.unselected();
            break label218;
            label438:
            Activity_Main.this.selected();
          }
          label448:
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter.isse = true;
          if (Activity_Main.this.list_type == 0) {
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
          for (;;)
          {
            paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
            Activity_Main.idlist.add(paramAnonymousAdapterView);
            Activity_Main.this.selected();
            if (Activity_Main.idlist.size() != Activity_Main.this.mlist2.size()) {
              break;
            }
            Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165291));
            break;
            Activity_Main.this.madapter2.notifyDataSetChanged();
          }
          label593:
          if (paramAnonymousView.isFolder())
          {
            paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_FolderFile.class);
            paramAnonymousAdapterView.setFlags(67108864);
            paramAnonymousAdapterView.putExtra("folder_path", Activity_Main.this.root_Path4 + paramAnonymousView.getName());
            paramAnonymousAdapterView.putExtra("folder_name", paramAnonymousView.getShowname());
            paramAnonymousAdapterView.putExtra("list_type", Activity_Main.this.list_type);
            paramAnonymousAdapterView.putExtra("sort_type", Activity_Main.sort_type);
            Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path4 + paramAnonymousView.getName() + "/");
            Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
            Activity_Main.this.editor.commit();
            Activity_Main.this.startActivity(paramAnonymousAdapterView);
            return;
          }
          paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_SelectFile.class);
          paramAnonymousAdapterView.setFlags(67108864);
          Activity_Main.this.editor.putString("folder_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getShowname());
          Activity_Main.this.editor.putString("folder_root_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath());
          Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
          Activity_Main.this.editor.commit();
          Activity_Main.this.startActivity(paramAnonymousAdapterView);
          return;
          label977:
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165331), 0).show();
          Activity_Main.this.mlist2.remove(paramAnonymousInt);
          if (Activity_Main.this.list_type == 0)
          {
            Activity_Main.this.madapter.notifyDataSetChanged();
            return;
          }
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
      });
      this.grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isFolder())
          {
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            if (!paramAnonymousAdapterView.exists()) {
              break label495;
            }
            if (Activity_Main.this.isSearch)
            {
              Activity_Main.access$2102(Activity_Main.this, true);
              Activity_Main.this.clear2();
            }
            Activity_Main.access$2202(Activity_Main.this, true);
            if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
              break label394;
            }
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
            if (!Activity_Main.idlist.isEmpty()) {
              break label384;
            }
            if (!Activity_Main.this.searchandclick) {
              break label374;
            }
            Activity_Main.this.unselected2();
            label207:
            Activity_Main.access$2202(Activity_Main.this, false);
            Activity_Main.this.madapter.isse = false;
          }
          for (;;)
          {
            Activity_Main.this.madapter.notifyDataSetChanged();
            Activity_Main.this.changeView();
            return true;
            if (Activity_Main.this.isSearch)
            {
              paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
              break;
            }
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
            label374:
            Activity_Main.this.unselected();
            break label207;
            label384:
            Activity_Main.this.selected();
          }
          label394:
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter.isse = true;
          if (Activity_Main.this.list_type == 0) {
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
          for (;;)
          {
            paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
            Activity_Main.idlist.add(paramAnonymousAdapterView);
            Activity_Main.this.selected();
            break;
            Activity_Main.this.madapter2.notifyDataSetChanged();
          }
          label495:
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165331), 0).show();
          Activity_Main.this.mlist2.remove(paramAnonymousInt);
          if (Activity_Main.this.list_type == 0)
          {
            Activity_Main.this.madapter.notifyDataSetChanged();
            return true;
          }
          Activity_Main.this.madapter2.notifyDataSetChanged();
          return true;
        }
      });
      return;
      if (getResources().getConfiguration().orientation == 2)
      {
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
        this.grid.setNumColumns(5);
        continue;
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(45.0F)) / 2);
        this.grid.setNumColumns(2);
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  public void list_by_list()
  {
    this.listphotos.removeAllViews();
    Object localObject = this.inflater.inflate(2130903099, null);
    this.listphotos.addView((View)localObject);
    localObject = (ListView)((View)localObject).findViewById(2131624291);
    if (this.madapter2 == null) {
      this.madapter2 = new ListAdapter(this.context, this.mlist2);
    }
    ((ListView)localObject).setAdapter(this.madapter2);
    ((ListView)localObject).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
        if (paramAnonymousView.isFolder())
        {
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          if (!paramAnonymousAdapterView.exists()) {
            break label949;
          }
          if (!Activity_Main.this.islongclick) {
            break label619;
          }
          if (Activity_Main.this.isSearch)
          {
            Activity_Main.access$2102(Activity_Main.this, true);
            Activity_Main.this.clear2();
          }
          if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
            break label474;
          }
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
          if (!Activity_Main.idlist.isEmpty()) {
            break label440;
          }
          if (!Activity_Main.this.searchandclick) {
            break label430;
          }
          Activity_Main.this.unselected2();
          label210:
          Activity_Main.access$2202(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label230:
          if (Activity_Main.this.list_type != 0) {
            break label461;
          }
          Activity_Main.this.madapter.notifyDataSetChanged();
        }
        for (;;)
        {
          if (Activity_Main.idlist.size() != Activity_Main.this.mlist2.size()) {
            Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165561));
          }
          Activity_Main.this.changeView();
          return;
          if (Activity_Main.this.isSearch)
          {
            paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
          }
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          break;
          label430:
          Activity_Main.this.unselected();
          break label210;
          label440:
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.selected();
          break label230;
          label461:
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        label474:
        ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
        Activity_Main.this.madapter2.isse = true;
        if (Activity_Main.this.list_type == 0) {
          Activity_Main.this.madapter.notifyDataSetChanged();
        }
        for (;;)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
          Activity_Main.idlist.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
          if (Activity_Main.idlist.size() != Activity_Main.this.mlist2.size()) {
            break;
          }
          Activity_Main.this.main_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165291));
          break;
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        label619:
        if (paramAnonymousView.isFolder())
        {
          paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_FolderFile.class);
          paramAnonymousAdapterView.setFlags(67108864);
          paramAnonymousAdapterView.putExtra("folder_path", Activity_Main.this.root_Path4 + paramAnonymousView.getName());
          paramAnonymousAdapterView.putExtra("folder_name", paramAnonymousView.getShowname());
          paramAnonymousAdapterView.putExtra("list_type", Activity_Main.this.list_type);
          paramAnonymousAdapterView.putExtra("sort_type", Activity_Main.sort_type);
          Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path4 + paramAnonymousView.getName() + "/");
          Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
          Activity_Main.this.editor.commit();
          Activity_Main.this.startActivity(paramAnonymousAdapterView);
          return;
        }
        paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_SelectFile.class);
        paramAnonymousAdapterView.setFlags(67108864);
        Activity_Main.this.editor.putString("document_path", Activity_Main.this.root_Path3 + paramAnonymousView.getName());
        Activity_Main.this.editor.putString("document_name", paramAnonymousView.getShowname());
        Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path3);
        Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
        Activity_Main.this.editor.commit();
        Activity_Main.this.startActivity(paramAnonymousAdapterView);
        return;
        label949:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165331), 0).show();
        Activity_Main.this.mlist2.remove(paramAnonymousInt);
        if (Activity_Main.this.list_type == 0)
        {
          Activity_Main.this.madapter.notifyDataSetChanged();
          return;
        }
        Activity_Main.this.madapter2.notifyDataSetChanged();
      }
    });
    ((ListView)localObject).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isFolder())
        {
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          if (!paramAnonymousAdapterView.exists()) {
            break label529;
          }
          if (Activity_Main.this.isSearch)
          {
            Activity_Main.access$2102(Activity_Main.this, true);
            Activity_Main.this.clear2();
          }
          Activity_Main.access$2202(Activity_Main.this, true);
          if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
            break label428;
          }
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
          if (!Activity_Main.idlist.isEmpty()) {
            break label394;
          }
          if (!Activity_Main.this.searchandclick) {
            break label384;
          }
          Activity_Main.this.unselected2();
          label207:
          Activity_Main.access$2202(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label227:
          if (Activity_Main.this.list_type != 0) {
            break label415;
          }
          Activity_Main.this.madapter.notifyDataSetChanged();
        }
        for (;;)
        {
          Activity_Main.this.changeView();
          return true;
          if (Activity_Main.this.isSearch)
          {
            paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
          }
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          break;
          label384:
          Activity_Main.this.unselected();
          break label207;
          label394:
          Activity_Main.this.selected();
          Activity_Main.this.madapter2.isse = true;
          break label227;
          label415:
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        label428:
        ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
        Activity_Main.this.madapter2.isse = true;
        if (Activity_Main.this.list_type == 0) {
          Activity_Main.this.madapter.notifyDataSetChanged();
        }
        for (;;)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
          Activity_Main.idlist.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
          break;
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        label529:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131165331), 0).show();
        Activity_Main.this.mlist2.remove(paramAnonymousInt);
        if (Activity_Main.this.list_type == 0)
        {
          Activity_Main.this.madapter.notifyDataSetChanged();
          return true;
        }
        Activity_Main.this.madapter2.notifyDataSetChanged();
        return true;
      }
    });
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public void list_folders()
  {
    this.mlist2.clear();
    this.list_type = this.preferences.getInt("list_type", 0);
    sort_type = this.preferences.getInt("sort_type", 0);
    File[] arrayOfFile;
    int k;
    int i;
    Object localObject1;
    int j;
    Object localObject3;
    if ((new File(this.root_Path3).isDirectory()) && (new File(this.root_Path4).isDirectory()))
    {
      arrayOfFile = new File(this.root_Path3).listFiles();
      if (arrayOfFile != null)
      {
        k = arrayOfFile.length;
        i = 0;
        for (;;)
        {
          if (i < k)
          {
            Object localObject2;
            if (arrayOfFile[i].isDirectory())
            {
              localObject2 = arrayOfFile[i].list();
              localObject1 = new ArrayList();
              if ((localObject2 != null) && (localObject2.length > 0))
              {
                j = 0;
                while (j < localObject2.length)
                {
                  if (localObject2[j].matches("[0-9]{18}.jpg")) {
                    ((List)localObject1).add(localObject2[j]);
                  }
                  j += 1;
                }
                if (((List)localObject1).size() > 0)
                {
                  localObject2 = Utils.getDate(new Date(arrayOfFile[i].lastModified()));
                  System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                }
              }
            }
            try
            {
              Collections.sort((List)localObject1, comparator3);
              ArrayList localArrayList2 = new ArrayList();
              localArrayList2.add(arrayOfFile[i].getPath() + "/" + (String)((List)localObject1).get(0));
              localObject1 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject2, arrayOfFile[i].lastModified(), ((List)localObject1).size(), localArrayList2, false, false);
              ((Photo_info)localObject1).setRotepath(this.root_Path3);
              this.mlist2.add(localObject1);
              i += 1;
            }
            catch (Exception localException3)
            {
              for (;;)
              {
                localException3.printStackTrace();
              }
            }
          }
        }
      }
      arrayOfFile = new File(this.root_Path4).listFiles();
      if (arrayOfFile != null)
      {
        int m = arrayOfFile.length;
        i = 0;
        if (i < m) {
          if (arrayOfFile[i].isDirectory())
          {
            localObject1 = arrayOfFile[i].listFiles();
            localObject3 = new ArrayList();
            k = localObject1.length;
            j = 0;
          }
        }
      }
    }
    for (;;)
    {
      if (j < k)
      {
        ((ArrayList)localObject3).add(localObject1[j]);
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        try
        {
          if (sort_type == 0) {
            Collections.sort((List)localObject3, comparator4);
          } else {
            Collections.sort((List)localObject3, comparator5);
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
      }
      localObject1 = Utils.getDate(new Date(arrayOfFile[i].lastModified()));
      ArrayList localArrayList1 = new ArrayList();
      j = 0;
      localObject3 = ((ArrayList)localObject3).iterator();
      for (;;)
      {
        if (((Iterator)localObject3).hasNext())
        {
          File localFile = (File)((Iterator)localObject3).next();
          if (!localFile.isDirectory()) {
            continue;
          }
          String[] arrayOfString = localFile.list();
          ArrayList localArrayList3 = new ArrayList();
          k = j;
          if (arrayOfString != null)
          {
            k = j;
            if (arrayOfString.length > 0)
            {
              k = 0;
              while (k < arrayOfString.length)
              {
                if (arrayOfString[k].matches("[0-9]{18}.jpg")) {
                  localArrayList3.add(arrayOfString[k]);
                }
                k += 1;
              }
              k = j;
              if (localArrayList3.size() > 0) {
                System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
              }
            }
          }
          try
          {
            Collections.sort(localArrayList3, comparator3);
            localArrayList1.add(localFile.getPath() + "/" + (String)localArrayList3.get(0));
            k = j + 1;
            j = k;
          }
          catch (Exception localException4)
          {
            for (;;)
            {
              localException4.printStackTrace();
            }
          }
        }
      }
      localObject1 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject1, arrayOfFile[i].lastModified(), j, localArrayList1, false, true);
      this.mlist2.add(localObject1);
      i += 1;
      break;
      try
      {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        if (sort_type == 0) {
          Collections.sort(this.mlist2, comparator);
        }
        for (;;)
        {
          j = idlist.size();
          i = 0;
          while (i < j)
          {
            k = findId((Photo_info)idlist.get(i));
            if (k != -1) {
              ((Photo_info)this.mlist2.get(k)).setCheck(true);
            }
            i += 1;
          }
          Collections.sort(this.mlist2, comparator2);
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localException1.printStackTrace();
        }
        if (this.list_type == 0)
        {
          list_by_grid();
          return;
        }
        list_by_list();
        return;
      }
      Toast.makeText(this.context, getResources().getString(2131165521), 0).show();
      return;
      j += 1;
    }
  }
  
  public void makefolder()
  {
    File[] arrayOfFile = ContextCompat.getExternalFilesDirs(activity_Main, null);
    Log.i("TAG", "size====" + arrayOfFile.length);
    if (arrayOfFile.length != this.mapp.getSdcard_list().size())
    {
      int i = 0;
      while (i < this.mapp.getSdcard_list().size())
      {
        if (i + 1 > arrayOfFile.length) {
          this.mapp.getSdcard_list().remove(i);
        }
        i += 1;
      }
    }
    if ((this.preferences.getInt("SDCARD_PATH", 0) > 0) && (this.mapp.getSdcard_list().size() > 1))
    {
      this.compressJpeg_Path = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Jpeg/");
      this.root_Path = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/");
      this.root_Path3 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/Documents/");
      this.root_Path4 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/Folders/");
      this.root_Path2 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/");
      this.root_Path5 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Documents/");
    }
    for (this.root_Path6 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Folders/");; this.root_Path6 = (Environment.getExternalStorageDirectory() + "/TopScanner/temporary/Folders/"))
    {
      new File(this.root_Path3).mkdirs();
      new File(this.root_Path4).mkdirs();
      new File(this.root_Path2).mkdirs();
      new File(this.root_Path + ".nomedia").mkdirs();
      new File(this.root_Path2 + ".nomedia").mkdirs();
      return;
      this.compressJpeg_Path = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Jpeg/");
      this.root_Path = (Environment.getExternalStorageDirectory() + "/TopScanner/");
      this.root_Path3 = (Environment.getExternalStorageDirectory() + "/TopScanner/Documents/");
      this.root_Path4 = (Environment.getExternalStorageDirectory() + "/TopScanner/Folders/");
      this.root_Path2 = (Environment.getExternalStorageDirectory() + "/TopScanner/temporary/");
      this.root_Path5 = (Environment.getExternalStorageDirectory() + "/TopScanner/temporary/Documents/");
    }
  }
  
  @SuppressLint({"InflateParams"})
  protected void moveDocment()
  {
    int i = 0;
    Object localObject1 = idlist.iterator();
    while (((Iterator)localObject1).hasNext()) {
      if (((Photo_info)((Iterator)localObject1).next()).isFolder()) {
        i = 1;
      }
    }
    if (i != 0)
    {
      showToast(getResources().getString(2131165463));
      return;
    }
    Object localObject2 = getLayoutInflater().inflate(2130903086, null);
    localObject1 = new ArrayList();
    Object localObject3 = this.mlist2.iterator();
    while (((Iterator)localObject3).hasNext())
    {
      Photo_info localPhoto_info = (Photo_info)((Iterator)localObject3).next();
      if (localPhoto_info.isFolder()) {
        ((ArrayList)localObject1).add(localPhoto_info);
      }
    }
    localObject3 = new Photo_info();
    ((Photo_info)localObject3).setShowname(getResources().getString(2131165439));
    ((ArrayList)localObject1).add(localObject3);
    localObject3 = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131165406)).setView((View)localObject2).setNegativeButton(getResources().getString(2131165274), null).create();
    ((AlertDialog)localObject3).show();
    localObject2 = (ListView)((View)localObject2).findViewById(2131624256);
    ((ListView)localObject2).setAdapter(new CopyAdapter(this.context, (ArrayList)localObject1, false));
    ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
      {
        this.val$mDialog.dismiss();
        if (paramAnonymousInt == this.val$sslist2.size() - 1)
        {
          Activity_Main.this.showFolderDialog(true);
          return;
        }
        new Thread(new Runnable()
        {
          public void run()
          {
            Iterator localIterator = Activity_Main.idlist.iterator();
            if (localIterator.hasNext())
            {
              Photo_info localPhoto_info = (Photo_info)localIterator.next();
              BitmapDrawable localBitmapDrawable = Activity_Main.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
              Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
              if (Activity_Main.this.isSearch) {}
              int k;
              int i;
              for (File localFile = new File(localPhoto_info.getRotepath() + localPhoto_info.getName());; localFile = new File(Activity_Main.this.root_Path3 + localPhoto_info.getName()))
              {
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName()).listFiles();
                k = 0;
                i = 0;
                if (localObject1 == null) {
                  break;
                }
                int m = localObject1.length;
                int j = 0;
                for (;;)
                {
                  k = i;
                  if (j >= m) {
                    break;
                  }
                  Object localObject2 = localObject1[j];
                  if (localPhoto_info.getShowname().equals(Activity_Main.this.getShowName(localObject2.getName()))) {
                    i = 1;
                  }
                  j += 1;
                }
              }
              if (k != 0) {
                if (Activity_Main.this.checkName(localPhoto_info.getShowname()))
                {
                  i = 2;
                  for (localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + 2 + ")"); ((File)localObject1).exists(); localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + i + ")")) {
                    i += 1;
                  }
                  localFile.renameTo((File)localObject1);
                }
              }
              for (;;)
              {
                Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + ((File)localObject1).getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
                break;
                Activity_Main.this.updateNameToDb(localPhoto_info.getName(), localPhoto_info.getShowname() + "(2)");
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
                localFile.renameTo((File)localObject1);
                continue;
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.30.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
                localFile.renameTo((File)localObject1);
              }
            }
            Object localObject1 = new Message();
            ((Message)localObject1).what = 21;
            Activity_Main.this.handler.sendMessage((Message)localObject1);
          }
        }).start();
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    do
    {
      for (;;)
      {
        return;
        if ((paramInt2 != 0) && (paramInt2 == 1))
        {
          Toast.makeText(this.context, getResources().getString(2131165273), 0).show();
          return;
          onAuthenticated(paramInt2, paramIntent);
          return;
          onFolderSelected(paramInt2, paramIntent);
          return;
          if (paramInt2 == -1)
          {
            String str;
            try
            {
              str = getRealPathFromURI(paramIntent.getData());
              this.mapp.setPhotoUri(paramIntent.getData());
              this.mapp.setPhotopath(str);
              this.editor.putBoolean("where", false);
              this.editor.commit();
              this.mapp.setPhotofrom(false);
              this.mapp.setSavePath(this.root_Path3);
              startActivity(new Intent(this.context, Activity_Detect.class));
              return;
            }
            catch (Exception paramIntent)
            {
              paramIntent.printStackTrace();
              return;
            }
            if ((paramInt2 == -1) && (paramIntent != null)) {
              try
              {
                if (paramIntent.getData() != null)
                {
                  str = Utils.getRealFilePath(activity_Main, paramIntent.getData());
                  this.mapp.setPhotoUri(paramIntent.getData());
                  this.mapp.setPhotopath(str);
                  this.editor.putBoolean("where", false);
                  this.editor.commit();
                  this.mapp.setPhotofrom(false);
                  startActivity(new Intent(this.context, Activity_Detect.class));
                  return;
                }
              }
              catch (Exception paramIntent)
              {
                paramIntent.printStackTrace();
                return;
              }
            }
          }
        }
      }
    } while ((paramInt2 != -1) || (paramInt1 != 10001));
    paramIntent = getSharedPreferences("TopScanner", 0).edit();
    paramIntent.putBoolean("GOOGLE_IAP", true);
    this.mapp.setIsBuyGoogleAds(true);
    this.mapp.setAdvOrChargeOrNormal(3);
    this.mapp.setIAPBuyVersion(1);
    paramIntent.commit();
    paramIntent = new Message();
    paramIntent.what = 10020;
    this.handler.sendMessage(paramIntent);
    thankBuy("Thank you for upgrading to pro! ");
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mapp.isPad())
    {
      if (paramConfiguration.orientation != 1) {
        break label106;
      }
      paramConfiguration = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
      this.mapp.setDisplaywidth(paramConfiguration.widthPixels);
      this.mapp.setDispalyheight(paramConfiguration.heightPixels);
      if (this.grid != null)
      {
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
        this.grid.setNumColumns(3);
      }
    }
    label106:
    do
    {
      do
      {
        return;
      } while (paramConfiguration.orientation != 2);
      paramConfiguration = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
      this.mapp.setDisplaywidth(paramConfiguration.widthPixels);
      this.mapp.setDispalyheight(paramConfiguration.heightPixels);
    } while (this.grid == null);
    this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
    this.grid.setNumColumns(5);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    MyApplication.activityList.add(this);
    this.context = this;
    this.mapp = MyApplication.getApplication(this.context);
    activity_Main = this;
    this.mApp = MyApp.getApp();
    this.mapp.setPad(isPad());
    this.mApp = MyApp.getApp();
    if (this.mapp.isPad())
    {
      setContentView(2130903076);
      this.imagedata = new Integer[] { Integer.valueOf(2130837691), Integer.valueOf(2130837691), Integer.valueOf(2130837691), Integer.valueOf(2130837691) };
      if (!this.mapp.isPad()) {
        break label745;
      }
      this.viewsortStr = new String[] { activity_Main.getResources().getString(2131165287), activity_Main.getResources().getString(2131165334) };
      label173:
      paramBundle = new DisplayMetrics();
      activity_Main.getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      this.mapp.setDensityDpi(paramBundle.densityDpi);
      this.mDbHelper = MyDbHelper.getHelper(this.context);
      this.db = this.mDbHelper.getWritableDatabase();
      paramBundle = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      this.mapp.setDisplaywidth(paramBundle.widthPixels);
      this.mapp.setDispalyheight(paramBundle.heightPixels);
      idlist = new ArrayList();
      this.mlist2 = new ArrayList();
      this.preferences = getSharedPreferences("TopScanner", 0);
      this.editor = this.preferences.edit();
      this.times = this.preferences.getInt("times", 0);
      this.isClickReminderMeLater = this.preferences.getBoolean("ISCLICKREMINDERMELATER", false);
      this.inAndOutTimes = this.preferences.getInt("INANDOUTTIMES", 1);
      this.initNotnowTimes = this.preferences.getInt("reset_nitnow1.3.1", 1);
      this.newuser_isnotnow = this.preferences.getBoolean("newuser_isnotnow", false);
      this.editor.putInt("times", this.times + 1);
      this.editor.commit();
      System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
      paramBundle = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      if (Math.sqrt(Math.pow(paramBundle.widthPixels / paramBundle.xdpi, 2.0D) + Math.pow(paramBundle.heightPixels / paramBundle.ydpi, 2.0D)) >= 8.2D) {
        break label816;
      }
    }
    label745:
    label816:
    for (com.simpleapp.db.AppDate.isSpecial = true;; com.simpleapp.db.AppDate.isSpecial = false)
    {
      if (this.times == 0)
      {
        this.editor.putInt("newUser_1.3", 1);
        this.editor.putInt("newUser_1.0", 1);
        this.editor.commit();
      }
      initView();
      this.main_relativelayout_ads = ((RelativeLayout)findViewById(2131624152));
      if (this.preferences.getInt("newUser_1.3", -1) == 1) {
        AdsUtils.showAds(activity_Main, this.main_relativelayout_ads, this.mapp, 1);
      }
      this.mapp.setUpdate(false);
      if (this.isClickReminderMeLater)
      {
        this.editor.putInt("INANDOUTTIMES", this.inAndOutTimes + 1);
        this.editor.commit();
      }
      if (this.newuser_isnotnow)
      {
        this.editor.putInt("reset_nitnow1.3.1", this.initNotnowTimes + 1);
        this.editor.commit();
      }
      if (Build.VERSION.SDK_INT >= 23)
      {
        if ((ContextCompat.checkSelfPermission(activity_Main, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) || (ContextCompat.checkSelfPermission(activity_Main, "android.permission.READ_EXTERNAL_STORAGE") != 0) || (ContextCompat.checkSelfPermission(activity_Main, "android.permission.CAMERA") != 0)) {
          break label823;
        }
        this.isrequestCheck = true;
      }
      return;
      setRequestedOrientation(1);
      break;
      this.viewsortStr = new String[] { activity_Main.getResources().getString(2131165347), activity_Main.getResources().getString(2131165391), activity_Main.getResources().getString(2131165287), activity_Main.getResources().getString(2131165334) };
      break label173;
    }
    label823:
    requstPermisstion();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.mapp.clearMemCache();
    this.mapp.clearReuseData();
    this.mApp.clearData();
    this.editor.putInt("folder_id", 0);
    this.editor.commit();
    if (this.db != null) {
      this.db.close();
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      Log.i("TAG", "onkey");
      if (this.searchandclick)
      {
        this.searchandclick = false;
        unselected2();
        return true;
      }
      if (this.isSelect)
      {
        unselected();
        return true;
      }
      if (this.isSearch)
      {
        clear();
        return true;
      }
      if ((this.preferences.getInt("newUser_1.3", -1) == 1) && (this.is_once_ads_chaye))
      {
        if (this.preferences.getInt("times", 0) >= 2)
        {
          this.is_once_ads_chaye = false;
          AdsUtils.showInterstitial(activity_Main, 0);
          return true;
        }
        this.editor.putInt("folder_id", 0);
        this.editor.commit();
        this.mapp.clearMemCache();
        paramKeyEvent = new Intent();
        paramKeyEvent.setAction("ExitLogin");
        sendBroadcast(paramKeyEvent);
        this.mapp.exit();
        finish();
        return true;
      }
      this.editor.putInt("folder_id", 0);
      this.editor.commit();
      this.mapp.clearMemCache();
      paramKeyEvent = new Intent();
      paramKeyEvent.setAction("ExitLogin");
      sendBroadcast(paramKeyEvent);
      this.mapp.exit();
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    if ((this.popupWindow != null) && (this.popupWindow.isShowing())) {
      this.popupWindow.dismiss();
    }
    this.popupWindow = null;
  }
  
  @SuppressLint({"NewApi"})
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if ((paramInt == 0) && (hasAllPermissionGranted(paramArrayOfInt)))
    {
      this.isrequestCheck = true;
      return;
    }
    this.isrequestCheck = false;
    if ((!shouldShowRequestPermissionRationale("android.permission.CAMERA")) && (!shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")))
    {
      showMissingPermissionDialog();
      return;
    }
    showCancelPermissionDialog();
  }
  
  protected void onResume()
  {
    super.onResume();
    MultipleVersionslimit();
    if (OpenCVLoader.initDebug()) {
      this.mLoaderCallback.onManagerConnected(0);
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (this.isrequestCheck) {
        writeSDcardFile();
      }
      return;
    }
    writeSDcardFile();
  }
  
  protected void onStart()
  {
    super.onStart();
    switch (this.preferences.getInt("login", 0))
    {
    default: 
      return;
    case 1: 
      FlurryAgent.logEvent("5_A");
      return;
    case 2: 
      FlurryAgent.logEvent("5_B");
      return;
    case 4: 
      FlurryAgent.logEvent("5_C");
      return;
    case 6: 
      FlurryAgent.logEvent("5_D");
      return;
    case 11: 
      FlurryAgent.logEvent("5_E");
      return;
    case 21: 
      FlurryAgent.logEvent("5_F");
      return;
    }
    FlurryAgent.logEvent("5_G");
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void removeid(Photo_info paramPhoto_info)
  {
    int j = idlist.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (paramPhoto_info.getName().equals(((Photo_info)idlist.get(i)).getName())) {
          idlist.remove(i);
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  public void saveNameToDb(String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("realname", paramString1);
    localContentValues.put("showname", paramString2);
    this.db.insert("NameMaps", "id", localContentValues);
  }
  
  public void search()
  {
    this.isSearch = true;
    this.more.setVisibility(8);
    this.main_imagesearch.setVisibility(8);
    if (this.mapp.isPad())
    {
      this.main_importfromgallery.setVisibility(8);
      this.main_newfolder.setVisibility(8);
      this.main_viewsortby.setVisibility(8);
    }
    this.back.setVisibility(0);
    this.search_layout.setVisibility(0);
    this.main_appname_textview.setVisibility(8);
    this.search_text.requestFocus();
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)Activity_Main.this.getSystemService("input_method")).toggleSoftInput(1, 2);
      }
    }, 200L);
  }
  
  public void search2()
  {
    this.back.setVisibility(0);
    this.search_text.setText(this.search_text.getText().toString());
    this.search_layout.setVisibility(0);
    this.search_text.requestFocus();
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 2);
  }
  
  public void selected()
  {
    this.isSelect = true;
    this.main_selecttextview.setVisibility(0);
    this.main_selectall.setVisibility(0);
    this.selecttext.setVisibility(0);
    this.selecttext.setText("" + idlist.size());
    this.camera.setVisibility(4);
    this.main_appname_textview.setVisibility(8);
    this.back.setVisibility(0);
    this.main_imagesearch.setVisibility(8);
    this.more.setVisibility(8);
    this.main_onlongclick_layout.setVisibility(0);
    if (this.mapp.isPad())
    {
      this.main_importfromgallery.setVisibility(8);
      this.main_newfolder.setVisibility(8);
      this.main_viewsortby.setVisibility(8);
    }
  }
  
  public void setActivity_Main(Activity_Main paramActivity_Main)
  {
    activity_Main = paramActivity_Main;
  }
  
  protected void shareDocment()
  {
    if (this.pdf_OR_jpeg == 1)
    {
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      if (this.export_size == 0)
      {
        localObject3 = new MyFilter2();
        i = 0;
        if (i < idlist.size())
        {
          if (((Photo_info)idlist.get(i)).isFolder())
          {
            localObject1 = new File(this.root_Path4 + ((Photo_info)idlist.get(i)).getName()).listFiles();
            if (localObject1 != null)
            {
              k = localObject1.length;
              j = 0;
              if (j < k)
              {
                localObject4 = localObject1[j];
                localObject6 = localObject4.listFiles((FilenameFilter)localObject3);
                if ((localObject6 != null) && (localObject6.length > 0)) {
                  localObject6 = new File(localObject4.getPath() + "/" + localObject4.getName() + ".pdf");
                }
                for (;;)
                {
                  try
                  {
                    if ((Util.getFileSize((File)localObject6) == 0L) || (!Util.isOpenPdf(localObject4.getPath() + "/" + localObject4.getName() + ".pdf")))
                    {
                      localObject6 = new HashMap();
                      ((HashMap)localObject6).put("name", localObject4.getName());
                      ((HashMap)localObject6).put("isfolder", Boolean.valueOf(true));
                      ((HashMap)localObject6).put("folder", ((Photo_info)idlist.get(i)).getName());
                      ((HashMap)localObject6).put("path", localObject4.getPath());
                      this.pdf_file_name.add(localObject6);
                    }
                  }
                  catch (Exception localException2)
                  {
                    localException2.printStackTrace();
                    continue;
                  }
                  j += 1;
                  break;
                  localObject6 = new HashMap();
                  ((HashMap)localObject6).put("name", localException2.getName());
                  ((HashMap)localObject6).put("isfolder", Boolean.valueOf(true));
                  ((HashMap)localObject6).put("folder", ((Photo_info)idlist.get(i)).getName());
                  ((HashMap)localObject6).put("path", localException2.getPath());
                  this.pdf_file_name.add(localObject6);
                }
              }
            }
          }
          else
          {
            if (!this.isSearch) {
              break label711;
            }
            localObject1 = new File(((Photo_info)idlist.get(i)).getRotepath() + ((Photo_info)idlist.get(i)).getName());
            label521:
            localObject5 = ((File)localObject1).listFiles((FilenameFilter)localObject3);
            if ((localObject5 == null) || (localObject5.length <= 0)) {
              break label768;
            }
            localObject5 = new File(((File)localObject1).getPath() + "/" + ((File)localObject1).getName() + ".pdf");
          }
          for (;;)
          {
            try
            {
              if ((Util.getFileSize((File)localObject5) == 0L) || (!Util.isOpenPdf(((File)localObject1).getPath() + "/" + ((File)localObject1).getName() + ".pdf")))
              {
                localObject5 = new HashMap();
                ((HashMap)localObject5).put("name", ((File)localObject1).getName());
                ((HashMap)localObject5).put("isfolder", Boolean.valueOf(false));
                ((HashMap)localObject5).put("path", ((File)localObject1).getPath());
                this.pdf_file_name.add(localObject5);
              }
            }
            catch (Exception localException1)
            {
              label711:
              localException1.printStackTrace();
              continue;
            }
            i += 1;
            break;
            localObject1 = new File(this.root_Path3 + ((Photo_info)idlist.get(i)).getName());
            break label521;
            label768:
            localObject5 = new HashMap();
            ((HashMap)localObject5).put("name", localException1.getName());
            ((HashMap)localObject5).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject5).put("path", localException1.getPath());
            this.pdf_file_name.add(localObject5);
          }
        }
        if (this.pdf_file_name.size() > 0) {
          createPDF();
        }
      }
    }
    Object localObject2;
    label1201:
    while (this.pdf_OR_jpeg != 2)
    {
      do
      {
        Object localObject3;
        Object localObject1;
        int k;
        int j;
        Object localObject4;
        Object localObject6;
        Object localObject5;
        return;
        localObject2 = new Message();
        ((Message)localObject2).what = 3;
        this.handler.sendMessage((Message)localObject2);
        return;
        int i = 0;
        if (i < idlist.size())
        {
          if (((Photo_info)idlist.get(i)).isFolder())
          {
            localObject2 = new File(this.root_Path4 + ((Photo_info)idlist.get(i)).getName()).listFiles();
            if (localObject2 != null)
            {
              k = localObject2.length;
              j = 0;
              while (j < k)
              {
                localObject3 = localObject2[j];
                localObject5 = new HashMap();
                ((HashMap)localObject5).put("name", ((File)localObject3).getName());
                ((HashMap)localObject5).put("isfolder", Boolean.valueOf(true));
                ((HashMap)localObject5).put("folder", ((Photo_info)idlist.get(i)).getName());
                ((HashMap)localObject5).put("path", ((File)localObject3).getPath());
                this.pdf_file_name.add(localObject5);
                j += 1;
              }
            }
          }
          else
          {
            if (!this.isSearch) {
              break label1201;
            }
          }
          for (localObject2 = new File(((Photo_info)idlist.get(i)).getRotepath() + ((Photo_info)idlist.get(i)).getName());; localObject2 = new File(this.root_Path3 + ((Photo_info)idlist.get(i)).getName()))
          {
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject3).put("name", ((File)localObject2).getName());
            ((HashMap)localObject3).put("path", ((File)localObject2).getPath());
            this.pdf_file_name.add(localObject3);
            i += 1;
            break;
          }
        }
      } while (this.pdf_file_name.size() <= 0);
      createPDF();
      return;
    }
    if (this.export_size == 0)
    {
      localObject2 = new Message();
      ((Message)localObject2).what = 33;
      this.handler.sendMessage((Message)localObject2);
      return;
    }
    CompressImage();
  }
  
  @SuppressLint({"InflateParams"})
  protected void showPdfSzieSelectDailog(final int paramInt)
  {
    long l = getfilesizeLength();
    if (l > 1048576L)
    {
      Object localObject = this.inflater.inflate(2130903118, null);
      TextView localTextView1 = (TextView)((View)localObject).findViewById(2131624329);
      TextView localTextView2 = (TextView)((View)localObject).findViewById(2131624330);
      TextView localTextView3 = (TextView)((View)localObject).findViewById(2131624331);
      localTextView1.setText(getResources().getString(2131165465) + " (" + Util.FormetFileSize1(l) + ")");
      localTextView2.setText(getResources().getString(2131165395) + " (about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)) + ")");
      localTextView3.setText(getResources().getString(2131165573) + " (about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)) + ")");
      localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
      localTextView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_Main.access$2402(Activity_Main.this, 0);
          if (paramInt == 1)
          {
            Activity_Main.access$2502(Activity_Main.this, 0);
            Activity_Main.access$2602(Activity_Main.this, 0);
          }
          for (;;)
          {
            Activity_Main.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_Main.access$2502(Activity_Main.this, 2);
              Activity_Main.access$2602(Activity_Main.this, 2);
            }
          }
        }
      });
      localTextView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_Main.access$2402(Activity_Main.this, 1);
          if (paramInt == 1)
          {
            Activity_Main.access$2502(Activity_Main.this, 0);
            Activity_Main.access$2602(Activity_Main.this, 0);
          }
          for (;;)
          {
            Activity_Main.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_Main.access$2502(Activity_Main.this, 2);
              Activity_Main.access$2602(Activity_Main.this, 2);
            }
          }
        }
      });
      localTextView3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_Main.access$2402(Activity_Main.this, 2);
          if (paramInt == 1)
          {
            Activity_Main.access$2502(Activity_Main.this, 0);
            Activity_Main.access$2602(Activity_Main.this, 0);
          }
          for (;;)
          {
            Activity_Main.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_Main.access$2502(Activity_Main.this, 2);
              Activity_Main.access$2602(Activity_Main.this, 2);
            }
          }
        }
      });
      ((AlertDialog)localObject).show();
      return;
    }
    if (paramInt == 1)
    {
      this.select_pdf_posiotion = 0;
      this.select_jpeg_posiotion = 0;
    }
    for (;;)
    {
      shareDocment();
      return;
      if (paramInt == 2)
      {
        this.select_pdf_posiotion = 2;
        this.select_jpeg_posiotion = 2;
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  protected void showPdf_or_imagejpeg_SelectDailog(final int paramInt)
  {
    long l = getfilesizeLength();
    Object localObject = this.inflater.inflate(2130903128, null);
    TextView localTextView1 = (TextView)((View)localObject).findViewById(2131624344);
    LinearLayout localLinearLayout = (LinearLayout)((View)localObject).findViewById(2131624345);
    TextView localTextView2 = (TextView)((View)localObject).findViewById(2131624346);
    localTextView1.setText(getResources().getString(2131165772) + " (" + Util.FormetFileSize1(l) + ")");
    localTextView2.setText(getResources().getString(2131165771) + " (" + Util.FormetFileSize1(l) + ")");
    localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
    localTextView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_Main.access$2302(Activity_Main.this, 1);
        Activity_Main.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    localLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_Main.access$2302(Activity_Main.this, 2);
        Activity_Main.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    ((AlertDialog)localObject).show();
  }
  
  public void showToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Activity_Main.this.getApplicationContext(), paramString, 0).show();
      }
    });
  }
  
  public void takePicture(boolean paramBoolean)
  {
    this.editor.putString("folder_root_path", this.root_Path3);
    this.editor.putBoolean("isCliek_takecamera", true);
    this.editor.commit();
    Intent localIntent;
    if (paramBoolean)
    {
      FlurryAgent.logEvent("4_CAMERA");
      this.editor.putBoolean("where", false);
      this.editor.commit();
      this.mapp.setSavePath(this.root_Path3);
      if (this.mapp.isPad())
      {
        localIntent = new Intent(this.context, Activity_PadCamera.class);
        activity_Main.startActivityForResult(localIntent, 0);
      }
    }
    for (;;)
    {
      overridePendingTransition(17432576, 17432577);
      return;
      localIntent = new Intent(this.context, Activity_CameraPreview.class);
      break;
      FlurryAgent.logEvent("4_ALBUM");
      if (this.preferences.getBoolean("setting_import_gallery", false))
      {
        localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        localIntent.setType("image/*");
        localIntent.setFlags(67108864);
        activity_Main.startActivityForResult(localIntent, 10);
      }
      else
      {
        this.editor.putBoolean("where", false);
        this.editor.commit();
        this.mapp.setPhotofrom(false);
        this.mapp.setSavePath(this.root_Path3);
        localIntent = new Intent(this, LocalAlbumActivity.class);
        activity_Main.startActivity(localIntent);
      }
    }
  }
  
  public void unselected()
  {
    Log.i("TAG", "unselected");
    int i;
    if (this.list_type == 0)
    {
      if (this.madapter != null) {
        this.madapter.isse = false;
      }
      this.isSelect = false;
      this.islongclick = false;
      int j = this.mlist2.size();
      i = 0;
      label54:
      if (i >= j) {
        break label139;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label122;
      }
      if (this.madapter != null) {
        this.madapter.notifyDataSetChanged();
      }
    }
    for (;;)
    {
      i += 1;
      break label54;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label122:
      if (this.madapter2 != null) {
        this.madapter2.notifyDataSetChanged();
      }
    }
    label139:
    idlist.clear();
    this.more.setVisibility(0);
    this.main_selecttextview.setVisibility(8);
    this.main_selectall.setText(activity_Main.getResources().getString(2131165561));
    this.main_selectall.setVisibility(8);
    this.selecttext.setText("");
    this.selecttext.setVisibility(8);
    this.camera.setVisibility(0);
    this.main_appname_textview.setVisibility(0);
    this.back.setVisibility(8);
    this.main_imagesearch.setVisibility(0);
    if (this.mapp.isPad())
    {
      this.main_importfromgallery.setVisibility(0);
      this.main_newfolder.setVisibility(0);
      this.main_viewsortby.setVisibility(0);
    }
    this.main_onlongclick_layout.setVisibility(8);
  }
  
  public void unselected2()
  {
    Log.i("TAG", "unselected2");
    int i;
    if (this.list_type == 0)
    {
      if (this.madapter != null) {
        this.madapter.isse = false;
      }
      this.isSelect = false;
      this.islongclick = false;
      this.searchandclick = false;
      int j = this.mlist2.size();
      i = 0;
      label59:
      if (i >= j) {
        break label144;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label127;
      }
      if (this.madapter != null) {
        this.madapter.notifyDataSetChanged();
      }
    }
    for (;;)
    {
      i += 1;
      break label59;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label127:
      if (this.madapter2 != null) {
        this.madapter2.notifyDataSetChanged();
      }
    }
    label144:
    idlist.clear();
    this.main_selecttextview.setVisibility(8);
    this.main_selectall.setText(activity_Main.getResources().getString(2131165561));
    this.main_selectall.setVisibility(8);
    this.selecttext.setText("");
    this.selecttext.setVisibility(8);
    this.camera.setVisibility(0);
    search2();
  }
  
  public void unselected3()
  {
    Log.i("TAG", "unselected3");
    int i;
    if (this.list_type == 0)
    {
      if (this.madapter != null) {
        this.madapter.isse = false;
      }
      this.isSelect = false;
      this.islongclick = false;
      this.searchandclick = false;
      int j = this.mlist2.size();
      i = 0;
      label59:
      if (i >= j) {
        break label144;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label127;
      }
      if (this.madapter != null) {
        this.madapter.notifyDataSetChanged();
      }
    }
    for (;;)
    {
      i += 1;
      break label59;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label127:
      if (this.madapter2 != null) {
        this.madapter2.notifyDataSetChanged();
      }
    }
    label144:
    idlist.clear();
    this.main_selecttextview.setVisibility(8);
    this.main_selectall.setText(activity_Main.getResources().getString(2131165561));
    this.main_selectall.setVisibility(8);
    this.selecttext.setText("");
    this.selecttext.setVisibility(8);
    this.camera.setVisibility(0);
    search2();
  }
  
  public void updateNameToDb(String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("showname", paramString2);
    this.db.update("NameMaps", localContentValues, "realname = ?", new String[] { paramString1 });
  }
  
  class MyFilter
    implements FilenameFilter
  {
    private String name;
    
    @SuppressLint({"DefaultLocale"})
    public MyFilter(String paramString)
    {
      this.name = paramString.toLowerCase();
    }
    
    @SuppressLint({"DefaultLocale"})
    public boolean accept(File paramFile, String paramString)
    {
      return paramString.toLowerCase().contains(this.name);
    }
  }
  
  class MyFilter2
    implements FilenameFilter
  {
    MyFilter2() {}
    
    @SuppressLint({"DefaultLocale"})
    public boolean accept(File paramFile, String paramString)
    {
      return paramString.toLowerCase().endsWith(".pdf");
    }
  }
}
