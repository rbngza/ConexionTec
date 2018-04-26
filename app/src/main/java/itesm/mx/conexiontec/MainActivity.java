package itesm.mx.conexiontec;

import android.app.Fragment;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout indicator;

    List<Integer> color;
    List<String> colorName;

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        indicator=(TabLayout)findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.WHITE);
        color.add(Color.WHITE);
        color.add(Color.WHITE);

        colorName = new ArrayList<>();
        colorName.add("RED");
        colorName.add("GREEN");
        colorName.add("BLUE");

        viewPager.setAdapter(new SliderAdapter(this, color, colorName));
        indicator.setupWithViewPager(viewPager, true);

        fragment = new MenuFragment();
        loadFragment(fragment);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);


    }

    private void loadFragment(Fragment fragment) {
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < color.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
