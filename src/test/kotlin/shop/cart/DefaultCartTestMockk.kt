package shop.cart

import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import shop.context.AppContext
import shop.context.Context
import shop.model.Drug

@ExtendWith(MockKExtension::class)
internal class DefaultCartTestMockk {

  @BeforeEach
  fun setUp() {
  }

  @AfterEach
  fun tearDown() {
    fun tearDown() {
      clearAllMocks()
    }
  }

  @Test
  fun moveSoldDrugsToContext() {
    val drug = Drug(
      productName = shop.model.DrugType.LSD,
      price = 10,
      quantity = 100
    )
    val listOfDrugs = mutableListOf(drug)

    @MockK
    val context: Context = spyk(AppContext())
    val cart: Cart = DefaultCart(context)

    cart.addPosition(drug)
    cart.moveSoldDrugsToContext()
    verify { context.setSoldDrugsInContext(any()) }

    Assertions.assertEquals(listOfDrugs, context.soldDrugs, "")
  }
}