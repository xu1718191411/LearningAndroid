package com.example.syoui.imagetab.foundation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.syoui.imagetab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListenerFragment extends android.app.Fragment {

     public interface interActivationBetweenActivityAndFragment{
        void changeListItem(int i,String str);
    }

    public void setEditText(int i) {
        this.editTextNum = i;
    }

    private int editTextNum = 0;

    public void setmInterActivationBetweenActivityAndFragment(interActivationBetweenActivityAndFragment mInterActivationBetweenActivityAndFragment) {
        this.mInterActivationBetweenActivityAndFragment = mInterActivationBetweenActivityAndFragment;
    }

    private interActivationBetweenActivityAndFragment mInterActivationBetweenActivityAndFragment = null;

    public FragmentListenerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_listener, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.editContent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = ((EditText)view.findViewById(R.id.editContent)).getText().toString();
                if(mInterActivationBetweenActivityAndFragment!= null){
                    mInterActivationBetweenActivityAndFragment.changeListItem(editTextNum,content);
                }
            }

        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
