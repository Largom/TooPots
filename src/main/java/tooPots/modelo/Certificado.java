package tooPots.modelo;

public class Certificado {

    private String id_certificado;
    private String nombre;
    private String descripcion;

    public Certificado() {
    }

    public String getId_certificado() {
        return id_certificado;
    }

    public void setId_certificado(String id_certificado) {
        this.id_certificado = id_certificado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
