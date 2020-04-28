package com.mctech.stocktradetracking.domain.stock_share.interaction

import com.mctech.library.logger.Logger
import com.mctech.stocktradetracking.domain.stock_share.service.StockShareService
import com.mctech.stocktradetracking.testing.data_factory.factories.StockShareDataFactory
import com.mctech.stocktradetracking.testing.data_factory.testScenario
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteStockShareCaseTest{
    private val service         = mock<StockShareService>()
    private val logger          = mock<Logger>()

    private val expectedError   = RuntimeException()
    private val expectedSingle  = StockShareDataFactory.single()

    private lateinit var useCase: DeleteStockShareCase

    @Before
    fun `before each test`() {
        useCase = DeleteStockShareCase(service, logger)
    }

    @Test
    fun `should delegate calling`() = testScenario(
        action = {
            useCase.execute(expectedSingle)
        },
        assertions = {
            verify(service).deleteStockShare(expectedSingle)
            verifyNoMoreInteractions(service)
        }
    )

    @Test
    fun `should log error`() = testScenario(
        scenario = {
            whenever(service.deleteStockShare(any())).thenThrow(expectedError)
        },
        action = {
            useCase.execute(expectedSingle)
        },
        assertions = {
            verify(logger).e(e = expectedError)
        }
    )
}