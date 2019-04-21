
package tooPots.modelo;

public class Cliente {

    private int id_cliente;
    private String nombre;
    private String nif;
    private String sexo;
    private int anyo_nacimiento;
    private String email;

    public Cliente() {
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getnif() {
        return nif;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setnif(String nif) {
        this.nif = nif;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getanyo_nacimiento() {
        return anyo_nacimiento;
    }

    public void setanyo_nacimiento(int anyo_nacimiento) {
        this.anyo_nacimiento = anyo_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}