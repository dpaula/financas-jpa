/**
 * 
 */
package br.com.lima.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author ferna
 *
 */
@Entity
public class Conta {

	// para informar que o atributo id √© uma chave prim√°ria, e que estamos usando
	// a
	// estrat√©gia de auto increment
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;

	// Com isto, temos um relacionamento bidirecional,
	// em que duas partes conhecem o relacionamento.
	// O que queremos È que ambas as partes representem o mesmo relacionamento.
//	Para isto, precisaremos escolher "a parte forte", que cria o relacionamento, 
//	a coluna de chave estrangeira (conta_id), que no nosso caso È @ManyToOne na classe Movimentacao.
	// @OneToMany, a parte que reflete, j· foi mapeada por uma coluna chamada conta,
//	do outro lado, na classe Movimentacao.
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conta [id=").append(id).append(", numero=").append(numero).append("]");
		return builder.toString();
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
}
