package com.gautomation.g_out_mqtt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gautomation.g_out_mqtt.PahoMqttClient;
import com.gautomation.g_out_mqtt.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //private AppBarConfiguration mAppBarConfiguration;
    int numerodeitem = 44;
    String[] Nome = new String[numerodeitem];
    String[] Unidade = new String[numerodeitem];
    TextView[] TextNome = new TextView[50];
    TextView[] variavel = new TextView[50];
    String[] Qtd_dig = new String[numerodeitem];
    int[] Tag = new int[numerodeitem];
    String[] ListaTag;

    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;
    private String clientid = "";
    private Timer myTimer;
    boolean flag;
    String MENSAGENS;
    String servidor, porta, usuario, senha, cliente, cabecalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        variavel[0] = (TextView) findViewById(R.id.txtValor1);
        variavel[1] = (TextView) findViewById(R.id.txtValor2);
        variavel[2] = (TextView) findViewById(R.id.txtValor3);
        variavel[3] = (TextView) findViewById(R.id.txtValor4);
        variavel[4] = (TextView) findViewById(R.id.txtValor5);
        variavel[5] = (TextView) findViewById(R.id.txtValor6);
        variavel[6] = (TextView) findViewById(R.id.txtValor7);
        variavel[7] = (TextView) findViewById(R.id.txtValor8);
        variavel[8] = (TextView) findViewById(R.id.txtValor9);
        variavel[9] = (TextView) findViewById(R.id.txtValor10);
        variavel[10] = (TextView) findViewById(R.id.txtValor11);
        variavel[11] = (TextView) findViewById(R.id.txtValor12);
        variavel[12] = (TextView) findViewById(R.id.txtValor13);
        variavel[13] = (TextView) findViewById(R.id.txtValor14);
        variavel[14] = (TextView) findViewById(R.id.txtValor15);
        variavel[15] = (TextView) findViewById(R.id.txtValor16);
        variavel[16] = (TextView) findViewById(R.id.txtValor17);
        variavel[17] = (TextView) findViewById(R.id.txtValor18);
        variavel[18] = (TextView) findViewById(R.id.txtValor19);
        variavel[19] = (TextView) findViewById(R.id.txtValor20);
        variavel[20] = (TextView) findViewById(R.id.txtValor21);
        variavel[21] = (TextView) findViewById(R.id.txtValor22);
        variavel[22] = (TextView) findViewById(R.id.txtValor23);
        variavel[23] = (TextView) findViewById(R.id.txtValor24);
        variavel[24] = (TextView) findViewById(R.id.txtValor25);
        variavel[25] = (TextView) findViewById(R.id.txtValor26);
        variavel[26] = (TextView) findViewById(R.id.txtValor27);
        variavel[27] = (TextView) findViewById(R.id.txtValor28);
        variavel[28] = (TextView) findViewById(R.id.txtValor29);
        variavel[29] = (TextView) findViewById(R.id.txtValor30);
        variavel[30] = (TextView) findViewById(R.id.txtValor31);
        variavel[31] = (TextView) findViewById(R.id.txtValor32);
        variavel[32] = (TextView) findViewById(R.id.txtValor33);
        variavel[33] = (TextView) findViewById(R.id.txtValor34);
        variavel[34] = (TextView) findViewById(R.id.txtValor35);
        variavel[35] = (TextView) findViewById(R.id.txtValor36);
        variavel[36] = (TextView) findViewById(R.id.txtValor37);
        variavel[37] = (TextView) findViewById(R.id.txtValor38);
        variavel[38] = (TextView) findViewById(R.id.txtValor39);
        variavel[39] = (TextView) findViewById(R.id.txtValor40);
        variavel[40] = (TextView) findViewById(R.id.txtValor41);
        variavel[41] = (TextView) findViewById(R.id.txtValor42);
        variavel[42] = (TextView) findViewById(R.id.txtValor43);
        variavel[43] = (TextView) findViewById(R.id.txtValor44);
        variavel[44] = (TextView) findViewById(R.id.txtValor45);
        variavel[45] = (TextView) findViewById(R.id.txtValor46);
        variavel[46] = (TextView) findViewById(R.id.txtValor47);
        variavel[47] = (TextView) findViewById(R.id.txtValor48);
        variavel[48] = (TextView) findViewById(R.id.txtValor49);
        variavel[49] = (TextView) findViewById(R.id.txtValor50);

        TextNome[0] = (TextView) findViewById( R.id.txtNome1 );
        TextNome[1] = (TextView) findViewById( R.id.txtNome2 );
        TextNome[2] = (TextView) findViewById( R.id.txtNome3 );
        TextNome[3] = (TextView) findViewById( R.id.txtNome4 );
        TextNome[4] = (TextView) findViewById( R.id.txtNome5 );
        TextNome[5] = (TextView) findViewById( R.id.txtNome6 );
        TextNome[6] = (TextView) findViewById( R.id.txtNome7 );
        TextNome[7] = (TextView) findViewById( R.id.txtNome8 );
        TextNome[8] = (TextView) findViewById( R.id.txtNome9 );
        TextNome[9] = (TextView) findViewById( R.id.txtNome10 );
        TextNome[10] = (TextView) findViewById( R.id.txtNome11 );
        TextNome[11] = (TextView) findViewById( R.id.txtNome12 );
        TextNome[12] = (TextView) findViewById( R.id.txtNome13 );
        TextNome[13] = (TextView) findViewById( R.id.txtNome14 );
        TextNome[14] = (TextView) findViewById( R.id.txtNome15 );
        TextNome[15] = (TextView) findViewById( R.id.txtNome16 );
        TextNome[16] = (TextView) findViewById( R.id.txtNome17 );
        TextNome[17] = (TextView) findViewById( R.id.txtNome18 );
        TextNome[18] = (TextView) findViewById( R.id.txtNome19 );
        TextNome[19] = (TextView) findViewById( R.id.txtNome20 );
        TextNome[20] = (TextView) findViewById( R.id.txtNome21 );
        TextNome[21] = (TextView) findViewById( R.id.txtNome22 );
        TextNome[22] = (TextView) findViewById( R.id.txtNome23 );
        TextNome[23] = (TextView) findViewById( R.id.txtNome24 );
        TextNome[24] = (TextView) findViewById( R.id.txtNome25 );
        TextNome[25] = (TextView) findViewById( R.id.txtNome26 );
        TextNome[26] = (TextView) findViewById( R.id.txtNome27 );
        TextNome[27] = (TextView) findViewById( R.id.txtNome28 );
        TextNome[28] = (TextView) findViewById( R.id.txtNome29 );
        TextNome[29] = (TextView) findViewById( R.id.txtNome30 );
        TextNome[30] = (TextView) findViewById( R.id.txtNome31 );
        TextNome[31] = (TextView) findViewById( R.id.txtNome32 );
        TextNome[32] = (TextView) findViewById( R.id.txtNome33 );
        TextNome[33] = (TextView) findViewById( R.id.txtNome34 );
        TextNome[34] = (TextView) findViewById( R.id.txtNome35 );
        TextNome[35] = (TextView) findViewById( R.id.txtNome36 );
        TextNome[36] = (TextView) findViewById( R.id.txtNome37 );
        TextNome[37] = (TextView) findViewById( R.id.txtNome38 );
        TextNome[38] = (TextView) findViewById( R.id.txtNome39 );
        TextNome[39] = (TextView) findViewById( R.id.txtNome40 );
        TextNome[40] = (TextView) findViewById( R.id.txtNome41 );
        TextNome[41] = (TextView) findViewById( R.id.txtNome42 );
        TextNome[42] = (TextView) findViewById( R.id.txtNome43 );
        TextNome[43] = (TextView) findViewById( R.id.txtNome44 );
        TextNome[44] = (TextView) findViewById( R.id.txtNome45 );
        TextNome[45] = (TextView) findViewById( R.id.txtNome46 );
        TextNome[46] = (TextView) findViewById( R.id.txtNome47 );
        TextNome[47] = (TextView) findViewById( R.id.txtNome48 );
        TextNome[48] = (TextView) findViewById( R.id.txtNome49 );
        TextNome[49] = (TextView) findViewById( R.id.txtNome50 );


        //mostra no edit as atuas edições
        SharedPreferences sharedservidor = getApplicationContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        servidor = sharedservidor.getString( "Servidor", "broker.hivemq.com");
        porta = sharedservidor.getString( "Porta", "1883");
        usuario = sharedservidor.getString( "Usuario", "eruyieu");
        senha = sharedservidor.getString( "Senha", "******");
        cliente = sharedservidor.getString("Cliente", "Cliente_G_AUT");
        cabecalho = sharedservidor.getString("Cabecalho", "estivas/");

        //================ Configurações MQTT ============================================
        clientid = cliente; // id do criente.

        Random aleatorio = new Random();
        int numeroID_1 = aleatorio.nextInt(100) + 1;
        int numeroID_2 = aleatorio.nextInt(100) + 1;
        int numeroID_3 = aleatorio.nextInt(100) + 1;

        pahoMqttClient = new PahoMqttClient();
        client = pahoMqttClient.getMqttClient(getApplicationContext(),// Connect to MQTT Broker
                "tcp://"+servidor+":"+porta,
                clientid + numeroID_1*numeroID_2/numeroID_3,
                usuario,
                senha
        );

        //mostra no edit as atuas edições
        SharedPreferences sharedtags = getApplicationContext().getSharedPreferences("Tags", Context.MODE_PRIVATE);
        for(int i = 0; i < numerodeitem; i ++){
            Tag[i] = sharedtags.getInt( "Tag"+i, i);
            Qtd_dig[i] = sharedtags.getString( "Qtd_dig"+i, "5");
        }
