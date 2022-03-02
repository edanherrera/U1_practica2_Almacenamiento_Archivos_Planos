package mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.CustomAdapter
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.R
import mx.tecnm.tepic.u1_prctica2_almacenamiento_archivosplanos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root
      val recyclerView = binding.recyclerView.findViewById<RecyclerView>(R.id.recyclerView)
      val adapter = CustomAdapter()

      recyclerView.layoutManager = LinearLayoutManager(requireContext())
      recyclerView.adapter = adapter


    val textView: TextView = binding.textView
    homeViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}