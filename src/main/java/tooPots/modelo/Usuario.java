package tooPots.modelo;

public class Usuario {

    public enum Role{ ADMINISTRADOR, MONITOR, CLIENTE;}
    private String usuario;
    private String password;
    private Role role;

    public Usuario() {
        super();
    }

    public Usuario(String usuario, String password, String role) {
        this.usuario = usuario;
        this.password = password;
        this.role = this.tipoRole(role);
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role tipoRole(String c){
        for (Role r: Role.values()) {
            if(c.toUpperCase().equals(r.toString()))
                return r;
        }
        return null;
    }
    public String getRole() { return role.toString(); }

    public void setRole(Role r) { this.role = r; }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}