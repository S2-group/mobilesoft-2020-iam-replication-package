package com.bitrhymes.nativeutils.functions;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.bitrhymes.nativeutils.utils.Utils;
import java.util.Iterator;
import java.util.List;

public class IsAirAvailable
  implements FREFunction
{
  private FREContext context;
  
  public IsAirAvailable() {}
  
  public FREObject call(FREContext paramFREContext, FREObject[] paramArrayOfFREObject)
  {
    this.context = paramFREContext;
    try
    {
      boolean bool = isPackageExisted("com.adobe.air");
      Log.i("IsAirAvailable", "PKG : " + bool);
      paramFREContext = FREObject.newObject(bool);
      return paramFREContext;
    }
    catch (FREWrongThreadException paramFREContext)
    {
      paramFREContext.printStackTrace();
      this.context.dispatchStatusEventAsync("ERROR_EVENT", paramFREContext.toString() + "," + paramFREContext.getMessage() + "," + paramFREContext.getLocalizedMessage() + "," + Utils.getExceptionStackTraceAsString(paramFREContext));
      return null;
    }
    catch (Exception paramFREContext)
    {
      paramFREContext.printStackTrace();
      this.context.dispatchStatusEventAsync("ERROR_EVENT", paramFREContext.toString() + "," + paramFREContext.getMessage() + "," + paramFREContext.getLocalizedMessage() + "," + Utils.getExceptionStackTraceAsString(paramFREContext));
    }
    return null;
  }
  
  public boolean isPackageExisted(String paramString)
  {
    Iterator localIterator = this.context.getActivity().getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    return true;
  }
}
