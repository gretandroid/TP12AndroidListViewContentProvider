package education.cccp.mobile.listview.contentprovider.dao;

import androidx.annotation.NonNull;

import java.util.List;

import education.cccp.mobile.listview.contentprovider.models.Person;

@SuppressWarnings(value = {
        "UnusedReturnValue",
        "unused"
})
public interface IPersonDao {
    int PERSON_ID_DEFAULT_VALUE = -1;

    Person save(@NonNull Person person) throws Exception;

    List<Person> findAll();

    Person findOneById(@NonNull Integer id);

    long count();

    Person save(@NonNull Integer currentIndex,
                @NonNull Person person);

    void delete(@NonNull Person person);

    void deleteById(@NonNull Integer id);
}
