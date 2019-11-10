package webshop.infrastructure

import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto}

object Store {

  var productList: List[(String, Double, String, Int)] = initProducts
  var customerList: Map[String, String] = Map() // email, name

  def addProduct(daoDto: ProductDaoDto): DaoDto = {
    productList = productList ++ List((daoDto.id, daoDto.price, daoDto.description, 1))
    daoDto
  }

  def getEmailList: List[String] = customerList.keys.toList

  def addCustomer(daoDto: CustomerDaoDto): DaoDto = {
    customerList = customerList + (daoDto.email -> daoDto.name )
    daoDto
  }

  def initProducts: List[(String, Double, String, Int)] =
    List(("milk", 1.5, "brick of milk", 5),
         ("shampoo", 2.0, "shampoo to clean our hair", 3),
         ("potatores", 3.7, "5 kg", 2))

}
