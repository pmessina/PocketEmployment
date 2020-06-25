/*
 * Copyright (c) 2016. Patrick Messina
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jobdetailsmanager.pocketemployment.recruiters


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.jobdetailsmanager.pocketemployment.DialogManager
import com.jobdetailsmanager.pocketemployment.R
import com.jobdetailsmanager.pocketemployment.ViewRecruitingCompaniesStickyListAdapter
import kotlinx.android.synthetic.main.activity_recruiter_calls.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import se.emilsjolander.stickylistheaders.StickyListHeadersListView
import java.util.*


class RecruitersFragment : Fragment(), KoinComponent {

    internal var lastExpandedPosition = -1

    //internal var expListAdapter: RecruiterCallsExpListAdapter<*>? = null

    //internal var queryDatesFromContacts: List<Contact>? = null

    private var isRecruiter: Boolean = false

    private var dialogManager: DialogManager? = null

    internal var intent: Intent? = null

    private val recruiterCallService: RecruiterCallService? = null

    private val broadcaster: LocalBroadcastManager? = null

    val viewModel: RecruiterCallsViewModel by inject()

//    val database: PocketEmploymentDatabase by inject()

    //internal var contactArrayAdapter: ArrayAdapter<Contact>? = null

    //    @BindView(R.id.expListView)
    //    ExpandableListView expListView;

    //    @BindView(R.id.rvViewContacts)
    //    RecyclerView rvViewContacts;

    //@BindView(R.id.stickyListViewContacts)
    internal var stickyListHeadersListView: StickyListHeadersListView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_recruiter_calls, container, false)

//        val args = this.arguments
//        if (args != null) {
//            val drawerIndex = args.getInt("drawer_position")
//            val drawerSelection = resources.getStringArray(R.array.drawer_items)[drawerIndex]
//            activity!!.title = drawerSelection
//        }

        this.setHasOptionsMenu(true)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Intent intent = new Intent(getActivity(), RecruiterCallService.class);

        //getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);

        //        RecruiterDao recruiterDao = greenDaoHelper.initSession().getRecruiterDao();
        //val contactDao = greenDaoHelper.initSession().contactDao

        //QueryBuilder<Contact, Integer> queryBuilder = contactDao.queryBuilder();

        //        List<Recruiter> queryFromRecruiters = recruiterDao.loadAll();

        //val queryFromContacts = contactDao.loadAll()

        //        queryFromContacts.addAll(queryFromRecruiters);

        val recruiterContactKeys = ArrayList<String>()


        //        ArrayMap<String, List<?>> contactsArrayMap = new ArrayMap<>();
        //
        //        for (Recruiter recruiter : queryFromRecruiters)
        //        {
        //            recruiterContactKeys.add(recruiter.getPhoneNumber());
        //
        //            contactsArrayMap.put(recruiter.getPhoneNumber(), queryFromRecruiters);
        //
        //        }
        //
        //        for (Contact contact : queryFromContacts)
        //        {
        //            recruiterContactKeys.add(contact.getContactPhoneNumber());
        //
        //            contactsArrayMap.put(contact.getContactPhoneNumber(), queryFromContacts);
        //
        //        }
        //


        //        rvViewContacts.setHasFixedSize(true);

        //.observe(getApplication(), Observer { observer ->  })

//        viewModel.getPhoneNumbers().observe(viewLifecycleOwner, androidx.lifecycle.Observer { t ->
//
//            val stickyListHeadersAdapter = ViewRecruitersStickyListAdapter(requireContext(), t)
//
//
//            stickyListViewContacts.adapter = stickyListHeadersAdapter
//
//        })

//        database.addCallback(object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//
//                WorkManager.getInstance(requireContext()).enqueue(request)
//            }
//
//        })

//        Executors.newSingleThreadExecutor().execute {
//            Room.databaseBuilder(requireContext(), PocketEmploymentDatabase::class.java, "employment_db")
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//
//                            Toast.makeText(requireContext(), "Database populated", Toast.LENGTH_LONG).show()
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance(requireContext()).enqueue(request)
//                        }
//
//                    })
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//        }


        //viewModel.addRecruiterCompanies()


        viewModel.getRecruiterCompanies().observe(viewLifecycleOwner, Observer { t ->
            Toast.makeText(this.requireContext(), "${t.size}", Toast.LENGTH_LONG).show()
            val stickListHeadersAdapter = ViewRecruitingCompaniesStickyListAdapter(requireContext(), t)
            stickyListViewContacts.adapter = stickListHeadersAdapter
        })


//
//        viewModel.getRecruiters().observe(viewLifecycleOwner, Observer{i->
//            val groupedRecruiters = i.groupBy { it.recruiterCompanyId }
//
//            groupedRecruiters.forEach{
//                Log.i("groupedRecruiters", "$it.key $it.value")
//            }
//
//
//        })


        //viewModel.getRecruitersByRecruitingCompany(viewLifecycleOwner)


        //        expListAdapter = new RecruiterCallsExpListAdapter<>(getActivity(), recruiterContactKeys, recruiterContactDates);
        //
        //        expListView.setAdapter(expListAdapter);

        //        MyRecyclerAdapter<Contact> myRecyclerAdapter = new MyRecyclerAdapter(queryFromContacts);
        //
        //        EasyHeaderFooterAdapter easyHeaderFooterAdapter = new EasyHeaderFooterAdapter(myRecyclerAdapter);
        //
        //        TextView tvHeaderView = new TextView(getActivity());
        //        tvHeaderView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //        tvHeaderView.setText("Header Text");
        //
        //        TextView tvFooterView = new TextView(getActivity());
        //        tvFooterView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //        tvFooterView.setText("Footer Text");
        //
        //        easyHeaderFooterAdapter.setHeader(tvHeaderView);
        //        easyHeaderFooterAdapter.setFooter(tvFooterView);
        //
        //        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());
        //        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        //        rvViewContacts.setLayoutManager(llManager);
        //
        //        rvViewContacts.setAdapter(easyHeaderFooterAdapter);
        //rvViewContacts.setAdapter(easyHeaderFooterAdapter);

        //View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.recruiter_contact_info)
        //easyHeaderFooterAdapter.setHeader();

        //dialogManager = DialogManager(activity!!)

    }


//    private inner class MyRecyclerAdapter<T : Contact>(private val contacts: List<T>) : RecyclerView.Adapter<ContactsViewHolder>()
//    {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder
//        {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.recruiter_contact_info, parent, false)
//
//            return ContactsViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ContactsViewHolder, position: Int)
//        {
//            val curContact = contacts[position]
//
//            Toast.makeText(activity, curContact.toString(), Toast.LENGTH_LONG).show()
//
//            holder.callDate.text = curContact.dateCallReceived
//            holder.callName.text = curContact.contactFirstName
//            holder.callPhoneNumber.text = curContact.contactPhoneNumber
//
//        }
//
//        override fun getItemCount(): Int
//        {
//            return contacts.size
//        }
//    }

    inner class ContactsViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val callName: TextView
        val callPhoneNumber: TextView
        val callDate: TextView

        init {
            callName = rootView.findViewById<View>(R.id.tvName) as TextView
            callPhoneNumber = rootView.findViewById<View>(R.id.tvPhoneNumber) as TextView
            callDate = rootView.findViewById<View>(R.id.tvTime) as TextView
        }
    }


    override fun onResume() {
        super.onResume()

        //populateExpandableListView();
    }

    fun populateExpandableListView() {
        //getActivity().registerForContextMenu(expListView);

        try {
            //DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);

            //val contactDao = greenDaoHelper.initSession().contactDao

            //QueryBuilder<Contact, Integer> queryBuilder = contactDao.queryBuilder();

            //queryDatesFromContacts = contactDao.loadAll() //contactDao.query(queryBuilder.prepare());

            //val recruiterContactDates = ArrayMap<String, List<Contact>>()

//            val dateTimes = TreeSet(ContactsComparator.invoke())
//
//            for (c in queryDatesFromContacts!!) {
//                dateTimes.add(c)
//            }

            //val queryDate = contactDao.queryBuilder()

//            for (date in dateTimes) {
//                val formattedDate = date.dateCallReceived
//
//                //FIXME: fix algorithm for sorting contacts by dates
//                val wc = ContactDao.Properties.DateCallReceived.like("%$formattedDate%")
//
//                val contactsList = queryDate.where(wc).build().list()
//
//                //Collections.sort(contactsList, Contact())
//
//                recruiterContactDates[formattedDate] = contactsList
//            }

//            val recruiterContactKeys = ArrayList<String>()
//
//            for (o in recruiterContactDates.keys.toTypedArray()) {
//                recruiterContactKeys.add(o.toString())
//            }
//
//            recruiterContactKeys.sort()

            //            expListAdapter = new RecruiterCallsExpListAdapter(getActivity(), recruiterContactKeys, recruiterContactDates);
            //
            //            expListView.setAdapter(expListAdapter);
            //            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
            //            {
            //                @Override
            //                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            //                {
            //                    GreenDaoHelper helper = new GreenDaoHelper(getActivity());
            //                    ContactDao contactDao = helper.initSession().getContactDao();
            //                    Query<Contact> queryContact = contactDao.queryBuilder().where(ContactDao.Properties.RecruiterPhoneNumber.eq(contact.getContactId())).build();
            //
            //
            //                    if (queryContact.list().isEmpty())
            //                    {
            //                        Contact contact = (Contact) expListAdapter.getChild(groupPosition, childPosition);
            //                    }

            //                    FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
            //                    fragmentManager.replace(R.id.drawer_layout_container, new JobContactDetailsFragment());
            //                    fragmentManager.addToBackStack(null);
            //                    fragmentManager.commit();
            //                    Intent intent = new Intent(getActivity(), RecruiterCallsDetailsActivity.class);
            //                    intent.putExtra("Contact", contact);
            //                    startActivity(intent);
            //
            //                    return true;
            //                }
            //            });
            //            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
            //            {
            //            @Override
            //            public void onGroupExpand(int groupPosition)
            //            {
            //                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
            //                {
            //                    expListView.collapseGroup(lastExpandedPosition);
            //                }
            //
            //                lastExpandedPosition = groupPosition;
            //            }
            //        });

            //greenDaoHelper.closeSession()

            //this.registerForContextMenu(expListView);
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }

    }


    //    @Override
    //    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    //    {
    //        inflater.inflate(R.menu.action_bar_main, menu);
    //        super.onCreateOptionsMenu(menu, inflater);
    //    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu)
    //    {
    //        // Inflate the menu; this adds items to the action bar if it is present.
    //        getActivity().getMenuInflater().inflate(R.menu.action_bar_main, menu);
    //
    //        return true;
    //    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.delete_contacts -> {
//                greenDaoHelper.initSession().contactDao.deleteAll()
//                greenDaoHelper.closeSession()
                //TableUtils.clearTable(helper.getConnectionSource(), Contact.class);
                Toast.makeText(activity, "Delete Succeeded", Toast.LENGTH_LONG).show()
//                if (contactArrayAdapter != null) {
//                    //contactArrayAdapter.clear();
//                    contactArrayAdapter!!.notifyDataSetChanged()
//                }
            }
            R.id.add_contacts -> {
                if (item.itemId == R.id.add_contacts) {
//                    val contactDao = greenDaoHelper.initSession().contactDao
////
////                    for (cr in RecruiterCallsLogActivity.callRows!!) {
////                        val contact = Contact()
////                        contact.dateCallReceived = cr.callDate!!.toDateTime().toString()
////                        contact.contactPhoneNumber = cr.phoneNumber
////                        contactDao.insert(contact)
////                        if (contactArrayAdapter != null) {
////                            contactArrayAdapter!!.notifyDataSetChanged()
////                        }
////
////                    }

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val menuInflater = activity?.menuInflater
        menuInflater?.inflate(R.menu.menu_recruiter_call_information, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val listContextMenuInfo = item.menuInfo as ExpandableListView.ExpandableListContextMenuInfo

        val groupPosition = ExpandableListView.getPackedPositionGroup(listContextMenuInfo.packedPosition)
        val childPosition = ExpandableListView.getPackedPositionChild(listContextMenuInfo.packedPosition)

        //val contact = expListAdapter!!.getChild(groupPosition, childPosition) as Contact


//        val dialog = AlertDialog.Builder(activity).setTitle("Recruiter?").setMessage("Are you sure you want to delete " + contact.formattedRecruiterPhoneNumber + "?").setPositiveButton("Yes") { dialog, which ->
//            val greenDaoHelper = GreenDaoHelper(activity)
//            val deleteDao = greenDaoHelper.initSession().contactDao
//            deleteDao.delete(contact)
//            expListAdapter!!.deleteChild(groupPosition, childPosition)
//            expListAdapter!!.notifyDataSetChanged()
//
//            Toast.makeText(activity, "The phone number " + contact.formattedRecruiterPhoneNumber + " was deleted", Toast.LENGTH_LONG).show()
//        }.setNegativeButton("No") { dialog, which -> isRecruiter = false }
//        val ad = dialog.create()
//        ad.show()

        return super.onContextItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        //getActivity().unregisterForContextMenu(expListView);
    }

    override fun onDestroy() {
        super.onDestroy()
        //        getActivity().unbindService(this);
    }

    companion object {

        private val NOTIF_ID = 1
    }


    //TODO: refactor to search contacts db
    //    public static String findRecruiterCompanyFromIncomingNumber(Context context, String incomingNumber)
    //    {
    //        List<Recruiter> recruiters = new ArrayList<>();
    //
    //        try
    //        {
    //            GreenDaoHelper greenDaoHelper = new GreenDaoHelper(context);//new DaoMaster.DevOpenHelper(RecruiterCallService.this, "recruiters-db");
    //
    //            RecruiterDao contactDao = greenDaoHelper.initSession().getRecruiterDao();
    //
    //            QueryBuilder<Recruiter> queryContact = contactDao.queryBuilder().where(RecruiterDao.Properties.PhoneNumber.eq(incomingNumber));
    //
    //            recruiters = queryContact.build().list();
    //
    //
    //
    //        }
    //        catch (Exception e)
    //        {
    //            e.printStackTrace();
    //            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
    //        }
    //
    //        return recruiters.get(0).getRecruiterCompany().getRecruiterCompanyName();
    //    }

}
