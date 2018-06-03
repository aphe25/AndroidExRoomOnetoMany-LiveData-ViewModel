package phedev.app.aplikasiperhitungandebit.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by phedev in 2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = ProjectEntry.class,
        parentColumns = "id",
        childColumns = "id_data",
        onDelete = CASCADE), tableName = "data",
        indices = @Index("id_data"))

public class DataEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "id_data")
    private int idData;
    @ColumnInfo(name = "panjang_lintasan")
    private Double panjangLintasan;
    private Double waktu;
    private Double kecepatan;
    private Double debit;

    @Ignore
    public DataEntry(int idData, Double panjangLintasan, Double waktu, Double kecepatan, Double debit){
        this.idData = idData;
        this.panjangLintasan = panjangLintasan;
        this.waktu = waktu;
        this.kecepatan = kecepatan;
        this.debit = debit;
    }

    public DataEntry(int id, int idData, Double panjangLintasan, Double waktu, Double kecepatan, Double debit){
        this.idData = idData;
        this.id = id;
        this.panjangLintasan = panjangLintasan;
        this.waktu = waktu;
        this.kecepatan = kecepatan;
        this.debit = debit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public Double getPanjangLintasan() {
        return panjangLintasan;
    }

    public void setPanjangLintasan(Double panjangLintasan) {
        this.panjangLintasan = panjangLintasan;
    }

    public Double getWaktu() {
        return waktu;
    }

    public void setWaktu(Double waktu) {
        this.waktu = waktu;
    }

    public Double getKecepatan() {
        return kecepatan;
    }

    public void setKecepatan(Double kecepatan) {
        this.kecepatan = kecepatan;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }
}
