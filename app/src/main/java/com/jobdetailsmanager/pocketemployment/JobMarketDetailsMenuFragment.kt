package com.jobdetailsmanager.pocketemployment


import android.os.Bundle
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class JobMarketDetailsMenuFragment : Fragment()
{

    //    @BindView(R.id.pagerTitleStrip)
    //    PagerTitleStrip pagerTitleStrip;

    //@BindView(R.id.job_market_details_menu)
    internal var viewPager: ViewPager? = null

    //@BindView(R.id.sliding_tabs)
    internal var tabLayout: TabLayout? = null

    lateinit var fragmentPagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.fragment_job_market_details_menu, container, false)


        val pageTitle = arrayOf("Job Contacts", "Job Positions", "Companies", "Job Interviews")


        //val typedArray = resources.obtainTypedArray(R.array.viewpager_items)


        val args = arguments

        if (args != null)
        {
            val viewPagerState = args.getString("data")

            if (viewPagerState != null)
            {
                if (viewPagerState == "isFromViewPager")
                {
                    viewPager!!.currentItem = 1
                }
            }


        }

        fragmentPagerAdapter = object : FragmentStatePagerAdapter(childFragmentManager)
        {
            override fun getCount(): Int
            {
                return pageTitle.size
            }


            override fun getItem(position: Int): Fragment
            {
                if (position == 0)
                {
                    val jobContactsChildFragment = JobContactsChildFragment()
                    val args = arguments

                    jobContactsChildFragment.arguments = args

                    return jobContactsChildFragment
                }
                if (position == 1)
                {
                    val jpf = JobPositionsFragment()
                    val b = arguments
                    jpf.arguments = b
                    return jpf
                }

                return JobMarketDetailsChildFragment()
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

        tabLayout!!.setupWithViewPager(viewPager)


        val textViewTitle = tabLayout!!.getTabAt(0)
        val tabItem = textViewTitle!!.tag as TabItem?


        //        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "arimamadurai_bold.ttf");
        //
        //        textViewTitle.setTypeface(face);


        return view
    }


}// Required empty public constructor
