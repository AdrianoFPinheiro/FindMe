package br.com.adrianofpinheiro.testefindme.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao

@Dao
interface SituacaoDAO {

    @Insert
    fun inserir(situacao: Situacao)

    @Query("SELECT * FROM tabelasituacao")
    fun lerSituacao(): LiveData<List<Situacao>>

    @Update
    fun atualizar(situacao: Situacao)

    @Delete
    fun apagar(situacao: Situacao)

}