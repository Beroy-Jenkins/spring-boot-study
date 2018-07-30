package estudo.br.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import estudo.br.boot.domain.Cargo;
@Repository
public interface CargoDao {
	void save(Cargo cargo);
	void update(Cargo cargo);
	void delete(Long id);
	Cargo findById(Long id);
	List<Cargo> findAll();
}
