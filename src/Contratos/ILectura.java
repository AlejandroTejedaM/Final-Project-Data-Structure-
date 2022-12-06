package Contratos;

// @author 21TE0284 y 21TE0704
import Clases.Personal;
import java.util.Queue;

public interface ILectura {

    void DefinirPath(String path);

    Queue<Personal> ObtenerPersonalCola();

    void CerrarArchivo();
}
