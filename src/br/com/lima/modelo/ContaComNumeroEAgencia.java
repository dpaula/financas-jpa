package br.com.lima.modelo;
public class ContaComNumeroEAgencia {
    private String numero;
    private String agencia;

    public ContaComNumeroEAgencia(String numero, String agencia) {
        this.numero = numero;
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ContaComNumeroEAgencia [numero=%s, agencia=%s]", numero, agencia);
	}
    
    
}