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
	private static final String TABLE_PRODUTOS 	= "produtos";
	private static final String TABLE_LOCAL 	= "local";

	// Tabela produtos
	static final String KEY_PRODUTOS_ID = "_id";
	static final String KEY_PRODUTOS_DESCRICAO = "descricao";
	static final String KEY_PRODUTOS_VALOR = "valor";
	static final String KEY_PRODUTOS_LOCAL = "local";
	
	// Tabela local
	
	static final String KEY_LOCAL_ID 	= "_id";
	static final String KEY_LOCAL_COD 	= "code_local";
	static final String KEY_LOCAL_NOME 	= "nome_local";
	
		
	public DataBaseHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PRODUTOS_TABLE = "CREATE TABLE " + TABLE_PRODUTOS + "("
				+ KEY_PRODUTOS_ID + " INTEGER PRIMARY KEY," 
				+ KEY_PRODUTOS_DESCRICAO + " TEXT, "
				+ KEY_PRODUTOS_VALOR + " DOUBLE, "
				+ KEY_PRODUTOS_LOCAL + " TEXT)";
		db.execSQL(CREATE_PRODUTOS_TABLE);
		
		
		String CREATE_LOCAL_TABLE = "CREATE TABLE " + TABLE_LOCAL + "("
				+ KEY_LOCAL_ID + " INTEGER PRIMARY KEY," 
				+ KEY_LOCAL_COD + " TEXT, "
				+ KEY_LOCAL_NOME + " TEXT)";
		db.execSQL(CREATE_LOCAL_TABLE);

			
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
                    public long criaProdutos(String d, double v, String l) {
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
                   
                   
                 //--------------LOCAL-------------------------//  
                   
                 //Cria local
                   public long criaLocal(String c, String n) {
                   SQLiteDatabase db = this.getWritableDatabase();
                   
                   ContentValues values = new ContentValues();
                   values.put(KEY_LOCAL_COD, c);
                   values.put(KEY_LOCAL_NOME, n);
                   
                   // Inserting Row
                   long id = db.insert(TABLE_LOCAL, null, values);
                   return id;
                   }
	
                   //exibe local
                   public String exibeLocal() {
                       
                       String selectQuery = 
                               "SELECT * FROM "+TABLE_LOCAL+" WHERE "+KEY_LOCAL_ID+"='1' LIMIT 1";

                           SQLiteDatabase db = this.getWritableDatabase();
                           Cursor cursor = db.rawQuery(selectQuery, null);

                           StringBuilder sb = new StringBuilder();

                           cursor.moveToFirst();

                           /*********** Fazer isto por cada coluna ***************/
                           String nome_da_coluna_string = cursor.getString(cursor.getColumnIndex(KEY_LOCAL_COD));

                           sb.append(nome_da_coluna_string);
                           /******************************************************/

                           cursor.close();

                           return sb.toString();

               }
	}
