package me.dio.creditapplicationsystem.dto.request

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Insert a valid credit value") val creditValue: BigDecimal,
    @field:FutureOrPresent(message ="Insert a valid date") val dayFirstOfInstallment: LocalDate,
    @field:Min(value = 1, message = "Invalid number of installments. Insert a value smaller than 0")
    @field:Max(value = 48, message = "Invalid number of installments. Insert a value smaller than 49") val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid Id. Insert a valid customer Id ") val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstOfInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
