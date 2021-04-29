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
    val expectedDrugName: String = drugToAddInFakeWarehouse.productName.name
    val expectedQuantity = 5
    val drugSelectedFromWarehouse = warehouse.getSelectedDrug(expectedDrugName, expectedQuantity)
    drugSelectedFromWarehouse.apply {
      assertAll(
        {
          Assertions.assertEquals(expectedDrugName, productName.name, "Return drug with incorrect name")
        },
        {
          Assertions.assertEquals(expectedQuantity, quantity, "Return drug with incorrect quantity")
        }
      )
    }
  }

  @Test
  fun `getSelectedDrug calculate drugs balance in warehouse`() {
    val expectedDrugName: String = drugToAddInFakeWarehouse.productName.name
    val expectedQuantity = 5
    val expectedBalance: Int = drugToAddInFakeWarehouse.quantity - expectedQuantity
    warehouse.getSelectedDrug(expectedDrugName, expectedQuantity)
    val totalQuantityOfDrugInWarehouse =
      fakeDrugsInWarehouse.find { it.productName.name == expectedDrugName }!!.quantity
    Assertions.assertEquals(
      expectedBalance,
      totalQuantityOfDrugInWarehouse,
      "Balance in warehouse calculated incorrectly"
    )
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
      expectedQuantityTotal,
      drugToAddInFakeWarehouse.quantity,
      "Quantity of drugs is incorrect after dismissing order"
    )
  }
}