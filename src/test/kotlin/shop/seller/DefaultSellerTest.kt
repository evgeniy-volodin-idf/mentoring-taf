package shop.seller

import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import shop.cart.DefaultCart
import shop.model.Drug
import shop.model.DrugType
import shop.warehouse.DefaultWarehouse

@ExtendWith(MockKExtension::class)
internal class DefaultSellerTest {
  @MockK
  lateinit var warehouse: DefaultWarehouse

  @MockK
  lateinit var cart: DefaultCart

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun selectDrugAndQuantity() {
    val seller: DefaultSeller = spyk(DefaultSeller(cart, warehouse))
    val drug = Drug(
      productName = DrugType.LSD,
      price = 50,
      quantity = 10
    )
    val drugName = drug.productName.name
    val drugQuantity = drug.quantity
    every { seller.selectDrug() } returns drugName
    every { seller.selectQuantity(drugName) } returns drugQuantity
    every { warehouse.getSelectedDrug(drugName, drugQuantity) } returns drug
    every { cart.addPosition(drug) } just Runs

    seller.selectDrugAndQuantity()

    verify(exactly = 1) { seller.selectDrug() }
    verify(exactly = 1) { seller.selectQuantity(drugName) }
    verify(exactly = 1) { warehouse.getSelectedDrug(drugName, drugQuantity) }
    verify(exactly = 1) { cart.addPosition(drug) }
  }

  @Test
  fun selectDrug() {
    val seller: DefaultSeller = spyk(DefaultSeller(cart, warehouse), recordPrivateCalls = true)
    val expectedDrugName = DrugType.LSD.name
    every { seller["readLineFromConsole"]() } returns expectedDrugName
    every { warehouse.isDrugExists(expectedDrugName) } returnsMany listOf(false, true)

    seller.selectDrug()

    verify(exactly = 2) { seller["readLineFromConsole"]() }
    verify(exactly = 2) { warehouse.isDrugExists(expectedDrugName) }
  }
}