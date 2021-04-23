package np.com.susanthapa.curved_bottom_navigation

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.annotation.StringRes

/**
 * Created by suson on 10/1/20
 */
data class CbnMenuItem(
    @StringRes
    val tittle: Int,
    @DrawableRes
    val icon: Int,
    @DrawableRes
    val avdIcon: Int,
    @IdRes
    val destinationId: Int = -1
)