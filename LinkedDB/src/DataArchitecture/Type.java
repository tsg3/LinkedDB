package DataArchitecture;

import org.json.simple.JSONObject;

public class Type {

	private String name;
	private JSONObject atributosR;
	private JSONObject atributosNR;
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

}
