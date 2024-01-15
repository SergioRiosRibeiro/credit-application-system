package me.dio.creditapplicationsystem.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entity.Address
import me.dio.creditapplicationsystem.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal


data class CustomerDto(
    @field:NotEmpty(message = "Insert a valid name") val firstName: String,
    @field:NotEmpty(message = "Insert a valid last name") val lastName: String,
    @field:NotEmpty(message = "Invalid Input: ") @field:CPF(message = "Insert a valid CPF") val cpf: String,
    @field:NotNull(message = "Insert a valid income value") val income: BigDecimal,
    @field:Email(message = "Invalid input: ") @field:NotEmpty(message = "Insert a valid e-mail") val email: String,
    @field:NotEmpty(message = "Insert a valid password") val password: String,
    @field:NotEmpty(message = "Insert a valid Zip Code") val zipCode: String,
    @field:NotEmpty(message = "Insert a valid street name") val street: String
) {

    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
