/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sep.vacuna;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Ares
 */
public class Docente implements Serializable{

    private Integer noRegistro;
    private String apellido1;
    private String apellido2;
    private String nombre;
    private Integer edad;
    private String funcion;
    private String cct;
    private String curp;
    private String rfc;
    private String domicilio;
    private String municipio;
    private String areaCentralPertenece;
    private String tipoComorbilidad;
    private String otroPadecimiento;
    private String estatusVacunaAplicada;
    private String nivelEducativo;
    private Integer noCorde;
    private String corde;
    private Integer noSede;
    private String sede;
    private String domicilioSede;
    private Date fechaVacunacionProgramada;
    private Date fechaDescargaFormato;

    

    public String getCorde() {
        return corde;
    }

    public void setCorde(String corde) {
        this.corde = corde;
    }

    public Integer getNoCorde() {
        return noCorde;
    }

    public void setNoCorde(Integer noCorde) {
        this.noCorde = noCorde;
    }

    public Integer getNoRegistro() {
        return noRegistro;
    }

    public void setNoRegistro(Integer noRegistro) {
        this.noRegistro = noRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCct() {
        return cct;
    }

    public void setCct(String cct) {
        this.cct = cct;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getAreaCentralPertenece() {
        return areaCentralPertenece;
    }

    public void setAreaCentralPertenece(String areaCentralPertenece) {
        this.areaCentralPertenece = areaCentralPertenece;
    }

    public String getTipoComorbilidad() {
        return tipoComorbilidad;
    }

    public void setTipoComorbilidad(String tipoComorbilidad) {
        this.tipoComorbilidad = tipoComorbilidad;
    }

    public String getOtroPadecimiento() {
        return otroPadecimiento;
    }

    public void setOtroPadecimiento(String otroPadecimiento) {
        this.otroPadecimiento = otroPadecimiento;
    }

    public String getEstatusVacunaAplicada() {
        return estatusVacunaAplicada;
    }

    public void setEstatusVacunaAplicada(String estatusVacunaAplicada) {
        this.estatusVacunaAplicada = estatusVacunaAplicada;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public Integer getNoSede() {
        return noSede;
    }

    public void setNoSede(Integer noSede) {
        this.noSede = noSede;
    }

    public String getDomicilioSede() {
        return domicilioSede;
    }

    public void setDomicilioSede(String domicilioSede) {
        this.domicilioSede = domicilioSede;
    }

    public Date getFechaVacunacionProgramada() {
        return fechaVacunacionProgramada;
    }

    public void setFechaVacunacionProgramada(Date fechaVacunacionProgramada) {
        this.fechaVacunacionProgramada = fechaVacunacionProgramada;
    }

    public Date getFechaDescargaFormato() {
        return fechaDescargaFormato;
    }

    public void setFechaDescargaFormato(Date fechaDescargaFormato) {
        this.fechaDescargaFormato = fechaDescargaFormato;
    }

    
}
