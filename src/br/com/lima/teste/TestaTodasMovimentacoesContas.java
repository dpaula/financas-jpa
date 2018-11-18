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

		String jpql = "select c from Conta c";
		Query query = em.createQuery(jpql);

		List<Conta> contas = query.getResultList();

		for (Conta conta : contas) {
			System.out.println("Titular conta: " + conta.getTitular());
			// N + 1, por realizar 1 select para buscar as contas e, para cada uma delas, no
			// laço, foi feito mais um select para buscar as movimentações correspondentes.
			System.out.println("    Movimentacoes: ");
			System.out.println(conta.getMovimentacoes());
		}

		// Isso ocorre justamente porque, por padrão, os relacionamentos [...]ToMany,
		// que são listas ou conjuntos, são considerados Lazy, com "carregamento
		// preguiçoso".
		// Ou seja, o JPA só irá ao banco para buscar estes relacionamentos quando você
		// precisa deles,
		// ao chamarmos o método getMovimentacoes(), por exemplo.

		em.getTransaction().commit();
		em.close();
	}

}
