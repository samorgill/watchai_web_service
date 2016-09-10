package com.bw;

import java.util.List;

import com.google.appengine.api.NamespaceManager;

/*import javax.persistence.EntityManager;
import javax.persistence.Query;*/

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entities;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


/**
 * @author Samuel Orgill 15118305
 * @version 7
 */

/**
 * Data Accessor Object class
 */

public enum DAO {
	INSTANCE;
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/*public List<Door> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Door m");
		List<Door> doors = q.getResultList();
		System.out.println(doors);
		return doors;
		
		}*/
	
	/**
	 * Method for adding a Door object
	 * @param courseID, courseName, fullOrPartTime, courseCredits, courseDuration, tutorID, courseTutor
	 */
	
	public void add(String thing, String state, String user, String serial, String type, String zone, String room) {
	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		NamespaceManager.set(user);
		
		Entity en = new Entity("Thing", thing);
		
		en.setProperty("thing", thing);
		en.setProperty("state", state);
		en.setProperty("serial", serial);
		en.setProperty("type", type);
		en.setProperty("zone", zone);
		en.setProperty("room", room);
		
		datastore.put(en);
		/*
		Door doory = new Door(doorName, state);
		
		return doory;
		*/
		
			}
	
	/**
	 * Method for adding a user to the database
	 * @param user
	 * @param pass
	 */
	
	public void logIn(String user, String pass) {
		System.out.println(user);	
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		NamespaceManager.set(user);
		
		Entity en = new Entity("User", user);
		
		en.setProperty("user", user);
		en.setProperty("pass", pass);
		
		datastore.put(en);
		
			}
	
	/**
	 * Method for registering a user
	 * @param user
	 * @param pass
	 */
	public void reg(String user, String pass, String email, String phone, String hub) {
		System.out.println(user);	
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		NamespaceManager.set(user);
		
		Entity en = new Entity("User", user);
		
		en.setProperty("user", user);
		en.setProperty("pass", pass);
		en.setProperty("email", email);
		en.setProperty("phone", phone);
		en.setProperty("hub", hub);
		datastore.put(en);
		
			}
	
	/**
	 * Method to search for a particular course in the datastore
	 * @param courseName
	 * @return courses
	 * @throws EntityNotFoundException 
	 */
	
		
	@SuppressWarnings("unchecked")
	public Thing getThing(String thing) throws EntityNotFoundException{
		
		/*EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select d from Door d where d.doorName = :doorName");
	
		q.setParameter("doorName", doorName);
		List<Door> doors = q.getResultList(); 
		return doors;*/
		
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();
		Key dKey = KeyFactory.createKey("Thing", thing);
		
		Entity Thing = dstore.get(dKey);
		Thing.getProperty(thing);
		
		String dName = (String) Thing.getProperty(thing);
		String st = (String) Thing.getProperty("state");
		String sl = (String) Thing.getProperty("serial");
		
		Thing doory = new Thing(dName, st, sl);
		return doory;
		
	}
	
	/**
	 * Method to return all courses in the datastore
	 * @return courses
	 */
	
	@SuppressWarnings("unchecked")
	public List<Entity> getThings() {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Thing");
		PreparedQuery pq = datastore.prepare(query);
		List<Entity> dList = pq.asList(FetchOptions.Builder.withDefaults());
		
		return dList;
		}
	
	/**
	 * Method to return all courses in the datastore
	 * @return courses
	 */
	
	@SuppressWarnings("unchecked")
	public List<Entity> getAllThings(String user) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		
		Query query = new Query("Thing");
		PreparedQuery pq = datastore.prepare(query);
		List<Entity> dList = pq.asList(FetchOptions.Builder.withDefaults());
		
		return dList;
		}
	
	public Thing updateThing(String thing, String state, String user, String serial, String type, String zone, String room) throws EntityNotFoundException{
		
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		
		Entity en = new Entity("Thing", thing);
		
		en.setProperty("thing", thing);
		en.setProperty("state", state);
		en.setProperty("serial", serial);
		en.setProperty("type", type);
		en.setProperty("zone", zone);
		en.setProperty("room", room);
		
		datastore.put(en);
		
		Thing doory = new Thing(thing, state, serial);
		
		return doory;
	}
	
}
