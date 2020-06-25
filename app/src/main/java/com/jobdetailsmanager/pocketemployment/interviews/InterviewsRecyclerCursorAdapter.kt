package com.jobdetailsmanager.pocketemployment.interviews

import android.content.Context
import android.database.Cursor
import android.database.DataSetObserver
import android.os.Build
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.jobdetailsmanager.pocketemployment.R
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter

@RequiresApi(Build.VERSION_CODES.O)
open class InterviewsRecyclerCursorAdapter(internal var context: Context, cursor: Cursor?) : InterviewsSectionedCursorAdapter(cursor) {//CursorRecyclerViewAdapter<InterviewViewHolder>(context, cursor) {

    private var interviewsSection = InterviewsSection()

    init{
        this.addSection(interviewsSection)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, cursor: Cursor?) {

        val eventStart = cursor?.getString(cursor.getColumnIndex(eventStartCol))
        val eventEnd = cursor?.getString(cursor.getColumnIndex(eventEndCol))
        val eventTitleName = cursor?.getString(cursor.getColumnIndex(eventTitle))

        (viewHolder as InterviewViewHolder).timeStart?.text = eventStart
        viewHolder.timeEnd?.text = eventEnd
        viewHolder.interviewType?.text = eventTitleName

        interviewsSection.onBindItemViewHolder(viewHolder, cursor!!.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewViewHolder {
        //val itemView = LayoutInflater.from(context).inflate(R.layout.interviews_lv_row_layout, parent, false)

        interviewsSection.getItemViewHolder(parent)

        return InterviewViewHolder(parent)
    }

    companion object {
        private val calendarUri = CalendarContract.Events.CONTENT_URI
        private val calDisplayName = CalendarContract.Events.CALENDAR_DISPLAY_NAME
        private val eventID = CalendarContract.Events._ID
        private val eventStartCol = CalendarContract.Events.DTSTART
        private val eventEndCol = CalendarContract.Events.DTEND
        private val eventTitle = CalendarContract.Events.TITLE
        private val eventDescription = CalendarContract.Events.DESCRIPTION
        private val eventTimeZone = CalendarContract.Events.EVENT_TIMEZONE
        private val eventAllDay = CalendarContract.Events.ALL_DAY
    }


}

class InterviewViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    val interviewType: TextView?
    val timeStart: TextView?
    val timeEnd: TextView?

    init {
        interviewType = rootView.findViewById(R.id.tvInterviewType)
        timeStart = rootView.findViewById(R.id.tvStartTime)
        timeEnd = rootView.findViewById(R.id.tvEndTime)
    }
}

class InterviewDateViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    var interviewDate: TextView

    init {
        interviewDate = rootView.findViewById(R.id.tvInterviewDate)
    }
}


abstract class InterviewsSectionedCursorAdapter(var cursor: Cursor?) : SectionedRecyclerViewAdapter() {

    private var mLayoutInflater: LayoutInflater? = null

    //private var mDataValid: Boolean
    private var mRowIdColumn: Int
    private val mDataSetObserver: DataSetObserver?

    init {
        //mDataValid = cursor != null
        mRowIdColumn = cursor!!.getColumnIndex("_id")
        mDataSetObserver = NotifyingDataSetObserver()

        cursor?.registerDataSetObserver(mDataSetObserver)

    }

    override fun getItemCount(): Int {
        return cursor!!.count
    }

    override fun getItemId(position: Int): Long {
        return if (cursor!!.moveToPosition(position)) {
            cursor!!.getLong(mRowIdColumn)
        } else 0
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, cursor: Cursor?)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //check(mDataValid) { "this should only be called when the cursor is valid" }
        check(cursor!!.moveToPosition(position)) { "couldn't move cursor to position $position" }

        //onBindViewHolder(holder, cursor)

    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     */
    open fun changeCursor(cursor: Cursor?) {
        val old = swapCursor(cursor)
        old?.close()
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * [.changeCursor], the returned old Cursor is *not*
     * closed.
     */
    open fun swapCursor(newCursor: Cursor?): Cursor? {
        if (newCursor === cursor) {
            return null
        }
        val oldCursor = cursor
        if (mDataSetObserver != null) {
            oldCursor?.unregisterDataSetObserver(mDataSetObserver)
        }
        cursor = newCursor!!
//        if (cursor != null) {
        if (mDataSetObserver != null) {
            cursor?.registerDataSetObserver(mDataSetObserver)
        }
        mRowIdColumn = newCursor.getColumnIndexOrThrow("_id")
        //mDataValid = true
        notifyDataSetChanged()
//        } else {
//            mRowIdColumn = -1
//            mDataValid = false
//            notifyDataSetChanged()
//            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
//        }
        return oldCursor
    }

    private inner class NotifyingDataSetObserver : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            //mDataValid = true
            notifyDataSetChanged()
        }

        override fun onInvalidated() {
            super.onInvalidated()
            //mDataValid = false
            notifyDataSetChanged()
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
        }
    }

    inner class InterviewsSection() : Section(SectionParameters.builder().headerResourceId(R.layout.interviews_date_tv_separator).itemResourceId(R.layout.interviews_lv_row_layout).build()) {

        override fun getContentItemsTotal(): Int {
            return cursor!!.count
        }

        override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            onBindViewHolder(holder!!, cursor)

        }

        override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {
            return InterviewViewHolder(view!!)
        }

        override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
            onBindViewHolder(holder!!, cursor)
        }

        override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
            return InterviewDateViewHolder(view!!)
        }

    }

}