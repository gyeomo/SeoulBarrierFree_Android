package seoulmobileplat.org.seoulbarrierfree;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class InformationPage extends AppCompatActivity {
    String publicKey = "NYuhd%2Fn48w7fyKXVNKimbbB5Tbu9K2AB%2BTXsps1lR1vMM9cwPYzxaPJ0afjSAFUp0LrIwkEGd6PT1zJtZpUnvA%3D%3D";
    TextView walkingText;
    TextView lightText;
    TextView hearingText;
    TextView youngText;
    TextView basicInformation;
    TextView titleView;
    TextView detailText;
    TextView remove1, remove2, remove3;
    Button bookMarker;
    Button intentMap;
    View remove4;
    LinearLayout lightInformation;
    LinearLayout hearingInformation;
    LinearLayout youngInformation;
    LinearLayout detailInformation;
    String dbName = "ListDB.db";
    String sql;
    Cursor cursor;
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    int dbID;
    String tableName;
    String titleName;
    int viewSelector=0;
    boolean viewHideFlag = false;
    int contentTypeId;
    String dump="";
    SpannableStringBuilder sb;
    android.support.v7.app.ActionBar abar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informaion);

        sb = new SpannableStringBuilder();


        abar = getSupportActionBar();
        abar.setTitle("");
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|
                ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);


        titleView = (TextView)findViewById(R.id.titleView);
        walkingText = (TextView)findViewById(R.id.walkingText);
        lightText = (TextView)findViewById(R.id.lightText);
        hearingText = (TextView)findViewById(R.id.hearingText);
        youngText = (TextView)findViewById(R.id.youngText);
        basicInformation = (TextView)findViewById(R.id.basicInformation);
        detailText = (TextView)findViewById(R.id.detailText);
        remove1 = (TextView)findViewById(R.id.remove1);
        remove2 = (TextView)findViewById(R.id.remove2);
        remove3 = (TextView)findViewById(R.id.remove3);
        remove4 = (View)findViewById(R.id.remove4);
        lightInformation = (LinearLayout)findViewById(R.id.lightInformation);
        hearingInformation = (LinearLayout)findViewById(R.id.hearingInformation);
        youngInformation = (LinearLayout)findViewById(R.id.youngInformation);
        detailInformation = (LinearLayout)findViewById(R.id.detailInformation);

        dbID = getIntent().getExtras().getInt("dbID");
        tableName = getIntent().getExtras().getString("tableName");
        if(tableName.equals("Restaurant"))
            contentTypeId = 39;
        else if(tableName.equals("Room"))
            contentTypeId = 32;
        else if(tableName.equals("Attraction"))
            contentTypeId = 12;





        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        db=dbHelper.getWritableDatabase();
        sql = "SELECT * FROM "+tableName+" ORDER BY NAME ASC;";
        cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while(true){
            if(cursor.getInt(0) == dbID)
                break;
            cursor.moveToNext();
        }

        bookMarker = (Button)findViewById(R.id.bookMarker);
        bookMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT*from Bookmark where ContentId = "+dbID+" and TableName = '"+tableName+"';", null);
                c.moveToFirst();
                if( c.getCount() == 0) {
                    sql = String.format("INSERT INTO Bookmark VALUES(NULL,'%s','%s');"
                            , dbID,tableName);
                    db.execSQL(sql);
                    Toast.makeText(getBaseContext(), "즐겨찾기에 추가됐습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(), "이미 즐겨찾기에 추가되어 있습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        intentMap = (Button)findViewById(R.id.intentMap);
        intentMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:0,0?q="+titleName);                  // 좌표값 설정.
                Intent intent = new Intent( android.content.Intent.ACTION_VIEW, uri); // 인텐트 생성.
                startActivity(intent);
            }
        });
        ///////////////////////////////////////
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer buffer1 = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();
                StringBuffer buffer3 = new StringBuffer();
                StringBuffer buffer4 = new StringBuffer();
                StringBuffer buffer5 = new StringBuffer();
                for(int i=1; i<34;i++) {
                    viewSelector=0;
                    if(i==1){titleName = cursor.getString(i);continue;}
                    if(i==25 || i==26 || i==27)
                        continue;
                    if(!cursor.getString(i).equals(" ") && !cursor.getString(i).equals("null")) {
                        if(cursor.getString(25).equals("공공데이터포털")){
                            getColumnP(i);
                            switch(viewSelector){
                                case 1 :
                                    buffer1.append("<b>-"+getColumnP(i) + "</b> : " + cursor.getString(i));
                                    buffer1.append("<br>"); //줄바꿈 문자 추가
                                    break;
                                case 2 :
                                    buffer2.append("<b>-"+getColumnP(i) + "</b> : " + cursor.getString(i));
                                    buffer2.append("<br>"); //줄바꿈 문자 추가
                                    break;
                                case 3 :
                                    buffer3.append("<b>-"+getColumnP(i) + "</b> : " + cursor.getString(i));
                                    buffer3.append("<br>"); //줄바꿈 문자 추가
                                    break;
                                case 4 :
                                    buffer4.append("<b>-"+getColumnP(i) + "</b> : " + cursor.getString(i));
                                    buffer4.append("<br>"); //줄바꿈 문자 추가
                                    break;
                                case 5 :
                                    buffer5.append("<b>-"+getColumnP(i) + "</b> : " + cursor.getString(i));
                                    buffer5.append("<br>"); //줄바꿈 문자 추가
                                    break;
                            }
                        }
                        else {
                            viewHideFlag = true;
                            getColumnS(i);
                            if(viewSelector==1) {
                                buffer1.append("<b>-" + getColumnS(i) + "</b> : " + cursor.getString(i));
                                buffer1.append("<br>"); //줄바꿈 문자 추가
                            }
                            else if(viewSelector==5){
                                if(i==7){
                                    dump = "<b>-" + getColumnS(i) + "</b> : " + cursor.getString(i);
                                }
                                else {
                                    buffer5.append("<b>-" + getColumnS(i) + "</b> : " + cursor.getString(i));
                                    buffer5.append("<br>"); //줄바꿈 문자 추가
                                }
                            }
                        }
                    }
                }

                final String data1 =buffer1.toString();
                final String data2 =buffer2.toString();
                final String data3 =buffer3.toString();
                final String data4 =buffer4.toString();
                final String data5;
                final String data6;

                if(viewHideFlag) {
                    data5 = buffer5.toString();
                    data6 = dump;
                }
                else {
                    data5 =buffer5.toString()+XmlParserPublicCommon(cursor.getString(2));
                    data6 = XmlParserPublicDetail(cursor.getString(2),contentTypeId)+dump;
                }
                cursor.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        titleView.setText(titleName);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            basicInformation.setText(Html.fromHtml(data5,Html.FROM_HTML_MODE_LEGACY));
                            walkingText.setText(Html.fromHtml(data1,Html.FROM_HTML_MODE_LEGACY));
                            lightText.setText(Html.fromHtml(data2,Html.FROM_HTML_MODE_LEGACY));
                            hearingText.setText(Html.fromHtml(data3,Html.FROM_HTML_MODE_LEGACY));
                            youngText.setText(Html.fromHtml(data4,Html.FROM_HTML_MODE_LEGACY));
                            detailText.setText(Html.fromHtml(data6,Html.FROM_HTML_MODE_LEGACY));
                            if (viewHideFlag) {
                                remove1.setText("상세정보");
                                lightText.setText(Html.fromHtml(data6,Html.FROM_HTML_MODE_LEGACY));
                                //    lightInformation.setVisibility(View.INVISIBLE);
                                hearingInformation.setVisibility(View.INVISIBLE);
                                youngInformation.setVisibility(View.INVISIBLE);
                                detailInformation.setVisibility(View.INVISIBLE);
                            }
                        }else{
                            basicInformation.setText(Html.fromHtml(data5));
                            walkingText.setText(Html.fromHtml(data1));
                            lightText.setText(Html.fromHtml(data2));
                            hearingText.setText(Html.fromHtml(data3));
                            youngText.setText(Html.fromHtml(data4));
                            detailText.setText(Html.fromHtml(data6));
                            if (viewHideFlag) {
                                remove1.setText("상세정보");
                                lightText.setText(Html.fromHtml(data6));
                                //    lightInformation.setVisibility(View.INVISIBLE);
                                hearingInformation.setVisibility(View.INVISIBLE);
                                youngInformation.setVisibility(View.INVISIBLE);
                                detailInformation.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
            }
        }).start();
    }
    String getColumnP(int index){
        String columnName = "";
        switch(index){
            case 10:
                columnName = "주차여부";
                viewSelector=1;
                break;
            case 8:
                columnName = "대중교통";
                viewSelector=1;
                break;
            case 28:
                columnName = "접근로";
                viewSelector=1;
                break;
            case 29:
                columnName = "휠체어";
                viewSelector=1;
                break;
            case 17:
                columnName = "매표소";
                viewSelector=1;
                break;
            case 9:
                columnName = "출입통로";
                viewSelector=1;
                break;
            case 13:
                columnName = "엘레베이터";
                viewSelector=1;
                break;
            case 14:
                columnName = "화장실";
                viewSelector=1;
                break;
            case 16:
                columnName = "관람석";
                viewSelector=1;
                break;
            case 20:
                columnName = "객실";
                viewSelector=1;
                break;
            case 11:
                columnName = "지체장애 기타 상세";
                viewSelector=1;
                break;
            case 12:
                columnName = "점자블록";
                viewSelector=2;
                break;
            case 30:
                columnName = "보조견동반";
                viewSelector=2;
                break;
            case 15:
                columnName = "안내요원";
                viewSelector=2;
                break;
            case 31:
                columnName = "오디오가이드";
                viewSelector=2;
                break;
            case 18:
                columnName = "점자홍보물 및 점자표지판";
                viewSelector=2;
                break;
            case 19:
                columnName = "시각장애 기타상세";
                viewSelector=2;
                break;
            case 21:
                columnName = "수화안내";
                viewSelector=3;
                break;
            case 32:
                columnName = "자막 비디오가이드 및 영상자막안내";
                viewSelector=3;
                break;
            case 22:
                columnName = "객실";
                viewSelector=3;
                break;
            case 33:
                columnName = "청각장애 기타상세";
                viewSelector=3;
                break;
            case 24:
                columnName = "유모차";
                viewSelector=4;
                break;
            case 23:
                columnName = "수유실";
                viewSelector=4;
                break;
            case 6:
                columnName = "유아용보조의자";
                viewSelector=4;
                break;
            case 5:
                columnName = "영유아가족 기타상세";
                viewSelector=4;
                break;
            case 1:
                columnName = "업소명";
                break;
            case 2:
                columnName = "contentId";
                break;
            case 3:
                columnName = "contentType";
                break;
            case 4:
                columnName = "주소";
                viewSelector=5;
                break;
        }
        return columnName;
    }
    String getColumnS(int index){
        String columnName = "";
        switch(index) {
            case 1:
                columnName = "업소명";
                break;
            case 2:
                columnName = "연락처";
                viewSelector=5;
                break;
            case 3:
                columnName = "핸드폰";
                viewSelector=5;
                break;
            case 4:
                columnName = "주소";
                viewSelector=5;
                break;
            case 5:
                columnName = "영업시간";
                viewSelector=5;
                break;
            case 6:
                columnName = "휴무일";
                viewSelector=5;
                break;
            case 7:
                columnName = "기본정보";
                viewSelector=5;
                break;
            case 8:
                columnName = "장애인 편의 시설";
                viewSelector=1;
                break;
            case 9:
                columnName = "주출입구";
                viewSelector=1;
                break;
            case 10:
                columnName = "장애인전용주차구역";
                viewSelector=1;
                break;
            case 11:
                columnName = "출입문 문 폭";
                viewSelector=1;
                break;
            case 12:
                columnName = "복도/통로";
                viewSelector=1;
                break;
            case 13:
                columnName = "장애인용 엘리베이터";
                viewSelector=1;
                break;
            case 14:
                columnName = "장애인용 화장실";
                viewSelector=1;
                break;
            case 15:
                columnName = "음식점 좌석/테이블";
                viewSelector=1;
                break;
            case 16:
                columnName = "장애인용 관람석";
                viewSelector=1;
                break;
            case 17:
                columnName = "접수대 작업대";
                viewSelector=1;
                break;
            case 18:
                columnName = "샤워실 탈의실";
                viewSelector=1;
                break;
            case 19:
                columnName = "테이블/진열대 등 통로 폭";
                viewSelector=1;
                break;
            case 20:
                columnName = "장애인용 객실";
                viewSelector=1;
                break;
            case 21:
                columnName = "여객시설";
                viewSelector=1;
                break;
            case 22:
                columnName = "비치용품";
                viewSelector=1;
                break;
            case 23:
                columnName = "이용가능댓수";
                break;
            case 24:
                columnName = "단차 cm";
                break;
        }
        return columnName;
    }

    private String XmlParserPublicCommon (String contentId){
        StringBuffer buffer = new StringBuffer();

        String queryUrl1 = "http://api.visitkorea.or.kr/openapi/service/rest/KorWithService/detailCommon?ServiceKey="+publicKey+"&contentId="+contentId+"&defaultYN=Y&overviewYN=Y&MobileOS=AND&MobileApp=AppTest";
        try {
            URL url = new URL(queryUrl1);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG){
                    String startTag = xpp.getName();

                    if (startTag.equals("tel")) {
                        xpp.next();
                        buffer.append("<b>-"+"전화번호</b> : "+xpp.getText());
                        buffer.append("<br>");
                    }
                    if (startTag.equals("overview")) {
                        xpp.next();

                        if(xpp.getText().equals("null")) {
                            eventType = xpp.next();
                            continue;
                        }
                        dump = "<br><b>-"+"개요</b> : "+xpp.getText()+"<br>";
                    }
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
            e.printStackTrace();

        }
        // buffer.append(dump);
        return buffer.toString();
    }

    private String XmlParserPublicDetail (String contentId, int contentTypeId){
        StringBuffer buffer = new StringBuffer();
        String queryUrl1 = "http://api.visitkorea.or.kr/openapi/service/rest/KorWithService/detailIntro?ServiceKey="+publicKey+"&contentId="+contentId+"&contentTypeId="+contentTypeId+"&MobileOS=AND&MobileApp=AppTest";
        try {
            URL url = new URL(queryUrl1);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG){
                    String startTag = xpp.getName();
                    if(contentTypeId == 12){
                        if (startTag.equals("accomcount")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"수용인원</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("chkbabycarriage")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"유모차 대여 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("chkcreditcard")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"신용카드 가능 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("chkpet")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"애완동물 가능 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("expagerange")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"체험가능 연령</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("expguide")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"체험안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("infocenter")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"문의 및 안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("opendate")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"개장일</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("parking")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"주차시설</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("restdate")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"쉬는날</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("useseason")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"이용시기</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("usetime")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"이용시간</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                    }
                    else if(contentTypeId == 32) {
                        if (startTag.equals("accomcountlodging")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"수용 가능인원</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("benikia")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"베니키아 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("checkintime")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"입실 시간</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("checkouttime")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"퇴실 시간</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("chkcooking")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"객실내 취사 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("foodplace")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"식음료장</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("goodstay")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"굿스테이 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("hanok")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"한옥 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("infocenterlodging")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"문의 및 안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("parkinglodging")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"주차시설</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("pickup")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"픽업 서비스</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("roomcount")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"객실수</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }

                        if (startTag.equals("reservationlodging")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"예약안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("roomtype")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"객실유형</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("scalelodging")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"규모</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("subfacility")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"부대시설 (기타)</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("barbecue")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"바비큐장 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("beauty")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"뷰티시설 정보</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("beverage")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"식음료장 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("karaoke")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"노래방 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("publicpc")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"공용 PC실 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("sauna")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"사우나실 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                    }
                    else if(contentTypeId == 39) {
                        if (startTag.equals("chkcreditcardfood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"신용카드 가능 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }

                        if (startTag.equals("firstmenu")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"대표 메뉴</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("infocenterfood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"문의 및 안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("kidsfacility")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"어린이 놀이방 여부</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("opendatefood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"개업일</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("opentimefood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"영업시간</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("packing")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"포장 가능</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("parkingfood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"주차시설</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("reservationfood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"예약안내</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("restdatefood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"쉬는날</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("scalefood")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"규모</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("seat")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"좌석수</b> : "+xpp.getText());
                            buffer.append("<br>");
                        }
                        if (startTag.equals("treatmenu")) {
                            xpp.next();
                            if(xpp.getText().equals("null")) {
                                eventType = xpp.next();
                                continue;
                            }
                            buffer.append("<b>-"+"취급 메뉴</b> : "+xpp.getText());

                            buffer.append("<br>");
                        }
                    }
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
            e.printStackTrace();

        }
        return buffer.toString();
    }
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }

}



