package it.prova.proprietariojpa.dao.automobile;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.proprietariojpa.model.Automobile;
import it.prova.proprietariojpa.model.Proprietario;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();

	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);

	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		automobileInstance = entityManager.merge(automobileInstance);

	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(automobileInstance);

	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(automobileInstance));

	}

	@Override
	public List<Automobile> findMistakes() throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery(
				"from Automobile a where a.proprietario.dataDiNascita > ?1", Automobile.class);
		query.setParameter(1,LocalDate.now().minusYears(18));
		

		// return query.getSingleResult() ha il problema che se non trova elementi
		// lancia NoResultException
		return query.getResultList();
	}

	public List<Automobile> codFisStartsWith(String iniziale) throws Exception {
		
		TypedQuery<Automobile> query = entityManager.createQuery(
				"from Automobile a where a.proprietario.cf like ?1", Automobile.class);
		
		query.setParameter(1, iniziale+"%");
		
		return query.getResultList();
		
	}

	public int automobiliWithPropietarioBornAfter(int annoDiNascita) throws Exception {
		TypedQuery<Long> query = entityManager.createQuery(
				"select count(a.id) from Automobile a where year(a.proprietario.dataDiNascita) > ?1", Long.class);
		
		query.setParameter(1, annoDiNascita);
		int count = query.getSingleResult().intValue();
		
		return count;
	}

}
