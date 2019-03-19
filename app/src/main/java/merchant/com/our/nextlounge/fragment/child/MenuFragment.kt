package merchant.com.our.nextlounge.fragment.child

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.adapter.DrinkAdapter
import merchant.com.our.nextlounge.adapter.FoodAdapter
import merchant.com.our.nextlounge.helper.AppUtils
import merchant.com.our.nextlounge.helper.Const
import merchant.com.our.nextlounge.helper.HttpUtility
import merchant.com.our.nextlounge.model.DrinkModel
import merchant.com.our.nextlounge.model.FoodModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ORDER_TYPE = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MenuFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var orderType: String? = null
    private var param2: String? = null
    private var recyclerView : RecyclerView ?= null
    //  private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderType = it.getString(ORDER_TYPE)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_menu, container, false)

        recyclerView = v.findViewById(R.id.recyclerView)

        MenuAsync().execute()
        return v
    }


    @SuppressLint("StaticFieldLeak")
    inner class MenuAsync : AsyncTask<Void, Int, String>(){

        override fun doInBackground(vararg p0: Void?): String? {
            var url: String?= null
            if (orderType =="Foods") {
               url = Const.NEXT_URL + "menus"
            }
            else if(orderType =="Drinks"){
                url = Const.NEXT_URL + "drinks"
            }
            val token = AppUtils.getMyToken(activity)
            return HttpUtility.getRequest(url, token)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null){
                val gson = Gson()
                if (orderType =="Foods") {
                    val type = object : TypeToken<FoodModel>() {}.type
                    val menuModel = gson.fromJson<FoodModel>(result, type)
                    if (menuModel != null) {
                        if (menuModel.status == "Successful") {
                            val adapter = FoodAdapter(menuModel.menu, activity!!)
                            recyclerView!!.layoutManager = LinearLayoutManager(activity)
                            recyclerView!!.adapter = adapter
                            recyclerView!!.setHasFixedSize(true)
                            recyclerView!!.requestFocus()
                        } else {
                        }
                    }

                }else if (orderType =="Drinks"){
                    val type = object : TypeToken<DrinkModel>() {}.type
                    val drinkModel = gson.fromJson<DrinkModel>(result, type)
                    if (drinkModel != null) {
                        if (drinkModel.status == "Successful") {
                            val adapter = DrinkAdapter(drinkModel.drinks, activity!!)
                            recyclerView!!.layoutManager = LinearLayoutManager(activity)
                            recyclerView!!.adapter = adapter
                            recyclerView!!.setHasFixedSize(true)
                            recyclerView!!.requestFocus()
                        } else {
                        }
                    }
                }
            }
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param orderType Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(orderType: String, param2: String?) =
                MenuFragment().apply {
                    arguments = Bundle().apply {
                        putString(ORDER_TYPE, orderType)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
