package com.mgogracon;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;

public class ListarProdutosActivity extends Activity {
	DataBaseHandler db = new DataBaseHandler(this);
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	Log.v("aviso","0");
	String[] from = new String[] {DataBaseHandler.KEY_PRODUTOS_DESCRICAO, DataBaseHandler.KEY_PRODUTOS_VALOR};
    
    //Toast.makeText(getSherlockActivity(), from[0], Toast.LENGTH_SHORT).show();
// Ids of views in listview_layout
int[] to = { R.id.txtDescricao,R.id.txtValor};        

Cursor cursor = db.listarProdutos();
     
     
   @SuppressWarnings("deprecation")
SimpleCursorAdapter adapter =  new SimpleCursorAdapter(this, R.layout.list_produtos, cursor, from, to);
   ListView dataList = (ListView) findViewById(R.id.list);
   dataList.setAdapter(adapter);
    
    
	}
}
