package com.example.kjogovelha

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val playerOne = arrayListOf<Int>()
    val playerTwo = arrayListOf<Int>()
    var currentPlayer = 1
    var jogo_rodando = true
    var jogador_vencedor = "player"
    var rodadas = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun play(position: Int, btnSelected: Button){

       if(jogo_rodando && rodadas <= 9) {

           if (currentPlayer == 1) {
               btnSelected.text = "X"
               btnSelected.setBackgroundResource(R.drawable.gradiente_background_button_orange)
               btnSelected.setTextColor(Color.parseColor("#006400"))

               playerOne.add(position)
               status.setText("Player 1 jogou")
               currentPlayer = 2
           } else {
               btnSelected.text = "O"
               btnSelected.setBackgroundResource(R.drawable.gradiente_background_button_green)
               btnSelected.setTextColor(Color.parseColor("#FFD700"))
               playerTwo.add(position)
               status.setText("Player 2 jogou")
               currentPlayer = 1
           }

           btnSelected.isClickable = false

           checkResult()
           rodadas++


       }else{
           if(rodadas == 9){
               Toast.makeText(this,"Jogo empatado. \nClique em reinicar para jogar novamente!!",Toast.LENGTH_LONG).show()

           }else{
               Toast.makeText(this,"$jogador_vencedor ganhou. \nClique em reinicar para jogar novamente!!",Toast.LENGTH_LONG).show()
           }
       }

    }


    fun btnPosition(view: View) = when(view.id){

        R.id.btn1 -> play(1,view as Button)
        R.id.btn2 -> play(2,view as Button)
        R.id.btn3 -> play(3,view as Button)
        R.id.btn4 -> play(4,view as Button)
        R.id.btn5 -> play(5,view as Button)
        R.id.btn6 -> play(6,view as Button)
        R.id.btn7 -> play(7,view as Button)
        R.id.btn8 -> play(8,view as Button)
        R.id.btn9 -> play(9,view as Button)
        else -> play(0, view as Button)
    }


    fun limparBtn(){

        btn1.isClickable = true
        btn1.setText("")
        btn1.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn2.isClickable = true
        btn2.setText("")
        btn2.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn3.isClickable = true
        btn3.setText("")
        btn3.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn4.isClickable = true
        btn4.setText("")
        btn4.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn5.isClickable = true
        btn5.setText("")
        btn5.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn6.isClickable = true
        btn6.setText("")
        btn6.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn7.isClickable = true
        btn7.setText("")
        btn7.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn8.isClickable = true
        btn8.setText("")
        btn8.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

        btn9.isClickable = true
        btn9.setText("")
        btn9.setBackgroundResource(R.drawable.gradiente_background_button)
        //#########

    }


    fun checkResult(){
        val row1 = listOf(1,2,3)
        val row2 = listOf(4,5,6)
        val row3 = listOf(7,8,9)

        val col1 = listOf(1,4,7)
        val col2 = listOf(2,5,8)
        val col3 = listOf(3,6,9)

        val diag1 = listOf(1,5,9)
        val diag2 = listOf(3,5,7)

        var winner = -1

        if(playerOne.containsAll(row1) || playerOne.containsAll(row2) || playerOne.containsAll(row3)||
            playerOne.containsAll(col1) || playerOne.containsAll(col2) || playerOne.containsAll(col3)||
            playerOne.containsAll(diag1) || playerOne.containsAll(diag2) ){
            winner = 1
        }

        if(playerTwo.containsAll(row1) || playerTwo.containsAll(row2) || playerTwo.containsAll(row3)||
            playerTwo.containsAll(col1) || playerTwo.containsAll(col2) || playerTwo.containsAll(col3)||
            playerTwo.containsAll(diag1) || playerTwo.containsAll(diag2) ){
            winner = 2
        }

        when(winner){
            1 -> gameOver("Parabens!! Player 1 Venceu","Player 1",1)
            2 -> gameOver("Parabens!! Player 2 Venceu", "Player 2",2)
        }



    }


    fun gameOver(frase:String, jogador:String, vencedor:Int){

        Toast.makeText(this,frase,Toast.LENGTH_LONG).show()
        status.text = "Payer $jogador ganhou!!!"
        jogo_rodando = false

        if(vencedor == 1){
            jogador_vencedor = "Player 1"
            abrirDialogo("Player 1")

        }else{
            jogador_vencedor = "Player 2"
            abrirDialogo("Player 2")

        }



    }

    fun restart(view: View){
        reiniciar()
    }

    fun reiniciar(){
        playerOne.clear()
        playerTwo.clear()
        limparBtn()
        status.setText("Novo jogo começou!!!")
        jogo_rodando = true
        rodadas = 1



    }

    fun abrirDialogo(player:String){


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Fim de jogo")
        builder.setMessage("$player ganhou!!\niniciar novo jogo?")


        builder.setPositiveButton("Reiniciar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Jogo reiniciado!", Toast.LENGTH_SHORT).show()
            reiniciar()
        }

        builder.setNegativeButton("Fechar") { dialog, which ->
            Toast.makeText(applicationContext,
                "Jogo finalizado", Toast.LENGTH_SHORT).show()
            this.finish()
        }


        builder.show()

    }



}
