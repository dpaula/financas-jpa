/**
 * 
 */
package br.com.lima.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.lima.modelo.Conta;
import br.com.lima.modelo.TipoMovimentacao;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TesteFuncoesJPQL2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);

		//agrupando por data para trazer as médias dos valores das movimentações por data
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo "
				+ "group by day(m.data), month(m.data), year(m.data)";

		//usando TypedQuery para chumbar a tipagem do retorno da query
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		// setando parametros objeto
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);


		List<Double> medias = query.getResultList();
		
		for (Double media : medias) {
			System.out.println("Media "+media);
		}
		
		em.getTransaction().commit();
		em.close();

	}

}
