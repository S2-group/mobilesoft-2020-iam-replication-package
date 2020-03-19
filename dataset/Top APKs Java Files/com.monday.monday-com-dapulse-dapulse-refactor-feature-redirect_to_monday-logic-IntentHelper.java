package com.dapulse.dapulse.refactor.feature.redirect_to_monday.logic;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 1}, d1={"\000\f\n\002\030\002\n\002\020\000\n\002\b\003\030\000 \0032\0020\001:\001\003B\005¢\006\002\020\002¨\006\004"}, d2={"Lcom/dapulse/dapulse/refactor/feature/redirect_to_monday/logic/IntentHelper;", "", "()V", "Companion", "app_mondayProdMonday"}, k=1, mv={1, 1, 6})
public final class IntentHelper
{
  public static final Companion Companion = new Companion(null);
  private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";
  private static final String GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending";
  @NotNull
  private static final String mondayAppId = "com.monday.monday";
  
  public IntentHelper() {}
  
  @Metadata(bv={1, 0, 1}, d1={"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\013\032\0020\f2\006\020\r\032\0020\016H\002J\022\020\017\032\004\030\0010\0202\b\020\021\032\004\030\0010\004J\022\020\022\032\004\030\0010\f2\006\020\r\032\0020\016H\002J\016\020\023\032\0020\0242\006\020\r\032\0020\016J\016\020\025\032\0020\0242\006\020\r\032\0020\016J\026\020\026\032\0020\0242\006\020\r\032\0020\0162\006\020\027\032\0020\004R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006R\024\020\t\032\0020\004XD¢\006\b\n\000\032\004\b\n\020\006¨\006\030"}, d2={"Lcom/dapulse/dapulse/refactor/feature/redirect_to_monday/logic/IntentHelper$Companion;", "", "()V", "GOOGLE_PLAY", "", "getGOOGLE_PLAY", "()Ljava/lang/String;", "GOOGLE_PLAY_PACKAGE_NAME", "getGOOGLE_PLAY_PACKAGE_NAME", "mondayAppId", "getMondayAppId", "createIntentForGooglePlay", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "getGooglePlay", "Landroid/net/Uri;", "packageName", "getMondayAppIntentIfExists", "goToStore", "", "isMondayAppInstalled", "isPackageExists", "targetPackage", "app_mondayProdMonday"}, k=1, mv={1, 1, 6})
  public static final class Companion
  {
    private Companion() {}
    
    private final Intent createIntentForGooglePlay(Context paramContext)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", ((Companion)this).getGooglePlay(((Companion)this).getMondayAppId()));
      if (((Companion)this).isPackageExists(paramContext, ((Companion)this).getGOOGLE_PLAY_PACKAGE_NAME()))
      {
        localIntent.setPackage(((Companion)this).getGOOGLE_PLAY_PACKAGE_NAME());
        if (paramContext.getPackageManager().queryIntentActivities(localIntent, 0).size() == 0) {
          localIntent.setPackage((String)null);
        }
      }
      return localIntent;
    }
    
    private final String getGOOGLE_PLAY()
    {
      return IntentHelper.access$getGOOGLE_PLAY$cp();
    }
    
    private final String getGOOGLE_PLAY_PACKAGE_NAME()
    {
      return IntentHelper.access$getGOOGLE_PLAY_PACKAGE_NAME$cp();
    }
    
    private final Intent getMondayAppIntentIfExists(Context paramContext)
    {
      if (((Companion)this).isPackageExists(paramContext, ((Companion)this).getMondayAppId())) {
        return paramContext.getPackageManager().getLaunchIntentForPackage(((Companion)this).getMondayAppId());
      }
      return null;
    }
    
    @Nullable
    public final Uri getGooglePlay(@Nullable String paramString)
    {
      if (paramString == null) {
        return null;
      }
      return Uri.parse(((Companion)this).getGOOGLE_PLAY() + paramString);
    }
    
    @NotNull
    public final String getMondayAppId()
    {
      return IntentHelper.access$getMondayAppId$cp();
    }
    
    public final boolean goToStore(@NotNull Context paramContext)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      Intent localIntent = ((Companion)this).getMondayAppIntentIfExists(paramContext);
      if (localIntent != null)
      {
        paramContext.startActivity(localIntent);
        return false;
      }
      paramContext.startActivity(((Companion)this).createIntentForGooglePlay(paramContext));
      return true;
    }
    
    public final boolean isMondayAppInstalled(@NotNull Context paramContext)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      return ((Companion)this).getMondayAppIntentIfExists(paramContext) != null;
    }
    
    public final boolean isPackageExists(@NotNull Context paramContext, @NotNull String paramString)
    {
      boolean bool2 = false;
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      Intrinsics.checkParameterIsNotNull(paramString, "targetPackage");
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      do
      {
        bool1 = bool2;
        if (!paramContext.hasNext()) {
          break;
        }
      } while (!Intrinsics.areEqual(((ApplicationInfo)paramContext.next()).packageName, paramString));
      boolean bool1 = true;
      return bool1;
    }
  }
}
