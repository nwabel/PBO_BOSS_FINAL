package pbo_final_boss.model.Soal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.*;

@Entity
@Table(name = "paket_soals")
public class PaketSoal {
    @Id
    @GeneratedValue
    private long paket_id;

    public String typePaket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
    private Grade nilaiPaket;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "paket_id")
    public List<Soal> daftarSoal;

    public PaketSoal() {
    }

    public PaketSoal(String typePaket) {
        this.typePaket = typePaket;
        this.daftarSoal = new ArrayList<>();
    }

    public void addSoal(Soal pertanyaan) {
        daftarSoal.add(pertanyaan);
    }

    public String getTypePaket() {
        return typePaket;
    }
    
    
}
