package me.dio.creditapplicationsystem.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Insert a valid credit value") val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalDate,
    val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid Id. Insert a valid customer Id ") val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
