package pbo_final_boss.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends User {
    @Id
    @GeneratedValue
    private long id_adm;

    private String kodeAdmin;

    public Admin() {
        super();
    }

    public Admin(String kodeAdmin, String userName, String password, String role) {
        super(userName, password, role);
        this.kodeAdmin = kodeAdmin;
    }

    public void setKodeAdmin(String kodeAdmin) {
        this.kodeAdmin = kodeAdmin;
    }

    public String getKodeAdmin() {
        return kodeAdmin;
    }
    
    
}
