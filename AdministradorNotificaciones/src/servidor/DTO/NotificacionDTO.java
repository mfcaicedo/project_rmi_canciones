package servidor.DTO;

import java.io.Serializable;

public class NotificacionDTO implements Serializable {
    private String fechaRegistro;
    private int cantidadCancionesMp3;
    private int cantidadCancionesfFLAC;
    private double espacioTotal;

    public NotificacionDTO(String fechaRegistro, int cantidadCancionesMp3, int cantidadCancionesfFLAC, double espacioTotal) {
        this.fechaRegistro = fechaRegistro;
        this.cantidadCancionesMp3 = cantidadCancionesMp3;
        this.cantidadCancionesfFLAC = cantidadCancionesfFLAC;
        this.espacioTotal = espacioTotal;
    }
    public NotificacionDTO(){}

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getCantidadCancionesMp3() {
        return cantidadCancionesMp3;
    }

    public void setCantidadCancionesMp3(int cantidadCancionesMp3) {
        this.cantidadCancionesMp3 = cantidadCancionesMp3;
    }

    public int getCantidadCancionesfFLAC() {
        return cantidadCancionesfFLAC;
    }

    public void setCantidadCancionesfFLAC(int cantidadCancionesfFLAC) {
        this.cantidadCancionesfFLAC = cantidadCancionesfFLAC;
    }

    public double getEspacioTotal() {
        return espacioTotal;
    }

    public void setEspacioTotal(int espacioTotal) {
        this.espacioTotal = espacioTotal;
    }
}
