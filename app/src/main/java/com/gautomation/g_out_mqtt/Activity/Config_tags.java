package com.gautomation.g_out_mqtt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gautomation.g_out_mqtt.R;

public class Config_tags extends AppCompatActivity {
    int numerodeitem = 10;
    int[] Tag = new int[numerodeitem];
    String[] Qtd_dig = new String[numerodeitem];
    //EditText[] EditTag = new EditText[numerodeitem];
    Spinner[] spinner = new Spinner[numerodeitem];
    EditText[] EditQtd_dig = new EditText[numerodeitem];
    String[] salvaTag = new String[numerodeitem];
    String[] salvaQtd_dig = new String[numerodeitem];
    String[] ListaTag;
    String[] ListaTag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_tags);

        spinner[0] = (Spinner) findViewById( R.id.SpinnerTag1);
        spinner[1] = (Spinner) findViewById( R.id.SpinnerTag2);
        spinner[2] = (Spinner) findViewById( R.id.SpinnerTag3);
        spinner[3] = (Spinner) findViewById( R.id.SpinnerTag4);
        spinner[4] = (Spinner) findViewById( R.id.SpinnerTag5);
        spinner[5] = (Spinner) findViewById( R.id.SpinnerTag6);
        spinner[6] = (Spinner) findViewById( R.id.SpinnerTag7);
        spinner[7] = (Spinner) findViewById( R.id.SpinnerTag8);
        spinner[8] = (Spinner) findViewById( R.id.SpinnerTag9);
        spinner[9] = (Spinner) findViewById( R.id.SpinnerTag10);
        for(int i = 0; i < numerodeitem; i ++) {
            ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.Tags_array, R.layout.spinner_item);
            spinner[i].setAdapter(adapter1);
        }
        EditQtd_dig[0] = (EditText) findViewById( R.id.editunidade1 );
        EditQtd_dig[1] = (EditText) findViewById( R.id.editunidade2 );
        EditQtd_dig[2] = (EditText) findViewById( R.id.editunidade3 );
        EditQtd_dig[3] = (EditText) findViewById( R.id.editunidade4 );
        EditQtd_dig[4] = (EditText) findViewById( R.id.editunidade5 );
        EditQtd_dig[5] = (EditText) findViewById( R.id.editunidade6 );
        EditQtd_dig[6] = (EditText) findViewById( R.id.editunidade7 );
        EditQtd_dig[7] = (EditText) findViewById( R.id.editunidade8 );
        EditQtd_dig[8] = (EditText) findViewById( R.id.editunidade9 );
        EditQtd_dig[9] = (EditText) findViewById( R.id.editunidade10 );
        ListaTag = getResources().getStringArray(R.array.Tags_array);
        ListaTag2 = getResources().getStringArray(R.array.Tags_array2);
        //mostra no edit as atuas edições
        SharedPreferences sharedtags = getApplicationContext().getSharedPreferences("Tags", Context.MODE_PRIVATE);
        for(int i = 0; i < numerodeitem; i ++){
            Tag[i] = sharedtags.getInt( "Tag"+i, i);
            Qtd_dig[i] = sharedtags.getString( "Qtd_dig"+i, "5");
        }
        for(int i = 0; i < numerodeitem; i ++){
            spinner[i].setSelection(Tag[i]);
            EditQtd_dig[i].setText(Qtd_dig[i]);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salvar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sairesalvar) {
            for(int i = 0; i < numerodeitem; i ++){
                salvaTag[i] = String.valueOf(spinner[i].getSelectedItemPosition());
                salvaQtd_dig[i] = EditQtd_dig[i].getText().toString();
            }
            String [] salva = {
                    salvaTag[0],
                    salvaTag[1],
                    salvaTag[2],
                    salvaTag[3],
                    salvaTag[4],
                    salvaTag[5],
                    salvaTag[6],
                    salvaTag[7],
                    salvaTag[8],
                    salvaTag[9],
                    salvaQtd_dig[0],
                    salvaQtd_dig[1],
                    salvaQtd_dig[2],
                    salvaQtd_dig[3],
                    salvaQtd_dig[4],
                    salvaQtd_dig[5],
                    salvaQtd_dig[6],
                    salvaQtd_dig[7],
                    salvaQtd_dig[8],
                    salvaQtd_dig[9]
            };
            Salvar_no_sharad(salva);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Salvar_no_sharad (String [] rotulo){

        SharedPreferences sharedservidor = getApplicationContext().getSharedPreferences("Tags", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedservidor.edit();

        for(int i = 0; i < numerodeitem; i ++){
            editor.putInt("Tag"+i, Integer.parseInt(rotulo[i]));
            if(rotulo[i + 10].equals("") || rotulo[i + 10].equals("0")) {
                editor.putString("Qtd_dig" + i, "5");
            }else{
                editor.putString("Qtd_dig" + i, rotulo[i + 10]);
            }
        }
        editor.commit();
        SharedPreferences sharedRenomear = getApplicationContext().getSharedPreferences("Renomear", Context.MODE_PRIVATE);
        SharedPreferences.Editor renomear = sharedRenomear.edit();
        for(int i = 0; i < numerodeitem; i ++){
            if(Tag[i] != Integer.parseInt(rotulo[i]) ){
                renomear.putString("Nome"+i, ListaTag2[Integer.parseInt(rotulo[i])]);
                if(ListaTag[Integer.parseInt(rotulo[i])].contains("PT") || ListaTag[Integer.parseInt(rotulo[i])].contains("KG")){
                    renomear.putString("Unidade"+i, "Kgf/m²");
                }else if(ListaTag[Integer.parseInt(rotulo[i])].contains("TT") || ListaTag[Integer.parseInt(rotulo[i])].contains("TEMPER")){
                    renomear.putString("Unidade"+i, "°C");
                }else if(ListaTag[Integer.parseInt(rotulo[i])].contains("FT") || ListaTag[Integer.parseInt(rotulo[i])].contains("VZ")){
                    renomear.putString("Unidade"+i, "m³/h");
                }else if(ListaTag[Integer.parseInt(rotulo[i])].contains("RPM")){
                    renomear.putString("Unidade"+i, "RPM");
                }else if(ListaTag[Integer.parseInt(rotulo[i])].contains("PH") || ListaTag[Integer.parseInt(rotulo[i])].contains("NIVEL")
                        || ListaTag[Integer.parseInt(rotulo[i])].contains("LT")){
                    renomear.putString("Unidade"+i, "%");
                }else if(ListaTag[Integer.parseInt(rotulo[i])].contains("POT") || ListaTag[Integer.parseInt(rotulo[i])].contains("EXPO")){
                    renomear.putString("Unidade"+i, "KW");
                }else{
                    renomear.putString("Unidade"+i, "Kgf/m²");
                }
            }
        }
        renomear.commit();

        //chama a proxima tela
        Intent trocatela=new Intent(Config_tags.this,MainActivity.class);
        startActivity(trocatela);
        finish();
        Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();
    }
}