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
public class Tipo_PlanDto {
    private int id;
    private String nombre;
    private int id_cobertura;

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
     * @return the id_cobertura
     */
    public int getId_cobertura() {
        return id_cobertura;
    }

    /**
     * @param id_cobertura the id_cobertura to set
     */
    public void setId_cobertura(int id_cobertura) {
        this.id_cobertura = id_cobertura;
    }
}
