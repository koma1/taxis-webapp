package pw.komarov.taxi.persistence.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import pw.komarov.taxi.persistence.dao.CityDao;
import pw.komarov.taxi.persistence.dao.TaxiDao;
import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectIncompletedException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;

public class TaxiService implements EntityService<TaxiEntity> {
	private static final TaxiDao taxiDao = new TaxiDao();
	private static final CityDao cityDao = new CityDao();
	
	public List<CityEntity> getAllCities() {
		return cityDao.getAll();
	}
	
	@Override
	public TaxiEntity getEntity(Integer id) {
		return taxiDao.findById(id);
	}

	@Override
	public List<TaxiEntity> getAllEntities() {
		return taxiDao.getAll();
	}
	
	public List<TaxiEntity> find(String name, String phone, String city, String text) {
		if(name == null && phone == null && city == null && text == null)
			return getAllEntities();
		else
			return taxiDao.findByParams(name, phone, city, text);
	}

	@Override
	public void save(TaxiEntity taxi) throws EntityObjectException {
		if(taxi.getName() == null || taxi.getName().isEmpty())
			throw new EntityObjectIncompletedException(taxi, "name");
		if(taxi.getPhone() == null || taxi.getPhone().isEmpty())
			throw new EntityObjectIncompletedException(taxi, "phone");
		
		Collection<Integer> idObjects = taxi.getCitiesIdObject();
		if(idObjects != null) { //cities was modified
			Set<CityEntity> cities = taxi.getCities();
			for(Integer cityId: idObjects) {
				CityEntity city = cityDao.findById(cityId);
				if(city == null)
					throw new EntityObjectNotFoundException(CityEntity.class, cityId);

				cities.add(city);				
			}

			taxi.setCitiesId(null); //converted from IDs to entities, can be resetted!
		}
		
		if(taxi.getCities() == null || taxi.getCities().isEmpty())
			throw new EntityObjectIncompletedException(taxi, "cities");
		
		if(taxi.isNew())
			taxi.setId(taxiDao.create(taxi));
		else
			taxiDao.update(taxi);
	}

	@Override
	public void delete(Integer id) throws EntityObjectException {
		TaxiEntity taxi = taxiDao.findById(id);
		if(taxi == null)
			throw new EntityObjectNotFoundException(TaxiEntity.class, id);
			
		taxiDao.delete(taxi);
	}
	
	public boolean checkNameDuplicated(String name, Integer idEditing) {
		TaxiEntity data = taxiDao.getByName(name);
		return ( (data != null) && ((idEditing == null) || (!data.getId().equals(idEditing))) );
	}
	
	public boolean checkPhoneDuplicated(String phone, Integer idEditing) {
		TaxiEntity data = taxiDao.getByPhone(phone);
		return ( (data != null) && ((idEditing == null) || (!data.getId().equals(idEditing))) );
	}
}