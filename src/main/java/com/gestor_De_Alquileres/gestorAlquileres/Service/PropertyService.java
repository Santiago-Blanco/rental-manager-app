package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.DepartmentNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.PropertyListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.PropertyNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService implements iPropertyService{
    @Autowired
    iPropertyRepository propertyrepo;
    @Override
    public PropertyResponseDTO saveProperty(PropertyCreateDTO propertyCreate) {
        Property property = new Property();
        property.setPropertyName(propertyCreate.getPropertyName());
        property.setAdress(propertyCreate.getAdress());

        propertyrepo.save(property);
        return new PropertyResponseDTO(property);
    }

    @Override
    public PropertyResponseDTO getProperty(String propertyName) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());
        return new PropertyResponseDTO(property);
    }

    @Override
    public List<PropertyResponseDTO> getProperties() {
        List<Property> propertiesList = propertyrepo.findAll();
        if (propertiesList.isEmpty()) throw new PropertyListNotFound();

        List<PropertyResponseDTO> dtoList = new ArrayList<>();
        for (Property property : propertiesList) {
            dtoList.add(new PropertyResponseDTO(property));
        }
        return dtoList;
    }

    @Override
    public PropertyResponseDTO deleteProperty(String propertyName) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());
        propertyrepo.delete(property);
        return new PropertyResponseDTO(property);
    }

    @Override
    public PropertyResponseDTO editProperty(String propertyName, PropertyEditDTO propertyEdit) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        if (propertyEdit.getAdress() != null && !propertyEdit.getAdress().isEmpty())
            property.setAdress(propertyEdit.getAdress());

        if (propertyEdit.getPropertyName() != null && !propertyEdit.getPropertyName().isEmpty())
            property.setPropertyName(propertyEdit.getPropertyName());

        propertyrepo.save(property);
        return new PropertyResponseDTO(property);
    }

}
