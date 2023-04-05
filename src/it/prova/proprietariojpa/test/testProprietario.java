package it.prova.proprietariojpa.test;

import it.prova.proprietariojpa.service.MyServiceFactory;
import it.prova.proprietariojpa.service.automobile.AutomobileService;

import java.time.LocalDate;
import java.util.List;

import it.prova.proprietariojpa.dao.EntityManagerUtil;
import it.prova.proprietariojpa.model.Automobile;
import it.prova.proprietariojpa.model.Proprietario;
import it.prova.proprietariojpa.service.proprietario.ProprietarioService;

public class testProprietario {

	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();
		
		try {
//			testInserisciProprietario(proprietarioService);
//			System.out.println("in tabella sono presenti "+ proprietarioService.listAllProprietari().size() + " elementi.");
			
//			testInserisciAutomobile(proprietarioService, automobileService);
//			System.out.println("in tabella sono presenti "+ proprietarioService.listAllProprietari().size() + " elementi.");
		
//			testAggiornaProprietario(proprietarioService);
//			System.out.println("in tabella sono presenti "+ proprietarioService.listAllProprietari().size() + " elementi.");

//			testRimuoviProprietario(proprietarioService);
//			System.out.println("in tabella sono presenti "+ proprietarioService.listAllProprietari().size() + " elementi.");
	
//			testCercaErrori(proprietarioService, automobileService);
//			System.out.println("in tabella sono presenti "+ automobileService.listAllAutomobile().size() + " elementi.");
			
//			testCodFisIniziaPer(proprietarioService, automobileService);
//			System.out.println("in tabella sono presenti "+ automobileService.listAllAutomobile().size() + " elementi.");

			testAutomobiliConPropietarioNatoDopo(proprietarioService, automobileService);
			System.out.println("in tabella sono presenti "+ automobileService.listAllAutomobile().size() + " elementi.");

		
		

	} catch (Throwable e) {
		e.printStackTrace();
	} finally {
		// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
		// main
		EntityManagerUtil.shutdown();
	}
	}
		
		//
		private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
			System.out.println(".......testInserisciProprietario inizio.............");
			// creo nuovo municipio
			Proprietario nuovoProprietario = new Proprietario("Matteo", "Castiello", "CSTMTT02D13A783C", LocalDate.of(2002, 04, 13));
			
			if (nuovoProprietario.getId() != null)
				throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
			// salvo
			proprietarioService.inserisciNuovo(nuovoProprietario);
			// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
			// (NOVITA' RISPETTO AL PASSATO!!!)
			if (nuovoProprietario.getId() == null)
				throw new RuntimeException("testInserisciProprietario fallito ");

			System.out.println(".......testInserisciProprietario fine: PASSED.............");



	}
		
		private static void testInserisciAutomobile (ProprietarioService proprietarioService, AutomobileService automobieService) throws Exception{
			System.out.println(".......testInserisciAutomobile inizio.............");

			// creo nuovo abitante ma prima mi serve un municipio
			List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
			if (listaProprietari.isEmpty())
				throw new RuntimeException("testInserisciAutomobile fallito: non ci sono municipi a cui collegarci ");

			Automobile nuovaAutomobile = new Automobile("Mini", "One", "DX378KB" );
			// lo lego al primo proprietario che trovo
			nuovaAutomobile.setProprietario(listaProprietari.get(0));;

			// salvo il nuovo abitante
			automobieService.inserisciNuovo(nuovaAutomobile);

			// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
			// (NOVITA' RISPETTO AL PASSATO!!!)
			if (nuovaAutomobile.getId() == null)
				throw new RuntimeException("testInserisciAbitante fallito ");

			// il test fallisce anche se non è riuscito a legare i due oggetti
			if (nuovaAutomobile.getProprietario() == null)
				throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il municipio ");

			System.out.println(".......testInserisciAutomobile fine: PASSED.............");
		}
		
		//
		private static void testAggiornaProprietario(ProprietarioService proprietarioService) throws Exception {
			System.out.println("......testUpdateProprietario inizio........");

			List<Proprietario> testLista = proprietarioService.listAllProprietari();
			Proprietario test=testLista.get(0);
			test.setNome("Matteo");
			test.setCognome("Castiello");
			test.setDataDiNascita(LocalDate.of(2002, 04, 14));
			proprietarioService.aggiorna(test);
			
			if(test.getNome()==null)
				throw new Exception();
			
			
			System.out.println("......testUpdateProprietario fine........");
		}
		
		
		private static void testRimuoviProprietario(ProprietarioService proprietarioService) throws Exception {
			System.out.println("......testRimuoviProprietario inizio........");

			List<Proprietario> testLista = proprietarioService.listAllProprietari();
			Proprietario test=testLista.get(1);
			proprietarioService.rimuovi(test);
			
			if(test.getNome()==null)
				throw new Exception();
			
			
			System.out.println("......testRimuoviProprietario fine........");
		}
		
		//
		private static void testCercaErrori (ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
			System.out.println("......testCercaErrori inizio........");
			List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
			if (listaProprietari.isEmpty())
				throw new RuntimeException("testCercaErrori fallito: non ci sono proprietari a cui collegarci ");
			
			List<Automobile> listaErrori=automobileService.cercaErrori();
			
			System.out.println(listaErrori);
			
			System.out.println("......testCercaErrori fine........");

		}
		
		private static void testCodFisIniziaPer (ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
			System.out.println("......testCodFisIniziaPer inizio........");
			List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
			if (listaProprietari.isEmpty())
				throw new RuntimeException("testCodFisIniziaPer fallito: non ci sono proprietari a cui collegarci ");
			
			String stringaCFIniziaPer="CST";
			List<Automobile> listaCodiciFiscali=automobileService.codFisIniziaPer(stringaCFIniziaPer);
			
			System.out.println(listaCodiciFiscali);
			
			
			System.out.println("......testCodFisIniziaPer fine........");

		}
		
		
		private static void testAutomobiliConPropietarioNatoDopo (ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
			System.out.println("......testAutomobiliConPropietarioNatoDopo inizio........");
			List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
			if (listaProprietari.isEmpty())
				throw new RuntimeException("testAutomobiliConPropietarioNatoDopo fallito: non ci sono proprietari a cui collegarci ");
			
			int annoProprietario=2003;
			int contaProprietariNatiDopoAnno=automobileService.automobiliConPropietarioNatoDopo(annoProprietario);
			
			System.out.println(contaProprietariNatiDopoAnno);
			
			
			System.out.println("......testAutomobiliConPropietarioNatoDopo fine........");

		}
		

}
