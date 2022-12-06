package Contratos;

// @author 21TE0284 y 21TE0704
public interface IEscritura {

    void DefinirPath(String path, boolean append);

    void Escribir(String contenido);
    
    void CerrarArchivo();
}
