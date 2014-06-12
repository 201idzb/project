package testandexceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBOutputAdapterTest {

	/*	 Testy akceptacyjne  - "czarnoskrzynkowe":
	 * 
	 *	Odlaczenie bazy danych podczas dzialania:
 	 *	Zlapany wyjatek SQL Exception
	 *	Po ponownym podlaczeniu bazy danych program kontynuuje wpisywanie rekordow
	 *
	 *	Odlaczenie bazy danych przed uzyciem adaptera:
	 *	Zlapany wyjatek SQL Exception
	 *	Po ponownym podlaczeniu bazy danych program kontynuuje wpisywanie rekordow
	 *
	 *	Podane zle haslo:
	 *	Zlapany wyjatek SQL Exception
	 *
	 *	Podany zly uzytkownik:
	 *	Zlapany wyjatek SQL Syntax error
	 *
	 *	Podana zla nazwa bazy danych:
	 *	Zlapany wyjatek SQL Syntax error
	 *
	 *	Podanie dobrej nazwy i hasla do uzytkownika bez uprawnien do edycji bazy:
	 *	Zlapany wyjatek SQL Syntax error
	 *
	 *	Proba wpisania do nie istniejacej tablicy:
	 *	Zlapany wyjatek SQL Syntax error
	 *
	 *	Proba wpisania do tablicy w zlym formacie:
	 *	Zlapany wyjatek SQL Exception
	 * 
	 *	Proba wpisania do innej tabeli w dobrym formacie (nazwa pobrana z conf.dat):
	 *	Zakonczona sukcesem */

}
