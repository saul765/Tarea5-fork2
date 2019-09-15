package sv.edu.bitlab.pupusap.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_detalle_orden.*
import kotlinx.android.synthetic.main.fragment_detalle_orden.view.*
import sv.edu.bitlab.pupusap.R
import java.text.DecimalFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetalleOrdenFragment.DetalleOrdenFragmentListener] interface
 * to handle interaction events.
 * Use the [DetalleOrdenFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetalleOrdenFragment : Fragment() {
  // TODO: Rename and change types of parameters
  private lateinit var arroz: ArrayList<Int>
  private lateinit var  maiz: ArrayList<Int>
  private var listener: DetalleOrdenFragmentListener? = null
  val handler = Handler()
  val lineItemsIDs = arrayOf(
    arrayOf(R.id.lineItemDetail1, R.id.lineItemPrice1),
    arrayOf(R.id.lineItemDetail2, R.id.lineItemPrice2),
    arrayOf(R.id.lineItemDetail3, R.id.lineItemPrice3),
    arrayOf(R.id.lineItemDetail4, R.id.lineItemPrice4),
    arrayOf(R.id.lineItemDetail5, R.id.lineItemPrice5),
    arrayOf(R.id.lineItemDetail6, R.id.lineItemPrice6)
  )



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      arroz = it.getIntegerArrayList(ARG_PARAM1)!!
      maiz = it.getIntegerArrayList(ARG_PARAM2)!!

    }

    Log.d("Orden Fragment",arroz.toString())
    Log.d("Orden Fragment2",maiz.toString())
    //displayDetalle()


  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val vista=inflater.inflate(R.layout.fragment_detalle_orden, container, false)
    displayDetalle(vista)

    vista.comfirmOrder.setOnClickListener {

      loadingLayout.visibility=View.VISIBLE

      handler.postDelayed({
        activity!!.runOnUiThread {
            loadingLayout.visibility=View.GONE
            sentOrderLayout.visibility=View.VISIBLE
        }
      }, FIVE_SECONDS)

    }

    vista.sentOrderLayout.setOnClickListener {

      sentOrderLayout.visibility=View.GONE
    }

    return vista
  }

  // TODO: Rename method, update argument and hook method into UI event
  fun onButtonPressed(uri: Uri) {
    listener?.onFragmentInteraction(uri)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is DetalleOrdenFragmentListener) {
      listener = context
    } else {
      throw RuntimeException(context.toString() + " must implement DetalleOrdenFragmentListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   *
   *
   * See the Android Training lesson [Communicating with Other Fragments]
   * (http://developer.android.com/training/basics/fragments/communicating.html)
   * for more information.
   */
  interface DetalleOrdenFragmentListener {
    // TODO: Update argument type and name
    fun onFragmentInteraction(uri: Uri)
  }

  fun displayDetalle(vista:View) {
    val arr = arroz + maiz
    var total = 0.0f
    for((index, contador) in arr.withIndex()){
      val ids = lineItemsIDs[index]
      val detailTexview = vista.findViewById<TextView>(ids[0])
      val priceTextView= vista.findViewById<TextView>(ids[1])
      if(contador > 0){
        val totalUnidad = contador * VALOR_PUPUSA
        val descripcion = getDescripcion(index)
        detailTexview.text = getString(
          R.string.pupusa_line_item_description,
          contador, descripcion)
        total += totalUnidad
        val precio = DecimalFormat("$#0.00").format(totalUnidad)
        priceTextView.text = precio
      } else{
        detailTexview.visibility = View.GONE
        priceTextView.visibility = View.GONE
      }
    }
    val totalPrecio = vista.lineItemPriceTotal
    val precio = DecimalFormat("$#0.00").format(total)
    totalPrecio.text = precio

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

  companion object {

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
    const val FIVE_SECONDS = 5000L
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: ArrayList<Int>,param2: ArrayList<Int>) =
      DetalleOrdenFragment().apply {
        arguments = Bundle().apply {
          putIntegerArrayList(ARG_PARAM1, param1)
          putIntegerArrayList(ARG_PARAM2, param2)
        }
      }
  }
}
