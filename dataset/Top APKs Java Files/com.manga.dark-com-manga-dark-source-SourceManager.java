package com.manga.dark.source;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import com.manga.dark.source.online.HttpSource;
import com.manga.dark.source.online.YamlHttpSource;
import com.manga.dark.source.online.english.Kissmanga;
import com.manga.dark.source.online.english.Mangaeden;
import com.manga.dark.source.online.english.Mangafree;
import com.manga.dark.source.online.english.Mangahasu;
import com.manga.dark.source.online.english.Manganelo;
import com.manga.dark.source.online.english.Mangasee;
import com.manga.dark.source.online.english.Mangastream;
import com.manga.dark.source.online.english.Readmangatoday;
import com.manga.dark.util.ContextExtensionsKt;
import dalvik.system.PathClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.yaml.snakeyaml.Yaml;
import timber.log.Timber;

@Metadata(bv={1, 0, 2}, d1={"\000N\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020%\n\002\020\t\n\002\030\002\n\000\n\002\020 \n\002\b\002\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\003\b\026\030\000 \0342\0020\001:\002\034\035B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\016\020\t\032\b\022\004\022\0020\b0\nH\002J\016\020\013\032\b\022\004\022\0020\b0\nH\002J\b\020\f\032\0020\rH\002J\016\020\016\032\b\022\004\022\0020\b0\nH\002J\022\020\017\032\004\030\0010\b2\006\020\020\032\0020\007H\026J\f\020\021\032\b\022\004\022\0020\0220\nJ\f\020\023\032\b\022\004\022\0020\0240\nJ\026\020\025\032\b\022\004\022\0020\b0\n2\006\020\026\032\0020\027H\002J\032\020\030\032\0020\r2\006\020\031\032\0020\b2\b\b\002\020\032\032\0020\033H\002R\016\020\002\032\0020\003X\004¢\006\002\n\000R\032\020\005\032\016\022\004\022\0020\007\022\004\022\0020\b0\006X\004¢\006\002\n\000¨\006\036"}, d2={"Lcom/manga/dark/source/SourceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sourcesMap", "", "", "Lcom/manga/dark/source/Source;", "createExtensionSources", "", "createInternalSources", "createSources", "", "createYamlSources", "get", "sourceKey", "getCatalogueSources", "Lcom/manga/dark/source/CatalogueSource;", "getOnlineSources", "Lcom/manga/dark/source/online/HttpSource;", "loadExtension", "ext", "Lcom/manga/dark/source/SourceManager$Extension;", "registerSource", "source", "overwrite", "", "Companion", "Extension", "app_release"}, k=1, mv={1, 1, 9})
public class SourceManager
{
  @Deprecated
  public static final Companion Companion = new Companion(null);
  private final Context context;
  private final Map<Long, Source> sourcesMap;
  
  public SourceManager(Context paramContext)
  {
    this.context = paramContext;
    this.sourcesMap = ((Map)new LinkedHashMap());
    createSources();
  }
  
