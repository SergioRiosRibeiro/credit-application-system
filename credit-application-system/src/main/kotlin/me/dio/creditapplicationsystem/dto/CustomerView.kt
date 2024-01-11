package me.dio.creditapplicationsystem.dto
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal

data class CustomerView(
    val firstName: String,
    val lastName: String,
    val income: BigDecimal,
    val cpf: String,
    val email: String,
    val zipCode: String,
    val street: String,
    val id: Long?
    ) {

    constructor(customer: Customer): this (
        firstName = customer.firstName,
        lastName = customer.lastName,
        income = customer.income,
        cpf = customer.cpf,
        email = customer.email,
        zipCode = customer.address.zipCode,
        street = customer.address.street,
        id = customer.id
    )
}
