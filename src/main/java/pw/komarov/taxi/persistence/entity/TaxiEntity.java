package pw.komarov.taxi.persistence.entity;

import javax.persistence.Transient;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.Singular;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAXIS")
@XmlType(propOrder = {"id", "name", "phone", "citiesId"})
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="taxi")
public class TaxiEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	public TaxiEntity(int id) { this.id = id; }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false)
	@Getter
	private Integer id;
	
	public void setId(Integer id) {
		if(this.id == null)
			this.id = id;
	}
	
	@Column(name = "NAME", nullable = false, unique = true)
	@Getter @Setter	
	private String name;

	@Column(name = "PHONE", nullable = false, unique = true)
	@Getter @Setter	
	private String phone;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "TAXI_CITY", joinColumns = {@JoinColumn(name = "TAXI_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CITY_ID",
			nullable = false, updatable = false) })
	@Getter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Singular
	@XmlTransient
	private final Set<CityEntity> cities = new HashSet<>();

	@Transient
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Integer> citiesId;

	@XmlElementWrapper(name="cities_id")
	@XmlElement(name="city_id")
	public void setCitiesId(Set<Integer> citiesId) {
		this.citiesId = citiesId;
	}
	
	public Set<Integer> getCitiesId() {
		final Set<Integer> result = new HashSet<>();
		cities.forEach( city -> result.add(city.getId()) );
		
		return !result.isEmpty() ? result : null;
	}

	@XmlTransient
	public Set<Integer> getCitiesIdObject() {
		return citiesId;
	}
	
	@Transient
	@XmlTransient
	public String getCitiesText() {
		StringBuilder sb = new StringBuilder();
		for(CityEntity city: cities)
			sb.append(city.getName()).append(", ");
		if(!cities.isEmpty())
			sb.delete(sb.length() - 2, sb.length());
		
		return sb.toString();
	}
}