package estudo.br.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.br.boot.dao.DepartamentoDao;
import estudo.br.boot.domain.Departamento;

@Service @Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao dao;

	@Override
	public void salvar(Departamento departamento) {
		dao.save(departamento);

	}

	@Override
	public void editar(Departamento departamento) {
		dao.update(departamento);

	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);

	}

	@Override
	public Departamento buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Departamento> buscatodos() {
		return dao.findAll();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		if(buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean validar(Departamento departamento) {
		if(departamento.getNome()== "") {
			return false;
		}
		return true;
	}
}
