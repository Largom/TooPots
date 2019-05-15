package tooPots.modelo;

import java.util.ArrayList;
import java.util.List;

public class Clasificacion {
     private List<String> tipos;
     private List<String> niveles;
     private String id_tipo;
     private int id_nivel;
     private String descripcion_tipo;
     private String descripcion_nivel;

    public Clasificacion(){
    }
    
    
    // Tipo para Actividades
    public Clasificacion(String tipo, int nivel) {
        this.id_tipo = tipo;
        this.id_nivel = nivel;
    }
    
    
    // Tipo para Certificados
    public Clasificacion(List<String> tipo, List<String> nivel) {
        this.tipos = tipo;
        this.niveles = nivel;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    public List<String> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<String> niveles) {
        this.niveles = niveles;
    }

    public String getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(String id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public String getDescripcion_tipo() {
        return descripcion_tipo;
    }

    public void setDescripcion_tipo(String descripcion_tipo) {
        this.descripcion_tipo = descripcion_tipo;
    }

    public String getDescripcion_nivel() {
        return descripcion_nivel;
    }

    public void setDescripcion_nivel(String descripcion_nivel) {
        this.descripcion_nivel = descripcion_nivel;
    }
}
