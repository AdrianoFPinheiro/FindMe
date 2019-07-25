package br.com.adrianofpinheiro.testefindme.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import java.util.*

@Entity(tableName = "TabelaSituacao")
data class Situacao (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var latLng: String? = "",
    var iniciada: String? = "",
    var situacao: String? = "",
    var finalizada: String = ""
)