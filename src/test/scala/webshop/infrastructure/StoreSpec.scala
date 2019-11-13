package webshop.infrastructure

import webshop.BaseTest
import webshop.infrastructure.dao.dto.CustomerDaoDto

class StoreSpec extends BaseTest {

  test("Customers count is 0") {
    val dataAndTime = timed(Store.customerList)
    val data : Map[_,_] = dataAndTime._1
    val time : Long = dataAndTime._2
    data match {
      case customer: Map[_,_] =>
        assert(customer.size == 0)
    }
  }
  test("Customers after is added"){
    Store.customerList = Map()
    val dataAndTime =
      timed(Store.addCustomer(CustomerDaoDto("Juan", "juan@email.com", "ES4")))
    val time : Long = dataAndTime._2
    assert(Store.customerList.size == 1)
  }
  test("Add duplicate customer"){
    Store.customerList = Map()
    Store.addCustomer(CustomerDaoDto("Juan", "juan@email.com", "ES4"))
    val dataAndTime =
      timed(Store.addCustomer(CustomerDaoDto("Juan", "juan@email.com", "ES4")))
    val time : Long = dataAndTime._2
    assert(Store.customerList.size == 1)
  }

}
