package com.nicolasfanin.IUASampleApp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nicolasfanin.IUASampleApp.data.Mail;
import com.nicolasfanin.IUASampleApp.data.User;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static MyDatabase databaseInstance;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MyDatabase.db";

    //Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MAILS = "mails";

    //User Table Columns
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_PROFILE_URL = "user_profile";
    private static final String KEY_USER_PASSWORD = "user_password";

    //Mail Table Columns
    private static final String KEY_MAIL_ID = "mail_id";
    private static final String KEY_MAIL_FROM = "mail_from";
    private static final String KEY_MAIL_TO = "mail_to";
    private static final String KEY_MAIL_SUBJECT = "mail_subject";
    private static final String KEY_MAIL_CONTENT = "mail_content";
    private static final String KEY_MAIL_DATE = "mail_date";

    //Singleton Pattern
    public static synchronized MyDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new MyDatabase(context);
        }
        return databaseInstance;
    }

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Aquí es donde tenemos que escribir create table ya que se llama cuando se crea la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT, "
                + KEY_USER_PROFILE_URL + " TEXT, "
                + KEY_USER_PASSWORD + " TEXT)";

        String CREATE_MAIL_TABLE = "CREATE TABLE " + TABLE_MAILS + "("
                + KEY_MAIL_ID + " INTEGER PRIMARY KEY,"
                + KEY_MAIL_FROM + " TEXT,"
                + KEY_MAIL_TO + " TEXT, "
                + KEY_MAIL_SUBJECT + " TEXT, "
                + KEY_MAIL_CONTENT + " TEXT, "
                + KEY_MAIL_DATE + " TEXT)";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_MAIL_TABLE);
    }

    // Este método se llama cuando la base de datos se actualiza como modificar la estructura de la tabla,
    // agregando restricciones a la base de datos, etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAILS);
        onCreate(db);
    }

    /**
     * CODIGO QUE INSERTA, ACTUALIZA, OBTIENE Y BORRA UN MAIL:
     */
    public long addUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_PROFILE_URL, user.getProfile());
        values.put(KEY_USER_PASSWORD, user.getPassword());

        return database.insert(TABLE_USERS, null, values);
    }

    public long checkUser(String user, String pass) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_USERS, null, KEY_USER_NAME + "=? AND " + KEY_USER_PASSWORD + "=?", new String[]{user, pass}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(0);
        }
        return 0;
    }
    /**
     * CODIGO QUE INSERTA, ACTUALIZA, OBTIENE Y BORRA UN MAIL:
     */
    public long addMail(Mail mail) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAIL_FROM, mail.getFrom());
        values.put(KEY_MAIL_TO, mail.getTo());
        values.put(KEY_MAIL_SUBJECT, mail.getSubject());
        values.put(KEY_MAIL_CONTENT, mail.getContent());
        values.put(KEY_MAIL_DATE, mail.getDate());

        return database.insert(TABLE_MAILS, null, values);
    }

    public Mail getMail(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_MAILS, null, KEY_MAIL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Mail(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));
    }

    public List<Mail> getMails() {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_MAILS, null, null, null, null, null, null);

        List<Mail> mailList = new ArrayList<>();
        while (cursor.moveToNext()) {
            mailList.add(new Mail(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
        }
        return mailList;
    }

    public int updateMail(Mail mail) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAIL_CONTENT, mail.getContent());
        values.put(KEY_MAIL_SUBJECT, mail.getSubject());

        //Where clause.
        String[] argument = {mail.getDate()};

        return database.update(TABLE_MAILS, values, KEY_MAIL_DATE + " = ", argument);
    }

    public void deleteMail(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String[] selectionArgument = {String.valueOf(id)};

        database.delete(TABLE_MAILS, KEY_MAIL_ID + " = ", selectionArgument);
    }
}
