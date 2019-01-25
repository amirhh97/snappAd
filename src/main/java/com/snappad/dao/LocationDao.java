package com.snappad.dao;

import java.util.List;

import com.snappad.dao.HibernateUtil;
import com.snappad.model.CityModel;
import com.snappad.model.DistrictModel;
import com.snappad.model.StateModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


public class LocationDao {
	Session session;
	public LocationDao()
	{
		session=new HibernateUtil().getSession();
	}
	public List<CityModel>getAllCities()
	{
		DetachedCriteria dc=DetachedCriteria.forClass(CityModel.class);
		Criteria c=dc.getExecutableCriteria(session);
		return c.list();
	}
	public List getAllState() {
		DetachedCriteria dc=DetachedCriteria.forClass(StateModel.class);
		Criteria c=dc.getExecutableCriteria(session);
		return c.list();
		}
	public StateModel getStateById(Integer integer) {
		StateModel s;
		s=(StateModel) session.get(StateModel.class,integer);
		return s;
	}
	public CityModel getCityById(Integer id) {
		CityModel c = null;
		try {
			c=(CityModel) session.get(CityModel.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public DistrictModel getDistrictByName(String district, CityModel city) {
		DistrictModel d=new DistrictModel();
	try {
		DetachedCriteria dc=DetachedCriteria.forClass(DistrictModel.class);
		Criteria c=dc.getExecutableCriteria(session);
		c.add(Restrictions.and(Restrictions.eq("Name", district), Restrictions.eq("City.id", city.getCityId())));
		if(c.list().size()>0)
		return (DistrictModel) c.list().get(0);
			d.setName(district);
			d.setCity(city);
			session.save(d);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		finally {
			session.close();
		}
	return d;	
	}
	
	
}
