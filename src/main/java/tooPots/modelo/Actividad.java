package tooPots.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class Actividad {

    private String id_actividad;
    private String nombre;
    private String lugar;
    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
    private LocalTime hora;
    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
    private LocalTime duracion;
    private String descripcion;
    private int precio;
    private int asistentesMinimos;
    private int asistentesMaximos;
    private String observaciones;
    private String estado;

    public Actividad() {
    }

    public String getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(String id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getAsistentesMinimos() {
        return asistentesMinimos;
    }

    public void setAsistentesMinimos(int asistentesMinimos) {
        this.asistentesMinimos = asistentesMinimos;
    }

    public int getAsistentesMaximos() {
        return asistentesMaximos;
    }

    public void setAsistentesMaximos(int asistentesMaximos) {
        this.asistentesMaximos = asistentesMaximos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
