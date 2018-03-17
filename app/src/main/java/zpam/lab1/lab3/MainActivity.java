package zpam.lab1.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nazwa;
    private EditText numer;
    private TextView zawartoscBazy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nazwa = findViewById(R.id.nazwa);
        numer = findViewById(R.id.numer);
        zawartoscBazy = findViewById(R.id.zawartoscBazy);
        SugarContext.init(this);
        SchemaGenerator schemaGenerator = new
                SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());

    }


    public void dodaj(View view){
        Kontakt kondakt = new Kontakt(nazwa.getText().toString(),numer.getText().toString());
        kondakt.save();
        zawartoscBazy();
    }

    public void usun(View view){

        if (nazwa.getText().toString().equals("") && numer.getText().toString().equals("")){
            Kontakt.deleteAll(Kontakt.class);
        }else {
            Kontakt kon = Select.from(Kontakt.class).where(Condition.prop("nazwa").eq(nazwa.getText().toString()))
                    .first();
            kon.delete();

        }
        zawartoscBazy();
    }

    private void  zawartoscBazy(){
        List<Kontakt> kontaktList = Kontakt.listAll(Kontakt.class);
        StringBuilder stringBuilder = new StringBuilder();
        for (Kontakt kt : kontaktList){
            stringBuilder.append(kt.getFullContact()).append(",\n");
        }
        zawartoscBazy.setText(stringBuilder.toString());
    }

}
