package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository repository;

    private Person person;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getById() {
    }

    @Test
    public void saveAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void listAll() {
    }
}