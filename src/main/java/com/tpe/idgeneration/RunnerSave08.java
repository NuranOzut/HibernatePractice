package com.tpe.idgeneration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave08 {
    public static void main(String[] args) {


        Developer08 dev1 = new Developer08("Harry Potter", "hp@gmail.com", "backend");
        Developer08 dev2 = new Developer08("Shrek", "shrek@gmail.com", "frontend");
        Developer08 dev3 = new Developer08("Jack Sparrow", "jack@gmail.com", "mobile");
        Developer08 dev4 = new Developer08("Richie Rich", "rich@gmail.com", "mobile");
        Developer08 dev5 = new Developer08("Gandalf", "gandalf@gmail.com", "mobile");


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Developer08.class);
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //seq=100,101,102,103,104,105

//        session.save(dev1);
//        session.save(dev2);
//        session.save(dev3);
      session.save(dev4);
      session.save(dev5);

        //generation type olarak identitiy kullandigimizda, java uygulamamiz
        // siradaki id yi bilmez.DB en son kaldigi degerden devam eder.

        //identitiy stratejisi -> mySQL ve SQL tarafindan desteklenir
        //sequence stratejisi -> Oracle ve PostgreSQL tarafindan desteklenir
        //table stratejisi -> Bir tabloda ID'leri izler ve her bir ID için bir sonraki değeri depolar.
        //auto stratejisi -> Identity, Sequence, Table stratejilerini kullanabilir
        //hibernate de UUID kullanilmaz




        tx.commit();
        session.close();
        sf.close();

    }
}