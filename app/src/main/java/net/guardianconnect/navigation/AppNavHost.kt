package net.guardianconnect.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.guardianconnect.ui.auth.AuthViewModel
import net.guardianconnect.ui.auth.LoginScreen
import net.guardianconnect.ui.auth.SignupScreen
import net.guardianconnect.ui.forgotpassword.ForgotPasswordScreen
import net.guardianconnect.ui.home.HomeScreen
import net.guardianconnect.ui.report.ReportScreen
import net.guardianconnect.ui.viewdata.ViewScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_REPORT
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(viewModel, navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(viewModel, navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(viewModel, navController)
        }
        composable(ROUTE_REPORT) {
            ReportScreen(viewModel, navController)
        }
        composable(ROUTE_VIEW) {
            ViewScreen(viewModel, navController)
        }
        composable(ROUTE_FORGOTPASSWORD) {
            ForgotPasswordScreen(viewModel, navController)
        }
    }
}