package com.example.discussor.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.discussor.R
import com.example.discussor.model.ThemeClickHandler
import com.example.domain.models.Theme
import kotlin.collections.ArrayList

class ThemeAdapter: RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private val mThemeList: MutableList<Theme> = ArrayList()
    private var themeClickHandler: ThemeClickHandler? = null

    fun setData(newTheme: List<Theme>) {
        mThemeList.clear()
        mThemeList.addAll(newTheme)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_theme, viewGroup, false),
            themeClickHandler = themeClickHandler)
    }

    override fun getItemCount(): Int {
        return mThemeList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mThemeList[position])
    }

    fun attachClickHandler(themeClickHandler: ThemeClickHandler){
        this.themeClickHandler = themeClickHandler
    }

    class ViewHolder(itemView: View, private val themeClickHandler: ThemeClickHandler?): RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.textTheme)
        private val textNumberPeople: TextView = itemView.findViewById(R.id.textNumberPeople)
        private val cellTheme = itemView.findViewById<LinearLayout>(R.id.llCellTheme)
        //private val textNameThemeOnDialogFragment: TextView =


        fun bind(model: Theme) {
            textName.text = model.name_theme
            getNumberPeople(vote_for = model.vote_for, vote_against = model.vote_against)

            cellTheme.setOnClickListener {
                themeClickHandler?.onItemClick(item = model, textNameTheme = model.name_theme, id_theme = model.id_theme,
                    voteFor = model.vote_for, voteAgainst = model.vote_against)
            }
        }

        private fun getNumberPeople(vote_for: String, vote_against: String) {
            if (vote_for == "-" && vote_against == "-") {
                textNumberPeople.text = itemView.context.getString(R.string.occupied_by_nobody)
            }
            if ((vote_for != "-" && vote_against == "-") || (vote_for == "-" && vote_against != "-")) {
                textNumberPeople.text = itemView.context.getString(R.string.occupied_by_one)
            }
            if (vote_for != "-" && vote_against != "-") {
                textNumberPeople.text = itemView.context.getString(R.string.occupied_by_two)
            }
        }
    }
}