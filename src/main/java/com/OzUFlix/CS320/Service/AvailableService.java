package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.Model.Available;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableService {
    @Autowired
    AvailableService availableService;

    public Available save(Available available){ return availableService.save(available); }

    public List<Available> findAll(){ return availableService.findAll();}

    public Available findById(int id){ return availableService.findById(id); }

    public void deleteById(int id){ availableService.deleteById(id); }
}