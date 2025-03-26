import android.R
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        const val DATABASE_NAME = "kullanicilar.db"
        const val TABLE_NAME = "kullanici_tablosu"
        const val COL_1 = "ID"
        const val COL_2 = "kullanici_adi"
        const val COL_3 = "sifre"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_2 TEXT, $COL_3 TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun dataEkle(kullanici_adi: String, sifre: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, kullanici_adi)
        contentValues.put(COL_3, sifre)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun kullaniciyi_kontrol_et(kullanici_adi: String, sifre: String): Boolean{
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_2 = ? AND $COL_3 = ?"
        val cursor = db.rawQuery(query, arrayOf(kullanici_adi,sifre))

        val kullaniKontrol = cursor.count > 0
        cursor.close()
        return kullaniKontrol
    }


}