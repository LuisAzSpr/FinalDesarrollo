package org.example;

public abstract class CondicionClimatica {
    protected String entrada;
    protected int valor;

    protected String estado;
    protected CondicionClimatica(String entrada,String unidades){
        this.entrada = entrada;
        this.valor = Calculator.calcularValor(entrada,unidades);
        this.estado = calcularEstado();
    }

    // El calculo del estado varia dependiendo de la clase, por lo que este sera un
    // metodo abstract que sera implemetando por cada clase en particular
    public abstract String calcularEstado();

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

    public String getEstado() {
        return estado;
    }
}
