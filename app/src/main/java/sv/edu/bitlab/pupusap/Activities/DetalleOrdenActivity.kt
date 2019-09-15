package sv.edu.bitlab.pupusap.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import sv.edu.bitlab.pupusap.MainActivity
import sv.edu.bitlab.pupusap.R
import sv.edu.bitlab.pupusap.fragments.DetalleOrdenFragment

class DetalleOrdenActivity : AppCompatActivity(), DetalleOrdenFragment.DetalleOrdenFragmentListener {
    var arroz =ArrayList<Int>()
  var maiz =ArrayList<Int>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detalle_orden)
    val params = this.intent.extras
    arroz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!
    maiz = params!!.getIntegerArrayList(CONTADOR_MAIZ)!!
    Log.d("Orden array1",arroz.toString())
    Log.d("Orden array2",maiz.toString())
    confirmarOrdenFragment()

  }
  override fun onFragmentInteraction(uri: Uri) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  private fun confirmarOrdenFragment() {

    val fragment = DetalleOrdenFragment.newInstance(arroz, maiz)
    val builder = supportFragmentManager
      .beginTransaction()
      .add(R.id.detalle_orden, fragment, FRAGMENT_TAG)
    builder.commit()


  }
  fun getDescripcion(index: Int): String {
    return when(index){
      QUESO -> "Queso de arroz"
      FRIJOLES -> "Frijol con queso de arroz"
      REVUELTAS -> "Revueltas de arroz"
      QUESO_MAIZ -> "Queso de maiz"
      FRIJOLES_MAIZ -> "Frijol con queso de maiz"
      REVUELTAS_MAIZ -> "Revueltas de maiz"
      else -> throw RuntimeException("Pupusa no soportada")
    }
  }


  companion object{
    const val QUESO = 0//3
    const val FRIJOLES = 1//4
    const val REVUELTAS = 2//5
    const val QUESO_MAIZ = 3//3
    const val FRIJOLES_MAIZ = 4//4
    const val REVUELTAS_MAIZ = 5//5
    const val CONTADOR_ARROZ = "ARROZ"
    const val CONTADOR_MAIZ = "MAIZ"
    const val VALOR_PUPUSA = 0.5F
    const val FRAGMENT_TAG = "FRAGMENT_TAG"
  }
}
