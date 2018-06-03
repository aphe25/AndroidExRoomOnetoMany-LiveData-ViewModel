package phedev.app.aplikasiperhitungandebit.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import phedev.app.aplikasiperhitungandebit.R;
import phedev.app.aplikasiperhitungandebit.activity.viewmodel.MainViewModel;
import phedev.app.aplikasiperhitungandebit.adapter.ProjectAdapter;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;

public class MainActivity extends AppCompatActivity implements ProjectAdapter.ItemClickListener {
    private ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProjectActivity.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ProjectAdapter(this, this);
        recyclerView.setAdapter(adapter);

        setupViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(int id) {
        Toast.makeText(getApplicationContext(), "you clicked " + String.valueOf(id), Toast.LENGTH_SHORT).show();
    }

    private void setupViewModel(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getProjectEntries().observe(this, new Observer<List<ProjectEntry>>() {
            @Override
            public void onChanged(@Nullable List<ProjectEntry> entryList) {
                Log.d(MainActivity.class.getSimpleName(), "Updating data from viewModel");
                adapter.setEntries(entryList);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
