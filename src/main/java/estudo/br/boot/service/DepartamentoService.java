package estudo.br.boot.service;

import java.util.List;

import estudo.br.boot.domain.Departamento;


public interface DepartamentoService  {
	void salvar(Departamento departamento);
	void editar(Departamento departamento);
	void excluir(Long id);
	Departamento buscarPorId(Long id);
	List<Departamento> buscatodos();
	boolean departamentoTemCargos(Long id);
	boolean validar(Departamento departamento);
}
