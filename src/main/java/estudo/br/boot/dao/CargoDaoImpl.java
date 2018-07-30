package estudo.br.boot.dao;

import org.springframework.stereotype.Repository;

import estudo.br.boot.domain.Cargo;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{

}
