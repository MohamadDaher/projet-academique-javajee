package model;
// Generated Nov 26, 2019, 5:21:03 PM by Hibernate Tools 5.4.3.Final

import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Mission generated by hbm2java
 */
@Entity
public class Mission implements java.io.Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Employeur employeur;
	@ManyToOne
	private Medecinremp medecinremp;
	private Boolean visiteDomicile;
	private BigInteger kmMaxVisite;
	private Boolean activiteSeule;
	private BigInteger retrocession;
	private Boolean secreteriat;
	private String typePatient;
	private String description;
	private LocalDate periode;

	public Mission() {
	}

	public Mission(long id, Employeur employeur) {
		this.id = id;
		this.employeur = employeur;
	}

	public Mission(long id, Employeur employeur, Medecinremp medecinremp, Boolean visiteDomicile,
			BigInteger kmMaxVisite, Boolean activiteSeule, BigInteger retrocession, Boolean secreteriat,
			String typePatient, String description, LocalDate periode) {
		this.id = id;
		this.employeur = employeur;
		this.medecinremp = medecinremp;
		this.visiteDomicile = visiteDomicile;
		this.kmMaxVisite = kmMaxVisite;
		this.activiteSeule = activiteSeule;
		this.retrocession = retrocession;
		this.secreteriat = secreteriat;
		this.typePatient = typePatient;
		this.description = description;
		this.periode = periode;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employeur getEmployeur() {
		return this.employeur;
	}

	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}

	public Medecinremp getMedecinremp() {
		return this.medecinremp;
	}

	public void setMedecinremp(Medecinremp medecinremp) {
		this.medecinremp = medecinremp;
	}

	public Boolean getVisiteDomicile() {
		return this.visiteDomicile;
	}

	public void setVisiteDomicile(Boolean visiteDomicile) {
		this.visiteDomicile = visiteDomicile;
	}

	public BigInteger getKmMaxVisite() {
		return this.kmMaxVisite;
	}

	public void setKmMaxVisite(BigInteger kmMaxVisite) {
		this.kmMaxVisite = kmMaxVisite;
	}

	public Boolean getActiviteSeule() {
		return this.activiteSeule;
	}

	public void setActiviteSeule(Boolean activiteSeule) {
		this.activiteSeule = activiteSeule;
	}

	public BigInteger getRetrocession() {
		return this.retrocession;
	}

	public void setRetrocession(BigInteger retrocession) {
		this.retrocession = retrocession;
	}

	public Boolean getSecreteriat() {
		return this.secreteriat;
	}

	public void setSecreteriat(Boolean secreteriat) {
		this.secreteriat = secreteriat;
	}

	public String getTypePatient() {
		return this.typePatient;
	}

	public void setTypePatient(String typePatient) {
		this.typePatient = typePatient;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPeriode() {
		return this.periode;
	}

	public void setPeriode(LocalDate periode) {
		this.periode = periode;
	}

}