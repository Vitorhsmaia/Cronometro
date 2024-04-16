package br.com.joaocarloslima;

public class Numero {
    private int limite;
    private int valor;

    public Numero(int limite) {
        this.limite = limite;
    }

    public void incrementar(){
        if(valor >= 0){
            valor++;
        }
    }

    public void decrementar(){
        if(valor > 0){
            valor--;
        }
        else{
            valor = 59;
        }
    }

    public String getValorFormatado(){
        if(valor < 10){
          String valorString = Integer.toString( valor);
            return "0" + valorString;
        }
        return Integer.toString(valor);
    }

    public int getLimite() {
        return limite;
    }
    public void setLimite(int limite) {
        this.limite = limite;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

}
