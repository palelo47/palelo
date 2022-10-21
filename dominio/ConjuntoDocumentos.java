package dominio;


import mocs.mockpersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class ConjuntoDocumentos {
    private Set<Documento> docsList;

    /**
     *  Creadora de clase con parámetros por defecto
     *  TODO:Modificar, esto se usa para test inicial
     * @throws Exception Archivos mal
     */
    ConjuntoDocumentos()throws Exception{
        docsList= mockpersistencia.auto();
    }

    /**
     * Función que devuelve el número de documentos
     * @return Número de documentos que existe en el conjunto
     */
    public int getNumDocs(){
        return docsList.size();
    }

    /**
     * Función que devuelve el número de documentos que contiene la palabra seleccionada.
     * @param query Palabra a buscar.
     * @return Número de documentos que contiene la palabra.
     */
    public int ocs(String query){
        int ocs=0;
        for(Documento d:docsList){
            if (d.contains(query))++ocs;
        }
        return ocs;
    }

    /**
     * Función que devuelve el documento con el nombre indicado
     * @param name Nombre del documento.
     * @return Devuelve el documento con nombre name si existe, si no devuelve null.
     */
    public Documento getDoc(String name){
        for (Documento d :docsList){
            if (d.getName().equals(name))return d;
        }
        return null;
    }

    /**
     * Función que obtiene el valor de la intersección de los valores
     * TODO: corregir, tendria que devolver 1 en el caso de que dos documentos fueran iguales
     * @param act dominio.Documento a comparar 1
     * @param comp dominio.Documento a comparar 2
     * @return  Parecido entre ambos documentos
     */
    public double VSS(Documento act,Documento comp){
        Integer actSize=act.getSize();
        Integer compSize=comp.getSize();
        double Moduloact=getModulo(act);
        double Modulocomp=getModulo(comp);
        
        HashMap<String,Integer> dact=act.getHMocs();
        HashMap<String,Integer> dcomp=comp.getHMocs();

        HashMap<String,Integer> dactmerged=new HashMap<>();
        HashMap<String,Integer> dcompmerged=new HashMap<>();
        
        intersectHashMaps(dact, dcomp, dactmerged, dcompmerged);

        System.out.println(dact.size()+" "+dcomp.size()+" "+dactmerged.size());//eliminar, para ver resultados

        double ret=MapDOT(dactmerged, dcompmerged, actSize, compSize);
        return ret/(Moduloact*Modulocomp);
        
    }

    /**
     * Función que devuelve los hashmap de las intersecciones de los dos HashMap de entrada
     * @param d1 HashMap de entrada 1
     * @param d2 HashMap de entrada 2
     * @param d1ret Hashmap a rellenar con las palabras que se intersectan entre los dos hashmap de entrada,
     *              con los valores de ocurrencias del primer HashMap
     * @param d2ret Hashmap a rellenar con las palabras que se intersectan entre los dos hashmap de entrada,
     *              con los valores de ocurrencias del segundo HashMap
     */
    private static void intersectHashMaps(HashMap<String,Integer> d1,HashMap<String,Integer> d2, HashMap<String,Integer> d1ret,HashMap<String,Integer> d2ret){
        d1.forEach((k,v)->{
            if (d2.containsKey(k)) {
                d1ret.put(k, v);
                d2ret.put(k, d2.get(k));
            }
        });
    }

    /**
     * Función que devuelve el módulo vectorial de un espacio vectorial
     * Usamos tf, idf normal
     * @param d dominio.Documento del cual se extrae el espacio vectorial
     * @return módulo vectorial del documento
     */
    private  double getModulo(Documento d){
        double res=0.0;
        ArrayList<Double> partres=new ArrayList<>();
        int size=d.getSize();
        d.getHMocs().forEach((k,v)->{
            double tf=((double)v/size);
            double idf=((double)ocs(k)/docsList.size());
            partres.add(Math.sqrt(Math.pow(tf*idf, 2)));
        });
        for (double i: partres){
            res+=i;
        }
        return res;
    }

    /**
     * Función que realiza la operiacion DOT entre dos espacios vectoriales
     * Usamos tf, idf normal
     * @param doc1int Espacio vectorial 1
     * @param doc2int Espacio vectorial 2
     * @param size1 Palabras totales del documento 1 de donde se saca el espacio vectorial 1
     * @param size2 Palabras totales del documento 2 de donde se saca el espacio vectorial 2
     * @return DOT entre el espacio vectorial 1 y 2
     */
    private double MapDOT(HashMap<String,Integer>doc1int,HashMap<String,Integer>doc2int,Integer size1,Integer size2){
        double res=0;
        ArrayList<Double> partres=new ArrayList<>();
        doc1int.forEach((k,v)->{
            double tf1=(double)doc1int.get(k)/size1;
            double tf2=(double)doc2int.get(k)/size2;
            double idf=(double)ocs(k)/docsList.size();
            partres.add(tf1*tf2*idf);
        });
        for (double i: partres){
            res+=i;
        }
        return res;
    }



    public static void main (String[] args) throws Exception{
        ConjuntoDocumentos docs=new ConjuntoDocumentos();

        Documento d = docs.getDoc("./txts/texto1.txt");
        for (Documento db :docs.docsList){
            System.out.println(docs.VSS(d,db));
        }
        
    }
}
