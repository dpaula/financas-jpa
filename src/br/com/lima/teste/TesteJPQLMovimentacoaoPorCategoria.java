/**
 * 
 */
package br.com.lima.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lima.modelo.Categoria;
import br.com.lima.modelo.Movimentacao;
import br.com.lima.util.JPAUtil;

/**
 * @author ferna
 *
 */
public class TesteJPQLMovimentacoaoPorCategoria {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria cate = em.find(Categoria.class, 1);

		//fazendo um join de movimentação com categoria para trazer a categoria desejada
		String jpql = "select m from Movimentacao m join m.categoria c "
				+ "where c = :pCategoria";
				
		Query query = em.createQuery(jpql);
		
		query.setParameter("pCategoria", cate);

		// retorna a lista dos registros
		List<Movimentacao> movis = query.getResultList();

		System.out.println();
		for (Movimentacao movi : movis) {
			System.out.println(movi.toString());
		}

		em.getTransaction().commit();
		em.close();

		
	}

}
