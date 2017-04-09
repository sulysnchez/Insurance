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
public class ServicioDto {
    private int id;
    private int id_afiliado;
    private int id_prestador;
    private int monto_reclamado;
    private int monto_pagar;
    private int monto_doferencia;
    private int no_autorizacion;
    private int no_reclamacion;
    private int monto_total;
    private String codigo;
    private int id_cobertura;
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
     * @return the monto_reclamado
     */
    public int getMonto_reclamado() {
        return monto_reclamado;
    }

    /**
     * @param monto_reclamado the monto_reclamado to set
     */
    public void setMonto_reclamado(int monto_reclamado) {
        this.monto_reclamado = monto_reclamado;
    }

    /**
     * @return the monto_pagar
     */
    public int getMonto_pagar() {
        return monto_pagar;
    }

    /**
     * @param monto_pagar the monto_pagar to set
     */
    public void setMonto_pagar(int monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    /**
     * @return the monto_doferencia
     */
    public int getMonto_doferencia() {
        return monto_doferencia;
    }

    /**
     * @param monto_doferencia the monto_doferencia to set
     */
    public void setMonto_doferencia(int monto_doferencia) {
        this.monto_doferencia = monto_doferencia;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the no_autorizacion
     */
    public int getNo_autorizacion() {
        return no_autorizacion;
    }

    /**
     * @param no_autorizacion the no_autorizacion to set
     */
    public void setNo_autorizacion(int no_autorizacion) {
        this.no_autorizacion = no_autorizacion;
    }

    /**
     * @return the no_reclamacion
     */
    public int getNo_reclamacion() {
        return no_reclamacion;
    }

    /**
     * @param no_reclamacion the no_reclamacion to set
     */
    public void setNo_reclamacion(int no_reclamacion) {
        this.no_reclamacion = no_reclamacion;
    }

    /**
     * @return the monto_total
     */
    public int getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }
}
