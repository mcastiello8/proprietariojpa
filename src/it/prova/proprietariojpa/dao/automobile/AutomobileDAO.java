package it.prova.proprietariojpa.dao.automobile;

import java.util.List;

import it.prova.proprietariojpa.dao.IBaseDAO;
import it.prova.proprietariojpa.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile>{
	
	public List<Automobile> findMistakes() throws Exception;
	
	//metodo che restituisce una lista di automobili i cui proprietari hanno un codice fiscale che inizia per 
	public List<Automobile> codFisStartsWith(String iniziale) throws Exception;
	
	//voglio il numero di automobili con proprietari nati da un certo anno in poi
	public int automobiliWithPropietarioBornAfter(int annoDiNascita) throws Exception;
	
}
