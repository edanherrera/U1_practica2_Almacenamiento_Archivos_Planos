package mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.ui.gallery

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.Archivos
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.CustomAdapter
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.databinding.FragmentGalleryBinding
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.bAgregar.setOnClickListener {
            try {
                val openn = InputStreamReader(requireActivity().openFileInput("animales.txt"))
            } catch (e: Exception) {
                val archivo = OutputStreamWriter(requireActivity().openFileOutput("animales.txt", 0))
                archivo.flush()
                archivo.close()
            }
            try {
                val openn = InputStreamReader(requireActivity().openFileInput("animales.txt"))
                var datos = openn.readLines()

                for (i in 0..datos.size - 1) {
                    var parts = datos[i].split("|")
                    Archivos.nombres[i] = parts[0]
                    Archivos.duenos[i] = parts[1]
                    Archivos.razas[i] = parts[2]
                }
                val archivo = OutputStreamWriter(requireActivity().openFileOutput("animales.txt", 0))
                for (i in 0..datos.size - 1) {
                    if (i < datos.size - 1) {
                        archivo.write(Archivos.nombres[i] + "|" + Archivos.duenos[i] + "|" + Archivos.razas[i] + "\n")
                    } else {
                        archivo.write(Archivos.nombres[i+1] + "|" + Archivos.duenos[+1] + "|" + Archivos.razas[i+1] + "\n")
                    }
                }
                Archivos.nombres[datos.size] = binding.eNombre.text.toString()
                Archivos.duenos[datos.size] = binding.eDueno.text.toString()
                Archivos.razas[datos.size] = binding.eRaza.text.toString()
                archivo.flush()
                archivo.close()
                binding.eNombre.setText("")
                binding.eDueno.setText("")
                binding.eRaza.setText("")
                AlertDialog.Builder(requireContext())
                    .setMessage("Se añadió un animal al sistema")
                    .show()
            } catch (e: Exception) {
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message)
                    .show()
            }

            val textView: TextView = binding.textGallery
            galleryViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }


        }
        return root



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}