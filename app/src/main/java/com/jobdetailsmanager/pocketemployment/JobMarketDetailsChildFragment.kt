package com.jobdetailsmanager.pocketemployment


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerTitleStrip
import androidx.viewpager.widget.ViewPager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class JobMarketDetailsChildFragment : Fragment()
{

    //@BindView(R.id.child_job_market_details_menu)
    internal var viewPager: ViewPager? = null

    //@BindView(R.id.pagerTitleStrip)
    internal var pagerTitleStrip: PagerTitleStrip? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_job_market_details_child, container, false)

        //ButterKnife.bind(this, view)

        val pageTitle = arrayOf("Recruiters", "HR", "Account Managers")

        val fragmentPagerAdapter = object : FragmentStatePagerAdapter(fragmentManager!!)
        {
            override fun getCount(): Int
            {
                return pageTitle.size
            }

            override fun getItem(position: Int): Fragment
            {
                return TodoListFragment()
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
            {
                super.destroyItem(container, position, `object`)
            }

            override fun getPageTitle(position: Int): CharSequence
            {
                return pageTitle[position]
            }
        }

        viewPager!!.adapter = fragmentPagerAdapter

        pagerTitleStrip!!.setTextColor(Color.BLUE)
        pagerTitleStrip!!.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)

        return view
    }

}// Required empty public constructor
