package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Personal;
import com.demo.FactoryMethodLogisticaTransporte.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService{
    @Autowired
    private PersonalRepository personalRepository;
    @Override
    public Personal GuardarPersonal(Personal p) {
        return personalRepository.save(p);
    }

    @Override
    public Personal obtenerPersonalPorId(Long id) {
        return personalRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personal> obtenerPersonal() {
        return personalRepository.findAll();
    }
}
