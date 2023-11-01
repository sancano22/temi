package com.example.prueba;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotemi.sdk.NlpResult;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.listeners.OnUserInteractionChangedListener;
import com.robotemi.sdk.listeners.OnConversationStatusChangedListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements
        OnRobotReadyListener,
        Robot.NlpListener,
        Robot.AsrListener,
        Robot.TtsListener,
        OnDetectionStateChangedListener,
        OnUserInteractionChangedListener,
        OnConversationStatusChangedListener,
        Robot.ConversationViewAttachesListener{
    public static final String ACTION_HOME_WELCOME="home.welcome";
    public static final String ACTION_HOME_DANCE="home.dance";
    public static final String ACTION_HOME_SLEEP="home.sleep";
    public static final String HOME_BASE_LOCATION="homebase";
    private static Robot mRobot;
    public TtsRequest ttsRequest;

    AnimationDrawable carita;
    ImageView cara;
    Button botonMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mRobot = Robot.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cara=(ImageView) findViewById(R.id.imagen);
        botonMsg=(Button) findViewById(R.id.boton);

        cara.setBackgroundResource(R.drawable.animacion);
        carita = (AnimationDrawable) cara.getBackground();
        carita.start();



        botonMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsRequest = TtsRequest.create("El Doctorado en Industria Inteligente de la PUCV,\n" +
                        "        se dispone para dotar al país de investigación interdisciplinaria en el campo de las ciencias de la ingeniería.\n" +
                        "        En consecuencia, los investigadores podrán ser capaces de liderar estudios interdisciplinarios en Ingeniería y dar solución a problemas claves de sectores productivos,\n" +
                        "        permitiéndole abordar desafíos en las áreas de vulnerabilidad y riesgos frente amenazas naturales, cambio climático, productividad, escasez hídrica y sustentabilidad\n" +
                        "        de la industria.", false);
                mRobot.speak(ttsRequest);
            }
        });

        /*ImageView btn=(ImageView) findViewById(R.id.imagen1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsRequest = TtsRequest.create("El Doctorado en Industria Inteligente de la PUCV,\n" +
                        "        se dispone para dotar al país de investigación interdisciplinaria en el campo de las ciencias de la ingeniería.\n" +
                        "        En consecuencia, los investigadores podrán ser capaces de liderar estudios interdisciplinarios en Ingeniería y dar solución a problemas claves de sectores productivos,\n" +
                        "        permitiéndole abordar desafíos en las áreas de vulnerabilidad y riesgos frente amenazas naturales, cambio climático, productividad, escasez hídrica y sustentabilidad\n" +
                        "        de la industria.", false);
                mRobot.speak(ttsRequest);
            }
        });*/

    }

    protected void onStart() {
        super.onStart();
        // Add robot event listeners
        mRobot.addOnRobotReadyListener(this);
        mRobot.addOnDetectionStateChangedListener(this);
        mRobot.addOnUserInteractionChangedListener(this);
        mRobot.addOnConversationStatusChangedListener(this);
        mRobot.addNlpListener(this);
        mRobot.addAsrListener(this);
        mRobot.addTtsListener(this);

    }


    @Override

    protected void onStop() {
        super.onStop();
        // Remove robot event listeners

        mRobot.removeOnRobotReadyListener(this);
        mRobot.removeOnDetectionStateChangedListener(this);
        mRobot.removeOnUserInteractionChangedListener(this);
        mRobot.removeOnConversationStatusChangedListener(this);
        mRobot.removeNlpListener(this);
        mRobot.removeAsrListener(this);
        mRobot.removeTtsListener(this);

    }


    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            try {
                final ActivityInfo activityinfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                mRobot.onStart(activityinfo);
                mRobot.hideTopBar(); // hide temi's top action bar when skill is active
                //Robot.getInstance().setDetectionModeOn(true, 2.0f);
                //Robot.getInstance().setTrackUserOn(true);

               // ttsRequest = TtsRequest.create("Hola, Mi nombre es temi, soy un  asistente robot", true);
               // mRobot.speak(ttsRequest);


                ttsRequest = TtsRequest.create("El Doctorado en Industria Inteligente de la PUCV,\n" +
                        "        se dispone para dotar al país de investigación interdisciplinaria en el campo de las ciencias de la ingeniería.\n" +
                        "        En consecuencia, los investigadores podrán ser capaces de liderar estudios interdisciplinarios en Ingeniería y dar solución a problemas claves de sectores productivos,\n" +
                        "        permitiéndole abordar desafíos en las áreas de vulnerabilidad y riesgos frente amenazas naturales, cambio climático, productividad, escasez hídrica y sustentabilidad\n" +
                        "        de la industria.", false);
                mRobot.speak(ttsRequest);

                //ttsRequest = TtsRequest.create("Si quieres información consulta las siguientes opciones del menú", true);
                //mRobot.speak(ttsRequest);


                //mRobot.askQuestion("Cómo te llamas?");


                //cargar tarjetas
                //https://github.com/kisahae/temirobotproject/blob/master/app/src/main/java/com/example/Temiproject/MainActivity.java
                //RECONOCER FACE
                //https://github.com/seungwon-yoo/kistApp1/blob/main/app/src/main/java/com/kist/kistapp1/MainActivity.java
            }catch (PackageManager.NameNotFoundException e){
                throw new RuntimeException(e);
            }

            /*ImageView Imagen = (ImageView) findViewById(R.id.imagen);
            Imagen.setBackgroundResource(R.drawable.animacion);
            AnimationDrawable gyroAnimation = (AnimationDrawable) Imagen.getBackground();
            gyroAnimation.start();

            mRobot.askQuestion("¿Qué información necesitas?");*/

        }
    }



    @Override
    public void onDetectionStateChanged(int state) {
        //final TextView textView=findViewById(R.id.detectionState);
        switch(state){
            case OnDetectionStateChangedListener.IDLE:
                //textView.setText("OnDetectionStateChanged: IDLE");

                break;
            case OnDetectionStateChangedListener.LOST:
                //textView.setText("OnDetectionStateChanged: LOST");
                break;

            case OnDetectionStateChangedListener.DETECTED: //deteccion facial
                //ttsRequest = TtsRequest.create("Cómo te encuentras hoy",true);
                //mRobot.speak(ttsRequest);

                //textView.setText("OnDetectionStateChanged: DETECTED");
                break;

            default:
                //textView.setText("OnDetectionStateChanged: UNKNOWN");
                break;
        }

    }

    @Override
    public void onUserInteraction(boolean isInteracting) {
        //final TextView textView=findViewById(R.id.userInteraction);
        if(isInteracting){
            //textView.setText("OnUserInteraction: TRUE");
        }else{
            //textView.setText("OnUserInteraction: FALSE");
        }
    }


    @Override
    public void onConversationStatusChanged(int status, @NotNull String text) {
        //final TextView textView = findViewById(R.id.conversationStatus);

        switch (status) {

            case LISTENING:
                //textView.setText("Status: LISTENING | Text: " + text);
                break;
            case THINKING:
                //textView.setText("Status: THINKING | Text: " + text);
                break;
            case SPEAKING:
                //textView.setText("Status: SPEAKING | Text: " + text);
                break;
            default:
                //textView.setText("Status: UNKNOWN | Text: " + text);
                break;
        }
    }

    @Override
    public void onNlpCompleted(@NotNull NlpResult nlpResult) {
        switch (nlpResult.action){
            case ACTION_HOME_WELCOME:
                mRobot.tiltAngle(23,5.3F);
                break;

            case ACTION_HOME_DANCE:
                long t=System.currentTimeMillis();
                long end=t+5000;
                while(System.currentTimeMillis()<end){
                    mRobot.skidJoy(0F,1F);
                }

                break;
            case ACTION_HOME_SLEEP:
                mRobot.goTo(HOME_BASE_LOCATION);

                break;


        }
    }

    @Override
    public void onConversationAttaches(boolean b){
 
    }

    @Override
    public void onAsrResult(@NotNull String s) {
        Log.d("onAsrResult","asrResult"+s);
    }




    @Override
    public void onTtsStatusChanged(@NotNull TtsRequest ttsRequest) {
         
    }
}