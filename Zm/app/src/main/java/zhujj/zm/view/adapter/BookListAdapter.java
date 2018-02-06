package zhujj.zm.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zhujj.zm.R;
import zhujj.zm.db.bean.Book;

/**
 * 作者：朱建晶 on 2018/2/5 16:32
 * 邮箱：344951059@qq.com
 */

public class BookListAdapter extends BaseAdapter {

    List<Book> books = new ArrayList<Book>();
    private Context context;

    public BookListAdapter(Context context) {
        this.context = context;
    }

    public void reflushAdapter(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_book_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.book_name = (TextView) view.findViewById(R.id.book_name);
            viewHolder.book_intro = (TextView) view.findViewById(R.id.book_intro);
            viewHolder.book_status = (TextView) view.findViewById(R.id.book_status);
            viewHolder.book_creat_time = (TextView) view.findViewById(R.id.book_creat_time);
            viewHolder.book_count = (TextView) view.findViewById(R.id.book_count);
            viewHolder.book_chapter_count = (TextView) view.findViewById(R.id.book_chapter_count);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.book_name.setText(books.get(i).getName());
        viewHolder.book_intro.setText(books.get(i).getIntroduce());
        viewHolder.book_status.setText(getTimeDifference(books.get(i).getUpdateTime(), viewHolder.book_status));
        viewHolder.book_count.setText("总字数:"+books.get(i).getCount());
        viewHolder.book_chapter_count.setText("|    共"+books.get(i).getChapters() + "章");
        viewHolder.book_creat_time.setText(timedateYMD(books.get(i).getCreatTime()));
        return view;
    }

    public final class ViewHolder {
        public TextView book_name, book_intro, book_status, book_creat_time, book_count, book_chapter_count;
    }

    private String timedateYMD(String time) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long  l = Long.valueOf(time);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String getTimeDifference(String starTime, TextView textView) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(timedateYMD(starTime));
            Date parse1 = dateFormat.parse(timedateYMD(""+System.currentTimeMillis()));

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            if (min1 < 60 && hour1 == 0) {
                timeString = min1 + "分钟前更新";
            } else if (hour1 < 12) {
                timeString = hour1 + "小时前更新";
                textView.setTextColor(context.getColor(R.color.darkturquoise));
            } else if (hour1 >= 12 && day < 1) {
                timeString = "当天更新";
                textView.setTextColor(context.getColor(R.color.deepskyblue));
            } else if (day < 7) {
                timeString = day + "天前更新";
                textView.setTextColor(context.getColor(R.color.deepskyblue));
            } else if (day == 7) {
                timeString = "一周前更新";
                textView.setTextColor(context.getColor(R.color.dodgerblue));
            } else if (day > 7 && day < 30) {
                timeString = day + "天前更新";
                textView.setTextColor(context.getColor(R.color.dodgerblue));
            } else {
                timeString = "很久以前更新";
                textView.setTextColor(context.getColor(R.color.red));
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }
}
