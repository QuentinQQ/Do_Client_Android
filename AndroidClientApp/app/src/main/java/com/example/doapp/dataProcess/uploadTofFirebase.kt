package com.example.doapp.dataProcess

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

//
//fun uploadAllFitnessMovements() {
//    viewModel.getAllFitnessMovements().observe(this, Observer { movements ->
//        val db = FirebaseFirestore.getInstance()
//        movements.forEach { movement ->
//            val movementMap = convertFitnessMovementToFirestore(movement)
//            db.collection("fitnessMovements")
//                .document(movement.movementId)
//                .set(movementMap)
//                .addOnSuccessListener {
//                    Log.d("Firestore", "Fitness Movement successfully written!")
//                }
//                .addOnFailureListener { e ->
//                    Log.w("Firestore", "Error writing document", e)
//                }
//        }
//    })
//}
