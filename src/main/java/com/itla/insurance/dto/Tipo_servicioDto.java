/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dto;

/**
 *
 * @author Sulenni
 */
public class Tipo_servicioDto {
    private int id;
    private String nombre;
    private String nombre_abreviado;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre_abreviado
     */
    public String getNombre_abreviado() {
        return nombre_abreviado;
    }

    /**
     * @param nombre_abreviado the nombre_abreviado to set
     */
    public void setNombre_abreviado(String nombre_abreviado) {
        this.nombre_abreviado = nombre_abreviado;
    }
}
