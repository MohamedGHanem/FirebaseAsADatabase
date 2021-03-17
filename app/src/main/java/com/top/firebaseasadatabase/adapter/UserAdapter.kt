package com.top.firebaseasadatabase.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.top.firebaseasadatabase.R
import com.top.firebaseasadatabase.model.UserInfo
import kotlinx.android.synthetic.main.custom_item_contact.view.*

class UserAdapter(var activity: Activity, var data: MutableList<UserInfo>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvId = itemView.tvId!!
        val tvName = itemView.tv_name!!
        val tvAverage = itemView.tv_mobile_number!!
        val tvAddress = itemView.tv_address!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity)
            .inflate(R.layout.custom_item_contact, parent, false)//// student_item ///
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvId.text = data[position].id.toString()
        holder.tvName.text = data[position].name
        holder.tvAverage.text = data[position].mobileNumber.toString()
        holder.tvAddress.text = data[position].address

        holder.tvAddress.setOnClickListener {
            Toast.makeText(activity, "User Details : ${data[position].name}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}