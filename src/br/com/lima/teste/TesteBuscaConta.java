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

	}

}
