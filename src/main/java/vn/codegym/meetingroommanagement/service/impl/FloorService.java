package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Floor;
import vn.codegym.meetingroommanagement.repository.IFloorRepository;
import vn.codegym.meetingroommanagement.service.IFloorService;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService implements IFloorService {

    @Autowired
    private IFloorRepository floorRepository;

    @Override
    public List<Floor> getAll() {
        return floorRepository.findAll();
    }

    @Override
    public Optional<Floor> getById(Integer key) {
        return Optional.empty();
    }

    @Override
    public Floor save(Floor entity) {
        return null;
    }

    @Override
    public void deleteById(Integer key) {

    }
}