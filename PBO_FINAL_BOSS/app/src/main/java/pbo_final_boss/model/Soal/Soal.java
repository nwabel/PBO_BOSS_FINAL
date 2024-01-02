package pbo_final_boss.model.Soal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "soals")
public class Soal {
    @Id
    @GeneratedValue
    private long soal_id;

    public String soal;

    private String kJawaban;

    private boolean status;

    public Soal() {
    }

    public Soal(String soal, String kJawaban) {
        this.soal = soal;
        this.kJawaban = kJawaban;
        this.status = false;
    }

    public void setkJawaban(String kJawaban) {
        this.kJawaban = kJawaban;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
