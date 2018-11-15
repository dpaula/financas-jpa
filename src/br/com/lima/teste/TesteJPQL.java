/**
 * 
 */
package br.com.lima.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lima.modelo.Conta;
import br.com.lima.modelo.Movimentacao;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TesteJPQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(1);

		// criando uma jpql sempre acessando os campos da tabela como atributos
		String jpql = "select m from Movimentacao m where m.conta = :pConta" + " order by m.valor desc";
		Query query = em.createQuery(jpql);
		// setando parametros objeto
		query.setParameter("pConta", conta);

		// retorna a lista dos registros
		List<Movimentacao> movis = query.getResultList();

		System.out.println();
		for (Movimentacao movi : movis) {
			System.out.println(movi.toString());
		}

		em.getTransaction().commit();
		em.close();

	}

}
