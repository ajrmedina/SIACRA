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
 * User Entity
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 */

@Entity
@Table(name = "usuario")
public class User implements Serializable{
        
        @Id
        @GeneratedValue
	@Column(name = "idusuario", unique = true, nullable = false)
        private int idUsuario;
        
        @Column(name = "nombreusuario", nullable = false, length = 30)
	private String nombreUsuario;
        
        @Column(name = "contrasenia", nullable = false, length = 30)
	private String contrasenia;
        
        @Column(name = "nombres", nullable = false, length = 50)
        private String nombres;

        @Column(name = "apellidos", nullable = false, length = 50)
        private String apellidos;
        
        @Column(name = "escuela", nullable = false, length = 50)
        private String escuela;
        
        @Column(name = "estadousuario", nullable = false)
	private boolean estadoUsuario;
        
        @Column(name = "esdocente", nullable = false)
	private boolean esDocente;
        
        @Column(name = "sesion")
	private boolean sesion;
        
        @ManyToOne
        @JoinColumn(name="idnivelacceso")
	private NivelAcceso nivel;
        
        
        @OneToOne(mappedBy = "user")
        private Docente docente;
        
	public int getIdUsuario() {
		return this.idUsuario;
	}
 
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
        
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
 
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
        
	public String getContrasenia() {
		return this.contrasenia;
	}
 
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
        
	public String getNombres() {
		return this.nombres;
	}
 
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
        
	public String getApellidos() {
		return this.apellidos;
	}
 
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
        
        public String getEscuela() {
		return this.escuela;
	}
 
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
        
	public boolean getEstadoUsuario() {
		return this.estadoUsuario;
	}
 
	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
        
        public boolean getEsDocente() {
		return this.esDocente;
	}
 
	public void setEsDocente(boolean esDocente) {
		this.esDocente = esDocente;
	}
        
	public NivelAcceso getNivel() {
		return this.nivel;
	}
 
	public void setNivel(NivelAcceso nivel) {
		this.nivel = nivel;
	}
        
        public Docente getDocente() {
            return this.docente;
        }

        public void setDocente(Docente docente) {
            this.docente = docente;
        }

        public boolean isSesion() {
            return sesion;
        }

        public void setSesion(boolean sesion) {
            this.sesion = sesion;
        }

    public boolean getSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
        
}
