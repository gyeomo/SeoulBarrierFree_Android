package seoulmobileplat.org.seoulbarrierfree;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.GregorianCalendar;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class LoadingPage extends Activity {

    String seoulKey = "647762677663616e38396e464e5050";
    String publicKey = "NYuhd%2Fn48w7fyKXVNKimbbB5Tbu9K2AB%2BTXsps1lR1vMM9cwPYzxaPJ0afjSAFUp0LrIwkEGd6PT1zJtZpUnvA%3D%3D";
    int typeId = 39;//12관광지, 32숙박, 39음식
    Handler handler;

    String dbName = "ListDB.db";
    String sql;
    Cursor cursor;
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    int currentYear;
    int currentMonth;
    int today;
    Intent intent;
    //TextView tv;
    ProgressBar progressBar;
    Button updateButton;

    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    boolean updateFlag = false;

    String tableName;
    String[] dump1 = new String[33];
    long timeStart = 0, timeEnd = 0;
    XmlPullParserFactory factoryP;
    XmlPullParserFactory factoryB;
    XmlPullParserFactory factory;
    XmlPullParser xpp;
    XmlPullParser xppP;
    XmlPullParser xppB;
    String[] txtArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        /*final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }*/
        //isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        try {
            factoryP = XmlPullParserFactory.newInstance();
            xppP = factoryP.newPullParser();
            factoryB = XmlPullParserFactory.newInstance();
            xppB = factoryB.newPullParser();
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        db=dbHelper.getWritableDatabase();
        GregorianCalendar currentCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
        currentYear = currentCalendar.get(GregorianCalendar.YEAR);
        currentMonth = currentCalendar.get(GregorianCalendar.MONTH)+1;
        //tv = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        updateButton = (Button)findViewById(R.id.updateButton);

        /////////////////////////////////////
     /*   new Thread(new Runnable() {
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
        }).start();*/

        ///////////////////////////////////////
        sql = "SELECT * FROM UpdatedDay;";
        cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        today = currentYear * 100 + currentMonth; // 년과 월로 이루어진 수

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateFlag)
                    Toast.makeText(getBaseContext(), "데이터를 불러오는 중입니다.\n잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                else if(today>cursor.getInt(1)) {
                    show();
                    updateFlag = true;
                }
                else
                    Toast.makeText(getBaseContext(), "이미 데이터가 최신입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        if(isNetworkAvailable()) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(!updateFlag) {
                                if (cursor.getInt(1) == 0) {//DB생성시 0을 넣음.
                                    //Toast.makeText(getBaseContext(), "데이터를 불러오는 중입니다.\n잠시만 기다려주세요.", Toast.LENGTH_LONG).show();
                                    //Toast.makeText(getBaseContext(), "1분정도 소요됩니다.", Toast.LENGTH_LONG).show();

                                    updateFlag = true;

                                    InputStream is = null;
                                    FileOutputStream fos = null;
                                    File outDir = new File("/data/data/seoulmobileplat.org.seoulbarrierfree/databases");
                                    outDir.mkdirs();
                                    // timeStart = System.nanoTime();
                                    try {
                                        is = getAssets().open("ListDB.mp3");
                                        int size = is.available();
                                        byte[] buffer = new byte[size];
                                        File outfile = new File(outDir + "/" + "ListDB.db");
                                        fos = new FileOutputStream(outfile);
                                        for (int c = is.read(buffer); c != -1; c = is.read(buffer)) {
                                            fos.write(buffer, 0, c);
                                        }
                                        is.close();
                                        fos.close();
                                        //   timeEnd = System.nanoTime();
                                        intent = new Intent(getBaseContext(), HelpPage.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | FLAG_ACTIVITY_CLEAR_TOP |
                                                Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.putExtra("First",1);
                                        startActivity(intent);
                                        finish();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                                else {
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }).start();
                }
            }, 3000);
        }else{
            Toast.makeText(getBaseContext(),"네트워크가 연결되지 않았습니다.\n연결 후 다시 실행해주세요.",Toast.LENGTH_LONG).show();
        }
    }


    private void XmlParserPublic(){
        Arrays.fill(dump1," ");
        tableName = "Restaurant";
        for(int i = 0; i< 3;i++){
            if(i==1){
                typeId = 32;
                tableName = "Room";//숙박 table
            }
            else if(i==2){
                typeId = 12;
                tableName = "Attraction";//관광지 table
            }
            String queryUrl1 = "http://api.visitkorea.or.kr/openapi/service/rest/KorWithService/areaBasedList?serviceKey="+publicKey+"&numOfRows=999&pageSize=10&pageNo=1&startPage=1&MobileOS=AND&MobileApp=배리어프리&listYN=Y&arrange=A&contentTypeId="+typeId+"&areaCode=1";
            try {
                URL url = new URL(queryUrl1);//문자열로 된 요청 url을 URL 객체로 생성.
                InputStream is = url.openStream(); //url위치로 입력스트림 연결

                xppP.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
                int eventType = xppP.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG){
                        String startTag = xppP.getName();
                        if (startTag.equals("addr1")) {

                            xppP.next();
                            txtArr = xppP.getText().split("\'");
                            if(txtArr.length > 1)
                                dump1[3] = txtArr[0]+txtArr[1];
                            else
                                dump1[3] = txtArr[0];
                        }
                        if (startTag.equals("contentid")) {
                            xppP.next();
                            dump1[1]  = xppP.getText();

                        }
                        if (startTag.equals("contenttypeid")) {
                            xppP.next();
                            dump1[2]  = xppP.getText();

                        }
                        if (startTag.equals("mapx")) {
                            xppP.next();
                            dump1[25]  = xppP.getText();

                        }
                        if (startTag.equals("mapy")) {
                            xppP.next();
                            dump1[26]  = xppP.getText();

                        }
                        if (startTag.equals("title")) {
                            xppP.next();
                            txtArr = xppP.getText().split("\'");
                            if(txtArr.length > 1)
                                dump1[0] = txtArr[0]+txtArr[1];
                            else
                                dump1[0] = txtArr[0];

                            XmlParserPublicBarrier(dump1[1]);

                            sql = String.format("INSERT INTO " + tableName + " VALUES(NULL,'%s','%s','%s','%s','%s'," +
                                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');"
                                    , dump1[0], dump1[1], dump1[2], dump1[3], dump1[4], dump1[5], dump1[6], dump1[7], dump1[8], dump1[9]
                                    , dump1[10], dump1[11], dump1[12], dump1[13], dump1[14], dump1[15], dump1[16], dump1[17], dump1[18], dump1[19]
                                    , dump1[20], dump1[21], dump1[22], dump1[23], "공공데이터포털", dump1[25], dump1[26]
                                    , dump1[27], dump1[28], dump1[29], dump1[30], dump1[31], dump1[32]);
                            db.execSQL(sql);
                            Arrays.fill(dump1," ");
                        }
                    }
                    eventType = xppP.next();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch blocke.printStackTrace();
                e.printStackTrace();
            }
        }
    }

    private void XmlParserPublicBarrier (String contentId){

        String queryUrl1 = "http://api.visitkorea.or.kr/openapi/service/rest/KorWithService/detailWithTour?serviceKey="+publicKey+"&numOfRows=50&pageSize=10&pageNo=1&startPage=1&MobileOS=AND&MobileApp=배리어프리&contentId="+contentId+"&contentTypeId="+typeId+"&withYN=Y";
        try {
            URL url = new URL(queryUrl1);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            xppB.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
            int eventType = xppB.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG){
                    String startTag = xppB.getName();

                    if (startTag.equals("parking")) {
                        xppB.next();

                        dump1[9] = xppB.getText();
                    }
                    if (startTag.equals("route")) {
                        xppB.next();
                        dump1[7] = xppB.getText();
                    }
                    if (startTag.equals("publictransport")) {
                        xppB.next();
                        dump1[27] = xppB.getText();
                    }
                    if (startTag.equals("ticketoffice")) {
                        xppB.next();

                        dump1[16] = xppB.getText();
                    }
                    if (startTag.equals("wheelchair")) {
                        xppB.next();
                        dump1[28] = xppB.getText();
                    }
                    if (startTag.equals("exit")) {
                        xppB.next();

                        dump1[8] = xppB.getText();
                    }
                    if (startTag.equals("elevator")) {
                        xppB.next();

                        dump1[12] = xppB.getText();
                    }
                    if (startTag.equals("restroom")) {
                        xppB.next();

                        dump1[13] =xppB.getText();
                    }
                    if (startTag.equals("auditorium")) {
                        xppB.next();

                        dump1[15] = xppB.getText();
                    }
                    if (startTag.equals("room")) {
                        xppB.next();

                        dump1[19] = xppB.getText();
                    }
                    if (startTag.equals("handicapetc")) {
                        xppB.next();

                        dump1[10] = xppB.getText();
                    }
                    if (startTag.equals("braileblok")) {
                        xppB.next();

                        dump1[11] = xppB.getText();
                    }
                    if (startTag.equals("helpdog")) {
                        xppB.next();

                        dump1[29] = xppB.getText();
                    }
                    if (startTag.equals("guidehuman")) {
                        xppB.next();

                        dump1[14] = xppB.getText();
                    }
                    if (startTag.equals("audioguide")) {
                        xppB.next();

                        dump1[30] =xppB.getText();
                    }
                    if (startTag.equals("brailepromotion")) {
                        xppB.next();

                        dump1[17] = xppB.getText();
                    }
                    if (startTag.equals("blindhandicapet")) {
                        xppB.next();

                        dump1[18] =xppB.getText();
                    }
                    if (startTag.equals("signguide")) {
                        xppB.next();

                        dump1[20] = xppB.getText();
                    }
                    if (startTag.equals("videoguide")) {
                        xppB.next();

                        dump1[31] = xppB.getText();
                    }
                    if (startTag.equals("hearingroom")) {
                        xppB.next();

                        dump1[21] = xppB.getText();
                    }
                    if (startTag.equals("hearinghandicapetc")) {
                        xppB.next();

                        dump1[32] = xppB.getText();
                    }
                    if (startTag.equals("stroller")) {
                        xppB.next();

                        dump1[23] = xppB.getText();
                    }
                    if (startTag.equals("lactationroom")) {
                        xppB.next();

                        dump1[22] = xppB.getText();
                    }
                    if (startTag.equals("babysparechair")) {
                        xppB.next();

                        dump1[5] = xppB.getText();
                    }
                    if (startTag.equals("infantsfamilyetc")) {
                        xppB.next();

                        dump1[4] = xppB.getText();
                    }
                }
                eventType = xppB.next();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
            e.printStackTrace();

        }
    }

    private void XmlParserSeoul(){
        String[] dump = new String[27];
        Arrays.fill(dump," ");
        for(int i = 1; i <3000;i+=1000) {
            String queryUrl = "http://openAPI.seoul.go.kr:8088/" + seoulKey + "/xml/InfoBarrierFree/"+Integer.valueOf(i)+"/"+Integer.valueOf(i+999)+"/";

            try {
                URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
                InputStream is = url.openStream(); //url위치로 입력스트림 연결
                xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG){
                        String startTag = xpp.getName();

                        if (startTag.equals("NAME")) {
                            xpp.next();
                            txtArr = xpp.getText().split("\'");
                            if(txtArr.length > 1)
                                dump[0] = txtArr[0]+txtArr[1];
                            else
                                dump[0] = txtArr[0];
                        }
                        if(startTag.equals("TEL")) {
                            xpp.next();
                            dump[1] = xpp.getText();
                        }
                        if(startTag.equals("HP")) {
                            xpp.next();
                            dump[2] = xpp.getText();
                        }
                        if(startTag.equals("ADDRESS")) {
                            xpp.next();
                            txtArr = xpp.getText().split("\'");
                            if(txtArr.length > 1)
                                dump[3] = txtArr[0]+txtArr[1];
                            else
                                dump[3] = txtArr[0];
                        }
                        if(startTag.equals("BIZHOUR")) {
                            xpp.next();
                            dump[4] = xpp.getText();
                        }
                        if(startTag.equals("REST")) {
                            xpp.next();
                            dump[5] = xpp.getText();
                        }
                        if(startTag.equals("INFORMATION")) {
                            xpp.next();
                            dump[6] = xpp.getText();
                        }
                        if(startTag.equals("INFOMATI_1")) {
                            xpp.next();
                            dump[7] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_1")) {
                            xpp.next();
                            dump[8] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_2")) {
                            xpp.next();
                            dump[9] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_3")) {
                            xpp.next();
                            dump[10] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_4")) {
                            xpp.next();
                            dump[11] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_5")) {
                            xpp.next();
                            dump[12] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_6")) {
                            xpp.next();
                            dump[13] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_7")) {
                            xpp.next();
                            dump[14] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_8")) {
                            xpp.next();
                            dump[15] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_9")) {
                            xpp.next();
                            dump[16] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_10")) {
                            xpp.next();
                            dump[17] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_11")) {
                            xpp.next();
                            dump[18] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_12")) {
                            xpp.next();
                            dump[19] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_13")) {
                            xpp.next();
                            dump[20] = xpp.getText();
                        }
                        if(startTag.equals("INFO_BLE_14")) {
                            xpp.next();
                            dump[21] = xpp.getText();
                        }
                        if(startTag.equals("INFO_B2_1")) {
                            xpp.next();
                            dump[22] = xpp.getText();
                        }
                        if(startTag.equals("INFO_B12_1")) {
                            xpp.next();
                            dump[23] = xpp.getText();
                        }
                        if (startTag.equals("BOARD_LIST")) {
                            xpp.next();
                            if(dump[0].contains("은행"))
                                dump[24] = "금융";
                            else if(dump[0].contains("KT"))
                                dump[24] = "기타";
                            else if(dump[0].contains("안경"))
                                dump[24] = "슈퍼마켓 등 판매점";
                            else if(dump[0].contains("약국"))
                                dump[24] = "병원";
                            else
                                dump[24] = xpp.getText();
                        }
                        if (startTag.equals("XX")) {
                            xpp.next();
                            dump[25] = xpp.getText();
                        }
                        if (startTag.equals("YY")) {
                            xpp.next();
                            dump[26] = xpp.getText();
                            sql = String.format("INSERT INTO " + Categorize.categorize(dump[24]) + " VALUES(NULL,'%s','%s','%s','%s','%s'," +
                                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');"
                                    , dump[0], dump[1], dump[2], dump[3], dump[4], dump[5], dump[6], dump[7], dump[8], dump[9]
                                    , dump[10], dump[11], dump[12], dump[13], dump[14], dump[15], dump[16], dump[17], dump[18], dump[19]
                                    , dump[20], dump[21], dump[22], dump[23], "서울 열린데이터 광장",dump[25],dump[26]," "," "," "," "," "," ");
                            db.execSQL(sql);
                            Arrays.fill(dump," ");
                        }
                    }
                    eventType = xpp.next();
                }

            } catch (Exception e) {
                // TODO Auto-generated catch blocke.printStackTrace();
                e.printStackTrace();
            }
        }
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("데이터 갱신");
        builder.setMessage("데이터를 갱신하시겠습니까?\n시간이 오래걸릴 수 있습니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "데이터를 불러옵니다..\n잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "1분정도 소요됩니다.", Toast.LENGTH_LONG).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                dbHelper.onReCreate(db);
                                db.beginTransaction();//https://lovefields.github.io/android/2017/03/21/post29.html
                                try {
                                    //           timeStart = System.nanoTime();
                                    XmlParserPublic();
                                    XmlParserSeoul();
                                    //           timeEnd = System.nanoTime();
                                    sql = "UPDATE UpdatedDay SET Date='" + today +
                                            "'WHERE _id='" + 1 + "';";
                                    db.execSQL(sql);
                                    db.setTransactionSuccessful();
                                } catch (Exception e) {
                                } finally {
                                    db.endTransaction();
                                }
                                db.close();

                                //    intent.putExtra("time",(timeEnd-timeStart));
                                startActivity(intent);
                                finish();

                            }
                        }).start();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent);finish();
                    }
                });
        builder.show();
    }
    @Override
    public void onDestroy() { // 종료시 실행
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}