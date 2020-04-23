package com.example.questionapp

import Models.Lembrete
import Models.MaskEditUtil
import android.app.TimePickerDialog
import java.util.*
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_screen.*
import java.text.SimpleDateFormat

class Formulario_Class : AppCompatActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Ativa o modo imersivo de tela
        // Para o modo "recuar", remova SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Esconde barra de status e barra de navegação
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN

                )
    }

    // Mostra as barras do sistema removendo todos os sinalizadores
    //Exceto aqueles que fazem o conteúdo aparecer sob as barras do sistema.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
    }

    //Função que criar um item na lista apartir de uma declaração de objeto Lembrete
    fun AddLembrete(nome:String,hora:String,descricao:String,importancia:String){
        list.add(Lembrete(nome,hora,descricao,importancia))
    }
    //A lista é composta por vários objetos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        showSystemUI()

        //declaração de variáveis
        var descricao:TextView =  findViewById(R.id.Plain_Descricao)
        var radio_group:RadioGroup = findViewById(R.id.Radio_Importancia)
        var importancia:String = ""

        //Manda o text do radioButton selecionado pra um variável
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{ radioGroup, CheckId ->
                val radio:RadioButton = findViewById(CheckId)
                importancia = radio.text.toString()
                Toast.makeText(applicationContext,"Botão:${radio.text}",Toast.LENGTH_LONG).show()
            }
        )

        //Mandar hora pro TextView atravez do TimePicker
        TextField_Hora.setOnClickListener{
            val cal = Calendar.getInstance() //Elemento java.util.*
            Toast.makeText(applicationContext,"Selecione a hora",Toast.LENGTH_LONG).show()

            val timerpicker = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour) //Efeitos de formatação de hora
                cal.set(Calendar.MINUTE,minute) //Efeitos de formatação de minutos
                //Mandar para o textView
                Plain_Hora.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this,timerpicker,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }



        //Ação ao clicar no botão Salvar
        Salvar_Button.setOnClickListener {
            var id:Int = radio_group.checkedRadioButtonId
            if(id==-1){
                Toast.makeText(applicationContext,"Nenhuma importância selecionada selecionada",Toast.LENGTH_LONG).show()
            }
            if(Plain_Nome.text.isEmpty() || Plain_Hora.text.isEmpty() || Plain_Descricao.text.isEmpty()){
                Toast.makeText(applicationContext,"É necessário que todos os campos estejam preenchidos",Toast.LENGTH_LONG).show()
            }
            else{
                AddLembrete(Plain_Nome.text.toString(),Plain_Hora.text.toString(),Plain_Descricao.text.toString(),importancia)
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
