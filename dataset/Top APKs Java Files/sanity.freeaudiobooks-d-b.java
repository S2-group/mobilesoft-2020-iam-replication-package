package d;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.preference.PreferenceManager;
import com.facebook.appevents.g;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import sanity.freeaudiobooks.e;

public class b
  extends Activity
{
  private Drawable a;
  private g b;
  private boolean c = true;
  
  public b(final Context paramContext)
  {
    this.b = g.b(paramContext);
    new Thread(new Runnable()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("http://");
        ((StringBuilder)localObject1).append("kofii12345.fut");
        localObject1 = ((StringBuilder)localObject1).toString();
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("urehost.pl/homeadsystem/dialog");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("/dialog.xml");
        localObject2 = ((StringBuilder)localObject2).toString();
        localObject1 = new a();
        try
        {
          StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
          localObject2 = new URL((String)localObject2);
          Object localObject3 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
          ((XMLReader)localObject3).setContentHandler((ContentHandler)localObject1);
          ((XMLReader)localObject3).parse(new InputSource(((URL)localObject2).openStream()));
          localObject3 = PreferenceManager.getDefaultSharedPreferences(paramContext);
          Object localObject4 = ((SharedPreferences)localObject3).edit();
          Object localObject5 = PreferenceManager.getDefaultSharedPreferences(paramContext);
          localObject2 = ((SharedPreferences)localObject5).edit();
          if (b.a(b.this, ((a)localObject1).d(), paramContext)) {
            return;
          }
          if (!((SharedPreferences)localObject3).getString(((a)localObject1).d(), "defValue").equals(((a)localObject1).d()))
          {
            ((SharedPreferences.Editor)localObject2).putBoolean(((a)localObject1).a(), false);
            ((SharedPreferences.Editor)localObject2).apply();
          }
          boolean bool = ((SharedPreferences)localObject5).getBoolean(((a)localObject1).a(), false);
          ((SharedPreferences.Editor)localObject4).putString(((a)localObject1).d(), ((a)localObject1).d());
          ((SharedPreferences.Editor)localObject4).apply();
          if (!bool)
          {
            b.a(b.this, null);
            try
            {
              localObject3 = (InputStream)new URL(((a)localObject1).b()).getContent();
              b.a(b.this, Drawable.createFromStream((InputStream)localObject3, "src name"));
            }
            catch (Exception localException2)
            {
              localObject4 = System.out;
              localObject5 = new StringBuilder();
              ((StringBuilder)localObject5).append("Exc=");
              ((StringBuilder)localObject5).append(localException2);
              ((PrintStream)localObject4).println(((StringBuilder)localObject5).toString());
              localException2.printStackTrace();
            }
            b.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                e.a("showing homead");
                final AlertDialog localAlertDialog = new AlertDialog.Builder(b.1.this.a).create();
                localAlertDialog.setTitle(this.a.a());
                localAlertDialog.setMessage(this.a.c());
                localAlertDialog.setButton("Install", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    paramAnonymous3DialogInterface = b.1.1.this.a.e();
                    Intent localIntent = new Intent("android.intent.action.VIEW");
                    localIntent.setData(Uri.parse(paramAnonymous3DialogInterface));
                    b.1.this.a.startActivity(localIntent);
                  }
                });
                localAlertDialog.setButton3("Don't remind", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    b.1.1.this.b.putBoolean(b.1.1.this.a.a(), true);
                    b.1.1.this.b.commit();
                  }
                });
                localAlertDialog.setButton2("Later", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    localAlertDialog.hide();
                  }
                });
                localAlertDialog.setIcon(b.a(b.this));
                if (b.b(b.this)) {
                  localAlertDialog.show();
                }
              }
            });
            return;
          }
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          localObject2 = System.out;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("XML Pasing Excpetion = ");
          localStringBuilder.append(localException1);
          ((PrintStream)localObject2).println(localStringBuilder.toString());
        }
      }
    }).start();
  }
  
  private boolean a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void a()
  {
    this.c = false;
  }
}
