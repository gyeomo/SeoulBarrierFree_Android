package seoulmobileplat.org.seoulbarrierfree;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CallPage extends AppCompatActivity implements View.OnClickListener {
    Button emergencyButton;
    Button counsellingButton;
    Button e1,e2,e3,e4,e5,e6;
    Button c1,c2,c3,c4,c5,c6,c7,c8;
    LinearLayout emergencyView;
    LinearLayout counsellingView;
    android.support.v7.app.ActionBar abar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        abar = getSupportActionBar();
        abar.setTitle("");
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|
                ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);

        emergencyButton = (Button)findViewById(R.id.emergencyButton);
        counsellingButton = (Button)findViewById(R.id.counsellingButton);
        emergencyView = (LinearLayout)findViewById(R.id.emergencyView);
        counsellingView = (LinearLayout)findViewById(R.id.counsellingView);
        e1 = (Button)findViewById(R.id.e1);e2 = (Button)findViewById(R.id.e2);
        e3 = (Button)findViewById(R.id.e3);e4 = (Button)findViewById(R.id.e4);
        e5 = (Button)findViewById(R.id.e5);e6 = (Button)findViewById(R.id.e6);
        c1 = (Button)findViewById(R.id.c1);c2 = (Button)findViewById(R.id.c2);
        c3 = (Button)findViewById(R.id.c3);c4 = (Button)findViewById(R.id.c4);
        c5 = (Button)findViewById(R.id.c5);c6 = (Button)findViewById(R.id.c6);
        c7 = (Button)findViewById(R.id.c7);c8 = (Button)findViewById(R.id.c8);
        emergencyButton.setOnClickListener(this);
        counsellingButton.setOnClickListener(this);
        e1.setOnClickListener(this);e2.setOnClickListener(this);e3.setOnClickListener(this);
        e4.setOnClickListener(this);e5.setOnClickListener(this);e6.setOnClickListener(this);
        c1.setOnClickListener(this);c2.setOnClickListener(this);c3.setOnClickListener(this);
        c4.setOnClickListener(this);c5.setOnClickListener(this);c6.setOnClickListener(this);
        c7.setOnClickListener(this);c8.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.emergencyButton:
                emergencyButton.setBackgroundColor(0xbbfa5555);
                counsellingButton.setBackgroundColor(0xffffffff);

                emergencyView.setVisibility(View.VISIBLE);
                counsellingView.setVisibility(View.INVISIBLE);
                break;
            case R.id.counsellingButton:
                emergencyButton.setBackgroundColor(0xffffffff);
                counsellingButton.setBackgroundColor(0xbbfa5555);

                counsellingView.setVisibility(View.VISIBLE);
                emergencyView.setVisibility(View.INVISIBLE);
                break;
            case R.id.e1:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:112")));
                break;
            case R.id.e2:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119")));
                break;
            case R.id.e3:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:122")));
                break;
            case R.id.e4:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1588-7722")));
                break;
            case R.id.e5:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-120")));
                break;
            case R.id.e6:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:110")));
                break;
            case R.id.c1:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1588-0420")));
                break;
            case R.id.c2:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1644-8295")));
                break;
            case R.id.c3:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-3472-3556")));
                break;
            case R.id.c4:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1588-0814")));
                break;
            case R.id.c5:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-3675-4465")));
                break;
            case R.id.c6:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:182")));
                break;
            case R.id.c7:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1366")));
                break;
            case R.id.c8:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1577-1389")));
                break;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }
}
