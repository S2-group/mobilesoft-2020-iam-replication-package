package org.jw.jwlibrary.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v4.util.Pair;
import android.support.v7.app.d.a;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.jw.a.n;
import org.jw.jwlibrary.core.Event;
import org.jw.jwlibrary.core.SimpleEvent;
import org.jw.jwlibrary.mobile.activity.SiloContainer;
import org.jw.jwlibrary.mobile.dialog.l;
import org.jw.jwlibrary.mobile.g.p;
import org.jw.jwlibrary.mobile.util.Dispatcher;
import org.jw.meps.common.jwpub.al;
import org.jw.meps.common.jwpub.ao;
import org.jw.meps.common.userdata.c;
import org.jw.pal.e.q;

public class a
{
  private final SimpleEvent<b> a = new SimpleEvent();
  private final SimpleEvent b = new SimpleEvent();
  private final Context c;
  private final org.jw.jwlibrary.mobile.g.o d;
  private final ao e;
  private final c f;
  
  public a(Context paramContext, org.jw.jwlibrary.mobile.g.o paramO, ao paramAo, c paramC)
  {
    this.c = paramContext;
    paramContext = paramAo;
    if (paramAo == null) {
      paramContext = org.jw.pal.d.e.a().f();
    }
    this.e = paramContext;
    paramContext = paramO;
    if (paramO == null) {
      paramContext = new p();
    }
    this.d = paramContext;
    paramContext = paramC;
    if (paramC == null) {
      paramContext = org.jw.meps.common.userdata.j.k();
    }
    this.f = paramContext;
  }
  
