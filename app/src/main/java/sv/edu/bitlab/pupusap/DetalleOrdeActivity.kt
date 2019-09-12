package sv.edu.bitlab.pupusap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class DetalleOrdeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detalle_orde)
    Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()
    val extras = this.intent.extras
    Log.d("ACTIVITY", "onCreate()")
  }

  companion object{
    const val ARROZ_QUESO = "ARROZ_QUESO"
    const val ARROZ_FRIJOLES = "ARROZ_FRIJOLES"
    const val ARROZ_REVUELTAS = "ARROZ_REVUELTAS"

    const val MAIZ_QUESO = "MAIZ_QUESO"
    const val MAIZ_FRIJOLES = "MAIZ_FRIJOLES"
    const val MAIZ_REVUELTAS = "MAIZ_REVUELTAS"
  }
}
