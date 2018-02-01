package zhujj.zm.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import zhujj.zm.db.bean.Book;

/**
 * 作者：朱建晶 on 2018/1/29 16:48
 * 邮箱：344951059@qq.com
 */

public class WirteBookListAdapter extends BaseAdapter {

    private List<Book> books = new ArrayList<Book>();

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        return view;
    }
}
