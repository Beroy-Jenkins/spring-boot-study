package estudo.br.boot.service;

import java.time.LocalDate;
import java.util.List;

import estudo.br.boot.domain.Funcionario;

public interface FuncionarioService {
	void salvar(Funcionario funcionario);
	void editar(Funcionario funcionario);
	void excluir(Long id);
	Funcionario buscarPorId(Long id);
	List<Funcionario> buscatodos();
	boolean validar(Funcionario funcionario);
	List<Funcionario> buscaPorNome(String nome);
	List<Funcionario>  buscaPorCargo(Long id);
	List<Funcionario> buscaPorDatas(LocalDate entrada, LocalDate saida);
}
