package com.snappad.dao;

import java.util.List;

import com.snappad.model.CategoryModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;


public class CategoryDao {
	Session session;
	public CategoryDao()
	{
		session=new HibernateUtil().getSession();
	}
	public List<CategoryModel>getAllCat()
	{
		List list = null;
		Transaction t=null;
		try {
			t=session.beginTransaction();
			DetachedCriteria dc=DetachedCriteria.forClass(CategoryModel.class);
			Criteria c=dc.getExecutableCriteria(session);
			t.commit();
			list=c.list();
			} catch (Exception e) {
			// TODO: handle exception
				t.rollback();
				e.printStackTrace();
		}finally {
			//session.close();
		}
		return list;
	}
	public CategoryModel getCatById(Integer id) {
		// TODO Auto-generated method stub
		CategoryModel cat;
		try {
			cat=(CategoryModel) session.get(CategoryModel.class, id);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return cat;
	}
}
