package com.boo.boomoji.Friends.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.boo.boomoji.Controller.BooMojiApplication;
import com.boo.boomoji.Controller.LocalDataController.Constant;
import com.boo.boomoji.Controller.LocalDataController.LocalData;
import com.boo.boomoji.Friends.LOGUtils;
import com.boo.boomoji.Friends.Util.PageJumpUtil;
import com.boo.boomoji.Friends.Util.ScrollLinearLayoutManager;
import com.boo.boomoji.Friends.Util.permission.PermissionBaseUtil;
import com.boo.boomoji.Friends.event.AnonymousLoveEvent;
import com.boo.boomoji.Friends.event.DownLoadEvent;
import com.boo.boomoji.Friends.event.ImageViewEvent;
import com.boo.boomoji.Friends.event.InviteImageEvent;
import com.boo.boomoji.Friends.event.LoveletterGuideEvent;
import com.boo.boomoji.Friends.event.LoveletterImageEvent;
import com.boo.boomoji.Friends.event.NewFriendRetryEvent;
import com.boo.boomoji.Friends.event.NewInviteRetryEvent;
import com.boo.boomoji.Friends.event.OnChickEvent;
import com.boo.boomoji.Friends.event.RetryMojiEvent;
import com.boo.boomoji.Friends.event.SmSEvent;
import com.boo.boomoji.Friends.service.model.InviteMessage;
import com.boo.boomoji.Utils.GeneralUtils.BooMojiUtils;
import com.boo.boomoji.user.utils.PreferenceManager;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import io.reactivex.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.greenrobot.eventbus.EventBus;

