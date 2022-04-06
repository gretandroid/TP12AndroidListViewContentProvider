package education.cccp.mobile.listview.contentprovider;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static education.cccp.mobile.listview.contentprovider.R.id.editTextPersonFirstNameId;
import static education.cccp.mobile.listview.contentprovider.R.id.editTextPersonLastNameId;
import static education.cccp.mobile.listview.contentprovider.R.layout.activity_main;
import static education.cccp.mobile.listview.contentprovider.SecondActivity.OUT_OF_BOUND_INDEX;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Objects;

import education.cccp.mobile.listview.contentprovider.dao.IPersonDao;
import education.cccp.mobile.listview.contentprovider.dao.inmemory.PersonDaoStatic;
import education.cccp.mobile.listview.contentprovider.models.Person;

public class MainActivity extends AppCompatActivity {

    public static final String EMPTY_FIELD = "";
    public static final String CURRENT_PERSON_KEY = "current_person_key";
    public static final String CURRENT_PERSON_INDEX_KEY = "current_person_index_key";
    public static final String PERSONS_KEY = "person_list_key";

    private EditText personFirstNameEditText;
    private EditText personLastNameEditText;
    private int currentIndex = OUT_OF_BOUND_INDEX;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    private final IPersonDao personDao = new PersonDaoStatic();


    private void setEditTextPersonFields(String firstName, String lastName) {
        personFirstNameEditText.setText(firstName);
        personLastNameEditText.setText(lastName);
    }

    private void makeTextPersonsIsEmptyOrNoOneSelected() {
        makeText(
                this,
                "person list is empty or no person selected",
                LENGTH_SHORT
        ).show();
    }


    private void gotoSecondActivity() {
        intentActivityResultLauncher.launch(
                new Intent(
                        this,
                        SecondActivity.class
                ).putExtra(
                        PERSONS_KEY,
                        (Serializable) personDao.findAll()
                )
        );
    }

    @Override
    @SuppressWarnings("Convert2Lambda")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        personFirstNameEditText = findViewById(editTextPersonFirstNameId);
        personLastNameEditText = findViewById(editTextPersonLastNameId);
        intentActivityResultLauncher = registerForActivityResult(
                new StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult activityResult) {
                        Intent data = activityResult.getData();
                        if (activityResult.getResultCode() == RESULT_OK) {
                            Person person = (Person) Objects.requireNonNull(data)
                                    .getSerializableExtra(CURRENT_PERSON_KEY);
                            setEditTextPersonFields(person.getFirstName(),
                                    person.getLastName());
                            currentIndex = data.getIntExtra(
                                    CURRENT_PERSON_INDEX_KEY,
                                    OUT_OF_BOUND_INDEX);
                        }
                        if (activityResult.getResultCode() == RESULT_CANCELED) {
                            setEditTextPersonFields(EMPTY_FIELD, EMPTY_FIELD);
                            currentIndex = Objects.requireNonNull(data).getIntExtra(
                                    CURRENT_PERSON_INDEX_KEY,
                                    OUT_OF_BOUND_INDEX);
                        }
                    }
                }
        );
    }

    public void onClickCreateButtonEvent(View view) throws Exception {
        personDao.save(new Person(
                personFirstNameEditText.getText().toString(),
                personLastNameEditText.getText().toString()));
        makeText(this,
                "person successfully added",
                LENGTH_LONG)
                .show();
        setEditTextPersonFields(
                EMPTY_FIELD,
                EMPTY_FIELD
        );
    }

    public void onClickShowAllButtonEvent(View view) {
        if (personDao.count() > 0) gotoSecondActivity();
        else makeTextPersonsIsEmptyOrNoOneSelected();
    }

    public void onClickDeleteButtonEvent(View view) {
        if (currentIndex != OUT_OF_BOUND_INDEX) {
            personDao.deleteById(currentIndex);
            setEditTextPersonFields(EMPTY_FIELD, EMPTY_FIELD);
            makeText(this,
                    "person successfully deleted",
                    LENGTH_SHORT).show();
            gotoSecondActivity();
        } else makeTextPersonsIsEmptyOrNoOneSelected();
    }

    public void onClickEditButtonEvent(View view) {
        if (currentIndex != OUT_OF_BOUND_INDEX) {
            Person person = personDao.findOneById(currentIndex);
            person.setLastName(personLastNameEditText.getText().toString());
            person.setFirstName(personFirstNameEditText.getText().toString());
            personDao.save(currentIndex, person);
            makeText(this,
                    "person successfully updated",
                    LENGTH_SHORT).show();
            gotoSecondActivity();
        } else makeTextPersonsIsEmptyOrNoOneSelected();
    }
}