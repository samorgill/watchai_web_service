package com.bw;

/**
 * @author Samuel Orgill 15118305
 * @version 7
 * 
 * A class returning an instance of the EntityManagerFactory
 * 
 */

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EMFService {
private static final EntityManagerFactory emfInstance = Persistence
.createEntityManagerFactory("transactions-optional");
private EMFService() {
}
public static EntityManagerFactory get() {
return emfInstance;
}
}