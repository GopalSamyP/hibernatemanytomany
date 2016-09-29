package com.gopal.hib.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gopal.hib.entity.Cart;
import com.gopal.hib.entity.Item;
import com.gopal.hib.util.HibernateUtil;

public class HibernateManyToManyAnnotationMain {
	
	
	public static void main(String[] args) {
		
		Item item1 = new Item();
		item1.setDescription("samsung"); item1.setPrice(300);
		item1.setId(4l);
		Item item2 = new Item();
		item2.setDescription("nokia"); item1.setPrice(200);
		item2.setId(5l);
		Cart cart = new Cart();
		cart.setTotal(item1.getPrice()+item2.getPrice());
		Set<Item> items = new HashSet<Item>();
		items.add(item1);
		items.add(item2);
		cart.setItems(items);
		
		SessionFactory sessionFactory = null;
		try{
			
			sessionFactory = HibernateUtil.getSessionAnnotationFactory();
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.save(cart);
			System.out.println("Before committing transaction");
			tx.commit();
			sessionFactory.close();
			
			System.out.println("Cart ID="+cart.getId());
			System.out.println("Item1 ID="+item1.getId());
			System.out.println("Item2 ID="+item2.getId());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory != null && !sessionFactory.isClosed()) sessionFactory.close();
		}
	}

}
