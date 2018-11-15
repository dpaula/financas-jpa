/**
 * 
 */
package br.com.lima.teste;

import javax.persistence.EntityManager;

import br.com.lima.modelo.Cliente;
import br.com.lima.modelo.Conta;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TestaContaCliente {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(1);

		Cliente cli = new Cliente();
		cli.setEndereco("Rua Regente , 222");
		cli.setNome("Fernando de Lima");
		cli.setProficao("Arquiteto de Solucoes");
		cli.setConta(conta);

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// persistindo a mesma conta para outro cliente e validando que vai dar erro de
		// duplicação de chaves pois o relacionamento está configurado para oneToOne
		em.persist(cli);

		em.getTransaction().commit();
		em.close();

	}
}
