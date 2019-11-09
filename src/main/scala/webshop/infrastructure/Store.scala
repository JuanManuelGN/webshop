package webshop.infrastructure

import webshop.infrastructure.dao.dto.{DaoDto, ProductDaoDto}

object Store {

    var productList: List[(String, Double, String, Int)] = List()

  def addProduct(daoDto: ProductDaoDto): DaoDto = {
    productList = productList ++ List((daoDto.id, daoDto.price, daoDto.description, 1))
    daoDto
  }

}
