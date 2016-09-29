package com.gopal.hib.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.gopal.hib.entity.Employee;
import com.gopal.hib.util.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
        Employee employee = new Employee();
        
        employee.setInsertTime(new Date());
        employee.setName("Pankaj");
        employee.setRole("Master");
        
        try{
        	
        	SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    		Session session = sessionFactory.getCurrentSession();
        	session.beginTransaction();
        	session.save(employee);
        	session.getTransaction().commit();
        	System.out.println("Id :" + employee.getEmpId());
        	sessionFactory.close();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}
