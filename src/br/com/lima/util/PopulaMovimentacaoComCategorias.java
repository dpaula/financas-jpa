/**
 * 
 */
package br.com.lima.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.lima.modelo.Categoria;
import br.com.lima.modelo.Conta;
import br.com.lima.modelo.Movimentacao;
import br.com.lima.modelo.TipoMovimentacao;

/**
 * @author ferna
 *
 */
public class PopulaMovimentacaoComCategorias {

	public static void main(String[] args) {

		// cria as enteidades categoria
		Categoria cat1 = new Categoria("Internet");
		Categoria cat2 = new Categoria("Compras");

		// busca a conta com id um
		Conta conta = new Conta();
		conta.setId(1);

		// cria uma nova movimentação
		Movimentacao movi = new Movimentacao();
		movi.setData(Calendar.getInstance());
		movi.setDescricao("Sapatos");
		movi.setTipo(TipoMovimentacao.SAIDA);
		movi.setValor(new BigDecimal("300.00"));
		// inclui as categorias
		movi.setCategorias(Arrays.asList(cat1, cat2));
		// relaciona a conta j� existente
		movi.setConta(conta);

		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Movimentacao movi2 = new Movimentacao();
		movi2.setData(amanha);
		movi2.setDescricao("Notebook");
		movi2.setTipo(TipoMovimentacao.SAIDA);
		movi2.setValor(new BigDecimal("5545.00"));
		movi2.setCategorias(Arrays.asList(cat1, cat2));
		movi2.setConta(conta);

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// primeiro persiste as categorias que ainda não existem na base
		em.persist(cat1);
		em.persist(cat2);

		// por fim persistem as movimentações
		em.persist(movi);
		em.persist(movi2);

		em.getTransaction().commit();
		em.close();

	}

}
