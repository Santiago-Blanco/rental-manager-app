package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;

import java.util.List;

public interface iPropertyService {
    PropertyResponseDTO saveProperty(PropertyCreateDTO propertyCreate);
    PropertyResponseDTO getProperty(String propertyName);
    List<PropertyResponseDTO> getProperties();
    PropertyResponseDTO deleteProperty(String propertyName);
    PropertyResponseDTO editProperty(String propertyName, PropertyEditDTO propertyEdit);
}
