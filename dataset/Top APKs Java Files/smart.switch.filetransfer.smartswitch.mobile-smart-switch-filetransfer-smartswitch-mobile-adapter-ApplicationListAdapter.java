package smart.switch.filetransfer.smartswitch.mobile.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import smart.switch.filetransfer.smartswitch.mobile.GlideApp;
import smart.switch.filetransfer.smartswitch.mobile.GlideRequest;
import smart.switch.filetransfer.smartswitch.mobile.GlideRequests;
import smart.switch.filetransfer.smartswitch.mobile.object.Shareable;
import smart.switch.filetransfer.smartswitch.mobile.util.FileUtils;
import smart.switch.filetransfer.smartswitch.mobile.widget.EditableListAdapter;
import smart.switch.filetransfer.smartswitch.mobile.widget.EditableListAdapter.EditableViewHolder;

public class ApplicationListAdapter
  extends EditableListAdapter<PackageHolder, EditableListAdapter.EditableViewHolder>
{
  private PackageManager mManager;
  private SharedPreferences mPreferences;
  
  public ApplicationListAdapter(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    super(paramContext);
    this.mPreferences = paramSharedPreferences;
    this.mManager = paramContext.getPackageManager();
  }
  
  public void onBindViewHolder(@NonNull EditableListAdapter.EditableViewHolder paramEditableViewHolder, int paramInt)
  {
    try
    {
      paramEditableViewHolder = paramEditableViewHolder.getView();
      PackageHolder localPackageHolder = (PackageHolder)getItem(paramInt);
      ImageView localImageView = (ImageView)paramEditableViewHolder.findViewById(2131296436);
      TextView localTextView1 = (TextView)paramEditableViewHolder.findViewById(2131296600);
      TextView localTextView2 = (TextView)paramEditableViewHolder.findViewById(2131296602);
      localTextView1.setText(localPackageHolder.friendlyName);
      localTextView2.setText(localPackageHolder.version);
      paramEditableViewHolder.setSelected(localPackageHolder.isSelectableSelected());
      GlideApp.with(getContext()).load(localPackageHolder.appInfo).override(160).centerCrop().into(localImageView);
      return;
    }
    catch (Exception paramEditableViewHolder) {}
  }
  
  @NonNull
  public EditableListAdapter.EditableViewHolder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
  {
    return new EditableListAdapter.EditableViewHolder(getInflater().inflate(2131492944, paramViewGroup, false));
  }
  
  public ArrayList<PackageHolder> onLoad()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mContext.getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if (((localApplicationInfo.flags & 0x1) != 1) || (this.mPreferences.getBoolean("show_system_apps", false))) {
        localArrayList.add(new PackageHolder(String.valueOf(localApplicationInfo.loadLabel(this.mManager)), localApplicationInfo, localPackageInfo.versionName, localPackageInfo.packageName, new File(localApplicationInfo.sourceDir)));
      }
    }
    Collections.sort(localArrayList, getDefaultComparator());
    return localArrayList;
  }
  
  public static class PackageHolder
    extends Shareable
  {
    public static final String FORMAT = ".apk";
    public static final String MIME_TYPE = FileUtils.getFileContentType(".apk");
    public ApplicationInfo appInfo;
    public String packageName;
    public String version;
    
    public PackageHolder(String paramString1, ApplicationInfo paramApplicationInfo, String paramString2, String paramString3, File paramFile)
    {
      super(paramString1, paramString1 + "_" + paramString2 + ".apk", MIME_TYPE, paramFile.lastModified(), paramFile.length(), Uri.fromFile(paramFile));
      this.appInfo = paramApplicationInfo;
      this.version = paramString2;
      this.packageName = paramString3;
    }
  }
}
