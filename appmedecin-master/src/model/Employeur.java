package model;
// Generated Nov 26, 2019, 5:21:03 PM by Hibernate Tools 5.4.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Employeur generated by hbm2java
 */
@Entity
public class Employeur implements java.io.Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Users users;
	private Long kmmax;
	private double longitude;
	private double lattitude;
	private String cartePro;
	@OneToMany(mappedBy = "employeur")
	private Set<Mission > missions = new HashSet<Mission>(0);

	public Employeur() {
	}

	public Employeur(long id, Users users, double longitude, double lattitude) {
		this.id = id;
		this.users = users;
		this.longitude = longitude;
		this.lattitude = lattitude;
	}

	public Employeur(long id, Users users, Long kmmax, double longitude, double lattitude, String cartepro,
			Set<Mission > missions) {
		this.id = id;
		this.users = users;
		this.kmmax = kmmax;
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.cartePro = cartepro;
		this.missions = missions;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Long getKmmax() {
		return this.kmmax;
	}

	public void setKmmax(Long kmmax) {
		this.kmmax = kmmax;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLattitude() {
		return this.lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public String getCartepro() {
		return this.cartePro;
	}

	public void setCartepro(String cartepro) {
		this.cartePro = cartepro;
	}

	public Set<Mission > getMissions() {
		return this.missions;
	}

	public void setMissions(Set<Mission > missions) {
		this.missions = missions;
	}

	@Override
	public String toString() {
		return "Employeur [id=" + id  + ", kmmax=" + kmmax + ", longitude=" + longitude
				+ ", lattitude=" + lattitude + ", cartepro=" + cartePro+"]";
	}

}
