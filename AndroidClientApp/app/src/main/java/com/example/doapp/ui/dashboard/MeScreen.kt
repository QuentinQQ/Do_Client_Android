package com.example.doapp.ui.dashboard

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.doapp.R
import com.example.doapp.dataProcess.getUserId
import com.example.doapp.db.ViewModel
import com.example.doapp.login.UserData
//import com.example.doapp.login.UserData
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.CardWhite
import com.example.doapp.ui.theme.DarkSecondary
import com.example.doapp.ui.theme.FontGray
import com.example.doapp.ui.theme.LightBackground
import com.example.doapp.ui.theme.LightFouth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun Me(
    navController: NavHostController,
    showNewPageOverlay: MutableState<Boolean>,
    googleSignInClient: GoogleSignInClient,
    auth: FirebaseAuth,
    viewModel: ViewModel,
    context: Context
//    userData: UserData?
) {
    val coroutineScope = rememberCoroutineScope()
    val userId = getUserId(context)

    val userInfo = userId?.let { viewModel.getUserInfoById(it).observeAsState().value }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .padding(16.dp)
    ) {
        // 1. First Part, including profile photo and username
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    userInfo?.profilePhotoUrl?.let { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(80.dp).clip(CircleShape).border(2.dp, Color.Gray, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } ?: Image(
                        painter = rememberVectorPainter(image = Icons.Default.AccountCircle),
                        contentDescription = "Default Profile Picture",
                        modifier = Modifier.size(80.dp).clip(CircleShape).border(2.dp, Color.Gray, CircleShape)
                    )
                }

                Spacer(Modifier.width(20.dp))

                Column {
                    Text(
                        text = userInfo?.userName ?: "Do",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    // 如果用户 ID 不为空，则显示 "User ID: [ID]"，否则显示 "User ID: [默认值]"
                    Text(
                        text = "User ID: ${userInfo?.userId ?: "default ID"}"
                    )
                }
            }

        }
        // 2. Second Part, including body data
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
//                .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(
                containerColor = CardWhite,
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            // Left side
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                ){
                    Row {
                        Text(
                            text = "My Body Data",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Icons.AutoMirrored.Filled
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text("180cm")
                        Spacer(Modifier.width(20.dp))
                        Text("70kg")
                        Spacer(Modifier.width(20.dp))
                        Text("21.6")
                    }
                    Row {
                        Text("Height")
                        Spacer(Modifier.width(20.dp))
                        Text("Weight")
                        Spacer(Modifier.width(20.dp))
                        Text("BMI")
                    }

                }

                // Right side
                Image(
                    painter = painterResource(id = R.drawable.bar_chart),
                    contentDescription = "BarChart",
                    modifier = Modifier.size(100.dp)
                )
            }


        }

        // 3. Third Part, including other option
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
//                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp))
                        .background(CardWhite),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Personal Information",
                        tint = ButtonBlue,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Personal Information")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp),
                        tint = FontGray
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
//                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp))
                        .background(CardWhite),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Preferences",
                        tint = ButtonBlue,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Preferences")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp),
                        tint = FontGray
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardWhite)
                        .clickable {
                            navController.navigate("HelpAndFeedback")
                        },
//                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Help&Feedback",
                        tint = ButtonBlue,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Help & Feedback")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp),
                        tint = FontGray
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardWhite)
//                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
                        .clickable {
                            navController.navigate("about")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Info",
                        tint = ButtonBlue,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("About Do")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp),
                        tint = FontGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 4. Log Out Button
        Button(
            onClick = {
                coroutineScope.launch {
                    googleSignInClient.signOut().addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.signOut()  // Ensure Firebase auth is also cleared
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }  // Clear back stack
                            }
                        } else {
                            // Handle error, possibly show a Snackbar
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DarkSecondary)
        ) {
            Text("Log Out")
        }

    }
    //  When clicked on "New" button in MeScreen
    if (showNewPageOverlay.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Cover a scrim
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(0.5f)))

            // add content in this scrim
            // 在这个遮罩层中添加内容
            New(navController, onDismiss = { showNewPageOverlay.value = false }) // 提供 onDismiss 参数
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewMe() {
//    val navController = rememberNavController()
//    val showOverlay = remember { mutableStateOf(false) }
//    Me(navController, showOverlay)
//}