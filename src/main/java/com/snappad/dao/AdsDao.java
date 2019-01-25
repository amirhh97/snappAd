package com.snappad.dao;

import java.util.ArrayList;
import java.util.List;

import com.snappad.model.AdsModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.hql.internal.ast.tree.OrderByClause;


public class AdsDao {
	Session session;
	Transaction transaction;

	public AdsDao() {
		session = new HibernateUtil().getSession();

	}

	public List getAllAds() {

		DetachedCriteria dc = DetachedCriteria.forClass(AdsModel.class);
		Criteria c = dc.getExecutableCriteria(session);
		c.addOrder(Order.desc("AdsDate"));
		//c.setFirstResult(1);
		//c.setMaxResults(9);
		return c.list();

	}
	public void RegAds(AdsModel Ad)
	{
		Transaction t = null;
		try {
			t=session.beginTransaction();
			session.save(Ad);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		
	}

}
