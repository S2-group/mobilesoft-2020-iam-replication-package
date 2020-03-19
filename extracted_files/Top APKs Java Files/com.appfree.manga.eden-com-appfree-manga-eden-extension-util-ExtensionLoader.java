package com.appfree.manga.eden.extension.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import com.appfree.manga.eden.data.preference.PreferencesHelper;
import com.appfree.manga.eden.data.preference.PreferencesHelperKt;
import com.appfree.manga.eden.extension.model.Extension.Installed;
import com.appfree.manga.eden.extension.model.Extension.Untrusted;
import com.appfree.manga.eden.extension.model.LoadResult;
import com.appfree.manga.eden.extension.model.LoadResult.Error;
import com.appfree.manga.eden.extension.model.LoadResult.Success;
import com.appfree.manga.eden.extension.model.LoadResult.Untrusted;
import com.appfree.manga.eden.source.CatalogueSource;
import com.appfree.manga.eden.source.Source;
import com.appfree.manga.eden.source.SourceFactory;
import com.appfree.manga.eden.util.Hash;
import com.f2prateek.rx.preferences.Preference;
import dalvik.system.PathClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.experimental.BuildersKt;
import kotlinx.coroutines.experimental.CoroutineScope;
import kotlinx.coroutines.experimental.Deferred;
import kotlinx.coroutines.experimental.DeferredKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
import uy.kohesive.injekt.InjektKt;
import uy.kohesive.injekt.api.FullTypeReference;
import uy.kohesive.injekt.api.InjektFactory;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\004\n\002\020\"\n\002\b\007\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020 \n\000\bÁ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\022\020\021\032\004\030\0010\0042\006\020\022\032\0020\023H\002J\020\020\024\032\0020\0252\006\020\022\032\0020\023H\002J \020\026\032\0020\0272\006\020\030\032\0020\0312\006\020\032\032\0020\0042\006\020\022\032\0020\023H\002J\026\020\033\032\0020\0272\006\020\030\032\0020\0312\006\020\032\032\0020\004J\024\020\034\032\b\022\004\022\0020\0270\0352\006\020\030\032\0020\031R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000R\016\020\007\032\0020\006XT¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000R\016\020\t\032\0020\006XT¢\006\002\n\000R(\020\n\032\020\022\f\022\n \f*\004\030\0010\0040\0040\013X\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020¨\006\036"}, d2={"Lcom/appfree/manga/eden/extension/util/ExtensionLoader;", "", "()V", "EXTENSION_FEATURE", "", "LIB_VERSION_MAX", "", "LIB_VERSION_MIN", "METADATA_SOURCE_CLASS", "PACKAGE_FLAGS", "trustedSignatures", "", "kotlin.jvm.PlatformType", "getTrustedSignatures", "()Ljava/util/Set;", "setTrustedSignatures", "(Ljava/util/Set;)V", "getSignatureHash", "pkgInfo", "Landroid/content/pm/PackageInfo;", "isPackageAnExtension", "", "loadExtension", "Lcom/appfree/manga/eden/extension/model/LoadResult;", "context", "Landroid/content/Context;", "pkgName", "loadExtensionFromPkgName", "loadExtensions", "", "app_devRelease"}, k=1, mv={1, 1, 11})
@SuppressLint({"PackageManagerGetSignatures"})
public final class ExtensionLoader
{
  private static final String EXTENSION_FEATURE = "tachiyomi.extension";
  public static final ExtensionLoader INSTANCE = new ExtensionLoader();
  private static final int LIB_VERSION_MAX = 1;
  private static final int LIB_VERSION_MIN = 1;
  private static final String METADATA_SOURCE_CLASS = "tachiyomi.extension.class";
  private static final int PACKAGE_FLAGS = 16448;
  @NotNull
  private static Set<String> trustedSignatures;
  
  static
  {
    Set localSet = (Set)new LinkedHashSet();
    Object localObject = ((PreferencesHelper)((InjektFactory)InjektKt.getInjekt()).getInstance(((FullTypeReference)new FullTypeReference() {}).getType())).trustedSignatures();
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Injekt.get<PreferencesHe…er>().trustedSignatures()");
    localObject = PreferencesHelperKt.getOrDefault((Preference)localObject);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Injekt.get<PreferencesHe…gnatures().getOrDefault()");
    trustedSignatures = SetsKt.plus(SetsKt.plus(localSet, (Iterable)localObject), "7ce04da7773d41b489f4693a366c36bcd0a11fc39b547168553c285bd7348e23");
  }
  
  private ExtensionLoader() {}
  
