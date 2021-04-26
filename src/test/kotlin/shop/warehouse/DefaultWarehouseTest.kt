package shop.warehouse

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import shop.model.Drug
import shop.model.DrugType

internal class DefaultWarehouseTest {
  private lateinit var warehouse: DefaultWarehouse
  private lateinit var fakeDrugsInWarehouse: List<Drug>
  private lateinit var drugToAddInFakeWarehouse: Drug

  @BeforeEach
  fun setUp() {
    drugToAddInFakeWarehouse = Drug(
      productName = DrugType.LSD,
      price = 10,
      quantity = 100
    )
    fakeDrugsInWarehouse = listOf(drugToAddInFakeWarehouse)
    warehouse = DefaultWarehouse(drugsInWarehouse = fakeDrugsInWarehouse)
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
  fun `isQuantityExist throws exception when provided Drug name is not in warehouse`() {
    val drugName = "TEST"
    val quantity = "5"
    Assertions.assertThrows(NullPointerException::class.java) { warehouse.isQuantityExist(drugName, quantity) }
  }

  @Test
  fun `getSelectedDrug return 'Drug' with provided name and quantity`() {
    val drugName: String = DrugType.LSD.name
    val expectedQuantity = 5
    val expectedBalance = drugToAddInFakeWarehouse.quantity - expectedQuantity
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
    val drugToAddToDismissList = Drug(
      productName = DrugType.LSD,
      price = 10,
      quantity = 10
    )
    val listOfDrugsToDismiss: List<Drug> = listOf(drugToAddToDismissList)
    val expectedQuantityTotal = drugToAddInFakeWarehouse.quantity + drugToAddToDismissList.quantity
    warehouse.dismissOrder(listOfDrugsToDismiss)
    Assertions.assertEquals(
      drugToAddInFakeWarehouse.quantity,
      expectedQuantityTotal,
      "Quantity of drugs is incorrect after dismissing order"
    )
  }
}