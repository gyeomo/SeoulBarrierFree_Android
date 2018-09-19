package seoulmobileplat.org.seoulbarrierfree;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    Spinner spinnerType;
    Spinner spinnerRegion;
    Button walking;
    Button light;
    Button hearing;
    Button young;
  //  TextView tv;

    ListViewAdapter adapter;
    String dbName = "ListDB.db";
    String sql;
    String tableName = "";
    String RegionName = "전체";
    Cursor cursor;
    Cursor cursorS;
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    double distance;
    String[] itemType;
    String[] itemRegion;
    Toast toast;
    boolean walkingEnable = true;
    boolean lightEnable = true;
    boolean hearingEnable = true;
    boolean youngEnable = true;

    double longitude;
    double latitude;

    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    LocationManager lm;

    int dumpPosition =0;
    String dumpString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        itemType = new String[]{"음식점","숙박","관광지",
                "슈퍼마켓 등 판매점","공원","금융","공연장/영화관","병원","공중화장실",
                "여객시설/지하철역","공공시설","이/미용실","전시장","종교시설","체육관","기타"};
        itemRegion = new String[]{"서울시 전체","1km 내","500m 내","강남구","강동구","강북구",
                "강서구","관악구","광진구","구로구","금천구","노원구",
                "도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구",
                "성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};
        dbHelper = new DBHelper(getBaseContext(), dbName, null, dbVersion);
        db=dbHelper.getWritableDatabase();

    //    tv = (TextView)findViewById(R.id.indicatorText);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        ////////////////////////////////////////////////////////////////////////////////////////
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try{
                            if(isGPSEnabled) {
                                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                        100, // 통지사이의 최소 시간간격 (miliSecond)
                                        1, // 통지사이의 최소 변경거리 (m)
                                        mLocationListener);
                            }
                            if(isNetworkEnabled) {
                                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                        100, // 통지사이의 최소 시간간격 (miliSecond)
                                        1, // 통지사이의 최소 변경거리 (m)
                                        mLocationListener);
                            }
                            else{
                                lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                            }
                        }catch(SecurityException ex){
                        }
                    }
                });
            }
        }).start();

        /////////////////////////////////////////////////////////////////////////////////////////
        toast=  makeText(getBaseContext(), "현재 설정에 맞는 데이터가 없습니다. 옵션에서 다시 설정해주세요.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        listView = (ListView)findViewById(R.id.listView);
        spinnerType = (Spinner)findViewById(R.id.spinnerType);
        spinnerRegion = (Spinner)findViewById(R.id.spinnerRegion);
        //   testText = (TextView)findViewById(testText);
        //   long time = getIntent().getExtras().getLong("time");
        //   testText.setText(""+time);
        ArrayAdapter<String> sTAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                itemType);
        sTAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tableName = Categorize.categorize(itemType[position]);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        runOnUiThread(runnable);
                    }
                });
                thread.interrupt();
                thread.start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerType.setAdapter(sTAdapter);

        ArrayAdapter<String> sRAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                itemRegion);
        sRAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dumpString = RegionName;
                RegionName = itemRegion[position];
                if(RegionName.equals("1km 내") || RegionName.equals("500m 내")) {
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        makeText(getBaseContext(), "GPS를 활성화 시켜주세요.", Toast.LENGTH_SHORT).show();
                        spinnerRegion.setSelection(dumpPosition);
                        RegionName=dumpString;
                        createGpsDisabledAlert();
                    }
                    else{
                        dumpPosition=position;
                        makeText(getBaseContext(), "실내에선 거리가 정확하지 않을 수 있습니다.", Toast.LENGTH_SHORT).show();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                runOnUiThread(runnable);
                            }
                        });
                        thread.interrupt();
                        thread.start();
                    }
                }
                else{
                    dumpPosition=position;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            runOnUiThread(runnable);
                        }
                    });
                    thread.interrupt();
                    thread.start();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRegion.setAdapter(sRAdapter);

        walking = (Button)findViewById(R.id.walking);walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(walkingEnable){
                    walkingEnable=false;    walking.setBackgroundColor(0xbbfa5555);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);
                    hearingEnable=true;    hearing.setBackgroundColor(0xffffffff);}
                else{
                    walkingEnable=true;    walking.setBackgroundColor(0xffffffff);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);
                    hearingEnable=true;    hearing.setBackgroundColor(0xffffffff);}
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        runOnUiThread(runnable);
                    }
                });
                thread.interrupt();
                thread.start();
            }
        });
        light = (Button)findViewById(R.id.light);light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightEnable){
                    lightEnable=false;      light.setBackgroundColor(0xbbfa5555);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);
                    hearingEnable=true;     hearing.setBackgroundColor(0xffffffff);}
                else{
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    hearingEnable=true;     hearing.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);}
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        runOnUiThread(runnable);
                    }
                });
                thread.interrupt();
                thread.start();
            }
        });
        hearing = (Button)findViewById(R.id.hearing);hearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hearingEnable){
                    hearingEnable=false;    hearing.setBackgroundColor(0xbbfa5555);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);}
                else{
                    hearingEnable=true;     hearing.setBackgroundColor(0xffffffff);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);}
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        runOnUiThread(runnable);
                    }
                });
                thread.interrupt();
                thread.start();
            }
        });
        young = (Button)findViewById(R.id.young);young.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(youngEnable){
                    youngEnable=false;      young.setBackgroundColor(0xbbfa5555);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    hearingEnable=true;     hearing.setBackgroundColor(0xffffffff);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);}
                else{
                    youngEnable=true;       young.setBackgroundColor(0xffffffff);
                    walkingEnable=true;     walking.setBackgroundColor(0xffffffff);
                    lightEnable=true;       light.setBackgroundColor(0xffffffff);
                    hearingEnable=true;     hearing.setBackgroundColor(0xffffffff);}
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        runOnUiThread(runnable);
                    }
                });
                thread.interrupt();
                thread.start();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean intentFlag = true;
                sql = "SELECT * FROM "+tableName+" ORDER BY NAME ASC;";
                cursor = db.rawQuery(sql,null);
                cursor.moveToFirst();
                while(true){
                    if(cursor.getString(1).equals(adapter.getTitle(position)) &&
                            cursor.getString(25).equals(adapter.getDesc(position)))
                        break;
                    if(!cursor.moveToNext()) {
                        intentFlag = false;
                        Toast.makeText(getBaseContext(),"데이터 오류!",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(intentFlag) {
                    Intent intent = new Intent(getBaseContext(), InformationPage.class);
                    intent.putExtra("dbID", cursor.getInt(0));
                    intent.putExtra("tableName", tableName);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            }
        });
        adapter = new ListViewAdapter();

    }


    public double DistanceByDegreeAndroid(double _latitude1, double _longitude1, double _latitude2, double _longitude2){
        Location startPos = new Location("PointA");
        Location endPos = new Location("PointB");

        startPos.setLatitude(_latitude1);
        startPos.setLongitude(_longitude1);
        endPos.setLatitude(_latitude2);
        endPos.setLongitude(_longitude2);

        double distance = startPos.distanceTo(endPos)/1000;

        return distance;
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try{ // 처음 시작할 때, DB에 있는 데이터를 리스트뷰로 보여줌.
                adapter.clearItem();
                sql = "SELECT * FROM "+tableName+" ORDER BY NAME ASC;";
                cursor = db.rawQuery(sql,null);
                if(cursor.getCount()>0){
                    while(cursor.moveToNext()){
                        boolean walkingFlag = false;
                        boolean lightFlag = false;
                        boolean hearingFlag = false;
                        boolean youngFlag = false;
                        boolean allFlag = false;
                        if(cursor.getString(25).equals("서울 열린데이터 광장")) {
                            if (!walkingEnable) {
                                sql = "SELECT * FROM SWalking;";
                                cursorS = db.rawQuery(sql,null);
                                cursorS.moveToFirst();
                                for(int i = 1; i< 6; i++) {
                                    if (cursorS.getInt(i) != 0) {
                                        if(i==1){
                                            if(cursor.getString(cursorS.getInt(1)).contains("턱이나")||
                                                    cursor.getString(cursorS.getInt(1)).contains("이용가능")){
                                                walkingFlag = true;
                                            }
                                            else{
                                                walkingFlag = false;
                                                break;
                                            }
                                        }
                                        else if(i==2){
                                            if(cursor.getString(cursorS.getInt(2)).contains("음성신호장치")||
                                                    cursor.getString(cursorS.getInt(2)).contains("휠체어")){
                                                walkingFlag = true;
                                            }
                                            else{
                                                walkingFlag = false;
                                                break;
                                            }
                                        }
                                        else if(i==3){
                                            if(cursor.getString(cursorS.getInt(3)).contains("지상버스")){
                                                walkingFlag = true;
                                            }
                                            else{
                                                walkingFlag = false;
                                                break;
                                            }
                                        }
                                        else if(i==4){
                                            if(cursor.getString(cursorS.getInt(4)).contains("장애인용화장실")||
                                                    cursor.getString(cursorS.getInt(4)).contains("장애인용 화장실")){
                                                walkingFlag = true;
                                            }
                                            else{
                                                walkingFlag = false;
                                                break;
                                            }
                                        }
                                        else if(i==5){
                                            if(cursor.getString(cursorS.getInt(4)).contains("이용가능")||
                                                    cursor.getString(cursorS.getInt(4)).contains("장애인전용주차구역")){
                                                walkingFlag = true;
                                            }
                                            else{
                                                walkingFlag = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                cursorS.close();
                            }
                        }
                        else {
                            if (!walkingEnable) {
                                sql = "SELECT * FROM PWalking;";
                                cursorS = db.rawQuery(sql,null);
                                cursorS.moveToFirst();
                                for(int i=1; i< 12 ; i++){
                                    if(cursorS.getInt(i) != 0){
                                        if(!cursor.getString(cursorS.getInt(i)).equals(" ")) {
                                            walkingFlag = true;
                                        }
                                        else{
                                            walkingFlag = false;
                                            break;
                                        }
                                    }
                                }
                                cursorS.close();
                            }
                            else if (!lightEnable) {
                                sql = "SELECT * FROM Light;";
                                cursorS = db.rawQuery(sql,null);
                                cursorS.moveToFirst();
                                for(int i=1; i< 7 ; i++){
                                    if(cursorS.getInt(i) != 0){
                                        if(!cursor.getString(cursorS.getInt(i)).equals(" ")) {
                                            lightFlag = true;
                                        }
                                        else{
                                            lightFlag = false;
                                            break;
                                        }
                                    }
                                }
                                cursorS.close();
                            } else if (!hearingEnable) {
                                sql = "SELECT * FROM Hearing;";
                                cursorS = db.rawQuery(sql,null);
                                cursorS.moveToFirst();
                                for(int i=1; i< 5 ; i++){
                                    if(cursorS.getInt(i) != 0){
                                        if(!cursor.getString(cursorS.getInt(i)).equals(" ")) {
                                            hearingFlag = true;
                                        }
                                        else{
                                            hearingFlag = false;
                                            break;
                                        }
                                    }
                                }
                                cursorS.close();
                            } else if (!youngEnable) {
                                sql = "SELECT * FROM Young;";
                                cursorS = db.rawQuery(sql,null);
                                cursorS.moveToFirst();
                                for(int i=1; i< 5 ; i++){
                                    if(cursorS.getInt(i) != 0){
                                        if(!cursor.getString(cursorS.getInt(i)).equals(" ")) {
                                            youngFlag = true;
                                        }
                                        else{
                                            youngFlag = false;
                                            break;
                                        }
                                    }
                                }
                                cursorS.close();
                            }
                        }
                        if (walkingEnable && lightEnable && hearingEnable && youngEnable) {
                            allFlag = true;
                        }
                        if (walkingFlag || lightFlag || hearingFlag || youngFlag || allFlag) {
                            String splitString[] = cursor.getString(4).split(" ");
                            if (splitString[1].equals(RegionName))
                                adapter.addItem(cursor.getString(1),
                                        splitString[0]+" "+splitString[1], cursor.getString(25));//distance+"km",cursor.getString(9));
                            if (RegionName.equals("서울시 전체"))
                                adapter.addItem(cursor.getString(1),
                                        splitString[0]+" "+splitString[1], cursor.getString(25));//distance+"km",cursor.getString(9));
                            if(RegionName.equals("500m 내")){
                                distance = DistanceByDegreeAndroid(latitude,longitude,cursor.getDouble(27), cursor.getDouble(26))*1000;
                                if(distance<=500) {
                                    adapter.addItem(cursor.getString(1),
                                            String.format("%.3f", distance) + "m", cursor.getString(25));//,cursor.getString(9));
                                }
                            }
                            if(RegionName.equals("1km 내")){
                                distance = DistanceByDegreeAndroid(latitude,longitude,cursor.getDouble(27), cursor.getDouble(26));
                                if(distance<=1) {
                                    adapter.addItem(cursor.getString(1),
                                            String.format("%.3f", distance) + "km", cursor.getString(25));//,cursor.getString(9));
                                }
                            }
                        }
                    }
                }
                else {
                }
            }finally{
                cursor.close(); // DB 닫음
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if(adapter.getCount() ==0)
                    toast.show();
            }
        }
    };
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int curId = item.getItemId();
        Intent intent;
        switch(curId){
            case R.id.menu_tip:
                intent = new Intent(getBaseContext(),TipPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            case R.id.menu_emergency:
                intent = new Intent(getBaseContext(),CallPage.class);
                startActivity(intent);
                break;
            case R.id.menu_bookmark:
                intent = new Intent(getBaseContext(),BookMarkPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            case R.id.menu_settings:
                intent = new Intent(getBaseContext(),SettingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            case R.id.menu_help:
                intent = new Intent(getBaseContext(),HelpPage.class);
                intent.putExtra("First",0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.
            Geocoder mGeoCoder = new Geocoder(getBaseContext());


            Log.d("test", "onLocationChanged, location:" + location);
            longitude = location.getLongitude(); //경도
            latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
                //try{ //http://developer88.tistory.com/135
                   //List<Address> mResultList = mGeoCoder.getFromLocation(latitude,longitude,1);
                   //tv.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
                  //         + "\n고도 : " + altitude + "\n정확도 : "  + accuracy+"\n주소 : "+mResultList.get(0).getAddressLine(0));
               //}catch (IOException e){
               //   e.printStackTrace();
              //}

        }
        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };
    // GPS Disabled Alert
    private void createGpsDisabledAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS가 꺼져있습니다. \nGPS를 켜시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("GPS 키기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                showGpsOptions();
                            }
                        })
                .setNegativeButton("그대로 두기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // show GPS Options
    private void showGpsOptions() {
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }
    @Override
    public void onDestroy() { // 종료시 실행
        super.onDestroy();
        if(isGPSEnabled)
            lm.removeUpdates(mLocationListener);
        finish();
    }
}

