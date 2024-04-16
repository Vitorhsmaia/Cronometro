package br.com.joaocarloslima;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    @FXML private Label lblHora;
    @FXML private Label lblMinutos;
    @FXML private Label lblSegundos;
    @FXML private TextField txtMinutos;
    @FXML Button btnStart;
    @FXML Button btnStop;
    @FXML Slider sldSleepTimer;
    @FXML CheckBox chkRegresivo;

    private Timer timer;

    private int sleepTime = 1000;

    public void handleTextChange(){
        if (txtMinutos.getText().isEmpty()) {
            return;
        }
        timer.setTempo(Integer.parseInt(txtMinutos.getText()));
        atualizar();
    }

    public void handleStart(){
        timer.start();
        atualizar();
    }
    
    public void handleStop(){
        timer.stop();
        atualizar();
    }

    public void handleSleepTime(){
        sleepTime = (int) sldSleepTimer.getValue();
    }

    public void handleTick(){

        timer.tick();
        atualizar();
    }

    public void handleRegresivo(){
        timer.regresivo = chkRegresivo.isSelected();
    }

    public void clockThread(){
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                    Platform.runLater(() -> handleTick());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        timer = new Timer(0);
        clockThread();
        atualizar();
    }

    private void atualizar() {
        lblHora.setText(timer.hora.getValorFormatado());
        lblMinutos.setText(timer.minutos.getValorFormatado());
        lblSegundos.setText(timer.segundos.getValorFormatado());

        btnStart.setDisable(timer.ligado);
        btnStop.setDisable(!timer.ligado);
    }

}
