package zhujj.zm.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
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
        this.notifyDataSetChanged();
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_book_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.book_name = (TextView) view.findViewById(R.id.btn_keys);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.book_name.setText(books.get(i).getName());

        return view;
    }

    public final class ViewHolder {
        public TextView book_name;
    }
}
