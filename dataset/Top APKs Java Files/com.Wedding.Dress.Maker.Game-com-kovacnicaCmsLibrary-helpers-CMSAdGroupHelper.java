package com.kovacnicaCmsLibrary.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kovacnicaCmsLibrary.models.CMSAdGroup;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CMSAdGroupHelper
{
  Gson gson;
  SharedPreferences.Editor sharedEditor;
  SharedPreferences sharedPref;
  
  public CMSAdGroupHelper(Context paramContext)
  {
    this.sharedPref = paramContext.getSharedPreferences("com.cms.kovacnica", 0);
    this.sharedEditor = this.sharedPref.edit();
    this.gson = new Gson();
  }
  
  public ArrayList<String> allPN(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((ApplicationInfo)paramContext.next()).packageName);
    }
    return localArrayList;
  }
  
  public HashMap<String, CMSAdGroup> loadAdGroups()
  {
    Object localObject = null;
    String str = this.sharedPref.getString("com.cms.kovacnica.gais", null);
    if (str != null)
    {
      localObject = new TypeToken() {}.getType();
      localObject = (HashMap)this.gson.fromJson(str, (Type)localObject);
    }
    return localObject;
  }
  
  public ArrayList<String> loadChangedAdGroupsIds()
  {
    Object localObject = null;
    String str = this.sharedPref.getString("com.cms.kovacnica.adgcid", null);
    if (str != null)
    {
      localObject = new TypeToken() {}.getType();
      localObject = (ArrayList)this.gson.fromJson(str, (Type)localObject);
    }
    return localObject;
  }
  
  public ArrayList<String> loadIdentitesForGroupId(String paramString)
  {
    Object localObject = null;
    String str = this.sharedPref.getString("com.cms.kovacnica.gaisidentprefix" + paramString, null);
    paramString = localObject;
    if (str != null)
    {
      paramString = new TypeToken() {}.getType();
      paramString = (ArrayList)this.gson.fromJson(str, paramString);
    }
    return paramString;
  }
  
  public void saveAdGroups(HashMap<String, CMSAdGroup> paramHashMap)
  {
    this.sharedEditor.putString("com.cms.kovacnica.gais", this.gson.toJson(paramHashMap)).commit();
  }
  
  public void saveChangedAdGroupsIds(ArrayList<String> paramArrayList)
  {
    this.sharedEditor.putString("com.cms.kovacnica.adgcid", this.gson.toJson(paramArrayList)).commit();
  }
  
  public void saveIdentitesForGroupId(String paramString, ArrayList<String> paramArrayList)
  {
    this.sharedEditor.putString("com.cms.kovacnica.gaisidentprefix" + paramString, this.gson.toJson(paramArrayList)).commit();
  }
}
