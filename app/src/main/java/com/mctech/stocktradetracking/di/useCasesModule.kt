package com.mctech.stocktradetracking.di

import com.mctech.stocktradetracking.domain.stock_share.interaction.CloseStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.DeleteStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.EditStockSharePriceCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.FilterStockDailyListCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.FilterStockShareListCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.GetFinalBalanceCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.GetFinalDailyBalanceCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.GetMarketStatusCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.GroupStockShareListCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.ObserveStockClosedListCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.ObserveStockShareListCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SaveStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SelectBestDailyStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SelectBestStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SelectWorstDailyStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SelectWorstStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SellStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SplitStockShareCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.SyncStockSharePriceCase
import com.mctech.stocktradetracking.domain.stock_share.interaction.strategies.ComputeBalanceStrategy
import com.mctech.stocktradetracking.domain.stock_share.interaction.strategies.FilterStockListStrategy
import com.mctech.stocktradetracking.domain.stock_share.interaction.strategies.ObserveStockListStrategy
import com.mctech.stocktradetracking.domain.stock_share.interaction.strategies.SelectStockStrategy
import com.mctech.stocktradetracking.domain.stock_share_filter.interaction.ObserveCurrentFilterCase
import com.mctech.stocktradetracking.domain.stock_share_filter.interaction.SaveStockShareFilterCase
import com.mctech.stocktradetracking.domain.timeline_balance.interaction.CreatePeriodCase
import com.mctech.stocktradetracking.domain.timeline_balance.interaction.EditPeriodCase
import com.mctech.stocktradetracking.domain.timeline_balance.interaction.GetCurrentPeriodBalanceCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val stockShareUseCasesModule = module {
  single { SaveStockShareCase(service = get(), logger = get()) }
  single { DeleteStockShareCase(service = get(), logger = get()) }
  single { CloseStockShareCase(service = get(), logger = get()) }
  single { EditStockSharePriceCase(service = get(), logger = get()) }
  single { SellStockShareCase(service = get(), logger = get()) }
  single { SyncStockSharePriceCase(service = get(), logger = get()) }
  single { GetMarketStatusCase(service = get(), logger = get()) }
  single { GetFinalBalanceCase() }
  single { GroupStockShareListCase() }
  single { ObserveCurrentFilterCase(service = get()) }
  single { SplitStockShareCase(service = get(), logger = get()) }

  single(named("closedListObserver")) { ObserveStockClosedListCase(service = get()) as ObserveStockListStrategy }

  single(named("stockFilter")) { FilterStockShareListCase(groupStockShareListCase = get()) as FilterStockListStrategy }
  single(named("dailyStockFilter")) { FilterStockDailyListCase(groupStockShareListCase = get()) as FilterStockListStrategy }

  single(named("dailyWorstSelector")) { SelectWorstDailyStockShareCase(groupStockShareListCase = get()) as SelectStockStrategy }
  single(named("dailyBestSelector")) { SelectBestDailyStockShareCase(groupStockShareListCase = get()) as SelectStockStrategy }
  single(named("stockWorstSelector")) { SelectWorstStockShareCase(groupStockShareListCase = get()) as SelectStockStrategy }
  single(named("stockBestSelector")) { SelectBestStockShareCase(groupStockShareListCase = get()) as SelectStockStrategy }

  single(named("stockBalance")) { GetFinalBalanceCase() as ComputeBalanceStrategy }
  single(named("dailyBalance")) { GetFinalDailyBalanceCase() as ComputeBalanceStrategy }
}

val stockShareFilterUseCasesModule = module {
  single { ObserveStockShareListCase(service = get()) as ObserveStockListStrategy }
  single { SaveStockShareFilterCase(service = get(), logger = get()) }
}

val timelineUseCasesModule = module {
  single { CreatePeriodCase(service = get(), logger = get()) }
  single { EditPeriodCase(service = get(), logger = get()) }
  single { GetCurrentPeriodBalanceCase(service = get(), logger = get()) }
}