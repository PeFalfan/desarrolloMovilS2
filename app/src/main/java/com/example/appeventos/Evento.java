package com.example.appeventos;

public class Evento {
    private int id;
    private String titulo;
    private String fecha;
    private String importancia;
    private String observacion;
    private String lugar;
    private int tiempoAviso;
    private int usuarioId;

    public Evento(String titulo, String fecha, String importancia, String observacion, String lugar, int tiempoAviso, int usuarioId) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.importancia = importancia;
        this.observacion = observacion;
        this.lugar = lugar;
        this.tiempoAviso = tiempoAviso;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getTiempoAviso() {
        return tiempoAviso;
    }

    public void setTiempoAviso(int tiempoAviso) {
        this.tiempoAviso = tiempoAviso;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

}
