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
public class TesteNamedQuery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);

		//invocando uma namedQuery pelo nome e definindo seu tipo de retorno
		TypedQuery<Double> mediasPorDiaETipo = em.createNamedQuery("MediasPorDiaETipo", Double.class);
		
		//setando seus parametros conforme nome definido na query e tipos
		mediasPorDiaETipo.setParameter("pConta", conta);
		mediasPorDiaETipo.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		//listando as namedQuery por lista
		List<Double> medias = mediasPorDiaETipo.getResultList();
		
		for (Double media : medias) {
			System.out.println("Medias por namedQuery: "+media);
		}
		
		
		em.getTransaction().commit();
		em.close();

	}

}
