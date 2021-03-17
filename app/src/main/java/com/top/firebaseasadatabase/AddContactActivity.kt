package com.top.firebaseasadatabase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_contact.*


class AddContactActivity : AppCompatActivity() {


    private val user: MutableMap<String, Any> = HashMap()
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

//            val user = hashMapOf(
//                "userName" to userName.toString(),
//                "mobileNumber" to mobileNumber.toString(),
//                "address" to address.toString()
//            )

        btn_save.setOnClickListener {
//            saveDateOnFireStore()
            readData()
        }

    }

    private fun saveDateOnFireStore() {

        user["user_name"] = et_useName.text.toString()
        user["mobile_number"] = et_mobileNumber.text.toString()
        user["user_address"] = et_address.text.toString()

        for (key in user.keys) {
            println("Element at key $key = ${user[key]}")
        }

        if (!user.values.isEmpty()) {
            // Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        "TAG",
                        "DocumentSnapshot added with ID: " + documentReference.id
                    )
                    Toast.makeText(applicationContext, "Success Added", Toast.LENGTH_SHORT).show()
                    clearEditText()

                }
                .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
        } else {
            Toast.makeText(applicationContext, "Enter Data", Toast.LENGTH_SHORT).show()
        }
    }


    private fun readData() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG_Get_Data: ", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents.", exception)
            }
    }


    private fun clearEditText() {
        et_useName.text.clear()
        et_mobileNumber.text.clear()
        et_address.text.clear()
    }
}