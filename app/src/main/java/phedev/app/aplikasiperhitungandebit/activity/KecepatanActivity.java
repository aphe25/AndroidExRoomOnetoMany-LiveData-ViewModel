package phedev.app.aplikasiperhitungandebit.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.Random;

import phedev.app.aplikasiperhitungandebit.R;
import phedev.app.aplikasiperhitungandebit.activity.viewmodel.KecepatanViewModel;
import phedev.app.aplikasiperhitungandebit.activity.viewmodel.KecepatanViewModelFactory;
import phedev.app.aplikasiperhitungandebit.adapter.DataAdapter;
import phedev.app.aplikasiperhitungandebit.database.AppDatabase;
import phedev.app.aplikasiperhitungandebit.database.DataEntry;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;
import phedev.app.aplikasiperhitungandebit.helper.AppExecutors;

import static phedev.app.aplikasiperhitungandebit.helper.StaticValue.COOEF_PROJECT;
import static phedev.app.aplikasiperhitungandebit.helper.StaticValue.ID_PROJECT;
import static phedev.app.aplikasiperhitungandebit.helper.StaticValue.LUAS_PROJECT;
import static phedev.app.aplikasiperhitungandebit.helper.StaticValue.NAMA_PROJECT;
import static phedev.app.aplikasiperhitungandebit.helper.StaticValue.PENAMPANG_PROJECT;

public class KecepatanActivity extends AppCompatActivity {
    private String projectname;
    private int penampang, idProject;
    private Double cooef, luas, panjang;
    private AppDatabase appDatabase;
    private Double kecepatan, waktu;
    private DataAdapter adapter;
    private ProjectEntry projectEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecepatan);

        Toolbar toolbar = findViewById(R.id.toolbar_kecepatan);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDataIntent();

        final EditText panjangEdt = findViewById(R.id.panjang_kec_edt);
        Button simpanBtn = findViewById(R.id.button4);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (panjangEdt.getText().toString().isEmpty()){
                    panjangEdt.setError("wajib diisi");
                } else {
                    Double[] dataKecepatan = new Double[]{1.2, 2.3, 5.6, 6.7, 7.3, 8.2, 4.1, 6.2};
                    waktu = dataKecepatan[new Random().nextInt(dataKecepatan.length)];

                    Log.d(KecepatanActivity.class.getSimpleName(), "Saved data");
                    panjang = Double.valueOf(panjangEdt.getText().toString());
                    kecepatan = panjang/waktu;
                    Double qTot = panjang * kecepatan * cooef;
                    final DataEntry dataEntry = new DataEntry(idProject, panjang, waktu, kecepatan, qTot);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase = AppDatabase.getsInstance(getApplicationContext());
                            appDatabase.dataDao().insertData(dataEntry);
                        }
                    });
                }
            }
        });

        Button doneBtn = findViewById(R.id.done_kecepatan_btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase = AppDatabase.getsInstance(getApplicationContext());
                        appDatabase.dataDao().updateProjectData(projectEntry);
                    }
                });
                Log.d(KecepatanActivity.class.getSimpleName(), "Updated data");
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_kecepatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new DataAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        setupViewModel();
    }

    private void getDataIntent(){
        projectname = getIntent().getStringExtra(NAMA_PROJECT);
        penampang = getIntent().getIntExtra(PENAMPANG_PROJECT, 0);
        cooef = getIntent().getDoubleExtra(COOEF_PROJECT, 0);
        luas = getIntent().getDoubleExtra(LUAS_PROJECT,0);
        idProject = getIntent().getIntExtra(ID_PROJECT, 0);
    }

    private void setupViewModel(){
        idProject = getIntent().getIntExtra(ID_PROJECT, 0);
        KecepatanViewModelFactory viewModelFactory = new KecepatanViewModelFactory(appDatabase, idProject);
        KecepatanViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(KecepatanViewModel.class);
        viewModel.getEntryData().observe(this, new Observer<List<DataEntry>>() {
            @Override
            public void onChanged(@Nullable List<DataEntry> dataEntries) {
                adapter.setEntries(dataEntries);
                Double debit = 0.0;
                assert dataEntries != null;
                for (int i = 0; i< dataEntries.size(); i++){
                    debit = debit + dataEntries.get(i).getDebit();
                }
                Double average = debit/dataEntries.size();
                projectEntry = new ProjectEntry(idProject,projectname, cooef, penampang, luas, average);
            }
        });
    }
}
