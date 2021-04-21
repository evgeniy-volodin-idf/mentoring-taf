package shop

import org.junit.jupiter.api.*
import shop.context.ContextHolder
import shop.model.Drug

internal class DefaultCartTest {
  private lateinit var cart: DefaultCart
  private lateinit var fakeDrugOrder: MutableList<Drug>
  private lateinit var drugToAdd: Drug

  @BeforeEach
  fun setUp() {
    drugToAdd = Drug(
      productName = shop.model.DrugType.LSD,
      price = 10,
      quantity = 100
    )
    fakeDrugOrder = mutableListOf(drugToAdd)
    cart = DefaultCart(fakeDrugOrder)
  }

  @AfterEach
  fun tearDown() {
  }

  @Test
  fun `addPosition return 'true' when Drug added to cart`() {
    val exampleCart = listOf(drugToAdd)
    Assertions.assertEquals(exampleCart, cart.getListOfDrugsInCart(), "Drug is not added to cart")
  }

  @Test
  fun addToSoldDrugs() {
    cart.addToSoldDrugs().apply {
      assertAll(
        {
          Assertions.assertEquals(
            cart.getListOfDrugsInCart(), ContextHolder.getContext().soldDrugs, "Cart is not save drugs to SoldData"
          )
        },
        {
          Assertions.assertTrue(drugToAdd.dateSold != null, "dateSold is not setted")
        }
      )
    }
  }

  @Test
  fun clearCart() {
    cart.clearCart()
    Assertions.assertTrue(cart.getListOfDrugsInCart().isEmpty(), "Cart is not cleared")
  }
}