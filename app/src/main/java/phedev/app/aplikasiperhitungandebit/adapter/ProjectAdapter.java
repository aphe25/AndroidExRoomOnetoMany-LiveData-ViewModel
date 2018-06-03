package phedev.app.aplikasiperhitungandebit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import phedev.app.aplikasiperhitungandebit.activity.KecepatanActivity;
import phedev.app.aplikasiperhitungandebit.R;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;
import phedev.app.aplikasiperhitungandebit.helper.StaticValue;

/**
 * Created by phedev in 2018.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private Context context;
    private ItemClickListener mItemClickListener;
    private List<ProjectEntry> entries;
    private DecimalFormat df;

    public ProjectAdapter(Context context, ItemClickListener clickListener){
        this.context = context;
        this.mItemClickListener = clickListener;
    }

    public interface ItemClickListener{
        void onItemClickListener(int id);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.content_recycle_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProjectEntry projectEntry = entries.get(position);

        holder.titleTxt.setText(projectEntry.getProjectName());
        String qRest = convertToFormat(projectEntry.getqrest());
        holder.resultTxt.setText(String.format("Q rata -rata = %s cm3/s", qRest));
        holder.addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KecepatanActivity.class);
                intent.putExtra(StaticValue.ID_PROJECT, projectEntry.getId());
                intent.putExtra(StaticValue.COOEF_PROJECT, projectEntry.getKoefisien());
                intent.putExtra(StaticValue.NAMA_PROJECT, projectEntry.getProjectName());
                intent.putExtra(StaticValue.PENAMPANG_PROJECT, projectEntry.getJenisPenampang());
                intent.putExtra(StaticValue.LUAS_PROJECT, projectEntry.getLuas());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (projectEntry.getJenisPenampang() == 1){
            holder.imageView.setImageResource(R.drawable.persegi);
        } else if (projectEntry.getJenisPenampang() == 2){
            holder.imageView.setImageResource(R.drawable.trapesium);
        }


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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTxt, resultTxt;
        ImageView imageView;
        Button addDataBtn;

        ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.title_content_txt);
            resultTxt = itemView.findViewById(R.id.average_content_txt);
            imageView = itemView.findViewById(R.id.image_content);
            addDataBtn = itemView.findViewById(R.id.button_add_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = entries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }

    public List<ProjectEntry> getEntries(){
        return entries;
    }

    public void setEntries(List<ProjectEntry> projectEntry){
        entries = projectEntry;
        notifyDataSetChanged();
    }

    private String convertToFormat(double value){
        df = new DecimalFormat("##.##");
        return df.format(value);
    }
}
