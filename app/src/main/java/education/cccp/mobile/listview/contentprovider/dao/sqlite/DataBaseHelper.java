package education.cccp.mobile.listview.contentprovider.dao.sqlite;

import static education.cccp.mobile.listview.contentprovider.models.Person.CREATE_TABLE_PERSON;
import static education.cccp.mobile.listview.contentprovider.models.Person.TABLE_PERSON;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/*
package education.cccp.tp9contentprovider;

import static android.database.sqlite.SQLiteDatabase.CursorFactory;
import static education.cccp.tp9contentprovider.Chapitre.CREATE_TABLE_CHAPITRE;
import static education.cccp.tp9contentprovider.Chapitre.TABLE_CHAPITRE;
import static education.cccp.tp9contentprovider.Person.CREATE_TABLE_PERSON;
import static education.cccp.tp9contentprovider.Person.TABLE_PERSON;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


// Cette classe nous fournis un helper(une classe d'aide)
// Elle contient deux méthodes qui font partis du cycle de vie
// de la classe SQLiteOpenHelper.
// Lorsque l'on ouvre la base de données en écriture
// alors la méthode onCreate(sqLiteDatabase) est appelé.
// De plus si la version(schéma) de la base de données change,
// alors c'est la méthode onUpgrade(sqLiteDatabase) qui est appelé.

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(@Nullable Context context,
                          @Nullable String name,
                          @Nullable CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CHAPITRE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion,
                          int newVersion) {
        sqLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS ")
                .append(TABLE_CHAPITRE)
                .toString());
        sqLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS ")
                .append(TABLE_PERSON)
                .toString());
        onCreate(sqLiteDatabase);
    }
}
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String BASE_CONTENT_URI = "content://";
    public static final int VERSION = 1;
    public static final int NO_URI_RESOURCE_ID_FOUND_RESULT = -1;
    public static final String NAME_DB = "chapitre.db";
    public DataBaseHelper(@Nullable Context context,
                          @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS ")
                .append(TABLE_PERSON)
                .toString());
        onCreate(sqLiteDatabase);
    }
}
