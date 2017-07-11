package com.example.syoui.imagetab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by syoui on 2017/07/07.
 */
public class IconListAdapter  extends ArrayAdapter<IconListItem> {

    private int mResource;
    private List<IconListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public IconListAdapter(Context context, int resource, List<IconListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        IconListItem item = mItems.get(position);


        // タイトルを設定
        TextView title = (TextView)view.findViewById(R.id.title);
        title.setText(item.getTitle());

        ImageView im = (ImageView)view.findViewById(R.id.onePieceId);
        im.setImageResource(this.getContext().getResources().getIdentifier(item.getImageName(),"drawable",  this.getContext().getPackageName()));





        return view;
    }


}
