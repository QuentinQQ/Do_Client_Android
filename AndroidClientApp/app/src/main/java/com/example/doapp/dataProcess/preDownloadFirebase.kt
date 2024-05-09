package com.example.doapp.dataProcess

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.google.firebase.firestore.FirebaseFirestore

fun fetchOfficialCourses(): LiveData<List<OfficialCourse>> {
    val coursesLiveData = MutableLiveData<List<OfficialCourse>>()

    FirebaseFirestore.getInstance().collection("officialCourses")
        .get()
        .addOnSuccessListener { result ->
            val courses = mutableListOf<OfficialCourse>()
            for (document in result) {
                try {
                    val course = document.toObject(OfficialCourse::class.java)
                    courses.add(course)
                } catch (e: Exception) {
                    Log.e("Firestore", "Error reading course", e)
                }
            }
            coursesLiveData.postValue(courses)
        }
        .addOnFailureListener { exception ->
            Log.e("Firestore", "Error getting documents: ", exception)
        }

    return coursesLiveData
}
