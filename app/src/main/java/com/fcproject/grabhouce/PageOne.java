package com.fcproject.grabhouce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageOne extends Fragment {

    public static PageOne newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        PageOne pageOne = new PageOne();
        pageOne.setArguments(args);
        return pageOne;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_page_one, container, false);
        final CrystalRangeSeekbar rangeSeekbar = view.findViewById(R.id.rangeSeekbar1);
        final TextView tvMin =  view.findViewById(R.id.tvmin);
        final TextView tvMax = view.findViewById(R.id.tvmax);
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
                Constants.MIN_PLACE_PRICE=Integer.parseInt((String) tvMin.getText());
                Constants.MAX_PLACE_PRICE=Integer.parseInt((String) tvMax.getText());
                
            }
        });

        return view;
    }

}
