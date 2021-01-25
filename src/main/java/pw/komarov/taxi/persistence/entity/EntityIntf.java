package pw.komarov.taxi.persistence.entity;

import java.io.Serializable;

public interface EntityIntf extends Serializable {
	Integer getId();
	
	default boolean isNew() {
		return getId() == null;
	}
	
	//Naming
	static String getEntitySimpleName(Class<? extends EntityIntf> clazz) {
		String name = clazz.getSimpleName();
		int idx = name.lastIndexOf("Entity");
		
		return idx > 0 ? name.substring(0, idx) : name;
	}
	
	static String getEntitySimplePluralName(Class<? extends EntityIntf> clazz) {
		return getEntitySimpleName(clazz) + "s";
	}
}