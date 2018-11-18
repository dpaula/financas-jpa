package br.com.lima.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lima.modelo.Conta;
import br.com.lima.util.JPAUtil;

public class TestaTodasMovimentacoesContas {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// Porém, podemos não querer isto e, pela quantidade de queries feitas, queremos
		// que quando a conta for buscada, sejam trazidas também as movimentações.
		// Então, precisaremos substituir o comportamento Lazy por Eager Loading, a
		// partir do parâmetro join fetch, o que quer dizer que queremos juntar, nesta
		// query, a conta e a movimentação.
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		Query query = em.createQuery(jpql);

		List<Conta> contas = query.getResultList();

		for (Conta conta : contas) {
			System.out.println("Titular conta: " + conta.getTitular());
			// então agora será feito apenas um select, e naõ um para cada conta
			System.out.println("    Movimentacoes: ");
			System.out.println(conta.getMovimentacoes());
		}

		em.getTransaction().commit();
		em.close();
	}

}
