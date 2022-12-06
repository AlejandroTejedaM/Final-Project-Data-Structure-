package Clases;

// @author AlexTM
public class Unidades {

    private int Id;
    private String ruta;
    private String destino;
    
    public Unidades(int Id, String ruta, String destino) {
        setId(Id);
        setDestino(destino);
        setRuta(ruta);
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "" + Id
                + "," + ruta
                + "," + destino;
    }

}
