package tooPots.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class Actividad {
	
    private int id_actividad;
    private String nombre;
    private String lugar;
    //@DateTimeFormat (pattern = "HH:mm:ss.SSS" )
    private final String hora = "";
    //@DateTimeFormat (pattern = "HH:mm:ss.SSS" )
    private final String duracion = "";
    private String descripcion;
    private int precio;
    private int asistentesMinimos;
    private int asistentesMaximos;
    private String observaciones;
    private String estado;
    private String tipo;
    private int nivel;
   /* private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
*/
	public Actividad() {
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
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

    public String getHora() {
        return hora;
    }

    public void setHora(String horastr) {
    	//String[] vecHora = horastr.split(":");
    	//LocalTime hora = LocalTime.of((Integer.parseInt(vecHora[0])), Integer.parseInt(vecHora[1]), 0, 0);

        
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracionstr) {
    	//String[] vecDura = duracionstr.split(":");
    	//LocalTime duracion = LocalTime.of((Integer.parseInt(vecDura[0])), Integer.parseInt(vecDura[1]), 0, 0);
    	
     
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
    

    
}
