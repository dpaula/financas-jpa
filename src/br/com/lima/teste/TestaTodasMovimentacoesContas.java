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

		// Por�m, podemos n�o querer isto e, pela quantidade de queries feitas, queremos
		// que quando a conta for buscada, sejam trazidas tamb�m as movimenta��es.
		// Ent�o, precisaremos substituir o comportamento Lazy por Eager Loading, a
		// partir do par�metro join fetch, o que quer dizer que queremos juntar, nesta
		// query, a conta e a movimenta��o.
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		Query query = em.createQuery(jpql);

		List<Conta> contas = query.getResultList();

		for (Conta conta : contas) {
			System.out.println("Titular conta: " + conta.getTitular());
			// ent�o agora ser� feito apenas um select, e na� um para cada conta
			System.out.println("    Movimentacoes: ");
			System.out.println(conta.getMovimentacoes());
		}

		em.getTransaction().commit();
		em.close();
	}

}
