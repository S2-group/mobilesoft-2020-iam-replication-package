package com.anmolahuja.cameralocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;

import com.anmolahuja.cameralocker.preferences.CameraLockerPreferenceManager;
import com.anmolahuja.cameralocker.sqldb.CameraLockerDatabase;
import com.anmolahuja.cameralocker.sqldb.PackageDataHelper;
import com.commonsware.cwac.loaderex.acl.SQLiteCursorLoader;

public class PackageData implements OnLoadCompleteListener<Cursor>, PackageDataHelper.PackageConstants
{
	public interface LoadCallback
	{
		public void onLoad();
	}

	private ArrayList<PackageInfo> m_packageList;
	private Context m_context;
	private PackageManager m_packageManager;

	// maintaining a map as updates via the loader trigger onContentChanged()
	// which basically just calls forceLoad() and reloads the cursor
	private HashMap<String, Boolean> m_allowedPackageMap = null;
	private LoadCallback m_callback = null;
	private SQLiteCursorLoader m_loader;
	private static PackageData s_instance;

	public static PackageData instance( Context context )
	{
		if( s_instance == null )
			s_instance = new PackageData( context.getApplicationContext() );
		return s_instance;
	}

	private PackageData( Context context )
	{
		assert Thread.currentThread() == Looper.getMainLooper().getThread();
		m_context = context;
		m_packageManager = m_context.getPackageManager();
		reloadPackageList();
		String query = "SELECT * FROM " + TABLE_NAME_PACKAGES; /** + " ORDER BY " + PACKAGE_NAME + " COLLATE NOCASE " + " ASC" */
		m_loader = CameraLockerDatabase.instance( m_context ).packageDatabase().loaderForQuery( query );
		m_loader.registerListener( 0, this );
		reloadSelectedPackages( null );
	}

	public boolean usesCamera( final String packageName )
	{
		for( final PackageInfo packageInfo : m_packageList )
			if( packageName.equals( packageInfo.packageName ) )
				return true;
		return false;
	}

	public int size()
	{
		return m_packageList.size();
	}

	public PackageInfo getInfo( int position )
	{
		return m_packageList.get( position );
	}

	public String getName( final PackageInfo info )
	{
		return m_packageManager.getApplicationLabel( info.applicationInfo ).toString();
	}

	public Drawable getIcon( final PackageInfo info )
	{
		return m_packageManager.getApplicationIcon( info.applicationInfo );
	}

	public boolean isSystemApp( final String packageName )
	{
		ApplicationInfo info;
		try
		{
			info = m_packageManager.getApplicationInfo( packageName, 0 );
			return ( info.flags & ApplicationInfo.FLAG_SYSTEM ) == 0;
		}
		catch( NameNotFoundException e )
		{
			e.printStackTrace();
		}
		return false;
	}

	public void setChecked( final String packageName, final boolean checked )
	{
		assert packageName != null;
		final ContentValues cv = new ContentValues();
		final PackageDataHelper packageDataHelper = CameraLockerDatabase.instance( m_context ).packageDatabase();
		cv.put( ALLOW, checked ? 1 : 0 );
		if( !m_allowedPackageMap.containsKey( packageName ) )
		{
			cv.put( PACKAGE_NAME, packageName );
			cv.put( SYSTEM, isSystemApp( packageName ) ? 1 : 0 );
			m_allowedPackageMap.put( packageName, checked );
			new Thread( new Runnable()
			{
				@Override
				public void run()
				{
					packageDataHelper.getWritableDatabase().insert( TABLE_NAME_PACKAGES, null, cv );
				}
			} ).start();
			// NOT using m_loader.insert as it triggers CUrsorLoader forceReload()
		}
		else
		{
			m_allowedPackageMap.put( packageName, checked );
			new Thread( new Runnable()
			{
				@Override
				public void run()
				{
					packageDataHelper.getWritableDatabase().update( TABLE_NAME_PACKAGES, cv, PACKAGE_NAME + " = ?" , new String[]{ packageName } );
				}
			} ).start();
			// again, not using m_loader.update
		}
	}

	public boolean isChecked( final String name )
	{
		Boolean isChecked = m_allowedPackageMap.get( name );
		return isChecked == null || isChecked; // if not in map it's a new app, allow by default
		// TODO maybe check earlier, warn user of new apps with camera access
	}

	/**
	 * Reload the selected packages from the database.
	 * Occurs in a separate thread.
	 */
	public void reloadSelectedPackages( LoadCallback callback )
	{
		m_callback = callback;
		m_loader.reset();
		m_loader.startLoading();
	}

	/**
	 * Reload the list of packages, must call on the main thread.
	 */
	public void reloadPackageList()
	{
		synchronized( this )
		{
			// REMEMBER: call getInstalledPackages() only in the UI Thread
			final List<PackageInfo> packageList = m_packageManager
					.getInstalledPackages( PackageManager.GET_PERMISSIONS );
			final boolean displaySystemApps = CameraLockerPreferenceManager.instance( m_context ).displaySystemApps();
			final String cameraLockerPackage = m_context.getPackageName();
			if( m_packageList == null )
				m_packageList = new ArrayList<PackageInfo>();
			else
				m_packageList.clear();
			for( final PackageInfo pi : packageList )
			{
				if( pi.packageName.equals( cameraLockerPackage )
						|| ( !displaySystemApps && ( ( pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM ) == 0 ) )
						|| pi.requestedPermissions == null )
					continue;
				for( String permission : pi.requestedPermissions )
				{
					if( permission != null
							&& permission.equals( "android.permission.CAMERA" ) )
					{
						m_packageList.add( pi );
						break;
					}
				}
			}
		}
	}

	public ArrayList<PackageInfo> packages()
	{
		return m_packageList;
	}

	@Override
	public void onLoadComplete( Loader<Cursor> loader, Cursor cursor )
	{
		m_allowedPackageMap = new HashMap<String, Boolean>();
		while( cursor.moveToNext() )
		{
			m_allowedPackageMap.put(
					cursor.getString( cursor.getColumnIndex( PACKAGE_NAME ) ),
					cursor.getInt( cursor.getColumnIndex( ALLOW ) ) == 1 );
		}

		if( m_callback != null )
			m_callback.onLoad();
		m_callback = null;
	}
}
