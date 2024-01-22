package me.dio.creditapplicationsystem.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entity.Address
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Insert a valid name") val firstName: String,
    @field:NotEmpty(message = "Insert a valid last name") val lastName: String,
    @field:NotNull(message = "Insert a valid income value") val income: BigDecimal,
    @field:NotEmpty(message = "Insert a valid Zip Code") val zipCode: String,
    @field:NotEmpty(message = "Insert a valid street name") val street: String
) {

    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
