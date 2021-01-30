package pw.komarov.taxi.persistence.entity;

import java.util.Collection;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@Getter @Setter
	private Integer id;

	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	@Getter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<UserEntity> users;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "ROLES_PRIVILEGES", 
		joinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID", nullable = false, updatable = false)})
	@Getter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PrivilegeEntity> privileges;
}