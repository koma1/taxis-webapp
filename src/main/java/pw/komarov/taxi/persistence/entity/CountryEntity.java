package pw.komarov.taxi.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "COUNTRIES")
@ToString
@EqualsAndHashCode
@XmlRootElement(name="country")
@XmlType(propOrder = {"id", "name"})
public class CountryEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	public CountryEntity() {}
	public CountryEntity(int id) { this.id = id; }
	public CountryEntity(String name) { this.name = name; }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false)
	@Getter @Setter
	private Integer id;

	@Column(name = "NAME", unique = true, nullable = false)
	@Getter @Setter
	private String name;
}