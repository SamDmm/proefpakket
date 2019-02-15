package be.vdab.proefpakket.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.proefpakket.valueobjects.Adres;

@Entity
@Table(name = "bestellingen")
public class Bestelling implements Serializable {
	public interface Stap1 {}
	public interface Stap2 {}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate datum = LocalDate.now();
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String voornaam;
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String familienaam;
	@NotNull(groups = Stap1.class)
	@Email(groups = Stap1.class)
	private String emailAdres;
	@Valid
	@Embedded
	private Adres adres;
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "brouwerid")
	private Brouwer brouwer;
	
	public long getId() {
		return id;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public String getEmailAdres() {
		return emailAdres;
	}
	public Adres getAdres() {
		return adres;
	}
	public Brouwer getBrouwer() {
		return brouwer;
	}
	
}
