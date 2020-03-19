package com.xd.pisces.server;

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
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.xd.pisces.remote.VParceledListSlice;
import java.util.ArrayList;
import java.util.List;

public abstract interface IPackageManager
  extends IInterface
{
  public abstract boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString)
    throws RemoteException;
  
  public abstract int checkPermission(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    throws RemoteException;
  
  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract String[] getDangerousPermissions(String paramString)
    throws RemoteException;
  
  public abstract VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract String getNameForUid(int paramInt)
    throws RemoteException;
  
  public abstract PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract IPackageInstaller getPackageInstaller()
    throws RemoteException;
  
  public abstract int getPackageUid(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract String[] getPackagesForUid(int paramInt)
    throws RemoteException;
  
  public abstract PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<String> getSharedLibraries(String paramString)
    throws RemoteException;
  
  public abstract VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<ResolveInfo> queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<ResolveInfo> queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<ResolveInfo> queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract List<String> querySharedPackages(String paramString)
    throws RemoteException;
  
  public abstract ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IPackageManager
  {
    private static final String DESCRIPTOR = "com.xd.pisces.server.IPackageManager";
    static final int TRANSACTION_activitySupportsIntent = 7;
    static final int TRANSACTION_checkPermission = 4;
    static final int TRANSACTION_getActivityInfo = 6;
    static final int TRANSACTION_getAllPermissionGroups = 22;
    static final int TRANSACTION_getApplicationInfo = 24;
    static final int TRANSACTION_getDangerousPermissions = 29;
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
      attachInterface(this, "com.xd.pisces.server.IPackageManager");
    }
    
    public static IPackageManager asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.xd.pisces.server.IPackageManager");
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
      throws RemoteException
    {
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject11 = null;
      Object localObject2 = null;
      Object localObject12 = null;
      Object localObject1 = null;
      int i = 0;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.xd.pisces.server.IPackageManager");
        return true;
      case 29: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = getDangerousPermissions(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringArray(paramParcel1);
        return true;
      case 28: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject2 = getPackageInstaller();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject1;
        if (localObject2 != null) {
          paramParcel1 = ((IPackageInstaller)localObject2).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 27: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = getNameForUid(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 26: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = querySharedPackages(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(paramParcel1);
        return true;
      case 25: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 24: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 23: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 22: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = getAllPermissionGroups(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 20: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = queryPermissionsByGroup(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 18: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 17: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 16: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = queryIntentContentProviders((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = queryIntentServices((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject5;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = resolveService((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject6;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = queryIntentReceivers((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject7;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = queryIntentActivities((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(paramParcel1);
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = resolveIntent((Intent)localObject1, paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject9;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = getProviderInfo((ComponentName)localObject1, paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject10;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = getServiceInfo((ComponentName)localObject1, paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject11;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = getReceiverInfo((ComponentName)localObject1, paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
        } else {
          localObject1 = null;
        }
        if (paramParcel1.readInt() != 0) {
          localObject2 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        boolean bool = activitySupportsIntent((ComponentName)localObject1, (Intent)localObject2, paramParcel1.readString());
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        localObject1 = localObject12;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = getActivityInfo((ComponentName)localObject1, paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
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
      case 4: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramInt1 = checkPermission(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = getSharedLibraries(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(paramParcel1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
        paramParcel1 = getPackagesForUid(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStringArray(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.xd.pisces.server.IPackageManager");
      paramInt1 = getPackageUid(paramParcel1.readString(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
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
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          boolean bool = false;
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public int checkPermission(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramComponentName = null;
          if (paramInt1 != 0) {
            paramComponentName = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramComponentName;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramString = null;
          if (paramInt1 != 0) {
            paramString = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String[] getDangerousPermissions(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
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
      
      public VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          VParceledListSlice localVParceledListSlice = null;
          if (paramInt1 != 0) {
            localVParceledListSlice = (VParceledListSlice)VParceledListSlice.CREATOR.createFromParcel(localParcel2);
          }
          return localVParceledListSlice;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          VParceledListSlice localVParceledListSlice = null;
          if (paramInt1 != 0) {
            localVParceledListSlice = (VParceledListSlice)VParceledListSlice.CREATOR.createFromParcel(localParcel2);
          }
          return localVParceledListSlice;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.xd.pisces.server.IPackageManager";
      }
      
      public String getNameForUid(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramString = null;
          if (paramInt1 != 0) {
            paramString = (PackageInfo)PackageInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IPackageInstaller getPackageInstaller()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IPackageInstaller localIPackageInstaller = IPackageInstaller.Stub.asInterface(localParcel2.readStrongBinder());
          return localIPackageInstaller;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int getPackageUid(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          paramString = null;
          if (paramInt != 0) {
            paramString = (PermissionGroupInfo)PermissionGroupInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public PermissionInfo getPermissionInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          paramString = null;
          if (paramInt != 0) {
            paramString = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramComponentName = null;
          if (paramInt1 != 0) {
            paramComponentName = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramComponentName;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramComponentName = null;
          if (paramInt1 != 0) {
            paramComponentName = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramComponentName;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramComponentName = null;
          if (paramInt1 != 0) {
            paramComponentName = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramComponentName;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<String> getSharedLibraries(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramString = null;
          if (paramInt1 != 0) {
            paramString = (VParceledListSlice)VParceledListSlice.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<ResolveInfo> queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramIntent = localParcel2.createTypedArrayList(ResolveInfo.CREATOR);
          return paramIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramIntent = localParcel2.createTypedArrayList(ResolveInfo.CREATOR);
          return paramIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<ResolveInfo> queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramIntent = localParcel2.createTypedArrayList(ResolveInfo.CREATOR);
          return paramIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<ResolveInfo> queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramIntent = localParcel2.createTypedArrayList(ResolveInfo.CREATOR);
          return paramIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public List<String> querySharedPackages(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
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
      
      public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(23, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramString = null;
          if (paramInt1 != 0) {
            paramString = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramIntent = null;
          if (paramInt1 != 0) {
            paramIntent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xd.pisces.server.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt1 = localParcel2.readInt();
          paramIntent = null;
          if (paramInt1 != 0) {
            paramIntent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
          }
          return paramIntent;
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
