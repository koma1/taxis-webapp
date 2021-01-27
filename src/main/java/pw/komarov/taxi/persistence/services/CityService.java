package pw.komarov.taxi.persistence.services;

import java.util.List;

import pw.komarov.taxi.persistence.dao.CityDao;
import pw.komarov.taxi.persistence.dao.CountryDao;
import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.entity.CountryEntity;
import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectHeldException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectIncompletedException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;

public class CityService extends AbstractDaoService<CityEntity> {
	private static final CityDao cityDao = new CityDao();
	private static final CountryDao countryDao = new CountryDao();
	
	public CityService() {
		super(cityDao);
	}
	
	public boolean checkNameDuplicated(String name, Integer idEditing) {
		CityEntity data = cityDao.getByName(name);
		return ( (data != null) && ((idEditing == null) || (!data.getId().equals(idEditing))) );
	}
	
	public List<TaxiEntity> getHeldBy(Integer cityId) {
		return cityDao.getHeldBy(cityId);
	}
	
	public List<CountryEntity> getAllCountries() {
		return countryDao.getAll();
	}
	
	public List<CityEntity> find(String name, String countryName, Integer countryId) {
		if(name == null && countryName == null && countryId == null)
			return getAllEntities();
		else
			return cityDao.findByParams(name, countryName, countryId);
	}

	@Override
	public void save(CityEntity city) throws EntityObjectException {
		if(city.getName() == null || city.getName().isEmpty())
			throw new EntityObjectIncompletedException(city, "name");
		
		Integer idObject = city.getCountryIdObject();
		if(idObject != null) { //cities was modified
			CountryEntity country = countryDao.findById(idObject);
			if(country == null)
				throw new EntityObjectNotFoundException(CountryEntity.class, idObject);
				
			city.setCountry(country);
			
			city.setCountryId(null); //drop state to "unchanged"
		}
		
		if(city.getCountry() == null)
			throw new EntityObjectIncompletedException(city, "country");
		
		if(city.isNew())
			city.setId(cityDao.create(city));
		else
			cityDao.update(city);
	}

	@Override
	public void delete(Integer id) throws EntityObjectException {	
		CityEntity city = cityDao.findById(id);
		if(city == null)
			throw new EntityObjectNotFoundException(CityEntity.class, id);
		
		List<TaxiEntity> taxis = getHeldBy(id);
		if(!taxis.isEmpty())
			throw new EntityObjectHeldException(city, taxis);
			
		cityDao.delete(city);		
	}
}