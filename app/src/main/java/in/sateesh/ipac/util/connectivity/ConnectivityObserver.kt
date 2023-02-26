package `in`.sateesh.ipac.util.connectivity

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}