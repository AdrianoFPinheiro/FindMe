package br.com.adrianofpinheiro.testefindme.view

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.adrianofpinheiro.testefindme.R
import br.com.adrianofpinheiro.testefindme.model.dao.BancoDeDados
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao
import kotlinx.android.synthetic.main.activity_situacao.*
import java.text.SimpleDateFormat
import java.util.*

class SituacaoActivity : AppCompatActivity() {

    lateinit var pegaLatitude: String
    lateinit var pegaLongitude: String
    lateinit var pegaDataInicial: String
    lateinit var db: BancoDeDados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situacao)

        db = BancoDeDados.getDatabase(applicationContext)!!
        pegaLatitude = intent.getStringExtra("Latitude")
        pegaLongitude = intent.getStringExtra("Longitude")

        pegaDataInicial = intent.getStringExtra("DataInicial")

        btFinalizarVisita.setOnClickListener {
            finalizaVisita()
            val detalheIntent = Intent(this, ListaActivity::class.java)
            startActivity(detalheIntent)
            finish()
        }
    }

    private fun finalizaVisita() {
        val pegaTexto = etSituacao.getText().toString()
        val formatDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val dataFinal = formatDate.format(Date())

        val dadosSituacao = Situacao(
            0,
            pegaLatitude + pegaLongitude,
            pegaDataInicial,
            pegaTexto,
            dataFinal
        )
        Log.i("TAG - Localizacao", pegaLatitude)
        Log.i("TAG - Localizacao", pegaLongitude)
        Log.i("TAG - Texto", pegaTexto)

        if (dadosSituacao.situacao!!.isNotEmpty()) {
            InsertAsyncTask(db!!).execute(dadosSituacao)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val situacaoIntent = Intent(this@SituacaoActivity, ListaActivity::class.java)
        startActivity(situacaoIntent)
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private inner class InsertAsyncTask internal
    constructor(appDatabase: BancoDeDados) : AsyncTask<Situacao, Void, String>() {

        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Situacao?): String {
            db.situacaoDAO().inserir(params[0]!!)
            return ""
        }
    }
}
