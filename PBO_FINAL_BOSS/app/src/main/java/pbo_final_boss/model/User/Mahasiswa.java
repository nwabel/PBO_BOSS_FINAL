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
@Table(name = "Mahasiswas")
public class Mahasiswa extends User {
    @Id
    @GeneratedValue
    public long idMhs;

    private String NIM;

    public String namaMhs;
    public String jurusanMhs;
    private LocalDateTime tanggalLahir;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mhs")
    public List<PaketSoal> daftarPaketPilihan;

    public Mahasiswa() {
        super();
    }

    public Mahasiswa(String userName, String password, String role, String nIM, String namaMhs, String jurusanMhs,
            LocalDateTime tanggalLahir, String email) {
        super(userName, password, role);
        NIM = nIM;
        this.namaMhs = namaMhs;
        this.jurusanMhs = jurusanMhs;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        // this.daftarNilai = new ArrayList<Grade>();
    }

    public String getNIM() {
        return NIM;
    }

    public String getEmail() {
        return email;
    }

    public long getId_msh() {
        return idMhs;
    }
    
    

}
