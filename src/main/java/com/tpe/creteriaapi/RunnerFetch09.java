package com.tpe.creteriaapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch09 {
    public static void main(String[] args) {


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Developer09.class);
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //1- id=1 olan developer in ismini "Sezai Karakoc" olarak update ediniz
        Developer09 dev1 = session.get(Developer09.class, 1L);
        dev1.setName("Sezai Karakoc");
        session.update(dev1);

        //2- id=1 olan developer in ismini "Sair Sezai Karakoc" olarak update ediniz
        String hql = "UPDATE Developer09 d SET d.name='Sair Sezai Karakoc' WHERE d.id=1";
        int numRecord=session.createQuery(hql).executeUpdate();
        System.out.println("Etkilenen kayit sayisi :"+numRecord);

        //3- parametre kulanarak salary 7000 den az olan developer larin salary sini 7850 yapiniz
        int updatedSalary=7850;
        int checkSalary=7000;
        String hql2= "UPDATE Developer09 d SET d.salary=:updatedSalary WHERE d.salary<:checkSalary";
        int numOfRecord =session.createQuery(hql2).setParameter("updatedSalary",updatedSalary).
                setParameter("checkSalary",checkSalary).executeUpdate();
        System.out.println("parametreli sorgu :" + numOfRecord);

        tx.commit();
        session.close();
        sf.close();
    }
}
