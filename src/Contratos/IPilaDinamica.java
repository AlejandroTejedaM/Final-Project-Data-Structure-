package Contratos;

// @author AlexTM
import Clases.Personal;
import java.util.HashMap;

public interface IPilaDinamica {

    boolean Vacia();

    boolean Llena();

    void insertar(HashMap<Integer, Personal[]> ob);

    HashMap<Integer, Personal[]> Quitar();
    
    Object Mostrar();

}
