package alencar.victor.projetoweb.repositorios;

import alencar.victor.projetoweb.models.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdministradoresRepo extends CrudRepository<Administrador, Integer> {
    @Query(value = "select * from administradores where email = :email and senha=:senha",nativeQuery = true)
    public Administrador login(String email, String senha);
}
