package app;

import core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import web.emp.pojo.Dept;
import web.emp.pojo.Emp;

import java.util.List;

public class TestApp2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Dept dept = session.get(Dept.class, 30);
//        List<Emp> emps = dept.getEmps();
//        for(Emp emp : emps){
//            System.out.println(emp.getEname());
//        }

//        Emp emp = session.get(Emp.class, 7369);
//        Dept dept = emp.getDept();
//        System.out.println(dept.getDeptno());
//        System.out.println(dept.getDname());

        Emp emp = session.get(Emp.class, 7369);
        Dept dept = emp.getDept();
        List<Emp> emps = dept.getEmps();
        for (Emp tmp : emps){
            System.out.println(tmp.getEname());
        }
    }
}
