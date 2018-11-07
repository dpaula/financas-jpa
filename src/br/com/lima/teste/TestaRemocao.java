/**
 * 
 */
package br.com.lima.teste;

import javax.persistence.EntityManager;

import br.com.lima.modelo.Conta;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TestaRemocao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		// Para remover qualquer entidade, ela precisa estar gerenciada (Managed). Então
		// vamos carregar a conta antes com o método find() deletando-a em seguida.
		Conta conta = em.find(Conta.class, 2);

		// Após a chamada de remove(), o objeto não tem mais representação no banco, já
		// que foi removido. Porém, ele continua existindo na memória, em um estado
		// conhecido como Removed.

		em.remove(conta);

		em.getTransaction().commit();

		em.close();

	}

}
