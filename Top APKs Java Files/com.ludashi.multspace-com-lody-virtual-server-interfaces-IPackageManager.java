package com.lody.virtual.server.interfaces;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.lody.virtual.remote.VParceledListSlice;
import java.util.ArrayList;
import java.util.List;

public abstract interface IPackageManager
  extends IInterface
{
  public abstract boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString);
  
  public abstract int checkPermission(String paramString1, String paramString2, int paramInt);
  
  public abstract int checkSignatures(String paramString1, String paramString2);
  
  public abstract ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract String[] getAllAuthorities();
  
  public abstract List getAllPermissionGroups(int paramInt);
  
  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2);
  
  public abstract String[] getDangrousPermissions(String paramString);
  
  public abstract VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2);
  
  public abstract VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2);
  
  public abstract String getNameForUid(int paramInt);
  
  public abstract PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2);
  
  public abstract IBinder getPackageInstaller();
  
  public abstract int getPackageUid(String paramString, int paramInt);
  
  public abstract String[] getPackagesForUid(int paramInt);
  
  public abstract PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt);
  
  public abstract PermissionInfo getPermissionInfo(String paramString, int paramInt);
  
  public abstract ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract List getSharedLibraries(String paramString);
  
  public abstract VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryPermissionsByGroup(String paramString, int paramInt);
  
  public abstract List querySharedPackages(String paramString);
  
  public abstract ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2);
  
  public abstract ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public static abstract class Stub
    extends Binder
    implements IPackageManager
  {
    private static final String DESCRIPTOR = "com.lody.virtual.server.interfaces.IPackageManager";
    static final int TRANSACTION_activitySupportsIntent = 7;
    static final int TRANSACTION_checkPermission = 4;
    static final int TRANSACTION_checkSignatures = 29;
    static final int TRANSACTION_getActivityInfo = 6;
    static final int TRANSACTION_getAllAuthorities = 31;
    static final int TRANSACTION_getAllPermissionGroups = 22;
    static final int TRANSACTION_getApplicationInfo = 24;
    static final int TRANSACTION_getDangrousPermissions = 30;
    static final int TRANSACTION_getInstalledApplications = 18;
    static final int TRANSACTION_getInstalledPackages = 17;
    static final int TRANSACTION_getNameForUid = 27;
    static final int TRANSACTION_getPackageInfo = 5;
    static final int TRANSACTION_getPackageInstaller = 28;
    static final int TRANSACTION_getPackageUid = 1;
    static final int TRANSACTION_getPackagesForUid = 2;
    static final int TRANSACTION_getPermissionGroupInfo = 21;
    static final int TRANSACTION_getPermissionInfo = 19;
    static final int TRANSACTION_getProviderInfo = 10;
    static final int TRANSACTION_getReceiverInfo = 8;
    static final int TRANSACTION_getServiceInfo = 9;
    static final int TRANSACTION_getSharedLibraries = 3;
    static final int TRANSACTION_queryContentProviders = 25;
    static final int TRANSACTION_queryIntentActivities = 12;
    static final int TRANSACTION_queryIntentContentProviders = 16;
    static final int TRANSACTION_queryIntentReceivers = 13;
    static final int TRANSACTION_queryIntentServices = 15;
    static final int TRANSACTION_queryPermissionsByGroup = 20;
    static final int TRANSACTION_querySharedPackages = 26;
    static final int TRANSACTION_resolveContentProvider = 23;
    static final int TRANSACTION_resolveIntent = 11;
    static final int TRANSACTION_resolveService = 14;
    
    public Stub()
    {
      attachInterface(this, "com.lody.virtual.server.interfaces.IPackageManager");
    }
    
    public static IPackageManager asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.lody.virtual.server.interfaces.IPackageManager");
      if ((localIInterface != null) && ((localIInterface instanceof IPackageManager))) {
        return (IPackageManager)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    {
      Object localObject;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.lody.virtual.server.interfaces.IPackageManager");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramInt1 = getPackageUid(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getPackagesForUid(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStringArray(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getSharedLibraries(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(paramParcel1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramInt1 = checkPermission(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getPackageInfo(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = getActivityInfo((ComponentName)localObject, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        Intent localIntent;
        if (paramParcel1.readInt() != 0)
        {
          localObject = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label615;
          }
          localIntent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          boolean bool = activitySupportsIntent((ComponentName)localObject, localIntent, paramParcel1.readString());
          paramParcel2.writeNoException();
          if (!bool) {
            break label621;
          }
        }
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
          localObject = null;
          break;
          localIntent = null;
          break label577;
        }
      case 8: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = getReceiverInfo((ComponentName)localObject, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = getServiceInfo((ComponentName)localObject, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = getProviderInfo((ComponentName)localObject, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = resolveIntent((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = queryIntentActivities((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(paramParcel1);
          return true;
        }
      case 13: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = queryIntentReceivers((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(paramParcel1);
          return true;
        }
      case 14: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = resolveService((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = queryIntentServices((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(paramParcel1);
          return true;
        }
      case 16: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        if (paramParcel1.readInt() != 0) {}
        for (localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localObject = null)
        {
          paramParcel1 = queryIntentContentProviders((Intent)localObject, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(paramParcel1);
          return true;
        }
      case 17: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getInstalledPackages(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getInstalledApplications(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getPermissionInfo(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = queryPermissionsByGroup(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getPermissionGroupInfo(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getAllPermissionGroups(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = resolveContentProvider(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 24: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getApplicationInfo(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 25: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = queryContentProviders(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 26: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = querySharedPackages(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(paramParcel1);
        return true;
      case 27: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getNameForUid(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 28: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getPackageInstaller();
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 29: 
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramInt1 = checkSignatures(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 30: 
        label577:
        label615:
        label621:
        paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
        paramParcel1 = getDangrousPermissions(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringArray(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.lody.virtual.server.interfaces.IPackageManager");
      paramParcel1 = getAllAuthorities();
      paramParcel2.writeNoException();
      paramParcel2.writeStringArray(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements IPackageManager
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString)
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              if (paramIntent != null)
              {
                localParcel1.writeInt(1);
                paramIntent.writeToParcel(localParcel1, 0);
                localParcel1.writeString(paramString);
                this.mRemote.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                if (i == 0) {
                  break label138;
                }
                return bool;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label138:
          bool = false;
        }
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public int checkPermission(String paramString1, String paramString2, int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          return paramInt;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int checkSignatures(String paramString1, String paramString2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(6, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramComponentName = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
                return paramComponentName;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramComponentName = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public String[] getAllAuthorities()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          this.mRemote.transact(31, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String[] arrayOfString = localParcel2.createStringArray();
          return arrayOfString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List getAllPermissionGroups(int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.createTypedArrayList(PermissionGroupInfo.CREATOR);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload 4
        //   31: iload_3
        //   32: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: bipush 24
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokeinterface 56 5 0
        //   51: pop
        //   52: aload 5
        //   54: invokevirtual 59	android/os/Parcel:readException	()V
        //   57: aload 5
        //   59: invokevirtual 63	android/os/Parcel:readInt	()I
        //   62: ifeq +29 -> 91
        //   65: getstatic 105	android/content/pm/ApplicationInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   68: aload 5
        //   70: invokeinterface 86 2 0
        //   75: checkcast 104	android/content/pm/ApplicationInfo
        //   78: astore_1
        //   79: aload 5
        //   81: invokevirtual 66	android/os/Parcel:recycle	()V
        //   84: aload 4
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload_1
        //   90: areturn
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -14 -> 79
        //   96: astore_1
        //   97: aload 5
        //   99: invokevirtual 66	android/os/Parcel:recycle	()V
        //   102: aload 4
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramString	String
        //   0	109	2	paramInt1	int
        //   0	109	3	paramInt2	int
        //   3	100	4	localParcel1	Parcel
        //   8	90	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	79	96	finally
      }
      
      public String[] getDangrousPermissions(String paramString)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString);
          this.mRemote.transact(30, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.createStringArray();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload_0
        //   30: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   33: bipush 18
        //   35: aload 4
        //   37: aload 5
        //   39: iconst_0
        //   40: invokeinterface 56 5 0
        //   45: pop
        //   46: aload 5
        //   48: invokevirtual 59	android/os/Parcel:readException	()V
        //   51: aload 5
        //   53: invokevirtual 63	android/os/Parcel:readInt	()I
        //   56: ifeq +29 -> 85
        //   59: getstatic 114	com/lody/virtual/remote/VParceledListSlice:CREATOR	Landroid/os/Parcelable$ClassLoaderCreator;
        //   62: aload 5
        //   64: invokeinterface 117 2 0
        //   69: checkcast 111	com/lody/virtual/remote/VParceledListSlice
        //   72: astore_3
        //   73: aload 5
        //   75: invokevirtual 66	android/os/Parcel:recycle	()V
        //   78: aload 4
        //   80: invokevirtual 66	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: areturn
        //   85: aconst_null
        //   86: astore_3
        //   87: goto -14 -> 73
        //   90: astore_3
        //   91: aload 5
        //   93: invokevirtual 66	android/os/Parcel:recycle	()V
        //   96: aload 4
        //   98: invokevirtual 66	android/os/Parcel:recycle	()V
        //   101: aload_3
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramInt1	int
        //   0	103	2	paramInt2	int
        //   72	15	3	localVParceledListSlice	VParceledListSlice
        //   90	12	3	localObject	Object
        //   3	94	4	localParcel1	Parcel
        //   8	84	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	73	90	finally
      }
      
      /* Error */
      public VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload_0
        //   30: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   33: bipush 17
        //   35: aload 4
        //   37: aload 5
        //   39: iconst_0
        //   40: invokeinterface 56 5 0
        //   45: pop
        //   46: aload 5
        //   48: invokevirtual 59	android/os/Parcel:readException	()V
        //   51: aload 5
        //   53: invokevirtual 63	android/os/Parcel:readInt	()I
        //   56: ifeq +29 -> 85
        //   59: getstatic 114	com/lody/virtual/remote/VParceledListSlice:CREATOR	Landroid/os/Parcelable$ClassLoaderCreator;
        //   62: aload 5
        //   64: invokeinterface 117 2 0
        //   69: checkcast 111	com/lody/virtual/remote/VParceledListSlice
        //   72: astore_3
        //   73: aload 5
        //   75: invokevirtual 66	android/os/Parcel:recycle	()V
        //   78: aload 4
        //   80: invokevirtual 66	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: areturn
        //   85: aconst_null
        //   86: astore_3
        //   87: goto -14 -> 73
        //   90: astore_3
        //   91: aload 5
        //   93: invokevirtual 66	android/os/Parcel:recycle	()V
        //   96: aload 4
        //   98: invokevirtual 66	android/os/Parcel:recycle	()V
        //   101: aload_3
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramInt1	int
        //   0	103	2	paramInt2	int
        //   72	15	3	localVParceledListSlice	VParceledListSlice
        //   90	12	3	localObject	Object
        //   3	94	4	localParcel1	Parcel
        //   8	84	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	73	90	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.lody.virtual.server.interfaces.IPackageManager";
      }
      
      public String getNameForUid(int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(27, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload 4
        //   31: iload_3
        //   32: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: iconst_5
        //   40: aload 4
        //   42: aload 5
        //   44: iconst_0
        //   45: invokeinterface 56 5 0
        //   50: pop
        //   51: aload 5
        //   53: invokevirtual 59	android/os/Parcel:readException	()V
        //   56: aload 5
        //   58: invokevirtual 63	android/os/Parcel:readInt	()I
        //   61: ifeq +29 -> 90
        //   64: getstatic 130	android/content/pm/PackageInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   67: aload 5
        //   69: invokeinterface 86 2 0
        //   74: checkcast 129	android/content/pm/PackageInfo
        //   77: astore_1
        //   78: aload 5
        //   80: invokevirtual 66	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: invokevirtual 66	android/os/Parcel:recycle	()V
        //   88: aload_1
        //   89: areturn
        //   90: aconst_null
        //   91: astore_1
        //   92: goto -14 -> 78
        //   95: astore_1
        //   96: aload 5
        //   98: invokevirtual 66	android/os/Parcel:recycle	()V
        //   101: aload 4
        //   103: invokevirtual 66	android/os/Parcel:recycle	()V
        //   106: aload_1
        //   107: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	108	0	this	Proxy
        //   0	108	1	paramString	String
        //   0	108	2	paramInt1	int
        //   0	108	3	paramInt2	int
        //   3	99	4	localParcel1	Parcel
        //   8	89	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	78	95	finally
      }
      
      public IBinder getPackageInstaller()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IBinder localIBinder = localParcel2.readStrongBinder();
          return localIBinder;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int getPackageUid(String paramString, int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          return paramInt;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String[] getPackagesForUid(int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String[] arrayOfString = localParcel2.createStringArray();
          return arrayOfString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_3
        //   21: iload_2
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_0
        //   26: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   29: bipush 21
        //   31: aload_3
        //   32: aload 4
        //   34: iconst_0
        //   35: invokeinterface 56 5 0
        //   40: pop
        //   41: aload 4
        //   43: invokevirtual 59	android/os/Parcel:readException	()V
        //   46: aload 4
        //   48: invokevirtual 63	android/os/Parcel:readInt	()I
        //   51: ifeq +28 -> 79
        //   54: getstatic 96	android/content/pm/PermissionGroupInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   57: aload 4
        //   59: invokeinterface 86 2 0
        //   64: checkcast 95	android/content/pm/PermissionGroupInfo
        //   67: astore_1
        //   68: aload 4
        //   70: invokevirtual 66	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 66	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: areturn
        //   79: aconst_null
        //   80: astore_1
        //   81: goto -13 -> 68
        //   84: astore_1
        //   85: aload 4
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	Proxy
        //   0	96	1	paramString	String
        //   0	96	2	paramInt	int
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	68	84	finally
      }
      
      /* Error */
      public PermissionInfo getPermissionInfo(String paramString, int paramInt)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_3
        //   21: iload_2
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_0
        //   26: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   29: bipush 19
        //   31: aload_3
        //   32: aload 4
        //   34: iconst_0
        //   35: invokeinterface 56 5 0
        //   40: pop
        //   41: aload 4
        //   43: invokevirtual 59	android/os/Parcel:readException	()V
        //   46: aload 4
        //   48: invokevirtual 63	android/os/Parcel:readInt	()I
        //   51: ifeq +28 -> 79
        //   54: getstatic 145	android/content/pm/PermissionInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   57: aload 4
        //   59: invokeinterface 86 2 0
        //   64: checkcast 144	android/content/pm/PermissionInfo
        //   67: astore_1
        //   68: aload 4
        //   70: invokevirtual 66	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 66	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: areturn
        //   79: aconst_null
        //   80: astore_1
        //   81: goto -13 -> 68
        //   84: astore_1
        //   85: aload 4
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	Proxy
        //   0	96	1	paramString	String
        //   0	96	2	paramInt	int
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	68	84	finally
      }
      
      public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(10, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramComponentName = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(localParcel2);
                return paramComponentName;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramComponentName = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(8, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramComponentName = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
                return paramComponentName;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramComponentName = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(9, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramComponentName = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(localParcel2);
                return paramComponentName;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramComponentName = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public List getSharedLibraries(String paramString)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.createStringArrayList();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload 4
        //   31: iload_3
        //   32: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: bipush 25
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokeinterface 56 5 0
        //   51: pop
        //   52: aload 5
        //   54: invokevirtual 59	android/os/Parcel:readException	()V
        //   57: aload 5
        //   59: invokevirtual 63	android/os/Parcel:readInt	()I
        //   62: ifeq +29 -> 91
        //   65: getstatic 114	com/lody/virtual/remote/VParceledListSlice:CREATOR	Landroid/os/Parcelable$ClassLoaderCreator;
        //   68: aload 5
        //   70: invokeinterface 117 2 0
        //   75: checkcast 111	com/lody/virtual/remote/VParceledListSlice
        //   78: astore_1
        //   79: aload 5
        //   81: invokevirtual 66	android/os/Parcel:recycle	()V
        //   84: aload 4
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload_1
        //   90: areturn
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -14 -> 79
        //   96: astore_1
        //   97: aload 5
        //   99: invokevirtual 66	android/os/Parcel:recycle	()V
        //   102: aload 4
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramString	String
        //   0	109	2	paramInt1	int
        //   0	109	3	paramInt2	int
        //   3	100	4	localParcel1	Parcel
        //   8	90	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	79	96	finally
      }
      
      /* Error */
      public List queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 47	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   57: bipush 12
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 56 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 59	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: getstatic 169	android/content/pm/ResolveInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: invokevirtual 100	android/os/Parcel:createTypedArrayList	(Landroid/os/Parcelable$Creator;)Ljava/util/ArrayList;
        //   83: astore_1
        //   84: aload 6
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: areturn
        //   96: aload 5
        //   98: iconst_0
        //   99: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   102: goto -68 -> 34
        //   105: astore_1
        //   106: aload 6
        //   108: invokevirtual 66	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 66	android/os/Parcel:recycle	()V
        //   116: aload_1
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	Proxy
        //   0	118	1	paramIntent	Intent
        //   0	118	2	paramString	String
        //   0	118	3	paramInt1	int
        //   0	118	4	paramInt2	int
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	105	finally
        //   21	34	105	finally
        //   34	84	105	finally
        //   96	102	105	finally
      }
      
      /* Error */
      public List queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 47	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   57: bipush 16
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 56 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 59	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: getstatic 169	android/content/pm/ResolveInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: invokevirtual 100	android/os/Parcel:createTypedArrayList	(Landroid/os/Parcelable$Creator;)Ljava/util/ArrayList;
        //   83: astore_1
        //   84: aload 6
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: areturn
        //   96: aload 5
        //   98: iconst_0
        //   99: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   102: goto -68 -> 34
        //   105: astore_1
        //   106: aload 6
        //   108: invokevirtual 66	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 66	android/os/Parcel:recycle	()V
        //   116: aload_1
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	Proxy
        //   0	118	1	paramIntent	Intent
        //   0	118	2	paramString	String
        //   0	118	3	paramInt1	int
        //   0	118	4	paramInt2	int
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	105	finally
        //   21	34	105	finally
        //   34	84	105	finally
        //   96	102	105	finally
      }
      
      /* Error */
      public List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 47	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   57: bipush 13
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 56 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 59	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: getstatic 169	android/content/pm/ResolveInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: invokevirtual 100	android/os/Parcel:createTypedArrayList	(Landroid/os/Parcelable$Creator;)Ljava/util/ArrayList;
        //   83: astore_1
        //   84: aload 6
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: areturn
        //   96: aload 5
        //   98: iconst_0
        //   99: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   102: goto -68 -> 34
        //   105: astore_1
        //   106: aload 6
        //   108: invokevirtual 66	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 66	android/os/Parcel:recycle	()V
        //   116: aload_1
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	Proxy
        //   0	118	1	paramIntent	Intent
        //   0	118	2	paramString	String
        //   0	118	3	paramInt1	int
        //   0	118	4	paramInt2	int
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	105	finally
        //   21	34	105	finally
        //   34	84	105	finally
        //   96	102	105	finally
      }
      
      /* Error */
      public List queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 47	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   57: bipush 15
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 56 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 59	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: getstatic 169	android/content/pm/ResolveInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: invokevirtual 100	android/os/Parcel:createTypedArrayList	(Landroid/os/Parcelable$Creator;)Ljava/util/ArrayList;
        //   83: astore_1
        //   84: aload 6
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 66	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: areturn
        //   96: aload 5
        //   98: iconst_0
        //   99: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   102: goto -68 -> 34
        //   105: astore_1
        //   106: aload 6
        //   108: invokevirtual 66	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 66	android/os/Parcel:recycle	()V
        //   116: aload_1
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	Proxy
        //   0	118	1	paramIntent	Intent
        //   0	118	2	paramString	String
        //   0	118	3	paramInt1	int
        //   0	118	4	paramInt2	int
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	105	finally
        //   21	34	105	finally
        //   34	84	105	finally
        //   96	102	105	finally
      }
      
      public List queryPermissionsByGroup(String paramString, int paramInt)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.createTypedArrayList(PermissionInfo.CREATOR);
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List querySharedPackages(String paramString)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
          localParcel1.writeString(paramString);
          this.mRemote.transact(26, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.createStringArrayList();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 50	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload 4
        //   31: iload_3
        //   32: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 19	com/lody/virtual/server/interfaces/IPackageManager$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: bipush 23
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokeinterface 56 5 0
        //   51: pop
        //   52: aload 5
        //   54: invokevirtual 59	android/os/Parcel:readException	()V
        //   57: aload 5
        //   59: invokevirtual 63	android/os/Parcel:readInt	()I
        //   62: ifeq +29 -> 91
        //   65: getstatic 150	android/content/pm/ProviderInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   68: aload 5
        //   70: invokeinterface 86 2 0
        //   75: checkcast 149	android/content/pm/ProviderInfo
        //   78: astore_1
        //   79: aload 5
        //   81: invokevirtual 66	android/os/Parcel:recycle	()V
        //   84: aload 4
        //   86: invokevirtual 66	android/os/Parcel:recycle	()V
        //   89: aload_1
        //   90: areturn
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -14 -> 79
        //   96: astore_1
        //   97: aload 5
        //   99: invokevirtual 66	android/os/Parcel:recycle	()V
        //   102: aload 4
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramString	String
        //   0	109	2	paramInt1	int
        //   0	109	3	paramInt2	int
        //   3	100	4	localParcel1	Parcel
        //   8	90	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	79	96	finally
      }
      
      public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(11, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramIntent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
                return paramIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.lody.virtual.server.interfaces.IPackageManager");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              localParcel1.writeInt(paramInt1);
              localParcel1.writeInt(paramInt2);
              this.mRemote.transact(14, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramIntent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
                return paramIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}
