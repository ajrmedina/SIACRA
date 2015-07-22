package com.siacra.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * Docente Entity
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */

@Entity
@Table(name="docente")
public class Docente {
    
    @Id
    @GeneratedValue
    @Column(name="iddocente")
    private int idDocente;
    
    @Column(name="aprobardocente", nullable = false)
    private int aprobarDocente;
    
    @OneToOne
    @JoinColumn(name="idusuario")
    private User user;
    
    /**
     * Get Id Docente
     *
     * @return int - IdDocente
     */
    public int getIdDocente() {
        return idDocente;
    }

    /**
     * Set Id Docente
     *
     * @param iddocente int - IdDocente
     */
    public void setIdDocente(int iddocente) {
        this.idDocente = iddocente;
    }

    /**
     * Get Aprobar Docente
     *
     * @return int aprobarDocente - AprobarDocente
     */
    public int getAprobarDocente() {
        return aprobarDocente;
    }

    /**
     * Set Aprobar Docente
     *
     * @param aprobardocente int - AprobarDocente
     */
    public void setAprobarDocente(int aprobardocente) {
        this.aprobarDocente = aprobardocente;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

