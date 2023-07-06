package com.tpe.creteriaapi;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RunnerFetch09criteriaapi {
    public static void main(String[] args) {


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Developer09.class);
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        ///-----------------Criteria API----------------

        //SQL veya HQL String bazli sorgularda  hataya acik ve hatalari run time da gorebiliriz

        //criteria api java kodlari ile programatik olarak sorgulama yapmamizi saglar
        //degerleme aninda hatalari gorebiliriz dolayisiyla hatalarin minimize edilmesini saglar


        //criteria query nesnesi olusturmak icin,bazi metodlari kullanmak icin
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer09> cq = cBuilder.createQuery(Developer09.class);
        Root<Developer09> root = cq.from(Developer09.class); //classin tum datalarini tutan kaynak

        //1- ismi Cemal olan developer i getiriniz
        cq.select(root).//tum datayi getirir
                where(cBuilder.equal(root.get("name"), "Cemal"));//where predicate ister
        List<Developer09> rs = session.createQuery(cq).getResultList();
        rs.forEach(System.out::println);

        //2- salary si 8000 olan developer lari getiriniz
        cq.select(root).where(cBuilder.equal(root.get("salary"),8000));
        List<Developer09> rs2 = session.createQuery(cq).getResultList();
        rs2.forEach(System.out::println);

        //3- salary si 8000 den fazla olan developer lari getiriniz
        cq.select(root).where(cBuilder.greaterThan(root.get("salary"),8000));
        List<Developer09> rs3 = session.createQuery(cq).getResultList();
        rs3.forEach(System.out::println);

        //4- ismi Erdem olan veya salary si 9000 den fazla olan developer lari getiriniz
        Predicate predicate1=cBuilder.equal(root.get("name"),"Erdem");
        Predicate predicate2=cBuilder.greaterThan(root.get("salary"),9000);
        Predicate predicateOr=cBuilder.or(predicate1, predicate2);

        cq.select(root).where(predicateOr);
        List<Developer09> rs4 =session.createQuery(cq).getResultList();
        rs4.forEach(System.out::println);


        tx.commit();
        session.close();
        sf.close();
    }
}
