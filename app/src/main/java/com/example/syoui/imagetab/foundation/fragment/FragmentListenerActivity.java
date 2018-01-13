package com.example.syoui.imagetab.foundation.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;

public class FragmentListenerActivity extends AppCompatActivity {


    private ArrayList<String> mList = null;
    private FragmentListenerFragment mFragment = null;
    private Context mContext = null;
    private ListView mListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_listener);
        mList = new ArrayList<String>();
        mList.add("nana");
        mList.add("yuri");
        mList.add("liaoliao");
        mList.add("tsutsu");
        mList.add("techi");
        mList.add("taiki");
        mList.add("yuya");
        mList.add("mayu");
        mList.add("gemo");
        mList.add("ruby");

        mListView = (ListView) findViewById(R.id.mainList);

        ArrayAdapter<String> mListAdapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(mListAdapter);

        mContext = this.getApplicationContext();

        mFragment = new FragmentListenerFragment();

        mFragment.setmInterActivationBetweenActivityAndFragment(new FragmentListenerFragment.interActivationBetweenActivityAndFragment() {
            @Override
            public void changeListItem(int i, String str) {
                mList.set(i,str);
                ArrayAdapter<String> mListAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,mList);
                mListView.setAdapter(mListAdapter);
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mFragment == null) return;
                mFragment.setEditText(i);
                showFragment(mFragment);
            }
        });



    }


    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}

