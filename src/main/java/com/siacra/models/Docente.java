package com.siacra.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class Docente implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="iddocente", unique = true, nullable = false)
    private int idDocente;
    
    @Column(name="aprobardocente", nullable = false)
    private boolean aprobarDocente;
    
    @OneToOne
    @JoinColumn(name="idusuario")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
    
    @ManyToOne
    @JoinColumn(name="idacuerdo")
    private Acuerdo acuerdo;
    
    @ManyToOne
    @JoinColumn(name="idcategoria")
    private Categoria categoria;
    
    /*@OneToMany(mappedBy="docente")
    private Set<Responsabilidad> responsabilidad;*/
    
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
     * @return boolean aprobarDocente - AprobarDocente
     */
    public boolean getAprobarDocente() {
        return aprobarDocente;
    }

    /**
     * Set Aprobar Docente
     *
     * @param aprobardocente boolean - AprobarDocente
     */
    public void setAprobarDocente(boolean aprobardocente) {
        this.aprobarDocente = aprobardocente;
    }
    
    /**
     * Get User
     *
     * @return user User
     */
    public User getUser() {
        return this.user;
    }
    
    /**
     * Set User
     *
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Get Escuela
     *
     * @return escuela Escuela
     */
    
    public Escuela getEscuela() {
        return this.escuela;
    }
    
    /**
     * Set Escuela
     *
     * @param escuela Escuela
     */
    
    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
    
    /**
     * Get Categoria
     *
     * @return categoria Categoria
     */
    
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    /**
     * Set Categoria
     *
     * @param categoria Categoria
     */
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }

    
    /**
     * Get Responsabilidad
     *
     * @return responsabilidad Set<Responsabilidad>
     */
    /*public Set<Responsabilidad> getResponsabilidad() {
        return responsabilidad;
    }*/
    
    /**
     * Set Responsabilidad
     *
     * @param responsabilidad Set<Responsabilidad>
     */
    /*public void setResponsabilidad(Set<Responsabilidad> responsabilidad) {
        this.responsabilidad = responsabilidad;
    }*/
    
}

