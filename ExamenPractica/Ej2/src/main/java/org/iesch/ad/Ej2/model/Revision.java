package org.iesch.ad.Ej2.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Revision {
    private Boolean apto;
    private LocalDateTime fecha;
    private LocalDateTime caduca;

    public Revision() {
    }

    public Boolean getApto() {
        return apto;
    }

    public void setApto(Boolean apto) {
        this.apto = apto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getCaduca() {
        return caduca;
    }

    public void setCaduca(LocalDateTime caduca) {
        this.caduca = caduca;
    }
}
