package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Area;
import vn.codegym.meetingroommanagement.repository.IAreaRepository;
import vn.codegym.meetingroommanagement.service.IAreaService;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService implements IAreaService {

    @Autowired
    private IAreaRepository areaRepository;

    @Override
    public List<Area> getAll() {
        return null;
    }

    @Override
    public Optional<Area> getById(Integer key) {
        return Optional.empty();
    }

    @Override
    public Area save(Area entity) {
        return areaRepository.save(entity);
    }

    @Override
    public void deleteById(Integer key) {

    }
}