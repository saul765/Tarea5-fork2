package sv.edu.bitlab.pupusap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
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

    val pupusaStringResources = hashMapOf(
        QUESO to R.string.pupusa_queso,
        FRIJOLES to R.string.frijol_con_queso,
        REVUELTAS to R.string.revueltas
    )

    var botonesMaiz = hashMapOf<String, Button>()
    var botonesArroz = hashMapOf<String, Button>()
    var quesoIzquierda: Button? = null
    var frijolIzquierda: Button? = null
    var revueltaIzquierda: Button? = null

    var quesoDerecha: Button? = null
    var frijolDerecha: Button? = null
    var revueltasDerecha: Button? = null
    var loadingContainer: View? = null

    var sendButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quesoIzquierda = findViewById(R.id.quesoIzquierda)
        frijolIzquierda = findViewById(R.id.frijolIzquierda)
        revueltaIzquierda = findViewById(R.id.revueltasIzquierda)

        botonesMaiz= hashMapOf(
            QUESO to quesoIzquierda!!,
            FRIJOLES to frijolIzquierda!!,
            REVUELTAS to revueltaIzquierda!!
        )

        quesoIzquierda!!.setOnClickListener { addMaiz(QUESO) }
        frijolIzquierda!!.setOnClickListener { addMaiz(FRIJOLES) }
        revueltaIzquierda!!.setOnClickListener { addMaiz(REVUELTAS) }


        quesoDerecha = findViewById(R.id.quesoDerecha)
        frijolDerecha = findViewById(R.id.frijolDerecha)
        revueltasDerecha = findViewById(R.id.revueltasDerecha)

        botonesArroz= hashMapOf(
            QUESO to quesoDerecha!!,
            FRIJOLES to frijolDerecha!!,
            REVUELTAS to revueltasDerecha!!
        )

        quesoDerecha!!.setOnClickListener { addArroz(QUESO) }
        frijolDerecha!!.setOnClickListener { addArroz(FRIJOLES) }
        revueltasDerecha!!.setOnClickListener { addArroz(REVUELTAS) }

        sendButton = findViewById(R.id.sendButton)
        sendButton!!.setOnClickListener { showLoading(true) }

        loadingContainer = findViewById(R.id.loadingContainer)
        loadingContainer!!.setOnClickListener { showLoading(false) }

        displayCounters()
        setActionBar(null)
    }

    fun displayCounters() {
        for ((key,value) in contadoresMaiz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesMaiz[key]!!.text = text
        }


        for ((key,value) in contadoresArroz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesArroz[key]!!.text = text
        }

    }

    fun addMaiz(relleno: String) {
        contadoresMaiz[relleno] = contadoresMaiz[relleno]!! + 1
        val contador = contadoresMaiz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesMaiz[relleno]!!.text = text
        var s = "hola"
        s = "$s mundo"
    }
    fun addArroz(relleno: String) {
        contadoresArroz[relleno] = contadoresArroz[relleno]!! + 1
        val contador = contadoresArroz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesArroz[relleno]!!.text = text
    }

    fun showLoading(show: Boolean) {
        val visibility = if(show) View.VISIBLE else View.GONE
        loadingContainer!!.visibility = visibility
    }

    companion object{
        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
    }

}
