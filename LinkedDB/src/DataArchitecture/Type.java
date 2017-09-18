package DataArchitecture;

import org.json.simple.JSONObject;

public class Type {

	private String name;
	private String archivo;
	private String[] atributosRString;
	private JSONObject atributosR;
	private JSONObject atributosNR;
	private String[] atributos;
	private Type next = null;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getNext() {
		return next;
	}
	public void setNext(Type next) {
		this.next = next;
	}
	public JSONObject getAtributosNR() {
		return atributosNR;
	}
	public void setAtributosNR(JSONObject atributosNR) {
		this.atributosNR = atributosNR;
	}
	public JSONObject getAtributosR() {
		return atributosR;
	}
	public void setAtributosR(JSONObject atributosR) {
		this.atributosR = atributosR;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String[] getAtributosRString() {
		return atributosRString;
	}
	public void setAtributosRString(String[] atributosRString) {
		this.atributosRString = atributosRString;
	}
	public String[] getAtributos() {
		return atributos;
	}
	public void setAtributos(String[] atributos) {
		this.atributos = atributos;
	}

}
