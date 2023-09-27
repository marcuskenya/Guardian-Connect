package net.guardianconnect.ui.forgotpassword
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.guardianconnect.navigation.ROUTE_FORGOTPASSWORD
import net.guardianconnect.navigation.ROUTE_LOGIN
import net.guardianconnect.ui.auth.AuthViewModel
import net.simplifiedcoding.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
fun ForgotPasswordScreen(viewModel: AuthViewModel?, navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(androidx.compose.ui.graphics.Color.White),
        // on below line adding vertical and
        // horizontal alignment for column.
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "FORGOT YOUR PASSWORD?",
            color = androidx.compose.ui.graphics.Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.forgotpassword),
            contentDescription = "",
            modifier = Modifier.size(240.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Text(
                text = "Enter your registered email below to receive password reset instruction",
                color = androidx.compose.ui.graphics.Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp
            )

            TextField(value = email, onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                singleLine = true,
                leadingIcon = {
                    Row {
                        Icon(
                            Icons.Default.Email, contentDescription = "emailicon",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(modifier = Modifier.width(1.dp)
                            .height(24.dp)
                    )
                })

            Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(

            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp

            ),
            shape = RoundedCornerShape(5.dp)


        )
        {
            Text(
                text = "Send Reset Link",
                color = androidx.compose.ui.graphics.Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

        var gotologin = LocalContext.current
    TextButton(
        onClick = {navController.navigate(ROUTE_LOGIN)
        },
        modifier = Modifier.fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Remember password? Login",
            color = androidx.compose.ui.graphics.Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )

    }
}
}




