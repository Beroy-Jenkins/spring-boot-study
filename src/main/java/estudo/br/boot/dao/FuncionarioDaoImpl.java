package estudo.br.boot.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import estudo.br.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	public List<Funcionario> findByNome (String nome) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE concat('%',?1,'%')", nome);
//********Pode ser feito:
//	public List<Funcionario> findByNome(String nome) {
//		TypedQuery<Funcionario> query = getEntityManager()
//				.createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE :nome", Funcionario.class);
//		query.setParameter("nome", nome);
//		return query.getResultList();
	}

	@Override
	public List<Funcionario> findByCargoId(Long id) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> findByDataEntrada(LocalDate entrada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> findByDataSaida(LocalDate saida) {
		// TODO Auto-generated method stub
		return null;
	}



}
