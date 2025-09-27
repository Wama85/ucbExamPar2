package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class DollarRepository(val realTimeRemoteDataSource: RealTimeRemoteDataSource): IDollarRepository {
    override  fun getDollar(): Flow<DollarModel> {

        return realTimeRemoteDataSource.getDollarUpdates()

    }
}
