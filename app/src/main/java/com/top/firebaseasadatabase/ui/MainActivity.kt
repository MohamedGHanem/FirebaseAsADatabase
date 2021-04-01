package com.top.firebaseasadatabase.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.top.firebaseasadatabase.R
import com.top.firebaseasadatabase.adapter.UserAdapter
import com.top.firebaseasadatabase.model.UserInfo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name
    private val data = mutableListOf<UserInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        readDataCloudFirestore()


        floatingActionButton.setOnClickListener {
            val intent = Intent(applicationContext, AddContactActivity::class.java)
            startActivity(intent)
        }


//          //adapter and recyclerview.
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.layoutManager = GridLayoutManager(this, 1)
        rv_main.setHasFixedSize(true)


    }


    private fun readDataCloudFirestore() {
        val db = FirebaseFirestore.getInstance()

        var idUser = 0

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    data.add(
                        UserInfo(
                            idUser++.toString(),
                            document.get("user_name").toString(),
                            document.get("mobile_number").toString(),
                            document.get("user_address").toString()
                        )


                    )
                }

                data.size

                val studentAdapter = UserAdapter(this, data)
                rv_main.adapter = studentAdapter


            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents.", exception)
            }

    }


}