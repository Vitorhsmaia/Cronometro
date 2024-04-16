package br.com.joaocarloslima;

public class Timer {
    Numero hora = new Numero(24);
    Numero minutos = new Numero(60);
    Numero segundos = new Numero(60);

    protected boolean regresivo = false;
    protected boolean ligado = false;

    public Timer(int minutos) {
        minutos = this.minutos.getValor();
    }

    public Timer() {
    }

    public void start() {
        ligado = true;
    }

    public void stop() {
        ligado = false;
    }

    public void setTempo(int minuto) {
        minutos.setValor(minuto);
    }

    public void setRegressivo(boolean regre) {
        this.regresivo = true;
    }

    public void tick() {
        // verifica se esta ligado
        if (ligado == true) {

            // verifica se e regresivo ou nao
            if (regresivo == true) {
                // Se minutos precisam ser decrementados
                if (minutos.getValor() > 0 && segundos.getValor() == 0) {
                    minutos.decrementar();
                }

                segundos.decrementar();

                // Se minutos chegarem a 0, decrementa horas
                if (minutos.getValor() == 0 && hora.getValor() > 0) {
                    minutos.setValor(59);
                    hora.decrementar();
                }

                // Se hora chegar a 0 e minutos e segundos forem 0, desliga o timer
                if (hora.getValor() == 0 && minutos.getValor() == 0 && segundos.getValor() == 0) {
                    ligado = false;
                }
            } else {
                segundos.incrementar();

                if (segundos.getValor() == 59) {
                    segundos.setValor(0);
                    minutos.incrementar();
                } else if (minutos.getValor() == 59) {
                    minutos.setValor(0);
                    hora.incrementar();
                } else if (hora.getValor() == 24) {
                    ligado = false;
                    segundos.setValor(0);
                    minutos.setValor(0);
                }
            }
        }
    }

    public Numero getHoras() {
        return hora;
    }

    public Numero getMinutos() {
        return minutos;
    }

    public Numero getSegundos() {
        return segundos;
    }

}
