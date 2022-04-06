package education.cccp.mobile.listview.contentprovider.dao.inmemory;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import education.cccp.mobile.listview.contentprovider.dao.IPersonDao;
import education.cccp.mobile.listview.contentprovider.models.Person;

public class PersonDaoStatic implements IPersonDao {
    /*
    TODO: synchroniser id et index, apres ajout et remove
    dans le but de se debarrarsser de la methode delete(int, Person)
    pour passer a deleteById(int) et delete(Person)
     */
    private static int counter = 0;

    public static int idGenerator() {
        return ++counter;
    }

    private static final List<Person> persons = new ArrayList<>();

    public Person save(@NonNull Person person) throws Exception {
        if (person.getId() == null) person = new Person(idGenerator(),
                person.getFirstName(),
                person.getLastName());
        if (persons.add(person)) return person;
        else throw new Exception("malformed exception : " + person);
    }

    public Person save(@NonNull Integer index,
                       @NonNull Person person) {
        return persons.set(index, person);
    }

    public void deleteById(@NonNull Integer id) {
        persons.remove(id.intValue());
    }

    public void delete(@NonNull Person person) {
        persons.remove(person.getId());
    }

    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Person findOneById(@NonNull Integer id) {
        return persons.get(id);
    }

    @Override
    public long count() {
        return persons.size();
    }
}