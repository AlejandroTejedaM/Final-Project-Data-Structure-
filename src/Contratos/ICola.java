package Contratos;

import Clases.Personal;

public interface ICola {

    boolean Vacio();

    boolean Llena();

    void Insertar(Personal o);

    Object Quitar();

    Object Mostrar();


}
