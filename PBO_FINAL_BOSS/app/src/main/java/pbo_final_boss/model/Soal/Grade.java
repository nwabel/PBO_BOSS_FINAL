package pbo_final_boss.model.Soal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue
    private long grade_id;

    public int nilai;

    @OneToOne(mappedBy = "nilaiPaket")
    private PaketSoal tipePaket;

    public Grade() {
    }
    
    public Grade(int nilai) {
        this.nilai = nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    
}
