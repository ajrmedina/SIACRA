/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * NivelAcceso Entity
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@Entity
@Table(name="nivelacceso")
public class NivelAcceso {

    private int idnivelacceso;
    private String nombreacceso;
    /**
     * Get Id Nivel Acceso
     *
     * @return int - IdNivelAcceso
     */
    @Id
    @GeneratedValue
    @Column(name="idnivelacceso")
    public int getId() {
        return idnivelacceso;
    }

    /**
     * Set Id Nivel Acceso
     *
     * @param idnivelacceso int - IdNivelAcceso
     */
    public void setId(int idnivelacceso) {
        this.idnivelacceso = idnivelacceso;
    }

    /**
     * Get Nombre Acceso
     *
     * @return String - NombreAcceso
     */
    @Column(name="nombreacceso")
    public String getAcceso() {
        return nombreacceso;
    }

    /**
     * Set Nombre Acceso
     *
     * @param nombreacceso String - NombreAcceso
     */
    public void setAcceso(String nombreacceso) {
        this.nombreacceso = nombreacceso;
    }


    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("idnivelacceso : ").append(getId());
        strBuff.append(", nombreacceso : ").append(getAcceso());
        return strBuff.toString();
    }
}

