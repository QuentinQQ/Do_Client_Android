package com.example.doapp.dataProcess

import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule

fun convertFitnessMovementToFirestore(fitnessMovement: FitnessMovement): Map<String, Any> {
    return mapOf(
        "movementId" to fitnessMovement.movementId,
        "movementName" to fitnessMovement.movementName,
        "mainTrainingArea" to fitnessMovement.mainTrainingArea
    )
}

fun convertEachActionDetailToFirestore(eachActionDetail: EachActionDetail): Map<String, Any> {
    return mapOf(
        "detailId" to eachActionDetail.detailId,
        "scheduleId" to eachActionDetail.scheduleId,
        "movementId" to eachActionDetail.movementId,
        "sequenceNum" to eachActionDetail.sequenceNum,
        "sets" to eachActionDetail.sets,
        "weight" to eachActionDetail.weight
    )
}

fun convertOfficialCourseToFirestore(officialCourse: OfficialCourse): Map<String, Any> {
    return mapOf(
        "courseId" to officialCourse.courseId,
        "courseName" to officialCourse.courseName,
        "courseLength" to officialCourse.courseLength,
        "courseDesc" to officialCourse.courseDesc
    )
}

fun convertOfficialCourseScheduleToFirestore(officialCourseSchedule: OfficialCourseSchedule): Map<String, Any> {
    return mapOf(
        "scheduleId" to officialCourseSchedule.scheduleId,
        "courseId" to officialCourseSchedule.courseId,
        "scheduleDesc" to officialCourseSchedule.scheduleDesc,
        "dayNum" to officialCourseSchedule.dayNum
    )
}