package mocs;

import dominio.Documento;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class mockpersistencia {

    /**
     * Función que sirve para insertar documentos en Conjunto de documentos de forma automática
     * @return  Documentos a insertar
     * @throws FileNotFoundException Si alguno de los documentos no está bien puesto.
     */
    public static Set<Documento> auto()throws FileNotFoundException {
        Set<Documento> docs=new LinkedHashSet<>() ;
        docs.add(new Documento("./txts/texto1.txt"));
        docs.add(new Documento("./txts/texto2.txt"));
        docs.add(new Documento("./txts/texto3.txt"));
        docs.add(new Documento("./txts/texto4.txt"));
        docs.add(new Documento("./txts/texto5.txt"));
        docs.add(new Documento("./txts/texto6.txt"));
        docs.add(new Documento("./txts/texto7.txt"));
        return docs;
    }

    /**
     * Función que pasa de un archivo txt a la lista de las palabras que contiene.
     * TODO: OBTENER NOMBRE / NOMBRE AUTOR , controlar lo que considera palabras
     * @param path ruta del archivo
     * @return Las palabras del archivo en un array
     * @throws FileNotFoundException si no encuentra el archivo con el nombre
     */
    public static ArrayList<String> getfile(String path)throws FileNotFoundException{
        File fd=new File(path);
        ArrayList<String> doc=new ArrayList<>();
        Scanner sfd=new Scanner(fd);
        while(sfd.hasNext()){
            doc.add(sfd.next());
        }
        sfd.close();
        return doc;
    }
}
