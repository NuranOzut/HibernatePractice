package com.tpe.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch01 {
    public static void main(String[] args) {

        Configuration cfg = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Developer01.class);
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Developer01 dev = session.get(Developer01.class, 1);
        System.out.println(dev);

        //1-sql ile tüm datayi çekiniz.
        String sqlQuery1 = "SELECT * FROM t_developer01";
        List<Object[]> resultList = session.createSQLQuery(sqlQuery1).getResultList();
        System.out.println("------SQL----------");
        for (Object[] r : resultList) {
            System.out.println(Arrays.toString(r));
        }
        //2-hql ile tüm datayi çekiniz.
        String hqlQuerry1 = "FROM Developer01";
        List<Developer01> devList = session.createQuery(hqlQuerry1, Developer01.class).getResultList();
        System.out.println("------HQL----------");
        devList.forEach(t -> System.out.println(t));

        //3-hql ile ismi 'Shrek' olan datayi cekiniz
        String hqlQuerry2 = "FROM Developer01 WHERE name = 'Shrek'";
        List<Developer01> devList2 = session.createQuery(hqlQuerry2, Developer01.class).getResultList();
        devList2.forEach(t -> System.out.println(t));

        //4-hql ile emaili 'jacj@gmail.com' olan datayi cekiniz
        String hqlQuerry3 = "FROM Developer01 WHERE email='jacj@gmail.com'";
        List<Developer01> devList3 = session.createQuery(hqlQuerry3, Developer01.class).getResultList();
        devList3.forEach(t -> System.out.println(t));

        //5-hql ile branch i backend olan datanin ismini getiriniz
        String hqlQuerry4 = "FROM Developer01 WHERE branch='backend'";
        List<Developer01> devList4 = session.createQuery(hqlQuerry3, Developer01.class).getResultList();
        devList4.forEach(t -> System.out.println(t));


        tx.commit();
        session.close();
        sf.close();
    }
}
