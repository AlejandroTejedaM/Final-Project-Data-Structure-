package Clases;

// @author 21TE0284 y 21TE0704
import Contratos.IPersonasArrayList;
import java.util.ArrayList;
import java.util.List;

public class PersonasArrayList implements IPersonasArrayList {

    List<Personal> persona;

    public PersonasArrayList() {
        persona = new ArrayList<>();
    }

    @Override
    public void AgregarPersona(Personal e) {
        persona.add(e);
    }

    @Override
    public boolean BuscarPersona(String matricula) {
        boolean semaforo = false;
        for (Personal estudiante : persona) {
            if (estudiante.getMatricula().equalsIgnoreCase(matricula)) {
                semaforo = true;
                break;
            }
        }
        return semaforo;
    }

    @Override
    public void Eliminar(Personal e) {
        persona.remove(e);
    }

    @Override
    public void Copiar(List<Personal> estudiante) {
        this.persona = estudiante;
    }

    @Override
    public void ActualizarPersona(int indice, Personal e) {
        persona.set(indice, e);
    }

    @Override
    public List<Personal> ConsultaPersonas() {
        return persona;
    }

    @Override
    public Personal ObtenerPersona(String matricula) {
        Personal e = null;
        for (Personal estudiante : persona) {
            if (estudiante.getMatricula().equalsIgnoreCase(matricula)) {
                e = estudiante;
                break;
            }
        }
        return e;
    }
    
    public int tamannio(){
        return persona.size();
    }

}
