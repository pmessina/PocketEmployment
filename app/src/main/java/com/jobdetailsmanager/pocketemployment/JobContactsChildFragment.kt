package com.jobdetailsmanager.pocketemployment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.PagerTitleStrip
import android.support.v4.view.ViewPager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class JobContactsChildFragment : Fragment()
{

    //@BindView(R.id.child_job_market_details_menu)
    internal var viewPager: ViewPager? = null

    //@BindView(R.id.pagerTitleStrip)
    internal var pagerTitleStrip: PagerTitleStrip? = null

    internal var fragmentPagerAdapter: PagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.fragment_job_market_details_child, container, false)

        val pageTitle = arrayOf("Add", "Modify")

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
                    val jobContactDetailsFragment = JobContactDetailsFragment()
                    jobContactDetailsFragment.arguments = arguments
                    return jobContactDetailsFragment
                }

                return RecruitersFragment()
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any)
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments

        if (fragmentPagerAdapter != null)
        {
            if (args != null)
            {
                val recruiterUpdated = args.getString("JobContactDetailsFragment")

                if (recruiterUpdated != null)
                {
                    viewPager!!.currentItem = 2
                    fragmentPagerAdapter!!.notifyDataSetChanged()
                }
            }
        }

    }
}// Required empty public constructor
