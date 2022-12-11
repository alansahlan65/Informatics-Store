package com.example.laundreasy.data.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserRepository() {

    var _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    var _userData : MutableLiveData<User> = MutableLiveData()
    var _isRegistered: MutableLiveData<Boolean> = MutableLiveData()
    var _loginResult : MutableLiveData<Boolean> = MutableLiveData()
    private val db = Firebase.firestore.collection("users")
    private val auth = Firebase.auth

    init{
        if(auth.currentUser != null){
            _user.value = auth.currentUser
            _loginResult.postValue(true)
            updateData()
        }
    }

    fun register(nama: String, email: String, password: String, no: String, alamat: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val userId = auth.currentUser!!.uid
                val user = User(userId, email, nama, no, alamat)
                db.document(userId).set(user)
                _isRegistered.postValue(true)
                Log.i("TEST", "register: Berhasil Register")
            }else{
                Log.i("TEST", "register: Gagal Register", task.exception)
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.postValue(auth.currentUser)
                    _loginResult.postValue(true)
                    Log.i("TEST", "register: berhasil Login", task.exception)
                } else {
                    _loginResult.postValue(false)
                    Log.i("TEST", "register: Gagal Login", task.exception)

                }
            }.addOnFailureListener {
                Log.i("TEST", "register: failure Login")
            }
    }

    fun logout() {
        auth.signOut()
        Log.i("TEST", "logout: SIGNOUT")
    }

    fun editData(nama: String, alamat: String){
        db.document(auth.currentUser!!.uid).update("nama", nama)
        db.document(auth.currentUser!!.uid).update("alamat", alamat)
    }

    fun updateData(){
        db.document(auth.currentUser!!.uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TEST", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                _userData.value = snapshot.toObject<User>()
            } else {
                Log.d("TEST", "Current data: null")
            }
        }
    }
}