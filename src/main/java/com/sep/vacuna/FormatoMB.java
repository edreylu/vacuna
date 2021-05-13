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
import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ares
 */
@ManagedBean
@ViewScoped
public class FormatoMB extends Conexion implements Serializable {

    private String curp;
    private String rfc;
    StreamedContent file;

    private List<Docente> docentes;
    private DAO dao;
    private Docente docenteSeleccionado;
    private boolean btnDescarga = true;

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

    public boolean isBtnDescarga() {
        return btnDescarga;
    }

    public void setBtnDescarga(boolean btnDescarga) {
        this.btnDescarga = btnDescarga;
    }

    public void consultaDocente() {
        file = null;
        System.out.println("Curp: " + curp + "rfc " + rfc);
        docentes = dao.buscaDocente(curp, rfc);
        if (docentes.size() == 1) {
            docenteSeleccionado = docentes.get(0);
            generarFormato();
        } else if (docentes.size() > 1) {
            for (Docente docente : docentes) {
                System.out.println("docente " + docente.getNombre());
            }
            /*   PrimeFaces.current().ajax().update(":frmDocenteSelect:dtDocenteSelect");*/
            PrimeFaces.current().executeScript("PF('dlgSeleccion').show();");
        } else {
            System.out.println("No se encontro usuario");
        }

        for (Docente docente : docentes) {
            System.out.println(docente.getNoRegistro() + " " + docente.getNombre());
        }
    }

    public void consultaDocente2() {
        System.out.println("Curp: " + docenteSeleccionado.getCurp() + "rfc " + docenteSeleccionado.getRfc());
        if (docenteSeleccionado != null) {
            generarFormato();
        }
    }

    public StreamedContent generarFormato() {
        Conexion conexion = new Conexion();
        Connection conn = null;
        try {

            conn = conexion.getConexion();
            Map parameters = new HashMap();
            String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/%s");
            Function<String, String> getPath = path -> String.format(fullPath, path);

            String imagenes = getPath.apply("/resources/img/");
            String outputPDF = getPath.apply("/resources/formato/VACUNA.pdf");
            String jasperFile = getPath.apply("/resources/formato/REPORTE_VACUNA.jasper");
            int noPaciente = docenteSeleccionado.getNoRegistro();
            String fileName = String.format("%s.pdf", docenteSeleccionado.getCurp());

            parameters.put("NO_PACIENTE", noPaciente);
            parameters.put("IMG_URL", imagenes);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPDF);
            InputStream inputSream = new FileInputStream(outputPDF);
            file = new DefaultStreamedContent(inputSream, "application/pdf", fileName);

            dao.actualizarFechaDescarga(docenteSeleccionado.getNoRegistro());

        } catch (JRException | IOException e) {
            System.out.println("Error al crear formato generarFormato()" + e.getMessage());
        } finally {
            conexion.closeAll(null, null, conn);
        }
        return file;

    }

    public void habilitaBtn() {
        btnDescarga = false;
    }

}
