package br.com.adrianofpinheiro.testefindme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.testefindme.R
import br.com.adrianofpinheiro.testefindme.model.entity.Situacao
import kotlinx.android.synthetic.main.situacao_item.view.*

class SituacaoAdapter(
    private var situacoes: List<Situacao>,
    private val context: Context,
    val listener: (Situacao) -> Unit
) : RecyclerView.Adapter<SituacaoAdapter.ViewHolder>() {

    //Método que recebe o ViewHolder e a posição da lista.
    //Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val situacao = situacoes[position]
        holder.let {
            it.bindView(situacao, listener)
        }
    }

    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.situacao_item, parent, false)
        return ViewHolder(view)
    }

    //Método que deverá retornar quantos itens há na lista.
    override fun getItemCount(): Int {
        return situacoes.size
    }

    /*
    Com o ViewHolder iremos relacionar o layout criado e adicionar os valores a ele*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            situacao: Situacao,
            listener: (Situacao) -> Unit
        ) = with(itemView) {
            val fieldLatLon = tvLatLon
            val fieldIniciada = tvIniciada
            val fieldSituacao = tvSituacao
            val fieldFinalizada = tvFinalizada
            fieldLatLon.text = situacao.latLng
            fieldIniciada.text = situacao.iniciada
            fieldSituacao.text = situacao.situacao
            fieldFinalizada.text = situacao.finalizada

            setOnClickListener { listener(situacao) }
        }
    }

    fun setList(situacoes: List<Situacao>) {

        this.situacoes = situacoes
        notifyDataSetChanged()

    }

}