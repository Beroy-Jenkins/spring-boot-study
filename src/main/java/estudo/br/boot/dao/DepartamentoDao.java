package estudo.br.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import estudo.br.boot.domain.Departamento;

@Repository
public interface DepartamentoDao {
	void save(Departamento departamento);
	void update(Departamento departamento);
	void delete(Long id);
	Departamento findById(Long id);
	List<Departamento> findAll();
}
