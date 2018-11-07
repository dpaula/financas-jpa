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
public class TesteBuscaConta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		// buscando um objeto pelo seu id
		Conta conta = em.find(Conta.class, 1);
		System.out.println(conta);

		// ao setar ele já faz o update na base
		// fazendo apenas um comando para várias alterações
		// e não repetindo comandos
		conta.setTitular("Fernando de Paula");
		conta.setAgencia("0001");
		conta.setNumero("8383838");
		System.out.println(conta);

		em.getTransaction().commit();

		em.close();

		// Significa que quando "matamos" a aplicação, a entidade "morre" junto. Esta
		// entidade, então, é Managed? Não, pois o EntityManager que buscou esta
		// entidade já foi fechado. Qual é o estado em que ela se encontra, então?
		//
		// Trata-se do estado Detached, em que a conta não é mais gerenciada pelo JPA.
		// Há uma representação sua no banco, no entanto a sincronização automática não
		// está ativada, pois o gerenciamento não ocorre mais. Nosso objetivo, então, é
		// transformar esta conta de Detached para Managed para que a sincronização
		// aconteça.
		//
		// Fazemos isto com o método merge(), responsável por transformar uma conta que
		// já foi gerenciada pelo JPA em um momento anterior, transformando-a em Managed
		// novamente, permitindo a sincronização com o banco de dados:

		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("Leonardo");
		em2.merge(conta);
		// em2.persist(conta);

		em2.getTransaction().commit();
		em2.close();

	}

}
