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

package com.jobdetailsmanager.pocketemployment


import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*



/**
 * A simple [Fragment] subclass.
 */
@TargetApi(value = 23)
class JobContactDetailsFragment : Fragment()//extends DialogFragment implements RecruiterPhoneNumberFragment.AddPhoneNumberListener

{

    internal var fragmentManager: FragmentManager? = null
    internal var transaction: FragmentTransaction? = null

    //private var currentContact: Contact? = null

    //    @BindView(R.id.btnAccessGmail)
    //    Button btnAccessGmail;
    //@BindView(R.id.btnRecruiterCompLookup)
    internal var btnRecruiterCompLookup: Button? = null
    //@BindView(R.id.btnAddToContacts)
    internal var btnAddToContacts: Button? = null
    //@BindView(R.id.edtFirstName)
    internal var edtFirstName: EditText? = null
    //@BindView(R.id.edtLastName)
    internal var edtLastName: EditText? = null
    //@BindView(R.id.edtCompanyName)
    internal var edtCompanyName: EditText? = null
    //@BindView(R.id.edtEmailAddress)
    internal var edtEmailAddress: EditText? = null
    //@BindView(R.id.spRecruiterCallState)
    internal var spRecruiterCallState: Spinner? = null
    //@BindView(R.id.spContactType)
    internal var spContactType: Spinner? = null
    //@BindView(R.id.edtPhoneNumber)
    internal var edtPhoneNumber: EditText? = null

    private var dialogManager: DialogManager? = null

    private var contactType: ContactType? = null

