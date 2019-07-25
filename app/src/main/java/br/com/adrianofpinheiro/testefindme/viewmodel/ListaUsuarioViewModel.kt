package br.com.adrianofpinheiro.testefindme.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.adrianofpinheiro.testefindme.model.dao.BancoDeDados
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao

class ListaUsuarioViewModel(application : Application) : AndroidViewModel(
    application
) {

    private val db: BancoDeDados = BancoDeDados.getDatabase(application.applicationContext)!!

    val situacoes : LiveData<List<Situacao>> = db.situacaoDAO().lerSituacao()




//    init {
//
//        carregaDados()
//
//    }
//
//    private fun carregaDados() {
//
//        situacoes = db.situacaoDAO().lerSituacao()
//
//    }


}