package pw.komarov.taxi.persistence.exceptions;

import lombok.Getter;
import pw.komarov.taxi.persistence.entity.EntityIntf;


public class EntityObjectIncompletedException extends EntityObjectException {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private EntityIntf entity;
	@Getter
	private String property;
	
	public EntityObjectIncompletedException(EntityIntf entity, String property) {
		super(String.format("Entity '%s' has incompleted property '%s'", entity.getClass().getSimpleName(), property));
		
		this.entity = entity;
		this.property = property;
	}
	
	public EntityObjectIncompletedException(String message, EntityIntf entity, String property) {
		super(message);
		
		this.entity = entity;
		this.property = property;
	}
}
