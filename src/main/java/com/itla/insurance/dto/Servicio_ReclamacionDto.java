/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dto;

/**
 *
 * @author Giame
 */
public class Servicio_ReclamacionDto {

    /**
     * @return the id_reclamacion
     */
    public int getId_reclamacion() {
        return id_reclamacion;
    }

    /**
     * @param id_reclamacion the id_reclamacion to set
     */
    public void setId_reclamacion(int id_reclamacion) {
        this.id_reclamacion = id_reclamacion;
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

    
    private int id;
    private int id_reclamacion;
    private int id_servicio;

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
}
