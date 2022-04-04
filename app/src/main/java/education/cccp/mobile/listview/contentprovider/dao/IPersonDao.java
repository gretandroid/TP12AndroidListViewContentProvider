package education.cccp.mobile.listview.contentprovider.dao;

import java.util.List;

import education.cccp.mobile.listview.contentprovider.models.Person;

@SuppressWarnings("UnusedReturnValue")
public interface IPersonDao {
    int PERSON_ID_DEFAULT_VALUE = -1;

    Person save(Person person) throws Exception;

    List<Person> findAll();

    Person findOneById(Integer id);

    long count();

    Person save(int currentIndex, Person person);

    void delete(int id);
}
