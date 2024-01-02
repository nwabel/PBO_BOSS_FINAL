package pbo_final_boss.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import pbo_final_boss.model.Soal.PaketSoal;
import jakarta.persistence.CascadeType;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "dosens")
public class Dosen extends User {
    @Id
    @GeneratedValue
    private long id_dsn;

    private String NIDN;
    public String namaDosen;
    public String mataKuliah;
    public LocalDate jadwalMK;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dsn")
    public List<PaketSoal> daftarPaket;

    public Dosen() {
        super();
    }

    public Dosen(String userName, String password, String role, String NIDN, String namaDosen, String mataKuliah,
            LocalDate jadwalMK,
            String email) {
        super(userName, password, role);
        this.NIDN = NIDN;
        this.namaDosen = namaDosen;
        this.mataKuliah = mataKuliah;
        this.jadwalMK = jadwalMK;
        this.email = email;
        this.daftarPaket = new ArrayList<PaketSoal>();
    }

    public String getNIDN() {
        return NIDN;
    }

    public void setNIDN(String nIDN) {
        NIDN = nIDN;
    }

    public String getEmail() {
        return email;
    }

    public long getId_dsn() {
        return id_dsn;
    }
    
    
    
}
