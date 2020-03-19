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
import com.simpelapp.entity.PhotoDao;
import com.simpleapp.adpter.MoreListAdapter;
import com.simpleapp.adpter.MyPrintDocumentAdapter;
import com.simpleapp.adpter.SelectFile_GridAdapter;
import com.simpleapp.adsUtils.AdsUtils;
import com.simpleapp.db.MyDbHelper;
import com.simpleapp.drawViews.DragGridView2;
import com.simpleapp.drawViews.DragGridView2.OnChanageListener;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Activity_SelectFile
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  static Comparator<String> comparator3 = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.substring(paramAnonymousString1.length() - 7, paramAnonymousString1.length() - 4).compareTo(paramAnonymousString2.substring(paramAnonymousString2.length() - 7, paramAnonymousString2.length() - 4));
    }
  };
  static ArrayList<PhotoDao> idlist;
  static ArrayList<PhotoDao> mlist2;
  private Activity_SelectFile activity_selectfileFile;
  private SelectFile_GridAdapter adapter;
  private ImageView back;
  private ImageView camera;
  Comparator<PhotoDao> comparator = new Comparator()
  {
    public int compare(PhotoDao paramAnonymousPhotoDao1, PhotoDao paramAnonymousPhotoDao2)
    {
      return paramAnonymousPhotoDao1.getPath().substring(paramAnonymousPhotoDao1.getPath().length() - 7, paramAnonymousPhotoDao1.getPath().length() - 4).compareTo(paramAnonymousPhotoDao2.getPath().substring(paramAnonymousPhotoDao2.getPath().length() - 7, paramAnonymousPhotoDao2.getPath().length() - 4));
    }
  };
  private String compressJpeg_Path;
  private Context context;
  private SQLiteDatabase db;
  private String documentPath;
  private SharedPreferences.Editor editor;
  private List<File> export_file;
  private int export_size = 0;
  private String folder_root_path;
  private DragGridView2 grid;
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
      label573:
      Object localObject4;
      label732:
      label1779:
      label1937:
      label1978:
      label2080:
      label2106:
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                    Activity_SelectFile.this.selectfile_progreebar.setVisibility(8);
                    Activity_SelectFile.this.relist();
                    return;
                    Activity_SelectFile.this.showToast(Activity_SelectFile.this.getResources().getString(2131165767));
                    return;
                    Activity_SelectFile.this.selectfile_progreebar.setVisibility(8);
                    Activity_SelectFile.this.mapp.setUpdate(false);
                    Activity_SelectFile.this.finish();
                    return;
                    Activity_SelectFile.this.selectfile_progreebar.setVisibility(8);
                    Activity_SelectFile.this.mapp.setUpdate(false);
                    Activity_SelectFile.this.finish();
                    return;
                    ((InputMethodManager)Activity_SelectFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                    return;
                    Activity_SelectFile.this.unselected();
                    return;
                    Activity_SelectFile.this.unselected();
                    Activity_SelectFile.this.relist();
                    return;
                    new AlertDialog.Builder(Activity_SelectFile.this.context).setTitle(Activity_SelectFile.this.getResources().getString(2131165605)).setMessage(Activity_SelectFile.this.getResources().getString(2131165298)).setPositiveButton(Activity_SelectFile.this.getResources().getString(2131165458), null).create().show();
                    return;
                    Activity_SelectFile.this.selectfilefile_progreebar.setVisibility(8);
                    Activity_SelectFile.access$2402(Activity_SelectFile.this, null);
                    Activity_SelectFile.this.mapp.setUpdate(false);
                    localObject1 = new ArrayList();
                    if (Activity_SelectFile.this.islongclick) {
                      break label732;
                    }
                    if (Activity_SelectFile.this.export_size != 0) {
                      break;
                    }
                    paramAnonymousMessage = new File(Activity_SelectFile.this.preferences.getString("folder_path", ""));
                    paramAnonymousMessage = paramAnonymousMessage.listFiles(new Activity_SelectFile.MyFilter(Activity_SelectFile.this, ".pdf"));
                  } while ((paramAnonymousMessage == null) || (paramAnonymousMessage.length <= 0));
                  ((ArrayList)localObject1).add(Uri.fromFile(paramAnonymousMessage[0]));
                  Activity_SelectFile.this.getPackageManager().getInstalledApplications(0).size();
                  localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                  ((Intent)localObject2).setType("application/pdf");
                  switch (Activity_SelectFile.this.select_pdf_posiotion)
                  {
                  case 4: 
                  default: 
                    return;
                  case 0: 
                    paramAnonymousMessage = new Intent("android.intent.action.SEND");
                    paramAnonymousMessage.setType("application/pdf");
                    localObject2 = new ArrayList();
                    paramAnonymousMessage = Activity_SelectFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
                  }
                } while (paramAnonymousMessage.isEmpty());
                localObject3 = paramAnonymousMessage.iterator();
                if (((Iterator)localObject3).hasNext())
                {
                  localObject4 = (ResolveInfo)((Iterator)localObject3).next();
                  if (((ArrayList)localObject1).size() > 1) {}
                  for (paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");; paramAnonymousMessage = new Intent("android.intent.action.SEND"))
                  {
                    paramAnonymousMessage.setType("application/pdf");
                    paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("folder_name", ""));
                    if (((ArrayList)localObject1).size() > 0) {
                      paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
                    }
                    paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
                    paramAnonymousMessage.setClassName(((ResolveInfo)localObject4).activityInfo.packageName, ((ResolveInfo)localObject4).activityInfo.name);
                    ((List)localObject2).add(paramAnonymousMessage);
                    break label573;
                    paramAnonymousMessage = new File(Activity_SelectFile.this.root_Path2);
                    break;
                    paramAnonymousMessage = new File(Activity_SelectFile.this.temporary_jpgtoPdf);
                    break;
                  }
                }
                if (((List)localObject2).size() > 0)
                {
                  paramAnonymousMessage = Intent.createChooser((Intent)((List)localObject2).remove(0), "Share PDF file");
                  paramAnonymousMessage.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                  Activity_SelectFile.this.startActivity(paramAnonymousMessage);
                  return;
                }
                Toast.makeText(Activity_SelectFile.this.activity_selectfileFile, "Error: Cannot open or share created PDF report.", 0).show();
                return;
                Activity_SelectFile.this.mapp.setPdf_path(((Uri)((ArrayList)localObject1).get(0)).getPath());
                Activity_SelectFile.this.mapp.setPdf_Name(paramAnonymousMessage[0].getName().replace(".pdf", ""));
                Activity_SelectFile.this.mapp.setPdf_pages(Util.getPdfPages(((Uri)((ArrayList)localObject1).get(0)).getPath()));
                try
                {
                  this.printManager = ((PrintManager)Activity_SelectFile.this.activity_selectfileFile.getSystemService("print"));
                  this.printManager.print(Activity_SelectFile.this.mapp.getPdf_Name(), new MyPrintDocumentAdapter(Activity_SelectFile.this.mapp), null);
                  return;
                }
                catch (Exception paramAnonymousMessage)
                {
                  Toast.makeText(Activity_SelectFile.this.activity_selectfileFile, "printing error.", 0).show();
                  return;
                }
                paramAnonymousMessage = new ArrayList();
                localObject2 = Activity_SelectFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
              } while (((List)localObject2).isEmpty());
              localObject2 = ((List)localObject2).iterator();
              if (((Iterator)localObject2).hasNext())
              {
                localObject3 = (ResolveInfo)((Iterator)localObject2).next();
                localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
                ((Intent)localObject4).setType("application/pdf");
                if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_to", ""))) {
                  ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_SelectFile.this.preferences.getString("emailsetting_to", "") });
                }
                if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_subject", ""))) {
                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("emailsetting_subject", ""));
                }
                for (;;)
                {
                  if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_body", ""))) {
                    ((Intent)localObject4).putExtra("android.intent.extra.TEXT", Activity_SelectFile.this.preferences.getString("emailsetting_body", ""));
                  }
                  if ((!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                    break;
                  }
                  ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                  ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                  paramAnonymousMessage.add(localObject4);
                  break;
                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("folder_name", ""));
                }
              }
              if (paramAnonymousMessage.size() > 0)
              {
                localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Email");
                ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
                Activity_SelectFile.this.startActivityForResult((Intent)localObject1, 3);
                return;
              }
              Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165275), 0).show();
              return;
              paramAnonymousMessage = new Intent("android.intent.action.VIEW");
              paramAnonymousMessage.setDataAndType((Uri)((ArrayList)localObject1).get(0), "application/pdf");
              paramAnonymousMessage.setFlags(67108864);
              Activity_SelectFile.this.startActivityForResult(Intent.createChooser(paramAnonymousMessage, "Export"), 3);
              return;
            } while (Utils.findAndGotoApp1(Activity_SelectFile.this.activity_selectfileFile, "com.studio.topfax", (ArrayList)localObject1));
            Utils.showGooglePlaysimplefax(Activity_SelectFile.this.activity_selectfileFile);
            return;
            Activity_SelectFile.this.selectfilefile_progreebar.setVisibility(8);
            Activity_SelectFile.access$2402(Activity_SelectFile.this, null);
            localObject1 = new ArrayList();
            if (Activity_SelectFile.this.export_size != 0) {
              break;
            }
            paramAnonymousMessage = new File(Activity_SelectFile.this.preferences.getString("folder_path", ""));
            paramAnonymousMessage = paramAnonymousMessage.listFiles(new Activity_SelectFile.MyFilter(Activity_SelectFile.this, ".jpg"));
          } while ((paramAnonymousMessage == null) || (paramAnonymousMessage.length <= 0));
          int i = 0;
          for (;;)
          {
            if (i >= paramAnonymousMessage.length) {
              break label1779;
            }
            if (Activity_SelectFile.this.islongclick)
            {
              int j = 0;
              while (j < Activity_SelectFile.idlist.size())
              {
                if (((PhotoDao)Activity_SelectFile.idlist.get(j)).getPath().equals(paramAnonymousMessage[i].getPath())) {
                  ((ArrayList)localObject1).add(Uri.fromFile(paramAnonymousMessage[i]));
                }
                j += 1;
              }
              paramAnonymousMessage = new File(Activity_SelectFile.this.compressJpeg_Path);
              break;
            }
            ((ArrayList)localObject1).add(Uri.fromFile(paramAnonymousMessage[i]));
            i += 1;
          }
          Activity_SelectFile.this.getPackageManager().getInstalledApplications(0).size();
          localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
          ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "TopScanner");
          ((Intent)localObject2).setType("image/jpeg");
          switch (Activity_SelectFile.this.select_jpeg_posiotion)
          {
          case 1: 
          default: 
            return;
          case 0: 
            if (((ArrayList)localObject1).size() <= 1) {
              break label2080;
            }
            paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
            paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", "TopScanner");
            paramAnonymousMessage.setType("image/jpeg");
            localObject2 = new ArrayList();
            paramAnonymousMessage = Activity_SelectFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
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
            paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("folder_name", ""));
            if (((ArrayList)localObject1).size() != 1) {
              break label2106;
            }
            paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
          }
          for (;;)
          {
            paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
            paramAnonymousMessage.setClassName(((ResolveInfo)localObject4).activityInfo.packageName, ((ResolveInfo)localObject4).activityInfo.name);
            ((List)localObject2).add(paramAnonymousMessage);
            break label1937;
            paramAnonymousMessage = new Intent("android.intent.action.SEND");
            break;
            paramAnonymousMessage = new Intent("android.intent.action.SEND");
            break label1978;
            paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
          }
        }
        if (((List)localObject2).size() > 0)
        {
          paramAnonymousMessage = Intent.createChooser((Intent)((List)localObject2).remove(0), "Share JPEG file");
          paramAnonymousMessage.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
          Activity_SelectFile.this.startActivity(paramAnonymousMessage);
          return;
        }
        Toast.makeText(Activity_SelectFile.this.activity_selectfileFile, "Error: No resource images found.", 0).show();
        return;
        paramAnonymousMessage = new ArrayList();
        localObject2 = Activity_SelectFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
      } while (((List)localObject2).isEmpty());
      Object localObject2 = ((List)localObject2).iterator();
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ResolveInfo)((Iterator)localObject2).next();
        localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
        ((Intent)localObject4).setType("image/jpeg");
        if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_to", ""))) {
          ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_SelectFile.this.preferences.getString("emailsetting_to", "") });
        }
        if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_subject", ""))) {
          ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("emailsetting_subject", ""));
        }
        for (;;)
        {
          if (!"".equals(Activity_SelectFile.this.preferences.getString("emailsetting_body", ""))) {
            ((Intent)localObject4).putExtra("android.intent.extra.TEXT", Activity_SelectFile.this.preferences.getString("emailsetting_body", ""));
          }
          if ((!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook"))) {
            break;
          }
          ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
          ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
          paramAnonymousMessage.add(localObject4);
          break;
          ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", Activity_SelectFile.this.preferences.getString("folder_name", ""));
        }
      }
      if (paramAnonymousMessage.size() > 0)
      {
        localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
        ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
        Activity_SelectFile.this.startActivityForResult((Intent)localObject1, 3);
        return;
      }
      Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165275), 0).show();
    }
  };
  private boolean hasFolder = false;
  private IAPBuy iapBuy;
  private LayoutInflater inflater;
  private boolean isMoveImageSort = false;
  private boolean isSelect = false;
  private boolean islongclick = false;
  private MyDbHelper mDbHelper;
  private Thread mThread;
  private MyApplication mapp;
  private ArrayList<MainListDao> moreList = new ArrayList();
  View.OnClickListener myOnClickListener = new View.OnClickListener()
  {
    @SuppressLint({"InflateParams"})
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131624232: 
      case 2131624233: 
      case 2131624234: 
      case 2131624235: 
      case 2131624236: 
      case 2131624237: 
      case 2131624242: 
      default: 
        return;
      case 2131624238: 
        Activity_SelectFile.this.showPdf_or_imagejpeg_SelectDailog(1);
        return;
      case 2131624239: 
        Activity_SelectFile.this.showPdf_or_imagejpeg_SelectDailog(2);
        return;
      case 2131624240: 
        Activity_SelectFile.this.deleteDocment();
        return;
      case 2131624241: 
        Activity_SelectFile.this.initPopuptWindow2(2);
        if (Activity_SelectFile.this.mapp.isPad())
        {
          Activity_SelectFile.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
          return;
        }
        if (Activity_SelectFile.this.selectfile_relativelayout_ads.getVisibility() == 0)
        {
          Activity_SelectFile.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, Activity_SelectFile.this.dip2px(56.0F));
          return;
        }
        Activity_SelectFile.this.popupWindow.showAtLocation(paramAnonymousView, 85, 0, 0);
        return;
      case 2131624243: 
        Activity_SelectFile.this.takePicture(false);
        return;
      case 2131624244: 
        Activity_SelectFile.this.Rename();
        return;
      }
      FlurryAgent.logEvent("6_simplefax");
      Activity_SelectFile.access$2002(Activity_SelectFile.this, 1);
      Activity_SelectFile.access$2102(Activity_SelectFile.this, 5);
      Activity_SelectFile.this.shareDocment();
    }
  };
  private String oldname;
  private int pdf_OR_jpeg = 1;
  private PopupWindow popupWindow;
  private SharedPreferences preferences;
  private String root_Path2;
  private int select_jpeg_posiotion = 0;
  private int select_pdf_posiotion = 0;
  private int selecter_docmentCount = 0;
  private TextView selectfile_counttext;
  private LinearLayout selectfile_deletelayout;
  private ImageView selectfile_editname;
  private LinearLayout selectfile_emaillayout;
  private ImageView selectfile_fax;
  private LinearLayout selectfile_faxlayout;
  private TextView selectfile_filelength;
  private ImageView selectfile_importgallery;
  private LinearLayout selectfile_morelayout;
  private LinearLayout selectfile_onlongclick_layout;
  private ProgressBar selectfile_progreebar;
  private RelativeLayout selectfile_relativelayout_ads;
  private RadioButton selectfile_selectall;
  private TextView selectfile_selecttextview;
  private ImageView selectfile_share;
  private LinearLayout selectfile_sharelayout;
  private ImageView selectfile_title_more;
  private ProgressBar selectfilefile_progreebar;
  private String temporary_jpgtoPdf;
  private TextView title;
  
  public Activity_SelectFile() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void Rename()
  {
    if (this.isMoveImageSort)
    {
      this.isMoveImageSort = false;
      saveMoveImageSort();
    }
    final View localView = this.inflater.inflate(2130903122, null);
    EditText localEditText = (EditText)localView.findViewById(2131624336);
    localEditText.setSelectAllOnFocus(true);
    localEditText.setText(this.title.getText().toString());
    new AlertDialog.Builder(this.context).setTitle(getString(2131165537)).setView(localView).setPositiveButton(getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        EditText localEditText = (EditText)localView.findViewById(2131624336);
        Utils.closeKeyBoard(Activity_SelectFile.this.activity_selectfileFile, localEditText);
        Object localObject2 = new File(Activity_SelectFile.this.documentPath);
        if (localEditText.getText().toString().equals(((File)localObject2).getName())) {
          return;
        }
        if (localEditText.getText().toString().equals(""))
        {
          Activity_SelectFile.this.showToast(Activity_SelectFile.this.getResources().getString(2131165333));
          return;
        }
        paramAnonymousDialogInterface = localEditText.getText().toString();
        Object localObject1 = localEditText.getText().toString();
        if (Activity_SelectFile.this.checkFilename(paramAnonymousDialogInterface))
        {
          Activity_SelectFile.this.showToast(Activity_SelectFile.this.getResources().getString(2131165332));
          return;
        }
        if (Activity_SelectFile.this.checkName(paramAnonymousDialogInterface)) {}
        for (;;)
        {
          localObject1 = new File(Activity_SelectFile.this.folder_root_path + paramAnonymousDialogInterface);
          if (((File)localObject2).exists()) {
            ((File)localObject2).renameTo((File)localObject1);
          }
          Activity_SelectFile.this.editor.putString("folder_path", ((File)localObject1).getPath());
          Activity_SelectFile.this.editor.putString("folder_name", paramAnonymousDialogInterface);
          Activity_SelectFile.this.editor.commit();
          Activity_SelectFile.access$902(Activity_SelectFile.this, ((File)localObject1).getPath());
          localObject1 = Activity_SelectFile.mlist2.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (PhotoDao)((Iterator)localObject1).next();
            if (((PhotoDao)localObject2).getPath() != null)
            {
              localObject2 = ((PhotoDao)localObject2).getPath();
              localObject3 = Activity_SelectFile.this.mapp.getBitmapFromMemCache("main" + (String)localObject2);
              Activity_SelectFile.this.mapp.getmMemoryCache().remove("main" + (String)localObject2);
              paramAnonymousInt = ((String)localObject2).lastIndexOf("/", ((String)localObject2).lastIndexOf("/") - 1);
              Activity_SelectFile.this.mapp.addBitmapToMemoryCache("main" + Activity_SelectFile.this.folder_root_path + paramAnonymousDialogInterface + "/" + ((String)localObject2).substring(paramAnonymousInt + 1, ((String)localObject2).length()), (BitmapDrawable)localObject3);
            }
          }
          Object localObject3 = new Timestamp(System.currentTimeMillis());
          localObject3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject3);
          localObject1 = ((String)localObject1).replaceAll("([*/\\\\\":?|<>])", "-") + "-" + (String)localObject3;
          Activity_SelectFile.this.saveNameToDb((String)localObject1, paramAnonymousDialogInterface);
          paramAnonymousDialogInterface = (DialogInterface)localObject1;
        }
        Activity_SelectFile.this.relist();
        Activity_SelectFile.this.title.setText(localEditText.getText().toString());
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
  }
  
  private void buy()
  {
    startActivity(new Intent(this.context, SupportUs_Activity.class));
  }
  
  private int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int findId(PhotoDao paramPhotoDao)
  {
    int i = 0;
    while (i < mlist2.size())
    {
      PhotoDao localPhotoDao = (PhotoDao)mlist2.get(i);
      if ((localPhotoDao != null) && (localPhotoDao.getPath().equals(paramPhotoDao.getPath()))) {
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
  private void initPopuptWindow2(int paramInt)
  {
    Object localObject1 = new ArrayList();
    if (this.mapp.isPad())
    {
      ((ArrayList)localObject1).clear();
      if (paramInt == 1)
      {
        if (Build.VERSION.SDK_INT >= 19) {
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165520));
        }
        ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165464));
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
      if (paramInt == 2)
      {
        ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165553));
        ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165665));
        if (Build.VERSION.SDK_INT >= 19) {
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165520));
        }
        ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165464));
        continue;
        ((ArrayList)localObject1).clear();
        if (paramInt == 1)
        {
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165377));
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165537));
          if (Build.VERSION.SDK_INT >= 19) {
            ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165520));
          }
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165464));
        }
        else if (paramInt == 2)
        {
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165553));
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165665));
          if (Build.VERSION.SDK_INT >= 19) {
            ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165520));
          }
          ((ArrayList)localObject1).add(this.activity_selectfileFile.getResources().getString(2131165464));
        }
      }
    }
    if (this.popupWindow != null)
    {
      this.popupWindow.dismiss();
      this.popupWindow = null;
    }
    localObject1 = this.activity_selectfileFile.getLayoutInflater().inflate(2130903104, null, false);
    this.popupWindow = new PopupWindow(this.activity_selectfileFile);
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
          if ((Activity_SelectFile.this.popupWindow != null) && (Activity_SelectFile.this.popupWindow.isShowing())) {
            Activity_SelectFile.this.popupWindow.dismiss();
          }
          Activity_SelectFile.access$702(Activity_SelectFile.this, null);
          return false;
        }
      });
      localObject2 = (ListView)((View)localObject1).findViewById(2131624306);
      ((ListView)localObject2).setAdapter(new MoreListAdapter(this.activity_selectfileFile, this.moreList));
      ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if ((Activity_SelectFile.this.popupWindow != null) && (Activity_SelectFile.this.popupWindow.isShowing())) {
            Activity_SelectFile.this.popupWindow.dismiss();
          }
          Activity_SelectFile.access$702(Activity_SelectFile.this, null);
          if (((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165464)))
          {
            Activity_SelectFile.access$2002(Activity_SelectFile.this, 1);
            Activity_SelectFile.access$2102(Activity_SelectFile.this, 3);
            Activity_SelectFile.this.shareDocment();
          }
          do
          {
            return;
            if (((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165520)))
            {
              Activity_SelectFile.access$2002(Activity_SelectFile.this, 1);
              Activity_SelectFile.access$2102(Activity_SelectFile.this, 1);
              Activity_SelectFile.this.shareDocment();
              return;
            }
            if (((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165377)))
            {
              Activity_SelectFile.this.takePicture(false);
              return;
            }
            if (((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165537)))
            {
              Activity_SelectFile.this.Rename();
              return;
            }
            if (((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165665)))
            {
              FlurryAgent.logEvent("6_simplefax");
              Activity_SelectFile.access$2002(Activity_SelectFile.this, 1);
              Activity_SelectFile.access$2102(Activity_SelectFile.this, 5);
              Activity_SelectFile.this.shareDocment();
              return;
            }
          } while (!((MainListDao)Activity_SelectFile.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165553)));
          new Thread(new Runnable()
          {
            public void run()
            {
              int i = 0;
              for (;;)
              {
                if (i < Activity_SelectFile.idlist.size())
                {
                  File localFile1 = new File(((PhotoDao)Activity_SelectFile.idlist.get(i)).getPath());
                  File localFile2 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TopScanner");
                  if (!localFile2.exists()) {
                    localFile2.mkdirs();
                  }
                  localFile2 = new File(localFile2.getPath() + "/" + Activity_SelectFile.this.title.getText().toString() + "_" + i + ".jpg");
                  if ((localFile2 != null) && (localFile2.exists())) {
                    localFile2.delete();
                  }
                  try
                  {
                    Activity_SelectFile.this.copy(localFile1, localFile2);
                    MediaScannerConnection.scanFile(Activity_SelectFile.this.getApplicationContext(), new String[] { localFile2.getAbsolutePath() }, null, null);
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
              Activity_SelectFile.this.handler.sendMessage(localMessage);
            }
          }).start();
        }
      });
      ((LinearLayout)((View)localObject1).findViewById(2131624305)).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4) && (Activity_SelectFile.this.popupWindow != null) && (Activity_SelectFile.this.popupWindow.isShowing())) {
            Activity_SelectFile.this.popupWindow.dismiss();
          }
          Activity_SelectFile.access$702(Activity_SelectFile.this, null);
          return false;
        }
      });
      return;
      this.popupWindow.setWidth(paramInt * 2 / 7);
      continue;
      this.popupWindow.setWidth(paramInt * 7 / 10);
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
  
  private void relist()
  {
    init();
    listByGrid();
  }
  
  private void saveMoveImageSort()
  {
    int i = 0;
    Object localObject;
    while (i < mlist2.size())
    {
      localObject = new File(((PhotoDao)mlist2.get(i)).getPath());
      this.mapp.getmMemoryCache().remove("main" + ((PhotoDao)mlist2.get(i)).getPath());
      ((File)localObject).renameTo(new File(((PhotoDao)mlist2.get(i)).getPath() + ".temp"));
      i += 1;
    }
    i = 0;
    if (i < mlist2.size())
    {
      File localFile = new File(((PhotoDao)mlist2.get(i)).getPath() + ".temp");
      if (i < 10) {
        localObject = "00" + i;
      }
      for (;;)
      {
        localObject = localFile.getName().substring(0, 15) + (String)localObject + ".jpg";
        localFile.renameTo(new File(this.documentPath + "/" + (String)localObject));
        i += 1;
        break;
        if (i < 100) {
          localObject = "0" + i;
        } else {
          localObject = "" + i;
        }
      }
    }
    relist();
  }
  
  private void saveNameToDb(String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("realname", paramString1);
    localContentValues.put("showname", paramString2);
    this.db.insert("NameMaps", "id", localContentValues);
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
    this.selectfilefile_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      public void run()
      {
        File localFile = new File(Activity_SelectFile.this.preferences.getString("folder_path", ""));
        Object localObject1 = localFile.list();
        if (Activity_SelectFile.this.islongclick)
        {
          localObject2 = new String[Activity_SelectFile.idlist.size()];
          i = 0;
          for (;;)
          {
            localObject1 = localObject2;
            if (i >= Activity_SelectFile.idlist.size()) {
              break;
            }
            localObject2[i] = ((PhotoDao)Activity_SelectFile.idlist.get(i)).getPath().substring(((PhotoDao)Activity_SelectFile.idlist.get(i)).getPath().lastIndexOf("/") + 1, ((PhotoDao)Activity_SelectFile.idlist.get(i)).getPath().length());
            i += 1;
          }
        }
        Object localObject2 = new ArrayList();
        if (localObject1 != null)
        {
          i = 0;
          while (i < localObject1.length)
          {
            if (localObject1[i].matches("[0-9]{18}.jpg")) {
              ((List)localObject2).add(localObject1[i]);
            }
            i += 1;
          }
        }
        Collections.sort((List)localObject2, Activity_SelectFile.comparator3);
        int j = ((List)localObject2).size();
        int i = 0;
        for (;;)
        {
          if (i < j)
          {
            BufferedOutputStream localBufferedOutputStream;
            try
            {
              localObject1 = BitmapFactory.decodeStream(new FileInputStream(new File(localFile.getPath() + "/" + (String)((List)localObject2).get(i))));
              localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Activity_SelectFile.this.compressJpeg_Path + (String)((List)localObject2).get(i)));
              if (Activity_SelectFile.this.export_size == 0) {
                ((Bitmap)localObject1).compress(Bitmap.CompressFormat.JPEG, 100, localBufferedOutputStream);
              } else if (Activity_SelectFile.this.export_size == 1) {
                ((Bitmap)localObject1).compress(Bitmap.CompressFormat.JPEG, 50, localBufferedOutputStream);
              }
            }
            catch (IOException localIOException)
            {
              System.err.println(localIOException.getMessage());
            }
            if (Activity_SelectFile.this.export_size == 2) {
              localIOException.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream);
            }
          }
          else
          {
            Message localMessage = new Message();
            localMessage.what = 33;
            Activity_SelectFile.this.handler.sendMessage(localMessage);
            return;
          }
          i += 1;
        }
      }
    });
    this.mThread.start();
  }
  
  public boolean checkFilename(String paramString)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    Object localObject = new File(this.documentPath);
    bool2 = bool3;
    try
    {
      localObject = ((File)localObject).listFiles();
      bool2 = bool3;
      int j = localObject.length;
      int i = 0;
      for (;;)
      {
        bool2 = bool1;
        if (i >= j) {
          break;
        }
        bool2 = bool1;
        bool3 = getShowName(localObject[i].getName()).equals(paramString);
        if (bool3) {
          bool1 = true;
        }
        i += 1;
      }
      return bool2;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
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
  
  public void createPDF()
  {
    if (this.isMoveImageSort)
    {
      this.isMoveImageSort = false;
      saveMoveImageSort();
    }
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
    this.selectfilefile_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      private PdfWriter writer;
      
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 31	java/io/File
        //   3: dup
        //   4: aload_0
        //   5: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   8: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   11: ldc 37
        //   13: ldc 39
        //   15: invokeinterface 45 3 0
        //   20: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   23: astore 6
        //   25: aload_0
        //   26: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   29: invokestatic 52	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   32: ifne +222 -> 254
        //   35: aload 6
        //   37: new 54	com/simpleapp/tinyscanfree/Activity_SelectFile$MyFilter
        //   40: dup
        //   41: aload_0
        //   42: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   45: ldc 56
        //   47: invokespecial 59	com/simpleapp/tinyscanfree/Activity_SelectFile$MyFilter:<init>	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;Ljava/lang/String;)V
        //   50: invokevirtual 63	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   53: astore_3
        //   54: aload_3
        //   55: ifnull +15 -> 70
        //   58: aload_3
        //   59: arraylength
        //   60: ifle +10 -> 70
        //   63: aload_3
        //   64: iconst_0
        //   65: aaload
        //   66: invokevirtual 67	java/io/File:delete	()Z
        //   69: pop
        //   70: new 31	java/io/File
        //   73: dup
        //   74: new 69	java/lang/StringBuilder
        //   77: dup
        //   78: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   81: aload_0
        //   82: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   85: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   88: ldc 37
        //   90: ldc 39
        //   92: invokeinterface 45 3 0
        //   97: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: ldc 76
        //   102: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload_0
        //   106: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   109: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   112: ldc 37
        //   114: ldc 39
        //   116: invokeinterface 45 3 0
        //   121: aload_0
        //   122: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   125: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   128: ldc 37
        //   130: ldc 39
        //   132: invokeinterface 45 3 0
        //   137: ldc 76
        //   139: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   142: iconst_1
        //   143: iadd
        //   144: aload_0
        //   145: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   148: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   151: ldc 37
        //   153: ldc 39
        //   155: invokeinterface 45 3 0
        //   160: invokevirtual 86	java/lang/String:length	()I
        //   163: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   166: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: ldc 56
        //   171: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   177: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   180: astore_3
        //   181: aload_3
        //   182: invokevirtual 97	java/io/File:exists	()Z
        //   185: ifeq +8 -> 193
        //   188: aload_3
        //   189: invokevirtual 67	java/io/File:delete	()Z
        //   192: pop
        //   193: aload 6
        //   195: invokevirtual 101	java/io/File:list	()[Ljava/lang/String;
        //   198: astore 4
        //   200: new 103	java/util/ArrayList
        //   203: dup
        //   204: invokespecial 104	java/util/ArrayList:<init>	()V
        //   207: astore 7
        //   209: aload 4
        //   211: ifnull +143 -> 354
        //   214: iconst_0
        //   215: istore_1
        //   216: iload_1
        //   217: aload 4
        //   219: arraylength
        //   220: if_icmpge +134 -> 354
        //   223: aload 4
        //   225: iload_1
        //   226: aaload
        //   227: ldc 106
        //   229: invokevirtual 110	java/lang/String:matches	(Ljava/lang/String;)Z
        //   232: ifeq +15 -> 247
        //   235: aload 7
        //   237: aload 4
        //   239: iload_1
        //   240: aaload
        //   241: invokeinterface 116 2 0
        //   246: pop
        //   247: iload_1
        //   248: iconst_1
        //   249: iadd
        //   250: istore_1
        //   251: goto -35 -> 216
        //   254: new 31	java/io/File
        //   257: dup
        //   258: new 69	java/lang/StringBuilder
        //   261: dup
        //   262: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   265: aload_0
        //   266: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   269: invokestatic 120	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   272: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: aload_0
        //   276: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   279: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   282: ldc 37
        //   284: ldc 39
        //   286: invokeinterface 45 3 0
        //   291: aload_0
        //   292: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   295: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   298: ldc 37
        //   300: ldc 39
        //   302: invokeinterface 45 3 0
        //   307: ldc 76
        //   309: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   312: iconst_1
        //   313: iadd
        //   314: aload_0
        //   315: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   318: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   321: ldc 37
        //   323: ldc 39
        //   325: invokeinterface 45 3 0
        //   330: invokevirtual 86	java/lang/String:length	()I
        //   333: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   336: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc 56
        //   341: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   347: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   350: astore_3
        //   351: goto -170 -> 181
        //   354: aload 7
        //   356: getstatic 124	com/simpleapp/tinyscanfree/Activity_SelectFile:comparator3	Ljava/util/Comparator;
        //   359: invokestatic 130	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   362: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   365: astore 4
        //   367: new 138	com/itextpdf/text/Document
        //   370: dup
        //   371: invokespecial 139	com/itextpdf/text/Document:<init>	()V
        //   374: astore 8
        //   376: aconst_null
        //   377: astore 5
        //   379: aconst_null
        //   380: astore 4
        //   382: new 141	com/itextpdf/text/pdf/PdfCopy
        //   385: dup
        //   386: aload 8
        //   388: new 143	java/io/FileOutputStream
        //   391: dup
        //   392: aload_3
        //   393: invokespecial 146	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   396: invokespecial 149	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   399: astore_3
        //   400: aload 8
        //   402: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   405: aload_3
        //   406: astore 4
        //   408: aload 7
        //   410: invokeinterface 155 1 0
        //   415: istore_2
        //   416: iconst_0
        //   417: istore_1
        //   418: iload_1
        //   419: iload_2
        //   420: if_icmpge +742 -> 1162
        //   423: aload 7
        //   425: iload_1
        //   426: invokeinterface 159 2 0
        //   431: checkcast 78	java/lang/String
        //   434: bipush 14
        //   436: bipush 15
        //   438: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   441: invokestatic 164	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   444: tableswitch	default:+40->484, 0:+473->917, 1:+480->924, 2:+487->931, 3:+494->938, 4:+501->945, 5:+508->952
        //   484: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   487: astore_3
        //   488: new 138	com/itextpdf/text/Document
        //   491: dup
        //   492: aload_3
        //   493: invokespecial 167	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   496: astore 5
        //   498: aload_0
        //   499: aload 5
        //   501: new 143	java/io/FileOutputStream
        //   504: dup
        //   505: new 69	java/lang/StringBuilder
        //   508: dup
        //   509: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   512: aload_0
        //   513: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   516: invokestatic 120	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   519: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: aload 7
        //   524: iload_1
        //   525: invokeinterface 159 2 0
        //   530: checkcast 78	java/lang/String
        //   533: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   536: ldc 56
        //   538: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   541: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   544: invokespecial 168	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   547: invokestatic 174	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   550: putfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   553: aload 5
        //   555: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   558: aconst_null
        //   559: astore_3
        //   560: new 178	java/io/FileInputStream
        //   563: dup
        //   564: new 31	java/io/File
        //   567: dup
        //   568: new 69	java/lang/StringBuilder
        //   571: dup
        //   572: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   575: aload 6
        //   577: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   580: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: ldc 76
        //   585: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   588: aload 7
        //   590: iload_1
        //   591: invokeinterface 159 2 0
        //   596: checkcast 78	java/lang/String
        //   599: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   602: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   605: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   608: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   611: invokestatic 188	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   614: astore 9
        //   616: new 190	java/io/ByteArrayOutputStream
        //   619: dup
        //   620: invokespecial 191	java/io/ByteArrayOutputStream:<init>	()V
        //   623: astore 10
        //   625: aload_0
        //   626: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   629: invokestatic 52	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   632: ifne +337 -> 969
        //   635: new 69	java/lang/StringBuilder
        //   638: dup
        //   639: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   642: aload 6
        //   644: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   647: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   650: ldc 76
        //   652: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   655: aload 7
        //   657: iload_1
        //   658: invokeinterface 159 2 0
        //   663: checkcast 78	java/lang/String
        //   666: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   672: invokestatic 196	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   675: astore_3
        //   676: aload_3
        //   677: invokevirtual 200	com/itextpdf/text/Image:getWidth	()F
        //   680: aload 5
        //   682: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   685: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   688: fcmpl
        //   689: ifge +19 -> 708
        //   692: aload_3
        //   693: invokevirtual 210	com/itextpdf/text/Image:getHeight	()F
        //   696: aload 5
        //   698: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   701: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   704: fcmpl
        //   705: iflt +12 -> 717
        //   708: aload_3
        //   709: aload 5
        //   711: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   714: invokevirtual 214	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   717: aload_3
        //   718: aload 5
        //   720: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   723: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   726: aload_3
        //   727: invokevirtual 217	com/itextpdf/text/Image:getScaledWidth	()F
        //   730: fsub
        //   731: fconst_2
        //   732: fdiv
        //   733: aload 5
        //   735: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   738: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   741: aload_3
        //   742: invokevirtual 220	com/itextpdf/text/Image:getScaledHeight	()F
        //   745: fsub
        //   746: fconst_2
        //   747: fdiv
        //   748: invokevirtual 224	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   751: aload 5
        //   753: aload_3
        //   754: invokevirtual 227	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   757: pop
        //   758: aload_0
        //   759: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   762: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   765: ifeq +18 -> 783
        //   768: aload_0
        //   769: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   772: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   775: ifne +8 -> 783
        //   778: aload 5
        //   780: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   783: new 238	com/itextpdf/text/pdf/PdfReader
        //   786: dup
        //   787: new 69	java/lang/StringBuilder
        //   790: dup
        //   791: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   794: aload_0
        //   795: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   798: invokestatic 120	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   801: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   804: aload 7
        //   806: iload_1
        //   807: invokeinterface 159 2 0
        //   812: checkcast 78	java/lang/String
        //   815: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   818: ldc 56
        //   820: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   823: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   826: invokespecial 239	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   829: astore_3
        //   830: aload 4
        //   832: aload_3
        //   833: invokevirtual 243	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   836: aload_3
        //   837: invokevirtual 244	com/itextpdf/text/pdf/PdfReader:close	()V
        //   840: new 31	java/io/File
        //   843: dup
        //   844: new 69	java/lang/StringBuilder
        //   847: dup
        //   848: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   851: aload_0
        //   852: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   855: invokestatic 120	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   858: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   861: aload 7
        //   863: iload_1
        //   864: invokeinterface 159 2 0
        //   869: checkcast 78	java/lang/String
        //   872: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   875: ldc 56
        //   877: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   880: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   883: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   886: invokevirtual 67	java/io/File:delete	()Z
        //   889: pop
        //   890: iload_1
        //   891: iconst_1
        //   892: iadd
        //   893: istore_1
        //   894: goto -476 -> 418
        //   897: astore_3
        //   898: aload_3
        //   899: invokevirtual 247	java/io/FileNotFoundException:printStackTrace	()V
        //   902: goto -494 -> 408
        //   905: astore_3
        //   906: aload 5
        //   908: astore 4
        //   910: aload_3
        //   911: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   914: goto -506 -> 408
        //   917: getstatic 251	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   920: astore_3
        //   921: goto -433 -> 488
        //   924: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   927: astore_3
        //   928: goto -440 -> 488
        //   931: getstatic 254	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   934: astore_3
        //   935: goto -447 -> 488
        //   938: getstatic 257	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   941: astore_3
        //   942: goto -454 -> 488
        //   945: getstatic 260	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   948: astore_3
        //   949: goto -461 -> 488
        //   952: new 206	com/itextpdf/text/Rectangle
        //   955: dup
        //   956: ldc_w 261
        //   959: ldc_w 262
        //   962: invokespecial 264	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   965: astore_3
        //   966: goto -478 -> 488
        //   969: aload_0
        //   970: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   973: invokestatic 52	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   976: iconst_1
        //   977: if_icmpne +28 -> 1005
        //   980: aload 9
        //   982: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   985: bipush 50
        //   987: aload 10
        //   989: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   992: pop
        //   993: aload 10
        //   995: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   998: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1001: astore_3
        //   1002: goto -326 -> 676
        //   1005: aload_0
        //   1006: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   1009: invokestatic 52	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   1012: iconst_2
        //   1013: if_icmpne -337 -> 676
        //   1016: aload 9
        //   1018: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1021: iconst_5
        //   1022: aload 10
        //   1024: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1027: pop
        //   1028: aload 10
        //   1030: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1033: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1036: astore_3
        //   1037: goto -361 -> 676
        //   1040: astore_3
        //   1041: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1044: aload_3
        //   1045: invokevirtual 292	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1048: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1051: aload_0
        //   1052: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1055: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1058: ifeq -275 -> 783
        //   1061: aload_0
        //   1062: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1065: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1068: ifne -285 -> 783
        //   1071: aload 5
        //   1073: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1076: goto -293 -> 783
        //   1079: astore_3
        //   1080: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1083: aload_3
        //   1084: invokevirtual 298	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1087: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1090: aload_0
        //   1091: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1094: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1097: ifeq -314 -> 783
        //   1100: aload_0
        //   1101: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1104: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1107: ifne -324 -> 783
        //   1110: aload 5
        //   1112: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1115: goto -332 -> 783
        //   1118: astore_3
        //   1119: aload_0
        //   1120: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1123: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1126: ifeq +18 -> 1144
        //   1129: aload_0
        //   1130: getfield 176	com/simpleapp/tinyscanfree/Activity_SelectFile$34:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1133: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1136: ifne +8 -> 1144
        //   1139: aload 5
        //   1141: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1144: aload_3
        //   1145: athrow
        //   1146: astore_3
        //   1147: aload_3
        //   1148: invokevirtual 299	java/io/IOException:printStackTrace	()V
        //   1151: goto -261 -> 890
        //   1154: astore_3
        //   1155: aload_3
        //   1156: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1159: goto -269 -> 890
        //   1162: aload 8
        //   1164: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1167: new 301	android/os/Message
        //   1170: dup
        //   1171: invokespecial 302	android/os/Message:<init>	()V
        //   1174: astore_3
        //   1175: aload_3
        //   1176: iconst_3
        //   1177: putfield 306	android/os/Message:what	I
        //   1180: aload_0
        //   1181: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$34:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   1184: getfield 310	com/simpleapp/tinyscanfree/Activity_SelectFile:handler	Landroid/os/Handler;
        //   1187: aload_3
        //   1188: invokevirtual 316	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1191: pop
        //   1192: return
        //   1193: astore 5
        //   1195: aload_3
        //   1196: astore 4
        //   1198: aload 5
        //   1200: astore_3
        //   1201: goto -291 -> 910
        //   1204: astore 5
        //   1206: aload_3
        //   1207: astore 4
        //   1209: aload 5
        //   1211: astore_3
        //   1212: goto -314 -> 898
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1215	0	this	34
        //   215	679	1	i	int
        //   415	6	2	j	int
        //   53	784	3	localObject1	Object
        //   897	2	3	localFileNotFoundException1	java.io.FileNotFoundException
        //   905	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   920	117	3	localObject2	Object
        //   1040	5	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   1079	5	3	localIOException1	IOException
        //   1118	27	3	localObject3	Object
        //   1146	2	3	localIOException2	IOException
        //   1154	2	3	localDocumentException3	com.itextpdf.text.DocumentException
        //   1174	38	3	localObject4	Object
        //   198	1010	4	localObject5	Object
        //   377	763	5	localDocument1	com.itextpdf.text.Document
        //   1193	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   1204	6	5	localFileNotFoundException2	java.io.FileNotFoundException
        //   23	620	6	localFile	File
        //   207	655	7	localArrayList	ArrayList
        //   374	789	8	localDocument2	com.itextpdf.text.Document
        //   614	403	9	localBitmap	Bitmap
        //   623	406	10	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   382	400	897	java/io/FileNotFoundException
        //   382	400	905	com/itextpdf/text/DocumentException
        //   498	558	1040	com/itextpdf/text/DocumentException
        //   560	676	1040	com/itextpdf/text/DocumentException
        //   676	708	1040	com/itextpdf/text/DocumentException
        //   708	717	1040	com/itextpdf/text/DocumentException
        //   717	758	1040	com/itextpdf/text/DocumentException
        //   969	1002	1040	com/itextpdf/text/DocumentException
        //   1005	1037	1040	com/itextpdf/text/DocumentException
        //   498	558	1079	java/io/IOException
        //   560	676	1079	java/io/IOException
        //   676	708	1079	java/io/IOException
        //   708	717	1079	java/io/IOException
        //   717	758	1079	java/io/IOException
        //   969	1002	1079	java/io/IOException
        //   1005	1037	1079	java/io/IOException
        //   498	558	1118	finally
        //   560	676	1118	finally
        //   676	708	1118	finally
        //   708	717	1118	finally
        //   717	758	1118	finally
        //   969	1002	1118	finally
        //   1005	1037	1118	finally
        //   1041	1051	1118	finally
        //   1080	1090	1118	finally
        //   783	890	1146	java/io/IOException
        //   783	890	1154	com/itextpdf/text/DocumentException
        //   400	405	1193	com/itextpdf/text/DocumentException
        //   400	405	1204	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  public void createPDF2()
  {
    if (this.isMoveImageSort)
    {
      this.isMoveImageSort = false;
      saveMoveImageSort();
    }
    this.mapp.clearMemCache();
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
    this.selectfile_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 31	java/io/File
        //   3: dup
        //   4: aload_0
        //   5: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   8: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   11: ldc 37
        //   13: ldc 39
        //   15: invokeinterface 45 3 0
        //   20: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   23: astore 6
        //   25: aload 6
        //   27: new 50	com/simpleapp/tinyscanfree/Activity_SelectFile$MyFilter
        //   30: dup
        //   31: aload_0
        //   32: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   35: ldc 52
        //   37: invokespecial 55	com/simpleapp/tinyscanfree/Activity_SelectFile$MyFilter:<init>	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;Ljava/lang/String;)V
        //   40: invokevirtual 59	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   43: astore_3
        //   44: aload_3
        //   45: ifnull +15 -> 60
        //   48: aload_3
        //   49: arraylength
        //   50: ifle +10 -> 60
        //   53: aload_3
        //   54: iconst_0
        //   55: aaload
        //   56: invokevirtual 63	java/io/File:delete	()Z
        //   59: pop
        //   60: new 31	java/io/File
        //   63: dup
        //   64: new 65	java/lang/StringBuilder
        //   67: dup
        //   68: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   71: aload_0
        //   72: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   75: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   78: ldc 37
        //   80: ldc 39
        //   82: invokeinterface 45 3 0
        //   87: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   90: ldc 72
        //   92: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   95: aload_0
        //   96: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   99: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   102: ldc 37
        //   104: ldc 39
        //   106: invokeinterface 45 3 0
        //   111: aload_0
        //   112: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   115: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   118: ldc 37
        //   120: ldc 39
        //   122: invokeinterface 45 3 0
        //   127: ldc 72
        //   129: invokevirtual 78	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   132: iconst_1
        //   133: iadd
        //   134: aload_0
        //   135: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   138: invokestatic 35	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/content/SharedPreferences;
        //   141: ldc 37
        //   143: ldc 39
        //   145: invokeinterface 45 3 0
        //   150: invokevirtual 82	java/lang/String:length	()I
        //   153: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
        //   156: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: ldc 52
        //   161: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   167: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   170: astore_3
        //   171: aload_3
        //   172: invokevirtual 93	java/io/File:exists	()Z
        //   175: ifeq +8 -> 183
        //   178: aload_3
        //   179: invokevirtual 63	java/io/File:delete	()Z
        //   182: pop
        //   183: aload 6
        //   185: invokevirtual 97	java/io/File:list	()[Ljava/lang/String;
        //   188: astore 4
        //   190: new 99	java/util/ArrayList
        //   193: dup
        //   194: invokespecial 100	java/util/ArrayList:<init>	()V
        //   197: astore 7
        //   199: aload 4
        //   201: ifnull +43 -> 244
        //   204: iconst_0
        //   205: istore_1
        //   206: iload_1
        //   207: aload 4
        //   209: arraylength
        //   210: if_icmpge +34 -> 244
        //   213: aload 4
        //   215: iload_1
        //   216: aaload
        //   217: ldc 102
        //   219: invokevirtual 106	java/lang/String:matches	(Ljava/lang/String;)Z
        //   222: ifeq +15 -> 237
        //   225: aload 7
        //   227: aload 4
        //   229: iload_1
        //   230: aaload
        //   231: invokeinterface 112 2 0
        //   236: pop
        //   237: iload_1
        //   238: iconst_1
        //   239: iadd
        //   240: istore_1
        //   241: goto -35 -> 206
        //   244: aload 7
        //   246: getstatic 116	com/simpleapp/tinyscanfree/Activity_SelectFile:comparator3	Ljava/util/Comparator;
        //   249: invokestatic 122	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   252: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   255: astore 4
        //   257: new 130	com/itextpdf/text/Document
        //   260: dup
        //   261: invokespecial 131	com/itextpdf/text/Document:<init>	()V
        //   264: astore 8
        //   266: aconst_null
        //   267: astore 5
        //   269: aconst_null
        //   270: astore 4
        //   272: new 133	com/itextpdf/text/pdf/PdfCopy
        //   275: dup
        //   276: aload 8
        //   278: new 135	java/io/FileOutputStream
        //   281: dup
        //   282: aload_3
        //   283: invokespecial 138	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   286: invokespecial 141	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   289: astore_3
        //   290: aload 8
        //   292: invokevirtual 144	com/itextpdf/text/Document:open	()V
        //   295: aload_3
        //   296: astore 4
        //   298: aload 7
        //   300: invokeinterface 147 1 0
        //   305: istore_2
        //   306: iconst_0
        //   307: istore_1
        //   308: iload_1
        //   309: iload_2
        //   310: if_icmpge +518 -> 828
        //   313: aload 7
        //   315: iload_1
        //   316: invokeinterface 151 2 0
        //   321: checkcast 74	java/lang/String
        //   324: bipush 14
        //   326: bipush 15
        //   328: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
        //   331: invokestatic 156	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   334: tableswitch	default:+38->372, 0:+379->713, 1:+386->720, 2:+393->727, 3:+400->734, 4:+407->741, 5:+414->748
        //   372: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   375: astore_3
        //   376: new 130	com/itextpdf/text/Document
        //   379: dup
        //   380: aload_3
        //   381: invokespecial 159	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   384: astore_3
        //   385: aload_3
        //   386: new 135	java/io/FileOutputStream
        //   389: dup
        //   390: new 65	java/lang/StringBuilder
        //   393: dup
        //   394: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   397: aload_0
        //   398: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   401: invokestatic 163	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   404: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   407: aload 7
        //   409: iload_1
        //   410: invokeinterface 151 2 0
        //   415: checkcast 74	java/lang/String
        //   418: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: ldc 52
        //   423: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   429: invokespecial 164	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   432: invokestatic 170	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   435: pop
        //   436: aload_3
        //   437: invokevirtual 144	com/itextpdf/text/Document:open	()V
        //   440: new 65	java/lang/StringBuilder
        //   443: dup
        //   444: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   447: aload 6
        //   449: invokevirtual 173	java/io/File:getPath	()Ljava/lang/String;
        //   452: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: ldc 72
        //   457: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: aload 7
        //   462: iload_1
        //   463: invokeinterface 151 2 0
        //   468: checkcast 74	java/lang/String
        //   471: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   477: invokestatic 178	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   480: astore 5
        //   482: aload 5
        //   484: invokevirtual 182	com/itextpdf/text/Image:getWidth	()F
        //   487: aload_3
        //   488: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   491: invokevirtual 189	com/itextpdf/text/Rectangle:getWidth	()F
        //   494: fcmpl
        //   495: ifge +19 -> 514
        //   498: aload 5
        //   500: invokevirtual 192	com/itextpdf/text/Image:getHeight	()F
        //   503: aload_3
        //   504: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   507: invokevirtual 193	com/itextpdf/text/Rectangle:getHeight	()F
        //   510: fcmpl
        //   511: iflt +12 -> 523
        //   514: aload 5
        //   516: aload_3
        //   517: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   520: invokevirtual 196	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   523: aload 5
        //   525: aload_3
        //   526: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   529: invokevirtual 189	com/itextpdf/text/Rectangle:getWidth	()F
        //   532: aload 5
        //   534: invokevirtual 199	com/itextpdf/text/Image:getScaledWidth	()F
        //   537: fsub
        //   538: fconst_2
        //   539: fdiv
        //   540: aload_3
        //   541: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   544: invokevirtual 193	com/itextpdf/text/Rectangle:getHeight	()F
        //   547: aload 5
        //   549: invokevirtual 202	com/itextpdf/text/Image:getScaledHeight	()F
        //   552: fsub
        //   553: fconst_2
        //   554: fdiv
        //   555: invokevirtual 206	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   558: aload_3
        //   559: aload 5
        //   561: invokevirtual 209	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   564: pop
        //   565: aload_3
        //   566: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   569: new 214	com/itextpdf/text/pdf/PdfReader
        //   572: dup
        //   573: new 65	java/lang/StringBuilder
        //   576: dup
        //   577: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   580: aload_0
        //   581: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   584: invokestatic 163	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   587: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: aload 7
        //   592: iload_1
        //   593: invokeinterface 151 2 0
        //   598: checkcast 74	java/lang/String
        //   601: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   604: ldc 52
        //   606: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   609: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   612: invokespecial 215	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   615: astore_3
        //   616: aload 4
        //   618: aload_3
        //   619: invokevirtual 219	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   622: aload_3
        //   623: invokevirtual 220	com/itextpdf/text/pdf/PdfReader:close	()V
        //   626: new 31	java/io/File
        //   629: dup
        //   630: new 65	java/lang/StringBuilder
        //   633: dup
        //   634: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   637: aload_0
        //   638: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   641: invokestatic 163	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   644: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   647: aload 7
        //   649: iload_1
        //   650: invokeinterface 151 2 0
        //   655: checkcast 74	java/lang/String
        //   658: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   661: ldc 52
        //   663: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   669: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   672: invokevirtual 63	java/io/File:delete	()Z
        //   675: pop
        //   676: iload_1
        //   677: iconst_1
        //   678: iadd
        //   679: istore_1
        //   680: goto -372 -> 308
        //   683: astore 4
        //   685: aload 4
        //   687: invokevirtual 223	java/lang/Exception:printStackTrace	()V
        //   690: goto -438 -> 252
        //   693: astore_3
        //   694: aload_3
        //   695: invokevirtual 224	java/io/FileNotFoundException:printStackTrace	()V
        //   698: goto -400 -> 298
        //   701: astore_3
        //   702: aload 5
        //   704: astore 4
        //   706: aload_3
        //   707: invokevirtual 225	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   710: goto -412 -> 298
        //   713: getstatic 228	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   716: astore_3
        //   717: goto -341 -> 376
        //   720: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   723: astore_3
        //   724: goto -348 -> 376
        //   727: getstatic 231	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   730: astore_3
        //   731: goto -355 -> 376
        //   734: getstatic 234	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   737: astore_3
        //   738: goto -362 -> 376
        //   741: getstatic 237	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   744: astore_3
        //   745: goto -369 -> 376
        //   748: new 188	com/itextpdf/text/Rectangle
        //   751: dup
        //   752: ldc -18
        //   754: ldc -17
        //   756: invokespecial 241	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   759: astore_3
        //   760: goto -384 -> 376
        //   763: astore 5
        //   765: getstatic 247	java/lang/System:err	Ljava/io/PrintStream;
        //   768: aload 5
        //   770: invokevirtual 250	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   773: invokevirtual 255	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   776: aload_3
        //   777: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   780: goto -211 -> 569
        //   783: astore 5
        //   785: getstatic 247	java/lang/System:err	Ljava/io/PrintStream;
        //   788: aload 5
        //   790: invokevirtual 256	java/io/IOException:getMessage	()Ljava/lang/String;
        //   793: invokevirtual 255	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   796: aload_3
        //   797: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   800: goto -231 -> 569
        //   803: astore 4
        //   805: aload_3
        //   806: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   809: aload 4
        //   811: athrow
        //   812: astore_3
        //   813: aload_3
        //   814: invokevirtual 257	java/io/IOException:printStackTrace	()V
        //   817: goto -141 -> 676
        //   820: astore_3
        //   821: aload_3
        //   822: invokevirtual 225	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   825: goto -149 -> 676
        //   828: aload 8
        //   830: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   833: new 259	android/os/Message
        //   836: dup
        //   837: invokespecial 260	android/os/Message:<init>	()V
        //   840: astore_3
        //   841: aload_3
        //   842: bipush 6
        //   844: putfield 264	android/os/Message:what	I
        //   847: aload_0
        //   848: getfield 17	com/simpleapp/tinyscanfree/Activity_SelectFile$22:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   851: getfield 268	com/simpleapp/tinyscanfree/Activity_SelectFile:handler	Landroid/os/Handler;
        //   854: aload_3
        //   855: invokevirtual 274	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   858: pop
        //   859: return
        //   860: astore_3
        //   861: aload_3
        //   862: invokevirtual 223	java/lang/Exception:printStackTrace	()V
        //   865: goto -32 -> 833
        //   868: astore 5
        //   870: aload_3
        //   871: astore 4
        //   873: aload 5
        //   875: astore_3
        //   876: goto -170 -> 706
        //   879: astore 5
        //   881: aload_3
        //   882: astore 4
        //   884: aload 5
        //   886: astore_3
        //   887: goto -193 -> 694
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	890	0	this	22
        //   205	475	1	i	int
        //   305	6	2	j	int
        //   43	580	3	localObject1	Object
        //   693	2	3	localFileNotFoundException1	java.io.FileNotFoundException
        //   701	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   716	90	3	localRectangle	com.itextpdf.text.Rectangle
        //   812	2	3	localIOException1	IOException
        //   820	2	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   840	15	3	localMessage	Message
        //   860	11	3	localException1	Exception
        //   875	12	3	localObject2	Object
        //   188	429	4	localObject3	Object
        //   683	3	4	localException2	Exception
        //   704	1	4	localObject4	Object
        //   803	7	4	localObject5	Object
        //   871	12	4	localObject6	Object
        //   267	436	5	localImage	com.itextpdf.text.Image
        //   763	6	5	localDocumentException3	com.itextpdf.text.DocumentException
        //   783	6	5	localIOException2	IOException
        //   868	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   879	6	5	localFileNotFoundException2	java.io.FileNotFoundException
        //   23	425	6	localFile	File
        //   197	451	7	localArrayList	ArrayList
        //   264	565	8	localDocument	com.itextpdf.text.Document
        // Exception table:
        //   from	to	target	type
        //   244	252	683	java/lang/Exception
        //   272	290	693	java/io/FileNotFoundException
        //   272	290	701	com/itextpdf/text/DocumentException
        //   385	514	763	com/itextpdf/text/DocumentException
        //   514	523	763	com/itextpdf/text/DocumentException
        //   523	565	763	com/itextpdf/text/DocumentException
        //   385	514	783	java/io/IOException
        //   514	523	783	java/io/IOException
        //   523	565	783	java/io/IOException
        //   385	514	803	finally
        //   514	523	803	finally
        //   523	565	803	finally
        //   765	776	803	finally
        //   785	796	803	finally
        //   569	676	812	java/io/IOException
        //   569	676	820	com/itextpdf/text/DocumentException
        //   828	833	860	java/lang/Exception
        //   290	295	868	com/itextpdf/text/DocumentException
        //   290	295	879	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  public void create_JpgToPDF()
  {
    Object localObject = new File(this.root_Path2);
    int j;
    int i;
    if ((localObject != null) && (((File)localObject).exists()))
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".pdf"));
      j = localObject.length;
      i = 0;
    }
    while (i < j)
    {
      clearFile(localObject[i]);
      i += 1;
      continue;
      ((File)localObject).mkdir();
    }
    localObject = new File(this.temporary_jpgtoPdf);
    if ((localObject != null) && (((File)localObject).exists()))
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".pdf"));
      j = localObject.length;
      i = 0;
    }
    while (i < j)
    {
      clearFile(localObject[i]);
      i += 1;
      continue;
      ((File)localObject).mkdir();
    }
    this.selectfilefile_progreebar.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      private PdfWriter writer;
      
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 31	java/io/File
        //   3: dup
        //   4: new 33	java/lang/StringBuilder
        //   7: dup
        //   8: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   11: aload_0
        //   12: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   15: invokestatic 38	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2700	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   18: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   21: aload_0
        //   22: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   25: invokestatic 46	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1400	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/widget/TextView;
        //   28: invokevirtual 52	android/widget/TextView:getText	()Ljava/lang/CharSequence;
        //   31: invokeinterface 58 1 0
        //   36: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   39: ldc 60
        //   41: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   44: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   47: invokespecial 64	java/io/File:<init>	(Ljava/lang/String;)V
        //   50: astore_3
        //   51: aload_3
        //   52: ifnull +15 -> 67
        //   55: aload_3
        //   56: invokevirtual 68	java/io/File:exists	()Z
        //   59: ifeq +8 -> 67
        //   62: aload_3
        //   63: invokevirtual 71	java/io/File:delete	()Z
        //   66: pop
        //   67: getstatic 75	com/simpleapp/tinyscanfree/Activity_SelectFile:idlist	Ljava/util/ArrayList;
        //   70: invokevirtual 81	java/util/ArrayList:size	()I
        //   73: anewarray 83	java/lang/String
        //   76: astore 4
        //   78: iconst_0
        //   79: istore_1
        //   80: iload_1
        //   81: getstatic 75	com/simpleapp/tinyscanfree/Activity_SelectFile:idlist	Ljava/util/ArrayList;
        //   84: invokevirtual 81	java/util/ArrayList:size	()I
        //   87: if_icmpge +66 -> 153
        //   90: aload 4
        //   92: iload_1
        //   93: getstatic 75	com/simpleapp/tinyscanfree/Activity_SelectFile:idlist	Ljava/util/ArrayList;
        //   96: iload_1
        //   97: invokevirtual 87	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   100: checkcast 89	com/simpelapp/entity/PhotoDao
        //   103: invokevirtual 92	com/simpelapp/entity/PhotoDao:getPath	()Ljava/lang/String;
        //   106: getstatic 75	com/simpleapp/tinyscanfree/Activity_SelectFile:idlist	Ljava/util/ArrayList;
        //   109: iload_1
        //   110: invokevirtual 87	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   113: checkcast 89	com/simpelapp/entity/PhotoDao
        //   116: invokevirtual 92	com/simpelapp/entity/PhotoDao:getPath	()Ljava/lang/String;
        //   119: ldc 94
        //   121: invokevirtual 98	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   124: iconst_1
        //   125: iadd
        //   126: getstatic 75	com/simpleapp/tinyscanfree/Activity_SelectFile:idlist	Ljava/util/ArrayList;
        //   129: iload_1
        //   130: invokevirtual 87	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   133: checkcast 89	com/simpelapp/entity/PhotoDao
        //   136: invokevirtual 92	com/simpelapp/entity/PhotoDao:getPath	()Ljava/lang/String;
        //   139: invokevirtual 101	java/lang/String:length	()I
        //   142: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
        //   145: aastore
        //   146: iload_1
        //   147: iconst_1
        //   148: iadd
        //   149: istore_1
        //   150: goto -70 -> 80
        //   153: new 77	java/util/ArrayList
        //   156: dup
        //   157: invokespecial 106	java/util/ArrayList:<init>	()V
        //   160: astore 6
        //   162: aload 4
        //   164: ifnull +43 -> 207
        //   167: iconst_0
        //   168: istore_1
        //   169: iload_1
        //   170: aload 4
        //   172: arraylength
        //   173: if_icmpge +34 -> 207
        //   176: aload 4
        //   178: iload_1
        //   179: aaload
        //   180: ldc 108
        //   182: invokevirtual 112	java/lang/String:matches	(Ljava/lang/String;)Z
        //   185: ifeq +15 -> 200
        //   188: aload 6
        //   190: aload 4
        //   192: iload_1
        //   193: aaload
        //   194: invokeinterface 118 2 0
        //   199: pop
        //   200: iload_1
        //   201: iconst_1
        //   202: iadd
        //   203: istore_1
        //   204: goto -35 -> 169
        //   207: ldc 120
        //   209: new 33	java/lang/StringBuilder
        //   212: dup
        //   213: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   216: ldc 122
        //   218: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: aload 6
        //   223: invokeinterface 123 1 0
        //   228: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   231: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   234: invokestatic 132	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   237: pop
        //   238: aload 6
        //   240: getstatic 136	com/simpleapp/tinyscanfree/Activity_SelectFile:comparator3	Ljava/util/Comparator;
        //   243: invokestatic 142	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   246: getstatic 148	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   249: astore 4
        //   251: new 150	com/itextpdf/text/Document
        //   254: dup
        //   255: invokespecial 151	com/itextpdf/text/Document:<init>	()V
        //   258: astore 7
        //   260: aconst_null
        //   261: astore 5
        //   263: aconst_null
        //   264: astore 4
        //   266: new 153	com/itextpdf/text/pdf/PdfCopy
        //   269: dup
        //   270: aload 7
        //   272: new 155	java/io/FileOutputStream
        //   275: dup
        //   276: aload_3
        //   277: invokespecial 158	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   280: invokespecial 161	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   283: astore_3
        //   284: aload 7
        //   286: invokevirtual 164	com/itextpdf/text/Document:open	()V
        //   289: aload_3
        //   290: astore 4
        //   292: aload 6
        //   294: invokeinterface 123 1 0
        //   299: istore_2
        //   300: iconst_0
        //   301: istore_1
        //   302: iload_1
        //   303: iload_2
        //   304: if_icmpge +758 -> 1062
        //   307: aload 6
        //   309: iload_1
        //   310: invokeinterface 165 2 0
        //   315: checkcast 83	java/lang/String
        //   318: bipush 14
        //   320: bipush 15
        //   322: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
        //   325: invokestatic 170	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   328: tableswitch	default:+40->368, 0:+489->817, 1:+496->824, 2:+503->831, 3:+510->838, 4:+517->845, 5:+524->852
        //   368: getstatic 148	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   371: astore_3
        //   372: new 150	com/itextpdf/text/Document
        //   375: dup
        //   376: aload_3
        //   377: invokespecial 173	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   380: astore 5
        //   382: aload_0
        //   383: aload 5
        //   385: new 155	java/io/FileOutputStream
        //   388: dup
        //   389: new 33	java/lang/StringBuilder
        //   392: dup
        //   393: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   396: aload_0
        //   397: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   400: invokestatic 176	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   403: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   406: aload_0
        //   407: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   410: invokestatic 46	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1400	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/widget/TextView;
        //   413: invokevirtual 52	android/widget/TextView:getText	()Ljava/lang/CharSequence;
        //   416: invokeinterface 58 1 0
        //   421: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   424: ldc 60
        //   426: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   429: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   432: invokespecial 177	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   435: invokestatic 183	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   438: putfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   441: aload 5
        //   443: invokevirtual 164	com/itextpdf/text/Document:open	()V
        //   446: aconst_null
        //   447: astore_3
        //   448: new 187	java/io/FileInputStream
        //   451: dup
        //   452: new 31	java/io/File
        //   455: dup
        //   456: new 33	java/lang/StringBuilder
        //   459: dup
        //   460: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   463: aload_0
        //   464: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   467: invokestatic 190	com/simpleapp/tinyscanfree/Activity_SelectFile:access$900	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   470: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: ldc 94
        //   475: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   478: aload 6
        //   480: iload_1
        //   481: invokeinterface 165 2 0
        //   486: checkcast 83	java/lang/String
        //   489: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   495: invokespecial 64	java/io/File:<init>	(Ljava/lang/String;)V
        //   498: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   501: invokestatic 197	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   504: astore 8
        //   506: new 199	java/io/ByteArrayOutputStream
        //   509: dup
        //   510: invokespecial 200	java/io/ByteArrayOutputStream:<init>	()V
        //   513: astore 9
        //   515: aload_0
        //   516: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   519: invokestatic 204	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   522: ifne +347 -> 869
        //   525: new 33	java/lang/StringBuilder
        //   528: dup
        //   529: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   532: aload_0
        //   533: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   536: invokestatic 190	com/simpleapp/tinyscanfree/Activity_SelectFile:access$900	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   539: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   542: ldc 94
        //   544: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   547: aload 6
        //   549: iload_1
        //   550: invokeinterface 165 2 0
        //   555: checkcast 83	java/lang/String
        //   558: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   561: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   564: invokestatic 209	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   567: astore_3
        //   568: aload_3
        //   569: invokevirtual 213	com/itextpdf/text/Image:getWidth	()F
        //   572: aload 5
        //   574: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   577: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   580: fcmpl
        //   581: ifge +19 -> 600
        //   584: aload_3
        //   585: invokevirtual 223	com/itextpdf/text/Image:getHeight	()F
        //   588: aload 5
        //   590: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   593: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   596: fcmpl
        //   597: iflt +12 -> 609
        //   600: aload_3
        //   601: aload 5
        //   603: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   606: invokevirtual 227	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   609: aload_3
        //   610: aload 5
        //   612: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   615: invokevirtual 220	com/itextpdf/text/Rectangle:getWidth	()F
        //   618: aload_3
        //   619: invokevirtual 230	com/itextpdf/text/Image:getScaledWidth	()F
        //   622: fsub
        //   623: fconst_2
        //   624: fdiv
        //   625: aload 5
        //   627: invokevirtual 217	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   630: invokevirtual 224	com/itextpdf/text/Rectangle:getHeight	()F
        //   633: aload_3
        //   634: invokevirtual 233	com/itextpdf/text/Image:getScaledHeight	()F
        //   637: fsub
        //   638: fconst_2
        //   639: fdiv
        //   640: invokevirtual 237	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   643: aload 5
        //   645: aload_3
        //   646: invokevirtual 240	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   649: pop
        //   650: aload_0
        //   651: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   654: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   657: ifeq +18 -> 675
        //   660: aload_0
        //   661: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   664: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   667: ifne +8 -> 675
        //   670: aload 5
        //   672: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   675: new 251	com/itextpdf/text/pdf/PdfReader
        //   678: dup
        //   679: new 33	java/lang/StringBuilder
        //   682: dup
        //   683: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   686: aload_0
        //   687: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   690: invokestatic 176	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   693: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: aload_0
        //   697: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   700: invokestatic 46	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1400	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/widget/TextView;
        //   703: invokevirtual 52	android/widget/TextView:getText	()Ljava/lang/CharSequence;
        //   706: invokeinterface 58 1 0
        //   711: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   714: ldc 60
        //   716: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   719: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   722: invokespecial 252	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   725: astore_3
        //   726: aload 4
        //   728: aload_3
        //   729: invokevirtual 256	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   732: aload_3
        //   733: invokevirtual 257	com/itextpdf/text/pdf/PdfReader:close	()V
        //   736: new 31	java/io/File
        //   739: dup
        //   740: new 33	java/lang/StringBuilder
        //   743: dup
        //   744: invokespecial 34	java/lang/StringBuilder:<init>	()V
        //   747: aload_0
        //   748: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   751: invokestatic 176	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2600	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Ljava/lang/String;
        //   754: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   757: aload_0
        //   758: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   761: invokestatic 46	com/simpleapp/tinyscanfree/Activity_SelectFile:access$1400	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)Landroid/widget/TextView;
        //   764: invokevirtual 52	android/widget/TextView:getText	()Ljava/lang/CharSequence;
        //   767: invokeinterface 58 1 0
        //   772: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   775: ldc 60
        //   777: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   780: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   783: invokespecial 64	java/io/File:<init>	(Ljava/lang/String;)V
        //   786: invokevirtual 71	java/io/File:delete	()Z
        //   789: pop
        //   790: iload_1
        //   791: iconst_1
        //   792: iadd
        //   793: istore_1
        //   794: goto -492 -> 302
        //   797: astore_3
        //   798: aload_3
        //   799: invokevirtual 260	java/io/FileNotFoundException:printStackTrace	()V
        //   802: goto -510 -> 292
        //   805: astore_3
        //   806: aload 5
        //   808: astore 4
        //   810: aload_3
        //   811: invokevirtual 261	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   814: goto -522 -> 292
        //   817: getstatic 264	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   820: astore_3
        //   821: goto -449 -> 372
        //   824: getstatic 148	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   827: astore_3
        //   828: goto -456 -> 372
        //   831: getstatic 267	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   834: astore_3
        //   835: goto -463 -> 372
        //   838: getstatic 270	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   841: astore_3
        //   842: goto -470 -> 372
        //   845: getstatic 273	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   848: astore_3
        //   849: goto -477 -> 372
        //   852: new 219	com/itextpdf/text/Rectangle
        //   855: dup
        //   856: ldc_w 274
        //   859: ldc_w 275
        //   862: invokespecial 277	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   865: astore_3
        //   866: goto -494 -> 372
        //   869: aload_0
        //   870: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   873: invokestatic 204	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   876: iconst_1
        //   877: if_icmpne +28 -> 905
        //   880: aload 8
        //   882: getstatic 283	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   885: bipush 50
        //   887: aload 9
        //   889: invokevirtual 289	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   892: pop
        //   893: aload 9
        //   895: invokevirtual 293	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   898: invokestatic 296	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   901: astore_3
        //   902: goto -334 -> 568
        //   905: aload_0
        //   906: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   909: invokestatic 204	com/simpleapp/tinyscanfree/Activity_SelectFile:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_SelectFile;)I
        //   912: iconst_2
        //   913: if_icmpne -345 -> 568
        //   916: aload 8
        //   918: getstatic 283	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   921: iconst_5
        //   922: aload 9
        //   924: invokevirtual 289	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   927: pop
        //   928: aload 9
        //   930: invokevirtual 293	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   933: invokestatic 296	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   936: astore_3
        //   937: goto -369 -> 568
        //   940: astore_3
        //   941: getstatic 302	java/lang/System:err	Ljava/io/PrintStream;
        //   944: aload_3
        //   945: invokevirtual 305	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   948: invokevirtual 310	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   951: aload_0
        //   952: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   955: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   958: ifeq -283 -> 675
        //   961: aload_0
        //   962: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   965: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   968: ifne -293 -> 675
        //   971: aload 5
        //   973: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   976: goto -301 -> 675
        //   979: astore_3
        //   980: getstatic 302	java/lang/System:err	Ljava/io/PrintStream;
        //   983: aload_3
        //   984: invokevirtual 311	java/io/IOException:getMessage	()Ljava/lang/String;
        //   987: invokevirtual 310	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   990: aload_0
        //   991: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   994: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   997: ifeq -322 -> 675
        //   1000: aload_0
        //   1001: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1004: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1007: ifne -332 -> 675
        //   1010: aload 5
        //   1012: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1015: goto -340 -> 675
        //   1018: astore_3
        //   1019: aload_0
        //   1020: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1023: invokevirtual 243	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1026: ifeq +18 -> 1044
        //   1029: aload_0
        //   1030: getfield 185	com/simpleapp/tinyscanfree/Activity_SelectFile$35:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1033: invokevirtual 246	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1036: ifne +8 -> 1044
        //   1039: aload 5
        //   1041: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1044: aload_3
        //   1045: athrow
        //   1046: astore_3
        //   1047: aload_3
        //   1048: invokevirtual 312	java/io/IOException:printStackTrace	()V
        //   1051: goto -261 -> 790
        //   1054: astore_3
        //   1055: aload_3
        //   1056: invokevirtual 261	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1059: goto -269 -> 790
        //   1062: aload 7
        //   1064: invokevirtual 249	com/itextpdf/text/Document:close	()V
        //   1067: new 314	android/os/Message
        //   1070: dup
        //   1071: invokespecial 315	android/os/Message:<init>	()V
        //   1074: astore_3
        //   1075: aload_3
        //   1076: iconst_3
        //   1077: putfield 319	android/os/Message:what	I
        //   1080: aload_0
        //   1081: getfield 19	com/simpleapp/tinyscanfree/Activity_SelectFile$35:this$0	Lcom/simpleapp/tinyscanfree/Activity_SelectFile;
        //   1084: getfield 323	com/simpleapp/tinyscanfree/Activity_SelectFile:handler	Landroid/os/Handler;
        //   1087: aload_3
        //   1088: invokevirtual 329	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1091: pop
        //   1092: return
        //   1093: astore 5
        //   1095: aload_3
        //   1096: astore 4
        //   1098: aload 5
        //   1100: astore_3
        //   1101: goto -291 -> 810
        //   1104: astore 5
        //   1106: aload_3
        //   1107: astore 4
        //   1109: aload 5
        //   1111: astore_3
        //   1112: goto -314 -> 798
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1115	0	this	35
        //   79	715	1	i	int
        //   299	6	2	j	int
        //   50	683	3	localObject1	Object
        //   797	2	3	localFileNotFoundException1	java.io.FileNotFoundException
        //   805	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   820	117	3	localObject2	Object
        //   940	5	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   979	5	3	localIOException1	IOException
        //   1018	27	3	localObject3	Object
        //   1046	2	3	localIOException2	IOException
        //   1054	2	3	localDocumentException3	com.itextpdf.text.DocumentException
        //   1074	38	3	localObject4	Object
        //   76	1032	4	localObject5	Object
        //   261	779	5	localDocument1	com.itextpdf.text.Document
        //   1093	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   1104	6	5	localFileNotFoundException2	java.io.FileNotFoundException
        //   160	388	6	localArrayList	ArrayList
        //   258	805	7	localDocument2	com.itextpdf.text.Document
        //   504	413	8	localBitmap	Bitmap
        //   513	416	9	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   266	284	797	java/io/FileNotFoundException
        //   266	284	805	com/itextpdf/text/DocumentException
        //   382	446	940	com/itextpdf/text/DocumentException
        //   448	568	940	com/itextpdf/text/DocumentException
        //   568	600	940	com/itextpdf/text/DocumentException
        //   600	609	940	com/itextpdf/text/DocumentException
        //   609	650	940	com/itextpdf/text/DocumentException
        //   869	902	940	com/itextpdf/text/DocumentException
        //   905	937	940	com/itextpdf/text/DocumentException
        //   382	446	979	java/io/IOException
        //   448	568	979	java/io/IOException
        //   568	600	979	java/io/IOException
        //   600	609	979	java/io/IOException
        //   609	650	979	java/io/IOException
        //   869	902	979	java/io/IOException
        //   905	937	979	java/io/IOException
        //   382	446	1018	finally
        //   448	568	1018	finally
        //   568	600	1018	finally
        //   600	609	1018	finally
        //   609	650	1018	finally
        //   869	902	1018	finally
        //   905	937	1018	finally
        //   941	951	1018	finally
        //   980	990	1018	finally
        //   675	790	1046	java/io/IOException
        //   675	790	1054	com/itextpdf/text/DocumentException
        //   284	289	1093	com/itextpdf/text/DocumentException
        //   284	289	1104	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
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
            while (i < Activity_SelectFile.idlist.size())
            {
              localObject = new File(((PhotoDao)Activity_SelectFile.idlist.get(i)).getPath());
              if ((localObject != null) && (((File)localObject).exists()))
              {
                ((File)localObject).delete();
                Activity_SelectFile.this.mapp.getmMemoryCache().remove("main" + ((File)localObject).getPath());
                Activity_SelectFile.mlist2.remove(Activity_SelectFile.idlist.get(i));
              }
              i += 1;
            }
            Activity_SelectFile.this.mapp.setUpdate(true);
            Activity_SelectFile.this.deletePDF();
            Object localObject = new Message();
            ((Message)localObject).what = 0;
            Activity_SelectFile.this.handler.sendMessage((Message)localObject);
            if (Activity_SelectFile.mlist2.size() == 0) {
              Activity_SelectFile.this.finish();
            }
          }
        }).start();
      }
    }).setNegativeButton(getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create().show();
  }
  
  public void deletePDF()
  {
    File[] arrayOfFile = new File(this.documentPath).listFiles(new MyFilter(".pdf"));
    if ((arrayOfFile != null) && (arrayOfFile.length > 0))
    {
      int i = 0;
      while (i < arrayOfFile.length)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
    }
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
  
  protected long getfilesizeLength()
  {
    long l1 = 0L;
    long l2;
    if (this.islongclick)
    {
      i = 0;
      for (;;)
      {
        l2 = l1;
        if (i >= idlist.size()) {
          break;
        }
        l1 += new File(((PhotoDao)idlist.get(i)).getPath()).length();
        i += 1;
      }
    }
    int i = 0;
    for (;;)
    {
      l2 = l1;
      if (i >= mlist2.size()) {
        break;
      }
      l1 += new File(((PhotoDao)mlist2.get(i)).getPath()).length();
      i += 1;
    }
    return l2;
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public void init()
  {
    mlist2 = new ArrayList();
    File[] arrayOfFile = new File(this.documentPath).listFiles();
    int i = 0;
    if (arrayOfFile != null) {
      i = arrayOfFile.length;
    }
    int j = 0;
    while (j < i)
    {
      if ((!arrayOfFile[j].isDirectory()) && (arrayOfFile[j].getName().matches("[0-9]{18}.jpg")))
      {
        PhotoDao localPhotoDao = new PhotoDao();
        localPhotoDao.setCheck(false);
        localPhotoDao.setPath(arrayOfFile[j].getPath());
        mlist2.add(localPhotoDao);
      }
      j += 1;
    }
    j = idlist.size();
    i = 0;
    while (i < j)
    {
      int k = findId((PhotoDao)idlist.get(i));
      if (k != -1) {
        ((PhotoDao)mlist2.get(k)).setCheck(true);
      }
      i += 1;
    }
    Collections.sort(mlist2, this.comparator);
    long l = 0L;
    i = 0;
    while (i < mlist2.size())
    {
      l += new File(((PhotoDao)mlist2.get(i)).getPath()).length();
      i += 1;
    }
    this.selectfile_filelength.setText(Util.FormetFileSize1(l));
  }
  
  @SuppressLint({"InflateParams"})
  public void listByGrid()
  {
    this.grid = ((DragGridView2)findViewById(2131624234));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.mapp.setDisplaywidth(localDisplayMetrics.widthPixels);
    this.mapp.setDispalyheight(localDisplayMetrics.heightPixels);
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
      this.adapter = new SelectFile_GridAdapter(this.context, mlist2);
      if (this.islongclick) {
        this.adapter.isse = true;
      }
      this.grid.setAdapter(this.adapter);
      this.grid.setOnChangeListener(new DragGridView2.OnChanageListener()
      {
        public void onChange(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          if ((paramAnonymousInt1 >= 0) && (paramAnonymousInt1 < Activity_SelectFile.mlist2.size()) && (paramAnonymousInt2 >= 0) && (paramAnonymousInt2 < Activity_SelectFile.mlist2.size()))
          {
            PhotoDao localPhotoDao = (PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt1);
            if (paramAnonymousInt1 < paramAnonymousInt2) {
              while (paramAnonymousInt1 < paramAnonymousInt2)
              {
                Collections.swap(Activity_SelectFile.mlist2, paramAnonymousInt1, paramAnonymousInt1 + 1);
                paramAnonymousInt1 += 1;
              }
            }
            if (paramAnonymousInt1 > paramAnonymousInt2) {
              while (paramAnonymousInt1 > paramAnonymousInt2)
              {
                Collections.swap(Activity_SelectFile.mlist2, paramAnonymousInt1, paramAnonymousInt1 - 1);
                paramAnonymousInt1 -= 1;
              }
            }
            Activity_SelectFile.mlist2.set(paramAnonymousInt2, localPhotoDao);
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            Activity_SelectFile.access$1502(Activity_SelectFile.this, true);
            Activity_SelectFile.this.deletePDF();
            Activity_SelectFile.this.mapp.setUpdate(true);
          }
        }
      });
      this.grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (new File(((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).getPath()).exists())
          {
            if (!Activity_SelectFile.this.islongclick) {
              break label282;
            }
            if (!((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).isCheck()) {
              break label180;
            }
            ((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_SelectFile.this.removeid((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt));
            if (!Activity_SelectFile.idlist.isEmpty()) {
              break label170;
            }
            Activity_SelectFile.this.unselected();
            Activity_SelectFile.access$302(Activity_SelectFile.this, false);
            Activity_SelectFile.this.adapter.isse = false;
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            if (Activity_SelectFile.idlist.size() != Activity_SelectFile.mlist2.size()) {
              Activity_SelectFile.this.selectfile_selectall.setText(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165561));
            }
          }
          label170:
          label180:
          do
          {
            return;
            Activity_SelectFile.this.selected();
            break;
            ((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).setCheck(true);
            Activity_SelectFile.this.adapter.isse = true;
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            paramAnonymousAdapterView = (PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt);
            Activity_SelectFile.idlist.add(paramAnonymousAdapterView);
            Activity_SelectFile.this.selected();
          } while (Activity_SelectFile.idlist.size() != Activity_SelectFile.mlist2.size());
          Activity_SelectFile.this.selectfile_selectall.setText(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165291));
          return;
          label282:
          paramAnonymousAdapterView = new Intent(Activity_SelectFile.this.context, Activity_EditPhoto.class);
          Activity_SelectFile.this.editor.putString("folder_path", Activity_SelectFile.this.documentPath);
          Activity_SelectFile.this.editor.putString("folder_name", Activity_SelectFile.this.oldname);
          Activity_SelectFile.this.editor.putString("folder_root_path", Activity_SelectFile.this.preferences.getString("folder_root_path", ""));
          Activity_SelectFile.this.editor.putInt("folder_id", paramAnonymousInt);
          Activity_SelectFile.this.editor.commit();
          Activity_SelectFile.this.mapp.setAdd(false);
          Activity_SelectFile.this.startActivity(paramAnonymousAdapterView);
        }
      });
      this.grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (new File(((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).getPath()).exists())
          {
            if (!Activity_SelectFile.this.islongclick)
            {
              Activity_SelectFile.access$302(Activity_SelectFile.this, true);
              if (!((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).isCheck()) {
                break label150;
              }
              ((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).setCheck(false);
              Activity_SelectFile.this.removeid((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt));
              if (!Activity_SelectFile.idlist.isEmpty()) {
                break label140;
              }
              Activity_SelectFile.this.unselected();
              Activity_SelectFile.access$302(Activity_SelectFile.this, false);
              Activity_SelectFile.this.adapter.isse = false;
            }
            for (;;)
            {
              Activity_SelectFile.this.adapter.notifyDataSetChanged();
              return true;
              label140:
              Activity_SelectFile.this.selected();
            }
            label150:
            ((PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt)).setCheck(true);
            Activity_SelectFile.this.adapter.isse = true;
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            paramAnonymousAdapterView = (PhotoDao)Activity_SelectFile.mlist2.get(paramAnonymousInt);
            Activity_SelectFile.idlist.add(paramAnonymousAdapterView);
            Activity_SelectFile.this.selected();
            return true;
          }
          Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165331), 0).show();
          Activity_SelectFile.mlist2.remove(paramAnonymousInt);
          Activity_SelectFile.this.adapter.notifyDataSetChanged();
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
      this.mapp.setSavePath(this.documentPath + "/");
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
    this.activity_selectfileFile = this;
    this.context = this;
    MyApplication.activityList.add(this);
    this.mapp = MyApplication.getApplication(this.context);
    if (this.mapp.isPad())
    {
      setContentView(2130903083);
      this.preferences = getSharedPreferences("TopScanner", 0);
      this.editor = this.preferences.edit();
      this.editor.putLong("show_chaye_in_out_position", this.preferences.getLong("show_chaye_in_out_position", 0L) + 1L);
      this.editor.commit();
      idlist = new ArrayList();
      if ((this.preferences.getInt("SDCARD_PATH", 0) <= 0) || (this.mapp.getSdcard_list().size() <= 1)) {
        break label976;
      }
      this.root_Path2 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Documents/");
      this.temporary_jpgtoPdf = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/PDF/");
      this.compressJpeg_Path = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Jpeg/");
      label325:
      this.inflater = LayoutInflater.from(this.context);
      this.selectfilefile_progreebar = ((ProgressBar)findViewById(2131624242));
      this.mDbHelper = MyDbHelper.getHelper(this.context);
      this.db = this.mDbHelper.getWritableDatabase();
      this.folder_root_path = this.preferences.getString("folder_root_path", "");
      this.documentPath = this.preferences.getString("folder_path", "");
      this.oldname = this.preferences.getString("folder_name", "");
      Log.i("TAG", "=========" + this.folder_root_path + " " + this.documentPath);
      this.title = ((TextView)findViewById(2131624226));
      this.title.setText(this.oldname);
      this.selectfile_filelength = ((TextView)findViewById(2131624227));
      this.selectfile_progreebar = ((ProgressBar)findViewById(2131624242));
      this.selectfile_counttext = ((TextView)findViewById(2131624228));
      this.selectfile_selecttextview = ((TextView)findViewById(2131624229));
      this.selectfile_selectall = ((RadioButton)findViewById(2131624230));
      this.selectfile_selectall.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_SelectFile.this.selectfile_selectall.getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165561)))
          {
            Activity_SelectFile.this.selectfile_selectall.setText(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165291));
            Activity_SelectFile.idlist.clear();
            i = 0;
            while (i < Activity_SelectFile.mlist2.size())
            {
              ((PhotoDao)Activity_SelectFile.mlist2.get(i)).setCheck(true);
              paramAnonymousView = (PhotoDao)Activity_SelectFile.mlist2.get(i);
              Activity_SelectFile.idlist.add(paramAnonymousView);
              i += 1;
            }
            Activity_SelectFile.this.adapter.isse = true;
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            Activity_SelectFile.this.selected();
          }
          while (!Activity_SelectFile.this.selectfile_selectall.getText().toString().equals(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165291))) {
            return;
          }
          Activity_SelectFile.this.selectfile_selectall.setText(Activity_SelectFile.this.activity_selectfileFile.getResources().getString(2131165561));
          int i = 0;
          while (i < Activity_SelectFile.mlist2.size())
          {
            ((PhotoDao)Activity_SelectFile.mlist2.get(i)).setCheck(false);
            Activity_SelectFile.this.removeid((PhotoDao)Activity_SelectFile.mlist2.get(i));
            i += 1;
          }
          if (Activity_SelectFile.idlist.isEmpty())
          {
            Activity_SelectFile.this.unselected();
            Activity_SelectFile.access$302(Activity_SelectFile.this, false);
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
          }
          for (;;)
          {
            Activity_SelectFile.this.adapter.notifyDataSetChanged();
            return;
            Activity_SelectFile.this.selected();
          }
        }
      });
      this.selectfile_share = ((ImageView)findViewById(2131624225));
      this.selectfile_share.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_SelectFile.this.showPdf_or_imagejpeg_SelectDailog(1);
        }
      });
      this.selectfile_share.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165567), 0);
          paramAnonymousView.setGravity(48, 0, Activity_SelectFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.selectfile_title_more = ((ImageView)findViewById(2131624232));
      this.selectfile_title_more.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_SelectFile.this.initPopuptWindow2(1);
          Activity_SelectFile.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
        }
      });
      this.selectfile_title_more.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165403), 0);
          paramAnonymousView.setGravity(48, 0, Activity_SelectFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.back = ((ImageView)findViewById(2131624224));
      this.back.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_SelectFile.this.islongclick)
          {
            Activity_SelectFile.this.unselected();
            return;
          }
          if (Activity_SelectFile.this.mapp.isUpdate())
          {
            Activity_SelectFile.this.createPDF2();
            Activity_SelectFile.this.mapp.setUpdate(false);
            return;
          }
          Activity_SelectFile.this.finish();
        }
      });
      this.camera = ((ImageView)findViewById(2131624237));
      this.camera.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_SelectFile.this.takePicture(true);
        }
      });
      this.selectfile_onlongclick_layout = ((LinearLayout)findViewById(2131624235));
      this.selectfile_sharelayout = ((LinearLayout)findViewById(2131624238));
      this.selectfile_fax = ((ImageView)findViewById(2131624231));
      this.selectfile_fax.setOnClickListener(this.myOnClickListener);
      this.selectfile_fax.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165665), 0);
          paramAnonymousView.setGravity(48, 0, Activity_SelectFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      if (this.mapp.isPad()) {
        break label1066;
      }
      this.selectfile_emaillayout = ((LinearLayout)findViewById(2131624239));
      this.selectfile_emaillayout.setOnClickListener(this.myOnClickListener);
    }
    for (;;)
    {
      this.selectfile_deletelayout = ((LinearLayout)findViewById(2131624240));
      this.selectfile_morelayout = ((LinearLayout)findViewById(2131624241));
      this.selectfile_sharelayout.setOnClickListener(this.myOnClickListener);
      this.selectfile_deletelayout.setOnClickListener(this.myOnClickListener);
      this.selectfile_morelayout.setOnClickListener(this.myOnClickListener);
      this.selectfile_relativelayout_ads = ((RelativeLayout)findViewById(2131624222));
      if (this.preferences.getInt("newUser_1.3", -1) == 1) {
        AdsUtils.showAds(Activity_Main.activity_Main, this.selectfile_relativelayout_ads, this.mapp, 4);
      }
      if (this.preferences.getLong("show_chaye_in_out_position", 0L) % 4L == 1L) {
        AdsUtils.showInterstitial(Activity_Main.activity_Main, 0);
      }
      return;
      setRequestedOrientation(1);
      break;
      label976:
      this.root_Path2 = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Documents/");
      this.temporary_jpgtoPdf = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/PDF/");
      this.compressJpeg_Path = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Jpeg/");
      break label325;
      label1066:
      this.selectfile_importgallery = ((ImageView)findViewById(2131624243));
      this.selectfile_editname = ((ImageView)findViewById(2131624244));
      this.selectfile_importgallery.setOnClickListener(this.myOnClickListener);
      this.selectfile_editname.setOnClickListener(this.myOnClickListener);
      this.selectfile_importgallery.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165377), 0);
          paramAnonymousView.setGravity(48, 0, Activity_SelectFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
      this.selectfile_editname.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          paramAnonymousView = Toast.makeText(Activity_SelectFile.this.context, Activity_SelectFile.this.getResources().getString(2131165537), 0);
          paramAnonymousView.setGravity(48, 0, Activity_SelectFile.this.dip2px(56.0F));
          paramAnonymousView.show();
          return false;
        }
      });
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.editor.putInt("folder_id", 0);
    this.editor.commit();
    this.db.close();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.islongclick) {
        unselected();
      }
      for (;;)
      {
        return true;
        if (this.mapp.isUpdate())
        {
          createPDF2();
          this.mapp.setUpdate(false);
        }
        else
        {
          finish();
        }
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((this.islongclick) && (this.isMoveImageSort)) {}
    for (;;)
    {
      if (this.isSelect) {
        selected();
      }
      if (this.grid != null) {
        this.grid.setSelection(this.preferences.getInt("folder_id", 0));
      }
      return;
      relist();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void removeid(PhotoDao paramPhotoDao)
  {
    int j = idlist.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (paramPhotoDao.getPath().equals(((PhotoDao)idlist.get(i)).getPath())) {
          idlist.remove(i);
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  public void selected()
  {
    this.isSelect = true;
    this.title.setVisibility(8);
    this.selectfile_filelength.setVisibility(8);
    this.selectfile_share.setVisibility(8);
    this.selectfile_fax.setVisibility(8);
    if (this.mapp.isPad())
    {
      this.selectfile_editname.setVisibility(8);
      this.selectfile_importgallery.setVisibility(8);
    }
    this.selectfile_title_more.setVisibility(8);
    this.camera.setVisibility(4);
    this.selectfile_onlongclick_layout.setVisibility(0);
    this.selectfile_selectall.setVisibility(0);
    this.selectfile_selecttextview.setVisibility(0);
    this.selectfile_counttext.setVisibility(0);
    this.selectfile_counttext.setText("" + idlist.size());
  }
  
  protected void shareDocment()
  {
    if (this.pdf_OR_jpeg == 1) {
      if (this.islongclick) {
        create_JpgToPDF();
      }
    }
    Message localMessage;
    while (this.pdf_OR_jpeg != 2)
    {
      return;
      if (this.export_size == 0)
      {
        if (this.mapp.isUpdate())
        {
          createPDF();
          return;
        }
        File localFile = new File(this.preferences.getString("folder_path", ""));
        Object localObject = localFile.listFiles(new MyFilter(".pdf"));
        if ((localObject != null) && (localObject.length > 0))
        {
          localObject = new File(localFile.getPath() + "/" + localFile.getName() + ".pdf");
          try
          {
            if ((Util.getFileSize((File)localObject) == 0L) || (!Util.isOpenPdf(localFile.getPath() + "/" + localFile.getName() + ".pdf")))
            {
              createPDF();
              return;
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            return;
          }
          localMessage = new Message();
          localMessage.what = 3;
          this.handler.sendMessage(localMessage);
          return;
        }
        createPDF();
        return;
      }
      createPDF();
      return;
    }
    if (this.export_size == 0)
    {
      localMessage = new Message();
      localMessage.what = 33;
      this.handler.sendMessage(localMessage);
      return;
    }
    CompressImage();
  }
  
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
          Activity_SelectFile.access$2502(Activity_SelectFile.this, 0);
          if (paramInt == 1)
          {
            Activity_SelectFile.access$2102(Activity_SelectFile.this, 0);
            Activity_SelectFile.access$2902(Activity_SelectFile.this, 0);
          }
          for (;;)
          {
            Activity_SelectFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_SelectFile.access$2102(Activity_SelectFile.this, 2);
              Activity_SelectFile.access$2902(Activity_SelectFile.this, 2);
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
          Activity_SelectFile.access$2502(Activity_SelectFile.this, 1);
          if (paramInt == 1)
          {
            Activity_SelectFile.access$2102(Activity_SelectFile.this, 0);
            Activity_SelectFile.access$2902(Activity_SelectFile.this, 0);
          }
          for (;;)
          {
            Activity_SelectFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_SelectFile.access$2102(Activity_SelectFile.this, 2);
              Activity_SelectFile.access$2902(Activity_SelectFile.this, 2);
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
          Activity_SelectFile.access$2502(Activity_SelectFile.this, 2);
          if (paramInt == 1)
          {
            Activity_SelectFile.access$2102(Activity_SelectFile.this, 0);
            Activity_SelectFile.access$2902(Activity_SelectFile.this, 0);
          }
          for (;;)
          {
            Activity_SelectFile.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_SelectFile.access$2102(Activity_SelectFile.this, 2);
              Activity_SelectFile.access$2902(Activity_SelectFile.this, 2);
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
        Activity_SelectFile.access$2002(Activity_SelectFile.this, 1);
        Activity_SelectFile.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    localLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_SelectFile.access$2002(Activity_SelectFile.this, 2);
        Activity_SelectFile.this.showPdfSzieSelectDailog(paramInt);
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
        Toast.makeText(Activity_SelectFile.this.getApplicationContext(), paramString, 0).show();
      }
    });
  }
  
  @SuppressLint({"InflateParams"})
  public void takePicture(boolean paramBoolean)
  {
    if (this.isMoveImageSort)
    {
      this.isMoveImageSort = false;
      saveMoveImageSort();
    }
    if (3 >= 0)
    {
      buy();
      return;
    }
    this.editor.commit();
    Intent localIntent;
    if (paramBoolean)
    {
      this.mapp.setSavePath(this.preferences.getString("folder_path", "") + "/");
      this.editor.putBoolean("where", true);
      this.editor.commit();
      if (this.mapp.isPad()) {}
      for (localIntent = new Intent(this.context, Activity_PadCamera.class);; localIntent = new Intent(this.context, Activity_CameraPreview.class))
      {
        startActivityForResult(localIntent, 0);
        return;
      }
    }
    if (this.preferences.getBoolean("setting_import_gallery", false))
    {
      localIntent = new Intent("android.intent.action.PICK");
      localIntent.setType("image/*");
      localIntent.setFlags(67108864);
      startActivityForResult(localIntent, 10);
      return;
    }
    this.mapp.setSavePath(this.preferences.getString("folder_path", "") + "/");
    this.mapp.setPhotofrom(false);
    this.editor.putBoolean("where", true);
    this.editor.commit();
    startActivity(new Intent(this, LocalAlbumActivity.class));
  }
  
  public void unselected()
  {
    this.adapter.isse = false;
    this.isSelect = false;
    this.islongclick = false;
    int j = mlist2.size();
    int i = 0;
    while (i < j)
    {
      ((PhotoDao)mlist2.get(i)).setCheck(false);
      this.adapter.notifyDataSetChanged();
      i += 1;
    }
    idlist.clear();
    this.selectfile_share.setVisibility(0);
    this.selectfile_fax.setVisibility(0);
    if (this.mapp.isPad())
    {
      this.selectfile_editname.setVisibility(0);
      this.selectfile_importgallery.setVisibility(0);
    }
    this.selectfile_title_more.setVisibility(0);
    this.title.setVisibility(0);
    this.selectfile_filelength.setVisibility(0);
    this.selectfile_counttext.setText("");
    this.selectfile_counttext.setVisibility(8);
    this.selectfile_selecttextview.setVisibility(8);
    this.selectfile_selectall.setText(this.activity_selectfileFile.getResources().getString(2131165561));
    this.selectfile_selectall.setVisibility(8);
    this.camera.setVisibility(0);
    this.selectfile_onlongclick_layout.setVisibility(8);
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
