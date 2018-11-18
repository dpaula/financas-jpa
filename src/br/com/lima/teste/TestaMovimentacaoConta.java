package br.com.lima.teste;

import javax.persistence.EntityManager;

import br.com.lima.modelo.Conta;
import br.com.lima.modelo.Movimentacao;
import br.com.lima.util.JPAUtil;

/**
 * 
 * @author user
 *
 */
public class TestaMovimentacaoConta {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		// trazendo a movimentaçao com id 3
		Movimentacao movi = em.find(Movimentacao.class, 3);
		// a partir da movimentação é possivel trazer a conta associada
		Conta conta = movi.getConta();
		
		System.out.println(conta.getTitular());
		// e esta conta conhece as movimentações, onde cada movimentação foi associada
		// e a mesma conhece essa bidirecionalidade conforme configurado
		System.out.println(conta.getMovimentacoes().size());
		
		em.getTransaction().commit();
		em.close();
	}

}
