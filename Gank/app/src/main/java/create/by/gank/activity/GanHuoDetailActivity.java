package create.by.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.HttpURLConnection;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;
import butterknife.BindView;
import butterknife.ButterKnife;
import create.by.gank.R;
import create.by.gank.bean.ArticleBean;
import create.by.gank.network.Api;
import create.by.gank.network.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GanHuoDetailActivity extends AppCompatActivity {

    @BindView(R.id.mdv)
    MarkdownView mdv;
    //    @BindView(R.id.ganhuo)
//    TextView ganhuoTv;
    private String mArticleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gan_huo_detail);
        ButterKnife.bind(this);
        mArticleId = (String) getIntent().getSerializableExtra("_id");
        //RichText.initCacheDir(this);
        mdv.addStyleSheet(new Github());
        loadGanHuoData();
    }

    private void loadGanHuoData() {
        Api build = RetrofitBuilder.build();
        build.getGanHuoArticle(mArticleId).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        showDetail(response.body().getData());
                    } else {
                        Toast.makeText(GanHuoDetailActivity.this, "加载数据为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GanHuoDetailActivity.this, "加载出错啦", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                Toast.makeText(GanHuoDetailActivity.this, "加载出错啦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDetail(ArticleBean.DataBean bean) {
        if (bean != null) {
            mdv.loadMarkdown(bean.getMarkdown());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void start(Context context, String _id) {
        Intent starter = new Intent(context, GanHuoDetailActivity.class);
        starter.putExtra("_id", _id);
        context.startActivity(starter);
    }
}