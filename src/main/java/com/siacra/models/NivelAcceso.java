package com.siacra.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class NivelAcceso implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idnivelacceso", unique = true, nullable = false)
    private int idnivelacceso;
    
    @Column(name="nombreacceso", nullable = false, length = 25)
    private String nombreacceso;
    
    @Column(name="na_estado", nullable = false)
    private boolean estado;
    
    @OneToMany(mappedBy="nivel")
    private Set<User> usuario;
    
    /**
     * Get Id Nivel Acceso
     *
     * @return int - IdNivelAcceso
     */
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
    
    /**
     * Get Estado Acceso
     *
     * @return boolean - EstadoAcceso
     */
    public boolean getEstadoAcceso() {
        return estado;
    }

    /**
     * Set Estado Acceso
     *
     * @param estado boolean - EstadoAcceso
     */
    public void setEstadoAcceso(boolean estado) {
        this.estado = estado;
    }
    
    /**
     * Get User
     *
     * @return usuario Set<User>
     */
    public Set<User> getUser() {
        return usuario;
    }
    
    /**
     * Set User
     *
     * @param usuario Set<User>
     */
    public void setUser(Set<User> usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        StringBuilder strBuff = new StringBuilder();
        strBuff.append("idnivelacceso : ").append(getId());
        strBuff.append(", nombreacceso : ").append(getAcceso());
        return strBuff.toString();
    }
}

