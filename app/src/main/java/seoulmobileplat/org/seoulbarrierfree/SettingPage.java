package seoulmobileplat.org.seoulbarrierfree;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingPage extends AppCompatActivity implements View.OnClickListener{
    Button walkingSettingButton, lightSettingButton, hearingSettingButton, youngSettingButton;
    LinearLayout walkingSettingView, lightSettingView,hearingSettingView,youngSettingView;
    CheckBox sw1,sw3,sw4,sw5,sw6,pw1,pw2,pw3,pw4,pw5,pw6,pw7,pw8,pw9,pw10,pw11,pl1,pl2,pl3,pl4,pl5,pl6
            ,ph1,ph2,ph3,ph4,py1,py2,py3,py4;
    boolean sw1B,sw3B,sw4B,sw5B,sw6B,pw1B,pw2B,pw3B,pw4B,pw5B,pw6B,pw7B,pw8B,pw9B
            ,pw10B,pw11B,pl1B,pl2B,pl3B,pl4B,pl5B,pl6B,ph1B,ph2B,ph3B,ph4B,py1B,py2B,py3B,py4B;
    String dbName = "ListDB.db";
    String sql;
    Cursor cursorS;
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    android.support.v7.app.ActionBar abar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        abar = getSupportActionBar();
        abar.setTitle("");
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|
                ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);

        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        db=dbHelper.getWritableDatabase();

        walkingSettingButton = (Button)findViewById(R.id.walkingSettingButton);
        lightSettingButton = (Button)findViewById(R.id.lightSettingButton);
        hearingSettingButton = (Button)findViewById(R.id.hearingSettingButton);
        youngSettingButton = (Button)findViewById(R.id.youngSettingButton);

        walkingSettingView = (LinearLayout)findViewById(R.id.walkingSettingView);
        lightSettingView = (LinearLayout)findViewById(R.id.lightSettingView);
        hearingSettingView = (LinearLayout)findViewById(R.id.hearingSettingView);
        youngSettingView = (LinearLayout)findViewById(R.id.youngSettingView);

        sw1=(CheckBox)findViewById(R.id.sw1); sw3=(CheckBox)findViewById(R.id.sw3);
        sw4=(CheckBox)findViewById(R.id.sw4);sw5=(CheckBox)findViewById(R.id.sw5);sw6=(CheckBox)findViewById(R.id.sw6);

        pw1=(CheckBox)findViewById(R.id.pw1);pw2=(CheckBox)findViewById(R.id.pw2);pw3=(CheckBox)findViewById(R.id.pw3);
        pw4=(CheckBox)findViewById(R.id.pw4);pw5=(CheckBox)findViewById(R.id.pw5);pw6=(CheckBox)findViewById(R.id.pw6);
        pw7=(CheckBox)findViewById(R.id.pw7);pw8=(CheckBox)findViewById(R.id.pw8);pw9=(CheckBox)findViewById(R.id.pw9);
        pw10=(CheckBox)findViewById(R.id.pw10);pw11=(CheckBox)findViewById(R.id.pw11);

        pl1=(CheckBox)findViewById(R.id.pl1); pl2=(CheckBox)findViewById(R.id.pl2); pl3=(CheckBox)findViewById(R.id.pl3);
        pl4=(CheckBox)findViewById(R.id.pl4); pl5=(CheckBox)findViewById(R.id.pl5); pl6=(CheckBox)findViewById(R.id.pl6);

        ph1=(CheckBox)findViewById(R.id.ph1);ph2=(CheckBox)findViewById(R.id.ph2);ph3=(CheckBox)findViewById(R.id.ph3);
        ph4=(CheckBox)findViewById(R.id.ph4);py1=(CheckBox)findViewById(R.id.py1);py2=(CheckBox)findViewById(R.id.py2);
        py3=(CheckBox)findViewById(R.id.py3);py4=(CheckBox)findViewById(R.id.py4);

        walkingSettingButton.setOnClickListener(this);lightSettingButton.setOnClickListener(this);
        hearingSettingButton.setOnClickListener(this);youngSettingButton.setOnClickListener(this);

        sql = "SELECT * FROM SWalking;";
        cursorS = db.rawQuery(sql,null);
        cursorS.moveToFirst();
        if(cursorS.getInt(1)==0) sw1B=false;    else sw1B=true;
        if(cursorS.getInt(2)==0) sw3B=false;    else sw3B=true;
        if(cursorS.getInt(3)==0) sw4B=false;    else sw4B=true;
        if(cursorS.getInt(4)==0) sw5B=false;    else sw5B=true;
        if(cursorS.getInt(5)==0) sw6B=false;    else sw6B=true;
        sql = "SELECT * FROM PWalking;";
        cursorS = db.rawQuery(sql,null);
        cursorS.moveToFirst();
        if(cursorS.getInt(1)==0) pw1B=false;    else pw1B=true;
        if(cursorS.getInt(2)==0) pw2B=false;    else pw2B=true;
        if(cursorS.getInt(3)==0) pw3B=false;    else pw3B=true;
        if(cursorS.getInt(4)==0) pw4B=false;    else pw4B=true;
        if(cursorS.getInt(5)==0) pw5B=false;    else pw5B=true;
        if(cursorS.getInt(6)==0) pw6B=false;    else pw6B=true;
        if(cursorS.getInt(7)==0) pw7B=false;    else pw7B=true;
        if(cursorS.getInt(8)==0) pw8B=false;    else pw8B=true;
        if(cursorS.getInt(9)==0) pw9B=false;    else pw9B=true;
        if(cursorS.getInt(10)==0) pw10B=false;    else pw10B=true;
        if(cursorS.getInt(11)==0) pw11B=false;    else pw11B=true;
        sql = "SELECT * FROM Light;";
        cursorS = db.rawQuery(sql,null);
        cursorS.moveToFirst();
        if(cursorS.getInt(1)==0) pl1B=false;    else pl1B=true;
        if(cursorS.getInt(2)==0) pl2B=false;    else pl2B=true;
        if(cursorS.getInt(3)==0) pl3B=false;    else pl3B=true;
        if(cursorS.getInt(4)==0) pl4B=false;    else pl4B=true;
        if(cursorS.getInt(5)==0) pl5B=false;    else pl5B=true;
        if(cursorS.getInt(6)==0) pl6B=false;    else pl6B=true;
        sql = "SELECT * FROM Hearing;";
        cursorS = db.rawQuery(sql,null);
        cursorS.moveToFirst();
        if(cursorS.getInt(1)==0) ph1B=false;    else ph1B=true;
        if(cursorS.getInt(2)==0) ph2B=false;    else ph2B=true;
        if(cursorS.getInt(3)==0) ph3B=false;    else ph3B=true;
        if(cursorS.getInt(4)==0) ph4B=false;    else ph4B=true;
        sql = "SELECT * FROM Young;";
        cursorS = db.rawQuery(sql,null);
        cursorS.moveToFirst();
        if(cursorS.getInt(1)==0) py1B=false;    else py1B=true;
        if(cursorS.getInt(2)==0) py2B=false;    else py2B=true;
        if(cursorS.getInt(3)==0) py3B=false;    else py3B=true;
        if(cursorS.getInt(4)==0) py4B=false;    else py4B=true;

        sw1.setChecked(sw1B);sw3.setChecked(sw3B);sw4.setChecked(sw4B);sw5.setChecked(sw5B);
        sw6.setChecked(sw6B);pw1.setChecked(pw1B);pw2.setChecked(pw2B);pw3.setChecked(pw3B);
        pw4.setChecked(pw4B);pw5.setChecked(pw5B);pw6.setChecked(pw6B);pw7.setChecked(pw7B);
        pw8.setChecked(pw8B);pw9.setChecked(pw9B);pw10.setChecked(pw10B);pw11.setChecked(pw11B);
        pl1.setChecked(pl1B);pl2.setChecked(pl2B);pl3.setChecked(pl3B);pl4.setChecked(pl4B);
        pl5.setChecked(pl5B);pl6.setChecked(pl6B);ph1.setChecked(ph1B);ph2.setChecked(ph2B);
        ph3.setChecked(ph3B);ph4.setChecked(ph4B);py1.setChecked(py1B);py2.setChecked(py2B);
        py3.setChecked(py3B);py4.setChecked(py4B);

        sw1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) sw1B=true;
                else                            sw1B=false;
            }
        }) ;
        sw3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) sw3B=true;
                else                            sw3B=false;
            }
        }) ;
        sw4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) sw4B=true;
                else                            sw4B=false;
            }
        }) ;
        sw5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) sw5B=true;
                else                            sw5B=false;
            }
        }) ;
        sw6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) sw6B=true;
                else                            sw6B=false;
            }
        }) ;
        pw1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw1B=true;
                else                            pw1B=false;
            }
        }) ;
        pw2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw2B=true;
                else                            pw2B=false;
            }
        }) ;
        pw3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw3B=true;
                else                            pw3B=false;
            }
        }) ;
        pw4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw4B=true;
                else                            pw4B=false;
            }
        }) ;
        pw5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw5B=true;
                else                            pw5B=false;
            }
        }) ;
        pw6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw6B=true;
                else                            pw6B=false;
            }
        }) ;
        pw7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw7B=true;
                else                            pw7B=false;
            }
        }) ;
        pw8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw8B=true;
                else                            pw8B=false;
            }
        }) ;
        pw9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw9B=true;
                else                            pw9B=false;
            }
        }) ;
        pw10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw10B=true;
                else                            pw10B=false;
            }
        }) ;
        pw11.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pw11B=true;
                else                            pw11B=false;
            }
        }) ;
        pl1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pl1B=true;
                else                            pl1B=false;
            }
        }) ;
        pl2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                pl2B=true;
                if (((CheckBox)v).isChecked()) pw1B=true;
                else                            pw1B=false;
            }
        }) ;
        pl3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pl3B=true;
                else                            pl3B=false;
            }
        }) ;
        pl4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pl4B=true;
                else                            pl4B=false;
            }
        }) ;
        pl5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pl5B=true;
                else                            pl5B=false;
            }
        }) ;
        pl6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) pl6B=true;
                else                            pl6B=false;
            }
        }) ;
        ph1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) ph1B=true;
                else                            ph1B=false;
            }
        }) ;
        ph2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) ph2B=true;
                else                            ph2B=false;
            }
        }) ;
        ph3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) ph3B=true;
                else                            ph3B=false;
            }
        }) ;
        ph4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) ph4B=true;
                else                            ph4B=false;
            }
        }) ;
        py1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) py1B=true;
                else                            py1B=false;
            }
        }) ;
        py2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) py2B=true;
                else                            py2B=false;
            }
        }) ;
        py3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) py3B=true;
                else                            py3B=false;
            }
        }) ;
        py4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) py4B=true;
                else                            py4B=false;
            }
        }) ;
    }
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.walkingSettingButton:
                walkingSettingButton.setBackgroundColor(0xbbfa5555);
                lightSettingButton.setBackgroundColor(0xffffffff);
                hearingSettingButton.setBackgroundColor(0xffffffff);
                youngSettingButton.setBackgroundColor(0xffffffff);

                walkingSettingView.setVisibility(View.VISIBLE);
                lightSettingView.setVisibility(View.INVISIBLE);
                hearingSettingView.setVisibility(View.INVISIBLE);
                youngSettingView.setVisibility(View.INVISIBLE);
                break;
            case R.id.lightSettingButton:
                walkingSettingButton.setBackgroundColor(0xffffffff);
                lightSettingButton.setBackgroundColor(0xbbfa5555);
                hearingSettingButton.setBackgroundColor(0xffffffff);
                youngSettingButton.setBackgroundColor(0xffffffff);

                walkingSettingView.setVisibility(View.INVISIBLE);
                lightSettingView.setVisibility(View.VISIBLE);
                hearingSettingView.setVisibility(View.INVISIBLE);
                youngSettingView.setVisibility(View.INVISIBLE);
                break;
            case R.id.hearingSettingButton:
                walkingSettingButton.setBackgroundColor(0xffffffff);
                lightSettingButton.setBackgroundColor(0xffffffff);
                hearingSettingButton.setBackgroundColor(0xbbfa5555);
                youngSettingButton.setBackgroundColor(0xffffffff);

                walkingSettingView.setVisibility(View.INVISIBLE);
                lightSettingView.setVisibility(View.INVISIBLE);
                hearingSettingView.setVisibility(View.VISIBLE);
                youngSettingView.setVisibility(View.INVISIBLE);
                break;
            case R.id.youngSettingButton:
                walkingSettingButton.setBackgroundColor(0xffffffff);
                lightSettingButton.setBackgroundColor(0xffffffff);
                hearingSettingButton.setBackgroundColor(0xffffffff);
                youngSettingButton.setBackgroundColor(0xbbfa5555);

                walkingSettingView.setVisibility(View.INVISIBLE);
                lightSettingView.setVisibility(View.INVISIBLE);
                hearingSettingView.setVisibility(View.INVISIBLE);
                youngSettingView.setVisibility(View.VISIBLE);
                break;
        }
    }
    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("설정");
        builder.setMessage("설정을 저장하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.onSettingCreate(db);
                        if(sw1B){
                            sql = "UPDATE SWalking SET cIndex1='" +  9+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(sw3B){
                            sql = "UPDATE SWalking SET cIndex3='" +  13+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(sw4B){
                            sql = "UPDATE SWalking SET cIndex4='" +  8+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(sw5B){
                            sql = "UPDATE SWalking SET cIndex5='" +  8+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(sw6B){
                            sql = "UPDATE SWalking SET cIndex6='" +  10+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw1B){
                            sql = "UPDATE PWalking SET cIndex1='" +  10+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw2B){
                            sql = "UPDATE PWalking SET cIndex2='" +  8+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw3B){
                            sql = "UPDATE PWalking SET cIndex3='" +  28+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw4B){
                            sql = "UPDATE PWalking SET cIndex4='" +  17+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw5B){
                            sql = "UPDATE PWalking SET cIndex5='" +  29+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw6B){
                            sql = "UPDATE PWalking SET cIndex6='" +  9+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw7B){
                            sql = "UPDATE PWalking SET cIndex7='" +  13+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw8B){
                            sql = "UPDATE PWalking SET cIndex8='" +  14+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw9B){
                            sql = "UPDATE PWalking SET cIndex9='" +  16+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw10B){
                            sql = "UPDATE PWalking SET cIndex10='" +  20+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pw11B){
                            sql = "UPDATE PWalking SET cIndex11='" +  11+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl1B){
                            sql = "UPDATE Light SET cIndex1='" +  12+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl2B){
                            sql = "UPDATE Light SET cIndex2='" +  30+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl3B){
                            sql = "UPDATE Light SET cIndex3='" +  15+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl4B){
                            sql = "UPDATE Light SET cIndex4='" +  31+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl5B){
                            sql = "UPDATE Light SET cIndex5='" +  18+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(pl6B){
                            sql = "UPDATE Light SET cIndex6='" +  19+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(ph1B){
                            sql = "UPDATE Hearing SET cIndex1='" +  21+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(ph2B){
                            sql = "UPDATE Hearing SET cIndex2='" +  32+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(ph3B){
                            sql = "UPDATE Hearing SET cIndex3='" +  22+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(ph4B){
                            sql = "UPDATE Hearing SET cIndex4='" +  33+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(py1B){
                            sql = "UPDATE Young SET cIndex1='" +  24+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(py2B){
                            sql = "UPDATE Young SET cIndex2='" +  23+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(py3B){
                            sql = "UPDATE Young SET cIndex3='" +  6+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }if(py4B){
                            sql = "UPDATE Young SET cIndex4='" +  5+
                                    "'WHERE _id='" + 1 + "';";//수정하는 쿼리
                            db.execSQL(sql);
                        }
                        db.close();
                        Toast.makeText(getApplicationContext(),"설정되었습니다.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            show();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    public boolean onSupportNavigateUp() {
        show();
        return super.onSupportNavigateUp();
    }
}
