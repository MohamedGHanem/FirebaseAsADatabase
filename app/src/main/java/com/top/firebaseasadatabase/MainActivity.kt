package com.top.firebaseasadatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.top.firebaseasadatabase.adapter.UserAdapter
import com.top.firebaseasadatabase.model.UserInfo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        floatingActionButton.setOnClickListener {
            val intent = Intent(applicationContext, AddContactActivity::class.java)
            startActivity(intent)

            readData()

        }


//        val rootRef = FirebaseFirestore.getInstance()
//        val query: Query = rootRef.collection("users")
//            .orderBy("user_name", Query.Direction.ASCENDING)

        val data = mutableListOf<UserInfo>()

        data.add(UserInfo(1, "Ahmed", 123, "address"))
        data.add(UserInfo(2, "M", 444, "address"))
        data.add(UserInfo(3, "B", 555, "address"))



        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.layoutManager = GridLayoutManager(this, 2)
        rv_main.setHasFixedSize(true)
        val studentAdapter = UserAdapter(this, data)

        rv_main.adapter = studentAdapter


    }


    private fun readData() {

//        db.collection("users").orderBy("user_name").limit(2)
        db.collection("users").orderBy("user_name").limit(2)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG_Get_Data: ", "${document.id} => ${document.data.values}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents.", exception)
            }
    }


}