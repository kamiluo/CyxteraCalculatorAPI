package com.caforerof.calculator.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "operators")
@NamedQuery(name = "Operator.findActiveBySessionId", query = "SELECT c FROM Operator c where c.sessionId.id = :sessionId and c.status = 'Activo' ")
@NamedQuery(name = "Operator.findAll", query = "SELECT c FROM Operator c ")
@NamedQuery(name = "Operator.updateStatusBySessionIdActive", query = "UPDATE Operator c set c.status = 'Inactivo' where c.sessionId.id = :sessionId and c.status = 'Activo' ")
public class Operator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String value;

	private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    private SessionId sessionId;
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public SessionId getSessionId() {
		return sessionId;
	}

	public void setSessionId(SessionId sessionId) {
		this.sessionId = sessionId;
	}


}
