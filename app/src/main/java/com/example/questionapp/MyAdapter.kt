package com.example.questionapp

import Models.Lembrete
import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView

class MyAdapter(var mCtx:Context,var resources:Int,var items:List<Lembrete>):ArrayAdapter<Lembrete>(mCtx,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val NomeView:TextView = view.findViewById(R.id.textView)
        val HoraView:TextView = view.findViewById(R.id.textView2)
        val DescricaoView:TextView = view.findViewById(R.id.textView3)
        val ImportanciaView:TextView = view.findViewById(R.id.Important)

        val mItem:Lembrete = items[position]
        NomeView.text = mItem.nome
        HoraView.text = mItem.hora
        DescricaoView.text = mItem.descricao

        when(mItem.importancia){
            "Pouco Importante" -> {
                ImportanciaView.text = "Pouco Importante"
                ImportanciaView.setTextColor(Color.GREEN)
            }

            "Importante" -> {
                ImportanciaView.text = "Importante"
                ImportanciaView.setTextColor(Color.YELLOW)
            }

            "Urgente" ->{
                ImportanciaView.text = "Urgente"
                ImportanciaView.setTextColor(Color.RED)
            }

            else -> {
                ImportanciaView.text = "Importância não definida"
                ImportanciaView.setTextColor(Color.BLUE)
            }
        }

        return view
    }
}
//Lista responsável por receber os itens escritos
var list = mutableListOf<Lembrete>()