    lateinit var listener: OnAddContactListener

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        listener = context as OnAddContactListener

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.activity_recruiter_calls_details, container, false)

        val args = arguments

        //String str = args.getString("JobContactDetailsFragment");


        edtPhoneNumber!!.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        //currentContact = Contact()
        if (args != null)
        {
            val retrievePhoneNumber = args.getString("phoneNumber")

            if (retrievePhoneNumber != null)
            {
                //tvContactNumber.setText(retrievePhoneNumber);

                //currentContact!!.contactPhoneNumber = retrievePhoneNumber

            }
        }

        //        if (getDialog() != null)
        //        {
        //            view = inflater.inflate(R.layout.fragment_recruiter_details, container, false);
        //
        //            Button btnOk = (Button)view.findViewById(R.id.btnRecruiterDetailsOk);
        //            Button btnCancel = (Button)view.findViewById(R.id.btnRecruiterDetailsCancel);
        //
        //            btnOk.setOnClickListener(new View.OnClickListener()
        //            {
        //                @Override
        //                public void onClick(View v)
        //                {
        //                    getDialog().dismiss();
        //                }
        //            });
        //
        //            btnCancel.setOnClickListener(new View.OnClickListener()
        //            {
        //                @Override
        //                public void onClick(View v)
        //                {
        //                    getDialog().dismiss();
        //                }
        //            });
        //
        //        }
        //        else
        //        {
        //            view = inflater.inflate(R.layout.fragment_recruiter_details, container, false);
        //        }
        //        ImageButton imgMinus = (ImageButton)view.findViewById(R.id.imgbtnMinus);

        //        final ListView rlPhoneNumber = (ListView)view.findViewById(R.id.lvPhoneNumber);
        //
        //        final ArrayList<String> stringList = new ArrayList<>();

        //        final ArrayAdapter<String> phoneNumberAdapter = new ArrayAdapter<>(getActivity(), R.layout.lv_row_phonenumber, R.id.edtPhoneNumber);

        //        final ArrayAdapter phoneNumberAdapter = new PhoneNumberListAdapter(getActivity(), R.layout.lv_row_phonenumber);//, R.id.edtPhoneNumber, stringList);
        //
        //        rlPhoneNumber.setAdapter(phoneNumberAdapter);

        //getActivity().setContentView(R.layout.activity_recruiter_calls_details);


        //TODO: change to getArguments
        //        String incomingNumber = (String) this.getIntent().getSerializableExtra("phoneNumber");
        //
        //        currentContact.setContactPhoneNumber(incomingNumber);
        //
        //        tvContactNumber.setText(incomingNumber);


        //        GreenDaoHelper greenDaoHelper = new GreenDaoHelper(getActivity());
        //        RecruiterDao recruiterDao = greenDaoHelper.initSession().getRecruiterDao();
        //        QueryBuilder<Recruiter> queryContacts;
        //        List<Recruiter> contactList = new ArrayList<>();
        //
        //        if (incomingNumber != null)
        //        {
        //            queryContacts = recruiterDao.queryBuilder().where(RecruiterDao.Properties
        //                    .PhoneNumber.eq(incomingNumber));
        //            contactList = queryContacts.list();
        //        } else
        //        {
        //
        //            if (savedInstanceState != null)
        //            {
        //                currentContact = (Contact) savedInstanceState.getSerializable("Contact");
        //            } else
        //            {
        //                currentContact = (Contact) this.getIntent().getSerializableExtra("Contact");
        //            }
        //
        //            if (currentContact != null)
        //            {
        //                queryContacts = recruiterDao.queryBuilder().where(RecruiterDao.Properties
        //                        .PhoneNumber.eq(currentContact.getContactPhoneNumber()));
        //                contactList = queryContacts.list();
        //            }
        //        }
        //
        //        Toast.makeText(getActivity(), "Found " + contactList.size() + " contacts", Toast.LENGTH_SHORT)
        //                .show();
        //
        //        if (currentContact.getContactFirstName() != null)
        //        {
        //            edtFirstName.setText(currentContact.getContactFirstName());
        //        }
        //        if (currentContact.getContactLastName() != null)
        //        {
        //            edtLastName.setText(currentContact.getContactLastName());
        //        }
        //        if (currentContact.getRecruiterCompanyName() != null)
        //        {
        //            edtCompanyName.setText(currentContact.getRecruiterCompanyName());
        //        }
        //        if (currentContact.getRecruiterCompanyAddress() != null)
        //        {
        //            edtEmailAddress.setText(currentContact.getRecruiterCompanyAddress());
        //        }

        dialogManager = DialogManager(activity!!)

        val callStateArrayAdapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, RecruiterCallState.values())
        spRecruiterCallState!!.adapter = callStateArrayAdapter

        val contactTypeArrayAdapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, ContactType.values())
        spContactType!!.adapter = contactTypeArrayAdapter


        //spRecruiterCallState!!.setSelection(currentContact!!.contactCallState)

        spContactType!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {
                contactType = parent.getItemAtPosition(position) as ContactType

            }

            override fun onNothingSelected(parent: AdapterView<*>)
            {

            }
        }


        //        btnAccessGmail.setOnClickListener(new View.OnClickListener()
        //        {
        //            @Override
        //            public void onClick(View v)
        //            {
        /**
         * TODO: Query company address
         */

        //                String companyAddress = "my.email@gmail.com";
        //                if (currentContact != null)
        //                {
        //                    companyAddress = currentContact.getRecruiterCompanyAddress();
        //                }
        //                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        //                sendIntent.setType("plain/text");
        //                sendIntent.setData(Uri.parse("pmm0501@gmail.com"));
        //                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm
        // .ComposeActivityGmail");
        //                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{companyAddress});
        //                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
        //                sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my
        // demo app :-)");
        //                startActivity(sendIntent);
        //            }
        //        });


        //        btnAddToContactProvider.setOnClickListener(new View.OnClickListener()
        //        {
        //            @Override
        //            public void onClick(View v)
        //            {
        //TODO:Add to Contacts provider under group name Recruiter if exists, else create
        // new group

        /**
         * TODO: query recruiter phone number
         */

        //                Intent intent = new Intent(Intents.Insert.ACTION);
        //                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //                intent.putExtra(Intents.Insert.EMAIL, "pmm0501@gmail.com");
        //                intent.putExtra(Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds
        // .Email.TYPE_WORK);
        //                    // Inserts a phone number
        //                intent.putExtra(Intents.Insert.PHONE, currentContact.getContactPhoneNumber());
        //                // Sets the MIME type to match the Contacts Provider
        //                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        //                startActivity(intent);
        //            }
        //        });


        //        if (contact != null)
        //        {
        //

        btnRecruiterCompLookup!!.setOnClickListener {
            //val intent = searchGoogleUri(currentContact!!.contactPhoneNumber)
            //startActivity(intent)
            //                        if (edtCompanyName.getText().toString().isEmpty())
            //                        {
            //                            dialogManager.showOkDialog("Enter Company name", "Please enter
            // Company name");
            //                        } else
            //                        {
            //Search Internet for company name if filled out
            /**
             * TODO: query Company name
             */
            /**
             * TODO: query Company name
             */
            //                        final String compName = currentContact.getRecruiterCompanyName();
            //
            //                        if (compName != null)
            //                        {
            //                            Intent intent = searchGoogleUri(currentContact
            // .getRecruiterCompanyName());
            //                            getBaseContext().startActivity(intent);
            //                        }
            //                        }
        }
        //            }
        //        }

        //


        return view
    }


    //@OnClick(R.id.btnAddToContacts)
    fun onAddContactClick(v: View)
    {
        //TODO: Check if contact/recruiter exists before click or during click

        if (spContactType!!.selectedItem === ContactType.RECRUITER)
        {
//            val recruiterCompanyDao = greenDaoHelper.initSession().recruiterCompanyDao
//            val recruiterCompany = RecruiterCompany()
//            recruiterCompany.recruiterCompanyName = edtCompanyName!!.text.toString()
//
//            //If insert is not successful, -1 is returned
//            if (recruiterCompanyDao.insert(recruiterCompany) != -1L)
//            {
//                Toast.makeText(activity, "Recruiter Company Added Successfully", Toast.LENGTH_LONG).show()
//                listener.onAddContact("RecruiterCompanyUpdated")
//
//            }

            //val contactDao = greenDaoHelper.initSession().contactDao

//            val contact = Contact()
//            contact.contactPhoneNumber = edtPhoneNumber!!.text.toString()
//            contact.contactFirstName = edtFirstName!!.text.toString()
//            contact.contactLastName = edtLastName!!.text.toString()
//            contact.contactEmailAddress = edtEmailAddress!!.text.toString()
//            contact.contactCallState = RecruiterCallState.INITIAL_CALL.value

//            if (contactDao.insert(contact) != -1L)
//            {
//                Toast.makeText(activity, "Recruiter Added Successfully", Toast.LENGTH_LONG).show()
//                listener.onAddContact("RecruiterUpdated")
//                edtFirstName!!.text.clear()
//                edtLastName!!.text.clear()
//                edtCompanyName!!.text.clear()
//                edtEmailAddress!!.text.clear()
//                edtPhoneNumber!!.text.clear()
//            }
        }

        //greenDaoHelper.closeSession()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        //        Toolbar okCancelToolBar = (Toolbar)view.findViewById(R.id.fragToolbar);
        //        okCancelToolBar.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM));

        //        edtPhoneNumber = (EditText) view.findViewById(R.id.edtPhoneNumber);
        //        edtPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        //
        //        CharSequence charSequence;
        //        final SharedPreferences.Editor editor = sharedPreferences.edit();
        //        if (savedInstanceState != null)
        //        {
        //            charSequence = savedInstanceState.getCharSequence("phoneNumber");
        //            edtPhoneNumber.setText(charSequence);
        //        } else
        //        {
        //            edtPhoneNumber.addTextChangedListener(new TextWatcher()
        //            {
        //                @Override
        //                public void beforeTextChanged(CharSequence s, int start, int count, int after)
        //                {
        //
        //                }
        //
        //                @Override
        //                public void onTextChanged(CharSequence s, int start, int before, int count)
        //                {
        //                    //Toast.makeText(getActivity(), "Text Changed", Toast.LENGTH_SHORT).show();
        //
        //                    editor.putString("phoneNumber", s.toString());
        //                }
        //
        //                @Override
        //                public void afterTextChanged(Editable s)
        //                {
        //                    if (editor.commit())
        //                    {
        //                        //Toast.makeText(getActivity(), "Text Saved", Toast.LENGTH_SHORT).show();
        //                    }
        //                }
        //            });
        //
        //                    //savedInstanceState.putCharSequence("phoneNumber", phoneNumber);
        //
        //            }

        //        int numFragments = 0;
        //        String fragmentTag = "fragment";
        //        numFragments++;
        //        String newTag = fragmentTag + numFragments;
        //        FragmentManager fragmentManager = getFragmentManager();
        //        RecruiterPhoneNumberFragment phoneNumberFragment = new RecruiterPhoneNumberFragment();
        //        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //        transaction.add(R.id.child_fragment, phoneNumberFragment, newTag);
        //        transaction.addToBackStack(newTag);
        //        transaction.commit();
        //        if (fragmentManager.getBackStackEntryCount() == 1)
        //        {
        //            ImageButton imgMinus = (ImageButton)view.findViewById(R.id.imgMinus);
        //            imgMinus.setVisibility(View.GONE);
        //        }
    }

    //    @Override
    //    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    //    {
    //        super.onActivityCreated(savedInstanceState);
    //
    //        AddPhoneNumber(getChildFragmentManager());
    //
    //    }
    //
    //    @Override
    //    public void onSaveInstanceState(Bundle outState)
    //    {
    //        super.onSaveInstanceState(outState);
    //        if (outState != null)
    //        {
    //            String state = outState.getString("phoneNumber");
    //            //Toast.makeText(getActivity(), "Activity Saved Instance State " + state, Toast.LENGTH_SHORT).show();
    //        }
    //
    //        String phoneNumber = sharedPreferences.getString("phoneNumber", null);
    //        if (phoneNumber != null)
    //        {
    //            outState.putString(phoneNumber, "phoneNumber");
    //        }
    //
    //    }
    //
    ////    @Override
    ////    public void AddPhoneNumber()
    ////    {
    ////
    ////        fragmentManager = getChildFragmentManager();
    ////
    ////        View view = this.getParentFragment().getView().findViewById(R.id.child_fragment);
    ////        RecruiterPhoneNumberFragment phoneNumberFragment = new RecruiterPhoneNumberFragment();
    ////        FragmentTransaction transaction = fragmentManager.beginTransaction();
    ////        transaction.add(view.getId(), phoneNumberFragment, "Tag1");
    ////        transaction.addToBackStack("Tag1");
    ////        transaction.commit();
    ////    }
    //
    //    @Override
    //    public void AddPhoneNumber(FragmentManager fragmentManager)
    //    {
    //        //View view = this.getParentFragment().getView().findViewById(R.id.child_fragment);
    //        RecruiterPhoneNumberFragment phoneNumberFragment = new RecruiterPhoneNumberFragment();
    //        FragmentTransaction transaction = fragmentManager.beginTransaction();
    //        transaction.add(R.id.child_fragment, phoneNumberFragment, "Tag1");
    //        transaction.addToBackStack(null);
    //        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    //        transaction.commit();
    //
    //        Toast.makeText(getActivity(), fragmentManager.getBackStackEntryCount() + "", Toast.LENGTH_SHORT).show();
    //    }
    //
    //    @Override
    //    public void RemovePhoneNumber(Fragment fragment)
    //    {
    //        //fragmentManager = getSupportFragmentManager();
    //        FragmentTransaction transaction = fragmentManager.beginTransaction();
    //
    //        int backstackEntryCount = fragmentManager.getBackStackEntryCount();
    //
    //        if (backstackEntryCount > 1)
    //        {
    //            transaction.remove(fragment);
    //            fragmentManager.popBackStack();
    //            transaction.commit();
    //            Toast.makeText(getActivity(), fragmentManager.getBackStackEntryCount() + "", Toast.LENGTH_SHORT).show();
    //
    //        }
    //
    //    }
    //
    //    @NonNull
    //    @Override
    //    public Dialog onCreateDialog(Bundle savedInstanceState)
    //    {
    //        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
    //
    //        LayoutInflater viewInflater = getActivity().getLayoutInflater();
    //
    //        View view = viewInflater.inflate(R.layout.fragment_recruiter_details, null, false);
    //        //alertDialog.create().requestWindowFeature(Window.FEATURE_NO_TITLE);
    //
    //        Button btnOk = (Button)view.findViewById(R.id.btnRecruiterDetailsOk);
    //        Button btnCancel = (Button)view.findViewById(R.id.btnRecruiterDetailsCancel);
    //
    //        btnOk.setOnClickListener(new View.OnClickListener()
    //        {
    //            @Override
    //            public void onClick(View v)
    //            {
    //                Dialog dialog = getDialog();
    //                if (dialog != null)
    //                {
    //                    getDialog().dismiss();
    //                }
    //            }
    //        });
    //
    //        btnCancel.setOnClickListener(new View.OnClickListener()
    //        {
    //            @Override
    //            public void onClick(View v)
    //            {
    //                Dialog dialog = getDialog();
    //                if (dialog != null)
    //                {
    //                    getDialog().dismiss();
    //                }
    //            }
    //        });
    //
    //        alertDialog.setView(view);


    //        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    //        {
    //            @Override
    //            public void onClick(DialogInterface dialog, int which)
    //            {
    //                dialog.dismiss();
    //            }
    //        });
    //        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    //        {
    //            @Override
    //            public void onClick(DialogInterface dialog, int which)
    //            {
    //                dialog.dismiss();
    //            }
    //        });

    //        return alertDialog.create();
    //       //return super.onCreateDialog(savedInstanceState);
    //    }


    //    @Override
    //    protected void onRestoreInstanceState(Bundle savedInstanceState)
    //    {
    //        super.onRestoreInstanceState(savedInstanceState);
    //
    //    }

    //    @Override
    //    protected void onResume()
    //    {
    //        super.onResume();
    //
    //        //populateDetails();
    //    }

    //    public void populateDetails()
    //    {
    //        if (currentContact != null)
    //        {
    //            //final long contactId = currentContact.getContactId();
    //
    //            Button btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
    //
    //            if (currentContact != null)
    //            {
    //                TextView tv = (TextView) this.findViewById(R.id.tvPhoneNumber);
    //                tv.setText(currentContact.getFormattedRecruiterPhoneNumber());
    //
    //                edtFirstName.setText(currentContact.getContactFirstName());
    //                edtLastName.setText(currentContact.getContactLastName());
    ////                edtCompanyName.setText(currentContact.getRecruiterCompanyName());
    ////                edtEmailAddress.setText(currentContact.getRecruiterCompanyAddress());
    //
    //                spRecruiterCallState.setSelection(currentContact.getRecruiterCallState());
    //
    //                spRecruiterCallState.setOnItemSelectedListener(new AdapterView
    //                        .OnItemSelectedListener()
    //                {
    //                    @Override
    //                    public void onItemSelected(AdapterView<?> parent, View view, int position,
    //                                               long id)
    //                    {
    //                        RecruiterCallState recruiterCallState = (RecruiterCallState) parent
    //                                .getItemAtPosition(position);
    //                        currentContact.setRecruiterCallState(recruiterCallState.getValue());
    //                    }
    //
    //                    @Override
    //                    public void onNothingSelected(AdapterView<?> parent)
    //                    {
    //
    //                    }
    //                });
    //            }
    //
    //            edtCompanyName.addTextChangedListener(new TextWatcher()
    //            {
    //                boolean ignoreChange = false;
    //
    //                @Override
    //                public void beforeTextChanged(CharSequence s, int start, int count, int after)
    //                {
    //                    //Toast.makeText(RecruiterCallsDetailsActivity.this, "beforeTextChanged" +
    //                    // start + " " + count + " " + after + " ", Toast.LENGTH_SHORT).show();
    //                }
    //
    //                @Override
    //                public void onTextChanged(CharSequence s, int start, int before, int count)
    //                {
    //                    //Toast.makeText(RecruiterCallsDetailsActivity.this, "onTextChanged" + start
    //                    // + " " + before + " " + count + " ", Toast.LENGTH_SHORT).show();
    //                }
    //
    //                @Override
    //                public void afterTextChanged(Editable s)
    //                {
    //                    if (!ignoreChange)
    //                    {
    //                        ignoreChange = true;
    ////                        currentContact.setRecruiterCompanyName(s.toString());
    //                        Toast.makeText(RecruiterCallsDetailsActivity.this, s.toString(), Toast
    //                                .LENGTH_SHORT).show();
    //                        ignoreChange = false;
    //                    }
    //                }
    //            });
    //
    //        }

    //            Dao<Contact, Integer> contactDao = RecruiterCallService.this.getHelper()
    // .getContactDao();
    //            //TableUtils.createTable(contactDao.getConnectionSource(), Contact.class);
    //
    //            //Check if Incoming Number exists in Database
    //            queryContact = contactDao.queryForEq("recruiterPhoneNumber", incomingNumber);
    //
    //            if (queryContact.isEmpty())
    //            {
    //                incomingNumberExists = true;
    //
    //                Contact contact = new Contact();
    //                contact.setContactPhoneNumber(incomingNumber);
    //
    //                contact.setDateCallReceived(DateTime.now().toString());
    //                contact.setRecruiterCallState(RecruiterCallState.INITIAL_CALL.getValue());
    //
    //                contactDao.createIfNotExists(contact);
    //
    //                spIncomingNumber.edit().putString("incomingNumber", incomingNumber).apply();
    //                spIncomingNumber.edit().putInt("contactId", contact.getContactId()).apply();
    //
    //                Toast.makeText(RecruiterCallService.this, incomingNumber + " stored in
    // database", Toast.LENGTH_LONG).show();
    //
    //            }
    //            else
    //            {
    //                incomingNumberExists = false;
    //
    //                Toast.makeText(RecruiterCallService.this, incomingNumber + " exists in
    // database", Toast.LENGTH_LONG).show();
    //            }
    //        }
    //        catch (Exception e)
    //        {
    //            Toast.makeText(RecruiterCallService.this, e.getMessage(), Toast.LENGTH_LONG).show();
    //        }
    //    }

    //    @Override
    //    protected void onSaveInstanceState(Bundle outState)
    //    {
    //        super.onSaveInstanceState(outState);
    //
    //        outState.putSerializable("Contact", currentContact);
    //    }


    //@OnClick(R.id.btnUpdate)
    fun btnUpdateOnClick(v: View)
    {
        try
        {
            val fn = edtFirstName!!.text.toString()
            val ln = edtLastName!!.text.toString()
            val cn = edtCompanyName!!.text.toString()
            val em = edtEmailAddress!!.text.toString()

            val cs = spRecruiterCallState!!.selectedItem as RecruiterCallState

            //            if (contactType == ContactType.RECRUITER)
            //            {
            //                RecruiterDao recruiterDao = greenDaoHelper.initSession().getRecruiterDao();
            //
            //                RecruiterCompany recruiterCompany = new RecruiterCompany();
            //                recruiterCompany.setAddress(cn);
            //                recruiterCompany.setRecruiterCompanyName(em);
            //
            //                Recruiter recruiter = new Recruiter();
            //                recruiter.setContactFirstName(fn);
            //                recruiter.setContactLastName(ln);
            //
            //                recruiter.setRecruiterCompany(recruiterCompany);
            //
            //                if (recruiterDao.insert(recruiter) > -1)
            //                {
            //                    Toast.makeText(getActivity(), "Recruiter saved " +
            //                            "successfully", Toast.LENGTH_LONG).show();
            //                }
            //
            //                greenDaoHelper.closeSession();
            //
            //            }
            //            else
            //            {

            //val contactDao = greenDaoHelper.initSession().contactDao

            //val queryContact = contactDao.queryBuilder().where(ContactDao.Properties.ContactId.eq(currentContact!!.contactId)).build()


            //val queryContactList = queryContact.list()

            //val contact = Contact()

//            if (!queryContactList.isEmpty())
//            {
//                //currentContact.setContactId(queryContactList.get(0).getContactId());
////                currentContact!!.contactFirstName = fn
////                currentContact!!.contactLastName = ln
//
//                //contactDao.update(currentContact)
//            } else
//            {
////                contact.contactFirstName = fn
////                contact.contactLastName = ln
////                //                            contact.setRecruiterCompanyName(cn);
////                //                            contact.setRecruiterCompanyAddress(em);
////                contactDao.insert(contact)
//            }

            //Toast.makeText(activity, "Updated  information for: " + contact.formattedRecruiterPhoneNumber, Toast.LENGTH_LONG).show()

            //contactDao.refresh(contact)
            //            }

        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }

    }

    fun searchGoogleUri(query: String): Intent
    {
        val uri = Uri.parse("https://www.google.com/search?q=" + query)

        val searchIntent = Intent(Intent.ACTION_VIEW, uri)
        searchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        return searchIntent

    }


    interface OnAddContactListener
    {
        fun onAddContact(message: String)

    }

}