  public a(SiloContainer paramSiloContainer)
  {
    this(paramSiloContainer, null, null, null);
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a(Build.MANUFACTURER));
    localStringBuilder.append("_");
    localStringBuilder.append(Build.MODEL);
    return localStringBuilder.toString();
  }
  
  private static String a(String paramString)
  {
    char c1 = paramString.charAt(0);
    if (Character.isUpperCase(c1)) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Character.toUpperCase(c1));
    if (paramString.length() > 1) {
      paramString = paramString.substring(1);
    } else {
      paramString = "";
    }
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  private JSONObject a(String paramString1, String paramString2, String paramString3, int paramInt, a paramA, String paramString4)
  {
    int i = org.jw.meps.common.userdata.j.k().l();
    Calendar localCalendar = org.jw.meps.common.userdata.j.k().o();
    String str = org.jw.meps.common.userdata.j.k().getDatabaseName();
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject1.put("name", paramString1);
      localJSONObject1.put("creationDate", paramString3);
      localJSONObject1.put("version", paramInt);
      localJSONObject1.put("type", paramA.ordinal());
      localJSONObject2.put("userMarkCount", i);
      if (localCalendar == null) {
        paramString1 = org.jw.pal.a.b.a();
      } else {
        paramString1 = org.jw.pal.a.b.a(localCalendar);
      }
      localJSONObject2.put("lastModifiedDate", paramString1);
      localJSONObject2.put("deviceName", paramString4);
      localJSONObject2.put("databaseName", str);
      localJSONObject2.put("hash", paramString2);
      localJSONObject2.put("schemaVersion", 5);
      localJSONObject1.put("userDataBackup", localJSONObject2);
      return localJSONObject1;
    }
    catch (JSONException paramString1)
    {
      paramString2 = (org.jw.a.a)org.jw.jwlibrary.core.g.e.a().a(org.jw.a.a.class);
      paramString3 = n.e;
      paramA = getClass().getSimpleName();
      paramString4 = new StringBuilder();
      paramString4.append("Error creating JSON object for userData backup. ");
      paramString4.append(paramString1.getMessage());
      paramString2.a(paramString3, paramA, paramString4.toString());
    }
    return null;
  }
  
  private void a(File paramFile)
  {
    paramFile = FileProvider.a(this.c, "org.jw.jwlibrary.mobile.fileprovider", paramFile);
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.STREAM", paramFile);
    localIntent.setType("application/octet-stream");
    Iterator localIterator = this.c.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      this.c.grantUriPermission(localApplicationInfo.packageName, paramFile, 1);
    }
    this.c.startActivity(Intent.createChooser(localIntent, this.c.getString(2131624063)));
  }
  
  private void a(final String paramString1, final String paramString2, final boolean paramBoolean)
  {
    final android.support.v7.app.d localD = new d.a(this.c).c(2131492945).b();
    localD.a(-2, this.c.getString(2131623986), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localD.setCanceledOnTouchOutside(false);
    localD.setCancelable(false);
    localD.show();
    org.jw.jwlibrary.mobile.util.j.a(new Runnable()
    {
      public void run()
      {
        localD.a(-2).setVisibility(4);
        final boolean bool = a.this.a(paramString1, paramString2);
        if (paramBoolean)
        {
          localObject = new File(paramString1);
          if (((File)localObject).exists()) {
            ((File)localObject).delete();
          }
        }
        Object localObject = (TextView)localD.findViewById(2131296648);
        final TextView localTextView = (TextView)localD.findViewById(2131296645);
        final ImageView localImageView1 = (ImageView)localD.findViewById(2131296647);
        final ImageView localImageView2 = (ImageView)localD.findViewById(2131296644);
        final ProgressBar localProgressBar = (ProgressBar)localD.findViewById(2131296646);
        bl.a().b.a(new Runnable()
        {
          public void run()
          {
            if (bool)
            {
              localProgressBar.setVisibility(8);
              localImageView2.setVisibility(8);
              localTextView.setVisibility(8);
              localImageView1.setVisibility(0);
              this.f.setText(a.a(a.this).getString(2131624265));
              a.b(a.this).a(this, null);
            }
            else
            {
              localProgressBar.setVisibility(8);
              localImageView1.setVisibility(8);
              localImageView2.setVisibility(0);
              localTextView.setVisibility(0);
              this.f.setText(a.a(a.this).getString(2131624262));
            }
            a.7.this.a.a(-2).setVisibility(0);
          }
        });
      }
    });
  }
  
  private void a(JSONObject paramJSONObject, final String paramString, final boolean paramBoolean)
  {
    Object localObject;
    final String str2;
    String str3;
    String str1;
    if (paramJSONObject.has("userDataBackup"))
    {
      localObject = paramJSONObject.getJSONObject("userDataBackup");
      if (((JSONObject)localObject).has("schemaVersion"))
      {
        int i = ((JSONObject)localObject).getInt("schemaVersion");
        if (i > 5)
        {
          paramJSONObject = getClass().getSimpleName();
          paramString = new StringBuilder();
          paramString.append("Restoring User Data aborted. Schema version in backup is ");
          paramString.append(i);
          paramString.append(", but this app's schema version is ");
          paramString.append(5);
          Log.d(paramJSONObject, paramString.toString());
          l.a(this.c.getString(2131624263));
          return;
        }
      }
      if ((((JSONObject)localObject).has("databaseName")) && (((JSONObject)localObject).has("deviceName")))
      {
        str2 = ((JSONObject)localObject).getString("databaseName");
        str3 = ((JSONObject)localObject).getString("deviceName");
        boolean bool = ((JSONObject)localObject).has("lastModifiedDate");
        str1 = null;
        paramJSONObject = str1;
        if (!bool) {}
      }
    }
    try
    {
      paramJSONObject = org.jw.pal.a.b.a(((JSONObject)localObject).getString("lastModifiedDate"));
      if (paramJSONObject == null) {
        paramJSONObject = "";
      } else {
        paramJSONObject = org.jw.jwlibrary.mobile.util.b.a(new Date().getTime() - paramJSONObject.getTimeInMillis());
      }
      str1 = g();
      localObject = a();
      final d.a localA = new d.a(this.c);
      View localView = LayoutInflater.from(this.c).inflate(2131492944, (ViewGroup)((Activity)this.c).getWindow().getDecorView(), false);
      ((TextView)localView.findViewById(2131296641)).setText(str3.replaceFirst("_", " "));
      ((TextView)localView.findViewById(2131296643)).setText(((String)localObject).replaceFirst("_", " "));
      ((TextView)localView.findViewById(2131296640)).setText(paramJSONObject);
      ((TextView)localView.findViewById(2131296642)).setText(str1);
      localA.b(localView).b(2131623980, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramBoolean)
          {
            File localFile = new File(paramString);
            if (localFile.exists()) {
              localFile.delete();
            }
          }
          paramAnonymousDialogInterface.dismiss();
        }
      }).a(2131624056, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          a.a(a.this, paramString, str2, paramBoolean);
        }
      });
      bl.a().b.a(new Runnable()
      {
        public void run()
        {
          android.support.v7.app.d localD = localA.b();
          localD.setCanceledOnTouchOutside(true);
          localD.show();
        }
      });
      return;
    }
    catch (ParseException paramJSONObject)
    {
      for (;;)
      {
        paramJSONObject = str1;
      }
    }
  }
  
  private void b(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
  {
    try
    {
      if ((paramJSONObject.has("version")) && (paramJSONObject.getInt("version") >= 1))
      {
        if (paramJSONObject.has("type"))
        {
          int i = paramJSONObject.getInt("type");
          if (8.a[a.values()[i].ordinal()] != 1) {
            return;
          }
          a(paramJSONObject, paramString, paramBoolean);
        }
      }
      else
      {
        Toast.makeText(this.c, this.c.getResources().getString(2131624218), 0).show();
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      com.google.a.a.a.a.a.a.a(paramJSONObject);
    }
  }
  
  private static boolean b(File paramFile)
  {
    paramFile = SQLiteDatabase.openOrCreateDatabase(paramFile, null);
    String str = org.jw.pal.b.d.b(paramFile, "PRAGMA integrity_check");
    paramFile.close();
    return "ok".equals(str);
  }
  
  private String g()
  {
    Calendar localCalendar = org.jw.meps.common.userdata.j.k().o();
    if (localCalendar == null) {
      return "";
    }
    return org.jw.jwlibrary.mobile.util.b.a(Long.valueOf(new Date().getTime() - localCalendar.getTimeInMillis()).longValue());
  }
  
  public File a(File paramFile1, File paramFile2)
  {
    synchronized ()
    {
      org.jw.meps.common.userdata.j.k().m();
      org.jw.meps.common.userdata.j.k().close();
      paramFile2.mkdirs();
      Object localObject1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
      String str = a();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("UserDataBackup_");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("_");
      ((StringBuilder)localObject2).append(str);
      localObject2 = ((StringBuilder)localObject2).toString();
      byte[] arrayOfByte = new byte['Ѐ'];
      try
      {
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append((String)localObject2);
        ((StringBuilder)localObject3).append(".jwlibrary");
        paramFile2 = new File(paramFile2, ((StringBuilder)localObject3).toString());
        if (!paramFile2.exists()) {
          paramFile2.createNewFile();
        }
        localObject3 = new FileOutputStream(paramFile2);
        ZipOutputStream localZipOutputStream = new ZipOutputStream((OutputStream)localObject3);
        FileInputStream localFileInputStream = new FileInputStream(paramFile1);
        localZipOutputStream.putNextEntry(new ZipEntry(paramFile1.getName()));
        for (;;)
        {
          int i = localFileInputStream.read(arrayOfByte);
          if (i <= 0) {
            break;
          }
          localZipOutputStream.write(arrayOfByte, 0, i);
        }
        paramFile1 = a((String)localObject2, org.jw.pal.e.a.b(paramFile1), (String)localObject1, 1, a.a, str);
        if (paramFile1 != null)
        {
          localZipOutputStream.putNextEntry(new ZipEntry("manifest.json"));
          paramFile1 = paramFile1.toString().getBytes();
          localZipOutputStream.write(paramFile1, 0, paramFile1.length);
          localFileInputStream.close();
          localZipOutputStream.closeEntry();
          localZipOutputStream.close();
          ((FileOutputStream)localObject3).close();
          return paramFile2;
        }
      }
      catch (IOException paramFile1)
      {
        paramFile2 = (org.jw.a.a)org.jw.jwlibrary.core.g.e.a().a(org.jw.a.a.class);
        localObject1 = n.e;
        str = getClass().getSimpleName();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Error creating userData backup. ");
        ((StringBuilder)localObject2).append(paramFile1.getMessage());
        paramFile2.a((n)localObject1, str, ((StringBuilder)localObject2).toString());
        return null;
      }
    }
  }
  
  b a(List<al> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      al localAl = (al)paramList.next();
      if (localAl.c().toLowerCase().equals("nwtsty")) {
        try
        {
          this.d.a(localAl.z()).get();
          localArrayList1.add(Integer.valueOf(localAl.A_()));
        }
        catch (Exception localException)
        {
          localArrayList2.add(new Pair(Integer.valueOf(localAl.A_()), localException));
        }
      }
    }
    return new b(localArrayList1, localArrayList2);
  }
  
  public void a(final String paramString, final boolean paramBoolean)
  {
    org.jw.jwlibrary.mobile.util.j.a(new Runnable()
    {
      public void run()
      {
        try
        {
          FileInputStream localFileInputStream = new FileInputStream(paramString);
          ZipInputStream localZipInputStream = new ZipInputStream(new BufferedInputStream(localFileInputStream));
          Object localObject1 = null;
          do
          {
            localObject2 = localZipInputStream.getNextEntry();
            if (localObject2 == null) {
              break;
            }
          } while (!((ZipEntry)localObject2).getName().equals("manifest.json"));
          localObject1 = new StringWriter();
          Object localObject2 = new byte[32768];
          for (;;)
          {
            int i = localZipInputStream.read((byte[])localObject2, 0, 32768);
            if (i == -1) {
              break;
            }
            ((StringWriter)localObject1).append(new String((byte[])localObject2, 0, i, "UTF-8"));
          }
          localObject1 = new JSONObject(((StringWriter)localObject1).toString());
          localZipInputStream.close();
          localFileInputStream.close();
          if (localObject1 != null)
          {
            a.a(a.this, (JSONObject)localObject1, paramString, paramBoolean);
            return;
          }
        }
        catch (IOException|JSONException localIOException)
        {
          com.google.a.a.a.a.a.a.a(localIOException);
        }
      }
    });
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    for (;;)
    {
      int i;
      Object localObject1;
      try
      {
        i = paramString2.indexOf('.');
        Object localObject2 = new StringBuilder();
        if (i < 0) {
          localObject1 = paramString2;
        } else {
          localObject1 = paramString2.substring(0, i);
        }
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(System.nanoTime());
        localObject1 = ((StringBuilder)localObject2).toString();
        File localFile = new File(org.jw.pal.d.e.a().b(org.jw.pal.e.a.a.b), (String)localObject1);
        localFile.mkdirs();
        paramString1 = new FileInputStream(paramString1);
        q.a(paramString1, localFile);
        paramString1.close();
        paramString1 = localFile.listFiles();
        if (paramString1 == null) {
          return false;
        }
        int j = paramString1.length;
        i = 0;
        if (i >= j) {
          break label455;
        }
        localObject1 = paramString1[i];
        if (!((File)localObject1).getName().equals(paramString2)) {
          break label448;
        }
        Object localObject3;
        if (!b((File)localObject1))
        {
          paramString1 = (org.jw.a.a)org.jw.jwlibrary.core.g.e.a().a(org.jw.a.a.class);
          localObject1 = n.e;
          localObject2 = getClass().getSimpleName();
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Integrity check failed while trying to restore ");
          ((StringBuilder)localObject3).append(paramString2);
          paramString1.a((n)localObject1, (String)localObject2, ((StringBuilder)localObject3).toString());
          break label455;
        }
        if (!((File)localObject1).getName().equals("userData.db"))
        {
          i = ((File)localObject1).getPath().lastIndexOf(paramString2);
          localObject2 = new File(((File)localObject1).getPath().substring(0, i), "userData.db");
          paramString1 = (String)localObject2;
          if (!((File)localObject1).renameTo((File)localObject2))
          {
            paramString1 = (org.jw.a.a)org.jw.jwlibrary.core.g.e.a().a(org.jw.a.a.class);
            localObject3 = n.e;
            String str = getClass().getSimpleName();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Failed to rename ");
            localStringBuilder.append(((File)localObject1).getName());
            localStringBuilder.append(" to ");
            localStringBuilder.append(((File)localObject2).getPath());
            localStringBuilder.append(" while trying to restore ");
            localStringBuilder.append(paramString2);
            paramString1.a((n)localObject3, str, localStringBuilder.toString());
            break label455;
          }
          org.jw.meps.common.userdata.j.k().close();
          boolean bool2 = org.jw.meps.common.userdata.j.k().a(paramString1);
          bool1 = bool2;
          if (bool2)
          {
            f();
            bool1 = bool2;
          }
          org.jw.pal.e.a.a(localFile, true);
          return bool1;
        }
      }
      catch (IOException paramString1)
      {
        return false;
      }
      paramString1 = (String)localObject1;
      continue;
      label448:
      i += 1;
      continue;
      label455:
      boolean bool1 = false;
    }
  }
  
  public Event<b> b()
  {
    return this.a;
  }
  
  public void c()
  {
    ((SiloContainer)this.c).k();
  }
  
  public void d()
  {
    Object localObject = org.jw.meps.common.userdata.j.k().getReadableDatabase().getPath();
    org.jw.meps.common.userdata.j.k().close();
    localObject = new File((String)localObject);
    org.jw.jwlibrary.mobile.util.j.a(new Runnable()
    {
      public void run()
      {
        org.jw.pal.e.a.a(this.a, true);
        File localFile = a.this.a(this.b, this.a);
        if (localFile != null) {
          a.a(a.this, localFile);
        }
      }
    });
  }
  
  public Event e()
  {
    return this.b;
  }
  
  void f()
  {
    this.f.a();
    Object localObject = this.e.a();
    if (localObject != null)
    {
      localObject = a((List)localObject);
      this.a.a(this, localObject);
    }
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static class b
  {
    private final Collection<Integer> a;
    private final Collection<Pair<Integer, Exception>> b;
    
    b(Collection<Integer> paramCollection, Collection<Pair<Integer, Exception>> paramCollection1)
    {
      Object localObject = paramCollection;
      if (paramCollection == null) {
        localObject = new ArrayList();
      }
      this.a = ((Collection)localObject);
      paramCollection = paramCollection1;
      if (paramCollection1 == null) {
        paramCollection = new ArrayList();
      }
      this.b = paramCollection;
    }
    
    public Collection<Pair<Integer, Exception>> a()
    {
      return this.b;
    }
  }
}
