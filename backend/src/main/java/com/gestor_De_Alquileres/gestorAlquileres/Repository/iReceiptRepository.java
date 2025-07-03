package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iReceiptRepository extends JpaRepository<Receipt, Long> {
}
