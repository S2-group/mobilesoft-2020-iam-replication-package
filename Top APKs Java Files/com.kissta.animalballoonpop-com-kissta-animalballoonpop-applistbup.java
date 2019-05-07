package com.kissta.animalballoonpop;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class applistbup
  extends Activity
{
  private static boolean backpressedonce = false;
  public String appName;
  private ArrayList<String> apps;
  int appstore = 4;
  final Context context = this;
  private final Runnable exit = new Runnable()
  {
    public void run()
    {
      applistbup.backpressedonce = false;
    }
  };
  int focus = 1;
  public int givenscreenSize;
  GridView gridview;
  int height = 0;
  boolean installed1 = false;
  boolean installed10 = false;
  boolean installed101 = false;
  boolean installed102 = false;
  boolean installed103 = false;
  boolean installed104 = false;
  boolean installed105 = false;
  boolean installed106 = false;
  boolean installed107 = false;
  boolean installed108 = false;
  boolean installed109 = false;
  boolean installed11 = false;
  boolean installed110 = false;
  boolean installed111 = false;
  boolean installed112 = false;
  boolean installed113 = false;
  boolean installed12 = false;
  boolean installed13 = false;
  boolean installed14 = false;
  boolean installed15 = false;
  boolean installed16 = false;
  boolean installed17 = false;
  boolean installed18 = false;
  boolean installed19 = false;
  boolean installed2 = true;
  boolean installed20 = false;
  boolean installed201 = false;
  boolean installed202 = false;
  boolean installed203 = false;
  boolean installed204 = false;
  boolean installed205 = false;
  boolean installed206 = false;
  boolean installed207 = false;
  boolean installed208 = false;
  boolean installed21 = false;
  boolean installed22 = false;
  boolean installed23 = false;
  boolean installed24 = false;
  boolean installed25 = false;
  boolean installed26 = false;
  boolean installed27 = false;
  boolean installed28 = false;
  boolean installed29 = false;
  boolean installed3 = false;
  boolean installed30 = false;
  boolean installed31 = false;
  boolean installed32 = false;
  boolean installed4 = false;
  boolean installed5 = false;
  boolean installed6 = false;
  boolean installed7 = false;
  boolean installed8 = false;
  boolean installed9 = false;
  boolean installedBalloonPaid = false;
  boolean installedCrazyPaid = false;
  boolean installedDollhouse2Paid = false;
  boolean installedDollhousePaid = false;
  boolean installedDressupPaid = false;
  boolean installedFishNumbersPaid = false;
  boolean installedPianoPaid = false;
  boolean installedcleanPaid = false;
  Handler myHandler = new Handler();
  int openapp = 0;
  int openthisapp = 2;
  private final Runnable playmusic = new Runnable()
  {
    public void run()
    {
      
      if (applistbup.this.hasWindowFocus()) {
        Playsound2.play(applistbup.this, 5);
      }
    }
  };
  private final Runnable sendemoff = new Runnable()
  {
    public void run()
    {
      Toast.makeText(applistbup.this, applistbup.this.getResources().getString(2131165232), 1).show();
      Intent localIntent = new Intent("android.intent.action.MAIN");
      switch (applistbup.this.openapp)
      {
      }
      for (;;)
      {
        Playsound2.stop();
        try
        {
          localIntent.putExtra("straighttogame", "truefalse");
          applistbup.this.startActivityForResult(localIntent, 0);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          Toast.makeText(applistbup.this.getApplicationContext(), "Please cancel, exit and then update/install the latest version of the application you are trying to open", 1).show();
        }
        localIntent.setComponent(new ComponentName("com.kissta.fireworks", "com.kissta.fireworks.openapp"));
        continue;
        if (applistbup.this.installedBalloonPaid)
        {
          localIntent.setComponent(new ComponentName("com.kissta.balloonpaid", "com.kissta.balloonpaid.openapp"));
        }
        else
        {
          localIntent = new Intent(applistbup.this, MainActivity.class);
          continue;
          localIntent.setComponent(new ComponentName("com.kisstakoala.babycolor", "familygames.androidpack.playme.openapp"));
          continue;
          localIntent.setComponent(new ComponentName("com.kissta.drums", "com.kissta.drums.openapp"));
          continue;
          localIntent.setComponent(new ComponentName("com.kissta.feedthetoddler", "com.kissta.feedthetoddler.openapp"));
          continue;
          localIntent.setComponent(new ComponentName("com.kissta.animalphone", "com.kissta.animalphone.openapp"));
          continue;
          localIntent.setComponent(new ComponentName("com.kissta.phone", "com.kissta.phone.openapp"));
          continue;
          if (applistbup.this.installedCrazyPaid)
          {
            localIntent.setComponent(new ComponentName("com.kissta.pianocrazypaid", "com.kissta.pianocrazypaid.openapp"));
          }
          else
          {
            localIntent.setComponent(new ComponentName("com.kissta.crazypiano", "com.kissta.crazypiano.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.undersea", "com.kissta.undersea.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.animalsounds", "com.kissta.animalsounds.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.animalbubbles", "com.kissta.animalbubbles.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.pianoold", "com.kissta.pianoold.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchport", "com.kissta.scratchport.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchprin", "com.kissta.scratchprin.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchcars", "com.kissta.scratchcars.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchanimals", "com.kissta.scratchanimals.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchport2", "com.kissta.scratchport2.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.scratchcolor", "com.kissta.scratchcolor.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dndanimals", "com.kissta.dndanimals.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dragndrop", "com.kissta.dragndrop.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dragndropfun", "com.kissta.dragndropfun.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.kitchen", "com.kissta.kitchen.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dinolearn", "com.kissta.dinolearn.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kisstakoala.vehiclesfree", "com.kisstakoala.vehiclesfree.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.ltca", "com.kissta.ltca.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.ltcount", "com.kissta.ltcount.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.shopping", "com.kissta.shopping.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.peekaboo", "com.kissta.peekaboo.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.peekaboofarm", "com.kissta.peekaboofarm.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dinodig", "com.kissta.dinodig.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.abcanimals", "com.kissta.abcanimals.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.christmas", "com.kissta.christmas.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.ftb2", "com.kissta.ftb2.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kisstakoala.letters", "com.kisstakoala.letters.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.mns", "com.kissta.mns.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.dots", "com.kissta.dots.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.shapes", "com.kissta.shapes.openapp"));
            continue;
            localIntent.setComponent(new ComponentName("com.kissta.fishing", "com.kissta.fishing.openapp"));
            continue;
            if (applistbup.this.installedPianoPaid)
            {
              localIntent.setComponent(new ComponentName("com.kissta.pianopaid", "com.kissta.pianopaid.openapp"));
            }
            else
            {
              localIntent.setComponent(new ComponentName("com.kissta.piano2", "com.kissta.piano2.openapp"));
              continue;
              localIntent.setComponent(new ComponentName("com.kissta.easycolor", "com.kissta.easycolor.openapp"));
              continue;
              localIntent.setComponent(new ComponentName("com.kissta.trace", "com.kissta.trace.openapp"));
              continue;
              localIntent.setComponent(new ComponentName("com.kissta.insect1", "com.kissta.insect1.openapp"));
              continue;
              localIntent.setComponent(new ComponentName("com.kissta.abccars", "com.kissta.abccars.openapp"));
              continue;
              if (applistbup.this.installedcleanPaid)
              {
                localIntent.setComponent(new ComponentName("com.kissta.cleanpaid", "com.kissta.cleanpaid.openapp"));
              }
              else
              {
                localIntent.setComponent(new ComponentName("com.kissta.clean", "com.kissta.clean.openapp"));
                continue;
                localIntent.setComponent(new ComponentName("com.kissta.cartonly", "com.kissta.cartonly.openapp"));
                continue;
                localIntent.setComponent(new ComponentName("com.kissta.kidmatch", "familygames.androidpack.playme.openapp"));
                continue;
                localIntent.setComponent(new ComponentName("com.kissta.pigevo1", "com.kissta.pigevo1.openapp"));
                continue;
                localIntent.setComponent(new ComponentName("com.kissta.evo1", "com.kissta.evo1.openapp"));
                continue;
                if (applistbup.this.installedDressupPaid)
                {
                  localIntent.setComponent(new ComponentName("com.kissta.dupaid", "com.kissta.dupaid.openapp"));
                }
                else
                {
                  localIntent.setComponent(new ComponentName("com.kissta.dressupsone", "com.kissta.dressupsone.openapp"));
                  continue;
                  if (applistbup.this.installedDollhousePaid)
                  {
                    localIntent.setComponent(new ComponentName("com.kissta.dollhousepaid", "com.kissta.dollhousepaid.openapp"));
                  }
                  else
                  {
                    localIntent.setComponent(new ComponentName("com.kissta.dollhouse", "com.kissta.dollhouse.openapp"));
                    continue;
                    if (applistbup.this.installedDollhouse2Paid)
                    {
                      localIntent.setComponent(new ComponentName("com.kissta.dollhouse2paid", "com.kissta.dollhouse2paid.openapp"));
                    }
                    else
                    {
                      localIntent.setComponent(new ComponentName("com.kissta.dollhouse2", "com.kissta.dollhouse2.openapp"));
                      continue;
                      localIntent.setComponent(new ComponentName("com.kissta.gf", "com.kissta.gf.openapp"));
                      continue;
                      if (applistbup.this.installedFishNumbersPaid) {
                        localIntent.setComponent(new ComponentName("com.kissta.mathfishpaid", "com.kissta.mathfishpaid.openapp"));
                      } else {
                        localIntent.setComponent(new ComponentName("com.kissta.mathfish", "com.kissta.mathfish.openapp"));
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  };
  int small = 2;
  private MyGridviewAdapter ssAdapter;
  boolean stopmusic = true;
  private final Runnable stopthemusic = new Runnable()
  {
    public void run()
    {
      if (applistbup.this.stopmusic) {
        Playsound2.stop();
      }
      applistbup.this.myHandler.removeCallbacks(applistbup.this.playmusic);
    }
  };
  private final Runnable stopthemusicNOW = new Runnable()
  {
    public void run() {}
  };
  boolean wenttogetnewapp = false;
  int width = 0;
  
  public applistbup() {}
  
  private boolean appInstalledOrNot(String paramString)
  {
    PackageManager localPackageManager = getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  private void displaychangeage()
  {
    this.ssAdapter = new MyGridviewAdapter(this, this.apps);
    this.gridview.setAdapter(this.ssAdapter);
    updategridview();
  }
  
  private void do1to4()
  {
    if (this.installed1)
    {
      this.apps.set(0, "Fireworks");
      if (!this.installedBalloonPaid) {
        break label650;
      }
      this.apps.set(1, "Balloons Paid");
      label38:
      if (!this.installed3) {
        break label687;
      }
      this.apps.set(2, "Colors");
      label57:
      if (!this.installed4) {
        break label702;
      }
      this.apps.set(3, "Drums");
      label76:
      if (!this.installed5) {
        break label717;
      }
      this.apps.set(4, "Feed the Baby");
      label95:
      if (!this.installed6) {
        break label732;
      }
      this.apps.set(5, "Animal Phone");
      label114:
      if (!this.installed7) {
        break label747;
      }
      this.apps.set(6, "Baby Phone");
      label134:
      if (!this.installedCrazyPaid) {
        break label763;
      }
      this.apps.set(7, "Fun Effects+");
      label154:
      if (!this.installed9) {
        break label802;
      }
      this.apps.set(8, "Undersea Adventure");
      label174:
      if (!this.installed10) {
        break label818;
      }
      this.apps.set(9, "Animal Sounds");
      label194:
      if (!this.installed11) {
        break label834;
      }
      this.apps.set(10, "Animal Bubbles");
      label214:
      if (!this.installed12) {
        break label850;
      }
      this.apps.set(11, "Piano Fun");
      label234:
      if (!this.installed13) {
        break label866;
      }
      this.apps.set(12, "Scratch Game");
      label254:
      if (!this.installed14) {
        break label882;
      }
      this.apps.set(13, "Princess Game");
      label274:
      if (!this.installed15) {
        break label898;
      }
      this.apps.set(14, "Transport Game");
      label294:
      if (!this.installed16) {
        break label914;
      }
      this.apps.set(15, "Animal Game");
      label314:
      if (!this.installed17) {
        break label930;
      }
      this.apps.set(16, "Reveal Puzzles");
      label334:
      if (!this.installed18) {
        break label946;
      }
      this.apps.set(17, "Touch Painting");
      label354:
      if (!this.installed19) {
        break label962;
      }
      this.apps.set(18, "Animal Shape Puzzles");
      label374:
      if (!this.installed20) {
        break label978;
      }
      this.apps.set(19, "Shape Puzzles");
      label394:
      if (!this.installed21) {
        break label994;
      }
      this.apps.set(20, "Fun Play Puzzles");
      label414:
      if (!this.installed22) {
        break label1010;
      }
      this.apps.set(21, "Kitchen Party");
      label434:
      if (!this.installed23) {
        break label1026;
      }
      this.apps.set(22, "Dinosaur Learning");
      label454:
      if (!this.installed24) {
        break label1042;
      }
      this.apps.set(23, "Cars for Kids");
      label474:
      if (!this.installed25) {
        break label1058;
      }
      this.apps.set(24, "Learn to Count Animals");
      label494:
      if (!this.installed26) {
        break label1074;
      }
      this.apps.set(25, "Learn to Count");
      label514:
      if (!this.installed27) {
        break label1090;
      }
      this.apps.set(26, "Baby Goes Shopping");
      label534:
      if (!this.installed28) {
        break label1106;
      }
      this.apps.set(27, "Peekaboo");
      label554:
      if (!this.installed29) {
        break label1122;
      }
      this.apps.set(28, "Peekaboo Farm");
      label574:
      if (!this.installed30) {
        break label1138;
      }
      this.apps.set(29, "Dinosaur Dig");
      label594:
      if (!this.installed31) {
        break label1154;
      }
      this.apps.set(30, "ABC Animals");
    }
    for (;;)
    {
      if (!this.installed32) {
        break label1170;
      }
      this.apps.set(31, "Christmas");
      return;
      this.apps.set(0, "Fireworks ");
      break;
      label650:
      if (this.installed2)
      {
        this.apps.set(1, "Balloons");
        break label38;
      }
      this.apps.set(1, "Balloons ");
      break label38;
      label687:
      this.apps.set(2, "Colors ");
      break label57;
      label702:
      this.apps.set(3, "Drums ");
      break label76;
      label717:
      this.apps.set(4, "Feed the Baby ");
      break label95;
      label732:
      this.apps.set(5, "Animal Phone ");
      break label114;
      label747:
      this.apps.set(6, "Baby Phone ");
      break label134;
      label763:
      if (this.installed8)
      {
        this.apps.set(7, "Fun Effects");
        break label154;
      }
      this.apps.set(7, "Fun Effects ");
      break label154;
      label802:
      this.apps.set(8, "Undersea Adventure ");
      break label174;
      label818:
      this.apps.set(9, "Animal Sounds ");
      break label194;
      label834:
      this.apps.set(10, "Animal Bubbles ");
      break label214;
      label850:
      this.apps.set(11, "Piano Fun ");
      break label234;
      label866:
      this.apps.set(12, "Scratch Game ");
      break label254;
      label882:
      this.apps.set(13, "Princess Game ");
      break label274;
      label898:
      this.apps.set(14, "Transport Game ");
      break label294;
      label914:
      this.apps.set(15, "Animal Game ");
      break label314;
      label930:
      this.apps.set(16, "Reveal Puzzles ");
      break label334;
      label946:
      this.apps.set(17, "Touch Painting ");
      break label354;
      label962:
      this.apps.set(18, "Animal Shape Puzzles ");
      break label374;
      label978:
      this.apps.set(19, "Shape Puzzles ");
      break label394;
      label994:
      this.apps.set(20, "Fun Play Puzzles ");
      break label414;
      label1010:
      this.apps.set(21, "Kitchen Party ");
      break label434;
      label1026:
      this.apps.set(22, "Dinosaur Learning ");
      break label454;
      label1042:
      this.apps.set(23, "Cars for Kids ");
      break label474;
      label1058:
      this.apps.set(24, "Learn to Count Animals ");
      break label494;
      label1074:
      this.apps.set(25, "Learn to Count ");
      break label514;
      label1090:
      this.apps.set(26, "Baby Goes Shopping ");
      break label534;
      label1106:
      this.apps.set(27, "Peekaboo ");
      break label554;
      label1122:
      this.apps.set(28, "Peekaboo Farm ");
      break label574;
      label1138:
      this.apps.set(29, "Dinosaur Dig ");
      break label594;
      label1154:
      this.apps.set(30, "ABC Animals ");
    }
    label1170:
    this.apps.set(31, "Christmas ");
  }
  
  private void do3to6()
  {
    if (this.installed101)
    {
      this.apps.set(0, "Feed the Baby 2");
      if (!this.installed102) {
        break label517;
      }
      this.apps.set(1, "ABC Letters");
      label38:
      if (!this.installed103) {
        break label532;
      }
      this.apps.set(2, "Match n Spell");
      label57:
      if (!this.installed104) {
        break label547;
      }
      this.apps.set(3, "Dots");
      label76:
      if (!this.installed105) {
        break label562;
      }
      this.apps.set(4, "Learn Shapes");
      label95:
      if (!this.installed106) {
        break label577;
      }
      this.apps.set(5, "Fishing Match");
      label114:
      if (!this.installedPianoPaid) {
        break label592;
      }
      this.apps.set(6, "Piano Deluxe+");
      label134:
      if (!this.installed108) {
        break label631;
      }
      this.apps.set(7, "Easy Coloring");
      label154:
      if (!this.installed109) {
        break label647;
      }
      this.apps.set(8, "Letter Tracing");
      label174:
      if (!this.installed110) {
        break label663;
      }
      this.apps.set(9, "Touch and Make");
      label194:
      if (!this.installed111) {
        break label679;
      }
      this.apps.set(10, "Car ABCs");
      label214:
      if (!this.installedcleanPaid) {
        break label695;
      }
      this.apps.set(11, "Cleanup Deluxe");
      label234:
      if (!this.installed113) {
        break label734;
      }
      this.apps.set(12, "Car Racer");
    }
    for (;;)
    {
      this.apps.set(13, " ");
      this.apps.set(14, " ");
      this.apps.set(15, " ");
      this.apps.set(16, " ");
      this.apps.set(17, " ");
      this.apps.set(18, " ");
      this.apps.set(19, " ");
      this.apps.set(20, " ");
      this.apps.set(21, " ");
      this.apps.set(22, " ");
      this.apps.set(23, " ");
      this.apps.set(24, " ");
      this.apps.set(25, " ");
      this.apps.set(26, " ");
      this.apps.set(27, " ");
      this.apps.set(28, " ");
      this.apps.set(29, " ");
      this.apps.set(30, " ");
      this.apps.set(31, " ");
      return;
      this.apps.set(0, "Feed the Baby 2 ");
      break;
      label517:
      this.apps.set(1, "ABC Letters ");
      break label38;
      label532:
      this.apps.set(2, "Match n Spell ");
      break label57;
      label547:
      this.apps.set(3, "Dots ");
      break label76;
      label562:
      this.apps.set(4, "Learn Shapes ");
      break label95;
      label577:
      this.apps.set(5, "Fishing Match ");
      break label114;
      label592:
      if (this.installed107)
      {
        this.apps.set(6, "Piano Deluxe");
        break label134;
      }
      this.apps.set(6, "Piano Deluxe ");
      break label134;
      label631:
      this.apps.set(7, "Easy Coloring ");
      break label154;
      label647:
      this.apps.set(8, "Letter Tracing ");
      break label174;
      label663:
      this.apps.set(9, "Touch and Make ");
      break label194;
      label679:
      this.apps.set(10, "Car ABCs ");
      break label214;
      label695:
      if (this.installed112)
      {
        this.apps.set(11, "Backyard Cleanup");
        break label234;
      }
      this.apps.set(11, "Backyard Cleanup ");
      break label234;
      label734:
      this.apps.set(12, "Car Racer ");
    }
  }
  
  private void do4plus()
  {
    if (this.installed201)
    {
      this.apps.set(0, "Memory Match");
      if (!this.installed202) {
        break label482;
      }
      this.apps.set(1, "Piggy Evolution");
      label38:
      if (!this.installed203) {
        break label497;
      }
      this.apps.set(2, "Panda Evolution");
      label57:
      if (!this.installedDressupPaid) {
        break label512;
      }
      this.apps.set(3, "Dress Up Deluxe");
      label76:
      if (!this.installedDollhousePaid) {
        break label549;
      }
      this.apps.set(4, "Doll House Deluxe");
      label95:
      if (!this.installedDollhouse2Paid) {
        break label586;
      }
      this.apps.set(5, "Doll House Princess+");
      label114:
      if (!this.installed207) {
        break label623;
      }
      this.apps.set(6, "Grumpy Farmer");
      label134:
      if (!this.installedFishNumbersPaid) {
        break label639;
      }
      this.apps.set(7, "Math Fish+");
    }
    for (;;)
    {
      this.apps.set(8, " ");
      this.apps.set(9, " ");
      this.apps.set(10, " ");
      this.apps.set(11, " ");
      this.apps.set(12, " ");
      this.apps.set(13, " ");
      this.apps.set(14, " ");
      this.apps.set(15, " ");
      this.apps.set(16, " ");
      this.apps.set(17, " ");
      this.apps.set(18, " ");
      this.apps.set(19, " ");
      this.apps.set(20, " ");
      this.apps.set(21, " ");
      this.apps.set(22, " ");
      this.apps.set(23, " ");
      this.apps.set(24, " ");
      this.apps.set(25, " ");
      this.apps.set(26, " ");
      this.apps.set(27, " ");
      this.apps.set(28, " ");
      this.apps.set(29, " ");
      this.apps.set(30, " ");
      this.apps.set(31, " ");
      return;
      this.apps.set(0, "Memory Match ");
      break;
      label482:
      this.apps.set(1, "Piggy Evolution ");
      break label38;
      label497:
      this.apps.set(2, "Panda Evolution ");
      break label57;
      label512:
      if (this.installed204)
      {
        this.apps.set(3, "Dress Up");
        break label76;
      }
      this.apps.set(3, "Dress Up ");
      break label76;
      label549:
      if (this.installed205)
      {
        this.apps.set(4, "Doll House");
        break label95;
      }
      this.apps.set(4, "Doll House ");
      break label95;
      label586:
      if (this.installed206)
      {
        this.apps.set(5, "Doll House Princess");
        break label114;
      }
      this.apps.set(5, "Doll House Princess ");
      break label114;
      label623:
      this.apps.set(6, "Grumpy Farmer ");
      break label134;
      label639:
      if (this.installed208) {
        this.apps.set(7, "Math Fish");
      } else {
        this.apps.set(7, "Math Fish ");
      }
    }
  }
  
  private void installcheck1()
  {
    this.installed1 = appInstalledOrNot("com.kissta.fireworks");
    this.installed2 = true;
    this.installed3 = appInstalledOrNot("com.kisstakoala.babycolor");
    this.installed4 = appInstalledOrNot("com.kissta.drums");
    this.installed5 = appInstalledOrNot("com.kissta.feedthetoddler");
    this.installed6 = appInstalledOrNot("com.kissta.animalphone");
    this.installed7 = appInstalledOrNot("com.kissta.phone");
    this.installed8 = appInstalledOrNot("com.kissta.crazypiano");
    this.installed9 = appInstalledOrNot("com.kissta.undersea");
    this.installed10 = appInstalledOrNot("com.kissta.animalsounds");
    this.installed11 = appInstalledOrNot("com.kissta.animalbubbles");
    this.installed12 = appInstalledOrNot("com.kissta.pianoold");
    this.installed13 = appInstalledOrNot("com.kissta.scratchport");
    this.installed14 = appInstalledOrNot("com.kissta.scratchprin");
    this.installed15 = appInstalledOrNot("com.kissta.scratchcars");
    this.installed16 = appInstalledOrNot("com.kissta.scratchanimals");
    this.installed17 = appInstalledOrNot("com.kissta.scratchport2");
    this.installed18 = appInstalledOrNot("com.kissta.scratchcolor");
    this.installed19 = appInstalledOrNot("com.kissta.dndanimals");
    this.installed20 = appInstalledOrNot("com.kissta.dragndrop");
    this.installed21 = appInstalledOrNot("com.kissta.dragndropfun");
    this.installed22 = appInstalledOrNot("com.kissta.kitchen");
    this.installed23 = appInstalledOrNot("com.kissta.dinolearn");
    this.installed24 = appInstalledOrNot("com.kisstakoala.vehiclesfree");
    this.installed25 = appInstalledOrNot("com.kissta.ltca");
    this.installed26 = appInstalledOrNot("com.kissta.ltcount");
    this.installed27 = appInstalledOrNot("com.kissta.shopping");
    this.installed28 = appInstalledOrNot("com.kissta.peekaboo");
    this.installed29 = appInstalledOrNot("com.kissta.peekaboofarm");
    this.installed30 = appInstalledOrNot("com.kissta.dinodig");
    this.installed31 = appInstalledOrNot("com.kissta.abcanimals");
    this.installed32 = appInstalledOrNot("com.kissta.christmas");
    this.installedCrazyPaid = appInstalledOrNot("com.kissta.pianocrazypaid");
    this.installedBalloonPaid = appInstalledOrNot("com.kissta.balloonpaid");
  }
  
  private void installcheck2()
  {
    this.installed101 = appInstalledOrNot("com.kissta.ftb2");
    this.installed102 = appInstalledOrNot("com.kisstakoala.letters");
    this.installed103 = appInstalledOrNot("com.kissta.mns");
    this.installed104 = appInstalledOrNot("com.kissta.dots");
    this.installed105 = appInstalledOrNot("com.kissta.shapes");
    this.installed106 = appInstalledOrNot("com.kissta.fishing");
    this.installed107 = appInstalledOrNot("com.kissta.piano2");
    this.installed108 = appInstalledOrNot("com.kissta.easycolor");
    this.installed109 = appInstalledOrNot("com.kissta.trace");
    this.installed110 = appInstalledOrNot("com.kissta.insect1");
    this.installed111 = appInstalledOrNot("com.kissta.abccars");
    this.installed112 = appInstalledOrNot("com.kissta.clean");
    this.installed113 = appInstalledOrNot("com.kissta.cartonly");
    this.installedPianoPaid = appInstalledOrNot("com.kissta.pianopaid");
  }
  
  private void installcheck3()
  {
    this.installed201 = appInstalledOrNot("com.kissta.kidmatch");
    this.installed202 = appInstalledOrNot("com.kissta.pigevo1");
    this.installed203 = appInstalledOrNot("com.kissta.evo1");
    this.installed204 = appInstalledOrNot("com.kissta.dressupsone");
    this.installed205 = appInstalledOrNot("com.kissta.dollhouse");
    this.installed206 = appInstalledOrNot("com.kissta.dollhouse2");
    this.installed207 = appInstalledOrNot("com.kissta.gf");
    this.installed208 = appInstalledOrNot("com.kissta.mathfish");
    this.installedDressupPaid = appInstalledOrNot("com.kissta.dupaid");
    this.installedDollhousePaid = appInstalledOrNot("com.kissta.dollhousepaid");
  }
  
  private void updateage()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131296304);
    switch (this.focus)
    {
    default: 
      return;
    case 1: 
      if (this.small == 1)
      {
        localLinearLayout.setBackgroundResource(2130837512);
        return;
      }
      if (this.small == 2)
      {
        localLinearLayout.setBackgroundResource(2130837513);
        return;
      }
      if (this.small == 3)
      {
        localLinearLayout.setBackgroundResource(2130837514);
        return;
      }
      localLinearLayout.setBackgroundResource(2130837515);
      return;
    case 2: 
      if (this.small == 1)
      {
        localLinearLayout.setBackgroundResource(2130837517);
        return;
      }
      if (this.small == 2)
      {
        localLinearLayout.setBackgroundResource(2130837518);
        return;
      }
      if (this.small == 3)
      {
        localLinearLayout.setBackgroundResource(2130837519);
        return;
      }
      localLinearLayout.setBackgroundResource(2130837520);
      return;
    }
    if (this.small == 1)
    {
      localLinearLayout.setBackgroundResource(2130837522);
      return;
    }
    if (this.small == 2)
    {
      localLinearLayout.setBackgroundResource(2130837523);
      return;
    }
    if (this.small == 3)
    {
      localLinearLayout.setBackgroundResource(2130837524);
      return;
    }
    localLinearLayout.setBackgroundResource(2130837525);
  }
  
  private void updategridview()
  {
    MyGridviewAdapter.notifyDataChanged();
    this.gridview.invalidateViews();
  }
  
  public boolean isApplicationIstalledByPackageName(String paramString)
  {
    Object localObject = getPackageManager().getInstalledPackages(0);
    if ((localObject != null) && (paramString != null)) {
      localObject = ((List)localObject).iterator();
    }
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return false;
      }
    } while (!paramString.equals(((PackageInfo)((Iterator)localObject).next()).packageName));
    return true;
  }
  
  public void onBackPressed()
  {
    if (backpressedonce)
    {
      this.myHandler.removeCallbacks(this.exit);
      backpressedonce = false;
      Playsound2.stop();
      this.myHandler.postDelayed(this.stopthemusic, 550L);
      this.myHandler.postDelayed(this.stopthemusicNOW, 1500L);
      finish();
      return;
    }
    Toast.makeText(this, getResources().getString(2131165240), 1).show();
    backpressedonce = true;
    this.myHandler.postDelayed(this.exit, 2600L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SoundManager.initSounds(getApplicationContext());
    SoundManager.loadSounds();
    int i;
    label184:
    Display localDisplay;
    label270:
    label690:
    int j;
    if (this.openthisapp < 101)
    {
      this.focus = 1;
      this.installedPianoPaid = appInstalledOrNot("com.kissta.pianopaid");
      this.installedFishNumbersPaid = appInstalledOrNot("com.kissta.mathfishpaid");
      this.installedCrazyPaid = appInstalledOrNot("com.kissta.pianocrazypaid");
      this.installedBalloonPaid = appInstalledOrNot("com.kissta.balloonpaid");
      this.installedDressupPaid = appInstalledOrNot("com.kissta.dupaid");
      this.installedDollhousePaid = appInstalledOrNot("com.kissta.dollhousepaid");
      this.installedDollhouse2Paid = appInstalledOrNot("com.kissta.dollhouse2paid");
      this.installedcleanPaid = appInstalledOrNot("com.kissta.cleanpaid");
      paramBundle = getResources().getConfiguration();
      i = paramBundle.screenLayout & 0xF;
      paramBundle.screenLayout = i;
      this.givenscreenSize = i;
      switch (this.givenscreenSize)
      {
      default: 
        this.small = 4;
        setContentView(2130903044);
        this.gridview = ((GridView)findViewById(2131296308));
        this.gridview.setSoundEffectsEnabled(false);
        paramBundle = (ImageButton)findViewById(2131296310);
        if (Build.VERSION.SDK_INT >= 13)
        {
          localDisplay = getWindowManager().getDefaultDisplay();
          Point localPoint = new Point();
          localDisplay.getSize(localPoint);
          this.width = localPoint.x;
          this.height = localPoint.y;
          paramBundle.getLayoutParams().height = ((int)(this.height * 0.17D));
          paramBundle.getLayoutParams().width = ((int)(this.height * 0.363D));
          paramBundle.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              applistbup.this.openapp = applistbup.this.openthisapp;
              applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
            }
          });
          this.apps = new ArrayList();
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          this.apps.add(" ");
          if (this.openthisapp >= 101) {
            break label929;
          }
          i = 1;
          if (this.openthisapp != 999) {
            break label934;
          }
          j = 1;
          label702:
          if ((i | j) == 0) {
            break label939;
          }
          this.focus = 1;
          installcheck1();
          do1to4();
          updateage();
        }
        break;
      }
    }
    for (;;)
    {
      displaychangeage();
      ((ImageButton)findViewById(2131296305)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (applistbup.this.focus != 1)
          {
            applistbup.this.installcheck1();
            applistbup.this.focus = 1;
            paramAnonymousView = (LinearLayout)applistbup.this.findViewById(2131296304);
            if (applistbup.this.small != 1) {
              break label71;
            }
            paramAnonymousView.setBackgroundResource(2130837512);
          }
          for (;;)
          {
            applistbup.this.do1to4();
            applistbup.this.displaychangeage();
            return;
            label71:
            if (applistbup.this.small == 2) {
              paramAnonymousView.setBackgroundResource(2130837513);
            } else if (applistbup.this.small == 3) {
              paramAnonymousView.setBackgroundResource(2130837514);
            } else {
              paramAnonymousView.setBackgroundResource(2130837515);
            }
          }
        }
      });
      ((ImageButton)findViewById(2131296306)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (applistbup.this.focus != 2)
          {
            applistbup.this.installcheck2();
            applistbup.this.focus = 2;
            paramAnonymousView = (LinearLayout)applistbup.this.findViewById(2131296304);
            if (applistbup.this.small != 1) {
              break label71;
            }
            paramAnonymousView.setBackgroundResource(2130837517);
          }
          for (;;)
          {
            applistbup.this.do3to6();
            applistbup.this.displaychangeage();
            return;
            label71:
            if (applistbup.this.small == 2) {
              paramAnonymousView.setBackgroundResource(2130837518);
            } else if (applistbup.this.small == 3) {
              paramAnonymousView.setBackgroundResource(2130837519);
            } else {
              paramAnonymousView.setBackgroundResource(2130837520);
            }
          }
        }
      });
      ((ImageButton)findViewById(2131296307)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (applistbup.this.focus != 3)
          {
            applistbup.this.installcheck3();
            applistbup.this.focus = 3;
            paramAnonymousView = (LinearLayout)applistbup.this.findViewById(2131296304);
            if (applistbup.this.small != 1) {
              break label71;
            }
            paramAnonymousView.setBackgroundResource(2130837522);
          }
          for (;;)
          {
            applistbup.this.do4plus();
            applistbup.this.displaychangeage();
            return;
            label71:
            if (applistbup.this.small == 2) {
              paramAnonymousView.setBackgroundResource(2130837523);
            } else if (applistbup.this.small == 3) {
              paramAnonymousView.setBackgroundResource(2130837524);
            } else {
              paramAnonymousView.setBackgroundResource(2130837525);
            }
          }
        }
      });
      this.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (applistbup.this.focus == 1) {
            switch (paramAnonymousInt)
            {
            }
          }
          for (;;)
          {
            if (applistbup.this.wenttogetnewapp)
            {
              Playsound2.stop();
              if (applistbup.this.appstore != 1) {
                break;
              }
            }
            else
            {
              try
              {
                if (applistbup.this.isApplicationIstalledByPackageName("com.slideme.sam.manager"))
                {
                  paramAnonymousAdapterView = new Intent("android.intent.action.VIEW");
                  paramAnonymousAdapterView.setData(Uri.parse("sam://search?q=pub:kisstakoala"));
                  applistbup.this.startActivity(paramAnonymousAdapterView);
                  return;
                  if (applistbup.this.installed1)
                  {
                    applistbup.this.openapp = 1;
                    applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                  }
                  else
                  {
                    applistbup.this.wenttogetnewapp = true;
                    applistbup.this.appName = "com.kissta.fireworks";
                    continue;
                    if (applistbup.this.installed2)
                    {
                      applistbup.this.openapp = 2;
                      applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                    }
                    else
                    {
                      applistbup.this.wenttogetnewapp = true;
                      applistbup.this.appName = "com.kissta.animalballoonpop";
                      continue;
                      if (applistbup.this.installed3)
                      {
                        applistbup.this.openapp = 3;
                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                      }
                      else
                      {
                        applistbup.this.wenttogetnewapp = true;
                        applistbup.this.appName = "com.kisstakoala.babycolor";
                        continue;
                        if (applistbup.this.installed4)
                        {
                          applistbup.this.openapp = 4;
                          applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                        }
                        else
                        {
                          applistbup.this.wenttogetnewapp = true;
                          applistbup.this.appName = "com.kissta.drums";
                          continue;
                          if (applistbup.this.installed5)
                          {
                            applistbup.this.openapp = 5;
                            applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                          }
                          else
                          {
                            applistbup.this.wenttogetnewapp = true;
                            applistbup.this.appName = "com.kissta.feedthetoddler";
                            continue;
                            if (applistbup.this.installed6)
                            {
                              applistbup.this.openapp = 6;
                              applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                            }
                            else
                            {
                              applistbup.this.wenttogetnewapp = true;
                              applistbup.this.appName = "com.kissta.animalphone";
                              continue;
                              if (applistbup.this.installed7)
                              {
                                applistbup.this.openapp = 7;
                                applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                              }
                              else
                              {
                                applistbup.this.wenttogetnewapp = true;
                                applistbup.this.appName = "com.kissta.phone";
                                continue;
                                if ((applistbup.this.installed8) || (applistbup.this.installedCrazyPaid))
                                {
                                  applistbup.this.openapp = 8;
                                  applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                }
                                else
                                {
                                  applistbup.this.wenttogetnewapp = true;
                                  applistbup.this.appName = "com.kissta.crazypiano";
                                  continue;
                                  if (applistbup.this.installed9)
                                  {
                                    applistbup.this.openapp = 9;
                                    applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                  }
                                  else
                                  {
                                    applistbup.this.wenttogetnewapp = true;
                                    applistbup.this.appName = "com.kissta.undersea";
                                    continue;
                                    if (applistbup.this.installed10)
                                    {
                                      applistbup.this.openapp = 10;
                                      applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                    }
                                    else
                                    {
                                      applistbup.this.wenttogetnewapp = true;
                                      applistbup.this.appName = "com.kissta.animalsounds";
                                      continue;
                                      if (applistbup.this.installed11)
                                      {
                                        applistbup.this.openapp = 11;
                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                      }
                                      else
                                      {
                                        applistbup.this.wenttogetnewapp = true;
                                        applistbup.this.appName = "com.kissta.animalbubbles";
                                        continue;
                                        if (applistbup.this.installed12)
                                        {
                                          applistbup.this.openapp = 12;
                                          applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                        }
                                        else
                                        {
                                          applistbup.this.wenttogetnewapp = true;
                                          applistbup.this.appName = "com.kissta.pianoold";
                                          continue;
                                          if (applistbup.this.installed13)
                                          {
                                            applistbup.this.openapp = 13;
                                            applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                          }
                                          else
                                          {
                                            applistbup.this.wenttogetnewapp = true;
                                            applistbup.this.appName = "com.kissta.scratchport";
                                            continue;
                                            if (applistbup.this.installed14)
                                            {
                                              applistbup.this.openapp = 14;
                                              applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                            }
                                            else
                                            {
                                              applistbup.this.wenttogetnewapp = true;
                                              applistbup.this.appName = "com.kissta.scratchprin";
                                              continue;
                                              if (applistbup.this.installed15)
                                              {
                                                applistbup.this.openapp = 15;
                                                applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                              }
                                              else
                                              {
                                                applistbup.this.wenttogetnewapp = true;
                                                applistbup.this.appName = "com.kissta.scratchcars";
                                                continue;
                                                if (applistbup.this.installed16)
                                                {
                                                  applistbup.this.openapp = 16;
                                                  applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                }
                                                else
                                                {
                                                  applistbup.this.wenttogetnewapp = true;
                                                  applistbup.this.appName = "com.kissta.scratchanimals";
                                                  continue;
                                                  if (applistbup.this.installed17)
                                                  {
                                                    applistbup.this.openapp = 17;
                                                    applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                  }
                                                  else
                                                  {
                                                    applistbup.this.wenttogetnewapp = true;
                                                    applistbup.this.appName = "com.kissta.scratchport2";
                                                    continue;
                                                    if (applistbup.this.installed18)
                                                    {
                                                      applistbup.this.openapp = 18;
                                                      applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                    }
                                                    else
                                                    {
                                                      applistbup.this.wenttogetnewapp = true;
                                                      applistbup.this.appName = "com.kissta.scratchcolor";
                                                      continue;
                                                      if (applistbup.this.installed19)
                                                      {
                                                        applistbup.this.openapp = 19;
                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                      }
                                                      else
                                                      {
                                                        applistbup.this.wenttogetnewapp = true;
                                                        applistbup.this.appName = "com.kissta.dndanimals";
                                                        continue;
                                                        if (applistbup.this.installed20)
                                                        {
                                                          applistbup.this.openapp = 20;
                                                          applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                        }
                                                        else
                                                        {
                                                          applistbup.this.wenttogetnewapp = true;
                                                          applistbup.this.appName = "com.kissta.dragndrop";
                                                          continue;
                                                          if (applistbup.this.installed21)
                                                          {
                                                            applistbup.this.openapp = 21;
                                                            applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                          }
                                                          else
                                                          {
                                                            applistbup.this.wenttogetnewapp = true;
                                                            applistbup.this.appName = "com.kissta.dragndropfun";
                                                            continue;
                                                            if (applistbup.this.installed22)
                                                            {
                                                              applistbup.this.openapp = 22;
                                                              applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                            }
                                                            else
                                                            {
                                                              applistbup.this.wenttogetnewapp = true;
                                                              applistbup.this.appName = "com.kissta.kitchen";
                                                              continue;
                                                              if (applistbup.this.installed23)
                                                              {
                                                                applistbup.this.openapp = 23;
                                                                applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                              }
                                                              else
                                                              {
                                                                applistbup.this.wenttogetnewapp = true;
                                                                applistbup.this.appName = "com.kissta.dinolearn";
                                                                continue;
                                                                if (applistbup.this.installed24)
                                                                {
                                                                  applistbup.this.openapp = 24;
                                                                  applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                }
                                                                else
                                                                {
                                                                  applistbup.this.wenttogetnewapp = true;
                                                                  applistbup.this.appName = "com.kisstakoala.vehiclesfree";
                                                                  continue;
                                                                  if (applistbup.this.installed25)
                                                                  {
                                                                    applistbup.this.openapp = 25;
                                                                    applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                  }
                                                                  else
                                                                  {
                                                                    applistbup.this.wenttogetnewapp = true;
                                                                    applistbup.this.appName = "com.kissta.ltca";
                                                                    continue;
                                                                    if (applistbup.this.installed26)
                                                                    {
                                                                      applistbup.this.openapp = 26;
                                                                      applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                    }
                                                                    else
                                                                    {
                                                                      applistbup.this.wenttogetnewapp = true;
                                                                      applistbup.this.appName = "com.kissta.ltcount";
                                                                      continue;
                                                                      if (applistbup.this.installed27)
                                                                      {
                                                                        applistbup.this.openapp = 27;
                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                      }
                                                                      else
                                                                      {
                                                                        applistbup.this.wenttogetnewapp = true;
                                                                        applistbup.this.appName = "com.kissta.shopping";
                                                                        continue;
                                                                        if (applistbup.this.installed28)
                                                                        {
                                                                          applistbup.this.openapp = 28;
                                                                          applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                        }
                                                                        else
                                                                        {
                                                                          applistbup.this.wenttogetnewapp = true;
                                                                          applistbup.this.appName = "com.kissta.peekaboo";
                                                                          continue;
                                                                          if (applistbup.this.installed29)
                                                                          {
                                                                            applistbup.this.openapp = 29;
                                                                            applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                          }
                                                                          else
                                                                          {
                                                                            applistbup.this.wenttogetnewapp = true;
                                                                            applistbup.this.appName = "com.kissta.peekaboofarm";
                                                                            continue;
                                                                            if (applistbup.this.installed30)
                                                                            {
                                                                              applistbup.this.openapp = 30;
                                                                              applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                            }
                                                                            else
                                                                            {
                                                                              applistbup.this.wenttogetnewapp = true;
                                                                              applistbup.this.appName = "com.kissta.dinodig";
                                                                              continue;
                                                                              if (applistbup.this.installed31)
                                                                              {
                                                                                applistbup.this.openapp = 31;
                                                                                applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                              }
                                                                              else
                                                                              {
                                                                                applistbup.this.wenttogetnewapp = true;
                                                                                applistbup.this.appName = "com.kissta.abcanimals";
                                                                                continue;
                                                                                if (applistbup.this.installed32)
                                                                                {
                                                                                  applistbup.this.openapp = 32;
                                                                                  applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                }
                                                                                else
                                                                                {
                                                                                  applistbup.this.wenttogetnewapp = true;
                                                                                  applistbup.this.appName = "com.kissta.christmas";
                                                                                  continue;
                                                                                  if (applistbup.this.focus == 2) {
                                                                                    switch (paramAnonymousInt)
                                                                                    {
                                                                                    default: 
                                                                                      break;
                                                                                    case 0: 
                                                                                      if (applistbup.this.installed101)
                                                                                      {
                                                                                        applistbup.this.openapp = 101;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.ftb2";
                                                                                      break;
                                                                                    case 1: 
                                                                                      if (applistbup.this.installed102)
                                                                                      {
                                                                                        applistbup.this.openapp = 102;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kisstakoala.letters";
                                                                                      break;
                                                                                    case 2: 
                                                                                      if (applistbup.this.installed103)
                                                                                      {
                                                                                        applistbup.this.openapp = 103;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.mns";
                                                                                      break;
                                                                                    case 3: 
                                                                                      if (applistbup.this.installed104)
                                                                                      {
                                                                                        applistbup.this.openapp = 104;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.dots";
                                                                                      break;
                                                                                    case 4: 
                                                                                      if (applistbup.this.installed105)
                                                                                      {
                                                                                        applistbup.this.openapp = 105;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.shapes";
                                                                                      break;
                                                                                    case 5: 
                                                                                      if (applistbup.this.installed106)
                                                                                      {
                                                                                        applistbup.this.openapp = 106;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.fishing";
                                                                                      break;
                                                                                    case 6: 
                                                                                      if ((applistbup.this.installed107) || (applistbup.this.installedPianoPaid))
                                                                                      {
                                                                                        applistbup.this.openapp = 107;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.piano2";
                                                                                      break;
                                                                                    case 7: 
                                                                                      if (applistbup.this.installed108)
                                                                                      {
                                                                                        applistbup.this.openapp = 108;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.easycolor";
                                                                                      break;
                                                                                    case 8: 
                                                                                      if (applistbup.this.installed109)
                                                                                      {
                                                                                        applistbup.this.openapp = 109;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.trace";
                                                                                      break;
                                                                                    case 9: 
                                                                                      if (applistbup.this.installed110)
                                                                                      {
                                                                                        applistbup.this.openapp = 110;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.insect1";
                                                                                      break;
                                                                                    case 10: 
                                                                                      if (applistbup.this.installed111)
                                                                                      {
                                                                                        applistbup.this.openapp = 111;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.abccars";
                                                                                      break;
                                                                                    case 11: 
                                                                                      if ((applistbup.this.installed112) || (applistbup.this.installedcleanPaid))
                                                                                      {
                                                                                        applistbup.this.openapp = 112;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.clean";
                                                                                      break;
                                                                                    case 12: 
                                                                                      if (applistbup.this.installed113)
                                                                                      {
                                                                                        applistbup.this.openapp = 113;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.cartonly";
                                                                                      break;
                                                                                    }
                                                                                  } else {
                                                                                    switch (paramAnonymousInt)
                                                                                    {
                                                                                    default: 
                                                                                      break;
                                                                                    case 0: 
                                                                                      if (applistbup.this.installed201)
                                                                                      {
                                                                                        applistbup.this.openapp = 201;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.kidmatch";
                                                                                      break;
                                                                                    case 1: 
                                                                                      if (applistbup.this.installed202)
                                                                                      {
                                                                                        applistbup.this.openapp = 202;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.pigevo1";
                                                                                      break;
                                                                                    case 2: 
                                                                                      if (applistbup.this.installed203)
                                                                                      {
                                                                                        applistbup.this.openapp = 203;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.evo1";
                                                                                      break;
                                                                                    case 3: 
                                                                                      if ((applistbup.this.installed204) || (applistbup.this.installedDressupPaid))
                                                                                      {
                                                                                        applistbup.this.openapp = 204;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 225L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.dressupsone";
                                                                                      break;
                                                                                    case 4: 
                                                                                      if ((applistbup.this.installed205) || (applistbup.this.installedDollhousePaid))
                                                                                      {
                                                                                        applistbup.this.openapp = 205;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.dollhouse";
                                                                                      break;
                                                                                    case 5: 
                                                                                      if ((applistbup.this.installed206) || (applistbup.this.installedDollhouse2Paid))
                                                                                      {
                                                                                        applistbup.this.openapp = 206;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.dollhouse2";
                                                                                      break;
                                                                                    case 6: 
                                                                                      if (applistbup.this.installed207)
                                                                                      {
                                                                                        applistbup.this.openapp = 207;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.gf";
                                                                                      break;
                                                                                    case 7: 
                                                                                      if ((applistbup.this.installed208) || (applistbup.this.installedFishNumbersPaid))
                                                                                      {
                                                                                        applistbup.this.openapp = 208;
                                                                                        applistbup.this.myHandler.postDelayed(applistbup.this.sendemoff, 235L);
                                                                                        continue;
                                                                                      }
                                                                                      applistbup.this.wenttogetnewapp = true;
                                                                                      applistbup.this.appName = "com.kissta.mathfish";
                                                                                      break;
                                                                                    }
                                                                                  }
                                                                                }
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else
                {
                  applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
                  return;
                }
              }
              catch (ActivityNotFoundException paramAnonymousAdapterView)
              {
                applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
                return;
              }
            }
          }
          if (applistbup.this.appstore == 2) {
            try
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + applistbup.this.appName)));
              return;
            }
            catch (ActivityNotFoundException paramAnonymousAdapterView)
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
              return;
            }
          }
          if (applistbup.this.appstore == 3) {
            try
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + applistbup.this.appName)));
              return;
            }
            catch (ActivityNotFoundException paramAnonymousAdapterView)
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
              return;
            }
          }
          if (applistbup.this.appstore == 4) {
            try
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
              return;
            }
            catch (ActivityNotFoundException paramAnonymousAdapterView)
            {
              applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + applistbup.this.appName)));
              return;
            }
          }
          try
          {
            applistbup.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + applistbup.this.appName)));
            return;
          }
          catch (ActivityNotFoundException paramAnonymousAdapterView)
          {
            Toast.makeText(applistbup.this.getApplicationContext(), "Not available at this store at this time. Please check your 2nd choice of App Store for available Kissta apps", 1).show();
          }
        }
      });
      return;
      if (this.openthisapp < 201)
      {
        this.focus = 2;
        break;
      }
      if (this.openthisapp < 301)
      {
        this.focus = 3;
        break;
      }
      this.focus = 1;
      break;
      this.small = 3;
      setContentView(2130903042);
      break label184;
      this.small = 2;
      setContentView(2130903041);
      break label184;
      this.small = 1;
      setContentView(2130903043);
      break label184;
      localDisplay = getWindowManager().getDefaultDisplay();
      this.height = localDisplay.getHeight();
      this.width = localDisplay.getWidth();
      break label270;
      label929:
      i = 0;
      break label690;
      label934:
      j = 0;
      break label702;
      label939:
      if (this.openthisapp < 201)
      {
        this.focus = 2;
        installcheck2();
        do3to6();
        updateage();
      }
      else if (this.openthisapp < 301)
      {
        this.focus = 3;
        installcheck3();
        do4plus();
        updateage();
      }
    }
  }
  
  public void onPause()
  {
    super.onPause();
    Playsound2.stop();
    this.myHandler.removeCallbacks(this.sendemoff);
    this.myHandler.postDelayed(this.stopthemusic, 320L);
  }
  
  public void onResume()
  {
    super.onResume();
    this.stopmusic = true;
    try
    {
      SoundManager.initSounds(getApplicationContext());
      SoundManager.loadSounds();
      if (this.wenttogetnewapp)
      {
        if (this.focus == 1)
        {
          installcheck1();
          do1to4();
        }
        if (this.focus == 2)
        {
          installcheck2();
          do3to6();
        }
        if (this.focus == 3)
        {
          installcheck3();
          do4plus();
        }
        displaychangeage();
        updategridview();
      }
      Playsound2.stop();
      this.wenttogetnewapp = false;
      if (getRequestedOrientation() == 1) {
        setRequestedOrientation(0);
      }
      return;
    }
    catch (Exception localException)
    {
      Playsound2.stop();
      SoundManager.initSounds(getApplicationContext());
      SoundManager.loadSounds();
    }
  }
  
  public void onStop()
  {
    super.onStop();
    if (this.stopmusic)
    {
      Playsound2.stop();
      this.myHandler.postDelayed(this.stopthemusic, 350L);
    }
    this.myHandler.removeCallbacks(this.playmusic);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean)
    {
      Playsound2.stop();
      this.stopmusic = false;
      this.myHandler.postDelayed(this.playmusic, 500L);
      return;
    }
    Playsound2.stop();
    this.myHandler.removeCallbacks(this.playmusic);
    this.myHandler.postDelayed(this.stopthemusic, 250L);
    this.myHandler.postDelayed(this.stopthemusic, 500L);
  }
}