  private final List<Source> createExtensionSources()
  {
    PackageManager localPackageManager = this.context.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(16448);
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "installedPkgs");
    localObject1 = (Iterable)localObject1;
    Object localObject2 = (Collection)new ArrayList();
    Object localObject3 = ((Iterable)localObject1).iterator();
    label83:
    label85:
    label134:
    label141:
    label144:
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = ((Iterator)localObject3).next();
      localObject1 = (Object[])((PackageInfo)localObject4).reqFeatures;
      if (localObject1 != null)
      {
        i = 0;
        if (i >= localObject1.length) {
          break label141;
        }
        if (!Intrinsics.areEqual(((FeatureInfo)localObject1[i]).name, "tachiyomi.extension")) {
          break label134;
        }
      }
      for (int i = 1;; i = 0)
      {
        if (i == 0) {
          break label144;
        }
        ((Collection)localObject2).add(localObject4);
        break;
        localObject1 = new FeatureInfo[0];
        break label83;
        i += 1;
        break label85;
      }
    }
    localObject1 = (List)localObject2;
    localObject3 = (List)new ArrayList();
    Object localObject4 = ((List)localObject1).iterator();
    while (((Iterator)localObject4).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject4).next();
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128);
      if (localApplicationInfo != null)
      {
        String str1 = StringsKt.substringAfter$default(localPackageManager.getApplicationLabel(localApplicationInfo).toString(), "Tachiyomi: ", null, 2, null);
        String str2 = localPackageInfo.versionName;
        localObject1 = localApplicationInfo.metaData.getString("tachiyomi.extension.class");
        Intrinsics.checkExpressionValueIsNotNull(localObject1, "appInfo.metaData.getString(METADATA_SOURCE_CLASS)");
        localObject1 = (Iterable)StringsKt.split$default((CharSequence)localObject1, new String[] { ";" }, false, 0, 6, null);
        Collection localCollection = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject1, 10));
        Iterator localIterator = ((Iterable)localObject1).iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (String)localIterator.next();
          if (localObject1 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
          }
          localObject2 = StringsKt.trim((CharSequence)localObject1).toString();
          localObject1 = localObject2;
          if (StringsKt.startsWith$default((String)localObject2, ".", false, 2, null)) {
            localObject1 = localPackageInfo.packageName + (String)localObject2;
          }
          localCollection.add(localObject1);
        }
        localObject1 = (List)localCollection;
        Intrinsics.checkExpressionValueIsNotNull(str2, "version");
        localObject1 = new Extension(str1, localApplicationInfo, str2, (List)localObject1);
        try
        {
          CollectionsKt.addAll((Collection)localObject3, (Iterable)loadExtension((Extension)localObject1));
        }
        catch (Exception localException)
        {
          Timber.e("Extension load error: " + str1 + '.', new Object[] { localException });
        }
        catch (LinkageError localLinkageError)
        {
          Timber.e("Extension load error: " + str1 + '.', new Object[] { localLinkageError });
        }
      }
    }
    return localObject3;
  }
  
  private final List<Source> createInternalSources()
  {
    return CollectionsKt.listOf(new CatalogueSource[] { (CatalogueSource)new LocalSource(this.context), (CatalogueSource)new Readmangatoday(), (CatalogueSource)new Mangasee(), (CatalogueSource)new Kissmanga(), (CatalogueSource)new Mangaeden(), (CatalogueSource)new Mangafree(), (CatalogueSource)new Manganelo(), (CatalogueSource)new Mangahasu(), (CatalogueSource)new Mangastream() });
  }
  
  private final void createSources()
  {
    Iterator localIterator = ((Iterable)createExtensionSources()).iterator();
    while (localIterator.hasNext()) {
      registerSource$default(this, (Source)localIterator.next(), false, 2, null);
    }
    localIterator = ((Iterable)createYamlSources()).iterator();
    while (localIterator.hasNext()) {
      registerSource$default(this, (Source)localIterator.next(), false, 2, null);
    }
    localIterator = ((Iterable)createInternalSources()).iterator();
    while (localIterator.hasNext()) {
      registerSource$default(this, (Source)localIterator.next(), false, 2, null);
    }
  }
  
  private final List<Source> createYamlSources()
  {
    List localList = (List)new ArrayList();
    Object localObject1 = new StringBuilder();
    Object localObject3 = Environment.getExternalStorageDirectory();
    Intrinsics.checkExpressionValueIsNotNull(localObject3, "Environment.getExternalStorageDirectory()");
    localObject1 = new File(((File)localObject3).getAbsolutePath() + File.separator + this.context.getString(2131689574), "parsers");
    Object localObject6;
    if ((((File)localObject1).exists()) && (ContextExtensionsKt.hasPermission(this.context, "android.permission.READ_EXTERNAL_STORAGE")))
    {
      Yaml localYaml = new Yaml();
      localObject1 = ((File)localObject1).listFiles();
      Intrinsics.checkExpressionValueIsNotNull(localObject1, "parsersDir.listFiles()");
      localObject1 = (Object[])localObject1;
      localObject3 = (Collection)new ArrayList();
      int i = 0;
      while (i < localObject1.length)
      {
        localObject5 = localObject1[i];
        localObject6 = (File)localObject5;
        Intrinsics.checkExpressionValueIsNotNull(localObject6, "it");
        if (Intrinsics.areEqual(FilesKt.getExtension((File)localObject6), "yml")) {
          ((Collection)localObject3).add(localObject5);
        }
        i += 1;
      }
      Object localObject5 = ((List)localObject3).iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject1 = (File)((Iterator)localObject5).next();
        try
        {
          Intrinsics.checkExpressionValueIsNotNull(localObject1, "file");
          localObject6 = (Closeable)new FileInputStream((File)localObject1);
          localObject3 = (Throwable)null;
          localObject1 = localObject3;
        }
        catch (Exception localException)
        {
          try
          {
            Map localMap = (Map)localYaml.loadAs((InputStream)localObject6, Map.class);
            CloseableKt.closeFinally((Closeable)localObject6, (Throwable)localObject3);
            Intrinsics.checkExpressionValueIsNotNull(localMap, "map");
            localList.add(new YamlHttpSource(localMap));
            continue;
          }
          catch (Throwable localThrowable)
          {
            localObject2 = localThrowable;
            throw localThrowable;
          }
          finally
          {
            Object localObject2;
            CloseableKt.closeFinally((Closeable)localObject6, localObject2);
          }
          localException = localException;
          Timber.e("Error loading source from file. Bad format?", new Object[] { localException });
        }
      }
    }
    return localList;
  }
  
  private final List<Source> loadExtension(Extension paramExtension)
  {
    int i = Integer.parseInt(StringsKt.substringBefore$default(paramExtension.getVersion(), '.', null, 2, null));
    if ((i < 1) || (i > 1)) {
      throw ((Throwable)new Exception("Lib version is " + i + ", while only versions " + "1 to 1 are allowed"));
    }
    PathClassLoader localPathClassLoader = new PathClassLoader(paramExtension.getAppInfo().sourceDir, null, this.context.getClassLoader());
    paramExtension = (Iterable)paramExtension.getSourceClasses();
    Collection localCollection = (Collection)new ArrayList();
    Iterator localIterator = paramExtension.iterator();
    if (localIterator.hasNext())
    {
      paramExtension = Class.forName((String)localIterator.next(), false, (ClassLoader)localPathClassLoader).newInstance();
      if ((paramExtension instanceof Source)) {}
      for (paramExtension = CollectionsKt.listOf(paramExtension);; paramExtension = ((SourceFactory)paramExtension).createSources())
      {
        CollectionsKt.addAll(localCollection, (Iterable)paramExtension);
        break;
        if (!(paramExtension instanceof SourceFactory)) {
          break label197;
        }
      }
      label197:
      throw ((Throwable)new Exception("Unknown source class type!"));
    }
    return (List)localCollection;
  }
  
  private final void registerSource(Source paramSource, boolean paramBoolean)
  {
    if ((paramBoolean) || (!this.sourcesMap.containsKey(Long.valueOf(paramSource.getId())))) {
      this.sourcesMap.put(Long.valueOf(paramSource.getId()), paramSource);
    }
  }
  
  public Source get(long paramLong)
  {
    return (Source)this.sourcesMap.get(Long.valueOf(paramLong));
  }
  
  public final List<CatalogueSource> getCatalogueSources()
  {
    Object localObject1 = (Iterable)this.sourcesMap.values();
    Collection localCollection = (Collection)new ArrayList();
    localObject1 = ((Iterable)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if ((localObject2 instanceof CatalogueSource)) {
        localCollection.add(localObject2);
      }
    }
    return (List)localCollection;
  }
  
  public final List<HttpSource> getOnlineSources()
  {
    Object localObject1 = (Iterable)this.sourcesMap.values();
    Collection localCollection = (Collection)new ArrayList();
    localObject1 = ((Iterable)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if ((localObject2 instanceof HttpSource)) {
        localCollection.add(localObject2);
      }
    }
    return (List)localCollection;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000R\016\020\007\032\0020\006XT¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000¨\006\t"}, d2={"Lcom/manga/dark/source/SourceManager$Companion;", "", "()V", "EXTENSION_FEATURE", "", "LIB_VERSION_MAX", "", "LIB_VERSION_MIN", "METADATA_SOURCE_CLASS", "app_release"}, k=1, mv={1, 1, 9})
  private static final class Companion
  {
    private Companion() {}
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\b\t\030\0002\0020\001B+\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\003\022\f\020\007\032\b\022\004\022\0020\0030\b¢\006\002\020\tR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\n\020\013R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\rR\027\020\007\032\b\022\004\022\0020\0030\b¢\006\b\n\000\032\004\b\016\020\017R\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\020\020\r¨\006\021"}, d2={"Lcom/manga/dark/source/SourceManager$Extension;", "", "name", "", "appInfo", "Landroid/content/pm/ApplicationInfo;", "version", "sourceClasses", "", "(Ljava/lang/String;Landroid/content/pm/ApplicationInfo;Ljava/lang/String;Ljava/util/List;)V", "getAppInfo", "()Landroid/content/pm/ApplicationInfo;", "getName", "()Ljava/lang/String;", "getSourceClasses", "()Ljava/util/List;", "getVersion", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Extension
  {
    private final ApplicationInfo appInfo;
    private final String name;
    private final List<String> sourceClasses;
    private final String version;
    
    public Extension(String paramString1, ApplicationInfo paramApplicationInfo, String paramString2, List<String> paramList)
    {
      this.name = paramString1;
      this.appInfo = paramApplicationInfo;
      this.version = paramString2;
      this.sourceClasses = paramList;
    }
    
    public final ApplicationInfo getAppInfo()
    {
      return this.appInfo;
    }
    
    public final List<String> getSourceClasses()
    {
      return this.sourceClasses;
    }
    
    public final String getVersion()
    {
      return this.version;
    }
  }
}
