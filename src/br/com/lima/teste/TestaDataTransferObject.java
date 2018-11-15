/**
 * 
 */
package br.com.lima.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.lima.modelo.ContaComNumeroEAgencia;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 * 
 *         Em algumas situações não precisamos buscar no banco de dados, todas
 *         as informações de uma entidade. Podemos usar um objeto apenas para
 *         transferir as informações de que precisamos. Ou seja, usar um Data
 *         Transfer Object.
 *
 */
public class TestaDataTransferObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		//instanciando novo objeto com campos de outras tabelas/entidade
		String jpql = "select new br.com.lima.modelo.ContaComNumeroEAgencia(c.numero, c.agencia) from Conta c ";
		
		TypedQuery<ContaComNumeroEAgencia> query = em.createQuery(jpql, ContaComNumeroEAgencia.class);
		
		List<ContaComNumeroEAgencia> contas = query.getResultList();
		
		for (ContaComNumeroEAgencia conta : contas) {
			System.out.println(conta);
		}
		
		em.getTransaction().commit();
		em.close();

	}

}
