package pbo_final_boss.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.checkerframework.checker.units.UnitsTools.m;
import org.hibernate.query.Query;
import pbo_final_boss.model.Soal.Grade;
import pbo_final_boss.model.Soal.PaketSoal;
import pbo_final_boss.model.Soal.Soal;
import pbo_final_boss.model.User.Admin;
import pbo_final_boss.model.User.Dosen;
import pbo_final_boss.model.User.Mahasiswa;
import pbo_final_boss.util.HibernateUtil;


public class MyController {
    public Mahasiswa searchUnameMHS(String userName) throws SQLException{
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Mahasiswa test = session.createQuery("from Mahasiswa m where m.userName = :uname", Mahasiswa.class).setParameter("uname", userName).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return test;
    }
    
    public Dosen searchUnameDSN(String userName) throws SQLException{
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Dosen test = session.createQuery("from Dosen m where m.userName = :uname", Dosen.class).setParameter("uname", userName).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return test;
    }
    
    public Admin searchUnameADM(String userName) throws SQLException{
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Admin test = session.createQuery("from Admin m where m.userName = :uname", Admin.class).setParameter("uname", userName).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return test;
    }
        
    public void createAccDsn(String userName, String password, String role, String NIDN, String namaDosen, String mataKuliah,
        LocalDate jadwalMK,
        String email){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            
        Dosen obj = new Dosen(userName, password, role, NIDN, namaDosen, mataKuliah, jadwalMK,email);
        session.persist(obj);
            
        session.getTransaction().commit();
        session.close();
    }
        
    public void createAccMhs(String userName, String password, String role, String nIM, String namaMhs, String jurusanMhs,
        LocalDateTime tanggalLahir, String email){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            
        Mahasiswa obj = new Mahasiswa(userName, password, role, nIM, namaMhs, jurusanMhs,tanggalLahir, email);
        session.persist(obj);
            
        session.getTransaction().commit();
        session.close();
    }
    
    public void deleteAcc(String uname) throws SQLException{
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
                
        Mahasiswa mhs = searchUnameMHS(uname);
        Dosen dsn = searchUnameDSN(uname);
            
        if(mhs != null){
            session.delete(mhs);
        }else{
            session.delete(dsn);
        }
            
        session.getTransaction().commit();
        session.close();
    }
        
    public void buatPaket(Dosen dsn, String s){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(dsn);
        PaketSoal ps = new PaketSoal(s);
        dsn.daftarPaket.add(ps);
        session.persist(ps);
            
        session.getTransaction().commit();
        session.close();
    }
    
    public void buatSoal(Dosen dsn, String s1, String s2, String s3){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(dsn);
        
        PaketSoal ps = null;
        for(PaketSoal p : dsn.daftarPaket){
            if(p.getTypePaket().equals(s1)){
                ps = p;
                break;
            }
        }
        
        Soal s = new Soal(s2, s3);
        ps.addSoal(s);
        
        session.persist(ps);
        session.getTransaction().commit();
        session.close();
    }
    
    public void selectedPaketSoal(Mahasiswa mhs, String s){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(mhs);
        
        PaketSoal ps = session.createQuery("from PaketSoal ps where ps.typePaket = :name", PaketSoal.class).setParameter("name",s).uniqueResult();
        
        mhs.daftarPaketPilihan.add(ps);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public List<PaketSoal> isiListPaket(){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<PaketSoal> lps = session.createQuery("from PaketSoal ps", PaketSoal.class).list();
        
        session.getTransaction().commit();
        session.close();
        return lps;
    }
    
    public List<Integer> getNilai(Mahasiswa m){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(m);
        
        String hql = "SELECT g.nilai FROM Mahasiswa m "
        + "JOIN m.daftarPaketPilihan ps "
        + "JOIN ps.nilaiPaket g "
        + "WHERE m.idMhs = :idMhs";
        List<Integer> nilai = session.createQuery(hql, Integer.class).setParameter("idMhs", m.idMhs).list();
        return nilai;

    }
    
    public List<String> getNamaPaket(Mahasiswa m){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(m);
        
        String hql = "SELECT ps.typePaket FROM Mahasiswa m "
        + "JOIN m.daftarPaketPilihan ps "
        + "JOIN ps.nilaiPaket g "
        + "WHERE m.idMhs = :idMhs";
        List<String> nilai = session.createQuery(hql, String.class).setParameter("idMhs", m.idMhs).list();
        return nilai;

    }
    
    public List<String> getSoal(Mahasiswa m){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(m);
        
        String hql = "SELECT ds.soal FROM Mahasiswa m "
        + "JOIN m.daftarPaketPilihan ps "
        + "JOIN ps.daftarSoal ds "
        + "WHERE m.idMhs = :idMhs";
        List<String> nilai = session.createQuery(hql, String.class).setParameter("idMhs", m.idMhs).list();
        session.getTransaction().commit();
        session.close();
        return nilai;

    }
    
    public List<String> geKjawaban(Mahasiswa m){
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(m);
        
        String hql = "SELECT ds.soal FROM Mahasiswa m "
        + "JOIN m.daftarPaketPilihan ps "
        + "JOIN ps.daftarSoal ds "
        + "WHERE m.idMhs = :idMhs";
        List<String> nilai = session.createQuery(hql, String.class).setParameter("idMhs", m.idMhs).list();
        session.getTransaction().commit();
        session.close();
        return nilai;
    }
    
   public Grade setGrade(Mahasiswa mhs) {
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(mhs);
        
        String hql = "SELECT ds FROM Mahasiswa m "
        + "JOIN m.daftarPaketPilihan ps "
        + "JOIN ps.nilaiPaket ds "
        + "WHERE m.idMhs = :idMhs";
        Grade grd = session.createQuery(hql, Grade.class).setParameter("idMhs", mhs.idMhs).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return grd;
   }

}
