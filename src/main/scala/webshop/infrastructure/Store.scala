package webshop.infrastructure

import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto, ShoppingDaoDto}

object Store {

  type Article = (String, Double, String, Int) // id, price, description, count

  var stock: Map[String, Article] = initProducts
  var customerList: Map[String, String] = Map() // email, name
  var shoppingCardList: Map[String, List[Article]] = Map() // email, products

  def addProductToStore(daoDto: ProductDaoDto): DaoDto = {
    val productAlredyExists = stock.contains(daoDto.id)
    if (productAlredyExists) {
      val article = stock.get(daoDto.id).get
      val articleModified = (article._1, article._2, article._3, article._4 + 1)
      stock = stock - daoDto.id
      stock = stock + (daoDto.id -> articleModified)
    } else {
      stock = stock + (daoDto.id -> (daoDto.id, daoDto.price, daoDto.description, 1))
    }
    daoDto
  }

  def addCustomer(daoDto: CustomerDaoDto): DaoDto = {
    customerList = customerList + (daoDto.email -> daoDto.name )
    daoDto
  }

  def addProductToShoppingCard(daoDto: ShoppingDaoDto): DaoDto = {

    val article = stock.get(daoDto.product).get
    val price = article._2
    val description = article._3
    val articleToShopping = (daoDto.product, price, description, daoDto.count)
    val shoppingCardExists = !shoppingCardList(daoDto.email).isEmpty

    // Drop product To stock
    stock = dropProduct(daoDto.product, daoDto.count)
    // Add product to shopiing card
    if(!shoppingCardExists) {
      shoppingCardList = shoppingCardList + (daoDto.email -> List(articleToShopping))
    } else {
      val productListOnSahoppingCard: List[Article] = shoppingCardList.get(daoDto.email).get
      val newProductList = productListOnSahoppingCard ++ List(articleToShopping)
      shoppingCardList = shoppingCardList - daoDto.email
      shoppingCardList = shoppingCardList + (daoDto.email -> newProductList)
    }
    daoDto

  }

  def getProductCount(productId: String): Int = stock.get(productId).fold(0)(_._4)

  def dropProduct(productId: String, count: Int): Map[String, Article] = stock

  def getEmailList: List[String] = customerList.keys.toList


  def initProducts: Map[String, (String, Double, String, Int)] =
    Map("milk" -> ("milk", 1.5, "brick of milk", 5),
      "shampoo" -> ("shampoo", 2.0, "shampoo to clean our hair", 3),
      "potatoes" -> ("potatores", 3.7, "5 kg", 2))
}
