package zhujj.zm.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhujj.zm.R;
import zhujj.zm.db.bean.Diary;
import zhujj.zm.util.TimeUtils;

/**
 * 作者：朱建晶 on 2018/3/5 22:52
 * 邮箱：344951059@qq.com
 */

public class DiaryListAdapter extends BaseAdapter {

    List<Diary> diarys = new ArrayList<>();
    private Context context;

    public DiaryListAdapter (Context context) {
        this.context = context;
    }
    public void reflushAdapter(List<Diary> diarys) {
        this.diarys = diarys;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return diarys.size();
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
            view = View.inflate(context, R.layout.item_diary_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.diaryDate = (TextView) view.findViewById(R.id.diary_date);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.diaryDate.setText(TimeUtils.timedateYM(diarys.get(i).getTime()));
        return view;
    }

    public final class ViewHolder {
        public TextView diaryDate;
    }
}
