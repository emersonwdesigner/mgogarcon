package com.mgogracon;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ResultadoActivity extends Activity {
	TextView resultado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		
		Bundle bundle = this.getIntent().getExtras();

		String coluna = bundle.get("RES").toString();
		
		resultado = (TextView) findViewById(R.id.result);
        resultado.setText(coluna);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
