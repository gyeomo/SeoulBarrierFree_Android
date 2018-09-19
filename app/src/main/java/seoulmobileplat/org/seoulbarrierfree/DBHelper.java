package seoulmobileplat.org.seoulbarrierfree;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //생성자 - database 파일을 생성합니다.
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB 처음 만들때 한번만 호출 -테이블을 생성합니다.

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Public (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Auditorium (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Park (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Ptoilet  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Attraction (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Bank  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Others (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Hospital  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Store (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Station  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Restaurant (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE BShop  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Showroom (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Religion  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Gym (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Room (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE UpdatedDay (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   Date Integer);");
        String sql = String.format("INSERT INTO UpdatedDay VALUES(NULL,'%s');",0);
        db.execSQL(sql);

        db.execSQL("CREATE TABLE SWalking (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer);");
        sql = String.format("INSERT INTO SWalking VALUES(NULL,'%s','%s','%s','%s','%s');"
                ,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE PWalking (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer, cIndex7 Integer, cIndex8 Integer, " +
                "   cIndex9 Integer, cIndex10 Integer, cIndex11 Integer);");
        sql = String.format("INSERT INTO PWalking VALUES(NULL,'%s','%s','%s','%s','%s','%s'" +
                        ",'%s','%s','%s','%s','%s');"
                ,0,0,0,0,0,0,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Light (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer);");
        sql = String.format("INSERT INTO Light VALUES(NULL,'%s','%s','%s','%s','%s','%s');"
                ,0,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Hearing (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer);");
        sql = String.format("INSERT INTO Hearing VALUES(NULL,'%s','%s','%s','%s');"
                ,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Young (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer);");
        sql = String.format("INSERT INTO Young VALUES(NULL,'%s','%s','%s','%s');"
                ,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Bookmark (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   ContentId Integer, TableName TEXT);");
    }
    public void onSettingCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS SWalking");
        db.execSQL("DROP TABLE IF EXISTS PWalking");
        db.execSQL("DROP TABLE IF EXISTS Light");
        db.execSQL("DROP TABLE IF EXISTS Hearing");
        db.execSQL("DROP TABLE IF EXISTS Young");
        db.execSQL("CREATE TABLE SWalking (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer);");
        String sql = String.format("INSERT INTO SWalking VALUES(NULL,'%s','%s','%s','%s','%s');"
                ,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE PWalking (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer, cIndex7 Integer, cIndex8 Integer, " +
                "   cIndex9 Integer, cIndex10 Integer, cIndex11 Integer);");
        sql = String.format("INSERT INTO PWalking VALUES(NULL,'%s','%s','%s','%s','%s','%s'" +
                        ",'%s','%s','%s','%s','%s');"
                ,0,0,0,0,0,0,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Light (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer," +
                "   cIndex5 Integer, cIndex6 Integer);");
        sql = String.format("INSERT INTO Light VALUES(NULL,'%s','%s','%s','%s','%s','%s');"
                ,0,0,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Hearing (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer);");
        sql = String.format("INSERT INTO Hearing VALUES(NULL,'%s','%s','%s','%s');"
                ,0,0,0,0);
        db.execSQL(sql);
        db.execSQL("CREATE TABLE Young (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   cIndex1 Integer, cIndex2 Integer, cIndex3 Integer, cIndex4 Integer);");
        sql = String.format("INSERT INTO Young VALUES(NULL,'%s','%s','%s','%s');"
                ,0,0,0,0);
        db.execSQL(sql);
    }
    //버전이 업데이트되면 DB를 다시 만듭니다.

    public void onReCreate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS Public");
        db.execSQL("DROP TABLE IF EXISTS Auditorium");
        db.execSQL("DROP TABLE IF EXISTS Park");
        db.execSQL("DROP TABLE IF EXISTS Ptoilet");
        db.execSQL("DROP TABLE IF EXISTS Attraction");
        db.execSQL("DROP TABLE IF EXISTS Bank");
        db.execSQL("DROP TABLE IF EXISTS Others");
        db.execSQL("DROP TABLE IF EXISTS Hospital");
        db.execSQL("DROP TABLE IF EXISTS Store");
        db.execSQL("DROP TABLE IF EXISTS Station");
        db.execSQL("DROP TABLE IF EXISTS Restaurant");
        db.execSQL("DROP TABLE IF EXISTS BShop");
        db.execSQL("DROP TABLE IF EXISTS Showroom");
        db.execSQL("DROP TABLE IF EXISTS Religion");
        db.execSQL("DROP TABLE IF EXISTS Gym");
        db.execSQL("DROP TABLE IF EXISTS UpdatedDay");
        db.execSQL("DROP TABLE IF EXISTS Room");

        db.execSQL("CREATE TABLE Public (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Auditorium (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Park (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Ptoilet  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Attraction (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Bank  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Others (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Hospital  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Store (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Station  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Restaurant (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE BShop  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Showroom (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Religion  (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Gym (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE Room (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   NAME TEXT, TEL TEXT, HP TEXT, ADDRESS TEXT, BIZHOUR TEXT, REST TEXT," +
                "   INFORMATION TEXT, INFOMATI_1 TEXT, INFO_BLE_1 TEXT, INFO_BLE_2 TEXT," +
                "   INFO_BLE_3 TEXT, INFO_BLE_4 TEXT, INFO_BLE_5 TEXT, INFO_BLE_6 TEXT," +
                "   INFO_BLE_7 TEXT, INFO_BLE_8 TEXT, INFO_BLE_9 TEXT, INFO_BLE_10 TEXT," +
                "   INFO_BLE_11 TEXT, INFO_BLE_12 TEXT, INFO_BLE_13 TEXT, INFO_BLE_14 TEXT," +
                "   INFO_B2_1 TEXT, INFO_B12_1 TEXT, BOARD_LIST TEXT ,XX Real, YY Real, Data1 TEXT," +
                "Data2 TEXT, Data3 TEXT, Data4 TEXT, Data5 TEXT, Data6 TEXT);");

        db.execSQL("CREATE TABLE UpdatedDay (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   Date Integer);");
        String sql = String.format("INSERT INTO UpdatedDay VALUES(NULL,'%s');",0);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Public");
        db.execSQL("DROP TABLE IF EXISTS Auditorium");
        db.execSQL("DROP TABLE IF EXISTS Park");
        db.execSQL("DROP TABLE IF EXISTS Ptoilet");
        db.execSQL("DROP TABLE IF EXISTS Attraction");
        db.execSQL("DROP TABLE IF EXISTS Bank");
        db.execSQL("DROP TABLE IF EXISTS Others");
        db.execSQL("DROP TABLE IF EXISTS Hospital");
        db.execSQL("DROP TABLE IF EXISTS Store");
        db.execSQL("DROP TABLE IF EXISTS Station");
        db.execSQL("DROP TABLE IF EXISTS Restaurant");
        db.execSQL("DROP TABLE IF EXISTS BShop");
        db.execSQL("DROP TABLE IF EXISTS Showroom");
        db.execSQL("DROP TABLE IF EXISTS Religion");
        db.execSQL("DROP TABLE IF EXISTS Gym");
        db.execSQL("DROP TABLE IF EXISTS UpdatedDay");
        db.execSQL("DROP TABLE IF EXISTS Room");
        onCreate(db);
    }
}