package net.guardianconnect.ui.home

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.simplifiedcoding.R
import net.guardianconnect.navigation.ROUTE_HOME
import net.guardianconnect.navigation.ROUTE_LOGIN
import net.guardianconnect.navigation.ROUTE_REPORT
import net.guardianconnect.navigation.ROUTE_VIEW
import net.guardianconnect.ui.auth.AuthViewModel
import net.guardianconnect.ui.theme.AppTheme
import net.guardianconnect.ui.theme.spacing

@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.medium)
            .padding(top = spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.welcome_back),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )


        Image(
            painter = painterResource(id = R.drawable.vigilante),
            contentDescription = stringResource(id = R.string.empty),
            modifier = Modifier.size(128.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {


         Button(onClick = {
             navController.navigate(ROUTE_REPORT)
         },
             shape = RoundedCornerShape(5.dp),
             modifier = Modifier.fillMaxWidth()
         ) {
         Text(text = "REPORT",color = Color.Black, fontSize = 12.sp)
         }
            
            
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {navController.navigate(ROUTE_VIEW)},
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
                ) {
                Text(text = "VIEW",color = Color.Black, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    viewModel?.logout()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_HOME) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = spacing.extraLarge)
            ) {
                Text(text = stringResource(id = R.string.logout),color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    AppTheme {
        HomeScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    AppTheme {
        HomeScreen(null, rememberNavController())
    }
}
