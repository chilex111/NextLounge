package merchant.com.our.nextlounge.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.db.LocalDatabase
import merchant.com.our.nextlounge.fragment.child.MenuFragment
import merchant.com.our.nextlounge.listener.MenuListener
import merchant.com.our.nextlounge.model.OrderModel
import merchant.com.our.nextlounge.model.PriceModel
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TABLE_NO = "param1"
private const val ARG_PARAM2 = "param2"

class NewOrderFragment : Fragment(),MenuListener {


    // TODO: Rename and change types of parameters
    private var table_No: String? = null
    private var param2: String? = null
    //   private var listener: OnFragmentInteractionListener? = null
    private var recyclerView: RecyclerView?= null
    private var viewPager: ViewPager ?= null
    private var tabLayout  : TabLayout ?= null
    private var localDB : LocalDatabase ?= null
    private var frameCount : FrameLayout ?= null
    private var textCount : TextView ?= null
    private var textAmount : TextView ?= null
    private var currentTab = 0
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            table_No = it.getString(TABLE_NO)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v =inflater.inflate(R.layout.fragment_new_order, container, false)
        merchant.com.our.nextlounge.adapter.MenuAdapter.listener = this

        localDB = LocalDatabase(activity!!)

        tabLayout = v.findViewById(R.id.tabLayout)
        viewPager = v.findViewById(R.id.viewPager)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        tabLayout!!.setupWithViewPager(viewPager)
        viewPager!!.adapter = mSectionsPagerAdapter
        tabLayout!!.getTabAt(0)!!.text = "Foods"
        tabLayout!!.getTabAt(1)!!.text = "Drinks"

        //tabLayout.getTabAt(0).setIcon(R.drawable.circle_blue);
        viewPager!!.setCurrentItem(currentTab, true)
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        // Set up the ViewPager with the sections adapter.

        frameCount = v.findViewById(R.id.frameCount)
        textCount = v.findViewById(R.id.textBasketNo)
        textAmount = v.findViewById(R.id.textTotalAmount)

        if (localDB!!.orderCount() == 0){
            frameCount!!.visibility = View.GONE
            textAmount!!.text = "0.00"
        }else{
            frameCount!!.visibility = View.VISIBLE
            textCount!!.text = localDB!!.orderCount().toString()

            val priceList = localDB!!.allPrice
            val totalPrice = getTotal(priceList)

            val decimalFormat = DecimalFormat("#,###.00")
            val am = decimalFormat.format(java.lang.Double.parseDouble(totalPrice.toString()))
            textAmount!!.text = am.toString()
        }

        v.findViewById<ImageButton>(R.id.buttonBack).setOnClickListener {
            activity!!.onBackPressed()
        }

        textCount!!.setOnClickListener {
            childFragmentManager.beginTransaction()
                    .add(R.id.container, ProcessOrderFragment.newInstance(table_No,null))
                    .commit()
        }
        return v
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(MenuFragment(), "Foods")
        adapter.addFragment(MenuFragment(), "Drinks")
        viewPager.adapter = adapter
    }

    override fun menuListener(menuOrder: List<OrderModel>?, msg: String) {
        if(menuOrder != null){
            localDB!!.insertOrder(menuOrder)
            val newValue = localDB!!.orderCount()
            textCount!!.text = newValue.toString()
            frameCount!!.visibility = View.VISIBLE
            val priceList = localDB!!.allPrice
            val totalPrice = getTotal(priceList)

            val decimalFormat = DecimalFormat("#,###.00")
            val am = decimalFormat.format(java.lang.Double.parseDouble(totalPrice.toString()))
            textAmount!!.text = am.toString()
        }
        else{
            Toast.makeText(activity!!,msg, Toast.LENGTH_LONG).show()
        }
    }

    fun  getTotal(models: List<PriceModel>): Double {
        val listPrice: MutableList<Int> = ArrayList()
        for (valuePrice in models) {
            listPrice.add(valuePrice.amount!!)
        }
        var sum = 0.0

        for (i in listPrice.indices)
            sum += listPrice[i].toDouble()
        return sum
    }

    inner class SectionsPagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            // getItem is called to newInstance the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return when (position) {
                0 -> {
                    currentTab = 0
                    MenuFragment()
                    MenuFragment.newInstance("Foods",null)
                }
                1 -> {
                    currentTab = 1
                    MenuFragment.newInstance("Drinks",null)
                }
                else -> null
            }
            // return PlaceholderFragment.newInstance(position + 1);
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }

    }
     inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

/*

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
*/

    /*  interface OnFragmentInteractionListener {
          // TODO: Update argument type and name
          fun onFragmentInteraction(uri: Uri)
      }
  */
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param tableNo Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(tableNo: String, param2: String?) =
                NewOrderFragment().apply {
                    arguments = Bundle().apply {
                        putString(TABLE_NO, tableNo)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
