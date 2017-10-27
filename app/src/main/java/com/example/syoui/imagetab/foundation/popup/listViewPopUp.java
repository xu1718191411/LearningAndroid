package com.example.syoui.imagetab.foundation.popup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syoui.imagetab.IconListItem;
import com.example.syoui.imagetab.R;

import java.util.ArrayList;
import java.util.List;

public class listViewPopUp extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private Button confirmButton;

    private ArrayList<IconListItem> listItems = new ArrayList<>();
    private ListView listView;

    private AsyncTask<Long, Void, Void> mTask;
    private _IconListAdapter mAdapter;
    private int position = 0;
    private  Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_pop_up);


        button = (Button) findViewById(R.id.buttonShowCustomDialog);

        // リストビューに表示する要素を設定
        for (int i = 0; i < 8; i++) {
            IconListItem item = new IconListItem("icon"+i,"sample text No. " + String.valueOf(i));
            listItems.add(item);
        }

        dialog = new Dialog(context);


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // custom dialog

                dialog.setContentView(R.layout.customize_list_view_layout);
                dialog.setTitle("list view pop up...");


                // レイアウトからリストビューを取得
                final ListView listView = (ListView) dialog.findViewById(R.id.popUpListView);


                // 出力結果をリストビューに表示
                mAdapter = new _IconListAdapter(context, R.layout.sampleiconlist_item, listItems);
                listView.setAdapter(mAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int _position, long id) {
                                            position = _position;

                        for (int i = 0; i < mAdapter.getCount(); i++) {
                            View item = listView.getChildAt(i);
                            if (item != null) {
                                item.setBackgroundColor(Color.WHITE);
                            }
                        }
                        view.setBackgroundColor(Color.GRAY);

                    }
                });



                confirmButton = (Button) dialog.findViewById(R.id.confirmButton);
                confirmButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        ImageView img = (ImageView) findViewById(R.id.changeImage);
                        String imgName = "_icon"+position;
                        img.setImageResource(context.getResources().getIdentifier(imgName,"drawable",  context.getPackageName()));
                    }
                });


                dialog.show();

            }


        });





    }


}



class _IconListAdapter  extends ArrayAdapter<IconListItem> {

    private int mResource;
    private List<IconListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public _IconListAdapter(Context context, int resource, List<IconListItem> items) {
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










