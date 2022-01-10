//package com.daniloperez.academia.domain;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.MapsId;
//import javax.persistence.OneToOne;
//
//import com.daniloperez.academia.domain.enums.EstadoPagamento;
//
//@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
//public class Pagamento  implements Serializable {
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	private Integer id;
//	private EstadoPagamento estado;
//	
//	@OneToOne
//	@JoinColumn(name = "matricula_id")
//	@MapsId
//	private Matricula matricula;
//	
//	public Pagamento() {
//			
//	}
//
//	public Pagamento(Integer id, EstadoPagamento estado, Matricula matricula) {
//		super();
//		this.id = id;
//		this.estado = estado;
//		this.matricula = matricula;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public EstadoPagamento getEstado() {
//		return estado;
//	}
//
//	public void setEstado(EstadoPagamento estado) {
//		this.estado = estado;
//	}
//
//	public Matricula getMatricula() {
//		return matricula;
//	}
//
//	public void setMatricula(Matricula matricula) {
//		this.matricula = matricula;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pagamento other = (Pagamento) obj;
//		return Objects.equals(id, other.id);
//	}
//}