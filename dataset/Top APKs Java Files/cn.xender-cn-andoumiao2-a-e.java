package cn.andoumiao2.a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import cn.andoumiao2.messenger.a.a;
import cn.andoumiao2.messenger.a.i;
import java.io.File;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e
{
  Handler a = new w(this);
  private Activity b;
  private ProgressDialog c;
  
  public e(Activity paramActivity)
  {
    this.b = paramActivity;
  }
  
  private void a()
  {
    if (this.c == null)
    {
      this.c = new ProgressDialog(this.b);
      this.c.setMessage(this.b.getString(2131427680));
      this.c.setCancelable(false);
    }
    this.c.show();
  }
  
  private void a(String paramString)
  {
    if ((this.c != null) && (this.c.isShowing())) {
      this.c.setMessage(paramString);
    }
  }
  
  private void a(JSONArray paramJSONArray)
  {
    int i = 0;
    PackageManager localPackageManager = this.b.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      JSONObject localJSONObject = new JSONObject();
      ApplicationInfo localApplicationInfo;
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0)
      {
        localApplicationInfo = localPackageInfo.applicationInfo;
        if (localApplicationInfo != null) {
          break label82;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        label82:
        String str = localApplicationInfo.loadLabel(localPackageManager).toString();
        try
        {
          localJSONObject.put("category", "app");
          localJSONObject.put("file_path", localApplicationInfo.sourceDir);
          localJSONObject.put("res_name", str + ".apk");
          localJSONObject.put("create_time", new File(localApplicationInfo.sourceDir).lastModified());
          localJSONObject.put("ip_addr", a.c(this.b));
          localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
          localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
          localJSONObject.put("package_name", localPackageInfo.packageName);
          localJSONObject.put("version", localPackageInfo.versionCode);
          paramJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException) {}
      }
    }
  }
  
  private void b()
  {
    if ((this.c != null) && (this.c.isShowing())) {
      this.c.dismiss();
    }
  }
  
  private void b(String paramString)
  {
    new v(this, paramString).start();
  }
  
  private void b(JSONArray paramJSONArray)
  {
    Cursor localCursor = this.b.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "_display_name", "_size", "date_modified", "mime_type", "title" }, "_size>=102400", null, "date_added desc");
    if (localCursor == null) {
      return;
    }
    while (localCursor.moveToNext())
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("category", "image");
        localJSONObject.put("file_path", localCursor.getString(1));
        localJSONObject.put("res_name", localCursor.getString(2));
        localJSONObject.put("create_time", localCursor.getLong(4));
        localJSONObject.put("ip_addr", a.c(this.b));
        localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
        localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
        paramJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException) {}
    }
    localCursor.close();
  }
  
  private JSONObject c()
  {
    String str1 = new t(this.b).a();
    if ("-1".equals(str1)) {
      return null;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("category", "name_card");
      localJSONObject.put("file_path", str1);
      String str2 = str1.substring(str1.lastIndexOf("_") + 1).replace(".contact", "");
      localJSONObject.put("res_name", str2 + this.b.getString(2131427689));
      localJSONObject.put("create_time", new File(str1).lastModified());
      localJSONObject.put("ip_addr", a.c(this.b));
      localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
      localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
  
  private void c(JSONArray paramJSONArray)
  {
    Cursor localCursor = this.b.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "title", "date_modified", "count(distinct _size and title)" }, "_size>=102400)group by(_size),(title", null, "title_key");
    if (localCursor == null) {
      return;
    }
    while (localCursor.moveToNext())
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("category", "audio");
        localJSONObject.put("file_path", localCursor.getString(1));
        localJSONObject.put("res_name", localCursor.getString(2));
        localJSONObject.put("create_time", localCursor.getLong(3));
        localJSONObject.put("ip_addr", a.c(this.b));
        localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
        localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
        paramJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException) {}
    }
    localCursor.close();
  }
  
  private JSONObject d()
  {
    String str1 = new c(this.b).a();
    if ("-1".equals(str1)) {
      return null;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("category", "phonecall");
      localJSONObject.put("file_path", str1);
      String str2 = str1.substring(str1.lastIndexOf("_") + 1).replace(".call", "");
      localJSONObject.put("res_name", str2 + this.b.getString(2131427691));
      localJSONObject.put("create_time", new File(str1).lastModified());
      localJSONObject.put("ip_addr", a.c(this.b));
      localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
      localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
  
  private void d(JSONArray paramJSONArray)
  {
    Cursor localCursor = this.b.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "_display_name", "date_modified" }, null, null, "date_modified desc");
    if (localCursor == null) {
      return;
    }
    while (localCursor.moveToNext())
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("category", "video");
        localJSONObject.put("file_path", localCursor.getString(1));
        localJSONObject.put("res_name", localCursor.getString(2));
        localJSONObject.put("create_time", localCursor.getLong(3));
        localJSONObject.put("ip_addr", a.c(this.b));
        localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
        localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
        paramJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException) {}
    }
    localCursor.close();
  }
  
  private JSONObject e()
  {
    String str1 = new j(this.b).a();
    if ("-1".equals(str1)) {
      return null;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("category", "sms");
      localJSONObject.put("file_path", str1);
      String str2 = str1.substring(str1.lastIndexOf("_") + 1).replace(".csv", "");
      localJSONObject.put("res_name", str2 + this.b.getString(2131427690));
      localJSONObject.put("create_time", new File(str1).lastModified());
      localJSONObject.put("ip_addr", a.c(this.b));
      localJSONObject.put("spirit_name", cn.andoumiao2.setname.t.b(this.b));
      localJSONObject.put("imei", cn.andoumiao2.setname.t.l(this.b));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
  
  public void a(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7)
  {
    a();
    i.a("data_sync", "contact_sync " + paramBoolean1 + " ,sms_sync " + paramBoolean2 + " ,app_sync " + paramBoolean3);
    new u(this, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6, paramBoolean7, paramString1, paramString2).start();
  }
}
