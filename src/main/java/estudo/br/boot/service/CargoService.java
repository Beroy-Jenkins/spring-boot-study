package estudo.br.boot.service;

import java.util.List;

import estudo.br.boot.domain.Cargo;

public interface CargoService {
	void salvar(Cargo cargo);
	void editar(Cargo cargo);
	void excluir(Long id);
	Cargo buscarPorId(Long id);
	List<Cargo> buscatodos();
	boolean validarNome(Cargo cargo);
	boolean cargoTemFuncionarios(Long id);
}
