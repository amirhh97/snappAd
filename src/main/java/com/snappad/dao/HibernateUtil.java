package com.snappad.dao;

import com.snappad.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	private static SessionFactory sessionfactory;
	public HibernateUtil(){
		init();
	
		
	}
	private void init(){
		if(sessionfactory==null){
			Configuration configuration=new Configuration();
			configuration.configure("hibernate.cfg.xml").addAnnotatedClass(UserModel.class).addAnnotatedClass(CityModel.class)
			.addAnnotatedClass(StateModel.class).addAnnotatedClass(AdsModel.class)
			.addAnnotatedClass(Favorite.class).addAnnotatedClass(CategoryModel.class).addAnnotatedClass(CountyModel.class).addAnnotatedClass(DistrictModel.class)
			.addAnnotatedClass(VehicleModel.class)
			.addAnnotatedClass(BuildingModel.class);
			StandardServiceRegistry srviceregistry=new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionfactory=configuration.buildSessionFactory(srviceregistry);
		}
	}
	public Session getSession(){
		
		return sessionfactory.openSession();
	}

}
