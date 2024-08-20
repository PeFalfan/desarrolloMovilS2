package com.example.appeventos;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String respuestaSecreta;

    public Usuario(String nombreUsuario, String contrasena, String preguntaSecreta, String respuestaSecreta) {
            this.nombreUsuario = nombreUsuario;
            this.contrasena = contrasena;
            this.respuestaSecreta = respuestaSecreta;
    }

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

}
