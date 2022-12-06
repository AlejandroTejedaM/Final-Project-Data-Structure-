package Clases;

import Contratos.IPila;

public class PilaEstatica implements IPila {
    
    int tope;
    Object[] pila;

    public PilaEstatica(int n) {
        pila = new Personal[n];
        tope = 0;
    }

    @Override
    public boolean Vacio() {
        if (tope == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean Llena() {
        if (tope == pila.length) {
            return true;
        }
        return false;
    }

    @Override
    public void Insertar(Personal obj) {
        pila[tope] = obj;
        tope++;

    }

    @Override
    public Object Quitar() {
        tope--;
        return pila[tope];
    }

    @Override
    public Object Mostrar() {
        String salida = "";
        for (int i = 0; i < tope; i++) {
            salida += pila[i] + "\n";
        }
        return salida;

    }

//    public int contar() {
//        int contador = 0;
//        tope aux = pila;
//        while (aux != null) {
//            contador++;
//            aux = aux.siguiente;
//        }
//        return contador;
//    }

   
}
