package pw.komarov.taxi.persistence.exceptions;

import lombok.Getter;
import lombok.Setter;
import pw.komarov.taxi.persistence.entity.EntityIntf;

public class EntityObjectNotFoundException extends EntityObjectException {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Class<? extends EntityIntf> entityClass;
	@Getter @Setter
	private Integer id;
	
	public EntityObjectNotFoundException(Class<? extends EntityIntf> entityClass, Integer id) {
		super(String.format("Entity class \"%s\" not found by ID#%d", entityClass.getSimpleName(), id));
		
		this.entityClass = entityClass;
		this.id = id;
	}
	
	public String getEntitySimpleName() {
		return EntityIntf.getEntitySimpleName(entityClass);
	}
}