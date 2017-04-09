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
public class Tipo_CoberturaDto {
    private int id;
    private int porciento;

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
     * @return the porciento
     */
    public Integer getPorciento() {
        return porciento;
    }

    /**
     * @param porciento the porciento to set
     */
    public void setPorciento(int porciento) {
        this.porciento = porciento;
    }
}
