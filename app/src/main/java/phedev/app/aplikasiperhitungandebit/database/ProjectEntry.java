package phedev.app.aplikasiperhitungandebit.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by phedev in 2018.
 */

@Entity(tableName = "projects")
public class ProjectEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "project_name")
    private String projectName;
    private Double koefisien;
    @ColumnInfo(name = "jenis_penampang")
    private int jenisPenampang;
    private Double luas;
    private Double qrest;

    @Ignore
    public ProjectEntry(int id, String name, Double koefisien, int jenisPenampang, Double luas, Double qrest){
        this.id = id;
        this.projectName = name;
        this.koefisien = koefisien;
        this.jenisPenampang = jenisPenampang;
        this.luas = luas;
        this.qrest = qrest;
    }

    public ProjectEntry(String projectName, Double koefisien, int jenisPenampang, Double luas, Double qrest){
        this.projectName = projectName;
        this.koefisien = koefisien;
        this.jenisPenampang = jenisPenampang;
        this.luas = luas;
        this.qrest = qrest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getKoefisien() {
        return koefisien;
    }

    public void setKoefisien(Double koefisien) {
        this.koefisien = koefisien;
    }

    public int getJenisPenampang() {
        return jenisPenampang;
    }

    public void setJenisPenampang(int jenisPenampang) {
        this.jenisPenampang = jenisPenampang;
    }

    public Double getLuas() {
        return luas;
    }

    public void setLuas(Double luas) {
        this.luas = luas;
    }

    public Double getqrest() {
        return qrest;
    }

    public Double getQrest() {
        return qrest;
    }

    public void setQrest(Double qrest) {
        this.qrest = qrest;
    }

    public void setqResult(Double qrest) {
        this.qrest = qrest;
    }
}
