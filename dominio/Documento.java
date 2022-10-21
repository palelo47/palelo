package dominio;

import mocs.mockpersistencia;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;


public class Documento {
    private HashMap<String,Integer> docOcs;
    private Integer size;
    private String name;
    private Frase nombreAutor;

    /**
     * Creadora del documento
     * @param path ruta del archivo
     * @throws FileNotFoundException Si la ruta del archivo es incorrecta
     */
    public Documento(String path)throws FileNotFoundException{
        name=path;
        ArrayList<String> docList= mockpersistencia.getfile(path);
        size=docList.size();
        docOcs=listtomap(docList);
    }



    /**
     * Función que pasa de un ArrayList de palabra a HashMap de las palabras y las ocurrencias
     * @param doc ArrayList de entrada
     * @return Hashmap de las palabras y sus ocurrencias
     */
    private static HashMap<String,Integer> listtomap(ArrayList<String> doc){
        HashMap<String,Integer> docOcs =new HashMap<>();
        doc.forEach((s)->{
            if(docOcs.containsKey(s))docOcs.compute(s, (k,v)->v+1);
            else docOcs.put(s, 1);
        });
        return docOcs;
    }


    /**
     * Función que devuelve el nombre del documento
     * @return Nombre del documento
     */
    public String getName(){
        return name;
    }

    /**
     * Función que devuelve el tamaño del documento
     * @return Tamaño del documento.
     */
    public int getSize(){
        return size;
    }

    /**
     * Función que devuelve las ocurrencias de una palabra en el documento
     * @param query palabra a buscar
     * @return Número de ocurrencias de una palabra en el documento
     */
    public int getOcs(String query){
        if(docOcs.get(query)==null)return 0;
        return docOcs.get(query);
    }

    /**
     * Función que expresa si una palabra está en el documento
     * @param key palabra a buscar
     * @return True si la palabra está en el documento
     */
    public boolean contains(String key){
        return docOcs.containsKey(key);
    }

    /**
     * Función que devuelve el HashMap de palabras y sus ocurrencias
     * @return El hashMap de ocurrencias de palabras
     */
    public HashMap<String,Integer> getHMocs(){
        return docOcs;
    }
}
