package com.mgogracon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("SimpleDateFormat")
public class DataBaseHandler extends SQLiteOpenHelper {
	Funcoes funcoes = new Funcoes();
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "menu";

	// Tabelas
	private static final String TABLE_PRODUTOS = "produtos";

	// Tabela usuário
	static final String KEY_PRODUTOS_ID = "_id";
	static final String KEY_PRODUTOS_DESCRICAO = "descricao";
	static final String KEY_PRODUTOS_VALOR = "valor";
	static final String KEY_PRODUTOS_LOCAL = "local";
		
	public DataBaseHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_PRODUTOS + "("
				+ KEY_PRODUTOS_ID + " INTEGER PRIMARY KEY," 
				+ KEY_PRODUTOS_DESCRICAO + " TEXT, "
				+ KEY_PRODUTOS_VALOR + " DOUBLE, "
				+ KEY_PRODUTOS_LOCAL + " INT)";
		db.execSQL(CREATE_USER_TABLE);

			
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
	// Create tables again
	onCreate(db);
	
	}

	//--------------produtos-------------------------//
    //Cria Produtos
                    public long criaProdutos(String d, double v, int l) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    
                    ContentValues values = new ContentValues();
                    values.put(KEY_PRODUTOS_DESCRICAO, d);
                    values.put(KEY_PRODUTOS_VALOR, v);
                    values.put(KEY_PRODUTOS_LOCAL, l);

                    // Inserting Row
                    long id = db.insert(TABLE_PRODUTOS, null, values);
                    return id;
                    }
	
     //Listar Produtos
                   public Cursor listarProdutos() {
                        
                String selectQuery = "SELECT * FROM "+TABLE_PRODUTOS+" ORDER BY "+KEY_PRODUTOS_ID+" ASC";

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor c = db.rawQuery(selectQuery, null);
                         if (c != null) {
                          c.moveToFirst();
                         }
                         db.close();
                         return c;
                
                }
           
            //conta Produtos      
                   public int contaProdutos() {

                       Cursor c = null;
                       SQLiteDatabase db = this.getWritableDatabase();
                   try {
                                   c = db.rawQuery("select count(*) from " + TABLE_PRODUTOS, null);
                               if (c.moveToFirst()) {
                                   return c.getInt(0);
                               }
                       return 0;
                   }
                   finally {
                       if (c != null) {
                           c.close();
                       }
                       if (db != null) {
                           db.close();
                       }
                   }
                       
               }            
	}
