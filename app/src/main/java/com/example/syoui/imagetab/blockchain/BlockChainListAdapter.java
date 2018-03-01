package com.example.syoui.imagetab.blockchain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;

/**
 * Created by syoui on 2018/02/08.
 */

public class BlockChainListAdapter extends BaseAdapter{

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Block> mArrayList = null;

    interface OnChangeBlockListener{
        void onBlockChanged(int index);
    }

    private OnChangeBlockListener mOnChangeBlockListener;

    public void setmOnChangeBlockListener(OnChangeBlockListener mOnChangeBlockListener) {
        this.mOnChangeBlockListener = mOnChangeBlockListener;
    }

    public ArrayList<Block> getmArrayList() {
        return mArrayList;
    }

    public void setmArrayList(ArrayList<Block> mArrayList) {
        this.mArrayList = mArrayList;
    }

    public BlockChainListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override

    public int getCount() {
        if(mArrayList == null) return 0;
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        if(mArrayList == null) return null;
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_block_chain_list,parent,false);

        Block block = mArrayList.get(position);
        BlockTransaction bt = block.getTransaction();
        ((TextView) convertView.findViewById(R.id.from)).setText(bt.getFrom());
        ((TextView) convertView.findViewById(R.id.to)).setText(bt.getTo());

        ((TextView) convertView.findViewById(R.id.hashCode)).setText(block.getBlockHash());

        TextView textViewValue = ((TextView) convertView.findViewById(R.id.coinsValue));
        textViewValue.setText(bt.getAmount()+"coins");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(mOnChangeBlockListener != null){
                        mOnChangeBlockListener.onBlockChanged(position);
                    }
            }
        });


        return convertView;
    }
}
