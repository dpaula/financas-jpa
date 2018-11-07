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
public class TestaConta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Conta conta = new Conta();

		conta.setAgencia("0147");
		conta.setBanco("Santander");
		conta.setNumero("010243938");
		conta.setTitular("Fernando de Lima");

		// criar uma entidade de persistencia usando as configurações declaradas no
		// arquivo persistence.xml

		EntityManager em = new JPAUtil().getEntityManager();

		// sempre se inicia uma transação
		em.getTransaction().begin();

		// Depois chamamos o método persist() para que a conta fosse inserida ao banco
		// de dados. Sem isto, ela não seria salva e sumiria completamente caso a
		// aplicação terminasse. Esse estado é chamado Transiente(ou Transient) e a
		// tarefa do método persist() é justamente alterar esse estado para
		// Gerenciado(Managed).
		// realiza a persistencia da entidade
		em.persist(conta);

		// e comita a transação no final
		em.getTransaction().commit();

		// não implementam Closeble, então sempre tem q fechar a sessão
		em.close();

	}

}
