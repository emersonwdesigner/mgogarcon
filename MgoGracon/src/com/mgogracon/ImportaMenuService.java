package com.mgogracon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.IBinder;
import android.util.Log;
import android.app.Service;
import android.content.Intent;
public class ImportaMenuService extends Service implements Runnable {

    
    DataBaseHandler db = new DataBaseHandler(this);
    String codigo;
    @Override
    public void onCreate() {
    	
    	codigo = db.exibeLocal();
    	
    	Log.v("aviso",codigo);
            new Thread(ImportaMenuService.this).start();
            
    }

    @Override
    public IBinder onBind(Intent arg0) {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public void run() {
          
    	Log.v("aviso","servico3");
            //busca dados no server
            HttpClient client = new DefaultHttpClient();
            
            final HttpPost post = new HttpPost("http://appoficina.atwebpages.com/garcon/listar.php");
            
    List<NameValuePair> pairs = new ArrayList<NameValuePair>();        
    pairs.add(new BasicNameValuePair("codigo", codigo));
    
    
        // Retorna o resultado
    
            try{
                    
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(pairs);
                    
                    // RESULTADOS DOS ITENS
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);  
            String responseBody = EntityUtils.toString(responsePOST.getEntity());
            Log.v("aviso",responseBody);
                    JSONObject json = new JSONObject(responseBody);

                 // Recupera a lista product do JSON
                JSONArray products = json.getJSONArray("product");

                int length = products.length();
                Log.v("RESPONSE","conta ITENS "+String.valueOf(length));
                int i =0;
                for( i = 0; i < length; ++i) {
                    JSONObject product = products.getJSONObject(i);
                    
                    String descricao 	= product.getString("descricao");
                    String local        = product.getString("codigo");
                    double valor        = product.getDouble("valor");
                            
                    db.criaProdutos(descricao, valor, local);
                    Log.v("RESPONSE","ITEN Inserido");
                }
                
                stopService(new Intent("MGO_GARCON_RESPOSTA"));
             
                Log.v("RESPONSE","Todos Inserido");
            }catch (ClientProtocolException e) {
                    
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
           
    }        


}

