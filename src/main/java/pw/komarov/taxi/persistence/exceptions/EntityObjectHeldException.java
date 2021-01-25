package pw.komarov.taxi.persistence.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pw.komarov.taxi.persistence.entity.EntityIntf;

@AllArgsConstructor
public class EntityObjectHeldException extends EntityObjectException {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private EntityIntf entity;
	
	@Getter
	private Object heldBy;
}