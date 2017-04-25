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
public class ReclamacionDto {
    private int id;
    private int id_tipo_servicio;
    private String diagnostico;
    private int id_afiliado;
    private String nombre_afiliado;
    private int id_prestador;
    private String nombre_prestador;
    private boolean pagado;
    private float totalAPagar;

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
     * @return the id_tipo_servicio
     */
    public int getId_tipo_servicio() {
        return id_tipo_servicio;
    }

    /**
     * @param id_tipo_servicio the id_tipo_servicio to set
     */
    public void setId_tipo_servicio(int id_tipo_servicio) {
        this.id_tipo_servicio = id_tipo_servicio;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return the id_afiliado
     */
    public int getId_afiliado() {
        return id_afiliado;
    }

    /**
     * @param id_afiliado the id_afiliado to set
     */
    public void setId_afiliado(int id_afiliado) {
        this.id_afiliado = id_afiliado;
    }
    
    /**
     * @return the nombre_afiliado
     */
    public String getNombre_afiliado() {
        return nombre_afiliado;
    }

    /**
     * @param nombre_afiliado the nombre_afiliado to set
     */
    public void setNombre_afiliado(String nombre_afiliado) {
        this.nombre_afiliado = nombre_afiliado;
    }

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
     * @return the nombre_prestador
     */
    public String getNombre_prestador() {
        return nombre_prestador;
    }

    /**
     * @param nombre_prestador the nombre_prestador to set
     */
    public void setNombre_prestador(String nombre_prestador) {
        this.nombre_prestador = nombre_prestador;
    }
    
    /**
     * @return the pagado
     */
    public boolean getPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
    
    /**
     * @return the totalAPagar
     */
    public float getTotalAPagar() {
        return totalAPagar;
    }

    /**
     * @param totalAPagar the totalAPagar to set
     */
    public void setTotalAPagar(float totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}
