package merchant.com.our.nextlounge.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.view.*

import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.adapter.OrderAdapter
import merchant.com.our.nextlounge.adapter.OrderTypeAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrderTypeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OrderTypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OrderTypeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView: RecyclerView ?= null
    private var textTitle : TextView ?= null
  //  private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_order_type, container, false)
        textTitle = v.findViewById(R.id.textTitle)
        recyclerView = v.findViewById(R.id.recyclerView)

        if (param1 =="Pending"){
            textTitle!!.text = getString(R.string.pending_order)
        }
        else if (param1 =="Active"){
            textTitle!!.text = getString(R.string.active_order)
        }
        val adapter = OrderTypeAdapter(null, this.activity!!)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.requestFocus()
        v.findViewById<ImageButton>(R.id.buttonBack).setOnClickListener {
            activity!!.onBackPressed()
        }
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderTypeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String?) =
                OrderTypeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
