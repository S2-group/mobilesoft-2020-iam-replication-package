package com.google.android.gms.games.broker;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.content.SyncStats;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ClientContext;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.provider.QuerySpec;
import com.google.android.gms.common.server.BaseApiaryServer;
import com.google.android.gms.common.server.response.FastMapJsonResponse;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.drive.server.apiary.DriveApiaryServer;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerLevel;
import com.google.android.gms.games.PlayerLevelInfo;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.cache.PlayerCache;
import com.google.android.gms.games.cache.XpEventStreamCache;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.games.internal.PopupManager.PopupLocationInfo;
import com.google.android.gms.games.internal.events.EventIncrementEntry;
import com.google.android.gms.games.provider.GamesContractInternal;
import com.google.android.gms.games.provider.GamesContractInternal.AccountMetadata;
import com.google.android.gms.games.provider.GamesContractInternal.ClientContexts;
import com.google.android.gms.games.provider.GamesContractInternal.Games;
import com.google.android.gms.games.provider.GamesContractInternal.Milestones;
import com.google.android.gms.games.provider.GamesContractInternal.Players;
import com.google.android.gms.games.provider.GamesContractInternal.Quests;
import com.google.android.gms.games.provider.GamesContractInternal.QuestsEntities;
import com.google.android.gms.games.server.GamesServer;
import com.google.android.gms.games.server.GamesServerFactory;
import com.google.android.gms.games.server.api.FirstPartyApplication;
import com.google.android.gms.games.server.api.InvalidId;
import com.google.android.gms.games.server.api.QuestMetadata;
import com.google.android.gms.games.server.api.Request;
import com.google.android.gms.games.server.api.RequestEntity;
import com.google.android.gms.games.service.GamesSyncAdapter.GamesSyncResult;
import com.google.android.gms.games.ui.popup.LevelPopup;
import com.google.android.gms.games.util.GamesUtils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class DataBroker
  extends Lockable
{
  private static final Lockable.LockableLock AGENT_SET_LOCK = new Lockable.LockableLock();
  private static DataBroker sInstance;
  public final AccountAgent mAccountAgent;
  public final AchievementAgent mAchievementAgent;
  private final AppContentAgent mCardStreamAgent;
  public final EventAgent mEventAgent;
  public final GameAgent mGameAgent;
  private final BaseApiaryServer mGameSearchSuggestServer;
  private final GamesServer mGamesServer;
  public final ArrayList<InboxCounter> mInboxCounters;
  private final GamesServer mInternalGamesServer;
  public final LeaderboardAgent mLeaderboardAgent;
  public final MultiplayerAgent mMultiplayerAgent;
  public final NotificationAgent mNotificationAgent;
  public final PlayCommonAgent mPlayCommonAgent;
  public final PlayerAgent mPlayerAgent;
  public final QuestAgent mQuestAgent;
  private final RealTimeAgent mRealTimeAgent;
  public final RequestAgent mRequestAgent;
  private final RevisionAgent mRevisionAgent;
  public final SnapshotAgent mSnapshotAgent;
  private final SocialAgent mSocialAgent;
  private final StatsAgent mStatsAgent;
  private final ArrayList<TransientCacheOwner> mTransientCaches;
  public final TurnBasedAgent mTurnBasedAgent;
  public final VideoAgent mVideoAgent;
  private final BaseApiaryServer mYoutubeServer;
  
  private DataBroker(Context paramContext)
  {
    super("DataBroker", AGENT_SET_LOCK, null);
    Context localContext = paramContext.getApplicationContext();
    this.mTransientCaches = new ArrayList();
    this.mInboxCounters = new ArrayList();
    this.mGamesServer = new GamesServer(localContext, false, ((Boolean)com.google.android.gms.games.config.G.cacheEnabled.get()).booleanValue(), ((Boolean)com.google.android.gms.games.config.G.verboseVolleyLogging.get()).booleanValue(), GamesServerFactory.getTraceToken());
    this.mInternalGamesServer = new GamesServer(localContext, true, ((Boolean)com.google.android.gms.games.config.G.cacheEnabled.get()).booleanValue(), ((Boolean)com.google.android.gms.games.config.G.verboseVolleyLogging.get()).booleanValue(), GamesServerFactory.getTraceToken());
    this.mGameSearchSuggestServer = new BaseApiaryServer(localContext, (String)com.google.android.gms.games.config.G.baseFinskyUrl.get(), "/suggest", false, ((Boolean)com.google.android.gms.games.config.G.verboseVolleyLogging.get()).booleanValue(), null, null);
    String str2 = (String)com.google.android.gms.games.config.G.baseServerUrl.get();
    String str3 = (String)com.google.android.gms.games.config.G.youtubeDataApiPath.get();
    boolean bool1 = ((Boolean)com.google.android.gms.games.config.G.cacheEnabled.get()).booleanValue();
    boolean bool2 = ((Boolean)com.google.android.gms.games.config.G.verboseVolleyLogging.get()).booleanValue();
    String str1 = (String)com.google.android.gms.games.config.G.apiaryTraceToken.get();
    if (!TextUtils.isEmpty(str1)) {}
    for (str1 = "token:" + str1;; str1 = null)
    {
      this.mYoutubeServer = new BaseApiaryServer(localContext, str2, str3, bool1, bool2, str1, null);
      paramContext = new DriveApiaryServer(paramContext, (String)com.google.android.gms.drive.G.serverUrl.get(), "/drive/v2/", ((Boolean)com.google.android.gms.games.config.G.cacheEnabled.get()).booleanValue(), ((Boolean)com.google.android.gms.games.config.G.verboseVolleyLogging.get()).booleanValue());
      this.mAccountAgent = ((AccountAgent)addAgent(new AccountAgent(this)));
      this.mAchievementAgent = ((AchievementAgent)addAgent(new AchievementAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mCardStreamAgent = ((AppContentAgent)addAgent(new AppContentAgent(this, this.mInternalGamesServer)));
      this.mEventAgent = ((EventAgent)addAgent(new EventAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mGameAgent = ((GameAgent)addAgent(new GameAgent(this, this.mGamesServer, this.mInternalGamesServer, this.mGameSearchSuggestServer)));
      this.mLeaderboardAgent = ((LeaderboardAgent)addAgent(new LeaderboardAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mNotificationAgent = ((NotificationAgent)addAgent(new NotificationAgent(this)));
      this.mPlayCommonAgent = ((PlayCommonAgent)addAgent(new PlayCommonAgent(this)));
      this.mPlayerAgent = ((PlayerAgent)addAgent(new PlayerAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mQuestAgent = ((QuestAgent)addAgent(new QuestAgent(this, this.mGamesServer, this.mInternalGamesServer, this.mEventAgent)));
      this.mRevisionAgent = ((RevisionAgent)addAgent(new RevisionAgent(this, this.mInternalGamesServer)));
      this.mRequestAgent = ((RequestAgent)addAgent(new RequestAgent(this, this.mInternalGamesServer)));
      this.mSnapshotAgent = ((SnapshotAgent)addAgent(new SnapshotAgent(this, this.mGamesServer, this.mInternalGamesServer, paramContext)));
      this.mStatsAgent = ((StatsAgent)addAgent(new StatsAgent(this, this.mGamesServer)));
      this.mMultiplayerAgent = ((MultiplayerAgent)addAgent(new MultiplayerAgent(this, this.mInternalGamesServer)));
      this.mRealTimeAgent = ((RealTimeAgent)addAgent(new RealTimeAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mTurnBasedAgent = ((TurnBasedAgent)addAgent(new TurnBasedAgent(this, this.mGamesServer, this.mInternalGamesServer)));
      this.mVideoAgent = ((VideoAgent)addAgent(new VideoAgent(this, this.mYoutubeServer)));
      this.mSocialAgent = ((SocialAgent)addAgent(new SocialAgent(this, this.mInternalGamesServer)));
      return;
    }
  }
  
  public static DataBroker acquireBroker(Context paramContext)
  {
    GamesUtils.assertGamesProcess();
    AGENT_SET_LOCK.lock();
    try
    {
      if (sInstance == null) {
        sInstance = new DataBroker(paramContext);
      }
      return sInstance;
    }
    finally
    {
      AGENT_SET_LOCK.unlock();
    }
  }
  
  private <T> T addAgent(T paramT)
  {
    if ((paramT instanceof TransientCacheOwner)) {
      this.mTransientCaches.add((TransientCacheOwner)paramT);
    }
    if ((paramT instanceof InboxCounter)) {
      this.mInboxCounters.add((InboxCounter)paramT);
    }
    return paramT;
  }
  
  private boolean areQuestNotificationsEnabled(ClientContext paramClientContext)
  {
    Object localObject2 = DataHolder.empty(0);
    boolean bool = true;
    Object localObject1 = localObject2;
    try
    {
      paramClientContext = loadContactSettings(paramClientContext, false);
      localObject1 = paramClientContext;
      localObject2 = paramClientContext;
      if (paramClientContext.mRowCount > 0)
      {
        localObject1 = paramClientContext;
        localObject2 = paramClientContext;
        bool = paramClientContext.getBoolean("quest_notifications_enabled", 0, paramClientContext.getWindowIndex(0));
      }
      paramClientContext.close();
      return bool;
    }
    catch (GoogleAuthException paramClientContext)
    {
      localObject2 = localObject1;
      GamesLog.e("DataBroker", "Failed to fetch contact settings", paramClientContext);
      return true;
    }
    finally
    {
      ((DataHolder)localObject2).close();
    }
  }
  
  private void clearTransientData_locked()
  {
    super.assertAllChildrenLockedByCurrentThread();
    int i = 0;
    int j = this.mTransientCaches.size();
    while (i < j)
    {
      ((TransientCacheOwner)this.mTransientCaches.get(i)).clearTransientCaches();
      i += 1;
    }
  }
  
  public static GamesClientContext fetchPlayerIdFromAccountWhenMissing(GamesClientContext paramGamesClientContext)
  {
    Object localObject = paramGamesClientContext;
    if (paramGamesClientContext.mExternalCurrentPlayerId == null)
    {
      localObject = AccountAgent.getExternalPlayerId(paramGamesClientContext.mContext, paramGamesClientContext.mClientContext);
      if (localObject != null)
      {
        paramGamesClientContext = paramGamesClientContext.getBuilder();
        paramGamesClientContext.mExternalCurrentPlayerId = ((String)localObject);
        localObject = paramGamesClientContext.build();
      }
    }
    else
    {
      return localObject;
    }
    GamesLog.w("DataBroker", "No player ID found when refreshing");
    return paramGamesClientContext;
  }
  
  private void gainXp(GamesClientContext paramGamesClientContext, long paramLong, PopupManager.PopupLocationInfo paramPopupLocationInfo)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mPlayerAgent });
    for (;;)
    {
      int i;
      try
      {
        localObject1 = this.mPlayerAgent;
        localPlayer = ((PlayerAgent)localObject1).getLocalPlayer(paramGamesClientContext);
        if (localPlayer == null)
        {
          GamesLog.e("PlayerAgent", "Could not find player " + paramGamesClientContext.mExternalTargetPlayerId);
          i = 1;
          releaseLocks(new Lockable[] { this.mPlayerAgent });
          if (i != 1501) {
            break label554;
          }
          i = 1;
          if (i != 0) {
            acquireLocks(new Lockable[] { this.mAchievementAgent, this.mPlayerAgent });
          }
        }
      }
      finally
      {
        Object localObject1;
        Player localPlayer;
        Object localObject3;
        Object localObject2;
        boolean bool2;
        boolean bool1;
        releaseLocks(new Lockable[] { this.mPlayerAgent });
      }
      try
      {
        paramGamesClientContext = paramGamesClientContext.getBuilder();
        paramGamesClientContext.mForceReload = true;
        paramGamesClientContext.mExternalTargetPlayerId = null;
        paramGamesClientContext = paramGamesClientContext.build();
        this.mAchievementAgent.submitPendingAchievementOps(paramGamesClientContext, new SyncResult());
        this.mPlayerAgent.fetchPlayer(paramGamesClientContext, true).close();
        return;
      }
      finally
      {
        label554:
        releaseLocks(new Lockable[] { this.mAchievementAgent, this.mPlayerAgent });
      }
      if (localPlayer.getLevelInfo() == null)
      {
        GamesLog.w("PlayerAgent", "Trying to add XP values to a player with no level info!");
        i = 1;
      }
      else
      {
        localObject3 = localPlayer.getLevelInfo();
        localObject2 = new ContentValues();
        paramLong = ((PlayerLevelInfo)localObject3).mCurrentXpTotal + paramLong;
        ((ContentValues)localObject2).put("current_xp_total", Long.valueOf(paramLong));
        bool2 = false;
        bool1 = bool2;
        if (paramLong >= ((PlayerLevelInfo)localObject3).mCurrentLevel.mMaxXp)
        {
          localObject3 = ((PlayerAgent)localObject1).getCurrentAndNextLevels(paramGamesClientContext, paramLong);
          bool1 = bool2;
          if (localObject3 != null)
          {
            ((ContentValues)localObject2).put("current_level", Integer.valueOf(((PlayerLevel)((Pair)localObject3).first).mLevelNumber));
            ((ContentValues)localObject2).put("current_level_min_xp", Long.valueOf(((PlayerLevel)((Pair)localObject3).first).mMinXp));
            ((ContentValues)localObject2).put("current_level_max_xp", Long.valueOf(((PlayerLevel)((Pair)localObject3).first).mMaxXp));
            ((ContentValues)localObject2).put("last_level_up_timestamp", Long.valueOf(DefaultClock.getInstance().currentTimeMillis()));
            ((ContentValues)localObject2).put("next_level", Integer.valueOf(((PlayerLevel)((Pair)localObject3).second).mLevelNumber));
            ((ContentValues)localObject2).put("next_level_max_xp", Long.valueOf(((PlayerLevel)((Pair)localObject3).second).mMaxXp));
            bool1 = true;
          }
        }
        localObject2 = new Pair(localObject2, Boolean.valueOf(bool1));
        localObject3 = GamesContractInternal.Players.getUriForExternalPlayerId(paramGamesClientContext.mClientContext, paramGamesClientContext.getExternalPlayerId());
        paramGamesClientContext.mContext.getContentResolver().update((Uri)localObject3, (ContentValues)((Pair)localObject2).first, null, null);
        ((PlayerAgent)localObject1).mXpStreamEventStreamCache.clear();
        if (!((Boolean)((Pair)localObject2).second).booleanValue())
        {
          i = 0;
        }
        else
        {
          localObject1 = ((PlayerAgent)localObject1).getLocalPlayer(paramGamesClientContext);
          LevelPopup.show(paramGamesClientContext, paramPopupLocationInfo, (Player)localPlayer.freeze(), (Player)((Player)localObject1).freeze());
          i = 1501;
          continue;
          i = 0;
        }
      }
    }
  }
  
  /* Error */
  private DataHolder getCachedGame(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   23: astore 5
    //   25: aload_1
    //   26: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   29: astore 6
    //   31: iconst_0
    //   32: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: aload 5
    //   38: getfield 584	com/google/android/gms/games/broker/GameAgent:mCache	Lcom/google/android/gms/games/cache/GameCache;
    //   41: aload_2
    //   42: invokevirtual 590	com/google/android/gms/games/cache/GameCache:enableCaching	(Ljava/lang/Object;)V
    //   45: aload 5
    //   47: getfield 584	com/google/android/gms/games/broker/GameAgent:mCache	Lcom/google/android/gms/games/cache/GameCache;
    //   50: invokevirtual 594	com/google/android/gms/games/cache/GameCache:getCacheKeys	()Ljava/util/Set;
    //   53: invokeinterface 600 1 0
    //   58: astore 7
    //   60: aload 7
    //   62: invokeinterface 605 1 0
    //   67: ifeq +172 -> 239
    //   70: aload 7
    //   72: invokeinterface 608 1 0
    //   77: checkcast 129	java/lang/String
    //   80: astore 4
    //   82: aload 5
    //   84: getfield 584	com/google/android/gms/games/broker/GameAgent:mCache	Lcom/google/android/gms/games/cache/GameCache;
    //   87: aload 4
    //   89: ldc_w 610
    //   92: aload 6
    //   94: invokevirtual 614	com/google/android/gms/games/cache/GameCache:findMatchingRows	(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   97: astore 4
    //   99: aload 4
    //   101: getfield 333	com/google/android/gms/common/data/DataHolder:mRowCount	I
    //   104: ifle +100 -> 204
    //   107: aload 4
    //   109: astore_1
    //   110: aload_1
    //   111: getfield 333	com/google/android/gms/common/data/DataHolder:mRowCount	I
    //   114: ifne +122 -> 236
    //   117: aload 5
    //   119: getfield 618	com/google/android/gms/games/broker/GameAgent:mSearchCache	Lcom/google/android/gms/games/cache/GameSearchCache;
    //   122: aload_2
    //   123: invokevirtual 621	com/google/android/gms/games/cache/GameSearchCache:enableCaching	(Ljava/lang/Object;)V
    //   126: aload 5
    //   128: getfield 618	com/google/android/gms/games/broker/GameAgent:mSearchCache	Lcom/google/android/gms/games/cache/GameSearchCache;
    //   131: invokevirtual 622	com/google/android/gms/games/cache/GameSearchCache:getCacheKeys	()Ljava/util/Set;
    //   134: invokeinterface 600 1 0
    //   139: astore 4
    //   141: aload 4
    //   143: invokeinterface 605 1 0
    //   148: ifeq +88 -> 236
    //   151: aload 4
    //   153: invokeinterface 608 1 0
    //   158: checkcast 129	java/lang/String
    //   161: astore_2
    //   162: aload 5
    //   164: getfield 618	com/google/android/gms/games/broker/GameAgent:mSearchCache	Lcom/google/android/gms/games/cache/GameSearchCache;
    //   167: aload_2
    //   168: ldc_w 610
    //   171: aload 6
    //   173: invokevirtual 623	com/google/android/gms/games/cache/GameSearchCache:findMatchingRows	(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   176: astore_2
    //   177: aload_2
    //   178: getfield 333	com/google/android/gms/common/data/DataHolder:mRowCount	I
    //   181: istore_3
    //   182: iload_3
    //   183: ifle +46 -> 229
    //   186: aload_2
    //   187: astore_1
    //   188: iconst_1
    //   189: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   192: dup
    //   193: iconst_0
    //   194: aload_0
    //   195: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   198: aastore
    //   199: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   202: aload_1
    //   203: areturn
    //   204: aload 4
    //   206: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   209: goto -149 -> 60
    //   212: astore_1
    //   213: iconst_1
    //   214: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   217: dup
    //   218: iconst_0
    //   219: aload_0
    //   220: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   223: aastore
    //   224: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   227: aload_1
    //   228: athrow
    //   229: aload_2
    //   230: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   233: goto -92 -> 141
    //   236: goto -48 -> 188
    //   239: goto -129 -> 110
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	this	DataBroker
    //   0	242	1	paramGamesClientContext	GamesClientContext
    //   0	242	2	paramString	String
    //   181	2	3	i	int
    //   80	125	4	localObject	Object
    //   23	140	5	localGameAgent	GameAgent
    //   29	143	6	str	String
    //   58	13	7	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   19	60	212	finally
    //   60	107	212	finally
    //   110	141	212	finally
    //   141	182	212	finally
    //   204	209	212	finally
    //   229	233	212	finally
  }
  
  /* Error */
  private com.google.android.gms.games.Game getGameForVideoCapture(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   4: astore 4
    //   6: aload 4
    //   8: astore_3
    //   9: aload 4
    //   11: ifnonnull +19 -> 30
    //   14: ldc 70
    //   16: ldc_w 627
    //   19: invokestatic 630	com/google/android/gms/games/internal/GamesLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   22: aload_1
    //   23: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   26: invokestatic 636	com/google/android/gms/games/util/AccountUtils:getResolvedAccountName	(Lcom/google/android/gms/common/internal/ClientContext;)Ljava/lang/String;
    //   29: astore_3
    //   30: aload_0
    //   31: aload_1
    //   32: aload_3
    //   33: invokespecial 638	com/google/android/gms/games/broker/DataBroker:getCachedGame	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   36: astore 4
    //   38: aload 4
    //   40: astore_3
    //   41: aload 4
    //   43: getfield 333	com/google/android/gms/common/data/DataHolder:mRowCount	I
    //   46: ifne +15 -> 61
    //   49: aload 4
    //   51: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   54: aload_0
    //   55: aload_1
    //   56: aconst_null
    //   57: invokevirtual 641	com/google/android/gms/games/broker/DataBroker:getGame	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   60: astore_3
    //   61: aconst_null
    //   62: astore 4
    //   64: aload 4
    //   66: astore_1
    //   67: aload_3
    //   68: getfield 644	com/google/android/gms/common/data/DataHolder:mStatusCode	I
    //   71: ifne +44 -> 115
    //   74: new 646	com/google/android/gms/games/client/games/GameFirstPartyBuffer
    //   77: dup
    //   78: aload_3
    //   79: invokespecial 649	com/google/android/gms/games/client/games/GameFirstPartyBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual 652	com/google/android/gms/games/client/games/GameFirstPartyBuffer:getCount	()I
    //   87: istore_2
    //   88: iload_2
    //   89: iconst_1
    //   90: if_icmpne +31 -> 121
    //   93: aload_1
    //   94: iconst_0
    //   95: invokevirtual 653	com/google/android/gms/games/client/games/GameFirstPartyBuffer:get	(I)Ljava/lang/Object;
    //   98: checkcast 655	com/google/android/gms/games/client/games/GameFirstParty
    //   101: invokeinterface 658 1 0
    //   106: invokeinterface 661 1 0
    //   111: checkcast 660	com/google/android/gms/games/Game
    //   114: astore_1
    //   115: aload_3
    //   116: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   119: aload_1
    //   120: areturn
    //   121: ldc 70
    //   123: new 153	java/lang/StringBuilder
    //   126: dup
    //   127: ldc_w 663
    //   130: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: iload_2
    //   134: invokevirtual 666	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   137: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   143: aload 4
    //   145: astore_1
    //   146: goto -31 -> 115
    //   149: astore_1
    //   150: aload_3
    //   151: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   154: aload_1
    //   155: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	this	DataBroker
    //   0	156	1	paramGamesClientContext	GamesClientContext
    //   87	47	2	i	int
    //   8	143	3	localObject1	Object
    //   4	140	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   67	88	149	finally
    //   93	115	149	finally
    //   121	143	149	finally
  }
  
  public static boolean isStreamingMetered(Context paramContext)
  {
    return VideoAgent.isStreamingMetered(paramContext);
  }
  
  private void updatePlayerCachesForSocialChange(DataHolder paramDataHolder)
  {
    this.mPlayerAgent.assertLockedByCurrentThread();
    if (paramDataHolder.mStatusCode != 0) {}
    do
    {
      return;
      localObject = new PlayerBuffer(paramDataHolder);
    } while (((PlayerBuffer)localObject).getCount() == 0);
    paramDataHolder = this.mPlayerAgent;
    paramDataHolder.assertLockedByCurrentThread();
    Object localObject = ((PlayerBuffer)localObject).singleRefIterator();
    while (((Iterator)localObject).hasNext())
    {
      Player localPlayer = (Player)((Iterator)localObject).next();
      ContentValues localContentValues = PlayerRef.toContentValues(localPlayer);
      int i = 0;
      while (i < 3)
      {
        String str = PlayerCache.getCacheKeyForGameIdCollection(null, null, PlayerAgent.SOCIAL_CACHE_COLLECTIONS[i]);
        paramDataHolder.mCache.modifyMatchingRowValues(str, "external_player_id", localPlayer.getPlayerId(), localContentValues);
        i += 1;
      }
    }
  }
  
  /* Error */
  public final DataHolder acceptFriendInvite(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_2
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 713	com/google/android/gms/games/broker/SocialAgent:acceptFriendInvite	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: aload_0
    //   37: aload_1
    //   38: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   41: iconst_2
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: aload_0
    //   55: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: iconst_2
    //   66: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   69: dup
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: aload_0
    //   79: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   82: aastore
    //   83: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	DataBroker
    //   0	88	1	paramGamesClientContext	GamesClientContext
    //   0	88	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   26	41	64	finally
  }
  
  /* Error */
  public final DataHolder acceptQuest(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   25: aload_1
    //   26: invokevirtual 720	com/google/android/gms/games/broker/EventAgent:submitPendingEventsForGame	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   29: istore_3
    //   30: iload_3
    //   31: ifne +36 -> 67
    //   34: aload_0
    //   35: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   38: aload_1
    //   39: aload_2
    //   40: invokevirtual 722	com/google/android/gms/games/broker/QuestAgent:acceptQuest	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   43: astore_1
    //   44: iconst_2
    //   45: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: aload_0
    //   58: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: areturn
    //   67: iload_3
    //   68: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   71: astore_1
    //   72: goto -28 -> 44
    //   75: astore_1
    //   76: iconst_2
    //   77: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   80: dup
    //   81: iconst_0
    //   82: aload_0
    //   83: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   86: aastore
    //   87: dup
    //   88: iconst_1
    //   89: aload_0
    //   90: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   93: aastore
    //   94: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   97: aload_1
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	DataBroker
    //   0	99	1	paramGamesClientContext	GamesClientContext
    //   0	99	2	paramString	String
    //   29	39	3	i	int
    // Exception table:
    //   from	to	target	type
    //   21	30	75	finally
    //   34	44	75	finally
    //   67	72	75	finally
  }
  
  /* Error */
  public final DataHolder acceptRequests(GamesClientContext paramGamesClientContext, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: new 81	java/util/ArrayList
    //   29: dup
    //   30: aload_2
    //   31: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   34: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   37: astore_2
    //   38: aload_0
    //   39: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   42: aload_1
    //   43: aload_2
    //   44: iconst_0
    //   45: iconst_1
    //   46: invokevirtual 742	com/google/android/gms/games/broker/RequestAgent:updateRequests	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/util/ArrayList;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: areturn
    //   66: astore_1
    //   67: iconst_1
    //   68: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   77: aastore
    //   78: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   81: aload_1
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	DataBroker
    //   0	83	1	paramGamesClientContext	GamesClientContext
    //   0	83	2	paramArrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   19	50	66	finally
  }
  
  /* Error */
  public final DataHolder acceptRoomInvite(GamesClientContext paramGamesClientContext, String paramString, com.google.android.gms.games.internal.ConnectionInfo paramConnectionInfo)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: aload_3
    //   33: invokevirtual 747	com/google/android/gms/games/broker/RealTimeAgent:acceptInvitation	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Lcom/google/android/gms/games/internal/ConnectionInfo;)Lcom/google/android/gms/common/data/DataHolder;
    //   36: astore_1
    //   37: iconst_1
    //   38: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   47: aastore
    //   48: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   51: aload_1
    //   52: areturn
    //   53: astore_1
    //   54: iconst_1
    //   55: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   58: dup
    //   59: iconst_0
    //   60: aload_0
    //   61: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   64: aastore
    //   65: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	this	DataBroker
    //   0	70	1	paramGamesClientContext	GamesClientContext
    //   0	70	2	paramString	String
    //   0	70	3	paramConnectionInfo	com.google.android.gms.games.internal.ConnectionInfo
    // Exception table:
    //   from	to	target	type
    //   19	37	53	finally
  }
  
  /* Error */
  public final DataHolder acceptTurnBasedInvitation(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 750	com/google/android/gms/games/broker/TurnBasedAgent:acceptInvitation	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: iconst_1
    //   37: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	DataBroker
    //   0	69	1	paramGamesClientContext	GamesClientContext
    //   0	69	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	36	52	finally
  }
  
  /* Error */
  public final int addNearbyPlayer(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 759	com/google/android/gms/games/broker/PlayerAgent:mPlayersNearbyLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: aload_0
    //   18: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   21: aload_1
    //   22: aload_2
    //   23: invokevirtual 761	com/google/android/gms/games/broker/PlayerAgent:addNearbyPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)I
    //   26: istore_3
    //   27: iconst_1
    //   28: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   31: dup
    //   32: iconst_0
    //   33: aload_0
    //   34: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   37: getfield 759	com/google/android/gms/games/broker/PlayerAgent:mPlayersNearbyLock	Lcom/google/android/gms/games/broker/Lockable;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: iload_3
    //   45: ireturn
    //   46: astore_1
    //   47: iconst_1
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   57: getfield 759	com/google/android/gms/games/broker/PlayerAgent:mPlayersNearbyLock	Lcom/google/android/gms/games/broker/Lockable;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	DataBroker
    //   0	66	1	paramGamesClientContext	GamesClientContext
    //   0	66	2	paramString	String
    //   26	19	3	i	int
    // Exception table:
    //   from	to	target	type
    //   17	27	46	finally
  }
  
  /* Error */
  public final DataHolder cancelFriendInvite(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_2
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 767	com/google/android/gms/games/broker/SocialAgent:cancelFriendInvite	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: aload_0
    //   37: aload_1
    //   38: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   41: iconst_2
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: aload_0
    //   55: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: iconst_2
    //   66: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   69: dup
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: aload_0
    //   79: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   82: aastore
    //   83: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	DataBroker
    //   0	88	1	paramGamesClientContext	GamesClientContext
    //   0	88	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   26	41	64	finally
  }
  
  /* Error */
  public final int cancelMatch(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   18: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   25: astore 5
    //   27: aload_1
    //   28: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   31: astore 6
    //   33: aload_1
    //   34: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   37: astore 7
    //   39: aload 6
    //   41: aload 7
    //   43: aload_2
    //   44: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   47: ifeq +46 -> 93
    //   50: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   53: new 153	java/lang/StringBuilder
    //   56: dup
    //   57: ldc_w 777
    //   60: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   63: aload_2
    //   64: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: sipush 6507
    //   76: istore_3
    //   77: iconst_1
    //   78: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   81: dup
    //   82: iconst_0
    //   83: aload_0
    //   84: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   87: aastore
    //   88: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   91: iload_3
    //   92: ireturn
    //   93: aload 5
    //   95: aload 6
    //   97: aload 7
    //   99: aload_2
    //   100: invokevirtual 781	com/google/android/gms/games/broker/TurnBasedAgent:cancelMatchInternal	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)I
    //   103: istore 4
    //   105: iload 4
    //   107: istore_3
    //   108: iload 4
    //   110: iconst_5
    //   111: if_icmpne -34 -> 77
    //   114: aload_1
    //   115: bipush 6
    //   117: aload_2
    //   118: invokestatic 785	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;)V
    //   121: iload 4
    //   123: istore_3
    //   124: goto -47 -> 77
    //   127: astore_1
    //   128: iconst_1
    //   129: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   132: dup
    //   133: iconst_0
    //   134: aload_0
    //   135: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   138: aastore
    //   139: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   142: aload_1
    //   143: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	this	DataBroker
    //   0	144	1	paramGamesClientContext	GamesClientContext
    //   0	144	2	paramString	String
    //   76	48	3	i	int
    //   103	19	4	j	int
    //   25	69	5	localTurnBasedAgent	TurnBasedAgent
    //   31	65	6	localContext	Context
    //   37	61	7	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   14	73	127	finally
    //   93	105	127	finally
    //   114	121	127	finally
  }
  
  /* Error */
  public final boolean checkRevision(Context paramContext, ClientContext paramClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 789	com/google/android/gms/games/broker/RevisionAgent:checkRevision	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)Z
    //   23: istore_3
    //   24: iconst_1
    //   25: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   28: dup
    //   29: iconst_0
    //   30: aload_0
    //   31: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   34: aastore
    //   35: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   38: iload_3
    //   39: ireturn
    //   40: astore_1
    //   41: iconst_1
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   51: aastore
    //   52: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	DataBroker
    //   0	57	1	paramContext	Context
    //   0	57	2	paramClientContext	ClientContext
    //   23	16	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	24	40	finally
  }
  
  /* Error */
  public final DataHolder claimMilestone(GamesClientContext paramGamesClientContext, String paramString1, String paramString2)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   25: aload_1
    //   26: invokevirtual 720	com/google/android/gms/games/broker/EventAgent:submitPendingEventsForGame	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   29: istore 4
    //   31: iload 4
    //   33: ifne +37 -> 70
    //   36: aload_0
    //   37: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   40: aload_1
    //   41: aload_2
    //   42: aload_3
    //   43: invokevirtual 793	com/google/android/gms/games/broker/QuestAgent:claimMilestone	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   46: astore_1
    //   47: iconst_2
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: aload_0
    //   61: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   64: aastore
    //   65: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   68: aload_1
    //   69: areturn
    //   70: iload 4
    //   72: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   75: astore_1
    //   76: goto -29 -> 47
    //   79: astore_1
    //   80: iconst_2
    //   81: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   84: dup
    //   85: iconst_0
    //   86: aload_0
    //   87: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   90: aastore
    //   91: dup
    //   92: iconst_1
    //   93: aload_0
    //   94: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   97: aastore
    //   98: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   101: aload_1
    //   102: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	DataBroker
    //   0	103	1	paramGamesClientContext	GamesClientContext
    //   0	103	2	paramString1	String
    //   0	103	3	paramString2	String
    //   29	42	4	i	int
    // Exception table:
    //   from	to	target	type
    //   21	31	79	finally
    //   36	47	79	finally
    //   70	76	79	finally
  }
  
  public final void clearDataForLocaleChange(Context paramContext)
  {
    Object localObject = getClientContextsForAllDatastores(paramContext);
    super.acquireLockOnAllChildren();
    try
    {
      clearTransientData_locked();
      localObject = ((HashSet)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Uri localUri = GamesContractInternal.getClearLocaleDataContentUri((ClientContext)((Iterator)localObject).next());
        paramContext.getContentResolver().delete(localUri, null, null);
      }
    }
    finally
    {
      super.releaseLockOnAllChildren();
    }
  }
  
  public final void clearPendingOps(Context paramContext, String paramString)
  {
    Iterator localIterator = getClientContextsForAllDatastores(paramContext).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ClientContext)localIterator.next();
      super.acquireLockOnAllChildren();
      try
      {
        localObject = GamesContractInternal.ClientContexts.getContentUri((ClientContext)localObject);
        paramContext.getContentResolver().delete((Uri)localObject, "package_name=?", new String[] { paramString });
        super.releaseLockOnAllChildren();
      }
      finally
      {
        super.releaseLockOnAllChildren();
      }
    }
  }
  
  /* Error */
  public final void clearRevisionCheck(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: invokestatic 830	com/google/android/gms/games/broker/RevisionAgent:clearRevisionCheck	(Landroid/content/Context;)V
    //   18: iconst_1
    //   19: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   22: dup
    //   23: iconst_0
    //   24: aload_0
    //   25: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   28: aastore
    //   29: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   32: return
    //   33: astore_1
    //   34: iconst_1
    //   35: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   38: dup
    //   39: iconst_0
    //   40: aload_0
    //   41: getfield 248	com/google/android/gms/games/broker/DataBroker:mRevisionAgent	Lcom/google/android/gms/games/broker/RevisionAgent;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	DataBroker
    //   0	50	1	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   14	18	33	finally
  }
  
  public final void clearTransientData()
  {
    super.acquireLockOnAllChildren();
    try
    {
      clearTransientData_locked();
      return;
    }
    finally
    {
      super.releaseLockOnAllChildren();
    }
  }
  
  /* Error */
  public final DataHolder commitSnapshot(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient, String paramString, com.google.android.gms.games.snapshot.SnapshotMetadataChange paramSnapshotMetadataChange, com.google.android.gms.drive.DriveContents paramDriveContents)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore_1
    //   24: aload_1
    //   25: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   28: ifnonnull +24 -> 52
    //   31: iconst_2
    //   32: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: iconst_1
    //   37: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: areturn
    //   52: aload_0
    //   53: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   56: aload_1
    //   57: aload_2
    //   58: aload_3
    //   59: aload 4
    //   61: iconst_2
    //   62: aload 5
    //   64: invokevirtual 838	com/google/android/gms/games/broker/SnapshotAgent:commitSnapshot	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;Ljava/lang/String;Lcom/google/android/gms/games/snapshot/SnapshotMetadataChange;ILcom/google/android/gms/drive/DriveContents;)Lcom/google/android/gms/common/data/DataHolder;
    //   67: astore_1
    //   68: goto -32 -> 36
    //   71: astore_1
    //   72: iconst_1
    //   73: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   76: dup
    //   77: iconst_0
    //   78: aload_0
    //   79: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   82: aastore
    //   83: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	DataBroker
    //   0	88	1	paramGamesClientContext	GamesClientContext
    //   0	88	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   0	88	3	paramString	String
    //   0	88	4	paramSnapshotMetadataChange	com.google.android.gms.games.snapshot.SnapshotMetadataChange
    //   0	88	5	paramDriveContents	com.google.android.gms.drive.DriveContents
    // Exception table:
    //   from	to	target	type
    //   19	36	71	finally
    //   52	68	71	finally
  }
  
  /* Error */
  public final DataHolder createRoom(GamesClientContext paramGamesClientContext, int paramInt, String[] paramArrayOfString, Bundle paramBundle, com.google.android.gms.games.internal.ConnectionInfo paramConnectionInfo)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: new 81	java/util/ArrayList
    //   29: dup
    //   30: aload_3
    //   31: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   34: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   37: astore_3
    //   38: aload_0
    //   39: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   42: aload_1
    //   43: iload_2
    //   44: aload_3
    //   45: aload 4
    //   47: aload 5
    //   49: invokevirtual 847	com/google/android/gms/games/broker/RealTimeAgent:createRoom	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/util/ArrayList;Landroid/os/Bundle;Lcom/google/android/gms/games/internal/ConnectionInfo;)Lcom/google/android/gms/common/data/DataHolder;
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: areturn
    //   69: astore_1
    //   70: iconst_1
    //   71: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   74: dup
    //   75: iconst_0
    //   76: aload_0
    //   77: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   80: aastore
    //   81: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   84: aload_1
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	DataBroker
    //   0	86	1	paramGamesClientContext	GamesClientContext
    //   0	86	2	paramInt	int
    //   0	86	3	paramArrayOfString	String[]
    //   0	86	4	paramBundle	Bundle
    //   0	86	5	paramConnectionInfo	com.google.android.gms.games.internal.ConnectionInfo
    // Exception table:
    //   from	to	target	type
    //   19	53	69	finally
  }
  
  /* Error */
  public final DataHolder createTurnBasedMatch$d2d5e18(GamesClientContext paramGamesClientContext, int paramInt, String[] paramArrayOfString, Bundle paramBundle)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: new 81	java/util/ArrayList
    //   29: dup
    //   30: aload_3
    //   31: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   34: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   37: astore_3
    //   38: aload_0
    //   39: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   42: aload_1
    //   43: iload_2
    //   44: aload_3
    //   45: aload 4
    //   47: invokevirtual 853	com/google/android/gms/games/broker/TurnBasedAgent:createMatch$46a5f9fd	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/util/ArrayList;Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: areturn
    //   67: astore_1
    //   68: iconst_1
    //   69: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   72: dup
    //   73: iconst_0
    //   74: aload_0
    //   75: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   78: aastore
    //   79: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	DataBroker
    //   0	84	1	paramGamesClientContext	GamesClientContext
    //   0	84	2	paramInt	int
    //   0	84	3	paramArrayOfString	String[]
    //   0	84	4	paramBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   19	51	67	finally
  }
  
  /* Error */
  public final int declineInvitation(GamesClientContext paramGamesClientContext, String paramString, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   18: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: iload_3
    //   22: ifne +29 -> 51
    //   25: aload_0
    //   26: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   29: aload_1
    //   30: aload_2
    //   31: invokevirtual 857	com/google/android/gms/games/broker/RealTimeAgent:declineInvitation	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)I
    //   34: istore_3
    //   35: iconst_1
    //   36: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   39: dup
    //   40: iconst_0
    //   41: aload_0
    //   42: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   45: aastore
    //   46: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   49: iload_3
    //   50: ireturn
    //   51: aload_0
    //   52: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   55: astore 4
    //   57: aload_1
    //   58: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   61: aload_1
    //   62: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   65: aload_2
    //   66: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   69: ifeq +33 -> 102
    //   72: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   75: new 153	java/lang/StringBuilder
    //   78: dup
    //   79: ldc_w 859
    //   82: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   85: aload_2
    //   86: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: sipush 6507
    //   98: istore_3
    //   99: goto +32 -> 131
    //   102: aload 4
    //   104: aload_1
    //   105: aload_2
    //   106: iconst_1
    //   107: invokevirtual 863	com/google/android/gms/games/broker/TurnBasedAgent:declineInvitationInternal	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Z)I
    //   110: istore_3
    //   111: goto +20 -> 131
    //   114: astore_1
    //   115: iconst_1
    //   116: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   119: dup
    //   120: iconst_0
    //   121: aload_0
    //   122: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   125: aastore
    //   126: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   129: aload_1
    //   130: athrow
    //   131: goto -96 -> 35
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	DataBroker
    //   0	134	1	paramGamesClientContext	GamesClientContext
    //   0	134	2	paramString	String
    //   0	134	3	paramInt	int
    //   55	48	4	localTurnBasedAgent	TurnBasedAgent
    // Exception table:
    //   from	to	target	type
    //   14	21	114	finally
    //   25	35	114	finally
    //   51	95	114	finally
    //   102	111	114	finally
  }
  
  /* Error */
  public final int deleteAllVideos(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   6: ifnull +22 -> 28
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 866	com/google/android/gms/games/broker/DataBroker:getGameForVideoCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/Game;
    //   14: astore_3
    //   15: aload_3
    //   16: ifnonnull +5 -> 21
    //   19: iconst_1
    //   20: ireturn
    //   21: aload_3
    //   22: invokeinterface 869 1 0
    //   27: astore_3
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   38: aastore
    //   39: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: aload_0
    //   43: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   46: aload_1
    //   47: aload_3
    //   48: invokevirtual 872	com/google/android/gms/games/broker/VideoAgent:deleteAllVideos	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Landroid/util/Pair;
    //   51: getfield 493	android/util/Pair:first	Ljava/lang/Object;
    //   54: checkcast 498	java/lang/Integer
    //   57: invokevirtual 875	java/lang/Integer:intValue	()I
    //   60: istore_2
    //   61: iconst_1
    //   62: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   65: dup
    //   66: iconst_0
    //   67: aload_0
    //   68: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: iload_2
    //   76: ireturn
    //   77: astore_1
    //   78: iconst_1
    //   79: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   82: dup
    //   83: iconst_0
    //   84: aload_0
    //   85: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   88: aastore
    //   89: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	DataBroker
    //   0	94	1	paramGamesClientContext	GamesClientContext
    //   60	16	2	i	int
    //   1	47	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   42	61	77	finally
  }
  
  /* Error */
  public final int deleteOneVideo(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 878	com/google/android/gms/games/broker/VideoAgent:deleteOneVideo	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Landroid/util/Pair;
    //   23: getfield 493	android/util/Pair:first	Ljava/lang/Object;
    //   26: checkcast 498	java/lang/Integer
    //   29: invokevirtual 875	java/lang/Integer:intValue	()I
    //   32: istore_3
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   43: aastore
    //   44: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   47: iload_3
    //   48: ireturn
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	DataBroker
    //   0	66	1	paramGamesClientContext	GamesClientContext
    //   0	66	2	paramString	String
    //   32	16	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	33	49	finally
  }
  
  public final int deletePlayer(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mPlayerAgent });
    int i;
    try
    {
      paramGamesClientContext = fetchPlayerIdFromAccountWhenMissing(paramGamesClientContext);
      i = this.mPlayerAgent.deletePlayer(paramGamesClientContext, paramBoolean);
      releaseLocks(new Lockable[] { this.mPlayerAgent });
      if (i == 0)
      {
        invalidateAppContentCache(paramGamesClientContext, null);
        if (paramBoolean)
        {
          paramGamesClientContext = paramGamesClientContext.mContext;
          Object localObject = getClientContextsForAllDatastores(paramGamesClientContext);
          super.acquireLockOnAllChildren();
          try
          {
            clearTransientData_locked();
            localObject = ((HashSet)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              Uri localUri = GamesContractInternal.getClearAllDataContentUri((ClientContext)((Iterator)localObject).next());
              paramGamesClientContext.getContentResolver().delete(localUri, null, null);
              continue;
              paramGamesClientContext = finally;
            }
          }
          finally
          {
            super.releaseLockOnAllChildren();
          }
          super.releaseLockOnAllChildren();
        }
      }
    }
    finally
    {
      tmp136_133[0] = this.mPlayerAgent;
      releaseLocks(tmp136_133);
    }
    return i;
  }
  
  /* Error */
  public final int deleteSnapshot(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient, String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   18: aload_3
    //   19: invokestatic 896	com/google/android/gms/games/provider/GamesContractInternal$Snapshots:getUriForExternalSnapshotId	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Landroid/net/Uri;
    //   22: astore 5
    //   24: aload_1
    //   25: aload_2
    //   26: aload_3
    //   27: invokestatic 900	com/google/android/gms/games/broker/SnapshotAgent:getDriveId	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;Ljava/lang/String;)Lcom/google/android/gms/drive/DriveId;
    //   30: astore 6
    //   32: aload 6
    //   34: ifnonnull +48 -> 82
    //   37: ldc_w 902
    //   40: new 153	java/lang/StringBuilder
    //   43: dup
    //   44: ldc_w 904
    //   47: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   50: aload_3
    //   51: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: sipush 4000
    //   63: istore 4
    //   65: iconst_1
    //   66: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   69: dup
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   75: aastore
    //   76: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   79: iload 4
    //   81: ireturn
    //   82: aload 6
    //   84: invokevirtual 910	com/google/android/gms/drive/DriveId:asDriveFile	()Lcom/google/android/gms/drive/DriveFile;
    //   87: aload_2
    //   88: invokeinterface 915 2 0
    //   93: pop
    //   94: aload_1
    //   95: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   98: invokevirtual 550	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   101: aload 5
    //   103: aconst_null
    //   104: aconst_null
    //   105: invokevirtual 815	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   108: pop
    //   109: aload_1
    //   110: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   113: aload_1
    //   114: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   117: getfield 918	com/google/android/gms/common/internal/ClientContext:mCallingPackageName	Ljava/lang/String;
    //   120: aload_1
    //   121: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   124: aload_1
    //   125: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   128: getfield 922	com/google/android/gms/common/internal/ClientContext:mResolvedAccount	Landroid/accounts/Account;
    //   131: iconst_3
    //   132: aload_3
    //   133: iconst_4
    //   134: ldc2_w 923
    //   137: invokestatic 930	com/google/android/gms/games/logging/GamesPlayLogger:logSnapshotAction	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;ILjava/lang/String;IJ)V
    //   140: iconst_0
    //   141: istore 4
    //   143: goto -78 -> 65
    //   146: astore_1
    //   147: iconst_1
    //   148: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   151: dup
    //   152: iconst_0
    //   153: aload_0
    //   154: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   157: aastore
    //   158: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   161: aload_1
    //   162: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	this	DataBroker
    //   0	163	1	paramGamesClientContext	GamesClientContext
    //   0	163	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   0	163	3	paramString	String
    //   63	79	4	i	int
    //   22	80	5	localUri	Uri
    //   30	53	6	localDriveId	com.google.android.gms.drive.DriveId
    // Exception table:
    //   from	to	target	type
    //   14	32	146	finally
    //   37	60	146	finally
    //   82	140	146	finally
  }
  
  /* Error */
  public final int dismissInvitation(GamesClientContext paramGamesClientContext, String paramString, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iload_3
    //   15: ifne +29 -> 44
    //   18: aload_0
    //   19: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   22: aload_1
    //   23: aload_2
    //   24: invokevirtual 933	com/google/android/gms/games/broker/RealTimeAgent:dismissInvitation	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)I
    //   27: istore_3
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: iload_3
    //   43: ireturn
    //   44: aload_0
    //   45: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   48: astore 5
    //   50: aload_1
    //   51: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   54: astore 6
    //   56: aload_1
    //   57: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   60: astore 7
    //   62: aload 6
    //   64: aload 7
    //   66: aload_2
    //   67: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   70: ifeq +33 -> 103
    //   73: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   76: new 153	java/lang/StringBuilder
    //   79: dup
    //   80: ldc_w 935
    //   83: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   86: aload_2
    //   87: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   96: sipush 6507
    //   99: istore_3
    //   100: goto +56 -> 156
    //   103: aload 5
    //   105: aload_1
    //   106: aload_2
    //   107: aload 7
    //   109: aload_2
    //   110: invokestatic 940	com/google/android/gms/games/provider/GamesContractInternal$Invitations:getUriForExternalInvitationId	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Landroid/net/Uri;
    //   113: invokevirtual 944	com/google/android/gms/games/broker/TurnBasedAgent:dismissInternal	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Landroid/net/Uri;)I
    //   116: istore 4
    //   118: iload 4
    //   120: istore_3
    //   121: iload 4
    //   123: iconst_5
    //   124: if_icmpne +32 -> 156
    //   127: aload_1
    //   128: iconst_1
    //   129: aload_2
    //   130: invokestatic 785	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;)V
    //   133: iload 4
    //   135: istore_3
    //   136: goto +20 -> 156
    //   139: astore_1
    //   140: iconst_1
    //   141: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   144: dup
    //   145: iconst_0
    //   146: aload_0
    //   147: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   150: aastore
    //   151: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   154: aload_1
    //   155: athrow
    //   156: goto -128 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	this	DataBroker
    //   0	159	1	paramGamesClientContext	GamesClientContext
    //   0	159	2	paramString	String
    //   0	159	3	paramInt	int
    //   116	18	4	i	int
    //   48	56	5	localTurnBasedAgent	TurnBasedAgent
    //   54	9	6	localContext	Context
    //   60	48	7	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   18	28	139	finally
    //   44	96	139	finally
    //   103	118	139	finally
    //   127	133	139	finally
  }
  
  /* Error */
  public final int dismissMatch(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   18: astore 5
    //   20: aload_1
    //   21: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   24: astore 6
    //   26: aload_1
    //   27: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   30: astore 7
    //   32: aload 6
    //   34: aload 7
    //   36: aload_2
    //   37: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   40: ifeq +46 -> 86
    //   43: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   46: new 153	java/lang/StringBuilder
    //   49: dup
    //   50: ldc_w 947
    //   53: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   56: aload_2
    //   57: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: sipush 6507
    //   69: istore_3
    //   70: iconst_1
    //   71: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   74: dup
    //   75: iconst_0
    //   76: aload_0
    //   77: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   80: aastore
    //   81: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   84: iload_3
    //   85: ireturn
    //   86: aload 5
    //   88: aload_1
    //   89: aload_2
    //   90: aload 7
    //   92: aload_2
    //   93: invokestatic 952	com/google/android/gms/games/provider/GamesContractInternal$Matches:getUriForExternalMatchId	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Landroid/net/Uri;
    //   96: invokevirtual 944	com/google/android/gms/games/broker/TurnBasedAgent:dismissInternal	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Landroid/net/Uri;)I
    //   99: istore 4
    //   101: iload 4
    //   103: istore_3
    //   104: iload 4
    //   106: iconst_5
    //   107: if_icmpne -37 -> 70
    //   110: aload_1
    //   111: iconst_2
    //   112: aload_2
    //   113: invokestatic 785	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;)V
    //   116: iload 4
    //   118: istore_3
    //   119: goto -49 -> 70
    //   122: astore_1
    //   123: iconst_1
    //   124: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   127: dup
    //   128: iconst_0
    //   129: aload_0
    //   130: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   133: aastore
    //   134: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   137: aload_1
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	DataBroker
    //   0	139	1	paramGamesClientContext	GamesClientContext
    //   0	139	2	paramString	String
    //   69	50	3	i	int
    //   99	18	4	j	int
    //   18	69	5	localTurnBasedAgent	TurnBasedAgent
    //   24	9	6	localContext	Context
    //   30	61	7	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   14	66	122	finally
    //   86	101	122	finally
    //   110	116	122	finally
  }
  
  /* Error */
  public final int dismissPlayerSuggestion(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: aload_1
    //   18: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   21: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   24: astore_1
    //   25: aload_1
    //   26: ifnonnull +24 -> 50
    //   29: iconst_2
    //   30: istore_2
    //   31: iconst_1
    //   32: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   35: dup
    //   36: iconst_0
    //   37: aload_0
    //   38: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   41: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: iload_2
    //   49: ireturn
    //   50: invokestatic 959	com/google/android/gms/games/broker/PlayerAgent:dismissPlayerSuggestion$6321ef0a	()I
    //   53: istore_2
    //   54: goto -23 -> 31
    //   57: astore_1
    //   58: iconst_1
    //   59: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   62: dup
    //   63: iconst_0
    //   64: aload_0
    //   65: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   68: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	DataBroker
    //   0	77	1	paramGamesClientContext	GamesClientContext
    //   30	24	2	i	int
    // Exception table:
    //   from	to	target	type
    //   17	25	57	finally
    //   50	54	57	finally
  }
  
  /* Error */
  public final DataHolder dismissRequests(GamesClientContext paramGamesClientContext, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: new 81	java/util/ArrayList
    //   29: dup
    //   30: aload_2
    //   31: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   34: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   37: astore_2
    //   38: aload_0
    //   39: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   42: aload_1
    //   43: aload_2
    //   44: iconst_1
    //   45: iconst_1
    //   46: invokevirtual 742	com/google/android/gms/games/broker/RequestAgent:updateRequests	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/util/ArrayList;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: areturn
    //   66: astore_1
    //   67: iconst_1
    //   68: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   77: aastore
    //   78: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   81: aload_1
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	DataBroker
    //   0	83	1	paramGamesClientContext	GamesClientContext
    //   0	83	2	paramArrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   19	50	66	finally
  }
  
  /* Error */
  public final void doQuestExpiringNotification(Context paramContext, ClientContext paramClientContext)
  {
    // Byte code:
    //   0: new 964	com/google/android/gms/games/quest/QuestBuffer
    //   3: dup
    //   4: iconst_0
    //   5: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   8: invokespecial 965	com/google/android/gms/games/quest/QuestBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   11: pop
    //   12: iconst_1
    //   13: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   16: dup
    //   17: iconst_0
    //   18: aload_0
    //   19: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_1
    //   27: aload_2
    //   28: invokestatic 969	com/google/android/gms/games/broker/QuestAgent:getQuestsForNotification	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)Lcom/google/android/gms/games/quest/QuestBuffer;
    //   31: astore 5
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   43: aastore
    //   44: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   47: aload 5
    //   49: invokevirtual 970	com/google/android/gms/games/quest/QuestBuffer:getCount	()I
    //   52: istore 4
    //   54: iload 4
    //   56: ifeq +11 -> 67
    //   59: aload_0
    //   60: aload_2
    //   61: invokespecial 972	com/google/android/gms/games/broker/DataBroker:areQuestNotificationsEnabled	(Lcom/google/android/gms/common/internal/ClientContext;)Z
    //   64: ifne +26 -> 90
    //   67: aload 5
    //   69: invokevirtual 975	com/google/android/gms/games/quest/QuestBuffer:release	()V
    //   72: return
    //   73: astore_1
    //   74: iconst_1
    //   75: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   78: dup
    //   79: iconst_0
    //   80: aload_0
    //   81: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   84: aastore
    //   85: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   88: aload_1
    //   89: athrow
    //   90: new 81	java/util/ArrayList
    //   93: dup
    //   94: iload 4
    //   96: invokespecial 978	java/util/ArrayList:<init>	(I)V
    //   99: astore 6
    //   101: aload_1
    //   102: ldc_w 980
    //   105: invokevirtual 984	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   108: checkcast 986	android/app/AlarmManager
    //   111: astore 7
    //   113: iconst_0
    //   114: istore_3
    //   115: iload_3
    //   116: iload 4
    //   118: if_icmpge +164 -> 282
    //   121: aload 5
    //   123: iload_3
    //   124: invokevirtual 987	com/google/android/gms/games/quest/QuestBuffer:get	(I)Ljava/lang/Object;
    //   127: checkcast 989	com/google/android/gms/games/quest/Quest
    //   130: astore 8
    //   132: aload 8
    //   134: invokeinterface 992 1 0
    //   139: astore 9
    //   141: invokestatic 995	com/google/android/gms/games/broker/NotificationAgent:getNewLocalNotificationId	()Ljava/lang/String;
    //   144: astore 10
    //   146: ldc 70
    //   148: ldc_w 997
    //   151: iconst_1
    //   152: anewarray 999	java/lang/Object
    //   155: dup
    //   156: iconst_0
    //   157: aload 9
    //   159: aastore
    //   160: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   163: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload_1
    //   167: invokevirtual 1010	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   170: astore 13
    //   172: aload 13
    //   174: ldc_w 1011
    //   177: invokevirtual 1017	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   180: astore 11
    //   182: aload 13
    //   184: ldc_w 1018
    //   187: invokevirtual 1017	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   190: astore 12
    //   192: aload 13
    //   194: ldc_w 1019
    //   197: iconst_1
    //   198: anewarray 999	java/lang/Object
    //   201: dup
    //   202: iconst_0
    //   203: aload 8
    //   205: invokeinterface 1022 1 0
    //   210: aastore
    //   211: invokevirtual 1025	android/content/res/Resources:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   214: astore 13
    //   216: aload 6
    //   218: new 1027	com/google/android/gms/games/broker/NotificationAgent$NotificationParams
    //   221: dup
    //   222: aload 8
    //   224: invokeinterface 1028 1 0
    //   229: invokeinterface 1031 1 0
    //   234: aload 10
    //   236: aload 9
    //   238: aload 11
    //   240: aload 12
    //   242: aload 13
    //   244: aload 8
    //   246: invokeinterface 1028 1 0
    //   251: invokeinterface 1035 1 0
    //   256: invokespecial 1038	com/google/android/gms/games/broker/NotificationAgent$NotificationParams:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   259: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   262: pop
    //   263: aload 7
    //   265: aload_1
    //   266: aload_2
    //   267: aload 8
    //   269: invokestatic 1042	com/google/android/gms/games/broker/QuestAgent:buildQuestExpiringNotificationIntent	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Lcom/google/android/gms/games/quest/Quest;)Landroid/app/PendingIntent;
    //   272: invokevirtual 1046	android/app/AlarmManager:cancel	(Landroid/app/PendingIntent;)V
    //   275: iload_3
    //   276: iconst_1
    //   277: iadd
    //   278: istore_3
    //   279: goto -164 -> 115
    //   282: aload 5
    //   284: invokevirtual 975	com/google/android/gms/games/quest/QuestBuffer:release	()V
    //   287: aload 6
    //   289: invokevirtual 362	java/util/ArrayList:size	()I
    //   292: ifeq -220 -> 72
    //   295: iconst_1
    //   296: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   299: dup
    //   300: iconst_0
    //   301: aload_0
    //   302: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   305: aastore
    //   306: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   309: aload_1
    //   310: aload_2
    //   311: aload 6
    //   313: invokestatic 1050	com/google/android/gms/games/broker/NotificationAgent:updateNotifications	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/util/ArrayList;)V
    //   316: iconst_1
    //   317: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   320: dup
    //   321: iconst_0
    //   322: aload_0
    //   323: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   326: aastore
    //   327: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   330: return
    //   331: astore_1
    //   332: aload 5
    //   334: invokevirtual 975	com/google/android/gms/games/quest/QuestBuffer:release	()V
    //   337: aload_1
    //   338: athrow
    //   339: astore_1
    //   340: iconst_1
    //   341: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   344: dup
    //   345: iconst_0
    //   346: aload_0
    //   347: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   350: aastore
    //   351: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   354: aload_1
    //   355: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	356	0	this	DataBroker
    //   0	356	1	paramContext	Context
    //   0	356	2	paramClientContext	ClientContext
    //   114	165	3	i	int
    //   52	67	4	j	int
    //   31	302	5	localQuestBuffer	com.google.android.gms.games.quest.QuestBuffer
    //   99	213	6	localArrayList	ArrayList
    //   111	153	7	localAlarmManager	android.app.AlarmManager
    //   130	138	8	localQuest	com.google.android.gms.games.quest.Quest
    //   139	98	9	str1	String
    //   144	91	10	str2	String
    //   180	59	11	str3	String
    //   190	51	12	str4	String
    //   170	73	13	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   26	33	73	finally
    //   101	113	331	finally
    //   121	275	331	finally
    //   309	316	339	finally
  }
  
  public final void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("Dumping DataBroker lock data");
    super.dumpLockInfo(paramPrintWriter, "  ");
  }
  
  /* Error */
  public final DataHolder finishMatch(GamesClientContext paramGamesClientContext, String paramString, byte[] paramArrayOfByte, com.google.android.gms.games.multiplayer.ParticipantResult[] paramArrayOfParticipantResult)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload 4
    //   28: ifnonnull +87 -> 115
    //   31: new 81	java/util/ArrayList
    //   34: dup
    //   35: invokespecial 82	java/util/ArrayList:<init>	()V
    //   38: astore 4
    //   40: aload_0
    //   41: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   44: astore 8
    //   46: aload_1
    //   47: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   50: astore 9
    //   52: aload_1
    //   53: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   56: astore 10
    //   58: aload 9
    //   60: aload 10
    //   62: aload_2
    //   63: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   66: ifeq +83 -> 149
    //   69: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   72: new 153	java/lang/StringBuilder
    //   75: dup
    //   76: ldc_w 1069
    //   79: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   82: aload_2
    //   83: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: sipush 6507
    //   95: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   98: astore_1
    //   99: iconst_1
    //   100: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   103: dup
    //   104: iconst_0
    //   105: aload_0
    //   106: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   109: aastore
    //   110: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   113: aload_1
    //   114: areturn
    //   115: new 81	java/util/ArrayList
    //   118: dup
    //   119: aload 4
    //   121: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   124: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   127: astore 4
    //   129: goto -89 -> 40
    //   132: astore_1
    //   133: iconst_1
    //   134: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   137: dup
    //   138: iconst_0
    //   139: aload_0
    //   140: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   143: aastore
    //   144: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   147: aload_1
    //   148: athrow
    //   149: aload 9
    //   151: aload 10
    //   153: aload_2
    //   154: invokestatic 1072	com/google/android/gms/games/broker/TurnBasedAgent:getLocalVersion	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)I
    //   157: istore 5
    //   159: iload 5
    //   161: iconst_m1
    //   162: if_icmpne +194 -> 356
    //   165: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   168: new 153	java/lang/StringBuilder
    //   171: dup
    //   172: ldc_w 1074
    //   175: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   178: aload_2
    //   179: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: iconst_1
    //   189: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   192: astore_1
    //   193: goto -94 -> 99
    //   196: new 1076	com/google/android/gms/games/server/api/TurnBasedMatchResults
    //   199: dup
    //   200: aload 7
    //   202: iload 5
    //   204: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   207: aload 4
    //   209: invokestatic 1080	com/google/android/gms/games/broker/TurnBasedAgent:convertToWireResults	(Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   212: invokespecial 1083	com/google/android/gms/games/server/api/TurnBasedMatchResults:<init>	(Lcom/google/android/gms/games/server/api/TurnBasedMatchDataRequest;Ljava/lang/Integer;Ljava/util/ArrayList;)V
    //   215: astore 7
    //   217: aload 8
    //   219: aload 9
    //   221: aload 10
    //   223: aload_2
    //   224: aload 7
    //   226: invokevirtual 1087	com/google/android/gms/games/broker/TurnBasedAgent:finishMatchInternal	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Lcom/google/android/gms/games/server/api/TurnBasedMatchResults;)Landroid/util/Pair;
    //   229: astore 11
    //   231: aload 11
    //   233: getfield 493	android/util/Pair:first	Ljava/lang/Object;
    //   236: checkcast 498	java/lang/Integer
    //   239: invokevirtual 875	java/lang/Integer:intValue	()I
    //   242: istore 6
    //   244: aload 11
    //   246: getfield 530	android/util/Pair:second	Ljava/lang/Object;
    //   249: checkcast 1089	com/google/android/gms/games/server/api/TurnBasedMatch
    //   252: astore 11
    //   254: aload 11
    //   256: ifnull +33 -> 289
    //   259: aload 8
    //   261: aload_1
    //   262: aload 11
    //   264: iload 6
    //   266: invokevirtual 1093	com/google/android/gms/games/broker/TurnBasedAgent:storeAndReturnMatch	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/TurnBasedMatch;I)Lcom/google/android/gms/common/data/DataHolder;
    //   269: astore_1
    //   270: goto -171 -> 99
    //   273: new 1095	com/google/android/gms/games/server/api/TurnBasedMatchDataRequest
    //   276: dup
    //   277: aload_3
    //   278: invokestatic 1101	com/google/android/gms/common/util/Base64Utils:encodeUrlSafe	([B)Ljava/lang/String;
    //   281: invokespecial 1102	com/google/android/gms/games/server/api/TurnBasedMatchDataRequest:<init>	(Ljava/lang/String;)V
    //   284: astore 7
    //   286: goto -90 -> 196
    //   289: iload 6
    //   291: sipush 6503
    //   294: if_icmpne +16 -> 310
    //   297: aload 8
    //   299: aload_1
    //   300: aload_2
    //   301: iload 6
    //   303: invokevirtual 1106	com/google/android/gms/games/broker/TurnBasedAgent:getConflictMatch	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   306: astore_1
    //   307: goto -208 -> 99
    //   310: iload 6
    //   312: iconst_5
    //   313: if_icmpeq +12 -> 325
    //   316: iload 6
    //   318: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   321: astore_1
    //   322: goto -223 -> 99
    //   325: aload_1
    //   326: iconst_4
    //   327: aload_2
    //   328: aconst_null
    //   329: iconst_0
    //   330: iload 5
    //   332: aload 7
    //   334: invokestatic 1109	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;Ljava/lang/String;ZILcom/google/android/gms/games/server/api/TurnBasedMatchResults;)V
    //   337: aload 9
    //   339: aload 10
    //   341: aload_2
    //   342: aconst_null
    //   343: aload_3
    //   344: iload 5
    //   346: iconst_1
    //   347: aload 4
    //   349: invokestatic 1113	com/google/android/gms/games/broker/TurnBasedAgent:updateLocalMatchStateForPendingOp	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Ljava/lang/String;[BIZLjava/util/ArrayList;)Lcom/google/android/gms/common/data/DataHolder;
    //   352: astore_1
    //   353: goto -254 -> 99
    //   356: aload_3
    //   357: ifnonnull -84 -> 273
    //   360: aconst_null
    //   361: astore 7
    //   363: goto -167 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	366	0	this	DataBroker
    //   0	366	1	paramGamesClientContext	GamesClientContext
    //   0	366	2	paramString	String
    //   0	366	3	paramArrayOfByte	byte[]
    //   0	366	4	paramArrayOfParticipantResult	com.google.android.gms.games.multiplayer.ParticipantResult[]
    //   157	188	5	i	int
    //   242	75	6	j	int
    //   200	162	7	localObject1	Object
    //   44	254	8	localTurnBasedAgent	TurnBasedAgent
    //   50	288	9	localContext	Context
    //   56	284	10	localClientContext	ClientContext
    //   229	34	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   19	26	132	finally
    //   31	40	132	finally
    //   40	99	132	finally
    //   115	129	132	finally
    //   149	159	132	finally
    //   165	193	132	finally
    //   196	254	132	finally
    //   259	270	132	finally
    //   273	286	132	finally
    //   297	307	132	finally
    //   316	322	132	finally
    //   325	353	132	finally
  }
  
  public final void forceResolveGames(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mGameAgent });
    GameAgent localGameAgent;
    Object localObject1;
    Context localContext;
    Object localObject3;
    ContentResolver localContentResolver;
    Object localObject2;
    try
    {
      localGameAgent = this.mGameAgent;
      localObject1 = paramGamesSyncResult.syncResult;
      localContext = paramGamesClientContext.mContext;
      paramGamesClientContext = paramGamesClientContext.mClientContext;
      localObject3 = new ArrayList();
      localContentResolver = localContext.getContentResolver();
      localObject2 = localContentResolver.query(GamesContractInternal.Games.getContentUri(paramGamesClientContext), GameAgent.VersionQuery.PROJECTION, "metadata_version<0", null, null);
    }
    finally
    {
      try
      {
        while (((Cursor)localObject2).moveToNext()) {
          ((ArrayList)localObject3).add(((Cursor)localObject2).getString(1));
        }
      }
      finally
      {
        ((Cursor)localObject2).close();
      }
      tmp114_111[0] = this.mGameAgent;
      releaseLocks(tmp114_111);
    }
    ((Cursor)localObject2).close();
    if (((ArrayList)localObject3).size() != 0)
    {
      localObject1 = localGameAgent.fetchFirstPartyApplications(localContext, paramGamesClientContext, (SyncResult)localObject1, (ArrayList)localObject3);
      localObject2 = new ArrayList();
      int j = ((GameAgent.ApplicationList)localObject1).applications.size();
      int i = 0;
      while (i < j)
      {
        localObject3 = (FirstPartyApplication)((GameAgent.ApplicationList)localObject1).applications.get(i);
        localGameAgent.addGameOps$2e0f2217(localContext, paramGamesClientContext, (FirstPartyApplication)localObject3, ((FirstPartyApplication)localObject3).getGamesData(), ((FirstPartyApplication)localObject3).getMarketData(), true, true, (ArrayList)localObject2);
        i += 1;
      }
      j = ((GameAgent.ApplicationList)localObject1).invalidIds.size();
      i = 0;
      while (i < j)
      {
        ((ArrayList)localObject2).add(ContentProviderOperation.newDelete(GamesContractInternal.Games.getUriForExternalGameId(paramGamesClientContext, ((InvalidId)((GameAgent.ApplicationList)localObject1).invalidIds.get(i)).getId())).build());
        i += 1;
      }
      if (((ArrayList)localObject2).size() > 0) {
        Agents.applyContentOperations(localContentResolver, (ArrayList)localObject2, "GameAgent");
      }
    }
    paramGamesSyncResult.addOp(19);
    releaseLocks(new Lockable[] { this.mGameAgent });
  }
  
  /* Error */
  public final DataHolder getAchievements(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   23: aload_1
    //   24: invokevirtual 1203	com/google/android/gms/games/broker/AchievementAgent:fetchAchievements	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   27: astore_1
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   55: aastore
    //   56: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	DataBroker
    //   0	61	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	28	44	finally
  }
  
  /* Error */
  public final DataHolder getAchievementsInternal(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: iconst_1
    //   22: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   25: pop
    //   26: aload_0
    //   27: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   30: aload_1
    //   31: iconst_0
    //   32: invokevirtual 443	com/google/android/gms/games/broker/PlayerAgent:fetchPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   35: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   38: aload_0
    //   39: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   42: astore_2
    //   43: aload_1
    //   44: getfield 1207	com/google/android/gms/games/broker/GamesClientContext:mIsFirstParty	Z
    //   47: ldc_w 1209
    //   50: invokestatic 1215	com/google/android/gms/common/internal/Preconditions:checkArgument	(ZLjava/lang/Object;)V
    //   53: aload_2
    //   54: aload_1
    //   55: invokevirtual 1203	com/google/android/gms/games/broker/AchievementAgent:fetchAchievements	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   58: astore_1
    //   59: iconst_2
    //   60: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   63: dup
    //   64: iconst_0
    //   65: aload_0
    //   66: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_0
    //   73: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   76: aastore
    //   77: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   80: aload_1
    //   81: areturn
    //   82: astore_1
    //   83: iconst_2
    //   84: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   87: dup
    //   88: iconst_0
    //   89: aload_0
    //   90: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   93: aastore
    //   94: dup
    //   95: iconst_1
    //   96: aload_0
    //   97: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   100: aastore
    //   101: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   104: aload_1
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	DataBroker
    //   0	106	1	paramGamesClientContext	GamesClientContext
    //   42	12	2	localAchievementAgent	AchievementAgent
    // Exception table:
    //   from	to	target	type
    //   26	59	82	finally
  }
  
  /* Error */
  public final DataHolder getAdditionalScores(GamesClientContext paramGamesClientContext, com.google.android.gms.games.leaderboard.LeaderboardScoreBufferHeader paramLeaderboardScoreBufferHeader, int paramInt1, int paramInt2)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   23: astore 12
    //   25: aload_2
    //   26: invokevirtual 1222	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:getExternalLeaderboardId	()Ljava/lang/String;
    //   29: astore 13
    //   31: aload_2
    //   32: getfield 1226	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:mBundle	Landroid/os/Bundle;
    //   35: ldc_w 1228
    //   38: invokevirtual 1234	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   41: istore 6
    //   43: aload_2
    //   44: invokevirtual 1237	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:getLeaderboardCollection	()I
    //   47: istore 5
    //   49: aload_2
    //   50: getfield 1226	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:mBundle	Landroid/os/Bundle;
    //   53: ldc_w 1239
    //   56: invokevirtual 1234	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   59: istore 7
    //   61: aload 12
    //   63: aload_1
    //   64: aload 13
    //   66: iload 6
    //   68: iload 5
    //   70: invokevirtual 1243	com/google/android/gms/games/broker/LeaderboardAgent:resolveInstanceId	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;II)J
    //   73: lstore 8
    //   75: lload 8
    //   77: ldc2_w 923
    //   80: lcmp
    //   81: ifne +76 -> 157
    //   84: ldc_w 1245
    //   87: new 153	java/lang/StringBuilder
    //   90: dup
    //   91: ldc_w 1247
    //   94: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   97: aload 13
    //   99: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: ldc_w 1249
    //   105: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: iload 5
    //   110: invokestatic 1254	com/google/android/gms/games/internal/constants/LeaderboardCollection:fromInt	(I)Ljava/lang/String;
    //   113: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: ldc_w 1256
    //   119: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: iload 6
    //   124: invokestatic 1259	com/google/android/gms/games/internal/constants/TimeSpan:fromInt	(I)Ljava/lang/String;
    //   127: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: iconst_4
    //   137: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   140: astore_1
    //   141: iconst_1
    //   142: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   145: dup
    //   146: iconst_0
    //   147: aload_0
    //   148: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   151: aastore
    //   152: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   155: aload_1
    //   156: areturn
    //   157: invokestatic 519	com/google/android/gms/common/util/DefaultClock:getInstance	()Lcom/google/android/gms/common/util/Clock;
    //   160: invokeinterface 525 1 0
    //   165: lstore 10
    //   167: new 1261	com/google/android/gms/games/cache/ScoreCacheKey
    //   170: dup
    //   171: lload 8
    //   173: iload 7
    //   175: invokespecial 1264	com/google/android/gms/games/cache/ScoreCacheKey:<init>	(JI)V
    //   178: astore 14
    //   180: aload 12
    //   182: getfield 1268	com/google/android/gms/games/broker/LeaderboardAgent:mScoreCache	Lcom/google/android/gms/games/cache/LeaderboardScoreCache;
    //   185: aload 14
    //   187: lload 10
    //   189: invokevirtual 1274	com/google/android/gms/games/cache/LeaderboardScoreCache:hasData	(Ljava/lang/Object;J)Z
    //   192: ifne +25 -> 217
    //   195: aload 12
    //   197: aload_1
    //   198: invokevirtual 1277	com/google/android/gms/games/broker/GamesClientContext:getWithForceReload$528fa945	()Lcom/google/android/gms/games/broker/GamesClientContext;
    //   201: aload 13
    //   203: iload 6
    //   205: iload 5
    //   207: iload_3
    //   208: iload 7
    //   210: invokevirtual 1281	com/google/android/gms/games/broker/LeaderboardAgent:getRootPage	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IIII)Lcom/google/android/gms/common/data/DataHolder;
    //   213: astore_1
    //   214: goto -73 -> 141
    //   217: aload 12
    //   219: getfield 1268	com/google/android/gms/games/broker/LeaderboardAgent:mScoreCache	Lcom/google/android/gms/games/cache/LeaderboardScoreCache;
    //   222: astore_2
    //   223: iload 4
    //   225: tableswitch	default:+207->432, 0:+66->291, 1:+181->406
    //   248: new 1283	java/lang/IllegalStateException
    //   251: dup
    //   252: new 153	java/lang/StringBuilder
    //   255: dup
    //   256: ldc_w 1285
    //   259: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   262: iload 4
    //   264: invokevirtual 666	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   267: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   270: invokespecial 1286	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   273: athrow
    //   274: astore_1
    //   275: iconst_1
    //   276: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   279: dup
    //   280: iconst_0
    //   281: aload_0
    //   282: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   285: aastore
    //   286: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   289: aload_1
    //   290: athrow
    //   291: aload_2
    //   292: aload 14
    //   294: lload 10
    //   296: invokevirtual 1292	com/google/android/gms/games/cache/TransientDataHolderCache:getNextPageToken	(Ljava/lang/Object;J)Ljava/lang/String;
    //   299: astore_2
    //   300: aload_2
    //   301: ifnull +134 -> 435
    //   304: aload 12
    //   306: aload_1
    //   307: aload 13
    //   309: iload 6
    //   311: iload 5
    //   313: iload_3
    //   314: iload 7
    //   316: aload_2
    //   317: aload_1
    //   318: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   321: invokestatic 1296	com/google/android/gms/games/broker/Agents:getLocaleString	(Landroid/content/Context;)Ljava/lang/String;
    //   324: invokevirtual 1300	com/google/android/gms/games/broker/LeaderboardAgent:fetchScoresFromNetwork	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/games/server/api/LeaderboardScores;
    //   327: astore_2
    //   328: aload_2
    //   329: ifnull +89 -> 418
    //   332: aload 12
    //   334: aload_1
    //   335: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   338: aload_1
    //   339: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   342: lload 8
    //   344: iload 7
    //   346: aload_2
    //   347: iload 4
    //   349: invokevirtual 1304	com/google/android/gms/games/broker/LeaderboardAgent:loadCachePage	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;JILcom/google/android/gms/games/server/api/LeaderboardScores;I)V
    //   352: goto +83 -> 435
    //   355: invokestatic 1308	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:builder	()Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   358: aload_1
    //   359: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   362: invokevirtual 1314	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:withGameId	(Ljava/lang/String;)Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   365: aload 13
    //   367: invokevirtual 1317	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:withLeaderboardId	(Ljava/lang/String;)Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   370: iload 6
    //   372: invokevirtual 1321	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:withTimeSpan	(I)Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   375: iload_3
    //   376: invokevirtual 1324	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:withLeaderboardCollection	(I)Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   379: iload 7
    //   381: invokevirtual 1327	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:withPageType	(I)Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder;
    //   384: invokevirtual 1330	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader$Builder:build	()Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader;
    //   387: astore_1
    //   388: aload 12
    //   390: getfield 1268	com/google/android/gms/games/broker/LeaderboardAgent:mScoreCache	Lcom/google/android/gms/games/cache/LeaderboardScoreCache;
    //   393: aload 14
    //   395: aload_1
    //   396: getfield 1226	com/google/android/gms/games/leaderboard/LeaderboardScoreBufferHeader:mBundle	Landroid/os/Bundle;
    //   399: invokevirtual 1334	com/google/android/gms/games/cache/LeaderboardScoreCache:getData	(Ljava/lang/Object;Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   402: astore_1
    //   403: goto -262 -> 141
    //   406: aload_2
    //   407: aload 14
    //   409: lload 10
    //   411: invokevirtual 1337	com/google/android/gms/games/cache/TransientDataHolderCache:getPrevPageToken	(Ljava/lang/Object;J)Ljava/lang/String;
    //   414: astore_2
    //   415: goto -115 -> 300
    //   418: aload 12
    //   420: getfield 1268	com/google/android/gms/games/broker/LeaderboardAgent:mScoreCache	Lcom/google/android/gms/games/cache/LeaderboardScoreCache;
    //   423: aload 14
    //   425: iconst_3
    //   426: invokevirtual 1341	com/google/android/gms/games/cache/LeaderboardScoreCache:setStatusCode	(Ljava/lang/Object;I)V
    //   429: goto +6 -> 435
    //   432: goto -184 -> 248
    //   435: iload 5
    //   437: istore_3
    //   438: iload 5
    //   440: iconst_2
    //   441: if_icmpne -86 -> 355
    //   444: iconst_1
    //   445: istore_3
    //   446: goto -91 -> 355
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	449	0	this	DataBroker
    //   0	449	1	paramGamesClientContext	GamesClientContext
    //   0	449	2	paramLeaderboardScoreBufferHeader	com.google.android.gms.games.leaderboard.LeaderboardScoreBufferHeader
    //   0	449	3	paramInt1	int
    //   0	449	4	paramInt2	int
    //   47	395	5	i	int
    //   41	330	6	j	int
    //   59	321	7	k	int
    //   73	270	8	l1	long
    //   165	245	10	l2	long
    //   23	396	12	localLeaderboardAgent	LeaderboardAgent
    //   29	337	13	str	String
    //   178	246	14	localScoreCacheKey	com.google.android.gms.games.cache.ScoreCacheKey
    // Exception table:
    //   from	to	target	type
    //   19	75	274	finally
    //   84	141	274	finally
    //   157	214	274	finally
    //   217	223	274	finally
    //   248	274	274	finally
    //   291	300	274	finally
    //   304	328	274	finally
    //   332	352	274	finally
    //   355	403	274	finally
    //   406	415	274	finally
    //   418	429	274	finally
  }
  
  /* Error */
  public final com.google.android.gms.games.client.ExperimentInfo getAllExperiments(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 231	com/google/android/gms/games/broker/DataBroker:mPlayCommonAgent	Lcom/google/android/gms/games/broker/PlayCommonAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: new 1345	com/google/android/gms/games/client/ExperimentInfo$Builder
    //   17: dup
    //   18: invokespecial 1346	com/google/android/gms/games/client/ExperimentInfo$Builder:<init>	()V
    //   21: invokevirtual 1349	com/google/android/gms/games/client/ExperimentInfo$Builder:build	()Lcom/google/android/gms/games/client/ExperimentInfo;
    //   24: pop
    //   25: aload_0
    //   26: getfield 231	com/google/android/gms/games/broker/DataBroker:mPlayCommonAgent	Lcom/google/android/gms/games/broker/PlayCommonAgent;
    //   29: astore_3
    //   30: new 1345	com/google/android/gms/games/client/ExperimentInfo$Builder
    //   33: dup
    //   34: invokespecial 1346	com/google/android/gms/games/client/ExperimentInfo$Builder:<init>	()V
    //   37: astore 4
    //   39: aload_1
    //   40: invokestatic 1353	com/google/android/gms/games/util/AccountUtils:getAvailableAccounts	(Landroid/content/Context;)[Landroid/accounts/Account;
    //   43: astore 5
    //   45: iconst_0
    //   46: istore_2
    //   47: iload_2
    //   48: aload 5
    //   50: arraylength
    //   51: if_icmpge +36 -> 87
    //   54: aload_3
    //   55: aload_1
    //   56: aload 5
    //   58: iload_2
    //   59: aaload
    //   60: invokevirtual 1357	com/google/android/gms/games/broker/PlayCommonAgent:refreshExperiments	(Landroid/content/Context;Landroid/accounts/Account;)V
    //   63: aload 4
    //   65: aload 5
    //   67: iload_2
    //   68: aaload
    //   69: aload 5
    //   71: iload_2
    //   72: aaload
    //   73: invokestatic 1363	com/google/android/gms/games/util/GamesExperiments:getExperimentIds	(Landroid/accounts/Account;)Ljava/util/Set;
    //   76: invokevirtual 1367	com/google/android/gms/games/client/ExperimentInfo$Builder:addAccountInfo	(Landroid/accounts/Account;Ljava/util/Set;)Lcom/google/android/gms/games/client/ExperimentInfo$Builder;
    //   79: pop
    //   80: iload_2
    //   81: iconst_1
    //   82: iadd
    //   83: istore_2
    //   84: goto -37 -> 47
    //   87: aload 4
    //   89: invokevirtual 1349	com/google/android/gms/games/client/ExperimentInfo$Builder:build	()Lcom/google/android/gms/games/client/ExperimentInfo;
    //   92: astore_1
    //   93: iconst_1
    //   94: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   97: dup
    //   98: iconst_0
    //   99: aload_0
    //   100: getfield 231	com/google/android/gms/games/broker/DataBroker:mPlayCommonAgent	Lcom/google/android/gms/games/broker/PlayCommonAgent;
    //   103: aastore
    //   104: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   107: aload_1
    //   108: areturn
    //   109: astore_1
    //   110: iconst_1
    //   111: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   114: dup
    //   115: iconst_0
    //   116: aload_0
    //   117: getfield 231	com/google/android/gms/games/broker/DataBroker:mPlayCommonAgent	Lcom/google/android/gms/games/broker/PlayCommonAgent;
    //   120: aastore
    //   121: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   124: aload_1
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	DataBroker
    //   0	126	1	paramContext	Context
    //   46	38	2	i	int
    //   29	26	3	localPlayCommonAgent	PlayCommonAgent
    //   37	51	4	localBuilder	com.google.android.gms.games.client.ExperimentInfo.Builder
    //   43	27	5	arrayOfAccount	android.accounts.Account[]
    // Exception table:
    //   from	to	target	type
    //   25	45	109	finally
    //   47	80	109	finally
    //   87	93	109	finally
  }
  
  public final GamesClientContext getBackgroundGamesContext(Context paramContext, ClientContext paramClientContext)
  {
    Preconditions.checkArgument(paramClientContext.isCurrentContext(), "Must be GmsCore context");
    paramContext = new GamesClientContext.Builder(paramContext, paramClientContext);
    paramContext.mBackground = true;
    paramContext.mForceReload = true;
    return fetchPlayerIdFromAccountWhenMissing(paramContext.build());
  }
  
  /* Error */
  public final Pair<Integer, Bundle> getCaptureState(Context paramContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: astore 8
    //   20: aload_1
    //   21: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   24: ifne +43 -> 67
    //   27: ldc_w 1388
    //   30: ldc_w 1390
    //   33: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   36: new 489	android/util/Pair
    //   39: dup
    //   40: sipush 9001
    //   43: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   46: aconst_null
    //   47: invokespecial 538	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: areturn
    //   67: aload 8
    //   69: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   72: ifnull +158 -> 230
    //   75: aload 8
    //   77: invokevirtual 1397	com/google/android/gms/games/broker/VideoAgent:isRecording	()Z
    //   80: istore 6
    //   82: aload 8
    //   84: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   87: getfield 1403	com/google/android/gms/games/broker/VideoAgent$RecordingSession:mConfiguration	Lcom/google/android/gms/games/video/VideoConfiguration;
    //   90: getfield 1408	com/google/android/gms/games/video/VideoConfiguration:mCaptureMode	I
    //   93: istore_3
    //   94: aload 8
    //   96: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   99: getfield 1403	com/google/android/gms/games/broker/VideoAgent$RecordingSession:mConfiguration	Lcom/google/android/gms/games/video/VideoConfiguration;
    //   102: getfield 1411	com/google/android/gms/games/video/VideoConfiguration:mQualityLevel	I
    //   105: istore_2
    //   106: aload 8
    //   108: getfield 1415	com/google/android/gms/games/broker/VideoAgent:mVideoRecordingOverlay	Lcom/google/android/gms/games/ui/video/ScreenCaptureOverlay;
    //   111: ifnull +107 -> 218
    //   114: iconst_1
    //   115: istore 4
    //   117: aload 8
    //   119: getfield 1419	com/google/android/gms/games/broker/VideoAgent:mCaptureController	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController;
    //   122: getfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   125: ifnull +99 -> 224
    //   128: iconst_1
    //   129: istore 5
    //   131: goto +115 -> 246
    //   134: new 1230	android/os/Bundle
    //   137: dup
    //   138: invokespecial 1426	android/os/Bundle:<init>	()V
    //   141: astore_1
    //   142: aload_1
    //   143: ldc_w 1428
    //   146: iload 6
    //   148: invokevirtual 1432	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   151: aload_1
    //   152: ldc_w 1434
    //   155: iload_3
    //   156: invokevirtual 1438	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   159: aload_1
    //   160: ldc_w 1440
    //   163: iload_2
    //   164: invokevirtual 1438	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   167: aload_1
    //   168: ldc_w 1442
    //   171: iload 5
    //   173: invokevirtual 1432	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   176: aload_1
    //   177: ldc_w 1444
    //   180: iload 4
    //   182: invokevirtual 1432	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   185: new 489	android/util/Pair
    //   188: dup
    //   189: iconst_0
    //   190: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   193: aload_1
    //   194: invokespecial 538	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   197: astore_1
    //   198: goto -147 -> 51
    //   201: astore_1
    //   202: iconst_1
    //   203: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   206: dup
    //   207: iconst_0
    //   208: aload_0
    //   209: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   212: aastore
    //   213: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   216: aload_1
    //   217: athrow
    //   218: iconst_0
    //   219: istore 4
    //   221: goto -104 -> 117
    //   224: iconst_0
    //   225: istore 5
    //   227: goto +19 -> 246
    //   230: iconst_0
    //   231: istore 4
    //   233: iconst_0
    //   234: istore 5
    //   236: iconst_m1
    //   237: istore_2
    //   238: iconst_m1
    //   239: istore_3
    //   240: iconst_0
    //   241: istore 6
    //   243: goto -109 -> 134
    //   246: iload 4
    //   248: istore 7
    //   250: iload 5
    //   252: istore 4
    //   254: iload 7
    //   256: istore 5
    //   258: goto -124 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	261	0	this	DataBroker
    //   0	261	1	paramContext	Context
    //   105	133	2	i	int
    //   93	147	3	j	int
    //   115	138	4	bool1	boolean
    //   129	128	5	bool2	boolean
    //   80	162	6	bool3	boolean
    //   248	7	7	bool4	boolean
    //   18	100	8	localVideoAgent	VideoAgent
    // Exception table:
    //   from	to	target	type
    //   14	51	201	finally
    //   67	114	201	finally
    //   117	128	201	finally
    //   134	198	201	finally
  }
  
  /* Error */
  public final DataHolder getCircledPlayers(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   49: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aload_1
    //   63: iload_2
    //   64: iload_3
    //   65: invokevirtual 1452	com/google/android/gms/games/broker/PlayerAgent:getCircledPlayers	(Lcom/google/android/gms/games/broker/GamesClientContext;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   68: astore_1
    //   69: goto -30 -> 39
    //   72: astore_1
    //   73: iconst_1
    //   74: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   77: dup
    //   78: iconst_0
    //   79: aload_0
    //   80: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   83: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   86: aastore
    //   87: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   90: aload_1
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	DataBroker
    //   0	92	1	paramGamesClientContext	GamesClientContext
    //   0	92	2	paramInt	int
    //   0	92	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   22	39	72	finally
    //   58	69	72	finally
  }
  
  /* Error */
  public final HashSet<ClientContext> getClientContextsForAllDatastores(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 1458	com/google/android/gms/games/GamesSharedPrefs:getDataStoreNames	(Landroid/content/Context;)Ljava/util/ArrayList;
    //   4: astore 4
    //   6: new 802	java/util/HashSet
    //   9: dup
    //   10: invokespecial 1459	java/util/HashSet:<init>	()V
    //   13: astore 5
    //   15: iconst_1
    //   16: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   25: aastore
    //   26: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   29: iconst_0
    //   30: istore_2
    //   31: aload 4
    //   33: invokevirtual 362	java/util/ArrayList:size	()I
    //   36: istore_3
    //   37: iload_2
    //   38: iload_3
    //   39: if_icmpge +42 -> 81
    //   42: aload_1
    //   43: aload 4
    //   45: iload_2
    //   46: invokevirtual 365	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   49: checkcast 129	java/lang/String
    //   52: invokestatic 1463	com/google/android/gms/games/broker/AccountAgent:getAccount	(Landroid/content/Context;Ljava/lang/String;)Landroid/accounts/Account;
    //   55: astore 6
    //   57: aload 6
    //   59: ifnull +15 -> 74
    //   62: aload 5
    //   64: aload_1
    //   65: aload 6
    //   67: invokestatic 1467	com/google/android/gms/games/broker/Agents:buildFirstPartyClientContext	(Landroid/content/Context;Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/ClientContext;
    //   70: invokevirtual 1468	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   73: pop
    //   74: iload_2
    //   75: iconst_1
    //   76: iadd
    //   77: istore_2
    //   78: goto -41 -> 37
    //   81: iconst_1
    //   82: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   85: dup
    //   86: iconst_0
    //   87: aload_0
    //   88: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   91: aastore
    //   92: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   95: aload 5
    //   97: areturn
    //   98: astore_1
    //   99: iconst_1
    //   100: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   103: dup
    //   104: iconst_0
    //   105: aload_0
    //   106: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   109: aastore
    //   110: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   113: aload_1
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	DataBroker
    //   0	115	1	paramContext	Context
    //   30	48	2	i	int
    //   36	4	3	j	int
    //   4	40	4	localArrayList	ArrayList
    //   13	83	5	localHashSet	HashSet
    //   55	11	6	localAccount	android.accounts.Account
    // Exception table:
    //   from	to	target	type
    //   31	37	98	finally
    //   42	57	98	finally
    //   62	74	98	finally
  }
  
  /* Error */
  public final DataHolder getConnectedPlayers$10cce170(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 1473	com/google/android/gms/games/broker/PlayerAgent:mPlayersConnectedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_2
    //   27: aload_2
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   49: getfield 1473	com/google/android/gms/games/broker/PlayerAgent:mPlayersConnectedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: astore_3
    //   63: aload_3
    //   64: getfield 1473	com/google/android/gms/games/broker/PlayerAgent:mPlayersConnectedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   67: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   70: aload_2
    //   71: invokevirtual 1476	com/google/android/gms/games/broker/GamesClientContext:isThirdParty	()Z
    //   74: ifeq +32 -> 106
    //   77: ldc_w 1478
    //   80: astore_1
    //   81: aload_3
    //   82: aload_2
    //   83: aload_2
    //   84: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   87: aload_2
    //   88: getfield 422	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetPlayerId	Ljava/lang/String;
    //   91: aload_1
    //   92: invokestatic 698	com/google/android/gms/games/cache/PlayerCache:getCacheKeyForGameIdCollection	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   95: aload_1
    //   96: bipush 50
    //   98: iconst_0
    //   99: invokevirtual 1482	com/google/android/gms/games/broker/PlayerAgent:loadPlayerData$2fcfbbfc	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   102: astore_1
    //   103: goto -64 -> 39
    //   106: ldc_w 1484
    //   109: astore_1
    //   110: goto -29 -> 81
    //   113: astore_1
    //   114: iconst_1
    //   115: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   118: dup
    //   119: iconst_0
    //   120: aload_0
    //   121: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   124: getfield 1473	com/google/android/gms/games/broker/PlayerAgent:mPlayersConnectedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   127: aastore
    //   128: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	DataBroker
    //   0	133	1	paramGamesClientContext	GamesClientContext
    //   26	62	2	localGamesClientContext	GamesClientContext
    //   62	20	3	localPlayerAgent	PlayerAgent
    // Exception table:
    //   from	to	target	type
    //   22	39	113	finally
    //   58	77	113	finally
    //   81	103	113	finally
  }
  
  /* Error */
  public final String getCurrentPlayerId(Context paramContext, ClientContext paramClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: aload_2
    //   16: invokestatic 388	com/google/android/gms/games/broker/AccountAgent:getExternalPlayerId	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)Ljava/lang/String;
    //   19: astore_1
    //   20: iconst_1
    //   21: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   24: dup
    //   25: iconst_0
    //   26: aload_0
    //   27: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   30: aastore
    //   31: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   34: aload_1
    //   35: areturn
    //   36: astore_1
    //   37: iconst_1
    //   38: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   47: aastore
    //   48: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   51: aload_1
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	DataBroker
    //   0	53	1	paramContext	Context
    //   0	53	2	paramClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   14	20	36	finally
  }
  
  /* Error */
  public final DataHolder getEvents(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   23: aload_1
    //   24: invokevirtual 1489	com/google/android/gms/games/broker/EventAgent:flushAndRefreshIfNeeded	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   27: istore_2
    //   28: aload_1
    //   29: invokestatic 1495	com/google/android/gms/games/provider/GamesContractInternal$EventInstances:getUriForExternalPlayerAndGameId	(Lcom/google/android/gms/games/broker/GamesClientContext;)Landroid/net/Uri;
    //   32: astore_3
    //   33: new 1497	com/google/android/gms/games/broker/Agents$QueryBuilder
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 1500	com/google/android/gms/games/broker/Agents$QueryBuilder:<init>	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   41: aload_3
    //   42: invokevirtual 1504	com/google/android/gms/games/broker/Agents$QueryBuilder:setQuerySpec	(Landroid/net/Uri;)Lcom/google/android/gms/games/broker/Agents$QueryBuilder;
    //   45: astore_1
    //   46: aload_1
    //   47: ldc_w 1506
    //   50: putfield 1509	com/google/android/gms/games/broker/Agents$QueryBuilder:mSortOrder	Ljava/lang/String;
    //   53: aload_1
    //   54: iload_2
    //   55: putfield 1510	com/google/android/gms/games/broker/Agents$QueryBuilder:mStatusCode	I
    //   58: aload_1
    //   59: aconst_null
    //   60: invokevirtual 1513	com/google/android/gms/games/broker/Agents$QueryBuilder:query	(Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   63: astore_1
    //   64: iconst_1
    //   65: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   68: dup
    //   69: iconst_0
    //   70: aload_0
    //   71: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   74: aastore
    //   75: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   78: aload_1
    //   79: areturn
    //   80: astore_1
    //   81: iconst_1
    //   82: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   85: dup
    //   86: iconst_0
    //   87: aload_0
    //   88: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   91: aastore
    //   92: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   95: aload_1
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	DataBroker
    //   0	97	1	paramGamesClientContext	GamesClientContext
    //   27	28	2	i	int
    //   32	10	3	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   19	64	80	finally
  }
  
  /* Error */
  public final DataHolder getEvents(GamesClientContext paramGamesClientContext, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   23: aload_1
    //   24: invokevirtual 1489	com/google/android/gms/games/broker/EventAgent:flushAndRefreshIfNeeded	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   27: istore_3
    //   28: new 1515	com/google/android/gms/common/provider/QuerySpec
    //   31: dup
    //   32: aload_1
    //   33: invokestatic 1495	com/google/android/gms/games/provider/GamesContractInternal$EventInstances:getUriForExternalPlayerAndGameId	(Lcom/google/android/gms/games/broker/GamesClientContext;)Landroid/net/Uri;
    //   36: invokespecial 1518	com/google/android/gms/common/provider/QuerySpec:<init>	(Landroid/net/Uri;)V
    //   39: astore 4
    //   41: aload 4
    //   43: ldc_w 1520
    //   46: aload_2
    //   47: invokevirtual 1524	com/google/android/gms/common/provider/QuerySpec:addWhereIn	(Ljava/lang/String;[Ljava/lang/String;)V
    //   50: new 1497	com/google/android/gms/games/broker/Agents$QueryBuilder
    //   53: dup
    //   54: aload_1
    //   55: invokespecial 1500	com/google/android/gms/games/broker/Agents$QueryBuilder:<init>	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   58: astore_1
    //   59: aload_1
    //   60: aload 4
    //   62: putfield 1528	com/google/android/gms/games/broker/Agents$QueryBuilder:mQuery	Lcom/google/android/gms/common/provider/QuerySpec;
    //   65: aload_1
    //   66: ldc_w 1506
    //   69: putfield 1509	com/google/android/gms/games/broker/Agents$QueryBuilder:mSortOrder	Ljava/lang/String;
    //   72: aload_1
    //   73: iload_3
    //   74: putfield 1510	com/google/android/gms/games/broker/Agents$QueryBuilder:mStatusCode	I
    //   77: aload_1
    //   78: aconst_null
    //   79: invokevirtual 1513	com/google/android/gms/games/broker/Agents$QueryBuilder:query	(Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   82: astore_1
    //   83: iconst_1
    //   84: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   87: dup
    //   88: iconst_0
    //   89: aload_0
    //   90: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   93: aastore
    //   94: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   97: aload_1
    //   98: areturn
    //   99: astore_1
    //   100: iconst_1
    //   101: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   104: dup
    //   105: iconst_0
    //   106: aload_0
    //   107: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   110: aastore
    //   111: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   114: aload_1
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	DataBroker
    //   0	116	1	paramGamesClientContext	GamesClientContext
    //   0	116	2	paramArrayOfString	String[]
    //   27	47	3	i	int
    //   39	22	4	localQuerySpec	QuerySpec
    // Exception table:
    //   from	to	target	type
    //   19	83	99	finally
  }
  
  /* Error */
  public final DataHolder getExtendedGame(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   28: aload_1
    //   29: invokevirtual 1531	com/google/android/gms/games/broker/GameAgent:getExtendedGame	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   32: astore_1
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   43: aastore
    //   44: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	DataBroker
    //   0	66	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	33	49	finally
  }
  
  /* Error */
  public final DataHolder getFirstPartyInvitablePlayers(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   49: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aload_1
    //   63: iload_2
    //   64: iload_3
    //   65: invokevirtual 1452	com/google/android/gms/games/broker/PlayerAgent:getCircledPlayers	(Lcom/google/android/gms/games/broker/GamesClientContext;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   68: astore_1
    //   69: goto -30 -> 39
    //   72: astore_1
    //   73: iconst_1
    //   74: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   77: dup
    //   78: iconst_0
    //   79: aload_0
    //   80: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   83: getfield 1450	com/google/android/gms/games/broker/PlayerAgent:mPlayersCircledLock	Lcom/google/android/gms/games/broker/Lockable;
    //   86: aastore
    //   87: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   90: aload_1
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	DataBroker
    //   0	92	1	paramGamesClientContext	GamesClientContext
    //   0	92	2	paramInt	int
    //   0	92	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   22	39	72	finally
    //   58	69	72	finally
  }
  
  /* Error */
  public final DataHolder getFirstPartyPlayers(GamesClientContext paramGamesClientContext, String paramString, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   4: aload_1
    //   5: aload_2
    //   6: invokevirtual 1538	com/google/android/gms/games/broker/PlayerAgent:getLockableForPlayersCollection	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/games/broker/Lockable;
    //   9: astore 5
    //   11: iconst_1
    //   12: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   15: dup
    //   16: iconst_0
    //   17: aload 5
    //   19: aastore
    //   20: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   23: iconst_1
    //   24: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   27: pop
    //   28: aload_1
    //   29: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   32: astore_1
    //   33: aload_1
    //   34: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   37: ifnonnull +22 -> 59
    //   40: iconst_2
    //   41: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload 5
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: areturn
    //   59: aload_0
    //   60: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   63: aload_1
    //   64: aload_2
    //   65: iload_3
    //   66: iload 4
    //   68: invokevirtual 1540	com/google/android/gms/games/broker/PlayerAgent:getFirstPartyPlayers	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   71: astore_1
    //   72: goto -27 -> 45
    //   75: astore_1
    //   76: iconst_1
    //   77: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   80: dup
    //   81: iconst_0
    //   82: aload 5
    //   84: aastore
    //   85: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   88: aload_1
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	DataBroker
    //   0	90	1	paramGamesClientContext	GamesClientContext
    //   0	90	2	paramString	String
    //   0	90	3	paramInt	int
    //   0	90	4	paramBoolean	boolean
    //   9	74	5	localLockable	Lockable
    // Exception table:
    //   from	to	target	type
    //   28	45	75	finally
    //   59	72	75	finally
  }
  
  public final GamesClientContext getForegroundGamesContext$1ae37e10(Context paramContext, ClientContext paramClientContext, String paramString)
  {
    paramContext = new GamesClientContext.Builder(paramContext, paramClientContext);
    paramContext.mForceReload = true;
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext.mExternalTargetGameId = paramString;
      if (!paramClientContext.isCurrentContext()) {
        paramContext.mExternalOwningGameId = paramString;
      }
    }
    return fetchPlayerIdFromAccountWhenMissing(paramContext.build());
  }
  
  /* Error */
  public final DataHolder getGame(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: invokevirtual 1547	com/google/android/gms/games/broker/GameAgent:getGame	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder getGameInstances(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 1550	com/google/android/gms/games/broker/GameAgent:getGameInstances	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   23: astore_1
    //   24: iconst_1
    //   25: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   28: dup
    //   29: iconst_0
    //   30: aload_0
    //   31: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   34: aastore
    //   35: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   38: aload_1
    //   39: areturn
    //   40: astore_1
    //   41: iconst_1
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   51: aastore
    //   52: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	DataBroker
    //   0	57	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	24	40	finally
  }
  
  /* Error */
  public final int getHeadlessCapturePermission(Context paramContext, ClientContext paramClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: aload_2
    //   16: invokestatic 1554	com/google/android/gms/games/broker/VideoAgent:getHeadlessCapturePermission	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)I
    //   19: istore_3
    //   20: iconst_1
    //   21: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   24: dup
    //   25: iconst_0
    //   26: aload_0
    //   27: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   30: aastore
    //   31: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   34: iload_3
    //   35: ireturn
    //   36: astore_1
    //   37: iconst_1
    //   38: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   47: aastore
    //   48: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   51: aload_1
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	DataBroker
    //   0	53	1	paramContext	Context
    //   0	53	2	paramClientContext	ClientContext
    //   19	16	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	20	36	finally
  }
  
  public final int getInboxCounts(GamesClientContext paramGamesClientContext, Bundle paramBundle)
  {
    GamesClientContext localGamesClientContext = fetchPlayerIdFromAccountWhenMissing(paramGamesClientContext);
    if (localGamesClientContext.mExternalCurrentPlayerId == null) {
      return 1;
    }
    boolean bool1 = false;
    int i = 0;
    int j = this.mInboxCounters.size();
    if (i < j)
    {
      paramGamesClientContext = (InboxCounter)this.mInboxCounters.get(i);
      acquireLocks(new Lockable[] { paramGamesClientContext.getLockable() });
    }
    for (;;)
    {
      try
      {
        String str = paramGamesClientContext.getActivityKey();
        paramBundle.putInt(str, paramGamesClientContext.getInboxCount(localGamesClientContext) + paramBundle.getInt(str, 0));
        boolean bool2 = paramGamesClientContext.hasInboxCountChanged();
        bool1 |= bool2;
        releaseLocks(new Lockable[] { paramGamesClientContext.getLockable() });
        i += 1;
      }
      finally
      {
        releaseLocks(new Lockable[] { paramGamesClientContext.getLockable() });
      }
      i += paramBundle.getInt((String)paramGamesClientContext.next(), 0);
    }
    paramBundle.putInt("inbox_total_count", i);
    paramBundle.putBoolean("inbox_has_new_activity", bool1);
    return 0;
  }
  
  /* Error */
  public final DataHolder getInvitablePlayers(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 1583	com/google/android/gms/games/broker/PlayerAgent:mPlayersVisibleLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore 5
    //   28: aload 5
    //   30: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   33: ifnonnull +27 -> 60
    //   36: iconst_2
    //   37: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   40: astore_1
    //   41: iconst_1
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   51: getfield 1583	com/google/android/gms/games/broker/PlayerAgent:mPlayersVisibleLock	Lcom/google/android/gms/games/broker/Lockable;
    //   54: aastore
    //   55: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   58: aload_1
    //   59: areturn
    //   60: aload_0
    //   61: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   64: astore 6
    //   66: aload 5
    //   68: getfield 1207	com/google/android/gms/games/broker/GamesClientContext:mIsFirstParty	Z
    //   71: ifne +84 -> 155
    //   74: iconst_1
    //   75: istore 4
    //   77: iload 4
    //   79: ldc_w 1585
    //   82: invokestatic 1590	com/google/android/gms/common/internal/Asserts:checkState	(ZLjava/lang/Object;)V
    //   85: aload 6
    //   87: getfield 1583	com/google/android/gms/games/broker/PlayerAgent:mPlayersVisibleLock	Lcom/google/android/gms/games/broker/Lockable;
    //   90: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   93: aload 5
    //   95: invokevirtual 1476	com/google/android/gms/games/broker/GamesClientContext:isThirdParty	()Z
    //   98: ifeq +30 -> 128
    //   101: ldc_w 1592
    //   104: astore_1
    //   105: aload 6
    //   107: aload 5
    //   109: aload 5
    //   111: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   114: aload_1
    //   115: invokestatic 1596	com/google/android/gms/games/cache/PlayerCache:getCacheKeyForCallingPackageCollection	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Ljava/lang/String;
    //   118: aload_1
    //   119: iload_2
    //   120: iload_3
    //   121: invokevirtual 1482	com/google/android/gms/games/broker/PlayerAgent:loadPlayerData$2fcfbbfc	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   124: astore_1
    //   125: goto -84 -> 41
    //   128: ldc_w 1598
    //   131: astore_1
    //   132: goto -27 -> 105
    //   135: astore_1
    //   136: iconst_1
    //   137: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   140: dup
    //   141: iconst_0
    //   142: aload_0
    //   143: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   146: getfield 1583	com/google/android/gms/games/broker/PlayerAgent:mPlayersVisibleLock	Lcom/google/android/gms/games/broker/Lockable;
    //   149: aastore
    //   150: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   153: aload_1
    //   154: athrow
    //   155: iconst_0
    //   156: istore 4
    //   158: goto -81 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	161	0	this	DataBroker
    //   0	161	1	paramGamesClientContext	GamesClientContext
    //   0	161	2	paramInt	int
    //   0	161	3	paramBoolean	boolean
    //   75	82	4	bool	boolean
    //   26	84	5	localGamesClientContext	GamesClientContext
    //   64	42	6	localPlayerAgent	PlayerAgent
    // Exception table:
    //   from	to	target	type
    //   22	41	135	finally
    //   60	74	135	finally
    //   77	101	135	finally
    //   105	125	135	finally
  }
  
  /* Error */
  public final DataHolder getInvitation(GamesClientContext paramGamesClientContext, String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: aload_2
    //   21: invokestatic 1602	com/google/android/gms/games/broker/MultiplayerAgent:getLocalInvitation	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   24: astore_1
    //   25: iconst_1
    //   26: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   29: dup
    //   30: iconst_0
    //   31: aload_0
    //   32: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   35: aastore
    //   36: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   39: aload_1
    //   40: areturn
    //   41: astore_1
    //   42: iconst_1
    //   43: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   46: dup
    //   47: iconst_0
    //   48: aload_0
    //   49: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	DataBroker
    //   0	58	1	paramGamesClientContext	GamesClientContext
    //   0	58	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	25	41	finally
  }
  
  /* Error */
  public final DataHolder getInvitations$7f5f6a5c(GamesClientContext paramGamesClientContext, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: iload_2
    //   21: aload_0
    //   22: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   25: aload_1
    //   26: invokevirtual 1607	com/google/android/gms/games/broker/MultiplayerAgent:syncEntities	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   29: invokestatic 1611	com/google/android/gms/games/broker/MultiplayerAgent:getLocalInvitations	(Lcom/google/android/gms/games/broker/GamesClientContext;II)Lcom/google/android/gms/common/data/DataHolder;
    //   32: astore_1
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   43: aastore
    //   44: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	DataBroker
    //   0	66	1	paramGamesClientContext	GamesClientContext
    //   0	66	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   19	33	49	finally
  }
  
  /* Error */
  public final DataHolder getLeaderboard(GamesClientContext paramGamesClientContext, String paramString, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: iload_3
    //   26: invokevirtual 1615	com/google/android/gms/games/broker/LeaderboardAgent:getLeaderboard	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   29: astore_1
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: aload_1
    //   45: areturn
    //   46: astore_1
    //   47: iconst_1
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   57: aastore
    //   58: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   61: aload_1
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	DataBroker
    //   0	63	1	paramGamesClientContext	GamesClientContext
    //   0	63	2	paramString	String
    //   0	63	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   19	30	46	finally
  }
  
  /* Error */
  public final DataHolder getLeaderboards(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   23: aload_1
    //   24: invokevirtual 1618	com/google/android/gms/games/broker/LeaderboardAgent:getLeaderboards	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   27: astore_1
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   55: aastore
    //   56: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	DataBroker
    //   0	61	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	28	44	finally
  }
  
  /* Error */
  public final DataHolder getLocalInvitations(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: iload_2
    //   21: iload_3
    //   22: invokestatic 1611	com/google/android/gms/games/broker/MultiplayerAgent:getLocalInvitations	(Lcom/google/android/gms/games/broker/GamesClientContext;II)Lcom/google/android/gms/common/data/DataHolder;
    //   25: astore_1
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	DataBroker
    //   0	59	1	paramGamesClientContext	GamesClientContext
    //   0	59	2	paramInt1	int
    //   0	59	3	paramInt2	int
    // Exception table:
    //   from	to	target	type
    //   19	26	42	finally
  }
  
  /* Error */
  public final DataHolder getLocalPlayer(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_2
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: astore_2
    //   5: aload_1
    //   6: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   9: astore_1
    //   10: aload_1
    //   11: invokevirtual 1621	com/google/android/gms/games/broker/GamesClientContext:canResolveCurrentPlayerId	()Z
    //   14: ifne +5 -> 19
    //   17: aload_2
    //   18: areturn
    //   19: iconst_1
    //   20: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   23: dup
    //   24: iconst_0
    //   25: aload_0
    //   26: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   29: aastore
    //   30: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   33: aload_1
    //   34: aload_1
    //   35: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   38: invokestatic 1624	com/google/android/gms/games/broker/PlayerAgent:queryLocalPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   41: astore_1
    //   42: iconst_1
    //   43: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   46: dup
    //   47: iconst_0
    //   48: aload_0
    //   49: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: astore_1
    //   59: iconst_1
    //   60: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   63: dup
    //   64: iconst_0
    //   65: aload_0
    //   66: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   69: aastore
    //   70: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   73: aload_1
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	DataBroker
    //   0	75	1	paramGamesClientContext	GamesClientContext
    //   4	14	2	localDataHolder	DataHolder
    // Exception table:
    //   from	to	target	type
    //   33	42	58	finally
  }
  
  /* Error */
  public final DataHolder getLocalRequestSummaries(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: iload_2
    //   21: iload_3
    //   22: invokestatic 1627	com/google/android/gms/games/broker/RequestAgent:getLocalRequestSummaries	(Lcom/google/android/gms/games/broker/GamesClientContext;II)Lcom/google/android/gms/common/data/DataHolder;
    //   25: astore_1
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	DataBroker
    //   0	59	1	paramGamesClientContext	GamesClientContext
    //   0	59	2	paramInt1	int
    //   0	59	3	paramInt2	int
    // Exception table:
    //   from	to	target	type
    //   19	26	42	finally
  }
  
  /* Error */
  public final DataHolder getLocalRequests(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: iload_2
    //   21: iload_3
    //   22: iload 4
    //   24: iload 5
    //   26: invokestatic 1631	com/google/android/gms/games/broker/RequestAgent:getLocalRequests	(Lcom/google/android/gms/games/broker/GamesClientContext;IIII)Lcom/google/android/gms/common/data/DataHolder;
    //   29: astore_1
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: aload_1
    //   45: areturn
    //   46: astore_1
    //   47: iconst_1
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   57: aastore
    //   58: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   61: aload_1
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	DataBroker
    //   0	63	1	paramGamesClientContext	GamesClientContext
    //   0	63	2	paramInt1	int
    //   0	63	3	paramInt2	int
    //   0	63	4	paramInt3	int
    //   0	63	5	paramInt4	int
    // Exception table:
    //   from	to	target	type
    //   19	30	46	finally
  }
  
  /* Error */
  public final DataHolder getLocalTurnBasedMatches(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: iload_2
    //   21: iload_3
    //   22: invokestatic 1635	com/google/android/gms/games/broker/TurnBasedAgent:getLocalMatches	(Lcom/google/android/gms/games/broker/GamesClientContext;II)Lcom/google/android/gms/common/data/DataHolder;
    //   25: astore_1
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	DataBroker
    //   0	59	1	paramGamesClientContext	GamesClientContext
    //   0	59	2	paramInt1	int
    //   0	59	3	paramInt2	int
    // Exception table:
    //   from	to	target	type
    //   19	26	42	finally
  }
  
  /* Error */
  public final DataHolder getNotifications(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: aload_2
    //   21: iload_3
    //   22: invokestatic 1639	com/google/android/gms/games/broker/NotificationAgent:getNotifications	(Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   25: astore_1
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	DataBroker
    //   0	59	1	paramContext	Context
    //   0	59	2	paramString	String
    //   0	59	3	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   19	26	42	finally
  }
  
  /* Error */
  public final DataHolder getPlayer(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: iconst_0
    //   25: invokevirtual 443	com/google/android/gms/games/broker/PlayerAgent:fetchPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder getPlayer(GamesClientContext paramGamesClientContext, android.accounts.Account paramAccount)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: astore_3
    //   24: aload_2
    //   25: invokestatic 1647	com/google/android/gms/games/internal/GamesIntents:generateAccountKey	(Landroid/accounts/Account;)Ljava/lang/String;
    //   28: astore_2
    //   29: aload_1
    //   30: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   33: aload_2
    //   34: invokestatic 1652	com/google/android/gms/games/provider/GamesContractInternal$AccountMetadata:getContentUri	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: ldc_w 703
    //   40: invokestatic 1656	com/google/android/gms/games/broker/Agents:queryString$510a9928	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;)Ljava/lang/String;
    //   43: astore_2
    //   44: aload_2
    //   45: ifnonnull +24 -> 69
    //   48: iconst_1
    //   49: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: areturn
    //   69: aload_1
    //   70: invokevirtual 392	com/google/android/gms/games/broker/GamesClientContext:getBuilder	()Lcom/google/android/gms/games/broker/GamesClientContext$Builder;
    //   73: astore_1
    //   74: aload_1
    //   75: aload_2
    //   76: putfield 432	com/google/android/gms/games/broker/GamesClientContext$Builder:mExternalTargetPlayerId	Ljava/lang/String;
    //   79: aload_3
    //   80: aload_1
    //   81: invokevirtual 399	com/google/android/gms/games/broker/GamesClientContext$Builder:build	()Lcom/google/android/gms/games/broker/GamesClientContext;
    //   84: iconst_0
    //   85: invokevirtual 443	com/google/android/gms/games/broker/PlayerAgent:fetchPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   88: astore_1
    //   89: goto -36 -> 53
    //   92: astore_1
    //   93: iconst_1
    //   94: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   97: dup
    //   98: iconst_0
    //   99: aload_0
    //   100: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   103: aastore
    //   104: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   107: aload_1
    //   108: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	DataBroker
    //   0	109	1	paramGamesClientContext	GamesClientContext
    //   0	109	2	paramAccount	android.accounts.Account
    //   23	57	3	localPlayerAgent	PlayerAgent
    // Exception table:
    //   from	to	target	type
    //   19	44	92	finally
    //   48	53	92	finally
    //   69	89	92	finally
  }
  
  /* Error */
  public final DataHolder getPlayerCenteredPage(GamesClientContext paramGamesClientContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: iload_3
    //   26: iload 4
    //   28: iload 5
    //   30: iconst_1
    //   31: invokevirtual 1281	com/google/android/gms/games/broker/LeaderboardAgent:getRootPage	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IIII)Lcom/google/android/gms/common/data/DataHolder;
    //   34: astore_1
    //   35: iconst_1
    //   36: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   39: dup
    //   40: iconst_0
    //   41: aload_0
    //   42: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   45: aastore
    //   46: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   49: aload_1
    //   50: areturn
    //   51: astore_1
    //   52: iconst_1
    //   53: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   56: dup
    //   57: iconst_0
    //   58: aload_0
    //   59: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   62: aastore
    //   63: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	DataBroker
    //   0	68	1	paramGamesClientContext	GamesClientContext
    //   0	68	2	paramString	String
    //   0	68	3	paramInt1	int
    //   0	68	4	paramInt2	int
    //   0	68	5	paramInt3	int
    // Exception table:
    //   from	to	target	type
    //   19	35	51	finally
  }
  
  /* Error */
  public final DataHolder getPlayerScore(GamesClientContext paramGamesClientContext, String paramString, int paramInt1, int paramInt2)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   23: ifnull +32 -> 55
    //   26: aload_0
    //   27: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: iload_3
    //   33: iload 4
    //   35: invokevirtual 1662	com/google/android/gms/games/broker/LeaderboardAgent:getPlayerScore	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;II)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   49: aastore
    //   50: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   53: aload_1
    //   54: areturn
    //   55: iconst_2
    //   56: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   59: astore_1
    //   60: goto -21 -> 39
    //   63: astore_1
    //   64: iconst_1
    //   65: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   68: dup
    //   69: iconst_0
    //   70: aload_0
    //   71: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   74: aastore
    //   75: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   78: aload_1
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	DataBroker
    //   0	80	1	paramGamesClientContext	GamesClientContext
    //   0	80	2	paramString	String
    //   0	80	3	paramInt1	int
    //   0	80	4	paramInt2	int
    // Exception table:
    //   from	to	target	type
    //   19	39	63	finally
    //   55	60	63	finally
  }
  
  /* Error */
  public final DataHolder getPlayers(GamesClientContext paramGamesClientContext, String paramString, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   4: aload_1
    //   5: aload_2
    //   6: invokevirtual 1538	com/google/android/gms/games/broker/PlayerAgent:getLockableForPlayersCollection	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/games/broker/Lockable;
    //   9: astore 6
    //   11: iconst_1
    //   12: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   15: dup
    //   16: iconst_0
    //   17: aload 6
    //   19: aastore
    //   20: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   23: iconst_1
    //   24: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   27: pop
    //   28: aload_1
    //   29: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   32: astore_1
    //   33: aload_1
    //   34: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   37: ifnonnull +22 -> 59
    //   40: iconst_2
    //   41: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload 6
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: areturn
    //   59: aload_0
    //   60: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   63: astore 7
    //   65: aload_1
    //   66: getfield 1207	com/google/android/gms/games/broker/GamesClientContext:mIsFirstParty	Z
    //   69: ifne +56 -> 125
    //   72: iconst_1
    //   73: istore 5
    //   75: iload 5
    //   77: ldc_w 1665
    //   80: invokestatic 1590	com/google/android/gms/common/internal/Asserts:checkState	(ZLjava/lang/Object;)V
    //   83: aload 7
    //   85: aload_1
    //   86: aload_2
    //   87: invokevirtual 1538	com/google/android/gms/games/broker/PlayerAgent:getLockableForPlayersCollection	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/games/broker/Lockable;
    //   90: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   93: aload_2
    //   94: ldc_w 1667
    //   97: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   100: invokestatic 1673	com/google/android/gms/common/internal/Asserts:checkState	(Z)V
    //   103: aload 7
    //   105: aload_1
    //   106: aload_1
    //   107: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   110: aload_2
    //   111: invokestatic 1596	com/google/android/gms/games/cache/PlayerCache:getCacheKeyForCallingPackageCollection	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Ljava/lang/String;
    //   114: aload_2
    //   115: iload_3
    //   116: iload 4
    //   118: invokevirtual 1482	com/google/android/gms/games/broker/PlayerAgent:loadPlayerData$2fcfbbfc	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   121: astore_1
    //   122: goto -77 -> 45
    //   125: iconst_0
    //   126: istore 5
    //   128: goto -53 -> 75
    //   131: astore_1
    //   132: iconst_1
    //   133: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   136: dup
    //   137: iconst_0
    //   138: aload 6
    //   140: aastore
    //   141: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   144: aload_1
    //   145: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	this	DataBroker
    //   0	146	1	paramGamesClientContext	GamesClientContext
    //   0	146	2	paramString	String
    //   0	146	3	paramInt	int
    //   0	146	4	paramBoolean	boolean
    //   73	54	5	bool	boolean
    //   9	130	6	localLockable	Lockable
    //   63	41	7	localPlayerAgent	PlayerAgent
    // Exception table:
    //   from	to	target	type
    //   28	45	131	finally
    //   59	72	131	finally
    //   75	122	131	finally
  }
  
  /* Error */
  public final DataHolder getPlayersArray(GamesClientContext paramGamesClientContext, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: invokevirtual 1676	com/google/android/gms/games/broker/PlayerAgent:getPlayersArray	(Lcom/google/android/gms/games/broker/GamesClientContext;[Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramArrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder getRoom(Context paramContext, ClientContext paramClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: aload_3
    //   26: invokevirtual 1680	com/google/android/gms/games/broker/RealTimeAgent:getRoom	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   29: astore_1
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: aload_1
    //   45: areturn
    //   46: astore_1
    //   47: iconst_1
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   57: aastore
    //   58: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   61: aload_1
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	DataBroker
    //   0	63	1	paramContext	Context
    //   0	63	2	paramClientContext	ClientContext
    //   0	63	3	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	30	46	finally
  }
  
  /* Error */
  public final DataHolder getSnapshotMetadataById(Context paramContext, ClientContext paramClientContext, String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: aload_2
    //   21: aload_3
    //   22: invokestatic 1683	com/google/android/gms/games/broker/SnapshotAgent:getSnapshotMetadataById	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   25: astore_1
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	DataBroker
    //   0	59	1	paramContext	Context
    //   0	59	2	paramClientContext	ClientContext
    //   0	59	3	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	26	42	finally
  }
  
  /* Error */
  public final Pair<Integer, Pair<String, String>> getStreamMetadata(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: aload_1
    //   19: invokevirtual 1687	com/google/android/gms/games/broker/VideoAgent:getStreamMetadata	(Lcom/google/android/gms/games/broker/GamesClientContext;)Landroid/util/Pair;
    //   22: astore_1
    //   23: iconst_1
    //   24: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   27: dup
    //   28: iconst_0
    //   29: aload_0
    //   30: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   33: aastore
    //   34: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   37: aload_1
    //   38: areturn
    //   39: astore_1
    //   40: iconst_1
    //   41: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   44: dup
    //   45: iconst_0
    //   46: aload_0
    //   47: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   50: aastore
    //   51: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   54: aload_1
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	DataBroker
    //   0	56	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   14	23	39	finally
  }
  
  /* Error */
  public final Pair<Integer, String> getStreamUrl(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: astore_2
    //   19: aload_1
    //   20: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   23: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   26: ifne +43 -> 69
    //   29: ldc_w 1388
    //   32: ldc_w 1691
    //   35: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: new 489	android/util/Pair
    //   41: dup
    //   42: sipush 9001
    //   45: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   48: aconst_null
    //   49: invokespecial 538	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: areturn
    //   69: aload_2
    //   70: aload_1
    //   71: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   74: invokevirtual 1695	com/google/android/gms/games/broker/VideoAgent:getDefaultYouTubeLiveBroadcastId	(Lcom/google/android/gms/common/internal/ClientContext;)Landroid/util/Pair;
    //   77: astore_1
    //   78: aload_1
    //   79: getfield 493	android/util/Pair:first	Ljava/lang/Object;
    //   82: checkcast 498	java/lang/Integer
    //   85: invokevirtual 875	java/lang/Integer:intValue	()I
    //   88: ifeq +6 -> 94
    //   91: goto -38 -> 53
    //   94: aload_1
    //   95: getfield 530	android/util/Pair:second	Ljava/lang/Object;
    //   98: ifnonnull +27 -> 125
    //   101: ldc_w 1388
    //   104: ldc_w 1697
    //   107: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: new 489	android/util/Pair
    //   113: dup
    //   114: sipush 9004
    //   117: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   120: aconst_null
    //   121: invokespecial 538	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   124: pop
    //   125: new 489	android/util/Pair
    //   128: dup
    //   129: iconst_0
    //   130: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   133: ldc_w 1699
    //   136: iconst_1
    //   137: anewarray 999	java/lang/Object
    //   140: dup
    //   141: iconst_0
    //   142: aload_1
    //   143: getfield 530	android/util/Pair:second	Ljava/lang/Object;
    //   146: aastore
    //   147: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   150: invokespecial 538	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   153: astore_1
    //   154: goto -101 -> 53
    //   157: astore_1
    //   158: iconst_1
    //   159: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   162: dup
    //   163: iconst_0
    //   164: aload_0
    //   165: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   168: aastore
    //   169: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   172: aload_1
    //   173: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	DataBroker
    //   0	174	1	paramGamesClientContext	GamesClientContext
    //   18	52	2	localVideoAgent	VideoAgent
    // Exception table:
    //   from	to	target	type
    //   14	53	157	finally
    //   69	91	157	finally
    //   94	125	157	finally
    //   125	154	157	finally
  }
  
  /* Error */
  public final com.google.android.gms.games.service.GamesSyncAdapter.GamesSystemStats getSystemStats(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: new 1704	com/google/android/gms/games/service/GamesSyncAdapter$GamesSystemStats
    //   3: dup
    //   4: invokespecial 1705	com/google/android/gms/games/service/GamesSyncAdapter$GamesSystemStats:<init>	()V
    //   7: astore_2
    //   8: iconst_1
    //   9: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   12: dup
    //   13: iconst_0
    //   14: aload_0
    //   15: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   18: aastore
    //   19: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   22: aload_1
    //   23: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   26: astore_3
    //   27: aload_1
    //   28: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   31: astore 4
    //   33: aload_2
    //   34: aload_1
    //   35: invokestatic 1708	com/google/android/gms/games/broker/GameAgent:countInstalledGames	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   38: putfield 1711	com/google/android/gms/games/service/GamesSyncAdapter$GamesSystemStats:numInstalledGames	I
    //   41: aload_2
    //   42: aload_3
    //   43: aload 4
    //   45: invokestatic 1716	com/google/android/gms/games/signin/SignInCache:countSignedInGames	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)I
    //   48: putfield 1719	com/google/android/gms/games/service/GamesSyncAdapter$GamesSystemStats:numSignedInGames	I
    //   51: aload_2
    //   52: aload_3
    //   53: aload 4
    //   55: invokestatic 1723	com/google/android/gms/games/broker/GameAgent:getMostRecentConnectionTime	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)J
    //   58: putfield 1726	com/google/android/gms/games/service/GamesSyncAdapter$GamesSystemStats:lastPlayedTimestampMillis	J
    //   61: iconst_1
    //   62: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   65: dup
    //   66: iconst_0
    //   67: aload_0
    //   68: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: aload_2
    //   76: areturn
    //   77: astore_1
    //   78: iconst_1
    //   79: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   82: dup
    //   83: iconst_0
    //   84: aload_0
    //   85: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   88: aastore
    //   89: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	DataBroker
    //   0	94	1	paramGamesClientContext	GamesClientContext
    //   7	69	2	localGamesSystemStats	com.google.android.gms.games.service.GamesSyncAdapter.GamesSystemStats
    //   26	27	3	localContext	Context
    //   31	23	4	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   22	61	77	finally
  }
  
  /* Error */
  public final DataHolder getTopScoresPage(GamesClientContext paramGamesClientContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: iload_3
    //   26: iload 4
    //   28: iload 5
    //   30: iconst_0
    //   31: invokevirtual 1281	com/google/android/gms/games/broker/LeaderboardAgent:getRootPage	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IIII)Lcom/google/android/gms/common/data/DataHolder;
    //   34: astore_1
    //   35: iconst_1
    //   36: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   39: dup
    //   40: iconst_0
    //   41: aload_0
    //   42: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   45: aastore
    //   46: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   49: aload_1
    //   50: areturn
    //   51: astore_1
    //   52: iconst_1
    //   53: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   56: dup
    //   57: iconst_0
    //   58: aload_0
    //   59: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   62: aastore
    //   63: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	DataBroker
    //   0	68	1	paramGamesClientContext	GamesClientContext
    //   0	68	2	paramString	String
    //   0	68	3	paramInt1	int
    //   0	68	4	paramInt2	int
    //   0	68	5	paramInt3	int
    // Exception table:
    //   from	to	target	type
    //   19	35	51	finally
  }
  
  /* Error */
  public final DataHolder getTurnBasedMatch(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: aload_1
    //   24: invokevirtual 1607	com/google/android/gms/games/broker/MultiplayerAgent:syncEntities	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   27: istore_3
    //   28: aload_1
    //   29: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   32: aload_1
    //   33: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   36: aload_2
    //   37: iload_3
    //   38: invokestatic 1732	com/google/android/gms/games/broker/TurnBasedAgent:getLocalMatch	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   41: astore_1
    //   42: iconst_1
    //   43: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   46: dup
    //   47: iconst_0
    //   48: aload_0
    //   49: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: astore_1
    //   59: iconst_1
    //   60: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   63: dup
    //   64: iconst_0
    //   65: aload_0
    //   66: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   69: aastore
    //   70: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   73: aload_1
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	DataBroker
    //   0	75	1	paramGamesClientContext	GamesClientContext
    //   0	75	2	paramString	String
    //   27	11	3	i	int
    // Exception table:
    //   from	to	target	type
    //   19	42	58	finally
  }
  
  /* Error */
  public final boolean hasRecentGameActivity(Context paramContext, ClientContext paramClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: aload_2
    //   16: invokestatic 1723	com/google/android/gms/games/broker/GameAgent:getMostRecentConnectionTime	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;)J
    //   19: lstore_3
    //   20: invokestatic 1736	java/lang/System:currentTimeMillis	()J
    //   23: lstore 5
    //   25: lload_3
    //   26: lconst_0
    //   27: lcmp
    //   28: ifle +47 -> 75
    //   31: getstatic 1739	com/google/android/gms/games/config/G:mostRecentConnectionThresholdMillis	Lcom/google/android/gms/common/config/GservicesValue;
    //   34: invokevirtual 100	com/google/android/gms/common/config/GservicesValue:get	()Ljava/lang/Object;
    //   37: checkcast 464	java/lang/Long
    //   40: invokevirtual 1742	java/lang/Long:longValue	()J
    //   43: lstore 7
    //   45: lload 5
    //   47: lload_3
    //   48: lsub
    //   49: lload 7
    //   51: lcmp
    //   52: ifgt +23 -> 75
    //   55: iconst_1
    //   56: istore 9
    //   58: iconst_1
    //   59: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   62: dup
    //   63: iconst_0
    //   64: aload_0
    //   65: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   68: aastore
    //   69: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   72: iload 9
    //   74: ireturn
    //   75: iconst_0
    //   76: istore 9
    //   78: goto -20 -> 58
    //   81: astore_1
    //   82: iconst_1
    //   83: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   86: dup
    //   87: iconst_0
    //   88: aload_0
    //   89: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   92: aastore
    //   93: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   96: aload_1
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	DataBroker
    //   0	98	1	paramContext	Context
    //   0	98	2	paramClientContext	ClientContext
    //   19	29	3	l1	long
    //   23	23	5	l2	long
    //   43	7	7	l3	long
    //   56	21	9	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	25	81	finally
    //   31	45	81	finally
  }
  
  /* Error */
  public final DataHolder ignoreFriendInvite(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_2
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 1745	com/google/android/gms/games/broker/SocialAgent:ignoreFriendInvite	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: aload_0
    //   37: aload_1
    //   38: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   41: iconst_2
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: aload_0
    //   55: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: iconst_2
    //   66: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   69: dup
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: aload_0
    //   79: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   82: aastore
    //   83: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	DataBroker
    //   0	88	1	paramGamesClientContext	GamesClientContext
    //   0	88	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   26	41	64	finally
  }
  
  public final int incrementAchievement(GamesClientContext paramGamesClientContext, String paramString, int paramInt, PopupManager.PopupLocationInfo paramPopupLocationInfo, boolean paramBoolean)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mAchievementAgent });
    try
    {
      paramString = this.mAchievementAgent.incrementAchievement(paramGamesClientContext, paramString, paramInt, paramPopupLocationInfo, paramBoolean);
      releaseLocks(new Lockable[] { this.mAchievementAgent });
      if (paramString.xpGained > 0L) {
        gainXp(paramGamesClientContext, paramString.xpGained, paramPopupLocationInfo);
      }
      return paramString.statusCode;
    }
    finally
    {
      releaseLocks(new Lockable[] { this.mAchievementAgent });
    }
  }
  
  public final int incrementEvents(GamesClientContext paramGamesClientContext, ArrayList<EventIncrementEntry> paramArrayList)
  {
    acquireLocks(new Lockable[] { this.mQuestAgent, this.mEventAgent });
    int j;
    String str;
    Object localObject1;
    Object localObject2;
    ArrayList localArrayList;
    try
    {
      j = this.mEventAgent.incrementEvents(paramGamesClientContext, paramArrayList);
      paramArrayList = this.mQuestAgent;
      paramArrayList = paramGamesClientContext.mContext;
      localClientContext = paramGamesClientContext.mClientContext;
      str = paramGamesClientContext.mExternalTargetGameId;
      localObject1 = new QuerySpec(GamesContractInternal.QuestsEntities.getUriForExternalGameId(localClientContext, str));
      ((QuerySpec)localObject1).addWhere("quest_state", Integer.toString(3));
      ((QuerySpec)localObject1).addWhere("milestone_state", Integer.toString(2));
      localObject2 = new Agents.QueryBuilder(paramArrayList);
      ((Agents.QueryBuilder)localObject2).mQuery = ((QuerySpec)localObject1);
      ((Agents.QueryBuilder)localObject2).mProjection = QuestAgent.CompletedQuestsQuery.PROJECTION;
      localObject1 = ((Agents.QueryBuilder)localObject2).queryCursor();
      localObject2 = new ArrayList();
      localArrayList = new ArrayList();
    }
    finally
    {
      try
      {
        ClientContext localClientContext;
        while (((Cursor)localObject1).moveToNext())
        {
          long l = ((Cursor)localObject1).getLong(3);
          if (((Cursor)localObject1).getLong(4) - l >= ((Cursor)localObject1).getLong(5))
          {
            ((ArrayList)localObject2).add(ContentProviderOperation.newInsert(GamesContractInternal.Quests.getUriForId(localClientContext, ((Cursor)localObject1).getLong(0))).withValue("quest_state", Integer.valueOf(4)).withValue("quest_last_updated_ts", Long.valueOf(DefaultClock.getInstance().currentTimeMillis())).build());
            ((ArrayList)localObject2).add(ContentProviderOperation.newInsert(GamesContractInternal.Milestones.getUriForId(localClientContext, ((Cursor)localObject1).getLong(1))).withValue("milestone_state", Integer.valueOf(3)).build());
            localArrayList.add(((Cursor)localObject1).getString(2));
          }
        }
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
      tmp306_303[0] = this.mQuestAgent;
      Lockable[] tmp313_306 = tmp306_303;
      tmp313_306[1] = this.mEventAgent;
      releaseLocks(tmp313_306);
    }
    ((Cursor)localObject1).close();
    if (!((ArrayList)localObject2).isEmpty()) {
      Agents.applyContentOperations(paramArrayList.getContentResolver(), (ArrayList)localObject2, "QuestAgent");
    }
    int k = tmp313_306.size();
    int i = 0;
    while (i < k)
    {
      QuestAgent.notifyListeners(paramGamesClientContext, str, (String)tmp313_306.get(i), true);
      i += 1;
    }
    releaseLocks(new Lockable[] { this.mQuestAgent, this.mEventAgent });
    return j;
  }
  
  /* Error */
  public final void invalidateAppContentCache(GamesClientContext paramGamesClientContext, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   18: astore 5
    //   20: aload_1
    //   21: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   24: astore 6
    //   26: aload 6
    //   28: ifnull +10 -> 38
    //   31: aload 5
    //   33: aload 6
    //   35: invokevirtual 1821	com/google/android/gms/games/broker/AppContentAgent:enableCaches	(Ljava/lang/String;)V
    //   38: aload_2
    //   39: ifnonnull +38 -> 77
    //   42: aload 5
    //   44: getfield 1825	com/google/android/gms/games/broker/AppContentAgent:mCardStreamCache	Lcom/google/android/gms/games/cache/AppContentSectionAndCardCache;
    //   47: invokevirtual 1828	com/google/android/gms/games/cache/AppContentSectionAndCardCache:clear	()V
    //   50: aload_1
    //   51: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   54: aload_1
    //   55: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   58: aconst_null
    //   59: invokestatic 1832	com/google/android/gms/games/broker/AppContentAgent:clearDBCache	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)V
    //   62: iconst_1
    //   63: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   66: dup
    //   67: iconst_0
    //   68: aload_0
    //   69: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   72: aastore
    //   73: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   76: return
    //   77: aload_2
    //   78: arraylength
    //   79: istore 4
    //   81: iconst_0
    //   82: istore_3
    //   83: iload_3
    //   84: iload 4
    //   86: if_icmpge -24 -> 62
    //   89: aload_2
    //   90: iload_3
    //   91: aaload
    //   92: astore 6
    //   94: aload 5
    //   96: getfield 1825	com/google/android/gms/games/broker/AppContentAgent:mCardStreamCache	Lcom/google/android/gms/games/cache/AppContentSectionAndCardCache;
    //   99: aload 6
    //   101: invokevirtual 1835	com/google/android/gms/games/cache/AppContentSectionAndCardCache:clearData	(Ljava/lang/Object;)V
    //   104: aload_1
    //   105: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   108: aload_1
    //   109: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   112: aload 6
    //   114: invokestatic 1832	com/google/android/gms/games/broker/AppContentAgent:clearDBCache	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)V
    //   117: iload_3
    //   118: iconst_1
    //   119: iadd
    //   120: istore_3
    //   121: goto -38 -> 83
    //   124: astore_1
    //   125: iconst_1
    //   126: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   129: dup
    //   130: iconst_0
    //   131: aload_0
    //   132: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   135: aastore
    //   136: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   139: aload_1
    //   140: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	DataBroker
    //   0	141	1	paramGamesClientContext	GamesClientContext
    //   0	141	2	paramArrayOfString	String[]
    //   82	39	3	i	int
    //   79	8	4	j	int
    //   18	77	5	localAppContentAgent	AppContentAgent
    //   24	89	6	str	String
    // Exception table:
    //   from	to	target	type
    //   14	26	124	finally
    //   31	38	124	finally
    //   42	62	124	finally
    //   77	81	124	finally
    //   94	117	124	finally
  }
  
  /* Error */
  public final boolean isCaptureAvailable()
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: invokevirtual 1839	com/google/android/gms/games/broker/VideoAgent:isAvailable	()Z
    //   21: istore_1
    //   22: iconst_1
    //   23: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   32: aastore
    //   33: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   36: iload_1
    //   37: ireturn
    //   38: astore_2
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   49: aastore
    //   50: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   53: aload_2
    //   54: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	55	0	this	DataBroker
    //   21	16	1	bool	boolean
    //   38	16	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	22	38	finally
  }
  
  /* Error */
  public final DataHolder isGameMuted(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   49: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   62: aload_1
    //   63: invokevirtual 1845	com/google/android/gms/games/broker/GameAgent:isGameMuted	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   66: astore_1
    //   67: goto -28 -> 39
    //   70: astore_1
    //   71: iconst_1
    //   72: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   75: dup
    //   76: iconst_0
    //   77: aload_0
    //   78: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   81: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   84: aastore
    //   85: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   88: aload_1
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	DataBroker
    //   0	90	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   22	39	70	finally
    //   58	67	70	finally
  }
  
  /* Error */
  public final boolean isStreamingAvailable(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: invokevirtual 1839	com/google/android/gms/games/broker/VideoAgent:isAvailable	()Z
    //   21: ifeq +50 -> 71
    //   24: aload_1
    //   25: invokestatic 1855	com/google/android/gms/common/util/NetworkUtils:getNetworkInfo	(Landroid/content/Context;)Landroid/net/NetworkInfo;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull +36 -> 66
    //   33: aload_1
    //   34: invokevirtual 1860	android/net/NetworkInfo:isConnectedOrConnecting	()Z
    //   37: istore_3
    //   38: iload_3
    //   39: ifeq +27 -> 66
    //   42: iconst_1
    //   43: istore_2
    //   44: iload_2
    //   45: ifeq +26 -> 71
    //   48: iconst_1
    //   49: istore_3
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   60: aastore
    //   61: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   64: iload_3
    //   65: ireturn
    //   66: iconst_0
    //   67: istore_2
    //   68: goto -24 -> 44
    //   71: iconst_0
    //   72: istore_3
    //   73: goto -23 -> 50
    //   76: astore_1
    //   77: iconst_1
    //   78: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   81: dup
    //   82: iconst_0
    //   83: aload_0
    //   84: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   87: aastore
    //   88: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	DataBroker
    //   0	93	1	paramContext	Context
    //   43	25	2	i	int
    //   37	36	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   14	29	76	finally
    //   33	38	76	finally
  }
  
  /* Error */
  public final int isStreamingEnabled(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   18: astore 7
    //   20: aload_1
    //   21: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   24: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   27: ifne +32 -> 59
    //   30: ldc_w 1388
    //   33: ldc_w 1863
    //   36: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: sipush 9001
    //   42: istore_2
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: iload_2
    //   58: ireturn
    //   59: invokestatic 519	com/google/android/gms/common/util/DefaultClock:getInstance	()Lcom/google/android/gms/common/util/Clock;
    //   62: invokeinterface 1866 1 0
    //   67: lstore_3
    //   68: aload_1
    //   69: invokestatic 1872	com/google/android/gms/games/utils/ServiceSharedPrefs:getLastStreamingEnabledSuccess	(Lcom/google/android/gms/games/broker/GamesClientContext;)J
    //   72: lstore 5
    //   74: lload 5
    //   76: ldc2_w 923
    //   79: lcmp
    //   80: ifeq +37 -> 117
    //   83: lload 5
    //   85: getstatic 1875	com/google/android/gms/games/config/G:videoStreamingEnabledCheckMillis	Lcom/google/android/gms/common/config/GservicesValue;
    //   88: invokevirtual 100	com/google/android/gms/common/config/GservicesValue:get	()Ljava/lang/Object;
    //   91: checkcast 464	java/lang/Long
    //   94: invokevirtual 1742	java/lang/Long:longValue	()J
    //   97: ladd
    //   98: lload_3
    //   99: lcmp
    //   100: iflt +17 -> 117
    //   103: ldc_w 1388
    //   106: ldc_w 1877
    //   109: invokestatic 1880	com/google/android/gms/games/util/VideoUtils:logChatty	(Ljava/lang/String;Ljava/lang/String;)V
    //   112: iconst_0
    //   113: istore_2
    //   114: goto -71 -> 43
    //   117: ldc_w 1388
    //   120: ldc_w 1882
    //   123: invokestatic 1880	com/google/android/gms/games/util/VideoUtils:logChatty	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: aload 7
    //   128: aload_1
    //   129: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   132: iconst_1
    //   133: anewarray 129	java/lang/String
    //   136: dup
    //   137: iconst_0
    //   138: ldc_w 1884
    //   141: aastore
    //   142: iconst_1
    //   143: invokevirtual 1888	com/google/android/gms/games/broker/VideoAgent:getDefaultYouTubeLiveStream	(Lcom/google/android/gms/common/internal/ClientContext;[Ljava/lang/String;Z)Lcom/google/android/gms/games/broker/VideoAgent$YouTubeObjectAndStatus;
    //   146: getfield 1891	com/google/android/gms/games/broker/VideoAgent$YouTubeObjectAndStatus:statusCode	I
    //   149: istore_2
    //   150: iload_2
    //   151: ifne +37 -> 188
    //   154: ldc_w 1388
    //   157: ldc_w 1893
    //   160: invokestatic 1880	com/google/android/gms/games/util/VideoUtils:logChatty	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: aload_1
    //   164: lload_3
    //   165: invokestatic 1897	com/google/android/gms/games/utils/ServiceSharedPrefs:setLastStreamingEnabledSuccess	(Lcom/google/android/gms/games/broker/GamesClientContext;J)V
    //   168: goto -125 -> 43
    //   171: astore_1
    //   172: iconst_1
    //   173: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   176: dup
    //   177: iconst_0
    //   178: aload_0
    //   179: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   182: aastore
    //   183: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   186: aload_1
    //   187: athrow
    //   188: ldc_w 1388
    //   191: ldc_w 1899
    //   194: invokestatic 1880	com/google/android/gms/games/util/VideoUtils:logChatty	(Ljava/lang/String;Ljava/lang/String;)V
    //   197: aload_1
    //   198: invokestatic 1902	com/google/android/gms/games/utils/ServiceSharedPrefs:removeLastStreamingEnabledSuccess	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   201: goto -158 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	204	0	this	DataBroker
    //   0	204	1	paramGamesClientContext	GamesClientContext
    //   42	109	2	i	int
    //   67	98	3	l1	long
    //   72	12	5	l2	long
    //   18	109	7	localVideoAgent	VideoAgent
    // Exception table:
    //   from	to	target	type
    //   14	39	171	finally
    //   59	74	171	finally
    //   83	112	171	finally
    //   117	150	171	finally
    //   154	168	171	finally
    //   188	201	171	finally
  }
  
  /* Error */
  public final int launchCaptureOverlayAndGame(GamesClientContext paramGamesClientContext, com.google.android.gms.games.video.VideoConfiguration paramVideoConfiguration, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 4
    //   3: aload_0
    //   4: aload_1
    //   5: invokespecial 866	com/google/android/gms/games/broker/DataBroker:getGameForVideoCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/Game;
    //   8: astore 5
    //   10: aload 5
    //   12: ifnull +45 -> 57
    //   15: iconst_1
    //   16: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   25: aastore
    //   26: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   29: aload_0
    //   30: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   33: aload_1
    //   34: aload_2
    //   35: aload 5
    //   37: iload_3
    //   38: invokevirtual 1907	com/google/android/gms/games/broker/VideoAgent:launchCaptureOverlayAndGame	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/video/VideoConfiguration;Lcom/google/android/gms/games/Game;Z)I
    //   41: istore 4
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: iload 4
    //   59: ireturn
    //   60: astore_1
    //   61: iconst_1
    //   62: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   65: dup
    //   66: iconst_0
    //   67: aload_0
    //   68: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	DataBroker
    //   0	77	1	paramGamesClientContext	GamesClientContext
    //   0	77	2	paramVideoConfiguration	com.google.android.gms.games.video.VideoConfiguration
    //   0	77	3	paramBoolean	boolean
    //   1	57	4	i	int
    //   8	28	5	localGame	com.google.android.gms.games.Game
    // Exception table:
    //   from	to	target	type
    //   29	43	60	finally
  }
  
  /* Error */
  public final int leaveRoom(Context paramContext, ClientContext paramClientContext, String paramString1, String paramString2, com.google.android.gms.games.server.api.RoomLeaveDiagnostics paramRoomLeaveDiagnostics)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   18: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   25: aload_1
    //   26: aload_2
    //   27: aload_3
    //   28: aload 4
    //   30: aload 5
    //   32: invokevirtual 1911	com/google/android/gms/games/broker/RealTimeAgent:leaveRoom	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/games/server/api/RoomLeaveDiagnostics;)I
    //   35: istore 6
    //   37: iconst_1
    //   38: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   47: aastore
    //   48: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   51: iload 6
    //   53: ireturn
    //   54: astore_1
    //   55: iconst_1
    //   56: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   59: dup
    //   60: iconst_0
    //   61: aload_0
    //   62: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   65: aastore
    //   66: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   69: aload_1
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	this	DataBroker
    //   0	71	1	paramContext	Context
    //   0	71	2	paramClientContext	ClientContext
    //   0	71	3	paramString1	String
    //   0	71	4	paramString2	String
    //   0	71	5	paramRoomLeaveDiagnostics	com.google.android.gms.games.server.api.RoomLeaveDiagnostics
    //   35	17	6	i	int
    // Exception table:
    //   from	to	target	type
    //   14	37	54	finally
  }
  
  /* Error */
  public final DataHolder leaveTurnBasedMatch(GamesClientContext paramGamesClientContext, String paramString1, boolean paramBoolean, String paramString2)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   30: astore 7
    //   32: aload_1
    //   33: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   36: astore 8
    //   38: aload_1
    //   39: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   42: astore 9
    //   44: aload 8
    //   46: aload 9
    //   48: aload_2
    //   49: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   52: ifeq +49 -> 101
    //   55: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   58: new 153	java/lang/StringBuilder
    //   61: dup
    //   62: ldc_w 1915
    //   65: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   68: aload_2
    //   69: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   78: sipush 6507
    //   81: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   84: astore_1
    //   85: iconst_1
    //   86: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   89: dup
    //   90: iconst_0
    //   91: aload_0
    //   92: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   95: aastore
    //   96: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   99: aload_1
    //   100: areturn
    //   101: aload 8
    //   103: aload 9
    //   105: aload_2
    //   106: invokestatic 1072	com/google/android/gms/games/broker/TurnBasedAgent:getLocalVersion	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)I
    //   109: istore 5
    //   111: iload 5
    //   113: iconst_m1
    //   114: if_icmpne +34 -> 148
    //   117: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   120: new 153	java/lang/StringBuilder
    //   123: dup
    //   124: ldc_w 1074
    //   127: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   130: aload_2
    //   131: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: iconst_1
    //   141: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   144: astore_1
    //   145: goto -60 -> 85
    //   148: aload 7
    //   150: aload_1
    //   151: aload_2
    //   152: iload 5
    //   154: iload_3
    //   155: aload 4
    //   157: invokevirtual 1919	com/google/android/gms/games/broker/TurnBasedAgent:leaveMatchInternal	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZLjava/lang/String;)I
    //   160: istore 6
    //   162: iload 6
    //   164: iconst_5
    //   165: if_icmpne +15 -> 180
    //   168: aload_1
    //   169: iconst_5
    //   170: aload_2
    //   171: aload 4
    //   173: iload_3
    //   174: iload 5
    //   176: aconst_null
    //   177: invokestatic 1109	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;Ljava/lang/String;ZILcom/google/android/gms/games/server/api/TurnBasedMatchResults;)V
    //   180: new 1497	com/google/android/gms/games/broker/Agents$QueryBuilder
    //   183: dup
    //   184: aload_1
    //   185: invokespecial 1500	com/google/android/gms/games/broker/Agents$QueryBuilder:<init>	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   188: aload 9
    //   190: aload_2
    //   191: invokestatic 1922	com/google/android/gms/games/provider/GamesContractInternal$MatchEntities:getUriForExternalMatchId	(Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Landroid/net/Uri;
    //   194: invokevirtual 1504	com/google/android/gms/games/broker/Agents$QueryBuilder:setQuerySpec	(Landroid/net/Uri;)Lcom/google/android/gms/games/broker/Agents$QueryBuilder;
    //   197: astore_1
    //   198: aload_1
    //   199: iload 6
    //   201: putfield 1510	com/google/android/gms/games/broker/Agents$QueryBuilder:mStatusCode	I
    //   204: aload_1
    //   205: aconst_null
    //   206: invokevirtual 1513	com/google/android/gms/games/broker/Agents$QueryBuilder:query	(Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   209: astore_1
    //   210: goto -125 -> 85
    //   213: astore_1
    //   214: iconst_1
    //   215: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   218: dup
    //   219: iconst_0
    //   220: aload_0
    //   221: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   224: aastore
    //   225: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   228: aload_1
    //   229: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	DataBroker
    //   0	230	1	paramGamesClientContext	GamesClientContext
    //   0	230	2	paramString1	String
    //   0	230	3	paramBoolean	boolean
    //   0	230	4	paramString2	String
    //   109	66	5	i	int
    //   160	40	6	j	int
    //   30	119	7	localTurnBasedAgent	TurnBasedAgent
    //   36	66	8	localContext	Context
    //   42	147	9	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   19	85	213	finally
    //   101	111	213	finally
    //   117	145	213	finally
    //   148	162	213	finally
    //   168	180	213	finally
    //   180	210	213	finally
  }
  
  /* Error */
  public final DataHolder listBackgroundCaptureEnabledGames(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   18: invokestatic 1927	com/google/android/gms/games/broker/VideoAgent:getCapturePermissionEnabledPackages	(Landroid/content/Context;)Ljava/util/Set;
    //   21: astore_2
    //   22: iconst_1
    //   23: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   32: aastore
    //   33: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   36: aload_2
    //   37: invokeinterface 1928 1 0
    //   42: ifeq +25 -> 67
    //   45: iconst_0
    //   46: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   49: areturn
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: athrow
    //   67: iconst_1
    //   68: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   77: aastore
    //   78: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   81: aload_1
    //   82: aload_2
    //   83: invokestatic 1932	com/google/android/gms/games/broker/GameAgent:getGamesForPackages	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/util/Set;)Lcom/google/android/gms/common/data/DataHolder;
    //   86: astore_1
    //   87: iconst_1
    //   88: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   91: dup
    //   92: iconst_0
    //   93: aload_0
    //   94: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   97: aastore
    //   98: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   101: aload_1
    //   102: areturn
    //   103: astore_1
    //   104: iconst_1
    //   105: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   108: dup
    //   109: iconst_0
    //   110: aload_0
    //   111: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   114: aastore
    //   115: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   118: aload_1
    //   119: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	DataBroker
    //   0	120	1	paramGamesClientContext	GamesClientContext
    //   21	62	2	localSet	Set
    // Exception table:
    //   from	to	target	type
    //   14	22	50	finally
    //   81	87	103	finally
  }
  
  /* Error */
  public final DataHolder listVideos(GamesClientContext paramGamesClientContext, int paramInt, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: new 1936	java/util/HashMap
    //   3: dup
    //   4: invokespecial 1937	java/util/HashMap:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: aload_1
    //   13: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   16: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   19: ifeq +114 -> 133
    //   22: aload_1
    //   23: invokevirtual 1940	com/google/android/gms/games/broker/GamesClientContext:assert1P	()V
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   36: aastore
    //   37: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: aload_0
    //   41: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   44: astore 7
    //   46: ldc_w 1388
    //   49: ldc_w 1942
    //   52: iconst_1
    //   53: anewarray 999	java/lang/Object
    //   56: dup
    //   57: iconst_0
    //   58: aload 5
    //   60: aastore
    //   61: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokestatic 1880	com/google/android/gms/games/util/VideoUtils:logChatty	(Ljava/lang/String;Ljava/lang/String;)V
    //   67: aload_1
    //   68: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   71: astore 9
    //   73: aload_1
    //   74: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   77: astore 8
    //   79: aload 9
    //   81: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   84: ifne +78 -> 162
    //   87: ldc_w 1388
    //   90: ldc_w 1944
    //   93: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   96: sipush 9001
    //   99: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   102: astore_3
    //   103: iconst_1
    //   104: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   107: dup
    //   108: iconst_0
    //   109: aload_0
    //   110: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   113: aastore
    //   114: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   117: aload_3
    //   118: getfield 644	com/google/android/gms/common/data/DataHolder:mStatusCode	I
    //   121: ifne +10 -> 131
    //   124: aload_3
    //   125: getfield 333	com/google/android/gms/common/data/DataHolder:mRowCount	I
    //   128: ifne +313 -> 441
    //   131: aload_3
    //   132: areturn
    //   133: aload_0
    //   134: aload_1
    //   135: invokespecial 866	com/google/android/gms/games/broker/DataBroker:getGameForVideoCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/Game;
    //   138: astore 7
    //   140: aload 7
    //   142: invokeinterface 869 1 0
    //   147: astore 5
    //   149: aload 6
    //   151: aload 5
    //   153: aload 7
    //   155: invokevirtual 1947	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   158: pop
    //   159: goto -133 -> 26
    //   162: aload_3
    //   163: ifnull +106 -> 269
    //   166: aload_3
    //   167: arraylength
    //   168: ifle +101 -> 269
    //   171: aload 5
    //   173: ifnonnull +61 -> 234
    //   176: ldc_w 1949
    //   179: iconst_1
    //   180: anewarray 999	java/lang/Object
    //   183: dup
    //   184: iconst_0
    //   185: ldc_w 1951
    //   188: invokestatic 1957	com/google/android/gms/common/internal/Joiner:on	(Ljava/lang/String;)Lcom/google/android/gms/common/internal/Joiner;
    //   191: aload_3
    //   192: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   195: invokevirtual 1961	com/google/android/gms/common/internal/Joiner:join	(Ljava/lang/Iterable;)Ljava/lang/String;
    //   198: aastore
    //   199: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: astore_3
    //   203: goto +642 -> 845
    //   206: new 1963	java/lang/RuntimeException
    //   209: dup
    //   210: ldc_w 1965
    //   213: invokespecial 1966	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   216: athrow
    //   217: astore_1
    //   218: iconst_1
    //   219: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   222: dup
    //   223: iconst_0
    //   224: aload_0
    //   225: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   228: aastore
    //   229: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   232: aload_1
    //   233: athrow
    //   234: ldc_w 1968
    //   237: iconst_2
    //   238: anewarray 999	java/lang/Object
    //   241: dup
    //   242: iconst_0
    //   243: aload 5
    //   245: aastore
    //   246: dup
    //   247: iconst_1
    //   248: ldc_w 1951
    //   251: invokestatic 1957	com/google/android/gms/common/internal/Joiner:on	(Ljava/lang/String;)Lcom/google/android/gms/common/internal/Joiner;
    //   254: aload_3
    //   255: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   258: invokevirtual 1961	com/google/android/gms/common/internal/Joiner:join	(Ljava/lang/Iterable;)Ljava/lang/String;
    //   261: aastore
    //   262: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   265: astore_3
    //   266: goto +579 -> 845
    //   269: aload 5
    //   271: ifnull +572 -> 843
    //   274: ldc_w 1970
    //   277: iconst_1
    //   278: anewarray 999	java/lang/Object
    //   281: dup
    //   282: iconst_0
    //   283: aload 5
    //   285: aastore
    //   286: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   289: astore_3
    //   290: goto +555 -> 845
    //   293: ldc_w 1972
    //   296: iconst_1
    //   297: anewarray 999	java/lang/Object
    //   300: dup
    //   301: iconst_0
    //   302: ldc_w 1974
    //   305: aastore
    //   306: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   309: astore 5
    //   311: aload_1
    //   312: invokestatic 1978	com/google/android/gms/games/utils/ServiceSharedPrefs:getVideoDirectoryCheckedForLegacyFiles	(Lcom/google/android/gms/games/broker/GamesClientContext;)Z
    //   315: ifne +13 -> 328
    //   318: aload 7
    //   320: aload_1
    //   321: invokevirtual 1981	com/google/android/gms/games/broker/VideoAgent:searchVideoFolderForLegacyFiles	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   324: aload_1
    //   325: invokestatic 1984	com/google/android/gms/games/utils/ServiceSharedPrefs:setVideoDirectoryCheckedForLegacyFiles	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   328: aload 8
    //   330: invokestatic 1987	com/google/android/gms/games/provider/GamesContractInternal$VideoInstances:getContentUri	(Lcom/google/android/gms/common/internal/ClientContext;)Landroid/net/Uri;
    //   333: astore 7
    //   335: new 1497	com/google/android/gms/games/broker/Agents$QueryBuilder
    //   338: dup
    //   339: aload_1
    //   340: invokespecial 1500	com/google/android/gms/games/broker/Agents$QueryBuilder:<init>	(Lcom/google/android/gms/games/broker/GamesClientContext;)V
    //   343: aload 7
    //   345: aload_3
    //   346: aconst_null
    //   347: invokevirtual 1990	com/google/android/gms/games/broker/Agents$QueryBuilder:setQuerySpec	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Lcom/google/android/gms/games/broker/Agents$QueryBuilder;
    //   350: astore_3
    //   351: aload_3
    //   352: aload 5
    //   354: putfield 1509	com/google/android/gms/games/broker/Agents$QueryBuilder:mSortOrder	Ljava/lang/String;
    //   357: aload_3
    //   358: aconst_null
    //   359: invokevirtual 1513	com/google/android/gms/games/broker/Agents$QueryBuilder:query	(Landroid/os/Bundle;)Lcom/google/android/gms/common/data/DataHolder;
    //   362: astore_3
    //   363: goto -260 -> 103
    //   366: ldc_w 1992
    //   369: iconst_1
    //   370: anewarray 999	java/lang/Object
    //   373: dup
    //   374: iconst_0
    //   375: ldc_w 1974
    //   378: aastore
    //   379: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   382: astore 5
    //   384: goto -73 -> 311
    //   387: ldc_w 1994
    //   390: iconst_2
    //   391: anewarray 999	java/lang/Object
    //   394: dup
    //   395: iconst_0
    //   396: ldc_w 1996
    //   399: aastore
    //   400: dup
    //   401: iconst_1
    //   402: ldc_w 1974
    //   405: aastore
    //   406: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   409: astore 5
    //   411: goto -100 -> 311
    //   414: ldc_w 1998
    //   417: iconst_2
    //   418: anewarray 999	java/lang/Object
    //   421: dup
    //   422: iconst_0
    //   423: ldc_w 1996
    //   426: aastore
    //   427: dup
    //   428: iconst_1
    //   429: ldc_w 1974
    //   432: aastore
    //   433: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   436: astore 5
    //   438: goto -127 -> 311
    //   441: getstatic 2004	com/google/android/gms/games/utils/GamesDataUtils:VIDEO_SPEC	Lcom/google/android/gms/games/provider/ColumnSpec;
    //   444: getfield 2009	com/google/android/gms/games/provider/ColumnSpec:mColumnNames	[Ljava/lang/String;
    //   447: invokestatic 2012	com/google/android/gms/common/data/DataHolder:builder	([Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder$Builder;
    //   450: astore 7
    //   452: new 81	java/util/ArrayList
    //   455: dup
    //   456: invokespecial 82	java/util/ArrayList:<init>	()V
    //   459: astore 8
    //   461: new 2014	com/google/android/gms/games/video/VideoBuffer
    //   464: dup
    //   465: aload_3
    //   466: invokespecial 2015	com/google/android/gms/games/video/VideoBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   469: astore 9
    //   471: iconst_0
    //   472: istore_2
    //   473: aload 9
    //   475: invokevirtual 2016	com/google/android/gms/games/video/VideoBuffer:getCount	()I
    //   478: istore 4
    //   480: iload_2
    //   481: iload 4
    //   483: if_icmpge +249 -> 732
    //   486: aload 9
    //   488: iload_2
    //   489: invokevirtual 2019	com/google/android/gms/games/video/VideoBuffer:get	(I)Lcom/google/android/gms/games/video/VideoRef;
    //   492: astore 10
    //   494: new 2021	java/io/File
    //   497: dup
    //   498: aload 10
    //   500: invokeinterface 2026 1 0
    //   505: invokespecial 2027	java/io/File:<init>	(Ljava/lang/String;)V
    //   508: invokevirtual 2030	java/io/File:isFile	()Z
    //   511: ifne +19 -> 530
    //   514: aload 8
    //   516: aload 10
    //   518: invokeinterface 2033 1 0
    //   523: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   526: pop
    //   527: goto +352 -> 879
    //   530: aload 6
    //   532: aload 10
    //   534: invokeinterface 2036 1 0
    //   539: invokevirtual 2038	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   542: checkcast 660	com/google/android/gms/games/Game
    //   545: astore 5
    //   547: aload 5
    //   549: astore_3
    //   550: aload 6
    //   552: aload 10
    //   554: invokeinterface 2036 1 0
    //   559: invokevirtual 2041	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   562: ifne +44 -> 606
    //   565: aload_1
    //   566: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   569: aload_1
    //   570: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   573: aload 10
    //   575: invokeinterface 2036 1 0
    //   580: invokestatic 2045	com/google/android/gms/games/broker/GameAgent:getExternalIdForPackage	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Ljava/lang/String;
    //   583: astore_3
    //   584: aload_3
    //   585: ifnonnull +98 -> 683
    //   588: aload 6
    //   590: aload 10
    //   592: invokeinterface 2036 1 0
    //   597: aload 5
    //   599: invokevirtual 1947	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   602: pop
    //   603: aload 5
    //   605: astore_3
    //   606: aload 10
    //   608: invokeinterface 2048 1 0
    //   613: astore 5
    //   615: aload_1
    //   616: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   619: aload 5
    //   621: invokestatic 2052	com/google/android/gms/games/util/VideoUtils:grantWhitelistedAppsUriPermission	(Landroid/content/Context;Landroid/net/Uri;)V
    //   624: aload 10
    //   626: aload_3
    //   627: invokestatic 2057	com/google/android/gms/games/video/VideoRef:toContentValues	(Lcom/google/android/gms/games/video/Video;Lcom/google/android/gms/games/Game;)Landroid/content/ContentValues;
    //   630: astore_3
    //   631: aload 10
    //   633: invokeinterface 2060 1 0
    //   638: ifeq +84 -> 722
    //   641: aload_1
    //   642: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   645: aload 10
    //   647: invokeinterface 2036 1 0
    //   652: aload 9
    //   654: iload_2
    //   655: invokevirtual 2019	com/google/android/gms/games/video/VideoBuffer:get	(I)Lcom/google/android/gms/games/video/VideoRef;
    //   658: invokevirtual 2061	com/google/android/gms/games/video/VideoRef:getVideoUri	()Landroid/net/Uri;
    //   661: iconst_1
    //   662: invokevirtual 2065	android/content/Context:grantUriPermission	(Ljava/lang/String;Landroid/net/Uri;I)V
    //   665: aload 7
    //   667: aload_3
    //   668: invokevirtual 2071	com/google/android/gms/common/data/DataHolder$Builder:withRow	(Landroid/content/ContentValues;)Lcom/google/android/gms/common/data/DataHolder$Builder;
    //   671: pop
    //   672: goto +207 -> 879
    //   675: astore_1
    //   676: aload 9
    //   678: invokevirtual 2072	com/google/android/gms/games/video/VideoBuffer:release	()V
    //   681: aload_1
    //   682: athrow
    //   683: aload_1
    //   684: invokevirtual 392	com/google/android/gms/games/broker/GamesClientContext:getBuilder	()Lcom/google/android/gms/games/broker/GamesClientContext$Builder;
    //   687: astore 5
    //   689: aload 5
    //   691: aload_3
    //   692: putfield 1543	com/google/android/gms/games/broker/GamesClientContext$Builder:mExternalTargetGameId	Ljava/lang/String;
    //   695: aload_0
    //   696: aload 5
    //   698: invokevirtual 399	com/google/android/gms/games/broker/GamesClientContext$Builder:build	()Lcom/google/android/gms/games/broker/GamesClientContext;
    //   701: invokespecial 866	com/google/android/gms/games/broker/DataBroker:getGameForVideoCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/Game;
    //   704: astore_3
    //   705: aload 6
    //   707: aload 10
    //   709: invokeinterface 2036 1 0
    //   714: aload_3
    //   715: invokevirtual 1947	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   718: pop
    //   719: goto -113 -> 606
    //   722: aload_3
    //   723: ldc_w 2074
    //   726: invokevirtual 2077	android/content/ContentValues:remove	(Ljava/lang/String;)V
    //   729: goto -64 -> 665
    //   732: aload 9
    //   734: invokevirtual 2072	com/google/android/gms/games/video/VideoBuffer:release	()V
    //   737: aload 8
    //   739: invokevirtual 362	java/util/ArrayList:size	()I
    //   742: istore_2
    //   743: iload_2
    //   744: ifle +75 -> 819
    //   747: iconst_1
    //   748: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   751: dup
    //   752: iconst_0
    //   753: aload_0
    //   754: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   757: aastore
    //   758: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   761: aload_1
    //   762: aload 8
    //   764: invokestatic 2080	com/google/android/gms/games/broker/VideoAgent:deleteVideosFromDatabase	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/util/ArrayList;)I
    //   767: istore 4
    //   769: iload 4
    //   771: iload_2
    //   772: if_icmpeq +33 -> 805
    //   775: ldc 70
    //   777: ldc_w 2082
    //   780: iconst_2
    //   781: anewarray 999	java/lang/Object
    //   784: dup
    //   785: iconst_0
    //   786: iload_2
    //   787: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   790: aastore
    //   791: dup
    //   792: iconst_1
    //   793: iload 4
    //   795: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   798: aastore
    //   799: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   802: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   805: iconst_1
    //   806: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   809: dup
    //   810: iconst_0
    //   811: aload_0
    //   812: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   815: aastore
    //   816: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   819: aload 7
    //   821: iconst_0
    //   822: invokevirtual 2084	com/google/android/gms/common/data/DataHolder$Builder:build	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   825: areturn
    //   826: astore_1
    //   827: iconst_1
    //   828: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   831: dup
    //   832: iconst_0
    //   833: aload_0
    //   834: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   837: aastore
    //   838: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   841: aload_1
    //   842: athrow
    //   843: aconst_null
    //   844: astore_3
    //   845: iload_2
    //   846: tableswitch	default:+30->876, 1:+-553->293, 2:+-480->366, 3:+-432->414, 4:+-459->387
    //   876: goto -670 -> 206
    //   879: iload_2
    //   880: iconst_1
    //   881: iadd
    //   882: istore_2
    //   883: goto -403 -> 480
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	886	0	this	DataBroker
    //   0	886	1	paramGamesClientContext	GamesClientContext
    //   0	886	2	paramInt	int
    //   0	886	3	paramArrayOfString	String[]
    //   478	316	4	i	int
    //   10	687	5	localObject1	Object
    //   7	699	6	localHashMap	HashMap
    //   44	776	7	localObject2	Object
    //   77	686	8	localObject3	Object
    //   71	662	9	localObject4	Object
    //   492	216	10	localVideoRef	com.google.android.gms.games.video.VideoRef
    // Exception table:
    //   from	to	target	type
    //   40	103	217	finally
    //   166	171	217	finally
    //   176	203	217	finally
    //   206	217	217	finally
    //   234	266	217	finally
    //   274	290	217	finally
    //   293	311	217	finally
    //   311	328	217	finally
    //   328	363	217	finally
    //   366	384	217	finally
    //   387	411	217	finally
    //   414	438	217	finally
    //   473	480	675	finally
    //   486	527	675	finally
    //   530	547	675	finally
    //   550	584	675	finally
    //   588	603	675	finally
    //   606	665	675	finally
    //   665	672	675	finally
    //   683	719	675	finally
    //   722	729	675	finally
    //   761	769	826	finally
    //   775	805	826	finally
  }
  
  /* Error */
  public final DataHolder[] loadCardStream(GamesClientContext paramGamesClientContext, AppContentContext paramAppContentContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   4: astore_1
    //   5: iconst_1
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   15: aastore
    //   16: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   19: aload_0
    //   20: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   23: astore 5
    //   25: aload_2
    //   26: getfield 2091	com/google/android/gms/games/broker/AppContentContext:screenName	Ljava/lang/String;
    //   29: astore 6
    //   31: invokestatic 519	com/google/android/gms/common/util/DefaultClock:getInstance	()Lcom/google/android/gms/common/util/Clock;
    //   34: invokeinterface 1866 1 0
    //   39: lstore_3
    //   40: aload 5
    //   42: aload_1
    //   43: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   46: invokevirtual 1821	com/google/android/gms/games/broker/AppContentAgent:enableCaches	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: getfield 2092	com/google/android/gms/games/broker/GamesClientContext:mForceReload	Z
    //   53: ifeq +13 -> 66
    //   56: aload 5
    //   58: getfield 1825	com/google/android/gms/games/broker/AppContentAgent:mCardStreamCache	Lcom/google/android/gms/games/cache/AppContentSectionAndCardCache;
    //   61: aload 6
    //   63: invokevirtual 1835	com/google/android/gms/games/cache/AppContentSectionAndCardCache:clearData	(Ljava/lang/Object;)V
    //   66: aload 5
    //   68: getfield 1825	com/google/android/gms/games/broker/AppContentAgent:mCardStreamCache	Lcom/google/android/gms/games/cache/AppContentSectionAndCardCache;
    //   71: aload 6
    //   73: lload_3
    //   74: invokevirtual 2093	com/google/android/gms/games/cache/AppContentSectionAndCardCache:hasData	(Ljava/lang/Object;J)Z
    //   77: ifeq +44 -> 121
    //   80: aload_1
    //   81: iconst_1
    //   82: newarray int
    //   84: dup
    //   85: iconst_0
    //   86: iconst_5
    //   87: iastore
    //   88: invokevirtual 2097	com/google/android/gms/games/broker/GamesClientContext:logLatencyEvents	([I)V
    //   91: aload 5
    //   93: aload_1
    //   94: aload 6
    //   96: invokevirtual 2101	com/google/android/gms/games/broker/AppContentAgent:buildResponseFromCache	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)[Lcom/google/android/gms/common/data/DataHolder;
    //   99: astore_1
    //   100: iconst_1
    //   101: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   104: dup
    //   105: iconst_0
    //   106: aload_0
    //   107: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   110: aastore
    //   111: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   114: aload_1
    //   115: invokestatic 2104	com/google/android/gms/common/internal/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   118: pop
    //   119: aload_1
    //   120: areturn
    //   121: aload 5
    //   123: aload_1
    //   124: aload_2
    //   125: lload_3
    //   126: invokevirtual 2108	com/google/android/gms/games/broker/AppContentAgent:loadCardStream$3489344c	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/broker/AppContentContext;J)[Lcom/google/android/gms/common/data/DataHolder;
    //   129: astore_1
    //   130: goto -30 -> 100
    //   133: astore_1
    //   134: iconst_1
    //   135: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   138: dup
    //   139: iconst_0
    //   140: aload_0
    //   141: getfield 204	com/google/android/gms/games/broker/DataBroker:mCardStreamAgent	Lcom/google/android/gms/games/broker/AppContentAgent;
    //   144: aastore
    //   145: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   148: aload_1
    //   149: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	this	DataBroker
    //   0	150	1	paramGamesClientContext	GamesClientContext
    //   0	150	2	paramAppContentContext	AppContentContext
    //   39	87	3	l	long
    //   23	99	5	localAppContentAgent	AppContentAgent
    //   29	66	6	str	String
    // Exception table:
    //   from	to	target	type
    //   19	66	133	finally
    //   66	100	133	finally
    //   121	130	133	finally
  }
  
  /* Error */
  public final DataHolder loadCommonGames(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_1
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   15: getfield 2112	com/google/android/gms/games/broker/GameAgent:mCompareCommonLock	Lcom/google/android/gms/games/broker/Lockable;
    //   18: aastore
    //   19: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   22: aload_0
    //   23: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   26: astore 4
    //   28: aload 4
    //   30: getfield 2112	com/google/android/gms/games/broker/GameAgent:mCompareCommonLock	Lcom/google/android/gms/games/broker/Lockable;
    //   33: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   36: aload 4
    //   38: aload_1
    //   39: ldc_w 2114
    //   42: iload_2
    //   43: iload_3
    //   44: invokevirtual 2117	com/google/android/gms/games/broker/GameAgent:loadCommonOrDisjointGames	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   47: astore_1
    //   48: iconst_1
    //   49: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   58: getfield 2112	com/google/android/gms/games/broker/GameAgent:mCompareCommonLock	Lcom/google/android/gms/games/broker/Lockable;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: areturn
    //   67: astore_1
    //   68: iconst_1
    //   69: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   72: dup
    //   73: iconst_0
    //   74: aload_0
    //   75: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   78: getfield 2112	com/google/android/gms/games/broker/GameAgent:mCompareCommonLock	Lcom/google/android/gms/games/broker/Lockable;
    //   81: aastore
    //   82: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	DataBroker
    //   0	87	1	paramGamesClientContext	GamesClientContext
    //   0	87	2	paramInt	int
    //   0	87	3	paramBoolean	boolean
    //   26	11	4	localGameAgent	GameAgent
    // Exception table:
    //   from	to	target	type
    //   22	48	67	finally
  }
  
  /* Error */
  public final DataHolder loadContactSettings(ClientContext paramClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: iload_2
    //   25: invokevirtual 2118	com/google/android/gms/games/broker/PlayerAgent:loadContactSettings	(Lcom/google/android/gms/common/internal/ClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramClientContext	ClientContext
    //   0	62	2	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder loadDisjointGames(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_1
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   15: getfield 2122	com/google/android/gms/games/broker/GameAgent:mCompareDisjointLock	Lcom/google/android/gms/games/broker/Lockable;
    //   18: aastore
    //   19: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   22: aload_0
    //   23: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   26: astore 4
    //   28: aload 4
    //   30: getfield 2122	com/google/android/gms/games/broker/GameAgent:mCompareDisjointLock	Lcom/google/android/gms/games/broker/Lockable;
    //   33: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   36: aload 4
    //   38: aload_1
    //   39: ldc_w 2124
    //   42: iload_2
    //   43: iload_3
    //   44: invokevirtual 2117	com/google/android/gms/games/broker/GameAgent:loadCommonOrDisjointGames	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   47: astore_1
    //   48: iconst_1
    //   49: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   58: getfield 2122	com/google/android/gms/games/broker/GameAgent:mCompareDisjointLock	Lcom/google/android/gms/games/broker/Lockable;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: areturn
    //   67: astore_1
    //   68: iconst_1
    //   69: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   72: dup
    //   73: iconst_0
    //   74: aload_0
    //   75: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   78: getfield 2122	com/google/android/gms/games/broker/GameAgent:mCompareDisjointLock	Lcom/google/android/gms/games/broker/Lockable;
    //   81: aastore
    //   82: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	DataBroker
    //   0	87	1	paramGamesClientContext	GamesClientContext
    //   0	87	2	paramInt	int
    //   0	87	3	paramBoolean	boolean
    //   26	11	4	localGameAgent	GameAgent
    // Exception table:
    //   from	to	target	type
    //   22	48	67	finally
  }
  
  /* Error */
  public final DataHolder loadFriends(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 2128	com/google/android/gms/games/broker/PlayerAgent:mPlayersFriendLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   49: getfield 2128	com/google/android/gms/games/broker/PlayerAgent:mPlayersFriendLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aload_1
    //   63: ldc_w 2130
    //   66: iload_2
    //   67: iload_3
    //   68: invokevirtual 1540	com/google/android/gms/games/broker/PlayerAgent:getFirstPartyPlayers	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   71: astore_1
    //   72: goto -33 -> 39
    //   75: astore_1
    //   76: iconst_1
    //   77: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   80: dup
    //   81: iconst_0
    //   82: aload_0
    //   83: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   86: getfield 2128	com/google/android/gms/games/broker/PlayerAgent:mPlayersFriendLock	Lcom/google/android/gms/games/broker/Lockable;
    //   89: aastore
    //   90: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	DataBroker
    //   0	95	1	paramGamesClientContext	GamesClientContext
    //   0	95	2	paramInt	int
    //   0	95	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   22	39	75	finally
    //   58	72	75	finally
  }
  
  /* Error */
  public final DataHolder loadGameCollection(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   4: iload_3
    //   5: invokevirtual 2136	com/google/android/gms/games/broker/GameAgent:getLockableForCollection	(I)Lcom/google/android/gms/games/broker/Lockable;
    //   8: astore 5
    //   10: iconst_1
    //   11: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   14: dup
    //   15: iconst_0
    //   16: aload 5
    //   18: aastore
    //   19: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   22: iconst_1
    //   23: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   26: pop
    //   27: aload_1
    //   28: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   31: astore_1
    //   32: aload_0
    //   33: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   36: aload_1
    //   37: iload_2
    //   38: iload_3
    //   39: iload 4
    //   41: invokevirtual 2138	com/google/android/gms/games/broker/GameAgent:loadGameCollection	(Lcom/google/android/gms/games/broker/GamesClientContext;IIZ)Lcom/google/android/gms/common/data/DataHolder;
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload 5
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: aload_1
    //   58: areturn
    //   59: astore_1
    //   60: iconst_1
    //   61: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   64: dup
    //   65: iconst_0
    //   66: aload 5
    //   68: aastore
    //   69: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   72: aload_1
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	DataBroker
    //   0	74	1	paramGamesClientContext	GamesClientContext
    //   0	74	2	paramInt1	int
    //   0	74	3	paramInt2	int
    //   0	74	4	paramBoolean	boolean
    //   8	59	5	localLockable	Lockable
    // Exception table:
    //   from	to	target	type
    //   27	45	59	finally
  }
  
  /* Error */
  public final DataHolder loadGameSearchSuggestions(Context paramContext, ClientContext paramClientContext, String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 2142	com/google/android/gms/games/broker/GameAgent:mSearchSuggestionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_0
    //   23: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   26: aload_1
    //   27: aload_2
    //   28: aload_3
    //   29: invokevirtual 2144	com/google/android/gms/games/broker/GameAgent:loadGameSearchSuggestions	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   32: astore_1
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   43: getfield 2142	com/google/android/gms/games/broker/GameAgent:mSearchSuggestionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   63: getfield 2142	com/google/android/gms/games/broker/GameAgent:mSearchSuggestionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   66: aastore
    //   67: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   70: aload_1
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	DataBroker
    //   0	72	1	paramContext	Context
    //   0	72	2	paramClientContext	ClientContext
    //   0	72	3	paramString	String
    // Exception table:
    //   from	to	target	type
    //   22	33	52	finally
  }
  
  /* Error */
  public final DataHolder loadMorePlayerXpStream(GamesClientContext paramGamesClientContext, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: iload_2
    //   25: invokevirtual 2148	com/google/android/gms/games/broker/PlayerAgent:loadMoreXpStream	(Lcom/google/android/gms/games/broker/GamesClientContext;I)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder loadPlayerStats(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 263	com/google/android/gms/games/broker/DataBroker:mStatsAgent	Lcom/google/android/gms/games/broker/StatsAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 263	com/google/android/gms/games/broker/DataBroker:mStatsAgent	Lcom/google/android/gms/games/broker/StatsAgent;
    //   18: aload_1
    //   19: invokevirtual 2151	com/google/android/gms/games/broker/StatsAgent:loadPlayerStats	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   22: astore_1
    //   23: iconst_1
    //   24: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   27: dup
    //   28: iconst_0
    //   29: aload_0
    //   30: getfield 263	com/google/android/gms/games/broker/DataBroker:mStatsAgent	Lcom/google/android/gms/games/broker/StatsAgent;
    //   33: aastore
    //   34: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   37: aload_1
    //   38: areturn
    //   39: astore_1
    //   40: iconst_1
    //   41: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   44: dup
    //   45: iconst_0
    //   46: aload_0
    //   47: getfield 263	com/google/android/gms/games/broker/DataBroker:mStatsAgent	Lcom/google/android/gms/games/broker/StatsAgent;
    //   50: aastore
    //   51: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   54: aload_1
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	DataBroker
    //   0	56	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   14	23	39	finally
  }
  
  /* Error */
  public final DataHolder loadPlayerXpForGameCategories(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: invokevirtual 2154	com/google/android/gms/games/broker/PlayerAgent:loadPlayerXpForGameCategories	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   27: astore_1
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   55: aastore
    //   56: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	DataBroker
    //   0	61	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	28	44	finally
  }
  
  /* Error */
  public final DataHolder loadPlayerXpStream(GamesClientContext paramGamesClientContext, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: iload_2
    //   25: invokevirtual 2158	com/google/android/gms/games/broker/PlayerAgent:loadXpStream	(Lcom/google/android/gms/games/broker/GamesClientContext;I)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder loadProfileSettings(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   28: aload_1
    //   29: iload_2
    //   30: invokevirtual 2161	com/google/android/gms/games/broker/PlayerAgent:loadProfileSettings	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   33: astore_1
    //   34: iconst_1
    //   35: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   38: dup
    //   39: iconst_0
    //   40: aload_0
    //   41: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	DataBroker
    //   0	67	1	paramGamesClientContext	GamesClientContext
    //   0	67	2	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   19	34	50	finally
  }
  
  /* Error */
  public final DataHolder loadQuests1P(GamesClientContext paramGamesClientContext, int[] paramArrayOfInt, int paramInt, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   25: aload_1
    //   26: invokevirtual 2166	com/google/android/gms/games/broker/EventAgent:sendPendingOps	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   29: istore 5
    //   31: iload 5
    //   33: ifne +257 -> 290
    //   36: aload_0
    //   37: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   40: astore 6
    //   42: aload_1
    //   43: invokevirtual 1940	com/google/android/gms/games/broker/GamesClientContext:assert1P	()V
    //   46: aload_1
    //   47: invokevirtual 2169	com/google/android/gms/games/broker/GamesClientContext:hasTargetGameId	()Z
    //   50: ifeq +48 -> 98
    //   53: aload 6
    //   55: aload_1
    //   56: invokevirtual 2172	com/google/android/gms/games/broker/QuestAgent:listQuests	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   59: istore 5
    //   61: iload 5
    //   63: invokestatic 2176	com/google/android/gms/games/broker/QuestAgent:shouldQueryForQuests	(I)Z
    //   66: ifne +182 -> 248
    //   69: iload 5
    //   71: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   74: astore_1
    //   75: iconst_2
    //   76: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   79: dup
    //   80: iconst_0
    //   81: aload_0
    //   82: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   85: aastore
    //   86: dup
    //   87: iconst_1
    //   88: aload_0
    //   89: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   92: aastore
    //   93: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   96: aload_1
    //   97: areturn
    //   98: aload 6
    //   100: getstatic 2179	com/google/android/gms/games/config/G:tickleSyncThresholdMillis	Lcom/google/android/gms/common/config/GservicesValue;
    //   103: invokevirtual 100	com/google/android/gms/common/config/GservicesValue:get	()Ljava/lang/Object;
    //   106: checkcast 464	java/lang/Long
    //   109: invokevirtual 1742	java/lang/Long:longValue	()J
    //   112: aload_1
    //   113: getfield 2092	com/google/android/gms/games/broker/GamesClientContext:mForceReload	Z
    //   116: invokestatic 2183	com/google/android/gms/games/broker/ApiRateLimitUtil:isSyncRateLimited	(Lcom/google/android/gms/games/broker/Lockable;JZ)Z
    //   119: ifeq +15 -> 134
    //   122: ldc_w 1813
    //   125: ldc_w 2185
    //   128: invokestatic 2188	com/google/android/gms/games/internal/GamesLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: goto +153 -> 284
    //   134: aload 6
    //   136: aload_1
    //   137: aload_1
    //   138: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   141: aload_1
    //   142: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   145: getstatic 2191	com/google/android/gms/games/broker/QuestAgent:SYNC_QUEST_TOKEN_PROJECTION	[Ljava/lang/String;
    //   148: invokestatic 2195	com/google/android/gms/games/broker/Agents:getSyncToken	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;[Ljava/lang/String;)Ljava/lang/String;
    //   151: invokevirtual 2199	com/google/android/gms/games/broker/QuestAgent:syncQuestsFromNetwork	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/games/broker/QuestAgent$SyncNetworkResponse;
    //   154: astore 7
    //   156: ldc_w 1813
    //   159: ldc_w 2201
    //   162: iconst_1
    //   163: anewarray 999	java/lang/Object
    //   166: dup
    //   167: iconst_0
    //   168: aload 7
    //   170: getfield 2206	com/google/android/gms/games/broker/QuestAgent$SyncNetworkResponse:mData	Ljava/util/ArrayList;
    //   173: invokevirtual 362	java/util/ArrayList:size	()I
    //   176: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   179: aastore
    //   180: invokestatic 1003	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   183: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   186: aload 7
    //   188: getfield 2207	com/google/android/gms/games/broker/QuestAgent$SyncNetworkResponse:mStatusCode	I
    //   191: ifeq +13 -> 204
    //   194: aload 7
    //   196: getfield 2207	com/google/android/gms/games/broker/QuestAgent$SyncNetworkResponse:mStatusCode	I
    //   199: istore 5
    //   201: goto -140 -> 61
    //   204: aload 6
    //   206: aload_1
    //   207: aload 7
    //   209: iconst_0
    //   210: invokevirtual 2211	com/google/android/gms/games/broker/QuestAgent:storeQuestEntities	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/broker/QuestAgent$SyncNetworkResponse;Z)Z
    //   213: ifeq +71 -> 284
    //   216: aload 6
    //   218: invokestatic 2214	com/google/android/gms/games/broker/ApiRateLimitUtil:updateSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   221: goto +63 -> 284
    //   224: astore_1
    //   225: iconst_2
    //   226: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   229: dup
    //   230: iconst_0
    //   231: aload_0
    //   232: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   235: aastore
    //   236: dup
    //   237: iconst_1
    //   238: aload_0
    //   239: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   242: aastore
    //   243: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   246: aload_1
    //   247: athrow
    //   248: aload_1
    //   249: aload_2
    //   250: iload_3
    //   251: aload 4
    //   253: iload 5
    //   255: invokestatic 2218	com/google/android/gms/games/broker/QuestAgent:getStateFilteredQuestsDataHolder	(Lcom/google/android/gms/games/broker/GamesClientContext;[II[Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   258: astore_1
    //   259: goto -184 -> 75
    //   262: aload_1
    //   263: aload_2
    //   264: iload_3
    //   265: aload 4
    //   267: iconst_3
    //   268: invokestatic 2218	com/google/android/gms/games/broker/QuestAgent:getStateFilteredQuestsDataHolder	(Lcom/google/android/gms/games/broker/GamesClientContext;[II[Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   271: astore_1
    //   272: goto -197 -> 75
    //   275: iload 5
    //   277: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   280: astore_1
    //   281: goto -206 -> 75
    //   284: iconst_0
    //   285: istore 5
    //   287: goto -226 -> 61
    //   290: iload 5
    //   292: iconst_3
    //   293: if_icmpeq -31 -> 262
    //   296: iload 5
    //   298: bipush 6
    //   300: if_icmpne -25 -> 275
    //   303: goto -41 -> 262
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	306	0	this	DataBroker
    //   0	306	1	paramGamesClientContext	GamesClientContext
    //   0	306	2	paramArrayOfInt	int[]
    //   0	306	3	paramInt	int
    //   0	306	4	paramArrayOfString	String[]
    //   29	272	5	i	int
    //   40	177	6	localQuestAgent	QuestAgent
    //   154	54	7	localSyncNetworkResponse	QuestAgent.SyncNetworkResponse
    // Exception table:
    //   from	to	target	type
    //   21	31	224	finally
    //   36	61	224	finally
    //   61	75	224	finally
    //   98	131	224	finally
    //   134	201	224	finally
    //   204	221	224	finally
    //   248	259	224	finally
    //   262	272	224	finally
    //   275	281	224	finally
  }
  
  /* Error */
  public final DataHolder loadQuests3P(GamesClientContext paramGamesClientContext, int[] paramArrayOfInt, int paramInt, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: aload_0
    //   22: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   25: aload_1
    //   26: invokevirtual 720	com/google/android/gms/games/broker/EventAgent:submitPendingEventsForGame	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   29: istore 5
    //   31: iload 5
    //   33: ifne +138 -> 171
    //   36: aload_0
    //   37: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   40: astore 7
    //   42: aload_1
    //   43: getfield 1207	com/google/android/gms/games/broker/GamesClientContext:mIsFirstParty	Z
    //   46: ifne +59 -> 105
    //   49: iconst_1
    //   50: istore 6
    //   52: iload 6
    //   54: ldc_w 2221
    //   57: invokestatic 1215	com/google/android/gms/common/internal/Preconditions:checkArgument	(ZLjava/lang/Object;)V
    //   60: aload 7
    //   62: aload_1
    //   63: invokevirtual 2172	com/google/android/gms/games/broker/QuestAgent:listQuests	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   66: istore 5
    //   68: iload 5
    //   70: invokestatic 2176	com/google/android/gms/games/broker/QuestAgent:shouldQueryForQuests	(I)Z
    //   73: ifne +38 -> 111
    //   76: iload 5
    //   78: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   81: astore_1
    //   82: iconst_2
    //   83: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   86: dup
    //   87: iconst_0
    //   88: aload_0
    //   89: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   92: aastore
    //   93: dup
    //   94: iconst_1
    //   95: aload_0
    //   96: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   99: aastore
    //   100: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   103: aload_1
    //   104: areturn
    //   105: iconst_0
    //   106: istore 6
    //   108: goto -56 -> 52
    //   111: aload_1
    //   112: aload_2
    //   113: iload_3
    //   114: aload 4
    //   116: iload 5
    //   118: invokestatic 2218	com/google/android/gms/games/broker/QuestAgent:getStateFilteredQuestsDataHolder	(Lcom/google/android/gms/games/broker/GamesClientContext;[II[Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   121: astore_1
    //   122: goto -40 -> 82
    //   125: aload_1
    //   126: aload_2
    //   127: iload_3
    //   128: aload 4
    //   130: iconst_3
    //   131: invokestatic 2218	com/google/android/gms/games/broker/QuestAgent:getStateFilteredQuestsDataHolder	(Lcom/google/android/gms/games/broker/GamesClientContext;[II[Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   134: astore_1
    //   135: goto -53 -> 82
    //   138: iload 5
    //   140: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   143: astore_1
    //   144: goto -62 -> 82
    //   147: astore_1
    //   148: iconst_2
    //   149: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   152: dup
    //   153: iconst_0
    //   154: aload_0
    //   155: getfield 243	com/google/android/gms/games/broker/DataBroker:mQuestAgent	Lcom/google/android/gms/games/broker/QuestAgent;
    //   158: aastore
    //   159: dup
    //   160: iconst_1
    //   161: aload_0
    //   162: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   165: aastore
    //   166: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   169: aload_1
    //   170: athrow
    //   171: iload 5
    //   173: iconst_3
    //   174: if_icmpeq -49 -> 125
    //   177: iload 5
    //   179: bipush 6
    //   181: if_icmpne -43 -> 138
    //   184: goto -59 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	this	DataBroker
    //   0	187	1	paramGamesClientContext	GamesClientContext
    //   0	187	2	paramArrayOfInt	int[]
    //   0	187	3	paramInt	int
    //   0	187	4	paramArrayOfString	String[]
    //   29	153	5	i	int
    //   50	57	6	bool	boolean
    //   40	21	7	localQuestAgent	QuestAgent
    // Exception table:
    //   from	to	target	type
    //   21	31	147	finally
    //   36	49	147	finally
    //   52	82	147	finally
    //   111	122	147	finally
    //   125	135	147	finally
    //   138	144	147	finally
  }
  
  /* Error */
  public final DataHolder loadRecentlyPlayedGames(GamesClientContext paramGamesClientContext, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 2225	com/google/android/gms/games/broker/GameAgent:mRecentlyPlayedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   49: getfield 2225	com/google/android/gms/games/broker/GameAgent:mRecentlyPlayedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   62: astore 4
    //   64: aload 4
    //   66: getfield 2225	com/google/android/gms/games/broker/GameAgent:mRecentlyPlayedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   69: invokevirtual 679	com/google/android/gms/games/broker/Lockable:assertLockedByCurrentThread	()V
    //   72: aload_1
    //   73: getfield 422	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetPlayerId	Ljava/lang/String;
    //   76: ifnull +10 -> 86
    //   79: aload_1
    //   80: invokevirtual 2228	com/google/android/gms/games/broker/GamesClientContext:isTargetingCurrentPlayer	()Z
    //   83: ifeq +15 -> 98
    //   86: aload 4
    //   88: aload_1
    //   89: iload_2
    //   90: iload_3
    //   91: invokevirtual 2231	com/google/android/gms/games/broker/GameAgent:loadRecentlyPlayedGamesForSignedInUser	(Lcom/google/android/gms/games/broker/GamesClientContext;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   94: astore_1
    //   95: goto +36 -> 131
    //   98: aload 4
    //   100: aload_1
    //   101: iload_2
    //   102: iconst_3
    //   103: iload_3
    //   104: invokevirtual 2138	com/google/android/gms/games/broker/GameAgent:loadGameCollection	(Lcom/google/android/gms/games/broker/GamesClientContext;IIZ)Lcom/google/android/gms/common/data/DataHolder;
    //   107: astore_1
    //   108: goto +23 -> 131
    //   111: astore_1
    //   112: iconst_1
    //   113: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   116: dup
    //   117: iconst_0
    //   118: aload_0
    //   119: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   122: getfield 2225	com/google/android/gms/games/broker/GameAgent:mRecentlyPlayedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   125: aastore
    //   126: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   129: aload_1
    //   130: athrow
    //   131: goto -92 -> 39
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	DataBroker
    //   0	134	1	paramGamesClientContext	GamesClientContext
    //   0	134	2	paramInt	int
    //   0	134	3	paramBoolean	boolean
    //   62	37	4	localGameAgent	GameAgent
    // Exception table:
    //   from	to	target	type
    //   22	39	111	finally
    //   58	86	111	finally
    //   86	95	111	finally
    //   98	108	111	finally
  }
  
  /* Error */
  public final DataHolder loadSelf(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   28: aload_1
    //   29: iconst_1
    //   30: invokevirtual 443	com/google/android/gms/games/broker/PlayerAgent:fetchPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   33: astore_1
    //   34: iconst_1
    //   35: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   38: dup
    //   39: iconst_0
    //   40: aload_0
    //   41: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	DataBroker
    //   0	67	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	34	50	finally
  }
  
  /* Error */
  public final DataHolder loadSnapshots(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   23: aload_1
    //   24: aload_2
    //   25: invokevirtual 2236	com/google/android/gms/games/broker/SnapshotAgent:loadSnapshots	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;)Lcom/google/android/gms/common/data/DataHolder;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    // Exception table:
    //   from	to	target	type
    //   19	29	45	finally
  }
  
  /* Error */
  public final DataHolder loadSocialInvites(GamesClientContext paramGamesClientContext, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   28: aload_1
    //   29: iload_2
    //   30: invokevirtual 2239	com/google/android/gms/games/broker/SocialAgent:loadSocialInvites	(Lcom/google/android/gms/games/broker/GamesClientContext;I)Lcom/google/android/gms/common/data/DataHolder;
    //   33: astore_1
    //   34: iconst_1
    //   35: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   38: dup
    //   39: iconst_0
    //   40: aload_0
    //   41: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: iconst_1
    //   52: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   55: dup
    //   56: iconst_0
    //   57: aload_0
    //   58: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   61: aastore
    //   62: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	DataBroker
    //   0	67	1	paramGamesClientContext	GamesClientContext
    //   0	67	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   19	34	50	finally
  }
  
  /* Error */
  public final DataHolder loadStockProfileImages(GamesClientContext paramGamesClientContext)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: invokevirtual 2242	com/google/android/gms/games/broker/PlayerAgent:loadStockProfileImages	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/common/data/DataHolder;
    //   27: astore_1
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   55: aastore
    //   56: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	DataBroker
    //   0	61	1	paramGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	28	44	finally
  }
  
  /* Error */
  public final com.google.android.gms.games.service.operations.snapshots.OpenSnapshotOperation.SnapshotOpenData openSnapshot(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean, int paramInt)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: aload_3
    //   21: iload 4
    //   23: iload 5
    //   25: invokevirtual 2246	com/google/android/gms/games/broker/SnapshotAgent:openSnapshot	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;Ljava/lang/String;ZI)Lcom/google/android/gms/games/service/operations/snapshots/OpenSnapshotOperation$SnapshotOpenData;
    //   28: astore_1
    //   29: iconst_1
    //   30: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   39: aastore
    //   40: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   43: aload_1
    //   44: areturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramGamesClientContext	GamesClientContext
    //   0	62	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   0	62	3	paramString	String
    //   0	62	4	paramBoolean	boolean
    //   0	62	5	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   14	29	45	finally
  }
  
  /* Error */
  public final DataHolder p2pStatus(Context paramContext, ClientContext paramClientContext, String paramString, List<com.google.android.gms.games.server.api.RoomP2PStatus> paramList)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: aload_3
    //   21: aload 4
    //   23: invokevirtual 2250	com/google/android/gms/games/broker/RealTimeAgent:p2pStatus	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Ljava/util/List;)Lcom/google/android/gms/common/data/DataHolder;
    //   26: astore_1
    //   27: iconst_1
    //   28: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   31: dup
    //   32: iconst_0
    //   33: aload_0
    //   34: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   37: aastore
    //   38: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   41: aload_1
    //   42: areturn
    //   43: astore_1
    //   44: iconst_1
    //   45: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   54: aastore
    //   55: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   58: aload_1
    //   59: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	this	DataBroker
    //   0	60	1	paramContext	Context
    //   0	60	2	paramClientContext	ClientContext
    //   0	60	3	paramString	String
    //   0	60	4	paramList	List<com.google.android.gms.games.server.api.RoomP2PStatus>
    // Exception table:
    //   from	to	target	type
    //   14	27	43	finally
  }
  
  /* Error */
  public final int recordApplicationSession(GamesClientContext paramGamesClientContext, String paramString1, long paramLong1, long paramLong2, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 2256	com/google/android/gms/games/broker/GameAgent:mApplicationSessionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: aload_1
    //   18: aload_2
    //   19: lload_3
    //   20: lload 5
    //   22: aload 7
    //   24: iload 8
    //   26: invokestatic 2258	com/google/android/gms/games/broker/GameAgent:recordApplicationSession	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;JJLjava/lang/String;Z)I
    //   29: pop
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   40: getfield 2256	com/google/android/gms/games/broker/GameAgent:mApplicationSessionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   43: aastore
    //   44: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   47: iconst_0
    //   48: ireturn
    //   49: astore_1
    //   50: iconst_1
    //   51: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   60: getfield 2256	com/google/android/gms/games/broker/GameAgent:mApplicationSessionLock	Lcom/google/android/gms/games/broker/Lockable;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	DataBroker
    //   0	69	1	paramGamesClientContext	GamesClientContext
    //   0	69	2	paramString1	String
    //   0	69	3	paramLong1	long
    //   0	69	5	paramLong2	long
    //   0	69	7	paramString2	String
    //   0	69	8	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   17	30	49	finally
  }
  
  /* Error */
  public final int recordPlayerSuggestionAction$11b7dad(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_2
    //   18: istore_2
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   26: ifnull +7 -> 33
    //   29: invokestatic 2261	com/google/android/gms/games/broker/PlayerAgent:recordPlayerSuggestionAction$11b7dad	()I
    //   32: istore_2
    //   33: iconst_1
    //   34: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   43: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: iload_2
    //   51: ireturn
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   63: getfield 956	com/google/android/gms/games/broker/PlayerAgent:mPlayersSuggestedLock	Lcom/google/android/gms/games/broker/Lockable;
    //   66: aastore
    //   67: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   70: aload_1
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	DataBroker
    //   0	72	1	paramGamesClientContext	GamesClientContext
    //   18	33	2	i	int
    // Exception table:
    //   from	to	target	type
    //   19	33	52	finally
  }
  
  /* Error */
  public final int recordSignIn(Context paramContext, ClientContext paramClientContext, String paramString1, String paramString2, Integer paramInteger)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: aload_2
    //   16: aload_3
    //   17: aload 4
    //   19: aload 5
    //   21: invokestatic 2265	com/google/android/gms/games/broker/AccountAgent:recordSignIn	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)I
    //   24: istore 6
    //   26: iconst_1
    //   27: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   30: dup
    //   31: iconst_0
    //   32: aload_0
    //   33: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   36: aastore
    //   37: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   40: iload 6
    //   42: ireturn
    //   43: astore_1
    //   44: iconst_1
    //   45: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: getfield 190	com/google/android/gms/games/broker/DataBroker:mAccountAgent	Lcom/google/android/gms/games/broker/AccountAgent;
    //   54: aastore
    //   55: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   58: aload_1
    //   59: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	this	DataBroker
    //   0	60	1	paramContext	Context
    //   0	60	2	paramClientContext	ClientContext
    //   0	60	3	paramString1	String
    //   0	60	4	paramString2	String
    //   0	60	5	paramInteger	Integer
    //   24	17	6	i	int
    // Exception table:
    //   from	to	target	type
    //   14	26	43	finally
  }
  
  /* Error */
  public final int recordSnapshotConflict(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient, com.google.android.gms.games.snapshot.SnapshotMetadataChange paramSnapshotMetadataChange, com.google.android.gms.drive.DriveContents paramDriveContents)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 2271	com/google/android/gms/games/broker/SnapshotAgent:getSnapshotFolder	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;)Lcom/google/android/gms/games/broker/SnapshotAgent$ResultPair;
    //   23: astore 6
    //   25: aload 6
    //   27: getfield 2276	com/google/android/gms/games/broker/SnapshotAgent$ResultPair:result	Ljava/lang/Object;
    //   30: checkcast 2278	com/google/android/gms/drive/DriveFolder
    //   33: astore 7
    //   35: aload 7
    //   37: ifnonnull +56 -> 93
    //   40: ldc_w 902
    //   43: new 153	java/lang/StringBuilder
    //   46: dup
    //   47: ldc_w 2280
    //   50: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   53: aload_1
    //   54: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   57: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: aload 6
    //   68: getfield 2284	com/google/android/gms/games/broker/SnapshotAgent$ResultPair:status	Lcom/google/android/gms/common/api/Status;
    //   71: getfield 2287	com/google/android/gms/common/api/Status:mStatusCode	I
    //   74: istore 5
    //   76: iconst_1
    //   77: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   80: dup
    //   81: iconst_0
    //   82: aload_0
    //   83: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   86: aastore
    //   87: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   90: iload 5
    //   92: ireturn
    //   93: aload_1
    //   94: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   97: astore 10
    //   99: aload_1
    //   100: invokestatic 2290	com/google/android/gms/games/broker/SnapshotAgent:getLoadUri	(Lcom/google/android/gms/games/broker/GamesClientContext;)Landroid/net/Uri;
    //   103: astore 6
    //   105: aload_1
    //   106: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   109: aload 6
    //   111: ldc_w 2292
    //   114: invokestatic 1656	com/google/android/gms/games/broker/Agents:queryString$510a9928	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;)Ljava/lang/String;
    //   117: astore 8
    //   119: aload_1
    //   120: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   123: aload 6
    //   125: ldc_w 2294
    //   128: invokestatic 1656	com/google/android/gms/games/broker/Agents:queryString$510a9928	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;)Ljava/lang/String;
    //   131: astore 6
    //   133: aload_1
    //   134: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   137: astore 9
    //   139: aload 10
    //   141: getfield 918	com/google/android/gms/common/internal/ClientContext:mCallingPackageName	Ljava/lang/String;
    //   144: astore 10
    //   146: aload_1
    //   147: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   150: astore 11
    //   152: aload_1
    //   153: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   156: getfield 922	com/google/android/gms/common/internal/ClientContext:mResolvedAccount	Landroid/accounts/Account;
    //   159: astore 12
    //   161: aload 6
    //   163: ifnonnull +198 -> 361
    //   166: ldc_w 2296
    //   169: astore_1
    //   170: aload 9
    //   172: aload 10
    //   174: aload 11
    //   176: aload 12
    //   178: iconst_5
    //   179: aload_1
    //   180: iconst_4
    //   181: aload 4
    //   183: invokestatic 2300	com/google/android/gms/games/broker/SnapshotAgent:getContentSizeBytes	(Lcom/google/android/gms/drive/DriveContents;)J
    //   186: invokestatic 930	com/google/android/gms/games/logging/GamesPlayLogger:logSnapshotAction	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;ILjava/lang/String;IJ)V
    //   189: aload 8
    //   191: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   194: ifne +11 -> 205
    //   197: aload 6
    //   199: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   202: ifeq +15 -> 217
    //   205: ldc_w 902
    //   208: ldc_w 2302
    //   211: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   214: goto +141 -> 355
    //   217: aload_3
    //   218: getstatic 2305	com/google/android/gms/games/config/G:snapshotConflictMimeType	Lcom/google/android/gms/common/config/GservicesValue;
    //   221: invokevirtual 100	com/google/android/gms/common/config/GservicesValue:get	()Ljava/lang/Object;
    //   224: checkcast 129	java/lang/String
    //   227: aload_2
    //   228: invokestatic 2309	com/google/android/gms/games/broker/SnapshotAgent:getDeviceName$3565d17f	(Lcom/google/android/gms/common/api/GoogleApiClient;)Ljava/lang/String;
    //   231: invokestatic 2313	com/google/android/gms/games/broker/SnapshotAgent:convertSnapshotToDriveChange	(Lcom/google/android/gms/games/snapshot/SnapshotMetadataChange;Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/drive/MetadataChangeSet$Builder;
    //   234: astore_1
    //   235: aload_1
    //   236: aload 8
    //   238: invokevirtual 2319	com/google/android/gms/drive/MetadataChangeSet$Builder:setTitle	(Ljava/lang/String;)Lcom/google/android/gms/drive/MetadataChangeSet$Builder;
    //   241: pop
    //   242: aload_1
    //   243: new 2321	com/google/android/gms/drive/metadata/CustomPropertyKey
    //   246: dup
    //   247: ldc_w 2323
    //   250: iconst_0
    //   251: invokespecial 2325	com/google/android/gms/drive/metadata/CustomPropertyKey:<init>	(Ljava/lang/String;I)V
    //   254: aload 8
    //   256: invokestatic 2329	com/google/android/gms/games/broker/SnapshotAgent:setCustomProperty	(Lcom/google/android/gms/drive/MetadataChangeSet$Builder;Lcom/google/android/gms/drive/metadata/CustomPropertyKey;Ljava/lang/String;)Lcom/google/android/gms/drive/MetadataChangeSet$Builder;
    //   259: pop
    //   260: aload 7
    //   262: aload_2
    //   263: aload_1
    //   264: invokevirtual 2332	com/google/android/gms/drive/MetadataChangeSet$Builder:build	()Lcom/google/android/gms/drive/MetadataChangeSet;
    //   267: aload 4
    //   269: invokeinterface 2336 4 0
    //   274: invokevirtual 2342	com/google/android/gms/common/api/PendingResult:await	()Lcom/google/android/gms/common/api/Result;
    //   277: checkcast 2344	com/google/android/gms/drive/DriveFolder$DriveFileResult
    //   280: astore_1
    //   281: aload_1
    //   282: invokeinterface 2348 1 0
    //   287: invokevirtual 2351	com/google/android/gms/common/api/Status:isSuccess	()Z
    //   290: ifne +65 -> 355
    //   293: ldc_w 902
    //   296: new 153	java/lang/StringBuilder
    //   299: dup
    //   300: ldc_w 2353
    //   303: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   306: aload_1
    //   307: invokeinterface 2348 1 0
    //   312: invokevirtual 2356	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   315: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: aload_1
    //   322: invokeinterface 2348 1 0
    //   327: getfield 2287	com/google/android/gms/common/api/Status:mStatusCode	I
    //   330: invokestatic 2359	com/google/android/gms/games/broker/SnapshotAgent:remapCommonStatusCode	(I)I
    //   333: istore 5
    //   335: goto -259 -> 76
    //   338: astore_1
    //   339: iconst_1
    //   340: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   343: dup
    //   344: iconst_0
    //   345: aload_0
    //   346: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   349: aastore
    //   350: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   353: aload_1
    //   354: athrow
    //   355: iconst_0
    //   356: istore 5
    //   358: goto -282 -> 76
    //   361: aload 6
    //   363: astore_1
    //   364: goto -194 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	367	0	this	DataBroker
    //   0	367	1	paramGamesClientContext	GamesClientContext
    //   0	367	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   0	367	3	paramSnapshotMetadataChange	com.google.android.gms.games.snapshot.SnapshotMetadataChange
    //   0	367	4	paramDriveContents	com.google.android.gms.drive.DriveContents
    //   74	283	5	i	int
    //   23	339	6	localObject1	Object
    //   33	228	7	localDriveFolder	com.google.android.gms.drive.DriveFolder
    //   117	138	8	str1	String
    //   137	34	9	localContext	Context
    //   97	76	10	localObject2	Object
    //   150	25	11	str2	String
    //   159	18	12	localAccount	android.accounts.Account
    // Exception table:
    //   from	to	target	type
    //   14	35	338	finally
    //   40	76	338	finally
    //   93	161	338	finally
    //   170	205	338	finally
    //   205	214	338	finally
    //   217	335	338	finally
  }
  
  /* Error */
  public final void refreshGameData(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   23: invokevirtual 2364	com/google/android/gms/games/broker/GameAgent:getGamesToRefresh	(Lcom/google/android/gms/games/broker/GamesClientContext;Landroid/content/SyncResult;)Ljava/util/ArrayList;
    //   26: astore 10
    //   28: aload_2
    //   29: bipush 17
    //   31: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   34: iconst_1
    //   35: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   38: dup
    //   39: iconst_0
    //   40: aload_0
    //   41: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   44: aastore
    //   45: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   48: aload 10
    //   50: invokevirtual 362	java/util/ArrayList:size	()I
    //   53: ifne +21 -> 74
    //   56: return
    //   57: astore_1
    //   58: iconst_1
    //   59: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   62: dup
    //   63: iconst_0
    //   64: aload_0
    //   65: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   68: aastore
    //   69: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   72: aload_1
    //   73: athrow
    //   74: aload_1
    //   75: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   78: astore_1
    //   79: aload 10
    //   81: invokevirtual 362	java/util/ArrayList:size	()I
    //   84: istore 7
    //   86: new 81	java/util/ArrayList
    //   89: dup
    //   90: iload 7
    //   92: invokespecial 978	java/util/ArrayList:<init>	(I)V
    //   95: astore 11
    //   97: iconst_0
    //   98: istore 4
    //   100: iload 4
    //   102: iload 7
    //   104: if_icmpge +667 -> 771
    //   107: iconst_3
    //   108: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   111: dup
    //   112: iconst_0
    //   113: aload_0
    //   114: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   117: aastore
    //   118: dup
    //   119: iconst_1
    //   120: aload_0
    //   121: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   124: aastore
    //   125: dup
    //   126: iconst_2
    //   127: aload_0
    //   128: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   131: aastore
    //   132: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   135: aload 10
    //   137: iload 4
    //   139: invokevirtual 365	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   142: checkcast 2366	com/google/android/gms/games/broker/GameAgent$GameRefreshRecord
    //   145: astore 12
    //   147: aload 12
    //   149: getfield 2369	com/google/android/gms/games/broker/GameAgent$GameRefreshRecord:mExternalGameId	Ljava/lang/String;
    //   152: astore 13
    //   154: aload_1
    //   155: invokevirtual 392	com/google/android/gms/games/broker/GamesClientContext:getBuilder	()Lcom/google/android/gms/games/broker/GamesClientContext$Builder;
    //   158: astore 14
    //   160: aload 14
    //   162: aload 13
    //   164: putfield 1543	com/google/android/gms/games/broker/GamesClientContext$Builder:mExternalTargetGameId	Ljava/lang/String;
    //   167: aload 14
    //   169: invokevirtual 399	com/google/android/gms/games/broker/GamesClientContext$Builder:build	()Lcom/google/android/gms/games/broker/GamesClientContext;
    //   172: astore 14
    //   174: aload 12
    //   176: getfield 2373	com/google/android/gms/games/broker/GameAgent$GameRefreshRecord:mAppUpdates	Ljava/util/HashSet;
    //   179: astore 15
    //   181: iconst_1
    //   182: istore 6
    //   184: iconst_0
    //   185: istore 5
    //   187: iload 5
    //   189: getstatic 2378	com/google/android/gms/games/constants/AppUpdateType:ALL_TYPES	[Ljava/lang/String;
    //   192: arraylength
    //   193: if_icmpge +502 -> 695
    //   196: getstatic 2378	com/google/android/gms/games/constants/AppUpdateType:ALL_TYPES	[Ljava/lang/String;
    //   199: iload 5
    //   201: aaload
    //   202: astore 16
    //   204: iload 6
    //   206: istore_3
    //   207: aload 15
    //   209: aload 16
    //   211: invokeinterface 2381 2 0
    //   216: ifeq +665 -> 881
    //   219: iconst_m1
    //   220: istore_3
    //   221: aload 16
    //   223: invokevirtual 2384	java/lang/String:hashCode	()I
    //   226: lookupswitch	default:+610->836, -292133702:+114->340, 810912824:+82->308, 1528308867:+50->276, 1713155957:+66->292, 1965593541:+98->324
    //   276: aload 16
    //   278: ldc_w 2386
    //   281: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   284: ifeq +552 -> 836
    //   287: iconst_0
    //   288: istore_3
    //   289: goto +547 -> 836
    //   292: aload 16
    //   294: ldc_w 2388
    //   297: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   300: ifeq +536 -> 836
    //   303: iconst_1
    //   304: istore_3
    //   305: goto +531 -> 836
    //   308: aload 16
    //   310: ldc_w 2390
    //   313: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   316: ifeq +520 -> 836
    //   319: iconst_2
    //   320: istore_3
    //   321: goto +515 -> 836
    //   324: aload 16
    //   326: ldc_w 2392
    //   329: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   332: ifeq +504 -> 836
    //   335: iconst_3
    //   336: istore_3
    //   337: goto +499 -> 836
    //   340: aload 16
    //   342: ldc_w 2394
    //   345: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   348: ifeq +488 -> 836
    //   351: iconst_4
    //   352: istore_3
    //   353: goto +483 -> 836
    //   356: aload_0
    //   357: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   360: astore 16
    //   362: aload_2
    //   363: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   366: astore 17
    //   368: aload 14
    //   370: getfield 2397	com/google/android/gms/games/broker/GamesClientContext:mIsFirstPartyBackground	Z
    //   373: ldc_w 2399
    //   376: invokestatic 1215	com/google/android/gms/common/internal/Preconditions:checkArgument	(ZLjava/lang/Object;)V
    //   379: aload 16
    //   381: aload 14
    //   383: invokevirtual 2402	com/google/android/gms/games/broker/AchievementAgent:refreshDefinitions	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   386: ifne +97 -> 483
    //   389: iconst_1
    //   390: istore 8
    //   392: iload 8
    //   394: ifne +49 -> 443
    //   397: ldc_w 2404
    //   400: new 153	java/lang/StringBuilder
    //   403: dup
    //   404: ldc_w 2406
    //   407: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   410: aload 14
    //   412: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   415: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   421: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   424: aload 17
    //   426: getfield 2410	android/content/SyncResult:stats	Landroid/content/SyncStats;
    //   429: astore 16
    //   431: aload 16
    //   433: aload 16
    //   435: getfield 2415	android/content/SyncStats:numIoExceptions	J
    //   438: lconst_1
    //   439: ladd
    //   440: putfield 2415	android/content/SyncStats:numIoExceptions	J
    //   443: aload_2
    //   444: bipush 20
    //   446: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   449: goto +426 -> 875
    //   452: astore_1
    //   453: iconst_3
    //   454: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   457: dup
    //   458: iconst_0
    //   459: aload_0
    //   460: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   463: aastore
    //   464: dup
    //   465: iconst_1
    //   466: aload_0
    //   467: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   470: aastore
    //   471: dup
    //   472: iconst_2
    //   473: aload_0
    //   474: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   477: aastore
    //   478: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   481: aload_1
    //   482: athrow
    //   483: iconst_0
    //   484: istore 8
    //   486: goto -94 -> 392
    //   489: aload_0
    //   490: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   493: aload 14
    //   495: aload_2
    //   496: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   499: invokevirtual 2419	com/google/android/gms/games/broker/LeaderboardAgent:syncLeaderboards	(Lcom/google/android/gms/games/broker/GamesClientContext;Landroid/content/SyncResult;)Z
    //   502: istore 8
    //   504: aload_2
    //   505: bipush 24
    //   507: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   510: goto +365 -> 875
    //   513: aload_0
    //   514: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   517: aload 14
    //   519: aload_2
    //   520: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   523: invokevirtual 2422	com/google/android/gms/games/broker/EventAgent:syncEventDefinitions	(Lcom/google/android/gms/games/broker/GamesClientContext;Landroid/content/SyncResult;)Z
    //   526: istore 8
    //   528: aload_2
    //   529: bipush 22
    //   531: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   534: goto +341 -> 875
    //   537: aload_0
    //   538: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   541: astore 16
    //   543: aload_2
    //   544: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   547: astore 17
    //   549: aload 14
    //   551: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   554: ifnonnull +15 -> 569
    //   557: iconst_1
    //   558: istore 8
    //   560: aload_2
    //   561: bipush 21
    //   563: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   566: goto +309 -> 875
    //   569: aload 14
    //   571: getfield 2397	com/google/android/gms/games/broker/GamesClientContext:mIsFirstPartyBackground	Z
    //   574: ldc_w 2399
    //   577: invokestatic 1215	com/google/android/gms/common/internal/Preconditions:checkArgument	(ZLjava/lang/Object;)V
    //   580: aload 16
    //   582: aload 14
    //   584: invokevirtual 2425	com/google/android/gms/games/broker/AchievementAgent:flushPendingOps	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   587: ifne +306 -> 893
    //   590: iconst_1
    //   591: istore_3
    //   592: iload_3
    //   593: ifeq +305 -> 898
    //   596: aload 16
    //   598: aload 14
    //   600: invokevirtual 2428	com/google/android/gms/games/broker/AchievementAgent:refreshInstances	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   603: ifne +295 -> 898
    //   606: iconst_1
    //   607: istore 9
    //   609: iload 9
    //   611: istore 8
    //   613: iload 9
    //   615: ifne -55 -> 560
    //   618: ldc_w 2404
    //   621: new 153	java/lang/StringBuilder
    //   624: dup
    //   625: ldc_w 2430
    //   628: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   631: aload 14
    //   633: getfield 580	com/google/android/gms/games/broker/GamesClientContext:mExternalTargetGameId	Ljava/lang/String;
    //   636: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   642: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   645: aload 17
    //   647: getfield 2410	android/content/SyncResult:stats	Landroid/content/SyncStats;
    //   650: astore 16
    //   652: aload 16
    //   654: aload 16
    //   656: getfield 2415	android/content/SyncStats:numIoExceptions	J
    //   659: lconst_1
    //   660: ladd
    //   661: putfield 2415	android/content/SyncStats:numIoExceptions	J
    //   664: iload 9
    //   666: istore 8
    //   668: goto -108 -> 560
    //   671: aload_0
    //   672: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   675: aload 14
    //   677: aload_2
    //   678: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   681: invokevirtual 2433	com/google/android/gms/games/broker/EventAgent:syncInstances	(Lcom/google/android/gms/games/broker/GamesClientContext;Landroid/content/SyncResult;)Z
    //   684: istore 8
    //   686: aload_2
    //   687: bipush 23
    //   689: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   692: goto +183 -> 875
    //   695: iload 6
    //   697: ifeq +48 -> 745
    //   700: aload 11
    //   702: aload 12
    //   704: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   707: pop
    //   708: iconst_3
    //   709: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   712: dup
    //   713: iconst_0
    //   714: aload_0
    //   715: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   718: aastore
    //   719: dup
    //   720: iconst_1
    //   721: aload_0
    //   722: getfield 209	com/google/android/gms/games/broker/DataBroker:mEventAgent	Lcom/google/android/gms/games/broker/EventAgent;
    //   725: aastore
    //   726: dup
    //   727: iconst_2
    //   728: aload_0
    //   729: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   732: aastore
    //   733: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   736: iload 4
    //   738: iconst_1
    //   739: iadd
    //   740: istore 4
    //   742: goto -642 -> 100
    //   745: ldc 70
    //   747: new 153	java/lang/StringBuilder
    //   750: dup
    //   751: ldc_w 2435
    //   754: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   757: aload 13
    //   759: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   765: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   768: goto -60 -> 708
    //   771: iconst_1
    //   772: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   775: dup
    //   776: iconst_0
    //   777: aload_0
    //   778: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   781: aastore
    //   782: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   785: aload_1
    //   786: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   789: aload_1
    //   790: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   793: aload 11
    //   795: invokestatic 2438	com/google/android/gms/games/broker/GameAgent:updateMetadataVersions	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/util/ArrayList;)V
    //   798: aload_2
    //   799: bipush 18
    //   801: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   804: iconst_1
    //   805: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   808: dup
    //   809: iconst_0
    //   810: aload_0
    //   811: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   814: aastore
    //   815: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   818: return
    //   819: astore_1
    //   820: iconst_1
    //   821: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   824: dup
    //   825: iconst_0
    //   826: aload_0
    //   827: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   830: aastore
    //   831: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   834: aload_1
    //   835: athrow
    //   836: iload_3
    //   837: tableswitch	default:+35->872, 0:+-481->356, 1:+-348->489, 2:+-324->513, 3:+-300->537, 4:+-166->671
    //   872: iconst_1
    //   873: istore 8
    //   875: iload 6
    //   877: iload 8
    //   879: iand
    //   880: istore_3
    //   881: iload 5
    //   883: iconst_1
    //   884: iadd
    //   885: istore 5
    //   887: iload_3
    //   888: istore 6
    //   890: goto -703 -> 187
    //   893: iconst_0
    //   894: istore_3
    //   895: goto -303 -> 592
    //   898: iconst_0
    //   899: istore 9
    //   901: goto -292 -> 609
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	904	0	this	DataBroker
    //   0	904	1	paramGamesClientContext	GamesClientContext
    //   0	904	2	paramGamesSyncResult	GamesSyncAdapter.GamesSyncResult
    //   206	689	3	i	int
    //   98	643	4	j	int
    //   185	701	5	k	int
    //   182	707	6	m	int
    //   84	21	7	n	int
    //   390	490	8	bool1	boolean
    //   607	293	9	bool2	boolean
    //   26	110	10	localArrayList1	ArrayList
    //   95	699	11	localArrayList2	ArrayList
    //   145	558	12	localGameRefreshRecord	GameAgent.GameRefreshRecord
    //   152	606	13	str	String
    //   158	518	14	localObject1	Object
    //   179	29	15	localHashSet	HashSet
    //   202	453	16	localObject2	Object
    //   366	280	17	localSyncResult	SyncResult
    // Exception table:
    //   from	to	target	type
    //   14	34	57	finally
    //   135	181	452	finally
    //   187	204	452	finally
    //   207	219	452	finally
    //   221	276	452	finally
    //   276	287	452	finally
    //   292	303	452	finally
    //   308	319	452	finally
    //   324	335	452	finally
    //   340	351	452	finally
    //   356	389	452	finally
    //   397	443	452	finally
    //   443	449	452	finally
    //   489	510	452	finally
    //   513	534	452	finally
    //   537	557	452	finally
    //   560	566	452	finally
    //   569	590	452	finally
    //   596	606	452	finally
    //   618	664	452	finally
    //   671	692	452	finally
    //   700	708	452	finally
    //   745	768	452	finally
    //   785	804	819	finally
  }
  
  public final int refreshQuests(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    paramGamesClientContext = fetchPlayerIdFromAccountWhenMissing(paramGamesClientContext);
    acquireLocks(new Lockable[] { this.mQuestAgent });
    Object localObject1;
    label163:
    Object localObject3;
    for (;;)
    {
      try
      {
        localObject1 = paramGamesClientContext.mExternalCurrentPlayerId;
        if (localObject1 == null)
        {
          i = 2;
          releaseLocks(new Lockable[] { this.mQuestAgent });
          registerQuestAlarmsForAccount(paramGamesClientContext.mContext, paramGamesClientContext.mClientContext);
          if ((i == 4) || (i == 3) || (i == 500))
          {
            paramGamesClientContext = paramGamesSyncResult.syncResult.stats;
            paramGamesClientContext.numIoExceptions += 1L;
          }
          return i;
        }
        localObject1 = this.mQuestAgent;
        if (!ApiRateLimitUtil.isSyncRateLimited((Lockable)localObject1, ((Long)com.google.android.gms.games.config.G.tickleSyncThresholdMillis.get()).longValue(), paramGamesClientContext.mForceReload)) {
          break label163;
        }
        GamesLog.i("QuestAgent", "Returning cached entities for quest metadata");
      }
      finally
      {
        releaseLocks(new Lockable[] { this.mQuestAgent });
      }
      paramGamesSyncResult.addOp(15);
      continue;
      localObject3 = ((QuestAgent)localObject1).syncMetadataFromNetwork(paramGamesClientContext, Agents.getSyncToken(paramGamesClientContext.mContext, paramGamesClientContext.mClientContext, QuestAgent.SYNC_QUEST_METADATA_TOKEN_PROJECTION));
      GamesLog.d("QuestAgent", String.format("Received %s quest metadata entities during sync", new Object[] { Integer.valueOf(((QuestAgent.SyncNetworkResponse)localObject3).mData.size()) }));
      if (((QuestAgent.SyncNetworkResponse)localObject3).mStatusCode == 0) {
        break;
      }
      i = ((QuestAgent.SyncNetworkResponse)localObject3).mStatusCode;
    }
    ArrayList localArrayList = ((QuestAgent.SyncNetworkResponse)localObject3).mData;
    Object localObject4 = new ArrayList();
    Object localObject2 = new ArrayList();
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        QuestMetadata localQuestMetadata = (QuestMetadata)localArrayList.get(i);
        if ("QUEST_METADATA".equals(localQuestMetadata.getType())) {
          ((ArrayList)localObject4).add(localQuestMetadata.getQuest());
        } else if ("APPLICATION_ID".equals(localQuestMetadata.getType())) {
          ((ArrayList)localObject2).add((String)localQuestMetadata.mValues.get("expiredApplicationId"));
        } else {
          GamesLog.w("QuestAgent", "Invalid QuestMetadata type: " + localQuestMetadata.getType());
        }
      }
      else
      {
        localArrayList = new ArrayList();
        localObject3 = ((QuestAgent)localObject1).buildQuestOperations(paramGamesClientContext, (QuestAgent.SyncNetworkResponse)localObject3, "quest_sync_metadata_token", (ArrayList)localObject4, localArrayList, false);
        if (((ArrayList)localObject2).size() > 0)
        {
          j = ((ArrayList)localObject2).size();
          i = 0;
          while (i < j)
          {
            localObject4 = (String)((ArrayList)localObject2).get(i);
            localArrayList.add(ContentProviderOperation.newDelete(GamesContractInternal.Quests.getUriForExternalGameId(paramGamesClientContext.mClientContext, (String)localObject4)).withYieldAllowed(Agents.shouldAllowYieldAtIndex(localArrayList.size())).build());
            i += 1;
          }
          localObject4 = GamesContractInternal.Quests.getContentUri(paramGamesClientContext.mClientContext);
          localObject2 = Agents.getTwoColumnMap(paramGamesClientContext.mContext, (Uri)localObject4, "external_game_id", "external_quest_id", (Collection)localObject2).entrySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject4 = (Map.Entry)((Iterator)localObject2).next();
            ((ArrayList)localObject3).add(new QuestAgent.NotifyDataHolder((String)((Map.Entry)localObject4).getKey(), (String)((Map.Entry)localObject4).getValue(), 3));
          }
        }
        if (((QuestAgent)localObject1).processQuestOperations(paramGamesClientContext, localArrayList, (ArrayList)localObject3)) {
          ApiRateLimitUtil.updateSyncTimestamp((Lockable)localObject1);
        }
        i = 0;
        break;
      }
      i += 1;
    }
  }
  
  /* Error */
  public final void registerForNotifications(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: invokestatic 2525	android/accounts/AccountManager:get	(Landroid/content/Context;)Landroid/accounts/AccountManager;
    //   18: ldc_w 2527
    //   21: invokevirtual 2531	android/accounts/AccountManager:getAccountsByType	(Ljava/lang/String;)[Landroid/accounts/Account;
    //   24: astore_3
    //   25: iconst_0
    //   26: istore_2
    //   27: iload_2
    //   28: aload_3
    //   29: arraylength
    //   30: if_icmpge +63 -> 93
    //   33: aload_1
    //   34: aload_3
    //   35: iload_2
    //   36: aaload
    //   37: invokestatic 1467	com/google/android/gms/games/broker/Agents:buildFirstPartyClientContext	(Landroid/content/Context;Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/ClientContext;
    //   40: astore 4
    //   42: aload_1
    //   43: aload 4
    //   45: aconst_null
    //   46: invokestatic 2535	com/google/android/gms/games/broker/AccountAgent:saveAccountToDatabase	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Landroid/net/Uri;
    //   49: ifnonnull +30 -> 79
    //   52: ldc_w 2537
    //   55: new 153	java/lang/StringBuilder
    //   58: dup
    //   59: ldc_w 2539
    //   62: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   65: aload 4
    //   67: getfield 922	com/google/android/gms/common/internal/ClientContext:mResolvedAccount	Landroid/accounts/Account;
    //   70: invokevirtual 2356	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokestatic 2542	com/google/android/gms/games/internal/GamesLog:pii	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload_1
    //   80: aload_3
    //   81: iload_2
    //   82: aaload
    //   83: invokestatic 2545	com/google/android/gms/games/broker/NotificationAgent:register	(Landroid/content/Context;Landroid/accounts/Account;)V
    //   86: iload_2
    //   87: iconst_1
    //   88: iadd
    //   89: istore_2
    //   90: goto -63 -> 27
    //   93: iconst_1
    //   94: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   97: dup
    //   98: iconst_0
    //   99: aload_0
    //   100: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   103: aastore
    //   104: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   107: return
    //   108: astore_1
    //   109: iconst_1
    //   110: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   113: dup
    //   114: iconst_0
    //   115: aload_0
    //   116: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   119: aastore
    //   120: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   123: aload_1
    //   124: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	this	DataBroker
    //   0	125	1	paramContext	Context
    //   26	64	2	i	int
    //   24	57	3	arrayOfAccount	android.accounts.Account[]
    //   40	26	4	localClientContext	ClientContext
    // Exception table:
    //   from	to	target	type
    //   14	25	108	finally
    //   27	79	108	finally
    //   79	86	108	finally
  }
  
  public final boolean registerGame(Context paramContext, ClientContext paramClientContext, String paramString, boolean paramBoolean)
  {
    acquireLocks(this.mGameAgent.mCacheLockables);
    try
    {
      paramBoolean = this.mGameAgent.registerGame(paramContext, paramClientContext, paramString, paramBoolean);
      return paramBoolean;
    }
    finally
    {
      releaseLocks(this.mGameAgent.mCacheLockables);
    }
  }
  
  public final void registerQuestAlarmsForAccount(Context paramContext, ClientContext paramClientContext)
  {
    QuestAgent.registerQuestAlarms(paramContext, paramClientContext);
    doQuestExpiringNotification(paramContext, paramClientContext);
  }
  
  public final void release()
  {
    super.assertNoChildrenLockedByCurrentThread();
  }
  
  /* Error */
  public final DataHolder rematchTurnBasedMatch(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 2562	com/google/android/gms/games/broker/TurnBasedAgent:retryMatch	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: iconst_1
    //   37: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: iconst_1
    //   54: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   63: aastore
    //   64: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	DataBroker
    //   0	69	1	paramGamesClientContext	GamesClientContext
    //   0	69	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	36	52	finally
  }
  
  /* Error */
  public final com.google.android.gms.games.service.operations.snapshots.OpenSnapshotOperation.SnapshotOpenData resolveSnapshotConflict(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2, com.google.android.gms.games.snapshot.SnapshotMetadataChange paramSnapshotMetadataChange, com.google.android.gms.drive.DriveContents paramDriveContents)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: aload_3
    //   21: aload 4
    //   23: aload 5
    //   25: aload 6
    //   27: iconst_m1
    //   28: invokevirtual 2567	com/google/android/gms/games/broker/SnapshotAgent:resolveSnapshotConflict	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/games/snapshot/SnapshotMetadataChange;Lcom/google/android/gms/drive/DriveContents;I)Lcom/google/android/gms/games/service/operations/snapshots/OpenSnapshotOperation$SnapshotOpenData;
    //   31: astore_1
    //   32: iconst_1
    //   33: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   36: dup
    //   37: iconst_0
    //   38: aload_0
    //   39: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   42: aastore
    //   43: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   46: aload_1
    //   47: areturn
    //   48: astore_1
    //   49: iconst_1
    //   50: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   53: dup
    //   54: iconst_0
    //   55: aload_0
    //   56: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   59: aastore
    //   60: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   63: aload_1
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	DataBroker
    //   0	65	1	paramGamesClientContext	GamesClientContext
    //   0	65	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   0	65	3	paramString1	String
    //   0	65	4	paramString2	String
    //   0	65	5	paramSnapshotMetadataChange	com.google.android.gms.games.snapshot.SnapshotMetadataChange
    //   0	65	6	paramDriveContents	com.google.android.gms.drive.DriveContents
    // Exception table:
    //   from	to	target	type
    //   14	32	48	finally
  }
  
  /* Error */
  public final int revealAchievement(GamesClientContext paramGamesClientContext, String paramString, PopupManager.PopupLocationInfo paramPopupLocationInfo)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: iconst_1
    //   21: aload_3
    //   22: invokevirtual 2573	com/google/android/gms/games/broker/AchievementAgent:updateAchievementState	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;ILcom/google/android/gms/games/internal/PopupManager$PopupLocationInfo;)Lcom/google/android/gms/games/broker/AchievementAgent$AchievementUpdateResult;
    //   25: getfield 1760	com/google/android/gms/games/broker/AchievementAgent$AchievementUpdateResult:statusCode	I
    //   28: istore 4
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: iload 4
    //   46: ireturn
    //   47: astore_1
    //   48: iconst_1
    //   49: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: getfield 197	com/google/android/gms/games/broker/DataBroker:mAchievementAgent	Lcom/google/android/gms/games/broker/AchievementAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	DataBroker
    //   0	64	1	paramGamesClientContext	GamesClientContext
    //   0	64	2	paramString	String
    //   0	64	3	paramPopupLocationInfo	PopupManager.PopupLocationInfo
    //   28	17	4	i	int
    // Exception table:
    //   from	to	target	type
    //   14	30	47	finally
  }
  
  /* Error */
  public final DataHolder searchForGames(GamesClientContext paramGamesClientContext, String paramString, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 2577	com/google/android/gms/games/broker/GameAgent:mSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   49: getfield 2577	com/google/android/gms/games/broker/GameAgent:mSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   62: aload_1
    //   63: aload_2
    //   64: iload_3
    //   65: iload 4
    //   67: invokevirtual 2579	com/google/android/gms/games/broker/GameAgent:searchForGames	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   70: astore_1
    //   71: goto -32 -> 39
    //   74: astore_1
    //   75: iconst_1
    //   76: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   79: dup
    //   80: iconst_0
    //   81: aload_0
    //   82: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   85: getfield 2577	com/google/android/gms/games/broker/GameAgent:mSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   88: aastore
    //   89: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	DataBroker
    //   0	94	1	paramGamesClientContext	GamesClientContext
    //   0	94	2	paramString	String
    //   0	94	3	paramInt	int
    //   0	94	4	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   22	39	74	finally
    //   58	71	74	finally
  }
  
  public final void searchForInstalledGames(GamesClientContext paramGamesClientContext)
  {
    acquireLocks(this.mGameAgent.mCacheLockables);
    try
    {
      long l = AccountAgent.getLastPackageScanTimestamp(paramGamesClientContext);
      if (l != -1L) {
        return;
      }
      GameAgent localGameAgent = this.mGameAgent;
      Context localContext = paramGamesClientContext.mContext;
      ClientContext localClientContext = paramGamesClientContext.mClientContext;
      List localList = localContext.getPackageManager().getInstalledApplications(0);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        localGameAgent.registerGame(localContext, localClientContext, ((ApplicationInfo)localList.get(i)).packageName, false);
        i += 1;
      }
      AccountAgent.setLastPackageScanTimestamp(paramGamesClientContext, System.currentTimeMillis());
      return;
    }
    finally
    {
      releaseLocks(this.mGameAgent.mCacheLockables);
    }
  }
  
  /* Error */
  public final DataHolder searchForPlayers(GamesClientContext paramGamesClientContext, String paramString, int paramInt, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: getfield 2609	com/google/android/gms/games/broker/PlayerAgent:mPlayersSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: iconst_1
    //   18: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   21: pop
    //   22: aload_1
    //   23: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   26: astore_1
    //   27: aload_1
    //   28: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   31: ifnonnull +27 -> 58
    //   34: iconst_2
    //   35: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   38: astore_1
    //   39: iconst_1
    //   40: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   49: getfield 2609	com/google/android/gms/games/broker/PlayerAgent:mPlayersSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   52: aastore
    //   53: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aload_1
    //   63: aload_2
    //   64: iload_3
    //   65: iload 4
    //   67: invokevirtual 2611	com/google/android/gms/games/broker/PlayerAgent:searchForPlayers	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;IZ)Lcom/google/android/gms/common/data/DataHolder;
    //   70: astore_1
    //   71: goto -32 -> 39
    //   74: astore_1
    //   75: iconst_1
    //   76: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   79: dup
    //   80: iconst_0
    //   81: aload_0
    //   82: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   85: getfield 2609	com/google/android/gms/games/broker/PlayerAgent:mPlayersSearchLock	Lcom/google/android/gms/games/broker/Lockable;
    //   88: aastore
    //   89: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	DataBroker
    //   0	94	1	paramGamesClientContext	GamesClientContext
    //   0	94	2	paramString	String
    //   0	94	3	paramInt	int
    //   0	94	4	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   22	39	74	finally
    //   58	71	74	finally
  }
  
  /* Error */
  public final DataHolder sendFriendInvite(GamesClientContext paramGamesClientContext, String paramString, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_2
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: iload_3
    //   33: invokevirtual 2614	com/google/android/gms/games/broker/SocialAgent:sendFriendInvite	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   36: astore_1
    //   37: aload_0
    //   38: aload_1
    //   39: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   42: iconst_2
    //   43: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   46: dup
    //   47: iconst_0
    //   48: aload_0
    //   49: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   52: aastore
    //   53: dup
    //   54: iconst_1
    //   55: aload_0
    //   56: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   59: aastore
    //   60: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   63: aload_1
    //   64: areturn
    //   65: astore_1
    //   66: iconst_2
    //   67: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   70: dup
    //   71: iconst_0
    //   72: aload_0
    //   73: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: aload_0
    //   80: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   83: aastore
    //   84: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	this	DataBroker
    //   0	89	1	paramGamesClientContext	GamesClientContext
    //   0	89	2	paramString	String
    //   0	89	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   26	42	65	finally
  }
  
  /* Error */
  public final DataHolder sendRequest(GamesClientContext paramGamesClientContext, int paramInt1, int paramInt2, byte[] paramArrayOfByte, String[] paramArrayOfString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: new 81	java/util/ArrayList
    //   29: dup
    //   30: aload 5
    //   32: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   35: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   38: astore 5
    //   40: aload_0
    //   41: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   44: aload_1
    //   45: iload_2
    //   46: iload_3
    //   47: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   50: aload 4
    //   52: aload 5
    //   54: invokevirtual 2619	com/google/android/gms/games/broker/RequestAgent:sendRequest	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/Integer;[BLjava/util/ArrayList;)Lcom/google/android/gms/common/data/DataHolder;
    //   57: astore_1
    //   58: iconst_1
    //   59: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   62: dup
    //   63: iconst_0
    //   64: aload_0
    //   65: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   68: aastore
    //   69: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   72: aload_1
    //   73: areturn
    //   74: astore_1
    //   75: iconst_1
    //   76: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   79: dup
    //   80: iconst_0
    //   81: aload_0
    //   82: getfield 253	com/google/android/gms/games/broker/DataBroker:mRequestAgent	Lcom/google/android/gms/games/broker/RequestAgent;
    //   85: aastore
    //   86: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	DataBroker
    //   0	91	1	paramGamesClientContext	GamesClientContext
    //   0	91	2	paramInt1	int
    //   0	91	3	paramInt2	int
    //   0	91	4	paramArrayOfByte	byte[]
    //   0	91	5	paramArrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   19	58	74	finally
  }
  
  public final int setAchievementSteps(GamesClientContext paramGamesClientContext, String paramString, int paramInt, PopupManager.PopupLocationInfo paramPopupLocationInfo, boolean paramBoolean)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mAchievementAgent });
    try
    {
      paramString = this.mAchievementAgent.setAchievementSteps(paramGamesClientContext, paramString, paramInt, paramPopupLocationInfo, paramBoolean);
      releaseLocks(new Lockable[] { this.mAchievementAgent });
      if (paramString.xpGained > 0L) {
        gainXp(paramGamesClientContext, paramString.xpGained, paramPopupLocationInfo);
      }
      return paramString.statusCode;
    }
    finally
    {
      releaseLocks(new Lockable[] { this.mAchievementAgent });
    }
  }
  
  /* Error */
  public final int setCaptureStatePaused(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: sipush 9001
    //   3: istore_3
    //   4: iconst_1
    //   5: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   8: dup
    //   9: iconst_0
    //   10: aload_0
    //   11: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   14: aastore
    //   15: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   18: aload_0
    //   19: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   22: astore 4
    //   24: aload_1
    //   25: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   28: ifne +28 -> 56
    //   31: ldc_w 1388
    //   34: ldc_w 2626
    //   37: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   40: iconst_1
    //   41: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   44: dup
    //   45: iconst_0
    //   46: aload_0
    //   47: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   50: aastore
    //   51: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   54: iload_3
    //   55: ireturn
    //   56: getstatic 2632	com/google/android/gms/games/util/ExperimentUtils:ENABLE_VIDEO_HEADLESS_CAPTURE	Lcom/google/android/gms/games/util/ExperimentUtils$DeviceExperiment;
    //   59: invokevirtual 2636	com/google/android/gms/games/util/ExperimentUtils$DeviceExperiment:get	()Z
    //   62: ifne +32 -> 94
    //   65: ldc_w 1388
    //   68: ldc_w 2638
    //   71: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: goto -34 -> 40
    //   77: astore_1
    //   78: iconst_1
    //   79: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   82: dup
    //   83: iconst_0
    //   84: aload_0
    //   85: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   88: aastore
    //   89: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   92: aload_1
    //   93: athrow
    //   94: aload 4
    //   96: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   99: ifnull +14 -> 113
    //   102: aload 4
    //   104: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   107: getfield 2641	com/google/android/gms/games/broker/VideoAgent$RecordingSession:wasThirdPartyLaunch	Z
    //   110: ifne +19 -> 129
    //   113: ldc_w 1388
    //   116: ldc_w 2643
    //   119: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: sipush 9000
    //   125: istore_3
    //   126: goto -86 -> 40
    //   129: aload 4
    //   131: getfield 1419	com/google/android/gms/games/broker/VideoAgent:mCaptureController	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController;
    //   134: astore_1
    //   135: aload_1
    //   136: getfield 2647	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mMediaMux	Lcom/google/android/gms/games/recorder/encode/MediaMux;
    //   139: ifnull +250 -> 389
    //   142: aload_1
    //   143: getfield 2651	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mVirtualDisplay	Landroid/hardware/display/VirtualDisplay;
    //   146: ifnonnull +6 -> 152
    //   149: goto +240 -> 389
    //   152: iload_2
    //   153: ifeq +10 -> 163
    //   156: aload_1
    //   157: getfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   160: ifnull +14 -> 174
    //   163: iload_2
    //   164: ifne +213 -> 377
    //   167: aload_1
    //   168: getfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   171: ifnull +206 -> 377
    //   174: iload_2
    //   175: ifeq +106 -> 281
    //   178: aload_1
    //   179: new 2653	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager
    //   182: dup
    //   183: aload_1
    //   184: invokespecial 2656	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:<init>	(Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController;)V
    //   187: putfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   190: aload_1
    //   191: getfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   194: astore_1
    //   195: aload_1
    //   196: getfield 2659	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:isPaused	Z
    //   199: ifne +74 -> 273
    //   202: aload_1
    //   203: getfield 2663	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mControllerRef	Ljava/lang/ref/WeakReference;
    //   206: invokevirtual 2666	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   209: checkcast 1421	com/google/android/gms/games/recorder/captors/ScreenCaptureController
    //   212: astore 4
    //   214: aload 4
    //   216: ifnonnull +15 -> 231
    //   219: ldc_w 2668
    //   222: ldc_w 2670
    //   225: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   228: goto +172 -> 400
    //   231: aload_1
    //   232: aload 4
    //   234: getfield 2673	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mIsMicEnabled	Z
    //   237: putfield 2676	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mPrePauseMicState	Z
    //   240: aload_1
    //   241: getfield 2676	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mPrePauseMicState	Z
    //   244: ifeq +9 -> 253
    //   247: aload 4
    //   249: iconst_0
    //   250: invokevirtual 2679	com/google/android/gms/games/recorder/captors/ScreenCaptureController:setMicEnabled	(Z)V
    //   253: aload 4
    //   255: getfield 2651	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mVirtualDisplay	Landroid/hardware/display/VirtualDisplay;
    //   258: aconst_null
    //   259: invokevirtual 2685	android/hardware/display/VirtualDisplay:setSurface	(Landroid/view/Surface;)V
    //   262: aload 4
    //   264: getfield 2647	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mMediaMux	Lcom/google/android/gms/games/recorder/encode/MediaMux;
    //   267: iconst_1
    //   268: invokeinterface 2690 2 0
    //   273: aload_1
    //   274: iconst_1
    //   275: putfield 2659	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:isPaused	Z
    //   278: goto +122 -> 400
    //   281: aload_1
    //   282: getfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   285: astore 4
    //   287: aload 4
    //   289: getfield 2659	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:isPaused	Z
    //   292: ifeq +76 -> 368
    //   295: aload 4
    //   297: getfield 2663	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mControllerRef	Ljava/lang/ref/WeakReference;
    //   300: invokevirtual 2666	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   303: checkcast 1421	com/google/android/gms/games/recorder/captors/ScreenCaptureController
    //   306: astore 5
    //   308: aload 5
    //   310: ifnonnull +20 -> 330
    //   313: ldc_w 2668
    //   316: ldc_w 2692
    //   319: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   322: aload_1
    //   323: aconst_null
    //   324: putfield 1425	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mPauseManager	Lcom/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager;
    //   327: goto +73 -> 400
    //   330: aload 4
    //   332: getfield 2676	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mPrePauseMicState	Z
    //   335: ifeq +9 -> 344
    //   338: aload 5
    //   340: iconst_1
    //   341: invokevirtual 2679	com/google/android/gms/games/recorder/captors/ScreenCaptureController:setMicEnabled	(Z)V
    //   344: aload 5
    //   346: getfield 2647	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mMediaMux	Lcom/google/android/gms/games/recorder/encode/MediaMux;
    //   349: iconst_0
    //   350: invokeinterface 2690 2 0
    //   355: aload 5
    //   357: getfield 2651	com/google/android/gms/games/recorder/captors/ScreenCaptureController:mVirtualDisplay	Landroid/hardware/display/VirtualDisplay;
    //   360: aload 4
    //   362: getfield 2696	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:mInputSurface	Landroid/view/Surface;
    //   365: invokevirtual 2685	android/hardware/display/VirtualDisplay:setSurface	(Landroid/view/Surface;)V
    //   368: aload 4
    //   370: iconst_0
    //   371: putfield 2659	com/google/android/gms/games/recorder/captors/ScreenCaptureController$PauseManager:isPaused	Z
    //   374: goto -52 -> 322
    //   377: iconst_0
    //   378: istore_3
    //   379: goto +12 -> 391
    //   382: sipush 9018
    //   385: istore_3
    //   386: goto -346 -> 40
    //   389: iconst_0
    //   390: istore_3
    //   391: iload_3
    //   392: ifeq -10 -> 382
    //   395: iconst_0
    //   396: istore_3
    //   397: goto -357 -> 40
    //   400: iconst_1
    //   401: istore_3
    //   402: goto -11 -> 391
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	405	0	this	DataBroker
    //   0	405	1	paramContext	Context
    //   0	405	2	paramBoolean	boolean
    //   3	399	3	i	int
    //   22	347	4	localObject	Object
    //   306	50	5	localScreenCaptureController	com.google.android.gms.games.recorder.captors.ScreenCaptureController
    // Exception table:
    //   from	to	target	type
    //   18	40	77	finally
    //   56	74	77	finally
    //   94	113	77	finally
    //   113	122	77	finally
    //   129	149	77	finally
    //   156	163	77	finally
    //   167	174	77	finally
    //   178	214	77	finally
    //   219	228	77	finally
    //   231	253	77	finally
    //   253	273	77	finally
    //   273	278	77	finally
    //   281	308	77	finally
    //   313	322	77	finally
    //   322	327	77	finally
    //   330	344	77	finally
    //   344	368	77	finally
    //   368	374	77	finally
  }
  
  /* Error */
  public final int setGameMuteStatus(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   13: aastore
    //   14: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   17: aload_1
    //   18: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   21: astore_1
    //   22: aload_0
    //   23: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   26: aload_1
    //   27: iload_2
    //   28: invokevirtual 2699	com/google/android/gms/games/broker/GameAgent:setGameMuteStatus	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)I
    //   31: istore_3
    //   32: iconst_1
    //   33: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   36: dup
    //   37: iconst_0
    //   38: aload_0
    //   39: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   42: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   45: aastore
    //   46: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   49: iload_3
    //   50: ireturn
    //   51: astore_1
    //   52: iconst_1
    //   53: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   56: dup
    //   57: iconst_0
    //   58: aload_0
    //   59: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   62: getfield 1843	com/google/android/gms/games/broker/GameAgent:mHiddenLock	Lcom/google/android/gms/games/broker/Lockable;
    //   65: aastore
    //   66: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   69: aload_1
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	this	DataBroker
    //   0	71	1	paramGamesClientContext	GamesClientContext
    //   0	71	2	paramBoolean	boolean
    //   31	19	3	i	int
    // Exception table:
    //   from	to	target	type
    //   17	32	51	finally
  }
  
  /* Error */
  public final int setIdentitySharingConfirmed(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: invokestatic 2702	com/google/android/gms/games/broker/GameAgent:setIdentitySharingConfirmed	(Lcom/google/android/gms/games/broker/GamesClientContext;)I
    //   18: istore_2
    //   19: iconst_1
    //   20: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   23: dup
    //   24: iconst_0
    //   25: aload_0
    //   26: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   29: aastore
    //   30: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   33: iload_2
    //   34: ireturn
    //   35: astore_1
    //   36: iconst_1
    //   37: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	DataBroker
    //   0	52	1	paramGamesClientContext	GamesClientContext
    //   18	16	2	i	int
    // Exception table:
    //   from	to	target	type
    //   14	19	35	finally
  }
  
  /* Error */
  public final DataHolder setPlayerMuted(GamesClientContext paramGamesClientContext, String paramString, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   17: aastore
    //   18: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   21: iconst_1
    //   22: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   25: pop
    //   26: aload_0
    //   27: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   30: aload_1
    //   31: iconst_0
    //   32: invokevirtual 443	com/google/android/gms/games/broker/PlayerAgent:fetchPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   35: invokevirtual 346	com/google/android/gms/common/data/DataHolder:close	()V
    //   38: aload_0
    //   39: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   42: aload_1
    //   43: aload_2
    //   44: iload_3
    //   45: invokevirtual 2705	com/google/android/gms/games/broker/SocialAgent:setPlayerMuted	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;Z)Lcom/google/android/gms/common/data/DataHolder;
    //   48: astore_1
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   54: iconst_2
    //   55: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   58: dup
    //   59: iconst_0
    //   60: aload_0
    //   61: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: aload_0
    //   68: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: aload_1
    //   76: areturn
    //   77: astore_1
    //   78: iconst_2
    //   79: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   82: dup
    //   83: iconst_0
    //   84: aload_0
    //   85: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   88: aastore
    //   89: dup
    //   90: iconst_1
    //   91: aload_0
    //   92: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   95: aastore
    //   96: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   99: aload_1
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	this	DataBroker
    //   0	101	1	paramGamesClientContext	GamesClientContext
    //   0	101	2	paramString	String
    //   0	101	3	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   26	54	77	finally
  }
  
  /* Error */
  public final int startCapture(GamesClientContext paramGamesClientContext, com.google.android.gms.games.service.WrappedGamesCallbacks paramWrappedGamesCallbacks, com.google.android.gms.games.video.VideoConfiguration paramVideoConfiguration)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 4
    //   3: aload_0
    //   4: aload_1
    //   5: invokespecial 866	com/google/android/gms/games/broker/DataBroker:getGameForVideoCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/Game;
    //   8: astore 5
    //   10: aload 5
    //   12: ifnull +45 -> 57
    //   15: iconst_1
    //   16: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   25: aastore
    //   26: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   29: aload_0
    //   30: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   33: aload_1
    //   34: aload_2
    //   35: aload 5
    //   37: aload_3
    //   38: invokevirtual 2710	com/google/android/gms/games/broker/VideoAgent:startCapture	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/service/WrappedGamesCallbacks;Lcom/google/android/gms/games/Game;Lcom/google/android/gms/games/video/VideoConfiguration;)I
    //   41: istore 4
    //   43: iconst_1
    //   44: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   47: dup
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   53: aastore
    //   54: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   57: iload 4
    //   59: ireturn
    //   60: astore_1
    //   61: iconst_1
    //   62: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   65: dup
    //   66: iconst_0
    //   67: aload_0
    //   68: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   71: aastore
    //   72: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	DataBroker
    //   0	77	1	paramGamesClientContext	GamesClientContext
    //   0	77	2	paramWrappedGamesCallbacks	com.google.android.gms.games.service.WrappedGamesCallbacks
    //   0	77	3	paramVideoConfiguration	com.google.android.gms.games.video.VideoConfiguration
    //   1	57	4	i	int
    //   8	28	5	localGame	com.google.android.gms.games.Game
    // Exception table:
    //   from	to	target	type
    //   29	43	60	finally
  }
  
  /* Error */
  public final int stopCapture(Context paramContext, com.google.android.gms.games.service.WrappedGamesCallbacks paramWrappedGamesCallbacks, String paramString)
  {
    // Byte code:
    //   0: sipush 9001
    //   3: istore 5
    //   5: iconst_1
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   15: aastore
    //   16: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   19: aload_0
    //   20: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   23: astore 7
    //   25: aload_1
    //   26: invokestatic 1386	com/google/android/gms/games/util/VideoUtils:isCaptureEnabled	(Landroid/content/Context;)Z
    //   29: ifne +33 -> 62
    //   32: ldc_w 1388
    //   35: ldc_w 2714
    //   38: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: iload 5
    //   43: istore 4
    //   45: iconst_1
    //   46: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   49: dup
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   55: aastore
    //   56: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   59: iload 4
    //   61: ireturn
    //   62: aload 7
    //   64: invokevirtual 1839	com/google/android/gms/games/broker/VideoAgent:isAvailable	()Z
    //   67: ifne +187 -> 254
    //   70: aload_2
    //   71: ifnonnull +53 -> 124
    //   74: iconst_1
    //   75: istore 4
    //   77: iload 4
    //   79: ifne +51 -> 130
    //   82: getstatic 2632	com/google/android/gms/games/util/ExperimentUtils:ENABLE_VIDEO_HEADLESS_CAPTURE	Lcom/google/android/gms/games/util/ExperimentUtils$DeviceExperiment;
    //   85: invokevirtual 2636	com/google/android/gms/games/util/ExperimentUtils$DeviceExperiment:get	()Z
    //   88: ifne +42 -> 130
    //   91: ldc_w 1388
    //   94: ldc_w 2716
    //   97: invokestatic 1006	com/google/android/gms/games/internal/GamesLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: iload 5
    //   102: istore 4
    //   104: goto -59 -> 45
    //   107: astore_1
    //   108: iconst_1
    //   109: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   112: dup
    //   113: iconst_0
    //   114: aload_0
    //   115: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   118: aastore
    //   119: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   122: aload_1
    //   123: athrow
    //   124: iconst_0
    //   125: istore 4
    //   127: goto -50 -> 77
    //   130: aload 7
    //   132: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   135: ifnull +133 -> 268
    //   138: aload 7
    //   140: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   143: getfield 2641	com/google/android/gms/games/broker/VideoAgent$RecordingSession:wasThirdPartyLaunch	Z
    //   146: ifeq +122 -> 268
    //   149: iconst_1
    //   150: istore 5
    //   152: aload 7
    //   154: invokevirtual 1397	com/google/android/gms/games/broker/VideoAgent:isRecording	()Z
    //   157: ifne +105 -> 262
    //   160: aload 7
    //   162: getfield 1415	com/google/android/gms/games/broker/VideoAgent:mVideoRecordingOverlay	Lcom/google/android/gms/games/ui/video/ScreenCaptureOverlay;
    //   165: ifnull +109 -> 274
    //   168: goto +94 -> 262
    //   171: iload 5
    //   173: ifeq +81 -> 254
    //   176: iload 6
    //   178: ifeq +76 -> 254
    //   181: aload 7
    //   183: invokevirtual 2719	com/google/android/gms/games/broker/VideoAgent:isStopping	()Z
    //   186: ifne +68 -> 254
    //   189: aload_3
    //   190: ifnull +64 -> 254
    //   193: aload_3
    //   194: aload 7
    //   196: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   199: invokevirtual 2720	com/google/android/gms/games/broker/VideoAgent$RecordingSession:getPackageName	()Ljava/lang/String;
    //   202: invokevirtual 1670	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   205: ifeq +49 -> 254
    //   208: iload 4
    //   210: ifne +19 -> 229
    //   213: aload 7
    //   215: getfield 1415	com/google/android/gms/games/broker/VideoAgent:mVideoRecordingOverlay	Lcom/google/android/gms/games/ui/video/ScreenCaptureOverlay;
    //   218: ifnull +11 -> 229
    //   221: sipush 9202
    //   224: istore 4
    //   226: goto -181 -> 45
    //   229: aload 7
    //   231: getfield 1394	com/google/android/gms/games/broker/VideoAgent:mRecordingSession	Lcom/google/android/gms/games/broker/VideoAgent$RecordingSession;
    //   234: aload_2
    //   235: putfield 2724	com/google/android/gms/games/broker/VideoAgent$RecordingSession:mStopCallbacks	Lcom/google/android/gms/games/service/WrappedGamesCallbacks;
    //   238: aload 7
    //   240: getfield 2728	com/google/android/gms/games/broker/VideoAgent:mProcessingHandler	Lcom/google/android/gms/games/util/VideoHandler;
    //   243: iconst_3
    //   244: iconst_1
    //   245: invokestatic 2732	com/google/android/gms/games/broker/VideoAgent:sendMessage	(Lcom/google/android/gms/games/util/VideoHandler;II)V
    //   248: iconst_0
    //   249: istore 4
    //   251: goto -206 -> 45
    //   254: sipush 9000
    //   257: istore 4
    //   259: goto -214 -> 45
    //   262: iconst_1
    //   263: istore 6
    //   265: goto -94 -> 171
    //   268: iconst_0
    //   269: istore 5
    //   271: goto -119 -> 152
    //   274: iconst_0
    //   275: istore 6
    //   277: goto -106 -> 171
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	280	0	this	DataBroker
    //   0	280	1	paramContext	Context
    //   0	280	2	paramWrappedGamesCallbacks	com.google.android.gms.games.service.WrappedGamesCallbacks
    //   0	280	3	paramString	String
    //   43	215	4	i	int
    //   3	267	5	j	int
    //   176	100	6	k	int
    //   23	216	7	localVideoAgent	VideoAgent
    // Exception table:
    //   from	to	target	type
    //   19	41	107	finally
    //   62	70	107	finally
    //   82	100	107	finally
    //   130	149	107	finally
    //   152	168	107	finally
    //   181	189	107	finally
    //   193	208	107	finally
    //   213	221	107	finally
    //   229	248	107	finally
  }
  
  /* Error */
  public final com.google.android.gms.games.leaderboard.ScoreSubmissionData submitScore(GamesClientContext paramGamesClientContext, String paramString1, long paramLong1, long paramLong2, String paramString2, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: lload_3
    //   21: lload 5
    //   23: aload 7
    //   25: iload 8
    //   27: invokevirtual 2736	com/google/android/gms/games/broker/LeaderboardAgent:submitScore	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;JJLjava/lang/String;Z)Lcom/google/android/gms/games/leaderboard/ScoreSubmissionData;
    //   30: astore_1
    //   31: iconst_1
    //   32: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   35: dup
    //   36: iconst_0
    //   37: aload_0
    //   38: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   41: aastore
    //   42: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   45: aload_1
    //   46: areturn
    //   47: astore_1
    //   48: iconst_1
    //   49: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: getfield 221	com/google/android/gms/games/broker/DataBroker:mLeaderboardAgent	Lcom/google/android/gms/games/broker/LeaderboardAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	DataBroker
    //   0	64	1	paramGamesClientContext	GamesClientContext
    //   0	64	2	paramString1	String
    //   0	64	3	paramLong1	long
    //   0	64	5	paramLong2	long
    //   0	64	7	paramString2	String
    //   0	64	8	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   14	31	47	finally
  }
  
  public final int syncMatches(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mMultiplayerAgent, this.mNotificationAgent });
    try
    {
      if (paramGamesClientContext.mForceReload) {
        ApiRateLimitUtil.clearSyncTimestamp(this.mMultiplayerAgent);
      }
      this.mTurnBasedAgent.submitPendingMatches(paramGamesClientContext, paramGamesSyncResult.syncResult);
      paramGamesSyncResult.addOp(9);
      int i = this.mMultiplayerAgent.syncEntities(paramGamesClientContext);
      paramGamesSyncResult.addOp(14);
      releaseLocks(new Lockable[] { this.mMultiplayerAgent, this.mNotificationAgent });
      if ((i == 4) || (i == 3) || (i == 500))
      {
        paramGamesClientContext = paramGamesSyncResult.syncResult.stats;
        paramGamesClientContext.numIoExceptions += 1L;
      }
      return i;
    }
    finally
    {
      releaseLocks(new Lockable[] { this.mMultiplayerAgent, this.mNotificationAgent });
    }
  }
  
  public final int syncRequests(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mRequestAgent, this.mNotificationAgent });
    GamesClientContext localGamesClientContext;
    RequestAgent localRequestAgent;
    label196:
    RequestAgent.SyncNetworkResponse localSyncNetworkResponse;
    for (;;)
    {
      try
      {
        localGamesClientContext = fetchPlayerIdFromAccountWhenMissing(paramGamesClientContext);
        paramGamesClientContext = localGamesClientContext.mExternalCurrentPlayerId;
        if (paramGamesClientContext == null)
        {
          i = 2;
          releaseLocks(new Lockable[] { this.mRequestAgent, this.mNotificationAgent });
          if ((i == 4) || (i == 3) || (i == 500))
          {
            paramGamesClientContext = paramGamesSyncResult.syncResult.stats;
            paramGamesClientContext.numIoExceptions += 1L;
          }
          return i;
        }
        if (localGamesClientContext.mForceReload) {
          ApiRateLimitUtil.clearSyncTimestamp(this.mRequestAgent);
        }
        localRequestAgent = this.mRequestAgent;
        localRequestAgent.flushPendingOps(localGamesClientContext);
        if (!ApiRateLimitUtil.isSyncRateLimited(localRequestAgent, ((Long)com.google.android.gms.games.config.G.tickleSyncThresholdMillis.get()).longValue(), localGamesClientContext.mForceReload)) {
          break label196;
        }
        GamesLog.i("RequestAgent", "Returning cached entities");
      }
      finally
      {
        releaseLocks(new Lockable[] { this.mRequestAgent, this.mNotificationAgent });
      }
      paramGamesSyncResult.addOp(13);
      continue;
      localGamesClientContext.resolveCurrentPlayerId();
      localSyncNetworkResponse = localRequestAgent.syncFromNetwork(localGamesClientContext, Agents.getSyncToken(localGamesClientContext.mContext, localGamesClientContext.mClientContext, RequestAgent.SYNC_TOKEN_PROJECTION));
      GamesLog.d("RequestAgent", String.format("Received %s requests during sync", new Object[] { Integer.valueOf(localSyncNetworkResponse.mEntities.size()) }));
      if (localSyncNetworkResponse.mStatusCode == 0) {
        break;
      }
      i = localSyncNetworkResponse.mStatusCode;
    }
    HashSet localHashSet = RequestAgent.getActionableIds(localGamesClientContext);
    ArrayList localArrayList1 = new ArrayList();
    paramGamesClientContext = localSyncNetworkResponse.mNewSyncToken;
    if (paramGamesClientContext != null) {
      localArrayList1.add(ContentProviderOperation.newUpdate(GamesContractInternal.AccountMetadata.getContentUri(localGamesClientContext.mClientContext)).withValue("request_sync_token", paramGamesClientContext).build());
    }
    Map localMap = RequestAgent.resolveExternalGameIds(localGamesClientContext.mContext, localGamesClientContext.mClientContext, localSyncNetworkResponse);
    ArrayList localArrayList2 = localSyncNetworkResponse.mEntities;
    int j = localArrayList2.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        RequestEntity localRequestEntity = (RequestEntity)localArrayList2.get(i);
        Request localRequest = localRequestEntity.getRequest();
        paramGamesClientContext = localRequest.getApplicationId();
        Long localLong;
        if (paramGamesClientContext.equals(localGamesClientContext.mExternalTargetGameId)) {
          localLong = Long.valueOf(localGamesClientContext.resolveTargetGameId());
        }
        GamesClientContext.Builder localBuilder;
        for (paramGamesClientContext = localGamesClientContext; localRequestAgent.addRequestOps(paramGamesClientContext, localRequest, localArrayList1) != -1; paramGamesClientContext = localBuilder.build())
        {
          localArrayList1.add(Agents.getNotificationOp(paramGamesClientContext.mContext, paramGamesClientContext.mClientContext, localRequestEntity.getNotification(), localLong, localRequest.getId(), 4));
          break;
          localLong = (Long)localMap.get(paramGamesClientContext);
          if (localLong == null)
          {
            GamesLog.e("RequestAgent", "No game found matching external game ID " + paramGamesClientContext);
            break;
          }
          localBuilder = localGamesClientContext.getBuilder();
          localBuilder.mExternalTargetGameId = paramGamesClientContext;
        }
      }
      if ((localArrayList1.size() > 0) && (!Agents.applyContentOperations(localGamesClientContext.mContext.getContentResolver(), localArrayList1, "RequestAgent"))) {
        GamesLog.e("RequestAgent", "Failed to store requests");
      }
      for (i = 0; i != 0; i = 1)
      {
        RequestAgent.postProcessNetworkEntities(localGamesClientContext, localSyncNetworkResponse);
        ApiRateLimitUtil.updateSyncTimestamp(localRequestAgent);
        break;
        paramGamesClientContext = RequestAgent.getActionableIds(localGamesClientContext);
        paramGamesClientContext.removeAll(localHashSet);
        if (paramGamesClientContext.size() > 0) {
          localRequestAgent.mHasNewActivity = true;
        }
        RequestAgent.trimRequestEntitiesForPlayer(localGamesClientContext);
      }
      i = 0;
      break;
      i += 1;
    }
  }
  
  /* Error */
  public final int syncSocialInvites$48637903(GamesClientContext paramGamesClientContext, GamesSyncAdapter.GamesSyncResult paramGamesSyncResult)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_3
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: aload_0
    //   14: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   17: aastore
    //   18: dup
    //   19: iconst_2
    //   20: aload_0
    //   21: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   24: aastore
    //   25: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   28: aload_1
    //   29: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   32: astore_1
    //   33: aload_1
    //   34: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   37: astore 4
    //   39: aload 4
    //   41: ifnonnull +70 -> 111
    //   44: iconst_2
    //   45: istore_3
    //   46: iconst_3
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: aload_0
    //   60: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   63: aastore
    //   64: dup
    //   65: iconst_2
    //   66: aload_0
    //   67: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   70: aastore
    //   71: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   74: iload_3
    //   75: iconst_4
    //   76: if_icmpeq +15 -> 91
    //   79: iload_3
    //   80: iconst_3
    //   81: if_icmpeq +10 -> 91
    //   84: iload_3
    //   85: sipush 500
    //   88: if_icmpne +21 -> 109
    //   91: aload_2
    //   92: getfield 1121	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:syncResult	Landroid/content/SyncResult;
    //   95: getfield 2410	android/content/SyncResult:stats	Landroid/content/SyncStats;
    //   98: astore_1
    //   99: aload_1
    //   100: aload_1
    //   101: getfield 2415	android/content/SyncStats:numIoExceptions	J
    //   104: lconst_1
    //   105: ladd
    //   106: putfield 2415	android/content/SyncStats:numIoExceptions	J
    //   109: iload_3
    //   110: ireturn
    //   111: aload_0
    //   112: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   115: aload_1
    //   116: iconst_1
    //   117: invokevirtual 2830	com/google/android/gms/games/broker/SocialAgent:syncInvites	(Lcom/google/android/gms/games/broker/GamesClientContext;Z)I
    //   120: istore_3
    //   121: aload_0
    //   122: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   125: invokevirtual 2831	com/google/android/gms/games/broker/PlayerAgent:clearTransientCaches	()V
    //   128: aload_2
    //   129: bipush 25
    //   131: invokevirtual 1198	com/google/android/gms/games/service/GamesSyncAdapter$GamesSyncResult:addOp	(I)V
    //   134: goto -88 -> 46
    //   137: astore_1
    //   138: iconst_3
    //   139: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   142: dup
    //   143: iconst_0
    //   144: aload_0
    //   145: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   148: aastore
    //   149: dup
    //   150: iconst_1
    //   151: aload_0
    //   152: getfield 226	com/google/android/gms/games/broker/DataBroker:mNotificationAgent	Lcom/google/android/gms/games/broker/NotificationAgent;
    //   155: aastore
    //   156: dup
    //   157: iconst_2
    //   158: aload_0
    //   159: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   162: aastore
    //   163: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   166: aload_1
    //   167: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	DataBroker
    //   0	168	1	paramGamesClientContext	GamesClientContext
    //   0	168	2	paramGamesSyncResult	GamesSyncAdapter.GamesSyncResult
    //   45	76	3	i	int
    //   37	3	4	str	String
    // Exception table:
    //   from	to	target	type
    //   28	39	137	finally
    //   111	134	137	finally
  }
  
  /* Error */
  public final DataHolder unfriendPlayer(GamesClientContext paramGamesClientContext, String paramString)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   4: pop
    //   5: iconst_2
    //   6: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   9: dup
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   22: aastore
    //   23: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   30: aload_1
    //   31: aload_2
    //   32: invokevirtual 2834	com/google/android/gms/games/broker/SocialAgent:unfriendPlayer	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder;
    //   35: astore_1
    //   36: aload_0
    //   37: aload_1
    //   38: invokespecial 715	com/google/android/gms/games/broker/DataBroker:updatePlayerCachesForSocialChange	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   41: iconst_2
    //   42: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: aload_0
    //   55: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   58: aastore
    //   59: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: iconst_2
    //   66: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   69: dup
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 288	com/google/android/gms/games/broker/DataBroker:mSocialAgent	Lcom/google/android/gms/games/broker/SocialAgent;
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: aload_0
    //   79: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   82: aastore
    //   83: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	DataBroker
    //   0	88	1	paramGamesClientContext	GamesClientContext
    //   0	88	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   26	41	64	finally
  }
  
  public final int unlockAchievement(GamesClientContext paramGamesClientContext, String paramString, PopupManager.PopupLocationInfo paramPopupLocationInfo)
    throws GoogleAuthException
  {
    acquireLocks(new Lockable[] { this.mAchievementAgent });
    try
    {
      paramString = this.mAchievementAgent.updateAchievementState(paramGamesClientContext, paramString, 0, paramPopupLocationInfo);
      releaseLocks(new Lockable[] { this.mAchievementAgent });
      if (paramString.xpGained > 0L) {
        gainXp(paramGamesClientContext, paramString.xpGained, paramPopupLocationInfo);
      }
      return paramString.statusCode;
    }
    finally
    {
      releaseLocks(new Lockable[] { this.mAchievementAgent });
    }
  }
  
  /* Error */
  public final int updateAutoSignIn(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   18: aload_1
    //   19: new 2838	com/google/android/gms/games/server/api/ProfileSettings
    //   22: dup
    //   23: iload_2
    //   24: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   27: aconst_null
    //   28: aconst_null
    //   29: aconst_null
    //   30: aconst_null
    //   31: aload_1
    //   32: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   35: aconst_null
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokespecial 2841	com/google/android/gms/games/server/api/ProfileSettings:<init>	(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V
    //   42: invokevirtual 2845	com/google/android/gms/games/broker/PlayerAgent:updateProfileSettings	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/ProfileSettings;)Lcom/google/android/gms/games/server/api/UpdateProfileSettingsResponse;
    //   45: astore_1
    //   46: aload_1
    //   47: ifnonnull +21 -> 68
    //   50: iconst_1
    //   51: istore_3
    //   52: iconst_1
    //   53: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   56: dup
    //   57: iconst_0
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aastore
    //   63: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   66: iload_3
    //   67: ireturn
    //   68: aload_1
    //   69: invokevirtual 2850	com/google/android/gms/games/server/api/UpdateProfileSettingsResponse:getStatus	()Ljava/lang/Integer;
    //   72: invokevirtual 875	java/lang/Integer:intValue	()I
    //   75: istore_3
    //   76: iload_3
    //   77: ifeq +9 -> 86
    //   80: bipush 6
    //   82: istore_3
    //   83: goto -31 -> 52
    //   86: iconst_0
    //   87: istore_3
    //   88: goto -36 -> 52
    //   91: astore_1
    //   92: iconst_1
    //   93: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   96: dup
    //   97: iconst_0
    //   98: aload_0
    //   99: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   102: aastore
    //   103: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   106: aload_1
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	DataBroker
    //   0	108	1	paramGamesClientContext	GamesClientContext
    //   0	108	2	paramBoolean	boolean
    //   51	37	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	46	91	finally
    //   68	76	91	finally
  }
  
  /* Error */
  public final long updateConnectionTime(GamesClientContext paramGamesClientContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: invokestatic 2853	com/google/android/gms/games/broker/GameAgent:updateConnectionTime	(Lcom/google/android/gms/games/broker/GamesClientContext;)J
    //   18: lstore_2
    //   19: iconst_1
    //   20: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   23: dup
    //   24: iconst_0
    //   25: aload_0
    //   26: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   29: aastore
    //   30: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   33: lload_2
    //   34: lreturn
    //   35: astore_1
    //   36: iconst_1
    //   37: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 216	com/google/android/gms/games/broker/DataBroker:mGameAgent	Lcom/google/android/gms/games/broker/GameAgent;
    //   46: aastore
    //   47: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	DataBroker
    //   0	52	1	paramGamesClientContext	GamesClientContext
    //   18	16	2	l	long
    // Exception table:
    //   from	to	target	type
    //   14	19	35	finally
  }
  
  /* Error */
  public final int updateContactSettings(ClientContext paramClientContext, boolean paramBoolean1, boolean paramBoolean2, Bundle paramBundle)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   18: aload_1
    //   19: iload_2
    //   20: iload_3
    //   21: aload 4
    //   23: invokevirtual 2857	com/google/android/gms/games/broker/PlayerAgent:updateContactSettings	(Lcom/google/android/gms/common/internal/ClientContext;ZZLandroid/os/Bundle;)I
    //   26: istore 5
    //   28: iconst_1
    //   29: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   38: aastore
    //   39: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   42: iload 5
    //   44: ireturn
    //   45: astore_1
    //   46: iconst_1
    //   47: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   56: aastore
    //   57: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	DataBroker
    //   0	62	1	paramClientContext	ClientContext
    //   0	62	2	paramBoolean1	boolean
    //   0	62	3	paramBoolean2	boolean
    //   0	62	4	paramBundle	Bundle
    //   26	17	5	i	int
    // Exception table:
    //   from	to	target	type
    //   14	28	45	finally
  }
  
  /* Error */
  public final DataHolder updateGamerProfile(GamesClientContext paramGamesClientContext, String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, boolean paramBoolean3)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_1
    //   20: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   23: astore 7
    //   25: aload_0
    //   26: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   29: aload 7
    //   31: new 2838	com/google/android/gms/games/server/api/ProfileSettings
    //   34: dup
    //   35: aconst_null
    //   36: aload_2
    //   37: iload_3
    //   38: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   41: iconst_1
    //   42: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   45: iconst_1
    //   46: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   49: aload 7
    //   51: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   54: iload 5
    //   56: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   59: iconst_1
    //   60: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   63: iload 6
    //   65: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   68: aload 4
    //   70: invokespecial 2841	com/google/android/gms/games/server/api/ProfileSettings:<init>	(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V
    //   73: invokevirtual 2845	com/google/android/gms/games/broker/PlayerAgent:updateProfileSettings	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/ProfileSettings;)Lcom/google/android/gms/games/server/api/UpdateProfileSettingsResponse;
    //   76: astore_1
    //   77: aload_1
    //   78: ifnonnull +39 -> 117
    //   81: bipush 6
    //   83: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   86: astore_1
    //   87: iconst_1
    //   88: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   91: dup
    //   92: iconst_0
    //   93: aload_0
    //   94: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   97: aastore
    //   98: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   101: aload_1
    //   102: getfield 644	com/google/android/gms/common/data/DataHolder:mStatusCode	I
    //   105: ifne +10 -> 115
    //   108: aload_0
    //   109: aload 7
    //   111: aconst_null
    //   112: invokevirtual 886	com/google/android/gms/games/broker/DataBroker:invalidateAppContentCache	(Lcom/google/android/gms/games/broker/GamesClientContext;[Ljava/lang/String;)V
    //   115: aload_1
    //   116: areturn
    //   117: aload_1
    //   118: getfield 2472	com/google/android/gms/common/server/response/FastMapJsonResponse:mValues	Ljava/util/HashMap;
    //   121: ldc_w 2861
    //   124: invokevirtual 2038	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   127: checkcast 81	java/util/ArrayList
    //   130: astore_2
    //   131: new 453	android/content/ContentValues
    //   134: dup
    //   135: invokespecial 454	android/content/ContentValues:<init>	()V
    //   138: astore 4
    //   140: aload 4
    //   142: ldc_w 2862
    //   145: aload_1
    //   146: invokevirtual 2850	com/google/android/gms/games/server/api/UpdateProfileSettingsResponse:getStatus	()Ljava/lang/Integer;
    //   149: invokevirtual 504	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   152: aload_2
    //   153: ifnull +47 -> 200
    //   156: aload_2
    //   157: invokevirtual 1811	java/util/ArrayList:isEmpty	()Z
    //   160: ifne +40 -> 200
    //   163: aload 4
    //   165: ldc_w 2864
    //   168: ldc_w 2866
    //   171: invokestatic 1957	com/google/android/gms/common/internal/Joiner:on	(Ljava/lang/String;)Lcom/google/android/gms/common/internal/Joiner;
    //   174: aload_2
    //   175: invokevirtual 1961	com/google/android/gms/common/internal/Joiner:join	(Ljava/lang/Iterable;)Ljava/lang/String;
    //   178: invokevirtual 2868	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   181: getstatic 2873	com/google/android/gms/games/internal/GamesContract$UpdateProfileSettingsResponseColumns:ALL_COLUMNS	[Ljava/lang/String;
    //   184: invokestatic 2012	com/google/android/gms/common/data/DataHolder:builder	([Ljava/lang/String;)Lcom/google/android/gms/common/data/DataHolder$Builder;
    //   187: aload 4
    //   189: invokevirtual 2071	com/google/android/gms/common/data/DataHolder$Builder:withRow	(Landroid/content/ContentValues;)Lcom/google/android/gms/common/data/DataHolder$Builder;
    //   192: iconst_0
    //   193: invokevirtual 2084	com/google/android/gms/common/data/DataHolder$Builder:build	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   196: astore_1
    //   197: goto -110 -> 87
    //   200: aload 4
    //   202: ldc_w 2864
    //   205: invokevirtual 2876	android/content/ContentValues:putNull	(Ljava/lang/String;)V
    //   208: goto -27 -> 181
    //   211: astore_1
    //   212: iconst_1
    //   213: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   216: dup
    //   217: iconst_0
    //   218: aload_0
    //   219: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   222: aastore
    //   223: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   226: aload_1
    //   227: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	DataBroker
    //   0	228	1	paramGamesClientContext	GamesClientContext
    //   0	228	2	paramString1	String
    //   0	228	3	paramBoolean1	boolean
    //   0	228	4	paramString2	String
    //   0	228	5	paramBoolean2	boolean
    //   0	228	6	paramBoolean3	boolean
    //   23	87	7	localGamesClientContext	GamesClientContext
    // Exception table:
    //   from	to	target	type
    //   19	77	211	finally
    //   81	87	211	finally
    //   117	152	211	finally
    //   156	181	211	finally
    //   181	197	211	finally
    //   200	208	211	finally
  }
  
  /* Error */
  public final int updateHeadlessCapturePermission(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: aload_2
    //   16: iload_3
    //   17: invokestatic 2880	com/google/android/gms/games/broker/VideoAgent:updateHeadlessCapturePermission	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   20: istore 4
    //   22: iconst_1
    //   23: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   32: aastore
    //   33: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   36: iload 4
    //   38: ireturn
    //   39: astore_1
    //   40: iconst_1
    //   41: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   44: dup
    //   45: iconst_0
    //   46: aload_0
    //   47: getfield 283	com/google/android/gms/games/broker/DataBroker:mVideoAgent	Lcom/google/android/gms/games/broker/VideoAgent;
    //   50: aastore
    //   51: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   54: aload_1
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	DataBroker
    //   0	56	1	paramContext	Context
    //   0	56	2	paramString	String
    //   0	56	3	paramBoolean	boolean
    //   20	17	4	i	int
    // Exception table:
    //   from	to	target	type
    //   14	22	39	finally
  }
  
  /* Error */
  public final int updateProfileDiscoverability(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   18: aload_1
    //   19: new 2838	com/google/android/gms/games/server/api/ProfileSettings
    //   22: dup
    //   23: aconst_null
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: aload_1
    //   29: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   32: iload_2
    //   33: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokespecial 2841	com/google/android/gms/games/server/api/ProfileSettings:<init>	(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V
    //   42: invokevirtual 2845	com/google/android/gms/games/broker/PlayerAgent:updateProfileSettings	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/ProfileSettings;)Lcom/google/android/gms/games/server/api/UpdateProfileSettingsResponse;
    //   45: astore_1
    //   46: aload_1
    //   47: ifnonnull +21 -> 68
    //   50: iconst_1
    //   51: istore_3
    //   52: iconst_1
    //   53: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   56: dup
    //   57: iconst_0
    //   58: aload_0
    //   59: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   62: aastore
    //   63: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   66: iload_3
    //   67: ireturn
    //   68: aload_1
    //   69: invokevirtual 2850	com/google/android/gms/games/server/api/UpdateProfileSettingsResponse:getStatus	()Ljava/lang/Integer;
    //   72: invokevirtual 875	java/lang/Integer:intValue	()I
    //   75: istore_3
    //   76: iload_3
    //   77: ifeq +9 -> 86
    //   80: bipush 6
    //   82: istore_3
    //   83: goto -31 -> 52
    //   86: iconst_0
    //   87: istore_3
    //   88: goto -36 -> 52
    //   91: astore_1
    //   92: iconst_1
    //   93: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   96: dup
    //   97: iconst_0
    //   98: aload_0
    //   99: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   102: aastore
    //   103: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   106: aload_1
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	DataBroker
    //   0	108	1	paramGamesClientContext	GamesClientContext
    //   0	108	2	paramBoolean	boolean
    //   51	37	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	46	91	finally
    //   68	76	91	finally
  }
  
  /* Error */
  public final int updateProfileSettings(GamesClientContext paramGamesClientContext, boolean paramBoolean)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_1
    //   15: invokestatic 835	com/google/android/gms/games/broker/DataBroker:fetchPlayerIdFromAccountWhenMissing	(Lcom/google/android/gms/games/broker/GamesClientContext;)Lcom/google/android/gms/games/broker/GamesClientContext;
    //   18: astore_1
    //   19: aload_0
    //   20: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   23: aload_1
    //   24: new 2838	com/google/android/gms/games/server/api/ProfileSettings
    //   27: dup
    //   28: aconst_null
    //   29: aconst_null
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aload_1
    //   34: getfield 376	com/google/android/gms/games/broker/GamesClientContext:mExternalCurrentPlayerId	Ljava/lang/String;
    //   37: aconst_null
    //   38: iconst_1
    //   39: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   42: iload_2
    //   43: invokestatic 535	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   46: aconst_null
    //   47: invokespecial 2841	com/google/android/gms/games/server/api/ProfileSettings:<init>	(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V
    //   50: invokevirtual 2845	com/google/android/gms/games/broker/PlayerAgent:updateProfileSettings	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/ProfileSettings;)Lcom/google/android/gms/games/server/api/UpdateProfileSettingsResponse;
    //   53: astore_1
    //   54: aload_1
    //   55: ifnull +27 -> 82
    //   58: iconst_1
    //   59: istore_3
    //   60: iload_3
    //   61: ifeq +26 -> 87
    //   64: iconst_0
    //   65: istore_3
    //   66: iconst_1
    //   67: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   70: dup
    //   71: iconst_0
    //   72: aload_0
    //   73: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   76: aastore
    //   77: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   80: iload_3
    //   81: ireturn
    //   82: iconst_0
    //   83: istore_3
    //   84: goto -24 -> 60
    //   87: bipush 6
    //   89: istore_3
    //   90: goto -24 -> 66
    //   93: astore_1
    //   94: iconst_1
    //   95: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   98: dup
    //   99: iconst_0
    //   100: aload_0
    //   101: getfield 236	com/google/android/gms/games/broker/DataBroker:mPlayerAgent	Lcom/google/android/gms/games/broker/PlayerAgent;
    //   104: aastore
    //   105: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   108: aload_1
    //   109: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	this	DataBroker
    //   0	110	1	paramGamesClientContext	GamesClientContext
    //   0	110	2	paramBoolean	boolean
    //   59	31	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	54	93	finally
  }
  
  /* Error */
  public final DataHolder updateRoom(Context paramContext, ClientContext paramClientContext, com.google.android.gms.games.server.api.RoomStatus paramRoomStatus)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload_0
    //   27: getfield 273	com/google/android/gms/games/broker/DataBroker:mRealTimeAgent	Lcom/google/android/gms/games/broker/RealTimeAgent;
    //   30: astore 5
    //   32: aload 5
    //   34: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   37: ifnonnull +50 -> 87
    //   40: ldc_w 2889
    //   43: new 153	java/lang/StringBuilder
    //   46: dup
    //   47: ldc_w 2891
    //   50: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   53: aload_3
    //   54: invokevirtual 2896	com/google/android/gms/games/server/api/RoomStatus:getRoomId	()Ljava/lang/String;
    //   57: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: iconst_1
    //   67: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   70: astore_1
    //   71: iconst_1
    //   72: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   75: dup
    //   76: iconst_0
    //   77: aload_0
    //   78: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   81: aastore
    //   82: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   85: aload_1
    //   86: areturn
    //   87: aload 5
    //   89: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   92: aload_3
    //   93: invokevirtual 2896	com/google/android/gms/games/server/api/RoomStatus:getRoomId	()Ljava/lang/String;
    //   96: invokevirtual 2902	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:isRoomCached	(Ljava/lang/String;)Z
    //   99: ifne +54 -> 153
    //   102: ldc_w 2889
    //   105: new 153	java/lang/StringBuilder
    //   108: dup
    //   109: ldc_w 2904
    //   112: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   115: aload 5
    //   117: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   120: getfield 2907	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:mRoomId	Ljava/lang/String;
    //   123: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 2909
    //   129: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_3
    //   133: invokevirtual 2896	com/google/android/gms/games/server/api/RoomStatus:getRoomId	()Ljava/lang/String;
    //   136: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: invokestatic 424	com/google/android/gms/games/internal/GamesLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: iconst_1
    //   146: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   149: astore_1
    //   150: goto -79 -> 71
    //   153: aload 5
    //   155: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   158: getfield 2913	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:mRoomDataValues	Ljava/util/Map;
    //   161: astore 6
    //   163: aload 5
    //   165: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   168: astore 4
    //   170: aload 4
    //   172: getfield 2913	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:mRoomDataValues	Ljava/util/Map;
    //   175: ifnull +16 -> 191
    //   178: aload 4
    //   180: getfield 2913	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:mRoomDataValues	Ljava/util/Map;
    //   183: invokeinterface 2914 1 0
    //   188: ifne +44 -> 232
    //   191: new 453	android/content/ContentValues
    //   194: dup
    //   195: invokespecial 454	android/content/ContentValues:<init>	()V
    //   198: astore 4
    //   200: aload_1
    //   201: aload_2
    //   202: aload 6
    //   204: aload 4
    //   206: aload_3
    //   207: invokestatic 2920	com/google/android/gms/games/broker/MultiplayerUtils:updateRoomData	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/util/Map;Landroid/content/ContentValues;Lcom/google/android/gms/games/server/api/RoomStatus;)V
    //   210: aload 5
    //   212: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   215: aload 6
    //   217: invokevirtual 2924	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:updateRoomValues	(Ljava/util/Map;)V
    //   220: aload 5
    //   222: getfield 2887	com/google/android/gms/games/broker/RealTimeAgent:mRoomCache	Lcom/google/android/gms/games/broker/RealTimeAgent$RoomCache;
    //   225: invokevirtual 2928	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:getRoomData	()Lcom/google/android/gms/common/data/DataHolder;
    //   228: astore_1
    //   229: goto -158 -> 71
    //   232: aload 4
    //   234: getfield 2913	com/google/android/gms/games/broker/RealTimeAgent$RoomCache:mRoomDataValues	Ljava/util/Map;
    //   237: invokeinterface 2502 1 0
    //   242: invokeinterface 600 1 0
    //   247: invokeinterface 608 1 0
    //   252: checkcast 2504	java/util/Map$Entry
    //   255: astore 4
    //   257: aload 4
    //   259: ldc_w 2930
    //   262: invokestatic 2932	com/google/android/gms/common/internal/Asserts:checkNotNull	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   265: aload 4
    //   267: invokeinterface 2512 1 0
    //   272: checkcast 453	android/content/ContentValues
    //   275: astore 4
    //   277: aload 4
    //   279: ldc_w 2934
    //   282: invokestatic 2932	com/google/android/gms/common/internal/Asserts:checkNotNull	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   285: aload 4
    //   287: invokestatic 2938	com/google/android/gms/games/broker/RealTimeAgent:access$100	()[Ljava/lang/String;
    //   290: invokestatic 2942	com/google/android/gms/games/broker/Agents:extractValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   293: astore 4
    //   295: goto -95 -> 200
    //   298: astore_1
    //   299: iconst_1
    //   300: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   303: dup
    //   304: iconst_0
    //   305: aload_0
    //   306: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   309: aastore
    //   310: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   313: aload_1
    //   314: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	315	0	this	DataBroker
    //   0	315	1	paramContext	Context
    //   0	315	2	paramClientContext	ClientContext
    //   0	315	3	paramRoomStatus	com.google.android.gms.games.server.api.RoomStatus
    //   168	126	4	localObject	Object
    //   30	191	5	localRealTimeAgent	RealTimeAgent
    //   161	55	6	localMap	Map
    // Exception table:
    //   from	to	target	type
    //   19	71	298	finally
    //   87	150	298	finally
    //   153	191	298	finally
    //   191	200	298	finally
    //   200	229	298	finally
    //   232	295	298	finally
  }
  
  /* Error */
  public final DataHolder updateTurnBasedMatch(GamesClientContext paramGamesClientContext, String paramString1, String paramString2, byte[] paramArrayOfByte, com.google.android.gms.games.multiplayer.ParticipantResult[] paramArrayOfParticipantResult)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: iconst_1
    //   15: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   18: pop
    //   19: aload_0
    //   20: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   23: invokestatic 729	com/google/android/gms/games/broker/ApiRateLimitUtil:clearSyncTimestamp	(Lcom/google/android/gms/games/broker/Lockable;)V
    //   26: aload 5
    //   28: ifnonnull +87 -> 115
    //   31: new 81	java/util/ArrayList
    //   34: dup
    //   35: invokespecial 82	java/util/ArrayList:<init>	()V
    //   38: astore 5
    //   40: aload_0
    //   41: getfield 278	com/google/android/gms/games/broker/DataBroker:mTurnBasedAgent	Lcom/google/android/gms/games/broker/TurnBasedAgent;
    //   44: astore 9
    //   46: aload_1
    //   47: getfield 380	com/google/android/gms/games/broker/GamesClientContext:mContext	Landroid/content/Context;
    //   50: astore 10
    //   52: aload_1
    //   53: getfield 384	com/google/android/gms/games/broker/GamesClientContext:mClientContext	Lcom/google/android/gms/common/internal/ClientContext;
    //   56: astore 11
    //   58: aload 10
    //   60: aload 11
    //   62: aload_2
    //   63: invokestatic 772	com/google/android/gms/games/broker/TurnBasedAgent:hasPendingOps	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)Z
    //   66: ifeq +83 -> 149
    //   69: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   72: new 153	java/lang/StringBuilder
    //   75: dup
    //   76: ldc_w 2946
    //   79: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   82: aload_2
    //   83: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: sipush 6507
    //   95: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   98: astore_1
    //   99: iconst_1
    //   100: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   103: dup
    //   104: iconst_0
    //   105: aload_0
    //   106: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   109: aastore
    //   110: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   113: aload_1
    //   114: areturn
    //   115: new 81	java/util/ArrayList
    //   118: dup
    //   119: aload 5
    //   121: invokestatic 735	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   124: invokespecial 738	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   127: astore 5
    //   129: goto -89 -> 40
    //   132: astore_1
    //   133: iconst_1
    //   134: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   137: dup
    //   138: iconst_0
    //   139: aload_0
    //   140: getfield 268	com/google/android/gms/games/broker/DataBroker:mMultiplayerAgent	Lcom/google/android/gms/games/broker/MultiplayerAgent;
    //   143: aastore
    //   144: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   147: aload_1
    //   148: athrow
    //   149: aload 10
    //   151: aload 11
    //   153: aload_2
    //   154: invokestatic 1072	com/google/android/gms/games/broker/TurnBasedAgent:getLocalVersion	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;)I
    //   157: istore 6
    //   159: iload 6
    //   161: iconst_m1
    //   162: if_icmpne +211 -> 373
    //   165: getstatic 775	com/google/android/gms/games/broker/TurnBasedAgent:TAG	Ljava/lang/String;
    //   168: new 153	java/lang/StringBuilder
    //   171: dup
    //   172: ldc_w 1074
    //   175: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   178: aload_2
    //   179: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokestatic 405	com/google/android/gms/games/internal/GamesLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: iconst_1
    //   189: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   192: astore_1
    //   193: goto -94 -> 99
    //   196: aload 5
    //   198: invokestatic 1080	com/google/android/gms/games/broker/TurnBasedAgent:convertToWireResults	(Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   201: astore 12
    //   203: aload 9
    //   205: aload 10
    //   207: aload 11
    //   209: aload_2
    //   210: new 2948	com/google/android/gms/games/server/api/TurnBasedMatchTurn
    //   213: dup
    //   214: aload 8
    //   216: iload 6
    //   218: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   221: aload_3
    //   222: aload 12
    //   224: invokespecial 2951	com/google/android/gms/games/server/api/TurnBasedMatchTurn:<init>	(Lcom/google/android/gms/games/server/api/TurnBasedMatchDataRequest;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;)V
    //   227: invokevirtual 2955	com/google/android/gms/games/broker/TurnBasedAgent:updateMatchInternal	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Lcom/google/android/gms/games/server/api/TurnBasedMatchTurn;)Landroid/util/Pair;
    //   230: astore 13
    //   232: aload 13
    //   234: getfield 493	android/util/Pair:first	Ljava/lang/Object;
    //   237: checkcast 498	java/lang/Integer
    //   240: invokevirtual 875	java/lang/Integer:intValue	()I
    //   243: istore 7
    //   245: aload 13
    //   247: getfield 530	android/util/Pair:second	Ljava/lang/Object;
    //   250: checkcast 1089	com/google/android/gms/games/server/api/TurnBasedMatch
    //   253: astore 13
    //   255: aload 13
    //   257: ifnull +34 -> 291
    //   260: aload 9
    //   262: aload_1
    //   263: aload 13
    //   265: iload 7
    //   267: invokevirtual 1093	com/google/android/gms/games/broker/TurnBasedAgent:storeAndReturnMatch	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/games/server/api/TurnBasedMatch;I)Lcom/google/android/gms/common/data/DataHolder;
    //   270: astore_1
    //   271: goto -172 -> 99
    //   274: new 1095	com/google/android/gms/games/server/api/TurnBasedMatchDataRequest
    //   277: dup
    //   278: aload 4
    //   280: invokestatic 1101	com/google/android/gms/common/util/Base64Utils:encodeUrlSafe	([B)Ljava/lang/String;
    //   283: invokespecial 1102	com/google/android/gms/games/server/api/TurnBasedMatchDataRequest:<init>	(Ljava/lang/String;)V
    //   286: astore 8
    //   288: goto -92 -> 196
    //   291: iload 7
    //   293: sipush 6503
    //   296: if_icmpne +16 -> 312
    //   299: aload 9
    //   301: aload_1
    //   302: aload_2
    //   303: iload 7
    //   305: invokevirtual 1106	com/google/android/gms/games/broker/TurnBasedAgent:getConflictMatch	(Lcom/google/android/gms/games/broker/GamesClientContext;Ljava/lang/String;I)Lcom/google/android/gms/common/data/DataHolder;
    //   308: astore_1
    //   309: goto -210 -> 99
    //   312: iload 7
    //   314: iconst_5
    //   315: if_icmpeq +12 -> 327
    //   318: iload 7
    //   320: invokestatic 325	com/google/android/gms/common/data/DataHolder:empty	(I)Lcom/google/android/gms/common/data/DataHolder;
    //   323: astore_1
    //   324: goto -225 -> 99
    //   327: aload_1
    //   328: iconst_3
    //   329: aload_2
    //   330: aload_3
    //   331: iconst_0
    //   332: iload 6
    //   334: new 1076	com/google/android/gms/games/server/api/TurnBasedMatchResults
    //   337: dup
    //   338: aload 8
    //   340: iload 6
    //   342: invokestatic 501	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   345: aload 12
    //   347: invokespecial 1083	com/google/android/gms/games/server/api/TurnBasedMatchResults:<init>	(Lcom/google/android/gms/games/server/api/TurnBasedMatchDataRequest;Ljava/lang/Integer;Ljava/util/ArrayList;)V
    //   350: invokestatic 1109	com/google/android/gms/games/broker/TurnBasedAgent:addPendingOp	(Lcom/google/android/gms/games/broker/GamesClientContext;ILjava/lang/String;Ljava/lang/String;ZILcom/google/android/gms/games/server/api/TurnBasedMatchResults;)V
    //   353: aload 10
    //   355: aload 11
    //   357: aload_2
    //   358: aload_3
    //   359: aload 4
    //   361: iload 6
    //   363: iconst_0
    //   364: aload 5
    //   366: invokestatic 1113	com/google/android/gms/games/broker/TurnBasedAgent:updateLocalMatchStateForPendingOp	(Landroid/content/Context;Lcom/google/android/gms/common/internal/ClientContext;Ljava/lang/String;Ljava/lang/String;[BIZLjava/util/ArrayList;)Lcom/google/android/gms/common/data/DataHolder;
    //   369: astore_1
    //   370: goto -271 -> 99
    //   373: aload 4
    //   375: ifnonnull -101 -> 274
    //   378: aconst_null
    //   379: astore 8
    //   381: goto -185 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	384	0	this	DataBroker
    //   0	384	1	paramGamesClientContext	GamesClientContext
    //   0	384	2	paramString1	String
    //   0	384	3	paramString2	String
    //   0	384	4	paramArrayOfByte	byte[]
    //   0	384	5	paramArrayOfParticipantResult	com.google.android.gms.games.multiplayer.ParticipantResult[]
    //   157	205	6	i	int
    //   243	76	7	j	int
    //   214	166	8	localTurnBasedMatchDataRequest	com.google.android.gms.games.server.api.TurnBasedMatchDataRequest
    //   44	256	9	localTurnBasedAgent	TurnBasedAgent
    //   50	304	10	localContext	Context
    //   56	300	11	localClientContext	ClientContext
    //   201	145	12	localArrayList	ArrayList
    //   230	34	13	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   19	26	132	finally
    //   31	40	132	finally
    //   40	99	132	finally
    //   115	129	132	finally
    //   149	159	132	finally
    //   165	193	132	finally
    //   196	255	132	finally
    //   260	271	132	finally
    //   274	288	132	finally
    //   299	309	132	finally
    //   318	324	132	finally
    //   327	370	132	finally
  }
  
  /* Error */
  public final int verifySnapshotFolder(GamesClientContext paramGamesClientContext, com.google.android.gms.common.api.GoogleApiClient paramGoogleApiClient)
    throws GoogleAuthException
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   4: dup
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   10: aastore
    //   11: invokestatic 411	com/google/android/gms/games/broker/DataBroker:acquireLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   14: aload_0
    //   15: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 2271	com/google/android/gms/games/broker/SnapshotAgent:getSnapshotFolder	(Lcom/google/android/gms/games/broker/GamesClientContext;Lcom/google/android/gms/common/api/GoogleApiClient;)Lcom/google/android/gms/games/broker/SnapshotAgent$ResultPair;
    //   23: getfield 2284	com/google/android/gms/games/broker/SnapshotAgent$ResultPair:status	Lcom/google/android/gms/common/api/Status;
    //   26: getfield 2287	com/google/android/gms/common/api/Status:mStatusCode	I
    //   29: istore_3
    //   30: iconst_1
    //   31: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   34: dup
    //   35: iconst_0
    //   36: aload_0
    //   37: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   40: aastore
    //   41: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   44: iload_3
    //   45: ireturn
    //   46: astore_1
    //   47: iconst_1
    //   48: anewarray 4	com/google/android/gms/games/broker/Lockable
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: getfield 258	com/google/android/gms/games/broker/DataBroker:mSnapshotAgent	Lcom/google/android/gms/games/broker/SnapshotAgent;
    //   57: aastore
    //   58: invokestatic 427	com/google/android/gms/games/broker/DataBroker:releaseLocks	([Lcom/google/android/gms/games/broker/Lockable;)V
    //   61: aload_1
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	DataBroker
    //   0	63	1	paramGamesClientContext	GamesClientContext
    //   0	63	2	paramGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   29	16	3	i	int
    // Exception table:
    //   from	to	target	type
    //   14	30	46	finally
  }
}
