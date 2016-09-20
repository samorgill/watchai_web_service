package uk.ac.mmu.watchai.DAO;

import java.util.List;

import com.google.appengine.api.NamespaceManager;

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

import uk.ac.mmu.watchai.Model.Thing;

/**
 * Data Accessor Object class
 */

public enum DAO {
	INSTANCE;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


	/**
	 * Method for adding a thing object to the datastore
	 * @param thing
	 * @param state
	 * @param user
	 * @param serial
	 * @param type
	 * @param zone
	 * @param room
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
	 * Method to search for a named thing in the datastore
	 * @param courseName
	 * @return courses
	 * @throws EntityNotFoundException 
	 */
		
	@SuppressWarnings("unchecked")
	public Thing getThing(String thing) throws EntityNotFoundException{
		
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
	 * Method to return all things in the datastore
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
	
	
	@SuppressWarnings("unchecked")
	public List<Entity> getAllMonitors(String user) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		
		Query query = new Query("Monitor");
		PreparedQuery pq = datastore.prepare(query);
		List<Entity> dList = pq.asList(FetchOptions.Builder.withDefaults());
		return dList;
	}
		
	
	/**
	 * Method to return all zones in the datastore
	 * @return courses
	 */
	
	@SuppressWarnings("unchecked")
	public List<Entity> getAllZones(String user) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		
		Query query = new Query("Zone");
		PreparedQuery pq = datastore.prepare(query);
		List<Entity> dList = pq.asList(FetchOptions.Builder.withDefaults());
		return dList;
		}
	
	
	/**
	 * A method for updating the state of a thing ie on/off in the database
	 * @param thing
	 * @param state
	 * @param user
	 * @param serial
	 * @param type
	 * @param zone
	 * @param room
	 * @return
	 * @throws EntityNotFoundException
	 */
	 
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
	
	
	/**
	 * Add Zone to the database
	 * @param zone
	 */
	 
	public void addZone(String zone, String user) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		Entity en = new Entity("Zone", zone);
		en.setProperty("zone", zone);
		datastore.put(en);
	}
	
	
	/**
	 * Add monitor  to the database
	 * @param zone
	 */
	
	public void addMonitor(String type, String state, String user) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(user);
		Entity en = new Entity("Monitor", type);
		en.setProperty("type", type);
		en.setProperty("state", state);
		datastore.put(en);
	}
	
}
