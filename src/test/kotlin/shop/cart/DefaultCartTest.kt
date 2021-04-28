package shop.cart

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import shop.context.Context
import shop.model.Drug

internal class DefaultCartTest {
  private lateinit var cart: DefaultCart
  private lateinit var drugToAdd: Drug
  private lateinit var fakeContext: FakeContext

  @BeforeEach
  fun setUp() {
    drugToAdd = Drug(
      productName = shop.model.DrugType.LSD,
      price = 10,
      quantity = 100
    )
    fakeContext = FakeContext(
      soldDrugs = mutableListOf(), drugsInWarehouse = listOf(), profit = 0
    )
    cart = DefaultCart(fakeContext)
    cart.addPosition(drugToAdd)
  }

  @Test
  fun `addPosition return 'true' when Drug added to cart`() {
    val exampleCart = listOf(drugToAdd)
    Assertions.assertEquals(exampleCart, cart.getListOfDrugsInCart(), "Drug is not added to cart")
  }

  @Test
  fun moveSoldDrugsToContext() {
    cart.moveSoldDrugsToContext()
    assertAll(
      {
        Assertions.assertEquals(
          cart.getListOfDrugsInCart(), fakeContext.soldDrugs, "Cart is not save drugs to SoldData"
        )
      },
      {
        Assertions.assertTrue(drugToAdd.dateSold != null, "dateSold is not set")
      }
    )
  }

  @Test
  fun clearCart() {
    cart.clearCart()
    Assertions.assertTrue(cart.getListOfDrugsInCart().isEmpty(), "Cart is not cleared")
  }

  class FakeContext(
    override var soldDrugs: MutableList<Drug>,
    override val drugsInWarehouse: List<Drug>,
    override var profit: Long
  ) : Context
}

