package com.jobdetailsmanager.pocketemployment

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_pocket_employment.*
import kotlinx.android.synthetic.main.activity_pocket_employment_item_layout.view.*


/**
 * The future of your employment is in the size of your pocket; take control of your job process
 */
class PocketEmploymentActivity : AppCompatActivity() {


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

    override fun onCreate(savedInstanceState: Bundle?)
    {
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




    }

    class PositionLocationAdapter(private val context: Context) : RecyclerView.Adapter<PositionLocationViewHolder>()
    {
        var jobs: List<Jobs> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionLocationViewHolder
        {
            val layoutInflator = LayoutInflater.from(context)

            val view = layoutInflator.inflate(R.layout.activity_pocket_employment_item_layout, parent, false)

            return PositionLocationViewHolder(view)
        }

        override fun onBindViewHolder(holder: PositionLocationViewHolder, position: Int) {

            holder.itemView.tvPosition?.text = jobs[position].position
            holder.itemView.tvLocation?.text = jobs[position].location
        }

        override fun getItemCount(): Int {
            return jobs.size
        }

    }

    class PositionLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

open class Jobs(var position:String? = null, var location:String? = null)
{
    //@PrimaryKey
    var id:Long = 0

}

//@Component(modules=[MyModule::class])
//interface ParentComponent
//{
//    fun inject(target: MyApplication)
//
//    //fun injectMySubComponent() : ChildComponent.Builder//Subcomponent.Builder
//
//    @Component.Builder
//    interface Builder
//    {
//        @BindsInstance fun myContext(context: Context): Builder
//        fun build(): ParentComponent
//
//    }
//}

//@Subcomponent(modules = [MyModule::class])
//interface ChildComponent
//{
//    @Subcomponent.Builder
//    interface Builder
//    {
//        @BindsInstance
//        fun myContext(context: Context): Builder
//        fun build(): ChildComponent
//
//    }
//}

//class MyApplication : Application()
//{
//
//    @Inject @SpecificString
//    lateinit var myString: String
//
//    @Inject @AnotherSpecificString
//    lateinit var myAnotherString: String
//
////    val component : ParentComponent by lazy{
////
////        //.MyContext(applicationContext)
////
////
////        DaggerParentComponent.builder().build()
////    }
//
//    override fun onCreate()
//    {
//        super.onCreate()
//        //component.inject(this)
//    }
//}
//
//@Module //(includes = [AnotherModule::class], subcomponents = [ChildComponent::class])
//class MyModule
//{
//    @Provides
//    fun provideString(): String = "Some Provided String"
//}
//
//@Module
//class AnotherModule
//{
//    @Provides
//    fun provideAnotherString(): String = "Another String"
//}
//
//@Qualifier
//@Retention(AnnotationRetention.RUNTIME)
//annotation class SpecificString
//
//@Qualifier
//@Retention(AnnotationRetention.RUNTIME)
//annotation class AnotherSpecificString


