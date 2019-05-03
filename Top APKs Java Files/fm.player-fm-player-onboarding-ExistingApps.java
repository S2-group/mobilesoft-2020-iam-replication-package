package fm.player.onboarding;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import fm.player.data.io.models.Channel;
import fm.player.data.io.models.Series;
import fm.player.utils.Alog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExistingApps
{
  private static final String TAG = "ExistingApps";
  private static List<ExistingApp> sExistingApps;
  
  static
  {
    ArrayList localArrayList = new ArrayList();
    sExistingApps = localArrayList;
    localArrayList.add(new ExistingApp("com.nike.plusgps", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.ejercicioscaseros", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.bluecorner.totalgym", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.runtastic.android", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.nike.ntc", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.popularapp.sevenmins", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.caynax.a6w", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.davor.thirtydayfit", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.myfitnesspal.android", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("softin.my.fast.fitness", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.dietcoacher.sos", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.shvagerfm.Butts", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.ego360.flatstomach", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.popularapp.abdominalexercise", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("co.thefabulous.app", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.teerstudios.abschallenge", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.ego360.sevenminute", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.sec.android.app.shealth", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("tools.bmirechner", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.playsimple.fitnessapp", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("cc.pacer.androidapp", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.tinymission.dailyworkoutsfree", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("de.aktiwir.aktibmi", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.strava", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("fitness.fitprosport", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("air.com.KalromSystems.BestAbsFitness", new Channel("662", "https://player.fm/featured/fitness")));
    sExistingApps.add(new ExistingApp("com.yahoo.mobile.client.android.yahoo", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.cnn.mobile.android.phone", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/ac360-audio")));
    sExistingApps.add(new ExistingApp("com.mobilesrepublic.appy", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.getnewsloop.app", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.yahoo.mobile.client.android.atom", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.guardian", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/politics-weekly")));
    sExistingApps.add(new ExistingApp("com.foxnews.android", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/fox-news-radio")));
    sExistingApps.add(new ExistingApp("bbc.mobile.news.ww", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/global-news")));
    sExistingApps.add(new ExistingApp("com.dailymail.online", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("it.pinenuts.rassegnastampa", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.zumobi.msnbc", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/nbc-nightly-news-audio")));
    sExistingApps.add(new ExistingApp("com.microsoft.amp.apps.bingnews", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/msnbc-rachel-maddow-audio")));
    sExistingApps.add(new ExistingApp("com.abc.abcnews", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/abc-news-world-news-tonight-with-david-muir-podcast")));
    sExistingApps.add(new ExistingApp("jp.gocro.smartnews.android", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.usatoday.android.news", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/capital-download")));
    sExistingApps.add(new ExistingApp("com.aol.mobile.aolapp", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.ft.news", new Channel("19", "https://player.fm/featured/daily-news"), new Series("https://player.fm/series/ft-news")));
    sExistingApps.add(new ExistingApp("net.jimblackler.newswidget", new Channel("19", "https://player.fm/featured/daily-news")));
    sExistingApps.add(new ExistingApp("com.oas_dev.doors_world_of_history", new Channel("11", "https://player.fm/featured/history")));
    sExistingApps.add(new ExistingApp("air.com.tedven.escapethroughhistory", new Channel("11", "https://player.fm/featured/history")));
    sExistingApps.add(new ExistingApp("com.shots.android", new Channel("4066679", "https://player.fm/featured/comedy")));
    sExistingApps.add(new ExistingApp("com.aol.mobile.engadget", new Channel("18", "https://player.fm/featured/tech")));
    sExistingApps.add(new ExistingApp("com.bestringtonesapps.bestclassicalmusicringtones", new Channel("818", "https://player.fm/featured/classical"), new Series("https://player.fm/series/new-classical-tracks-with-julie-amacher")));
    sExistingApps.add(new ExistingApp("com.audioaddict.rr", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("com.Zeeppo.RockLife", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("com.wjp.music.android", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("com.gamestar.pianoperfect", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("company.gamesx.guitarflash", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("com.tpvapps.simpledrumsdeluxe", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("com.halfbrick.bandstars", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("batalsoft.drumsolohd.rock", new Channel("827", "https://player.fm/featured/rock")));
    sExistingApps.add(new ExistingApp("br.com.rodrigokolb.electropads", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.paullipnyagov.electrodrumpads24", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.magix.android.mmjam", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.singlecellsoftware.caustic", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.audioaddict.di", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("br.com.rodrigokolb.dubstepbeatz", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("de.darkbloodstudios.electrodrumpads", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.aura.ringtones.auratechnoremix", new Channel("820", "https://player.fm/featured/electronic")));
    sExistingApps.add(new ExistingApp("com.issuu.android.app", new Channel("236528", "https://player.fm/featured/pop-culture")));
    sExistingApps.add(new ExistingApp("com.tubitv", new Channel("236528", "https://player.fm/featured/pop-culture")));
    sExistingApps.add(new ExistingApp("flipboard.app", new Channel("8", "https://player.fm/featured/true-stories")));
    sExistingApps.add(new ExistingApp("com.ideashower.readitlater.pro", new Channel("8", "https://player.fm/featured/true-stories")));
    sExistingApps.add(new ExistingApp("com.buzzfeed.android", new Channel("8", "https://player.fm/featured/true-stories"), new Series("https://player.fm/series/buzzfeeds-internet-explorer")));
    sExistingApps.add(new ExistingApp("com.scribd.app.reader0", new Channel("8", "https://player.fm/featured/true-stories")));
    sExistingApps.add(new ExistingApp("com.josh.jagran.android.activity", new Channel("25", "https://player.fm/featured/current-affairs")));
    sExistingApps.add(new ExistingApp("com.nis.app", new Channel("25", "https://player.fm/featured/current-affairs")));
    sExistingApps.add(new ExistingApp("net.aljazeera.english", new Channel("25", "https://player.fm/featured/current-affairs"), new Series("https://player.fm/series/al-jazeera-world-audio")));
    sExistingApps.add(new ExistingApp("org.npr.android.news", new Channel("25", "https://player.fm/featured/current-affairs"), new Series("https://player.fm/series/npr-wait-wait-dont-tell-me-podcast")));
    sExistingApps.add(new ExistingApp("com.idmedia.android.newsportal", new Channel("25", "https://player.fm/featured/current-affairs")));
    sExistingApps.add(new ExistingApp("com.ted.android", new Channel("292959", "https://player.fm/featured/science-roundup"), new Series("https://player.fm/series/tedtalks-audio")));
    sExistingApps.add(new ExistingApp("org.khanacademy.android", new Channel("292959", "https://player.fm/featured/science-roundup")));
    sExistingApps.add(new ExistingApp("com.socratica.mobile.chemistry", new Channel("292959", "https://player.fm/featured/science-roundup")));
    sExistingApps.add(new ExistingApp("gov.nasa", new Channel("292959", "https://player.fm/featured/science-roundup"), new Series("https://player.fm/series/nasacast-audio-96526")));
    sExistingApps.add(new ExistingApp("jqsoft.apps.periodictable.hd", new Channel("292959", "https://player.fm/featured/science-roundup")));
    sExistingApps.add(new ExistingApp("com.drippler.android.updates", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.google.android.apps.adm", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.innovationm.myandroid", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.advancedprocessmanager", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.electricsheep.asi", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.google.android.apps.androidify", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("de.androidpit.app", new Channel("20", "https://player.fm/featured/android")));
    sExistingApps.add(new ExistingApp("com.androidcentral.app", new Channel("20", "https://player.fm/featured/android"), new Series("https://player.fm/series/android-central-podcast")));
    sExistingApps.add(new ExistingApp("com.picsart.studio", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.adsk.sketchbook", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.sonymobile.sketch", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.artelplus.howtodraw", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.bytestorm.artflow", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.brakefield.painter", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.doodlejoy.studio.kaleidoo", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.deviantart.android.damobile", new Channel("932125", "https://player.fm/featured/arts")));
    sExistingApps.add(new ExistingApp("com.merriamwebster", new Series("https://player.fm/series/merriam-websters-word-of-the-day")));
    sExistingApps.add(new ExistingApp("com.supercell.clashofclans", new Series("https://player.fm/series/clash-of-clans-podcast-119423")));
    sExistingApps.add(new ExistingApp("com.nbaimd.gametime.nba2011", new Series("https://player.fm/series/the-starters")));
    sExistingApps.add(new ExistingApp("com.Slack", new Series("https://player.fm/series/slack-variety-pack")));
    sExistingApps.add(new ExistingApp("com.fiverr.fiverr", new Series("https://player.fm/series/fiverrcast-the-podcast-for-fiverr-sellers-by-sellers")));
    sExistingApps.add(new ExistingApp("com.ebay.mobile", new Series("https://player.fm/series/ebay-radio-58516")));
    sExistingApps.add(new ExistingApp("com.amazon.windowshop", new Series("https://player.fm/series/the-amazing-seller-podcast")));
  }
  
  public ExistingApps() {}
  
  public static List<ExistingApp> getMatchesChannelsWithExistingApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return localArrayList;
      }
      String str = ((ApplicationInfo)paramContext.next()).packageName;
      Alog.v("ExistingApps", "listAppsPackages: " + str);
      Iterator localIterator = sExistingApps.iterator();
      if (localIterator.hasNext())
      {
        ExistingApp localExistingApp = (ExistingApp)localIterator.next();
        if ((!localExistingApp.packageName.equals(str)) || (localExistingApp.channel == null)) {
          break;
        }
        if (!localArrayList.contains(localExistingApp)) {
          localArrayList.add(localExistingApp);
        }
        localExistingApp.matchesCount += 1;
        Alog.v("ExistingApps", "getMatchesChannelsWithExistingApps: " + str);
      }
    }
    return localArrayList;
  }
  
  public static List<ExistingApp> getMatchesSeriesWithExistingApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return localArrayList;
      }
      String str = ((ApplicationInfo)paramContext.next()).packageName;
      Alog.v("ExistingApps", "listAppsPackages: " + str);
      Iterator localIterator = sExistingApps.iterator();
      if (localIterator.hasNext())
      {
        ExistingApp localExistingApp = (ExistingApp)localIterator.next();
        if ((!localExistingApp.packageName.equals(str)) || (localExistingApp.series == null)) {
          break;
        }
        if (!localArrayList.contains(localExistingApp)) {
          localArrayList.add(localExistingApp);
        }
        localExistingApp.matchesCount += 1;
        Alog.v("ExistingApps", "getMatchesChannelsWithExistingApps: " + str);
      }
    }
    return localArrayList;
  }
  
  public static class ExistingApp
  {
    Channel channel;
    int matchesCount;
    String packageName;
    Series series;
    
    public ExistingApp(String paramString, Channel paramChannel)
    {
      this.packageName = paramString;
      this.channel = paramChannel;
    }
    
    public ExistingApp(String paramString, Channel paramChannel, Series paramSeries)
    {
      this.packageName = paramString;
      this.channel = paramChannel;
      this.series = paramSeries;
    }
    
    public ExistingApp(String paramString, Series paramSeries)
    {
      this.packageName = paramString;
      this.series = paramSeries;
    }
  }
}
