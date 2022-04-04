package education.cccp.mobile.listview.contentprovider.dao;

import java.util.ArrayList;
import java.util.List;

import education.cccp.mobile.listview.contentprovider.models.Person;

public class PersonDaoStatic implements IPersonDao {

    private static int counter = 0;

    public static int idGenerator() {
        return ++counter;
    }

    private static final List<Person> persons = new ArrayList<>();

    public Person save(Person person) throws Exception {
        if (person.getId() == null) person = new Person(idGenerator(),
                person.getFirstName(),
                person.getLastName());
        if (persons.add(person)) return person;
        else throw new Exception("malformed exception : " + person);
    }

    public Person save(int index, Person person) {
        return persons.set(index, person);
    }

    public static void delete(int index) {
        persons.remove(index);
    }

    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Person findOneById(Integer id) {
        return persons.get(id);
    }

    @Override
    public long count() {
        return persons.size();
    }
}