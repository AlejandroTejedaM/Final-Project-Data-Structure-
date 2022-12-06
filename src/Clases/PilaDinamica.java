package Clases;

import Contratos.IPilaDinamica;
import java.util.HashMap;
import java.util.Stack;

// @author AlexTM
public class PilaDinamica implements IPilaDinamica {

    Stack<HashMap<Integer, Personal[]>> pila;

    public PilaDinamica() {
        pila = new Stack<>();
    }

    @Override
    public boolean Vacia() {
        return pila.empty();
    }

    @Override
    public boolean Llena() {
        return pila.size() == 17;
    }

    @Override
    public void insertar(HashMap<Integer, Personal[]> ob) {
        pila.push(ob);
    }

    @Override
    public HashMap<Integer, Personal[]> Quitar() {
        return pila.pop();
    }

    @Override
    public Object Mostrar() {
        String salida = "";
        for (HashMap<Integer, Personal[]> e : pila) {
            salida += e.toString() + "\n";
        }
        return salida;
    }

    public Stack<HashMap<Integer, Personal[]>> getPila() {
        return pila;
    }

    public int ConocerTamannio() {
        return pila.size();
    }

    public HashMap<Integer, Personal[]> getUltimo() {
        if (Vacia() == true) {
            return null;
        } else {
            return pila.peek();
        }
    }
//
//    public HashMap<Integer, Personal[]> Ultimo() {
//        return pila.lastElement();
//    }

}
