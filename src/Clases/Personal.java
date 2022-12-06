package Clases;

public class Personal {

    private char tipo; //D    A
    private String matricula;
    private String nombre;
    private int semestre;
    private String carrera;
    private char grupo;
    private char genero;

    public Personal(char tipo, String matricula, String nombre, int semestre,
            String carrera, char grupo, char genero) {
        setTipo(tipo);
        setMatricula(matricula);
        setNombre(nombre);
        setSemestre(semestre);
        setCarrera(carrera);
        setGrupo(grupo);
        setGenero(genero);

    }

//    public Personal(char tipo, String matricula, String nombre) {
//        setTipo(tipo);
//        setMatricula(matricula);
//        setNombre(nombre);
//    }
//
//    public Personal(char grupo, String carrera, String especi, int semestre, char genero, int edad, char modalidad) {
//        setGrupo(grupo);
//        setCarrera(carrera);
//        setEspeci(especi);
//        setSemestre(semestre);
//        setGenero(genero);
//        setEdad(edad);
//        setModalidad(modalidad);
//    }
    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "" + tipo
                + "," + matricula
                + "," + nombre
                + "," + semestre
                + "," + carrera
                + "," + grupo
                + "," + genero;
    }

}
