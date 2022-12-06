package Archivos;

import Contratos.ILectura;
import Clases.Personal;
import java.io.BufferedReader;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
// @author 21TE0284 y 21TE0704

public class Lectura implements ILectura {

    File archivo;
    FileReader fr;
    BufferedReader br;

    @Override
    public void DefinirPath(String path) {
        try {
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Queue<Personal> ObtenerPersonalCola() {
        Queue<Personal> colaTemp = new LinkedList<>();
        try {
            String fila = "", temp = "";
            while ((temp = br.readLine()) != null) {
                fila = temp;
                String[] estudianteArray = fila.split(",");

                char tipo = estudianteArray[0].charAt(0);
                String matricula = estudianteArray[1];
                String nombre = estudianteArray[2];
                int semestre = Integer.parseInt(estudianteArray[3]);
                String carrera = estudianteArray[4];
                char grupo = estudianteArray[5].charAt(0);
                char genero = estudianteArray[6].charAt(0);
                
                Personal p = new Personal(tipo, matricula, nombre, semestre, carrera, grupo, genero);
                colaTemp.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colaTemp;
    }

    @Override
    public void CerrarArchivo() {
        try {
            fr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
