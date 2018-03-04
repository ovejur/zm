package zhujj.zm.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhujj.zm.R;
import zhujj.zm.db.bean.Chapter;
import zhujj.zm.util.TimeUtils;

/**
 * 作者：朱建晶 on 2018/2/7 15:49
 * 邮箱：344951059@qq.com
 */

public class ChapterListAdapter extends BaseAdapter {

    List<Chapter> chapters = new ArrayList<Chapter>();
    private Context context;

    public ChapterListAdapter(Context context) {
        this.context = context;
    }

    public void reflushAdapter(List<Chapter> chapters) {
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chapters.size();
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
            view = View.inflate(context, R.layout.item_chapter_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.chapter_name = (TextView) view.findViewById(R.id.chapter_name);
            viewHolder.chapter_update_time = (TextView) view.findViewById(R.id.chapter_update_time);
            viewHolder.chapter_count = (TextView) view.findViewById(R.id.chapter_count);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.chapter_name.setText(chapters.get(i).getName());
        viewHolder.chapter_update_time.setText("更新时间:"+ TimeUtils.timedateYMD(chapters.get(i).getUpdateTime()));
        viewHolder.chapter_count.setText("字数:"+chapters.get(i).getCount());
        return view;
    }

    public final class ViewHolder {
        public TextView chapter_name, chapter_update_time, chapter_count;    }
}
