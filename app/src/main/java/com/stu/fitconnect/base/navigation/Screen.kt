package com.stu.fitconnect.base.navigation

sealed class Screen(
    val route: String
) {

    object SignIn : Screen(ROUTE_SIGN_IN)
    object SignUp : Screen(ROUTE_SIGN_UP)
    object SportClubList : Screen(ROUTE_SPORT_CLUBS_LIST)
    object SportClubFilters : Screen(ROUTE_SPORT_CLUBS_FILTERS)
    data class SelectedSportClub(val sportClubId: Int) : Screen(ROUTE_SELECTED_SPORT_CLUB) {
        fun getRouteWithArgs(): String {
            return "$ROUTE_SELECTED_SPORT_CLUB_FOR_ARG/$sportClubId"
        }
    }
    object Home : Screen(ROUTE_HOME)

    companion object {

        const val KEY_SPORT_CLUB = "sport_club_id"

        const val ROUTE_SIGN_IN = "sign_in"
        const val ROUTE_SIGN_UP = "sign_up"
        const val ROUTE_HOME = "home"
        const val ROUTE_SPORT_CLUBS_LIST = "clubs_list"
        const val ROUTE_SPORT_CLUBS_FILTERS = "clubs_filters"
        const val ROUTE_SELECTED_SPORT_CLUB_FOR_ARG = "selected_club"
        const val ROUTE_SELECTED_SPORT_CLUB = "$ROUTE_SELECTED_SPORT_CLUB_FOR_ARG/{$KEY_SPORT_CLUB}"
    }

//    sealed class Destinations(val route: String) {
//        object NewsListScreen : Destinations("news_list_screen")
//        data class NewsDetailScreen(val news: String = "news") : Destinations("news_detail_screen")
//        object FavoriteNewsScreen : Destinations("favorite_news_screen")
//        object ProfileScreen : Destination("profile_screen")
//        object SettingScreen : Destination("setting_screen")
//        object ThemeScreen : Destination("theme_screen")
//        object LoginScreen : Destination("login_screen")
//        object VerifyCodeScreen : Destination("verify_code_screen")
//        ...
//    }
}