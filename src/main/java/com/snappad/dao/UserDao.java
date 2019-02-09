package com.snappad.dao;

import com.snappad.dao.HibernateUtil;
import com.snappad.model.UserModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


public class UserDao {
	private Session session;
	private Transaction transaction;

	public UserDao() {
		session = new HibernateUtil().getSession();
		transaction = session.beginTransaction();
	}

	public boolean UserExist(String num) {
		UserModel user = null;
		DetachedCriteria dc = DetachedCriteria.forClass(UserModel.class);
		Criteria ec = dc.getExecutableCriteria(session);
		ec.add(Restrictions.eq("usermobilenum", num));
		if (ec.list().size() > 0)
			return true;
		return false;
	}

	public void RegUser(UserModel user) {

		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
	public int login(String Number,String password)
	{
		DetachedCriteria dc=DetachedCriteria.forClass(UserModel.class);
		Criteria criteria=dc.getExecutableCriteria(session);
		criteria.add(Restrictions.and(Restrictions.eq("usermobilenum", Number),Restrictions.eq("userpass", password)));
		if(criteria.list().size()>0)
		return ((UserModel)criteria.list().get(0)).getUserid();
		return -1;
	}
}
