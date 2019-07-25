package br.com.adrianofpinheiro.testefindme.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.adrianofpinheiro.testefindme.R
import br.com.adrianofpinheiro.testefindme.adapter.SituacaoAdapter
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.app_bar_lista.*
import kotlinx.android.synthetic.main.content_lista.*
import org.jetbrains.anko.alert
import br.com.adrianofpinheiro.testefindme.viewmodel.ListaUsuarioViewModel

class ListaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: SituacaoAdapter = SituacaoAdapter(listOf(), this, {
        Log.i("TAG", "MEU ITEM")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setSupportActionBar(toolbar)

        val situacoesMock = situacoesMock()
        mostraDados()

        val layoutManager = LinearLayoutManager(this)
        rvListaSituacoes.layoutManager = layoutManager

        rvListaSituacoes.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this@ListaActivity, MainActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        mostraDados()

    }

    private fun mostraDados() {

        ViewModelProviders.of(this)
            .get(ListaUsuarioViewModel::class.java)
            .situacoes
            .observe(this, Observer<List<Situacao>> { situacoes ->
                adapter.setList(situacoes!!)
            })

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_info -> {
                startActivity(Intent(this, SobreActivity::class.java))
            }

            R.id.nav_exit -> {
                alert(
                    R.string.msg_confirma_sair,
                    R.string.app_name
                ) {
                    positiveButton(R.string.sim) {
                        finish()
                    }
                    negativeButton(R.string.nao) {
                        // Não confirmou...
                    }
                }.show()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun situacoesMock(): List<Situacao> {
        return listOf(
            Situacao(
                1,
                "100.2999983 25.2999983",
                "25/7/2019 02:46:57",
                "Texto Digitado",
                "25/7/2019 02:47:00"
            ),
            Situacao(
                2,
                "100.29767683 25.2945383",
                "25/7/2019 02:46:57",
                "Texto Digitado",
                "25/7/2019 02:47:00"
            ),
            Situacao(
                3,
                "123.29767683 25.7655383",
                "25/7/2019 02:46:57",
                "Texto Digitado",
                "25/7/2019 02:47:00"
            ),
            Situacao(
                4,
                "23.2976432 255.7655321",
                "25/7/2019 02:46:57",
                "Texto Digitado",
                "25/7/2019 02:47:00"
            )
        )
    }
}
