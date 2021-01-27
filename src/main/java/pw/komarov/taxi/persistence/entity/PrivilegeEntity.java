package pw.komarov.taxi.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRIVILEGES")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PrivilegeEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@Getter @Setter
	private Integer id;
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String name;
}