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
public class Prestador_ServicioDto {

    /**
     * @return the id_prestador
     */
    public int getId_prestador() {
        return id_prestador;
    }

    /**
     * @param id_prestador the id_prestador to set
     */
    public void setId_prestador(int id_prestador) {
        this.id_prestador = id_prestador;
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
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    private int id;
    private int id_prestador;
    private int id_servicio;
    private float precio;
    private String nombre_servicio;

    /**
     * @return the nombre_servicio
     */
    public String getNombre_servicio() {
        return nombre_servicio;
    }

    /**
     * @param nombre_servicio the nombre_servicio to set
     */
    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

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
