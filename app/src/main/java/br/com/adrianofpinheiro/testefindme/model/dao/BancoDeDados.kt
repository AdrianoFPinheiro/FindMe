package br.com.adrianofpinheiro.testefindme.model.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao

@Database(entities = arrayOf(Situacao::class), version = 1)
abstract class BancoDeDados : RoomDatabase() {

    abstract fun situacaoDAO(): SituacaoDAO

    companion object {

        var INSTANCE: BancoDeDados? = null

        fun getDatabase(context: Context) : BancoDeDados? {

            if(INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    BancoDeDados::class.java,
                    "situacaodbs").build()

            }

            return INSTANCE

        }

    }

}