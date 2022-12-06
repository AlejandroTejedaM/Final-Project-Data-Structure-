package Archivos;

import Contratos.IEscritura;
import java.io.*;
// @author 21TE0284 y 21TE0704

public class Escritura implements IEscritura {

    File archivo;
    FileWriter fw;
    PrintWriter pw;

    @Override
    public void DefinirPath(String path, boolean append) {
        try {
            archivo = new File(path);
            fw = new FileWriter(archivo, append);
            pw = new PrintWriter(archivo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Escribir(String contenido) {
        try {
            pw.println(contenido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void CerrarArchivo() {
        try {
            fw.close();
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

  

}
