package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.Pojos.Player;
import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Coleccion;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;
import com.android.lauracalvente.app4_poketri.Utils.bdd;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class questFragment extends Fragment {

    @BindView(R.id.btnAnswer3)
    Button btnAnswer3;
    @BindView(R.id.btnAnswer4)
    Button btnAnswer4;
    @BindView(R.id.btnAnswer2)
    Button btnAnswer2;
    @BindView(R.id.btnAnswer1)
    Button btnAnswer1;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.lblQuest)
    TextView lblQuest;
    @BindView(R.id.frgGame)
    RelativeLayout activityJuego;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;

    int num = 0;
    Random rnd = new Random();
    public int contador = 0;
    Player player;
    Manejador manejador;
    public Runnable tarea;
    Thread hiloSecundario;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/pokemon.ttf");
        lblQuest.setTypeface(custom_font);
        btnAnswer1.setTypeface(custom_font);
        btnAnswer2.setTypeface(custom_font);
        btnAnswer3.setTypeface(custom_font);
        btnAnswer4.setTypeface(custom_font);
        btnNext.setTypeface(custom_font);

        rellenarDatosPreguntaAleatoria();
        player = new Player("", "", "");
    }

    public static questFragment newInstance() {
        questFragment fragmento = new questFragment();
        return fragmento;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnAnswer3, R.id.btnAnswer4, R.id.btnAnswer2, R.id.btnAnswer1, R.id.btnNext})
    public void onViewClicked(View v) {
        //Si pulsamos siguiente recargamos la cuestion
        if (v.getId() == R.id.btnNext) {
            rellenarDatosPreguntaAleatoria();
            btnNext.setVisibility(View.INVISIBLE);
        }
        //Si la respuesta es correcta el boton siguiente se hace visible y el contador suma +1
        else if (((Button) v).getText().equals(bdd.quests[num][5])) {
            btnNext.setVisibility(View.VISIBLE);
            btnAnswer1.setEnabled(false);
            btnAnswer2.setEnabled(false);
            btnAnswer3.setEnabled(false);
            btnAnswer4.setEnabled(false);
            ((Button) v).setBackgroundColor(Color.DKGRAY);
            ((Button) v).setTextColor(Color.WHITE);
            contador++;
            //Paramos el time
            pararTime();
        }
        //Sino volvemos al principio
        else if (!((Button) v).getText().equals(bdd.quests[num][5])) {
            gameOver();
            //Paramos el time
            pararTime();
        }
    }

    //Nos rellena los datos que necesitamos en la vista
    private void rellenarDatosPreguntaAleatoria() {

        num = rnd.nextInt(19);

        lblQuest.setText(bdd.quests[num][0]);
        btnAnswer1.setText(bdd.quests[num][1]);
        btnAnswer2.setText(bdd.quests[num][2]);
        btnAnswer3.setText(bdd.quests[num][3]);
        btnAnswer4.setText(bdd.quests[num][4]);

        //Damos colores y formas :)
        btnAnswer1.setEnabled(true);
        btnAnswer2.setEnabled(true);
        btnAnswer3.setEnabled(true);
        btnAnswer4.setEnabled(true);
        btnAnswer1.setBackgroundColor(Color.TRANSPARENT);
        btnAnswer2.setBackgroundColor(Color.TRANSPARENT);
        btnAnswer3.setBackgroundColor(Color.TRANSPARENT);
        btnAnswer4.setBackgroundColor(Color.TRANSPARENT);
        btnAnswer1.setTextColor(Color.BLACK);
        btnAnswer2.setTextColor(Color.BLACK);
        btnAnswer3.setTextColor(Color.BLACK);
        btnAnswer4.setTextColor(Color.BLACK);

        iniciarTime();
    }

    public void gameOver(){
        //capturamos la time actual del sistema y la transformamos en string para el player
        //le otorgamos la score actual;
        //le damos el name tambien
        Date date = new Date();
        String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);

        player.time = fecha;
        player.score = String.format("%d", contador);
        player.name = MainActivity.namePlayer;

        //agregamos el player a la coleccion
        Coleccion.addPlayer(player);
        gameFragment.cargarOverFragment(String.format("Score: %d", contador));
    }

    //METODOS PARA EL POSGRESSBAR
    private void iniciarTime() {
        // Se crea el manejador.
        manejador = new Manejador( (MainActivity) getActivity(), progressBar);
        // Se crea la tarea secundaria.
        tarea = new TareaSecundaria();
        // Se crea el hilo y se inicia.
        hiloSecundario = new Thread(tarea);
        progressBar.setVisibility(View.VISIBLE);
        hiloSecundario.start();
    }

    private void pararTime(){
        Message msgFin = new Message();
        msgFin.what = Constantes.onPostExecute;
        msgFin.arg1 = Constantes.FRACTION;
        manejador.sendMessage(msgFin);
    }

    // Hace visibles las vistas relacionadas con el progreso.
    static public void mostrarBarra(ProgressBar p) {
        p.setVisibility(View.VISIBLE);
    }

    // Actualiza el valor de las barras de progreso.
    static public void actualizarBarras(int progreso, ProgressBar p) {
        p.setProgress(progreso);
    }

    // Resetea las vistas relacionadas con el progreso.
    static public void resetearVistas(ProgressBar p) {
        p.setVisibility(View.GONE);
        p.setProgress(0);
    }

    // Clase interna para la Tarea Secundaria.
    public class TareaSecundaria implements Runnable {

        @Override
        public void run() {
            iniciar();
            proceso();
            fin();
        }

        private void iniciar(){
            // Crea y envía el mensaje de inicio de ejecución al manejador.
            Message msgInicio = new Message();
            msgInicio.what = Constantes.onPreExecute;
            manejador.sendMessage(msgInicio);
        }

        private void proceso(){
            // Se realizan diez pasos.
            for (int i = 0; i < Constantes.FRACTION; i++) {
                // Se pone a trabajar.
                trabajar();
                // Crea y envía un mensaje de actualización al manejador.
                Message msgProgreso = new Message();
                msgProgreso.what = Constantes.onProgressUpdate;
                msgProgreso.arg1 = i + 1;
                manejador.sendMessage(msgProgreso);
            }
        }

        private void fin(){
            // Crea y envía el mensaje de fin de ejecución al manejador.
            Message msgFin = new Message();
            msgFin.what = Constantes.onPostExecute;
            msgFin.arg1 = Constantes.FRACTION;
            manejador.sendMessage(msgFin);
        }

        // Simula un trabajo de 1 segundo.
        private void trabajar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // Clase interna para el Manejador.
    static class Manejador extends Handler {

        private final WeakReference<MainActivity> mActivityWeakReference; // Referencia débil a la actividad. Las actividades no se deben guardar, ni el contexto tampoco.
        ProgressBar p;

        Manejador(MainActivity activity, ProgressBar p) {
            mActivityWeakReference = new WeakReference<>(activity);
            this.p = p;
        }

        // Debemos sobrescribir este método para recibir mensajes.
        @Override
        public void handleMessage(Message mensaje) {
            // Se obtiene la actividad (si aún existe).
            MainActivity activity = mActivityWeakReference.get();
            if (activity != null) {
                // Dependiendo del mensaje recibido.
                switch (mensaje.what) {
                    // Mensaje de inicio del hilo secundario.
                    case Constantes.onPreExecute:
                        // Se hacen visibles las vistas para el progreso.
                        questFragment.mostrarBarra(p);
                        break;
                    // Mensaje de progreso del hilo secundario.
                    case Constantes.onProgressUpdate:
                        // Se actualizan las barras.
                        int progreso = mensaje.arg1;
                        questFragment.actualizarBarras(progreso, p);
                        break;
                    // Mensaje de fin del hilo secundario.
                    case Constantes.onPostExecute:
                        // Se informa al usuario y se resetean las vistas.
                        int tareas = mensaje.arg1;
                        questFragment.resetearVistas(p);
                        break;
                }
            }
        }
    }
}

