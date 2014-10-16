package com.mgogracon;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	 
	  protected static final String ZXING_MARKET = "market://search?q=pname:com.google.zxing.client.android";
		protected static final String ZXING_DIRECT = "https://zxing.googlecode.com/files/BarcodeScanner3.1.apk";
	 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
		}
	 
	 
		/**
		 * Método que será chamado no clique do botão
		 * 
		 * @param view
		 */
		public void lerQR(View view) {
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	 
			try {
				startActivityForResult(intent, 0);
	 
			} catch (ActivityNotFoundException e) {
				mostrarMensagem();
			}
		}
	 
		/**
		 * Pergnta se o usuário deseja instalar o ZXing
		 */
		private void mostrarMensagem() {
			new AlertDialog.Builder(this)
					.setTitle("Instalar barcode scanner?")
					.setMessage("Para escanear QR code você precisa instalar o ZXing barcode scanner.")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("Instalar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ZXING_MARKET));
									try {
										startActivity(intent);
									} catch (ActivityNotFoundException e) { // Se não tiver o Play Store
										intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ZXING_DIRECT));
										startActivity(intent);
									}
								}
							})
					.setNegativeButton("Cancelar", null).show();
	 
		}
		
	    @Override
		public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
				String qrcode = intent.getStringExtra("SCAN_RESULT");
				
				TextView label = (TextView) findViewById(R.id.texto);
				label.setText(qrcode);			
			} 
		}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
