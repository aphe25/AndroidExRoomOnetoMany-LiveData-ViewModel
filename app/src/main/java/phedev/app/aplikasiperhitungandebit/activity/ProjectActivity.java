package phedev.app.aplikasiperhitungandebit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import phedev.app.aplikasiperhitungandebit.R;
import phedev.app.aplikasiperhitungandebit.database.AppDatabase;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;
import phedev.app.aplikasiperhitungandebit.helper.AppExecutors;

public class ProjectActivity extends AppCompatActivity {
    private EditText projectEdt, cooefEdt, penampangEdt, panjangEdt;
    private String projectname;
    private int penampang;
    private Double cooef, panjang;
    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Toolbar toolbar = findViewById(R.id.toolbar_project);
        setSupportActionBar(toolbar);

        appDatabase = AppDatabase.getsInstance(getApplicationContext());

        projectEdt = findViewById(R.id.nama_project_edt);
        cooefEdt = findViewById(R.id.cooef_project_edt);
        penampangEdt = findViewById(R.id.penampang_project_edt);
        panjangEdt = findViewById(R.id.panjang_project_edt);

        final Button nextBtn = findViewById(R.id.next_project_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (projectEdt.getText().toString().isEmpty()){
                    projectEdt.setError("wajib di isi");
                } else if (cooefEdt.getText().toString().isEmpty()){
                    cooefEdt.setError("wajib di isi");
                } else if (penampangEdt.getText().toString().isEmpty()){
                    penampangEdt.setError("wajib di isi");
                } else if (panjangEdt.getText().toString().isEmpty()){
                    panjangEdt.setError("wajib di isi");
                } else {
                    projectname = projectEdt.getText().toString();
                    penampang = Integer.parseInt(penampangEdt.getText().toString());
                    cooef = Double.valueOf(cooefEdt.getText().toString());
                    panjang = Double.valueOf(panjangEdt.getText().toString());

                    //we use panjang as luas for temporary options
                    final ProjectEntry projectEntry = new ProjectEntry(projectname, cooef, penampang, panjang, 0.0);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.dataDao().insertProjectData(projectEntry);
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
