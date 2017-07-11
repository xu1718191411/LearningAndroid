package com.example.syoui.imagetab.foundation.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.example.syoui.imagetab.IconListAdapter;
import com.example.syoui.imagetab.IconListItem;
import com.example.syoui.imagetab.R;

import java.util.ArrayList;
public class foundation_activity_icon_listView extends AppCompatActivity implements OnScrollListener {
    private ArrayList<IconListItem> listItems = new ArrayList<>();
    private ListView listView;

    private AsyncTask<Long, Void, Void> mTask;
    private IconListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_icon_list_view);


        // レイアウトからリストビューを取得
        listView = (ListView)findViewById(R.id.icon_listview);

        // リストビューに表示する要素を設定

        for (int i = 0; i < 8; i++) {
            IconListItem item = new IconListItem("icon"+i,"sample text No. " + String.valueOf(i));
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        mAdapter = new IconListAdapter(this, R.layout.sampleiconlist_item, listItems);
        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(this);
    }

    void addListLoading(){

        mTask = new AsyncTask<Long, Void, Void>() {


            @Override
            protected Void doInBackground(Long[] params) {
                try {
                    Log.d("DEBUG", "sleep..." + params[0]);
                    Thread.sleep(params[0]);	//処理を重くする仮のウェイト処理
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                freshData();
            }
        }.execute(2000l);

    }


    void freshData(){
        for (int i = 0; i < 8; i++) {
            IconListItem item = new IconListItem("icon"+i,"sample text No. " + String.valueOf(i));
            //listItems.add(item);
            mAdapter.add(item);

        }
        listView.invalidateViews();
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
            //
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        Log.d("CHECK","");
        if (totalItemCount == firstVisibleItem + visibleItemCount && totalItemCount > 0) {	//このタイミングでListを追加します
            addListLoading();	//追加処理実行
        }
    }
}


