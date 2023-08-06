package app;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import web.member.entity.Member;

import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TestApp {
	public static void main(String[] args) {
		TestApp app = new TestApp();
//		Member member = new Member();
//		member.setUsername("Hunter");
//		member.setPassword("123456");
//		member.setNickname("暱稱");
//		
//		app.insert(member);
//		System.out.println(member.getId());
//		System.out.println(app.deleteById(4));
		
//		Member member = new Member();
//		member.setId(3);
//		member.setPass(false);
//		member.setRoleId(1);
//		System.out.println(app.UpdateById(member));
		
//		System.out.println(app.SelectById(1));

		// app.selectAll().forEach(member -> System.out.println(member.getNickname));
//		List<Member> list = app.SelectALL();
//		for(Member value: list) {
//			System.out.println(value);
//		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		//Select USERNAME, NICKNAME from MEMBER where USERNAME = ? and PASSWORD = ? ORDER BY id
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//from MEMBER
		Root<Member> root = criteriaQuery.from(Member.class);
		//where USERNAME = ? and PASSWORD = ?
		criteriaQuery.where(criteriaBuilder.and(
			criteriaBuilder.equal(root.get("username"), "admin"),
			criteriaBuilder.equal(root.get("password"), "P@ssw0rd")
		));
		//Select USERNAME, NICKNAME
		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));

		//ORDER BY id
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

		Member member = session.createQuery(criteriaQuery).uniqueResult();
	}
	
	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		return null;
	}
	
	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		return -1;
	}
	
	public int UpdateById(Member newMember) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldMember = session.get(Member.class, newMember.getId());
			if(newMember.getPass() != null) {
				oldMember.setPass(newMember.getPass());
			}
			if(newMember.getRoleId() != null) {
				oldMember.setRoleId(newMember.getRoleId());
			}
			transaction.commit();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		return -1;
	}
	
	public Member SelectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			transaction.commit();
			return member;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		return null;
	}
	
	public List<Member> SelectALL() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query = session.createQuery(
					"SELECT new web.member.pojo.Member(username, nickname) FROM Member", Member.class);
			List<Member> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		return null;
	}
	
}
