package com.intuit.mobile.identity.contentprovider;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SharedResourceContentProviderManager
{
  private Context context;
  private String groupNamespace;
  private String masterContentProviderNamespace;
  private Uri masterContentProviderUri;
  private String myContentProviderNamespace;
  private Uri myContentProviderUri;
  private String mySharedResourceName;
  
  public SharedResourceContentProviderManager(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.context = paramContext;
    this.groupNamespace = paramString1;
    this.myContentProviderNamespace = (paramString1 + paramString2);
    this.mySharedResourceName = paramString3;
    this.myContentProviderUri = Uri.parse("content://" + this.myContentProviderNamespace + "/" + paramString3);
    this.masterContentProviderNamespace = this.myContentProviderNamespace;
    this.masterContentProviderUri = this.myContentProviderUri;
    this.masterContentProviderNamespace = findMasterContentProviderNamespace();
    this.masterContentProviderUri = Uri.parse("content://" + this.masterContentProviderNamespace + "/" + paramString3);
  }
  
  private String findMasterContentProviderNamespace()
  {
    Object localObject1 = this.context.getContentResolver().acquireContentProviderClient(this.myContentProviderUri);
    if (localObject1 != null)
    {
      localObject2 = (SharedResourceContentProvider)((ContentProviderClient)localObject1).getLocalContentProvider();
      if (localObject2 != null)
      {
        localObject2 = ((SharedResourceContentProvider)localObject2).getResourceMasterContentProvider();
        if (localObject2 != null)
        {
          localObject3 = Uri.parse("content://" + (String)localObject2 + "/" + this.mySharedResourceName);
          if (this.context.getContentResolver().query((Uri)localObject3, new String[] { "shared_resource_master_content_provider" }, null, null, null) != null) {
            return localObject2;
          }
        }
      }
      ((ContentProviderClient)localObject1).release();
    }
    Object localObject2 = new ArrayList();
    localObject1 = this.context.getPackageManager().getInstalledPackages(8).iterator();
    String str;
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = ((PackageInfo)((Iterator)localObject1).next()).providers;
      if (localObject3 != null)
      {
        int j = localObject3.length;
        int i = 0;
        while (i < j)
        {
          str = localObject3[i];
          if ((str.authority != null) && (str.authority.startsWith(this.groupNamespace))) {
            ((List)localObject2).add(str.authority);
          }
          i += 1;
        }
      }
    }
    ((List)localObject2).remove(this.myContentProviderNamespace);
    Object localObject3 = ((List)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      str = (String)((Iterator)localObject3).next();
      try
      {
        localObject1 = Uri.parse("content://" + str + "/" + this.mySharedResourceName);
        Cursor localCursor = this.context.getContentResolver().query((Uri)localObject1, new String[] { "shared_resource_master_content_provider" }, null, null, null);
        if (localCursor != null)
        {
          localObject1 = null;
          if (localCursor.moveToFirst()) {
            localObject1 = localCursor.getString(localCursor.getColumnIndexOrThrow("shared_resource_master_content_provider"));
          }
          localCursor.close();
          if (localObject1 != null)
          {
            boolean bool = ((List)localObject2).contains(localObject1);
            if (bool) {
              return localObject1;
            }
          }
        }
      }
      catch (SecurityException localSecurityException)
      {
        Log.e("DIS-CLIENT", "SecurityException thrown trying to access content provider at location: " + str);
        Log.e("DIS-CLIENT", localSecurityException.getLocalizedMessage());
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.w("DIS-CLIENT", "IllegalArgumentException thrown trying to access content provider at location: " + str);
        Log.w("DIS-CLIENT", "This is likely because of an Android bug in verions prior to 4.0 where a request for an external CP is instead routed to the current APK because of matching name of implementing class.");
        Log.w("DIS-CLIENT", localIllegalArgumentException.getLocalizedMessage());
      }
      catch (Exception localException)
      {
        Log.e("DIS-CLIENT", "Exception thrown trying to access content provider at location: " + str);
        Log.e("DIS-CLIENT", localException.getLocalizedMessage());
      }
    }
    return this.myContentProviderNamespace;
  }
  
  public String getMasterContentProviderNamespace()
  {
    return this.masterContentProviderNamespace;
  }
  
  public Uri getMasterContentProviderUri()
  {
    return this.masterContentProviderUri;
  }
  
  public void insertData(ContentValues paramContentValues)
  {
    paramContentValues.put("shared_resource_master_content_provider", this.masterContentProviderNamespace);
    this.context.getContentResolver().insert(this.masterContentProviderUri, paramContentValues);
    if (!this.masterContentProviderNamespace.equals(this.myContentProviderNamespace))
    {
      paramContentValues = new ContentValues();
      paramContentValues.put("shared_resource_master_content_provider", this.masterContentProviderNamespace);
      this.context.getContentResolver().insert(this.myContentProviderUri, paramContentValues);
    }
  }
  
  public Cursor readData(String[] paramArrayOfString)
  {
    return this.context.getContentResolver().query(this.masterContentProviderUri, paramArrayOfString, null, null, null);
  }
}
