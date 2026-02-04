package cursojava.notasvolatiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.notasvolatiles.models.Nota;

public interface NotaRepository extends JpaRepository<Nota, String> {
   // No es necesario agregar métodos adicionales, JpaRepository ya proporciona los métodos CRUD básicos:
   // - save(Nota nota): para crear o actualizar una nota.
   // - findById(String clave): para buscar una nota por su clave.
   // - deleteById(String clave): para eliminar una nota por su clave.
}
