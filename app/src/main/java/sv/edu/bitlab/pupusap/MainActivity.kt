package sv.edu.bitlab.pupusap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.ARROZ_FRIJOLES
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.ARROZ_QUESO
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.ARROZ_REVUELTAS
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.MAIZ_FRIJOLES
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.MAIZ_QUESO
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.MAIZ_REVUELTAS

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
        sendButton!!.setOnClickListener {
            confirmarOrden()
        }

        loadingContainer = findViewById(R.id.loadingContainer)
        loadingContainer!!.setOnClickListener { showLoading(false) }

        displayCounters()
        setActionBar(null)
        Log.d("ACTIVITY", "MainActivity onCreate()")
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



    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY", "MainActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY", "MainActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY", "MainActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY", "MainActivity onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY", "MainActivity onDestroy()")
        Toast.makeText(this.application, "onDestroy()", Toast.LENGTH_LONG).show()
    }

    private fun confirmarOrden() {
        val intent = Intent(this, DetalleOrdeActivity::class.java)
        intent.putExtra(ARROZ_QUESO, contadoresArroz[QUESO])
        intent.putExtra(ARROZ_FRIJOLES, contadoresArroz[FRIJOLES])
        intent.putExtra(ARROZ_REVUELTAS, contadoresArroz[REVUELTAS])

        intent.putExtra(MAIZ_QUESO, contadoresMaiz[QUESO])
        intent.putExtra(MAIZ_FRIJOLES, contadoresMaiz[FRIJOLES])
        intent.putExtra(MAIZ_REVUELTAS, contadoresMaiz[REVUELTAS])

        this.startActivity(intent)
    }

    fun showLoading(show: Boolean) {
        val visibility = if(show) View.VISIBLE else View.GONE
        loadingContainer!!.visibility = visibility
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    companion object{
        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
        const val MAIZ = "MAIZ"
        const val ARROZ = "ARROZ"
    }

}
