package com.simpleapp.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import com.appxy.tools.IAPBuy;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
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

public class Activity_FolderFile
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  static Comparator<Photo_info> comparator = new Comparator()
  {
    public int compare(Photo_info paramAnonymousPhoto_info1, Photo_info paramAnonymousPhoto_info2)
    {
      return (int)(paramAnonymousPhoto_info2.getRealtime() - paramAnonymousPhoto_info1.getRealtime());
    }
  };
  static Comparator<Photo_info> comparator2 = new Comparator()
  {
    @SuppressLint({"DefaultLocale"})
    public int compare(Photo_info paramAnonymousPhoto_info1, Photo_info paramAnonymousPhoto_info2)
    {
      if ((paramAnonymousPhoto_info1.getName().matches("New Document\\(\\d{1,5}\\)")) && (paramAnonymousPhoto_info2.getName().matches("New Document\\(\\d{1,5}\\)"))) {
        return Integer.parseInt(paramAnonymousPhoto_info1.getName().substring(13, paramAnonymousPhoto_info1.getName().length() - 1)) - Integer.parseInt(paramAnonymousPhoto_info2.getName().substring(13, paramAnonymousPhoto_info2.getName().length() - 1));
      }
      return paramAnonymousPhoto_info1.getName().toLowerCase().compareTo(paramAnonymousPhoto_info2.getName().toLowerCase());
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
      return paramAnonymousFile1.getName().compareTo(paramAnonymousFile2.getName());
    }
  };
  private static List<Photo_info> idlist;
  static ArrayList<Photo_info> mlist2;
  private Activity_FolderFile activity_folderFile;
  private ImageView back;
  private ImageView camera;
  private String compressJpeg_Path;
  private Context context;
  private SQLiteDatabase db;
  private SharedPreferences.Editor editor;
  private List<File> export_file;
  private int export_size = 0;
  private String folderPath;
  private LinearLayout folder_deletelayout;
  private LinearLayout folder_emaillayout;
  private ImageView folder_image_delete_pad;
  private ImageView folder_image_editname;
  private ImageView folder_image_email_pad;
  private ImageView folder_image_importgallery;
  private ImageView folder_image_more_pad;
  private ImageView folder_image_share_pad;
  private ImageView folder_imagesearch;
  private ImageView folder_more;
  private LinearLayout folder_morelayout;
  private LinearLayout folder_onlongclick_layout;
  private RelativeLayout folder_relativelayout_ads;
  private RelativeLayout folder_search_relative;
  private EditText folder_search_text;
  private ImageView folder_search_text_delete;
  private RadioButton folder_selectall;
  private TextView folder_selecttextview;
  private LinearLayout folder_sharelayout;
  private ImageView folder_title_delete_pad;
  private ImageView folder_title_email_pad;
  private ImageView folder_title_more_pad;
  private ImageView folder_title_share_pad;
  private ProgressBar folderfile_progreebar;
  private GridView grid;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private PrintManager printManager;
    
    @SuppressLint({"InflateParams", "DefaultLocale", "InlinedApi", "NewApi"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      Object localObject1;
      Object localObject3;
      label701:
      Object localObject4;
      label755:
      label835:
      label907:
      label935:
      label961:
      label2311:
      label2352:
      label2433:
      label2505:
      label2533:
      label2559:
      do
      {
        do
        {
          int i;
          do
          {
            do
            {
              do
              {
                return;
                Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131165767));
                return;
                ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                return;
                Activity_FolderFile.idlist.clear();
                if (Activity_FolderFile.this.list_type == 0) {
                  Activity_FolderFile.this.madapter.notifyDataSetChanged();
                }
                for (;;)
                {
                  Activity_FolderFile.this.folderfile_progreebar.setVisibility(8);
                  Activity_FolderFile.this.unselected();
                  return;
                  Activity_FolderFile.this.madapter2.notifyDataSetChanged();
                }
                Activity_FolderFile.this.unselected();
                Activity_FolderFile.this.relist();
                return;
                new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131165605)).setMessage(Activity_FolderFile.this.getResources().getString(2131165298)).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131165458), null).create().show();
                return;
                Activity_FolderFile.this.folderfile_progreebar.setVisibility(8);
                Activity_FolderFile.access$3002(Activity_FolderFile.this, null);
                Activity_FolderFile.access$3102(Activity_FolderFile.this, new ArrayList());
                Activity_FolderFile.this.export_file.clear();
                localObject1 = new ArrayList();
                if (Activity_FolderFile.this.export_size == 0)
                {
                  paramAnonymousMessage = new Activity_FolderFile.MyFilter2(Activity_FolderFile.this);
                  i = 0;
                  while (i < Activity_FolderFile.idlist.size())
                  {
                    localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName()).listFiles(paramAnonymousMessage);
                    if (localObject2.length > 0) {
                      Activity_FolderFile.this.export_file.add(localObject2[0]);
                    }
                    i += 1;
                  }
                }
                paramAnonymousMessage = new Activity_FolderFile.MyFilter(Activity_FolderFile.this, ".pdf");
                paramAnonymousMessage = new File(Activity_FolderFile.this.root_Path2).listFiles(paramAnonymousMessage);
                i = 0;
                while (i < paramAnonymousMessage.length)
                {
                  Activity_FolderFile.this.export_file.add(paramAnonymousMessage[i]);
                  i += 1;
                }
                i = 0;
                while (i < Activity_FolderFile.this.export_file.size())
                {
                  ((ArrayList)localObject1).add(Uri.fromFile((File)Activity_FolderFile.this.export_file.get(i)));
                  i += 1;
                }
                Activity_FolderFile.this.getPackageManager().getInstalledApplications(0).size();
                localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                ((Intent)localObject2).setType("application/pdf");
                switch (Activity_FolderFile.this.select_pdf_posiotion)
                {
                case 4: 
                default: 
                  return;
                case 0: 
                  if (Activity_FolderFile.idlist.size() <= 1) {
                    break label907;
                  }
                  paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                  paramAnonymousMessage.setType("application/pdf");
                  localObject2 = new ArrayList();
                  paramAnonymousMessage = Activity_FolderFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
                }
              } while (paramAnonymousMessage.isEmpty());
              localObject3 = paramAnonymousMessage.iterator();
              if (((Iterator)localObject3).hasNext())
              {
                localObject4 = (ResolveInfo)((Iterator)localObject3).next();
                if ((Activity_FolderFile.idlist.size() > 1) || (Activity_FolderFile.this.hasFolder))
                {
                  paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                  paramAnonymousMessage.setType("application/pdf");
                  if (Activity_FolderFile.idlist.size() <= 1) {
                    break label935;
                  }
                  paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + " and other " + (Activity_FolderFile.idlist.size() - 1) + " documents");
                  if (((ArrayList)localObject1).size() != 1) {
                    break label961;
                  }
                  paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
                }
                for (;;)
                {
                  paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
                  paramAnonymousMessage.setClassName(((ResolveInfo)localObject4).activityInfo.packageName, ((ResolveInfo)localObject4).activityInfo.name);
                  ((List)localObject2).add(paramAnonymousMessage);
                  break label701;
                  paramAnonymousMessage = new Intent("android.intent.action.SEND");
                  break;
                  paramAnonymousMessage = new Intent("android.intent.action.SEND");
                  break label755;
                  paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
                  break label835;
                  paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                }
              }
              if (((List)localObject2).size() > 0)
              {
                paramAnonymousMessage = Intent.createChooser((Intent)((List)localObject2).remove(0), "Share PDF file");
                paramAnonymousMessage.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                Activity_FolderFile.this.startActivity(paramAnonymousMessage);
                return;
              }
              Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Error: Cannot open or share created PDF report.", 0).show();
              return;
              Activity_FolderFile.this.mapp.setPdf_path(((Uri)((ArrayList)localObject1).get(0)).getPath());
              Activity_FolderFile.this.mapp.setPdf_Name(((File)Activity_FolderFile.this.export_file.get(0)).getName().replace(".pdf", ""));
              Activity_FolderFile.this.mapp.setPdf_pages(Util.getPdfPages(((Uri)((ArrayList)localObject1).get(0)).getPath()));
              try
              {
                this.printManager = ((PrintManager)Activity_FolderFile.this.activity_folderFile.getSystemService("print"));
                this.printManager.print(Activity_FolderFile.this.mapp.getPdf_Name(), new MyPrintDocumentAdapter(Activity_FolderFile.this.mapp), null);
                return;
              }
              catch (Exception paramAnonymousMessage)
              {
                Toast.makeText(Activity_FolderFile.this.activity_folderFile, "printing error.", 0).show();
                return;
              }
              paramAnonymousMessage = new ArrayList();
              localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
            } while (((List)localObject2).isEmpty());
            localObject2 = ((List)localObject2).iterator();
            if (((Iterator)localObject2).hasNext())
            {
              localObject3 = (ResolveInfo)((Iterator)localObject2).next();
              localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
              ((Intent)localObject4).setType("application/pdf");
              if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_to", ""))) {
                ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("emailsetting_to", "") });
              }
              if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_subject", ""))) {
                ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_FolderFile.this.preferences.getString("emailsetting_subject", ""));
              }
              for (;;)
              {
                if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_body", ""))) {
                  ((Intent)localObject4).putExtra("android.intent.extra.TEXT", Activity_FolderFile.this.preferences.getString("emailsetting_body", ""));
                }
                if ((!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                  break;
                }
                ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                paramAnonymousMessage.add(localObject4);
                break;
                if (Activity_FolderFile.idlist.size() > 1) {
                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + " and other " + (Activity_FolderFile.idlist.size() - 1) + " documents");
                } else if (Activity_FolderFile.idlist.size() == 1) {
                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
                } else {
                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
                }
              }
            }
            if (paramAnonymousMessage.size() > 0)
            {
              localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
              ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
              Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
              return;
            }
            Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165275), 0).show();
            return;
            paramAnonymousMessage = new Intent("android.intent.action.VIEW");
            paramAnonymousMessage.setDataAndType((Uri)((ArrayList)localObject1).get(0), "application/pdf");
            paramAnonymousMessage.setFlags(67108864);
            Activity_FolderFile.this.startActivityForResult(Intent.createChooser(paramAnonymousMessage, "Export"), 3);
            return;
          } while (Utils.findAndGotoApp1(Activity_FolderFile.this.activity_folderFile, "com.studio.topfax", (ArrayList)localObject1));
          Utils.showGooglePlaysimplefax(Activity_FolderFile.this.activity_folderFile);
          return;
          Activity_FolderFile.this.folderfile_progreebar.setVisibility(8);
          Activity_FolderFile.access$3002(Activity_FolderFile.this, null);
          Activity_FolderFile.this.mapp.setUpdate(false);
          localObject1 = new ArrayList();
          if (Activity_FolderFile.this.export_size == 0)
          {
            i = 0;
            while (i < Activity_FolderFile.idlist.size())
            {
              paramAnonymousMessage = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName()).listFiles(new Activity_FolderFile.MyFilter(Activity_FolderFile.this, ".jpg"));
              if ((paramAnonymousMessage != null) && (paramAnonymousMessage.length > 0))
              {
                int j = 0;
                while (j < paramAnonymousMessage.length)
                {
                  ((ArrayList)localObject1).add(Uri.fromFile(paramAnonymousMessage[j]));
                  j += 1;
                }
              }
              i += 1;
            }
          }
          paramAnonymousMessage = new File(Activity_FolderFile.this.compressJpeg_Path).listFiles(new Activity_FolderFile.MyFilter(Activity_FolderFile.this, ".jpg"));
          if ((paramAnonymousMessage != null) && (paramAnonymousMessage.length > 0))
          {
            i = 0;
            while (i < paramAnonymousMessage.length)
            {
              ((ArrayList)localObject1).add(Uri.fromFile(paramAnonymousMessage[i]));
              i += 1;
            }
          }
          Activity_FolderFile.this.getPackageManager().getInstalledApplications(0).size();
          localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
          ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "TopScanner");
          ((Intent)localObject2).setType("image/jpeg");
          switch (Activity_FolderFile.this.select_jpeg_posiotion)
          {
          case 1: 
          default: 
            return;
          case 0: 
            if (((ArrayList)localObject1).size() <= 1) {
              break label2505;
            }
            paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
            paramAnonymousMessage.setType("image/jpeg");
            localObject2 = new ArrayList();
            paramAnonymousMessage = Activity_FolderFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
          }
        } while (paramAnonymousMessage.isEmpty());
        localObject3 = paramAnonymousMessage.iterator();
        if (((Iterator)localObject3).hasNext())
        {
          localObject4 = (ResolveInfo)((Iterator)localObject3).next();
          if (((ArrayList)localObject1).size() > 1)
          {
            paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
            paramAnonymousMessage.setType("image/jpeg");
            if (Activity_FolderFile.idlist.size() <= 1) {
              break label2533;
            }
            paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + " and other " + (Activity_FolderFile.idlist.size() - 1) + " documents");
            if (((ArrayList)localObject1).size() != 1) {
              break label2559;
            }
            paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
          }
          for (;;)
          {
            paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
            paramAnonymousMessage.setClassName(((ResolveInfo)localObject4).activityInfo.packageName, ((ResolveInfo)localObject4).activityInfo.name);
            ((List)localObject2).add(paramAnonymousMessage);
            break label2311;
            paramAnonymousMessage = new Intent("android.intent.action.SEND");
            break;
            paramAnonymousMessage = new Intent("android.intent.action.SEND");
            break label2352;
            paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
            break label2433;
            paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
          }
        }
        if (((List)localObject2).size() > 0)
        {
          paramAnonymousMessage = Intent.createChooser((Intent)((List)localObject2).remove(0), "Share JPEG file");
          paramAnonymousMessage.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
          Activity_FolderFile.this.startActivity(paramAnonymousMessage);
          return;
        }
        Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Error: No resource images found.", 0).show();
        return;
        paramAnonymousMessage = new ArrayList();
        localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
      } while (((List)localObject2).isEmpty());
      Object localObject2 = ((List)localObject2).iterator();
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ResolveInfo)((Iterator)localObject2).next();
        localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
        ((Intent)localObject4).setType("image/jpeg");
        if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_to", ""))) {
          ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("emailsetting_to", "") });
        }
        if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_subject", ""))) {
          ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_FolderFile.this.preferences.getString("emailsetting_subject", ""));
        }
        for (;;)
        {
          if (!"".equals(Activity_FolderFile.this.preferences.getString("emailsetting_body", ""))) {
            ((Intent)localObject4).putExtra("android.intent.extra.TEXT", Activity_FolderFile.this.preferences.getString("emailsetting_body", ""));
          }
          if ((!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook"))) {
            break;
          }
          ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
          ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
          paramAnonymousMessage.add(localObject4);
          break;
          if (Activity_FolderFile.idlist.size() > 1) {
            ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + " and other " + (Activity_FolderFile.idlist.size() - 1) + " documents");
          } else if (Activity_FolderFile.idlist.size() == 1) {
            ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
          } else {
            ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Top Scanner");
          }
        }
      }
      if (paramAnonymousMessage.size() > 0)
      {
        localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Email");
        ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
        Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
        return;
      }
      Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165275), 0).show();
    }
  };
  private boolean hasFolder = false;
  private IAPBuy iapBuy;
  private LayoutInflater inflater;
  private boolean isSearch = false;
  private boolean isSelect = false;
  private boolean islongclick = false;
  private ListView list;
  private int list_type;
  private LinearLayout listphotos;
  private MyDbHelper mDbHelper;
  private Thread mThread;
  private GridAdapter madapter;
  private ListAdapter madapter2;
  private MyApplication mapp;
  private ArrayList<Photo_item> mlist = null;
  private ArrayList<MainListDao> moreList = new ArrayList();
  View.OnClickListener myOnClickListener = new View.OnClickListener()
  {
    @SuppressLint({"InflateParams"})
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131624102: 
      case 2131624103: 
      case 2131624104: 
      case 2131624105: 
      case 2131624106: 
      case 2131624107: 
      case 2131624108: 
      case 2131624109: 
      case 2131624111: 
      case 2131624112: 
      case 2131624113: 
      case 2131624114: 
      case 2131624115: 
      case 2131624120: 
      default: 
        return;
      case 2131624125: 
        Activity_FolderFile.this.deleteDocment();
        return;
      case 2131624124: 
        Activity_FolderFile.this.showPdf_or_imagejpeg_SelectDailog(2);
        return;
      case 2131624126: 
        Activity_FolderFile.this.initPopuptWindow2(2);
        Activity_FolderFile.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
        return;
      case 2131624123: 
        Activity_FolderFile.this.showPdf_or_imagejpeg_SelectDailog(1);
        return;
      case 2131624122: 
        Activity_FolderFile.this.Rename();
        return;
      case 2131624121: 
        Activity_FolderFile.this.takePicture(false);
        return;
      case 2131624101: 
        Activity_FolderFile.this.search();
        return;
      case 2131624110: 
        int i;
        if (Activity_FolderFile.this.folder_selectall.getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165561)))
        {
          Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165291));
          Activity_FolderFile.idlist.clear();
          i = 0;
          while (i < Activity_FolderFile.mlist2.size())
          {
            ((Photo_info)Activity_FolderFile.mlist2.get(i)).setCheck(true);
            paramAnonymousView = (Photo_info)Activity_FolderFile.mlist2.get(i);
            Activity_FolderFile.idlist.add(paramAnonymousView);
            i += 1;
          }
          if (Activity_FolderFile.this.list_type == 0)
          {
            Activity_FolderFile.this.madapter.isse = true;
            Activity_FolderFile.this.madapter.notifyDataSetChanged();
            Activity_FolderFile.this.selected();
          }
        }
        for (;;)
        {
          Activity_FolderFile.this.changeView();
          return;
          Activity_FolderFile.this.madapter2.isse = true;
          Activity_FolderFile.this.madapter2.notifyDataSetChanged();
          break;
          if (Activity_FolderFile.this.folder_selectall.getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165291)))
          {
            Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165561));
            i = 0;
            while (i < Activity_FolderFile.mlist2.size())
            {
              ((Photo_info)Activity_FolderFile.mlist2.get(i)).setCheck(false);
              Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(i));
              i += 1;
            }
            if (Activity_FolderFile.idlist.isEmpty())
            {
              Activity_FolderFile.this.unselected();
              Activity_FolderFile.access$802(Activity_FolderFile.this, false);
              if (Activity_FolderFile.this.list_type == 0) {
                Activity_FolderFile.this.madapter.isse = false;
              }
            }
            for (;;)
            {
              if (Activity_FolderFile.this.list_type != 0) {
                break label599;
              }
              Activity_FolderFile.this.madapter.notifyDataSetChanged();
              break;
              Activity_FolderFile.this.madapter2.isse = false;
              continue;
              Activity_FolderFile.this.selected();
            }
            Activity_FolderFile.this.madapter2.notifyDataSetChanged();
          }
        }
      case 2131624116: 
        Activity_FolderFile.this.showPdf_or_imagejpeg_SelectDailog(1);
        return;
      case 2131624117: 
        Activity_FolderFile.this.showPdf_or_imagejpeg_SelectDailog(2);
        return;
      case 2131624118: 
        label599:
        Activity_FolderFile.this.deleteDocment();
        return;
      }
      Activity_FolderFile.this.initPopuptWindow2(2);
      if (Activity_FolderFile.this.folder_relativelayout_ads.getVisibility() == 0)
      {
        Activity_FolderFile.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, Activity_FolderFile.this.dip2px(56.0F));
        return;
      }
      Activity_FolderFile.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, 0);
    }
  };
  private String oldname;
  private int pdf_OR_jpeg = 1;
  private List<HashMap<String, Object>> pdf_file_name;
  private PopupWindow popupWindow;
  private SharedPreferences preferences;
  private String root_Path2;
  private String root_Path3;
  private String root_Path4;
  private boolean searchandclick = false;
  private int select_jpeg_posiotion = 0;
  private int select_pdf_posiotion = 0;
  private int selecter_docmentCount = 0;
  private TextView selecttext;
  private int sort_type;
  private TextView title;
  
  public Activity_FolderFile() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void Rename()
  {
    final View localView = this.inflater.inflate(2130903122, null);
    EditText localEditText = (EditText)localView.findViewById(2131624336);
    localEditText.setSelectAllOnFocus(true);
    localEditText.setText(this.title.getText().toString());
    new AlertDialog.Builder(this.context).setTitle(getString(2131165537)).setView(localView).setPositiveButton(getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        EditText localEditText = (EditText)localView.findViewById(2131624336);
        Utils.closeKeyBoard(Activity_FolderFile.this.activity_folderFile, localEditText);
        Object localObject2 = new File(Activity_FolderFile.this.folderPath);
        if (localEditText.getText().toString().equals(((File)localObject2).getName())) {
          return;
        }
        if (localEditText.getText().toString().equals(""))
        {
          Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131165333));
          return;
        }
        paramAnonymousDialogInterface = localEditText.getText().toString();
        Object localObject1 = localEditText.getText().toString();
        if (Activity_FolderFile.this.checkFoldername(paramAnonymousDialogInterface))
        {
          Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131165332));
          return;
        }
        if (Activity_FolderFile.this.checkName(paramAnonymousDialogInterface)) {}
        for (;;)
        {
          localObject1 = new File(Activity_FolderFile.this.root_Path4 + paramAnonymousDialogInterface);
          if (((File)localObject2).exists()) {
            ((File)localObject2).renameTo((File)localObject1);
          }
          Activity_FolderFile.this.editor.putString("folder_path", ((File)localObject1).getPath());
          Activity_FolderFile.this.editor.commit();
          Activity_FolderFile.access$1002(Activity_FolderFile.this, ((File)localObject1).getPath());
          localObject1 = Activity_FolderFile.mlist2.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = ((Photo_info)((Iterator)localObject1).next()).getImage_name().iterator();
            while (((Iterator)localObject2).hasNext())
            {
              localObject3 = (String)((Iterator)localObject2).next();
              BitmapDrawable localBitmapDrawable = Activity_FolderFile.this.mapp.getBitmapFromMemCache("main" + (String)localObject3);
              Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)localObject3);
              paramAnonymousInt = ((String)localObject3).lastIndexOf("/", ((String)localObject3).lastIndexOf("/") - 1);
              Activity_FolderFile.this.mapp.addBitmapToMemoryCache("main" + Activity_FolderFile.this.root_Path4 + paramAnonymousDialogInterface + "/" + ((String)localObject3).substring(paramAnonymousInt + 1, ((String)localObject3).length()), localBitmapDrawable);
            }
          }
          Object localObject3 = new Timestamp(System.currentTimeMillis());
          localObject3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject3);
          localObject1 = ((String)localObject1).replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject3;
          Activity_FolderFile.this.saveNameToDb((String)localObject1, paramAnonymousDialogInterface);
          paramAnonymousDialogInterface = (DialogInterface)localObject1;
        }
        Activity_FolderFile.this.relist();
        Activity_FolderFile.this.title.setVisibility(0);
        Activity_FolderFile.this.title.setText(localEditText.getText().toString());
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
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
  
  private boolean checkName(String paramString)
  {
    return (!paramString.contains("*")) && (!paramString.contains("/")) && (!paramString.contains("\\")) && (!paramString.contains("\"")) && (!paramString.contains(":")) && (!paramString.contains("?")) && (!paramString.contains("|")) && (!paramString.contains("<")) && (!paramString.contains(">"));
  }
  
  private void clear()
  {
    if (this.list_type == 0)
    {
      if (this.madapter != null) {
        this.madapter.isse = false;
      }
      if (!this.searchandclick) {
        break label191;
      }
      unselected2();
      label33:
      if (this.mapp.isPad()) {
        break label198;
      }
      this.folder_more.setVisibility(0);
    }
    for (;;)
    {
      this.title.setVisibility(0);
      this.folder_imagesearch.setVisibility(0);
      this.selecttext.setVisibility(8);
      this.folder_selecttextview.setVisibility(8);
      this.folder_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165561));
      this.folder_selectall.setVisibility(8);
      this.folder_search_text.setText("");
      this.folder_search_relative.setVisibility(8);
      this.folder_onlongclick_layout.setVisibility(8);
      relist();
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.folder_search_text.getWindowToken(), 0);
      this.isSearch = false;
      return;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label191:
      unselected();
      break label33;
      label198:
      this.folder_image_editname.setVisibility(0);
      this.folder_image_importgallery.setVisibility(0);
    }
  }
  
  private void clear2()
  {
    this.folder_search_relative.setVisibility(8);
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.folder_search_text.getWindowToken(), 0);
  }
  
  private void createPDF()
  {
    Object localObject = new File(this.root_Path2);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".pdf"));
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        clearFile(localObject[i]);
        i += 1;
      }
    }
    ((File)localObject).mkdir();
    this.folderfile_progreebar.setVisibility(0);
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
        //   4: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   7: invokestatic 33	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3900	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   10: invokeinterface 39 1 0
        //   15: if_icmpge +1175 -> 1190
        //   18: new 41	java/io/File
        //   21: dup
        //   22: aload_0
        //   23: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   26: invokestatic 33	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3900	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   29: iload_1
        //   30: invokeinterface 45 2 0
        //   35: checkcast 47	java/util/HashMap
        //   38: ldc 49
        //   40: invokevirtual 52	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   43: checkcast 54	java/lang/String
        //   46: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   49: astore 7
        //   51: aload_0
        //   52: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   55: invokestatic 61	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3200	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)I
        //   58: ifne +185 -> 243
        //   61: aload 7
        //   63: new 63	com/simpleapp/tinyscanfree/Activity_FolderFile$MyFilter2
        //   66: dup
        //   67: aload_0
        //   68: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   71: invokespecial 65	com/simpleapp/tinyscanfree/Activity_FolderFile$MyFilter2:<init>	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)V
        //   74: invokevirtual 69	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   77: astore 4
        //   79: aload 4
        //   81: arraylength
        //   82: ifle +11 -> 93
        //   85: aload 4
        //   87: iconst_0
        //   88: aaload
        //   89: invokevirtual 73	java/io/File:delete	()Z
        //   92: pop
        //   93: new 41	java/io/File
        //   96: dup
        //   97: new 75	java/lang/StringBuilder
        //   100: dup
        //   101: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   104: aload_0
        //   105: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   108: invokestatic 33	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3900	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   111: iload_1
        //   112: invokeinterface 45 2 0
        //   117: checkcast 47	java/util/HashMap
        //   120: ldc 49
        //   122: invokevirtual 52	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   125: checkcast 54	java/lang/String
        //   128: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: ldc 82
        //   133: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: aload_0
        //   137: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   140: invokestatic 33	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3900	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   143: iload_1
        //   144: invokeinterface 45 2 0
        //   149: checkcast 47	java/util/HashMap
        //   152: ldc 84
        //   154: invokevirtual 52	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   157: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   160: ldc 89
        //   162: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   168: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   171: astore 4
        //   173: aload 4
        //   175: invokevirtual 96	java/io/File:exists	()Z
        //   178: ifeq +9 -> 187
        //   181: aload 4
        //   183: invokevirtual 73	java/io/File:delete	()Z
        //   186: pop
        //   187: aload 7
        //   189: invokevirtual 100	java/io/File:list	()[Ljava/lang/String;
        //   192: astore 5
        //   194: new 102	java/util/ArrayList
        //   197: dup
        //   198: invokespecial 103	java/util/ArrayList:<init>	()V
        //   201: astore 8
        //   203: iconst_0
        //   204: istore_2
        //   205: iload_2
        //   206: aload 5
        //   208: arraylength
        //   209: if_icmpge +95 -> 304
        //   212: aload 5
        //   214: iload_2
        //   215: aaload
        //   216: ldc 105
        //   218: invokevirtual 109	java/lang/String:matches	(Ljava/lang/String;)Z
        //   221: ifeq +15 -> 236
        //   224: aload 8
        //   226: aload 5
        //   228: iload_2
        //   229: aaload
        //   230: invokeinterface 113 2 0
        //   235: pop
        //   236: iload_2
        //   237: iconst_1
        //   238: iadd
        //   239: istore_2
        //   240: goto -35 -> 205
        //   243: new 41	java/io/File
        //   246: dup
        //   247: new 75	java/lang/StringBuilder
        //   250: dup
        //   251: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   254: aload_0
        //   255: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   258: invokestatic 117	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3300	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
        //   261: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload_0
        //   265: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   268: invokestatic 33	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3900	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   271: iload_1
        //   272: invokeinterface 45 2 0
        //   277: checkcast 47	java/util/HashMap
        //   280: ldc 84
        //   282: invokevirtual 52	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   285: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   288: ldc 89
        //   290: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   296: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   299: astore 4
        //   301: goto -128 -> 173
        //   304: aload 8
        //   306: getstatic 121	com/simpleapp/tinyscanfree/Activity_FolderFile:comparator3	Ljava/util/Comparator;
        //   309: invokestatic 127	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   312: getstatic 133	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   315: astore 5
        //   317: new 135	com/itextpdf/text/Document
        //   320: dup
        //   321: invokespecial 136	com/itextpdf/text/Document:<init>	()V
        //   324: astore 9
        //   326: aconst_null
        //   327: astore 6
        //   329: aconst_null
        //   330: astore 5
        //   332: new 138	com/itextpdf/text/pdf/PdfCopy
        //   335: dup
        //   336: aload 9
        //   338: new 140	java/io/FileOutputStream
        //   341: dup
        //   342: aload 4
        //   344: invokespecial 143	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   347: invokespecial 146	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   350: astore 4
        //   352: aload 9
        //   354: invokevirtual 149	com/itextpdf/text/Document:open	()V
        //   357: aload 4
        //   359: astore 5
        //   361: aload 8
        //   363: invokeinterface 39 1 0
        //   368: istore_3
        //   369: iconst_0
        //   370: istore_2
        //   371: iload_2
        //   372: iload_3
        //   373: if_icmpge +805 -> 1178
        //   376: aload 8
        //   378: iload_2
        //   379: invokeinterface 45 2 0
        //   384: checkcast 54	java/lang/String
        //   387: bipush 14
        //   389: bipush 15
        //   391: invokevirtual 153	java/lang/String:substring	(II)Ljava/lang/String;
        //   394: invokestatic 159	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   397: tableswitch	default:+39->436, 0:+425->822, 1:+433->830, 2:+441->838, 3:+449->846, 4:+457->854, 5:+465->862
        //   436: getstatic 133	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   439: astore 4
        //   441: new 135	com/itextpdf/text/Document
        //   444: dup
        //   445: aload 4
        //   447: invokespecial 162	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   450: astore 10
        //   452: aload_0
        //   453: aload 10
        //   455: new 140	java/io/FileOutputStream
        //   458: dup
        //   459: new 75	java/lang/StringBuilder
        //   462: dup
        //   463: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   466: aload_0
        //   467: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   470: invokestatic 117	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3300	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
        //   473: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: aload 8
        //   478: iload_2
        //   479: invokeinterface 45 2 0
        //   484: checkcast 54	java/lang/String
        //   487: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: ldc 89
        //   492: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   495: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   498: invokespecial 163	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   501: invokestatic 169	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   504: putfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   507: aload 10
        //   509: invokevirtual 149	com/itextpdf/text/Document:open	()V
        //   512: aconst_null
        //   513: astore 4
        //   515: aload_0
        //   516: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   519: invokestatic 61	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3200	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)I
        //   522: ifne +356 -> 878
        //   525: new 75	java/lang/StringBuilder
        //   528: dup
        //   529: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   532: aload 7
        //   534: invokevirtual 174	java/io/File:getPath	()Ljava/lang/String;
        //   537: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   540: ldc 82
        //   542: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   545: aload 8
        //   547: iload_2
        //   548: invokeinterface 45 2 0
        //   553: checkcast 54	java/lang/String
        //   556: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   562: invokestatic 179	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   565: astore 6
        //   567: aload 6
        //   569: invokevirtual 183	com/itextpdf/text/Image:getWidth	()F
        //   572: aload 10
        //   574: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   577: invokevirtual 190	com/itextpdf/text/Rectangle:getWidth	()F
        //   580: fcmpl
        //   581: ifge +20 -> 601
        //   584: aload 6
        //   586: invokevirtual 193	com/itextpdf/text/Image:getHeight	()F
        //   589: aload 10
        //   591: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   594: invokevirtual 194	com/itextpdf/text/Rectangle:getHeight	()F
        //   597: fcmpl
        //   598: iflt +13 -> 611
        //   601: aload 6
        //   603: aload 10
        //   605: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   608: invokevirtual 197	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   611: aload 6
        //   613: aload 10
        //   615: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   618: invokevirtual 190	com/itextpdf/text/Rectangle:getWidth	()F
        //   621: aload 6
        //   623: invokevirtual 200	com/itextpdf/text/Image:getScaledWidth	()F
        //   626: fsub
        //   627: fconst_2
        //   628: fdiv
        //   629: aload 10
        //   631: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   634: invokevirtual 194	com/itextpdf/text/Rectangle:getHeight	()F
        //   637: aload 6
        //   639: invokevirtual 203	com/itextpdf/text/Image:getScaledHeight	()F
        //   642: fsub
        //   643: fconst_2
        //   644: fdiv
        //   645: invokevirtual 207	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   648: aload 10
        //   650: aload 6
        //   652: invokevirtual 210	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   655: pop
        //   656: aload_0
        //   657: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   660: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   663: ifeq +18 -> 681
        //   666: aload_0
        //   667: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   670: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   673: ifne +8 -> 681
        //   676: aload 10
        //   678: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   681: new 221	com/itextpdf/text/pdf/PdfReader
        //   684: dup
        //   685: new 75	java/lang/StringBuilder
        //   688: dup
        //   689: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   692: aload_0
        //   693: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   696: invokestatic 117	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3300	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
        //   699: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   702: aload 8
        //   704: iload_2
        //   705: invokeinterface 45 2 0
        //   710: checkcast 54	java/lang/String
        //   713: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   716: ldc 89
        //   718: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   721: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   724: invokespecial 222	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   727: astore 4
        //   729: aload 5
        //   731: aload 4
        //   733: invokevirtual 226	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   736: aload 4
        //   738: invokevirtual 227	com/itextpdf/text/pdf/PdfReader:close	()V
        //   741: new 41	java/io/File
        //   744: dup
        //   745: new 75	java/lang/StringBuilder
        //   748: dup
        //   749: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   752: aload_0
        //   753: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   756: invokestatic 117	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3300	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
        //   759: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   762: aload 8
        //   764: iload_2
        //   765: invokeinterface 45 2 0
        //   770: checkcast 54	java/lang/String
        //   773: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   776: ldc 89
        //   778: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   781: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   784: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   787: invokevirtual 73	java/io/File:delete	()Z
        //   790: pop
        //   791: iload_2
        //   792: iconst_1
        //   793: iadd
        //   794: istore_2
        //   795: goto -424 -> 371
        //   798: astore 4
        //   800: aload 4
        //   802: invokevirtual 230	java/io/FileNotFoundException:printStackTrace	()V
        //   805: goto -444 -> 361
        //   808: astore 4
        //   810: aload 6
        //   812: astore 5
        //   814: aload 4
        //   816: invokevirtual 231	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   819: goto -458 -> 361
        //   822: getstatic 234	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   825: astore 4
        //   827: goto -386 -> 441
        //   830: getstatic 133	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   833: astore 4
        //   835: goto -394 -> 441
        //   838: getstatic 237	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   841: astore 4
        //   843: goto -402 -> 441
        //   846: getstatic 240	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   849: astore 4
        //   851: goto -410 -> 441
        //   854: getstatic 243	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   857: astore 4
        //   859: goto -418 -> 441
        //   862: new 189	com/itextpdf/text/Rectangle
        //   865: dup
        //   866: ldc -12
        //   868: ldc -11
        //   870: invokespecial 247	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   873: astore 4
        //   875: goto -434 -> 441
        //   878: new 249	java/io/FileInputStream
        //   881: dup
        //   882: new 41	java/io/File
        //   885: dup
        //   886: new 75	java/lang/StringBuilder
        //   889: dup
        //   890: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   893: aload 7
        //   895: invokevirtual 174	java/io/File:getPath	()Ljava/lang/String;
        //   898: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   901: ldc 82
        //   903: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   906: aload 8
        //   908: iload_2
        //   909: invokeinterface 45 2 0
        //   914: checkcast 54	java/lang/String
        //   917: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   920: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   923: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   926: invokespecial 250	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   929: invokestatic 256	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   932: astore 11
        //   934: new 258	java/io/ByteArrayOutputStream
        //   937: dup
        //   938: invokespecial 259	java/io/ByteArrayOutputStream:<init>	()V
        //   941: astore 6
        //   943: aload_0
        //   944: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   947: invokestatic 61	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3200	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)I
        //   950: iconst_1
        //   951: if_icmpne +100 -> 1051
        //   954: aload 11
        //   956: getstatic 265	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   959: bipush 50
        //   961: aload 6
        //   963: invokevirtual 271	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   966: pop
        //   967: aload 6
        //   969: invokevirtual 275	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   972: invokestatic 278	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   975: astore 4
        //   977: aload 4
        //   979: astore 6
        //   981: aload 11
        //   983: ifnull -416 -> 567
        //   986: aload 4
        //   988: astore 6
        //   990: aload 11
        //   992: invokevirtual 281	android/graphics/Bitmap:isRecycled	()Z
        //   995: ifne -428 -> 567
        //   998: aload 11
        //   1000: invokevirtual 284	android/graphics/Bitmap:recycle	()V
        //   1003: aload 4
        //   1005: astore 6
        //   1007: goto -440 -> 567
        //   1010: astore 4
        //   1012: getstatic 290	java/lang/System:err	Ljava/io/PrintStream;
        //   1015: aload 4
        //   1017: invokevirtual 293	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1020: invokevirtual 298	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1023: aload_0
        //   1024: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1027: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1030: ifeq -349 -> 681
        //   1033: aload_0
        //   1034: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1037: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1040: ifne -359 -> 681
        //   1043: aload 10
        //   1045: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1048: goto -367 -> 681
        //   1051: aload_0
        //   1052: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   1055: invokestatic 61	com/simpleapp/tinyscanfree/Activity_FolderFile:access$3200	(Lcom/simpleapp/tinyscanfree/Activity_FolderFile;)I
        //   1058: iconst_2
        //   1059: if_icmpne -82 -> 977
        //   1062: aload 11
        //   1064: getstatic 265	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1067: iconst_5
        //   1068: aload 6
        //   1070: invokevirtual 271	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1073: pop
        //   1074: aload 6
        //   1076: invokevirtual 275	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1079: invokestatic 278	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1082: astore 4
        //   1084: goto -107 -> 977
        //   1087: astore 4
        //   1089: getstatic 290	java/lang/System:err	Ljava/io/PrintStream;
        //   1092: aload 4
        //   1094: invokevirtual 299	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1097: invokevirtual 298	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1100: aload_0
        //   1101: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1104: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1107: ifeq -426 -> 681
        //   1110: aload_0
        //   1111: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1114: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1117: ifne -436 -> 681
        //   1120: aload 10
        //   1122: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1125: goto -444 -> 681
        //   1128: astore 4
        //   1130: aload_0
        //   1131: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1134: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1137: ifeq +18 -> 1155
        //   1140: aload_0
        //   1141: getfield 171	com/simpleapp/tinyscanfree/Activity_FolderFile$27:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1144: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1147: ifne +8 -> 1155
        //   1150: aload 10
        //   1152: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1155: aload 4
        //   1157: athrow
        //   1158: astore 4
        //   1160: aload 4
        //   1162: invokevirtual 300	java/io/IOException:printStackTrace	()V
        //   1165: goto -374 -> 791
        //   1168: astore 4
        //   1170: aload 4
        //   1172: invokevirtual 231	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1175: goto -384 -> 791
        //   1178: aload 9
        //   1180: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1183: iload_1
        //   1184: iconst_1
        //   1185: iadd
        //   1186: istore_1
        //   1187: goto -1185 -> 2
        //   1190: new 302	android/os/Message
        //   1193: dup
        //   1194: invokespecial 303	android/os/Message:<init>	()V
        //   1197: astore 4
        //   1199: aload 4
        //   1201: iconst_3
        //   1202: putfield 307	android/os/Message:what	I
        //   1205: aload_0
        //   1206: getfield 19	com/simpleapp/tinyscanfree/Activity_FolderFile$27:this$0	Lcom/simpleapp/tinyscanfree/Activity_FolderFile;
        //   1209: getfield 311	com/simpleapp/tinyscanfree/Activity_FolderFile:handler	Landroid/os/Handler;
        //   1212: aload 4
        //   1214: invokevirtual 317	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1217: pop
        //   1218: return
        //   1219: astore 6
        //   1221: aload 4
        //   1223: astore 5
        //   1225: aload 6
        //   1227: astore 4
        //   1229: goto -415 -> 814
        //   1232: astore 6
        //   1234: aload 4
        //   1236: astore 5
        //   1238: aload 6
        //   1240: astore 4
        //   1242: goto -442 -> 800
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1245	0	this	27
        //   1	1186	1	i	int
        //   204	705	2	j	int
        //   368	6	3	k	int
        //   77	660	4	localObject1	Object
        //   798	3	4	localFileNotFoundException1	java.io.FileNotFoundException
        //   808	7	4	localDocumentException1	com.itextpdf.text.DocumentException
        //   825	179	4	localObject2	Object
        //   1010	6	4	localDocumentException2	com.itextpdf.text.DocumentException
        //   1082	1	4	localImage	com.itextpdf.text.Image
        //   1087	6	4	localIOException1	IOException
        //   1128	28	4	localObject3	Object
        //   1158	3	4	localIOException2	IOException
        //   1168	3	4	localDocumentException3	com.itextpdf.text.DocumentException
        //   1197	44	4	localObject4	Object
        //   192	1045	5	localObject5	Object
        //   327	748	6	localObject6	Object
        //   1219	7	6	localDocumentException4	com.itextpdf.text.DocumentException
        //   1232	7	6	localFileNotFoundException2	java.io.FileNotFoundException
        //   49	845	7	localFile	File
        //   201	706	8	localArrayList	ArrayList
        //   324	855	9	localDocument1	com.itextpdf.text.Document
        //   450	701	10	localDocument2	com.itextpdf.text.Document
        //   932	131	11	localBitmap	Bitmap
        // Exception table:
        //   from	to	target	type
        //   332	352	798	java/io/FileNotFoundException
        //   332	352	808	com/itextpdf/text/DocumentException
        //   452	512	1010	com/itextpdf/text/DocumentException
        //   515	567	1010	com/itextpdf/text/DocumentException
        //   567	601	1010	com/itextpdf/text/DocumentException
        //   601	611	1010	com/itextpdf/text/DocumentException
        //   611	656	1010	com/itextpdf/text/DocumentException
        //   878	977	1010	com/itextpdf/text/DocumentException
        //   990	1003	1010	com/itextpdf/text/DocumentException
        //   1051	1084	1010	com/itextpdf/text/DocumentException
        //   452	512	1087	java/io/IOException
        //   515	567	1087	java/io/IOException
        //   567	601	1087	java/io/IOException
        //   601	611	1087	java/io/IOException
        //   611	656	1087	java/io/IOException
        //   878	977	1087	java/io/IOException
        //   990	1003	1087	java/io/IOException
        //   1051	1084	1087	java/io/IOException
        //   452	512	1128	finally
        //   515	567	1128	finally
        //   567	601	1128	finally
        //   601	611	1128	finally
        //   611	656	1128	finally
        //   878	977	1128	finally
        //   990	1003	1128	finally
        //   1012	1023	1128	finally
        //   1051	1084	1128	finally
        //   1089	1100	1128	finally
        //   681	791	1158	java/io/IOException
        //   681	791	1168	com/itextpdf/text/DocumentException
        //   352	357	1219	com/itextpdf/text/DocumentException
        //   352	357	1232	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  private int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  private void faxMonth()
  {
    FlurryAgent.logEvent("6_simplefax");
    this.pdf_OR_jpeg = 1;
    this.select_pdf_posiotion = 5;
    shareDocment();
  }
  
  public static int findId(Photo_info paramPhoto_info)
  {
    Photo_info localPhoto_info;
    if (paramPhoto_info.isFolder())
    {
      i = 0;
      while (i < mlist2.size())
      {
        localPhoto_info = (Photo_info)mlist2.get(i);
        if ((localPhoto_info != null) && (localPhoto_info.getName().equals(paramPhoto_info.getName())) && (localPhoto_info.isFolder())) {
          return i;
        }
        i += 1;
      }
    }
    int i = 0;
    while (i < mlist2.size())
    {
      localPhoto_info = (Photo_info)mlist2.get(i);
      if ((localPhoto_info != null) && (localPhoto_info.getName().equals(paramPhoto_info.getName())) && (!localPhoto_info.isFolder())) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private String getRealPathFromURI(Uri paramUri)
  {
    paramUri = new CursorLoader(this.context, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
    if (paramUri != null)
    {
      int i = paramUri.getColumnIndexOrThrow("_data");
      paramUri.moveToFirst();
      return paramUri.getString(i);
    }
    return "";
  }
  
  @SuppressLint({"InflateParams"})
  private void initPopuptWindow2(final int paramInt)
  {
    Object localObject1 = new ArrayList();
    if (this.mapp.isPad())
    {
      ((ArrayList)localObject1).clear();
      if (paramInt != 1) {}
    }
    for (;;)
    {
      this.moreList.clear();
      i = 0;
      while (i < ((ArrayList)localObject1).size())
      {
        localObject2 = new MainListDao();
        ((MainListDao)localObject2).setText((String)((ArrayList)localObject1).get(i));
        this.moreList.add(localObject2);
        i += 1;
      }
      if ((idlist.size() == 1) && (!this.hasFolder)) {
        ((ArrayList)localObject1).add(Activity_Main.activity_Main.getResources().getString(2131165537));
      }
      if ((this.selecter_docmentCount > 1) && (!this.hasFolder)) {
        ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165402));
      }
      if (!this.hasFolder) {
        ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165407));
      }
      ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165553));
      if ((Build.VERSION.SDK_INT >= 19) && (!this.hasFolder) && (this.selecter_docmentCount == 1)) {
        ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165520));
      }
      ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165665));
      if ((this.selecter_docmentCount <= 1) && (!this.hasFolder))
      {
        ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165464));
        continue;
        ((ArrayList)localObject1).clear();
        if (paramInt == 1)
        {
          ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165377));
          ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165537));
        }
        else if (paramInt == 2)
        {
          if ((idlist.size() == 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(Activity_Main.activity_Main.getResources().getString(2131165537));
          }
          if ((this.selecter_docmentCount > 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165402));
          }
          if (!this.hasFolder) {
            ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165407));
          }
          ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165553));
          if ((Build.VERSION.SDK_INT >= 19) && (!this.hasFolder) && (this.selecter_docmentCount == 1)) {
            ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165520));
          }
          ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165665));
          if ((this.selecter_docmentCount <= 1) && (!this.hasFolder)) {
            ((ArrayList)localObject1).add(this.activity_folderFile.getResources().getString(2131165464));
          }
        }
      }
    }
    if (this.popupWindow != null)
    {
      this.popupWindow.dismiss();
      this.popupWindow = null;
    }
    localObject1 = this.activity_folderFile.getLayoutInflater().inflate(2130903104, null, false);
    this.popupWindow = new PopupWindow(this.activity_folderFile);
    Object localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    int i = ((DisplayMetrics)localObject2).widthPixels;
    this.popupWindow.setHeight(-2);
    if (this.mapp.isPad()) {
      if (getResources().getConfiguration().orientation == 1) {
        this.popupWindow.setWidth(i * 2 / 5);
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
          if ((Activity_FolderFile.this.popupWindow != null) && (Activity_FolderFile.this.popupWindow.isShowing())) {
            Activity_FolderFile.this.popupWindow.dismiss();
          }
          Activity_FolderFile.access$302(Activity_FolderFile.this, null);
          return false;
        }
      });
      localObject2 = (ListView)((View)localObject1).findViewById(2131624306);
      ((ListView)localObject2).setAdapter(new MoreListAdapter(this.activity_folderFile, this.moreList));
      ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if ((Activity_FolderFile.this.popupWindow != null) && (Activity_FolderFile.this.popupWindow.isShowing())) {
            Activity_FolderFile.this.popupWindow.dismiss();
          }
          Activity_FolderFile.access$302(Activity_FolderFile.this, null);
          if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165402))) {
            Activity_FolderFile.this.mergeDocment();
          }
          do
          {
            return;
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165407)))
            {
              Activity_FolderFile.this.moveoutDocment();
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165520)))
            {
              Activity_FolderFile.this.printMonth();
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165665)))
            {
              Activity_FolderFile.this.faxMonth();
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165464)))
            {
              Activity_FolderFile.this.openInMonth();
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165307)))
            {
              Activity_FolderFile.this.showPdf_or_imagejpeg_SelectDailog(2);
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165377)))
            {
              Activity_FolderFile.this.takePicture(false);
              return;
            }
            if (((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165537)))
            {
              if (paramInt == 1)
              {
                Activity_FolderFile.this.Rename();
                return;
              }
              Activity_FolderFile.this.renameDocument();
              return;
            }
          } while (!((MainListDao)Activity_FolderFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165553)));
          new Thread(new Runnable()
          {
            public void run()
            {
              int i = 0;
              while (i < Activity_FolderFile.idlist.size())
              {
                Object localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName()).list();
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
                Collections.sort((List)localObject1, Activity_FolderFile.comparator3);
                int j = 0;
                for (;;)
                {
                  if (j < ((List)localObject1).size())
                  {
                    localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName() + "/" + (String)((List)localObject1).get(j));
                    File localFile = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TopScanner");
                    if (!localFile.exists()) {
                      localFile.mkdirs();
                    }
                    localFile = new File(localFile.getPath() + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName() + "_" + j + ".jpg");
                    if ((localFile != null) && (localFile.exists())) {
                      localFile.delete();
                    }
                    try
                    {
                      Activity_FolderFile.this.copy((File)localObject2, localFile);
                      MediaScannerConnection.scanFile(Activity_FolderFile.this.getApplicationContext(), new String[] { localFile.getAbsolutePath() }, null, null);
                      j += 1;
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
                i += 1;
              }
              Object localObject1 = new Message();
              ((Message)localObject1).what = 10;
              Activity_FolderFile.this.handler.sendMessage((Message)localObject1);
            }
          }).start();
        }
      });
      ((LinearLayout)((View)localObject1).findViewById(2131624305)).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4) && (Activity_FolderFile.this.popupWindow != null) && (Activity_FolderFile.this.popupWindow.isShowing())) {
            Activity_FolderFile.this.popupWindow.dismiss();
          }
          Activity_FolderFile.access$302(Activity_FolderFile.this, null);
          return false;
        }
      });
      return;
      this.popupWindow.setWidth(i * 2 / 7);
      continue;
      this.popupWindow.setWidth(i * 7 / 10);
    }
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
  
  private void openInMonth()
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
  
  private void relist()
  {
    init();
    if (this.list_type == 0)
    {
      listByGrid();
      return;
    }
    listByList();
  }
  
  private void renameDocument()
  {
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
        if (((EditText)localObject1).getText().toString().equals(((Photo_info)Activity_FolderFile.idlist.get(0)).getName()))
        {
          Activity_FolderFile.this.unselected();
          paramAnonymousDialogInterface.dismiss();
          return;
        }
        if (((EditText)localObject1).getText().toString().trim().equals(""))
        {
          Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131165333));
          return;
        }
        String str = ((EditText)localObject1).getText().toString();
        localObject1 = ((EditText)localObject1).getText().toString();
        Photo_info localPhoto_info = (Photo_info)Activity_FolderFile.idlist.get(0);
        if (Activity_FolderFile.this.checkFilename(str))
        {
          paramAnonymousDialogInterface.dismiss();
          ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
          paramAnonymousDialogInterface = new Message();
          paramAnonymousDialogInterface.what = 31;
          Activity_FolderFile.this.handler.sendMessageDelayed(paramAnonymousDialogInterface, 100L);
          return;
        }
        if (Activity_FolderFile.this.checkName(str)) {
          localObject1 = str;
        }
        for (;;)
        {
          Object localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + ".pdf");
          if (((File)localObject2).exists()) {
            ((File)localObject2).renameTo(new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + "/" + (String)localObject1 + ".pdf"));
          }
          localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
          if (((File)localObject2).exists()) {
            ((File)localObject2).renameTo(new File(Activity_FolderFile.this.folderPath + "/" + (String)localObject1));
          }
          localObject2 = Activity_FolderFile.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
          Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
          Activity_FolderFile.this.mapp.addBitmapToMemoryCache("main" + Activity_FolderFile.this.folderPath + "/" + (String)localObject1 + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), (BitmapDrawable)localObject2);
          localPhoto_info.setName((String)localObject1);
          localPhoto_info.setShowname(str);
          Activity_FolderFile.this.relist();
          paramAnonymousDialogInterface.dismiss();
          return;
          localObject2 = new Timestamp(System.currentTimeMillis());
          localObject2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject2);
          localObject1 = ((String)localObject1).replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject2;
          Activity_FolderFile.this.saveNameToDb((String)localObject1, str);
        }
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
    Utils.closeKeyBoard(this.activity_folderFile, localEditText);
  }
  
  private void saveNameToDb(String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("realname", paramString1);
    localContentValues.put("showname", paramString2);
    this.db.insert("NameMaps", "id", localContentValues);
  }
  
  private void showToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Activity_FolderFile.this.getApplicationContext(), paramString, 0).show();
      }
    });
  }
  
  private void thankBuy(String paramString)
  {
    new AlertDialog.Builder(this).setMessage(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
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
    this.folderfile_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        Object localObject1;
        Object localObject2;
        ArrayList localArrayList;
        int j;
        int k;
        if (i < Activity_FolderFile.idlist.size())
        {
          localObject1 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName());
          Log.i("TAG", "path===" + Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName());
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
          Collections.sort(localArrayList, Activity_FolderFile.comparator3);
          k = localArrayList.size();
          j = 0;
        }
        for (;;)
        {
          if (j < k)
          {
            BufferedOutputStream localBufferedOutputStream;
            try
            {
              localObject2 = BitmapFactory.decodeStream(new FileInputStream(new File(((File)localObject1).getPath() + "/" + (String)localArrayList.get(j))));
              Log.i("TAG", "pp=======" + ((File)localObject1).getPath() + "/" + (String)localArrayList.get(j));
              localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Activity_FolderFile.this.compressJpeg_Path + (String)localArrayList.get(j)));
              if (Activity_FolderFile.this.export_size == 0) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 100, localBufferedOutputStream);
              } else if (Activity_FolderFile.this.export_size == 1) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 50, localBufferedOutputStream);
              }
            }
            catch (IOException localIOException)
            {
              System.err.println(localIOException.getMessage());
            }
            if (Activity_FolderFile.this.export_size == 2) {
              localIOException.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream);
            }
          }
          else
          {
            i += 1;
            break;
            localObject1 = new Message();
            ((Message)localObject1).what = 33;
            Activity_FolderFile.this.handler.sendMessage((Message)localObject1);
            return;
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
  
  public boolean checkFilename(String paramString)
  {
    boolean bool = false;
    File[] arrayOfFile = new File(this.folderPath).listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      if (getShowName(arrayOfFile[i].getName()).equals(paramString)) {
        bool = true;
      }
      i += 1;
    }
    return bool;
  }
  
  public boolean checkFoldername(String paramString)
  {
    boolean bool = false;
    File[] arrayOfFile = new File(this.root_Path4).listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      if (getShowName(arrayOfFile[i].getName()).equals(paramString)) {
        bool = true;
      }
      i += 1;
    }
    return bool;
  }
  
  public void clearFile(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        clearFile(arrayOfFile[i]);
        i += 1;
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
  
  protected void deleteDocment()
  {
    String str = getResources().getString(2131165238);
    new AlertDialog.Builder(this.context).setMessage(str).setPositiveButton(getResources().getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            int i = 0;
            while (i < Activity_FolderFile.idlist.size())
            {
              localObject = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName());
              if (((File)localObject).exists())
              {
                if (((File)localObject).isDirectory())
                {
                  File[] arrayOfFile1 = ((File)localObject).listFiles();
                  int j = 0;
                  while (j < arrayOfFile1.length)
                  {
                    if (arrayOfFile1[j].isDirectory())
                    {
                      File[] arrayOfFile2 = arrayOfFile1[j].listFiles();
                      int m = arrayOfFile2.length;
                      int k = 0;
                      while (k < m)
                      {
                        arrayOfFile2[k].delete();
                        k += 1;
                      }
                    }
                    arrayOfFile1[j].delete();
                    j += 1;
                  }
                }
                ((File)localObject).delete();
                Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)((Photo_info)Activity_FolderFile.idlist.get(i)).getImage_name().get(0));
                Activity_FolderFile.mlist2.remove(Activity_FolderFile.idlist.get(i));
              }
              i += 1;
            }
            Object localObject = new Message();
            ((Message)localObject).what = 0;
            Activity_FolderFile.this.handler.sendMessage((Message)localObject);
          }
        }).start();
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create().show();
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
  
  @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
  public void getResults(String paramString)
  {
    if (paramString.equals("")) {
      relist();
    }
    do
    {
      for (;;)
      {
        return;
        mlist2.clear();
        File[] arrayOfFile = new File(this.root_Path4 + "/" + this.title.getText().toString()).listFiles();
        int k;
        int i;
        int j;
        if (arrayOfFile != null)
        {
          k = arrayOfFile.length;
          i = 0;
          for (;;)
          {
            if (i < k)
            {
              Object localObject2;
              Object localObject1;
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
                ArrayList localArrayList = new ArrayList();
                localArrayList.add(arrayOfFile[i].getPath() + "/" + (String)((List)localObject1).get(0));
                localObject1 = new Photo_info(arrayOfFile[i].getName(), getShowName(arrayOfFile[i].getName()), (String)localObject2, arrayOfFile[i].lastModified(), ((List)localObject1).size(), localArrayList, false, false);
                ((Photo_info)localObject1).setRotepath(this.root_Path3);
                mlist2.add(localObject1);
                i += 1;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  localException.printStackTrace();
                }
              }
            }
          }
        }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        try
        {
          if (this.sort_type == 0) {
            Collections.sort(mlist2, comparator);
          }
          for (;;)
          {
            j = idlist.size();
            i = 0;
            while (i < j)
            {
              k = findId((Photo_info)idlist.get(i));
              if (k != -1) {
                ((Photo_info)mlist2.get(k)).setCheck(true);
              }
              i += 1;
            }
            Collections.sort(mlist2, comparator2);
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
  
  public long getfilesizeLength()
  {
    long l = 0L;
    this.mlist = new ArrayList();
    int i = 0;
    while (i < idlist.size())
    {
      TraverseImagesSize(new File(this.folderPath + "/" + ((Photo_info)idlist.get(i)).getName()));
      i += 1;
    }
    i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    return l;
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public void init()
  {
    mlist2 = new ArrayList();
    File[] arrayOfFile = new File(this.folderPath).listFiles();
    int i = 0;
    if (arrayOfFile != null) {
      i = arrayOfFile.length;
    }
    int j = 0;
    int k;
    while (j < i)
    {
      if (arrayOfFile[j].isDirectory())
      {
        Object localObject2 = arrayOfFile[j].list();
        Object localObject1 = new ArrayList();
        if (localObject2.length > 0)
        {
          k = 0;
          while (k < localObject2.length)
          {
            if (localObject2[k].matches("[0-9]{18}.jpg")) {
              ((List)localObject1).add(localObject2[k]);
            }
            k += 1;
          }
          if (((List)localObject1).size() > 0)
          {
            localObject2 = Utils.getDate(new Date(arrayOfFile[j].lastModified()));
            Collections.sort((List)localObject1, comparator3);
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(arrayOfFile[j].getPath() + "/" + (String)((List)localObject1).get(0));
            localObject1 = new Photo_info(arrayOfFile[j].getName(), getShowName(arrayOfFile[j].getName()), (String)localObject2, arrayOfFile[j].lastModified(), ((List)localObject1).size(), localArrayList, false, false);
            mlist2.add(localObject1);
          }
        }
      }
      j += 1;
    }
    j = idlist.size();
    i = 0;
    while (i < j)
    {
      k = findId((Photo_info)idlist.get(i));
      if (k != -1) {
        ((Photo_info)mlist2.get(k)).setCheck(true);
      }
      i += 1;
    }
    if (this.sort_type == 0)
    {
      Collections.sort(mlist2, comparator);
      return;
    }
    Collections.sort(mlist2, comparator2);
  }
  
  public boolean isEmail(String paramString)
  {
    return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(paramString).matches();
  }
  
  @SuppressLint({"InflateParams"})
  public void listByGrid()
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
      this.madapter = new GridAdapter(this.context, mlist2);
      if (this.islongclick) {
        this.madapter.isse = true;
      }
      this.grid.setAdapter(this.madapter);
      this.grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
          if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
          {
            if (Activity_FolderFile.this.islongclick)
            {
              if (Activity_FolderFile.this.isSearch)
              {
                Activity_FolderFile.access$1902(Activity_FolderFile.this, true);
                Activity_FolderFile.this.clear2();
              }
              if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
              {
                ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
                Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
                if (Activity_FolderFile.idlist.isEmpty()) {
                  if (Activity_FolderFile.this.searchandclick)
                  {
                    Activity_FolderFile.this.unselected2();
                    Activity_FolderFile.access$802(Activity_FolderFile.this, false);
                    Activity_FolderFile.this.madapter.isse = false;
                    label187:
                    Activity_FolderFile.this.madapter.notifyDataSetChanged();
                    if (Activity_FolderFile.idlist.size() != Activity_FolderFile.mlist2.size()) {
                      Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165561));
                    }
                    label239:
                    if (!Activity_FolderFile.idlist.isEmpty()) {
                      break label386;
                    }
                  }
                }
              }
              for (;;)
              {
                Activity_FolderFile.this.changeView();
                return;
                Activity_FolderFile.this.unselected();
                break;
                Activity_FolderFile.this.selected();
                break label187;
                ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
                Activity_FolderFile.this.madapter.isse = true;
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
                paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
                Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
                Activity_FolderFile.this.selected();
                if (Activity_FolderFile.idlist.size() != Activity_FolderFile.mlist2.size()) {
                  break label239;
                }
                Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165291));
                break label239;
                label386:
                if (Activity_FolderFile.idlist.size() <= 1) {}
              }
            }
            if (paramAnonymousAdapterView.isFolder())
            {
              paramAnonymousView = new Intent(Activity_FolderFile.this.context, Activity_FolderFile.class);
              paramAnonymousView.putExtra("folder_path", Activity_FolderFile.this.root_Path4 + paramAnonymousAdapterView.getName());
              Activity_FolderFile.this.startActivity(paramAnonymousView);
              return;
            }
            paramAnonymousAdapterView = new Intent(Activity_FolderFile.this.context, Activity_SelectFile.class);
            Activity_FolderFile.this.mapp.setStateheight(Activity_FolderFile.this.back.getHeight() * 2);
            Activity_FolderFile.this.editor.putString("folder_path", Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getName());
            Activity_FolderFile.this.editor.putString("folder_name", ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getShowname());
            Activity_FolderFile.this.editor.putString("folder_root_path", Activity_FolderFile.this.folderPath + "/");
            Activity_FolderFile.this.editor.putInt("folder_folder_id", paramAnonymousInt);
            Activity_FolderFile.this.editor.commit();
            Activity_FolderFile.this.mapp.setAdd(false);
            Activity_FolderFile.this.startActivity(paramAnonymousAdapterView);
            return;
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165331), 0).show();
          Activity_FolderFile.mlist2.remove(paramAnonymousInt);
          if (Activity_FolderFile.this.list_type == 0)
          {
            Activity_FolderFile.this.madapter.notifyDataSetChanged();
            return;
          }
          Activity_FolderFile.this.madapter2.notifyDataSetChanged();
        }
      });
      this.grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
          if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
          {
            if (Activity_FolderFile.this.isSearch)
            {
              Activity_FolderFile.access$1902(Activity_FolderFile.this, true);
              Activity_FolderFile.this.clear2();
            }
            Activity_FolderFile.access$802(Activity_FolderFile.this, true);
            if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
            {
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
              Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
              if (Activity_FolderFile.idlist.isEmpty()) {
                if (Activity_FolderFile.this.searchandclick)
                {
                  Activity_FolderFile.this.unselected2();
                  Activity_FolderFile.access$802(Activity_FolderFile.this, false);
                  Activity_FolderFile.this.madapter.isse = false;
                  label186:
                  Activity_FolderFile.this.madapter.notifyDataSetChanged();
                  label196:
                  if (!Activity_FolderFile.idlist.isEmpty()) {
                    break label302;
                  }
                }
              }
            }
            for (;;)
            {
              Activity_FolderFile.this.changeView();
              return true;
              Activity_FolderFile.this.unselected();
              break;
              Activity_FolderFile.this.selected();
              break label186;
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
              Activity_FolderFile.this.madapter.isse = true;
              Activity_FolderFile.this.madapter.notifyDataSetChanged();
              paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
              Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
              Activity_FolderFile.this.selected();
              break label196;
              label302:
              if (Activity_FolderFile.idlist.size() <= 1) {}
            }
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165331), 0).show();
          Activity_FolderFile.mlist2.remove(paramAnonymousInt);
          if (Activity_FolderFile.this.list_type == 0)
          {
            Activity_FolderFile.this.madapter.notifyDataSetChanged();
            return true;
          }
          Activity_FolderFile.this.madapter2.notifyDataSetChanged();
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
  public void listByList()
  {
    this.listphotos.removeAllViews();
    View localView = this.inflater.inflate(2130903099, null);
    this.listphotos.addView(localView);
    this.list = ((ListView)localView.findViewById(2131624291));
    this.madapter2 = new ListAdapter(this.context, mlist2);
    if (this.islongclick) {
      this.madapter2.isse = true;
    }
    this.list.setAdapter(this.madapter2);
    this.list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
        if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
        {
          if (Activity_FolderFile.this.islongclick)
          {
            if (Activity_FolderFile.this.isSearch)
            {
              Activity_FolderFile.access$1902(Activity_FolderFile.this, true);
              Activity_FolderFile.this.clear2();
            }
            if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
            {
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
              Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
              if (Activity_FolderFile.idlist.isEmpty()) {
                if (Activity_FolderFile.this.searchandclick)
                {
                  Activity_FolderFile.this.unselected2();
                  Activity_FolderFile.access$802(Activity_FolderFile.this, false);
                  Activity_FolderFile.this.madapter2.isse = false;
                  label187:
                  if (Activity_FolderFile.this.list_type != 0) {
                    break label299;
                  }
                  Activity_FolderFile.this.madapter.notifyDataSetChanged();
                  label207:
                  if (Activity_FolderFile.idlist.size() != Activity_FolderFile.mlist2.size()) {
                    Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165561));
                  }
                  if (!Activity_FolderFile.idlist.isEmpty()) {
                    break label443;
                  }
                }
              }
            }
            for (;;)
            {
              Activity_FolderFile.this.changeView();
              return;
              Activity_FolderFile.this.unselected();
              break;
              Activity_FolderFile.this.selected();
              Activity_FolderFile.this.madapter2.isse = true;
              break label187;
              label299:
              Activity_FolderFile.this.madapter2.notifyDataSetChanged();
              break label207;
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
              Activity_FolderFile.this.madapter2.isse = true;
              if (Activity_FolderFile.this.list_type == 0) {
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
              }
              for (;;)
              {
                paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
                Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
                Activity_FolderFile.this.selected();
                if (Activity_FolderFile.idlist.size() != Activity_FolderFile.mlist2.size()) {
                  break;
                }
                Activity_FolderFile.this.folder_selectall.setText(Activity_FolderFile.this.activity_folderFile.getResources().getString(2131165291));
                break;
                Activity_FolderFile.this.madapter2.notifyDataSetChanged();
              }
              label443:
              if (Activity_FolderFile.idlist.size() <= 1) {}
            }
          }
          if (paramAnonymousAdapterView.isFolder())
          {
            paramAnonymousView = new Intent(Activity_FolderFile.this.context, Activity_FolderFile.class);
            paramAnonymousView.putExtra("folder_path", Activity_FolderFile.this.root_Path4 + paramAnonymousAdapterView.getName());
            Activity_FolderFile.this.startActivity(paramAnonymousView);
            return;
          }
          paramAnonymousAdapterView = new Intent(Activity_FolderFile.this.context, Activity_SelectFile.class);
          Activity_FolderFile.this.mapp.setStateheight(Activity_FolderFile.this.back.getHeight() * 2);
          Activity_FolderFile.this.editor.putString("document_path", Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getName());
          Activity_FolderFile.this.editor.putString("document_name", ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getShowname());
          Activity_FolderFile.this.editor.putString("folder_root_path", Activity_FolderFile.this.folderPath + "/");
          Activity_FolderFile.this.editor.putInt("folder_folder_id", paramAnonymousInt);
          Activity_FolderFile.this.editor.commit();
          Activity_FolderFile.this.mapp.setAdd(false);
          Activity_FolderFile.this.startActivity(paramAnonymousAdapterView);
          return;
        }
        Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165331), 0).show();
        Activity_FolderFile.mlist2.remove(paramAnonymousInt);
        if (Activity_FolderFile.this.list_type == 0)
        {
          Activity_FolderFile.this.madapter.notifyDataSetChanged();
          return;
        }
        Activity_FolderFile.this.madapter2.notifyDataSetChanged();
      }
    });
    this.list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
        if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
        {
          if (Activity_FolderFile.this.isSearch)
          {
            Activity_FolderFile.access$1902(Activity_FolderFile.this, true);
            Activity_FolderFile.this.clear2();
          }
          Activity_FolderFile.access$802(Activity_FolderFile.this, true);
          if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
          {
            ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
            if (Activity_FolderFile.idlist.isEmpty()) {
              if (Activity_FolderFile.this.searchandclick)
              {
                Activity_FolderFile.this.unselected2();
                Activity_FolderFile.access$802(Activity_FolderFile.this, false);
                Activity_FolderFile.this.madapter2.isse = false;
                label186:
                if (Activity_FolderFile.this.list_type != 0) {
                  break label257;
                }
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
                label206:
                if (!Activity_FolderFile.idlist.isEmpty()) {
                  break label359;
                }
              }
            }
          }
          for (;;)
          {
            Activity_FolderFile.this.changeView();
            return true;
            Activity_FolderFile.this.unselected();
            break;
            Activity_FolderFile.this.selected();
            Activity_FolderFile.this.madapter2.isse = true;
            break label186;
            label257:
            Activity_FolderFile.this.madapter2.notifyDataSetChanged();
            break label206;
            ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
            Activity_FolderFile.this.madapter2.isse = true;
            if (Activity_FolderFile.this.list_type == 0) {
              Activity_FolderFile.this.madapter.notifyDataSetChanged();
            }
            for (;;)
            {
              paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
              Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
              Activity_FolderFile.this.selected();
              break;
              Activity_FolderFile.this.madapter2.notifyDataSetChanged();
            }
            label359:
            if (Activity_FolderFile.idlist.size() <= 1) {}
          }
        }
        Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165331), 0).show();
        Activity_FolderFile.mlist2.remove(paramAnonymousInt);
        if (Activity_FolderFile.this.list_type == 0)
        {
          Activity_FolderFile.this.madapter.notifyDataSetChanged();
          return true;
        }
        Activity_FolderFile.this.madapter2.notifyDataSetChanged();
        return true;
      }
    });
  }
  
  @SuppressLint({"InflateParams"})
  protected void mergeDocment()
  {
    if (idlist.size() == 1) {
      showToast(getResources().getString(2131165405));
    }
    Object localObject;
    do
    {
      return;
      localObject = this.inflater.inflate(2130903122, null);
      EditText localEditText = (EditText)((View)localObject).findViewById(2131624336);
      localEditText.setSelectAllOnFocus(true);
      localEditText.setText(((Photo_info)idlist.get(0)).getShowname());
      localObject = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131165402)).setView((View)localObject).setPositiveButton(getResources().getString(2131165549), new DialogInterface.OnClickListener()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Object localObject2 = (EditText)this.val$view2.findViewById(2131624336);
          if (((EditText)localObject2).getText().toString().trim().equals(""))
          {
            Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131165333));
            return;
          }
          paramAnonymousInt = 0;
          Object localObject1;
          Object localObject3;
          int i;
          while (paramAnonymousInt < Activity_FolderFile.idlist.size())
          {
            localObject1 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName()).listFiles();
            localObject3 = new ArrayList();
            if (localObject1 != null)
            {
              i = 0;
              while (i < localObject1.length)
              {
                if (localObject1[i].getName().matches("[0-9]{18}.jpg")) {
                  ((ArrayList)localObject3).add(localObject1[i].getPath());
                }
                i += 1;
              }
            }
            int j = ((ArrayList)localObject3).size();
            if (paramAnonymousInt != 0)
            {
              localObject3 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(paramAnonymousInt)).getName());
              localObject1 = ((File)localObject3).listFiles();
              Object localObject4 = new ArrayList();
              int k = localObject1.length;
              i = 0;
              File localFile;
              while (i < k)
              {
                localFile = localObject1[i];
                if (localFile.getName().matches("[0-9]{18}.jpg")) {
                  ((ArrayList)localObject4).add(localFile.getPath());
                }
                i += 1;
              }
              Collections.sort((List)localObject4, Activity_FolderFile.comparator3);
              localObject4 = ((ArrayList)localObject4).iterator();
              i = j;
              if (((Iterator)localObject4).hasNext())
              {
                localFile = new File((String)((Iterator)localObject4).next());
                if (i < 10) {
                  localObject1 = "00" + i;
                }
                for (;;)
                {
                  localObject1 = localFile.getName().substring(0, 15) + (String)localObject1 + ".jpg";
                  localFile.renameTo(new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + "/" + (String)localObject1));
                  i += 1;
                  break;
                  if (i < 100) {
                    localObject1 = "0" + i;
                  } else {
                    localObject1 = "" + i;
                  }
                }
              }
              localObject1 = ((File)localObject3).listFiles();
              j = localObject1.length;
              i = 0;
              while (i < j)
              {
                localObject1[i].delete();
                i += 1;
              }
              ((File)localObject3).delete();
            }
            paramAnonymousInt += 1;
          }
          if (((EditText)localObject2).getText().toString().equals(((Photo_info)Activity_FolderFile.idlist.get(0)).getShowname()))
          {
            localObject1 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName()).list(new Activity_FolderFile.MyFilter2(Activity_FolderFile.this));
            if (localObject1 != null)
            {
              i = localObject1.length;
              paramAnonymousInt = 0;
              while (paramAnonymousInt < i)
              {
                localObject2 = localObject1[paramAnonymousInt];
                new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + "/" + (String)localObject2).delete();
                paramAnonymousInt += 1;
              }
            }
          }
          else
          {
            localObject1 = ((EditText)localObject2).getText().toString();
            localObject2 = ((EditText)localObject2).getText().toString();
            if (Activity_FolderFile.this.checkName((String)localObject1)) {}
            for (;;)
            {
              localObject1 = new File(Activity_FolderFile.this.folderPath + "/" + (String)localObject1);
              new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName()).renameTo((File)localObject1);
              localObject2 = ((File)localObject1).list(new Activity_FolderFile.MyFilter2(Activity_FolderFile.this));
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
              Activity_FolderFile.this.saveNameToDb((String)localObject2, (String)localObject1);
              localObject1 = localObject2;
            }
          }
          Activity_FolderFile.this.unselected();
          Activity_FolderFile.this.relist();
          paramAnonymousDialogInterface.dismiss();
        }
      }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
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
        ((InputMethodManager)this.val$mDialog2.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
      }
    }, 100L);
  }
  
  @SuppressLint({"InflateParams"})
  protected void moveoutDocment()
  {
    Object localObject1 = getLayoutInflater().inflate(2130903086, null);
    final ArrayList localArrayList = Activity_Main.getActivity_main().getFolders(this.oldname);
    Object localObject2 = new Photo_info();
    ((Photo_info)localObject2).setShowname(getResources().getString(2131165407));
    localArrayList.add(localObject2);
    localObject2 = new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131165406)).setView((View)localObject1).setNegativeButton(getResources().getString(2131165274), null).create();
    ((AlertDialog)localObject2).show();
    localObject1 = (ListView)((View)localObject1).findViewById(2131624256);
    ((ListView)localObject1).setAdapter(new CopyAdapter(this.context, localArrayList, true));
    ((ListView)localObject1).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            int j;
            Photo_info localPhoto_info;
            BitmapDrawable localBitmapDrawable;
            Object localObject1;
            File localFile;
            int m;
            int i;
            int n;
            int k;
            Object localObject2;
            if (paramAnonymousInt == Activity_FolderFile.45.this.val$sslist2.size() - 1)
            {
              j = 0;
              if (j < Activity_FolderFile.idlist.size())
              {
                localPhoto_info = (Photo_info)Activity_FolderFile.idlist.get(j);
                localBitmapDrawable = Activity_FolderFile.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
                Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
                localObject1 = new File(Activity_FolderFile.this.root_Path3);
                localFile = new File(Activity_FolderFile.this.folderPath + "/" + localPhoto_info.getName());
                localObject1 = ((File)localObject1).listFiles();
                m = 0;
                i = 0;
                if (localObject1 != null)
                {
                  n = localObject1.length;
                  k = 0;
                  for (;;)
                  {
                    m = i;
                    if (k >= n) {
                      break;
                    }
                    localObject2 = localObject1[k];
                    if (localPhoto_info.getShowname().equals(Activity_FolderFile.this.getShowName(localObject2.getName()))) {
                      i = 1;
                    }
                    k += 1;
                  }
                }
                if (m != 0) {
                  if (Activity_FolderFile.this.checkName(localPhoto_info.getShowname()))
                  {
                    i = 2;
                    for (localObject1 = new File(Activity_FolderFile.this.root_Path3 + localPhoto_info.getName() + "(" + 2 + ")"); ((File)localObject1).exists(); localObject1 = new File(Activity_FolderFile.this.root_Path3 + localPhoto_info.getName() + "(" + i + ")")) {
                      i += 1;
                    }
                    localFile.renameTo((File)localObject1);
                  }
                }
                for (;;)
                {
                  Activity_FolderFile.this.mapp.addBitmapToMemoryCache("main" + Activity_FolderFile.this.root_Path3 + ((File)localObject1).getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
                  j += 1;
                  break;
                  Activity_FolderFile.this.updateNameToDb(localPhoto_info.getName(), localPhoto_info.getShowname() + "(2)");
                  localObject1 = new File(Activity_FolderFile.this.root_Path3 + localPhoto_info.getName());
                  localFile.renameTo((File)localObject1);
                  continue;
                  localObject1 = new File(Activity_FolderFile.this.root_Path3 + localPhoto_info.getName());
                  localFile.renameTo((File)localObject1);
                }
              }
              localObject1 = new Message();
              ((Message)localObject1).what = 21;
              Activity_FolderFile.this.handler.sendMessage((Message)localObject1);
            }
            for (;;)
            {
              Activity_FolderFile.45.this.val$mDialog.dismiss();
              return;
              j = 0;
              if (j < Activity_FolderFile.idlist.size())
              {
                localPhoto_info = (Photo_info)Activity_FolderFile.idlist.get(j);
                localBitmapDrawable = Activity_FolderFile.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
                Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
                localFile = new File(Activity_FolderFile.this.folderPath + "/" + localPhoto_info.getName());
                localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName()).listFiles();
                m = 0;
                i = 0;
                if (localObject1 != null)
                {
                  n = localObject1.length;
                  k = 0;
                  for (;;)
                  {
                    m = i;
                    if (k >= n) {
                      break;
                    }
                    localObject2 = localObject1[k];
                    if (localPhoto_info.getShowname().equals(Activity_FolderFile.this.getShowName(localObject2.getName()))) {
                      i = 1;
                    }
                    k += 1;
                  }
                }
                if (m != 0) {
                  if (Activity_FolderFile.this.checkName(localPhoto_info.getShowname()))
                  {
                    i = 2;
                    for (localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + 2 + ")"); ((File)localObject1).exists(); localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName() + "(" + i + ")")) {
                      i += 1;
                    }
                    localFile.renameTo((File)localObject1);
                  }
                }
                for (;;)
                {
                  Activity_FolderFile.this.mapp.addBitmapToMemoryCache("main" + Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + ((File)localObject1).getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
                  j += 1;
                  break;
                  Activity_FolderFile.this.updateNameToDb(localPhoto_info.getName(), localPhoto_info.getShowname() + "(2)");
                  localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
                  localFile.renameTo((File)localObject1);
                  continue;
                  localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.45.this.val$sslist2.get(paramAnonymousInt)).getName() + "/" + localPhoto_info.getName());
                  localFile.renameTo((File)localObject1);
                }
              }
              localObject1 = new Message();
              ((Message)localObject1).what = 21;
              Activity_FolderFile.this.handler.sendMessage((Message)localObject1);
            }
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
      do
      {
        return;
        onAuthenticated(paramInt2, paramIntent);
        return;
        onFolderSelected(paramInt2, paramIntent);
        return;
      } while (paramInt2 != -1);
      String str = getRealPathFromURI(paramIntent.getData());
      this.mapp.setPhotoUri(paramIntent.getData());
      this.mapp.setPhotopath(str);
      this.editor.putBoolean("where", false);
      this.editor.commit();
      this.mapp.setPhotofrom(false);
      this.mapp.setSavePath(this.folderPath + "/");
      startActivity(new Intent(this.context, Activity_Detect.class));
      return;
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
  
  public void onBackPressed()
  {
    if (this.islongclick)
    {
      unselected();
      return;
    }
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mapp.isPad())
    {
      if (paramConfiguration.orientation != 1) {
        break label99;
      }
      paramConfiguration = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
      this.mapp.setDisplaywidth(paramConfiguration.widthPixels);
      this.mapp.setDispalyheight(paramConfiguration.heightPixels);
      this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(80.0F)) / 3);
      this.grid.setNumColumns(3);
    }
    label99:
    while (paramConfiguration.orientation != 2) {
      return;
    }
    paramConfiguration = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
    this.mapp.setDisplaywidth(paramConfiguration.widthPixels);
    this.mapp.setDispalyheight(paramConfiguration.heightPixels);
    this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(120.0F)) / 5);
    this.grid.setNumColumns(5);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.activity_folderFile = this;
    this.context = this;
    MyApplication.activityList.add(this);
    this.mapp = MyApplication.getApplication(this.context);
    if (this.mapp.isPad())
    {
      setContentView(2130903071);
      this.preferences = getSharedPreferences("TopScanner", 0);
      this.editor = this.preferences.edit();
      if ((this.preferences.getInt("SDCARD_PATH", 0) <= 0) || (this.mapp.getSdcard_list().size() <= 1)) {
        break label1105;
      }
      this.root_Path2 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Documents/");
      this.root_Path3 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/Documents/");
      this.root_Path4 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/Folders/");
      this.compressJpeg_Path = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Jpeg/");
      label332:
      this.inflater = LayoutInflater.from(this.context);
      this.listphotos = ((LinearLayout)findViewById(2131624112));
      this.selecttext = ((TextView)findViewById(2131624108));
      this.folderfile_progreebar = ((ProgressBar)findViewById(2131624120));
      this.folder_imagesearch = ((ImageView)findViewById(2131624101));
      this.folder_imagesearch.setOnClickListener(this.myOnClickListener);
      this.folder_imagesearch.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165558), 0);
          paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      if (this.mapp.isPad())
      {
        this.folder_image_editname = ((ImageView)findViewById(2131624122));
        this.folder_image_importgallery = ((ImageView)findViewById(2131624121));
        this.folder_image_editname.setOnClickListener(this.myOnClickListener);
        this.folder_image_importgallery.setOnClickListener(this.myOnClickListener);
        this.folder_image_editname.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165537), 0);
            paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
            paramAnonymousView.show();
            return false;
          }
        });
        this.folder_image_importgallery.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165377), 0);
            paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
            paramAnonymousView.show();
            return false;
          }
        });
      }
      this.folder_search_relative = ((RelativeLayout)findViewById(2131624105));
      this.folder_search_text = ((EditText)findViewById(2131624106));
      this.folder_search_text_delete = ((ImageView)findViewById(2131624107));
      if (!this.mapp.isPad())
      {
        this.folder_more = ((ImageView)findViewById(2131624102));
        this.folder_more.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Activity_FolderFile.this.initPopuptWindow2(1);
            Activity_FolderFile.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
          }
        });
        this.folder_more.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165403), 0);
            paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
            paramAnonymousView.show();
            return false;
          }
        });
      }
      this.folder_search_text_delete.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_FolderFile.this.folder_search_text.setText("");
        }
      });
      this.folder_search_text.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          if (paramAnonymousEditable.toString().equals("")) {
            Activity_FolderFile.this.folder_search_text_delete.setVisibility(4);
          }
          for (;;)
          {
            if (Activity_FolderFile.this.isSearch) {
              Activity_FolderFile.this.getResults(paramAnonymousEditable.toString());
            }
            return;
            Activity_FolderFile.this.folder_search_text_delete.setVisibility(0);
          }
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
      paramBundle = getIntent();
      this.mDbHelper = MyDbHelper.getHelper(this.context);
      this.db = this.mDbHelper.getWritableDatabase();
      this.folderPath = paramBundle.getExtras().getString("folder_path");
      this.list_type = paramBundle.getExtras().getInt("list_type");
      this.sort_type = paramBundle.getExtras().getInt("sort_type");
      idlist = new ArrayList();
      this.title = ((TextView)findViewById(2131624104));
      this.oldname = paramBundle.getExtras().getString("folder_name");
      this.title.setText(this.oldname);
      this.folder_selecttextview = ((TextView)findViewById(2131624109));
      this.folder_selectall = ((RadioButton)findViewById(2131624110));
      this.folder_selectall.setOnClickListener(this.myOnClickListener);
      this.back = ((ImageView)findViewById(2131624103));
      this.back.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_FolderFile.this.isSearch)
          {
            Activity_FolderFile.this.clear();
            return;
          }
          if (Activity_FolderFile.this.islongclick)
          {
            Activity_FolderFile.this.unselected();
            return;
          }
          Activity_FolderFile.this.finish();
        }
      });
      this.camera = ((ImageView)findViewById(2131624114));
      this.camera.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_FolderFile.this.takePicture(true);
        }
      });
      this.folder_onlongclick_layout = ((LinearLayout)findViewById(2131624115));
      this.folder_onlongclick_layout.setVisibility(8);
      if (!this.mapp.isPad()) {
        break label1224;
      }
      this.folder_image_share_pad = ((ImageView)findViewById(2131624123));
      this.folder_image_email_pad = ((ImageView)findViewById(2131624124));
      this.folder_image_delete_pad = ((ImageView)findViewById(2131624125));
      this.folder_image_more_pad = ((ImageView)findViewById(2131624126));
      this.folder_image_share_pad.setOnClickListener(this.myOnClickListener);
      this.folder_image_email_pad.setOnClickListener(this.myOnClickListener);
      this.folder_image_delete_pad.setOnClickListener(this.myOnClickListener);
      this.folder_image_more_pad.setOnClickListener(this.myOnClickListener);
      this.folder_image_share_pad.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165567), 0);
          paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.folder_image_email_pad.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165307), 0);
          paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.folder_image_delete_pad.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165290), 0);
          paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.folder_image_more_pad.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131165403), 0);
          paramAnonymousView.setGravity(48, 0, Activity_FolderFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
    }
    for (;;)
    {
      this.folder_relativelayout_ads = ((RelativeLayout)findViewById(2131624099));
      if (this.preferences.getInt("newUser_1.3", -1) == 1) {
        AdsUtils.showAds(this.activity_folderFile, this.folder_relativelayout_ads, this.mapp, 2);
      }
      return;
      setRequestedOrientation(1);
      break;
      label1105:
      this.root_Path2 = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Documents/");
      this.root_Path3 = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/Documents/");
      this.root_Path4 = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/Folders/");
      this.compressJpeg_Path = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Jpeg/");
      break label332;
      label1224:
      this.folder_sharelayout = ((LinearLayout)findViewById(2131624116));
      this.folder_emaillayout = ((LinearLayout)findViewById(2131624117));
      this.folder_deletelayout = ((LinearLayout)findViewById(2131624118));
      this.folder_morelayout = ((LinearLayout)findViewById(2131624119));
      this.folder_sharelayout.setOnClickListener(this.myOnClickListener);
      this.folder_emaillayout.setOnClickListener(this.myOnClickListener);
      this.folder_deletelayout.setOnClickListener(this.myOnClickListener);
      this.folder_morelayout.setOnClickListener(this.myOnClickListener);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.editor.putInt("folder_folder_id", 0);
    this.editor.commit();
    this.db.close();
  }
  
  protected void onResume()
  {
    super.onResume();
    relist();
    Object localObject;
    if (this.searchandclick)
    {
      search();
      getResults(this.folder_search_text.getText().toString());
      clear2();
      selected();
      if (this.list_type != 0) {
        break label180;
      }
      if (this.listphotos != null)
      {
        localObject = (GridView)this.listphotos.findViewById(2131624290);
        if (localObject != null) {
          ((GridView)localObject).setSelection(this.preferences.getInt("folder_folder_id", 0));
        }
      }
    }
    label180:
    do
    {
      do
      {
        return;
        if (this.isSelect)
        {
          selected();
          break;
        }
        if (!this.isSearch) {
          break;
        }
        if (this.folder_search_text.getText().toString().equals("")) {}
        for (;;)
        {
          getResults(this.folder_search_text.getText().toString());
          new Timer().schedule(new TimerTask()
          {
            public void run()
            {
              ((InputMethodManager)Activity_FolderFile.this.getSystemService("input_method")).toggleSoftInput(1, 2);
            }
          }, 500L);
          break;
          this.folder_search_text_delete.setVisibility(0);
        }
      } while (this.listphotos == null);
      localObject = (ListView)this.listphotos.findViewById(2131624291);
    } while (localObject == null);
    ((ListView)localObject).setSelection(this.preferences.getInt("folder_folder_id", 0));
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    Log.i("TAG", "111");
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
  
  protected void sdCard(String paramString)
  {
    try
    {
      if (ExistSDCard())
      {
        File localFile = new File(Environment.getExternalStorageDirectory() + "/TopScanner/");
        paramString = new File(Environment.getExternalStorageDirectory() + "/TopScanner/" + paramString + ".TopScanner");
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
  
  public void search()
  {
    this.isSearch = true;
    if (!this.mapp.isPad()) {
      this.folder_more.setVisibility(8);
    }
    for (;;)
    {
      this.folder_imagesearch.setVisibility(8);
      this.folder_search_relative.setVisibility(0);
      this.title.setVisibility(8);
      this.folder_search_text.requestFocus();
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          ((InputMethodManager)Activity_FolderFile.this.getSystemService("input_method")).toggleSoftInput(1, 2);
        }
      }, 200L);
      return;
      this.folder_image_editname.setVisibility(8);
      this.folder_image_importgallery.setVisibility(8);
    }
  }
  
  public void search2()
  {
    this.folder_search_text.setText(this.folder_search_text.getText().toString());
    this.folder_search_relative.setVisibility(0);
    this.folder_search_text.requestFocus();
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 2);
  }
  
  public void selected()
  {
    this.isSelect = true;
    this.title.setVisibility(8);
    this.folder_imagesearch.setVisibility(8);
    if (!this.mapp.isPad()) {
      this.folder_more.setVisibility(8);
    }
    for (;;)
    {
      this.selecttext.setVisibility(0);
      this.selecttext.setText("" + idlist.size());
      this.folder_selecttextview.setVisibility(0);
      this.folder_selectall.setVisibility(0);
      this.camera.setVisibility(4);
      this.folder_onlongclick_layout.setVisibility(0);
      return;
      this.folder_image_editname.setVisibility(8);
      this.folder_image_importgallery.setVisibility(8);
    }
  }
  
  protected void shareDocment()
  {
    Object localObject1;
    if (this.pdf_OR_jpeg == 1)
    {
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      if (this.export_size == 0)
      {
        localObject1 = new MyFilter2();
        i = 0;
        if (i < idlist.size())
        {
          localFile = new File(this.folderPath + "/" + ((Photo_info)idlist.get(i)).getName());
          localObject2 = localFile.listFiles((FilenameFilter)localObject1);
          if ((localObject2 != null) && (localObject2.length > 0)) {
            localObject2 = new File(localFile.getPath() + "/" + localFile.getName() + ".pdf");
          }
          for (;;)
          {
            try
            {
              if ((Util.getFileSize((File)localObject2) == 0L) || (!Util.isOpenPdf(localFile.getPath() + "/" + localFile.getName() + ".pdf")))
              {
                localObject2 = new HashMap();
                ((HashMap)localObject2).put("name", localFile.getName());
                ((HashMap)localObject2).put("isfolder", Boolean.valueOf(false));
                ((HashMap)localObject2).put("path", localFile.getPath());
                this.pdf_file_name.add(localObject2);
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              continue;
            }
            i += 1;
            break;
            localObject2 = new HashMap();
            ((HashMap)localObject2).put("name", localException.getName());
            ((HashMap)localObject2).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject2).put("path", localException.getPath());
            this.pdf_file_name.add(localObject2);
          }
        }
        if (this.pdf_file_name.size() > 0) {
          createPDF();
        }
      }
    }
    while (this.pdf_OR_jpeg != 2)
    {
      do
      {
        File localFile;
        Object localObject2;
        return;
        localObject1 = new Message();
        ((Message)localObject1).what = 3;
        this.handler.sendMessage((Message)localObject1);
        return;
        int i = 0;
        while (i < idlist.size())
        {
          localObject1 = new File(this.folderPath + "/" + ((Photo_info)idlist.get(i)).getName());
          HashMap localHashMap = new HashMap();
          localHashMap.put("isfolder", Boolean.valueOf(false));
          localHashMap.put("name", ((File)localObject1).getName());
          localHashMap.put("path", ((File)localObject1).getPath());
          this.pdf_file_name.add(localHashMap);
          i += 1;
        }
      } while (this.pdf_file_name.size() <= 0);
      createPDF();
      return;
    }
    if (this.export_size == 0)
    {
      localObject1 = new Message();
      ((Message)localObject1).what = 33;
      this.handler.sendMessage((Message)localObject1);
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
          Activity_FolderFile.access$3202(Activity_FolderFile.this, 0);
          if (paramInt == 1)
          {
            Activity_FolderFile.access$3402(Activity_FolderFile.this, 0);
            Activity_FolderFile.access$3802(Activity_FolderFile.this, 0);
          }
          for (;;)
          {
            Activity_FolderFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_FolderFile.access$3402(Activity_FolderFile.this, 2);
              Activity_FolderFile.access$3802(Activity_FolderFile.this, 2);
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
          Activity_FolderFile.access$3202(Activity_FolderFile.this, 1);
          if (paramInt == 1)
          {
            Activity_FolderFile.access$3402(Activity_FolderFile.this, 0);
            Activity_FolderFile.access$3802(Activity_FolderFile.this, 0);
          }
          for (;;)
          {
            Activity_FolderFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_FolderFile.access$3402(Activity_FolderFile.this, 2);
              Activity_FolderFile.access$3802(Activity_FolderFile.this, 2);
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
          Activity_FolderFile.access$3202(Activity_FolderFile.this, 2);
          if (paramInt == 1)
          {
            Activity_FolderFile.access$3402(Activity_FolderFile.this, 0);
            Activity_FolderFile.access$3802(Activity_FolderFile.this, 0);
          }
          for (;;)
          {
            Activity_FolderFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_FolderFile.access$3402(Activity_FolderFile.this, 2);
              Activity_FolderFile.access$3802(Activity_FolderFile.this, 2);
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
        Activity_FolderFile.access$4502(Activity_FolderFile.this, 1);
        Activity_FolderFile.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    localLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_FolderFile.access$4502(Activity_FolderFile.this, 2);
        Activity_FolderFile.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    ((AlertDialog)localObject).show();
  }
  
  @SuppressLint({"InflateParams"})
  public void takePicture(boolean paramBoolean)
  {
    if (3 >= 0) {
      return;
    }
    Intent localIntent;
    if (paramBoolean)
    {
      FlurryAgent.logEvent("4_CAMERA");
      this.editor.putBoolean("where", false);
      this.editor.commit();
      this.mapp.setSavePath(this.folderPath + "/");
      Log.i("TAG", "path===" + this.folderPath + "/");
      if (this.mapp.isPad()) {}
      for (localIntent = new Intent(this.context, Activity_PadCamera.class);; localIntent = new Intent(this.context, Activity_CameraPreview.class))
      {
        startActivityForResult(localIntent, 0);
        return;
      }
    }
    FlurryAgent.logEvent("4_ALBUM");
    if (this.preferences.getBoolean("setting_import_gallery", false))
    {
      localIntent = new Intent("android.intent.action.PICK");
      localIntent.setType("image/*");
      localIntent.setFlags(67108864);
      startActivityForResult(localIntent, 10);
      return;
    }
    this.editor.putBoolean("where", false);
    this.editor.commit();
    this.mapp.setPhotofrom(false);
    this.mapp.setSavePath(this.folderPath + "/");
    startActivity(new Intent(this, LocalAlbumActivity.class));
  }
  
  public void unselected()
  {
    int i;
    if (this.list_type == 0)
    {
      this.madapter.isse = false;
      this.isSelect = false;
      this.islongclick = false;
      int j = mlist2.size();
      i = 0;
      label34:
      if (i >= j) {
        break label95;
      }
      ((Photo_info)mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label85;
      }
      this.madapter.notifyDataSetChanged();
    }
    for (;;)
    {
      i += 1;
      break label34;
      this.madapter2.isse = false;
      break;
      label85:
      this.madapter2.notifyDataSetChanged();
    }
    label95:
    idlist.clear();
    this.title.setVisibility(0);
    this.folder_imagesearch.setVisibility(0);
    if (!this.mapp.isPad()) {
      this.folder_more.setVisibility(0);
    }
    for (;;)
    {
      this.selecttext.setText("");
      this.selecttext.setVisibility(8);
      this.folder_selecttextview.setVisibility(8);
      this.folder_selectall.setText(this.activity_folderFile.getResources().getString(2131165561));
      this.folder_selectall.setVisibility(8);
      this.camera.setVisibility(0);
      this.folder_onlongclick_layout.setVisibility(8);
      return;
      this.folder_image_editname.setVisibility(0);
      this.folder_image_importgallery.setVisibility(0);
    }
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
      int j = mlist2.size();
      i = 0;
      label56:
      if (i >= j) {
        break label138;
      }
      ((Photo_info)mlist2.get(i)).setCheck(false);
      if (this.list_type != 0) {
        break label121;
      }
      if (this.madapter != null) {
        this.madapter.notifyDataSetChanged();
      }
    }
    for (;;)
    {
      i += 1;
      break label56;
      if (this.madapter2 == null) {
        break;
      }
      this.madapter2.isse = false;
      break;
      label121:
      if (this.madapter2 != null) {
        this.madapter2.notifyDataSetChanged();
      }
    }
    label138:
    idlist.clear();
    this.folder_selecttextview.setVisibility(8);
    this.folder_selectall.setText(Activity_Main.activity_Main.getResources().getString(2131165561));
    this.folder_selectall.setVisibility(8);
    this.selecttext.setText("");
    this.selecttext.setVisibility(8);
    this.camera.setVisibility(0);
    this.folder_onlongclick_layout.setVisibility(8);
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
