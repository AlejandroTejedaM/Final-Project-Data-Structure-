package Clases;

import Contratos.ICola;
import java.util.LinkedList;
import java.util.Queue;

public class ColaDinamica implements ICola {

   int frente, atras;

    Queue<Personal> cola;

    public ColaDinamica() {
        cola = new LinkedList<>();

    }

    @Override
    public boolean Vacio() {
        return cola.isEmpty();

    }

    @Override
    public boolean Llena() {
        return false;

    }

    @Override
    public void Insertar(Personal o) {
        cola.add(o);

    }

    @Override
    public Personal Quitar() {
        return cola.poll();

    }

    @Override
    public Object Mostrar() {
        String salida = "";
        for (Personal item : cola) {
            salida += item.toString() + "\n";
        }
        return salida;
    }

    public Object getHead() {
        return cola.peek();
    }

    public int getAtras() {
        return atras;
    }

    public int getFrente() {
        return frente;
    }

    public Queue<Personal> getCola() {
        return cola;
    }

    public void CopiarCola(Queue<Personal> alumnos) {
        this.cola = alumnos;
    }


}
