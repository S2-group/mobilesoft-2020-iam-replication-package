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
import android.support.v4.content.FileProvider;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.adpter.CopyAdapter;
import com.appxy.adpter.GridAdapter;
import com.appxy.adpter.ListAdapter;
import com.appxy.adpter.MyPrintDocumentAdapter;
import com.appxy.adpter.SharePopuList1Adapter;
import com.appxy.adpter.SharePopuList1_padAdapter;
import com.appxy.adpter.SharePopuList2Adapter;
import com.appxy.adpter.SharePopuList2_padAdapter;
import com.appxy.db.MyDbHelper;
import com.appxy.entity.Photo_info;
import com.appxy.entity.Photo_item;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.RateMeDialogFragment;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
import com.appxy.views.HorizontalListView;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.itextpdf.text.pdf.PdfWriter;
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

public class Activity_FolderFile
  extends BaseActivity
{
  static final String APP_CLIENT_ID = "000000004C12023B";
  private static final int RC_REQUEST = 10001;
  static Comparator<Photo_info> comparator = new Comparator()
  {
    public int compare(Photo_info paramAnonymousPhoto_info1, Photo_info paramAnonymousPhoto_info2)
    {
      return Long.valueOf(paramAnonymousPhoto_info2.getRealtime()).compareTo(Long.valueOf(paramAnonymousPhoto_info1.getRealtime()));
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
  public static List<Photo_info> idlist;
  static ArrayList<Photo_info> mlist2;
  public String CLIENT_ID = "tlmxq5fvx3x2ryhmg2pl5d9612w6lnc1";
  public String CLIENT_SECRET = "ks8fU65XGaun5zxY4a2Gt6WBQd3hikbT";
  private Activity_FolderFile activity_folderFile;
  private ImageView back2;
  ImageView camera;
  private int clickid2;
  private boolean clickrate = false;
  private String compressJpeg_Path = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan/temporary/Jpeg/";
  private Context context;
  private int currentWidth = 0;
  private SQLiteDatabase db;
  private ImageView delete;
  private SharedPreferences.Editor editor;
  private SharePopuList1_padAdapter exportAdapter1;
  private SharePopuList2_padAdapter exportAdapter2;
  private List<File> export_file;
  private int export_select = 0;
  private int export_size = 0;
  String folderPath;
  private RelativeLayout folder_camera_gallery_layout;
  private TextView folder_documet_count;
  private RelativeLayout folder_onLongclicklayout;
  private Toolbar folder_toolbar;
  private ImageView folderedit;
  private RelativeLayout folderfile_tile_relativelayout;
  ImageView gallery;
  private ImageView getall;
  private GridView grid;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private PrintManager printManager;
    
    @SuppressLint({"InflateParams", "DefaultLocale"})
    public void handleMessage(final Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      label260:
      Object localObject1;
      Object localObject2;
      label980:
      label1027:
      label1152:
      label1277:
      label1623:
      label1634:
      Object localObject4;
      label2982:
      label3322:
      label3410:
      label3625:
      label3666:
      label3886:
      label3974:
      label4634:
      label4679:
      label4729:
      label4908:
      label4920:
      label4922:
      label5012:
      label5647:
      label6159:
      label7312:
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
                int i;
                int j;
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
                                            do
                                            {
                                              do
                                              {
                                                return;
                                                Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131296599));
                                                return;
                                                ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                                                return;
                                                Activity_FolderFile.idlist.clear();
                                                if (Activity_FolderFile.this.list_type == 0)
                                                {
                                                  Activity_FolderFile.this.madapter.notifyDataSetChanged();
                                                  if (Activity_FolderFile.mlist2.size() <= 1) {
                                                    break label260;
                                                  }
                                                  Activity_FolderFile.this.folder_documet_count.setVisibility(0);
                                                  Activity_FolderFile.this.folder_documet_count.setText(Activity_FolderFile.mlist2.size() + " Docs");
                                                }
                                                for (;;)
                                                {
                                                  if ((Activity_FolderFile.this.progressDialog != null) && (Activity_FolderFile.this.progressDialog.isShowing())) {
                                                    Activity_FolderFile.this.progressDialog.dismiss();
                                                  }
                                                  Activity_FolderFile.access$2302(Activity_FolderFile.this, null);
                                                  Activity_FolderFile.this.unselected();
                                                  return;
                                                  Activity_FolderFile.this.madapter2.notifyDataSetChanged();
                                                  break;
                                                  if (Activity_FolderFile.mlist2.size() == 1)
                                                  {
                                                    Activity_FolderFile.this.folder_documet_count.setVisibility(0);
                                                    Activity_FolderFile.this.folder_documet_count.setText(Activity_FolderFile.mlist2.size() + " Doc");
                                                  }
                                                  else
                                                  {
                                                    Activity_FolderFile.this.folder_documet_count.setVisibility(8);
                                                  }
                                                }
                                                Activity_FolderFile.this.unselected();
                                                Activity_FolderFile.this.relist();
                                                return;
                                                new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296530)).setMessage(Activity_FolderFile.this.getResources().getString(2131296387)).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296464), null).create().show();
                                                return;
                                                if ((Activity_FolderFile.this.progressDialog != null) && (Activity_FolderFile.this.progressDialog.isShowing())) {
                                                  Activity_FolderFile.this.progressDialog.dismiss();
                                                }
                                                Activity_FolderFile.access$2302(Activity_FolderFile.this, null);
                                                Activity_FolderFile.access$2402(Activity_FolderFile.this, null);
                                                Activity_FolderFile.access$2502(Activity_FolderFile.this, new ArrayList());
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
                                                if (i < Activity_FolderFile.this.export_file.size())
                                                {
                                                  if (Build.VERSION.SDK_INT >= 24) {
                                                    ((ArrayList)localObject1).add(FileProvider.getUriForFile(Activity_FolderFile.this.context, Activity_FolderFile.this.getPackageName() + ".fileprovider", (File)Activity_FolderFile.this.export_file.get(i)));
                                                  }
                                                  for (;;)
                                                  {
                                                    i += 1;
                                                    break;
                                                    ((ArrayList)localObject1).add(Uri.fromFile((File)Activity_FolderFile.this.export_file.get(i)));
                                                  }
                                                }
                                                localObject2 = Activity_FolderFile.this.getPackageManager().getInstalledApplications(0);
                                                j = ((List)localObject2).size();
                                                localObject3 = new Intent("android.intent.action.SEND_MULTIPLE");
                                                ((Intent)localObject3).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                                                ((Intent)localObject3).setType("application/pdf");
                                                if (Activity_FolderFile.this.isListView1OrListview2) {
                                                  break label1634;
                                                }
                                                switch (Activity_FolderFile.this.export_select)
                                                {
                                                default: 
                                                  return;
                                                case 0: 
                                                  if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                                                    break label1027;
                                                  }
                                                  if (!Activity_FolderFile.this.findAndGotoApp("dropbox", (ArrayList)localObject1)) {
                                                    break label980;
                                                  }
                                                }
                                              } while (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1);
                                              Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                              return;
                                              new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                              {
                                                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                                {
                                                  paramAnonymous2DialogInterface.dismiss();
                                                }
                                              }).create().show();
                                              return;
                                              Activity_FolderFile.this.iapBuy.IAP_Buy();
                                              return;
                                              if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                                                break label1152;
                                              }
                                              if (!Activity_FolderFile.this.findAndGotoApp("evernote", (ArrayList)localObject1)) {
                                                break;
                                              }
                                            } while (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1);
                                            Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                            return;
                                            new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                            {
                                              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                              {
                                                paramAnonymous2DialogInterface.dismiss();
                                              }
                                            }).create().show();
                                            return;
                                            Activity_FolderFile.this.iapBuy.IAP_Buy();
                                            return;
                                            if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                                              break label1277;
                                            }
                                            if (!Activity_FolderFile.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject1)) {
                                              break;
                                            }
                                          } while (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1);
                                          Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                          return;
                                          new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          return;
                                          Activity_FolderFile.this.iapBuy.IAP_Buy();
                                          return;
                                          if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
                                          {
                                            paramAnonymousMessage = null;
                                            i = 0;
                                            while (i < j)
                                            {
                                              if (((ApplicationInfo)((List)localObject2).get(i)).packageName.equals("com.box.android")) {
                                                paramAnonymousMessage = (ApplicationInfo)((List)localObject2).get(i);
                                              }
                                              i += 1;
                                            }
                                            if (paramAnonymousMessage != null)
                                            {
                                              if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                                Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                              }
                                              paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                                              paramAnonymousMessage.setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                                              paramAnonymousMessage.setType("application/*");
                                              paramAnonymousMessage.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject1);
                                              Activity_FolderFile.this.startActivityForResult(paramAnonymousMessage, 3);
                                              return;
                                            }
                                            new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                            {
                                              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                              {
                                                paramAnonymous2DialogInterface.dismiss();
                                              }
                                            }).create().show();
                                            return;
                                          }
                                          Activity_FolderFile.this.iapBuy.IAP_Buy();
                                          return;
                                          if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                                            break label1623;
                                          }
                                          if (!Activity_FolderFile.this.findAndGotoApp("skydrive", (ArrayList)localObject1)) {
                                            break;
                                          }
                                        } while (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1);
                                        Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                        return;
                                        new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                        {
                                          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                          {
                                            paramAnonymous2DialogInterface.dismiss();
                                          }
                                        }).create().show();
                                        return;
                                        Activity_FolderFile.this.iapBuy.IAP_Buy();
                                        return;
                                      } while (!Activity_FolderFile.this.isListView1OrListview2);
                                      switch (Activity_FolderFile.this.export_select)
                                      {
                                      default: 
                                        return;
                                      case 0: 
                                        if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                                        {
                                          Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                                          Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                          Activity_FolderFile.this.editor.commit();
                                        }
                                        paramAnonymousMessage = new ArrayList();
                                        localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject3, 0);
                                      }
                                    } while (((List)localObject2).isEmpty());
                                    localObject2 = ((List)localObject2).iterator();
                                    if (((Iterator)localObject2).hasNext())
                                    {
                                      localObject3 = (ResolveInfo)((Iterator)localObject2).next();
                                      localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
                                      ((Intent)localObject4).setType("application/pdf");
                                      if (Activity_FolderFile.this.export_file.size() != 0) {
                                        if (Activity_FolderFile.this.export_file.size() == 1) {
                                          ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName().substring(0, ((File)Activity_FolderFile.this.export_file.get(0)).getName().length() - 4));
                                        }
                                      }
                                      while ((((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook")))
                                      {
                                        ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                                        ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                                        paramAnonymousMessage.add(localObject4);
                                        break;
                                        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                                        continue;
                                        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "");
                                      }
                                    }
                                    if (paramAnonymousMessage.size() > 0)
                                    {
                                      localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
                                      ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
                                      Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
                                      return;
                                    }
                                    Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                                    return;
                                    if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                                      break label2982;
                                    }
                                    if (!Activity_FolderFile.this.preferences.getString("email", "").equals("")) {
                                      break;
                                    }
                                    paramAnonymousMessage = LayoutInflater.from(Activity_FolderFile.this.context).inflate(2130903161, null);
                                    localObject2 = (EditText)paramAnonymousMessage.findViewById(2131755630);
                                    ((EditText)localObject2).setInputType(33);
                                    ((EditText)localObject2).setSelectAllOnFocus(true);
                                    ((EditText)localObject2).setText(Activity_FolderFile.this.preferences.getString("email", ""));
                                    new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296457)).setView(paramAnonymousMessage).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
                                    {
                                      @SuppressLint({"DefaultLocale"})
                                      public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                      {
                                        Object localObject1 = (EditText)paramAnonymousMessage.findViewById(2131755630);
                                        Object localObject2 = ((EditText)localObject1).getText().toString();
                                        if (Activity_FolderFile.this.isEmail((String)localObject2))
                                        {
                                          Activity_FolderFile.access$302(Activity_FolderFile.this, Activity_FolderFile.this.preferences.edit());
                                          Activity_FolderFile.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                                          Activity_FolderFile.this.editor.commit();
                                          paramAnonymous2DialogInterface.dismiss();
                                          paramAnonymous2DialogInterface = new ArrayList();
                                          localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                                          ((Intent)localObject1).setType("application/pdf");
                                          localObject1 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                                          if (!((List)localObject1).isEmpty())
                                          {
                                            localObject1 = ((List)localObject1).iterator();
                                            label514:
                                            while (((Iterator)localObject1).hasNext())
                                            {
                                              localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                                              Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                                              localIntent.setType("application/pdf");
                                              if (Activity_FolderFile.this.export_file.size() != 0) {
                                                if (Activity_FolderFile.this.export_file.size() == 1) {
                                                  localIntent.putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName().substring(0, ((File)Activity_FolderFile.this.export_file.get(0)).getName().length() - 4));
                                                }
                                              }
                                              for (;;)
                                              {
                                                if ((!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                                                  break label514;
                                                }
                                                localIntent.putExtra("android.intent.extra.STREAM", this.val$fileUris);
                                                localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("email", "") });
                                                localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                                                paramAnonymous2DialogInterface.add(localIntent);
                                                break;
                                                localIntent.putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                                                continue;
                                                localIntent.putExtra("android.intent.extra.SUBJECT", "");
                                              }
                                            }
                                            if (paramAnonymous2DialogInterface.size() > 0)
                                            {
                                              localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                                              ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                                              Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
                                              return;
                                            }
                                            Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                                            return;
                                          }
                                          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                                          return;
                                        }
                                        paramAnonymous2DialogInterface.dismiss();
                                        new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296530)).setMessage("Invalid Email Adress").setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296464), null).create().show();
                                      }
                                    }).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
                                    {
                                      public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                      {
                                        ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                                        paramAnonymous2DialogInterface.dismiss();
                                      }
                                    }).create().show();
                                  } while (Activity_FolderFile.this.mapp.isPad());
                                  new Timer().schedule(new TimerTask()
                                  {
                                    public void run()
                                    {
                                      ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
                                    }
                                  }, 100L);
                                  return;
                                  if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                                  {
                                    Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                                    Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                    Activity_FolderFile.this.editor.commit();
                                  }
                                  paramAnonymousMessage = new ArrayList();
                                  localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject3, 0);
                                  if (!((List)localObject2).isEmpty())
                                  {
                                    localObject2 = ((List)localObject2).iterator();
                                    if (((Iterator)localObject2).hasNext())
                                    {
                                      localObject3 = (ResolveInfo)((Iterator)localObject2).next();
                                      localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
                                      ((Intent)localObject4).setType("application/pdf");
                                      if (Activity_FolderFile.this.export_file.size() != 0) {
                                        if (Activity_FolderFile.this.export_file.size() == 1) {
                                          ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName().substring(0, ((File)Activity_FolderFile.this.export_file.get(0)).getName().length() - 4));
                                        }
                                      }
                                      while ((((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")))
                                      {
                                        ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                                        ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("email", "") });
                                        ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                                        paramAnonymousMessage.add(localObject4);
                                        break;
                                        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                                        continue;
                                        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "");
                                      }
                                    }
                                    if (paramAnonymousMessage.size() > 0)
                                    {
                                      localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
                                      ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
                                      Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
                                      return;
                                    }
                                    Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                                    return;
                                  }
                                  Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                                  return;
                                  Activity_FolderFile.this.iapBuy.IAP_Buy();
                                  return;
                                } while (Activity_FolderFile.idlist.size() > 1);
                                paramAnonymousMessage = new String[1];
                                paramAnonymousMessage[0] = "US";
                                j = 0;
                                i = 0;
                                while (i < paramAnonymousMessage.length)
                                {
                                  if (Locale.getDefault().getCountry().equals(paramAnonymousMessage[i])) {
                                    j = 1;
                                  }
                                  i += 1;
                                }
                                if (j == 0) {
                                  break label3666;
                                }
                                if ((!Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfax")) && (!Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfaxplus"))) {
                                  break label3625;
                                }
                                if ((!Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfax")) || (!Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfaxplus"))) {
                                  break;
                                }
                                if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                                {
                                  Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                                  Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                  Activity_FolderFile.this.editor.commit();
                                }
                                localObject2 = new Intent("android.intent.action.SEND");
                                ((Intent)localObject2).setType("application/pdf");
                                paramAnonymousMessage = new ArrayList();
                                localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                              } while (((List)localObject2).isEmpty());
                              localObject2 = ((List)localObject2).iterator();
                              while (((Iterator)localObject2).hasNext())
                              {
                                localObject3 = (ResolveInfo)((Iterator)localObject2).next();
                                localObject4 = new Intent("android.intent.action.SEND");
                                ((Intent)localObject4).setType("application/pdf");
                                if (Activity_FolderFile.this.export_file.size() != 0)
                                {
                                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName());
                                  if (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfax")) {
                                    continue;
                                  }
                                  if (((ArrayList)localObject1).size() != 1) {
                                    break label3410;
                                  }
                                  ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
                                }
                                for (;;)
                                {
                                  ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                                  paramAnonymousMessage.add(localObject4);
                                  break;
                                  ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner");
                                  break label3322;
                                  ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                                }
                              }
                              if (paramAnonymousMessage.size() > 0)
                              {
                                localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Fax");
                                ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
                                Activity_FolderFile.this.startActivity((Intent)localObject1);
                                return;
                              }
                              Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Error: Cannot open or share created PDF report.", 0).show();
                              return;
                              if (Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfax"))
                              {
                                if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                  Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                                }
                                Util.findAndGotoApp1(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfax", (ArrayList)localObject1, Activity_FolderFile.this.getfilesizeLength(), Activity_FolderFile.this.export_size);
                                return;
                              }
                              if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                Activity_FolderFile.access$2902(Activity_FolderFile.this, true);
                              }
                              Util.findAndGotoApp1(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfaxplus", (ArrayList)localObject1, Activity_FolderFile.this.getfilesizeLength(), Activity_FolderFile.this.export_size);
                              return;
                              if (Activity_FolderFile.this.preferences.getInt("newversion_1.2.5_faxpro", -1) == 0)
                              {
                                Util.showGooglePlayFax_pro(Activity_FolderFile.this.activity_folderFile);
                                return;
                              }
                              Util.showGooglePlayFax(Activity_FolderFile.this.activity_folderFile);
                              return;
                              if (!Util.isPkgInstalled(Activity_FolderFile.this.activity_folderFile, "com.appxy.tinyfaxintl")) {
                                break;
                              }
                              if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                              {
                                Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                                Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                Activity_FolderFile.this.editor.commit();
                              }
                              localObject2 = new Intent("android.intent.action.SEND");
                              ((Intent)localObject2).setType("application/pdf");
                              paramAnonymousMessage = new ArrayList();
                              localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                            } while (((List)localObject2).isEmpty());
                            localObject2 = ((List)localObject2).iterator();
                            while (((Iterator)localObject2).hasNext())
                            {
                              localObject3 = (ResolveInfo)((Iterator)localObject2).next();
                              localObject4 = new Intent("android.intent.action.SEND");
                              ((Intent)localObject4).setType("application/pdf");
                              if (Activity_FolderFile.this.export_file.size() != 0)
                              {
                                ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName());
                                if (!((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfaxintl")) {
                                  continue;
                                }
                                if (((ArrayList)localObject1).size() != 1) {
                                  break label3974;
                                }
                                ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
                              }
                              for (;;)
                              {
                                ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
                                paramAnonymousMessage.add(localObject4);
                                break;
                                ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Tiny Scanner");
                                break label3886;
                                ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
                              }
                            }
                            if (paramAnonymousMessage.size() > 0)
                            {
                              localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Fax");
                              ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
                              Activity_FolderFile.this.startActivity((Intent)localObject1);
                              return;
                            }
                            Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Error: Cannot open or share created PDF report.", 0).show();
                            return;
                            Util.showGooglePlayFax11(Activity_FolderFile.this.activity_folderFile);
                            return;
                          } while (Activity_FolderFile.idlist.size() > 1);
                          if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                          {
                            Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                            Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                            Activity_FolderFile.this.editor.commit();
                          }
                          paramAnonymousMessage = new Intent("android.intent.action.VIEW");
                          paramAnonymousMessage.setDataAndType((Uri)((ArrayList)localObject1).get(0), "application/pdf");
                          if (Build.VERSION.SDK_INT >= 24) {
                            paramAnonymousMessage.setFlags(3);
                          }
                          for (;;)
                          {
                            Activity_FolderFile.this.startActivityForResult(Intent.createChooser(paramAnonymousMessage, "Open in"), 3);
                            return;
                            paramAnonymousMessage.setFlags(67108864);
                          }
                          if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                            break;
                          }
                          Activity_FolderFile.this.editor.putInt("pdf_pages", Util.getPdfPages(((File)Activity_FolderFile.this.export_file.get(0)).getPath()));
                          Activity_FolderFile.this.editor.putString("pdf_path", ((File)Activity_FolderFile.this.export_file.get(0)).getPath());
                          Activity_FolderFile.this.editor.putString("pdf_name", ((File)Activity_FolderFile.this.export_file.get(0)).getName().replace(".pdf", ""));
                          Activity_FolderFile.this.editor.commit();
                        } while (Build.VERSION.SDK_INT < 19);
                        try
                        {
                          this.printManager = ((PrintManager)Activity_FolderFile.this.activity_folderFile.getSystemService("print"));
                          this.printManager.print(Activity_FolderFile.this.preferences.getString("pdf_name", ""), new MyPrintDocumentAdapter(Activity_FolderFile.this.preferences), null);
                          return;
                        }
                        catch (Exception paramAnonymousMessage)
                        {
                          Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Print error!", 0).show();
                          return;
                        }
                        Activity_FolderFile.this.iapBuy.IAP_Buy();
                        return;
                        if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                          break label5012;
                        }
                        if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1)
                        {
                          Activity_FolderFile.access$3102(Activity_FolderFile.this, true);
                          Activity_FolderFile.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                          Activity_FolderFile.this.editor.commit();
                        }
                        if (Activity_FolderFile.idlist.size() <= 1) {
                          break;
                        }
                        paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                        paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", "TinyScannerPro");
                        paramAnonymousMessage.setType("application/pdf");
                        localObject2 = new ArrayList();
                        paramAnonymousMessage = Activity_FolderFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
                      } while (paramAnonymousMessage.isEmpty());
                      localObject3 = paramAnonymousMessage.iterator();
                      if (((Iterator)localObject3).hasNext())
                      {
                        localObject4 = (ResolveInfo)((Iterator)localObject3).next();
                        if (Activity_FolderFile.idlist.size() > 1)
                        {
                          paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                          paramAnonymousMessage.setType("application/pdf");
                          if (Activity_FolderFile.this.export_file.size() == 0) {
                            break label4908;
                          }
                          paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((File)Activity_FolderFile.this.export_file.get(0)).getName());
                          if ((((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("dropbox")) || (((ResolveInfo)localObject4).activityInfo.name.toLowerCase().contains("evernote")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("com.box.android")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("skydrive"))) {
                            break label4920;
                          }
                          if (((ArrayList)localObject1).size() != 1) {
                            break label4922;
                          }
                          paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject1).get(0));
                        }
                        for (;;)
                        {
                          paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
                          ((List)localObject2).add(paramAnonymousMessage);
                          break label4634;
                          paramAnonymousMessage = new Intent("android.intent.action.SEND");
                          break;
                          paramAnonymousMessage = new Intent("android.intent.action.SEND");
                          break label4679;
                          paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", "Tiny Scanner Pro");
                          break label4729;
                          break label4634;
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
                      Activity_FolderFile.this.iapBuy.IAP_Buy();
                      return;
                      if ((Activity_FolderFile.this.progressDialog != null) && (Activity_FolderFile.this.progressDialog.isShowing())) {
                        Activity_FolderFile.this.progressDialog.dismiss();
                      }
                      Activity_FolderFile.access$2302(Activity_FolderFile.this, null);
                      Activity_FolderFile.access$2402(Activity_FolderFile.this, null);
                      localObject1 = new ArrayList();
                      paramAnonymousMessage = new ArrayList();
                      if (Activity_FolderFile.this.export_size == 0)
                      {
                        i = 0;
                        while (i < Activity_FolderFile.idlist.size())
                        {
                          localObject2 = (Photo_info)Activity_FolderFile.idlist.get(i);
                          localObject2 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName()).listFiles(new Activity_FolderFile.MyFilter(Activity_FolderFile.this, ".jpg"));
                          if ((localObject2 != null) && (localObject2.length > 0))
                          {
                            j = 0;
                            if (j < localObject2.length)
                            {
                              paramAnonymousMessage.add(localObject2[j]);
                              if (Build.VERSION.SDK_INT >= 24) {
                                ((ArrayList)localObject1).add(FileProvider.getUriForFile(Activity_FolderFile.this.context, Activity_FolderFile.this.getPackageName() + ".fileprovider", localObject2[j]));
                              }
                              for (;;)
                              {
                                j += 1;
                                break;
                                ((ArrayList)localObject1).add(Uri.fromFile(localObject2[j]));
                              }
                            }
                          }
                          i += 1;
                        }
                      }
                      localObject2 = new File(Activity_FolderFile.this.compressJpeg_Path).listFiles(new Activity_FolderFile.MyFilter(Activity_FolderFile.this, ".jpg"));
                      if ((localObject2 != null) && (localObject2.length > 0))
                      {
                        i = 0;
                        if (i < localObject2.length)
                        {
                          paramAnonymousMessage.add(localObject2[i]);
                          if (Build.VERSION.SDK_INT >= 24) {
                            ((ArrayList)localObject1).add(FileProvider.getUriForFile(Activity_FolderFile.this.context, Activity_FolderFile.this.getPackageName() + ".fileprovider", localObject2[i]));
                          }
                          for (;;)
                          {
                            i += 1;
                            break;
                            ((ArrayList)localObject1).add(Uri.fromFile(localObject2[i]));
                          }
                        }
                      }
                      localObject2 = Activity_FolderFile.this.getPackageManager().getInstalledApplications(0);
                      j = ((List)localObject2).size();
                      localObject3 = new Intent("android.intent.action.SEND_MULTIPLE");
                      ((Intent)localObject3).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                      ((Intent)localObject3).setType("image/jpeg");
                      if (Activity_FolderFile.this.isListView1OrListview2) {
                        break label6159;
                      }
                      switch (Activity_FolderFile.this.export_select)
                      {
                      default: 
                        return;
                      case 0: 
                        if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                          break label5647;
                        }
                      }
                    } while (Activity_FolderFile.this.findAndGotoApp("dropbox", (ArrayList)localObject1));
                    new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                      {
                        paramAnonymous2DialogInterface.dismiss();
                      }
                    }).create().show();
                    return;
                    Activity_FolderFile.this.iapBuy.IAP_Buy();
                    return;
                    if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                      break;
                    }
                  } while (Activity_FolderFile.this.findAndGotoApp("evernote", (ArrayList)localObject1));
                  new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  return;
                  Activity_FolderFile.this.iapBuy.IAP_Buy();
                  return;
                  if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                    break;
                  }
                } while (Activity_FolderFile.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject1));
                new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    paramAnonymous2DialogInterface.dismiss();
                  }
                }).create().show();
                return;
                Activity_FolderFile.this.iapBuy.IAP_Buy();
                return;
                if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
                {
                  paramAnonymousMessage = null;
                  i = 0;
                  while (i < j)
                  {
                    if (((ApplicationInfo)((List)localObject2).get(i)).packageName.equals("com.box.android")) {
                      paramAnonymousMessage = (ApplicationInfo)((List)localObject2).get(i);
                    }
                    i += 1;
                  }
                  if (paramAnonymousMessage != null)
                  {
                    paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
                    paramAnonymousMessage.setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                    paramAnonymousMessage.setType("application/*");
                    paramAnonymousMessage.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject1);
                    Activity_FolderFile.this.startActivityForResult(paramAnonymousMessage, 3);
                    return;
                  }
                  new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                    }
                  }).create().show();
                  return;
                }
                Activity_FolderFile.this.iapBuy.IAP_Buy();
                return;
                if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                  break;
                }
              } while (Activity_FolderFile.this.findAndGotoApp("skydrive", (ArrayList)localObject1));
              new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                }
              }).create().show();
              return;
              Activity_FolderFile.this.iapBuy.IAP_Buy();
              return;
            } while (!Activity_FolderFile.this.isListView1OrListview2);
            switch (Activity_FolderFile.this.export_select)
            {
            default: 
              return;
            case 0: 
              paramAnonymousMessage = new ArrayList();
              localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject3, 0);
            }
          } while (((List)localObject2).isEmpty());
          localObject2 = ((List)localObject2).iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localObject3 = (ResolveInfo)((Iterator)localObject2).next();
            localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject4).setType("image/jpeg");
            if (Activity_FolderFile.idlist.size() != 0) {
              if (Activity_FolderFile.idlist.size() == 1) {
                ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
              }
            }
            while ((((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("outlook")))
            {
              ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
              ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
              paramAnonymousMessage.add(localObject4);
              break;
              ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
              continue;
              ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "");
            }
          }
          if (paramAnonymousMessage.size() > 0)
          {
            localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
            Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
            return;
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
          return;
          if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
            break label7312;
          }
          if (!Activity_FolderFile.this.preferences.getString("email", "").equals("")) {
            break;
          }
          paramAnonymousMessage = LayoutInflater.from(Activity_FolderFile.this.context).inflate(2130903161, null);
          localObject2 = (EditText)paramAnonymousMessage.findViewById(2131755630);
          ((EditText)localObject2).setInputType(33);
          ((EditText)localObject2).setSelectAllOnFocus(true);
          ((EditText)localObject2).setText(Activity_FolderFile.this.preferences.getString("email", ""));
          new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296457)).setView(paramAnonymousMessage).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
          {
            @SuppressLint({"DefaultLocale"})
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              Object localObject1 = (EditText)paramAnonymousMessage.findViewById(2131755630);
              Object localObject2 = ((EditText)localObject1).getText().toString();
              if (Activity_FolderFile.this.isEmail((String)localObject2))
              {
                Activity_FolderFile.access$302(Activity_FolderFile.this, Activity_FolderFile.this.preferences.edit());
                Activity_FolderFile.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                Activity_FolderFile.this.editor.commit();
                paramAnonymous2DialogInterface.dismiss();
                paramAnonymous2DialogInterface = new ArrayList();
                localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                ((Intent)localObject1).setType("image/jpeg");
                localObject1 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                if (!((List)localObject1).isEmpty())
                {
                  localObject1 = ((List)localObject1).iterator();
                  label462:
                  while (((Iterator)localObject1).hasNext())
                  {
                    localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                    Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                    localIntent.setType("image/jpeg");
                    if (Activity_FolderFile.idlist.size() != 0) {
                      if (Activity_FolderFile.idlist.size() == 1) {
                        localIntent.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
                      }
                    }
                    for (;;)
                    {
                      if ((!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                        break label462;
                      }
                      localIntent.putExtra("android.intent.extra.STREAM", this.val$file_imageUris);
                      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("email", "") });
                      localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                      paramAnonymous2DialogInterface.add(localIntent);
                      break;
                      localIntent.putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
                      continue;
                      localIntent.putExtra("android.intent.extra.SUBJECT", "");
                    }
                  }
                  if (paramAnonymous2DialogInterface.size() > 0)
                  {
                    localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                    ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                    Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
                    return;
                  }
                  Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                  return;
                }
                Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
                return;
              }
              paramAnonymous2DialogInterface.dismiss();
              new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296530)).setMessage("Invalid Email Adress").setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296464), null).create().show();
            }
          }).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
              paramAnonymous2DialogInterface.dismiss();
            }
          }).create().show();
        } while (Activity_FolderFile.this.mapp.isPad());
        new Timer().schedule(new TimerTask()
        {
          public void run()
          {
            ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
          }
        }, 100L);
        return;
        paramAnonymousMessage = new ArrayList();
        localObject2 = Activity_FolderFile.this.getPackageManager().queryIntentActivities((Intent)localObject3, 0);
        if (!((List)localObject2).isEmpty())
        {
          localObject2 = ((List)localObject2).iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localObject3 = (ResolveInfo)((Iterator)localObject2).next();
            localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
            ((Intent)localObject4).setType("image/jpeg");
            if (Activity_FolderFile.idlist.size() != 0) {
              if (Activity_FolderFile.idlist.size() == 1) {
                ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
              }
            }
            while ((((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject3).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject3).activityInfo.name.toLowerCase().contains("blue")))
            {
              ((Intent)localObject4).putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
              ((Intent)localObject4).putExtra("android.intent.extra.EMAIL", new String[] { Activity_FolderFile.this.preferences.getString("email", "") });
              ((Intent)localObject4).setPackage(((ResolveInfo)localObject3).activityInfo.packageName);
              paramAnonymousMessage.add(localObject4);
              break;
              ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "Multiple Documents");
              continue;
              ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "");
            }
          }
          if (paramAnonymousMessage.size() > 0)
          {
            localObject1 = Intent.createChooser((Intent)paramAnonymousMessage.remove(0), "Export");
            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousMessage.toArray(new Parcelable[0]));
            Activity_FolderFile.this.startActivityForResult((Intent)localObject1, 3);
            return;
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
          return;
        }
        Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296373), 0).show();
        return;
        Activity_FolderFile.this.iapBuy.IAP_Buy();
        return;
        new Thread(new Runnable()
        {
          public void run()
          {
            int i = 0;
            for (;;)
            {
              if (i < paramAnonymousMessage.size())
              {
                File localFile1 = new File(((File)paramAnonymousMessage.get(i)).getPath());
                File localFile2 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TinyScan");
                if (!localFile2.exists()) {
                  localFile2.mkdirs();
                }
                localFile2 = new File(localFile2.getPath() + "/" + localFile1.getName());
                try
                {
                  Activity_FolderFile.this.copy(localFile1, localFile2);
                  MediaScannerConnection.scanFile(Activity_FolderFile.this.getApplicationContext(), new String[] { localFile2.getAbsolutePath() }, null, null);
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
            Activity_FolderFile.this.handler.sendMessage(localMessage);
          }
        }).start();
        return;
        if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() != 1)) {
          break label7776;
        }
        if (((ArrayList)localObject1).size() <= 1) {
          break;
        }
        paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
        paramAnonymousMessage.setType("image/jpeg");
        localObject2 = new ArrayList();
        paramAnonymousMessage = Activity_FolderFile.this.getPackageManager().queryIntentActivities(paramAnonymousMessage, 0);
      } while (paramAnonymousMessage.isEmpty());
      Object localObject3 = paramAnonymousMessage.iterator();
      label7438:
      label7480:
      label7685:
      label7697:
      for (;;)
      {
        if (!((Iterator)localObject3).hasNext()) {
          break label7699;
        }
        localObject4 = (ResolveInfo)((Iterator)localObject3).next();
        if (((ArrayList)localObject1).size() > 1)
        {
          paramAnonymousMessage = new Intent("android.intent.action.SEND_MULTIPLE");
          paramAnonymousMessage.setType("image/jpeg");
          if (Activity_FolderFile.idlist.size() == 0) {
            break label7685;
          }
          paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", ((Photo_info)Activity_FolderFile.idlist.get(0)).getName());
        }
        for (;;)
        {
          if ((((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("dropbox")) || (((ResolveInfo)localObject4).activityInfo.name.toLowerCase().contains("evernote")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("com.box.android")) || (((ResolveInfo)localObject4).activityInfo.packageName.toLowerCase().contains("skydrive"))) {
            break label7697;
          }
          paramAnonymousMessage.putExtra("android.intent.extra.STREAM", (Serializable)localObject1);
          paramAnonymousMessage.setPackage(((ResolveInfo)localObject4).activityInfo.packageName);
          ((List)localObject2).add(paramAnonymousMessage);
          break label7438;
          paramAnonymousMessage = new Intent("android.intent.action.SEND");
          break;
          paramAnonymousMessage = new Intent("android.intent.action.SEND");
          break label7480;
          paramAnonymousMessage.putExtra("android.intent.extra.SUBJECT", "Tiny Scanner Pro");
        }
      }
      label7699:
      if (((List)localObject2).size() > 0)
      {
        paramAnonymousMessage = Intent.createChooser((Intent)((List)localObject2).remove(0), "Share JPG file");
        paramAnonymousMessage.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
        Activity_FolderFile.this.startActivity(paramAnonymousMessage);
        return;
      }
      Toast.makeText(Activity_FolderFile.this.activity_folderFile, "Error: Cannot open or share created JPG report.", 0).show();
      return;
      label7776:
      Activity_FolderFile.this.iapBuy.IAP_Buy();
    }
  };
  private IAPBuy iapBuy;
  private LayoutInflater inflater;
  boolean isFirst = true;
  private boolean isListView1OrListview2 = false;
  boolean isSelect = false;
  private boolean isclick_cloud = false;
  private boolean isclick_systemdialog = false;
  boolean islongclick = false;
  private View.OnClickListener itemsOnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView) {}
  };
  private ListView list;
  private int list_type;
  private LinearLayout listphotos;
  private MyDbHelper mDbHelper;
  private Thread mThread;
  private GridAdapter madapter;
  private ListAdapter madapter2;
  private SelectPicPopupWindow menuWindow;
  ArrayList<Photo_item> mlist = null;
  private String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
  private ImageView moveto;
  View.OnClickListener myOnClickListener = new View.OnClickListener()
  {
    private String[] country;
    
    @SuppressLint({"InflateParams"})
    public void onClick(final View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      }
      do
      {
        do
        {
          return;
          paramAnonymousView = Activity_FolderFile.this.getResources().getString(2131296363);
          new AlertDialog.Builder(Activity_FolderFile.this.context).setMessage(paramAnonymousView).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296464), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
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
                      Activity_FolderFile.mlist2.remove(Activity_FolderFile.idlist.get(i));
                      Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)((Photo_info)Activity_FolderFile.idlist.get(i)).getImage_name().get(0));
                    }
                    i += 1;
                  }
                  Object localObject = new Message();
                  ((Message)localObject).what = 0;
                  Activity_FolderFile.this.handler.sendMessage((Message)localObject);
                }
              }).start();
            }
          }).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
          return;
          localObject1 = Activity_FolderFile.this.getLayoutInflater().inflate(2130903078, null);
          paramAnonymousView = Activity_Main.getActivity_main().getFolders(Activity_FolderFile.this.oldname);
          Object localObject2 = new Photo_info();
          ((Photo_info)localObject2).setShowname(Activity_FolderFile.this.getResources().getString(2131296455));
          paramAnonymousView.add(localObject2);
          localObject2 = new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296454)).setView((View)localObject1).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), null).create();
          ((AlertDialog)localObject2).show();
          localObject1 = (ListView)((View)localObject1).findViewById(2131755328);
          ((ListView)localObject1).setAdapter(new CopyAdapter(Activity_FolderFile.this.context, paramAnonymousView, true));
          ((ListView)localObject1).setOnItemClickListener(new AdapterView.OnItemClickListener()
          {
            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, final int paramAnonymous2Int, long paramAnonymous2Long)
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
                  if (paramAnonymous2Int == Activity_FolderFile.9.3.this.val$sslist2.size() - 1)
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
                    Activity_FolderFile.9.3.this.val$mDialog.dismiss();
                    return;
                    j = 0;
                    if (j < Activity_FolderFile.idlist.size())
                    {
                      localPhoto_info = (Photo_info)Activity_FolderFile.idlist.get(j);
                      localBitmapDrawable = Activity_FolderFile.this.mapp.getBitmapFromMemCache("main" + (String)localPhoto_info.getImage_name().get(0));
                      Activity_FolderFile.this.mapp.getmMemoryCache().remove("main" + (String)localPhoto_info.getImage_name().get(0));
                      localFile = new File(Activity_FolderFile.this.folderPath + "/" + localPhoto_info.getName());
                      localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName()).listFiles();
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
                          for (localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName() + "/" + localPhoto_info.getName() + "(" + 2 + ")"); ((File)localObject1).exists(); localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName() + "/" + localPhoto_info.getName() + "(" + i + ")")) {
                            i += 1;
                          }
                          localFile.renameTo((File)localObject1);
                        }
                      }
                      for (;;)
                      {
                        Activity_FolderFile.this.mapp.addBitmapToMemoryCache("main" + Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName() + "/" + ((File)localObject1).getName() + "/" + ((String)localPhoto_info.getImage_name().get(0)).substring(((String)localPhoto_info.getImage_name().get(0)).lastIndexOf("/") + 1, ((String)localPhoto_info.getImage_name().get(0)).length()), localBitmapDrawable);
                        j += 1;
                        break;
                        Activity_FolderFile.this.updateNameToDb(localPhoto_info.getName(), localPhoto_info.getShowname() + "(2)");
                        localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName() + "/" + localPhoto_info.getName());
                        localFile.renameTo((File)localObject1);
                        continue;
                        localObject1 = new File(Activity_FolderFile.this.root_Path4 + ((Photo_info)Activity_FolderFile.9.3.this.val$sslist2.get(paramAnonymous2Int)).getName() + "/" + localPhoto_info.getName());
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
          return;
          if (Activity_FolderFile.idlist.size() == 1)
          {
            Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131296453));
            return;
          }
          paramAnonymousView = Activity_FolderFile.this.inflater.inflate(2130903161, null);
          localObject1 = (EditText)paramAnonymousView.findViewById(2131755630);
          ((EditText)localObject1).setSelectAllOnFocus(true);
          ((EditText)localObject1).setText(((Photo_info)Activity_FolderFile.idlist.get(0)).getShowname());
          paramAnonymousView = new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296451)).setView(paramAnonymousView).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
          {
            @SuppressLint({"SimpleDateFormat"})
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              Object localObject2 = (EditText)paramAnonymousView.findViewById(2131755630);
              if (((EditText)localObject2).getText().toString().trim().equals(""))
              {
                Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131296410));
                return;
              }
              paramAnonymous2Int = 0;
              Object localObject1;
              Object localObject3;
              int i;
              while (paramAnonymous2Int < Activity_FolderFile.idlist.size())
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
                if (paramAnonymous2Int != 0)
                {
                  localObject3 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(paramAnonymous2Int)).getName());
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
                paramAnonymous2Int += 1;
              }
              if (((EditText)localObject2).getText().toString().equals(((Photo_info)Activity_FolderFile.idlist.get(0)).getShowname()))
              {
                localObject1 = new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName()).list(new Activity_FolderFile.MyFilter2(Activity_FolderFile.this));
                if (localObject1 != null)
                {
                  i = localObject1.length;
                  paramAnonymous2Int = 0;
                  while (paramAnonymous2Int < i)
                  {
                    localObject2 = localObject1[paramAnonymous2Int];
                    new File(Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.idlist.get(0)).getName() + "/" + (String)localObject2).delete();
                    paramAnonymous2Int += 1;
                  }
                }
              }
              else
              {
                localObject1 = ((EditText)localObject2).getText().toString().trim();
                localObject2 = ((EditText)localObject2).getText().toString().trim();
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
                  paramAnonymous2Int = 0;
                  while (paramAnonymous2Int < i)
                  {
                    localObject3 = localObject2[paramAnonymous2Int];
                    new File(((File)localObject1).getPath() + "/" + (String)localObject3).delete();
                    paramAnonymous2Int += 1;
                  }
                  localObject3 = new Timestamp(System.currentTimeMillis());
                  localObject3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject3);
                  localObject2 = ((String)localObject2).replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject3;
                  Activity_FolderFile.this.saveNameToDb((String)localObject1, (String)localObject2);
                  localObject1 = localObject2;
                }
              }
              Activity_FolderFile.this.unselected();
              Activity_FolderFile.this.relist();
              paramAnonymous2DialogInterface.dismiss();
            }
          }).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.dismiss();
            }
          }).create();
          paramAnonymousView.show();
        } while (Activity_FolderFile.this.mapp.isPad());
        new Timer().schedule(new TimerTask()
        {
          public void run()
          {
            ((InputMethodManager)paramAnonymousView.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
          }
        }, 100L);
        return;
        Activity_FolderFile.this.unselected();
        return;
        this.country = new String[] { "US", "CA", "GB", "JP", "FR", "DE", "AR", "BR", "IL", "IN", "PT", "IT", "HK", "AU", "AT", "BE", "DK", "GR", "IE", "LU", "NL", "NO", "RO", "ES", "ZA", "SE", "CH" };
        int i = 0;
        while (i < this.country.length)
        {
          if (Locale.getDefault().getCountry().equals(this.country[i])) {
            Activity_FolderFile.this.mapp.setIsContainCountry(true);
          }
          i += 1;
        }
        if (Activity_FolderFile.this.mapp.isPad())
        {
          Activity_FolderFile.this.sharepadAlterdialog();
          return;
        }
        Object localObject1 = new DisplayMetrics();
        Activity_FolderFile.this.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
        Activity_FolderFile.access$902(Activity_FolderFile.this, new Activity_FolderFile.SelectPicPopupWindow(Activity_FolderFile.this, Activity_FolderFile.this, Activity_FolderFile.this.itemsOnClick, ((DisplayMetrics)localObject1).widthPixels));
        Activity_FolderFile.this.menuWindow.showAtLocation(paramAnonymousView, 81, 0, 0);
        return;
        if (Activity_FolderFile.idlist.size() > 1)
        {
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296466), 0).show();
          return;
        }
        paramAnonymousView = Activity_FolderFile.this.inflater.inflate(2130903161, null);
        localObject1 = (EditText)paramAnonymousView.findViewById(2131755630);
        ((EditText)localObject1).setSelectAllOnFocus(true);
        ((EditText)localObject1).setText(((Photo_info)Activity_FolderFile.idlist.get(0)).getShowname());
        new AlertDialog.Builder(Activity_FolderFile.this.context).setTitle(Activity_FolderFile.this.getResources().getString(2131296485)).setView(paramAnonymousView).setPositiveButton(Activity_FolderFile.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
        {
          @SuppressLint({"SimpleDateFormat"})
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Object localObject1 = (EditText)paramAnonymousView.findViewById(2131755630);
            if (((EditText)localObject1).getText().toString().equals(((Photo_info)Activity_FolderFile.idlist.get(0)).getName()))
            {
              Activity_FolderFile.this.unselected();
              paramAnonymous2DialogInterface.dismiss();
              return;
            }
            if (((EditText)localObject1).getText().toString().trim().equals(""))
            {
              Activity_FolderFile.this.showToast(Activity_FolderFile.this.getResources().getString(2131296410));
              return;
            }
            String str = ((EditText)localObject1).getText().toString().trim();
            localObject1 = ((EditText)localObject1).getText().toString().trim();
            Photo_info localPhoto_info = (Photo_info)Activity_FolderFile.idlist.get(0);
            if (Activity_FolderFile.this.checkFilename(str))
            {
              paramAnonymous2DialogInterface.dismiss();
              ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
              paramAnonymous2DialogInterface = new Message();
              paramAnonymous2DialogInterface.what = 31;
              Activity_FolderFile.this.handler.sendMessageDelayed(paramAnonymous2DialogInterface, 100L);
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
              paramAnonymous2DialogInterface.dismiss();
              return;
              localObject2 = new Timestamp(System.currentTimeMillis());
              localObject2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject2);
              localObject1 = ((String)localObject1).replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject2;
              Activity_FolderFile.this.saveNameToDb(str, (String)localObject1);
            }
          }
        }).setNegativeButton(Activity_FolderFile.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        }).create().show();
      } while (Activity_FolderFile.this.mapp.isPad());
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          ((InputMethodManager)Activity_FolderFile.this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
        }
      }, 100L);
    }
  };
  private String oldname;
  private HorizontalListView pad_listview1;
  private HorizontalListView pad_listview2;
  private ArrayList<HashMap<String, Object>> padexportlist1;
  private ArrayList<HashMap<String, Object>> padexportlist2;
  private List<HashMap<String, Object>> pdf_file_name;
  private boolean pdf_or_jpg = true;
  private SharedPreferences preferences;
  private Dialog progressDialog;
  private String root_Path2 = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan_PDF/";
  private String root_Path3 = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan/Documents/";
  private String root_Path4 = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan/Folders/";
  TextView selecttext;
  private ImageView share;
  private AlertDialog shareDialog;
  private int sort_type;
  TextView title;
  private EditText titleedit;
  
  public Activity_FolderFile() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
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
          if (idlist.size() > 1) {
            localHashMap.put("isEnable", Boolean.valueOf(false));
          }
        }
        else
        {
          label236:
          if (i != 3) {
            break label280;
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
          break label236;
          label280:
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
          label420:
          if ((i == 2) || (i == 3) || (i == 4))
          {
            if (i == 3) {
              localHashMap.put("id", Integer.valueOf(6));
            }
            if (i == 4) {
              localHashMap.put("id", Integer.valueOf(3));
            }
            if (idlist.size() <= 1) {
              break label538;
            }
            localHashMap.put("isEnable", Boolean.valueOf(false));
          }
          label494:
          if (i != 3) {
            break label553;
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
          break label420;
          label538:
          localHashMap.put("isEnable", Boolean.valueOf(true));
          break label494;
          label553:
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
  
  private void addjpg_listdata()
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
          localHashMap.put("isEnable", Boolean.valueOf(true));
          if (i != 1) {
            break label357;
          }
          localHashMap.put("isPro", Boolean.valueOf(true));
        }
        for (;;)
        {
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
  
  private boolean checkGooglePlayServicesAvailable()
  {
    return !GooglePlayServicesUtil.isUserRecoverableError(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.context));
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
  
  private SharedPreferences getSharedPreferencesForCurrentUser()
  {
    return getSharedPreferences(this.mapp.getCurrentUser(), 0);
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
          Object localObject = Activity_FolderFile.this.getPackageManager().getInstalledApplications(0);
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
            Activity_FolderFile.this.startActivity((Intent)localObject);
          }
          for (;;)
          {
            if ((this.val$mDialog != null) && (this.val$mDialog.isShowing())) {
              this.val$mDialog.dismiss();
            }
            return;
            Activity_FolderFile.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscanner&hl=en")));
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
    }
  }
  
  private void selectMothed()
  {
    this.islongclick = true;
    if (this.list_type == 0)
    {
      this.madapter.isse = true;
      this.madapter.notifyDataSetChanged();
    }
    for (;;)
    {
      selected();
      changeView();
      return;
      this.madapter2.isse = true;
      this.madapter2.notifyDataSetChanged();
    }
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
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.progressDialog.setCancelable(true);
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
          localObject1 = (Photo_info)Activity_FolderFile.idlist.get(i);
          localObject1 = new File(Activity_FolderFile.this.folderPath + ((Photo_info)Activity_FolderFile.idlist.get(i)).getName());
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
    int j;
    int i;
    Iterator localIterator;
    if (idlist.size() > 1)
    {
      if (this.mapp.isPad())
      {
        this.folderedit.setEnabled(false);
        this.folderedit.setImageResource(2130837970);
      }
      j = 0;
      i = 0;
      localIterator = idlist.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label120;
      }
      if (((Photo_info)localIterator.next()).isFolder())
      {
        i = 1;
        continue;
        if (!this.mapp.isPad()) {
          break;
        }
        this.folderedit.setEnabled(true);
        this.folderedit.setImageResource(2130837969);
        break;
      }
      j += 1;
    }
    label120:
    if (i != 0)
    {
      this.moveto.setEnabled(false);
      this.moveto.setImageResource(2130837991);
      if ((j <= 1) || (i != 0)) {
        break label284;
      }
      this.getall.setEnabled(true);
      this.getall.setImageResource(2130837791);
    }
    for (;;)
    {
      if (idlist.size() != 0) {
        break label305;
      }
      this.share.setEnabled(false);
      this.share.setImageResource(2130837796);
      if (this.mapp.isPad())
      {
        this.folderedit.setEnabled(false);
        this.folderedit.setImageResource(2130837970);
      }
      this.moveto.setEnabled(false);
      this.moveto.setImageResource(2130837991);
      this.delete.setEnabled(false);
      this.delete.setImageResource(2130837981);
      return;
      this.moveto.setEnabled(true);
      this.moveto.setImageResource(2130837989);
      break;
      label284:
      this.getall.setEnabled(false);
      this.getall.setImageResource(2130837792);
    }
    label305:
    this.share.setEnabled(true);
    this.share.setImageResource(2130837795);
    this.delete.setEnabled(true);
    this.delete.setImageResource(2130837980);
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
  
  public boolean checkName(String paramString)
  {
    return (!paramString.contains("*")) && (!paramString.contains("/")) && (!paramString.contains("\\")) && (!paramString.contains("\"")) && (!paramString.contains("?")) && (!paramString.contains(":")) && (!paramString.contains("|")) && (!paramString.contains("<")) && (!paramString.contains(">"));
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
  
  protected void clickOne(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    Log.i("TAG", "111=" + paramInt);
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
    if (this.export_size == 0)
    {
      paramArrayList = new MyFilter2();
      paramInt = 0;
      if (paramInt < idlist.size())
      {
        File localFile = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
        Object localObject = localFile.listFiles(paramArrayList);
        if ((localObject != null) && (localObject.length > 0)) {
          localObject = new File(localFile.getPath() + "/" + localFile.getName() + ".pdf");
        }
        for (;;)
        {
          try
          {
            if ((Util.getFileSize((File)localObject) == 0L) || (!Util.isOpenPdf(localFile.getPath() + "/" + localFile.getName() + ".pdf")))
            {
              localObject = new HashMap();
              ((HashMap)localObject).put("name", localFile.getName());
              ((HashMap)localObject).put("isfolder", Boolean.valueOf(false));
              ((HashMap)localObject).put("path", localFile.getPath());
              this.pdf_file_name.add(localObject);
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            continue;
          }
          paramInt += 1;
          break;
          localObject = new HashMap();
          ((HashMap)localObject).put("name", localException.getName());
          ((HashMap)localObject).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject).put("path", localException.getPath());
          this.pdf_file_name.add(localObject);
        }
      }
      if (this.pdf_file_name.size() > 0) {
        createPDF();
      }
    }
    do
    {
      return;
      paramArrayList = new Message();
      paramArrayList.what = 3;
      this.handler.sendMessage(paramArrayList);
      return;
      paramInt = 0;
      while (paramInt < idlist.size())
      {
        paramArrayList = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
        HashMap localHashMap = new HashMap();
        localHashMap.put("isfolder", Boolean.valueOf(false));
        localHashMap.put("name", paramArrayList.getName());
        localHashMap.put("path", paramArrayList.getPath());
        this.pdf_file_name.add(localHashMap);
        paramInt += 1;
      }
    } while (this.pdf_file_name.size() <= 0);
    createPDF();
  }
  
  protected void clickOne_jpg(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    Log.i("TAG", "111=" + paramInt);
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
  
  protected void clickTwo(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    this.isListView1OrListview2 = true;
    boolean bool = ((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue();
    int i;
    label133:
    label167:
    label219:
    File localFile;
    Object localObject2;
    if ((paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8) || (paramInt == 9))
    {
      i = paramInt % 6;
      if (!bool) {
        break label612;
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
          break label477;
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
            break label499;
          }
          i = 3;
        }
      }
      if ((i != 0) && (i != 3)) {
        break label783;
      }
      this.export_select = i;
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      if (this.export_size != 0) {
        break label636;
      }
      paramArrayList = new MyFilter2();
      paramInt = 0;
      if (paramInt >= idlist.size()) {
        break label596;
      }
      localFile = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
      localObject2 = localFile.listFiles(paramArrayList);
      if ((localObject2 == null) || (localObject2.length <= 0)) {
        break label531;
      }
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
      catch (Exception localException1)
      {
        label477:
        label499:
        localException1.printStackTrace();
        continue;
      }
      paramInt += 1;
      break label219;
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
      label531:
      localObject2 = new HashMap();
      ((HashMap)localObject2).put("name", localException1.getName());
      ((HashMap)localObject2).put("isfolder", Boolean.valueOf(false));
      ((HashMap)localObject2).put("path", localException1.getPath());
      this.pdf_file_name.add(localObject2);
    }
    label596:
    if (this.pdf_file_name.size() > 0) {
      createPDF();
    }
    label612:
    label636:
    label783:
    do
    {
      Object localObject1;
      do
      {
        return;
        paramArrayList = new Message();
        paramArrayList.what = 3;
        this.handler.sendMessage(paramArrayList);
        return;
        paramInt = 0;
        while (paramInt < idlist.size())
        {
          paramArrayList = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
          localObject1 = new HashMap();
          ((HashMap)localObject1).put("isfolder", Boolean.valueOf(false));
          ((HashMap)localObject1).put("name", paramArrayList.getName());
          ((HashMap)localObject1).put("path", paramArrayList.getPath());
          this.pdf_file_name.add(localObject1);
          paramInt += 1;
        }
      } while (this.pdf_file_name.size() <= 0);
      createPDF();
      return;
      this.export_select = i;
      this.pdf_file_name = new ArrayList();
      this.pdf_file_name.clear();
      if (this.export_size == 0)
      {
        paramArrayList = new MyFilter2();
        paramInt = 0;
        if (paramInt < idlist.size())
        {
          localObject1 = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
          localObject2 = ((File)localObject1).listFiles(paramArrayList);
          if ((localObject2 != null) && (localObject2.length > 0)) {
            localObject2 = new File(((File)localObject1).getPath() + "/" + ((File)localObject1).getName() + ".pdf");
          }
          for (;;)
          {
            try
            {
              if ((Util.getFileSize((File)localObject2) == 0L) || (!Util.isOpenPdf(((File)localObject1).getPath() + "/" + ((File)localObject1).getName() + ".pdf")))
              {
                localObject2 = new HashMap();
                ((HashMap)localObject2).put("name", ((File)localObject1).getName());
                ((HashMap)localObject2).put("isfolder", Boolean.valueOf(false));
                ((HashMap)localObject2).put("path", ((File)localObject1).getPath());
                this.pdf_file_name.add(localObject2);
              }
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
              continue;
            }
            paramInt += 1;
            break;
            localObject2 = new HashMap();
            ((HashMap)localObject2).put("name", localException2.getName());
            ((HashMap)localObject2).put("isfolder", Boolean.valueOf(false));
            ((HashMap)localObject2).put("path", localException2.getPath());
            this.pdf_file_name.add(localObject2);
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
      while (paramInt < idlist.size())
      {
        paramArrayList = new File(this.folderPath + "/" + ((Photo_info)idlist.get(paramInt)).getName());
        HashMap localHashMap = new HashMap();
        localHashMap.put("isfolder", Boolean.valueOf(false));
        localHashMap.put("name", paramArrayList.getName());
        localHashMap.put("path", paramArrayList.getPath());
        this.pdf_file_name.add(localHashMap);
        paramInt += 1;
      }
    } while (this.pdf_file_name.size() <= 0);
    createPDF();
  }
  
  protected void clickTwo_jpg(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    this.isListView1OrListview2 = true;
    boolean bool = ((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue();
    if ((paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8) || (paramInt == 9)) {
      paramInt %= 5;
    }
    for (;;)
    {
      if (bool)
      {
        if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
          this.shareDialog.dismiss();
        }
        if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
          this.menuWindow.dismiss();
        }
        this.export_select = paramInt;
        if (this.export_size != 0) {
          break;
        }
        paramArrayList = new Message();
        paramArrayList.what = 33;
        this.handler.sendMessage(paramArrayList);
      }
      return;
      paramInt %= 5;
    }
    CompressImage();
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
        //   4: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   7: invokestatic 33	com/appxy/tinyscanfree/Activity_FolderFile:access$3600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   10: invokeinterface 39 1 0
        //   15: if_icmpge +1145 -> 1160
        //   18: new 41	java/io/File
        //   21: dup
        //   22: aload_0
        //   23: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   26: invokestatic 33	com/appxy/tinyscanfree/Activity_FolderFile:access$3600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
        //   29: iload_1
        //   30: invokeinterface 45 2 0
        //   35: checkcast 47	java/util/HashMap
        //   38: ldc 49
        //   40: invokevirtual 52	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   43: checkcast 54	java/lang/String
        //   46: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
        //   49: astore 7
        //   51: aload_0
        //   52: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   55: invokestatic 61	com/appxy/tinyscanfree/Activity_FolderFile:access$2100	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)I
        //   58: ifne +185 -> 243
        //   61: aload 7
        //   63: new 63	com/appxy/tinyscanfree/Activity_FolderFile$MyFilter2
        //   66: dup
        //   67: aload_0
        //   68: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   71: invokespecial 65	com/appxy/tinyscanfree/Activity_FolderFile$MyFilter2:<init>	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)V
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
        //   105: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   108: invokestatic 33	com/appxy/tinyscanfree/Activity_FolderFile:access$3600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
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
        //   137: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   140: invokestatic 33	com/appxy/tinyscanfree/Activity_FolderFile:access$3600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
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
        //   255: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   258: invokestatic 117	com/appxy/tinyscanfree/Activity_FolderFile:access$2600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
        //   261: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload_0
        //   265: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   268: invokestatic 33	com/appxy/tinyscanfree/Activity_FolderFile:access$3600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/util/List;
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
        //   306: getstatic 121	com/appxy/tinyscanfree/Activity_FolderFile:comparator3	Ljava/util/Comparator;
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
        //   373: if_icmpge +775 -> 1148
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
        //   450: astore 6
        //   452: aload_0
        //   453: aload 6
        //   455: new 140	java/io/FileOutputStream
        //   458: dup
        //   459: new 75	java/lang/StringBuilder
        //   462: dup
        //   463: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   466: aload_0
        //   467: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   470: invokestatic 117	com/appxy/tinyscanfree/Activity_FolderFile:access$2600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
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
        //   504: putfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   507: aload 6
        //   509: invokevirtual 149	com/itextpdf/text/Document:open	()V
        //   512: aconst_null
        //   513: astore 4
        //   515: aload_0
        //   516: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   519: invokestatic 61	com/appxy/tinyscanfree/Activity_FolderFile:access$2100	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)I
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
        //   565: astore 4
        //   567: aload 4
        //   569: invokevirtual 183	com/itextpdf/text/Image:getWidth	()F
        //   572: aload 6
        //   574: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   577: invokevirtual 190	com/itextpdf/text/Rectangle:getWidth	()F
        //   580: fcmpl
        //   581: ifge +20 -> 601
        //   584: aload 4
        //   586: invokevirtual 193	com/itextpdf/text/Image:getHeight	()F
        //   589: aload 6
        //   591: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   594: invokevirtual 194	com/itextpdf/text/Rectangle:getHeight	()F
        //   597: fcmpl
        //   598: iflt +13 -> 611
        //   601: aload 4
        //   603: aload 6
        //   605: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   608: invokevirtual 197	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   611: aload 4
        //   613: aload 6
        //   615: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   618: invokevirtual 190	com/itextpdf/text/Rectangle:getWidth	()F
        //   621: aload 4
        //   623: invokevirtual 200	com/itextpdf/text/Image:getScaledWidth	()F
        //   626: fsub
        //   627: fconst_2
        //   628: fdiv
        //   629: aload 6
        //   631: invokevirtual 187	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   634: invokevirtual 194	com/itextpdf/text/Rectangle:getHeight	()F
        //   637: aload 4
        //   639: invokevirtual 203	com/itextpdf/text/Image:getScaledHeight	()F
        //   642: fsub
        //   643: fconst_2
        //   644: fdiv
        //   645: invokevirtual 207	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   648: aload 6
        //   650: aload 4
        //   652: invokevirtual 210	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   655: pop
        //   656: aload_0
        //   657: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   660: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   663: ifeq +18 -> 681
        //   666: aload_0
        //   667: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   670: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   673: ifne +8 -> 681
        //   676: aload 6
        //   678: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   681: new 221	com/itextpdf/text/pdf/PdfReader
        //   684: dup
        //   685: new 75	java/lang/StringBuilder
        //   688: dup
        //   689: invokespecial 76	java/lang/StringBuilder:<init>	()V
        //   692: aload_0
        //   693: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   696: invokestatic 117	com/appxy/tinyscanfree/Activity_FolderFile:access$2600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
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
        //   753: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   756: invokestatic 117	com/appxy/tinyscanfree/Activity_FolderFile:access$2600	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)Ljava/lang/String;
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
        //   932: astore 10
        //   934: new 258	java/io/ByteArrayOutputStream
        //   937: dup
        //   938: invokespecial 259	java/io/ByteArrayOutputStream:<init>	()V
        //   941: astore 11
        //   943: aload_0
        //   944: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   947: invokestatic 61	com/appxy/tinyscanfree/Activity_FolderFile:access$2100	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)I
        //   950: iconst_1
        //   951: if_icmpne +29 -> 980
        //   954: aload 10
        //   956: getstatic 265	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   959: bipush 50
        //   961: aload 11
        //   963: invokevirtual 271	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   966: pop
        //   967: aload 11
        //   969: invokevirtual 275	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   972: invokestatic 278	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   975: astore 4
        //   977: goto -410 -> 567
        //   980: aload_0
        //   981: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   984: invokestatic 61	com/appxy/tinyscanfree/Activity_FolderFile:access$2100	(Lcom/appxy/tinyscanfree/Activity_FolderFile;)I
        //   987: iconst_2
        //   988: if_icmpne -421 -> 567
        //   991: aload 10
        //   993: getstatic 265	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   996: iconst_5
        //   997: aload 11
        //   999: invokevirtual 271	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1002: pop
        //   1003: aload 11
        //   1005: invokevirtual 275	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1008: invokestatic 278	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1011: astore 4
        //   1013: goto -446 -> 567
        //   1016: astore 4
        //   1018: getstatic 284	java/lang/System:err	Ljava/io/PrintStream;
        //   1021: aload 4
        //   1023: invokevirtual 287	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1026: invokevirtual 292	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1029: aload_0
        //   1030: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1033: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1036: ifeq -355 -> 681
        //   1039: aload_0
        //   1040: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1043: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1046: ifne -365 -> 681
        //   1049: aload 6
        //   1051: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1054: goto -373 -> 681
        //   1057: astore 4
        //   1059: getstatic 284	java/lang/System:err	Ljava/io/PrintStream;
        //   1062: aload 4
        //   1064: invokevirtual 293	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1067: invokevirtual 292	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1070: aload_0
        //   1071: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1074: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1077: ifeq -396 -> 681
        //   1080: aload_0
        //   1081: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1084: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1087: ifne -406 -> 681
        //   1090: aload 6
        //   1092: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1095: goto -414 -> 681
        //   1098: astore 4
        //   1100: aload_0
        //   1101: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1104: invokevirtual 213	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1107: ifeq +18 -> 1125
        //   1110: aload_0
        //   1111: getfield 171	com/appxy/tinyscanfree/Activity_FolderFile$25:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1114: invokevirtual 216	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1117: ifne +8 -> 1125
        //   1120: aload 6
        //   1122: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1125: aload 4
        //   1127: athrow
        //   1128: astore 4
        //   1130: aload 4
        //   1132: invokevirtual 294	java/io/IOException:printStackTrace	()V
        //   1135: goto -344 -> 791
        //   1138: astore 4
        //   1140: aload 4
        //   1142: invokevirtual 231	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1145: goto -354 -> 791
        //   1148: aload 9
        //   1150: invokevirtual 219	com/itextpdf/text/Document:close	()V
        //   1153: iload_1
        //   1154: iconst_1
        //   1155: iadd
        //   1156: istore_1
        //   1157: goto -1155 -> 2
        //   1160: new 296	android/os/Message
        //   1163: dup
        //   1164: invokespecial 297	android/os/Message:<init>	()V
        //   1167: astore 4
        //   1169: aload 4
        //   1171: iconst_3
        //   1172: putfield 301	android/os/Message:what	I
        //   1175: aload_0
        //   1176: getfield 19	com/appxy/tinyscanfree/Activity_FolderFile$25:this$0	Lcom/appxy/tinyscanfree/Activity_FolderFile;
        //   1179: getfield 305	com/appxy/tinyscanfree/Activity_FolderFile:handler	Landroid/os/Handler;
        //   1182: aload 4
        //   1184: invokevirtual 311	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1187: pop
        //   1188: return
        //   1189: astore 6
        //   1191: aload 4
        //   1193: astore 5
        //   1195: aload 6
        //   1197: astore 4
        //   1199: goto -385 -> 814
        //   1202: astore 6
        //   1204: aload 4
        //   1206: astore 5
        //   1208: aload 6
        //   1210: astore 4
        //   1212: goto -412 -> 800
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1215	0	this	25
        //   1	1156	1	i	int
        //   204	705	2	j	int
        //   368	6	3	k	int
        //   77	660	4	localObject1	Object
        //   798	3	4	localFileNotFoundException1	java.io.FileNotFoundException
        //   808	7	4	localDocumentException1	com.itextpdf.text.DocumentException
        //   825	187	4	localObject2	Object
        //   1016	6	4	localDocumentException2	com.itextpdf.text.DocumentException
        //   1057	6	4	localIOException1	IOException
        //   1098	28	4	localObject3	Object
        //   1128	3	4	localIOException2	IOException
        //   1138	3	4	localDocumentException3	com.itextpdf.text.DocumentException
        //   1167	44	4	localObject4	Object
        //   192	1015	5	localObject5	Object
        //   327	794	6	localDocument1	com.itextpdf.text.Document
        //   1189	7	6	localDocumentException4	com.itextpdf.text.DocumentException
        //   1202	7	6	localFileNotFoundException2	java.io.FileNotFoundException
        //   49	845	7	localFile	File
        //   201	706	8	localArrayList	ArrayList
        //   324	825	9	localDocument2	com.itextpdf.text.Document
        //   932	60	10	localBitmap	Bitmap
        //   941	63	11	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   332	352	798	java/io/FileNotFoundException
        //   332	352	808	com/itextpdf/text/DocumentException
        //   452	512	1016	com/itextpdf/text/DocumentException
        //   515	567	1016	com/itextpdf/text/DocumentException
        //   567	601	1016	com/itextpdf/text/DocumentException
        //   601	611	1016	com/itextpdf/text/DocumentException
        //   611	656	1016	com/itextpdf/text/DocumentException
        //   878	977	1016	com/itextpdf/text/DocumentException
        //   980	1013	1016	com/itextpdf/text/DocumentException
        //   452	512	1057	java/io/IOException
        //   515	567	1057	java/io/IOException
        //   567	601	1057	java/io/IOException
        //   601	611	1057	java/io/IOException
        //   611	656	1057	java/io/IOException
        //   878	977	1057	java/io/IOException
        //   980	1013	1057	java/io/IOException
        //   452	512	1098	finally
        //   515	567	1098	finally
        //   567	601	1098	finally
        //   601	611	1098	finally
        //   611	656	1098	finally
        //   878	977	1098	finally
        //   980	1013	1098	finally
        //   1018	1029	1098	finally
        //   1059	1070	1098	finally
        //   681	791	1128	java/io/IOException
        //   681	791	1138	com/itextpdf/text/DocumentException
        //   352	357	1189	com/itextpdf/text/DocumentException
        //   352	357	1202	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
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
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("MM", Locale.US);
    SimpleDateFormat localSimpleDateFormat3 = new SimpleDateFormat("dd", Locale.US);
    int i = 0;
    if (arrayOfFile != null) {
      i = arrayOfFile.length;
    }
    int j = 0;
    int k;
    if (j < i)
    {
      Object localObject1;
      ArrayList localArrayList;
      Object localObject2;
      if (arrayOfFile[j].isDirectory())
      {
        localObject1 = arrayOfFile[j].list();
        localArrayList = new ArrayList();
        if (localObject1.length > 0)
        {
          k = 0;
          while (k < localObject1.length)
          {
            if (localObject1[k].matches("[0-9]{18}.jpg")) {
              localArrayList.add(localObject1[k]);
            }
            k += 1;
          }
          if (localArrayList.size() > 0)
          {
            localObject2 = localSimpleDateFormat1.format(new Date(arrayOfFile[j].lastModified()));
            localObject1 = localSimpleDateFormat2.format(new Date(arrayOfFile[j].lastModified()));
            String str2 = localSimpleDateFormat3.format(new Date(arrayOfFile[j].lastModified()));
            String str1 = localSimpleDateFormat1.format(new Date(System.currentTimeMillis()));
            localObject1 = this.month[(Integer.parseInt(localObject1) - 1)] + " " + str2;
            if (!((String)localObject2).equals(str1)) {
              break label428;
            }
          }
        }
      }
      for (;;)
      {
        Collections.sort(localArrayList, comparator3);
        localObject2 = new ArrayList();
        ((ArrayList)localObject2).add(arrayOfFile[j].getPath() + "/" + (String)localArrayList.get(0));
        localObject1 = new Photo_info(arrayOfFile[j].getName(), getShowName(arrayOfFile[j].getName()), (String)localObject1, arrayOfFile[j].lastModified(), localArrayList.size(), (ArrayList)localObject2, false, false);
        mlist2.add(localObject1);
        j += 1;
        break;
        label428:
        localObject1 = (String)localObject1 + " ,";
        localObject1 = (String)localObject1 + (String)localObject2;
      }
    }
    j = idlist.size();
    if (mlist2.size() > 1)
    {
      this.folder_documet_count.setVisibility(0);
      this.folder_documet_count.setText(mlist2.size() + " Docs");
    }
    for (;;)
    {
      i = 0;
      while (i < j)
      {
        k = findId((Photo_info)idlist.get(i));
        if (k != -1) {
          ((Photo_info)mlist2.get(k)).setCheck(true);
        }
        i += 1;
      }
      if (mlist2.size() == 1)
      {
        this.folder_documet_count.setVisibility(0);
        this.folder_documet_count.setText(mlist2.size() + " Doc");
      }
      else
      {
        this.folder_documet_count.setVisibility(8);
      }
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
    View localView = this.inflater.inflate(2130903110, null);
    this.listphotos.addView(localView);
    this.grid = ((GridView)localView.findViewById(2131755452));
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
      this.madapter = new GridAdapter(this.context, mlist2, true);
      this.grid.setAdapter(this.madapter);
      this.grid.setOnScrollListener(new AbsListView.OnScrollListener()
      {
        private boolean isLastRow;
        
        public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if ((paramAnonymousInt1 + paramAnonymousInt2 == paramAnonymousInt3) && (paramAnonymousInt3 > 0)) {
            this.isLastRow = true;
          }
        }
        
        public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
        {
          if ((this.isLastRow) && (paramAnonymousInt == 0) && (Activity_FolderFile.this.madapter != null))
          {
            paramAnonymousAbsListView = Activity_FolderFile.this.madapter;
            paramAnonymousAbsListView.count += 15;
            Activity_FolderFile.this.madapter.notifyDataSetChanged();
          }
          this.isLastRow = false;
        }
      });
      this.grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
          if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
          {
            if (Activity_FolderFile.this.islongclick)
            {
              if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
              {
                ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
                Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
                if (Activity_FolderFile.idlist.isEmpty())
                {
                  Activity_FolderFile.this.unselected();
                  Activity_FolderFile.this.islongclick = false;
                  Activity_FolderFile.this.madapter.isse = false;
                  Activity_FolderFile.this.madapter.notifyDataSetChanged();
                }
              }
              for (;;)
              {
                Activity_FolderFile.this.changeView();
                return;
                Activity_FolderFile.this.selected();
                break;
                ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
                Activity_FolderFile.this.madapter.isse = true;
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
                paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
                Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
                Activity_FolderFile.this.selected();
              }
            }
            if (paramAnonymousAdapterView.isFolder())
            {
              paramAnonymousView = new Intent(Activity_FolderFile.this.context, Activity_FolderFile.class);
              paramAnonymousView.putExtra("folder_path", Activity_FolderFile.this.root_Path4 + paramAnonymousAdapterView.getName());
              Activity_FolderFile.this.startActivity(paramAnonymousView);
              return;
            }
            paramAnonymousAdapterView = new Intent(Activity_FolderFile.this.context, Activity_EditPhoto.class);
            Activity_FolderFile.this.editor.putString("folder_path", Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getName());
            Activity_FolderFile.this.editor.putString("folder_name", ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getShowname());
            Activity_FolderFile.this.editor.putString("folder_root_path", Activity_FolderFile.this.folderPath + "/");
            Activity_FolderFile.this.editor.putInt("folder_folder_id", paramAnonymousInt);
            Activity_FolderFile.this.editor.commit();
            Activity_FolderFile.this.mapp.setAdd(false);
            Activity_FolderFile.this.startActivity(paramAnonymousAdapterView);
            return;
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296408), 0).show();
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
            Activity_FolderFile.this.islongclick = true;
            if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
            {
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
              Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
              if (Activity_FolderFile.idlist.isEmpty())
              {
                Activity_FolderFile.this.unselected();
                Activity_FolderFile.this.islongclick = false;
                Activity_FolderFile.this.madapter.isse = false;
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
              }
            }
            for (;;)
            {
              Activity_FolderFile.this.changeView();
              return true;
              Activity_FolderFile.this.selected();
              break;
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(true);
              Activity_FolderFile.this.madapter.isse = true;
              Activity_FolderFile.this.madapter.notifyDataSetChanged();
              paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
              Activity_FolderFile.idlist.add(paramAnonymousAdapterView);
              Activity_FolderFile.this.selected();
            }
          }
          Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296408), 0).show();
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
        this.grid.setColumnWidth((this.mapp.getDisplaywidth() - dip2px(24.0F)) / 2);
        this.grid.setNumColumns(2);
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  public void listByList()
  {
    this.listphotos.removeAllViews();
    View localView = this.inflater.inflate(2130903112, null);
    this.listphotos.addView(localView);
    this.list = ((ListView)localView.findViewById(2131755453));
    this.madapter2 = new ListAdapter(this.context, mlist2, true);
    this.list.setAdapter(this.madapter2);
    this.list.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      private boolean isLastRow;
      
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((paramAnonymousInt1 + paramAnonymousInt2 == paramAnonymousInt3) && (paramAnonymousInt3 > 0)) {
          this.isLastRow = true;
        }
      }
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if ((this.isLastRow) && (paramAnonymousInt == 0) && (Activity_FolderFile.this.madapter2 != null))
        {
          paramAnonymousAbsListView = Activity_FolderFile.this.madapter2;
          paramAnonymousAbsListView.count += 15;
          Activity_FolderFile.this.madapter2.notifyDataSetChanged();
        }
        this.isLastRow = false;
      }
    });
    this.list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt);
        if (new File(Activity_FolderFile.this.folderPath + "/" + paramAnonymousAdapterView.getName()).exists())
        {
          if (Activity_FolderFile.this.islongclick)
          {
            if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
            {
              ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
              Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
              if (Activity_FolderFile.idlist.isEmpty())
              {
                Activity_FolderFile.this.unselected();
                Activity_FolderFile.this.islongclick = false;
                Activity_FolderFile.this.madapter2.isse = false;
                if (Activity_FolderFile.this.list_type != 0) {
                  break label210;
                }
                Activity_FolderFile.this.madapter.notifyDataSetChanged();
                label170:
                if (!Activity_FolderFile.idlist.isEmpty()) {
                  break label312;
                }
              }
            }
            for (;;)
            {
              Activity_FolderFile.this.changeView();
              return;
              Activity_FolderFile.this.selected();
              Activity_FolderFile.this.madapter2.isse = true;
              break;
              label210:
              Activity_FolderFile.this.madapter2.notifyDataSetChanged();
              break label170;
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
              label312:
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
          paramAnonymousAdapterView = new Intent(Activity_FolderFile.this.context, Activity_EditPhoto.class);
          Activity_FolderFile.this.editor.putString("folder_path", Activity_FolderFile.this.folderPath + "/" + ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getName());
          Activity_FolderFile.this.editor.putString("folder_name", ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).getShowname());
          Activity_FolderFile.this.editor.putString("folder_root_path", Activity_FolderFile.this.folderPath + "/");
          Activity_FolderFile.this.editor.putInt("folder_folder_id", paramAnonymousInt);
          Activity_FolderFile.this.editor.commit();
          Activity_FolderFile.this.mapp.setAdd(false);
          Activity_FolderFile.this.startActivity(paramAnonymousAdapterView);
          return;
        }
        Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296408), 0).show();
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
          Activity_FolderFile.this.islongclick = true;
          if (((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).isCheck())
          {
            ((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt)).setCheck(false);
            Activity_FolderFile.this.removeid((Photo_info)Activity_FolderFile.mlist2.get(paramAnonymousInt));
            if (Activity_FolderFile.idlist.isEmpty())
            {
              Activity_FolderFile.this.unselected();
              Activity_FolderFile.this.islongclick = false;
              Activity_FolderFile.this.madapter2.isse = false;
              if (Activity_FolderFile.this.list_type != 0) {
                break label209;
              }
              Activity_FolderFile.this.madapter.notifyDataSetChanged();
              label168:
              if (!Activity_FolderFile.idlist.isEmpty()) {
                break label311;
              }
            }
          }
          for (;;)
          {
            Activity_FolderFile.this.changeView();
            return true;
            Activity_FolderFile.this.selected();
            Activity_FolderFile.this.madapter2.isse = true;
            break;
            label209:
            Activity_FolderFile.this.madapter2.notifyDataSetChanged();
            break label168;
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
            label311:
            if (Activity_FolderFile.idlist.size() <= 1) {}
          }
        }
        Toast.makeText(Activity_FolderFile.this.context, Activity_FolderFile.this.getResources().getString(2131296408), 0).show();
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
    paramIntent = this.activity_folderFile.getSharedPreferences("TinyScanPro", 0);
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
        break label514;
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
      label514:
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  public void onBackPressed()
  {
    if (this.isSelect)
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
        break label234;
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
    label234:
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
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (this.mapp.isPad())
    {
      setContentView(2130903102);
      this.activity_folderFile = this;
      this.context = this;
      this.folder_toolbar = ((Toolbar)findViewById(2131755409));
      setSupportActionBar(this.folder_toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      this.inflater = LayoutInflater.from(this.context);
      this.titleedit = ((EditText)findViewById(2131755410));
      this.title = ((TextView)findViewById(2131755412));
      this.folderfile_tile_relativelayout = ((RelativeLayout)findViewById(2131755411));
      this.folder_documet_count = ((TextView)findViewById(2131755413));
      this.folder_onLongclicklayout = ((RelativeLayout)findViewById(2131755414));
      this.folder_onLongclicklayout.setVisibility(8);
      this.back2 = ((ImageView)findViewById(2131755415));
      this.back2.setOnClickListener(this.myOnClickListener);
      this.share = ((ImageView)findViewById(2131755418));
      this.share.setOnClickListener(this.myOnClickListener);
      this.moveto = ((ImageView)findViewById(2131755419));
      this.moveto.setOnClickListener(this.myOnClickListener);
      this.getall = ((ImageView)findViewById(2131755420));
      this.getall.setOnClickListener(this.myOnClickListener);
      this.delete = ((ImageView)findViewById(2131755421));
      this.delete.setOnClickListener(this.myOnClickListener);
      this.listphotos = ((LinearLayout)findViewById(2131755422));
      if (this.mapp.isPad())
      {
        this.folderedit = ((ImageView)findViewById(2131755426));
        this.folderedit.setOnClickListener(this.myOnClickListener);
      }
      this.selecttext = ((TextView)findViewById(2131755417));
      paramBundle = getIntent();
      this.mDbHelper = MyDbHelper.getHelper(this.context);
      this.db = this.mDbHelper.getWritableDatabase();
      this.folderPath = paramBundle.getExtras().getString("folder_path");
      this.list_type = paramBundle.getExtras().getInt("list_type");
      this.sort_type = paramBundle.getExtras().getInt("sort_type");
      idlist = new ArrayList();
      this.preferences = getSharedPreferences("TinyScanPro", 0);
      this.editor = this.preferences.edit();
      this.oldname = paramBundle.getExtras().getString("folder_name");
      this.title.setText(paramBundle.getExtras().getString("folder_name"));
      this.folder_camera_gallery_layout = ((RelativeLayout)findViewById(2131755423));
      this.camera = ((ImageView)findViewById(2131755424));
      this.camera.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_FolderFile.this.takePicture(true);
        }
      });
      this.gallery = ((ImageView)findViewById(2131755425));
      this.gallery.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_FolderFile.this.takePicture(false);
        }
      });
      init();
      if (this.list_type != 0) {
        break label615;
      }
      listByGrid();
    }
    for (;;)
    {
      if (this.mapp.getAdvOrChargeOrNormal() != 3)
      {
        this.iapBuy = new IAPBuy(this);
        this.iapBuy.buyGoogleAdvPro();
      }
      return;
      setRequestedOrientation(1);
      break;
      label615:
      listByList();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131820545, paramMenu);
    this.folder_toolbar.getMenu().findItem(2131755703).setVisible(false);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.editor.putInt("folder_folder_id", 0);
    this.editor.commit();
    this.db.close();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      if (paramMenuItem.getItemId() == 2131755704)
      {
        this.folder_toolbar.getMenu().findItem(2131755703).setVisible(true);
        this.folder_toolbar.getMenu().findItem(2131755704).setVisible(false);
        this.folder_toolbar.getMenu().findItem(2131755705).setVisible(false);
        this.folderfile_tile_relativelayout.setVisibility(8);
        this.titleedit.setVisibility(0);
        this.titleedit.setText(this.title.getText().toString());
        this.titleedit.requestFocus();
        this.titleedit.setSelection(this.titleedit.getText().length());
        ((InputMethodManager)this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
      }
      else if (paramMenuItem.getItemId() == 2131755705)
      {
        selectMothed();
      }
      else if (paramMenuItem.getItemId() == 2131755703)
      {
        ((InputMethodManager)this.context.getSystemService("input_method")).toggleSoftInput(0, 2);
        Object localObject3 = new File(this.folderPath);
        if (this.titleedit.getText().toString().equals(((File)localObject3).getName()))
        {
          this.folderfile_tile_relativelayout.setVisibility(0);
          this.titleedit.setVisibility(8);
          this.folder_toolbar.getMenu().findItem(2131755703).setVisible(false);
          this.folder_toolbar.getMenu().findItem(2131755704).setVisible(true);
          this.folder_toolbar.getMenu().findItem(2131755705).setVisible(true);
        }
        else if (this.titleedit.getText().toString().equals(""))
        {
          showToast(getResources().getString(2131296410));
        }
        else
        {
          Object localObject1 = this.titleedit.getText().toString().trim();
          Object localObject2 = this.titleedit.getText().toString().trim();
          if (checkFoldername((String)localObject1))
          {
            showToast(getResources().getString(2131296409));
          }
          else
          {
            if (checkName((String)localObject1)) {}
            for (;;)
            {
              localObject2 = new File(this.root_Path4 + (String)localObject1);
              if (((File)localObject3).exists()) {
                ((File)localObject3).renameTo((File)localObject2);
              }
              this.editor.putString("folder_path", ((File)localObject2).getPath());
              this.editor.commit();
              this.folderPath = ((File)localObject2).getPath();
              localObject2 = mlist2.iterator();
              while (((Iterator)localObject2).hasNext())
              {
                localObject3 = ((Photo_info)((Iterator)localObject2).next()).getImage_name().iterator();
                while (((Iterator)localObject3).hasNext())
                {
                  localObject4 = (String)((Iterator)localObject3).next();
                  BitmapDrawable localBitmapDrawable = this.mapp.getBitmapFromMemCache("main" + (String)localObject4);
                  this.mapp.getmMemoryCache().remove("main" + (String)localObject4);
                  int i = ((String)localObject4).lastIndexOf("/", ((String)localObject4).lastIndexOf("/") - 1);
                  this.mapp.addBitmapToMemoryCache("main" + this.root_Path4 + (String)localObject1 + "/" + ((String)localObject4).substring(i + 1, ((String)localObject4).length()), localBitmapDrawable);
                }
              }
              Object localObject4 = new Timestamp(System.currentTimeMillis());
              localObject4 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject4);
              localObject2 = ((String)localObject2).replaceAll("([*/\\\\\"?|:<>])", "-") + "-" + (String)localObject4;
              saveNameToDb((String)localObject1, (String)localObject2);
              localObject1 = localObject2;
            }
            relist();
            this.folderfile_tile_relativelayout.setVisibility(0);
            this.title.setText((CharSequence)localObject1);
            this.titleedit.setVisibility(8);
            this.folder_toolbar.getMenu().findItem(2131755703).setVisible(false);
            this.folder_toolbar.getMenu().findItem(2131755704).setVisible(true);
            this.folder_toolbar.getMenu().findItem(2131755705).setVisible(true);
          }
        }
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((this.mapp.getAdvOrChargeOrNormal() == 1) && (this.preferences.getInt("file_count_total", 0) >= 2) && ((this.isclick_cloud) || (this.isclick_systemdialog)))
    {
      if (!this.isclick_cloud) {
        break label158;
      }
      FlurryAgent.logEvent("7_OPTIONDIR");
      Utils.showAd_chayeads(this.activity_folderFile);
    }
    label88:
    label157:
    label158:
    label212:
    label235:
    do
    {
      break label157;
      this.isclick_cloud = false;
      this.isclick_systemdialog = false;
      if (this.isFirst)
      {
        this.isFirst = false;
        if (this.isSelect) {
          selected();
        }
        if (this.list_type != 0) {
          break label212;
        }
        this.grid.setSelection(this.preferences.getInt("folder_folder_id", 0));
      }
      for (;;)
      {
        if ((this.mapp.getAdvOrChargeOrNormal() != 3) || (this.preferences.getBoolean("limitRate", false))) {
          break label235;
        }
        ratenew();
        return;
        if ((!this.isclick_systemdialog) || (System.currentTimeMillis() - this.preferences.getLong("click_systemdialog_time", 0L) < 5000L)) {
          break;
        }
        FlurryAgent.logEvent("7_OPTION5S");
        Utils.showAd_chayeads(this.activity_folderFile);
        break;
        relist();
        break label88;
        this.list.setSelection(this.preferences.getInt("folder_folder_id", 0));
      }
    } while ((this.preferences.getBoolean("limitRate", false)) || (!this.preferences.getBoolean("isFirstTakingPictures", false)));
    ratenew1();
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
          this.editor.commit();
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
              Activity_FolderFile.this.editor.putLong("time", 0L);
              Activity_FolderFile.this.editor.commit();
              switch (Activity_FolderFile.this.preferences.getInt("ratetime", 0))
              {
              }
              Object localObject;
              for (;;)
              {
                Activity_FolderFile.access$3502(Activity_FolderFile.this, true);
                localObject = Activity_FolderFile.this.getPackageManager().getInstalledApplications(0);
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
                Activity_FolderFile.this.startActivity((Intent)localObject);
              }
              for (;;)
              {
                if ((this.val$mDialog != null) && (this.val$mDialog.isShowing())) {
                  this.val$mDialog.dismiss();
                }
                return;
                Activity_FolderFile.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscanner&hl=en")));
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
              if (!Activity_FolderFile.this.clickrate) {}
              switch (Activity_FolderFile.this.preferences.getInt("ratetime", 0))
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
      break label125;
    }
    new RateMeDialogFragment().show(getFragmentManager(), "");
  }
  
  public void relist()
  {
    init();
    if (this.list_type == 0)
    {
      this.madapter = new GridAdapter(this.context, mlist2, true);
      this.grid.setAdapter(this.madapter);
      return;
    }
    this.madapter2 = new ListAdapter(this.context, mlist2, true);
    this.list.setAdapter(this.madapter2);
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
  
  protected void sdCard(String paramString)
  {
    try
    {
      if (ExistSDCard())
      {
        File localFile = new File(Environment.getExternalStorageDirectory() + "/MyTinyScan/");
        paramString = new File(Environment.getExternalStorageDirectory() + "/MyTinyScan/" + paramString + ".Tinyscanner");
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
  
  public void selected()
  {
    this.isSelect = true;
    this.titleedit.setVisibility(8);
    this.folder_toolbar.getMenu().findItem(2131755704).setVisible(false);
    this.folder_toolbar.getMenu().findItem(2131755705).setVisible(false);
    this.folder_toolbar.getMenu().findItem(2131755703).setVisible(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    this.folderfile_tile_relativelayout.setVisibility(8);
    this.folder_onLongclicklayout.setVisibility(0);
    if (this.mapp.isPad())
    {
      this.selecttext.setText("" + idlist.size());
      this.folderedit.setVisibility(0);
    }
    for (;;)
    {
      this.folder_camera_gallery_layout.setVisibility(4);
      return;
      this.selecttext.setText("" + idlist.size());
    }
  }
  
  @SuppressLint({"InflateParams"})
  protected void sharepadAlterdialog()
  {
    Object localObject1 = this.inflater.inflate(2130903167, null);
    Object localObject2 = (RadioGroup)((View)localObject1).findViewById(2131755675);
    Object localObject3 = (RadioButton)((View)localObject1).findViewById(2131755676);
    ((RadioGroup)localObject2).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, @IdRes int paramAnonymousInt)
      {
        if (paramAnonymousInt == this.val$popu_radiobutton_pdf.getId())
        {
          Activity_FolderFile.access$1102(Activity_FolderFile.this, true);
          Activity_FolderFile.this.addPDF_listdata();
          Activity_FolderFile.access$1302(Activity_FolderFile.this, new SharePopuList1_padAdapter(Activity_FolderFile.this.context, Activity_FolderFile.this.padexportlist1, Activity_FolderFile.this.currentWidth));
          Activity_FolderFile.access$1602(Activity_FolderFile.this, new SharePopuList2_padAdapter(Activity_FolderFile.this.context, Activity_FolderFile.this.padexportlist2, Activity_FolderFile.this.currentWidth));
          Activity_FolderFile.this.pad_listview1.setAdapter(Activity_FolderFile.this.exportAdapter1);
          Activity_FolderFile.this.pad_listview2.setAdapter(Activity_FolderFile.this.exportAdapter2);
        }
        while (paramAnonymousInt != this.val$popu_radiobutton_jpg.getId()) {
          return;
        }
        Activity_FolderFile.access$1102(Activity_FolderFile.this, false);
        Activity_FolderFile.this.addjpg_listdata();
        Activity_FolderFile.access$1302(Activity_FolderFile.this, new SharePopuList1_padAdapter(Activity_FolderFile.this.context, Activity_FolderFile.this.padexportlist1, Activity_FolderFile.this.currentWidth));
        Activity_FolderFile.access$1602(Activity_FolderFile.this, new SharePopuList2_padAdapter(Activity_FolderFile.this.context, Activity_FolderFile.this.padexportlist2, Activity_FolderFile.this.currentWidth));
        Activity_FolderFile.this.pad_listview1.setAdapter(Activity_FolderFile.this.exportAdapter1);
        Activity_FolderFile.this.pad_listview2.setAdapter(Activity_FolderFile.this.exportAdapter2);
      }
    });
    final long l;
    WindowManager.LayoutParams localLayoutParams;
    if (((RadioButton)localObject3).isChecked())
    {
      this.pdf_or_jpg = true;
      addPDF_listdata();
      this.pad_listview1 = ((HorizontalListView)((View)localObject1).findViewById(2131755678));
      this.pad_listview2 = ((HorizontalListView)((View)localObject1).findViewById(2131755679));
      localObject2 = (TextView)((View)localObject1).findViewById(2131755680);
      localObject3 = (TextView)((View)localObject1).findViewById(2131755681);
      l = getfilesizeLength();
      ((TextView)localObject2).setText("File Size: " + Util.FormetFileSize1(l));
      ((TextView)localObject3).setText("Large");
      this.export_size = 0;
      this.shareDialog = new AlertDialog.Builder(this.activity_folderFile).setView((View)localObject1).create();
      this.shareDialog.show();
      this.shareDialog.setCanceledOnTouchOutside(true);
      this.shareDialog.setCancelable(true);
      localObject1 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      localLayoutParams = this.shareDialog.getWindow().getAttributes();
      if (this.context.getResources().getConfiguration().orientation != 2) {
        break label452;
      }
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject1).heightPixels * 0.9D));
    }
    for (this.currentWidth = localLayoutParams.width;; this.currentWidth = localLayoutParams.width)
    {
      this.currentWidth -= dip2px(30.0F);
      this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, this.currentWidth);
      this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, this.currentWidth);
      this.pad_listview1.setAdapter(this.exportAdapter1);
      this.pad_listview2.setAdapter(this.exportAdapter2);
      this.shareDialog.getWindow().setAttributes(localLayoutParams);
      ((TextView)localObject3).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$filesize_size.getText().toString().equals("Large"))
          {
            this.val$filesize_size.setText("Medium");
            this.val$filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)));
            Activity_FolderFile.access$2102(Activity_FolderFile.this, 1);
          }
          do
          {
            return;
            if (this.val$filesize_size.getText().toString().equals("Medium"))
            {
              this.val$filesize_size.setText("Small");
              this.val$filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)));
              Activity_FolderFile.access$2102(Activity_FolderFile.this, 2);
              return;
            }
          } while (!this.val$filesize_size.getText().toString().equals("Small"));
          this.val$filesize_size.setText("Large");
          this.val$filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
          Activity_FolderFile.access$2102(Activity_FolderFile.this, 0);
        }
      });
      this.pad_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_FolderFile.this.pdf_or_jpg)
          {
            Activity_FolderFile.this.clickOne(Activity_FolderFile.this.padexportlist1, paramAnonymousInt);
            return;
          }
          Activity_FolderFile.this.clickOne_jpg(Activity_FolderFile.this.padexportlist1, paramAnonymousInt);
        }
      });
      this.pad_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_FolderFile.this.pdf_or_jpg)
          {
            Activity_FolderFile.this.clickTwo(Activity_FolderFile.this.padexportlist2, paramAnonymousInt);
            return;
          }
          Activity_FolderFile.this.clickTwo_jpg(Activity_FolderFile.this.padexportlist2, paramAnonymousInt);
        }
      });
      return;
      this.pdf_or_jpg = false;
      addjpg_listdata();
      break;
      label452:
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject1).widthPixels * 0.9D));
    }
  }
  
  public void showToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Activity_FolderFile.this.getApplicationContext(), paramString, 0).show();
      }
    });
  }
  
  @SuppressLint({"InflateParams"})
  public void takePicture(boolean paramBoolean)
  {
    int j = 0;
    int i = j;
    Object localObject1;
    if (this.mapp.getAdvOrChargeOrNormal() == 2)
    {
      localObject1 = Activity_Main.getActivity_main();
      i = j;
      if (localObject1 != null) {
        i = ((Activity_Main)localObject1).getPhotoNum(new File(this.folderPath).getName()) + mlist2.size();
      }
    }
    if (i >= 3)
    {
      Object localObject2 = this.inflater.inflate(2130903104, null);
      localObject1 = new AlertDialog.Builder(this.context).setTitle(getString(2131296433)).setView((View)localObject2).create();
      ((AlertDialog)localObject1).show();
      ImageView localImageView = (ImageView)((View)localObject2).findViewById(2131755431);
      localObject2 = (ImageView)((View)localObject2).findViewById(2131755433);
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          this.val$mDialog.dismiss();
          Activity_FolderFile.this.iapBuy.IAP_Buy();
        }
      });
      ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1) {
            Activity_FolderFile.this.sdCard(".Ads_Pro");
          }
          Object localObject;
          for (;;)
          {
            this.val$mDialog.dismiss();
            localObject = Activity_FolderFile.this.getPackageManager().getInstalledApplications(0);
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
            if (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 2) {
              Activity_FolderFile.this.sdCard(".Doc_Pro");
            }
          }
          try
          {
            localObject = new Intent("android.intent.action.VIEW");
            ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscan&hl=en"));
            ((Intent)localObject).setPackage(paramAnonymousView.packageName);
            Activity_FolderFile.this.startActivity((Intent)localObject);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Activity_FolderFile.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscan&hl=en")));
          }
        }
      });
      return;
    }
    this.editor.commit();
    if (paramBoolean)
    {
      FlurryAgent.logEvent("4_CAMERA");
      this.editor.putBoolean("where", false);
      this.editor.commit();
      this.mapp.setSavePath(this.folderPath + "/");
      if (this.mapp.isPad()) {}
      for (localObject1 = new Intent(this.context, Activity_PadCamera.class);; localObject1 = new Intent(this.context, Activity_CameraPreview.class))
      {
        startActivityForResult((Intent)localObject1, 0);
        return;
      }
    }
    FlurryAgent.logEvent("4_ALBUM");
    this.editor.putBoolean("where", false);
    this.editor.commit();
    this.mapp.clearCheckeditems();
    this.mapp.setPhotofrom(false);
    this.mapp.setSavePath(this.folderPath + "/");
    startActivity(new Intent(this, LocalAlbum.class));
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
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.folderfile_tile_relativelayout.setVisibility(0);
    this.selecttext.setText("");
    this.folder_onLongclicklayout.setVisibility(8);
    this.titleedit.setVisibility(8);
    this.folder_toolbar.getMenu().findItem(2131755704).setVisible(true);
    this.folder_toolbar.getMenu().findItem(2131755705).setVisible(true);
    this.folder_toolbar.getMenu().findItem(2131755703).setVisible(false);
    if (this.mapp.isPad()) {
      this.folderedit.setVisibility(8);
    }
    this.folder_camera_gallery_layout.setVisibility(0);
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
            Activity_FolderFile.SelectPicPopupWindow.access$3702(Activity_FolderFile.SelectPicPopupWindow.this, true);
            Activity_FolderFile.SelectPicPopupWindow.this.addPDF_listdata();
            Activity_FolderFile.SelectPicPopupWindow.access$3902(Activity_FolderFile.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_FolderFile.SelectPicPopupWindow.this.exportlist1, paramInt));
            Activity_FolderFile.SelectPicPopupWindow.access$4102(Activity_FolderFile.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_FolderFile.SelectPicPopupWindow.this.exportlist2, paramInt));
            Activity_FolderFile.SelectPicPopupWindow.this.listview1.setAdapter(Activity_FolderFile.SelectPicPopupWindow.this.exportAdapter1);
            Activity_FolderFile.SelectPicPopupWindow.this.listview2.setAdapter(Activity_FolderFile.SelectPicPopupWindow.this.exportAdapter2);
          }
          while (paramAnonymousInt != this.val$popu_radiobutton_jpg.getId()) {
            return;
          }
          Activity_FolderFile.SelectPicPopupWindow.access$3702(Activity_FolderFile.SelectPicPopupWindow.this, false);
          Activity_FolderFile.SelectPicPopupWindow.this.addjpg_listdata();
          Activity_FolderFile.SelectPicPopupWindow.access$3902(Activity_FolderFile.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_FolderFile.SelectPicPopupWindow.this.exportlist1, paramInt));
          Activity_FolderFile.SelectPicPopupWindow.access$4102(Activity_FolderFile.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_FolderFile.SelectPicPopupWindow.this.exportlist2, paramInt));
          Activity_FolderFile.SelectPicPopupWindow.this.listview1.setAdapter(Activity_FolderFile.SelectPicPopupWindow.this.exportAdapter1);
          Activity_FolderFile.SelectPicPopupWindow.this.listview2.setAdapter(Activity_FolderFile.SelectPicPopupWindow.this.exportAdapter2);
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
        final long l = Activity_FolderFile.this.getfilesizeLength();
        this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
        this.filesize_size.setText("Large");
        Activity_FolderFile.access$2102(Activity_FolderFile.this, 0);
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
            int i = Activity_FolderFile.SelectPicPopupWindow.this.mMenuView.findViewById(2131755682).getTop();
            int j = (int)paramAnonymousMotionEvent.getY();
            if ((paramAnonymousMotionEvent.getAction() == 1) && (j < i)) {
              Activity_FolderFile.SelectPicPopupWindow.this.dismiss();
            }
            return true;
          }
        });
        this.filesize_size.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Large"))
            {
              Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.setText("Medium");
              Activity_FolderFile.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)));
              Activity_FolderFile.access$2102(Activity_FolderFile.this, 1);
            }
            do
            {
              return;
              if (Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Medium"))
              {
                Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.setText("Small");
                Activity_FolderFile.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)));
                Activity_FolderFile.access$2102(Activity_FolderFile.this, 2);
                return;
              }
            } while (!Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Small"));
            Activity_FolderFile.SelectPicPopupWindow.this.filesize_size.setText("Large");
            Activity_FolderFile.SelectPicPopupWindow.this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
            Activity_FolderFile.access$2102(Activity_FolderFile.this, 0);
          }
        });
        this.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (Activity_FolderFile.SelectPicPopupWindow.this.pdf_or_jpg)
            {
              Activity_FolderFile.this.clickOne(Activity_FolderFile.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
              return;
            }
            Activity_FolderFile.this.clickOne_jpg(Activity_FolderFile.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
          }
        });
        this.listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (Activity_FolderFile.SelectPicPopupWindow.this.pdf_or_jpg)
            {
              Activity_FolderFile.this.clickTwo(Activity_FolderFile.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
              return;
            }
            Activity_FolderFile.this.clickTwo_jpg(Activity_FolderFile.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
          }
        });
        return;
        this.pdf_or_jpg = false;
        addjpg_listdata();
      }
    }
    
    private void addPDF_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
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
            if (Activity_FolderFile.idlist.size() > 1) {
              localHashMap.put("isEnable", Boolean.valueOf(false));
            }
          }
          else
          {
            label242:
            if (i != 3) {
              break label286;
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
            break label242;
            label286:
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
            label426:
            if ((i == 2) || (i == 3) || (i == 4))
            {
              if (i == 3) {
                localHashMap.put("id", Integer.valueOf(6));
              }
              if (i == 4) {
                localHashMap.put("id", Integer.valueOf(3));
              }
              if (Activity_FolderFile.idlist.size() <= 1) {
                break label544;
              }
              localHashMap.put("isEnable", Boolean.valueOf(false));
            }
            label500:
            if (i != 3) {
              break label559;
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
            break label426;
            label544:
            localHashMap.put("isEnable", Boolean.valueOf(true));
            break label500;
            label559:
            this.exportlist2.add(localHashMap);
          }
        }
      }
      if (!Activity_FolderFile.this.mapp.getIsContainCountry()) {
        this.exportlist2.remove(2);
      }
      if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
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
    
    private void addjpg_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
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
            localHashMap.put("isEnable", Boolean.valueOf(true));
            if (i != 1) {
              break label363;
            }
            localHashMap.put("isPro", Boolean.valueOf(true));
          }
          for (;;)
          {
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
      if ((Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_FolderFile.this.mapp.getAdvOrChargeOrNormal() == 1))
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
}
