package estudo.br.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.br.boot.dao.CargoDao;
import estudo.br.boot.domain.Cargo;

@Service @Transactional
public class CargoServiceImp implements CargoService{
	
	@Autowired
	private CargoDao dao;

	@Override
	public void salvar(Cargo cargo) {
		dao.save(cargo);
		
	}

	@Override
	public void editar(Cargo cargo) {
		dao.update(cargo);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override @Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Cargo> buscatodos() {
		return dao.findAll();
	}

	@Override
	public boolean validarNome(Cargo cargo) {
		if(cargo.getNome()== "") {
			return false;
		}
		return true;
	}

	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

}
