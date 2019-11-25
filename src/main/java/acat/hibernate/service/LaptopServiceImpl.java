package acat.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dto.LaptopDto;
import acat.hibernate.model.Laptop;
import acat.hibernate.repository.LaptopRepository;

public class LaptopServiceImpl implements LaptopService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4694922824312438039L;
	private final LaptopRepository repo = new LaptopDao();

	public LaptopDto findOne(long id) {
		// TODO Auto-generated method stub
		Laptop laptop = repo.findOne(id);
		LaptopDto dto = new LaptopDto(laptop);
		return dto;
	}

	public List<LaptopDto> findAll() {
		// TODO Auto-generated method stub
		List<Laptop> laptopListDao = repo.findAll();
		List<LaptopDto> laptopListDto = null;
		for(Laptop person: laptopListDao) {
			if (laptopListDto==null) {
				laptopListDto = new ArrayList<LaptopDto>();
			}
			LaptopDto dto = new LaptopDto(person);
			laptopListDto.add(dto);
		}
		return laptopListDto;
	}

	public LaptopDto save(LaptopDto dto) {
		// TODO Auto-generated method stub
		Laptop laptop = repo.save(dto.getEntity());
		return new LaptopDto(laptop);
	}

	public LaptopDto update(LaptopDto dto) {
		// TODO Auto-generated method stub
		Laptop laptop = repo.update(dto.getEntity());
		return new LaptopDto(laptop);
	}

	public void delete(LaptopDto dto) {
		// TODO Auto-generated method stub
		repo.delete(dto.getEntity());
	}

	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repo.deleteById(entityId);
	}
}
