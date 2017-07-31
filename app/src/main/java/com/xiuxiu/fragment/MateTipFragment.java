package com.xiuxiu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MainActivity;
import com.R;
import com.xiuxiu.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MateTipFragment extends Fragment {

      public interface  MateTip{

     void tip();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (getActivity() instanceof MateTipFragment.MateTip)
        {

            (( MateTipFragment.MateTip) getActivity()).tip();
        }
        return inflater.inflate(R.layout.fragment_mate_tip, container, false);
    }


}
