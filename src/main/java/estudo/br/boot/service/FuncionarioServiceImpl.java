package estudo.br.boot.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.br.boot.dao.FuncionarioDao;
import estudo.br.boot.domain.Funcionario;

@Service @Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;
	
	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	public void editar(Funcionario funcionario) {
		dao.update(funcionario);

	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);

	}

	@Override @Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Funcionario> buscatodos() {
		return dao.findAll();
	}

	@Override
	public boolean validar(Funcionario funcionario) {
		if(funcionario.getNome()== "") {
			return false;
		}
		return true;
	}

	@Override
	public List<Funcionario> buscaPorNome(String nome) {
		return dao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscaPorCargo(Long id) {
		return dao.findByCargoId(id);
	}

	@Override
	public List<Funcionario> buscaPorDatas(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida != null) {
			return dao.findByDataEntradaDataSaida(entrada, saida);
		}else if(entrada != null) {
			return dao.findByDataEntrada(entrada);
		}else if(saida != null) {
			return dao.findByDataSaida(saida);
		}else {
			return new ArrayList<>();
		}
	}

}
