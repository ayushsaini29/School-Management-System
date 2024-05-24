package com.parent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parent.dao.ParentRepository;
import com.parent.entity.Parent;

@Service
public class ParentService {
	
    @Autowired
    private ParentRepository parentRepository;

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public Optional<Parent> getParentById(Long id) {
        return parentRepository.findById(id);
    }

    public Parent addParent(Parent parent) {
        return parentRepository.save(parent);
    }

    public Parent updateParent(Long id, Parent parentDetails) {
        return parentRepository.findById(id).map(parent -> {
            parent.setName(parentDetails.getName());
            parent.setPhoneNumber(parentDetails.getPhoneNumber());
            parent.setEmail(parentDetails.getEmail());
            return parentRepository.save(parent);
        }).orElseGet(() -> {
            parentDetails.setId(id);
            return parentRepository.save(parentDetails);
        });
    }

    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }

}
