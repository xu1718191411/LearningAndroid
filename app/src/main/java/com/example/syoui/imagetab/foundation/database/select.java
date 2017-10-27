package com.example.syoui.imagetab.foundation.database;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class select extends AppCompatActivity {
    String TABLE_CONTACTS = "member";
    Dialog dialog;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        SQLiteOpenHelperLocal helper = new SQLiteOpenHelperLocal(this,true);
        database = helper.openDataBase();


        List<Member> ml = searchData(database);

        ListView listView = (ListView) findViewById(R.id.listView);

        _IconListAdapter mAdapter = new _IconListAdapter(this, R.layout.sample_member_list, ml);
        listView.setAdapter(mAdapter);



        Button button = (Button) findViewById(R.id.news);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.select_add_new_layout);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialog.show();
                Button _submit = (Button) dialog.findViewById(R.id.confirmButton);

                _submit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        EditText newName = (EditText) dialog.findViewById(R.id.newName);
                        String strName = newName.getText().toString();

                        EditText newMail = (EditText) dialog.findViewById(R.id.newMail);
                        String strMail = newMail.getText().toString();

                        EditText newTel = (EditText) dialog.findViewById(R.id.newTel);
                        String strTel = newTel.getText().toString();


                        Log.i("strName",strName);
                        Log.i("strMail",strMail);
                        Log.i("strTel",strTel);
                        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.sexGroup);

                        insertData(strName,radioGroup.getCheckedRadioButtonId(),strTel,strMail,database);

                        dialog.dismiss();

                        updateListView();
                    }
                });


            }
        });

    }



    private void updateListView(){
        List<Member> ml = searchData(database);

        ListView listView = (ListView) findViewById(R.id.listView);

        _IconListAdapter mAdapter = new _IconListAdapter(this, R.layout.sample_member_list, ml);
        listView.setAdapter(mAdapter);
    }

    ArrayList<Member> searchData(SQLiteDatabase db){
        // Cursorを確実にcloseするために、try{}～finally{}にする
        Cursor cursor = null;
        try{
            //SQL文
            String sql = "SELECT * FROM member";

            //SQL文の実行
            cursor = db.rawQuery(sql,null);

            // 検索結果をcursorから読み込んで返す
            return readCursor( cursor );
        }
        finally{
            // Cursorを忘れずにcloseする
            if( cursor != null ){
                cursor.close();
            }
        }
    }



    /** 検索結果の読み込み */
    private ArrayList<Member> readCursor(Cursor cursor ){
        //カーソル開始位置を先頭にする
        cursor.moveToFirst();

        ArrayList<Member> memberList = new ArrayList<>(cursor.getCount());
        for (int i = 1; i <= cursor.getCount(); i++) {
            //SQL文の結果から、必要な値を取り出す
            Member m = new Member(cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4));
            memberList.add(m);
            cursor.moveToNext();
        }
        return memberList;
    }



    public void insertData(String name, int sex,String tel,String mail,SQLiteDatabase db){
        String sql = "insert into member (name,sex,tel, mail) values(?,?,?,?);";
        Log.i("selectNum",sex+"");
        Object[] bindStr = new Object[]{
                name.toString(),
                sex,
                tel.toString(),
                mail.toString()
        };
        try {
            db.execSQL(sql, bindStr);
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }



}



class _IconListAdapter  extends ArrayAdapter<Member> {

    private int mResource;
    private List<Member> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public _IconListAdapter(Context context, int resource, List<Member> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        Member item = mItems.get(position);


        // タイトルを設定
        TextView title = (TextView)view.findViewById(R.id.name);
        title.setText(item.getName());


        TextView sex = (TextView)view.findViewById(R.id.sex);

        if(item.getSex() == 1){
            sex.setText("男性");
        }else{
            sex.setText("女性");
        }


        TextView tel = (TextView)view.findViewById(R.id.tel);
        tel.setText(item.getTel());

        TextView mail = (TextView)view.findViewById(R.id.mail);
        mail.setText(item.getMail());
        //ImageView im = (ImageView)view.findViewById(R.id.onePieceId);
        //im.setImageResource(this.getContext().getResources().getIdentifier(item.getImageName(),"drawable",  this.getContext().getPackageName()));

        return view;
    }


}




