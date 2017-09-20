

import java.io.IOException;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class TypeList {

	public Type root;
	public FileWriter file;
	public String carpeta;


	public TypeList(StoreList store){
		this.root=null;
		this.carpeta=store.getCarpeta();
	}

	@SuppressWarnings("unchecked")
	public void add(String[] atributos, Object[] valoresRequeridos, Object[] valoresNoRequeridos, String nombre){
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject objActual = obj;
		Type newType = new Type();
		newType.setName(nombre);
		newType.setArchivo(this.carpeta+"\\"+nombre);
		int n = atributos.length;
		String atributo = atributos[0];
		Object[] valores = valoresRequeridos;
		Object valor = valores[0];
		String[] requeridos = new String[valoresRequeridos.length];
		String[] valoresTotales = new String[n];
		for (int i=0; i < n; i++){
			objActual.put(atributo, valor);
			valoresTotales[i]=atributos[i];
			if(i<n-1){
				atributo = atributos[i+1];
				if (i<valoresRequeridos.length){
					requeridos[i]=atributos[i];}
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
		newType.setAtributosRString(requeridos);
		newType.setAtributosNR(objActual);
		newType.setAtributos(valoresTotales);
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
				if (current.getNext().getName().equals(name)){
					current.setNext(current.getNext().getNext());
					return;
				}
				current = current.getNext();
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
            	String archivo = carpeta+"\\"+current.getName()+".json";
            	current.setArchivo(archivo);
    			file = new FileWriter(archivo);
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
    public Type find(String name){
    	Type current = root;
        while (current != null) {
       		if (current.getName().equals(name)){
        		return current;
        	}
        	current=current.getNext();
        }
        return null;

	}

}
