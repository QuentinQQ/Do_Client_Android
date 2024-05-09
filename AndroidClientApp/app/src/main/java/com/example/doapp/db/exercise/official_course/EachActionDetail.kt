package com.example.doapp.db.exercise.official_course

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement

@Entity(
    // 设置外键
    foreignKeys = [
        ForeignKey(entity = OfficialCourseSchedule::class,
            parentColumns = arrayOf("scheduleId"),
            childColumns = arrayOf("scheduleId"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FitnessMovement::class,
            parentColumns = arrayOf("movementId"),
            childColumns = arrayOf("movementId"),
            onDelete = ForeignKey.CASCADE)
    ],
    // 确保courseId和dayNum的组合唯一
    indices = [
        Index(value = ["sequenceNum", "scheduleId"], unique = true)
    ]
)
data class EachActionDetail(
    @PrimaryKey
    val detailId: String,
    val scheduleId: String, // 外键，关联到 OfficialCourseSchedule
    val movementId: String, // 动作id
    val sequenceNum: Int, // 动作的顺序编号，在同一 OfficialCourseSchedule 内唯一
    val sets: Int, // 组数
    val weight: Double // 重量
)



//训练日程安排
//星期一 (胸部和背部)
//平板杠铃卧推（Flat Bench Press）：3-4组 x 8-12次
//引体向上（Pull-Ups）：3-4组 x 8-12次
//斜板杠铃卧推（Incline Bench Press）：3-4组 x 8-12次
//单臂哑铃划船（Single-Arm Dumbbell Row）：3-4组 x 8-12次
//星期二 (腿部)
//腿部推蹬（Leg Press）：3-4组 x 8-12次
//深蹲（Squats）：3-4组 x 8-12次
//腿弯举（Leg Curls）：3-4组 x 8-12次
//小腿提踵（Calf Raises）：3-4组 x 8-12次
//星期三 (休息)
//星期四 (肩部和手臂)
//肩部推举（Shoulder Press）：3-4组 x 8-12次
//哑铃侧平举（Dumbbell Lateral Raises）：3-4组 x 8-12次
//杠铃卧推（Bench Press）：3-4组 x 8-12次
//弯举（Bicep Curls）：3-4组 x 8-12次
//平板杠铃颈后臂屈伸（Skull Crushers）：3-4组 x 8-12次
//星期五 (休息)
//星期六 (再次胸部和背部)
//哑铃飞鸟（Dumbbell Flyes）：3-4组 x 8-12次
//坡度拉力器下拉（Incline Cable Pulldowns）：3-4组 x 8-12次
//杠铃卧推（Bench Press）：3-4组 x 8-12次
//哑铃飞鸟（Dumbbell Flyes）：3-4组 x 8-12次
//星期日 (休息)