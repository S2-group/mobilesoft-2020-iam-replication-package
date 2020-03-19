package com.lumaticsoft.watchdroidassistant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class n
  extends Activity
{
  private ProgressDialog a;
  private String[] b;
  private String[] c;
  private Drawable[] d;
  private b e;
  
  public n() {}
  
  private void a(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      this.e = new b(getApplicationContext());
    }
    catch (Exception paramBundle)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error al crear debug.");
      localStringBuilder.append(paramBundle.getMessage());
      a(localStringBuilder.toString());
    }
    try
    {
      setContentView(2131296306);
      return;
    }
    catch (Exception paramBundle)
    {
      this.e.a("PantOpcionesAplicacionesSeleccionadas", "onCreate", paramBundle);
    }
  }
  
  protected void onStart()
  {
    try
    {
      super.onStart();
      this.a = new ProgressDialog(this);
      this.a.setCancelable(true);
      this.a.setCanceledOnTouchOutside(false);
      this.a.setProgressStyle(0);
      new a(null).execute(new Void[0]);
      return;
    }
    catch (Exception localException)
    {
      this.e.a("PantOpcionesAplicacionesSeleccionadas", "onStart", localException);
    }
  }
  
  private class a
    extends AsyncTask<Void, Integer, Boolean>
  {
    private a() {}
    
    protected Boolean a(Void... paramVarArgs)
    {
      HashMap localHashMap1 = new HashMap();
      HashMap localHashMap2 = new HashMap();
      HashMap localHashMap3 = new HashMap();
      int n = 0;
      do
      {
        for (;;)
        {
          int m;
          int k;
          int j;
          try
          {
            localPackageManager = n.this.getPackageManager();
            paramVarArgs = localPackageManager.getInstalledApplications(0);
            Collections.sort(paramVarArgs, new ApplicationInfo.DisplayNameComparator(localPackageManager));
            Iterator localIterator = paramVarArgs.iterator();
            i = 0;
            m = 0;
            if (localIterator.hasNext())
            {
              paramVarArgs = (ApplicationInfo)localIterator.next();
              k = i;
            }
          }
          catch (Exception paramVarArgs)
          {
            PackageManager localPackageManager;
            int i;
            String str1;
            boolean bool;
            continue;
          }
          try
          {
            str1 = paramVarArgs.packageName;
            j = i;
            k = m;
            if (str1 != null)
            {
              k = i;
              Object localObject = n.this.getPackageManager().getApplicationInfo(str1, 0);
              k = i;
              String str2 = (String)localPackageManager.getApplicationLabel((ApplicationInfo)localObject);
              j = i;
              k = m;
              if (str2 != null)
              {
                j = i;
                k = i;
                if ((paramVarArgs.flags & 0x1) == 0)
                {
                  k = i;
                  localObject = localPackageManager.getApplicationIcon((ApplicationInfo)localObject);
                  paramVarArgs = (Void[])localObject;
                  if (localObject == null)
                  {
                    k = i;
                    paramVarArgs = n.this.getResources().getDrawable(2131361792);
                  }
                  k = i;
                  localHashMap1.put(Integer.valueOf(i), str1);
                  k = i;
                  localHashMap2.put(Integer.valueOf(i), str2);
                  k = i;
                  localHashMap3.put(Integer.valueOf(i), paramVarArgs);
                  j = i + 1;
                }
                k = j;
                publishProgress(new Integer[] { Integer.valueOf(m) });
                k = m + 1;
              }
            }
          }
          catch (PackageManager.NameNotFoundException paramVarArgs)
          {
            j = k;
            k = m;
          }
        }
        bool = isCancelled();
        i = j;
        m = k;
      } while (!bool);
      try
      {
        n.a(n.this, new String[localHashMap3.size()]);
        n.b(n.this, new String[localHashMap2.size()]);
        n.a(n.this, new Drawable[localHashMap3.size()]);
        i = n;
        while (i < localHashMap2.size())
        {
          n.a(n.this)[i] = ((String)localHashMap2.get(Integer.valueOf(i)));
          n.b(n.this)[i] = ((Drawable)localHashMap3.get(Integer.valueOf(i)));
          n.c(n.this)[i] = ((String)localHashMap1.get(Integer.valueOf(i)));
          bool = isCancelled();
          if (bool) {
            break;
          }
          i += 1;
        }
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
      return Boolean.valueOf(isCancelled() ^ true);
    }
    
    protected void a(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue()) {
        try
        {
          paramBoolean = new n.b(n.this, n.this, n.b(n.this), n.a(n.this));
          ListView localListView = (ListView)n.this.findViewById(2131165367);
          localListView.setAdapter(paramBoolean);
          localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
          {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
              paramAnonymousAdapterView = new Intent("android.intent.action.DELETE", Uri.fromParts("package", n.c(n.this)[paramAnonymousInt], null));
              n.this.startActivity(paramAnonymousAdapterView);
            }
          });
        }
        catch (Exception paramBoolean)
        {
          n.e(n.this).a("PantOpcionesAplicacionesSeleccionadas", "onPostExecute", paramBoolean);
        }
      }
      n.d(n.this).dismiss();
    }
    
    protected void a(Integer... paramVarArgs)
    {
      ProgressDialog localProgressDialog = n.d(n.this);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cargando ");
      localStringBuilder.append(String.valueOf(paramVarArgs[0].intValue()));
      localProgressDialog.setMessage(localStringBuilder.toString());
    }
    
    protected void onCancelled() {}
    
    protected void onPreExecute()
    {
      n.a(n.this, ProgressDialog.show(n.this, null, "Cargando...", true, true, new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          n.a.this.cancel(true);
          n.this.finish();
        }
      }));
    }
  }
  
  private class b
    extends ArrayAdapter<String>
  {
    private final Activity b;
    private final Drawable[] c;
    private final String[] d;
    
    b(Activity paramActivity, Drawable[] paramArrayOfDrawable, String[] paramArrayOfString)
    {
      super(2131296298, paramArrayOfString);
      this.b = paramActivity;
      this.c = paramArrayOfDrawable;
      this.d = paramArrayOfString;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = this.b.getLayoutInflater().inflate(2131296298, null, true);
      paramViewGroup = (TextView)paramView.findViewById(2131165458);
      ImageView localImageView = (ImageView)paramView.findViewById(2131165351);
      paramViewGroup.setText(this.d[paramInt]);
      localImageView.setImageDrawable(this.c[paramInt]);
      return paramView;
    }
  }
}
