package com.appxy.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.adpter.CopyAdapter;
import com.appxy.adpter.Folder_GridAdapter;
import com.appxy.adpter.Folder_ListAdapter;
import com.appxy.adpter.GridAdapter;
import com.appxy.adpter.ListAdapter;
import com.appxy.adpter.MyPrintDocumentAdapter;
import com.appxy.adpter.SharePopuList1Adapter;
import com.appxy.adpter.SharePopuList1_padAdapter;
import com.appxy.adpter.SharePopuList2Adapter;
import com.appxy.adpter.SharePopuList2_padAdapter;
import com.appxy.db.AppDate;
import com.appxy.db.MyDbHelper;
import com.appxy.entity.Photo_info;
import com.appxy.entity.Photo_item;
import com.appxy.entity.SortByDao;
import com.appxy.tools.ACache;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.MyApp;
import com.appxy.tools.RateMeDialogFragment;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
import com.appxy.views.HorizontalListView;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.itextpdf.text.pdf.PdfWriter;
import io.milton.simpleton.SimpletonServer;
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
import jp.co.cyberagent.android.gpuimage.extension.GPUImageWrapper;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;

public class Activity_Main
  extends BaseActivity
{
  private static final String AD_UNIT_ID = "ca-app-pub-2853676859073957/7068145829";
  static final String APP_CLIENT_ID = "000000004C12023B";
  public static final String CLIENT_ID = "tlmxq5fvx3x2ryhmg2pl5d9612w6lnc1";
  public static final String CLIENT_SECRET = "ks8fU65XGaun5zxY4a2Gt6WBQd3hikbT";
  private static final String PACKAGE_URL_SCHEME = "package:";
  private static final int PERMISSION_REQUEST_CODE = 0;
  private static final int RC_REQUEST = 10001;
  private static final int WAIT_TIME = 5000;
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
      return Long.valueOf(paramAnonymousFile2.lastModified()).compareTo(Long.valueOf(paramAnonymousFile1.lastModified()));
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
  private String[] PERMISSION = { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA" };
  private ImageView back2;
  private ImageView camera;
  private int clickid2;
  private boolean clickrate = false;
  private String compressJpeg_Path;
  private Context context;
  private String[] country;
  private String[] countryIAP = { "US", "CA", "GB", "AU", "DE", "FR", "NL", "IT", "BE", "DK", "PL", "TR", "CZ", "NO", "CH", "AT", "RO", "SE", "IE", "ES", "HU", "GR", "PT" };
  private int currentWidth = 0;
  private SQLiteDatabase db;
  private ImageView delete;
  private List<Photo_info> editnameList;
  private SharedPreferences.Editor editor;
  private SharePopuList1_padAdapter exportAdapter1;
  private SharePopuList2_padAdapter exportAdapter2;
  private List<File> export_file;
  private int export_select = 0;
  private int export_size = 0;
  private AlertDialog fax2linkDialog;
  private int faxCount;
  private TextView faxlink_dismisstext;
  private TextView faxlink_downloadtext;
  private ImageView faxlink_imageview;
  private RelativeLayout faxlink_layout;
  private GridView folder_grid;
  private ImageView gallery;
  private ImageView getall;
  private ImageView ggimage;
  private RelativeLayout googleAdv_layout;
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
        Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131296599));
        continue;
        Activity_Main.access$4502(Activity_Main.this, false);
        Activity_Main.this.mlist2.clear();
        Activity_Main.this.mlist2.addAll(Activity_Main.this.searchList);
        if (Activity_Main.this.list_type == 0)
        {
          Activity_Main.this.main_folder_textview_name_space.setVisibility(8);
          Activity_Main.this.main_file_textview_name_space.setVisibility(8);
          if (Activity_Main.this.mlist2.size() > 0) {
            if ((Activity_Main.this.mlist2_folder.size() > 0) && (Activity_Main.this.searchText.equals("")))
            {
              Activity_Main.this.main_linear_folder.setVisibility(0);
              Activity_Main.this.main_folder_textview_name.setVisibility(0);
              Activity_Main.this.main_file_textview_name.setVisibility(0);
            }
          }
        }
        int j;
        int i;
        int k;
        for (;;)
        {
          j = Activity_Main.idlist.size();
          i = 0;
          while (i < j)
          {
            k = Activity_Main.this.findId((Photo_info)Activity_Main.idlist.get(i));
            if (k != -1) {
              ((Photo_info)Activity_Main.this.mlist2.get(k)).setCheck(true);
            }
            i += 1;
          }
          Activity_Main.this.main_linear_folder.setVisibility(8);
          Activity_Main.this.main_folder_textview_name.setVisibility(8);
          break;
          Activity_Main.this.main_file_textview_name.setVisibility(8);
          Activity_Main.this.main_folder_textview_name.setVisibility(8);
          Activity_Main.this.main_linear_folder.setVisibility(8);
          continue;
          if (Activity_Main.this.mlist2.size() > 0)
          {
            if ((Activity_Main.this.mlist2_folder.size() > 0) && (Activity_Main.this.searchText.equals("")))
            {
              Activity_Main.this.main_linear_folder.setVisibility(0);
              Activity_Main.this.main_folder_textview_name.setVisibility(0);
              Activity_Main.this.main_folder_textview_name_space.setVisibility(0);
            }
            for (;;)
            {
              Activity_Main.this.main_file_textview_name.setVisibility(0);
              Activity_Main.this.main_file_textview_name_space.setVisibility(0);
              break;
              Activity_Main.this.main_linear_folder.setVisibility(8);
              Activity_Main.this.main_folder_textview_name.setVisibility(8);
              Activity_Main.this.main_folder_textview_name_space.setVisibility(8);
            }
          }
          Activity_Main.this.main_file_textview_name.setVisibility(8);
          Activity_Main.this.main_file_textview_name_space.setVisibility(8);
          Activity_Main.this.main_folder_textview_name.setVisibility(8);
          Activity_Main.this.main_folder_textview_name_space.setVisibility(8);
          Activity_Main.this.main_linear_folder.setVisibility(8);
        }
        if (Activity_Main.this.list_type == 0)
        {
          if (Activity_Main.this.madapter != null) {
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
        }
        else if (Activity_Main.this.madapter2 != null)
        {
          Activity_Main.this.madapter2.notifyDataSetChanged();
          continue;
          Activity_Main.this.main_progreebar.setVisibility(8);
          Activity_Main.access$4502(Activity_Main.this, false);
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296477), 0).show();
          continue;
          Activity_Main.this.main_progreebar.setVisibility(8);
          Activity_Main.access$4502(Activity_Main.this, false);
          Activity_Main.this.mlist2.clear();
          Activity_Main.this.mlist2_folder.clear();
          i = 0;
          if (i < Activity_Main.this.mlist2_copy.size())
          {
            if (((Photo_info)Activity_Main.this.mlist2_copy.get(i)).isFolder()) {
              Activity_Main.this.mlist2_folder.add(Activity_Main.this.mlist2_copy.get(i));
            }
            for (;;)
            {
              i += 1;
              break;
              Activity_Main.this.mlist2.add(Activity_Main.this.mlist2_copy.get(i));
            }
          }
          Object localObject1;
          Object localObject3;
          try
          {
            System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
            if (Activity_Main.sort_type == 0)
            {
              Collections.sort(Activity_Main.this.mlist2_folder, Activity_Main.comparator);
              Collections.sort(Activity_Main.this.mlist2, Activity_Main.comparator);
            }
            for (;;)
            {
              j = Activity_Main.this.idlist_folder.size();
              i = 0;
              while (i < j)
              {
                k = Activity_Main.this.findId((Photo_info)Activity_Main.this.idlist_folder.get(i));
                if (k != -1) {
                  ((Photo_info)Activity_Main.this.mlist2_folder.get(k)).setCheck(true);
                }
                i += 1;
              }
              Collections.sort(Activity_Main.this.mlist2_folder, Activity_Main.comparator2);
              Collections.sort(Activity_Main.this.mlist2, Activity_Main.comparator2);
            }
          }
          catch (Exception localException1)
          {
            for (;;)
            {
              localException1.printStackTrace();
            }
            j = Activity_Main.idlist.size();
            i = 0;
            while (i < j)
            {
              k = Activity_Main.this.findId((Photo_info)Activity_Main.idlist.get(i));
              if (k != -1) {
                ((Photo_info)Activity_Main.this.mlist2.get(k)).setCheck(true);
              }
              i += 1;
            }
            Activity_Main.this.setFolder_file_isshow();
            if (Activity_Main.this.list_type == 0) {
              if ((Activity_Main.this.madapter != null) && (Activity_Main.this.madapter_folder != null))
              {
                Activity_Main.this.madapter_folder.notifyDataSetChanged();
                Activity_Main.this.madapter.notifyDataSetChanged();
              }
            }
            for (;;)
            {
              i = 0;
              if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() != 2) {
                break label1433;
              }
              localObject1 = Activity_Main.this.mlist2.iterator();
              while (((Iterator)localObject1).hasNext())
              {
                localObject3 = (Photo_info)((Iterator)localObject1).next();
                if (((Photo_info)localObject3).getImage_name() != null) {
                  i += ((Photo_info)localObject3).getImage_name().size();
                }
              }
              Activity_Main.this.list_by_grid();
              continue;
              if ((Activity_Main.this.madapter2 != null) && (Activity_Main.this.madapter2_folder != null))
              {
                Activity_Main.this.madapter2_folder.notifyDataSetChanged();
                Activity_Main.this.madapter2.notifyDataSetChanged();
              }
              else
              {
                Activity_Main.this.list_by_list();
              }
            }
            localObject1 = Activity_Main.this.mlist2_folder.iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject3 = (Photo_info)((Iterator)localObject1).next();
              if (((Photo_info)localObject3).getImage_name() != null) {
                i += ((Photo_info)localObject3).getImage_name().size();
              }
            }
            if ((Activity_Main.this.preferences.getInt("newversion_1.2.7_first", -1) == -1) && (Activity_Main.this.preferences.getBoolean("1.2.7_is_times", true)))
            {
              Activity_Main.this.editor.putBoolean("1.2.7_is_times", false);
              Activity_Main.this.editor.commit();
              if (i != 1) {
                break label1493;
              }
              FlurryAgent.logEvent("9_Free1.2.6_1Doc");
            }
            for (;;)
            {
              if (i >= 2) {
                Activity_Main.this.googleAdv_layout.setVisibility(0);
              }
              label1433:
              i = 0;
              localObject1 = Activity_Main.this.mlist2.iterator();
              while (((Iterator)localObject1).hasNext())
              {
                localObject3 = (Photo_info)((Iterator)localObject1).next();
                if (((Photo_info)localObject3).getImage_name() != null) {
                  i += ((Photo_info)localObject3).getImage_name().size();
                }
              }
              label1493:
              if (i == 2) {
                FlurryAgent.logEvent("9_Free1.2.6_2Doc");
              } else if (i == 3) {
                FlurryAgent.logEvent("9_Free1.2.6_3Doc");
              }
            }
            localObject1 = Activity_Main.this.mlist2_folder.iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject3 = (Photo_info)((Iterator)localObject1).next();
              if (((Photo_info)localObject3).getImage_name() != null) {
                i += ((Photo_info)localObject3).getImage_name().size();
              }
            }
            Activity_Main.this.editor.putInt("file_count_total", i);
            Activity_Main.this.editor.commit();
          }
          if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1) && (Activity_Main.this.preferences.getInt("file_count_total", 0) >= 2) && ((Activity_Main.this.isclick_cloud) || (Activity_Main.this.isclick_systemdialog)))
          {
            if (Activity_Main.this.isclick_cloud)
            {
              FlurryAgent.logEvent("7_OPTIONDIR");
              Utils.showAd_chayeads(Activity_Main.activity_Main);
            }
            for (;;)
            {
              Activity_Main.access$5702(Activity_Main.this, false);
              Activity_Main.access$5802(Activity_Main.this, false);
              break;
              if ((Activity_Main.this.isclick_systemdialog) && (System.currentTimeMillis() - Activity_Main.this.preferences.getLong("click_systemdialog_time", 0L) >= 5000L))
              {
                FlurryAgent.logEvent("7_OPTION5S");
                Utils.showAd_chayeads(Activity_Main.activity_Main);
              }
            }
            new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131296530)).setMessage(Activity_Main.this.getResources().getString(2131296387)).setPositiveButton(Activity_Main.this.getResources().getString(2131296464), null).create().show();
            continue;
            if (Activity_Main.this.searchandclick) {
              if (Activity_Main.this.mapp.isPad()) {
                Activity_Main.this.unselected3();
              }
            }
            for (;;)
            {
              Activity_Main.access$2102(Activity_Main.this, "");
              Activity_Main.this.list_folders();
              break;
              Activity_Main.this.unselected2();
              continue;
              Activity_Main.this.unselected();
            }
            Activity_Main.idlist.clear();
            Activity_Main.this.idlist_folder.clear();
            if (Activity_Main.this.list_type == 0)
            {
              Activity_Main.this.madapter_folder.notifyDataSetChanged();
              Activity_Main.this.madapter.notifyDataSetChanged();
              label1946:
              if ((Activity_Main.this.progressDialog != null) && (Activity_Main.this.progressDialog.isShowing())) {
                Activity_Main.this.progressDialog.dismiss();
              }
              Activity_Main.access$5902(Activity_Main.this, null);
              if (!Activity_Main.this.searchandclick) {
                break label2061;
              }
              if (!Activity_Main.this.mapp.isPad()) {
                break label2051;
              }
              Activity_Main.this.unselected3();
            }
            for (;;)
            {
              Activity_Main.this.setFolder_file_isshow();
              break;
              Activity_Main.this.madapter2_folder.notifyDataSetChanged();
              Activity_Main.this.madapter2.notifyDataSetChanged();
              break label1946;
              label2051:
              Activity_Main.this.unselected2();
              continue;
              label2061:
              Activity_Main.this.unselected();
            }
            if ((Activity_Main.this.progressDialog != null) && (Activity_Main.this.progressDialog.isShowing())) {
              Activity_Main.this.progressDialog.dismiss();
            }
            Activity_Main.access$5902(Activity_Main.this, null);
            Activity_Main.access$6002(Activity_Main.this, null);
            Activity_Main.this.editor.putBoolean("isUpdate3", false);
            Activity_Main.this.editor.commit();
            Activity_Main.access$2102(Activity_Main.this, "");
            Activity_Main.this.list_folders();
            continue;
            if (Activity_Main.this.list_type == 0) {
              Activity_Main.this.list_by_grid();
            }
            for (;;)
            {
              Activity_Main.this.progressDialog.dismiss();
              Activity_Main.access$5902(Activity_Main.this, null);
              break;
              Activity_Main.this.list_by_list();
            }
            if ((Activity_Main.this.progressDialog != null) && (Activity_Main.this.progressDialog.isShowing())) {
              Activity_Main.this.progressDialog.dismiss();
            }
            Activity_Main.access$5902(Activity_Main.this, null);
            Activity_Main.access$6002(Activity_Main.this, null);
            Activity_Main.access$6102(Activity_Main.this, new ArrayList());
            Activity_Main.this.export_file.clear();
            localObject3 = new ArrayList();
            if (Activity_Main.this.export_size == 0)
            {
              localObject4 = new Activity_Main.MyFilter2(Activity_Main.this);
              i = 0;
              while (i < Activity_Main.this.idlist_folder.size())
              {
                localObject1 = (Photo_info)Activity_Main.this.idlist_folder.get(i);
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.idlist_folder.get(i)).getName()).listFiles();
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
                i += 1;
              }
              i = 0;
              if (i < Activity_Main.idlist.size())
              {
                localObject1 = (Photo_info)Activity_Main.idlist.get(i);
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
            i = 0;
            if (i < Activity_Main.this.export_file.size())
            {
              if (Build.VERSION.SDK_INT >= 24) {
                ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_Main.this.context, Activity_Main.this.getPackageName() + ".fileprovider", (File)Activity_Main.this.export_file.get(i)));
              }
              for (;;)
              {
                i += 1;
                break;
                ((ArrayList)localObject3).add(Uri.fromFile((File)Activity_Main.this.export_file.get(i)));
              }
            }
            Object localObject4 = Activity_Main.this.getPackageManager().getInstalledApplications(0);
            j = ((List)localObject4).size();
            Object localObject5 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject5).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
            ((Intent)localObject5).setType("application/pdf");
            if (!Activity_Main.this.isListView1OrListview2)
            {
              switch (Activity_Main.this.export_select)
              {
              default: 
                break;
              case 0: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.findAndGotoApp("dropbox", (ArrayList)localObject3))
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() != 1) {
                      continue;
                    }
                    Activity_Main.access$5702(Activity_Main.this, true);
                    continue;
                  }
                  new AlertDialog.Builder(Activity_Main.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  continue;
                }
                Activity_Main.this.iapBuy.IAP_Buy();
                break;
              case 1: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.findAndGotoApp("evernote", (ArrayList)localObject3))
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() != 1) {
                      continue;
                    }
                    Activity_Main.access$5702(Activity_Main.this, true);
                    continue;
                  }
                  new AlertDialog.Builder(Activity_Main.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  continue;
                }
                Activity_Main.this.iapBuy.IAP_Buy();
                break;
              case 2: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject3))
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() != 1) {
                      continue;
                    }
                    Activity_Main.access$5702(Activity_Main.this, true);
                    continue;
                  }
                  new AlertDialog.Builder(Activity_Main.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  continue;
                }
                Activity_Main.this.iapBuy.IAP_Buy();
                break;
              case 3: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  localObject1 = null;
                  i = 0;
                  while (i < j)
                  {
                    if (((ApplicationInfo)((List)localObject4).get(i)).packageName.equals("com.box.android")) {
                      localObject1 = (ApplicationInfo)((List)localObject4).get(i);
                    }
                    i += 1;
                  }
                  if (localObject1 != null)
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1) {
                      Activity_Main.access$5702(Activity_Main.this, true);
                    }
                    localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                    ((Intent)localObject1).setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                    ((Intent)localObject1).setType("application/*");
                    ((Intent)localObject1).putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject3);
                    Activity_Main.this.startActivityForResult((Intent)localObject1, 3);
                    continue;
                  }
                  new AlertDialog.Builder(Activity_Main.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  continue;
                }
                Activity_Main.this.iapBuy.IAP_Buy();
                break;
              case 4: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.findAndGotoApp("skydrive", (ArrayList)localObject3))
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() != 1) {
                      continue;
                    }
                    Activity_Main.access$5702(Activity_Main.this, true);
                    continue;
                  }
                  new AlertDialog.Builder(Activity_Main.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  continue;
                }
                Activity_Main.this.iapBuy.IAP_Buy();
                break;
              }
            }
            else if (Activity_Main.this.isListView1OrListview2)
            {
              Object localObject6;
              switch (Activity_Main.this.export_select)
              {
              default: 
                break;
              case 0: 
                if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                {
                  Activity_Main.access$5802(Activity_Main.this, true);
                  Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                  Activity_Main.this.editor.commit();
                }
                localObject1 = new ArrayList();
                localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                if (!((List)localObject4).isEmpty())
                {
                  localObject4 = ((List)localObject4).iterator();
                  if (((Iterator)localObject4).hasNext())
                  {
                    localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                    localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                    ((Intent)localObject6).setType("application/pdf");
                    if (Activity_Main.this.export_file.size() != 0) {
                      if (Activity_Main.this.export_file.size() == 1) {
                        ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName().substring(0, ((File)Activity_Main.this.export_file.get(0)).getName().length() - 4));
                      }
                    }
                    while ((((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook")))
                    {
                      ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                      ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                      ((List)localObject1).add(localObject6);
                      break;
                      ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                      continue;
                      ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "");
                    }
                  }
                  if (((List)localObject1).size() > 0)
                  {
                    localObject3 = Intent.createChooser((Intent)((List)localObject1).remove(0), "Export");
                    ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject1).toArray(new Parcelable[0]));
                    Activity_Main.this.startActivityForResult((Intent)localObject3, 3);
                  }
                  else
                  {
                    Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                  }
                }
                break;
              case 1: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.preferences.getString("email", "").equals(""))
                  {
                    localObject1 = LayoutInflater.from(Activity_Main.this.context).inflate(2130903161, null);
                    localObject4 = (EditText)((View)localObject1).findViewById(2131755630);
                    ((EditText)localObject4).setInputType(33);
                    ((EditText)localObject4).setSelectAllOnFocus(true);
                    ((EditText)localObject4).setText(Activity_Main.this.preferences.getString("email", ""));
                    new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131296457)).setView((View)localObject1).setPositiveButton(Activity_Main.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                      {
                        Object localObject1 = (EditText)this.val$mview.findViewById(2131755630);
                        Object localObject2 = ((EditText)localObject1).getText().toString();
                        if (Activity_Main.this.isEmail((String)localObject2))
                        {
                          Activity_Main.access$5502(Activity_Main.this, Activity_Main.this.preferences.edit());
                          Activity_Main.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                          Activity_Main.this.editor.commit();
                          paramAnonymous2DialogInterface.dismiss();
                          paramAnonymous2DialogInterface = new ArrayList();
                          localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                          if (Activity_Main.this.export_file.size() != 0) {
                            if (Activity_Main.this.export_file.size() == 1)
                            {
                              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName().substring(0, ((File)Activity_Main.this.export_file.get(0)).getName().length() - 4));
                              ((Intent)localObject1).setType("application/pdf");
                              localObject1 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                              if (((List)localObject1).isEmpty()) {
                                break label729;
                              }
                              localObject1 = ((List)localObject1).iterator();
                            }
                          }
                          label264:
                          label635:
                          for (;;)
                          {
                            if (!((Iterator)localObject1).hasNext()) {
                              break label637;
                            }
                            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                            Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                            localIntent.setType("application/pdf");
                            if (Activity_Main.this.export_file.size() != 0) {
                              if (Activity_Main.this.export_file.size() == 1) {
                                localIntent.putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName().substring(0, ((File)Activity_Main.this.export_file.get(0)).getName().length() - 4));
                              }
                            }
                            for (;;)
                            {
                              if ((!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                                break label635;
                              }
                              localIntent.putExtra("android.intent.extra.STREAM", this.val$fileUris);
                              localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("email", "") });
                              localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                              paramAnonymous2DialogInterface.add(localIntent);
                              break label264;
                              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                              break;
                              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "");
                              break;
                              localIntent.putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                              continue;
                              localIntent.putExtra("android.intent.extra.SUBJECT", "");
                            }
                          }
                          label637:
                          if (paramAnonymous2DialogInterface.size() > 0)
                          {
                            localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                            Activity_Main.this.startActivityForResult((Intent)localObject1, 3);
                            return;
                          }
                          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                          return;
                          label729:
                          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                          return;
                        }
                        paramAnonymous2DialogInterface.dismiss();
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131296530)).setMessage(Activity_Main.this.getResources().getString(2131296442)).setPositiveButton(Activity_Main.this.getResources().getString(2131296464), null).create().show();
                      }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                      {
                        ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                        paramAnonymous2DialogInterface.dismiss();
                      }
                    }).create().show();
                    if (!Activity_Main.this.mapp.isPad()) {
                      new Timer().schedule(new TimerTask()
                      {
                        public void run()
                        {
                          ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                        }
                      }, 100L);
                    }
                  }
                  else
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                    {
                      Activity_Main.access$5802(Activity_Main.this, true);
                      Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                      Activity_Main.this.editor.commit();
                    }
                    localObject1 = new ArrayList();
                    localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                    if (!((List)localObject4).isEmpty())
                    {
                      localObject4 = ((List)localObject4).iterator();
                      if (((Iterator)localObject4).hasNext())
                      {
                        localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                        localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                        ((Intent)localObject6).setType("application/pdf");
                        if (Activity_Main.this.export_file.size() != 0) {
                          if (Activity_Main.this.export_file.size() == 1) {
                            ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName().substring(0, ((File)Activity_Main.this.export_file.get(0)).getName().length() - 4));
                          }
                        }
                        while ((((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")))
                        {
                          ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                          ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("email", "") });
                          ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                          ((List)localObject1).add(localObject6);
                          break;
                          ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                          continue;
                          ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "");
                        }
                      }
                      if (((List)localObject1).size() > 0)
                      {
                        localObject3 = Intent.createChooser((Intent)((List)localObject1).remove(0), "Export");
                        ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject1).toArray(new Parcelable[0]));
                        Activity_Main.this.startActivityForResult((Intent)localObject3, 3);
                      }
                      else
                      {
                        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                      }
                    }
                    else
                    {
                      Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                    }
                  }
                }
                else {
                  Activity_Main.this.iapBuy.IAP_Buy();
                }
                break;
              case 2: 
                if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasfolder))
                {
                  Activity_Main.access$6702(Activity_Main.this, new String[] { "US" });
                  j = 0;
                  i = 0;
                  while (i < Activity_Main.this.country.length)
                  {
                    if (Locale.getDefault().getCountry().equals(Activity_Main.this.country[i])) {
                      j = 1;
                    }
                    i += 1;
                  }
                  if (j != 0)
                  {
                    if ((Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfax")) || (Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfaxplus")))
                    {
                      if ((Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfax")) && (Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfaxplus")))
                      {
                        if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                        {
                          Activity_Main.access$5802(Activity_Main.this, true);
                          Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                          Activity_Main.this.editor.commit();
                        }
                        localObject1 = new Intent("android.intent.action.SEND");
                        ((Intent)localObject1).setType("application/pdf");
                        localObject4 = new ArrayList();
                        localObject1 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                        if (!((List)localObject1).isEmpty())
                        {
                          localObject5 = ((List)localObject1).iterator();
                          while (((Iterator)localObject5).hasNext())
                          {
                            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                            if ((Activity_Main.idlist.size() > 1) || (Activity_Main.this.hasfolder))
                            {
                              localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                              ((Intent)localObject1).setType("application/pdf");
                              if (Activity_Main.this.export_file.size() == 0) {
                                break label5625;
                              }
                              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName());
                              if (!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfax")) {
                                continue;
                              }
                              if (((ArrayList)localObject3).size() != 1) {
                                break label5640;
                              }
                              ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                            }
                            for (;;)
                            {
                              ((Intent)localObject1).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                              ((List)localObject4).add(localObject1);
                              break;
                              localObject1 = new Intent("android.intent.action.SEND");
                              break label5484;
                              ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner Pro");
                              break label5536;
                              ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                            }
                          }
                          if (((List)localObject4).size() > 0)
                          {
                            localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Fax");
                            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                            Activity_Main.this.startActivity((Intent)localObject1);
                          }
                          else
                          {
                            Toast.makeText(Activity_Main.activity_Main, "Error: Cannot open or share created PDF report.", 0).show();
                          }
                        }
                      }
                      else if (Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfax"))
                      {
                        if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1) {
                          Activity_Main.access$5702(Activity_Main.this, true);
                        }
                        Util.findAndGotoApp1(Activity_Main.activity_Main, "com.appxy.tinyfax", (ArrayList)localObject3, Activity_Main.this.getfilesizeLength(), Activity_Main.this.export_size);
                      }
                      else
                      {
                        if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1) {
                          Activity_Main.access$5702(Activity_Main.this, true);
                        }
                        Util.findAndGotoApp1(Activity_Main.activity_Main, "com.appxy.tinyfaxplus", (ArrayList)localObject3, Activity_Main.this.getfilesizeLength(), Activity_Main.this.export_size);
                      }
                    }
                    else if (Activity_Main.this.preferences.getInt("newversion_1.2.5_faxpro", -1) == 0) {
                      Util.showGooglePlayFax_pro(Activity_Main.activity_Main);
                    } else {
                      Util.showGooglePlayFax(Activity_Main.activity_Main);
                    }
                  }
                  else if (Util.isPkgInstalled(Activity_Main.activity_Main, "com.appxy.tinyfaxintl"))
                  {
                    if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                    {
                      Activity_Main.access$5802(Activity_Main.this, true);
                      Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                      Activity_Main.this.editor.commit();
                    }
                    localObject1 = new Intent("android.intent.action.SEND");
                    ((Intent)localObject1).setType("application/pdf");
                    localObject4 = new ArrayList();
                    localObject1 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                    if (!((List)localObject1).isEmpty())
                    {
                      localObject5 = ((List)localObject1).iterator();
                      while (((Iterator)localObject5).hasNext())
                      {
                        localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                        if ((Activity_Main.idlist.size() > 1) || (Activity_Main.this.hasfolder))
                        {
                          localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                          ((Intent)localObject1).setType("application/pdf");
                          if (Activity_Main.this.export_file.size() == 0) {
                            break label6215;
                          }
                          ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName());
                          if (!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfaxintl")) {
                            continue;
                          }
                          if (((ArrayList)localObject3).size() != 1) {
                            break label6230;
                          }
                          ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                        }
                        for (;;)
                        {
                          ((Intent)localObject1).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                          ((List)localObject4).add(localObject1);
                          break;
                          localObject1 = new Intent("android.intent.action.SEND");
                          break label6074;
                          ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner Pro");
                          break label6126;
                          ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                        }
                      }
                      if (((List)localObject4).size() > 0)
                      {
                        localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Fax");
                        ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                        Activity_Main.this.startActivity((Intent)localObject1);
                      }
                      else
                      {
                        Toast.makeText(Activity_Main.activity_Main, "Error: Cannot open or share created PDF report.", 0).show();
                      }
                    }
                  }
                  else
                  {
                    Util.showGooglePlayFax11(Activity_Main.activity_Main);
                  }
                }
                break;
              case 4: 
                if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasfolder))
                {
                  if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                  {
                    Activity_Main.access$5802(Activity_Main.this, true);
                    Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                    Activity_Main.this.editor.commit();
                  }
                  localObject1 = new Intent("android.intent.action.VIEW");
                  ((Intent)localObject1).setDataAndType((Uri)((ArrayList)localObject3).get(0), "application/pdf");
                  if (Build.VERSION.SDK_INT >= 24) {
                    ((Intent)localObject1).setFlags(3);
                  }
                  for (;;)
                  {
                    Activity_Main.this.startActivityForResult(Intent.createChooser((Intent)localObject1, "Open in"), 3);
                    break;
                    ((Intent)localObject1).setFlags(67108864);
                  }
                }
                break;
              case 3: 
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  Activity_Main.this.editor.putInt("pdf_pages", Util.getPdfPages(((File)Activity_Main.this.export_file.get(0)).getPath()));
                  Activity_Main.this.editor.putString("pdf_path", ((File)Activity_Main.this.export_file.get(0)).getPath());
                  Activity_Main.this.editor.putString("pdf_name", ((File)Activity_Main.this.export_file.get(0)).getName().replace(".pdf", ""));
                  Activity_Main.this.editor.commit();
                  if (Build.VERSION.SDK_INT >= 19) {
                    try
                    {
                      this.printManager = ((PrintManager)Activity_Main.activity_Main.getSystemService("print"));
                      this.printManager.print(Activity_Main.this.preferences.getString("pdf_name", ""), new MyPrintDocumentAdapter(Activity_Main.this.preferences), null);
                    }
                    catch (Exception localException2)
                    {
                      Toast.makeText(Activity_Main.activity_Main, "Print error!", 0).show();
                    }
                  }
                }
                else
                {
                  Activity_Main.this.iapBuy.IAP_Buy();
                }
                break;
              case 5: 
                label5484:
                label5536:
                label5625:
                label5640:
                label6074:
                label6126:
                label6215:
                label6230:
                Object localObject2;
                if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1)
                  {
                    Activity_Main.access$5802(Activity_Main.this, true);
                    Activity_Main.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                    Activity_Main.this.editor.commit();
                  }
                  if ((Activity_Main.idlist.size() > 1) || (Activity_Main.this.hasfolder))
                  {
                    localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                    ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                    ((Intent)localObject2).setType("application/pdf");
                    localObject4 = new ArrayList();
                    localObject2 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                    if (((List)localObject2).isEmpty()) {
                      continue;
                    }
                    localObject5 = ((List)localObject2).iterator();
                    label6922:
                    if (!((Iterator)localObject5).hasNext()) {
                      break label7243;
                    }
                    localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                    if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasfolder)) {
                      break label7199;
                    }
                    localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                    label6978:
                    ((Intent)localObject2).setType("application/pdf");
                    if (Activity_Main.this.export_file.size() == 0) {
                      break label7214;
                    }
                    ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", ((File)Activity_Main.this.export_file.get(0)).getName());
                    label7030:
                    if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("dropbox")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("evernote")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.box.android")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("skydrive"))) {
                      break label7227;
                    }
                    if (((ArrayList)localObject3).size() != 1) {
                      break label7229;
                    }
                    ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                  }
                  for (;;)
                  {
                    ((Intent)localObject2).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                    ((List)localObject4).add(localObject2);
                    break label6922;
                    localObject2 = new Intent("android.intent.action.SEND");
                    break;
                    label7199:
                    localObject2 = new Intent("android.intent.action.SEND");
                    break label6978;
                    label7214:
                    ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner");
                    break label7030;
                    label7227:
                    break label6922;
                    label7229:
                    ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                  }
                  label7243:
                  if (((List)localObject4).size() > 0)
                  {
                    localObject2 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share PDF file");
                    ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                    Activity_Main.this.startActivity((Intent)localObject2);
                  }
                  else
                  {
                    Toast.makeText(Activity_Main.activity_Main, "Error: Cannot open or share created PDF report.", 0).show();
                  }
                }
                else
                {
                  Activity_Main.this.iapBuy.IAP_Buy();
                  continue;
                  if ((Activity_Main.this.progressDialog != null) && (Activity_Main.this.progressDialog.isShowing())) {
                    Activity_Main.this.progressDialog.dismiss();
                  }
                  Activity_Main.access$5902(Activity_Main.this, null);
                  Activity_Main.access$6002(Activity_Main.this, null);
                  localObject3 = new ArrayList();
                  localObject2 = new ArrayList();
                  if (Activity_Main.this.export_size == 0)
                  {
                    i = 0;
                    while (i < Activity_Main.this.idlist_folder.size())
                    {
                      localObject4 = (Photo_info)Activity_Main.this.idlist_folder.get(i);
                      localObject4 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.idlist_folder.get(i)).getName()).listFiles();
                      int m = localObject4.length;
                      j = 0;
                      while (j < m)
                      {
                        localObject5 = localObject4[j].listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
                        if ((localObject5 != null) && (localObject5.length > 0))
                        {
                          k = 0;
                          if (k < localObject5.length)
                          {
                            ((ArrayList)localObject2).add(localObject5[k]);
                            if (Build.VERSION.SDK_INT >= 24) {
                              ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_Main.this.context, Activity_Main.this.getPackageName() + ".fileprovider", localObject5[k]));
                            }
                            for (;;)
                            {
                              k += 1;
                              break;
                              ((ArrayList)localObject3).add(Uri.fromFile(localObject5[k]));
                            }
                          }
                        }
                        j += 1;
                      }
                      i += 1;
                    }
                    i = 0;
                    while (i < Activity_Main.idlist.size())
                    {
                      localObject4 = (Photo_info)Activity_Main.idlist.get(i);
                      localObject4 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName()).listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
                      if ((localObject4 != null) && (localObject4.length > 0))
                      {
                        j = 0;
                        if (j < localObject4.length)
                        {
                          ((ArrayList)localObject2).add(localObject4[j]);
                          if (Build.VERSION.SDK_INT >= 24) {
                            ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_Main.this.context, Activity_Main.this.getPackageName() + ".fileprovider", localObject4[j]));
                          }
                          for (;;)
                          {
                            j += 1;
                            break;
                            ((ArrayList)localObject3).add(Uri.fromFile(localObject4[j]));
                          }
                        }
                      }
                      i += 1;
                    }
                  }
                  localObject4 = new File(Activity_Main.this.compressJpeg_Path).listFiles(new Activity_Main.MyFilter(Activity_Main.this, ".jpg"));
                  if ((localObject4 != null) && (localObject4.length > 0))
                  {
                    i = 0;
                    if (i < localObject4.length)
                    {
                      ((ArrayList)localObject2).add(localObject4[i]);
                      if (Build.VERSION.SDK_INT >= 24) {
                        ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_Main.this.context, Activity_Main.this.getPackageName() + ".fileprovider", localObject4[i]));
                      }
                      for (;;)
                      {
                        i += 1;
                        break;
                        ((ArrayList)localObject3).add(Uri.fromFile(localObject4[i]));
                      }
                    }
                  }
                  localObject4 = Activity_Main.this.getPackageManager().getInstalledApplications(0);
                  j = ((List)localObject4).size();
                  localObject5 = new Intent("android.intent.action.SEND_MULTIPLE");
                  ((Intent)localObject5).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                  ((Intent)localObject5).setType("image/jpeg");
                  if (!Activity_Main.this.isListView1OrListview2) {
                    switch (Activity_Main.this.export_select)
                    {
                    default: 
                      break;
                    case 0: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_Main.this.findAndGotoApp("dropbox", (ArrayList)localObject3)) {
                          continue;
                        }
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                          {
                            paramAnonymous2DialogInterface.dismiss();
                          }
                        }).create().show();
                        continue;
                      }
                      Activity_Main.this.iapBuy.IAP_Buy();
                      break;
                    case 1: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_Main.this.findAndGotoApp("evernote", (ArrayList)localObject3)) {
                          continue;
                        }
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                          {
                            paramAnonymous2DialogInterface.dismiss();
                          }
                        }).create().show();
                        continue;
                      }
                      Activity_Main.this.iapBuy.IAP_Buy();
                      break;
                    case 2: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_Main.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject3)) {
                          continue;
                        }
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                          {
                            paramAnonymous2DialogInterface.dismiss();
                          }
                        }).create().show();
                        continue;
                      }
                      Activity_Main.this.iapBuy.IAP_Buy();
                      break;
                    case 3: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        localObject2 = null;
                        i = 0;
                        while (i < j)
                        {
                          if (((ApplicationInfo)((List)localObject4).get(i)).packageName.equals("com.box.android")) {
                            localObject2 = (ApplicationInfo)((List)localObject4).get(i);
                          }
                          i += 1;
                        }
                        if (localObject2 != null)
                        {
                          localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                          ((Intent)localObject2).setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                          ((Intent)localObject2).setType("application/*");
                          ((Intent)localObject2).putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject3);
                          Activity_Main.this.startActivityForResult((Intent)localObject2, 3);
                          continue;
                        }
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                          {
                            paramAnonymous2DialogInterface.dismiss();
                          }
                        }).create().show();
                        continue;
                      }
                      Activity_Main.this.iapBuy.IAP_Buy();
                      break;
                    case 4: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_Main.this.findAndGotoApp2("skydrive", (ArrayList)localObject3)) {
                          continue;
                        }
                        new AlertDialog.Builder(Activity_Main.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                          {
                            paramAnonymous2DialogInterface.dismiss();
                          }
                        }).create().show();
                        continue;
                      }
                      Activity_Main.this.iapBuy.IAP_Buy();
                      break;
                    }
                  } else if (Activity_Main.this.isListView1OrListview2) {
                    switch (Activity_Main.this.export_select)
                    {
                    default: 
                      break;
                    case 0: 
                      localObject2 = new ArrayList();
                      localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                      if (!((List)localObject4).isEmpty())
                      {
                        localObject4 = ((List)localObject4).iterator();
                        if (((Iterator)localObject4).hasNext())
                        {
                          localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                          localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                          ((Intent)localObject6).setType("image/jpeg");
                          if (((ArrayList)localObject3).size() > 1) {
                            ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                          }
                          while ((((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook")))
                          {
                            ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                            ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                            ((List)localObject2).add(localObject6);
                            break;
                            ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "");
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
                          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                        }
                      }
                      break;
                    case 1: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_Main.this.preferences.getString("email", "").equals(""))
                        {
                          localObject2 = LayoutInflater.from(Activity_Main.this.context).inflate(2130903161, null);
                          localObject4 = (EditText)((View)localObject2).findViewById(2131755630);
                          ((EditText)localObject4).setInputType(33);
                          ((EditText)localObject4).setSelectAllOnFocus(true);
                          ((EditText)localObject4).setText(Activity_Main.this.preferences.getString("email", ""));
                          new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131296457)).setView((View)localObject2).setPositiveButton(Activity_Main.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
                          {
                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                            {
                              Object localObject1 = (EditText)this.val$mview.findViewById(2131755630);
                              Object localObject2 = ((EditText)localObject1).getText().toString();
                              if (Activity_Main.this.isEmail((String)localObject2))
                              {
                                Activity_Main.access$5502(Activity_Main.this, Activity_Main.this.preferences.edit());
                                Activity_Main.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                                Activity_Main.this.editor.commit();
                                paramAnonymous2DialogInterface.dismiss();
                                paramAnonymous2DialogInterface = new ArrayList();
                                localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                                ((Intent)localObject1).setType("image/jpeg");
                                localObject1 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                                if (!((List)localObject1).isEmpty())
                                {
                                  localObject1 = ((List)localObject1).iterator();
                                  label424:
                                  while (((Iterator)localObject1).hasNext())
                                  {
                                    localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                                    Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                                    localIntent.setType("image/jpeg");
                                    if (this.val$file_imageUris.size() > 1) {
                                      localIntent.putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                                    }
                                    for (;;)
                                    {
                                      if ((!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                                        break label424;
                                      }
                                      localIntent.putExtra("android.intent.extra.STREAM", this.val$file_imageUris);
                                      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("email", "") });
                                      localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                                      paramAnonymous2DialogInterface.add(localIntent);
                                      break;
                                      localIntent.putExtra("android.intent.extra.SUBJECT", "");
                                    }
                                  }
                                  if (paramAnonymous2DialogInterface.size() > 0)
                                  {
                                    localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                                    ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                                    Activity_Main.this.startActivityForResult((Intent)localObject1, 3);
                                    return;
                                  }
                                  Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                                  return;
                                }
                                Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                                return;
                              }
                              paramAnonymous2DialogInterface.dismiss();
                              new AlertDialog.Builder(Activity_Main.this.context).setTitle(Activity_Main.this.getResources().getString(2131296530)).setMessage(Activity_Main.this.getResources().getString(2131296442)).setPositiveButton(Activity_Main.this.getResources().getString(2131296464), null).create().show();
                            }
                          }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                          {
                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                            {
                              ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                              paramAnonymous2DialogInterface.dismiss();
                            }
                          }).create().show();
                          if (!Activity_Main.this.mapp.isPad()) {
                            new Timer().schedule(new TimerTask()
                            {
                              public void run()
                              {
                                ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                              }
                            }, 100L);
                          }
                        }
                        else
                        {
                          localObject2 = new ArrayList();
                          localObject4 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                          if (!((List)localObject4).isEmpty())
                          {
                            localObject4 = ((List)localObject4).iterator();
                            if (((Iterator)localObject4).hasNext())
                            {
                              localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                              localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                              ((Intent)localObject6).setType("image/jpeg");
                              if (((ArrayList)localObject3).size() > 1) {
                                ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                              }
                              while ((((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")))
                              {
                                ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_Main.this.preferences.getString("email", "") });
                                ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                                ((List)localObject2).add(localObject6);
                                break;
                                ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "");
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
                              Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                            }
                          }
                          else
                          {
                            Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296373), 0).show();
                          }
                        }
                      }
                      else {
                        Activity_Main.this.iapBuy.IAP_Buy();
                      }
                      break;
                    case 2: 
                      new Thread(new Runnable()
                      {
                        public void run()
                        {
                          int i = 0;
                          for (;;)
                          {
                            if (i < this.val$files_path.size())
                            {
                              File localFile1 = new File(((File)this.val$files_path.get(i)).getPath());
                              File localFile2 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TinyScan");
                              if (!localFile2.exists()) {
                                localFile2.mkdirs();
                              }
                              localFile2 = new File(localFile2.getPath() + "/" + localFile1.getName());
                              try
                              {
                                Activity_Main.this.copy(localFile1, localFile2);
                                MediaScannerConnection.scanFile(Activity_Main.this.getApplicationContext(), new String[] { localFile2.getAbsolutePath() }, null, null);
                                i += 1;
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
                          Message localMessage = new Message();
                          localMessage.what = 10;
                          Activity_Main.this.handler.sendMessage(localMessage);
                        }
                      }).start();
                      break;
                    case 3: 
                      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (((ArrayList)localObject3).size() > 1)
                        {
                          localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                          ((Intent)localObject2).setType("image/jpeg");
                          localObject4 = new ArrayList();
                          localObject2 = Activity_Main.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                          if (!((List)localObject2).isEmpty()) {
                            localObject5 = ((List)localObject2).iterator();
                          }
                        }
                        else
                        {
                          label9971:
                          label10014:
                          label10226:
                          label10239:
                          for (;;)
                          {
                            if (!((Iterator)localObject5).hasNext()) {
                              break label10241;
                            }
                            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                            if (((ArrayList)localObject3).size() > 1)
                            {
                              localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                              ((Intent)localObject2).setType("image/jpeg");
                              if (Activity_Main.idlist.size() == 0) {
                                break label10226;
                              }
                              ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_Main.idlist.get(0)).getName());
                            }
                            for (;;)
                            {
                              if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("dropbox")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("evernote")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.box.android")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("skydrive"))) {
                                break label10239;
                              }
                              ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                              ((Intent)localObject2).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                              ((List)localObject4).add(localObject2);
                              break label9971;
                              localObject2 = new Intent("android.intent.action.SEND");
                              break;
                              localObject2 = new Intent("android.intent.action.SEND");
                              break label10014;
                              ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner Pro");
                            }
                          }
                          label10241:
                          if (((List)localObject4).size() > 0)
                          {
                            localObject2 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share JPG file");
                            ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                            Activity_Main.this.startActivity((Intent)localObject2);
                          }
                          else
                          {
                            Toast.makeText(Activity_Main.activity_Main, "Error: Cannot open or share created JPG report.", 0).show();
                          }
                        }
                      }
                      else {
                        Activity_Main.this.iapBuy.IAP_Buy();
                      }
                      break;
                    }
                  }
                }
                break;
              }
            }
          }
        }
      }
    }
  };
  private boolean hasfolder;
  private IAPBuy iapBuy;
  private ArrayList<Photo_info> idlist_folder;
  private LayoutInflater inflater;
  private InterstitialAd interstitial;
  private boolean interstitialCanceled = false;
  private boolean isListView1OrListview2 = false;
  private boolean isRun_listfolders = false;
  private boolean isSearch = false;
  private boolean isSelect = false;
  private boolean isclick_cloud = false;
  private boolean isclick_systemdialog = false;
  private boolean isexitSearchMonth;
  private boolean islongclick = false;
  private boolean islongclick_folder = false;
  private boolean isrequestCheck = false;
  boolean isupload = false;
  private View.OnClickListener itemsOnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView) {}
  };
  private ArrayList<SortByDao> list;
  private int list_type;
  private LinearLayout listphotos;
  private MyApp mApp;
  private ACache mCache;
  private MyDbHelper mDbHelper;
  private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
  {
    public void onManagerConnected(int paramAnonymousInt)
    {
      super.onManagerConnected(paramAnonymousInt);
    }
  };
  private Thread mThread;
  private GridAdapter madapter;
  private ListAdapter madapter2;
  private Folder_ListAdapter madapter2_folder;
  private Folder_GridAdapter madapter_folder;
  private RelativeLayout main_camera_gallery_layout;
  private EditText main_edittext;
  private ImageView main_edittext_delete_imageview;
  private TextView main_file_textview_name;
  private TextView main_file_textview_name_space;
  private TextView main_folder_textview_name;
  private TextView main_folder_textview_name_space;
  private LinearLayout main_linear_folder;
  private RelativeLayout main_onlongclick_relativelayout;
  private ProgressBar main_progreebar;
  private ScrollView main_scrollview;
  private RelativeLayout mainactivity_layout1;
  private ImageView mainedit_documentname;
  private MyApplication mapp;
  private SelectPicPopupWindow menuWindow;
  private RelativeLayout mian_adslayout;
  private RelativeLayout mian_search_relativelayout;
  ArrayList<Photo_item> mlist = null;
  public List<Photo_info> mlist2;
  public ArrayList<Photo_info> mlist2_copy;
  private ArrayList<Photo_info> mlist2_folder;
  View.OnTouchListener mlistener = new View.OnTouchListener()
  {
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (paramAnonymousMotionEvent.getAction() == 0) {
        paramAnonymousView.setBackgroundColor(Color.rgb(77, 84, 94));
      }
      for (;;)
      {
        return false;
        if (paramAnonymousMotionEvent.getAction() == 1) {
          paramAnonymousView.setBackgroundColor(Color.rgb(77, 84, 94));
        } else if (paramAnonymousMotionEvent.getAction() == 2) {
          paramAnonymousView.setBackgroundColor(Color.rgb(66, 69, 72));
        }
      }
    }
  };
  private String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
  private ImageView moveto;
  View.OnClickListener myOnClickListener = new View.OnClickListener()
  {
    @SuppressLint({"InflateParams"})
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      case 2131755231: 
        Activity_Main.this.moveinMethod();
        return;
      case 2131755232: 
        Activity_Main.this.mergeDocumentMethod();
        return;
      case 2131755248: 
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (Activity_Main.this.isrequestCheck)
          {
            Activity_Main.this.takePicture(false);
            return;
          }
          Activity_Main.this.requstPermisstion();
          return;
        }
        Activity_Main.this.takePicture(false);
        return;
      case 2131755230: 
        Activity_Main.this.shareMethod(paramAnonymousView);
        return;
      case 2131755253: 
        Activity_Main.this.editdocumentname();
        return;
      case 2131755247: 
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
      case 2131755227: 
        if (Activity_Main.this.list_type == 0) {
          Activity_Main.this.madapter.isse = false;
        }
        while (Activity_Main.this.searchandclick) {
          if (Activity_Main.this.mapp.isPad())
          {
            Activity_Main.this.unselected3();
            return;
            Activity_Main.this.madapter2.isse = false;
          }
          else
          {
            Activity_Main.this.unselected2();
            return;
          }
        }
        Activity_Main.this.unselected();
        return;
      case 2131755233: 
        Activity_Main.this.deleteDocumentMethod();
        return;
      case 2131755251: 
        FlurryAgent.logEvent("Dismiss");
        Activity_Main.this.toolbar.setVisibility(0);
        Activity_Main.this.faxlink_layout.setVisibility(8);
        Activity_Main.this.mainactivity_layout1.setVisibility(0);
        return;
      }
      FlurryAgent.logEvent("Download");
      Activity_Main.this.toolbar.setVisibility(0);
      Activity_Main.this.faxlink_layout.setVisibility(8);
      Activity_Main.this.mainactivity_layout1.setVisibility(0);
      if (Activity_Main.this.preferences.getInt("newversion_1.2.5_faxpro", -1) == 0)
      {
        Util.showGooglePlayFax_pro(Activity_Main.activity_Main);
        return;
      }
      Util.showGooglePlayFax(Activity_Main.activity_Main);
    }
  };
  private String[] newCountryIAP_117 = { "US" };
  private String[] newCountryIAP_120 = { "US", "CA" };
  private String[] newCountryIAP_121 = { "US", "CA", "AU", "GB", "DE" };
  private String[] newCountryIAP_124 = { "US", "CA", "AU", "GB", "DE", "MX" };
  private HorizontalListView pad_listview1;
  private HorizontalListView pad_listview2;
  private ArrayList<HashMap<String, Object>> padexportlist1;
  private ArrayList<HashMap<String, Object>> padexportlist2;
  private List<HashMap<String, Object>> pdf_file_name;
  private boolean pdf_or_jpg = true;
  private RadioButton popu_radiobutton_jpg;
  private RadioButton popu_radiobutton_pdf;
  private RadioGroup popu_radiogroup;
  private PopupWindow popupWindow;
  private SharedPreferences preferences;
  private TextView price;
  private Dialog progressDialog;
  private String root_Path;
  private String root_Path2;
  private String root_Path3;
  private String root_Path4;
  private String root_Path5;
  private String root_Path6;
  public ArrayList<Photo_info> searchList;
  private String searchText = "";
  private boolean searchandclick = false;
  private LinearLayout selectline;
  private TextView selecttext;
  private ImageView share;
  private AlertDialog shareDialog;
  private View sortbyView;
  private SortbyPopupWindow sortbyWindow;
  private ListView sortby_Listview;
  private int times;
  private Toolbar toolbar;
  
  public Activity_Main() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void MultipleVersionslimit()
  {
    if (this.preferences.getInt("mathrandomSale", -1) != -1) {
      isShowAdvOrCharge();
    }
    if (this.preferences.getInt("newversion", -1) != -1) {
      newVersionShowDocOrfree();
    }
    if (this.preferences.getInt("newversion_1.1.7", -1) != -1) {
      newVersionShowDoc_117();
    }
    if (this.preferences.getInt("newversion_1.2.0", -1) != -1) {
      newVersionShowDoc_120();
    }
    if (this.preferences.getInt("newversion_1.2.1", -1) != -1) {
      newVersionShowDoc_121();
    }
    if (this.preferences.getInt("newversion_1.2.4", -1) != -1) {
      newVersionShowDoc_124();
    }
    if (this.mapp.getAdvOrChargeOrNormal() != 3)
    {
      Log.i("TAG", "=======getAdvOrChargeOrNormal");
      this.iapBuy = new IAPBuy(activity_Main, this.price, this.googleAdv_layout, this.mian_adslayout);
      this.iapBuy.buyGoogleAdvPro();
    }
  }
  
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
  
  private void addJpg_listdata()
  {
    this.padexportlist1 = new ArrayList();
    this.padexportlist2 = new ArrayList();
    int i;
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 3)
      {
        localHashMap = new HashMap();
        if (i == 2) {
          localHashMap.put("id", Integer.valueOf(4));
        }
        for (;;)
        {
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.padexportlist2.add(localHashMap);
          i += 1;
          break;
          localHashMap.put("id", Integer.valueOf(i));
        }
      }
    }
    else
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(true));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 3)
      {
        localHashMap = new HashMap();
        if (i == 2)
        {
          localHashMap.put("id", Integer.valueOf(4));
          label297:
          if (i != 1) {
            break label357;
          }
          localHashMap.put("isPro", Boolean.valueOf(true));
        }
        for (;;)
        {
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.padexportlist2.add(localHashMap);
          i += 1;
          break;
          localHashMap.put("id", Integer.valueOf(i));
          break label297;
          label357:
          localHashMap.put("isPro", Boolean.valueOf(false));
        }
      }
    }
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(false));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.padexportlist2.add(localHashMap);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(5));
    localHashMap.put("isPro", Boolean.valueOf(true));
    localHashMap.put("isEnable", Boolean.valueOf(true));
    this.padexportlist2.add(localHashMap);
  }
  
  private void addPDF_listdata()
  {
    this.padexportlist1 = new ArrayList();
    this.padexportlist2 = new ArrayList();
    int i;
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        localHashMap.put("isPro", Boolean.valueOf(false));
        if ((i == 2) || (i == 3) || (i == 4))
        {
          if (i == 3) {
            localHashMap.put("id", Integer.valueOf(6));
          }
          if (i == 4) {
            localHashMap.put("id", Integer.valueOf(3));
          }
          this.hasfolder = false;
          if (this.idlist_folder.size() > 0) {
            this.hasfolder = true;
          }
          if ((idlist.size() > 1) || (this.hasfolder)) {
            localHashMap.put("isEnable", Boolean.valueOf(false));
          }
        }
        else
        {
          label263:
          if (i != 3) {
            break label307;
          }
          if (Build.VERSION.SDK_INT >= 19) {
            this.padexportlist2.add(localHashMap);
          }
        }
        for (;;)
        {
          i += 1;
          break;
          localHashMap.put("isEnable", Boolean.valueOf(true));
          break label263;
          label307:
          this.padexportlist2.add(localHashMap);
        }
      }
    }
    else
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(true));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        if ((i == 1) || (i == 3))
        {
          localHashMap.put("isPro", Boolean.valueOf(true));
          label447:
          if ((i == 2) || (i == 3) || (i == 4))
          {
            if (i == 3) {
              localHashMap.put("id", Integer.valueOf(6));
            }
            if (i == 4) {
              localHashMap.put("id", Integer.valueOf(3));
            }
            this.hasfolder = false;
            if (this.idlist_folder.size() > 0) {
              this.hasfolder = true;
            }
            if ((idlist.size() <= 1) && (!this.hasfolder)) {
              break label592;
            }
            localHashMap.put("isEnable", Boolean.valueOf(false));
          }
          label548:
          if (i != 3) {
            break label607;
          }
          if (Build.VERSION.SDK_INT >= 19) {
            this.padexportlist2.add(localHashMap);
          }
        }
        for (;;)
        {
          i += 1;
          break;
          localHashMap.put("isPro", Boolean.valueOf(false));
          break label447;
          label592:
          localHashMap.put("isEnable", Boolean.valueOf(true));
          break label548;
          label607:
          this.padexportlist2.add(localHashMap);
        }
      }
    }
    if (!this.mapp.getIsContainCountry()) {
      this.padexportlist2.remove(2);
    }
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(false));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.padexportlist2.add(localHashMap);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(5));
    localHashMap.put("isPro", Boolean.valueOf(true));
    localHashMap.put("isEnable", Boolean.valueOf(true));
    this.padexportlist2.add(localHashMap);
  }
  
  private void addmlist2_listData()
  {
    File[] arrayOfFile;
    int k;
    int i;
    Object localObject3;
    int j;
    Object localObject7;
    label472:
    int m;
    Object localObject5;
    Object localObject10;
    if ((this.isSearch) || (this.searchandclick))
    {
      localSimpleDateFormat1 = new SimpleDateFormat("yyyy");
      localSimpleDateFormat2 = new SimpleDateFormat("MM");
      localSimpleDateFormat3 = new SimpleDateFormat("dd");
      this.searchList.clear();
      arrayOfFile = new File(this.root_Path3).listFiles();
      Object localObject1;
      Object localObject8;
      if (arrayOfFile != null)
      {
        k = arrayOfFile.length;
        i = 0;
        for (;;)
        {
          if (i < k)
          {
            Object localObject4;
            if ((arrayOfFile[i].isDirectory()) && (getShowName(arrayOfFile[i].getName()).toLowerCase().contains(this.searchText)))
            {
              localObject1 = arrayOfFile[i].list();
              localObject3 = new ArrayList();
              if (localObject1.length > 0)
              {
                j = 0;
                while (j < localObject1.length)
                {
                  if (localObject1[j].matches("[0-9]{18}.jpg")) {
                    ((List)localObject3).add(localObject1[j]);
                  }
                  j += 1;
                }
                if (((List)localObject3).size() > 0)
                {
                  localObject4 = localSimpleDateFormat1.format(new Date(arrayOfFile[i].lastModified()));
                  localObject1 = localSimpleDateFormat2.format(new Date(arrayOfFile[i].lastModified()));
                  localObject8 = localSimpleDateFormat3.format(new Date(arrayOfFile[i].lastModified()));
                  localObject7 = localSimpleDateFormat1.format(new Date(System.currentTimeMillis()));
                  localObject1 = this.month[(Integer.parseInt(localObject1) - 1)] + " " + (String)localObject8;
                  if (!((String)localObject4).equals(localObject7)) {
                    break label472;
                  }
                  System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
                }
              }
            }
            try
            {
              Collections.sort((List)localObject3, comparator3);
              localObject4 = new ArrayList();
              ((ArrayList)localObject4).add(arrayOfFile[i].getPath() + "/" + (String)((List)localObject3).get(0));
              localObject1 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject1, arrayOfFile[i].lastModified(), ((List)localObject3).size(), (ArrayList)localObject4, false, false);
              ((Photo_info)localObject1).setRotepath(this.root_Path3);
              this.searchList.add(localObject1);
              i += 1;
              continue;
              localObject1 = (String)localObject1 + " ,";
              localObject1 = (String)localObject1 + (String)localObject4;
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
      arrayOfFile = new File(this.root_Path4).listFiles();
      if (arrayOfFile != null)
      {
        m = arrayOfFile.length;
        i = 0;
        while (i < m)
        {
          if (arrayOfFile[i].isDirectory())
          {
            localObject3 = arrayOfFile[i].listFiles();
            if (localObject3 != null)
            {
              int n = localObject3.length;
              j = 0;
              for (;;)
              {
                if (j < n)
                {
                  localObject5 = localObject3[j];
                  if ((((File)localObject5).isDirectory()) && (getShowName(((File)localObject5).getName()).toLowerCase().contains(this.searchText)))
                  {
                    localObject1 = ((File)localObject5).list();
                    localObject7 = new ArrayList();
                    if (localObject1.length > 0)
                    {
                      k = 0;
                      while (k < localObject1.length)
                      {
                        if (localObject1[k].toLowerCase().matches("[0-9]{18}.jpg".toLowerCase())) {
                          ((List)localObject7).add(localObject1[k]);
                        }
                        k += 1;
                      }
                      if (((List)localObject7).size() > 0)
                      {
                        localObject8 = localSimpleDateFormat1.format(new Date(((File)localObject5).lastModified()));
                        localObject1 = localSimpleDateFormat2.format(new Date(((File)localObject5).lastModified()));
                        String str = localSimpleDateFormat3.format(new Date(((File)localObject5).lastModified()));
                        localObject10 = localSimpleDateFormat1.format(new Date(System.currentTimeMillis()));
                        localObject1 = this.month[(Integer.parseInt(localObject1) - 1)] + " " + str;
                        if (!((String)localObject8).equals(localObject10)) {
                          break label998;
                        }
                        System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
                      }
                    }
                  }
                  try
                  {
                    Collections.sort((List)localObject7, comparator3);
                    localObject8 = new ArrayList();
                    ((ArrayList)localObject8).add(((File)localObject5).getPath() + "/" + (String)((List)localObject7).get(0));
                    localObject1 = new Photo_info(((File)localObject5).getName(), getShowName(((File)localObject5).getName()), (String)localObject1, ((File)localObject5).lastModified(), ((List)localObject7).size(), (ArrayList)localObject8, false, false);
                    ((Photo_info)localObject1).setRotepath(arrayOfFile[i].getPath() + "/");
                    this.searchList.add(localObject1);
                    j += 1;
                    continue;
                    label998:
                    localObject1 = (String)localObject1 + " ,";
                    localObject1 = (String)localObject1 + (String)localObject8;
                  }
                  catch (Exception localException5)
                  {
                    for (;;)
                    {
                      localException5.printStackTrace();
                    }
                  }
                }
              }
            }
          }
          i += 1;
        }
      }
      System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
      try
      {
        if (sort_type == 0) {
          Collections.sort(this.searchList, comparator);
        }
        for (;;)
        {
          localObject1 = new Message();
          ((Message)localObject1).what = 8;
          this.handler.sendMessage((Message)localObject1);
          return;
          Collections.sort(this.searchList, comparator2);
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localException1.printStackTrace();
        }
      }
    }
    this.mlist2_copy.clear();
    this.list_type = this.preferences.getInt("list_type", 0);
    sort_type = this.preferences.getInt("sort_type", 0);
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy");
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("MM");
    SimpleDateFormat localSimpleDateFormat3 = new SimpleDateFormat("dd");
    Object localObject2;
    Object localObject9;
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
            if (arrayOfFile[i].isDirectory())
            {
              localObject2 = arrayOfFile[i].list();
              localObject3 = new ArrayList();
              if (localObject2.length > 0)
              {
                j = 0;
                while (j < localObject2.length)
                {
                  if (localObject2[j].matches("[0-9]{18}.jpg")) {
                    ((List)localObject3).add(localObject2[j]);
                  }
                  j += 1;
                }
                if (((List)localObject3).size() > 0)
                {
                  localObject5 = localSimpleDateFormat1.format(new Date(arrayOfFile[i].lastModified()));
                  localObject2 = localSimpleDateFormat2.format(new Date(arrayOfFile[i].lastModified()));
                  localObject9 = localSimpleDateFormat3.format(new Date(arrayOfFile[i].lastModified()));
                  localObject7 = localSimpleDateFormat1.format(new Date(System.currentTimeMillis()));
                  localObject2 = this.month[(Integer.parseInt(localObject2) - 1)] + " " + (String)localObject9;
                  if (!((String)localObject5).equals(localObject7)) {
                    break label1640;
                  }
                  System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
                }
              }
            }
            try
            {
              Collections.sort((List)localObject3, comparator3);
              localObject5 = new ArrayList();
              ((ArrayList)localObject5).add(arrayOfFile[i].getPath() + "/" + (String)((List)localObject3).get(0));
              localObject2 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject2, arrayOfFile[i].lastModified(), ((List)localObject3).size(), (ArrayList)localObject5, false, false);
              ((Photo_info)localObject2).setRotepath(this.root_Path3);
              this.mlist2_copy.add(localObject2);
              i += 1;
              continue;
              label1640:
              localObject2 = (String)localObject2 + ", ";
              localObject2 = (String)localObject2 + (String)localObject5;
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
        m = arrayOfFile.length;
        i = 0;
        if (i < m) {
          if (arrayOfFile[i].isDirectory())
          {
            localObject2 = arrayOfFile[i].listFiles();
            localObject3 = new ArrayList();
            k = localObject2.length;
            j = 0;
          }
        }
      }
    }
    for (;;)
    {
      if (j < k)
      {
        ((ArrayList)localObject3).add(localObject2[j]);
        System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
        try
        {
          if (sort_type == 0) {
            Collections.sort((List)localObject3, comparator4);
          } else {
            Collections.sort((List)localObject3, comparator5);
          }
        }
        catch (Exception localException4)
        {
          localException4.printStackTrace();
        }
      }
      Object localObject6 = localSimpleDateFormat1.format(new Date(arrayOfFile[i].lastModified()));
      localObject2 = localSimpleDateFormat2.format(new Date(arrayOfFile[i].lastModified()));
      localObject9 = localSimpleDateFormat3.format(new Date(arrayOfFile[i].lastModified()));
      localObject7 = localSimpleDateFormat1.format(new Date(System.currentTimeMillis()));
      localObject2 = this.month[(Integer.parseInt(localObject2) - 1)] + " " + (String)localObject9;
      if (((String)localObject6).equals(localObject7))
      {
        localObject6 = new ArrayList();
        j = 0;
        localObject3 = ((ArrayList)localObject3).iterator();
      }
      for (;;)
      {
        if (((Iterator)localObject3).hasNext())
        {
          localObject7 = (File)((Iterator)localObject3).next();
          if (!((File)localObject7).isDirectory()) {
            continue;
          }
          localObject10 = ((File)localObject7).list();
          localObject9 = new ArrayList();
          k = j;
          if (localObject10.length > 0)
          {
            k = 0;
            for (;;)
            {
              if (k < localObject10.length)
              {
                if (localObject10[k].matches("[0-9]{18}.jpg")) {
                  ((ArrayList)localObject9).add(localObject10[k]);
                }
                k += 1;
                continue;
                localObject2 = (String)localObject2 + " ,";
                localObject2 = (String)localObject2 + (String)localObject6;
                break;
              }
            }
            k = j;
            if (((ArrayList)localObject9).size() > 0) {
              System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
            }
          }
          try
          {
            Collections.sort((List)localObject9, comparator3);
            ((ArrayList)localObject6).add(((File)localObject7).getPath() + "/" + (String)((ArrayList)localObject9).get(0));
            k = j + 1;
            j = k;
          }
          catch (Exception localException6)
          {
            for (;;)
            {
              localException6.printStackTrace();
            }
          }
        }
      }
      localObject2 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject2, arrayOfFile[i].lastModified(), j, (ArrayList)localObject6, false, true);
      this.mlist2_copy.add(localObject2);
      i += 1;
      break;
      localObject2 = new Message();
      ((Message)localObject2).what = 6;
      this.handler.sendMessage((Message)localObject2);
      return;
      localObject2 = new Message();
      ((Message)localObject2).what = 7;
      this.handler.sendMessage((Message)localObject2);
      return;
      j += 1;
    }
  }
  
  private boolean checkGooglePlayServicesAvailable()
  {
    return !GooglePlayServicesUtil.isUserRecoverableError(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.context));
  }
  
  private void clickOne(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
      this.shareDialog.dismiss();
    }
    if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
      this.menuWindow.dismiss();
    }
    this.isListView1OrListview2 = false;
    this.export_select = paramInt;
    this.pdf_file_name = new ArrayList();
    this.pdf_file_name.clear();
    Object localObject1;
    int j;
    int i;
    label568:
    Object localObject3;
    if (this.export_size == 0)
    {
      localObject1 = new MyFilter2();
      paramInt = 0;
      if (paramInt < idlist.size())
      {
        if (((Photo_info)idlist.get(paramInt)).isFolder())
        {
          paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
          if (paramArrayList != null)
          {
            j = paramArrayList.length;
            i = 0;
            if (i < j)
            {
              Object localObject2 = paramArrayList[i];
              Object localObject4 = localObject2.listFiles((FilenameFilter)localObject1);
              if ((localObject4 != null) && (localObject4.length > 0)) {
                localObject4 = new File(localObject2.getPath() + "/" + localObject2.getName() + ".pdf");
              }
              for (;;)
              {
                try
                {
                  if ((Util.getFileSize((File)localObject4) == 0L) || (!Util.isOpenPdf(localObject2.getPath() + "/" + localObject2.getName() + ".pdf")))
                  {
                    localObject4 = new HashMap();
                    ((HashMap)localObject4).put("name", localObject2.getName());
                    ((HashMap)localObject4).put("isfolder", Boolean.valueOf(true));
                    ((HashMap)localObject4).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
                    ((HashMap)localObject4).put("path", localObject2.getPath());
                    this.pdf_file_name.add(localObject4);
                  }
                }
                catch (Exception localException)
                {
                  localException.printStackTrace();
                  continue;
                }
                i += 1;
                break;
                localObject4 = new HashMap();
                ((HashMap)localObject4).put("name", localException.getName());
                ((HashMap)localObject4).put("isfolder", Boolean.valueOf(true));
                ((HashMap)localObject4).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
                ((HashMap)localObject4).put("path", localException.getPath());
                this.pdf_file_name.add(localObject4);
              }
            }
          }
        }
        else
        {
          if (!this.isSearch) {
            break label751;
          }
          paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());
          localObject3 = paramArrayList.listFiles((FilenameFilter)localObject1);
          if ((localObject3 == null) || (localObject3.length <= 0)) {
            break label805;
          }
          localObject3 = new File(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf");
        }
        for (;;)
        {
          try
          {
            if ((Util.getFileSize((File)localObject3) == 0L) || (!Util.isOpenPdf(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf")))
            {
              localObject3 = new HashMap();
              ((HashMap)localObject3).put("name", paramArrayList.getName());
              ((HashMap)localObject3).put("isfolder", Boolean.valueOf(false));
              ((HashMap)localObject3).put("path", paramArrayList.getPath());
              this.pdf_file_name.add(localObject3);
            }
          }
          catch (Exception paramArrayList)
          {
            label751:
            paramArrayList.printStackTrace();
            continue;
          }
          paramInt += 1;
          break;
          paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName());
          break label568;
          label805:
          localObject3 = new HashMap();
          ((HashMap)localObject3).put("name", paramArrayList.getName());
          ((HashMap)localObject3).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject3).put("path", paramArrayList.getPath());
          this.pdf_file_name.add(localObject3);
        }
      }
      if (this.pdf_file_name.size() > 0) {
        createPDF();
      }
    }
    label1228:
    do
    {
      return;
      paramArrayList = new Message();
      paramArrayList.what = 3;
      this.handler.sendMessage(paramArrayList);
      return;
      paramInt = 0;
      if (paramInt < idlist.size())
      {
        if (((Photo_info)idlist.get(paramInt)).isFolder())
        {
          paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
          if (paramArrayList != null)
          {
            j = paramArrayList.length;
            i = 0;
            while (i < j)
            {
              localObject1 = paramArrayList[i];
              localObject3 = new HashMap();
              ((HashMap)localObject3).put("name", ((File)localObject1).getName());
              ((HashMap)localObject3).put("isfolder", Boolean.valueOf(true));
              ((HashMap)localObject3).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
              ((HashMap)localObject3).put("path", ((File)localObject1).getPath());
              this.pdf_file_name.add(localObject3);
              i += 1;
            }
          }
        }
        else
        {
          if (!this.isSearch) {
            break label1228;
          }
        }
        for (paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());; paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName()))
        {
          localObject1 = new HashMap();
          ((HashMap)localObject1).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject1).put("name", paramArrayList.getName());
          ((HashMap)localObject1).put("path", paramArrayList.getPath());
          this.pdf_file_name.add(localObject1);
          paramInt += 1;
          break;
        }
      }
    } while (this.pdf_file_name.size() <= 0);
    createPDF();
  }
  
  private void clickOne_jpg(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
      this.shareDialog.dismiss();
    }
    if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
      this.menuWindow.dismiss();
    }
    this.isListView1OrListview2 = false;
    this.export_select = paramInt;
    if (this.export_size == 0)
    {
      paramArrayList = new Message();
      paramArrayList.what = 33;
      this.handler.sendMessage(paramArrayList);
      return;
    }
    CompressImage();
  }
  
  private void clickTwo(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    this.isListView1OrListview2 = true;
    boolean bool = ((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue();
    int i;
    label133:
    label167:
    Object localObject1;
    int j;
    label306:
    Object localObject2;
    Object localObject5;
    if ((paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8) || (paramInt == 9))
    {
      i = paramInt % 6;
      if (!bool) {
        break label1058;
      }
      if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
        this.shareDialog.dismiss();
      }
      if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
        this.menuWindow.dismiss();
      }
      paramInt = i;
      if (!this.mapp.getIsContainCountry())
      {
        if (i != 2) {
          break label538;
        }
        paramInt = 3;
      }
      i = paramInt;
      if (this.mapp.isPad())
      {
        i = paramInt;
        if (this.mapp.getDensityDpi() == 213)
        {
          if (paramInt != 2) {
            break label560;
          }
          i = 3;
        }
      }
      if ((i != 0) && (i != 3)) {
        break label1465;
      }
      this.export_select = i;
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      if (this.export_size != 0) {
        break label1082;
      }
      localObject1 = new MyFilter2();
      paramInt = 0;
      if (paramInt >= idlist.size()) {
        break label1042;
      }
      if (!((Photo_info)idlist.get(paramInt)).isFolder()) {
        break label681;
      }
      paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
      if (paramArrayList == null) {
        break label918;
      }
      j = paramArrayList.length;
      i = 0;
      if (i >= j) {
        break label918;
      }
      localObject2 = paramArrayList[i];
      localObject5 = localObject2.listFiles((FilenameFilter)localObject1);
      if (localObject5 != null)
      {
        if (localObject5.length <= 0) {
          break label592;
        }
        localObject5 = new File(localObject2.getPath() + "/" + localObject2.getName() + ".pdf");
      }
    }
    for (;;)
    {
      try
      {
        if ((Util.getFileSize((File)localObject5) == 0L) || (!Util.isOpenPdf(localObject2.getPath() + "/" + localObject2.getName() + ".pdf")))
        {
          localObject5 = new HashMap();
          ((HashMap)localObject5).put("name", localObject2.getName());
          ((HashMap)localObject5).put("isfolder", Boolean.valueOf(true));
          ((HashMap)localObject5).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
          ((HashMap)localObject5).put("path", localObject2.getPath());
          this.pdf_file_name.add(localObject5);
        }
      }
      catch (Exception localException1)
      {
        label538:
        label560:
        localException1.printStackTrace();
        continue;
      }
      i += 1;
      break label306;
      i = paramInt % 5;
      break;
      if (i == 3)
      {
        paramInt = 4;
        break label133;
      }
      paramInt = i;
      if (i != 4) {
        break label133;
      }
      paramInt = 5;
      break label133;
      if (paramInt == 3)
      {
        i = 4;
        break label167;
      }
      i = paramInt;
      if (paramInt != 4) {
        break label167;
      }
      i = 5;
      break label167;
      label592:
      localObject5 = new HashMap();
      ((HashMap)localObject5).put("name", localException1.getName());
      ((HashMap)localObject5).put("isfolder", Boolean.valueOf(true));
      ((HashMap)localObject5).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
      ((HashMap)localObject5).put("path", localException1.getPath());
      this.pdf_file_name.add(localObject5);
    }
    label681:
    label742:
    Object localObject3;
    if (this.isSearch)
    {
      paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());
      localObject3 = paramArrayList.listFiles((FilenameFilter)localObject1);
      if ((localObject3 == null) || (localObject3.length <= 0)) {
        break label979;
      }
      localObject3 = new File(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf");
    }
    for (;;)
    {
      try
      {
        if ((Util.getFileSize((File)localObject3) == 0L) || (!Util.isOpenPdf(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf")))
        {
          localObject3 = new HashMap();
          ((HashMap)localObject3).put("name", paramArrayList.getName());
          ((HashMap)localObject3).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject3).put("path", paramArrayList.getPath());
          this.pdf_file_name.add(localObject3);
        }
      }
      catch (Exception paramArrayList)
      {
        label918:
        paramArrayList.printStackTrace();
        continue;
      }
      paramInt += 1;
      break;
      paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName());
      break label742;
      label979:
      localObject3 = new HashMap();
      ((HashMap)localObject3).put("name", paramArrayList.getName());
      ((HashMap)localObject3).put("isfolder", Boolean.valueOf(false));
      ((HashMap)localObject3).put("path", paramArrayList.getPath());
      this.pdf_file_name.add(localObject3);
    }
    label1042:
    if (this.pdf_file_name.size() > 0) {
      createPDF();
    }
    label1058:
    label1082:
    label1402:
    label1465:
    label1980:
    label2163:
    label2217:
    label2640:
    do
    {
      do
      {
        return;
        paramArrayList = new Message();
        paramArrayList.what = 3;
        this.handler.sendMessage(paramArrayList);
        return;
        paramInt = 0;
        if (paramInt < idlist.size())
        {
          if (((Photo_info)idlist.get(paramInt)).isFolder())
          {
            paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
            if (paramArrayList != null)
            {
              j = paramArrayList.length;
              i = 0;
              while (i < j)
              {
                localObject1 = paramArrayList[i];
                localObject3 = new HashMap();
                ((HashMap)localObject3).put("name", ((File)localObject1).getName());
                ((HashMap)localObject3).put("isfolder", Boolean.valueOf(true));
                ((HashMap)localObject3).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
                ((HashMap)localObject3).put("path", ((File)localObject1).getPath());
                this.pdf_file_name.add(localObject3);
                i += 1;
              }
            }
          }
          else
          {
            if (!this.isSearch) {
              break label1402;
            }
          }
          for (paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());; paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName()))
          {
            localObject1 = new HashMap();
            ((HashMap)localObject1).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject1).put("name", paramArrayList.getName());
            ((HashMap)localObject1).put("path", paramArrayList.getPath());
            this.pdf_file_name.add(localObject1);
            paramInt += 1;
            break;
          }
        }
      } while (this.pdf_file_name.size() <= 0);
      createPDF();
      return;
      this.export_select = i;
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      Object localObject4;
      if (this.export_size == 0)
      {
        localObject1 = new MyFilter2();
        paramInt = 0;
        if (paramInt < idlist.size())
        {
          if (((Photo_info)idlist.get(paramInt)).isFolder())
          {
            paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
            if (paramArrayList != null)
            {
              j = paramArrayList.length;
              i = 0;
              if (i < j)
              {
                localObject3 = paramArrayList[i];
                localObject5 = ((File)localObject3).listFiles((FilenameFilter)localObject1);
                if ((localObject5 != null) && (localObject5.length > 0)) {
                  localObject5 = new File(((File)localObject3).getPath() + "/" + ((File)localObject3).getName() + ".pdf");
                }
                for (;;)
                {
                  try
                  {
                    if ((Util.getFileSize((File)localObject5) == 0L) || (!Util.isOpenPdf(((File)localObject3).getPath() + "/" + ((File)localObject3).getName() + ".pdf")))
                    {
                      localObject5 = new HashMap();
                      ((HashMap)localObject5).put("name", ((File)localObject3).getName());
                      ((HashMap)localObject5).put("isfolder", Boolean.valueOf(true));
                      ((HashMap)localObject5).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
                      ((HashMap)localObject5).put("path", ((File)localObject3).getPath());
                      this.pdf_file_name.add(localObject5);
                    }
                  }
                  catch (Exception localException2)
                  {
                    localException2.printStackTrace();
                    continue;
                  }
                  i += 1;
                  break;
                  localObject5 = new HashMap();
                  ((HashMap)localObject5).put("name", localException2.getName());
                  ((HashMap)localObject5).put("isfolder", Boolean.valueOf(true));
                  ((HashMap)localObject5).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
                  ((HashMap)localObject5).put("path", localException2.getPath());
                  this.pdf_file_name.add(localObject5);
                }
              }
            }
          }
          else
          {
            if (!this.isSearch) {
              break label2163;
            }
            paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());
            localObject4 = paramArrayList.listFiles((FilenameFilter)localObject1);
            if ((localObject4 == null) || (localObject4.length <= 0)) {
              break label2217;
            }
            localObject4 = new File(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf");
          }
          for (;;)
          {
            try
            {
              if ((Util.getFileSize((File)localObject4) == 0L) || (!Util.isOpenPdf(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf")))
              {
                localObject4 = new HashMap();
                ((HashMap)localObject4).put("name", paramArrayList.getName());
                ((HashMap)localObject4).put("isfolder", Boolean.valueOf(false));
                ((HashMap)localObject4).put("path", paramArrayList.getPath());
                this.pdf_file_name.add(localObject4);
              }
            }
            catch (Exception paramArrayList)
            {
              paramArrayList.printStackTrace();
              continue;
            }
            paramInt += 1;
            break;
            paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName());
            break label1980;
            localObject4 = new HashMap();
            ((HashMap)localObject4).put("name", paramArrayList.getName());
            ((HashMap)localObject4).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject4).put("path", paramArrayList.getPath());
            this.pdf_file_name.add(localObject4);
          }
        }
        if (this.pdf_file_name.size() > 0)
        {
          createPDF();
          return;
        }
        paramArrayList = new Message();
        paramArrayList.what = 3;
        this.handler.sendMessage(paramArrayList);
        return;
      }
      paramInt = 0;
      if (paramInt < idlist.size())
      {
        if (((Photo_info)idlist.get(paramInt)).isFolder())
        {
          paramArrayList = new File(this.root_Path4 + ((Photo_info)idlist.get(paramInt)).getName()).listFiles();
          if (paramArrayList != null)
          {
            j = paramArrayList.length;
            i = 0;
            while (i < j)
            {
              localObject1 = paramArrayList[i];
              localObject4 = new HashMap();
              ((HashMap)localObject4).put("name", ((File)localObject1).getName());
              ((HashMap)localObject4).put("isfolder", Boolean.valueOf(true));
              ((HashMap)localObject4).put("folder", ((Photo_info)idlist.get(paramInt)).getName());
              ((HashMap)localObject4).put("path", ((File)localObject1).getPath());
              this.pdf_file_name.add(localObject4);
              i += 1;
            }
          }
        }
        else
        {
          if (!this.isSearch) {
            break label2640;
          }
        }
        for (paramArrayList = new File(((Photo_info)idlist.get(paramInt)).getRotepath() + ((Photo_info)idlist.get(paramInt)).getName());; paramArrayList = new File(this.root_Path3 + ((Photo_info)idlist.get(paramInt)).getName()))
        {
          localObject1 = new HashMap();
          ((HashMap)localObject1).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject1).put("name", paramArrayList.getName());
          ((HashMap)localObject1).put("path", paramArrayList.getPath());
          this.pdf_file_name.add(localObject1);
          paramInt += 1;
          break;
        }
      }
    } while (this.pdf_file_name.size() <= 0);
    createPDF();
  }
  
  private void clickTwo_jpg(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    this.isListView1OrListview2 = true;
    boolean bool = ((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue();
    int i;
    if ((paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8) || (paramInt == 9))
    {
      paramInt %= 5;
      if (bool)
      {
        if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
          this.shareDialog.dismiss();
        }
        if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
          this.menuWindow.dismiss();
        }
        i = paramInt;
        if (this.mapp.isPad())
        {
          i = paramInt;
          if (this.mapp.getDensityDpi() == 213)
          {
            if (paramInt != 2) {
              break label190;
            }
            i = 3;
          }
        }
      }
    }
    for (;;)
    {
      this.export_select = i;
      if (this.export_size != 0) {
        break label202;
      }
      paramArrayList = new Message();
      paramArrayList.what = 33;
      this.handler.sendMessage(paramArrayList);
      return;
      paramInt %= 5;
      break;
      label190:
      i = paramInt;
      if (paramInt == 3) {
        i = 4;
      }
    }
    label202:
    CompressImage();
  }
  
  private void dateCreateSortby()
  {
    sort_type = 0;
    System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
    try
    {
      Collections.sort(this.mlist2, comparator);
      Collections.sort(this.mlist2_folder, comparator);
      if (this.list_type == 0)
      {
        this.madapter.notifyDataSetChanged();
        this.madapter_folder.notifyDataSetChanged();
      }
      for (;;)
      {
        this.editor = this.preferences.edit();
        this.editor.putInt("sort_type", 0);
        this.editor.commit();
        return;
        this.madapter2.notifyDataSetChanged();
        this.madapter2_folder.notifyDataSetChanged();
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
  
  private String dateToString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(paramDate);
  }
  
  private void deleteDocumentMethod()
  {
    String str = getResources().getString(2131296363);
    new AlertDialog.Builder(this.context).setMessage(str).setPositiveButton(getResources().getString(2131296464), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            int i = 0;
            Object localObject2;
            int j;
            File[] arrayOfFile;
            int m;
            int k;
            while (i < Activity_Main.this.idlist_folder.size())
            {
              localObject1 = (Photo_info)Activity_Main.this.idlist_folder.get(i);
              localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.idlist_folder.get(i)).getName());
              if (((File)localObject1).exists())
              {
                if (((File)localObject1).isDirectory())
                {
                  localObject2 = ((File)localObject1).listFiles();
                  j = 0;
                  while (j < localObject2.length)
                  {
                    if (localObject2[j].isDirectory())
                    {
                      arrayOfFile = localObject2[j].listFiles();
                      m = arrayOfFile.length;
                      k = 0;
                      while (k < m)
                      {
                        arrayOfFile[k].delete();
                        k += 1;
                      }
                    }
                    localObject2[j].delete();
                    j += 1;
                  }
                }
                ((File)localObject1).delete();
                Activity_Main.this.mlist2_folder.remove(Activity_Main.this.idlist_folder.get(i));
                localObject1 = ((Photo_info)Activity_Main.this.idlist_folder.get(i)).getImage_name().iterator();
                while (((Iterator)localObject1).hasNext())
                {
                  localObject2 = (String)((Iterator)localObject1).next();
                  Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localObject2);
                }
              }
              i += 1;
            }
            i = 0;
            while (i < Activity_Main.idlist.size())
            {
              localObject1 = (Photo_info)Activity_Main.idlist.get(i);
              if (Activity_Main.this.isSearch)
              {
                localObject1 = new File(((Photo_info)Activity_Main.idlist.get(i)).getRotepath() + ((Photo_info)Activity_Main.idlist.get(i)).getName());
                if (!((File)localObject1).exists()) {
                  break label671;
                }
                if (((File)localObject1).isDirectory())
                {
                  localObject2 = ((File)localObject1).listFiles();
                  j = 0;
                }
              }
              else
              {
                for (;;)
                {
                  if (j >= localObject2.length) {
                    break label558;
                  }
                  if (localObject2[j].isDirectory())
                  {
                    arrayOfFile = localObject2[j].listFiles();
                    m = arrayOfFile.length;
                    k = 0;
                    for (;;)
                    {
                      if (k < m)
                      {
                        arrayOfFile[k].delete();
                        k += 1;
                        continue;
                        localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(i)).getName());
                        break;
                      }
                    }
                  }
                  localObject2[j].delete();
                  j += 1;
                }
              }
              label558:
              ((File)localObject1).delete();
              Activity_Main.this.mlist2.remove(Activity_Main.idlist.get(i));
              localObject1 = ((Photo_info)Activity_Main.idlist.get(i)).getImage_name().iterator();
              while (((Iterator)localObject1).hasNext())
              {
                localObject2 = (String)((Iterator)localObject1).next();
                Activity_Main.this.mapp.getmMemoryCache().remove("main" + (String)localObject2);
              }
              label671:
              i += 1;
            }
            Object localObject1 = new Message();
            ((Message)localObject1).what = 0;
            Activity_Main.this.handler.sendMessage((Message)localObject1);
          }
        }).start();
      }
    }).setNegativeButton(getResources().getString(2131296372), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }
  
  private void editdocumentname()
  {
    if (idlist.size() + this.idlist_folder.size() > 1)
    {
      Toast.makeText(this.context, getResources().getString(2131296466), 0).show();
      return;
    }
    if (idlist.size() == 1) {}
    for (this.editnameList = idlist;; this.editnameList = this.idlist_folder)
    {
      final View localView = this.inflater.inflate(2130903161, null);
      EditText localEditText = (EditText)localView.findViewById(2131755630);
      localEditText.setSelectAllOnFocus(true);
      localEditText.setText(((Photo_info)this.editnameList.get(0)).getShowname());
      new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131296485)).setView(localView).setPositiveButton(getResources().getString(2131296486), new DialogInterface.OnClickListener()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Object localObject1 = (EditText)localView.findViewById(2131755630);
          if (((EditText)localObject1).getText().toString().equals(((Photo_info)Activity_Main.this.editnameList.get(0)).getName()))
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
            Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131296410));
            return;
          }
          String str1 = ((EditText)localObject1).getText().toString().trim();
          localObject1 = ((EditText)localObject1).getText().toString().trim();
          Photo_info localPhoto_info = (Photo_info)Activity_Main.this.editnameList.get(0);
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
              break label671;
            }
          }
          for (;;)
          {
            if (!localPhoto_info.isFolder()) {
              break label837;
            }
            localObject2 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.editnameList.get(0)).getName());
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
            localObject1 = ((String)localObject1).replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject2;
            Activity_Main.this.saveNameToDb(str1, (String)localObject1);
            break;
            label671:
            localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.editnameList.get(0)).getName() + "/" + ((Photo_info)Activity_Main.this.editnameList.get(0)).getName() + ".pdf");
            if (((File)localObject2).exists()) {
              ((File)localObject2).renameTo(new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.editnameList.get(0)).getName() + "/" + (String)localObject1 + ".pdf"));
            }
          }
          label837:
          Object localObject2 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.editnameList.get(0)).getName());
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
            Activity_Main.access$2102(Activity_Main.this, "");
            Activity_Main.this.list_folders();
            paramAnonymousDialogInterface.dismiss();
            return;
            Activity_Main.this.unselected2();
            continue;
            Activity_Main.this.unselected();
          }
        }
      }).setNegativeButton(getResources().getString(2131296372), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).create().show();
      if (this.mapp.isPad()) {
        break;
      }
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
        }
      }, 100L);
      return;
    }
  }
  
  private void exitSearchMonth()
  {
    this.isexitSearchMonth = true;
    this.main_camera_gallery_layout.setVisibility(0);
    Util.hideKeyboard(this.main_edittext);
    this.mian_search_relativelayout.setVisibility(8);
    this.main_edittext.clearFocus();
    this.main_edittext.setText("");
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    this.toolbar.getMenu().findItem(2131755709).setVisible(true);
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(true);
    }
    for (;;)
    {
      this.toolbar.getMenu().findItem(2131755714).setVisible(true);
      this.toolbar.getMenu().findItem(2131755705).setVisible(true);
      this.toolbar.getMenu().findItem(2131755707).setVisible(true);
      clear();
      return;
      this.toolbar.getMenu().findItem(2131755711).setVisible(true);
      if (this.preferences.getInt("list_type", 0) == 0)
      {
        this.toolbar.getMenu().findItem(2131755712).setVisible(false);
        this.toolbar.getMenu().findItem(2131755713).setVisible(true);
      }
      else
      {
        this.toolbar.getMenu().findItem(2131755712).setVisible(true);
        this.toolbar.getMenu().findItem(2131755713).setVisible(false);
      }
    }
  }
  
  private void faxlinkMethod()
  {
    this.faxCount += 1;
    this.editor.putInt("faxCount", this.faxCount);
    this.editor.commit();
    if (this.preferences.getInt("faxCount", 0) >= 2) {
      this.toolbar.getMenu().findItem(2131755708).setVisible(false);
    }
    if (this.mapp.isPad())
    {
      View localView = this.inflater.inflate(2130903098, null);
      this.fax2linkDialog = new AlertDialog.Builder(this.context).setView(localView).setPositiveButton("Download", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FlurryAgent.logEvent("Download_Tab");
          paramAnonymousDialogInterface.dismiss();
          paramAnonymousDialogInterface = new String[1];
          paramAnonymousDialogInterface[0] = "US";
          int i = 0;
          paramAnonymousInt = 0;
          while (paramAnonymousInt < paramAnonymousDialogInterface.length)
          {
            if (Locale.getDefault().getCountry().equals(paramAnonymousDialogInterface[paramAnonymousInt])) {
              i = 1;
            }
            paramAnonymousInt += 1;
          }
          if (i != 0)
          {
            if (Activity_Main.this.preferences.getInt("newversion_1.2.5_faxpro", -1) == 0)
            {
              Util.showGooglePlayFax_pro(Activity_Main.activity_Main);
              return;
            }
            Util.showGooglePlayFax(Activity_Main.activity_Main);
            return;
          }
          Util.showGooglePlayFax11(Activity_Main.activity_Main);
        }
      }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FlurryAgent.logEvent("Dismiss_Tab");
          paramAnonymousDialogInterface.dismiss();
        }
      }).create();
      this.fax2linkDialog.show();
      return;
    }
    this.toolbar.setVisibility(8);
    this.faxlink_layout.setVisibility(0);
    this.mainactivity_layout1.setVisibility(8);
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
  
  private SharedPreferences getSharedPreferencesForCurrentUser()
  {
    return getSharedPreferences(this.mapp.getCurrentUser(), 0);
  }
  
  private long getfilesizeLength()
  {
    long l = 0L;
    this.mlist = new ArrayList();
    int i = 0;
    Object localObject;
    while (i < this.idlist_folder.size())
    {
      localObject = (Photo_info)this.idlist_folder.get(i);
      localObject = new File(this.root_Path4 + ((Photo_info)this.idlist_folder.get(i)).getName()).listFiles();
      if (localObject != null)
      {
        int j = 0;
        while (j < localObject.length)
        {
          TraverseImagesSize(new File(localObject[j].getPath()));
          j += 1;
        }
      }
      i += 1;
    }
    i = 0;
    if (i < idlist.size())
    {
      localObject = (Photo_info)idlist.get(i);
      if (this.isSearch) {}
      for (localObject = new File(((Photo_info)idlist.get(i)).getRotepath() + ((Photo_info)idlist.get(i)).getName());; localObject = new File(this.root_Path3 + ((Photo_info)idlist.get(i)).getName()))
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
  
  private void gridviewMethod()
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
  
  private boolean isFileName(String paramString)
  {
    paramString = new File(paramString).listFiles();
    if (paramString != null)
    {
      int i = 0;
      while (i < paramString.length)
      {
        if ((!paramString[i].isDirectory()) && (paramString[i].getName().trim().endsWith(".Tinyscanner_1_2_7"))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  private boolean isPad()
  {
    return (this.context.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  private void isShowAdvOrCharge()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    int i = 0;
    while (i < this.countryIAP.length)
    {
      if (str.equals(this.countryIAP[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("mathrandomSale", -1);
    if ((j != 0) && (i == 0) && (this.mapp.getIsShowAdv()))
    {
      this.mapp.setAdvOrChargeOrNormal(3);
      if (this.mapp.getIsBuyGoogleAds()) {
        this.mapp.setAdvOrChargeOrNormal(3);
      }
      if (this.mapp.getAdvOrChargeOrNormal() == 1)
      {
        if (!this.mapp.isPad()) {
          break label231;
        }
        if (!this.mapp.getIsShowBatch()) {
          break label211;
        }
        this.ggimage.setImageDrawable(getResources().getDrawable(2130837959));
      }
    }
    for (;;)
    {
      if (this.times >= 10)
      {
        showAd();
        this.googleAdv_layout.setVisibility(0);
      }
      return;
      if ((j != 0) && (i == 1) && (this.mapp.getIsFileLimitCount()))
      {
        this.mapp.setAdvOrChargeOrNormal(2);
        break;
      }
      this.mapp.setAdvOrChargeOrNormal(3);
      break;
      label211:
      this.ggimage.setImageDrawable(getResources().getDrawable(2130837788));
      continue;
      label231:
      if (this.mapp.getIsShowBatch()) {
        this.ggimage.setImageDrawable(getResources().getDrawable(2130837958));
      } else {
        this.ggimage.setImageDrawable(getResources().getDrawable(2130837957));
      }
    }
  }
  
  private void isShowImagefaxlink()
  {
    Object localObject = getResources().getConfiguration().locale;
    Locale.getDefault().getCountry();
    localObject = ((Locale)localObject).getLanguage();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "US";
    int j = 0;
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (Locale.getDefault().getCountry().equals(arrayOfString[i])) {
        j = 1;
      }
      i += 1;
    }
    if ((j != 0) && ("en".equals(localObject)) && ((this.faxCount == 0) || (this.faxCount == 1)) && (!Util.isPkgInstalled(activity_Main, "com.appxy.tinyfax")) && (!Util.isPkgInstalled(activity_Main, "com.appxy.tinyfaxplus")) && (this.preferences.getInt("mathrandom", -1) == 0)) {
      if (this.toolbar.getMenu().findItem(2131755708) != null) {
        this.toolbar.getMenu().findItem(2131755708).setVisible(true);
      }
    }
    do
    {
      do
      {
        return;
        if ((j != 0) || (!"en".equals(localObject)) || ((this.faxCount != 0) && (this.faxCount != 1)) || (Util.isPkgInstalled(activity_Main, "com.appxy.tinyfaxintl")) || (this.preferences.getInt("mathrandom", -1) != 0)) {
          break;
        }
      } while (this.toolbar.getMenu().findItem(2131755708) == null);
      this.toolbar.getMenu().findItem(2131755708).setVisible(true);
      return;
    } while (this.toolbar.getMenu().findItem(2131755708) == null);
    this.toolbar.getMenu().findItem(2131755708).setVisible(false);
  }
  
  private void listviewMethod()
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
  
  private void mergeDocumentMethod()
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
      if (idlist.size() == 1) {
        showToast(getResources().getString(2131296452));
      }
      do
      {
        return;
        localObject = this.inflater.inflate(2130903161, null);
        EditText localEditText = (EditText)((View)localObject).findViewById(2131755630);
        localEditText.setSelectAllOnFocus(true);
        localEditText.setText(((Photo_info)idlist.get(0)).getShowname());
        localObject = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131296451)).setView((View)localObject).setPositiveButton(getResources().getString(2131296486), new DialogInterface.OnClickListener()
        {
          @SuppressLint({"SimpleDateFormat"})
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Object localObject3 = (EditText)this.val$view.findViewById(2131755630);
            if (((EditText)localObject3).getText().toString().trim().equals(""))
            {
              Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131296410));
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
                  System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
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
            if (((EditText)localObject3).getText().toString().equals(((Photo_info)Activity_Main.idlist.get(0)).getShowname()))
            {
              localObject1 = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName()).list(new Activity_Main.MyFilter2(Activity_Main.this));
              if (localObject1 != null)
              {
                i = localObject1.length;
                paramAnonymousInt = 0;
                while (paramAnonymousInt < i)
                {
                  localObject2 = localObject1[paramAnonymousInt];
                  new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName() + "/" + (String)localObject2).delete();
                  paramAnonymousInt += 1;
                }
              }
            }
            else
            {
              localObject1 = ((EditText)localObject3).getText().toString().trim();
              localObject2 = ((EditText)localObject3).getText().toString().trim();
              if (Activity_Main.this.checkName((String)localObject1)) {}
              for (;;)
              {
                localObject1 = new File(Activity_Main.this.root_Path3 + (String)localObject1);
                new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.idlist.get(0)).getName()).renameTo((File)localObject1);
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
                localObject2 = ((String)localObject2).replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject3;
                Activity_Main.this.saveNameToDb((String)localObject1, (String)localObject2);
                localObject1 = localObject2;
              }
            }
            if (Activity_Main.this.searchandclick) {
              if (Activity_Main.this.mapp.isPad()) {
                Activity_Main.this.unselected3();
              }
            }
            for (;;)
            {
              Activity_Main.access$2102(Activity_Main.this, "");
              Activity_Main.this.list_folders();
              paramAnonymousDialogInterface.dismiss();
              return;
              Activity_Main.this.unselected2();
              continue;
              Activity_Main.this.unselected();
            }
          }
        }).setNegativeButton(getResources().getString(2131296372), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.dismiss();
          }
        }).create();
        ((AlertDialog)localObject).show();
      } while (this.mapp.isPad());
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          ((InputMethodManager)this.val$mDialog.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
        }
      }, 100L);
      return;
    }
    showToast(getResources().getString(2131296465));
  }
  
  private void moveinMethod()
  {
    int i = 0;
    if (this.idlist_folder.size() > 0) {
      i = 1;
    }
    if (i != 0)
    {
      showToast(getResources().getString(2131296466));
      return;
    }
    Object localObject1 = getLayoutInflater().inflate(2130903078, null);
    final ArrayList localArrayList = new ArrayList();
    Object localObject2 = this.mlist2_folder.iterator();
    while (((Iterator)localObject2).hasNext()) {
      localArrayList.add((Photo_info)((Iterator)localObject2).next());
    }
    localObject2 = new Photo_info();
    ((Photo_info)localObject2).setShowname(getResources().getString(2131296459));
    localArrayList.add(localObject2);
    localObject2 = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131296454)).setView((View)localObject1).setNegativeButton(getResources().getString(2131296372), null).create();
    ((AlertDialog)localObject2).show();
    localObject1 = (ListView)((View)localObject1).findViewById(2131755328);
    ((ListView)localObject1).setAdapter(new CopyAdapter(this.context, localArrayList, false));
    ((ListView)localObject1).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
      {
        this.val$mDialog.dismiss();
        if (paramAnonymousInt == localArrayList.size() - 1)
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
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName()).listFiles();
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
                  for (localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + 2 + ")"); ((File)localObject1).exists(); localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + i + ")")) {
                    i += 1;
                  }
                  localFile.renameTo((File)localObject1);
                }
              }
              for (;;)
              {
                Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + ((File)localObject1).getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
                break;
                Activity_Main.this.updateNameToDb(localPhoto_info.getName(), localPhoto_info.getShowname() + "(2)");
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
                localFile.renameTo((File)localObject1);
                continue;
                localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.10.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
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
  
  private void newVersionShowDocOrfree()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    int i = 0;
    while (i < this.newCountryIAP_117.length)
    {
      if (str.equals(this.newCountryIAP_117[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("newversion", -1);
    if ((j != 0) && (i == 1) && (this.mapp.getIsFileLimitCount()))
    {
      this.mapp.setAdvOrChargeOrNormal(2);
      return;
    }
    this.mapp.setAdvOrChargeOrNormal(3);
  }
  
  private void newVersionShowDoc_117()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    int i = 0;
    while (i < this.newCountryIAP_117.length)
    {
      if (str.equals(this.newCountryIAP_117[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("newversion_1.1.7", -1);
    if ((j != 0) && (i == 0) && (this.mapp.getIsFileLimitCount()))
    {
      this.mapp.setAdvOrChargeOrNormal(2);
      return;
    }
    this.mapp.setAdvOrChargeOrNormal(3);
  }
  
  private void newVersionShowDoc_120()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    int i = 0;
    while (i < this.newCountryIAP_120.length)
    {
      if (str.equals(this.newCountryIAP_120[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("newversion_1.2.0", -1);
    if ((j != 0) && (i == 0) && (this.mapp.getIsFileLimitCount()))
    {
      this.mapp.setAdvOrChargeOrNormal(2);
      return;
    }
    this.mapp.setAdvOrChargeOrNormal(3);
  }
  
  private void newVersionShowDoc_121()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    Log.i("TAG", "country===" + str);
    int i = 0;
    while (i < this.newCountryIAP_121.length)
    {
      if (str.equals(this.newCountryIAP_121[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("newversion_1.2.1", -1);
    if ((j != 0) && (i == 0) && (this.mapp.getIsFileLimitCount()))
    {
      this.mapp.setAdvOrChargeOrNormal(2);
      return;
    }
    this.mapp.setAdvOrChargeOrNormal(3);
  }
  
  @SuppressLint({"DefaultLocale"})
  private void newVersionShowDoc_124()
  {
    int j = 0;
    String str = Locale.getDefault().getCountry();
    Log.i("TAG", "country===" + str);
    int i = 0;
    while (i < this.newCountryIAP_124.length)
    {
      if (str.equals(this.newCountryIAP_124[i])) {
        j = 1;
      }
      i += 1;
    }
    i = this.preferences.getInt("newversion_1.2.4", -1);
    if ((j != 0) && (i == 0) && (this.mapp.getIsFileLimitCount()))
    {
      this.mapp.setAdvOrChargeOrNormal(2);
      Log.i("TAG", "=======setAdvOrChargeOrNormal");
      return;
    }
    if (this.preferences.getBoolean("CountryIAP_ads_user_chayeads", false))
    {
      this.mapp.setAdvOrChargeOrNormal(1);
      return;
    }
    this.mapp.setAdvOrChargeOrNormal(3);
  }
  
  private void onAuthenticated(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131296445), 1).show();
    }
  }
  
  private void onFolderSelected(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131296403), 1).show();
    }
  }
  
  @SuppressLint({"InflateParams"})
  private void ratenew1()
  {
    if (this.preferences.getInt("files", 0) > 0)
    {
      this.editor.putBoolean("limitRate", true);
      this.editor.commit();
      Object localObject = this.inflater.inflate(2130903128, null);
      Button localButton1 = (Button)((View)localObject).findViewById(2131755516);
      Button localButton2 = (Button)((View)localObject).findViewById(2131755515);
      localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
      ((AlertDialog)localObject).show();
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Object localObject = Activity_Main.this.getPackageManager().getInstalledApplications(0);
          int j = ((List)localObject).size();
          paramAnonymousView = null;
          int i = 0;
          while (i < j)
          {
            if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
              paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
            }
            i += 1;
          }
          if (paramAnonymousView != null)
          {
            localObject = new Intent("android.intent.action.VIEW");
            ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscanner&hl=en"));
            ((Intent)localObject).setPackage(paramAnonymousView.packageName);
            Activity_Main.this.startActivity((Intent)localObject);
          }
          for (;;)
          {
            if ((this.val$mDialog != null) && (this.val$mDialog.isShowing())) {
              this.val$mDialog.dismiss();
            }
            return;
            Activity_Main.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscanner&hl=en")));
          }
        }
      });
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((this.val$mDialog != null) && (this.val$mDialog.isShowing()))
          {
            Activity_Main.this.editor.putBoolean("beifenershiUser", true);
            Activity_Main.this.editor.commit();
            this.val$mDialog.dismiss();
          }
        }
      });
    }
  }
  
  private void requstPermisstion()
  {
    ActivityCompat.requestPermissions(this, this.PERMISSION, 0);
  }
  
  private void runOnresumeMethods()
  {
    this.isexitSearchMonth = false;
    MultipleVersionslimit();
    if (this.mapp.getIsBuyGoogleAds())
    {
      this.mapp.setAdvOrChargeOrNormal(3);
      if (this.googleAdv_layout.getVisibility() == 0) {
        this.googleAdv_layout.setVisibility(8);
      }
      if (this.mian_adslayout.getVisibility() == 0) {
        this.mian_adslayout.setVisibility(8);
      }
    }
    isShowImagefaxlink();
    Object localObject1 = this.mapp;
    MyApplication.initImageLoader(this.context);
    if (OpenCVLoader.initDebug()) {
      this.mLoaderCallback.onManagerConnected(0);
    }
    if (ExistSDCard())
    {
      makefolder();
      if ((new File(this.root_Path).exists()) && (this.preferences.getBoolean("isUpdate3", true)))
      {
        localObject1 = new File(this.root_Path);
        Object localObject3 = new File(this.root_Path3);
        Object localObject2 = new File(this.root_Path4);
        Object localObject4 = ((File)localObject1).listFiles();
        int k;
        int i;
        int j;
        if (localObject4 != null)
        {
          k = localObject4.length;
          i = 0;
          if (i < k)
          {
            Object localObject5 = localObject4[i];
            if (localObject5.isDirectory())
            {
              if ((localObject5.getPath().equals(((File)localObject3).getPath())) || (localObject5.getPath().equals(((File)localObject2).getPath()))) {
                break label483;
              }
              localObject1 = localObject5.listFiles();
              if ((localObject1 != null) && (localObject1.length > 0) && (localObject1[0].isFile()))
              {
                j = 0;
                for (localObject1 = new File(this.root_Path3 + localObject5.getName()); ((File)localObject1).exists(); localObject1 = new File(this.root_Path3 + localObject5.getName() + "(" + j + ")")) {
                  j += 1;
                }
                if (j == 0) {
                  break label442;
                }
                localObject5.renameTo(new File(this.root_Path3 + localObject5.getName() + "(" + j + ")"));
              }
            }
            for (;;)
            {
              i += 1;
              break;
              label442:
              localObject5.renameTo(new File(this.root_Path3 + localObject5.getName()));
              continue;
              label483:
              localObject1 = localObject5.listFiles();
              if ((localObject1 != null) && (localObject1.length > 0))
              {
                Object localObject6 = new ArrayList();
                int m = localObject1.length;
                j = 0;
                Object localObject7;
                while (j < m)
                {
                  localObject7 = localObject1[j];
                  if (((File)localObject7).isFile()) {
                    ((ArrayList)localObject6).add(localObject7);
                  }
                  j += 1;
                }
                if (((ArrayList)localObject6).size() > 0)
                {
                  new File(this.root_Path3 + localObject5.getName() + "/").mkdirs();
                  j = 0;
                  localObject6 = ((ArrayList)localObject6).iterator();
                  while (((Iterator)localObject6).hasNext())
                  {
                    localObject7 = (File)((Iterator)localObject6).next();
                    for (localObject1 = new File(this.root_Path3 + localObject5.getName() + "/" + ((File)localObject7).getName()); ((File)localObject1).exists(); localObject1 = new File(this.root_Path3 + localObject5.getName() + "/" + ((File)localObject7).getName() + "(" + j + ")")) {
                      j += 1;
                    }
                    if (j != 0) {
                      ((File)localObject7).renameTo(new File(this.root_Path3 + localObject5.getName() + "/" + ((File)localObject7).getName() + "(" + j + ")"));
                    } else {
                      ((File)localObject7).renameTo(new File(this.root_Path3 + localObject5.getName() + "/" + ((File)localObject7).getName()));
                    }
                  }
                }
              }
            }
          }
        }
        this.pdf_file_name = new ArrayList();
        this.pdf_file_name.clear();
        localObject1 = ((File)localObject3).listFiles();
        if (localObject1 != null)
        {
          i = 0;
          while (i < localObject1.length)
          {
            if (localObject1[i].isDirectory())
            {
              localObject3 = new HashMap();
              ((HashMap)localObject3).put("name", localObject1[i].getName());
              ((HashMap)localObject3).put("path", localObject1[i].getPath());
              this.pdf_file_name.add(localObject3);
            }
            i += 1;
          }
        }
        localObject1 = ((File)localObject2).listFiles();
        if (localObject1 != null)
        {
          i = 0;
          while (i < localObject1.length)
          {
            if (localObject1[i].isDirectory())
            {
              localObject2 = localObject1[i].listFiles();
              k = localObject2.length;
              j = 0;
              while (j < k)
              {
                localObject3 = localObject2[j];
                if (((File)localObject3).isDirectory())
                {
                  localObject4 = new HashMap();
                  ((HashMap)localObject4).put("name", ((File)localObject3).getName());
                  ((HashMap)localObject4).put("path", ((File)localObject3).getPath());
                  this.pdf_file_name.add(localObject4);
                }
                j += 1;
              }
            }
            i += 1;
          }
        }
        if (this.pdf_file_name.size() > 0) {
          createPDF2();
        }
      }
      this.searchText = "";
      list_folders();
      if (this.searchandclick) {
        if (this.mapp.isPad())
        {
          search3();
          getResults(this.main_edittext.getText().toString());
          selected();
          label1195:
          if (this.list_type != 0) {
            break label1324;
          }
          if (this.listphotos != null)
          {
            localObject1 = (GridView)this.listphotos.findViewById(2131755452);
            if (localObject1 != null) {
              ((GridView)localObject1).setSelection(this.preferences.getInt("folder_id", 0));
            }
          }
          label1247:
          if ((this.mapp.getAdvOrChargeOrNormal() != 3) || (this.preferences.getBoolean("limitRate", false))) {
            break label1393;
          }
          ratenew();
        }
      }
    }
    label1324:
    label1393:
    do
    {
      return;
      search();
      break;
      if (this.isSelect)
      {
        selected();
        break label1195;
      }
      if (!this.isSearch) {
        break label1195;
      }
      getResults(this.main_edittext.getText().toString());
      break label1195;
      if (this.listphotos == null) {
        break label1247;
      }
      localObject1 = (ListView)this.listphotos.findViewById(2131755453);
      if (localObject1 == null) {
        break label1247;
      }
      ((ListView)localObject1).setSelection(this.preferences.getInt("folder_id", 0));
      break label1247;
      Toast.makeText(this.context, getString(2131296489), 0).show();
      break label1247;
      if ((this.mapp.getAdvOrChargeOrNormal() == 3) && (this.preferences.getBoolean("beifenershiUser", false)))
      {
        ratenew();
        return;
      }
    } while ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.preferences.getBoolean("limitRate", false)) || (!this.preferences.getBoolean("isFirstTakingPictures", false)));
    ratenew1();
  }
  
  private void selectMothed()
  {
    this.islongclick_folder = true;
    this.islongclick = true;
    if (this.list_type == 0)
    {
      this.madapter.isse = true;
      this.madapter.notifyDataSetChanged();
      this.madapter_folder.isse = true;
      this.madapter_folder.notifyDataSetChanged();
    }
    for (;;)
    {
      selected();
      changeView();
      return;
      this.madapter2.isse = true;
      this.madapter2.notifyDataSetChanged();
      this.madapter2_folder.isse = true;
      this.madapter2_folder.notifyDataSetChanged();
    }
  }
  
  private void setFolder_file_isshow()
  {
    if (this.list_type == 0)
    {
      this.main_folder_textview_name_space.setVisibility(8);
      this.main_file_textview_name_space.setVisibility(8);
      if (this.mlist2.size() > 0) {
        this.main_file_textview_name.setVisibility(0);
      }
      while (this.mlist2_folder.size() > 0)
      {
        this.main_linear_folder.setVisibility(0);
        this.main_folder_textview_name.setVisibility(0);
        return;
        this.main_file_textview_name.setVisibility(8);
      }
      this.main_linear_folder.setVisibility(8);
      this.main_folder_textview_name.setVisibility(8);
      return;
    }
    if (this.mlist2.size() > 0)
    {
      this.main_file_textview_name.setVisibility(0);
      this.main_file_textview_name_space.setVisibility(0);
    }
    while (this.mlist2_folder.size() > 0)
    {
      this.main_linear_folder.setVisibility(0);
      this.main_folder_textview_name.setVisibility(0);
      this.main_folder_textview_name_space.setVisibility(0);
      return;
      this.main_file_textview_name.setVisibility(8);
      this.main_file_textview_name_space.setVisibility(8);
    }
    this.main_linear_folder.setVisibility(8);
    this.main_folder_textview_name.setVisibility(8);
    this.main_folder_textview_name_space.setVisibility(8);
  }
  
  private void shareMethod(View paramView)
  {
    this.country = new String[] { "US", "CA", "GB", "JP", "FR", "DE", "AR", "BR", "IL", "IN", "PT", "IT", "HK", "AU", "AT", "BE", "DK", "GR", "IE", "LU", "NL", "NO", "RO", "ES", "ZA", "SE", "CH" };
    int i = 0;
    while (i < this.country.length)
    {
      if (Locale.getDefault().getCountry().equals(this.country[i])) {
        this.mapp.setIsContainCountry(true);
      }
      i += 1;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (this.mapp.isPad())
    {
      sharepadAlterdialog();
      return;
    }
    this.menuWindow = new SelectPicPopupWindow(this, this.itemsOnClick, localDisplayMetrics.widthPixels);
    this.menuWindow.showAtLocation(paramView, 81, 0, 0);
  }
  
  private void showAd()
  {
    this.interstitial = new InterstitialAd(activity_Main);
    this.interstitial.setAdUnitId("ca-app-pub-2853676859073957/7068145829");
    this.interstitial.setAdListener(new AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt) {}
      
      public void onAdLoaded()
      {
        if (!Activity_Main.this.interstitialCanceled) {
          Activity_Main.this.interstitial.show();
        }
      }
    });
    this.interstitial.loadAd(new AdRequest.Builder().build());
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(5000L);
          Activity_Main.access$8902(Activity_Main.this, true);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    });
  }
  
  private void showCancelPermissionDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setMessage(2131296606);
    localBuilder.setNegativeButton(2131296572, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.finish();
      }
    });
    localBuilder.setPositiveButton(2131296611, new DialogInterface.OnClickListener()
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
  
  private void showMissingPermissionDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_Main);
    localBuilder.setMessage(2131296607);
    localBuilder.setNegativeButton(2131296583, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Activity_Main.this.finish();
      }
    });
    localBuilder.setPositiveButton(2131296578, new DialogInterface.OnClickListener()
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
  
  @SuppressLint({"InflateParams"})
  private void showProIapBuyDialog()
  {
    Object localObject = this.inflater.inflate(2130903104, null);
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this.context).setTitle(getString(2131296433)).setView((View)localObject).create();
    localAlertDialog.show();
    ImageView localImageView = (ImageView)((View)localObject).findViewById(2131755431);
    localObject = (ImageView)((View)localObject).findViewById(2131755433);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Activity_Main.this.iapBuy.IAP_Buy();
      }
    });
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1) {
          Activity_Main.this.sdCard(".Ads_Pro");
        }
        Object localObject;
        for (;;)
        {
          localAlertDialog.dismiss();
          localObject = Activity_Main.this.getPackageManager().getInstalledApplications(0);
          int j = ((List)localObject).size();
          paramAnonymousView = null;
          int i = 0;
          while (i < j)
          {
            if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
              paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
            }
            i += 1;
          }
          if (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 2) {
            Activity_Main.this.sdCard(".Doc_Pro");
          }
        }
        try
        {
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscan&hl=en"));
          ((Intent)localObject).setPackage(paramAnonymousView.packageName);
          Activity_Main.this.startActivity((Intent)localObject);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          Activity_Main.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscan&hl=en")));
        }
      }
    });
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
  
  private void titleSortby()
  {
    sort_type = 1;
    System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
    try
    {
      Collections.sort(this.mlist2, comparator2);
      Collections.sort(this.mlist2_folder, comparator2);
      if (this.list_type == 0)
      {
        this.madapter.notifyDataSetChanged();
        this.madapter_folder.notifyDataSetChanged();
      }
      for (;;)
      {
        this.editor = this.preferences.edit();
        this.editor.putInt("sort_type", 1);
        this.editor.commit();
        return;
        this.madapter2.notifyDataSetChanged();
        this.madapter2_folder.notifyDataSetChanged();
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
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.progressDialog.setCancelable(true);
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
        while (i < Activity_Main.this.idlist_folder.size())
        {
          localObject1 = (Photo_info)Activity_Main.this.idlist_folder.get(i);
          localObject1 = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.idlist_folder.get(i)).getName()).listFiles();
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
                break label801;
              }
              localIOException2.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream2);
              break label801;
            }
            j += 1;
          }
          i += 1;
        }
        i = 0;
        label406:
        if (i < Activity_Main.idlist.size())
        {
          localObject1 = (Photo_info)Activity_Main.idlist.get(i);
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
            break label406;
            localObject1 = new Message();
            ((Message)localObject1).what = 33;
            Activity_Main.this.handler.sendMessage((Message)localObject1);
            return;
            label801:
            k += 1;
            break;
          }
          j += 1;
        }
      }
    });
    this.mThread.start();
  }
  
  public void changeView()
  {
    if (idlist.size() + this.idlist_folder.size() > 1) {
      if (this.mapp.isPad())
      {
        this.mainedit_documentname.setEnabled(false);
        this.mainedit_documentname.setImageResource(2130837970);
      }
    }
    int j;
    int i;
    for (;;)
    {
      j = 0;
      i = 0;
      if (this.idlist_folder.size() > 0) {
        i = 1;
      }
      Iterator localIterator = idlist.iterator();
      while (localIterator.hasNext())
      {
        Photo_info localPhoto_info = (Photo_info)localIterator.next();
        j += 1;
      }
      if (this.mapp.isPad())
      {
        this.mainedit_documentname.setEnabled(true);
        this.mainedit_documentname.setImageResource(2130837969);
      }
    }
    if ((i != 0) || (this.isSearch))
    {
      this.moveto.setEnabled(false);
      this.moveto.setImageResource(2130837708);
      if (this.isSearch) {
        this.moveto.setVisibility(8);
      }
      if ((j <= 1) || (i != 0) || (this.isSearch)) {
        break label333;
      }
      this.getall.setEnabled(true);
      this.getall.setImageResource(2130837791);
    }
    for (;;)
    {
      if (idlist.size() + this.idlist_folder.size() != 0) {
        break label370;
      }
      this.share.setEnabled(false);
      this.share.setImageResource(2130837796);
      if (this.mapp.isPad())
      {
        this.mainedit_documentname.setEnabled(false);
        this.mainedit_documentname.setImageResource(2130837970);
      }
      this.moveto.setEnabled(false);
      this.moveto.setImageResource(2130837708);
      this.delete.setEnabled(false);
      this.delete.setImageResource(2130837981);
      return;
      this.moveto.setEnabled(true);
      this.moveto.setImageResource(2130837707);
      break;
      label333:
      this.getall.setEnabled(false);
      this.getall.setImageResource(2130837792);
      if (this.isSearch) {
        this.getall.setVisibility(8);
      }
    }
    label370:
    this.share.setEnabled(true);
    this.share.setImageResource(2130837795);
    this.delete.setEnabled(true);
    this.delete.setImageResource(2130837980);
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
    return (!paramString.contains("*")) && (!paramString.contains("/")) && (!paramString.contains("\\")) && (!paramString.contains("\"")) && (!paramString.contains("?")) && (!paramString.contains(":")) && (!paramString.contains("|")) && (!paramString.contains("<")) && (!paramString.contains(">"));
  }
  
  public void clear()
  {
    isShowImagefaxlink();
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(true);
    }
    this.searchText = "";
    list_folders();
    this.isSearch = false;
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
  
  @SuppressLint({"SimpleDateFormat"})
  public void createNewFolder(String paramString1, final String paramString2, boolean paramBoolean)
  {
    if (checkName(paramString1))
    {
      paramString2 = new File(this.root_Path4 + paramString1);
      paramString2.mkdirs();
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
              ((File)localObject).renameTo(new File(Activity_Main.this.root_Path4 + paramString2.getName() + "/" + localPhoto_info.getName()));
              Activity_Main.this.mapp.addBitmapToMemoryCache("main" + Activity_Main.this.root_Path4 + paramString2.getName() + "/" + localPhoto_info.getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
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
    label295:
    label460:
    do
    {
      for (;;)
      {
        return;
        Object localObject1 = new Timestamp(System.currentTimeMillis());
        localObject1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject1);
        paramString2 = paramString2.replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject1;
        saveNameToDb(paramString2, paramString1);
        paramString1 = paramString2;
        break;
        paramString1 = new SimpleDateFormat("yyyy");
        Object localObject2 = new SimpleDateFormat("MM");
        Object localObject3 = new SimpleDateFormat("dd");
        localObject1 = paramString1.format(new Date(paramString2.lastModified()));
        localObject2 = ((SimpleDateFormat)localObject2).format(new Date(paramString2.lastModified()));
        localObject3 = ((SimpleDateFormat)localObject3).format(new Date(paramString2.lastModified()));
        String str = paramString1.format(new Date(System.currentTimeMillis()));
        paramString1 = this.month[(Integer.parseInt(localObject2) - 1)] + " " + (String)localObject3;
        if (((String)localObject1).equals(str))
        {
          localObject1 = new ArrayList();
          paramString1 = new Photo_info(paramString2.getName(), getShowName(paramString2.getName()), paramString1, paramString2.lastModified(), 0, (ArrayList)localObject1, false, true);
          this.mlist2_folder.add(paramString1);
          System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
        }
        try
        {
          if (sort_type == 0) {
            Collections.sort(this.mlist2_folder, comparator);
          }
          for (;;)
          {
            setFolder_file_isshow();
            if (this.list_type != 0) {
              break label460;
            }
            if (this.madapter_folder == null) {
              break;
            }
            this.madapter_folder.notifyDataSetChanged();
            return;
            paramString1 = paramString1 + " ,";
            paramString1 = paramString1 + (String)localObject1;
            break label295;
            Collections.sort(this.mlist2_folder, comparator2);
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
    } while (this.madapter2_folder == null);
    this.madapter2_folder.notifyDataSetChanged();
  }
  
  public void createPDF()
  {
    int j = 0;
    Object localObject = new File(this.root_Path5);
    int k;
    int i;
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles();
      if (localObject != null)
      {
        k = localObject.length;
        i = 0;
        while (i < k)
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
        k = localObject.length;
        i = j;
        while (i < k)
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
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.progressDialog.setCancelable(true);
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
        //   4: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   7: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   10: invokeinterface 41 1 0
        //   15: if_icmpge +1337 -> 1352
        //   18: new 43	java/io/File
        //   21: dup
        //   22: aload_0
        //   23: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   26: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   29: iload_1
        //   30: invokeinterface 47 2 0
        //   35: checkcast 49	java/util/HashMap
        //   38: ldc 51
        //   40: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   43: checkcast 56	java/lang/String
        //   46: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   49: astore 7
        //   51: aload_0
        //   52: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   55: invokestatic 63	com/appxy/tinyscanfree/Activity_Main:access$4000	(Lcom/appxy/tinyscanfree/Activity_Main;)I
        //   58: ifne +190 -> 248
        //   61: aload 7
        //   63: new 65	com/appxy/tinyscanfree/Activity_Main$MyFilter2
        //   66: dup
        //   67: aload_0
        //   68: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   71: invokespecial 67	com/appxy/tinyscanfree/Activity_Main$MyFilter2:<init>	(Lcom/appxy/tinyscanfree/Activity_Main;)V
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
        //   110: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   113: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
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
        //   142: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   145: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
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
        //   208: iconst_0
        //   209: istore_2
        //   210: iload_2
        //   211: aload 5
        //   213: arraylength
        //   214: if_icmpge +263 -> 477
        //   217: aload 5
        //   219: iload_2
        //   220: aaload
        //   221: ldc 107
        //   223: invokevirtual 111	java/lang/String:matches	(Ljava/lang/String;)Z
        //   226: ifeq +15 -> 241
        //   229: aload 8
        //   231: aload 5
        //   233: iload_2
        //   234: aaload
        //   235: invokeinterface 115 2 0
        //   240: pop
        //   241: iload_2
        //   242: iconst_1
        //   243: iadd
        //   244: istore_2
        //   245: goto -35 -> 210
        //   248: aload_0
        //   249: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   252: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   255: iload_1
        //   256: invokeinterface 47 2 0
        //   261: checkcast 49	java/util/HashMap
        //   264: ldc 117
        //   266: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   269: checkcast 119	java/lang/Boolean
        //   272: invokevirtual 122	java/lang/Boolean:booleanValue	()Z
        //   275: ifeq +141 -> 416
        //   278: aload_0
        //   279: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   282: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   285: iload_1
        //   286: invokeinterface 47 2 0
        //   291: checkcast 49	java/util/HashMap
        //   294: ldc 124
        //   296: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   299: checkcast 56	java/lang/String
        //   302: astore 4
        //   304: new 43	java/io/File
        //   307: dup
        //   308: new 77	java/lang/StringBuilder
        //   311: dup
        //   312: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   315: aload_0
        //   316: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   319: invokestatic 128	com/appxy/tinyscanfree/Activity_Main:access$6300	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   322: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   325: aload 4
        //   327: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   333: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   336: astore 4
        //   338: aload 4
        //   340: invokevirtual 98	java/io/File:exists	()Z
        //   343: ifne +9 -> 352
        //   346: aload 4
        //   348: invokevirtual 131	java/io/File:mkdirs	()Z
        //   351: pop
        //   352: new 43	java/io/File
        //   355: dup
        //   356: new 77	java/lang/StringBuilder
        //   359: dup
        //   360: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   363: aload 4
        //   365: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   368: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: ldc 84
        //   373: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: aload_0
        //   377: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   380: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   383: iload_1
        //   384: invokeinterface 47 2 0
        //   389: checkcast 49	java/util/HashMap
        //   392: ldc 86
        //   394: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   397: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   400: ldc 91
        //   402: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   405: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   408: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   411: astore 4
        //   413: goto -235 -> 178
        //   416: new 43	java/io/File
        //   419: dup
        //   420: new 77	java/lang/StringBuilder
        //   423: dup
        //   424: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   427: aload_0
        //   428: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   431: invokestatic 137	com/appxy/tinyscanfree/Activity_Main:access$6200	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   434: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: aload_0
        //   438: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   441: invokestatic 35	com/appxy/tinyscanfree/Activity_Main:access$7500	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/util/List;
        //   444: iload_1
        //   445: invokeinterface 47 2 0
        //   450: checkcast 49	java/util/HashMap
        //   453: ldc 86
        //   455: invokevirtual 54	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   458: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   461: ldc 91
        //   463: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   469: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   472: astore 4
        //   474: goto -296 -> 178
        //   477: ldc -117
        //   479: ldc -115
        //   481: invokestatic 147	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   484: pop
        //   485: aload 8
        //   487: getstatic 151	com/appxy/tinyscanfree/Activity_Main:comparator3	Ljava/util/Comparator;
        //   490: invokestatic 157	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   493: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   496: astore 5
        //   498: new 165	com/itextpdf/text/Document
        //   501: dup
        //   502: invokespecial 166	com/itextpdf/text/Document:<init>	()V
        //   505: astore 9
        //   507: aconst_null
        //   508: astore 6
        //   510: aconst_null
        //   511: astore 5
        //   513: new 168	com/itextpdf/text/pdf/PdfCopy
        //   516: dup
        //   517: aload 9
        //   519: new 170	java/io/FileOutputStream
        //   522: dup
        //   523: aload 4
        //   525: invokespecial 173	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   528: invokespecial 176	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   531: astore 4
        //   533: aload 9
        //   535: invokevirtual 179	com/itextpdf/text/Document:open	()V
        //   538: aload 4
        //   540: astore 5
        //   542: aload 8
        //   544: invokeinterface 41 1 0
        //   549: istore_3
        //   550: iconst_0
        //   551: istore_2
        //   552: iload_2
        //   553: iload_3
        //   554: if_icmpge +786 -> 1340
        //   557: aload 8
        //   559: iload_2
        //   560: invokeinterface 47 2 0
        //   565: checkcast 56	java/lang/String
        //   568: bipush 14
        //   570: bipush 15
        //   572: invokevirtual 183	java/lang/String:substring	(II)Ljava/lang/String;
        //   575: invokestatic 189	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   578: tableswitch	default:+38->616, 0:+434->1012, 1:+442->1020, 2:+450->1028, 3:+458->1036, 4:+466->1044, 5:+474->1052
        //   616: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   619: astore 4
        //   621: new 165	com/itextpdf/text/Document
        //   624: dup
        //   625: aload 4
        //   627: invokespecial 192	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   630: astore 6
        //   632: aload_0
        //   633: aload 6
        //   635: new 170	java/io/FileOutputStream
        //   638: dup
        //   639: new 77	java/lang/StringBuilder
        //   642: dup
        //   643: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   646: aload_0
        //   647: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   650: invokestatic 195	com/appxy/tinyscanfree/Activity_Main:access$7600	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   653: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   656: aload 8
        //   658: iload_2
        //   659: invokeinterface 47 2 0
        //   664: checkcast 56	java/lang/String
        //   667: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   670: ldc 91
        //   672: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   675: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   678: invokespecial 196	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   681: invokestatic 202	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   684: putfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   687: aload 6
        //   689: invokevirtual 179	com/itextpdf/text/Document:open	()V
        //   692: aconst_null
        //   693: astore 4
        //   695: aload_0
        //   696: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   699: invokestatic 63	com/appxy/tinyscanfree/Activity_Main:access$4000	(Lcom/appxy/tinyscanfree/Activity_Main;)I
        //   702: ifne +368 -> 1070
        //   705: new 77	java/lang/StringBuilder
        //   708: dup
        //   709: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   712: aload 7
        //   714: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   717: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   720: ldc 84
        //   722: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   725: aload 8
        //   727: iload_2
        //   728: invokeinterface 47 2 0
        //   733: checkcast 56	java/lang/String
        //   736: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   739: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   742: invokestatic 209	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   745: astore 4
        //   747: aload 4
        //   749: invokevirtual 213	com/itextpdf/text/Image:getWidth	()F
        //   752: aload 6
        //   754: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   757: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   760: fcmpl
        //   761: ifge +20 -> 781
        //   764: aload 4
        //   766: invokevirtual 223	com/itextpdf/text/Image:getHeight	()F
        //   769: aload 6
        //   771: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   774: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   777: fcmpl
        //   778: iflt +13 -> 791
        //   781: aload 4
        //   783: aload 6
        //   785: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   788: invokevirtual 227	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   791: aload 4
        //   793: aload 6
        //   795: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   798: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   801: aload 4
        //   803: invokevirtual 230	com/itextpdf/text/Image:getScaledWidth	()F
        //   806: fsub
        //   807: fconst_2
        //   808: fdiv
        //   809: aload 6
        //   811: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   814: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   817: aload 4
        //   819: invokevirtual 233	com/itextpdf/text/Image:getScaledHeight	()F
        //   822: fsub
        //   823: fconst_2
        //   824: fdiv
        //   825: invokevirtual 237	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   828: aload 6
        //   830: aload 4
        //   832: invokevirtual 240	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   835: pop
        //   836: aload_0
        //   837: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   840: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   843: ifeq +18 -> 861
        //   846: aload_0
        //   847: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   850: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   853: ifne +8 -> 861
        //   856: aload 6
        //   858: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   861: new 251	com/itextpdf/text/pdf/PdfReader
        //   864: dup
        //   865: new 77	java/lang/StringBuilder
        //   868: dup
        //   869: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   872: aload_0
        //   873: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   876: invokestatic 195	com/appxy/tinyscanfree/Activity_Main:access$7600	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   879: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   882: aload 8
        //   884: iload_2
        //   885: invokeinterface 47 2 0
        //   890: checkcast 56	java/lang/String
        //   893: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: ldc 91
        //   898: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   901: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   904: invokespecial 252	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   907: astore 4
        //   909: aload 5
        //   911: aload 4
        //   913: invokevirtual 256	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   916: aload 4
        //   918: invokevirtual 257	com/itextpdf/text/pdf/PdfReader:close	()V
        //   921: new 43	java/io/File
        //   924: dup
        //   925: new 77	java/lang/StringBuilder
        //   928: dup
        //   929: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   932: aload_0
        //   933: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   936: invokestatic 195	com/appxy/tinyscanfree/Activity_Main:access$7600	(Lcom/appxy/tinyscanfree/Activity_Main;)Ljava/lang/String;
        //   939: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   942: aload 8
        //   944: iload_2
        //   945: invokeinterface 47 2 0
        //   950: checkcast 56	java/lang/String
        //   953: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   956: ldc 91
        //   958: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   961: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   964: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   967: invokevirtual 75	java/io/File:delete	()Z
        //   970: pop
        //   971: iload_2
        //   972: iconst_1
        //   973: iadd
        //   974: istore_2
        //   975: goto -423 -> 552
        //   978: astore 5
        //   980: aload 5
        //   982: invokevirtual 260	java/lang/Exception:printStackTrace	()V
        //   985: goto -492 -> 493
        //   988: astore 4
        //   990: aload 4
        //   992: invokevirtual 261	java/io/FileNotFoundException:printStackTrace	()V
        //   995: goto -453 -> 542
        //   998: astore 4
        //   1000: aload 6
        //   1002: astore 5
        //   1004: aload 4
        //   1006: invokevirtual 262	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1009: goto -467 -> 542
        //   1012: getstatic 265	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   1015: astore 4
        //   1017: goto -396 -> 621
        //   1020: getstatic 163	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   1023: astore 4
        //   1025: goto -404 -> 621
        //   1028: getstatic 268	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   1031: astore 4
        //   1033: goto -412 -> 621
        //   1036: getstatic 271	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   1039: astore 4
        //   1041: goto -420 -> 621
        //   1044: getstatic 274	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   1047: astore 4
        //   1049: goto -428 -> 621
        //   1052: new 219	com/itextpdf/text/Rectangle
        //   1055: dup
        //   1056: ldc_w 275
        //   1059: ldc_w 276
        //   1062: invokespecial 278	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   1065: astore 4
        //   1067: goto -446 -> 621
        //   1070: new 280	java/io/FileInputStream
        //   1073: dup
        //   1074: new 43	java/io/File
        //   1077: dup
        //   1078: new 77	java/lang/StringBuilder
        //   1081: dup
        //   1082: invokespecial 78	java/lang/StringBuilder:<init>	()V
        //   1085: aload 7
        //   1087: invokevirtual 134	java/io/File:getPath	()Ljava/lang/String;
        //   1090: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1093: ldc 84
        //   1095: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1098: aload 8
        //   1100: iload_2
        //   1101: invokeinterface 47 2 0
        //   1106: checkcast 56	java/lang/String
        //   1109: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1112: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1115: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
        //   1118: invokespecial 281	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   1121: invokestatic 287	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   1124: astore 10
        //   1126: new 289	java/io/ByteArrayOutputStream
        //   1129: dup
        //   1130: invokespecial 290	java/io/ByteArrayOutputStream:<init>	()V
        //   1133: astore 11
        //   1135: aload_0
        //   1136: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   1139: invokestatic 63	com/appxy/tinyscanfree/Activity_Main:access$4000	(Lcom/appxy/tinyscanfree/Activity_Main;)I
        //   1142: iconst_1
        //   1143: if_icmpne +29 -> 1172
        //   1146: aload 10
        //   1148: getstatic 296	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1151: bipush 50
        //   1153: aload 11
        //   1155: invokevirtual 302	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1158: pop
        //   1159: aload 11
        //   1161: invokevirtual 306	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1164: invokestatic 309	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1167: astore 4
        //   1169: goto -422 -> 747
        //   1172: aload_0
        //   1173: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   1176: invokestatic 63	com/appxy/tinyscanfree/Activity_Main:access$4000	(Lcom/appxy/tinyscanfree/Activity_Main;)I
        //   1179: iconst_2
        //   1180: if_icmpne -433 -> 747
        //   1183: aload 10
        //   1185: getstatic 296	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1188: iconst_5
        //   1189: aload 11
        //   1191: invokevirtual 302	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1194: pop
        //   1195: aload 11
        //   1197: invokevirtual 306	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1200: invokestatic 309	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1203: astore 4
        //   1205: goto -458 -> 747
        //   1208: astore 4
        //   1210: getstatic 313	java/lang/System:err	Ljava/io/PrintStream;
        //   1213: aload 4
        //   1215: invokevirtual 316	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1218: invokevirtual 321	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1221: aload_0
        //   1222: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1225: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1228: ifeq -367 -> 861
        //   1231: aload_0
        //   1232: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1235: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1238: ifne -377 -> 861
        //   1241: aload 6
        //   1243: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1246: goto -385 -> 861
        //   1249: astore 4
        //   1251: getstatic 313	java/lang/System:err	Ljava/io/PrintStream;
        //   1254: aload 4
        //   1256: invokevirtual 322	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1259: invokevirtual 321	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1262: aload_0
        //   1263: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1266: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1269: ifeq -408 -> 861
        //   1272: aload_0
        //   1273: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1276: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1279: ifne -418 -> 861
        //   1282: aload 6
        //   1284: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1287: goto -426 -> 861
        //   1290: astore 4
        //   1292: aload_0
        //   1293: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1296: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1299: ifeq +18 -> 1317
        //   1302: aload_0
        //   1303: getfield 204	com/appxy/tinyscanfree/Activity_Main$55:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1306: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1309: ifne +8 -> 1317
        //   1312: aload 6
        //   1314: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1317: aload 4
        //   1319: athrow
        //   1320: astore 4
        //   1322: aload 4
        //   1324: invokevirtual 323	java/io/IOException:printStackTrace	()V
        //   1327: goto -356 -> 971
        //   1330: astore 4
        //   1332: aload 4
        //   1334: invokevirtual 262	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1337: goto -366 -> 971
        //   1340: aload 9
        //   1342: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1345: iload_1
        //   1346: iconst_1
        //   1347: iadd
        //   1348: istore_1
        //   1349: goto -1347 -> 2
        //   1352: new 325	android/os/Message
        //   1355: dup
        //   1356: invokespecial 326	android/os/Message:<init>	()V
        //   1359: astore 4
        //   1361: aload 4
        //   1363: iconst_3
        //   1364: putfield 330	android/os/Message:what	I
        //   1367: aload_0
        //   1368: getfield 19	com/appxy/tinyscanfree/Activity_Main$55:this$0	Lcom/appxy/tinyscanfree/Activity_Main;
        //   1371: getfield 334	com/appxy/tinyscanfree/Activity_Main:handler	Landroid/os/Handler;
        //   1374: aload 4
        //   1376: invokevirtual 340	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1379: pop
        //   1380: return
        //   1381: astore 6
        //   1383: aload 4
        //   1385: astore 5
        //   1387: aload 6
        //   1389: astore 4
        //   1391: goto -387 -> 1004
        //   1394: astore 6
        //   1396: aload 4
        //   1398: astore 5
        //   1400: aload 6
        //   1402: astore 4
        //   1404: goto -414 -> 990
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1407	0	this	55
        //   1	1348	1	i	int
        //   209	892	2	j	int
        //   549	6	3	k	int
        //   77	840	4	localObject1	Object
        //   988	3	4	localFileNotFoundException1	java.io.FileNotFoundException
        //   998	7	4	localDocumentException1	com.itextpdf.text.DocumentException
        //   1015	189	4	localObject2	Object
        //   1208	6	4	localDocumentException2	com.itextpdf.text.DocumentException
        //   1249	6	4	localIOException1	IOException
        //   1290	28	4	localObject3	Object
        //   1320	3	4	localIOException2	IOException
        //   1330	3	4	localDocumentException3	com.itextpdf.text.DocumentException
        //   1359	44	4	localObject4	Object
        //   197	713	5	localObject5	Object
        //   978	3	5	localException	Exception
        //   1002	397	5	localObject6	Object
        //   508	805	6	localDocument1	com.itextpdf.text.Document
        //   1381	7	6	localDocumentException4	com.itextpdf.text.DocumentException
        //   1394	7	6	localFileNotFoundException2	java.io.FileNotFoundException
        //   49	1037	7	localFile	File
        //   206	893	8	localArrayList	ArrayList
        //   505	836	9	localDocument2	com.itextpdf.text.Document
        //   1124	60	10	localBitmap	Bitmap
        //   1133	63	11	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   485	493	978	java/lang/Exception
        //   513	533	988	java/io/FileNotFoundException
        //   513	533	998	com/itextpdf/text/DocumentException
        //   632	692	1208	com/itextpdf/text/DocumentException
        //   695	747	1208	com/itextpdf/text/DocumentException
        //   747	781	1208	com/itextpdf/text/DocumentException
        //   781	791	1208	com/itextpdf/text/DocumentException
        //   791	836	1208	com/itextpdf/text/DocumentException
        //   1070	1169	1208	com/itextpdf/text/DocumentException
        //   1172	1205	1208	com/itextpdf/text/DocumentException
        //   632	692	1249	java/io/IOException
        //   695	747	1249	java/io/IOException
        //   747	781	1249	java/io/IOException
        //   781	791	1249	java/io/IOException
        //   791	836	1249	java/io/IOException
        //   1070	1169	1249	java/io/IOException
        //   1172	1205	1249	java/io/IOException
        //   632	692	1290	finally
        //   695	747	1290	finally
        //   747	781	1290	finally
        //   781	791	1290	finally
        //   791	836	1290	finally
        //   1070	1169	1290	finally
        //   1172	1205	1290	finally
        //   1210	1221	1290	finally
        //   1251	1262	1290	finally
        //   861	971	1320	java/io/IOException
        //   861	971	1330	com/itextpdf/text/DocumentException
        //   533	538	1381	com/itextpdf/text/DocumentException
        //   533	538	1394	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  public void createPDF2()
  {
    Object localObject = new File(this.root_Path2);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".pdf"));
      if (localObject != null)
      {
        int j = localObject.length;
        int i = 0;
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
    localObject = new Message();
    ((Message)localObject).what = 11;
    this.handler.sendMessage((Message)localObject);
  }
  
  public int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  @SuppressLint({"DefaultLocale"})
  public boolean findAndGotoApp(String paramString, ArrayList<Uri> paramArrayList)
  {
    Object localObject = new Intent("android.intent.action.SEND_MULTIPLE");
    ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "TinyScan");
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
        localIntent.putExtra("android.intent.extra.SUBJECT", "TinyScan");
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
  
  @SuppressLint({"DefaultLocale"})
  public boolean findAndGotoApp2(String paramString, ArrayList<Uri> paramArrayList)
  {
    Object localObject = new Intent("android.intent.action.SEND_MULTIPLE");
    ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "TinyScan");
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
        localIntent.putExtra("android.intent.extra.SUBJECT", "TinyScan");
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
      while (i < this.mlist2_folder.size())
      {
        localPhoto_info = (Photo_info)this.mlist2_folder.get(i);
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
    Iterator localIterator = this.mlist2_folder.iterator();
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
    if (paramString.equals(""))
    {
      this.searchText = "";
      list_folders();
      return;
    }
    this.searchText = paramString.toLowerCase();
    list_folders();
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
    list_by_grid_folder();
    this.listphotos.removeAllViews();
    View localView;
    if ((this.mapp.isPad()) && (AppDate.isSpecial))
    {
      localView = this.inflater.inflate(2130903109, null);
      this.listphotos.addView(localView);
      this.grid = ((GridView)localView.findViewById(2131755452));
      if (!this.mapp.isPad()) {
        break label265;
      }
      if (getResources().getConfiguration().orientation != 1) {
        break label216;
      }
      this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
      this.grid.setNumColumns(3);
    }
    for (;;)
    {
      this.grid.setSelector(new ColorDrawable(0));
      if (this.madapter == null) {
        this.madapter = new GridAdapter(this.context, this.mlist2, false);
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
              break label1057;
            }
            if ((!Activity_Main.this.islongclick) && (!Activity_Main.this.islongclick_folder)) {
              break label543;
            }
            if (Activity_Main.this.isSearch) {
              Activity_Main.access$1202(Activity_Main.this, true);
            }
            if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
              break label465;
            }
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
            if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
              break label455;
            }
            if (!Activity_Main.this.searchandclick) {
              break label445;
            }
            if (!Activity_Main.this.mapp.isPad()) {
              break label435;
            }
            Activity_Main.this.unselected3();
            label239:
            Activity_Main.access$7202(Activity_Main.this, false);
            Activity_Main.this.madapter_folder.isse = false;
            Activity_Main.access$7102(Activity_Main.this, false);
            Activity_Main.this.madapter.isse = false;
            label279:
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
          for (;;)
          {
            Activity_Main.this.changeView();
            return;
            if (Activity_Main.this.isSearch)
            {
              paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
              break;
            }
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
            label435:
            Activity_Main.this.unselected2();
            break label239;
            label445:
            Activity_Main.this.unselected();
            break label239;
            label455:
            Activity_Main.this.selected();
            break label279;
            label465:
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
            Activity_Main.this.madapter.isse = true;
            Activity_Main.this.madapter.notifyDataSetChanged();
            paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
            Activity_Main.idlist.add(paramAnonymousAdapterView);
            Activity_Main.this.selected();
          }
          label543:
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
          paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_EditPhoto.class);
          if (Activity_Main.this.isSearch)
          {
            Activity_Main.this.editor.putString("folder_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getShowname());
            Activity_Main.this.editor.putString("folder_root_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath());
          }
          for (;;)
          {
            Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
            Activity_Main.this.editor.commit();
            Activity_Main.this.mapp.setAdd(false);
            Activity_Main.this.startActivity(paramAnonymousAdapterView);
            return;
            Activity_Main.this.editor.putString("folder_path", Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getShowname());
            Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path3);
          }
          label1057:
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
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
              break label571;
            }
            if (Activity_Main.this.isSearch)
            {
              Activity_Main.access$1202(Activity_Main.this, true);
              Util.hideKeyboard(Activity_Main.this.main_edittext);
            }
            Activity_Main.access$7202(Activity_Main.this, true);
            Activity_Main.access$7102(Activity_Main.this, true);
            if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
              break label472;
            }
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
            if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
              break label462;
            }
            if (!Activity_Main.this.searchandclick) {
              break label452;
            }
            if (!Activity_Main.this.mapp.isPad()) {
              break label442;
            }
            Activity_Main.this.unselected3();
            label245:
            Activity_Main.access$7202(Activity_Main.this, false);
            Activity_Main.this.madapter_folder.isse = false;
            Activity_Main.access$7102(Activity_Main.this, false);
            Activity_Main.this.madapter.isse = false;
            label285:
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
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
            label442:
            Activity_Main.this.unselected2();
            break label245;
            label452:
            Activity_Main.this.unselected();
            break label245;
            label462:
            Activity_Main.this.selected();
            break label285;
            label472:
            ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
            Activity_Main.this.madapter.isse = true;
            Activity_Main.this.madapter.notifyDataSetChanged();
            Activity_Main.this.madapter_folder.isse = true;
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
            Activity_Main.idlist.add(paramAnonymousAdapterView);
            Activity_Main.this.selected();
          }
          label571:
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
          Activity_Main.this.mlist2.remove(paramAnonymousInt);
          Activity_Main.this.madapter.notifyDataSetChanged();
          return true;
        }
      });
      return;
      localView = this.inflater.inflate(2130903109, null);
      break;
      label216:
      if (getResources().getConfiguration().orientation == 2)
      {
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
        this.grid.setNumColumns(5);
        continue;
        label265:
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(24.0F)) / 2);
        this.grid.setNumColumns(2);
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  public void list_by_grid_folder()
  {
    this.main_linear_folder.removeAllViews();
    View localView;
    if ((this.mapp.isPad()) && (AppDate.isSpecial))
    {
      localView = this.inflater.inflate(2130903109, null);
      this.main_linear_folder.addView(localView);
      this.folder_grid = ((GridView)localView.findViewById(2131755452));
      if (!this.mapp.isPad()) {
        break label260;
      }
      if (getResources().getConfiguration().orientation != 1) {
        break label211;
      }
      this.folder_grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
      this.folder_grid.setNumColumns(3);
    }
    for (;;)
    {
      this.folder_grid.setSelector(new ColorDrawable(0));
      if (this.madapter_folder == null) {
        this.madapter_folder = new Folder_GridAdapter(this.context, this.mlist2_folder);
      }
      this.folder_grid.setAdapter(this.madapter_folder);
      this.folder_grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
          if (new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName()).exists())
          {
            if ((Activity_Main.this.islongclick) || (Activity_Main.this.islongclick_folder))
            {
              if (Activity_Main.this.isSearch) {
                Activity_Main.access$1202(Activity_Main.this, true);
              }
              if (((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isCheck())
              {
                ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(false);
                Activity_Main.this.removeid_folder((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt));
                if ((Activity_Main.idlist.isEmpty()) && (Activity_Main.this.idlist_folder.isEmpty())) {
                  if (Activity_Main.this.searchandclick) {
                    if (Activity_Main.this.mapp.isPad())
                    {
                      Activity_Main.this.unselected3();
                      Activity_Main.access$7202(Activity_Main.this, false);
                      Activity_Main.this.madapter_folder.isse = false;
                      Activity_Main.access$7102(Activity_Main.this, false);
                      Activity_Main.this.madapter.isse = false;
                    }
                  }
                }
                for (;;)
                {
                  Activity_Main.this.madapter_folder.notifyDataSetChanged();
                  Activity_Main.this.madapter.notifyDataSetChanged();
                  Activity_Main.this.changeView();
                  return;
                  Activity_Main.this.unselected2();
                  break;
                  Activity_Main.this.unselected();
                  break;
                  Activity_Main.this.selected();
                }
              }
              ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(true);
              Activity_Main.this.madapter_folder.isse = true;
              if (Activity_Main.this.list_type == 0) {
                Activity_Main.this.madapter_folder.notifyDataSetChanged();
              }
              for (;;)
              {
                paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
                Activity_Main.this.idlist_folder.add(paramAnonymousAdapterView);
                Activity_Main.this.selected();
                break;
                Activity_Main.this.madapter2_folder.notifyDataSetChanged();
              }
            }
            paramAnonymousView = new Intent(Activity_Main.this.context, Activity_FolderFile.class);
            paramAnonymousView.setFlags(67108864);
            paramAnonymousView.putExtra("folder_path", Activity_Main.this.root_Path4 + paramAnonymousAdapterView.getName());
            paramAnonymousView.putExtra("folder_name", paramAnonymousAdapterView.getShowname());
            paramAnonymousView.putExtra("list_type", Activity_Main.this.list_type);
            paramAnonymousView.putExtra("sort_type", Activity_Main.sort_type);
            Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path4 + paramAnonymousAdapterView.getName() + "/");
            Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
            Activity_Main.this.editor.commit();
            Activity_Main.this.startActivity(paramAnonymousView);
            return;
          }
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
          Activity_Main.this.mlist2_folder.remove(paramAnonymousInt);
          if (Activity_Main.this.list_type == 0)
          {
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            return;
          }
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
        }
      });
      this.folder_grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isFolder())
          {
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
            if (!paramAnonymousAdapterView.exists()) {
              break label554;
            }
            if (Activity_Main.this.isSearch) {
              Activity_Main.access$1202(Activity_Main.this, true);
            }
            Activity_Main.access$7202(Activity_Main.this, true);
            Activity_Main.access$7102(Activity_Main.this, true);
            if (!((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isCheck()) {
              break label457;
            }
            ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(false);
            Activity_Main.this.removeid_folder((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt));
            if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
              break label447;
            }
            if (!Activity_Main.this.searchandclick) {
              break label437;
            }
            if (!Activity_Main.this.mapp.isPad()) {
              break label427;
            }
            Activity_Main.this.unselected3();
            label225:
            Activity_Main.access$7202(Activity_Main.this, false);
            Activity_Main.this.madapter_folder.isse = false;
            Activity_Main.access$7102(Activity_Main.this, false);
            Activity_Main.this.madapter.isse = false;
            label265:
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            Activity_Main.this.madapter.notifyDataSetChanged();
          }
          for (;;)
          {
            if (Activity_Main.idlist.isEmpty()) {}
            Activity_Main.this.changeView();
            return true;
            if (Activity_Main.this.isSearch)
            {
              paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
              break;
            }
            paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
            break;
            label427:
            Activity_Main.this.unselected2();
            break label225;
            label437:
            Activity_Main.this.unselected();
            break label225;
            label447:
            Activity_Main.this.selected();
            break label265;
            label457:
            ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(true);
            Activity_Main.this.madapter_folder.isse = true;
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            Activity_Main.this.madapter.isse = true;
            Activity_Main.this.madapter.notifyDataSetChanged();
            paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
            Activity_Main.this.idlist_folder.add(paramAnonymousAdapterView);
            Activity_Main.this.selected();
          }
          label554:
          Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
          Activity_Main.this.mlist2_folder.remove(paramAnonymousInt);
          if (Activity_Main.this.list_type == 0)
          {
            Activity_Main.this.madapter_folder.notifyDataSetChanged();
            return true;
          }
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          return true;
        }
      });
      return;
      localView = this.inflater.inflate(2130903109, null);
      break;
      label211:
      if (getResources().getConfiguration().orientation == 2)
      {
        this.folder_grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
        this.folder_grid.setNumColumns(5);
        continue;
        label260:
        this.folder_grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(24.0F)) / 2);
        this.folder_grid.setNumColumns(2);
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  public void list_by_list()
  {
    list_by_list_folder();
    this.listphotos.removeAllViews();
    Object localObject = this.inflater.inflate(2130903111, null);
    this.listphotos.addView((View)localObject);
    localObject = (ListView)((View)localObject).findViewById(2131755453);
    if (this.madapter2 == null) {
      this.madapter2 = new ListAdapter(this.context, this.mlist2, false);
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
            break label1068;
          }
          if ((!Activity_Main.this.islongclick) && (!Activity_Main.this.islongclick_folder)) {
            break label554;
          }
          if (Activity_Main.this.isSearch) {
            Activity_Main.access$1202(Activity_Main.this, true);
          }
          if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
            break label476;
          }
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
          if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
            break label455;
          }
          if (!Activity_Main.this.searchandclick) {
            break label445;
          }
          if (!Activity_Main.this.mapp.isPad()) {
            break label435;
          }
          Activity_Main.this.unselected3();
          label239:
          Activity_Main.access$7202(Activity_Main.this, false);
          Activity_Main.this.madapter2_folder.isse = false;
          Activity_Main.access$7102(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label279:
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        for (;;)
        {
          Activity_Main.this.changeView();
          return;
          if (Activity_Main.this.isSearch)
          {
            paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
          }
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          break;
          label435:
          Activity_Main.this.unselected2();
          break label239;
          label445:
          Activity_Main.this.unselected();
          break label239;
          label455:
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.selected();
          break label279;
          label476:
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.madapter2.notifyDataSetChanged();
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
          Activity_Main.idlist.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
        }
        label554:
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
        paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_EditPhoto.class);
        if (Activity_Main.this.isSearch)
        {
          Activity_Main.this.editor.putString("folder_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getShowname());
          Activity_Main.this.editor.putString("folder_root_path", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getRotepath());
        }
        for (;;)
        {
          Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
          Activity_Main.this.editor.commit();
          Activity_Main.this.mapp.setAdd(false);
          Activity_Main.this.startActivity(paramAnonymousAdapterView);
          return;
          Activity_Main.this.editor.putString("folder_path", Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
          Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getShowname());
          Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path3);
        }
        label1068:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
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
            break label582;
          }
          if (Activity_Main.this.isSearch)
          {
            Activity_Main.access$1202(Activity_Main.this, true);
            Util.hideKeyboard(Activity_Main.this.main_edittext);
          }
          Activity_Main.access$7202(Activity_Main.this, true);
          Activity_Main.access$7102(Activity_Main.this, true);
          if (!((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).isCheck()) {
            break label483;
          }
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt));
          if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
            break label462;
          }
          if (!Activity_Main.this.searchandclick) {
            break label452;
          }
          if (!Activity_Main.this.mapp.isPad()) {
            break label442;
          }
          Activity_Main.this.unselected3();
          label245:
          Activity_Main.access$7202(Activity_Main.this, false);
          Activity_Main.this.madapter2_folder.isse = false;
          Activity_Main.access$7102(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label285:
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          Activity_Main.this.madapter2.notifyDataSetChanged();
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
          label442:
          Activity_Main.this.unselected2();
          break label245;
          label452:
          Activity_Main.this.unselected();
          break label245;
          label462:
          Activity_Main.this.selected();
          Activity_Main.this.madapter2.isse = true;
          break label285;
          label483:
          ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.madapter2.notifyDataSetChanged();
          Activity_Main.this.madapter2_folder.isse = true;
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt);
          Activity_Main.idlist.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
        }
        label582:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
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
  
  @SuppressLint({"InflateParams"})
  public void list_by_list_folder()
  {
    this.main_linear_folder.removeAllViews();
    Object localObject = this.inflater.inflate(2130903111, null);
    this.main_linear_folder.addView((View)localObject);
    localObject = (ListView)((View)localObject).findViewById(2131755453);
    if (this.madapter2_folder == null) {
      this.madapter2_folder = new Folder_ListAdapter(this.context, this.mlist2_folder);
    }
    ((ListView)localObject).setAdapter(this.madapter2_folder);
    ((ListView)localObject).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
        if (paramAnonymousView.isFolder())
        {
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
          if (!paramAnonymousAdapterView.exists()) {
            break label896;
          }
          if ((!Activity_Main.this.islongclick) && (!Activity_Main.this.islongclick_folder)) {
            break label536;
          }
          if (Activity_Main.this.isSearch) {
            Activity_Main.access$1202(Activity_Main.this, true);
          }
          if (!((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isCheck()) {
            break label460;
          }
          ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid_folder((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt));
          if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
            break label439;
          }
          if (!Activity_Main.this.searchandclick) {
            break label429;
          }
          if (!Activity_Main.this.mapp.isPad()) {
            break label419;
          }
          Activity_Main.this.unselected3();
          label229:
          Activity_Main.access$7202(Activity_Main.this, false);
          Activity_Main.this.madapter2_folder.isse = false;
          Activity_Main.access$7102(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label269:
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        for (;;)
        {
          Activity_Main.this.changeView();
          return;
          if (Activity_Main.this.isSearch)
          {
            paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
            break;
          }
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
          break;
          label419:
          Activity_Main.this.unselected2();
          break label229;
          label429:
          Activity_Main.this.unselected();
          break label229;
          label439:
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.selected();
          break label269;
          label460:
          ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter2_folder.isse = true;
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
          Activity_Main.this.idlist_folder.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
        }
        label536:
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
        paramAnonymousAdapterView = new Intent(Activity_Main.this.context, Activity_EditPhoto.class);
        Activity_Main.this.editor.putString("folder_path", Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
        Activity_Main.this.editor.putString("folder_name", ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getShowname());
        Activity_Main.this.editor.putString("folder_root_path", Activity_Main.this.root_Path3);
        Activity_Main.this.editor.putInt("folder_id", paramAnonymousInt);
        Activity_Main.this.editor.commit();
        Activity_Main.this.mapp.setAdd(false);
        Activity_Main.this.startActivity(paramAnonymousAdapterView);
        return;
        label896:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
        Activity_Main.this.mlist2_folder.remove(paramAnonymousInt);
        if (Activity_Main.this.list_type == 0)
        {
          Activity_Main.this.madapter_folder.notifyDataSetChanged();
          return;
        }
        Activity_Main.this.madapter2_folder.notifyDataSetChanged();
      }
    });
    ((ListView)localObject).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isFolder())
        {
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path4 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
          if (!paramAnonymousAdapterView.exists()) {
            break label556;
          }
          if (Activity_Main.this.isSearch) {
            Activity_Main.access$1202(Activity_Main.this, true);
          }
          Activity_Main.access$7202(Activity_Main.this, true);
          Activity_Main.access$7102(Activity_Main.this, true);
          if (!((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).isCheck()) {
            break label459;
          }
          ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(false);
          Activity_Main.this.removeid_folder((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt));
          if ((!Activity_Main.idlist.isEmpty()) || (!Activity_Main.this.idlist_folder.isEmpty())) {
            break label438;
          }
          if (!Activity_Main.this.searchandclick) {
            break label428;
          }
          if (!Activity_Main.this.mapp.isPad()) {
            break label418;
          }
          Activity_Main.this.unselected3();
          label225:
          Activity_Main.access$7202(Activity_Main.this, false);
          Activity_Main.this.madapter2_folder.isse = false;
          Activity_Main.access$7102(Activity_Main.this, false);
          Activity_Main.this.madapter2.isse = false;
          label265:
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          Activity_Main.this.madapter2.notifyDataSetChanged();
        }
        for (;;)
        {
          Activity_Main.this.changeView();
          return true;
          if (Activity_Main.this.isSearch)
          {
            paramAnonymousAdapterView = new File(((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getRotepath() + ((Photo_info)Activity_Main.this.mlist2.get(paramAnonymousInt)).getName());
            break;
          }
          paramAnonymousAdapterView = new File(Activity_Main.this.root_Path3 + ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).getName());
          break;
          label418:
          Activity_Main.this.unselected2();
          break label225;
          label428:
          Activity_Main.this.unselected();
          break label225;
          label438:
          Activity_Main.this.selected();
          Activity_Main.this.madapter2.isse = true;
          break label265;
          label459:
          ((Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt)).setCheck(true);
          Activity_Main.this.madapter2_folder.isse = true;
          Activity_Main.this.madapter2_folder.notifyDataSetChanged();
          Activity_Main.this.madapter2.isse = true;
          Activity_Main.this.madapter2.notifyDataSetChanged();
          paramAnonymousAdapterView = (Photo_info)Activity_Main.this.mlist2_folder.get(paramAnonymousInt);
          Activity_Main.this.idlist_folder.add(paramAnonymousAdapterView);
          Activity_Main.this.selected();
        }
        label556:
        Toast.makeText(Activity_Main.this.context, Activity_Main.this.getResources().getString(2131296408), 0).show();
        Activity_Main.this.mlist2_folder.remove(paramAnonymousInt);
        Activity_Main.this.madapter2_folder.notifyDataSetChanged();
        return true;
      }
    });
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public void list_folders()
  {
    if ((this.isSearch) || (this.searchandclick)) {
      addmlist2_listData();
    }
    do
    {
      return;
      if (this.preferences.getInt("file_count_total", 0) < 100) {
        break;
      }
    } while (this.isRun_listfolders);
    this.isRun_listfolders = true;
    if ((this.madapter == null) && (this.madapter2 == null)) {
      this.main_progreebar.setVisibility(0);
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Activity_Main.this.addmlist2_listData();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
    return;
    addmlist2_listData();
  }
  
  public void makefolder()
  {
    new File(this.root_Path3).mkdirs();
    new File(this.root_Path4).mkdirs();
    new File(this.compressJpeg_Path).mkdirs();
    new File(this.root_Path2).mkdirs();
    new File(this.root_Path + ".nomedia").mkdirs();
    new File(this.root_Path2 + ".nomedia").mkdirs();
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
      do
      {
        do
        {
          return;
        } while ((paramInt2 == 0) || (paramInt2 != 1));
        Toast.makeText(this.context, getResources().getString(2131296371), 0).show();
        return;
        onAuthenticated(paramInt2, paramIntent);
        return;
        onFolderSelected(paramInt2, paramIntent);
        return;
      } while (paramInt2 != -1);
      try
      {
        String str = getRealPathFromURI(paramIntent.getData());
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
    } while ((paramInt2 != -1) || (paramInt1 != 10001));
    paramIntent = activity_Main.getSharedPreferences("TinyScanPro", 0);
    if ((paramIntent.getInt("newversion_1.2.7_first", -1) == 1) && (!"".equals(Util.FileName())) && (Util.FileName().length() > 11)) {
      FlurryAgent.logEvent("8_D" + ((new Date().getTime() - Util.strToDate(Util.FileName().substring(1, 11).toString()).getTime()) / 24L * 60L * 60L * 1000L + 1L));
    }
    if (!paramIntent.getString("newversion_firsttime", "").equals(""))
    {
      paramInt2 = (int)((new Date().getTime() - Util.stringToDate1(paramIntent.getString("newversion_firsttime", "")).getTime()) / 86400000L);
      if (paramInt2 > 50) {
        FlurryAgent.logEvent("A_IAP_50");
      }
    }
    else
    {
      if (this.mapp.getAdvOrChargeOrNormal() != 1) {
        break label547;
      }
      FlurryAgent.logEvent("7_UserAds_IAP");
    }
    for (;;)
    {
      paramIntent = getSharedPreferences("msp", 0).edit();
      paramIntent.putBoolean("GOOGLE_IAP", true);
      this.mapp.setIsBuyGoogleAds(true);
      this.mapp.setAdvOrChargeOrNormal(3);
      paramIntent.commit();
      paramIntent = new Message();
      paramIntent.what = 10020;
      this.handler.sendMessage(paramIntent);
      thankBuy("Thank you for upgrading to pro! ");
      return;
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        FlurryAgent.logEvent("A_IAP_" + paramInt1);
        paramInt1 += 1;
      }
      break;
      label547:
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mapp.isPad())
    {
      if (paramConfiguration.orientation != 1) {
        break label273;
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
      if (this.folder_grid != null)
      {
        this.folder_grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
        this.folder_grid.setNumColumns(3);
      }
      if ((this.shareDialog != null) && (this.shareDialog.isShowing()))
      {
        localLayoutParams = this.shareDialog.getWindow().getAttributes();
        localLayoutParams.width = ((int)(paramConfiguration.widthPixels * 0.9D));
        i = localLayoutParams.width - dip2px(30.0F);
        this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, i);
        this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, i);
        this.pad_listview1.setAdapter(this.exportAdapter1);
        this.pad_listview2.setAdapter(this.exportAdapter2);
        this.shareDialog.getWindow().setAttributes(localLayoutParams);
      }
    }
    label273:
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
      if (this.grid != null)
      {
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
        this.grid.setNumColumns(5);
      }
      if (this.folder_grid != null)
      {
        this.folder_grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
        this.folder_grid.setNumColumns(5);
      }
    } while ((this.shareDialog == null) || (!this.shareDialog.isShowing()));
    WindowManager.LayoutParams localLayoutParams = this.shareDialog.getWindow().getAttributes();
    localLayoutParams.width = ((int)(paramConfiguration.heightPixels * 0.9D));
    int i = localLayoutParams.width - dip2px(30.0F);
    this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, i);
    this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, i);
    this.pad_listview1.setAdapter(this.exportAdapter1);
    this.pad_listview2.setAdapter(this.exportAdapter2);
    this.shareDialog.getWindow().setAttributes(localLayoutParams);
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.context = this;
    this.mapp = MyApplication.getApplication(this.context);
    activity_Main = this;
    this.mapp.setPad(isPad());
    this.mApp = MyApp.getApp();
    if (this.mapp.isPad())
    {
      setContentView(2130903067);
      this.toolbar = ((Toolbar)findViewById(2131755222));
      setSupportActionBar(this.toolbar);
      this.mCache = ACache.get(this);
      if (Util.getTotalMemory(activity_Main).longValue() < 2097152L) {
        break label1575;
      }
      this.mapp.setIsshowBatch(true);
      label120:
      Log.i("TAG", "memorysize=============" + Util.getTotalMemory(activity_Main));
      paramBundle = new DisplayMetrics();
      activity_Main.getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      this.mapp.setDensityDpi(paramBundle.densityDpi);
      this.root_Path = (Environment.getExternalStorageDirectory() + "/MyTinyScan/");
      this.root_Path2 = (Environment.getExternalStorageDirectory() + "/MyTinyScan_PDF/");
      this.root_Path5 = (Environment.getExternalStorageDirectory() + "/MyTinyScan_PDF/Documents/");
      this.root_Path6 = (Environment.getExternalStorageDirectory() + "/MyTinyScan_PDF/Folders/");
      this.root_Path3 = (Environment.getExternalStorageDirectory() + "/MyTinyScan/Documents/");
      this.root_Path4 = (Environment.getExternalStorageDirectory() + "/MyTinyScan/Folders/");
      this.compressJpeg_Path = (Environment.getExternalStorageDirectory() + "/MyTinyScan/temporary/Jpeg/");
      this.mDbHelper = MyDbHelper.getHelper(this.context);
      this.db = this.mDbHelper.getWritableDatabase();
      paramBundle = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      this.mapp.setDisplaywidth(paramBundle.widthPixels);
      this.mapp.setDispalyheight(paramBundle.heightPixels);
      idlist = new ArrayList();
      this.idlist_folder = new ArrayList();
      this.mlist2 = new ArrayList();
      this.mlist2_folder = new ArrayList();
      this.mlist2_copy = new ArrayList();
      this.searchList = new ArrayList();
      this.preferences = getSharedPreferences("TinyScanPro", 0);
      this.editor = this.preferences.edit();
      this.times = this.preferences.getInt("times", 0);
      if (this.times != 0) {
        break label1586;
      }
      this.editor.putLong("time", System.currentTimeMillis());
      this.editor.putBoolean("isUpdate3", true);
      this.editor.commit();
      label587:
      this.editor.putInt("times", this.times + 1);
      this.editor.commit();
      System.setProperty("java.com.appxy.util.Arrays.useLegacyMergeSort", "true");
      paramBundle = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
      if (Math.sqrt(Math.pow(paramBundle.widthPixels / paramBundle.xdpi, 2.0D) + Math.pow(paramBundle.heightPixels / paramBundle.ydpi, 2.0D)) >= 8.2D) {
        break label1649;
      }
      AppDate.isSpecial = true;
      label696:
      this.main_onlongclick_relativelayout = ((RelativeLayout)findViewById(2131755226));
      if (!this.mapp.isPad())
      {
        this.mainactivity_layout1 = ((RelativeLayout)findViewById(2131755234));
        this.faxlink_layout = ((RelativeLayout)findViewById(2131755250));
        this.faxlink_dismisstext = ((TextView)findViewById(2131755251));
        this.faxlink_downloadtext = ((TextView)findViewById(2131755252));
        this.faxlink_dismisstext.setOnClickListener(this.myOnClickListener);
        this.faxlink_downloadtext.setOnClickListener(this.myOnClickListener);
      }
      this.faxCount = this.preferences.getInt("faxCount", 0);
      if (this.times == 0)
      {
        this.editor.putInt("mathrandom", 0);
        this.editor.commit();
      }
      if (this.times == 0)
      {
        sdCard_date("." + dateToString(new Date()));
        this.editor.putString("newversion_firsttime", Util.dateToString1(new Date()));
        this.editor.putInt("newversion_1.2.7_first", 1);
        this.editor.commit();
        int i = (int)(Math.random() * 1.0D);
        if (i == 0) {
          FlurryAgent.logEvent("6_UserDoc");
        }
        this.editor.putInt("newversion_1.2.4", i);
        this.editor.commit();
      }
      this.price = ((TextView)findViewById(2131755238));
      this.googleAdv_layout = ((RelativeLayout)findViewById(2131755236));
      this.ggimage = ((ImageView)findViewById(2131755237));
      if (!this.mapp.isPad()) {
        break label1676;
      }
      if (!this.mapp.getIsShowBatch()) {
        break label1656;
      }
      this.ggimage.setImageDrawable(getResources().getDrawable(2130837961));
    }
    for (;;)
    {
      this.googleAdv_layout.setVisibility(8);
      this.googleAdv_layout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Main.this.showProIapBuyDialog();
        }
      });
      this.inflater = LayoutInflater.from(this.context);
      this.main_scrollview = ((ScrollView)findViewById(2131755239));
      this.main_folder_textview_name = ((TextView)findViewById(2131755240));
      this.main_folder_textview_name_space = ((TextView)findViewById(2131755241));
      this.main_file_textview_name = ((TextView)findViewById(2131755243));
      this.main_file_textview_name_space = ((TextView)findViewById(2131755244));
      this.main_progreebar = ((ProgressBar)findViewById(2131755249));
      this.main_linear_folder = ((LinearLayout)findViewById(2131755242));
      this.listphotos = ((LinearLayout)findViewById(2131755245));
      this.selectline = ((LinearLayout)findViewById(2131755228));
      this.selecttext = ((TextView)findViewById(2131755229));
      this.back2 = ((ImageView)findViewById(2131755227));
      this.back2.setOnClickListener(this.myOnClickListener);
      if (this.mapp.isPad())
      {
        this.mainedit_documentname = ((ImageView)findViewById(2131755253));
        this.mainedit_documentname.setOnClickListener(this.myOnClickListener);
      }
      this.share = ((ImageView)findViewById(2131755230));
      this.share.setOnClickListener(this.myOnClickListener);
      this.moveto = ((ImageView)findViewById(2131755231));
      this.moveto.setOnClickListener(this.myOnClickListener);
      this.getall = ((ImageView)findViewById(2131755232));
      this.getall.setOnClickListener(this.myOnClickListener);
      this.delete = ((ImageView)findViewById(2131755233));
      this.delete.setOnClickListener(this.myOnClickListener);
      this.main_camera_gallery_layout = ((RelativeLayout)findViewById(2131755246));
      this.camera = ((ImageView)findViewById(2131755247));
      this.camera.setOnClickListener(this.myOnClickListener);
      this.gallery = ((ImageView)findViewById(2131755248));
      this.gallery.setOnClickListener(this.myOnClickListener);
      this.mapp.setUpdate(false);
      this.mian_adslayout = ((RelativeLayout)findViewById(2131755235));
      this.mian_search_relativelayout = ((RelativeLayout)findViewById(2131755223));
      this.main_edittext = ((EditText)findViewById(2131755224));
      this.main_edittext_delete_imageview = ((ImageView)findViewById(2131755225));
      this.main_edittext_delete_imageview.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_Main.this.main_edittext.setText("");
        }
      });
      this.main_edittext.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if (paramAnonymousCharSequence.toString().equals("")) {
            Activity_Main.this.main_edittext_delete_imageview.setVisibility(8);
          }
          for (;;)
          {
            Activity_Main.this.getResults(paramAnonymousCharSequence.toString());
            return;
            Activity_Main.this.main_edittext_delete_imageview.setVisibility(0);
          }
        }
      });
      if ((Build.VERSION.SDK_INT >= 23) && (!this.isrequestCheck)) {
        requstPermisstion();
      }
      return;
      setRequestedOrientation(1);
      break;
      label1575:
      this.mapp.setIsshowBatch(false);
      break label120;
      label1586:
      if ((this.preferences.getLong("time", 0L) != 0L) || (this.preferences.getInt("ratetime", 0) != 0)) {
        break label587;
      }
      this.editor.putLong("time", System.currentTimeMillis());
      this.editor.commit();
      break label587;
      label1649:
      AppDate.isSpecial = false;
      break label696;
      label1656:
      this.ggimage.setImageDrawable(getResources().getDrawable(2130837789));
      continue;
      label1676:
      if (this.mapp.getIsShowBatch()) {
        this.ggimage.setImageDrawable(getResources().getDrawable(2130837960));
      } else {
        this.ggimage.setImageDrawable(getResources().getDrawable(2130837962));
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131820548, paramMenu);
    isShowImagefaxlink();
    if (!this.mapp.isPad())
    {
      if (this.preferences.getInt("list_type", 0) == 0)
      {
        this.toolbar.getMenu().findItem(2131755712).setVisible(false);
        this.toolbar.getMenu().findItem(2131755713).setVisible(true);
      }
      for (;;)
      {
        this.toolbar.getMenu().findItem(2131755710).setVisible(false);
        return true;
        this.toolbar.getMenu().findItem(2131755712).setVisible(true);
        this.toolbar.getMenu().findItem(2131755713).setVisible(false);
      }
    }
    this.toolbar.getMenu().findItem(2131755711).setVisible(false);
    this.toolbar.getMenu().findItem(2131755712).setVisible(false);
    this.toolbar.getMenu().findItem(2131755713).setVisible(false);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mapp.getSs() != null) {
      this.mapp.getSs().stop();
    }
    this.mapp.setSs(null);
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
      if (this.searchandclick)
      {
        this.searchandclick = false;
        if (this.mapp.isPad()) {
          unselected3();
        }
      }
      for (;;)
      {
        return true;
        unselected2();
        continue;
        if (this.isSelect)
        {
          unselected();
        }
        else if (this.isSearch)
        {
          exitSearchMonth();
        }
        else if ((this.faxlink_layout != null) && (this.faxlink_layout.getVisibility() == 0))
        {
          FlurryAgent.logEvent("PromoBack");
          this.toolbar.setVisibility(0);
          this.faxlink_layout.setVisibility(8);
          this.mainactivity_layout1.setVisibility(0);
        }
        else if (!this.isupload)
        {
          this.editor.putInt("folder_id", 0);
          this.editor.commit();
          this.mapp.clearMemCache();
          paramKeyEvent = new Intent();
          paramKeyEvent.setAction("ExitLogin");
          sendBroadcast(paramKeyEvent);
          finish();
        }
        else
        {
          showToast(getResources().getString(2131296525));
        }
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131755709)
    {
      this.isSearch = true;
      this.main_camera_gallery_layout.setVisibility(8);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      this.toolbar.getMenu().findItem(2131755714).setVisible(false);
      this.toolbar.getMenu().findItem(2131755705).setVisible(false);
      this.toolbar.getMenu().findItem(2131755707).setVisible(false);
      this.toolbar.getMenu().findItem(2131755709).setVisible(false);
      if (this.mapp.isPad())
      {
        this.toolbar.getMenu().findItem(2131755710).setVisible(false);
        this.mian_search_relativelayout.setVisibility(0);
        this.main_edittext.requestFocus();
        Util.showKeyboard(this.main_edittext);
      }
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      this.toolbar.getMenu().findItem(2131755711).setVisible(false);
      this.toolbar.getMenu().findItem(2131755712).setVisible(false);
      this.toolbar.getMenu().findItem(2131755713).setVisible(false);
      break;
      if (i == 2131755707)
      {
        startActivity(new Intent(this.context, Activity_Setting.class));
        return true;
      }
      if (i == 2131755711)
      {
        showFolderDialog(false);
        return true;
      }
      if (i == 2131755712)
      {
        if (!this.mapp.isPad())
        {
          this.toolbar.getMenu().findItem(2131755712).setVisible(false);
          this.toolbar.getMenu().findItem(2131755713).setVisible(true);
        }
        gridviewMethod();
        setFolder_file_isshow();
        return true;
      }
      if (i == 2131755713)
      {
        if (!this.mapp.isPad())
        {
          this.toolbar.getMenu().findItem(2131755712).setVisible(true);
          this.toolbar.getMenu().findItem(2131755713).setVisible(false);
        }
        listviewMethod();
        setFolder_file_isshow();
        return true;
      }
      if (i == 2131755714)
      {
        this.sortbyWindow = new SortbyPopupWindow(this);
        this.sortbyWindow.showAtLocation(this.toolbar, 81, 0, 0);
        return true;
      }
      if (i == 2131755705)
      {
        selectMothed();
      }
      else
      {
        if (i == 2131755708)
        {
          faxlinkMethod();
          return true;
        }
        if (i == 16908332) {
          exitSearchMonth();
        } else if (i == 2131755710) {
          showFolderDialog(false);
        }
      }
    }
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
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (this.isrequestCheck) {
        runOnresumeMethods();
      }
      return;
    }
    runOnresumeMethods();
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
  
  public int px2dip(float paramFloat)
  {
    return (int)(paramFloat / getResources().getDisplayMetrics().density + 0.5F);
  }
  
  @SuppressLint({"InflateParams"})
  public void ratenew()
  {
    int i;
    if ((this.preferences.getBoolean("canrate", false)) && (this.preferences.getInt("files", 0) > 0))
    {
      i = this.preferences.getInt("ratetime", 0) + 1;
      this.editor.putInt("ratetime", i);
      this.editor.putBoolean("canrate", false);
      switch (i)
      {
      default: 
        if (i < 3)
        {
          this.editor.putLong("time", System.currentTimeMillis());
          label125:
          if (i >= 3) {
            break label289;
          }
          localObject = this.inflater.inflate(2130903128, null);
          localButton1 = (Button)((View)localObject).findViewById(2131755516);
          localButton2 = (Button)((View)localObject).findViewById(2131755515);
          localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
          ((AlertDialog)localObject).show();
          localButton1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              Activity_Main.this.editor.putLong("time", 0L);
              Activity_Main.this.editor.commit();
              switch (Activity_Main.this.preferences.getInt("ratetime", 0))
              {
              }
              Object localObject;
              for (;;)
              {
                Activity_Main.access$7402(Activity_Main.this, true);
                localObject = Activity_Main.this.getPackageManager().getInstalledApplications(0);
                int j = ((List)localObject).size();
                paramAnonymousView = null;
                int i = 0;
                while (i < j)
                {
                  if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
                    paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
                  }
                  i += 1;
                }
                FlurryAgent.logEvent("1_RATE");
                continue;
                FlurryAgent.logEvent("2_RATE");
                continue;
                FlurryAgent.logEvent("3_RATE");
              }
              if (paramAnonymousView != null)
              {
                localObject = new Intent("android.intent.action.VIEW");
                ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscanner&hl=en"));
                ((Intent)localObject).setPackage(paramAnonymousView.packageName);
                Activity_Main.this.startActivity((Intent)localObject);
              }
              for (;;)
              {
                if ((this.val$mDialog != null) && (this.val$mDialog.isShowing())) {
                  this.val$mDialog.dismiss();
                }
                return;
                Activity_Main.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscanner&hl=en")));
              }
            }
          });
          localButton2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if ((this.val$mDialog != null) && (this.val$mDialog.isShowing())) {
                this.val$mDialog.dismiss();
              }
            }
          });
          ((AlertDialog)localObject).setOnDismissListener(new DialogInterface.OnDismissListener()
          {
            public void onDismiss(DialogInterface paramAnonymousDialogInterface)
            {
              if (!Activity_Main.this.clickrate) {}
              switch (Activity_Main.this.preferences.getInt("ratetime", 0))
              {
              default: 
                return;
              case 1: 
                FlurryAgent.logEvent("1_NOT_NOW");
                return;
              case 2: 
                FlurryAgent.logEvent("2_NOT_NOW");
                return;
              }
              FlurryAgent.logEvent("3_NOT_NOW");
            }
          });
        }
        break;
      }
    }
    label289:
    while ((i != 3) || (!checkGooglePlayServicesAvailable()))
    {
      Object localObject;
      Button localButton1;
      Button localButton2;
      return;
      FlurryAgent.logEvent("1_POP");
      break;
      FlurryAgent.logEvent("2_POP");
      break;
      FlurryAgent.logEvent("3_POP");
      break;
      this.editor.putLong("time", 0L);
      this.editor.commit();
      break label125;
    }
    new RateMeDialogFragment().show(getFragmentManager(), "");
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
  
  public void removeid_folder(Photo_info paramPhoto_info)
  {
    int j = this.idlist_folder.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (paramPhoto_info.getName().equals(((Photo_info)this.idlist_folder.get(i)).getName())) {
          this.idlist_folder.remove(i);
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
  
  protected void sdCard(String paramString)
  {
    try
    {
      if (ExistSDCard())
      {
        File localFile = new File(this.root_Path);
        paramString = new File(this.root_Path + paramString + ".Tinyscanner");
        if ((localFile.exists()) && (paramString.exists())) {
          return;
        }
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        if (!paramString.exists())
        {
          paramString.createNewFile();
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  protected void sdCard_date(String paramString)
  {
    try
    {
      if (ExistSDCard())
      {
        File localFile = new File(this.root_Path);
        paramString = new File(this.root_Path + paramString + ".Tinyscanner_1_2_7");
        if (isFileName(this.root_Path)) {
          return;
        }
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        if (!paramString.exists())
        {
          paramString.createNewFile();
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void search()
  {
    this.isSearch = true;
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
    }
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
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 2);
  }
  
  public void search3()
  {
    this.isSearch = true;
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
    }
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 2);
  }
  
  public void search4()
  {
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 2);
  }
  
  public void selected()
  {
    this.isSelect = true;
    this.mian_search_relativelayout.setVisibility(8);
    this.toolbar.getMenu().findItem(2131755708).setVisible(false);
    this.toolbar.getMenu().findItem(2131755709).setVisible(false);
    if (this.mapp.isPad())
    {
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
      this.toolbar.getMenu().findItem(2131755714).setVisible(false);
      this.toolbar.getMenu().findItem(2131755705).setVisible(false);
      this.toolbar.getMenu().findItem(2131755707).setVisible(false);
      this.main_onlongclick_relativelayout.setVisibility(0);
      if (!this.mapp.isPad()) {
        break label421;
      }
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
      this.mainedit_documentname.setVisibility(0);
      if (this.isSearch) {
        this.mainedit_documentname.setVisibility(8);
      }
      this.selecttext.setText("" + (idlist.size() + this.idlist_folder.size()));
    }
    for (;;)
    {
      this.selecttext.setVisibility(0);
      this.share.setVisibility(0);
      this.moveto.setVisibility(0);
      this.getall.setVisibility(0);
      if (this.isSearch)
      {
        this.moveto.setVisibility(8);
        this.getall.setVisibility(8);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      }
      this.delete.setVisibility(0);
      this.selectline.setVisibility(0);
      this.main_camera_gallery_layout.setVisibility(4);
      return;
      this.toolbar.getMenu().findItem(2131755711).setVisible(false);
      this.toolbar.getMenu().findItem(2131755712).setVisible(false);
      this.toolbar.getMenu().findItem(2131755713).setVisible(false);
      break;
      label421:
      this.selecttext.setText("" + (idlist.size() + this.idlist_folder.size()));
    }
  }
  
  public void setActivity_Main(Activity_Main paramActivity_Main)
  {
    activity_Main = paramActivity_Main;
  }
  
  @SuppressLint({"InflateParams"})
  protected void sharepadAlterdialog()
  {
    Object localObject = this.inflater.inflate(2130903167, null);
    this.popu_radiogroup = ((RadioGroup)((View)localObject).findViewById(2131755675));
    this.popu_radiobutton_pdf = ((RadioButton)((View)localObject).findViewById(2131755676));
    this.popu_radiobutton_jpg = ((RadioButton)((View)localObject).findViewById(2131755677));
    this.popu_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, @IdRes int paramAnonymousInt)
      {
        if (paramAnonymousInt == Activity_Main.this.popu_radiobutton_pdf.getId())
        {
          Activity_Main.access$2902(Activity_Main.this, true);
          Activity_Main.this.addPDF_listdata();
          Activity_Main.access$3102(Activity_Main.this, new SharePopuList1_padAdapter(Activity_Main.this.context, Activity_Main.this.padexportlist1, Activity_Main.this.currentWidth));
          Activity_Main.access$3402(Activity_Main.this, new SharePopuList2_padAdapter(Activity_Main.this.context, Activity_Main.this.padexportlist2, Activity_Main.this.currentWidth));
          Activity_Main.this.pad_listview1.setAdapter(Activity_Main.this.exportAdapter1);
          Activity_Main.this.pad_listview2.setAdapter(Activity_Main.this.exportAdapter2);
        }
        while (paramAnonymousInt != Activity_Main.this.popu_radiobutton_jpg.getId()) {
          return;
        }
        Activity_Main.access$2902(Activity_Main.this, false);
        Activity_Main.this.addJpg_listdata();
        Activity_Main.access$3102(Activity_Main.this, new SharePopuList1_padAdapter(Activity_Main.this.context, Activity_Main.this.padexportlist1, Activity_Main.this.currentWidth));
        Activity_Main.access$3402(Activity_Main.this, new SharePopuList2_padAdapter(Activity_Main.this.context, Activity_Main.this.padexportlist2, Activity_Main.this.currentWidth));
        Activity_Main.this.pad_listview1.setAdapter(Activity_Main.this.exportAdapter1);
        Activity_Main.this.pad_listview2.setAdapter(Activity_Main.this.exportAdapter2);
      }
    });
    final TextView localTextView1;
    final TextView localTextView2;
    final long l;
    WindowManager.LayoutParams localLayoutParams;
    if (this.popu_radiobutton_pdf.isChecked())
    {
      this.pdf_or_jpg = true;
      addPDF_listdata();
      this.pad_listview1 = ((HorizontalListView)((View)localObject).findViewById(2131755678));
      this.pad_listview2 = ((HorizontalListView)((View)localObject).findViewById(2131755679));
      localTextView1 = (TextView)((View)localObject).findViewById(2131755680);
      localTextView2 = (TextView)((View)localObject).findViewById(2131755681);
      l = getfilesizeLength();
      localTextView1.setText("File Size: " + Util.FormetFileSize1(l));
      localTextView2.setText("Large");
      this.export_size = 0;
      this.shareDialog = new AlertDialog.Builder(activity_Main).setView((View)localObject).create();
      this.shareDialog.show();
      this.shareDialog.setCanceledOnTouchOutside(true);
      this.shareDialog.setCancelable(true);
      localObject = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      localLayoutParams = this.shareDialog.getWindow().getAttributes();
      if (this.context.getResources().getConfiguration().orientation != 2) {
        break label470;
      }
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject).heightPixels * 0.9D));
    }
    for (this.currentWidth = localLayoutParams.width;; this.currentWidth = localLayoutParams.width)
    {
      this.currentWidth -= dip2px(30.0F);
      this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, this.currentWidth);
      this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, this.currentWidth);
      this.pad_listview1.setAdapter(this.exportAdapter1);
      this.pad_listview2.setAdapter(this.exportAdapter2);
      this.shareDialog.getWindow().setAttributes(localLayoutParams);
      localTextView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (localTextView2.getText().toString().equals("Large"))
          {
            localTextView2.setText("Medium");
            localTextView1.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)));
            Activity_Main.access$4002(Activity_Main.this, 1);
          }
          do
          {
            return;
            if (localTextView2.getText().toString().equals("Medium"))
            {
              localTextView2.setText("Small");
              localTextView1.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)));
              Activity_Main.access$4002(Activity_Main.this, 2);
              return;
            }
          } while (!localTextView2.getText().toString().equals("Small"));
          localTextView2.setText("Large");
          localTextView1.setText("File Size: " + Util.FormetFileSize1(l));
          Activity_Main.access$4002(Activity_Main.this, 0);
        }
      });
      this.pad_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_Main.this.pdf_or_jpg)
          {
            Activity_Main.this.clickOne(Activity_Main.this.padexportlist1, paramAnonymousInt);
            return;
          }
          Activity_Main.this.clickOne_jpg(Activity_Main.this.padexportlist1, paramAnonymousInt);
        }
      });
      this.pad_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_Main.this.pdf_or_jpg)
          {
            Activity_Main.this.clickTwo(Activity_Main.this.padexportlist2, paramAnonymousInt);
            return;
          }
          Activity_Main.this.clickTwo_jpg(Activity_Main.this.padexportlist2, paramAnonymousInt);
        }
      });
      return;
      this.pdf_or_jpg = false;
      addJpg_listdata();
      break;
      label470:
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject).widthPixels * 0.9D));
    }
  }
  
  @SuppressLint({"InflateParams"})
  public void showFolderDialog(final boolean paramBoolean)
  {
    File localFile = new File(this.root_Path4 + getString(2131296459));
    int i = 2;
    while (localFile.exists())
    {
      localFile = new File(this.root_Path4 + getString(2131296459) + "(" + i + ")");
      i += 1;
    }
    final View localView = this.inflater.inflate(2130903161, null);
    EditText localEditText = (EditText)localView.findViewById(2131755630);
    localEditText.setSelectAllOnFocus(true);
    localEditText.setText(localFile.getName());
    new AlertDialog.Builder(this.context).setTitle(getString(2131296459)).setView(localView).setPositiveButton(getString(2131296464), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        EditText localEditText = (EditText)localView.findViewById(2131755630);
        if (localEditText.getText().toString().trim().equals(""))
        {
          Activity_Main.this.showToast(Activity_Main.this.getResources().getString(2131296410));
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
        Activity_Main.this.createNewFolder(localEditText.getText().toString().trim(), localEditText.getText().toString().trim(), paramBoolean);
      }
    }).setNegativeButton(getResources().getString(2131296372), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
    if (this.mapp.isPad()) {
      return;
    }
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)Activity_Main.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
      }
    }, 100L);
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
    int j = 0;
    int i = 0;
    Object localObject;
    if (this.mapp.getAdvOrChargeOrNormal() == 2)
    {
      localObject = this.mlist2.iterator();
      Photo_info localPhoto_info;
      while (((Iterator)localObject).hasNext())
      {
        localPhoto_info = (Photo_info)((Iterator)localObject).next();
        if (localPhoto_info.getImage_name() != null) {
          i += localPhoto_info.getImage_name().size();
        }
      }
      localObject = this.mlist2_folder.iterator();
      for (;;)
      {
        j = i;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localPhoto_info = (Photo_info)((Iterator)localObject).next();
        if (localPhoto_info.getImage_name() != null) {
          i += localPhoto_info.getImage_name().size();
        }
      }
    }
    if (j >= 3)
    {
      showProIapBuyDialog();
      return;
    }
    this.editor.putString("folder_root_path", this.root_Path3);
    this.editor.commit();
    if (paramBoolean)
    {
      FlurryAgent.logEvent("4_CAMERA");
      this.editor.putBoolean("where", false);
      this.editor.commit();
      this.mapp.setSavePath(this.root_Path3);
      if (this.mapp.isPad()) {}
      for (localObject = new Intent(this.context, Activity_PadCamera.class);; localObject = new Intent(this.context, Activity_CameraPreview.class))
      {
        startActivityForResult((Intent)localObject, 0);
        return;
      }
    }
    if (this.mapp.getIsShowBatch()) {}
    FlurryAgent.logEvent("4_ALBUM");
    this.editor.putBoolean("where", false);
    this.editor.commit();
    this.mapp.clearCheckeditems();
    this.mapp.setPhotofrom(false);
    this.mapp.setSavePath(this.root_Path3);
    startActivity(new Intent(this, LocalAlbum.class));
  }
  
  public void unselected()
  {
    if (this.list_type == 0)
    {
      this.madapter_folder.isse = false;
      this.madapter.isse = false;
      this.isSelect = false;
      this.islongclick_folder = false;
      this.islongclick = false;
      j = this.mlist2.size();
      i = 0;
      label50:
      if (i >= j) {
        break label122;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label112;
      }
      this.madapter.notifyDataSetChanged();
    }
    for (;;)
    {
      i += 1;
      break label50;
      this.madapter2_folder.isse = false;
      this.madapter2.isse = false;
      break;
      label112:
      this.madapter2.notifyDataSetChanged();
    }
    label122:
    idlist.clear();
    int j = this.mlist2_folder.size();
    int i = 0;
    if (i < j)
    {
      ((Photo_info)this.mlist2_folder.get(i)).setCheck(false);
      if (this.list_type == 0) {
        this.madapter_folder.notifyDataSetChanged();
      }
      for (;;)
      {
        i += 1;
        break;
        this.madapter2_folder.notifyDataSetChanged();
      }
    }
    this.idlist_folder.clear();
    if (this.mapp.isPad())
    {
      this.toolbar.getMenu().findItem(2131755710).setVisible(true);
      this.mainedit_documentname.setVisibility(8);
    }
    isShowImagefaxlink();
    this.mian_search_relativelayout.setVisibility(8);
    this.toolbar.getMenu().findItem(2131755709).setVisible(true);
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(true);
    }
    for (;;)
    {
      this.toolbar.getMenu().findItem(2131755714).setVisible(true);
      this.toolbar.getMenu().findItem(2131755705).setVisible(true);
      this.toolbar.getMenu().findItem(2131755707).setVisible(true);
      this.main_onlongclick_relativelayout.setVisibility(8);
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      this.selecttext.setText("");
      this.selecttext.setVisibility(8);
      this.selectline.setVisibility(8);
      this.moveto.setVisibility(8);
      this.getall.setVisibility(8);
      this.share.setVisibility(8);
      this.delete.setVisibility(8);
      this.main_camera_gallery_layout.setVisibility(0);
      return;
      this.toolbar.getMenu().findItem(2131755711).setVisible(true);
      if (this.preferences.getInt("list_type", 0) == 0)
      {
        this.toolbar.getMenu().findItem(2131755712).setVisible(false);
        this.toolbar.getMenu().findItem(2131755713).setVisible(true);
      }
      else
      {
        this.toolbar.getMenu().findItem(2131755712).setVisible(true);
        this.toolbar.getMenu().findItem(2131755713).setVisible(false);
      }
    }
  }
  
  public void unselected2()
  {
    if (this.list_type == 0)
    {
      this.madapter_folder.isse = false;
      this.madapter.isse = false;
      this.searchandclick = false;
      this.isSelect = false;
      this.islongclick_folder = false;
      this.islongclick = false;
      j = this.mlist2.size();
      i = 0;
      label55:
      if (i >= j) {
        break label127;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label117;
      }
      this.madapter.notifyDataSetChanged();
    }
    for (;;)
    {
      i += 1;
      break label55;
      this.madapter2_folder.isse = false;
      this.madapter2.isse = false;
      break;
      label117:
      this.madapter2.notifyDataSetChanged();
    }
    label127:
    idlist.clear();
    int j = this.mlist2_folder.size();
    int i = 0;
    if (i < j)
    {
      ((Photo_info)this.mlist2_folder.get(i)).setCheck(false);
      if (this.list_type == 0) {
        this.madapter_folder.notifyDataSetChanged();
      }
      for (;;)
      {
        i += 1;
        break;
        this.madapter2_folder.notifyDataSetChanged();
      }
    }
    this.idlist_folder.clear();
    isShowImagefaxlink();
    this.mian_search_relativelayout.setVisibility(0);
    this.toolbar.getMenu().findItem(2131755709).setVisible(false);
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
    }
    for (;;)
    {
      this.toolbar.getMenu().findItem(2131755714).setVisible(false);
      this.toolbar.getMenu().findItem(2131755705).setVisible(false);
      this.toolbar.getMenu().findItem(2131755707).setVisible(false);
      this.main_onlongclick_relativelayout.setVisibility(8);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      this.selecttext.setText("");
      this.selecttext.setVisibility(8);
      this.selectline.setVisibility(8);
      if (this.mapp.isPad()) {
        this.mainedit_documentname.setVisibility(8);
      }
      this.moveto.setVisibility(8);
      this.getall.setVisibility(8);
      this.share.setVisibility(8);
      this.delete.setVisibility(8);
      this.main_camera_gallery_layout.setVisibility(0);
      if (!this.mapp.isPad()) {
        break;
      }
      search4();
      return;
      this.toolbar.getMenu().findItem(2131755711).setVisible(false);
      this.toolbar.getMenu().findItem(2131755712).setVisible(false);
      this.toolbar.getMenu().findItem(2131755713).setVisible(false);
    }
    search2();
  }
  
  public void unselected3()
  {
    if (this.list_type == 0)
    {
      this.madapter_folder.isse = false;
      this.madapter.isse = false;
      this.searchandclick = false;
      this.isSelect = false;
      this.islongclick_folder = false;
      this.islongclick = false;
      j = this.mlist2.size();
      i = 0;
      label55:
      if (i >= j) {
        break label127;
      }
      ((Photo_info)this.mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label117;
      }
      this.madapter.notifyDataSetChanged();
    }
    for (;;)
    {
      i += 1;
      break label55;
      this.madapter2_folder.isse = false;
      this.madapter2.isse = false;
      break;
      label117:
      this.madapter2.notifyDataSetChanged();
    }
    label127:
    idlist.clear();
    int j = this.mlist2_folder.size();
    int i = 0;
    if (i < j)
    {
      ((Photo_info)this.mlist2_folder.get(i)).setCheck(false);
      if (this.list_type == 0) {
        this.madapter_folder.notifyDataSetChanged();
      }
      for (;;)
      {
        i += 1;
        break;
        this.madapter2_folder.notifyDataSetChanged();
      }
    }
    this.idlist_folder.clear();
    isShowImagefaxlink();
    this.mian_search_relativelayout.setVisibility(0);
    this.toolbar.getMenu().findItem(2131755709).setVisible(false);
    if (this.mapp.isPad()) {
      this.toolbar.getMenu().findItem(2131755710).setVisible(false);
    }
    for (;;)
    {
      this.toolbar.getMenu().findItem(2131755714).setVisible(false);
      this.toolbar.getMenu().findItem(2131755705).setVisible(false);
      this.toolbar.getMenu().findItem(2131755707).setVisible(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      this.main_onlongclick_relativelayout.setVisibility(8);
      this.selecttext.setText("");
      this.selecttext.setVisibility(8);
      this.selectline.setVisibility(8);
      if (this.mapp.isPad()) {
        this.mainedit_documentname.setVisibility(8);
      }
      this.main_camera_gallery_layout.setVisibility(0);
      this.moveto.setVisibility(8);
      this.getall.setVisibility(8);
      this.share.setVisibility(8);
      this.delete.setVisibility(8);
      if (!this.mapp.isPad()) {
        break;
      }
      search4();
      return;
      this.toolbar.getMenu().findItem(2131755711).setVisible(false);
      this.toolbar.getMenu().findItem(2131755712).setVisible(false);
      this.toolbar.getMenu().findItem(2131755713).setVisible(false);
    }
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
  
  class SelectPicPopupWindow
    extends PopupWindow
  {
    private SharePopuList1Adapter exportAdapter1;
    private SharePopuList2Adapter exportAdapter2;
    private ArrayList<HashMap<String, Object>> exportlist1;
    private ArrayList<HashMap<String, Object>> exportlist2;
    private TextView filesize_size;
    private TextView filesize_tv;
    private HorizontalListView listview1;
    private HorizontalListView listview2;
    private View mMenuView;
    private boolean pdf_or_jpg = true;
    
    @SuppressLint({"InflateParams"})
    public SelectPicPopupWindow(final Activity paramActivity, View.OnClickListener paramOnClickListener, final int paramInt)
    {
      super();
      this.mMenuView = ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(2130903168, null);
      paramOnClickListener = (RadioGroup)this.mMenuView.findViewById(2131755675);
      final RadioButton localRadioButton = (RadioButton)this.mMenuView.findViewById(2131755676);
      paramOnClickListener.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, @IdRes int paramAnonymousInt)
        {
          if (paramAnonymousInt == localRadioButton.getId())
          {
            Activity_Main.SelectPicPopupWindow.access$7702(Activity_Main.SelectPicPopupWindow.this, true);
            Activity_Main.SelectPicPopupWindow.this.addPDF_listdata();
            Activity_Main.SelectPicPopupWindow.access$7902(Activity_Main.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_Main.SelectPicPopupWindow.this.exportlist1, paramInt));
            Activity_Main.SelectPicPopupWindow.access$8102(Activity_Main.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_Main.SelectPicPopupWindow.this.exportlist2, paramInt));
            Activity_Main.SelectPicPopupWindow.this.listview1.setAdapter(Activity_Main.SelectPicPopupWindow.this.exportAdapter1);
            Activity_Main.SelectPicPopupWindow.this.listview2.setAdapter(Activity_Main.SelectPicPopupWindow.this.exportAdapter2);
          }
          while (paramAnonymousInt != this.val$popu_radiobutton_jpg.getId()) {
            return;
          }
          Activity_Main.SelectPicPopupWindow.access$7702(Activity_Main.SelectPicPopupWindow.this, false);
          Activity_Main.SelectPicPopupWindow.this.addJpg_listdata();
          Activity_Main.SelectPicPopupWindow.access$7902(Activity_Main.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_Main.SelectPicPopupWindow.this.exportlist1, paramInt));
          Activity_Main.SelectPicPopupWindow.access$8102(Activity_Main.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_Main.SelectPicPopupWindow.this.exportlist2, paramInt));
          Activity_Main.SelectPicPopupWindow.this.listview1.setAdapter(Activity_Main.SelectPicPopupWindow.this.exportAdapter1);
          Activity_Main.SelectPicPopupWindow.this.listview2.setAdapter(Activity_Main.SelectPicPopupWindow.this.exportAdapter2);
        }
      });
      if (localRadioButton.isChecked())
      {
        this.pdf_or_jpg = true;
        addPDF_listdata();
      }
      for (;;)
      {
        this.exportAdapter1 = new SharePopuList1Adapter(paramActivity, this.exportlist1, paramInt);
        this.exportAdapter2 = new SharePopuList2Adapter(paramActivity, this.exportlist2, paramInt);
        this.listview1 = ((HorizontalListView)this.mMenuView.findViewById(2131755683));
        this.listview2 = ((HorizontalListView)this.mMenuView.findViewById(2131755684));
        this.filesize_tv = ((TextView)this.mMenuView.findViewById(2131755680));
        this.filesize_size = ((TextView)this.mMenuView.findViewById(2131755681));
        final long l = Activity_Main.this.getfilesizeLength();
        this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
        this.filesize_size.setText("Large");
        Activity_Main.access$4002(Activity_Main.this, 0);
        this.listview1.setAdapter(this.exportAdapter1);
        this.listview2.setAdapter(this.exportAdapter2);
        setContentView(this.mMenuView);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setAnimationStyle(2131493040);
        setBackgroundDrawable(new ColorDrawable(1275068416));
        this.mMenuView.setOnTouchListener(new View.OnTouchListener()
        {
          @SuppressLint({"ClickableViewAccessibility"})
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            int i = Activity_Main.SelectPicPopupWindow.this.mMenuView.findViewById(2131755682).getTop();
            int j = (int)paramAnonymousMotionEvent.getY();
            if ((paramAnonymousMotionEvent.getAction() == 1) && (j < i)) {
              Activity_Main.SelectPicPopupWindow.this.dismiss();
            }
            return true;
          }
        });
        this.filesize_size.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (Activity_Main.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Large"))
            {
              Activity_Main.SelectPicPopupWindow.this.filesize_size.setText("Medium");
              Activity_Main.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)));
              Activity_Main.access$4002(Activity_Main.this, 1);
            }
            do
            {
              return;
              if (Activity_Main.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Medium"))
              {
                Activity_Main.SelectPicPopupWindow.this.filesize_size.setText("Small");
                Activity_Main.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)));
                Activity_Main.access$4002(Activity_Main.this, 2);
                return;
              }
            } while (!Activity_Main.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Small"));
            Activity_Main.SelectPicPopupWindow.this.filesize_size.setText("Large");
            Activity_Main.SelectPicPopupWindow.this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
            Activity_Main.access$4002(Activity_Main.this, 0);
          }
        });
        this.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (Activity_Main.SelectPicPopupWindow.this.pdf_or_jpg)
            {
              Activity_Main.this.clickOne(Activity_Main.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
              return;
            }
            Activity_Main.this.clickOne_jpg(Activity_Main.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
          }
        });
        this.listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (Activity_Main.SelectPicPopupWindow.this.pdf_or_jpg)
            {
              Activity_Main.this.clickTwo(Activity_Main.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
              return;
            }
            Activity_Main.this.clickTwo_jpg(Activity_Main.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
          }
        });
        return;
        this.pdf_or_jpg = false;
        addJpg_listdata();
      }
    }
    
    private void addJpg_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 3)
        {
          localHashMap = new HashMap();
          if (i == 2) {
            localHashMap.put("id", Integer.valueOf(4));
          }
          for (;;)
          {
            localHashMap.put("isPro", Boolean.valueOf(false));
            localHashMap.put("isEnable", Boolean.valueOf(true));
            this.exportlist2.add(localHashMap);
            i += 1;
            break;
            localHashMap.put("id", Integer.valueOf(i));
          }
        }
      }
      else
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(true));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 3)
        {
          localHashMap = new HashMap();
          if (i == 2)
          {
            localHashMap.put("id", Integer.valueOf(4));
            label303:
            if (i != 1) {
              break label363;
            }
            localHashMap.put("isPro", Boolean.valueOf(true));
          }
          for (;;)
          {
            localHashMap.put("isEnable", Boolean.valueOf(true));
            this.exportlist2.add(localHashMap);
            i += 1;
            break;
            localHashMap.put("id", Integer.valueOf(i));
            break label303;
            label363:
            localHashMap.put("isPro", Boolean.valueOf(false));
          }
        }
      }
      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(5));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.exportlist2.add(localHashMap);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(true));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.exportlist2.add(localHashMap);
    }
    
    private void addPDF_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          localHashMap.put("isPro", Boolean.valueOf(false));
          if ((i == 2) || (i == 3) || (i == 4))
          {
            if (i == 3) {
              localHashMap.put("id", Integer.valueOf(6));
            }
            if (i == 4) {
              localHashMap.put("id", Integer.valueOf(3));
            }
            Activity_Main.access$6602(Activity_Main.this, false);
            if (Activity_Main.this.idlist_folder.size() > 0) {
              Activity_Main.access$6602(Activity_Main.this, true);
            }
            if ((Activity_Main.idlist.size() > 1) || (Activity_Main.this.hasfolder)) {
              localHashMap.put("isEnable", Boolean.valueOf(false));
            }
          }
          else
          {
            label283:
            if (i != 3) {
              break label327;
            }
            if (Build.VERSION.SDK_INT >= 19) {
              this.exportlist2.add(localHashMap);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            localHashMap.put("isEnable", Boolean.valueOf(true));
            break label283;
            label327:
            this.exportlist2.add(localHashMap);
          }
        }
      }
      else
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(true));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          if ((i == 1) || (i == 3))
          {
            localHashMap.put("isPro", Boolean.valueOf(true));
            label467:
            if ((i == 2) || (i == 3) || (i == 4))
            {
              if (i == 3) {
                localHashMap.put("id", Integer.valueOf(6));
              }
              if (i == 4) {
                localHashMap.put("id", Integer.valueOf(3));
              }
              Activity_Main.access$6602(Activity_Main.this, false);
              if (Activity_Main.this.idlist_folder.size() > 0) {
                Activity_Main.access$6602(Activity_Main.this, true);
              }
              if ((Activity_Main.idlist.size() <= 1) && (!Activity_Main.this.hasfolder)) {
                break label626;
              }
              localHashMap.put("isEnable", Boolean.valueOf(false));
            }
            label582:
            if (i != 3) {
              break label641;
            }
            if (Build.VERSION.SDK_INT >= 19) {
              this.exportlist2.add(localHashMap);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            localHashMap.put("isPro", Boolean.valueOf(false));
            break label467;
            label626:
            localHashMap.put("isEnable", Boolean.valueOf(true));
            break label582;
            label641:
            this.exportlist2.add(localHashMap);
          }
        }
      }
      if (!Activity_Main.this.mapp.getIsContainCountry()) {
        this.exportlist2.remove(2);
      }
      if ((Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_Main.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(5));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.exportlist2.add(localHashMap);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(true));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.exportlist2.add(localHashMap);
    }
  }
  
  class SortbyListviewAdapter
    extends BaseAdapter
  {
    Context context;
    LayoutInflater inflater;
    ArrayList<SortByDao> mlist;
    
    public SortbyListviewAdapter(ArrayList<SortByDao> paramArrayList)
    {
      this.context = paramArrayList;
      Object localObject;
      this.mlist = localObject;
      this.inflater = LayoutInflater.from(paramArrayList);
    }
    
    public int getCount()
    {
      return this.mlist.size();
    }
    
    public Object getItem(int paramInt)
    {
      return this.mlist.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    @SuppressLint({"InflateParams"})
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ListItemView();
        paramView = this.inflater.inflate(2130903173, null);
        paramViewGroup.sortby_item_name = ((TextView)paramView.findViewById(2131755689));
        paramViewGroup.sortby_item_isselect = ((ImageView)paramView.findViewById(2131755690));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.sortby_item_name.setText(((SortByDao)this.mlist.get(paramInt)).getName());
        if (!((SortByDao)this.mlist.get(paramInt)).isSelect()) {
          break;
        }
        paramViewGroup.sortby_item_isselect.setVisibility(0);
        return paramView;
        paramViewGroup = (ListItemView)paramView.getTag();
      }
      paramViewGroup.sortby_item_isselect.setVisibility(8);
      return paramView;
    }
    
    public final class ListItemView
    {
      public ImageView sortby_item_isselect;
      public TextView sortby_item_name;
      
      public ListItemView() {}
    }
  }
  
  class SortbyPopupWindow
    extends PopupWindow
  {
    public SortbyPopupWindow(Activity paramActivity)
    {
      super();
      Activity_Main.access$9102(Activity_Main.this, ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(2130903172, null));
      Activity_Main.access$9202(Activity_Main.this, (ListView)Activity_Main.this.sortbyView.findViewById(2131755688));
      Activity_Main.access$9302(Activity_Main.this, new ArrayList());
      ArrayList localArrayList = Activity_Main.this.list;
      String str = Activity_Main.this.getResources().getString(2131296379);
      boolean bool1;
      if (Activity_Main.this.preferences.getInt("sort_type", 0) == 0)
      {
        bool1 = true;
        localArrayList.add(new SortByDao(str, bool1));
        localArrayList = Activity_Main.this.list;
        str = Activity_Main.this.getResources().getString(2131296513);
        bool1 = bool2;
        if (Activity_Main.this.preferences.getInt("sort_type", 0) == 1) {
          bool1 = true;
        }
        localArrayList.add(new SortByDao(str, bool1));
        Activity_Main.this.sortby_Listview.setAdapter(new Activity_Main.SortbyListviewAdapter(Activity_Main.this, paramActivity, Activity_Main.this.list));
        Activity_Main.this.sortby_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if ((Activity_Main.this.sortbyWindow != null) && (Activity_Main.this.sortbyWindow.isShowing())) {
              Activity_Main.this.sortbyWindow.dismiss();
            }
            switch (paramAnonymousInt)
            {
            default: 
              return;
            case 0: 
              Activity_Main.this.dateCreateSortby();
              return;
            }
            Activity_Main.this.titleSortby();
          }
        });
        setContentView(Activity_Main.this.sortbyView);
        paramActivity = new DisplayMetrics();
        Activity_Main.this.getWindowManager().getDefaultDisplay().getMetrics(paramActivity);
        if (!Activity_Main.this.mapp.isPad()) {
          break label332;
        }
        setWidth(-2);
      }
      for (;;)
      {
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(2131493040);
        setBackgroundDrawable(new ColorDrawable());
        paramActivity = Activity_Main.this.getWindow().getAttributes();
        paramActivity.alpha = 0.7F;
        Activity_Main.this.getWindow().setAttributes(paramActivity);
        setOnDismissListener(new PopupWindow.OnDismissListener()
        {
          public void onDismiss()
          {
            WindowManager.LayoutParams localLayoutParams = Activity_Main.this.getWindow().getAttributes();
            localLayoutParams.alpha = 1.0F;
            Activity_Main.this.getWindow().setAttributes(localLayoutParams);
          }
        });
        Activity_Main.this.sortbyView.setOnTouchListener(new View.OnTouchListener()
        {
          @SuppressLint({"ClickableViewAccessibility"})
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            int i = Activity_Main.this.sortbyView.findViewById(2131755682).getTop();
            int j = (int)paramAnonymousMotionEvent.getY();
            if ((paramAnonymousMotionEvent.getAction() == 1) && (j < i)) {
              Activity_Main.SortbyPopupWindow.this.dismiss();
            }
            return true;
          }
        });
        return;
        bool1 = false;
        break;
        label332:
        setWidth(-1);
      }
    }
  }
}
