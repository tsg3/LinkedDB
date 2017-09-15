package DataArchitecture;

import java.io.IOException;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class TypeList {

	public Type root;
	public FileWriter file;

	public TypeList(){
		this.root=null;
	}

	@SuppressWarnings("unchecked")
	public void add(String[] atributos, Object[] valoresRequeridos, Object[] valoresNoRequeridos, String nombre){
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject objActual = obj;
		Type newType = new Type();
		newType.setName(nombre);
		int n = atributos.length;
		Object atributo = atributos[0];
		Object[] valores = valoresRequeridos;
		Object valor = valores[0];
		for (int i=0; i < n; i++){
			objActual.put(atributo, valor);
			if(i<n-1){
				atributo = atributos[i+1];
				if ((i+1)==valores.length){
					newType.setAtributosR(objActual);
					objActual = obj2;
					valores = valoresNoRequeridos;
					valor = valores[0];
				}
				else {
					valor = valores[i+1];
				}
			}
		}
		newType.setAtributosNR(objActual);
		if (root==null){
			root = newType;
		}
		else{
			Type current = root;
			while(current.getNext()!=null){
				current = current.getNext();
			}
			current.setNext(newType);
		}
	}

	public void delete(String name){
		if (root.getName().equals(name)){
			root = root.getNext();
		}
		else{
			Type current = root;
			while (current.getNext() != null){
				current = current.getNext();
				if (current.getNext().getName().equals(name)){
					current.setNext(current.getNext().getNext());
					return;
				}
			}
			return;
		}
	}

	public void display() {
		if(this.root == null){
			return;
		}
        Type current = root;
        while (current != null) {
            System.out.println("Requeridos: " + current.getAtributosR() + ", No Requeridos: " + current.getAtributosNR());
            current = current.getNext();
        }

	}

	@SuppressWarnings("unchecked")
	public void write(){
		if(this.root == null){
			return;
		}
		Type current = root;
		while (current != null) {
			JSONObject doc = new JSONObject();
            doc.put("Requirido",current.getAtributosR());
            doc.put("No Requirido",current.getAtributosNR());
            try {
    			file = new FileWriter("C:\\Users\\este0\\Desktop\\Pruebas\\Proyecto 1\\"+current.getName()+".json");
    			file.write(doc.toJSONString());
    			file.flush();
    		} catch (IOException e) {
    		}
            current = current.getNext();
        }
	}

    public boolean exists(String name){
    	Type current = root;
        while (current != null) {
       		if (current.getName().equals(name)){
        		return true;
        	}
        	current=current.getNext();
        }
        return false;

	}

	public static void main(String[] args){
		String[] atributos = {"Nombre", "Carnet", "Número"};
		Object[] R = {"", 0};
		Object[] N = {0};
		String nombre = "Persona";
		TypeList list = new TypeList();
		list.add(atributos,R,N,nombre);
		list.display();
		list.exists("Persona");
	}

}
