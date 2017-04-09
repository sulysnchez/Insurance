/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dto;

import java.util.Date;

/**
 *
 * @author Sulenni
 */
public class AutorizacionDto {
    private int id;
    private int id_servicio;
    private Date fecha_autorizacion;
    private int secuencia_laboratorio;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_servicio
     */
    public int getId_servicio() {
        return id_servicio;
    }

    /**
     * @param id_servicio the id_servicio to set
     */
    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    /**
     * @return the fecha_autorizacion
     */
    public Date getFecha_autorizacion() {
        return fecha_autorizacion;
    }

    /**
     * @param fecha_autorizacion the fecha_autorizacion to set
     */
    public void setFecha_autorizacion(Date fecha_autorizacion) {
        this.fecha_autorizacion = fecha_autorizacion;
    }

    /**
     * @return the secuencia_laboratorio
     */
    public int getSecuencia_laboratorio() {
        return secuencia_laboratorio;
    }

    /**
     * @param secuencia_laboratorio the secuencia_laboratorio to set
     */
    public void setSecuencia_laboratorio(int secuencia_laboratorio) {
        this.secuencia_laboratorio = secuencia_laboratorio;
    }
}
