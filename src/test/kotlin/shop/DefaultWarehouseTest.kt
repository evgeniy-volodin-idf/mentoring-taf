package shop

import org.junit.jupiter.api.*
import shop.model.Drug
import shop.model.DrugType

internal class DefaultWarehouseTest() {
  private lateinit var warehouse: DefaultWarehouse
  private lateinit var fakeDrugsInWarehouse: List<Drug>

  @BeforeEach
  fun setUp() {
    fakeDrugsInWarehouse = listOf(
      Drug(
        productName = DrugType.LSD,
        price = 10,
        quantity = 100
      )
    )
    warehouse = DefaultWarehouse(drugsInWarehouse = fakeDrugsInWarehouse)
  }

  @AfterEach
  fun tearDown() {
  }

  @Test
  fun `isDrugExists return 'true' when valid Drug name provided`() {
    val drugName: String = DrugType.LSD.name
    Assertions.assertTrue(warehouse.isDrugExists(drugName), "Return 'false' with valid Drug name")
  }

  @Test
  fun `isDrugExists return 'false' when invalid Drug name provided`() {
    val drugName: String = DrugType.CANNABIS.name
    Assertions.assertFalse(warehouse.isDrugExists(drugName), "Return 'true' with invalid Drug name")
  }

  @Test
  fun `isQuantityExist return 'true' when provided quantity is less or equal drug quantity in warehouse`() {
    val drugName: String = DrugType.LSD.name
    val quantity = "5"
    Assertions.assertTrue(
      warehouse.isQuantityExist(drugName, quantity), "Return 'false' with quantity less or equal " +
          "drug quantity in warehouse"
    )
  }

  @Test
  fun `isQuantityExist return 'false' when provided quantity is more drug quantity in warehouse`() {
    val drugName: String = DrugType.LSD.name
    val quantity = "101"
    Assertions.assertFalse(
      warehouse.isQuantityExist(drugName, quantity), "Return 'true' when quantity is more drug quantity in warehouse"
    )
  }

  @Test
  fun `getSelectedDrug return 'Drug' with provided name and quantity`() {
    val drugName: String = DrugType.LSD.name
    val quantity = 5
    val balance = 95
    warehouse.apply {
      assertAll(
        {
          getSelectedDrug(drugName, quantity).apply {
            Assertions.assertTrue(
              balance == fakeDrugsInWarehouse.find { it.productName.name == drugName }!!.quantity,
              "Balance in warehouse calculated incorrectly"
            )
          }
        },
        {
          Assertions.assertEquals(
            getSelectedDrug(drugName, quantity).productName, DrugType.LSD,
            "Return drug with incorrect name"
          )
        },
        {
          Assertions.assertEquals(
            getSelectedDrug(drugName, quantity).quantity, quantity,
            "Return drug with incorrect quantity"
          )
        }
      )
    }
  }

  @Test
  fun dismissOrder() {
    val fakeCart: List<Drug> = listOf(
      Drug(
        productName = DrugType.LSD,
        price = 10,
        quantity = 10
      )
    )
    val quantityTotal = 110
    val drugName: DrugType = DrugType.LSD
    warehouse.dismissOrder(fakeCart)
    Assertions.assertTrue(fakeDrugsInWarehouse.find {
      it.productName == drugName
    }!!.quantity == quantityTotal, "Quantity of drugs is incorrect after dismissing order")
  }
}