package create.by.gank.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import create.by.gank.R;
import create.by.gank.bean.GirlBean;

public class GirlDetailActivity extends AppCompatActivity {

    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.like)
    TextView like;
    @BindView(R.id.views)
    TextView views;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.publish_at)
    TextView publishAt;
    @BindView(R.id.text_content)
    ConstraintLayout textContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_girl_detail);
        ButterKnife.bind(this);
        GirlBean.DataBean dataBean = (GirlBean.DataBean) getIntent().getSerializableExtra("GirlBean.DataBean");
        assert dataBean != null;
        init(dataBean);
        Glide.with(this).load(dataBean.getImages().get(0)).into(photoView);
    }

    @SuppressLint("SetTextI18n")
    private void init(GirlBean.DataBean dataBean) {
        String replaceStr = dataBean.getDesc().replace("\n", "");
        desc.setText(replaceStr);
        author.setText("作者：" + dataBean.getAuthor());
        like.setText(String.valueOf(dataBean.getLikeCounts()));
        views.setText(String.valueOf(dataBean.getViews()));
        comment.setText(String.valueOf(dataBean.getStars()));
        String substring = dataBean.getPublishedAt().substring(0, "1111-11-11".length());
        publishAt.setText(substring);

        //隐藏文字动画
        AlphaAnimation hideAnimation = new AlphaAnimation(1f, 0f);
        hideAnimation.setDuration(500);

        //展示文字动画
        AlphaAnimation showAnimation = new AlphaAnimation(0f, 1f);
        showAnimation.setDuration(500);

        photoView.setOnClickListener(v -> {
            if (textContent.getVisibility() == View.VISIBLE) {
                textContent.startAnimation(hideAnimation);
                textContent.setVisibility(View.INVISIBLE);
            } else {
                textContent.startAnimation(showAnimation);
                textContent.setVisibility(View.VISIBLE);
            }
        });
    }

    public static void start(Context context, GirlBean.DataBean dataBean) {
        Intent starter = new Intent(context, GirlDetailActivity.class);
        starter.putExtra("GirlBean.DataBean", dataBean);
        context.startActivity(starter);
    }
}
