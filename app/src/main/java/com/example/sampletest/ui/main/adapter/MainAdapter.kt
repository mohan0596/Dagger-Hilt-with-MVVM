package com.example.sampletest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletest.R
import com.example.sampletest.data.dto.DeptDTO
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val users: ArrayList<DeptDTO>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>(), Filterable {

    var userFilterList = ArrayList<DeptDTO>()

    init {
        userFilterList = users
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: DeptDTO) {
            itemView.textViewUserName.text = user.name
            itemView.textViewDeptName.text = user.dept_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = userFilterList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<DeptDTO>) {
        userFilterList.addAll(list)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isNotEmpty()) {

                    val resultList = ArrayList<DeptDTO>()
                    for (row in users) {
                        if (row.name!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                            || row.dept_name!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) ) {
                            resultList.add(row)
                        }
                    }
                    userFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilterList = results?.values as ArrayList<DeptDTO>
                notifyDataSetChanged()
            }
        }
    }
}