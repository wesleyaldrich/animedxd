package com.wesleyaldrich.animedxd;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;

public class NewsFragment extends Fragment {

    private ViewPager2 carouselViewPager;
    private CarouselAdapter carouselAdapter;
    private Handler sliderHandler = new Handler();
    private List<Integer> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        carouselViewPager = view.findViewById(R.id.carouselViewPager);

        imageList = Arrays.asList(
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5
        );

        carouselAdapter = new CarouselAdapter(imageList);
        carouselViewPager.setAdapter(carouselAdapter);

        int initialPosition = (carouselAdapter.getItemCount() / 2) - (carouselAdapter.getItemCount() / 2 % imageList.size());
        carouselViewPager.setCurrentItem(initialPosition, false);

        carouselViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000);
            }
        });



        return view;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (carouselViewPager != null && carouselAdapter != null) {
                int currentItem = carouselViewPager.getCurrentItem();
                int nextItem = currentItem + 1;
                if (nextItem >= carouselAdapter.getItemCount()) {
                    nextItem = 0;
                }
                carouselViewPager.setCurrentItem(nextItem, true);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 5000);
    }
}