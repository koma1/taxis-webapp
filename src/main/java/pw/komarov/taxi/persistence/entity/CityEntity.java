package pw.komarov.taxi.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CITIES")
@ToString
@EqualsAndHashCode
@XmlRootElement(name="city")
@XmlType(propOrder = {"id", "name", "countryId"})
public class CityEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	public CityEntity() {}
	public CityEntity(int id) {
		setId(id);
	}
	
	public CityEntity(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	@Getter
	private Integer id;
	
	public void setId(Integer id) {
		if(this.id == null)
			this.id = id;
	}
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String name;
	
	@ManyToOne(targetEntity = CountryEntity.class)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	@Getter
	private CountryEntity country;
	
	@XmlTransient
	public void setCountry(CountryEntity country) {
		this.country = country;
	}
	
	@Transient
	private Integer countryId;
	
	@Transient
	@XmlElement(name = "country_id")
	public Integer getCountryId() {
		return country != null ? country.getId() : null;
	}
	
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
	public Integer getCountryIdObject() {
		return countryId;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cities")
	@Getter
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<TaxiEntity> taxis = new HashSet<>();
}