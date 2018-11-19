/**
 * 
 */
package br.com.lima.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lima.modelo.Conta;
import br.com.lima.modelo.TipoMovimentacao;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TesteFuncoesJPQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);

		// selecionando a soma dos registros das movimentações de uma conta, para
		// movimentações com tipo de saida
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo";

		Query query = em.createQuery(jpql);
		// setando parametros objeto
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		// trazendo um objeto do resultado conforme retorno da jpql, onde temo apenas um
		// valor
		// BigDecimal somaValores = (BigDecimal) query.getSingleResult();
		Double media = (Double) query.getSingleResult();// para média tem que ser tipo Double

		//
//		System.out.println("Soma dos valores da conta " + conta.getNumero() + " -> " + somaValores);
		System.out.println("Média dos valores da conta " + conta.getNumero() + " -> " + media);

		em.getTransaction().commit();
		em.close();

	}

}
