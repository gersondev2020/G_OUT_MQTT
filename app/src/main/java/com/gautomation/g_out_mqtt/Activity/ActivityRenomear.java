package com.gautomation.g_out_mqtt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.gautomation.g_out_mqtt.R;

public class ActivityRenomear extends AppCompatActivity {
    int numerodeitem = 10;
    String[] Nome = new String[numerodeitem];
    String[] Unidade = new String[numerodeitem];
    EditText[] EditNomes = new EditText[numerodeitem];
    EditText[] EditUnidades = new EditText[numerodeitem];
    String[] rotulos = new String[numerodeitem];
    String[] rotulosUnd = new String[numerodeitem];
    int[] Tag = new int[numerodeitem];
    String[] ListaTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renomear);

        EditNomes[0] = (EditText) findViewById( R.id.editTag1);
        EditNomes[1] = (EditText) findViewById( R.id.editTag2);
        EditNomes[2] = (EditText) findViewById( R.id.editTag3);
        EditNomes[3] = (EditText) findViewById( R.id.editTag4);
        EditNomes[4] = (EditText) findViewById( R.id.editTag5);
        EditNomes[5] = (EditText) findViewById( R.id.editTag6);
        EditNomes[6] = (EditText) findViewById( R.id.editTag7);
        EditNomes[7] = (EditText) findViewById( R.id.editTag8);
        EditNomes[8] = (EditText) findViewById( R.id.editTag9);
        EditNomes[9] = (EditText) findViewById( R.id.editTag10);
        EditUnidades[0] = (EditText) findViewById( R.id.editunidade1 );
        EditUnidades[1] = (EditText) findViewById( R.id.editunidade2 );
        EditUnidades[2] = (EditText) findViewById( R.id.editunidade3 );
        EditUnidades[3] = (EditText) findViewById( R.id.editunidade4 );
        EditUnidades[4] = (EditText) findViewById( R.id.editunidade5 );
        EditUnidades[5] = (EditText) findViewById( R.id.editunidade6 );
        EditUnidades[6] = (EditText) findViewById( R.id.editunidade7 );
        EditUnidades[7] = (EditText) findViewById( R.id.editunidade8 );
        EditUnidades[8] = (EditText) findViewById( R.id.editunidade9 );
        EditUnidades[9] = (EditText) findViewById( R.id.editunidade10 );

        ListaTag = getResources().getStringArray(R.array.Tags_array);
        //mostra no edit as atuas edições
        SharedPreferences sharedtags = getApplicationContext().getSharedPreferences("Tags", Context.MODE_PRIVATE);
        for(int i = 0; i < numerodeitem; i ++){
            Tag[i] = sharedtags.getInt( "Tag"+i, i);
        }

        //mostra no edit as atuas edições
        SharedPreferences sharedNomes = getApplicationContext().getSharedPreferences("Renomear", Context.MODE_PRIVATE);
        for(int i = 0; i < numerodeitem; i ++){
            Nome[i] = sharedNomes.getString( "Nome"+i, ListaTag[Tag[i]]);
            if( Nome[i].contains("PT") ||  Nome[i].contains("KG")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "Kgf/m²");
            }else if(Nome[i].contains("TT") || Nome[i].contains("TEMPER")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "°C");
            }else if(Nome[i].contains("FT") || Nome[i].contains("VZ") || Nome[i].contains("VAZAO")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "m³/h");
            }else if(Nome[i].contains("RPM")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "RPM");
            }else if(Nome[i].contains("PH") || Nome[i].contains("NIVEL") || Nome[i].contains("LT")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "%");
            }else if(Nome[i].contains("POT") || Nome[i].contains("EXPO")){
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "KW");
            }else{
                Unidade[i] = sharedNomes.getString( "Unidade"+i, "Kgf/m²");
            }
        }
        for(int i = 0; i < numerodeitem; i ++){
            EditNomes[i].setText(Nome[i]);
            EditUnidades[i].setText(Unidade[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tags_e_salvar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.configtag) {
            Intent trocatela=new Intent(ActivityRenomear.this,Config_tags.class);
            startActivity(trocatela);
            finish();
            return true;
        }
        if (id == R.id.sairesalvar) {
            for(int i = 0; i < numerodeitem; i ++){
                rotulos[i] = EditNomes[i].getText().toString();
                rotulosUnd[i] = EditUnidades[i].getText().toString();
            }
            String [] salva = {
                    rotulos[0],
                    rotulos[1],
                    rotulos[2],
                    rotulos[3],
                    rotulos[4],
                    rotulos[5],
                    rotulos[6],
                    rotulos[7],
                    rotulos[8],
                    rotulos[9],
                    rotulosUnd[0],
                    rotulosUnd[1],
                    rotulosUnd[2],
                    rotulosUnd[3],
                    rotulosUnd[4],
                    rotulosUnd[5],
                    rotulosUnd[6],
                    rotulosUnd[7],
                    rotulosUnd[8],
                    rotulosUnd[9]
            };
            Salvar_no_sharad(salva);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Salvar_no_sharad (String [] rotulo){
        SharedPreferences sharedRenomear = getApplicationContext().getSharedPreferences("Renomear", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedRenomear.edit();

        for(int i = 0; i < numerodeitem; i ++){
            editor.putString("Nome"+i, rotulo[i]);
            editor.putString("Unidade"+i, rotulo[i+10]);
        }

        editor.commit();

        //chama a proxima tela
        Intent trocatela=new Intent(ActivityRenomear.this,MainActivity.class);
        startActivity(trocatela);
        finish();
        Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();
    }
}