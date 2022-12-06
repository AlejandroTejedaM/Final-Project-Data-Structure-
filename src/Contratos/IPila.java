package Contratos;

import Clases.Personal;

public interface IPila {

    boolean Vacio();

    boolean Llena();

    void Insertar(Personal o);

    Object Quitar();

    Object Mostrar();

}
