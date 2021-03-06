package create.by.mylauncher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *  用于显示app图标的适配器
 */
public class LauncherAdapter extends BaseAdapter {

    private List<ResolveInfo> mApps = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

    public LauncherAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mApps.size();
    }

    @Override
    public Object getItem(int position) {
        return mApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_app_item, parent, false);
            viewHolder.mAppIcon = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.mAppTitle = (TextView) convertView.findViewById(R.id.app_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ResolveInfo appInfo = mApps.get(position);
        viewHolder.mAppIcon.setImageDrawable(appInfo.loadIcon(mContext.getPackageManager()));
        viewHolder.mAppTitle.setText(appInfo.loadLabel(mContext.getPackageManager()));

        convertView.setOnClickListener((v) -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(appInfo);
            }
        });

        return convertView;
    }


    static class ViewHolder {
        public ImageView mAppIcon = null;
        public TextView mAppTitle = null;
    }

    public void setAppData(List<ResolveInfo> apps) {
        if (apps != null) {
            this.mApps = apps;
        }
    }


    public interface OnItemClickListener {
        void onItemClick(ResolveInfo appInfo);
    }

    public void setOnItemClickListener(LauncherAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
