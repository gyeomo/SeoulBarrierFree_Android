package seoulmobileplat.org.seoulbarrierfree;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class HelpPage extends AppCompatActivity {
    ViewPager pager;
    android.support.v7.app.ActionBar abar;
    int FirstFlag;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        FirstFlag = getIntent().getExtras().getInt("First");
        toast=  Toast.makeText(getBaseContext(), "마지막 페이지입니다. 뒤로가기를 눌러주세요.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);

        abar = getSupportActionBar();
        abar.setTitle("");
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|
                ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);

        pager= (ViewPager)findViewById(R.id.pager);
       // pager.setOffscreenPageLimit(1);
        ViewPagerAdapter adapter= new ViewPagerAdapter(getLayoutInflater());
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 10){
                    toast.show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onDestroy() { // 종료시 실행
        super.onDestroy();
        if(FirstFlag == 1){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
         //   intent.putExtra("First",1);
            startActivity(intent);
        }
       // finish();
    }
}

