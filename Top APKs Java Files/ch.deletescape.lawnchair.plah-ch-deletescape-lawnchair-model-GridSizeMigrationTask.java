package ch.deletescape.lawnchair.model;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import ch.deletescape.lawnchair.ItemInfo;
import ch.deletescape.lawnchair.LauncherAppState;
import ch.deletescape.lawnchair.LauncherAppWidgetProviderInfo;
import ch.deletescape.lawnchair.LauncherModel;
import ch.deletescape.lawnchair.LauncherSettings.Favorites;
import ch.deletescape.lawnchair.LauncherSettings.Settings;
import ch.deletescape.lawnchair.LauncherSettings.WorkspaceScreens;
import ch.deletescape.lawnchair.Utilities;
import ch.deletescape.lawnchair.compat.AppWidgetManagerCompat;
import ch.deletescape.lawnchair.compat.PackageInstallerCompat;
import ch.deletescape.lawnchair.config.ProviderConfig;
import ch.deletescape.lawnchair.preferences.IPreferenceProvider;
import ch.deletescape.lawnchair.util.GridOccupancy;
import ch.deletescape.lawnchair.util.LongArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GridSizeMigrationTask
{
  public static boolean ENABLED = Utilities.ATLEAST_NOUGAT;
  protected final ArrayList<DbEntry> mCarryOver = new ArrayList();
  private final Context mContext;
  private final int mDestHotseatSize;
  protected final ArrayList<Long> mEntryToRemove = new ArrayList();
  private final boolean mShouldRemoveX;
  private final boolean mShouldRemoveY;
  private final int mSrcHotseatSize;
  private final int mSrcX;
  private final int mSrcY;
  private final ContentValues mTempValues = new ContentValues();
  private final int mTrgX;
  private final int mTrgY;
  private final ArrayList<ContentProviderOperation> mUpdateOperations = new ArrayList();
  private final HashSet<String> mValidPackages;
  private final HashMap<String, Point> mWidgetMinSize = new HashMap();
  
  protected GridSizeMigrationTask(Context paramContext, HashSet<String> paramHashSet, int paramInt1, int paramInt2)
  {
    this.mContext = paramContext;
    this.mValidPackages = paramHashSet;
    this.mSrcHotseatSize = paramInt1;
    this.mDestHotseatSize = paramInt2;
    this.mTrgY = -1;
    this.mTrgX = -1;
    this.mSrcY = -1;
    this.mSrcX = -1;
    this.mShouldRemoveY = false;
    this.mShouldRemoveX = false;
  }
  
  protected GridSizeMigrationTask(Context paramContext, HashSet<String> paramHashSet, Point paramPoint1, Point paramPoint2)
  {
    this.mContext = paramContext;
    this.mValidPackages = paramHashSet;
    this.mSrcX = paramPoint1.x;
    this.mSrcY = paramPoint1.y;
    this.mTrgX = paramPoint2.x;
    this.mTrgY = paramPoint2.y;
    int i = this.mTrgX;
    int j = this.mSrcX;
    boolean bool2 = false;
    if (i < j) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    this.mShouldRemoveX = bool1;
    boolean bool1 = bool2;
    if (this.mTrgY < this.mSrcY) {
      bool1 = true;
    }
    this.mShouldRemoveY = bool1;
    this.mDestHotseatSize = -1;
    this.mSrcHotseatSize = -1;
  }
  
  private boolean applyOperations()
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch(ProviderConfig.AUTHORITY, this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty()) {
      this.mContext.getContentResolver().delete(LauncherSettings.Favorites.CONTENT_URI, Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
    }
    return (!this.mUpdateOperations.isEmpty()) || (!this.mEntryToRemove.isEmpty());
  }
  
  private static ArrayList<DbEntry> deepCopy(ArrayList<DbEntry> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.add(((DbEntry)paramArrayList.next()).copy());
    }
    return localArrayList;
  }
  
  private int getFolderItemsCount(long paramLong)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("container = ");
    ((StringBuilder)localObject).append(paramLong);
    localObject = ((StringBuilder)localObject).toString();
    localObject = queryWorkspace(new String[] { "_id", "intent" }, (String)localObject);
    int i = 0;
    while (((Cursor)localObject).moveToNext())
    {
      try
      {
        verifyIntent(((Cursor)localObject).getString(1));
        i += 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      this.mEntryToRemove.add(Long.valueOf(((Cursor)localObject).getLong(0)));
    }
    ((Cursor)localObject).close();
    return i;
  }
  
  private static String getPointString(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  protected static HashSet<String> getValidPackages(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    localHashSet.addAll(PackageInstallerCompat.getInstance(paramContext).updateAndGetActiveSessionCache().keySet());
    return localHashSet;
  }
  
  private ArrayList<DbEntry> loadHotseatEntries()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, new String[] { "_id", "itemType", "intent", "screen", "cellY" }, "container = -101", null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_id");
    int j = localCursor.getColumnIndexOrThrow("itemType");
    int k = localCursor.getColumnIndexOrThrow("intent");
    int m = localCursor.getColumnIndexOrThrow("screen");
    int n = localCursor.getColumnIndexOrThrow("cellY");
    ArrayList localArrayList = new ArrayList();
    DbEntry localDbEntry;
    for (;;)
    {
      if (!localCursor.moveToNext()) {
        break label370;
      }
      localDbEntry = new DbEntry();
      localDbEntry.id = localCursor.getLong(i);
      localDbEntry.itemType = localCursor.getInt(j);
      localDbEntry.screenId = localCursor.getLong(m);
      localDbEntry.cellY = localCursor.getInt(n);
      if (localDbEntry.screenId < this.mSrcHotseatSize) {
        break;
      }
      label205:
      this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
    }
    for (;;)
    {
      label370:
      try
      {
        int i1 = localDbEntry.itemType;
        if ((i1 != 4) && (i1 != 6)) {}
        switch (i1)
        {
        case 2: 
          throw new Exception("Invalid item type");
          i1 = getFolderItemsCount(localDbEntry.id);
          if (i1 == 0) {
            throw new Exception("Folder is empty");
          }
          localDbEntry.weight = (0.5F * i1);
          break;
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(k));
          if (localDbEntry.itemType != 0) {
            break label388;
          }
          f = 0.8F;
          localDbEntry.weight = f;
          localArrayList.add(localDbEntry);
        }
      }
      catch (Exception localException) {}
      localCursor.close();
      return localArrayList;
      break label205;
      continue;
      label388:
      float f = 1.0F;
    }
  }
  
  /* Error */
  public static boolean migrateGridIfNeeded(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 353	ch/deletescape/lawnchair/Utilities:getPrefs	(Landroid/content/Context;)Lch/deletescape/lawnchair/preferences/IPreferenceProvider;
    //   4: astore 5
    //   6: invokestatic 358	ch/deletescape/lawnchair/LauncherAppState:getInstance	()Lch/deletescape/lawnchair/LauncherAppState;
    //   9: invokevirtual 362	ch/deletescape/lawnchair/LauncherAppState:getInvariantDeviceProfile	()Lch/deletescape/lawnchair/InvariantDeviceProfile;
    //   12: astore 6
    //   14: aload 6
    //   16: getfield 367	ch/deletescape/lawnchair/InvariantDeviceProfile:numColumns	I
    //   19: aload 6
    //   21: getfield 370	ch/deletescape/lawnchair/InvariantDeviceProfile:numRows	I
    //   24: invokestatic 372	ch/deletescape/lawnchair/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   27: astore 7
    //   29: aload 7
    //   31: aload 5
    //   33: ldc_w 374
    //   36: invokeinterface 380 2 0
    //   41: invokevirtual 383	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +25 -> 69
    //   47: aload 6
    //   49: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   52: aload 5
    //   54: aload 6
    //   56: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   59: invokeinterface 389 2 0
    //   64: if_icmpeq +5 -> 69
    //   67: iconst_1
    //   68: ireturn
    //   69: invokestatic 395	java/lang/System:currentTimeMillis	()J
    //   72: lstore_2
    //   73: aload_0
    //   74: invokestatic 397	ch/deletescape/lawnchair/model/GridSizeMigrationTask:getValidPackages	(Landroid/content/Context;)Ljava/util/HashSet;
    //   77: astore 8
    //   79: aload 5
    //   81: aload 6
    //   83: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   86: invokeinterface 389 2 0
    //   91: istore_1
    //   92: iload_1
    //   93: aload 6
    //   95: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   98: if_icmpeq +339 -> 437
    //   101: new 2	ch/deletescape/lawnchair/model/GridSizeMigrationTask
    //   104: dup
    //   105: aload_0
    //   106: aload 8
    //   108: iload_1
    //   109: aload 6
    //   111: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   114: invokespecial 399	ch/deletescape/lawnchair/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Ljava/util/HashSet;II)V
    //   117: invokevirtual 402	ch/deletescape/lawnchair/model/GridSizeMigrationTask:migrateHotseat	()Z
    //   120: istore 4
    //   122: goto +3 -> 125
    //   125: new 99	android/graphics/Point
    //   128: dup
    //   129: aload 6
    //   131: getfield 367	ch/deletescape/lawnchair/InvariantDeviceProfile:numColumns	I
    //   134: aload 6
    //   136: getfield 370	ch/deletescape/lawnchair/InvariantDeviceProfile:numRows	I
    //   139: invokespecial 405	android/graphics/Point:<init>	(II)V
    //   142: astore 9
    //   144: aload 5
    //   146: aload 7
    //   148: invokeinterface 380 2 0
    //   153: invokestatic 409	ch/deletescape/lawnchair/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   156: astore 10
    //   158: new 9	ch/deletescape/lawnchair/model/GridSizeMigrationTask$MultiStepMigrationTask
    //   161: dup
    //   162: aload 8
    //   164: aload_0
    //   165: invokespecial 412	ch/deletescape/lawnchair/model/GridSizeMigrationTask$MultiStepMigrationTask:<init>	(Ljava/util/HashSet;Landroid/content/Context;)V
    //   168: aload 10
    //   170: aload 9
    //   172: invokevirtual 416	ch/deletescape/lawnchair/model/GridSizeMigrationTask$MultiStepMigrationTask:migrate	(Landroid/graphics/Point;Landroid/graphics/Point;)Z
    //   175: ifeq +6 -> 181
    //   178: iconst_1
    //   179: istore 4
    //   181: iload 4
    //   183: ifeq +48 -> 231
    //   186: aload_0
    //   187: invokevirtual 125	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   190: getstatic 143	ch/deletescape/lawnchair/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   193: aconst_null
    //   194: aconst_null
    //   195: aconst_null
    //   196: aconst_null
    //   197: invokevirtual 419	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   200: astore_0
    //   201: aload_0
    //   202: invokeinterface 215 1 0
    //   207: istore 4
    //   209: aload_0
    //   210: invokeinterface 236 1 0
    //   215: iload 4
    //   217: ifne +14 -> 231
    //   220: new 186	java/lang/Exception
    //   223: dup
    //   224: ldc_w 421
    //   227: invokespecial 336	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   230: athrow
    //   231: new 188	java/lang/StringBuilder
    //   234: dup
    //   235: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   238: astore_0
    //   239: aload_0
    //   240: ldc_w 423
    //   243: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: pop
    //   247: aload_0
    //   248: invokestatic 395	java/lang/System:currentTimeMillis	()J
    //   251: lload_2
    //   252: lsub
    //   253: invokevirtual 198	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   256: pop
    //   257: ldc_w 425
    //   260: aload_0
    //   261: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   264: invokestatic 431	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   267: pop
    //   268: aload 5
    //   270: aload 7
    //   272: iconst_0
    //   273: invokeinterface 434 3 0
    //   278: aload 5
    //   280: aload 6
    //   282: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   285: iconst_0
    //   286: invokeinterface 437 3 0
    //   291: iconst_1
    //   292: ireturn
    //   293: astore_0
    //   294: goto +77 -> 371
    //   297: astore_0
    //   298: ldc_w 425
    //   301: ldc_w 439
    //   304: aload_0
    //   305: invokestatic 443	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   308: pop
    //   309: new 188	java/lang/StringBuilder
    //   312: dup
    //   313: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   316: astore_0
    //   317: aload_0
    //   318: ldc_w 423
    //   321: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: aload_0
    //   326: invokestatic 395	java/lang/System:currentTimeMillis	()J
    //   329: lload_2
    //   330: lsub
    //   331: invokevirtual 198	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   334: pop
    //   335: ldc_w 425
    //   338: aload_0
    //   339: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   342: invokestatic 431	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   345: pop
    //   346: aload 5
    //   348: aload 7
    //   350: iconst_0
    //   351: invokeinterface 434 3 0
    //   356: aload 5
    //   358: aload 6
    //   360: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   363: iconst_0
    //   364: invokeinterface 437 3 0
    //   369: iconst_0
    //   370: ireturn
    //   371: new 188	java/lang/StringBuilder
    //   374: dup
    //   375: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   378: astore 8
    //   380: aload 8
    //   382: ldc_w 423
    //   385: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: pop
    //   389: aload 8
    //   391: invokestatic 395	java/lang/System:currentTimeMillis	()J
    //   394: lload_2
    //   395: lsub
    //   396: invokevirtual 198	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   399: pop
    //   400: ldc_w 425
    //   403: aload 8
    //   405: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   408: invokestatic 431	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   411: pop
    //   412: aload 5
    //   414: aload 7
    //   416: iconst_0
    //   417: invokeinterface 434 3 0
    //   422: aload 5
    //   424: aload 6
    //   426: getfield 386	ch/deletescape/lawnchair/InvariantDeviceProfile:numHotseatIcons	I
    //   429: iconst_0
    //   430: invokeinterface 437 3 0
    //   435: aload_0
    //   436: athrow
    //   437: iconst_0
    //   438: istore 4
    //   440: goto -315 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	443	0	paramContext	Context
    //   91	18	1	i	int
    //   72	323	2	l	long
    //   120	319	4	bool	boolean
    //   4	419	5	localIPreferenceProvider	IPreferenceProvider
    //   12	413	6	localInvariantDeviceProfile	ch.deletescape.lawnchair.InvariantDeviceProfile
    //   27	388	7	str	String
    //   77	327	8	localObject	Object
    //   142	29	9	localPoint1	Point
    //   156	13	10	localPoint2	Point
    // Exception table:
    //   from	to	target	type
    //   73	122	293	finally
    //   125	158	293	finally
    //   158	178	293	finally
    //   186	215	293	finally
    //   220	231	293	finally
    //   298	309	293	finally
    //   73	122	297	java/lang/Exception
    //   125	158	297	java/lang/Exception
    //   158	178	297	java/lang/Exception
    //   186	215	297	java/lang/Exception
    //   220	231	297	java/lang/Exception
  }
  
  private static Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  private ArrayList<DbEntry> tryRemove(int paramInt1, int paramInt2, int paramInt3, ArrayList<DbEntry> paramArrayList, float[] paramArrayOfFloat)
  {
    GridOccupancy localGridOccupancy = new GridOccupancy(this.mTrgX, this.mTrgY);
    localGridOccupancy.markCells(0, 0, this.mTrgX, paramInt3, true);
    if (!this.mShouldRemoveX) {
      paramInt1 = Integer.MAX_VALUE;
    }
    if (!this.mShouldRemoveY) {
      paramInt2 = Integer.MAX_VALUE;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      DbEntry localDbEntry = (DbEntry)paramArrayList.next();
      if (((localDbEntry.cellX <= paramInt1) && (localDbEntry.spanX + localDbEntry.cellX > paramInt1)) || ((localDbEntry.cellY <= paramInt2) && (localDbEntry.spanY + localDbEntry.cellY > paramInt2)))
      {
        localArrayList2.add(localDbEntry);
        if (localDbEntry.cellX >= paramInt1) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY >= paramInt2) {
          localDbEntry.cellY -= 1;
        }
      }
      else
      {
        if (localDbEntry.cellX > paramInt1) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY > paramInt2) {
          localDbEntry.cellY -= 1;
        }
        localArrayList1.add(localDbEntry);
        localGridOccupancy.markCells(localDbEntry, true);
      }
    }
    paramArrayList = new OptimalPlacementSolution(localGridOccupancy, localArrayList2, paramInt3);
    paramArrayList.find();
    localArrayList1.addAll(paramArrayList.finalPlacedItems);
    paramArrayOfFloat[0] = paramArrayList.lowestWeightLoss;
    paramArrayOfFloat[1] = paramArrayList.lowestMoveCost;
    return localArrayList1;
  }
  
  private void verifyIntent(String paramString)
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null) {}
    for (paramString = paramString.getComponent().getPackageName();; paramString = paramString.getPackage())
    {
      verifyPackage(paramString);
      return;
      if (paramString.getPackage() == null) {
        break;
      }
    }
  }
  
  private void verifyPackage(String paramString)
  {
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  protected ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("container = -100 AND screen = ");
    ((StringBuilder)localObject1).append(paramLong);
    localObject1 = ((StringBuilder)localObject1).toString();
    Cursor localCursor = queryWorkspace(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, (String)localObject1);
    int k = localCursor.getColumnIndexOrThrow("_id");
    int m = localCursor.getColumnIndexOrThrow("itemType");
    int n = localCursor.getColumnIndexOrThrow("cellX");
    int i1 = localCursor.getColumnIndexOrThrow("cellY");
    int i2 = localCursor.getColumnIndexOrThrow("spanX");
    int i3 = localCursor.getColumnIndexOrThrow("spanY");
    int i4 = localCursor.getColumnIndexOrThrow("intent");
    int i5 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i = localCursor.getColumnIndexOrThrow("appWidgetId");
    localObject1 = new ArrayList();
    for (;;)
    {
      DbEntry localDbEntry;
      if (localCursor.moveToNext())
      {
        localDbEntry = new DbEntry();
        localDbEntry.id = localCursor.getLong(k);
        localDbEntry.itemType = localCursor.getInt(m);
        localDbEntry.cellX = localCursor.getInt(n);
        localDbEntry.cellY = localCursor.getInt(i1);
        localDbEntry.spanX = localCursor.getInt(i2);
        localDbEntry.spanY = localCursor.getInt(i3);
        localDbEntry.screenId = paramLong;
      }
      try
      {
        j = localDbEntry.itemType;
        if (j != 4) {
          if (j == 6) {}
        }
        switch (j)
        {
        case 2: 
          throw new Exception("Invalid item type");
          j = getFolderItemsCount(localDbEntry.id);
          if (j == 0) {
            throw new Exception("Folder is empty");
          }
          localDbEntry.weight = (0.5F * j);
          break;
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(i4));
          if (localDbEntry.itemType == 0)
          {
            f = 0.8F;
            localDbEntry.weight = f;
            break;
            localObject2 = localCursor.getString(i5);
            verifyPackage(ComponentName.unflattenFromString((String)localObject2).getPackageName());
            f = localDbEntry.spanX;
            localDbEntry.weight = Math.max(2.0F, localDbEntry.spanY * (0.6F * f));
          }
          break;
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            Object localObject2;
            int j = localCursor.getInt(i);
            LauncherAppWidgetProviderInfo localLauncherAppWidgetProviderInfo = AppWidgetManagerCompat.getInstance(this.mContext).getLauncherAppWidgetInfo(j);
            if (localLauncherAppWidgetProviderInfo == null) {
              localObject2 = (Point)this.mWidgetMinSize.get(localObject2);
            } else {
              localObject2 = localLauncherAppWidgetProviderInfo.getMinSpans();
            }
            if (localObject2 != null)
            {
              if (((Point)localObject2).x > 0) {
                j = ((Point)localObject2).x;
              } else {
                j = localDbEntry.spanX;
              }
              localDbEntry.minSpanX = j;
              if (((Point)localObject2).y > 0) {
                j = ((Point)localObject2).y;
              } else {
                j = localDbEntry.spanY;
              }
              localDbEntry.minSpanY = j;
            }
            else
            {
              localDbEntry.minSpanY = 2;
              localDbEntry.minSpanX = 2;
            }
            if (localDbEntry.minSpanX <= this.mTrgX)
            {
              j = localDbEntry.minSpanY;
              int i6 = this.mTrgY;
              if (j <= i6) {
                ((ArrayList)localObject1).add(localDbEntry);
              }
            }
          }
          catch (Exception localException2)
          {
            try
            {
              throw new Exception("Widget can't be resized down to fit the grid");
              this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
              break;
              localCursor.close();
              return localObject1;
              localException1 = localException1;
              continue;
              localException2 = localException2;
              continue;
            }
            catch (Exception localException3)
            {
              continue;
            }
          }
          continue;
          continue;
          float f = 1.0F;
        }
      }
    }
  }
  
  protected boolean migrateHotseat()
  {
    ArrayList localArrayList = loadHotseatEntries();
    DbEntry localDbEntry;
    while (localArrayList.size() > this.mDestHotseatSize)
    {
      localObject = (DbEntry)localArrayList.get(localArrayList.size() / 2);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        localDbEntry = (DbEntry)localIterator.next();
        if (localDbEntry.weight < ((DbEntry)localObject).weight) {
          localObject = localDbEntry;
        }
      }
      this.mEntryToRemove.add(Long.valueOf(((DbEntry)localObject).id));
      localArrayList.remove(localObject);
    }
    Object localObject = localArrayList.iterator();
    int i = 0;
    while (((Iterator)localObject).hasNext())
    {
      localDbEntry = (DbEntry)((Iterator)localObject).next();
      long l1 = localDbEntry.screenId;
      long l2 = i;
      if (l1 != l2)
      {
        localDbEntry.screenId = l2;
        localDbEntry.cellX = i;
        localDbEntry.cellY = 0;
        update(localDbEntry);
      }
      i += 1;
    }
    return applyOperations();
  }
  
  protected void migrateScreen(long paramLong)
  {
    int i;
    if ((Utilities.getPrefs(LauncherAppState.getInstance().getContext()).getShowPixelBar()) && (paramLong == 0L)) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject4 = loadWorkspaceEntries(paramLong);
    Object localObject5 = new float[2];
    Object localObject1 = null;
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    int j = 0;
    Object localObject2;
    float f3;
    for (;;)
    {
      localObject2 = localObject1;
      f3 = f1;
      if (j >= this.mSrcX) {
        break;
      }
      int k = this.mSrcY;
      f3 = f1;
      f1 = f2;
      k -= 1;
      f2 = f3;
      float f4;
      for (;;)
      {
        f4 = f2;
        f3 = f1;
        localObject2 = localObject1;
        if (k < i) {
          break;
        }
        localObject3 = tryRemove(j, k, i, deepCopy((ArrayList)localObject4), (float[])localObject5);
        if (localObject5[0] >= f2)
        {
          f4 = f2;
          f3 = f1;
          localObject2 = localObject1;
          if (localObject5[0] == f2)
          {
            f4 = f2;
            f3 = f1;
            localObject2 = localObject1;
            if (localObject5[1] >= f1) {}
          }
        }
        else
        {
          f4 = localObject5[0];
          f3 = localObject5[1];
          boolean bool = this.mShouldRemoveX;
          bool = this.mShouldRemoveY;
          localObject2 = localObject3;
        }
        if (!this.mShouldRemoveY) {
          break;
        }
        k -= 1;
        f2 = f4;
        f1 = f3;
        localObject1 = localObject2;
      }
      f1 = f4;
      f2 = f3;
      localObject1 = localObject2;
      if (!this.mShouldRemoveX)
      {
        localObject2 = localObject1;
        f3 = f1;
        break;
      }
      j += 1;
    }
    localObject1 = new LongArrayMap();
    Object localObject3 = deepCopy((ArrayList)localObject4).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      ((LongArrayMap)localObject1).put(((DbEntry)localObject4).id, localObject4);
    }
    localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      localObject5 = (DbEntry)((LongArrayMap)localObject1).get(((DbEntry)localObject4).id);
      ((LongArrayMap)localObject1).remove(((DbEntry)localObject4).id);
      if (!((DbEntry)localObject4).columnsSame((DbEntry)localObject5)) {
        update((DbEntry)localObject4);
      }
    }
    localObject1 = ((LongArrayMap)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (DbEntry)((Iterator)localObject1).next();
      this.mCarryOver.add(localObject3);
    }
    if ((!this.mCarryOver.isEmpty()) && (f3 == 0.0F))
    {
      localObject1 = new GridOccupancy(this.mTrgX, this.mTrgY);
      ((GridOccupancy)localObject1).markCells(0, 0, this.mTrgX, i, true);
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((GridOccupancy)localObject1).markCells((DbEntry)((Iterator)localObject2).next(), true);
      }
      localObject1 = new OptimalPlacementSolution((GridOccupancy)localObject1, deepCopy(this.mCarryOver), i, true);
      ((OptimalPlacementSolution)localObject1).find();
      if (((OptimalPlacementSolution)localObject1).lowestWeightLoss == 0.0F)
      {
        localObject1 = ((OptimalPlacementSolution)localObject1).finalPlacedItems.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DbEntry)((Iterator)localObject1).next();
          ((DbEntry)localObject2).screenId = paramLong;
          update((DbEntry)localObject2);
        }
        this.mCarryOver.clear();
      }
    }
  }
  
  protected boolean migrateWorkspace()
  {
    ArrayList localArrayList = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    while (((Iterator)localObject1).hasNext()) {
      migrateScreen(((Long)((Iterator)localObject1).next()).longValue());
    }
    if (!this.mCarryOver.isEmpty())
    {
      localObject1 = new LongArrayMap();
      Object localObject2 = this.mCarryOver.iterator();
      DbEntry localDbEntry;
      while (((Iterator)localObject2).hasNext())
      {
        localDbEntry = (DbEntry)((Iterator)localObject2).next();
        ((LongArrayMap)localObject1).put(localDbEntry.id, localDbEntry);
      }
      do
      {
        localObject2 = new OptimalPlacementSolution(new GridOccupancy(this.mTrgX, this.mTrgY), deepCopy(this.mCarryOver), 0, true);
        ((OptimalPlacementSolution)localObject2).find();
        if (((OptimalPlacementSolution)localObject2).finalPlacedItems.size() <= 0) {
          break;
        }
        long l = LauncherSettings.Settings.call(this.mContext.getContentResolver(), "generate_new_screen_id").getLong("value");
        localArrayList.add(Long.valueOf(l));
        localObject2 = ((OptimalPlacementSolution)localObject2).finalPlacedItems.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localDbEntry = (DbEntry)((Iterator)localObject2).next();
          if (!this.mCarryOver.remove(((LongArrayMap)localObject1).get(localDbEntry.id))) {
            throw new Exception("Unable to find matching items");
          }
          localDbEntry.screenId = l;
          update(localDbEntry);
        }
      } while (!this.mCarryOver.isEmpty());
      localObject1 = LauncherSettings.WorkspaceScreens.CONTENT_URI;
      this.mUpdateOperations.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i)).longValue()));
        ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i));
        this.mUpdateOperations.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
        i += 1;
      }
      throw new Exception("None of the items can be placed on an empty screen");
    }
    return applyOperations();
  }
  
  protected Cursor queryWorkspace(String[] paramArrayOfString, String paramString)
  {
    return this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, paramArrayOfString, paramString, null, null, null);
  }
  
  protected void update(DbEntry paramDbEntry)
  {
    this.mTempValues.clear();
    paramDbEntry.addToContentValues(this.mTempValues);
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(LauncherSettings.Favorites.getContentUri(paramDbEntry.id)).withValues(this.mTempValues).build());
  }
  
  protected static class DbEntry
    extends ItemInfo
    implements Comparable<DbEntry>
  {
    public float weight;
    
    public DbEntry() {}
    
    public void addToContentValues(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.screenId));
      paramContentValues.put("cellX", Integer.valueOf(this.cellX));
      paramContentValues.put("cellY", Integer.valueOf(this.cellY));
      paramContentValues.put("spanX", Integer.valueOf(this.spanX));
      paramContentValues.put("spanY", Integer.valueOf(this.spanY));
    }
    
    public boolean columnsSame(DbEntry paramDbEntry)
    {
      return (paramDbEntry.cellX == this.cellX) && (paramDbEntry.cellY == this.cellY) && (paramDbEntry.spanX == this.spanX) && (paramDbEntry.spanY == this.spanY) && (paramDbEntry.screenId == this.screenId);
    }
    
    public int compareTo(DbEntry paramDbEntry)
    {
      if (this.itemType == 4)
      {
        if (paramDbEntry.itemType == 4) {
          return paramDbEntry.spanY * paramDbEntry.spanX - this.spanX * this.spanY;
        }
        return -1;
      }
      if (paramDbEntry.itemType == 4) {
        return 1;
      }
      return Float.compare(paramDbEntry.weight, this.weight);
    }
    
    public DbEntry copy()
    {
      DbEntry localDbEntry = new DbEntry();
      localDbEntry.copyFrom(this);
      localDbEntry.weight = this.weight;
      localDbEntry.minSpanX = this.minSpanX;
      localDbEntry.minSpanY = this.minSpanY;
      return localDbEntry;
    }
  }
  
  protected static class MultiStepMigrationTask
  {
    private final Context mContext;
    private final HashSet<String> mValidPackages;
    
    public MultiStepMigrationTask(HashSet<String> paramHashSet, Context paramContext)
    {
      this.mValidPackages = paramHashSet;
      this.mContext = paramContext;
    }
    
    public boolean migrate(Point paramPoint1, Point paramPoint2)
    {
      boolean bool1 = paramPoint2.equals(paramPoint1);
      boolean bool2 = false;
      boolean bool3 = false;
      if (!bool1)
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        bool1 = bool3;
        if (paramPoint1.y < paramPoint2.y)
        {
          paramPoint1.y = paramPoint2.y;
          bool1 = bool3;
        }
        for (;;)
        {
          bool2 = bool1;
          if (paramPoint2.equals(paramPoint1)) {
            break;
          }
          Point localPoint = new Point(paramPoint1);
          if (paramPoint2.x < localPoint.x) {
            localPoint.x -= 1;
          }
          if (paramPoint2.y < localPoint.y) {
            localPoint.y -= 1;
          }
          if (runStepTask(paramPoint1, localPoint)) {
            bool1 = true;
          }
          paramPoint1.set(localPoint.x, localPoint.y);
        }
      }
      return bool2;
    }
    
    protected boolean runStepTask(Point paramPoint1, Point paramPoint2)
    {
      return new GridSizeMigrationTask(this.mContext, this.mValidPackages, paramPoint1, paramPoint2).migrateWorkspace();
    }
  }
  
  private class OptimalPlacementSolution
  {
    ArrayList<GridSizeMigrationTask.DbEntry> finalPlacedItems;
    private final boolean ignoreMove;
    private final ArrayList<GridSizeMigrationTask.DbEntry> itemsToPlace;
    float lowestMoveCost = Float.MAX_VALUE;
    float lowestWeightLoss = Float.MAX_VALUE;
    private final GridOccupancy occupied;
    private final int startY;
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList, int paramInt)
    {
      this(paramArrayList, paramInt, i, false);
    }
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList, int paramInt, boolean paramBoolean)
    {
      this.occupied = paramArrayList;
      this.itemsToPlace = paramInt;
      boolean bool;
      this.ignoreMove = bool;
      this.startY = paramBoolean;
      Collections.sort(this.itemsToPlace);
    }
    
    public void find()
    {
      find(0, 0.0F, 0.0F, new ArrayList());
    }
    
    public void find(int paramInt, float paramFloat1, float paramFloat2, ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
    {
      if (paramFloat1 < this.lowestWeightLoss)
      {
        if ((paramFloat1 == this.lowestWeightLoss) && (paramFloat2 >= this.lowestMoveCost)) {
          return;
        }
        if (paramInt >= this.itemsToPlace.size())
        {
          this.lowestWeightLoss = paramFloat1;
          this.lowestMoveCost = paramFloat2;
          this.finalPlacedItems = GridSizeMigrationTask.deepCopy(paramArrayList);
          return;
        }
        GridSizeMigrationTask.DbEntry localDbEntry = (GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt);
        int i2 = localDbEntry.cellX;
        int i3 = localDbEntry.cellY;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localDbEntry);
        int i;
        int k;
        int j;
        int m;
        float f1;
        float f2;
        if ((localDbEntry.spanX <= 1) && (localDbEntry.spanY <= 1))
        {
          i = this.startY;
          k = Integer.MAX_VALUE;
          int n = Integer.MAX_VALUE;
          int i1 = n;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              if (this.occupied.cells[j][i] == 0)
              {
                if (this.ignoreMove) {
                  m = 0;
                } else {
                  m = (localDbEntry.cellX - j) * (localDbEntry.cellX - j) + (localDbEntry.cellY - i) * (localDbEntry.cellY - i);
                }
                if (m < i1)
                {
                  n = i;
                  k = j;
                  i1 = m;
                }
              }
              j += 1;
            }
            i += 1;
          }
          if ((k < GridSizeMigrationTask.this.mTrgX) && (n < GridSizeMigrationTask.this.mTrgY))
          {
            if (k != i2)
            {
              localDbEntry.cellX = k;
              f1 = paramFloat2 + 1.0F;
            }
            else
            {
              f1 = paramFloat2;
            }
            f2 = f1;
            if (n != i3)
            {
              localDbEntry.cellY = n;
              f2 = f1 + 1.0F;
            }
            f1 = f2;
            if (this.ignoreMove) {
              f1 = paramFloat2;
            }
            this.occupied.markCells(localDbEntry, true);
            paramInt += 1;
            find(paramInt, paramFloat1, f1, localArrayList);
            this.occupied.markCells(localDbEntry, false);
            localDbEntry.cellX = i2;
            localDbEntry.cellY = i3;
            if ((paramInt >= this.itemsToPlace.size()) || (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight < localDbEntry.weight) || (this.ignoreMove)) {
              return;
            }
          }
          else
          {
            paramInt += 1;
            while (paramInt < this.itemsToPlace.size())
            {
              paramFloat1 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
              paramInt += 1;
            }
            paramInt = this.itemsToPlace.size();
          }
        }
        else
        {
          j = localDbEntry.spanX;
          m = localDbEntry.spanY;
          i = this.startY;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            k = 0;
            while (k < GridSizeMigrationTask.this.mTrgX)
            {
              if (k != i2)
              {
                localDbEntry.cellX = k;
                f1 = paramFloat2 + 1.0F;
              }
              else
              {
                f1 = paramFloat2;
              }
              f2 = f1;
              if (i != i3)
              {
                localDbEntry.cellY = i;
                f2 = f1 + 1.0F;
              }
              f1 = f2;
              if (this.ignoreMove) {
                f1 = paramFloat2;
              }
              if (this.occupied.isRegionVacant(k, i, j, m))
              {
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1, localArrayList);
                this.occupied.markCells(localDbEntry, false);
              }
              if ((j > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(k, i, j - 1, m)))
              {
                localDbEntry.spanX -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
              }
              if ((m > localDbEntry.minSpanY) && (this.occupied.isRegionVacant(k, i, j, m - 1)))
              {
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanY += 1;
              }
              if ((m > localDbEntry.minSpanY) && (j > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(k, i, j - 1, m - 1)))
              {
                localDbEntry.spanX -= 1;
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
                localDbEntry.spanY += 1;
              }
              localDbEntry.cellX = i2;
              localDbEntry.cellY = i3;
              k += 1;
            }
            i += 1;
          }
          paramInt += 1;
        }
        find(paramInt, paramFloat1 + localDbEntry.weight, paramFloat2, paramArrayList);
      }
    }
  }
}
