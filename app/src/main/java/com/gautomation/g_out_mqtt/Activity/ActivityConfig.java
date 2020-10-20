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

public class ActivityConfig extends AppCompatActivity {
    String servidor, porta, usuario, senha, cliente, cabecalho;
    EditText Servidor, Porta, Usuario, Senha, Cliente, Cabecalho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Servidor = (EditText) findViewById( R.id.editServidor );
        Porta = (EditText) findViewById( R.id.editPorta );
        Usuario = (EditText) findViewById( R.id.editUsuario );
        Senha = (EditText) findViewById( R.id.editSenha );
        Cliente = (EditText) findViewById( R.id.editcliente );
        Cabecalho = (EditText) findViewById( R.id.editcabecalho );

        //mostra no edit as atuas edições
        SharedPreferences sharedservidor = getApplicationContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        servidor = sharedservidor.getString( "Servidor", "broker.hivemq.com");
        porta = sharedservidor.getString( "Porta", "1883");
        usuario = sharedservidor.getString( "Usuario", "eruyieu");
        senha = sharedservidor.getString( "Senha", "******");
        cliente = sharedservidor.getString("Cliente", "Cliente_G_AUT");
        cabecalho = sharedservidor.getString("Cabecalho", "estivas/");

        Servidor.setText(servidor);
        Porta.setText(porta);
        Usuario.setText(usuario);
        Senha.setText(senha);
        Cliente.setText(cliente);
        Cabecalho.setText(cabecalho);
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
            Salvar_no_sharad(
                    Servidor.getText().toString(),
                    Porta.getText().toString(),
                    Usuario.getText().toString(),
                    Senha.getText().toString(),
                    Cliente.getText().toString(),
                    Cabecalho.getText().toString()
            );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Salvar_no_sharad (String Servidor, String Porta, String Usuario, String Senha, String Cliente, String Cabecalho){

        SharedPreferences sharedservidor = getApplicationContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedservidor.edit();

        editor.putString("Servidor", Servidor);
        editor.putString("Porta", Porta);
        editor.putString("Usuario", Usuario);
        editor.putString("Senha", Senha);
        editor.putString("Cliente", Cliente);
        editor.putString("Cabecalho", Cabecalho);

        editor.commit();

        //chama a proxima tela
        Intent trocatela=new Intent(ActivityConfig.this,MainActivity.class);
        startActivity(trocatela);
        finish();
        Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();
    }

}

