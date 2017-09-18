package DataArchitecture;

public class DocumentObject {

	private DocumentObject next = null;
	private String atributo;
	private Object valor;

	public DocumentObject getNext() {
		return next;
	}
	public void setNext(DocumentObject next) {
		this.next = next;
	}
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}

}
