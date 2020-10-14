package com.mctech.stocktradetracking.domain.stock_share.service

import com.mctech.stocktradetracking.domain.stock_share.entity.MarketStatus
import com.mctech.stocktradetracking.domain.stock_share.entity.StockShare
import kotlinx.coroutines.flow.Flow

interface StockShareService {
  suspend fun observeStockShareList(): Flow<List<StockShare>>
  suspend fun observeStockClosedList(): Flow<List<StockShare>>
  suspend fun getMarketStatus(): MarketStatus

  suspend fun saveStockShare(share: StockShare)
  suspend fun sellStockShare(share: StockShare)
  suspend fun deleteStockShare(share: StockShare)
  suspend fun closeStockShare(share: StockShare)
  suspend fun editStockShareValue(shareCode: String, value: Double)
  suspend fun syncStockSharePrice()
}
