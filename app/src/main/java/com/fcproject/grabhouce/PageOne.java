package com.fcproject.grabhouce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageOne extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<String> cNames = new ArrayList<>();
    ArrayList<String>imgUrls = new ArrayList<>();
    EditText etCname;


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
        recyclerView=view.findViewById(R.id.homeRecycler);
        etCname=view.findViewById(R.id.etCname);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getContext(),cNames,imgUrls);
        recyclerView.setAdapter(recyclerViewAdapter);

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
        imgUrlFromInternet();
        etCname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                 String cname= String.valueOf(etCname.getText());
                    Toast.makeText(getContext(), cname, Toast.LENGTH_SHORT).show();
                  Constants.C_NAME= String.valueOf(cname);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private void imgUrlFromInternet()
    {
        imgUrls.add("https://www.tourmyindia.com/states/maharashtra/images/gateway-of-india-mumbai1.jpg");
        cNames.add("Mumbai");

        imgUrls.add("http://3.bp.blogspot.com/-7YiTuTMAwwE/UGw4bDIOb4I/AAAAAAAAADI/T8R78H50rgM/s1600/India-Gate-New-Delhi.jpg");
        cNames.add("Delhi");

        imgUrls.add("https://media-cdn.tripadvisor.com/media/photo-s/03/9b/2f/3a/bangalore.jpg");
        cNames.add("Banglore");

        imgUrls.add("http://www.exploretelangana.com/wp-content/uploads/2013/08/History-of-Hyderabad-charminar-exploretelangana.jpg");
        cNames.add("Hyderabad");

        imgUrls.add("http://www.virtualoffices360.com/wp-content/uploads/2015/02/1-Pune-VirtualOffices360.jpg");
        cNames.add("Pune");

        imgUrls.add("https://s3.amazonaws.com/worldpackersproduction/volunteer_positions/photos/000/011/778/original/e0079216089f03035a0d23224d16bb87.jpg");
        cNames.add("Chennai");

        imgUrls.add("http://images.indianexpress.com/2018/02/howrah-bridge-759-pixabay.jpg");
        cNames.add("kolkata");

        imgUrls.add("http://cdn77.orangesmile.com/common/img_cities_original/new-delhi--2106102-13.jpg");
        cNames.add("Agra");

        imgUrls.add("https://4.bp.blogspot.com/-8YyN3TmuO2U/VsQgZtZHfYI/AAAAAAAABg8/LuazvSfhuOw/s1600/The%2BGolden%2BTemple%2BAmritsar%2BPicture%2B3.JPG");
        cNames.add("Amritsar");

        imgUrls.add("https://i.ytimg.com/vi/JyYuJBJS64s/maxresdefault.jpg");
        cNames.add("Goa");

    }

}
