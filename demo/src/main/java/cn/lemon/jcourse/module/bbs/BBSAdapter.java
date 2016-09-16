package cn.lemon.jcourse.module.bbs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.lemon.jcourse.R;
import cn.lemon.jcourse.config.Config;
import cn.lemon.jcourse.model.bean.BBS;
import cn.lemon.jcourse.model.net.GlideCircleTransform;
import cn.lemon.multi.MultiView;
import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;


/**
 * Created by linlongxin on 2016/9/14.
 */

public class BBSAdapter extends RecyclerAdapter<BBS> {

    private Context mContext;

    public BBSAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder<BBS> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new BBSViewHolder(parent);
    }


    class BBSViewHolder extends BaseViewHolder<BBS> implements View.OnClickListener{

        private ImageView mAvatar;
        private TextView mName;
        private TextView mSign;
        private TextView mTitle;
        private TextView mContent;
        private MultiView mMultiView;

        public BBSViewHolder(ViewGroup parent) {
            super(parent, R.layout.bbs_holder_list);
        }

        @Override
        public void onInitializeView() {
            mAvatar = findViewById(R.id.avatar);
            mName = findViewById(R.id.name);
            mSign = findViewById(R.id.sign);
            mTitle = findViewById(R.id.title);
            mContent = findViewById(R.id.content);
            mMultiView = findViewById(R.id.multi_view);
        }

        @Override
        public void setData(BBS bbs) {
            super.setData(bbs);
            Glide.with(mContext)
                    .load(bbs.avatar)
                    .transform(new GlideCircleTransform(mContext))
                    .into(mAvatar);
            mName.setText(bbs.name);
            mSign.setText(bbs.sign);
            mTitle.setText(bbs.title);
            mContent.setText(bbs.content);
            if (bbs.pictures.length() > 0) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<String>>() {}.getType();
                List<String> pics = gson.fromJson(bbs.pictures, listType);
                mMultiView.removeAllViews();
                mMultiView.setImages(pics);
            }

            mName.setOnClickListener(this);
            mSign.setOnClickListener(this);
            mAvatar.setOnClickListener(this);
        }

        @Override
        public void onItemViewClick(BBS object) {
            Intent intent = new Intent(mContext,BBSDetailActivity.class);
            intent.putExtra(Config.BBS_DETAIL_ID,object.id);
            mContext.startActivity(intent);
        }

        @Override
        public void onClick(View v) {

        }
    }
}