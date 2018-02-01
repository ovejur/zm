package zhujj.zm.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import zhujj.zm.R;

/**
 * 作者：朱建晶 on 2018/1/24 17:12
 * 邮箱：344951059@qq.com
 */

public class LoginGridviewAdapter extends BaseAdapter {

    private Context context;

    public LoginGridviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_login_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.btnNumKey = (TextView) view.findViewById(R.id.btn_keys);
            viewHolder.btnDelete = (ImageView) view.findViewById(R.id.img_password_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == 0) {
            viewHolder.btnNumKey.setText("注册");
        } else if (i == 1) {
            viewHolder.btnNumKey.setText("0");
        } else if (i == 2) {
            viewHolder.btnDelete.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnNumKey.setText("" + (i - 2));
        }
        return view;
    }

    public final class ViewHolder {
        public TextView btnNumKey;
        public ImageView btnDelete;
    }
}