  private final String getSignatureHash(PackageInfo paramPackageInfo)
  {
    Object localObject = paramPackageInfo.signatures;
    if (localObject != null)
    {
      int i;
      if (localObject.length == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0)
      {
        paramPackageInfo = Hash.INSTANCE;
        localObject = ((Signature)ArraysKt.first((Object[])localObject)).toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(localObject, "signatures.first().toByteArray()");
        return paramPackageInfo.sha256((byte[])localObject);
      }
    }
    return null;
  }
  
  private final boolean isPackageAnExtension(PackageInfo paramPackageInfo)
  {
    paramPackageInfo = paramPackageInfo.reqFeatures;
    if (paramPackageInfo == null) {
      paramPackageInfo = new FeatureInfo[0];
    }
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      if (Intrinsics.areEqual(paramPackageInfo[i].name, "tachiyomi.extension")) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private final LoadResult loadExtension(Context paramContext, String paramString, PackageInfo paramPackageInfo)
  {
    Object localObject1 = paramContext.getPackageManager();
    try
    {
      Object localObject3 = ((PackageManager)localObject1).getApplicationInfo(paramString, 128);
      localObject1 = ((PackageManager)localObject1).getApplicationLabel((ApplicationInfo)localObject3);
      if (localObject1 != null) {
        localObject1 = localObject1.toString();
      } else {
        localObject1 = null;
      }
      if (localObject1 == null) {
        localObject1 = "";
      }
      String str1 = StringsKt.substringAfter$default((String)localObject1, "Tachiyomi: ", null, 2, null);
      String str2 = paramPackageInfo.versionName;
      int i = paramPackageInfo.versionCode;
      Intrinsics.checkExpressionValueIsNotNull(str2, "versionName");
      int j = Integer.parseInt(StringsKt.substringBefore$default(str2, '.', null, 2, null));
      if ((j >= 1) && (j <= 1))
      {
        localObject1 = getSignatureHash(paramPackageInfo);
        if (localObject1 == null)
        {
          paramContext = new StringBuilder();
          paramContext.append("Package ");
          paramContext.append(paramString);
          paramContext.append(" isn't signed");
          return (LoadResult)new LoadResult.Error(paramContext.toString());
        }
        if (!trustedSignatures.contains(localObject1))
        {
          paramContext = new Extension.Untrusted(str1, paramString, str2, i, (String)localObject1, null, 32, null);
          paramPackageInfo = new StringBuilder();
          paramPackageInfo.append("Extension ");
          paramPackageInfo.append(paramString);
          paramPackageInfo.append(" isn't trusted");
          Timber.w(paramPackageInfo.toString(), new Object[0]);
          return (LoadResult)new LoadResult.Untrusted(paramContext);
        }
        Object localObject2 = new PathClassLoader(((ApplicationInfo)localObject3).sourceDir, null, paramContext.getClassLoader());
        paramContext = ((ApplicationInfo)localObject3).metaData.getString("tachiyomi.extension.class");
        Intrinsics.checkExpressionValueIsNotNull(paramContext, "appInfo.metaData.getString(METADATA_SOURCE_CLASS)");
        paramContext = (Iterable)StringsKt.split$default((CharSequence)paramContext, new String[] { ";" }, false, 0, 6, null);
        localObject3 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault(paramContext, 10));
        Iterator localIterator = paramContext.iterator();
        while (localIterator.hasNext())
        {
          paramContext = (String)localIterator.next();
          if (paramContext != null)
          {
            localObject1 = StringsKt.trim((CharSequence)paramContext).toString();
            paramContext = (Context)localObject1;
            if (StringsKt.startsWith$default((String)localObject1, ".", false, 2, null))
            {
              paramContext = new StringBuilder();
              paramContext.append(paramPackageInfo.packageName);
              paramContext.append((String)localObject1);
              paramContext = paramContext.toString();
            }
            ((Collection)localObject3).add(paramContext);
          }
          else
          {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
          }
        }
        paramContext = (Iterable)localObject3;
        paramPackageInfo = (Collection)new ArrayList();
        localObject1 = paramContext.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          paramContext = (String)((Iterator)localObject1).next();
          try
          {
            paramContext = Class.forName(paramContext, false, (ClassLoader)localObject2).newInstance();
            if ((paramContext instanceof Source))
            {
              paramContext = CollectionsKt.listOf(paramContext);
            }
            else
            {
              if (!(paramContext instanceof SourceFactory)) {
                break label561;
              }
              paramContext = ((SourceFactory)paramContext).createSources();
            }
            CollectionsKt.addAll(paramPackageInfo, (Iterable)paramContext);
            continue;
            label561:
            paramString = new StringBuilder();
            paramString.append("Unknown source class type! ");
            paramString.append(paramContext.getClass());
            throw ((Throwable)new Exception(paramString.toString()));
          }
          catch (Throwable paramContext)
          {
            paramString = new StringBuilder();
            paramString.append("Extension load error: ");
            paramString.append(str1);
            paramString.append('.');
            Timber.e(paramContext, paramString.toString(), new Object[0]);
            return (LoadResult)new LoadResult.Error(paramContext);
          }
        }
        paramPackageInfo = (List)paramPackageInfo;
        localObject1 = (Iterable)paramPackageInfo;
        paramContext = (Collection)new ArrayList();
        localObject1 = ((Iterable)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = ((Iterator)localObject1).next();
          if ((localObject2 instanceof CatalogueSource)) {
            paramContext.add(localObject2);
          }
        }
        localObject1 = (Iterable)paramContext;
        paramContext = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject1, 10));
        localObject1 = ((Iterable)localObject1).iterator();
        while (((Iterator)localObject1).hasNext()) {
          paramContext.add(((CatalogueSource)((Iterator)localObject1).next()).getLang());
        }
        paramContext = CollectionsKt.toSet((Iterable)paramContext);
        switch (paramContext.size())
        {
        default: 
          paramContext = "all";
        }
        for (;;)
        {
          break;
          paramContext = (String)CollectionsKt.first((Iterable)paramContext);
          continue;
          paramContext = "";
        }
        return (LoadResult)new LoadResult.Success(new Extension.Installed(str1, paramString, str2, i, paramPackageInfo, paramContext, false, 64, null));
      }
      paramContext = new StringBuilder();
      paramContext.append("Lib version is ");
      paramContext.append(j);
      paramContext.append(", while only versions ");
      paramContext.append("1 to 1 are allowed");
      paramContext = (Throwable)new Exception(paramContext.toString());
      Timber.w(paramContext);
      return (LoadResult)new LoadResult.Error(paramContext);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return (LoadResult)new LoadResult.Error((Throwable)paramContext);
  }
  
  @NotNull
  public final Set<String> getTrustedSignatures()
  {
    return trustedSignatures;
  }
  
  @NotNull
  public final LoadResult loadExtensionFromPkgName(@NotNull Context paramContext, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramString, "pkgName");
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 16448);
      Intrinsics.checkExpressionValueIsNotNull(localPackageInfo, "pkgInfo");
      if (!isPackageAnExtension(localPackageInfo)) {
        return (LoadResult)new LoadResult.Error("Tried to load a package that wasn't a extension");
      }
      return loadExtension(paramContext, paramString, localPackageInfo);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return (LoadResult)new LoadResult.Error((Throwable)paramContext);
  }
  
  @NotNull
  public final List<LoadResult> loadExtensions(@NotNull final Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(16448);
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "installedPkgs");
    Object localObject2 = (Iterable)localObject1;
    localObject1 = (Collection)new ArrayList();
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = ((Iterator)localObject2).next();
      PackageInfo localPackageInfo = (PackageInfo)localObject3;
      ExtensionLoader localExtensionLoader = INSTANCE;
      Intrinsics.checkExpressionValueIsNotNull(localPackageInfo, "it");
      if (localExtensionLoader.isPackageAnExtension(localPackageInfo)) {
        ((Collection)localObject1).add(localObject3);
      }
    }
    localObject1 = (List)localObject1;
    if (((List)localObject1).isEmpty()) {
      return CollectionsKt.emptyList();
    }
    (List)BuildersKt.runBlocking$default(null, (Function2)new CoroutineImpl((List)localObject1, paramContext)
    {
      Object L$0;
      Object L$1;
      Object L$2;
      Object L$3;
      Object L$4;
      Object L$5;
      Object L$6;
      Object L$7;
      private CoroutineScope p$;
      
      @NotNull
      public final Continuation<Unit> create(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super List<? extends LoadResult>> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineScope, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        paramAnonymousContinuation = new 1(this.$extPkgs, paramContext, paramAnonymousContinuation);
        paramAnonymousContinuation.p$ = paramAnonymousCoroutineScope;
        return paramAnonymousContinuation;
      }
      
      @Nullable
      public final Object doResume(@Nullable Object paramAnonymousObject, @Nullable Throwable paramAnonymousThrowable)
      {
        Object localObject6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object localObject4;
        Object localObject1;
        Iterator localIterator;
        Object localObject5;
        Object localObject2;
        Object localObject3;
        List localList;
        switch (this.label)
        {
        default: 
          throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        case 1: 
          localObject4 = (Collection)this.L$7;
          localObject1 = (Deferred)this.L$6;
          localObject1 = this.L$5;
          localIterator = (Iterator)this.L$4;
          localObject5 = (Collection)this.L$3;
          localObject2 = (Iterable)this.L$2;
          localObject3 = (Iterable)this.L$1;
          localList = (List)this.L$0;
          if (paramAnonymousThrowable == null)
          {
            localObject1 = this;
            paramAnonymousThrowable = (Throwable)localObject5;
            localObject5 = paramAnonymousObject;
          }
          else
          {
            throw paramAnonymousThrowable;
          }
          break;
        case 0: 
          if (paramAnonymousThrowable != null) {
            break label393;
          }
          paramAnonymousObject = this.p$;
          paramAnonymousThrowable = (Iterable)this.$extPkgs;
          paramAnonymousObject = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault(paramAnonymousThrowable, 10));
          paramAnonymousThrowable = paramAnonymousThrowable.iterator();
          while (paramAnonymousThrowable.hasNext()) {
            paramAnonymousObject.add(DeferredKt.async$default(null, null, null, (Function2)new CoroutineImpl((PackageInfo)paramAnonymousThrowable.next(), null)
            {
              private CoroutineScope p$;
              
              @NotNull
              public final Continuation<Unit> create(@NotNull CoroutineScope paramAnonymous2CoroutineScope, @NotNull Continuation<? super LoadResult> paramAnonymous2Continuation)
              {
                Intrinsics.checkParameterIsNotNull(paramAnonymous2CoroutineScope, "$receiver");
                Intrinsics.checkParameterIsNotNull(paramAnonymous2Continuation, "continuation");
                paramAnonymous2Continuation = new 1(this.$it, paramAnonymous2Continuation, jdField_this);
                paramAnonymous2Continuation.p$ = paramAnonymous2CoroutineScope;
                return paramAnonymous2Continuation;
              }
              
              @Nullable
              public final Object doResume(@Nullable Object paramAnonymous2Object, @Nullable Throwable paramAnonymous2Throwable)
              {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0)
                {
                  if (paramAnonymous2Throwable == null)
                  {
                    paramAnonymous2Object = this.p$;
                    paramAnonymous2Object = ExtensionLoader.INSTANCE;
                    paramAnonymous2Throwable = jdField_this.$context;
                    String str = this.$it.packageName;
                    Intrinsics.checkExpressionValueIsNotNull(str, "it.packageName");
                    PackageInfo localPackageInfo = this.$it;
                    Intrinsics.checkExpressionValueIsNotNull(localPackageInfo, "it");
                    return ExtensionLoader.access$loadExtension(paramAnonymous2Object, paramAnonymous2Throwable, str, localPackageInfo);
                  }
                  throw paramAnonymous2Throwable;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
              }
              
              @Nullable
              public final Object invoke(@NotNull CoroutineScope paramAnonymous2CoroutineScope, @NotNull Continuation<? super LoadResult> paramAnonymous2Continuation)
              {
                return ((1)create(paramAnonymous2CoroutineScope, paramAnonymous2Continuation)).doResume(Unit.INSTANCE, null);
              }
            }, 7, null));
          }
          localList = (List)paramAnonymousObject;
          paramAnonymousObject = (Iterable)localList;
          localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault(paramAnonymousObject, 10));
          localIterator = paramAnonymousObject.iterator();
          localObject3 = paramAnonymousObject;
          paramAnonymousThrowable = this;
          localObject2 = paramAnonymousObject;
          paramAnonymousObject = localObject1;
        }
        while (localIterator.hasNext())
        {
          localObject1 = localIterator.next();
          localObject4 = (Deferred)localObject1;
          paramAnonymousThrowable.L$0 = localList;
          paramAnonymousThrowable.L$1 = localObject3;
          paramAnonymousThrowable.L$2 = localObject2;
          paramAnonymousThrowable.L$3 = paramAnonymousObject;
          paramAnonymousThrowable.L$4 = localIterator;
          paramAnonymousThrowable.L$5 = localObject1;
          paramAnonymousThrowable.L$6 = localObject4;
          paramAnonymousThrowable.L$7 = paramAnonymousObject;
          paramAnonymousThrowable.label = 1;
          localObject5 = ((Deferred)localObject4).await(paramAnonymousThrowable);
          if (localObject5 == localObject6) {
            return localObject6;
          }
          localObject4 = paramAnonymousObject;
          localObject1 = paramAnonymousThrowable;
          paramAnonymousThrowable = (Throwable)localObject4;
          localObject4 = paramAnonymousObject;
          ((Collection)localObject4).add((LoadResult)localObject5);
          paramAnonymousObject = paramAnonymousThrowable;
          paramAnonymousThrowable = (Throwable)localObject1;
        }
        return (List)paramAnonymousObject;
        label393:
        throw paramAnonymousThrowable;
      }
      
      @Nullable
      public final Object invoke(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super List<? extends LoadResult>> paramAnonymousContinuation)
      {
        return ((1)create(paramAnonymousCoroutineScope, paramAnonymousContinuation)).doResume(Unit.INSTANCE, null);
      }
    }, 1, null);
  }
  
  public final void setTrustedSignatures(@NotNull Set<String> paramSet)
  {
    Intrinsics.checkParameterIsNotNull(paramSet, "<set-?>");
    trustedSignatures = paramSet;
  }
}
