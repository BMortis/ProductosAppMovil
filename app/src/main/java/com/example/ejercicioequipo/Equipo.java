package com.example.ejercicioequipo;

public class Equipo {
    private int serie;
    private String descripcion;
    private int valor;
    private int tipo;

    //region getters y setters
    public int getSerie(){
        return this.serie;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public int getValor(){
        return this.valor;
    }

    public int getTipo(){
        return this.tipo;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setEquipo(int s, String d, int v, int t){
        this.serie = s;
        this.descripcion = d;
        this.valor = v;
        this.tipo = t;
    }
    //endregion
    @Override
    public String toString() {
        return "Equipo{" +
                "serie=" + serie +
                ", descripcion='" + descripcion + '\'' +
                ", valor=" + valor +
                ", tipo=" + tipo +
                '}';
    }
}
