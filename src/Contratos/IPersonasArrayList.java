package Contratos;

// @author 21TE0284 y 21TE0704
import Clases.Personal;
import java.util.List;

public interface IPersonasArrayList {

    void AgregarPersona(Personal e);

    void ActualizarPersona(int indice, Personal e);

    List<Personal> ConsultaPersonas();

    boolean BuscarPersona(String matricula);

    Personal ObtenerPersona(String matricula);

    void Eliminar(Personal e);

    void Copiar(List<Personal> personas);

}
