/**
 * 
 */
package br.com.lima.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.lima.modelo.Conta;
import br.com.lima.modelo.Movimentacao;
import br.com.lima.modelo.TipoMovimentacao;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TestaJPARelacionamento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setAgencia("0147");
		conta.setBanco("Santander");
		conta.setNumero("010243938");
		conta.setTitular("Fernando de Lima");

		Movimentacao movi = new Movimentacao();
		movi.setData(Calendar.getInstance());
		movi.setDescricao("Cerva");
		movi.setTipo(TipoMovimentacao.SAIDA);
		movi.setValor(new BigDecimal("45.00"));
		movi.setConta(conta);

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		// passando as entidades de Transient para Managed

		// primeiro persiste a conta senão da erro, pois ao persistir a movimentação não
		// existirá foreing key para a conta
		em.persist(conta);
		em.persist(movi);

		em.getTransaction().commit();
		em.close();

	}

}
