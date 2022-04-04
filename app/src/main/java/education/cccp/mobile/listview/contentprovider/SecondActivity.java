package education.cccp.mobile.listview.contentprovider;

import static android.R.layout.simple_list_item_1;
import static education.cccp.mobile.listview.contentprovider.R.id.personListViewId;
import static education.cccp.mobile.listview.contentprovider.R.layout.activity_second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import education.cccp.mobile.listview.contentprovider.dao.IPersonDao;
import education.cccp.mobile.listview.contentprovider.dao.PersonDaoStatic;
import education.cccp.mobile.listview.contentprovider.models.Person;

public class SecondActivity extends AppCompatActivity {

    public static final String CURRENT_PERSON_KEY = "current_person_key";
    public static final String CURRENT_PERSON_INDEX_KEY = "current_person_index_key";
    public static final String PERSON_LIST_KEY = "person_list_key";
    public static final int OUT_OF_BOUND_INDEX = -1;

    private final IPersonDao personDao = new PersonDaoStatic();


    @Override
    @SuppressWarnings("Convert2Lambda")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        ListView personsListView = findViewById(personListViewId);
        //noinspection unchecked
        personsListView.setAdapter(new ArrayAdapter<>(this,
                simple_list_item_1,
                (List<Person>) getIntent()
                        .getSerializableExtra(PERSON_LIST_KEY)));
        personsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int index,
                                    long l) {
                //retrieve person's clicked
                setResult(RESULT_OK, new Intent()
                        .putExtra(CURRENT_PERSON_KEY, personDao.findOneById(index))
                        .putExtra(CURRENT_PERSON_INDEX_KEY, index));
                finish();
            }
        });
    }

    public void onClickBackButtonEvent(View view) {
        setResult(RESULT_CANCELED, new Intent()
                .putExtra(CURRENT_PERSON_INDEX_KEY, OUT_OF_BOUND_INDEX));
        finish();
    }
}