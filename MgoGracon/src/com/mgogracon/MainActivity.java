package com.mgogracon;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import net.sourceforge.zbar.Symbol;

public class MainActivity extends Activity {

	DataBaseHandler db = new DataBaseHandler(this);
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    TextView resultado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.resultado);
        resultado.setText("resultado");
        
        db.criaProdutos("Xis Salada", 5.5, 1);
        
        int conta = db.contaProdutos();
        Toast.makeText(this, String.valueOf(conta), Toast.LENGTH_SHORT).show();
        
    }

    public void launchScanner(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void launchQRScanner(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void listar(View v) {
    	Log.v("aviso","sim");
    	startActivity(new Intent(MainActivity.this, ListarProdutosActivity.class));
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                   // Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
                    resultado.setText(data.getStringExtra(ZBarConstants.SCAN_RESULT) + " ");
                    
                    Intent intent = new Intent(this, ResultadoActivity.class);
                    intent.putExtra("RES", data.getStringExtra(ZBarConstants.SCAN_RESULT));
                    startActivity(intent);
                    
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}