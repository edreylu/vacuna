/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sep.vacuna;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ares
 */
@ManagedBean
@ViewScoped
public class FormatoMB extends Conexion implements Serializable{

    private String curp;
    private String rfc;
    StreamedContent file;

    private List<Docente> docentes;
    private DAO dao;
    private Docente docenteSeleccionado;

    @PostConstruct
    public void init() {
        dao = new DAO();
        docentes = new ArrayList();
        docenteSeleccionado = new Docente();
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

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public Docente getDocenteSeleccionado() {
        return docenteSeleccionado;
    }

    public void setDocenteSeleccionado(Docente docenteSeleccionado) {
        this.docenteSeleccionado = docenteSeleccionado;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public void consultaDocente() {
        System.out.println("Curp: " + curp + "rfc " + rfc);
        docentes = dao.buscaDocente(curp, rfc);
        if (docentes.size() == 1) {
            docenteSeleccionado = docentes.get(0);
            generarFormato();
        }

        for (Docente docente : docentes) {
            System.out.println(docente.getNoRegistro() + " " + docente.getNombre());
        }
    }

    public StreamedContent generarFormato() {
        Conexion conexion = new Conexion();
        Connection conn = null;
        try {

            conn = conexion.getConexion();
            Map parameters = new HashMap();
            
            String imagenes = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/");
            String docpdf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/formato/VACUNA.pdf");
            System.out.println("url: "+imagenes);
            parameters.put("NO_PACIENTE", docenteSeleccionado.getNoRegistro());
            parameters.put("IMG_URL", imagenes);

            String nombreArchivo = docenteSeleccionado.getCurp() + ".pdf";

            String jasperFile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/formato/REPORTE_VACUNA.jrxml");
            JasperReport report = JasperCompileManager.compileReport(jasperFile);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, docpdf);
            InputStream inputS = new FileInputStream(docpdf);
            file = new DefaultStreamedContent(inputS, "application/pdf", nombreArchivo);
           

        } catch (JRException | IOException e) {
            System.out.println("Error al crear formato generarFormato()" + e.getMessage());
        } finally {
            conexion.closeAll(null, null, conn);
        }
  return file;

}

}
