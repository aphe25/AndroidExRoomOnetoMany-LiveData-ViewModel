package phedev.app.aplikasiperhitungandebit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import phedev.app.aplikasiperhitungandebit.R;
import phedev.app.aplikasiperhitungandebit.database.DataEntry;

/**
 * Created by phedev in 2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<DataEntry> entries;
    private Context context;
    private DecimalFormat df;

    public DataAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.content_recycler_kecepatan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataEntry dataEntry = entries.get(position);
        String waktu = convertToFormat(dataEntry.getWaktu());
        String kecepatan = convertToFormat(dataEntry.getKecepatan());
        holder.textView.setText(String.format("%s s | %s m/s", waktu, kecepatan));
    }

    @Override
    public int getItemCount() {
        int size;
        if (entries == null){
            size = 0;
        } else {
            size = entries.size();
        }
        return size;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_content_kec);
        }
    }

    public List<DataEntry> dataEntries(){
        return entries;
    }

    public void setEntries(List<DataEntry> dataEntries){
        entries = dataEntries;
        notifyDataSetChanged();
    }


    private String convertToFormat(double value){
        df = new DecimalFormat("##.##");
        return df.format(value);
    }
}
