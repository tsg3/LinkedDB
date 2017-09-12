package DataArchitecture;

import org.json.simple.JSONObject;

public class JSON {

	private String name;
	private JSONObject atributos;
	private JSON next = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public JSONObject getAtributos() {
		return atributos;
	}
	public void setAtributos(JSONObject atributos) {
		this.atributos = atributos;
	}
	public JSON getNext() {
		return next;
	}
	public void setNext(JSON next) {
		this.next = next;
	}
}