public class RecyclerviewAdapter
  extends RecyclerView.Adapter
{
  private static final int CONTENT_COUNT = 1;
  private static final int FOOT_COUNT = 1;
  private static final int HEAD_COUNT = 1;
  private static final int TYPE_CONTENT = 1;
  private static final int TYPE_FOOTER = 3;
  private static final int TYPE_FRIEND = 2;
  private static final int TYPE_HEAD = 0;
  private Items anonyItems;
  private MultiTypeAdapter anonyMultiTypeAdapter = null;
  private Context context;
  private int friendTmp = 0;
  private Items inviteItems;
  private List<InviteMessage> inviteMessages;
  private boolean isContactsBoomoji;
  private boolean isSelectedBoomoji;
  private Items items;
  private MultiTypeAdapter mMultiTypeAdapter = null;
  private MultiTypeAdapter mMultiTypeAdapterInvite = null;
  private int newAnonyType;
  private int newFriendType;
  private int newInviteType;
  private int newStarType;
  private int noContacts = 0;
  private Items starItems;
  private int tmp = 0;
  private int width = 0;
  
  public RecyclerviewAdapter(Context paramContext)
  {
    this.context = paramContext;
    this.inviteMessages = new ArrayList();
    this.width = paramContext.getResources().getDisplayMetrics().widthPixels;
    this.tmp = (this.width / 4 - 65);
    this.friendTmp = (this.width / 4 - 65);
    this.items = new Items();
    this.mMultiTypeAdapter = new MultiTypeAdapter();
    this.mMultiTypeAdapter.register(FriendItem.class, new FriendItemViewBinder(this.tmp));
    this.anonyItems = new Items();
    this.anonyMultiTypeAdapter = new MultiTypeAdapter();
    this.anonyMultiTypeAdapter.register(InviteItem.class, new AnonymousItemViewBinder());
    this.inviteItems = new Items();
    this.mMultiTypeAdapterInvite = new MultiTypeAdapter();
    this.mMultiTypeAdapterInvite.register(InviteItem.class, new InviteItemViewBinder());
  }
  
  private void loadingFriend(SimpleDraweeView paramSimpleDraweeView, Context paramContext)
  {
    paramSimpleDraweeView.setVisibility(0);
    paramContext = paramContext.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("res://");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("/");
    localStringBuilder.append(2131558419);
    paramContext = ImageRequestBuilder.newBuilderWithSource(Uri.parse(localStringBuilder.toString())).build();
    paramSimpleDraweeView.setController(((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setImageRequest(paramContext)).setAutoPlayAnimations(true)).build());
  }
  
  private void loadingInvite(SimpleDraweeView paramSimpleDraweeView, Context paramContext)
  {
    paramSimpleDraweeView.setVisibility(0);
    paramContext = paramContext.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("res://");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("/");
    localStringBuilder.append(2131558419);
    paramContext = ImageRequestBuilder.newBuilderWithSource(Uri.parse(localStringBuilder.toString())).build();
    paramSimpleDraweeView.setController(((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setImageRequest(paramContext)).setAutoPlayAnimations(true)).build());
  }
  
  public void addData(List<InviteMessage> paramList, boolean paramBoolean)
  {
    int i = this.inviteMessages.size();
    this.inviteMessages.addAll(paramList);
    int j = this.inviteMessages.size();
    this.isSelectedBoomoji = paramBoolean;
    paramList = new StringBuilder();
    paramList.append("...showInvite....load data   ---- rangeStart：  ");
    paramList.append(i);
    paramList.append("     rangeEnd  ： ");
    paramList.append(j);
    LOGUtils.LOGE(paramList.toString());
    notifyItemRangeInserted(i, j);
  }
  
  public void addHeadWh(Items paramItems)
  {
    this.items = paramItems;
    notifyItemChanged(0);
  }
  
  public int getContentSize()
  {
    return this.inviteMessages.size() + 2;
  }
  
  public int getItemCount()
  {
    return this.inviteMessages.size();
  }
  
  public int getItemViewType(int paramInt)
  {
    int i = getContentSize();
    if (paramInt == 0) {
      return 0;
    }
    if (paramInt == 1) {
      return 2;
    }
    if (paramInt == i + 2) {
      return 3;
    }
    return 1;
  }
  
  public int getSelectMoji()
  {
    return this.newStarType;
  }
  
  public Items getboomojiStar()
  {
    this.starItems = this.items;
    return this.starItems;
  }
  
  public boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public boolean isFoot(int paramInt)
  {
    return paramInt == getContentSize() + 2;
  }
  
  public boolean isHead(int paramInt)
  {
    return paramInt == 0;
  }
  
  public void onAttachedToRecyclerView(final RecyclerView paramRecyclerView)
  {
    super.onAttachedToRecyclerView(paramRecyclerView);
    paramRecyclerView = paramRecyclerView.getLayoutManager();
    if ((paramRecyclerView instanceof GridLayoutManager))
    {
      paramRecyclerView = (GridLayoutManager)paramRecyclerView;
      paramRecyclerView.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
      {
        public int getSpanSize(int paramAnonymousInt)
        {
          paramAnonymousInt = RecyclerviewAdapter.this.getItemViewType(paramAnonymousInt);
          if (paramAnonymousInt != 0)
          {
            if (paramAnonymousInt != 2) {
              return 1;
            }
            return paramRecyclerView.getSpanCount();
          }
          return paramRecyclerView.getSpanCount();
        }
      });
    }
  }
  
  public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("InviteHolder=inviteItems=");
    ((StringBuilder)localObject1).append(this.inviteItems.size());
    LOGUtils.LOGE(((StringBuilder)localObject1).toString());
    Object localObject2;
    if ((paramViewHolder instanceof HeadHolder))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("InviteHolder=HeadHolder=");
      ((StringBuilder)localObject1).append(this.inviteMessages.size());
      LOGUtils.LOGE(((StringBuilder)localObject1).toString());
      localObject1 = (HeadHolder)paramViewHolder;
      if (this.noContacts == 1)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("v==noContacts");
        ((StringBuilder)localObject2).append(this.noContacts);
        ((StringBuilder)localObject2).append("===");
        ((StringBuilder)localObject2).append(System.currentTimeMillis());
        LOGUtils.LOGE(((StringBuilder)localObject2).toString());
        ((HeadHolder)localObject1).contactNo.setVisibility(8);
      }
      BooMojiUtils.loadRefreshLoading(paramViewHolder.itemView.getContext(), ((HeadHolder)localObject1).imageFriendBoomoji, 2131558404);
      paramViewHolder = new StringBuilder();
      paramViewHolder.append("inviteMessages===:");
      paramViewHolder.append(this.inviteMessages.size());
      LOGUtils.LOGE(paramViewHolder.toString());
      if ((PreferenceManager.getInstance().getRegisterPhone() != null) && (!PreferenceManager.getInstance().getRegisterPhone().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS")))
      {
        if (isAvilible(this.context, "com.boo.chat")) {
          ((HeadHolder)localObject1).imGG.setVisibility(8);
        } else {
          ((HeadHolder)localObject1).imGG.setVisibility(0);
        }
        ((HeadHolder)localObject1).contactNo.setVisibility(8);
        ((HeadHolder)localObject1).recycle_view_love.setVisibility(0);
        if (this.anonyItems.size() > 0)
        {
          ((HeadHolder)localObject1).recycle_view_love.setVisibility(0);
          this.anonyMultiTypeAdapter.setItems(this.anonyItems);
          paramViewHolder = new GridLayoutManager(this.context, 4);
          paramViewHolder.setOrientation(1);
          ((HeadHolder)localObject1).recycle_view_love.setLayoutManager(paramViewHolder);
          ((HeadHolder)localObject1).recycle_view_love.setHasFixedSize(true);
          ((HeadHolder)localObject1).recycle_view_love.setNestedScrollingEnabled(false);
          ((HeadHolder)localObject1).recycle_view_love.setAdapter(this.anonyMultiTypeAdapter);
          ((HeadHolder)localObject1).view_anony_contacts.setVisibility(8);
        }
        else
        {
          ((HeadHolder)localObject1).view_anony_contacts.setVisibility(0);
        }
      }
      else if ((!PreferenceManager.getInstance().getSkipPhoneNumber().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS")))
      {
        ((HeadHolder)localObject1).contactNo.setVisibility(8);
        if (isAvilible(this.context, "com.boo.chat")) {
          ((HeadHolder)localObject1).imGG.setVisibility(8);
        } else {
          ((HeadHolder)localObject1).imGG.setVisibility(0);
        }
      }
      else
      {
        ((HeadHolder)localObject1).contactNo.setVisibility(0);
        ((HeadHolder)localObject1).recycle_view_love.setVisibility(8);
      }
      if (this.newStarType == 0)
      {
        ((HeadHolder)localObject1).image_loading_contacts.setVisibility(0);
        ((HeadHolder)localObject1).rel_fail.setVisibility(8);
        loadingFriend(((HeadHolder)localObject1).image_loading_contacts, BooMojiApplication.getAppContext());
        ((HeadHolder)localObject1).text_boomoji_star.setVisibility(8);
        ((HeadHolder)localObject1).seeAll.setVisibility(8);
      }
      else if (this.newStarType == 1)
      {
        ((HeadHolder)localObject1).image_loading_contacts.setVisibility(8);
        ((HeadHolder)localObject1).rel_fail.setVisibility(0);
        ((HeadHolder)localObject1).text_boomoji_star.setVisibility(8);
        ((HeadHolder)localObject1).seeAll.setVisibility(8);
      }
      else if (this.newStarType == 2)
      {
        ((HeadHolder)localObject1).image_loading_contacts.setVisibility(8);
        ((HeadHolder)localObject1).rel_fail.setVisibility(8);
        if (this.items.size() > 0) {
          ((HeadHolder)localObject1).text_boomoji_star.setVisibility(8);
        } else {
          ((HeadHolder)localObject1).text_boomoji_star.setVisibility(0);
        }
        ((HeadHolder)localObject1).seeAll.setVisibility(8);
      }
      else
      {
        ((HeadHolder)localObject1).image_loading_contacts.setVisibility(8);
        ((HeadHolder)localObject1).rel_fail.setVisibility(8);
        ((HeadHolder)localObject1).text_boomoji_star.setVisibility(8);
        ((HeadHolder)localObject1).seeAll.setVisibility(0);
      }
      if (this.items.size() > 0)
      {
        ((HeadHolder)localObject1).rel_fail.setVisibility(8);
        ((HeadHolder)localObject1).image_loading_contacts.setVisibility(8);
        ((HeadHolder)localObject1).whRecyclerView.setVisibility(0);
        this.mMultiTypeAdapter.setItems(this.items);
        paramViewHolder = new ScrollLinearLayoutManager(this.context);
        paramViewHolder.setOrientation(0);
        ((HeadHolder)localObject1).whRecyclerView.setLayoutManager(paramViewHolder);
        ((HeadHolder)localObject1).whRecyclerView.setHasFixedSize(true);
        ((HeadHolder)localObject1).whRecyclerView.setNestedScrollingEnabled(false);
        ((HeadHolder)localObject1).whRecyclerView.setAdapter(this.mMultiTypeAdapter);
      }
      if ((PreferenceManager.getInstance().getRegisterPhone() != null) && (!PreferenceManager.getInstance().getRegisterPhone().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS"))) {
        if (this.newAnonyType == 0)
        {
          ((HeadHolder)localObject1).image_loading_anony.setVisibility(0);
          ((HeadHolder)localObject1).rel_anony_fail.setVisibility(8);
          loadingFriend(((HeadHolder)localObject1).image_loading_anony, BooMojiApplication.getAppContext());
        }
        else if (this.newAnonyType == 1)
        {
          ((HeadHolder)localObject1).image_loading_anony.setVisibility(8);
          ((HeadHolder)localObject1).rel_anony_fail.setVisibility(8);
        }
        else if (this.newAnonyType == 2)
        {
          ((HeadHolder)localObject1).image_loading_anony.setVisibility(8);
          ((HeadHolder)localObject1).rel_anony_fail.setVisibility(8);
        }
        else
        {
          ((HeadHolder)localObject1).image_loading_anony.setVisibility(8);
          ((HeadHolder)localObject1).rel_anony_fail.setVisibility(8);
        }
      }
      if (BooMojiApplication.getLocalData().getInt(Constant.ANONYMOUS_BELICKE) > 0)
      {
        if (BooMojiApplication.getLocalData().getInt(Constant.ANONYMOUS_BELICKE) > 99)
        {
          paramViewHolder = "99+";
        }
        else
        {
          paramViewHolder = new StringBuilder();
          paramViewHolder.append(BooMojiApplication.getLocalData().getInt(Constant.ANONYMOUS_BELICKE));
          paramViewHolder.append("");
          paramViewHolder = paramViewHolder.toString();
        }
        ((HeadHolder)localObject1).iv_friend_new.setVisibility(0);
        if (paramViewHolder.equalsIgnoreCase("99+")) {
          ((HeadHolder)localObject1).iv_friend_new.setBackground(BooMojiApplication.getAppContext().getResources().getDrawable(2131230960));
        } else {
          ((HeadHolder)localObject1).iv_friend_new.setBackground(BooMojiApplication.getAppContext().getResources().getDrawable(2131230959));
        }
        ((HeadHolder)localObject1).iv_friend_new.setText(paramViewHolder);
      }
      else
      {
        ((HeadHolder)localObject1).iv_friend_new.setVisibility(4);
      }
      ((HeadHolder)localObject1).rel_anony_fail.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new AnonymousLoveEvent());
        }
      });
      ((HeadHolder)localObject1).image_love_letter.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new LoveletterImageEvent());
          BooMojiApplication.getLocalData().setBoolean(Constant.BELICKE, false);
        }
      });
      ((HeadHolder)localObject1).image_my_crashe.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new LoveletterGuideEvent());
        }
      });
      ((HeadHolder)localObject1).rel_fail.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new RetryMojiEvent());
        }
      });
      ((HeadHolder)localObject1).seeAll.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          PageJumpUtil.jumpFriendsAllActivity(this.val$myHolder.itemView.getContext());
        }
      });
      ((HeadHolder)localObject1).friendSetting.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new OnChickEvent(0));
        }
      });
      ((HeadHolder)localObject1).imGG.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          EventBus.getDefault().post(new DownLoadEvent());
        }
      });
      ((HeadHolder)localObject1).image_view_friend.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          EventBus.getDefault().post(new ImageViewEvent());
        }
      });
      return;
    }
    if ((paramViewHolder instanceof InviteHolder))
    {
      paramViewHolder = (InviteHolder)paramViewHolder;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("InviteHolder===");
      ((StringBuilder)localObject1).append(this.inviteMessages.size());
      LOGUtils.LOGE(((StringBuilder)localObject1).toString());
      if ((PreferenceManager.getInstance().getRegisterPhone() != null) && (!PreferenceManager.getInstance().getRegisterPhone().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS")))
      {
        paramViewHolder.itemView.setVisibility(0);
        paramViewHolder.recycle_friend.setVisibility(0);
        if ((this.inviteMessages.size() == 2) && (this.isSelectedBoomoji) && (this.isContactsBoomoji)) {
          paramViewHolder.text_invite_empty.setVisibility(0);
        } else {
          paramViewHolder.text_invite_empty.setVisibility(8);
        }
        if (this.newFriendType == 0)
        {
          paramViewHolder.rel_friend_fail.setVisibility(8);
          paramViewHolder.text_friend_empty.setVisibility(8);
          loadingFriend(paramViewHolder.image_loading_friend, BooMojiApplication.getAppContext());
          paramViewHolder.recycle_friend.setVisibility(8);
        }
        else if (this.newFriendType == 1)
        {
          paramViewHolder.rel_friend_fail.setVisibility(8);
          paramViewHolder.text_friend_empty.setVisibility(8);
          paramViewHolder.image_loading_friend.setVisibility(8);
          if (this.inviteItems.size() > 0) {
            paramViewHolder.recycle_friend.setVisibility(0);
          }
        }
        else if (this.newFriendType == 2)
        {
          if (this.inviteItems.size() == 0) {
            paramViewHolder.rel_friend_fail.setVisibility(0);
          } else {
            paramViewHolder.rel_friend_fail.setVisibility(8);
          }
          paramViewHolder.text_friend_empty.setVisibility(8);
          paramViewHolder.image_loading_friend.setVisibility(8);
          paramViewHolder.recycle_friend.setVisibility(8);
        }
        else if (this.newFriendType == 3)
        {
          if (this.inviteItems.size() == 0) {
            paramViewHolder.text_friend_empty.setVisibility(0);
          }
          paramViewHolder.rel_friend_fail.setVisibility(8);
          paramViewHolder.image_loading_friend.setVisibility(8);
          paramViewHolder.recycle_friend.setVisibility(8);
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("newInviteType===");
        ((StringBuilder)localObject1).append(this.newInviteType);
        LOGUtils.LOGE(((StringBuilder)localObject1).toString());
        if (this.newInviteType == 0)
        {
          paramViewHolder.rel_invite_fail.setVisibility(8);
          paramViewHolder.text_invite_empty.setVisibility(8);
          if (this.inviteMessages.size() <= 2) {
            loadingInvite(paramViewHolder.image_loading_invite, BooMojiApplication.getAppContext());
          } else {
            paramViewHolder.rel_invite_fail.setVisibility(8);
          }
        }
        else if (this.newInviteType == 1)
        {
          paramViewHolder.rel_invite_fail.setVisibility(8);
          paramViewHolder.text_invite_empty.setVisibility(8);
          paramViewHolder.image_loading_invite.setVisibility(8);
        }
        else if (this.newInviteType == 2)
        {
          if (this.inviteMessages.size() <= 2)
          {
            paramViewHolder.rel_invite_fail.setVisibility(0);
          }
          else
          {
            paramViewHolder.rel_invite_fail.setVisibility(8);
            paramViewHolder.text_invite_empty.setVisibility(8);
          }
          paramViewHolder.text_invite_empty.setVisibility(8);
          paramViewHolder.image_loading_invite.setVisibility(8);
        }
        else if (this.newInviteType == 3)
        {
          paramViewHolder.rel_invite_fail.setVisibility(8);
          if (this.inviteMessages.size() <= 2) {
            paramViewHolder.text_invite_empty.setVisibility(0);
          }
          paramViewHolder.image_loading_invite.setVisibility(8);
        }
        if ((this.inviteItems != null) && (this.inviteItems.size() > 0))
        {
          paramViewHolder.recycle_friend.setVisibility(0);
          this.mMultiTypeAdapterInvite.setItems(this.inviteItems);
          localObject1 = new GridLayoutManager(this.context, 4);
          ((GridLayoutManager)localObject1).setOrientation(1);
          paramViewHolder.recycle_friend.setLayoutManager((RecyclerView.LayoutManager)localObject1);
          paramViewHolder.recycle_friend.setHasFixedSize(true);
          paramViewHolder.recycle_friend.setNestedScrollingEnabled(false);
          paramViewHolder.recycle_friend.setAdapter(this.mMultiTypeAdapterInvite);
          paramViewHolder.text_friend_empty.setVisibility(8);
        }
        paramViewHolder.rel_ivite.setVisibility(0);
      }
      else
      {
        paramViewHolder.itemView.setVisibility(8);
        paramViewHolder.recycle_friend.setVisibility(8);
      }
      paramViewHolder.image_invite_friend.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          EventBus.getDefault().post(new InviteImageEvent());
        }
      });
      paramViewHolder.rel_friend_fail.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          EventBus.getDefault().post(new NewInviteRetryEvent());
        }
      });
      paramViewHolder.rel_invite_fail.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          EventBus.getDefault().post(new NewFriendRetryEvent());
        }
      });
      return;
    }
    if ((paramViewHolder instanceof ContentHolder))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("InviteHolder==ContentHolder=");
      ((StringBuilder)localObject1).append(this.inviteMessages.size());
      LOGUtils.LOGE(((StringBuilder)localObject1).toString());
      int i = this.context.getResources().getDisplayMetrics().widthPixels / 4 - 65;
      ContentHolder localContentHolder = (ContentHolder)paramViewHolder;
      if ((this.inviteMessages.size() == 2) && (this.isSelectedBoomoji)) {
        localContentHolder.itemView.setVisibility(8);
      } else {
        localContentHolder.itemView.setVisibility(0);
      }
      if ((PreferenceManager.getInstance().getRegisterPhone() != null) && (!PreferenceManager.getInstance().getRegisterPhone().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS"))) {
        localContentHolder.itemView.setVisibility(0);
      } else {
        localContentHolder.itemView.setVisibility(8);
      }
      if ((PreferenceManager.getInstance().getRegisterPhone() != null) && (!PreferenceManager.getInstance().getRegisterPhone().equals("")) && (PermissionBaseUtil.getInstance().hasPermission("android.permission.READ_CONTACTS"))) {
        if (paramInt == this.inviteMessages.size() - 1) {
          localContentHolder.view_contacts.setVisibility(0);
        } else {
          localContentHolder.view_contacts.setVisibility(8);
        }
      }
      paramViewHolder = localContentHolder.headIcon.getLayoutParams();
      paramViewHolder.width = i;
      paramViewHolder.height = i;
      localContentHolder.headIcon.setLayoutParams(paramViewHolder);
      final InviteMessage localInviteMessage = (InviteMessage)this.inviteMessages.get(paramInt);
      localObject1 = "";
      String str = localInviteMessage.getAvatar();
      paramViewHolder = localInviteMessage.getNickname();
      localObject2 = localInviteMessage.getUsername();
      if ((paramViewHolder == null) || (paramViewHolder.equals("")))
      {
        paramViewHolder = (RecyclerView.ViewHolder)localObject1;
        if (localObject2 != null)
        {
          paramViewHolder = (RecyclerView.ViewHolder)localObject1;
          if (!((String)localObject2).equals("")) {
            paramViewHolder = (RecyclerView.ViewHolder)localObject2;
          }
        }
      }
      localContentHolder.name.setText(paramViewHolder);
      localContentHolder.headIcon.setVisibility(0);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("friendAdapter=inviteItems=  ");
      ((StringBuilder)localObject1).append(paramViewHolder);
      ((StringBuilder)localObject1).append("avatar==  ");
      ((StringBuilder)localObject1).append(str);
      LOGUtils.LOGE(((StringBuilder)localObject1).toString());
      Glide.with(localContentHolder.headIcon.getContext()).load(Integer.valueOf(2131230840)).priority(Priority.HIGH).placeholder(2131230840).error(2131230840).into(localContentHolder.headIcon);
      localContentHolder.itemView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (System.currentTimeMillis() - PageJumpUtil.mLastClickTime < PageJumpUtil.CLICK_TIME) {
            return;
          }
          PageJumpUtil.mLastClickTime = System.currentTimeMillis();
          EventBus.getDefault().post(new SmSEvent(localInviteMessage));
        }
      });
    }
  }
  
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams;
    if (paramInt == 0)
    {
      paramViewGroup = LayoutInflater.from(this.context).inflate(2131427462, paramViewGroup, false);
      localLayoutParams = paramViewGroup.getLayoutParams();
      localLayoutParams.width = this.width;
      paramViewGroup.setLayoutParams(localLayoutParams);
      return new HeadHolder(paramViewGroup);
    }
    if (paramInt == 2)
    {
      paramViewGroup = LayoutInflater.from(this.context).inflate(2131427461, paramViewGroup, false);
      localLayoutParams = paramViewGroup.getLayoutParams();
      localLayoutParams.width = this.width;
      paramViewGroup.setLayoutParams(localLayoutParams);
      return new InviteHolder(paramViewGroup);
    }
    return new ContentHolder(LayoutInflater.from(this.context).inflate(2131427556, paramViewGroup, false));
  }
  
  public void onLocalBoomoji(boolean paramBoolean)
  {
    this.isContactsBoomoji = paramBoolean;
  }
  
  public void onSelectMoji(int paramInt)
  {
    this.newStarType = paramInt;
    notifyItemChanged(0);
  }
  
  public void setAnonyFriend(int paramInt)
  {
    this.newAnonyType = paramInt;
    notifyItemChanged(1);
  }
  
  public void setAnonymnousList(Items paramItems)
  {
    this.anonyItems = paramItems;
    notifyItemChanged(0);
    paramItems = new StringBuilder();
    paramItems.append("items=InviteHolder=inviteItems=");
    paramItems.append(this.anonyItems.size());
    LOGUtils.LOGE(paramItems.toString());
  }
  
  public void setInviteList(Items paramItems)
  {
    this.inviteItems = paramItems;
    paramItems = new StringBuilder();
    paramItems.append("items=InviteHolder=inviteItems=");
    paramItems.append(this.inviteItems.size());
    LOGUtils.LOGE(paramItems.toString());
  }
  
  public void setNewFriend(int paramInt)
  {
    this.newFriendType = paramInt;
    notifyItemChanged(1);
  }
  
  public void setNewInvite(int paramInt)
  {
    this.newInviteType = paramInt;
    notifyItemChanged(1);
  }
  
  public void setNoContacts(int paramInt)
  {
    this.noContacts = paramInt;
    notifyDataSetChanged();
  }
  
  public void setRemoveContacts()
  {
    this.inviteMessages.clear();
    this.inviteMessages.add(new InviteMessage());
    this.inviteMessages.add(new InviteMessage());
    notifyDataSetChanged();
  }
  
  public class ContentHolder
    extends RecyclerView.ViewHolder
  {
    @BindView(2131296525)
    ImageView headIcon;
    @BindView(2131296527)
    TextView name;
    @BindView(2131297071)
    View view_contacts;
    
    public ContentHolder(View paramView)
    {
      super();
      ButterKnife.bind(this, paramView);
    }
  }
  
  public class HeadHolder
    extends RecyclerView.ViewHolder
  {
    @BindView(2131296435)
    RelativeLayout contactNo;
    @BindView(2131296957)
    TextView friendSetting;
    @BindView(2131296551)
    ImageView imGG;
    @BindView(2131296550)
    SimpleDraweeView imageFriendBoomoji;
    @BindView(2131296555)
    SimpleDraweeView image_loading_anony;
    @BindView(2131296556)
    SimpleDraweeView image_loading_contacts;
    @BindView(2131296567)
    ConstraintLayout image_love_letter;
    @BindView(2131296569)
    ImageView image_my_crashe;
    @BindView(2131296573)
    ImageView image_view_friend;
    @BindView(2131296603)
    TextView iv_friend_new;
    @BindView(2131296773)
    RecyclerView recycle_view_love;
    @BindView(2131296779)
    RelativeLayout rel_anony_fail;
    @BindView(2131296782)
    RelativeLayout rel_fail;
    @BindView(2131297043)
    TextView seeAll;
    @BindView(2131296951)
    TextView text_boomoji_star;
    @BindView(2131297070)
    View view_anony_contacts;
    @BindView(2131296852)
    RecyclerView whRecyclerView;
    
    public HeadHolder(View paramView)
    {
      super();
      ButterKnife.bind(this, paramView);
    }
  }
  
  public class InviteHolder
    extends RecyclerView.ViewHolder
  {
    @BindView(2131296554)
    ImageView image_invite_friend;
    @BindView(2131296557)
    SimpleDraweeView image_loading_friend;
    @BindView(2131296558)
    SimpleDraweeView image_loading_invite;
    @BindView(2131296768)
    RecyclerView recycle_friend;
    @BindView(2131296784)
    RelativeLayout rel_friend_fail;
    @BindView(2131296785)
    RelativeLayout rel_invite_fail;
    @BindView(2131296786)
    RelativeLayout rel_ivite;
    @BindView(2131296955)
    TextView text_friend_empty;
    @BindView(2131296959)
    TextView text_invite_empty;
    
    public InviteHolder(View paramView)
    {
      super();
      ButterKnife.bind(this, paramView);
    }
  }
}
