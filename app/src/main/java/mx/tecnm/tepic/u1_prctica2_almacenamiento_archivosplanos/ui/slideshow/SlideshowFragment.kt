package mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.ui.slideshow

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.Archivos
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.databinding.FragmentSlideshowBinding
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.ui.gallery.GalleryFragment
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class SlideshowFragment : Fragment() {

private var _binding: FragmentSlideshowBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

    _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
    val root: View = binding.root

      binding.bModificar.setOnClickListener {
          try {
              val openn = InputStreamReader(requireActivity().openFileInput("animales.txt"))
              var datos = openn.readLines()
              var oa=1
              for (i in 0..datos.size - 1) {
                  var apoyo = datos[i].split("|")
                  agregar(i,apoyo[0],apoyo[1],apoyo[2])
              }
              var posicion =0;
              for (i in 0..Archivos.nombres.size - 1) {
                  if (Archivos.nombres[i].equals(binding.eNombre.text.toString())){
                      binding.eDueno.setText(Archivos.duenos[i])
                      binding.eRaza.setText(Archivos.razas[i])
                      posicion=i;
                      val archivo = OutputStreamWriter(requireActivity().openFileOutput("animales.txt", 0))
                      for (i in 0..datos.size - 1) {
                          if (i==posicion) {
                              Archivos.nombres[i] = binding.eNombre.text.toString()
                              Archivos.duenos[i] = binding.eDueno.text.toString()
                              Archivos.razas[i] = binding.eRaza.text.toString()
                              archivo.write(Archivos.nombres[i] + "|" + Archivos.duenos[i] + "|" + Archivos.razas[i] + "\n")
                          }else{
                              archivo.write(Archivos.nombres[i] + "|" + Archivos.duenos[i] + "|" + Archivos.razas[i] + "\n")
                          }
                      }
                      archivo.flush()
                      archivo.close()
                      limpiar()
                  }else{
                      oa=1
                  }
              }
              if (oa==1){
                  AlertDialog.Builder(requireContext())
                      .setMessage("No existe ese animal").show()
              }

          }catch (e:Exception){}


      }


    val textView: TextView = binding.textSlideshow
    slideshowViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    public fun limpiar(){
        binding.eNombre.setText("")
        binding.eDueno.setText("")
        binding.eRaza.setText("")
    }
    public fun agregar(i:Int,n: String,d: String,r:String){
        Archivos.nombres[i] = n
        Archivos.duenos[i] = d
        Archivos.razas[i] = r
    }
}