class Member {
    private String name;
    private int sex;
    private String tel;
    private String mail;

    Member(String _name,int _sex,String _tel,String _mail){
        name = _name;
        sex = _sex;
        tel = _tel;
        mail = _mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }
}


class SQLiteOpenHelperLocal extends SQLiteOpenHelper {

    private static final String TAG = "myTag";

    // データベースパスとファイル名
    public static final String DB_PATH = "/data/data/com.example.syoui.imagetab/databases/";
    public static final String DB_NAME = "membersList.db";

    public static final String DB_NAME_OUT = "membersList_out.db";


    private Context context;
    private SQLiteDatabase db;

    public SQLiteOpenHelperLocal(Context context, boolean copyflag) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        // DBが存在しない場合にシステムパス上にDBを作成
        if(copyflag){	//DBをコピーする場合のみ	 こっちは本来必要ないかも・・・
            if(isExists()){
                Log.d(TAG, "MySQLiteOpenHelper isExists:true");
                // updateDB();// テスト時に利用(強制リフレッシュ)
            }else{
                Log.d(TAG, "MySQLiteOpenHelper isExists:false");
                updateDB();// DBを作成
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "MySQLiteOpenHelper onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d(TAG, "MySQLiteOpenHelper onUpgrade");
    }

    @Override
    public synchronized void close(){
        super.close();
        Log.d(TAG, "MySQLiteOpenHelper close");
        if(db != null)
            db.close();
    }

    // DBファイルの初期化
    public void updateDB(){


        //アプリのデフォルトシステムパスに作られる
        //this.getReadableDatabase();
        //assetに格納したデータベースをコピーする
        copyDatabaseFromAsset();
    }

    // DBの存在を確認する
    public boolean isExists(){
        boolean exist = false;
        String dbPath = DB_PATH + DB_NAME_OUT;

        File file = new File(dbPath);
        if(file.exists()){
            try{
                db = SQLiteDatabase.openDatabase(dbPath, null,
                        SQLiteDatabase.OPEN_READONLY);
                if(db != null)
                    db.close();
                exist = true;
            }catch(SQLiteException e){
                Log.d(TAG, "SQLE:" + e.toString());
                exist = false;
            }
        }else{
            exist = false;
        }

        return exist;
    }

    // assetに格納したDBをデフォルトのDBパスに作成し、コピーする
    private void copyDatabaseFromAsset(){
        try{
            //富士通系一部端末においてデフォルトパスを作成出来ない問題あり
            File databaseDir = new File(DB_PATH);
            if (!databaseDir.exists()) databaseDir.mkdirs();

            // asset内のDBにアクセス
            InputStream is = context.getAssets().open(DB_NAME);

            // デフォルトのDBパスに作成した空のDB
            String dbPath = DB_PATH + DB_NAME_OUT;
            OutputStream os = new FileOutputStream(dbPath);

            // コピー
            byte[] buffer = new byte[1024];
            int size;
            while((size = is.read(buffer)) > 0){
                os.write(buffer, 0, size);
            }

            // クローズ
            is.close();
            os.flush();
            os.close();
            Log.d(TAG,"DB Copy:success");
        }catch(IOException e){
            Log.e(TAG, "IOE:" + e.toString());
            Log.d(TAG, "DB Copy:failed");
        }
    }

    // DBを開く
    public SQLiteDatabase openDataBase(){
        try{
            String dbPath = DB_PATH + DB_NAME_OUT;
            db = SQLiteDatabase.openDatabase(
                    dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            Log.d(TAG, "SQLE:" + e.toString());
        }
        return db;
    }
}
