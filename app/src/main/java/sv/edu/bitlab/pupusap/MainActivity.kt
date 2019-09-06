package sv.edu.bitlab.pupusap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(){
    var contadoresMaiz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )

    var contadoresArroz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )
    var quesoIzquierda :Button?=null
    var frijolIzquierda :Button?=null
    var revueltaIzquierda :Button?=null

    var quesoDerecha :Button?=null
    var frijolDerecha :Button?=null
    var revueltasDerecha :Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quesoIzquierda = findViewById(R.id.quesoIzquierda)
        frijolIzquierda = findViewById(R.id.frijolIzquierda)
        revueltaIzquierda = findViewById(R.id.revueltasIzquierda)

        quesoIzquierda!!.setOnClickListener { addMaiz(QUESO) }
        frijolIzquierda!!.setOnClickListener { addMaiz(FRIJOLES) }
        revueltaIzquierda!!.setOnClickListener { addMaiz(REVUELTAS) }


        quesoDerecha = findViewById(R.id.quesoDerecha)
        frijolDerecha = findViewById(R.id.frijolDerecha)
        revueltasDerecha = findViewById(R.id.revueltasDerecha)

        quesoDerecha!!.setOnClickListener { addArroz(QUESO) }
        frijolDerecha!!.setOnClickListener { addArroz(FRIJOLES) }
        revueltasDerecha!!.setOnClickListener { addArroz(REVUELTAS) }


    }

    fun addMaiz(relleno: String) {
        contadoresMaiz[relleno] = contadoresMaiz[relleno]!! + 1
    }

    fun addArroz(relleno: String) {
        contadoresArroz[relleno] = contadoresArroz[relleno]!! + 1

    }

    companion object{
        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
    }

}