//        for(int i = 0; i < numerodeitem; i++) {
//            variavel[i].setFilters(new InputFilter[]{new InputFilter.LengthFilter(Integer.parseInt(Qtd_dig[i]))});
//        }
        ListaTag = getResources().getStringArray(R.array.Tags_array);
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
            TextNome[i].setText(Nome[i]);
           // TextUnidades[i].setText(Unidade[i]);
        }
        //Create listener for MQTT messages.
        mqttCallback();
        //Create Timer to report MQTT connection status every 1 second
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScheduleTasks();
            }

        }, 0, 2000);
        //================ Fim Configurações MQTT ===========================================
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_conf, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.confservidor) {
            Intent trocatela=new Intent(MainActivity.this,ActivityConfig.class);
            startActivity(trocatela);
            finish();
            return true;
        }
        if (id == R.id.renomear) {
            Intent trocatela=new Intent(MainActivity.this,ActivityRenomear.class);
            startActivity(trocatela);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void ScheduleTasks()
    {
        this.runOnUiThread(RunScheduledTasks);
    }
    private Runnable RunScheduledTasks = new Runnable() {
        public void run() {
            TextView status  = (TextView) findViewById(R.id.statusconexao);
            String msg_new="";

            if(pahoMqttClient.mqttAndroidClient.isConnected() ) {
                msg_new = "Conectado\r\n";
                status.setTextColor(0xFF00FF00); //Green if connected
                status.setTextSize( TypedValue.COMPLEX_UNIT_SP, 14);

                try {
                    for(int i = 0; i < numerodeitem; i++){
                        pahoMqttClient.subscribe(client, cabecalho+ListaTag[Tag[i]], 0);
                    }

                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            else {
                msg_new = "Desconectado\r\n";
                status.setTextColor(0xFFFF0000); //Red if not connected
                status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

            }
            status.setText(msg_new);

        }
    };

    // Called when a subscribed message is received
    protected void mqttCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                //msg("Connection lost...");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String msg = message.toString();
                //TextView tvMessage = (TextView) findViewById(R.id.subscribedMsg);
                String[] topc = new String[numerodeitem];
                for(int i = 0; i < numerodeitem; i++){
                    topc[i] = cabecalho+ListaTag[Tag[i]];
                }
                for(int i = 0; i < numerodeitem; i++){
                    if(topic.equals(topc[i])) {
                        variavel[i].setText(msg.substring(0,Integer.parseInt(Qtd_dig[i]))+" "+Unidade[i]);
                    }
                }

//                if(topic.equals("gerencia/CAL_01_PT")) {
//                    variavel[0].setText(msg);                }
//                else if(topic.equals("gerencia/CAL_01_FT")) {
//                    variavel[1].setText(msg);
//                }
//                else if(topic.equals("gerencia/CAL_01_TT")) {
//                    variavel[2].setText(msg);
//                }else{
//
//
//                    //String msg = message.toString();
//                    //tvMessage.append( msg);
//                    //String[] dados_recebidos = msg.split(",");
//
//
//
//                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

}