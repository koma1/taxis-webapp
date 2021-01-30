package pw.komarov.taxi.persistence.entity;

import java.util.Collection;
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserEntity implements EntityIntf {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@Getter @Setter
	private Integer id;

	@Column(unique = true, nullable = false, updatable = false)
	@Getter @Setter
	private String login;
	
	@Column
	@Getter @Setter
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", 
			joinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)})
	@Getter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<RoleEntity> roles;
	
	public Set<PrivilegeEntity> getPrivileges() {
		Set<PrivilegeEntity> result = new HashSet<>();
		
		roles.forEach((role) -> role.getPrivileges().forEach(result::add));
		return result;
	}
}