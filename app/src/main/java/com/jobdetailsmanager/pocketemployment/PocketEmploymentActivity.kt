package com.jobdetailsmanager.pocketemployment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_pocket_employment.*
import kotlinx.android.synthetic.main.activity_pocket_employment_item_layout.view.*
import kotlin.properties.Delegates


/**
 * The future of your employment in the palm of your hands; take control of your job process
 */
class PocketEmploymentActivity : AppCompatActivity() {


    private var realm : Realm by Delegates.notNull()


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pocket_employment)

        setSupportActionBar(toolbar)

        val adapter = PositionLocationAdapter(this)

        rvPositionLocation.layoutManager = LinearLayoutManager(this)

//        val params = ActionBar.LayoutParams(//Center the textview in the ActionBar !
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER)
//
//        val viewActionBar = layoutInflater.inflate(R.layout.toolbar_title, null)
//
//        val tvToolbarTitle = this.findViewById<TextView>(R.id.tvToolbarTitle)
//
//        tvToolbarTitle.text = "Jobs On My Radar"
//
//        supportActionBar?.setCustomView(viewActionBar, params)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        val realm = RealmConfig(this)
//
//
//        val instance = realm.setUpRealm()
//
//        instance.beginTransaction()
        //val contact = instance.createObject(Contact::class.java)
        //contact.firstName = "John"
        //contact.lastName = "Doe"

        //instance.commitTransaction()


        //val binder = ViewBinder.Binder(ItemViewModel(), )
        realm = Realm.getDefaultInstance()


        realm.executeTransaction {
            realm.createObject<Jobs>(Jobs("Position 1", "Location 1"))
            realm.createObject<Jobs>(Jobs("Position 2", "Location 2"))
            realm.createObject<Jobs>(Jobs("Position 3", "Location 3"))
            realm.createObject<Jobs>(Jobs("Position 4", "Location 4"))
            realm.createObject<Jobs>(Jobs("Position 5", "Location 5"))
            realm.createObject<Jobs>(Jobs("Position 6", "Location 6"))

        }

        realm.executeTransaction {
            adapter.jobs = realm.where(Jobs::class.java).findAll()
            rvPositionLocation.adapter = adapter

        }


    }

    class PositionLocationAdapter(private val context: Context) : RecyclerView.Adapter<PositionLocationViewHolder>()
    {
        var jobs: List<Jobs> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PositionLocationViewHolder
        {
            val layoutInflator = LayoutInflater.from(context)

            val view = layoutInflator.inflate(R.layout.activity_pocket_employment_item_layout, parent, false)

            return PositionLocationViewHolder(view)
        }

        override fun onBindViewHolder(holder: PositionLocationViewHolder?, position: Int) {

            holder?.itemView?.tvPosition?.text = jobs[position].position
            holder?.itemView?.tvLocation?.text = jobs[position].location
        }

        override fun getItemCount(): Int {
            return jobs.size
        }

    }

    class PositionLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

//@RealmClass
//open class Contact(var firstName:String? = null, var lastName:String? = null) : RealmObject()
//{
//    @PrimaryKey
//    var id:Long = 0
//
//}

@RealmClass
open class Jobs(var position:String? = null, var location:String? = null) : RealmObject()
{
    @PrimaryKey
    var id:Long = 0

}


