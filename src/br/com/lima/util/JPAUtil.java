/**
 * 
 */
package br.com.lima.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author ferna
 *
 */
public class JPAUtil {

	private static EntityManagerFactory emFabrica = Persistence.createEntityManagerFactory("financasdb");

	public EntityManager getEntityManager() {
		return emFabrica.createEntityManager();
	}

}
