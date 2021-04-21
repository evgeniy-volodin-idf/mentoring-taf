package shop

import org.junit.jupiter.api.*
import shop.model.Drug
import shop.model.DrugType

internal class DefaultWarehouseTest {
  private lateinit var warehouse: DefaultWarehouse
  private lateinit var fakeDrugsInWarehouse: List<Drug>
  private lateinit var drugToAddInFakeW: Drug

  @BeforeEach
  fun setUp() {
    drugToAddInFakeW = Drug(
      productName = DrugType.LSD,
      price = 10,
      quantity = 100
    )
    fakeDrugsInWarehouse = listOf(drugToAddInFakeW)
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
  fun `isDrugExists return 'false' when provided Drug name not exists in warehouse`() {
    val drugName: String = DrugType.CANNABIS.name
    Assertions.assertFalse(warehouse.isDrugExists(drugName), "Return 'true' with invalid Drug name")
  }

  @Test
  fun `isDrugExists return 'false' when provided Drug name is invalid`() {
    val drugName = "TEST"
    Assertions.assertFalse(warehouse.isDrugExists(drugName), "Return 'true' with invalid Drug name")
  }

  @Test
  fun `isQuantityExist return 'true' when provided quantity is less or equal drug quantity in warehouse`() {
    val drugName: String = DrugType.LSD.name
    val quantity = "5"
    Assertions.assertTrue(
      warehouse.isQuantityExist(drugName, quantity),
      "Return 'false' with quantity less or equal drug quantity in warehouse"
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
  fun `isQuantityExist failed with Exception when provided Drug name is not in warehouse`() {
    val drugName = "TEST"
    val quantity = "5"
    Assertions.assertThrows(NullPointerException::class.java) { warehouse.isQuantityExist(drugName, quantity) }
  }

  @Test
  fun `getSelectedDrug return 'Drug' with provided name and quantity`() {
    val drugName: String = DrugType.LSD.name
    val expectedQuantity = 5
    val expectedBalance = 95
    warehouse.getSelectedDrug(drugName, expectedQuantity).apply {
      assertAll(
        {
          Assertions.assertEquals(
            expectedBalance, fakeDrugsInWarehouse.find { it.productName.name == drugName }!!.quantity,
            "Balance in warehouse calculated incorrectly"
          )
        },
        {
          Assertions.assertEquals(
            productName.name, drugName,
            "Return drug with incorrect name"
          )
        },
        {
          Assertions.assertEquals(
            expectedQuantity, quantity,
            "Return drug with incorrect quantity"
          )
        }
      )
    }
  }

  @Test
  fun dismissOrder() {
    val listOfDrugsToDismiss: List<Drug> = listOf(
      Drug(
        productName = DrugType.LSD,
        price = 10,
        quantity = 10
      )
    )
    val expectedQuantityTotal = 110
    warehouse.dismissOrder(listOfDrugsToDismiss)
    Assertions.assertEquals(
      drugToAddInFakeW.quantity,
      expectedQuantityTotal,
      "Quantity of drugs is incorrect after dismissing order"
    )
  }
}