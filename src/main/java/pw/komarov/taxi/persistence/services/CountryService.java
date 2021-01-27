package pw.komarov.taxi.persistence.services;

import java.util.List;

import pw.komarov.taxi.persistence.dao.CountryDao;
import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.entity.CountryEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectHeldException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectIncompletedException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;

public class CountryService extends AbstractDaoService<CountryEntity>  {
	private static final CountryDao countryDao = new CountryDao();
	
	public CountryService() {
		super(countryDao);
	}
	
	public boolean checkNameDuplicated(String name, Integer idEditing) {
		CountryEntity data = countryDao.getByName(name);
		return ( (data != null) && ((idEditing == null) || (!data.getId().equals(idEditing))) );
	}

	public List<CityEntity> getHeldBy(Integer countryId) {
		return countryDao.getHeldBy(countryId);
	}
	
	public List<CountryEntity> find(String name) {
		if(name == null || name == "")
			return getAllEntities();
		else
			return countryDao.findByParams(name);
	}

	@Override
	public void save(CountryEntity country) throws EntityObjectException {
		if(country.getName() == null || country.getName().isEmpty())
			throw new EntityObjectIncompletedException(country, "name");
		
		if(country.isNew())
			country.setId(countryDao.create(country));
		else
			countryDao.update(country);
	}

	@Override
	public void delete(Integer id) throws EntityObjectException {	
		CountryEntity country = countryDao.findById(id);
		if(country == null)
			throw new EntityObjectNotFoundException(CountryEntity.class, id);
		
		List<CityEntity> cities = getHeldBy(id);
		if(!cities.isEmpty())
			throw new EntityObjectHeldException(country, cities);
			
		countryDao.delete(country);
	}
}