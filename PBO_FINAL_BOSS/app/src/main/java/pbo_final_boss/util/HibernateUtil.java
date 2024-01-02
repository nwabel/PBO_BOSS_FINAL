package pbo_final_boss.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
   private static SessionFactory sessionFactory;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
               .build();
         try {
            sessionFactory = new MetadataSources(registry)
                  .addAnnotatedClass(pbo_final_boss.model.Soal.Soal.class)
                  .addAnnotatedClass(pbo_final_boss.model.Soal.PaketSoal.class)
                  .addAnnotatedClass(pbo_final_boss.model.User.Admin.class)
                  .addAnnotatedClass(pbo_final_boss.model.User.Mahasiswa.class)
                  .addAnnotatedClass(pbo_final_boss.model.User.Dosen.class)
                  .addAnnotatedClass(pbo_final_boss.model.Soal.Grade.class)
                  .buildMetadata()
                  .buildSessionFactory();
         } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
         }
      }
      return sessionFactory;
   }
}