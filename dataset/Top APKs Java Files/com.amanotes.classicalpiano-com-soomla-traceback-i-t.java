package com.soomla.traceback.i;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.soomla.traceback.AdAction;
import com.soomla.traceback.AdListener;
import com.soomla.traceback.Advertising.AdType;
import com.soomla.traceback.InstallBroadcastReceiver;
import com.soomla.traceback.LocalEvent;
import com.soomla.traceback.LocalEventConsts;
import com.soomla.traceback.LogUtils;
import com.soomla.traceback.SafeRunnable;
import com.soomla.traceback.SoomlaTraceback;
import com.soomla.traceback.TracebackListener;
import com.soomla.traceback.UidReadyCallback;
import com.soomla.traceback.UserAdActionsCallback;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class t
  extends SoomlaTraceback
{
  private static char[] ˊᐝ;
  private static int ˋˋ = 1;
  private static int ˌ = 0;
  private static long ˍ = -636240300156391434L;
  private static t ˎ;
  private static final String[] ॱ;
  private boolean ʻ = false;
  private Handler ʻॱ;
  private boolean ʼ = false;
  private at ʼॱ = null;
  private boolean ʽ = false;
  private aq ʽॱ = null;
  private Thread ʾ;
  private BlockingQueue<ax> ʿ = new PriorityBlockingQueue();
  private aw ˈ = null;
  private au ˉ = new au();
  private boolean ˊ = false;
  private boolean ˊˊ = false;
  private av ˊˋ;
  private i ˊॱ = null;
  private boolean ˋ = false;
  private Set<String> ˋˊ = new HashSet();
  private e ˋॱ;
  private r ˏ;
  private List<ax> ˏॱ;
  private Context ͺ;
  private List<TracebackListener> ॱˊ = new ArrayList();
  private int ॱˋ;
  private Thread ॱˎ;
  private l ॱॱ;
  private int ॱᐝ;
  private Thread.UncaughtExceptionHandler ᐝ;
  private boolean ᐝॱ;
  
  static
  {
    char[] arrayOfChar = new char['༹'];
    CharBuffer localCharBuffer = ByteBuffer.wrap("®\013Aáqûa÷\021Ì\001×1¼!¶ÑÁñ§á¯²G±O¡Y\000SïßÏ¿´¯¯ÈÑoÅ_ùOð?é/\035\037\037\017Jÿ\027ï7ß?Ïb¿Y¯BVqob^NÒ>.¶\036¡\016¾þ¤îÙÞÛÎÌ¾¶\000SïßÏ¿´¯¯ÈÑoÅ_ùOð?é/\035\037\037\017Jÿ\027ï7ß?Ïb¿V¯KRbo&^N>.ª\036½\016«þ¬îßÞÖÎÇ¾ü®®©Z~\036n\t^|N<>-.[\036P\016\nþTîyÞ,Îq½­¾}¿m±]ÒM8×yçL÷nOD§O·!G W.g\rñ,\036í.÷>äNÃ^nÞ~»¬»®¾ÎÞpî=þg\016f\036H.K\000eïßÏ¿¿\000eïßÏ¿¿©F}vvft\026E\006R$hËûë¾\000EïßÏ¿ª¯î¥ÞÔoÏ_òOõ?¨/\033\037\006\017\030ÿ\017ï$ßlÏ/¿]¯]W{wocÁÉ.;\036\016\016:~\027n\001^\026Nu\000CïßÏÅ¿¬¯î­ÔÙoÒ_õOó?ä/\027\037\016\017\017ÿ@ï{ßlÏ\021¿W¯AIvqoR^N>.»\036¶\016«þ£îÝÞÎÕ¾ù®ý¤\t~\030n\023^(N6>'.I\036Z\016\004\000SïßÏ¿´¯¯ÈÑoÅ_ùOð?é/\035\037\037\017Jÿ\tï%ßlÏ#¿T¯\\A{to^ÜN>.·\036 \016£þ¡îÚÞÅÎØ¾ý®êª\000SïßÏ¿´¯¯ÈÑoÅ_ùOð?é/\035\037\037\017Jÿ\tï8ß%Ï6¿Q¯OHsjoo^N>Æ.ð\036ú\000Wï·ß¾Ï¬¿¯o_¼OÖ?ç/^\037\032\017\005ÿ\024ïvß+Ï-¿\030¯BMluo&^N>.¶\036ô\016¾þ¥îÅÞØÎï¾÷®êáG~\004n\024^)N7>i.\037\036\025\016\nþeî`ÞiÎl½­Ä­}¹m²]ÔM=Ü-û\035ç\rþýÍí\031Ý\bÍ\007½e­:6O}Um\006]kM{=d,\034Ô\füìöÜ¨Ì«¼Ë¬ÍÅè|ôlã\\\030LS<I,Øz!J1Z)*\016:\036\n\031\032-êfúlÊ\013ÚjªBº¾£°jzJZùE\026¬&¤6F°V­f½vÄ\000eïßÏ¿¬¯à;*ÔàäúôöÍÖ¤é´±D¨T¼dt\004\024d$f43ÄjÔ[äTôI5>¤3´\004DIT\031eêuù\005±\025Ò%ß5ßÅÕï¿b~RdBw2P\"\024\022W\002.ò#â(Ò\017Â\t²\036¢íôùrôbËRBë2­\"»\022³\002òâÜÓRÃz³s£GKRs[c/S=.ÇÁ\023ñ\006áA(j±3¡[Q@A\002qmae\021i\0011Ð!ÑÁòñåáæïÅ±Ï¡óQøAãp,`\004\020\r\000905 ,Ð%ÀQðCà\006kk°s ÞP@p­`¢\020¨\000Õ0Ç ÀÐ\000YïßÏÂ¿µ¯»·ÎoÏ_òOû?ü/\027\037\025\017\006ÿ\tï,ß)Ïb¿k¯AKw|og^¨N>.½\036±\016¨þ¡îÕÞÇÎ¾ú®ëâ\025~\002n\003^|N1>).R\036X\016CþNîqÞ,Î%½­}£m£]ÎMû=Ì-¹utEAUc%O5O\005E\025\027å'õ:Å\n\000EïßÏ¿ª¯î·ßÄoÒ_õOü?ï/^\037\001\017\031ÿ\005ï$ß\005Ï&¿\030·ÐX1h+x8\b\037\030z(q8ZÈVØVè{ø'T¯¨Á¸HXãh·x¢\bÁ\030×(8¯ÈÕØÿé\fù\006\016.©a¹2I4YHi\\y\027\t~\031n)c9ÉÅÙé¦ùç­Ê©Ò¹ÌIYâi¹yá\n\f\032\027*\030:+ÊeÚ=êFúI\020eªtºsJyZÃjz\n¨\032©*ñ:æÊáÚêýúè½8«\016»\020K\030[/k8{\003\013_\033Z+R;jËgÛrëûÓ\033}âMï]ì-Ê=Ý\rÐ\035§íäý¥ÍÝ­Ü½so~mn\000Eï¤ß¾Ï­¿¯ïäãßoÓ_»Oà?í/^\037\001\017\031ÿ\tï8ß+Ïb¿L¯FA:toc^N>.²\036 \016êþµîÅÞÉÎÐ¾¸®ÇÀZ\000.ïÖß¼Ï¿½¯¯·ßoË_ýOù?í/^\037\007\017\037ÿ\022ï3ßlÏ6¿W¯\016T{cou^ÜN>È.«\036º\016£þ±îÃÞÉÎ¾í®ýá\b~Pn/^\030Nr>..Q\036F\016\nþEîwÞoÎj½Ø­¿}¢mæ]ÈMÝ=-Í\035û\råýíí\032Ý\rÍ6½*­/'_}RmG]My=&\000[ïßÁÏ¿¯ão_±OÍ?§/#\037^\000Eï¤ß¾Ï­¿¯ïäÏÃoÃ_îO²?Á/:\037T\000 ïßÏ¿¬¯¯­ÔÃo_õOþ?ä/\033\037\023\017\013ÿ\fïvß/Ï*¿Y¯\\Eydoc^N>Æ.þ\036\016¦þ¥î×ÞßÎÇ¾¸®ãå\021~\025nF^/N'>:.[\036\024\016^þOî6Þ|Îc½­Ä»}ðm³]ÏM×=Ú-¾\035Ý\rÎý í\025Ý\003Í\f½,­/-T}YmH]{M2=g,\034\füÀìµÜ¤Ì£¼Ê¬Ýü|âlé\\\021LR<3,?\034y\f0ü\001ì\033ÜvÌ\022¼5¬7)¥{ßk»[üK¦;§+\033ç\013ÅûÏëûÛàËã»,«\034\0059{5k$[]KQ;C+0|¶v£v³Ã\027ÓrãDó:\0032\023%#\0223]C\022Sâcþs÷¯ð£ç³Ã¾Ó²ãñóÕ\000TïßÌÏ¿±¯«³üoÏ_êO÷?¨/;\037\002\017\017ÿ\016ï\"ßlÏ\016¿W¯IW:woi^ÜN>.þ\036¼\016¾þ´îÆÞÎ¾·®úö\033~\023n\003^>N3>+.U\036\032\016YþOîyÞaÎ,½­Ä÷}îmæ]åMÝ=Ý-ì\035´\rëýðí\006ÝLÍO½f­n\027_}DmR]uM|=o,\034Ô\fÇüÞìöÜÌ¬¼Ì¬ËÖº|élé\\\tL\000<H,+\034'\f/ü2ì\026ÜeÌf\000iïßÏÌ¿«¯«·ÉùoÂ\000CïßÏÅ¿¬¯î·ßÄo_èO÷?û/\n\037T\017\007ÿ\017ï2ß)Ïb¿\025¯\016wuok^N>¼.¬\036µ\016©þ¥îÔÞÍÎÁ¾ó®®ó\033~\003nF^/N:>=.J\036P\016EþWîxÞ\"'DÈøèñ´¸¨¨ÇXHÛxðhè\030õ\bW8\020(\fØ\rÈ:øeè)TA¸B¨aX|H/yi\031\t£9´)¢Ù¥ÉÖùßéÂÿà¹¬õ|\032*:JªZºj½zÄÕªõºêÊûÚ\027êGú\026\n\036\032?*8:0JKZ\035j{zFYO«¤»½Ë¬Ûëû\013\033å+ö;úKÁO½ AV^ð-àsÐvÀ\0230\030 \t\020\024\000,p6`Ë4GÛ¶ë¡û°\000fïß\r\004âëÒð[a´\000pïßÏ´\016[ÏkÕ{Æ\013á\033¥+î;ËÛë¹û¾ãS«V»SKX[ik'{`\013\035\033\026+\033;0Ë7Û!êúÕÂæªëº¡JþZjz\n§\032 *ï:EÊRÚ@êrú9w\032ª_º\013J\030Z2j)Õ»:o\nz\032=jTz\026J]Z&ª,º~\020\013ê\027úõÊ¬Ú¿*¸:ý\nÛ\032Õj­zºJ½Z¶ªºgoërûGËOÛY+\030;9\0135\033)k@{\005K\024[÷«ü»úËÝëÞûè\000aïßÏ¶¿¹¯©·ÃoÎ_óOç?ä/\032\037T\017\bÿ\005ïvß9Ï1¿]¯J\004{vor^N>È.·\036º\016£þ´îßÞÍÎÎ¾ñ®ôí\024~\027nF^\017N=>'.S\036X\016KþtîdÞmÎa½­¹}»\000EïßÏ¿ª¯î¥ÞÔoÏ_òOõ?¨/\n\037\025\017\rÿ@\000.ïÖß¸Ï¿¿¯½äÙÑoÈ_òOý?ü/^\037\026\017\017ÿ@ï:ß#Ï,¿_¯KV:don^N>È.ë\036ä\016êþ£îÞÞÍÎÐ¾ë® ·X}hgxR\bC\030@(L8&\000EïßÏ¿ª¯î¥ÞÔoÏ_òOõ?¨/\n\037\025\017\rÿ\023ïv\000CïßÏÅ¿¬¯î¶ßÝoÉ_êO÷?¨/\n\037\025\017\rÿ\023ïvßaÏb¿k¯AKw|og^¨N>.½\036±\016¨þ¡îÕÞÇÎ¾ï®ï÷Z~\003n\016^)N&>,.Q\036C\016Dþ\016oâ\003°\021 \035Ð>À;ð\000àK\020G\000E0, qPp@p`æ°¾ ·ÐÀËðÇàï\020ä\000¶1\r!\004Q\fA+q6az9H±U¡FÑaÁñxá\021\0011¢!¥QøAýqËaÕÝê±ý¡ÆÒ\032Â\037ò\027â/\022\"\00272O\"IG{¦K´[¸+;\013®\033ûëäûôËÚ\000EïßÏ¿ª¯î¶ßÝoÉ_êOû?æ/\031\037T\017\036ÿ\001ï1ß?Ïbp÷#¯6¿qÏ\030ßZï\003ÿk\017p\0372/I?BO\034_¦o©­ ¯¿Ïþßºï½ÿ\017÷\037Ý.'>+N0^\013n4~\f\025a®}¾tÎMÞYî[þî\016³\036³.>ÆN^ânõ~êðÍ®Ï¾ØÍb\000CïßÏÅ¿¬¯î£ßÄo_éOá?í/\f\037T\017\013ÿ\004ïvß-Ï!¿L¯GKtco&^ÑNÒ>».±\036»\016§þ¬î×ÞøÎÐ¾ù®íá\030~\021n\005^7Nr>?._\036G\016\nþSî~ÞyÎv½­´}þ\000gïßÏ·¿«¯«¶ûÔoç_ÿOæ?á/\021\037\032\017\031ÿ@ï%ß$Ï-¿M¯B@:roc^ÜN>.»\036°\016êþ¡îÐÞØÎÇ¾ê®®í\024~\031n\022^5N3>$.W\036N\016CþNîqÞ,ÎQ½­¶}±m]ÎMÓ=Ë-û\035ö\rëýãí\035\000AïßÏ¿¹¯ª½ÖoÃ_èOñ?à/\027\037\032\017\rÿ@ï?ß\"Ï1¿Q¯ILncMö¢\007\000\024ò â9Ò/ÂV2\001\"U\022b\002~rcbRB²¢½¶\000iïßÏ¿¿¯¦°ÉoÅ_ýOñ?à/\033\000EïßÏ¿ª¯î£ßÄoÒ_õOü?ï/^\037\001\017\031ÿ\005ï$ßlÏ#¿\\¯\016Eydoo^N>.þ\036²\016¸þ¯îÛÞÎÁ¾ù®íì\037\000hïßÏ¿«¯ôëÙoÈ_ïOû?ï/\026\037\000\017\031ÿNï%ß#Ï-¿U¯\000H{?oe^N>.¬\036 \016¹z\001À¥ÚµÉÅîÕªåçõ\005\025%±5¸E«U\032eEu]A`¥(µgÅ\030ÕJå\001õ=\005 \025+$×4ØDß4\023ÛâëâûýÐÚ«ß»¬Kê[¹k{\013\033v/KÀðàÍ¤æ°£ ÄPÝ@Üpæ`ó\020ä\000\0230\\ \001Ð\007À+ð*à>B_°\f qPw@jqaÚ\021Í\001ö1!­Ñ§ÁÓñÈáËÄô±í¡\021Q\035A\fq5a9\021+\001\0261K!CÑ[Á>ñwáb²¢½R¯B r\000CïßÏÅ¿¬¯î·ßÞoÂ_¼Oû?æ/S\037\025\017\032ÿ\020ïvß<Ï7¿J¯ML{coc^ÜN>.»\036º\016¾þàîÞÎñ¾÷®áé\026~\021n2^.N3>+.[\036V\016KþCî}Þ,Îu½­Ä©}¸m³]ÈMÖ=Ç-é\035ú\r¤\000iïßÏ\000pïßÏ¿½¼Stcisg\000iïßÏ½¿»¯¡©ÊÜoÃ_èO÷?ì\000EïßÏ¿ª¯î§ÈÕoÇ_èOû?æ/\031\037T\017#ÿ!ï\006ßlÏ(¿K¯AJ\000aïßÏ¿·¯ ·\000fïßÏ¿ö¯¯°ÎÂoõ_ùOü?ü/PÒ%=Æ\rÜ\035ÔmÃ}øMã]­,ÖÃ'ó>\000tïßHe§¼îS/c5s&\003\001\023E#\0343tÃoÓyã^óWDõ£¾³µC¿Scs\003æ\023ñ#æ3ÞÃÕÓâ3ò87\024\000.ïßÏ¿½¯©¶ÛÄoÏ_óOü?û/P­B\022r.b3\022\013\002\0332\026\"kÒcÂ}Y\037¶ÎÓÀæçöùÆþÖ&6\006¤\026£fþvAFLVH¦S¶ghuæ\032ö\021Æ\035Ö\"&f\000Dï¿ß¿Ï£¿¯þ\000YïßÏ¿ø¯õýoê_ÝO²\fvãÃÓÕÃÚ³à£ýñscSêC¶3»#Y\023W\003UóDãeÓiÃ4³\017£X\034)s1c5RØB2È\"í\022ð\002ïòÿâÒÂÔ²¡¢¾òXrNbUR*Be2y\"\r\022\f\002\bòXâ`Ò\nÂ8±Ë¡ÙÁéq¦aåQA1!¼\021§\001üñ¢áHÑ_Á\024±o¡w\002q\022a^\000 ïßÏ¿¶¯«§ÎßoÔ_¼Oä?í/\f\037\007\017\003ÿ\017ï8ßl\000 ïßÏ¿­¯§¶ßÃo_ÈOà?é/\035\037\021\017\bÿ\001ï5ß'Ïb¿Y¯IAtdo&^N>.­\036½\016¥þ®î\000 ïßÏ¿ø¯¯¦ÕÆoÃ_¼Oº?ë/\013\037\006\017\030ÿ\005ï8ß8Ïb¿Y¯IAtdo&^N>ÈAÚ®03$þ\0062Ú\000Aï¢ß¸Ï§¿¯õþo_½O³?¨/0\037\033\017JªoEÜuÕeÈ\025ù\005õ5ø%ÕÕÅõ¿å±çSµ^¥\005U\\E|umey\025W\005\0255\004%uÕ\fÅ\006ôüäðëÐ´µÎS!\021\001qò\000 ïßÏ¿ø¯§·ÞoÉ_èO²?û/\013\037\004\017\032ÿ\017ï$ß8Ï'¿\\¯\016Fc0or^N>È.\036\016þîúÞíÎ½\001R´b¢r­\002\022\"2ïÂþÒõâòù3¢0²*B2R\022bMr\026\002i\022k\"d2OÂTÒ\007ã©ó»¬ß£³CSùcèsà\003Í\023À#×3u?+Ðàðó³ ª°Ã@ÈPÄ`øp÷\000£\000 ïßÏÂ¿¶¯¡°ÃoÓ_ìOâ?ç/\f\037\000\017\017ÿ\004ïvß.Ï;¿\030\000 ïßÏ¿«¯§«Ôz\tØ¥ØµÇÅÿÕüååõ\005\025%²5òâ\r3=(-=]SM\003}\000mdui\000EïßÏ¿ª¯î­ÔÙoÒ_õOó?ä/\027\037\016\017\003ÿ\016ï1ßlÏ+¿V¯ZA}bog^N>.°\036ô\000Eï¸ß­Ï ¿¯]«²X_L\000EïßÏ¿ª¯î¥ÞÔoÏ_òOõ?¨/\016\037\030\017\037ÿ\007ï?ß\"Ïb¿N¯KViyoi^N\b\tçê×öÇìÊç%&\025<\005/u\beLU\007E|µv¥mPWõ\nå¨Õ·Å¯5±%Î\025Î\037$ðôÀåÐû Æ°Ù¾`¯pö@P 0j\000$\020àfðCÀRÐF r°~\000 ïßÏ¿°¯î¡ÂÄoÔ_ýO²?á/\020\037\022\017\005ÿZïv&TÉùé©¬¹°©ÃYÎIÑy¬\026oùÜÉÕÙÈ©ù¹õ«ØißyIºY¹)©9\026\tO\031\005é_ùxÉpÙ~©W¹\025\003'i:y:HÛXÒ(Ë8õ\032hðXç\000sïßÞ¢1N\001Y\000uïú|\025\000lïßÏ¿¯¡±ÙØìy-I7Y$)\0039G\t\016\031aé|ùnÉAÙR©O¹°ý¦i¿yIY)Ü9â\tù\031Òrþ\004­\034½QÍ$Ý=í=ýU\rB\035R-e=!Mc]m}¥­¶\000EïßÏ¿ª¯î£ßÄoÒ_õOü?ï/^\037\035\017\004ÿ\023ï\"ß-Ï.¿T¯K@:`og^N>.¹\036±\016¹þúî\000SïßÏ¿±¯ £ÕoÐ_ùOü?ü/\r\037T\017\fÿ\022ï9ß!Ïb¿[¯OGruØ*7Ï\007Æ\027Ãgãwò\000GïßÏÂ¿¹¯î·ÏÓoÅ_ùOá?û/^\037\006\017\017ÿ\023ï&ß#Ï,¿K¯K\004|ot^ÜN>.°\036°\016þ¶îÓÞÂÎÖ¾¹®®´\007[ÍkÕ{Ó\013ø\033ò+ù;ËÛÒë¿û£²^«\000»IKF[mkv{q\013L\033\r+\031;:Ë,ÛrêÛúÃÒîªÅºèJñZjzØ\nì\032*¿:ZÊ\004Ú@êmúul\005ª\016º\rJ\021ZxjxQF;3ÔþäëôæÒÍ¤´¨D¹Tàdt\004\024|$24XÄcÔ\\äOôT1:¤6´\\D\023T\026eÿuú\005ú\025%aN¾E®KÞlÎ}þ~îG\036\030\016\013>a.-^'NÌ~ÈnÓÞê¾â®ëÞÅÎþî¤\036¨\016²?W/J_GO-\000aïßÏ¿·¯§ ÞoÃ_èO¼?ë/\021\037\032\017\004ÿNï\025ß\003Ï\f¿v¯kgNYoP^µN¦>±.\036\016þîøÞëÎç\000NïßÌÏ¿ ¯º¶ÛoÏ_òOô?ç/^\037\022\017\005ÿ\022ïvß)Ï4¿]¯@P 0À]/¯\037\017¢o_\000CïßÏ¿¼¯ ãÎoÄ_éOû?ä/\032\037T\017\017ÿ\026ï3ß\"Ï6¿\030¯^KidoB^N>.ð\000nïßÏ®ÉA9q0a<\021\000\001\0211\030!dÑf\000nïßÏxr¢B¢Rµ\"2\002Ø\022õâ«òÿÂÒÒÀ¢ß²!o4b-r\bB\031R\r\"#2e\002p\022Râ_òyÃ¦Ó½£²³ËÏcsÿCøSë#3>zÌJùZÛ*÷:÷\ný\032¯êúÊ·Úªª¶\000EïßÏ¿ª¯î­ÔoÇ_ìOâ?Ü/\021\0376\017\013ÿ\003ï=ß+Ï0¿W¯[J~Îà!\001\021\030\001\025q8a.Q3AZ\t«æFÖEÆK\000cïßÏÌ¿¹¯  ÈßoÏ_øO¼?þ/\033\037\032\017\016ÿ\tï8ß+Ïl¿q¯`wNQoJ^°N­>º.\036\016þîäÞéÎð\000EïßÏ¿ª¯î·ßÞoÂ_õOü?ï/^\037\025\017\tÿ\024ï?ß#Ï,¿\030¯MKw>og^N>.±\036½\016®þîîÀÞÉÎÌ¾ü®çê\035~^n/^\022N\001>\034.\036x\016fþîDÞIÎD½½­¼¶}mæ]ÈMÝ=-cìSöCå3Â#\023Å\003¼óøãÓÃ³£wpnsJcLSKCK34#%\023-\003\001ó\fã<ÒñÂù²å¢ßÊÇrÚVî¹92)é\033ù\bÉ\035Ù*8¨×{çF÷d]H§C·*eË\030º+ª\tÚ Ê2ú+ê\\!òÎ!þ\022î>\t\000AïßÏ±¿½¯¼²\000AïßÏ¿·¯ ÞÃ\000AïßÏ®¿·¯¸­Ô\033xô¿Ä¥Ôµ¤´\000AïßÏ¿¼¯«¥Ö\000CïßÏ¿¬¯¬«ÕÃoÒ\000CïßÏ¿½¯¼·ÛÞoÒC\"¬åâðüÊììÜÍÌº<¿,©\034\f\000DïßÏ¿´¯«ÖÙoÅ_÷\000FïßÏ¿º¯¡«ÑñoèUÈº\024\027\036ê$ú9\000FïßÏ¿ª\000FïßÏ\000HïßÏ¿¹¯¾\000HïßÏ¿¯ñ,\036ý.Ä>èNß^Â\000IïßÏ¿ª¯¯§ÎÙoÐ_ùÒZ=\r\035m}²M¢]Û­À½Ð\000KïßÏÌ$#û\023å\003îsÒcÉSÀC¦\000LïßÏ¿¯º¶ßÕoÒcy£¼±¬ºÜ¾Ì\000MïßÏ¿¹¯¶ÓÈ\003Ñì\003Ü\034Ì\022¼!¬<6O|MlV\\MLk<p,\034ÙK6¾\006\026fªv©F°\000MïßÏ¤¿·¯¶\000MïßÏ¿´¯«ÕÂoÃ\000MïßÏ¿±¯½°ÛÕò:&\n\003\032(j\005ui¬Y£I°9)\031§\000OïßÏ¿¡\000OïßÏ¿4LÛëû¬³«®»ÃKÂ\000PïßÏ¬¿¹¯º­ÌÕTI»´ë¬û·\000RïßÏ¿»¯¡ªè²\007z7l'bWMG@\000SïßÏ¿¬¯´Ê\000SïßÏ¿ª¯½«ÔÙoÅ#OÌüì¬¬rCBHRe\"i2t\002c\022\0132¬Ýaí|ýoXv­Y½0\000VïßÏ¿´¯«\000YïßÏ¿·\000YïßÏ¿½¯¶\000YïßÏ¿µ¯¡¦Ó\000YïßÏ£¿¨¯¾­".getBytes("ISO-8859-1")).asCharBuffer();
    int i = 0;
    localCharBuffer.get(arrayOfChar, 0, 3897);
    ˊᐝ = arrayOfChar;
    ॱ = new String[] { ˊ('㣩', 3516, 8).intern(), ˊ('斊', 3524, 8).intern(), ˊ('↳', 3532, 5).intern(), ˊ('\000', 3537, 7).intern(), ˊ('\000', 3544, 9).intern(), ˊ('\000', 3553, 8).intern(), ˊ('ᬹ', 3561, 7).intern(), ˊ('\000', 3568, 8).intern(), ˊ('\000', 3576, 10).intern(), ˊ('\000', 3586, 10).intern(), ˊ('䍡', 3596, 12).intern(), ˊ('\000', 3608, 11).intern(), ˊ('\000', 3619, 10).intern(), ˊ('喎', 3629, 6).intern(), ˊ('\000', 3635, 5).intern(), ˊ('\000', 3640, 4).intern(), ˊ('\000', 3644, 6).intern(), ˊ('\000', 3650, 6).intern(), ˊ(61797, 3656, 6).intern(), ˊ('\000', 3662, 11).intern(), ˊ(53779, 3673, 10).intern(), ˊ('\000', 3683, 4).intern(), ˊ(52328, 3687, 8).intern(), ˊ('\000', 3695, 10).intern(), ˊ('挴', 3705, 6).intern(), ˊ('\000', 3711, 9).intern(), ˊ('Μ', 3720, 15).intern(), ˊ(55558, 3735, 7).intern(), ˊ('\000', 3742, 6).intern(), ˊ('\000', 3748, 10).intern(), ˊ('\000', 3758, 8).intern(), ˊ(54719, 3766, 5).intern(), ˊ(34363, 3771, 7).intern(), ˊ('\000', 3778, 5).intern(), ˊ('\000', 3783, 5).intern(), ˊ('㐜', 3788, 9).intern(), ˊ('\000', 3797, 9).intern(), ˊ('君', 3806, 6).intern(), ˊ('\000', 3812, 7).intern(), ˊ(59617, 3819, 6).intern(), ˊ('\000', 3825, 8).intern(), ˊ('\000', 3833, 10).intern(), ˊ('⌛', 3843, 6).intern(), ˊ(40404, 3849, 8).intern(), ˊ('㋹', 3857, 8).intern(), ˊ('\000', 3865, 6).intern(), ˊ('\000', 3871, 5).intern(), ˊ('\000', 3876, 6).intern(), ˊ('\000', 3882, 8).intern(), ˊ('\000', 3890, 7).intern() };
    int j = ˌ + 3;
    ˋˋ = j % 128;
    if (j % 2 == 0) {
      i = 1;
    }
    if (i != 1) {
      return;
    }
    throw new NullPointerException();
  }
  
  private t() {}
  
  private void ʻॱ()
  {
    try
    {
      int i = ˌ + 51;
      ˋˋ = i % 128;
      if (this.ʾ == null)
      {
        this.ʾ = new Thread(new SafeRunnable()
        {
          private static int ʽ = 0;
          private static int ˊ = -983316493;
          private static int ˋ = 86;
          private static byte[] ˎ = { 86, 80, 77, 75, 80, 80, 61, 108, -126, 45, 67, 77, 76, 78, 74, -34, -34, -89, -37, -29, -32, -36, 33, -113, -39, 50, -105, -41, -29, -39, -25, -49, -33, -24, -37, 42, -102, -35, -49, -32, -17, -53, -33, 49, -102, -31, -38, -47, -24, -46, 50, -116, -31, -38, -34, -20, -32, -46, -37, -32, 46, -103, -37, -29, -32, -4, -19, -19, -80, -26, -16, -27, 68, -102, -20, -8, 54, -90, -26, -14, -30, 0, -32, -14, -23, -24, -6, -2, 33, -20, -86, -20, -34, -15, -24, -16, -19, -6, -34, -13, -14, 54, -102, -1, -41, 68, -87, -16, -23, -32, -9, -31, 65, -101, -16, -23, -19, -5, -17, -31, -22, -17, 61, -88, -22, -14, -17, 11, 0, 0, 0 };
          private static int ˏ = -1118031922;
          private static int ॱॱ = 1;
          
          private static String ॱ(int paramAnonymousInt1, short paramAnonymousShort, byte paramAnonymousByte, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            Object localObject = new StringBuilder();
            paramAnonymousInt3 += ˋ;
            int m = 0;
            int j;
            if (paramAnonymousInt3 == -1)
            {
              j = ʽ + 71;
              ॱॱ = j % 128;
              j = 1;
            }
            else
            {
              j = 0;
            }
            if (j != 0) {
              if (ˎ != null)
              {
                paramAnonymousInt3 = (byte)(ˎ[(ˏ + paramAnonymousInt2)] + ˋ);
              }
              else
              {
                paramAnonymousInt1 = ˏ;
                throw new NullPointerException();
              }
            }
            int k;
            if (paramAnonymousInt3 > 0) {
              k = 1;
            } else {
              k = 0;
            }
            if (k != 0)
            {
              k = ʽ + 119;
              ॱॱ = k % 128;
              k = ˏ;
              if (j != 0)
              {
                j = ʽ + 103;
                ॱॱ = j % 128;
                j = 1;
              }
              else
              {
                j = 0;
              }
              paramAnonymousInt2 = paramAnonymousInt2 + paramAnonymousInt3 - 2 + k + j;
              int i = (char)(paramAnonymousInt1 + ˊ);
              ((StringBuilder)localObject).append(i);
              j = i;
              paramAnonymousInt1 = 1;
              while (paramAnonymousInt1 < paramAnonymousInt3)
              {
                if (ˎ != null)
                {
                  i = (char)(j + ((byte)(ˎ[paramAnonymousInt2] + paramAnonymousShort) ^ paramAnonymousByte));
                  paramAnonymousInt2 -= 1;
                }
                else
                {
                  throw new NullPointerException();
                }
                ((StringBuilder)localObject).append(i);
                paramAnonymousInt1 += 1;
                j = i;
              }
            }
            localObject = localObject.toString();
            paramAnonymousInt1 = ॱॱ + 51;
            ʽ = paramAnonymousInt1 % 128;
            if (paramAnonymousInt1 % 2 != 0) {
              paramAnonymousInt1 = m;
            } else {
              paramAnonymousInt1 = 1;
            }
            if (paramAnonymousInt1 != 0) {
              return localObject;
            }
            paramAnonymousInt1 = null.length;
            return localObject;
          }
          
          /* Error */
          public final void safeRun()
          {
            // Byte code:
            //   0: ldc -121
            //   2: bipush -78
            //   4: iconst_0
            //   5: ldc -120
            //   7: bipush -70
            //   9: invokestatic 138	com/soomla/traceback/i/t$5:ॱ	(ISBII)Ljava/lang/String;
            //   12: invokevirtual 143	java/lang/String:intern	()Ljava/lang/String;
            //   15: ldc -112
            //   17: bipush 34
            //   19: iconst_0
            //   20: ldc -111
            //   22: bipush -34
            //   24: invokestatic 138	com/soomla/traceback/i/t$5:ॱ	(ISBII)Ljava/lang/String;
            //   27: invokevirtual 143	java/lang/String:intern	()Ljava/lang/String;
            //   30: invokestatic 151	com/soomla/traceback/LogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
            //   33: invokestatic 157	java/lang/Thread:currentThread	()Ljava/lang/Thread;
            //   36: invokevirtual 161	java/lang/Thread:isInterrupted	()Z
            //   39: ifne +187 -> 226
            //   42: aload_0
            //   43: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   46: astore_3
            //   47: aload_3
            //   48: monitorenter
            //   49: aload_0
            //   50: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   53: invokevirtual 164	java/lang/Object:wait	()V
            //   56: aload_0
            //   57: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   60: invokestatic 168	com/soomla/traceback/i/t:ʻ	(Lcom/soomla/traceback/i/t;)Landroid/os/Handler;
            //   63: ifnonnull +32 -> 95
            //   66: aload_0
            //   67: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   70: invokestatic 172	com/soomla/traceback/i/t:ॱˊ	()Lcom/soomla/traceback/i/t$e;
            //   73: invokevirtual 177	com/soomla/traceback/i/t$e:ˏ	()Landroid/os/Handler;
            //   76: invokestatic 180	com/soomla/traceback/i/t:ˏ	(Lcom/soomla/traceback/i/t;Landroid/os/Handler;)Landroid/os/Handler;
            //   79: pop
            //   80: aload_0
            //   81: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   84: invokestatic 168	com/soomla/traceback/i/t:ʻ	(Lcom/soomla/traceback/i/t;)Landroid/os/Handler;
            //   87: ifnonnull +8 -> 95
            //   90: aload_3
            //   91: monitorexit
            //   92: goto -59 -> 33
            //   95: aload_0
            //   96: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   99: invokestatic 168	com/soomla/traceback/i/t:ʻ	(Lcom/soomla/traceback/i/t;)Landroid/os/Handler;
            //   102: aconst_null
            //   103: invokevirtual 186	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
            //   106: aload_0
            //   107: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   110: invokestatic 168	com/soomla/traceback/i/t:ʻ	(Lcom/soomla/traceback/i/t;)Landroid/os/Handler;
            //   113: astore 4
            //   115: new 11	com/soomla/traceback/i/t$5$2
            //   118: dup
            //   119: aload_0
            //   120: invokespecial 189	com/soomla/traceback/i/t$5$2:<init>	(Lcom/soomla/traceback/i/t$5;)V
            //   123: astore 5
            //   125: aload_0
            //   126: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   129: invokestatic 193	com/soomla/traceback/i/t:ᐝ	(Lcom/soomla/traceback/i/t;)Z
            //   132: ifeq +99 -> 231
            //   135: aload_0
            //   136: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   139: invokestatic 196	com/soomla/traceback/i/t:ʽ	(Lcom/soomla/traceback/i/t;)I
            //   142: i2l
            //   143: lstore_1
            //   144: goto +3 -> 147
            //   147: aload 4
            //   149: aload 5
            //   151: lload_1
            //   152: invokevirtual 200	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
            //   155: pop
            //   156: aload_3
            //   157: monitorexit
            //   158: goto -125 -> 33
            //   161: astore 4
            //   163: aload_3
            //   164: monitorexit
            //   165: aload 4
            //   167: athrow
            //   168: ldc -121
            //   170: bipush -78
            //   172: iconst_0
            //   173: ldc -120
            //   175: bipush -70
            //   177: invokestatic 138	com/soomla/traceback/i/t$5:ॱ	(ISBII)Ljava/lang/String;
            //   180: invokevirtual 143	java/lang/String:intern	()Ljava/lang/String;
            //   183: ldc -112
            //   185: bipush 19
            //   187: iconst_0
            //   188: ldc -55
            //   190: bipush -23
            //   192: invokestatic 138	com/soomla/traceback/i/t$5:ॱ	(ISBII)Ljava/lang/String;
            //   195: invokevirtual 143	java/lang/String:intern	()Ljava/lang/String;
            //   198: invokestatic 204	com/soomla/traceback/LogUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
            //   201: aload_0
            //   202: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   205: astore_3
            //   206: aload_3
            //   207: monitorenter
            //   208: aload_0
            //   209: getfield 102	com/soomla/traceback/i/t$5:ॱ	Lcom/soomla/traceback/i/t;
            //   212: invokestatic 208	com/soomla/traceback/i/t:ˊॱ	(Lcom/soomla/traceback/i/t;)Ljava/lang/Thread;
            //   215: pop
            //   216: aload_3
            //   217: monitorexit
            //   218: return
            //   219: astore 4
            //   221: aload_3
            //   222: monitorexit
            //   223: aload 4
            //   225: athrow
            //   226: return
            //   227: astore_3
            //   228: goto -60 -> 168
            //   231: lconst_0
            //   232: lstore_1
            //   233: goto -86 -> 147
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	236	0	this	5
            //   143	90	1	l	long
            //   227	1	3	localInterruptedException	InterruptedException
            //   113	35	4	localHandler	Handler
            //   161	5	4	localObject1	Object
            //   219	5	4	localObject2	Object
            //   123	27	5	local2	2
            // Exception table:
            //   from	to	target	type
            //   49	92	161	finally
            //   95	144	161	finally
            //   147	158	161	finally
            //   208	218	219	finally
            //   42	49	227	java/lang/InterruptedException
            //   163	168	227	java/lang/InterruptedException
          }
        });
        this.ʾ.start();
      }
      i = ˌ + 13;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 1) {
        return;
      }
      i = null.length;
      return;
    }
    finally {}
  }
  
  private void ʼॱ()
  {
    for (;;)
    {
      try
      {
        i = ˋˋ + 5;
        ˌ = i % 128;
        if (i % 2 != 0)
        {
          i = 21;
          if (i != 26)
          {
            notify();
            i = 76 / 0;
          }
          else
          {
            notify();
          }
          i = ˋˋ + 105;
          ˌ = i % 128;
          return;
        }
      }
      finally {}
      int i = 26;
    }
  }
  
  private long ʽ(String paramString)
  {
    int i = ˋˋ + 21;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 64;
    } else {
      i = 76;
    }
    if (i != 76) {}
    for (;;)
    {
      try
      {
        paramString = this.ͺ.getPackageManager().getPackageInfo(paramString, 1);
        return paramString.lastUpdateTime;
      }
      catch (Throwable paramString)
      {
        continue;
      }
      paramString = this.ͺ.getPackageManager().getPackageInfo(paramString, 0);
    }
    return 0L;
  }
  
  private static void ʽॱ()
  {
    LogUtils.LogDebug(ˊ(44632, 0, 16).intern(), ˊ('懝', 3146, 30).intern());
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(ˊ('\000', 3176, 36).intern());
    ॱ().ͺ.registerReceiver(new az(), localIntentFilter);
    int i = ˋˋ + 87;
    ˌ = i % 128;
  }
  
  private boolean ʾ()
  {
    for (;;)
    {
      try
      {
        Object localObject2 = new JSONArray();
        Object localObject1 = this.ˏॱ.iterator();
        if (((Iterator)localObject1).hasNext())
        {
          i = 52;
          Object localObject3;
          if (i != 52)
          {
            localObject3 = x.ˎ(ˊ(new JSONObject(), true), false, false);
            ((JSONObject)localObject3).put(ˊ(55375, 3019, 6).intern(), localObject2);
            g localG = g.ˎ();
            if (this.ʼ) {
              break label508;
            }
            i = 47;
            if (i != 97)
            {
              localObject1 = this.ˏ.ॱॱ();
            }
            else
            {
              localObject1 = this.ˏ.ʼ();
              i = ˋˋ + 1;
              ˌ = i % 128;
              if (i % 2 != 0) {}
            }
            localObject1 = bb.ॱ((JSONObject)localObject3, localG.ˋ((String)localObject1));
            if (localObject1 != null)
            {
              i = ˌ + 45;
              ˋˋ = i % 128;
              if (i % 2 == 0)
              {
                if (((ay)localObject1).ˋ().ˏ() < 9751) {
                  continue;
                }
              }
              else
              {
                if (((ay)localObject1).ˋ().ˏ() < 200) {
                  break label514;
                }
                i = 33;
                break label517;
              }
              if (((ay)localObject1).ˋ().ˏ() <= 299)
              {
                localObject1 = ˊ(44632, 0, 16).intern();
                localObject3 = new StringBuilder();
                ((StringBuilder)localObject3).append(ˊ('\000', 3025, 38).intern());
                ((StringBuilder)localObject3).append(localObject2);
                LogUtils.LogDebug((String)localObject1, localObject3.toString());
                return true;
              }
              if (((ay)localObject1).ˋ().ˏ() != 403) {
                break label526;
              }
              i = 43;
              if (i != 24)
              {
                ˋ(true);
                return true;
              }
              localObject2 = ˊ(44632, 0, 16).intern();
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append(ˊ(46164, 3063, 51).intern());
              ((StringBuilder)localObject3).append(((ay)localObject1).ˋ().ˏ());
              ((StringBuilder)localObject3).append(ˊ('兦', 3114, 1).intern());
              ((StringBuilder)localObject3).append(((ay)localObject1).ˋ().ॱ());
              LogUtils.LogDebug((String)localObject2, localObject3.toString());
              return false;
            }
          }
          else
          {
            ((JSONArray)localObject2).put(((ax)((Iterator)localObject1).next()).ॱ());
            continue;
          }
          return false;
        }
      }
      catch (Exception localException)
      {
        localObject2 = ˊ(44632, 0, 16).intern();
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(ˊ('㭦', 3115, 31).intern());
        ((StringBuilder)localObject3).append(localException.toString());
        LogUtils.LogError((String)localObject2, localObject3.toString());
      }
      int i = 16;
      continue;
      label508:
      i = 97;
      continue;
      label514:
      i = 6;
      label517:
      if (i != 6)
      {
        continue;
        label526:
        i = 24;
      }
    }
  }
  
  private void ʿ()
  {
    LogUtils.LogDebug(ˊ(44632, 0, 16).intern(), ˊ('\000', 2994, 25).intern());
    Queue localQueue = this.ˈ.ॱ(this.ˏ.ॱ());
    if (localQueue != null) {
      try
      {
        if ((!localQueue.isEmpty()) && (this.ˏॱ == null))
        {
          this.ˏॱ = new ArrayList();
          for (;;)
          {
            ax localAx = (ax)localQueue.poll();
            if (localAx == null) {
              break;
            }
            this.ˏॱ.add(localAx);
          }
          try
          {
            if (this.ˏॱ.isEmpty())
            {
              this.ˏॱ = null;
              return;
            }
            new Thread(new SafeRunnable()
            {
              public final void safeRun()
              {
                boolean bool = t.ʻॱ(t.this);
                if (bool)
                {
                  ??? = t.ॱˋ(t.this).iterator();
                  while (((Iterator)???).hasNext())
                  {
                    ax localAx = (ax)((Iterator)???).next();
                    t.ᐝॱ(t.this).ˎ(localAx);
                  }
                }
                synchronized (t.this)
                {
                  t.ॱᐝ(t.this);
                  if (bool) {
                    t.ॱˎ(t.this);
                  }
                  return;
                }
              }
            }).start();
            return;
          }
          finally {}
        }
      }
      finally
      {
        break label163;
      }
    }
    return;
    label163:
    throw localObject2;
  }
  
  private List<ApplicationInfo> ˈ()
  {
    int i = ˋˋ + 31;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 94;
    } else {
      i = 39;
    }
    int j = 0;
    if (i != 39) {}
    try
    {
      localObject1 = this.ͺ.getPackageManager().getInstalledApplications(1);
      break label65;
      localObject1 = this.ͺ.getPackageManager().getInstalledApplications(0);
      label65:
      i = ˋˋ + 57;
      ˌ = i % 128;
      if (i % 2 != 0) {
        i = j;
      } else {
        i = 1;
      }
      if (i != 1) {
        i = null.length;
      }
      return localObject1;
    }
    catch (Exception localException)
    {
      Object localObject1;
      StringBuilder localStringBuilder;
      Object localObject2;
      for (;;) {}
    }
    localObject1 = ˊ(44632, 0, 16).intern();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(ˊ('\000', 2960, 34).intern());
    localStringBuilder.append(localObject2.getLocalizedMessage());
    LogUtils.LogError((String)localObject1, localStringBuilder.toString());
    return new ArrayList();
  }
  
  private static String ˊ(char paramChar, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    int i = ˋˋ + 83;
    ˌ = i % 128;
    i = 0;
    for (;;)
    {
      if (i < paramInt2) {
        j = 1;
      } else {
        j = 0;
      }
      if (j == 0) {
        break;
      }
      int j = ˋˋ + 17;
      ˌ = j % 128;
      if (j % 2 != 0) {
        j = 51;
      } else {
        j = 76;
      }
      if (j != 51)
      {
        arrayOfChar[i] = ((char)(int)(ˊᐝ[(paramInt1 + i)] ^ i * ˍ ^ paramChar));
        i += 1;
      }
      else
      {
        arrayOfChar[i] = ((char)(int)(ˊᐝ[(paramInt1 >>> i)] / (i & ˍ) / paramChar));
        i += 34;
      }
    }
    return new String(arrayOfChar);
  }
  
  private JSONObject ˊ(JSONObject paramJSONObject, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    if (!paramBoolean) {}
    try
    {
      localJSONObject.put(ˊ(34671, 2895, 3).intern(), g.ˎ().ᐝ());
      try
      {
        int i = this.ॱˋ;
        if (!paramBoolean) {
          this.ॱˋ += 1;
        }
        long l1 = x.ˎ();
        long l2 = paramJSONObject.optLong(LocalEventConsts.EVENT_KEY_TIMESTAMP);
        if (l2 != 0L)
        {
          localJSONObject.put(ˊ('\000', 2898, 3).intern(), l1);
          l1 = l2;
        }
        localJSONObject.put(ˊ(57041, 2901, 3).intern(), this.ॱᐝ);
        localJSONObject.put(LocalEventConsts.EVENT_KEY_TIMESTAMP, l1);
        localJSONObject.put(ˊ('\000', 2904, 2).intern(), x.ˏ());
        if (!paramBoolean) {
          localJSONObject.put(ˊ(64025, 2906, 2).intern(), i);
        }
        if (paramBoolean) {
          break label258;
        }
        localJSONObject.put(ˊ('\000', 2908, 9).intern(), af.ॱ().ˏ());
      }
      finally {}
    }
    catch (JSONException localJSONException)
    {
      label258:
      for (;;) {}
    }
    LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ(38569, 2917, 24).intern(), localThrowable);
    paramJSONObject.remove(LocalEventConsts.EVENT_KEY_TIMESTAMP);
    return localJSONObject;
  }
  
  private void ˋ(Context paramContext)
  {
    paramContext.registerReceiver(new BroadcastReceiver()new IntentFilter
    {
      public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        x.ˊ(paramAnonymousIntent);
      }
    }, new IntentFilter(ˊ(62749, 1263, 37).intern()));
    int i = ˋˋ + 65;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 20;
    } else {
      i = 34;
    }
    if (i != 20) {
      return;
    }
    i = 83 / 0;
  }
  
  private void ˋ(final Context paramContext, String paramString, boolean paramBoolean1, final boolean paramBoolean2, final boolean paramBoolean3)
  {
    String str1 = ˊ(44632, 0, 16).intern();
    final String str2 = g.ˎ().ᐝ();
    g.ˎ().ᐝ(paramString);
    int i;
    if (paramString == null) {
      i = 77;
    } else {
      i = 16;
    }
    if (i != 16)
    {
      i = ˌ + 97;
      ˋˋ = i % 128;
      LogUtils.ExtLogError(str1, ˊ(46997, 656, 93).intern());
    }
    else
    {
      StringBuilder localStringBuilder;
      if (paramString.equals(ˊ(37498, 749, 17).intern()))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(ˊ('\000', 766, 40).intern());
        localStringBuilder.append(paramString);
        localStringBuilder.append(ˊ('\000', 806, 77).intern());
        LogUtils.ExtLogError(str1, localStringBuilder.toString());
      }
      else if (!paramString.matches(ˊ('\000', 883, 15).intern()))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(ˊ('\000', 898, 15).intern());
        localStringBuilder.append(paramString);
        localStringBuilder.append(ˊ('\000', 913, 126).intern());
        LogUtils.ExtLogError(str1, localStringBuilder.toString());
        i = ˌ + 111;
        ˋˋ = i % 128;
        if (i % 2 == 0) {}
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(ˊ('糯', 1039, 24).intern());
        localStringBuilder.append(paramString);
        LogUtils.ExtLogInfo(str1, localStringBuilder.toString());
        LogUtils.ExtLogInfo(str1, ˊ('\000', 1063, 100).intern());
      }
    }
    if (paramBoolean1)
    {
      ॱᐝ();
      new Handler(Looper.getMainLooper()).post(new SafeRunnable()
      {
        private static int ʻ = 1889867648;
        private static int ʼ = -1565295681;
        private static byte[] ʽ = { -63, -89, -9, -95, -88, 11, -6, -66, -1, -108, 4, -57, 117, -124, -112, 116, -121, -113, 115, 113, -91, -121, -110, -97 };
        private static int ͺ = 1;
        private static int ॱॱ = 0;
        private static int ᐝ = 70;
        
        private static String ˊ(int paramAnonymousInt1, short paramAnonymousShort, byte paramAnonymousByte, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          int k = paramAnonymousInt3 + ᐝ;
          if (k == -1) {
            paramAnonymousInt3 = 1;
          } else {
            paramAnonymousInt3 = 0;
          }
          if (paramAnonymousInt3 != 1)
          {
            paramAnonymousInt3 = ͺ + 29;
            ॱॱ = paramAnonymousInt3 % 128;
          }
          else
          {
            paramAnonymousInt3 = ͺ + 109;
            ॱॱ = paramAnonymousInt3 % 128;
            if (paramAnonymousInt3 % 2 == 0) {
              break label88;
            }
          }
          int j = 0;
          break label91;
          label88:
          j = 1;
          label91:
          paramAnonymousInt3 = k;
          if (j != 0)
          {
            paramAnonymousInt3 = ॱॱ + 35;
            ͺ = paramAnonymousInt3 % 128;
            if (ʽ != null)
            {
              paramAnonymousInt3 = (byte)(ʽ[(ʻ + paramAnonymousInt2)] + ᐝ);
            }
            else
            {
              paramAnonymousInt1 = ʻ;
              throw new NullPointerException();
            }
          }
          if (paramAnonymousInt3 > 0) {
            k = 1;
          } else {
            k = 0;
          }
          if (k == 1)
          {
            k = ͺ + 123;
            ॱॱ = k % 128;
            k = ʻ;
            if (j != 0)
            {
              j = ͺ + 87;
              ॱॱ = j % 128;
              j = 1;
            }
            else
            {
              j = 0;
            }
            paramAnonymousInt2 = paramAnonymousInt2 + paramAnonymousInt3 - 2 + k + j;
            int i = (char)(paramAnonymousInt1 + ʼ);
            localStringBuilder.append(i);
            k = i;
            j = 1;
            paramAnonymousInt1 = paramAnonymousInt2;
            paramAnonymousInt2 = j;
            while (paramAnonymousInt2 < paramAnonymousInt3)
            {
              if (ʽ != null) {
                j = 1;
              } else {
                j = 0;
              }
              if (j != 0)
              {
                i = (char)(k + ((byte)(ʽ[paramAnonymousInt1] + paramAnonymousShort) ^ paramAnonymousByte));
                paramAnonymousInt1 -= 1;
              }
              else
              {
                throw new NullPointerException();
              }
              localStringBuilder.append(i);
              paramAnonymousInt2 += 1;
              k = i;
            }
          }
          return localStringBuilder.toString();
        }
        
        public final void safeRun()
        {
          if (!t.ˎ(t.this))
          {
            t.ˏ(t.this, paramContext);
            t.ˏ(t.this);
          }
          t localT1 = t.this;
          String str2 = ˊ(1565295797, (short)-77, (byte)81, -1889867648, -71).intern();
          t localT2 = t.this;
          if (paramBoolean2) {
            i = 1;
          } else {
            i = 0;
          }
          String str1;
          if (i != 0)
          {
            if (str2 != null) {
              i = 74;
            } else {
              i = 72;
            }
            if (i != 74)
            {
              str1 = ˊ(1565295791, (short)-70, (byte)-71, -1889867641, -71).intern();
            }
            else
            {
              i = ॱॱ + 89;
              ͺ = i % 128;
              str1 = str2;
            }
          }
          else
          {
            str1 = null;
          }
          localT1.ˊ(str2, t.ˏ(localT2, str1));
          if (paramBoolean3)
          {
            t.this.ˊ(ˊ(1565295797, (short)-11, (byte)104, -1889867637, -71).intern(), new JSONObject());
            i = ͺ + 101;
            ॱॱ = i % 128;
          }
          int i = ॱॱ + 83;
          ͺ = i % 128;
        }
      });
    }
  }
  
  /* Error */
  private void ˋ(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 513	com/soomla/traceback/i/t:ˏ	()Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifeq +9 -> 17
    //   11: bipush 26
    //   13: istore_2
    //   14: goto +6 -> 20
    //   17: bipush 70
    //   19: istore_2
    //   20: iload_2
    //   21: bipush 70
    //   23: if_icmpeq +93 -> 116
    //   26: getstatic 149	com/soomla/traceback/i/t:ˌ	I
    //   29: bipush 87
    //   31: iadd
    //   32: istore_2
    //   33: iload_2
    //   34: sipush 128
    //   37: irem
    //   38: putstatic 151	com/soomla/traceback/i/t:ˋˋ	I
    //   41: iload_2
    //   42: iconst_2
    //   43: irem
    //   44: ifne +8 -> 52
    //   47: iconst_0
    //   48: istore_2
    //   49: goto +5 -> 54
    //   52: iconst_1
    //   53: istore_2
    //   54: iload_2
    //   55: ifeq +32 -> 87
    //   58: ldc_w 256
    //   61: iconst_0
    //   62: bipush 16
    //   64: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   67: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   70: iconst_0
    //   71: bipush 16
    //   73: bipush 37
    //   75: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   78: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   81: invokestatic 480	com/soomla/traceback/LogUtils:ExtLogError	(Ljava/lang/String;Ljava/lang/String;)V
    //   84: aload_0
    //   85: monitorexit
    //   86: return
    //   87: ldc_w 256
    //   90: iconst_0
    //   91: bipush 116
    //   93: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   96: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   99: iconst_0
    //   100: bipush 97
    //   102: bipush 117
    //   104: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   107: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   110: invokestatic 480	com/soomla/traceback/LogUtils:ExtLogError	(Ljava/lang/String;Ljava/lang/String;)V
    //   113: aload_0
    //   114: monitorexit
    //   115: return
    //   116: aload_0
    //   117: getfield 160	com/soomla/traceback/i/t:ˊ	Z
    //   120: istore_3
    //   121: iload_3
    //   122: ifne +47 -> 169
    //   125: getstatic 151	com/soomla/traceback/i/t:ˋˋ	I
    //   128: bipush 29
    //   130: iadd
    //   131: istore_2
    //   132: iload_2
    //   133: sipush 128
    //   136: irem
    //   137: putstatic 149	com/soomla/traceback/i/t:ˌ	I
    //   140: ldc_w 256
    //   143: iconst_0
    //   144: bipush 16
    //   146: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   149: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   152: iconst_0
    //   153: bipush 53
    //   155: bipush 60
    //   157: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   160: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   163: invokestatic 480	com/soomla/traceback/LogUtils:ExtLogError	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload_0
    //   167: monitorexit
    //   168: return
    //   169: iload_1
    //   170: ifne +29 -> 199
    //   173: aload_0
    //   174: sipush 14591
    //   177: bipush 113
    //   179: bipush 11
    //   181: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   184: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   187: new 299	org/json/JSONObject
    //   190: dup
    //   191: invokespecial 300	org/json/JSONObject:<init>	()V
    //   194: aconst_null
    //   195: iconst_1
    //   196: invokespecial 516	com/soomla/traceback/i/t:ˎ	(Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;Z)V
    //   199: aload_0
    //   200: iconst_1
    //   201: putfield 164	com/soomla/traceback/i/t:ʻ	Z
    //   204: aload_0
    //   205: getfield 238	com/soomla/traceback/i/t:ͺ	Landroid/content/Context;
    //   208: invokestatic 521	com/soomla/traceback/i/ac:ˋ	(Landroid/content/Context;)Lcom/soomla/traceback/i/ac;
    //   211: invokevirtual 523	com/soomla/traceback/i/ac:ॱ	()V
    //   214: aload_0
    //   215: getfield 173	com/soomla/traceback/i/t:ॱˊ	Ljava/util/List;
    //   218: invokeinterface 292 1 0
    //   223: astore 4
    //   225: aload 4
    //   227: invokeinterface 297 1 0
    //   232: ifeq +21 -> 253
    //   235: aload 4
    //   237: invokeinterface 364 1 0
    //   242: checkcast 525	com/soomla/traceback/TracebackListener
    //   245: invokeinterface 528 1 0
    //   250: goto -25 -> 225
    //   253: aload_0
    //   254: getfield 173	com/soomla/traceback/i/t:ॱˊ	Ljava/util/List;
    //   257: invokeinterface 531 1 0
    //   262: aload_0
    //   263: aconst_null
    //   264: putfield 173	com/soomla/traceback/i/t:ॱˊ	Ljava/util/List;
    //   267: aload_0
    //   268: getfield 533	com/soomla/traceback/i/t:ॱॱ	Lcom/soomla/traceback/i/l;
    //   271: invokevirtual 537	com/soomla/traceback/i/l:ˎ	()V
    //   274: invokestatic 541	com/soomla/traceback/i/ai:ˊ	()V
    //   277: aload_0
    //   278: getfield 182	com/soomla/traceback/i/t:ʽॱ	Lcom/soomla/traceback/i/aq;
    //   281: invokevirtual 544	com/soomla/traceback/i/aq:ˎ	()V
    //   284: aload_0
    //   285: aconst_null
    //   286: putfield 182	com/soomla/traceback/i/t:ʽॱ	Lcom/soomla/traceback/i/aq;
    //   289: aload_0
    //   290: monitorexit
    //   291: return
    //   292: astore 4
    //   294: goto +39 -> 333
    //   297: astore 4
    //   299: ldc_w 256
    //   302: iconst_0
    //   303: bipush 16
    //   305: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   308: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   311: ldc_w 545
    //   314: bipush 124
    //   316: bipush 19
    //   318: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   321: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   324: aload 4
    //   326: iconst_1
    //   327: invokestatic 550	com/soomla/traceback/i/o:ˏ	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   330: aload_0
    //   331: monitorexit
    //   332: return
    //   333: aload_0
    //   334: monitorexit
    //   335: aload 4
    //   337: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	338	0	this	t
    //   0	338	1	paramBoolean	boolean
    //   13	124	2	i	int
    //   6	116	3	bool	boolean
    //   223	13	4	localIterator	Iterator
    //   292	1	4	localObject	Object
    //   297	39	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	7	292	finally
    //   26	41	292	finally
    //   58	84	292	finally
    //   87	113	292	finally
    //   116	121	292	finally
    //   125	140	292	finally
    //   140	166	292	finally
    //   173	199	292	finally
    //   199	225	292	finally
    //   225	250	292	finally
    //   253	289	292	finally
    //   299	330	292	finally
    //   2	7	297	java/lang/Exception
    //   58	84	297	java/lang/Exception
    //   87	113	297	java/lang/Exception
    //   116	121	297	java/lang/Exception
    //   140	166	297	java/lang/Exception
    //   173	199	297	java/lang/Exception
    //   199	225	297	java/lang/Exception
    //   225	250	297	java/lang/Exception
    //   253	289	297	java/lang/Exception
  }
  
  private ApplicationInfo ˎ(String paramString)
  {
    int i = ˌ + 65;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 1) {}
    try
    {
      return this.ͺ.getPackageManager().getApplicationInfo(paramString, 1);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      StringBuilder localStringBuilder;
      Object localObject;
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    paramString = this.ͺ.getPackageManager().getApplicationInfo(paramString, 0);
    return paramString;
    paramString = ˊ(44632, 0, 16).intern();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(ˊ('\000', 2960, 34).intern());
    localStringBuilder.append(localObject.getLocalizedMessage());
    LogUtils.LogError(paramString, localStringBuilder.toString());
    i = ˌ + 123;
    ˋˋ = i % 128;
    return null;
  }
  
  private static List<AdAction> ˎ(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    paramJSONObject = paramJSONObject.getJSONArray(ˊ('\000', 2227, 7).intern());
    int i = ˌ + 37;
    ˋˋ = i % 128;
    while (j < paramJSONObject.length())
    {
      localArrayList.add(new ah(paramJSONObject.getJSONObject(j)));
      j += 1;
      i = ˋˋ + 43;
      ˌ = i % 128;
    }
    return localArrayList;
  }
  
  private void ˎ(final String paramString, final JSONObject paramJSONObject1, final JSONObject paramJSONObject2, boolean paramBoolean)
  {
    if (ˏ()) {
      return;
    }
    int i;
    if (!this.ʼ)
    {
      i = ˌ + 21;
      ˋˋ = i % 128;
      if (ˊ(49597, 190, 8).intern().equals(paramString)) {
        i = 90;
      } else {
        i = 60;
      }
      if (i != 60)
      {
        i = ˌ + 19;
        ˋˋ = i % 128;
        double d3 = Math.random();
        double d2 = 100.0D;
        double d1 = d2;
        if (!this.ʼ)
        {
          if (this.ˏ != null) {
            i = 69;
          } else {
            i = 29;
          }
          d1 = d2;
          if (i != 29)
          {
            i = ˋˋ + 7;
            ˌ = i % 128;
            if (i % 2 != 0)
            {
              d1 = this.ˏ.ˋ();
              i = 8 / 0;
            }
            else
            {
              d1 = this.ˏ.ˋ();
            }
          }
        }
        if (d3 * 100.0D >= d1)
        {
          paramJSONObject1 = ˊ(44632, 0, 16).intern();
          paramJSONObject2 = new StringBuilder();
          paramJSONObject2.append(ˊ('☐', 2854, 11).intern());
          paramJSONObject2.append(paramString);
          paramJSONObject2.append(ˊ('ᙏ', 2865, 30).intern());
          LogUtils.LogDebug(paramJSONObject1, paramJSONObject2.toString());
          i = ˋˋ + 77;
          ˌ = i % 128;
          if (i % 2 != 0) {
            i = 13 / 0;
          }
          return;
        }
      }
    }
    String str = ˊ(44632, 0, 16).intern();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(ˊ('ὰ', 2814, 22).intern());
    localStringBuilder.append(paramString);
    localStringBuilder.append(ˊ('\000', 2836, 18).intern());
    localStringBuilder.append(paramJSONObject1.toString());
    LogUtils.LogDebug(str, localStringBuilder.toString());
    paramString = new SafeRunnable()
    {
      private static char[] ʻ = { 83, 26398, -12563, 13758, -25336, 1236, 27590, -11421, 15098, -24136, 2377, 28702, -10322, 16252, -23027, 3540, 78, 26430, -12586, 13779, -25289, 1264, 27528, -11437, 15073, -24137, 2381, 28763, -10327, 16235, -23029, 3537, 30052, -9151, 17349, -21654, 4640, 31181, -7978, 18473, -20487, 5764, 32351, -6735, 19708, 32, 26489, -12601, 13707, -25305, 1273, 27539, -11437, 15085, -24131, 2314, 28697, -10315, 16189, -23011, 3546, 30050, -9193, 17367, -21647, 4733, 11455, 19400, -7632, 6513, -20031, 10265, 18221, -88, 5644, -29422, 9650, 23797, -1207, 5010, -30043, 8497, 22925, -3857, 28471, -30820, 16031, 21817, -13196, 25816, -31973, 14882, 21183, -14079, 24666, -26461, 14255, -20988, 40, 26402, -12601, 13725, -25312, 1232, 27536, -11438, 15078, -24147, 2307, 28763, -10353, 16242, -23013, 3539, 30068, -9201, 17301, -21641, 4724, 31175, -8061, 18478, -20492, 5773, 32282, -6674, 19626, -19384, 6928, -32069, -5632, 20757, -18261, 8304, -30960, -4388, 22121, -17095, 9417, -29813, -3277, 23221, -15924, 10606, -28422, -2128, 24384, -14607, 11707, -27315, -1005, 25573, -13467, 12802, -26154, 365, 26747, 29674, 5311, -17053, 17950, -4417, 30584, 6155, -24330, 18789, -11725, 31445, 919, -23514, 9828, 16689, -5907, 5008, -17615, 8950, 19845, -2696, 7421, -30809, 12126, 97, 26401, -12590, 13696, 27881, 2959, -23940, 22832, -3686, 26649, 1835, -16385, 22080, -13028, 26088, 7344, -17600, 21464, -13652, 24928, 6600, -20308, 12146, -14397, 32413, 5485, -29574, 9371, -15531, 31270, 4861, -30394, 8215, -10012, 30706, -4608, -31005, 15784, -11264, 19659, -5134, -32199, 116, 26401, -12547, 13722, -25302, 1276, 27538, 105, 26431, -12586, 13789, -25289, 1264, 27541, -11452, 15041, -24131 };
      private static long ʼ = 4401689123959564113L;
      private static int ʽ = 0;
      private static int ᐝ = 1;
      
      private static String ॱ(char paramAnonymousChar, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        int i = ʽ + 107;
        ᐝ = i % 128;
        char[] arrayOfChar = new char[paramAnonymousInt2];
        i = 0;
        for (;;)
        {
          if (i < paramAnonymousInt2) {
            j = 0;
          } else {
            j = 87;
          }
          if (j == 87) {
            break;
          }
          int j = ᐝ + 41;
          ʽ = j % 128;
          if (j % 2 != 0) {
            j = 1;
          } else {
            j = 0;
          }
          if (j != 1)
          {
            arrayOfChar[i] = ((char)(int)(ʻ[(paramAnonymousInt1 + i)] ^ i * ʼ ^ paramAnonymousChar));
            i += 1;
          }
          else
          {
            arrayOfChar[i] = ((char)(int)(ʻ[(paramAnonymousInt1 << i)] - (i | ʼ) & paramAnonymousChar));
            i += 34;
          }
        }
        return new String(arrayOfChar);
      }
      
      public final void safeRun()
      {
        if (t.this.ˏॱ().ᐝॱ().contains(paramString))
        {
          localObject1 = ॱ('\000', 0, 16).intern();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(ॱ('\000', 16, 29).intern());
          ((StringBuilder)localObject2).append(paramString);
          ((StringBuilder)localObject2).append(ॱ('\000', 45, 21).intern());
          LogUtils.LogDebug((String)localObject1, localObject2.toString());
          return;
        }
        Object localObject1 = ॱ('\000', 0, 16).intern();
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(ॱ('Ⳬ', 66, 32).intern());
        ((StringBuilder)localObject2).append(paramString);
        LogUtils.LogDebug((String)localObject1, localObject2.toString());
        localObject1 = t.ˋ(paramString, this.ॱ, paramJSONObject1, paramJSONObject2);
        if (localObject1 == null)
        {
          LogUtils.LogError(ॱ('\000', 0, 16).intern(), ॱ('\000', 98, 59).intern());
          return;
        }
        int i;
        if (!paramString.equals(ॱ('玞', 157, 13).intern())) {
          i = 70;
        } else {
          i = 46;
        }
        String str;
        StringBuilder localStringBuilder;
        if (i != 46)
        {
          i = ᐝ + 49;
          ʽ = i % 128;
          if (paramString.equals(ॱ('☐', 170, 11).intern())) {
            i = 5;
          } else {
            i = 84;
          }
          if (i == 84)
          {
            if (!paramString.equals(ॱ('\000', 223, 7).intern())) {
              break label489;
            }
            localObject2 = t.this.ʻ();
            str = ॱ('\000', 230, 10).intern();
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(t.ͺ(t.this));
            ((av)localObject2).ˏ(str, localStringBuilder.toString());
            i = ʽ + 107;
            ᐝ = i % 128;
            if (i % 2 == 0) {
              break label489;
            }
            break label489;
          }
        }
        try
        {
          ((ax)localObject1).ॱ().put(ॱ('\000', 181, 4).intern(), t.ॱˊ(t.this).size());
        }
        catch (Exception localException)
        {
          str = ॱ('\000', 0, 16).intern();
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(ॱ('沬', 185, 38).intern());
          localStringBuilder.append(localException.getLocalizedMessage());
          LogUtils.LogError(str, localStringBuilder.toString());
        }
        label489:
        t.ˋ(t.this).add(localObject1);
        t.ˋॱ(t.this);
      }
    };
    if (!paramBoolean) {
      i = 86;
    } else {
      i = 35;
    }
    if (i == 86)
    {
      i = ˌ + 11;
      ˋˋ = i % 128;
      if (x.ˊ()) {}
    }
    else
    {
      new Thread(paramString).start();
      return;
    }
    paramString.run();
  }
  
  private static JSONObject ˏ(List<String> paramList)
  {
    JSONObject localJSONObject = new JSONObject();
    int i;
    if (paramList != null) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 1)
    {
      i = ˋˋ + 75;
      ˌ = i % 128;
      if (paramList.size() == 0) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 1) {
        return localJSONObject;
      }
      Object localObject = new JSONArray();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        i = ˌ + 79;
        ˋˋ = i % 128;
        if (i % 2 == 0) {
          i = 90;
        } else {
          i = 47;
        }
        if (i != 90)
        {
          ((JSONArray)localObject).put((String)paramList.next());
        }
        else
        {
          ((JSONArray)localObject).put((String)paramList.next());
          i = 56 / 0;
        }
      }
      try
      {
        localJSONObject.put(ˊ('ࡽ', 2791, 4).intern(), localObject);
        return localJSONObject;
      }
      catch (JSONException paramList)
      {
        localObject = ˊ(44632, 0, 16).intern();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(ˊ(51874, 2795, 19).intern());
        localStringBuilder.append(paramList.getLocalizedMessage());
        LogUtils.ExtLogError((String)localObject, localStringBuilder.toString());
        return localJSONObject;
      }
    }
    return localJSONObject;
  }
  
  /* Error */
  private void ˏ(final Application paramApplication, final Activity paramActivity, final String paramString1, String paramString2, boolean paramBoolean1, final boolean paramBoolean2)
  {
    // Byte code:
    //   0: getstatic 149	com/soomla/traceback/i/t:ˌ	I
    //   3: bipush 9
    //   5: iadd
    //   6: istore 7
    //   8: iload 7
    //   10: sipush 128
    //   13: irem
    //   14: putstatic 151	com/soomla/traceback/i/t:ˋˋ	I
    //   17: iload 7
    //   19: iconst_2
    //   20: irem
    //   21: ifne +10 -> 31
    //   24: bipush 68
    //   26: istore 7
    //   28: goto +7 -> 35
    //   31: bipush 67
    //   33: istore 7
    //   35: iconst_0
    //   36: istore 8
    //   38: iload 7
    //   40: bipush 67
    //   42: if_icmpeq +52 -> 94
    //   45: ldc_w 256
    //   48: iconst_0
    //   49: bipush 100
    //   51: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   54: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   57: astore 10
    //   59: aload_0
    //   60: invokevirtual 513	com/soomla/traceback/i/t:ˏ	()Z
    //   63: ifeq +10 -> 73
    //   66: bipush 45
    //   68: istore 7
    //   70: goto +7 -> 77
    //   73: bipush 46
    //   75: istore 7
    //   77: aload 10
    //   79: astore 9
    //   81: iload 7
    //   83: bipush 46
    //   85: if_icmpeq +6 -> 91
    //   88: goto +49 -> 137
    //   91: goto +96 -> 187
    //   94: ldc_w 256
    //   97: iconst_0
    //   98: bipush 16
    //   100: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   103: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   106: astore 10
    //   108: aload_0
    //   109: invokevirtual 513	com/soomla/traceback/i/t:ˏ	()Z
    //   112: ifeq +10 -> 122
    //   115: bipush 12
    //   117: istore 7
    //   119: goto +7 -> 126
    //   122: bipush 47
    //   124: istore 7
    //   126: aload 10
    //   128: astore 9
    //   130: iload 7
    //   132: bipush 47
    //   134: if_icmpeq -43 -> 91
    //   137: aload 10
    //   139: iconst_0
    //   140: sipush 198
    //   143: bipush 48
    //   145: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   148: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   151: invokestatic 480	com/soomla/traceback/LogUtils:ExtLogError	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: getstatic 151	com/soomla/traceback/i/t:ˋˋ	I
    //   157: bipush 29
    //   159: iadd
    //   160: istore 7
    //   162: iload 7
    //   164: sipush 128
    //   167: irem
    //   168: putstatic 149	com/soomla/traceback/i/t:ˌ	I
    //   171: iload 7
    //   173: iconst_2
    //   174: irem
    //   175: ifeq +11 -> 186
    //   178: new 153	java/lang/NullPointerException
    //   181: dup
    //   182: invokespecial 156	java/lang/NullPointerException:<init>	()V
    //   185: athrow
    //   186: return
    //   187: aload_0
    //   188: getfield 160	com/soomla/traceback/i/t:ˊ	Z
    //   191: ifeq +21 -> 212
    //   194: aload 9
    //   196: iconst_0
    //   197: sipush 246
    //   200: bipush 39
    //   202: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   205: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   208: invokestatic 262	com/soomla/traceback/LogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: return
    //   212: iload 5
    //   214: ifeq +25 -> 239
    //   217: aload_0
    //   218: aload_1
    //   219: invokevirtual 613	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   222: new 34	com/soomla/traceback/i/t$9
    //   225: dup
    //   226: aload_0
    //   227: aload_1
    //   228: aload_2
    //   229: aload_3
    //   230: iload 6
    //   232: invokespecial 616	com/soomla/traceback/i/t$9:<init>	(Lcom/soomla/traceback/i/t;Landroid/app/Application;Landroid/app/Activity;Ljava/lang/String;Z)V
    //   235: invokevirtual 620	com/soomla/traceback/SoomlaTraceback:getGAIDWithFallbackAsync	(Landroid/content/Context;Lcom/soomla/traceback/UidReadyCallback;)V
    //   238: return
    //   239: aload 9
    //   241: iconst_0
    //   242: sipush 285
    //   245: bipush 31
    //   247: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   250: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   253: invokestatic 262	com/soomla/traceback/LogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   256: aload_0
    //   257: iload 6
    //   259: putfield 166	com/soomla/traceback/i/t:ʼ	Z
    //   262: iload 6
    //   264: ifeq +505 -> 769
    //   267: bipush 76
    //   269: istore 7
    //   271: goto +3 -> 274
    //   274: iload 7
    //   276: bipush 65
    //   278: if_icmpeq +20 -> 298
    //   281: aload 9
    //   283: iconst_0
    //   284: sipush 316
    //   287: bipush 94
    //   289: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   292: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   295: invokestatic 480	com/soomla/traceback/LogUtils:ExtLogError	(Ljava/lang/String;Ljava/lang/String;)V
    //   298: ldc_w 621
    //   301: sipush 410
    //   304: bipush 20
    //   306: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   309: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   312: invokestatic 627	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   315: pop
    //   316: invokestatic 318	com/soomla/traceback/i/g:ˎ	()Lcom/soomla/traceback/i/g;
    //   319: aload_3
    //   320: invokevirtual 629	com/soomla/traceback/i/g:ˎ	(Ljava/lang/String;)V
    //   323: aload_1
    //   324: invokevirtual 613	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   327: astore 10
    //   329: aload_0
    //   330: aload 10
    //   332: putfield 238	com/soomla/traceback/i/t:ͺ	Landroid/content/Context;
    //   335: invokestatic 632	com/soomla/traceback/i/t:ॱˊ	()Lcom/soomla/traceback/i/t$e;
    //   338: pop
    //   339: aload_0
    //   340: new 634	com/soomla/traceback/i/av
    //   343: dup
    //   344: invokestatic 318	com/soomla/traceback/i/g:ˎ	()Lcom/soomla/traceback/i/g;
    //   347: invokevirtual 635	com/soomla/traceback/i/g:ʼ	()Ljava/lang/String;
    //   350: new 108	java/lang/String
    //   353: dup
    //   354: bipush 12
    //   356: newarray char
    //   358: dup
    //   359: iconst_0
    //   360: ldc_w 636
    //   363: castore
    //   364: dup
    //   365: iconst_1
    //   366: ldc_w 637
    //   369: castore
    //   370: dup
    //   371: iconst_2
    //   372: ldc_w 638
    //   375: castore
    //   376: dup
    //   377: iconst_3
    //   378: ldc_w 639
    //   381: castore
    //   382: dup
    //   383: iconst_4
    //   384: ldc_w 640
    //   387: castore
    //   388: dup
    //   389: iconst_5
    //   390: ldc_w 641
    //   393: castore
    //   394: dup
    //   395: bipush 6
    //   397: ldc_w 642
    //   400: castore
    //   401: dup
    //   402: bipush 7
    //   404: ldc_w 640
    //   407: castore
    //   408: dup
    //   409: bipush 8
    //   411: ldc_w 643
    //   414: castore
    //   415: dup
    //   416: bipush 9
    //   418: ldc_w 644
    //   421: castore
    //   422: dup
    //   423: bipush 10
    //   425: ldc_w 638
    //   428: castore
    //   429: dup
    //   430: bipush 11
    //   432: ldc_w 645
    //   435: castore
    //   436: invokespecial 411	java/lang/String:<init>	([C)V
    //   439: invokespecial 647	com/soomla/traceback/i/av:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   442: putfield 649	com/soomla/traceback/i/t:ˊˋ	Lcom/soomla/traceback/i/av;
    //   445: aload_0
    //   446: new 379	com/soomla/traceback/i/aw
    //   449: dup
    //   450: ldc_w 650
    //   453: sipush 430
    //   456: bipush 8
    //   458: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   461: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   464: iconst_0
    //   465: sipush 438
    //   468: bipush 6
    //   470: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   473: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   476: aload_0
    //   477: getfield 649	com/soomla/traceback/i/t:ˊˋ	Lcom/soomla/traceback/i/av;
    //   480: invokespecial 653	com/soomla/traceback/i/aw:<init>	(Ljava/lang/String;Ljava/lang/String;Lcom/soomla/traceback/i/av;)V
    //   483: putfield 186	com/soomla/traceback/i/t:ˈ	Lcom/soomla/traceback/i/aw;
    //   486: aload_0
    //   487: new 655	com/soomla/traceback/i/at
    //   490: dup
    //   491: invokespecial 656	com/soomla/traceback/i/at:<init>	()V
    //   494: putfield 184	com/soomla/traceback/i/t:ʼॱ	Lcom/soomla/traceback/i/at;
    //   497: aload_0
    //   498: new 543	com/soomla/traceback/i/aq
    //   501: dup
    //   502: aload 10
    //   504: invokespecial 658	com/soomla/traceback/i/aq:<init>	(Landroid/content/Context;)V
    //   507: putfield 182	com/soomla/traceback/i/t:ʽॱ	Lcom/soomla/traceback/i/aq;
    //   510: aload_0
    //   511: new 230	com/soomla/traceback/i/r
    //   514: dup
    //   515: invokespecial 659	com/soomla/traceback/i/r:<init>	()V
    //   518: putfield 228	com/soomla/traceback/i/t:ˏ	Lcom/soomla/traceback/i/r;
    //   521: aload_0
    //   522: getfield 228	com/soomla/traceback/i/t:ˏ	Lcom/soomla/traceback/i/r;
    //   525: invokevirtual 660	com/soomla/traceback/i/r:ˊ	()V
    //   528: new 340	java/lang/StringBuilder
    //   531: dup
    //   532: invokespecial 341	java/lang/StringBuilder:<init>	()V
    //   535: astore_3
    //   536: aload_3
    //   537: sipush 15225
    //   540: sipush 444
    //   543: bipush 34
    //   545: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   548: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   551: invokevirtual 345	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: pop
    //   555: aload_3
    //   556: invokestatic 318	com/soomla/traceback/i/g:ˎ	()Lcom/soomla/traceback/i/g;
    //   559: invokevirtual 662	com/soomla/traceback/i/g:ˊ	()Ljava/lang/String;
    //   562: invokevirtual 345	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload 9
    //   568: aload_3
    //   569: invokevirtual 351	java/lang/Object:toString	()Ljava/lang/String;
    //   572: invokestatic 262	com/soomla/traceback/LogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   575: aload_2
    //   576: ifnull +9 -> 585
    //   579: iconst_1
    //   580: istore 5
    //   582: goto +23 -> 605
    //   585: getstatic 149	com/soomla/traceback/i/t:ˌ	I
    //   588: bipush 37
    //   590: iadd
    //   591: istore 7
    //   593: iload 7
    //   595: sipush 128
    //   598: irem
    //   599: putstatic 151	com/soomla/traceback/i/t:ˋˋ	I
    //   602: iconst_0
    //   603: istore 5
    //   605: aload_0
    //   606: aload_2
    //   607: aload 4
    //   609: iconst_1
    //   610: iconst_0
    //   611: iload 5
    //   613: invokespecial 664	com/soomla/traceback/i/t:ˋ	(Landroid/content/Context;Ljava/lang/String;ZZZ)V
    //   616: aload_2
    //   617: ifnonnull +159 -> 776
    //   620: iconst_1
    //   621: istore 7
    //   623: goto +156 -> 779
    //   626: aload_0
    //   627: iload 5
    //   629: putfield 188	com/soomla/traceback/i/t:ˊˊ	Z
    //   632: invokestatic 669	com/soomla/traceback/i/ab:ˎ	()Lcom/soomla/traceback/i/ab;
    //   635: aload 10
    //   637: invokevirtual 671	com/soomla/traceback/i/ab:ˎ	(Landroid/content/Context;)V
    //   640: aload_0
    //   641: aload_1
    //   642: aload_2
    //   643: invokestatic 674	com/soomla/traceback/i/l:ˊ	(Landroid/app/Application;Landroid/app/Activity;)Lcom/soomla/traceback/i/l;
    //   646: putfield 533	com/soomla/traceback/i/t:ॱॱ	Lcom/soomla/traceback/i/l;
    //   649: aload_0
    //   650: invokestatic 678	java/lang/Thread:getDefaultUncaughtExceptionHandler	()Ljava/lang/Thread$UncaughtExceptionHandler;
    //   653: putfield 680	com/soomla/traceback/i/t:ᐝ	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   656: new 30	com/soomla/traceback/i/t$7
    //   659: dup
    //   660: aload_0
    //   661: invokespecial 681	com/soomla/traceback/i/t$7:<init>	(Lcom/soomla/traceback/i/t;)V
    //   664: invokestatic 685	java/lang/Thread:setDefaultUncaughtExceptionHandler	(Ljava/lang/Thread$UncaughtExceptionHandler;)V
    //   667: invokestatic 447	com/soomla/traceback/i/af:ॱ	()Lcom/soomla/traceback/i/af;
    //   670: invokevirtual 687	com/soomla/traceback/i/af:ˋ	()V
    //   673: aload_0
    //   674: getfield 198	com/soomla/traceback/i/t:ˉ	Lcom/soomla/traceback/i/au;
    //   677: aload 10
    //   679: invokevirtual 689	com/soomla/traceback/i/au:ˋ	(Landroid/content/Context;)V
    //   682: invokestatic 691	com/soomla/traceback/i/t:ʽॱ	()V
    //   685: aload_0
    //   686: aload 10
    //   688: invokespecial 692	com/soomla/traceback/i/t:ˋ	(Landroid/content/Context;)V
    //   691: aload 10
    //   693: getstatic 695	com/soomla/traceback/LocalEventConsts:EVENT_NETWORK_CONNECTED	Ljava/lang/String;
    //   696: new 10	com/soomla/traceback/i/t$10
    //   699: dup
    //   700: aload_0
    //   701: invokespecial 696	com/soomla/traceback/i/t$10:<init>	(Lcom/soomla/traceback/i/t;)V
    //   704: invokestatic 699	com/soomla/traceback/i/x:ˎ	(Landroid/content/Context;Ljava/lang/String;Lcom/soomla/traceback/LocalEvent;)V
    //   707: aload 10
    //   709: getstatic 702	com/soomla/traceback/LocalEventConsts:EVENT_APP_TO_FOREGROUND	Ljava/lang/String;
    //   712: new 28	com/soomla/traceback/i/t$6
    //   715: dup
    //   716: aload_0
    //   717: invokespecial 703	com/soomla/traceback/i/t$6:<init>	(Lcom/soomla/traceback/i/t;)V
    //   720: invokestatic 699	com/soomla/traceback/i/x:ˎ	(Landroid/content/Context;Ljava/lang/String;Lcom/soomla/traceback/LocalEvent;)V
    //   723: aload_0
    //   724: invokespecial 705	com/soomla/traceback/i/t:ᐝॱ	()V
    //   727: aload_0
    //   728: invokespecial 707	com/soomla/traceback/i/t:ʻॱ	()V
    //   731: aload_0
    //   732: invokespecial 220	com/soomla/traceback/i/t:ʿ	()V
    //   735: aload_0
    //   736: iconst_1
    //   737: putfield 160	com/soomla/traceback/i/t:ˊ	Z
    //   740: return
    //   741: astore_1
    //   742: aload 9
    //   744: ldc_w 708
    //   747: sipush 478
    //   750: bipush 35
    //   752: invokestatic 133	com/soomla/traceback/i/t:ˊ	(CII)Ljava/lang/String;
    //   755: invokevirtual 137	java/lang/String:intern	()Ljava/lang/String;
    //   758: aload_1
    //   759: iconst_1
    //   760: invokestatic 550	com/soomla/traceback/i/o:ˏ	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   763: return
    //   764: astore 10
    //   766: goto -450 -> 316
    //   769: bipush 65
    //   771: istore 7
    //   773: goto -499 -> 274
    //   776: iconst_0
    //   777: istore 7
    //   779: iload 7
    //   781: iconst_1
    //   782: if_icmpeq +10 -> 792
    //   785: iload 8
    //   787: istore 5
    //   789: goto -163 -> 626
    //   792: iconst_1
    //   793: istore 5
    //   795: goto -169 -> 626
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	798	0	this	t
    //   0	798	1	paramApplication	Application
    //   0	798	2	paramActivity	Activity
    //   0	798	3	paramString1	String
    //   0	798	4	paramString2	String
    //   0	798	5	paramBoolean1	boolean
    //   0	798	6	paramBoolean2	boolean
    //   6	777	7	i	int
    //   36	750	8	bool	boolean
    //   79	664	9	localObject1	Object
    //   57	651	10	localObject2	Object
    //   764	1	10	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   239	262	741	java/lang/Exception
    //   281	298	741	java/lang/Exception
    //   298	316	741	java/lang/Exception
    //   316	575	741	java/lang/Exception
    //   605	616	741	java/lang/Exception
    //   626	740	741	java/lang/Exception
    //   298	316	764	java/lang/Throwable
  }
  
  private static ax ॱ(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3)
  {
    JSONObject localJSONObject = paramJSONObject2;
    Object localObject;
    if (paramJSONObject2 == null)
    {
      localJSONObject = new JSONObject();
      localObject = ˊ(44632, 0, 16).intern();
      paramJSONObject2 = new StringBuilder();
      paramJSONObject2.append(ˊ('\000', 3212, 25).intern());
      paramJSONObject2.append(paramString);
      LogUtils.LogDebug((String)localObject, paramJSONObject2.toString());
    }
    try
    {
      paramJSONObject2 = x.ˎ(paramJSONObject1, true, paramString.equals(ˊ(49193, 3237, 7).intern()));
      int i;
      if (paramJSONObject2 == null)
      {
        i = ˌ + 87;
        ˋˋ = i % 128;
        if (i % 2 == 0) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 1)
        {
          LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 3244, 30).intern());
          return null;
        }
        LogUtils.LogError(ˊ(44632, 0, 31).intern(), ˊ('\000', 22227, 79).intern());
        return null;
      }
      localObject = new JSONObject();
      ((JSONObject)localObject).put(ˊ('\000', 3274, 4).intern(), paramString);
      x.ˋ((JSONObject)localObject, localJSONObject);
      paramString = ˊ(44733, 3278, 9).intern();
      if (paramJSONObject3 != null)
      {
        paramString = paramJSONObject3.optString(LocalEventConsts.EVENT_KEY_INTEGRATION);
        ((JSONObject)localObject).put(LocalEventConsts.EVENT_KEY_IV, paramJSONObject3.optString(LocalEventConsts.EVENT_KEY_IV, null));
      }
      paramJSONObject1 = paramString;
      if (TextUtils.isEmpty(paramString)) {
        paramJSONObject1 = ˊ('\000', 3287, 4).intern();
      }
      ((JSONObject)localObject).put(LocalEventConsts.EVENT_KEY_INTG, paramJSONObject1);
      boolean bool = ((JSONObject)localObject).has(LocalEventConsts.EVENT_KEY_PLGN);
      if (!bool) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1)
      {
        i = ˌ + 83;
        ˋˋ = i % 128;
        if (i % 2 == 0)
        {
          ((JSONObject)localObject).put(LocalEventConsts.EVENT_KEY_PLGN, paramJSONObject1);
          throw new NullPointerException();
        }
        ((JSONObject)localObject).put(LocalEventConsts.EVENT_KEY_PLGN, paramJSONObject1);
      }
      x.ˋ((JSONObject)localObject, paramJSONObject2);
      paramString = new ax(aw.ॱ((JSONObject)localObject), false);
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramJSONObject2 = ˊ(44632, 0, 16).intern();
      paramJSONObject1 = new StringBuilder();
      paramJSONObject1.append(ˊ(40251, 3291, 38).intern());
      paramJSONObject1.append(paramString.getLocalizedMessage());
      LogUtils.LogError(paramJSONObject2, paramJSONObject1.toString());
    }
    return null;
  }
  
  public static t ॱ()
  {
    try
    {
      if (ˎ == null) {
        ˎ = new t();
      }
      return ˎ;
    }
    finally {}
  }
  
  private JSONObject ॱ(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    boolean bool = TextUtils.isEmpty(this.ˊˋ.ˊ(ˊ('俛', 1300, 14).intern()));
    int i;
    if (bool) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      this.ˊˋ.ˏ(ˊ('俛', 1300, 14).intern(), ˊ('㐡', 1314, 5).intern());
      i = ˌ + 7;
      ˋˋ = i % 128;
    }
    long l1 = ˋ(this.ͺ.getPackageName());
    long l2 = ʽ(this.ͺ.getPackageName());
    if (l1 > 0L) {
      try
      {
        localJSONObject.put(ˊ('\000', 1319, 3).intern(), l1);
        i = ˋˋ + 61;
        ˌ = i % 128;
      }
      catch (JSONException paramString)
      {
        break label342;
      }
    }
    if (l2 > 0L) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 1)
    {
      i = ˌ + 83;
      ˋˋ = i % 128;
      localJSONObject.put(ˊ('൨', 1322, 3).intern(), l2);
    }
    if (bool) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 1)
    {
      i = ˌ + 69;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localJSONObject.put(ˊ('嬇', 1316, 5).intern(), false);
      } else {
        localJSONObject.put(ˊ('嬇', 1325, 2).intern(), true);
      }
    }
    localJSONObject.put(ˊ('\000', 1327, 4).intern(), paramString);
    break label372;
    label342:
    LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ(46155, 1331, 51).intern(), paramString);
    label372:
    ab.ˎ(this.ͺ, localJSONObject);
    x.ˋ(localJSONObject, ॱˎ());
    return localJSONObject;
  }
  
  private static JSONObject ॱ(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(ˊ('Ⲣ', 2257, 3).intern(), paramString1);
      localJSONObject.put(ˊ('\000', 2260, 3).intern(), new JSONObject(paramMap));
      localJSONObject.put(ˊ('䠑', 2263, 4).intern(), paramString2);
      i = ˌ + 9;
      ˋˋ = i % 128;
    }
    catch (JSONException paramString1)
    {
      LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ(48299, 2267, 30).intern(), paramString1);
    }
    int i = ˌ + 63;
    ˋˋ = i % 128;
    return localJSONObject;
  }
  
  public static void ॱ(Context paramContext, Intent paramIntent)
  {
    int i;
    if (paramIntent != null)
    {
      label262:
      do
      {
        do
        {
          try
          {
            ab.ˎ();
            if (ab.ˊ(paramContext)) {
              return;
            }
            localObject1 = paramIntent.getStringExtra(ˊ(52882, 3366, 8).intern());
            ab.ˎ();
            if (localObject1 != null)
            {
              i = ˌ + 75;
              ˋˋ = i % 128;
              if (i % 2 == 0) {
                throw new NullPointerException();
              }
            }
            else
            {
              localObject1 = ˊ('৅', 3374, 4).intern();
            }
            ab.ˊ(paramContext, (String)localObject1);
            Iterator localIterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent(ˊ('\000', 3378, 36).intern()), 0).iterator();
            bool = localIterator.hasNext();
            if (bool) {
              i = 0;
            } else {
              i = 1;
            }
            if (i != 0) {
              return;
            }
            i = ˋˋ + 85;
            ˌ = i % 128;
            if (i % 2 != 0)
            {
              localObject2 = (ResolveInfo)localIterator.next();
              localObject1 = localObject2;
              try
              {
                ((ResolveInfo)localObject2).activityInfo.packageName.equals(paramContext.getPackageName());
                localObject1 = localObject2;
                throw new NullPointerException();
              }
              catch (Throwable localThrowable) {}
            }
            localObject2 = (ResolveInfo)localIterator.next();
            localObject1 = localObject2;
            if (!((ResolveInfo)localObject2).activityInfo.packageName.equals(paramContext.getPackageName())) {
              break label540;
            }
            i = 1;
          }
          catch (Throwable paramContext)
          {
            Object localObject1;
            boolean bool;
            Object localObject2;
            LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ(35944, 3475, 33).intern());
            LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ('嚪', 3508, 8).intern(), paramContext);
            return;
          }
          localObject1 = localObject2;
          bool = ˊ('\000', 3378, 36).intern().equals(paramIntent.getAction());
        } while (!bool);
        i = ˋˋ + 69;
        ˌ = i % 128;
        if (i % 2 == 0) {
          break;
        }
        localObject1 = localObject2;
        bool = InstallBroadcastReceiver.class.getName().equals(((ResolveInfo)localObject2).activityInfo.name);
        localObject1 = localObject2;
        i = 35 / 0;
      } while (bool);
      break label379;
      localObject1 = localObject2;
      if (InstallBroadcastReceiver.class.getName().equals(((ResolveInfo)localObject2).activityInfo.name)) {
        break label550;
      }
      i = 89;
      break label553;
    }
    for (;;)
    {
      label379:
      localObject1 = localObject2;
      ((BroadcastReceiver)Class.forName(((ResolveInfo)localObject2).activityInfo.name).newInstance()).onReceive(paramContext, paramIntent);
      break;
      localObject2 = ˊ(44632, 0, 16).intern();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(ˊ('\000', 3414, 61).intern());
      localStringBuilder.append(((ResolveInfo)localObject1).activityInfo.name);
      LogUtils.LogError((String)localObject2, localStringBuilder.toString(), localThrowable);
      break;
      return;
      label540:
      i = 0;
      if (i == 1) {
        break label262;
      }
      break;
      label550:
      i = 33;
      label553:
      if (i == 33) {
        break;
      }
    }
  }
  
  static e ॱˊ()
  {
    for (;;)
    {
      try
      {
        i = ˌ + 87;
        ˋˋ = i % 128;
        if (i % 2 == 0)
        {
          i = 0;
          if (i != 0)
          {
            if (ˎ.ˋॱ != null) {
              continue;
            }
          }
          else
          {
            localE = ˎ.ˋॱ;
            i = 75 / 0;
            if (localE != null) {
              continue;
            }
          }
          ˎ.ˋॱ = new e();
          ˎ.ˋॱ.start();
          e localE = ˎ.ˋॱ;
          i = ˌ + 89;
          ˋˋ = i % 128;
          return localE;
        }
      }
      finally {}
      int i = 1;
    }
  }
  
  private i ॱˋ()
  {
    for (;;)
    {
      try
      {
        i = ˋˋ + 33;
        ˌ = i % 128;
        if (i % 2 != 0)
        {
          i = 4;
          if (i != 4)
          {
            if (this.ˊॱ != null) {
              continue;
            }
          }
          else
          {
            localI = this.ˊॱ;
            throw new NullPointerException();
          }
          this.ˊॱ = new i();
          i = ˌ + 37;
          ˋˋ = i % 128;
          i localI = this.ˊॱ;
          return localI;
        }
      }
      finally {}
      int i = 64;
    }
  }
  
  private JSONObject ॱˎ()
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      Iterator localIterator = this.ॱˊ.iterator();
      int i = ˌ + 77;
      ˋˋ = i % 128;
      if (i % 2 != 0) {}
      while (localIterator.hasNext())
      {
        TracebackListener localTracebackListener = (TracebackListener)localIterator.next();
        JSONObject localJSONObject3 = new JSONObject();
        localJSONObject3.put(LocalEventConsts.EVENT_KEY_IV, localTracebackListener.getListenerVersion());
        localJSONObject3.put(LocalEventConsts.EVENT_KEY_SIV, localTracebackListener.getSdkVersion());
        localJSONObject2.put(localTracebackListener.getListenerName(), localJSONObject3);
      }
      localJSONObject1.put(ˊ('川', 2759, 4).intern(), localJSONObject2);
      i = ˌ + 121;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        return localJSONObject1;
      }
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 2763, 28).intern(), localJSONException);
    }
    return localJSONObject1;
  }
  
  private void ॱᐝ()
  {
    int i = ˋˋ + 7;
    ˌ = i % 128;
    Object localObject = this.ˊˋ;
    i = 0;
    localObject = ((av)localObject).ˊ(ˊ('\000', 1163, 10).intern());
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      i = 1;
    }
    if (i != 1)
    {
      this.ॱᐝ = 1;
    }
    else
    {
      i = ˌ + 25;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        i = 90;
      } else {
        i = 70;
      }
      if (i != 70) {}
      for (i = Integer.parseInt((String)localObject) * 1;; i = Integer.parseInt((String)localObject) + 1)
      {
        this.ॱᐝ = i;
        break;
      }
    }
    this.ॱˋ = 1;
  }
  
  private void ᐝॱ()
  {
    try
    {
      int i = ˋˋ + 23;
      ˌ = i % 128;
      if (this.ॱˎ == null)
      {
        this.ॱˎ = new Thread(new SafeRunnable()
        {
          private static int ˋ = 0;
          private static int ˎ = 1;
          private static int ˏ = 125;
          
          private static String ˏ(int paramAnonymousInt1, boolean paramAnonymousBoolean, String paramAnonymousString, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            if (paramAnonymousString != null) {
              i = 1;
            } else {
              i = 0;
            }
            Object localObject = paramAnonymousString;
            if (i != 0)
            {
              i = ˎ + 25;
              ˋ = i % 128;
              if (i % 2 != 0) {
                i = 71;
              } else {
                i = 5;
              }
              if (i != 71)
              {
                localObject = paramAnonymousString.toCharArray();
              }
              else
              {
                localObject = paramAnonymousString.toCharArray();
                i = null.length;
              }
            }
            localObject = (char[])localObject;
            paramAnonymousString = new char[paramAnonymousInt2];
            int i = 0;
            while (i < paramAnonymousInt2)
            {
              paramAnonymousString[i] = ((char)(localObject[i] + paramAnonymousInt1));
              paramAnonymousString[i] = ((char)(paramAnonymousString[i] - ˏ));
              i += 1;
            }
            if (paramAnonymousInt3 > 0) {
              paramAnonymousInt1 = 54;
            } else {
              paramAnonymousInt1 = 8;
            }
            if (paramAnonymousInt1 == 54)
            {
              localObject = new char[paramAnonymousInt2];
              System.arraycopy(paramAnonymousString, 0, localObject, 0, paramAnonymousInt2);
              paramAnonymousInt1 = paramAnonymousInt2 - paramAnonymousInt3;
              System.arraycopy(localObject, 0, paramAnonymousString, paramAnonymousInt1, paramAnonymousInt3);
              System.arraycopy(localObject, paramAnonymousInt3, paramAnonymousString, 0, paramAnonymousInt1);
            }
            if (paramAnonymousBoolean) {
              paramAnonymousInt1 = 1;
            } else {
              paramAnonymousInt1 = 0;
            }
            if (paramAnonymousInt1 != 0)
            {
              localObject = new char[paramAnonymousInt2];
              paramAnonymousInt1 = 0;
              while (paramAnonymousInt1 < paramAnonymousInt2)
              {
                localObject[paramAnonymousInt1] = paramAnonymousString[(paramAnonymousInt2 - paramAnonymousInt1 - 1)];
                paramAnonymousInt1 += 1;
                paramAnonymousInt3 = ˎ + 93;
                ˋ = paramAnonymousInt3 % 128;
                if (paramAnonymousInt3 % 2 != 0) {}
              }
              paramAnonymousInt1 = ˎ + 59;
              ˋ = paramAnonymousInt1 % 128;
              paramAnonymousString = (String)localObject;
            }
            return new String(paramAnonymousString);
          }
          
          public final void safeRun()
          {
            LogUtils.LogDebug(ˏ(211, false, "\013\r\017\f\013\r\025�￹￹￷￶￫ￊ￾\034", 16, 7).intern(), ˏ(220, false, "\020\017\024\026\016\006\023￁\025\t\023\006\002\005￁\024\025\002\023\025\006\005￁\r\n\024\025\006\017\n\017\b￁\025\020￁\006\027\006\017\025\024ￏￏￏ￦\027\006\017\025￁\004", 52, 45).intern());
            for (;;)
            {
              if (Thread.currentThread().isInterrupted()) {
                break label127;
              }
              try
              {
                t.ॱ(t.this, (ax)t.ˋ(t.this).take());
              }
              catch (InterruptedException localInterruptedException)
              {
                for (;;) {}
              }
            }
            LogUtils.LogError(ˏ(211, false, "\013\r\017\f\013\r\025�￹￹￷￶￫ￊ￾\034", 16, 7).intern(), ˏ(220, false, "\006\023\016\n\017\002\025\n\017\b￁\n\025\024￁\030\020\023\fￏￏￏ￦\027\006\017\025￁\004\020\017\024\026\016\006\023￁\025\t\023\006\002\005￁\030\002\024￁\n\017\025\006\023\023\026\021\025\006\005ￂ￁￵", 62, 22).intern());
            synchronized (t.this)
            {
              t.ॱॱ(t.this);
              return;
            }
            label127:
          }
        });
        this.ॱˎ.start();
        i = ˋˋ + 99;
        ˌ = i % 128;
        if (i % 2 != 0) {}
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void addTags(List<String> paramList)
  {
    int i = ˋˋ + 29;
    ˌ = i % 128;
    for (;;)
    {
      try
      {
        if (ˏ())
        {
          i = 1;
          if (i != 1)
          {
            if (this.ˊ) {
              break label381;
            }
            i = 0;
            if (i != 0)
            {
              ArrayList localArrayList = new ArrayList();
              localObject1 = paramList.iterator();
              if (((Iterator)localObject1).hasNext())
              {
                localObject2 = (String)((Iterator)localObject1).next();
                if (((String)localObject2).length() > 50)
                {
                  String str = ˊ(44632, 0, 16).intern();
                  StringBuilder localStringBuilder = new StringBuilder();
                  localStringBuilder.append(ˊ('\000', 1485, 17).intern());
                  localStringBuilder.append((String)localObject2);
                  localStringBuilder.append(ˊ('\000', 1502, 38).intern());
                  LogUtils.ExtLogError(str, localStringBuilder.toString());
                  continue;
                }
                localArrayList.add(localObject2);
                continue;
              }
              localObject1 = ˊ(47087, 1540, 8).intern();
              if (localArrayList.size() <= 0) {
                break;
              }
              ˎ((String)localObject1, ˏ(localArrayList), null, true);
              return;
            }
            LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 1428, 57).intern());
            i = ˋˋ + 125;
            ˌ = i % 128;
            return;
          }
          LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ(54776, 1382, 46).intern());
          i = ˋˋ + 29;
          ˌ = i % 128;
          return;
        }
      }
      catch (Exception localException)
      {
        Object localObject1 = ˊ(44632, 0, 16).intern();
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(ˊ('\000', 1548, 18).intern());
        ((StringBuilder)localObject2).append(paramList);
        o.ˏ((String)localObject1, localObject2.toString(), localException, true);
        return;
      }
      i = 0;
      continue;
      label381:
      i = 1;
    }
  }
  
  public void getGAIDWithFallbackAsync(final Context paramContext, final UidReadyCallback paramUidReadyCallback)
  {
    new Thread(new SafeRunnable()
    {
      public final void safeRun()
      {
        final String str = x.ˏ(paramContext);
        new Handler(Looper.getMainLooper()).post(new SafeRunnable()
        {
          public final void safeRun()
          {
            t.1.this.ॱ.onUidReady(str);
          }
        });
      }
    }).start();
    int i = ˌ + 11;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 64;
    } else {
      i = 83;
    }
    if (i != 64) {
      return;
    }
    i = 65 / 0;
  }
  
  public void getUserAdActions(final UserAdActionsCallback paramUserAdActionsCallback)
  {
    int i = ˋˋ + 55;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 54;
    } else {
      i = 51;
    }
    String str3;
    if (i != 54)
    {
      str3 = ˊ(44632, 0, 16).intern();
      localObject = str3;
      try
      {
        boolean bool = ˏ();
        String str1 = str3;
        if (!bool) {
          break label148;
        }
      }
      catch (Exception localException1)
      {
        break label619;
      }
    }
    else
    {
      str3 = ˊ(44632, 0, 38).intern();
      localObject = str3;
      if (!ˏ()) {
        break label662;
      }
      i = 97;
      break label665;
    }
    Object localObject = str3;
    String str2 = ˊ('\000', 1759, 57).intern();
    localObject = str3;
    LogUtils.ExtLogError(str3, str2);
    localObject = str3;
    paramUserAdActionsCallback.onAdActionsFailed(str2);
    return;
    label148:
    localObject = str2;
    if (!this.ˊ)
    {
      localObject = str2;
      str3 = ˊ('\000', 1816, 66).intern();
      localObject = str2;
      LogUtils.ExtLogError(str2, str3);
      localObject = str2;
      paramUserAdActionsCallback.onAdActionsFailed(str3);
      i = ˋˋ + 125;
      ˌ = i % 128;
      if (i % 2 != 0) {
        i = 98 / 0;
      }
      return;
    }
    localObject = str2;
    if (this.ᐝॱ)
    {
      i = 24;
      label244:
      if (i != 24)
      {
        localObject = str2;
        this.ᐝॱ = true;
      }
    }
    for (;;)
    {
      try
      {
        long l1 = this.ˏ.ॱˊ();
        localObject = ˊ('䶟', 1907, 19).intern();
        localObject = this.ˊˋ.ˊ((String)localObject);
        if (localObject == null) {
          break label683;
        }
        i = 0;
        if (i != 1)
        {
          long l2 = x.ˎ();
          long l3 = Long.parseLong((String)localObject);
          if (l2 - l3 >= l1) {
            i = 85;
          } else {
            i = 78;
          }
          if (i == 78)
          {
            i = 0;
            continue;
          }
        }
        i = ˋˋ + 15;
        ˌ = i % 128;
        i = 1;
        if (i == 0)
        {
          localObject = ˊ('\000', 1926, 14).intern();
          localObject = ˎ(new JSONObject(this.ˊˋ.ˊ((String)localObject)));
          this.ᐝॱ = false;
          paramUserAdActionsCallback.onAdActionsReceived((List)localObject);
          return;
        }
        i = ˋˋ + 57;
        ˌ = i % 128;
      }
      catch (Exception localException2)
      {
        localObject = str2;
        o.ˏ(str2, ˊ('\000', 1940, 40).intern(), localException2, true);
      }
      localObject = str2;
      x.ˊ(ˊ('\000', 1980, 32).intern(), new k()
      {
        private static long ˋ = 6655236993632315337L;
        private static int ˎ = 1;
        private static int ॱ;
        
        private static String ˎ(String paramAnonymousString)
        {
          int i = ॱ + 7;
          ˎ = i % 128;
          if (i % 2 == 0)
          {
            i = 37 / 0;
            if (paramAnonymousString != null) {
              i = 94;
            } else {
              i = 55;
            }
            localObject = paramAnonymousString;
            if (i == 55) {
              break label61;
            }
          }
          else
          {
            localObject = paramAnonymousString;
            if (paramAnonymousString == null) {
              break label61;
            }
          }
          Object localObject = paramAnonymousString.toCharArray();
          label61:
          paramAnonymousString = (char[])localObject;
          paramAnonymousString = e.ˏ(ˋ, paramAnonymousString);
          i = 4;
          for (;;)
          {
            if (i < paramAnonymousString.length) {
              j = 40;
            } else {
              j = 95;
            }
            if (j != 40) {
              return new String(paramAnonymousString, 4, paramAnonymousString.length - 4);
            }
            int j = ॱ + 111;
            ˎ = j % 128;
            paramAnonymousString[i] = ((char)(int)(paramAnonymousString[i] ^ paramAnonymousString[(i % 4)] ^ (i - 4) * ˋ));
            i += 1;
            j = ॱ + 31;
            ˎ = j % 128;
          }
        }
        
        public final void ˎ(ay paramAnonymousAy)
        {
          int i = ˎ + 99;
          ॱ = i % 128;
          if (i % 2 != 0)
          {
            t.ˊ(t.this);
            i = 49 / 0;
            if (paramAnonymousAy != null) {
              break label98;
            }
          }
          else
          {
            t.ˊ(t.this);
            if (paramAnonymousAy != null) {
              i = 1;
            } else {
              i = 0;
            }
            if (i == 1) {
              break label98;
            }
          }
          int j = -1;
          int k = ˎ + 55;
          ॱ = k % 128;
          i = j;
          if (k % 2 != 0)
          {
            i = j;
            break label106;
            label98:
            i = paramAnonymousAy.ˋ().ˏ();
          }
          label106:
          paramAnonymousAy = new StringBuilder();
          paramAnonymousAy.append(ˎ("㵪㴯싹祜ꏉ๨ꈼ⎏䴘ᡲ͖茧⹺뮥轞嫁䆉䅲澌﷫⚪Ꙍ좯鴁蟀ތ꧎㱻擥擢ਵ?쐯엙縳").intern());
          paramAnonymousAy.append(i);
          paramAnonymousAy = paramAnonymousAy.toString();
          LogUtils.LogError(ˎ("傡僲眚傜?꾃阾?쿉阰碨캾溛㛬ᮇ浓趯嗈몣谹").intern(), paramAnonymousAy);
          paramUserAdActionsCallback.onAdActionsFailed(paramAnonymousAy);
        }
        
        public final void ˎ(JSONObject paramAnonymousJSONObject)
        {
          try
          {
            Object localObject = ˎ("傡僲眚傜?꾃阾?쿉阰碨캾溛㛬ᮇ浓趯嗈몣谹").intern();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(ˎ("田畣咴猈덫䖃ﲚ㊻떖቟⒢䬍ᕵ焬蝡꡼癏퀁星৅휸ノ섵滠さ鞵ꇚ쾛釀àⲸ售").intern());
            localStringBuilder.append(paramAnonymousJSONObject.toString());
            LogUtils.LogDebug((String)localObject, localStringBuilder.toString());
            localObject = t.ˋ(paramAnonymousJSONObject);
            t.this.ˎ(ˎ("䅗䄾਍ⶪ㡑О瞰猬?饲攠缱䯘廓웬鱓⣺").intern(), paramAnonymousJSONObject.toString(), ˎ("岗峾韪끍䟮ርࠏ攟쏔盈猓拱혰薴탏膏딍⒁ㆼ⁦ᑠ쑈").intern());
            t.ˊ(t.this);
            paramUserAdActionsCallback.onAdActionsReceived((List)localObject);
            int i = ˎ + 39;
            ॱ = i % 128;
            if (i % 2 != 0) {
              i = 0;
            } else {
              i = 1;
            }
            if (i != 0) {
              return;
            }
            throw new NullPointerException();
          }
          catch (Exception localException)
          {
            paramAnonymousJSONObject = new StringBuilder();
            paramAnonymousJSONObject.append(ˎ("鈋鉎鷛멠䚝ॽ隺൝粭肤갱?蓎⍷伀뽵◪숓Ṑ씸改觋葉戛ד⢡墮ͺ꒩쮄㯀ꂁ䞛歎髻䇵").intern());
            paramAnonymousJSONObject.append(localException.getLocalizedMessage());
            paramAnonymousJSONObject = paramAnonymousJSONObject.toString();
            LogUtils.LogError(ˎ("傡僲眚傜?꾃阾?쿉阰碨캾溛㛬ᮇ浓趯嗈몣谹").intern(), paramAnonymousJSONObject);
            paramUserAdActionsCallback.onAdActionsFailed(paramAnonymousJSONObject);
          }
        }
      });
      i = ˋˋ + 41;
      ˌ = i % 128;
      return;
      i = ˌ + 5;
      ˋˋ = i % 128;
      if (i % 2 == 0)
      {
        localObject = str2;
        str4 = ˊ('\000', 12045, 107).intern();
        localObject = str2;
        LogUtils.ExtLogError(str2, str4);
        localObject = str2;
        paramUserAdActionsCallback.onAdActionsFailed(str4);
        return;
      }
      localObject = str2;
      String str4 = ˊ('\000', 1882, 25).intern();
      localObject = str2;
      LogUtils.ExtLogError(str2, str4);
      localObject = str2;
      paramUserAdActionsCallback.onAdActionsFailed(str4);
      return;
      label619:
      o.ˏ((String)localObject, ˊ('穄', 2012, 29).intern(), str2, true);
      paramUserAdActionsCallback.onAdActionsFailed(ˊ('㑺', 2041, 14).intern());
      return;
      label662:
      i = 84;
      label665:
      str2 = str4;
      if (i == 84) {
        break label148;
      }
      break;
      i = 2;
      break label244;
      label683:
      i = 1;
    }
  }
  
  public void initialize(Activity paramActivity, String paramString1, String paramString2)
  {
    int i = ˌ + 91;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 0) {
      initialize(paramActivity, paramString1, paramString2, false);
    } else {
      initialize(paramActivity, paramString1, paramString2, true);
    }
    i = ˌ + 109;
    ˋˋ = i % 128;
  }
  
  public void initialize(Activity paramActivity, String paramString1, String paramString2, boolean paramBoolean)
  {
    int i = ˋˋ + 95;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 62;
    } else {
      i = 6;
    }
    if (i != 6)
    {
      ˏ(paramActivity.getApplication(), paramActivity, paramString1, paramString2, true, paramBoolean);
      return;
    }
    ˏ(paramActivity.getApplication(), paramActivity, paramString1, paramString2, false, paramBoolean);
  }
  
  public void initialize(Application paramApplication, String paramString1, String paramString2)
  {
    int i = ˋˋ + 21;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 49;
    } else {
      i = 16;
    }
    if (i != 16) {}
    for (boolean bool = true;; bool = false)
    {
      initialize(paramApplication, paramString1, paramString2, bool);
      return;
    }
  }
  
  public void initialize(Application paramApplication, String paramString1, String paramString2, boolean paramBoolean)
  {
    int i = ˋˋ + 29;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 1)
    {
      ˏ(paramApplication, null, paramString1, paramString2, false, paramBoolean);
      return;
    }
    ˏ(paramApplication, null, paramString1, paramString2, true, paramBoolean);
  }
  
  public void initializeWithAutoUserId(Activity paramActivity, String paramString)
  {
    int i = ˌ + 31;
    ˋˋ = i % 128;
    initializeWithAutoUserId(paramActivity, paramString, false);
    i = ˌ + 69;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 94;
    } else {
      i = 97;
    }
    if (i != 97) {
      throw new NullPointerException();
    }
  }
  
  public void initializeWithAutoUserId(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    int i = ˌ + 63;
    ˋˋ = i % 128;
    ˏ(paramActivity.getApplication(), paramActivity, paramString, null, true, paramBoolean);
    i = ˌ + 25;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 61;
    } else {
      i = 96;
    }
    if (i != 61) {
      return;
    }
    throw new NullPointerException();
  }
  
  public void initializeWithAutoUserId(Application paramApplication, String paramString)
  {
    int i = ˌ + 65;
    ˋˋ = i % 128;
    initializeWithAutoUserId(paramApplication, paramString, false);
    i = ˋˋ + 57;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 49;
    } else {
      i = 32;
    }
    if (i != 49) {
      return;
    }
    i = null.length;
  }
  
  public void initializeWithAutoUserId(Application paramApplication, String paramString, boolean paramBoolean)
  {
    int i = ˋˋ + 31;
    ˌ = i % 128;
    ˏ(paramApplication, null, paramString, null, true, paramBoolean);
    i = ˌ + 119;
    ˋˋ = i % 128;
  }
  
  public void onCrossPromotionAdClicked(Object paramObject, Advertising.AdType paramAdType, String paramString)
  {
    int i = ˌ + 13;
    ˋˋ = i % 128;
    ॱˋ();
    i.ˎ(paramObject, paramAdType, paramString);
    i = ˌ + 11;
    ˋˋ = i % 128;
  }
  
  public void onCrossPromotionAdClosed(Object paramObject, Advertising.AdType paramAdType, String paramString)
  {
    int i = ˋˋ + 13;
    ˌ = i % 128;
    ॱˋ();
    i.ˊ(paramObject, paramAdType, paramString);
    i = ˋˋ + 117;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 0) {
      return;
    }
    i = 9 / 0;
  }
  
  public void onCrossPromotionAdDisplayed(Object paramObject, Advertising.AdType paramAdType, String paramString)
  {
    int i = ˌ + 85;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 1)
    {
      ॱˋ();
      i.ˋ(paramObject, paramAdType, paramString);
    }
    else
    {
      ॱˋ();
      i.ˋ(paramObject, paramAdType, paramString);
      i = null.length;
    }
    i = ˌ + 59;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 80;
    } else {
      i = 89;
    }
    if (i != 89) {
      i = null.length;
    }
  }
  
  public void onInAppPurchaseCompleted(String paramString1, double paramDouble, String paramString2)
  {
    boolean bool = ˏ();
    int k = 0;
    if (bool)
    {
      int i = ˌ + 23;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        i = 0;
      } else {
        i = 1;
      }
      int j;
      if (i != 0)
      {
        paramString1 = ˊ(44632, 0, 16).intern();
        j = 2114;
      }
      for (i = 64;; i = 81)
      {
        LogUtils.ExtLogError(paramString1, ˊ('\000', j, i).intern());
        break;
        paramString1 = ˊ(44632, 1, 112).intern();
        j = 13430;
      }
      i = ˌ + 19;
      ˋˋ = i % 128;
      if (i % 2 == 0) {
        i = k;
      } else {
        i = 1;
      }
      if (i != 1) {
        throw new NullPointerException();
      }
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(ˊ('\000', 2178, 4).intern(), paramString1);
      localJSONObject.put(ˊ('\000', 2182, 5).intern(), paramDouble);
      localJSONObject.put(ˊ(48375, 2187, 4).intern(), paramString2);
      ˎ(ˊ('\000', 2191, 13).intern(), localJSONObject, null, true);
      return;
    }
    catch (JSONException paramString1)
    {
      LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 2204, 23).intern(), paramString1);
    }
  }
  
  public void overrideCountryCode(String paramString)
  {
    if (ˏ()) {
      i = 71;
    } else {
      i = 28;
    }
    if (i != 71)
    {
      g.ˎ().ˏ(paramString);
      i = ˌ + 123;
      ˋˋ = i % 128;
      return;
    }
    int i = ˌ + 59;
    ˋˋ = i % 128;
    LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('⼈', 2055, 59).intern());
  }
  
  public void removeTags(List<String> paramList)
  {
    int i = ˌ + 29;
    ˋˋ = i % 128;
    label251:
    do
    {
      for (;;)
      {
        try
        {
          if (ˏ())
          {
            i = 0;
            if (i != 0)
            {
              if (!this.ˊ)
              {
                LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('澐', 1615, 60).intern());
                i = ˋˋ + 41;
                ˌ = i % 128;
                if (i % 2 != 0) {
                  i = null.length;
                }
                return;
              }
              String str1 = ˊ(37941, 1675, 11).intern();
              if (paramList != null)
              {
                if (paramList.size() <= 0) {
                  break;
                }
                i = 23;
                break label251;
                ˎ(str1, ˏ(paramList), null, true);
                i = ˌ + 43;
                ˋˋ = i % 128;
              }
              return;
            }
            LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 1566, 49).intern());
            return;
          }
        }
        catch (Exception localException)
        {
          String str2 = ˊ(44632, 0, 16).intern();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(ˊ('\000', 1686, 20).intern());
          localStringBuilder.append(paramList);
          o.ˏ(str2, localStringBuilder.toString(), localException, true);
          return;
        }
        i = 1;
      }
      i = 37;
    } while (i == 23);
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    if (ˏ()) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 1)
    {
      i = ˌ + 115;
      ˋˋ = i % 128;
      LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('炴', 1706, 53).intern());
      return;
    }
    this.ˉ.ˎ(paramAdListener);
    int i = ˌ + 123;
    ˋˋ = i % 128;
  }
  
  public void setUserConsent(boolean paramBoolean)
  {
    int i = ˌ + 51;
    ˋˋ = i % 128;
    g.ˎ().ˋ(paramBoolean);
    i = ˋˋ + 83;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 13;
    } else {
      i = 25;
    }
    if (i != 25) {
      i = 13 / 0;
    }
  }
  
  public void setUserId(String paramString)
  {
    try
    {
      boolean bool1 = ˏ();
      int i;
      if (bool1)
      {
        i = ˌ + 77;
        ˋˋ = i % 128;
        LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('⺄', 513, 49).intern());
        i = ˋˋ + 111;
        ˌ = i % 128;
        return;
      }
      bool1 = this.ˊ;
      if (!bool1)
      {
        i = ˋˋ + 71;
        ˌ = i % 128;
        LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 562, 62).intern());
        return;
      }
      if (paramString == null) {
        i = 9;
      } else {
        i = 90;
      }
      if (i == 9)
      {
        i = ˌ + 111;
        ˋˋ = i % 128;
        if (i % 2 == 0)
        {
          String str1 = g.ˎ().ᐝ();
          i = null.length;
          if (str1 != null) {
            break label234;
          }
        }
        else
        {
          if (g.ˎ().ᐝ() != null) {
            break label234;
          }
        }
      }
      if (paramString != null)
      {
        bool1 = paramString.equals(g.ˎ().ᐝ());
        if (!bool1) {}
      }
      else
      {
        i = ˌ + 11;
        ˋˋ = i % 128;
        bool1 = false;
        break label236;
      }
      label234:
      bool1 = true;
      label236:
      boolean bool2 = this.ॱॱ.ˊ();
      if (bool1) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 1)
      {
        i = ˋˋ + 87;
        ˌ = i % 128;
        if (bool2) {
          ˎ(ˊ(39666, 624, 11).intern(), new JSONObject(), null, true);
        }
      }
      ˋ(null, paramString, bool1, true, bool2);
      return;
    }
    catch (Exception localException)
    {
      String str2 = ˊ(44632, 0, 16).intern();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(ˊ('\000', 635, 21).intern());
      localStringBuilder.append(paramString);
      o.ˏ(str2, localStringBuilder.toString(), localException, true);
    }
  }
  
  public void shutdown()
  {
    for (;;)
    {
      try
      {
        i = ˌ + 23;
        ˋˋ = i % 128;
        int j = 1;
        if (i % 2 == 0)
        {
          i = 1;
          if (i != 0) {
            ˋ(true);
          } else {
            ˋ(false);
          }
          i = ˋˋ + 9;
          ˌ = i % 128;
          if (i % 2 == 0) {
            break label98;
          }
          i = j;
          if (i != 0)
          {
            i = 31 / 0;
            return;
          }
          return;
        }
      }
      finally {}
      int i = 0;
      continue;
      label98:
      i = 0;
    }
  }
  
  public final av ʻ()
  {
    int i = ˋˋ + 73;
    ˌ = i % 128;
    av localAv = this.ˊˋ;
    i = ˌ + 15;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 19;
    } else {
      i = 82;
    }
    if (i != 82) {
      i = 58 / 0;
    }
    return localAv;
  }
  
  public final JSONObject ʼ()
  {
    JSONObject localJSONObject = ˊ(new JSONObject(), false);
    int i = ˌ + 57;
    ˋˋ = i % 128;
    return localJSONObject;
  }
  
  public final void ʽ()
  {
    int i = ˋˋ + 69;
    ˌ = i % 128;
    ʼॱ();
    i = ˌ + 121;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 65;
    } else {
      i = 32;
    }
    if (i != 65) {
      return;
    }
    i = null.length;
  }
  
  public final Context ˊ()
  {
    int i = ˋˋ + 99;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 19;
    } else {
      i = 48;
    }
    if (i != 19) {
      return this.ͺ;
    }
    Context localContext = this.ͺ;
    throw new NullPointerException();
  }
  
  public final void ˊ(au.c paramC)
  {
    int i = ˋˋ + 123;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 27;
    } else {
      i = 55;
    }
    if (i != 27)
    {
      this.ˉ.ˋ(paramC);
      return;
    }
    this.ˉ.ˋ(paramC);
    i = null.length;
  }
  
  public final void ˊ(p paramP)
  {
    int i = ˌ + 121;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 25;
    } else {
      i = 55;
    }
    if (i != 55)
    {
      this.ˏ.ˋ(paramP);
      i = 16 / 0;
      return;
    }
    this.ˏ.ˋ(paramP);
  }
  
  public final void ˊ(String paramString, JSONObject paramJSONObject)
  {
    int i = ˌ + 109;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 1;
    } else {
      i = 92;
    }
    if (i != 1)
    {
      ˎ(paramString, paramJSONObject, null, true);
      return;
    }
    ˎ(paramString, paramJSONObject, null, false);
  }
  
  public final boolean ˊ(String paramString)
  {
    int i = ˋˋ + 111;
    ˌ = i % 128;
    if (ˎ(paramString) != null) {
      i = 70;
    } else {
      i = 52;
    }
    if (i != 52)
    {
      i = ˋˋ + 113;
      ˌ = i % 128;
      return true;
    }
    i = ˋˋ + 57;
    ˌ = i % 128;
    return false;
  }
  
  public final void ˊॱ()
  {
    try
    {
      this.ˊˊ = true;
      ˎ(ˊ(39666, 624, 11).intern(), new JSONObject(), null, false);
      try
      {
        if (this.ʻॱ != null) {
          this.ʻॱ.removeCallbacksAndMessages(null);
        }
        ʿ();
        return;
      }
      finally {}
      return;
    }
    catch (Exception localException)
    {
      o.ˏ(ˊ(44632, 0, 16).intern(), ˊ('\000', 3342, 24).intern(), localException, false);
    }
  }
  
  public final long ˋ(String paramString)
  {
    int i = ˋˋ + 67;
    ˌ = i % 128;
    try
    {
      long l = this.ͺ.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      i = ˋˋ + 77;
      ˌ = i % 128;
      if (i % 2 != 0) {
        i = 87;
      } else {
        i = 83;
      }
      if (i != 83) {
        i = null.length;
      }
      return l;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return 0L;
  }
  
  public final aq ˋ()
  {
    int i = ˌ + 111;
    ˋˋ = i % 128;
    aq localAq = this.ʽॱ;
    i = ˋˋ + 55;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 1) {
      return localAq;
    }
    i = null.length;
    return localAq;
  }
  
  public final void ˋॱ()
  {
    int i = ˌ + 95;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 13;
    } else {
      i = 81;
    }
    if (i != 13)
    {
      if (!this.ˊˊ) {
        break label118;
      }
    }
    else
    {
      boolean bool = this.ˊˊ;
      throw new NullPointerException();
    }
    ᐝॱ();
    ʻॱ();
    ˎ(ˊ(38218, 3329, 13).intern(), new JSONObject(), null, true);
    i = ˋˋ + 71;
    ˌ = i % 128;
    if (i % 2 == 0) {}
    label118:
    this.ˊˊ = false;
  }
  
  public final at ˎ()
  {
    int i = ˋˋ + 81;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    at localAt;
    if (i != 0)
    {
      localAt = this.ʼॱ;
      i = null.length;
    }
    else
    {
      localAt = this.ʼॱ;
    }
    i = ˋˋ + 107;
    ˌ = i % 128;
    return localAt;
  }
  
  public final void ˎ(String paramString1, String paramString2, String paramString3)
  {
    int i = ˋˋ + 7;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      this.ˊˋ.ˏ(paramString1, paramString2);
      this.ˊˋ.ˏ(paramString3, Long.toString(x.ˎ()));
      i = null.length;
      return;
    }
    this.ˊˋ.ˏ(paramString1, paramString2);
    this.ˊˋ.ˏ(paramString3, Long.toString(x.ˎ()));
  }
  
  public final void ˎ(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    int i = ˋˋ + 73;
    ˌ = i % 128;
    if (i % 2 != 0)
    {
      i = null.length;
      if (paramMap == null) {
        return;
      }
    }
    else if (paramMap == null)
    {
      return;
    }
    String str = Integer.toHexString(paramMap.hashCode());
    Object localObject = this.ˊˋ;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(ˊ('\000', 2234, 14).intern());
    localStringBuilder.append(paramString1);
    localObject = ((av)localObject).ˊ(localStringBuilder.toString());
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      i = 75;
    } else {
      i = 76;
    }
    if (i != 76)
    {
      i = ˋˋ + 89;
      ˌ = i % 128;
      if (i % 2 != 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        boolean bool = localObject.equals(str);
        i = null.length;
        if (bool) {
          return;
        }
      }
      else if (localObject.equals(str))
      {
        return;
      }
    }
    localObject = this.ˊˋ;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(ˊ('\000', 2234, 14).intern());
    localStringBuilder.append(paramString1);
    ((av)localObject).ˏ(localStringBuilder.toString(), str);
    ˎ(ˊ(53828, 2248, 9).intern(), ॱ(paramString1, paramMap, paramString2), null, true);
  }
  
  public final void ˎ(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    int j = ˋˋ + 51;
    ˌ = j % 128;
    int i = 1;
    if (j % 2 == 0) {}
    ˎ(paramString, paramJSONObject1, paramJSONObject2, true);
    j = ˋˋ + 81;
    ˌ = j % 128;
    if (j % 2 != 0) {
      i = 0;
    }
    if (i != 0) {
      return;
    }
    i = 77 / 0;
  }
  
  public final void ˎ(boolean paramBoolean)
  {
    int i = ˋˋ + 75;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      boolean bool = ˏ();
      i = null.length;
      if (bool) {
        i = 87;
      } else {
        i = 49;
      }
      if (i != 49) {
        break label151;
      }
    }
    else
    {
      if (ˏ()) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 1) {
        break label151;
      }
    }
    if (this.ˊ) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 1)
    {
      this.ʽ = paramBoolean;
      return;
    }
    LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('✉', 1224, 39).intern());
    i = ˌ + 23;
    ˋˋ = i % 128;
    return;
    label151:
    LogUtils.ExtLogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 1173, 51).intern());
    i = ˌ + 25;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      throw new NullPointerException();
    }
  }
  
  public final String ˏ(String paramString)
  {
    int i = ˋˋ + 5;
    ˌ = i % 128;
    paramString = this.ˊˋ.ˊ(paramString);
    i = ˌ + 55;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 11;
    } else {
      i = 45;
    }
    if (i != 45) {
      throw new NullPointerException();
    }
    return paramString;
  }
  
  public final void ˏ(String paramString, Map<String, String> paramMap)
  {
    int i = ˋˋ + 59;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 60;
    } else {
      i = 68;
    }
    if (i != 68)
    {
      ˎ(paramString, paramMap, null);
      i = 70 / 0;
    }
    else
    {
      ˎ(paramString, paramMap, null);
    }
    i = ˋˋ + 85;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 97;
    } else {
      i = 39;
    }
    if (i != 97) {
      return;
    }
    i = 43 / 0;
  }
  
  public final boolean ˏ()
  {
    for (;;)
    {
      try
      {
        i = ˌ + 3;
        ˋˋ = i % 128;
        if (i % 2 == 0)
        {
          i = 80;
          boolean bool;
          if (i != 11)
          {
            bool = this.ʻ;
            i = 72 / 0;
          }
          else
          {
            bool = this.ʻ;
          }
          i = ˌ + 51;
          ˋˋ = i % 128;
          if (i % 2 == 0) {
            i = 11;
          } else {
            i = 75;
          }
          if (i != 11) {
            return bool;
          }
          i = 2 / 0;
          return bool;
        }
      }
      finally {}
      int i = 11;
    }
  }
  
  public final boolean ˏ(long paramLong, String paramString)
  {
    paramString = this.ˊˋ.ˊ(paramString);
    if (paramString != null)
    {
      i = ˋˋ + 79;
      ˌ = i % 128;
      if (i % 2 != 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 1)
      {
        if (x.ˎ() - Long.parseLong(paramString) >= paramLong) {
          i = 91;
        } else {
          i = 7;
        }
        if (i != 91) {
          return false;
        }
      }
      else
      {
        if (x.ˎ() * Long.parseLong(paramString) >= paramLong) {
          i = 0;
        } else {
          i = 1;
        }
        if (i == 1) {
          return false;
        }
      }
    }
    int i = ˋˋ + 113;
    ˌ = i % 128;
    return true;
  }
  
  public final r ˏॱ()
  {
    int i = ˌ + 85;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 1;
    } else {
      i = 0;
    }
    r localR;
    if (i != 0)
    {
      localR = this.ˏ;
      i = 32 / 0;
    }
    else
    {
      localR = this.ˏ;
    }
    i = ˌ + 57;
    ˋˋ = i % 128;
    return localR;
  }
  
  public final l ͺ()
  {
    int i = ˋˋ + 125;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 3;
    } else {
      i = 79;
    }
    l localL;
    if (i != 3)
    {
      localL = this.ॱॱ;
    }
    else
    {
      localL = this.ॱॱ;
      i = 98 / 0;
    }
    i = ˋˋ + 27;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 84;
    } else {
      i = 94;
    }
    if (i != 94) {
      i = null.length;
    }
    return localL;
  }
  
  public final void ॱ(String paramString1, String paramString2, String paramString3, String paramString4, JSONObject paramJSONObject, boolean paramBoolean)
  {
    int i = ˌ + 61;
    ˋˋ = i % 128;
    if (i % 2 == 0) {
      i = 32;
    } else {
      i = 88;
    }
    if (i != 88)
    {
      this.ˋˊ.contains(paramString3);
      throw new NullPointerException();
    }
    if (this.ˋˊ.contains(paramString3))
    {
      if (!paramBoolean) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 1)
      {
        i = ˋˋ + 63;
        ˌ = i % 128;
        if (i % 2 != 0)
        {
          paramBoolean = this.ˏ.ˋॱ();
          i = null.length;
          if (paramBoolean) {
            break label167;
          }
        }
        else
        {
          if (this.ˏ.ˋॱ()) {
            break label167;
          }
        }
        i = ˌ + 67;
        ˋˋ = i % 128;
        return;
      }
    }
    label167:
    this.ˋˊ.add(paramString3);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(ˊ('\000', 143, 5).intern(), paramString1);
      localJSONObject.put(ˊ('\000', 148, 5).intern(), paramString2);
      localJSONObject.put(ˊ(43513, 153, 6).intern(), paramString3);
      paramBoolean = TextUtils.isEmpty(paramString4);
      if (!paramBoolean)
      {
        i = ˌ + 13;
        ˋˋ = i % 128;
        localJSONObject.put(ˊ('␍', 159, 5).intern(), paramString4);
      }
      x.ˋ(localJSONObject, ॱˎ());
      if (paramJSONObject != null) {
        i = 46;
      } else {
        i = 87;
      }
      if (i != 87)
      {
        i = ˋˋ + 105;
        ˌ = i % 128;
        x.ˋ(localJSONObject, paramJSONObject);
      }
      i = ˋˋ + 51;
      ˌ = i % 128;
      if (i % 2 != 0) {
        break label411;
      }
    }
    catch (JSONException paramString1)
    {
      LogUtils.LogError(ˊ(44632, 0, 16).intern(), ˊ('\000', 164, 26).intern(), paramString1);
    }
    label411:
    ˎ(ˊ(49597, 190, 8).intern(), localJSONObject, null, true);
    i = ˋˋ + 33;
    ˌ = i % 128;
    if (i % 2 != 0)
    {
      i = 71 / 0;
      return;
    }
  }
  
  public final boolean ॱॱ()
  {
    int i = ˋˋ + 57;
    ˌ = i % 128;
    boolean bool = this.ʽ;
    i = ˋˋ + 43;
    ˌ = i % 128;
    return bool;
  }
  
  public final int ᐝ()
  {
    int i = ˋˋ + 55;
    ˌ = i % 128;
    ApplicationInfo localApplicationInfo;
    if (i % 2 != 0)
    {
      localApplicationInfo = ˎ(ˊ('犝', 20499, 18).intern());
      if (localApplicationInfo != null) {
        i = 41;
      } else {
        i = 88;
      }
      if (i == 88) {
        break label120;
      }
    }
    else
    {
      localApplicationInfo = ˎ(ˊ('犝', 2941, 19).intern());
      if (localApplicationInfo == null) {
        break label120;
      }
    }
    i = localApplicationInfo.uid;
    int j = ˌ + 1;
    ˋˋ = j % 128;
    if (j % 2 == 0) {
      throw new NullPointerException();
    }
    return i;
    label120:
    i = ˋˋ + 75;
    ˌ = i % 128;
    if (i % 2 != 0) {
      i = 48;
    } else {
      i = 20;
    }
    if (i != 20) {
      i = null.length;
    }
    return -1;
  }
  
  static final class e
    extends Thread
  {
    private Handler ˏ;
    
    e() {}
    
    public final void run()
    {
      Looper.prepare();
      this.ˏ = new Handler();
      Looper.loop();
    }
    
    public final Handler ˏ()
    {
      return this.ˏ;
    }
  }
}
