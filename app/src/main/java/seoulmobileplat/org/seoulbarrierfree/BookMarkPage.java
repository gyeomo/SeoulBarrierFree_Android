package seoulmobileplat.org.seoulbarrierfree;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class BookMarkPage extends AppCompatActivity {
    ListViewAdapter adapter;
    SwipeMenuListView listviewMarker;
    Cursor cursor1, cursor2;
    String sql1, sql2;
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String dbName = "ListDB.db";
    android.support.v7.app.ActionBar abar;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        abar = getSupportActionBar();
        abar.setTitle("");
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|
                ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);

        toast=  Toast.makeText(getBaseContext(), "즐겨찾기로 추가한 항목이 없습니다.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);

        dbHelper = new DBHelper(getBaseContext(), dbName, null, dbVersion);
        db=dbHelper.getWritableDatabase();

        listviewMarker = findViewById(R.id.listviewMarker);
        adapter = new ListViewAdapter();

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) { // Swipe 메뉴에 대한 설정
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getBaseContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(250);
                deleteItem.setTitle("삭제");
                deleteItem.setTitleSize(23);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        listviewMarker.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener(){
            @Override
            public void onSwipeStart(int position) {
                // swipe start
                //      listView1.smoothOpenMenu(position);
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                listviewMarker.smoothOpenMenu(position);
            }
        });
        listviewMarker.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0: // delete

                        sql1 = "SELECT * FROM Bookmark ORDER BY TableName ASC, ContentId ASC;";
                        cursor1 = db.rawQuery(sql1, null);
                        cursor1.moveToPosition(position);

                        sql1 = "DELETE FROM Bookmark WHERE _id=" + cursor1.getInt(0) + ";"; //삭제 쿼리
                        db.execSQL(sql1);

                        adapter.clearItem();
                        listSet();
                        listviewMarker.setAdapter(adapter); //어뎁터에 대한 값을 리스트에 삽입
                        adapter.notifyDataSetChanged(); // 어뎁터 변경사항 설정

                        cursor1.close();

                        break;
                }
                return false;
            }
        });

        listSet();
        listviewMarker.setMenuCreator(creator);
        listviewMarker.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listviewMarker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean intentFlag = true;

                sql1 = "SELECT * FROM Bookmark ORDER BY TableName ASC, ContentId ASC;";
                cursor1 = db.rawQuery(sql1, null);
                cursor1.moveToPosition(position);


                if(intentFlag) {
                    Intent intent = new Intent(getBaseContext(), InformationPage.class);
                    intent.putExtra("dbID", cursor1.getInt(1));
                    intent.putExtra("tableName", cursor1.getString(2));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    cursor1.close();
                    startActivity(intent);
                }
            }
        });
    }

    void listSet(){
        sql1 = "SELECT * FROM Bookmark ORDER BY TableName ASC, ContentId ASC;";
        cursor1 = db.rawQuery(sql1,null);
        if(cursor1.getCount()>0) {
            while(cursor1.moveToNext()) {
                sql2 = "SELECT * FROM "+cursor1.getString(2)+";";//+" where _id = "+cursor1.getInt(1)
                cursor2 = db.rawQuery(sql2,null);
                cursor2.moveToFirst();
                while(true){
                    if(cursor2.getInt(0) == cursor1.getInt(1))
                        break;
                    if(!cursor2.moveToNext()) {
                        Toast.makeText(getBaseContext(),"데이터 오류!",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(cursor2.getCount()>0){
                    String splitString[] = cursor2.getString(4).split(" ");
                    adapter.addItem(cursor2.getString(1),
                            splitString[0]+" "+splitString[1], cursor2.getString(25));
                    cursor2.close(); // DB 닫음
                }
            }
        }
        if(adapter.getCount() ==0)
            toast.show();
        cursor1.close(); // DB 닫음

    }
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }
}


