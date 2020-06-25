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


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.jobdetailsmanager.pocketemployment.R

/**or
 * A simple [Fragment] subclass.
 */
class RecruiterPhoneNumberFragment : Fragment()
{
    private var phoneNumber: String? = null

    //internal var fragmentManager: FragmentManager
    internal var transaction: FragmentTransaction? = null

    internal var phoneNumberListener: AddPhoneNumberListener? = null


    override fun onAttach(context: Context)
    {
        super.onAttach(context)
    }


    override fun onAttachFragment(childFragment: Fragment)
    {
        super.onAttachFragment(childFragment)

        phoneNumberListener = childFragment as AddPhoneNumberListener?


    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setRetainInstance(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val entryCount = fragmentManager!!.backStackEntryCount
        Toast.makeText(activity, "Child BackStack Entry Count: $entryCount", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        //        if (entryCount > 0)
        //        {
        //            return inflater.inflate(R.layout.fragment_remove_phonenumber, container, false);
        //        }
        //        else
        //        {
        return inflater.inflate(R.layout.fragment_recruiter_phone_number, container, false)
        //        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        var savedInstanceState = savedInstanceState

        when (view.id)
        {
            R.id.llAddRecruiterPhoneNumber ->
            {
                val imgAdd = view.findViewById(R.id.imgbtnPlus) as ImageButton

                imgAdd.setOnClickListener {
                    val parentFragment = parentFragment

                    phoneNumberListener = parentFragment as AddPhoneNumberListener

                    if (phoneNumberListener != null) {
                        phoneNumberListener!!.AddPhoneNumber(fragmentManager!!)
                    }
                }

            }
        //            case R.id.llRemoveRecruiterPhoneNumber:
        //            {
        //                final Fragment parentFragment = getParentFragment();
        //
        //                final ImageButton imgMinus = (ImageButton)view.findViewById(R.id.imgMinus);
        //
        //                imgMinus.setOnClickListener(new View.OnClickListener()
        //                {
        //                    @Override
        //                    public void onClick(View view)
        //                    {
        //                        phoneNumberListener = (AddPhoneNumberListener)parentFragment;
        //                        phoneNumberListener.RemovePhoneNumber(RecruiterPhoneNumberFragment.this);

        //                        int backStackCount = getFragmentManager().getBackStackEntryCount();
        //
        //                        if (backStackCount > 1)
        //                        {
        //                            //imgAdd.setVisibility(View.GONE);
        //                            imgMinus.setVisibility(View.VISIBLE);
        //                        }
        //                        else
        //                        {
        //                            imgMinus.setVisibility(View.GONE);
        //                            //imgAdd.setVisibility(View.VISIBLE);
        //                        }
        //                    }
        //                });
        //            }
        //            break;
            else ->
            {
            }
        }

        //
        //        int backStackCount = getFragmentManager().getBackStackEntryCount();
        //
        //        if (backStackCount > 1)
        //        {
        //            imgAdd.setVisibility(View.GONE);
        //            imgMinus.setVisibility(View.VISIBLE);
        //        }
        //        else
        //        {
        //            imgMinus.setVisibility(View.GONE);
        //            imgAdd.setVisibility(View.VISIBLE);
        //        }


        val edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber) as EditText
        edtPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        if (savedInstanceState != null)
        {
            val state = savedInstanceState.getString("phoneNumber")
            //Toast.makeText(getActivity(), "FragmentPhoneNumber's View Created Instance State " + state, Toast.LENGTH_SHORT).show();
        }

        val charSequence: CharSequence?
        if (savedInstanceState != null)
        {
            charSequence = savedInstanceState.getCharSequence("phoneNumber")
            if (charSequence != null)
            {
                edtPhoneNumber.setText(charSequence)
            }
        } else
        {
            savedInstanceState = Bundle()

            val textWatcher = PhoneNumberTextWatcher()
            edtPhoneNumber.addTextChangedListener(textWatcher)
            val phoneNumber = textWatcher.phoneNumber
            savedInstanceState.putCharSequence("phoneNumber", phoneNumber)

            //            edtPhoneNumber.addTextChangedListener(new TextWatcher()
            //            {
            //
            //                @Override
            //                public void beforeTextChanged(CharSequence s, int start, int count, int after)
            //                {
            //
            //                }
            //
            //                @Override
            //                public void onTextChanged(CharSequence s, int start, int before, int count)
            //                {
            //                    Toast.makeText(getActivity(), "Text Changed", Toast.LENGTH_SHORT).show();
            //
            //                    phoneNumber = String.valueOf(s);
            //
            //                    savedInstanceState.putCharSequence("phoneNumber", s);
            //
            //                }
            //
            //                @Override
            //                public void afterTextChanged(Editable s)
            //                {
            //
            //                }
            //            });
        }
    }

    inner class PhoneNumberTextWatcher : TextWatcher
    {

        lateinit var phoneNumber: CharSequence


        override fun beforeTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int)
        {

        }

        override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int)
        {
            phoneNumber = charSequence.toString()
        }

        override fun afterTextChanged(editable: Editable)
        {

        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null)
        {
            val state = savedInstanceState.getString("phoneNumber")
            //Toast.makeText(getActivity(), "FragmentPhoneNumber's Activity Created Instance State " + state, Toast.LENGTH_SHORT).show();
        }

    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        //Toast.makeText(getActivity(), "Saved Instance State", Toast.LENGTH_SHORT).show();
        if (phoneNumber != null)
        {
            outState!!.putString("phoneNumber", phoneNumber)
        }
    }

    interface AddPhoneNumberListener
    {
        fun AddPhoneNumber(fragmentManager: FragmentManager)
        fun RemovePhoneNumber(fragment: Fragment)
    }


}// Required empty public constructor
