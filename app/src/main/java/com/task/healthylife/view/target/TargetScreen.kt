package com.task.healthylife.view.target

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.healthylife.R
import com.task.healthylife.classes.TaskList
import com.task.healthylife.model.Task

@Composable
fun TargetScreen(){



    val taskList = TaskList()
    val tasks = taskList.tasks
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)

    tasks.forEach { task ->
        task.isComplete = sharedPreferences.getBoolean(task.label, task.isComplete)
    }


    var isTaskDialog by rememberSaveable { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    selectedTask?.let { task ->
        TaskDialog(
            task = task,
            isOpen = isTaskDialog,
            image = task.image,
            label = task.label,
            description = task.description,
            onDismissClick = { isTaskDialog = false},
            onConfirmClick = {
                updateTaskStatus(task,sharedPreferences)
                isTaskDialog = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(30.dp))
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.undraw_complete_task_re_44tb),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(180.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(tasks){task ->
                    TaskItem(
                        image = task.image,
                        label = task.label,
                        isComplete = task.isComplete,
                        onClick = {
                            selectedTask = task
                            isTaskDialog = true
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xFFF5F5F5)
                        )
                    )
                )
        )
    }
}

@Composable
fun TaskItem(
    image: Int,
    label:String,
    isComplete:Boolean,
    onClick:() -> Unit
){

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(80.dp)
            .shadow(elevation = 5.dp, RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
            .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
            .background(Color(0xFFF5F5F5))
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, end = 15.dp)
                .size(60.dp)
                .clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(image),
                contentDescription = null,
                tint = Color(0xFFFFD188),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = label,
            color = Color(0xFF3BA170),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(if (isComplete) R.drawable.noun_true_2560898 else R.drawable.noun_false_1688472),
            contentDescription = null,
            tint = if (isComplete) Color(0xFF34A853) else Color.Red,
            modifier = Modifier.padding(top = 10.dp, end = 10.dp)
                .size(if (isComplete) 40.dp else 60.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun TaskDialog(
    task: Task,
    isOpen: Boolean,
    image: Int,
    label: String,
    description: String,
    onDismissClick:() -> Unit,
    onConfirmClick:() -> Unit
){
    if (isOpen){
        AlertDialog(
            shape = RoundedCornerShape(20.dp),
            containerColor = Color(0xFFF5F5F5),
            onDismissRequest = onDismissClick,
            title = {
                Text(
                    text = label,
                    color = Color(0xFF34A853),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(image),
                        contentDescription = null,
                        tint = Color(0xFFFFD188),
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onConfirmClick) {
                    Text(
                        text = if (task.isComplete) "Restart" else "ِِAchieved it",
                        color = Color(0xFF34A853),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissClick) {
                    Text(
                        text = "Cancel",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        )
    }
}

fun updateTaskStatus(task: Task, sharedPreferences: SharedPreferences) {
    task.isComplete = !task.isComplete
    sharedPreferences.edit().putBoolean(task.label,task.isComplete).apply()
}

